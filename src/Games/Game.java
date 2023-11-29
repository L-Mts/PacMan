package Games;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.lang.Runnable;

import Ressources.Maze;

public abstract class Game implements Runnable {

    // --- Attributs --- //
    
    //compteur nbr tours
    public int turn;

    //max nobr tours
    //pré-défini via constructeur de Game
    public int maxturn;

    //vérifie mise en pause ou non
    public boolean isRunning;

    private Thread thread;

    public long time;

    public Maze maze;

    // --- Constructeur --- //

    public Game(int maxturn) {
        this.maxturn = maxturn;
        this.isRunning = false;
        this.time = 500;
        this.init();
    }

    // --- Observé --- //

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    
    public void addPropertyChangeListener(String property, PropertyChangeListener listener) {
        support.addPropertyChangeListener(property, listener);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    
    public void removePropertyChangeListener(String property, PropertyChangeListener listener) {
        support.removePropertyChangeListener(property, listener);
    }



    // --- Méthodes Abstraites --- //

    public abstract void initialiseGame() throws Exception;
    public abstract void takeTurn();
    public abstract boolean gameContinue();
    public abstract void gameOver();


    // --- Méthodes Concrètes --- //

    //initialise jeu
    public void init() {
        this.turn = 0;
        try {
            initialiseGame();
        } catch (Exception e) {
            System.out.println("Erreur à l'initialisation du jeu: Java Exception " + e.getMessage());
            System.out.println("Erreur à l'initialisation du jeu, vérifier le nom du fichier labyrinthe");
        }
    }

    //effectue un tour de jeu
    public void step() {
        this.turn = this.turn + 1;
        if (gameContinue()) {
            takeTurn();
            support.firePropertyChange("turn", this.turn - 1, this.turn);
        } else {
            this.isRunning = false;
            gameOver();
        }
    }

    //met jeu en pause
    public void pause() {
        this.isRunning = false;
    }

    //fait tourner le jeu
    public void run() {
        while(this.isRunning) {
            try {
                Thread.sleep(this.time);
            } catch (InterruptedException e) {
                // Auto-generated catch block
                e.printStackTrace();
            }
            step();
        }
    }

    //lance le jeu dans un Thread
    public void launch() {
        this.isRunning = true;
        this.thread = new Thread(this);
        thread.start();                     //lance this.run()
    }
}