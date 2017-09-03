package camasia.code.item.resource;

import camasia.code.entity.Player;
import camasia.code.level.Level;
import camasia.code.level.tile.Tile;

import java.util.Arrays;
import java.util.List;

public class PlantableResource extends Resource {
	private List<Tile> sourceTiles; // list of tiles it can be plated on
	private Tile targetTile; // what the source tile turns into when planted. (sapling/wheat seed)

	public PlantableResource(String name, int sprite, int color, Tile targetTile, Tile... sourceTiles1) {
		this(name, sprite, color, targetTile, Arrays.asList(sourceTiles1)); // assigns everything
	}

	public PlantableResource(String name, int sprite, int color, Tile targetTile, List<Tile> sourceTiles) {
		super(name, sprite, color); // assigns the name, sprite, and color.
		this.sourceTiles = sourceTiles; // assigns the source tiles
		this.targetTile = targetTile; // assigns the target tile
	}

	/** Determines what happens when the resource is used on a certain tile */
	public boolean interactOn(Tile tile, Level level, int xt, int yt, Player player, int attackDir) {
		if (sourceTiles.contains(tile)) { // if the sourceTiles contains the called tile...
			level.setTile(xt, yt, targetTile, 0); // sets the source tile into the targetTile
			return true;
		}
		return false;
	}
}
