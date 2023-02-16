package org.example.enemies;

import org.example.BalloonType;

public class BlueBalloon extends Balloon {


    public BlueBalloon(float x, float y, BalloonType enemyType, int direction) {
        super(x, y, enemyType, direction);
        this.speed = 2f;
        this.appearAfter = BalloonType.RED;
        this.goldForKill = 6;
    }

}
