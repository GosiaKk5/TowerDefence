package org.example.objects;

import java.awt.*;

public class Tile {
    private final Image image;

    public Tile(Image image){
        this.image = image;
    }

    public Image getImage(){
        return this.image;
    }

}
