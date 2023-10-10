package Controlleurs;

import Games.*;
import Vues.*;

public class ControleurPacmanGame extends AbstractController {

    //TODO : changer tout (copier coller du ControllerSimpleGame)

    public Game jeu;
    public ViewSimpleGame vueJeu;
    public ViewCommand vueCommandes;

    public ControleurPacmanGame (SimpleGame jeuSimple) {
        super(jeuSimple);
        this.vueJeu = new ViewSimpleGame(jeuSimple);
        this.vueCommandes = new ViewCommand(jeuSimple, this);
    }

    
}
