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

package dk.philiphansen.craftech.handler;

import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import dk.philiphansen.craftech.CrafTech;
import dk.philiphansen.craftech.gui.GuiIds;
import dk.philiphansen.craftech.inventory.*;
import dk.philiphansen.craftech.tileentities.TileentityBlastFurnace;
import dk.philiphansen.craftech.tileentities.TileentityCrusher;
import dk.philiphansen.craftech.tileentities.TileentityTechTable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler{
	
	public GuiHandler() {
		CrafTech.logger.info("Watch out!");
		NetworkRegistry.INSTANCE.registerGuiHandler(CrafTech.instance, this);
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID) {
			case 0:
				TileEntity tileentity = world.getTileEntity(x, y, z);
				
				if (tileentity != null && tileentity instanceof TileentityBlastFurnace) {
					return new ContainerBlastFurnace(player.inventory, (TileentityBlastFurnace)tileentity);
				}
				break;
				
			case GuiIds.CRUSHER:
				TileEntity crusherEntity = world.getTileEntity(x, y, z);
				
				if (crusherEntity != null && crusherEntity instanceof TileentityCrusher) {
					return new ContainerCrusher(player.inventory, (TileentityCrusher)crusherEntity);
				}
				break;
            case GuiIds.TECH_TABLE:
                TileEntity tileEntity = world.getTileEntity(x, y, z);

                if (tileEntity != null && tileEntity instanceof TileentityTechTable) {
                    return new ContainerTechTable(player.inventory, (TileentityTechTable)tileEntity);
                }
                break;
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID) {
		case 0:
			TileEntity tileentity = world.getTileEntity(x, y, z);
			
			if (tileentity != null && tileentity instanceof TileentityBlastFurnace) {
				return new GuiBlastFurnace(player.inventory, (TileentityBlastFurnace)tileentity);
			}
			break;
		case GuiIds.CRUSHER:
			TileEntity crusherEntity = world.getTileEntity(x, y, z);
			
			if (crusherEntity != null && crusherEntity instanceof TileentityCrusher) {
				return new GuiCrusher(player.inventory, (TileentityCrusher)crusherEntity);
			}
			break;
        case GuiIds.TECH_TABLE:
            TileEntity tileEntity = world.getTileEntity(x, y, z);

            if (tileEntity != null && tileEntity instanceof TileentityTechTable) {
                return new GuiTechTable(player.inventory, (TileentityTechTable)tileEntity);
            }
		}
	return null;
	}

}
