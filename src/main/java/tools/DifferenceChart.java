package tools;

import main.User;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.Axis;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickMarkPosition;
import org.jfree.chart.plot.*;
import org.jfree.chart.renderer.category.BoxAndWhiskerRenderer;
import org.jfree.chart.renderer.category.GroupedStackedBarRenderer;
import org.jfree.chart.renderer.xy.*;
import org.jfree.data.KeyToGroupMap;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.Day;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.Layer;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.TextAnchor;

import java.awt.*;
import java.util.List;

/**
 * Created by Maciek on 2017-01-06.
 */
public class DifferenceChart extends ApplicationFrame {

    public DifferenceChart(double[] rightAggregateValues, double[] leftAggregateValues, double [] elementsKnowledgeRatio) {
        super("Difference chart");

        double[] fractionOfProductsBetterThanElement = ChartDataProvider.getFractionOfProductsBetterThanElement(rightAggregateValues);

        double[] fractionOfProductsWorstThanElement = ChartDataProvider.getFractionOfProductsWorstThanElement(leftAggregateValues);

        double[] centerAreasForElement = ChartDataProvider.getCenterAreaForElement(fractionOfProductsBetterThanElement, fractionOfProductsWorstThanElement);

        JFreeChart aggregateFreeChart = createJFreeChart(fractionOfProductsBetterThanElement,
                centerAreasForElement, fractionOfProductsWorstThanElement);

        final ChartPanel chartPanel = new ChartPanel(aggregateFreeChart);

        chartPanel.setPreferredSize(new java.awt.Dimension(900, 670));
        setContentPane(chartPanel);

        printRecomendation(centerAreasForElement, fractionOfProductsBetterThanElement);
    }


    public JFreeChart createJFreeChart(double [] rightFraction, double [] centerFraction, double [] leftFraction){
        final JFreeChart chart = ChartFactory.createStackedBarChart(
                "Aggregate values",
                "Set element",
                "Right/Left aggregate values",
                createDataset(rightFraction, centerFraction, leftFraction),
                PlotOrientation.VERTICAL,    // the plot orientation
                true,                        // legend
                true,                        // tooltips
                false
        );
        chart.setBackgroundPaint(Color.gray);
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        GroupedStackedBarRenderer groupStackedRenderer = createGroupStackedRenderer();
        plot.setRenderer(groupStackedRenderer);
        return chart;
    }

    private CategoryDataset createDataset(double [] rightfraction, double [] centerFraction, double [] leftFraction){
        int numberOfElements = rightfraction.length;
        DefaultCategoryDataset result = new DefaultCategoryDataset();
        for(int i = 0; i < numberOfElements; i++){
            result.addValue(leftFraction[i], "worse" , i + 1 + "");
            result.addValue(centerFraction[i], "center" , i + 1 + "");
            result.addValue(rightfraction[i], "better", i + 1 + "");
        }
        return result;
    }

    private GroupedStackedBarRenderer createGroupStackedRenderer(){
        GroupedStackedBarRenderer renderer = new GroupedStackedBarRenderer();
        KeyToGroupMap map = new KeyToGroupMap("G1");
        map.mapKeyToGroup("better", "G1");
        map.mapKeyToGroup("center", "G1");
        map.mapKeyToGroup("worse", "G1");
        renderer.setSeriesToGroupMap(map);
        renderer.setItemMargin(0.0);
        Paint p1 = new GradientPaint(
                0.0f, 0.0f, new Color(0x22, 0x22, 0xFF), 0.0f, 0.0f, new Color(0x88, 0x88, 0xFF)
        );
        renderer.setSeriesPaint(0, p1);
        renderer.setSeriesPaint(3, p1);
        renderer.setSeriesPaint(7, p1);

        Paint p2 = new GradientPaint(
                0.0f, 0.0f, new Color(0x22, 0xFF, 0x22), 0.0f, 0.0f, new Color(0x88, 0xFF, 0x88)
        );
        renderer.setSeriesPaint(1, p2);
        renderer.setSeriesPaint(4, p2);
        renderer.setSeriesPaint(8, p2);

        Paint p3 = new GradientPaint(
                0.0f, 0.0f, new Color(0xFF, 0x22, 0x22), 0.0f, 0.0f, new Color(0xFF, 0x88, 0x88)
        );
        renderer.setSeriesPaint(2, p3);
        renderer.setSeriesPaint(5, p3);
        renderer.setSeriesPaint(9, p3);

        return renderer;
    }

    /*
    print the most popular and the best items
     */
    private void printRecomendation(double [] centerValues, double [] rightValues){
        int valuesLength = centerValues.length;
        int theMostPopularItem = 0;
        int theBestItem = 0;
        for(int i = 0;i < valuesLength; i++){
            if(centerValues[i] < centerValues[theMostPopularItem])
                theMostPopularItem = i;
            if(rightValues[i] < rightValues[theBestItem])
                theBestItem = i;
            System.out.println("right value : " + rightValues[i]);
        }
        //for chart legend needs
        theBestItem = theBestItem + 1;
        theMostPopularItem = theMostPopularItem + 1;
        System.out.println("The best item : " + theBestItem);
        System.out.println("The most popular item : " + theMostPopularItem);
    }

}
