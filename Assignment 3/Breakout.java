/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

	/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

	/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

	/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

	/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

	/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

	/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

	/** Separation between bricks */
	private static final int BRICK_SEP = 4;

	/** Width of a brick */
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

	/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

	/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

	/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

	/** Number of turns */
	private static final int NTURNS = 3;
	
	/** Total number of bricks */
    private static final int TOTAL_BRICKS = NBRICK_ROWS * NBRICKS_PER_ROW;
	
	/** Method: run() - Runs the Breakout program. */
	public void run() {
		addMouseListeners();
		addMessage("Welcome. You have " + NTURNS + " turns to break out. Click to START!");
		setupGame();
		playGame();
	}

	private void setupGame() {
		waitForClick();
		msg.setVisible(false);
		addBricks();
		addPaddle();
		addBall();
	}
	
	private void addBricks() {
		// local variables
        int x = BRICK_SEP/2;
        int y = BRICK_Y_OFFSET;
    
        for (int i = 0; i < NBRICK_ROWS; i++){
            for (int j = 0; j < NBRICKS_PER_ROW; j++){
                brick = new GRect (x, y, BRICK_WIDTH, BRICK_HEIGHT);
                brick.setFilled(true);
                
                if (i < 2) {
                	brick.setFillColor(Color.RED);
                }
                if (i >= 2 && i < 4) {
                	brick.setFillColor(Color.ORANGE);
                }
                if (i >= 4 && i < 6) {
                	brick.setFillColor(Color.YELLOW);
                }
                if (i >= 6 && i < 8) {
                	brick.setFillColor(Color.GREEN);
                }
                if (i >= 8 && i < 10) {
                	brick.setFillColor(Color.CYAN);
                }
 
                add(brick); 
                x+= BRICK_WIDTH+BRICK_SEP; // increases the x coordinate by the width of brick + the brick separation 
            }
            y+= BRICK_HEIGHT+BRICK_SEP; // coordinate y jumps one row down
            x = BRICK_SEP/2; // coordinate x back to its original spot
        }
	}
	
	private void addPaddle() {
		// local variables
        int x = (getWidth()/2) - (PADDLE_WIDTH/2);
        int y = getHeight() - PADDLE_Y_OFFSET;
   
        paddle = new GRect (x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
        paddle.setFilled(true);
        add(paddle);
	}
	
	private void addBall() {
		// local variables
		int x = getWidth()/2 - BALL_RADIUS;
		int y = getHeight()/2 - BALL_RADIUS;
		
		ball = new GOval(x, y, BALL_RADIUS, BALL_RADIUS);
		ball.setFilled(true);
		add(ball);
	}
	
	public void mouseMoved(MouseEvent e) {
		// local variables
		int x = e.getX() - PADDLE_WIDTH/2;
		int y = (int) paddle.getY();
		
		paddle.setLocation(x, y);
		
		if (paddle.getX() <= 0) {
			paddle.setLocation(0, y);
		}
		
        if (paddle.getX() + PADDLE_WIDTH >= getWidth()) {
        	paddle.setLocation(getWidth() - PADDLE_WIDTH, y);
        }
	}

	private void playGame() {
		getBallVelocity();
		while (gameCounter < NTURNS) {
			while (brickCounter < TOTAL_BRICKS) {
                moveBall();
                checkForCollisions();
                
                if (ball.getY() == getHeight() - BALL_RADIUS*2) {
                    gameCounter ++;
            		removeAll();
            		if (gameCounter != NTURNS) {
            			 addMessage("Next turn: Click to restart");
                         restartGame();
                    } else {
                    	removeAll();
                    	addMessage("Game Over!");
                    	msg.setColor(Color.RED);
                    }
                    break;
                }
                
            }
			
			if (brickCounter == TOTAL_BRICKS) {
	        	addMessage("Congrats - You won!");
			}
        }   
	}
	
	private void getBallVelocity() {
		vx = rgen.nextDouble(1.0, 3.0);
		vy = 10.0;
		
        if (rgen.nextBoolean(0.5))  {
        	vx = -vx;
        }
	}
	
	private void moveBall() {
		ball.move(vx, vy);
		
        if (ball.getX() + BALL_RADIUS * 2 >= getWidth() || ball.getX() <= 0) {
        	vx = -vx;
        }
        
        if (ball.getY() <= 0) {
			vy = -vy; 
		}
         
		pause (60);
	}
	
	 // checks if there was a collision and, if brick, removes the colliding object
    private void checkForCollisions(){
        GObject collider = getCollidingObject();
        
        if (collider != null) {
            vy = -vy; 
            
            if (collider != paddle){
            	remove(collider);
                brickCounter++;
            }
        }
    }
    
    private GObject getCollidingObject() {
    	if( (getElementAt(ball.getX(), ball.getY()) ) != null) {
	         return getElementAt(ball.getX(), ball.getY());
    	} else if (getElementAt( (ball.getX() + BALL_RADIUS*2), ball.getY()) != null ) {
	         return getElementAt(ball.getX() + BALL_RADIUS*2, ball.getY());
    	} else if(getElementAt(ball.getX(), (ball.getY() + BALL_RADIUS*2)) != null ){
	         return getElementAt(ball.getX(), ball.getY() + BALL_RADIUS*2);
    	} else if(getElementAt((ball.getX() + BALL_RADIUS*2), (ball.getY() + BALL_RADIUS*2)) != null ) {
	         return getElementAt(ball.getX() + BALL_RADIUS*2, ball.getY() + BALL_RADIUS*2);
    	} else {
    		 return null;
    	}
    }

	private void addMessage(String messageText) {
		msg = new GLabel(messageText);
		int x = getWidth()/2 - (int) msg.getWidth()/2;
		int y = getHeight()/2;
		
		add(msg, x, y);
	}
	
	private void restartGame() {
		setupGame();
		playGame();
	}
	
	// instance variables 
	private double vx, vy;
    private int gameCounter = 0;
    private int brickCounter = 0;
    private RandomGenerator rgen = new RandomGenerator();
    private GRect brick;
    private GRect paddle;
    private GOval ball;
    private GLabel msg;
}
