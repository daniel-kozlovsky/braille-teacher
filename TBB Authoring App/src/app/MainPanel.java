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
		buttonNewScenario.getAccessibleContext().setAccessibleName("Create a new scenario");
		buttonNewScenario.getAccessibleContext()
				.setAccessibleDescription("Create a new scenario using the scenario editor.");

		buttonNewScenario.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				openScenarioCreator(null);
			}
		});

		// import
		buttonImportScenario = new JButton("Import scenario");
		buttonImportScenario.getAccessibleContext().setAccessibleName("Import an existing scenario");
		buttonImportScenario.getAccessibleContext()
				.setAccessibleDescription("Open and edit an existing scenario file.");

		buttonImportScenario.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				JFileChooser fileChooser = new JFileChooser();
				int result = fileChooser.showOpenDialog(buttonNewScenario);
				if (result == JFileChooser.APPROVE_OPTION) {
				Scenario importedScenario = ScenarioFormatter.importParse(fileChooser.getCurrentDirectory().getAbsolutePath()+"\\"+fileChooser.getSelectedFile().getName());
				
				System.out.println(fileChooser.getCurrentDirectory().getAbsolutePath()+"\\"+fileChooser.getSelectedFile().getName());
				
				openScenarioCreator(importedScenario);
				}
				
				/*if (result == JFileChooser.APPROVE_OPTION) {
					try {

						Scanner scanner = new Scanner(fileChooser.getSelectedFile());
						while (scanner.hasNext()) {
							// TODO: read and parse selected file
							scanner.next();
						}
						scanner.close();

						openScenarioCreator();

					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}*/
			}
		});
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		this.add(buttonImportScenario);
		this.add(buttonNewScenario);
	}

	private void openScenarioCreator(Scenario importedScenario) {

		ScenarioCreator sc = new ScenarioCreator(parent, importedScenario);
		parent.getContentPane().removeAll();
		parent.getContentPane().add(sc);
		parent.revalidate();
		sc.setVisible(true);

	}

}
