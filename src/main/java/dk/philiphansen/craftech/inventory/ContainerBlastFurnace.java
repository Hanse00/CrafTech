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
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import dk.philiphansen.craftech.tileentities.TileentityBlastFurnace;

public class ContainerBlastFurnace extends Container {
	
	private TileentityBlastFurnace blastFurnace;
	
	public ContainerBlastFurnace(InventoryPlayer player, TileentityBlastFurnace blastFurnace) {
		this.blastFurnace = blastFurnace;
		
		for (int i = 0; i < 9; i++) {
			addSlotToContainer(new Slot(player, i, 8 + 18 * i, 130));
		}
		
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 9; x++) {
				addSlotToContainer(new Slot(player, x + y * 9 + 9, 8 + 18 * x, 72 + y * 18));
			}
		}
		
		for (int i = 0; i < 3; i++) {
			addSlotToContainer(new ModSlot(i, blastFurnace, i, 62 + 18 * i, 8));
		}
		
		addSlotToContainer(new ModSlot(-1, blastFurnace, 3, 80, 46));
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return blastFurnace.isUseableByPlayer(player);
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int i) {
		return null;
	}

}