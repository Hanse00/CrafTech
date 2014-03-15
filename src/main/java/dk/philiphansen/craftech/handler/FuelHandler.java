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

/**
 * Handles fuel items added by the mod.
 */
public class FuelHandler implements IFuelHandler {

	/**
	 * Registers the fuel handler.
	 * Allows for forge to "see" the handler, and try it when a fuel is inserted into a furnace.
	 */
	public FuelHandler() {
		GameRegistry.registerFuelHandler(this);
	}


	/**
	 * Takes an item and returns it's burn time (in game ticks).
	 *
	 * @param fuel The item to check.
	 * @return Burntime The amount of ticks the given fuel will burn for.
	 */
	@Override
	public int getBurnTime(ItemStack fuel) {
		if (fuel.getItem() == ModItems.itemCoke) {
	        /* 2,6 minutes (ticks / (20 ticks per second * 60 seconds per minute)) */
			return 3200;
		} else if (fuel.getItem() == Item.getItemFromBlock(ModBlocks.blockCoke)) {
            /* Burn time for coke block is defined as 9 x coke item */
			return getBurnTime(new ItemStack(ModItems.itemCoke)) * 9;
		} else {
			return 0;
		}
	}

}
