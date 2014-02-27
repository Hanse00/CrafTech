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

import dk.philiphansen.craftech.blocks.ModBlocks;
import dk.philiphansen.craftech.items.ModItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ModSlot extends Slot{

	private int slotId;
	
	public ModSlot(int slotId, IInventory inventory, int id, int x, int y) {
		super(inventory, id, x, y);
		this.slotId = slotId;
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		switch(slotId) {
		case 0:
			return stack.getItem() == Item.getItemFromBlock(Blocks.iron_ore);
		case 1:
			return stack.getItem() == Item.getItemFromBlock(ModBlocks.blockLimestone);
		case 2:
			return stack.getItem() == ModItems.coalCoke;
		case 3:
			return stack.getItem() == Item.getItemFromBlock(Blocks.iron_ore) || stack.getItem() == Item.getItemFromBlock(ModBlocks.blockLimestone) || stack.getItem() == ModItems.coalCoke;
	}
	return false;
	}

}
