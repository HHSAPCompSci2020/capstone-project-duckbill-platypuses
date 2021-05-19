import processing.core.PApplet;
import processing.core.PImage;

/**
 * The player class is a person, which moves through user interactions.
 * 
 * @author Ido Ofek
 * @version 05/06/2021
 */
public class Player extends Person {

	private int mX; 
	private int mY; 
	
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
		mX = 0;
		mY = 0;

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

		setX(getX() + mX);
		setY(getY() + mY);
		marker.keyReleased();
		if (marker.key == PApplet.CODED && marker.keyCode == PApplet.UP) {
			
				mY = 0;
		
		} else if (marker.key == PApplet.CODED && marker.keyCode == PApplet.DOWN) {
			
				mY = 0;
			
		}
		if (marker.key == PApplet.CODED && marker.keyCode == PApplet.LEFT) {
			
				mX = 0;
			

		} else if (marker.key == PApplet.CODED && marker.keyCode == PApplet.RIGHT) {
			
				mX = 0;
		
		}
		if (marker.keyPressed) {
			if (marker.key == PApplet.CODED && marker.keyCode == PApplet.UP) {
				if ((super.getY() > 2)) {
					//super.setY(super.getY() - 2);
					mY -= 2;
				}
			} else if (marker.key == PApplet.CODED && marker.keyCode == PApplet.DOWN) {
				if (((super.getY() + super.getHeight()) < 600)) {
					//super.setY(super.getY() + 2);
					mY += 2;
				}
			}
			if (marker.key == PApplet.CODED && marker.keyCode == PApplet.LEFT) {
				if ((super.getX() > -30)) {
					//super.setX(super.getX() - 2);
					mX -= 2;
				}

			} else if (marker.key == PApplet.CODED && marker.keyCode == PApplet.RIGHT) {
				if (((super.getX() + super.getWidth()) < 830)) {
					//super.setX(super.getX() + 2);
					mX += 2;
				}
			}
		}
		
	
//		if (marker.key) {
//			if (marker.key == PApplet.CODED && marker.keyCode == PApplet.UP) {
//				if ((super.getY() > 2)) {
//					//super.setY(super.getY() - 2);
//					mY -= 2;
//				}
//			} else if (marker.key == PApplet.CODED && marker.keyCode == PApplet.DOWN) {
//				if (((super.getY() + super.getHeight()) < 600)) {
//					//super.setY(super.getY() + 2);
//					mY += 2;
//				}
//			}
//			if (marker.key == PApplet.CODED && marker.keyCode == PApplet.LEFT) {
//				if ((super.getX() > -30)) {
//					//super.setX(super.getX() - 2);
//					mX -= 2;
//				}
//
//			} else if (marker.key == PApplet.CODED && marker.keyCode == PApplet.RIGHT) {
//				if (((super.getX() + super.getWidth()) < 830)) {
//					//super.setX(super.getX() + 2);
//					mX += 2;
//				}
//			}
//		}
	}
}
