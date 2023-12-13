package p2;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class Clock extends JPanel implements ActionListener {
	double startTime = System.currentTimeMillis();// implicit casting from long to double
	double elapsedTime = System.currentTimeMillis() - startTime;
	int elapsedTimeSec = 0;
	int elapsedTimeMin = 0;
	private Timer timer = new Timer(1000, this);
	String display = "0 : 0";
	JLabel displaylb = new JLabel(display, JLabel.CENTER);

	Clock() {
		displaylb.setFont(new Font("Verdana", Font.PLAIN, 30));
		this.add(displaylb);
	}

	void updateClock() {
		display = elapsedTimeMin + " : " + Math.floorMod(elapsedTimeSec, 60);// use Math static method floorMod
		displaylb.setText(display);
	}

	public void startClock() {
		timer.start();
	}

	public void stopClock() {
		timer.stop();
	}

	public void actionPerformed(ActionEvent e) {
		updateClock();
		elapsedTime = System.currentTimeMillis() - startTime;
		elapsedTimeSec = (int) Math.floor(elapsedTime / 1000);// explicit casting from double to int
		elapsedTimeMin = (int) Math.floor(elapsedTimeSec / 60);// Math Static class is called from overridden listener													// method
	}
	
	public void resetClock() {
		startTime = System.currentTimeMillis();
		updateClock();
	}
}
