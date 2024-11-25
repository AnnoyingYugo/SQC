package pl.put.poznan.sqc.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import pl.put.poznan.sqc.logic.SQC;
import pl.put.poznan.sqc.logic.SQC.ScenarioBody;
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

}
