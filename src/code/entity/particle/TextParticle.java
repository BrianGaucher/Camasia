package code.entity.particle;

import code.entity.Entity;
import code.gfx.Color;
import code.gfx.Font;
import code.gfx.Screen;

public class TextParticle extends Entity {
	 public double xa, ya, za; // x,y,z acceleration
	 public double xx, yy, zz; // x,y,z coordinates
	private String msg; // Message of the text particle
	private int col; // Color of the text particle
	private int time = 0; // time that the particle has been alive

	public TextParticle(String msg, int x, int y, int col) {
		this.msg = msg; // assigns the message
		this.x = x; // assigns the x coordinate
		this.y = y; // assigns the y coordinate
		this.col = col; // assigns the color
		xx = x; // assigns the xx coordinate from x
		yy = y; // assigns the yy coordinate from y
		zz = 2; // assigns the zz coordinate
		xa = random.nextGaussian() * 0.3; // assigns the x acceleration
		ya = random.nextGaussian() * 0.2; // assigns the y acceleration
		za = random.nextFloat() * 0.7 + 2; // assigns the z acceleration
	}

	/** Update method, 60 updates (ticks) per second */
	public void tick() {
		time++; // increases time
		if (time > 60) { // if time is over 60 (1 second) then...
			remove(); // remove this!
		}
		xx += xa; // xx moves with xa acceleration
		yy += ya; // yy moves with ya acceleration
		zz += za; // zz moves with za acceleration
		if (zz < 0) { // if zz is bigger than 0
			zz = 0; // z = 0
			za *= -0.5; // za multiplies itself by -0.5
			xa *= 0.6; // xa multiplies itself by 0.6
			ya *= 0.6; // ya multiplies itself by 0.6
		}
		za -= 0.15; // za minuses by 0.15 every tick
		x = (int) xx; // the x coordinate is xx
		y = (int) yy; // the y coordinate is yy
	}

	public void render(Screen screen) {
		Font.draw(msg, screen, x - msg.length() * 4 + 1, y - (int) (zz) + 1, Color.get(-1, 0, 0, 0)); //renders the backdrop
		Font.draw(msg, screen, x - msg.length() * 4, y - (int) (zz), col); // renders the text
	}

}
