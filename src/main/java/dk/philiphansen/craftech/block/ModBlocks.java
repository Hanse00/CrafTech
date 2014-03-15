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
import dk.philiphansen.craftech.item.crafting.TechTableRecipes;
import dk.philiphansen.craftech.reference.BlockInfo;
import dk.philiphansen.craftech.tileentity.TileEntityBlastFurnace;
import dk.philiphansen.craftech.tileentity.TileEntityCrusher;
import dk.philiphansen.craftech.tileentity.TileEntityTechTable;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

/**
 * Instantiates and handles blocks.
 * Including handling of block recipes, and smelting.
 */
public class ModBlocks {

	public static BlockLimestone blockLimestone;
	public static BlockCobbleLimestone blockCobbleLimestone;
	private static BlockLimestoneBrick blockLimestoneBrick;
	public static BlockCoke blockCoke;
	public static BlockCrusher blockCrusher;
	private static BlockBlastFurnace blockBlastFurnace;
	private static BlockTechTable blockTechTable;

	/**
	 * Instantiates and registers mod blocks.
	 */
	public static void init() {
		blockLimestone = new BlockLimestone();
		blockCobbleLimestone = new BlockCobbleLimestone();
		blockLimestoneBrick = new BlockLimestoneBrick();
		blockCoke = new BlockCoke();
		blockCrusher = new BlockCrusher();
		blockBlastFurnace = new BlockBlastFurnace();
		blockTechTable = new BlockTechTable();

		GameRegistry.registerBlock(blockLimestone, BlockInfo.LIMESTONE_NAME);
		GameRegistry.registerBlock(blockCobbleLimestone, BlockInfo.COBBLE_LIMESTONE_NAME);
		GameRegistry.registerBlock(blockLimestoneBrick, BlockInfo.LIMESTONE_BRICK_NAME);
		GameRegistry.registerBlock(blockCoke, BlockInfo.COKE_BLOCK_NAME);
		GameRegistry.registerBlock(blockCrusher, BlockInfo.CRUSHER_NAME);
		GameRegistry.registerBlock(blockBlastFurnace, BlockInfo.BLAST_FURNACE_NAME);
		GameRegistry.registerBlock(blockTechTable, BlockInfo.TECH_TABLE_NAME);
	}

	/**
	 * Adds vanilla crafting recipes.
	 */
	public static void initCrafting() {
		GameRegistry.addShapedRecipe(new ItemStack(blockLimestoneBrick, 4), "XX", "XX", 'X', blockLimestone);

		GameRegistry.addShapedRecipe(new ItemStack(blockTechTable), "B", "C", 'B', Items.book, 'C',
				Blocks.crafting_table);

		GameRegistry.addRecipe(new ItemStack(blockCoke, 1), "CCC", "CCC", "CCC", 'C', ModItems.itemCoke);
	}

	/**
	 * Adds vanilla furnace recipes.
	 */
	public static void initSmelting() {
		GameRegistry.addSmelting(blockCobbleLimestone, new ItemStack(blockLimestone), 0.1F);
	}

	/**
	 * Adds recipes to the tech table.
	 */
	public static void initTechTableRecipes() {
	    /*
        * Tech table recipes work like vanilla recipes,
        * except they take an extra item stack, the recipe to check for.
        */
		TechTableRecipes.getInstance().addTechTable(new ItemStack(blockBlastFurnace),
				new ItemStack(ModItems.itemBlastFurnaceRecipe), "XXX", "X X", "XXX", 'X', Blocks.brick_block);

		TechTableRecipes.getInstance().addTechTable(new ItemStack(blockCrusher),
				new ItemStack(ModItems.itemCrusherRecipe), "SPS", "S S", "SPS", 'S', Blocks.stone, 'P', Blocks.piston);
	}

	/**
	 * Initializes the tile entities.
	 */
	public static void initTileEntities() {
		GameRegistry.registerTileEntity(TileEntityCrusher.class, BlockInfo.CRUSHER_TILEENTITY_NAME);
		GameRegistry.registerTileEntity(TileEntityBlastFurnace.class, BlockInfo.BLAST_FURNACE_TILEENTITY_NAME);
		GameRegistry.registerTileEntity(TileEntityTechTable.class, BlockInfo.TECH_TABLE_TILEENTITY_NAME);
	}
}