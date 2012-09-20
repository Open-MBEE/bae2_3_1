package generated;

import gov.nasa.jpl.ae.event.Parameter;
import gov.nasa.jpl.ae.event.IntegerParameter;
import gov.nasa.jpl.ae.event.DoubleParameter;
import gov.nasa.jpl.ae.event.StringParameter;
import gov.nasa.jpl.ae.event.BooleanParameter;
import gov.nasa.jpl.ae.event.Timepoint;
import gov.nasa.jpl.ae.event.Expression;
import gov.nasa.jpl.ae.event.ConstraintExpression;
import gov.nasa.jpl.ae.event.Functions;
import gov.nasa.jpl.ae.event.FunctionCall;
import gov.nasa.jpl.ae.event.Effect;
import gov.nasa.jpl.ae.event.TimeDependentConstraintExpression;
import gov.nasa.jpl.ae.event.Dependency;
import gov.nasa.jpl.ae.event.ElaborationRule;
import gov.nasa.jpl.ae.event.EventInvocation;
import gov.nasa.jpl.ae.event.ParameterListenerImpl;
import gov.nasa.jpl.ae.event.Event;
import gov.nasa.jpl.ae.event.DurativeEvent;
import gov.nasa.jpl.ae.event.TimeVarying;
import gov.nasa.jpl.ae.event.TimeVaryingMap;
import gov.nasa.jpl.ae.util.Utils;
import java.util.Vector;
import java.util.Map;

public class BobCreator extends DurativeEvent {

    public Parameter Bob = null;

    public ElaborationRule elaborationRule652 = null;

    public void initBobCreatorMembers() {
        try {
            Bob = new Parameter("Bob", null, new Power_System(), this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void initBobCreatorCollections() {
        parameters.add(Bob);
    }

    public void initBobCreatorElaborations() {
        Expression<?>[] arguments652 = new Expression<?>[0];
        Expression<Boolean> condition652 = new Expression<Boolean>(true);
        elaborationRule652 = addElaborationRule(condition652, Bob, Power_System._17_0_5_edc0357_1345510113586_863604_13814.class, "Activity", arguments652);
    }

    public BobCreator() {
        super();
        initBobCreatorMembers();
        initBobCreatorCollections();
        initBobCreatorElaborations();
    }

    public BobCreator(Expression<Integer> startTime) {
        super();
        initBobCreatorMembers();
        initBobCreatorCollections();
        removeDependency(this.startTime);
        addDependency(this.startTime, startTime);
        initBobCreatorElaborations();
        fixTimeDependencies();
    }
}
