/*
 * Authors: Luka Wilmink, Charlotte Lyda-Turner, Cole Lassiter
 * Date: 11/20/2024
 * 
 * Observer in charge of recieving and processing player events
 */

public class PlayerObserver implements IObserver {

    @Override
    public void update(Object o) {
        //checks for death
        if (Player.getInstance().getStatistics().getDeath()) {
            UI.getInstance().setMsg("!!! Your health has been reduced to 0 and you have died !!!\nThe game is over and will close in 5 seconds.");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
            }
            Game.getInstance().quit();
        }
        //updates UI displays related to the player
        UI.getInstance().setInv(Player.getInstance().descInventory());
        UI.getInstance().setStats(Player.getInstance().getStatistics().toString());
    }

}
