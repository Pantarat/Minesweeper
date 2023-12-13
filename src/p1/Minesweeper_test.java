package p1;
import javax.swing.JFrame;
import p2.GenerateMinesweeper;

public class Minesweeper_test {
	public static void main(String[] args) {
		JFrame mainframe = new JFrame("Minesweeper Main Menu");
		mainframe.add(new GenerateMinesweeper());
		mainframe.setVisible(true);
		mainframe.setSize(500,200);
		mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainframe.setLocationRelativeTo(null);
	}
}
