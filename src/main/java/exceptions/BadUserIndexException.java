package exceptions;

/**
 * Created by Maciek on 2017-01-02.
 */
public class BadUserIndexException extends Exception {

    public BadUserIndexException(){
        super("You have to choose user index between 0 and 1 value.");
    }
}
