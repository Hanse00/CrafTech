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
import dk.philiphansen.craftech.item.crafting.CrusherRecipes;
import dk.philiphansen.craftech.reference.ItemInfo;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ModItems {
	public static ItemCoke coke;
	public static ItemCokeDust cokeDust;
	public static ItemIronDust ironDust;
	public static ItemLimestoneDust limestoneDust;

	public static void init() {
		constructItems();
		registerItems();
	}

	private static void constructItems() {
		coke = new ItemCoke();
		cokeDust = new ItemCokeDust();
		ironDust = new ItemIronDust();
		limestoneDust = new ItemLimestoneDust();
	}

	private static void registerItems() {
		GameRegistry.registerItem(coke, ItemInfo.COKE_NAME);
		GameRegistry.registerItem(cokeDust, ItemInfo.COKE_DUST_NAME);
		GameRegistry.registerItem(ironDust, ItemInfo.IRON_DUST_NAME);
		GameRegistry.registerItem(limestoneDust, ItemInfo.LIMESTONE_DUST_NAME);
	}

	public static void registerRecipes() {
		registerCrafting();
		registerSmelting();
		registerCrushing();
	}

	private static void registerCrafting() {
		GameRegistry.addShapelessRecipe(new ItemStack(coke, 9), new ItemStack(ModBlocks.coke));
	}

	private static void registerSmelting() {
		GameRegistry.addSmelting(Items.coal, new ItemStack(coke), 0.1F);
	}

	private static void registerCrushing() {
		CrusherRecipes.getInstance().addRecipe(new ItemStack(coke), new ItemStack(cokeDust));
		CrusherRecipes.getInstance().addRecipe(new ItemStack(Blocks.iron_ore), new ItemStack(ironDust));
		CrusherRecipes.getInstance().addRecipe(new ItemStack(ModBlocks.limestone), new ItemStack(limestoneDust));
	}
}
