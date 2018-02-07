package app;

import java.awt.Dimension;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.*;

public class MainPanel extends JPanel {
	
	//common button size for styling consistency
	public final Dimension BUTTON_SIZE = new Dimension(100, 30);
	private JFrame parent;
	
	//check master
	
	JButton buttonNewScenario;
	JButton buttonImportScenario;
	
	public MainPanel(JFrame parent)
	{
		this.parent = parent;
		initComponents();
	}
	
	private void initComponents()
	{
		
		TextArea ta = new TextArea();
		
		//Import new Scenario Button
		buttonImportScenario = new JButton("Import Scenario");
		buttonImportScenario.setSize(BUTTON_SIZE);
		buttonImportScenario.setLocation(30,30);
		buttonImportScenario.getAccessibleContext().setAccessibleName("Import Scenario");
		buttonImportScenario.getAccessibleContext().setAccessibleDescription("description test");
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
		
		buttonImportScenario.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				JFileChooser fc = new JFileChooser();
				fc.showOpenDialog(buttonNewScenario);
				try {
					Scanner sc = new Scanner(fc.getSelectedFile());
					
					StringBuffer input = new StringBuffer();
					
					while(sc.hasNext()){
						ta.append(sc.nextLine()+'\n');
					}
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
		this.add(ta);
		this.add(buttonImportScenario);
		this.add(buttonNewScenario);
	}

}