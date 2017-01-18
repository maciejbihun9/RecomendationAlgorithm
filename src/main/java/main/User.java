package main;

import exceptions.BadNumberOfDegreesException;

/**
 * Created by Maciek on 2016-12-25.
 */
public class User {

    private String username;

    private int [] preferenceValues;

    public User(String username, int [] preferenceValues) throws BadNumberOfDegreesException {
        this.username = username;
        this.preferenceValues = preferenceValues;
        if(preferenceValues.length != SystemConstants.NUMBER_OF_DEGREES)
            throw new BadNumberOfDegreesException(preferenceValues.length);
    }

    public String getUsername(){
        return username;
    }

    public int [] getPreferenceValues(){
        return preferenceValues;
    }

    public void printUserValues(){
        System.out.println("User preference values: ");
        System.out.print("{");
        for (int i = 0; i < preferenceValues.length; i++) {
            System.out.print(preferenceValues[i]);
            if(i == preferenceValues.length - 1)
                break;
            System.out.print(" ");
        }
        System.out.println("}");
    }

}
