package p2;

import javax.swing.*;
import java.awt.event.*;

public class GenerateMinesweeper extends SelectionGUI implements ActionListener, ItemListener {
	static JFrame gameFrame;
	Minesweeper game;

	public GenerateMinesweeper() {
		super();
		super.startbt.addActionListener(this);
		super.widthtf.addActionListener(this);
		super.heighttf.addActionListener(this);
		super.difficultycb.addItemListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if (!Minesweeper.gameEnded) {
			System.out.println("A Game is currently running!");
		} else {
			width = Integer.parseInt(widthtf.getText());
			height = Integer.parseInt(heighttf.getText());
			game = new Minesweeper(super.width, super.height, super.difficulty);
			gameFrame = new JFrame("Minesweeper");
			gameFrame.add(game);
			gameFrame.setVisible(true);
			gameFrame.setLocationRelativeTo(null);
			gameFrame.setSize(width * 45, height * 45);
			gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			clock.resetClock();
			clock.startClock();
		}
	}

	public void itemStateChanged(ItemEvent e) {
		if (difficultycb.getSelectedItem() == "easy") {
			difficulty = 1;
		} else if (difficultycb.getSelectedItem() == "medium") {
			difficulty = 2;
		} else {
			difficulty = 3;
		}
	}
}
