package app;

import javax.swing.SwingUtilities;
import javax.swing.UnsupportedLookAndFeelException;


public class Entry {

	public static void main(String[] args) {

		for(javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
			
			if("Nimbus".equals(info.getName()))
					{
						try {
							javax.swing.UIManager.setLookAndFeel(info.getClassName());
						} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
								| UnsupportedLookAndFeelException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;
					}
		}
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				
				final ApplicationWindow mainApplicationWindow = new ApplicationWindow();
				mainApplicationWindow.setVisible(true);
			}

		});

	}

}
