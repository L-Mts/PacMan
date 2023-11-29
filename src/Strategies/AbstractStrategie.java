package Strategies;

import Agents.*;
import Games.*;
import Ressources.*;

public abstract class AbstractStrategie {

    // --- ATTRIBUTS --- //

    PacmanGame jeu;

    // --- CONSTRUCTEUR --- //

    public AbstractStrategie (PacmanGame jeu) {
        this.jeu = jeu;
    }

    public abstract AgentAction getAction(AbstractAgent agent, Maze maze);
}
