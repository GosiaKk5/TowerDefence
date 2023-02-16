package org.example;

import java.awt.*;

public class Render {

    private final Main game;

    public Render(Main game){
        this.game = game;
    }

    public void render(Graphics g){
        switch (GameStates.gameState) {
            case MENU -> game.getMenu().render(g);
            case PLAYING -> game.getPlaying().render(g);
        }
    }

}
