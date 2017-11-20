package code.gfx

/**
 * Created on 17-11-08.
 * @author Brian
 * @since 2017/11/08
 * @param row this is the row of the sprite
 * @param column The column of the sprite
 * @param bit The bit-inversion
 */
data class Sprite0x3(val column: Int, val row: Int, val bit: Inversion) {
	init {
		check(column in 0..127 && row in 0..127)
	}
	
	fun update(colour: Int): Sprite0x4 = Sprite0x4(this.column, this.row, this.bit, colour)
	
	
}