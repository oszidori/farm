package frame;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Help extends JFrame{
    private JPanel panel;
    private JLabel header;

    public Help(){
        //title of the window
		super("Help");
		//set the window size, not resizable
		super.setSize(550, 500);
		super.setResizable(false);
		//click on the x, hide the window
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		//window appears on the center of the monitor
		setLocationRelativeTo(null);
        panel = new JPanel();
        header = new JLabel();
        Customize();
        this.add(panel);
    }
    public void Customize(){
        header.setText("Guide for Daisy Fields");
        header.setFont(new Font("Arial", Font.BOLD, 30));

        JLabel text = new JLabel();
        text.setFont(new Font("Arial", Font.PLAIN, 15));
        text.setText("<html><b>Buttons:</b><br>" +
        "<b>Inventory:</b> shows the owned seeds, you can sell or plant these <br>"+
        "<b>Shop:</b> you can buy new seeds by clicking on the preffered seed name<br> or sell a seed by choose it from the list and click on the Sell button<br>"+
        "<b>Soils:</b> the numbered rectangles are the soils, you can interact with them.<br>"+
        "<b>SAVE and Go back:</b> this button saves the current field and inventory items <br> The plant is lost if it didn't harvested before clicking on the save button<br>"+
        "<b>The meaning of the colors of the soil:</b></html>");
        text.setHorizontalAlignment(SwingConstants.LEFT);
        text.setVerticalAlignment(SwingConstants.CENTER);

        JLabel grey = new JLabel();
        grey.setFont(new Font("Arial", Font.PLAIN, 15));
        grey.setForeground(Color.DARK_GRAY);
        grey.setText("<html>Grey: means that you have to buy this soil before you can plant here<br></html>");
        grey.setHorizontalAlignment(SwingConstants.LEFT);
        grey.setVerticalAlignment(SwingConstants.CENTER);

        JLabel green = new JLabel();
        green.setFont(new Font("Arial", Font.PLAIN, 15));
        green.setForeground(Color.green);
        green.setText("<html>Green: means that the soil can be planted<br></html>");
        green.setHorizontalAlignment(SwingConstants.LEFT);
        green.setVerticalAlignment(SwingConstants.CENTER);

        JLabel brown = new JLabel();
        brown.setFont(new Font("Arial", Font.PLAIN, 15));
        brown.setForeground(new Color(153, 76, 0));
        brown.setText("<html>Brown: means that the seed has been planted in the field<br></html>");
        brown.setHorizontalAlignment(SwingConstants.LEFT);
        brown.setVerticalAlignment(SwingConstants.CENTER);

        JLabel different = new JLabel();
        different.setFont(new Font("Arial", Font.PLAIN, 15));
        different.setForeground(Color.red);
        different.setText("<html>Yellow, Orange, Red: the plant is ready to harvest,<br> the colour depends on the type of the seed.<br></html>");
        different.setHorizontalAlignment(SwingConstants.LEFT);
        different.setVerticalAlignment(SwingConstants.CENTER);

        JLabel money = new JLabel();
        money.setFont(new Font("Arial", Font.PLAIN, 15));
        money.setForeground(Color.BLACK);
        money.setText( "<html>Soils: buying and selling Price: 200<br> Wheat: price: 80, harvest profit: 100<br> Tomato: price: 100, harvest profit: 140 <br> Carrot: price: 120, harvest profit: 180</html>");
        money.setHorizontalAlignment(SwingConstants.LEFT);
        money.setVerticalAlignment(SwingConstants.CENTER);

        panel.setLayout(new FlowLayout(FlowLayout.LEFT));

        panel.add(header);
        panel.add(text);
        panel.add(grey);
        panel.add(green);
        panel.add(brown);
        panel.add(different);
        panel.add(money);
    }
}
