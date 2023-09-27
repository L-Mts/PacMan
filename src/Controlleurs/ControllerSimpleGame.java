package Controlleurs;

import Games.*;
import Vues.*;

public class ControllerSimpleGame extends AbstractController {
    
    public Game jeu;
    public ViewSimpleGame vueJeu;
    public ViewCommand vueCommandes;

    public ControllerSimpleGame (SimpleGame jeuSimple) {
        super(jeuSimple);
        this.vueJeu = new ViewSimpleGame(jeuSimple);
        this.vueCommandes = new ViewCommand(jeuSimple, this);
    }

}
