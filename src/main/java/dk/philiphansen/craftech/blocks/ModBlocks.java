package dk.philiphansen.craftech.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import dk.philiphansen.craftech.reference.BlockInfo;

public class ModBlocks {
	
	public static BlockLimestone BlockLimestone;

	public static void init() {
		BlockLimestone = new BlockLimestone();
		
		GameRegistry.registerBlock(BlockLimestone, BlockInfo.LIMESTONE_NAME);
	}
}