/*
 * Authors: Luka Wilmink, Charlotte Lyda-Turner, Cole Lassiter
 * Date: 10/14/2024
 * 
 * Cell is one position in the world grid.
 * Holds a String of what object is contain
 */

public class Cell {
    //main label of the cell
    public String type;
    //boolean to determine if the cell will be displayed
    public boolean visible;

    //parameterized constructor
    public Cell(String t) {
        type = t;
        visible = true;
    }

    //returns a single ASCII character representation of the type of cell
    public String toString() {
        switch (type) {
            case "empty":
                return " ";
            case "wall":
                return "█";
            default:
                return "X";
        }
    }
}
