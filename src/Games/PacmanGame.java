package Games;

import java.util.ArrayList;

import Agents.*;
import Ressources.*;

public class PacmanGame extends Game {

    // --- Atrributs --- //

    private ArrayList<AbstractAgent> liste_agents;
    
    // --- Constructeur --- //

    public PacmanGame(int maxturn) {
        super(maxturn);
    }

    // --- Méthodes Héritées de Game --- //
    
    @Override
    public void initialiseGame() throws Exception {

        //initialise les 2 attributs qui ne peuvent pas être initialisés dans Constructeur (car appel de méthode initialiseGame dans super contrôleur)
        this.maze = new Maze("layouts/testMaze.lay");
        this.liste_agents = new ArrayList<AbstractAgent>();

        // récupère position départ en copie --> non changées dans maze --> possibilité de restart le jeu
        ArrayList<PositionAgent> pacman_start = this.maze.getPacman_start();
        ArrayList<PositionAgent> ghosts_start = this.maze.getGhosts_start();

        //ajout les positions de départ des agents à la liste des agents du jeu
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
        // effectue une action pour chaque agent
        ArrayList<PositionAgent> pacman_pos = new ArrayList<PositionAgent>();
        ArrayList<PositionAgent> ghost_pos = new ArrayList<PositionAgent>();
        for (AbstractAgent e : liste_agents) {
            moveAgent(e, new AgentAction(2));                   // TODO : changer le nombre dans new AgentAction selon stratégie !
            if (e instanceof AgentPacman) pacman_pos.add(e.getPos());
            if (e instanceof AgentGhost) ghost_pos.add(e.getPos());
        }
       
        /* A FAIRE DANS LA VUE !!!
         * 
         * panel.setPacmans_pos(pacman_pos);
         * panel.setGhosts_pos(ghost_pos);
         * 
         * panel.repaint();
         */


        // TODO mise à jour du labyrinthe (food eaten, ...)
        // = mise à jour des tableaux de boolean Food et Capsule
        
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
