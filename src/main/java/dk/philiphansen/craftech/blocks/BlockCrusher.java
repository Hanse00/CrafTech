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
import dk.philiphansen.craftech.CrafTech;
import dk.philiphansen.craftech.core.GuiIds;
import dk.philiphansen.craftech.reference.BlockInfo;
import dk.philiphansen.craftech.tileentities.TileentityCrusher;
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

public class BlockCrusher extends BlockContainer {

	@SideOnly(Side.CLIENT)
	private IIcon verticalIcon;
	@SideOnly(Side.CLIENT)
	private IIcon sidesIcon;
	@SideOnly(Side.CLIENT)
	private IIcon frontIcon;
	
	
	public BlockCrusher() {
		super(Material.iron);
		setBlockName(BlockInfo.CRUSHER_NAME);
		setCreativeTab(CreativeTabs.tabDecorations);
		setHardness(3.5F);
		setStepSound(soundTypePiston);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register) {
		verticalIcon = register.registerIcon(BlockInfo.TEXTURE_LOCATION + ":" + BlockInfo.CRUSHER_TEXTURE_VERTICAL);
		sidesIcon = register.registerIcon(BlockInfo.TEXTURE_LOCATION + ":" + BlockInfo.CRUSHER_TEXTURE_SIDES);
		frontIcon = register.registerIcon(BlockInfo.TEXTURE_LOCATION + ":" + BlockInfo.CRUSHER_TEXTURE_FRONT);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		if (side == 0 || side == 1) {
			return verticalIcon;
		} else if (side == meta + 1) {
			return frontIcon;
		} else {
			return sidesIcon;
		}
	}
	
	@Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack item)
    {
        int l = MathHelper.floor_double((double)(player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        switch (l) {
		case 0:
			world.setBlockMetadataWithNotify(x, y, z, 1, 2);
			break;
		case 1:
			world.setBlockMetadataWithNotify(x, y, z, 4, 2);
			break;
		case 2:
			world.setBlockMetadataWithNotify(x, y, z, 2, 2);
			break;
		case 3:
			world.setBlockMetadataWithNotify(x, y, z, 3, 2);
			break;
        }

    }
	
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
        if (world.isRemote){
            return true;
        } else {
            TileentityCrusher crusher  = (TileentityCrusher)world.getTileEntity(x, y, z);

            if (crusher != null) {
            	player.openGui(CrafTech.instance, GuiIds.CRUSHER, world, x, y, z);
            }

            return true;
        }
    }
	
	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileentityCrusher();
	}

}
