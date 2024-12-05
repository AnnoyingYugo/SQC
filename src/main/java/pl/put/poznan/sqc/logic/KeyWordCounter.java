package pl.put.poznan.sqc.logic;


import pl.put.poznan.sqc.logic.SQC.ScenarioDescription;

public class KeyWordCounter {
    private ScenarioDescription[] scenarios;

    public KeyWordCounter(ScenarioDescription[] scenarios) {
        this.scenarios = scenarios;
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
    public int getInfo() {
        int result = stepsWithKeyword();
        return result;
    }
}
