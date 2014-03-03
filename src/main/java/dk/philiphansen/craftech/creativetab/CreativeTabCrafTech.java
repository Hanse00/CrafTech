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

package dk.philiphansen.craftech.creativetab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dk.philiphansen.craftech.blocks.ModBlocks;
import dk.philiphansen.craftech.reference.ModInfo;

public class CreativeTabCrafTech extends CreativeTabs {

	public CreativeTabCrafTech() {
		super(ModInfo.MODID);	
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getTabIconItem() {
		return Item.getItemFromBlock(ModBlocks.blockBlastFurnace);
	}

}
