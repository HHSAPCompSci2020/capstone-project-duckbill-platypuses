import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * The DrawingSurface class uses all other classes, excluding main, to draw
 * the entire project.
 * 
 * @author Itai Reingold - Nutman
 * @version 05/06/2021
 */

public class DrawingSurface extends PApplet {

	private ArrayList<Classroom> classroom; 
	private ArrayList<Zombie> zombies;
	private Player player;
	private Map map;
	private int sizeDoors = 80;
	private int sizeAnswers = 30;
	private int setting;
	private int timer; 
	private boolean firstSetNot0;


	/**
	 * Initializes a drawing surface, which will contain all things you need on the drawin surface, 
	 * such as the players, zombies, and map. 
	 * 
	 */
	public DrawingSurface() {

	}

	/**
	 * Initializes the setting of the drawing surface. 
	 * 
	 */
	public void settings() {
		size(800, 600);  
	}
	
	/**
	 * Initializes the fields of the drawing surface. 
	 * 
	 */
	public void setup() {
		background(255);

		classroom = new ArrayList<Classroom>(5);

		PImage classImg = loadImage("images/Classroom.png");
		
		ArrayList<Problem> problems1 = new ArrayList<Problem>(); //C1
		ArrayList<String> possibleAnswers1 = new ArrayList<String>();
		possibleAnswers1.add("3.1");
		possibleAnswers1.add("2");
		possibleAnswers1.add("5");
		possibleAnswers1.add("3.14159");
		Problem p1 = new Problem(possibleAnswers1, 3,  "What is pi?" );
		problems1.add(p1);
		classroom.add(new Classroom(problems1 ,classImg));

		
		

		ArrayList<Problem> problems2 = new ArrayList<Problem>(); //C2
		ArrayList<String> possibleAnswers2 = new ArrayList<String>();
		possibleAnswers2.add("School");
		possibleAnswers2.add("Happiness");
		possibleAnswers2.add("Love");
		possibleAnswers2.add("42");
		Problem p2 = new Problem(possibleAnswers2, 3,  "What is the meaning of life?" );
		problems2.add(p2);
		classroom.add(new Classroom(problems2 ,classImg));

		
		
		ArrayList<Problem> problems3 = new ArrayList<Problem>(); //C3
		ArrayList<String> possibleAnswers3 = new ArrayList<String>();
		possibleAnswers3.add("Ido");
		possibleAnswers3.add("Itai");
		possibleAnswers3.add("Ophir");
		possibleAnswers3.add("None");
		Problem p3 = new Problem(possibleAnswers3, 1,  "Who is better at coding?" );
		problems3.add(p3);
		classroom.add(new Classroom(problems3 ,classImg));

		
		
		
		ArrayList<Problem> problems4 = new ArrayList<Problem>(); //C4
		ArrayList<String> possibleAnswers4 = new ArrayList<String>();
		possibleAnswers4.add("Barack");
		possibleAnswers4.add("Big Man");
		possibleAnswers4.add("Omaha");
		possibleAnswers4.add("Obama");
		Problem p4 = new Problem(possibleAnswers4, 3,  "What is obamas last name" );
		problems4.add(p4);
		classroom.add(new Classroom(problems4 ,classImg));

		
		ArrayList<Problem> problems5 = new ArrayList<Problem>(); //C5
		ArrayList<String> possibleAnswers5 = new ArrayList<String>();
		possibleAnswers5.add("Pickles");
		possibleAnswers5.add("Tomato");
		possibleAnswers5.add("Apple");
		possibleAnswers5.add("Cheetos");
		Problem p5 = new Problem(possibleAnswers5, 2,  "Best fruit" );
		problems5.add(p5);
		classroom.add(new Classroom(problems5 ,classImg));

		
		zombies = new ArrayList<Zombie>(5);
		PImage img2 = loadImage("images/Zombie.png");
		PImage img = loadImage("images/Player.png");
		player = new Player(0,0,img,true);

		for (int i = 0; i < 5; i ++) {
			zombies.add(new Zombie(0,0,img2,false));
		}
		setting = -3;
		PImage mapIm = loadImage("images/Map.png");
		map = new Map(mapIm);
		timer = 0; 

		player.setX(map.returnStartPointX());
		player.setY(map.returnStartPointY());
		firstSetNot0 = true;
	}

	/**
	 * Draws the drawing surface with all settings and characters. 
	 * 
	 */
	public void draw() {


		if (setting == -2) { 
			PImage WinScreen = loadImage("images/WinScreen.png");
			image(WinScreen,0,0,(float)width,(float)height);
			
			System.out.println("Win");

		}
		
		if (setting == -1) { 

			PImage looseScreen = loadImage("images/LooseScreen.png");
			image(looseScreen,0,0,(float)width,(float)height);
			
			System.out.println("Lost");

		} else if (setting == -3) {
			
			int counter = 0;
			for (int i = 0; i < classroom.size(); i ++) {
				
				if (classroom.get(i).isFinished()){
					counter ++;
				}
				
			}
			if (counter >= 5) {
				setting = -2;
			}
			
			map.draw(this);
			player.draw(this);
			ArrayList<Point> doorsCoord  = map.returnDoorLocations();
			ArrayList<Rectangle> doorsRect  = new ArrayList<Rectangle>(doorsCoord.size());
			for (int i = 0; i < doorsCoord.size(); i++) {
				doorsRect.add( new Rectangle(doorsCoord.get(i).x - sizeDoors, doorsCoord.get(i).y - sizeDoors, sizeDoors*2, sizeDoors*2)); 

			}
			for (int i = 0; i < doorsCoord.size(); i++) {


				if (doorsRect.get(i).contains(player.getX(), player.getY()) ||
						doorsRect.get(i).contains(player.getX() + player.getWidth(), player.getY()) ||
						doorsRect.get(i).contains(player.getX(), player.getY() + player.getHeight()) ||
						doorsRect.get(i).contains(player.getX()  + player.getWidth(), player.getY() + player.getHeight())) {



					if (classroom.get(i).isFinished() == false) {
						setting = i; 
						player.setX(classroom.get(i).startPointX());
						player.setY(classroom.get(i).startPointY());

						
					}


				}
			}


		} else if (setting >= 0) {

			classroom.get(setting).draw(this);
			player.draw(this);
			if (firstSetNot0) {
				for (int i = 0; i < zombies.size(); i ++) {
					zombies.get(i).makeHidden();
					zombies.get(i).setX(40);
					zombies.get(i).setY(100*i);
					firstSetNot0 = false;
				}
			}

			for (int i = 0; i < zombies.size(); i ++) {
				if (zombies.get(i).isShown()) {
					zombies.get(i).draw(this, player);
				}

			}


			ArrayList<Point> answerCoord  = classroom.get(setting).answerLocations();
			ArrayList<Rectangle> answerRect  = new ArrayList<Rectangle>(answerCoord.size());
			for (int i = 0; i < answerCoord.size(); i++) {
				answerRect.add(new Rectangle(answerCoord.get(i).x - sizeAnswers*2, answerCoord.get(i).y - sizeAnswers*1, sizeAnswers*4, sizeAnswers*2)); 
			}
			for (int i = 0; i < answerCoord.size(); i++) {			 
				if (answerRect.get(i).contains((float) (player.getX() + 32), (float)player.getY() + 32) ||
						answerRect.get(i).contains(player.getX() + 32 + (((float)(player.getX() + player.getWidth()/2) + 32) - ((float)player.getX() + 32)), (float)(player.getY() + 32)) ||
						answerRect.get(i).contains((float)(player.getX() + 32), (float)player.getY() + 32 + (((float)(player.getY() + player.getHeight()/2) + 32)-((float)player.getY() + 32))) ||
						answerRect.get(i).contains((float)(player.getX() + 32 + (((float)(player.getX() + player.getWidth()/2) + 32) - ((float)player.getX() + 32))), (float)player.getY() + 32 + (((float)(player.getY() + player.getHeight()/2) + 32)-((float)player.getY() + 32)))) {

					if (classroom.get(setting).getCorrectAnswer() == i) {
						classroom.get(setting).changeClassToFinished();
						setting = -3;
						firstSetNot0 = true;
						player.setX(map.returnStartPointX());
						player.setY(map.returnStartPointY());
						System.out.println("Correct");

					}else if (classroom.get(setting).getLives() == 1 ) {
						classroom.get(setting).removeLives();
						setting = -1;
						System.out.println("Second wrong");
					}else if (classroom.get(setting).getLives() == 2) {
						player.setX(classroom.get(i).startPointX());
						player.setY(classroom.get(i).startPointY());
						classroom.get(setting).removeLives();
						classroom.get(setting).removeAnswer(i);
						System.out.println("First wrong");
					}

				}
			}

			if (timer > 200) {
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
					System.out.println("Zombeilost????");
					setting = -1;
					
				}
			}


		}



	}



}

