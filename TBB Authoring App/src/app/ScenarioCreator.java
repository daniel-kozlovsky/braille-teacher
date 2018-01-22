package app;

import java.awt.*;
import java.awt.image.ImageObserver;

import javax.swing.*;

public class ScenarioCreator extends JPanel {

	private static final long serialVersionUID = 1L;
	private JFrame parent;
	private JButton addAudioButton;

	public ScenarioCreator(JFrame parent) {
		this.parent = parent;
        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(new FlowLayout());
		initComponents();
	}
	
	private void initComponents(){

		JTextField audioTextField = new JTextField(20);
		audioTextField.getAccessibleContext().setAccessibleDescription("Textfield for speaker audio script.");
		
		addAudioButton = new JButton("Add Audio");
		addAudioButton.setLocation(ImageObserver.WIDTH/2,0);
		addAudioButton.setVisible(true);
		
		this.add(addAudioButton);
		this.add(audioTextField);
		
	}

}
