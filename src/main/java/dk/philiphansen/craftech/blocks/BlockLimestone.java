package dk.philiphansen.craftech.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import dk.philiphansen.craftech.reference.BlockInfo;

public class BlockLimestone extends Block {

	protected BlockLimestone() {
		super(Material.rock);
		setBlockName(BlockInfo.LIMESTONE_NAME);
		setCreativeTab(CreativeTabs.tabBlock);
		setHardness(1.5F);
		setResistance(10.0F);
		setStepSound(soundTypePiston);
		setBlockTextureName(BlockInfo.TEXTURE_LOCATION + ":" + BlockInfo.LIMESTONE_TEXTURE);
	}
}