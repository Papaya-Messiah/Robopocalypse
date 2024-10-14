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
        init();
        //main game loop
        while (true) {
            clearScreen();
            World.getInstance().update();
            World.getInstance().showDisplay();

            //wait (in ms) between screen updates
            try {
                Thread.sleep(250);
            } catch (Exception e) {}
        }
    }

    //dirty and inelegant way to clear the terminal, but here we are.
    public void clearScreen() {
        String lots_of_newlines = "";
        for (int i = 0; i < 100; i++) {
            lots_of_newlines += "\n";
        }
        System.out.println(lots_of_newlines);
    }
}
