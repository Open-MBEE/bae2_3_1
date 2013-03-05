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
import gov.nasa.jpl.ae.util.Utils;
import gov.nasa.jpl.ae.util.ClassUtils;
import java.util.Vector;
import java.util.Map;
import java.util.Queue;
import java.util.LinkedList;
import gov.nasa.jpl.ae.fuml.ObjectFlow;
import gov.nasa.jpl.ae.event.TimeVaryingList;
import gov.nasa.jpl.ae.event.TimeVaryingProjection;
import java.lang.Math;
import java.util.Set;
import java.util.TreeSet;
import gov.nasa.jpl.ae.event.EffectFunction;

public class Customer extends ParameterListenerImpl {

    public Customer() {
        super();
        initCustomerMembers();
        initCustomerCollections();
        initCustomerElaborations();
    }

    public class _17_0_2_1_edc0357_1360798765036_519893_20269 extends DurativeEvent {

        public _17_0_2_1_edc0357_1360798765036_519893_20269() {
            super();
            init_17_0_2_1_edc0357_1360798765036_519893_20269Members();
            init_17_0_2_1_edc0357_1360798765036_519893_20269Collections();
            init_17_0_2_1_edc0357_1360798765036_519893_20269Elaborations();
        }

        public class _17_0_2_1_edc0357_1360798765708_699932_20872 extends DurativeEvent {

            public _17_0_2_1_edc0357_1360798765708_699932_20872() {
                super();
                init_17_0_2_1_edc0357_1360798765708_699932_20872Members();
                init_17_0_2_1_edc0357_1360798765708_699932_20872Collections();
                init_17_0_2_1_edc0357_1360798765708_699932_20872Elaborations();
            }

            public _17_0_2_1_edc0357_1360798765708_699932_20872(Expression<Integer> startTime) {
                super();
                init_17_0_2_1_edc0357_1360798765708_699932_20872Members();
                init_17_0_2_1_edc0357_1360798765708_699932_20872Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_1_edc0357_1360798765708_699932_20872Elaborations();
                fixTimeDependencies();
            }

            public DoubleParameter _17_0_2_1_edc0357_1360798766415_665337_22043 = null;

            public BooleanParameter _17_0_2_1_edc0357_1360798765712_241699_20878_exists = null;

            public BooleanParameter objectToPass = null;

            public BooleanParameter addToDecider__17_0_2_1_edc0357_1360798765712_241699_20878 = null;

            public IntegerParameter myDeciderID_decider_17_0_2_1_edc0357_1360798765712_241699_20878 = null;

            public Dependency< Double > _17_0_2_1_edc0357_1360798766415_665337_22043Dependency = null;

            public Dependency< Boolean > _17_0_2_1_edc0357_1360798765712_241699_20878_existsDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Dependency< Boolean > addToDecider__17_0_2_1_edc0357_1360798765712_241699_20878Dependency = null;

            public Dependency< Integer > myDeciderID_decider_17_0_2_1_edc0357_1360798765712_241699_20878Dependency = null;

            public Effect effect34 = null;

            public Parameter effect34Var = null;

            public Effect effect35 = null;

            public Parameter effect35Var = null;

            public Effect effect36 = null;

            public Parameter effect36Var = null;

            public ElaborationRule elaborationRule37 = null;

            public void init_17_0_2_1_edc0357_1360798765708_699932_20872Members() {
                try {
                    if (_17_0_2_1_edc0357_1360798766415_665337_22043 == null) _17_0_2_1_edc0357_1360798766415_665337_22043 = new DoubleParameter("_17_0_2_1_edc0357_1360798766415_665337_22043", (Double) null, this);
                    if (_17_0_2_1_edc0357_1360798765712_241699_20878_exists == null) _17_0_2_1_edc0357_1360798765712_241699_20878_exists = new BooleanParameter("_17_0_2_1_edc0357_1360798765712_241699_20878_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (addToDecider__17_0_2_1_edc0357_1360798765712_241699_20878 == null) addToDecider__17_0_2_1_edc0357_1360798765712_241699_20878 = new BooleanParameter("addToDecider__17_0_2_1_edc0357_1360798765712_241699_20878", (Boolean) false, this);
                    if (myDeciderID_decider_17_0_2_1_edc0357_1360798765712_241699_20878 == null) myDeciderID_decider_17_0_2_1_edc0357_1360798765712_241699_20878 = new IntegerParameter("myDeciderID_decider_17_0_2_1_edc0357_1360798765712_241699_20878", (Integer) 2, this);
                    Object effect34VarV = sig_17_0_2_1_edc0357_1360798765716_333668_20887;
                    effect34Var = new Parameter("effect34Var", null, null, this);
                    addDependency(effect34Var, new Expression(effect34VarV));
                    effect34 = new EffectFunction(new EffectFunction(effect34Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "Demo_Scenario", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect35VarV = decider_17_0_2_1_edc0357_1360798765712_241699_20878;
                    effect35Var = new Parameter("effect35Var", null, null, this);
                    addDependency(effect35Var, new Expression(effect35VarV));
                    effect35 = new EffectFunction(new EffectFunction(effect35Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "Demo_Scenario", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class, java.lang.Boolean.class), new Object[] { endTime, myDeciderID_decider_17_0_2_1_edc0357_1360798765712_241699_20878, addToDecider__17_0_2_1_edc0357_1360798765712_241699_20878 }));
                    Object effect36VarV = reduction_level__17_0_2_1_edc0357_1360798765043_828353_20277;
                    effect36Var = new Parameter("effect36Var", null, null, this);
                    addDependency(effect36Var, new Expression(effect36VarV));
                    effect36 = new EffectFunction(new EffectFunction(effect36Var, ClassUtils.getMethodForArgTypes("TimeVaryingMap<Float>", "Demo_Scenario", "setValue", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { startTime, _17_0_2_1_edc0357_1360798766415_665337_22043 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_1_edc0357_1360798765708_699932_20872Collections() {
                parameters.add(_17_0_2_1_edc0357_1360798766415_665337_22043);
                parameters.add(_17_0_2_1_edc0357_1360798765712_241699_20878_exists);
                parameters.add(objectToPass);
                parameters.add(addToDecider__17_0_2_1_edc0357_1360798765712_241699_20878);
                parameters.add(myDeciderID_decider_17_0_2_1_edc0357_1360798765712_241699_20878);
                Set<Effect> effectsForeffect34Var = new TreeSet<Effect>();
                effectsForeffect34Var.add(effect34);
                addEffects((Parameter<?>) effect34Var, effectsForeffect34Var);
                Set<Effect> effectsForeffect35Var = new TreeSet<Effect>();
                effectsForeffect35Var.add(effect35);
                addEffects((Parameter<?>) effect35Var, effectsForeffect35Var);
                Set<Effect> effectsForeffect36Var = new TreeSet<Effect>();
                effectsForeffect36Var.add(effect36);
                addEffects((Parameter<?>) effect36Var, effectsForeffect36Var);
            }

            public void init_17_0_2_1_edc0357_1360798765708_699932_20872Dependencies() {
                addDependency(_17_0_2_1_edc0357_1360798766415_665337_22043, new Expression<Double>(new EffectFunction(sig_17_0_2_1_edc0357_1360798765716_835048_20886, ClassUtils.getMethodForArgTypes("ObjectFlow<Float>", "Demo_Scenario", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_1_edc0357_1360798765712_241699_20878_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765709_595827_20874_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765709_595827_20874_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_1_edc0357_1360798765712_241699_20878, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "Demo_Scenario", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_1_edc0357_1360798765712_241699_20878, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "Demo_Scenario", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_1_edc0357_1360798765712_241699_20878, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "Demo_Scenario", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_1_edc0357_1360798765712_241699_20878)))))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new EffectFunction(sig_17_0_2_1_edc0357_1360798765715_639545_20884, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "Demo_Scenario", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(addToDecider__17_0_2_1_edc0357_1360798765712_241699_20878, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765709_595827_20874_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765709_595827_20874_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(myDeciderID_decider_17_0_2_1_edc0357_1360798765712_241699_20878, new Expression<Integer>(2));
            }

            public void init_17_0_2_1_edc0357_1360798765708_699932_20872Elaborations() {
                init_17_0_2_1_edc0357_1360798765708_699932_20872Dependencies();
                Expression<?>[] arguments37 = new Expression<?>[1];
                arguments37[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition37 = new Expression<Boolean>(_17_0_2_1_edc0357_1360798765712_241699_20878_exists);
                elaborationRule37 = addElaborationRule(condition37, _17_0_2_1_edc0357_1360798765036_519893_20269.this, Customer._17_0_2_1_edc0357_1360798765036_519893_20269._17_0_2_1_edc0357_1360798765712_241699_20878.class, "CDR_send_yes_SendSignalAction_setDRCap", arguments37);
            }
        }

        public class _17_0_2_1_edc0357_1360798765708_946798_20873 extends DurativeEvent {

            public _17_0_2_1_edc0357_1360798765708_946798_20873() {
                super();
                init_17_0_2_1_edc0357_1360798765708_946798_20873Members();
                init_17_0_2_1_edc0357_1360798765708_946798_20873Collections();
                init_17_0_2_1_edc0357_1360798765708_946798_20873Elaborations();
            }

            public _17_0_2_1_edc0357_1360798765708_946798_20873(Expression<Integer> startTime) {
                super();
                init_17_0_2_1_edc0357_1360798765708_946798_20873Members();
                init_17_0_2_1_edc0357_1360798765708_946798_20873Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_1_edc0357_1360798765708_946798_20873Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter objectToPass = null;

            public BooleanParameter _17_0_2_1_edc0357_1360951536105_515998_20307_exists = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Dependency< Boolean > _17_0_2_1_edc0357_1360951536105_515998_20307_existsDependency = null;

            public Effect effect38 = null;

            public Parameter effect38Var = null;

            public ElaborationRule elaborationRule39 = null;

            public void init_17_0_2_1_edc0357_1360798765708_946798_20873Members() {
                try {
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (_17_0_2_1_edc0357_1360951536105_515998_20307_exists == null) _17_0_2_1_edc0357_1360951536105_515998_20307_exists = new BooleanParameter("_17_0_2_1_edc0357_1360951536105_515998_20307_exists", (Boolean) false, this);
                    Object effect38VarV = sig_17_0_2_1_edc0357_1360798765717_84499_20896;
                    effect38Var = new Parameter("effect38Var", null, null, this);
                    addDependency(effect38Var, new Expression(effect38VarV));
                    effect38 = new EffectFunction(new EffectFunction(effect38Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "Demo_Scenario", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_1_edc0357_1360798765708_946798_20873Collections() {
                parameters.add(objectToPass);
                parameters.add(_17_0_2_1_edc0357_1360951536105_515998_20307_exists);
                Set<Effect> effectsForeffect38Var = new TreeSet<Effect>();
                effectsForeffect38Var.add(effect38);
                addEffects((Parameter<?>) effect38Var, effectsForeffect38Var);
            }

            public void init_17_0_2_1_edc0357_1360798765708_946798_20873Dependencies() {
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(true));
                addDependency(_17_0_2_1_edc0357_1360951536105_515998_20307_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765709_595827_20874_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765709_595827_20874_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
            }

            public void init_17_0_2_1_edc0357_1360798765708_946798_20873Elaborations() {
                init_17_0_2_1_edc0357_1360798765708_946798_20873Dependencies();
                Expression<?>[] arguments39 = new Expression<?>[1];
                arguments39[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition39 = new Expression<Boolean>(_17_0_2_1_edc0357_1360951536105_515998_20307_exists);
                elaborationRule39 = addElaborationRule(condition39, _17_0_2_1_edc0357_1360798765036_519893_20269.this, Customer._17_0_2_1_edc0357_1360798765036_519893_20269._17_0_2_1_edc0357_1360951536105_515998_20307.class, "CDR_get_participation_ReadStructuralFeatureAction_setDRCap", arguments39);
            }
        }

        public class _17_0_2_1_edc0357_1360798765709_595827_20874 extends DurativeEvent {

            public _17_0_2_1_edc0357_1360798765709_595827_20874() {
                super();
                init_17_0_2_1_edc0357_1360798765709_595827_20874Members();
                init_17_0_2_1_edc0357_1360798765709_595827_20874Collections();
                init_17_0_2_1_edc0357_1360798765709_595827_20874Elaborations();
            }

            public _17_0_2_1_edc0357_1360798765709_595827_20874(Expression<Integer> startTime) {
                super();
                init_17_0_2_1_edc0357_1360798765709_595827_20874Members();
                init_17_0_2_1_edc0357_1360798765709_595827_20874Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_1_edc0357_1360798765709_595827_20874Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter objectToPass = null;

            public Dependency< Integer > endTimeDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Dependency< Integer > finalNode_endTimeDependency = null;

            public void init_17_0_2_1_edc0357_1360798765709_595827_20874Members() {
                try {
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_1_edc0357_1360798765709_595827_20874Collections() {
                parameters.add(objectToPass);
            }

            public void init_17_0_2_1_edc0357_1360798765709_595827_20874Dependencies() {
                addDependency(endTime, new Expression<Integer>(new Functions.Plus(new Expression<Integer>(startTime), new Expression<Integer>(duration))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new EffectFunction(sig_17_0_2_1_edc0357_1360798765718_360217_20901, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "Demo_Scenario", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(finalNode_endTime, new Expression<Integer>(endTime));
            }

            public void init_17_0_2_1_edc0357_1360798765709_595827_20874Elaborations() {
                init_17_0_2_1_edc0357_1360798765709_595827_20874Dependencies();
            }
        }

        public class _17_0_2_1_edc0357_1360798765710_65657_20875 extends DurativeEvent {

            public _17_0_2_1_edc0357_1360798765710_65657_20875() {
                super();
                init_17_0_2_1_edc0357_1360798765710_65657_20875Members();
                init_17_0_2_1_edc0357_1360798765710_65657_20875Collections();
                init_17_0_2_1_edc0357_1360798765710_65657_20875Elaborations();
            }

            public _17_0_2_1_edc0357_1360798765710_65657_20875(Expression<Integer> startTime) {
                super();
                init_17_0_2_1_edc0357_1360798765710_65657_20875Members();
                init_17_0_2_1_edc0357_1360798765710_65657_20875Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_1_edc0357_1360798765710_65657_20875Elaborations();
                fixTimeDependencies();
            }

            public IntegerParameter myDeciderID_decider_17_0_2_1_edc0357_1360798765708_699932_20872 = null;

            public BooleanParameter _17_0_2_1_edc0357_1360798765710_524481_20876_exists = null;

            public BooleanParameter _17_0_2_1_edc0357_1360798765708_699932_20872_exists = null;

            public BooleanParameter objectToPass = null;

            public BooleanParameter addToDecider__17_0_2_1_edc0357_1360798765708_699932_20872 = null;

            public BooleanParameter decisionInput = null;

            public Dependency< Integer > myDeciderID_decider_17_0_2_1_edc0357_1360798765708_699932_20872Dependency = null;

            public Dependency< Boolean > _17_0_2_1_edc0357_1360798765710_524481_20876_existsDependency = null;

            public Dependency< Boolean > _17_0_2_1_edc0357_1360798765708_699932_20872_existsDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Dependency< Boolean > addToDecider__17_0_2_1_edc0357_1360798765708_699932_20872Dependency = null;

            public Dependency< Boolean > decisionInputDependency = null;

            public Effect effect40 = null;

            public Parameter effect40Var = null;

            public Effect effect41 = null;

            public Parameter effect41Var = null;

            public Effect effect42 = null;

            public Parameter effect42Var = null;

            public ElaborationRule elaborationRule43 = null;

            public ElaborationRule elaborationRule44 = null;

            public void init_17_0_2_1_edc0357_1360798765710_65657_20875Members() {
                try {
                    if (myDeciderID_decider_17_0_2_1_edc0357_1360798765708_699932_20872 == null) myDeciderID_decider_17_0_2_1_edc0357_1360798765708_699932_20872 = new IntegerParameter("myDeciderID_decider_17_0_2_1_edc0357_1360798765708_699932_20872", (Integer) 2, this);
                    if (_17_0_2_1_edc0357_1360798765710_524481_20876_exists == null) _17_0_2_1_edc0357_1360798765710_524481_20876_exists = new BooleanParameter("_17_0_2_1_edc0357_1360798765710_524481_20876_exists", (Boolean) false, this);
                    if (_17_0_2_1_edc0357_1360798765708_699932_20872_exists == null) _17_0_2_1_edc0357_1360798765708_699932_20872_exists = new BooleanParameter("_17_0_2_1_edc0357_1360798765708_699932_20872_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (addToDecider__17_0_2_1_edc0357_1360798765708_699932_20872 == null) addToDecider__17_0_2_1_edc0357_1360798765708_699932_20872 = new BooleanParameter("addToDecider__17_0_2_1_edc0357_1360798765708_699932_20872", (Boolean) false, this);
                    if (decisionInput == null) decisionInput = new BooleanParameter("decisionInput", (Boolean) null, this);
                    Object effect40VarV = sig_17_0_2_1_edc0357_1360798765715_639545_20884;
                    effect40Var = new Parameter("effect40Var", null, null, this);
                    addDependency(effect40Var, new Expression(effect40VarV));
                    effect40 = new EffectFunction(new EffectFunction(effect40Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "Demo_Scenario", "sendIf", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class, java.lang.Boolean.class), new Object[] { objectToPass, endTime, _17_0_2_1_edc0357_1360798765708_699932_20872_exists }));
                    Object effect41VarV = sig_17_0_2_1_edc0357_1360798765716_43881_20885;
                    effect41Var = new Parameter("effect41Var", null, null, this);
                    addDependency(effect41Var, new Expression(effect41VarV));
                    effect41 = new EffectFunction(new EffectFunction(effect41Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "Demo_Scenario", "sendIf", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class, java.lang.Boolean.class), new Object[] { objectToPass, endTime, _17_0_2_1_edc0357_1360798765710_524481_20876_exists }));
                    Object effect42VarV = decider_17_0_2_1_edc0357_1360798765708_699932_20872;
                    effect42Var = new Parameter("effect42Var", null, null, this);
                    addDependency(effect42Var, new Expression(effect42VarV));
                    effect42 = new EffectFunction(new EffectFunction(effect42Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "Demo_Scenario", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class, java.lang.Boolean.class), new Object[] { endTime, myDeciderID_decider_17_0_2_1_edc0357_1360798765708_699932_20872, addToDecider__17_0_2_1_edc0357_1360798765708_699932_20872 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_1_edc0357_1360798765710_65657_20875Collections() {
                parameters.add(myDeciderID_decider_17_0_2_1_edc0357_1360798765708_699932_20872);
                parameters.add(_17_0_2_1_edc0357_1360798765710_524481_20876_exists);
                parameters.add(_17_0_2_1_edc0357_1360798765708_699932_20872_exists);
                parameters.add(objectToPass);
                parameters.add(addToDecider__17_0_2_1_edc0357_1360798765708_699932_20872);
                parameters.add(decisionInput);
                Set<Effect> effectsForeffect40Var = new TreeSet<Effect>();
                effectsForeffect40Var.add(effect40);
                addEffects((Parameter<?>) effect40Var, effectsForeffect40Var);
                Set<Effect> effectsForeffect41Var = new TreeSet<Effect>();
                effectsForeffect41Var.add(effect41);
                addEffects((Parameter<?>) effect41Var, effectsForeffect41Var);
                Set<Effect> effectsForeffect42Var = new TreeSet<Effect>();
                effectsForeffect42Var.add(effect42);
                addEffects((Parameter<?>) effect42Var, effectsForeffect42Var);
            }

            public void init_17_0_2_1_edc0357_1360798765710_65657_20875Dependencies() {
                addDependency(myDeciderID_decider_17_0_2_1_edc0357_1360798765708_699932_20872, new Expression<Integer>(2));
                addDependency(_17_0_2_1_edc0357_1360798765710_524481_20876_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(decisionInput))), new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765709_595827_20874_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765709_595827_20874_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))))));
                addDependency(_17_0_2_1_edc0357_1360798765708_699932_20872_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(decisionInput), new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765709_595827_20874_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765709_595827_20874_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_1_edc0357_1360798765708_699932_20872, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "Demo_Scenario", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_1_edc0357_1360798765708_699932_20872, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "Demo_Scenario", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_1_edc0357_1360798765708_699932_20872, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "Demo_Scenario", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_1_edc0357_1360798765708_699932_20872)))))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(true));
                addDependency(addToDecider__17_0_2_1_edc0357_1360798765708_699932_20872, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(decisionInput), new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765709_595827_20874_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765709_595827_20874_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))))));
                addDependency(decisionInput, new Expression<Boolean>(new EffectFunction(sig_17_0_2_1_edc0357_1360798765717_350668_20895, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "Demo_Scenario", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_1_edc0357_1360798765710_65657_20875Elaborations() {
                init_17_0_2_1_edc0357_1360798765710_65657_20875Dependencies();
                Expression<?>[] arguments43 = new Expression<?>[1];
                arguments43[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition43 = new Expression<Boolean>(_17_0_2_1_edc0357_1360798765710_524481_20876_exists);
                elaborationRule43 = addElaborationRule(condition43, _17_0_2_1_edc0357_1360798765036_519893_20269.this, Customer._17_0_2_1_edc0357_1360798765036_519893_20269._17_0_2_1_edc0357_1360798765710_524481_20876.class, "CDR_send_no_SendSignalAction_setDRCap", arguments43);
                Expression<?>[] arguments44 = new Expression<?>[1];
                arguments44[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition44 = new Expression<Boolean>(_17_0_2_1_edc0357_1360798765708_699932_20872_exists);
                elaborationRule44 = addElaborationRule(condition44, _17_0_2_1_edc0357_1360798765036_519893_20269.this, Customer._17_0_2_1_edc0357_1360798765036_519893_20269._17_0_2_1_edc0357_1360798765708_699932_20872.class, "CDR_set_reduction_level_AddStructuralFeatureValueAction_setDRCap", arguments44);
            }
        }

        public class _17_0_2_1_edc0357_1360798765710_524481_20876 extends DurativeEvent {

            public _17_0_2_1_edc0357_1360798765710_524481_20876() {
                super();
                init_17_0_2_1_edc0357_1360798765710_524481_20876Members();
                init_17_0_2_1_edc0357_1360798765710_524481_20876Collections();
                init_17_0_2_1_edc0357_1360798765710_524481_20876Elaborations();
            }

            public _17_0_2_1_edc0357_1360798765710_524481_20876(Expression<Integer> startTime) {
                super();
                init_17_0_2_1_edc0357_1360798765710_524481_20876Members();
                init_17_0_2_1_edc0357_1360798765710_524481_20876Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_1_edc0357_1360798765710_524481_20876Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_1_edc0357_1360798765715_519650_20883_exists = null;

            public BooleanParameter objectToPass = null;

            public Parameter< Power_System.Signalno > signalObject = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > _17_0_2_1_edc0357_1360798765715_519650_20883_existsDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Dependency< Power_System.Signalno > signalObjectDependency = null;

            public Effect effect45 = null;

            public Parameter effect45Var = null;

            public Effect effect46 = null;

            public Parameter effect46Var = null;

            public Effect effect47 = null;

            public Parameter effect47Var = null;

            public ElaborationRule elaborationRule48 = null;

            public void init_17_0_2_1_edc0357_1360798765710_524481_20876Members() {
                try {
                    if (_17_0_2_1_edc0357_1360798765715_519650_20883_exists == null) _17_0_2_1_edc0357_1360798765715_519650_20883_exists = new BooleanParameter("_17_0_2_1_edc0357_1360798765715_519650_20883_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (signalObject == null) signalObject = new Parameter<Power_System.Signalno>("signalObject", null, (Power_System.Signalno) null, this);
                    Object effect45VarV = sig_17_0_2_1_edc0357_1360798765717_782661_20899;
                    effect45Var = new Parameter("effect45Var", null, null, this);
                    addDependency(effect45Var, new Expression(effect45VarV));
                    effect45 = new EffectFunction(new EffectFunction(effect45Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "Demo_Scenario", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect46VarV = new FunctionCall(x, Parameter.class, "getMember", new Object[] { "ss_17_0_2_1_edc0357_1360798765041_950141_20275_no" });
                    effect46Var = new Parameter("effect46Var", null, null, this);
                    addDependency(effect46Var, new Expression(effect46VarV));
                    effect46 = new EffectFunction(new EffectFunction(null, ClassUtils.getMethodForArgTypes("ObjectFlow<Signalno>", "Demo_Scenario", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { signalObject, endTime }, effect46Var));
                    Object effect47VarV = new FunctionCall(signalObject, Parameter.class, "getMember", new Object[] { "control" });
                    effect47Var = new Parameter("effect47Var", null, null, this);
                    addDependency(effect47Var, new Expression(effect47VarV));
                    effect47 = new EffectFunction(new EffectFunction(null, ClassUtils.getMethodForArgTypes("TimeVaryingMap<Boolean>", "Demo_Scenario", "setValue", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, true }, effect47Var));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_1_edc0357_1360798765710_524481_20876Collections() {
                parameters.add(_17_0_2_1_edc0357_1360798765715_519650_20883_exists);
                parameters.add(objectToPass);
                parameters.add(signalObject);
                Set<Effect> effectsForeffect45Var = new TreeSet<Effect>();
                effectsForeffect45Var.add(effect45);
                addEffects((Parameter<?>) effect45Var, effectsForeffect45Var);
                Set<Effect> effectsForeffect46Var = new TreeSet<Effect>();
                effectsForeffect46Var.add(effect46);
                addEffects((Parameter<?>) effect46Var, effectsForeffect46Var);
                Set<Effect> effectsForeffect47Var = new TreeSet<Effect>();
                effectsForeffect47Var.add(effect47);
                addEffects((Parameter<?>) effect47Var, effectsForeffect47Var);
            }

            public void init_17_0_2_1_edc0357_1360798765710_524481_20876Dependencies() {
                addDependency(duration, new Expression<Integer>(1));
                addDependency(_17_0_2_1_edc0357_1360798765715_519650_20883_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765709_595827_20874_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765709_595827_20874_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(objectToPass, new Expression<Boolean>(new EffectFunction(sig_17_0_2_1_edc0357_1360798765716_43881_20885, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "Demo_Scenario", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(signalObject, new Expression<Power_System.Signalno>(new ConstructorCall(x, ClassUtils.getConstructorForArgTypes(Power_System.Signalno.class), new Object[] {})));
            }

            public void init_17_0_2_1_edc0357_1360798765710_524481_20876Elaborations() {
                init_17_0_2_1_edc0357_1360798765710_524481_20876Dependencies();
                Expression<?>[] arguments48 = new Expression<?>[2];
                arguments48[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                arguments48[1] = new Expression<ObjectFlow<Boolean>>(sig_17_0_2_1_edc0357_1360798765717_782661_20899);
                Expression<Boolean> condition48 = new Expression<Boolean>(_17_0_2_1_edc0357_1360798765715_519650_20883_exists);
                elaborationRule48 = addElaborationRule(condition48, _17_0_2_1_edc0357_1360798765036_519893_20269.this, Customer._17_0_2_1_edc0357_1360798765036_519893_20269._17_0_2_1_edc0357_1360798765715_519650_20883.class, "CDR_merge_response_MergeNode_setDRCap", arguments48);
            }
        }

        public class _17_0_2_1_edc0357_1360798765711_758933_20877 extends DurativeEvent {

            public _17_0_2_1_edc0357_1360798765711_758933_20877() {
                super();
                init_17_0_2_1_edc0357_1360798765711_758933_20877Members();
                init_17_0_2_1_edc0357_1360798765711_758933_20877Collections();
                init_17_0_2_1_edc0357_1360798765711_758933_20877Elaborations();
            }

            public _17_0_2_1_edc0357_1360798765711_758933_20877(Expression<Integer> startTime) {
                super();
                init_17_0_2_1_edc0357_1360798765711_758933_20877Members();
                init_17_0_2_1_edc0357_1360798765711_758933_20877Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_1_edc0357_1360798765711_758933_20877Elaborations();
                fixTimeDependencies();
            }

            public IntegerParameter myDeciderID_decider_17_0_2_1_edc0357_1360798765708_699932_20872 = null;

            public BooleanParameter _17_0_2_1_edc0357_1360798765712_241699_20878_exists = null;

            public BooleanParameter _17_0_2_1_edc0357_1360798765708_699932_20872_exists = null;

            public DoubleParameter objectToPass = null;

            public BooleanParameter addToDecider__17_0_2_1_edc0357_1360798765712_241699_20878 = null;

            public IntegerParameter myDeciderID_decider_17_0_2_1_edc0357_1360798765712_241699_20878 = null;

            public BooleanParameter addToDecider__17_0_2_1_edc0357_1360798765708_699932_20872 = null;

            public Dependency< Integer > myDeciderID_decider_17_0_2_1_edc0357_1360798765708_699932_20872Dependency = null;

            public Dependency< Boolean > _17_0_2_1_edc0357_1360798765712_241699_20878_existsDependency = null;

            public Dependency< Boolean > _17_0_2_1_edc0357_1360798765708_699932_20872_existsDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Double > objectToPassDependency = null;

            public Dependency< Boolean > addToDecider__17_0_2_1_edc0357_1360798765712_241699_20878Dependency = null;

            public Dependency< Integer > myDeciderID_decider_17_0_2_1_edc0357_1360798765712_241699_20878Dependency = null;

            public Dependency< Boolean > addToDecider__17_0_2_1_edc0357_1360798765708_699932_20872Dependency = null;

            public Effect effect49 = null;

            public Parameter effect49Var = null;

            public Effect effect50 = null;

            public Parameter effect50Var = null;

            public Effect effect51 = null;

            public Parameter effect51Var = null;

            public Effect effect52 = null;

            public Parameter effect52Var = null;

            public ElaborationRule elaborationRule53 = null;

            public ElaborationRule elaborationRule54 = null;

            public void init_17_0_2_1_edc0357_1360798765711_758933_20877Members() {
                try {
                    if (myDeciderID_decider_17_0_2_1_edc0357_1360798765708_699932_20872 == null) myDeciderID_decider_17_0_2_1_edc0357_1360798765708_699932_20872 = new IntegerParameter("myDeciderID_decider_17_0_2_1_edc0357_1360798765708_699932_20872", (Integer) 1, this);
                    if (_17_0_2_1_edc0357_1360798765712_241699_20878_exists == null) _17_0_2_1_edc0357_1360798765712_241699_20878_exists = new BooleanParameter("_17_0_2_1_edc0357_1360798765712_241699_20878_exists", (Boolean) false, this);
                    if (_17_0_2_1_edc0357_1360798765708_699932_20872_exists == null) _17_0_2_1_edc0357_1360798765708_699932_20872_exists = new BooleanParameter("_17_0_2_1_edc0357_1360798765708_699932_20872_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new DoubleParameter("objectToPass", (Double) null, this);
                    if (addToDecider__17_0_2_1_edc0357_1360798765712_241699_20878 == null) addToDecider__17_0_2_1_edc0357_1360798765712_241699_20878 = new BooleanParameter("addToDecider__17_0_2_1_edc0357_1360798765712_241699_20878", (Boolean) false, this);
                    if (myDeciderID_decider_17_0_2_1_edc0357_1360798765712_241699_20878 == null) myDeciderID_decider_17_0_2_1_edc0357_1360798765712_241699_20878 = new IntegerParameter("myDeciderID_decider_17_0_2_1_edc0357_1360798765712_241699_20878", (Integer) 1, this);
                    if (addToDecider__17_0_2_1_edc0357_1360798765708_699932_20872 == null) addToDecider__17_0_2_1_edc0357_1360798765708_699932_20872 = new BooleanParameter("addToDecider__17_0_2_1_edc0357_1360798765708_699932_20872", (Boolean) false, this);
                    Object effect49VarV = sig_17_0_2_1_edc0357_1360798765716_835048_20886;
                    effect49Var = new Parameter("effect49Var", null, null, this);
                    addDependency(effect49Var, new Expression(effect49VarV));
                    effect49 = new EffectFunction(new EffectFunction(effect49Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Float>", "Demo_Scenario", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect50VarV = sig_17_0_2_1_edc0357_1360798765716_380599_20888;
                    effect50Var = new Parameter("effect50Var", null, null, this);
                    addDependency(effect50Var, new Expression(effect50VarV));
                    effect50 = new EffectFunction(new EffectFunction(effect50Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Float>", "Demo_Scenario", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect51VarV = decider_17_0_2_1_edc0357_1360798765708_699932_20872;
                    effect51Var = new Parameter("effect51Var", null, null, this);
                    addDependency(effect51Var, new Expression(effect51VarV));
                    effect51 = new EffectFunction(new EffectFunction(effect51Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "Demo_Scenario", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class, java.lang.Boolean.class), new Object[] { endTime, myDeciderID_decider_17_0_2_1_edc0357_1360798765708_699932_20872, addToDecider__17_0_2_1_edc0357_1360798765708_699932_20872 }));
                    Object effect52VarV = decider_17_0_2_1_edc0357_1360798765712_241699_20878;
                    effect52Var = new Parameter("effect52Var", null, null, this);
                    addDependency(effect52Var, new Expression(effect52VarV));
                    effect52 = new EffectFunction(new EffectFunction(effect52Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "Demo_Scenario", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class, java.lang.Boolean.class), new Object[] { endTime, myDeciderID_decider_17_0_2_1_edc0357_1360798765712_241699_20878, addToDecider__17_0_2_1_edc0357_1360798765712_241699_20878 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_1_edc0357_1360798765711_758933_20877Collections() {
                parameters.add(myDeciderID_decider_17_0_2_1_edc0357_1360798765708_699932_20872);
                parameters.add(_17_0_2_1_edc0357_1360798765712_241699_20878_exists);
                parameters.add(_17_0_2_1_edc0357_1360798765708_699932_20872_exists);
                parameters.add(objectToPass);
                parameters.add(addToDecider__17_0_2_1_edc0357_1360798765712_241699_20878);
                parameters.add(myDeciderID_decider_17_0_2_1_edc0357_1360798765712_241699_20878);
                parameters.add(addToDecider__17_0_2_1_edc0357_1360798765708_699932_20872);
                Set<Effect> effectsForeffect49Var = new TreeSet<Effect>();
                effectsForeffect49Var.add(effect49);
                addEffects((Parameter<?>) effect49Var, effectsForeffect49Var);
                Set<Effect> effectsForeffect50Var = new TreeSet<Effect>();
                effectsForeffect50Var.add(effect50);
                addEffects((Parameter<?>) effect50Var, effectsForeffect50Var);
                Set<Effect> effectsForeffect51Var = new TreeSet<Effect>();
                effectsForeffect51Var.add(effect51);
                addEffects((Parameter<?>) effect51Var, effectsForeffect51Var);
                Set<Effect> effectsForeffect52Var = new TreeSet<Effect>();
                effectsForeffect52Var.add(effect52);
                addEffects((Parameter<?>) effect52Var, effectsForeffect52Var);
            }

            public void init_17_0_2_1_edc0357_1360798765711_758933_20877Dependencies() {
                addDependency(myDeciderID_decider_17_0_2_1_edc0357_1360798765708_699932_20872, new Expression<Integer>(1));
                addDependency(_17_0_2_1_edc0357_1360798765712_241699_20878_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765709_595827_20874_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765709_595827_20874_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_1_edc0357_1360798765712_241699_20878, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "Demo_Scenario", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_1_edc0357_1360798765712_241699_20878, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "Demo_Scenario", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_1_edc0357_1360798765712_241699_20878, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "Demo_Scenario", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_1_edc0357_1360798765712_241699_20878)))))))));
                addDependency(_17_0_2_1_edc0357_1360798765708_699932_20872_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765709_595827_20874_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765709_595827_20874_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_1_edc0357_1360798765708_699932_20872, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "Demo_Scenario", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_1_edc0357_1360798765708_699932_20872, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "Demo_Scenario", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_1_edc0357_1360798765708_699932_20872, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "Demo_Scenario", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_1_edc0357_1360798765708_699932_20872)))))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Double>(new EffectFunction(sig_17_0_2_1_edc0357_1360798765717_801642_20894, ClassUtils.getMethodForArgTypes("ObjectFlow<Float>", "Demo_Scenario", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(addToDecider__17_0_2_1_edc0357_1360798765712_241699_20878, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765709_595827_20874_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765709_595827_20874_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(myDeciderID_decider_17_0_2_1_edc0357_1360798765712_241699_20878, new Expression<Integer>(1));
                addDependency(addToDecider__17_0_2_1_edc0357_1360798765708_699932_20872, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765709_595827_20874_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765709_595827_20874_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
            }

            public void init_17_0_2_1_edc0357_1360798765711_758933_20877Elaborations() {
                init_17_0_2_1_edc0357_1360798765711_758933_20877Dependencies();
                Expression<?>[] arguments53 = new Expression<?>[1];
                arguments53[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition53 = new Expression<Boolean>(_17_0_2_1_edc0357_1360798765708_699932_20872_exists);
                elaborationRule53 = addElaborationRule(condition53, _17_0_2_1_edc0357_1360798765036_519893_20269.this, Customer._17_0_2_1_edc0357_1360798765036_519893_20269._17_0_2_1_edc0357_1360798765708_699932_20872.class, "CDR_set_reduction_level_AddStructuralFeatureValueAction_setDRCap", arguments53);
                Expression<?>[] arguments54 = new Expression<?>[1];
                arguments54[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition54 = new Expression<Boolean>(_17_0_2_1_edc0357_1360798765712_241699_20878_exists);
                elaborationRule54 = addElaborationRule(condition54, _17_0_2_1_edc0357_1360798765036_519893_20269.this, Customer._17_0_2_1_edc0357_1360798765036_519893_20269._17_0_2_1_edc0357_1360798765712_241699_20878.class, "CDR_send_yes_SendSignalAction_setDRCap", arguments54);
            }
        }

        public class _17_0_2_1_edc0357_1360798765712_241699_20878 extends DurativeEvent {

            public _17_0_2_1_edc0357_1360798765712_241699_20878() {
                super();
                init_17_0_2_1_edc0357_1360798765712_241699_20878Members();
                init_17_0_2_1_edc0357_1360798765712_241699_20878Collections();
                init_17_0_2_1_edc0357_1360798765712_241699_20878Elaborations();
            }

            public _17_0_2_1_edc0357_1360798765712_241699_20878(Expression<Integer> startTime) {
                super();
                init_17_0_2_1_edc0357_1360798765712_241699_20878Members();
                init_17_0_2_1_edc0357_1360798765712_241699_20878Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_1_edc0357_1360798765712_241699_20878Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_1_edc0357_1360798765715_519650_20883_exists = null;

            public DoubleParameter _17_0_2_1_edc0357_1360798766418_259639_22047 = null;

            public BooleanParameter objectToPass = null;

            public Parameter< Power_System.Signalyes > signalObject = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > _17_0_2_1_edc0357_1360798765715_519650_20883_existsDependency = null;

            public Dependency< Double > _17_0_2_1_edc0357_1360798766418_259639_22047Dependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Dependency< Power_System.Signalyes > signalObjectDependency = null;

            public Effect effect55 = null;

            public Parameter effect55Var = null;

            public Effect effect56 = null;

            public Parameter effect56Var = null;

            public Effect effect57 = null;

            public Parameter effect57Var = null;

            public ElaborationRule elaborationRule58 = null;

            public void init_17_0_2_1_edc0357_1360798765712_241699_20878Members() {
                try {
                    if (_17_0_2_1_edc0357_1360798765715_519650_20883_exists == null) _17_0_2_1_edc0357_1360798765715_519650_20883_exists = new BooleanParameter("_17_0_2_1_edc0357_1360798765715_519650_20883_exists", (Boolean) false, this);
                    if (_17_0_2_1_edc0357_1360798766418_259639_22047 == null) _17_0_2_1_edc0357_1360798766418_259639_22047 = new DoubleParameter("_17_0_2_1_edc0357_1360798766418_259639_22047", (Double) null, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (signalObject == null) signalObject = new Parameter<Power_System.Signalyes>("signalObject", null, (Power_System.Signalyes) null, this);
                    Object effect55VarV = sig_17_0_2_1_edc0357_1360798765718_205784_20900;
                    effect55Var = new Parameter("effect55Var", null, null, this);
                    addDependency(effect55Var, new Expression(effect55VarV));
                    effect55 = new EffectFunction(new EffectFunction(effect55Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "Demo_Scenario", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect56VarV = new FunctionCall(x, Parameter.class, "getMember", new Object[] { "ss_17_0_2_1_edc0357_1360798765041_950141_20275_yes" });
                    effect56Var = new Parameter("effect56Var", null, null, this);
                    addDependency(effect56Var, new Expression(effect56VarV));
                    effect56 = new EffectFunction(new EffectFunction(null, ClassUtils.getMethodForArgTypes("ObjectFlow<Signalyes>", "Demo_Scenario", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { signalObject, endTime }, effect56Var));
                    Object effect57VarV = new FunctionCall(signalObject, Parameter.class, "getMember", new Object[] { "newLoad__17_0_2_1_edc0357_1360798765097_803626_20354" });
                    effect57Var = new Parameter("effect57Var", null, null, this);
                    addDependency(effect57Var, new Expression(effect57VarV));
                    effect57 = new EffectFunction(new EffectFunction(null, ClassUtils.getMethodForArgTypes("TimeVaryingMap<Float>", "Demo_Scenario", "setValue", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, _17_0_2_1_edc0357_1360798766418_259639_22047 }, effect57Var));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_1_edc0357_1360798765712_241699_20878Collections() {
                parameters.add(_17_0_2_1_edc0357_1360798765715_519650_20883_exists);
                parameters.add(_17_0_2_1_edc0357_1360798766418_259639_22047);
                parameters.add(objectToPass);
                parameters.add(signalObject);
                Set<Effect> effectsForeffect55Var = new TreeSet<Effect>();
                effectsForeffect55Var.add(effect55);
                addEffects((Parameter<?>) effect55Var, effectsForeffect55Var);
                Set<Effect> effectsForeffect56Var = new TreeSet<Effect>();
                effectsForeffect56Var.add(effect56);
                addEffects((Parameter<?>) effect56Var, effectsForeffect56Var);
                Set<Effect> effectsForeffect57Var = new TreeSet<Effect>();
                effectsForeffect57Var.add(effect57);
                addEffects((Parameter<?>) effect57Var, effectsForeffect57Var);
            }

            public void init_17_0_2_1_edc0357_1360798765712_241699_20878Dependencies() {
                addDependency(duration, new Expression<Integer>(1));
                addDependency(_17_0_2_1_edc0357_1360798765715_519650_20883_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765709_595827_20874_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765709_595827_20874_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(_17_0_2_1_edc0357_1360798766418_259639_22047, new Expression<Double>(new EffectFunction(sig_17_0_2_1_edc0357_1360798765716_380599_20888, ClassUtils.getMethodForArgTypes("ObjectFlow<Float>", "Demo_Scenario", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(objectToPass, new Expression<Boolean>(new EffectFunction(sig_17_0_2_1_edc0357_1360798765716_333668_20887, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "Demo_Scenario", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(signalObject, new Expression<Power_System.Signalyes>(new ConstructorCall(x, ClassUtils.getConstructorForArgTypes(Power_System.Signalyes.class), new Object[] {})));
            }

            public void init_17_0_2_1_edc0357_1360798765712_241699_20878Elaborations() {
                init_17_0_2_1_edc0357_1360798765712_241699_20878Dependencies();
                Expression<?>[] arguments58 = new Expression<?>[2];
                arguments58[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                arguments58[1] = new Expression<ObjectFlow<Boolean>>(sig_17_0_2_1_edc0357_1360798765718_205784_20900);
                Expression<Boolean> condition58 = new Expression<Boolean>(_17_0_2_1_edc0357_1360798765715_519650_20883_exists);
                elaborationRule58 = addElaborationRule(condition58, _17_0_2_1_edc0357_1360798765036_519893_20269.this, Customer._17_0_2_1_edc0357_1360798765036_519893_20269._17_0_2_1_edc0357_1360798765715_519650_20883.class, "CDR_merge_response_MergeNode_setDRCap", arguments58);
            }
        }

        public class _17_0_2_1_edc0357_1360798765713_870203_20880 extends DurativeEvent {

            public _17_0_2_1_edc0357_1360798765713_870203_20880() {
                super();
                init_17_0_2_1_edc0357_1360798765713_870203_20880Members();
                init_17_0_2_1_edc0357_1360798765713_870203_20880Collections();
                init_17_0_2_1_edc0357_1360798765713_870203_20880Elaborations();
            }

            public _17_0_2_1_edc0357_1360798765713_870203_20880(Expression<Integer> startTime, Expression<Double> _17_0_2_1_edc0357_1360798765691_696195_20849) {
                super();
                init_17_0_2_1_edc0357_1360798765713_870203_20880Members();
                init_17_0_2_1_edc0357_1360798765713_870203_20880Collections();
                addDependency(this.startTime, startTime);
                addDependency(this._17_0_2_1_edc0357_1360798765691_696195_20849, _17_0_2_1_edc0357_1360798765691_696195_20849);
                init_17_0_2_1_edc0357_1360798765713_870203_20880Elaborations();
                fixTimeDependencies();
            }

            public DoubleParameter objectToPass = null;

            public BooleanParameter _17_0_2_1_edc0357_1360798765711_758933_20877_exists = null;

            public DoubleParameter _17_0_2_1_edc0357_1360798765691_696195_20849 = null;

            public Dependency< Double > objectToPassDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > _17_0_2_1_edc0357_1360798765711_758933_20877_existsDependency = null;

            public Effect effect59 = null;

            public Parameter effect59Var = null;

            public ElaborationRule elaborationRule60 = null;

            public void init_17_0_2_1_edc0357_1360798765713_870203_20880Members() {
                try {
                    if (objectToPass == null) objectToPass = new DoubleParameter("objectToPass", (Double) null, this);
                    if (_17_0_2_1_edc0357_1360798765711_758933_20877_exists == null) _17_0_2_1_edc0357_1360798765711_758933_20877_exists = new BooleanParameter("_17_0_2_1_edc0357_1360798765711_758933_20877_exists", (Boolean) false, this);
                    if (_17_0_2_1_edc0357_1360798765691_696195_20849 == null) _17_0_2_1_edc0357_1360798765691_696195_20849 = new DoubleParameter("_17_0_2_1_edc0357_1360798765691_696195_20849", (Double) null, this);
                    Object effect59VarV = sig_17_0_2_1_edc0357_1360798765717_801642_20894;
                    effect59Var = new Parameter("effect59Var", null, null, this);
                    addDependency(effect59Var, new Expression(effect59VarV));
                    effect59 = new EffectFunction(new EffectFunction(effect59Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Float>", "Demo_Scenario", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_1_edc0357_1360798765713_870203_20880Collections() {
                parameters.add(objectToPass);
                parameters.add(_17_0_2_1_edc0357_1360798765711_758933_20877_exists);
                parameters.add(_17_0_2_1_edc0357_1360798765691_696195_20849);
                Set<Effect> effectsForeffect59Var = new TreeSet<Effect>();
                effectsForeffect59Var.add(effect59);
                addEffects((Parameter<?>) effect59Var, effectsForeffect59Var);
            }

            public void init_17_0_2_1_edc0357_1360798765713_870203_20880Dependencies() {
                addDependency(objectToPass, new Expression<Double>(_17_0_2_1_edc0357_1360798765691_696195_20849));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(_17_0_2_1_edc0357_1360798765711_758933_20877_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765709_595827_20874_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765709_595827_20874_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
            }

            public void init_17_0_2_1_edc0357_1360798765713_870203_20880Elaborations() {
                init_17_0_2_1_edc0357_1360798765713_870203_20880Dependencies();
                Expression<?>[] arguments60 = new Expression<?>[1];
                arguments60[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition60 = new Expression<Boolean>(_17_0_2_1_edc0357_1360798765711_758933_20877_exists);
                elaborationRule60 = addElaborationRule(condition60, _17_0_2_1_edc0357_1360798765036_519893_20269.this, Customer._17_0_2_1_edc0357_1360798765036_519893_20269._17_0_2_1_edc0357_1360798765711_758933_20877.class, "CDR_fork2_ForkNode_setDRCap", arguments60);
            }
        }

        public class _17_0_2_1_edc0357_1360798765715_519650_20883 extends DurativeEvent {

            public _17_0_2_1_edc0357_1360798765715_519650_20883() {
                super();
                init_17_0_2_1_edc0357_1360798765715_519650_20883Members();
                init_17_0_2_1_edc0357_1360798765715_519650_20883Collections();
                init_17_0_2_1_edc0357_1360798765715_519650_20883Elaborations();
            }

            public _17_0_2_1_edc0357_1360798765715_519650_20883(Expression<Integer> startTime, Expression<ObjectFlow<Boolean>> receiveThis) {
                super();
                init_17_0_2_1_edc0357_1360798765715_519650_20883Members();
                init_17_0_2_1_edc0357_1360798765715_519650_20883Collections();
                addDependency(this.startTime, startTime);
                addDependency(this.receiveThis, receiveThis);
                init_17_0_2_1_edc0357_1360798765715_519650_20883Elaborations();
                fixTimeDependencies();
            }

            public Parameter< ObjectFlow<Boolean> > receiveThis = null;

            public BooleanParameter objectToPass = null;

            public Dependency< Integer > finalNode_startTimeDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Effect effect61 = null;

            public Parameter effect61Var = null;

            public void init_17_0_2_1_edc0357_1360798765715_519650_20883Members() {
                try {
                    if (receiveThis == null) receiveThis = new Parameter<ObjectFlow<Boolean>>("receiveThis", null, (ObjectFlow<Boolean>) null, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect61VarV = sig_17_0_2_1_edc0357_1360798765718_360217_20901;
                    effect61Var = new Parameter("effect61Var", null, null, this);
                    addDependency(effect61Var, new Expression(effect61VarV));
                    effect61 = new EffectFunction(new EffectFunction(effect61Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "Demo_Scenario", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_1_edc0357_1360798765715_519650_20883Collections() {
                parameters.add(receiveThis);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect61Var = new TreeSet<Effect>();
                effectsForeffect61Var.add(effect61);
                addEffects((Parameter<?>) effect61Var, effectsForeffect61Var);
            }

            public void init_17_0_2_1_edc0357_1360798765715_519650_20883Dependencies() {
                addDependency(finalNode_startTime, new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new EffectFunction(receiveThis, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "Demo_Scenario", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_1_edc0357_1360798765715_519650_20883Elaborations() {
                init_17_0_2_1_edc0357_1360798765715_519650_20883Dependencies();
            }
        }

        public class _17_0_2_1_edc0357_1360951536105_515998_20307 extends DurativeEvent {

            public _17_0_2_1_edc0357_1360951536105_515998_20307() {
                super();
                init_17_0_2_1_edc0357_1360951536105_515998_20307Members();
                init_17_0_2_1_edc0357_1360951536105_515998_20307Collections();
                init_17_0_2_1_edc0357_1360951536105_515998_20307Elaborations();
            }

            public _17_0_2_1_edc0357_1360951536105_515998_20307(Expression<Integer> startTime) {
                super();
                init_17_0_2_1_edc0357_1360951536105_515998_20307Members();
                init_17_0_2_1_edc0357_1360951536105_515998_20307Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_1_edc0357_1360951536105_515998_20307Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_1_edc0357_1360798765710_65657_20875_exists = null;

            public BooleanParameter _17_0_2_1_edc0357_1360798766418_709225_22049 = null;

            public BooleanParameter objectToPass = null;

            public Dependency< Boolean > _17_0_2_1_edc0357_1360798765710_65657_20875_existsDependency = null;

            public Dependency< Boolean > _17_0_2_1_edc0357_1360798766418_709225_22049Dependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Effect effect62 = null;

            public Parameter effect62Var = null;

            public ElaborationRule elaborationRule63 = null;

            public void init_17_0_2_1_edc0357_1360951536105_515998_20307Members() {
                try {
                    if (_17_0_2_1_edc0357_1360798765710_65657_20875_exists == null) _17_0_2_1_edc0357_1360798765710_65657_20875_exists = new BooleanParameter("_17_0_2_1_edc0357_1360798765710_65657_20875_exists", (Boolean) false, this);
                    if (_17_0_2_1_edc0357_1360798766418_709225_22049 == null) _17_0_2_1_edc0357_1360798766418_709225_22049 = new BooleanParameter("_17_0_2_1_edc0357_1360798766418_709225_22049", (Boolean) null, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect62VarV = sig_17_0_2_1_edc0357_1360798765717_350668_20895;
                    effect62Var = new Parameter("effect62Var", null, null, this);
                    addDependency(effect62Var, new Expression(effect62VarV));
                    effect62 = new EffectFunction(new EffectFunction(effect62Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "Demo_Scenario", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { _17_0_2_1_edc0357_1360798766418_709225_22049, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_1_edc0357_1360951536105_515998_20307Collections() {
                parameters.add(_17_0_2_1_edc0357_1360798765710_65657_20875_exists);
                parameters.add(_17_0_2_1_edc0357_1360798766418_709225_22049);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect62Var = new TreeSet<Effect>();
                effectsForeffect62Var.add(effect62);
                addEffects((Parameter<?>) effect62Var, effectsForeffect62Var);
            }

            public void init_17_0_2_1_edc0357_1360951536105_515998_20307Dependencies() {
                addDependency(_17_0_2_1_edc0357_1360798765710_65657_20875_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765709_595827_20874_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765709_595827_20874_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(_17_0_2_1_edc0357_1360798766418_709225_22049, new Expression<Boolean>(new EffectFunction(participation_policy__17_0_2_1_edc0357_1360951506981_801034_20303, ClassUtils.getMethodForArgTypes("TimeVaryingMap<Boolean>", "Demo_Scenario", "getValue", java.lang.Integer.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new EffectFunction(sig_17_0_2_1_edc0357_1360798765717_84499_20896, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "Demo_Scenario", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_1_edc0357_1360951536105_515998_20307Elaborations() {
                init_17_0_2_1_edc0357_1360951536105_515998_20307Dependencies();
                Expression<?>[] arguments63 = new Expression<?>[1];
                arguments63[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition63 = new Expression<Boolean>(_17_0_2_1_edc0357_1360798765710_65657_20875_exists);
                elaborationRule63 = addElaborationRule(condition63, _17_0_2_1_edc0357_1360798765036_519893_20269.this, Customer._17_0_2_1_edc0357_1360798765036_519893_20269._17_0_2_1_edc0357_1360798765710_65657_20875.class, "CDR_decide_particip_DecisionNode_setDRCap", arguments63);
            }
        }

        public _17_0_2_1_edc0357_1360798765036_519893_20269(Expression<Integer> startTime, Expression<_17_0_2_1_edc0357_1360798765036_519893_20269_interface> caller, Expression<Double> _17_0_2_1_edc0357_1360798765691_696195_20849) {
            super();
            init_17_0_2_1_edc0357_1360798765036_519893_20269Members();
            init_17_0_2_1_edc0357_1360798765036_519893_20269Collections();
            addDependency(this.startTime, startTime);
            addDependency(this.caller, caller);
            addDependency(this._17_0_2_1_edc0357_1360798765691_696195_20849, _17_0_2_1_edc0357_1360798765691_696195_20849);
            init_17_0_2_1_edc0357_1360798765036_519893_20269Elaborations();
            fixTimeDependencies();
        }

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_1_edc0357_1360798765717_350668_20895 = null;

        public Parameter< ObjectFlow<Float> > sig_17_0_2_1_edc0357_1360798765716_380599_20888 = null;

        public BooleanParameter _17_0_2_1_edc0357_1360798765709_595827_20874_exists = null;

        public Parameter< Customer._17_0_2_1_edc0357_1360798765036_519893_20269_interface > caller = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_1_edc0357_1360798765715_639545_20884 = null;

        public Parameter< TimeVaryingList<Integer> > decider_17_0_2_1_edc0357_1360798765712_241699_20878 = null;

        public Parameter< ObjectFlow<Float> > sig_17_0_2_1_edc0357_1360798765717_801642_20894 = null;

        public Parameter< ObjectFlow<Float> > sig_17_0_2_1_edc0357_1360798765716_835048_20886 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_1_edc0357_1360798765716_43881_20885 = null;

        public IntegerParameter finalNode_startTime = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_1_edc0357_1360798765717_782661_20899 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_1_edc0357_1360798765717_84499_20896 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_1_edc0357_1360798765716_333668_20887 = null;

        public IntegerParameter finalNode_endTime = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_1_edc0357_1360798765718_205784_20900 = null;

        public Parameter< TimeVaryingList<Integer> > decider_17_0_2_1_edc0357_1360798765708_699932_20872 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_1_edc0357_1360798765718_360217_20901 = null;

        public DoubleParameter _17_0_2_1_edc0357_1360798765691_696195_20849 = null;

        public Dependency< Integer > caller_endTimeDependency = null;

        public Dependency< Boolean > _17_0_2_1_edc0357_1360798765709_595827_20874_existsDependency = null;

        public Dependency< Integer > endTimeDependency = null;

        public ElaborationRule elaborationRule31 = null;

        public ElaborationRule elaborationRule32 = null;

        public ElaborationRule elaborationRule33 = null;

        public void init_17_0_2_1_edc0357_1360798765036_519893_20269Members() {
            try {
                if (sig_17_0_2_1_edc0357_1360798765717_350668_20895 == null) sig_17_0_2_1_edc0357_1360798765717_350668_20895 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_1_edc0357_1360798765717_350668_20895", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360798765717_350668_20895" })).evaluate(true), this);
                if (sig_17_0_2_1_edc0357_1360798765716_380599_20888 == null) sig_17_0_2_1_edc0357_1360798765716_380599_20888 = new Parameter<ObjectFlow<Float>>("sig_17_0_2_1_edc0357_1360798765716_380599_20888", null, (ObjectFlow<Float>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360798765716_380599_20888" })).evaluate(true), this);
                if (_17_0_2_1_edc0357_1360798765709_595827_20874_exists == null) _17_0_2_1_edc0357_1360798765709_595827_20874_exists = new BooleanParameter("_17_0_2_1_edc0357_1360798765709_595827_20874_exists", (Boolean) false, this);
                if (caller == null) caller = new Parameter<Customer._17_0_2_1_edc0357_1360798765036_519893_20269_interface>("caller", null, (Customer._17_0_2_1_edc0357_1360798765036_519893_20269_interface) null, this);
                if (sig_17_0_2_1_edc0357_1360798765715_639545_20884 == null) sig_17_0_2_1_edc0357_1360798765715_639545_20884 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_1_edc0357_1360798765715_639545_20884", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360798765715_639545_20884" })).evaluate(true), this);
                if (decider_17_0_2_1_edc0357_1360798765712_241699_20878 == null) decider_17_0_2_1_edc0357_1360798765712_241699_20878 = new Parameter<TimeVaryingList<Integer>>("decider_17_0_2_1_edc0357_1360798765712_241699_20878", null, (TimeVaryingList<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingList.class, java.lang.String.class, int.class), new Object[] { "decider_17_0_2_1_edc0357_1360798765712_241699_20878", 2 })).evaluate(true), this);
                if (sig_17_0_2_1_edc0357_1360798765717_801642_20894 == null) sig_17_0_2_1_edc0357_1360798765717_801642_20894 = new Parameter<ObjectFlow<Float>>("sig_17_0_2_1_edc0357_1360798765717_801642_20894", null, (ObjectFlow<Float>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360798765717_801642_20894" })).evaluate(true), this);
                if (sig_17_0_2_1_edc0357_1360798765716_835048_20886 == null) sig_17_0_2_1_edc0357_1360798765716_835048_20886 = new Parameter<ObjectFlow<Float>>("sig_17_0_2_1_edc0357_1360798765716_835048_20886", null, (ObjectFlow<Float>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360798765716_835048_20886" })).evaluate(true), this);
                if (sig_17_0_2_1_edc0357_1360798765716_43881_20885 == null) sig_17_0_2_1_edc0357_1360798765716_43881_20885 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_1_edc0357_1360798765716_43881_20885", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360798765716_43881_20885" })).evaluate(true), this);
                if (finalNode_startTime == null) finalNode_startTime = new IntegerParameter("finalNode_startTime", (Integer) null, this);
                if (sig_17_0_2_1_edc0357_1360798765717_782661_20899 == null) sig_17_0_2_1_edc0357_1360798765717_782661_20899 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_1_edc0357_1360798765717_782661_20899", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360798765717_782661_20899" })).evaluate(true), this);
                if (sig_17_0_2_1_edc0357_1360798765717_84499_20896 == null) sig_17_0_2_1_edc0357_1360798765717_84499_20896 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_1_edc0357_1360798765717_84499_20896", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360798765717_84499_20896" })).evaluate(true), this);
                if (sig_17_0_2_1_edc0357_1360798765716_333668_20887 == null) sig_17_0_2_1_edc0357_1360798765716_333668_20887 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_1_edc0357_1360798765716_333668_20887", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360798765716_333668_20887" })).evaluate(true), this);
                if (finalNode_endTime == null) finalNode_endTime = new IntegerParameter("finalNode_endTime", (Integer) null, this);
                if (sig_17_0_2_1_edc0357_1360798765718_205784_20900 == null) sig_17_0_2_1_edc0357_1360798765718_205784_20900 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_1_edc0357_1360798765718_205784_20900", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360798765718_205784_20900" })).evaluate(true), this);
                if (decider_17_0_2_1_edc0357_1360798765708_699932_20872 == null) decider_17_0_2_1_edc0357_1360798765708_699932_20872 = new Parameter<TimeVaryingList<Integer>>("decider_17_0_2_1_edc0357_1360798765708_699932_20872", null, (TimeVaryingList<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingList.class, java.lang.String.class, int.class), new Object[] { "decider_17_0_2_1_edc0357_1360798765708_699932_20872", 2 })).evaluate(true), this);
                if (sig_17_0_2_1_edc0357_1360798765718_360217_20901 == null) sig_17_0_2_1_edc0357_1360798765718_360217_20901 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_1_edc0357_1360798765718_360217_20901", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360798765718_360217_20901" })).evaluate(true), this);
                if (_17_0_2_1_edc0357_1360798765691_696195_20849 == null) _17_0_2_1_edc0357_1360798765691_696195_20849 = new DoubleParameter("_17_0_2_1_edc0357_1360798765691_696195_20849", (Double) null, this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void init_17_0_2_1_edc0357_1360798765036_519893_20269Collections() {
            parameters.add(sig_17_0_2_1_edc0357_1360798765717_350668_20895);
            parameters.add(sig_17_0_2_1_edc0357_1360798765716_380599_20888);
            parameters.add(_17_0_2_1_edc0357_1360798765709_595827_20874_exists);
            parameters.add(caller);
            parameters.add(sig_17_0_2_1_edc0357_1360798765715_639545_20884);
            parameters.add(decider_17_0_2_1_edc0357_1360798765712_241699_20878);
            parameters.add(sig_17_0_2_1_edc0357_1360798765717_801642_20894);
            parameters.add(sig_17_0_2_1_edc0357_1360798765716_835048_20886);
            parameters.add(sig_17_0_2_1_edc0357_1360798765716_43881_20885);
            parameters.add(finalNode_startTime);
            parameters.add(sig_17_0_2_1_edc0357_1360798765717_782661_20899);
            parameters.add(sig_17_0_2_1_edc0357_1360798765717_84499_20896);
            parameters.add(sig_17_0_2_1_edc0357_1360798765716_333668_20887);
            parameters.add(finalNode_endTime);
            parameters.add(sig_17_0_2_1_edc0357_1360798765718_205784_20900);
            parameters.add(decider_17_0_2_1_edc0357_1360798765708_699932_20872);
            parameters.add(sig_17_0_2_1_edc0357_1360798765718_360217_20901);
            parameters.add(_17_0_2_1_edc0357_1360798765691_696195_20849);
        }

        public void init_17_0_2_1_edc0357_1360798765036_519893_20269Dependencies() {
            caller.getValue(true).addDependency(caller.getValue(true).endTime, new Expression<Integer>(finalNode_endTime));
            addDependency(_17_0_2_1_edc0357_1360798765709_595827_20874_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.NotEquals(new Expression<Integer>(finalNode_startTime), null)), new Expression(new EffectFunction(sig_17_0_2_1_edc0357_1360798765718_360217_20901, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "Demo_Scenario", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(new Functions.Minus(new Expression<Integer>(finalNode_startTime), new Expression<Integer>(1))) })))));
            addDependency(endTime, new Expression<Integer>(finalNode_endTime));
        }

        public void init_17_0_2_1_edc0357_1360798765036_519893_20269Elaborations() {
            init_17_0_2_1_edc0357_1360798765036_519893_20269Dependencies();
            Expression<?>[] arguments31 = new Expression<?>[2];
            arguments31[0] = new Expression<Integer>(startTime);
            arguments31[1] = new Expression<Double>(_17_0_2_1_edc0357_1360798765691_696195_20849);
            Expression<Boolean> condition31 = new Expression<Boolean>(true);
            elaborationRule31 = addElaborationRule(condition31, _17_0_2_1_edc0357_1360798765036_519893_20269.this, Customer._17_0_2_1_edc0357_1360798765036_519893_20269._17_0_2_1_edc0357_1360798765713_870203_20880.class, "CDR_capval_ActivityParameterNode_setDRCap", arguments31);
            Expression<?>[] arguments32 = new Expression<?>[1];
            arguments32[0] = new Expression<Integer>(startTime);
            Expression<Boolean> condition32 = new Expression<Boolean>(true);
            elaborationRule32 = addElaborationRule(condition32, _17_0_2_1_edc0357_1360798765036_519893_20269.this, Customer._17_0_2_1_edc0357_1360798765036_519893_20269._17_0_2_1_edc0357_1360798765708_946798_20873.class, "CDR_start_InitialNode_setDRCap", arguments32);
            Expression<?>[] arguments33 = new Expression<?>[1];
            arguments33[0] = new Expression<Integer>(finalNode_startTime);
            Expression<Boolean> condition33 = new Expression<Boolean>(_17_0_2_1_edc0357_1360798765709_595827_20874_exists);
            elaborationRule33 = addElaborationRule(condition33, _17_0_2_1_edc0357_1360798765036_519893_20269.this, Customer._17_0_2_1_edc0357_1360798765036_519893_20269._17_0_2_1_edc0357_1360798765709_595827_20874.class, "CDR_final_ActivityFinalNode_setDRCap", arguments33);
        }
    }

    public class _17_0_2_1_edc0357_1360798765036_519893_20269_interface extends DurativeEvent {

        public _17_0_2_1_edc0357_1360798765036_519893_20269_interface() {
            super();
            init_17_0_2_1_edc0357_1360798765036_519893_20269_interfaceMembers();
            init_17_0_2_1_edc0357_1360798765036_519893_20269_interfaceCollections();
            init_17_0_2_1_edc0357_1360798765036_519893_20269_interfaceElaborations();
        }

        public DoubleParameter _17_0_2_1_edc0357_1360798765691_696195_20849 = null;

        public void init_17_0_2_1_edc0357_1360798765036_519893_20269_interfaceMembers() {
            try {
                if (_17_0_2_1_edc0357_1360798765691_696195_20849 == null) _17_0_2_1_edc0357_1360798765691_696195_20849 = new DoubleParameter("_17_0_2_1_edc0357_1360798765691_696195_20849", (Double) null, this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void init_17_0_2_1_edc0357_1360798765036_519893_20269_interfaceCollections() {
            parameters.add(_17_0_2_1_edc0357_1360798765691_696195_20849);
        }

        public void init_17_0_2_1_edc0357_1360798765036_519893_20269_interfaceDependencies() {
        }

        public void init_17_0_2_1_edc0357_1360798765036_519893_20269_interfaceElaborations() {
            init_17_0_2_1_edc0357_1360798765036_519893_20269_interfaceDependencies();
        }
    }

    public class _17_0_2_1_edc0357_1360798765037_222001_20270 extends DurativeEvent {

        public _17_0_2_1_edc0357_1360798765037_222001_20270() {
            super();
            init_17_0_2_1_edc0357_1360798765037_222001_20270Members();
            init_17_0_2_1_edc0357_1360798765037_222001_20270Collections();
            init_17_0_2_1_edc0357_1360798765037_222001_20270Elaborations();
        }

        public class _17_0_2_1_edc0357_1360798765733_536254_20923 extends DurativeEvent {

            public _17_0_2_1_edc0357_1360798765733_536254_20923() {
                super();
                init_17_0_2_1_edc0357_1360798765733_536254_20923Members();
                init_17_0_2_1_edc0357_1360798765733_536254_20923Collections();
                init_17_0_2_1_edc0357_1360798765733_536254_20923Elaborations();
            }

            public _17_0_2_1_edc0357_1360798765733_536254_20923(Expression<Integer> startTime) {
                super();
                init_17_0_2_1_edc0357_1360798765733_536254_20923Members();
                init_17_0_2_1_edc0357_1360798765733_536254_20923Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_1_edc0357_1360798765733_536254_20923Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_1_edc0357_1360798765734_501480_20925_exists = null;

            public BooleanParameter objectToPass = null;

            public Dependency< Boolean > _17_0_2_1_edc0357_1360798765734_501480_20925_existsDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Effect effect66 = null;

            public Parameter effect66Var = null;

            public ElaborationRule elaborationRule67 = null;

            public void init_17_0_2_1_edc0357_1360798765733_536254_20923Members() {
                try {
                    if (_17_0_2_1_edc0357_1360798765734_501480_20925_exists == null) _17_0_2_1_edc0357_1360798765734_501480_20925_exists = new BooleanParameter("_17_0_2_1_edc0357_1360798765734_501480_20925_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect66VarV = sig_17_0_2_1_edc0357_1360798765744_973847_20946;
                    effect66Var = new Parameter("effect66Var", null, null, this);
                    addDependency(effect66Var, new Expression(effect66VarV));
                    effect66 = new EffectFunction(new EffectFunction(effect66Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "Demo_Scenario", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_1_edc0357_1360798765733_536254_20923Collections() {
                parameters.add(_17_0_2_1_edc0357_1360798765734_501480_20925_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect66Var = new TreeSet<Effect>();
                effectsForeffect66Var.add(effect66);
                addEffects((Parameter<?>) effect66Var, effectsForeffect66Var);
            }

            public void init_17_0_2_1_edc0357_1360798765733_536254_20923Dependencies() {
                addDependency(_17_0_2_1_edc0357_1360798765734_501480_20925_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765737_27623_20930_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765737_27623_20930_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(true));
            }

            public void init_17_0_2_1_edc0357_1360798765733_536254_20923Elaborations() {
                init_17_0_2_1_edc0357_1360798765733_536254_20923Dependencies();
                Expression<?>[] arguments67 = new Expression<?>[1];
                arguments67[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition67 = new Expression<Boolean>(_17_0_2_1_edc0357_1360798765734_501480_20925_exists);
                elaborationRule67 = addElaborationRule(condition67, _17_0_2_1_edc0357_1360798765037_222001_20270.this, Customer._17_0_2_1_edc0357_1360798765037_222001_20270._17_0_2_1_edc0357_1360798765734_501480_20925.class, "C_fork_ForkNode_CustomerCB", arguments67);
            }
        }

        public class _17_0_2_1_edc0357_1360798765734_501480_20925 extends DurativeEvent {

            public _17_0_2_1_edc0357_1360798765734_501480_20925() {
                super();
                init_17_0_2_1_edc0357_1360798765734_501480_20925Members();
                init_17_0_2_1_edc0357_1360798765734_501480_20925Collections();
                init_17_0_2_1_edc0357_1360798765734_501480_20925Elaborations();
            }

            public _17_0_2_1_edc0357_1360798765734_501480_20925(Expression<Integer> startTime) {
                super();
                init_17_0_2_1_edc0357_1360798765734_501480_20925Members();
                init_17_0_2_1_edc0357_1360798765734_501480_20925Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_1_edc0357_1360798765734_501480_20925Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_1_edc0357_1360798765741_633596_20936_exists = null;

            public BooleanParameter _17_0_2_1_edc0357_1360798765738_797322_20931_exists = null;

            public BooleanParameter _17_0_2_1_edc0357_1360798765740_12841_20934_exists = null;

            public BooleanParameter _17_0_2_1_edc0357_1360798765739_210124_20933_exists = null;

            public BooleanParameter objectToPass = null;

            public Dependency< Boolean > _17_0_2_1_edc0357_1360798765741_633596_20936_existsDependency = null;

            public Dependency< Boolean > _17_0_2_1_edc0357_1360798765738_797322_20931_existsDependency = null;

            public Dependency< Boolean > _17_0_2_1_edc0357_1360798765740_12841_20934_existsDependency = null;

            public Dependency< Boolean > _17_0_2_1_edc0357_1360798765739_210124_20933_existsDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Effect effect68 = null;

            public Parameter effect68Var = null;

            public Effect effect69 = null;

            public Parameter effect69Var = null;

            public Effect effect70 = null;

            public Parameter effect70Var = null;

            public Effect effect71 = null;

            public Parameter effect71Var = null;

            public ElaborationRule elaborationRule72 = null;

            public ElaborationRule elaborationRule73 = null;

            public ElaborationRule elaborationRule74 = null;

            public ElaborationRule elaborationRule75 = null;

            public void init_17_0_2_1_edc0357_1360798765734_501480_20925Members() {
                try {
                    if (_17_0_2_1_edc0357_1360798765741_633596_20936_exists == null) _17_0_2_1_edc0357_1360798765741_633596_20936_exists = new BooleanParameter("_17_0_2_1_edc0357_1360798765741_633596_20936_exists", (Boolean) false, this);
                    if (_17_0_2_1_edc0357_1360798765738_797322_20931_exists == null) _17_0_2_1_edc0357_1360798765738_797322_20931_exists = new BooleanParameter("_17_0_2_1_edc0357_1360798765738_797322_20931_exists", (Boolean) false, this);
                    if (_17_0_2_1_edc0357_1360798765740_12841_20934_exists == null) _17_0_2_1_edc0357_1360798765740_12841_20934_exists = new BooleanParameter("_17_0_2_1_edc0357_1360798765740_12841_20934_exists", (Boolean) false, this);
                    if (_17_0_2_1_edc0357_1360798765739_210124_20933_exists == null) _17_0_2_1_edc0357_1360798765739_210124_20933_exists = new BooleanParameter("_17_0_2_1_edc0357_1360798765739_210124_20933_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect68VarV = sig_17_0_2_1_edc0357_1360798765744_788738_20950;
                    effect68Var = new Parameter("effect68Var", null, null, this);
                    addDependency(effect68Var, new Expression(effect68VarV));
                    effect68 = new EffectFunction(new EffectFunction(effect68Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "Demo_Scenario", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect69VarV = sig_17_0_2_1_edc0357_1360798765745_954000_20952;
                    effect69Var = new Parameter("effect69Var", null, null, this);
                    addDependency(effect69Var, new Expression(effect69VarV));
                    effect69 = new EffectFunction(new EffectFunction(effect69Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "Demo_Scenario", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect70VarV = sig_17_0_2_1_edc0357_1360798765745_72306_20957;
                    effect70Var = new Parameter("effect70Var", null, null, this);
                    addDependency(effect70Var, new Expression(effect70VarV));
                    effect70 = new EffectFunction(new EffectFunction(effect70Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "Demo_Scenario", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect71VarV = sig_17_0_2_1_edc0357_1360798765746_851404_20960;
                    effect71Var = new Parameter("effect71Var", null, null, this);
                    addDependency(effect71Var, new Expression(effect71VarV));
                    effect71 = new EffectFunction(new EffectFunction(effect71Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "Demo_Scenario", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_1_edc0357_1360798765734_501480_20925Collections() {
                parameters.add(_17_0_2_1_edc0357_1360798765741_633596_20936_exists);
                parameters.add(_17_0_2_1_edc0357_1360798765738_797322_20931_exists);
                parameters.add(_17_0_2_1_edc0357_1360798765740_12841_20934_exists);
                parameters.add(_17_0_2_1_edc0357_1360798765739_210124_20933_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect68Var = new TreeSet<Effect>();
                effectsForeffect68Var.add(effect68);
                addEffects((Parameter<?>) effect68Var, effectsForeffect68Var);
                Set<Effect> effectsForeffect69Var = new TreeSet<Effect>();
                effectsForeffect69Var.add(effect69);
                addEffects((Parameter<?>) effect69Var, effectsForeffect69Var);
                Set<Effect> effectsForeffect70Var = new TreeSet<Effect>();
                effectsForeffect70Var.add(effect70);
                addEffects((Parameter<?>) effect70Var, effectsForeffect70Var);
                Set<Effect> effectsForeffect71Var = new TreeSet<Effect>();
                effectsForeffect71Var.add(effect71);
                addEffects((Parameter<?>) effect71Var, effectsForeffect71Var);
            }

            public void init_17_0_2_1_edc0357_1360798765734_501480_20925Dependencies() {
                addDependency(_17_0_2_1_edc0357_1360798765741_633596_20936_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765737_27623_20930_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765737_27623_20930_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(_17_0_2_1_edc0357_1360798765738_797322_20931_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765737_27623_20930_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765737_27623_20930_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(_17_0_2_1_edc0357_1360798765740_12841_20934_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765737_27623_20930_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765737_27623_20930_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(_17_0_2_1_edc0357_1360798765739_210124_20933_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765737_27623_20930_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765737_27623_20930_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new EffectFunction(sig_17_0_2_1_edc0357_1360798765744_973847_20946, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "Demo_Scenario", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_1_edc0357_1360798765734_501480_20925Elaborations() {
                init_17_0_2_1_edc0357_1360798765734_501480_20925Dependencies();
                Expression<?>[] arguments72 = new Expression<?>[1];
                arguments72[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition72 = new Expression<Boolean>(_17_0_2_1_edc0357_1360798765741_633596_20936_exists);
                elaborationRule72 = addElaborationRule(condition72, _17_0_2_1_edc0357_1360798765037_222001_20270.this, Customer._17_0_2_1_edc0357_1360798765037_222001_20270._17_0_2_1_edc0357_1360798765741_633596_20936.class, "C_activity_timer_AcceptEventAction_CustomerCB", arguments72);
                Expression<?>[] arguments73 = new Expression<?>[2];
                arguments73[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                arguments73[1] = new Expression<ObjectFlow<Boolean>>(sig_17_0_2_1_edc0357_1360798765745_72306_20957);
                Expression<Boolean> condition73 = new Expression<Boolean>(_17_0_2_1_edc0357_1360798765740_12841_20934_exists);
                elaborationRule73 = addElaborationRule(condition73, _17_0_2_1_edc0357_1360798765037_222001_20270.this, Customer._17_0_2_1_edc0357_1360798765037_222001_20270._17_0_2_1_edc0357_1360798765740_12841_20934.class, "C_dr_m_MergeNode_CustomerCB", arguments73);
                Expression<?>[] arguments74 = new Expression<?>[2];
                arguments74[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                arguments74[1] = new Expression<ObjectFlow<Boolean>>(sig_17_0_2_1_edc0357_1360798765745_954000_20952);
                Expression<Boolean> condition74 = new Expression<Boolean>(_17_0_2_1_edc0357_1360798765739_210124_20933_exists);
                elaborationRule74 = addElaborationRule(condition74, _17_0_2_1_edc0357_1360798765037_222001_20270.this, Customer._17_0_2_1_edc0357_1360798765037_222001_20270._17_0_2_1_edc0357_1360798765739_210124_20933.class, "C_change_m_MergeNode_CustomerCB", arguments74);
                Expression<?>[] arguments75 = new Expression<?>[2];
                arguments75[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                arguments75[1] = new Expression<ObjectFlow<Boolean>>(sig_17_0_2_1_edc0357_1360798765744_788738_20950);
                Expression<Boolean> condition75 = new Expression<Boolean>(_17_0_2_1_edc0357_1360798765738_797322_20931_exists);
                elaborationRule75 = addElaborationRule(condition75, _17_0_2_1_edc0357_1360798765037_222001_20270.this, Customer._17_0_2_1_edc0357_1360798765037_222001_20270._17_0_2_1_edc0357_1360798765738_797322_20931.class, "C_use_m_MergeNode_CustomerCB", arguments75);
            }
        }

        public class _17_0_2_1_edc0357_1360798765735_403788_20926 extends DurativeEvent {

            public _17_0_2_1_edc0357_1360798765735_403788_20926() {
                super();
                init_17_0_2_1_edc0357_1360798765735_403788_20926Members();
                init_17_0_2_1_edc0357_1360798765735_403788_20926Collections();
                init_17_0_2_1_edc0357_1360798765735_403788_20926Elaborations();
            }

            public _17_0_2_1_edc0357_1360798765735_403788_20926(Expression<Integer> startTime) {
                super();
                init_17_0_2_1_edc0357_1360798765735_403788_20926Members();
                init_17_0_2_1_edc0357_1360798765735_403788_20926Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_1_edc0357_1360798765735_403788_20926Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_1_edc0357_1360798765738_580431_20932_exists = null;

            public Parameter< Power_System.Signaldr_request > _17_0_2_1_edc0357_1360798766429_995963_22072 = null;

            public BooleanParameter objectToPass = null;

            public Dependency< Power_System.Signaldr_request > _17_0_2_1_edc0357_1360798766429_995963_22072Dependency = null;

            public Dependency< Boolean > _17_0_2_1_edc0357_1360798765738_580431_20932_existsDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Effect effect76 = null;

            public Parameter effect76Var = null;

            public ElaborationRule elaborationRule77 = null;

            public void init_17_0_2_1_edc0357_1360798765735_403788_20926Members() {
                try {
                    if (_17_0_2_1_edc0357_1360798765738_580431_20932_exists == null) _17_0_2_1_edc0357_1360798765738_580431_20932_exists = new BooleanParameter("_17_0_2_1_edc0357_1360798765738_580431_20932_exists", (Boolean) false, this);
                    if (_17_0_2_1_edc0357_1360798766429_995963_22072 == null) _17_0_2_1_edc0357_1360798766429_995963_22072 = new Parameter<Power_System.Signaldr_request>("_17_0_2_1_edc0357_1360798766429_995963_22072", null, (Power_System.Signaldr_request) null, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect76VarV = sig_17_0_2_1_edc0357_1360798765745_692296_20953;
                    effect76Var = new Parameter("effect76Var", null, null, this);
                    addDependency(effect76Var, new Expression(effect76VarV));
                    effect76 = new EffectFunction(new EffectFunction(effect76Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Signaldr_request>", "Demo_Scenario", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { _17_0_2_1_edc0357_1360798766429_995963_22072, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_1_edc0357_1360798765735_403788_20926Collections() {
                parameters.add(_17_0_2_1_edc0357_1360798765738_580431_20932_exists);
                parameters.add(_17_0_2_1_edc0357_1360798766429_995963_22072);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect76Var = new TreeSet<Effect>();
                effectsForeffect76Var.add(effect76);
                addEffects((Parameter<?>) effect76Var, effectsForeffect76Var);
            }

            public void init_17_0_2_1_edc0357_1360798765735_403788_20926Dependencies() {
                addDependency(_17_0_2_1_edc0357_1360798766429_995963_22072, new Expression<Power_System.Signaldr_request>(new EffectFunction(q_Customer_dr_request, ClassUtils.getMethodForArgTypes("ObjectFlow<Signaldr_request>", "Demo_Scenario", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_1_edc0357_1360798765738_580431_20932_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765737_27623_20930_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765737_27623_20930_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new EffectFunction(sig_17_0_2_1_edc0357_1360798765745_845538_20955, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "Demo_Scenario", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_1_edc0357_1360798765735_403788_20926Elaborations() {
                init_17_0_2_1_edc0357_1360798765735_403788_20926Dependencies();
                Expression<?>[] arguments77 = new Expression<?>[1];
                arguments77[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition77 = new Expression<Boolean>(_17_0_2_1_edc0357_1360798765738_580431_20932_exists);
                elaborationRule77 = addElaborationRule(condition77, _17_0_2_1_edc0357_1360798765037_222001_20270.this, Customer._17_0_2_1_edc0357_1360798765037_222001_20270._17_0_2_1_edc0357_1360798765738_580431_20932.class, "C_readReductionLevel_ReadStructuralFeatureAction_CustomerCB", arguments77);
            }
        }

        public class _17_0_2_1_edc0357_1360798765736_958603_20927 extends DurativeEvent {

            public _17_0_2_1_edc0357_1360798765736_958603_20927() {
                super();
                init_17_0_2_1_edc0357_1360798765736_958603_20927Members();
                init_17_0_2_1_edc0357_1360798765736_958603_20927Collections();
                init_17_0_2_1_edc0357_1360798765736_958603_20927Elaborations();
            }

            public _17_0_2_1_edc0357_1360798765736_958603_20927(Expression<Integer> startTime) {
                super();
                init_17_0_2_1_edc0357_1360798765736_958603_20927Members();
                init_17_0_2_1_edc0357_1360798765736_958603_20927Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_1_edc0357_1360798765736_958603_20927Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_1_edc0357_1360798765738_797322_20931_exists = null;

            public BooleanParameter objectToPass = null;

            public Dependency< Boolean > _17_0_2_1_edc0357_1360798765738_797322_20931_existsDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Effect effect78 = null;

            public Parameter effect78Var = null;

            public ElaborationRule elaborationRule79 = null;

            public void init_17_0_2_1_edc0357_1360798765736_958603_20927Members() {
                try {
                    if (_17_0_2_1_edc0357_1360798765738_797322_20931_exists == null) _17_0_2_1_edc0357_1360798765738_797322_20931_exists = new BooleanParameter("_17_0_2_1_edc0357_1360798765738_797322_20931_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect78VarV = sig_17_0_2_1_edc0357_1360798765744_378657_20947;
                    effect78Var = new Parameter("effect78Var", null, null, this);
                    addDependency(effect78Var, new Expression(effect78VarV));
                    effect78 = new EffectFunction(new EffectFunction(effect78Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "Demo_Scenario", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_1_edc0357_1360798765736_958603_20927Collections() {
                parameters.add(_17_0_2_1_edc0357_1360798765738_797322_20931_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect78Var = new TreeSet<Effect>();
                effectsForeffect78Var.add(effect78);
                addEffects((Parameter<?>) effect78Var, effectsForeffect78Var);
            }

            public void init_17_0_2_1_edc0357_1360798765736_958603_20927Dependencies() {
                addDependency(_17_0_2_1_edc0357_1360798765738_797322_20931_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765737_27623_20930_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765737_27623_20930_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(900));
                addDependency(objectToPass, new Expression<Boolean>(new EffectFunction(sig_17_0_2_1_edc0357_1360879806465_663546_16039, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "Demo_Scenario", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_1_edc0357_1360798765736_958603_20927Elaborations() {
                init_17_0_2_1_edc0357_1360798765736_958603_20927Dependencies();
                Expression<?>[] arguments79 = new Expression<?>[2];
                arguments79[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                arguments79[1] = new Expression<ObjectFlow<Boolean>>(sig_17_0_2_1_edc0357_1360798765744_378657_20947);
                Expression<Boolean> condition79 = new Expression<Boolean>(_17_0_2_1_edc0357_1360798765738_797322_20931_exists);
                elaborationRule79 = addElaborationRule(condition79, _17_0_2_1_edc0357_1360798765037_222001_20270.this, Customer._17_0_2_1_edc0357_1360798765037_222001_20270._17_0_2_1_edc0357_1360798765738_797322_20931.class, "C_use_m_MergeNode_CustomerCB", arguments79);
            }
        }

        public class _17_0_2_1_edc0357_1360798765736_372247_20928 extends Customer._17_0_2_1_edc0357_1360798765036_519893_20269_interface {

            public _17_0_2_1_edc0357_1360798765736_372247_20928() {
                super();
                init_17_0_2_1_edc0357_1360798765736_372247_20928Members();
                init_17_0_2_1_edc0357_1360798765736_372247_20928Collections();
                init_17_0_2_1_edc0357_1360798765736_372247_20928Elaborations();
            }

            public _17_0_2_1_edc0357_1360798765736_372247_20928(Expression<Integer> startTime) {
                super();
                init_17_0_2_1_edc0357_1360798765736_372247_20928Members();
                init_17_0_2_1_edc0357_1360798765736_372247_20928Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_1_edc0357_1360798765736_372247_20928Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_1_edc0357_1360798765740_12841_20934_exists = null;

            public BooleanParameter objectToPass = null;

            public DoubleParameter _17_0_2_1_edc0357_1360798766430_23808_22074 = null;

            public Dependency< Boolean > _17_0_2_1_edc0357_1360798765740_12841_20934_existsDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Dependency< Double > _17_0_2_1_edc0357_1360798766430_23808_22074Dependency = null;

            public Dependency< Double > _17_0_2_1_edc0357_1360798765691_696195_20849Dependency = null;

            public Effect effect80 = null;

            public Parameter effect80Var = null;

            public ElaborationRule elaborationRule81 = null;

            public ElaborationRule elaborationRule82 = null;

            public void init_17_0_2_1_edc0357_1360798765736_372247_20928Members() {
                try {
                    if (_17_0_2_1_edc0357_1360798765740_12841_20934_exists == null) _17_0_2_1_edc0357_1360798765740_12841_20934_exists = new BooleanParameter("_17_0_2_1_edc0357_1360798765740_12841_20934_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (_17_0_2_1_edc0357_1360798766430_23808_22074 == null) _17_0_2_1_edc0357_1360798766430_23808_22074 = new DoubleParameter("_17_0_2_1_edc0357_1360798766430_23808_22074", (Double) null, this);
                    Object effect80VarV = sig_17_0_2_1_edc0357_1360798765745_929532_20956;
                    effect80Var = new Parameter("effect80Var", null, null, this);
                    addDependency(effect80Var, new Expression(effect80VarV));
                    effect80 = new EffectFunction(new EffectFunction(effect80Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "Demo_Scenario", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_1_edc0357_1360798765736_372247_20928Collections() {
                parameters.add(_17_0_2_1_edc0357_1360798765740_12841_20934_exists);
                parameters.add(objectToPass);
                parameters.add(_17_0_2_1_edc0357_1360798766430_23808_22074);
                Set<Effect> effectsForeffect80Var = new TreeSet<Effect>();
                effectsForeffect80Var.add(effect80);
                addEffects((Parameter<?>) effect80Var, effectsForeffect80Var);
            }

            public void init_17_0_2_1_edc0357_1360798765736_372247_20928Dependencies() {
                addDependency(_17_0_2_1_edc0357_1360798765740_12841_20934_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765737_27623_20930_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765737_27623_20930_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(objectToPass, new Expression<Boolean>(true));
                addDependency(_17_0_2_1_edc0357_1360798766430_23808_22074, new Expression<Double>(new EffectFunction(sig_17_0_2_1_edc0357_1360798765745_439888_20954, ClassUtils.getMethodForArgTypes("ObjectFlow<Float>", "Demo_Scenario", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_1_edc0357_1360798765691_696195_20849, new Expression<Double>(_17_0_2_1_edc0357_1360798766430_23808_22074));
            }

            public void init_17_0_2_1_edc0357_1360798765736_372247_20928Elaborations() {
                init_17_0_2_1_edc0357_1360798765736_372247_20928Dependencies();
                Expression<?>[] arguments81 = new Expression<?>[3];
                arguments81[0] = new Expression<Integer>(startTime);
                arguments81[1] = new Expression<_17_0_2_1_edc0357_1360798765036_519893_20269_interface>(this);
                arguments81[2] = new Expression<Double>(_17_0_2_1_edc0357_1360798765691_696195_20849);
                Expression<Boolean> condition81 = new Expression<Boolean>(true);
                elaborationRule81 = addElaborationRule(condition81, Customer.this, Customer._17_0_2_1_edc0357_1360798765036_519893_20269.class, "setDRCap_Activity_Customer", arguments81);
                Expression<?>[] arguments82 = new Expression<?>[2];
                arguments82[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                arguments82[1] = new Expression<ObjectFlow<Boolean>>(sig_17_0_2_1_edc0357_1360798765745_929532_20956);
                Expression<Boolean> condition82 = new Expression<Boolean>(_17_0_2_1_edc0357_1360798765740_12841_20934_exists);
                elaborationRule82 = addElaborationRule(condition82, _17_0_2_1_edc0357_1360798765037_222001_20270.this, Customer._17_0_2_1_edc0357_1360798765037_222001_20270._17_0_2_1_edc0357_1360798765740_12841_20934.class, "C_dr_m_MergeNode_CustomerCB", arguments82);
            }
        }

        public class _17_0_2_1_edc0357_1360798765737_27623_20930 extends DurativeEvent {

            public _17_0_2_1_edc0357_1360798765737_27623_20930() {
                super();
                init_17_0_2_1_edc0357_1360798765737_27623_20930Members();
                init_17_0_2_1_edc0357_1360798765737_27623_20930Collections();
                init_17_0_2_1_edc0357_1360798765737_27623_20930Elaborations();
            }

            public _17_0_2_1_edc0357_1360798765737_27623_20930(Expression<Integer> startTime) {
                super();
                init_17_0_2_1_edc0357_1360798765737_27623_20930Members();
                init_17_0_2_1_edc0357_1360798765737_27623_20930Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_1_edc0357_1360798765737_27623_20930Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter objectToPass = null;

            public Dependency< Integer > endTimeDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Dependency< Integer > finalNode_endTimeDependency = null;

            public void init_17_0_2_1_edc0357_1360798765737_27623_20930Members() {
                try {
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_1_edc0357_1360798765737_27623_20930Collections() {
                parameters.add(objectToPass);
            }

            public void init_17_0_2_1_edc0357_1360798765737_27623_20930Dependencies() {
                addDependency(endTime, new Expression<Integer>(new Functions.Plus(new Expression<Integer>(startTime), new Expression<Integer>(duration))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new EffectFunction(sig_17_0_2_1_edc0357_1360798765746_550928_20961, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "Demo_Scenario", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(finalNode_endTime, new Expression<Integer>(endTime));
            }

            public void init_17_0_2_1_edc0357_1360798765737_27623_20930Elaborations() {
                init_17_0_2_1_edc0357_1360798765737_27623_20930Dependencies();
            }
        }

        public class _17_0_2_1_edc0357_1360798765738_797322_20931 extends DurativeEvent {

            public _17_0_2_1_edc0357_1360798765738_797322_20931() {
                super();
                init_17_0_2_1_edc0357_1360798765738_797322_20931Members();
                init_17_0_2_1_edc0357_1360798765738_797322_20931Collections();
                init_17_0_2_1_edc0357_1360798765738_797322_20931Elaborations();
            }

            public _17_0_2_1_edc0357_1360798765738_797322_20931(Expression<Integer> startTime, Expression<ObjectFlow<Boolean>> receiveThis) {
                super();
                init_17_0_2_1_edc0357_1360798765738_797322_20931Members();
                init_17_0_2_1_edc0357_1360798765738_797322_20931Collections();
                addDependency(this.startTime, startTime);
                addDependency(this.receiveThis, receiveThis);
                init_17_0_2_1_edc0357_1360798765738_797322_20931Elaborations();
                fixTimeDependencies();
            }

            public Parameter< ObjectFlow<Boolean> > receiveThis = null;

            public BooleanParameter objectToPass = null;

            public BooleanParameter _17_0_2_1_edc0357_1360879730962_992833_16000_exists = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Dependency< Boolean > _17_0_2_1_edc0357_1360879730962_992833_16000_existsDependency = null;

            public Effect effect83 = null;

            public Parameter effect83Var = null;

            public ElaborationRule elaborationRule84 = null;

            public void init_17_0_2_1_edc0357_1360798765738_797322_20931Members() {
                try {
                    if (receiveThis == null) receiveThis = new Parameter<ObjectFlow<Boolean>>("receiveThis", null, (ObjectFlow<Boolean>) null, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (_17_0_2_1_edc0357_1360879730962_992833_16000_exists == null) _17_0_2_1_edc0357_1360879730962_992833_16000_exists = new BooleanParameter("_17_0_2_1_edc0357_1360879730962_992833_16000_exists", (Boolean) false, this);
                    Object effect83VarV = sig_17_0_2_1_edc0357_1360879763189_916669_16029;
                    effect83Var = new Parameter("effect83Var", null, null, this);
                    addDependency(effect83Var, new Expression(effect83VarV));
                    effect83 = new EffectFunction(new EffectFunction(effect83Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "Demo_Scenario", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_1_edc0357_1360798765738_797322_20931Collections() {
                parameters.add(receiveThis);
                parameters.add(objectToPass);
                parameters.add(_17_0_2_1_edc0357_1360879730962_992833_16000_exists);
                Set<Effect> effectsForeffect83Var = new TreeSet<Effect>();
                effectsForeffect83Var.add(effect83);
                addEffects((Parameter<?>) effect83Var, effectsForeffect83Var);
            }

            public void init_17_0_2_1_edc0357_1360798765738_797322_20931Dependencies() {
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new EffectFunction(receiveThis, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "Demo_Scenario", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_1_edc0357_1360879730962_992833_16000_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765737_27623_20930_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765737_27623_20930_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
            }

            public void init_17_0_2_1_edc0357_1360798765738_797322_20931Elaborations() {
                init_17_0_2_1_edc0357_1360798765738_797322_20931Dependencies();
                Expression<?>[] arguments84 = new Expression<?>[1];
                arguments84[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition84 = new Expression<Boolean>(_17_0_2_1_edc0357_1360879730962_992833_16000_exists);
                elaborationRule84 = addElaborationRule(condition84, _17_0_2_1_edc0357_1360798765037_222001_20270.this, Customer._17_0_2_1_edc0357_1360798765037_222001_20270._17_0_2_1_edc0357_1360879730962_992833_16000.class, "CUse_readusageparam_ReadStructuralFeatureAction_CustomerCB", arguments84);
            }
        }

        public class _17_0_2_1_edc0357_1360798765738_580431_20932 extends DurativeEvent {

            public _17_0_2_1_edc0357_1360798765738_580431_20932() {
                super();
                init_17_0_2_1_edc0357_1360798765738_580431_20932Members();
                init_17_0_2_1_edc0357_1360798765738_580431_20932Collections();
                init_17_0_2_1_edc0357_1360798765738_580431_20932Elaborations();
            }

            public _17_0_2_1_edc0357_1360798765738_580431_20932(Expression<Integer> startTime) {
                super();
                init_17_0_2_1_edc0357_1360798765738_580431_20932Members();
                init_17_0_2_1_edc0357_1360798765738_580431_20932Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_1_edc0357_1360798765738_580431_20932Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_1_edc0357_1360798765736_372247_20928_exists = null;

            public Parameter< Power_System.Signaldr_request > _17_0_2_1_edc0357_1360798766431_574039_22076 = null;

            public DoubleParameter _17_0_2_1_edc0357_1360798766430_702718_22075 = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Double > _17_0_2_1_edc0357_1360798766430_702718_22075Dependency = null;

            public Dependency< Boolean > _17_0_2_1_edc0357_1360798765736_372247_20928_existsDependency = null;

            public Dependency< Power_System.Signaldr_request > _17_0_2_1_edc0357_1360798766431_574039_22076Dependency = null;

            public Effect effect85 = null;

            public Parameter effect85Var = null;

            public ElaborationRule elaborationRule86 = null;

            public void init_17_0_2_1_edc0357_1360798765738_580431_20932Members() {
                try {
                    if (_17_0_2_1_edc0357_1360798765736_372247_20928_exists == null) _17_0_2_1_edc0357_1360798765736_372247_20928_exists = new BooleanParameter("_17_0_2_1_edc0357_1360798765736_372247_20928_exists", (Boolean) false, this);
                    if (_17_0_2_1_edc0357_1360798766431_574039_22076 == null) _17_0_2_1_edc0357_1360798766431_574039_22076 = new Parameter<Power_System.Signaldr_request>("_17_0_2_1_edc0357_1360798766431_574039_22076", null, (Power_System.Signaldr_request) null, this);
                    if (_17_0_2_1_edc0357_1360798766430_702718_22075 == null) _17_0_2_1_edc0357_1360798766430_702718_22075 = new DoubleParameter("_17_0_2_1_edc0357_1360798766430_702718_22075", (Double) null, this);
                    Object effect85VarV = sig_17_0_2_1_edc0357_1360798765745_439888_20954;
                    effect85Var = new Parameter("effect85Var", null, null, this);
                    addDependency(effect85Var, new Expression(effect85VarV));
                    effect85 = new EffectFunction(new EffectFunction(effect85Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Float>", "Demo_Scenario", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { _17_0_2_1_edc0357_1360798766430_702718_22075, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_1_edc0357_1360798765738_580431_20932Collections() {
                parameters.add(_17_0_2_1_edc0357_1360798765736_372247_20928_exists);
                parameters.add(_17_0_2_1_edc0357_1360798766431_574039_22076);
                parameters.add(_17_0_2_1_edc0357_1360798766430_702718_22075);
                Set<Effect> effectsForeffect85Var = new TreeSet<Effect>();
                effectsForeffect85Var.add(effect85);
                addEffects((Parameter<?>) effect85Var, effectsForeffect85Var);
            }

            public void init_17_0_2_1_edc0357_1360798765738_580431_20932Dependencies() {
                addDependency(duration, new Expression<Integer>(1));
                addDependency(_17_0_2_1_edc0357_1360798766430_702718_22075, new Expression<Double>(new EffectFunction(null, ClassUtils.getMethodForArgTypes("TimeVaryingMap<Float>", "Demo_Scenario", "getValue", java.lang.Integer.class), new Object[] { new Expression<Integer>(startTime) }, new FunctionCall(_17_0_2_1_edc0357_1360798766431_574039_22076, Parameter.class, "getMember", new Object[] { "reduction_percent__17_0_2_1_edc0357_1360798765096_200759_20353" }))));
                addDependency(_17_0_2_1_edc0357_1360798765736_372247_20928_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765737_27623_20930_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765737_27623_20930_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(_17_0_2_1_edc0357_1360798766431_574039_22076, new Expression<Power_System.Signaldr_request>(new EffectFunction(sig_17_0_2_1_edc0357_1360798765745_692296_20953, ClassUtils.getMethodForArgTypes("ObjectFlow<Signaldr_request>", "Demo_Scenario", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_1_edc0357_1360798765738_580431_20932Elaborations() {
                init_17_0_2_1_edc0357_1360798765738_580431_20932Dependencies();
                Expression<?>[] arguments86 = new Expression<?>[1];
                arguments86[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition86 = new Expression<Boolean>(_17_0_2_1_edc0357_1360798765736_372247_20928_exists);
                elaborationRule86 = addElaborationRule(condition86, _17_0_2_1_edc0357_1360798765037_222001_20270.this, Customer._17_0_2_1_edc0357_1360798765037_222001_20270._17_0_2_1_edc0357_1360798765736_372247_20928.class, "C_setcap_CallBehaviorAction_CustomerCB", arguments86);
            }
        }

        public class _17_0_2_1_edc0357_1360798765739_210124_20933 extends DurativeEvent {

            public _17_0_2_1_edc0357_1360798765739_210124_20933() {
                super();
                init_17_0_2_1_edc0357_1360798765739_210124_20933Members();
                init_17_0_2_1_edc0357_1360798765739_210124_20933Collections();
                init_17_0_2_1_edc0357_1360798765739_210124_20933Elaborations();
            }

            public _17_0_2_1_edc0357_1360798765739_210124_20933(Expression<Integer> startTime, Expression<ObjectFlow<Boolean>> receiveThis) {
                super();
                init_17_0_2_1_edc0357_1360798765739_210124_20933Members();
                init_17_0_2_1_edc0357_1360798765739_210124_20933Collections();
                addDependency(this.startTime, startTime);
                addDependency(this.receiveThis, receiveThis);
                init_17_0_2_1_edc0357_1360798765739_210124_20933Elaborations();
                fixTimeDependencies();
            }

            public Parameter< ObjectFlow<Boolean> > receiveThis = null;

            public BooleanParameter _17_0_2_1_edc0357_1360798765742_888690_20937_exists = null;

            public BooleanParameter objectToPass = null;

            public Dependency< Boolean > _17_0_2_1_edc0357_1360798765742_888690_20937_existsDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Effect effect87 = null;

            public Parameter effect87Var = null;

            public ElaborationRule elaborationRule88 = null;

            public void init_17_0_2_1_edc0357_1360798765739_210124_20933Members() {
                try {
                    if (receiveThis == null) receiveThis = new Parameter<ObjectFlow<Boolean>>("receiveThis", null, (ObjectFlow<Boolean>) null, this);
                    if (_17_0_2_1_edc0357_1360798765742_888690_20937_exists == null) _17_0_2_1_edc0357_1360798765742_888690_20937_exists = new BooleanParameter("_17_0_2_1_edc0357_1360798765742_888690_20937_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect87VarV = sig_17_0_2_1_edc0357_1360798765746_684537_20962;
                    effect87Var = new Parameter("effect87Var", null, null, this);
                    addDependency(effect87Var, new Expression(effect87VarV));
                    effect87 = new EffectFunction(new EffectFunction(effect87Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "Demo_Scenario", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_1_edc0357_1360798765739_210124_20933Collections() {
                parameters.add(receiveThis);
                parameters.add(_17_0_2_1_edc0357_1360798765742_888690_20937_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect87Var = new TreeSet<Effect>();
                effectsForeffect87Var.add(effect87);
                addEffects((Parameter<?>) effect87Var, effectsForeffect87Var);
            }

            public void init_17_0_2_1_edc0357_1360798765739_210124_20933Dependencies() {
                addDependency(_17_0_2_1_edc0357_1360798765742_888690_20937_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765737_27623_20930_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765737_27623_20930_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new EffectFunction(receiveThis, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "Demo_Scenario", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_1_edc0357_1360798765739_210124_20933Elaborations() {
                init_17_0_2_1_edc0357_1360798765739_210124_20933Dependencies();
                Expression<?>[] arguments88 = new Expression<?>[1];
                arguments88[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition88 = new Expression<Boolean>(_17_0_2_1_edc0357_1360798765742_888690_20937_exists);
                elaborationRule88 = addElaborationRule(condition88, _17_0_2_1_edc0357_1360798765037_222001_20270.this, Customer._17_0_2_1_edc0357_1360798765037_222001_20270._17_0_2_1_edc0357_1360798765742_888690_20937.class, "C_change_timer_AcceptEventAction_CustomerCB", arguments88);
            }
        }

        public class _17_0_2_1_edc0357_1360798765740_12841_20934 extends DurativeEvent {

            public _17_0_2_1_edc0357_1360798765740_12841_20934() {
                super();
                init_17_0_2_1_edc0357_1360798765740_12841_20934Members();
                init_17_0_2_1_edc0357_1360798765740_12841_20934Collections();
                init_17_0_2_1_edc0357_1360798765740_12841_20934Elaborations();
            }

            public _17_0_2_1_edc0357_1360798765740_12841_20934(Expression<Integer> startTime, Expression<ObjectFlow<Boolean>> receiveThis) {
                super();
                init_17_0_2_1_edc0357_1360798765740_12841_20934Members();
                init_17_0_2_1_edc0357_1360798765740_12841_20934Collections();
                addDependency(this.startTime, startTime);
                addDependency(this.receiveThis, receiveThis);
                init_17_0_2_1_edc0357_1360798765740_12841_20934Elaborations();
                fixTimeDependencies();
            }

            public Parameter< ObjectFlow<Boolean> > receiveThis = null;

            public BooleanParameter _17_0_2_1_edc0357_1360798765735_403788_20926_exists = null;

            public BooleanParameter objectToPass = null;

            public Dependency< Integer > endTimeDependency = null;

            public Dependency< Boolean > _17_0_2_1_edc0357_1360798765735_403788_20926_existsDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Effect effect89 = null;

            public Parameter effect89Var = null;

            public ElaborationRule elaborationRule90 = null;

            public void init_17_0_2_1_edc0357_1360798765740_12841_20934Members() {
                try {
                    if (receiveThis == null) receiveThis = new Parameter<ObjectFlow<Boolean>>("receiveThis", null, (ObjectFlow<Boolean>) null, this);
                    if (_17_0_2_1_edc0357_1360798765735_403788_20926_exists == null) _17_0_2_1_edc0357_1360798765735_403788_20926_exists = new BooleanParameter("_17_0_2_1_edc0357_1360798765735_403788_20926_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect89VarV = sig_17_0_2_1_edc0357_1360798765745_845538_20955;
                    effect89Var = new Parameter("effect89Var", null, null, this);
                    addDependency(effect89Var, new Expression(effect89VarV));
                    effect89 = new EffectFunction(new EffectFunction(effect89Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "Demo_Scenario", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_1_edc0357_1360798765740_12841_20934Collections() {
                parameters.add(receiveThis);
                parameters.add(_17_0_2_1_edc0357_1360798765735_403788_20926_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect89Var = new TreeSet<Effect>();
                effectsForeffect89Var.add(effect89);
                addEffects((Parameter<?>) effect89Var, effectsForeffect89Var);
            }

            public void init_17_0_2_1_edc0357_1360798765740_12841_20934Dependencies() {
                addDependency(endTime, new Expression<Integer>(new FunctionCall(null, ClassUtils.getMethodForArgTypes("Math", "Demo_Scenario", "min", int.class, int.class), new Object[] { new Expression(new EffectFunction(q_Customer_dr_request, ClassUtils.getMethodForArgTypes("ObjectFlow<Signaldr_request>", "Demo_Scenario", "nextTimeHasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(startTime) })), new Expression<Integer>(finalNode_endTime) })));
                addDependency(_17_0_2_1_edc0357_1360798765735_403788_20926_exists, new Expression<Boolean>(new Functions.And(new Expression(new EffectFunction(q_Customer_dr_request, ClassUtils.getMethodForArgTypes("ObjectFlow<Signaldr_request>", "Demo_Scenario", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765737_27623_20930_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765737_27623_20930_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))))));
                addDependency(objectToPass, new Expression<Boolean>(new EffectFunction(receiveThis, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "Demo_Scenario", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_1_edc0357_1360798765740_12841_20934Elaborations() {
                init_17_0_2_1_edc0357_1360798765740_12841_20934Dependencies();
                Expression<?>[] arguments90 = new Expression<?>[1];
                arguments90[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition90 = new Expression<Boolean>(_17_0_2_1_edc0357_1360798765735_403788_20926_exists);
                elaborationRule90 = addElaborationRule(condition90, _17_0_2_1_edc0357_1360798765037_222001_20270.this, Customer._17_0_2_1_edc0357_1360798765037_222001_20270._17_0_2_1_edc0357_1360798765735_403788_20926.class, "C_get_drsig_AcceptEventAction_CustomerCB", arguments90);
            }
        }

        public class _17_0_2_1_edc0357_1360798765741_633596_20936 extends DurativeEvent {

            public _17_0_2_1_edc0357_1360798765741_633596_20936() {
                super();
                init_17_0_2_1_edc0357_1360798765741_633596_20936Members();
                init_17_0_2_1_edc0357_1360798765741_633596_20936Collections();
                init_17_0_2_1_edc0357_1360798765741_633596_20936Elaborations();
            }

            public _17_0_2_1_edc0357_1360798765741_633596_20936(Expression<Integer> startTime) {
                super();
                init_17_0_2_1_edc0357_1360798765741_633596_20936Members();
                init_17_0_2_1_edc0357_1360798765741_633596_20936Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_1_edc0357_1360798765741_633596_20936Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter objectToPass = null;

            public Dependency< Integer > finalNode_startTimeDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Effect effect91 = null;

            public Parameter effect91Var = null;

            public void init_17_0_2_1_edc0357_1360798765741_633596_20936Members() {
                try {
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect91VarV = sig_17_0_2_1_edc0357_1360798765746_550928_20961;
                    effect91Var = new Parameter("effect91Var", null, null, this);
                    addDependency(effect91Var, new Expression(effect91VarV));
                    effect91 = new EffectFunction(new EffectFunction(effect91Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "Demo_Scenario", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_1_edc0357_1360798765741_633596_20936Collections() {
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect91Var = new TreeSet<Effect>();
                effectsForeffect91Var.add(effect91);
                addEffects((Parameter<?>) effect91Var, effectsForeffect91Var);
            }

            public void init_17_0_2_1_edc0357_1360798765741_633596_20936Dependencies() {
                addDependency(finalNode_startTime, new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))));
                addDependency(duration, new Expression<Integer>(14400));
                addDependency(objectToPass, new Expression<Boolean>(new EffectFunction(sig_17_0_2_1_edc0357_1360798765746_851404_20960, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "Demo_Scenario", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_1_edc0357_1360798765741_633596_20936Elaborations() {
                init_17_0_2_1_edc0357_1360798765741_633596_20936Dependencies();
            }
        }

        public class _17_0_2_1_edc0357_1360798765742_888690_20937 extends DurativeEvent {

            public _17_0_2_1_edc0357_1360798765742_888690_20937() {
                super();
                init_17_0_2_1_edc0357_1360798765742_888690_20937Members();
                init_17_0_2_1_edc0357_1360798765742_888690_20937Collections();
                init_17_0_2_1_edc0357_1360798765742_888690_20937Elaborations();
            }

            public _17_0_2_1_edc0357_1360798765742_888690_20937(Expression<Integer> startTime) {
                super();
                init_17_0_2_1_edc0357_1360798765742_888690_20937Members();
                init_17_0_2_1_edc0357_1360798765742_888690_20937Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_1_edc0357_1360798765742_888690_20937Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_1_edc0357_1360798765742_582142_20938_exists = null;

            public BooleanParameter objectToPass = null;

            public Dependency< Boolean > _17_0_2_1_edc0357_1360798765742_582142_20938_existsDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Effect effect92 = null;

            public Parameter effect92Var = null;

            public ElaborationRule elaborationRule93 = null;

            public void init_17_0_2_1_edc0357_1360798765742_888690_20937Members() {
                try {
                    if (_17_0_2_1_edc0357_1360798765742_582142_20938_exists == null) _17_0_2_1_edc0357_1360798765742_582142_20938_exists = new BooleanParameter("_17_0_2_1_edc0357_1360798765742_582142_20938_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect92VarV = sig_17_0_2_1_edc0357_1360798765746_317210_20963;
                    effect92Var = new Parameter("effect92Var", null, null, this);
                    addDependency(effect92Var, new Expression(effect92VarV));
                    effect92 = new EffectFunction(new EffectFunction(effect92Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "Demo_Scenario", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_1_edc0357_1360798765742_888690_20937Collections() {
                parameters.add(_17_0_2_1_edc0357_1360798765742_582142_20938_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect92Var = new TreeSet<Effect>();
                effectsForeffect92Var.add(effect92);
                addEffects((Parameter<?>) effect92Var, effectsForeffect92Var);
            }

            public void init_17_0_2_1_edc0357_1360798765742_888690_20937Dependencies() {
                addDependency(_17_0_2_1_edc0357_1360798765742_582142_20938_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765737_27623_20930_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765737_27623_20930_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(240));
                addDependency(objectToPass, new Expression<Boolean>(new EffectFunction(sig_17_0_2_1_edc0357_1360798765746_684537_20962, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "Demo_Scenario", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_1_edc0357_1360798765742_888690_20937Elaborations() {
                init_17_0_2_1_edc0357_1360798765742_888690_20937Dependencies();
                Expression<?>[] arguments93 = new Expression<?>[1];
                arguments93[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition93 = new Expression<Boolean>(_17_0_2_1_edc0357_1360798765742_582142_20938_exists);
                elaborationRule93 = addElaborationRule(condition93, _17_0_2_1_edc0357_1360798765037_222001_20270.this, Customer._17_0_2_1_edc0357_1360798765037_222001_20270._17_0_2_1_edc0357_1360798765742_582142_20938.class, "C_useWithCap_CallBehaviorAction_CustomerCB", arguments93);
            }
        }

        public class _17_0_2_1_edc0357_1360798765742_582142_20938 extends DurativeEvent {

            public _17_0_2_1_edc0357_1360798765742_582142_20938() {
                super();
                init_17_0_2_1_edc0357_1360798765742_582142_20938Members();
                init_17_0_2_1_edc0357_1360798765742_582142_20938Collections();
                init_17_0_2_1_edc0357_1360798765742_582142_20938Elaborations();
            }

            public _17_0_2_1_edc0357_1360798765742_582142_20938(Expression<Integer> startTime) {
                super();
                init_17_0_2_1_edc0357_1360798765742_582142_20938Members();
                init_17_0_2_1_edc0357_1360798765742_582142_20938Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_1_edc0357_1360798765742_582142_20938Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_1_edc0357_1360798765739_210124_20933_exists = null;

            public BooleanParameter objectToPass = null;

            public Dependency< Boolean > _17_0_2_1_edc0357_1360798765739_210124_20933_existsDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Effect effect94 = null;

            public Parameter effect94Var = null;

            public ElaborationRule elaborationRule95 = null;

            public ElaborationRule elaborationRule96 = null;

            public void init_17_0_2_1_edc0357_1360798765742_582142_20938Members() {
                try {
                    if (_17_0_2_1_edc0357_1360798765739_210124_20933_exists == null) _17_0_2_1_edc0357_1360798765739_210124_20933_exists = new BooleanParameter("_17_0_2_1_edc0357_1360798765739_210124_20933_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect94VarV = sig_17_0_2_1_edc0357_1360798765744_783735_20951;
                    effect94Var = new Parameter("effect94Var", null, null, this);
                    addDependency(effect94Var, new Expression(effect94VarV));
                    effect94 = new EffectFunction(new EffectFunction(effect94Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "Demo_Scenario", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_1_edc0357_1360798765742_582142_20938Collections() {
                parameters.add(_17_0_2_1_edc0357_1360798765739_210124_20933_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect94Var = new TreeSet<Effect>();
                effectsForeffect94Var.add(effect94);
                addEffects((Parameter<?>) effect94Var, effectsForeffect94Var);
            }

            public void init_17_0_2_1_edc0357_1360798765742_582142_20938Dependencies() {
                addDependency(_17_0_2_1_edc0357_1360798765739_210124_20933_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765737_27623_20930_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765737_27623_20930_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(objectToPass, new Expression<Boolean>(new EffectFunction(sig_17_0_2_1_edc0357_1360798765746_317210_20963, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "Demo_Scenario", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_1_edc0357_1360798765742_582142_20938Elaborations() {
                init_17_0_2_1_edc0357_1360798765742_582142_20938Dependencies();
                Expression<?>[] arguments95 = new Expression<?>[2];
                arguments95[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                arguments95[1] = new Expression<ObjectFlow<Boolean>>(sig_17_0_2_1_edc0357_1360798765744_783735_20951);
                Expression<Boolean> condition95 = new Expression<Boolean>(_17_0_2_1_edc0357_1360798765739_210124_20933_exists);
                elaborationRule95 = addElaborationRule(condition95, _17_0_2_1_edc0357_1360798765037_222001_20270.this, Customer._17_0_2_1_edc0357_1360798765037_222001_20270._17_0_2_1_edc0357_1360798765739_210124_20933.class, "C_change_m_MergeNode_CustomerCB", arguments95);
                Expression<?>[] arguments96 = new Expression<?>[2];
                arguments96[0] = new Expression<Integer>(startTime);
                arguments96[1] = new Expression<DurativeEvent>(this);
                Expression<Boolean> condition96 = new Expression<Boolean>(true);
                elaborationRule96 = addElaborationRule(condition96, Customer.this, Customer._17_0_2_1_edc0357_1360798765038_229449_20271.class, "usePowerWIthCap_Activity_Customer", arguments96);
            }
        }

        public class _17_0_2_1_edc0357_1360879730962_992833_16000 extends DurativeEvent {

            public _17_0_2_1_edc0357_1360879730962_992833_16000() {
                super();
                init_17_0_2_1_edc0357_1360879730962_992833_16000Members();
                init_17_0_2_1_edc0357_1360879730962_992833_16000Collections();
                init_17_0_2_1_edc0357_1360879730962_992833_16000Elaborations();
            }

            public _17_0_2_1_edc0357_1360879730962_992833_16000(Expression<Integer> startTime) {
                super();
                init_17_0_2_1_edc0357_1360879730962_992833_16000Members();
                init_17_0_2_1_edc0357_1360879730962_992833_16000Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_1_edc0357_1360879730962_992833_16000Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_1_edc0357_1360879730963_893331_16001_exists = null;

            public BooleanParameter objectToPass = null;

            public DoubleParameter _17_0_2_1_edc0357_1360879730964_303007_16002 = null;

            public BooleanParameter _17_0_2_1_edc0357_1360798765736_958603_20927_exists = null;

            public Dependency< Boolean > _17_0_2_1_edc0357_1360879730963_893331_16001_existsDependency = null;

            public Dependency< Double > _17_0_2_1_edc0357_1360879730964_303007_16002Dependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Dependency< Boolean > _17_0_2_1_edc0357_1360798765736_958603_20927_existsDependency = null;

            public Effect effect97 = null;

            public Parameter effect97Var = null;

            public Effect effect98 = null;

            public Parameter effect98Var = null;

            public ElaborationRule elaborationRule99 = null;

            public ElaborationRule elaborationRule100 = null;

            public void init_17_0_2_1_edc0357_1360879730962_992833_16000Members() {
                try {
                    if (_17_0_2_1_edc0357_1360879730963_893331_16001_exists == null) _17_0_2_1_edc0357_1360879730963_893331_16001_exists = new BooleanParameter("_17_0_2_1_edc0357_1360879730963_893331_16001_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (_17_0_2_1_edc0357_1360879730964_303007_16002 == null) _17_0_2_1_edc0357_1360879730964_303007_16002 = new DoubleParameter("_17_0_2_1_edc0357_1360879730964_303007_16002", (Double) null, this);
                    if (_17_0_2_1_edc0357_1360798765736_958603_20927_exists == null) _17_0_2_1_edc0357_1360798765736_958603_20927_exists = new BooleanParameter("_17_0_2_1_edc0357_1360798765736_958603_20927_exists", (Boolean) false, this);
                    Object effect97VarV = sig_17_0_2_1_edc0357_1360879730965_77797_16004;
                    effect97Var = new Parameter("effect97Var", null, null, this);
                    addDependency(effect97Var, new Expression(effect97VarV));
                    effect97 = new EffectFunction(new EffectFunction(effect97Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Float>", "Demo_Scenario", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { _17_0_2_1_edc0357_1360879730964_303007_16002, endTime }));
                    Object effect98VarV = sig_17_0_2_1_edc0357_1360879806465_663546_16039;
                    effect98Var = new Parameter("effect98Var", null, null, this);
                    addDependency(effect98Var, new Expression(effect98VarV));
                    effect98 = new EffectFunction(new EffectFunction(effect98Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "Demo_Scenario", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_1_edc0357_1360879730962_992833_16000Collections() {
                parameters.add(_17_0_2_1_edc0357_1360879730963_893331_16001_exists);
                parameters.add(objectToPass);
                parameters.add(_17_0_2_1_edc0357_1360879730964_303007_16002);
                parameters.add(_17_0_2_1_edc0357_1360798765736_958603_20927_exists);
                Set<Effect> effectsForeffect97Var = new TreeSet<Effect>();
                effectsForeffect97Var.add(effect97);
                addEffects((Parameter<?>) effect97Var, effectsForeffect97Var);
                Set<Effect> effectsForeffect98Var = new TreeSet<Effect>();
                effectsForeffect98Var.add(effect98);
                addEffects((Parameter<?>) effect98Var, effectsForeffect98Var);
            }

            public void init_17_0_2_1_edc0357_1360879730962_992833_16000Dependencies() {
                addDependency(_17_0_2_1_edc0357_1360879730963_893331_16001_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765737_27623_20930_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765737_27623_20930_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(_17_0_2_1_edc0357_1360879730964_303007_16002, new Expression<Double>(new EffectFunction(customer_actual_load__17_0_2_1_edc0357_1360798765039_852829_20272, ClassUtils.getMethodForArgTypes("TimeVaryingPlottableMap<Float>", "Demo_Scenario", "getValue", java.lang.Integer.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new EffectFunction(sig_17_0_2_1_edc0357_1360879763189_916669_16029, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "Demo_Scenario", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_1_edc0357_1360798765736_958603_20927_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765737_27623_20930_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765737_27623_20930_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
            }

            public void init_17_0_2_1_edc0357_1360879730962_992833_16000Elaborations() {
                init_17_0_2_1_edc0357_1360879730962_992833_16000Dependencies();
                Expression<?>[] arguments99 = new Expression<?>[1];
                arguments99[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition99 = new Expression<Boolean>(_17_0_2_1_edc0357_1360879730963_893331_16001_exists);
                elaborationRule99 = addElaborationRule(condition99, _17_0_2_1_edc0357_1360798765037_222001_20270.this, Customer._17_0_2_1_edc0357_1360798765037_222001_20270._17_0_2_1_edc0357_1360879730963_893331_16001.class, "CUse_sendmeter_SendSignalAction_CustomerCB", arguments99);
                Expression<?>[] arguments100 = new Expression<?>[1];
                arguments100[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition100 = new Expression<Boolean>(_17_0_2_1_edc0357_1360798765736_958603_20927_exists);
                elaborationRule100 = addElaborationRule(condition100, _17_0_2_1_edc0357_1360798765037_222001_20270.this, Customer._17_0_2_1_edc0357_1360798765037_222001_20270._17_0_2_1_edc0357_1360798765736_958603_20927.class, "C_read_timer_AcceptEventAction_CustomerCB", arguments100);
            }
        }

        public class _17_0_2_1_edc0357_1360879730963_893331_16001 extends DurativeEvent {

            public _17_0_2_1_edc0357_1360879730963_893331_16001() {
                super();
                init_17_0_2_1_edc0357_1360879730963_893331_16001Members();
                init_17_0_2_1_edc0357_1360879730963_893331_16001Collections();
                init_17_0_2_1_edc0357_1360879730963_893331_16001Elaborations();
            }

            public _17_0_2_1_edc0357_1360879730963_893331_16001(Expression<Integer> startTime) {
                super();
                init_17_0_2_1_edc0357_1360879730963_893331_16001Members();
                init_17_0_2_1_edc0357_1360879730963_893331_16001Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_1_edc0357_1360879730963_893331_16001Elaborations();
                fixTimeDependencies();
            }

            public DoubleParameter _17_0_2_1_edc0357_1360879730964_88024_16003 = null;

            public Parameter< Power_System.SignalreceiveMeterReading > signalObject = null;

            public Dependency< Double > _17_0_2_1_edc0357_1360879730964_88024_16003Dependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Power_System.SignalreceiveMeterReading > signalObjectDependency = null;

            public Effect effect101 = null;

            public Parameter effect101Var = null;

            public Effect effect102 = null;

            public Parameter effect102Var = null;

            public void init_17_0_2_1_edc0357_1360879730963_893331_16001Members() {
                try {
                    if (_17_0_2_1_edc0357_1360879730964_88024_16003 == null) _17_0_2_1_edc0357_1360879730964_88024_16003 = new DoubleParameter("_17_0_2_1_edc0357_1360879730964_88024_16003", (Double) null, this);
                    if (signalObject == null) signalObject = new Parameter<Power_System.SignalreceiveMeterReading>("signalObject", null, (Power_System.SignalreceiveMeterReading) null, this);
                    Object effect101VarV = new FunctionCall(x, Parameter.class, "getMember", new Object[] { "ss_17_0_2_1_edc0357_1360798765042_106286_20276_receiveMeterReading" });
                    effect101Var = new Parameter("effect101Var", null, null, this);
                    addDependency(effect101Var, new Expression(effect101VarV));
                    effect101 = new EffectFunction(new EffectFunction(null, ClassUtils.getMethodForArgTypes("ObjectFlow<SignalreceiveMeterReading>", "Demo_Scenario", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { signalObject, endTime }, effect101Var));
                    Object effect102VarV = new FunctionCall(signalObject, Parameter.class, "getMember", new Object[] { "meter_value__17_0_2_1_edc0357_1360798765095_360811_20350" });
                    effect102Var = new Parameter("effect102Var", null, null, this);
                    addDependency(effect102Var, new Expression(effect102VarV));
                    effect102 = new EffectFunction(new EffectFunction(null, ClassUtils.getMethodForArgTypes("TimeVaryingMap<Float>", "Demo_Scenario", "setValue", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, _17_0_2_1_edc0357_1360879730964_88024_16003 }, effect102Var));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_1_edc0357_1360879730963_893331_16001Collections() {
                parameters.add(_17_0_2_1_edc0357_1360879730964_88024_16003);
                parameters.add(signalObject);
                Set<Effect> effectsForeffect101Var = new TreeSet<Effect>();
                effectsForeffect101Var.add(effect101);
                addEffects((Parameter<?>) effect101Var, effectsForeffect101Var);
                Set<Effect> effectsForeffect102Var = new TreeSet<Effect>();
                effectsForeffect102Var.add(effect102);
                addEffects((Parameter<?>) effect102Var, effectsForeffect102Var);
            }

            public void init_17_0_2_1_edc0357_1360879730963_893331_16001Dependencies() {
                addDependency(_17_0_2_1_edc0357_1360879730964_88024_16003, new Expression<Double>(new EffectFunction(sig_17_0_2_1_edc0357_1360879730965_77797_16004, ClassUtils.getMethodForArgTypes("ObjectFlow<Float>", "Demo_Scenario", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(signalObject, new Expression<Power_System.SignalreceiveMeterReading>(new ConstructorCall(x, ClassUtils.getConstructorForArgTypes(Power_System.SignalreceiveMeterReading.class), new Object[] {})));
            }

            public void init_17_0_2_1_edc0357_1360879730963_893331_16001Elaborations() {
                init_17_0_2_1_edc0357_1360879730963_893331_16001Dependencies();
            }
        }

        public _17_0_2_1_edc0357_1360798765037_222001_20270(Expression<Integer> startTime) {
            super();
            init_17_0_2_1_edc0357_1360798765037_222001_20270Members();
            init_17_0_2_1_edc0357_1360798765037_222001_20270Collections();
            addDependency(this.startTime, startTime);
            init_17_0_2_1_edc0357_1360798765037_222001_20270Elaborations();
            fixTimeDependencies();
        }

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_1_edc0357_1360798765746_851404_20960 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_1_edc0357_1360798765744_788738_20950 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_1_edc0357_1360798765744_378657_20947 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_1_edc0357_1360798765746_550928_20961 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_1_edc0357_1360798765745_929532_20956 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_1_edc0357_1360798765744_783735_20951 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_1_edc0357_1360798765746_317210_20963 = null;

        public Parameter< ObjectFlow<Power_System.Signaldr_request> > sig_17_0_2_1_edc0357_1360798764895_373133_20122 = null;

        public IntegerParameter finalNode_endTime = null;

        public Parameter< ObjectFlow<Float> > sig_17_0_2_1_edc0357_1360798765745_439888_20954 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_1_edc0357_1360798765745_954000_20952 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_1_edc0357_1360879763189_916669_16029 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_1_edc0357_1360798765744_973847_20946 = null;

        public Parameter< ObjectFlow<Power_System.Signaldr_request> > sig_17_0_2_1_edc0357_1360798765745_692296_20953 = null;

        public IntegerParameter finalNode_startTime = null;

        public BooleanParameter _17_0_2_1_edc0357_1360798765737_27623_20930_exists = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_1_edc0357_1360879806465_663546_16039 = null;

        public Parameter< ObjectFlow<Float> > sig_17_0_2_1_edc0357_1360879730965_77797_16004 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_1_edc0357_1360798765746_684537_20962 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_1_edc0357_1360798765745_845538_20955 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_1_edc0357_1360798765745_72306_20957 = null;

        public Dependency< Integer > endTimeDependency = null;

        public Dependency< Boolean > _17_0_2_1_edc0357_1360798765737_27623_20930_existsDependency = null;

        public ElaborationRule elaborationRule64 = null;

        public ElaborationRule elaborationRule65 = null;

        public void init_17_0_2_1_edc0357_1360798765037_222001_20270Members() {
            try {
                if (sig_17_0_2_1_edc0357_1360798765746_851404_20960 == null) sig_17_0_2_1_edc0357_1360798765746_851404_20960 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_1_edc0357_1360798765746_851404_20960", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360798765746_851404_20960" })).evaluate(true), this);
                if (sig_17_0_2_1_edc0357_1360798765744_788738_20950 == null) sig_17_0_2_1_edc0357_1360798765744_788738_20950 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_1_edc0357_1360798765744_788738_20950", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360798765744_788738_20950" })).evaluate(true), this);
                if (sig_17_0_2_1_edc0357_1360798765744_378657_20947 == null) sig_17_0_2_1_edc0357_1360798765744_378657_20947 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_1_edc0357_1360798765744_378657_20947", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360798765744_378657_20947" })).evaluate(true), this);
                if (sig_17_0_2_1_edc0357_1360798765746_550928_20961 == null) sig_17_0_2_1_edc0357_1360798765746_550928_20961 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_1_edc0357_1360798765746_550928_20961", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360798765746_550928_20961" })).evaluate(true), this);
                if (sig_17_0_2_1_edc0357_1360798765745_929532_20956 == null) sig_17_0_2_1_edc0357_1360798765745_929532_20956 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_1_edc0357_1360798765745_929532_20956", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360798765745_929532_20956" })).evaluate(true), this);
                if (sig_17_0_2_1_edc0357_1360798765744_783735_20951 == null) sig_17_0_2_1_edc0357_1360798765744_783735_20951 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_1_edc0357_1360798765744_783735_20951", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360798765744_783735_20951" })).evaluate(true), this);
                if (sig_17_0_2_1_edc0357_1360798765746_317210_20963 == null) sig_17_0_2_1_edc0357_1360798765746_317210_20963 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_1_edc0357_1360798765746_317210_20963", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360798765746_317210_20963" })).evaluate(true), this);
                if (sig_17_0_2_1_edc0357_1360798764895_373133_20122 == null) sig_17_0_2_1_edc0357_1360798764895_373133_20122 = new Parameter<ObjectFlow<Power_System.Signaldr_request>>("sig_17_0_2_1_edc0357_1360798764895_373133_20122", null, (ObjectFlow<Power_System.Signaldr_request>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360798764895_373133_20122" })).evaluate(true), this);
                if (finalNode_endTime == null) finalNode_endTime = new IntegerParameter("finalNode_endTime", (Integer) null, this);
                if (sig_17_0_2_1_edc0357_1360798765745_439888_20954 == null) sig_17_0_2_1_edc0357_1360798765745_439888_20954 = new Parameter<ObjectFlow<Float>>("sig_17_0_2_1_edc0357_1360798765745_439888_20954", null, (ObjectFlow<Float>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360798765745_439888_20954" })).evaluate(true), this);
                if (sig_17_0_2_1_edc0357_1360798765745_954000_20952 == null) sig_17_0_2_1_edc0357_1360798765745_954000_20952 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_1_edc0357_1360798765745_954000_20952", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360798765745_954000_20952" })).evaluate(true), this);
                if (sig_17_0_2_1_edc0357_1360879763189_916669_16029 == null) sig_17_0_2_1_edc0357_1360879763189_916669_16029 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_1_edc0357_1360879763189_916669_16029", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360879763189_916669_16029" })).evaluate(true), this);
                if (sig_17_0_2_1_edc0357_1360798765744_973847_20946 == null) sig_17_0_2_1_edc0357_1360798765744_973847_20946 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_1_edc0357_1360798765744_973847_20946", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360798765744_973847_20946" })).evaluate(true), this);
                if (sig_17_0_2_1_edc0357_1360798765745_692296_20953 == null) sig_17_0_2_1_edc0357_1360798765745_692296_20953 = new Parameter<ObjectFlow<Power_System.Signaldr_request>>("sig_17_0_2_1_edc0357_1360798765745_692296_20953", null, (ObjectFlow<Power_System.Signaldr_request>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360798765745_692296_20953" })).evaluate(true), this);
                if (finalNode_startTime == null) finalNode_startTime = new IntegerParameter("finalNode_startTime", (Integer) null, this);
                if (_17_0_2_1_edc0357_1360798765737_27623_20930_exists == null) _17_0_2_1_edc0357_1360798765737_27623_20930_exists = new BooleanParameter("_17_0_2_1_edc0357_1360798765737_27623_20930_exists", (Boolean) false, this);
                if (sig_17_0_2_1_edc0357_1360879806465_663546_16039 == null) sig_17_0_2_1_edc0357_1360879806465_663546_16039 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_1_edc0357_1360879806465_663546_16039", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360879806465_663546_16039" })).evaluate(true), this);
                if (sig_17_0_2_1_edc0357_1360879730965_77797_16004 == null) sig_17_0_2_1_edc0357_1360879730965_77797_16004 = new Parameter<ObjectFlow<Float>>("sig_17_0_2_1_edc0357_1360879730965_77797_16004", null, (ObjectFlow<Float>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360879730965_77797_16004" })).evaluate(true), this);
                if (sig_17_0_2_1_edc0357_1360798765746_684537_20962 == null) sig_17_0_2_1_edc0357_1360798765746_684537_20962 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_1_edc0357_1360798765746_684537_20962", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360798765746_684537_20962" })).evaluate(true), this);
                if (sig_17_0_2_1_edc0357_1360798765745_845538_20955 == null) sig_17_0_2_1_edc0357_1360798765745_845538_20955 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_1_edc0357_1360798765745_845538_20955", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360798765745_845538_20955" })).evaluate(true), this);
                if (sig_17_0_2_1_edc0357_1360798765745_72306_20957 == null) sig_17_0_2_1_edc0357_1360798765745_72306_20957 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_1_edc0357_1360798765745_72306_20957", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360798765745_72306_20957" })).evaluate(true), this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void init_17_0_2_1_edc0357_1360798765037_222001_20270Collections() {
            parameters.add(sig_17_0_2_1_edc0357_1360798765746_851404_20960);
            parameters.add(sig_17_0_2_1_edc0357_1360798765744_788738_20950);
            parameters.add(sig_17_0_2_1_edc0357_1360798765744_378657_20947);
            parameters.add(sig_17_0_2_1_edc0357_1360798765746_550928_20961);
            parameters.add(sig_17_0_2_1_edc0357_1360798765745_929532_20956);
            parameters.add(sig_17_0_2_1_edc0357_1360798765744_783735_20951);
            parameters.add(sig_17_0_2_1_edc0357_1360798765746_317210_20963);
            parameters.add(sig_17_0_2_1_edc0357_1360798764895_373133_20122);
            parameters.add(finalNode_endTime);
            parameters.add(sig_17_0_2_1_edc0357_1360798765745_439888_20954);
            parameters.add(sig_17_0_2_1_edc0357_1360798765745_954000_20952);
            parameters.add(sig_17_0_2_1_edc0357_1360879763189_916669_16029);
            parameters.add(sig_17_0_2_1_edc0357_1360798765744_973847_20946);
            parameters.add(sig_17_0_2_1_edc0357_1360798765745_692296_20953);
            parameters.add(finalNode_startTime);
            parameters.add(_17_0_2_1_edc0357_1360798765737_27623_20930_exists);
            parameters.add(sig_17_0_2_1_edc0357_1360879806465_663546_16039);
            parameters.add(sig_17_0_2_1_edc0357_1360879730965_77797_16004);
            parameters.add(sig_17_0_2_1_edc0357_1360798765746_684537_20962);
            parameters.add(sig_17_0_2_1_edc0357_1360798765745_845538_20955);
            parameters.add(sig_17_0_2_1_edc0357_1360798765745_72306_20957);
        }

        public void init_17_0_2_1_edc0357_1360798765037_222001_20270Dependencies() {
            addDependency(endTime, new Expression<Integer>(finalNode_endTime));
            addDependency(_17_0_2_1_edc0357_1360798765737_27623_20930_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.NotEquals(new Expression<Integer>(finalNode_startTime), null)), new Expression(new EffectFunction(sig_17_0_2_1_edc0357_1360798765746_550928_20961, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "Demo_Scenario", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(new Functions.Minus(new Expression<Integer>(finalNode_startTime), new Expression<Integer>(1))) })))));
        }

        public void init_17_0_2_1_edc0357_1360798765037_222001_20270Elaborations() {
            init_17_0_2_1_edc0357_1360798765037_222001_20270Dependencies();
            Expression<?>[] arguments64 = new Expression<?>[1];
            arguments64[0] = new Expression<Integer>(finalNode_startTime);
            Expression<Boolean> condition64 = new Expression<Boolean>(_17_0_2_1_edc0357_1360798765737_27623_20930_exists);
            elaborationRule64 = addElaborationRule(condition64, _17_0_2_1_edc0357_1360798765037_222001_20270.this, Customer._17_0_2_1_edc0357_1360798765037_222001_20270._17_0_2_1_edc0357_1360798765737_27623_20930.class, "C_end_ActivityFinalNode_CustomerCB", arguments64);
            Expression<?>[] arguments65 = new Expression<?>[1];
            arguments65[0] = new Expression<Integer>(startTime);
            Expression<Boolean> condition65 = new Expression<Boolean>(true);
            elaborationRule65 = addElaborationRule(condition65, _17_0_2_1_edc0357_1360798765037_222001_20270.this, Customer._17_0_2_1_edc0357_1360798765037_222001_20270._17_0_2_1_edc0357_1360798765733_536254_20923.class, "C_start_InitialNode_CustomerCB", arguments65);
        }
    }

    public class _17_0_2_1_edc0357_1360798765038_229449_20271 extends DurativeEvent {

        public _17_0_2_1_edc0357_1360798765038_229449_20271() {
            super();
            init_17_0_2_1_edc0357_1360798765038_229449_20271Members();
            init_17_0_2_1_edc0357_1360798765038_229449_20271Collections();
            init_17_0_2_1_edc0357_1360798765038_229449_20271Elaborations();
        }

        public class _17_0_2_1_edc0357_1360798765764_673556_20988 extends DurativeEvent {

            public _17_0_2_1_edc0357_1360798765764_673556_20988() {
                super();
                init_17_0_2_1_edc0357_1360798765764_673556_20988Members();
                init_17_0_2_1_edc0357_1360798765764_673556_20988Collections();
                init_17_0_2_1_edc0357_1360798765764_673556_20988Elaborations();
            }

            public _17_0_2_1_edc0357_1360798765764_673556_20988(Expression<Integer> startTime) {
                super();
                init_17_0_2_1_edc0357_1360798765764_673556_20988Members();
                init_17_0_2_1_edc0357_1360798765764_673556_20988Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_1_edc0357_1360798765764_673556_20988Elaborations();
                fixTimeDependencies();
            }

            public IntegerParameter myDeciderID_decider_17_0_2_1_edc0357_1360798765768_429359_20994 = null;

            public BooleanParameter addToDecider__17_0_2_1_edc0357_1360798765768_429359_20994 = null;

            public DoubleParameter _17_0_2_1_edc0357_1360798766448_924927_22109 = null;

            public BooleanParameter objectToPass = null;

            public BooleanParameter _17_0_2_1_edc0357_1360798765768_429359_20994_exists = null;

            public Dependency< Integer > myDeciderID_decider_17_0_2_1_edc0357_1360798765768_429359_20994Dependency = null;

            public Dependency< Boolean > addToDecider__17_0_2_1_edc0357_1360798765768_429359_20994Dependency = null;

            public Dependency< Double > _17_0_2_1_edc0357_1360798766448_924927_22109Dependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Dependency< Boolean > _17_0_2_1_edc0357_1360798765768_429359_20994_existsDependency = null;

            public Effect effect105 = null;

            public Parameter effect105Var = null;

            public Effect effect106 = null;

            public Parameter effect106Var = null;

            public Effect effect107 = null;

            public Parameter effect107Var = null;

            public ElaborationRule elaborationRule108 = null;

            public void init_17_0_2_1_edc0357_1360798765764_673556_20988Members() {
                try {
                    if (myDeciderID_decider_17_0_2_1_edc0357_1360798765768_429359_20994 == null) myDeciderID_decider_17_0_2_1_edc0357_1360798765768_429359_20994 = new IntegerParameter("myDeciderID_decider_17_0_2_1_edc0357_1360798765768_429359_20994", (Integer) 2, this);
                    if (addToDecider__17_0_2_1_edc0357_1360798765768_429359_20994 == null) addToDecider__17_0_2_1_edc0357_1360798765768_429359_20994 = new BooleanParameter("addToDecider__17_0_2_1_edc0357_1360798765768_429359_20994", (Boolean) false, this);
                    if (_17_0_2_1_edc0357_1360798766448_924927_22109 == null) _17_0_2_1_edc0357_1360798766448_924927_22109 = new DoubleParameter("_17_0_2_1_edc0357_1360798766448_924927_22109", (Double) null, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (_17_0_2_1_edc0357_1360798765768_429359_20994_exists == null) _17_0_2_1_edc0357_1360798765768_429359_20994_exists = new BooleanParameter("_17_0_2_1_edc0357_1360798765768_429359_20994_exists", (Boolean) false, this);
                    Object effect105VarV = sig_17_0_2_1_edc0357_1360798765774_590820_21009;
                    effect105Var = new Parameter("effect105Var", null, null, this);
                    addDependency(effect105Var, new Expression(effect105VarV));
                    effect105 = new EffectFunction(new EffectFunction(effect105Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "Demo_Scenario", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect106VarV = decider_17_0_2_1_edc0357_1360798765768_429359_20994;
                    effect106Var = new Parameter("effect106Var", null, null, this);
                    addDependency(effect106Var, new Expression(effect106VarV));
                    effect106 = new EffectFunction(new EffectFunction(effect106Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "Demo_Scenario", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class, java.lang.Boolean.class), new Object[] { endTime, myDeciderID_decider_17_0_2_1_edc0357_1360798765768_429359_20994, addToDecider__17_0_2_1_edc0357_1360798765768_429359_20994 }));
                    Object effect107VarV = customer_actual_load__17_0_2_1_edc0357_1360798765039_852829_20272;
                    effect107Var = new Parameter("effect107Var", null, null, this);
                    addDependency(effect107Var, new Expression(effect107VarV));
                    effect107 = new EffectFunction(new EffectFunction(effect107Var, ClassUtils.getMethodForArgTypes("TimeVaryingPlottableMap<Float>", "Demo_Scenario", "setValue", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { startTime, _17_0_2_1_edc0357_1360798766448_924927_22109 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_1_edc0357_1360798765764_673556_20988Collections() {
                parameters.add(myDeciderID_decider_17_0_2_1_edc0357_1360798765768_429359_20994);
                parameters.add(addToDecider__17_0_2_1_edc0357_1360798765768_429359_20994);
                parameters.add(_17_0_2_1_edc0357_1360798766448_924927_22109);
                parameters.add(objectToPass);
                parameters.add(_17_0_2_1_edc0357_1360798765768_429359_20994_exists);
                Set<Effect> effectsForeffect105Var = new TreeSet<Effect>();
                effectsForeffect105Var.add(effect105);
                addEffects((Parameter<?>) effect105Var, effectsForeffect105Var);
                Set<Effect> effectsForeffect106Var = new TreeSet<Effect>();
                effectsForeffect106Var.add(effect106);
                addEffects((Parameter<?>) effect106Var, effectsForeffect106Var);
                Set<Effect> effectsForeffect107Var = new TreeSet<Effect>();
                effectsForeffect107Var.add(effect107);
                addEffects((Parameter<?>) effect107Var, effectsForeffect107Var);
            }

            public void init_17_0_2_1_edc0357_1360798765764_673556_20988Dependencies() {
                addDependency(myDeciderID_decider_17_0_2_1_edc0357_1360798765768_429359_20994, new Expression<Integer>(2));
                addDependency(addToDecider__17_0_2_1_edc0357_1360798765768_429359_20994, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765765_561605_20990_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765765_561605_20990_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(_17_0_2_1_edc0357_1360798766448_924927_22109, new Expression<Double>(new EffectFunction(sig_17_0_2_1_edc0357_1360798765774_359281_21007, ClassUtils.getMethodForArgTypes("ObjectFlow<Float>", "Demo_Scenario", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(true));
                addDependency(_17_0_2_1_edc0357_1360798765768_429359_20994_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765765_561605_20990_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765765_561605_20990_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_1_edc0357_1360798765768_429359_20994, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "Demo_Scenario", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_1_edc0357_1360798765768_429359_20994, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "Demo_Scenario", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_1_edc0357_1360798765768_429359_20994, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "Demo_Scenario", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_1_edc0357_1360798765768_429359_20994)))))))));
            }

            public void init_17_0_2_1_edc0357_1360798765764_673556_20988Elaborations() {
                init_17_0_2_1_edc0357_1360798765764_673556_20988Dependencies();
                Expression<?>[] arguments108 = new Expression<?>[1];
                arguments108[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition108 = new Expression<Boolean>(_17_0_2_1_edc0357_1360798765768_429359_20994_exists);
                elaborationRule108 = addElaborationRule(condition108, _17_0_2_1_edc0357_1360798765038_229449_20271.this, Customer._17_0_2_1_edc0357_1360798765038_229449_20271._17_0_2_1_edc0357_1360798765768_429359_20994.class, "CChange_sendnewusage_SendSignalAction_usePowerWIthCap", arguments108);
            }
        }

        public class _17_0_2_1_edc0357_1360798765765_263316_20989 extends DurativeEvent {

            public _17_0_2_1_edc0357_1360798765765_263316_20989() {
                super();
                init_17_0_2_1_edc0357_1360798765765_263316_20989Members();
                init_17_0_2_1_edc0357_1360798765765_263316_20989Collections();
                init_17_0_2_1_edc0357_1360798765765_263316_20989Elaborations();
            }

            public _17_0_2_1_edc0357_1360798765765_263316_20989(Expression<Integer> startTime) {
                super();
                init_17_0_2_1_edc0357_1360798765765_263316_20989Members();
                init_17_0_2_1_edc0357_1360798765765_263316_20989Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_1_edc0357_1360798765765_263316_20989Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_1_edc0357_1360798765766_161011_20991_exists = null;

            public BooleanParameter objectToPass = null;

            public Dependency< Boolean > _17_0_2_1_edc0357_1360798765766_161011_20991_existsDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Effect effect109 = null;

            public Parameter effect109Var = null;

            public ElaborationRule elaborationRule110 = null;

            public void init_17_0_2_1_edc0357_1360798765765_263316_20989Members() {
                try {
                    if (_17_0_2_1_edc0357_1360798765766_161011_20991_exists == null) _17_0_2_1_edc0357_1360798765766_161011_20991_exists = new BooleanParameter("_17_0_2_1_edc0357_1360798765766_161011_20991_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect109VarV = sig_17_0_2_1_edc0357_1360880681317_965549_16069;
                    effect109Var = new Parameter("effect109Var", null, null, this);
                    addDependency(effect109Var, new Expression(effect109VarV));
                    effect109 = new EffectFunction(new EffectFunction(effect109Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "Demo_Scenario", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_1_edc0357_1360798765765_263316_20989Collections() {
                parameters.add(_17_0_2_1_edc0357_1360798765766_161011_20991_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect109Var = new TreeSet<Effect>();
                effectsForeffect109Var.add(effect109);
                addEffects((Parameter<?>) effect109Var, effectsForeffect109Var);
            }

            public void init_17_0_2_1_edc0357_1360798765765_263316_20989Dependencies() {
                addDependency(_17_0_2_1_edc0357_1360798765766_161011_20991_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765765_561605_20990_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765765_561605_20990_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(true));
            }

            public void init_17_0_2_1_edc0357_1360798765765_263316_20989Elaborations() {
                init_17_0_2_1_edc0357_1360798765765_263316_20989Dependencies();
                Expression<?>[] arguments110 = new Expression<?>[1];
                arguments110[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition110 = new Expression<Boolean>(_17_0_2_1_edc0357_1360798765766_161011_20991_exists);
                elaborationRule110 = addElaborationRule(condition110, _17_0_2_1_edc0357_1360798765038_229449_20271.this, Customer._17_0_2_1_edc0357_1360798765038_229449_20271._17_0_2_1_edc0357_1360798765766_161011_20991.class, "CChange_readcurrentcap_ReadStructuralFeatureAction_usePowerWIthCap", arguments110);
            }
        }

        public class _17_0_2_1_edc0357_1360798765765_561605_20990 extends DurativeEvent {

            public _17_0_2_1_edc0357_1360798765765_561605_20990() {
                super();
                init_17_0_2_1_edc0357_1360798765765_561605_20990Members();
                init_17_0_2_1_edc0357_1360798765765_561605_20990Collections();
                init_17_0_2_1_edc0357_1360798765765_561605_20990Elaborations();
            }

            public _17_0_2_1_edc0357_1360798765765_561605_20990(Expression<Integer> startTime) {
                super();
                init_17_0_2_1_edc0357_1360798765765_561605_20990Members();
                init_17_0_2_1_edc0357_1360798765765_561605_20990Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_1_edc0357_1360798765765_561605_20990Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter objectToPass = null;

            public Dependency< Integer > endTimeDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Dependency< Integer > finalNode_endTimeDependency = null;

            public void init_17_0_2_1_edc0357_1360798765765_561605_20990Members() {
                try {
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_1_edc0357_1360798765765_561605_20990Collections() {
                parameters.add(objectToPass);
            }

            public void init_17_0_2_1_edc0357_1360798765765_561605_20990Dependencies() {
                addDependency(endTime, new Expression<Integer>(new Functions.Plus(new Expression<Integer>(startTime), new Expression<Integer>(duration))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new EffectFunction(sig_17_0_2_1_edc0357_1360798765774_725978_21006, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "Demo_Scenario", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(finalNode_endTime, new Expression<Integer>(endTime));
            }

            public void init_17_0_2_1_edc0357_1360798765765_561605_20990Elaborations() {
                init_17_0_2_1_edc0357_1360798765765_561605_20990Dependencies();
            }
        }

        public class _17_0_2_1_edc0357_1360798765766_161011_20991 extends DurativeEvent {

            public _17_0_2_1_edc0357_1360798765766_161011_20991() {
                super();
                init_17_0_2_1_edc0357_1360798765766_161011_20991Members();
                init_17_0_2_1_edc0357_1360798765766_161011_20991Collections();
                init_17_0_2_1_edc0357_1360798765766_161011_20991Elaborations();
            }

            public _17_0_2_1_edc0357_1360798765766_161011_20991(Expression<Integer> startTime) {
                super();
                init_17_0_2_1_edc0357_1360798765766_161011_20991Members();
                init_17_0_2_1_edc0357_1360798765766_161011_20991Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_1_edc0357_1360798765766_161011_20991Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_1_edc0357_1360798765773_288507_21001_exists = null;

            public DoubleParameter _17_0_2_1_edc0357_1360798766449_745073_22111 = null;

            public BooleanParameter _17_0_2_1_edc0357_1360798765770_511877_20997_exists = null;

            public BooleanParameter objectToPass = null;

            public Dependency< Boolean > _17_0_2_1_edc0357_1360798765773_288507_21001_existsDependency = null;

            public Dependency< Double > _17_0_2_1_edc0357_1360798766449_745073_22111Dependency = null;

            public Dependency< Boolean > _17_0_2_1_edc0357_1360798765770_511877_20997_existsDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Effect effect111 = null;

            public Parameter effect111Var = null;

            public Effect effect112 = null;

            public Parameter effect112Var = null;

            public ElaborationRule elaborationRule113 = null;

            public ElaborationRule elaborationRule114 = null;

            public void init_17_0_2_1_edc0357_1360798765766_161011_20991Members() {
                try {
                    if (_17_0_2_1_edc0357_1360798765773_288507_21001_exists == null) _17_0_2_1_edc0357_1360798765773_288507_21001_exists = new BooleanParameter("_17_0_2_1_edc0357_1360798765773_288507_21001_exists", (Boolean) false, this);
                    if (_17_0_2_1_edc0357_1360798766449_745073_22111 == null) _17_0_2_1_edc0357_1360798766449_745073_22111 = new DoubleParameter("_17_0_2_1_edc0357_1360798766449_745073_22111", (Double) null, this);
                    if (_17_0_2_1_edc0357_1360798765770_511877_20997_exists == null) _17_0_2_1_edc0357_1360798765770_511877_20997_exists = new BooleanParameter("_17_0_2_1_edc0357_1360798765770_511877_20997_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect111VarV = sig_17_0_2_1_edc0357_1360798765775_476651_21012;
                    effect111Var = new Parameter("effect111Var", null, null, this);
                    addDependency(effect111Var, new Expression(effect111VarV));
                    effect111 = new EffectFunction(new EffectFunction(effect111Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Float>", "Demo_Scenario", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { _17_0_2_1_edc0357_1360798766449_745073_22111, endTime }));
                    Object effect112VarV = sig_17_0_2_1_edc0357_1360880675037_212150_16064;
                    effect112Var = new Parameter("effect112Var", null, null, this);
                    addDependency(effect112Var, new Expression(effect112VarV));
                    effect112 = new EffectFunction(new EffectFunction(effect112Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "Demo_Scenario", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_1_edc0357_1360798765766_161011_20991Collections() {
                parameters.add(_17_0_2_1_edc0357_1360798765773_288507_21001_exists);
                parameters.add(_17_0_2_1_edc0357_1360798766449_745073_22111);
                parameters.add(_17_0_2_1_edc0357_1360798765770_511877_20997_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect111Var = new TreeSet<Effect>();
                effectsForeffect111Var.add(effect111);
                addEffects((Parameter<?>) effect111Var, effectsForeffect111Var);
                Set<Effect> effectsForeffect112Var = new TreeSet<Effect>();
                effectsForeffect112Var.add(effect112);
                addEffects((Parameter<?>) effect112Var, effectsForeffect112Var);
            }

            public void init_17_0_2_1_edc0357_1360798765766_161011_20991Dependencies() {
                addDependency(_17_0_2_1_edc0357_1360798765773_288507_21001_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765765_561605_20990_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765765_561605_20990_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(_17_0_2_1_edc0357_1360798766449_745073_22111, new Expression<Double>(new EffectFunction(reduction_level__17_0_2_1_edc0357_1360798765043_828353_20277, ClassUtils.getMethodForArgTypes("TimeVaryingMap<Float>", "Demo_Scenario", "getValue", java.lang.Integer.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_1_edc0357_1360798765770_511877_20997_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765765_561605_20990_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765765_561605_20990_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new EffectFunction(sig_17_0_2_1_edc0357_1360880681317_965549_16069, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "Demo_Scenario", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_1_edc0357_1360798765766_161011_20991Elaborations() {
                init_17_0_2_1_edc0357_1360798765766_161011_20991Dependencies();
                Expression<?>[] arguments113 = new Expression<?>[1];
                arguments113[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition113 = new Expression<Boolean>(_17_0_2_1_edc0357_1360798765773_288507_21001_exists);
                elaborationRule113 = addElaborationRule(condition113, _17_0_2_1_edc0357_1360798765038_229449_20271.this, Customer._17_0_2_1_edc0357_1360798765038_229449_20271._17_0_2_1_edc0357_1360798765773_288507_21001.class, "CUse_readLoadProfile_ReadStructuralFeatureAction_usePowerWIthCap", arguments113);
                Expression<?>[] arguments114 = new Expression<?>[1];
                arguments114[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition114 = new Expression<Boolean>(_17_0_2_1_edc0357_1360798765770_511877_20997_exists);
                elaborationRule114 = addElaborationRule(condition114, _17_0_2_1_edc0357_1360798765038_229449_20271.this, Customer._17_0_2_1_edc0357_1360798765038_229449_20271._17_0_2_1_edc0357_1360798765770_511877_20997.class, "CChange_fork3cap_ForkNode_usePowerWIthCap", arguments114);
            }
        }

        public class _17_0_2_1_edc0357_1360798765767_791304_20993 extends DurativeEvent {

            public _17_0_2_1_edc0357_1360798765767_791304_20993() {
                super();
                init_17_0_2_1_edc0357_1360798765767_791304_20993Members();
                init_17_0_2_1_edc0357_1360798765767_791304_20993Collections();
                init_17_0_2_1_edc0357_1360798765767_791304_20993Elaborations();
            }

            public _17_0_2_1_edc0357_1360798765767_791304_20993(Expression<Integer> startTime) {
                super();
                init_17_0_2_1_edc0357_1360798765767_791304_20993Members();
                init_17_0_2_1_edc0357_1360798765767_791304_20993Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_1_edc0357_1360798765767_791304_20993Elaborations();
                fixTimeDependencies();
            }

            public DoubleParameter new_load = null;

            public DoubleParameter _17_0_2_1_edc0357_1360798766451_234795_22114 = null;

            public DoubleParameter cap = null;

            public DoubleParameter _17_0_2_1_edc0357_1360798766452_823242_22115 = null;

            public DoubleParameter desired = null;

            public BooleanParameter objectToPass = null;

            public DoubleParameter _17_0_2_1_edc0357_1360798766450_344902_22113 = null;

            public BooleanParameter _17_0_2_1_edc0357_1360798765772_631817_21000_exists = null;

            public Dependency< Double > new_loadDependency = null;

            public Dependency< Double > _17_0_2_1_edc0357_1360798766451_234795_22114Dependency = null;

            public Dependency< Double > capDependency = null;

            public Dependency< Double > _17_0_2_1_edc0357_1360798766452_823242_22115Dependency = null;

            public Dependency< Double > desiredDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Dependency< Double > _17_0_2_1_edc0357_1360798766450_344902_22113Dependency = null;

            public Dependency< Boolean > _17_0_2_1_edc0357_1360798765772_631817_21000_existsDependency = null;

            public Effect effect115 = null;

            public Parameter effect115Var = null;

            public ElaborationRule elaborationRule116 = null;

            public void init_17_0_2_1_edc0357_1360798765767_791304_20993Members() {
                try {
                    if (new_load == null) new_load = new DoubleParameter("new_load", (Double) null, this);
                    if (_17_0_2_1_edc0357_1360798766451_234795_22114 == null) _17_0_2_1_edc0357_1360798766451_234795_22114 = new DoubleParameter("_17_0_2_1_edc0357_1360798766451_234795_22114", (Double) null, this);
                    if (cap == null) cap = new DoubleParameter("cap", (Double) null, this);
                    if (_17_0_2_1_edc0357_1360798766452_823242_22115 == null) _17_0_2_1_edc0357_1360798766452_823242_22115 = new DoubleParameter("_17_0_2_1_edc0357_1360798766452_823242_22115", (Double) null, this);
                    if (desired == null) desired = new DoubleParameter("desired", (Double) null, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (_17_0_2_1_edc0357_1360798766450_344902_22113 == null) _17_0_2_1_edc0357_1360798766450_344902_22113 = new DoubleParameter("_17_0_2_1_edc0357_1360798766450_344902_22113", (Double) null, this);
                    if (_17_0_2_1_edc0357_1360798765772_631817_21000_exists == null) _17_0_2_1_edc0357_1360798765772_631817_21000_exists = new BooleanParameter("_17_0_2_1_edc0357_1360798765772_631817_21000_exists", (Boolean) false, this);
                    Object effect115VarV = sig_17_0_2_1_edc0357_1360798765776_307433_21021;
                    effect115Var = new Parameter("effect115Var", null, null, this);
                    addDependency(effect115Var, new Expression(effect115VarV));
                    effect115 = new EffectFunction(new EffectFunction(effect115Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Float>", "Demo_Scenario", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { _17_0_2_1_edc0357_1360798766450_344902_22113, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_1_edc0357_1360798765767_791304_20993Collections() {
                parameters.add(new_load);
                parameters.add(_17_0_2_1_edc0357_1360798766451_234795_22114);
                parameters.add(cap);
                parameters.add(_17_0_2_1_edc0357_1360798766452_823242_22115);
                parameters.add(desired);
                parameters.add(objectToPass);
                parameters.add(_17_0_2_1_edc0357_1360798766450_344902_22113);
                parameters.add(_17_0_2_1_edc0357_1360798765772_631817_21000_exists);
                Set<Effect> effectsForeffect115Var = new TreeSet<Effect>();
                effectsForeffect115Var.add(effect115);
                addEffects((Parameter<?>) effect115Var, effectsForeffect115Var);
            }

            public void init_17_0_2_1_edc0357_1360798765767_791304_20993Dependencies() {
                addDependency(new_load, new Expression<Double>(new Functions.Times(new Expression<Double>(desired), new Expression<Double>(cap))));
                addDependency(_17_0_2_1_edc0357_1360798766451_234795_22114, new Expression<Double>(new EffectFunction(sig_17_0_2_1_edc0357_1360798765776_784178_21018, ClassUtils.getMethodForArgTypes("ObjectFlow<Float>", "Demo_Scenario", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(cap, new Expression<Double>(_17_0_2_1_edc0357_1360798766452_823242_22115));
                addDependency(_17_0_2_1_edc0357_1360798766452_823242_22115, new Expression<Double>(new EffectFunction(sig_17_0_2_1_edc0357_1360798765775_489050_21014, ClassUtils.getMethodForArgTypes("ObjectFlow<Float>", "Demo_Scenario", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(desired, new Expression<Double>(_17_0_2_1_edc0357_1360798766451_234795_22114));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new EffectFunction(sig_17_0_2_1_edc0357_1360798765775_963097_21015, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "Demo_Scenario", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_1_edc0357_1360798766450_344902_22113, new Expression<Double>(new_load));
                addDependency(_17_0_2_1_edc0357_1360798765772_631817_21000_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765765_561605_20990_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765765_561605_20990_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
            }

            public void init_17_0_2_1_edc0357_1360798765767_791304_20993Elaborations() {
                init_17_0_2_1_edc0357_1360798765767_791304_20993Dependencies();
                Expression<?>[] arguments116 = new Expression<?>[2];
                arguments116[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                arguments116[1] = new Expression<ObjectFlow<Float>>(sig_17_0_2_1_edc0357_1360798765776_307433_21021);
                Expression<Boolean> condition116 = new Expression<Boolean>(_17_0_2_1_edc0357_1360798765772_631817_21000_exists);
                elaborationRule116 = addElaborationRule(condition116, _17_0_2_1_edc0357_1360798765038_229449_20271.this, Customer._17_0_2_1_edc0357_1360798765038_229449_20271._17_0_2_1_edc0357_1360798765772_631817_21000.class, "CChange_merge_changeTo_MergeNode_usePowerWIthCap", arguments116);
            }
        }

        public class _17_0_2_1_edc0357_1360798765768_429359_20994 extends DurativeEvent {

            public _17_0_2_1_edc0357_1360798765768_429359_20994() {
                super();
                init_17_0_2_1_edc0357_1360798765768_429359_20994Members();
                init_17_0_2_1_edc0357_1360798765768_429359_20994Collections();
                init_17_0_2_1_edc0357_1360798765768_429359_20994Elaborations();
            }

            public _17_0_2_1_edc0357_1360798765768_429359_20994(Expression<Integer> startTime) {
                super();
                init_17_0_2_1_edc0357_1360798765768_429359_20994Members();
                init_17_0_2_1_edc0357_1360798765768_429359_20994Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_1_edc0357_1360798765768_429359_20994Elaborations();
                fixTimeDependencies();
            }

            public DoubleParameter _17_0_2_1_edc0357_1360798766453_71170_22117 = null;

            public BooleanParameter objectToPass = null;

            public Parameter< Power_System.SignalchangeLoadValue > signalObject = null;

            public Dependency< Double > _17_0_2_1_edc0357_1360798766453_71170_22117Dependency = null;

            public Dependency< Integer > finalNode_startTimeDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Dependency< Power_System.SignalchangeLoadValue > signalObjectDependency = null;

            public Effect effect117 = null;

            public Parameter effect117Var = null;

            public Effect effect118 = null;

            public Parameter effect118Var = null;

            public Effect effect119 = null;

            public Parameter effect119Var = null;

            public void init_17_0_2_1_edc0357_1360798765768_429359_20994Members() {
                try {
                    if (_17_0_2_1_edc0357_1360798766453_71170_22117 == null) _17_0_2_1_edc0357_1360798766453_71170_22117 = new DoubleParameter("_17_0_2_1_edc0357_1360798766453_71170_22117", (Double) null, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (signalObject == null) signalObject = new Parameter<Power_System.SignalchangeLoadValue>("signalObject", null, (Power_System.SignalchangeLoadValue) null, this);
                    Object effect117VarV = sig_17_0_2_1_edc0357_1360798765774_725978_21006;
                    effect117Var = new Parameter("effect117Var", null, null, this);
                    addDependency(effect117Var, new Expression(effect117VarV));
                    effect117 = new EffectFunction(new EffectFunction(effect117Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "Demo_Scenario", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect118VarV = new FunctionCall(x, Parameter.class, "getMember", new Object[] { "ss_17_0_2_1_edc0357_1360798765040_940520_20273_changeLoadValue" });
                    effect118Var = new Parameter("effect118Var", null, null, this);
                    addDependency(effect118Var, new Expression(effect118VarV));
                    effect118 = new EffectFunction(new EffectFunction(null, ClassUtils.getMethodForArgTypes("ObjectFlow<SignalchangeLoadValue>", "Demo_Scenario", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { signalObject, endTime }, effect118Var));
                    Object effect119VarV = new FunctionCall(signalObject, Parameter.class, "getMember", new Object[] { "load__17_0_2_1_edc0357_1360798765030_737782_20262" });
                    effect119Var = new Parameter("effect119Var", null, null, this);
                    addDependency(effect119Var, new Expression(effect119VarV));
                    effect119 = new EffectFunction(new EffectFunction(null, ClassUtils.getMethodForArgTypes("TimeVaryingMap<Float>", "Demo_Scenario", "setValue", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, _17_0_2_1_edc0357_1360798766453_71170_22117 }, effect119Var));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_1_edc0357_1360798765768_429359_20994Collections() {
                parameters.add(_17_0_2_1_edc0357_1360798766453_71170_22117);
                parameters.add(objectToPass);
                parameters.add(signalObject);
                Set<Effect> effectsForeffect117Var = new TreeSet<Effect>();
                effectsForeffect117Var.add(effect117);
                addEffects((Parameter<?>) effect117Var, effectsForeffect117Var);
                Set<Effect> effectsForeffect118Var = new TreeSet<Effect>();
                effectsForeffect118Var.add(effect118);
                addEffects((Parameter<?>) effect118Var, effectsForeffect118Var);
                Set<Effect> effectsForeffect119Var = new TreeSet<Effect>();
                effectsForeffect119Var.add(effect119);
                addEffects((Parameter<?>) effect119Var, effectsForeffect119Var);
            }

            public void init_17_0_2_1_edc0357_1360798765768_429359_20994Dependencies() {
                addDependency(_17_0_2_1_edc0357_1360798766453_71170_22117, new Expression<Double>(new EffectFunction(sig_17_0_2_1_edc0357_1360798765774_117048_21008, ClassUtils.getMethodForArgTypes("ObjectFlow<Float>", "Demo_Scenario", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(finalNode_startTime, new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new EffectFunction(sig_17_0_2_1_edc0357_1360798765774_590820_21009, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "Demo_Scenario", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(signalObject, new Expression<Power_System.SignalchangeLoadValue>(new ConstructorCall(x, ClassUtils.getConstructorForArgTypes(Power_System.SignalchangeLoadValue.class), new Object[] {})));
            }

            public void init_17_0_2_1_edc0357_1360798765768_429359_20994Elaborations() {
                init_17_0_2_1_edc0357_1360798765768_429359_20994Dependencies();
            }
        }

        public class _17_0_2_1_edc0357_1360798765769_193393_20995 extends DurativeEvent {

            public _17_0_2_1_edc0357_1360798765769_193393_20995() {
                super();
                init_17_0_2_1_edc0357_1360798765769_193393_20995Members();
                init_17_0_2_1_edc0357_1360798765769_193393_20995Collections();
                init_17_0_2_1_edc0357_1360798765769_193393_20995Elaborations();
            }

            public _17_0_2_1_edc0357_1360798765769_193393_20995(Expression<Integer> startTime) {
                super();
                init_17_0_2_1_edc0357_1360798765769_193393_20995Members();
                init_17_0_2_1_edc0357_1360798765769_193393_20995Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_1_edc0357_1360798765769_193393_20995Elaborations();
                fixTimeDependencies();
            }

            public IntegerParameter myDeciderID_decider_17_0_2_1_edc0357_1360798765768_429359_20994 = null;

            public BooleanParameter addToDecider__17_0_2_1_edc0357_1360798765768_429359_20994 = null;

            public BooleanParameter _17_0_2_1_edc0357_1360798765764_673556_20988_exists = null;

            public DoubleParameter objectToPass = null;

            public BooleanParameter _17_0_2_1_edc0357_1360798765768_429359_20994_exists = null;

            public Dependency< Integer > myDeciderID_decider_17_0_2_1_edc0357_1360798765768_429359_20994Dependency = null;

            public Dependency< Boolean > addToDecider__17_0_2_1_edc0357_1360798765768_429359_20994Dependency = null;

            public Dependency< Boolean > _17_0_2_1_edc0357_1360798765764_673556_20988_existsDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Double > objectToPassDependency = null;

            public Dependency< Boolean > _17_0_2_1_edc0357_1360798765768_429359_20994_existsDependency = null;

            public Effect effect120 = null;

            public Parameter effect120Var = null;

            public Effect effect121 = null;

            public Parameter effect121Var = null;

            public Effect effect122 = null;

            public Parameter effect122Var = null;

            public ElaborationRule elaborationRule123 = null;

            public ElaborationRule elaborationRule124 = null;

            public void init_17_0_2_1_edc0357_1360798765769_193393_20995Members() {
                try {
                    if (myDeciderID_decider_17_0_2_1_edc0357_1360798765768_429359_20994 == null) myDeciderID_decider_17_0_2_1_edc0357_1360798765768_429359_20994 = new IntegerParameter("myDeciderID_decider_17_0_2_1_edc0357_1360798765768_429359_20994", (Integer) 1, this);
                    if (addToDecider__17_0_2_1_edc0357_1360798765768_429359_20994 == null) addToDecider__17_0_2_1_edc0357_1360798765768_429359_20994 = new BooleanParameter("addToDecider__17_0_2_1_edc0357_1360798765768_429359_20994", (Boolean) false, this);
                    if (_17_0_2_1_edc0357_1360798765764_673556_20988_exists == null) _17_0_2_1_edc0357_1360798765764_673556_20988_exists = new BooleanParameter("_17_0_2_1_edc0357_1360798765764_673556_20988_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new DoubleParameter("objectToPass", (Double) null, this);
                    if (_17_0_2_1_edc0357_1360798765768_429359_20994_exists == null) _17_0_2_1_edc0357_1360798765768_429359_20994_exists = new BooleanParameter("_17_0_2_1_edc0357_1360798765768_429359_20994_exists", (Boolean) false, this);
                    Object effect120VarV = sig_17_0_2_1_edc0357_1360798765774_359281_21007;
                    effect120Var = new Parameter("effect120Var", null, null, this);
                    addDependency(effect120Var, new Expression(effect120VarV));
                    effect120 = new EffectFunction(new EffectFunction(effect120Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Float>", "Demo_Scenario", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect121VarV = sig_17_0_2_1_edc0357_1360798765774_117048_21008;
                    effect121Var = new Parameter("effect121Var", null, null, this);
                    addDependency(effect121Var, new Expression(effect121VarV));
                    effect121 = new EffectFunction(new EffectFunction(effect121Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Float>", "Demo_Scenario", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect122VarV = decider_17_0_2_1_edc0357_1360798765768_429359_20994;
                    effect122Var = new Parameter("effect122Var", null, null, this);
                    addDependency(effect122Var, new Expression(effect122VarV));
                    effect122 = new EffectFunction(new EffectFunction(effect122Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "Demo_Scenario", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class, java.lang.Boolean.class), new Object[] { endTime, myDeciderID_decider_17_0_2_1_edc0357_1360798765768_429359_20994, addToDecider__17_0_2_1_edc0357_1360798765768_429359_20994 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_1_edc0357_1360798765769_193393_20995Collections() {
                parameters.add(myDeciderID_decider_17_0_2_1_edc0357_1360798765768_429359_20994);
                parameters.add(addToDecider__17_0_2_1_edc0357_1360798765768_429359_20994);
                parameters.add(_17_0_2_1_edc0357_1360798765764_673556_20988_exists);
                parameters.add(objectToPass);
                parameters.add(_17_0_2_1_edc0357_1360798765768_429359_20994_exists);
                Set<Effect> effectsForeffect120Var = new TreeSet<Effect>();
                effectsForeffect120Var.add(effect120);
                addEffects((Parameter<?>) effect120Var, effectsForeffect120Var);
                Set<Effect> effectsForeffect121Var = new TreeSet<Effect>();
                effectsForeffect121Var.add(effect121);
                addEffects((Parameter<?>) effect121Var, effectsForeffect121Var);
                Set<Effect> effectsForeffect122Var = new TreeSet<Effect>();
                effectsForeffect122Var.add(effect122);
                addEffects((Parameter<?>) effect122Var, effectsForeffect122Var);
            }

            public void init_17_0_2_1_edc0357_1360798765769_193393_20995Dependencies() {
                addDependency(myDeciderID_decider_17_0_2_1_edc0357_1360798765768_429359_20994, new Expression<Integer>(1));
                addDependency(addToDecider__17_0_2_1_edc0357_1360798765768_429359_20994, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765765_561605_20990_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765765_561605_20990_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(_17_0_2_1_edc0357_1360798765764_673556_20988_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765765_561605_20990_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765765_561605_20990_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Double>(new EffectFunction(sig_17_0_2_1_edc0357_1360798765776_353253_21022, ClassUtils.getMethodForArgTypes("ObjectFlow<Float>", "Demo_Scenario", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_1_edc0357_1360798765768_429359_20994_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765765_561605_20990_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765765_561605_20990_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_1_edc0357_1360798765768_429359_20994, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "Demo_Scenario", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_1_edc0357_1360798765768_429359_20994, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "Demo_Scenario", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_1_edc0357_1360798765768_429359_20994, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "Demo_Scenario", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_1_edc0357_1360798765768_429359_20994)))))))));
            }

            public void init_17_0_2_1_edc0357_1360798765769_193393_20995Elaborations() {
                init_17_0_2_1_edc0357_1360798765769_193393_20995Dependencies();
                Expression<?>[] arguments123 = new Expression<?>[1];
                arguments123[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition123 = new Expression<Boolean>(_17_0_2_1_edc0357_1360798765764_673556_20988_exists);
                elaborationRule123 = addElaborationRule(condition123, _17_0_2_1_edc0357_1360798765038_229449_20271.this, Customer._17_0_2_1_edc0357_1360798765038_229449_20271._17_0_2_1_edc0357_1360798765764_673556_20988.class, "CChange_setnewusage_AddStructuralFeatureValueAction_usePowerWIthCap", arguments123);
                Expression<?>[] arguments124 = new Expression<?>[1];
                arguments124[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition124 = new Expression<Boolean>(_17_0_2_1_edc0357_1360798765768_429359_20994_exists);
                elaborationRule124 = addElaborationRule(condition124, _17_0_2_1_edc0357_1360798765038_229449_20271.this, Customer._17_0_2_1_edc0357_1360798765038_229449_20271._17_0_2_1_edc0357_1360798765768_429359_20994.class, "CChange_sendnewusage_SendSignalAction_usePowerWIthCap", arguments124);
            }
        }

        public class _17_0_2_1_edc0357_1360798765769_406258_20996 extends DurativeEvent {

            public _17_0_2_1_edc0357_1360798765769_406258_20996() {
                super();
                init_17_0_2_1_edc0357_1360798765769_406258_20996Members();
                init_17_0_2_1_edc0357_1360798765769_406258_20996Collections();
                init_17_0_2_1_edc0357_1360798765769_406258_20996Elaborations();
            }

            public _17_0_2_1_edc0357_1360798765769_406258_20996(Expression<Integer> startTime) {
                super();
                init_17_0_2_1_edc0357_1360798765769_406258_20996Members();
                init_17_0_2_1_edc0357_1360798765769_406258_20996Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_1_edc0357_1360798765769_406258_20996Elaborations();
                fixTimeDependencies();
            }

            public DoubleParameter new_load = null;

            public DoubleParameter _17_0_2_1_edc0357_1360798766453_368605_22118 = null;

            public DoubleParameter desired = null;

            public BooleanParameter objectToPass = null;

            public DoubleParameter _17_0_2_1_edc0357_1360798766454_457653_22119 = null;

            public BooleanParameter _17_0_2_1_edc0357_1360798765772_631817_21000_exists = null;

            public Dependency< Double > _17_0_2_1_edc0357_1360798766453_368605_22118Dependency = null;

            public Dependency< Double > new_loadDependency = null;

            public Dependency< Double > desiredDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Dependency< Double > _17_0_2_1_edc0357_1360798766454_457653_22119Dependency = null;

            public Dependency< Boolean > _17_0_2_1_edc0357_1360798765772_631817_21000_existsDependency = null;

            public Effect effect125 = null;

            public Parameter effect125Var = null;

            public ElaborationRule elaborationRule126 = null;

            public void init_17_0_2_1_edc0357_1360798765769_406258_20996Members() {
                try {
                    if (new_load == null) new_load = new DoubleParameter("new_load", (Double) null, this);
                    if (_17_0_2_1_edc0357_1360798766453_368605_22118 == null) _17_0_2_1_edc0357_1360798766453_368605_22118 = new DoubleParameter("_17_0_2_1_edc0357_1360798766453_368605_22118", (Double) null, this);
                    if (desired == null) desired = new DoubleParameter("desired", (Double) null, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (_17_0_2_1_edc0357_1360798766454_457653_22119 == null) _17_0_2_1_edc0357_1360798766454_457653_22119 = new DoubleParameter("_17_0_2_1_edc0357_1360798766454_457653_22119", (Double) null, this);
                    if (_17_0_2_1_edc0357_1360798765772_631817_21000_exists == null) _17_0_2_1_edc0357_1360798765772_631817_21000_exists = new BooleanParameter("_17_0_2_1_edc0357_1360798765772_631817_21000_exists", (Boolean) false, this);
                    Object effect125VarV = sig_17_0_2_1_edc0357_1360798765776_287217_21020;
                    effect125Var = new Parameter("effect125Var", null, null, this);
                    addDependency(effect125Var, new Expression(effect125VarV));
                    effect125 = new EffectFunction(new EffectFunction(effect125Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Float>", "Demo_Scenario", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { _17_0_2_1_edc0357_1360798766453_368605_22118, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_1_edc0357_1360798765769_406258_20996Collections() {
                parameters.add(new_load);
                parameters.add(_17_0_2_1_edc0357_1360798766453_368605_22118);
                parameters.add(desired);
                parameters.add(objectToPass);
                parameters.add(_17_0_2_1_edc0357_1360798766454_457653_22119);
                parameters.add(_17_0_2_1_edc0357_1360798765772_631817_21000_exists);
                Set<Effect> effectsForeffect125Var = new TreeSet<Effect>();
                effectsForeffect125Var.add(effect125);
                addEffects((Parameter<?>) effect125Var, effectsForeffect125Var);
            }

            public void init_17_0_2_1_edc0357_1360798765769_406258_20996Dependencies() {
                addDependency(_17_0_2_1_edc0357_1360798766453_368605_22118, new Expression<Double>(new_load));
                addDependency(new_load, new Expression<Double>(desired));
                addDependency(desired, new Expression<Double>(_17_0_2_1_edc0357_1360798766454_457653_22119));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new EffectFunction(sig_17_0_2_1_edc0357_1360798765775_158487_21016, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "Demo_Scenario", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_1_edc0357_1360798766454_457653_22119, new Expression<Double>(new EffectFunction(sig_17_0_2_1_edc0357_1360798765776_292505_21019, ClassUtils.getMethodForArgTypes("ObjectFlow<Float>", "Demo_Scenario", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_1_edc0357_1360798765772_631817_21000_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765765_561605_20990_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765765_561605_20990_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
            }

            public void init_17_0_2_1_edc0357_1360798765769_406258_20996Elaborations() {
                init_17_0_2_1_edc0357_1360798765769_406258_20996Dependencies();
                Expression<?>[] arguments126 = new Expression<?>[2];
                arguments126[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                arguments126[1] = new Expression<ObjectFlow<Float>>(sig_17_0_2_1_edc0357_1360798765776_287217_21020);
                Expression<Boolean> condition126 = new Expression<Boolean>(_17_0_2_1_edc0357_1360798765772_631817_21000_exists);
                elaborationRule126 = addElaborationRule(condition126, _17_0_2_1_edc0357_1360798765038_229449_20271.this, Customer._17_0_2_1_edc0357_1360798765038_229449_20271._17_0_2_1_edc0357_1360798765772_631817_21000.class, "CChange_merge_changeTo_MergeNode_usePowerWIthCap", arguments126);
            }
        }

        public class _17_0_2_1_edc0357_1360798765770_511877_20997 extends DurativeEvent {

            public _17_0_2_1_edc0357_1360798765770_511877_20997() {
                super();
                init_17_0_2_1_edc0357_1360798765770_511877_20997Members();
                init_17_0_2_1_edc0357_1360798765770_511877_20997Collections();
                init_17_0_2_1_edc0357_1360798765770_511877_20997Elaborations();
            }

            public _17_0_2_1_edc0357_1360798765770_511877_20997(Expression<Integer> startTime) {
                super();
                init_17_0_2_1_edc0357_1360798765770_511877_20997Members();
                init_17_0_2_1_edc0357_1360798765770_511877_20997Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_1_edc0357_1360798765770_511877_20997Elaborations();
                fixTimeDependencies();
            }

            public IntegerParameter myDeciderID_decider_17_0_2_1_edc0357_1360798765767_791304_20993 = null;

            public BooleanParameter _17_0_2_1_edc0357_1360798765767_791304_20993_exists = null;

            public BooleanParameter addToDecider__17_0_2_1_edc0357_1360798765767_791304_20993 = null;

            public DoubleParameter objectToPass = null;

            public BooleanParameter _17_0_2_1_edc0357_1360798765771_341663_20998_exists = null;

            public Dependency< Integer > myDeciderID_decider_17_0_2_1_edc0357_1360798765767_791304_20993Dependency = null;

            public Dependency< Boolean > _17_0_2_1_edc0357_1360798765767_791304_20993_existsDependency = null;

            public Dependency< Boolean > addToDecider__17_0_2_1_edc0357_1360798765767_791304_20993Dependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Double > objectToPassDependency = null;

            public Dependency< Boolean > _17_0_2_1_edc0357_1360798765771_341663_20998_existsDependency = null;

            public Effect effect127 = null;

            public Parameter effect127Var = null;

            public Effect effect128 = null;

            public Parameter effect128Var = null;

            public Effect effect129 = null;

            public Parameter effect129Var = null;

            public ElaborationRule elaborationRule130 = null;

            public ElaborationRule elaborationRule131 = null;

            public void init_17_0_2_1_edc0357_1360798765770_511877_20997Members() {
                try {
                    if (myDeciderID_decider_17_0_2_1_edc0357_1360798765767_791304_20993 == null) myDeciderID_decider_17_0_2_1_edc0357_1360798765767_791304_20993 = new IntegerParameter("myDeciderID_decider_17_0_2_1_edc0357_1360798765767_791304_20993", (Integer) 2, this);
                    if (_17_0_2_1_edc0357_1360798765767_791304_20993_exists == null) _17_0_2_1_edc0357_1360798765767_791304_20993_exists = new BooleanParameter("_17_0_2_1_edc0357_1360798765767_791304_20993_exists", (Boolean) false, this);
                    if (addToDecider__17_0_2_1_edc0357_1360798765767_791304_20993 == null) addToDecider__17_0_2_1_edc0357_1360798765767_791304_20993 = new BooleanParameter("addToDecider__17_0_2_1_edc0357_1360798765767_791304_20993", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new DoubleParameter("objectToPass", (Double) null, this);
                    if (_17_0_2_1_edc0357_1360798765771_341663_20998_exists == null) _17_0_2_1_edc0357_1360798765771_341663_20998_exists = new BooleanParameter("_17_0_2_1_edc0357_1360798765771_341663_20998_exists", (Boolean) false, this);
                    Object effect127VarV = sig_17_0_2_1_edc0357_1360798765775_704791_21013;
                    effect127Var = new Parameter("effect127Var", null, null, this);
                    addDependency(effect127Var, new Expression(effect127VarV));
                    effect127 = new EffectFunction(new EffectFunction(effect127Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Float>", "Demo_Scenario", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect128VarV = sig_17_0_2_1_edc0357_1360798765775_489050_21014;
                    effect128Var = new Parameter("effect128Var", null, null, this);
                    addDependency(effect128Var, new Expression(effect128VarV));
                    effect128 = new EffectFunction(new EffectFunction(effect128Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Float>", "Demo_Scenario", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect129VarV = decider_17_0_2_1_edc0357_1360798765767_791304_20993;
                    effect129Var = new Parameter("effect129Var", null, null, this);
                    addDependency(effect129Var, new Expression(effect129VarV));
                    effect129 = new EffectFunction(new EffectFunction(effect129Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "Demo_Scenario", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class, java.lang.Boolean.class), new Object[] { endTime, myDeciderID_decider_17_0_2_1_edc0357_1360798765767_791304_20993, addToDecider__17_0_2_1_edc0357_1360798765767_791304_20993 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_1_edc0357_1360798765770_511877_20997Collections() {
                parameters.add(myDeciderID_decider_17_0_2_1_edc0357_1360798765767_791304_20993);
                parameters.add(_17_0_2_1_edc0357_1360798765767_791304_20993_exists);
                parameters.add(addToDecider__17_0_2_1_edc0357_1360798765767_791304_20993);
                parameters.add(objectToPass);
                parameters.add(_17_0_2_1_edc0357_1360798765771_341663_20998_exists);
                Set<Effect> effectsForeffect127Var = new TreeSet<Effect>();
                effectsForeffect127Var.add(effect127);
                addEffects((Parameter<?>) effect127Var, effectsForeffect127Var);
                Set<Effect> effectsForeffect128Var = new TreeSet<Effect>();
                effectsForeffect128Var.add(effect128);
                addEffects((Parameter<?>) effect128Var, effectsForeffect128Var);
                Set<Effect> effectsForeffect129Var = new TreeSet<Effect>();
                effectsForeffect129Var.add(effect129);
                addEffects((Parameter<?>) effect129Var, effectsForeffect129Var);
            }

            public void init_17_0_2_1_edc0357_1360798765770_511877_20997Dependencies() {
                addDependency(myDeciderID_decider_17_0_2_1_edc0357_1360798765767_791304_20993, new Expression<Integer>(2));
                addDependency(_17_0_2_1_edc0357_1360798765767_791304_20993_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765765_561605_20990_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765765_561605_20990_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_1_edc0357_1360798765767_791304_20993, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "Demo_Scenario", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_1_edc0357_1360798765767_791304_20993, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "Demo_Scenario", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_1_edc0357_1360798765767_791304_20993, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "Demo_Scenario", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_1_edc0357_1360798765767_791304_20993)))))))));
                addDependency(addToDecider__17_0_2_1_edc0357_1360798765767_791304_20993, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765765_561605_20990_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765765_561605_20990_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Double>(new EffectFunction(sig_17_0_2_1_edc0357_1360798765775_476651_21012, ClassUtils.getMethodForArgTypes("ObjectFlow<Float>", "Demo_Scenario", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_1_edc0357_1360798765771_341663_20998_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765765_561605_20990_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765765_561605_20990_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
            }

            public void init_17_0_2_1_edc0357_1360798765770_511877_20997Elaborations() {
                init_17_0_2_1_edc0357_1360798765770_511877_20997Dependencies();
                Expression<?>[] arguments130 = new Expression<?>[1];
                arguments130[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition130 = new Expression<Boolean>(_17_0_2_1_edc0357_1360798765771_341663_20998_exists);
                elaborationRule130 = addElaborationRule(condition130, _17_0_2_1_edc0357_1360798765038_229449_20271.this, Customer._17_0_2_1_edc0357_1360798765038_229449_20271._17_0_2_1_edc0357_1360798765771_341663_20998.class, "CChange_decidecap_DecisionNode_usePowerWIthCap", arguments130);
                Expression<?>[] arguments131 = new Expression<?>[1];
                arguments131[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition131 = new Expression<Boolean>(_17_0_2_1_edc0357_1360798765767_791304_20993_exists);
                elaborationRule131 = addElaborationRule(condition131, _17_0_2_1_edc0357_1360798765038_229449_20271.this, Customer._17_0_2_1_edc0357_1360798765038_229449_20271._17_0_2_1_edc0357_1360798765767_791304_20993.class, "CChange_calc_capmin_CallBehaviorAction_usePowerWIthCap", arguments131);
            }
        }

        public class _17_0_2_1_edc0357_1360798765771_341663_20998 extends DurativeEvent {

            public _17_0_2_1_edc0357_1360798765771_341663_20998() {
                super();
                init_17_0_2_1_edc0357_1360798765771_341663_20998Members();
                init_17_0_2_1_edc0357_1360798765771_341663_20998Collections();
                init_17_0_2_1_edc0357_1360798765771_341663_20998Elaborations();
            }

            public _17_0_2_1_edc0357_1360798765771_341663_20998(Expression<Integer> startTime) {
                super();
                init_17_0_2_1_edc0357_1360798765771_341663_20998Members();
                init_17_0_2_1_edc0357_1360798765771_341663_20998Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_1_edc0357_1360798765771_341663_20998Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter addToDecider__17_0_2_1_edc0357_1360798765769_406258_20996 = null;

            public IntegerParameter myDeciderID_decider_17_0_2_1_edc0357_1360798765769_406258_20996 = null;

            public BooleanParameter _17_0_2_1_edc0357_1360798765769_406258_20996_exists = null;

            public IntegerParameter myDeciderID_decider_17_0_2_1_edc0357_1360798765767_791304_20993 = null;

            public BooleanParameter _17_0_2_1_edc0357_1360798765767_791304_20993_exists = null;

            public BooleanParameter addToDecider__17_0_2_1_edc0357_1360798765767_791304_20993 = null;

            public BooleanParameter objectToPass = null;

            public DoubleParameter decisionInput = null;

            public Dependency< Boolean > _17_0_2_1_edc0357_1360798765769_406258_20996_existsDependency = null;

            public Dependency< Boolean > addToDecider__17_0_2_1_edc0357_1360798765769_406258_20996Dependency = null;

            public Dependency< Integer > myDeciderID_decider_17_0_2_1_edc0357_1360798765769_406258_20996Dependency = null;

            public Dependency< Integer > myDeciderID_decider_17_0_2_1_edc0357_1360798765767_791304_20993Dependency = null;

            public Dependency< Boolean > _17_0_2_1_edc0357_1360798765767_791304_20993_existsDependency = null;

            public Dependency< Boolean > addToDecider__17_0_2_1_edc0357_1360798765767_791304_20993Dependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Dependency< Double > decisionInputDependency = null;

            public Effect effect132 = null;

            public Parameter effect132Var = null;

            public Effect effect133 = null;

            public Parameter effect133Var = null;

            public Effect effect134 = null;

            public Parameter effect134Var = null;

            public Effect effect135 = null;

            public Parameter effect135Var = null;

            public ElaborationRule elaborationRule136 = null;

            public ElaborationRule elaborationRule137 = null;

            public void init_17_0_2_1_edc0357_1360798765771_341663_20998Members() {
                try {
                    if (addToDecider__17_0_2_1_edc0357_1360798765769_406258_20996 == null) addToDecider__17_0_2_1_edc0357_1360798765769_406258_20996 = new BooleanParameter("addToDecider__17_0_2_1_edc0357_1360798765769_406258_20996", (Boolean) false, this);
                    if (myDeciderID_decider_17_0_2_1_edc0357_1360798765769_406258_20996 == null) myDeciderID_decider_17_0_2_1_edc0357_1360798765769_406258_20996 = new IntegerParameter("myDeciderID_decider_17_0_2_1_edc0357_1360798765769_406258_20996", (Integer) 2, this);
                    if (_17_0_2_1_edc0357_1360798765769_406258_20996_exists == null) _17_0_2_1_edc0357_1360798765769_406258_20996_exists = new BooleanParameter("_17_0_2_1_edc0357_1360798765769_406258_20996_exists", (Boolean) false, this);
                    if (myDeciderID_decider_17_0_2_1_edc0357_1360798765767_791304_20993 == null) myDeciderID_decider_17_0_2_1_edc0357_1360798765767_791304_20993 = new IntegerParameter("myDeciderID_decider_17_0_2_1_edc0357_1360798765767_791304_20993", (Integer) 3, this);
                    if (_17_0_2_1_edc0357_1360798765767_791304_20993_exists == null) _17_0_2_1_edc0357_1360798765767_791304_20993_exists = new BooleanParameter("_17_0_2_1_edc0357_1360798765767_791304_20993_exists", (Boolean) false, this);
                    if (addToDecider__17_0_2_1_edc0357_1360798765767_791304_20993 == null) addToDecider__17_0_2_1_edc0357_1360798765767_791304_20993 = new BooleanParameter("addToDecider__17_0_2_1_edc0357_1360798765767_791304_20993", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (decisionInput == null) decisionInput = new DoubleParameter("decisionInput", (Double) null, this);
                    Object effect132VarV = sig_17_0_2_1_edc0357_1360798765775_963097_21015;
                    effect132Var = new Parameter("effect132Var", null, null, this);
                    addDependency(effect132Var, new Expression(effect132VarV));
                    effect132 = new EffectFunction(new EffectFunction(effect132Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "Demo_Scenario", "sendIf", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class, java.lang.Boolean.class), new Object[] { objectToPass, endTime, _17_0_2_1_edc0357_1360798765767_791304_20993_exists }));
                    Object effect133VarV = sig_17_0_2_1_edc0357_1360798765775_158487_21016;
                    effect133Var = new Parameter("effect133Var", null, null, this);
                    addDependency(effect133Var, new Expression(effect133VarV));
                    effect133 = new EffectFunction(new EffectFunction(effect133Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "Demo_Scenario", "sendIf", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class, java.lang.Boolean.class), new Object[] { objectToPass, endTime, _17_0_2_1_edc0357_1360798765769_406258_20996_exists }));
                    Object effect134VarV = decider_17_0_2_1_edc0357_1360798765767_791304_20993;
                    effect134Var = new Parameter("effect134Var", null, null, this);
                    addDependency(effect134Var, new Expression(effect134VarV));
                    effect134 = new EffectFunction(new EffectFunction(effect134Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "Demo_Scenario", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class, java.lang.Boolean.class), new Object[] { endTime, myDeciderID_decider_17_0_2_1_edc0357_1360798765767_791304_20993, addToDecider__17_0_2_1_edc0357_1360798765767_791304_20993 }));
                    Object effect135VarV = decider_17_0_2_1_edc0357_1360798765769_406258_20996;
                    effect135Var = new Parameter("effect135Var", null, null, this);
                    addDependency(effect135Var, new Expression(effect135VarV));
                    effect135 = new EffectFunction(new EffectFunction(effect135Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "Demo_Scenario", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class, java.lang.Boolean.class), new Object[] { endTime, myDeciderID_decider_17_0_2_1_edc0357_1360798765769_406258_20996, addToDecider__17_0_2_1_edc0357_1360798765769_406258_20996 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_1_edc0357_1360798765771_341663_20998Collections() {
                parameters.add(addToDecider__17_0_2_1_edc0357_1360798765769_406258_20996);
                parameters.add(myDeciderID_decider_17_0_2_1_edc0357_1360798765769_406258_20996);
                parameters.add(_17_0_2_1_edc0357_1360798765769_406258_20996_exists);
                parameters.add(myDeciderID_decider_17_0_2_1_edc0357_1360798765767_791304_20993);
                parameters.add(_17_0_2_1_edc0357_1360798765767_791304_20993_exists);
                parameters.add(addToDecider__17_0_2_1_edc0357_1360798765767_791304_20993);
                parameters.add(objectToPass);
                parameters.add(decisionInput);
                Set<Effect> effectsForeffect132Var = new TreeSet<Effect>();
                effectsForeffect132Var.add(effect132);
                addEffects((Parameter<?>) effect132Var, effectsForeffect132Var);
                Set<Effect> effectsForeffect133Var = new TreeSet<Effect>();
                effectsForeffect133Var.add(effect133);
                addEffects((Parameter<?>) effect133Var, effectsForeffect133Var);
                Set<Effect> effectsForeffect134Var = new TreeSet<Effect>();
                effectsForeffect134Var.add(effect134);
                addEffects((Parameter<?>) effect134Var, effectsForeffect134Var);
                Set<Effect> effectsForeffect135Var = new TreeSet<Effect>();
                effectsForeffect135Var.add(effect135);
                addEffects((Parameter<?>) effect135Var, effectsForeffect135Var);
            }

            public void init_17_0_2_1_edc0357_1360798765771_341663_20998Dependencies() {
                addDependency(_17_0_2_1_edc0357_1360798765769_406258_20996_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.LessEquals(new Expression<Double>(decisionInput), new Expression<Integer>(0))), new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765765_561605_20990_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765765_561605_20990_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_1_edc0357_1360798765769_406258_20996, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "Demo_Scenario", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_1_edc0357_1360798765769_406258_20996, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "Demo_Scenario", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_1_edc0357_1360798765769_406258_20996, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "Demo_Scenario", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_1_edc0357_1360798765769_406258_20996)))))))));
                addDependency(addToDecider__17_0_2_1_edc0357_1360798765769_406258_20996, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.LessEquals(new Expression<Double>(decisionInput), new Expression<Integer>(0))), new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765765_561605_20990_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765765_561605_20990_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))))));
                addDependency(myDeciderID_decider_17_0_2_1_edc0357_1360798765769_406258_20996, new Expression<Integer>(2));
                addDependency(myDeciderID_decider_17_0_2_1_edc0357_1360798765767_791304_20993, new Expression<Integer>(3));
                addDependency(_17_0_2_1_edc0357_1360798765767_791304_20993_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Greater(new Expression<Double>(decisionInput), new Expression<Integer>(0))), new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765765_561605_20990_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765765_561605_20990_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_1_edc0357_1360798765767_791304_20993, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "Demo_Scenario", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_1_edc0357_1360798765767_791304_20993, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "Demo_Scenario", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_1_edc0357_1360798765767_791304_20993, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "Demo_Scenario", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_1_edc0357_1360798765767_791304_20993)))))))));
                addDependency(addToDecider__17_0_2_1_edc0357_1360798765767_791304_20993, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Greater(new Expression<Double>(decisionInput), new Expression<Integer>(0))), new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765765_561605_20990_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765765_561605_20990_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(true));
                addDependency(decisionInput, new Expression<Double>(new EffectFunction(sig_17_0_2_1_edc0357_1360798765775_704791_21013, ClassUtils.getMethodForArgTypes("ObjectFlow<Float>", "Demo_Scenario", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_1_edc0357_1360798765771_341663_20998Elaborations() {
                init_17_0_2_1_edc0357_1360798765771_341663_20998Dependencies();
                Expression<?>[] arguments136 = new Expression<?>[1];
                arguments136[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition136 = new Expression<Boolean>(_17_0_2_1_edc0357_1360798765767_791304_20993_exists);
                elaborationRule136 = addElaborationRule(condition136, _17_0_2_1_edc0357_1360798765038_229449_20271.this, Customer._17_0_2_1_edc0357_1360798765038_229449_20271._17_0_2_1_edc0357_1360798765767_791304_20993.class, "CChange_calc_capmin_CallBehaviorAction_usePowerWIthCap", arguments136);
                Expression<?>[] arguments137 = new Expression<?>[1];
                arguments137[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition137 = new Expression<Boolean>(_17_0_2_1_edc0357_1360798765769_406258_20996_exists);
                elaborationRule137 = addElaborationRule(condition137, _17_0_2_1_edc0357_1360798765038_229449_20271.this, Customer._17_0_2_1_edc0357_1360798765038_229449_20271._17_0_2_1_edc0357_1360798765769_406258_20996.class, "CChange_calc_desired_CallBehaviorAction_usePowerWIthCap", arguments137);
            }
        }

        public class _17_0_2_1_edc0357_1360798765771_497308_20999 extends DurativeEvent {

            public _17_0_2_1_edc0357_1360798765771_497308_20999() {
                super();
                init_17_0_2_1_edc0357_1360798765771_497308_20999Members();
                init_17_0_2_1_edc0357_1360798765771_497308_20999Collections();
                init_17_0_2_1_edc0357_1360798765771_497308_20999Elaborations();
            }

            public _17_0_2_1_edc0357_1360798765771_497308_20999(Expression<Integer> startTime) {
                super();
                init_17_0_2_1_edc0357_1360798765771_497308_20999Members();
                init_17_0_2_1_edc0357_1360798765771_497308_20999Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_1_edc0357_1360798765771_497308_20999Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter addToDecider__17_0_2_1_edc0357_1360798765769_406258_20996 = null;

            public IntegerParameter myDeciderID_decider_17_0_2_1_edc0357_1360798765769_406258_20996 = null;

            public BooleanParameter _17_0_2_1_edc0357_1360798765769_406258_20996_exists = null;

            public IntegerParameter myDeciderID_decider_17_0_2_1_edc0357_1360798765767_791304_20993 = null;

            public BooleanParameter _17_0_2_1_edc0357_1360798765767_791304_20993_exists = null;

            public BooleanParameter addToDecider__17_0_2_1_edc0357_1360798765767_791304_20993 = null;

            public DoubleParameter objectToPass = null;

            public Dependency< Boolean > _17_0_2_1_edc0357_1360798765769_406258_20996_existsDependency = null;

            public Dependency< Boolean > addToDecider__17_0_2_1_edc0357_1360798765769_406258_20996Dependency = null;

            public Dependency< Integer > myDeciderID_decider_17_0_2_1_edc0357_1360798765769_406258_20996Dependency = null;

            public Dependency< Integer > myDeciderID_decider_17_0_2_1_edc0357_1360798765767_791304_20993Dependency = null;

            public Dependency< Boolean > _17_0_2_1_edc0357_1360798765767_791304_20993_existsDependency = null;

            public Dependency< Boolean > addToDecider__17_0_2_1_edc0357_1360798765767_791304_20993Dependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Double > objectToPassDependency = null;

            public Effect effect138 = null;

            public Parameter effect138Var = null;

            public Effect effect139 = null;

            public Parameter effect139Var = null;

            public Effect effect140 = null;

            public Parameter effect140Var = null;

            public Effect effect141 = null;

            public Parameter effect141Var = null;

            public ElaborationRule elaborationRule142 = null;

            public ElaborationRule elaborationRule143 = null;

            public void init_17_0_2_1_edc0357_1360798765771_497308_20999Members() {
                try {
                    if (addToDecider__17_0_2_1_edc0357_1360798765769_406258_20996 == null) addToDecider__17_0_2_1_edc0357_1360798765769_406258_20996 = new BooleanParameter("addToDecider__17_0_2_1_edc0357_1360798765769_406258_20996", (Boolean) false, this);
                    if (myDeciderID_decider_17_0_2_1_edc0357_1360798765769_406258_20996 == null) myDeciderID_decider_17_0_2_1_edc0357_1360798765769_406258_20996 = new IntegerParameter("myDeciderID_decider_17_0_2_1_edc0357_1360798765769_406258_20996", (Integer) 1, this);
                    if (_17_0_2_1_edc0357_1360798765769_406258_20996_exists == null) _17_0_2_1_edc0357_1360798765769_406258_20996_exists = new BooleanParameter("_17_0_2_1_edc0357_1360798765769_406258_20996_exists", (Boolean) false, this);
                    if (myDeciderID_decider_17_0_2_1_edc0357_1360798765767_791304_20993 == null) myDeciderID_decider_17_0_2_1_edc0357_1360798765767_791304_20993 = new IntegerParameter("myDeciderID_decider_17_0_2_1_edc0357_1360798765767_791304_20993", (Integer) 1, this);
                    if (_17_0_2_1_edc0357_1360798765767_791304_20993_exists == null) _17_0_2_1_edc0357_1360798765767_791304_20993_exists = new BooleanParameter("_17_0_2_1_edc0357_1360798765767_791304_20993_exists", (Boolean) false, this);
                    if (addToDecider__17_0_2_1_edc0357_1360798765767_791304_20993 == null) addToDecider__17_0_2_1_edc0357_1360798765767_791304_20993 = new BooleanParameter("addToDecider__17_0_2_1_edc0357_1360798765767_791304_20993", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new DoubleParameter("objectToPass", (Double) null, this);
                    Object effect138VarV = sig_17_0_2_1_edc0357_1360798765776_784178_21018;
                    effect138Var = new Parameter("effect138Var", null, null, this);
                    addDependency(effect138Var, new Expression(effect138VarV));
                    effect138 = new EffectFunction(new EffectFunction(effect138Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Float>", "Demo_Scenario", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect139VarV = sig_17_0_2_1_edc0357_1360798765776_292505_21019;
                    effect139Var = new Parameter("effect139Var", null, null, this);
                    addDependency(effect139Var, new Expression(effect139VarV));
                    effect139 = new EffectFunction(new EffectFunction(effect139Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Float>", "Demo_Scenario", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect140VarV = decider_17_0_2_1_edc0357_1360798765767_791304_20993;
                    effect140Var = new Parameter("effect140Var", null, null, this);
                    addDependency(effect140Var, new Expression(effect140VarV));
                    effect140 = new EffectFunction(new EffectFunction(effect140Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "Demo_Scenario", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class, java.lang.Boolean.class), new Object[] { endTime, myDeciderID_decider_17_0_2_1_edc0357_1360798765767_791304_20993, addToDecider__17_0_2_1_edc0357_1360798765767_791304_20993 }));
                    Object effect141VarV = decider_17_0_2_1_edc0357_1360798765769_406258_20996;
                    effect141Var = new Parameter("effect141Var", null, null, this);
                    addDependency(effect141Var, new Expression(effect141VarV));
                    effect141 = new EffectFunction(new EffectFunction(effect141Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "Demo_Scenario", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class, java.lang.Boolean.class), new Object[] { endTime, myDeciderID_decider_17_0_2_1_edc0357_1360798765769_406258_20996, addToDecider__17_0_2_1_edc0357_1360798765769_406258_20996 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_1_edc0357_1360798765771_497308_20999Collections() {
                parameters.add(addToDecider__17_0_2_1_edc0357_1360798765769_406258_20996);
                parameters.add(myDeciderID_decider_17_0_2_1_edc0357_1360798765769_406258_20996);
                parameters.add(_17_0_2_1_edc0357_1360798765769_406258_20996_exists);
                parameters.add(myDeciderID_decider_17_0_2_1_edc0357_1360798765767_791304_20993);
                parameters.add(_17_0_2_1_edc0357_1360798765767_791304_20993_exists);
                parameters.add(addToDecider__17_0_2_1_edc0357_1360798765767_791304_20993);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect138Var = new TreeSet<Effect>();
                effectsForeffect138Var.add(effect138);
                addEffects((Parameter<?>) effect138Var, effectsForeffect138Var);
                Set<Effect> effectsForeffect139Var = new TreeSet<Effect>();
                effectsForeffect139Var.add(effect139);
                addEffects((Parameter<?>) effect139Var, effectsForeffect139Var);
                Set<Effect> effectsForeffect140Var = new TreeSet<Effect>();
                effectsForeffect140Var.add(effect140);
                addEffects((Parameter<?>) effect140Var, effectsForeffect140Var);
                Set<Effect> effectsForeffect141Var = new TreeSet<Effect>();
                effectsForeffect141Var.add(effect141);
                addEffects((Parameter<?>) effect141Var, effectsForeffect141Var);
            }

            public void init_17_0_2_1_edc0357_1360798765771_497308_20999Dependencies() {
                addDependency(_17_0_2_1_edc0357_1360798765769_406258_20996_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765765_561605_20990_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765765_561605_20990_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_1_edc0357_1360798765769_406258_20996, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "Demo_Scenario", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_1_edc0357_1360798765769_406258_20996, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "Demo_Scenario", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_1_edc0357_1360798765769_406258_20996, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "Demo_Scenario", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_1_edc0357_1360798765769_406258_20996)))))))));
                addDependency(addToDecider__17_0_2_1_edc0357_1360798765769_406258_20996, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765765_561605_20990_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765765_561605_20990_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(myDeciderID_decider_17_0_2_1_edc0357_1360798765769_406258_20996, new Expression<Integer>(1));
                addDependency(myDeciderID_decider_17_0_2_1_edc0357_1360798765767_791304_20993, new Expression<Integer>(1));
                addDependency(_17_0_2_1_edc0357_1360798765767_791304_20993_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765765_561605_20990_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765765_561605_20990_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_1_edc0357_1360798765767_791304_20993, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "Demo_Scenario", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_1_edc0357_1360798765767_791304_20993, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "Demo_Scenario", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_1_edc0357_1360798765767_791304_20993, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "Demo_Scenario", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_1_edc0357_1360798765767_791304_20993)))))))));
                addDependency(addToDecider__17_0_2_1_edc0357_1360798765767_791304_20993, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765765_561605_20990_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765765_561605_20990_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Double>(new EffectFunction(sig_17_0_2_1_edc0357_1360798765775_919808_21017, ClassUtils.getMethodForArgTypes("ObjectFlow<Float>", "Demo_Scenario", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_1_edc0357_1360798765771_497308_20999Elaborations() {
                init_17_0_2_1_edc0357_1360798765771_497308_20999Dependencies();
                Expression<?>[] arguments142 = new Expression<?>[1];
                arguments142[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition142 = new Expression<Boolean>(_17_0_2_1_edc0357_1360798765767_791304_20993_exists);
                elaborationRule142 = addElaborationRule(condition142, _17_0_2_1_edc0357_1360798765038_229449_20271.this, Customer._17_0_2_1_edc0357_1360798765038_229449_20271._17_0_2_1_edc0357_1360798765767_791304_20993.class, "CChange_calc_capmin_CallBehaviorAction_usePowerWIthCap", arguments142);
                Expression<?>[] arguments143 = new Expression<?>[1];
                arguments143[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition143 = new Expression<Boolean>(_17_0_2_1_edc0357_1360798765769_406258_20996_exists);
                elaborationRule143 = addElaborationRule(condition143, _17_0_2_1_edc0357_1360798765038_229449_20271.this, Customer._17_0_2_1_edc0357_1360798765038_229449_20271._17_0_2_1_edc0357_1360798765769_406258_20996.class, "CChange_calc_desired_CallBehaviorAction_usePowerWIthCap", arguments143);
            }
        }

        public class _17_0_2_1_edc0357_1360798765772_631817_21000 extends DurativeEvent {

            public _17_0_2_1_edc0357_1360798765772_631817_21000() {
                super();
                init_17_0_2_1_edc0357_1360798765772_631817_21000Members();
                init_17_0_2_1_edc0357_1360798765772_631817_21000Collections();
                init_17_0_2_1_edc0357_1360798765772_631817_21000Elaborations();
            }

            public _17_0_2_1_edc0357_1360798765772_631817_21000(Expression<Integer> startTime, Expression<ObjectFlow<Float>> receiveThis) {
                super();
                init_17_0_2_1_edc0357_1360798765772_631817_21000Members();
                init_17_0_2_1_edc0357_1360798765772_631817_21000Collections();
                addDependency(this.startTime, startTime);
                addDependency(this.receiveThis, receiveThis);
                init_17_0_2_1_edc0357_1360798765772_631817_21000Elaborations();
                fixTimeDependencies();
            }

            public Parameter< ObjectFlow<Float> > receiveThis = null;

            public BooleanParameter _17_0_2_1_edc0357_1360798765769_193393_20995_exists = null;

            public DoubleParameter objectToPass = null;

            public Dependency< Boolean > _17_0_2_1_edc0357_1360798765769_193393_20995_existsDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Double > objectToPassDependency = null;

            public Effect effect144 = null;

            public Parameter effect144Var = null;

            public ElaborationRule elaborationRule145 = null;

            public void init_17_0_2_1_edc0357_1360798765772_631817_21000Members() {
                try {
                    if (receiveThis == null) receiveThis = new Parameter<ObjectFlow<Float>>("receiveThis", null, (ObjectFlow<Float>) null, this);
                    if (_17_0_2_1_edc0357_1360798765769_193393_20995_exists == null) _17_0_2_1_edc0357_1360798765769_193393_20995_exists = new BooleanParameter("_17_0_2_1_edc0357_1360798765769_193393_20995_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new DoubleParameter("objectToPass", (Double) null, this);
                    Object effect144VarV = sig_17_0_2_1_edc0357_1360798765776_353253_21022;
                    effect144Var = new Parameter("effect144Var", null, null, this);
                    addDependency(effect144Var, new Expression(effect144VarV));
                    effect144 = new EffectFunction(new EffectFunction(effect144Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Float>", "Demo_Scenario", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_1_edc0357_1360798765772_631817_21000Collections() {
                parameters.add(receiveThis);
                parameters.add(_17_0_2_1_edc0357_1360798765769_193393_20995_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect144Var = new TreeSet<Effect>();
                effectsForeffect144Var.add(effect144);
                addEffects((Parameter<?>) effect144Var, effectsForeffect144Var);
            }

            public void init_17_0_2_1_edc0357_1360798765772_631817_21000Dependencies() {
                addDependency(_17_0_2_1_edc0357_1360798765769_193393_20995_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765765_561605_20990_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765765_561605_20990_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Double>(new EffectFunction(receiveThis, ClassUtils.getMethodForArgTypes("ObjectFlow<Float>", "Demo_Scenario", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_1_edc0357_1360798765772_631817_21000Elaborations() {
                init_17_0_2_1_edc0357_1360798765772_631817_21000Dependencies();
                Expression<?>[] arguments145 = new Expression<?>[1];
                arguments145[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition145 = new Expression<Boolean>(_17_0_2_1_edc0357_1360798765769_193393_20995_exists);
                elaborationRule145 = addElaborationRule(condition145, _17_0_2_1_edc0357_1360798765038_229449_20271.this, Customer._17_0_2_1_edc0357_1360798765038_229449_20271._17_0_2_1_edc0357_1360798765769_193393_20995.class, "CChange_forkchangeto_ForkNode_usePowerWIthCap", arguments145);
            }
        }

        public class _17_0_2_1_edc0357_1360798765773_288507_21001 extends DurativeEvent {

            public _17_0_2_1_edc0357_1360798765773_288507_21001() {
                super();
                init_17_0_2_1_edc0357_1360798765773_288507_21001Members();
                init_17_0_2_1_edc0357_1360798765773_288507_21001Collections();
                init_17_0_2_1_edc0357_1360798765773_288507_21001Elaborations();
            }

            public _17_0_2_1_edc0357_1360798765773_288507_21001(Expression<Integer> startTime) {
                super();
                init_17_0_2_1_edc0357_1360798765773_288507_21001Members();
                init_17_0_2_1_edc0357_1360798765773_288507_21001Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_1_edc0357_1360798765773_288507_21001Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_1_edc0357_1360798765771_497308_20999_exists = null;

            public DoubleParameter _17_0_2_1_edc0357_1360798766455_191814_22120 = null;

            public BooleanParameter objectToPass = null;

            public Dependency< Boolean > _17_0_2_1_edc0357_1360798765771_497308_20999_existsDependency = null;

            public Dependency< Double > _17_0_2_1_edc0357_1360798766455_191814_22120Dependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Effect effect146 = null;

            public Parameter effect146Var = null;

            public ElaborationRule elaborationRule147 = null;

            public void init_17_0_2_1_edc0357_1360798765773_288507_21001Members() {
                try {
                    if (_17_0_2_1_edc0357_1360798765771_497308_20999_exists == null) _17_0_2_1_edc0357_1360798765771_497308_20999_exists = new BooleanParameter("_17_0_2_1_edc0357_1360798765771_497308_20999_exists", (Boolean) false, this);
                    if (_17_0_2_1_edc0357_1360798766455_191814_22120 == null) _17_0_2_1_edc0357_1360798766455_191814_22120 = new DoubleParameter("_17_0_2_1_edc0357_1360798766455_191814_22120", (Double) null, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect146VarV = sig_17_0_2_1_edc0357_1360798765775_919808_21017;
                    effect146Var = new Parameter("effect146Var", null, null, this);
                    addDependency(effect146Var, new Expression(effect146VarV));
                    effect146 = new EffectFunction(new EffectFunction(effect146Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Float>", "Demo_Scenario", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { _17_0_2_1_edc0357_1360798766455_191814_22120, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_1_edc0357_1360798765773_288507_21001Collections() {
                parameters.add(_17_0_2_1_edc0357_1360798765771_497308_20999_exists);
                parameters.add(_17_0_2_1_edc0357_1360798766455_191814_22120);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect146Var = new TreeSet<Effect>();
                effectsForeffect146Var.add(effect146);
                addEffects((Parameter<?>) effect146Var, effectsForeffect146Var);
            }

            public void init_17_0_2_1_edc0357_1360798765773_288507_21001Dependencies() {
                addDependency(_17_0_2_1_edc0357_1360798765771_497308_20999_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765765_561605_20990_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765765_561605_20990_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(_17_0_2_1_edc0357_1360798766455_191814_22120, new Expression<Double>(new EffectFunction(customer_load_profile__17_0_2_1_edc0357_1360798765043_677547_20278, ClassUtils.getMethodForArgTypes("TimeVaryingProjection<Float>", "Demo_Scenario", "getValue", java.lang.Integer.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new EffectFunction(sig_17_0_2_1_edc0357_1360880675037_212150_16064, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "Demo_Scenario", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_1_edc0357_1360798765773_288507_21001Elaborations() {
                init_17_0_2_1_edc0357_1360798765773_288507_21001Dependencies();
                Expression<?>[] arguments147 = new Expression<?>[1];
                arguments147[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition147 = new Expression<Boolean>(_17_0_2_1_edc0357_1360798765771_497308_20999_exists);
                elaborationRule147 = addElaborationRule(condition147, _17_0_2_1_edc0357_1360798765038_229449_20271.this, Customer._17_0_2_1_edc0357_1360798765038_229449_20271._17_0_2_1_edc0357_1360798765771_497308_20999.class, "CChange_fork4changeval_ForkNode_usePowerWIthCap", arguments147);
            }
        }

        public _17_0_2_1_edc0357_1360798765038_229449_20271(Expression<Integer> startTime, Expression<DurativeEvent> caller) {
            super();
            init_17_0_2_1_edc0357_1360798765038_229449_20271Members();
            init_17_0_2_1_edc0357_1360798765038_229449_20271Collections();
            addDependency(this.startTime, startTime);
            addDependency(this.caller, caller);
            init_17_0_2_1_edc0357_1360798765038_229449_20271Elaborations();
            fixTimeDependencies();
        }

        public Parameter< ObjectFlow<Integer> > sig_17_0_2_1_edc0357_1360798766446_722238_22106 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_1_edc0357_1360880675037_212150_16064 = null;

        public Parameter< DurativeEvent > caller = null;

        public Parameter< TimeVaryingList<Integer> > decider_17_0_2_1_edc0357_1360798765767_791304_20993 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_1_edc0357_1360880681317_965549_16069 = null;

        public Parameter< TimeVaryingList<Integer> > decider_17_0_2_1_edc0357_1360798765769_406258_20996 = null;

        public Parameter< ObjectFlow<Float> > sig_17_0_2_1_edc0357_1360798765776_784178_21018 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_1_edc0357_1360798765775_963097_21015 = null;

        public Parameter< ObjectFlow<Float> > sig_17_0_2_1_edc0357_1360798765775_704791_21013 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_1_edc0357_1360798765774_725978_21006 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_2_1_edc0357_1360798766446_875722_22105 = null;

        public Parameter< ObjectFlow<Float> > sig_17_0_2_1_edc0357_1360798765775_489050_21014 = null;

        public Parameter< ObjectFlow<Float> > sig_17_0_2_1_edc0357_1360798765774_359281_21007 = null;

        public Parameter< TimeVaryingList<Integer> > decider_17_0_2_1_edc0357_1360798765768_429359_20994 = null;

        public Parameter< ObjectFlow<Float> > sig_17_0_2_1_edc0357_1360798765774_117048_21008 = null;

        public IntegerParameter finalNode_endTime = null;

        public Parameter< ObjectFlow<Float> > sig_17_0_2_1_edc0357_1360798765776_292505_21019 = null;

        public Parameter< ObjectFlow<Float> > sig_17_0_2_1_edc0357_1360798765776_287217_21020 = null;

        public BooleanParameter _17_0_2_1_edc0357_1360798765765_561605_20990_exists = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_1_edc0357_1360798765775_158487_21016 = null;

        public IntegerParameter finalNode_startTime = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_1_edc0357_1360798765774_590820_21009 = null;

        public Parameter< ObjectFlow<Float> > sig_17_0_2_1_edc0357_1360798765776_353253_21022 = null;

        public Parameter< ObjectFlow<Float> > sig_17_0_2_1_edc0357_1360798765776_307433_21021 = null;

        public Parameter< ObjectFlow<Float> > sig_17_0_2_1_edc0357_1360798765775_476651_21012 = null;

        public Parameter< ObjectFlow<Float> > sig_17_0_2_1_edc0357_1360798765775_919808_21017 = null;

        public Dependency< Integer > caller_endTimeDependency = null;

        public Dependency< Integer > endTimeDependency = null;

        public Dependency< Boolean > _17_0_2_1_edc0357_1360798765765_561605_20990_existsDependency = null;

        public ElaborationRule elaborationRule103 = null;

        public ElaborationRule elaborationRule104 = null;

        public void init_17_0_2_1_edc0357_1360798765038_229449_20271Members() {
            try {
                if (sig_17_0_2_1_edc0357_1360798766446_722238_22106 == null) sig_17_0_2_1_edc0357_1360798766446_722238_22106 = new Parameter<ObjectFlow<Integer>>("sig_17_0_2_1_edc0357_1360798766446_722238_22106", null, (ObjectFlow<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360798766446_722238_22106" })).evaluate(true), this);
                if (sig_17_0_2_1_edc0357_1360880675037_212150_16064 == null) sig_17_0_2_1_edc0357_1360880675037_212150_16064 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_1_edc0357_1360880675037_212150_16064", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360880675037_212150_16064" })).evaluate(true), this);
                if (caller == null) caller = new Parameter<DurativeEvent>("caller", null, (DurativeEvent) null, this);
                if (decider_17_0_2_1_edc0357_1360798765767_791304_20993 == null) decider_17_0_2_1_edc0357_1360798765767_791304_20993 = new Parameter<TimeVaryingList<Integer>>("decider_17_0_2_1_edc0357_1360798765767_791304_20993", null, (TimeVaryingList<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingList.class, java.lang.String.class, int.class), new Object[] { "decider_17_0_2_1_edc0357_1360798765767_791304_20993", 3 })).evaluate(true), this);
                if (sig_17_0_2_1_edc0357_1360880681317_965549_16069 == null) sig_17_0_2_1_edc0357_1360880681317_965549_16069 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_1_edc0357_1360880681317_965549_16069", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360880681317_965549_16069" })).evaluate(true), this);
                if (decider_17_0_2_1_edc0357_1360798765769_406258_20996 == null) decider_17_0_2_1_edc0357_1360798765769_406258_20996 = new Parameter<TimeVaryingList<Integer>>("decider_17_0_2_1_edc0357_1360798765769_406258_20996", null, (TimeVaryingList<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingList.class, java.lang.String.class, int.class), new Object[] { "decider_17_0_2_1_edc0357_1360798765769_406258_20996", 2 })).evaluate(true), this);
                if (sig_17_0_2_1_edc0357_1360798765776_784178_21018 == null) sig_17_0_2_1_edc0357_1360798765776_784178_21018 = new Parameter<ObjectFlow<Float>>("sig_17_0_2_1_edc0357_1360798765776_784178_21018", null, (ObjectFlow<Float>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360798765776_784178_21018" })).evaluate(true), this);
                if (sig_17_0_2_1_edc0357_1360798765775_963097_21015 == null) sig_17_0_2_1_edc0357_1360798765775_963097_21015 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_1_edc0357_1360798765775_963097_21015", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360798765775_963097_21015" })).evaluate(true), this);
                if (sig_17_0_2_1_edc0357_1360798765775_704791_21013 == null) sig_17_0_2_1_edc0357_1360798765775_704791_21013 = new Parameter<ObjectFlow<Float>>("sig_17_0_2_1_edc0357_1360798765775_704791_21013", null, (ObjectFlow<Float>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360798765775_704791_21013" })).evaluate(true), this);
                if (sig_17_0_2_1_edc0357_1360798765774_725978_21006 == null) sig_17_0_2_1_edc0357_1360798765774_725978_21006 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_1_edc0357_1360798765774_725978_21006", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360798765774_725978_21006" })).evaluate(true), this);
                if (sig_17_0_2_1_edc0357_1360798766446_875722_22105 == null) sig_17_0_2_1_edc0357_1360798766446_875722_22105 = new Parameter<ObjectFlow<Integer>>("sig_17_0_2_1_edc0357_1360798766446_875722_22105", null, (ObjectFlow<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360798766446_875722_22105" })).evaluate(true), this);
                if (sig_17_0_2_1_edc0357_1360798765775_489050_21014 == null) sig_17_0_2_1_edc0357_1360798765775_489050_21014 = new Parameter<ObjectFlow<Float>>("sig_17_0_2_1_edc0357_1360798765775_489050_21014", null, (ObjectFlow<Float>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360798765775_489050_21014" })).evaluate(true), this);
                if (sig_17_0_2_1_edc0357_1360798765774_359281_21007 == null) sig_17_0_2_1_edc0357_1360798765774_359281_21007 = new Parameter<ObjectFlow<Float>>("sig_17_0_2_1_edc0357_1360798765774_359281_21007", null, (ObjectFlow<Float>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360798765774_359281_21007" })).evaluate(true), this);
                if (decider_17_0_2_1_edc0357_1360798765768_429359_20994 == null) decider_17_0_2_1_edc0357_1360798765768_429359_20994 = new Parameter<TimeVaryingList<Integer>>("decider_17_0_2_1_edc0357_1360798765768_429359_20994", null, (TimeVaryingList<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingList.class, java.lang.String.class, int.class), new Object[] { "decider_17_0_2_1_edc0357_1360798765768_429359_20994", 2 })).evaluate(true), this);
                if (sig_17_0_2_1_edc0357_1360798765774_117048_21008 == null) sig_17_0_2_1_edc0357_1360798765774_117048_21008 = new Parameter<ObjectFlow<Float>>("sig_17_0_2_1_edc0357_1360798765774_117048_21008", null, (ObjectFlow<Float>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360798765774_117048_21008" })).evaluate(true), this);
                if (finalNode_endTime == null) finalNode_endTime = new IntegerParameter("finalNode_endTime", (Integer) null, this);
                if (sig_17_0_2_1_edc0357_1360798765776_292505_21019 == null) sig_17_0_2_1_edc0357_1360798765776_292505_21019 = new Parameter<ObjectFlow<Float>>("sig_17_0_2_1_edc0357_1360798765776_292505_21019", null, (ObjectFlow<Float>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360798765776_292505_21019" })).evaluate(true), this);
                if (sig_17_0_2_1_edc0357_1360798765776_287217_21020 == null) sig_17_0_2_1_edc0357_1360798765776_287217_21020 = new Parameter<ObjectFlow<Float>>("sig_17_0_2_1_edc0357_1360798765776_287217_21020", null, (ObjectFlow<Float>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360798765776_287217_21020" })).evaluate(true), this);
                if (_17_0_2_1_edc0357_1360798765765_561605_20990_exists == null) _17_0_2_1_edc0357_1360798765765_561605_20990_exists = new BooleanParameter("_17_0_2_1_edc0357_1360798765765_561605_20990_exists", (Boolean) false, this);
                if (sig_17_0_2_1_edc0357_1360798765775_158487_21016 == null) sig_17_0_2_1_edc0357_1360798765775_158487_21016 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_1_edc0357_1360798765775_158487_21016", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360798765775_158487_21016" })).evaluate(true), this);
                if (finalNode_startTime == null) finalNode_startTime = new IntegerParameter("finalNode_startTime", (Integer) null, this);
                if (sig_17_0_2_1_edc0357_1360798765774_590820_21009 == null) sig_17_0_2_1_edc0357_1360798765774_590820_21009 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_1_edc0357_1360798765774_590820_21009", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360798765774_590820_21009" })).evaluate(true), this);
                if (sig_17_0_2_1_edc0357_1360798765776_353253_21022 == null) sig_17_0_2_1_edc0357_1360798765776_353253_21022 = new Parameter<ObjectFlow<Float>>("sig_17_0_2_1_edc0357_1360798765776_353253_21022", null, (ObjectFlow<Float>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360798765776_353253_21022" })).evaluate(true), this);
                if (sig_17_0_2_1_edc0357_1360798765776_307433_21021 == null) sig_17_0_2_1_edc0357_1360798765776_307433_21021 = new Parameter<ObjectFlow<Float>>("sig_17_0_2_1_edc0357_1360798765776_307433_21021", null, (ObjectFlow<Float>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360798765776_307433_21021" })).evaluate(true), this);
                if (sig_17_0_2_1_edc0357_1360798765775_476651_21012 == null) sig_17_0_2_1_edc0357_1360798765775_476651_21012 = new Parameter<ObjectFlow<Float>>("sig_17_0_2_1_edc0357_1360798765775_476651_21012", null, (ObjectFlow<Float>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360798765775_476651_21012" })).evaluate(true), this);
                if (sig_17_0_2_1_edc0357_1360798765775_919808_21017 == null) sig_17_0_2_1_edc0357_1360798765775_919808_21017 = new Parameter<ObjectFlow<Float>>("sig_17_0_2_1_edc0357_1360798765775_919808_21017", null, (ObjectFlow<Float>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360798765775_919808_21017" })).evaluate(true), this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void init_17_0_2_1_edc0357_1360798765038_229449_20271Collections() {
            parameters.add(sig_17_0_2_1_edc0357_1360798766446_722238_22106);
            parameters.add(sig_17_0_2_1_edc0357_1360880675037_212150_16064);
            parameters.add(caller);
            parameters.add(decider_17_0_2_1_edc0357_1360798765767_791304_20993);
            parameters.add(sig_17_0_2_1_edc0357_1360880681317_965549_16069);
            parameters.add(decider_17_0_2_1_edc0357_1360798765769_406258_20996);
            parameters.add(sig_17_0_2_1_edc0357_1360798765776_784178_21018);
            parameters.add(sig_17_0_2_1_edc0357_1360798765775_963097_21015);
            parameters.add(sig_17_0_2_1_edc0357_1360798765775_704791_21013);
            parameters.add(sig_17_0_2_1_edc0357_1360798765774_725978_21006);
            parameters.add(sig_17_0_2_1_edc0357_1360798766446_875722_22105);
            parameters.add(sig_17_0_2_1_edc0357_1360798765775_489050_21014);
            parameters.add(sig_17_0_2_1_edc0357_1360798765774_359281_21007);
            parameters.add(decider_17_0_2_1_edc0357_1360798765768_429359_20994);
            parameters.add(sig_17_0_2_1_edc0357_1360798765774_117048_21008);
            parameters.add(finalNode_endTime);
            parameters.add(sig_17_0_2_1_edc0357_1360798765776_292505_21019);
            parameters.add(sig_17_0_2_1_edc0357_1360798765776_287217_21020);
            parameters.add(_17_0_2_1_edc0357_1360798765765_561605_20990_exists);
            parameters.add(sig_17_0_2_1_edc0357_1360798765775_158487_21016);
            parameters.add(finalNode_startTime);
            parameters.add(sig_17_0_2_1_edc0357_1360798765774_590820_21009);
            parameters.add(sig_17_0_2_1_edc0357_1360798765776_353253_21022);
            parameters.add(sig_17_0_2_1_edc0357_1360798765776_307433_21021);
            parameters.add(sig_17_0_2_1_edc0357_1360798765775_476651_21012);
            parameters.add(sig_17_0_2_1_edc0357_1360798765775_919808_21017);
        }

        public void init_17_0_2_1_edc0357_1360798765038_229449_20271Dependencies() {
            caller.getValue(true).addDependency(caller.getValue(true).endTime, new Expression<Integer>(finalNode_endTime));
            addDependency(endTime, new Expression<Integer>(finalNode_endTime));
            addDependency(_17_0_2_1_edc0357_1360798765765_561605_20990_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.NotEquals(new Expression<Integer>(finalNode_startTime), null)), new Expression(new EffectFunction(sig_17_0_2_1_edc0357_1360798765774_725978_21006, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "Demo_Scenario", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(new Functions.Minus(new Expression<Integer>(finalNode_startTime), new Expression<Integer>(1))) })))));
        }

        public void init_17_0_2_1_edc0357_1360798765038_229449_20271Elaborations() {
            init_17_0_2_1_edc0357_1360798765038_229449_20271Dependencies();
            Expression<?>[] arguments103 = new Expression<?>[1];
            arguments103[0] = new Expression<Integer>(startTime);
            Expression<Boolean> condition103 = new Expression<Boolean>(true);
            elaborationRule103 = addElaborationRule(condition103, _17_0_2_1_edc0357_1360798765038_229449_20271.this, Customer._17_0_2_1_edc0357_1360798765038_229449_20271._17_0_2_1_edc0357_1360798765765_263316_20989.class, "CChange_start_InitialNode_usePowerWIthCap", arguments103);
            Expression<?>[] arguments104 = new Expression<?>[1];
            arguments104[0] = new Expression<Integer>(finalNode_startTime);
            Expression<Boolean> condition104 = new Expression<Boolean>(_17_0_2_1_edc0357_1360798765765_561605_20990_exists);
            elaborationRule104 = addElaborationRule(condition104, _17_0_2_1_edc0357_1360798765038_229449_20271.this, Customer._17_0_2_1_edc0357_1360798765038_229449_20271._17_0_2_1_edc0357_1360798765765_561605_20990.class, "CChange_end_ActivityFinalNode_usePowerWIthCap", arguments104);
        }
    }

    public Customer(Power_System x) {
        super();
        this.x = new Parameter<Power_System>("Power_System", null, x, null);
        initCustomerMembers();
        initCustomerCollections();
        initCustomerElaborations();
    }

    public StringParameter classifierBehavior = null;

    public Parameter< ObjectFlow<Power_System.SignalreceiveMeterReading> > q_Customer_receiveMeterReading = null;

    public Parameter< TimeVaryingProjection<Float> > customer_load_profile__17_0_2_1_edc0357_1360798765043_677547_20278 = null;

    public Parameter< TimeVaryingMap<Float> > reduction_level__17_0_2_1_edc0357_1360798765043_828353_20277 = null;

    public Parameter< TimeVaryingMap<Boolean> > participation_policy__17_0_2_1_edc0357_1360951506981_801034_20303 = null;

    public Parameter< ObjectFlow<Power_System.Signaldr_request> > q_Customer_dr_request = null;

    public Parameter< Power_System > x = null;

    public Parameter< ObjectFlow<Power_System.Signalyes> > q_Customer_yes = null;

    public Parameter< ObjectFlow<Power_System.SignalchangeLoadValue> > q_Customer_changeLoadValue = null;

    public Parameter< TimeVaryingPlottableMap<Float> > customer_actual_load__17_0_2_1_edc0357_1360798765039_852829_20272 = null;

    public Parameter< ObjectFlow<Power_System.Signalno> > q_Customer_no = null;

    public void initCustomerMembers() {
        try {
            if (classifierBehavior == null) classifierBehavior = new StringParameter("classifierBehavior", (String) "_17_0_2_1_edc0357_1360798765037_222001_20270", this);
            if (q_Customer_receiveMeterReading == null) q_Customer_receiveMeterReading = new Parameter<ObjectFlow<Power_System.SignalreceiveMeterReading>>("q_Customer_receiveMeterReading", null, (ObjectFlow<Power_System.SignalreceiveMeterReading>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class, java.lang.Class.class), new Object[] { "q_Customer_receiveMeterReading", Power_System.SignalreceiveMeterReading.class })).evaluate(true), this);
            if (customer_load_profile__17_0_2_1_edc0357_1360798765043_677547_20278 == null) customer_load_profile__17_0_2_1_edc0357_1360798765043_677547_20278 = new Parameter<TimeVaryingProjection<Float>>("customer_load_profile__17_0_2_1_edc0357_1360798765043_677547_20278", null, (TimeVaryingProjection<Float>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingProjection.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Class.class), new Object[] { "customer_load_profile", "test_load.csv", null, Float.class, true })).evaluate(true), this);
            if (reduction_level__17_0_2_1_edc0357_1360798765043_828353_20277 == null) reduction_level__17_0_2_1_edc0357_1360798765043_828353_20277 = new Parameter<TimeVaryingMap<Float>>("reduction_level__17_0_2_1_edc0357_1360798765043_828353_20277", null, (TimeVaryingMap<Float>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingMap.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Class.class), new Object[] { "reduction_level", null, -1.0, Float.class })).evaluate(true), this);
            if (participation_policy__17_0_2_1_edc0357_1360951506981_801034_20303 == null) participation_policy__17_0_2_1_edc0357_1360951506981_801034_20303 = new Parameter<TimeVaryingMap<Boolean>>("participation_policy__17_0_2_1_edc0357_1360951506981_801034_20303", null, (TimeVaryingMap<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingMap.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Class.class), new Object[] { "participation_policy", null, true, Boolean.class })).evaluate(true), this);
            if (q_Customer_dr_request == null) q_Customer_dr_request = new Parameter<ObjectFlow<Power_System.Signaldr_request>>("q_Customer_dr_request", null, (ObjectFlow<Power_System.Signaldr_request>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class, java.lang.Class.class), new Object[] { "q_Customer_dr_request", Power_System.Signaldr_request.class })).evaluate(true), this);
            if (x == null) x = new Parameter<Power_System>("x", null, (Power_System) null, this);
            if (q_Customer_yes == null) q_Customer_yes = new Parameter<ObjectFlow<Power_System.Signalyes>>("q_Customer_yes", null, (ObjectFlow<Power_System.Signalyes>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class, java.lang.Class.class), new Object[] { "q_Customer_yes", Power_System.Signalyes.class })).evaluate(true), this);
            if (q_Customer_changeLoadValue == null) q_Customer_changeLoadValue = new Parameter<ObjectFlow<Power_System.SignalchangeLoadValue>>("q_Customer_changeLoadValue", null, (ObjectFlow<Power_System.SignalchangeLoadValue>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class, java.lang.Class.class), new Object[] { "q_Customer_changeLoadValue", Power_System.SignalchangeLoadValue.class })).evaluate(true), this);
            if (customer_actual_load__17_0_2_1_edc0357_1360798765039_852829_20272 == null) customer_actual_load__17_0_2_1_edc0357_1360798765039_852829_20272 = new Parameter<TimeVaryingPlottableMap<Float>>("customer_actual_load__17_0_2_1_edc0357_1360798765039_852829_20272", null, (TimeVaryingPlottableMap<Float>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingPlottableMap.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Class.class), new Object[] { "customer_actual_load", null, 0, Float.class })).evaluate(true), this);
            if (q_Customer_no == null) q_Customer_no = new Parameter<ObjectFlow<Power_System.Signalno>>("q_Customer_no", null, (ObjectFlow<Power_System.Signalno>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class, java.lang.Class.class), new Object[] { "q_Customer_no", Power_System.Signalno.class })).evaluate(true), this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void initCustomerCollections() {
        parameters.add(classifierBehavior);
        parameters.add(q_Customer_receiveMeterReading);
        parameters.add(customer_load_profile__17_0_2_1_edc0357_1360798765043_677547_20278);
        parameters.add(reduction_level__17_0_2_1_edc0357_1360798765043_828353_20277);
        parameters.add(participation_policy__17_0_2_1_edc0357_1360951506981_801034_20303);
        parameters.add(q_Customer_dr_request);
        parameters.add(x);
        parameters.add(q_Customer_yes);
        parameters.add(q_Customer_changeLoadValue);
        parameters.add(customer_actual_load__17_0_2_1_edc0357_1360798765039_852829_20272);
        parameters.add(q_Customer_no);
    }

    public void initCustomerDependencies() {
    }

    public void initCustomerElaborations() {
        initCustomerDependencies();
    }
}
