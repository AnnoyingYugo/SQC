package pl.put.poznan.sqc.logic;

import java.util.ArrayList;
import java.util.List;

/**
 * This is just an example to show that the logic should be outside the REST
 * service.
 * 
 * @author Filip Batóg, Krzysztof Garsztka, Aleksandra Ostrowska, Paviel Mamchur
 */
public class SQC {
    /**
     * This is the class that represents the body of the scenario.
     */
    /**
     * Represents the body of a scenario, including its title, actors, system, and sub-scenarios.
     */
    public static class ScenarioBody {

        private String title;
        private String[] actors;
        private String[] system;
        private ScenarioDescription[] scenarios;
        /**
         * Gets the title of the scenario.
         * 
         * @return the title of the scenario.
         */
        public String getTitle() {
            return this.title;
        }

        /**
         * Gets the actors involved in the scenario.
         * 
         * @return an array of actors.
         */
        public String[] getActors() {
            return this.actors;
        }

        /**
         * Gets the system components involved in the scenario.
         * 
         * @return an array of system components.
         */
        public String[] getSystem() {
            return this.system;
        }

        /**
         * Gets the sub-scenarios of the scenario.
         * 
         * @return an array of sub-scenarios.
         */
        public ScenarioDescription[] getScenarios() {
            return this.scenarios;
        }

        /**
         * Sets the title of the scenario.
         * 
         * @param title the title to set.
         */
        public void setTitle(String title) {
            this.title = title;
        }

        /**
         * Sets the actors involved in the scenario.
         * 
         * @param actors the actors to set.
         */
        public void setActors(String[] actors) {
            this.actors = actors;
        }

        /**
         * Sets the system components involved in the scenario.
         * 
         * @param system the system components to set.
         */
        public void setSystem(String[] system) {
            this.system = system;
        }

        /**
         * Sets the sub-scenarios of the scenario.
         * 
         * @param scenarios the sub-scenarios to set.
         */
        public void setScenarios(ScenarioDescription[] scenarios) {
            this.scenarios = scenarios;
        }

        /**
         * Accepts a visitor to perform operations on the scenario body.
         * 
         * @param visitor the visitor to accept.
         */
        public void accept(Visitor visitor) {
            visitor.visit(this);
        }
    }

    /**
     * This is the class that represents the description of the scenario.
     */
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
        this.scenario.setTitle(title);
        this.scenario.setActors(actors);
        this.scenario.setScenarios(scenarios);
        this.scenario.setSystem(system);
    }

    /**
     * This method accepts a visitor.
     * @param scenarios
     * @return SQC
     */
    public static ScenarioDescription[] readScenarioDescriptions(String scenarios) {
        if (scenarios == null) {
            return new ScenarioDescription[0];
        }
        String[] scenarioSteps = scenarios.split(",");
        List<ScenarioDescription> correctScenarios = new ArrayList<>();

        ScenarioDescription[] scenarioDescriptions = new ScenarioDescription[scenarioSteps.length];
        for (String step : scenarioSteps) {
            String[] splitScenario = step.split(":");
            if(splitScenario.length == 2) {
                try {
                    Integer depth = Integer.valueOf(splitScenario[0]);
                    String content = splitScenario[1];
                    if (content != null && !content.trim().isEmpty()) {
                        correctScenarios.add(new ScenarioDescription(depth, content));
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }
        return correctScenarios.toArray(new ScenarioDescription[0]);
    }

    /**
     * Prints the scenario.
     * @return String scenario
     */
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
