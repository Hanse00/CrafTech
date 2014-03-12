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

package dk.philiphansen.craftech.items.crafting;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class TechTableRecipe implements IRecipe {

    public final int recipeWidth;
    public final int recipeHeight;
    public final ItemStack[] recipeInput;
    public final ItemStack recipeItem;

    private ItemStack recipeOutput;

    public TechTableRecipe(int recipeWidth, int recipeHeight, ItemStack[] recipeInput, ItemStack recipeOutput, ItemStack recipeItem) {
        this.recipeWidth = recipeWidth;
        this.recipeHeight = recipeHeight;
        this.recipeInput = recipeInput;
        this.recipeItem = recipeItem;
        this.recipeOutput = recipeOutput;
    }

    public boolean matches(InventoryCrafting inventoryCrafting, IInventory recipeItem, World world) {
        for (int i = 0; i <= 3 - this.recipeWidth; ++i) {
            for (int j = 0; j <= 3 - this.recipeHeight; ++j) {
                if (checkMatch(inventoryCrafting, recipeItem, i, j, true)) {
                    return true;
                }

                if (checkMatch(inventoryCrafting, recipeItem, i, j, false)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean checkMatch(InventoryCrafting par1InventoryCrafting, IInventory recipeItemInventory, int par2, int par3, boolean par4) {
        for (int k = 0; k < 3; ++k) {
            for (int l = 0; l < 3; ++l) {
                int i1 = k - par2;
                int j1 = l - par3;
                ItemStack itemstack = null;
                ItemStack recipeItem = recipeItemInventory.getStackInSlot(0);

                if (i1 >= 0 && j1 >= 0 && i1 < this.recipeWidth && j1 < this.recipeHeight) {
                    if (par4) {
                        itemstack = recipeInput[this.recipeWidth - i1 - 1 + j1 * this.recipeWidth];
                    }
                    else {
                        itemstack = recipeInput[i1 + j1 * this.recipeWidth];
                    }
                }

                ItemStack itemstack1 = par1InventoryCrafting.getStackInRowAndColumn(k, l);

                if (itemstack1 != null || itemstack != null) {
                    if (itemstack1 == null && itemstack != null || itemstack1 != null && itemstack == null) {
                        return false;
                    }

                    if (itemstack.getItem() != itemstack1.getItem()) {
                        return false;
                    }

                    if (itemstack.getItemDamage() != 32767 && itemstack.getItemDamage() != itemstack1.getItemDamage()) {
                        return false;
                    }

                    if (recipeItem == null) {
                        return false;
                    }
                    if (recipeItem.getItem() != this.recipeItem.getItem()) {
                        return false;
                    }

                    if (recipeItem.getItem() == this.recipeItem.getItem() && recipeItem.getItemDamage() != this.recipeItem.getItemDamage()) {
                        return false;
                    }
                }
            }
        }

        return true;

    }

    @Override
    public boolean matches(InventoryCrafting var1, World var2) {
        return false;
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting var1) {
        return getRecipeOutput().copy();
    }

    @Override
    public int getRecipeSize() {
        return recipeHeight * recipeWidth;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return recipeOutput;
    }
}
