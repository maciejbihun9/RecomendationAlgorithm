package main;

import exceptions.NotAllowedDegreeException;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

/**
 * Created by Maciek on 2016-12-29.
 */
public class PenaltyEstimatorTest {

    @Test
    public void shouldReturnGoodNumberOfFourDegreeOccurances(){
        //given
        int degree = 4;
        int [] userDegrees = {3, 4, 4, 5, 0, 2, 1, 6, 9, 2};
        PenaltyEstimator penaltyEstimator = new PenaltyEstimator(userDegrees);

        //when
        int numberOfDegreeOccurences = 0;
        try {
            numberOfDegreeOccurences = penaltyEstimator.getNumberOfDegreeOccurences(degree);
        } catch (NotAllowedDegreeException e) {
            e.printStackTrace();
        }
        //then
        assertThat(numberOfDegreeOccurences, equalTo(2));
    }

    @Test
    public void shouldReturnGoodNumberOfZeros(){
        //given
        int degree = 0;
        int [] userDegrees = {3, 4, 4, 0, 0, 2, 1, 6, 9, 0};
        PenaltyEstimator penaltyEstimator = new PenaltyEstimator(userDegrees);

        //when
        int numberOfDegreeOccurences = 0;
        try {
            numberOfDegreeOccurences = penaltyEstimator.getNumberOfDegreeOccurences(degree);
        } catch (NotAllowedDegreeException e) {
            e.printStackTrace();
        }
        //then
        assertThat(numberOfDegreeOccurences, equalTo(3));
    }

    //-----------------------SINGLE ITERATION TEST----------------------------//
    @Test
    public void shouldReturnSingleIterationHesistanceValue(){
        //given
        int degree = 3;
        int [] userDegrees = {1, 2, 3, 5, 3, 7, 3, 4, 3, 2};

        //when
        PenaltyEstimator penaltyEstimator = new PenaltyEstimator(userDegrees);

        //then
        double singleIterationValue = penaltyEstimator.singleIterationHesistanceValue(degree);
        assertThat(singleIterationValue, equalTo(1.333));
    }

    @Test
    public void shouldReturnUserHesistance(){
        //given
        int [] userDegrees = {7, 1, 3, 2, 7, 5, 8, 6, 7, 4};

        //when
        PenaltyEstimator penaltyEstimator = new PenaltyEstimator(userDegrees);

        //then
        double userHesistance = penaltyEstimator.getUserHesitationPenalty();
        assertThat(userHesistance, equalTo(0.067));
        System.out.println("User hesistance : " + userHesistance);
    }

    @Test
    public void getUserLackOfKnowledgePenalty(){
        //given
        int [] userDegrees = {0, 0, 1, 1, 3, 7, 7, 0, 1, 2};

        //when
        PenaltyEstimator penaltyEstimator = new PenaltyEstimator(userDegrees);

        //then
        double lackOfKnowledge = penaltyEstimator.getUserLackOfKnowledgePenalty();
        assertThat(lackOfKnowledge, equalTo(0.533));
        System.out.println("Lack of knowledge : " + lackOfKnowledge);
    }

    @Test
    public void shouldReturnUserLackOfKnowledgeHesistanceValue(){
        //given
        int [] userDegrees = {0, 2, 1, 4, 3, 9, 7, 0, 1, 2};

        //when
        PenaltyEstimator penaltyEstimator = new PenaltyEstimator(userDegrees);

        //then
        double lackOfKnowledgeHesistanceValue = penaltyEstimator.getUserLackOfKnowledgeHesistanceValue();
        double userHesitationPenalty = penaltyEstimator.getUserHesitationPenalty();
        double userLackOfKnowledgePenalty = penaltyEstimator.getUserLackOfKnowledgePenalty();
        double lackOfKnowledgeHesistnaceValue = userLackOfKnowledgePenalty + userHesitationPenalty;
        assertThat(lackOfKnowledgeHesistanceValue, equalTo(lackOfKnowledgeHesistnaceValue));
        System.out.println("User lack of knowledge and hesistance value: " + lackOfKnowledgeHesistanceValue);
    }

}
