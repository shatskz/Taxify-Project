package taxify;

/*
 * The interface IObserver declares the update method to post the notifications received from the subject
 */

public interface IObserver {

    /**
     * This method is called when the subject is updated
     * @param message the message to be displayed
     */
    public void updateObserver(String message);

}