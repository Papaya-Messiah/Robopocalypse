/*
 * Authors: Luka Wilmink, Charlotte Lyda-Turner, Cole Lassiter
 * Date: 10/14/2024
 * 
 * Central controller/orchestrator of all game objects.
 * Is an eager-instantiation Singleton.
 */

public class Game {
    private static Game instance = new Game();

    //constructor
    private Game() { }

    //instance getter
    public static Game getInstance() {
        return instance;
    }

    //initializes the game
    private void init() {
        Player.getInstance().popStats("default");
        Player.getInstance().setCoords(2, 2);

        UI.getInstance().setMsg("Welcome to Robopocalypse!");

        //adding observers
        World.getInstance().addObserver(new WorldObserver());
        Player.getInstance().addObserver(new PlayerObserver());
    }

    public void run() {
        init();

        //constantly handle events sent by the different objects in the game
        Display.getInstance().addKeyListener(new Controls());

        World.getInstance().getCell(2, 4).setType(Cell.CellType.ITEM);
    }

    public void quit() {
        System.out.println("Quitting...");
        System.exit(0);
    }

}
