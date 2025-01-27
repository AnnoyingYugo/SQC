package pl.put.poznan.sqc.logic;

import pl.put.poznan.sqc.logic.SQC.ScenarioBody;
import pl.put.poznan.sqc.logic.SQC.ScenarioDescription;

/**
 * This class is a visitor that formats the scenario.
 * 
 * @author Krzysztof Garsztka, Filip Batóg
 * @since 8.0
 */
public class ScenarioValidator implements Visitor {
    private ScenarioBody scenarioBody;

    /**
     * This method visits the ScenarioBody and sets the scenarioBody to the
     * ScenarioBody.
     * 
     * @param scenarioBody the ScenarioBody to visit
     */
    @Override
    public void visit(ScenarioBody scenarioBody) {
        this.scenarioBody = scenarioBody;
    }

    private boolean stepStartsWithKeyword(ScenarioDescription scenario) {
        if (scenario.content.length() == 0) {
            return false;
        }
        String[] content = scenario.content.split("\\s+");
        String firstWord = content[0];

        if (firstWord.contains("IF") || firstWord.contains("ELSE")) {
            return true;
        } else if (firstWord.contains("FOR")) {
            try {
                String secondWord = content[1];
                if (secondWord.contains("EACH")) {
                    return true;
                }
            } catch (Throwable e) {
                return false;
            }
        }
        return false;
    }

    private String validateScenario() {
        String enumaration = "";
        var scenarios = scenarioBody.getScenarios();
        if (scenarios.length < 1) {
            return "#NoScenario";
        }
        for (int i = 0; i < scenarios.length - 2; i++) {
            // add to return the line
            enumaration += scenarios[i].content;
            enumaration += "\n";
            if (stepStartsWithKeyword(scenarios[i])) {
                if (scenarios[i].depth != scenarios[i + 1].depth - 1) {
                    enumaration += "#KeyWordBroken";
                    enumaration += "\n";

                }
            }
        }
        enumaration += scenarios[scenarios.length - 1].content;
        enumaration += "\n";
        if (stepStartsWithKeyword(scenarios[scenarios.length - 1])) {
            enumaration += "#KeyWordBroken";
            enumaration += "\n";
        }
        return enumaration;
    }

    /**
     * This method calls validateScenario
     *
     * @return String "True" when scenario Validation passed, "False" when it
     *         didn't.
     */
    @Override
    public String getInfo() {
        return validateScenario();
    }
}
