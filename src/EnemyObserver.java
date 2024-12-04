/*
 * Authors: Luka Wilmink, Charlotte Lyda-Turner, Cole Lassiter
 * Date: 12/1/2024
 * 
 * Observer in charge of receiving and processing enemy events
 */
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
            if (Game.getInstance().enemies.isEmpty()) {
                UI.getInstance().setMsg("$$$ Congratulations! All the enemies are dead and you have won. $$$\nThe game is over and will close in 5 seconds.");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                }
                Game.getInstance().quit();
            }
        }
    }
    
}
