package pl.put.poznan.sqc.logic;

import java.util.ArrayList;
import java.util.Arrays;

import pl.put.poznan.sqc.logic.SQC.ScenarioDescription;

public class ActorsExistTest {
    private ScenarioDescription[] scenarios;
    private String[] actors;

    public ActorsExistTest(ScenarioDescription[] scenarios, String[] actors) {
        this.scenarios = scenarios;
        this.actors = actors;
    }

    public  String[] checkSteps(){
        ArrayList<String> stepsNoActor = new ArrayList<String>();
        for(int i = 0; i < scenarios.length; i++){
            //check first if usees a constant word - TODO after adding key words
            String firstWord = scenarios[i].content.split("\\s+")[0];
            boolean matches = Arrays.stream(actors).anyMatch(firstWord::equals);
            if(!matches){
                stepsNoActor.add(scenarios[i].content);
            }
        }
        String[] result = new String[stepsNoActor.size()];
        result = stepsNoActor.toArray(result);
        return result;
    }

    /*
    public String convertToSingleString(String[] stringArray){
        StringBuilder resultBuilder = new StringBuilder();
        for(int i = 0; i < stringArray.length; i++){
            resultBuilder.append(stringArray[i]);
            resultBuilder.append("\n");
        }
        return resultBuilder.toString();
    }*/

    public String[] getInfo() {
        String[] result = checkSteps();
        return result;
    }
}
