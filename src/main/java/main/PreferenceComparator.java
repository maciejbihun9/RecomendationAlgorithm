package main;

import exceptions.BadUserIndexException;
import exceptions.UserWithoutNotDescribedPreferencesException;
import tools.Rounder;

public class PreferenceComparator {

    private int [] userOnePreferenceSystem;

    private int [] userTwoPreferenceSystem;

    private int numberOfElements;

    public PreferenceComparator(User userOne, User userTwo) {
        this.userOnePreferenceSystem = userOne.getPreferenceValues();
        this.userTwoPreferenceSystem = userTwo.getPreferenceValues();
        this.numberOfElements = userOnePreferenceSystem.length;
    }

    public double measureEuclideanDistance()  {

        int prValLength = userOnePreferenceSystem.length;
        SystemExplorer userOneSystem = new SystemExplorer(userOnePreferenceSystem);
        SystemExplorer userTwoSystem = new SystemExplorer(userTwoPreferenceSystem);
        double result = 0;
        for(int i = 0 ;i < prValLength; i++){
            //for each value measure belon and nonbelong parameters.
            double oneIterationMeasure = oneElementMeasure(userOneSystem.getBelongResult(i), userTwoSystem.getBelongResult(i),
                    userOneSystem.getNonBelongResult(i), userTwoSystem.getNonBelongResult(i));
            result += oneIterationMeasure;
        }
        return Rounder.round(Math.sqrt(result / 2), 2);
    }

    public double measureNormalizedSimilarity(){
        double upFraction = 3 * (numberOfElements - 1);
        double downFraction = numberOfElements * (numberOfElements + 1);
        double underSqrt = upFraction / downFraction;
        double measure = measureEuclideanDistance();
        return Rounder.round(1 - (Math.sqrt(underSqrt)) * measureEuclideanDistance(), 2);
    }

    public double oneElementMeasure(double mi1, double mi2, double v1, double v2){
        double fullNumber = Math.pow((mi1 - mi2), 2) + Math.pow((v1 - v2), 2);
        return Rounder.round(fullNumber, 2);
    }

    /**
     *
     * @param userIndex
     * @return More percise information about User preference system similarity.
     * @throws BadUserIndexException
     * @throws UserWithoutNotDescribedPreferencesException
     */
    public double getPreferencesSimilarityWithPenalty(int userIndex) throws BadUserIndexException,
            UserWithoutNotDescribedPreferencesException {
        //Initialize PeanaltyEstimator with preferences that contains NA values.
        int[] userPreferencesWithNotDescribedElements = getUserPreferences(userIndex);
        if(!preferencesContainsNotDescribedValues(userPreferencesWithNotDescribedElements))
            throw new UserWithoutNotDescribedPreferencesException();
        PenaltyEstimator penaltyEstimator = new PenaltyEstimator(userPreferencesWithNotDescribedElements);
        return Rounder.round(measureNormalizedSimilarity() * (1 - penaltyEstimator.getUserLackOfKnowledgePenalty()), 3);
    }

    private boolean preferencesContainsNotDescribedValues(int [] userPreferenceValues){
        for(Integer userPreference : userPreferenceValues)
            if(userPreference == 0)
                return true;
        return false;
    }

    private int [] getUserPreferences(int userIndex) throws BadUserIndexException {
        if(userIndex == 0)
            return userOnePreferenceSystem;
        else if(userIndex == 1)
            return userTwoPreferenceSystem;
        else
            throw new BadUserIndexException();

    }


}
