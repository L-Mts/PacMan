package Agents;

import Ressources.*;

public abstract class AbstractAgent {

    // --- Attributs --- //

    private PositionAgent pos;

    // --- Constructeur --- //

    public AbstractAgent (PositionAgent pos) {
        this.pos = pos;
    }
    
    // --- Méthodes --- //

    public PositionAgent getPos() {
        return pos;
    }

    public void setPos(PositionAgent pos) {
        this.pos = pos;
    }
}
