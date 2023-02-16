package org.example.ui;

import org.example.Weapons.Tower;

import org.example.scenes.Playing;

import java.awt.*;
import java.util.ArrayList;

import static org.example.GameStates.*;

public class BottomBar {
    private final int x;
    private final int y;
    private final int width;
    private final int height;
    private final Playing playing;
    private MyButton menuButton;
    private final ArrayList<MyButton> weaponButtons = new ArrayList<>();
    private int selectedWeapon;
    private Tower displayedWeapon;
    private MyButton upgradeWeapon;
    private MyButton pauseButton;

    public BottomBar(int x, int y, int width, int height, Playing playing){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.playing = playing;
        initButtons();

    }

    public void reset(){
        displayedWeapon = null;
        selectedWeapon = -1;
    }
    public void draw(Graphics g){
        g.setColor(new Color(59, 92, 128, 255));
        g.fillRect(x, y, width, height);
        drawButtons(g);
        drawDisplayedWeapon(g);
        drawDisplayedWeaponRange(g);
        drawWaveCounter(g);
        drawLifesLeft(g);
        drawGoldStatus(g);
    }

    public void displayWeapon(Tower weapon){
        if(weapon == displayedWeapon){
            displayedWeapon = null;
        }else{
            displayedWeapon = weapon;
        }

    }
    private void drawDisplayedWeapon(Graphics g){
        int x = 390;
        g.setColor(Color.white);
        g.fillRect(x, 650, 150, 70);
        g.setColor(Color.black);
        g.drawRect(x, 650, 150, 70);
        if(displayedWeapon != null){
            Image weaponImage = playing.getWeaponManager().getWeaponImages()[displayedWeapon.getType()];
            g.drawImage(weaponImage, x+5, 655, 32, 32, null);
            g.setColor(Color.black);
            g.drawString(displayedWeapon.getTowerName(), x+50, 675);
            g.drawString(displayedWeapon.getLvl() + " lvl", x+90, 675);
            upgradeWeapon = new MyButton("upgrade (" + displayedWeapon.getUpgradeCost() + "g)", x+20, 690, 100, 20);
            upgradeWeapon.setFont(new Font("LucindaSans", Font.BOLD, 12));
            upgradeWeapon.setColor(new Color(41, 175, 33));
            upgradeWeapon.setTextColor(Color.white);
            upgradeWeapon.draw(g);
        }
    }

    private void drawDisplayedWeaponRange(Graphics g){
        if(displayedWeapon != null){
            g.setColor(Color.white);
            int x = displayedWeapon.getX() - displayedWeapon.getRange() + 16;
            int y = displayedWeapon.getY() - displayedWeapon.getRange()+ 16;
            g.drawOval( x, y, 2*displayedWeapon.getRange(),2*displayedWeapon.getRange());
        }

    }

    private void drawWaveCounter(Graphics g){
        g.setColor(Color.white);
        g.setFont(new Font("LucindaSans", Font.BOLD, 15));
        g.drawString(("Waves: " + playing.getWaveManager().getWaveIndex()) + "/" + playing.getWaveManager().getNoWaves(), 550, 705);
    }

    private void drawLifesLeft(Graphics g){
        g.setColor(Color.white);
        g.setFont(new Font("LucindaSans", Font.BOLD, 15));
        g.drawString("Lifes: " + playing.getLives() , 550, 665);
    }

    private void drawGoldStatus(Graphics g){
        g.setColor(Color.white);
        g.setFont(new Font("LucindaSans", Font.BOLD, 15));
        g.drawString("Gold: " + playing.getGold() , 550, 685);
    }

    private void initButtons(){
        this.menuButton = new MyButton("MENU", 10, 650, 70, 30);
        pauseButton = new MyButton("Pause", 10, 685, 70, 30);

        int w = 50;
        int h = 60;
        int xStart = 110;
        int yStart = 650;

        int buttonId = 0;

        for(int i = 0; i < 5; i++){
            weaponButtons.add(new MyButton("", xStart, yStart, w, h, buttonId));
            xStart += w + 5;
            buttonId++;
        }
    }

    private void changeStringToPauseButton(){
        System.out.println("BOOM");
        if(playing.getIsPaused()){
            pauseButton.setString("Continue");
        }else{
            pauseButton.setString("Pause");
        }

    }

    public void drawButtons(Graphics g){
        menuButton.draw(g);
        pauseButton.draw(g);
        drawWeaponButtons(g);
    }

    private void drawWeaponButtons(Graphics g){
        for(MyButton b : weaponButtons){
            b.draw(g);
            g.drawImage(playing.getWeaponManager().getWeaponImages()[b.getId()], b.x + 9, b.y + 9, 32, 32, null );
            g.setColor(Color.black);
            g.setFont( new Font("LucindaSans", Font.BOLD, 10));
            g.drawString(playing.getWeaponManager().getWeaponCost(b.getId()) + " gold", b.x + 5, b.y + 53);
            g.drawRect(b.x, b.y, b.width, b.height);
        }

    }

    public void mouseClicked(int x, int y) {
        selectedWeapon = -1;
        playing.setSelectedWeapon(-1);

        if(menuButton.getBounds().contains(x,y)){
            playing.reset();
            SetGameState(MENU);
        }else if(pauseButton.getBounds().contains(x, y)){
            playing.changePauseState();
            changeStringToPauseButton();
        }else{
            for(MyButton b : weaponButtons){
                if(b.getBounds().contains(x,y) && isEnoughGoldForTower(b.getId())){
                    selectedWeapon = b.getId();
                    playing.setSelectedWeapon(selectedWeapon);
                    return;
                }
            }
            if(displayedWeapon != null){
                if(upgradeWeapon.getBounds().contains(x,y) && isEnoughGoldForUpgrade()){
                    displayedWeapon.upgradeTower();
                    playing.payForUpgrade(displayedWeapon.getUpgradeCost());
                }
            }
        }
    }

    private boolean isEnoughGoldForTower(int type){
        return playing.getWeaponManager().getWeaponCost(type) <= playing.getGold();
    }

    private boolean isEnoughGoldForUpgrade(){
        return displayedWeapon.getUpgradeCost() <= playing.getGold();
    }


}
