/*
 * Authors: Luka Wilmink, Charlotte Lyda-Turner, Cole Lassiter
 * Date: 10/14/2024
 * 
 * Broadly defines the player's behavior and statistics. 
 * Is an eager-instantiation Singleton.
 */

public class Player {
    private static Player instance = new Player();

    //constructor
    private Player() {

    }

    public static Player getInstance() {
        return instance;
    }
}
