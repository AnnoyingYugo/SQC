package pl.put.poznan.sqc.logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import pl.put.poznan.sqc.logic.SQC.ScenarioBody;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class tests ActorSwapper's class functionality using JUnit library.
 *
 * @author Aleksandra Ostrowska
 * @since 7.0
 */
class ActorSwapperTest {
    private ActorSwapper actorSwapper;
    private SQC.ScenarioBody scenarioBody;

    @Mock
    private ActorCounter actorCounter;

    @BeforeEach
    void setUp() {
        actorSwapper = new ActorSwapper();
        scenarioBody =mock(ScenarioBody.class);

        actorCounter = mock(ActorCounter.class);

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
    void test_visit_getInfo_actor1_null(){
        actorSwapper.visit(scenarioBody);
        actorSwapper.setActorNames("", "Sklepikarz");

        String info = actorSwapper.getInfo();
        assertNull(info);
    }

    @Test
    void test_visit_getInfo_actor2_null(){
        actorSwapper.visit(scenarioBody);
        actorSwapper.setActorNames( "Bibliotekarz", "");

        String info = actorSwapper.getInfo();
        assertNull(info);
    }

    @Test
    void test_visit_getInfo_actors_equal(){
        actorSwapper.visit(scenarioBody);
        actorSwapper.setActorNames( "Bibliotekarz", "Bibliotekarz");

        String info = actorSwapper.getInfo();
        assertNull(info);
    }

    @Test
    void test_visit_getInfo_invalid_actor_null(){
        actorSwapper.visit(scenarioBody);
        actorSwapper.setActorNames( "Sklepikarz", "Sklepikarz1");

        String info = actorSwapper.getInfo();
        assertNull(info);
    }



}