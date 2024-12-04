/*
 * Authors: Luka Wilmink, Charlotte Lyda-Turner, Cole Lassiter
 * Date: 11/28/2024
 * 
 * An implementation of the Factory Design that generates items.
 */

import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class ItemFactory
{
    private ArrayList<Item> itemList = new ArrayList<>();
    private Random rand = new Random();

    public ItemFactory() {
        populateList();
    }

    public void populateList() {
        File file = new File("Itemdatabase.txt");
        FileReader fd;
        try {
            fd = new FileReader(file);
            Scanner in = new Scanner(fd);
            String line;
            String[] itemParams;
            while (in.hasNextLine()) {
                line = in.nextLine();
                itemParams = line.split(",");

                itemList.add(new Item(itemParams[0], Integer.parseInt(itemParams[1]), Integer.parseInt(itemParams[2]), Integer.parseInt(itemParams[3]), Integer.parseInt(itemParams[4])));
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Item createItem() {
        return itemList.remove(rand.nextInt(itemList.size()));
    }
}