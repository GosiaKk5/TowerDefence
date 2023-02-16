package org.example;

import org.example.scenes.Menu;
import org.example.scenes.Playing;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame implements Runnable{

    private GameScreen gameScreen;
    private Thread gameThread;
    static final double FPS_SET = 120.0;
    static final double UPS_SET = 60.0;


    private Render render;
    private Menu menu;
    private Playing playing;

    public Main(){

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - 640)/2,50);
        initClasses();
        setResizable(false);
        add(gameScreen);
        pack(); // use this after adding sth
        setVisible(true);
    }

    private void initClasses(){
        this.render = new Render(this);
        this.gameScreen = new GameScreen(this);
        this.menu = new Menu(this);
        this.playing = new Playing(this);


    }

    private void start(){
        gameThread = new Thread(this){};
        gameThread.start();
    }
    private void updateGame(){
        switch(GameStates.gameState){
            case MENU:
                break;
            case PLAYING:
                playing.update();
                break;
        }
    }


    public static void main(String[] args) {
        Main game = new Main();
        game.start();
        game.gameScreen.initInputs();
    }

    @Override
    public void run() {

        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;

        long lastTimeCheck = System.currentTimeMillis();
        long lastFrame = System.nanoTime();
        long lastUpdate = System.nanoTime();
        int frames = 0;
        int updates = 0;

        long now;

        while(true){
            now = System.nanoTime();
            //Render
            if(now - lastFrame >= timePerFrame){
                lastFrame = now;
                repaint();
                frames++;
            }
            //Update
            if(now - lastUpdate >= timePerUpdate){
                updateGame();
                updates++;
                lastUpdate = now;
            }

            //Checking FPS and UPS
            if(System.currentTimeMillis() - lastTimeCheck >= 1000){
                System.out.println("FPS: " + frames + "| UPS: " + updates);
                frames = 0;
                updates = 0;
                lastTimeCheck = System.currentTimeMillis();
            }
        }
    }

    //Getters and setters
    public Render getRender(){
        return this.render;
    }

    public Menu getMenu() {
        return menu;
    }


    public Playing getPlaying() {
        return playing;
    }

}