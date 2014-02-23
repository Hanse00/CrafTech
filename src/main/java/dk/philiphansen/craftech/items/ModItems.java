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

package dk.philiphansen.craftech.items;

import cpw.mods.fml.common.registry.GameRegistry;
import dk.philiphansen.craftech.reference.ItemInfo;
import net.minecraft.item.Item;
import net.minecraft.item.ItemCoal;
import net.minecraft.item.ItemStack;

public class ModItems {
	public static Item coalCoke;
	
	public static void init() {
		coalCoke = new ItemCoalCoke().setUnlocalizedName("coalCoke");
		GameRegistry.registerItem(coalCoke, ItemInfo.COALCOKE_NAME);
	}
	
	public static void initSmelting() {
		//ToDo: Create smelting system
	}
}
