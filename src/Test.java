import javax.swing.JLabel;

public class Test {
    public static void main(String[] args) {
        SimpleGame jeu = new SimpleGame();

        jeu.turn = 0;
        jeu.maxturn = 10;

        ViewSimpleGame vueJeu = new ViewSimpleGame(new JLabel ("Current Turn : 0", JLabel.CENTER), jeu);
        ViewCommand vueCommandes = new ViewCommand(new JLabel ("Current Turn : 0", JLabel.CENTER), jeu);

        jeu.init();
        jeu.run();
    }
        
}
