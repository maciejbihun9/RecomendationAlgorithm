package main;

import tools.Rounder;

import java.util.List;

/**
 * Created by Maciek on 2017-01-02.
 */
public class ModifiedSimilarityMeasure {

    private List<User> systemUsers;

    public ModifiedSimilarityMeasure(List<User> systemUsers){
        this.systemUsers = systemUsers;
    }

    public double getAggregateBelongnessForItem(int itemIndex){
        return Rounder.round(getEquationPrefix(itemIndex) * getBelongSumValueForAllUsersForGivenItem(itemIndex), 3);
    }

    public double getAggregateNonBelongness(int itemIndex){
        return Rounder.round(getEquationPrefix(itemIndex) * getNonBelongSumValueForAllUsersForGivenItem(itemIndex), 3);
    }

    public double getEquationPrefix(int itemIndex){
        double numberOfAvailablePrefs = 0;
        for(User systemUser : systemUsers){
            SystemExplorer systemExplorer = new SystemExplorer(systemUser.getPreferenceValues());
            if(systemExplorer.getBelongResult(itemIndex) > 0 || systemExplorer.getNonBelongResult(itemIndex) > 0){
                numberOfAvailablePrefs++;
            }
        }
        return Rounder.round(1 / numberOfAvailablePrefs, 3);
    }

    //-----------------------------BELONGNESS----------------------------------------//

    public double getBelongSumValueForAllUsersForGivenItem(int itemIndex){
        double systemUsersSum = 0;
        for(User systemUser : systemUsers){
            int[] userPreferenceValues = systemUser.getPreferenceValues();
            SystemExplorer systemExplorer = new SystemExplorer(userPreferenceValues);
            double belongResult = systemExplorer.getBelongResult(itemIndex);
            systemUsersSum += belongResult;
        }
        return systemUsersSum;
    }



    //-----------------------------NONBELONGNESS----------------------------------------//


    public double getNonBelongSumValueForAllUsersForGivenItem(int itemIndex){
        double systemUsersSum = 0;
        for(User systemUser : systemUsers){
            int[] userPreferenceValues = systemUser.getPreferenceValues();
            SystemExplorer systemExplorer = new SystemExplorer(userPreferenceValues);
            double nonBelongResult = systemExplorer.getNonBelongResult(itemIndex);
            systemUsersSum += nonBelongResult;
        }
        return systemUsersSum;
    }

}
