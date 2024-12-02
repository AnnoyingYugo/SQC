package pl.put.poznan.sqc.logic;

import java.util.ArrayList;
import java.util.List;

import pl.put.poznan.sqc.logic.SQC.ScenarioDescription;

public class ActorsExistTest {
    private ScenarioDescription[] scenarios;
    private String[] actors;

    public ActorsExistTest(ScenarioDescription[] scenarios, String[] actors) {
        this.scenarios = scenarios;
        this.actors = actors;
    }

    public boolean stepStartsWithActor(ScenarioDescription scenario) {
        if (actors == null) {
            return false;
        }
        if (scenario.content.length() == 0) {
            return false;
        }
        for (String actor : actors) {
            String[] content = scenario.content.split("\\s+");
            String firstWord = content[0];

            if (firstWord.contains("IF") || firstWord.contains("ELSE")) {
                firstWord = content[1];
            } else if (firstWord.contains("FOR")) {
                return true;
            }

            if (firstWord.contains(actor)) {
                return true;
            }
        }
        return false;
    }

    public List<String> stepsWithoutActors(){
        List<String> stepDescriptions = new ArrayList<String>();
        for(int i = 0; i < scenarios.length; i++){
            if (!stepStartsWithActor(scenarios[i])){
                stepDescriptions.add(scenarios[i].content);
            }
        }
        return stepDescriptions;
    }

    public String getInfo() {
        List<String> result = stepsWithoutActors();
        return "These steps do not start with any actor: \n" + String.join(",\n", result);
    }
}
