package p2;

import javax.swing.*;
import java.awt.*;

public class SelectionGUI extends JPanel {
	JPanel mainPanel = new JPanel();
	String[] difficulty_array = { "easy", "medium", "hard" };
	JComboBox<String> difficultycb = new JComboBox<String>(difficulty_array);
	JLabel widthlb = new JLabel("Insert width: ");
	JLabel heightlb = new JLabel("Insert height: ");
	JTextField widthtf = new JTextField(5);
	JTextField heighttf = new JTextField(5);
	JButton startbt = new JButton("Start!");
	JLabel suggestlb = new JLabel("Note: same width and height between 8-20 is recommended",JLabel.CENTER);
	static Clock clock = new Clock();
	protected int width = 0;
	protected int height = 0;
	protected int difficulty = 1;

	protected SelectionGUI() {
		this.setLayout(new BorderLayout());
		this.add(mainPanel, BorderLayout.NORTH);
		mainPanel.add(widthlb);
		mainPanel.add(widthtf);
		mainPanel.add(heightlb);
		mainPanel.add(heighttf);
		mainPanel.add(difficultycb);
		mainPanel.add(startbt);
		this.add(clock, BorderLayout.CENTER);
		this.add(suggestlb,BorderLayout.SOUTH);
	}

}
