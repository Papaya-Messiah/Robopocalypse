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
    private Game() {

    }

    //instance getter
    public static Game getInstance() {
        return instance;
    }

    private void init() {
        int worldSize = World.getInstance().worldSize;
        Player.getInstance().setCoords(worldSize/2, worldSize/2);
    }

    public void run() {
        //initialize game
        init();

        //show first display of the game
        World.getInstance().showDisplay();
        World.getInstance().sendEvent();
        //constantly handle events sent by the different objects in the game
        while (true) {
            EventHandler.getInstance().handleEvent();
        }
    }

}
