
import java.io.IOException;

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

    //initializes the game
    private void init() throws IOException, ClassNotFoundException {
        int worldSize = World.getInstance().worldSize;
        Player.getInstance().setDefault();
        //Player.getInstance().loadPlayer();
        Player.getInstance().setCoords(worldSize/2, worldSize/2);

        //adding observers
        World.getInstance().addObserver(new WorldObserver());
        Player.getInstance().addObserver(new PlayerObserver());
    }

    public void run() throws IOException, ClassNotFoundException {
        init();

        //constantly handle events sent by the different objects in the game
        Display.getInstance().addKeyListener(new Controls());
    }

    public void quit() {
        System.out.println("Quitting...");
        System.exit(0);
    }

}
