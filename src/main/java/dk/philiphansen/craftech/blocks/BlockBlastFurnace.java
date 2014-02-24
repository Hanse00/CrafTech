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

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dk.philiphansen.craftech.CrafTech;
import dk.philiphansen.craftech.reference.BlockInfo;
import dk.philiphansen.craftech.tileentities.TileentityBlastFurnace;

public class BlockBlastFurnace extends BlockContainer{
	
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
    	else if (side == meta + 1) {
    		return frontIcon;
    	}
    	else {
    		return sidesIcon;
    	}
    }

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileentityBlastFurnace();
	}
	
	@Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack item)
    {
        int l = MathHelper.floor_double((double)(player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (l == 0)
        {
            world.setBlockMetadataWithNotify(x, y, z, 1, 2);
            //Set front to side 2
            CrafTech.logger.info("Setting metadata 1");
        }

        if (l == 1)
        {
            world.setBlockMetadataWithNotify(x, y, z, 4, 2);
          //Set front to side 5
            CrafTech.logger.info("Setting metadata 4");
        }

        if (l == 2)
        {
            world.setBlockMetadataWithNotify(x, y, z, 2, 2);
          //Set front to side 3
            CrafTech.logger.info("Setting metadata 2");
        }

        if (l == 3)
        {
            world.setBlockMetadataWithNotify(x, y, z, 3, 2);
          //Set front to side 4
            CrafTech.logger.info("Setting metadata 3");
        }

    }
	
	/*
    public IIcon getIcon(int side, int meta)
    {
        return side == 1 ? this.field_149935_N : (side == 0 ? this.field_149935_N : (side != meta ? this.blockIcon : this.field_149936_O));
    }
    */

}
