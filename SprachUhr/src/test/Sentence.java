package test;

import java.util.ArrayList;

public class Sentence implements HasCharPositions {

	private HasCharPositions[] words;

	public Sentence(HasCharPositions... words) {
		this.words = words;
	}

	@Override
	public Integer[] getAbsoluteCharPositions() {
		ArrayList<Integer> allCharPositions = new ArrayList<>();
		for (HasCharPositions hasCharPositions : words) {
			Integer[] absoluteCharPositions = hasCharPositions.getAbsoluteCharPositions();
			for (int i : absoluteCharPositions) {
				allCharPositions.add(i);
			}
		}
		return allCharPositions.toArray(new Integer[0]);
	}

}
