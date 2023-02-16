package org.example.managers;

import org.example.objects.Tile;
import org.example.scenes.Playing;

import java.awt.*;
import java.util.ArrayList;

public class TileManager {
    public ArrayList<Tile> tiles;

    public TileManager(Playing playing){
        tiles = playing.getImagesHandler().getTiles();
    }

    public Image getTileImage(int id){
        return tiles.get(id).getImage();
    }


}
