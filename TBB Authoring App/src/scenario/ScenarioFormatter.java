package scenario;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

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
	private static ArrayList<String> scenarioText = new ArrayList<String>();

	private ScenarioFormatter() {

	}

	/**
	 * Writes the formated scenario to a text file. Overwrites an existing file with
	 * the same name.
	 * 
	 * @param scenario
	 *            the scenario to format
	 * @param filename
	 *            name of the created text file
	 */
	public static void export(Scenario scenario, String filepath) {
		format(scenario);
		try {
			File file = new File(filepath);
			PrintWriter printWriter = new PrintWriter(file);
			for (String s : scenarioText) {
				printWriter.println(s);
			}

			printWriter.flush();
			printWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Formats a scenario for exporting. Overwrites existing formatting. The format
	 * is text and is outlined by EECS 2311 Scenario File Format Documentation
	 * 
	 * @see https://wiki.eecs.yorku.ca/course_archive/2017-18/W/2311/_media/scenarioformat.pdf
	 * 
	 * @param scenario
	 *            The scenario to be formatted. Every scenario command must have its
	 *            arguments satisfied
	 * 
	 */
	private static void format(Scenario scenario) {

		scenarioText.clear();
		// First two lines common for all scenarios
		scenarioText.add("Cells " + scenario.getNumCells());
		scenarioText.add("Button " + scenario.getNumButtons());

		for (ScenarioCommand command : scenario) {

			StringBuilder stringToAdd = new StringBuilder();

			// Format - Identifier
			stringToAdd.append(command.getFormat());

			Object[] arguments = command.getArguments();

			// if arguments exist
			if (arguments.length != 0) {
				// each argument
				// safe because only integer or String allowed
				for (int i = 0; i < arguments.length; i++) {

					stringToAdd.append(arguments[i].toString());
					if (i != arguments.length - 1) {
						stringToAdd.append(" "); // space to separate arguments as long as not last argument
					}
				}
			}

			scenarioText.add(stringToAdd.toString());
		}

	}

	/**
	 * Parses a text file into a Scenario object. Overwrites any existing scenarios.
	 * 
	 * @param path
	 *            The path and name of the file to import
	 * @return A parsed Scenario imported from a text file. Will return null if
	 *         number of cells and buttons is not the first two lines in the text
	 *         file.
	 */
	public static Scenario importParse(String path) {
		Scenario importedScenario;

		// removed hard-coded path
		File file = new File(path);
		try {
			Scanner fileScan = new Scanner(file);
			String numButtonsString;
			String numCellsString;

			if (fileScan.hasNext()) {
				numCellsString = fileScan.nextLine();
			} else {
				fileScan.close();
				return null;
			}
			if (fileScan.hasNext()) {
				numButtonsString = fileScan.nextLine();

			} else {
				fileScan.close();
				return null;
			}

			numButtonsString = numButtonsString.substring(7, numButtonsString.length());
			numCellsString = numCellsString.substring(6, numCellsString.length());

			// cells and buttons first
			int numCells = Integer.parseInt(numCellsString);
			int numButtons = Integer.parseInt(numButtonsString);

			importedScenario = new Scenario(numCells, numButtons);

			// Actual parsing of rest of file
			while (fileScan.hasNext()) {
				String line = fileScan.nextLine();

				// READ_TEXT command check
				if (!line.matches("^/~.*")) {
					importedScenario.addCommand(new ScenarioCommand(EnumPossibleCommands.READ_TEXT,
							new Object[] { line }, numCells, numButtons));

				}
				// /~ commands check
				else {
					for (EnumPossibleCommands possibleCommand : EnumPossibleCommands.values()) {
						// skip READ_TEXT
						if (possibleCommand.equals(EnumPossibleCommands.READ_TEXT)) {
							continue;
						}
						int possibleCommandLength = possibleCommand.getFormat().length();
						// wrong command if format length is longer than whole line
						if (possibleCommandLength > line.length()) {
							continue;
						}

						// match command to format
						if (line.substring(0, possibleCommandLength).equals(possibleCommand.getFormat())) {
							// store arguments
							ArrayList<Object> argsList = new ArrayList<Object>();
							String arguments = line.substring(possibleCommandLength);

							// Parsing arguments
							Class<?>[] possibleArgs = possibleCommand.getArgumentTypes();

							if (possibleArgs.length == 2) {
								String splitArgs[] = arguments.split(" ");
								for (String s : splitArgs) {
									argsList.add(s);
								}

							} else if (possibleArgs.length == 1) {
								argsList.add(arguments);
							}

							// Check argument types
							for (int x = 0; x < argsList.size(); x++) {
								if (possibleArgs[x].equals(Integer.class)) {
									argsList.set(x, Integer.parseInt(argsList.get(x).toString()));
								} else if (possibleArgs[x].equals(Character.class)) {
									argsList.set(x, argsList.get(x).toString().charAt(0));
								}
							}

							Object[] args = argsList.toArray();

							importedScenario
									.addCommand(new ScenarioCommand(possibleCommand, args, numCells, numButtons));
							break; // found command
						}
					}
				}
			}

			fileScan.close();
			return importedScenario;
		} catch (Exception e) {
			e.printStackTrace();
			// TODO add exception to throw instead of null return
			return null;
		}
	}

	// TODO add command validate to check commands like skip are closed

}
