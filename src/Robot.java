/*
 * Authors: Luka Wilmink, Charlotte Lyda-Turner, Cole Lassiter
 * Date: 12/1/2024
 * 
 * Base class that both Player and Enemy inherit from
 */

public abstract class Robot {
    protected String name;
    protected int x_pos;
    protected int y_pos;
    protected Statistics stats = new Statistics();

    public Robot(){ }

    public Robot(String name, int x_pos, int y_pos){
        this.name = name;
        this.x_pos = x_pos;
        this.y_pos = y_pos;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getX() {
        return x_pos;
    }
    
    public int getY() {
        return y_pos;
    }

    public abstract void setCoords(int x, int y);

    public Statistics getStatistics(){
        return stats;
    }

    public int getAttack() {
        return stats.getAttack();
    }

    public int getDefense() {
        return stats.getDefense();
    }

    public int getHealth() {
        return stats.getHealth();
    }

    //handles default stats and random stats
    public void popStats(String statMethod){
        if (statMethod.equals("default")){

        } else if (statMethod.equals("random weak")){
            stats.randomStats("weak");
        } else if (statMethod.equals("random normal")){
            stats.randomStats("normal");
        } else if (statMethod.equals("random strong")){
            stats.randomStats("strong");
        } 
    }

    //handles custom stats
    public void popStats(int attack, int defense, int health){
        stats = new Statistics(attack, defense, health);
    }
}
