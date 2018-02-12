package app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.*;
import scenario.Scenario;
import scenario.EnumPossibleCommands;

public class MainPanel extends JPanel {

	private JFrame parent;

	JButton buttonNewScenario, buttonImportScenario;
	Scenario importedScenario = new Scenario();

	public MainPanel(JFrame parent) {
		this.parent = parent;
		initComponents();
	}

	private void initComponents() {
		
		//mock import scenario
		for(int i=0;i<10;i++){
		importedScenario.addCommand(
				importedScenario.createNewCommand(EnumPossibleCommands.PAUSE, new Object[] { i+10 }));
			
		}

		// import
		buttonImportScenario = new JButton("Import Scenario");
		buttonImportScenario.getAccessibleContext().setAccessibleName("Import an existing Scenario");
		buttonImportScenario.getAccessibleContext()
				.setAccessibleDescription("Open and edit an existing Scenario file.");

		buttonImportScenario.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				JFileChooser fileChooser = new JFileChooser();
				int result = fileChooser.showOpenDialog(buttonNewScenario);

				if (result == JFileChooser.APPROVE_OPTION) {
					try {

						Scanner scanner = new Scanner(fileChooser.getSelectedFile());
						while (scanner.hasNext()) {
							//TODO: read and parse selected file
							scanner.next();
						}
						scanner.close();

						openScenarioCreator();

					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
		});

		// create
		buttonNewScenario = new JButton("New Scenario");
		buttonNewScenario.getAccessibleContext().setAccessibleName("Create a New Scenario");
		buttonNewScenario.getAccessibleContext()
				.setAccessibleDescription("Create a new Scenario using the Scenario Editor.");

		buttonNewScenario.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				openScenarioCreator();
			}
		});
		this.add(buttonImportScenario);
		this.add(buttonNewScenario);
	}

	private void openScenarioCreator() {

		ScenarioCreator sc = new ScenarioCreator(parent, importedScenario);
		parent.getContentPane().removeAll();
		parent.getContentPane().add(sc);
		parent.revalidate();
		sc.setVisible(true);

	}

}
