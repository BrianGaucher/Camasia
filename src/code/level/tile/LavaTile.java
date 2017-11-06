package code.level.tile;

import code.entity.Entity;
import code.gfx.Color;
import code.gfx.Screen;
import code.level.Level;

import java.util.Random;

public class LavaTile extends Tile {
	 private Random wRandom = new Random( );

	public LavaTile(int id) {
		super(id); // assigns the id
		connectsToSand = true; // this tile can connect to sand
		connectsToLava = true; // this tile can connect to lava
	}

	public void render(Screen screen, Level level, int x, int y) {
		/* Sets the seed for which it will affect random variables */
		wRandom.setSeed((tickCount + (x / 2 - y) * 4311) / 10 * 54_687_121L + x * 3_271_612L + y * 3_412_987_161L );

		int col = Color.get(500, 500, 520, 550); // main color of the lava
		int transitionColor1 = Color.get(3, 500, level.dirtColor - 111, level.dirtColor); // transition color with dirt
		int transitionColor2 = Color.get(3, 500, level.sandColor - 110, level.sandColor); // transition color with sand

		boolean u = !level.getTile(x, y - 1).connectsToLava; // Checks if the tile above can NOT connect to lava
		boolean d = !level.getTile(x, y + 1).connectsToLava; // Checks if the tile below can NOT connect to lava
		boolean l = !level.getTile(x - 1, y).connectsToLava; // Checks if the tile to the left can NOT connect to lava
		boolean r = !level.getTile(x + 1, y).connectsToLava; // Checks if the tile to the right can NOT connect to lava

		boolean su = u && level.getTile(x, y - 1).connectsToSand; // Checks u, and sees if the tile above can connect to sand
		boolean sd = d && level.getTile(x, y + 1).connectsToSand; // Checks d, and sees if the tile down can connect to sand 
		boolean sl = l && level.getTile(x - 1, y).connectsToSand; // Checks l, and sees if the tile to the left can connect to sand 
		boolean sr = r && level.getTile(x + 1, y).connectsToSand; // Checks r, and sees if the tile to the right can connect to sand 

		if (!u && !l) { // if the tile to the left, and the tile above can connect to lava...
			screen.render(x * 16 + 0, y * 16 + 0, wRandom.nextInt(4), col, wRandom.nextInt(4)); // renders the top-left part of the tile
		} else
			/* Renders the the top-left part with a corner depending on if the tile is grass or sand */
			screen.render(x * 16 + 0, y * 16 + 0, (l ? 14 : 15) + (u ? 0 : 1) * 32, (su || sl) ? transitionColor2 : transitionColor1, 0);

		if (!u && !r) { // if the tile to the right, and the tile above can connect to lava...
			screen.render(x * 16 + 8, y * 16 + 0, wRandom.nextInt(4), col, wRandom.nextInt(4)); // renders the top-right part of the tile
		} else
			/* Renders the the top-right part with a corner depending on if the tile is grass or sand */
			screen.render(x * 16 + 8, y * 16 + 0, (r ? 16 : 15) + (u ? 0 : 1) * 32, (su || sr) ? transitionColor2 : transitionColor1, 0);

		if (!d && !l) { // if the tile to the left, and the tile below can connect to lava...
			screen.render(x * 16 + 0, y * 16 + 8, wRandom.nextInt(4), col, wRandom.nextInt(4)); // renders the bottom-left part of the tile
		} else
			/* Renders the the top-right part with a corner depending on if the tile is grass or sand */
			screen.render(x * 16 + 0, y * 16 + 8, (l ? 14 : 15) + (d ? 2 : 1) * 32, (sd || sl) ? transitionColor2 : transitionColor1, 0);
		
		if (!d && !r) { // if the tile to the right, and the tile below can connect to lava...
			screen.render(x * 16 + 8, y * 16 + 8, wRandom.nextInt(4), col, wRandom.nextInt(4)); // renders the bottom-right part of the tile
		} else
			/* Renders the the top-right part with a corner depending on if the tile is grass or sand */
			screen.render(x * 16 + 8, y * 16 + 8, (r ? 16 : 15) + (d ? 2 : 1) * 32, (sd || sr) ? transitionColor2 : transitionColor1, 0);
	}

	/** Determines if the entity can pass through the block */
	public boolean mayPass(Level level, int x, int y, Entity e) {
		return e.canSwim(); // if the entity can swim, then they can pass through
	}

	public void tick(Level level, int xt, int yt) {
		 int xn = xt; // next column position
		 int yn = yt; // next row position

		if (random.nextBoolean()) // makes a random decision of true or false
			 xn += random
						.nextInt( 2 ) * 2 - 1; // if that decision is true, then the next column position = (random value between 0 to 1) * 2 - 1
		else
			 yn += random
						.nextInt( 2 ) * 2 - 1; // if that decision is false, then the next row position = (random value between 0 to 1) * 2 - 1

		if (level.getTile(xn, yn) == Tile.hole) { // if the next positions are a hole tile then...
			level.setTile(xn, yn, this, 0); // set that hole tile to a lava tile
		}
	}

	/** Gets the underground light level. */
	public int getLightRadius(Level level, int x, int y) {
		return 6; // returns a radius of 6
	}
}
