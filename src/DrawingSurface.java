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
//		classroom = new ArrayList<Classroom>(5);
//		zombies = new ArrayList<Zombie>(5);
//		PImage img = loadImage("images/Player.png");
//		player = new Player(0,0,img,true);
//		PImage img2 = loadImage("images/Zombie.png");
//		for (int i = 0; i < zombies.size(); i ++) {
//			zombies.set(i, new Zombie(0,0,img2,false));
//		}
//		setting = 0;
//		PImage classImg = loadImage("images/Classroom.png");
//		PImage mapIm = loadImage("images/Map.png");
//		map = new Map(mapIm); // pass in the image for the map
//		timer = 0; 
//		for (int i = 0; i < classroom.size(); i ++) { // add questions into classrooms
//		
//			ArrayList<Problem> problems = new ArrayList<Problem>(1); 
//			problems.get(0).setQuestion("Qtest");
//			ArrayList<String> possibleAnswers = new ArrayList<String>();
//			possibleAnswers.add("A1");
//			possibleAnswers.add("A2");
//			possibleAnswers.add("A3");
//			possibleAnswers.add("A4");
//			problems.get(0).setPossibleAnswers(possibleAnswers); //four answer, with indexes 0 -3. 
//			problems.get(0).setCorrectAnswer(2); //imput index of correct (Answer A3)
//			
//			classroom.set(i, new Classroom(problems ,classImg));
			
		//}
	}
	
	public void settings() {
		size(800, 600);  
	}
	public void setup() {
		background(255);
		
		classroom = new ArrayList<Classroom>(5);
		zombies = new ArrayList<Zombie>(5);
		PImage img = loadImage("images/Player.png");
		player = new Player(0,0,img,true);
		PImage img2 = loadImage("images/Zombie.png");
		for (int i = 0; i < zombies.size(); i ++) {
			zombies.set(i, new Zombie(0,0,img2,false));
		}
		setting = 0;
		PImage classImg = loadImage("images/Classroom.png");
		PImage mapIm = loadImage("images/Map.png");
		map = new Map(mapIm); // pass in the image for the map
		timer = 0; 
		for (int i = 0; i < classroom.size(); i ++) { // add questions into classrooms
		
			ArrayList<Problem> problems = new ArrayList<Problem>(1); 
			problems.get(0).setQuestion("Qtest");
			ArrayList<String> possibleAnswers = new ArrayList<String>();
			possibleAnswers.add("A1");
			possibleAnswers.add("A2");
			possibleAnswers.add("A3");
			possibleAnswers.add("A4");
			problems.get(0).setPossibleAnswers(possibleAnswers); //four answer, with indexes 0 -3. 
			problems.get(0).setCorrectAnswer(2); //imput index of correct (Answer A3)
			
			classroom.set(i, new Classroom(problems ,classImg));
		}
	}
	
	public void draw() {
		
		
		if (setting == -1) {
			
		}
		
		
		else if (setting == 0) {
			map.draw(this);
			player.setX(map.returnStartPointX()); //for the person
			player.setY(map.returnStartPointY());
			player.draw(this);
			ArrayList<Point> doorsCoord  = map.returnDoorLocations(); //x y loc should be middle of door
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
			
			classroom.get(setting).draw(this);
			player.setX(classroom.get(setting).startPointX());
			player.setY(classroom.get(setting).startPointY());
			player.draw(this);
			for (int i = 0; i < zombies.size(); i ++) {
				zombies.get(i).spawnLoc(player);
				zombies.get(i).draw(this);
			}
			

			ArrayList<Point> answerCoord  = classroom.get(setting).answerLocations(); //x y loc should be middle of answer
			ArrayList<Rectangle> answerRect  = new ArrayList<Rectangle>(answerCoord.size());
			for (int i = 0; i < answerCoord.size(); i++) {
				answerRect.add(new Rectangle(answerCoord.get(i).x - sizeAnswers, answerCoord.get(i).y - sizeAnswers, sizeAnswers*2, sizeAnswers*2)); 
			}
			for (int i = 0; i < answerCoord.size(); i++) {
				if (answerRect.get(i).contains(player.getX(), player.getY()) ||
					answerRect.get(i).contains(player.getX() + player.getWidth(), player.getY()) ||
					answerRect.get(i).contains(player.getX(), player.getY() + player.getHeight()) ||
					answerRect.get(i).contains(player.getX()  +player.getWidth(), player.getY() + player.getHeight())) {
						
					if (classroom.get(setting).getCorrectAnswer() == setting) {
						classroom.get(setting).changeClassToFinished();
						setting = 0; 
					}
					else if (classroom.get(setting).getLives() == 1 ) {
						classroom.get(setting).removeLives();
						setting = -1;
					}
					else if (classroom.get(setting).getLives() == 2) {
						classroom.get(setting).removeLives();
						classroom.get(setting).removeAnswer(i);
						// a b c d, loc: 0 1 2 3, loc 0 1 2 3, a null c d
						//If an answer is removed then you shouldn't draw it. (Only draw the answers that aren't null)
					}
					
					
				}
			}
			
			if (timer % 100 == 0) {
				timer = 0; 
				if (zombies.get(0).isShown() == false) {
					zombies.get(0).makeShown();
				}
				else if (zombies.get(1).isShown() == false) {
					zombies.get(1).makeShown();
				}
				else if (zombies.get(2).isShown() == false) {
					zombies.get(2).makeShown();
				}
				else if (zombies.get(3).isShown() == false) {
					zombies.get(3).makeShown();
				}
				else if (zombies.get(4).isShown() == false) {
					zombies.get(4).makeShown();
				}
			}
			else {
				timer ++;
			}
			
			for (int i = 0; i < zombies.size(); i ++) {
				if (zombies.get(i).isTouching(player)) {
					setting = -1;
				}
			}
			
			
		}
		
		

	}
	
	

}

