import processing.core.PApplet;
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

	/**
	 * Draws the player to the given Processing PApplet.
	 * 
	 * @param marker The Processing PApplet on which to draw the player.
	 * @pre The player will be drawn with attributes previously set on the given
	 *      PApplet.
	 */
	public void draw(PApplet marker) {
		super.draw(marker);

		if (marker.keyPressed) {
			if (marker.key == PApplet.CODED && marker.keyCode == PApplet.UP) {
				if ((super.getY() > 2)) {
					super.setY(super.getY() - 2);
				}
			} else if (marker.key == PApplet.CODED && marker.keyCode == PApplet.DOWN) {
				if (((super.getY() + super.getHeight()) < 600)) {
					super.setY(super.getY() + 2);
				}
			}
			if (marker.key == PApplet.CODED && marker.keyCode == PApplet.LEFT) {
				if ((super.getX() > -30)) {
					super.setX(super.getX() - 2);
				}

			} else if (marker.key == PApplet.CODED && marker.keyCode == PApplet.RIGHT) {
				if (((super.getX() + super.getWidth()) < 830)) {
					super.setX(super.getX() + 2);
				}
			}
		}
	}
}
