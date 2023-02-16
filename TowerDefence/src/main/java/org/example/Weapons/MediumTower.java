package org.example.Weapons;

public class MediumTower extends Tower{

    public MediumTower(int x, int y, int type) {
        super(x, y, type);
        this.range = 75;
        this.bulletsPerShot = 2;
        this.frequency = 2000;
        this.name = "Bear";
        this.upgradeCost = 100;
    }
}
