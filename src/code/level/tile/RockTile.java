package code.level.tile;

import code.entity.Entity;
import code.entity.ItemEntity;
import code.entity.Mob;
import code.entity.Player;
import code.entity.particle.SmashParticle;
import code.entity.particle.TextParticle;
import code.gfx.Color;
import code.gfx.Screen;
import code.gfx.Sprite0x4;
import code.item.Item;
import code.item.ResourceItem;
import code.item.ToolItem;
import code.item.ToolType;
import code.item.resource.Resource;
import code.level.Level;

import static code.gfx.Inversion.NONE;
import static code.gfx.Inversion.ROTATE_180;

public class RockTile extends Tile {
	 Tile t = this; // this tile, (The reason why this is here is for HardRockTile.java)
	 int mainColor = 444; // main color of the rock
	 int darkColor = 111; // dark color of the rock
	 
	 public RockTile(int id) {
		  super( id ); // assigns the id
	 }
	 
	 //	 /**
//	  * Renders the dirt
//	  * @param screen
//	  * @param level
//	  * @param x
//	  * @param y
//	  */
	 @SuppressWarnings( "PointlessArithmeticExpression" )
	 public void render(Screen screen, Level level, int x, int y) {
		  
		  // color of the rock
		  int col = Color.get( mainColor, mainColor, mainColor - 111, mainColor - 111 );
		  // transitional color for the rock
		  int transitionColor = Color.get( darkColor, mainColor, mainColor + 111, level.dirtColor );
		  
		  boolean u = level.getTile( x, y - 1 ) == t; // sees if the tile above this is not a rock tile.
		  boolean d = level.getTile( x, y + 1 ) == t; // sees if the tile below this is not a rock tile.
		  boolean l = level.getTile( x - 1, y ) == t; // sees if the tile to the left this is not a rock tile.
		  boolean r = level.getTile( x + 1, y ) == t; // sees if the tile to the right this is not a rock tile.
		  
		  boolean ul = level.getTile( x - 1, y - 1 ) == t; // sees if the tile to the upper-left is not a rock tile.
		  boolean dl = level.getTile( x - 1, y + 1 ) == t; // sees if the tile to the lower-left is not a rock tile.
		  boolean ur = level.getTile( x + 1, y - 1 ) == t; // sees if the tile to the upper-right is not a rock tile.
		  boolean dr = level.getTile( x + 1, y + 1 ) == t; // sees if the tile to the lower-right is not a rock tile.
		  
		  Sprite0x4 sprite;
		  int up = y * 16;
		  int down = y * 16 + 8;
		  int left = x * 16;
		  int right = x * 16 + 8;
		  
		  //region HISTORY
/*
		  // Eight version
		  if ( !u ) { // If it's up
				if ( !l ) { // if it's up and left
					 if ( !ul ) { // if it's up  & left & up-left
						  sprite = new Sprite0x4( 0, 0, NONE, col );
						  // render a corner piece. (upper-left sprite)
					 } else { // If it's only up & left
						  sprite = new Sprite0x4( 7, 0, ROTATE_180, transitionColor );
					 }
				} else { // If it's only up
					 sprite = new Sprite0x4( 6, 1, ROTATE_180, transitionColor );
				}
		  } else if ( !l ) { // If it's only left
				sprite = new Sprite0x4( 5, 2, ROTATE_180, transitionColor );
		  } else { // No contact at all
				sprite = new Sprite0x4( 6, 2, ROTATE_180, transitionColor );
		  }
		  screen.render( left, up, sprite ); // else render an end piece.
*/

/*
		  // Seventh method
		  if ( !d ) { // If it's up
				if ( !r ) { // if it's up and left
					 if ( !dr ) { // if it's up  & left & up-left
						  sprite = new Sprite0x3( 0, 0, NONE );
						  colour = col;
						  // render a corner piece. (up-left sprite)
					 } else { // If it's only up & left
						  sprite = new Sprite0x3( 7, 0, ROTATE_180 );
						  colour = transitionColor;
					 }
				} else { // If it's only up
					 sprite = new Sprite0x3( 4, 1, ROTATE_180 );
					 colour = transitionColor;
				}
		  } else if ( !r ) { // If it's only left
				sprite = new Sprite0x3( 5, 2, ROTATE_180 );
				colour = transitionColor;
		  } else { // No contact at all
				sprite = new Sprite0x3( 6, 2, ROTATE_180 );
				colour = transitionColor;
		  }
		  screen.render( right, down, sprite, colour ); // else render an end piece.
*/

/*
		  // Sixth method
		  if ( !d ) { // If it's up
				if ( !r ) { // if it's up and left
					 if ( !dr ) { // if it's up  & left & up-left
						  sprite = new Sprite0x3( 0, 0, Inversion.NONE );
						  colour = col;
						  // render a corner piece. (up-left sprite)
					 } else { // If it's only up & left
						  sprite = new Sprite0x3( 7, 0, Inversion.ROTATE_180 );
						  colour = transitionColor;
					 }
				} else { // If it's only up
					 sprite = new Sprite0x3( 4, 1, Inversion.ROTATE_180 );
					 colour = transitionColor;
				}
		  } else if ( !r ) { // If it's only left
				sprite = new Sprite0x3( 5, 2, Inversion.ROTATE_180 );
				colour = transitionColor;
		  } else { // No contact at all
				sprite = new Sprite0x3( 6, 2, Inversion.ROTATE_180 );
				colour = transitionColor;
		  }
		  screen.render( right, down, sprite, colour ); // else render an end piece.
*/

/*
		  // Fifth method
		  if ( !d ) { // If it's up
				if ( !r ) { // if it's up and left
					 if ( !dr ) { // if it's up  & left & up-left
						  sprite = new Sprite0x3( 0, 0, 0 );
						  colour = col;
						  // render a corner piece. (up-left sprite)
					 } else { // If it's only up & left
						  sprite = new Sprite0x3( 7, 0, 3 );
						  colour = transitionColor;
					 }
				} else { // If it's only up
					 sprite = new Sprite0x3( 4, 1, 3 );
					 colour = transitionColor;
				}
		  } else if ( !r ) { // If it's only left
				sprite = new Sprite0x3( 5, 2, 3 );
				colour = transitionColor;
		  } else { // No contact at all
				sprite = new Sprite0x3( 6, 2, 3 );
				colour = transitionColor;
		  }
		  screen.render( right, down, sprite, colour ); // else render an end piece.
*/

/*
		  // Fourth method
		  if ( !d ) { // If it's up
				if ( !r ) { // if it's up and left
					 if ( !dr ) { // if it's up  & left & up-left
						  sprite = new Sprite0x2( 0, 0 );
						  colour = col;
		  				  bit = 0;
						  // render a corner piece. (up-left sprite)
					 } else { // If it's only up & left
						  sprite = new Sprite0x2( 7, 0 );
						  colour = transitionColor;
		  				  bit = 3;
					 }
				} else { // If it's only up
					 sprite = new Sprite0x2( 4, 1 );
					 colour = transitionColor;
		  			 bit = 3;
				}
		  } else if ( !r ) { // If it's only left
				sprite = new Sprite0x2( 5, 2 );
				colour = transitionColor;
		  		bit = 3;
		  } else { // No contact at all
				sprite = new Sprite0x2( 6, 2 );
				colour = transitionColor;
		  		bit = 3;
		  }
		  screen.render( right, down, sprite, colour ); // else render an end piece.
*/

/*
		  // Third method
		  if ( !u ) { // If it's up
				if ( !l ) { // if it's up and left
					 if ( !ul )// if it's up  & left & up-left
						  screen.render( column * 16 + 0, row * 16 + 0, new Sprite0x1( 0, 0 ), col, 0 );  // render a corner piece. (upper-left sprite)
					 else // If it's only up & left
						  screen.render( column * 16 + 0, row * 16 + 0, new Sprite0x1( 7, 0 ), transitionColor, 3 ); // render a flat tile
				} else // If it's only up
					 screen.render( column * 16 + 0, row * 16 + 0, new Sprite0x1( 6, 1 ), transitionColor, 3 ); // else render an end piece.
		  } else {// if it has no up contact
				if ( !l )// If it's only left
					 screen.render( column * 16 + 0, row * 16 + 0, new Sprite0x1( 5, 2 ), transitionColor, 3 ); // else render an end piece.
				else // No contact at all
					 screen.render( column * 16 + 0, row * 16 + 0, new Sprite0x1( 6, 2 ), transitionColor, 3 ); // else render an end piece.
		  }
*/

/*
		  // Second method
		  if ( !u ) { // If it's up
				if ( !l ) { // if it's up and left
					 if ( !ul )// if it's up  & left & up-left
						  screen.render( column * 16 + 0, row * 16 + 0, 0, col, 0 );  // render a corner piece. (upper-left sprite)
					 else // If it's only up & left
						  screen.render( column * 16 + 0, row * 16 + 0, 7, transitionColor, 3 ); // render a flat tile
				} else // If it's only up
					 screen.render( column * 16 + 0, row * 16 + 0, 6 + 1 * 32, transitionColor, 3 ); // else render an end piece.
		  } else {// if it has no up contact
				if ( !l )// If it's only left
					 screen.render( column * 16 + 0, row * 16 + 0, 5 + 2 * 32, transitionColor, 3 ); // else render an end piece.
				else // No contact at all
					 screen.render( column * 16 + 0, row * 16 + 0, 6 + 2 * 32, transitionColor, 3 ); // else render an end piece.
		  }
*/

/*
		  First method
		  	if ( !u && !l ) { // if there is a rock tile above, or to the left of this then...
				if ( !ul ) // if there is a rock tile to the upper-left of this one then...
					 screen.render( column * 16 + 0, row * 16 + 0, 0, col, 0 );  // render a corner piece. (upper-left sprite)
				else
					 screen.render( column * 16 + 0, row * 16 + 0, 7 + 0 * 32, transitionColor, 3 ); // render a flat tile
		  } else
				screen.render( column * 16 + 0, row * 16 + 0, (l? 6: 5) + (u? 2: 1) * 32, transitionColor, 3 ); // else render an end piece.
*/
		  //endregion
		  
		  //region render up-left
		  if ( u ) { // If it's up
				if ( l ) { // if it's up and left
					 if ( ul ) { // if it's up  & left & up-left
						  sprite = new Sprite0x4( 0, 0, NONE, col );
						  // render a corner piece. (upper-left sprite)
					 } else { // If it's only up & left
						  sprite = new Sprite0x4( 7, 0, ROTATE_180, transitionColor );
					 }
				} else { // If it's only up
					 sprite = new Sprite0x4( 6, 1, ROTATE_180, transitionColor );
				}
		  } else if ( l ) { // If it's only left
				sprite = new Sprite0x4( 5, 2, ROTATE_180, transitionColor );
		  } else { // No contact at all
				sprite = new Sprite0x4( 6, 2, ROTATE_180, transitionColor );
		  }
		  screen.render( left, up, sprite ); // else render an end piece.
		  //endregion
		  
		  //region render up-right
		  if ( u ) { // If it's up
				if ( !r ) { // if it's up and right
					 if ( ur ) { // if it's u  & r & up-right
						  sprite = new Sprite0x4( 1, 0, NONE, col );
						  // render a corner piece. (up-right sprite)
					 } else { // If it's only up & right
						  sprite = new Sprite0x4( 8, 0, ROTATE_180, transitionColor );
					 }
				} else { // If it's only up
					 sprite = new Sprite0x4( 4, 1, ROTATE_180, transitionColor );
				}
		  } else if ( r ) { // If it's only right
				sprite = new Sprite0x4( 5, 2, ROTATE_180, transitionColor );
		  } else { // No contact at all
				sprite = new Sprite0x4( 4, 2, ROTATE_180, transitionColor );
		  }
		  screen.render( right, up, sprite ); // else render an end piece.
		  //endregion
		  
		  //region render down-left
		  if ( d ) { // If it's down
				if ( l ) { // if it's down and left
					 if ( dl ) { // if it's down  & left & down-left
						  sprite = new Sprite0x4( 2, 0, NONE, col );
						  // render a corner piece. (down-left sprite)
					 } else { // If it's only d & l
						  sprite = new Sprite0x4( 7, 1, ROTATE_180, transitionColor );
					 }
				} else { // If it's only down
					 sprite = new Sprite0x4( 6, 1, ROTATE_180, transitionColor );
				}
		  } else if ( l ) { // If it's only left
				sprite = new Sprite0x4( 5, 0, ROTATE_180, transitionColor );
		  } else { // No contact at all
				sprite = new Sprite0x4( 6, 0, ROTATE_180, transitionColor );
		  }
		  screen.render( left, down, sprite ); // else render an end piece.
		  //endregion
		  
		  //region down-right
		  if ( d ) { // If it's down
				if ( r ) { // if it's down and right
					 if ( dr ) { // if it's down  & right & down-right
						  sprite = new Sprite0x4( 3, 0, NONE, col );
						  // render a corner piece. (down-right sprite)
					 } else { // If it's only down & right
						  sprite = new Sprite0x4( 8, 1, ROTATE_180, transitionColor );
					 }
				} else { // If it's only down
					 sprite = new Sprite0x4( 4, 1, ROTATE_180, transitionColor );
				}
		  } else if ( r ) { // If it's only right
				sprite = new Sprite0x4( 5, 0, ROTATE_180, transitionColor );
		  } else { // No contact at all
				sprite = new Sprite0x4( 4, 0, ROTATE_180, transitionColor );
		  }
		  screen.render( right, down, sprite ); // else render an end piece.
		  //endregion
	 }
	 
	 /**
	  * Determines of the player can pass through this tile
	  */
	 public boolean mayPass(Level level, int x, int y, Entity e) {
		  return false; // the player cannot pass through it.
	 }
	 
	 /**
	  * What happens when you punch the tile
	  */
	 public void hurt(Level level, int x, int y, Mob source, int dmg, int attackDir) {
		  hurt( level, x, y, dmg ); // do a punch amount of damage to it (1-3)
	 }
	 
	 /**
	  * What happens when you use an item in this tile (like a pick-axe)
	  */
	 public boolean interact(Level level, int xt, int yt, Player player, Item item, int attackDir) {
		  if ( item instanceof ToolItem ) { //if the item is a tool
				ToolItem tool = (ToolItem) item; // converts the Item object to a ToolItem object
				if ( tool.type == ToolType.pickaxe ) { // if the type of tool is a pickaxe...
					 if ( player.payStamina( 4 - tool.level ) ) { // if the player can pay the stamina...
						  hurt( level, xt, yt, random
									 .nextInt( 10 ) + (tool.level) * 5 + 10 ); // hurts the tile, damage based on the tool's level.
						  return true;
					 }
				}
		  }
		  return false;
	 }
	 
	 public void hurt(Level level, int x, int y, int dmg) {
		  int damage = level.getData( x, y ) + dmg; // adds the damage.
		  level.add( new SmashParticle( x * 16 + 8, y * 16 + 8 ) ); // creates a smash particle
		  level.add( new TextParticle( "" + dmg, x * 16 + 8, y * 16 + 8, Color
					 .get( -1, 500, 500, 500 ) ) ); // adds text telling how much damage you did.
		  if ( damage >= 50 ) { // if the damage is larger or equal to 50 then...
				int count = random.nextInt( 4 ) + 1; // count is between 1 to 4
				for ( int i = 0; i < count; i++ ) { //loops through the count
				/* adds stone to the world */
					 level.add( new ItemEntity( new ResourceItem( Resource.stone ), x * 16 + random
								.nextInt( 10 ) + 3, y * 16 + random.nextInt( 10 ) + 3 ) );
				}
				count = random.nextInt( 2 ); // count is between 0 and 1
				for ( int i = 0; i < count; i++ ) { // loops through the count
				/* adds coal to the world */
					 level.add( new ItemEntity( new ResourceItem( Resource.coal ), x * 16 + random
								.nextInt( 10 ) + 3, y * 16 + random.nextInt( 10 ) + 3 ) );
				}
				level.setTile( x, y, Tile.dirt, 0 ); // sets the tile to dirt
		  } else {
				level.setData( x, y, damage ); // else just set the damage data.
		  }
	 }
	 
	 /**
	  * Update method
	  */
	 public void tick(Level level, int xt, int yt) {
		  int damage = level.getData( xt, yt ); // gets the current damage of the tile
		  if ( damage > 0 )
				level.setData( xt, yt, damage - 1 ); // if the damage is larger than 0, then minus the current damage by 1.
	 }
}
