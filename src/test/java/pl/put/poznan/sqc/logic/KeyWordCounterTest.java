package pl.put.poznan.sqc.logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * This class tests KeyWordCounter's class functionality using JUnit library.
 *
 * @author Aleksandra Ostrowska
 * @since 6.0
 */
class KeyWordCounterTest {
    private KeyWordCounter keyWordCounter;
    private SQC.ScenarioBody scenarioBody;

    private SQC.ScenarioDescription stepIf;
    private SQC.ScenarioDescription stepElse;
    private SQC.ScenarioDescription stepForEach;
    private SQC.ScenarioDescription stepFor;
    private SQC.ScenarioDescription stepNoKeyword;
    private SQC.ScenarioDescription stepNull;

    @BeforeEach
    void setUp() {
        keyWordCounter = new KeyWordCounter();

        stepIf = new SQC.ScenarioDescription(1, "IF sth happens");
        stepElse = new SQC.ScenarioDescription(1, "ELSE do sth");
        stepForEach = new SQC.ScenarioDescription(2, "FOR EACH word");
        stepFor = new SQC.ScenarioDescription(3, "FOR 3 times in row");
        stepNoKeyword = new SQC.ScenarioDescription(4, "Actor writes book");
        stepNull = new SQC.ScenarioDescription(5, "");

        scenarioBody = new SQC.ScenarioBody();
        scenarioBody.scenarios = new SQC.ScenarioDescription[]{
                stepIf, stepElse, stepForEach, stepFor, stepNoKeyword, stepNull
        };
    }

    @Test
    void test_visit_getInfo(){
        keyWordCounter.visit(scenarioBody);

        Integer info = keyWordCounter.getInfo();

        assertEquals(3, info);
    }

    @Test
    void test_visit_stepsWithKeyword(){
        keyWordCounter.visit(scenarioBody);

        int result = keyWordCounter.stepsWithKeyword();

        assertEquals(3, result);
    }

    @Test
    void test_if_stepStartsWithKeyword(){
        assertTrue(keyWordCounter.stepStartsWithKeyword(stepIf));
    }

    @Test
    void test_else_stepStartsWithKeyword(){
        assertTrue(keyWordCounter.stepStartsWithKeyword(stepElse));
    }

    @Test
    void test_foreach_stepStartsWithKeyword(){
        assertTrue(keyWordCounter.stepStartsWithKeyword(stepForEach));
    }

    @Test
    void test_for_stepStartsWithKeyword(){
        assertFalse(keyWordCounter.stepStartsWithKeyword(stepFor));
    }

    @Test
    void test_noKeyword_stepStartsWithKeyword(){
        assertFalse(keyWordCounter.stepStartsWithKeyword(stepNoKeyword));
    }

    @Test
    void test_null_stepStartsWithKeyword(){
        assertFalse(keyWordCounter.stepStartsWithKeyword(stepNull));
    }

}