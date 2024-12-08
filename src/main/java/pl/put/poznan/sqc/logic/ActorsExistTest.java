package pl.put.poznan.sqc.logic;

import java.util.ArrayList;
import java.util.List;

import pl.put.poznan.sqc.logic.SQC.ScenarioBody;
import pl.put.poznan.sqc.logic.SQC.ScenarioDescription;

// generate javadoc
/**
 * This class is a visitor that checks if each step in the scenario starts with an actor.
 * 
 * @author Krzysztof Garsztka, Paviel Mamchur
 * @since 3.0
 */
public class ActorsExistTest implements Visitor {
    private ScenarioDescription[] scenarios;
    private String[] actors;
    private String[] system;

    /**
     * This method is called when the visitor visits the ScenarioBody object.
     * 
     * @param scenarioBody the ScenarioBody object that the visitor visits
     */
    @Override
    public void visit(ScenarioBody scenarioBody) {
        this.scenarios = scenarioBody.scenarios;
        this.actors = scenarioBody.actors;
        this.system = scenarioBody.system;
    }

    /**
     * This method checks if the step starts with an actor.
     * 
     * @param scenario the scenario that the visitor visits
     * @return true if the step starts with an actor, false otherwise
     */
    public boolean stepStartsWithActor(ScenarioDescription scenario) {
        if (actors == null) {
            return false;
        }
        if (scenario.content.length() == 0) {
            return false;
        }
        // check if the first word of the step is an actor
        for (String actor : actors) {
            String[] content = scenario.content.split("\\s+");
            String firstWord = content[0];

            if (firstWord.contains(actor)) {
                return true;
            }
        }

        // check if the first word of the step is a system
        for (String sys : system) {
            String[] content = scenario.content.split("\\s+");
            String firstWord = content[0];

            if (firstWord.contains(sys)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method returns a list of steps that do not start with any actor.
     * 
     * @return a list of steps that do not start with any actor
     */
    public List<String> stepsWithoutActors(){
        List<String> stepDescriptions = new ArrayList<String>();
        for(int i = 0; i < scenarios.length; i++){
            if (!stepStartsWithActor(scenarios[i])){
                stepDescriptions.add(scenarios[i].content);
            }
        }
        return stepDescriptions;
    }

    /**
     * This method returns a string with information about steps that do not start with any actor.
     * 
     * @return a string with information about steps that do not start with any actor
     */
    public String getInfo() {
        List<String> result = stepsWithoutActors();
        return "These steps do not start with any actor: \n" + String.join(",\n", result);
    }
}
