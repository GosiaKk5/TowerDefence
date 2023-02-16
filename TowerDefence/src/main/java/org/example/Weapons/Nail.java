package org.example.Weapons;

public class Nail{

    private boolean used;
    private final int x;
    private final int y;
    private final int type;
    public Nail(int x, int y, int type) {
        used = false;
        this.type = type;
        this.x = x;
        this.y = y;
    }

    public boolean isUsed(){
        return used;
    }

    public void setUsed(){
        used = true;
    }

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public int getType(){
        return type;
    }

}
