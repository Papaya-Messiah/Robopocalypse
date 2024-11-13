/*
 * Authors: Luka Wilmink, Charlotte Lyda-Turner, Cole Lassiter
 * Date: 11/12/2024
 * 
 * Class in charge of displaying everything
 */

public class Display {
    private static Display instance = new Display();

    private Display() {}

    public static Display getInstance() {
        return instance;
    }

    //dirty and inelegant way to clear the terminal, but here we are.
    private void clearScreen() {
        String lots_of_newlines = "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n";
        System.out.println(lots_of_newlines);
    }

    //Displays the (visible) world in the terminal.
    public void display() {
        clearScreen();
        System.out.println(
            mergeDisplays(
            World.getInstance().toString(),
            UI.getInstance().toString()
            )
        );
    }

    private String mergeDisplays(String worldStr, String uiStr) {
        String merged = "";
        String[] list1 = worldStr.split("\n");
        String[] list2 = uiStr.split("\n");
        for (int i = 0; i < list1.length; i++) {
            merged += list1[i];
            if (i < list2.length) {
                merged += list2[i];
            }
            merged += "\n";
        }
        return merged;
    }

    public void sendEvent() {
        EventHandler.getInstance().queueEvent(new Event(this));
    }
}
