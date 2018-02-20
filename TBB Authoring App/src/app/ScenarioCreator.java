package app;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import scenario.EnumPossibleCommands;
import scenario.Scenario;
import scenario.ScenarioCommand;
public class ScenarioCreator extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	//Sizes
	private Rectangle scenarioCreatorBounds;
	private final int preferredButtonWidth = 200;
	//misc
    private int comboIndex;
    private String qst;
    private int secpause;
 	private Scenario workingScenario;
    private int numCells;
    private int numButtons;
    private JFrame parent;
	//list
	private DefaultListModel<String> sessionModel;
 	private JList<String> sessionScenarioList;
    //panels
    private JPanel scenarioProgressPanel;
    private JPanel componentsPanel;
    //buttons
    private JButton btnAdd;
    private JButton btnRemove;
    private JButton btnMoveUp;
    private JButton btnMoveDown;
    private JButton btnEdit;
    private JButton btnRepeat;
    //labels
    private JLabel lblNewLabel;
    //combo-boxes
    private JComboBox<String> comboBox;
    //fonts
    Font mainButtonFont = new Font("Tahoma", Font.PLAIN, 14);
    //colours
    Color mainButtonColour = new Color(0, 0, 0);
    //Layouts
    GroupLayout mainGroupLayout;
    GroupLayout gl_componentsPanel;
 	
 	public ScenarioCreator(JFrame parent, Scenario sessionScenario) {
 		
 		this.parent = parent;
 		scenarioCreatorBounds = parent.getBounds();
 		this.setBounds(scenarioCreatorBounds);
		this.workingScenario = sessionScenario;
        this.parent.setTitle("Create new Scenario");
        
        initComponents();
        initLayout();
        
        this.setLayout(mainGroupLayout);
        componentsPanel.setLayout(gl_componentsPanel);
        
        //Dialog for number of cells and buttons
        newScenarioSetUpDialog();
        
        workingScenario = new Scenario(numCells, numButtons);
        
        //populates list box
        updateSessionModel(); 
    }
 	
 	private void initComponents()
 	{
 		//Labels
 	    lblNewLabel = new JLabel("Please choose a command from the top down menu ");
 		//Buttons
 		btnAdd = new JButton("Add Command");
 		btnAdd.setFont(mainButtonFont);
        btnAdd.setForeground(mainButtonColour);
        btnAdd.addActionListener( new ActionListener () {
        	public void actionPerformed(ActionEvent arg0) 
        	{
        		btnAddClickHandler();
        	}
        	});
        
        btnRemove = new JButton("Remove");
        btnRemove.setFont(mainButtonFont);
        btnRemove.setForeground(mainButtonColour);
        btnRemove.addActionListener( new ActionListener () {
        	public void actionPerformed(ActionEvent arg0) 
        	{
        		btnRemoveClickHandler();
        	}
        	});
        
        btnMoveUp = new JButton("Move Up");
        btnMoveUp.setFont(mainButtonFont);
        btnMoveUp.setForeground(mainButtonColour);
        btnMoveUp.addActionListener( new ActionListener () {
        	public void actionPerformed(ActionEvent arg0) 
        	{
        		btnMoveUpClickHandler();
        	}
        	});
        
        btnMoveDown = new JButton("Move down");
        btnMoveDown.setFont(mainButtonFont);
        btnMoveDown.setForeground(mainButtonColour);
        btnMoveDown.addActionListener( new ActionListener () {
        	public void actionPerformed(ActionEvent arg0) 
        	{
        		btnMoveDownClickHandler();
        	}
        	});
        
        btnEdit = new JButton("Edit");
        btnEdit.setFont(mainButtonFont);
        btnEdit.setForeground(mainButtonColour);
        btnEdit.addActionListener( new ActionListener () {
        	public void actionPerformed(ActionEvent arg0) 
        	{
        		btnMoveDownClickHandler();
        	}
        	});
        
        btnRepeat = new JButton("Repeat");
        btnRepeat.setFont(mainButtonFont);
        btnRepeat.setForeground(mainButtonColour);
        btnRepeat.addActionListener( new ActionListener () {
        	public void actionPerformed(ActionEvent arg0) 
        	{
        		btnRepeatClickHandler();
        	}
        	});
        
        //ListBox
        sessionModel = new DefaultListModel<>();
        sessionScenarioList = new JList<String>();
 		sessionScenarioList.setModel(sessionModel);
     
        //Panels
        scenarioProgressPanel = new JPanel();
        scenarioProgressPanel.setLayout(new BorderLayout(0, 0));
        scenarioProgressPanel.add(sessionScenarioList);
        componentsPanel = new JPanel();
        
        //combo-boxes
        comboBox = new JComboBox<String>();
        //items in the combo box 
        comboBox.addItem("Add Audio");
		comboBox.addItem("Add a Question");
		comboBox.addItem("Pause");
		comboBox.addItem("disp-string");
		comboBox.addItem("Skip");
		comboBox.addItem("Skip Button");
		comboBox.addItem("Repeat Button");
		comboBox.addItem("User input");
		comboBox.addItem("disp clearAll");
		comboBox.addItem("disp clear cell");
		comboBox.addItem("disp cell lower");
		comboBox.addItem("disp cell pins");
        comboBox.addActionListener( new ActionListener () {
        	public void actionPerformed(ActionEvent arg0)
        	{
        		comboBoxSelectionChangedHandler();
        	}
        });
 	}
 	
 	//TODO change component layout to have two columns if enough space after resizing window.
 	private void initLayout()
 	{
 		//group layout customizations 
        mainGroupLayout = new GroupLayout(this);
        mainGroupLayout.setHorizontalGroup(
            mainGroupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(mainGroupLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(scenarioProgressPanel, 0, 200, Short.MAX_VALUE)
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addComponent(componentsPanel, 0, 200, Short.MAX_VALUE)
                    .addContainerGap())
        );
        mainGroupLayout.setVerticalGroup(
            mainGroupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(mainGroupLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(mainGroupLayout.createParallelGroup(Alignment.LEADING)
                        .addComponent(scenarioProgressPanel, GroupLayout.PREFERRED_SIZE, 496, GroupLayout.PREFERRED_SIZE)
                        .addComponent(componentsPanel, GroupLayout.PREFERRED_SIZE, 390, Short.MAX_VALUE))
                    .addContainerGap())
        );
        
        // componentsPanel customizations 
        gl_componentsPanel = new GroupLayout(componentsPanel);
        gl_componentsPanel.setHorizontalGroup(
            gl_componentsPanel.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_componentsPanel.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_componentsPanel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_componentsPanel.createSequentialGroup()
                            .addGap(10)
                            .addComponent(comboBox, 0, preferredButtonWidth, preferredButtonWidth)
                            .addContainerGap())
                        .addGroup(Alignment.LEADING, gl_componentsPanel.createSequentialGroup()
                            .addComponent(lblNewLabel,  0, 200, Short.MAX_VALUE)
                            .addGap(47))
                        .addGroup(Alignment.LEADING, gl_componentsPanel.createSequentialGroup()
                            .addGroup(gl_componentsPanel.createParallelGroup(Alignment.LEADING)
                                .addComponent(btnMoveUp, Alignment.LEADING,  0, preferredButtonWidth, preferredButtonWidth)
                                .addComponent(btnRepeat, Alignment.LEADING, 0, preferredButtonWidth, preferredButtonWidth)
                                .addComponent(btnEdit, Alignment.LEADING, 0, preferredButtonWidth, preferredButtonWidth)
                                .addComponent(btnMoveDown, 0, preferredButtonWidth, preferredButtonWidth))
                            .addGap(28))
                        .addGroup(Alignment.LEADING, gl_componentsPanel.createSequentialGroup()
                            .addGroup(gl_componentsPanel.createParallelGroup(Alignment.LEADING)
                                .addComponent(btnRemove, Alignment.LEADING, 0, preferredButtonWidth, preferredButtonWidth)
                                .addComponent(btnAdd, 0, preferredButtonWidth, preferredButtonWidth))
                            .addGap(28))))
        );
        gl_componentsPanel.setVerticalGroup(
            gl_componentsPanel.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_componentsPanel.createSequentialGroup()
                    .addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addComponent(comboBox, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addGap(38)
                    .addComponent(btnAdd, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addGap(18)
                    .addComponent(btnRemove, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addGap(27)
                    .addComponent(btnMoveUp, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addGap(18)
                    .addComponent(btnMoveDown, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addGap(18)
                    .addComponent(btnEdit, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addGap(20)
                    .addComponent(btnRepeat, GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                    .addGap(8))
        );
        
 	}
 	
 	/**
 	 * Handles the event when a selection is made in the combo box.
 	 */
    private void comboBoxSelectionChangedHandler() 
    {
    	comboIndex = comboBox.getSelectedIndex();
    }
    
    /**
     * Handles the click event for the add command button
     */
    //TODO BUG: some commands throw exceptions
    private void btnAddClickHandler()
    {
    	// add audio add command 
		if(comboIndex==0)
		{
		addAudio audio = new addAudio(parent);
		parent.revalidate();
		audio.setVisible(true);
		}
		// add question add command 
		else if(comboIndex==1)
		{
			 JOptionPane question  = new JOptionPane();
			 question.getAccessibleContext().setAccessibleDescription(" Enter the question below ");
			 qst = question.showInputDialog(parent, "Enter the question below  ", null);
			 EnumPossibleCommands cmd = EnumPossibleCommands.DISP_STRING ;
			workingScenario.addCommand(workingScenario.createNewCommand(cmd,  new String[] {qst}));
		}
		// pause command 
		else if (comboIndex == 2)
		{
			JOptionPane pause  = new JOptionPane();
			pause.getAccessibleContext().setAccessibleDescription(" please enter the number of seconds you want to pause ");
			secpause= Integer.parseInt(JOptionPane.showInputDialog(parent, "please enter the number of seconds you want to pause  ", null));
			Object[] obj = new Object[] {secpause};
			workingScenario.addCommand(workingScenario.createNewCommand(EnumPossibleCommands.PAUSE, obj ));
		}
		// disp-string
		else if(comboIndex==3)
		{
			String dispstring; 
			JOptionPane disp = new JOptionPane();
			disp.getAccessibleContext().setAccessibleDescription("Please write the string disp ");
			dispstring= disp.showInputDialog(parent,"please write the String here ",null);
			EnumPossibleCommands cmd = EnumPossibleCommands.DISP_STRING ;
			workingScenario.addCommand(workingScenario.createNewCommand(cmd,  new String[] {dispstring}));
		}
		
		//Skip
		else if (comboIndex==4)
		{ 
			String skip;
			JOptionPane skipp = new JOptionPane();
			skipp.getAccessibleContext().setAccessibleDescription("Please write the string you want to skip ");
			skip= skipp.showInputDialog(parent,"please write the String you want to skip ",null);
			EnumPossibleCommands cmd = EnumPossibleCommands.SKIP ;
			workingScenario.addCommand(workingScenario.createNewCommand(cmd,  new String[] {skip}));
		}
		// skip button
		else if(comboIndex ==5)
		{
			String str;
			int bnts;
			JTextField btn = new JTextField();
			
			Object [] obj= {
					"please enter the button number:",btn,
					"please enter the string here:",
			};
			
			JOptionPane skipp  = new JOptionPane();	
			str=(JOptionPane.showInputDialog(null, obj,"skip button",JOptionPane.OK_CANCEL_OPTION));
			bnts=Integer.parseInt(btn.getText());
			EnumPossibleCommands cmd = EnumPossibleCommands.SKIP_BUTTON;
			workingScenario.addCommand(workingScenario.createNewCommand(cmd, new Object [] {bnts,str}));
			}
		
		//Repeat Button
		else if(comboIndex ==6 )
		{

			JOptionPane rebnt  = new JOptionPane();
			rebnt.getAccessibleContext().setAccessibleDescription(" please enter the button number you want to repeat ");
			int btns;
			btns = Integer.parseInt(rebnt.showInputDialog(parent, "please enter the button number you want to repeat ", null));
			EnumPossibleCommands cmd = EnumPossibleCommands.REPEAT_BUTTON ;
			workingScenario.addCommand(workingScenario.createNewCommand(cmd, new Object [] {btns}));
		}
		// disp clear cell
		else if( comboIndex == 9)
		{
			int cells;
			JOptionPane clrcell  = new JOptionPane();
			clrcell.getAccessibleContext().setAccessibleDescription(" please enter the cell number you want to clear ");
			cells = Integer.parseInt(clrcell.showInputDialog(parent, "please enter the cell number you want to clear ", null));
			EnumPossibleCommands cmd = EnumPossibleCommands.DISP_CLEAR_CELL ;
			workingScenario.addCommand(workingScenario.createNewCommand(cmd, new Object [] {cells}));
		}	
		// disp cell pin
		else if(comboIndex==11)
		{
			int cell;
			int pin;
			JTextField pins = new JTextField();
			
			Object [] obj= {
					"please enter the cell number:",pins,
					"please enter the pin number :",
			};
			
			JOptionPane skipp  = new JOptionPane();	
			pin=Integer.parseInt(JOptionPane.showInputDialog(null, obj,"skip button",JOptionPane.OK_CANCEL_OPTION));
			cell=Integer.parseInt(pins.getText());
			EnumPossibleCommands cmd = EnumPossibleCommands.DISP_CELL_PINS;
			workingScenario.addCommand(workingScenario.createNewCommand(cmd, new Object [] {cell,pin}));
			}
		
		updateSessionModel();
    }
    
    /**
     * Handles the click event for the remove command button
     */
    private void btnRemoveClickHandler()
    {
    	
    }
    
    /**
     * Handles the click event for the add command button
     */
    private void btnMoveUpClickHandler()
    {
    	swapCommands(sessionScenarioList.getSelectedIndex(),sessionScenarioList.getSelectedIndex()-1);
    }
    
    /**
     * Handles the click event for the add command button
     */
    private void btnMoveDownClickHandler()
    {
    	swapCommands(sessionScenarioList.getSelectedIndex(),sessionScenarioList.getSelectedIndex()+1);
    }
    
    /**
     * Handles the click event for the add command button
     */
    private void btnEditClickHandler()
    {
    	
    }
    
    /**
     * Handles the click event for the add command button
     */
    private void btnRepeatClickHandler()
    {
    	
    }
    
    /**
     * Displays a new dialog box that prompts the user for braille cell and button amounts 
     */
    public void newScenarioSetUpDialog()
    {
        JOptionPane jcell  = new JOptionPane();
        jcell.getAccessibleContext().setAccessibleDescription("How many braille cells do you need?");
        numCells = Integer.parseInt(jcell.showInputDialog(parent, "How many braille cells do you need?", null));
        JOptionPane jbutton = new JOptionPane();
        jbutton.getAccessibleContext().setAccessibleDescription("How many buttons do you need?");
        numButtons = Integer.parseInt(jbutton.showInputDialog(parent, "How many buttons do you need?", null));
        
        //mock scenario
        StringBuffer input = new StringBuffer();
		for (int i=0;i<10;i++) {
			//input.append("Command: "+importedScenario.getCommand(i).getName()+" Args: "+importedScenario.getCommand(i).getArguments()+'\n');
		}
		
		//set mock scenario to textfield
		//TextField.setText(input.toString());
        
        
    }
    
    /**
     * Updates the list box to current state. Should be done when changes are meant to be seen by the user
     */
    private void updateSessionModel(){
    	
    	sessionModel.clear();
    	for(ScenarioCommand cmd : workingScenario)
    	{
    		String element = cmd.getName() + " - " + cmd.getDescription() + " - " + 
    				Arrays.toString(cmd.getArguments());
    		sessionModel.addElement(element);
    	}
    		
    }
    
    private void swapCommands(int first, int second) {
    	//TODO: Use Scenario.moveCommand(). Has been tested and includes error checking
    	if(second<0 || second>sessionModel.size()-1) {
    		return;
    	}
        String tmp = (String) sessionModel.get(first);
        sessionModel.set(first, sessionModel.get(second));
        sessionModel.set(second, tmp);
    }
}