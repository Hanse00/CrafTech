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

import com.google.common.primitives.Ints;
import dk.philiphansen.craftech.items.crafting.CrusherRecipes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileentityCrusher extends TileEntity implements ISidedInventory {

	private ItemStack[] items;
	private int processTimer;
	private int maxTime = 600;
	private boolean running;
	private int dustCount = 2;
	private boolean firstUpdate;
	
	// Initial setup 
	public TileentityCrusher() {
		items = new ItemStack[2];
		processTimer = 0;
		running = false;
		firstUpdate = true;
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
		return "Crusher";
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

	//Check if the item in the main slot is the correct one
	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		switch (slot) {
			case 0:
				if (CrusherRecipes.getInstance().hasCrusherRecipe(stack)) {
                    return true;
				} else {
					return false;
				}
			default:
				return false;
		}
	}
	
	@Override
	public void writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		
		NBTTagList items = new NBTTagList();
		
		for (int i = 0; i < getSizeInventory(); i++) {
			ItemStack stack  = getStackInSlot(i);
			
			if (stack != null) {
				NBTTagCompound item = new NBTTagCompound();
				item.setByte("Slot", (byte)i);
				stack.writeToNBT(item);
				items.appendTag(item);
			}
		}
		
		compound.setTag("Items", items);
		compound.setInteger("ProcessTimer", processTimer);
		compound.setBoolean("Running", running);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		
		NBTTagList items = compound.getTagList("Items", 10);
		
		for (int i = 0; i < items.tagCount(); i++) {
			NBTTagCompound item = (NBTTagCompound)items.getCompoundTagAt(i);
			int slot = item.getByte("Slot");
			
			if (slot >= 0 && slot < getSizeInventory()) {
				setInventorySlotContents(slot, ItemStack.loadItemStackFromNBT(item));
			}
		}
	}
	
	//Called each time the entity needs to be updated
	//e.g when placed, when you open it GUI, when the processing has completed
	@Override
	public void updateEntity() {
		if (!worldObj.isRemote) {
			if (firstUpdate) {
				firstUpdate = false;
				updateBlockMeta();
			}
			if (running) {
				
				processTimer++;
				
				if (!correctItemInSlot()) {
					stopProcess();
				}
				
				if (processTimer >= maxTime) {
					completeProcess(getStackInSlot(0));
					
					if (correctItemInSlot() && spaceForProcess()) {
						startProcess();
					} else {
						stopProcess();
					}
				}
			} else if (correctItemInSlot() && spaceForProcess()) {
				startProcess();
			}
		}
	}
	
	//Returns true or false based on if we have any of these items in slot 0
	private boolean correctItemInSlot() {
		if (getStackInSlot(0) != null && CrusherRecipes.getInstance().hasCrusherRecipe(getStackInSlot(0))) {
			return true;
		}
		return false;
	}
	
	//Start the process 
	private void startProcess() {
		processTimer = 0;
		running = true;
		updateBlockMeta();
	}
	
	//End the process 
	private void stopProcess() {
		processTimer = 0;
		running = false;
		updateBlockMeta();
	}
	
	private boolean spaceForProcess() {
		if (getStackInSlot(1) != null) {
			if (getStackInSlot(1).stackSize <= getInventoryStackLimit() - dustCount) {
				return true;
			}
		} else {
			return true;
		}
		return false;
	}
	
	public void updateBlockMeta() {
		if (!worldObj.isRemote) {
			int meta = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
			
			if (running) {
				worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, ((meta / 2) * 2) + 1, 3);
			} else {
				worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, (meta / 2) * 2, 3);
			}
		}
	}
	
	//Get the completion percentage
	public int getCompletion() {
		return (int)(((float)processTimer / (float)maxTime) * 100);
	}
	
	//If the process has completed lets return the player the processed item
	private void completeProcess(ItemStack stack) {
		decrStackSize(0, 1);

        if (stack != null && CrusherRecipes.getInstance().hasCrusherRecipe(stack)) {
            if (getStackInSlot(1) != null && getStackInSlot(1).isItemEqual(CrusherRecipes.getInstance().getCrusherResult(stack))) {
                ItemStack slotStack = getStackInSlot(1);
                slotStack.stackSize += dustCount;

                setInventorySlotContents(1, slotStack);
            } else {
                setInventorySlotContents(1, CrusherRecipes.getInstance().getCrusherResult(getStackInSlot(0)).copy());
            }
        }
	}
	
	public int getTimer() {
		return processTimer;
	}
	
	//Set the length of the process timer
	public void setTimer(int processTimer) {
		this.processTimer = processTimer;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int i) {
		if (i == 0) {
			return new int[]{1};
		} else {
			return new int[]{0};
		}
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack stack, int side) {
		if (Ints.contains(getAccessibleSlotsFromSide(side), slot) && isItemValidForSlot(slot, stack)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack stack, int side) {
		if (Ints.contains(getAccessibleSlotsFromSide(side), slot) && getStackInSlot(slot) == stack) {
			return true;
		} else {
			return false;
		}
	}
}
