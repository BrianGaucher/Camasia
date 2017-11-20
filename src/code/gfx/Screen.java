package code.gfx;

public class Screen {
	 //region fields
	 private static final byte BIT_MIRROR_X = 0x01; // used for mirroring an image
	 private static final byte BIT_MIRROR_Y = 0x02; // used for mirroring an image
	 public final int w, h; // width and height of the screen
	 public final int[] pixels; // pixels on the screen
	 private final SpriteSheet sheet; // sprite sheet used in the game
	 /* Used for the scattered dots at the edge of the light radius underground. */
	 private final int[] dither = new int[]{ 0, 8, 2, 10, 12, 4, 14, 6, 3, 11, 1, 9, 15, 7, 13, 5, };
	 private int xOffset; // the column offset of the screen.
	 private int yOffset; // the row offset of the screen
	 //endregion
	 
	 public Screen(int w, int h, SpriteSheet sheet) {
		  this.sheet = sheet; // assigns the sprite-sheet
		  this.w = w; // assigns width of the screen
		  this.h = h; // assigns height of the screen
		  
		  pixels = new int[w * h]; // integer array of all the pixels on the screen.
		  
	 }
	 
	 /**
	  * Clears all the colors on the screen
	  */
	 public void clear( ) {
		  clear( 0 );
	 }
	 
	 /**
	  * Clears all the colors on the screen
	  */
	 @SuppressWarnings( { "WeakerAccess", "SameParameterValue" } )
	 public void clear(int colour) {
		  for ( int i = 0; i < pixels.length; i++ ) // Loops through each pixel on the screen
				pixels[i] = colour; // turns each pixel into a single color (clearing the screen!)
	 }
	 
	 
	 /**
	  * Renders an object from the sprite sheet based on screen coordinates, tile (SpriteSheet location), colors, and bits (for mirroring)
	  *
	  * @param xp     The X-coordinate on the screen
	  * @param yp     The X-coordinate on the screen
	  * @param sprite the Sprite object containing (column, row) of the Spritesheet coordinate
	  */
	 public void render(int xp, int yp, Sprite0x5 sprite) {
		  int colours = sprite.getColour( );
		  final int tileSize = 8;
		  xp -= xOffset; // horizontal offset of the screen
		  yp -= yOffset; // vertical offset of the screen
		  // determines if the image should be mirrored horizontally and vertically
		  boolean mirrorX = sprite.getBit( ).getX( );
		  boolean mirrorY = sprite.getBit( ).getY( );
		  
		  int xTile = sprite.getColumn( ); // gets the column position of the tile
		  int yTile = sprite.getRow( );    // gets the row position of the tile
		  int toffs = xTile * tileSize + yTile * tileSize * sheet.width; // Get's the offset, the 8's represent the size of tile. (8 by 8 pixels)
		  
		  // You can space each line out if it looks too complicated at once.
		  
		  for ( int y = 0; y < tileSize; y++ ) {
				int ys = y; // current row pixel
				if ( mirrorY ) ys = 7 - y; // reverses the pixel for a mirroring effect
				if ( y + yp < 0 || y + yp >= h ) continue; // If the pixel is out of bounds, then skip the rest of the loop.
				
				for ( int x = 0; x < tileSize; x++ ) { // Loops 8 times (because of the width of the tile)
					 if ( x + xp < 0 || x + xp >= w )
						  continue; // If the pixelLocation is out of bounds, then skip the rest of the loop.
					 int xs = x; // current column pixelLocation
					 if ( mirrorX ) xs = 7 - x;  // Reverses the pixelLocation for a mirroring effect
					 //region comments
					 /*
					 * Brian :
					 * So I'm trying to understand this code below.
					 * so the colour variable is an integer, it has 32 bits
					 * 4 colours are stored in colours. Each one has 8 bis
					 * So colours >> (sheet.pixels[xs + (ys * sheet.width) + toffs] * 0x8)
					 * will bit-shift colours
					 * Sheet has 3 properties, width, height, and pixels
					 * Pixels is an integer array of 65536 integers, an integer has the value in 0..3
					 * 0 being black, #000000, 1 being dark gray #515151, 2 being light grey #adadad, 3 being white #ffffff
					 * Once it shifts $shift << 3, it becomes 0, 8, 16, 24
					 * When it shifts $colour $shift to the right, it then cuts out anything to the right of the desired value
					 * When it bitwise and with 0xff, it removes everything to the left of the desired value, leaving the wanted colour.
					 */
					 //endregion
					 /*int colour = 0x7ad7ac2b*/
					 int col; // gets the color based on the passed in colors value.
					 int shift;
					 int pixelLocation;
					 // gets
					 pixelLocation = xs + (ys * sheet.width) + toffs; // Possible values 0..0xffff, 65535, Sample : 0x1725, 5970
					 // Finds the pixelLocation in the sheet.
					 shift = sheet.pixels[pixelLocation];               // Possible value : 0..3; Sample : 2
					 // Shifts to the left 3 bits. Multiplies by eight
					 shift <<= 3;					/*		*= 8		*///Possible value 0x0,0x8,0x10,0x18, 0,8,16,24 Sample : 0x10, 16
					 // Shifts colours $shift bits to the left. Divides colour by 2^shift
					 col = colours >> shift;	/*		colours / 2^shift	*///Sample : 0x7ad7ac2b -> 0x7ad7
					 // Then it keeps the last 8 bits
					 col = col & 0xff; /*	col % 255	*/ // Sample : 0x7ad7 -> 0xd7
					 
					 pixelLocation = (x + xp) + (y + yp) * w;
					 pixels[pixelLocation] = col; // Inserts the colors into the image.
				}
		  }
	 }
	 
	 /**
	  * Renders an object from the sprite sheet based on screen coordinates, tile (SpriteSheet location), colors, and bits (for mirroring)
	  *
	  * @param xp     The X-coordinate on the screen
	  * @param yp     The X-coordinate on the screen
	  * @param sprite the Sprite object containing (column, row) of the Spritesheet coordinate
	  */
	 public void render(int xp, int yp, Sprite0x4 sprite) {
		  int colours = sprite.getColour( );
		  final int tileSize = 8;
		  xp -= xOffset; // horizontal offset of the screen
		  yp -= yOffset; // vertical offset of the screen
		  // determines if the image should be mirrored horizontally and vertically
		  boolean mirrorX = sprite.getBit( ).getX( );
		  boolean mirrorY = sprite.getBit( ).getY( );
		  
		  
		  int xTile = sprite.getColumn( ); // gets the column position of the tile
		  int yTile = sprite.getRow( );    // gets the row position of the tile
		  int toffs = xTile * tileSize + yTile * tileSize * sheet.width; // Get's the offset, the 8's represent the size of tile. (8 by 8 pixels)
		  
		  // You can space each line out if it looks too complicated at once.
		  
		  for ( int y = 0; y < tileSize; y++ ) {
				int ys = y; // current row pixel
				if ( mirrorY ) ys = 7 - y; // reverses the pixel for a mirroring effect
				if ( y + yp < 0 || y + yp >= h ) continue; // If the pixel is out of bounds, then skip the rest of the loop.
				
				for ( int x = 0; x < tileSize; x++ ) { // Loops 8 times (because of the width of the tile)
					 if ( x + xp < 0 || x + xp >= w )
						  continue; // If the pixelLocation is out of bounds, then skip the rest of the loop.
					 int xs = x; // current column pixelLocation
					 if ( mirrorX ) xs = 7 - x;  // Reverses the pixelLocation for a mirroring effect
					 
					 //region comments
					 /*
					 * Brian :
					 * So I'm trying to understand this code below.
					 * so the colour variable is an integer, it has 32 bits
					 * 4 colours are stored in colours. Each one has 8 bis
					 * So colours >> (sheet.pixels[xs + (ys * sheet.width) + toffs] * 0x8)
					 * will bit-shift colours
					 * Sheet has 3 properties, width, height, and pixels
					 * Pixels is an integer array of 65536 integers, an integer has the value in 0..3
					 * 0 being black, #000000, 1 being dark gray #515151, 2 being light grey #adadad, 3 being white #ffffff
					 * Once it shifts $shift << 3, it becomes 0, 8, 16, 24
					 * When it shifts $colour $shift to the right, it then cuts out anything to the right of the desired value
					 * When it bitwise and with 0xff, it removes everything to the left of the desired value, leaving the wanted colour.
					 */
					 //endregion
					 /*int colour = 0x7ad7ac2b*/
					 int col; // gets the color based on the passed in colors value.
					 int shift;
					 int pixelLocation;
					 // gets
					 pixelLocation = xs + (ys * sheet.width) + toffs; // Possible values 0..0xffff, 65535, Sample : 0x1725, 5970
					 // Finds the pixelLocation in the sheet.
					 shift = sheet.pixels[pixelLocation];               // Possible value : 0..3; Sample : 2
					 // Shifts to the left 3 bits. Multiplies by eight
					 shift <<= 3;					/*		*= 8		*///Possible value 0x0,0x8,0x10,0x18, 0,8,16,24 Sample : 0x10, 16
					 // Shifts colours $shift bits to the left. Divides colour by 2^shift
					 col = colours >> shift;	/*		colours / 2^shift	*///Sample : 0x7ad7ac2b -> 0x7ad7
					 // Then it keeps the last 8 bits
					 col = col & 0xff; /*	col % 255	*/ // Sample : 0x7ad7 -> 0xd7
					 
					 pixelLocation = (x + xp) + (y + yp) * w;
					 pixels[pixelLocation] = col; // Inserts the colors into the image.
				}
		  }
	 }
	 
	 /**
	  * Renders an object from the sprite sheet based on screen coordinates, tile (SpriteSheet location), colors, and bits (for mirroring)
	  *
	  * @param xp      The X-coordinate on the screen
	  * @param yp      The X-coordinate on the screen
	  * @param sprite  the Sprite object containing (column, row) of the Spritesheet coordinate
	  * @param colours The colour integer
	  * @deprecated
	  */
	 public void render(int xp, int yp, Sprite0x3 sprite, int colours) {
		  Sprite0x4 newSprite = sprite.update( colours );
		  render( xp, yp, newSprite );
	 }
	 
	 /**
	  * Renders an object from the sprite sheet based on screen coordinates, tile (SpriteSheet location), colors, and bits (for mirroring)
	  *
	  * @param xp      The X-coordinate on the screen
	  * @param yp      The X-coordinate on the screen
	  * @param sprite  the Sprite object containing (column, row) of the Spritesheet coordinate
	  * @param colours The colour integer
	  * @deprecated
	  */
	 public void render(int xp, int yp, Sprite0x2 sprite, int colours) {
		  render( xp, yp, sprite.update( ), colours );
		  
	 }
	 
	 /**
	  * Renders an object from the sprite sheet based on screen coordinates, tile (SpriteSheet location), colors, and bits (for mirroring).
	  *
	  * @param xp      The X-coordinate on the screen
	  * @param yp      The X-coordinate on the screen
	  * @param sprite  the Sprite object containing (column, row) of the Spritesheet coordinate
	  * @param colours The colour integer
	  * @param bits    the bits inversion
	  * @deprecated
	  */
	 public void render(int xp, int yp, Sprite0x1 sprite, int colours, int bits) {
		  Sprite0x2 spriteModified = sprite.update( bits );
		  render( xp, yp, spriteModified, colours );
	 }
	 
	 /**
	  * Renders an object from the sprite sheet based on screen coordinates, tile (SpriteSheet location), colors, and bits (for mirroring)
	  *
	  * @param xp     The X-coordinate on the screen
	  * @param yp     The X-coordinate on the screen
	  * @param tile   the Sprite int containing (column * 32 + row) of the Spritesheet coordinate
	  * @param colors The colour integer
	  * @param bits   the bits inversion
	  */
	 public void render(int xp, int yp, int tile, int colors, int bits) {
		  xp -= xOffset; // horizontal offset of the screen
		  yp -= yOffset; // vertical offset of the screen
		  boolean mirrorX = (bits & BIT_MIRROR_X) > 0; // determines if the image should be mirrored horizontally.
		  boolean mirrorY = (bits & BIT_MIRROR_Y) > 0; // determines if the image should be mirrored vertically.
		  
		  
		  int xTile = tile % 32; // gets the column position of the tile by taking the number and getting the remainder when you divide it by 32.
		  int yTile = tile / 32; // gets the row position of the tile by taking the number and diving it by 32
		  int toffs = xTile * 8 + yTile * 8 * sheet.width; // Get's the offset, the 8's represent the size of the tile. (8 by 8 pixels)
		  
		  // You can space each line out if it looks too complicated at once.
		  
		  for ( int y = 0; y < 8; y++ ) { // Loops 8 times (because of the height of the tile)
				int ys = y; // current row pixel
				if ( mirrorY ) ys = 7 - y; // Reverses the pixel for a mirroring effect
				if ( y + yp < 0 || y + yp >= h ) continue; // If the pixel is out of bounds, then skip the rest of the loop.
				for ( int x = 0; x < 8; x++ ) { // Loops 8 times (because of the width of the tile)
					 if ( x + xp < 0 || x + xp >= w )
						  continue; // If the pixel is out of bounds, then skip the rest of the loop.
					 int xs = x; // current column pixel
					 if ( mirrorX ) xs = 7 - x;  // Reverses the pixel for a mirroring effect
					 int col = (colors >> (sheet.pixels[xs + ys * sheet.width + toffs] * 8)) & 255; // gets the color based on the passed in colors value.
					 if ( col < 255 ) pixels[(x + xp) + (y + yp) * w] = col; // Inserts the colors into the image.
				}
		  }
	 }
	 
	 /**
	  * Sets the offset of the screen
	  */
	 public void setOffset(int xOffset, int yOffset) {
		  this.xOffset = xOffset; // assigns the horizontal offset
		  this.yOffset = yOffset; // assigns the vertical offset
	 }
	 
	 /**
	  * Overlays the screen with pixels
	  */
	 public void overlay(Screen screen2, int xa, int ya) {
		  int[] oPixels = screen2.pixels; // Integer array of pixels to overlay the screen with.
		  int i = 0; // current pixel on the screen
		  for ( int y = 0; y < h; y++ ) { // Loops through the height of the screen.
				for ( int x = 0; x < w; x++ ) { // Loops through the width of the screen.
				 /* if the current pixel divided by 10 is smaller than the dither thingy with a complicated formula
					then it will fill the pixel with a black color. Yep, Nailed it! */
					 if ( oPixels[i] / 10 <= dither[((x + xa) & 3) + ((y + ya) & 3) * 4] ) pixels[i] = 0;
					 i++; // moves to the next pixel.
				}
				
		  }
	 }
	 
	 public void renderLight(int x, int y, int r) {
		  x -= xOffset; // applies the horizontal offset to the column position
		  y -= yOffset; // applies the vertical offset to the row position
		  int x0 = x - r; // starting column position of the circle
		  int x1 = x + r; // ending column position of the circle
		  int y0 = y - r; // starting row position of the circle
		  int y1 = y + r; // ending row position of the circle
		  
		  if ( x0 < 0 ) x0 = 0; // If the starting position of the circle is less than 0, then move it to 0
		  if ( y0 < 0 ) y0 = 0; // If the starting position of the circle is less than 0, then move it to 0
		  if ( x1 > w ) x1 = w; // If the ending position of the circle is more than the width, then move it to the width
		  if ( y1 > h )
				y1 = h; // If the ending position of the circle is more than the height, then move it to the height
		  for ( int yy = y0; yy < y1; yy++ ) { // Loops for (the difference between y0 and y1) times
				int yd = yy - y; // the vertical difference between the current pixel and the row position
				yd = yd * yd; // squares the yd value
				for ( int xx = x0; xx < x1; xx++ ) { // Loops for (the difference between x0 and x1) times
					 int xd = xx - x; // the horizontal difference between the current pixel and the column position
					 int dist = xd * xd + yd; // squares the distance of xd and adds yd for total distance.
					 if ( dist <= r * r ) { // if distance is smaller or equal to r (radius) squared then...
						  int br = 255 - dist * 255 / (r * r); // (255 - (distance value * 255) / r�) the area where light will be rendered
						  if ( pixels[xx + yy * w] < br )
								pixels[xx + yy * w] = br; // If the current pixel is smaller than br, then the pixel will equal br.
					 }
				}
		  }
	 }
}