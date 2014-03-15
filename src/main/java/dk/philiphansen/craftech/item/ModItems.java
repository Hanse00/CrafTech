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
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;

/**
 * Instantiates and handles items.
 * Including handling of item recipes, and smelting.
 */
public class ModItems {
	public static Item itemCoke;
	public static Item itemCokeDust;
	public static Item itemIronDust;
	public static Item itemLimestoneDust;
	public static Item itemBlastFurnaceRecipe;
	public static Item itemCrusherRecipe;

	/**
	 * Instantiates and registers mod items.
	 */
	public static void init() {
		itemCoke = new ItemCoke();
		itemCokeDust = new ItemCokeDust();
		itemIronDust = new ItemIronDust();
		itemLimestoneDust = new ItemLimestoneDust();
		itemBlastFurnaceRecipe = new ItemBlastFurnaceRecipe();
		itemCrusherRecipe = new ItemCrusherRecipe();

		GameRegistry.registerItem(itemCoke, ItemInfo.COKE_NAME);
		GameRegistry.registerItem(itemCokeDust, ItemInfo.COKE_DUST_NAME);
		GameRegistry.registerItem(itemIronDust, ItemInfo.IRON_DUST_NAME);
		GameRegistry.registerItem(itemLimestoneDust, ItemInfo.LIMESTONE_DUST_NAME);
		GameRegistry.registerItem(itemBlastFurnaceRecipe, ItemInfo.BLAST_FURNACE_RECIPE_NAME);
		GameRegistry.registerItem(itemCrusherRecipe, ItemInfo.CRUSHER_RECIPE_NAME);
	}

	/**
	 * Adds vanilla crafting recipes.
	 */
	public static void initCrafting() {
		GameRegistry.addShapelessRecipe(new ItemStack(itemCoke, 9), new ItemStack(ModBlocks.blockCoke));
	}

	/**
	 * Adds vanilla furnace recipes.
	 */
	public static void initSmelting() {
		GameRegistry.addSmelting(Items.coal, new ItemStack(itemCoke), 0.1F);
	}

	/**
	 * Adds recipes to the crusher.
	 */
	public static void initCrusher() {
		CrusherRecipes.getInstance().addRecipe(new ItemStack(ModBlocks.blockLimestone),
				new ItemStack(ModItems.itemLimestoneDust, 2));

		CrusherRecipes.getInstance().addRecipe(new ItemStack(ModItems.itemCoke), new ItemStack(ModItems.itemCokeDust,
				2));

		CrusherRecipes.getInstance().addRecipe(new ItemStack(Blocks.iron_ore), new ItemStack(ModItems.itemIronDust,
				2));
	}

	/**
	 * Adds the recipe items to world generation.
	 */
	public static void genRecipes() {
	    /*
         * ChestGenHooks.addItem(Enum ChestGen Hooks (Which chests to spawn in), new WeightedRandomChestContent(
         * Itemstack, min number to spawn, max number to spawn, rarity));
         */
		ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(new ItemStack(ModItems
				.itemBlastFurnaceRecipe), 1, 1, 10));

		ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(new ItemStack(ModItems
				.itemCrusherRecipe), 1, 1, 10));

		ChestGenHooks.addItem(ChestGenHooks.MINESHAFT_CORRIDOR, new WeightedRandomChestContent(new ItemStack(ModItems
				.itemBlastFurnaceRecipe), 1, 1, 10));

		ChestGenHooks.addItem(ChestGenHooks.MINESHAFT_CORRIDOR, new WeightedRandomChestContent(new ItemStack(ModItems
				.itemCrusherRecipe), 1, 1, 10));

		ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST, new WeightedRandomChestContent(new ItemStack(ModItems
				.itemBlastFurnaceRecipe), 1, 1, 10));

		ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST, new WeightedRandomChestContent(new ItemStack(ModItems
				.itemCrusherRecipe), 1, 1, 10));

		ChestGenHooks.addItem(ChestGenHooks.PYRAMID_DESERT_CHEST, new WeightedRandomChestContent(new ItemStack
				(ModItems.itemBlastFurnaceRecipe), 1, 1, 10));

		ChestGenHooks.addItem(ChestGenHooks.PYRAMID_DESERT_CHEST, new WeightedRandomChestContent(new ItemStack
				(ModItems.itemCrusherRecipe), 1, 1, 10));

		ChestGenHooks.addItem(ChestGenHooks.PYRAMID_JUNGLE_CHEST, new WeightedRandomChestContent(new ItemStack
				(ModItems.itemBlastFurnaceRecipe), 1, 1, 10));

		ChestGenHooks.addItem(ChestGenHooks.PYRAMID_JUNGLE_CHEST, new WeightedRandomChestContent(new ItemStack
				(ModItems.itemCrusherRecipe), 1, 1, 10));

		ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_LIBRARY, new WeightedRandomChestContent(new ItemStack(ModItems
				.itemBlastFurnaceRecipe), 1, 1, 10));

		ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_LIBRARY, new WeightedRandomChestContent(new ItemStack(ModItems
				.itemCrusherRecipe), 1, 1, 10));
	}
}
