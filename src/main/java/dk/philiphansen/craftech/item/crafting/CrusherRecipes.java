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

package dk.philiphansen.craftech.item.crafting;

import dk.philiphansen.craftech.util.ItemUtils;
import net.minecraft.item.ItemStack;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class CrusherRecipes {
	private static final CrusherRecipes instance = new CrusherRecipes();
	private Map crushingList = new HashMap();

	public static CrusherRecipes getInstance() {
		return instance;
	}

	@SuppressWarnings("unchecked")
	public void addRecipe(ItemStack inputStack, ItemStack outputStack) {
		crushingList.put(inputStack, outputStack);
	}

	public ItemStack getCrusherResult(ItemStack stack) {
		Iterator iterator = crushingList.entrySet().iterator();
		Entry entry;

		while (iterator.hasNext()) {
			entry = (Entry) iterator.next();

			if (matchFound(stack, entry)) {
				return (ItemStack) entry.getValue();
			}
		}
		return null;
	}

	public boolean hasCrusherRecipe(ItemStack stack) {
		Iterator iterator = crushingList.entrySet().iterator();
		Entry entry;

		while (iterator.hasNext()) {
			entry = (Entry) iterator.next();

			if (matchFound(stack, entry)) {
				return true;
			}
		}
		return false;
	}

	private boolean matchFound(ItemStack stack, Entry entry) {
		return ItemUtils.equals(stack, (ItemStack) entry.getKey());
	}
}
