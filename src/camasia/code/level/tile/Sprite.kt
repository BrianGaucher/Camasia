package camasia.code.level.tile

import camasia.code.gfx.Colour

/**
 * Use this class for describing sprites
 *
 * @author Brian 11/6/17
 * @param row this is the row of the sprite
 * @param column The column of the sprite
 * @param bit  = 0 : the bit-inversion of the sprite
 * @param colour The colour variable
 * @constructor creates all the fields.
 */
class Sprite(val row: Int, val column: Int, val bit: Inversion = Inversion.NONE, val colour: Colour)

enum class Inversion(val X: Boolean, val Y: Boolean) {
	NONE(false, false), X_AXIS(true, false), Y_AXIS(false, true), MIRROR_180(true, true)
	
}