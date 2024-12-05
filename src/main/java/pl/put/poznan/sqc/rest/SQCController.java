package pl.put.poznan.sqc.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import pl.put.poznan.sqc.logic.SQC;
import pl.put.poznan.sqc.logic.SQC.ScenarioBody;
import pl.put.poznan.sqc.logic.ScenarioFormatter;
import pl.put.poznan.sqc.logic.ActorsExistTest;
import pl.put.poznan.sqc.logic.KeyWordCounter;
import pl.put.poznan.sqc.logic.NumberOfSteps;

import java.util.Arrays;

@RestController
@RequestMapping("/api")
public class SQCController {

    private static final Logger logger = LoggerFactory.getLogger(SQCController.class);

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public String get(@RequestParam(value = "title", defaultValue = "Default Title") String title,
            @RequestParam(value = "actors", defaultValue = "[]") String[] actors,
            @RequestParam(value = "system", defaultValue = "[]") String[] system,
            @RequestParam(value = "scenarios", defaultValue = "[]") String scenarios) {

        // log
        logger.debug("GET /api");
        logger.debug(Arrays.toString(actors));

        // split and read scenarios
        SQC.ScenarioDescription[] scenarioDescriptions = SQC.readScenarioDescriptions(scenarios);

        // create the SQC object
        SQC sqc = new SQC(title, actors, system, scenarioDescriptions);

        // return the result
        return sqc.toPrint();
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public String post(
            @RequestBody ScenarioBody scenario) {

        // log
        logger.debug("POST /api");

        // create the SQC object
        SQC sqc = new SQC(scenario);

        // return the result
        return sqc.toPrint();
    }

    @RequestMapping(value = "/countsteps",method = RequestMethod.POST, produces = "application/json" )
    public String countScenarioSteps(
            @RequestBody ScenarioBody scenario) {

        // log
        logger.debug("POST /api/countsteps");

        // get information about steps
        NumberOfSteps numberOfSteps = new NumberOfSteps(scenario.scenarios);

        // return the result
        return numberOfSteps.getInfo();
    }

    @RequestMapping(value = "/testactors",method = RequestMethod.POST, produces = "application/json" )
    public String testIfActorExists(
            @RequestBody ScenarioBody scenario) {

        // log
        logger.debug("POST /api/testactors");

        // get information about steps
        ActorsExistTest actorsExistTest = new ActorsExistTest(scenario.scenarios, scenario.actors);

        // return the result
        return actorsExistTest.getInfo();
    }

    @RequestMapping(value = "/format",method = RequestMethod.POST, produces = "application/json")
    public String formatScenario(
            @RequestBody ScenarioBody scenario) {

        // log
        logger.debug("POST /api/format");

        // get information about steps
        ScenarioFormatter scenarioFormatter = new pl.put.poznan.sqc.logic.ScenarioFormatter(scenario);

        // return the result
        return scenarioFormatter.getInfo();
    }

    @RequestMapping(value = "/countkeyword",method = RequestMethod.POST, produces = "application/json" )
    public int countKeywordSteps(
            @RequestBody ScenarioBody scenario) {

        // log
        logger.debug("POST /api/countkeyword");

        // get information about steps
        KeyWordCounter keyWordCounter = new KeyWordCounter(scenario.scenarios);

        // return the result
        return keyWordCounter.getInfo();
    }

}
