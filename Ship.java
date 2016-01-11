/**
 * Ship class
 *
 * Creates and displays the ship
 * This class includes Arrays and a key listener
 * 
 * @author Rehan Hajee
 **/
 
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;

public class Ship extends JFrame implements KeyListener {
	
	//setting coordinates of the ship from top left going counter-clockwise
	static int shipX[] = {20, 20, 28, 40, 48, 48, 40, 40, 28, 28};
	static int shipY[] = {60, 80, 88, 88, 80, 60, 60, 80, 80, 60};
	
	static double averageX = 0.0;
	static double averageY = 0.0;
	
	//angle of rotation for the ship
	static double angle = 0.0;
	
	DrawPanel pnlGraphics = new DrawPanel();
	
	//setting ship constructor
	public Ship() {
		super("Asteroids");
		
		pnlGraphics.setPreferredSize(new Dimension(800, 500));
		
		addKeyListener(this);
		
		add(pnlGraphics);
		setVisible(true);
		setResizable(false);
		
		pack();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}//end ship constructor
	
	static class DrawPanel extends JPanel {
		
		public DrawPanel() {
			repaint();		
		}
		@Override
		public void paintComponent (Graphics g) {
			super.paintComponent(g);
			
			//resetting averageX and averageY to zero
			averageX = averageY = 0.0;
			for (int i = 0; i < shipX.length;i++){
				averageX += shipX[i];
				averageY += shipY[i];
			}//end for
			
			averageX /= 10;
			averageY /= 10;
			
			Graphics2D g2 = (Graphics2D) g;
			//save current rotation setting
			AffineTransform old = g2.getTransform();
			AffineTransform afx = new AffineTransform();
			//rotate shape certain number of degrees around a certain point
			afx.setToRotation(Math.toRadians(angle), averageX, averageY);
			g2.setTransform(afx);
			g.setColor(Color.blue);
			g2.fillPolygon(shipX, shipY, 10);
			System.out.println(angle + " sin θ = " + Math.sin(Math.toRadians(angle)));
			//restore old rotation seting
			g2.setTransform(old);
			// everything after this is not rotated
			
		}//end paintComponent
	}//end DrawPanel
	
	public void keyReleased(KeyEvent e) {
		//if letter p is pressed amd released
		if (e.getKeyCode() == KeyEvent.VK_P){
			new PauseMenu();
		}//end if
	}//end keyReleased
	public void keyTyped(KeyEvent e) {}
	public void keyPressed(KeyEvent e) {
		//if arrow up is pressed
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			if (angle % 360 == 0){ //if the angle is divisible by 360
				for (int i = 0; i < shipX.length; i++) {
				shipY[i] -= 10;
				}//end for loop
			} else if (Math.cos(Math.toRadians(angle+90)) >= 0) { //else if cos(angle+90) is greater than or equal to zero
				angle += 7.5;
			} else { //else if cos(angle+90) is less than zero
				angle -= 7.5;
			}//end if/else
			pnlGraphics.repaint();
		}//end if
		//if arrow down is pressed
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			
			if ((angle-180) % 360 == 0){ //if the angle minus 180 is divisible by 360
				for (int i = 0; i < shipX.length; i++) {
				shipY[i] += 10;
				}
			} else if (Math.cos(Math.toRadians(angle+90)) >= 0) { //else if cos(angle+90) is greater than or equal to zero
				angle -= 7.5;
			} else { //else if cos(angle+90) is less than zero
				angle += 7.5;
			}//end if/else
			
			pnlGraphics.repaint();
		}//end if
		//if right arrow is pressed
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			
			if ((angle - 90) % 360 == 0) { //if the angle minus 90 is divisible by 360
				for (int i = 0; i < shipX.length; i++) {
					shipX[i] += 10;
				}
			}  else if (Math.sin(Math.toRadians(angle+90)) >= 0) { //else if sin(angle+90) is greater than or equal to zero
				angle += 7.5;
			} else if (Math.sin(Math.toRadians(angle+90)) < 0 ) { //else if sin(angle+90) is less than zero
				angle -= 7.5;
			}//end if/else
			
			pnlGraphics.repaint();
			
		}//end if
		//if left arrow is pressed
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			if ((angle - 270) % 360 == 0) {
				for (int i = 0; i < shipX.length; i++) {
					shipX[i] -= 10;
				}
			} else if (Math.sin(Math.toRadians(angle+90)) >= 0 ){ //else if sin(angle+90) is greater than or equal to zero
				angle -= 7.5;
			} else if (Math.sin(Math.toRadians(angle+90)) < 0){ //else if sin(angle+90) is less than zero
				angle += 7.5;
			}//end if/else
			pnlGraphics.repaint();
		}//end if
		
	}//end keyPressed
}//end Ship
