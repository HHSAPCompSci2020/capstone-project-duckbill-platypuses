import java.util.ArrayList;
import processing.core.PApplet;

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
	//sizeofDoors
	//sizeofDoors
	///int setting; 0== hallway, cl -1, cl - 5
	
	public DrawingSurface() {
		super();
	}
	
	public void setup() {
		
	}
	
	public void draw() {
		background(255);
		
		
		
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

