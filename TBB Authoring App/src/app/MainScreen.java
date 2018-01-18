package app;

import java.awt.Dimension;
import java.awt.HeadlessException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class MainScreen extends JFrame {
	
	//common button size for styling consistency
	public final Dimension BUTTON_SIZE = new Dimension(100, 30);
	
	JPanel PanelComponentField;
	
	JButton buttonNewScenario;
	JButton buttonImportScenario;
	
	
	public MainScreen()
	{
		initFrame();
		initComponents();
		
		//pack();
	}
	
	private void initFrame()
	{
		setTitle("Authoring App");
		setSize(500, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		//Panel
		PanelComponentField = new JPanel();
		this.add(PanelComponentField);
		
	}
	
	private void initComponents()
	{
		
		//Import new Scenario Button
		buttonImportScenario = new JButton("Import Scenario");
		buttonImportScenario.setSize(BUTTON_SIZE);
		buttonImportScenario.setLocation(30,30);
		buttonImportScenario.setVisible(true);
		PanelComponentField.add(buttonImportScenario);
		
		//Create new Scenario button
		buttonNewScenario = new JButton("New Scenario...");
		buttonNewScenario.setSize(BUTTON_SIZE);
		buttonNewScenario.setLocation(30, 50);
		buttonNewScenario.setVisible(true);
		
		
		PanelComponentField.add(buttonImportScenario);
		PanelComponentField.add(buttonNewScenario);
		
	}

}
