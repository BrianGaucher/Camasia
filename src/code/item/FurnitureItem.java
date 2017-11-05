package code.item;

import code.entity.Furniture;
import code.entity.ItemEntity;
import code.entity.Player;
import code.gfx.Color;
import code.gfx.Font;
import code.gfx.Screen;
import code.level.Level;
import code.level.tile.Tile;

public class FurnitureItem extends Item {
	public Furniture furniture; // the furniture of this item
	public boolean placed = false; // value if the furniture has been placed or not.

	public FurnitureItem(Furniture furniture) {
		this.furniture = furniture; // Assigns the furniture to the item
	}

	/** Gets the color of the furniture */
	public int getColor() {
		return furniture.col;
	}

	/** Gets the sprite of the furniture */
	public int getSprite() {
		return furniture.sprite + 10 * 32;
	}

	/** Renders the icon used for the furniture */
	public void renderIcon(Screen screen, int x, int y) {
		screen.render(x, y, getSprite(), getColor(), 0); // renders the icon
	}

	/** Renders the icon, and name of the furniture */
	public void renderInventory(Screen screen, int x, int y) {
		screen.render(x, y, getSprite(), getColor(), 0); // renders the icon
		Font.draw(furniture.name, screen, x + 8, y, Color.get(-1, 555, 555, 555)); // draws the name of the furniture
	}

	/** What happens when you pick up the item off the ground (Not with the power glove) */
	public void onTake(ItemEntity itemEntity) {
	}

	/** Determines if you can attack enemies with furniture (you can't) */
	public boolean canAttack() {
		return false;
	}

	/** What happens when you press the "Attack" key with the furniture in your hands */
	public boolean interactOn(Tile tile, Level level, int xt, int yt, Player player, int attackDir) {
		if (tile.mayPass(level, xt, yt, furniture)) { // If the furniture can go on the tile
			furniture.x = xt * 16 + 8; // Placed furniture's X position
			furniture.y = yt * 16 + 8; // Placed furniture's Y position
			level.add(furniture); // adds the furniture to the world
			placed = true; // the value becomes true, which removes it from the player's active item
			return true;
		}
		return false;
	}

	/** Removes this item from the player's active item slot when depleted is true */
	public boolean isDepleted() {
		return placed;
	}
	
	/** Gets the name of the furniture */
	public String getName() {
		return furniture.name;
	}
}