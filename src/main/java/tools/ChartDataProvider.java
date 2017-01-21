package tools;

/**
 * Created by Maciek on 2017-01-18.
 */
public class ChartDataProvider {

    public static double [] getFractionOfProductsBetterThanElement(double[] rightAggregateValues){
        double [] fractionsOfProductsBetterThanElement = new double[rightAggregateValues.length];
        for(int i = 0; i < rightAggregateValues.length; i++){
            fractionsOfProductsBetterThanElement[i] = 1 - rightAggregateValues[i];
        }
        return fractionsOfProductsBetterThanElement;
    }

    public static double [] getFractionOfProductsWorstThanElement(double[] leftAggregateValues){
        double [] fractionsOfProductsWorstThanElement = new double[leftAggregateValues.length];
        for(int i = 0; i < leftAggregateValues.length; i++){
            fractionsOfProductsWorstThanElement[i] = leftAggregateValues[i];
        }
        return fractionsOfProductsWorstThanElement;
    }

    public static double [] getCenterAreaForElement(double[] fractionOfProductsBetterThanElement, double[] fractionOfProductsWorstThanElement){
        double [] centerAreasForElement = new double[fractionOfProductsBetterThanElement.length];
        for(int i = 0; i < fractionOfProductsBetterThanElement.length; i++){
            centerAreasForElement[i] = 1 - fractionOfProductsBetterThanElement[i] - fractionOfProductsWorstThanElement[i];
        }
        return centerAreasForElement;
    }

}
