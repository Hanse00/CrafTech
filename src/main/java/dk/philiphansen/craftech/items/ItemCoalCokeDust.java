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

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import dk.philiphansen.craftech.reference.ItemInfo;

public class ItemCoalCokeDust extends Item {
	public ItemCoalCokeDust() {
		super();
		setUnlocalizedName(ItemInfo.COALCOKE_DUST_NAME);
		this.setCreativeTab(CreativeTabs.tabMaterials);
		setTextureName(ItemInfo.TEXTURE_LOCATION + ":" + ItemInfo.COALCOKE_DUST_TEXTURE);
	}
}
