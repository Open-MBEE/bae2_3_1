package latest;

import gov.nasa.jpl.ae.event.TimeVarying;
import gov.nasa.jpl.ae.event.TimeVaryingMap;
import gov.nasa.jpl.ae.event.TimeVaryingPlottableMap;
import gov.nasa.jpl.ae.event.Expression;
import gov.nasa.jpl.ae.event.Timepoint;

public class Main extends BobCreator {

    public Main() {
        super(new Expression<Integer>( 0 ));
    }

    public static void main(String[] args) {
        Timepoint.setUnits("seconds");
        Timepoint.setEpoch("Sun Aug 05 23:30:00 PDT 2012");
        Timepoint.setHorizonDuration(86400);
        Main scenario = new Main();
        scenario.executeAndSimulate();
    }
}
