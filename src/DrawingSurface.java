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
		size(800, 600);
	}

	/**
	 * Initializes the fields of the drawing surface.
	 * 
	 */
	public void setup() {
		background(255);
		makeQuestions();

		zombies = new ArrayList<Zombie>(5);
		PImage img2 = loadImage("images/Zombie.png");
		PImage img = loadImage("images/Player.png");
		player = new Player(0, 0, img, true);

		for (int i = 0; i < 5; i++) {
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
		on = false;
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

		if (setting == -1) { //Loose
			settingNeg1();

		} else if (setting == -2) { //Win
			settingNeg2();

		} else if (setting == -3) { //Start
			settingNeg3();

		} else if (setting >= 0) { //Rooms 1 - 4
			settingBiggerOr0();
		} else if (setting == -4) { //Room 5
			settingNeg4();
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
						setting = -4;
						player.setX(classroom.get(i).startPointX());
						player.setY(classroom.get(i).startPointY());
						System.out.println("Final");
					}
					else if (i == 4) {
						System.out.println("dont go into this room, final not unlocked.");
					}
					else if (classroom.get(i).isFinished() == false) {
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
		if (firstSetNot0) {
			for (int i = 0; i < zombies.size(); i++) {
				zombies.get(i).makeHidden();
				zombies.get(i).setX(40);
				zombies.get(i).setY(100 * i);
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

					}
				}
			}

		}

		if (timer > 200) {
			timer = 0;
			if (zombies.get(0).isShown() == false) {
				zombies.get(0).makeShown();
			} else if (zombies.get(1).isShown() == false) {
				zombies.get(1).makeShown();
			} else if (zombies.get(2).isShown() == false) {
				zombies.get(2).makeShown();
			} else if (zombies.get(3).isShown() == false) {
				zombies.get(3).makeShown();
			} else if (zombies.get(4).isShown() == false) {
				zombies.get(4).makeShown();
			}
		} else {
			timer++;
		}

		for (int i = 0; i < zombies.size(); i++) {
			if (zombies.get(i).isTouching(player)) {
				setting = -1;

			}
		}
	}
	
	private void settingNeg4() {
		setting = 4;
		classroom.get(setting).draw(this);
		player.draw(this);
		if (firstSetNot0) {
			for (int i = 0; i < zombies.size(); i++) {
				zombies.get(i).makeHidden();
				zombies.get(i).setX(40);
				zombies.get(i).setY(100 * i);
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

					}
				}
			}

		}

		if (timer > 200) {
			timer = 0;
			if (zombies.get(0).isShown() == false) {
				zombies.get(0).makeShown();
			} else if (zombies.get(1).isShown() == false) {
				zombies.get(1).makeShown();
			} else if (zombies.get(2).isShown() == false) {
				zombies.get(2).makeShown();
			} else if (zombies.get(3).isShown() == false) {
				zombies.get(3).makeShown();
			} else if (zombies.get(4).isShown() == false) {
				zombies.get(4).makeShown();
			}
		} else {
			timer++;
		}

		for (int i = 0; i < zombies.size(); i++) {
			if (zombies.get(i).isTouching(player)) {
				setting = -1;

			}
		}
		if(setting != -2) {
		setting = -4;
		}
	}

	private void makeQuestions() {
		

		classroom = new ArrayList<Classroom>(5);

		PImage classImg = loadImage("images/Classroom.png");

		ArrayList<Problem> problems1 = new ArrayList<Problem>(); // C1
		ArrayList<String> possibleAnswers1 = new ArrayList<String>();
		possibleAnswers1.add("3.1");
		possibleAnswers1.add("2");
		possibleAnswers1.add("5");
		possibleAnswers1.add("3.14159");
		Problem p1 = new Problem(possibleAnswers1, 3, "What is pi?");
		problems1.add(p1);
		classroom.add(new Classroom(problems1, classImg));

		ArrayList<Problem> problems2 = new ArrayList<Problem>(); // C2
		ArrayList<String> possibleAnswers2 = new ArrayList<String>();
		possibleAnswers2.add("School");
		possibleAnswers2.add("Happiness");
		possibleAnswers2.add("Love");
		possibleAnswers2.add("42");
		Problem p2 = new Problem(possibleAnswers2, 3, "What is the meaning of life?");
		problems2.add(p2);
		classroom.add(new Classroom(problems2, classImg));

		ArrayList<Problem> problems3 = new ArrayList<Problem>(); // C3
		ArrayList<String> possibleAnswers3 = new ArrayList<String>();
		possibleAnswers3.add("Ido");
		possibleAnswers3.add("Itai");
		possibleAnswers3.add("Ophir");
		possibleAnswers3.add("None");
		Problem p3 = new Problem(possibleAnswers3, 1, "Who is better at coding?");
		problems3.add(p3);
		classroom.add(new Classroom(problems3, classImg));

		ArrayList<Problem> problems4 = new ArrayList<Problem>(); // C4
		ArrayList<String> possibleAnswers4 = new ArrayList<String>();
		possibleAnswers4.add("Barack");
		possibleAnswers4.add("Big Man");
		possibleAnswers4.add("Omaha");
		possibleAnswers4.add("Obama");
		Problem p4 = new Problem(possibleAnswers4, 3, "What is obamas last name");
		problems4.add(p4);
		classroom.add(new Classroom(problems4, classImg));

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
