package frame;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class ErrorMessage extends JFrame{

	private JLabel errorMessage;
	
	public ErrorMessage(String text) {
		//title of the window
		super("Error");
		//set the window size, not resizable
		super.setSize(400, 100);
		super.setResizable(false);
		//click on the x, hide the window
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		//window appears on the center of the monitor
		setLocationRelativeTo(null);
		
		//customize the message
		errorMessage = new JLabel();
		errorMessage.setText(text);
		Font errorFont = new Font("Arial", Font.BOLD, 16);
		errorMessage.setFont(errorFont);
		errorMessage.setForeground(Color.RED);
		
		//add to the frame
		add(errorMessage);
	}

}
