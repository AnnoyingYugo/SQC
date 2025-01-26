package pl.put.poznan.sqc.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import pl.put.poznan.sqc.logic.*;
import pl.put.poznan.sqc.logic.SQC.ScenarioBody;

import java.util.Arrays;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * This is the controller class for the REST service.
 * 
 * @author Filip Batóg, Krzysztof Garsztka, Aleksandra Ostrowska, Paviel Mamchur
 * @version 5.0
 */
@RestController
@RequestMapping("/api")
public class SQCController {

    private static final Logger logger = LoggerFactory.getLogger(SQCController.class);

    /**
     * This method is used to get the SQC object.
     * 
     * @param title
     * @param actors
     * @param system
     * @param scenarios
     * @return SQC
     */
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public String get(@RequestParam(value = "title", defaultValue = "Default Title") String title,
            @RequestParam(value = "actors", defaultValue = "[]") String[] actors,
            @RequestParam(value = "system", defaultValue = "[]") String[] system,
            @RequestParam(value = "scenarios", defaultValue = "[]") String scenarios) {

        // log
        logger.debug("GET /api");
        logger.debug(Arrays.toString(actors));

        logger.info("[GET /api] with title: " + title);

        // split and read scenarios
        SQC.ScenarioDescription[] scenarioDescriptions = SQC.readScenarioDescriptions(scenarios);
        logger.info("[GET /api] read scenario descriptions");

        // create the SQC object
        SQC sqc = new SQC(title, actors, system, scenarioDescriptions);
        logger.info("[GET /api] created SQC object");

        // return the result
        return sqc.toPrint();
    }

    /**
     * This method is used to post the SQC object.
     * 
     * @param scenario
     * @return SQC
     */
    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public String post(
            @RequestBody ScenarioBody scenario) {

        // log
        logger.debug("POST /api");

        logger.info("[POST /api] with title: " + scenario.title);

        // create the SQC object
        SQC sqc = new SQC(scenario);
        logger.info("[POST /api] created SQC object");

        // return the result
        return sqc.toPrint();
    }

    /**
     * This method is used to count the number of steps in the scenario.
     * 
     * @param scenario
     * @return String
     */
    @RequestMapping(value = "/countsteps", method = RequestMethod.POST, produces = "application/json")
    public String countScenarioSteps(
            @RequestBody ScenarioBody scenario) {

        // log
        logger.debug("POST /api/countsteps");

        logger.info("[POST /api/countsteps] with title: " + scenario.title);

        // get information about steps
        Visitor numberOfSteps = new NumberOfSteps();
        scenario.accept(numberOfSteps);
        logger.info("[POST /api/countsteps] counted steps");

        // return the result
        return (String) numberOfSteps.getInfo();
    }

    /**
     * This method is used to test if the actors exist in the scenario.
     * 
     * @param scenario
     * @return String
     */
    @RequestMapping(value = "/testactors", method = RequestMethod.POST, produces = "application/json")
    public String testIfActorExists(
            @RequestBody ScenarioBody scenario) {

        // log
        logger.debug("POST /api/testactors");

        logger.info("[POST /api/testactors] with title: " + scenario.title);

        // get information about actors
        Visitor actorsExistTest = new ActorsExistTest();
        scenario.accept(actorsExistTest);
        logger.info("[POST /api/testactors] tested actors");

        // return the result
        return (String) actorsExistTest.getInfo();
    }

    /**
     * This method is used to format the scenario.
     * 
     * @param scenario
     * @return String
     */
    @RequestMapping(value = "/format", method = RequestMethod.POST, produces = "application/json")
    public String formatScenario(
            @RequestBody ScenarioBody scenario) {

        // log
        logger.debug("POST /api/format");

        logger.info("[POST /api/format] with title: " + scenario.title);

        // operate request
        Visitor scenarioFormatter = new pl.put.poznan.sqc.logic.ScenarioFormatter();
        scenario.accept(scenarioFormatter);
        logger.info("[POST /api/format] formatted scenario");

        // return the result
        return (String) scenarioFormatter.getInfo();
    }

    /**
     * This method is used to count the number of keywords in the scenario.
     * 
     * @param scenario
     * @return Integer
     */
    @RequestMapping(value = "/countkeyword", method = RequestMethod.POST, produces = "application/json")
    public Integer countKeywordSteps(
            @RequestBody ScenarioBody scenario) {

        // log
        logger.debug("POST /api/countkeyword");

        logger.info("[POST /api/countkeyword] with title: " + scenario.title);

        // get information about keywords
        Visitor keyWordCounter = new KeyWordCounter();
        scenario.accept(keyWordCounter);
        logger.info("[POST /api/countkeyword] counted keyword steps");

        // return the result
        return (Integer) keyWordCounter.getInfo();
    }

    /**
     * This method is used to swap actor with another one in the scenario.
     *
     * @param scenario
     * @param oldName the name to be replaced.
     * @param newName the name to replace with.
     * @return String
     */
    @RequestMapping(value = "/swapactor", method = RequestMethod.POST, produces = "application/json")
    public String swapActor(
            @RequestBody ScenarioBody scenario, @RequestParam String oldName, @RequestParam String newName) {

        // log
        logger.debug("POST /api/swapactor");

        logger.info("[POST /api/swapactor] with title: " + scenario.title);

        // operate request
        Visitor actorSwapper = new ActorSwapper();
        scenario.accept(actorSwapper);
        ((ActorSwapper) actorSwapper).setActorNames(oldName, newName);
        logger.info("[POST /api/swapactor] swapped actors " + oldName + " with " + newName);

        // return the result
        return (String) actorSwapper.getInfo();
    }

    /**
     * This method is used to count the number of times actor appears in the scenario.
     *
     * @param scenario
     * @param actorName the name of actor to be counted.
     * @return Integer
     */
    @RequestMapping(value = "/countactor", method = RequestMethod.POST, produces = "application/json")
    public Integer countActor(
            @RequestBody ScenarioBody scenario, @RequestParam String actorName) {

        // log
        logger.debug("POST /api/countactor");

        logger.info("[POST /api/countactor] with title: " + scenario.title);

        // get information about actor
        Visitor actorCounter = new ActorCounter();
        scenario.accept(actorCounter);
        ((ActorCounter) actorCounter).setActorNames(actorName);
        logger.info("[POST /api/countactor] counted actor " + actorName);

        // return the result
        return (Integer) actorCounter.getInfo();
    }

    @RequestMapping(value = "/validate", method=RequestMethod.POST, produces = "application/json")
    public String requestMethodName(@RequestBody ScenarioBody scenario) {
        // log
        logger.debug("POST /api/validate");

        logger.info("[POST /api/validate] with title: " + scenario.title);

        // get information about actors
        Visitor scenarioValidator = new ScenarioValidator();
        scenario.accept(scenarioValidator);
        logger.info("[POST /api/validate] validated scenario");

        // return the result
        return (String) scenarioValidator.getInfo();
    }
}
