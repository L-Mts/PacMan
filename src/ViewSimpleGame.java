import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ViewSimpleGame implements PropertyChangeListener {

    JLabel label;

    // --- Constructeur --- //

    public ViewSimpleGame (JLabel label, SimpleGame simpleGame) {
    
        JFrame simpleGameView = new JFrame();
        simpleGameView.setTitle("Game");
        simpleGameView.setSize(new Dimension(700, 700));
        Dimension windowSize = simpleGameView.getSize();
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point centerPoint = ge.getCenterPoint();
        int dx = centerPoint.x - windowSize.width / 2 ;
        int dy = centerPoint.y - windowSize.height / 2 - 350;
        simpleGameView.setLocation(dx, dy);

        this.label = label;
        simpleGameView.add(label);

        simpleGameView.setVisible(true);

        // Déclaration observateur de "turn"
        simpleGame.addPropertyChangeListener("turn", this);

    }

    // --- Observateur --- //

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        this.label.setText("Current turn: " + evt.getNewValue().toString());
    }

}


