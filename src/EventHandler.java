/*
 * Authors: Luka Wilmink, Charlotte Lyda-Turner, Cole Lassiter
 * Date: 11/12/2024
 * 
 * Central observer object that handles global events as needed.
 */

import java.util.ArrayList;

public class EventHandler {
    private static EventHandler instance = new EventHandler();

    //queue structure that stores events
    //events are processed as FIFO
    private ArrayList<Event> events = new ArrayList<>();

    private EventHandler() {}

    public static EventHandler getInstance() {
        return instance;
    }

    //called every "tick" by the main game loop
    public void handleEvent() {
        if (!events.isEmpty()) {
            //handle event in some way depending on the object inside it
            switch (events.get(0).getObject().getClass().getSimpleName()) {
                case "Display":
                    System.out.println("A Display object was passed in an event to the EventHandler!");
                    Display.getInstance().display();
                    break;
            
                default:
                    System.out.println("The object passed in the event to the EventHandler is not handled");
                    break;
            }
            //pop event from the front of the queue
            events.remove(0);
        }
    }

    //add an event to the handling queue
    public void queueEvent(Event e) {
        events.add(e);
    }
}
