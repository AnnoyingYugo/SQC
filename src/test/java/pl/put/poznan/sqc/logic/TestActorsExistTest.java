package pl.put.poznan.sqc.logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.json.JsonContentAssert;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class TestActorsExistTest {
    private ActorsExistTest actorsExistTest;
    private SQC.ScenarioBody scenarioBody;

    @BeforeEach
    void setUp() {
        actorsExistTest = new ActorsExistTest();
        scenarioBody = new SQC.ScenarioBody();

        scenarioBody.actors = new String[]{"Bibliotekarz", "Uzytkownik"};
        scenarioBody.system = new String[]{"System"};
        scenarioBody.scenarios = new SQC.ScenarioDescription[]{
                new SQC.ScenarioDescription(0, "Bibliotekarz wybiera opcje dodania nowej pozycji książkowej"),
                new SQC.ScenarioDescription(0, "Wyświetla się formularz."),
                new SQC.ScenarioDescription(0, "Bibliotekarz podaje dane książki."),
                new SQC.ScenarioDescription(1, "IF: Bibliotekarz pragnie dodać egzemplarze książki"),
                new SQC.ScenarioDescription(1, "Bibliotekarz wybiera opcję definiowania egzemplarzy"),
                new SQC.ScenarioDescription(1, "System prezentuje zdefiniowane egzemplarze"),
                new SQC.ScenarioDescription(1, "FOR EACH egzemplarz:"),
                new SQC.ScenarioDescription(2, "Bibliotekarz wybiera opcję dodania egzemplarza"),
                new SQC.ScenarioDescription(2, "System prosi o podanie danych egzemplarza"),
                new SQC.ScenarioDescription(2, "Bibliotekarz podaje dane egzemplarza i zatwierdza."),
                new SQC.ScenarioDescription(2, "System informuje o poprawnym dodaniu egzemplarza i prezentuje zaktualizowaną listę egzemplarzy."),
                new SQC.ScenarioDescription(0, "Bibliotekarz zatwierdza dodanie książki."),
                new SQC.ScenarioDescription(0, "System informuje o poprawnym dodaniu książki."),
                new SQC.ScenarioDescription(0, "")
        };
    }

    @Test
    void test_actor_stepStartsWithActor(){
        actorsExistTest.visit(scenarioBody);
        assertTrue(actorsExistTest.stepStartsWithActor(scenarioBody.scenarios[0]));
    }

    @Test
    void test_system_stepStartsWithActor(){
        actorsExistTest.visit(scenarioBody);
        assertTrue(actorsExistTest.stepStartsWithActor(scenarioBody.scenarios[5]));
    }

    @Test
    void test_none_stepStartsWithActor(){
        actorsExistTest.visit(scenarioBody);
        assertFalse(actorsExistTest.stepStartsWithActor(scenarioBody.scenarios[1]));
    }

    @Test
    void test_null_stepStartsWithActor(){
        actorsExistTest.visit(scenarioBody);
        assertFalse(actorsExistTest.stepStartsWithActor(scenarioBody.scenarios[13]));
    }

    @Test
    void test_all_stepsWithoutActors() {
        actorsExistTest.visit(scenarioBody);

        List<String> steps = actorsExistTest.stepsWithoutActors();
        assertEquals(4, steps.size());
        assertTrue(steps.contains("Wyświetla się formularz."));
        assertTrue(steps.contains("IF: Bibliotekarz pragnie dodać egzemplarze książki"));
        assertTrue(steps.contains("FOR EACH egzemplarz:"));
        assertTrue(steps.contains(""));
    }

}