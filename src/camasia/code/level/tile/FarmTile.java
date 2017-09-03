package camasia.code.level.tile;

import camasia.code.entity.Entity;
import camasia.code.entity.Player;
import camasia.code.gfx.Color;
import camasia.code.gfx.Screen;
import camasia.code.item.Item;
import camasia.code.item.ToolItem;
import camasia.code.item.ToolType;
import camasia.code.level.Level;

public class FarmTile extends Tile {
	public FarmTile(int id) {
		super(id); //assigns the id
	}

	public void render(Screen screen, Level level, int x, int y) {
		int col = Color.get(level.dirtColor - 121, level.dirtColor - 11, level.dirtColor, level.dirtColor + 111); // gives the tile color based on level.dirtColor
		screen.render(x * 16 + 0, y * 16 + 0, 2 + 32, col, 1); // renders the top-left tile
		screen.render(x * 16 + 8, y * 16 + 0, 2 + 32, col, 0); // renders the top-right tile
		screen.render(x * 16 + 0, y * 16 + 8, 2 + 32, col, 0); // renders bottom-left tile
		screen.render(x * 16 + 8, y * 16 + 8, 2 + 32, col, 1); // renders the bottom-right
	}

	/* What happens when you use a tool on this tile */
	public boolean interact(Level level, int xt, int yt, Player player, Item item, int attackDir) {
		 if ( item instanceof ToolItem ) { // if the item happens to be a tool...
			ToolItem tool = (ToolItem) item; // converts the Item object to a ToolItem object
			if (tool.type == ToolType.shovel) { // if the type of the tool is a shovel...
				if (player.payStamina(4 - tool.level)) { // If the player can pay the stamina...
					level.setTile(xt, yt, Tile.dirt, 0); // sets the tile into a dirt tile
					return true;
				}
			}
		}
		return false;
	}

	/** Update method, updates (ticks) 60 times a second */
	public void tick(Level level, int xt, int yt) {
		int age = level.getData(xt, yt); // gets the current age of the tile
		if (age < 5) level.setData(xt, yt, age + 1); // if the age is under 5, then adds 1 to the age
	}

	/** What happens when you step on the tile */
	public void steppedOn(Level level, int xt, int yt, Entity entity) {
		
		if (random.nextInt(60) != 0) return; // if a random number between 0 and 59 does NOT equal 0, then skip the rest of this code
		if (level.getData(xt, yt) < 5) return; // if the age of this tile is less than 5, then skip the rest of this code
		//if (entity instanceof Player) // uncommented this bit if you only want the player to trample crops.
		level.setTile(xt, yt, Tile.dirt, 0); // sets the tile to dirt.
		
	}
}
