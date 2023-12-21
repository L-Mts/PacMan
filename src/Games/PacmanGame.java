package Games;

import java.util.ArrayList;

import Agents.*;
import Ressources.*;
import Strategies.*;

public class PacmanGame extends Game {

    // --- Atrributs --- //

    private ArrayList<AbstractAgent> liste_agents;
    private ArrayList<PositionAgent> pacman_start;
    private ArrayList<PositionAgent> ghosts_start;
    private boolean continu = true;
    private int capsuleCompteur = 0;
    
    // --- Constructeur --- //

    public PacmanGame(int maxturn) {
        super(maxturn);
    }

    // --- Méthodes Héritées de Game --- //
    
    /**
     * <p> Initialisation du jeu : </p>
     * <p> Chargement du labyrinthe & des positions de départ des agents du jeu </p>
     * <p> Exception si le labyrinthe n'est pas chargé correctement </p>
     */
    @Override
    public void initialiseGame() throws Exception {

        //initialise les 2 attributs qui ne peuvent pas être initialisés dans Constructeur (car appel de méthode initialiseGame dans super contrôleur)
        this.maze = new Maze("layouts/capsuleClassic.lay");
        this.liste_agents = new ArrayList<AbstractAgent>();

        // récupère positions de départ en copie --> non changées dans maze --> possibilité de restart le jeu
        this.pacman_start = this.maze.getPacman_start();
        this.ghosts_start = this.maze.getGhosts_start();

        //ajoute les positions de départ des agents à la liste des agents du jeu
        for (PositionAgent e : pacman_start) {
            this.liste_agents.add(new AgentPacman(e));
        }
        for (PositionAgent e : ghosts_start) {
            this.liste_agents.add(new AgentGhost(e));
        }

        /*
        // Tous les agents démarrent avec une strategie random
        System.out.println("Les agents ont tous une stratégie Random");
        for (AbstractAgent e : this.liste_agents) {
            e.setStrategie(new StrategieRandom(this));
        }
        */

        // Tous les agent démarrent avec la stratégie simple
        System.out.println("Les agents ont tous une stratégie Simple");
        for (AbstractAgent e : this.liste_agents) {
            e.setStrategie(new StrategieSimple(this));
        }

        System.out.println("Le jeu est initialisé");
    }
    

    /**
     * <p> Pour chaque agent du jeu, effectue une action selon sa stratégie personnelle </p>
     * <p> Vérification que l'action est autorisée dans les méthodes de la stratégie de l'agent </p>
     * <p> Met à jour les éléments du labyrinthe pour correspondre aux actions </p>
     */
    @Override
    public void takeTurn() {
        if (this.capsuleCompteur > 0) this.capsuleCompteur = this.capsuleCompteur-1;
        for (AbstractAgent e : this.liste_agents) {
            AgentAction action = e.getStrategie().getAction(e, this.maze);
            moveAgent(e, action);
            if (e instanceof AgentPacman) {
                if (this.maze.isFood(e.getPos().getX(), e.getPos().getY())) {
                    this.maze.setFood(e.getPos().getX(), e.getPos().getY(), false);
                }
                if (this.maze.isCapsule(e.getPos().getX(), e.getPos().getY())) {
                    this.maze.setCapsule(e.getPos().getX(), e.getPos().getY(), false);
                    this.capsuleCompteur = 20;
                }
            }   
        }

        if (this.capsuleCompteur > 0) {
            ArrayList<AbstractAgent> toRemove = new ArrayList<AbstractAgent>();
            for (AbstractAgent pacman : this.liste_agents) {
                if (pacman instanceof AgentPacman) {
                    for (AbstractAgent ghost : this.liste_agents) {
                        System.out.println("Entrée dans le for");
                        if (ghost instanceof AgentGhost && (ghost.getPos().equals(pacman.getPos()) || ghost.getPos().equals(pacman.getLastPos()) ) && !toRemove.contains(ghost)) {
                            System.out.println("Entrée dans le if");
                            toRemove.add(ghost);
                        }
                    }
                }
            }
            if (toRemove.isEmpty() == false) {
                System.out.println("toRemove no empty");
                System.out.println("Liste des agents = " + this.liste_agents);
                for (AbstractAgent ghost : toRemove) {
                    this.liste_agents.remove(ghost);
                }
                System.out.println("Liste des agents = " + this.liste_agents);
            }
            
        }
        
        System.out.println("Tour " + this.turn + " en cours");
    }

    /**
     * <p> Est-ce que le jeu continu ? </p>
     * @return vrai
     */
    @Override
    public boolean gameContinue() {
        return this.continu;
    }

    /**
     * 
     */
    public void setContinue(boolean val) {
        this.continu = val;
    }

    /**
     * <p> Arrête le jeu </p>
     */
    @Override
    public void gameOver() {
        System.out.println("Le jeu est terminé");
    }


    // --- Méthodes de la classe --- //

    /**
     * <p> Vérifie qu'une action donnée est possible dans le labyrinthe </p>
     * <p> = vérifie que l'action ne met pas l'agent dans un mur </p>
     * 
     * @param agent
     * @param action
     * @return vrai : si l'action est possible </br> </br> faux : sinon
     */
    public boolean isLegalMove (AbstractAgent agent, AgentAction action) {
        PositionAgent pos = agent.getPos();
        int vx = action.get_vx();
        int vy = action.get_vy();

        if (maze.isWall(pos.getX()+vx, pos.getY()+vy) != true) {
            return true;
        }
        
        return false;
    }

    
    /** 
     * <p> Mise à jour de la position de l'agent selon l'action effectuée </p>
     * 
     * @param agent
     * @param action
     */
    public void moveAgent(AbstractAgent agent, AgentAction action) {
        int vx = action.get_vx();
        int vy = action.get_vy();
        int dir = action.get_direction();
        PositionAgent pos = agent.getPos();
        agent.setLastPos(pos);
        PositionAgent new_pos = new PositionAgent(pos.getX()+vx, pos.getY()+vy, dir);
        agent.setPos(new_pos);
    }

    /**
     * @return this.list_agents
     */
    public ArrayList<AbstractAgent> getListe_agents() {
        return this.liste_agents;
    }

    /**
     * @param liste_agents : la liste d'agents à mettre dans l'attribut this.list_agents
     */
    public void setListe_agents(ArrayList<AbstractAgent> liste_agents) {
        this.liste_agents = liste_agents;
    }

    /**
     * @return this.capsuleCompteur
     */
    public int getCapsuleCompteur() {
        return this.capsuleCompteur;
    }



    
}
