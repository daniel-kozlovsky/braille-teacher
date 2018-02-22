package app;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
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

	// Sizes
	private Rectangle scenarioCreatorBounds;
	// misc
	private int comboIndex;
	private int listBoxIndex;
	private Scenario workingScenario;
	private int numCells;
	private int numButtons;
	private JFrame parent;
	// list
	private DefaultListModel<String> sessionListModel;
	private JList<String> sessionScenarioList;
	// panels
	private JPanel scenarioProgressPanel;
	private JPanel componentsPanel;
	// buttons
	private JButton btnAdd;
	private JButton btnRemove;
	private JButton btnMoveUp;
	private JButton btnMoveDown;
	// labels
	private JLabel lblNewLabel;
	// combo-boxes
	private JComboBox<String> commandsComboBox;
	// fonts
	Font mainButtonFont = new Font("Tahoma", Font.PLAIN, 14);
	// colours
	Color mainButtonColour = new Color(0, 0, 0);
	// Layouts
	GroupLayout mainGroupLayout;
	GroupLayout gl_componentsPanel;
	private JButton btnExport;

	public ScenarioCreator(JFrame parent, Scenario importedScenario) {

		this.parent = parent;

		scenarioCreatorBounds = parent.getBounds();
		this.setBounds(scenarioCreatorBounds);

		// import OR initialize scenario
		if (importedScenario != null) {
			this.workingScenario = importedScenario;
		} else {

			// Dialog for number of cells and buttons
			newScenarioSetUpDialog();
			workingScenario = new Scenario(numCells, numButtons);
		}

		this.parent.setTitle("Create new Scenario");
		// this.parent.setSize(700, 700);
		initComponents();
		initLayout();

		this.setLayout(mainGroupLayout);
		componentsPanel.setLayout(gl_componentsPanel);

		// populates list box
		updateSessionModel();
	}

	private void initComponents() {
		// Labels
		lblNewLabel = new JLabel("Choose a command from the menu below ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		// Buttons
		btnAdd = new JButton("Add Command");
		btnAdd.setFont(mainButtonFont);
		btnAdd.setForeground(mainButtonColour);
		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnAddClickHandler();
			}
		});

		btnRemove = new JButton("Remove");
		btnRemove.setFont(mainButtonFont);
		btnRemove.setForeground(mainButtonColour);
		btnRemove.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnRemoveClickHandler();
			}
		});

		btnMoveUp = new JButton("Move Up");
		btnMoveUp.setFont(mainButtonFont);
		btnMoveUp.setForeground(mainButtonColour);
		btnMoveUp.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnMoveUpClickHandler();
			}
		});

		btnMoveDown = new JButton("Move down");
		btnMoveDown.setFont(mainButtonFont);
		btnMoveDown.setForeground(mainButtonColour);
		btnMoveDown.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnMoveDownClickHandler();
			}
		});

		// ListBox
		sessionListModel = new DefaultListModel<>();
		sessionScenarioList = new JList<String>();
		sessionScenarioList.setModel(sessionListModel);
		sessionScenarioList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				listBoxIndex = sessionScenarioList.getSelectedIndex();
			}

		});

		// Panels
		scenarioProgressPanel = new JPanel();
		scenarioProgressPanel.setLayout(new BorderLayout(0, 0));
		scenarioProgressPanel.add(sessionScenarioList);
		componentsPanel = new JPanel();

		// combo-boxes
		commandsComboBox = new JComboBox<String>();
		commandsComboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
		// Populate comboBox with all possible commands
		for (EnumPossibleCommands cmd : EnumPossibleCommands.values()) {
			commandsComboBox.addItem(cmd.getName());
		}

		/*
		 * OLD: If you prefer this way, remove my 'for loop' above and use
		 * btnAddClickHandler_OLD()
		 * 
		 * -DKozlovsky
		 * 
		 * comboBox.addItem("Add Audio"); comboBox.addItem("Add a Question");
		 * comboBox.addItem("Pause"); comboBox.addItem("disp-string");
		 * comboBox.addItem("Skip"); comboBox.addItem("Skip Button");
		 * comboBox.addItem("Repeat Button"); comboBox.addItem("User input");
		 * comboBox.addItem("disp clearAll"); comboBox.addItem("disp clear cell");
		 * comboBox.addItem("disp cell lower"); comboBox.addItem("disp cell pins");
		 */
		commandsComboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				comboBoxSelectionChangedHandler();
			}
		});
	}

	// TODO change component layout to have two columns if enough space after
	// resizing window.
	private void initLayout() {
		// group layout customizations
		mainGroupLayout = new GroupLayout(this);
		mainGroupLayout.setHorizontalGroup(mainGroupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(mainGroupLayout.createSequentialGroup().addContainerGap()
						.addComponent(scenarioProgressPanel, 0, 319, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(componentsPanel,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGap(5)));
		mainGroupLayout.setVerticalGroup(mainGroupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(mainGroupLayout.createSequentialGroup().addContainerGap()
						.addGroup(mainGroupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(componentsPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 641,
										Short.MAX_VALUE)
								.addComponent(scenarioProgressPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 641,
										Short.MAX_VALUE))
						.addContainerGap()));

		btnExport = new JButton("Export Scenario");
		btnExport.setForeground(Color.BLACK);
		btnExport.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnExport.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnExportClickHandler();
			}
		});

		// componentsPanel customizations
		gl_componentsPanel = new GroupLayout(componentsPanel);
		gl_componentsPanel.setHorizontalGroup(
			gl_componentsPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_componentsPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 315, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(Alignment.TRAILING, gl_componentsPanel.createSequentialGroup()
					.addGap(42)
					.addGroup(gl_componentsPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnRemove, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
						.addComponent(btnMoveDown, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
						.addComponent(btnMoveUp, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
						.addComponent(btnExport, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
						.addComponent(btnAdd, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE))
					.addGap(50))
				.addGroup(gl_componentsPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(commandsComboBox, 0, 315, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_componentsPanel.setVerticalGroup(
			gl_componentsPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_componentsPanel.createSequentialGroup()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(commandsComboBox, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addGap(31)
					.addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
					.addGap(39)
					.addComponent(btnMoveUp, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
					.addGap(49)
					.addComponent(btnMoveDown, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addGap(44)
					.addComponent(btnRemove, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 206, Short.MAX_VALUE)
					.addComponent(btnExport, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
					.addGap(34))
		);

		// accessibility

		sessionScenarioList.getAccessibleContext().setAccessibleName("Sceanrio commands");
		sessionScenarioList.getAccessibleContext().setAccessibleDescription("Scenario commands shown as a queue");
		
		commandsComboBox.getAccessibleContext().setAccessibleName("Commands drop-down");
		commandsComboBox.getAccessibleContext().setAccessibleDescription("Select a command to add in the scenaio queue");

		btnAdd.getAccessibleContext().setAccessibleName("Add command");
		btnAdd.getAccessibleContext()
				.setAccessibleDescription("Create and add the selected command in the drop-down to the scenario");

		btnMoveUp.getAccessibleContext().setAccessibleName("Move command up");
		btnMoveUp.getAccessibleContext().setAccessibleDescription("Move the selected command up the scenario queue");

		btnMoveDown.getAccessibleContext().setAccessibleName("Move command down");
		btnMoveDown.getAccessibleContext()
				.setAccessibleDescription("Move the selected command down the scenario queue");

		btnRemove.getAccessibleContext().setAccessibleName("Remove command");
		btnRemove.getAccessibleContext().setAccessibleDescription("Remove the selected command from the scenario");

		btnExport.getAccessibleContext().setAccessibleName("Exprot scenario");
		btnExport.getAccessibleContext().setAccessibleDescription("Save the scenario as a file");

	}

	/**
	 * Handles the event when a selection is made in the combo box.
	 */
	private void comboBoxSelectionChangedHandler() {
		comboIndex = commandsComboBox.getSelectedIndex();
	}

	/**
	 * Handles the click event for the add command button
	 */
	// Keep this if you prefer your method
	/*
	 * // -DKozlovsky
	 * 
	 * private void btnAddClickHandler_OLD() { // add audio add command if
	 * (comboIndex == 0) { addAudio audio = new addAudio(); audio.revalidate();
	 * audio.setVisible(true); // audio.setSize(300, 500); } // add question add
	 * command else if (comboIndex == 1) { JOptionPane question = new JOptionPane();
	 * question.getAccessibleContext().
	 * setAccessibleDescription(" Enter the question below "); qst =
	 * JOptionPane.showInputDialog(parent, "Enter the question below  ", null);
	 * EnumPossibleCommands cmd = EnumPossibleCommands.DISP_STRING;
	 * workingScenario.addCommand(workingScenario.createNewCommand(cmd, new String[]
	 * { qst })); } // pause command else if (comboIndex == 2) { JOptionPane pause =
	 * new JOptionPane(); pause.getAccessibleContext()
	 * .setAccessibleDescription(" please enter the number of seconds you want to pause "
	 * ); secpause = Integer.parseInt(JOptionPane.showInputDialog(parent,
	 * "please enter the number of seconds you want to pause  ", null)); Object[]
	 * obj = new Object[] { secpause };
	 * workingScenario.addCommand(workingScenario.createNewCommand(
	 * EnumPossibleCommands.PAUSE, obj)); } // disp-string else if (comboIndex == 3)
	 * { String dispstring; JOptionPane disp = new JOptionPane();
	 * disp.getAccessibleContext().
	 * setAccessibleDescription("Please write the string disp "); dispstring =
	 * JOptionPane.showInputDialog(parent, "please write the String here ", null);
	 * EnumPossibleCommands cmd = EnumPossibleCommands.DISP_STRING;
	 * workingScenario.addCommand(workingScenario.createNewCommand(cmd, new String[]
	 * { dispstring })); }
	 * 
	 * // Skip else if (comboIndex == 4) { String skip; JOptionPane skipp = new
	 * JOptionPane(); skipp.getAccessibleContext().
	 * setAccessibleDescription("Please write the string you want to skip "); skip =
	 * JOptionPane.showInputDialog(parent,
	 * "please write the String you want to skip ", null); EnumPossibleCommands cmd
	 * = EnumPossibleCommands.SKIP;
	 * workingScenario.addCommand(workingScenario.createNewCommand(cmd, new String[]
	 * { skip })); } // skip button else if (comboIndex == 5) { String str; int
	 * bnts; JTextField btn = new JTextField();
	 * 
	 * Object[] obj = { "please enter the button number:", btn,
	 * "please enter the string here:", };
	 * 
	 * JOptionPane skipp = new JOptionPane(); str =
	 * (JOptionPane.showInputDialog(null, obj, "skip button",
	 * JOptionPane.OK_CANCEL_OPTION)); bnts = Integer.parseInt(btn.getText());
	 * EnumPossibleCommands cmd = EnumPossibleCommands.SKIP_BUTTON;
	 * workingScenario.addCommand(workingScenario.createNewCommand(cmd, new Object[]
	 * { bnts, str })); }
	 * 
	 * // Repeat Button else if (comboIndex == 6) {
	 * 
	 * JOptionPane rebnt = new JOptionPane(); rebnt.getAccessibleContext()
	 * .setAccessibleDescription(" please enter the button number you want to repeat "
	 * ); int btns; btns = Integer.parseInt( JOptionPane.showInputDialog(parent,
	 * "please enter the button number you want to repeat ", null));
	 * EnumPossibleCommands cmd = EnumPossibleCommands.REPEAT_BUTTON;
	 * workingScenario.addCommand(workingScenario.createNewCommand(cmd, new Object[]
	 * { btns })); } // disp clear cell else if (comboIndex == 9) { int cells;
	 * JOptionPane clrcell = new JOptionPane(); clrcell.getAccessibleContext().
	 * setAccessibleDescription(" please enter the cell number you want to clear ");
	 * cells = Integer.parseInt( JOptionPane.showInputDialog(parent,
	 * "please enter the cell number you want to clear ", null));
	 * EnumPossibleCommands cmd = EnumPossibleCommands.DISP_CLEAR_CELL;
	 * workingScenario.addCommand(workingScenario.createNewCommand(cmd, new Object[]
	 * { cells })); } // disp cell pin else if (comboIndex == 11) { int cell; int
	 * pin; JTextField pins = new JTextField();
	 * 
	 * Object[] obj = { "please enter the cell number:", pins,
	 * "please enter the pin number :", };
	 * 
	 * JOptionPane skipp = new JOptionPane(); pin =
	 * Integer.parseInt(JOptionPane.showInputDialog(null, obj, "skip button",
	 * JOptionPane.OK_CANCEL_OPTION)); cell = Integer.parseInt(pins.getText());
	 * EnumPossibleCommands cmd = EnumPossibleCommands.DISP_CELL_PINS;
	 * workingScenario.addCommand(workingScenario.createNewCommand(cmd, new Object[]
	 * { cell, pin })); }
	 * 
	 * updateSessionModel(); }
	 */

	/**
	 * Handles the click event for the add command button
	 */
	private void btnAddClickHandler() {
		EnumPossibleCommands cmdType = EnumPossibleCommands.values()[comboIndex];
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
	 * Handles the click event for the remove command button
	 */
	private void btnRemoveClickHandler() {
		if (listBoxIndex > 0) {
			workingScenario.removeCommand(workingScenario.getCommand(listBoxIndex));
		}
		updateSessionModel();
	}

	/**
	 * Handles the click event for the Move Up button
	 */
	private void btnMoveUpClickHandler() {
		// swapCommands(sessionScenarioList.getSelectedIndex(),sessionScenarioList.getSelectedIndex()-1);
		if (listBoxIndex > 0) {
			workingScenario.moveCommand(workingScenario.getCommand(listBoxIndex), listBoxIndex - 1);
		}
		updateSessionModel();
	}

	/**
	 * Handles the click event for the Move Down button
	 */
	private void btnMoveDownClickHandler() {
		// swapCommands(sessionScenarioList.getSelectedIndex(),sessionScenarioList.getSelectedIndex()+1);
		if (listBoxIndex >= 0 && listBoxIndex != sessionListModel.size() - 1) {
			workingScenario.moveCommand(workingScenario.getCommand(listBoxIndex), listBoxIndex + 1);
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
	}

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