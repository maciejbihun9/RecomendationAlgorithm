package main;

import exceptions.BadNumberOfDegreesException;
import org.junit.Test;

/**
 * Created by Maciek on 2017-01-06.
 */
public class UserTest {

    @Test(expected=BadNumberOfDegreesException.class)
    public void shouldThrowBadNumberOfPreferencesException() throws BadNumberOfDegreesException {
        //given
        int numberOfPrefs = 11;
        int preferenceValues [] = new int[numberOfPrefs];

        //when
        User newUser = new User("new user", preferenceValues);
    }

}
