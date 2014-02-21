package dk.philiphansen.craftech;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.init.Blocks;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import dk.philiphansen.craftech.blocks.ModBlocks;

@Mod(modid = CrafTech.MODID, name = CrafTech.NAME, version = CrafTech.VERSION)
public class CrafTech {
    public static final String MODID = "craftech";
    public static final String NAME = "CrafTech";
    public static final String VERSION = "${version}";
    
    public static final Logger logger = LogManager.getLogger(CrafTech.NAME);
    
    @Instance(CrafTech.NAME)
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
