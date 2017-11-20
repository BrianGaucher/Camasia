package code.gfx

/**
 * Created on 17-11-05.
 * @author brian
 * @since 2017/11/05
 * @param row this is the row of the sprite
 * @param column The column of the sprite
 * @param bit The bit-inversion
 *       1 & 3 give X-Axis
 *       2 & 3 give Y-axis
 */
data class Sprite0x2(val column: Int, val row: Int, val bit: Int) {
	init {
		check(column in 1..128 && row in 1..128 && bit in 1..3)
		column
	}
	fun update(): Sprite0x3 {
		val bit: Inversion = when (bit) {
			0 -> Inversion.NONE
			1 -> Inversion.X_AXIS
			2 -> Inversion.Y_AXIS
		/*3*/ else -> Inversion.ROTATE_180
		}
		return Sprite0x3(column, row, bit)
	}
}