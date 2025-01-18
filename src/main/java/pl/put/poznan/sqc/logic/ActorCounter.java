package pl.put.poznan.sqc.logic;

/**
 * This class is a visitor that counts actor's presence in the scenario.
 *
 * @author Aleksandra Ostrowska
 * @since 7.0
 */
public class ActorCounter implements Visitor{
    private SQC.ScenarioBody scenarioBody;
    private String actorName;

    /**
     * This method is called when the visitor visits the ScenarioBody object.
     *
     * @param scenarioBody object that the visitor visits
     */
    @Override
    public void visit(SQC.ScenarioBody scenarioBody) {
        this.scenarioBody = scenarioBody;
    }

    /**
     * Sets the actor name to be counted.
     *
     * @param actorName the name of actor to be counted.
     */
    public void setActorNames(String actorName) {
        this.actorName = actorName;
    }

    /**
     * This method counts actor's presence in the scenario.
     *
     * @param actorName the name to be counted.
     * @return Integer or null if conditions are not met.
     */
    public Integer countActor(String actorName) {
        int count = 0;
        boolean actorFound = false;

        if (actorName.isEmpty()) {
            return null;
        }

        for (int i = 0; i < scenarioBody.actors.length; i++) {
            if (scenarioBody.actors[i].equals(actorName)) {
                actorFound = true;
                break;
            }
        }

        if (!actorFound) {
            return 0;
        }

        for (SQC.ScenarioDescription scenario : scenarioBody.scenarios) {
            if (scenario.content.contains(actorName)) {
                count++;
            }
        }
        return count;
    }

    /**
     * This method uses countActor to counts actor's presence in the scenario.
     *
     * @return Integer or null if conditions are not met.
     */
    @Override
    public Integer getInfo() {
        return countActor(actorName);
    }
}
