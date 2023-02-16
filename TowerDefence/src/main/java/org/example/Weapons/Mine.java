package org.example.Weapons;

public class Mine{

    private boolean used;

    private final int x;
    private final int y;
    private final int type;
    private final int range;
    public Mine(int x, int y, int type) {
        this.used = false;
        this.range = 33;
        this.x = x;
        this.y = y;
        this.type = type;
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
    public int getRange(){
        return range;
    }
}
