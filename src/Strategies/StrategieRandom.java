package Strategies;

import Agents.*;
import Ressources.*;

public class StrategieRandom implements Strategie {

    @Override
    public AgentAction getAction(AbstractAgent agent, Maze maze) {
        
        int random = (int) (Math.random() * 5);

        return new AgentAction(random);
    }
    
}
