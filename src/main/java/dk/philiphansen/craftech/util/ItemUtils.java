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

package dk.philiphansen.craftech.util;

import net.minecraft.item.ItemStack;

public class ItemUtils {
	public static boolean equals(ItemStack stack1, ItemStack stack2) {
		return (sameItem(stack1, stack2)) && ((sameDamage(stack1, stack2)) || (stack2.getItemDamage() == Short
				.MAX_VALUE));
	}

	private static boolean sameItem(ItemStack stack1, ItemStack stack2) {
		return stack1.getItem() == stack2.getItem();
	}

	private static boolean sameDamage(ItemStack stack1, ItemStack stack2) {
		return stack1.getItemDamage() == stack2.getItemDamage();
	}
}
