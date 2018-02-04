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
		format(scenario);
		//TODO: Implement
	}
	
	/**
	 * Formats a scenario for exporting. Overwrites existing formatting. The format is text and is outlined 
	 * by EECS 2311 Scenario File Format Documentation
	 * @see https://wiki.eecs.yorku.ca/course_archive/2017-18/W/2311/_media/scenarioformat.pdf
	 * 
	 * @param scenario The scenario to be formatted.
	 *  Every scenario command must have its arguments satisfied
	 * 
	 */
	private void format(Scenario scenario)
	{
		
		scenarioText.clear();
		//First two lines common for all scenarios
		scenarioText.add("Cells " + scenario.getNumCells());
		scenarioText.add("Button " + scenario.getNumButtons());
		
		for(ScenarioCommand command : scenario)
		{
			
			StringBuilder stringToAdd = new StringBuilder();
			
			//Format - Identifier
			stringToAdd.append(command.getFormat());
			
			Object[] arguments = command.getArguments();
			
			//if arguments exist
			if(arguments.length != 0)
			{
				//each argument
				//safe because only int or String allowed
				for(Object arg : command.getArguments())
				{
					
						stringToAdd.append((String) arg + " ");
				}
			}
			
			scenarioText.add(stringToAdd.toString());
		}
		
	}
	

}
