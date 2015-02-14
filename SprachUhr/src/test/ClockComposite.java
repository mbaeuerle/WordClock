package test;

import static test.Clock.COLUMNS;
import static test.Clock.ROWS;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

public class ClockComposite extends JComponent {

	private static final long serialVersionUID = 4229018579689301767L;

	private JLabel[][] characters;

	private static final Color HIGHLIGHT_COLOR = Color.WHITE;
	private static final Color DEFAULT_COLOR = new Color(50, 50, 50);

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
				newLabel.setForeground(DEFAULT_COLOR);
				newLabel.setFont(newLabel.getFont().deriveFont(32.0f));
				add(newLabel);
				characters[row][col] = newLabel;
			}
		}
	}

	public void resetAllChars() {
		for (JLabel[] labels : characters) {
			for (JLabel label : labels) {
				label.setForeground(DEFAULT_COLOR);
			}
		}
	}

	public void setHighlighted(int row, int col) {
		characters[row][col].setForeground(HIGHLIGHT_COLOR);
	}
}
