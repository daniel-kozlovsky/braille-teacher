package scenario;

import java.util.ArrayList;
import java.util.Iterator;


/**
 * Data structure which defines a scenario. Used for storing and accessing a
 * scenario. ScenarioCommands in the Scenario are in the order that the
 * simulation will iterate through (chronological).
 * 
 * @author DKozlovsky
 *
 */

public class Scenario implements Iterable<ScenarioCommand> {

	private final int NUM_CELLS;
	private final int NUM_BUTTONS;

	private ArrayList<ScenarioCommand> runningScenario;

	/**
	 * Default constructor; sets cells and buttons to 0 Note* the number of cells
	 * and buttons cannot be reset
	 */
	public Scenario() {
		this(0, 0);
	}

	/**
	 * Create a new Scenario with specified number of braille cells and buttons.
	 * These parameters remain consistent throughout the scenario due to formatting
	 * requirements.
	 * 
	 * @param number
	 *            of cells in the scenario
	 * @param number
	 *            of buttons in the scenario
	 * @throws IllegalArgumentException
	 *             if any parameter is negative
	 */
	public Scenario(int numcells, int numbuttons) throws IllegalArgumentException {
		if (numcells < 0 || numbuttons < 0) {
			throw new IllegalArgumentException("Cannot have negative number of buttons or cells!");
		}
		NUM_CELLS = numcells;
		NUM_BUTTONS = numbuttons;
		runningScenario = new ArrayList<ScenarioCommand>();
	}

	/**
	 * Factory method for creating a scenario command
	 * 
	 * @param cmd
	 *            the type of specific command it is
	 * @param args
	 *            the arguments for the command
	 * @return a new instance of ScenarioCommand with specified parameters
	 */
	public ScenarioCommand createNewCommand(EnumPossibleCommands cmd, Object[] args) {
		return new ScenarioCommand(cmd, args, NUM_CELLS, NUM_BUTTONS);
	}

	@Override
	public Iterator<ScenarioCommand> iterator() {

		return runningScenario.iterator();
	}

	/**
	 * get the number of cells. Required for formatting.
	 * 
	 * @return number of cells
	 */
	public int getNumCells() {
		return NUM_CELLS;
	}

	/**
	 * get the number of buttons. Required for formatting
	 * 
	 * @return number of buttons
	 */
	public int getNumButtons() {
		return NUM_BUTTONS;
	}

	public ScenarioCommand getCommand(int index) {

		return runningScenario.get(index);
	}

	/**
	 * Adds an event to the end of the scenario.
	 * 
	 * @param command
	 *            The event or action to be added
	 */
	public void addCommand(ScenarioCommand command) {
		runningScenario.add(command);
	}

	/**
	 * Removes an event from the scenario.
	 * 
	 * @param command
	 *            The event to be removed
	 */
	public void removeCommand(ScenarioCommand command) {
		runningScenario.remove(command);
	}

	/**
	 * @return Current amount of commands in scenario.
	 */
	public int getNumCommands() {
		return runningScenario.size();
	}

	/**
	 * Moves an event from its current position to a new position. Elements are
	 * shifted left after removal, then right after insertion.
	 * 
	 * @param command
	 *            The event to move
	 * @param newIndex
	 *            The new position of the event. Cannot be outside the list
	 * @throws IllegalArgumentException
	 *             if newIndex is not in the list
	 */
	public void moveCommand(ScenarioCommand command, int newIndex) throws IllegalArgumentException {
		if (newIndex > runningScenario.size() - 1 || newIndex < 0) {
			throw new IllegalArgumentException("newIndex must be within the list!");
		}
		runningScenario.remove(command);
		runningScenario.add(newIndex, command);
	}

	/**
	 * Test two scenarios for equality. Equality is defined by having same field
	 * values as well as same commands in the same order. Each command's equality is
	 * based on ScenarioCommand.deepEquals().
	 * 
	 * @param scenario
	 *            The second scenario to compare.
	 */
	public boolean deepEquals(Scenario scenario) {
		if (this.equals(scenario)) {
			return true;
		}
		if (this.NUM_BUTTONS == scenario.getNumButtons() && this.NUM_CELLS == scenario.getNumCells()
				&& this.getNumCommands() == scenario.getNumCommands()) {
			for (int i = 0; i < this.getNumCommands(); i++) {
				if (!this.getCommand(i).deepEquals(scenario.getCommand(i))) {
					return false;
				}
			}
			return true;
		}
		return false;

	}

}
