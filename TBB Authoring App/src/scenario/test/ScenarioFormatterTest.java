package scenario.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.Test;

import scenario.EnumPossibleCommands;
import scenario.Scenario;
import scenario.ScenarioFormatter;

class ScenarioFormatterTest {

	@Test
	void testExport_createsFile() {
		Scenario testScenario = new Scenario(0, 0);
		testScenario.addCommand(testScenario.createNewCommand(EnumPossibleCommands.PAUSE, new Object[] { 1 }));

		ScenarioFormatter.export(testScenario, "\\test_scenarios\\testfile");
		File testFile = new File("scenarios\\test_scenarios\\testfile");

		assertTrue(testFile.exists());

	}

	@Test
	void testExportImport_allCommands() {
		final String correctTestString = "11110000";
		final Character correctTestChar = 'A';
		final int correctTestInt = 1;

		Scenario testScenario = new Scenario(1, 1);

		for (EnumPossibleCommands cmd : EnumPossibleCommands.values()) {
			Class<?>[] argTypes = cmd.getArgumentTypes();
			Object[] args = new Object[argTypes.length];

			// set correct and same arguments
			for (int i = 0; i < args.length; i++) {
				if (argTypes[i].equals(Integer.class)) {
					args[i] = correctTestInt;
				} else if (argTypes[i].equals(String.class)) {
					args[i] = correctTestString;
				} else if (argTypes[i].equals(Character.class)) {
					args[i] = correctTestChar;
				}
			}
			testScenario.addCommand(testScenario.createNewCommand(cmd, args));
		}
		ScenarioFormatter.export(testScenario, "\\test_scenarios\\allPossibleCommandsTest");

		Scenario importedScenario = ScenarioFormatter.importParse("\\test_scenarios\\allPossibleCommandsTest");

		boolean test = testScenario.deepEquals(importedScenario);
		assertTrue(test);
	}

	@Test
	void testImportParse_simpleCommands() {

		Scenario expScenario = new Scenario(1, 1);
		expScenario.addCommand(expScenario.createNewCommand(EnumPossibleCommands.PAUSE, new Object[] { 1 }));
		expScenario.addCommand(expScenario.createNewCommand(EnumPossibleCommands.END_REPEAT, new Object[] {}));
		Scenario actualScenario = ScenarioFormatter.importParse("\\test_scenarios\\importTestScenario_01");

		assertTrue(expScenario.deepEquals(actualScenario));
	}

	@Test
	void testImportParse_emptyFile() {
		Scenario importedScenario = ScenarioFormatter.importParse("\\test_scenarios\\importTestScenario_empty");
		assertEquals(importedScenario, null, "should equal null");
	}

}
