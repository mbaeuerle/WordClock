package test;

public class Word {

	private int[] charPositions;
	private int row;

	Word(int row, int... charPositions) {
		this.row = row;
		this.charPositions = charPositions;
	}

	public int[] getCharPositions() {
		return charPositions;
	}

	public int getRow() {
		return row;
	}

	public void setCharPositions(int[] chars) {
		this.charPositions = chars;
	}

	public void setRow(int row) {
		this.row = row;
	}

}
