package pl.put.poznan.sqc.logic;

import pl.put.poznan.sqc.logic.SQC.ScenarioBody;

/**
 * This class is a visitor that swaps an old actor name with a new one
 * throughout the scenario.
 *
 * @author Aleksandra Ostrowska
 * @since 7.0
 */
public class ActorSwapper implements Visitor {
    private ScenarioBody scenarioBody;
    private String oldName;
    private String newName;

    /**
     * This method is called when the visitor visits the ScenarioBody object.
     *
     * @param scenarioBody object that the visitor visits
     */
    @Override
    public void visit(ScenarioBody scenarioBody) {
        this.scenarioBody = scenarioBody;
    }

    /**
     * Sets the actor names to be swapped.
     *
     * @param oldName the name to be replaced.
     * @param newName the name to replace with.
     */
    public void setActorNames(String oldName, String newName) {
        this.oldName = oldName;
        this.newName = newName;
    }

    /**
     * This method swaps an old actor name with a new one throughout the scenario.
     *
     * @param oldName the name to be replaced.
     * @param newName the name to replace with.
     * @return a String representation of the updated scenario, or null if
     *         conditions are not met.
     */
    public String swapActor(String oldName, String newName) {
        if (oldName.isEmpty() || newName.isEmpty() || oldName.equals(newName)) {
            return null;
        }
        boolean actorFound = false;
        var scenarios = scenarioBody.getScenarios();
        var actors = scenarioBody.getActors();

        for (int i = 0; i < actors.length; i++) {
            if (actors[i].equals(oldName)) {
                actors[i] = newName;
                actorFound = true;
            }
        }

        if (!actorFound) {
            return null;
        }

        for (SQC.ScenarioDescription scenario : scenarios) {
            if (scenario.content.contains(oldName)) {
                scenario.content = scenario.content.replace(oldName, newName);
            }
        }

        ScenarioFormatter scenarioFormatter = new ScenarioFormatter();
        scenarioFormatter.visit(scenarioBody);
        return scenarioFormatter.formatScenario();
    }

    /**
     * This method uses swapActor to operate the swap and returns the resulting
     * string.
     *
     * @return a String representation of the updated scenario, or null if
     *         conditions are not met.
     */
    @Override
    public String getInfo() {
        return swapActor(oldName, newName);
    }
}
