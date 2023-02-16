package org.example;

import org.example.objects.Tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import static org.example.BalloonType.*;

public class ImagesHandler {

    private final Image[] weaponImages;
    private final HashMap<BalloonType, Image> balloonsImg;
    private ArrayList<Tile> tiles;

    public ImagesHandler(){
        this.weaponImages = new Image[5];
        loadWeaponImages();
        this.balloonsImg = new HashMap<>();
        loadBalloondImages();
        this.tiles = new ArrayList<>();
        loadTileImgs();

    }
    private void loadWeaponImages(){
        InputStream giraffeImage = getClass().getResourceAsStream("/giraffe.png");
        InputStream bearImage = getClass().getResourceAsStream("/bear.png");
        InputStream lionImage = getClass().getResourceAsStream("/lion.png");
        InputStream bombImage = getClass().getResourceAsStream("/bomb.png");
        InputStream nailsImage = getClass().getResourceAsStream("/nails.png");
        try{
            this.weaponImages[0] = ImageIO.read(giraffeImage).getScaledInstance(32, 32, Image.SCALE_SMOOTH);
            this.weaponImages[1] = ImageIO.read(bearImage).getScaledInstance(32, 32, Image.SCALE_SMOOTH);
            this.weaponImages[2] = ImageIO.read(lionImage).getScaledInstance(32, 32, Image.SCALE_SMOOTH);
            this.weaponImages[3] = ImageIO.read(nailsImage).getScaledInstance(32, 32, Image.SCALE_SMOOTH);
            this.weaponImages[4] = ImageIO.read(bombImage).getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    private void loadBalloondImages(){
        InputStream blueImage = getClass().getResourceAsStream("/blueBalloon.png");
        try{
            Image blue = ImageIO.read(blueImage).getScaledInstance(32, 32, Image.SCALE_SMOOTH);
            balloonsImg.put(BLUE, blue);
        }catch (IOException e){
            e.printStackTrace();
        }

        InputStream greenImage = getClass().getResourceAsStream("/greenBalloon.png");
        try{
            Image green= ImageIO.read(greenImage).getScaledInstance(32, 32, Image.SCALE_SMOOTH);
            balloonsImg.put(GREEN, green);
        }catch (IOException e){
            e.printStackTrace();
        }

        InputStream redImage = getClass().getResourceAsStream("/redBalloon3.png");
        try{
            Image red = ImageIO.read(redImage).getScaledInstance(32, 32, Image.SCALE_SMOOTH);
            balloonsImg.put(RED, red);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void loadTileImgs(){
        InputStream grass = getClass().getResourceAsStream("/grass.jpg");
        try{
            Image grassImg = ImageIO.read(grass).getScaledInstance(32, 32, Image.SCALE_DEFAULT);
            tiles.add(new Tile(grassImg));
        }catch (IOException e){
            e.printStackTrace();
        }

        InputStream road = getClass().getResourceAsStream("/road2.jpg");
        try{
            Image roadImg = ImageIO.read(road).getScaledInstance(32, 32, Image.SCALE_SMOOTH);
            tiles.add(new Tile(roadImg));
        }catch (IOException e){
            e.printStackTrace();
        }

        InputStream endTile = getClass().getResourceAsStream("/cyrcus.jpg");
        try{
            Image endImage = ImageIO.read(endTile).getScaledInstance(32, 32, Image.SCALE_SMOOTH);
            tiles.add(new Tile(endImage));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public Image[] getWeaponImages(){
        return weaponImages;
    }
    public HashMap<BalloonType, Image> getBalloonsImg(){
        return balloonsImg;
    }

    public ArrayList<Tile> getTiles(){
        return tiles;
    }
}
