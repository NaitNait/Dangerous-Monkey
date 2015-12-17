import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

class PauseMenu extends JFrame implements ActionListener, KeyListener {

	JLabel lblTop = new JLabel("GAME PAUSED");
	
	JPanel top = new JPanel();
	JPanel buttons = new JPanel();
	JPanel background = new JPanel();
	JPanel blank = new JPanel();

	JButton buttonList[] = new JButton[5];
	String buttonText[] = {"RESUME", "RESTART", "SAVE GAME", "SETTINGS", "EXIT"};
	
	public PauseMenu() {
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
		
		//.setPreferredSize(new Dimension(600, 300));
		background.setLayout(new BoxLayout(background, BoxLayout.Y_AXIS));

		top.add(lblTop);
		buttons.setLayout(new GridLayout(5, 1, 10, 10));
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
		if (buttonList[2] == e.getSource()){
			JFileChooser chooseFile = new JFileChooser();
			chooseFile.setDialogTitle("Specify a file to save");

			int userSelection = chooseFile.showSaveDialog(this);
			if (userSelection == JFileChooser.APPROVE_OPTION)
			{
				File fileToSave = chooseFile.getSelectedFile();
				try
				{
					FileOutputStream out = new FileOutputStream(fileToSave.getAbsolutePath());
					ObjectOutputStream fileOut = new ObjectOutputStream(out);
				  
					//fileOut.writeObject();
					fileOut.flush();
					fileOut.close();
				}
				catch (IOException x)
				{
					System.out.println("The following problem writing to a file occurred:\n" + x);
				}
			}
		}
		if (buttonList[4] == e.getSource())
			System.exit(0);
	}
	
	public void keyReleased(KeyEvent e){
		if (e.getKeyCode() == KeyEvent.VK_P){
			dispose();
		}
	}
	public void keyTyped(KeyEvent e){}
	public void keyPressed(KeyEvent e){}
}
