package pl.put.poznan.sqc.logic;


import pl.put.poznan.sqc.logic.SQC.ScenarioBody;
import pl.put.poznan.sqc.logic.SQC.ScenarioDescription;

public class KeyWordCounter implements Visitor{
    private ScenarioDescription[] scenarios;


    @Override
    public void visit(ScenarioBody scenario){
       this.scenarios= scenario.scenarios;
    }


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
    public int stepsWithKeyword(){
        int result = 0;
        for(int i = 0; i < scenarios.length; i++){
            if (stepStartsWithKeyword(scenarios[i])){
               result++;
            }
        }
        return result;
    }
    public Integer getInfo() {
        Integer result = stepsWithKeyword();
        return result;
    }
}
