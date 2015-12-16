import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class MainMenu extends JFrame implements ActionListener, KeyListener
{

	JLabel lblTop = new JLabel("ASTEROIDS");
	
	JPanel top = new JPanel();
	JPanel buttons = new JPanel();
	JPanel background = new JPanel();
	JPanel blank = new JPanel();

	/*
	JButton buttonA = new JButton("START NEW GAME");
	JButton buttonB = new JButton("RESUME PREVIOUS GAME");
	JButton buttonC = new JButton("SETTINGS");
	JButton buttonD = new JButton("EXIT");
	*/

	JButton buttonList[] = new JButton[4];
	String buttonText[] = {"START NEW GAME", "RESUME PREVIOUS GAME", "SETTINGS", "EXIT"};
	
	public MainMenu()
    {
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
		
		/*
		buttonA.setPreferredSize(new Dimension(200, 60));
		buttonB.setPreferredSize(new Dimension(200, 60));
		buttonC.setPreferredSize(new Dimension(200, 60));
		buttonD.setPreferredSize(new Dimension(200, 60));
		
		buttonA.setFocusPainted(false);
		buttonA.setOpaque(false);
		buttonA.setContentAreaFilled(false);
		buttonA.setBorderPainted(false);
		
		buttonA.addActionListener(this);
		buttons.add(buttonA);
		buttonB.addActionListener(this);
		buttons.add(buttonB);
		buttonC.addActionListener(this);
		buttons.add(buttonC);
		buttonD.addActionListener(this);
		buttons.add(buttonD);
		*/
		
		

		//.setPreferredSize(new Dimension(600, 300));
		background.setLayout(new BoxLayout(background, BoxLayout.Y_AXIS));

		top.add(lblTop);
		buttons.setLayout(new GridLayout(4,1, 10, 10));
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

	public void actionPerformed(ActionEvent e)
	{
		if (buttonList[0] == e.getSource())
			dispose();
		if (buttonList[3] == e.getSource())
			System.exit(0);
	}

	public void keyReleased(KeyEvent e){
		if (e.getKeyCode() == KeyEvent.VK_P){
			new PauseMenu();
		}
	}
	public void keyTyped(KeyEvent e){}
	public void keyPressed(KeyEvent e){
		
	}
	/*
	public static void main(String[] args)
	{
		new MainMenu();
	}
	*/
}
