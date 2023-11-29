package Agents;

import java.util.ArrayList;

import Ressources.*;
import Strategies.*;

public abstract class AbstractAgent {

    // --- Attributs --- //

    private PositionAgent pos;
    private AbstractStrategie strategie;
    public ArrayList<PositionAgent> lastFivePos;

    // --- Constructeur --- //

    public AbstractAgent (PositionAgent pos) {
        this.pos = pos;
        this.lastFivePos = new ArrayList<PositionAgent>();
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

    public ArrayList<PositionAgent> getLastFivePos () {
        return this.lastFivePos;
    }

    public void setLastFivePos (ArrayList<PositionAgent> lastFivePos) {
        this.lastFivePos = lastFivePos;
    }


}
