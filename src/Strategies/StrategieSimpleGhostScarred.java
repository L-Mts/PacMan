package Strategies;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Agents.*;
import Games.PacmanGame;
import Ressources.*;
import java.lang.Math;

public class StrategieSimpleGhostScarred extends AbstractStrategie {

    public StrategieSimpleGhostScarred(PacmanGame jeu) {
        super(jeu);
    }

    @Override
    public AgentAction getAction(AbstractAgent agent, Maze maze) {

        ArrayList<AbstractAgent> allPacman = new ArrayList<AbstractAgent>();
        for (AbstractAgent pacman : this.jeu.getListe_agents()) {
            if (pacman instanceof AgentPacman) {
                allPacman.add(pacman);
            }
        }

        AbstractAgent pacmanProche = allPacman.get(0);

        if (allPacman.size() > 1) {
            int min = 0;
            for (AbstractAgent pacman : allPacman) {
                int distManhattan = Math.abs(pacman.getPos().getX() - agent.getPos().getX()) + Math.abs(pacman.getPos().getY() - agent.getPos().getY());
                if (distManhattan < min) {
                    min = distManhattan;
                    pacmanProche = pacman;
                }
            }
        }

        // en partant du pacman le plus proche : s'il est à ma gauche/droite/au-dessus/en-dessous :
        // bouger dans la direction opposée selon les possibilité : si possible, aller dans direction opposée, si pas possible, choisir toute autre direction qui n'est pas celle du pacman sans préférence de choix
        int pacX = pacmanProche.getPos().getX();
        int pacY = pacmanProche.getPos().getY();
        int ghostX = agent.getPos().getX();
        int ghostY = agent.getPos().getY();

        List<AgentAction> actionPossibles;

        //le pacman est à gauche et au dessus ou au même niveau
        if (pacX < ghostX && (pacY >= ghostY)) {
            actionPossibles = List.of(actionEAST, actionSOUTH, actionNORTH);
        } else if (pacX < ghostX && pacY < ghostY) {        //le pacman est à gauche et en dessous
            actionPossibles = List.of(actionEAST, actionNORTH, actionSOUTH);
        } else if (pacX > ghostX && (pacY >= ghostY)) {     //le pacman est à droite et au dessus ou au même niveau
            actionPossibles = List.of(actionWEST, actionSOUTH, actionNORTH);
        } else if (pacX > ghostX && pacY < ghostY) {        //le pacman est à droite et en dessous
            actionPossibles = List.of(actionWEST, actionNORTH, actionSOUTH);
        } else if (pacX == ghostX && pacY > ghostY) {       //le pacman est pile au dessus
            actionPossibles = List.of(actionSOUTH, actionWEST, actionEAST);
        } else if (pacX == ghostX && pacY < ghostY) {       //le pacman est pile en dessous
            actionPossibles = List.of(actionNORTH, actionWEST, actionEAST);
        } else {
            actionPossibles = List.of(actionSTOP);
        }

        for (AgentAction action : actionPossibles) {
            if (this.jeu.isLegalMove(agent, action)) return action;
        }

        return AbstractStrategie.actionSTOP;
    }
    
}