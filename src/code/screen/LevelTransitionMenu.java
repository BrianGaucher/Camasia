package code.screen;

import code.Game;
import code.gfx.Screen;

public class LevelTransitionMenu extends Menu {
	private int dir; // Direction that you are changing levels. (going up or down stairs)
	private int time = 0; // Time it spends on this menu

	public LevelTransitionMenu(int dir) {
		this.dir = dir; // Assigns the direction
	}

	public void tick() {
		time += 2; // Ticks up 2 times per tick
		if (time == 30) game.changeLevel(dir); // When time equals 30, it will change the level
		if (time == 60) game.setMenu(null); // When time equals 60, it will get out of this menu
	}
	
	public void render(Screen screen) {
		 for ( int x = 0; x < (Game.WIDTH / 3); x++ ) { // Loop however many times depending on the width (It's divided by 3 because the pixels are scaled up by 3)
			for (int y = 0; y < (Game.HEIGHT/3); y++) { // Loop however many times depending on the height (It's divided by 3 because the pixels are scaled up by 3)
				int dd = (y + x % 2 * 2 + x / 3) - time; // Used as part of the positioning.
				if (dd < 0 && dd > -30) {
					if (dir > 0)
						screen.render(x * 8, y * 8, 0, 0, 0); // If the direction is upwards then render the squares going up
					else
						screen.render(x * 8, screen.h - y * 8 - 8, 0, 0, 0); // If the direction is negative, then the squares will go down.
				}
			}
		}
	}
}
