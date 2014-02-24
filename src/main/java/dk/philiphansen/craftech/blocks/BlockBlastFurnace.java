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

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import dk.philiphansen.craftech.reference.BlockInfo;

public class BlockBlastFurnace extends Block{
	
	@SideOnly(Side.CLIENT)
	private IIcon verticalIcon;
	@SideOnly(Side.CLIENT)
	private IIcon sidesIcon;
	@SideOnly(Side.CLIENT)
	private IIcon frontIcon;

	protected BlockBlastFurnace() {
		super(Material.iron);
		setBlockName(BlockInfo.BLAST_FURNACE_NAME);
		setCreativeTab(CreativeTabs.tabDecorations);
		setHardness(3.5F);
		setStepSound(soundTypePiston);
		setBlockTextureName(BlockInfo.TEXTURE_LOCATION + ":" + BlockInfo.BLAST_FURNACE_TEXTURE_SIDES);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register) {
        verticalIcon = register.registerIcon(BlockInfo.TEXTURE_LOCATION + ":" + BlockInfo.BLAST_FURNACE_TEXTURE_VERTICAL);
        frontIcon = register.registerIcon(BlockInfo.TEXTURE_LOCATION + ":" + BlockInfo.BLAST_FURNACE_TEXTURE_FRONT);
        sidesIcon = register.registerIcon(BlockInfo.TEXTURE_LOCATION + ":" + BlockInfo.BLAST_FURNACE_TEXTURE_SIDES);
    }
	
	@Override
    @SideOnly(Side.CLIENT)
	//TODO: Make the face facing the player the front icon rather than a certain direction
    public IIcon getIcon(int side, int meta) {
    	if (side == 0 || side == 1) {
    		return verticalIcon;
    	}
    	else if (side == 2) {
    		return frontIcon;
    	}
    	else {
    		return sidesIcon;
    	}
    }

}
