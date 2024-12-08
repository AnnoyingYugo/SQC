package pl.put.poznan.sqc.logic;
import pl.put.poznan.sqc.logic.SQC.ScenarioBody;
import pl.put.poznan.sqc.logic.SQC.ScenarioDescription;

public class NumberOfSteps implements Visitor{

    private ScenarioDescription[] scenarios;


    @Override
    public void visit(ScenarioBody scenarioBody) {
        this.scenarios = scenarioBody.scenarios;
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
