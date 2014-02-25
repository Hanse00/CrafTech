/*
 * This file is part of CrafTech.
 *
 *  CrafTech is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.

 *  CrafTech is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with CrafTech.  If not, see <http://www.gnu.org/licenses/>. 
 */

package dk.philiphansen.craftech.tileentities;

import dk.philiphansen.craftech.CrafTech;
import dk.philiphansen.craftech.blocks.ModBlocks;
import dk.philiphansen.craftech.items.ModItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileentityBlastFurnace extends TileEntity implements IInventory{
	
	private ItemStack[] items;
	private int processTimer;
	private int maxTime = 20;
	
	public TileentityBlastFurnace() {
		items = new ItemStack[4];
		processTimer = 0;
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
				}else{
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
		return "Blast Furnace";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
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
	public void openInventory() {}

	@Override
	public void closeInventory() {}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		switch (slot) {
			case 0:
				if (stack.getItem() == Item.getItemFromBlock(Blocks.iron_ore)) {
					return true;
				}
				else {
					return false;
				}
			case 1:
				if (stack.getItem() == Item.getItemFromBlock(ModBlocks.blockLimestone)) {
					return true;
				}
				else {
					return false;
				}
			case 2:
				if (stack.getItem() == ModItems.coalCoke) {
					return true;
				}
				else {
					return false;
				}
			default:
				return false;
		}

	}
	
	@Override
    public void writeToNBT(NBTTagCompound compund) {
		super.writeToNBT(compund);
		
		NBTTagList items = new NBTTagList();
		
		for (int i = 0; i < getSizeInventory(); i++) {
			ItemStack stack = getStackInSlot(i);
			
			if (stack != null) {
				NBTTagCompound item = new NBTTagCompound();
				item.setByte("Slot", (byte)i);
				stack.writeToNBT(item);
				items.appendTag(item);
			}
		}
		
		compund.setTag("Items", items);
		compund.setInteger("ProcessTimer", processTimer);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compund) {
		super.readFromNBT(compund);
		
		NBTTagList items = compund.getTagList("Items", 10);
		
		for (int i = 0; i < items.tagCount(); i++) {
			NBTTagCompound item = (NBTTagCompound)items.getCompoundTagAt(i);
			int slot = item.getByte("Slot");
			
			if (slot >= 0 && slot < getSizeInventory()) {
				setInventorySlotContents(slot, ItemStack.loadItemStackFromNBT(item));
			}
		}
		
		processTimer = compund.getInteger("ProcessTimer");
	}
	
	@Override
	public void updateEntity() {
		if (!this.worldObj.isRemote) {
			if (allItemsfound() && getStackInSlot(3).stackSize <= getInventoryStackLimit() - 5) {
				runProcess();
			}
			else {
				processTimer = 0;
			}
		}
	}
	
	private boolean allItemsfound() {
		if (getStackInSlot(0) != null && getStackInSlot(0).getItem() == Item.getItemFromBlock(Blocks.iron_ore)) {
			if (getStackInSlot(1) != null && getStackInSlot(1).getItem() == Item.getItemFromBlock(ModBlocks.blockLimestone)) {
				if (getStackInSlot(2) != null && getStackInSlot(2).getItem() == ModItems.coalCoke) {
					return true;
				}
			}
		}
		return false;
	}
	
	private void runProcess() {
		CrafTech.logger.info("Completion: " + getCompletion());
		if (processTimer < maxTime) {
			processTimer++;
		}
		else {
			CrafTech.logger.info("Complete");
			processTimer = 0;
			completeProcess();
		}
	}
	
	public int getCompletion() {
		return (int)(((float)processTimer / (float)maxTime) * 100);
	}
	
	private void completeProcess() {
		for (int i = 0; i < 3; i++) {
			decrStackSize(i, 1);
		}
		
		if (getStackInSlot(3) != null && getStackInSlot(3).getItem() == Items.iron_ingot) {
			ItemStack stack = getStackInSlot(3);
			
			stack.stackSize += 5;
			
			setInventorySlotContents(3, stack);
		}
		else {
			setInventorySlotContents(3, new ItemStack(Items.iron_ingot, 5));
		}
	}

}
