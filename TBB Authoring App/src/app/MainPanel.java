package app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import scenario.Scenario;
import scenario.ScenarioFormatter;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Font;

public class MainPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JFrame parent;

	JButton buttonNewScenario, buttonImportScenario;
	private JPanel panel_1;
	private JLabel lblHello;

	public MainPanel(JFrame parent) {
		this.parent = parent;

		//parent.setMinimumSize(new Dimension(300, 300));
		//parent.setSize(new Dimension(300, 300));
		
		initComponents();
	}

	private void initComponents() {
		setLayout(new GridLayout(0, 1, 0, 0));
		
		panel_1 = new JPanel();
		add(panel_1);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		lblHello = new JLabel("TBB Authoring App");
		lblHello.setHorizontalAlignment(SwingConstants.CENTER);
		lblHello.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel_1.add(lblHello);
		
		JPanel panel = new JPanel();
		add(panel);
		
				// import
				buttonImportScenario = new JButton("Import scenario");
				panel.add(buttonImportScenario);
				
						// create
						buttonNewScenario = new JButton("New scenario");
						panel.add(buttonNewScenario);
						
								buttonNewScenario.addActionListener(new ActionListener() {
						
									@Override
									public void actionPerformed(ActionEvent arg0) {
										openScenarioCreator(null);
									}
								});
								
										// accessibility
										buttonNewScenario.getAccessibleContext().setAccessibleName("Create a new scenario");
										buttonNewScenario.getAccessibleContext()
												.setAccessibleDescription("Use the scenario editor to build a scenario from scratch");
				
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
