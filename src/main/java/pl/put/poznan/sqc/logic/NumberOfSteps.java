package pl.put.poznan.sqc.logic;
import pl.put.poznan.sqc.logic.SQC.ScenarioBody;
import pl.put.poznan.sqc.logic.SQC.ScenarioDescription;

/**
 * This class is a visitor that counts the number of steps in the scenario.
 *
 * @author Aleksandra Ostrowska
 * @since 2.0
 */
public class NumberOfSteps implements Visitor{
    private ScenarioDescription[] scenarios;


    /**
     * This method visits the ScenarioBody and sets the scenarios array to the array of scenarios in the ScenarioBody.
     *
     * @param scenarioBody the ScenarioBody to visit
     */
    @Override
    public void visit(ScenarioBody scenarioBody) {
        this.scenarios = scenarioBody.scenarios;
    }

    /**
     * This method counts the number of steps in the scenario.
     *
     * @return the number of steps in the scenario
     */
    public int countSteps() {
        if (scenarios == null) {
            return 0;
        }

        int stepCount = 0;
        for (ScenarioDescription scenario : scenarios) {
            if(scenario.content != null && !scenario.content.trim().isEmpty()) {
                stepCount++;
            }
        }
        return stepCount;
    }

    /**
     * This method returns the information about the number of steps in the scenario.
     *
     * @return the information about the number of steps in the scenario
     */
    public String getInfo() {
        int numberOfSteps = countSteps();
        return "Number of steps in the scenario: " + numberOfSteps;
    }

}
