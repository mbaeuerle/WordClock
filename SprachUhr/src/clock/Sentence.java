package clock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Sentence implements HasCharPositions {

	private List<HasCharPositions> words;

	public Sentence(HasCharPositions... words) {
		this.words = new ArrayList<>();
		this.words.addAll(Arrays.asList(words));
	}

	public Sentence() {
		this.words = Collections.<HasCharPositions> emptyList();
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

	public void addWord(Word word) {
		words.add(word);
	}

}
