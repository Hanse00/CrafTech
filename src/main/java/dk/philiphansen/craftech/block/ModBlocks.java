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

public class ModBlocks {
	public static BlockLimestone limestone;
	public static BlockLimestoneCobble limestoneCobble;
	public static BlockLimestoneBrick limestoneBrick;
	public static BlockCoke coke;
	public static BlockCrusher crusher;
	public static BlockBlastFurnace blastFurnace;
	public static BlockTechTable techTable;

	public static void init() {
		constructBlocks();
		registerBlocks();
		registerTileEntities();
	}

	private static void constructBlocks() {
		limestone = new BlockLimestone();
		limestoneCobble = new BlockLimestoneCobble();
		limestoneBrick = new BlockLimestoneBrick();
		coke = new BlockCoke();
		crusher = new BlockCrusher();
		blastFurnace = new BlockBlastFurnace();
		techTable = new BlockTechTable();
	}

	private static void registerBlocks() {
		GameRegistry.registerBlock(limestone, BlockInfo.LIMESTONE_NAME);
		GameRegistry.registerBlock(limestoneCobble, BlockInfo.LIMESTONE_COBBLE_NAME);
		GameRegistry.registerBlock(limestoneBrick, BlockInfo.LIMESTONE_BRICK_NAME);
		GameRegistry.registerBlock(coke, BlockInfo.COKE_NAME);
		GameRegistry.registerBlock(crusher, BlockInfo.CRUSHER_NAME);
		GameRegistry.registerBlock(blastFurnace, BlockInfo.BLAST_FURNACE_NAME);
		GameRegistry.registerBlock(techTable, BlockInfo.TECH_TABLE_NAME);
	}

	private static void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntityCrusher.class, BlockInfo.CRUSHER_TILE_ENTITY_NAME);
		GameRegistry.registerTileEntity(TileEntityBlastFurnace.class, BlockInfo.BLAST_FURNACE_TILE_ENTITY_NAME);
		GameRegistry.registerTileEntity(TileEntityTechTable.class, BlockInfo.TECH_TABLE_TILE_ENTITY_NAME);
	}

	public static void registerRecipes() {
		registerCrafting();
		registerSmelting();
		registerTechTable();
	}

	private static void registerCrafting() {
		GameRegistry.addShapedRecipe(new ItemStack(limestoneBrick, 4), "XX", "XX", 'X', limestone);
		GameRegistry.addShapedRecipe(new ItemStack(coke), "XXX", "XXX", "XXX", 'X', ModItems.coke);
		GameRegistry.addShapedRecipe(new ItemStack(techTable), "B", "C", 'B', Items.book, 'C', Blocks.crafting_table);
	}

	private static void registerSmelting() {
		GameRegistry.addSmelting(limestoneCobble, new ItemStack(limestone), 0.1F);
	}

	private static void registerTechTable() {
		TechTableRecipes.getInstance().addTechTable(new ItemStack(blastFurnace),
				new ItemStack(ModItems.blastFurnaceRecipe), "XXX", "X X", "XXX", 'X', Blocks.brick_block);
		TechTableRecipes.getInstance().addTechTable(new ItemStack(crusher), new ItemStack(ModItems.crusherRecipe),
				"SPS", "S S", "SPS", 'S', Blocks.stone, 'P', Blocks.piston);
	}
}
