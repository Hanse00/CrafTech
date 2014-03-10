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

import dk.philiphansen.craftech.items.ModItems;
import dk.philiphansen.craftech.items.crafting.CrusherRecipes;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
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
			return stack.getItem() == ModItems.ironDust;
		case 1:
			return stack.getItem() == ModItems.limestoneDust;
		case 2:
			return stack.getItem() == ModItems.coalCokeDust;
		case 3:
			return CrusherRecipes.getInstance().hasCrusherRecipe(stack);
        case 4:
            return stack.getItem() == Items.book;
        }
	return false;
	}

}
