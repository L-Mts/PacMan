import Controlleurs.*;
import Games.*;

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

        ControllerPacmanGame control = new ControllerPacmanGame(jeu); //, new PanelPacmanGame(null)));


    }
        
}
