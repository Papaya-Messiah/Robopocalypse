/*
 * Authors: Luka Wilmink, Charlotte Lyda-Turner, Cole Lassiter
 * Date: 10/14/2024
 * 
 * Cell is one position in the world grid.
 * Holds a String of what object is contain
 */

public class Cell {
    //main label of the cell
    private CellType type;
    private boolean visible;

    //reference to the object of the cell
    private Object occupant;

    //parameterized constructor
    public Cell(CellType t) {
        type = t;
        visible = false;
    }

    public void setType(CellType t) {
        type = t;
    }

    public CellType getType() {
        return type;
    }

    public void setOccupant(Object o) {
        occupant = o;
    }

    public Object getOccupant() {
        return occupant;
    }

    //returns a single ASCII character representation of the type of cell
    public String toString() {
        if (visible) {
            switch (type) {
                case PLAYER:
                    return "@@";
                case ENEMY:
                    return "!!";
                case ITEM:
                    return "$$";
                case EMPTY:
                    return "  ";
                case WALL:
                    return "██";
                default:
                    return "ER";
            }
        }
        else { return "  "; }
    }

    public void setVisible(boolean b) {
        visible = b;
    }

    public boolean getVisible() {
        return visible;
    }

    public enum CellType {
        PLAYER,
        ENEMY,
        ITEM,
        EMPTY,
        WALL
    }
}
