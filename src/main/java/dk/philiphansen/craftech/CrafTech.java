package dk.philiphansen.craftech;

import net.minecraft.init.Blocks;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;

@Mod(modid = CrafTech.MODID, version = CrafTech.VERSION)
public class CrafTech
{
    public static final String MODID = "craftech";
    public static final String VERSION = "1.0";
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
		System.out.println("Welcome to the new age!");
    }
}
