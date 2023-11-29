package Strategies;

import Agents.*;
import Games.PacmanGame;
import Ressources.*;

public class StrategieRandom extends AbstractStrategie {

    public StrategieRandom(PacmanGame jeu) {
        super(jeu);
    }


    /**
     * <p> Génère un nombre aléatoire entre 0 et 4 pour la direction de l'action </p>
     * 
     * @param agent
     * @param maze
     * 
     * @return action : une action aléatoire si elle est valide </br> </br> une action "stop" si l'action aléatoire n'est pas valide
     */
    @Override
    public AgentAction getAction(AbstractAgent agent, Maze maze) {

        int random = (int) (Math.random() * 5);
        AgentAction actionRandom = new AgentAction(random);
        if (this.jeu.isLegalMove(agent, actionRandom)) {
            return actionRandom;
        } else {
            return new AgentAction(4);
        }
    }
    
}
