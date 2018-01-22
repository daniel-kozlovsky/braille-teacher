package app;

import javax.swing.SwingUtilities;

public class Entry {

	public static void main(String[] args) {
		
<<<<<<< HEAD
		//Not broken program
=======
	    //Break program for merge testing (1)
		/.
		//
>>>>>>> merge_testing
		
		SwingUtilities.invokeLater( new Runnable() {

			@Override
			public void run() {
				final ApplicationWindow m = new ApplicationWindow();
				m.setVisible(true);
			}
			
		});
		
		

	}

}
