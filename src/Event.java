/*
 * Authors: Luka Wilmink, Charlotte Lyda-Turner, Cole Lassiter
 * Date: 11/12/2024
 * 
 * Basic Event class that holds an object.
 * Will likely be inherited from for specific events, like EnemyDeathEvent, PlayerDeathEvent, etc.
 */

public class Event {
    private Object o;

    public Event (Object o) {
        this.o = o;
    }

    public Object getObject() {
        return o;
    }
}