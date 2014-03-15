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

import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.registry.GameRegistry;
import dk.philiphansen.craftech.block.ModBlocks;
import dk.philiphansen.craftech.reference.GenerationInfo;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

/**
 * Handles world generation specific to the mod.
 */
public class GenerationHandler implements IWorldGenerator {

	private final WorldGenerator generator;

	/**
	 * Registers the generation handler, sets the type of generation for each generator.
	 */
	public GenerationHandler() {
		GameRegistry.registerWorldGenerator(this, 0);
		generator = new WorldGenMinable(ModBlocks.blockLimestone, GenerationInfo.LIMESTONE_VEIN_SIZE);
	}

	/**
	 * Called every time a chunk needs generation.
	 * This is where we can insert our own generation code to generate in the chunk.
	 *
	 * @param random         The random generator used by minecraft, this is important because it carries the world
	 *                          seed.
	 *                       If you use any other generator, the "seed" option on the world will not affect how your
	 *                       mod
	 *                       generates it's blocks.
	 * @param chunkX         X coordinate of the chunk to generate (this is not world block coordinates!)
	 * @param chunkZ         Z coordinate of the chunk to generate (this is not world block coordinates!)
	 * @param world          The world to generate in.
	 * @param chunkGenerator
	 * @param chunkProvider
	 */
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator,
	                     IChunkProvider chunkProvider) {
		generateStandardOre(random, chunkX, chunkZ, world, GenerationInfo.LIMESTONE_VEINS_PER_CHUNK, generator,
				GenerationInfo.LIMESTONE_LOWEST_SPAWN, GenerationInfo.LIMESTONE_HIGHEST_SPAWN);
	}

	/**
	 * Calls the generator provided, x number of times at random positions in the chunk given
	 * within y max and min.
	 *
	 * @param rand       The random generator used by minecraft, this is important because it carries the world seed.
	 *                   If you use any other generator, the "seed" option on the world will not affect how your mod
	 *                   generates it's blocks.
	 * @param chunkX     X coordinate of the chunk to generate (this is not world block coordinates!)
	 * @param chunkZ     Z coordinate of the chunk to generate (this is not world block coordinates!)
	 * @param world      The world to generate in.
	 * @param iterations The number of times to generate in this chunk.
	 * @param gen        The generator to generate with.
	 * @param lowestY    The lowest Y value to generate at.
	 * @param highestY   The highest Y value to generate at.
	 */
	private void generateStandardOre(Random rand, int chunkX, int chunkZ, World world, int iterations,
	                                 WorldGenerator gen, int lowestY, int highestY) {
		for (int i = 0; i < iterations; i++) {
			int x = chunkX * 16 + rand.nextInt(16);
			int y = rand.nextInt(highestY - lowestY) + lowestY;
			int z = chunkZ * 16 + rand.nextInt(16);

			gen.generate(world, rand, x, y, z);
		}
	}
}
