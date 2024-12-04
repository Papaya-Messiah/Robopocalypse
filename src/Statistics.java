/*
 * Authors: Luka Wilmink, Charlotte Lyda-Turner, Cole Lassiter
 * Date: 12/1/2024
 * 
 * Class that encapsulates and populates the Statistics for Robots
 */
import java.util.Random;

public class Statistics {
    //init variables
    private int attack;
    private int defense; 
    private int health;
    private boolean isDead;

    //default stats constructor
    public Statistics(){
        attack = 1;
        defense = 0;
        health = 10;
        isDead = false;
    }

    //custom stats constructor
    public Statistics(int attack, int defense, int health){
        this.attack = attack;
        this.defense = defense;
        this.health = health;
        isDead = false;
    }

    //allows the generation of random stats which are modified by the difficulty of the Robot (will only be for enemies, really)
    public void randomStats(String strengthOfStats){
        int mod = 1;
        if (strengthOfStats.equals("weak")){
            mod = 6;
        } else if (strengthOfStats.equals("normal")){
            mod = 3;
        } else if (strengthOfStats.equals("strong")){
            mod = 1;
        }
        Random rand = new Random();
        //generates a random integer between 5*mod (so that no Robot can have stats below 5) and 100
        attack = rand.nextInt(5*mod,101)/mod;
        defense = rand.nextInt(5*mod,101)/mod;
        health = rand.nextInt(5*mod,101)/mod;
    }

    public int getAttack(){
        return attack;
    }

    public void setAttack(int str){
        this.attack = str;
    }

    public int getDefense(){
        return defense;
    }

    public void setDefense(int def){
        this.defense = def;
    }

    public int getHealth(){
        return health;
    }

    public void setHealth(int health){
        this.health = health;
    }

    public String toString(){
        return "Stats:\nAttack:  " + attack + "\nDefense:  " + defense + "\nHealth:  " + health;
    }

    public boolean getDeath(){
        return isDead;
    }

    public void setDeath(boolean isDead){
        this.isDead = isDead;
    }
}
