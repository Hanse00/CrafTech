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

import dk.philiphansen.craftech.item.crafting.TechTableRecipes;
import dk.philiphansen.craftech.tileentity.TileEntityTechTable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;

public class ContainerTechTable extends Container {
	private final TileEntityTechTable techTable;
	private final InventoryPlayer player;
	private final InventoryCrafting craftingMatrix;
	private final IInventory craftResult;
	private final IInventory recipeItem;

	public ContainerTechTable(InventoryPlayer player, TileEntityTechTable techTable) {
		this.techTable = techTable;
		this.player = player;
		craftingMatrix = new InventoryTechTableInput(this, 3, 3, techTable);
		craftResult = new InventoryTechTableOutput(techTable);
		recipeItem = new InventoryTechTableRecipe(this, techTable);

		addHotBar();
		addPlayerInventory();
		addTechTableInventory();
	}

	private void addHotBar() {
		for (int i = 0; i < 9; i++) {
			addSlotToContainer(new Slot(player, i, (18 * i) + 8, 142));
		}
	}

	private void addPlayerInventory() {
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 9; x++) {
				addSlotToContainer(new Slot(player, ((y * 9) + x) + 9, (18 * x) + 8, (18 * y) + 84));
			}
		}
	}

	private void addTechTableInventory() {
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 3; x++) {
				addSlotToContainer(new Slot(craftingMatrix, x + (y * 3), 50 + (18 * x), 17 + (y * 18)));
			}
		}

		addSlotToContainer(new SlotCrafting(player.player, craftingMatrix, craftResult, 0, 144, 35));
		addSlotToContainer(new ModSlot(SlotType.RECIPE, recipeItem, 0, 12, 35));

		onCraftMatrixChanged(craftingMatrix);
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return techTable.isUseableByPlayer(player);
	}

	@Override
	//TODO: Implement proper shift clicking
	public ItemStack transferStackInSlot(EntityPlayer player, int i) {
		return null;
	}

	@Override
	public void onCraftMatrixChanged(IInventory inventory) {
		craftResult.setInventorySlotContents(0, TechTableRecipes.getInstance().findMatchingRecipe(craftingMatrix,
				recipeItem, techTable.getWorldObj()));
	}
}
