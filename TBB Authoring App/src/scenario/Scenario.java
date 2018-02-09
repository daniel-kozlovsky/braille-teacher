package scenario;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Data structure which defines a scenario. Used for storing and accessing
 * a scenario. ScenarioCommands in the Scenario are in the order that the simulation 
 * will iterate through (chronological).
 * 
 * @author DKozlovsky
 *
 */

public class Scenario implements Iterable<ScenarioCommand> {
	
	private final int NUM_CELLS;
	private final int NUM_BUTTONS;
	
	
	private ArrayList<ScenarioCommand> runningScenario;
	
	
	/**
	 * Default constructor; sets cells and buttons to 0
	 * Note* the number of cells and buttons cannot be reset
	 */
	public Scenario()
	{
		this(0,0);	
	}
	
	/**
	 * Set number of braille cells and buttons
	 * @param numcells
	 * @param numbuttons
	 * @throws IllegalArgumentException if any parameter is negative
	 */
	public Scenario(int numcells, int numbuttons) throws IllegalArgumentException
	{
		if(numcells < 0 | numbuttons < 0)
		{
			throw new IllegalArgumentException("Cannot have negative number of buttons or cells!");
		}
		NUM_CELLS = numcells;
		NUM_BUTTONS = numbuttons;
		runningScenario = new ArrayList<ScenarioCommand>();
	}
	/**
	 * Factory method for creating a scenario command 
	 * @param cmd the type of specific command it is
	 * @param args the arguments for the command
	 * @return a new instance of ScenarioCommand with specified parameters
	 */
	public ScenarioCommand createNewCommand(EnumPossibleCommands cmd, Object[] args)
	{
		return new ScenarioCommand(cmd, args, NUM_CELLS, NUM_BUTTONS);
	}
	
	@Override
	public Iterator<ScenarioCommand> iterator() {
		
		return runningScenario.iterator();
	}
	
	/**
	 * get the number of cells. Required for formatting.
	 * @return number of cells
	 */
	public int getNumCells()
	{
		return NUM_CELLS;
	}
	
	/**
	 * get the number of buttons. Required for formatting
	 * @return number of buttons
	 */
	public int getNumButtons()
	{
		return NUM_BUTTONS;
	}
	
	public ScenarioCommand getCommand(int index)
	{
		
		return runningScenario.get(index);
	}
	
	/**
	 * Adds an event to the end of the scenario.
	 * 
	 * @param command The event or action to be added
	 */
	public void addCommand(ScenarioCommand command)
	{
		runningScenario.add(command);
	}
	
	/**
	 * Removes an event from the scenario.
	 * 
	 * @param command The event to be removed
	 */
	public void removeCommand(ScenarioCommand command)
	{
		runningScenario.remove(command);
	}
	
	/**
	 * Moves an event from its current position to a new position. Elements are shifted left 
	 * after removal, then right after insertion.
	 * 
	 * @param command The event to move
	 * @param newIndex The new position of the event. Cannot be outside the list
	 * @throws IllegalArgumentException if newIndex is not in the list
	 */
	public void moveCommand(ScenarioCommand command, int newIndex) throws IllegalArgumentException
	{
		if(newIndex > runningScenario.size()-1 || newIndex < 0)
		{
			throw new IllegalArgumentException("newIndex must be within the list!");
		}
		runningScenario.remove(command);
		runningScenario.add(newIndex, command);
		
		
	}

	//TODO implement equals for testing

}
