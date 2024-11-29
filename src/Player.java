/*
 * Authors: Luka Wilmink, Charlotte Lyda-Turner, Cole Lassiter
 * Date: 10/14/2024
 * 
 * Broadly defines the player's behavior and statistics. 
 * Is an eager-instantiation Singleton.
 */

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
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
    private Player() {
    }
    public void savePlayer(){
        Scanner userInput = new Scanner(System.in);
        System.out.println("Would you like to save? (Y/N)");
        String userChoice = userInput.nextLine();
        if(userChoice.equals("Y") || userChoice.equals("y")){
            try{
                System.out.println("Please enter a save name: ");
                userChoice = userInput.nextLine();
                File playerObject = new File(userChoice);
                if(playerObject.createNewFile()){
                    System.out.println("File Created: " + playerObject.getName() );
                    FileWriter playerWriter = new FileWriter(userChoice);
                    playerWriter.write("Str: " + str + "\n" + "Agi: " + agi + "\n" + "Def: " + def + " \n" + "Con: " + con + "\n");
                    playerWriter.close();
                }
                else{
                    System.out.println("Overwriting file");
                    FileWriter playerWriter = new FileWriter(userChoice);
                    playerWriter.write("Str: " + str + "\n" + "Agi: " + agi + "\n" + "Def: " + def + " \n" + "Con: " + con + "\n");
                    playerWriter.close();
                }
            }
            catch(Exception e){
                System.out.println("An error occured");
            }
        }
        else{
            System.out.println("Not saving. Goodbye");
        }
        userInput.close();
    }
    public void loadPlayer(){
        try{
            Scanner userInput = new Scanner(System.in);
            System.out.println("Load Character? (Y/N)");
            String userChoice = userInput.nextLine();
            if(userChoice.equals("Y") || userChoice.equals("y")){
                System.out.println("Enter Save name: ");
                userChoice = userInput.nextLine();
                File f = new File(userChoice);
                if (f.exists()) {
                    System.out.println("Loading character");
                    Scanner txt_Reader = new Scanner(new FileReader(userChoice));
                        String breaker = txt_Reader.next();
                        String statNumString = txt_Reader.next();
                        this.str = Integer.parseInt(statNumString);
                        breaker = txt_Reader.next();  
                        statNumString = txt_Reader.next();
                        this.agi = Integer.parseInt(statNumString);
                        breaker = txt_Reader.next();
                        statNumString = txt_Reader.next();
                        this.def = Integer.parseInt(statNumString);
                        breaker = txt_Reader.next();
                        statNumString = txt_Reader.next();
                        this.con = Integer.parseInt(statNumString);
                        this.health = con * 4;
                        txt_Reader.close();
                }
                else{
                    System.out.println("No save found");
                    System.out.println("Default Character generated");
                }
                }
            else{
                System.out.println("Default Character generated");
            }
        }
        catch(Exception e){
            System.out.println("Uh oh");
        }
    }
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
}
