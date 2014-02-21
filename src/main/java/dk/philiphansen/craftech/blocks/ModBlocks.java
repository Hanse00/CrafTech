package dk.philiphansen.craftech.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class ModBlocks {
	
	public static BlockLimestone BlockLimestone;

	public static void init() {
		BlockLimestone = new BlockLimestone();
		
		GameRegistry.registerBlock(BlockLimestone, "BlockLimestone");
	}
	
}
