/*
 * Authors: Luka Wilmink, Charlotte Lyda-Turner, Cole Lassiter
 * Date: 11/20/2024
 * 
 * Observer interface to be implemented by objects that can observe subjects
 */

public interface IObserver {
    public void update(Object o);
    public void death();
}
