package ru.pavlenty.surfacegame2;

import java.util.Random;

public class Meteorite {
    private int x;
    private int y;
    private double speed;

    private int maxX;
    private int maxY;
    private int minX =0;
    private int minY = 0;



    public Meteorite(int screenX, int screenY) {
        maxX = screenX;
        maxY = screenY;

        Random generator = new Random();
        speed = 0.1;


        x = generator.nextInt(maxX);
        y = generator.nextInt(3) * (screenY / 3);
    }

    public void update(int playerSpeed) {

        x -= playerSpeed;
        x -= speed;

        if (x < 0) {

            x = maxX;
            Random generator = new Random();
            y = generator.nextInt(maxY);
            speed = generator.nextInt(15);
        }
    }

    public float getStarWidth() {

        float minX = 1.0f;
        float maxX = 4.0f;
        Random rand = new Random();
        float finalX = rand.nextFloat()* (maxX - minX) + minX;
        return finalX;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
