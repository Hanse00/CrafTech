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

package dk.philiphansen.craftech.block;

import cpw.mods.fml.common.registry.GameRegistry;
import dk.philiphansen.craftech.item.ModItems;
import dk.philiphansen.craftech.reference.BlockInfo;
import net.minecraft.item.ItemStack;

public class ModBlocks {
	public static BlockLimestone limestone;
	public static BlockLimestoneCobble limestoneCobble;
	public static BlockLimestoneBrick limestoneBrick;
	public static BlockCoke coke;

	public static void init() {
		constructBlocks();
		registerBlocks();
	}

	private static void constructBlocks() {
		limestone = new BlockLimestone();
		limestoneCobble = new BlockLimestoneCobble();
		limestoneBrick = new BlockLimestoneBrick();
		coke = new BlockCoke();
	}

	private static void registerBlocks() {
		GameRegistry.registerBlock(limestone, BlockInfo.LIMESTONE_NAME);
		GameRegistry.registerBlock(limestoneCobble, BlockInfo.LIMESTONE_COBBLE_NAME);
		GameRegistry.registerBlock(limestoneBrick, BlockInfo.LIMESTONE_BRICK_NAME);
		GameRegistry.registerBlock(coke, BlockInfo.COKE_NAME);
	}

	public static void registerRecipes() {
		registerCrafting();
		registerSmelting();
	}

	private static void registerCrafting() {
		GameRegistry.addShapedRecipe(new ItemStack(limestoneBrick, 4), "XX", "XX", 'X', limestone);
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.coke, 9), new ItemStack(coke));
	}

	private static void registerSmelting() {
		GameRegistry.addSmelting(limestoneCobble, new ItemStack(limestone), 0.1F);
	}
}
