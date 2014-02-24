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

package dk.philiphansen.craftech.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import dk.philiphansen.craftech.tileentities.TileentityBlastFurnace;

public class ContainerBlastFurnace extends Container {
	
	private TileentityBlastFurnace blastFurnace;
	
	public ContainerBlastFurnace(InventoryPlayer player, TileentityBlastFurnace blastFurnace) {
		this.blastFurnace = blastFurnace;
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return blastFurnace.isUseableByPlayer(player);
	}

}
