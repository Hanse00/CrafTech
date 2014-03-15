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

package dk.philiphansen.craftech.handler;

import dk.philiphansen.craftech.reference.ConfigInfo;
import dk.philiphansen.craftech.reference.GenerationInfo;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

/**
 * Handles reading and writing the mod configuration file.
 */
public class ConfigHandler {

	/**
	 * Takes the file to use as a configuration file.
	 * Reads properties set in the file (uses the default values if none are found.
	 * Writes the file once done, inserting fields that were not found.
	 *
	 * @param file The file to use as a config.
	 */
	public static void init(File file) {
		Configuration config = new Configuration(file);

		config.load();

		GenerationInfo.LIMESTONE_VEIN_SIZE = config.get(ConfigInfo.WORLDGEN_CATEGORY, ConfigInfo.LIMESTONE +
				": " + ConfigInfo.VEIN_SIZE, GenerationInfo.LIMESTONE_VEIN_SIZE_DEFAULT).getInt();

		GenerationInfo.LIMESTONE_VEINS_PER_CHUNK = config.get(ConfigInfo.WORLDGEN_CATEGORY, ConfigInfo.LIMESTONE +
				": " + ConfigInfo.VEIN_COUNT, GenerationInfo.LIMESTONE_VEINS_PER_CHUNK_DEFAULT).getInt();

		GenerationInfo.LIMESTONE_HIGHEST_SPAWN = config.get(ConfigInfo.WORLDGEN_CATEGORY, ConfigInfo.LIMESTONE +
				": " + ConfigInfo.HIGHEST_SPAWN, GenerationInfo.LIMESTONE_HIGHEST_SPAWN_DEFAULT).getInt();

		GenerationInfo.LIMESTONE_LOWEST_SPAWN = config.get(ConfigInfo.WORLDGEN_CATEGORY, ConfigInfo.LIMESTONE +
				": " + ConfigInfo.LOWEST_SPAWN, GenerationInfo.LIMESTONE_LOWEST_SPAWN_DEFAULT).getInt();

		config.save();
	}

}
