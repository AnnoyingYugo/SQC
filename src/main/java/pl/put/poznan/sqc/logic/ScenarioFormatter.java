package pl.put.poznan.sqc.logic;

import java.util.HashMap;
import java.util.Map;

import pl.put.poznan.sqc.logic.SQC.ScenarioBody;
import pl.put.poznan.sqc.logic.SQC.ScenarioDescription;

public class ScenarioFormatter {
    private ScenarioBody scenarioBody;

    public ScenarioFormatter(ScenarioBody scenarioBody) {
        this.scenarioBody = scenarioBody;
    }

    public String formatScenario() {
        String formattedScenario = "";

        formattedScenario += "Title: " + scenarioBody.title + "\n";
        formattedScenario += "Actors: " + String.join(", ", scenarioBody.actors) + "\n";
        formattedScenario += "System: " + String.join(", ", scenarioBody.system) + "\n";

        Map<Integer, Integer> depthMap = new HashMap<>();
        depthMap.put(0, 0);
        int previousDepth = 0;
        for (ScenarioDescription scenario : scenarioBody.scenarios) {
            formattedScenario += enumerate(scenario, depthMap, previousDepth);
        }

        return formattedScenario;
    }

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

    public String getInfo() {
        return formatScenario();
    }
}
