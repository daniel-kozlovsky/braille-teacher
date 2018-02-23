package scenario;

import java.util.Arrays;

/**
 * Defines an event/command in a scenario.
 * 
 * @author DKozlovsky
 *
 */
public class ScenarioCommand {

	private Object[] arguments;
	private EnumPossibleCommands command;
	private final int NUM_CELLS_IN_SCENARIO;
	private final int NUM_BUTTONS_IN_SCENARIO;

	/**
	 * A new instance of ScenarioCommand which encapsulates a command or event in
	 * the working scenario. Can only be made through a scenario. Same instance
	 * cannot be used in more than one scenario.
	 * 
	 * @param command
	 *            the type of command that this will be.
	 * @param numcells
	 *            number of braille cells in the scenario
	 * @param numbuttons
	 *            number of buttons in the scenario
	 * @param arguments
	 *            argument for specified command. If no arguments are required, pass
	 *            in null.
	 * 
	 * @throws IllegalArgumentException
	 */
	ScenarioCommand(EnumPossibleCommands command, Object[] arguments, int numCells, int numButtons)
			throws IllegalArgumentException, NullPointerException {
		if (command == null) {
			throw new NullPointerException("command must not be null!");
		}
		if (numCells < 0 || numButtons < 0) {
			throw new IllegalArgumentException("Cannot have negative amount of cells or buttons!");
		}
		NUM_CELLS_IN_SCENARIO = numCells;
		NUM_BUTTONS_IN_SCENARIO = numButtons;
		this.command = command;
		setArguments(arguments);
	}

	/**
	 * Set the arguments for a specific command. Overwrites existing arguments.
	 *
	 * @param arguments
	 *            The arguments to set. Must correspond to command, i.e: same amount
	 *            of arguments and same types. If no arguments required pass in
	 *            null. Example: the disp_cell_char command must have arguments
	 *            {Integer, String}.
	 *
	 * @throws IllegalArgumentException
	 *             If parameter conditions are not satisfied.
	 * 
	 * 
	 */
	public void setArguments(Object[] arguments) throws IllegalArgumentException {
		// empty array to symbolize no arguments
		if (arguments == null) {
			arguments = new Object[0];
		}
		Class<?>[] commandArguments = command.getArgumentTypes();

		// Check number of arguments
		if (arguments.length != commandArguments.length) {
			throw new IllegalArgumentException("Invalid amount of arguments!");
		}
		// Check types of arguments
		for (int i = 0; i < commandArguments.length; i++) {
			if (!arguments[i].getClass().equals(commandArguments[i])) {
				throw new IllegalArgumentException("Argument type invalid! Expected: " + commandArguments[i]);
			}
		}
		// Check arguments boundaries
		try {
			validateArguments(arguments);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Argument(s) are nonsensical: " + e.getMessage());
		}
		this.arguments = arguments;

	}

	/**
	 * Get the name of the command
	 * 
	 * @return the name of the command
	 */
	public String getName() {
		return command.getName();
	}

	/**
	 * Get the description for the command
	 * 
	 * @return the description of the command
	 */
	public String getDescription() {
		return command.getDescription();
	}

	/**
	 * Get the command format
	 * 
	 * @return the formatting of the command
	 */
	public String getFormat() {
		return command.getFormat();
	}

	/**
	 * get the arguments for a command
	 * 
	 * @return the arguments
	 */
	public Object[] getArguments() {
		return this.arguments;
	}

	/**
	 * Gets the type of command that this ScenarioCommand is. Does not return
	 * reference to actual command.
	 * 
	 * @return a copy of the command
	 */
	public EnumPossibleCommands getCommandType() {
		return this.command;
	}

	/**
	 * Turns details of a command into a string so a user could see what it does.
	 * Includes arguments and what they do.
	 * 
	 * @return The string representation of a commands function
	 */
	@Override
	public String toString() {
		StringBuilder returnString = new StringBuilder();
		returnString.append(this.command.getName());
		returnString.append(" - ");
		returnString.append(this.command.getDescription());
		returnString.append(". ");
		returnString.append(argsToString());

		return returnString.toString();
	}

	/**
	 * Text representation of what arguments of the scenario do.
	 * 
	 * @return The arguments and what they do
	 */
	public String argsToString() {
		String returnString;
		switch (this.command) {
		case READ_TEXT:
			returnString = "Reads: " + arguments[0].toString();
			break;

		case PAUSE:
			returnString = "Pauses for: " + arguments[0].toString() + " seconds";
			break;

		case DISP_STRING:
			returnString = "Displays the text: " + arguments[0].toString();
			break;

		case REPEAT:
			returnString = "Repeats the following text: " + arguments[0].toString();
			break;

		case END_REPEAT:
			returnString = "End of text to be repeated";
			break;

		case REPEAT_BUTTON:
			returnString = "Button: " + arguments[0].toString();
			break;

		case SKIP_BUTTON:
			returnString = "Button: " + arguments[0].toString() + " will skip to: " + arguments[1].toString();
			break;

		case SOUND:
			returnString = "Will play file: " + arguments[0].toString();
			break;

		case SKIP:
			returnString = "Will skip to: " + arguments[0].toString();
			break;

		case SKIP_LOCATION:
			returnString = "Location: " + arguments[0].toString();
			break;

		case DISP_CELL_CLEAR:
			returnString = "Clears display: " + arguments[0].toString();
			break;

		case DISP_CELL_PINS:
			returnString = "Will display pins: " + arguments[1].toString() + " on cell: " + arguments[0].toString();
			break;
		case DISP_CELL_CHAR:
			returnString = "Will display: " + arguments[1].toString() + " on cell: " + arguments[0].toString();
			break;
		case DISP_CELL_RAISE:
			returnString = "Raises pin: " + arguments[1].toString() + " on cell: " + arguments[0].toString();
			break;
		case DISP_CELL_LOWER:
			returnString = "Lowers pin: " + arguments[1].toString() + " on cell: " + arguments[0].toString();
			break;

		default:
			returnString = "";
		}
		return returnString;
	}

	/**
	 * Checks arguments to make sure they make are valid depending on the command
	 * 
	 * @param cmd
	 *            - The ScenarioCommand to check
	 * @throws IllegalArgumentException
	 *             if arguments do not match requirements
	 */
	private void validateArguments(Object[] args) throws IllegalArgumentException {

		switch (this.command) {
		case READ_TEXT:
			if (args[0].equals("")) {
				throw new IllegalArgumentException("Cannot read zero text!");
			}
			break;
		case PAUSE:
			if ((int) args[0] <= 0) {
				throw new IllegalArgumentException("Cannot pause for negative or zero time!");
			}
			break;
		case DISP_STRING:
			if (args[0].equals("")) {
				throw new IllegalArgumentException("Must display some string!");
			}
			break;
		case REPEAT:
			if (args[0].equals("")) {
				throw new IllegalArgumentException("Must have some string to repeat!");
			}
			break;
		case REPEAT_BUTTON:
			if ((int) args[0] < 0 || (int) args[0] >= NUM_BUTTONS_IN_SCENARIO) {
				throw new IllegalArgumentException("Buttons index must correspond to existing button!");
			}
			break;
		case SKIP_BUTTON:
			if ((int) args[0] < 0 || (int) args[0] >= NUM_BUTTONS_IN_SCENARIO) {
				throw new IllegalArgumentException("Buttons index must correspond to existing button!");
			} else if (args[1].equals("")) {
				throw new IllegalArgumentException("Must specify a location to skip to!");
			}
			break;
		// TODO : check for sound file existence and format
		case SOUND:
			if (args[0].equals("")) {
				throw new IllegalArgumentException("Cannot have empty sound file name!");
			}
			break;
		case SKIP:
			if (args[0].equals("")) {
				throw new IllegalArgumentException("Must specify a location to skip to!");
			}
			break;
		case SKIP_LOCATION:
			if (args[0].equals("")) {
				throw new IllegalArgumentException("Cannot have an empty skip location name!");
			}
			break;
		case DISP_CELL_CLEAR:
			if ((int) args[0] < 0 || (int) args[0] >= NUM_CELLS_IN_SCENARIO) {
				throw new IllegalArgumentException("Cell index must correspond to existing cell");
			}
			break;
		case DISP_CELL_PINS:
			if ((int) args[0] < 0 || (int) args[0] >= NUM_CELLS_IN_SCENARIO) {
				throw new IllegalArgumentException("Cell index must correspond to existing cell");
			} else if (args[1].toString().length() < 8) {
				throw new IllegalArgumentException("Must have an 8 character sequence!");
			} else if (args[1].toString().matches("[^10]")) {
				throw new IllegalArgumentException("Characters can only be 1 or 0!");
			}
			break;
		case DISP_CELL_CHAR:
			if ((int) args[0] < 0 || (int) args[0] >= NUM_CELLS_IN_SCENARIO) {
				throw new IllegalArgumentException("Cell index must correspond to existing cell");
			} else if (args[1].toString().matches("[^a-zA-z]")) {
				throw new IllegalArgumentException("Must be from English alphabet!");
			}
			break;
		case DISP_CELL_RAISE:
			if ((int) args[0] < 0 || (int) args[0] >= NUM_CELLS_IN_SCENARIO) {
				throw new IllegalArgumentException("Cell index must correspond to existing cell");
			} else if ((int) args[1] > 8 || (int) args[1] < 0) {
				throw new IllegalArgumentException("Must choose one of eight pins!");
			}
			break;
		case DISP_CELL_LOWER:
			if ((int) args[0] < 0 || (int) args[0] >= NUM_CELLS_IN_SCENARIO) {
				throw new IllegalArgumentException("Cell index must correspond to existing cell");
			} else if ((int) args[1] > 8 || (int) args[1] < 0) {
				throw new IllegalArgumentException("Must choose one of eight pins!");
			}
			break;

		default:
			;
		}

	}

	/**
	 * Provides a deep equals that checks parent against parameter. Only works with
	 * other ScenarioCommand objects.
	 *
	 * @param cmd
	 *            The ScenarioCommand to compare with
	 * 
	 * @return True if same object OR have same field values. <br/>
	 *         False otherwise.
	 * 
	 * @see java.lang.Object.equals()
	 */
	public boolean deepEquals(ScenarioCommand cmd) {
		if (this.equals(cmd)) {
			return true;
		}
		if (this.NUM_BUTTONS_IN_SCENARIO == cmd.NUM_BUTTONS_IN_SCENARIO
				&& this.NUM_CELLS_IN_SCENARIO == cmd.NUM_CELLS_IN_SCENARIO
				&& Arrays.deepEquals(this.arguments, cmd.getArguments()) && this.command == cmd.command) {
			return true;
		}

		return false;
	}

}
