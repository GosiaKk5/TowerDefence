package org.example.scenes;

import org.example.ImagesHandler;
import org.example.Main;

public class GameScene {

    protected Main game;
    protected ImagesHandler imagesHandler;

    public GameScene(Main game){
        this.game = game;
        this.imagesHandler = new ImagesHandler();
    }

}
