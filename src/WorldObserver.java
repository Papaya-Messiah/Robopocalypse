/*
 * Authors: Luka Wilmink, Charlotte Lyda-Turner, Cole Lassiter
 * Date: 11/20/2024
 * 
 * Observer in charge of recieving and processing world events
 */

public class WorldObserver implements IObserver {

    @Override
    public void update(Object o) {
        Display.getInstance().updateWorldDisplay();
    }
    
}
