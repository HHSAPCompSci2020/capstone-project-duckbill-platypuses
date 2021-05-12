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

	//x y, width, height, img image
//	private double x;
//	private double y;
	private double width;
	private double height;
	private PImage mapImage;
	private ArrayList<Point> doorLocations; //make up locations that make sense
	
	
	public Map(PImage image) {
		mapImage = image;
		width = 800;
		height = 600;
		doorLocations = new ArrayList<Point>();
		doorLocations.add(new Point(50, 0));
		doorLocations.add(new Point(400, 0));
		doorLocations.add(new Point(50, (int)(height - 25)));
		doorLocations.add(new Point(400, (int)(height - 25)));
		doorLocations.add(new Point((int)(width - 25), (int)(height/2)));
	}
	
	
	public void draw(PApplet marker) {
		
		marker.image(mapImage,0,0,(float)width,(float)height);
		
		//draw the map
		//Check (call itai to see what to check)
		
		
	}


	public ArrayList<Point> returnDoorLocations() {
		return doorLocations;
	}

	public int returnStartPointX() {
		return (int)(20);
	}

	public int returnStartPointY() {
		return (int)(height/2);
	}
	
	//start return point.
	
//	public boolean isPersonInClass(Player player) {
//		
//	}

}
