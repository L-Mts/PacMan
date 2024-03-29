package Vues;

import javax.swing.*;

import Agents.*;
import Games.*;
import Ressources.*;
import Strategies.StrategieInteractive;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;


public class ViewPacmanGame implements PropertyChangeListener {

    PanelPacmanGame panel;
    PacmanGame jeu;
    JFrame pacmanGameView;

    JLabel nbrPoints = new JLabel("Points : 0", JLabel.CENTER);
    JLabel nbrLifes = new JLabel( "Lifes : 3", JLabel.CENTER);

    public ViewPacmanGame (PacmanGame jeu) {

        this.jeu = jeu;

        this.panel = new PanelPacmanGame(this.jeu.maze);

        this.pacmanGameView = new JFrame();
        this.pacmanGameView.setTitle("Game");

        int maze_x = this.jeu.maze.getSizeX();
        int maze_y = this.jeu.maze.getSizeY();
        this.pacmanGameView.setSize(new Dimension(maze_x*30,maze_y*30));
        Dimension windowSize = this.pacmanGameView.getSize();

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point centerPoint = ge.getCenterPoint();
        int dx = centerPoint.x - windowSize.width / 2 ;
        int dy = centerPoint.y - windowSize.height / 2;
        this.pacmanGameView.setLocation(dx, dy-75);

        this.pacmanGameView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Compte les points & le nombre de vies
        JPanel panelTop = new JPanel(new GridLayout(1, 3));
        panelTop.setBackground(new Color(52, 152, 219));

        this.nbrPoints.setForeground(Color.WHITE);
        this.nbrLifes.setForeground(Color.WHITE);

        panelTop.add(this.nbrPoints);
        panelTop.add(this.nbrLifes);
        

        this.pacmanGameView.add(panelTop, BorderLayout.NORTH);        

        this.pacmanGameView.add(this.panel);

        this.pacmanGameView.setVisible(true);
        
        // Observateur de "turn"
        this.jeu.addPropertyChangeListener("turn", this);

        pacmanGameView.addKeyListener(new KeyAdapter() {
            StrategieInteractive stratPacmanInteractif = (StrategieInteractive) jeu.getPacmanInteractif().getStrategie();
            // 0: HAUT / 1: BAS / 2: DROITE / 3: GAUCHE / 4: STOP
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_UP) {
                    stratPacmanInteractif.setDirection(0);
                }
                else if (keyCode == KeyEvent.VK_DOWN) {
                    stratPacmanInteractif.setDirection(1);
                }
                else if (keyCode == KeyEvent.VK_LEFT) {
                    stratPacmanInteractif.setDirection(3);
                }
                else if (keyCode == KeyEvent.VK_RIGHT) {
                    stratPacmanInteractif.setDirection(2);
                }
            }
        });

    }

    // Méthode UPDATE
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (this.jeu.getEnding() == -1) {

            ArrayList<PositionAgent> pacman_pos = new ArrayList<PositionAgent>();
            ArrayList<PositionAgent> ghost_pos = new ArrayList<PositionAgent>();

            for (AbstractAgent e : this.jeu.getListe_agents()) {
                if (e instanceof AgentPacman) {
                    pacman_pos.add(e.getPos());
                } else {
                    ghost_pos.add(e.getPos());
                }
            }

            this.panel.setPacmans_pos(pacman_pos);
            this.panel.setGhosts_pos(ghost_pos);       

            if (this.jeu.getCapsuleCompteur()==20 && this.panel.getGhostsScarred()==false) this.panel.setGhostsScarred(true);
            if (this.jeu.getCapsuleCompteur()==0 && this.panel.getGhostsScarred()==true) this.panel.setGhostsScarred(false);

            this.nbrPoints.setText("Points : " + this.jeu.getPoints());
            this.nbrLifes.setText("Lifes : " + this.jeu.getLifes());

            this.panel.repaint();

        } else {
            this.gameOver();
        }

    }


    public void gameOver() {

        this.pacmanGameView.setVisible(false);
        
        JFrame viewGameOver = new JFrame();
        viewGameOver.setTitle("End Game");

        int maze_x = this.jeu.maze.getSizeX();
        int maze_y = this.jeu.maze.getSizeY();
        viewGameOver.setSize(new Dimension(maze_x*30,maze_y*30));
        Dimension windowSize = viewGameOver.getSize();


        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point centerPoint = ge.getCenterPoint();
        int dx = centerPoint.x - windowSize.width / 2 ;
        int dy = centerPoint.y - windowSize.height / 2;
        viewGameOver.setLocation(dx, dy-75);

        viewGameOver.getContentPane().setBackground(Color.BLACK);
        
        JLabel label = new JLabel();
        label.setHorizontalAlignment(JLabel.CENTER);
        
        if (this.jeu.getEnding() == 0) {
            label.setText("<html><center>GAME OVER !!! <br/>Points : " + this.jeu.pointsToString() + "</center></html>");
            label.setForeground(Color.RED);
        } else {
            label.setText("<html><center>YOU WIN !!! <br/>Points : " + this.jeu.pointsToString() + "</center></html>");
            label.setForeground(Color.GREEN);
        }

        viewGameOver.add(label);

        viewGameOver.setVisible(true);

    }

}
