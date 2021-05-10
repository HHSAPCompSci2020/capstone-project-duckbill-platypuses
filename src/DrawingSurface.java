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
	int timer; 
	
	
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
		timer = 0; 
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
					doorsRect.get(i).contains(player.getX() + player.getWidth(), player.getY()) ||
					doorsRect.get(i).contains(player.getX(), player.getY() + player.getHeight()) ||
					doorsRect.get(i).contains(player.getX()  +player.getWidth(), player.getY() + player.getHeight())) {
						
					if (classroom.get(i).isFinished() == false) {
					setting = i; 
					}
					
					
				}
			}
			
			
		}
		
		
	
		else if (setting != 0) {
			
			classroom[setting].draw();
			player.setX(classroom[setting].startPointX());
			player.setY(classroom[setting].startPointY());
			player.draw(this);

			ArrayList<Point> answerCoord  = classroom[setting].answerLocations();
			ArrayList<Rectangle> answerRect  = new ArrayList<Rectangle>(answerCoord.size());
			for (int i = 0; i < answerCoord.size(); i++) {
				answerRect.add(new Rectangle(answerCoord.get(i).x - sizeAnswers, answerCoord.get(i).y - sizeAnswers, sizeAnswers*2, sizeAnswers*2)); 
			}
			for (int i = 0; i < answerCoord.size(); i++) {
				if (answerRect.get(i).contains(player.getX(), player.getY()) ||
					answerRect.get(i).contains(player.getX() + player.getWidth(), player.getY()) ||
					answerRect.get(i).contains(player.getX(), player.getY() + player.getHeight()) ||
					answerRect.get(i).contains(player.getX()  +player.getWidth(), player.getY() + player.getHeight())) {
						
					if (classroom[setting].correctAnswer == setting) {
						classroom[setting].changeClassToFinished();
						setting = 0; 
					}
					
					
				}
			}
			
			if (timer % 100 == 0) {
				timer = 0; 
				if (zombies[0].isShown() == false) {
					zombies[0].makeShown;
				}
				else if (zombies[1].isShown() == false) {
					zombies[1].makeShown;
				}
				else if (zombies[2].isShown() == false) {
					zombies[2].makeShown;
				}
				else if (zombies[3].isShown() == false) {
					zombies[3].makeShown;
				}
				else if (zombies[4].isShown() == false) {
					zombies[4].makeShown;
				}
			}
			else {
				timer ++;
			}
			
		}

	}
	
	

}

