import java.awt.Point;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * The classroom class represents a class where the user will complete a challenge. 
 *  * 
 * @author Ido Ofek
 * @version 05/06/2021
 */
public class Classroom {

	boolean finished;
	Problem randomProblem;
	PImage classImage;
	double width;
	double height;
	int lives = 2;
	
	public Classroom(ArrayList<Problem> problems, PImage image) {
		
		randomProblem = problems.get((int) (Math.random() * 1));
		classImage = image;
		finished = false;
		width = 800;
		height = 600;
	}
	
	public void draw(PApplet marker){
		
		marker.image(classImage, 0,0,800,600);
		
		//Question
		marker.text(randomProblem.getQuestion(), (float)(width/2), (float)(height /8));	
	
		//Answer 1
		if(randomProblem.getPossibleAnswers().get(0) != null) {
			marker.text(randomProblem.getPossibleAnswers().get(0), (float)200, (float)200);
		}
			
		//Answer 2
		if(randomProblem.getPossibleAnswers().get(1) != null) {
			marker.text(randomProblem.getPossibleAnswers().get(1), (float)200, (float)1400 );	
		}
		
		//Answer 3
		if(randomProblem.getPossibleAnswers().get(2) != null) {
			marker.text(randomProblem.getPossibleAnswers().get(2), (float)1400, (float)200);	
		}
		
		//Answer 4
		if(randomProblem.getPossibleAnswers().get(3) != null) {
			marker.text(randomProblem.getPossibleAnswers().get(3), (float)1400, (float)1400);	
		}
		
	}

	public boolean isFinished() {
		return finished;
	}

	public int startPointX() {
		return (int)(width / 4);
	}

	public int startPointY() {
		return (int) (height / 2);
	}

	public ArrayList<Point> answerLocations() {
		ArrayList<Point> locations = new ArrayList<>();
		locations.add(new Point(200,200));
		locations.add(new Point(200,1400));
		locations.add(new Point(1400,200));
		locations.add(new Point(1400,1400));
		return locations;
	}

	public void changeClassToFinished() {
		finished = true;
	}

	public int getLives() {
		return lives;
	}

	public void removeLives() {
		lives--;
		
	}

	public int getCorrectAnswer() {
		return randomProblem.getCorrectAnswer();
	}
	
	public void removeAnswer(int remove) {
		randomProblem.getPossibleAnswers().set(remove, null);
	}
	
/*	
 * 
 *

	
	
}

	changeClassToFinished(){
		Changes finished to true;
	}
	
	isFinished{
	   returns finished
	}
	
	*/
	
}
