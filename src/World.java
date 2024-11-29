/*
 * Authors: Luka Wilmink, Charlotte Lyda-Turner, Cole Lassiter
 * Date: 10/14/2024
 * 
 * Defines the game world's overall structure and behavior.
 * Perhaps a square grid, with the player in the center?
 * Is an eager-instantiation Singleton.
 */

import java.util.ArrayList;

public class World implements ISubject {
    private static World instance = new World();
    //2d grid of the world
    private Cell[][] grid;
    //side length of the square grid
    public int worldSize = 50;
    private ArrayList<IObserver> observers = new ArrayList<>();

    //constructor
    private World() {
        grid = new Cell[worldSize][worldSize];

        //initializing cells to hold a value
        for (int i = 0; i < worldSize; i++) {
            for (int j = 0; j < worldSize; j++) {
                if (i == 0 || j == 0) {
                    grid[i][j] = new Cell(Cell.CellType.WALL);
                }
                else if (i == worldSize-1 || j ==worldSize-1) {
                    grid[i][j] = new Cell(Cell.CellType.WALL);
                }
                else {
                    grid[i][j] = new Cell(Cell.CellType.EMPTY);
                }
            }
        }
    }

    public static World getInstance() {
        return instance;
    }

    public String toString() {
        String display = "";
        for (int i = 0; i < worldSize; i++) {
            for (int j = 0; j < worldSize; j++) {
                display += grid[i][j].toString();
            }
            display += "\n";
        }
        return display;
    }

    public Cell[][] getGrid() {
        return grid;
    }

    public Cell getCell(int x, int y) {
        //x and y are switched on purpose here to make the axes what one would normally expect
        return grid[y][x];
    }

    //returns the distance between the cells at two x,y coords.
    public int measureDistance(int x1, int y1, int x2, int y2) {
        int distance;
        distance = Math.abs(x1-x2) + Math.abs(y1-y2);
        return distance;
    }

    @Override
    public void addObserver(IObserver o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(IObserver o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (IObserver o : observers) {
            o.update(this);
        }
    }
}
