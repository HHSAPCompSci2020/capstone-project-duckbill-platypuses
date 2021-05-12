import java.awt.Rectangle;

import processing.core.PApplet;
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
	
	public void draw(PApplet marker, Player p) {
		super.draw(marker);
		
		followPlayer(p);
	
		//System.out.println("Hello World");
		
	}
	
	public void followPlayer (Player p) {
		
		moveX((p.getX() - getX())/300);
		moveY((p.getY() - getY())/300);
		//System.out.println("Hello World");
		//setX(getX()+0.1);
	
		
		

	}
	
	public void makeShown() {
		shown = true;
	}
	
	public boolean isShown() {
		return shown;
	}
	
	public boolean isTouching(Player p) {
		
		Rectangle playerR = new Rectangle((int)p.getX(), (int)p.getY(),(int) p.getWidth(),(int) p.getHeight());
		
		
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
		Rectangle r = new Rectangle((int)p.getX()-30,(int) p.getY()-30, 60, 60);
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
