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

import dk.philiphansen.craftech.tileentity.TileEntityBlastFurnace;
import dk.philiphansen.craftech.tileentity.TileEntityCrusher;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ModSlot extends Slot {
	private final SlotType type;

	public ModSlot(SlotType type, IInventory inventory, int id, int x, int y) {
		super(inventory, id, x, y);
		this.type = type;
	}

	@Override
	public boolean isItemValid(ItemStack stack) {
		switch (type) {
			case CRUSHER_INPUT:
				return inventory.isItemValidForSlot(((TileEntityCrusher) inventory).getInputSlot(), stack);
			case OUTPUT:
				return false;
			case COKE_DUST:
				return inventory.isItemValidForSlot(((TileEntityBlastFurnace) inventory).getCokeSlot(), stack);
			case LIMESTONE_DUST:
				return inventory.isItemValidForSlot(((TileEntityBlastFurnace) inventory).getLimestoneSlot(), stack);
			case IRON_DUST:
				return inventory.isItemValidForSlot(((TileEntityBlastFurnace) inventory).getIronSlot(), stack);
			default:
				return false;
		}
	}
}
