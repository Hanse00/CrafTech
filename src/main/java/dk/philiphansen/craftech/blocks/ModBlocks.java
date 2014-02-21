package dk.philiphansen.craftech.blocks;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModBlocks {
	
	public static BlockLimestone BlockLimestone;

	public static void init() {
		BlockLimestone = new BlockLimestone();
		
		GameRegistry.registerBlock(BlockLimestone, "BlockLimestone");
	}
}