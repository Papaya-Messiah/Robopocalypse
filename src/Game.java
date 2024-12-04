/*
 * Authors: Luka Wilmink, Charlotte Lyda-Turner, Cole Lassiter
 * Date: 10/14/2024
 * 
 * Central controller/orchestrator of all game objects.
 * Is an eager-instantiation Singleton.
 */

import java.util.ArrayList;

public class Game {
    private static Game instance = new Game();
    private ItemFactory factory;

    //all enemies
    public ArrayList<Enemy> enemies = new ArrayList<>();

    //constructor
    private Game() {
        factory = new ItemFactory();
    }

    //instance getter
    public static Game getInstance() {
        return instance;
    }

    //creates an item
    public Item genItem() {
        return factory.createItem();
    }

    //initializes the game
    private void init() {
        Player.getInstance().popStats("default");
        Player.getInstance().setCoords(2, 2);

        //spawning enemies
        int toSpawn = 10;
        for (int i = 0; i < toSpawn; i++) {
            Enemy toAdd = new Enemy();
            toAdd.popStats("default");
            toAdd.setCoords(27 + i*2, 21);
            enemies.add(toAdd);
        }

        UI.getInstance().setMsg("Welcome to Robopocalypse!");

        //adding observers
        World.getInstance().addObserver(new WorldObserver());
        Player.getInstance().addObserver(new PlayerObserver());
        EnemyObserver enemyObserver = new EnemyObserver();
        for (Enemy e : enemies) {
            e.addObserver(enemyObserver);
        }
    }

    public void run() {
        init();

        //constantly handle events sent by the different objects in the game
        Display.getInstance().addKeyListener(new Controls());

        //add some starting items for the player
        World.getInstance().getCell(2, 4).setType(Cell.CellType.ITEM);
        World.getInstance().getCell(2, 5).setType(Cell.CellType.ITEM);
        World.getInstance().getCell(2, 5).setType(Cell.CellType.ITEM);
        World.getInstance().getCell(2, 6).setType(Cell.CellType.ITEM);
        World.getInstance().getCell(2, 7).setType(Cell.CellType.ITEM);

        //call player observers to populate the UI at the start of the game
        Player.getInstance().notifyObservers();

        //update world
        World.getInstance().notifyObservers();
    }

    //quit the program
    public void quit() {
        System.out.println("Quitting...");
        System.exit(0);
    }

}
