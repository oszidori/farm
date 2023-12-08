package frame;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.*;

public class MyFrame extends JFrame{
	private MainMenu menu = new MainMenu(this);
	
    public MyFrame() {
    	//title of the window
		super("Daisy Fields - MainMenu");
		//set the window size, not resizable
		super.setSize(1280, 720);
		super.setResizable(false);
		//click on the x, shut down the program
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//window appears on the center of the monitor
		setLocationRelativeTo(null);
		//set to center the layout
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.getContentPane().setBackground(new Color(153, 255, 153));
    	
		add(menu);

    }
}
