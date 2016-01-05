import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

class MainMenu extends JFrame implements ActionListener, KeyListener {

	ImageIcon asteroids = new ImageIcon("img\\Asteroids.png");
	JButton lblTop = new JButton(asteroids);
	JPanel top = new JPanel();
	JPanel buttons = new JPanel();
	JPanel background = new JPanel();
	JPanel blank = new JPanel();

	JButton buttonList[] = new JButton[5];
	String buttonText[] = {"START NEW GAME", "RESUME PREVIOUS GAME", "HIGH SCORES", "SETTINGS", "EXIT"};
	
	public MainMenu() {
		super("Asteroids");
		
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
		}
		
		lblTop.setFocusPainted(false);
		lblTop.setOpaque(false);
		lblTop.setContentAreaFilled(false);
		lblTop.setBorderPainted(false);
		lblTop.setFocusable(false);
		
		//.setPreferredSize(new Dimension(600, 300));
		background.setLayout(new BoxLayout(background, BoxLayout.Y_AXIS));

		top.add(lblTop);
		
		buttons.setLayout(new GridLayout(5,1, 10, 10));
		buttons.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		background.add(top);
		background.add(blank);
		background.add(buttons);

		add(background);
		addKeyListener(this);
		
		setVisible(true);
		pack();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e) {
		if (buttonList[0] == e.getSource())
			dispose();
		if (buttonList[1] == e.getSource()){
			JFileChooser chooseFile = new JFileChooser();
			chooseFile.setDialogTitle("Specify a file to Load");

			int userSelection = chooseFile.showOpenDialog(this);
			if (userSelection == JFileChooser.APPROVE_OPTION)
			{
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
				}
			}
		}
		if (buttonList[2] == e.getSource())
			HighScore.score("a");
		if (buttonList[4] == e.getSource())
			System.exit(0);
	}

	public void keyReleased(KeyEvent e){
		if (e.getKeyCode() == KeyEvent.VK_P){
			new PauseMenu();
		}
	}
	public void keyTyped(KeyEvent e){}
	public void keyPressed(KeyEvent e){}
}
