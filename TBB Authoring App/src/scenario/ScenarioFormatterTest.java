package scenario;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ScenarioFormatterTest {

	@Test
	void testExport() {
		fail("Not yet implemented");
	}

	@Test
	void testImportParse_NO_COMMANDS() {
		Scenario expScenario = new Scenario(1,1);
		Scenario actualScenario = ScenarioFormatter.importParse("scenarios\\importTestScenario_01");
		
		assertEquals(expScenario, actualScenario);
	}

}
