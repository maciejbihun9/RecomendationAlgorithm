package exceptions;

/**
 * Created by Maciek on 2016-12-29.
 */
public class NotAllowedDegreeException extends Exception {

    public NotAllowedDegreeException(int degree){
        super(degree + " not allowed. Make sure that you pick value between 1 and 9 or 0 for not available.");
    }

}
