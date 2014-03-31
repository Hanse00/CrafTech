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
import dk.philiphansen.craftech.reference.ModInfo;
import dk.philiphansen.craftech.tileentity.TileEntityCrusher;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiCrusher extends GuiContainer {
	private static final ResourceLocation texture = new ResourceLocation(ModInfo.ID, "textures/gui/crusher.png");
	private final TileEntityCrusher crusher;

	public GuiCrusher(InventoryPlayer player, TileEntityCrusher crusher) {
		super(new ContainerCrusher(player, crusher));

		this.crusher = crusher;

		xSize = 176;
		ySize = 163;

	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int x, int y) {
		GL11.glColor4f(1, 1, 1, 1);

		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);

		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

		//This will tick through until the "process" is complete the crusher
		int arrowHeight = (int) (crusher.getCompletion() * 0.12);
		if (arrowHeight > 0) {
			int srcX = xSize;
			int srcY = 0;

			drawTexturedModalRect(guiLeft + 85, guiTop + 36, srcX, srcY, 38, arrowHeight);
		}
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y) {
		String containerName = StatCollector.translateToLocal(crusher.getInventoryName());

		fontRendererObj.drawString(containerName, ((xSize / 2) - (fontRendererObj.getStringWidth(containerName) / 2)),
				6, 0x404040);
		fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 94, 0x404040);
	}
}
