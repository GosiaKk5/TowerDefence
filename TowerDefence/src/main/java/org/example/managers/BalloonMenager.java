package org.example.managers;

import org.example.BalloonType;
import org.example.Vector2D;
import org.example.enemies.BlueBalloon;
import org.example.enemies.Balloon;
import org.example.enemies.GreenBalloon;
import org.example.enemies.RedBalloon;
import org.example.scenes.Playing;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import static org.example.BalloonType.*;

public class BalloonMenager {
    static final int NO_NEW_BALLOONS = 4;

    private final Playing playing;
    private final ArrayList<Balloon> enemies;
    private final Vector2D start;
    private final Vector2D end;
    private final ArrayList<Balloon> balloonsToKill;
    private final ArrayList<Balloon> balloonsToAdd;

    private final HashMap<BalloonType, Image> balloonsImg;


    public BalloonMenager(Playing playing, Vector2D start, Vector2D end){
        this.playing = playing;
        this.start = start;
        this.end = end;
        this.balloonsToKill = new ArrayList<>();
        this.balloonsToAdd = new ArrayList<>();
        this.enemies = new ArrayList<>();
        this.balloonsImg = playing.getImagesHandler().getBalloonsImg();
    }

    public void reset(){
        this.balloonsToKill.clear();
        balloonsToAdd.clear();
        this.enemies.clear();
    }
    public void update(){
        addNewBalloonsFromKilling();
        addAllNewBalloons();
        removeDeadBalloons();

        for(Balloon e : enemies){
            if(isNextTileRoad(e, e.getCurrentDirection())){
                e.move(SpeedX(e.getCurrentDirection(),e),SpeedY(e.getCurrentDirection(), e));
            }else if(isBalloonWin(e) && e.isItAlive()) {
                playing.lifeLost();
                e.setDead();
            }else{
                changeDirection(e);
            }
        }
    }


    private Image getBalloonImage(BalloonType type){
        return balloonsImg.get(type);
    }

    public boolean isThereMoreBalloonsToAdd(){
        if(balloonsToKill.size() == 0){
            return true;
        }
        for(Balloon e: balloonsToKill){
            if(e.getBalloonType() != RED){
                return false;
            }
        }
        return true;
    }
    private boolean isBalloonWin(Balloon e){
        return end.x <= (int) e.getX() && end.y <= (int) e.getY();
    }

    private void changeDirection(Balloon e){
        fixBalloonPosition(e);
        if(e.getCurrentDirection()%2 == 0){
            if(isNextTileRoad(e, 1)){
                e.setCurrentDirection(1);
            }else if(isNextTileRoad(e, 3)) {
                e.setCurrentDirection(3);
            }
        }else if(e.getCurrentDirection()%2 == 1){
            if(isNextTileRoad(e, 0)){
                e.setCurrentDirection(0);
            }else if(isNextTileRoad(e, 2)) {
                e.setCurrentDirection(2);
            }
        }
        e.move(SpeedX(e.getCurrentDirection(), e),SpeedY(e.getCurrentDirection(), e));
    }

    private void fixBalloonPosition(Balloon e){
        int x = (int)(e.getX()/32.0);
        int y = (int)(e.getY()/32.0);
        int direction = e.getCurrentDirection();
        if(direction == 1 && x <19){
            x++;
        } else if(direction == 2 && y <19) {
            y++;
        }
        e.setPosition(x*32,y*32);
    }

    private boolean isNextTileRoad(Balloon e, int direction){
        float newX = e.getX() + SpeedX(direction, e);
        float newY = e.getY() + SpeedY(direction, e);

        if(direction == 1){
            newX += 32;
        }else if(direction == 2){
            newY += 32;
        }

        if(newX < 640 && newY < 640 && newX > 0 && newY > 0){
            return playing.isItRoad((int) (newX),(int)(newY));
        }
        return false;
    }

    private float SpeedX(int direction, Balloon e){
        if(direction == 1){
            return e.getSpeed();
        }else if (direction == 3){
            return -e.getSpeed();
        }else{
            return 0;
        }
    }
    private float SpeedY(int direction, Balloon e){
        if(direction == 2){
            return e.getSpeed();
        }else if (direction == 0){
            return -e.getSpeed();
        }else{
            return 0;
        }
    }

    public void addBalloon(Vector2D position, BalloonType balloonType, int direction){
        int x = position.x;
        int y = position.y;

        switch (balloonType){
            case RED -> this.balloonsToAdd.add(new RedBalloon(x, y, RED, direction));
            case BLUE -> this.balloonsToAdd.add(new BlueBalloon(x, y, BalloonType.BLUE, direction));
            case GREEN -> this.balloonsToAdd.add(new GreenBalloon(x, y, BalloonType.GREEN, direction));
            default -> throw new IllegalStateException("Unexpected value: " + balloonType);
        }
    }

    public void killBalloon(Balloon balloon){
        balloonsToKill.add(balloon);
    }

    private void removeDeadBalloons(){
        ArrayList<Balloon> toRemove = new ArrayList<>();
        for(Balloon e: enemies){
            if(!e.isItAlive()){
                toRemove.add(e);
            }
        }
        enemies.removeAll(toRemove);
        balloonsToKill.clear();
    }

    private void addNewBalloonsFromKilling(){
        for(Balloon e: balloonsToKill){
            if(e.getBalloonType() != RED){
                for(int i = 0; i < NO_NEW_BALLOONS; i++){
                    addBalloon(new Vector2D((int)e.getX(), (int)e.getY()), e.getAppearAfter(), e.getCurrentDirection());
                }
            }
        }
    }
    private void addAllNewBalloons(){
        enemies.addAll(balloonsToAdd);
        balloonsToAdd.clear();
    }
    public void draw(Graphics g){
        ArrayList<Balloon> copy = new ArrayList<>(enemies);
        for(Balloon e : copy){
            drawBalloon(e, g);
        }
    }

    private void drawBalloon(Balloon e, Graphics g){
        g.drawImage(getBalloonImage(e.getBalloonType()), (int)e.getX(), (int)e.getY(), null);
    }

    public ArrayList<Balloon> getEnemies(){
        return enemies;
    }
}
