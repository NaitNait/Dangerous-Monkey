/**
 * PauseMenu class
 *
 * Creates the pause menu of the game containing 1 JLabel and 5 JButtons
 * This class includes an action listener, key listener, and file writer
 * 
 * @author Rehan
 * #rehan
 **/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

//#method
class PauseMenu extends JFrame implements ActionListener, KeyListener {

	//create JLabel
	JLabel lblTop = new JLabel("<html><u>GAME PAUSED</u></html>");
	
	
	//Initializing the JPanels
	JPanel top = new JPanel();
	JPanel buttons = new JPanel();
	JPanel pauseBackground = new JPanel();

	//Setting 2 lists: a JButton list and a String list to label the buttons
	JButton buttonList[] = new JButton[5];
	String buttonText[] = {"RESUME", "RESTART", "SAVE GAME", "SETTINGS", "EXIT GAME"};
	
	static boolean pause = false;
	
	/**
	 * Main Constructor
	 * Constructs the menu with an ImageIcon on the top, with JButtons
	 * in a GridLayout 
	 *
	 * @param none
	 **/
	public PauseMenu() {
		super("Asteroids");
		
		//Configures each JButton and adds it to the button list
		for(int i = 0; i < buttonList.length;i++) {
			buttonList[i] = new JButton(buttonText[i]);
			buttonList[i].setPreferredSize(new Dimension(200, 40));
			buttonList[i].setFocusPainted(false);
			buttonList[i].setOpaque(false);
			buttonList[i].setContentAreaFilled(false);
			buttonList[i].setBorderPainted(false);
			buttonList[i].addActionListener(this);
			buttonList[i].setFocusable(false);
			buttons.add(buttonList[i]);
		}//end for
		
		pauseBackground.setLayout(new BoxLayout(pauseBackground, BoxLayout.Y_AXIS));

		top.add(lblTop);
		buttons.setLayout(new GridLayout(5, 1, 10, 10));
		buttons.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		pauseBackground.add(top);
		pauseBackground.add(buttons);

		add(pauseBackground);
		addKeyListener(this);
		setVisible(true);
		setResizable(false);
		pack();

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}//end PauseMenu constructor

	//#action
	public void actionPerformed(ActionEvent e) {
		if (buttonList[0] == e.getSource()) {
			pause = !pause;
			dispose();
		}//end if
		
		if (buttonList[1] == e.getSource()) {
			dispose();
			
			//resetting all variables to their original
			Main.shipX = new int[] {400, 400, 408, 420, 428, 428, 420, 420, 408, 408};
			Main.shipY = new int[] {300, 320, 328, 328, 320, 300, 300, 320, 320, 300};
			Main.backgroundX = -159;
			Main.backgroundY = -1105;
			Main.averageX = 0.0;
			Main.averageY = 0.0;
			Main.angle = 0.0;
			
			Main.pnlGraphics.repaint();
		}//end if
		
		if (buttonList[2] == e.getSource()) {
			JFileChooser chooseFile = new JFileChooser();
			chooseFile.setDialogTitle("Specify a file to save");

			//#save
			int userSelection = chooseFile.showSaveDialog(this);
			if (userSelection == JFileChooser.APPROVE_OPTION) {
				
				//Attempt to save to the file
				//#error
				File file = chooseFile.getSelectedFile();
				try {
					FileWriter fw = new FileWriter(file);
					PrintWriter pw = new PrintWriter(fw);
					for (int i  = 0; i < Main.shipX.length; i++) {
						pw.print(Main.shipX[i] + " ");
						pw.println(Main.shipY[i]);
					}//end for
					pw.println(Main.angle);
					pw.println(Main.backgroundX + " " + Main.backgroundY);
					pw.println(Main.cheatEnabled);
					
					pw.close();
				} catch (IOException x) {
					System.out.println("The following problem writing to a file occurred:\n" + x);
				}//end try/catch
			}//end if
		}//end if
		
		if (buttonList[3] == e.getSource()) {
			new Settings();
		}//end if
		
		if (buttonList[4] == e.getSource()) {
			System.exit(0);
		}//end if
		
	}//end actionPerformed
	
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_P || e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			pause = !pause;
			dispose();
		}//end if
	}//end keyReleased
	
	public void keyTyped(KeyEvent e) {}
	public void keyPressed(KeyEvent e) {}
}
