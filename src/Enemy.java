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
}
