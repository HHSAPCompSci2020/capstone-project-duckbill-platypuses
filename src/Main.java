import java.awt.Dimension;

import javax.swing.JFrame;

import processing.awt.PSurfaceAWT;
import processing.core.PApplet;

/**
 * The main class that runs the program.
 * 
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

		window.setSize(800, 600);
		window.setMinimumSize(new Dimension(800,600));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);

		window.setVisible(true);
		canvas.requestFocus();
	}
	
}

