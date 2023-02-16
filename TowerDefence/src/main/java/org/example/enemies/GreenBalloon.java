package org.example.enemies;

import org.example.BalloonType;

public class GreenBalloon extends Balloon {
    public GreenBalloon(float x, float y, BalloonType enemyType, int direction) {
        super(x, y, enemyType, direction);
        this.speed = 4.1f;
        this.appearAfter = BalloonType.BLUE;
        this.goldForKill = 8;
    }

}
