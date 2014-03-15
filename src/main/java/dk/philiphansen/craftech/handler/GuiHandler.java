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
import dk.philiphansen.craftech.inventory.*;
import dk.philiphansen.craftech.reference.GuiInfo;
import dk.philiphansen.craftech.tileentity.TileEntityBlastFurnace;
import dk.philiphansen.craftech.tileentity.TileEntityCrusher;
import dk.philiphansen.craftech.tileentity.TileEntityTechTable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Handles opening GUIs on the Client and Server side.
 */
public class GuiHandler implements IGuiHandler {

	/**
	 * Register this handler with forge.
	 * This lets forge know that any GUI being opened by this mod, should be handled by this class.
	 */
	public GuiHandler() {
		NetworkRegistry.INSTANCE.registerGuiHandler(CrafTech.instance, this);
	}

	/**
	 * Handle GUI opening on the server side of things.
	 *
	 * @param ID     Numerical ID of the GUI opened.
	 * @param player The player who opened the GUI.
	 * @param world  The world in which the GUI was opened.
	 * @param x      The x coordinate of the block which opened the GUI.
	 * @param y      The y coordinate of the block which opened the GUI.
	 * @param z      The y coordinate of the block which opened the GUI.
	 * @return Container The container needed by the server to handle the GUI actions on the server side.
	 */
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID) {
	    /* Check the ID sent by the block against the registry of IDs */
			case GuiInfo.BLAST_FURNACE:
            /*
             * Before opening the container, make sure there's a TileEntity to open against.
             * And that it's the correct kind of tile entity (Just to make sure).
             * Without the TileEntity a container is not much use (What inventory are you going to save stuff in?
             */
				TileEntity tileentity = world.getTileEntity(x, y, z);

				if (tileentity != null && tileentity instanceof TileEntityBlastFurnace) {
					return new ContainerBlastFurnace(player.inventory, (TileEntityBlastFurnace) tileentity);
				}
				break;

			case GuiInfo.CRUSHER:
				TileEntity crusherEntity = world.getTileEntity(x, y, z);

				if (crusherEntity != null && crusherEntity instanceof TileEntityCrusher) {
					return new ContainerCrusher(player.inventory, (TileEntityCrusher) crusherEntity);
				}
				break;
			case GuiInfo.TECH_TABLE:
				TileEntity tileEntity = world.getTileEntity(x, y, z);

				if (tileEntity != null && tileEntity instanceof TileEntityTechTable) {
					return new ContainerTechTable(player.inventory, (TileEntityTechTable) tileEntity);
				}
				break;
		}
		return null;
	}

	/**
	 * Handle GUI opening on the client side of things.
	 *
	 * @param ID     Numerical ID of the GUI opened.
	 * @param player The player who opened the GUI.
	 * @param world  The world in which the GUI was opened.
	 * @param x      The x coordinate of the block which opened the GUI.
	 * @param y      The y coordinate of the block which opened the GUI.
	 * @param z      The y coordinate of the block which opened the GUI.
	 * @return Gui The GUI to display on the client.
	 */
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID) {
			case GuiInfo.BLAST_FURNACE:
				TileEntity tileentity = world.getTileEntity(x, y, z);

				if (tileentity != null && tileentity instanceof TileEntityBlastFurnace) {
					return new GuiBlastFurnace(player.inventory, (TileEntityBlastFurnace) tileentity);
				}
				break;
			case GuiInfo.CRUSHER:
				TileEntity crusherEntity = world.getTileEntity(x, y, z);

				if (crusherEntity != null && crusherEntity instanceof TileEntityCrusher) {
					return new GuiCrusher(player.inventory, (TileEntityCrusher) crusherEntity);
				}
				break;
			case GuiInfo.TECH_TABLE:
				TileEntity tileEntity = world.getTileEntity(x, y, z);

				if (tileEntity != null && tileEntity instanceof TileEntityTechTable) {
					return new GuiTechTable(player.inventory, (TileEntityTechTable) tileEntity);
				}
		}
		return null;
	}
}
