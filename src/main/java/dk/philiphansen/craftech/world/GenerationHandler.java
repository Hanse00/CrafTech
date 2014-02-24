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

package dk.philiphansen.craftech.world;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.registry.GameRegistry;
import dk.philiphansen.craftech.blocks.ModBlocks;
import dk.philiphansen.craftech.reference.BlockInfo;
import dk.philiphansen.craftech.reference.GenerationInfo;

public class GenerationHandler implements IWorldGenerator{
	private WorldGenerator generator;
	
	public GenerationHandler() {
		GameRegistry.registerWorldGenerator(this, 0);
		generator = new WorldGenMinable(ModBlocks.blockLimestone, GenerationInfo.LIMESTONE_VEIN_SIZE);
	}
	
	private void generateStandardOre(Random rand, int chunkX, int chunkZ, World world, int iterations, WorldGenerator gen, int lowestY, int highestY) {
		for (int i = 0; i < iterations; i++) {
			int x = chunkX * 16 + rand.nextInt(16);
			int y = rand.nextInt(highestY - lowestY) + lowestY;
			int z = chunkZ * 16 + rand.nextInt(16);
			
			gen.generate(world, rand, x, y, z);
		}
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		generateStandardOre(random, chunkX, chunkZ, world, GenerationInfo.LIMESTONE_VEINS_PER_CHUNK, generator, GenerationInfo.LIMESTONE_LOWEST_SPAWN, GenerationInfo.LIMESTONE_HIGHEST_SPAWN);
	}
}
