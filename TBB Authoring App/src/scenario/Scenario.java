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
	
	public Scenario()
	{
		//TODO: Implement
		
	}
	
	public ScenarioCommand getEvent(int index)
	{
		//TODO: Implement
		
		return null;
	}
	
	/**
	 * Adds an event to the end of the scenario.
	 * 
	 * @param event The event or action to be added
	 */
	public void addEvent(ScenarioCommand event)
	{
		//TODO: Implement
	}
	
	/**
	 * Removes an event from the scenario.
	 * 
	 * @param event The event to be removed
	 */
	public void removeEvent(ScenarioCommand event)
	{
		//TODO: Implement
	}
	
	/**
	 * Moves an event from its current position to a new position. 
	 * Removes the event at the current index and inserts into specified 
	 * index, shifting all events after, further down. 
	 * 
	 * @param event The event to move
	 * @param newIndex The new position of the event
	 */
	public void moveEvent(ScenarioCommand event, int newIndex)
	{
		//TODO: Implement
	}

}
