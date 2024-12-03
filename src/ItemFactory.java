/*
 * Authors: Luka Wilmink, Charlotte Lyda-Turner, Cole Lassiter
 * Date: 11/28/2024
 * 
 * An implementation of the Factory Design that generates items.
 */

import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import java.util.Random;

public class ItemFactory
{
    public ItemFactory() { }

    public Item createItem() {
        Random rand = new Random();
        File file = new File("Itemdatabase.txt");
        FileReader fd;
        Item newItem = null;
        try {
            fd = new FileReader(file);
            Scanner in = new Scanner(fd);

            //count lines in file
            int lineCount = 0;
            while (in.hasNextLine()) {
                lineCount++;
                in.nextLine();
            }

            //reset scanner
            in.close();
            fd = new FileReader(file);
            in = new Scanner(fd);

            //advance a random number of lines within range
            for (int i = 0; i < rand.nextInt(lineCount); i++) {
                in.nextLine();
            }
            String line = in.nextLine();
            String[] itemParams = line.split(",");

            newItem = new Item(itemParams[0], Integer.parseInt(itemParams[1]), Integer.parseInt(itemParams[2]), Integer.parseInt(itemParams[3]), Integer.parseInt(itemParams[4]));

            in.close();

            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newItem;
    }
}