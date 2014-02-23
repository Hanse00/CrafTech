package dk.philiphansen.craftech.blocks;

import dk.philiphansen.craftech.reference.BlockInfo;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockLimestoneBrick extends Block{

	protected BlockLimestoneBrick() {
		super(Material.rock);
		setBlockName(BlockInfo.LIMESTONE_BRICK_NAME);
		setCreativeTab(CreativeTabs.tabBlock);
		setHardness(2.0F);
		setResistance(10.0F);
		setStepSound(soundTypePiston);
		setBlockTextureName(BlockInfo.TEXTURE_LOCATION + ":" + BlockInfo.LIMESTONE_BRICK_TEXTURE);
	}

}
