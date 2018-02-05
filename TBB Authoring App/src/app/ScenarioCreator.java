package app;

import java.awt.*;
import java.awt.image.ImageObserver;

import javax.swing.*;

import scenario.Scenario;

public class ScenarioCreator extends JPanel {

	private static final long serialVersionUID = 1L;
	private JFrame parent;
	private Scenario importedScenario;
	private JButton addAudioButton;

	public ScenarioCreator(JFrame parent, Scenario importedScenario) {
		this.parent = parent;
		this.importedScenario = importedScenario;
		BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(new FlowLayout());
		initComponents();
	}

	private void initComponents() {

		JTextField audioTextField = new JTextField(20);
		audioTextField.getAccessibleContext().setAccessibleDescription("Textfield for speaker audio script.");

		addAudioButton = new JButton("Add Audio");
		addAudioButton.setLocation(ImageObserver.WIDTH / 2, 0);
		addAudioButton.setVisible(true);

		StringBuffer input = new StringBuffer();
		for (int i=0;i<10;i++) {
			input.append("Command: "+importedScenario.getCommand(i).getName()+" Args: "+importedScenario.getCommand(i).getArguments()+'\n');
		}

		audioTextField.setText(input.toString());

		this.add(addAudioButton);
		this.add(audioTextField);

	}

}
