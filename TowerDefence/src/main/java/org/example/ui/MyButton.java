package org.example.ui;

import java.awt.*;

public class MyButton {
    public int x, y, width, height;
    private String text;
    private Rectangle bounds; //class check if mouse is inside this rectangle
    private final int id;
    private Color color;
    private Color borderColor;
    private Color textColor;
    private Font font;


    public MyButton(String text, int x, int y, int width, int height){
        this.text = text;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.id = -1;
        initBounds();
        this.color = Color.white;
        this.borderColor = Color.BLACK;
        this.textColor = Color.BLACK;
        this.font = new Font("LucindaSans", Font.PLAIN, bounds.height/2);
    }

    public MyButton(String text, int x, int y, int width, int height, int id){
        this.text = text;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.id = id;
        initBounds();
        this.color = Color.white;
        this.borderColor = Color.BLACK;
        this.textColor = Color.BLACK;
        this.font = new Font("LucindaSans", Font.PLAIN, bounds.height/2);
    }

    private void initBounds(){
        this.bounds = new Rectangle(x, y, width, height);
    }
    public void draw(Graphics g){
        g.setColor(color);
        g.fillRect(x, y, width, height);

        g.setColor(borderColor);
        g.drawRect(x, y, width, height);

        g.setColor(textColor);
        drawText(g);

    }
    private void drawText(Graphics g){
        g.setFont(font);
        int textWidth = g.getFontMetrics().stringWidth(text);
        int textHeight = g.getFontMetrics().getHeight();
        g.drawString(text, x - textWidth/2 + width/2, y + textHeight/3 + height/2);
    }

    public void setString(String string){
        text = string;
    }

    public Rectangle getBounds(){
        return bounds;
    }

    public int getId(){
        return id;
    }

    public void setColor(Color color){
        this.color = color;
    }

    public void setBorderColor(Color color){
        this.borderColor = color;
    }
    public void setTextColor(Color color){
        this.textColor = color;
    }

    public void setFont(Font font){
        this.font = font;
    }
}
