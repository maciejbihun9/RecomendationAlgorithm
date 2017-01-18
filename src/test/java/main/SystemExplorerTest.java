package main;

import exceptions.BadNumberOfDegreesException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.BaseMatcher.*;

import java.util.List;

/**
 * Created by Maciek on 2016-12-26.
 */
public class SystemExplorerTest {

    @Test
    public void shouldReturnConvenientWaAndBaParameters() throws BadNumberOfDegreesException {
        //given
        int index = 3;
        int [] userPreferenceValues1 = {0,0,1,2,3,0,3,5,2,2};// wa = 1, ba = 3
        int [] userPreferenceValues2 = {0,0,1,0,0,0,0,5,0,2};// wa = 0, ba = 0
        int [] userPreferenceValues3 = {1,2,1,2,3,9,3,5,2,2};// wa = 2, ba = 4
        int [] userPreferenceValues4 = {1,1,1,9,1,1,6,7,8,4};// wa = 9, ba = 0

        //when
        SystemExplorer systemExplorer1 = new SystemExplorer(userPreferenceValues1);
        SystemExplorer systemExplorer2 = new SystemExplorer(userPreferenceValues2);
        SystemExplorer systemExplorer3 = new SystemExplorer(userPreferenceValues3);
        SystemExplorer systemExplorer4 = new SystemExplorer(userPreferenceValues4);

        int waParam1 = systemExplorer1.getWaParam(index);
        int waParam2 = systemExplorer2.getWaParam(index);
        int waParam3 = systemExplorer3.getWaParam(index);

        int baParam1 = systemExplorer1.getBaParam(index);
        int baParam2 = systemExplorer2.getBaParam(index);
        int baParam3 = systemExplorer3.getBaParam(index);

        double belongResult1 = systemExplorer1.getBelongResult(index);
        double belongResult2 = systemExplorer2.getBelongResult(index);
        double belongResult3 = systemExplorer3.getBelongResult(index);
        double belongResult4 = systemExplorer4.getBelongResult(index);

        double nonBelongResult4 = systemExplorer4.getNonBelongResult(index);

        //then
        assertThat(belongResult1, equalTo(0.111));
        assertThat(belongResult2, equalTo(0.0));
        assertThat(belongResult3, equalTo(0.222));
        assertThat(belongResult4, equalTo(1.0));
        assertThat(nonBelongResult4, equalTo(0.0));

        assertThat(waParam1, equalTo(1));
        assertThat(waParam2, equalTo(0));
        assertThat(waParam3, equalTo(2));

        assertThat(baParam1, equalTo(3));
        assertThat(baParam2, equalTo(0));
        assertThat(baParam3, equalTo(4));
    }

}
