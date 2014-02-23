package dk.philiphansen.craftech.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import dk.philiphansen.craftech.reference.BlockInfo;

public class BlockCobbleLimestone extends Block{

	protected BlockCobbleLimestone() {
		super(Material.rock);
		setBlockName(BlockInfo.COBBLE_LIMESTONE_NAME);
		setCreativeTab(CreativeTabs.tabBlock);
		setHardness(2.0F);
		setResistance(10.0F);
		setStepSound(soundTypePiston);
		setBlockTextureName(BlockInfo.TEXTURE_LOCATION + ":" + BlockInfo.COBBLE_LIMESTONE_TEXTURE);
	}

}
