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

package dk.philiphansen.craftech.inventory;

import dk.philiphansen.craftech.tileentities.TileentityTechTable;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.item.ItemStack;

public class InventoryTechTableOutput extends InventoryCraftResult {

    TileentityTechTable techTable;

    public InventoryTechTableOutput(TileentityTechTable techTable) {
        this.techTable = techTable;
    }

    @Override
    public int getSizeInventory() {
        return 1;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return techTable.getStackInSlot(9);
    }

    @Override
    public ItemStack decrStackSize(int slot, int count) {
        return techTable.decrStackSize(9, count);
    }

    @Override
    public ItemStack getStackInSlotOnClosing (int slot) {
        return null;
    }

    @Override
    public void setInventorySlotContents (int slot, ItemStack stack) {
        techTable.setInventorySlotContents(9, stack);
    }

    @Override
    public int getInventoryStackLimit () {
        return 64;
    }

}
