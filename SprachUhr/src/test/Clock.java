package test;

import java.util.HashMap;

public class Clock {

	private ClockComposite clock;
	private static final char characterArray[][] = new char[][] {
			{ 'E', 'S', 'K', 'I', 'S', 'T', 'A', 'F', 'Ü', 'N', 'F' },
			{ 'Z', 'E', 'H', 'N', 'Z', 'W', 'A', 'N', 'Z', 'I', 'G' },
			{ 'D', 'R', 'E', 'I', 'V', 'I', 'E', 'R', 'T', 'E', 'L' },
			{ 'V', 'O', 'R', 'F', 'U', 'N', 'K', 'N', 'A', 'C', 'H' },
			{ 'H', 'A', 'L', 'B', 'A', 'E', 'L', 'F', 'Ü', 'N', 'F' },
			{ 'E', 'I', 'N', 'S', 'X', 'A', 'M', 'Z', 'W', 'E', 'I' },
			{ 'D', 'R', 'E', 'I', 'P', 'M', 'J', 'V', 'I', 'E', 'R' },
			{ 'S', 'E', 'C', 'H', 'S', 'N', 'L', 'A', 'C', 'H', 'T' },
			{ 'S', 'I', 'E', 'B', 'E', 'N', 'Z', 'W', 'Ö', 'L', 'F' },
			{ 'Z', 'E', 'H', 'N', 'E', 'U', 'N', 'K', 'U', 'H', 'R' } };
	private HashMap<Integer, Word> hours;

	private Word keyWord = new Word(0, 0, 1, 3, 4, 5);

	public Clock(ClockComposite clock) {
		this.clock = clock;
		clock.initCharacters(characterArray);
		initHours();

	}

	private void initHours() {
		hours = new HashMap<>();
		hours.put(1, new Word(5, 0, 1, 2, 3));
		hours.put(2, new Word(5, 7, 8, 9, 10));
		hours.put(3, new Word(6, 0, 1, 2, 3));
		hours.put(4, new Word(6, 7, 8, 9, 10));
		hours.put(5, new Word(4, 7, 8, 9, 10));
		hours.put(6, new Word(7, 0, 1, 2, 3, 4, 5));
		hours.put(7, new Word(8, 0, 1, 2, 3, 4, 5, 6));
		hours.put(8, new Word(7, 7, 8, 9, 10));
		hours.put(9, new Word(9, 3, 4, 5, 6));
		hours.put(10, new Word(9, 0, 1, 2, 3, 4));
		hours.put(11, new Word(4, 5, 6, 7));
		hours.put(12, new Word(8, 6, 7, 8, 9, 10));
	}

	private void showWord(Word w) {
		int row = w.getRow();
		int[] charPositions = w.getCharPositions();
		for (int col : charPositions) {
			clock.setHighlighted(row, col);
		}

	}

	public void showCurrentTime() {
		clock.resetAllChars();
		showWord(keyWord);
		showWord(hours.get(5));
	}

}
