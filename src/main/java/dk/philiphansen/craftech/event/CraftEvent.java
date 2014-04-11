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

package dk.philiphansen.craftech.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import dk.philiphansen.craftech.block.ModBlocks;
import dk.philiphansen.craftech.handler.AchievementHandler;
import net.minecraft.item.Item;

public class CraftEvent {
	@SubscribeEvent
	@SuppressWarnings("unused")
	public void onCraftEvent(PlayerEvent.ItemCraftedEvent event) {
		if (event.crafting.getItem() == Item.getItemFromBlock(ModBlocks.techTable)) {
			event.player.addStat(AchievementHandler.achievementTechTable, 1);
		} else if (event.crafting.getItem() == Item.getItemFromBlock(ModBlocks.crusher)) {
			event.player.addStat(AchievementHandler.achievementCrusher, 1);
		} else if (event.crafting.getItem() == Item.getItemFromBlock(ModBlocks.blastFurnace)) {
			event.player.addStat(AchievementHandler.achievementBlastFurnace, 1);
		}
	}
}
