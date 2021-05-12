import java.awt.Image;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * The person class represents a person that can move left, right, up, and down.
 * 
 * @author Itai Reingold Nutman
 * @version 05/06/2021
 */
public class Person {

	private double x;
	private double y;
	private PImage img;
	private boolean shown;
	private double width;
	private double height;

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
		width = 150;
		height = 150;
		
	}

	/**
	 * Moves the person x distance horizontally.
	 * 
	 * @param xDistance The change that will be done to the X-coordinate.
	 */
	public void moveX(double xDistance) {
		x = x + xDistance;
	}

	/**
	 * Moves the person y distance vertically.
	 * 
	 * @param yDistance The change that will be done to the X-coordinate.
	 */
	public void moveY(double yDistance) {
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
			marker.image(img, (float) x, (float) y, (float)width, (float) height);
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
	

	/**
	 * Returns the x coordinate of the person
	 * 
	 * @return x-coordinate of the person
	 */
	public double getX() {
		return x;
	}
	

	/**
	 * Returns the y coordinate of the person
	 * 
	 * @return y-coordinate of the person
	 */
	public double getY() {
		return y;
	}
	
	/**
	 * Returns the y coordinate of the person
	 * @param xIn - x to change to
	 */
	public void setX(double xIn) {
		 x = xIn;
	}
	

	/**
	 * set the y coordinate of the person
	 * @param yIn - y to change to
	 */
	public void setY(double yIn) {
		 y = yIn;
	}
	
	/**
	 * Returns the width of the person
	 * 
	 * @return width of the person
	 */
	public double getWidth() {
		return width;
	}
	

	/**
	 * Returns the height  of the person
	 * 
	 * @return height of the person
	 */
	public double getHeight() {
		return height;
	}
	
	public void setShown(boolean t) {
		shown = t;
	}
	
	
	public boolean getShown() {
		return shown;
	}
	
	
	

}
