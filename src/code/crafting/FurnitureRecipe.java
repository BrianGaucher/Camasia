package code.crafting;

import code.entity.Furniture;
import code.entity.Player;
import code.item.FurnitureItem;

public class FurnitureRecipe extends Recipe {
	private Class<? extends Furniture> clazz; // class of the furniture

	public FurnitureRecipe(Class<? extends Furniture> clazz) throws InstantiationException, IllegalAccessException {
		super(new FurnitureItem(clazz.newInstance())); // assigns the furniture by class
		this.clazz = clazz; // assigns the class
	}

	public void craft(Player player) {
		try {
			player.inventory.add(0, new FurnitureItem(clazz.newInstance())); // crafts the furniture item into the player's inventory
		} catch (Exception e) {
			throw new RuntimeException(e); // else it will throw an error
		}
	}
}
