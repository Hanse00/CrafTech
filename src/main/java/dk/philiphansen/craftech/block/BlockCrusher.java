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
import dk.philiphansen.craftech.tileentity.TileEntityCrusher;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import static dk.philiphansen.craftech.util.WorldUtils.isServer;

public class BlockCrusher extends BlockContainer {
	@SideOnly(Side.CLIENT)
	private IIcon verticalIcon;
	@SideOnly(Side.CLIENT)
	private IIcon sideIcon;
	@SideOnly(Side.CLIENT)
	private IIcon frontOffIcon;
	@SideOnly(Side.CLIENT)
	private IIcon frontOnIcon;

	protected BlockCrusher() {
		super(Material.rock);
		setCreativeTab(CreativeTabs.tabRedstone);

		setHardness(3.5F);
		setStepSound(soundTypePiston);

		setBlockName(BlockInfo.CRUSHER_NAME);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register) {
		verticalIcon = register.registerIcon(ModInfo.ID + ":" + BlockInfo.CRUSHER_TEXTURE_VERTICAL);
		sideIcon = register.registerIcon(ModInfo.ID + ":" + BlockInfo.CRUSHER_TEXTURE_SIDE);
		frontOffIcon = register.registerIcon(ModInfo.ID + ":" + BlockInfo.CRUSHER_TEXTURE_FRONT_OFF);
		frontOnIcon = register.registerIcon(ModInfo.ID + ":" + BlockInfo.CRUSHER_TEXTURE_FRONT_ON);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		if (bottomOrTop(side)) {
			return verticalIcon;
		} else if (front(side, meta) || itemFront(side, meta)) {
			if (isOff(meta)) {
				return frontOffIcon;
			} else {
				return frontOnIcon;
			}
		} else {
			return sideIcon;
		}
	}

	private boolean bottomOrTop(int side) {
		return side <= 1;
	}

	private boolean itemFront(int side, int meta) {
		return (meta == 0) && (side == 3);
	}

	private boolean front(int side, int meta) {
		return side == ((meta / 2) + 1);
	}

	private boolean isOff(int meta) {
		return (meta % 2) == 0;
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack itemStack) {
		int rotation = getEntityDirection(player);

		/*
			This is needed because Forge sides go 0, 3, 1, 2 compared to Minecraft's 0, 1, 2, 3
		 */
		switch (rotation) {
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

	private int getEntityDirection(EntityLivingBase player) {
		return (int) Math.floor((player.rotationYaw / 90) + 0.5) & 3;
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileEntityCrusher();
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX,
	                                float hitY, float hitZ) {
		if (isServer(world)) {
			FMLNetworkHandler.openGui(player, CrafTech.instance, GuiIds.CRUSHER, world, x, y, z);
		}
		return true;
	}
}
