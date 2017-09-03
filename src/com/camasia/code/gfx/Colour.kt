package com.camasia.code.gfx

/**
 * Created by brian on 9/1/17.
 */

typealias SingleColour = Short

enum class Position {
	RED, YELLOW, GREEN, BLUE
}

@Suppress("MemberVisibilityCanPrivate")
/**
 * This class is used for storing colours.
 *
 * @param red The colour substituting for the red pixels
 * @param yellow The colour substituting for the yellow pixels
 * @param green The colour substituting for the green pixels
 * @param blue The colour substituting for the blue pixels
 */
class Colour(red: Int, yellow: Int, green: Int, blue: Int = 0) {
	
	val red: SingleColour
	val yellow: SingleColour
	val green: SingleColour
	val blue: SingleColour
	
	init {
		val max = 0xfff
		require(red <= max && yellow <= max && green <= max && blue <= max) {
			"""Value of red is $red,
                |yellow is $yellow,
                |green is $green,
                |blue is $blue.
                |One is not less than 0xfff or 4096""".trimMargin()
		}
		this.red = red.toSingleColour()
		this.yellow = yellow.toSingleColour()
		this.green = green.toSingleColour()
		this.blue = blue.toSingleColour()
	}
	
	operator fun get(i: Int): Int {
		require(i in 0..3) { "index i must be between 0 and 3. It is $i" }
		return when (i) {
			0 -> red
			1 -> yellow
			2 -> green
			3 -> blue
			else -> throw IllegalArgumentException("Error in code")
		}.toInt()
	}
	
	operator fun get(i: Position): Int = when (i) {
		Position.RED -> red
		Position.YELLOW -> yellow
		Position.GREEN -> green
		Position.BLUE -> blue
	}.toInt()
	
	private fun Int.toSingleColour() = this.toShort()
	
	fun tuple() = Quadruple(red, yellow, green, blue)
	
	
}

fun test(): Unit {
	val tOb:Colour = Colour(red = 0xf00, yellow = 0xf80, green = 0x0f0, blue = 0x00f)
	var (red: Int,yellow,green, blue) = tOb.tuple().toInt()
	green = tOb.green.toInt
	red = tOb.get(2)
	yellow = tOb.get(Position.YELLOW)
	green = tOb[Position.GREEN]
	red = tOb[2]
	
	red = tOb.red.toInt()
	red = tOb.get(0)
	red = tOb.get(Position.RED)
	red = tOb[0]
	red = tOb[Position.RED]
	


}

