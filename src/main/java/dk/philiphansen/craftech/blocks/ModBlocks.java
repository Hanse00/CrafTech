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

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;
import dk.philiphansen.craftech.reference.BlockInfo;
import dk.philiphansen.craftech.tileentities.TileentityBlastFurnace;

public class ModBlocks {
	
	public static BlockLimestone blockLimestone;
	public static BlockCobbleLimestone blockCobbleLimestone;
	public static BlockLimestoneBrick blockLimestoneBrick;
	public static BlockCoalCoke blockCoalCoke;
	public static BlockBlastFurnace blockBlastFurnace;

	public static void init() {
		blockLimestone = new BlockLimestone();
		blockCobbleLimestone = new BlockCobbleLimestone();
		blockLimestoneBrick = new BlockLimestoneBrick();
		blockCoalCoke = new BlockCoalCoke();
		blockBlastFurnace = new BlockBlastFurnace();
		
		GameRegistry.registerBlock(blockLimestone, BlockInfo.LIMESTONE_NAME);
		GameRegistry.registerBlock(blockCobbleLimestone, BlockInfo.COBBLE_LIMESTONE_NAME);
		GameRegistry.registerBlock(blockLimestoneBrick, BlockInfo.LIMESTONE_BRICK_NAME);
		GameRegistry.registerBlock(blockCoalCoke, BlockInfo.COALCOKE_BLOCK_NAME);
		GameRegistry.registerBlock(blockBlastFurnace, BlockInfo.BLAST_FURNACE_NAME);
	}
	
	public static void initCrafting() {
		GameRegistry.addShapedRecipe(new ItemStack(blockLimestoneBrick, 4), new Object[] {
			"XX",
			"XX",
			'X', blockLimestone
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(blockBlastFurnace), new Object[] {
			"XXX",
			"X X",
			"XXX",
			'X', Blocks.brick_block
		});
	}
	
	public static void initSmelting() {
		GameRegistry.addSmelting(blockCobbleLimestone, new ItemStack(blockLimestone), 0.1F);
	}
	
	public static void initTileentities() {
		GameRegistry.registerTileEntity(TileentityBlastFurnace.class, BlockInfo.BLAST_FURNACE_TILEENTITY_NAME);
	}
}