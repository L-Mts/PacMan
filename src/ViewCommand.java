import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ViewCommand implements PropertyChangeListener {

    JLabel labelTurns;

    // --- Constructeur --- //
    
    public ViewCommand (JLabel labelTurns, SimpleGame simpleGame) {

        JFrame commandsView = new JFrame();
        commandsView.setTitle("Commands");
        commandsView.setSize(new Dimension(700, 250));
        Dimension windowSize = commandsView.getSize();
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point centerPoint = ge.getCenterPoint();
        int dx = centerPoint.x - windowSize.width / 2 ;
        int dy = centerPoint.y - windowSize.height / 2 + 350;
        commandsView.setLocation(dx, dy);

        JPanel panelMain = new JPanel (new GridLayout(2,1));
        JPanel panelTop = new JPanel(new GridLayout(1,4));
        JPanel panelBottom = new JPanel (new GridLayout(1,2));
        JPanel panelBottom1 = new JPanel(new GridLayout(2,1));

        // Icons
        Icon restartIcon = new ImageIcon("icons/icon_restart.png");
        Icon pauseIcon = new ImageIcon("icons/icon_pause.png");
        Icon runIcon = new ImageIcon("icons/icon_run.png");
        Icon stepIcon = new ImageIcon("icons/icon_step.png");
        
        //Buttons
        JButton restartButton = new JButton(restartIcon);
        JButton pauseButton = new JButton(pauseIcon);
        JButton runButton = new JButton(runIcon);
        JButton stepButton = new JButton(stepIcon);

        // Slider and Label
        JSlider slider = new JSlider(JSlider.HORIZONTAL, 1, 10, 2);
        slider.setMajorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        JLabel sliderLabel = new JLabel ("Number of turns per second", JLabel.CENTER);
        this.labelTurns = labelTurns;

        commandsView.add(panelMain);

        panelMain.add(panelTop);
        panelTop.add(restartButton);
        panelTop.add(runButton);
        panelTop.add(pauseButton);
        panelTop.add(stepButton);

        panelMain.add(panelBottom);
        panelBottom1.add(sliderLabel);
        panelBottom1.add(slider);
        panelBottom.add(panelBottom1);
        panelBottom.add(labelTurns);

        commandsView.setVisible(true);

        // Déclaration observateur de "turn"
        simpleGame.addPropertyChangeListener("turn", this);

    }

    // --- Observateur --- //

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        this.labelTurns.setText("Current turn: " + evt.getNewValue().toString());
    }
    
}
