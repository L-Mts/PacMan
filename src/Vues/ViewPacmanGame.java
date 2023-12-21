package Vues;

import javax.swing.*;

import Agents.*;
import Games.*;
import Ressources.*;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;


public class ViewPacmanGame implements PropertyChangeListener {

    PanelPacmanGame panel;
    PacmanGame jeu;
    JFrame pacmanGameView;

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
        this.pacmanGameView.add(this.panel);
        
        this.pacmanGameView.setVisible(true);
        
        // Observateur de "turn"
        this.jeu.addPropertyChangeListener("turn", this);

    }

    // Méthode UPDATE
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // TODO implémenter ce qui se passe à chaque tour :
        //      - Mouvement de Pacman
        //      - Mouvement des fantômes
        //      - Si Pacman passe sur une case avec nourriture --> mange la nourriture
        //      - Si Pacman passe sur une case avec une capsule --> mange la capsule + ghost_scared
        //      - Si Pacman passe sur une case avec un fantôme --> meurt
        //      - Si Pacman passe sur une case avec un fantôme après avoir mangé capsule --> fantôme meurt

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

        this.panel.repaint();

        if (this.jeu.getCapsuleCompteur() == 0) {

            for(PositionAgent posPacman : pacman_pos) {
                for (PositionAgent posGhost : ghost_pos) {
                    if (posPacman.getX()==posGhost.getX() && posPacman.getY()==posGhost.getY()) this.gameOver();
                }
            }
            
            for (AbstractAgent e : this.jeu.getListe_agents()) {
                if (e instanceof AgentPacman) {
                    PositionAgent lastPos = e.getLastPos();
                    for (PositionAgent posGhost : ghost_pos) {
                        if (lastPos.getX()==posGhost.getX() && lastPos.getY()==posGhost.getY()) this.gameOver();
                    }
                }
            }
        }
    }


    public void gameOver() {

        this.jeu.setContinue(false);

        this.pacmanGameView.setVisible(false);
        
        JFrame viewGameOver = new JFrame();
        viewGameOver.setTitle("Game");

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

        JLabel label = new JLabel ("GAME OVER !!!", JLabel.CENTER);
        label.setForeground(Color.RED);
        viewGameOver.add(label);

        viewGameOver.setVisible(true);

    }

}
