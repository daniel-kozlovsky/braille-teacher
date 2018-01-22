package app;

import javax.swing.SwingUtilities;

public class Entry {
// change A 
	public static void main(String[] args) {
	
		SwingUtilities.invokeLater( new Runnable() {

			@Override
			public void run() {
				final ApplicationWindow m = new ApplicationWindow();
				m.setVisible(true);
			}
			
		});
		
		

	}

}
