package Controlleurs;

import Games.*;
import Vues.*;

public class ControleurPacmanGame extends AbstractController {

    public ViewPacmanGame vueJeu;
    public ViewCommand vueCommandes;

    public ControleurPacmanGame (Game jeu) throws Exception {
        super(jeu);
        
        System.out.println(this.jeu.maze);
        this.vueJeu = new ViewPacmanGame(jeu);
        this.vueCommandes = new ViewCommand(jeu, this);
    }

    
}
