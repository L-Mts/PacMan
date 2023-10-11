import Controlleurs.*;
import Games.*;
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

        Game jeu = new PacmanGame(5);
        ViewPacmanGame view = new ViewPacmanGame(jeu);


    }
        
}
