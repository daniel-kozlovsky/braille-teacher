package scenario;

import java.util.Arrays;

/**
 * Defines an event in a scenario.
 * 
 * @author DKozlovsky
 *
 */
public abstract class ScenarioCommand{
	
	
	private Object[] arguments;
	private EnumPossibleCommands command;
	
	
	public ScenarioCommand(EnumPossibleCommands command, Object[] arguments)
	{
		this.command = command;
		setArguments(arguments);
	}
	
	/**
	 * Set the arguments for a specific command. Overwrites current arguments. 
	 *
	 * @param arguments The arguments to set. Must correspond to command, i.e: same amount of arguments
	 * and same types.
	 * Example: the disp_cell_char command must have arguments {Integer, String}.
	 * 
	 * @throws IllegalArgumentException If parameter conditions are not satisfied.
	 * 
	 */
	public void setArguments(Object[] arguments) throws IllegalArgumentException
	{
		//TODO need to specify argument bounds
		
		if(arguments.length != command.getArgumentTypes().length)
		{
			throw new IllegalArgumentException("Invalid amount of arguments!");
		}
		//TODO fix argument type check. Current else if is wrong.
		else if(!Arrays.equals(arguments, command.getArgumentTypes()))
		{
			throw new IllegalArgumentException("Invalid types in argument");
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
		return command.getName();
	}
	
	/**
	 * Get the description for the command
	 * @return the description of the command
	 */
	public String getDescription() 
	{
		return command.getDescription();
	}
	
	/**
	 * Get the command format
	 * @return the formatting of the command
	 */
	public String getFormat()
	{
		return command.getFormat();
	}
	/**
	 * get the arguments for a command
	 * @return the arguments
	 */
	public Object[] getArguments()
	{
		return this.arguments;
	}
	
	/**
	 * Gets the type of command that this ScenarioCommand is. 
	 * Does not return reference to actual command.
	 * 
	 * @return a copy of the command
	 */
	public EnumPossibleCommands getEnum()
	{
		EnumPossibleCommands e = command;
		return e;
		//TODO: Test privacy
	}
	/**
	 * Checks arguments for a ScenarioCommand to make sure they are not null if they
	 * are required by the command encapsulated by ScenarioCommand
	 * 
	 * @param cmd - The ScenarioCommand to check
	 * @throws MissingArgumentException if null arguments are found when required
	 */
	private void validateArguments(ScenarioCommand cmd) throws MissingArgumentException
	{
		//TODO error checks for arguments bounds
		//TODO this is to loop to specify error message. may remove
		for(EnumPossibleCommands enumCmd : EnumPossibleCommands.values())
		{
			//skip commands without arguments
			if(cmd.getEnum() != EnumPossibleCommands.END_REPEAT && 
					cmd.getEnum() != EnumPossibleCommands.USER_INPUT &&
					cmd.getEnum() != EnumPossibleCommands.RESET_BUTTONS &&
					cmd.getEnum() != EnumPossibleCommands.DISP_CLEAR_ALL)
			{
				//TODO see above
				if(cmd.getEnum().equals(enumCmd))
				{	
					//null arguments found
					if(cmd.getArguments().equals(null))
					{
						throw new MissingArgumentException(
								"Missing required argument for " + enumCmd.toString());
					}
				}
			}
		}
		
		
	}

}
