package clock;

import java.util.Calendar;
import java.util.HashMap;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.SwingUtilities;

public class Clock {

	public static final int COLUMNS = 11;

	public static final int ROWS = 10;

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
	private HashMap<Integer, HasCharPositions> minutes;

	private Word keyWordEsIst = new Word(0, 0, 1, 3, 4, 5);
	private Word keyWordClock = new Word(9, 8, 9, 10);

	public Clock(ClockComposite clock) {
		this.clock = clock;
		clock.initCharacters(characterArray);
		initHours();
		initMinutes();
	}

	private void initHours() {
		hours = new HashMap<>();
		hours.put(1, new Word(5, 0, 1, 2, 3));
		hours.put(2, new Word(5, 7, 8, 9, 10));
		hours.put(3, new Word(6, 0, 1, 2, 3));
		hours.put(4, new Word(6, 7, 8, 9, 10));
		hours.put(5, new Word(4, 7, 8, 9, 10));
		hours.put(6, new Word(7, 0, 1, 2, 3, 4));
		hours.put(7, new Word(8, 0, 1, 2, 3, 4, 5));
		hours.put(8, new Word(7, 7, 8, 9, 10));
		hours.put(9, new Word(9, 3, 4, 5, 6));
		hours.put(10, new Word(9, 0, 1, 2, 3));
		hours.put(11, new Word(4, 5, 6, 7));
		hours.put(0, new Word(8, 6, 7, 8, 9, 10));
	}

	private void initMinutes() {
		minutes = new HashMap<>();
		Word fuenf = new Word(0, 7, 8, 9, 10);
		Word zehn = new Word(1, 0, 1, 2, 3);
		Word vor = new Word(3, 0, 1, 2);
		Word halb = new Word(4, 0, 1, 2, 3);
		Word viertel = new Word(2, 4, 5, 6, 7, 8, 9, 10);
		Word nach = new Word(3, 7, 8, 9, 10);
		Word zwanzig = new Word(1, 4, 5, 6, 7, 8, 9, 10);
		minutes.put(0, new Sentence());
		minutes.put(5, new Sentence(fuenf, nach));
		minutes.put(10, new Sentence(zehn, nach));
		minutes.put(15, new Sentence(viertel, nach));
		minutes.put(20, new Sentence(zwanzig, nach));
		minutes.put(25, new Sentence(fuenf, vor, halb));
		minutes.put(30, new Sentence(halb));
		minutes.put(35, new Sentence(fuenf, nach, halb));
		minutes.put(40, new Sentence(zehn, nach, halb));
		minutes.put(45, new Word(2, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
		minutes.put(50, new Sentence(zehn, vor));
		minutes.put(55, new Sentence(fuenf, vor));
	}

	private void showWord(HasCharPositions w) {
		Integer[] charPositions = w.getAbsoluteCharPositions();
		for (int col : charPositions) {
			int row = col / COLUMNS;
			int offset = col % COLUMNS;
			clock.setHighlighted(row, offset);
		}
		clock.repaint();
	}

	public void showCurrentTime() {
		Timer timer = new Timer(true);

		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Berlin"));
				int hour = cal.get(Calendar.HOUR);
				int minute = cal.get(Calendar.MINUTE);
				internalShowTime(hour, minute);

			}

		}, 0, 60 * 1000);
	}

	int hour = 0;
	int minute = 0;

	public void testTime() {
		Timer timer = new Timer(true);

		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				internalShowTime(hour, minute);

				minute++;
				if (minute >= 60) {
					minute = 0;
					hour++;
					hour = hour % 12;
				}
			}

		}, 0, 200);
	}

	private void internalShowTime(int hour, int minute) {

		int shownMinute = ((minute + 2) / 5) * 5;
		int shownHour = hour;
		if (shownMinute >= 60) {
			shownMinute = 0;
			shownHour++;
			shownHour = shownHour % 12;
		} else if (shownMinute > 20) {
			shownHour++;
			shownHour = shownHour % 12;
		}
		Word hourWord = hours.get(shownHour);
		HasCharPositions minuteWord = minutes.get(shownMinute);
		final Sentence sentenceToShow = new Sentence(hourWord, minuteWord, keyWordEsIst);
		if (shownMinute == 0) {
			sentenceToShow.addWord(keyWordClock);
		}

		System.out.println("Stunde: " + hour + " Minute: " + minute + " Shown: " + shownHour + ":" + shownMinute);
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				clock.resetAllChars();
				showWord(sentenceToShow);
			}
		});
	}
}
