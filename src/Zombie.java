import java.awt.Rectangle;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * The zombie class is a person, that moves by following the player.
 * 
 * @author Itai Reingold Nutman
 * @version 05/06/2021
 */
public class Zombie extends Person {

	/**
	 * Initializes a zombie to an initial x coordinate, y coordinate, image, and
	 * visibility of the zombie.
	 * 
	 * @param x     X-coordinate of the zombie
	 * @param y     Y - coordinate of the zombie
	 * @param img   the image of the zombie
	 * @param shown decides the initial visibility of the zombie.
	 */
	public Zombie(int x, int y, PImage img, boolean shown) {
		super(x, y, img, shown);

	}

	/**
	 * Draws the zombie to the given Processing PApplet, if shown is true. Also
	 * calls the followPlayer method, so the zombie moves as intended.
	 * 
	 * @param marker The Processing PApplet on which to draw the zombie.
	 * @param p      the player which the zombie will follow.
	 * @pre The Person will be drawn with attributes previously set on the given
	 *      PApplet.
	 */
	public void draw(PApplet marker, Player p) {
		super.draw(marker);
		followPlayer(p);

	}

	/**
	 * Follows the player with every new draw call.
	 * 
	 * @param p the player which the zombie will follow.
	 */
	public void followPlayer(Player p) {
		moveX((p.getX() - getX()) / 300);
		moveY((p.getY() - getY()) / 300);

	}

	/**
	 * Makes the shown variable true
	 */
	public void makeShown() {
		setShown(true);
	}

	/**
	 * Makes the shown variable false
	 */
	public void makeHidden() {
		setShown(false);
	}

	/**
	 * returns the shown variable
	 * 
	 * @return the visibility of the zombie.
	 */
	public boolean isShown() {
		return getShown();
	}

	/**
	 * checks weather or not the zombie is touching a player
	 * 
	 * @param p the player that will be checked if touching.
	 * @return weather or not the zombie is touching a player.
	 */
	public boolean isTouching(Player p) {

		if (getShown()) {

			Rectangle playerR = new Rectangle((int) p.getX() + 32, (int) p.getY() + 32, (int) p.getWidth() / 2 + 8,
					(int) p.getHeight() / 2 + 8);
			Rectangle zombieR = new Rectangle((int) getX() + 32, (int) getY() + 32, (int) getWidth() / 2 + 8,
					(int) getHeight() / 2 + 8);
			if (playerR.intersects(zombieR)) {
				return true;

			}
		}

		return false;

	}

}
