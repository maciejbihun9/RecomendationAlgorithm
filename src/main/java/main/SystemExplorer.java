package main;

import tools.Rounder;

import java.util.List;

/**
 * Created by Maciek on 2016-12-26.
 */
public class SystemExplorer {

    private int [] userPrefValues;

    public SystemExplorer(int [] userPrefValues){
        this.userPrefValues = userPrefValues;
    }

    //get mi function result
    public double getBelongResult(int index){
        double wa = getWaParam(index);
        double numberOfElements = userPrefValues.length;
        return Rounder.round(wa / (numberOfElements - 1), 3);
    }

    //get va function result
    public double getNonBelongResult(int index){
        double ba = getBaParam(index);
        double numberOfElements = userPrefValues.length;
        return Rounder.round(ba / (numberOfElements - 1), 3);
    }

    public int getWaParam(int index){
        if(userPrefValues[index] == 0)
            return 0;
        int wa = 0;
        for (int i = 0; i < userPrefValues.length; i++) {
            if(userPrefValues[i] == 0)
                continue;
            if(userPrefValues[i] == userPrefValues[index])
                continue;
            if(userPrefValues[i] < userPrefValues[index])
                wa++;
        }
        return wa;
    }

    public int getBaParam(int index){
        if(userPrefValues[index] == 0)
            return 0;
        int ba = 0;
        for (int i = 0; i < userPrefValues.length; i++) {
            if(userPrefValues[i] == 0)
                continue;
            if(userPrefValues[i] == userPrefValues[index])
                continue;
            if(userPrefValues[i] > userPrefValues[index])
                ba++;
        }
        return ba;
    }

}
