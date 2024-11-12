/*
 * Authors: Luka Wilmink, Charlotte Lyda-Turner, Cole Lassiter
 * Date: 10/14/2024
 * 
 * Defines the game world's overall structure and behavior.
 * Perhaps a square grid, with the player in the center?
 * Is an eager-instantiation Singleton.
 */

public class World {
    public static World instance = new World();
    //2d grid of the world
    private Cell[][] grid;
    //side length of the square grid
    public int worldSize = 40;
    //this String holds the most recent update of the world
    private String display;

    //constructor
    private World() {
        grid = new Cell[worldSize][worldSize];

        //initializing cells to hold a value
        for (int i = 0; i < worldSize; i++) {
            for (int j = 0; j < worldSize; j++) {
                grid[i][j] = new Cell("wall");
            }
        }
    }

    public static World getInstance() {
        return instance;
    }

    //Displays the (visible) world in the terminal.
    public void showDisplay() {
        clearScreen();
        update();
        System.out.println(display);
    }

    //dirty and inelegant way to clear the terminal, but here we are.
    public void clearScreen() {
        String lots_of_newlines = "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n";
        System.out.println(lots_of_newlines);
    }
    
    //updates what will be displayed
    private void update() {
        display = "";
        for (int i = 0; i < worldSize; i++) {
            for (int j = 0; j < worldSize; j++) {
                display += grid[i][j].toString();
            }
            display += "\n";
        }
    }

    public Cell[][] getGrid() {
        return grid;
    }

    public Cell getCell(int x, int y) {
        return grid[x][y];
    }

    //returns the distance between the cells at two x,y coords.
    public int measureDistance(int x1, int y1, int x2, int y2) {
        int distance;
        distance = Math.abs(x1-x2) + Math.abs(y1-y2);
        return distance;
    }

    //send an Event to the handler
    public void sendEvent() {
        EventHandler.getInstance().sendEvent(new Event(this));
    }
}
