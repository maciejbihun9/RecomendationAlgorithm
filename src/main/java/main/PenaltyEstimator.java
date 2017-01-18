package main;

import exceptions.NotAllowedDegreeException;
import tools.Rounder;

/**
 * Created by Maciek on 2016-12-29.
 */
public class PenaltyEstimator {

    private static final int MIN_DEGREE = 0;

    private static final int MAX_DEGREE = 9;

    private int numberOfDegrees = 0;

    private int [] userPreferenceValues;

    public PenaltyEstimator(int [] userDegrees){
        this.userPreferenceValues = userDegrees;
        this.numberOfDegrees = userPreferenceValues.length;
    }

    /**
     * Should return value between 0 and 1.
     * Lower value means that user pays lower value for hesistance in
     * his reccomendation system.
     * @return user system hesistance value.
     */
    public double getUserHesitationPenalty(){
        double userHesistance = 0;
        for(int i = MIN_DEGREE ; i < MAX_DEGREE; i++){
            userHesistance += singleIterationHesistanceValue(i);
        }
        return Rounder.round(userHesistance / numberOfDegrees, 3);
    }

    /**
     * Measure hesistance for one degree value.
     * @param degree
     * @return
     */
    public double singleIterationHesistanceValue(int degree){
        int numberOfDegreeOccurences = 0;
        try {
            numberOfDegreeOccurences = getNumberOfDegreeOccurences(degree);
        } catch (NotAllowedDegreeException e) {
            e.printStackTrace();
        }
        double upFraction = numberOfDegreeOccurences * (numberOfDegreeOccurences - 1);
        double downFraction = numberOfDegrees - 1;
        return Rounder.round(upFraction / downFraction, 3);
    }

    //get k value for each degree
    public int getNumberOfDegreeOccurences(int degree) throws NotAllowedDegreeException {
        if(degree > MAX_DEGREE || degree < MIN_DEGREE)
            throw new NotAllowedDegreeException(degree);
        int degreeCounter = 0;
        for(int i = 0; i < numberOfDegrees; i++){
            if(degree == userPreferenceValues[i]){
                degreeCounter++;
            }
        }
        return degreeCounter;
    }

    //---------------------LOCK OF KNOWLEDGE----------------------------//

    public double getUserLackOfKnowledgePenalty(){
        int elementsWithoutDegree = 0;
        for(Integer preferenceValue : userPreferenceValues){
            if(preferenceValue == 0){
                elementsWithoutDegree++;
            }
        }
        double upFraction = (numberOfDegrees - elementsWithoutDegree) * elementsWithoutDegree;
        double downFraction = numberOfDegrees - 1;
        return Rounder.round((upFraction/downFraction + elementsWithoutDegree) / numberOfDegrees, 3);
    }

    public double getUserLackOfKnowledgeHesistanceValue(){
        return getUserLackOfKnowledgePenalty() + getUserHesitationPenalty();
    }
}
