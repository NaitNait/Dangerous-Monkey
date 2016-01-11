/**
 * MainMenu class
 *
 * Creates the main menu of the game containing 1 ImageIcon (in a JLabel) and 5 JButtons
 * This class includes an action listener, key listener, and file reader
 * 
 * @author Rehan Hajee
 **/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

class MainMenu extends JFrame implements ActionListener, KeyListener {

	//create the image as a JLabel
	ImageIcon asteroids = new ImageIcon("img\\Asteroids.png");
	JLabel lblTop = new JLabel(asteroids);
	
	//Initializing the JPanels
	JPanel top = new JPanel();
	JPanel buttons = new JPanel();
	JPanel highScore = new JPanel();
	JPanel highScoreChart = new JPanel();
	JPanel background = new JPanel();


	//Setting 2 lists: a JButton list and a String list to label the buttons 
	JButton buttonList[] = new JButton[5];
	String buttonText[] = {"START NEW GAME", "RESUME PREVIOUS GAME", "HIGH SCORES", "SETTINGS", "EXIT"};
	
	JButton menuReturn = new JButton("<html><u>RETURN TO MENU</u></html>");
	
	/**
	 * Main Constructor
	 * Constructs the menu with an ImageIcon on the top, with JButtons
	 * in a GridLayout 
	 *
	 * @param none
	 **/
	public MainMenu() throws IOException{
		super("Asteroids");
		
		// Retrieve high scores from the file
		HighScore.getScores();
		
		//Configures each JButton and adds it to the button list
		for(int i = 0; i < buttonList.length;i++){
			buttonList[i] = new JButton(buttonText[i]);
			buttonList[i].setPreferredSize(new Dimension(200, 60));
			buttonList[i].setFocusPainted(false);
			buttonList[i].setOpaque(false);
			buttonList[i].setContentAreaFilled(false);
			buttonList[i].setBorderPainted(false);
			buttonList[i].addActionListener(this);
			buttonList[i].setFocusable(false);
			buttons.add(buttonList[i]);
		}//end for loop
		
		
		for(int i = 0; i < HighScore.arrHighScoreList.size()/2-1;i++){
			JLabel s1 = new JLabel(Integer.toString(HighScore.arrHighScoreList.get(i).score));
			highScoreChart.add(s1);
			JLabel n1 = new JLabel(HighScore.arrHighScoreList.get(i).name);
			highScoreChart.add(n1);
			JLabel s2 = new JLabel(Integer.toString(HighScore.arrHighScoreList.get(i + 9).score));
			highScoreChart.add(s2);
			JLabel n2 = new JLabel(HighScore.arrHighScoreList.get(i + 9).name);
			highScoreChart.add(n2);
		}
		
		menuReturn.setFocusPainted(false);
		menuReturn.setOpaque(false);
		menuReturn.setContentAreaFilled(false);
		menuReturn.setBorderPainted(false);
		menuReturn.addActionListener(this);
		menuReturn.setFocusable(false);
		
		highScoreChart.setLayout(new GridLayout(10,2));
		
		highScore.add(highScoreChart);
		highScore.add(menuReturn);
		highScore.setVisible(false);
		
		background.setLayout(new BoxLayout(background, BoxLayout.Y_AXIS));
		
		top.add(lblTop);
		
		buttons.setLayout(new GridLayout(5,1, 10, 10));
		buttons.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		
		background.add(top);
		background.add(buttons);
		background.add(highScore);
		
		add(background);
		
		addKeyListener(this);
		
		setVisible(true);
		//setResizable(false);
		pack();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}//end MainMenu constructor 

	public void actionPerformed(ActionEvent e) {
		if (buttonList[0] == e.getSource()){
			dispose();
			new Ship();
		}//end if
		if (buttonList[1] == e.getSource()){
			JFileChooser chooseFile = new JFileChooser();
			chooseFile.setDialogTitle("Specify a file to Load");

			int userSelection = chooseFile.showOpenDialog(this);
			if (userSelection == JFileChooser.APPROVE_OPTION)
			{
				//Attempt to Read the file
				File fileToOpen = chooseFile.getSelectedFile();
				try
				{
					ObjectInputStream input = new ObjectInputStream(new FileInputStream(fileToOpen.getAbsolutePath()));
					input.close();
				}
				catch (Exception c)
				{
					System.out.println("Class not found");
				    c.printStackTrace();
				}//end try/catch
			}//end if
		}//end if
		if (buttonList[2] == e.getSource()){
			HighScore.printScores();
			buttons.setVisible(false);
			highScore.setVisible(true);
		}//end if
		if (buttonList[4] == e.getSource())
			System.exit(0);
		
		if (menuReturn == e.getSource()){
			System.out.println("Test");
			highScore.setVisible(false);
			buttons.setVisible(true);
			
		}
	}//end actionPerformed

	public void keyReleased(KeyEvent e){
		if (e.getKeyCode() == KeyEvent.VK_P){
			new PauseMenu();
		}
	}//end keyReleased
	public void keyTyped(KeyEvent e){}
	public void keyPressed(KeyEvent e){}
}//end class
