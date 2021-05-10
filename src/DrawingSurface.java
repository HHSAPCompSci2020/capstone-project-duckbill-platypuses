import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * The DrawingSurface class uses all other classes, excluding main, to draw
 * the entire project.
 *  * 
 * @author Itai Reingold - Nutman
 * @version 05/06/2021
 */

public class DrawingSurface extends PApplet {
		
	private ArrayList<Classroom> classroom; 
	private ArrayList<Zombie> zombies;
	private Player player;
	private Map map;
	int sizeDoors = 10;
	int sizeAnswers = 5;
	int setting;
	
	
	public DrawingSurface() {
		classroom = new ArrayList<Classroom>(5);
		zombies = new ArrayList<Zombie>(5);
		PImage img = loadImage("characters/Player.png");
		player = new Player(0,0,img,true);
		PImage img2 = loadImage("characters/Zombie.png");
		for (int i = 0; i < zombies.size(); i ++) {
			zombies.set(i, new Zombie(0,0,img2,false));
		}
		setting = 0;
		map = new Map();
//		for (int i = 0; i < zombies.size(); i ++) { // add questions into classrooms
		
//			zombies.set(i, new Zombie(0,0,img2,false));
//		}
	}
	
	public void setup() {
		
	}
	
	public void draw() {
		background(255);
		
		
		
		if (setting == 0) {
			map.draw(this);
			player.setX(map.startPointX());
			player.setY(map.startPointY());
			player.draw(this);
			ArrayList<Point> doorsCoord  = map.doorLocations();
			ArrayList<Rectangle> doorsRect  = new ArrayList<Rectangle>(doorsCoord.size());
			for (int i = 0; i < doorsCoord.size(); i++) {
				doorsRect.add( new Rectangle(doorsCoord.get(i).x - sizeDoors, doorsCoord.get(i).y - sizeDoors, sizeDoors*2, sizeDoors*2)); 
			}
			for (int i = 0; i < doorsCoord.size(); i++) {
				if (doorsRect.get(i).contains(player.getX(), player.getY()) ||
					doorsRect.get(i).contains(player.getX() +i , player.getY()) ||
					doorsRect.get(i).contains(player.getX(), player.getY()) ||
					doorsRect.get(i).contains(player.getX(), player.getY())) {
					
				}
			}
		}
		
		
		//if { 0 
		//drawMap(0
		//player.setX, //MapStartibgPOintX
		//player.setY, //MapStartibgPOintY
		//draw player
		//call doorloc map // list of points.
		//convert points to rectangles +- size of doors, 
		//if person is touching the rectangle of doors (if tL, bL, tR, bR touching rectangle)
		//if so, change setting to that classroom
	//	player.updatewhere("Class")
		
		
		//if not, do nothing. 
		
		
		
	//}
		
		//if { !0
			
				//classroom[setting].draw()
				//player.setX, //classroom[setting].StartingPointX
				//player.setY, //Class1StartingPointY
				//draw player
				//locAnswers classroom[setting] (gets a list of points from classroom with the x and y coordinates of all the answers a = 0, b = 1 ... d = 3)
				//convert points to rectangles +- answerSizes, 
				//if person is touching the rectangle of answers (if tL,TR,BL, BR = any answer) (checks if the person is touching any of the sides of the answers)
				//if so, classroom[setting]checkanswer(char answer) (checks if the answer that they put in makes sense)
				//If their answer is correct then send the player back to the map and then changes settings
				
				
		//
				
				
			//}
	}
	
	

}

