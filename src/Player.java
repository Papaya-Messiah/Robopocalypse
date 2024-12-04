/*
 * Authors: Luka Wilmink, Charlotte Lyda-Turner, Cole Lassiter
 * Date: 10/14/2024
 * 
 * Broadly defines the player's behavior and statistics. 
 * Is an eager-instantiation Singleton.
 * Is the receiver of the command pattern implemented by PlayerCommands
 */

import java.util.ArrayList;

public class Player extends Robot implements ISubject {
    private static Player instance = new Player();
    private int view_distance;
    private Direction facing = Direction.SOUTH; //faces south by default
    private Cell.CellType currentCellType = Cell.CellType.WALL;
    private ArrayList<Item> inventory = new ArrayList<>();
    private ArrayList<IObserver> observers = new ArrayList<>();

    //constructor
    private Player() {
        view_distance = 10;
    }

    public static Player getInstance() {
        return instance;
    }
  
    public void setFacing(Direction d) {
        facing = d;
    }

    //returns the cell the player is facing
    public Cell getFacingCell() {
        switch (facing) {
            case NORTH:
                return World.getInstance().getCell(x_pos, y_pos-1);
            case SOUTH:
                return World.getInstance().getCell(x_pos, y_pos+1);
            case EAST:
                return World.getInstance().getCell(x_pos+1, y_pos);
            case WEST:
                return World.getInstance().getCell(x_pos-1, y_pos);
            default:
                return null;
        }
    }

    //inspects the cell in front of the player
    public void inspect() {
        UI.getInstance().setMsg("In front of you stands a(n) " + getFacingCell().getType().toString().toLowerCase());
    }

    //move the player
    public void setCoords(int x, int y) {
        //set the current cell type back to what it was before the player moves away
        World.getInstance().getCell(x_pos, y_pos).setType(currentCellType);

        x_pos = x;
        y_pos = y;

        //get and update the current celltype
        currentCellType = World.getInstance().getCell(x, y).getType();

        //if player has moved into an item, pick it up
        if (currentCellType == Cell.CellType.ITEM) {
            Item newItem = Game.getInstance().genItem();
            this.addItem(newItem);
            UI.getInstance().setMsg("Picked up a(n) " + newItem.toString());
            notifyObservers();
            //empty the cell of the item
            currentCellType = Cell.CellType.EMPTY;
        }

        //temporarily set the occupying cell to the PLAYER type
        World.getInstance().getCell(x, y).setType(Cell.CellType.PLAYER);
        updateVisible();
    } 

    //make the cells within the player's viewdistance visible
    public void updateVisible() {
        //loop over grid
        for (int i = 0; i < World.getInstance().getGrid().length; i++) {
            for (int j = 0; j < World.getInstance().getGrid().length; j++) {
                //set visible if within view of the player and currently not visible
                if (World.getInstance().measureDistance(x_pos, y_pos, i, j) <= view_distance) {
                    World.getInstance().getCell(i, j).setVisible(true);
                }
                //unset visible if no longer in view of the player
                else if (World.getInstance().getCell(i, j).getVisible()) {
                    World.getInstance().getCell(i, j).setVisible(false);
                }
            }
        }
        //notify the world's observers that there was an update
        World.getInstance().notifyObservers();
    }

    //add item to inventory
    public void addItem(Item i){
        inventory.add(i);
    }

    //list just item names
    public String descInventory(){
        String invString = "";
        for (Item i : inventory){
            invString += (i.getName() + "\n");
        }
        return invString;
    }

    //equip item from inventory and modify stats accordingly
    public void equip() {
        if (!inventory.isEmpty()) {
            Item toEquip = inventory.remove(0);
            this.stats.setAttack(this.getAttack() + toEquip.getAttackMod());
            this.stats.setDefense(this.getDefense() + toEquip.getDefenseMod());
            this.setHealth(this.getHealth() + toEquip.getHealthMod());
            this.view_distance += toEquip.getViewdistMod();
            UI.getInstance().setMsg("Equipped " + toEquip.getName());
            notifyObservers();
        }
        else {
            UI.getInstance().setMsg("No items in inventory to equip!");
        }
    }

    //delegates to statistics
    public void setHealth(int health){
        this.stats.setHealth(health);
        if (this.stats.getHealth() <= 0){
            this.stats.setDeath(true);
            notifyObservers();
        }
    }

    //where the damage to player and enemy happen from fighting
    private void doDamage(Enemy e) {
        //do damage to player
        this.setHealth(this.getHealth() - e.getAttack());

        //do damage to enemy
        e.setHealth(e.getHealth() - this.getAttack());
        UI.getInstance().setMsg("Enemy attacked, Player health:" + this.getHealth() + " Enemy health:" + e.stats.getHealth());
        notifyObservers();
    }

    //player tries to attack the cell they are facing
    public void attack() {
        if (getFacingCell().getType() == Cell.CellType.ENEMY) {
            //search enemy list for enemy with matching coordinates
            for (int i = 0; i < Game.getInstance().enemies.size(); i++) {
                Enemy e = Game.getInstance().enemies.get(i);
                switch (this.facing) {
                    case NORTH:
                        if (e.getX() == this.getX() && e.getY() == this.getY()-1) {
                            doDamage(e);
                        }
                        break;
                    case SOUTH:
                        if (e.getX() == this.getX() && e.getY() == this.getY()+1) {
                            doDamage(e);
                        }
                        break;
                    case EAST:
                        if (e.getX() == this.getX()+1 && e.getY() == this.getY()) {
                            doDamage(e);
                        }
                        break;
                    case WEST:
                        if (e.getX() == this.getX()-1 && e.getY() == this.getY()) {
                            doDamage(e);
                        }
                        break;
                }
            }
        }
        else {
            UI.getInstance().setMsg("You are not facing an enemy to attack.");
        }
    }

    @Override
    public void addObserver(IObserver o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(IObserver o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (IObserver o : observers) {
            o.update(this);
        }
    }

    public int getX() {
        return x_pos;
    }
    
    public int getY() {
        return y_pos;
    }
}
