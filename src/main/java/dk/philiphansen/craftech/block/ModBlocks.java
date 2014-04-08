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

import cpw.mods.fml.common.registry.GameRegistry;
import dk.philiphansen.craftech.reference.BlockInfo;

public class ModBlocks {
	public static BlockLimestone blockLimestone;

	public static void init() {
		constructBlocks();
		registerBlocks();
	}

	public static void constructBlocks() {
		blockLimestone = new BlockLimestone();
	}

	public static void registerBlocks() {
		GameRegistry.registerBlock(blockLimestone, BlockInfo.LIMESTONE_NAME);
	}
}
