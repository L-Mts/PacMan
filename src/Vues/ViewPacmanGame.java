package Vues;

import javax.swing.*;

import Games.*;
import Ressources.AgentAction;
import Ressources.Maze;
import Ressources.PanelPacmanGame;
import Ressources.PositionAgent;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;


public class ViewPacmanGame implements PropertyChangeListener {

    PanelPacmanGame panel;

    public ViewPacmanGame (Game jeu) {

        System.out.println(jeu.maze.toString()); // NE TROUVE PAS LE MAZE

        this.panel = new PanelPacmanGame(jeu.maze);

        JFrame pacmanGameView = new JFrame();
        pacmanGameView.setTitle("Game");

        int maze_x = jeu.maze.getSizeX();
        int maze_y = jeu.maze.getSizeY();
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
        jeu.addPropertyChangeListener("turn", this);

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // TODO implémenter ce qui se passe à chaque tour :
        //      - Mouvement de Pacman
        //      - Mouvement des fantômes
        //      - Si Pacman passe sur une case avec nourriture --> mange la nourriture
        //      - Si Pacman passe sur une case avec une capsule --> mange la capsule + ghost_scared
        //      - Si Pacman passe sur une case avec un fantôme --> meurt
        //      - Si Pacman passe sur une case avec un fantôme après avoir mangé capsule --> fantôme meurt
    }

}
