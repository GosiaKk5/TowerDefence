package org.example.Weapons;

public class AdvancedTower extends Tower{

    public AdvancedTower(int x, int y, int type) {
        super(x, y, type);
        this.range = 50;
        this.bulletsPerShot = 3;
        this.frequency = 1000;
        this.name = "Lion";
        this.upgradeCost = 150;
    }
}
