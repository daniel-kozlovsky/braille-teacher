package scenario;

import java.util.Arrays;

/**
 * Defines an event in a scenario.
 * 
 * @author DK
 *
 */
public abstract class ScenarioEvent{
	
	private String name;
	private String description;
	private String format;
	private Object[] argumentTypes;
	private Object[] arguments;
	
	
	
	public ScenarioEvent(EnumPossibleEvents event)
	{
		this.name = event.getName();
		this.description = event.getDescription();
		this.format = event.getFormat();
		this.argumentTypes = event.getArgumentTypes();
	}
	/**
	 * Set the arguments for a specific command. Overwrites current arguments. 
	 *
	 * @param arguments The arguments to set. Must correspond to command, i.e: same amount of arguments
	 * and same types.
	 * Example: the disp_cell_char command must have arguments {Integer, String}.
	 * 
	 * @throws ImproperArgumentsException If parameter conditions are not satisfied.
	 * 
	 */
	public void setArguments(Object[] arguments) throws ImproperArgumentsException
	{
		if(arguments.length != this.argumentTypes.length)
		{
			throw new ImproperArgumentsException("Invalid amount of arguments!");
		}
		else if(!Arrays.equals(arguments, this.argumentTypes))
		{
			throw new ImproperArgumentsException("Invalid types in argument");
		}
		else
		{
			this.arguments = arguments;
		}
	}
	
	/**
	 * Get the name of the command
	 * @return the name of the command
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * Get the description for the command
	 * @return the description of the command
	 */
	public String getDescription() 
	{
		return description;
	}
	
	/**
	 * Get the command format
	 * @return the formatting of the command
	 */
	public String getFormat()
	{
		return format;
	}

}
