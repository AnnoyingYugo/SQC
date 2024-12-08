package pl.put.poznan.sqc.logic;

import pl.put.poznan.sqc.logic.SQC.ScenarioBody;

public interface Visitor {
    void visit(ScenarioBody scenario);
    Object getInfo();
}
