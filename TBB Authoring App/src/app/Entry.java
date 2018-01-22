package app;

import javax.swing.SwingUtilities;

public class Entry {
 
	public static void main(String[] args) {
		


		
		//Master change

		
		SwingUtilities.invokeLater( new Runnable() {

			@Override
			public void run() {
				final ApplicationWindow m = new ApplicationWindow();
				m.setVisible(true);
			}
			
		});
		
		

	}

}
