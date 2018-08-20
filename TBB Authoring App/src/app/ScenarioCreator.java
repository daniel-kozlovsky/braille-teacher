package app;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
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

	// misc
	private int comboIndex;
	private int[] listBoxSelectedIndices;
	private Scenario workingScenario;
	private int numCells;
	private int numButtons;
	private JFrame parent;
	// list
	private DefaultListModel<String> sessionListModel;
	private JList<String> sessionScenarioList;
	private final int LIST_CELL_HEIGHT = 50;
	private final int LIST_CELL_WIDTH = 100;
	// panels
	private JPanel scenarioProgressPanel;
	private JPanel componentsPanel;
	// labels
	// fonts
	Font mainButtonFont = new Font("Tahoma", Font.PLAIN, 14);
	// colors
	Color mainButtonColour = new Color(0, 0, 0);
	// Layouts
	BoxLayout mainGroupLayout;
	GroupLayout gl_componentsPanel;
	private JButton btnExport;
	//context menus
	JPopupMenu rClickContextLBox;
	JMenu addContextSubMenu;

	public ScenarioCreator(JFrame parent, Scenario importedScenario) {

		this.parent = parent;
		this.parent.setMinimumSize(new Dimension(600, 600));
		this.parent.setSize(new Dimension(800, 600));

		// reposition
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = screenSize.height;
		int width = screenSize.width;
		this.parent.setLocation(width / 5, height / 10);
		this.parent.setLocationRelativeTo(null);

		//scenarioCreatorBounds = parent.getBounds();
		//this.setBounds(scenarioCreatorBounds);

		// import OR initialize scenario
		if (importedScenario != null) {
			this.workingScenario = importedScenario;
		} else {

			// Dialog for number of cells and buttons
			newScenarioSetUpDialog();
			workingScenario = new Scenario(numCells, numButtons);
		}
		
		this.parent.setTitle("Create new Scenario");
		
		initComponents();
		initLayout();

		this.setLayout(mainGroupLayout);
		componentsPanel.setLayout(gl_componentsPanel);

		// populates list box
		updateSessionModel();
	}

	private void initComponents() {
		
		//Buttons
		btnExport = new JButton("Export Scenario");
		btnExport.setForeground(Color.BLACK);
		btnExport.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnExport.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnExportClickHandler();
			}
		});
		
		// ListBox
		sessionListModel = new DefaultListModel<>();
		sessionScenarioList = new JList<String>();
	    JScrollPane scrollableList = new JScrollPane(sessionScenarioList);
		sessionScenarioList.setModel(sessionListModel);
		sessionScenarioList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		sessionScenarioList.setFixedCellHeight(LIST_CELL_HEIGHT);
		sessionScenarioList.setFixedCellWidth(LIST_CELL_WIDTH);
		sessionScenarioList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				
				sessionScenarioListValueChangedHandler();
				
			}

		});
		//refers right click event to handler
		MouseListener mouselistener = new MouseAdapter() {
			public void mouseClicked(MouseEvent me)
			{
				//select right clicked element
				sessionScenarioList.setSelectedIndex(sessionScenarioList.locationToIndex(me.getPoint()));
				//If right click
				if(me.getButton() == MouseEvent.BUTTON3)
				{
					listBoxElementRClickedHandler(me.getPoint().x, me.getPoint().y);
				}
				
			}
		};
		
		sessionScenarioList.addMouseListener(mouselistener);
		//Submenu for add context
		addContextSubMenu = new JMenu("Add");
		
		//populate submenu with all possible commands
		for(EnumPossibleCommands e : EnumPossibleCommands.values())
		{
			JMenuItem item = new JMenuItem(e.getName());
			item.addActionListener(new ActionListener() {
				@Override 
				public void actionPerformed(ActionEvent arg0)
				{
					//click on command
					btnAddClickHandler(e);
				}
			});
			addContextSubMenu.add(item);
		}
		
		
		
		//Custom context menu for listbox
		rClickContextLBox = new JPopupMenu();
		
		//Remove command menu item
		JMenuItem menuItemRemove = new JMenuItem("Remove");
		menuItemRemove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				btnRemoveClickHandler();
			}
		});
		//move command up 
		JMenuItem menuItemMoveUp = new JMenuItem("Move up");
		menuItemMoveUp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				btnMoveUpClickHandler();
			}
		});
		//move command down
		JMenuItem menuItemMoveDown = new JMenuItem("Move down");
		menuItemMoveDown.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				btnMoveDownClickHandler();
			}
		});
		//populate menu
		rClickContextLBox.add(addContextSubMenu);
		rClickContextLBox.add(menuItemRemove);
		rClickContextLBox.add(menuItemMoveUp);
		rClickContextLBox.add(menuItemMoveDown);
		
		//
		
		// Panels
		scenarioProgressPanel = new JPanel();
		scenarioProgressPanel.setLayout(new BorderLayout(0, 0));
		scenarioProgressPanel.add(scrollableList, BorderLayout.CENTER);
		componentsPanel = new JPanel();
	
	}

	
		

	// TODO change component layout to have two columns if enough space after
	// resizing window.
	private void initLayout() {
		// group layout customizations
		mainGroupLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
		mainGroupLayout.setHorizontalGroup(
				mainGroupLayout.addLayoutComponent(scenarioProgressPanel);
			mainGroupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(mainGroupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scenarioProgressPanel, 0, this.getWidth(), Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(componentsPanel, GroupLayout.PREFERRED_SIZE, this.getWidth(), GroupLayout.PREFERRED_SIZE)
					.addGap(5))
		);
		mainGroupLayout.setVerticalGroup(
			mainGroupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, mainGroupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(mainGroupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(componentsPanel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 696, Short.MAX_VALUE)
						.addComponent(scenarioProgressPanel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 696, Short.MAX_VALUE))
					.addContainerGap())
		);

		

		// componentsPanel customizations
		gl_componentsPanel = new GroupLayout(componentsPanel);
		gl_componentsPanel.setHorizontalGroup(
			gl_componentsPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_componentsPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_componentsPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnExport, GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
						
		)));
		gl_componentsPanel.setVerticalGroup(
			gl_componentsPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_componentsPanel.createSequentialGroup()
					.addPreferredGap(ComponentPlacement.RELATED)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGap(7)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addPreferredGap(ComponentPlacement.RELATED, 321, Short.MAX_VALUE)
					.addComponent(btnExport, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE))
		);
			
		
				// accessibility

		sessionScenarioList.getAccessibleContext().setAccessibleName("Sceanrio commands");
		sessionScenarioList.getAccessibleContext().setAccessibleDescription("Scenario commands shown as a queue");

		btnExport.getAccessibleContext().setAccessibleName("Exprot scenario");
		btnExport.getAccessibleContext().setAccessibleDescription("Save the scenario as a file");

	}

	/**
	 * Handles event when list box selection is changed.
	 */
	private void sessionScenarioListValueChangedHandler()
	{
		listBoxSelectedIndices = sessionScenarioList.getSelectedIndices();
	}
	

	/**
	 * Handler for right clicks on list elements. Meant to bring up menu.
	 */
	private void listBoxElementRClickedHandler(int x, int y)
	{
		rClickContextLBox.show(this, x+5, y+5);
	}
	
	/**
	 * Handles the click event for the add command button
	 */
	private void btnAddClickHandler(EnumPossibleCommands command) {
		
		EnumPossibleCommands cmdType = command;
		Object[] args = null;

		switch (cmdType) {
		case SOUND:
			// Add sound command with arguments
			// TODO whatever happens here must create a new command
			/*
			 * addAudio audio = new addAudio(); parent.revalidate(); audio.setVisible(true);
			 * audio.setSize(400, 600); audio.setResizable(false);
			 */

			// **********
			// workingScenario.addCommand(newCommand);
			// **********

			// opens file selection dialog (choose a file OR record new)
			JLabel labelFile = new JLabel("File Chosen");

			JLabel labelFilePath = new JLabel("N/A");
			labelFilePath.setPreferredSize(new Dimension(400, 30));
			labelFilePath.setForeground(Color.BLUE);

			JLabel labelOR = new JLabel("--- OR ---");
			labelOR.setHorizontalAlignment(SwingConstants.CENTER);

			JTextField inputFileName = new JTextField("");

			JLabel labelRecording = new JLabel("Recording...");
			labelRecording.setForeground(Color.RED);

			inputFileName.setText("File name...");

			JButton btnChooseFile = new JButton("Choose existing sound file");

			JButton btnRecord = new JButton("Start Recording");

			JButton btnStop = new JButton("Stop Recording");

			final JComponent[] inputs = new JComponent[] { labelFile, labelFilePath, btnChooseFile, labelOR,
					inputFileName, labelRecording, btnRecord, btnStop };

			btnRecord.setEnabled(true);
			btnStop.setEnabled(false);
			labelRecording.setVisible(false);

			// choose a file
			btnChooseFile.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// open file chooser
					JFileChooser openFilePrompt = new JFileChooser();
					int result = openFilePrompt.showOpenDialog(ScenarioCreator.this);

					if (result == JFileChooser.APPROVE_OPTION) {
						labelFilePath.setText(openFilePrompt.getSelectedFile().getAbsolutePath());
					}
				}
			});

			AudioRecorder audioRecorder = new AudioRecorder();

			// start recording
			btnRecord.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {

					btnRecord.setEnabled(false);
					btnStop.setEnabled(true);
					labelRecording.setVisible(true);

					try {
						audioRecorder.recordAndSave(inputFileName.getText());
					} catch (NumberFormatException | IOException e) {
						System.out.print("Record failed, Exception: " + e.getMessage() + " due to " + e.getCause());
						e.printStackTrace();
					}

					// open sound recorder

					// labelFilePath.setText(openFilePrompt.getSelectedFile().getAbsolutePath());

				}
			});

			// stop recording
			btnStop.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {

					btnRecord.setEnabled(true);
					btnStop.setEnabled(false);
					labelRecording.setVisible(false);

					audioRecorder.dataLine.stop();
					audioRecorder.dataLine.close();
					audioRecorder.dataLine.flush();
					labelFilePath.setText(audioRecorder.audioFile.getAbsolutePath());
				}
			});

			int result = JOptionPane.showConfirmDialog(null, inputs, "Sound Selection", JOptionPane.PLAIN_MESSAGE);
			if (result == JOptionPane.OK_OPTION) {
				File tmp = new File(labelFilePath.getText());
				if (tmp.exists()) {
					args = new Object[] { labelFilePath.getText() };
				}
			}

			if (args != null) {
				workingScenario.addCommand(workingScenario.createNewCommand(EnumPossibleCommands.SOUND, args));
			}

			break;

		case REPEAT:
			args = getArgumentsThroughDialog(cmdType);
			workingScenario.addCommand(workingScenario.createNewCommand(cmdType, args));
			workingScenario
					.addCommand(workingScenario.createNewCommand(EnumPossibleCommands.END_REPEAT, new Object[] {}));
			break;

		case SKIP_BUTTON:
			args = getArgumentsThroughDialog(cmdType);
			workingScenario.addCommand(workingScenario.createNewCommand(cmdType, args));
			workingScenario.addCommand(
					workingScenario.createNewCommand(EnumPossibleCommands.SKIP_LOCATION, new Object[] { args[1] }));
			break;

		case SKIP:
			args = getArgumentsThroughDialog(cmdType);
			workingScenario.addCommand(workingScenario.createNewCommand(cmdType, args));
			workingScenario.addCommand(
					workingScenario.createNewCommand(EnumPossibleCommands.SKIP_LOCATION, new Object[] { args[1] }));
			break;

		// All other commands are fine
		default:
			args = getArgumentsThroughDialog(cmdType);
			if (args != null) {
				workingScenario.addCommand(workingScenario.createNewCommand(cmdType, args));
			}
		}

		updateSessionModel();
	}

	
	/**
	 * Handles the click event for the remove command button.
	 * Will remove all selected items from the list
	 */
	private void btnRemoveClickHandler() {
		
		int positionModifier = 0;
		
		for(int i : listBoxSelectedIndices)
		{
			workingScenario.removeCommand(workingScenario.getCommand(i-positionModifier));
			positionModifier++;
		}
		
		updateSessionModel();
	}

	
	/**
	 * Handles the click event for the Move Up button
	 */
	private void btnMoveUpClickHandler() {
		// swapCommands(sessionScenarioList.getSelectedIndex(),sessionScenarioList.getSelectedIndex()-1);
		
		//TODO allow multiple commands to be moved. 
		int listIndex = sessionScenarioList.getSelectedIndex();
		if ( listIndex > 0) {
			workingScenario.moveCommand(workingScenario.getCommand(listIndex), listIndex - 1);
		}
		updateSessionModel();
	}

	
	/**
	 * Handles the click event for the Move Down button
	 */
	private void btnMoveDownClickHandler() {
		// swapCommands(sessionScenarioList.getSelectedIndex(),sessionScenarioList.getSelectedIndex()+1);
		int listIndex = sessionScenarioList.getSelectedIndex();
		if (listIndex >= 0 && listIndex != sessionListModel.size() - 1) {
			workingScenario.moveCommand(workingScenario.getCommand(listIndex), listIndex + 1);
		}
		updateSessionModel();
	}

	
	/**
	 * Displays a new dialog box that prompts the user for braille cell and button
	 * amounts
	 */
	private void newScenarioSetUpDialog() {
		JOptionPane jcell = new JOptionPane();
		jcell.getAccessibleContext().setAccessibleDescription("How many braille cells do you need?");
		numCells = Integer.parseInt(JOptionPane.showInputDialog(parent, "How many braille cells do you need?", null));
		JOptionPane jbutton = new JOptionPane();
		jbutton.getAccessibleContext().setAccessibleDescription("How many buttons do you need?");
		numButtons = Integer.parseInt(JOptionPane.showInputDialog(parent, "How many buttons do you need?", null));
	}

	
	/**
	 * Prompts user for arguments to a command. Should be used when adding or
	 * editing a command.
	 * 
	 * @param cmd
	 *            The command type from EnumPossibleCommands
	 * @return an array of arguments for the command
	 */
	private Object[] getArgumentsThroughDialog(EnumPossibleCommands cmd) {
		// TODO add input checks
		Class<?>[] argTypes = cmd.getArgumentTypes();
		Object[] args = new Object[argTypes.length];

		for (int i = 0; i < args.length; i++) {
			String input = JOptionPane.showInputDialog(this, "Input argument " + (i + 1) + " for " + cmd.getName());

			if (input != null && input.length() > 0) {
				if (argTypes[i].equals(Integer.class)) {
					args[i] = Integer.parseInt(input);
				} else if (argTypes[i].equals(Character.class)) {
					args[i] = input.charAt(0);
				} else {
					args[i] = input;
				}
			} else {
				return null;
			}
		}

		return args;
	}

	
	/**
	 * Updates the list box to current state. Should be done when changes are meant
	 * to be seen by the user
	 */
	private void updateSessionModel() {

		sessionListModel.clear();
		for (ScenarioCommand cmd : workingScenario) {
			sessionListModel.addElement(cmd.toString());
		}
		//Last element in list. Makes it more intuitive for user to add commands.
		sessionListModel.addElement("+");
	}

	/**
	 * Export button click handler
	 */
	public void btnExportClickHandler() {
		// save prompt
		JFileChooser saveFilePrompt = new JFileChooser();
		int result = saveFilePrompt.showSaveDialog(ScenarioCreator.this);

		if (result == JFileChooser.APPROVE_OPTION) {
			// parse export scenario
			ScenarioFormatter.export(workingScenario, saveFilePrompt.getSelectedFile().getAbsolutePath());
		}
	}
}