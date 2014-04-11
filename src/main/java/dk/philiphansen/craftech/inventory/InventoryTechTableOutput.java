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

import dk.philiphansen.craftech.tileentity.TileEntityTechTable;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.item.ItemStack;

public class InventoryTechTableOutput extends InventoryCraftResult {
	private static final int inventorySize = 1;
	private static final int outputSlot = 9;
	private static final int maxStackSize = 64;
	private final TileEntityTechTable techTable;

	public InventoryTechTableOutput(TileEntityTechTable techTable) {
		this.techTable = techTable;
	}

	@Override
	public int getSizeInventory() {
		return inventorySize;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return techTable.getStackInSlot(outputSlot);
	}

	@Override
	public ItemStack decrStackSize(int slot, int count) {
		return techTable.decrStackSize(outputSlot, count);
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		return null;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		techTable.setInventorySlotContents(outputSlot, stack);
	}

	@Override
	public int getInventoryStackLimit() {
		return maxStackSize;
	}
}
