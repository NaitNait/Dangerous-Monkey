/**
 * MainMenu class
 *
 * Creates the main menu of the game containing 1 ImageIcon (in a JLabel) and 5 JButtons
 * This class includes an action listener, key listener, and file reader
 * 
 * #rehan
 **/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

//#method
//#main
class MainMenu extends JFrame implements ActionListener, KeyListener {

	//create the image as a JLabel
	ImageIcon asteroids = new ImageIcon("img\\Asteroids.png");
	JLabel lblTop = new JLabel(asteroids);
	
	//initializing the JPanels
	JPanel top = new JPanel();
	JPanel buttons = new JPanel();
	JPanel highScore = new JPanel();
	JPanel highScoreChart = new JPanel();
	JPanel mainMenuBackground = new JPanel();


	//initializing 2 lists: a JButton list and a String list to label the buttons 
	JButton buttonList[] = new JButton[5];
	String buttonText[] = {"START NEW GAME", "RESUME PREVIOUS GAME", "HIGH SCORES", "SETTINGS", "EXIT GAME"};
	
	JButton menuReturn = new JButton("<html><u>RETURN TO MENU</u></html>");
	
	/**
	 * Main Constructor
	 * Constructs the menu with an ImageIcon on the top, with JButtons
	 * in a GridLayout 
	 *
	 * @param none
	 **/
	public MainMenu() throws IOException {
		super("Asteroids");
		
		// Retrieve high scores from the file
		HighScore.getScores();
		
		//Configures each JButton and adds it to the button list
		for(int i = 0; i < buttonList.length;i++) {
			buttonList[i] = new JButton(buttonText[i]);
			buttonList[i].setPreferredSize(new Dimension(200, 50));
			buttonList[i].setFocusPainted(false);
			buttonList[i].setOpaque(false);
			buttonList[i].setContentAreaFilled(false);
			buttonList[i].setBorderPainted(false);
			buttonList[i].addActionListener(this);
			buttonList[i].setFocusable(false);
			buttons.add(buttonList[i]);
		}//end for loop
		
		highScoreChart.setLayout(new BoxLayout(highScoreChart, BoxLayout.Y_AXIS));
		for(int i = 0; i < HighScore.arrHighScoreList.size()/2;i++) {
			JPanel temp = new JPanel();
			temp.setLayout(new FlowLayout(FlowLayout.LEFT));
			JLabel s1 = new JLabel(Integer.toString(HighScore.arrHighScoreList.get(i).score) + " " + HighScore.arrHighScoreList.get(i).name);
			s1.setPreferredSize(new Dimension(120, 20));
			temp.add(s1);
			JLabel s2 = new JLabel(Integer.toString(HighScore.arrHighScoreList.get(i + 10).score) + " " + HighScore.arrHighScoreList.get(i + 10).name);
			temp.add(s2);
			highScoreChart.add(temp);
		}//end for
		
		menuReturn.setFocusPainted(false);
		menuReturn.setOpaque(false);
		menuReturn.setContentAreaFilled(false);
		menuReturn.setBorderPainted(false);
		menuReturn.addActionListener(this);
		menuReturn.setFocusable(false);
		
		highScore.add(highScoreChart);
		highScore.add(menuReturn);
		
		highScore.setVisible(false);
		
		mainMenuBackground.setLayout(new BoxLayout(mainMenuBackground, BoxLayout.Y_AXIS));
		
		top.add(lblTop);
		
		buttons.setLayout(new GridLayout(5,1, 10, 10));
		buttons.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		
		mainMenuBackground.add(top);
		mainMenuBackground.add(buttons);
		mainMenuBackground.add(highScore);
		
		add(mainMenuBackground);
		
		addKeyListener(this);
		
		setVisible(true);
		setResizable(false);
		pack();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}//end MainMenu constructor 

	//#action
	public void actionPerformed(ActionEvent e) {
		if (buttonList[0] == e.getSource()) {
			dispose();
			new Main();
		}//end if
		
		if (buttonList[1] == e.getSource()) {
			JFileChooser chooseFile = new JFileChooser();
			chooseFile.setDialogTitle("Specify a file to Load");

			//#read
			int userSelection = chooseFile.showOpenDialog(this);
			if (userSelection == JFileChooser.APPROVE_OPTION)
			{
				//Attempt to Read the file
				//#error
				File fileToOpen = chooseFile.getSelectedFile();
				try {
					Scanner sc = new Scanner(fileToOpen);
					while (sc.hasNext()) {
						for (int i = 0; i < Main.shipX.length; i++) {
							Main.shipX[i] = sc.nextInt();
							Main.shipY[i] = sc.nextInt();
						}
						Main.angle = sc.nextDouble();
						Main.backgroundX = sc.nextInt();
						Main.backgroundY = sc.nextInt();
						Main.cheatEnabled = sc.nextBoolean();
					}//end while
					
				} catch (Exception ex) {
					System.out.println("Class not found");
				    ex.printStackTrace();
				}//end try/catch
				
				new Main();
			}//end if
			
			
		}//end if
		
		if (buttonList[2] == e.getSource()) {
			HighScore.printScores();
			buttons.setVisible(false);
			highScore.setVisible(true);
		}//end if
		
		if (buttonList[3] == e.getSource()) {
			new Settings();
		}//end if
		
		if (buttonList[4] == e.getSource()) {
			System.exit(0);
		}//end if
		
		if (menuReturn == e.getSource()) {
			highScore.setVisible(false);
			buttons.setVisible(true);	
		}//end if
		
	}//end actionPerformed

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_P || e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			PauseMenu.pause = !PauseMenu.pause;
			new PauseMenu();
		}//end if
		
		//if backquote is pressed and released
		if (e.getKeyCode() == 192) {
			Main.cheatEnabled = !Main.cheatEnabled;
			Main.pnlGraphics.repaint();
		}//end if
	}//end keyReleased
	
	public void keyTyped(KeyEvent e) {}
	public void keyPressed(KeyEvent e) {}
	
	public static void main(String [] args) throws IOException{
		new MainMenu();
	}
}//end class
