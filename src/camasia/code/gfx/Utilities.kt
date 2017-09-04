package camasia.code.gfx

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



//I eventually want to use a typealias instead to remove overhead
typealias Coordinate = Pair<Int, Int>

/**
 * Used for storing a coordinate
 * @param X The X-coordinate
 * @param Y The Y-coordinate
 * @property X The X-coordinate
 * @property Y The Y-coordinate
 */
data class LegacyCoordinate(val X: Int, val Y: Int) {
	fun toCoordinate(): Coordinate = Coordinate(X, Y)
}


/**
 * This type is for clarifications that it's for storing a colour value
 */
typealias Colour12_bit = Short