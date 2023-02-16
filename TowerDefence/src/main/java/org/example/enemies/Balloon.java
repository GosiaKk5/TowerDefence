package org.example.enemies;

import org.example.BalloonType;

public abstract class Balloon {
    private float x, y;
    private final BalloonType ballonType;
    private int currentDirection;
    protected float speed;
    protected BalloonType appearAfter;
    protected int goldForKill;
    private boolean isAlive;
    public Balloon(float x, float y, BalloonType balloonType, int startDirection){
        this.x = x;
        this.y = y;
        this.ballonType = balloonType;
        this.currentDirection = startDirection;
        this.isAlive = true;
    }

    public void move(float x, float y){
        this.x += x;
        this.y += y;
    }

    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void setDead(){
        this.isAlive = false;
    }

    public boolean isItAlive(){
        return isAlive;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public BalloonType getBalloonType() {
        return ballonType;
    }
    public int getCurrentDirection(){
        return currentDirection;
    }

    public void setCurrentDirection(int direction){
        this.currentDirection = direction;
    }
    public float getSpeed(){
        return speed;
    }

    public BalloonType getAppearAfter(){
        return appearAfter;
    }

    public int getGoldForKill(){
        return goldForKill;
    }


}
