/*
 * Copyright (C) 2014 Philip Hansen and CrafTech contributors.
 *
 * This file is part of CrafTech.
 *
 * CrafTech is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * CrafTech is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with CrafTech.  If not, see <http://www.gnu.org/licenses/>.
 */

package dk.philiphansen.craftech.tileentity;

import com.google.common.primitives.Ints;
import dk.philiphansen.craftech.item.ModItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.EnumSkyBlock;

//TODO: Clean up the code and comment it
public class TileEntityBlastFurnace extends TileEntity implements ISidedInventory {

	private final ItemStack[] items;
	private int processTimer;
	private final int maxTime = 600;
	private boolean running;
	private final int ironCount = 3;

	public TileEntityBlastFurnace() {
		items = new ItemStack[4];
		processTimer = 0;
		running = false;
	}

	@Override
	public int getSizeInventory() {
		return items.length;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return items[i];
	}

	@Override
	public ItemStack decrStackSize(int i, int count) {
		ItemStack itemstack = getStackInSlot(i);

		if (itemstack != null) {
			if (itemstack.stackSize <= count) {
				setInventorySlotContents(i, null);
			} else {
				itemstack = itemstack.splitStack(count);
			}

		}

		return itemstack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		ItemStack item = getStackInSlot(i);
		setInventorySlotContents(i, null);
		return item;
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		items[i] = itemstack;

		if (itemstack != null && itemstack.stackSize > getInventoryStackLimit()) {
			itemstack.stackSize = getInventoryStackLimit();
		}
	}

	@Override
	public String getInventoryName() {
		return "container.blastFurnace";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return true;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return player.getDistanceSq(xCoord, yCoord, zCoord) <= 64;
	}

	@Override
	public void openInventory() {
	}

	@Override
	public void closeInventory() {
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		switch (slot) {
			case 0:
				return stack.getItem() == ModItems.itemIronDust;
			case 1:
				return stack.getItem() == ModItems.itemLimestoneDust;
			case 2:
				return stack.getItem() == ModItems.itemCokeDust;
			default:
				return false;
		}

	}

	@Override
	public int[] getAccessibleSlotsFromSide(int i) {
		if (i == 0) {
			return new int[]{3};
		} else {
			return new int[]{0, 1, 2};
		}
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack stack, int side) {
		return Ints.contains(getAccessibleSlotsFromSide(side), slot) && isItemValidForSlot(slot, stack);
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack stack, int side) {
		return Ints.contains(getAccessibleSlotsFromSide(side), slot) && getStackInSlot(slot) == stack;
	}

	@Override
	public void writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);

		NBTTagList items = new NBTTagList();

		for (int i = 0; i < getSizeInventory(); i++) {
			ItemStack stack = getStackInSlot(i);

			if (stack != null) {
				NBTTagCompound item = new NBTTagCompound();
				item.setByte("Slot", (byte) i);
				stack.writeToNBT(item);
				items.appendTag(item);
			}
		}

		compound.setTag("Items", items);
		compound.setInteger("ProcessTimer", processTimer);
		compound.setBoolean("Running", running);
	}

	@Override
	public void readFromNBT(NBTTagCompound compund) {
		super.readFromNBT(compund);

		NBTTagList items = compund.getTagList("Items", 10);

		for (int i = 0; i < items.tagCount(); i++) {
			NBTTagCompound item = (NBTTagCompound) items.getCompoundTagAt(i);
			int slot = item.getByte("Slot");

			if (slot >= 0 && slot < getSizeInventory()) {
				setInventorySlotContents(slot, ItemStack.loadItemStackFromNBT(item));
			}
		}

		processTimer = compund.getInteger("ProcessTimer");
		running = compund.getBoolean("Running");
	}

	@Override
	public void updateEntity() {
		if (!worldObj.isRemote) {
			if (running) {
				processTimer++;

				if (!allItemsfound()) {
					stopProcess();
				}

				if (processTimer >= maxTime) {
					completeProcess();
					if (allItemsfound() && spaceForProcess()) {
						startProcess();
					} else {
						stopProcess();
					}
				}
			} else if (allItemsfound() && spaceForProcess()) {
				startProcess();
			}
		}
	}

	private void startProcess() {
		processTimer = 0;
		running = true;
		updateBlockMeta();
	}

	private void stopProcess() {
		running = false;
		processTimer = 0;
		updateBlockMeta();
	}

	void updateBlockMeta() {
		if (!worldObj.isRemote) {
			int meta = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
			if (running) {
				worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, ((meta / 2) * 2) + 1, 3);
			} else {
				worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, (meta / 2) * 2, 3);
			}
			worldObj.updateLightByType(EnumSkyBlock.Block, xCoord, yCoord, zCoord);
			worldObj.notifyBlockChange(xCoord, yCoord, zCoord, getBlockType());
		}
	}

	private boolean allItemsfound() {
		if (getStackInSlot(0) != null && getStackInSlot(0).getItem() == ModItems.itemIronDust) {
			if (getStackInSlot(1) != null && getStackInSlot(1).getItem() == ModItems.itemLimestoneDust) {
				if (getStackInSlot(2) != null && getStackInSlot(2).getItem() == ModItems.itemCokeDust) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean spaceForProcess() {
		if (getStackInSlot(3) != null) {
			if (getStackInSlot(3).stackSize <= getInventoryStackLimit() - ironCount) {
				return true;
			}
		} else {
			return true;
		}
		return false;
	}

	public int getCompletion() {
		return (int) (((float) processTimer / (float) maxTime) * 100);
	}

	private void completeProcess() {
		for (int i = 0; i < 3; i++) {
			decrStackSize(i, 1);
		}

		if (getStackInSlot(3) != null && getStackInSlot(3).getItem() == Items.iron_ingot) {
			ItemStack stack = getStackInSlot(3);

			stack.stackSize += ironCount;

			setInventorySlotContents(3, stack);
		} else {
			setInventorySlotContents(3, new ItemStack(Items.iron_ingot, ironCount));
		}
	}

	public int getTimer() {
		return processTimer;
	}

	public void setTimer(int processTimer) {
		this.processTimer = processTimer;
	}

}
