package strategies;

import exceptions.BadNumberOfDegreesException;
import main.Main;
import main.User;
import main.UserProducer;
import org.jfree.ui.RefineryUtilities;
import org.junit.Before;
import org.junit.Test;
import tools.DifferenceChart;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.BaseMatcher.*;

import java.util.List;

/**
 * Created by Maciek on 2017-01-02.
 */
public class MostExperiencedUserStrategyTest {

    private MostExperiencedUserStrategy mostExperiencedUserStrategy;

    private static final int NUMBER_OF_ELEMENTS = 10;

    private double [] elementsKnowledgeRatio;

    @Before
    public void initialize() throws BadNumberOfDegreesException {
        List<User> availableUsers = UserProducer.produceUsersList(250, NUMBER_OF_ELEMENTS);
        mostExperiencedUserStrategy = new MostExperiencedUserStrategy(availableUsers);
        elementsKnowledgeRatio = Main.getElementsKnowledgeRatio(availableUsers);
    }

    @Test
    public void getAggRightLeftDiffIndex(){
        int index = mostExperiencedUserStrategy.indexWithTheLowestDiffBetweenLeftAndRightFunctions();
        System.out.println("Index value : " + index);
    }

    @Test
    public void generateAggregateValuesForTest(){
        double [] leftAggregateValues = new double[NUMBER_OF_ELEMENTS];
        double [] rightAggregateValues = new double[NUMBER_OF_ELEMENTS];
        for(int i = 0 ; i < NUMBER_OF_ELEMENTS; i++){
            leftAggregateValues[i] =  mostExperiencedUserStrategy.getLeftBelongAggregateFunctionValue(i);
            System.out.println("left value: " + i + " " + leftAggregateValues[i]);
            rightAggregateValues[i] =  mostExperiencedUserStrategy.getRightBelongAggregateFunctionValue(i);
            System.out.println("right value: " + i + " " + rightAggregateValues[i]);
        }

        DifferenceChart differenceChart = new DifferenceChart(rightAggregateValues, leftAggregateValues, elementsKnowledgeRatio);
        differenceChart.pack();
        RefineryUtilities.centerFrameOnScreen(differenceChart);
        differenceChart.setVisible(true);
    }

}
