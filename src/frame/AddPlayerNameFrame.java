package frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

//add player name in a separate frame
public class AddPlayerNameFrame extends JFrame{
	private JPanel panel;
	private JLabel text;
	private JTextField name;
	private JButton save;
	
	public AddPlayerNameFrame(MyFrame frame) {
		//title of the window
		super("Daisy Fields - NewGame");
		//set the window size, not resizable
		super.setSize(500, 100);
		super.setResizable(false);
		//click on the x, shut down the program
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		//window appears on the center of the monitor
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		
		//create the panels
		panel = new JPanel(new FlowLayout());
		text = new JLabel("Enter your name: ");
		name = new JTextField();
		name.setColumns(30);
		
		//save the player's name
		save = new JButton("Save");
		//add response to the button
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				//get the entered name
				String PlayerName = name.getText();
				//if the user did not enter the name, show errorMessage
				if(PlayerName.equals("")) {
					ErrorMessage error = new ErrorMessage("Please give your player name!");
					error.setVisible(true);
				}
				//if the name was correct then appears the new game frame
				else {
					frame.dispose();
					dispose();
					NewGameFrame startGame = new NewGameFrame(PlayerName);
					startGame.setVisible(true);
				}
				
			}
		});
		
		panel.add(text);
		panel.add(name);
		panel.add(save);
		
		add(panel);

	}
	

}
