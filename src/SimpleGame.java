public class SimpleGame extends Game {

    // --- Constructeur --- //

    SimpleGame (int maxturn) {
        super(maxturn);  
    }

    // --- Méthodes héritées de Game --- //

    @Override
    public void initialiseGame() {
        System.out.println("Le jeu est initialisé\n");
    }

    @Override
    public void takeTurn() {
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
