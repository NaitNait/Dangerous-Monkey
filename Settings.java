import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Settings extends JFrame implements ActionListener, KeyListener
{

	JLabel lblTop = new JLabel("<html><u>SETTINGS</u></html>");
	
	JPanel top = new JPanel();
	JPanel buttons = new JPanel();
	JPanel background = new JPanel();
	JPanel blank = new JPanel();
	
	static JButton buttonList[] = new JButton[5];
	static String buttonText[] = {"CHANGE SHIP COLOUR", "SPEED", "NUMBER OF ASTEROIDS", "CLOSE SETTINGS", "CHEAT MODE: DISABLED"};
	
	public Settings()
    {
		super("Asteroids");
		
		for(int i = 0; i < buttonList.length;i++){
			buttonList[i] = new JButton(buttonText[i]);
			buttonList[i].setPreferredSize(new Dimension(200, 40));
			buttonList[i].setFocusPainted(false);
			buttonList[i].setOpaque(false);
			buttonList[i].setContentAreaFilled(false);
			buttonList[i].setBorderPainted(false);
			buttonList[i].addActionListener(this);
			buttonList[i].setFocusable(false);
			
			buttons.add(buttonList[i]);
		}
		
		if (Main.cheatEnabled)
			buttonList[4].setText("CHEAT MODE: ENABLED");
		else 
			buttonList[4].setText("CHEAT MODE: DISABLED");
		
		buttonList[4].setEnabled(false);

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

	public void actionPerformed(ActionEvent e)
	{
		if (buttonList[0] == e.getSource())
			Main.shipColor = JColorChooser.showDialog(background, "Pick Ship Colour", Main.shipColor);
			Main.pnlGraphics.repaint();

		if (buttonList[3] == e.getSource())
			dispose();
	}

	public void keyReleased(KeyEvent e){
		if (e.getKeyCode() == KeyEvent.VK_P || e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			PauseMenu.pause = !PauseMenu.pause;
			new PauseMenu();
		}
		if (e.getKeyCode() == 192) {
			Main.cheatEnabled = !Main.cheatEnabled;
			if (Main.cheatEnabled)
				buttonList[4].setText("CHEAT MODE: ENABLED");
			else 
				buttonList[4].setText("CHEAT MODE: DISABLED");
			repaint();
		} 
	}
	public void keyTyped(KeyEvent e){}
	public void keyPressed(KeyEvent e){
		
	}
	
	public static void main(String[] args)
	{
		new Settings();
	}
	
}
