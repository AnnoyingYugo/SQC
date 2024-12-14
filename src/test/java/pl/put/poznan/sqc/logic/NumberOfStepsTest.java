package pl.put.poznan.sqc.logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class tests NumberOfSteps's class functionality using JUnit library.
 *
 * @author Aleksandra Ostrowska
 * @since 6.0
 */
class NumberOfStepsTest {
    private NumberOfSteps numberOfSteps;
    private SQC.ScenarioBody scenarioBody;

    private SQC.ScenarioDescription stepIf;
    private SQC.ScenarioDescription stepElse;
    private SQC.ScenarioDescription stepForEach;
    private SQC.ScenarioDescription stepFor;
    private SQC.ScenarioDescription stepNoKeyword;
    private SQC.ScenarioDescription stepNull;

    @BeforeEach
    void setUp() {
        numberOfSteps = new NumberOfSteps();

        stepIf = new SQC.ScenarioDescription(1, "IF sth happens");
        stepElse = new SQC.ScenarioDescription(1, "ELSE do sth");
        stepForEach = new SQC.ScenarioDescription(2, "FOR EACH word");
        stepFor = new SQC.ScenarioDescription(3, "FOR 3 times in row");
        stepNoKeyword = new SQC.ScenarioDescription(4, "Actor writes book");
        stepNull = new SQC.ScenarioDescription(5, "  ");

        scenarioBody = new SQC.ScenarioBody();
        scenarioBody.scenarios = new SQC.ScenarioDescription[]{
                stepIf, stepElse, stepForEach, stepFor, stepNoKeyword, stepNull
        };
    }

    @Test
    void test_visit_getInfo() {
        numberOfSteps.visit(scenarioBody);

        String info = numberOfSteps.getInfo();

        assertEquals("Number of steps in the scenario: 5", info);
    }

    @Test
    void test_visit_countSteps() {
        numberOfSteps.visit(scenarioBody);

        int count = numberOfSteps.countSteps();

        assertEquals(5, count);
    }
}