package code.gfx

/**
 * Created on 17-11-05.
 * @author brian
 * @since 2017/11/05
 * @param row this is the row of the sprite
 * @param column The column of the sprite
 *
 */
@Deprecated(message = "This is a relic", replaceWith = ReplaceWith("Sprite0x2(column, row,)", "code.gfx.Sprite0x2"), level = DeprecationLevel.HIDDEN) data class Sprite0x1(val column: Int, val row: Int) {
	init {
		check(column in 1..128 && row in 1..128)
	}
	
	fun update(bit: Int) = Sprite0x2(this.row, this.column, bit)
}