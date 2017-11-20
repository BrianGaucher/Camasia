package code;

import java.applet.Applet;
import java.awt.*;

public class GameApplet extends Applet {
	 private static final long serialVersionUID = 1L; //this is here because eclipse will yell a warning at us if we don't have it :\

	/* This class is called when it is loaded in a website (like playminicraft.com) */
	 
	 private Game game = new Game( ); // Creates a new Game object.
	 
	 /**
	  * Initialization step, called when the applet is started
	  */
	 public void init( ) {
		  setLayout( new BorderLayout( ) ); // creates a layout for the game to use.
		  add( game, BorderLayout.CENTER ); // Adds the game (since it's a canvas) to the center of the applet.
	 }
	 
	 /**
	  * starts the game on the applet
	  */
	 public void start( ) {
		  game.start( );
	 }
	 
	 /**
	  * stops the game on the applet
	  */
	 public void stop( ) {
		  game.stop( );
	 }
}