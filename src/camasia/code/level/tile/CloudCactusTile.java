package camasia.code.level.tile;

import camasia.code.entity.AirWizard;
import camasia.code.entity.Entity;
import camasia.code.entity.Mob;
import camasia.code.entity.Player;
import camasia.code.entity.particle.SmashParticle;
import camasia.code.entity.particle.TextParticle;
import camasia.code.gfx.Color;
import camasia.code.gfx.Screen;
import camasia.code.item.Item;
import camasia.code.item.ToolItem;
import camasia.code.item.ToolType;
import camasia.code.level.Level;

public class CloudCactusTile extends Tile {
	public CloudCactusTile(int id) {
		super(id); //assigns the id
	} 

	public void render(Screen screen, Level level, int x, int y) {
		int color = Color.get(444, 111, 333, 555);  // colors of the cloud cactus
		screen.render(x * 16 + 0, y * 16 + 0, 17 + 1 * 32, color, 0); // renders the top-left part of the cloud cactus
		screen.render(x * 16 + 8, y * 16 + 0, 18 + 1 * 32, color, 0); // renders the top-right part of the cloud cactus
		screen.render(x * 16 + 0, y * 16 + 8, 17 + 2 * 32, color, 0); // renders the bottom-left part of the cloud cactus
		screen.render(x * 16 + 8, y * 16 + 8, 18 + 2 * 32, color, 0); // renders the bottom-right part of the cloud cactus
	}

	/* Determines what can pass this tile */
	public boolean mayPass(Level level, int x, int y, Entity e) {
		if (e instanceof AirWizard) return true; // If the entity is the Air Wizard, then it can pass right through.
		return false;
	}

	public void hurt(Level level, int x, int y, Mob source, int dmg, int attackDir) {
		hurt(level, x, y, 0); // If you punch it, it will do 0 damage
	}

	public boolean interact(Level level, int xt, int yt, Player player, Item item, int attackDir) {
		if (item instanceof ToolItem) { // If the item the player is holding is a tool...
			ToolItem tool = (ToolItem) item; // makes a ToolItem conversion of item.
			if (tool.type == ToolType.pickaxe) { // if the tool happens to be a pickaxe...
				if (player.payStamina(6 - tool.level)) { // if the player can pay the stamina...
					hurt(level, xt, yt, 1); // Do 1 damage to the cloud cactus (call the method below this one)
					return true;
				}
			}
		}
		return false;
	}

	/** This hurt method is special, called from the interact method above. */
	public void hurt(Level level, int x, int y, int dmg) {
		int damage = level.getData(x, y) + 1; // Adds the damage to the tile's data
		level.add(new SmashParticle(x * 16 + 8, y * 16 + 8)); // Adds a smash particle
		level.add(new TextParticle("" + dmg, x * 16 + 8, y * 16 + 8, Color.get(-1, 500, 500, 500))); // Adds text of how much damage you've done.
		if (dmg > 0) { //if the damage you did is over 0...
			if (damage >= 10) { //if the current damage the cloud cactus has is equal to or larger than 0...
				level.setTile(x, y, Tile.cloud, 0);// set the tile to cloud (destroys the cloud cactus tile)
			} else {
				level.setData(x, y, damage);// else just do normal damage to it
			}
		}
	}

	public void bumpedInto(Level level, int x, int y, Entity entity) {
		if (entity instanceof AirWizard) return; // The AirWizard will not get hurt by this
		entity.hurt(this, x, y, 3); // 3 damage will be done to anyone who bumps into this (except the air-wizard)
	}
}