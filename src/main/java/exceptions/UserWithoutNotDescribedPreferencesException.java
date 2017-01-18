package exceptions;

/**
 * Created by Maciek on 2017-01-02.
 */
public class UserWithoutNotDescribedPreferencesException extends Exception {

    public UserWithoutNotDescribedPreferencesException(){
        super("User that tou choose do not contains any not described preferences.");
    }
}
