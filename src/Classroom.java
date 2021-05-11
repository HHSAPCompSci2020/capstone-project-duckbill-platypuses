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
	String question1;
	String question2;
	String question3;
	String question4;
	double width;
	double height;
	
	public Classroom(ArrayList<Problem> problems, PImage image) {
		
		randomProblem = problems.get((int) (Math.random() * 1));
		classImage = image;
		finished = false;
		width = 1600;
		height = 1200;
	}
	
	public void draw(PApplet marker){
		
		marker.image(classImage, 0,0,1600,1200);
		
		//Question
		marker.text(randomProblem.getQuestion(), (float)(width/2), (float)(height /8));	
	
		//Answer 1
		if(randomProblem.getPossibleAnswers().get(0) != null) {
			marker.text(randomProblem.getPossibleAnswers().get(0), (float)200, (float)200);
		}
			
		//Answer 2
		if(randomProblem.getPossibleAnswers().get(0) != null) {
			marker.text(randomProblem.getPossibleAnswers().get(1), (float)200, (float)1400 );	
		}
		
		//Answer 3
		if(randomProblem.getPossibleAnswers().get(0) != null) {
			marker.text(randomProblem.getPossibleAnswers().get(2), (float)1400, (float)200);	
		}
		
		//Answer 4
		if(randomProblem.getPossibleAnswers().get(0) != null) {
			marker.text(randomProblem.getPossibleAnswers().get(3), (float)1400, (float)1400);	
		}
		
	}

	public boolean isFinished() {
		return finished;
	}

	public int startPointX() {
		return 50;
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
	
	
	
/*	
 * 
 *
 	//boolean finished
	//problem randomProb;
	//image classImage;
	//width
	//height
	
	
	
	//constructor(arraylist problem , image){
	 * randomProb = selectRandomProblem(problem)
	 * Set the rest of the fields
	 * }

	
	//draw(){
	 * 
	 * draw image to width height
	 * draw randomProb
	 * draw A
	 * draw B
	 * draw C
	 * draw D
	 * (draw all of the text answer if they aren't null)
	
}

// return locA() Return the location of rectangle A ( a point with x,y)
 *return LocB() Return the location of rectangle B ( a point with x,y)
 *return LocC() Return the location of rectangle C ( a point with x,y)
  return locD() Return the location of rectangle D ( a point with x,y)
	
	
		//start retuyrn point. (returns the x,y coordinates of where the player should spawn in when he loads into the classroom)

	
	//selectRandomProblem() (Selects a random problem from the arraykist and retuns it)
	
	changeClassToFinished(){
		Changes finished to true;
	}
	
	isFinished{
	   returns finished
	}
	
	*/
	
}
