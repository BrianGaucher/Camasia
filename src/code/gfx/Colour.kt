package code.gfx

@Suppress("MemberVisibilityCanPrivate")
/**
 * This class is used for storing colours.
 *
 * @author Brian Gaucher
 * @since 2017/09/03
 * 0.0.0.1.2
 * @param red The colour substituting for the red pixels
 * @param yellow The colour substituting for the yellow pixels
 * @param green The colour substituting for the green pixels
 * @param blue The colour substituting for the blue pixels
 * @constructor Creates a Colour variable from the colours given
 * @sample Colour(red = 0xfff, yellow = 0xf80, green = 0x0f0, blue = 0x00f)
 */
class Colour(red: Int, yellow: Int, green: Int, blue: Int = 0) {
	
	val red: Colour12_bit
	val yellow: Colour12_bit
	val green: Colour12_bit
	val blue: Colour12_bit
	
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
	
	private fun Int.toSingleColour() = this.toShort()
	
	fun tuple() = Quadruple(red, yellow, green, blue)
	
}
