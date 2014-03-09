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

import net.minecraft.item.ItemStack;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class CrusherRecipes {

    private static final CrusherRecipes instance = new CrusherRecipes();

    private Map crusherList = new HashMap();

    public static CrusherRecipes getInstance() {
        return instance;
    }

    public void addRecipe(ItemStack inputStack, ItemStack outputStack) {
        crusherList.put(inputStack, outputStack);
    }

    public ItemStack getCrusherResult(ItemStack inputStack) {
        Iterator iterator = crusherList.entrySet().iterator();
        Entry entry;

        do {
            if (!iterator.hasNext()) {
                return null;
            }

            entry = (Entry)iterator.next();

        } while (!equals(inputStack, (ItemStack) entry.getKey()));

        return (ItemStack)entry.getValue();
    }

    private boolean equals(ItemStack stack1, ItemStack stack2) {
        if ((stack1.getItem() == stack2.getItem()) && (stack1.getItemDamage() == stack2.getItemDamage() || stack2.getItemDamage() == 32767)) {
            return true;
        } else {
            return false;
        }
    }
}
