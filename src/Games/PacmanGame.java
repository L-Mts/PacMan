package Games;

public class PacmanGame extends Game {

    public PacmanGame(int maxturn) {
        super(maxturn);
    }

    // --- Méthodes Héritées de Game --- //

    @Override
    public void initialiseGame() {
        System.out.println("Le jeu est initialisé");
    }

    @Override
    public void takeTurn() {
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
    
}
