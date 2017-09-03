package com.camasia.code.item.resource;

import com.camasia.code.entity.Player;
import com.camasia.code.gfx.Color;
import com.camasia.code.level.Level;
import com.camasia.code.level.tile.Tile;

public class Resource {
	public static Resource wood = new Resource("Wood", 1 + 4 * 32, Color.get(-1, 200, 531, 430));
	public static Resource stone = new Resource("Stone", 2 + 4 * 32, Color.get(-1, 111, 333, 555));
	public static Resource flower = new PlantableResource("Flower", 0 + 4 * 32, Color.get(-1, 10, 444, 330), Tile.flower, Tile.grass);
	public static Resource acorn = new PlantableResource("Acorn", 3 + 4 * 32, Color.get(-1, 100, 531, 320), Tile.treeSapling, Tile.grass);
	public static Resource dirt = new PlantableResource("Dirt", 2 + 4 * 32, Color.get(-1, 100, 322, 432), Tile.dirt, Tile.hole, Tile.water, Tile.lava);
	public static Resource sand = new PlantableResource("Sand", 2 + 4 * 32, Color.get(-1, 110, 440, 550), Tile.sand, Tile.grass, Tile.dirt);
	public static Resource cactusFlower = new PlantableResource("Cactus", 4 + 4 * 32, Color.get(-1, 10, 40, 50), Tile.cactusSapling, Tile.sand);
	public static Resource seeds = new PlantableResource("Seeds", 5 + 4 * 32, Color.get(-1, 10, 40, 50), Tile.wheat, Tile.farmland);
	public static Resource wheat = new Resource("Wheat", 6 + 4 * 32, Color.get(-1, 110, 330, 550));
	public static Resource bread = new FoodResource("Bread", 8 + 4 * 32, Color.get(-1, 110, 330, 550), 2, 5);
	public static Resource apple = new FoodResource("Apple", 9 + 4 * 32, Color.get(-1, 100, 300, 500), 1, 5);

	public static Resource coal = new Resource("COAL", 10 + 4 * 32, Color.get(-1, 000, 111, 111));
	public static Resource ironOre = new Resource("I.ORE", 10 + 4 * 32, Color.get(-1, 100, 322, 544));
	public static Resource goldOre = new Resource("G.ORE", 10 + 4 * 32, Color.get(-1, 110, 440, 553));
	public static Resource ironIngot = new Resource("IRON", 11 + 4 * 32, Color.get(-1, 100, 322, 544));
	public static Resource goldIngot = new Resource("GOLD", 11 + 4 * 32, Color.get(-1, 110, 330, 553));

	public static Resource slime = new Resource("SLIME", 10 + 4 * 32, Color.get(-1, 10, 30, 50));
	public static Resource glass = new Resource("glass", 12 + 4 * 32, Color.get(-1, 555, 555, 555));
	public static Resource cloth = new Resource("cloth", 1 + 4 * 32, Color.get(-1, 25, 252, 141));
	public static Resource cloud = new PlantableResource("cloud", 2 + 4 * 32, Color.get(-1, 222, 555, 444), Tile.cloud, Tile.infiniteFall);
	public static Resource gem = new Resource("gem", 13 + 4 * 32, Color.get(-1, 101, 404, 545));

	// public static Resource resource = new Resource("Name", x-sprite position + y-sprite position * 32, Color.get(-1,555,555,555))
	
	public final String name; // the name of the resource
	public final int sprite; // the sprite of the resource
	public final int color; // the color of the resource

	public Resource(String name, int sprite, int color) {
		/* if the name is longer than six characters, then throw an error.*/
		if (name.length() > 6) throw new RuntimeException("Name cannot be longer than six characters!");
		this.name = name; // assigns the name
		this.sprite = sprite; // assigns the sprite
		this.color = color; // assigns the color
	}

	/** Determines what happens when the resource is used on a certain tile */
	public boolean interactOn(Tile tile, Level level, int xt, int yt, Player player, int attackDir) {
		return false;
	}
}