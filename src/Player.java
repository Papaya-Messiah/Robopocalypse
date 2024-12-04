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
        view_distance = 50;
    }

    public static Player getInstance() {
        return instance;
    }
  
    public void setFacing(Direction d) {
        facing = d;
    }

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

    public void inspect() {
        UI.getInstance().setMsg("In front of you stands a(n) " + getFacingCell().getType().toString().toLowerCase());
    }

    public void setCoords(int x, int y) {
        //set the current cell type back to what it was before the player moves away
        World.getInstance().getCell(x_pos, y_pos).setType(currentCellType);

        x_pos = x;
        y_pos = y;

        //get and update the current celltype
        currentCellType = World.getInstance().getCell(x, y).getType();

        //if player has moved into an item, pick it up
        if (currentCellType == Cell.CellType.ITEM) {
            Item newItem = World.getInstance().genItem();
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

    public void addItem(Item i){
        inventory.add(i);
    }

    public String descInventory(){
        String invString = "";
        for (Item i : inventory){
            invString += (i.getName() + "\n");
        }
        return invString;
    }

    public void equip() {
        if (!inventory.isEmpty()) {
            Item toEquip = inventory.remove(0);
            this.stats.setAttack(this.getAttack() + toEquip.getAttackMod());
            this.stats.setDefense(this.getDefense() + toEquip.getDefenseMod());
            this.stats.setHealth(this.getHealth() + toEquip.getHealthMod());
            this.view_distance += toEquip.getViewdistMod();
            UI.getInstance().setMsg("Equipped " + toEquip.getName());
            notifyObservers();
        }
        else {
            UI.getInstance().setMsg("No items in inventory to equip!");
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
