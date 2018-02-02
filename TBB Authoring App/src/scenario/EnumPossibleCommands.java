package scenario;

/**
 * All the possible commands, action and commands for a scenario
 * Includes: name, description,formatted command and a class array indicating 
 * number of arguments and types of each. 
 * 
 * @author DKozlovsky
 *
 */
public enum EnumPossibleCommands {
	
	READ_TEXT ("Read Text", "Reads text outloud",
			"", new Class<?>[] {String.class}),
	/**
	 * Pauses the scenario
	 */
	PAUSE ("Pause", "Pauses the scenario",
			"/~pause:", new Class<?>[] {Integer.class}),
	/**
	 * Displays text on screen
	 */
	DISP_STRING ("Display Text", "Shows text on the screen",
			"/~disp-sring:", new Class<?>[] {String.class}),
	/**
	 * Sets up beginning of text to be repeated
	 */
	REPEAT("Repeat", "Sets the beginning of some text to be repeated",
			"/~repeat:", new Class<?>[] {String.class}),
	/**
	 * Sets up end of text to be repeated. Must be accompanied with END_REPEAT
	 */
	END_REPEAT("End Repeat", "Sets the end of some text to be repeated", 
			"/~end-repeat", new Class<?>[] {}),
	/**
	 * Sets a button to repeat most recent set up 'repeat' text
	 */
	REPEAT_BUTTON("Repeat Button", "Makes specified button repeat most recent repeat text",
			"/~repeat-button:", new Class<?>[] {Integer.class}),
	/**
	 * Sets a button to skip to specified point in the scenario
	 */
	SKIP_BUTTON("Skip Button", "Makes button skip to a different point in scenario",
			"/~skip-button:", new Class<?>[] {Integer.class, String.class}),
	/**
	 * Location to skip to. Must be accompanied with GO_HERE
	 */
	GO_HERE("Skip Here", "Skips to this location in the scenario",
			"/~", new Class<?>[] {String.class}),
	/**
	 * Pauses the scenario to allow for button presses
	 */
	USER_INPUT("User Input", "Waits for a button press",
			"/~user-input", new Class<?>[] {}),
	/**
	 * Play a sound
	 */
	SOUND("Sound", "Plays a sound", 
			"/~sound:", new Class<?>[] {String.class}),
	/**
	 * Sets all buttons to inoperative
	 */
	RESET_BUTTONS("Reset Buttons", "Makes all buttons inoperative",
			"/~reset-buttons", new Class<?>[] {}),
	/**
	 * Skips to specified location
	 */
	SKIP("Skip", "Skips to specified location in scenario",
			"/~skip:", new Class<?>[] {String.class}),
	/**
	 * Location to skip to. Must be accompanied with SKIP_LOCATION
	 */
	SKIP_LOCATION("Skip Location", "Location to skip to", 
			"/~", new Class<?>[] {String.class}),
	/**
	 * Clears all braille cells in the simulator
	 */
	DISP_CLEAR_ALL("Clear All Cell Displays", "Clears all braille displays",
			"/~disp-clearAll", new Class<?>[] {}),
	/**
	 * Clear a specific braille cell
	 */
	DISP_CLEAR_CELL("Clear Cell Display", "Clears a specific braille display",
			"/~disp-clear-cell:", new Class<?>[] {Integer.class}),
	/**
	 * Sets specified pins on a braille cell
	 */
	DISP_CELL_PINS("Display Cell Pins", "Shows certain pins on a specified braille cell",
			"/~disp-cell-pins:", new Class<?>[] {Integer.class, String.class}),
	/**
	 * Displays an English letter on a braille cell
	 */
	DISP_CELL_CHAR("Display Character", "Displays an English character on a specified braille cell",
			"/~disp-cell-char:", new Class<?>[] {char.class}),
	/**
	 * Raise a specific pin on a braille cell
	 */
	DISP_CELL_RAISE("Raise Pin", "Raises a specific pin on the specified braille cell",
			"/~disp-cell-raise:", new Class<?>[] {Integer.class, Integer.class}),
	/**
	 * Lower a specific pin on a braille cell
	 */
	DISP_CELL_LOWER("Lower Pin", "Lowers a specific pin on the specified braille cell",
			"/~disp-cell-lower:", new Class<?>[] {Integer.class, Integer.class});
	
	
	
	private final String commandName;
	private final String commandDesc;
	private final String commandFormat;
	private final Class<?>[] argumentTypes;
	
	EnumPossibleCommands(String commandname, String commanddesc, String commandformat, Class<?>[] args)
	{
		this.argumentTypes = args;
		this.commandName = commandname;
		this.commandDesc = commanddesc;
		this.commandFormat = commandformat;
	}
	
	public String getName()
	{
		return commandName;
	}
	
	public String getDescription() 
	{
		return commandDesc;
	}
	
	public String getFormat()
	{
		return commandFormat;
	}
	
	public Class<?>[] getArgumentTypes()
	{
		return argumentTypes;
	}
	

}
