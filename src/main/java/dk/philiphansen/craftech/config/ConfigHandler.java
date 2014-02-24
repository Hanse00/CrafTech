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

package dk.philiphansen.craftech.config;

import java.io.File;

import dk.philiphansen.craftech.reference.ConfigInfo;
import dk.philiphansen.craftech.reference.GenerationInfo;
import net.minecraftforge.common.config.Configuration;

public class ConfigHandler {

	public static void init(File file) {
		Configuration config = new Configuration(file);
		
		config.load();
		
		GenerationInfo.LIMESTONE_VEIN_SIZE = config.get(ConfigInfo.WORLDGEN_CATEGORY, ConfigInfo.LIMESTONE + ": " + ConfigInfo.VEIN_SIZE, GenerationInfo.LIMESTONE_VEIN_SIZE_DEFAULT).getInt();
		GenerationInfo.LIMESTONE_VEINS_PER_CHUNK = config.get(ConfigInfo.WORLDGEN_CATEGORY, ConfigInfo.LIMESTONE + ": " + ConfigInfo.VEIN_COUNT, GenerationInfo.LIMESTONE_VEINS_PER_CHUNK_DEFAULT).getInt();
		GenerationInfo.LIMESTONE_HIGHEST_SPAWN = config.get(ConfigInfo.WORLDGEN_CATEGORY, ConfigInfo.LIMESTONE + ": " + ConfigInfo.HIGHEST_SPAWN, GenerationInfo.LIMESTONE_HIGHEST_SPAWN_DEFAULT).getInt();
		GenerationInfo.LIMESTONE_LOWEST_SPAWN = config.get(ConfigInfo.WORLDGEN_CATEGORY, ConfigInfo.LIMESTONE + ": " + ConfigInfo.LOWEST_SPAWN, GenerationInfo.LIMESTONE_LOWEST_SPAWN_DEFAULT).getInt();
		
		config.save();
	}
	
}
