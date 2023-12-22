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

    private AgentPacman pacmanInteractif;

    private int capsuleCompteur = 0;
    private int nbrCapsules;
    private int nbrFood;
    
    // ending = -1 : le jeu est en cours
    // ending = 0 : Game Over - perdu
    // ending = 1 : Game Win - gagné
    private int ending = -1;

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
        this.pacmanInteractif = null;

        //initialise les 2 attributs qui ne peuvent pas être initialisés dans Constructeur (car appel de méthode initialiseGame dans super contrôleur)
        this.maze = new Maze("layouts/capsuleClassic.lay");
        
        this.liste_agents = new ArrayList<AbstractAgent>();

        // récupère positions de départ en copie --> non changées dans maze --> possibilité de restart le jeu
        this.pacman_start = this.maze.getPacman_start();
        this.ghosts_start = this.maze.getGhosts_start();

        // récupère le nombre de food et de capsules du jeu
        this.nbrCapsules = this.maze.getNbrCapsules();
        this.nbrFood = this.maze.getNbrFood();

        //ajoute les positions de départ des agents à la liste des agents du jeu
        for (PositionAgent e : pacman_start) {
            this.liste_agents.add(new AgentPacman(e));
        }
        for (PositionAgent e : ghosts_start) {
            this.liste_agents.add(new AgentGhost(e));
        }

        /* POUR UNE STRATEGIE RANDOM

        // Tous les agents démarrent avec une strategie random
        System.out.println("Les agents ont tous une stratégie Random");
        for (AbstractAgent e : this.liste_agents) {
            e.setStrategie(new StrategieRandom(this));
        }

        */

        /* POUR UNE STRATEGIE SIMPLE POUR TOUS LES AGENTS
        
        // Tous les agent démarrent avec la stratégie simple
        System.out.println("Les agents ont tous une stratégie Simple");
        for (AbstractAgent e : this.liste_agents) {
            e.setStrategie(new StrategieSimple(this));
        }

        */

        /* POUR UNE STRATEGIE INTERACTIVE POUR LE PACMAN ET UNE STRATEGIE SIMPLE POUR LES AUTRES AGENTS */
        // Pour un jeu interactif, on considère qu'on ne contrôle qu'un seul Pacman (le premier trouvé parmis les agents du labyrinthe) même s'il y en a plusieurs initialisé avec le labyrinthe
        for (AbstractAgent e : this.liste_agents) {
            if (e instanceof AgentPacman) {
                e.setStrategie(new StrategieInteractive(this));
                if (this.pacmanInteractif == null) this.pacmanInteractif = (AgentPacman) e;
            } else {
                e.setStrategie(new StrategieSimple(this));
            }
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
        if (this.capsuleCompteur > 0) {
            this.capsuleCompteur = this.capsuleCompteur-1;
            if (this.capsuleCompteur == 0) {
                for (AbstractAgent ghost : this.liste_agents) {
                    if (ghost instanceof AgentGhost) {
                        ghost.setStrategie(new StrategieSimple(this));
                    }
                }
            }
        }

        for (AbstractAgent e : this.liste_agents) {
            AgentAction action = e.getStrategie().getAction(e, this.maze);
            moveAgent(e, action);
            if (e instanceof AgentPacman) {
                if (this.maze.isFood(e.getPos().getX(), e.getPos().getY())) {
                    this.maze.setFood(e.getPos().getX(), e.getPos().getY(), false);
                    this.nbrFood -= 1;
                }
                if (this.maze.isCapsule(e.getPos().getX(), e.getPos().getY())) {
                    this.maze.setCapsule(e.getPos().getX(), e.getPos().getY(), false);
                    this.capsuleCompteur = 20;
                    this.nbrCapsules -= 1;
                }
            }   
        }

        if (this.capsuleCompteur == 20) {
            for (AbstractAgent ghost : this.liste_agents) {
                if (ghost instanceof AgentGhost) {
                    ghost.setStrategie(new StrategieSimpleGhostScarred(this));
                }
            }
        }

        if (this.capsuleCompteur > 0) {
            ArrayList<AbstractAgent> toRemove = new ArrayList<AbstractAgent>();
            for (AbstractAgent pacman : this.liste_agents) {
                if (pacman instanceof AgentPacman) {
                    for (AbstractAgent ghost : this.liste_agents) {
                        if (ghost instanceof AgentGhost && (ghost.getPos().equals(pacman.getPos()) || ghost.getPos().equals(pacman.getLastPos()) ) && !toRemove.contains(ghost)) {
                            toRemove.add(ghost);
                        }
                    }
                }
            }
            if (toRemove.isEmpty() == false) {
                for (AbstractAgent ghost : toRemove) {
                    this.liste_agents.remove(ghost);
                }
            }
            
        }

        this.endGame();
        
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

    public void endGame () {
        ArrayList<PositionAgent> pacman_pos = new ArrayList<PositionAgent>();
        ArrayList<PositionAgent> ghost_pos = new ArrayList<PositionAgent>();
        for (AbstractAgent e : this.liste_agents) {
            if (e instanceof AgentPacman) {
                pacman_pos.add(e.getPos());
            } else {
                ghost_pos.add(e.getPos());
            }
        }

        for(PositionAgent posPacman : pacman_pos) {
            for (PositionAgent posGhost : ghost_pos) {
                if (posPacman.getX()==posGhost.getX() && posPacman.getY()==posGhost.getY()) this.gameOver();
            }
        }
        
        for (AbstractAgent e : this.getListe_agents()) {
            if (e instanceof AgentPacman) {
                PositionAgent lastPos = e.getLastPos();
                for (PositionAgent posGhost : ghost_pos) {
                    if (lastPos.getX()==posGhost.getX() && lastPos.getY()==posGhost.getY()) this.gameOver();
                }
            }
        }

        if (this.nbrCapsules == 0 && this.nbrFood == 0) {
            this.gameWin();
        }
    
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
     * <p> Arrête le jeu - perdu </p>
     */
    @Override
    public void gameOver() {
        this.setContinue(false);
        this.ending = 0;
    }

    /**
     * <p> Arrête le jeu - gagné </p>
     */
    public void gameWin() {
        this.setContinue(false);
        this.ending = 1;
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

    /**
     * @return this.capsuleCompteur
     */
    public AgentPacman getPacmanInteractif () {
        return this.pacmanInteractif;
    }

    /**
     * @return this.ending
     */
    public int getEnding () {
        return this.ending;
    }

    

    
}
