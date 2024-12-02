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

    private UI() {}

    public static UI getInstance() {
        return instance;
    }

    public void setMsg(String m) {
        msg = m;
        Display.getInstance().updateMessageDisplay();
    }
    public void setStats(String s) {
        stats = s;
    }
    public void setInv(String i) {
        inv = i;
    }
    public void setControls(String c) {
        controls = c;
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
