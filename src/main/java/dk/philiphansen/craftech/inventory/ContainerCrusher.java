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

package dk.philiphansen.craftech.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dk.philiphansen.craftech.tileentities.TileentityCrusher;

public class ContainerCrusher  extends Container {
	
	private TileentityCrusher crusher;
	
	public ContainerCrusher(InventoryPlayer player, TileentityCrusher crusher) {
		this.crusher = crusher;
		
		for (int i = 0; i < 9; i++) {
			addSlotToContainer(new Slot(player, i, 8 + 18 * i, 130));
		}
		
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 9; x++) {
				addSlotToContainer(new Slot(player, x + y * 9 + 9, 8 + 18 * x, 72 + y * 18));
			}
		}
		
		for (int i = 0; i < 1; i++) {
			addSlotToContainer(new ModSlot((i + 3), crusher, i, 80 + 18 * i, 8));
		}
		
		addSlotToContainer(new ModSlot(-1, crusher, 1, 80, 46));
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return crusher.isUseableByPlayer(player);
	}
	
	@Override
    public ItemStack transferStackInSlot(EntityPlayer player, int i) {
		Slot slot = getSlot(i);
		
		if (slot != null && slot.getHasStack()) {
			ItemStack stack = slot.getStack();
			ItemStack result = stack.copy();
			
			if (i >= 36) {
				if (!super.mergeItemStack(stack, 0, 36, false)) {
				return null;
				}
			}
			else if (!mergeItemStack(stack, 36, 37, false)) {
				return null;
			}
			
			if (stack.stackSize == 0) {
				slot.putStack(null);
			}
			else {
				slot.onSlotChanged();
			}
			
			slot.onPickupFromSlot(player, stack);
			
			return result;
		}
		
		return null;
	}
	
	//On GUI open
	@Override
	public void addCraftingToCrafters(ICrafting player) {
		super.addCraftingToCrafters(player);
		
		player.sendProgressBarUpdate(this, 0, crusher.getTimer());
	}
	
	//Update the progress arrow
	//Sent every tick to those who have the GUI open
	//Client end
	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int id, int data) {
		super.updateProgressBar(id, data);
		
		crusher.setTimer(data);
	}
	
	private int oldData;
	
	//Check for changes and send them
	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		
		for (Object player : crafters) {
			if (crusher.getTimer() != oldData) {
				((ICrafting)player).sendProgressBarUpdate(this, 0, crusher.getTimer());
			}
		}
		
		oldData = crusher.getTimer();
	}

}
