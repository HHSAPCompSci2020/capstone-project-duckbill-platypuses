import java.util.ArrayList;
import java.util.Collections;

/**
 * The Problem class represents a problem that is given in a class. The class
 * holds the question and answer of the problem.
 * 
 * @author Ophir Peleg
 * @version 05/06/2021
 */

public class Problem {

	private ArrayList<String> possibleAnswers;
	private int correctAnswer;
	private String question;

	/**
	 * Constructs a Problem object, that holds a question, the possible answers, and
	 * the correct answer.
	 * 
	 * @param answers  all possible answers to the problem, String ArrayList
	 * @param answer   the correct answer to the problem, int
	 * @param question the question asked in the problem, String
	 * @post possibleAnswers = answers
	 * @post correctAnswer = answer
	 * @post question = parameter question
	 */

	public Problem(ArrayList<String> answers, int answer, String question) {
		possibleAnswers = answers;
		correctAnswer = answer;
		this.question = question;
	}

	/**
	 * Fills the possibleAnswers ArrayList with the values entered in the parameter
	 * ArrayList.
	 * 
	 * @param answers all possible answers to the problem,String ArrayList
	 * @post possibleAnswers = answers
	 */

	public void setPossibleAnswers(ArrayList<String> answers) {
		possibleAnswers = answers;
	}

	/**
	 * Sets the index of the correct answer to the parameter.
	 * 
	 * @param index the index of the correct answer in the possibleAnswers
	 *              ArrayList, int
	 * @post correctAnswer = index
	 */

	public void setCorrectAnswer(int index) {
		correctAnswer = index;
	}

	/**
	 * Sets the question field to the question in the parameter.
	 * 
	 * @param question the question asked in the problem, String
	 * @post question = parameter question
	 */

	public void setQuestion(String question) {
		this.question = question;
	}

	/**
	 * Returns an ArrayList of possible answers to the problem.
	 * 
	 * @return the ArrayList of possible answers.
	 */

	public ArrayList<String> getPossibleAnswers() {
		return possibleAnswers;
	}

	/**
	 * Returns the index of the correct answer in the possibleAnswers ArrayList.
	 * 
	 * @return index of the correct answer.
	 */

	public int getCorrectAnswer() {
		return correctAnswer;
	}

	/**
	 * Returns the question of this Problem
	 * 
	 * @return the question
	 */

	public String getQuestion() {
		return question;
	}

	/**
	 * Randomizes the questions answers, and maintains the right answer.
	 * 
	 * 
	 */
	public void randomize() {

		String ans = possibleAnswers.get(correctAnswer);
		Collections.shuffle(possibleAnswers);

		for (int i = 0; i < 4; i++) {
			if (possibleAnswers.get(i).equals(ans)) {
				correctAnswer = i;
			}
		}

	}
}
