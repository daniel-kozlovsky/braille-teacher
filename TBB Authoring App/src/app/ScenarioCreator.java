package app;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import scenario.EnumPossibleCommands;
import scenario.Scenario;
import scenario.ScenarioCommand;
public class ScenarioCreator extends JPanel {
	protected static final EnumPossibleCommands EnimPossibleCommands = null;
	private JFrame parent;
	private Scenario importedScenario;
    private String cell;
    private String buttons;
    private final JPanel panel = new JPanel();
    private final JButton btnAddCommand = new JButton("Add Command");
    private JButton btnRemove = new JButton("Remove");
    private JButton btnmoveUP = new JButton("Move Up");
    private JButton btnMoveDown = new JButton("Move down");
    private JButton btnEdit = new JButton("Edit");
    private JButton btnRe = new JButton("Repeat");
    private final JPanel panel_1 = new JPanel();
    private final JLabel lblNewLabel = new JLabel("Please choose a command from the top down menu ");
    private final JComboBox comboBox = new JComboBox();
    private int state;
    private String qst;
    private int secpause;
 	private Scenario sc;
 	public ScenarioCreator(JFrame parent, Scenario importedScenario) {
		//customize the jframe 
		
		this.parent = parent;
        
		this.importedScenario = importedScenario;
        this.parent.setTitle("Creat new Scenario");
        this.parent.setSize(700, 700);
        // calling the joptiopn method 
        Jopttion();
        sc = new Scenario((Integer.parseInt(cell)),(Integer.parseInt(buttons)));

        // group layout customizations 
        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panel, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 252, Short.MAX_VALUE)
                    .addContainerGap())
        );
        groupLayout.setVerticalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                        .addComponent(panel, GroupLayout.PREFERRED_SIZE, 496, GroupLayout.PREFERRED_SIZE)
                        .addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 390, Short.MAX_VALUE))
                    .addContainerGap())
        );
        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
        	gl_panel.createParallelGroup(Alignment.LEADING)
        		.addGap(0, 206, Short.MAX_VALUE)
        );
        gl_panel.setVerticalGroup(
        	gl_panel.createParallelGroup(Alignment.LEADING)
        		.addGap(0, 496, Short.MAX_VALUE)
        );
        panel.setLayout(gl_panel);
        
       
       // calling buttons method 
        comboxdescription();
        addcommandBtn();
        removeBtn();
        moveUpBtn();
        moveDownBtn();
        editBtn();
        reBtn();
        // panel_1 customizations 
        GroupLayout gl_panel_1 = new GroupLayout(panel_1);
        gl_panel_1.setHorizontalGroup(
            gl_panel_1.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_1.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel_1.createSequentialGroup()
                            .addGap(10)
                            .addComponent(comboBox, 0, 390, Short.MAX_VALUE)
                            .addContainerGap())
                        .addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
                            .addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE)
                            .addGap(47))
                        .addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
                            .addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
                                .addComponent(btnmoveUP, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE)
                                .addComponent(btnRe, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE)
                                .addComponent(btnEdit, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE)
                                .addComponent(btnMoveDown, GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE))
                            .addGap(28))
                        .addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
                            .addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
                                .addComponent(btnRemove, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE)
                                .addComponent(btnAddCommand, GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE))
                            .addGap(28))))
        );
        gl_panel_1.setVerticalGroup(
            gl_panel_1.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_1.createSequentialGroup()
                    .addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addComponent(comboBox, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addGap(38)
                    .addComponent(btnAddCommand, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addGap(18)
                    .addComponent(btnRemove, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addGap(27)
                    .addComponent(btnmoveUP, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addGap(18)
                    .addComponent(btnMoveDown, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addGap(18)
                    .addComponent(btnEdit, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addGap(20)
                    .addComponent(btnRe, GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                    .addGap(8))
        );
        panel_1.setLayout(gl_panel_1);
        setLayout(groupLayout);
        
    }
    private void comboxdescription() {
        // combo box choice change class and method 
        class ItemChangeListener implements ItemListener{
            @Override
            public void itemStateChanged(ItemEvent event) {
              state=event.getStateChange(); 
            }       
        }
        // items in the combo box 
        comboBox.addItem("Add Auido");
		comboBox.addItem("Add a Question");
		comboBox.addItem("Pasue");
		comboBox.addItem("disp-string");
		comboBox.addItem("Skip");
		comboBox.addItem("Skip Button");
		comboBox.addItem("Repeat Button");
		comboBox.addItem("User input");
		comboBox.addItem("disp clearAll");
		comboBox.addItem("disp clear cell");
		comboBox.addItem("disp cell lower");
		comboBox.addItem("disp cell pins");
		state =comboBox.getSelectedIndex();
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
					 question.getAccessibleContext().setAccessibleDescription(" please enter the question here ");
					 qst=question.showInputDialog(parent, "please enter the Question here  ", null);
					 EnumPossibleCommands cmd = EnumPossibleCommands.DISP_STRING ;
					sc.addCommand(sc.createNewCommand(cmd,  new String[] {qst}));
				}
				// pause command 
				else if (state == 2)
				{
					JOptionPane pause  = new JOptionPane();
					pause.getAccessibleContext().setAccessibleDescription(" please enter the number of seconds you want to pause ");
					secpause= Integer.parseInt(JOptionPane.showInputDialog(parent, "please enter the number of seconds you want to pause  ", null));
					Object[] obj = new Object[] {secpause};
					sc.addCommand(sc.createNewCommand(EnumPossibleCommands.PAUSE, obj ));
				}
				// disp-string
				else if(state==3)
				{
					String dispstring; 
					JOptionPane disp = new JOptionPane();
					disp.getAccessibleContext().setAccessibleDescription("Please write the string disp ");
					dispstring= disp.showInputDialog(parent,"please write the String here ",null);
					EnumPossibleCommands cmd = EnumPossibleCommands.DISP_STRING ;
					sc.addCommand(sc.createNewCommand(cmd,  new String[] {dispstring}));
				}
				
				//Skip
				else if (state==4)
				{ 
					String skip;
					JOptionPane skipp = new JOptionPane();
					skipp.getAccessibleContext().setAccessibleDescription("Please write the string you want to skip ");
					skip= skipp.showInputDialog(parent,"please write the String you want to skip ",null);
					EnumPossibleCommands cmd = EnumPossibleCommands.SKIP ;
					sc.addCommand(sc.createNewCommand(cmd,  new String[] {skip}));
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
					sc.addCommand(sc.createNewCommand(cmd, new Object [] {bnts,str}));
					}
				
				//Repeat Button
				else if(state ==6 )
				{

					JOptionPane rebnt  = new JOptionPane();
					rebnt.getAccessibleContext().setAccessibleDescription(" please enter the button number you want to repeat ");
					int btns;
					btns = Integer.parseInt(rebnt.showInputDialog(parent, "please enter the button number you want to repeat ", null));
					EnumPossibleCommands cmd = EnumPossibleCommands.REPEAT_BUTTON ;
					sc.addCommand(sc.createNewCommand(cmd, new Object [] {btns}));
				}
				// disp clear cell
				else if( state == 9)
				{
					int cells;
					JOptionPane clrcell  = new JOptionPane();
					clrcell.getAccessibleContext().setAccessibleDescription(" please enter the cell number you want to clear ");
					cells = Integer.parseInt(clrcell.showInputDialog(parent, "please enter the cell number you want to clear ", null));
					EnumPossibleCommands cmd = EnumPossibleCommands.DISP_CLEAR_CELL ;
					sc.addCommand(sc.createNewCommand(cmd, new Object [] {cells}));
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
					sc.addCommand(sc.createNewCommand(cmd, new Object [] {cell,pin}));
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
        btnmoveUP.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnmoveUP.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            }
        });
    }
    public void moveDownBtn() {
        btnMoveDown.setFont(new Font("Tahoma", Font.PLAIN, 14));
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
    
    public void Jopttion()
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
			input.append("Command: "+importedScenario.getCommand(i).getName()+" Args: "+importedScenario.getCommand(i).getArguments()+'\n');
		}
		
		
		//TextField.setText(input.toString());
        
        
    }
}









