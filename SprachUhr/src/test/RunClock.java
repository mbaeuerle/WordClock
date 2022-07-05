package test;

import javax.swing.JFrame;

import clock.Clock;
import clock.ClockComposite;

public class RunClock {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JFrame mainWindow = new JFrame();
		ClockComposite clockComposite = new ClockComposite();
		Clock clock = new Clock(clockComposite);

		mainWindow.setSize(800, 800);
		mainWindow.add(clockComposite);

		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.setVisible(true);

		clock.showCurrentTime();
//		 clock.testTime();
	}

}
