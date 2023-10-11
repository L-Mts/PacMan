package Games;

import java.util.ArrayList;

import Agents.*;
import Ressources.*;

public class PacmanGame extends Game {

    // --- Atrributs --- //

    Maze maze;
    private ArrayList<AbstractAgent> liste_agents;

    // --- Constructeur --- //

    public PacmanGame(int maxturn, Maze maze) {
        super(maxturn);
        this.maze = maze;
        this.liste_agents = new ArrayList<AbstractAgent>();
    }

    // --- Méthodes Héritées de Game --- //

    @Override
    public void initialiseGame() {
        ArrayList<PositionAgent> pacman_start = maze.getPacman_start();
        ArrayList<PositionAgent> ghosts_start = maze.getGhosts_start();
        for (PositionAgent e : pacman_start) {
            this.liste_agents.add(new AgentPacman(e));
        }
        for (PositionAgent e : ghosts_start) {
            this.liste_agents.add(new AgentGhost(e));
        }
        System.out.println("Le jeu est initialisé");
    }

    @Override
    public void takeTurn() {
        // effectue une action pour chaque agent puis met à jour labyrinthe
        System.out.println("Tour " + this.turn + " en cours");
    }

    @Override
    public boolean gameContinue() {
        return true;
    }

    @Override
    public void gameOver() {
        System.out.println("Le jeu est terminé");
    }


    // --- Méthodes de la classe --- //

    // vérification que le mouvement de l'agent est possible dans le labyrinthe
    public boolean isLegalMove (AbstractAgent agent, AgentAction action) {
        PositionAgent pos = agent.getPos();
        int vx = action.get_vx();
        int vy = action.get_vy();

        if (maze.isWall(pos.getX()+vx, pos.getY()+vy) != true) {
            return true;
        }
        
        return false;
    }

    // mise à jour position de l'agent après action
    public void moveAgent(AbstractAgent agent, AgentAction action) {
        if(isLegalMove(agent, action)) {
            int vx = action.get_vx();
            int vy = action.get_vy();
            int dir = action.get_direction();
            PositionAgent pos = agent.getPos();
            PositionAgent new_pos = new PositionAgent(pos.getX()+vx, pos.getY()+vy, dir);
            agent.setPos(new_pos);
        }
    }

    public ArrayList<AbstractAgent> getListe_agents() {
        return liste_agents;
    }

    public void setListe_agents(ArrayList<AbstractAgent> liste_agents) {
        this.liste_agents = liste_agents;
    }

    
}
