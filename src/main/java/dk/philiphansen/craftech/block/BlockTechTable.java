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
import dk.philiphansen.craftech.reference.GuiIds;
import dk.philiphansen.craftech.reference.ModInfo;
import dk.philiphansen.craftech.tileentity.TileEntityTechTable;
import dk.philiphansen.craftech.util.WorldUtils;
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

public class BlockTechTable extends BlockContainer {
	private static final int bottomSide = 0;
	private static final int topSide = 1;
	@SideOnly(Side.CLIENT)
	private IIcon topIcon;
	@SideOnly(Side.CLIENT)
	private IIcon bottomIcon;
	@SideOnly(Side.CLIENT)
	private IIcon sideIcon;

	protected BlockTechTable() {
		super(Material.wood);
		setCreativeTab(CrafTech.tabCrafTech);

		setHardness(2.5F);
		setStepSound(soundTypeWood);

		setBlockName(BlockInfo.TECH_TABLE_NAME);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register) {
		topIcon = register.registerIcon(ModInfo.ID + ":" + BlockInfo.TECH_TABLE_TEXTURE_TOP);
		bottomIcon = register.registerIcon(ModInfo.ID + ":" + BlockInfo.TECH_TABLE_TEXTURE_BOTTOM);
		sideIcon = register.registerIcon(ModInfo.ID + ":" + BlockInfo.TECH_TABLE_TEXTURE_SIDE);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		if (side == bottomSide) {
			return bottomIcon;
		} else if (side == topSide) {
			return topIcon;
		} else {
			return sideIcon;
		}
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileEntityTechTable();
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX,
	                                float hitY, float hitZ) {
		if (WorldUtils.isServer(world)) {
			FMLNetworkHandler.openGui(player, CrafTech.instance, GuiIds.TECH_TABLE, world, x, y, z);
		}
		return true;
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
		TileEntity tileentity = world.getTileEntity(x, y, z);

		if (hasInventory(tileentity)) {
			IInventory inventory = (IInventory) tileentity;
			dropInventory(inventory, world, x, y, z);
		}

		super.breakBlock(world, x, y, z, block, meta);
	}

	private boolean hasInventory(TileEntity tileEntity) {
		return tileEntity != null && tileEntity instanceof IInventory;
	}

	private void dropInventory(IInventory inventory, World world, int x, int y, int z) {
		for (int i = 0; i < inventory.getSizeInventory(); i++) {
			ItemStack stack = inventory.getStackInSlotOnClosing(i);

			if (stack != null) {
				dropStack(stack, world, x, y, z);
			}
		}
	}

	private void dropStack(ItemStack stack, World world, int x, int y, int z) {
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
