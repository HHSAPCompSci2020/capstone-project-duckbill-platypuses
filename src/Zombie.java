import java.awt.Rectangle;

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
		moveX((p.getX() - getX())/4);
		moveY((p.getY() - getY())/4);
		

	}
	
	public void makeShown() {
		shown = true;
	}
	
	public boolean isShown() {
		return shown;
	}
	
	public boolean isTouching(Player p) {
		
		Rectangle playerR = new Rectangle(p.getX(), p.getY(), p.getWidth(), p.getHeight());
		
		
		if (playerR.contains(getX(), getY()) ||
			playerR.contains(getX() + getWidth(), getY()) ||
			playerR.contains(getX(), getY() + getHeight()) ||
			playerR.contains(getX() + getWidth(), getY() + getHeight()) ) {
			
			return true;
			
			
		}
		
		else {
			return false;
		}
		
	}
	
	public void spawnLoc(Player p) {
		Rectangle r = new Rectangle(p.getX()-30, p.getY()-30, 60, 60);
		for (int i = 0; i < 1; i ++) {
			int xZom = (int)Math.random()*800;
			int yZom = (int)Math.random()*400;
			if (!r.contains(xZom, yZom)) {
			setX(xZom);
			setY(yZom);
			return;
			}
			else {
				i--;
			}
		}
	}
	

}
