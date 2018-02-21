package app;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import scenario.EnumPossibleCommands;
import scenario.Scenario;
import scenario.ScenarioCommand;
import scenario.ScenarioFormatter;
public class ScenarioCreator extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	//Sizes
	private Rectangle scenarioCreatorBounds;
	private final int preferredButtonWidth = 200;
	//misc
    private int comboIndex;
    private int listBoxIndex;
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
    private JButton btnExxportScenario;
    private static int number=1;
 	
 	public ScenarioCreator(JFrame parent, Scenario sessionScenario) {
 		
 		this.parent = parent;
 		
 		scenarioCreatorBounds = parent.getBounds();
 		this.setBounds(scenarioCreatorBounds);
		this.workingScenario = sessionScenario;
        this.parent.setTitle("Create new Scenario");
        this.parent.setSize(700, 700);
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
 	    lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
 		//Buttons
 		btnAdd = new JButton("Add Command");
 		btnAdd.setFont(mainButtonFont);
        btnAdd.setForeground(mainButtonColour);
        btnAdd.addActionListener( new ActionListener () {
        	
        	@Override
        	public void actionPerformed(ActionEvent arg0) 
        	{
        		btnAddClickHandler();
        	}
        	});
        
        btnRemove = new JButton("Remove");
        btnRemove.setFont(mainButtonFont);
        btnRemove.setForeground(mainButtonColour);
        btnRemove.addActionListener( new ActionListener () {

        	@Override
        	public void actionPerformed(ActionEvent arg0) 
        	{
        		btnRemoveClickHandler();
        	}
        	});
        
        btnMoveUp = new JButton("Move Up");
        btnMoveUp.setFont(mainButtonFont);
        btnMoveUp.setForeground(mainButtonColour);
        btnMoveUp.addActionListener( new ActionListener () {

        	@Override
        	public void actionPerformed(ActionEvent arg0) 
        	{
        		btnMoveUpClickHandler();
        	}
        	});
        
        btnMoveDown = new JButton("Move down");
        btnMoveDown.setFont(mainButtonFont);
        btnMoveDown.setForeground(mainButtonColour);
        btnMoveDown.addActionListener( new ActionListener () {

        	@Override
        	public void actionPerformed(ActionEvent arg0) 
        	{
        		btnMoveDownClickHandler();
        	}
        	});
        
        btnEdit = new JButton("Edit");
        btnEdit.setFont(mainButtonFont);
        btnEdit.setForeground(mainButtonColour);
        btnEdit.addActionListener( new ActionListener () {

        	@Override
        	public void actionPerformed(ActionEvent arg0) 
        	{
        		btnMoveDownClickHandler();
        	}
        	});
        
        btnRepeat = new JButton("Repeat");
        btnRepeat.setFont(mainButtonFont);
        btnRepeat.setForeground(mainButtonColour);
        btnRepeat.addActionListener( new ActionListener () {
        	
        	@Override
        	public void actionPerformed(ActionEvent arg0) 
        	{
        		btnRepeatClickHandler();
        	}
        	});
        
        //ListBox
        sessionModel = new DefaultListModel<>();
        sessionScenarioList = new JList<String>();
 		sessionScenarioList.setModel(sessionModel);
 		sessionScenarioList.addListSelectionListener( new ListSelectionListener () 
 			{
				@Override
				public void valueChanged(ListSelectionEvent arg0)
				{
					listBoxIndex = sessionScenarioList.getSelectedIndex();
				}
 				
 			});
     
        //Panels
        scenarioProgressPanel = new JPanel();
        scenarioProgressPanel.setLayout(new BorderLayout(0, 0));
        scenarioProgressPanel.add(sessionScenarioList);
        componentsPanel = new JPanel();
        
        //combo-boxes
        comboBox = new JComboBox<String>();
        //Populate comboBox with all possible commands
        for(EnumPossibleCommands cmd : EnumPossibleCommands.values())
        {
        	comboBox.addItem(cmd.getName());
        }
        
        /*OLD: 
         * If you prefer this way, remove my 'for loop' above 
         * and use btnAddClickHandler_OLD()
         * 
         * -DKozlovsky 
         * 
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
		comboBox.addItem("disp cell pins");*/
        comboBox.addActionListener( new ActionListener () {
        	
        	@Override
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
        			.addComponent(scenarioProgressPanel, 0, 313, Short.MAX_VALUE)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(componentsPanel, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        			.addContainerGap())
        );
        mainGroupLayout.setVerticalGroup(
        	mainGroupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(Alignment.TRAILING, mainGroupLayout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(mainGroupLayout.createParallelGroup(Alignment.TRAILING)
        				.addComponent(componentsPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 513, Short.MAX_VALUE)
        				.addComponent(scenarioProgressPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 513, Short.MAX_VALUE))
        			.addContainerGap())
        );
        
        btnExxportScenario = new JButton("Export Scenario");
        btnExxportScenario.setForeground(Color.BLACK);
        btnExxportScenario.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnExxportScenario.addActionListener( new ActionListener () {
        	
        	@Override
        	public void actionPerformed(ActionEvent arg0) 
        	{
        		btnExportClickHandler();
        	}
        	});
        
        
        
        // componentsPanel customizations 
        gl_componentsPanel = new GroupLayout(componentsPanel);
        gl_componentsPanel.setHorizontalGroup(
        	gl_componentsPanel.createParallelGroup(Alignment.TRAILING)
        		.addGroup(gl_componentsPanel.createSequentialGroup()
        			.addGroup(gl_componentsPanel.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_componentsPanel.createSequentialGroup()
        					.addContainerGap()
        					.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        				.addGroup(gl_componentsPanel.createSequentialGroup()
        					.addContainerGap()
        					.addComponent(comboBox, 0, 293, Short.MAX_VALUE))
        				.addGroup(gl_componentsPanel.createSequentialGroup()
        					.addGap(33)
        					.addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 243, GroupLayout.PREFERRED_SIZE))
        				.addGroup(gl_componentsPanel.createSequentialGroup()
        					.addContainerGap()
        					.addGroup(gl_componentsPanel.createParallelGroup(Alignment.LEADING)
        						.addGroup(gl_componentsPanel.createSequentialGroup()
        							.addComponent(btnRemove, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
        							.addGap(39)
        							.addComponent(btnMoveUp, GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE))
        						.addGroup(gl_componentsPanel.createSequentialGroup()
        							.addComponent(btnEdit, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
        							.addGap(39)
        							.addComponent(btnMoveDown, GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)))))
        			.addContainerGap())
        		.addGroup(gl_componentsPanel.createSequentialGroup()
        			.addContainerGap(84, Short.MAX_VALUE)
        			.addComponent(btnRepeat, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
        			.addGap(82))
        		.addGroup(gl_componentsPanel.createSequentialGroup()
        			.addContainerGap(36, Short.MAX_VALUE)
        			.addComponent(btnExxportScenario, GroupLayout.PREFERRED_SIZE, 243, GroupLayout.PREFERRED_SIZE)
        			.addGap(34))
        );
        gl_componentsPanel.setVerticalGroup(
        	gl_componentsPanel.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_componentsPanel.createSequentialGroup()
        			.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
        			.addGap(18)
        			.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
        			.addGap(18)
        			.addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
        			.addGap(41)
        			.addGroup(gl_componentsPanel.createParallelGroup(Alignment.BASELINE)
        				.addComponent(btnRemove, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
        				.addComponent(btnMoveUp, GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))
        			.addGap(18)
        			.addGroup(gl_componentsPanel.createParallelGroup(Alignment.BASELINE)
        				.addComponent(btnEdit, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
        				.addComponent(btnMoveDown, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
        			.addGap(29)
        			.addComponent(btnRepeat, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
        			.addGap(30)
        			.addComponent(btnExxportScenario, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap())
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
    //Keep this if you prefer your method
    //-DKozlovsky
    private void btnAddClickHandler_OLD()
    {
    	// add audio add command 
		if(comboIndex==0)
		{
			addAudio audio = new addAudio();
		audio.revalidate();
		audio.setVisible(true);
	//	audio.setSize(300, 500);
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
     * Handles the click event for the add command button
     */
    private void btnAddClickHandler()
    {
    	EnumPossibleCommands cmdType = EnumPossibleCommands.values()[comboIndex];
    	Object[] args;
    	
    	switch (cmdType)
    	{
    		case SOUND:
	    		//Add sound command with arguments 
	        	//TODO whatever happens here must create a new command
	    		addAudio audio = new addAudio();
	    		parent.revalidate();
	    		audio.setVisible(true);
	    		audio.setSize(400, 600);
	    		audio.setResizable(false);
	    		
	    		//**********
	    		//workingScenario.addCommand(newCommand);
	    		//**********
	    		break;
	    		
    		case REPEAT:
    			args = getArgumentsThroughDialog(cmdType);
    	    	workingScenario.addCommand(workingScenario.createNewCommand(cmdType, args));
    	    	workingScenario.addCommand(workingScenario.createNewCommand(EnumPossibleCommands.END_REPEAT,
    	    			new Object[] {}));
    			break;
    			
    		case SKIP_BUTTON:
    			args = getArgumentsThroughDialog(cmdType);
    	    	workingScenario.addCommand(workingScenario.createNewCommand(cmdType, args));
    	    	workingScenario.addCommand(workingScenario.createNewCommand(EnumPossibleCommands.SKIP_LOCATION,
    	    			new Object[] {args[1]}));
    			break;
    			
    		case SKIP:
    			args = getArgumentsThroughDialog(cmdType);
    	    	workingScenario.addCommand(workingScenario.createNewCommand(cmdType, args));
    	    	workingScenario.addCommand(workingScenario.createNewCommand(EnumPossibleCommands.SKIP_LOCATION, 
    	    			new Object[] {args[1]}));
    			break;
    			
    		//All other commands are fine	
    		default:
    			args = getArgumentsThroughDialog(cmdType);
    			workingScenario.addCommand(workingScenario.createNewCommand(cmdType, args));
    	}
    	
    	updateSessionModel();
    }
    /**
     * Handles the click event for the remove command button
     */
    private void btnRemoveClickHandler()
    {
    	workingScenario.removeCommand(workingScenario.getCommand(listBoxIndex));
    	updateSessionModel();
    }
    
    /**
     * Handles the click event for the Move Up button
     */
    private void btnMoveUpClickHandler()
    {	
    	//doesnt work anymore
    	//swapCommands(sessionScenarioList.getSelectedIndex(),sessionScenarioList.getSelectedIndex()-1);
    	workingScenario.moveCommand(workingScenario.getCommand(listBoxIndex), listBoxIndex - 1);
    	updateSessionModel();
    }
    
    /**
     * Handles the click event for the Move Down button
     */
    private void btnMoveDownClickHandler()
    {
    	//doesnt work anymore
    	//swapCommands(sessionScenarioList.getSelectedIndex(),sessionScenarioList.getSelectedIndex()+1);
    	workingScenario.moveCommand(workingScenario.getCommand(listBoxIndex), listBoxIndex + 1);
    	updateSessionModel();
    }
    
    /**
     * Handles the click event for the edit command button
     */
    private void btnEditClickHandler()
    {
    	updateSessionModel();
    }
    
    /**
     * Handles the click event for the Repeat command button
     */
    private void btnRepeatClickHandler()
    {
    	updateSessionModel();
    }
    
    /**
     * Displays a new dialog box that prompts the user for braille cell and button amounts 
     */
    private void newScenarioSetUpDialog()
    {
        JOptionPane jcell  = new JOptionPane();
        jcell.getAccessibleContext().setAccessibleDescription("How many braille cells do you need?");
        numCells = Integer.parseInt(jcell.showInputDialog(parent, "How many braille cells do you need?", null));
        JOptionPane jbutton = new JOptionPane();
        jbutton.getAccessibleContext().setAccessibleDescription("How many buttons do you need?");
        numButtons = Integer.parseInt(jbutton.showInputDialog(parent, "How many buttons do you need?", null));
    }
    
    /**
     * Prompts user for arguments to a command. Should be used when adding or editing a command.
     * @param cmd The command type from EnumPossibleCommands
     * @return an array of arguments for the command
     */
    private Object[] getArgumentsThroughDialog(EnumPossibleCommands cmd)
    {
    	//TODO add input checks 
    	Class<?>[]  argTypes = cmd.getArgumentTypes();
    	Object[] args = new Object[argTypes.length];
    	
    	for(int i = 0; i <  args.length; i++)
       	{
       		String input = JOptionPane.showInputDialog(this, "Input argument " + (i+1) + " for " + cmd.getName());
       		
       		if(argTypes[i].equals(Integer.class))
       		{
       			args[i] = Integer.parseInt(input);
       		}
       		else if(argTypes[i].equals(Character.class))
        	{
        			args[i] = input.charAt(0);
        	}
       		else
       		{
        			args[i] = input;
       		}
    		
    	}
    	
    	return args;
    }
    /**
     * Updates the list box to current state. Should be done when changes are meant to be seen by the user
     */
    private void updateSessionModel(){
    	
    	sessionModel.clear();
    	for(ScenarioCommand cmd : workingScenario)
    	{
    		sessionModel.addElement(cmd.toString());
    	}
    		
    }
    
    private void swapCommands(int first, int second) {
    	//TODO: Please use Scenario.moveCommand(). This method has been tested and includes error checking.
    	if(second<0 || second>sessionModel.size()-1) {
    		return;
    	}
        String tmp = (String) sessionModel.get(first);
        sessionModel.set(first, sessionModel.get(second));
        sessionModel.set(second, tmp);
    }
    public void btnExportClickHandler()
    {
    	ScenarioFormatter.export(workingScenario, ("Scenario_"+number+".txt"));
    	number++;
    }
}