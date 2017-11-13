package code.level.levelgen

import code.level.levelgen.LevelGen.Companion.createCheckerboardMap
import code.level.levelgen.LevelGen.Companion.createDirtMap
import code.level.levelgen.LevelGen.Companion.createFourthLevel
import code.level.levelgen.LevelGen.Companion.createThirdLevel
import code.level.tile.Tile
import code.level.tile.Tile.*
import java.awt.Image
import java.awt.image.BufferedImage
import javax.swing.ImageIcon
import javax.swing.JOptionPane

/**
 * This class is meant to become a replacement for the current LegacyLevelGen class
 * @author Brian Gaucher
 * @since 2017/10/08
 */
class LevelGen {
	
	companion object {
		
		/**
		 * This will create a checkerboard world
		 * @param w The width of the map
		 * @param h The height of the map
		 */
		fun createCheckerboardMap(w: Int, h: Int): LevelMap {
			val map = ByteArray(w * h)
			val data = ByteArray(w * h)
			for (x in 0 until w) {
				for (y in 0 until h) {
					val i: Int = x + (y * w)
					map[i] = if (x + y and 1 == 0) grass.id else rock.id
				}
			}
			return arrayOf(map, data) // returns the map's tiles and data.
		}
		
		/**
		 * This will create an all-dirt world
		 * @param w The width of the map
		 * @param h The height of the map
		 */
		fun createDirtMap(w: Int, h: Int): LevelMap {
			val map = ByteArray(w * h)
			val data = ByteArray(w * h)
			for (i in 0 until map.size) map[i] = grass.id
			
			map[29 + 21 * w] = stairsUp.id
			
			
			addStairs(w, h, map, 0x2df) // Adds the stairs
			return arrayOf(map, data) // returns the map's tiles and data.
		}
		
		/**
		 * This will create a checkerboard world
		 * @param w The width of the map
		 * @param h The height of the map
		 */
		fun createThirdLevel(w: Int, h: Int): LevelMap {
			//region Protected
			val map = ByteArray(w * h)
			val data = ByteArray(w * h)
			operator fun ByteArray.set(x: Int, y: Int, value: Tile) = this.set(y * w + x, value.id)
			//endregion
			
			// This populates the entire world with grass
			for (i in 0 until map.size) map[i] = grass.id
			
			// Makes the outside walls
			for (i in 20..29) map[20, i] = rock
			for (i in 20..29) map[29, i] = rock
			for (i in 20..29) map[i, 20] = rock
			for (i in 20..29) map[i, 29] = rock
			
			// Makes the inside walls
			for (i in 20..25) map[25, i] = rock
			for (i in 27..28) map[23, i] = rock
			for (i in 27..28) map[27, i] = rock
			for (i in 20..23) map[i, 25] = rock
			for (i in 27..28) map[i, 25] = rock
			
			// Makes the articles.
			map[28, 21] = stairsDown
			map[26, 29] = sand
			map[25, 30] = tree
			map[27, 30] = tree
			
			//region Protected
			addStairs(w, h, map, 0x248) // Adds the stairs
			return arrayOf(map, data) // returns the map's tiles and data.
			//endregion
		}
		
		/**
		 * This will create a checkerboard world
		 * @param w The width of the map
		 * @param h The height of the map
		 */
		fun createFourthLevel(w: Int, h: Int): LevelMap {
			//region Protected
			val map = ByteArray(w * h)
			val data = ByteArray(w * h)
			operator fun ByteArray.set(x: Int, y: Int, value: Tile) = this.set(y * w + x, value.id)
			//endregion
			
			// This populates the entire world with grass
			for (i in 0 until map.size) map[i] = grass.id
			
			// Makes the outside walls
			for (i in 20..29) map[20, i] = rock
			for (i in 20..29) map[29, i] = rock
			for (i in 20..29) map[i, 20] = rock
			for (i in 20..29) map[i, 29] = rock
			
			// Makes the inside walls
			for (i in 20..25) map[25, i] = rock
			for (i in 27..28) map[23, i] = rock
			for (i in 27..28) map[27, i] = rock
			for (i in 20..23) map[i, 25] = rock
			for (i in 27..28) map[i, 25] = rock
			
			// Makes the stairs
			map[28, 28] = stairsDown
			
			//region Protected
			addStairs(w, h, map, 0x248) // Adds the stairs
			return arrayOf(map, data) // returns the map's tiles and data.
			//endregion
		}
		
		private fun addStairs(w: Int, h: Int, map: ByteArray, frequency: Int = 0x22) {
			for (x in 0 until w) {
				for (y in 0 until h) {
					val i: Int = x + (y * w)
					if (i % frequency == 0) map[i] = stairsDown.id
				}
			}
		}
	}
	
	
}


/**
 * This will generate the world. Especially useful in testing.
 */
fun main(args: Array<String>) {
	
	var hasQuit = false // Determines if the player has quit the program or not.
	val w = 128 // width of the map
	val h = 128 // height of the map
	val options = arrayOf("Checkerboard", "Dirt", "Third", "Fourth", "Quit") // Name of the buttons used for the window.
	val optionsFun: Array<() -> LevelMap> = arrayOf({ -> createCheckerboardMap(w, h) }, { -> createDirtMap(w, h) }, { -> createThirdLevel(w, h) }, { -> createFourthLevel(w, h) }, { -> throw IllegalArgumentException("This should quit") })
	
	var level = 0 // map being looked at (0 = all-dirt, 1 = checkerboard, 2 = quit)
	while (!hasQuit) { //If the player has not quit the map
		val map: ByteArray// the map
		
		try {
			map = optionsFun[level]()[0] // gets the tiles section
		} catch (e: IllegalArgumentException) {
			println("There is an error in the code in the levelGenerator class")
			throw    IllegalStateException("Fix the error in LevelGenerator")
		}
		println("Generated map $level")
		
		val img = BufferedImage(w, h, BufferedImage.TYPE_INT_RGB) // creates an image
		val pixels = IntArray(w * h) // The pixels in the image. (an integer array, the size is Width * height)
		for (y in 0 until h) { // Loops through the height of the map
			for (x in 0 until w) { // (inner-loop)Loops through the entire width of the map
				val i = x + y * w // current tile of the map.
				
				/*The colors used in the pixels are hexadecimal (0xRRGGBB).
				  0xff0000 would be fully red
				 0x00ff00 would be fully blue
				 0x0000ff would be fully green
				 0x000000 would be black
				 and 0xffffff would be white
				 etc. */
				if (map[i] == water.id) pixels[i] = 0x000080 // If the tile is water, then the pixel will be blue
				if (map[i] == grass.id) pixels[i] = 0x208020 // If the tile is grass, then the pixel will be green
				if (map[i] == rock.id) pixels[i] = 0x909090 // if the tile is rock, then the pixel will be gray
				if (map[i] == dirt.id) pixels[i] = 0x604040 // if the tile is dirt, then the pixel will be brown
				if (map[i] == sand.id) pixels[i] = 0xa0a020  // if the tile is sand, then the pixel will be yellow
				if (map[i] == tree.id) pixels[i] = 0x003000 // if the tile is tree, then the pixel will be a darker green
				if (map[i] == lava.id) pixels[i] = 0xff2020 // if the tile is lava, then it will be red
				if (map[i] == cloud.id) pixels[i] = 0xeeeeee // if the tile is a cloud, then it will be light gray
				if (map[i] == stairsDown.id) pixels[i] = 0x000000 // if the tile is a stairs down, then it will be white.
				if (map[i] == stairsUp.id) pixels[i] = 0x000000 // if the tile is a stairs up, then it will be white.
				if (map[i] == cloudCactus.id) pixels[i] = 0xdd55dd // if the tile is a cloud cactus, then it will be pink
				if (map[i] == infiniteFall.id) pixels[i] = 0xcccccc // if the tile is a cloud, then it will be lighter gray
			}
		}
		img.setRGB(0, 0, w, h, pixels, 0, w) // sets the pixels into the image
		
		val o = JOptionPane.showOptionDialog(// creates a new window dialog (It's an integer because it returns a number)
				// lists the buttons below the image
				null // start value (not important)
				, null, // this would normally be used for a message, but since we use a image so it's null.
				"Map Generator", // Title of the window
				JOptionPane.YES_NO_OPTION, // Option type
				JOptionPane.QUESTION_MESSAGE, // message type (not important)
				ImageIcon(img.getScaledInstance(w * 4, h * 4, Image.SCALE_AREA_AVERAGING)), // creates the image, and scales it up 4 times as big
				options, null)// this would normally be used for a parent component (parent window), but we don't have one so it's null.
		/* Now you noticed that we made the dialog an integer. This is because when you click a button it will return a number.
			 Since we passed in 'options', the window will return 0 if you press "Another" and it will return 1 when you press "Quit".
			If you press the red "column" close mark, the window will return -1 */
		
		// If the dialog returns -1 (red "column" button) or 1 ("Quit" button) then...
		
		level = o
		if (o == -1 || o == options.lastIndex) hasQuit = true // stop the loop and close the program.
	}
}

/**
 * This is an easier way to express the maps.
 * The first index is the map
 * The second is the data
 * Each ByteArray is 16 383 indexes. The bi-dimensional maps are flattened out.
 */
typealias LevelMap = Array<ByteArray> // An easier way to express the maps.