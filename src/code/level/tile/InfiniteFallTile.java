package code.level.tile;

import code.entity.AirWizard;
import code.entity.Entity;
import code.gfx.Screen;
import code.level.Level;

public class InfiniteFallTile extends Tile {
	
	/* This will be easy :D */
	
	public InfiniteFallTile(int id) {
		super(id); // assigns the id
	}

	/** Infinite fall tile doesn't render anything! */
	public void render(Screen screen, Level level, int x, int y) {
	}

	/** Update method, updates (ticks) 60 times a second */
	public void tick(Level level, int xt, int yt) {
	}

	/** Determines if an entity can pass through this tile */
	public boolean mayPass(Level level, int x, int y, Entity e) {
		 return e instanceof AirWizard;
	}
}
