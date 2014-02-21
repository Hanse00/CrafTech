package dk.philiphansen.craftech.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockLimestone extends Block {

	protected BlockLimestone() {
		super(Material.iron);
		setBlockName("BlockLimestone");
		setCreativeTab(CreativeTabs.tabBlock);
		setBlockName("blockLimestone");
	}
	
}