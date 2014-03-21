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
import dk.philiphansen.craftech.creativetab.CreativeTabCrafTech;
import dk.philiphansen.craftech.handler.*;
import dk.philiphansen.craftech.item.ModItems;
import dk.philiphansen.craftech.reference.ModInfo;
import net.minecraft.creativetab.CreativeTabs;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Mod base class, this is where everything is called from.
 * Forge Mod Loader calls event handlers in this class when Minecraft is loading.
 * Those calls in these three methods are responsible for setting up the mod as the game loads.
 */
@Mod(modid = ModInfo.ID, name = ModInfo.NAME, version = ModInfo.VERSION)
public class CrafTech {

	public static final CreativeTabs tabCrafTech = new CreativeTabCrafTech();
	public static final Logger logger = LogManager.getLogger(ModInfo.NAME);

	@Instance(ModInfo.ID)
	public static CrafTech instance;

	/**
	 * Forge Mod loader pre-initialization.
	 * This is called before FML really "loads" the mod,
	 * useful for loading things the rest of the load sequence depends on.
	 * (eg. Load blocks and items before recipes are loaded)
	 *
	 * @param event FML pre-init event, can be used to get info about the file system.
	 */
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ConfigHandler.init(event.getSuggestedConfigurationFile());

		ModBlocks.init();
		ModItems.init();
	}

	/**
	 * Forge Mod loader initialization.
	 * This is where forge loads the mod, and all it's components.
	 *
	 * @param event Event passed by FML, this can be used to get the state of the mod.
	 */
	@EventHandler
	public void init(FMLInitializationEvent event) {
		ModBlocks.initTileEntities();

		ModBlocks.initCrafting();
		ModBlocks.initSmelting();
		ModBlocks.initTechTableRecipes();

		ModItems.initCrafting();
		ModItems.initSmelting();
		ModItems.initCrusher();

		ModItems.genRecipes();

		new FuelHandler();
		new GenerationHandler();
		new GuiHandler();
		new AchievementHandler();
	}

	/**
	 * Forge Mod Loader post initialization.
	 * This is called after FML has loaded all the mods,
	 * likely where you would put any inter-mod code.
	 *
	 * @param event FML event, can be used to build dependencies on other mods.
	 */
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
	    /* A debug message to check that the mod has loaded */
		logger.info("Welcome to the new age!");
	}
}