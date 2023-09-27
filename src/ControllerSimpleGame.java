public class ControllerSimpleGame extends AbstractController {
    
    Game jeu;
    ViewSimpleGame vueJeu;
    ViewCommand vueCommandes;

    ControllerSimpleGame (SimpleGame jeuSimple) {
        super(jeuSimple);
        this.vueJeu = new ViewSimpleGame(jeuSimple);
        this.vueCommandes = new ViewCommand(jeuSimple, this);
    }

}
