package dk.philiphansen.craftech;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import dk.philiphansen.craftech.blocks.ModBlocks;
import dk.philiphansen.craftech.reference.ModInfo;

@Mod(modid = ModInfo.MODID, name = ModInfo.NAME, version = ModInfo.VERSION)
public class CrafTech {
    
    public static final Logger logger = LogManager.getLogger(ModInfo.NAME);
    
    @Instance(ModInfo.NAME)
    public static CrafTech instance;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	ModBlocks.init();
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
		logger.info("Welcome to the new age!");
    }
}