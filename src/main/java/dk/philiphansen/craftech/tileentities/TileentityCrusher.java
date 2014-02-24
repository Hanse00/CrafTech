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

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class TileentityCrusher extends TileEntity implements ISidedInventory {
	private static final int[] slotsTop = new int[] {0};
    private static final int[] slotsBottom = new int[] {2, 1};
    private static final int[] slotsSides = new int[] {1};
    
    private ItemStack[] crusherItemStacks = new ItemStack[3];

	@Override
	public int getSizeInventory() {
		return this.crusherItemStacks.length;
	}

	@Override
	public ItemStack getStackInSlot(int var1) {
		return this.crusherItemStacks[var1];
	}

	@Override
	public ItemStack decrStackSize(int var1, int var2) {
		if (this.crusherItemStacks[var1] != null) {
			ItemStack itemstack;
			
			if (this.crusherItemStacks[var1].stackSize <= var2) {
				itemstack = this.crusherItemStacks[var1];
				this.crusherItemStacks[var1] = null;
				return itemstack;
			} else {
				itemstack = this.crusherItemStacks[var1].splitStack(var2);
				
				if (this.crusherItemStacks[var1].stackSize == 0) {
					this.crusherItemStacks[var1] = null;
				}
				
				return itemstack;
			}
		} else {
			return null;
		}
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int var1) {
		if (this.crusherItemStacks[var1] != null) {
			ItemStack itemstack = this.crusherItemStacks[var1];
			this.crusherItemStacks[var1] = null;
		}
		return null;
	}

	@Override
	public void setInventorySlotContents(int var1, ItemStack var2) {
		this.crusherItemStacks[var1] = var2;
		if (var2 != null && var2.stackSize > this.getInventoryStackLimit()) {
			var2.stackSize = this.getInventoryStackLimit();
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
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer var1) {
		return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : var1.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
	}

	@Override
	public void openInventory() {}

	@Override
	public void closeInventory() {}

	@Override
	public boolean isItemValidForSlot(int var1, ItemStack var2) {
		return var1 == 2 ? false : (var1 == 1 ? isItemValidForSlot(var1, var2) : true);
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int var1) {
		return var1 == 0 ? slotsBottom : (var1 == 1 ? slotsTop : slotsSides);
	}

	@Override
	public boolean canInsertItem(int var1, ItemStack var2, int var3) {
		return this.isItemValidForSlot(var1, var2);
	}

	@Override
	public boolean canExtractItem(int var1, ItemStack var2, int var3) {
		return var3 != 0 || var1 != 1 || var2.getItem() == Items.bucket;
	}
}
