package app;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MainPanel extends JPanel {
	
	//common button size for styling consistency
	public final Dimension BUTTON_SIZE = new Dimension(100, 30);
	
	
	JButton buttonNewScenario;
	JButton buttonImportScenario;
	
	public MainPanel()
	{
		initComponents();
	}
	
	private void initComponents()
	{
		//Import new Scenario Button
		buttonImportScenario = new JButton("Import Scenario");
		buttonImportScenario.setSize(BUTTON_SIZE);
		buttonImportScenario.setLocation(30,30);
		buttonImportScenario.setVisible(true);
		
		//Create new Scenario button
		buttonNewScenario = new JButton("New Scenario...");
		buttonNewScenario.setSize(BUTTON_SIZE);
		buttonNewScenario.setLocation(30, 50);
		buttonNewScenario.setVisible(true);
		
		this.add(buttonImportScenario);
		this.add(buttonNewScenario);
	}

}
