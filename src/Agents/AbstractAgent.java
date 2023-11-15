package Agents;

import Ressources.*;
import Strategies.*;

public abstract class AbstractAgent {

    // --- Attributs --- //

    private PositionAgent pos;
    private Strategie strategie;

    // --- Constructeur --- //

    public AbstractAgent (PositionAgent pos) {
        this.pos = pos;
    }
    
    // --- Méthodes --- //

    public PositionAgent getPos() {
        return this.pos;
    }

    public void setPos(PositionAgent pos) {
        this.pos = pos;
    }

    public Strategie getStrategie() {
        return this.strategie;
    } 

    public void setStrategie(Strategie strat) {
        this.strategie = strat;
    }
}
