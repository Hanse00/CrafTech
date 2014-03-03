package dk.philiphansen.craftech.creativetab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dk.philiphansen.craftech.blocks.ModBlocks;
import dk.philiphansen.craftech.reference.ModInfo;

public class CreativeTabCrafTech extends CreativeTabs {

	public CreativeTabCrafTech() {
		super(ModInfo.MODID);	
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getTabIconItem() {
		return Item.getItemFromBlock(ModBlocks.blockBlastFurnace);
	}

}
