package scenario;

import java.util.ArrayList;

/**
 * Data structure which defines a scenario. Used for storing and accessing
 * a scenario.
 * 
 * @author DKozlovsky
 *
 */
public class Scenario {
	
	private int numCells;
	private int numButtons;
	
	ArrayList<ScenarioEvent> runningScenario;
	
	public Scenario()
	{
		
	}
	
	/**
	 * Adds an event to the end of the scenario.
	 * 
	 * @param event The event or action to be added
	 */
	public void addEvent(ScenarioEvent event)
	{
		
	}
	
	/**
	 * Removes an event from the scenario.
	 * 
	 * @param event The event to be removed
	 */
	public void removeEvent(ScenarioEvent event)
	{
		
	}
	
	/**
	 * Moves an event from its current position to a new position. 
	 * Removes the event at the current index and inserts into specified 
	 * index, shifting all events after, further down. 
	 * 
	 * @param event The event to move
	 * @param newIndex The new position of the event
	 */
	public void moveEvent(ScenarioEvent event, int newIndex)
	{
		
	}

}
