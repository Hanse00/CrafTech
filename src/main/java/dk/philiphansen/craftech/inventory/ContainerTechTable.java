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

import dk.philiphansen.craftech.items.crafting.TechTableRecipes;
import dk.philiphansen.craftech.tileentities.TileentityTechTable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;

public class ContainerTechTable extends Container {

    private TileentityTechTable techTable;
    public InventoryCrafting craftMatrix;
    public IInventory craftResult;
    public IInventory recipeItem;

    public ContainerTechTable(InventoryPlayer player, TileentityTechTable techTable) {
        this.techTable = techTable;
        craftMatrix = new InventoryTechTableInput(this, 3, 3, techTable);
        craftResult = new InventoryTechTableOutput(techTable);
        recipeItem = new InventoryTechTableRecipe(this, techTable);

        for (int i = 0; i < 9; i++) {
            addSlotToContainer(new Slot(player, i, 8 + 18 * i, 142));
        }

        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 9; x++) {
                addSlotToContainer(new Slot(player, x + y * 9 + 9, 8 + 18 * x, 84 + y * 18));
            }
        }

        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                addSlotToContainer(new Slot(craftMatrix, x + (y * 3), 50 + (18 * x), 17 + (y * 18)));
            }
        }

        addSlotToContainer(new SlotCrafting(player.player, craftMatrix, craftResult, 0, 144, 35));
        addSlotToContainer(new ModSlot(4, recipeItem, 0, 12, 35));

        onCraftMatrixChanged(craftMatrix);
    }


    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return techTable.isUseableByPlayer(player);
    }

    public ItemStack transferStackInSlot(EntityPlayer player, int i) {
        Slot slot = getSlot(i);

        if (slot != null && slot.getHasStack()) {
            ItemStack stack = slot.getStack();
            ItemStack result = stack.copy();

            if (i >= 36) {
                if (!super.mergeItemStack(stack, 0, 36, false)) {
                    return null;
                }
            }
            else if (!mergeItemStack(stack, 46, 47, false)) {
                return null;
            }

            if (stack.stackSize == 0) {
                slot.putStack(null);
            }
            else {
                slot.onSlotChanged();
            }

            slot.onPickupFromSlot(player, stack);

            return result;
        }

        return null;
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

    @Override
    public void onCraftMatrixChanged(IInventory inventory) {
        craftResult.setInventorySlotContents(0, TechTableRecipes.getInstance().findMatchingRecipe(craftMatrix, recipeItem, techTable.getWorldObj()));
    }
}
