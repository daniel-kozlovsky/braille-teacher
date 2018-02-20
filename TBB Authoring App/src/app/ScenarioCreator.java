package app;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import scenario.EnumPossibleCommands;
import scenario.Scenario;
import scenario.ScenarioCommand;
public class ScenarioCreator extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame parent;
	
	//Sizes
	private Rectangle scenarioCreatorBounds;
	private final int preferredButtonWidth = 200;
	
	//list management
	private Scenario sessionScenario;
	private DefaultListModel<String> sessionModel = new DefaultListModel<>();
 	private final JList<String> sessionScenarioList = new JList<String>();
	
    private String cell;
    private String buttons;
    private final JPanel scenarioProgressPanel = new JPanel();
    private final JButton btnAddCommand = new JButton("Add Command");
    private JButton btnRemove = new JButton("Remove");
    private JButton btnMoveUp = new JButton("Move Up");
    private JButton btnMoveDown = new JButton("Move down");
    private JButton btnEdit = new JButton("Edit");
    private JButton btnRe = new JButton("Repeat");
    private final JPanel componentsPanel = new JPanel();
    private final JLabel lblNewLabel = new JLabel("Please choose a command from the top down menu ");
    private final JComboBox<String> comboBox = new JComboBox<String>();
    private int state;
    private String qst;
    private int secpause;
 	private Scenario workingScenario;
 	
 	public ScenarioCreator(JFrame parent, Scenario sessionScenario) {
 		
 		this.parent = parent;
 		scenarioCreatorBounds = parent.getBounds();
 		this.setBounds(scenarioCreatorBounds);
		this.sessionScenario = sessionScenario;
        this.parent.setTitle("Create new Scenario");
        
        // calling the joption method 
        jOption();
        workingScenario = new Scenario((Integer.parseInt(cell)),(Integer.parseInt(buttons)));

        // group layout customizations 
        GroupLayout mainGroupLayout = new GroupLayout(this);
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
        scenarioProgressPanel.setLayout(new BorderLayout(0, 0));
        
        //list setup
        scenarioProgressPanel.add(sessionScenarioList);
        
        sessionScenarioList.setModel(sessionModel);
        
        if(sessionScenario!=null) {
        updateModel();
        }else {
        	
        	// mock sessionModel
        	sessionModel = new DefaultListModel();
        	
        	for(int i=0;i<10;i++) {
        		sessionModel.addElement("Item - "+ (i+1) +": Name (Description)");
        	}
        	
        	sessionScenarioList.setModel(sessionModel);
        }
       
       // calling buttons method 
        comboxdescription();
        addcommandBtn();
        removeBtn();
        moveUpBtn();
        moveDownBtn();
        editBtn();
        reBtn();
        // componentsPanel customizations 
        GroupLayout gl_componentsPanel = new GroupLayout(componentsPanel);
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
                                .addComponent(btnRe, Alignment.LEADING, 0, preferredButtonWidth, preferredButtonWidth)
                                .addComponent(btnEdit, Alignment.LEADING, 0, preferredButtonWidth, preferredButtonWidth)
                                .addComponent(btnMoveDown, 0, preferredButtonWidth, preferredButtonWidth))
                            .addGap(28))
                        .addGroup(Alignment.LEADING, gl_componentsPanel.createSequentialGroup()
                            .addGroup(gl_componentsPanel.createParallelGroup(Alignment.LEADING)
                                .addComponent(btnRemove, Alignment.LEADING, 0, preferredButtonWidth, preferredButtonWidth)
                                .addComponent(btnAddCommand, 0, preferredButtonWidth, preferredButtonWidth))
                            .addGap(28))))
        );
        gl_componentsPanel.setVerticalGroup(
            gl_componentsPanel.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_componentsPanel.createSequentialGroup()
                    .addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addComponent(comboBox, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addGap(38)
                    .addComponent(btnAddCommand, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addGap(18)
                    .addComponent(btnRemove, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addGap(27)
                    .addComponent(btnMoveUp, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addGap(18)
                    .addComponent(btnMoveDown, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addGap(18)
                    .addComponent(btnEdit, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addGap(20)
                    .addComponent(btnRe, GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                    .addGap(8))
        );
        componentsPanel.setLayout(gl_componentsPanel);
        setLayout(mainGroupLayout);
        
    }
    private void comboxdescription() {
        // combo box state  change 
        class ItemChangeListener implements ItemListener{
            @Override
            public void itemStateChanged(ItemEvent event) {
              state = event.getStateChange(); 
            }       
        }
        // items in the combo box 
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
		state = comboBox.getSelectedIndex();
        comboBox.addItemListener(new ItemChangeListener());
        
        
    }
    
    public void addcommandBtn()
    {
        btnAddCommand.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnAddCommand.setForeground(new Color(0, 0, 0));
        btnAddCommand.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                comboxdescription();
                
               
               // add audio add command 
				if(state==0)
				{
				addAudio audio = new addAudio(parent);
				parent.revalidate();
				audio.setVisible(true);
				}
				// add question add command 
				else if(state==1)
				{
					 JOptionPane question  = new JOptionPane();
					 question.getAccessibleContext().setAccessibleDescription(" Enter the question below ");
					 qst=question.showInputDialog(parent, "Enter the question below  ", null);
					 EnumPossibleCommands cmd = EnumPossibleCommands.DISP_STRING ;
					workingScenario.addCommand(workingScenario.createNewCommand(cmd,  new String[] {qst}));
				}
				// pause command 
				else if (state == 2)
				{
					JOptionPane pause  = new JOptionPane();
					pause.getAccessibleContext().setAccessibleDescription(" please enter the number of seconds you want to pause ");
					secpause= Integer.parseInt(JOptionPane.showInputDialog(parent, "please enter the number of seconds you want to pause  ", null));
					Object[] obj = new Object[] {secpause};
					workingScenario.addCommand(workingScenario.createNewCommand(EnumPossibleCommands.PAUSE, obj ));
				}
				// disp-string
				else if(state==3)
				{
					String dispstring; 
					JOptionPane disp = new JOptionPane();
					disp.getAccessibleContext().setAccessibleDescription("Please write the string disp ");
					dispstring= disp.showInputDialog(parent,"please write the String here ",null);
					EnumPossibleCommands cmd = EnumPossibleCommands.DISP_STRING ;
					workingScenario.addCommand(workingScenario.createNewCommand(cmd,  new String[] {dispstring}));
				}
				
				//Skip
				else if (state==4)
				{ 
					String skip;
					JOptionPane skipp = new JOptionPane();
					skipp.getAccessibleContext().setAccessibleDescription("Please write the string you want to skip ");
					skip= skipp.showInputDialog(parent,"please write the String you want to skip ",null);
					EnumPossibleCommands cmd = EnumPossibleCommands.SKIP ;
					workingScenario.addCommand(workingScenario.createNewCommand(cmd,  new String[] {skip}));
				}
				// skip button
				else if(state ==5)
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
				else if(state ==6 )
				{

					JOptionPane rebnt  = new JOptionPane();
					rebnt.getAccessibleContext().setAccessibleDescription(" please enter the button number you want to repeat ");
					int btns;
					btns = Integer.parseInt(rebnt.showInputDialog(parent, "please enter the button number you want to repeat ", null));
					EnumPossibleCommands cmd = EnumPossibleCommands.REPEAT_BUTTON ;
					workingScenario.addCommand(workingScenario.createNewCommand(cmd, new Object [] {btns}));
				}
				// disp clear cell
				else if( state == 9)
				{
					int cells;
					JOptionPane clrcell  = new JOptionPane();
					clrcell.getAccessibleContext().setAccessibleDescription(" please enter the cell number you want to clear ");
					cells = Integer.parseInt(clrcell.showInputDialog(parent, "please enter the cell number you want to clear ", null));
					EnumPossibleCommands cmd = EnumPossibleCommands.DISP_CLEAR_CELL ;
					workingScenario.addCommand(workingScenario.createNewCommand(cmd, new Object [] {cells}));
				}	
				// disp cell pin
				else if(state==11)
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
            }
        });
    }
    public void removeBtn()
    {
        btnRemove.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnRemove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            }
        });
    }
    public void moveUpBtn()
    {
    	btnMoveUp.setFont(new Font("Tahoma", Font.PLAIN, 14));
    	btnMoveUp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	swapCommands(sessionScenarioList.getSelectedIndex(),sessionScenarioList.getSelectedIndex()-1);
            }
        });
    }
    public void moveDownBtn() {
        btnMoveDown.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnMoveDown.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	swapCommands(sessionScenarioList.getSelectedIndex(),sessionScenarioList.getSelectedIndex()+1);
            }
        });
    }
    public void editBtn() {
        btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 14));
    }
    
    public void reBtn()
    {
        btnRe.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            }
        });
        btnRe.setFont(new Font("Tahoma", Font.PLAIN, 14));
    }
    
    public void jOption()
    {
         JOptionPane jcell  = new JOptionPane();
        jcell.getAccessibleContext().setAccessibleDescription(" How many cells do you need ");
        cell = jcell.showInputDialog(parent, " How many cells do you need ", null);
        JOptionPane jbutton = new JOptionPane();
        jbutton.getAccessibleContext().setAccessibleDescription(" How many buttons do you need ");
        buttons = jbutton.showInputDialog(parent, " How many buttons do you need ", null);
        
        //mock scenario
        StringBuffer input = new StringBuffer();
		for (int i=0;i<10;i++) {
			//input.append("Command: "+importedScenario.getCommand(i).getName()+" Args: "+importedScenario.getCommand(i).getArguments()+'\n');
		}
		
		//set mock scenario to textfield
		//TextField.setText(input.toString());
        
        
    }
    
    private void updateModel(){
    	//TODO get Scenario Size
    		sessionModel.clear();
    	for(int i = 0; i < 10; i++) {
    		ScenarioCommand command = sessionScenario.getCommand(i);
    		String element = command.getName()+" "+command.getArguments();
    		sessionModel.addElement(element);
    	}
    }
    
    private void swapCommands(int first, int second) {
    	if(second<0 || second>sessionModel.size()-1) {
    		return;
    	}
        String tmp = (String) sessionModel.get(first);
        sessionModel.set(first, sessionModel.get(second));
        sessionModel.set(second, tmp);
    }
}