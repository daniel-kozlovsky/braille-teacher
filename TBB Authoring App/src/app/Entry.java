package app;

import javax.swing.SwingUtilities;

public class Entry {

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				final ApplicationWindow mainApplicationWindow = new ApplicationWindow();
				mainApplicationWindow.setVisible(true);
			}

		});

	}

}
