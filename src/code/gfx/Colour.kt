package code.gfx

@Suppress("MemberVisibilityCanPrivate")
/**
 * This class is used for storing colours.
 *
 * @author Brian Gaucher
 * @since 2017/09/03
 * 0.0.0.1.2
 * @param black The colour substituting for the black pixels
 * @param darkGrey The colour substituting for the darkGrey pixels
 * @param lightGrey The colour substituting for the lightGrey pixels
 * @param white The colour substituting for the white pixels
 * @constructor Creates a Colour variable from the colours given
 * @sample Colour(black = 0xfff, darkGrey
 *= 0xf80, lightGrey = 0x0f0, white = 0x00f)
 */
class Colour(black: Int, darkGrey: Int, lightGrey: Int, white: Int) {
	
	val black: Colour12_bit
	val darkGrey: Colour12_bit
	val lightGrey: Colour12_bit
	val white: Colour12_bit
	
	init {
		val max = 0xfff
		require(black <= max && darkGrey <= max && lightGrey <= max && white <= max) {
			"""Value of black is $black,
				|darkGrey is $darkGrey,
				|lightGrey is $lightGrey,
				|white is $white.
				|One is not less than 0xfff or 4096""".trimMargin()
		}
		this.black = black.Colour12_bit()
		this.darkGrey = darkGrey.Colour12_bit()
		this.lightGrey = lightGrey.Colour12_bit()
		this.white = white.Colour12_bit()
	}
	
	operator fun get(i: Int): Int {
		require(i in 0..3) { "index i must be between 0 and 3. It is $i" }
		return when (i) {
			0 -> black
			1 -> darkGrey
			2 -> lightGrey
			3 -> white
			else -> throw IllegalArgumentException("Error in code")
		}.toInt()
	}
	
	private fun Int.Colour12_bit() = this.toShort()
	
	fun tuple() = Quadruple(black, darkGrey, lightGrey, white)
	
}
