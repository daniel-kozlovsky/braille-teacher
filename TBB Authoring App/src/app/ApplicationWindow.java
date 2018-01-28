package app;


import java.awt.Rectangle;
import javax.swing.JFrame;



@SuppressWarnings("serial")
public class ApplicationWindow extends JFrame {
	
	//Size of Frame
	private final int FRAME_WIDTH = 500;
	private final int FRAME_HEIGHT = 500;
	
	//Size of main panel
	public final Rectangle MainPanelArea = new Rectangle(this.getX(), this.getY(),
													this.getWidth(), this.getHeight());
	MainPanel PanelComponentField;
	
	public ApplicationWindow()
	{
		initFrame();
		initComponents();
		PanelComponentField.setVisible(true); //First visible panel
		
	}
	
	private void initFrame()
	{
		setTitle("Authoring App");
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
	}
	
	private void initComponents()
	{
		//Panel
		PanelComponentField = new MainPanel(this);
		PanelComponentField.setBounds(MainPanelArea);
		this.add(PanelComponentField);
		
	}

}
