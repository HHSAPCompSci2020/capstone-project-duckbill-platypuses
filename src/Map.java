import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;

import processing.core.PApplet;

/**
 * The map class represents the map of the school, which the user
 * will navigate through. 
 *  * 
 * @author Ophir Peleg
 * @version 05/06/2021
 */
public class Map {

	//x y, width, height, img image
	private double x;
	private double y;
	private double width;
	private double height;
	private Image image;
	private ArrayList<Point> doorLocations; //make up locations that make sense
	
	
	public Map() {
		
	}
	
	
	public void draw(PApplet marker) {
		
		
		//draw the map
		//Check (call itai to see what to check)
		
		
	}


	public ArrayList<Point> returnDoorLocations() {
		return doorLocations;
	}

	public int returnStartPointX() {
		return (int)x;
	}

	public int returnStartPointY() {
		return (int)y;
	}
	
	//start return point.
	
//	public boolean isPersonInClass(Player player) {
//		
//	}

}
