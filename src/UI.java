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

    public String getMsg() {
        String temp = "";]
        temp += "Message Placeholder";
        return temp;
    }

    public String getStats() {
        String temp = "";
        temp += "Stats:\nStr:\t" + Player.getInstance().getStr() + "\nAgi:\t" + Player.getInstance().getAgi() + " \nDef:\t" + Player.getInstance().getDef();
        return temp;
    }

    public String getInv() {
        String temp = "";
        temp += "Inventory Placeholder";
        return temp;
    }

    public String getControls() {
        String temp = "";
        temp += "Controls Placeholder";
        return temp;
    }
}
