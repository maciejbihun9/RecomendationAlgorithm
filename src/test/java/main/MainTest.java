package main;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.BaseMatcher.*;

import java.util.List;
import java.util.Random;

/**
 * Created by Maciek on 2016-12-25.
 */
public class MainTest {

    @Test
    public void shouldReturnElementsRatio(){
        List<User> users = UserProducer.produceUsersList(250, 10);
        double[] elementsKnowladgeRatio = Main.getElementsKnowledgeRatio(users);
        for (Double element : elementsKnowladgeRatio){
            System.out.print("element : " + element);
            System.out.print(" ");
        }
    }


}
