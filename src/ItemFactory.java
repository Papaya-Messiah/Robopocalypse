/*
 * Authors: Luka Wilmink, Charlotte Lyda-Turner, Cole Lassiter
 * Date: 11/28/2024
 * 
 * An implementation of the Factory Design pattern that generates items.
 * Items are generated from a list located in "Itemdatabase.txt"
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

    //read the items from the file and store them in itemList to spawn items
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

    //spawn items
    public Item createItem() {
        if (itemList.isEmpty()) {
            populateList();
        }
        return itemList.remove(rand.nextInt(itemList.size()));
    }
}