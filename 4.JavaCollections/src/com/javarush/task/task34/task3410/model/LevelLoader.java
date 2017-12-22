package com.javarush.task.task34.task3410.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

import static com.javarush.task.task34.task3410.model.Model.FIELD_CELL_SIZE;

public class LevelLoader {

    Path levels;

    public LevelLoader(Path levels) {
        this.levels = levels;
    }

    public GameObjects getLevel(int level) {
        Set<Wall> walls = new HashSet<>();
        Set<Box> boxes = new HashSet<>();
        Set<Home> homes = new HashSet<>();

        walls.add(new Wall(5 * FIELD_CELL_SIZE / 2, 10 * FIELD_CELL_SIZE / 2));
        walls.add(new Wall(10 * FIELD_CELL_SIZE / 2, 5 * FIELD_CELL_SIZE / 2));

        boxes.add(new Box(5 * FIELD_CELL_SIZE / 2, 10 * FIELD_CELL_SIZE / 2));

        homes.add(new Home(5 * FIELD_CELL_SIZE / 2, 10 * FIELD_CELL_SIZE / 2));

        Player player = new Player(5 * FIELD_CELL_SIZE / 2, 10 * FIELD_CELL_SIZE / 2);

        return new GameObjects(walls, boxes, homes, player);
    }
}
