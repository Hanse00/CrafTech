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

package dk.philiphansen.craftech.items;

import cpw.mods.fml.common.registry.GameRegistry;
import dk.philiphansen.craftech.blocks.ModBlocks;
import dk.philiphansen.craftech.reference.ItemInfo;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemCoal;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;

public class ModItems {
	public static Item coalCoke;
	public static Item coalCokeDust;
	public static Item ironDust;
	public static Item limestoneDust;
    public static Item blastFurnaceRecipe;
    public static Item crusherRecipe;
	
	public static void init() {
		coalCoke = new ItemCoalCoke();
		coalCokeDust = new ItemCoalCokeDust();
		ironDust = new ItemIronDust();
		limestoneDust = new ItemLimestoneDust();
        blastFurnaceRecipe = new ItemBlastFurnaceRecipe();
        crusherRecipe = new ItemCrusherRecipe();
		
		GameRegistry.registerItem(coalCoke, ItemInfo.COALCOKE_NAME);
		GameRegistry.registerItem(coalCokeDust, ItemInfo.COALCOKE_DUST_NAME);
		GameRegistry.registerItem(ironDust, ItemInfo.IRON_DUST_NAME);
		GameRegistry.registerItem(limestoneDust, ItemInfo.LIMESTONE_DUST_NAME);
        GameRegistry.registerItem(blastFurnaceRecipe, ItemInfo.BLAST_FURNACE_RECIPE_NAME);
        GameRegistry.registerItem(crusherRecipe, ItemInfo.CRUSHER_RECIPE_NAME);
	}
	
	public static void initCrafting() {
		//Coal Coke
		GameRegistry.addRecipe(new ItemStack(ModBlocks.blockCoalCoke, 1), new Object[] {
			"CCC",
			"CCC",
			"CCC",
			'C', coalCoke
		});
	}
	
	public static void initSmelting() {
		GameRegistry.addSmelting(Items.coal, new ItemStack(coalCoke), 0.1F);
	}

    public static void genRecipes() {
        ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(new ItemStack(ModItems.blastFurnaceRecipe), 1, 1, 10));
        ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, new WeightedRandomChestContent(new ItemStack(ModItems.crusherRecipe), 1, 1, 10));

        ChestGenHooks.addItem(ChestGenHooks.MINESHAFT_CORRIDOR, new WeightedRandomChestContent(new ItemStack(ModItems.blastFurnaceRecipe), 1, 1, 10));
        ChestGenHooks.addItem(ChestGenHooks.MINESHAFT_CORRIDOR, new WeightedRandomChestContent(new ItemStack(ModItems.crusherRecipe), 1, 1, 10));

        ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST, new WeightedRandomChestContent(new ItemStack(ModItems.blastFurnaceRecipe), 1, 1, 10));
        ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST, new WeightedRandomChestContent(new ItemStack(ModItems.crusherRecipe), 1, 1, 10));

        ChestGenHooks.addItem(ChestGenHooks.PYRAMID_DESERT_CHEST, new WeightedRandomChestContent(new ItemStack(ModItems.blastFurnaceRecipe), 1, 1, 10));
        ChestGenHooks.addItem(ChestGenHooks.PYRAMID_DESERT_CHEST, new WeightedRandomChestContent(new ItemStack(ModItems.crusherRecipe), 1, 1, 10));

        ChestGenHooks.addItem(ChestGenHooks.PYRAMID_JUNGLE_CHEST, new WeightedRandomChestContent(new ItemStack(ModItems.blastFurnaceRecipe), 1, 1, 10));
        ChestGenHooks.addItem(ChestGenHooks.PYRAMID_JUNGLE_CHEST, new WeightedRandomChestContent(new ItemStack(ModItems.crusherRecipe), 1, 1, 10));

        ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_LIBRARY, new WeightedRandomChestContent(new ItemStack(ModItems.blastFurnaceRecipe), 1, 1, 10));
        ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_LIBRARY, new WeightedRandomChestContent(new ItemStack(ModItems.crusherRecipe), 1, 1, 10));
    }
}
