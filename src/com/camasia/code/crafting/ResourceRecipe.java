package com.camasia.code.crafting;

import com.camasia.code.entity.Player;
import com.camasia.code.item.ResourceItem;
import com.camasia.code.item.resource.Resource;

public class ResourceRecipe extends Recipe {
	private Resource resource; //The resource used in this recipe

	/** Adds a recipe to craft a resource */
	public ResourceRecipe(Resource resource) {
		super(new ResourceItem(resource, 1)); //this goes through Recipe.java to be put on a list.
		this.resource = resource; //resource to be added
	}

	/** Adds the resource into your inventory */
	public void craft(Player player) {
		player.inventory.add(0, new ResourceItem(resource, 1)); //adds the resource
		}
}
