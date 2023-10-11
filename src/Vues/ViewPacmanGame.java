package Vues;

import javax.swing.*;

import Games.*;
import Ressources.Maze;
import Ressources.PanelPacmanGame;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class ViewPacmanGame implements PropertyChangeListener {

    public ViewPacmanGame (Game jeu) throws Exception {

        Maze maze = new Maze("layouts/testMaze.lay");
        PanelPacmanGame panel = new PanelPacmanGame(maze);

        JFrame pacmanGameView = new JFrame();
        pacmanGameView.setTitle("Game");

        int maze_x = maze.getSizeX();
        int maze_y = maze.getSizeY();
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'propertyChange'");
    }

}
