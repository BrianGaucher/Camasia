package code.gfx

/**
 * Created on 17-11-10.
 * @author Brian
 * @since 2017/11/10
 * @param row this is the row of the sprite
 * @param column The column of the sprite
 * @param bit The bit-inversion
 * @param colour The colour integer to colourise this sprite
 */
data class Sprite0x6(val column: Int, val row: Int, val bit: Inversion, val colour: Colour) {
	
	init {
		check(column in 0..127 && row in 0..127)
	}
	
}