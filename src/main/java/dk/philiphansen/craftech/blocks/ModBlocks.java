/*
 * This file is part of CrafTech.
 *
 *  CrafTech is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.

 *  CrafTech is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with CrafTech.  If not, see <http://www.gnu.org/licenses/>. 
 */

package dk.philiphansen.craftech.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import dk.philiphansen.craftech.items.ModItems;
import dk.philiphansen.craftech.items.crafting.CrusherRecipes;
import dk.philiphansen.craftech.items.crafting.TechTableRecipes;
import dk.philiphansen.craftech.reference.BlockInfo;
import dk.philiphansen.craftech.tileentities.TileentityBlastFurnace;
import dk.philiphansen.craftech.tileentities.TileentityCrusher;
import dk.philiphansen.craftech.tileentities.TileentityTechTable;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ModBlocks {
	
	public static BlockLimestone blockLimestone;
	public static BlockCobbleLimestone blockCobbleLimestone;
	public static BlockLimestoneBrick blockLimestoneBrick;
	public static BlockCoalCoke blockCoalCoke;
	public static BlockCrusher blockCrusher;
	public static BlockBlastFurnace blockBlastFurnace;
    public static BlockTechTable blockTechTable;

	public static void init() {
		blockLimestone = new BlockLimestone();
		blockCobbleLimestone = new BlockCobbleLimestone();
		blockLimestoneBrick = new BlockLimestoneBrick();
		blockCoalCoke = new BlockCoalCoke();
		blockCrusher = new BlockCrusher();
		blockBlastFurnace = new BlockBlastFurnace();
        blockTechTable = new BlockTechTable();
		
		GameRegistry.registerBlock(blockLimestone, BlockInfo.LIMESTONE_NAME);
		GameRegistry.registerBlock(blockCobbleLimestone, BlockInfo.COBBLE_LIMESTONE_NAME);
		GameRegistry.registerBlock(blockLimestoneBrick, BlockInfo.LIMESTONE_BRICK_NAME);
		GameRegistry.registerBlock(blockCoalCoke, BlockInfo.COALCOKE_BLOCK_NAME);
		GameRegistry.registerBlock(blockCrusher, BlockInfo.CRUSHER_NAME);
		GameRegistry.registerBlock(blockBlastFurnace, BlockInfo.BLAST_FURNACE_NAME);
        GameRegistry.registerBlock(blockTechTable, BlockInfo.TECH_TABLE_NAME);
	}
	
	public static void initCrafting() {
		GameRegistry.addShapedRecipe(new ItemStack(blockLimestoneBrick, 4), new Object[] {
			"XX",
			"XX",
			'X', blockLimestone
		});
		
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.coalCoke, 9), new ItemStack(blockCoalCoke));

        GameRegistry.addShapedRecipe(new ItemStack(blockTechTable), new Object[] {
                "B",
                "C",
                'B', Items.book,
                'C', Blocks.crafting_table
        });
	}
	
	public static void initSmelting() {
		GameRegistry.addSmelting(blockCobbleLimestone, new ItemStack(blockLimestone), 0.1F);
	}

    public static void  initCrusher() {
        CrusherRecipes.getInstance().addRecipe(new ItemStack(ModBlocks.blockLimestone), new ItemStack(ModItems.limestoneDust, 2));
        CrusherRecipes.getInstance().addRecipe(new ItemStack(ModItems.coalCoke), new ItemStack(ModItems.coalCokeDust, 2));
        CrusherRecipes.getInstance().addRecipe(new ItemStack(Blocks.iron_ore), new ItemStack(ModItems.ironDust, 2));
    }

    public static void initTechTableRecipes() {
        TechTableRecipes.getInstance().addTechTable(new ItemStack(blockBlastFurnace), new ItemStack(ModItems.blastFurnaceRecipe), new Object[]{
                "XXX",
                "X X",
                "XXX",
                'X', Blocks.brick_block
        });

        TechTableRecipes.getInstance().addTechTable(new ItemStack(blockCrusher), new ItemStack(ModItems.crusherRecipe), new Object[]{
                "SPS",
                "S S",
                "SPS",
                'S', Blocks.stone,
                'P', Blocks.piston
        });
    }
	
	public static void initTileentities() {
		GameRegistry.registerTileEntity(TileentityCrusher.class, BlockInfo.CRUSHER_NAME);
		GameRegistry.registerTileEntity(TileentityBlastFurnace.class, BlockInfo.BLAST_FURNACE_TILEENTITY_NAME);
        GameRegistry.registerTileEntity(TileentityTechTable.class, BlockInfo.TECH_TABLE_TILEENTITY_NAME);
	}
}