import java.awt.Point;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * The classroom class represents a class where the user will answer a question
 * that is written on the board.
 * 
 * @author Ido Ofek
 * @version 05/06/2021
 */
public class Classroom {

	private boolean finished;
	private Problem randomProblem;
	private PImage classImage;
	private double width;
	private double height;
	private int lives = 2;

	/**
	 * Initializes a classroom, which will is where the user has to answer a
	 * question.
	 * 
	 * @param problems An ArrayList of possible problems that the user can choose
	 *                 from.
	 * @param image    An image that the classroom is set to.
	 */
	public Classroom(ArrayList<Problem> problems, PImage image) {

		randomProblem = problems.get((int) (Math.random() * problems.size()));
		//random choice spot, keep as field
		classImage = image;
		finished = false;
		width = 800;
		height = 600;
	}

	/*
	 * Draws the classroom to the given Processing PApplet.
	 * 
	 * @param marker The Processing PApplet on which to draw the classroom.
	 * 
	 * @pre The classroom will be drawn with attributes previously set on the given
	 * PApplet.
	 */
	public void draw(PApplet marker) {

		marker.image(classImage, 0, 0, 800, 600);

		marker.text(randomProblem.getQuestion(), (float) (width / 2), (float) (height / 8));
		marker.fill(0, 0, 0);
		//Randomize answer by using random fields initialzed in constructor
		if (randomProblem.getPossibleAnswers().get(0) != null) {
			marker.text(randomProblem.getPossibleAnswers().get(0), (float) 170, (float) 185);
		}
		if (randomProblem.getPossibleAnswers().get(1) != null) {
			marker.text(randomProblem.getPossibleAnswers().get(1), (float) 170, (float) 490);
		}
		if (randomProblem.getPossibleAnswers().get(2) != null) {
			marker.text(randomProblem.getPossibleAnswers().get(2), (float) 640, (float) 185);
		}
		if (randomProblem.getPossibleAnswers().get(3) != null) {
			marker.text(randomProblem.getPossibleAnswers().get(3), (float) 640, (float) 490);
		}
	}

	/*
	 * Returns if the class has already been finished.
	 * 
	 * @return True if the class is finished and false if the class isn't finished.
	 */
	public boolean isFinished() {
		return finished;
	}

	/*
	 * Returns the x value of where the person should be when he enters a classroom.
	 * 
	 * @return The starting x value for the player when he goes in a class.
	 */
	public int startPointX() {
		return (int) (350);
	}

	/*
	 * Returns the y value of where the person should be when he enters a classroom.
	 * 
	 * @return The starting y value for the player when he goes in a class.
	 */
	public int startPointY() {
		return (int) (260);
	}

	/**
	 * The method adds points that have the x and y values of the middle of the
	 * answer boxes to an ArrsyList and returns it.
	 * 
	 * @return An ArrayList of points containing the x and y coordinates of the
	 *         middle of the answer boxes.
	 */
	public ArrayList<Point> answerLocations() {
		ArrayList<Point> locations = new ArrayList<>();
		locations.add(new Point(170, 185));
		locations.add(new Point(170, 490));
		locations.add(new Point(640, 185));
		locations.add(new Point(640, 490));
		return locations;
	}

	/**
	 * Changes the class to finished.
	 * 
	 */
	public void changeClassToFinished() {
		finished = true;
	}

	/**
	 * Returns the amount of lives that the person has left in the classroom.
	 * 
	 * @return The amount of lives that the person had left in the classroom.
	 */
	public int getLives() {
		return lives;
	}

	/**
	 * Removes one life from the amount lives that the person has in the classroom.
	 * 
	 */
	public void removeLives() {
		lives--;

	}

	/**
	 * Returns the index of the correct answer from the problem given to the
	 * classroom..
	 * 
	 * @return The index of the correct answer from the problem given to the
	 *         classroom.
	 */
	public int getCorrectAnswer() {
		return randomProblem.getCorrectAnswer();
	}

	/**
	 * Sets the answer of the given index to null.
	 * 
	 * @param remove The index of the answer to remove.
	 */
	public void removeAnswer(int remove) {
		randomProblem.getPossibleAnswers().set(remove, null);
	}
	
	public Problem getProblem() {
		return randomProblem;
	}

}
