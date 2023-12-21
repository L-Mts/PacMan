package Agents;

import java.util.ArrayList;

import Ressources.*;
import Strategies.*;

public abstract class AbstractAgent {

    // --- Attributs --- //

    private PositionAgent pos;
    private AbstractStrategie strategie;
    public PositionAgent lastPos;

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

    public AbstractStrategie getStrategie() {
        return this.strategie;
    } 

    public void setStrategie(AbstractStrategie strat) {
        this.strategie = strat;
    }

    public PositionAgent getLastPos () {
        return this.lastPos;
    }

    public void setLastPos (PositionAgent lastPos) {
        this.lastPos = lastPos;
    }


}
