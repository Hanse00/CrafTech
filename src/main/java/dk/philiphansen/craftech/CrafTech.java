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

package dk.philiphansen.craftech;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.config.Configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import dk.philiphansen.craftech.blocks.ModBlocks;
import dk.philiphansen.craftech.config.ConfigHandler;
import dk.philiphansen.craftech.creativetab.CreativeTabCrafTech;
import dk.philiphansen.craftech.handler.GuiHandler;
import dk.philiphansen.craftech.handler.ModFuelHandler;
import dk.philiphansen.craftech.items.ModItems;
import dk.philiphansen.craftech.reference.ModInfo;
import dk.philiphansen.craftech.world.GenerationHandler;

@Mod(modid = ModInfo.MODID, name = ModInfo.NAME, version = ModInfo.VERSION)
public class CrafTech {
    
    public static final Logger logger = LogManager.getLogger(ModInfo.NAME);
    
    @Instance(ModInfo.MODID)
    public static CrafTech instance;
    
    public static CreativeTabs tabCrafTech = new CreativeTabCrafTech();
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	ConfigHandler.init(event.getSuggestedConfigurationFile());
    	
    	ModBlocks.init();
    	ModItems.init();
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event) {
    	ModBlocks.initTileentities();
    	
    	ModBlocks.initCrafting();
    	ModBlocks.initSmelting();
    	
    	ModItems.initCrafting();
    	ModItems.initSmelting();
    	
    	new ModFuelHandler();
    	new GenerationHandler();
    	
    	new GuiHandler();
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    	logger.info("Welcome to the new age!");
    }
}