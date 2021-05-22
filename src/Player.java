import processing.core.PImage;

/**
 * The player class is a person, which moves through user interactions.
 * 
 * @author Ido Ofek
 * @version 05/06/2021
 */
public class Player extends Person {

	/**
	 * Initializes a player to an initial x coordinate, y coordinate, image, and
	 * visibility of the player.
	 * 
	 * @param x     x - coordinate of the player
	 * @param y     y - coordinate of the player
	 * @param img   Image of the player
	 * @param shown The visibility of the player
	 */
	public Player(int x, int y, PImage img, boolean shown) {
		super(x, y, img, shown);

	}

}
