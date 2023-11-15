package Strategies;

import Agents.*;
import Ressources.*;

public interface Strategie {
    public AgentAction getAction(AbstractAgent agent, Maze maze);
}
