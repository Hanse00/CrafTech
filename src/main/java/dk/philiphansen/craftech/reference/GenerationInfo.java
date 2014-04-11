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

package dk.philiphansen.craftech.reference;

public class GenerationInfo {
	//176 * 32 adds up to roughly 10% of the chunk, the amount of stone that is limestone according to wikipedia.
	public static final int DEFAULT_LIMESTONE_VEIN_SIZE = 32;
	public static final int DEFAULT_LIMESTONE_VEIN_COUNT = 176;
	public static final int DEFAULT_LIMESTONE_MAX_HEIGHT = 256;
	public static final int DEFAULT_LIMESTONE_MIN_HEIGHT = 32;
	public static int LIMESTONE_VEIN_SIZE;
	public static int LIMESTONE_VEIN_COUNT;
	public static int LIMESTONE_MAX_HEIGHT;
	public static int LIMESTONE_MIN_HEIGHT;
}
