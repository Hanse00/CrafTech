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

package dk.philiphansen.craftech;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import dk.philiphansen.craftech.block.ModBlocks;
import dk.philiphansen.craftech.reference.ModInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = ModInfo.ID, name = ModInfo.NAME, version = ModInfo.VERSION)
public class CrafTech {
	public static final Logger logger = LogManager.getLogger(ModInfo.NAME);
	@Instance(ModInfo.ID)
	public static CrafTech instance;

	@EventHandler
	@SuppressWarnings("unused")
	public void preInit(FMLPreInitializationEvent event) {
		ModBlocks.init();
	}

	@EventHandler
	@SuppressWarnings("unused")
	public void init(FMLInitializationEvent event) {
	}

	@EventHandler
	@SuppressWarnings("unused")
	public void postInit(FMLPostInitializationEvent event) {
		logger.info("Welcome to the new age!");
	}
}