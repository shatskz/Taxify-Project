package taxifyV5;

/**
 * Interface for Observer declares the update method to post the notifications received from
 * the subject
 *
 * @author Zack Shatsky
 * @author Benjy Kurcz
 * @version 4
 */
public interface IObserver {

    /**
     * This method is called when the subject is updated
     * @param message the message to be displayed
     */
    public void updateObserver(String message);

}