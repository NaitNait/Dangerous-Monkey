/***
 * DrawPanel class
 *  
 * All things to be drawn go through this class
 *
 * @author Rehan
 * 
 */
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.io.*;
import javax.swing.*;

//#method
public class DrawPanel extends JPanel {
	
	Asteroid_Small1 asteroid;
	public DrawPanel() {
		System.out.println("Before1");
		asteroid = new Asteroid_Small1(45.0, 200.0, 200.0, 5);
		System.out.println("After1");
		repaint();
		
		double x = 0.0;
	}
	
	//#rehan
	public void paintComponent (Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(Main.backgroundImg, Main.backgroundX, Main.backgroundY, null);
		
		//if the game is not paused
		if (!PauseMenu.pause) {
			Main.mouseX = (int) MouseInfo.getPointerInfo().getLocation().x;			
			Main.mouseY = (int) MouseInfo.getPointerInfo().getLocation().y;
			//if mouse is out of the window
			if (Main.mouseX >= getWidth() - 10)
				Main.mouseX = getWidth() - 10 ;
			if (Main.mouseY >= getHeight() - 10)
				Main.mouseY = getHeight() - 10;
		}//end if
		
		//recalculating Main.averageXand Main.averageY
		Main.averageX = Main.averageY = 0.0;
		for (int i = 0; i < Main.shipX.length;i++) {
			Main.averageX += Main.shipX[i];
			Main.averageY += Main.shipY[i];
		}//end for
		Main.averageX/= 10;
		Main.averageY /= 10;
		
		Graphics2D g2 = (Graphics2D) g;
		//save current rotation setting
		AffineTransform old = g2.getTransform();
		AffineTransform afx = new AffineTransform();
		//rotate shape certain number of degrees around a certain point
		afx.setToRotation(Math.toRadians(Main.angle), Main.averageX, Main.averageY);
		g2.setTransform(afx);
		g.setColor(Main.shipColor);
		g2.fillPolygon(Main.shipX, Main.shipY, 10);
		
		//#cheat
		if (Main.cheatEnabled) {
			System.out.println("Mouse: "+ Main.mouseX + ", " + Main.mouseY);
			System.out.println("backgroundX = " + Main.backgroundX + "\nbackgroundY = " + Main.backgroundY);
			System.out.print("\nMainX: ");
			for (int i = 0; i < Main.shipX.length; i++) {
				System.out.print(Main.shipX[i] + " ");
			}
			System.out.print("\nMainY: ");
			for (int i = 0; i < Main.shipY.length; i++) {
				System.out.print(Main.shipY[i] + " ");
			}
			System.out.println("\n");
			System.out.println("Angle = " + Main.angle + " sin (" + (Main.angle) + ") = " + Math.sin(Math.toRadians(Main.angle)));
			System.out.println("Angle = " + Main.angle + " cos (" + (Main.angle) + ") = " + Math.cos(Math.toRadians(Main.angle)));
			System.out.println("\n\n");
		}//end if
		
		//restore old rotation seting
		g2.setTransform(old);
		// everything after this is not rotated
		System.out.println("Before");
		asteroid.draw(g);
		System.out.println("After");
		//System.out.println(Asteroid_Small1.Small_Asteroid.x + ", " + Asteroid_Small1.Small_Asteroid.y);
	}//end paintComponent
	
	//#static
	//#tiantian
	public static void drawObj(Graphics g){
		Graphics2D gg = (Graphics2D) g;
		g.setColor(Color.black);
		gg.setStroke(new BasicStroke(4));
		//int[] a = {(int)Asteroid_Small1.Small_Asteroid.x + 30, (int)Asteroid_Small1.Small_Asteroid.x, (int)Asteroid_Small1.Small_Asteroid.x + 15, (int)Asteroid_Small1.Small_Asteroid.x + 35, (int)Asteroid_Small1.Small_Asteroid.x + 50, (int)Asteroid_Small1.Small_Asteroid.x + 40}; 
		//int[] b = {(int)Asteroid_Small1.Small_Asteroid.y, (int)Asteroid_Small1.Small_Asteroid.y + 18, (int)Asteroid_Small1.Small_Asteroid.y + 40, (int)Asteroid_Small1.Small_Asteroid.y + 45, (int)Asteroid_Small1.Small_Asteroid.y + 35, (int)Asteroid_Small1.Small_Asteroid.y + 20}; 
		
		//gg.drawPolygon(a, b, 6);
		
	}
	
}//end DrawPanel
