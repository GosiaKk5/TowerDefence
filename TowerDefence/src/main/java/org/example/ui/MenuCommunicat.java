package org.example.ui;

import org.example.CommunicatState;
import org.example.Main;
import org.example.scenes.Playing;

import java.awt.*;

public class MenuCommunicat {

    private final Main game;
    private CommunicatState communicatState;
    private Playing playing;
    public MenuCommunicat(Main game){

        this.communicatState = CommunicatState.WELCOME;
        this.game = game;
    }

    public void draw(Graphics g){
        if(communicatState == CommunicatState.VICTORY){
            drawCommunicatForWinners(g);
        } else if (communicatState == CommunicatState.FAILURE) {
            drawCommunicatForLooser(g);
        }else{
            drawStartCommunicat(g);
        }
    }

    private void drawStartCommunicat(Graphics g){
        g.setFont(new Font("LucindaSans", Font.BOLD, 50));
        g.setColor(new Color(255, 255, 255));
        String text = "WELCOME!";
        int textWidth = g.getFontMetrics().stringWidth(text);
        g.drawString(text, (640 - textWidth)/2,200);
    }

    private void drawCommunicatForWinners(Graphics g){
        g.setFont(new Font("LucindaSans", Font.BOLD, 40));
        g.setColor(new Color(255, 255, 255));
        String text = "YOU WON!";
        int textWidth = g.getFontMetrics().stringWidth(text);
        g.drawString(text, (640 - textWidth)/2,200);
    }

    private void drawCommunicatForLooser(Graphics g){
        g.setFont(new Font("LucindaSans", Font.BOLD, 40));
        g.setColor(new Color(255, 255, 255));
        String text = "YOU LOST!";
        int textWidth = g.getFontMetrics().stringWidth(text);
        g.drawString(text, (640 - textWidth)/2,200);
    }

    public void setCommunicatState(CommunicatState state){
        communicatState = state;
    }
}
