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

package dk.philiphansen.craftech.handler;

import cpw.mods.fml.common.IFuelHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import dk.philiphansen.craftech.block.ModBlocks;
import dk.philiphansen.craftech.item.ModItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class FuelHandler implements IFuelHandler {
	public FuelHandler() {
		GameRegistry.registerFuelHandler(this);
	}

	@Override
	public int getBurnTime(ItemStack fuel) {
		Item fuelItem = fuel.getItem();

		if (fuelItem == ModItems.coke) {
			return 1800; //Coal + 1/8 (The amount of coal needed to make this.
		} else if (fuelItem == Item.getItemFromBlock(ModBlocks.coke)) {
			return (getBurnTime(new ItemStack(ModItems.coke)) * 9);
		} else {
			return 0;
		}
	}
}
