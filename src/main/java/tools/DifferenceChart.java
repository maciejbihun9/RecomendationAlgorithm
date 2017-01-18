package tools;

import main.User;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.Axis;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickMarkPosition;
import org.jfree.chart.plot.IntervalMarker;
import org.jfree.chart.plot.Marker;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BoxAndWhiskerRenderer;
import org.jfree.chart.renderer.xy.*;
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
        super("no title");

        XYSeries rightAggregateValuesSeries = createXYSeries("Right aggregate values", rightAggregateValues, elementsKnowledgeRatio);
        XYSeries leftAggregateValuesSeries = createXYSeries("Left aggregate values", leftAggregateValues, elementsKnowledgeRatio);

        XYSeriesCollection seriesCollection = new XYSeriesCollection();
        seriesCollection.addSeries(rightAggregateValuesSeries);
        seriesCollection.addSeries(leftAggregateValuesSeries);
        JFreeChart aggregateFreeChart = createJFreeChart(seriesCollection);

        drawIntervalLines(aggregateFreeChart, elementsKnowledgeRatio);
        final ChartPanel chartPanel = new ChartPanel(aggregateFreeChart);

        chartPanel.setPreferredSize(new java.awt.Dimension(900, 670));
        setContentPane(chartPanel);
    }


    public JFreeChart createJFreeChart(XYSeriesCollection xySeriesCollection){
        final JFreeChart chart = ChartFactory.createXYAreaChart(
                "Aggregate values",
                "Set element",
                "Right/Left aggregate values",
                xySeriesCollection
        );
        chart.setBackgroundPaint(Color.gray);

        XYDotRenderer renderer = new XYDotRenderer();
        renderer.setDotHeight(7);
        renderer.setDotWidth(7);
        final XYPlot plot = chart.getXYPlot();
        plot.setRenderer(renderer);


        return chart;
    }

    public XYSeries createXYSeries(String seriesName, double [] aggregateValues, double [] elementsKnowledgeRatio){
        XYSeries xySeries = new XYSeries(seriesName);
        for(int i = 0 ; i < aggregateValues.length; i++){
            //xySeries.add(elementsKnowledgeRatio[i], aggregateValues[i]);
            xySeries.add(i + 1, aggregateValues[i]);
        }
        return xySeries;
    }

    private void drawIntervalLines(JFreeChart chart, double [] elementsKnowledgeRatio){
        XYPlot xyPlot = chart.getXYPlot();
        Font myFont = new Font("Courier", Font.PLAIN, 20);
        for(int i = 1; i <= elementsKnowledgeRatio.length; i++){
            ValueMarker marker = new ValueMarker(i);  // position is the value on the axis
/*            marker.setLabel("x " + i);*/
            marker.setLabelAnchor(RectangleAnchor.BOTTOM );
            marker.setLabelFont(myFont);
            marker.setLabelTextAnchor( TextAnchor.BOTTOM_LEFT );
            marker.setPaint(Color.gray);
            xyPlot.addDomainMarker(marker);



        }
    }

}
