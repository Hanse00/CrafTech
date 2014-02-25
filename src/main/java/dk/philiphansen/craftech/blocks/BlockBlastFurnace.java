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
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
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
    public IIcon getIcon(int side, int meta) {		
    	if (side == 0 || side == 1) {
    		return verticalIcon;
    	}
    	else if (side == meta + 1 || (meta == 0 && side == 3)) {
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
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack item) {
        int l = MathHelper.floor_double((double)(player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (l == 0)
        {
            world.setBlockMetadataWithNotify(x, y, z, 1, 2);
        }

        if (l == 1)
        {
            world.setBlockMetadataWithNotify(x, y, z, 4, 2);
        }

        if (l == 2)
        {
            world.setBlockMetadataWithNotify(x, y, z, 2, 2);
        }

        if (l == 3)
        {
            world.setBlockMetadataWithNotify(x, y, z, 3, 2);
        }

    }
	
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			FMLNetworkHandler.openGui(player, CrafTech.instance, 0, world, x, y, z);
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
