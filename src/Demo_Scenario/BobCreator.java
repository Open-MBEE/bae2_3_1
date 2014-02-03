package Demo_Scenario;

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
import gov.nasa.jpl.ae.event.ConstructorCall;
import gov.nasa.jpl.ae.event.Call;
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
import gov.nasa.jpl.ae.event.TimeVaryingPlottableMap;
import gov.nasa.jpl.mbee.util.ClassUtils;
import gov.nasa.jpl.mbee.util.Utils;

import java.util.Vector;
import java.util.Map;

public class BobCreator extends DurativeEvent {

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
        addDependency(this.startTime, startTime);
        initBobCreatorElaborations();
        fixTimeDependencies();
    }

    public Parameter< Power_System > Bob = null;

    public ElaborationRule elaborationRule652 = null;

    public void initBobCreatorMembers() {
        try {
            if (Bob == null) Bob = new Parameter<Power_System>("Bob", null, (Power_System) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(Power_System.class), new Object[] {})).evaluate(true), this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void initBobCreatorCollections() {
        parameters.add(Bob);
    }

    public void initBobCreatorDependencies() {
    }

    public void initBobCreatorElaborations() {
        initBobCreatorDependencies();
        Expression<?>[] arguments652 = new Expression<?>[2];
        arguments652[0] = new Expression<DurativeEvent>(this);
        arguments652[1] = new Expression<Integer>(startTime);
        Expression<Boolean> condition652 = new Expression<Boolean>(true);
        elaborationRule652 = addElaborationRule(condition652, Bob, Power_System._17_0_2_1_edc0357_1360798765060_634347_20307.class, "start_system_Activity_Power_System", arguments652);
    }
}
