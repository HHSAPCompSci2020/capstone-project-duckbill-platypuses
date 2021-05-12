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
	
	public boolean isTouching(Player p, PApplet m) {
		
		Rectangle playerR = new Rectangle((int)p.getX(), (int)p.getY(),(int) p.getWidth(),(int) p.getHeight());
		
		m.rect((float)p.getX() + 32, (float)p.getY() + 32,(int) p.getWidth()/2, (int) p.getHeight()/2);
		
//		m.line((float)getX(), (float)getY(), (float)(getX() + getWidth()), (float)getY());
//		m.line((float)getX(), (float)getY(), (float)(getX() ), (float)( getY() + getHeight()));
//		m.line((float)(getX() + getWidth()), (float)(getY() + getHeight()), (float)(getX() + getWidth()), (float)getY());
//		m.line((float)(getX() + getWidth()), (float)(getY() + getHeight()), (float)(getX() ), (float)( getY() + getHeight()));
		
//		m.line((float)getX() + 32, (float)getY() + 32, (float)(getX() + getWidth()/2) + 32, (float)getY() + 32);
//		m.line((float)getX() + 32, (float)getY() + 32, (float)(getX() ) + 32, (float)( getY() + getHeight()/2) + 32);
//		m.line((float)(getX() + getWidth()/2) + 32, (float)(getY() + getHeight()/2) + 32, (float)(getX() + getWidth()/2) + 32, (float)getY() + 32);
//		m.line((float)(getX() + getWidth()/2) + 32, (float)(getY() + getHeight()/2) + 32, (float)(getX() ) + 32, (float)( getY() + getHeight()/2) + 32);
//		
		
		//fix this if game too hard. 
		if (playerR.contains((float)getX() + 32, (float)getY() + 32) || //Tl
			playerR.contains((float)getX() + 32 + (((float)(getX() + getWidth()/2) + 32) - ((float)getX() + 32)), (float)getY() + 32) || //TR
			playerR.contains((float)getX() + 32, (float)getY() + 32 + (((float)(getY() + getHeight()/2) + 32)-((float)getY() + 32))) || //BL
			playerR.contains((float)getX() + 32 + (((float)(getX() + getWidth()/2) + 32) - ((float)getX() + 32)), (float)getY() + 32 + (((float)(getY() + getHeight()/2) + 32)-((float)getY() + 32))) ) {
			
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
