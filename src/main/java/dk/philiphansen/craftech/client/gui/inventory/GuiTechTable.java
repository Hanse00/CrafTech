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

package dk.philiphansen.craftech.client.gui.inventory;

import dk.philiphansen.craftech.inventory.ContainerTechTable;
import dk.philiphansen.craftech.item.crafting.TechTableRecipe;
import dk.philiphansen.craftech.item.crafting.TechTableRecipes;
import dk.philiphansen.craftech.reference.ModInfo;
import dk.philiphansen.craftech.tileentity.TileEntityTechTable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

public class GuiTechTable extends GuiContainer {

	private static final ResourceLocation texture = new ResourceLocation(ModInfo.ID, "textures/gui/tech_table.png");
	private final TileEntityTechTable techTable;

	public GuiTechTable(InventoryPlayer player, TileEntityTechTable techTable) {
		super(new ContainerTechTable(player, techTable));

		this.techTable = techTable;

		xSize = 176;
		ySize = 166;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int x, int y) {
		GL11.glColor4f(1, 1, 1, 1);

		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);

		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y) {
		String containerName = StatCollector.translateToLocal(techTable.getInventoryName());

		fontRendererObj.drawString(containerName, ((xSize / 2) - (fontRendererObj.getStringWidth(containerName) / 2)),
				6, 0x404040);
		fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 94, 0x404040);

		renderItems();
	}

	/**
	 * Render the items correlating to the crafting recipe, in the crafting matrix.
	 */
	private void renderItems() {
		if (techTable.getStackInSlot(10) != null) {
			ItemStack recipeItem = techTable.getStackInSlot(10);
			TechTableRecipe recipe = TechTableRecipes.getInstance().findMatchingRecipe(recipeItem);
			ItemStack[] input = recipe.getRecipeInput();

			RenderItem renderer = new RenderItem();
			renderer.renderWithColor = false;
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GL11.glColor4d(0.6f, 0.6f, 0.6f, 0.7f);

			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					ItemStack itemStack = input[i * 3 + j];

					GL11.glPushMatrix();

					if (itemStack != null) {
						FontRenderer fontRenderer = itemStack.getItem().getFontRenderer(itemStack);

						renderer.renderItemIntoGUI(fontRenderer, mc.renderEngine, itemStack, 50 + (18 * j),
								17 + (i * 18));
					}

					GL11.glPopMatrix();
				}
			}
			GL11.glDisable(GL11.GL_BLEND);
		}
	}
}