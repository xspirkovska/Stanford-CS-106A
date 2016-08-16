/*
 * File: Pyramid.java
 * Name: 
 * Section Leader: 
 * ------------------
 * This file is the starter file for the Pyramid problem.
 * It includes definitions of the constants that match the
 * sample run in the assignment, but you should make sure
 * that changing these values causes the generated display
 * to change accordingly.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Pyramid extends GraphicsProgram {

	/** Width of each brick in pixels */
	private static final int BRICK_WIDTH = 50;

	/** Width of each brick in pixels */
	private static final int BRICK_HEIGHT = 25;

	/** Number of bricks in the base of the pyramid */
	private static final int BRICKS_IN_BASE = 100;
	
	public void run() {
		drawPyramid();
	}
	private void drawPyramid() {
	
		for (int row = 0; row < BRICKS_IN_BASE; row++ ) {
			int bricksInRow = BRICKS_IN_BASE - row;
			
			for (int brickNum = 0; brickNum < bricksInRow; brickNum++ )  {
				int x = ( getWidth()/2 ) - (BRICK_WIDTH * bricksInRow) / 2 + brickNum * BRICK_WIDTH;
				int y = getHeight() - BRICK_HEIGHT * (row+1);
				
				GRect brick = new GRect( x , y , BRICK_WIDTH , BRICK_HEIGHT );
				add(brick);
			}
		}
	}
}


