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

public class BlockCoke extends BlockCrafTech {

	BlockCoke() {
		super(Material.rock);
		setBlockName(BlockInfo.COKE_BLOCK_NAME);
		setHardness(2.0F);
		setResistance(10.0F);
		setStepSound(soundTypePiston);
		setBlockTextureName(ModInfo.ID + ":" + BlockInfo.COKE_BLOCK_TEXTURE);
	}
}
