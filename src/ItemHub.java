import java.util.Scanner;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.Random;

public class ItemHub
{
    private ItemFactory fact = new ItemFactory();
    private Random d10 = new Random(); //used for determining item number.
    private Random d100 = new Random(); //used for determining item rarity.
    
    public ItemHub()
    {
    }
    public Item generateItem(){
        Item genItem = null;
        try{
            int itemNumber = d10.nextInt(10) + 1; //generates a number 1-10 which will be used to find an item.
            int rarity = d100.nextInt(100) + 1;
            String rarityStr = null;
            if(rarity <= 60){
                rarityStr = "Common";
            }
            if(rarity >= 61 && rarity <= 90){
                rarityStr = "Rare";
            }
            if(rarity >= 91){
                rarityStr = "Legendary";
            }
            String itemNumberString = String.valueOf(itemNumber); //Since the text file is all strings this allows us to search for the item number using .equals later.
            itemNumberString = itemNumberString +".";
            String itemName =null;
            String Str = null;
            String Agi = null;
            String Def = null;
            String Con = null;
            String Health = null;
            Scanner txt_reader = new Scanner(new FileReader("Itemdatabase.txt"));
            while (txt_reader.hasNextLine()) {
                String line = txt_reader.nextLine();
                if (line.equals(rarityStr)){
                    while(txt_reader.hasNextLine()){
                        String line2 = txt_reader.next();
                        if(line2.equals(itemNumberString)){
                            itemName = txt_reader.next();
                            String statBreak = txt_reader.next();
                            Str = txt_reader.next();
                            statBreak = txt_reader.next();
                            Agi = txt_reader.next();
                            statBreak = txt_reader.next();
                            Def = txt_reader.next();
                            statBreak = txt_reader.next();
                            Con = txt_reader.next();
                            statBreak =txt_reader.next();
                            Health = txt_reader.next();
                            break;   
                        }
                    }
                }
            }
            int StrInt = Integer.parseInt(Str);
            int AgiInt = Integer.parseInt(Agi);
            int DefInt = Integer.parseInt(Def);
            int ConInt = Integer.parseInt(Con);
            int HealthInt = Integer.parseInt(Health);
            genItem = fact.createItem(itemName, StrInt, AgiInt, DefInt,ConInt,HealthInt);
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        return genItem;
    }
}