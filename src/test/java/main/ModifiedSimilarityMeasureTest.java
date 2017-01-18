package main;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by Maciek on 2017-01-02.
 */
public class ModifiedSimilarityMeasureTest {

    private ModifiedSimilarityMeasure modifiedSimilarityMeasure;

    @Before
    public void initializeModifiedSimilarityMeasure(){
        List<User> systemUsers = UserProducer.produceUsersList(10, 10);
        modifiedSimilarityMeasure = new ModifiedSimilarityMeasure(systemUsers);

    }

    //BELONG-----------------------------------------------------

    @Test
    public void shouldGetBelongSumValueForAllUsersForGivenItem(){
        //given
        int itemIndex = 4;
        //when
        double belongSumValueForAllUsersForGivenItem = modifiedSimilarityMeasure.getBelongSumValueForAllUsersForGivenItem(itemIndex);
        //then
        System.out.println("Belong value for given item : " + belongSumValueForAllUsersForGivenItem);
    }

    @Test
    public void shouldReturnAggregateBelongForItem(){
        //given
        int itemIndex = 3;

        //when
        double aggregateBelongnessForItem = modifiedSimilarityMeasure.getAggregateBelongnessForItem(itemIndex);

        //then
        System.out.println("Aggregate belong for item : " + aggregateBelongnessForItem);
    }

    //NONBELONG----------------------------------------------------

    @Test
    public void shouldGetNonBelongSumValueForAllUsersForGivenItem(){
        //given
        int itemIndex = 4;
        //when
        double belongSumValueForAllUsersForGivenItem = modifiedSimilarityMeasure.getNonBelongSumValueForAllUsersForGivenItem(itemIndex);
        //then
        System.out.println("Belong value for given item : " + belongSumValueForAllUsersForGivenItem);
    }

    @Test
    public void shouldReturnAggregateNonBelongForItem(){
        //given
        int itemIndex = 3;

        //when
        double aggregateNonBelongnessForItem = modifiedSimilarityMeasure.getAggregateNonBelongness(itemIndex);

        double aggregateBelongnessForItem = modifiedSimilarityMeasure.getAggregateBelongnessForItem(itemIndex);

        //then
        System.out.println("Aggregate non belong for item : " + aggregateNonBelongnessForItem);

        System.out.println("Aggregate belong for item : " + aggregateBelongnessForItem);

    }
}
