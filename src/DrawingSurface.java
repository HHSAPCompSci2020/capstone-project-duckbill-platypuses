import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * The DrawingSurface class uses all other classes, excluding main, to draw the
 * entire project.
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
	private PImage onButton;
	private PImage offButton;
	private boolean on;
	private int widthSwitch;
	private int heightSwitch;
	private int xSwitch;
	private int ySwitch;
	private int barX;
	private int barY;
	private int barWidth;
	private int barHeight;
	private ArrayList<Rectangle> answerRect;
	ArrayList<Point> answerCoord;
	Rectangle playerR;
	ArrayList<Point> doorsCoord;
	ArrayList<Rectangle> doorsRect;

	/**
	 * Initializes a drawing surface, which will contain all things you need on the
	 * drawin surface, such as the players, zombies, and map.
	 * 
	 */
	public DrawingSurface() {

	}

	/**
	 * Initializes the setting of the drawing surface.
	 * 
	 */
	public void settings() {
		size(800, 620);
	}

	/**
	 * Initializes the fields of the drawing surface.
	 * 
	 */
	public void setup() {
		background(255);
		makeQuestions();

		zombies = new ArrayList<Zombie>(4);
		PImage img2 = loadImage("images/Zombie.png");
		PImage img = loadImage("images/Player.png");
		player = new Player(0, 0, img, true);

		for (int i = 0; i < 4; i++) {
			zombies.add(new Zombie(0, 0, img2, false));
		}
		setting = -3;
		PImage mapIm = loadImage("images/Map.png");
		map = new Map(mapIm);
		timer = 0;

		player.setX(map.returnStartPointX());
		player.setY(map.returnStartPointY());
		firstSetNot0 = true;
		onButton = loadImage("images/On.png");
		offButton = loadImage("images/Off.png");
		on = true;
		barX = 300;
		barY = 100;
		barWidth = 200;
		barHeight = 30;
		widthSwitch = 120;
		heightSwitch = 40;
		xSwitch = 635;
		ySwitch = 30;

		answerCoord = classroom.get(1).answerLocations();
		answerRect = new ArrayList<Rectangle>(answerCoord.size());
		for (int i = 0; i < answerCoord.size(); i++) {
			answerRect.add(new Rectangle(answerCoord.get(i).x - sizeAnswers * 2, answerCoord.get(i).y - sizeAnswers * 1,
					sizeAnswers * 4, sizeAnswers * 2));
		}
		doorsCoord = map.returnDoorLocations();
		doorsRect = new ArrayList<Rectangle>(doorsCoord.size());
		for (int i = 0; i < doorsCoord.size(); i++) {
			doorsRect.add(new Rectangle(doorsCoord.get(i).x - sizeDoors, doorsCoord.get(i).y - sizeDoors, sizeDoors * 2,
					sizeDoors * 2));

		}

	}

	/**
	 * Draws the drawing surface with all settings and characters.
	 * 
	 */
	public void draw() {

		if (setting == -1) { // Loose
			settingNeg1();

		} else if (setting == -2) { // Win
			settingNeg2();

		} else if (setting == -3) { // Start
			settingNeg3();

		} else if (setting >= 0 && setting < 4) { // Rooms 1 - 4
			settingBiggerOr0();
		} else if (setting == 4) { // Room 5
			setting4();
		}

	}

	private void settingNeg1() {
		PImage looseScreen = loadImage("images/LooseScreen.png");
		image(looseScreen, 0, 0, (float) width, (float) height);

	}

	private void settingNeg2() {
		PImage WinScreen = loadImage("images/WinScreen.png");
		image(WinScreen, 0, 0, (float) width, (float) height);

	}

	private void settingNeg3() {
		int counter = 0;
		for (int i = 0; i < classroom.size(); i++) {
			if (classroom.get(i).isFinished()) {
				counter++;
			}
		}

		map.draw(this);
		player.draw(this);
		if (on) {
			image(onButton, xSwitch, ySwitch, widthSwitch, heightSwitch);
			fill(255, 255, 255);

			rect(barX, barY, barWidth, barHeight);
			fill(0, 255, 0);
			rect(barX, barY, (barWidth / 4) * counter, barHeight);
			for (int i = 1; i < 4; i++) {
				line(barX + (barWidth / 4) * i, barY, barX + (barWidth / 4) * i, barHeight + barY);
			}
		} else {
			image(offButton, xSwitch, ySwitch, widthSwitch, heightSwitch);
		}
		Rectangle playerR = new Rectangle((int) player.getX() + 32, (int) player.getY() + 32,
				(int) player.getWidth() / 2 + 8, (int) player.getHeight() / 2 + 8);
		for (int i = 0; i < doorsCoord.size(); i++) {
			if (playerR.intersects(doorsRect.get(i))) {
				if (i == 4 && counter >= 4) {
					setting = 4;
					player.setX(classroom.get(i).startPointX());
					player.setY(classroom.get(i).startPointY());
					System.out.println("Final");
				} else if (i == 4) {
					System.out.println("dont go into this room, final not unlocked.");
				} else if (classroom.get(i).isFinished() == false) {
					setting = i;
					player.setX(classroom.get(i).startPointX());
					player.setY(classroom.get(i).startPointY());
				}
			}
		}
	}

	private void settingBiggerOr0() {

		classroom.get(setting).draw(this);
		player.draw(this);
		ArrayList<Point> zombieLocations = new ArrayList<Point>();
		zombieLocations.add(new Point(40, 0));
		zombieLocations.add(new Point(40, 400));
		zombieLocations.add(new Point(530, 400));
		zombieLocations.add(new Point(530, 0));

		if (firstSetNot0) {
			for (int i = 0; i < zombies.size(); i++) {
				zombies.get(i).makeHidden();
				int randomZombie = (int) (Math.random() * (4 - i));
				Point p = zombieLocations.get(randomZombie);
				System.out.println(randomZombie + "Point: " + p);
				zombieLocations.remove(randomZombie);
				zombies.get(i).setX(p.getX());
				zombies.get(i).setY(p.getY());
				firstSetNot0 = false;
			}
		}

		for (int i = 0; i < zombies.size(); i++) {
			if (zombies.get(i).isShown()) {
				zombies.get(i).draw(this, player);
			}

		}

		int tempSetting = setting;

		Rectangle playerR = new Rectangle((int) player.getX() + 32, (int) player.getY() + 32,
				(int) player.getWidth() / 2 + 8, (int) player.getHeight() / 2 + 8);
		for (int i = 0; i < 4; i++) {
			if (classroom.get(tempSetting).getProblem().getPossibleAnswers().get(i) != null) {
				if (playerR.intersects(answerRect.get(i))) {
					if (classroom.get(setting).getCorrectAnswer() == i) {
						classroom.get(setting).changeClassToFinished();
						setting = -3;
						firstSetNot0 = true;
						player.setX(map.returnStartPointX());
						player.setY(map.returnStartPointY());
						System.out.println("correct");

					} else if (classroom.get(setting).getLives() == 1) {
						classroom.get(setting).removeLives();
						setting = -1;
						System.out.println("incorrect no lives left");

					} else if (classroom.get(setting).getLives() == 2) {
						player.setX(classroom.get(i).startPointX());
						player.setY(classroom.get(i).startPointY());
						classroom.get(setting).removeLives();
						classroom.get(setting).removeAnswer(i);
						System.out.println("incorrect 1 life left");
						firstSetNot0 = true;

					}
				}
			}

		}

		if (timer > 400) {
			timer = 0;
			if (zombies.get(0).isShown() == false) {
				zombies.get(0).makeShown();
			} else if (zombies.get(1).isShown() == false) {
				zombies.get(1).makeShown();
			} else if (zombies.get(2).isShown() == false) {
				zombies.get(2).makeShown();
			} else if (zombies.get(3).isShown() == false) {
				zombies.get(3).makeShown();
			}
		} else {
			timer++;
		}
		if (setting >= 0) {
			for (int i = 0; i < zombies.size(); i++) {
				if (zombies.get(i).isTouching(player)) {
					setting = -1;

				}
			}
		}

	}

	private void setting4() {
		
		
		classroom.get(setting).draw(this);
		player.draw(this);
		ArrayList<Point> zombieLocations = new ArrayList<Point>();
		zombieLocations.add(new Point(40, 0));
		zombieLocations.add(new Point(40, 400));
		zombieLocations.add(new Point(530, 400));
		zombieLocations.add(new Point(530, 0));

		if (firstSetNot0) {
			for (int i = 0; i < zombies.size(); i++) {
				zombies.get(i).makeHidden();
				int randomZombie = (int) (Math.random() * (4 - i));
				Point p = zombieLocations.get(randomZombie);
				System.out.println(randomZombie + "Point: " + p);
				zombieLocations.remove(randomZombie);
				zombies.get(i).setX(p.getX());
				zombies.get(i).setY(p.getY());
				firstSetNot0 = false;
			}
		}
		for (int i = 0; i < zombies.size(); i++) {
			if (zombies.get(i).isShown()) {
				zombies.get(i).draw(this, player);
			}

		}

		int tempSetting = setting;

		Rectangle playerR = new Rectangle((int) player.getX() + 32, (int) player.getY() + 32,
				(int) player.getWidth() / 2 + 8, (int) player.getHeight() / 2 + 8);
		for (int i = 0; i < 4; i++) {
			if (classroom.get(tempSetting).getProblem().getPossibleAnswers().get(i) != null) {
				if (playerR.intersects(answerRect.get(i))) {
					if (classroom.get(setting).getCorrectAnswer() == i) {
						setting = -2;
					} else if (classroom.get(setting).getLives() == 1) {
						classroom.get(setting).removeLives();
						setting = -1;
						System.out.println("incorrect no lives left");

					} else if (classroom.get(setting).getLives() == 2) {
						player.setX(classroom.get(i).startPointX());
						player.setY(classroom.get(i).startPointY());
						classroom.get(setting).removeLives();
						classroom.get(setting).removeAnswer(i);
						System.out.println("incorrect 1 life left");
						firstSetNot0 = true;

					}
				}
			}

		}

		if (timer > 300) {
			timer = 0;
			if (zombies.get(0).isShown() == false) {
				zombies.get(0).makeShown();
			} else if (zombies.get(1).isShown() == false) {
				zombies.get(1).makeShown();
			} else if (zombies.get(2).isShown() == false) {
				zombies.get(2).makeShown();
			} else if (zombies.get(3).isShown() == false) {
				zombies.get(3).makeShown();
			}
		} else {
			timer++;
		}

		for (int i = 0; i < zombies.size(); i++) {
			if (zombies.get(i).isTouching(player)) {
				setting = -1;

			}
		}
		if (setting != -2) {
			// setting = -4;
		}
	}

	private void makeQuestions() {

		classroom = new ArrayList<Classroom>(5);

		PImage classImg = loadImage("images/Classroom.png");
		// Math
		ArrayList<Problem> problems1 = new ArrayList<Problem>(); // C1
		ArrayList<String> possibleAnswers1 = new ArrayList<String>();
		possibleAnswers1.add("x^3");
		possibleAnswers1.add("x");
		possibleAnswers1.add("2x");
		possibleAnswers1.add("3x^2");
		Problem p1 = new Problem(possibleAnswers1, 3, "What is the derivative \n of x^3?");
		problems1.add(p1);

		ArrayList<String> possibleAnswers1b = new ArrayList<String>();
		possibleAnswers1b.add("3 cm");
		possibleAnswers1b.add("5 cm");
		possibleAnswers1b.add("6 cm");
		possibleAnswers1b.add("2 cm");
		Problem p1b = new Problem(possibleAnswers1b, 1,
				"A right triangle has legs of \n 3 cm, 4 cm. \n What is the hypotenuse");
		problems1.add(p1b);

		ArrayList<String> possibleAnswers1c = new ArrayList<String>();
		possibleAnswers1c.add("1");
		possibleAnswers1c.add("0");
		possibleAnswers1c.add("Undefined");
		possibleAnswers1c.add("Infinity");
		Problem p1c = new Problem(possibleAnswers1c, 2, "What is 0^0");
		problems1.add(p1c);

		ArrayList<String> possibleAnswers1d = new ArrayList<String>();
		possibleAnswers1d.add("heptagon");
		possibleAnswers1d.add("hexagon");
		possibleAnswers1d.add("pentagon");
		possibleAnswers1d.add("nonagon");
		Problem p1d = new Problem(possibleAnswers1d, 1, "What is a shape \n that has 6 sides?");
		problems1.add(p1d);

		ArrayList<String> possibleAnswers1e = new ArrayList<String>();
		possibleAnswers1e.add("√2/2");
		possibleAnswers1e.add("√2");
		possibleAnswers1e.add("√3/1");
		possibleAnswers1e.add("1/√3");
		Problem p1e = new Problem(possibleAnswers1e, 0, "Which of the following \n is sin(pi/4)");
		problems1.add(p1e);
		classroom.add(new Classroom(problems1, classImg));

		//History
		ArrayList<Problem> problems2 = new ArrayList<Problem>(); // C2
		ArrayList<String> possibleAnswers2 = new ArrayList<String>();
		possibleAnswers2.add("Christopher Columbus");
		possibleAnswers2.add("Leif Erikson");
		possibleAnswers2.add("Holen F Rib");
		possibleAnswers2.add("Sull Burg");
		Problem p2 = new Problem(possibleAnswers2, 1, "Who first discovered America?");
		problems2.add(p2);

		ArrayList<String> possibleAnswers2b = new ArrayList<String>();
		possibleAnswers2b.add("July 4, 1776");
		possibleAnswers2b.add("Jul 4, 1777");
		possibleAnswers2b.add("August 2, 1776");
		possibleAnswers2b.add("August 2, 1777");
		Problem p2b = new Problem(possibleAnswers2b, 2, "When was the Declaration \n of Independence signed?");
		problems2.add(p2b);

		ArrayList<String> possibleAnswers2c = new ArrayList<String>();
		possibleAnswers2c.add("Germany");
		possibleAnswers2c.add("Only United States");
		possibleAnswers2c.add("France");
		possibleAnswers2c.add("Tuvalu");
		Problem p2c = new Problem(possibleAnswers2c, 2, "Who helped pay for the \n statue of liberty?");
		problems2.add(p2c);

		ArrayList<String> possibleAnswers2d = new ArrayList<String>();
		possibleAnswers2d.add("Franklin Pierce");
		possibleAnswers2d.add("Abraham Lincoln");
		possibleAnswers2d.add("Zachary Taylor");
		possibleAnswers2d.add("John Tyler");
		Problem p2d = new Problem(possibleAnswers2d, 0, "Who was the 14th president");
		problems2.add(p2d);

		ArrayList<String> possibleAnswers2e = new ArrayList<String>();
		possibleAnswers2e.add("1914");
		possibleAnswers2e.add("1916");
		possibleAnswers2e.add("1915");
		possibleAnswers2e.add("2001");
		Problem p2e = new Problem(possibleAnswers2e, 0, "When did ww1 start?");
		problems2.add(p2e);

		classroom.add(new Classroom(problems2, classImg));

		// Science
		ArrayList<Problem> problems3 = new ArrayList<Problem>(); // C3
		ArrayList<String> possibleAnswers3 = new ArrayList<String>();
		possibleAnswers3.add("F");
		possibleAnswers3.add("NaCl");
		possibleAnswers3.add("Lu");
		possibleAnswers3.add("Rf");
		Problem p3 = new Problem(possibleAnswers3, 1, "Which is a molecule");
		problems3.add(p3);

		ArrayList<String> possibleAnswers3b = new ArrayList<String>();
		possibleAnswers3b.add("None of the above");
		possibleAnswers3b.add("3.1415 * 10^22");
		possibleAnswers3b.add("6.401 * 10^23");
		possibleAnswers3b.add("60.22 * 10^23");
		Problem p3b = new Problem(possibleAnswers3b, 0, "What is Avogadro's number?");
		problems3.add(p3b);
		//		
		//		ArrayList<String> possibleAnswers3c = new ArrayList<String>();
		//		possibleAnswers3c.add("None of the above");
		//		possibleAnswers3c.add("3.1415 * 10^22");
		//		possibleAnswers3c.add("6.401 * 10^23");
		//		possibleAnswers3c.add("60.22 * 10^23");
		//		Problem p3c = new Problem(possibleAnswers3c, 0, "What is Avogadro's number?");
		//		problems3.add(p3c);
		//				
		//		
		//		ArrayList<String> possibleAnswers3d = new ArrayList<String>();
		//		possibleAnswers3d.add("None of the above");
		//		possibleAnswers3d.add("3.1415 * 10^22");
		//		possibleAnswers3d.add("6.401 * 10^23");
		//		possibleAnswers3d.add("60.22 * 10^23");
		//		Problem p3d = new Problem(possibleAnswers3d, 0, "What is Avogadro's number?");
		//		problems3.add(p3d);
		//				
		//		
		//		ArrayList<String> possibleAnswers3e = new ArrayList<String>();
		//		possibleAnswers3e.add("None of the above");
		//		possibleAnswers3e.add("3.1415 * 10^22");
		//		possibleAnswers3e.add("6.401 * 10^23");
		//		possibleAnswers3e.add("60.22 * 10^23");
		//		Problem p3e = new Problem(possibleAnswers3e, 0, "What is Avogadro's number?");
		//		problems3.add(p3e);
		//				
		//		

		classroom.add(new Classroom(problems3, classImg));

		// Business

		ArrayList<Problem> problems4 = new ArrayList<Problem>(); // C4
		ArrayList<String> possibleAnswers4 = new ArrayList<String>();
		possibleAnswers4.add("My Right Plan");
		possibleAnswers4.add("Materials Requirement Planning");
		possibleAnswers4.add("Manufacturing Revenue Plus");
		possibleAnswers4.add("Marginal Rate of Principal");
		Problem p4 = new Problem(possibleAnswers4, 1, "What is MRP? ");
		problems4.add(p4);

		//		ArrayList<String> possibleAnswers4b = new ArrayList<String>();
		//		possibleAnswers4b.add("Barack");
		//		possibleAnswers4b.add("Big Man");
		//		possibleAnswers4b.add("Omaha");
		//		possibleAnswers4b.add("Obama");
		//		Problem p4b = new Problem(possibleAnswers4b, 3, "What is obamas last name");
		//		problems4.add(p4b);
		//		
		//		ArrayList<String> possibleAnswers4c = new ArrayList<String>();
		//		possibleAnswers4c.add("Barack");
		//		possibleAnswers4c.add("Big Man");
		//		possibleAnswers4c.add("Omaha");
		//		possibleAnswers4c.add("Obama");
		//		Problem p4c = new Problem(possibleAnswers4c, 3, "What is obamas last name");
		//		problems4.add(p4c);
		//		
		//		ArrayList<String> possibleAnswers4d = new ArrayList<String>();
		//		possibleAnswers4d.add("Barack");
		//		possibleAnswers4d.add("Big Man");
		//		possibleAnswers4d.add("Omaha");
		//		possibleAnswers4d.add("Obama");
		//		Problem p4d = new Problem(possibleAnswers4d, 3, "What is obamas last name");
		//		problems4.add(p4d);
		//		
		//		ArrayList<String> possibleAnswers4e = new ArrayList<String>();
		//		possibleAnswers4e.add("Barack");
		//		possibleAnswers4e.add("Big Man");
		//		possibleAnswers4e.add("Omaha");
		//		possibleAnswers4e.add("Obama");
		//		Problem p4e = new Problem(possibleAnswers4e, 3, "What is obamas last name");
		//		problems4.add(p4e);
		//		

		classroom.add(new Classroom(problems4, classImg));

		// Final Java
		ArrayList<Problem> problems5 = new ArrayList<Problem>(); // C5
		ArrayList<String> possibleAnswers5 = new ArrayList<String>();
		possibleAnswers5.add("30 mod 3");
		possibleAnswers5.add("(int)2.9");
		possibleAnswers5.add("((Integer.MIN_VALUE)-(Integer.MAX_VALUE))*0");
		possibleAnswers5.add("2(3)");
		Problem p5 = new Problem(possibleAnswers5, 2, "What is biggest in Java. ");
		problems5.add(p5);
		classroom.add(new Classroom(problems5, classImg));
	}

	/*
	 * This method is called once after every time a mouse button is pressed. It
	 * then, depending on weather the user pressed the are of the switch, might
	 * change the switch to on or off.
	 */
	public void mousePressed() {

		Rectangle r = new Rectangle(xSwitch, ySwitch, widthSwitch, heightSwitch);
		if (r.contains(mouseX, mouseY)) {
			on = !on;
		}

	}

}
