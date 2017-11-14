package code.level.tile

import code.entity.Entity
import code.entity.ItemEntity
import code.entity.Player
import code.gfx.Color
import code.gfx.Inversion.NONE
import code.gfx.Screen
import code.gfx.Sprite0x4
import code.item.Item
import code.item.ResourceItem
import code.item.ToolItem
import code.item.ToolType
import code.item.resource.Resource
import code.level.Level
import code.sound.Sound

class DirtTile(id: Int): Tile(id) {//assigns the id
	
	override fun render(screen: Screen, level: Level, x: Int, y: Int) {
		val col = Color.get(level.dirtColor, level.dirtColor, level.dirtColor - 111, level.dirtColor - 111) // Colors of the dirt (more info in level.java)
		screen.apply {
			render(x * 16 + 0, y * 16 + 0, Sprite0x4(0, 0, NONE, col)) // renders the top-left part of the tile
			render(x * 16 + 8, y * 16 + 0, Sprite0x4(1, 0, NONE, col)) // renders the top-right part of the tile
			render(x * 16 + 0, y * 16 + 8, Sprite0x4(2, 0, NONE, col)) // renders the bottom-left part of the tile
			render(x * 16 + 8, y * 16 + 8, Sprite0x4(3, 0, NONE, col)) // renders the bottom-right part of the tile
		}
	}
	
	override fun interact(level: Level, xt: Int, yt: Int, player: Player, item: Item, attackDir: Int): Boolean {
		if (item is ToolItem) { // if the player's current item is a tool...
			if (item.type === ToolType.shovel) { // if the tool is a shovel...
				if (player.payStamina(4 - item.level)) { // if the player can pay the stamina...
					level.setTile(xt, yt, Tile.hole, 0) //sets the tile to a hole
					level.add(ItemEntity(ResourceItem(Resource.dirt), xt * 16 + random.nextInt(10) + 3, yt * 16 + random.nextInt(10) + 3)) // pops out a dirt resource
					// level += ItemEntity(ResourceItem(Resource.dirt), xt * 16 + random.nextInt(10) + 3, yt * 16 + random.nextInt(10) + 3)
					Sound.monsterHurt.play()// sound plays
					return true
				}
			}
			if (item.type === ToolType.hoe) { // if the tool is a hoe...
				if (player.payStamina(4 - item.level)) { // if the player can pay the stamina...
					level.setTile(xt, yt, Tile.farmland, 0) //sets the tile to a FarmTile
					Sound.monsterHurt.play() //sound plays
					return true
				}
			}
		}
		return false
	}
}

operator fun Level.plusAssign(entity: Entity) {
	this.add(entity)
}
