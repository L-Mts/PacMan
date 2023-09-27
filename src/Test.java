import javax.swing.JLabel;

public class Test {
    public static void main(String[] args) {
        SimpleGame jeu = new SimpleGame(10);

        ViewSimpleGame vueJeu = new ViewSimpleGame(jeu);
        ViewCommand vueCommandes = new ViewCommand(jeu);
    }
        
}
