import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;

public class SimpleGame extends Game {

    // --- Constructeur --- //

    SimpleGame (int maxturn) {
        super(maxturn);
        
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

    // --- Méthodes héritées de Game --- //

    @Override
    public void initialiseGame() {
        System.out.println("Le jeu est initialisé\n");
    }

    @Override
    public void takeTurn() {
        support.firePropertyChange("turn", this.turn - 1, this.turn);
        System.out.println("Tour " + this.turn + " du jeu en cours\n");
    }

    @Override
    public boolean gameContinue() {
        return true;
    }

    @Override
    public void gameOver() {
        System.out.println("Le jeu est terminé");
    }
    
    
}
