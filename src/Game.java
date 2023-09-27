import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.lang.Runnable;

public abstract class Game implements Runnable {

    // --- Attributs --- //
    
    //compteur nbr tours
    public int turn;

    //max nobr tours
    //pré-défini via constructeur de Game
    public int maxturn;

    //vérifie mise en pause ou non
    public boolean isRunning;

    Thread thread;

    long time;

    // --- Constructeur --- //

    Game(int maxturn) {
        this.maxturn = maxturn;
        this.isRunning = false;
        this.time = 1000;
        this.init();
    }

    // --- Observé --- //

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    
    public void addPropertyChangeListener(String property, PropertyChangeListener listener) {
        support.addPropertyChangeListener( property, listener);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    
    public void removePropertyChangeListener(String property, PropertyChangeListener listener) {
        support.removePropertyChangeListener(property, listener);
    }



    // --- Méthodes Abstraites --- //

    public abstract void initialiseGame();
    public abstract void takeTurn();
    public abstract boolean gameContinue();
    public abstract void gameOver();


    // --- Méthodes Concrètes --- //

    //initialise jeu
    public void init() {
        this.turn = 0;
        initialiseGame();
    }

    //effectue un tour de jeu
    public void step() {
        this.turn = this.turn + 1;
        if (gameContinue()) {
            support.firePropertyChange("turn", this.turn - 1, this.turn);
            takeTurn();
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
                // TODO Auto-generated catch block
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