/*
 * Authors: Luka Wilmink, Charlotte Lyda-Turner, Cole Lassiter
 * Date: 11/20/2024
 * 
 * Observer in charge of recieving and processing player events
 */

public class PlayerObserver implements IObserver {

    @Override
    public void update(Object o) {
        UI.getInstance().setInv(Player.getInstance().descInventory());
        UI.getInstance().setStats(Player.getInstance().getStatistics().toString());
    }

}
