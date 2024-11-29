/*
 * Authors: Luka Wilmink, Charlotte Lyda-Turner, Cole Lassiter
 * Date: 11/20/2024
 * 
 * Subject interface to be implemented by objects that can be observed
 */

public interface ISubject {
    public void addObserver(IObserver o);
    public void removeObserver(IObserver o);
    public void notifyObservers();
}
