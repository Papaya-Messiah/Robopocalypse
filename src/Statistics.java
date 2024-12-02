import java.util.Random;

public class Statistics {
    //init variables
    private int str;
    private int agi;
    private int def; 
    private int con;
    private int health;
    private boolean isDead;

    //default stats constructor
    public Statistics(){
        str = 10;
        agi = 10;
        def = 10;
        con = 10;
        health = con*4;
        isDead = false;
    }

    //custom stats constructor
    public Statistics(int str, int agi, int def, int con){
        this.str = str;
        this.agi = agi;
        this.def = def;
        this.con = con;
        health = con*4;
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
        str = rand.nextInt(5*mod,101)/mod;
        agi = rand.nextInt(5*mod,101)/mod;
        con = rand.nextInt(5*mod,101)/mod;
        def = rand.nextInt(5*mod,101)/mod;
        health = con*4;
    }

    public int getStr(){
        return str;
    }

    public void setStr(int str){
        this.str = str;
    }

    public int getAgi(){
        return agi;
    }

    public void setAgi(int agi){
        this.agi = agi;
    }

    public int getDef(){
        return def;
    }

    public void setDef(int def){
        this.def = def;
    }

    public int getCon(){
        return con;
    }

    public void setCon(int con){
        this.con = con;
    }

    public int getHealth(){
        return health;
    }

    public void setHealth(int health){
        this.health = health;
        if (this.health <= 0){
            isDead = true;
        }
    }

    public String toString(){
        return "Stats:\nStr:\t" + str + "\nAgi:\t" + agi + " \nDef:\t" + def + "\nCon:\t" + con;
    }

    public boolean getDeath(){
        return isDead;
    }

    public void setDeath(boolean isDead){
        this.isDead = isDead;
    }
}
