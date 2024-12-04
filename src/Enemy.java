import java.util.ArrayList;
import java.util.Random;

public class Enemy extends Robot implements ISubject {

    private Cell.CellType currentCellType = Cell.CellType.WALL;
    private Random rand = new Random();
    private ArrayList<IObserver> observers = new ArrayList<>();

    public Enemy(){ }

    public void setCoords(int x, int y) {
        //set the current cell type back to what it was before the enemy moves away
        World.getInstance().getCell(x_pos, y_pos).setType(currentCellType);

        x_pos = x;
        y_pos = y;

        //update the current celltype
        currentCellType = World.getInstance().getCell(x, y).getType();

        //temporarily set the occupying cell to the ENEMY type
        World.getInstance().getCell(x, y).setType(Cell.CellType.ENEMY);
    }

    public void setHealth(int health){
        this.stats.setHealth(health);
        if (this.stats.getHealth() <= 0){
            this.stats.setDeath(true);
            notifyObservers();
        }
    }

    public void move(){
        //find direction of player relative to enemy
        int playerX = Player.getInstance().x_pos;
        int playerY = Player.getInstance().y_pos;

        int deltaX = x_pos - playerX;
        int deltaY = y_pos - playerY;

        Direction xDirection = null;
        Direction yDirection = null;

        if (deltaX < 0){
            xDirection = Direction.EAST;
        } else if (deltaX > 0) {
            xDirection = Direction.WEST;
        } else if (deltaX == 0){
            xDirection = null;
        }

        if (deltaY < 0){
            yDirection = Direction.SOUTH;
        } else if (deltaY > 0) {
            yDirection = Direction.NORTH;
        } else if (deltaY == 0){
            yDirection = null;
        }

        Direction moveDirection = null;

        if (deltaX > deltaY){
            if(deltaY == 0){
                moveDirection = xDirection;
            } else {
                moveDirection = yDirection;
            }
        } else if (deltaX < deltaY){
            if(deltaX == 0){
                moveDirection = yDirection;
            } else {
                moveDirection = xDirection;
            }
        } else if (deltaX == deltaY){
            if (rand.nextInt(2) == 0){
                moveDirection = xDirection;
            } else {
                moveDirection = yDirection;
            }
        }
        
        if (moveDirection == Direction.NORTH){
            if (World.getInstance().getCell(x_pos, y_pos-1).getType() == Cell.CellType.EMPTY  && World.getInstance().measureDistance(x_pos, y_pos, playerX, playerY) > 2) {
                setCoords(x_pos, y_pos-1);
            }
        } else if (moveDirection == Direction.SOUTH){
            if (World.getInstance().getCell(x_pos, y_pos+1).getType() == Cell.CellType.EMPTY && World.getInstance().measureDistance(x_pos, y_pos, playerX, playerY) > 2) {
                setCoords(x_pos, y_pos+1);
            }
        } else if (moveDirection == Direction.EAST){
            if (World.getInstance().getCell(x_pos+1, y_pos).getType() == Cell.CellType.EMPTY && World.getInstance().measureDistance(x_pos, y_pos, playerX, playerY) > 2) {
                setCoords(x_pos+1, y_pos);
            }
        } else if (moveDirection == Direction.WEST){
            if (World.getInstance().getCell(x_pos-1, y_pos).getType() == Cell.CellType.EMPTY && World.getInstance().measureDistance(x_pos, y_pos, playerX, playerY) > 2) {
                setCoords(x_pos-1, y_pos);
            }
        }
        World.getInstance().notifyObservers(); 
        System.out.println("New enemy position: " + x_pos + ", " + y_pos);
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
