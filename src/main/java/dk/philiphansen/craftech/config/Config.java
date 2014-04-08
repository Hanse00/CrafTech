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

package dk.philiphansen.craftech.config;

import dk.philiphansen.craftech.reference.ConfigInfo;
import dk.philiphansen.craftech.reference.GenerationInfo;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class Config {
	public static void init(File file) {
		Configuration configuration = new Configuration(file);

		configuration.load();

		GenerationInfo.LIMESTONE_VEIN_SIZE = configuration.get(ConfigInfo.GENERATION_CATEGORY,
				ConfigInfo.LIMESTONE + ": " + ConfigInfo.VEIN_SIZE, GenerationInfo.DEFAULT_LIMESTONE_VEIN_SIZE)
				.getInt();
		GenerationInfo.LIMESTONE_VEIN_COUNT = configuration.get(ConfigInfo.GENERATION_CATEGORY,
				ConfigInfo.LIMESTONE + ": " + ConfigInfo.VEIN_COUNT, GenerationInfo.DEFAULT_LIMESTONE_VEIN_COUNT)
				.getInt();
		GenerationInfo.LIMESTONE_MAX_HEIGHT = configuration.get(ConfigInfo.GENERATION_CATEGORY,
				ConfigInfo.LIMESTONE + ": " + ConfigInfo.VEIN_MAX_HEIGHT, GenerationInfo.DEFAULT_LIMESTONE_MAX_HEIGHT)
				.getInt();
		GenerationInfo.LIMESTONE_MIN_HEIGHT = configuration.get(ConfigInfo.GENERATION_CATEGORY,
				ConfigInfo.LIMESTONE + ": " + ConfigInfo.VEIN_MIN_HEIGHT, GenerationInfo.DEFAULT_LIMESTONE_MIN_HEIGHT)
				.getInt();

		configuration.save();
	}
}
