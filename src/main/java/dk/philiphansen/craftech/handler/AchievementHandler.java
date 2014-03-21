/*
 * This file is part of CrafTech.
 *
 * CrafTech is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * CrafTech is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with CrafTech.  If not, see <http://www.gnu.org/licenses/>.
 */

package dk.philiphansen.craftech.handler;

import dk.philiphansen.craftech.block.ModBlocks;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.AchievementPage;

public class AchievementHandler {

	static Achievement achievementTechTable;
	static Achievement achievementCrusher;
	static Achievement achievementBlastFurnace;

	public AchievementHandler() {
		makeAchievements();
		makeAchievementPage();
	}

	public void makeAchievements() {
		achievementTechTable = new Achievement("achievement.techTable", "techTable", 0, 0, ModBlocks.blockTechTable,
				AchievementList.buildWorkBench).initIndependentStat().registerStat();
		achievementCrusher = new Achievement("achievement.crusher", "crusher", 2, 1, ModBlocks.blockCrusher,
				achievementTechTable).registerStat();
		achievementBlastFurnace = new Achievement("achievement.blastFurnace", "blastFurnace", -2, 1,
				ModBlocks.blockBlastFurnace, achievementTechTable).registerStat();
	}

	public void makeAchievementPage() {
		AchievementPage achievementPageCraftech = new AchievementPage(StatCollector.translateToLocal("achievementPage" +
				".craftech"), achievementTechTable, achievementCrusher, achievementBlastFurnace);
		AchievementPage.registerAchievementPage(achievementPageCraftech);
	}
}
