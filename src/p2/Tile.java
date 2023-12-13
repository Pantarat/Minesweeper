package p2;

import javax.swing.JButton;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.event.*;
import java.awt.Color;

public class Tile extends JButton implements MouseListener {
	boolean containsMine = false;
	int numSurround = 0;
	boolean clicked = false;
	boolean pressed = false;
	boolean marked = false;
	static boolean mineExplode = false;
	ActionInterpretor actionInt = new ActionInterpretor();

	public Tile(int a) {
		if (a == 0) {
			containsMine = true;
		}
		this.setSize(20, 20);
		this.setLayout(new BorderLayout());
		this.addActionListener(actionInt);
		this.addMouseListener(this);
	}

	public void changeSurround(int numSurround) {
		this.numSurround = numSurround;
	}

	public class ActionInterpretor implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			setEnabled(false);
			clicked = true;
			if (containsMine) {
				setBackground(Color.red);
				mineExplode = true;
			} else if (numSurround != 0) {
				setBackground(new JButton().getBackground());
				setText("" + numSurround);
			} else {
				setBackground(new JButton().getBackground());
			}
			repaint();
			revalidate();
		}
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
		this.getModel().setArmed(false);//from Internet
		this.getModel().setPressed(false);//from Internet
		if (pressed) {
			if (SwingUtilities.isRightMouseButton(e)) {
				if (!marked) {
					this.setBackground(Color.green);
					marked = true;
				} else {
					this.setBackground(new JButton().getBackground());
					marked = false;
				}
			} else {
				this.actionInt.actionPerformed(null);
			}
		}
		pressed = false;
	}

	public void mousePressed(MouseEvent e) {
		if (!clicked) {
			this.getModel().setArmed(true);//from Internet
			this.getModel().setPressed(true);//from Internet
			pressed = true;
		}
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}
}
