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
import org.eclipse.wb.swing.FocusTraversalOnArray;

public class ScenarioCreator extends JPanel {

	// private static final long serialVersionUID = 1L;
	private JFrame parent;

	private JButton addAudioButton;
	private TextArea ta = new TextArea();
	private JButton addquestion;
	private JButton addItem;
	private JButton moveup;
	private JButton moveDown;
	private  int senarionumber=1;
	String cell;
	String buttons;

	public ScenarioCreator(JFrame parent) {
		this.parent = parent;
		// creating new  window to add the number of cells
		JOptionPane jcell= new JOptionPane();
		jcell.getAccessibleContext().setAccessibleDescription(" How many cells do you need ");
		cell=	jcell.showInputDialog(parent," How many cells do you need ",null);
		//creating new window to add the numnber of buttons
		JOptionPane jbutton= new JOptionPane();
		jbutton.getAccessibleContext().setAccessibleDescription(" How many buttons do you need ");
		buttons=	jbutton.showInputDialog(parent," How many buttons do you need ",null);
		
		
		
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
// text field  customization 
		ta.setLocation(HEIGHT / 2, WIDTH / 2);
		ta.setLocation(ImageObserver.WIDTH / 2, 5);
		ta.setVisible(true);
		
		
		
		
		
		
		
		// add Audio button customization
		JTextField audioTextField = new JTextField(30);
		audioTextField.getAccessibleContext().setAccessibleDescription("Textfield for speaker audio script.");
		addAudioButton = new JButton("Add Audio");
		addAudioButton.setLocation(ImageObserver.WIDTH / 2, 0);
		addAudioButton.setVisible(true);
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
		addItem.setLocation(50, 50);
		addItem.setSize(50, 50);
		addItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		
		
		
		// add question button customization
		addquestion = new JButton("Add Question");
		addquestion.getAccessibleContext().setAccessibleDescription("Add question");
		addquestion.setVisible(true);
		addquestion.setLocation(50, 50);
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
		
		
		moveup= new JButton("move up");
		moveup.setVisible(true);
		moveup.getAccessibleContext().setAccessibleDescription("move up ");
		
		moveDown = new JButton("move down");
		moveDown.setVisible(true);
		moveDown.getAccessibleContext().setAccessibleDescription("move Down ");
		writer.close();
		this.add(ta);
		this.add(addAudioButton);
		this.add(audioTextField);
		this.add(addquestion);
		this.add(questionText);
		this.add(addItem);
		this.add( moveup);
		this.add( moveDown);
	}

}
