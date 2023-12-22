package Strategies;

import Agents.*;
import Games.*;
import Ressources.*;

public abstract class AbstractStrategie {

    // --- ATTRIBUTS --- //

    PacmanGame jeu;

    final static AgentAction actionNORTH = new AgentAction(AgentAction.NORTH);
    final static AgentAction actionSOUTH = new AgentAction(AgentAction.SOUTH);
    final static AgentAction actionEAST = new AgentAction(AgentAction.EAST);
    final static AgentAction actionWEST = new AgentAction(AgentAction.WEST);
    final static AgentAction actionSTOP = new AgentAction(AgentAction.STOP);

    // --- CONSTRUCTEUR --- //

    public AbstractStrategie (PacmanGame jeu) {
        this.jeu = jeu;
    }

    public abstract AgentAction getAction(AbstractAgent agent, Maze maze);
}
