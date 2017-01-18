package main;

import org.jfree.ui.RefineryUtilities;
import strategies.MostExperiencedUserStrategy;
import tools.DifferenceChart;

import java.util.List;

/**
 * Created by Maciek on 2016-12-25.
 */
public class Main {

    private static final int NUMBER_OF_ELEMENTS = SystemConstants.NUMBER_OF_DEGREES;

    public static void main(String[] args) {
        List<User> availableUsers = UserProducer.produceUsersList(250, NUMBER_OF_ELEMENTS);
        MostExperiencedUserStrategy mostExperiencedUserStrategy = new MostExperiencedUserStrategy(availableUsers);

        double [] leftAggregateValues = new double[NUMBER_OF_ELEMENTS];
        double [] rightAggregateValues = new double[NUMBER_OF_ELEMENTS];
        for(int i = 0 ; i < NUMBER_OF_ELEMENTS; i++){
            leftAggregateValues[i] =  mostExperiencedUserStrategy.getLeftBelongAggregateFunctionValue(i);
            System.out.println("left value: " + i + " " + leftAggregateValues[i]);
            rightAggregateValues[i] =  mostExperiencedUserStrategy.getRightBelongAggregateFunctionValue(i);
            System.out.println("right value: " + i + " " + rightAggregateValues[i]);
        }

        DifferenceChart differenceChart = new DifferenceChart(rightAggregateValues, leftAggregateValues,
                getElementsKnowledgeRatio(availableUsers));
        differenceChart.pack();
        RefineryUtilities.centerFrameOnScreen(differenceChart);
        differenceChart.setVisible(true);
    }

    public static double [] getElementsKnowledgeRatio(List<User> userList){
        int elementsKnowledgeCounter [] = new int[userList.get(0).getPreferenceValues().length];
        for(User user : userList){
            int[] userPreferences = user.getPreferenceValues();
            for(int i = 0 ; i < userPreferences.length; i++){
                if(userPreferences[i] != 0){
                    elementsKnowledgeCounter[i] = elementsKnowledgeCounter[i] + 1;
                }
            }
        }
        double [] elementsKnowledgeRatio = new double[userList.get(0).getPreferenceValues().length];
        for(int i = 0 ; i < elementsKnowledgeRatio.length; i++){
            elementsKnowledgeRatio[i] = (double)elementsKnowledgeCounter[i] / (double)userList.size();
        }
        return elementsKnowledgeRatio;
    }

}
