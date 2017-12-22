package com.javarush.task.task35.task3513;

import java.util.*;

public class Model {

    public static final int FIELD_WIDTH = 4;
    private Tile[][] gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
    public int score;
    public int maxTile;
    private Stack<Tile[][]> previousStates = new Stack<>();
    private Stack<Integer> previousScores = new Stack<>();
    private boolean isSaveNeeded = true;

    public Model() {
        resetGameTiles();
    }

    protected void resetGameTiles() {
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                gameTiles[i][j] = new Tile();
            }
        }

        this.score = 0;
        this.maxTile = 2;
        addTile();
        addTile();
    }

    private void addTile() {
        List<Tile> listEmptyTile = getEmptyTiles();
        int number = (int) (Math.random() * listEmptyTile.size());

        if (listEmptyTile.size() != 0)
            listEmptyTile.get(number).value = (Math.random() < 0.9 ? 2 : 4);
    }

    private List<Tile> getEmptyTiles() {
        List<Tile> listEmptyTile = new ArrayList<>();
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                if (gameTiles[i][j].isEmpty())
                    listEmptyTile.add(gameTiles[i][j]);
            }
        }
        return listEmptyTile;
    }

    private boolean compressTiles(Tile[] tiles) {
        boolean isChange = false;
        for (int j = 0; j < tiles.length - 1; j++) {
            for (int i = 0; i < tiles.length - 1; i++) {
                if (tiles[i].isEmpty() && !tiles[i + 1].isEmpty()) {
                    tiles[i].value = tiles[i + 1].value;
                    tiles[i + 1].value = 0;
                    isChange = true;
                }
            }
        }

        return isChange;
    }

    private boolean mergeTiles(Tile[] tiles) {

        boolean isChange = compressTiles(tiles);
        for (int i = 0; i < tiles.length - 1; i++) {
            if (!tiles[i].isEmpty() && tiles[i + 1].value == tiles[i].value) {
                tiles[i].value = 2 * tiles[i].value;
                tiles[i + 1].value = 0;

                if (tiles[i].value > maxTile) {
                    maxTile = tiles[i].value;
                }
                score = score + tiles[i].value;
                isChange = true;
            }
        }
        compressTiles(tiles);

        return isChange;
    }

    public void left() {
        if (isSaveNeeded)
            saveState(gameTiles);
        int j = 0;
        for (int i = 0; i < gameTiles.length; i++) {
            if (mergeTiles(gameTiles[i]))
                j++;
        }

        if (j > 0)
            addTile();

        isSaveNeeded = true;
    }

    public void right() {
        saveState(gameTiles);
        rotatMatrix(180);
        left();
        rotatMatrix(180);
    }

    public void up() {
        saveState(gameTiles);
        rotatMatrix(270);
        left();
        rotatMatrix(90);
    }

    public void down() {
        saveState(gameTiles);
        rotatMatrix(90);
        left();
        rotatMatrix(270);

    }

    public void rotatMatrix(int angle) {
        int buffer;
        int buffer2;
        switch (angle) {
            case 90:
                for (int i = 0; i < FIELD_WIDTH / 2; i++) {
                    for (int j = i; j < FIELD_WIDTH - i - 1; j++) {
                        buffer = gameTiles[j][FIELD_WIDTH - 1 - i].value;
                        gameTiles[j][FIELD_WIDTH - 1 - i].value = gameTiles[i][j].value;
                        buffer2 = gameTiles[FIELD_WIDTH - 1 - i][FIELD_WIDTH - 1 - j].value;
                        gameTiles[FIELD_WIDTH - 1 - i][FIELD_WIDTH - 1 - j].value = buffer;
                        buffer = gameTiles[FIELD_WIDTH - 1 - j][i].value;
                        gameTiles[FIELD_WIDTH - 1 - j][i].value = buffer2;
                        gameTiles[i][j].value = buffer;
                    }
                }
                break;
            case 180:
                for (int i = 0; i < FIELD_WIDTH; i++) {
                    for (int j = 0; j < FIELD_WIDTH / 2; j++) {
                        buffer = gameTiles[i][j].value;
                        gameTiles[i][j].value = gameTiles[i][FIELD_WIDTH - 1 - j].value;
                        gameTiles[i][FIELD_WIDTH - 1 - j].value = buffer;
                    }
                }
                for (int j = 0; j < FIELD_WIDTH; j++) {
                    for (int i = 0; i < FIELD_WIDTH / 2; i++) {
                        buffer = gameTiles[i][j].value;
                        gameTiles[i][j].value = gameTiles[FIELD_WIDTH - 1 - i][j].value;
                        gameTiles[FIELD_WIDTH - 1 - i][j].value = buffer;
                    }
                }
                break;
            case 270:
                for (int i = 0; i < FIELD_WIDTH / 2; i++) {
                    for (int j = i; j < FIELD_WIDTH - i - 1; j++) {
                        buffer = gameTiles[FIELD_WIDTH - 1 - j][i].value;
                        gameTiles[FIELD_WIDTH - 1 - j][i].value = gameTiles[i][j].value;
                        buffer2 = gameTiles[FIELD_WIDTH - 1 - i][FIELD_WIDTH - 1 - j].value;
                        gameTiles[FIELD_WIDTH - 1 - i][FIELD_WIDTH - 1 - j].value = buffer;
                        buffer = gameTiles[j][FIELD_WIDTH - 1 - i].value;
                        gameTiles[j][FIELD_WIDTH - 1 - i].value = buffer2;
                        gameTiles[i][j].value = buffer;
                    }
                }
                break;
        }
    }

    public Tile[][] getGameTiles() {
        return gameTiles;
    }

    public boolean canMove() {
        boolean itCan = false;
        if (getEmptyTiles().size() != 0)
            itCan = true;
        else {
            label:
            for (int i = 0; i < FIELD_WIDTH - 1; i++) {
                for (int j = 0; j < FIELD_WIDTH - 1; j++) {
                    if (gameTiles[i][j].value == gameTiles[i][j + 1].value
                            || gameTiles[i][j].value == gameTiles[i + 1][j].value
                            || gameTiles[i][j + 1].value == gameTiles[i + 1][j + 1].value) {
                        itCan = true;
                        break label;
                    }
                }
            }
        }

        return itCan;
    }

    private void saveState(Tile[][] tiles) {
        Tile[][] copyTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                copyTiles[i][j] = new Tile(tiles[i][j].value);
            }
        }
        previousStates.push(copyTiles);
        previousScores.push(score);
        isSaveNeeded = false;
    }

    public void rollback() {
        if (!previousScores.isEmpty() && !previousStates.isEmpty()) {
            gameTiles = previousStates.pop();
            score = previousScores.pop();
        }
    }

    public void randomMove() {
        int n = ((int) (Math.random() * 100)) % 4;
        switch (n) {
            case 0:
                left();
                break;
            case 1:
                right();
                break;
            case 2:
                up();
                break;
            case 3:
                down();
                break;
        }
    }

    public boolean hasBoardChanged() {
        Tile[][] gameFromStack = previousStates.peek();

        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                if (gameFromStack[i][j].value != gameTiles[i][j].value)
                    return true;
            }
        }

        return false;
    }

    public MoveEfficiency getMoveEfficiency(Move move) {
        move.move();
        MoveEfficiency m;
        if (!hasBoardChanged()) {
            m = new MoveEfficiency(-1, 0, move);
        } else {
            m = new MoveEfficiency(getEmptyTiles().size(), score, move);
        }
        rollback();
        return m;
    }


    public void autoMove() {
        PriorityQueue PQ = new PriorityQueue(4, Collections.reverseOrder());

        PQ.offer(getMoveEfficiency(this::left));
        PQ.offer(getMoveEfficiency(this::right));
        PQ.offer(getMoveEfficiency(this::up));
        PQ.offer(getMoveEfficiency(this::down));

        MoveEfficiency me = (MoveEfficiency) PQ.poll();
        me.getMove().move();
    }
}
