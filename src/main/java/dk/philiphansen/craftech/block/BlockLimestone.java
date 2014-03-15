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

import dk.philiphansen.craftech.reference.BlockInfo;
import dk.philiphansen.craftech.reference.ModInfo;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

import java.util.Random;

public class BlockLimestone extends BlockCrafTech {

	BlockLimestone() {
		super(Material.rock);
		setBlockName(BlockInfo.LIMESTONE_NAME);
		setHardness(1.5F);
		setResistance(10.0F);
		setStepSound(soundTypePiston);
		setBlockTextureName(ModInfo.ID + ":" + BlockInfo.LIMESTONE_TEXTURE);
	}

	/**
	 * Called when the block is broken and minecraft wants to spawn the correlating item.
	 * This method is only required if you want to drop something else than the item version
	 * of the block broken.
	 *
	 * @param p_149650_1_
	 * @param p_149650_2_
	 * @param p_149650_3_
	 * @return The item to drop
	 */
	@Override
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
	    /* We want to drop the "cobble" version of limestone */
		return Item.getItemFromBlock(ModBlocks.blockCobbleLimestone);
	}
}