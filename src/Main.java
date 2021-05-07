import java.awt.Dimension;

import javax.swing.JFrame;

import processing.awt.PSurfaceAWT;
import processing.core.PApplet;

/**
 * The main class that runs the program.
 *  * 
 * @author Ophir Peleg
 * @version 05/06/2021
 */
public class Main {

	public static void main(String[] args)
	{
		DrawingSurface panel = new DrawingSurface();
		PApplet.runSketch(new String[]{""}, panel);
		PSurfaceAWT surf = (PSurfaceAWT) panel.getSurface();
		PSurfaceAWT.SmoothCanvas canvas = (PSurfaceAWT.SmoothCanvas) surf.getNative();
		JFrame window = (JFrame)canvas.getFrame();

		window.setSize(500, 500);
		window.setMinimumSize(new Dimension(100,100));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(true);

		window.setVisible(true);
		canvas.requestFocus();
	}
	
}

