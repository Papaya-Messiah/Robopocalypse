/*
 * Authors: Luka Wilmink, Charlotte Lyda-Turner, Cole Lassiter
 * Date: 11/20/2024
 * 
 * Item base class
 */


public class Item {
    private String itemName;
    private int Str;
    private int Agi;
    private int Def;
    private int Con;
    private int Health;
    public Item(String name, int Strength, int Agility, int Defense, int Constitution, int Heal)
    {
        this.itemName = name;
        this.Str = Strength;
        this.Agi = Agility;
        this.Def = Defense;
        this.Con = Constitution;
        this.Health = Heal;
    }
    public String getName(){
        return itemName;
    }
    public int getStr(){
        return Str;
    }
    public int getAgi(){
        return Agi;
    }
    public int getDef(){
        return Def;
    }
    public int getCon(){
        return Con;
    }
    public int getHealth(){
        return Health;
    }
    public void printItemInfo(){
        System.out.println(itemName + " str: " + Str + " Agi: " + Agi + " Def: " + Def + " Con: " + Con + " Health: " + Health);
    }
}