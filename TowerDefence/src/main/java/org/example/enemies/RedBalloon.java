package org.example.enemies;

import org.example.BalloonType;

public class RedBalloon extends Balloon {

    public RedBalloon(float x, float y, BalloonType enemyType, int direction) {
        super(x, y, enemyType, direction);
        this.speed = 1f;
        this.appearAfter = null;
        this.goldForKill = 5;
    }

}
