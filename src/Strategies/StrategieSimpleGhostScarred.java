package Strategies;

import java.util.ArrayList;

import Agents.*;
import Games.PacmanGame;
import Ressources.*;

public class StrategieSimpleGhostScarred extends AbstractStrategie {

    public StrategieSimpleGhostScarred(PacmanGame jeu) {
        super(jeu);
    }

    @Override
    public AgentAction getAction(AbstractAgent agent, Maze maze) {

        AgentAction actionSimple = new AgentAction(0);


        ArrayList<AbstractAgent> allPacman = new ArrayList<AbstractAgent>();
        for (AbstractAgent pacman : this.jeu.getListe_agents()) {
            if (pacman instanceof AgentPacman) {
                allPacman.add(pacman);
            }
        }

        if (allPacman.size() > 1) {
            for (AbstractAgent pacman : allPacman) {
                //le pacman le plus proche sera celui qui a la différence abs(pacman.x - ghost.x) + abs(pacma.y - ghost.y) la plus petite
            }
        }

        // en partant du pacman le plus proche : s'il est à ma gauche/droite/au-dessus/en-dessous :
        // bouger dans la direction opposée selon les possibilité : si possible, aller dans direction opposée, si pas possible, choisir toute autre direction qui n'est pas celle du pacman sans préférence de choix


        return actionSimple;
    }
    
}