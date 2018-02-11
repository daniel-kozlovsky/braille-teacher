package app;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;

import javax.swing.*;

import scenario.Scenario;

public class ScenarioCreator extends JPanel {

	private static final long serialVersionUID = 1L;
    private JPanel panel = new JPanel();
    private JButton btnAddCommand = new JButton("Add command");
    private JButton btnRemove = new JButton("Remove");
    private JButton btnMoveUp = new JButton("Move Up");
    private JButton btnMoveDown = new JButton("Move Down");
    private JButton btnEdit = new JButton("Edit");
    private JButton btnRepeat = new JButton("Repeat");
    private String cell;
    private String buttons;
	private JFrame parent;
	private Scenario importedScenario;
	private JButton addAudioButton;

	public ScenarioCreator(JFrame parent, Scenario importedScenario) {
		this.parent = parent;
        this.setLayout(null);
		this.importedScenario = importedScenario;
        this.parent.setTitle("Creat new Scenario");
        // creating new  window to add the number of cells
        JOptionPane jcell= new JOptionPane();
        jcell.getAccessibleContext().setAccessibleDescription(" How many cells do you need ");
        cell=   jcell.showInputDialog(parent," How many cells do you need ",null);
        //creating new window to add the numnber of buttons
        JOptionPane jbutton= new JOptionPane();
        jbutton.getAccessibleContext().setAccessibleDescription(" How many buttons do you need ");
        buttons =   jbutton.showInputDialog(parent," How many buttons do you need ",null);
    initComponents();
    this.parent.setSize(parent.getWidth(), 400);
		
		
		
	}

	private void initComponents() {
        
        panel.setBounds(6, 10, 272, 513);
        add(panel);
        
        btnAddCommand.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //addCommand command = new addCommand();

        		AudioRecorder sc = new AudioRecorder();
        		parent.getContentPane().removeAll();
        		parent.getContentPane().add(sc);
        		parent.revalidate();
        		sc.setVisible(true);
            }
        });

        btnAddCommand.setBounds(288, 20, 117, 29);
        add(btnAddCommand);
        
        btnRemove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        btnRemove.setBounds(288, 69, 117, 29);
        add(btnRemove);
        btnMoveUp.setBounds(288, 119, 117, 29);
        add(btnMoveUp);
        btnMoveDown.setBounds(288, 173, 117, 29);
        add(btnMoveDown);
        btnEdit.setBounds(288, 233, 117, 29);
        add(btnEdit);
        btnRepeat.setBounds(288, 288, 117, 29);
        add(btnRepeat);
        
		JTextArea audioTextField = new JTextArea();
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


