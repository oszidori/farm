package frame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*Appears when the program starts, the user can choose from new game, load game, exit options*/
public class MainMenu extends JPanel {
	
	//create the menu panel with 5 rows and 1 column
	private JLabel name_text = new JLabel();
	private JLabel text = new JLabel();
	private JButton newGame = new JButton("New Game");
	private JButton loadGame = new JButton("Load Game");
	private JButton exit = new JButton("Exit");
	
	public MainMenu(MyFrame frame) {	
		
		//customize the components
		Customize();
		
		//add components to the panel
		add(name_text);
		add(text);
		add(newGame);
		add(loadGame);
		add(exit);
		
		newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddPlayerNameFrame playerFrame= new AddPlayerNameFrame(frame);
                playerFrame.setVisible(true);
            }
        });

        loadGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewGameFrame gameframe = new NewGameFrame("loadgame");
                gameframe.setVisible(true);
				frame.dispose();
            }
        });
        exit.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
		
	}
	
	//initial customize function, change the MenuPanel, texts, alignment, background
	public void Customize() {
		//resize the Menu smaller than the Frame
		setPreferredSize(new Dimension(300, 620));
		setLayout(new GridLayout(5, 1, 20, 20));
		setBackground(new Color(153, 255, 153));
		
		//create customized font
		Font title = new Font("Cooper Black", Font.BOLD, 40);
		Font f_text = new Font("Cooper Black", Font.BOLD, 22);
		
		//customize the text, alignment
		name_text.setHorizontalAlignment(JLabel.CENTER);
		name_text.setFont(title);
		name_text.setText("Daisy Fields");
		
		text.setHorizontalAlignment(JLabel.CENTER);
		text.setFont(f_text);
		text.setText("<html>Please choose one of the following options...</html>");
		
		//customize the button text and background
		newGame.setFont(f_text);
		newGame.setBackground(new Color(0,204,0));
		
		loadGame.setFont(f_text);
		loadGame.setBackground(new Color(0,204,0));
		
		exit.setFont(f_text);
		exit.setBackground(new Color(0,204,0));
		
	}
}
