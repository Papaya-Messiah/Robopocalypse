
import java.io.IOException;
import java.util.ArrayList;
/*
 * Authors: Luka Wilmink, Charlotte Lyda-Turner, Cole Lassiter
 * Date: 10/14/2024
 * 
 * Runner for the overall game/program
 */

public class Runner {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //Main entry point, should be a very small block.

        /* Ex:
         * Game.getInstance().run()
         * Game.getInstance().save()
         */
        //Game.getInstance().run();
        ItemHub hub = new ItemHub();
                ArrayList<Item> itemList = new ArrayList<Item>();
        ItemHub hub = new ItemHub();
        int count = 1;
        for(int i = 0; i < 100; i++){
            itemList.add(hub.generateItem());
        }
        for(Item currentItem : itemList){
            System.out.print(count + " ");
            currentItem.printItemInfo();
            count++;
        }

    }
}