import java.util.ArrayList;

/**
 * The Problem class represents a problem that is given in a class.
 * The class holds the question and answer of the problem.
 *  * 
 * @author Ophir Peleg
 * @version 05/06/2021
 */

public class Problem {

	
	private ArrayList<String> possibleAnswers;
	private int correctAnswer;
	private String question;
	
	// Constructor
	public Problem(ArrayList<String> answers, int answer, String question) {
		possibleAnswers = answers;
		correctAnswer = answer;
		this.question = question;
	}
	
	// Setters
	public void setPossibleAnswers(ArrayList<String> answers) {
		possibleAnswers = answers;
	}
	
	public void setCorrectAnswer(int index) {
		correctAnswer = index;
	}
	
	public void setQuestion(String question) {
		this.question = question;
	}
	
	// Getters
	public ArrayList<String> getPossibleAnswers() {
		return possibleAnswers;
	}
	
	public int getCorrectAnswer() {
		return correctAnswer;
	}
	
	public String getQuestion() {
		return question;
	}
	
	
	
	
	
	
	
	//returnAnswer ()
	
	//get + set methods for all fields
	
}
