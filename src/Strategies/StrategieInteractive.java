package Strategies;

import Agents.AbstractAgent;
import Games.PacmanGame;
import Ressources.AgentAction;
import Ressources.Maze;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class StrategieInteractive extends AbstractStrategie implements KeyListener {

    private int direction = 4;
    private AgentAction lastAction = actionSTOP;

    public StrategieInteractive(PacmanGame jeu) {
        super(jeu);
    }

    @Override
    public AgentAction getAction(AbstractAgent agent, Maze maze) {
        AgentAction prochaineAction;
        switch (this.direction) {
            case 0:
                prochaineAction = actionNORTH;
                break;
            case 1:
                prochaineAction = actionSOUTH;
                break;
            case 2:
                prochaineAction = actionEAST;
                break;
            case 3:
                prochaineAction = actionWEST;
                break;
            default:
                prochaineAction = lastAction;
        }

        if (this.jeu.isLegalMove(agent, prochaineAction)) {
            lastAction = prochaineAction;
            return prochaineAction;
        } else if (this.jeu.isLegalMove(agent, lastAction)) {
            return lastAction;
        } else {
            return actionSTOP;
        }
    }

    public int getDirection() {
        return this.direction;
    }

    public void setDirection (int direction) {
        this.direction = direction;
    }
    
}
