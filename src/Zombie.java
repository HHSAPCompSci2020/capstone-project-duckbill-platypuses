import processing.core.PImage;

/**
 * The zombie class is a person, that moves by following the player. 
 *  * 
 * @author Itai Reingold Nutman
 * @version 05/06/2021
 */
public class Zombie extends Person{

	
	public Zombie(int x, int y, PImage img, boolean shown) {
		super(x, y, img, shown);	
		
	}
	
	public void followPlayer (Player p) {
//		moveX((p.getX() - getX())/2);
//		moveY((p.getY() - getY())/2);
		//do move methods to follow

	}
	
	

}
