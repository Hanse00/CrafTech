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

import dk.philiphansen.craftech.reference.BlockInfo;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockLimestoneBrick extends Block{

	protected BlockLimestoneBrick() {
		super(Material.rock);
		setBlockName(BlockInfo.LIMESTONE_BRICK_NAME);
		setCreativeTab(CreativeTabs.tabBlock);
		setHardness(2.0F);
		setResistance(10.0F);
		setStepSound(soundTypePiston);
		setBlockTextureName(BlockInfo.TEXTURE_LOCATION + ":" + BlockInfo.LIMESTONE_BRICK_TEXTURE);
	}

}
