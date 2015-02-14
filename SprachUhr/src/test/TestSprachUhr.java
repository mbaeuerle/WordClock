package test;

import javax.swing.JFrame;

import clock.Clock;
import clock.ClockComposite;

public class TestSprachUhr {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JFrame testFrame = new JFrame();
		ClockComposite clockComposite = new ClockComposite();
		Clock clock = new Clock(clockComposite);

		testFrame.setSize(800, 600);
		testFrame.add(clockComposite);

		testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		testFrame.setVisible(true);

		clock.showCurrentTime();
	}

}
