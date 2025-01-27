package pl.put.poznan.sqc.logic;

import java.util.HashMap;
import java.util.Map;

import pl.put.poznan.sqc.logic.SQC.ScenarioBody;
import pl.put.poznan.sqc.logic.SQC.ScenarioDescription;

/**
 * This class is a visitor that formats the scenario.
 * 
 * @author Paviel Mamchur
 * @since 4.0
 */
public class ScenarioFormatter implements Visitor {
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

    /**
     * This method formats the scenario.
     * 
     * @return the formatted scenario
     */
    public String formatScenario() {
        String formattedScenario = "";
        var scenarios = scenarioBody.getScenarios();

        formattedScenario += "Title: " + scenarioBody.getTitle() + "\n";
        formattedScenario += "Actors: " + String.join(", ", scenarioBody.getActors()) + "\n";
        formattedScenario += "System: " + String.join(", ", scenarioBody.getSystem()) + "\n";

        Map<Integer, Integer> depthMap = new HashMap<>();
        depthMap.put(0, 0);
        int previousDepth = 0;
        for (ScenarioDescription scenario : scenarios) {
            formattedScenario += enumerate(scenario, depthMap, previousDepth);
        }

        return formattedScenario;
    }

    /**
     * This method enumerates the scenario.
     * 
     * @param scenario      the scenario to enumerate
     * @param depthMap      the map of depths
     * @param previousDepth the previous depth
     * @return the enumerated scenario
     */
    public String enumerate(ScenarioDescription scenario, Map<Integer, Integer> depthMap, int previousDepth) {
        String enumeration = "";

        int depth = 0;

        if (previousDepth > scenario.depth) {
            for (int i = previousDepth; i <= scenario.depth; i++) {
                depthMap.put(i, 0);
            }
        }

        while (depth <= scenario.depth) {
            if (depthMap.containsKey(depth)) {
                if (depth == scenario.depth) {
                    depthMap.put(depth, depthMap.get(depth) + 1);
                }
            } else {
                depthMap.put(depth, 1);
            }
            depth++;
        }

        enumeration += " ".repeat(scenario.depth * 2);
        for (int i = 0; i <= scenario.depth; i++) {
            int value = depthMap.get(i);
            if (i % 2 == 0)
                enumeration += value + ".";
            else
                enumeration += (char) (value - 1 + 'A') + ".";
        }

        enumeration += " " + scenario.content + "\n";
        previousDepth = scenario.depth;

        return enumeration;
    }

    /**
     * This method returns the information about the formatted scenario.
     * 
     * @return the information about the formatted scenario
     */
    public String getInfo() {
        return formatScenario();
    }
}
