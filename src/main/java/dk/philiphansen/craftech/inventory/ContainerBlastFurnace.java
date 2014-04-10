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

public class ContainerBlastFurnace extends Container {
	private final TileEntityBlastFurnace blastFurnace;
	private final InventoryPlayer player;
	private int oldData;

	public ContainerBlastFurnace(InventoryPlayer player, TileEntityBlastFurnace blastFurnace) {
		this.blastFurnace = blastFurnace;
		this.player = player;

		addHotBar();
		addPlayerInventory();
		addBlastFurnaceSlots();
	}

	private void addHotBar() {
		for (int i = 0; i < 9; i++) {
			addSlotToContainer(new Slot(player, i, (18 * i) + 8, 139));
		}
	}

	private void addPlayerInventory() {
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 9; x++) {
				addSlotToContainer(new Slot(player, ((y * 9) + x) + 9, (18 * x) + 8, (18 * y) + 81));
			}
		}
	}

	private void addBlastFurnaceSlots() {
		addSlotToContainer(new ModSlot(SlotType.OUTPUT, blastFurnace, 0, 80, 55));
		addSlotToContainer(new ModSlot(SlotType.COKE_DUST, blastFurnace, 1, 62, 17));
		addSlotToContainer(new ModSlot(SlotType.LIMESTONE_DUST, blastFurnace, 2, 80, 17));
		addSlotToContainer(new ModSlot(SlotType.IRON_DUST, blastFurnace, 3, 98, 17));
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return blastFurnace.isUseableByPlayer(player);
	}

	@Override
	//TODO: Implement proper shift clicking
	public ItemStack transferStackInSlot(EntityPlayer player, int i) {
		return null;
	}

	@Override
	public void addCraftingToCrafters(ICrafting player) {
		super.addCraftingToCrafters(player);

		player.sendProgressBarUpdate(this, 0, blastFurnace.getProcessTimer());
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int id, int data) {
		super.updateProgressBar(id, data);

		blastFurnace.setProcessTimer(data);
	}

	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();

		for (Object player : crafters) {
			if (blastFurnace.getProcessTimer() != oldData) {
				((ICrafting) player).sendProgressBarUpdate(this, 0, blastFurnace.getProcessTimer());
			}
		}

		oldData = blastFurnace.getProcessTimer();
	}
}
