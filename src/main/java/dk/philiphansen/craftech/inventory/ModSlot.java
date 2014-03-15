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

package dk.philiphansen.craftech.inventory;

import dk.philiphansen.craftech.item.ItemRecipe;
import dk.philiphansen.craftech.item.ModItems;
import dk.philiphansen.craftech.item.crafting.CrusherRecipes;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

//TODO: Comment code
class ModSlot extends Slot {

	private final int slotId;

	public ModSlot(int slotId, IInventory inventory, int id, int x, int y) {
		super(inventory, id, x, y);
		this.slotId = slotId;
	}

	@Override
	public boolean isItemValid(ItemStack stack) {
		switch (slotId) {
			case 0:
				return stack.getItem() == ModItems.itemIronDust;
			case 1:
				return stack.getItem() == ModItems.itemLimestoneDust;
			case 2:
				return stack.getItem() == ModItems.itemCokeDust;
			case 3:
				return CrusherRecipes.getInstance().hasCrusherRecipe(stack);
			case 4:
				return stack.getItem() instanceof ItemRecipe;
		}
		return false;
	}

}
