package dk.philiphansen.craftech.blocks;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModBlocks {
	
	public static final String LIMESTONE_NAME = "blockLimestone";
	
	public static BlockLimestone BlockLimestone;

	public static void init() {
		BlockLimestone = new BlockLimestone();
		
		GameRegistry.registerBlock(BlockLimestone, ModBlocks.LIMESTONE_NAME);
	}
}