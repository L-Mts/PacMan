package Strategies;

import Agents.AbstractAgent;
import Games.PacmanGame;
import Ressources.*;

public class StrategieSimple extends AbstractStrategie {

    public StrategieSimple(PacmanGame jeu) {
        super(jeu);
    }

    @Override
    public AgentAction getAction(AbstractAgent agent, Maze maze) {
        // Cherche à réaliser une action en priorisant dans l'ordre :
        // 0: HAUT --> 1: BAS --> 2: DROITE --> 3: GAUCHE --> 4: STOP

        int i = 0;
        AgentAction actionSimple = new AgentAction(0);

        if (agent.lastFivePos.isEmpty() == false) {
            PositionAgent last_pos = agent.lastFivePos.get(agent.lastFivePos.size() - 1);
            int last_x = last_pos.getX();
            int last_y = last_pos.getY();
            PositionAgent pos = agent.getPos();
            int x = pos.getX();
            int y = pos.getY();

            while ((this.jeu.isLegalMove(agent, actionSimple) == false && i < 5) || !(x+actionSimple.get_vx()!=last_x || y+actionSimple.get_vy()!=last_y) ) {
                actionSimple = new AgentAction(i++);
            }
        } else {
            while (this.jeu.isLegalMove(agent, actionSimple) == false && i < 5) {
                actionSimple = new AgentAction(i++);
            }
        }

        return actionSimple;

    }
    
}
