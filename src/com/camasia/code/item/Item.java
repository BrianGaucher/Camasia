package com.camasia.code.item;

import com.camasia.code.entity.Entity;
import com.camasia.code.entity.ItemEntity;
import com.camasia.code.entity.Player;
import com.camasia.code.gfx.Screen;
import com.camasia.code.level.Level;
import com.camasia.code.level.tile.Tile;
import com.camasia.code.screen.ListItem;

public class Item implements ListItem {
	
	/* Note: Most of the stuff in the class is expanded upon in ResourceItem/PowerGloveItem/FurnitureItem/etc */
	
	/** Gets the color of the item */
	public int getColor() {
		return 0;
	}

	/** Gets the sprite of the item */
	public int getSprite() {
		return 0;
	}

	/** What happens when you pick up the item off the ground */
	public void onTake(ItemEntity itemEntity) {
	}

	/** Renders an item (sprite & name) in an inventory */
	public void renderInventory(Screen screen, int x, int y) {
	}

	/** Determines what happens when the player interacts with a entity */
	public boolean interact(Player player, Entity entity, int attackDir) {
		return false;
	}

	/** Renders the icon of the Item */
	public void renderIcon(Screen screen, int x, int y) {
	}

	/** Determines what happens when you use a item in a tile */
	public boolean interactOn(Tile tile, Level level, int xt, int yt, Player player, int attackDir) {
		return false;
	}
	
	/** Returns if the item is depleted or not */
	public boolean isDepleted() {
		return false;
	}

	/** Returns if the item can attack mobs or not */
	public boolean canAttack() {
		return false;
	}

	/** Gets the attack bonus from an item/tool (sword/axe) */
	public int getAttackDamageBonus(Entity e) {
		return 0;
	}

	/** Gets the name of the tool */
	public String getName() {
		return "";
	}

	/** Sees if an item matches another item */
	public boolean matches(Item item) {
		return item.getClass() == getClass();
	}
}