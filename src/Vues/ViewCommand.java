package Vues;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Controllers.*;
import States.*;
import Games.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ViewCommand implements PropertyChangeListener {

    private JLabel labelTurns;
    private State state;

    // --- Constructeur --- //
    
    public ViewCommand (Game jeu, AbstractController controller) {

        JFrame commandsView = new JFrame();
        commandsView.setTitle("Commands");
        commandsView.setSize(new Dimension(500, 200));
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
        JSlider slider = new JSlider(JSlider.HORIZONTAL, 1, 10, 1);
        slider.setMajorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        JLabel sliderLabel = new JLabel ("Number of turns per second", JLabel.CENTER);
        this.labelTurns = new JLabel("Current Turn : 0", JLabel.CENTER);

        //Ajout panelMain à la JFrame
        commandsView.add(panelMain);

        //Ajout des éléments dans les panels
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

        // Ajout Etat à la vue
        this.state = new StateInitial();

        // Déclaration observateur de "turn"
        jeu.addPropertyChangeListener("turn", this);

        // Déclaration des observateurs des boutons
        // Appel des fonctions du controlleur à effectuer
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!state.LockRestartButton()) {
                    controller.restart();
                    state = new StateInitial();
                }
            }
        });

        runButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!state.LockPlayButton()){
                    controller.play();
                    state = new StateRunning();
                    if (controller instanceof ControllerPacmanGame) {
                        ((ControllerPacmanGame)controller).vueJeu.pacmanGameView.toFront();
                        ((ControllerPacmanGame)controller).vueJeu.pacmanGameView.requestFocus();
                    }
                }  
            }
        });

        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!state.LockPauseButton()){
                    controller.pause();
                    state = new StatePause();
                }
            }
        });

        stepButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!state.LockStepButton()) {
                    controller.step();
                    state = new StatePause();
                }
            }
        });

        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                double speed = slider.getValue();
                controller.setSpeed(speed);
            }
        });
    }

    // --- Observateur --- //

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        this.labelTurns.setText("Current turn: " + evt.getNewValue().toString());
    }
    
}
