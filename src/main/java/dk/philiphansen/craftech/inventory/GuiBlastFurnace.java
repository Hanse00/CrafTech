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

package dk.philiphansen.craftech.inventory;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dk.philiphansen.craftech.item.ModItems;
import dk.philiphansen.craftech.reference.ModInfo;
import dk.philiphansen.craftech.tileentity.TileEntityBlastFurnace;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiBlastFurnace extends GuiContainer {

	private static final ResourceLocation texture = new ResourceLocation(ModInfo.ID, "textures/gui/blast_furnace.png");
	private final TileEntityBlastFurnace blastFurnace;

	public GuiBlastFurnace(InventoryPlayer player, TileEntityBlastFurnace blastFurnace) {
		super(new ContainerBlastFurnace(player, blastFurnace));

		this.blastFurnace = blastFurnace;

		xSize = 176;
		ySize = 163;

	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int x, int y) {
		GL11.glColor4f(1, 1, 1, 1);

		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);

		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

		int arrowHeight = (int) (blastFurnace.getCompletion() * 0.12);
		if (arrowHeight > 0) {
			int srcX = xSize;
			int srcY = 0;

			drawTexturedModalRect(guiLeft + 69, guiTop + 36, srcX, srcY, 38, arrowHeight);

		}
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y) {
		String containerName = StatCollector.translateToLocal(blastFurnace.getInventoryName());

		fontRendererObj.drawString(containerName, ((xSize / 2) - (fontRendererObj.getStringWidth(containerName) / 2)),
				6, 0x404040);
		fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 94, 0x404040);

		renderItems();
	}

	private void renderItems() {
		RenderItem renderItem = new RenderItem();
		renderItem.renderWithColor = false;
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glColor4d(0.6f, 0.6f, 0.6f, 0.7f);

		GL11.glPushMatrix();

		renderItem.renderItemIntoGUI(fontRendererObj, mc.renderEngine, new ItemStack(ModItems.itemIronDust), 62, 17);
		renderItem.renderItemIntoGUI(fontRendererObj, mc.renderEngine, new ItemStack(ModItems.itemLimestoneDust), 80,
				17);
		renderItem.renderItemIntoGUI(fontRendererObj, mc.renderEngine, new ItemStack(ModItems.itemCokeDust), 98, 17);

		GL11.glPopMatrix();
		GL11.glDisable(GL11.GL_BLEND);
	}
}
