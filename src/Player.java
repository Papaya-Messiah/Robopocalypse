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
    private Cell.CellType currentCellType = Cell.CellType.WALL;

    //constructor
    private Player() {
        view_distance = 5;
    }

    public static Player getInstance() {
        return instance;
    }

    public int getX() {
        return x_pos;
    }
    
    public int getY() {
        return y_pos;
    }

    public void setCoords(int x, int y) {
        //set the current cell type back to what it was before the player moves away
        World.getInstance().getCell(x_pos, y_pos).setType(currentCellType);

        x_pos = x;
        y_pos = y;

        //update the current celltype
        currentCellType = World.getInstance().getCell(x, y).getType();

        //temporarily set the occupying cell to the PLAYER type
        World.getInstance().getCell(x, y).setType(Cell.CellType.PLAYER);
        updateVisible();
    }

    public void updateVisible() {
        //loop over grid
        for (int i = 0; i < World.getInstance().getGrid().length; i++) {
            for (int j = 0; j < World.getInstance().getGrid().length; j++) {
                //set visible if within view of the player and currently not visible
                if (World.getInstance().measureDistance(x_pos, y_pos, i, j) <= view_distance) {
                    World.getInstance().getCell(i, j).setVisible(true);
                }
                //unset visible if no longer in view of the player
                else if (World.getInstance().getCell(i, j).getVisible()) {
                    World.getInstance().getCell(i, j).setVisible(false);
                }
            }
        }
        //after updating which cells are visible, update the display for the user
        Display.getInstance().updateWorldDisplay();
    }
}
