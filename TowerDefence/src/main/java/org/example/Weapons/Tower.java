package org.example.Weapons;

public abstract class Tower {
    private final int x;
    private final int y;
    private final int type;
    protected int range;
    protected int frequency;
    protected int bulletsPerShot;
    protected String name;
    private long lastShootTime;
    private int bulletsLeft;
    private int lvl;
    protected double upgradeBonus;
    protected int upgradeCost;
    public Tower(int x, int y, int type){
        this.x = x;
        this.y = y;
        this.type = type;
        this.lastShootTime = 0;
        this.lvl = 1;
        this.upgradeBonus = 0.7;
    }

    public void shotDone(){
        lastShootTime = System.currentTimeMillis();
        bulletsLeft--;
    }

    public void upgradeTower(){
        if(lvl < 5){
            this.lvl++;
            this.frequency *= upgradeBonus;
            this.upgradeCost += 15;
        }
    }

    public boolean isReadyToShoot(){
        if(bulletsLeft > 0){
            return true;
        }
        if(System.currentTimeMillis() - lastShootTime >= getFrequency()){
            bulletsLeft = bulletsPerShot;
            return true;
        }
        return false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getType() {
        return type;
    }

    public int getRange(){
        return range;
    }

    public int getFrequency(){
        return frequency;
    }

    public String getTowerName(){
        return name;
    }

    public int getLvl(){
        return lvl;
    }

    public int getUpgradeCost(){
        return upgradeCost;
    }
}


