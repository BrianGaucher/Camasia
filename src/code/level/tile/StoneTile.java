package code.level.tile;

import code.entity.Entity;
import code.gfx.Color;
import code.gfx.Screen;
import code.level.Level;

public class StoneTile extends Tile {
	public StoneTile(int id) {
		super(id); // assigns the id
	}

	/** Draws the tile to the screen */
	public void render(Screen screen, Level level, int x, int y) {
		int rc1 = 111; // first rock color
		int rc2 = 333; // second rock color
		int rc3 = 555; // third rock color
		screen.render(x * 16 + 0, y * 16 + 0, 32, Color.get(rc1, level.dirtColor, rc2, rc3), 0); // renders the top-left part of the tile
		screen.render(x * 16 + 8, y * 16 + 0, 32, Color.get(rc1, level.dirtColor, rc2, rc3), 0); // renders the top-right part of the tile
		screen.render(x * 16 + 0, y * 16 + 8, 32, Color.get(rc1, level.dirtColor, rc2, rc3), 0); // renders the bottom-left part of the tile
		screen.render(x * 16 + 8, y * 16 + 8, 32, Color.get(rc1, level.dirtColor, rc2, rc3), 0); // renders the bottom-right part of the tile
	}

	/** Determines if entities can pass through the tile */
	public boolean mayPass(Level level, int x, int y, Entity e) {
		return false; // they can't.
	}

}
