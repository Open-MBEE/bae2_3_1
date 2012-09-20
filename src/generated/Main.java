package generated;

import gov.nasa.jpl.ae.event.TimeVarying;
import gov.nasa.jpl.ae.event.TimeVaryingMap;
import gov.nasa.jpl.ae.event.Expression;
import gov.nasa.jpl.ae.event.Timepoint;

public class Main {

    public static void main(String[] args) {
        Timepoint.setUnits("seconds");
        Timepoint.setEpoch("Sun Aug 05 23:30:00 PDT 2012");
        BobCreator BobCreator653 = new BobCreator(new Expression<Integer>(0));
        BobCreator653.execute();
    }
}
