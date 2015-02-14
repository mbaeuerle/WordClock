package clock;

public class Word implements HasCharPositions {

	private Integer[] charPositions;

	Word(int row, int... charPositions) {
		int y = 0;
		this.charPositions = new Integer[charPositions.length];
		for (int i : charPositions) {
			this.charPositions[y] = i + row * Clock.COLUMNS;
			y++;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see test.HasCharPositions#getAbsoluteCharPositions()
	 */
	@Override
	public Integer[] getAbsoluteCharPositions() {
		return charPositions;
	}

}
