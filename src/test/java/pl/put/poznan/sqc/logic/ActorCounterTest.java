package pl.put.poznan.sqc.logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pl.put.poznan.sqc.logic.SQC.ScenarioBody;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * This class tests ActorCounter's class functionality using JUnit library.
 *
 * @author Aleksandra Ostrowska
 * @since 7.0
 */
class ActorCounterTest {
    private ActorCounter actorCounter;
    private SQC.ScenarioBody scenarioBody;

    @BeforeEach
    void setUp() {
        actorCounter = new ActorCounter();
        scenarioBody = mock(ScenarioBody.class);

        when(scenarioBody.getActors()).thenReturn(new String[]{"Bibliotekarz", "Uzytkownik"});
        when(scenarioBody.getSystem()).thenReturn(new String[]{"System"});
        when(scenarioBody.getScenarios()).thenReturn(new SQC.ScenarioDescription[]{
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
        });
    }

    @Test
    void test_visit_getInfo_actor_null(){
        actorCounter.visit(scenarioBody);
        actorCounter.setActorNames("");

        Integer info = actorCounter.getInfo();

        assertNull(info);
    }

    @Test
    void test_visit_getInfo(){
        actorCounter.visit(scenarioBody);
        actorCounter.setActorNames("Bibliotekarz");

        Integer info = actorCounter.getInfo();

        assertEquals(7, info);
    }

    @Test
    void test_visit_getInfo_actor_zero(){
        actorCounter.visit(scenarioBody);
        actorCounter.setActorNames("Sklepikarz");

        Integer info = actorCounter.getInfo();

        assertEquals(0, info);
    }

    @Test
    void test_visit_count_Bibliotekarz(){
        actorCounter.visit(scenarioBody);

        int result = actorCounter.countActor("Bibliotekarz");

        assertEquals(7, result);
    }

    @Test
    void test_visit_count_zero(){
        actorCounter.visit(scenarioBody);

        int result = actorCounter.countActor("Sklepikarz");

        assertEquals(0, result);
    }


}