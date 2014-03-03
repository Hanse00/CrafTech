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


import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dk.philiphansen.craftech.CrafTech;
import dk.philiphansen.craftech.gui.GuiIds;
import dk.philiphansen.craftech.reference.BlockInfo;
import dk.philiphansen.craftech.tileentities.TileentityCrusher;

public class BlockCrusher extends BlockContainer {

	@SideOnly(Side.CLIENT)
	private IIcon verticalIcon;
	@SideOnly(Side.CLIENT)
	private IIcon sidesIcon;
	@SideOnly(Side.CLIENT)
	private IIcon frontIconOn;
	@SideOnly(Side.CLIENT)
	private IIcon frontIconOff;
	
	
	public BlockCrusher() {
		super(Material.iron);
		setBlockName(BlockInfo.CRUSHER_NAME);
		setCreativeTab(CrafTech.tabCrafTech);
		setHardness(3.5F);
		setStepSound(soundTypePiston);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register) {
		verticalIcon = register.registerIcon(BlockInfo.TEXTURE_LOCATION + ":" + BlockInfo.CRUSHER_TEXTURE_VERTICAL);
		sidesIcon = register.registerIcon(BlockInfo.TEXTURE_LOCATION + ":" + BlockInfo.CRUSHER_TEXTURE_SIDES);
		frontIconOn = register.registerIcon(BlockInfo.TEXTURE_LOCATION + ":" + BlockInfo.CRUSHER_TEXTURE_FRONT_ON);
		frontIconOff = register.registerIcon(BlockInfo.TEXTURE_LOCATION + ":" + BlockInfo.CRUSHER_TEXTURE_FRONT_OFF);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		if (side == 0 || side == 1) {
			return verticalIcon;
		} else if (side == MathHelper.floor_float(meta / 2) + 1 || (meta == 0 && side == 3)) {
			if (meta % 2 == 0) {
				return frontIconOff;
			} else {
				return frontIconOn;
			}
		} else {
			return sidesIcon;
		}
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileentityCrusher();
	}

	@Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack item)
    {
        int l = MathHelper.floor_double((double)(player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        switch (l) {
		case 0:
			world.setBlockMetadataWithNotify(x, y, z, 2, 2);
			break;
		case 1:
			world.setBlockMetadataWithNotify(x, y, z, 8, 2);
			break;
		case 2:
			world.setBlockMetadataWithNotify(x, y, z, 4, 2);
			break;
		case 3:
			world.setBlockMetadataWithNotify(x, y, z, 6, 2);
			break;
        }
    }

	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
		if (!world.isRemote) {
			FMLNetworkHandler.openGui(player, CrafTech.instance, GuiIds.CRUSHER, world, x, y, z);
		}
    	return true;
    }
	
	@Override
    public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
    	TileEntity tileentity = world.getTileEntity(x, y, z);
    	
    	if (tileentity != null && tileentity instanceof IInventory) {
    		IInventory inventory = (IInventory)tileentity;
    		
    		for (int i = 0; i < inventory.getSizeInventory(); i++) {
    			ItemStack stack = inventory.getStackInSlotOnClosing(i);
    			
    			if (stack != null) {
    				float spawnX = x + world.rand.nextFloat();
    				float spawnY = y + world.rand.nextFloat();
    				float spawnZ = z + world.rand.nextFloat();
    				
    				EntityItem droppedItem = new EntityItem(world, spawnX, spawnY, spawnZ, stack);
    				
    				float mult = 0.05F;
    				
    				droppedItem.motionX = (-0.5F + world.rand.nextFloat()) * mult;
    				droppedItem.motionY = (4 + world.rand.nextFloat()) * mult;
    				droppedItem.motionZ = (-0.5F + world.rand.nextFloat()) * mult;
    				
    				world.spawnEntityInWorld(droppedItem);
    			}
    		}
    	}
    	
    	super.breakBlock(world, x, y, z, block, meta);
    }
}
