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

    public ViewPacmanGame (PacmanGame jeu) {

        this.jeu = jeu;

        this.panel = new PanelPacmanGame(this.jeu.maze);

        JFrame pacmanGameView = new JFrame();
        pacmanGameView.setTitle("Game");

        int maze_x = this.jeu.maze.getSizeX();
        int maze_y = this.jeu.maze.getSizeY();
        pacmanGameView.setSize(new Dimension(maze_x*30,maze_y*30));
        Dimension windowSize = pacmanGameView.getSize();

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point centerPoint = ge.getCenterPoint();
        int dx = centerPoint.x - windowSize.width / 2 ;
        int dy = centerPoint.y - windowSize.height / 2;
        pacmanGameView.setLocation(dx, dy);

        pacmanGameView.add(panel);
        
        pacmanGameView.setVisible(true);
        
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

        panel.setPacmans_pos(pacman_pos);
        panel.setGhosts_pos(ghost_pos);
        

        panel.repaint();
    }

}
