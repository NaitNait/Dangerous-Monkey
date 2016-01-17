/**
 * PauseMenu class
 *
 * Creates the pause menu of the game containing 1 JLabel and 5 JButtons
 * This class includes an action listener, key listener, and file writer
 * 
 * @author Rehan Hajee
 **/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;


class PauseMenu extends JFrame implements ActionListener, KeyListener {

	//create JLabel
	JLabel lblTop = new JLabel("<html><u>GAME PAUSED</u></html>");
	
	
	//Initializing the JPanels
	JPanel top = new JPanel();
	JPanel buttons = new JPanel();
	JPanel pauseBackground = new JPanel();

	//Setting 2 lists: a JButton list and a String list to label the buttons
	JButton buttonList[] = new JButton[5];
	String buttonText[] = {"RESUME", "RESTART", "SAVE GAME", "SETTINGS", "EXIT"};
	
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

	public void actionPerformed(ActionEvent e) {
		if (buttonList[0] == e.getSource())
			dispose();
		if (buttonList[2] == e.getSource()){
			JFileChooser chooseFile = new JFileChooser();
			chooseFile.setDialogTitle("Specify a file to save");

			int userSelection = chooseFile.showSaveDialog(this);
			if (userSelection == JFileChooser.APPROVE_OPTION)
			{
				//Attempt to save to the file
				File file = chooseFile.getSelectedFile();
				try
				{
					FileWriter fw = new FileWriter(file);
					PrintWriter pw = new PrintWriter(fw);
					for (int i  = 0; i < Ship.shipX.length; i++){
						pw.println(Ship.shipX[i]);
					}
					for (int i  = 0; i < Ship.shipY.length; i++){
						pw.println(Ship.shipY[i]);
					}
					pw.close();
				}
				catch (IOException x)
				{
					System.out.println("The following problem writing to a file occurred:\n" + x);
				}//end try/catch
			}//end if
		}//end if
		if (buttonList[4] == e.getSource())
			System.exit(0);
	}//end actionPerformed
	
	public void keyReleased(KeyEvent e){
		if (e.getKeyCode() == KeyEvent.VK_P){
			dispose();
		}
	}//end keyReleased
	public void keyTyped(KeyEvent e){}
	public void keyPressed(KeyEvent e){}
}
