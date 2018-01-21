package app;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MainPanel extends JPanel {
	
	//common button size for styling consistency
	public final Dimension BUTTON_SIZE = new Dimension(100, 30);
	private JFrame parent;
	
	
	JButton buttonNewScenario;
	JButton buttonImportScenario;
	
	public MainPanel(JFrame parent)
	{
		this.parent = parent;
		initComponents();
	}
	
	private void initComponents()
	{
		//Import new Scenario Button
		buttonImportScenario = new JButton("Import Scenario");
		buttonImportScenario.setSize(BUTTON_SIZE);
		buttonImportScenario.setLocation(30,30);
		buttonImportScenario.setVisible(true);
		buttonImportScenario.getAccessibleContext().setAccessibleDescription("Open Scenario file.");
		
		//Create new Scenario button
		buttonNewScenario = new JButton("New Scenario...");
		buttonNewScenario.setSize(BUTTON_SIZE);
		buttonNewScenario.setLocation(30, 50);
		buttonNewScenario.setVisible(true);
		buttonNewScenario.getAccessibleContext().setAccessibleDescription("Create a new Scenario.");
		buttonNewScenario.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				ScenarioCreator sc = new ScenarioCreator(parent);
				parent.getContentPane().removeAll();
				parent.getContentPane().add(sc);
				parent.revalidate();
				sc.setVisible(true);
				
			}
		});
		
		this.add(buttonImportScenario);
		this.add(buttonNewScenario);
	}

}
