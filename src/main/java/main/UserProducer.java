package main;

import exceptions.BadNumberOfDegreesException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Maciek on 2016-12-26.
 */
public class UserProducer {

    public static List<User> produceUsersList(int numberOfUsers, int numberOfUserValues){
        List<User> users = new ArrayList<User>();
        for (int i = 0; i < numberOfUsers; i++) {
            User user = null;
            try {
                user = new User("user " + i, generateUserPreferenceValues(numberOfUserValues));
                user.printUserValues();
            } catch (BadNumberOfDegreesException e) {
                e.printStackTrace();
            }
            users.add(user);
        }
        return users;
    }


    public static int [] generateUserPreferenceValues(int numberOfValues){
        Random random = new Random();
        int [] preferenceValues = new int[numberOfValues];
        for(int i = 0 ;i < numberOfValues; i++){
            preferenceValues[i] = random.nextInt(10);
        }
        return preferenceValues;
    }

}
