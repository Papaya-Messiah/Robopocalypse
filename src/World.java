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
    private int worldSize = 50;
    private int worldHeight;
    private int worldWidth;
    //this String holds the most recent update of the world
    private String display;

    //constructor
    private World() {
        //having height be width/2 makes for a nice squarish shape in the console
        worldHeight = worldSize/2;
        worldWidth = worldSize;
        grid = new Cell[worldHeight][worldWidth];

        //initializing cells to hold a value
        for (int i = 0; i < worldHeight; i++) {
            for (int j = 0; j < worldWidth; j++) {
                grid[i][j] = new Cell("wall");
            }
        }
    }

    public static World getInstance() {
        return instance;
    }

    //Displays the (visible) world in the terminal.
    public void showDisplay() {
        System.out.println(display);
    }

    //updates what will be displayed. Encapsulated here because printing in a for loop causes flickering.
    public void update() {
        display = "";
        for (int i = 0; i < worldHeight; i++) {
            for (int j = 0; j < worldWidth; j++) {
                display += grid[i][j].toString();
            }
            display += "\n";
        }
    }
}
