package test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

public class ClockComposite extends JComponent {

	private static final int COLUMNS = 11;

	private static final int ROWS = 10;

	private static final long serialVersionUID = 4229018579689301767L;

	private JLabel[][] characters;

	public ClockComposite() {
		setDoubleBuffered(true);
		setBorder(new EmptyBorder(10, 10, 10, 10));
		setLayout(new GridLayout(ROWS, COLUMNS));

	}

	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(Color.BLACK);
		int width = getWidth();
		int height = getHeight();
		g.fillRect(0, 0, width, height);
		super.paintComponent(g);
	}

	public void initCharacters(char[][] characterArray) {
		characters = new JLabel[ROWS][COLUMNS];

		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLUMNS; col++) {
				JLabel newLabel = new JLabel(characterArray[row][col] + "");
				newLabel.setForeground(Color.GRAY);
				add(newLabel);
				characters[row][col] = newLabel;
			}
		}
	}

	public void resetAllChars() {
		for (JLabel[] labels : characters) {
			for (JLabel label : labels) {
				label.setForeground(Color.GRAY);
			}
		}
	}

	public void setHighlighted(int row, int col) {
		characters[row][col].setBackground(Color.BLUE);
	}
}
