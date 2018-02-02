package scenario;

import java.util.ArrayList;

/**
 * Formats and exports a scenario. 
 * 
 * @author DKozlovsky
 *
 */
public class ScenarioFormatter {
	
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
	 * Formats a scenario for exporting. The format is text and is outlined 
	 * by EECS 2311 Scenario File Format Documentation
	 * @see https://wiki.eecs.yorku.ca/course_archive/2017-18/W/2311/_media/scenarioformat.pdf
	 * 
	 * @param scenario The scenario to be formatted.
	 *  Every scenario command must have its arguments satisfied
	 */
	private void format(Scenario scenario)
	{
		//First two lines common for all scenarios
		scenarioText.add("Cells " + scenario.getNumCells());
		scenarioText.add("Button " + scenario.getNumButtons());
		
		for(ScenarioCommand command : scenario)
		{
			
		}
		
	}

}
