package scenario;
/**
 * Defines an event in a scenario.
 * 
 * @author DK
 *
 */
public class ScenarioEvent<A> {
	
	private String name;
	private String description;
	private String format;
	private A argument;
	
	
	public ScenarioEvent(EnumPossibleEvents event)
	{
		this.name = event.getName();
		this.description = event.getDescription();
		this.format = event.getFormat();
	}
	
	/**
	 * Set a parameter for the Scenario event
	 * 
	 * @param argument the parameter to add. Can be a child of String
	 * or Integer
	 */
	public <A extends Integer >void addArgument(A argument)
	{
		
	}
	
	public <A extends String> void addArgument(A argument)
	{
		
	}

}
