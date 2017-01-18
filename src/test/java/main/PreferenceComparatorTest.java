package main;

import exceptions.BadNumberOfDegreesException;
import exceptions.BadUserIndexException;
import exceptions.UserWithoutNotDescribedPreferencesException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * Created by Maciek on 2016-12-28.
 */
public class PreferenceComparatorTest {

    private PreferenceComparator preferenceComparator;

    @Before
    public void initialize(){
        int [] userOneValues = {1,3,7,6,7,9,2,0,2,0};
        int [] userTwoValues = {1,3,7,4,7,9,3,1,2,5};

        User one = null;
        User two = null;
        try {
            one = new User("Maciej", userOneValues);
            two = new User("Jakub", userTwoValues);
        } catch (BadNumberOfDegreesException e) {
            e.printStackTrace();
        }
        preferenceComparator = new PreferenceComparator(one, two);
    }

    @Test
    public void oneElementMeasureTest(){
        double prefValue = preferenceComparator.oneElementMeasure(0.7, 0.3, 0.3, 0.8);
        System.out.println("Preference value : " + prefValue);
        assertThat(prefValue, equalTo(0.41));
    }

    @Test
    public void measureEuclideanDistanceTest(){
        double preferenceResult = preferenceComparator.measureNormalizedSimilarity();
        System.out.println("Preference result : " + preferenceResult);
    }

    @Test
    public void getPreferencesSimilarityWithPeanaltyTest() throws BadUserIndexException, UserWithoutNotDescribedPreferencesException {
        double preferencesSimilarityWithPeanalty = preferenceComparator.getPreferencesSimilarityWithPenalty(0);
        assertThat(preferencesSimilarityWithPeanalty, equalTo(0.342));
    }



}
