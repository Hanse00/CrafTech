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
import dk.philiphansen.craftech.item.crafting.CrusherRecipes;
import dk.philiphansen.craftech.util.ItemUtils;
import dk.philiphansen.craftech.util.WorldUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityCrusher extends TileEntity implements ISidedInventory {
	private final ItemStack[] inventory;
	private final int inventorySize = 2;
	private final int inputSlot = 1;
	private final int outputSlot = 0;
	private final int bottomSide = 0;
	private final int stackSizeLimit = 64;
	private final int completeTime = 600;
	private boolean running;
	private int processTimer;

	public TileEntityCrusher() {
		inventory = new ItemStack[inventorySize];
		running = false;
		processTimer = 0;
	}

	@Override
	public void updateEntity() {
		if (WorldUtils.isServer(worldObj)) {
			if (running) {
				updateProcess();
			} else if (canRun()) {
				startProcess();
			}
		}
	}

	private void updateProcess() {
		if (!canCrushInput()) {
			stopProcess();
		}

		processTimer++;
		if (processTimer >= completeTime) {
			completeProcess();
			if (canRun()) {
				startProcess();
			} else {
				stopProcess();
			}
		}
	}

	private boolean canRun() {
		return canCrushInput() && hasOutputSpace();
	}

	private boolean canCrushInput() {
		return getStackInSlot(inputSlot) != null && CrusherRecipes.getInstance().hasCrusherRecipe(getStackInSlot
				(inputSlot));
	}

	private boolean hasOutputSpace() {
		if (getStackInSlot(outputSlot) != null) {
			ItemStack stack = getStackInSlot(outputSlot);

			if (!equalsCrusherResult(stack) || !spaceForOutput(stack)) {
				return false;
			}
		}
		return true;
	}

	private boolean equalsCrusherResult(ItemStack stack) {
		return ItemUtils.equals(stack, CrusherRecipes.getInstance().getCrusherResult(getStackInSlot(inputSlot)));
	}

	private boolean spaceForOutput(ItemStack stack) {
		return stack.stackSize <= (getInventoryStackLimit() - CrusherRecipes.getInstance().getCrusherResult
				(getStackInSlot(inputSlot)).stackSize);
	}

	private void startProcess() {
		running = true;
		resetBlock();
	}

	private void stopProcess() {
		running = false;
		resetBlock();
	}

	private void completeProcess() {
		if (getStackInSlot(outputSlot) != null) {
			incrementStack();
		} else {
			setStack();
		}
		decrStackSize(inputSlot, 1);
	}

	private void incrementStack() {
		ItemStack stack = getStackInSlot(outputSlot);
		stack.stackSize += CrusherRecipes.getInstance().getCrusherResult(getStackInSlot(inputSlot)).stackSize;
		setInventorySlotContents(outputSlot, stack);
	}

	private void setStack() {
		setInventorySlotContents(outputSlot, CrusherRecipes.getInstance().getCrusherResult(getStackInSlot(inputSlot))
				.copy());
	}

	private void resetBlock() {
		processTimer = 0;
		updateBlockMeta();
	}

	private void updateBlockMeta() {
		if (WorldUtils.isServer(worldObj)) {
			int meta = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);

			if (running) {
				worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, ((meta / 2) * 2) + 1, 3);
			} else {
				worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, (meta / 2) * 2, 3);
			}
		}
	}

	public int getCompletion() {
		return (int) (((float) processTimer / (float) completeTime) * 100);
	}

	public int getProcessTimer() {
		return processTimer;
	}

	public void setProcessTimer(int timer) {
		processTimer = timer;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		if (side == bottomSide) {
			return new int[]{outputSlot};
		} else {
			return new int[]{inputSlot};
		}
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack stack, int side) {
		return (canAccessSlotFromSide(slot, side)) && (isItemValidForSlot(slot, stack));
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack stack, int side) {
		return (canAccessSlotFromSide(slot, side)) && (getStackInSlot(slot) == stack);
	}

	private boolean canAccessSlotFromSide(int slot, int side) {
		return Ints.contains(getAccessibleSlotsFromSide(side), slot);
	}

	@Override
	public int getSizeInventory() {
		return inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return inventory[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int count) {
		ItemStack stack = getStackInSlot(slot);

		if (stack != null) {
			if (stack.stackSize <= count) {
				setInventorySlotContents(slot, null);
			} else {
				stack = stack.splitStack(count);
			}
		}
		return stack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		ItemStack stack = getStackInSlot(slot);
		setInventorySlotContents(slot, null);
		return stack;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		inventory[slot] = stack;

		if (stack != null && stack.stackSize > getInventoryStackLimit()) {
			stack.stackSize = getInventoryStackLimit();
		}
	}

	@Override
	public String getInventoryName() {
		return "container.crusher";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return true;
	}

	@Override
	public int getInventoryStackLimit() {
		return stackSizeLimit;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return player.getDistance(xCoord, yCoord, zCoord) <= 8;
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
			case inputSlot:
				return CrusherRecipes.getInstance().hasCrusherRecipe(stack);
			default:
				return false;
		}
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
		compound.setBoolean("Running", running);
		compound.setInteger("ProcessTimer", processTimer);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);

		NBTTagList items = compound.getTagList("Items", 10);
		for (int i = 0; i < items.tagCount(); i++) {
			NBTTagCompound item = items.getCompoundTagAt(i);
			int slot = item.getByte("Slot");

			if (slot >= 0 && slot < getSizeInventory()) {
				setInventorySlotContents(slot, ItemStack.loadItemStackFromNBT(item));
			}
		}

		running = compound.getBoolean("Running");
		processTimer = compound.getInteger("ProcessTimer");
	}

	public int getInputSlot() {
		return inputSlot;
	}
}
