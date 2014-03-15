/*
 * Copyright (C) 2014 Philip Hansen and CrafTech contributors.
 *
 * This file is part of CrafTech.
 *
 * CrafTech is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * CrafTech is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with CrafTech.  If not, see <http://www.gnu.org/licenses/>.
 */

package dk.philiphansen.craftech.block;

import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dk.philiphansen.craftech.CrafTech;
import dk.philiphansen.craftech.reference.BlockInfo;
import dk.philiphansen.craftech.reference.GuiInfo;
import dk.philiphansen.craftech.reference.ModInfo;
import dk.philiphansen.craftech.tileentity.TileEntityTechTable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

/**
 * Defines the tech table block.
 */
public class BlockTechTable extends BlockContainer {

	@SideOnly(Side.CLIENT)
	private IIcon topIcon;
	@SideOnly(Side.CLIENT)
	private IIcon bottomIcon;
	@SideOnly(Side.CLIENT)
	private IIcon sideIcon;

	/**
	 * Make the blast furnace block.
	 * Sets basic block values.
	 */
	BlockTechTable() {
		super(Material.wood);
		setBlockName(BlockInfo.TECH_TABLE_NAME);
		setCreativeTab(CrafTech.tabCrafTech);
		setHardness(2.5F);
		setStepSound(soundTypeWood);
	}

	/**
	 * Registers the icons for the block.
	 *
	 * @param register
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register) {
		topIcon = register.registerIcon(ModInfo.ID + ":" + BlockInfo.TECH_TABLE_TEXTURE_TOP);
		bottomIcon = register.registerIcon(ModInfo.ID + ":" + BlockInfo.TECH_TABLE_TEXTURE_BOTTOM);
		sideIcon = register.registerIcon(ModInfo.ID + ":" + BlockInfo.TECH_TABLE_TEXTURE_SIDE);
	}

	/**
	 * Called by the client to find the icon texture for the block.
	 * Passes along side of block and block metadata, gets back the appropriate icon to display.
	 *
	 * @param side The side of the block.
	 * @param meta The current block metadata value.
	 * @return icon Gets the icon to display.
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		if (side == 0) {
			return bottomIcon;
		} else if (side == 1) {
			return topIcon;
		} else {
			return sideIcon;
		}
	}

	/**
	 * Called by the block when it's placed, creates the tile entity that goes with the block.
	 *
	 * @param var1
	 * @param var2
	 * @return TileEntityBlastFurnace
	 */
	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileEntityTechTable();
	}

	/**
	 * Called when the block is right clicked by a player.
	 *
	 * @param world  World of the block.
	 * @param x      x coordinate of the block.
	 * @param y      y coordinate of the block.
	 * @param z      z coordinate of the block.
	 * @param player player right clicking.
	 * @param side   side right clicked.
	 * @param hitX   x coordinate on side of the click.
	 * @param hitY   y coordinate on side of the click.
	 * @param hitZ
	 * @return
	 */
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX,
	                                float hitY, float hitZ) {
	    /* On the server, call network handler to open GUI */
		if (!world.isRemote) {
			FMLNetworkHandler.openGui(player, CrafTech.instance, GuiInfo.TECH_TABLE, world, x, y, z);
		}
		return true;
	}

	/**
	 * Called when a player breaks the block.
	 * Takes care of everything that needs to be done as the block is removed.
	 * (Eg. dropping items)
	 *
	 * @param world The world in which the block is located.
	 * @param x     The x coordinate of the block.
	 * @param y     The y coordinate of the block.
	 * @param z     The z coordinate of the block.
	 * @param block The block being broken.
	 * @param meta  The metadata of the block.
	 */
	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
		TileEntity tileentity = world.getTileEntity(x, y, z);

		if (tileentity != null && tileentity instanceof IInventory) {
			IInventory inventory = (IInventory) tileentity;

            /* For each stack in the inventory */
			for (int i = 0; i < inventory.getSizeInventory(); i++) {
				ItemStack stack = inventory.getStackInSlotOnClosing(i);

				if (stack != null) {
					float spawnX = x + world.rand.nextFloat();
					float spawnY = y + world.rand.nextFloat();
					float spawnZ = z + world.rand.nextFloat();

					EntityItem droppedItem = new EntityItem(world, spawnX, spawnY, spawnZ, stack);

					float mult = 0.05F;

                    /* Calculate random(ish) direction */
					droppedItem.motionX = (-0.5F + world.rand.nextFloat()) * mult;
					droppedItem.motionY = (4 + world.rand.nextFloat()) * mult;
					droppedItem.motionZ = (-0.5F + world.rand.nextFloat()) * mult;

                    /* Drop stack */
					world.spawnEntityInWorld(droppedItem);
				}
			}
		}
        /* Make sure the block actually gets removed */
		super.breakBlock(world, x, y, z, block, meta);
	}
}
