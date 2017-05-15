package com.javarush.task.task35.task3513;


import java.util.ArrayList;
import java.util.List;

public class Model {
    private final static int FIELD_WIDTH = 4;
    private Tile[][] gameTiles;

    public Model() {
        resetGameTiles();
    }

    private void addTile() {
        List<Tile> emptyTiles = getEmptyTiles();
        Tile tile = emptyTiles.get((int)(emptyTiles.size() * Math.random()));
        tile.value = Math.random() < 0.9 ? 2 : 4;
    }

    private List<Tile> getEmptyTiles() {
        List<Tile> list = new ArrayList<>();
        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 0; j < gameTiles[i].length; j++) {
                if (gameTiles[i][j].isEmpty())
                    list.add(gameTiles[i][j]);
            }
        }
        return list;
    }

    public void resetGameTiles() {
        gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 0; j < gameTiles[0].length; j++) {
                gameTiles[i][j] = new Tile();
            }
        }
        addTile();
        addTile();
    }
}
