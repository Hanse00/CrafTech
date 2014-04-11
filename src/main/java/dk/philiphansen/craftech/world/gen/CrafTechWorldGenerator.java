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

package dk.philiphansen.craftech.world.gen;

import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.registry.GameRegistry;
import dk.philiphansen.craftech.block.ModBlocks;
import dk.philiphansen.craftech.reference.GenerationInfo;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class CrafTechWorldGenerator implements IWorldGenerator {
	private WorldGenerator limestoneGenerator;

	public CrafTechWorldGenerator() {
		registerGenerator();
		initBlockGenerators();
	}

	public void registerGenerator() {
		GameRegistry.registerWorldGenerator(this, 1);
	}

	public void initBlockGenerators() {
		limestoneGenerator = new WorldGenMinable(ModBlocks.limestone, GenerationInfo.LIMESTONE_VEIN_SIZE);
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator,
	                     IChunkProvider chunkProvider) {
		generateIteration(random, chunkX, chunkZ, world, GenerationInfo.LIMESTONE_VEIN_COUNT, limestoneGenerator,
				GenerationInfo.LIMESTONE_MIN_HEIGHT, GenerationInfo.LIMESTONE_MAX_HEIGHT);
	}

	private void generateIteration(Random random, int chunkX, int chunkZ, World world, int iterations,
	                               WorldGenerator generator, int lowestY, int highestY) {
		for (int i = 0; i < iterations; i++) {
			int x = chunkX * 16 + random.nextInt(16);
			int y = random.nextInt(highestY - lowestY) + lowestY;
			int z = chunkZ * 16 + random.nextInt(16);

			generator.generate(world, random, x, y, z);
		}
	}
}
