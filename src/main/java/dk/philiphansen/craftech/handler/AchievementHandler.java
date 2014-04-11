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

package dk.philiphansen.craftech.handler;

import dk.philiphansen.craftech.block.ModBlocks;
import dk.philiphansen.craftech.reference.BlockInfo;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.AchievementPage;

public class AchievementHandler {
	public static Achievement achievementTechTable;
	public static Achievement achievementCrusher;
	public static Achievement achievementBlastFurnace;

	public AchievementHandler() {
		makeAchievements();
		makeAchievementPage();
	}

	public void makeAchievements() {
		achievementTechTable = new Achievement("achievement." + BlockInfo.TECH_TABLE_NAME, BlockInfo.TECH_TABLE_NAME,
				4, -1, ModBlocks.techTable, AchievementList.buildWorkBench).initIndependentStat().registerStat();
		achievementCrusher = new Achievement("achievement." + BlockInfo.CRUSHER_NAME, BlockInfo.CRUSHER_NAME, 6, 0,
				ModBlocks.crusher, achievementTechTable).registerStat();
		achievementBlastFurnace = new Achievement("achievement." + BlockInfo.BLAST_FURNACE_NAME,
				BlockInfo.BLAST_FURNACE_NAME, 2, 0, ModBlocks.blastFurnace, achievementTechTable).registerStat();
	}

	public void makeAchievementPage() {
		AchievementPage achievementPageCraftech = new AchievementPage(StatCollector.translateToLocal("achievementPage"
				+ ".craftech"), achievementTechTable, achievementCrusher, achievementBlastFurnace);
		AchievementPage.registerAchievementPage(achievementPageCraftech);
	}
}
