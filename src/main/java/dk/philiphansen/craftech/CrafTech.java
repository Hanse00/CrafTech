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

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import dk.philiphansen.craftech.block.ModBlocks;
import dk.philiphansen.craftech.creativetab.CreativeTabCrafTech;
import dk.philiphansen.craftech.event.CraftEvent;
import dk.philiphansen.craftech.handler.*;
import dk.philiphansen.craftech.item.ModItems;
import dk.philiphansen.craftech.reference.ModInfo;
import net.minecraft.creativetab.CreativeTabs;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = ModInfo.ID, name = ModInfo.NAME, version = ModInfo.VERSION)
public class CrafTech {

    @Instance(ModInfo.ID)
    public static CrafTech instance;
    public static final CreativeTabs tabCrafTech = new CreativeTabCrafTech();
	public static final Logger logger = LogManager.getLogger(ModInfo.NAME);

	@EventHandler
	@SuppressWarnings("unused")
	public void preInit(FMLPreInitializationEvent event) {
		ConfigHandler.init(event.getSuggestedConfigurationFile());

		ModBlocks.init();
		ModItems.init();
	}

	@EventHandler
	@SuppressWarnings("unused")
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

		FMLCommonHandler.instance().bus().register(new CraftEvent());
	}

	@EventHandler
	@SuppressWarnings("unused")
	public void postInit(FMLPostInitializationEvent event) {
		logger.info("Welcome to the new age!");
	}
}