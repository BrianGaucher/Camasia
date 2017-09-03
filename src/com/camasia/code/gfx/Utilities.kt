package com.camasia.code.gfx

/**
 * This utility class allows tying four values together.
 * It is used by the [Colour] class
 * Created by brian on 9/1/17.
 * @param a First value
 * @param b Second value
 * @param c Third value
 * @param d Fourth value
 */

data class Quadruple<A, B, C, D>(val a: A, val b: B, val c: C, val d: D)

/**
 * This converts all the singleColour/Short into Ints
 */
fun Quadruple<Short, Short, Short, Short>.toInt(): Quadruple<Int, Int, Int, Int> {
	return Quadruple(a.toInt(), b.toInt(), c.toInt(), d.toInt())
}
