package scenario.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import scenario.EnumPossibleCommands;
import scenario.Scenario;
import scenario.ScenarioCommand;

class ScenarioCommandTest {

	Scenario testScenario;

	final int[] TEST_NUM_BUTTONS_SET = { -1, 0, 1, 2, 3 };
	final int[] TEST_NUM_CELLS_SET = { -1, 0, 1, 2, 3 };
	final int SCENARIOS_TO_REPEAT = TEST_NUM_CELLS_SET.length * TEST_NUM_BUTTONS_SET.length;

	// Test cases for correct inputs

	String[] correctTestStringSet = new String[] { "10101010", "11111111" };
	int correctTestInt;
	Character[] correctTestCharSet = new Character[] { 'A', 'Z' };

	// Test cases for incorrect inputs
	String[] incorrectTestStringSet = { "" };
	int[] incorrectTestIntSet;
	Character[] incorrectTestCharSet = { '*', '0', '+' };

	Random rand = new Random();

	@BeforeEach
	protected void setUpClass() throws Exception {
		// reset integer because of dependency on buttons/cells
		testScenario = new Scenario();
		correctTestInt = 0;
		incorrectTestIntSet = null;

	}

	@Test
	void testDeepEquals_sameObjSingle() {
		ScenarioCommand cmdA = testScenario.createNewCommand(EnumPossibleCommands.READ_TEXT, new Object[] { "Test" });
		ScenarioCommand cmdB = cmdA;
		try {
			assertTrue(cmdA.deepEquals(cmdB));
		} catch (Exception e) {
			e.printStackTrace();
			fail("Should not throw exception!");
		}
	}

	@Test
	void testDeepEquals_sameObjAllCommands() {
		Scenario sc = new Scenario(1, 1);

		for (EnumPossibleCommands cmd : EnumPossibleCommands.values()) {
			Class<?>[] argTypes = cmd.getArgumentTypes();
			Object[] args = new Object[argTypes.length];

			// set correct and same arguments
			for (int i = 0; i < args.length; i++) {
				if (argTypes[i].equals(Integer.class)) {
					args[i] = 1;
				} else if (argTypes[i].equals(String.class)) {
					args[i] = correctTestStringSet[0];
				} else if (argTypes[i].equals(Character.class)) {
					args[i] = correctTestCharSet[0];
				}
			}

			ScenarioCommand cmdA = sc.createNewCommand(cmd, args);
			ScenarioCommand cmdB = sc.createNewCommand(cmd, args);

			assertTrue(cmdA.deepEquals(cmdB));
			assertTrue(cmdB.deepEquals(cmdA));
		}

	}

	@Test
	void testDeepEquals_sameArgs() {
		ScenarioCommand cmdA = testScenario.createNewCommand(EnumPossibleCommands.READ_TEXT, new Object[] { "Test" });
		ScenarioCommand cmdB = testScenario.createNewCommand(EnumPossibleCommands.READ_TEXT, new Object[] { "Test" });

		assertTrue(cmdA.deepEquals(cmdB));
	}

	@Test
	void testDeepEquals_differentArgs() {
		ScenarioCommand cmdA = testScenario.createNewCommand(EnumPossibleCommands.PAUSE, new Object[] { 1 });
		ScenarioCommand cmdB = testScenario.createNewCommand(EnumPossibleCommands.PAUSE, new Object[] { 2 });

		assertFalse(cmdA.deepEquals(cmdB));
	}

	@Test
	void testDeepEqual_difObjAllCommands() {
		Scenario sc = new Scenario(2, 2);

		for (EnumPossibleCommands cmd : EnumPossibleCommands.values()) {

			Class<?>[] argTypes = cmd.getArgumentTypes();

			if (argTypes.length == 0) {
				continue;
			}
			Object[] args = new Object[argTypes.length];
			Object[] args_dif = new Object[argTypes.length];

			// set correct and same arguments
			for (int i = 0; i < args.length; i++) {
				if (argTypes[i].equals(Integer.class)) {
					args[i] = 1;
					args_dif[i] = 2;
				} else if (argTypes[i].equals(String.class)) {
					args[i] = correctTestStringSet[0];
					args_dif[i] = correctTestStringSet[1];
				} else if (argTypes[i].equals(Character.class)) {
					args[i] = correctTestCharSet[0];
					args_dif[i] = correctTestCharSet[1];
				}
			}

			ScenarioCommand cmdA = sc.createNewCommand(cmd, args);
			ScenarioCommand cmdB = sc.createNewCommand(cmd, args_dif);

			assertFalse(cmdA.deepEquals(cmdB), "Failed for: " + cmd.getName());
			assertFalse(cmdB.deepEquals(cmdA), "Failed for: " + cmd.getName());
		}
	}

	/**
	 * Fundamental check for null arguments for arbitrary (no argument) END_REPEAT
	 * Command
	 * 
	 * Should be empty array
	 */
	@Test
	void testSetArguments_nullArgs() {
		ScenarioCommand testCommand = testScenario.createNewCommand(EnumPossibleCommands.END_REPEAT, null);

		Object[] actual = testCommand.getArguments();
		Object[] exp = new Object[0];

		assertEquals(exp.length, actual.length, "Should be empty array");
	}

	/**
	 * fundamental check for IllegalArgumentException for wrong amount of arguments
	 * for arbitrary READ_TEXT Command
	 */
	@Test
	void testSetArguments_throwsIllegalArgumentExceptionWrongAmount() {
		ScenarioCommand testCommand = testScenario.createNewCommand(EnumPossibleCommands.READ_TEXT,
				new Object[] { "Test" });

		assertThrows(IllegalArgumentException.class, () -> {
			testCommand.setArguments(new Object[] { 1 });
		});
	}

	/**
	 * fundamental check for IllegalArgumentException for wrong type of arguments
	 * for arbitrary PAUSE Command
	 */
	@Test
	void testSetArguments_throwsIllegalArgumentExceptionWrongType() {
		ScenarioCommand testCommand = testScenario.createNewCommand(EnumPossibleCommands.PAUSE, new Object[] { 1 });

		assertThrows(IllegalArgumentException.class, () -> {
			testCommand.setArguments(new Object[] { "foo" });
		});

	}

	/**
	 * wrong argument amounts for each command
	 */
	@Test
	void testSetArguments_allCommandsWrongArgumentAmount() {
		ScenarioCommand testCommand;

		for (EnumPossibleCommands cmd : EnumPossibleCommands.values()) {
			Object[] args = new Object[cmd.getArgumentTypes().length + 1];

			try {
				testCommand = testScenario.createNewCommand(cmd, args);
				fail("No Exception thrown for: " + cmd.getName());
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	/**
	 * null argument types for each command
	 */
	@Test
	void testSetArguments_allCommandsWrongArgumentType() {
		ScenarioCommand testCommand;

		for (EnumPossibleCommands cmd : EnumPossibleCommands.values()) {
			// Skip commands with no arguments
			if (cmd.getArgumentTypes().length == 0) {
				continue;
			}
			Object[] args = new Object[cmd.getArgumentTypes().length];

			try {
				testCommand = testScenario.createNewCommand(cmd, args);
				fail("No Exception thrown for: " + cmd.getName());
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	/**
	 * Fundamental structure check for proper command -- arbitrary command:
	 * READ_TEXT
	 */
	@Test
	void testSetArguments_correctArgsReadText() {
		try {
			ScenarioCommand testCommand = testScenario.createNewCommand(EnumPossibleCommands.READ_TEXT,
					new Object[] { "Test" });
		} catch (Exception e) {
			e.printStackTrace();
			fail("No exceptions should be thrown!");
		}
	}

	/**
	 * check proper arguments for each command
	 */
	@Test
	void testValidateArguments_correctArgsAllCommands() {
		Scenario sc = new Scenario(2, 2);
		correctTestInt = 2;

		for (EnumPossibleCommands cmd : EnumPossibleCommands.values()) {
			Class<?>[] argTypes = cmd.getArgumentTypes();
			Object[] args = new Object[argTypes.length];

			// set correct and same arguments
			for (int i = 0; i < args.length; i++) {
				if (argTypes[i].equals(Integer.class)) {
					args[i] = correctTestInt;
				} else if (argTypes[i].equals(String.class)) {
					args[i] = correctTestStringSet[0];
				} else if (argTypes[i].equals(Character.class)) {
					args[i] = correctTestCharSet[0];
				}
			}

			try {
				ScenarioCommand cmdA = sc.createNewCommand(cmd, args);
			} catch (Exception e) {
				e.printStackTrace();
				fail("Exception should not be thrown!");
			}
		}
	}

	@Test
	@RepeatedTest(10)
	void testSetArguments_wrongArgsAllCommands() {
		Scenario sc = new Scenario(2, 2);
		incorrectTestIntSet = new int[] { 0 };

		for (EnumPossibleCommands cmd : EnumPossibleCommands.values()) {

			Class<?>[] argTypes = cmd.getArgumentTypes();

			if (argTypes.length == 0)// skip no argument commands
			{
				continue;
			}
			Object[] args = new Object[argTypes.length];

			// set correct and same arguments
			for (int i = 0; i < args.length; i++) {
				if (argTypes[i].equals(Integer.class)) {
					args[i] = incorrectTestIntSet[rand.nextInt(incorrectTestIntSet.length)];
				} else if (argTypes[i].equals(String.class)) {
					args[i] = incorrectTestStringSet[rand.nextInt(incorrectTestStringSet.length)];
				} else if (argTypes[i].equals(Character.class)) {
					args[i] = incorrectTestCharSet[rand.nextInt(incorrectTestCharSet.length)];
				}
			}

			assertThrows(IllegalArgumentException.class, () -> {
				sc.createNewCommand(cmd, args);
			});

		}

	}

}
