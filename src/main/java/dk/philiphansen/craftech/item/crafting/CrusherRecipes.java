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

import net.minecraft.item.ItemStack;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Handles the recipes for the crusher.
 * Used to store new recipes, and for the crusher to check if there's a recipe with the given item.
 * Also lets the crusher find out which output correlates to the given input.
 */
public class CrusherRecipes {

	private static final CrusherRecipes instance = new CrusherRecipes();
	private final Map crusherList = new HashMap();

	/**
	 * Gets the instance of the CrusherRecipes class.
	 *
	 * @return Instance
	 */
	public static CrusherRecipes getInstance() {
		return instance;
	}

	/**
	 * Add a recipe to the list of crusher recipes.
	 *
	 * @param inputStack  Itemstack to require for the recipe.
	 * @param outputStack Resulting itemstack from the recipe.
	 */
	public void addRecipe(ItemStack inputStack, ItemStack outputStack) {
		crusherList.put(inputStack, outputStack);
	}

	/**
	 * Get the resulting output from an input.
	 *
	 * @param inputStack The stack to get the output from.
	 * @return outputStack The result of the inputStack.
	 */
	public ItemStack getCrusherResult(ItemStack inputStack) {
	    /* Iterate through the list of recipes until list key equals input */
		Iterator iterator = crusherList.entrySet().iterator();
		Entry entry;

		do {
			if (!iterator.hasNext()) {
				return null;
			}

			entry = (Entry) iterator.next();

		} while (!equals(inputStack, (ItemStack) entry.getKey()));

        /* Return value correlating to the key (which equaled the input) */
		return (ItemStack) entry.getValue();
	}

	/**
	 * Checks if the given stack has a crusher recipe.
	 *
	 * @param stack ItemStack to check for.
	 * @return Boolean value indicating if the given stack has a recipe.
	 */
	public boolean hasCrusherRecipe(ItemStack stack) {
        /* Iterate through recipe list, return if there's a key correlating to the input stack */
		Iterator iterator = crusherList.entrySet().iterator();
		Entry entry;

		while (iterator.hasNext()) {
			entry = (Entry) iterator.next();

			if (equals(stack, (ItemStack) entry.getKey())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Check if two item stacks equal each other.
	 *
	 * @param stack1 First stack to check.
	 * @param stack2 Second stack to check.
	 * @return Boolean value indicating of the stacks are equal.
	 */
	private boolean equals(ItemStack stack1, ItemStack stack2) {
        /*
         * If items are the same, and damage value is the same
         * Or short max, not sure why, see furnace recipes
         */
		return ((stack1.getItem() == stack2.getItem()) && (stack1.getItemDamage() == stack2.getItemDamage() || stack2
				.getItemDamage() == Short.MAX_VALUE));
	}
}
