package clock;

import static clock.Clock.COLUMNS;
import static clock.Clock.ROWS;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.Arrays;
import java.util.stream.Stream;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class ClockComposite extends JComponent {

    private static final long serialVersionUID = 4229018579689301767L;

    private JLabel[][] characters;
    private JLabel[] minutes;

    private static final Color HIGHLIGHT_COLOR = Color.WHITE;
    private static final Color DEFAULT_COLOR = new Color(30, 30, 30);

    public ClockComposite() {
        setDoubleBuffered(true);
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setLayout(new GridLayout(ROWS + 2, COLUMNS + 2));

    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }

    public void initCharacters(char[][] characterArray) {
        characters = new JLabel[ROWS][COLUMNS];

        minutes = new JLabel[4];

        minutes[0] = createLabel("·");
        createFiller();
        minutes[1] = createLabel("·");

        for (int row = 0; row < ROWS; row++) {
            createLabel("");
            for (int col = 0; col < COLUMNS; col++) {
                characters[row][col] = createLabel(characterArray[row][col] + "");
            }
            createLabel("");
        }
        minutes[3] = createLabel("·");
        createFiller();
        minutes[2] = createLabel("·");
    }

    private void createFiller() {
        Stream.iterate(0, i -> i < COLUMNS, i -> i+1).forEach(i -> createLabel(""));
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(DEFAULT_COLOR);
        label.setFont(label.getFont().deriveFont(32.0f));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        add(label);
        return label;
    }

    public void resetAllChars() {
        Arrays.stream(characters).forEach(labels ->
                Arrays.stream(labels).forEach(label -> label.setForeground(DEFAULT_COLOR)));
        Arrays.stream(minutes).forEach(minute -> minute.setForeground(DEFAULT_COLOR));
    }

    public void setHighlighted(int row, int col) {
        characters[row][col].setForeground(HIGHLIGHT_COLOR);
    }

    public void setMinute(int minute) {
        if (minute > 0) {
            for (int i = 0; i < minute; i ++) {
                minutes[i].setForeground(HIGHLIGHT_COLOR);
            }
        }
    }
}
