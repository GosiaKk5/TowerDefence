package org.example.managers;

import org.example.BalloonType;
import org.example.Events.Wave;

import java.util.ArrayList;
import java.util.Arrays;
public class WaveManager {
    private final ArrayList<Wave> waves;
    private final int enemySpawnTickLimit;
    private int enemySpawnTick;
    private int enemyIndex;
    private int waveIndex;

    public WaveManager() {
        this.enemySpawnTickLimit = 60;
        this.enemySpawnTick = enemySpawnTickLimit;
        this.enemyIndex = 0;
        this.waveIndex = 0;
        this.waves = new ArrayList<>();
        createWaves();
    }

    public void reset(){
        enemyIndex = 0;
        waveIndex = 0;
    }

    public void update(){
        if(enemySpawnTick < enemySpawnTickLimit){
            enemySpawnTick++;
        }
    }

    public BalloonType getNextBalloonType(){
        enemySpawnTick = 0;
        return waves.get(waveIndex).getEnemyList().get(enemyIndex++);
    }

    private void createWaves(){
        ArrayList<BalloonType> wave = new ArrayList<>(Arrays.asList(BalloonType.RED, BalloonType.RED, BalloonType.RED, BalloonType.RED));
        ArrayList<BalloonType> wave2 = new ArrayList<>(Arrays.asList(BalloonType.BLUE, BalloonType.RED, BalloonType.RED, BalloonType.RED));
        ArrayList<BalloonType> wave3 = new ArrayList<>(Arrays.asList(BalloonType.BLUE, BalloonType.BLUE, BalloonType.BLUE));
        ArrayList<BalloonType> wave4 = new ArrayList<>(Arrays.asList(BalloonType.RED, BalloonType.BLUE, BalloonType.RED,BalloonType.GREEN));
        ArrayList<BalloonType> wave5 = new ArrayList<>(Arrays.asList( BalloonType.BLUE, BalloonType.GREEN, BalloonType.RED, BalloonType.GREEN, BalloonType.RED));
        ArrayList<BalloonType> wave6 = new ArrayList<>(Arrays.asList(BalloonType.BLUE, BalloonType.GREEN,BalloonType.GREEN, BalloonType.GREEN,  BalloonType.RED));
        ArrayList<BalloonType> wave7 = new ArrayList<>(Arrays.asList(BalloonType.RED,BalloonType.GREEN, BalloonType.GREEN,BalloonType.RED, BalloonType.GREEN, BalloonType.GREEN, BalloonType.GREEN, BalloonType.BLUE, BalloonType.RED, BalloonType.RED));
        ArrayList<BalloonType> wave8 = new ArrayList<>(Arrays.asList(BalloonType.BLUE, BalloonType.GREEN, BalloonType.GREEN, BalloonType.GREEN, BalloonType.GREEN, BalloonType.GREEN, BalloonType.GREEN, BalloonType.BLUE, BalloonType.BLUE, BalloonType.BLUE,BalloonType.GREEN));
        ArrayList<BalloonType> wave9 = new ArrayList<>(Arrays.asList(BalloonType.GREEN, BalloonType.GREEN,BalloonType.GREEN,BalloonType.GREEN, BalloonType.GREEN, BalloonType.GREEN, BalloonType.GREEN, BalloonType.GREEN, BalloonType.GREEN, BalloonType.GREEN, BalloonType.GREEN,BalloonType.GREEN, BalloonType.GREEN, BalloonType.GREEN, BalloonType.GREEN, BalloonType.GREEN,BalloonType.GREEN,BalloonType.GREEN, BalloonType.GREEN,BalloonType.GREEN, BalloonType.GREEN));
        waves.add(new Wave(wave));
        waves.add(new Wave(wave2));
        waves.add(new Wave(wave3));
        waves.add(new Wave(wave4));
        waves.add(new Wave(wave5));
        waves.add(new Wave(wave6));
        waves.add(new Wave(wave7));
        waves.add(new Wave(wave8));
        waves.add(new Wave(wave9));
    }

    public boolean isTimeForNewBalloon(){
        return enemySpawnTick >= enemySpawnTickLimit;
    }

    public boolean isThereMoreEnemiesInWave(){
        return waves.get(waveIndex).getEnemyList().size() > enemyIndex;
    }

    public boolean isThereMoreWaves(){
        return waveIndex + 1 < waves.size();
    }

    public void setNewWave(){
        waveIndex++;
        enemyIndex = 0;
    }

    public int getWaveIndex(){
        return waveIndex + 1;
    }
    public int getNoWaves(){
        return waves.size();
    }
}
