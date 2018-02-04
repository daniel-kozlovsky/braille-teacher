package app;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.Dimension;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;
import javax.swing.JOptionPane;

import javax.swing.*;


public class ScenarioCreator extends JPanel {

	// private static final long serialVersionUID = 1L;
	private JFrame parent;

	private JButton addAudioButton;
	private TextArea ta = new TextArea();
	private JButton addquestion;
	private JButton addItem;
	private JButton performAction;
	private JButton pause;
	
	private  int senarionumber;
	String cell;
	String buttons;
	private JComboBox comboBox;
	private JPanel panel;
	public ScenarioCreator(JFrame parent) {
		this.parent = parent;
		this.setLayout(null);
		// creating new  window to add the number of cells
		JOptionPane jcell= new JOptionPane();
		jcell.getAccessibleContext().setAccessibleDescription(" How many cells do you need ");
		cell=	jcell.showInputDialog(parent," How many cells do you need ",null);
		//creating new window to add the numnber of buttons
		JOptionPane jbutton= new JOptionPane();
		jbutton.getAccessibleContext().setAccessibleDescription(" How many buttons do you need ");
		buttons =	jbutton.showInputDialog(parent," How many buttons do you need ",null);
		
		
		
		try {
			initComponents();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	

	private void initComponents() throws FileNotFoundException, UnsupportedEncodingException {
		
		//creating the Scenario file
		PrintWriter writer = new PrintWriter("Senario_"+senarionumber+".txt", "UTF-8");
		senarionumber++;
		
		//adding cells to the Scenario file
		writer.println("Cell " +Integer.parseInt(cell));
		//adding Button to the Scenario file
		writer.println("Button " +Integer.parseInt(buttons));
		
		JTextField questionText = new JTextField(30);
		questionText.setLocation(192, 229);
		questionText.setSize(241, 30);

		
		// add Audio button customization
		JTextField audioTextField = new JTextField(20);
		audioTextField.setLocation(192, 173);
		audioTextField.setSize(241, 30);
		audioTextField.getAccessibleContext().setAccessibleDescription("Textfield for speaker audio script.");
		addAudioButton = new JButton("Add Audio");
		addAudioButton.setLocation(7, 162);
		addAudioButton.setVisible(true);
		addAudioButton.setSize(104, 50);
		addAudioButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				JFileChooser fc = new JFileChooser();
				fc.showOpenDialog(addAudioButton);
				writer.println(fc.getName());

			}
		});
		
		
			// add item  customization
		addItem = new JButton("Add item");
		addItem.getAccessibleContext().setAccessibleDescription("Add question");
		addItem.setVisible(true);
		addItem.setLocation(7, 336);
		addItem.setSize(104, 49);
		addItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		
		
		
		// add question button customization
		addquestion = new JButton("Add Question");
		addquestion.getAccessibleContext().setAccessibleDescription("Add question");
		addquestion.setVisible(true);
		addquestion.setLocation(7, 222);
		addquestion.setSize(107, 43);
		addquestion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					Scanner s = new Scanner(questionText.getText());
					while (s.hasNext()) {
						ta.append(s.nextLine() + '\n');
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		performAction = new JButton("perform action ");
		performAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		performAction.setLocation(275, 397);
		performAction.setVisible(true);
		performAction.getAccessibleContext().setAccessibleDescription("click on that buttom to performe the action choesed from the drop down menu");
		performAction.setSize(155, 57);
		// pause
		pause=new JButton("pause");
		pause.setVisible(true);
		pause.getAccessibleContext().setAccessibleDescription("pause");
		pause.setLocation(7, 283);
		pause.setSize(104,43);
		
		
		JTextField pausetText = new JTextField(5);
		pausetText.setLocation(192, 290);
		pausetText.setSize(241, 30);
		writer.close();
		this.add(addAudioButton);
		this.add(audioTextField);
		this.add(addquestion);
		this.add(questionText);
		this.add(addItem);
		this.add(pause);
		ta.setBounds(0, 0, 440, 140);
		add(ta);
		ta.setVisible(true);
		this.add(pausetText);
		
		panel = new JPanel();
		panel.setLocation(10, 0);
		add(panel);
		panel.setSize(490, 158);
		this.add(performAction);
		
		comboBox = new JComboBox();
		comboBox.setBounds(10, 406, 166, 38);
		add(comboBox);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Skip", "Move up", "Move down", "reset ", "ClearAll", "Repet ", "disp-string"}));
		comboBox.getAccessibleContext().setAccessibleDescription("actions to perform on the scenario file Choese from the drop down menu ");
	}

}
