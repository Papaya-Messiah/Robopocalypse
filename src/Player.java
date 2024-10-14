/*
 * Authors: Luka Wilmink, Charlotte Lyda-Turner, Cole Lassiter
 * Date: 10/14/2024
 * 
 * Broadly defines the player's behavior and statistics. 
 * Is an eager-instantiation Singleton.
 */

public class Player {
    private static Player instance = new Player();
    private int x_pos;
    private int y_pos;
    private int view_distance;

    //constructor
    private Player() {
        view_distance = 3;
    }

    public static Player getInstance() {
        return instance;
    }

    public void setCoords(int x, int y) {
        x_pos = x;
        y_pos = y;
        World.getInstance().getCell(x, y).setType("player");
        updateVisible();
    }

    public void updateVisible() {
        for (int i = 0; i < World.getInstance().getGrid().length; i++) {
            for (int j = 0; j < World.getInstance().getGrid().length; j++) {
                //set visible if within view of the player
                if (World.getInstance().measureDistance(x_pos, y_pos, i, j) <= view_distance) {
                    World.getInstance().getCell(i, j).toggleVisible();
                }
                //unset visible if no longer in view of the player
                else if (World.getInstance().getCell(i, j).getVisible()) {
                    World.getInstance().getCell(i, j).toggleVisible();
                }
            }
        }
    }
}
