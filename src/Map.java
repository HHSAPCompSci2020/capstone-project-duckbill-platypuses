import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * The map class represents the map of the school, which the user
 * will navigate through. 
 *  * 
 * @author Ophir Peleg
 * @version 05/06/2021
 */
public class Map {

	private double width;
	private double height;
	private PImage mapImage;
	private ArrayList<Point> doorLocations; //make up locations that make sense
	
	/**
	 * Constructs a Map object.
	 * 
	 * @param image image of the map, PImage.
	 * @post mapImage = image
	 * @post width = 800
	 * @post height = 600
	 * @post doorLocations is an arrayList of Points that holds
	 *       five points, which are the locations of the doors in
	 *       the map.
	 */
	
	public Map(PImage image) {
		mapImage = image;
		width = 800;
		height = 600;
		doorLocations = new ArrayList<Point>();
		doorLocations.add(new Point(160, -5));
		doorLocations.add(new Point(550, -5));
		doorLocations.add(new Point(170, (int)(height - 5)));
		doorLocations.add(new Point(600, (int)(height - 5)));
		doorLocations.add(new Point((int)(width - 20), (int)(277)));
	}
	
	/**
	 * Draws the map, which is an image located in
	 * the images folder.
	 * 
	 * @param marker graphics used to draw the line, PApplet
	 */
	
	public void draw(PApplet marker) {
		marker.image(mapImage,0,0,(float)width,(float)height);
	}

	/**
	 * Returns an ArrayList of Points for the doors'
	 * locations on the map.
	 * 
	 * @return an ArrayList of Points of the door's locations.
	 */
	
	public ArrayList<Point> returnDoorLocations() {
		return doorLocations;
	}

	/**
	 * Returns the x-coordinate of the player's 
	 * starting point on the map.
	 * 
	 * @return x-coordinate of the player's 
	 * starting point
	 */
	
	public int returnStartPointX() {
		return (int)(20);
	}

	/**
	 * Returns the y-coordinate of the player's 
	 * starting point on the map.
	 * 
	 * @return y-coordinate of the player's 
	 * starting point
	 */
	
	public int returnStartPointY() {
		return (int)(height/2);
	}
}
