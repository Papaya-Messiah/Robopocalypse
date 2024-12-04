public class EnemyObserver implements IObserver {

    @Override
    public void update(Object o) {
        Enemy e = (Enemy) o;
        if (e.getStatistics().getDeath()) {
            //make enemy cell an item cell
            World.getInstance().getCell(e.getX(), e.getY()).setType(Cell.CellType.ITEM);

            //remove enemy from enemylist
            Game.getInstance().enemies.remove(e);
            UI.getInstance().setMsg("Killed an enemy!");
            World.getInstance().notifyObservers();
        }
    }
    
}
