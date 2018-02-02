package scenario;

import java.util.ArrayList;

/**
 * Data structure which defines a scenario. Used for storing and accessing
 * a scenario. ScenarioEvents in the Scenario are in the order that the simulation 
 * will iterate through (chronological).
 * 
 * @author DKozlovsky
 *
 */
public class Scenario {
	
	private int numCells;
	private int numButtons;
	
	
	private ArrayList<ScenarioCommand> runningScenario;
	
	/**
	 * Default constructor; sets to 0
	 */
	public Scenario()
	{
		this(0,0);	
	}
	
	/**
	 * Set number of braille cells and buttons
	 * @param numcells
	 * @param numbuttons
	 */
	public Scenario(int numcells, int numbuttons)
	{
		numCells = numcells;
		numButtons = numbuttons;
		runningScenario = new ArrayList<ScenarioCommand>();
	}
	
	/**
	 * get the number of cells. Required for formatting.
	 * @return number of cells
	 */
	public int getNumCells()
	{
		return numCells;
	}
	
	/**
	 * get the number of buttons. Required for formatting
	 * @return number of buttons
	 */
	public int getNumButtons()
	{
		return numButtons;
	}
	
	public ScenarioCommand getEvent(int index)
	{
		
		return runningScenario.get(index);
	}
	
	/**
	 * Adds an event to the end of the scenario.
	 * 
	 * @param command The event or action to be added
	 */
	public void addEvent(ScenarioCommand command)
	{
		runningScenario.add(command);
	}
	
	/**
	 * Removes an event from the scenario.
	 * 
	 * @param command The event to be removed
	 */
	public void removeEvent(ScenarioCommand command)
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
	public void moveEvent(ScenarioCommand command, int newIndex) throws IllegalArgumentException
	{
		if(newIndex > runningScenario.size()-1 || newIndex < 0)
		{
			throw new IllegalArgumentException("newIndex must be within the list!");
		}
		runningScenario.remove(command);
		runningScenario.add(newIndex, command);
		
		
	}

}
