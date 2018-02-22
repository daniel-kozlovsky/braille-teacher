package app;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Rectangle;
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
		setTitle("TBB Scenario Creator");
		// Set to maximum window size
		this.setExtendedState(this.getExtendedState() | Frame.MAXIMIZED_BOTH);

		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(600, 600));
		setSize(new Dimension(800, 700));
		setVisible(true);

	}

	private void initComponents() {
		// Panel
		PanelComponentField = new MainPanel(this);
		MainPanelArea = new Rectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		getContentPane().add(PanelComponentField);

	}

}
