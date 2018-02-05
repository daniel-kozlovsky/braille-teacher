package app;

import java.awt.Dimension;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;

import scenario.Scenario;
import scenario.ScenarioCommand;
import scenario.EnumPossibleCommands;

public class MainPanel extends JPanel {
	
	//common button size for styling consistency
	public final Dimension BUTTON_SIZE = new Dimension(100, 30);
	private JFrame parent;
	
	//check master
	
	JButton buttonNewScenario;
	JButton buttonImportScenario;
	Scenario importedScenario = new Scenario();
	
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
				
				ScenarioCreator sc = new ScenarioCreator(parent,importedScenario);
				parent.getContentPane().removeAll();
				parent.getContentPane().add(sc);
				parent.revalidate();
				sc.setVisible(true);
				
			}
		});
		
		buttonImportScenario.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.showOpenDialog(buttonNewScenario);
				try {
					Scanner scanner = new Scanner(fileChooser.getSelectedFile());
					
					int i=0;
					while(scanner.hasNext()){
						importedScenario.addCommand(importedScenario.createNewCommand(EnumPossibleCommands.PAUSE, new Object[] {10}));
						ta.append(importedScenario.getCommand(i).getName()+'\n');
						i++;
						scanner.next();
					}
					scanner.close();
					
					ScenarioCreator sc = new ScenarioCreator(parent, importedScenario);
					parent.getContentPane().removeAll();
					parent.getContentPane().add(sc);
					parent.revalidate();
					sc.setVisible(true);
					
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
