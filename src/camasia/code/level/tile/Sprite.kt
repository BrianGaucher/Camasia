package camasia.code.level.tile

/**
 * Use this class for describing sprites
 *
 * @author Brian 11/6/17
 * @param row this is the row of the sprite
 * @param column The column of the sprite
 * @param bit  = 0 : the bit-inversion of the sprite
 * @constructor creates all the fields.
 */
class Sprite(val row: Int, val column: Int, val bit: Int = 0, val colour: Unit)