package exceptions;

/**
 * Created by Maciek on 2016-12-29.
 */
public class BadNumberOfDegreesException extends Exception {

    public BadNumberOfDegreesException(int size){
        super("Bad number of degrees {" +size+ "}. Make sure that each user has 10 degrees between 0-9");
    }


}
