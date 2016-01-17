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
import java.awt.image.*;
import java.io.*;
import javax.swing.*;
import javax.imageio.*;

public class Ship extends JFrame implements KeyListener {
	
	//setting coordinates of the ship from top left going counter-clockwise
	//static int shipX[] = {20, 20, 28, 40, 48, 48, 40, 40, 28, 28};
	static int shipX[] = {20, 20, 28, 40, 48, 48, 40, 40, 28, 28};
	static int shipY[] = {60, 80, 88, 88, 80, 60, 60, 80, 80, 60};
	//static int shipY[] = {60, 80, 88, 88, 80, 60, 60, 80, 80, 60};
	
	static int backgroundX = -159;
	static int backgroundY = -1105;
	
	static double averageX = 0.0;
	static double averageY = 0.0;
	
	//angle of rotation for the ship
	static double angle = 0.0;
	
	DrawPanel pnlGraphics = new DrawPanel();
	
	//ImageIcon img = new ImageIcon("img\\Background.jpg");
	static BufferedImage backgroundImg = null;
	static int imgHeightAvg = 0;
	static int imgWidthAvg = 0;
	
	//setting ship constructor
	public Ship() {
		super("Asteroids");
		try {
			backgroundImg = ImageIO.read(new File("img\\Background.jpg"));
		} catch (IOException e){
			System.out.println("The following problem writing to a file occurred:\n" + e);
		}
		pnlGraphics.setPreferredSize(new Dimension(800, 600));
		
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
			
			g.drawImage(backgroundImg, backgroundX, backgroundY, null);
			//System.out.println(backgroundX + ", " + backgroundY);
			
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
			System.out.println(shipY[0] + ", " + shipY[5]);
			System.out.println(angle + " sin (" + (angle /**+ 90*/) + ") = " + Math.sin(Math.toRadians(angle /**+ 90*/ )));
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
			
			if (shipX[0] >= 7 && shipX[0] <= 777 && shipY[0] >= 2 && shipY[0] <= 582){
			
				for (int i = 0; i < shipX.length; i++) {
					shipX[i] -= Math.cos(Math.toRadians(angle + 90))*10;
					shipY[i] -= Math.sin(Math.toRadians(angle + 90))*10;
				}//end for loop
			
				backgroundX += Math.cos(Math.toRadians(angle + 90))*10;
				backgroundY += Math.sin(Math.toRadians(angle + 90))*10;

				if (Math.round(backgroundX / 10) * 10 == -30)
					backgroundX = -356;
				else if (Math.round(backgroundX / 10) * 10 == -550)
					backgroundX = -356;
				if (Math.round(backgroundY / 10) * 10 -5 == -395)
					backgroundY = -1105;
				else if (Math.round(backgroundY / 10) * 10 == -1750)
					backgroundY = -340;
			}
			else {
				if (shipX[0] < 7){
					if (Math.sin(Math.toRadians(angle)) >= 0){
						for (int i = 0; i < shipX.length; i++) {
							shipX[i] -= Math.cos(Math.toRadians(angle + 90))*10;
							shipY[i] -= Math.sin(Math.toRadians(angle + 90))*10;
						}//end for loop
			
						backgroundX += Math.cos(Math.toRadians(angle + 90))*10;
						backgroundY += Math.sin(Math.toRadians(angle + 90))*10;

						if (Math.round(backgroundX / 10) * 10 == -30)
							backgroundX = -356;
						else if (Math.round(backgroundX / 10) * 10 == -550)
							backgroundX = -356;
						if (Math.round(backgroundY / 10) * 10 -5 == -395)
							backgroundY = -1105;
						else if (Math.round(backgroundY / 10) * 10 == -1750)
							backgroundY = -340;
					}
				}
				if (shipX[0] > 777){
					if (Math.sin(Math.toRadians(angle)) <= 0){
						for (int i = 0; i < shipX.length; i++) {
							shipX[i] -= Math.cos(Math.toRadians(angle + 90))*10;
							shipY[i] -= Math.sin(Math.toRadians(angle + 90))*10;
						}//end for loop
			
						backgroundX += Math.cos(Math.toRadians(angle + 90))*10;
						backgroundY += Math.sin(Math.toRadians(angle + 90))*10;

						if (Math.round(backgroundX / 10) * 10 == -30)
							backgroundX = -356;
						else if (Math.round(backgroundX / 10) * 10 == -550)
							backgroundX = -356;
						if (Math.round(backgroundY / 10) * 10 -5 == -395)
							backgroundY = -1105;
						else if (Math.round(backgroundY / 10) * 10 == -1750)
							backgroundY = -340;
					}
				}
				if (shipY[0] < 2){
					if (Math.cos(Math.toRadians(angle)) <= 0){
						for (int i = 0; i < shipX.length; i++) {
							shipX[i] -= Math.cos(Math.toRadians(angle + 90))*10;
							shipY[i] -= Math.sin(Math.toRadians(angle + 90))*10;
						}//end for loop
			
						backgroundX += Math.cos(Math.toRadians(angle + 90))*10;
						backgroundY += Math.sin(Math.toRadians(angle + 90))*10;

						if (Math.round(backgroundX / 10) * 10 == -30)
							backgroundX = -356;
						else if (Math.round(backgroundX / 10) * 10 == -550)
							backgroundX = -356;
						if (Math.round(backgroundY / 10) * 10 -5 == -395)
							backgroundY = -1105;
						else if (Math.round(backgroundY / 10) * 10 == -1750)
							backgroundY = -340;
					}
				}
				if (shipY[0] > 582){
					if (Math.cos(Math.toRadians(angle)) >= 0){
						for (int i = 0; i < shipX.length; i++) {
							shipX[i] -= Math.cos(Math.toRadians(angle + 90))*10;
							shipY[i] -= Math.sin(Math.toRadians(angle + 90))*10;
						}//end for loop
			
						backgroundX += Math.cos(Math.toRadians(angle + 90))*10;
						backgroundY += Math.sin(Math.toRadians(angle + 90))*10;

						if (Math.round(backgroundX / 10) * 10 == -30)
							backgroundX = -356;
						else if (Math.round(backgroundX / 10) * 10 == -550)
							backgroundX = -356;
						if (Math.round(backgroundY / 10) * 10 -5 == -395)
							backgroundY = -1105;
						else if (Math.round(backgroundY / 10) * 10 == -1750)
							backgroundY = -340;
					}
				}
			}
			pnlGraphics.repaint();
		}//end if
		
		//if right arrow is pressed
		else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
		
			angle += 7.5;
			
			pnlGraphics.repaint();
			
		}//end if
		//if left arrow is pressed
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			
			angle -= 7.5;
			
			pnlGraphics.repaint();
		}//end if
	}//end keyPressed
}//end Ship
