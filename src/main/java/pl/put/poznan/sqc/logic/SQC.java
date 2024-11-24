package pl.put.poznan.sqc.logic;

/**
 * This is just an example to show that the logic should be outside the REST
 * service.
 */
public class SQC {
    public static class ScenarioBody {
        public String title;
        public String[] actors;
        public String[] system;
        public ScenarioDescription[] scenarios;
    }

    public static class ScenarioDescription {
        public String content;
        public Integer depth;
    }

    private ScenarioBody scenario;

    public SQC(ScenarioBody scenario) {
        this.scenario = scenario;
    }

    public SQC(String title, String[] actors, String[] system, String[] scenarios) {
        this.scenario = new ScenarioBody();
        this.scenario.title = title;
        this.scenario.actors = actors;
        this.scenario.system = system;
        this.scenario.scenarios = new ScenarioDescription[scenarios.length];
        for (int i = 0; i < scenarios.length; i++) {
            this.scenario.scenarios[i] = new ScenarioDescription();
            this.scenario.scenarios[i].content = scenarios[i];
            this.scenario.scenarios[i].depth = 0;
        }
    }

    public String toPrint() {
        String str = "Scenario: " + scenario.title + "\n" + "Actors: " + String.join(", ", scenario.actors) + "\n"
                + "System: " + String.join(", ", scenario.system) + "\n" + "Scenarios:\n";
        for (ScenarioDescription scenarioDescription : scenario.scenarios) {
            for (int i = 0; i < scenarioDescription.depth; i++) {
                str += "-";
            }
            str += scenarioDescription.content + "\n";
        }

        return str;
    }
}
