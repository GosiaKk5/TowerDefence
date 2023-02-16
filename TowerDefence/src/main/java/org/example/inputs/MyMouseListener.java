package org.example.inputs;

import org.example.GameStates;
import org.example.Main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MyMouseListener implements MouseListener, MouseMotionListener {
    private final Main game;
    public MyMouseListener(Main game){
        this.game = game;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1){
            switch (GameStates.gameState) {
                case MENU -> game.getMenu().mouseClicked(e.getX(), e.getY());
                case PLAYING -> game.getPlaying().mouseClicked(e.getX(), e.getY());
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        switch (GameStates.gameState) {
            case MENU -> game.getMenu().mouseMoved(e.getX(), e.getY());
            case PLAYING -> game.getPlaying().mouseMoved(e.getX(), e.getY());
        }

    }
}
