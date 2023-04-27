package taxifyV4;

/**
 * Interface for Subject
 *
 * @author Zack Shatsky
 * @author Benjy Kurcz
 * @version 4
 */
public interface ISubject {

    /**
     * This method is called to add an observer
     * @param observer the observer to be added
     */
    public void addObserver(IObserver observer);

    /**
     * This method is called to notify the observer when the subject is updated
     * @param message the message to be sent to the observers
     */
    public void notifyObserver(String message);

}