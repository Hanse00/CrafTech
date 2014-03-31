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
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;

class InventoryTechTableInput extends InventoryCrafting {

	private final int width;
	private final int height;
	private final Container container;
	private final TileEntityTechTable techTable;

	public InventoryTechTableInput(Container container, int width, int height, TileEntityTechTable techTable) {
		super(container, width, height);
		this.container = container;
		this.height = height;
		this.techTable = techTable;
		this.width = width;
	}

	@Override
	public int getSizeInventory() {
		return 9;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		if (slot >= getSizeInventory()) {
			return null;
		} else {
			return techTable.getStackInSlot(slot);
		}
	}

	@Override
	public ItemStack getStackInRowAndColumn(int row, int column) {
		if ((row >= 0 && row < width) && (column >= 0 && column < height)) {
			return getStackInSlot(row + (column * width));
		} else {
			return null;
		}
	}

	@Override
	public ItemStack decrStackSize(int slot, int count) {
		ItemStack itemstack = getStackInSlot(slot);

		if (itemstack != null) {
			if (itemstack.stackSize <= count) {
				setInventorySlotContents(slot, null);
				container.onCraftMatrixChanged(this);

			} else {
				itemstack = itemstack.splitStack(count);
				container.onCraftMatrixChanged(this);
			}

		}

		return itemstack;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		techTable.setInventorySlotContents(slot, stack);
		container.onCraftMatrixChanged(this);
	}
}
