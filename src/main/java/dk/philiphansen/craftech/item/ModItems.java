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

package dk.philiphansen.craftech.item;

import cpw.mods.fml.common.registry.GameRegistry;
import dk.philiphansen.craftech.block.ModBlocks;
import dk.philiphansen.craftech.reference.ItemInfo;
import net.minecraft.item.ItemStack;

public class ModItems {
	public static ItemCoke coke;

	public static void init() {
		constructItems();
		registerItems();
	}

	private static void constructItems() {
		coke = new ItemCoke();
	}

	private static void registerItems() {
		GameRegistry.registerItem(coke, ItemInfo.COKE_NAME);
	}

	public static void registerRecipes() {
		registerCrafting();
	}

	private static void registerCrafting() {
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.coke), "XXX", "XXX", "XXX", 'X', coke);
	}
}
