package app;

import javax.swing.SwingUtilities;

public class Entry {

	public static void main(String[] args) {
	
		SwingUtilities.invokeLater( new Runnable() {

			@Override
			public void run() {
				final MainScreen m = new MainScreen();
				m.setVisible(true);
			}
			
		});
		
		

	}

}
