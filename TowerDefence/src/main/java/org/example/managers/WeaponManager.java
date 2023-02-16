package org.example.managers;

import org.example.Weapons.*;
import org.example.enemies.Balloon;
import org.example.scenes.Playing;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class WeaponManager {
    static final int NO_WEAPONS = 5;
    private final Playing playing;
    private final Image[] weaponImages;
    private int[] weaponCost;
    private final ArrayList<Tower> towers;
    private final ArrayList<Mine> mines;
    private final ArrayList<Nail> nails;

    public WeaponManager(Playing playing){
        this.playing = playing;
        this.weaponImages = playing.getImagesHandler().getWeaponImages();
        this.towers = new ArrayList<>();
        this.mines = new ArrayList<>();
        this.nails = new ArrayList<>();
        initWeaponCost();
    }

    public void reset(){
        towers.clear();
        nails.clear();
        mines.clear();
    }

    public void update(){
        killBalloonsInRange();
    }

    private void initWeaponCost(){
        weaponCost = new int[NO_WEAPONS];
        weaponCost[0] = 50;
        weaponCost[1] = 100;
        weaponCost[2] = 300;
        weaponCost[3] = 110;
        weaponCost[4] = 300;
    }

    public void killBalloonsInRange(){
        BalloonMenager balloonMenager = playing.getEnemyMenager();
        ArrayList<Balloon> enemies = balloonMenager.getEnemies();
        for(Tower w : towers){
            for(Balloon e: enemies){
                killBalloonsByTowers(e, w);
            }
        }
        for(Nail n: nails){
            for(Balloon e: enemies){
                killBalloonsByNails(e, n);
            }
        }
        delateUsedNails();
        for(Mine m : mines){
            ArrayList<Balloon> balloonsNearMine = new ArrayList<>();
            for(Balloon e: enemies){
                if(e.isItAlive() && isEnemyInRange(e, m.getRange(), m.getX(), m.getY())){
                    balloonsNearMine.add(e);
                }
            }
            if(balloonsNearMine.size() >= 3){
                for(Balloon e: balloonsNearMine){
                    playing.addGoldForKilling(e.getGoldForKill());
                    playing.getEnemyMenager().killBalloon(e);
                    e.setDead();
                }
                m.setUsed();
            }
            balloonsNearMine.clear();
        }
        delateUsedMines();
    }

    private void killBalloonsByTowers(Balloon e, Tower w){
        if(e.isItAlive()){
            if(isEnemyInRange(e, w.getRange(), w.getX(), w.getY()) && w.isReadyToShoot()){
                playing.addGoldForKilling(e.getGoldForKill());
                playing.getEnemyMenager().killBalloon(e);
                e.setDead();
                w.shotDone();
            }
        }
    }

    private void killBalloonsByNails(Balloon e, Nail n){
        if(e.isItAlive() && !n.isUsed()){
            if(Math.abs(n.getX() - e.getX()) < 32 && Math.abs(n.getY() - e.getY()) < 32){
                playing.addGoldForKilling(e.getGoldForKill());
                e.setDead();
                playing.getEnemyMenager().killBalloon(e);
                n.setUsed();
            }
        }
    }

    private void delateUsedNails(){
        ArrayList<Nail> toRemove = new ArrayList<>();
        for(Nail n : nails){
            if(n.isUsed()){
                toRemove.add(n);
            }
        }
        nails.removeAll(toRemove);
    }

    private void delateUsedMines(){
        ArrayList<Mine> toRemove = new ArrayList<>();
        for(Mine m : mines){
            if(m.isUsed()){
                toRemove.add(m);
            }
        }
        mines.removeAll(toRemove);
    }
    private boolean isEnemyInRange(Balloon e, int range, int x, int y){
        float a = Math.abs(e.getX() - x);
        float b = Math.abs(e.getY() - y);

        return ((a*a + b*b) < range*range);
    }

    public void addTower(int type, int x, int y){
        switch(type){
            case 0 -> this.towers.add(new BasicTower(x,y,type));
            case 1 -> this.towers.add(new MediumTower(x,y,type));
            case 2 -> this.towers.add(new AdvancedTower(x,y,type));
        }


    }

    public void addTrap(int type, int x, int y){
        if(type == 3){
            this.nails.add(new Nail(x,y,type));
        }
        if(type == 4){
            this.mines.add(new Mine(x,y,type));
        }
    }
    public void draw(Graphics g){
        for(Tower w : towers){
            g.drawImage(weaponImages[w.getType()], w.getX(), w.getY(), null);
        }
        for(Mine t:mines){
            g.drawImage(weaponImages[t.getType()], t.getX(), t.getY(), null);
        }
        for(Nail t:nails){
            g.drawImage(weaponImages[t.getType()], t.getX(), t.getY(), null);
        }
    }

    public Image[] getWeaponImages() {
        return weaponImages;
    }

    public Tower getTowerAt(int x, int y){
        for(Tower w : towers){
            if(w.getX() == x && w.getY() == y){
                return w;
            }
        }
        return null;
    }

    public boolean isThereTrapAt(int x, int y){
        for(Mine t : mines){
            if(t.getX() == x && t.getY() == y){
                return true;
            }
        }
        for(Nail t : nails){
            if(t.getX() == x && t.getY() == y){
                return true;
            }
        }
        return false;
    }

    public int getWeaponCost(int type){
        return weaponCost[type];
    }



}
