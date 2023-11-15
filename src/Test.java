import Controlleurs.*;
import Games.*;
import Ressources.PanelPacmanGame;
import Vues.ViewCommand;
import Vues.ViewPacmanGame;


public class Test {
    public static void main(String[] args) throws Exception {

        /**
         * TEST SIMPLEGAME !
         */
         
        /**
         * ControllerSimpleGame control = new ControllerSimpleGame(new SimpleGame(10));
         */

         /**
          * TEST PACMANGAMEVIEW
          */

        PacmanGame jeu = new PacmanGame(10);


        ControleurPacmanGame control = new ControleurPacmanGame(jeu); //, new PanelPacmanGame(null)));


    }
        
}
