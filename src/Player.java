/*
 * Authors: Luka Wilmink, Charlotte Lyda-Turner, Cole Lassiter
 * Date: 10/14/2024
 * 
 * Broadly defines the player's behavior and statistics. 
 * Is an eager-instantiation Singleton.
 */

 public class Player {
    private static Player instance = new Player();
    private int x_pos;
    private int y_pos;
    private int view_distance;
    private int str;
    private int agi;
    private int def; 
    private int con;
    private int health;
    private boolean isDead = false;
    //constructor
    private Player() {
        view_distance = 3;
        this.str = 10;
        this.agi = 10;
        this.con = 10;
        this.health = con * 4;
        this.def = 10;
    }

    public static Player getInstance() {
        return instance;
    }
    public int getHealth(){
        return health;
    }
    public void healthChange(int changer){
        this.health = health + changer;
        if (this.health <= 0){
            isDead = true;
        }
    }
    public boolean returnDeathFlag(){
        return isDead;
    }
    public int getStr(){
        return str;
    }
    public int getAgi(){
        return agi;
    }
    public int getCon(){
        return con;
    }
    public int getDef(){
        return def;
    }
    public void defChange(int changer){
        this.def = def + changer;
    }
    public void strChange(int changer){
        this.str = str + changer;
    }
    public void agiChange(int changer){
        this.agi = agi + changer;
    }
    public void conChange(int changer){
        this.con = con + changer;
    }
    public void setCoords(int x, int y) {
        x_pos = x;
        y_pos = y;
        World.getInstance().getCell(x, y).setType("player");
        updateVisible();
    }

    public void updateVisible() {
        for (int i = 0; i < World.getInstance().getGrid().length; i++) {
            for (int j = 0; j < World.getInstance().getGrid().length; j++) {
                //set visible if within view of the player
                if (World.getInstance().measureDistance(x_pos, y_pos, i, j) <= view_distance) {
                    World.getInstance().getCell(i, j).toggleVisible();
                }
                //unset visible if no longer in view of the player
                else if (World.getInstance().getCell(i, j).getVisible()) {
                    World.getInstance().getCell(i, j).toggleVisible();
                }
            }
        }
    }
}
