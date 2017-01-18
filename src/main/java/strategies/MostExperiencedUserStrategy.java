package strategies;

import main.ModifiedSimilarityMeasure;
import main.SystemConstants;
import main.User;
import tools.Rounder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maciek on 2017-01-02.
 */
public class MostExperiencedUserStrategy {

    private List<User> availableUsers;

    private ModifiedSimilarityMeasure modifiedSimilarityMeasure;

    public MostExperiencedUserStrategy(List<User> availableUsers){
        this.availableUsers = availableUsers;
        modifiedSimilarityMeasure = new ModifiedSimilarityMeasure(availableUsers);
    }

    public int indexWithTheLowestDiffBetweenLeftAndRightFunctions(){
        int seekingIndex = 0;
        double theLowestMargin = getRightBelongAggregateFunctionValue(seekingIndex) -
                getLeftBelongAggregateFunctionValue(seekingIndex);
        List<Integer> rightAggregateIndexes = getProductIndexesWithRightAggregateFunctionHigherThanHalf();
        for(int itemIndex = 1; itemIndex < rightAggregateIndexes.size(); itemIndex++){
            double aggMargin = getRightBelongAggregateFunctionValue(itemIndex) -
                    getLeftBelongAggregateFunctionValue(itemIndex);
            if(aggMargin < theLowestMargin){
                theLowestMargin = aggMargin;
                seekingIndex = itemIndex;
            }
        }
        return seekingIndex;
    }

    private List<Integer> getProductIndexesWithRightAggregateFunctionHigherThanHalf(){
        List<Integer> indexesList = new ArrayList<Integer>();
        for(int i = 0 ; i < SystemConstants.NUMBER_OF_DEGREES; i++){
            if(getRightBelongAggregateFunctionValue(i) >= 0.5){
                indexesList.add(i);
            }
        }
        return indexesList;
    }

    public double getRightBelongAggregateFunctionValue(int itemIndex){
        return Rounder.round(1 - modifiedSimilarityMeasure.getAggregateNonBelongness(itemIndex), 3);
    }

    public double getLeftBelongAggregateFunctionValue(int itemIndex){
        return Rounder.round(modifiedSimilarityMeasure.getAggregateBelongnessForItem(itemIndex), 3);
    }

}
