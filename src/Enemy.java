import java.lang.Math;

public class Enemy extends Robot {

    private Cell.CellType currentCellType = Cell.CellType.WALL;

    public Enemy(){

    }

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

        Direction closestDirection = null;

        if (deltaX > deltaY){
            closestDirection = xDirection;
        } else if (deltaX < deltaY){
            closestDirection = yDirection;
        }
        
        if (closestDirection == Direction.NORTH){
            if (World.getInstance().getCell(x_pos, y_pos-1).getType() != Cell.CellType.WALL) {
                setCoords(x_pos, y_pos-1);
            }
        } else if (closestDirection == Direction.SOUTH){
            if (World.getInstance().getCell(x_pos, y_pos+1).getType() != Cell.CellType.WALL) {
                setCoords(x_pos, y_pos+1);
            }
        } else if (closestDirection == Direction.EAST){
            if (World.getInstance().getCell(x_pos+1, y_pos).getType() != Cell.CellType.WALL) {
                setCoords(x_pos+1, y_pos);
            }
        } else if (closestDirection == Direction.WEST){
            if (World.getInstance().getCell(x_pos-1, y_pos).getType() != Cell.CellType.WALL) {
                setCoords(x_pos-1, y_pos);
            }
        }
    }
}
