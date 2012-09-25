package latest;

import gov.nasa.jpl.ae.event.TimeVarying;
import gov.nasa.jpl.ae.event.TimeVaryingMap;
import gov.nasa.jpl.ae.event.TimeVaryingPlottableMap;
import gov.nasa.jpl.ae.event.Expression;
import gov.nasa.jpl.ae.event.Timepoint;

public class Main {

    public static void main(String[] args) {
        Timepoint.setUnits("seconds");
        Timepoint.setEpoch("Sun Aug 05 23:30:00 PDT 2012");
        Timepoint.setHorizonDuration(72000);
        BobCreator BobCreator675 = new BobCreator(new Expression<Integer>(0));
        BobCreator675.execute();
    }
}
