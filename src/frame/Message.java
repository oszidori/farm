package frame;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Message extends JFrame{
	private JLabel message;
	
	public Message(String text) {
		//title of the window
		super("Information");
		//set the window size, not resizable
		super.setSize(400, 100);
		super.setResizable(false);
		//click on the x, hide the window
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		//window appears on the center of the monitor
		setLocationRelativeTo(null);
		
		//customize the message
		message = new JLabel();
		message.setText(text);
		Font errorFont = new Font("Arial", Font.BOLD, 16);
		message.setFont(errorFont);
		message.setForeground(Color.GREEN);
		
		//add to the frame
		add(message);
	}
}
