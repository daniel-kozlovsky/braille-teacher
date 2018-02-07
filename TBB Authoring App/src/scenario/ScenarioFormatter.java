package scenario;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Formats and exports a scenario. 
 * 
 * @author DKozlovsky
 *
 */
public class ScenarioFormatter {

	public static final String SAVED_SCENARIOS_PATH = "scenarios\\";
	
	/**
	 * each element will be a line in the text file
	 */
	private static ArrayList<String> scenarioText = new ArrayList<String>();
	
	
	private ScenarioFormatter()
	{

	}
	
	/**
	 * Writes the formated scenario to a text file. Overwrites an existing file with the same name.
	 * 
	 * @param scenario the scenario to format
	 * @param filename name of the created text file
	 */
	public static void export(Scenario scenario, String filename)
	{
		format(scenario);
		try
		{
			File file = new File(SAVED_SCENARIOS_PATH + filename + ".txt");
			PrintWriter printWriter = new PrintWriter(file);
			for(String s : scenarioText)
			{
				printWriter.println(s);
			}
			
			printWriter.flush();
			printWriter.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
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
	private static void format(Scenario scenario)
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
					
						stringToAdd.append(arg.toString() + " ");
				}
			}
			
			scenarioText.add(stringToAdd.toString());
		}
		
	}
	//TODO add command validate to check command like skip are closed
	

}
