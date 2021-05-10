import processing.core.PApplet;
import processing.core.PImage;

/**
 * The player class is a person, which moves through user interactions.
 *  * 
 * @author Ido Ofek
 * @version 05/06/2021
 */
public class Player extends Person {

	//boolean Hall = true; 
	boolean map = true;
	//field, size of class,
	double roomX;
	double roomY;
	double roomHeight;
	double roomWidth;


	public Player(int x, int y, PImage img, boolean shown) {
		super(x, y, img, shown);

		//roomX;
		//roomY;
		//roomHeight;
		//roomWidth;
	}


	//draw method(){
	public void draw(PApplet marker) {
		super.draw(marker);

		if(marker.keyPressed) {

			if(marker.key == marker.CODED && marker.keyCode == marker.UP) {
				if(!(super.getY() < 2)) {
					super.setY(super.getY() - 1);
				}

			}else if(marker.key == marker.CODED && marker.keyCode == marker.DOWN) {
				if(!(super.getY() + super.getHeight() > roomHeight - 2)) {
					super.setY(super.getY() + 1);
				}

				if(marker.key == marker.CODED && marker.keyCode == marker.LEFT) {

					if(!(super.getX() < 2)) {
						super.setX(super.getX() - 1);
					}


				}else if(marker.key == marker.CODED && marker.keyCode == marker.RIGHT) {
					if(!(super.getX() + super.getWidth() > roomWidth - 2)) {
						super.setX(super.getX() + 1);
					}


				}

			}


			/*
	if(marker.keyPressed) {

		if(marker.key == marker.CODED && marker.keyCode == marker.UP) {
		{if class{ can up in class (before you move to a certain location check wherther you are in a class or a map and see if you are going to go out of bounds. Leave it blank if you are on the edge )
			y--;
		}else if(marker.key == marker.CODED && marker.keyCode == marker.DOWN) {
			y++;
		}
		fly(background.getGrassY(), marker);

		if(marker.key == marker.CODED && marker.keyCode == marker.LEFT) {
			x--;
}	
}
			 */
		}
	}
}

