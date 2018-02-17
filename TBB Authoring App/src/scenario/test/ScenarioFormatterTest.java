package scenario.test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import scenario.EnumPossibleCommands;
import scenario.Scenario;
import scenario.ScenarioFormatter;

class ScenarioFormatterTest {

	@Test
	void testExport() {
		fail("Not yet implemented");
	}

	@Test
	void testImportParse_NO_COMMANDS() {
		
		Scenario expScenario = new Scenario(1,1);
		expScenario.addCommand(expScenario.createNewCommand(EnumPossibleCommands.PAUSE, new Object[] {1}));
		expScenario.addCommand(expScenario.createNewCommand(EnumPossibleCommands.END_REPEAT, new Object[] {}));
		Scenario actualScenario = ScenarioFormatter.importParse("scenarios\\test_scenarios\\importTestScenario_01");
		
		assertTrue(expScenario.deepEquals(actualScenario));
	}

}
