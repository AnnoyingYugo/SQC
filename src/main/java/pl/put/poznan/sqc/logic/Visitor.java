package pl.put.poznan.sqc.logic;

import pl.put.poznan.sqc.logic.SQC.ScenarioBody;

/**
 * This is the interface for the Visitor pattern.
 * 
 * @author Krzysztof Garsztka
 * @since 5.0
 * @see <a href="https://en.wikipedia.org/wiki/Visitor_pattern">Visitor pattern</a>
 * @see <a href="https://en.wikipedia.org/wiki/Double_dispatch">Double dispatch</a>
 */
public interface Visitor {
    void visit(ScenarioBody scenario);
    Object getInfo();
}
