package com.javarush.task.task34.task3410.model;

import java.awt.*;

public class Home extends GameObject {

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.RED);
        int leftUpperCornerX = getX() - getWidth() / 2;
        int leftUpperCornerY = getY() - getHeight() / 2;

        graphics.drawRect(leftUpperCornerX, leftUpperCornerY, getWidth(), getHeight());
        graphics.fillRect(leftUpperCornerX, leftUpperCornerY, getWidth(), getHeight());
    }

    public Home(int x, int y) {
        super(x, y, 2, 2);
    }
}
