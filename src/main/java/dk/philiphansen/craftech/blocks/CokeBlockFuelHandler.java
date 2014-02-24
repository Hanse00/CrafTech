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

import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.IFuelHandler;

public class CokeBlockFuelHandler implements IFuelHandler {

	@Override
	public int getBurnTime(ItemStack fuel) {
		
		//This can be improved but I don't know how... It works for now
		if (fuel.getUnlocalizedName().equals(ModBlocks.blockCoalCoke.getUnlocalizedName().toString())) {
			return 12000; //10 Mins
		}
		return 0;
	}

}
