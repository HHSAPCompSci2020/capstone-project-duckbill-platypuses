import java.awt.Image;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * The person class represents a person that can move left, right, up, and down.
 * 
 * @author Itai
 * @version 5/6
 */
public class Person {

	private int x;
	private int y;
	private PImage img;
	boolean shown;

	/**
	 * Initializes a person to an initial x coordinate, y coordinate, image, and
	 * visibility of the person.
	 * 
	 * @param x     X-coordinate of the person
	 * @param y     Y - coordinate of the person
	 * @param img   the image of the person
	 * @param shown decides the initial visibility of the person.
	 */
	public Person(int x, int y, PImage img, boolean shown) {
		this.x = x;
		this.y = y;
		this.img = img;
		this.shown = shown;
	}

	/**
	 * Moves the person x distance horizontally.
	 * 
	 * @param xDistance The change that will be done to the X-coordinate.
	 */
	public void moveX(int xDistance) {
		x = x + xDistance;
	}

	/**
	 * Moves the person y distance vertically.
	 * 
	 * @param yDistance The change that will be done to the X-coordinate.
	 */
	public void moveY(int yDistance) {
		y = y + yDistance;
	}

	/**
	 * Draws the Person to the given Processing PApplet, if shown is true.
	 * 
	 * @param marker The Processing PApplet on which to draw the Circle.
	 * @pre The Person will be drawn with attributes previously set on the given
	 *      PApplet.
	 */
	public void draw(PApplet marker) {

		if (shown) {
			marker.image(img, (float) x, (float) y);
		}

	}

	/**
	 * Hides the person, the draw method will not draw anything. If shown is already
	 * false, shown remains false.
	 * 
	 */
	public void hide() {
		shown = false;
	}

	/**
	 * Shows the person, the draw method will draw the person. If shown is already
	 * true, shown remains true.
	 * 
	 */
	public void show() {
		shown = true;
	}

}
