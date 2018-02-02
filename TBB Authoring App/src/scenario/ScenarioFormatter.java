package scenario;

import java.util.ArrayList;

/**
 * Formats and exports a scenario. 
 * 
 * @author DKozlovsky
 *
 */
public class ScenarioFormatter {
	/**
	 * each element will be a line in the text file
	 */
	private ArrayList<String> scenarioText;
	
	public ScenarioFormatter()
	{
		scenarioText = new ArrayList<String>();
	}
	
	/**
	 * Exports a scenario
	 * 
	 * @param scenario the scenario to format
	 * @param filename name of the created text file
	 */
	public void export(Scenario scenario, String filename)
	{
		try 
		{
			format(scenario);
			
		} catch (MissingArgumentException e)
		{
			
			e.printStackTrace();
		}
		//TODO: Implement
	}
	
	/**
	 * Formats a scenario for exporting. The format is text and is outlined 
	 * by EECS 2311 Scenario File Format Documentation
	 * @see https://wiki.eecs.yorku.ca/course_archive/2017-18/W/2311/_media/scenarioformat.pdf
	 * 
	 * @param scenario The scenario to be formatted.
	 *  Every scenario command must have its arguments satisfied
	 * @throws MissingArgumentException if a specific command is missing arguments
	 */
	private void format(Scenario scenario) throws MissingArgumentException
	{
		//First two lines common for all scenarios
		scenarioText.add("Cells " + scenario.getNumCells());
		scenarioText.add("Button " + scenario.getNumButtons());
		
		for(ScenarioCommand command : scenario)
		{
			
			validateArguments(command);
			String stringToAdd;
			
			
			//TODO format scenario - add to array list
			
			scenarioText.add(stringToAdd);
		}
		
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
