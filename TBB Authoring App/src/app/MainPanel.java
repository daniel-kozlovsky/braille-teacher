package app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import javax.swing.*;
import scenario.Scenario;
import scenario.ScenarioFormatter;
import java.awt.FlowLayout;
import java.awt.Rectangle;

public class MainPanel extends JPanel {

	private JFrame parent;

	JButton buttonNewScenario, buttonImportScenario;

	public MainPanel(JFrame parent) {
		this.parent = parent;
		initComponents();
	}

	private void initComponents() {

		// create
		buttonNewScenario = new JButton("New scenario");

		buttonNewScenario.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				openScenarioCreator(null);
			}
		});

		// import
		buttonImportScenario = new JButton("Import scenario");

		buttonImportScenario.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				// choose a file to import as a scenario
				JFileChooser fileChooser = new JFileChooser();
				int result = fileChooser.showOpenDialog(buttonNewScenario);
				if (result == JFileChooser.APPROVE_OPTION) {
					Scenario importedScenario = ScenarioFormatter
							.importParse(fileChooser.getCurrentDirectory().getAbsolutePath() + "\\"
									+ fileChooser.getSelectedFile().getName());
					openScenarioCreator(importedScenario);
				}
			}
		});
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		this.add(buttonImportScenario);
		this.add(buttonNewScenario);

		// accessibility
		buttonNewScenario.getAccessibleContext().setAccessibleName("Create a new scenario");
		buttonNewScenario.getAccessibleContext()
				.setAccessibleDescription("Use the scenario editor to build a scenario from scratch");

		buttonImportScenario.getAccessibleContext().setAccessibleName("Import an existing scenario");
		buttonImportScenario.getAccessibleContext().setAccessibleDescription("Open and edit an existing scenario file");

	}

	private void openScenarioCreator(Scenario importedScenario) {

		ScenarioCreator sc = new ScenarioCreator(parent, importedScenario);
		parent.getContentPane().removeAll();
		parent.getContentPane().add(sc);
		parent.revalidate();
		sc.setVisible(true);

	}

}
