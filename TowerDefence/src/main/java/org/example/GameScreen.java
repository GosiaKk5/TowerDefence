package org.example;

import org.example.inputs.MyMouseListener;

import javax.swing.*;
import java.awt.*;

public class GameScreen extends JPanel {

    private final Main game;

    public GameScreen(Main game){

        this.game = game;
        setPanelSize();
    }

    private void setPanelSize(){
        Dimension size = new Dimension(640, 740);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    public void initInputs(){
        MyMouseListener myMouseListener = new MyMouseListener(game);

        addMouseListener(myMouseListener);
        addMouseMotionListener(myMouseListener);

        requestFocus();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        game.getRender().render(g);

    }





}
