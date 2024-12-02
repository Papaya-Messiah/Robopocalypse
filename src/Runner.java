
import java.io.IOException;
/*
 * Authors: Luka Wilmink, Charlotte Lyda-Turner, Cole Lassiter
 * Date: 10/14/2024
 * 
 * Runner for the overall game/program
 */

public class Runner {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Game.getInstance().run();
    }
}