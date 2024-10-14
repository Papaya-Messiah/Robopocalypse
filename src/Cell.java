/*
 * Authors: Luka Wilmink, Charlotte Lyda-Turner, Cole Lassiter
 * Date: 10/14/2024
 * 
 * Cell is one position in the world grid.
 * Holds a String of what object is contain
 */

public class Cell {
    //main label of the cell
    private String type;
    private boolean visible;

    //parameterized constructor
    public Cell(String t) {
        type = t;
        visible = false;
    }

    public void setType(String t) {
        type = t;
    }

    //returns a single ASCII character representation of the type of cell
    public String toString() {
        if (visible) {
            switch (type) {
                case "player":
                    return "◁▷";
                case "enemy":
                    return "╳";
                case "item":
                    return "◉";
                case "empty":
                    return "  ";
                case "wall":
                    return "██";
                default:
                    return "X";
            }
        }
        else { return "  "; }
    }

    public void toggleVisible() {
        visible = !visible;
    }

    public boolean getVisible() {
        return visible;
    }
}
