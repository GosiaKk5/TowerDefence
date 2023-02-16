package org.example.Events;

import org.example.BalloonType;

import java.util.ArrayList;

public class Wave {
    private ArrayList<BalloonType> balloonList;

    public Wave(ArrayList<BalloonType> enemyList){
        this.balloonList= enemyList;
    }

    public ArrayList<BalloonType> getEnemyList(){
        return balloonList;
    }
}
