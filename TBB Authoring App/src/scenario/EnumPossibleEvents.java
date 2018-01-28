package scenario;

/**
 * All the possible commands, action and events for a scenario
 * Includes: name, description and the formatted command. 
 * 
 * @author DK
 *
 */
public enum EnumPossibleEvents {
	
	PAUSE ("Pause", "Pauses the simulation", "/~pause");
	//TODO: add values for all commands
	/*DISP_STRING,
	REPEAT,
	END_REPEAT,
	REPEAT_BUTTON,
	SKIP_BUTTON,
	USER_INPUT,
	SOUND,
	RESET_BUTTONS,
	SKIP,
	DISP_CLEAR_ALL,
	DISP_CLEAR_CELL;*/
	
	
	private final String eventName;
	private final String eventDesc;
	private final String eventFormat;
	
	EnumPossibleEvents(String eventname, String eventdesc, String eventformat)
	{
		this.eventName = eventname;
		this.eventDesc = eventdesc;
		this.eventFormat = eventformat;
	}
	
	public String getName()
	{
		return eventName;
	}
	
	public String getDescription() 
	{
		return eventDesc;
	}
	
	public String getFormat()
	{
		return eventFormat;
	}

}
