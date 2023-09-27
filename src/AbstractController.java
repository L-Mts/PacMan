public abstract class AbstractController {

    // --- Attributs --- //
    Game jeu;

    // --- Constructeur --- //

    AbstractController (Game jeu) {
        this.jeu = jeu;
    }

     // --- Méthodes --- //

    // arrêt et réinitialisation
    public void restart () {
        System.out.println("Restart Game\n");
        this.jeu.pause();
        this.jeu.init();
    }

    // passage manuel d'une étape
    public void step () {
        System.out.println("Passage à l'étape suivante\n");
        this.jeu.step();
    }

    // passage automatique des étapes
    public void play () {
        System.out.println("Mode automatique du passage des étapes\n");
        this.jeu.launch();
    }

    // interruption du passage automatique des étapes
    public void pause () {
        System.out.println("Jeu en pause\n");
        this.jeu.pause();
    }

    // réglage de la vitesse du jeu
    // !attention! conversion à faire pour régler temps de pause entre chaque tour de jeu
    public void setSpeed (double speed) {
        long speedLong = (long) speed;
        System.out.println("La vitesse a été réglée sur " + speedLong + "\n");
        this.jeu.time = 1000 / speedLong;
    }
    
}
