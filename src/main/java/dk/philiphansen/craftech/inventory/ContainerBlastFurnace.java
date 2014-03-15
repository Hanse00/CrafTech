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
import dk.philiphansen.craftech.tileentity.TileEntityBlastFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

//TODO: Comment code
public class ContainerBlastFurnace extends Container {

	private final TileEntityBlastFurnace blastFurnace;

	public ContainerBlastFurnace(InventoryPlayer player, TileEntityBlastFurnace blastFurnace) {
		this.blastFurnace = blastFurnace;

		for (int i = 0; i < 9; i++) {
			addSlotToContainer(new Slot(player, i, 8 + 18 * i, 130));
		}

		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 9; x++) {
				addSlotToContainer(new Slot(player, x + y * 9 + 9, 8 + 18 * x, 72 + y * 18));
			}
		}

		for (int i = 0; i < 3; i++) {
			addSlotToContainer(new ModSlot(i, blastFurnace, i, 62 + 18 * i, 8));
		}

		addSlotToContainer(new ModSlot(-1, blastFurnace, 3, 80, 46));
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return blastFurnace.isUseableByPlayer(player);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int i) {
		Slot slot = getSlot(i);

		if (slot != null && slot.getHasStack()) {
			ItemStack stack = slot.getStack();
			ItemStack result = stack.copy();

			if (i >= 36) {
				if (!super.mergeItemStack(stack, 0, 36, false)) {
					return null;
				}
			} else if (!mergeItemStack(stack, 36, 39, false)) {
				return null;
			}

			if (stack.stackSize == 0) {
				slot.putStack(null);
			} else {
				slot.onSlotChanged();
			}

			slot.onPickupFromSlot(player, stack);

			return result;
		}

		return null;
	}

	@Override
	public void addCraftingToCrafters(ICrafting player) {
		super.addCraftingToCrafters(player);

		player.sendProgressBarUpdate(this, 0, blastFurnace.getTimer());
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int id, int data) {
		super.updateProgressBar(id, data);

		blastFurnace.setTimer(data);
	}

	private int oldData;

	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();

		for (Object player : crafters) {
			if (blastFurnace.getTimer() != oldData) {
				((ICrafting) player).sendProgressBarUpdate(this, 0, blastFurnace.getTimer());
			}
		}

		oldData = blastFurnace.getTimer();
	}

	@Override
	protected boolean mergeItemStack(ItemStack stack, int min, int max, boolean backwards) {
		for (int i = min; i < max; i++) {
			Slot slot = getSlot(i);

			if (slot != null && slot.isItemValid(stack)) {
				return super.mergeItemStack(stack, i, i + 1, backwards);
			}
		}
		return false;
	}

}
