package dk.philiphansen.craftech.blocks;

import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;
import dk.philiphansen.craftech.reference.BlockInfo;

public class ModBlocks {
	
	public static BlockLimestone blockLimestone;
	public static BlockCobbleLimestone blockCobbleLimestone;
	public static BlockLimestoneBrick blockLimestoneBrick;

	public static void init() {
		blockLimestone = new BlockLimestone();
		blockCobbleLimestone = new BlockCobbleLimestone();
		blockLimestoneBrick = new BlockLimestoneBrick();
		
		GameRegistry.registerBlock(blockLimestone, BlockInfo.LIMESTONE_NAME);
		GameRegistry.registerBlock(blockCobbleLimestone, BlockInfo.COBBLE_LIMESTONE_NAME);
		GameRegistry.registerBlock(blockLimestoneBrick, BlockInfo.LIMESTONE_BRICK_NAME);
	}
	
	public static void initCrafting() {
		GameRegistry.addShapedRecipe(new ItemStack(blockLimestoneBrick, 4), new Object[] {
			"XX",
			"XX",
			'X', blockLimestone
		});
	}
	
	public static void initSmelting() {
		GameRegistry.addSmelting(blockCobbleLimestone, new ItemStack(blockLimestone), 0.1F);
	}
}