package com.javarush.task.task34.task3410.model;

public abstract class CollisionObject extends GameObject {

    public CollisionObject(int x, int y) {
        super(x, y);
    }

    public boolean isCollision(GameObject gameObject, Direction direction) {
        int xx = gameObject.getX();
        int yy = gameObject.getY();

        switch (direction) {
            case DOWN:
                yy = yy - Model.FIELD_CELL_SIZE;
                break;
            case UP:
                yy = yy + Model.FIELD_CELL_SIZE;
                break;
            case LEFT:
                xx = xx + Model.FIELD_CELL_SIZE;
                break;
            case RIGHT:
                xx = xx - Model.FIELD_CELL_SIZE;
                break;
        }
        return (getX() == xx) && (getY() == yy);
    }
}
