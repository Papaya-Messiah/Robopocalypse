/*
 * Authors: Luka Wilmink, Charlotte Lyda-Turner, Cole Lassiter
 * Date: 11/12/2024
 * 
 * Keeps track of the UI displayed on the right side of the screen
 */

public class UI {
    private static UI instance = new UI();

    private UI() {}

    public static UI getInstance() {
        return instance;
    }

    public String toString() {
        String temp = "";
        temp += "Stats:\tStr:\tAgi:\tDef:\t\n";
        temp += "\n";
        temp += "Inventory\n";
        temp += "Ion cannon\n";
        temp += "Spring\n";
        temp += "Socket Wrench\n";
        temp += "test\n";
        temp += "test\n";
        return temp;
    }
}
