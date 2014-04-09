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

package dk.philiphansen.craftech.handler;

import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import dk.philiphansen.craftech.CrafTech;
import dk.philiphansen.craftech.client.gui.inventory.GuiCrusher;
import dk.philiphansen.craftech.inventory.ContainerCrusher;
import dk.philiphansen.craftech.reference.GuiIds;
import dk.philiphansen.craftech.tileentity.TileEntityCrusher;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler {
	public GuiHandler() {
		NetworkRegistry.INSTANCE.registerGuiHandler(CrafTech.instance, this);
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tileEntity = world.getTileEntity(x, y, z);

		switch (ID) {
			case GuiIds.CRUSHER:
				if (tileEntity != null && tileEntity instanceof TileEntityCrusher) {
					return new ContainerCrusher(player.inventory, (TileEntityCrusher) tileEntity);
				}
			default:
				return null;
		}
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tileEntity = world.getTileEntity(x, y, z);

		switch (ID) {
			case GuiIds.CRUSHER:
				if (tileEntity != null && tileEntity instanceof TileEntityCrusher) {
					return new GuiCrusher(player.inventory, (TileEntityCrusher) tileEntity);
				}
			default:
				return null;
		}
	}
}
