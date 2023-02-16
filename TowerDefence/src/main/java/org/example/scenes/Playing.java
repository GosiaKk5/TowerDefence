package org.example.scenes;

import org.example.CommunicatState;
import org.example.ImagesHandler;
import org.example.Main;
import org.example.Vector2D;
import org.example.Weapons.Tower;
import org.example.managers.BalloonMenager;
import org.example.managers.TileManager;
import org.example.managers.WaveManager;
import org.example.managers.WeaponManager;
import org.example.other.LevelBuild;
import org.example.ui.BottomBar;

import java.awt.*;
import static org.example.GameStates.*;

public class Playing extends GameScene implements SceneMethods{

    static final int START_GOLD = 100;
    private final int[][] lvl;
    private final Vector2D start;
    private final Vector2D end;
    private final TileManager tileManager;
    private final BalloonMenager balloonMenager;
    private final BottomBar bottomBar;
    private final WeaponManager weaponManager;
    private final WaveManager waveManager;
    private int selectedWeapon;
    private int mouseX;
    private int mouseY;
    private int lives;
    private int gold;
    private boolean isPaused;
    private int nailsLeft;
    public Playing(Main game) {
        super(game);
        this.lvl = LevelBuild.getLevelData();
        this.start = LevelBuild.getLvlStart();
        this.end = LevelBuild.getLvlEnd();
        this.tileManager = new TileManager(this);
        this.balloonMenager = new BalloonMenager(this, start, end);
        this.weaponManager = new WeaponManager(this);
        this.waveManager = new WaveManager();
        this.bottomBar = new BottomBar(0,640,640,100, this);
        this.lives = 3;
        this.gold = START_GOLD;
        this.isPaused = false;
        this.selectedWeapon = -1;
        this.nailsLeft = 5;

    }

    public void reset(){
        lives = 3;
        gold = START_GOLD;
        isPaused = false;
        selectedWeapon = -1;
        nailsLeft = 5;

        balloonMenager.reset();
        waveManager.reset();
        weaponManager.reset();
        bottomBar.reset();
    }

    @Override
    public void render(Graphics g) {
        for(int x = 0; x < lvl.length; x++){
            for(int y = 0; y < lvl[x].length; y++){
                g.drawImage(tileManager.getTileImage(lvl[y][x]), 32*x, 32*y, null);
            }
        }
        bottomBar.draw(g);
        balloonMenager.draw(g);
        weaponManager.draw(g);
        drawSelectedWeapon(g);
        //g.drawImage(tileManager.getTileImage(2), end.x, end.y, null);
    }

    private void drawSelectedWeapon(Graphics g){
        if(selectedWeapon != -1){
            g.drawImage(weaponManager.getWeaponImages()[selectedWeapon], mouseX, mouseY, null );
        }
    }

    public void update(){
        if(lives <= 0){
            backToMenuForLooser();
        }
        if(!isPaused){
            balloonMenager.update();
            weaponManager.update();
            waveManager.update();

            if(isTimeForNewBalloons()){
                balloonMenager.addBalloon(start, waveManager.getNextBalloonType(), 1);
            }

            if (isAllBalloonsAreDead()){
                if(waveManager.isThereMoreWaves()){
                    System.out.println("WAVE IS KILLED");
                    waveManager.setNewWave();
                }else{
                    backToMenuForWinner();
                    System.out.println("YOU WON");
                }
            }
        }
    }

    private void backToMenuForLooser(){
        SetGameState(MENU);
        reset();
        game.getMenu().getCommunicat().setCommunicatState(CommunicatState.FAILURE);
    }

    private void backToMenuForWinner(){
        SetGameState(MENU);
        reset();
        game.getMenu().getCommunicat().setCommunicatState(CommunicatState.VICTORY);
    }

    public void lifeLost(){
        lives--;
        if(lives == 0){
            System.out.println("YOU LOST");
        }
    }

    public void changePauseState(){
        isPaused = !isPaused;
    }
    private boolean isAllBalloonsAreDead(){
        if(waveManager.isThereMoreEnemiesInWave() && balloonMenager.isThereMoreBalloonsToAdd()){
            return false;
        }
        if(balloonMenager.getEnemies().size() == 0){
            return true;
        }
        return false;
    }

    private boolean isTimeForNewBalloons(){
        if(waveManager.isTimeForNewBalloon()){
            if(waveManager.isThereMoreEnemiesInWave()){
                return true;
            }
        }
        return false;
    }

    public void setSelectedWeapon(int type){
        this.selectedWeapon = type;
    }

    @Override
    public void mouseClicked(int x, int y) {
        if(y > 640){
            bottomBar.mouseClicked(x, y);
        }else{
            x = (x/32)*32;
            y = (y/32)*32;
            if(0 <= selectedWeapon && selectedWeapon < 3){
                if(isTileGrass(x, y) && weaponManager.getTowerAt(x,y) == null){
                    weaponManager.addTower(selectedWeapon, x, y);
                    payForTower(selectedWeapon);
                    selectedWeapon = -1;
                }
            } else if (selectedWeapon == 3) {
                if(isItRoad(x, y) && !weaponManager.isThereTrapAt(x,y)){
                    weaponManager.addTrap(3, x, y);
                    if(nailsLeft == 5){
                        payForTower(selectedWeapon);
                    }
                    nailsLeft --;
                    if(nailsLeft == 0){
                        selectedWeapon = -1;
                        nailsLeft = 5;
                    }
                }
            } else if (selectedWeapon == 4) {
                if(isItRoad(x, y) && !weaponManager.isThereTrapAt(x,y)) {
                    weaponManager.addTrap(selectedWeapon, x, y);
                    payForTower(selectedWeapon);
                    selectedWeapon = -1;
                }
                }else{
                Tower weapon = weaponManager.getTowerAt(x, y);
                bottomBar.displayWeapon(weapon);
            }
        }
    }
    private void payForTower(int type){
        gold -= weaponManager.getWeaponCost(type);
    }

    public void payForUpgrade(int amount){
        gold -= amount;
    }

    public void addGoldForKilling(int amount){
        gold += amount;
    }

    private boolean isTileGrass(int x, int y){
        return lvl[y / 32][x / 32] == 0;
    }
    @Override
    public void mouseMoved(int x,int y){
        mouseX = x;
        mouseY = y;
    }
    public boolean isItRoad(int x, int y){
        return lvl[y / 32][x / 32] == 1;
    }

    public WeaponManager getWeaponManager() {
        return weaponManager;
    }

    public BalloonMenager getEnemyMenager(){
        return balloonMenager;
    }

    public WaveManager getWaveManager(){
        return waveManager;
    }

    public int getLives(){
        return lives;
    }

    public int getGold(){
        return gold;
    }

    public boolean getIsPaused(){
        return isPaused;
    }

    public ImagesHandler getImagesHandler(){
        return imagesHandler;
    }
}
