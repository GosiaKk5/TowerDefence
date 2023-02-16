package org.example.scenes;

import org.example.Main;
import org.example.ui.MenuCommunicat;
import org.example.ui.MyButton;

import java.awt.*;

import static org.example.GameStates.PLAYING;
import static org.example.GameStates.SetGameState;

public class Menu extends GameScene implements SceneMethods{
    private MyButton playButton;
    private final MenuCommunicat communicat;
    public Menu(Main game) {
        super(game);
        initButtons();
        communicat = new MenuCommunicat(game);
    }

    private void initButtons(){
        playButton = new MyButton("Play", 220, 300, 200, 50);

        Color color = new Color(41, 175, 33);
        playButton.setColor(color);
        playButton.setBorderColor(color);
        playButton.setTextColor(Color.white);
        playButton.setFont(new Font("LucindaSans", Font.BOLD, playButton.height/2));
    }

    @Override
    public void render(Graphics g) {
        drawBackground(g);
        drawButtons(g);
        communicat.draw(g);
    }

    private void drawBackground(Graphics g){
        g.setColor(new Color(59, 92, 128, 255));
        g.fillRect(0,0, 640,800);
    }

    @Override
    public void mouseClicked(int x, int y) {
        if(playButton.getBounds().contains(x,y)){
            SetGameState(PLAYING);
        }
    }

    @Override
    public void mouseMoved(int x, int y) {

    }

    public void drawButtons(Graphics g){
        playButton.draw(g);
    }

    public MenuCommunicat getCommunicat(){
        return communicat;
    }

}
