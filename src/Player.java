/*
 * Authors: Luka Wilmink, Charlotte Lyda-Turner, Cole Lassiter
 * Date: 10/14/2024
 * 
 * Broadly defines the player's behavior and statistics. 
 * Is an eager-instantiation Singleton.
 * Is the receiver of the command pattern implemented by PlayerCommands
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Player implements ISubject,Serializable {
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
    private Cell.CellType currentCellType = Cell.CellType.WALL;
    private ArrayList<Item> inventory;
    private ArrayList<IObserver> observers = new ArrayList<>();
    //constructor
    private Player() { }

    public void savePlayer(){
        try {
            FileOutputStream file = new FileOutputStream("Character.txt");
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(instance);
            out.close();
        } catch (Exception e) {
            System.out.print("An error is throwing");
        }
    }

    public void loadPlayer(){
        File f = new File("Character.txt");
        if(f.exists()){
            Scanner userInput = new Scanner(System.in);
            String userChoice;
            System.out.println("Would you like to load previous character? Y/N");
            userChoice = userInput.nextLine();
            if(userChoice.equals("Y") || userChoice.equals("y")){
                try {
                    FileInputStream file = new FileInputStream("Character.txt");
                    ObjectInputStream in = new ObjectInputStream(file);
                    Player loadedPlayer = (Player)in.readObject();
                    this.str = loadedPlayer.str;
                    this.view_distance = loadedPlayer.view_distance;
                    this.agi = loadedPlayer.agi;
                    this.def = loadedPlayer.def;
                    this.con = loadedPlayer.con;
                    file.close();
                    in.close();
                } catch (Exception e) {
                }
            }
            else{
                System.out.println("Starting with new Character.");
            }
            userInput.close();
        }
        else{
            System.out.println("No Previous Player Data Found.");
            System.out.println("Starting with new character");
        }
    
    }

    public void setCoords(int x, int y) {
        //set the current cell type back to what it was before the player moves away
        World.getInstance().getCell(x_pos, y_pos).setType(currentCellType);

        x_pos = x;
        y_pos = y;

        //update the current celltype
        currentCellType = World.getInstance().getCell(x, y).getType();

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

    //Boilerplate code
    public void setDefault(){
        view_distance = 5;
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
  
    public void setHealth(int h){
        this.health = h;
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
  
    public void setDef(int d){
        this.def = d;
    }
    public void setStr(int s){
        this.str = s;
    }
    public void setAgi(int a){
        this.agi = a;
    }
    public void setCon(int c){
        this.con = c;
    }

    public int getX() {
        return x_pos;
    }
    
    public int getY() {
        return y_pos;
    }
}
