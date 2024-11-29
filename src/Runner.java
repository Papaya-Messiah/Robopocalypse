
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
        Game.getInstance().run();
    }
}