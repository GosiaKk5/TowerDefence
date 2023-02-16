package org.example.Weapons;

public class BasicTower extends Tower{
    public BasicTower(int x, int y, int type) {
        super(x, y, type);
        this.range = 100;
        this.bulletsPerShot = 1;
        this.frequency = 3000;
        this.name = "Giraffe";
        this.upgradeCost = 75;
    }
}
