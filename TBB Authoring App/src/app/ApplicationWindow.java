package app;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class ApplicationWindow extends JFrame {

	// Size of main panel
	public Rectangle MainPanelArea;
	MainPanel PanelComponentField;

	public ApplicationWindow() {
		initFrame();
		initComponents();
		PanelComponentField.setVisible(true); // First visible panel

	}

	private void initFrame() {
		setTitle("TBB Authoring App");
		// Set to maximum window size
		//this.setExtendedState(this.getExtendedState() | Frame.MAXIMIZED_BOTH);
		
		//reposition
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    int height = screenSize.height;
	    int width = screenSize.width;
	    setSize(width/3, height/3);
	    
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(400, 200));
		setSize(new Dimension(400, 200));
		setVisible(true);

	}

	private void initComponents() {
		// Panel
		PanelComponentField = new MainPanel(ApplicationWindow.this);
		MainPanelArea = new Rectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		getContentPane().add(PanelComponentField);
		pack();

	}

}
