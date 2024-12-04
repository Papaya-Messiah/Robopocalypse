/*
 * Authors: Luka Wilmink, Charlotte Lyda-Turner, Cole Lassiter
 * Date: 11/12/2024
 * 
 * Keeps track of the UI displayed on the right side of the screen
 */

public class UI {
    private static UI instance = new UI();
    private String msg;
    private String stats;
    private String inv;
    private String controls;

    //constructor
    private UI() {}

    public static UI getInstance() {
        return instance;
    }

    /*
     * All setters call their respective Display updates.
     */
    public void setMsg(String m) {
        msg = m;
        Display.getInstance().updateMessageDisplay();
    }
    public void setStats(String s) {
        stats = s;
        Display.getInstance().updateStatsDisplay();
    }
    public void setInv(String i) {
        inv = i;
        Display.getInstance().updateInventoryDisplay();
    }
    public void setControls(String c) {
        controls = c;
        Display.getInstance().updateControlsDisplay();
    }

    public String getMsg() {
        return msg;
    }

    public String getStats() {
        return stats;
    }

    public String getInv() {
        return inv;
    }

    public String getControls() {
        return controls;
    }
}
