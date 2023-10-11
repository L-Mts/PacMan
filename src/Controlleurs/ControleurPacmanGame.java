package Controlleurs;

import Games.*;
import Vues.*;

public class ControleurPacmanGame extends AbstractController {

    public Game jeu;
    public ViewPacmanGame vueJeu;
    public ViewCommand vueCommandes;

    public ControleurPacmanGame (Game jeu) throws Exception {
        super(jeu);
        this.vueJeu = new ViewPacmanGame(jeu);
        this.vueCommandes = new ViewCommand(jeu, this);
    }

    
}
