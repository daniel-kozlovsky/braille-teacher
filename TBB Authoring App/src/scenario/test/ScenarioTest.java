package scenario.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import scenario.EnumPossibleCommands;
import scenario.Scenario;
import scenario.ScenarioCommand;

class ScenarioTest {

	@Test
	void testMoveCommand() {
		Scenario sc = new Scenario(0,0);
		ScenarioCommand cmdA = sc.createNewCommand(EnumPossibleCommands.PAUSE, new Object[] {1});
		ScenarioCommand cmdB = sc.createNewCommand(EnumPossibleCommands.READ_TEXT, new Object[] {"g"});
		sc.addCommand(cmdA);
		sc.addCommand(cmdB);
		
		sc.moveCommand(sc.getCommand(0), 1);
		
		assertEquals(sc.getCommand(0), cmdB);
		
	}
	
	@Test
	void testDeepEquals()
	{
		Scenario scA = new Scenario(1,1);
		Scenario scB = new Scenario(1,1);
		
		scA.addCommand(scA.createNewCommand(EnumPossibleCommands.PAUSE, new Object[] {1}));
		scA.addCommand(scA.createNewCommand(EnumPossibleCommands.READ_TEXT, new Object[] {"test"}));
		scA.addCommand(scA.createNewCommand(EnumPossibleCommands.DISP_STRING, new Object[] {"test"}));
		
		scB.addCommand(scB.createNewCommand(EnumPossibleCommands.PAUSE, new Object[] {1}));
		scB.addCommand(scB.createNewCommand(EnumPossibleCommands.READ_TEXT, new Object[] {"test"}));
		scB.addCommand(scB.createNewCommand(EnumPossibleCommands.DISP_STRING, new Object[] {"test"}));
		
		assertTrue(scA.deepEquals(scB));
		
	}

}
