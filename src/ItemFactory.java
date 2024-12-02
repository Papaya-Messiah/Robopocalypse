/*
 * Authors: Luka Wilmink, Charlotte Lyda-Turner, Cole Lassiter
 * Date: 10/14/2024
 * 
 * An implementation of the Factory Design that generates items.
 */
public class ItemFactory
{
    public ItemFactory()
    {
    }
    public Item createItem(String Name, int Str, int Agi, int Def, int Con, int Health){
        Item genItem = new Item(Name, Str, Agi, Def, Con, Health);
        return genItem;
    }
}