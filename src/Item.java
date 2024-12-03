/*
 * Authors: Luka Wilmink, Charlotte Lyda-Turner, Cole Lassiter
 * Date: 11/20/2024
 * 
 * Item base class
 */


public class Item {
    private String itemName;
    private int attackMod;
    private int defenseMod;
    private int healthMod;
    private int viewdistMod;
    public Item (String name, int atk, int def, int hp, int view) {
        this.itemName = name;
        this.attackMod = atk;
        this.defenseMod = def;
        this.healthMod = hp;
        this.viewdistMod = view;
    }

    public String getName(){
        return itemName;
    }

    public int getAttackMod(){
        return attackMod;
    }

    public int getDefenseMod(){
        return defenseMod;
    }

    public int getHealthMod(){
        return healthMod;
    }

    public int getViewdistModt(){
        return viewdistMod;
    }

    public String toString() {
        return "Name: " + itemName + " Attack Modifier: " + attackMod + " Defense Modifier: " + defenseMod + " Health Modifier: " + healthMod + " View Distance Modifier: " + viewdistMod;
    }
}