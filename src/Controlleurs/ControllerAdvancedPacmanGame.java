package Controlleurs;

import Games.*;
import Vues.*;

public class ControllerAdvancedPacmanGame extends AbstractController {


    public ViewPacmanGame vueJeu;
    public ViewCommand vueCommandes;

    public ControllerAdvancedPacmanGame (PacmanGame jeu) throws Exception {
        super(jeu);

        this.vueJeu = new ViewPacmanGame(jeu);
        this.vueCommandes = new ViewCommand(jeu, this);
    }

    @Override
    public void restart () {
        System.out.println("Restart Game\n");
        this.jeu.pause();
        this.jeu.init();
        this.vueJeu = new ViewPacmanGame((PacmanGame) jeu);
    }

    
}