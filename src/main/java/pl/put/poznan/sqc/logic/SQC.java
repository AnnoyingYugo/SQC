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
        public void accept(Visitor visitor) {
            visitor.visit(this);
        }
    }

    public static class ScenarioDescription {
        public String content;
        public Integer depth;

        public ScenarioDescription(Integer depth, String content) {
            this.depth = depth;
            this.content = content;
        }
    }

    private ScenarioBody scenario;

    public SQC(ScenarioBody scenario) {
        this.scenario = scenario;
    }

    public SQC(String title, String[] actors, String[] system, ScenarioDescription[] scenarios) {
        this.scenario = new ScenarioBody();
        this.scenario.title = title;
        this.scenario.actors = actors;
        this.scenario.system = system;
        this.scenario.scenarios = scenarios;
    }

    public static ScenarioDescription[] readScenarioDescriptions(String scenarios) {
        if (scenarios == null) {
            return new ScenarioDescription[0];
        }
        String[] scenarioSteps = scenarios.split(",");

        ScenarioDescription[] scenarioDescriptions = new ScenarioDescription[scenarioSteps.length];
        for (int i = 0; i < scenarioDescriptions.length; i++) {
            String[] splitScenario = scenarioSteps[i].split(":");
            Integer depth = Integer.valueOf(splitScenario[0]);
            String content = splitScenario[1];
            scenarioDescriptions[i] = new ScenarioDescription(depth, content);
        }
        return scenarioDescriptions;
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
