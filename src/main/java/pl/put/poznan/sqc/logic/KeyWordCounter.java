package pl.put.poznan.sqc.logic;


import pl.put.poznan.sqc.logic.SQC.ScenarioBody;
import pl.put.poznan.sqc.logic.SQC.ScenarioDescription;

/**
 * This class is a visitor that checks if each step in the scenario starts with a keyword.
 * 
 * @author Krzysztof Garsztka
 * @since 5.0
 */
public class KeyWordCounter implements Visitor{
    private ScenarioDescription[] scenarios;

    /**
     * This method is called when the visitor visits the ScenarioBody object.
     * 
     * @param scenario the ScenarioBody object that the visitor visits
     */
    @Override
    public void visit(ScenarioBody scenario){
       this.scenarios= scenario.scenarios;
    }

    /**
     * This method checks if the step starts with a keyword.
     * 
     * @param scenario the scenario that the visitor visits
     * @return true if the step starts with a keyword, false otherwise
     */
    public boolean stepStartsWithKeyword(ScenarioDescription scenario) {
        if (scenario.content.length() == 0) {
            return false;
        }
        String[] content = scenario.content.split("\\s+");
        String firstWord = content[0];

        if (firstWord.contains("IF") || firstWord.contains("ELSE")) {
            return true;
        }
        else if (firstWord.contains("FOR") ) {
            try{
                String secondWord = content[1];
                    if(secondWord.contains("EACH")){
                        return true;
                    }
            }
            catch(Throwable e){
                return false;
            }
        }
        return false;
    }

    /**
     * This method counts the number of steps that start with a keyword.
     * 
     * @return the number of steps that start with a keyword
     */
    public int stepsWithKeyword(){
        int result = 0;
        for(int i = 0; i < scenarios.length; i++){
            if (stepStartsWithKeyword(scenarios[i])){
               result++;
            }
        }
        return result;
    }

    /**
     * This method returns the number of steps that start with a keyword.
     * 
     * @return the number of steps that start with a keyword
     */
    public Integer getInfo() {
        Integer result = stepsWithKeyword();
        return result;
    }
}
