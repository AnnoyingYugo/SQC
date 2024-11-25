package pl.put.poznan.sqc.logic;
import pl.put.poznan.sqc.logic.SQC.ScenarioDescription;

public class NumberOfSteps {

    private ScenarioDescription[] scenarios;

    public NumberOfSteps(ScenarioDescription[] scenarios) {
        this.scenarios = scenarios;
    }

    public int countSteps() {
        if (scenarios == null) {
            return 0;
        }
        return scenarios.length;
    }

    public String getInfo() {
        int numberOfSteps = countSteps();
        return "Number of steps in the scenario: " + numberOfSteps;
    }

}
