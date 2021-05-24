import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;
import javax.sound.sampled.*;

/**
 * The DrawingSurface class uses all other classes, excluding main, to draw the
 * entire project.
 * 
 * @author Itai Reingold - Nutman
 * @version 05/06/2021
 */

public class DrawingSurface extends PApplet {

	private ArrayList<Integer> keys;

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
	private ArrayList<Point> answerCoord;
	private ArrayList<Point> doorsCoord;
	private ArrayList<Rectangle> doorsRect;
	private PImage globe;
	private PImage calculator;
	private PImage beaker;
	private PImage dollar;
	private PImage computer;
	private PImage info;
	private boolean onInfo;
	private int xInfo;
	private int yInfo;
	private int widthInfo;
	private int heightInfo;
	private int timer1;
	private boolean first;
	private int time = 0;

	/**
	 * Initializes a drawing surface, which will contain all things you need on the
	 * drawin surface, such as the players, zombies, and map.
	 * 
	 */
	public DrawingSurface() {
		keys = new ArrayList<Integer>();
	}

	/**
	 * Initializes the setting of the drawing surface.
	 * 
	 */
	public void settings() {

		size(800, 700);
	}

	/**
	 * Initializes the fields of the drawing surface.
	 * 
	 */
	public void setup() {
		background(0);
		makeQuestions();

		zombies = new ArrayList<Zombie>(4);
		PImage img2 = loadImage("images/Zombie.png");
		PImage img = loadImage("images/Player.png");
		player = new Player(0, 0, img, true);

		for (int i = 0; i < 4; i++) {
			zombies.add(new Zombie(0, 0, img2, false));
		}
		setting = 5;
		PImage mapIm = loadImage("images/Map.png");
		map = new Map(mapIm);
		timer = 0;

		player.setX(map.returnStartPointX());
		player.setY(map.returnStartPointY());
		firstSetNot0 = true;
		onButton = loadImage("images/On.png");
		offButton = loadImage("images/Off.png");
		on = true;
		barX = 50;
		barY = 625;
		barWidth = 200;
		barHeight = 30;
		widthSwitch = 140;
		heightSwitch = 40;
		xSwitch = 570;
		ySwitch = 620;
		globe = loadImage("images/Globe.png");
		calculator = loadImage("images/Calculator.png");
		beaker = loadImage("images/Beaker.png");
		dollar = loadImage("images/Dollar.png");
		computer = loadImage("images/Computer.png");
		info = loadImage("images/info.png");
		onInfo = true;
		xInfo = 380;
		yInfo = 610;
		widthInfo = 60;
		heightInfo = 60;
		timer1 = 0;
		first = true;

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

		 background(0);

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
		} else if (setting == 5) {
			setting5();
		}

		// Movement
		if (isPressed(KeyEvent.VK_LEFT)) {
			if ((player.getX() > -30)) {
				player.setX(player.getX() - 3);
			}
		}
		if (isPressed(KeyEvent.VK_RIGHT)) {
			if (((player.getX() + player.getWidth()) < 830)) {
				player.setX(player.getX() + 3);
			}
		}

		if (isPressed(KeyEvent.VK_UP)) {
			if ((player.getY() > 2)) {
				player.setY(player.getY() - 3);
			}
		}
		if (isPressed(KeyEvent.VK_DOWN)) {
			if (((player.getY() + player.getHeight()) < 600)) {
				player.setY(player.getY() + 3);
			}
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

		image(info, xInfo, yInfo, widthInfo, heightInfo);

		if (onInfo) {
			setting = 5;
		}

		if (on) {
			image(onButton, xSwitch, ySwitch, widthSwitch, heightSwitch);
			fill(255, 255, 255);

			rect(barX, barY, barWidth, barHeight);
			fill(0, 255, 0);
			rect(barX, barY, (barWidth / 4) * counter, barHeight);
			for (int i = 1; i < 4; i++) {
				line(barX + (barWidth / 4) * i, barY, barX + (barWidth / 4) * i, barHeight + barY);
			}
		}

		else {
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
				} else if (i == 4) {
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
		if (setting == 0) {
			image(calculator, 270, 45, 75, 75);
		} else if (setting == 1) {
			image(globe, 270, 45, 75, 75);
		} else if (setting == 2) {
			image(beaker, 270, 45, 75, 75);
		} else if (setting == 3) {
			image(dollar, 270, 45, 75, 75);
		}
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

		Rectangle playerR = new Rectangle((int) player.getX() + 32, (int) player.getY() + 32,
				(int) player.getWidth() / 2 + 8, (int) player.getHeight() / 2 + 8);
		for (int i = 0; i < 4; i++) {

			if (classroom.get(setting).getProblem().getPossibleAnswers().get(i) != null) {
				if (playerR.intersects(answerRect.get(i))) {
					if (classroom.get(setting).getCorrectAnswer() == i) {
						classroom.get(setting).changeClassToFinished();
						setting = -3;
						firstSetNot0 = true;
						player.setX(map.returnStartPointX());
						player.setY(map.returnStartPointY());

						break;

					} else if (classroom.get(setting).getLives() == 1) {
						classroom.get(setting).removeLives();
						setting = -1;

						break;

					} else if (classroom.get(setting).getLives() == 2) {
						player.setX(classroom.get(i).startPointX());
						player.setY(classroom.get(i).startPointY());
						classroom.get(setting).removeLives();
						classroom.get(setting).removeAnswer(i);

						firstSetNot0 = true;
						break;

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
		if (setting >= 0 && firstSetNot0 == false) {
			for (int i = 0; i < zombies.size(); i++) {
				if (zombies.get(i).isTouching(player)) {
					setting = -1;

				}
			}
		}

	}

	private void setting4() {

		classroom.get(setting).draw(this);
		image(computer, 270, 45, 75, 75);
		player.draw(this);
		ArrayList<Point> zombieLocations = new ArrayList<Point>();
		zombieLocations.add(new Point(40, 0));
		zombieLocations.add(new Point(40, 400));
		zombieLocations.add(new Point(530, 400));
		zombieLocations.add(new Point(530, 0));

		if (timer1 > 15) {
			if (timer >= 19) {
				timer1 = 0;
			}
			fill(255, 0, 0);
			rect(0, 0, 800, 600);
		}

		if (first) {
			File f = new File("images/sound1.wav");
			AudioInputStream audioStream = null;
			try {
				audioStream = AudioSystem.getAudioInputStream(f);
			} catch (UnsupportedAudioFileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Clip clip = null;
			try {
				clip = AudioSystem.getClip();
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				clip.open(audioStream);
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			clip.start();
			first = false;
		}
		if (!first) {
			time++;
		}
		if (time > 100) {
			time = 0;
			first = true;
		}

		if (firstSetNot0) {
			for (int i = 0; i < zombies.size(); i++) {
				zombies.get(i).makeHidden();
				int randomZombie = (int) (Math.random() * (4 - i));
				Point p = zombieLocations.get(randomZombie);
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
						break;
					} else if (classroom.get(setting).getLives() == 1) {
						classroom.get(setting).removeLives();
						setting = -1;
						break;

					} else if (classroom.get(setting).getLives() == 2) {
						player.setX(classroom.get(i).startPointX());
						player.setY(classroom.get(i).startPointY());
						classroom.get(setting).removeLives();
						classroom.get(setting).removeAnswer(i);
						firstSetNot0 = true;
						break;

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

		if (setting >= 0 && firstSetNot0 == false) {
			for (int i = 0; i < zombies.size(); i++) {
				if (zombies.get(i).isTouching(player)) {
					setting = -1;

				}
			}
		}
		if (setting != -2) {

		}

		timer1++;
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

		// History
		ArrayList<Problem> problems2 = new ArrayList<Problem>(); // C2
		ArrayList<String> possibleAnswers2 = new ArrayList<String>();
		possibleAnswers2.add("Alexander Hamilton");
		possibleAnswers2.add("Thomas Jefferson");
		possibleAnswers2.add("James Madison");
		possibleAnswers2.add("Aaron Burr");
		Problem p2 = new Problem(possibleAnswers2, 1, "Who was the first \n Secretary of State?");
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

		ArrayList<String> possibleAnswers3c = new ArrayList<String>();
		possibleAnswers3c.add("Change in energy");
		possibleAnswers3c.add("F x d");
		possibleAnswers3c.add("Both answers");
		possibleAnswers3c.add("Neither answer");
		Problem p3c = new Problem(possibleAnswers3c, 2, "Which of the following \n is an expression \n for work?");
		problems3.add(p3c);

		ArrayList<String> possibleAnswers3d = new ArrayList<String>();
		possibleAnswers3d.add("W");
		possibleAnswers3d.add("hp");
		possibleAnswers3d.add("J/s");
		possibleAnswers3d.add("Kg*m^2/s");
		Problem p3d = new Problem(possibleAnswers3d, 3, "Which of the following \n is not a unit \n of power?");
		problems3.add(p3d);

		ArrayList<String> possibleAnswers3e = new ArrayList<String>();
		possibleAnswers3e.add("6.67 x 10^-11");
		possibleAnswers3e.add("9.81");
		possibleAnswers3e.add("1.602176634 x 10−19");
		possibleAnswers3e.add("1.380649	x 10−23");
		Problem p3e = new Problem(possibleAnswers3e, 0, "What is the value of G?");
		problems3.add(p3e);

		classroom.add(new Classroom(problems3, classImg));

		// Business

		ArrayList<Problem> problems4 = new ArrayList<Problem>(); // C4
		ArrayList<String> possibleAnswers4 = new ArrayList<String>();
		possibleAnswers4.add("My Right Plan");
		possibleAnswers4.add("Materials Requirement \n Planning");
		possibleAnswers4.add("Manufacturing Revenue Plus");
		possibleAnswers4.add("Marginal Rate of Principal");
		Problem p4 = new Problem(possibleAnswers4, 1, "What is MRP? ");
		problems4.add(p4);

		ArrayList<String> possibleAnswers4b = new ArrayList<String>();
		possibleAnswers4b.add("A business taking a risk \n  to make a profit");
		possibleAnswers4b.add("A program administered by \n the Government");
		possibleAnswers4b.add("People in business trying \n to make a profit");
		possibleAnswers4b.add("A business adventure or \n undertaking");
		Problem p4b = new Problem(possibleAnswers4b, 0, "Define Free Enterprise");
		problems4.add(p4b);

		ArrayList<String> possibleAnswers4c = new ArrayList<String>();
		possibleAnswers4c.add("Money available to invest");
		possibleAnswers4c.add("Prohibitive cost of entry");
		possibleAnswers4c.add("A guarantee that a \n company will be successful");
		possibleAnswers4c.add("The building in which \n the legislative body \n of government meets");
		Problem p4c = new Problem(possibleAnswers4c, 0, "What is Capital");
		problems4.add(p4c);

		ArrayList<String> possibleAnswers4d = new ArrayList<String>();
		possibleAnswers4d.add("Price");
		possibleAnswers4d.add("Number");
		possibleAnswers4d.add("Less expensive \nsupply sources");
		possibleAnswers4d.add("All of the above");
		Problem p4d = new Problem(possibleAnswers4d, 3, "What could you change \n to adjust your \n profit? ");
		problems4.add(p4d);

		ArrayList<String> possibleAnswers4e = new ArrayList<String>();
		possibleAnswers4e.add("Consumer shopping");
		possibleAnswers4e.add("A warranty");
		possibleAnswers4e.add("Impulse buying");
		possibleAnswers4e.add("Comparison shopping");
		Problem p4e = new Problem(possibleAnswers4e, 2, "Making a purchase you \n had not planned is \n called:");
		problems4.add(p4e);

		classroom.add(new Classroom(problems4, classImg));

		// Final Java
		ArrayList<Problem> problems5 = new ArrayList<Problem>(); // C5
		ArrayList<String> possibleAnswers5 = new ArrayList<String>();
		possibleAnswers5.add("30 % 3");
		possibleAnswers5.add("(int)2.9");
		possibleAnswers5.add("Math.sqrt(25)");
		possibleAnswers5.add("2(3)");
		Problem p5 = new Problem(possibleAnswers5, 3, "Which expression is \n illegal?");
		problems5.add(p5);

		ArrayList<String> possibleAnswers5b = new ArrayList<String>();
		possibleAnswers5b.add("Dog extends Chihuahua");
		possibleAnswers5b.add("Dog has a Chihuahua");
		possibleAnswers5b.add("Chihuahua extends Dog");
		possibleAnswers5b.add("Chihuahua has a Dog");
		Problem p5b = new Problem(possibleAnswers5b, 2,
				"What should be the relationship \n between a Dog class and a \n Chihuahua class?");
		problems5.add(p5b);

		ArrayList<String> possibleAnswers5c = new ArrayList<String>();
		possibleAnswers5c.add("1");
		possibleAnswers5c.add("2");
		possibleAnswers5c.add("6");
		possibleAnswers5c.add("12/7");
		Problem p5c = new Problem(possibleAnswers5c, 0,
				"int[][] grid = new int[x][2*x/7];\n What is the value of \n grid[0].length()\n if x = 6");
		problems5.add(p5c);

		ArrayList<String> possibleAnswers5d = new ArrayList<String>();
		possibleAnswers5d.add("!A && !B");
		possibleAnswers5d.add("!A || !B");
		possibleAnswers5d.add("A || B");
		possibleAnswers5d.add("A && B");
		Problem p5d = new Problem(possibleAnswers5d, 1,
				"Which expression is \nequivalent to the following?\n!(A && B)");
		problems5.add(p5d);

		ArrayList<String> possibleAnswers5e = new ArrayList<String>();
		possibleAnswers5e.add("(int)(Math.random()*100)");
		possibleAnswers5e.add("(int)(Math.random()*100+1)");
		possibleAnswers5e.add("(int)(Math.random())*100");
		possibleAnswers5e.add("(int)(Math.random())*100+1");
		Problem p5e = new Problem(possibleAnswers5e, 1, "Which expression returns a\n random number between 1-100?");
		problems5.add(p5e);

		classroom.add(new Classroom(problems5, classImg));
	}

	private void setting5() {

		fill(135, 206, 236);
		rect(0, 0, 800, 600);

		fill(0);
		textSize(45);
		String welcome = "Welcome to Homestead Chase";
		text(welcome, width / 2 - this.textWidth(welcome) / 2, 45);
		textSize(23);
		String rules = " You came to school 10 minutes late to find that all your \n classmates have become zombies."
				+ " The only way \n to save them is by answering questions in \n each classroom. The final classroom is special, "
				+ "\n and you can only enter it after doing the others \n first. If one of your classmates (zombies)"
				+ " \n attacks you, you die. If you get two questions \n wrong in one classroom, you die. To answer a \n"
				+ " question or enter a door simply walk over it. \n To see your progress, toggle on or off \n the green or red button in the bottom \n right of your screen. To start (or see these \n instructions again) press the i button below. \n Good luck. ";
		text(rules, width / 2 - this.textWidth(rules) / 2, 80);
		textSize(30);
		fill(255, 0, 0);
		String warning = "Note: Epilepsy Warning";
		text(warning, width / 2 - this.textWidth(warning) / 2, 560);
		textSize(13);
		image(info, xInfo, yInfo, widthInfo, heightInfo);

		if (!onInfo) {
			setting = -3;
		}

	}

	/*
	 * This method is called once after every time a mouse button is pressed.
	 * Depending on what the user presses, It could affect the visibility of the
	 * progress bar.
	 */
	public void mousePressed() {

		Rectangle r = new Rectangle(xSwitch, ySwitch, widthSwitch, heightSwitch);
		if (r.contains(mouseX, mouseY)) {
			on = !on;
		} else {
			Rectangle info = new Rectangle(xInfo, yInfo, widthInfo, heightInfo);
			if (info.contains(mouseX, mouseY)) {
				onInfo = !onInfo;
			}
		}

	}

	/*
	 * This method is called once after every time a key button is pressed.
	 */
	public void keyPressed() {
		keys.add(keyCode);
	}

	/*
	 * This method is called once after every time a key button is released.
	 */
	public void keyReleased() {
		while (keys.contains(keyCode))
			keys.remove(new Integer(keyCode));
	}

	private boolean isPressed(Integer code) {
		return keys.contains(code);
	}

}
