package DIFF_SCENARIO;

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
import gov.nasa.jpl.ae.event.TimeVaryingPlottableMaps;
import gov.nasa.jpl.ae.event.TimeVaryingProjection;
import java.util.Vector;
import java.util.Map;
import java.util.Queue;
import java.util.LinkedList;
import gov.nasa.jpl.ae.fuml.ObjectFlow;
import gov.nasa.jpl.ae.event.TimeVaryingList;
import java.lang.Math;
import java.util.Set;
import java.util.TreeSet;
import gov.nasa.jpl.ae.event.EffectFunction;
import gov.nasa.jpl.mbee.util.ClassUtils;
import gov.nasa.jpl.mbee.util.Utils;

public class Power extends ParameterListenerImpl {

    public Power() {
        super();
        initPowerMembers();
        initPowerCollections();
        initPowerElaborations();
    }

    public class _17_0_2_1_edc0357_1360798765021_125160_20250 extends DurativeEvent {

        public _17_0_2_1_edc0357_1360798765021_125160_20250() {
            super();
            init_17_0_2_1_edc0357_1360798765021_125160_20250Members();
            init_17_0_2_1_edc0357_1360798765021_125160_20250Collections();
            init_17_0_2_1_edc0357_1360798765021_125160_20250Elaborations();
        }

        public class _17_0_2_1_edc0357_1360798765440_273045_20572 extends DurativeEvent {

            public _17_0_2_1_edc0357_1360798765440_273045_20572() {
                super();
                init_17_0_2_1_edc0357_1360798765440_273045_20572Members();
                init_17_0_2_1_edc0357_1360798765440_273045_20572Collections();
                init_17_0_2_1_edc0357_1360798765440_273045_20572Elaborations();
            }

            public _17_0_2_1_edc0357_1360798765440_273045_20572(Expression<Integer> startTime) {
                super();
                init_17_0_2_1_edc0357_1360798765440_273045_20572Members();
                init_17_0_2_1_edc0357_1360798765440_273045_20572Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_1_edc0357_1360798765440_273045_20572Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter objectToPass = null;

            public BooleanParameter _17_0_2_1_edc0357_1360798765441_546996_20574_exists = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Dependency _17_0_2_1_edc0357_1360798765441_546996_20574_existsDependency = null;

            public Effect effect150 = null;

            public Parameter effect150Var = null;

            public ElaborationRule elaborationRule151 = null;

            public void init_17_0_2_1_edc0357_1360798765440_273045_20572Members() {
                try {
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (_17_0_2_1_edc0357_1360798765441_546996_20574_exists == null) _17_0_2_1_edc0357_1360798765441_546996_20574_exists = new BooleanParameter("_17_0_2_1_edc0357_1360798765441_546996_20574_exists", (Boolean) false, this);
                    Object effect150VarV = sig_17_0_2_1_edc0357_1360878965913_511003_15928;
                    effect150Var = new Parameter("effect150Var", null, null, this);
                    addDependency(effect150Var, new Expression(effect150VarV));
                    effect150 = new EffectFunction(new EffectFunction(effect150Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "DIFF_SCENARIO", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Boolean>(objectToPass), new Expression<Integer>(endTime) }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_1_edc0357_1360798765440_273045_20572Collections() {
                parameters.add(objectToPass);
                parameters.add(_17_0_2_1_edc0357_1360798765441_546996_20574_exists);
                Set<Effect> effectsForeffect150Var = new TreeSet<Effect>();
                effectsForeffect150Var.add(effect150);
                addEffects((Parameter<?>) effect150Var, effectsForeffect150Var);
            }

            public void init_17_0_2_1_edc0357_1360798765440_273045_20572Dependencies() {
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(true));
                addDependency(_17_0_2_1_edc0357_1360798765441_546996_20574_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765440_675335_20573_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765440_675335_20573_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
            }

            public void init_17_0_2_1_edc0357_1360798765440_273045_20572Elaborations() {
                init_17_0_2_1_edc0357_1360798765440_273045_20572Dependencies();
                Expression<?>[] arguments151 = new Expression<?>[1];
                arguments151[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition151 = new Expression<Boolean>(_17_0_2_1_edc0357_1360798765441_546996_20574_exists);
                elaborationRule151 = addElaborationRule(condition151, _17_0_2_1_edc0357_1360798765021_125160_20250.this, Power._17_0_2_1_edc0357_1360798765021_125160_20250._17_0_2_1_edc0357_1360798765441_546996_20574.class, "PGen_read_profile__ReadStructuralFeatureAction__generate__Power", arguments151);
            }
        }

        public class _17_0_2_1_edc0357_1360798765440_675335_20573 extends DurativeEvent {

            public _17_0_2_1_edc0357_1360798765440_675335_20573() {
                super();
                init_17_0_2_1_edc0357_1360798765440_675335_20573Members();
                init_17_0_2_1_edc0357_1360798765440_675335_20573Collections();
                init_17_0_2_1_edc0357_1360798765440_675335_20573Elaborations();
            }

            public _17_0_2_1_edc0357_1360798765440_675335_20573(Expression<Integer> startTime) {
                super();
                init_17_0_2_1_edc0357_1360798765440_675335_20573Members();
                init_17_0_2_1_edc0357_1360798765440_675335_20573Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_1_edc0357_1360798765440_675335_20573Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter objectToPass = null;

            public Dependency endTimeDependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Dependency finalNode_endTimeDependency = null;

            public void init_17_0_2_1_edc0357_1360798765440_675335_20573Members() {
                try {
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_1_edc0357_1360798765440_675335_20573Collections() {
                parameters.add(objectToPass);
            }

            public void init_17_0_2_1_edc0357_1360798765440_675335_20573Dependencies() {
                addDependency(endTime, new Expression<Integer>(new Functions.Plus(new Expression<Integer>(startTime), new Expression<Integer>(duration))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_1_edc0357_1360798765446_873055_20593, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "DIFF_SCENARIO", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(finalNode_endTime, new Expression<Integer>(endTime));
            }

            public void init_17_0_2_1_edc0357_1360798765440_675335_20573Elaborations() {
                init_17_0_2_1_edc0357_1360798765440_675335_20573Dependencies();
            }
        }

        public class _17_0_2_1_edc0357_1360798765441_546996_20574 extends DurativeEvent {

            public _17_0_2_1_edc0357_1360798765441_546996_20574() {
                super();
                init_17_0_2_1_edc0357_1360798765441_546996_20574Members();
                init_17_0_2_1_edc0357_1360798765441_546996_20574Collections();
                init_17_0_2_1_edc0357_1360798765441_546996_20574Elaborations();
            }

            public _17_0_2_1_edc0357_1360798765441_546996_20574(Expression<Integer> startTime) {
                super();
                init_17_0_2_1_edc0357_1360798765441_546996_20574Members();
                init_17_0_2_1_edc0357_1360798765441_546996_20574Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_1_edc0357_1360798765441_546996_20574Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter addToDecider__17_0_2_1_edc0357_1360798765444_162511_20578 = null;

            public IntegerParameter myDeciderID_decider_17_0_2_1_edc0357_1360798765444_162511_20578 = null;

            public DoubleParameter _17_0_2_1_edc0357_1360798766254_732299_21881 = null;

            public BooleanParameter objectToPass = null;

            public BooleanParameter _17_0_2_1_edc0357_1360798765443_547604_20577_exists = null;

            public BooleanParameter _17_0_2_1_edc0357_1360798765444_162511_20578_exists = null;

            public Dependency addToDecider__17_0_2_1_edc0357_1360798765444_162511_20578Dependency = null;

            public Dependency myDeciderID_decider_17_0_2_1_edc0357_1360798765444_162511_20578Dependency = null;

            public Dependency _17_0_2_1_edc0357_1360798766254_732299_21881Dependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Dependency _17_0_2_1_edc0357_1360798765443_547604_20577_existsDependency = null;

            public Dependency _17_0_2_1_edc0357_1360798765444_162511_20578_existsDependency = null;

            public Effect effect152 = null;

            public Parameter effect152Var = null;

            public Effect effect153 = null;

            public Parameter effect153Var = null;

            public Effect effect154 = null;

            public Parameter effect154Var = null;

            public ElaborationRule elaborationRule155 = null;

            public ElaborationRule elaborationRule156 = null;

            public void init_17_0_2_1_edc0357_1360798765441_546996_20574Members() {
                try {
                    if (addToDecider__17_0_2_1_edc0357_1360798765444_162511_20578 == null) addToDecider__17_0_2_1_edc0357_1360798765444_162511_20578 = new BooleanParameter("addToDecider__17_0_2_1_edc0357_1360798765444_162511_20578", (Boolean) false, this);
                    if (myDeciderID_decider_17_0_2_1_edc0357_1360798765444_162511_20578 == null) myDeciderID_decider_17_0_2_1_edc0357_1360798765444_162511_20578 = new IntegerParameter("myDeciderID_decider_17_0_2_1_edc0357_1360798765444_162511_20578", (Integer) 1, this);
                    if (_17_0_2_1_edc0357_1360798766254_732299_21881 == null) _17_0_2_1_edc0357_1360798766254_732299_21881 = new DoubleParameter("_17_0_2_1_edc0357_1360798766254_732299_21881", (Double) null, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (_17_0_2_1_edc0357_1360798765443_547604_20577_exists == null) _17_0_2_1_edc0357_1360798765443_547604_20577_exists = new BooleanParameter("_17_0_2_1_edc0357_1360798765443_547604_20577_exists", (Boolean) false, this);
                    if (_17_0_2_1_edc0357_1360798765444_162511_20578_exists == null) _17_0_2_1_edc0357_1360798765444_162511_20578_exists = new BooleanParameter("_17_0_2_1_edc0357_1360798765444_162511_20578_exists", (Boolean) false, this);
                    Object effect152VarV = sig_17_0_2_1_edc0357_1360798765445_230375_20587;
                    effect152Var = new Parameter("effect152Var", null, null, this);
                    addDependency(effect152Var, new Expression(effect152VarV));
                    effect152 = new EffectFunction(new EffectFunction(effect152Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Float>", "DIFF_SCENARIO", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Double>(_17_0_2_1_edc0357_1360798766254_732299_21881), new Expression<Integer>(endTime) }));
                    Object effect153VarV = sig_17_0_2_1_edc0357_1360878970443_16585_15933;
                    effect153Var = new Parameter("effect153Var", null, null, this);
                    addDependency(effect153Var, new Expression(effect153VarV));
                    effect153 = new EffectFunction(new EffectFunction(effect153Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "DIFF_SCENARIO", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Boolean>(objectToPass), new Expression<Integer>(endTime) }));
                    Object effect154VarV = decider_17_0_2_1_edc0357_1360798765444_162511_20578;
                    effect154Var = new Parameter("effect154Var", null, null, this);
                    addDependency(effect154Var, new Expression(effect154VarV));
                    effect154 = new EffectFunction(new EffectFunction(effect154Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "DIFF_SCENARIO", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class, java.lang.Boolean.class), new Object[] { new Expression<Integer>(endTime), new Expression<Integer>(myDeciderID_decider_17_0_2_1_edc0357_1360798765444_162511_20578), new Expression<Boolean>(addToDecider__17_0_2_1_edc0357_1360798765444_162511_20578) }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_1_edc0357_1360798765441_546996_20574Collections() {
                parameters.add(addToDecider__17_0_2_1_edc0357_1360798765444_162511_20578);
                parameters.add(myDeciderID_decider_17_0_2_1_edc0357_1360798765444_162511_20578);
                parameters.add(_17_0_2_1_edc0357_1360798766254_732299_21881);
                parameters.add(objectToPass);
                parameters.add(_17_0_2_1_edc0357_1360798765443_547604_20577_exists);
                parameters.add(_17_0_2_1_edc0357_1360798765444_162511_20578_exists);
                Set<Effect> effectsForeffect152Var = new TreeSet<Effect>();
                effectsForeffect152Var.add(effect152);
                addEffects((Parameter<?>) effect152Var, effectsForeffect152Var);
                Set<Effect> effectsForeffect153Var = new TreeSet<Effect>();
                effectsForeffect153Var.add(effect153);
                addEffects((Parameter<?>) effect153Var, effectsForeffect153Var);
                Set<Effect> effectsForeffect154Var = new TreeSet<Effect>();
                effectsForeffect154Var.add(effect154);
                addEffects((Parameter<?>) effect154Var, effectsForeffect154Var);
            }

            public void init_17_0_2_1_edc0357_1360798765441_546996_20574Dependencies() {
                addDependency(addToDecider__17_0_2_1_edc0357_1360798765444_162511_20578, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765440_675335_20573_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765440_675335_20573_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(myDeciderID_decider_17_0_2_1_edc0357_1360798765444_162511_20578, new Expression<Integer>(1));
                addDependency(_17_0_2_1_edc0357_1360798766254_732299_21881, new Expression(new FunctionCall(null, ClassUtils.getMethodForArgTypes("null", "DIFF_SCENARIO", "getValue"), new Object[] { new Expression<Integer>(startTime) }, new EffectFunction(genProfile__17_0_2_1_edc0357_1360798765028_430905_20259, ClassUtils.getMethodForArgTypes("TimeVaryingProjection<Float>", "DIFF_SCENARIO", "getValue", java.lang.Integer.class), new Object[] { startTime }))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_1_edc0357_1360878965913_511003_15928, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "DIFF_SCENARIO", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_1_edc0357_1360798765443_547604_20577_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765440_675335_20573_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765440_675335_20573_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(_17_0_2_1_edc0357_1360798765444_162511_20578_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765440_675335_20573_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765440_675335_20573_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_1_edc0357_1360798765444_162511_20578, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "DIFF_SCENARIO", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_1_edc0357_1360798765444_162511_20578, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "DIFF_SCENARIO", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_1_edc0357_1360798765444_162511_20578, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "DIFF_SCENARIO", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_1_edc0357_1360798765444_162511_20578)))))))));
            }

            public void init_17_0_2_1_edc0357_1360798765441_546996_20574Elaborations() {
                init_17_0_2_1_edc0357_1360798765441_546996_20574Dependencies();
                Expression<?>[] arguments155 = new Expression<?>[1];
                arguments155[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition155 = new Expression<Boolean>(_17_0_2_1_edc0357_1360798765443_547604_20577_exists);
                elaborationRule155 = addElaborationRule(condition155, _17_0_2_1_edc0357_1360798765021_125160_20250.this, Power._17_0_2_1_edc0357_1360798765021_125160_20250._17_0_2_1_edc0357_1360798765443_547604_20577.class, "PGen_read_augment__ReadStructuralFeatureAction__generate__Power", arguments155);
                Expression<?>[] arguments156 = new Expression<?>[1];
                arguments156[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition156 = new Expression<Boolean>(_17_0_2_1_edc0357_1360798765444_162511_20578_exists);
                elaborationRule156 = addElaborationRule(condition156, _17_0_2_1_edc0357_1360798765021_125160_20250.this, Power._17_0_2_1_edc0357_1360798765021_125160_20250._17_0_2_1_edc0357_1360798765444_162511_20578.class, "PGen_add_augment__CallBehaviorAction__generate__Power", arguments156);
            }
        }

        public class _17_0_2_1_edc0357_1360798765442_572902_20575 extends DurativeEvent {

            public _17_0_2_1_edc0357_1360798765442_572902_20575() {
                super();
                init_17_0_2_1_edc0357_1360798765442_572902_20575Members();
                init_17_0_2_1_edc0357_1360798765442_572902_20575Collections();
                init_17_0_2_1_edc0357_1360798765442_572902_20575Elaborations();
            }

            public _17_0_2_1_edc0357_1360798765442_572902_20575(Expression<Integer> startTime) {
                super();
                init_17_0_2_1_edc0357_1360798765442_572902_20575Members();
                init_17_0_2_1_edc0357_1360798765442_572902_20575Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_1_edc0357_1360798765442_572902_20575Elaborations();
                fixTimeDependencies();
            }

            public DoubleParameter _17_0_2_1_edc0357_1360798766255_580546_21884 = null;

            public BooleanParameter objectToPass = null;

            public Parameter< Power_System.SignalreceiveGenReading > signalObject = null;

            public Dependency finalNode_startTimeDependency = null;

            public Dependency durationDependency = null;

            public Dependency _17_0_2_1_edc0357_1360798766255_580546_21884Dependency = null;

            public Dependency objectToPassDependency = null;

            public Effect effect157 = null;

            public Parameter effect157Var = null;

            public Effect effect158 = null;

            public Parameter effect158Var = null;

            public Effect effect159 = null;

            public Parameter effect159Var = null;

            public void init_17_0_2_1_edc0357_1360798765442_572902_20575Members() {
                try {
                    if (_17_0_2_1_edc0357_1360798766255_580546_21884 == null) _17_0_2_1_edc0357_1360798766255_580546_21884 = new DoubleParameter("_17_0_2_1_edc0357_1360798766255_580546_21884", (Double) null, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (signalObject == null) signalObject = new Parameter<Power_System.SignalreceiveGenReading>("signalObject", null, (Power_System.SignalreceiveGenReading) (new ConstructorCall(x, ClassUtils.getConstructorForArgTypes(Power_System.SignalreceiveGenReading.class), new Object[] {})).evaluate(true), this);
                    Object effect157VarV = sig_17_0_2_1_edc0357_1360798765446_873055_20593;
                    effect157Var = new Parameter("effect157Var", null, null, this);
                    addDependency(effect157Var, new Expression(effect157VarV));
                    effect157 = new EffectFunction(new EffectFunction(effect157Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "DIFF_SCENARIO", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Boolean>(objectToPass), new Expression<Integer>(endTime) }));
                    Object effect158VarV = new FunctionCall(x, Parameter.class, "getMember", new Object[] { "ss_17_0_2_1_edc0357_1360798765028_640858_20258_receiveGenReading" });
                    effect158Var = new Parameter("effect158Var", null, null, this);
                    addDependency(effect158Var, new Expression(effect158VarV));
                    effect158 = new EffectFunction(new EffectFunction(null, ClassUtils.getMethodForArgTypes("ObjectFlow<SignalreceiveGenReading>", "DIFF_SCENARIO", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Power_System.SignalreceiveGenReading>(signalObject), new Expression<Integer>(endTime) }, effect158Var));
                    Object effect159VarV = new FunctionCall(signalObject, Parameter.class, "getMember", new Object[] { "actual_power__17_0_2_1_edc0357_1360798765096_597525_20352" });
                    effect159Var = new Parameter("effect159Var", null, null, this);
                    addDependency(effect159Var, new Expression(effect159VarV));
                    effect159 = new EffectFunction(new EffectFunction(null, ClassUtils.getMethodForArgTypes("TimeVaryingMap<Float>", "DIFF_SCENARIO", "setValue", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { new Expression<Integer>(endTime), new Expression<Double>(_17_0_2_1_edc0357_1360798766255_580546_21884) }, effect159Var));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_1_edc0357_1360798765442_572902_20575Collections() {
                parameters.add(_17_0_2_1_edc0357_1360798766255_580546_21884);
                parameters.add(objectToPass);
                parameters.add(signalObject);
                Set<Effect> effectsForeffect157Var = new TreeSet<Effect>();
                effectsForeffect157Var.add(effect157);
                addEffects((Parameter<?>) effect157Var, effectsForeffect157Var);
                Set<Effect> effectsForeffect158Var = new TreeSet<Effect>();
                effectsForeffect158Var.add(effect158);
                addEffects((Parameter<?>) effect158Var, effectsForeffect158Var);
                Set<Effect> effectsForeffect159Var = new TreeSet<Effect>();
                effectsForeffect159Var.add(effect159);
                addEffects((Parameter<?>) effect159Var, effectsForeffect159Var);
            }

            public void init_17_0_2_1_edc0357_1360798765442_572902_20575Dependencies() {
                addDependency(finalNode_startTime, new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(_17_0_2_1_edc0357_1360798766255_580546_21884, new Expression(new EffectFunction(sig_17_0_2_1_edc0357_1360798765445_206282_20582, ClassUtils.getMethodForArgTypes("ObjectFlow<Float>", "DIFF_SCENARIO", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_1_edc0357_1360798765446_942231_20592, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "DIFF_SCENARIO", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_1_edc0357_1360798765442_572902_20575Elaborations() {
                init_17_0_2_1_edc0357_1360798765442_572902_20575Dependencies();
            }
        }

        public class _17_0_2_1_edc0357_1360798765443_547604_20577 extends DurativeEvent {

            public _17_0_2_1_edc0357_1360798765443_547604_20577() {
                super();
                init_17_0_2_1_edc0357_1360798765443_547604_20577Members();
                init_17_0_2_1_edc0357_1360798765443_547604_20577Collections();
                init_17_0_2_1_edc0357_1360798765443_547604_20577Elaborations();
            }

            public _17_0_2_1_edc0357_1360798765443_547604_20577(Expression<Integer> startTime) {
                super();
                init_17_0_2_1_edc0357_1360798765443_547604_20577Members();
                init_17_0_2_1_edc0357_1360798765443_547604_20577Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_1_edc0357_1360798765443_547604_20577Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter addToDecider__17_0_2_1_edc0357_1360798765444_162511_20578 = null;

            public IntegerParameter myDeciderID_decider_17_0_2_1_edc0357_1360798765444_162511_20578 = null;

            public BooleanParameter objectToPass = null;

            public DoubleParameter _17_0_2_1_edc0357_1360798766256_140761_21885 = null;

            public BooleanParameter _17_0_2_1_edc0357_1360798765444_162511_20578_exists = null;

            public Dependency addToDecider__17_0_2_1_edc0357_1360798765444_162511_20578Dependency = null;

            public Dependency myDeciderID_decider_17_0_2_1_edc0357_1360798765444_162511_20578Dependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Dependency _17_0_2_1_edc0357_1360798766256_140761_21885Dependency = null;

            public Dependency _17_0_2_1_edc0357_1360798765444_162511_20578_existsDependency = null;

            public Effect effect160 = null;

            public Parameter effect160Var = null;

            public Effect effect161 = null;

            public Parameter effect161Var = null;

            public ElaborationRule elaborationRule162 = null;

            public void init_17_0_2_1_edc0357_1360798765443_547604_20577Members() {
                try {
                    if (addToDecider__17_0_2_1_edc0357_1360798765444_162511_20578 == null) addToDecider__17_0_2_1_edc0357_1360798765444_162511_20578 = new BooleanParameter("addToDecider__17_0_2_1_edc0357_1360798765444_162511_20578", (Boolean) false, this);
                    if (myDeciderID_decider_17_0_2_1_edc0357_1360798765444_162511_20578 == null) myDeciderID_decider_17_0_2_1_edc0357_1360798765444_162511_20578 = new IntegerParameter("myDeciderID_decider_17_0_2_1_edc0357_1360798765444_162511_20578", (Integer) 2, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (_17_0_2_1_edc0357_1360798766256_140761_21885 == null) _17_0_2_1_edc0357_1360798766256_140761_21885 = new DoubleParameter("_17_0_2_1_edc0357_1360798766256_140761_21885", (Double) null, this);
                    if (_17_0_2_1_edc0357_1360798765444_162511_20578_exists == null) _17_0_2_1_edc0357_1360798765444_162511_20578_exists = new BooleanParameter("_17_0_2_1_edc0357_1360798765444_162511_20578_exists", (Boolean) false, this);
                    Object effect160VarV = sig_17_0_2_1_edc0357_1360798765445_246064_20588;
                    effect160Var = new Parameter("effect160Var", null, null, this);
                    addDependency(effect160Var, new Expression(effect160VarV));
                    effect160 = new EffectFunction(new EffectFunction(effect160Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Float>", "DIFF_SCENARIO", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Double>(_17_0_2_1_edc0357_1360798766256_140761_21885), new Expression<Integer>(endTime) }));
                    Object effect161VarV = decider_17_0_2_1_edc0357_1360798765444_162511_20578;
                    effect161Var = new Parameter("effect161Var", null, null, this);
                    addDependency(effect161Var, new Expression(effect161VarV));
                    effect161 = new EffectFunction(new EffectFunction(effect161Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "DIFF_SCENARIO", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class, java.lang.Boolean.class), new Object[] { new Expression<Integer>(endTime), new Expression<Integer>(myDeciderID_decider_17_0_2_1_edc0357_1360798765444_162511_20578), new Expression<Boolean>(addToDecider__17_0_2_1_edc0357_1360798765444_162511_20578) }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_1_edc0357_1360798765443_547604_20577Collections() {
                parameters.add(addToDecider__17_0_2_1_edc0357_1360798765444_162511_20578);
                parameters.add(myDeciderID_decider_17_0_2_1_edc0357_1360798765444_162511_20578);
                parameters.add(objectToPass);
                parameters.add(_17_0_2_1_edc0357_1360798766256_140761_21885);
                parameters.add(_17_0_2_1_edc0357_1360798765444_162511_20578_exists);
                Set<Effect> effectsForeffect160Var = new TreeSet<Effect>();
                effectsForeffect160Var.add(effect160);
                addEffects((Parameter<?>) effect160Var, effectsForeffect160Var);
                Set<Effect> effectsForeffect161Var = new TreeSet<Effect>();
                effectsForeffect161Var.add(effect161);
                addEffects((Parameter<?>) effect161Var, effectsForeffect161Var);
            }

            public void init_17_0_2_1_edc0357_1360798765443_547604_20577Dependencies() {
                addDependency(addToDecider__17_0_2_1_edc0357_1360798765444_162511_20578, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765440_675335_20573_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765440_675335_20573_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(myDeciderID_decider_17_0_2_1_edc0357_1360798765444_162511_20578, new Expression<Integer>(2));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_1_edc0357_1360878970443_16585_15933, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "DIFF_SCENARIO", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_1_edc0357_1360798766256_140761_21885, new Expression(new EffectFunction(powerAugmentation__17_0_2_1_edc0357_1360798765029_789574_20260, ClassUtils.getMethodForArgTypes("TimeVaryingMap<Float>", "DIFF_SCENARIO", "getValue", java.lang.Integer.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_1_edc0357_1360798765444_162511_20578_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765440_675335_20573_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765440_675335_20573_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_1_edc0357_1360798765444_162511_20578, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "DIFF_SCENARIO", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_1_edc0357_1360798765444_162511_20578, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "DIFF_SCENARIO", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_1_edc0357_1360798765444_162511_20578, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "DIFF_SCENARIO", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_1_edc0357_1360798765444_162511_20578)))))))));
            }

            public void init_17_0_2_1_edc0357_1360798765443_547604_20577Elaborations() {
                init_17_0_2_1_edc0357_1360798765443_547604_20577Dependencies();
                Expression<?>[] arguments162 = new Expression<?>[1];
                arguments162[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition162 = new Expression<Boolean>(_17_0_2_1_edc0357_1360798765444_162511_20578_exists);
                elaborationRule162 = addElaborationRule(condition162, _17_0_2_1_edc0357_1360798765021_125160_20250.this, Power._17_0_2_1_edc0357_1360798765021_125160_20250._17_0_2_1_edc0357_1360798765444_162511_20578.class, "PGen_add_augment__CallBehaviorAction__generate__Power", arguments162);
            }
        }

        public class _17_0_2_1_edc0357_1360798765444_162511_20578 extends DurativeEvent {

            public _17_0_2_1_edc0357_1360798765444_162511_20578() {
                super();
                init_17_0_2_1_edc0357_1360798765444_162511_20578Members();
                init_17_0_2_1_edc0357_1360798765444_162511_20578Collections();
                init_17_0_2_1_edc0357_1360798765444_162511_20578Elaborations();
            }

            public _17_0_2_1_edc0357_1360798765444_162511_20578(Expression<Integer> startTime) {
                super();
                init_17_0_2_1_edc0357_1360798765444_162511_20578Members();
                init_17_0_2_1_edc0357_1360798765444_162511_20578Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_1_edc0357_1360798765444_162511_20578Elaborations();
                fixTimeDependencies();
            }

            public DoubleParameter total = null;

            public DoubleParameter aug = null;

            public DoubleParameter _17_0_2_1_edc0357_1360798766257_118217_21888 = null;

            public BooleanParameter _17_0_2_1_edc0357_1360798765444_561758_20579_exists = null;

            public DoubleParameter _17_0_2_1_edc0357_1360798766258_891416_21889 = null;

            public DoubleParameter _17_0_2_1_edc0357_1360798766257_871897_21887 = null;

            public DoubleParameter gen = null;

            public Dependency totalDependency = null;

            public Dependency augDependency = null;

            public Dependency _17_0_2_1_edc0357_1360798766257_118217_21888Dependency = null;

            public Dependency _17_0_2_1_edc0357_1360798765444_561758_20579_existsDependency = null;

            public Dependency durationDependency = null;

            public Dependency _17_0_2_1_edc0357_1360798766257_871897_21887Dependency = null;

            public Dependency _17_0_2_1_edc0357_1360798766258_891416_21889Dependency = null;

            public Dependency genDependency = null;

            public Effect effect163 = null;

            public Parameter effect163Var = null;

            public ElaborationRule elaborationRule164 = null;

            public void init_17_0_2_1_edc0357_1360798765444_162511_20578Members() {
                try {
                    if (total == null) total = new DoubleParameter("total", (Double) null, this);
                    if (aug == null) aug = new DoubleParameter("aug", (Double) null, this);
                    if (_17_0_2_1_edc0357_1360798766257_118217_21888 == null) _17_0_2_1_edc0357_1360798766257_118217_21888 = new DoubleParameter("_17_0_2_1_edc0357_1360798766257_118217_21888", (Double) null, this);
                    if (_17_0_2_1_edc0357_1360798765444_561758_20579_exists == null) _17_0_2_1_edc0357_1360798765444_561758_20579_exists = new BooleanParameter("_17_0_2_1_edc0357_1360798765444_561758_20579_exists", (Boolean) false, this);
                    if (_17_0_2_1_edc0357_1360798766258_891416_21889 == null) _17_0_2_1_edc0357_1360798766258_891416_21889 = new DoubleParameter("_17_0_2_1_edc0357_1360798766258_891416_21889", (Double) null, this);
                    if (_17_0_2_1_edc0357_1360798766257_871897_21887 == null) _17_0_2_1_edc0357_1360798766257_871897_21887 = new DoubleParameter("_17_0_2_1_edc0357_1360798766257_871897_21887", (Double) null, this);
                    if (gen == null) gen = new DoubleParameter("gen", (Double) null, this);
                    Object effect163VarV = sig_17_0_2_1_edc0357_1360798765446_227708_20589;
                    effect163Var = new Parameter("effect163Var", null, null, this);
                    addDependency(effect163Var, new Expression(effect163VarV));
                    effect163 = new EffectFunction(new EffectFunction(effect163Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Float>", "DIFF_SCENARIO", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Double>(_17_0_2_1_edc0357_1360798766257_871897_21887), new Expression<Integer>(endTime) }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_1_edc0357_1360798765444_162511_20578Collections() {
                parameters.add(total);
                parameters.add(aug);
                parameters.add(_17_0_2_1_edc0357_1360798766257_118217_21888);
                parameters.add(_17_0_2_1_edc0357_1360798765444_561758_20579_exists);
                parameters.add(_17_0_2_1_edc0357_1360798766258_891416_21889);
                parameters.add(_17_0_2_1_edc0357_1360798766257_871897_21887);
                parameters.add(gen);
                Set<Effect> effectsForeffect163Var = new TreeSet<Effect>();
                effectsForeffect163Var.add(effect163);
                addEffects((Parameter<?>) effect163Var, effectsForeffect163Var);
            }

            public void init_17_0_2_1_edc0357_1360798765444_162511_20578Dependencies() {
                addDependency(total, new Expression<Double>(new Functions.Plus(new Expression<Double>(gen), new Expression<Double>(aug))));
                addDependency(aug, new Expression<Double>(_17_0_2_1_edc0357_1360798766258_891416_21889));
                addDependency(_17_0_2_1_edc0357_1360798766257_118217_21888, new Expression(new EffectFunction(sig_17_0_2_1_edc0357_1360798765445_230375_20587, ClassUtils.getMethodForArgTypes("ObjectFlow<Float>", "DIFF_SCENARIO", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_1_edc0357_1360798765444_561758_20579_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765440_675335_20573_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765440_675335_20573_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(_17_0_2_1_edc0357_1360798766257_871897_21887, new Expression<Double>(total));
                addDependency(_17_0_2_1_edc0357_1360798766258_891416_21889, new Expression(new EffectFunction(sig_17_0_2_1_edc0357_1360798765445_246064_20588, ClassUtils.getMethodForArgTypes("ObjectFlow<Float>", "DIFF_SCENARIO", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(gen, new Expression<Double>(_17_0_2_1_edc0357_1360798766257_118217_21888));
            }

            public void init_17_0_2_1_edc0357_1360798765444_162511_20578Elaborations() {
                init_17_0_2_1_edc0357_1360798765444_162511_20578Dependencies();
                Expression<?>[] arguments164 = new Expression<?>[1];
                arguments164[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition164 = new Expression<Boolean>(_17_0_2_1_edc0357_1360798765444_561758_20579_exists);
                elaborationRule164 = addElaborationRule(condition164, _17_0_2_1_edc0357_1360798765021_125160_20250.this, Power._17_0_2_1_edc0357_1360798765021_125160_20250._17_0_2_1_edc0357_1360798765444_561758_20579.class, "f_gen__ForkNode__generate__Power", arguments164);
            }
        }

        public class _17_0_2_1_edc0357_1360798765444_561758_20579 extends DurativeEvent {

            public _17_0_2_1_edc0357_1360798765444_561758_20579() {
                super();
                init_17_0_2_1_edc0357_1360798765444_561758_20579Members();
                init_17_0_2_1_edc0357_1360798765444_561758_20579Collections();
                init_17_0_2_1_edc0357_1360798765444_561758_20579Elaborations();
            }

            public _17_0_2_1_edc0357_1360798765444_561758_20579(Expression<Integer> startTime) {
                super();
                init_17_0_2_1_edc0357_1360798765444_561758_20579Members();
                init_17_0_2_1_edc0357_1360798765444_561758_20579Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_1_edc0357_1360798765444_561758_20579Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_1_edc0357_1360798765442_572902_20575_exists = null;

            public BooleanParameter addToDecider__17_0_2_1_edc0357_1360798765442_572902_20575 = null;

            public BooleanParameter _17_0_2_1_edc0357_1360798765444_866435_20580_exists = null;

            public DoubleParameter objectToPass = null;

            public IntegerParameter myDeciderID_decider_17_0_2_1_edc0357_1360798765442_572902_20575 = null;

            public Dependency _17_0_2_1_edc0357_1360798765442_572902_20575_existsDependency = null;

            public Dependency addToDecider__17_0_2_1_edc0357_1360798765442_572902_20575Dependency = null;

            public Dependency _17_0_2_1_edc0357_1360798765444_866435_20580_existsDependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Dependency myDeciderID_decider_17_0_2_1_edc0357_1360798765442_572902_20575Dependency = null;

            public Effect effect165 = null;

            public Parameter effect165Var = null;

            public Effect effect166 = null;

            public Parameter effect166Var = null;

            public Effect effect167 = null;

            public Parameter effect167Var = null;

            public ElaborationRule elaborationRule168 = null;

            public ElaborationRule elaborationRule169 = null;

            public void init_17_0_2_1_edc0357_1360798765444_561758_20579Members() {
                try {
                    if (_17_0_2_1_edc0357_1360798765442_572902_20575_exists == null) _17_0_2_1_edc0357_1360798765442_572902_20575_exists = new BooleanParameter("_17_0_2_1_edc0357_1360798765442_572902_20575_exists", (Boolean) false, this);
                    if (addToDecider__17_0_2_1_edc0357_1360798765442_572902_20575 == null) addToDecider__17_0_2_1_edc0357_1360798765442_572902_20575 = new BooleanParameter("addToDecider__17_0_2_1_edc0357_1360798765442_572902_20575", (Boolean) false, this);
                    if (_17_0_2_1_edc0357_1360798765444_866435_20580_exists == null) _17_0_2_1_edc0357_1360798765444_866435_20580_exists = new BooleanParameter("_17_0_2_1_edc0357_1360798765444_866435_20580_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new DoubleParameter("objectToPass", (Double) null, this);
                    if (myDeciderID_decider_17_0_2_1_edc0357_1360798765442_572902_20575 == null) myDeciderID_decider_17_0_2_1_edc0357_1360798765442_572902_20575 = new IntegerParameter("myDeciderID_decider_17_0_2_1_edc0357_1360798765442_572902_20575", (Integer) 1, this);
                    Object effect165VarV = sig_17_0_2_1_edc0357_1360798765445_206282_20582;
                    effect165Var = new Parameter("effect165Var", null, null, this);
                    addDependency(effect165Var, new Expression(effect165VarV));
                    effect165 = new EffectFunction(new EffectFunction(effect165Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Float>", "DIFF_SCENARIO", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Double>(objectToPass), new Expression<Integer>(endTime) }));
                    Object effect166VarV = sig_17_0_2_1_edc0357_1360798765446_108540_20591;
                    effect166Var = new Parameter("effect166Var", null, null, this);
                    addDependency(effect166Var, new Expression(effect166VarV));
                    effect166 = new EffectFunction(new EffectFunction(effect166Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Float>", "DIFF_SCENARIO", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Double>(objectToPass), new Expression<Integer>(endTime) }));
                    Object effect167VarV = decider_17_0_2_1_edc0357_1360798765442_572902_20575;
                    effect167Var = new Parameter("effect167Var", null, null, this);
                    addDependency(effect167Var, new Expression(effect167VarV));
                    effect167 = new EffectFunction(new EffectFunction(effect167Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "DIFF_SCENARIO", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class, java.lang.Boolean.class), new Object[] { new Expression<Integer>(endTime), new Expression<Integer>(myDeciderID_decider_17_0_2_1_edc0357_1360798765442_572902_20575), new Expression<Boolean>(addToDecider__17_0_2_1_edc0357_1360798765442_572902_20575) }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_1_edc0357_1360798765444_561758_20579Collections() {
                parameters.add(_17_0_2_1_edc0357_1360798765442_572902_20575_exists);
                parameters.add(addToDecider__17_0_2_1_edc0357_1360798765442_572902_20575);
                parameters.add(_17_0_2_1_edc0357_1360798765444_866435_20580_exists);
                parameters.add(objectToPass);
                parameters.add(myDeciderID_decider_17_0_2_1_edc0357_1360798765442_572902_20575);
                Set<Effect> effectsForeffect165Var = new TreeSet<Effect>();
                effectsForeffect165Var.add(effect165);
                addEffects((Parameter<?>) effect165Var, effectsForeffect165Var);
                Set<Effect> effectsForeffect166Var = new TreeSet<Effect>();
                effectsForeffect166Var.add(effect166);
                addEffects((Parameter<?>) effect166Var, effectsForeffect166Var);
                Set<Effect> effectsForeffect167Var = new TreeSet<Effect>();
                effectsForeffect167Var.add(effect167);
                addEffects((Parameter<?>) effect167Var, effectsForeffect167Var);
            }

            public void init_17_0_2_1_edc0357_1360798765444_561758_20579Dependencies() {
                addDependency(_17_0_2_1_edc0357_1360798765442_572902_20575_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765440_675335_20573_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765440_675335_20573_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_1_edc0357_1360798765442_572902_20575, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "DIFF_SCENARIO", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_1_edc0357_1360798765442_572902_20575, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "DIFF_SCENARIO", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_1_edc0357_1360798765442_572902_20575, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "DIFF_SCENARIO", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_1_edc0357_1360798765442_572902_20575)))))))));
                addDependency(addToDecider__17_0_2_1_edc0357_1360798765442_572902_20575, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765440_675335_20573_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765440_675335_20573_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(_17_0_2_1_edc0357_1360798765444_866435_20580_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765440_675335_20573_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765440_675335_20573_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_1_edc0357_1360798765446_227708_20589, ClassUtils.getMethodForArgTypes("ObjectFlow<Float>", "DIFF_SCENARIO", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(myDeciderID_decider_17_0_2_1_edc0357_1360798765442_572902_20575, new Expression<Integer>(1));
            }

            public void init_17_0_2_1_edc0357_1360798765444_561758_20579Elaborations() {
                init_17_0_2_1_edc0357_1360798765444_561758_20579Dependencies();
                Expression<?>[] arguments168 = new Expression<?>[1];
                arguments168[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition168 = new Expression<Boolean>(_17_0_2_1_edc0357_1360798765442_572902_20575_exists);
                elaborationRule168 = addElaborationRule(condition168, _17_0_2_1_edc0357_1360798765021_125160_20250.this, Power._17_0_2_1_edc0357_1360798765021_125160_20250._17_0_2_1_edc0357_1360798765442_572902_20575.class, "PGen_send_gen__SendSignalAction__generate__Power", arguments168);
                Expression<?>[] arguments169 = new Expression<?>[1];
                arguments169[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition169 = new Expression<Boolean>(_17_0_2_1_edc0357_1360798765444_866435_20580_exists);
                elaborationRule169 = addElaborationRule(condition169, _17_0_2_1_edc0357_1360798765021_125160_20250.this, Power._17_0_2_1_edc0357_1360798765021_125160_20250._17_0_2_1_edc0357_1360798765444_866435_20580.class, "setGeneration__AddStructuralFeatureValueAction__generate__Power", arguments169);
            }
        }

        public class _17_0_2_1_edc0357_1360798765444_866435_20580 extends DurativeEvent {

            public _17_0_2_1_edc0357_1360798765444_866435_20580() {
                super();
                init_17_0_2_1_edc0357_1360798765444_866435_20580Members();
                init_17_0_2_1_edc0357_1360798765444_866435_20580Collections();
                init_17_0_2_1_edc0357_1360798765444_866435_20580Elaborations();
            }

            public _17_0_2_1_edc0357_1360798765444_866435_20580(Expression<Integer> startTime) {
                super();
                init_17_0_2_1_edc0357_1360798765444_866435_20580Members();
                init_17_0_2_1_edc0357_1360798765444_866435_20580Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_1_edc0357_1360798765444_866435_20580Elaborations();
                fixTimeDependencies();
            }

            public DoubleParameter _17_0_2_1_edc0357_1360798766259_77770_21890 = null;

            public BooleanParameter _17_0_2_1_edc0357_1360798765442_572902_20575_exists = null;

            public BooleanParameter addToDecider__17_0_2_1_edc0357_1360798765442_572902_20575 = null;

            public BooleanParameter objectToPass = null;

            public IntegerParameter myDeciderID_decider_17_0_2_1_edc0357_1360798765442_572902_20575 = null;

            public Dependency _17_0_2_1_edc0357_1360798766259_77770_21890Dependency = null;

            public Dependency _17_0_2_1_edc0357_1360798765442_572902_20575_existsDependency = null;

            public Dependency addToDecider__17_0_2_1_edc0357_1360798765442_572902_20575Dependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Dependency myDeciderID_decider_17_0_2_1_edc0357_1360798765442_572902_20575Dependency = null;

            public Effect effect170 = null;

            public Parameter effect170Var = null;

            public Effect effect171 = null;

            public Parameter effect171Var = null;

            public Effect effect172 = null;

            public Parameter effect172Var = null;

            public ElaborationRule elaborationRule173 = null;

            public void init_17_0_2_1_edc0357_1360798765444_866435_20580Members() {
                try {
                    if (_17_0_2_1_edc0357_1360798766259_77770_21890 == null) _17_0_2_1_edc0357_1360798766259_77770_21890 = new DoubleParameter("_17_0_2_1_edc0357_1360798766259_77770_21890", (Double) null, this);
                    if (_17_0_2_1_edc0357_1360798765442_572902_20575_exists == null) _17_0_2_1_edc0357_1360798765442_572902_20575_exists = new BooleanParameter("_17_0_2_1_edc0357_1360798765442_572902_20575_exists", (Boolean) false, this);
                    if (addToDecider__17_0_2_1_edc0357_1360798765442_572902_20575 == null) addToDecider__17_0_2_1_edc0357_1360798765442_572902_20575 = new BooleanParameter("addToDecider__17_0_2_1_edc0357_1360798765442_572902_20575", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (myDeciderID_decider_17_0_2_1_edc0357_1360798765442_572902_20575 == null) myDeciderID_decider_17_0_2_1_edc0357_1360798765442_572902_20575 = new IntegerParameter("myDeciderID_decider_17_0_2_1_edc0357_1360798765442_572902_20575", (Integer) 2, this);
                    Object effect170VarV = sig_17_0_2_1_edc0357_1360798765446_942231_20592;
                    effect170Var = new Parameter("effect170Var", null, null, this);
                    addDependency(effect170Var, new Expression(effect170VarV));
                    effect170 = new EffectFunction(new EffectFunction(effect170Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "DIFF_SCENARIO", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Boolean>(objectToPass), new Expression<Integer>(endTime) }));
                    Object effect171VarV = decider_17_0_2_1_edc0357_1360798765442_572902_20575;
                    effect171Var = new Parameter("effect171Var", null, null, this);
                    addDependency(effect171Var, new Expression(effect171VarV));
                    effect171 = new EffectFunction(new EffectFunction(effect171Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "DIFF_SCENARIO", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class, java.lang.Boolean.class), new Object[] { new Expression<Integer>(endTime), new Expression<Integer>(myDeciderID_decider_17_0_2_1_edc0357_1360798765442_572902_20575), new Expression<Boolean>(addToDecider__17_0_2_1_edc0357_1360798765442_572902_20575) }));
                    Object effect172VarV = power__17_0_2_1_edc0357_1360798765025_253187_20253;
                    effect172Var = new Parameter("effect172Var", null, null, this);
                    addDependency(effect172Var, new Expression(effect172VarV));
                    effect172 = new EffectFunction(new EffectFunction(effect172Var, ClassUtils.getMethodForArgTypes("TimeVaryingPlottableMap<Float>", "DIFF_SCENARIO", "setValue", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { new Expression<Integer>(startTime), new Expression<Double>(_17_0_2_1_edc0357_1360798766259_77770_21890) }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_1_edc0357_1360798765444_866435_20580Collections() {
                parameters.add(_17_0_2_1_edc0357_1360798766259_77770_21890);
                parameters.add(_17_0_2_1_edc0357_1360798765442_572902_20575_exists);
                parameters.add(addToDecider__17_0_2_1_edc0357_1360798765442_572902_20575);
                parameters.add(objectToPass);
                parameters.add(myDeciderID_decider_17_0_2_1_edc0357_1360798765442_572902_20575);
                Set<Effect> effectsForeffect170Var = new TreeSet<Effect>();
                effectsForeffect170Var.add(effect170);
                addEffects((Parameter<?>) effect170Var, effectsForeffect170Var);
                Set<Effect> effectsForeffect171Var = new TreeSet<Effect>();
                effectsForeffect171Var.add(effect171);
                addEffects((Parameter<?>) effect171Var, effectsForeffect171Var);
                Set<Effect> effectsForeffect172Var = new TreeSet<Effect>();
                effectsForeffect172Var.add(effect172);
                addEffects((Parameter<?>) effect172Var, effectsForeffect172Var);
            }

            public void init_17_0_2_1_edc0357_1360798765444_866435_20580Dependencies() {
                addDependency(_17_0_2_1_edc0357_1360798766259_77770_21890, new Expression(new EffectFunction(sig_17_0_2_1_edc0357_1360798765446_108540_20591, ClassUtils.getMethodForArgTypes("ObjectFlow<Float>", "DIFF_SCENARIO", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_1_edc0357_1360798765442_572902_20575_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765440_675335_20573_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765440_675335_20573_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_1_edc0357_1360798765442_572902_20575, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "DIFF_SCENARIO", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_1_edc0357_1360798765442_572902_20575, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "DIFF_SCENARIO", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_1_edc0357_1360798765442_572902_20575, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "DIFF_SCENARIO", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_1_edc0357_1360798765442_572902_20575)))))))));
                addDependency(addToDecider__17_0_2_1_edc0357_1360798765442_572902_20575, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765440_675335_20573_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765440_675335_20573_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(true));
                addDependency(myDeciderID_decider_17_0_2_1_edc0357_1360798765442_572902_20575, new Expression<Integer>(2));
            }

            public void init_17_0_2_1_edc0357_1360798765444_866435_20580Elaborations() {
                init_17_0_2_1_edc0357_1360798765444_866435_20580Dependencies();
                Expression<?>[] arguments173 = new Expression<?>[1];
                arguments173[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition173 = new Expression<Boolean>(_17_0_2_1_edc0357_1360798765442_572902_20575_exists);
                elaborationRule173 = addElaborationRule(condition173, _17_0_2_1_edc0357_1360798765021_125160_20250.this, Power._17_0_2_1_edc0357_1360798765021_125160_20250._17_0_2_1_edc0357_1360798765442_572902_20575.class, "PGen_send_gen__SendSignalAction__generate__Power", arguments173);
            }
        }

        public _17_0_2_1_edc0357_1360798765021_125160_20250(Expression<Integer> startTime, Expression<DurativeEvent> caller) {
            super();
            init_17_0_2_1_edc0357_1360798765021_125160_20250Members();
            init_17_0_2_1_edc0357_1360798765021_125160_20250Collections();
            addDependency(this.startTime, startTime);
            addDependency(this.caller, caller);
            init_17_0_2_1_edc0357_1360798765021_125160_20250Elaborations();
            fixTimeDependencies();
        }

        public Parameter< ObjectFlow<Float> > sig_17_0_2_1_edc0357_1360798765446_227708_20589 = null;

        public Parameter< ObjectFlow<Float> > sig_17_0_2_1_edc0357_1360798765445_230375_20587 = null;

        public Parameter< ObjectFlow<Float> > sig_17_0_2_1_edc0357_1360798766253_187998_21879 = null;

        public Parameter< TimeVaryingList<Integer> > decider_17_0_2_1_edc0357_1360798765444_162511_20578 = null;

        public Parameter< TimeVaryingList<Integer> > decider_17_0_2_1_edc0357_1360798765442_572902_20575 = null;

        public Parameter< DurativeEvent > caller = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_1_edc0357_1360798765446_873055_20593 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_1_edc0357_1360798765446_942231_20592 = null;

        public Parameter< ObjectFlow<Float> > sig_17_0_2_1_edc0357_1360798765445_206282_20582 = null;

        public IntegerParameter finalNode_startTime = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_1_edc0357_1360878965913_511003_15928 = null;

        public IntegerParameter finalNode_endTime = null;

        public Parameter< ObjectFlow<Float> > sig_17_0_2_1_edc0357_1360798765445_246064_20588 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_1_edc0357_1360878970443_16585_15933 = null;

        public BooleanParameter _17_0_2_1_edc0357_1360798765440_675335_20573_exists = null;

        public Parameter< ObjectFlow<Float> > sig_17_0_2_1_edc0357_1360798765446_108540_20591 = null;

        public Dependency caller_endTimeDependency = null;

        public Dependency endTimeDependency = null;

        public Dependency _17_0_2_1_edc0357_1360798765440_675335_20573_existsDependency = null;

        public ElaborationRule elaborationRule148 = null;

        public ElaborationRule elaborationRule149 = null;

        public void init_17_0_2_1_edc0357_1360798765021_125160_20250Members() {
            try {
                if (sig_17_0_2_1_edc0357_1360798765446_227708_20589 == null) sig_17_0_2_1_edc0357_1360798765446_227708_20589 = new Parameter<ObjectFlow<Float>>("sig_17_0_2_1_edc0357_1360798765446_227708_20589", null, (ObjectFlow<Float>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360798765446_227708_20589" })).evaluate(true), this);
                if (sig_17_0_2_1_edc0357_1360798765445_230375_20587 == null) sig_17_0_2_1_edc0357_1360798765445_230375_20587 = new Parameter<ObjectFlow<Float>>("sig_17_0_2_1_edc0357_1360798765445_230375_20587", null, (ObjectFlow<Float>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360798765445_230375_20587" })).evaluate(true), this);
                if (sig_17_0_2_1_edc0357_1360798766253_187998_21879 == null) sig_17_0_2_1_edc0357_1360798766253_187998_21879 = new Parameter<ObjectFlow<Float>>("sig_17_0_2_1_edc0357_1360798766253_187998_21879", null, (ObjectFlow<Float>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360798766253_187998_21879" })).evaluate(true), this);
                if (decider_17_0_2_1_edc0357_1360798765444_162511_20578 == null) decider_17_0_2_1_edc0357_1360798765444_162511_20578 = new Parameter<TimeVaryingList<Integer>>("decider_17_0_2_1_edc0357_1360798765444_162511_20578", null, (TimeVaryingList<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingList.class, java.lang.String.class, int.class), new Object[] { "decider_17_0_2_1_edc0357_1360798765444_162511_20578", 2 })).evaluate(true), this);
                if (decider_17_0_2_1_edc0357_1360798765442_572902_20575 == null) decider_17_0_2_1_edc0357_1360798765442_572902_20575 = new Parameter<TimeVaryingList<Integer>>("decider_17_0_2_1_edc0357_1360798765442_572902_20575", null, (TimeVaryingList<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingList.class, java.lang.String.class, int.class), new Object[] { "decider_17_0_2_1_edc0357_1360798765442_572902_20575", 2 })).evaluate(true), this);
                if (caller == null) caller = new Parameter<DurativeEvent>("caller", null, (DurativeEvent) null, this);
                if (sig_17_0_2_1_edc0357_1360798765446_873055_20593 == null) sig_17_0_2_1_edc0357_1360798765446_873055_20593 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_1_edc0357_1360798765446_873055_20593", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360798765446_873055_20593" })).evaluate(true), this);
                if (sig_17_0_2_1_edc0357_1360798765446_942231_20592 == null) sig_17_0_2_1_edc0357_1360798765446_942231_20592 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_1_edc0357_1360798765446_942231_20592", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360798765446_942231_20592" })).evaluate(true), this);
                if (sig_17_0_2_1_edc0357_1360798765445_206282_20582 == null) sig_17_0_2_1_edc0357_1360798765445_206282_20582 = new Parameter<ObjectFlow<Float>>("sig_17_0_2_1_edc0357_1360798765445_206282_20582", null, (ObjectFlow<Float>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360798765445_206282_20582" })).evaluate(true), this);
                if (finalNode_startTime == null) finalNode_startTime = new IntegerParameter("finalNode_startTime", (Integer) null, this);
                if (sig_17_0_2_1_edc0357_1360878965913_511003_15928 == null) sig_17_0_2_1_edc0357_1360878965913_511003_15928 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_1_edc0357_1360878965913_511003_15928", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360878965913_511003_15928" })).evaluate(true), this);
                if (finalNode_endTime == null) finalNode_endTime = new IntegerParameter("finalNode_endTime", (Integer) null, this);
                if (sig_17_0_2_1_edc0357_1360798765445_246064_20588 == null) sig_17_0_2_1_edc0357_1360798765445_246064_20588 = new Parameter<ObjectFlow<Float>>("sig_17_0_2_1_edc0357_1360798765445_246064_20588", null, (ObjectFlow<Float>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360798765445_246064_20588" })).evaluate(true), this);
                if (sig_17_0_2_1_edc0357_1360878970443_16585_15933 == null) sig_17_0_2_1_edc0357_1360878970443_16585_15933 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_1_edc0357_1360878970443_16585_15933", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360878970443_16585_15933" })).evaluate(true), this);
                if (_17_0_2_1_edc0357_1360798765440_675335_20573_exists == null) _17_0_2_1_edc0357_1360798765440_675335_20573_exists = new BooleanParameter("_17_0_2_1_edc0357_1360798765440_675335_20573_exists", (Boolean) false, this);
                if (sig_17_0_2_1_edc0357_1360798765446_108540_20591 == null) sig_17_0_2_1_edc0357_1360798765446_108540_20591 = new Parameter<ObjectFlow<Float>>("sig_17_0_2_1_edc0357_1360798765446_108540_20591", null, (ObjectFlow<Float>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360798765446_108540_20591" })).evaluate(true), this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void init_17_0_2_1_edc0357_1360798765021_125160_20250Collections() {
            parameters.add(sig_17_0_2_1_edc0357_1360798765446_227708_20589);
            parameters.add(sig_17_0_2_1_edc0357_1360798765445_230375_20587);
            parameters.add(sig_17_0_2_1_edc0357_1360798766253_187998_21879);
            parameters.add(decider_17_0_2_1_edc0357_1360798765444_162511_20578);
            parameters.add(decider_17_0_2_1_edc0357_1360798765442_572902_20575);
            parameters.add(caller);
            parameters.add(sig_17_0_2_1_edc0357_1360798765446_873055_20593);
            parameters.add(sig_17_0_2_1_edc0357_1360798765446_942231_20592);
            parameters.add(sig_17_0_2_1_edc0357_1360798765445_206282_20582);
            parameters.add(finalNode_startTime);
            parameters.add(sig_17_0_2_1_edc0357_1360878965913_511003_15928);
            parameters.add(finalNode_endTime);
            parameters.add(sig_17_0_2_1_edc0357_1360798765445_246064_20588);
            parameters.add(sig_17_0_2_1_edc0357_1360878970443_16585_15933);
            parameters.add(_17_0_2_1_edc0357_1360798765440_675335_20573_exists);
            parameters.add(sig_17_0_2_1_edc0357_1360798765446_108540_20591);
        }

        public void init_17_0_2_1_edc0357_1360798765021_125160_20250Dependencies() {
            caller.getValue(true).addDependency(caller.getValue(true).endTime, new Expression<Integer>(finalNode_endTime));
            addDependency(endTime, new Expression<Integer>(finalNode_endTime));
            addDependency(_17_0_2_1_edc0357_1360798765440_675335_20573_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.NotEquals(new Expression<Integer>(finalNode_startTime), null)), new Expression(new EffectFunction(sig_17_0_2_1_edc0357_1360798765446_873055_20593, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "DIFF_SCENARIO", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(new Functions.Minus(new Expression<Integer>(finalNode_startTime), new Expression<Integer>(1))) })))));
        }

        public void init_17_0_2_1_edc0357_1360798765021_125160_20250Elaborations() {
            init_17_0_2_1_edc0357_1360798765021_125160_20250Dependencies();
            Expression<?>[] arguments148 = new Expression<?>[1];
            arguments148[0] = new Expression<Integer>(startTime);
            Expression<Boolean> condition148 = new Expression<Boolean>(true);
            elaborationRule148 = addElaborationRule(condition148, _17_0_2_1_edc0357_1360798765021_125160_20250.this, Power._17_0_2_1_edc0357_1360798765021_125160_20250._17_0_2_1_edc0357_1360798765440_273045_20572.class, "PGen_start__InitialNode__generate__Power", arguments148);
            Expression<?>[] arguments149 = new Expression<?>[1];
            arguments149[0] = new Expression<Integer>(finalNode_startTime);
            Expression<Boolean> condition149 = new Expression<Boolean>(_17_0_2_1_edc0357_1360798765440_675335_20573_exists);
            elaborationRule149 = addElaborationRule(condition149, _17_0_2_1_edc0357_1360798765021_125160_20250.this, Power._17_0_2_1_edc0357_1360798765021_125160_20250._17_0_2_1_edc0357_1360798765440_675335_20573.class, "PGen_final__ActivityFinalNode__generate__Power", arguments149);
        }
    }

    public class _17_0_2_1_edc0357_1360798765022_187244_20251 extends DurativeEvent {

        public _17_0_2_1_edc0357_1360798765022_187244_20251() {
            super();
            init_17_0_2_1_edc0357_1360798765022_187244_20251Members();
            init_17_0_2_1_edc0357_1360798765022_187244_20251Collections();
            init_17_0_2_1_edc0357_1360798765022_187244_20251Elaborations();
        }

        public class _17_0_2_1_edc0357_1360798765461_594674_20615 extends DurativeEvent {

            public _17_0_2_1_edc0357_1360798765461_594674_20615() {
                super();
                init_17_0_2_1_edc0357_1360798765461_594674_20615Members();
                init_17_0_2_1_edc0357_1360798765461_594674_20615Collections();
                init_17_0_2_1_edc0357_1360798765461_594674_20615Elaborations();
            }

            public _17_0_2_1_edc0357_1360798765461_594674_20615(Expression<Integer> startTime) {
                super();
                init_17_0_2_1_edc0357_1360798765461_594674_20615Members();
                init_17_0_2_1_edc0357_1360798765461_594674_20615Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_1_edc0357_1360798765461_594674_20615Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_1_edc0357_1360798765462_670651_20617_exists = null;

            public BooleanParameter objectToPass = null;

            public Dependency _17_0_2_1_edc0357_1360798765462_670651_20617_existsDependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Effect effect176 = null;

            public Parameter effect176Var = null;

            public ElaborationRule elaborationRule177 = null;

            public void init_17_0_2_1_edc0357_1360798765461_594674_20615Members() {
                try {
                    if (_17_0_2_1_edc0357_1360798765462_670651_20617_exists == null) _17_0_2_1_edc0357_1360798765462_670651_20617_exists = new BooleanParameter("_17_0_2_1_edc0357_1360798765462_670651_20617_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect176VarV = sig_17_0_2_1_edc0357_1360798765475_844019_20645;
                    effect176Var = new Parameter("effect176Var", null, null, this);
                    addDependency(effect176Var, new Expression(effect176VarV));
                    effect176 = new EffectFunction(new EffectFunction(effect176Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "DIFF_SCENARIO", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Boolean>(objectToPass), new Expression<Integer>(endTime) }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_1_edc0357_1360798765461_594674_20615Collections() {
                parameters.add(_17_0_2_1_edc0357_1360798765462_670651_20617_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect176Var = new TreeSet<Effect>();
                effectsForeffect176Var.add(effect176);
                addEffects((Parameter<?>) effect176Var, effectsForeffect176Var);
            }

            public void init_17_0_2_1_edc0357_1360798765461_594674_20615Dependencies() {
                addDependency(_17_0_2_1_edc0357_1360798765462_670651_20617_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765468_931127_20626_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765468_931127_20626_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(true));
            }

            public void init_17_0_2_1_edc0357_1360798765461_594674_20615Elaborations() {
                init_17_0_2_1_edc0357_1360798765461_594674_20615Dependencies();
                Expression<?>[] arguments177 = new Expression<?>[1];
                arguments177[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition177 = new Expression<Boolean>(_17_0_2_1_edc0357_1360798765462_670651_20617_exists);
                elaborationRule177 = addElaborationRule(condition177, _17_0_2_1_edc0357_1360798765022_187244_20251.this, Power._17_0_2_1_edc0357_1360798765022_187244_20251._17_0_2_1_edc0357_1360798765462_670651_20617.class, "f_P__ForkNode__PowerCB__Power", arguments177);
            }
        }

        public class _17_0_2_1_edc0357_1360798765462_670651_20617 extends DurativeEvent {

            public _17_0_2_1_edc0357_1360798765462_670651_20617() {
                super();
                init_17_0_2_1_edc0357_1360798765462_670651_20617Members();
                init_17_0_2_1_edc0357_1360798765462_670651_20617Collections();
                init_17_0_2_1_edc0357_1360798765462_670651_20617Elaborations();
            }

            public _17_0_2_1_edc0357_1360798765462_670651_20617(Expression<Integer> startTime) {
                super();
                init_17_0_2_1_edc0357_1360798765462_670651_20617Members();
                init_17_0_2_1_edc0357_1360798765462_670651_20617Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_1_edc0357_1360798765462_670651_20617Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_1_edc0357_1360798765472_324054_20632_exists = null;

            public BooleanParameter _17_0_2_1_edc0357_1360798765466_933706_20623_exists = null;

            public BooleanParameter _17_0_2_1_edc0357_1360798765467_815816_20625_exists = null;

            public BooleanParameter _17_0_2_1_edc0357_1360798765470_351482_20629_exists = null;

            public BooleanParameter _17_0_2_1_edc0357_1360798765467_857766_20624_exists = null;

            public BooleanParameter objectToPass = null;

            public Dependency _17_0_2_1_edc0357_1360798765472_324054_20632_existsDependency = null;

            public Dependency _17_0_2_1_edc0357_1360798765466_933706_20623_existsDependency = null;

            public Dependency _17_0_2_1_edc0357_1360798765467_815816_20625_existsDependency = null;

            public Dependency _17_0_2_1_edc0357_1360798765470_351482_20629_existsDependency = null;

            public Dependency _17_0_2_1_edc0357_1360798765467_857766_20624_existsDependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Effect effect178 = null;

            public Parameter effect178Var = null;

            public Effect effect179 = null;

            public Parameter effect179Var = null;

            public Effect effect180 = null;

            public Parameter effect180Var = null;

            public Effect effect181 = null;

            public Parameter effect181Var = null;

            public Effect effect182 = null;

            public Parameter effect182Var = null;

            public ElaborationRule elaborationRule183 = null;

            public ElaborationRule elaborationRule184 = null;

            public ElaborationRule elaborationRule185 = null;

            public ElaborationRule elaborationRule186 = null;

            public ElaborationRule elaborationRule187 = null;

            public void init_17_0_2_1_edc0357_1360798765462_670651_20617Members() {
                try {
                    if (_17_0_2_1_edc0357_1360798765472_324054_20632_exists == null) _17_0_2_1_edc0357_1360798765472_324054_20632_exists = new BooleanParameter("_17_0_2_1_edc0357_1360798765472_324054_20632_exists", (Boolean) false, this);
                    if (_17_0_2_1_edc0357_1360798765466_933706_20623_exists == null) _17_0_2_1_edc0357_1360798765466_933706_20623_exists = new BooleanParameter("_17_0_2_1_edc0357_1360798765466_933706_20623_exists", (Boolean) false, this);
                    if (_17_0_2_1_edc0357_1360798765467_815816_20625_exists == null) _17_0_2_1_edc0357_1360798765467_815816_20625_exists = new BooleanParameter("_17_0_2_1_edc0357_1360798765467_815816_20625_exists", (Boolean) false, this);
                    if (_17_0_2_1_edc0357_1360798765470_351482_20629_exists == null) _17_0_2_1_edc0357_1360798765470_351482_20629_exists = new BooleanParameter("_17_0_2_1_edc0357_1360798765470_351482_20629_exists", (Boolean) false, this);
                    if (_17_0_2_1_edc0357_1360798765467_857766_20624_exists == null) _17_0_2_1_edc0357_1360798765467_857766_20624_exists = new BooleanParameter("_17_0_2_1_edc0357_1360798765467_857766_20624_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect178VarV = sig_17_0_2_1_edc0357_1360798765475_389092_20646;
                    effect178Var = new Parameter("effect178Var", null, null, this);
                    addDependency(effect178Var, new Expression(effect178VarV));
                    effect178 = new EffectFunction(new EffectFunction(effect178Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "DIFF_SCENARIO", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Boolean>(objectToPass), new Expression<Integer>(endTime) }));
                    Object effect179VarV = sig_17_0_2_1_edc0357_1360798765475_531734_20647;
                    effect179Var = new Parameter("effect179Var", null, null, this);
                    addDependency(effect179Var, new Expression(effect179VarV));
                    effect179 = new EffectFunction(new EffectFunction(effect179Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "DIFF_SCENARIO", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Boolean>(objectToPass), new Expression<Integer>(endTime) }));
                    Object effect180VarV = sig_17_0_2_1_edc0357_1360798765477_681208_20657;
                    effect180Var = new Parameter("effect180Var", null, null, this);
                    addDependency(effect180Var, new Expression(effect180VarV));
                    effect180 = new EffectFunction(new EffectFunction(effect180Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "DIFF_SCENARIO", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Boolean>(objectToPass), new Expression<Integer>(endTime) }));
                    Object effect181VarV = sig_17_0_2_1_edc0357_1360798765477_934669_20662;
                    effect181Var = new Parameter("effect181Var", null, null, this);
                    addDependency(effect181Var, new Expression(effect181VarV));
                    effect181 = new EffectFunction(new EffectFunction(effect181Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "DIFF_SCENARIO", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Boolean>(objectToPass), new Expression<Integer>(endTime) }));
                    Object effect182VarV = sig_17_0_2_1_edc0357_1360798765477_773280_20663;
                    effect182Var = new Parameter("effect182Var", null, null, this);
                    addDependency(effect182Var, new Expression(effect182VarV));
                    effect182 = new EffectFunction(new EffectFunction(effect182Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "DIFF_SCENARIO", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Boolean>(objectToPass), new Expression<Integer>(endTime) }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_1_edc0357_1360798765462_670651_20617Collections() {
                parameters.add(_17_0_2_1_edc0357_1360798765472_324054_20632_exists);
                parameters.add(_17_0_2_1_edc0357_1360798765466_933706_20623_exists);
                parameters.add(_17_0_2_1_edc0357_1360798765467_815816_20625_exists);
                parameters.add(_17_0_2_1_edc0357_1360798765470_351482_20629_exists);
                parameters.add(_17_0_2_1_edc0357_1360798765467_857766_20624_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect178Var = new TreeSet<Effect>();
                effectsForeffect178Var.add(effect178);
                addEffects((Parameter<?>) effect178Var, effectsForeffect178Var);
                Set<Effect> effectsForeffect179Var = new TreeSet<Effect>();
                effectsForeffect179Var.add(effect179);
                addEffects((Parameter<?>) effect179Var, effectsForeffect179Var);
                Set<Effect> effectsForeffect180Var = new TreeSet<Effect>();
                effectsForeffect180Var.add(effect180);
                addEffects((Parameter<?>) effect180Var, effectsForeffect180Var);
                Set<Effect> effectsForeffect181Var = new TreeSet<Effect>();
                effectsForeffect181Var.add(effect181);
                addEffects((Parameter<?>) effect181Var, effectsForeffect181Var);
                Set<Effect> effectsForeffect182Var = new TreeSet<Effect>();
                effectsForeffect182Var.add(effect182);
                addEffects((Parameter<?>) effect182Var, effectsForeffect182Var);
            }

            public void init_17_0_2_1_edc0357_1360798765462_670651_20617Dependencies() {
                addDependency(_17_0_2_1_edc0357_1360798765472_324054_20632_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765468_931127_20626_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765468_931127_20626_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(_17_0_2_1_edc0357_1360798765466_933706_20623_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765468_931127_20626_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765468_931127_20626_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(_17_0_2_1_edc0357_1360798765467_815816_20625_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765468_931127_20626_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765468_931127_20626_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(_17_0_2_1_edc0357_1360798765470_351482_20629_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765468_931127_20626_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765468_931127_20626_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(_17_0_2_1_edc0357_1360798765467_857766_20624_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765468_931127_20626_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765468_931127_20626_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_1_edc0357_1360798765475_844019_20645, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "DIFF_SCENARIO", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_1_edc0357_1360798765462_670651_20617Elaborations() {
                init_17_0_2_1_edc0357_1360798765462_670651_20617Dependencies();
                Expression<?>[] arguments183 = new Expression<?>[2];
                arguments183[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                arguments183[1] = new Expression<ObjectFlow<Boolean>>(sig_17_0_2_1_edc0357_1360798765477_773280_20663);
                Expression<Boolean> condition183 = new Expression<Boolean>(_17_0_2_1_edc0357_1360798765472_324054_20632_exists);
                elaborationRule183 = addElaborationRule(condition183, _17_0_2_1_edc0357_1360798765022_187244_20251.this, Power._17_0_2_1_edc0357_1360798765022_187244_20251._17_0_2_1_edc0357_1360798765472_324054_20632.class, "P_merge_feeders__MergeNode__PowerCB__Power", arguments183);
                Expression<?>[] arguments184 = new Expression<?>[2];
                arguments184[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                arguments184[1] = new Expression<ObjectFlow<Boolean>>(sig_17_0_2_1_edc0357_1360798765475_531734_20647);
                Expression<Boolean> condition184 = new Expression<Boolean>(_17_0_2_1_edc0357_1360798765467_857766_20624_exists);
                elaborationRule184 = addElaborationRule(condition184, _17_0_2_1_edc0357_1360798765022_187244_20251.this, Power._17_0_2_1_edc0357_1360798765022_187244_20251._17_0_2_1_edc0357_1360798765467_857766_20624.class, "P_load_m__MergeNode__PowerCB__Power", arguments184);
                Expression<?>[] arguments185 = new Expression<?>[2];
                arguments185[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                arguments185[1] = new Expression<ObjectFlow<Boolean>>(sig_17_0_2_1_edc0357_1360798765477_934669_20662);
                Expression<Boolean> condition185 = new Expression<Boolean>(_17_0_2_1_edc0357_1360798765467_815816_20625_exists);
                elaborationRule185 = addElaborationRule(condition185, _17_0_2_1_edc0357_1360798765022_187244_20251.this, Power._17_0_2_1_edc0357_1360798765022_187244_20251._17_0_2_1_edc0357_1360798765467_815816_20625.class, "P_gen_merge__MergeNode__PowerCB__Power", arguments185);
                Expression<?>[] arguments186 = new Expression<?>[1];
                arguments186[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition186 = new Expression<Boolean>(_17_0_2_1_edc0357_1360798765470_351482_20629_exists);
                elaborationRule186 = addElaborationRule(condition186, _17_0_2_1_edc0357_1360798765022_187244_20251.this, Power._17_0_2_1_edc0357_1360798765022_187244_20251._17_0_2_1_edc0357_1360798765470_351482_20629.class, "powerCBTimer__AcceptEventAction__PowerCB__Power", arguments186);
                Expression<?>[] arguments187 = new Expression<?>[2];
                arguments187[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                arguments187[1] = new Expression<ObjectFlow<Boolean>>(sig_17_0_2_1_edc0357_1360798765475_389092_20646);
                Expression<Boolean> condition187 = new Expression<Boolean>(_17_0_2_1_edc0357_1360798765466_933706_20623_exists);
                elaborationRule187 = addElaborationRule(condition187, _17_0_2_1_edc0357_1360798765022_187244_20251.this, Power._17_0_2_1_edc0357_1360798765022_187244_20251._17_0_2_1_edc0357_1360798765466_933706_20623.class, "P_change_m__MergeNode__PowerCB__Power", arguments187);
            }
        }

        public class _17_0_2_1_edc0357_1360798765463_38238_20618 extends DurativeEvent {

            public _17_0_2_1_edc0357_1360798765463_38238_20618() {
                super();
                init_17_0_2_1_edc0357_1360798765463_38238_20618Members();
                init_17_0_2_1_edc0357_1360798765463_38238_20618Collections();
                init_17_0_2_1_edc0357_1360798765463_38238_20618Elaborations();
            }

            public _17_0_2_1_edc0357_1360798765463_38238_20618(Expression<Integer> startTime) {
                super();
                init_17_0_2_1_edc0357_1360798765463_38238_20618Members();
                init_17_0_2_1_edc0357_1360798765463_38238_20618Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_1_edc0357_1360798765463_38238_20618Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_1_edc0357_1360798765468_517742_20627_exists = null;

            public Parameter< Power_System.SignalchangeLoadValue > _17_0_2_1_edc0357_1360798766306_121376_21907 = null;

            public BooleanParameter objectToPass = null;

            public Dependency _17_0_2_1_edc0357_1360798765468_517742_20627_existsDependency = null;

            public Dependency _17_0_2_1_edc0357_1360798766306_121376_21907Dependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Effect effect188 = null;

            public Parameter effect188Var = null;

            public ElaborationRule elaborationRule189 = null;

            public void init_17_0_2_1_edc0357_1360798765463_38238_20618Members() {
                try {
                    if (_17_0_2_1_edc0357_1360798765468_517742_20627_exists == null) _17_0_2_1_edc0357_1360798765468_517742_20627_exists = new BooleanParameter("_17_0_2_1_edc0357_1360798765468_517742_20627_exists", (Boolean) false, this);
                    if (_17_0_2_1_edc0357_1360798766306_121376_21907 == null) _17_0_2_1_edc0357_1360798766306_121376_21907 = new Parameter<Power_System.SignalchangeLoadValue>("_17_0_2_1_edc0357_1360798766306_121376_21907", null, (Power_System.SignalchangeLoadValue) null, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect188VarV = sig_17_0_2_1_edc0357_1360798765476_99835_20653;
                    effect188Var = new Parameter("effect188Var", null, null, this);
                    addDependency(effect188Var, new Expression(effect188VarV));
                    effect188 = new EffectFunction(new EffectFunction(effect188Var, ClassUtils.getMethodForArgTypes("ObjectFlow<SignalchangeLoadValue>", "DIFF_SCENARIO", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Power_System.SignalchangeLoadValue>(_17_0_2_1_edc0357_1360798766306_121376_21907), new Expression<Integer>(endTime) }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_1_edc0357_1360798765463_38238_20618Collections() {
                parameters.add(_17_0_2_1_edc0357_1360798765468_517742_20627_exists);
                parameters.add(_17_0_2_1_edc0357_1360798766306_121376_21907);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect188Var = new TreeSet<Effect>();
                effectsForeffect188Var.add(effect188);
                addEffects((Parameter<?>) effect188Var, effectsForeffect188Var);
            }

            public void init_17_0_2_1_edc0357_1360798765463_38238_20618Dependencies() {
                addDependency(_17_0_2_1_edc0357_1360798765468_517742_20627_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765468_931127_20626_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765468_931127_20626_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(_17_0_2_1_edc0357_1360798766306_121376_21907, new Expression(new EffectFunction(q_Power_changeLoadValue, ClassUtils.getMethodForArgTypes("ObjectFlow<SignalchangeLoadValue>", "DIFF_SCENARIO", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_1_edc0357_1360798765475_964511_20649, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "DIFF_SCENARIO", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_1_edc0357_1360798765463_38238_20618Elaborations() {
                init_17_0_2_1_edc0357_1360798765463_38238_20618Dependencies();
                Expression<?>[] arguments189 = new Expression<?>[1];
                arguments189[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition189 = new Expression<Boolean>(_17_0_2_1_edc0357_1360798765468_517742_20627_exists);
                elaborationRule189 = addElaborationRule(condition189, _17_0_2_1_edc0357_1360798765022_187244_20251.this, Power._17_0_2_1_edc0357_1360798765022_187244_20251._17_0_2_1_edc0357_1360798765468_517742_20627.class, "P_readLoadFromSignal__ReadStructuralFeatureAction__PowerCB__Power", arguments189);
            }
        }

        public class _17_0_2_1_edc0357_1360798765463_360893_20619 extends DurativeEvent {

            public _17_0_2_1_edc0357_1360798765463_360893_20619() {
                super();
                init_17_0_2_1_edc0357_1360798765463_360893_20619Members();
                init_17_0_2_1_edc0357_1360798765463_360893_20619Collections();
                init_17_0_2_1_edc0357_1360798765463_360893_20619Elaborations();
            }

            public _17_0_2_1_edc0357_1360798765463_360893_20619(Expression<Integer> startTime) {
                super();
                init_17_0_2_1_edc0357_1360798765463_360893_20619Members();
                init_17_0_2_1_edc0357_1360798765463_360893_20619Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_1_edc0357_1360798765463_360893_20619Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_1_edc0357_1360798765469_417764_20628_exists = null;

            public Parameter< Power_System.SignalchangeGenerationValue > _17_0_2_1_edc0357_1360798766307_585895_21909 = null;

            public BooleanParameter objectToPass = null;

            public Dependency _17_0_2_1_edc0357_1360798765469_417764_20628_existsDependency = null;

            public Dependency _17_0_2_1_edc0357_1360798766307_585895_21909Dependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Effect effect190 = null;

            public Parameter effect190Var = null;

            public ElaborationRule elaborationRule191 = null;

            public void init_17_0_2_1_edc0357_1360798765463_360893_20619Members() {
                try {
                    if (_17_0_2_1_edc0357_1360798765469_417764_20628_exists == null) _17_0_2_1_edc0357_1360798765469_417764_20628_exists = new BooleanParameter("_17_0_2_1_edc0357_1360798765469_417764_20628_exists", (Boolean) false, this);
                    if (_17_0_2_1_edc0357_1360798766307_585895_21909 == null) _17_0_2_1_edc0357_1360798766307_585895_21909 = new Parameter<Power_System.SignalchangeGenerationValue>("_17_0_2_1_edc0357_1360798766307_585895_21909", null, (Power_System.SignalchangeGenerationValue) null, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect190VarV = sig_17_0_2_1_edc0357_1360798765476_487996_20655;
                    effect190Var = new Parameter("effect190Var", null, null, this);
                    addDependency(effect190Var, new Expression(effect190VarV));
                    effect190 = new EffectFunction(new EffectFunction(effect190Var, ClassUtils.getMethodForArgTypes("ObjectFlow<SignalchangeGenerationValue>", "DIFF_SCENARIO", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Power_System.SignalchangeGenerationValue>(_17_0_2_1_edc0357_1360798766307_585895_21909), new Expression<Integer>(endTime) }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_1_edc0357_1360798765463_360893_20619Collections() {
                parameters.add(_17_0_2_1_edc0357_1360798765469_417764_20628_exists);
                parameters.add(_17_0_2_1_edc0357_1360798766307_585895_21909);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect190Var = new TreeSet<Effect>();
                effectsForeffect190Var.add(effect190);
                addEffects((Parameter<?>) effect190Var, effectsForeffect190Var);
            }

            public void init_17_0_2_1_edc0357_1360798765463_360893_20619Dependencies() {
                addDependency(_17_0_2_1_edc0357_1360798765469_417764_20628_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765468_931127_20626_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765468_931127_20626_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(_17_0_2_1_edc0357_1360798766307_585895_21909, new Expression(new EffectFunction(q_Power_changeGenerationValue, ClassUtils.getMethodForArgTypes("ObjectFlow<SignalchangeGenerationValue>", "DIFF_SCENARIO", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_1_edc0357_1360798765475_501086_20648, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "DIFF_SCENARIO", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_1_edc0357_1360798765463_360893_20619Elaborations() {
                init_17_0_2_1_edc0357_1360798765463_360893_20619Dependencies();
                Expression<?>[] arguments191 = new Expression<?>[1];
                arguments191[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition191 = new Expression<Boolean>(_17_0_2_1_edc0357_1360798765469_417764_20628_exists);
                elaborationRule191 = addElaborationRule(condition191, _17_0_2_1_edc0357_1360798765022_187244_20251.this, Power._17_0_2_1_edc0357_1360798765022_187244_20251._17_0_2_1_edc0357_1360798765469_417764_20628.class, "P_readChangeGenVal__ReadStructuralFeatureAction__PowerCB__Power", arguments191);
            }
        }

        public class _17_0_2_1_edc0357_1360798765465_127271_20622 extends DurativeEvent {

            public _17_0_2_1_edc0357_1360798765465_127271_20622() {
                super();
                init_17_0_2_1_edc0357_1360798765465_127271_20622Members();
                init_17_0_2_1_edc0357_1360798765465_127271_20622Collections();
                init_17_0_2_1_edc0357_1360798765465_127271_20622Elaborations();
            }

            public _17_0_2_1_edc0357_1360798765465_127271_20622(Expression<Integer> startTime) {
                super();
                init_17_0_2_1_edc0357_1360798765465_127271_20622Members();
                init_17_0_2_1_edc0357_1360798765465_127271_20622Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_1_edc0357_1360798765465_127271_20622Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_1_edc0357_1360798765471_478130_20630_exists = null;

            public BooleanParameter objectToPass = null;

            public Dependency _17_0_2_1_edc0357_1360798765471_478130_20630_existsDependency = null;

            public Dependency objectToPassDependency = null;

            public Effect effect192 = null;

            public Parameter effect192Var = null;

            public ElaborationRule elaborationRule193 = null;

            public ElaborationRule elaborationRule194 = null;

            public void init_17_0_2_1_edc0357_1360798765465_127271_20622Members() {
                try {
                    if (_17_0_2_1_edc0357_1360798765471_478130_20630_exists == null) _17_0_2_1_edc0357_1360798765471_478130_20630_exists = new BooleanParameter("_17_0_2_1_edc0357_1360798765471_478130_20630_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect192VarV = sig_17_0_2_1_edc0357_1360798765477_228170_20659;
                    effect192Var = new Parameter("effect192Var", null, null, this);
                    addDependency(effect192Var, new Expression(effect192VarV));
                    effect192 = new EffectFunction(new EffectFunction(effect192Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "DIFF_SCENARIO", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Boolean>(objectToPass), new Expression<Integer>(endTime) }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_1_edc0357_1360798765465_127271_20622Collections() {
                parameters.add(_17_0_2_1_edc0357_1360798765471_478130_20630_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect192Var = new TreeSet<Effect>();
                effectsForeffect192Var.add(effect192);
                addEffects((Parameter<?>) effect192Var, effectsForeffect192Var);
            }

            public void init_17_0_2_1_edc0357_1360798765465_127271_20622Dependencies() {
                addDependency(_17_0_2_1_edc0357_1360798765471_478130_20630_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765468_931127_20626_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765468_931127_20626_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_1_edc0357_1360798765476_584348_20650, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "DIFF_SCENARIO", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_1_edc0357_1360798765465_127271_20622Elaborations() {
                init_17_0_2_1_edc0357_1360798765465_127271_20622Dependencies();
                Expression<?>[] arguments193 = new Expression<?>[1];
                arguments193[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition193 = new Expression<Boolean>(_17_0_2_1_edc0357_1360798765471_478130_20630_exists);
                elaborationRule193 = addElaborationRule(condition193, _17_0_2_1_edc0357_1360798765022_187244_20251.this, Power._17_0_2_1_edc0357_1360798765022_187244_20251._17_0_2_1_edc0357_1360798765471_478130_20630.class, "P_gen_timer__AcceptEventAction__PowerCB__Power", arguments193);
                Expression<?>[] arguments194 = new Expression<?>[2];
                arguments194[0] = new Expression<Integer>(startTime);
                arguments194[1] = new Expression<DurativeEvent>(this);
                Expression<Boolean> condition194 = new Expression<Boolean>(true);
                elaborationRule194 = addElaborationRule(condition194, Power.this, Power._17_0_2_1_edc0357_1360798765021_125160_20250.class, "generate__Activity__Power__Power", arguments194);
            }
        }

        public class _17_0_2_1_edc0357_1360798765466_933706_20623 extends DurativeEvent {

            public _17_0_2_1_edc0357_1360798765466_933706_20623() {
                super();
                init_17_0_2_1_edc0357_1360798765466_933706_20623Members();
                init_17_0_2_1_edc0357_1360798765466_933706_20623Collections();
                init_17_0_2_1_edc0357_1360798765466_933706_20623Elaborations();
            }

            public _17_0_2_1_edc0357_1360798765466_933706_20623(Expression<Integer> startTime, Expression<ObjectFlow<Boolean>> receiveThis) {
                super();
                init_17_0_2_1_edc0357_1360798765466_933706_20623Members();
                init_17_0_2_1_edc0357_1360798765466_933706_20623Collections();
                addDependency(this.startTime, startTime);
                addDependency(this.receiveThis, receiveThis);
                init_17_0_2_1_edc0357_1360798765466_933706_20623Elaborations();
                fixTimeDependencies();
            }

            public Parameter< ObjectFlow<Boolean> > receiveThis = null;

            public BooleanParameter _17_0_2_1_edc0357_1360798765463_360893_20619_exists = null;

            public BooleanParameter objectToPass = null;

            public Dependency endTimeDependency = null;

            public Dependency _17_0_2_1_edc0357_1360798765463_360893_20619_existsDependency = null;

            public Dependency objectToPassDependency = null;

            public Effect effect195 = null;

            public Parameter effect195Var = null;

            public ElaborationRule elaborationRule196 = null;

            public void init_17_0_2_1_edc0357_1360798765466_933706_20623Members() {
                try {
                    if (receiveThis == null) receiveThis = new Parameter<ObjectFlow<Boolean>>("receiveThis", null, (ObjectFlow<Boolean>) null, this);
                    if (_17_0_2_1_edc0357_1360798765463_360893_20619_exists == null) _17_0_2_1_edc0357_1360798765463_360893_20619_exists = new BooleanParameter("_17_0_2_1_edc0357_1360798765463_360893_20619_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect195VarV = sig_17_0_2_1_edc0357_1360798765475_501086_20648;
                    effect195Var = new Parameter("effect195Var", null, null, this);
                    addDependency(effect195Var, new Expression(effect195VarV));
                    effect195 = new EffectFunction(new EffectFunction(effect195Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "DIFF_SCENARIO", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Boolean>(objectToPass), new Expression<Integer>(endTime) }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_1_edc0357_1360798765466_933706_20623Collections() {
                parameters.add(receiveThis);
                parameters.add(_17_0_2_1_edc0357_1360798765463_360893_20619_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect195Var = new TreeSet<Effect>();
                effectsForeffect195Var.add(effect195);
                addEffects((Parameter<?>) effect195Var, effectsForeffect195Var);
            }

            public void init_17_0_2_1_edc0357_1360798765466_933706_20623Dependencies() {
                addDependency(endTime, new Expression(new FunctionCall(null, ClassUtils.getMethodForArgTypes("Math", "DIFF_SCENARIO", "min", int.class, int.class), new Object[] { new Expression(new EffectFunction(q_Power_changeGenerationValue, ClassUtils.getMethodForArgTypes("ObjectFlow<SignalchangeGenerationValue>", "DIFF_SCENARIO", "nextTimeHasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(startTime) })), new Expression<Integer>(finalNode_endTime) })));
                addDependency(_17_0_2_1_edc0357_1360798765463_360893_20619_exists, new Expression<Boolean>(new Functions.And(new Expression(new EffectFunction(q_Power_changeGenerationValue, ClassUtils.getMethodForArgTypes("ObjectFlow<SignalchangeGenerationValue>", "DIFF_SCENARIO", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765468_931127_20626_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765468_931127_20626_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))))));
                addDependency(objectToPass, new Expression(new EffectFunction(receiveThis, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "DIFF_SCENARIO", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_1_edc0357_1360798765466_933706_20623Elaborations() {
                init_17_0_2_1_edc0357_1360798765466_933706_20623Dependencies();
                Expression<?>[] arguments196 = new Expression<?>[1];
                arguments196[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition196 = new Expression<Boolean>(_17_0_2_1_edc0357_1360798765463_360893_20619_exists);
                elaborationRule196 = addElaborationRule(condition196, _17_0_2_1_edc0357_1360798765022_187244_20251.this, Power._17_0_2_1_edc0357_1360798765022_187244_20251._17_0_2_1_edc0357_1360798765463_360893_20619.class, "P_receivecvg_sig__AcceptEventAction__PowerCB__Power", arguments196);
            }
        }

        public class _17_0_2_1_edc0357_1360798765467_857766_20624 extends DurativeEvent {

            public _17_0_2_1_edc0357_1360798765467_857766_20624() {
                super();
                init_17_0_2_1_edc0357_1360798765467_857766_20624Members();
                init_17_0_2_1_edc0357_1360798765467_857766_20624Collections();
                init_17_0_2_1_edc0357_1360798765467_857766_20624Elaborations();
            }

            public _17_0_2_1_edc0357_1360798765467_857766_20624(Expression<Integer> startTime, Expression<ObjectFlow<Boolean>> receiveThis) {
                super();
                init_17_0_2_1_edc0357_1360798765467_857766_20624Members();
                init_17_0_2_1_edc0357_1360798765467_857766_20624Collections();
                addDependency(this.startTime, startTime);
                addDependency(this.receiveThis, receiveThis);
                init_17_0_2_1_edc0357_1360798765467_857766_20624Elaborations();
                fixTimeDependencies();
            }

            public Parameter< ObjectFlow<Boolean> > receiveThis = null;

            public BooleanParameter objectToPass = null;

            public BooleanParameter _17_0_2_1_edc0357_1360798765463_38238_20618_exists = null;

            public Dependency endTimeDependency = null;

            public Dependency objectToPassDependency = null;

            public Dependency _17_0_2_1_edc0357_1360798765463_38238_20618_existsDependency = null;

            public Effect effect197 = null;

            public Parameter effect197Var = null;

            public ElaborationRule elaborationRule198 = null;

            public void init_17_0_2_1_edc0357_1360798765467_857766_20624Members() {
                try {
                    if (receiveThis == null) receiveThis = new Parameter<ObjectFlow<Boolean>>("receiveThis", null, (ObjectFlow<Boolean>) null, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (_17_0_2_1_edc0357_1360798765463_38238_20618_exists == null) _17_0_2_1_edc0357_1360798765463_38238_20618_exists = new BooleanParameter("_17_0_2_1_edc0357_1360798765463_38238_20618_exists", (Boolean) false, this);
                    Object effect197VarV = sig_17_0_2_1_edc0357_1360798765475_964511_20649;
                    effect197Var = new Parameter("effect197Var", null, null, this);
                    addDependency(effect197Var, new Expression(effect197VarV));
                    effect197 = new EffectFunction(new EffectFunction(effect197Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "DIFF_SCENARIO", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Boolean>(objectToPass), new Expression<Integer>(endTime) }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_1_edc0357_1360798765467_857766_20624Collections() {
                parameters.add(receiveThis);
                parameters.add(objectToPass);
                parameters.add(_17_0_2_1_edc0357_1360798765463_38238_20618_exists);
                Set<Effect> effectsForeffect197Var = new TreeSet<Effect>();
                effectsForeffect197Var.add(effect197);
                addEffects((Parameter<?>) effect197Var, effectsForeffect197Var);
            }

            public void init_17_0_2_1_edc0357_1360798765467_857766_20624Dependencies() {
                addDependency(endTime, new Expression(new FunctionCall(null, ClassUtils.getMethodForArgTypes("Math", "DIFF_SCENARIO", "min", int.class, int.class), new Object[] { new Expression(new EffectFunction(q_Power_changeLoadValue, ClassUtils.getMethodForArgTypes("ObjectFlow<SignalchangeLoadValue>", "DIFF_SCENARIO", "nextTimeHasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(startTime) })), new Expression<Integer>(finalNode_endTime) })));
                addDependency(objectToPass, new Expression(new EffectFunction(receiveThis, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "DIFF_SCENARIO", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_1_edc0357_1360798765463_38238_20618_exists, new Expression<Boolean>(new Functions.And(new Expression(new EffectFunction(q_Power_changeLoadValue, ClassUtils.getMethodForArgTypes("ObjectFlow<SignalchangeLoadValue>", "DIFF_SCENARIO", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765468_931127_20626_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765468_931127_20626_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))))));
            }

            public void init_17_0_2_1_edc0357_1360798765467_857766_20624Elaborations() {
                init_17_0_2_1_edc0357_1360798765467_857766_20624Dependencies();
                Expression<?>[] arguments198 = new Expression<?>[1];
                arguments198[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition198 = new Expression<Boolean>(_17_0_2_1_edc0357_1360798765463_38238_20618_exists);
                elaborationRule198 = addElaborationRule(condition198, _17_0_2_1_edc0357_1360798765022_187244_20251.this, Power._17_0_2_1_edc0357_1360798765022_187244_20251._17_0_2_1_edc0357_1360798765463_38238_20618.class, "P_receiveclv_sig__AcceptEventAction__PowerCB__Power", arguments198);
            }
        }

        public class _17_0_2_1_edc0357_1360798765467_815816_20625 extends DurativeEvent {

            public _17_0_2_1_edc0357_1360798765467_815816_20625() {
                super();
                init_17_0_2_1_edc0357_1360798765467_815816_20625Members();
                init_17_0_2_1_edc0357_1360798765467_815816_20625Collections();
                init_17_0_2_1_edc0357_1360798765467_815816_20625Elaborations();
            }

            public _17_0_2_1_edc0357_1360798765467_815816_20625(Expression<Integer> startTime, Expression<ObjectFlow<Boolean>> receiveThis) {
                super();
                init_17_0_2_1_edc0357_1360798765467_815816_20625Members();
                init_17_0_2_1_edc0357_1360798765467_815816_20625Collections();
                addDependency(this.startTime, startTime);
                addDependency(this.receiveThis, receiveThis);
                init_17_0_2_1_edc0357_1360798765467_815816_20625Elaborations();
                fixTimeDependencies();
            }

            public Parameter< ObjectFlow<Boolean> > receiveThis = null;

            public BooleanParameter objectToPass = null;

            public BooleanParameter _17_0_2_1_edc0357_1360798765465_127271_20622_exists = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Dependency _17_0_2_1_edc0357_1360798765465_127271_20622_existsDependency = null;

            public Effect effect199 = null;

            public Parameter effect199Var = null;

            public ElaborationRule elaborationRule200 = null;

            public void init_17_0_2_1_edc0357_1360798765467_815816_20625Members() {
                try {
                    if (receiveThis == null) receiveThis = new Parameter<ObjectFlow<Boolean>>("receiveThis", null, (ObjectFlow<Boolean>) null, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (_17_0_2_1_edc0357_1360798765465_127271_20622_exists == null) _17_0_2_1_edc0357_1360798765465_127271_20622_exists = new BooleanParameter("_17_0_2_1_edc0357_1360798765465_127271_20622_exists", (Boolean) false, this);
                    Object effect199VarV = sig_17_0_2_1_edc0357_1360798765476_584348_20650;
                    effect199Var = new Parameter("effect199Var", null, null, this);
                    addDependency(effect199Var, new Expression(effect199VarV));
                    effect199 = new EffectFunction(new EffectFunction(effect199Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "DIFF_SCENARIO", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Boolean>(objectToPass), new Expression<Integer>(endTime) }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_1_edc0357_1360798765467_815816_20625Collections() {
                parameters.add(receiveThis);
                parameters.add(objectToPass);
                parameters.add(_17_0_2_1_edc0357_1360798765465_127271_20622_exists);
                Set<Effect> effectsForeffect199Var = new TreeSet<Effect>();
                effectsForeffect199Var.add(effect199);
                addEffects((Parameter<?>) effect199Var, effectsForeffect199Var);
            }

            public void init_17_0_2_1_edc0357_1360798765467_815816_20625Dependencies() {
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression(new EffectFunction(receiveThis, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "DIFF_SCENARIO", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_1_edc0357_1360798765465_127271_20622_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765468_931127_20626_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765468_931127_20626_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
            }

            public void init_17_0_2_1_edc0357_1360798765467_815816_20625Elaborations() {
                init_17_0_2_1_edc0357_1360798765467_815816_20625Dependencies();
                Expression<?>[] arguments200 = new Expression<?>[1];
                arguments200[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition200 = new Expression<Boolean>(_17_0_2_1_edc0357_1360798765465_127271_20622_exists);
                elaborationRule200 = addElaborationRule(condition200, _17_0_2_1_edc0357_1360798765022_187244_20251.this, Power._17_0_2_1_edc0357_1360798765022_187244_20251._17_0_2_1_edc0357_1360798765465_127271_20622.class, "P_gen__CallBehaviorAction__PowerCB__Power", arguments200);
            }
        }

        public class _17_0_2_1_edc0357_1360798765468_931127_20626 extends DurativeEvent {

            public _17_0_2_1_edc0357_1360798765468_931127_20626() {
                super();
                init_17_0_2_1_edc0357_1360798765468_931127_20626Members();
                init_17_0_2_1_edc0357_1360798765468_931127_20626Collections();
                init_17_0_2_1_edc0357_1360798765468_931127_20626Elaborations();
            }

            public _17_0_2_1_edc0357_1360798765468_931127_20626(Expression<Integer> startTime) {
                super();
                init_17_0_2_1_edc0357_1360798765468_931127_20626Members();
                init_17_0_2_1_edc0357_1360798765468_931127_20626Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_1_edc0357_1360798765468_931127_20626Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter objectToPass = null;

            public Dependency endTimeDependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Dependency finalNode_endTimeDependency = null;

            public void init_17_0_2_1_edc0357_1360798765468_931127_20626Members() {
                try {
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_1_edc0357_1360798765468_931127_20626Collections() {
                parameters.add(objectToPass);
            }

            public void init_17_0_2_1_edc0357_1360798765468_931127_20626Dependencies() {
                addDependency(endTime, new Expression<Integer>(new Functions.Plus(new Expression<Integer>(startTime), new Expression<Integer>(duration))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_1_edc0357_1360798765477_693138_20658, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "DIFF_SCENARIO", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(finalNode_endTime, new Expression<Integer>(endTime));
            }

            public void init_17_0_2_1_edc0357_1360798765468_931127_20626Elaborations() {
                init_17_0_2_1_edc0357_1360798765468_931127_20626Dependencies();
            }
        }

        public class _17_0_2_1_edc0357_1360798765468_517742_20627 extends DurativeEvent {

            public _17_0_2_1_edc0357_1360798765468_517742_20627() {
                super();
                init_17_0_2_1_edc0357_1360798765468_517742_20627Members();
                init_17_0_2_1_edc0357_1360798765468_517742_20627Collections();
                init_17_0_2_1_edc0357_1360798765468_517742_20627Elaborations();
            }

            public _17_0_2_1_edc0357_1360798765468_517742_20627(Expression<Integer> startTime) {
                super();
                init_17_0_2_1_edc0357_1360798765468_517742_20627Members();
                init_17_0_2_1_edc0357_1360798765468_517742_20627Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_1_edc0357_1360798765468_517742_20627Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_1_edc0357_1360878349175_589211_15843_exists = null;

            public Parameter< Power_System.SignalchangeLoadValue > _17_0_2_1_edc0357_1360798766309_83168_21913 = null;

            public DoubleParameter _17_0_2_1_edc0357_1360798766309_159629_21912 = null;

            public Dependency _17_0_2_1_edc0357_1360878349175_589211_15843_existsDependency = null;

            public Dependency _17_0_2_1_edc0357_1360798766309_83168_21913Dependency = null;

            public Dependency durationDependency = null;

            public Dependency _17_0_2_1_edc0357_1360798766309_159629_21912Dependency = null;

            public Effect effect201 = null;

            public Parameter effect201Var = null;

            public ElaborationRule elaborationRule202 = null;

            public void init_17_0_2_1_edc0357_1360798765468_517742_20627Members() {
                try {
                    if (_17_0_2_1_edc0357_1360878349175_589211_15843_exists == null) _17_0_2_1_edc0357_1360878349175_589211_15843_exists = new BooleanParameter("_17_0_2_1_edc0357_1360878349175_589211_15843_exists", (Boolean) false, this);
                    if (_17_0_2_1_edc0357_1360798766309_83168_21913 == null) _17_0_2_1_edc0357_1360798766309_83168_21913 = new Parameter<Power_System.SignalchangeLoadValue>("_17_0_2_1_edc0357_1360798766309_83168_21913", null, (Power_System.SignalchangeLoadValue) null, this);
                    if (_17_0_2_1_edc0357_1360798766309_159629_21912 == null) _17_0_2_1_edc0357_1360798766309_159629_21912 = new DoubleParameter("_17_0_2_1_edc0357_1360798766309_159629_21912", (Double) null, this);
                    Object effect201VarV = sig_17_0_2_1_edc0357_1360798765476_833192_20654;
                    effect201Var = new Parameter("effect201Var", null, null, this);
                    addDependency(effect201Var, new Expression(effect201VarV));
                    effect201 = new EffectFunction(new EffectFunction(effect201Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Float>", "DIFF_SCENARIO", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Double>(_17_0_2_1_edc0357_1360798766309_159629_21912), new Expression<Integer>(endTime) }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_1_edc0357_1360798765468_517742_20627Collections() {
                parameters.add(_17_0_2_1_edc0357_1360878349175_589211_15843_exists);
                parameters.add(_17_0_2_1_edc0357_1360798766309_83168_21913);
                parameters.add(_17_0_2_1_edc0357_1360798766309_159629_21912);
                Set<Effect> effectsForeffect201Var = new TreeSet<Effect>();
                effectsForeffect201Var.add(effect201);
                addEffects((Parameter<?>) effect201Var, effectsForeffect201Var);
            }

            public void init_17_0_2_1_edc0357_1360798765468_517742_20627Dependencies() {
                addDependency(_17_0_2_1_edc0357_1360878349175_589211_15843_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765468_931127_20626_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765468_931127_20626_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(_17_0_2_1_edc0357_1360798766309_83168_21913, new Expression(new EffectFunction(sig_17_0_2_1_edc0357_1360798765476_99835_20653, ClassUtils.getMethodForArgTypes("ObjectFlow<SignalchangeLoadValue>", "DIFF_SCENARIO", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(_17_0_2_1_edc0357_1360798766309_159629_21912, new Expression(new EffectFunction(null, ClassUtils.getMethodForArgTypes("TimeVaryingMap<Float>", "DIFF_SCENARIO", "getValue", java.lang.Integer.class), new Object[] { new Expression<Integer>(startTime) }, new FunctionCall(_17_0_2_1_edc0357_1360798766309_83168_21913, Parameter.class, "getMember", new Object[] { "load__17_0_2_1_edc0357_1360798765030_737782_20262" }))));
            }

            public void init_17_0_2_1_edc0357_1360798765468_517742_20627Elaborations() {
                init_17_0_2_1_edc0357_1360798765468_517742_20627Dependencies();
                Expression<?>[] arguments202 = new Expression<?>[1];
                arguments202[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition202 = new Expression<Boolean>(_17_0_2_1_edc0357_1360878349175_589211_15843_exists);
                elaborationRule202 = addElaborationRule(condition202, _17_0_2_1_edc0357_1360798765022_187244_20251.this, Power._17_0_2_1_edc0357_1360798765022_187244_20251._17_0_2_1_edc0357_1360878349175_589211_15843.class, "P_update_feeder__AddStructuralFeatureValueAction__PowerCB__Power", arguments202);
            }
        }

        public class _17_0_2_1_edc0357_1360798765469_417764_20628 extends DurativeEvent {

            public _17_0_2_1_edc0357_1360798765469_417764_20628() {
                super();
                init_17_0_2_1_edc0357_1360798765469_417764_20628Members();
                init_17_0_2_1_edc0357_1360798765469_417764_20628Collections();
                init_17_0_2_1_edc0357_1360798765469_417764_20628Elaborations();
            }

            public _17_0_2_1_edc0357_1360798765469_417764_20628(Expression<Integer> startTime) {
                super();
                init_17_0_2_1_edc0357_1360798765469_417764_20628Members();
                init_17_0_2_1_edc0357_1360798765469_417764_20628Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_1_edc0357_1360798765469_417764_20628Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_1_edc0357_1360878700672_703950_15878_exists = null;

            public BooleanParameter _17_0_2_1_edc0357_1360878683758_623651_15865_exists = null;

            public DoubleParameter _17_0_2_1_edc0357_1360798766310_329451_21914 = null;

            public Parameter< Power_System.SignalchangeGenerationValue > _17_0_2_1_edc0357_1360798766311_546066_21915 = null;

            public BooleanParameter objectToPass = null;

            public Dependency _17_0_2_1_edc0357_1360878700672_703950_15878_existsDependency = null;

            public Dependency _17_0_2_1_edc0357_1360878683758_623651_15865_existsDependency = null;

            public Dependency _17_0_2_1_edc0357_1360798766310_329451_21914Dependency = null;

            public Dependency _17_0_2_1_edc0357_1360798766311_546066_21915Dependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Effect effect203 = null;

            public Parameter effect203Var = null;

            public Effect effect204 = null;

            public Parameter effect204Var = null;

            public ElaborationRule elaborationRule205 = null;

            public ElaborationRule elaborationRule206 = null;

            public void init_17_0_2_1_edc0357_1360798765469_417764_20628Members() {
                try {
                    if (_17_0_2_1_edc0357_1360878700672_703950_15878_exists == null) _17_0_2_1_edc0357_1360878700672_703950_15878_exists = new BooleanParameter("_17_0_2_1_edc0357_1360878700672_703950_15878_exists", (Boolean) false, this);
                    if (_17_0_2_1_edc0357_1360878683758_623651_15865_exists == null) _17_0_2_1_edc0357_1360878683758_623651_15865_exists = new BooleanParameter("_17_0_2_1_edc0357_1360878683758_623651_15865_exists", (Boolean) false, this);
                    if (_17_0_2_1_edc0357_1360798766310_329451_21914 == null) _17_0_2_1_edc0357_1360798766310_329451_21914 = new DoubleParameter("_17_0_2_1_edc0357_1360798766310_329451_21914", (Double) null, this);
                    if (_17_0_2_1_edc0357_1360798766311_546066_21915 == null) _17_0_2_1_edc0357_1360798766311_546066_21915 = new Parameter<Power_System.SignalchangeGenerationValue>("_17_0_2_1_edc0357_1360798766311_546066_21915", null, (Power_System.SignalchangeGenerationValue) null, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect203VarV = sig_17_0_2_1_edc0357_1360963130781_302800_23138;
                    effect203Var = new Parameter("effect203Var", null, null, this);
                    addDependency(effect203Var, new Expression(effect203VarV));
                    effect203 = new EffectFunction(new EffectFunction(effect203Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Float>", "DIFF_SCENARIO", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Double>(_17_0_2_1_edc0357_1360798766310_329451_21914), new Expression<Integer>(endTime) }));
                    Object effect204VarV = sig_17_0_2_1_edc0357_1360963135293_976442_23143;
                    effect204Var = new Parameter("effect204Var", null, null, this);
                    addDependency(effect204Var, new Expression(effect204VarV));
                    effect204 = new EffectFunction(new EffectFunction(effect204Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "DIFF_SCENARIO", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Boolean>(objectToPass), new Expression<Integer>(endTime) }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_1_edc0357_1360798765469_417764_20628Collections() {
                parameters.add(_17_0_2_1_edc0357_1360878700672_703950_15878_exists);
                parameters.add(_17_0_2_1_edc0357_1360878683758_623651_15865_exists);
                parameters.add(_17_0_2_1_edc0357_1360798766310_329451_21914);
                parameters.add(_17_0_2_1_edc0357_1360798766311_546066_21915);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect203Var = new TreeSet<Effect>();
                effectsForeffect203Var.add(effect203);
                addEffects((Parameter<?>) effect203Var, effectsForeffect203Var);
                Set<Effect> effectsForeffect204Var = new TreeSet<Effect>();
                effectsForeffect204Var.add(effect204);
                addEffects((Parameter<?>) effect204Var, effectsForeffect204Var);
            }

            public void init_17_0_2_1_edc0357_1360798765469_417764_20628Dependencies() {
                addDependency(_17_0_2_1_edc0357_1360878700672_703950_15878_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765468_931127_20626_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765468_931127_20626_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(_17_0_2_1_edc0357_1360878683758_623651_15865_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765468_931127_20626_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765468_931127_20626_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(_17_0_2_1_edc0357_1360798766310_329451_21914, new Expression(new EffectFunction(null, ClassUtils.getMethodForArgTypes("TimeVaryingMap<Float>", "DIFF_SCENARIO", "getValue", java.lang.Integer.class), new Object[] { new Expression<Integer>(startTime) }, new FunctionCall(_17_0_2_1_edc0357_1360798766311_546066_21915, Parameter.class, "getMember", new Object[] { "newGenValue__17_0_2_1_edc0357_1360798765030_16604_20261" }))));
                addDependency(_17_0_2_1_edc0357_1360798766311_546066_21915, new Expression(new EffectFunction(sig_17_0_2_1_edc0357_1360798765476_487996_20655, ClassUtils.getMethodForArgTypes("ObjectFlow<SignalchangeGenerationValue>", "DIFF_SCENARIO", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(true));
            }

            public void init_17_0_2_1_edc0357_1360798765469_417764_20628Elaborations() {
                init_17_0_2_1_edc0357_1360798765469_417764_20628Dependencies();
                Expression<?>[] arguments205 = new Expression<?>[1];
                arguments205[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition205 = new Expression<Boolean>(_17_0_2_1_edc0357_1360878683758_623651_15865_exists);
                elaborationRule205 = addElaborationRule(condition205, _17_0_2_1_edc0357_1360798765022_187244_20251.this, Power._17_0_2_1_edc0357_1360798765022_187244_20251._17_0_2_1_edc0357_1360878683758_623651_15865.class, "Pchange_set_aug__AddStructuralFeatureValueAction__PowerCB__Power", arguments205);
                Expression<?>[] arguments206 = new Expression<?>[1];
                arguments206[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition206 = new Expression<Boolean>(_17_0_2_1_edc0357_1360878700672_703950_15878_exists);
                elaborationRule206 = addElaborationRule(condition206, _17_0_2_1_edc0357_1360798765022_187244_20251.this, Power._17_0_2_1_edc0357_1360798765022_187244_20251._17_0_2_1_edc0357_1360878700672_703950_15878.class, "Pchange_send_change_confirm__SendSignalAction__PowerCB__Power", arguments206);
            }
        }

        public class _17_0_2_1_edc0357_1360798765470_351482_20629 extends DurativeEvent {

            public _17_0_2_1_edc0357_1360798765470_351482_20629() {
                super();
                init_17_0_2_1_edc0357_1360798765470_351482_20629Members();
                init_17_0_2_1_edc0357_1360798765470_351482_20629Collections();
                init_17_0_2_1_edc0357_1360798765470_351482_20629Elaborations();
            }

            public _17_0_2_1_edc0357_1360798765470_351482_20629(Expression<Integer> startTime) {
                super();
                init_17_0_2_1_edc0357_1360798765470_351482_20629Members();
                init_17_0_2_1_edc0357_1360798765470_351482_20629Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_1_edc0357_1360798765470_351482_20629Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter objectToPass = null;

            public Dependency finalNode_startTimeDependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Effect effect207 = null;

            public Parameter effect207Var = null;

            public void init_17_0_2_1_edc0357_1360798765470_351482_20629Members() {
                try {
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect207VarV = sig_17_0_2_1_edc0357_1360798765477_693138_20658;
                    effect207Var = new Parameter("effect207Var", null, null, this);
                    addDependency(effect207Var, new Expression(effect207VarV));
                    effect207 = new EffectFunction(new EffectFunction(effect207Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "DIFF_SCENARIO", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Boolean>(objectToPass), new Expression<Integer>(endTime) }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_1_edc0357_1360798765470_351482_20629Collections() {
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect207Var = new TreeSet<Effect>();
                effectsForeffect207Var.add(effect207);
                addEffects((Parameter<?>) effect207Var, effectsForeffect207Var);
            }

            public void init_17_0_2_1_edc0357_1360798765470_351482_20629Dependencies() {
                addDependency(finalNode_startTime, new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))));
                addDependency(duration, new Expression<Integer>(14400));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_1_edc0357_1360798765477_681208_20657, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "DIFF_SCENARIO", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_1_edc0357_1360798765470_351482_20629Elaborations() {
                init_17_0_2_1_edc0357_1360798765470_351482_20629Dependencies();
            }
        }

        public class _17_0_2_1_edc0357_1360798765471_478130_20630 extends DurativeEvent {

            public _17_0_2_1_edc0357_1360798765471_478130_20630() {
                super();
                init_17_0_2_1_edc0357_1360798765471_478130_20630Members();
                init_17_0_2_1_edc0357_1360798765471_478130_20630Collections();
                init_17_0_2_1_edc0357_1360798765471_478130_20630Elaborations();
            }

            public _17_0_2_1_edc0357_1360798765471_478130_20630(Expression<Integer> startTime) {
                super();
                init_17_0_2_1_edc0357_1360798765471_478130_20630Members();
                init_17_0_2_1_edc0357_1360798765471_478130_20630Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_1_edc0357_1360798765471_478130_20630Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_1_edc0357_1360798765467_815816_20625_exists = null;

            public BooleanParameter objectToPass = null;

            public Dependency _17_0_2_1_edc0357_1360798765467_815816_20625_existsDependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Effect effect208 = null;

            public Parameter effect208Var = null;

            public ElaborationRule elaborationRule209 = null;

            public void init_17_0_2_1_edc0357_1360798765471_478130_20630Members() {
                try {
                    if (_17_0_2_1_edc0357_1360798765467_815816_20625_exists == null) _17_0_2_1_edc0357_1360798765467_815816_20625_exists = new BooleanParameter("_17_0_2_1_edc0357_1360798765467_815816_20625_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect208VarV = sig_17_0_2_1_edc0357_1360798765477_583668_20660;
                    effect208Var = new Parameter("effect208Var", null, null, this);
                    addDependency(effect208Var, new Expression(effect208VarV));
                    effect208 = new EffectFunction(new EffectFunction(effect208Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "DIFF_SCENARIO", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Boolean>(objectToPass), new Expression<Integer>(endTime) }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_1_edc0357_1360798765471_478130_20630Collections() {
                parameters.add(_17_0_2_1_edc0357_1360798765467_815816_20625_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect208Var = new TreeSet<Effect>();
                effectsForeffect208Var.add(effect208);
                addEffects((Parameter<?>) effect208Var, effectsForeffect208Var);
            }

            public void init_17_0_2_1_edc0357_1360798765471_478130_20630Dependencies() {
                addDependency(_17_0_2_1_edc0357_1360798765467_815816_20625_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765468_931127_20626_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765468_931127_20626_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(300));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_1_edc0357_1360798765477_228170_20659, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "DIFF_SCENARIO", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_1_edc0357_1360798765471_478130_20630Elaborations() {
                init_17_0_2_1_edc0357_1360798765471_478130_20630Dependencies();
                Expression<?>[] arguments209 = new Expression<?>[2];
                arguments209[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                arguments209[1] = new Expression<ObjectFlow<Boolean>>(sig_17_0_2_1_edc0357_1360798765477_583668_20660);
                Expression<Boolean> condition209 = new Expression<Boolean>(_17_0_2_1_edc0357_1360798765467_815816_20625_exists);
                elaborationRule209 = addElaborationRule(condition209, _17_0_2_1_edc0357_1360798765022_187244_20251.this, Power._17_0_2_1_edc0357_1360798765022_187244_20251._17_0_2_1_edc0357_1360798765467_815816_20625.class, "P_gen_merge__MergeNode__PowerCB__Power", arguments209);
            }
        }

        public class _17_0_2_1_edc0357_1360798765472_324054_20632 extends DurativeEvent {

            public _17_0_2_1_edc0357_1360798765472_324054_20632() {
                super();
                init_17_0_2_1_edc0357_1360798765472_324054_20632Members();
                init_17_0_2_1_edc0357_1360798765472_324054_20632Collections();
                init_17_0_2_1_edc0357_1360798765472_324054_20632Elaborations();
            }

            public _17_0_2_1_edc0357_1360798765472_324054_20632(Expression<Integer> startTime, Expression<ObjectFlow<Boolean>> receiveThis) {
                super();
                init_17_0_2_1_edc0357_1360798765472_324054_20632Members();
                init_17_0_2_1_edc0357_1360798765472_324054_20632Collections();
                addDependency(this.startTime, startTime);
                addDependency(this.receiveThis, receiveThis);
                init_17_0_2_1_edc0357_1360798765472_324054_20632Elaborations();
                fixTimeDependencies();
            }

            public Parameter< ObjectFlow<Boolean> > receiveThis = null;

            public BooleanParameter _17_0_2_1_edc0357_1360879079292_32519_15939_exists = null;

            public BooleanParameter objectToPass = null;

            public Dependency _17_0_2_1_edc0357_1360879079292_32519_15939_existsDependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Effect effect210 = null;

            public Parameter effect210Var = null;

            public ElaborationRule elaborationRule211 = null;

            public void init_17_0_2_1_edc0357_1360798765472_324054_20632Members() {
                try {
                    if (receiveThis == null) receiveThis = new Parameter<ObjectFlow<Boolean>>("receiveThis", null, (ObjectFlow<Boolean>) null, this);
                    if (_17_0_2_1_edc0357_1360879079292_32519_15939_exists == null) _17_0_2_1_edc0357_1360879079292_32519_15939_exists = new BooleanParameter("_17_0_2_1_edc0357_1360879079292_32519_15939_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect210VarV = sig_17_0_2_1_edc0357_1360798765477_758751_20664;
                    effect210Var = new Parameter("effect210Var", null, null, this);
                    addDependency(effect210Var, new Expression(effect210VarV));
                    effect210 = new EffectFunction(new EffectFunction(effect210Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "DIFF_SCENARIO", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Boolean>(objectToPass), new Expression<Integer>(endTime) }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_1_edc0357_1360798765472_324054_20632Collections() {
                parameters.add(receiveThis);
                parameters.add(_17_0_2_1_edc0357_1360879079292_32519_15939_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect210Var = new TreeSet<Effect>();
                effectsForeffect210Var.add(effect210);
                addEffects((Parameter<?>) effect210Var, effectsForeffect210Var);
            }

            public void init_17_0_2_1_edc0357_1360798765472_324054_20632Dependencies() {
                addDependency(_17_0_2_1_edc0357_1360879079292_32519_15939_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765468_931127_20626_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765468_931127_20626_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression(new EffectFunction(receiveThis, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "DIFF_SCENARIO", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_1_edc0357_1360798765472_324054_20632Elaborations() {
                init_17_0_2_1_edc0357_1360798765472_324054_20632Dependencies();
                Expression<?>[] arguments211 = new Expression<?>[1];
                arguments211[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition211 = new Expression<Boolean>(_17_0_2_1_edc0357_1360879079292_32519_15939_exists);
                elaborationRule211 = addElaborationRule(condition211, _17_0_2_1_edc0357_1360798765022_187244_20251.this, Power._17_0_2_1_edc0357_1360798765022_187244_20251._17_0_2_1_edc0357_1360879079292_32519_15939.class, "PGen_read_load_struct__ReadStructuralFeatureAction__PowerCB__Power", arguments211);
            }
        }

        public class _17_0_2_1_edc0357_1360798765473_313038_20633 extends DurativeEvent {

            public _17_0_2_1_edc0357_1360798765473_313038_20633() {
                super();
                init_17_0_2_1_edc0357_1360798765473_313038_20633Members();
                init_17_0_2_1_edc0357_1360798765473_313038_20633Collections();
                init_17_0_2_1_edc0357_1360798765473_313038_20633Elaborations();
            }

            public _17_0_2_1_edc0357_1360798765473_313038_20633(Expression<Integer> startTime) {
                super();
                init_17_0_2_1_edc0357_1360798765473_313038_20633Members();
                init_17_0_2_1_edc0357_1360798765473_313038_20633Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_1_edc0357_1360798765473_313038_20633Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_1_edc0357_1360798765472_324054_20632_exists = null;

            public BooleanParameter objectToPass = null;

            public Dependency _17_0_2_1_edc0357_1360798765472_324054_20632_existsDependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Effect effect212 = null;

            public Parameter effect212Var = null;

            public ElaborationRule elaborationRule213 = null;

            public void init_17_0_2_1_edc0357_1360798765473_313038_20633Members() {
                try {
                    if (_17_0_2_1_edc0357_1360798765472_324054_20632_exists == null) _17_0_2_1_edc0357_1360798765472_324054_20632_exists = new BooleanParameter("_17_0_2_1_edc0357_1360798765472_324054_20632_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect212VarV = sig_17_0_2_1_edc0357_1360798765478_201586_20666;
                    effect212Var = new Parameter("effect212Var", null, null, this);
                    addDependency(effect212Var, new Expression(effect212VarV));
                    effect212 = new EffectFunction(new EffectFunction(effect212Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "DIFF_SCENARIO", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Boolean>(objectToPass), new Expression<Integer>(endTime) }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_1_edc0357_1360798765473_313038_20633Collections() {
                parameters.add(_17_0_2_1_edc0357_1360798765472_324054_20632_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect212Var = new TreeSet<Effect>();
                effectsForeffect212Var.add(effect212);
                addEffects((Parameter<?>) effect212Var, effectsForeffect212Var);
            }

            public void init_17_0_2_1_edc0357_1360798765473_313038_20633Dependencies() {
                addDependency(_17_0_2_1_edc0357_1360798765472_324054_20632_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765468_931127_20626_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765468_931127_20626_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(300));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_1_edc0357_1360798765478_663072_20665, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "DIFF_SCENARIO", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_1_edc0357_1360798765473_313038_20633Elaborations() {
                init_17_0_2_1_edc0357_1360798765473_313038_20633Dependencies();
                Expression<?>[] arguments213 = new Expression<?>[2];
                arguments213[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                arguments213[1] = new Expression<ObjectFlow<Boolean>>(sig_17_0_2_1_edc0357_1360798765478_201586_20666);
                Expression<Boolean> condition213 = new Expression<Boolean>(_17_0_2_1_edc0357_1360798765472_324054_20632_exists);
                elaborationRule213 = addElaborationRule(condition213, _17_0_2_1_edc0357_1360798765022_187244_20251.this, Power._17_0_2_1_edc0357_1360798765022_187244_20251._17_0_2_1_edc0357_1360798765472_324054_20632.class, "P_merge_feeders__MergeNode__PowerCB__Power", arguments213);
            }
        }

        public class _17_0_2_1_edc0357_1360878349175_589211_15843 extends DurativeEvent {

            public _17_0_2_1_edc0357_1360878349175_589211_15843() {
                super();
                init_17_0_2_1_edc0357_1360878349175_589211_15843Members();
                init_17_0_2_1_edc0357_1360878349175_589211_15843Collections();
                init_17_0_2_1_edc0357_1360878349175_589211_15843Elaborations();
            }

            public _17_0_2_1_edc0357_1360878349175_589211_15843(Expression<Integer> startTime) {
                super();
                init_17_0_2_1_edc0357_1360878349175_589211_15843Members();
                init_17_0_2_1_edc0357_1360878349175_589211_15843Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_1_edc0357_1360878349175_589211_15843Elaborations();
                fixTimeDependencies();
            }

            public DoubleParameter _17_0_2_1_edc0357_1360878349177_108243_15844 = null;

            public BooleanParameter _17_0_2_1_edc0357_1360798765467_857766_20624_exists = null;

            public BooleanParameter objectToPass = null;

            public Dependency _17_0_2_1_edc0357_1360878349177_108243_15844Dependency = null;

            public Dependency _17_0_2_1_edc0357_1360798765467_857766_20624_existsDependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Effect effect214 = null;

            public Parameter effect214Var = null;

            public Effect effect215 = null;

            public Parameter effect215Var = null;

            public ElaborationRule elaborationRule216 = null;

            public void init_17_0_2_1_edc0357_1360878349175_589211_15843Members() {
                try {
                    if (_17_0_2_1_edc0357_1360878349177_108243_15844 == null) _17_0_2_1_edc0357_1360878349177_108243_15844 = new DoubleParameter("_17_0_2_1_edc0357_1360878349177_108243_15844", (Double) null, this);
                    if (_17_0_2_1_edc0357_1360798765467_857766_20624_exists == null) _17_0_2_1_edc0357_1360798765467_857766_20624_exists = new BooleanParameter("_17_0_2_1_edc0357_1360798765467_857766_20624_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect214VarV = sig_17_0_2_1_edc0357_1360798765476_328870_20652;
                    effect214Var = new Parameter("effect214Var", null, null, this);
                    addDependency(effect214Var, new Expression(effect214VarV));
                    effect214 = new EffectFunction(new EffectFunction(effect214Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "DIFF_SCENARIO", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Boolean>(objectToPass), new Expression<Integer>(endTime) }));
                    Object effect215VarV = load__17_0_2_1_edc0357_1360798765025_607442_20254;
                    effect215Var = new Parameter("effect215Var", null, null, this);
                    addDependency(effect215Var, new Expression(effect215VarV));
                    effect215 = new EffectFunction(new EffectFunction(effect215Var, ClassUtils.getMethodForArgTypes("TimeVaryingPlottableMap<Float>", "DIFF_SCENARIO", "setValue", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { new Expression<Integer>(startTime), new Expression<Double>(_17_0_2_1_edc0357_1360878349177_108243_15844) }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_1_edc0357_1360878349175_589211_15843Collections() {
                parameters.add(_17_0_2_1_edc0357_1360878349177_108243_15844);
                parameters.add(_17_0_2_1_edc0357_1360798765467_857766_20624_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect214Var = new TreeSet<Effect>();
                effectsForeffect214Var.add(effect214);
                addEffects((Parameter<?>) effect214Var, effectsForeffect214Var);
                Set<Effect> effectsForeffect215Var = new TreeSet<Effect>();
                effectsForeffect215Var.add(effect215);
                addEffects((Parameter<?>) effect215Var, effectsForeffect215Var);
            }

            public void init_17_0_2_1_edc0357_1360878349175_589211_15843Dependencies() {
                addDependency(_17_0_2_1_edc0357_1360878349177_108243_15844, new Expression(new EffectFunction(sig_17_0_2_1_edc0357_1360798765476_833192_20654, ClassUtils.getMethodForArgTypes("ObjectFlow<Float>", "DIFF_SCENARIO", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_1_edc0357_1360798765467_857766_20624_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765468_931127_20626_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765468_931127_20626_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(true));
            }

            public void init_17_0_2_1_edc0357_1360878349175_589211_15843Elaborations() {
                init_17_0_2_1_edc0357_1360878349175_589211_15843Dependencies();
                Expression<?>[] arguments216 = new Expression<?>[2];
                arguments216[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                arguments216[1] = new Expression<ObjectFlow<Boolean>>(sig_17_0_2_1_edc0357_1360798765476_328870_20652);
                Expression<Boolean> condition216 = new Expression<Boolean>(_17_0_2_1_edc0357_1360798765467_857766_20624_exists);
                elaborationRule216 = addElaborationRule(condition216, _17_0_2_1_edc0357_1360798765022_187244_20251.this, Power._17_0_2_1_edc0357_1360798765022_187244_20251._17_0_2_1_edc0357_1360798765467_857766_20624.class, "P_load_m__MergeNode__PowerCB__Power", arguments216);
            }
        }

        public class _17_0_2_1_edc0357_1360878683758_623651_15865 extends DurativeEvent {

            public _17_0_2_1_edc0357_1360878683758_623651_15865() {
                super();
                init_17_0_2_1_edc0357_1360878683758_623651_15865Members();
                init_17_0_2_1_edc0357_1360878683758_623651_15865Collections();
                init_17_0_2_1_edc0357_1360878683758_623651_15865Elaborations();
            }

            public _17_0_2_1_edc0357_1360878683758_623651_15865(Expression<Integer> startTime) {
                super();
                init_17_0_2_1_edc0357_1360878683758_623651_15865Members();
                init_17_0_2_1_edc0357_1360878683758_623651_15865Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_1_edc0357_1360878683758_623651_15865Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_1_edc0357_1360798765466_933706_20623_exists = null;

            public DoubleParameter _17_0_2_1_edc0357_1360878683759_142586_15866 = null;

            public BooleanParameter objectToPass = null;

            public Dependency _17_0_2_1_edc0357_1360798765466_933706_20623_existsDependency = null;

            public Dependency durationDependency = null;

            public Dependency _17_0_2_1_edc0357_1360878683759_142586_15866Dependency = null;

            public Dependency objectToPassDependency = null;

            public Effect effect217 = null;

            public Parameter effect217Var = null;

            public Effect effect218 = null;

            public Parameter effect218Var = null;

            public ElaborationRule elaborationRule219 = null;

            public void init_17_0_2_1_edc0357_1360878683758_623651_15865Members() {
                try {
                    if (_17_0_2_1_edc0357_1360798765466_933706_20623_exists == null) _17_0_2_1_edc0357_1360798765466_933706_20623_exists = new BooleanParameter("_17_0_2_1_edc0357_1360798765466_933706_20623_exists", (Boolean) false, this);
                    if (_17_0_2_1_edc0357_1360878683759_142586_15866 == null) _17_0_2_1_edc0357_1360878683759_142586_15866 = new DoubleParameter("_17_0_2_1_edc0357_1360878683759_142586_15866", (Double) null, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect217VarV = sig_17_0_2_1_edc0357_1360798765476_307646_20651;
                    effect217Var = new Parameter("effect217Var", null, null, this);
                    addDependency(effect217Var, new Expression(effect217VarV));
                    effect217 = new EffectFunction(new EffectFunction(effect217Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "DIFF_SCENARIO", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Boolean>(objectToPass), new Expression<Integer>(endTime) }));
                    Object effect218VarV = powerAugmentation__17_0_2_1_edc0357_1360798765029_789574_20260;
                    effect218Var = new Parameter("effect218Var", null, null, this);
                    addDependency(effect218Var, new Expression(effect218VarV));
                    effect218 = new EffectFunction(new EffectFunction(effect218Var, ClassUtils.getMethodForArgTypes("TimeVaryingMap<Float>", "DIFF_SCENARIO", "add", java.lang.Number.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Double>(_17_0_2_1_edc0357_1360878683759_142586_15866), new Expression<Integer>(startTime) }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_1_edc0357_1360878683758_623651_15865Collections() {
                parameters.add(_17_0_2_1_edc0357_1360798765466_933706_20623_exists);
                parameters.add(_17_0_2_1_edc0357_1360878683759_142586_15866);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect217Var = new TreeSet<Effect>();
                effectsForeffect217Var.add(effect217);
                addEffects((Parameter<?>) effect217Var, effectsForeffect217Var);
                Set<Effect> effectsForeffect218Var = new TreeSet<Effect>();
                effectsForeffect218Var.add(effect218);
                addEffects((Parameter<?>) effect218Var, effectsForeffect218Var);
            }

            public void init_17_0_2_1_edc0357_1360878683758_623651_15865Dependencies() {
                addDependency(_17_0_2_1_edc0357_1360798765466_933706_20623_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765468_931127_20626_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765468_931127_20626_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(_17_0_2_1_edc0357_1360878683759_142586_15866, new Expression(new EffectFunction(sig_17_0_2_1_edc0357_1360963130781_302800_23138, ClassUtils.getMethodForArgTypes("ObjectFlow<Float>", "DIFF_SCENARIO", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(objectToPass, new Expression<Boolean>(true));
            }

            public void init_17_0_2_1_edc0357_1360878683758_623651_15865Elaborations() {
                init_17_0_2_1_edc0357_1360878683758_623651_15865Dependencies();
                Expression<?>[] arguments219 = new Expression<?>[2];
                arguments219[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                arguments219[1] = new Expression<ObjectFlow<Boolean>>(sig_17_0_2_1_edc0357_1360798765476_307646_20651);
                Expression<Boolean> condition219 = new Expression<Boolean>(_17_0_2_1_edc0357_1360798765466_933706_20623_exists);
                elaborationRule219 = addElaborationRule(condition219, _17_0_2_1_edc0357_1360798765022_187244_20251.this, Power._17_0_2_1_edc0357_1360798765022_187244_20251._17_0_2_1_edc0357_1360798765466_933706_20623.class, "P_change_m__MergeNode__PowerCB__Power", arguments219);
            }
        }

        public class _17_0_2_1_edc0357_1360878700672_703950_15878 extends DurativeEvent {

            public _17_0_2_1_edc0357_1360878700672_703950_15878() {
                super();
                init_17_0_2_1_edc0357_1360878700672_703950_15878Members();
                init_17_0_2_1_edc0357_1360878700672_703950_15878Collections();
                init_17_0_2_1_edc0357_1360878700672_703950_15878Elaborations();
            }

            public _17_0_2_1_edc0357_1360878700672_703950_15878(Expression<Integer> startTime) {
                super();
                init_17_0_2_1_edc0357_1360878700672_703950_15878Members();
                init_17_0_2_1_edc0357_1360878700672_703950_15878Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_1_edc0357_1360878700672_703950_15878Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter objectToPass = null;

            public Parameter< Power_System.Signalgeneration_ack > signalObject = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Effect effect220 = null;

            public Parameter effect220Var = null;

            public Effect effect221 = null;

            public Parameter effect221Var = null;

            public void init_17_0_2_1_edc0357_1360878700672_703950_15878Members() {
                try {
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (signalObject == null) signalObject = new Parameter<Power_System.Signalgeneration_ack>("signalObject", null, (Power_System.Signalgeneration_ack) (new ConstructorCall(x, ClassUtils.getConstructorForArgTypes(Power_System.Signalgeneration_ack.class), new Object[] {})).evaluate(true), this);
                    Object effect220VarV = new FunctionCall(x, Parameter.class, "getMember", new Object[] { "ss_17_0_2_1_edc0357_1360961106142_903091_22490_generation_ack" });
                    effect220Var = new Parameter("effect220Var", null, null, this);
                    addDependency(effect220Var, new Expression(effect220VarV));
                    effect220 = new EffectFunction(new EffectFunction(null, ClassUtils.getMethodForArgTypes("ObjectFlow<Signalgeneration_ack>", "DIFF_SCENARIO", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Power_System.Signalgeneration_ack>(signalObject), new Expression<Integer>(endTime) }, effect220Var));
                    Object effect221VarV = new FunctionCall(signalObject, Parameter.class, "getMember", new Object[] { "control" });
                    effect221Var = new Parameter("effect221Var", null, null, this);
                    addDependency(effect221Var, new Expression(effect221VarV));
                    effect221 = new EffectFunction(new EffectFunction(null, ClassUtils.getMethodForArgTypes("TimeVaryingMap<Boolean>", "DIFF_SCENARIO", "setValue", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { new Expression<Integer>(endTime), new Expression<Boolean>(true) }, effect221Var));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_1_edc0357_1360878700672_703950_15878Collections() {
                parameters.add(objectToPass);
                parameters.add(signalObject);
                Set<Effect> effectsForeffect220Var = new TreeSet<Effect>();
                effectsForeffect220Var.add(effect220);
                addEffects((Parameter<?>) effect220Var, effectsForeffect220Var);
                Set<Effect> effectsForeffect221Var = new TreeSet<Effect>();
                effectsForeffect221Var.add(effect221);
                addEffects((Parameter<?>) effect221Var, effectsForeffect221Var);
            }

            public void init_17_0_2_1_edc0357_1360878700672_703950_15878Dependencies() {
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_1_edc0357_1360963135293_976442_23143, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "DIFF_SCENARIO", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_1_edc0357_1360878700672_703950_15878Elaborations() {
                init_17_0_2_1_edc0357_1360878700672_703950_15878Dependencies();
            }
        }

        public class _17_0_2_1_edc0357_1360879079292_32519_15939 extends DurativeEvent {

            public _17_0_2_1_edc0357_1360879079292_32519_15939() {
                super();
                init_17_0_2_1_edc0357_1360879079292_32519_15939Members();
                init_17_0_2_1_edc0357_1360879079292_32519_15939Collections();
                init_17_0_2_1_edc0357_1360879079292_32519_15939Elaborations();
            }

            public _17_0_2_1_edc0357_1360879079292_32519_15939(Expression<Integer> startTime) {
                super();
                init_17_0_2_1_edc0357_1360879079292_32519_15939Members();
                init_17_0_2_1_edc0357_1360879079292_32519_15939Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_1_edc0357_1360879079292_32519_15939Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_1_edc0357_1360879079293_57273_15940_exists = null;

            public BooleanParameter objectToPass = null;

            public DoubleParameter _17_0_2_1_edc0357_1360879079294_5124_15941 = null;

            public Dependency _17_0_2_1_edc0357_1360879079293_57273_15940_existsDependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Dependency _17_0_2_1_edc0357_1360879079294_5124_15941Dependency = null;

            public Effect effect222 = null;

            public Parameter effect222Var = null;

            public ElaborationRule elaborationRule223 = null;

            public void init_17_0_2_1_edc0357_1360879079292_32519_15939Members() {
                try {
                    if (_17_0_2_1_edc0357_1360879079293_57273_15940_exists == null) _17_0_2_1_edc0357_1360879079293_57273_15940_exists = new BooleanParameter("_17_0_2_1_edc0357_1360879079293_57273_15940_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (_17_0_2_1_edc0357_1360879079294_5124_15941 == null) _17_0_2_1_edc0357_1360879079294_5124_15941 = new DoubleParameter("_17_0_2_1_edc0357_1360879079294_5124_15941", (Double) null, this);
                    Object effect222VarV = sig_17_0_2_1_edc0357_1360879079295_184227_15943;
                    effect222Var = new Parameter("effect222Var", null, null, this);
                    addDependency(effect222Var, new Expression(effect222VarV));
                    effect222 = new EffectFunction(new EffectFunction(effect222Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Float>", "DIFF_SCENARIO", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Double>(_17_0_2_1_edc0357_1360879079294_5124_15941), new Expression<Integer>(endTime) }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_1_edc0357_1360879079292_32519_15939Collections() {
                parameters.add(_17_0_2_1_edc0357_1360879079293_57273_15940_exists);
                parameters.add(objectToPass);
                parameters.add(_17_0_2_1_edc0357_1360879079294_5124_15941);
                Set<Effect> effectsForeffect222Var = new TreeSet<Effect>();
                effectsForeffect222Var.add(effect222);
                addEffects((Parameter<?>) effect222Var, effectsForeffect222Var);
            }

            public void init_17_0_2_1_edc0357_1360879079292_32519_15939Dependencies() {
                addDependency(_17_0_2_1_edc0357_1360879079293_57273_15940_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765468_931127_20626_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765468_931127_20626_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_1_edc0357_1360798765477_758751_20664, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "DIFF_SCENARIO", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_1_edc0357_1360879079294_5124_15941, new Expression(new EffectFunction(load__17_0_2_1_edc0357_1360798765025_607442_20254, ClassUtils.getMethodForArgTypes("TimeVaryingPlottableMap<Float>", "DIFF_SCENARIO", "getValue", java.lang.Integer.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_1_edc0357_1360879079292_32519_15939Elaborations() {
                init_17_0_2_1_edc0357_1360879079292_32519_15939Dependencies();
                Expression<?>[] arguments223 = new Expression<?>[1];
                arguments223[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition223 = new Expression<Boolean>(_17_0_2_1_edc0357_1360879079293_57273_15940_exists);
                elaborationRule223 = addElaborationRule(condition223, _17_0_2_1_edc0357_1360798765022_187244_20251.this, Power._17_0_2_1_edc0357_1360798765022_187244_20251._17_0_2_1_edc0357_1360879079293_57273_15940.class, "PGen_send_load__SendSignalAction__PowerCB__Power", arguments223);
            }
        }

        public class _17_0_2_1_edc0357_1360879079293_57273_15940 extends DurativeEvent {

            public _17_0_2_1_edc0357_1360879079293_57273_15940() {
                super();
                init_17_0_2_1_edc0357_1360879079293_57273_15940Members();
                init_17_0_2_1_edc0357_1360879079293_57273_15940Collections();
                init_17_0_2_1_edc0357_1360879079293_57273_15940Elaborations();
            }

            public _17_0_2_1_edc0357_1360879079293_57273_15940(Expression<Integer> startTime) {
                super();
                init_17_0_2_1_edc0357_1360879079293_57273_15940Members();
                init_17_0_2_1_edc0357_1360879079293_57273_15940Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_1_edc0357_1360879079293_57273_15940Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter objectToPass = null;

            public Parameter< Power_System.SignalreceiveLoadReading > signalObject = null;

            public DoubleParameter _17_0_2_1_edc0357_1360879079295_434103_15942 = null;

            public BooleanParameter _17_0_2_1_edc0357_1360798765473_313038_20633_exists = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Dependency _17_0_2_1_edc0357_1360879079295_434103_15942Dependency = null;

            public Dependency _17_0_2_1_edc0357_1360798765473_313038_20633_existsDependency = null;

            public Effect effect224 = null;

            public Parameter effect224Var = null;

            public Effect effect225 = null;

            public Parameter effect225Var = null;

            public Effect effect226 = null;

            public Parameter effect226Var = null;

            public ElaborationRule elaborationRule227 = null;

            public void init_17_0_2_1_edc0357_1360879079293_57273_15940Members() {
                try {
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (signalObject == null) signalObject = new Parameter<Power_System.SignalreceiveLoadReading>("signalObject", null, (Power_System.SignalreceiveLoadReading) (new ConstructorCall(x, ClassUtils.getConstructorForArgTypes(Power_System.SignalreceiveLoadReading.class), new Object[] {})).evaluate(true), this);
                    if (_17_0_2_1_edc0357_1360879079295_434103_15942 == null) _17_0_2_1_edc0357_1360879079295_434103_15942 = new DoubleParameter("_17_0_2_1_edc0357_1360879079295_434103_15942", (Double) null, this);
                    if (_17_0_2_1_edc0357_1360798765473_313038_20633_exists == null) _17_0_2_1_edc0357_1360798765473_313038_20633_exists = new BooleanParameter("_17_0_2_1_edc0357_1360798765473_313038_20633_exists", (Boolean) false, this);
                    Object effect224VarV = sig_17_0_2_1_edc0357_1360798765478_663072_20665;
                    effect224Var = new Parameter("effect224Var", null, null, this);
                    addDependency(effect224Var, new Expression(effect224VarV));
                    effect224 = new EffectFunction(new EffectFunction(effect224Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "DIFF_SCENARIO", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Boolean>(objectToPass), new Expression<Integer>(endTime) }));
                    Object effect225VarV = new FunctionCall(x, Parameter.class, "getMember", new Object[] { "ss_17_0_2_1_edc0357_1360798765027_973745_20257_receiveLoadReading" });
                    effect225Var = new Parameter("effect225Var", null, null, this);
                    addDependency(effect225Var, new Expression(effect225VarV));
                    effect225 = new EffectFunction(new EffectFunction(null, ClassUtils.getMethodForArgTypes("ObjectFlow<SignalreceiveLoadReading>", "DIFF_SCENARIO", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Power_System.SignalreceiveLoadReading>(signalObject), new Expression<Integer>(endTime) }, effect225Var));
                    Object effect226VarV = new FunctionCall(signalObject, Parameter.class, "getMember", new Object[] { "actual_load__17_0_2_1_edc0357_1360798765095_91554_20351" });
                    effect226Var = new Parameter("effect226Var", null, null, this);
                    addDependency(effect226Var, new Expression(effect226VarV));
                    effect226 = new EffectFunction(new EffectFunction(null, ClassUtils.getMethodForArgTypes("TimeVaryingMap<Float>", "DIFF_SCENARIO", "setValue", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { new Expression<Integer>(endTime), new Expression<Double>(_17_0_2_1_edc0357_1360879079295_434103_15942) }, effect226Var));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_1_edc0357_1360879079293_57273_15940Collections() {
                parameters.add(objectToPass);
                parameters.add(signalObject);
                parameters.add(_17_0_2_1_edc0357_1360879079295_434103_15942);
                parameters.add(_17_0_2_1_edc0357_1360798765473_313038_20633_exists);
                Set<Effect> effectsForeffect224Var = new TreeSet<Effect>();
                effectsForeffect224Var.add(effect224);
                addEffects((Parameter<?>) effect224Var, effectsForeffect224Var);
                Set<Effect> effectsForeffect225Var = new TreeSet<Effect>();
                effectsForeffect225Var.add(effect225);
                addEffects((Parameter<?>) effect225Var, effectsForeffect225Var);
                Set<Effect> effectsForeffect226Var = new TreeSet<Effect>();
                effectsForeffect226Var.add(effect226);
                addEffects((Parameter<?>) effect226Var, effectsForeffect226Var);
            }

            public void init_17_0_2_1_edc0357_1360879079293_57273_15940Dependencies() {
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(true));
                addDependency(_17_0_2_1_edc0357_1360879079295_434103_15942, new Expression(new EffectFunction(sig_17_0_2_1_edc0357_1360879079295_184227_15943, ClassUtils.getMethodForArgTypes("ObjectFlow<Float>", "DIFF_SCENARIO", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_1_edc0357_1360798765473_313038_20633_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765468_931127_20626_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765468_931127_20626_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
            }

            public void init_17_0_2_1_edc0357_1360879079293_57273_15940Elaborations() {
                init_17_0_2_1_edc0357_1360879079293_57273_15940Dependencies();
                Expression<?>[] arguments227 = new Expression<?>[1];
                arguments227[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition227 = new Expression<Boolean>(_17_0_2_1_edc0357_1360798765473_313038_20633_exists);
                elaborationRule227 = addElaborationRule(condition227, _17_0_2_1_edc0357_1360798765022_187244_20251.this, Power._17_0_2_1_edc0357_1360798765022_187244_20251._17_0_2_1_edc0357_1360798765473_313038_20633.class, "P_wait_feeders__AcceptEventAction__PowerCB__Power", arguments227);
            }
        }

        public _17_0_2_1_edc0357_1360798765022_187244_20251(Expression<Integer> startTime) {
            super();
            init_17_0_2_1_edc0357_1360798765022_187244_20251Members();
            init_17_0_2_1_edc0357_1360798765022_187244_20251Collections();
            addDependency(this.startTime, startTime);
            init_17_0_2_1_edc0357_1360798765022_187244_20251Elaborations();
            fixTimeDependencies();
        }

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_1_edc0357_1360798765475_844019_20645 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_1_edc0357_1360798765477_934669_20662 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_1_edc0357_1360798765477_228170_20659 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_1_edc0357_1360798765475_964511_20649 = null;

        public Parameter< ObjectFlow<Power_System.SignalchangeGenerationValue> > sig_17_0_2_1_edc0357_1360798765476_487996_20655 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_1_edc0357_1360798765476_307646_20651 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_1_edc0357_1360798765477_773280_20663 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_1_edc0357_1360798765475_389092_20646 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_1_edc0357_1360798765476_584348_20650 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_1_edc0357_1360798765475_531734_20647 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_1_edc0357_1360798765476_328870_20652 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_1_edc0357_1360798765478_663072_20665 = null;

        public Parameter< ObjectFlow<Float> > sig_17_0_2_1_edc0357_1360798765476_833192_20654 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_1_edc0357_1360798765477_583668_20660 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_1_edc0357_1360798765475_501086_20648 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_1_edc0357_1360798765477_693138_20658 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_1_edc0357_1360798765477_758751_20664 = null;

        public IntegerParameter finalNode_endTime = null;

        public Parameter< ObjectFlow<Power_System.SignalchangeGenerationValue> > sig_17_0_2_1_edc0357_1360798764887_813421_20111 = null;

        public Parameter< ObjectFlow<Float> > sig_17_0_2_1_edc0357_1360879079295_184227_15943 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_1_edc0357_1360798765477_681208_20657 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_1_edc0357_1360798765478_201586_20666 = null;

        public Parameter< ObjectFlow<Power_System.SignalchangeLoadValue> > sig_17_0_2_1_edc0357_1360798764888_165874_20112 = null;

        public IntegerParameter finalNode_startTime = null;

        public Parameter< ObjectFlow<Float> > sig_17_0_2_1_edc0357_1360963130781_302800_23138 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_1_edc0357_1360963135293_976442_23143 = null;

        public BooleanParameter _17_0_2_1_edc0357_1360798765468_931127_20626_exists = null;

        public Parameter< ObjectFlow<Power_System.SignalchangeLoadValue> > sig_17_0_2_1_edc0357_1360798765476_99835_20653 = null;

        public Dependency endTimeDependency = null;

        public Dependency _17_0_2_1_edc0357_1360798765468_931127_20626_existsDependency = null;

        public ElaborationRule elaborationRule174 = null;

        public ElaborationRule elaborationRule175 = null;

        public void init_17_0_2_1_edc0357_1360798765022_187244_20251Members() {
            try {
                if (sig_17_0_2_1_edc0357_1360798765475_844019_20645 == null) sig_17_0_2_1_edc0357_1360798765475_844019_20645 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_1_edc0357_1360798765475_844019_20645", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360798765475_844019_20645" })).evaluate(true), this);
                if (sig_17_0_2_1_edc0357_1360798765477_934669_20662 == null) sig_17_0_2_1_edc0357_1360798765477_934669_20662 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_1_edc0357_1360798765477_934669_20662", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360798765477_934669_20662" })).evaluate(true), this);
                if (sig_17_0_2_1_edc0357_1360798765477_228170_20659 == null) sig_17_0_2_1_edc0357_1360798765477_228170_20659 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_1_edc0357_1360798765477_228170_20659", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360798765477_228170_20659" })).evaluate(true), this);
                if (sig_17_0_2_1_edc0357_1360798765475_964511_20649 == null) sig_17_0_2_1_edc0357_1360798765475_964511_20649 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_1_edc0357_1360798765475_964511_20649", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360798765475_964511_20649" })).evaluate(true), this);
                if (sig_17_0_2_1_edc0357_1360798765476_487996_20655 == null) sig_17_0_2_1_edc0357_1360798765476_487996_20655 = new Parameter<ObjectFlow<Power_System.SignalchangeGenerationValue>>("sig_17_0_2_1_edc0357_1360798765476_487996_20655", null, (ObjectFlow<Power_System.SignalchangeGenerationValue>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360798765476_487996_20655" })).evaluate(true), this);
                if (sig_17_0_2_1_edc0357_1360798765476_307646_20651 == null) sig_17_0_2_1_edc0357_1360798765476_307646_20651 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_1_edc0357_1360798765476_307646_20651", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360798765476_307646_20651" })).evaluate(true), this);
                if (sig_17_0_2_1_edc0357_1360798765477_773280_20663 == null) sig_17_0_2_1_edc0357_1360798765477_773280_20663 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_1_edc0357_1360798765477_773280_20663", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360798765477_773280_20663" })).evaluate(true), this);
                if (sig_17_0_2_1_edc0357_1360798765475_389092_20646 == null) sig_17_0_2_1_edc0357_1360798765475_389092_20646 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_1_edc0357_1360798765475_389092_20646", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360798765475_389092_20646" })).evaluate(true), this);
                if (sig_17_0_2_1_edc0357_1360798765476_584348_20650 == null) sig_17_0_2_1_edc0357_1360798765476_584348_20650 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_1_edc0357_1360798765476_584348_20650", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360798765476_584348_20650" })).evaluate(true), this);
                if (sig_17_0_2_1_edc0357_1360798765475_531734_20647 == null) sig_17_0_2_1_edc0357_1360798765475_531734_20647 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_1_edc0357_1360798765475_531734_20647", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360798765475_531734_20647" })).evaluate(true), this);
                if (sig_17_0_2_1_edc0357_1360798765476_328870_20652 == null) sig_17_0_2_1_edc0357_1360798765476_328870_20652 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_1_edc0357_1360798765476_328870_20652", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360798765476_328870_20652" })).evaluate(true), this);
                if (sig_17_0_2_1_edc0357_1360798765478_663072_20665 == null) sig_17_0_2_1_edc0357_1360798765478_663072_20665 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_1_edc0357_1360798765478_663072_20665", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360798765478_663072_20665" })).evaluate(true), this);
                if (sig_17_0_2_1_edc0357_1360798765476_833192_20654 == null) sig_17_0_2_1_edc0357_1360798765476_833192_20654 = new Parameter<ObjectFlow<Float>>("sig_17_0_2_1_edc0357_1360798765476_833192_20654", null, (ObjectFlow<Float>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360798765476_833192_20654" })).evaluate(true), this);
                if (sig_17_0_2_1_edc0357_1360798765477_583668_20660 == null) sig_17_0_2_1_edc0357_1360798765477_583668_20660 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_1_edc0357_1360798765477_583668_20660", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360798765477_583668_20660" })).evaluate(true), this);
                if (sig_17_0_2_1_edc0357_1360798765475_501086_20648 == null) sig_17_0_2_1_edc0357_1360798765475_501086_20648 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_1_edc0357_1360798765475_501086_20648", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360798765475_501086_20648" })).evaluate(true), this);
                if (sig_17_0_2_1_edc0357_1360798765477_693138_20658 == null) sig_17_0_2_1_edc0357_1360798765477_693138_20658 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_1_edc0357_1360798765477_693138_20658", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360798765477_693138_20658" })).evaluate(true), this);
                if (sig_17_0_2_1_edc0357_1360798765477_758751_20664 == null) sig_17_0_2_1_edc0357_1360798765477_758751_20664 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_1_edc0357_1360798765477_758751_20664", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360798765477_758751_20664" })).evaluate(true), this);
                if (finalNode_endTime == null) finalNode_endTime = new IntegerParameter("finalNode_endTime", (Integer) null, this);
                if (sig_17_0_2_1_edc0357_1360798764887_813421_20111 == null) sig_17_0_2_1_edc0357_1360798764887_813421_20111 = new Parameter<ObjectFlow<Power_System.SignalchangeGenerationValue>>("sig_17_0_2_1_edc0357_1360798764887_813421_20111", null, (ObjectFlow<Power_System.SignalchangeGenerationValue>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360798764887_813421_20111" })).evaluate(true), this);
                if (sig_17_0_2_1_edc0357_1360879079295_184227_15943 == null) sig_17_0_2_1_edc0357_1360879079295_184227_15943 = new Parameter<ObjectFlow<Float>>("sig_17_0_2_1_edc0357_1360879079295_184227_15943", null, (ObjectFlow<Float>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360879079295_184227_15943" })).evaluate(true), this);
                if (sig_17_0_2_1_edc0357_1360798765477_681208_20657 == null) sig_17_0_2_1_edc0357_1360798765477_681208_20657 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_1_edc0357_1360798765477_681208_20657", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360798765477_681208_20657" })).evaluate(true), this);
                if (sig_17_0_2_1_edc0357_1360798765478_201586_20666 == null) sig_17_0_2_1_edc0357_1360798765478_201586_20666 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_1_edc0357_1360798765478_201586_20666", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360798765478_201586_20666" })).evaluate(true), this);
                if (sig_17_0_2_1_edc0357_1360798764888_165874_20112 == null) sig_17_0_2_1_edc0357_1360798764888_165874_20112 = new Parameter<ObjectFlow<Power_System.SignalchangeLoadValue>>("sig_17_0_2_1_edc0357_1360798764888_165874_20112", null, (ObjectFlow<Power_System.SignalchangeLoadValue>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360798764888_165874_20112" })).evaluate(true), this);
                if (finalNode_startTime == null) finalNode_startTime = new IntegerParameter("finalNode_startTime", (Integer) null, this);
                if (sig_17_0_2_1_edc0357_1360963130781_302800_23138 == null) sig_17_0_2_1_edc0357_1360963130781_302800_23138 = new Parameter<ObjectFlow<Float>>("sig_17_0_2_1_edc0357_1360963130781_302800_23138", null, (ObjectFlow<Float>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360963130781_302800_23138" })).evaluate(true), this);
                if (sig_17_0_2_1_edc0357_1360963135293_976442_23143 == null) sig_17_0_2_1_edc0357_1360963135293_976442_23143 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_1_edc0357_1360963135293_976442_23143", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360963135293_976442_23143" })).evaluate(true), this);
                if (_17_0_2_1_edc0357_1360798765468_931127_20626_exists == null) _17_0_2_1_edc0357_1360798765468_931127_20626_exists = new BooleanParameter("_17_0_2_1_edc0357_1360798765468_931127_20626_exists", (Boolean) false, this);
                if (sig_17_0_2_1_edc0357_1360798765476_99835_20653 == null) sig_17_0_2_1_edc0357_1360798765476_99835_20653 = new Parameter<ObjectFlow<Power_System.SignalchangeLoadValue>>("sig_17_0_2_1_edc0357_1360798765476_99835_20653", null, (ObjectFlow<Power_System.SignalchangeLoadValue>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360798765476_99835_20653" })).evaluate(true), this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void init_17_0_2_1_edc0357_1360798765022_187244_20251Collections() {
            parameters.add(sig_17_0_2_1_edc0357_1360798765475_844019_20645);
            parameters.add(sig_17_0_2_1_edc0357_1360798765477_934669_20662);
            parameters.add(sig_17_0_2_1_edc0357_1360798765477_228170_20659);
            parameters.add(sig_17_0_2_1_edc0357_1360798765475_964511_20649);
            parameters.add(sig_17_0_2_1_edc0357_1360798765476_487996_20655);
            parameters.add(sig_17_0_2_1_edc0357_1360798765476_307646_20651);
            parameters.add(sig_17_0_2_1_edc0357_1360798765477_773280_20663);
            parameters.add(sig_17_0_2_1_edc0357_1360798765475_389092_20646);
            parameters.add(sig_17_0_2_1_edc0357_1360798765476_584348_20650);
            parameters.add(sig_17_0_2_1_edc0357_1360798765475_531734_20647);
            parameters.add(sig_17_0_2_1_edc0357_1360798765476_328870_20652);
            parameters.add(sig_17_0_2_1_edc0357_1360798765478_663072_20665);
            parameters.add(sig_17_0_2_1_edc0357_1360798765476_833192_20654);
            parameters.add(sig_17_0_2_1_edc0357_1360798765477_583668_20660);
            parameters.add(sig_17_0_2_1_edc0357_1360798765475_501086_20648);
            parameters.add(sig_17_0_2_1_edc0357_1360798765477_693138_20658);
            parameters.add(sig_17_0_2_1_edc0357_1360798765477_758751_20664);
            parameters.add(finalNode_endTime);
            parameters.add(sig_17_0_2_1_edc0357_1360798764887_813421_20111);
            parameters.add(sig_17_0_2_1_edc0357_1360879079295_184227_15943);
            parameters.add(sig_17_0_2_1_edc0357_1360798765477_681208_20657);
            parameters.add(sig_17_0_2_1_edc0357_1360798765478_201586_20666);
            parameters.add(sig_17_0_2_1_edc0357_1360798764888_165874_20112);
            parameters.add(finalNode_startTime);
            parameters.add(sig_17_0_2_1_edc0357_1360963130781_302800_23138);
            parameters.add(sig_17_0_2_1_edc0357_1360963135293_976442_23143);
            parameters.add(_17_0_2_1_edc0357_1360798765468_931127_20626_exists);
            parameters.add(sig_17_0_2_1_edc0357_1360798765476_99835_20653);
        }

        public void init_17_0_2_1_edc0357_1360798765022_187244_20251Dependencies() {
            addDependency(endTime, new Expression<Integer>(finalNode_endTime));
            addDependency(_17_0_2_1_edc0357_1360798765468_931127_20626_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.NotEquals(new Expression<Integer>(finalNode_startTime), null)), new Expression(new EffectFunction(sig_17_0_2_1_edc0357_1360798765477_693138_20658, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "DIFF_SCENARIO", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(new Functions.Minus(new Expression<Integer>(finalNode_startTime), new Expression<Integer>(1))) })))));
        }

        public void init_17_0_2_1_edc0357_1360798765022_187244_20251Elaborations() {
            init_17_0_2_1_edc0357_1360798765022_187244_20251Dependencies();
            Expression<?>[] arguments174 = new Expression<?>[1];
            arguments174[0] = new Expression<Integer>(finalNode_startTime);
            Expression<Boolean> condition174 = new Expression<Boolean>(_17_0_2_1_edc0357_1360798765468_931127_20626_exists);
            elaborationRule174 = addElaborationRule(condition174, _17_0_2_1_edc0357_1360798765022_187244_20251.this, Power._17_0_2_1_edc0357_1360798765022_187244_20251._17_0_2_1_edc0357_1360798765468_931127_20626.class, "P_CBFinal__ActivityFinalNode__PowerCB__Power", arguments174);
            Expression<?>[] arguments175 = new Expression<?>[1];
            arguments175[0] = new Expression<Integer>(startTime);
            Expression<Boolean> condition175 = new Expression<Boolean>(true);
            elaborationRule175 = addElaborationRule(condition175, _17_0_2_1_edc0357_1360798765022_187244_20251.this, Power._17_0_2_1_edc0357_1360798765022_187244_20251._17_0_2_1_edc0357_1360798765461_594674_20615.class, "P_start__InitialNode__PowerCB__Power", arguments175);
        }
    }

    public Power(Power_System x) {
        super();
        this.x = new Parameter<Power_System>("Power_System", null, x, null);
        initPowerMembers();
        initPowerCollections();
        initPowerElaborations();
    }

    public StringParameter classifierBehavior = null;

    public Parameter< ObjectFlow<Power_System.Signalgeneration_ack> > q_Power_generation_ack = null;

    public Parameter< TimeVaryingProjection<Float> > genProfile__17_0_2_1_edc0357_1360798765028_430905_20259 = null;

    public Parameter< ObjectFlow<Power_System.SignalreceiveGenReading> > q_Power_receiveGenReading = null;

    public Parameter< ObjectFlow<Power_System.SignalchangeGenerationValue> > q_Power_changeGenerationValue = null;

    public Parameter< TimeVaryingMap<Float> > powerAugmentation__17_0_2_1_edc0357_1360798765029_789574_20260 = null;

    public Parameter< TimeVaryingPlottableMap<Float> > load__17_0_2_1_edc0357_1360798765025_607442_20254 = null;

    public Parameter< Power_System > x = null;

    public Parameter< TimeVaryingPlottableMap<Float> > power__17_0_2_1_edc0357_1360798765025_253187_20253 = null;

    public Parameter< ObjectFlow<Power_System.SignalreceiveLoadReading> > q_Power_receiveLoadReading = null;

    public Parameter< ObjectFlow<Power_System.SignalchangeLoadValue> > q_Power_changeLoadValue = null;

    public void initPowerMembers() {
        try {
            if (classifierBehavior == null) classifierBehavior = new StringParameter("classifierBehavior", (String) "_17_0_2_1_edc0357_1360798765022_187244_20251", this);
            if (q_Power_generation_ack == null) q_Power_generation_ack = new Parameter<ObjectFlow<Power_System.Signalgeneration_ack>>("q_Power_generation_ack", null, (ObjectFlow<Power_System.Signalgeneration_ack>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class, java.lang.Class.class), new Object[] { "q_Power_generation_ack", Power_System.Signalgeneration_ack.class })).evaluate(true), this);
            if (genProfile__17_0_2_1_edc0357_1360798765028_430905_20259 == null) genProfile__17_0_2_1_edc0357_1360798765028_430905_20259 = new Parameter<TimeVaryingProjection<Float>>("genProfile__17_0_2_1_edc0357_1360798765028_430905_20259", null, (TimeVaryingProjection<Float>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingProjection.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Class.class), new Object[] { "genProfile", "test_gen.csv", null, Float.class })).evaluate(true), this);
            if (q_Power_receiveGenReading == null) q_Power_receiveGenReading = new Parameter<ObjectFlow<Power_System.SignalreceiveGenReading>>("q_Power_receiveGenReading", null, (ObjectFlow<Power_System.SignalreceiveGenReading>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class, java.lang.Class.class), new Object[] { "q_Power_receiveGenReading", Power_System.SignalreceiveGenReading.class })).evaluate(true), this);
            if (q_Power_changeGenerationValue == null) q_Power_changeGenerationValue = new Parameter<ObjectFlow<Power_System.SignalchangeGenerationValue>>("q_Power_changeGenerationValue", null, (ObjectFlow<Power_System.SignalchangeGenerationValue>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class, java.lang.Class.class), new Object[] { "q_Power_changeGenerationValue", Power_System.SignalchangeGenerationValue.class })).evaluate(true), this);
            if (powerAugmentation__17_0_2_1_edc0357_1360798765029_789574_20260 == null) powerAugmentation__17_0_2_1_edc0357_1360798765029_789574_20260 = new Parameter<TimeVaryingMap<Float>>("powerAugmentation__17_0_2_1_edc0357_1360798765029_789574_20260", null, (TimeVaryingMap<Float>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingMap.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Class.class), new Object[] { "powerAugmentation", null, 0.0, Float.class })).evaluate(true), this);
            if (load__17_0_2_1_edc0357_1360798765025_607442_20254 == null) load__17_0_2_1_edc0357_1360798765025_607442_20254 = new Parameter<TimeVaryingPlottableMap<Float>>("load__17_0_2_1_edc0357_1360798765025_607442_20254", null, (TimeVaryingPlottableMap<Float>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingPlottableMap.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Class.class), new Object[] { "load", null, 0.0, Float.class })).evaluate(true), this);
            if (x == null) x = new Parameter<Power_System>("x", null, (Power_System) null, this);
            if (power__17_0_2_1_edc0357_1360798765025_253187_20253 == null) power__17_0_2_1_edc0357_1360798765025_253187_20253 = new Parameter<TimeVaryingPlottableMap<Float>>("power__17_0_2_1_edc0357_1360798765025_253187_20253", null, (TimeVaryingPlottableMap<Float>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingPlottableMap.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Class.class), new Object[] { "power", null, 0.0, Float.class })).evaluate(true), this);
            if (q_Power_receiveLoadReading == null) q_Power_receiveLoadReading = new Parameter<ObjectFlow<Power_System.SignalreceiveLoadReading>>("q_Power_receiveLoadReading", null, (ObjectFlow<Power_System.SignalreceiveLoadReading>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class, java.lang.Class.class), new Object[] { "q_Power_receiveLoadReading", Power_System.SignalreceiveLoadReading.class })).evaluate(true), this);
            if (q_Power_changeLoadValue == null) q_Power_changeLoadValue = new Parameter<ObjectFlow<Power_System.SignalchangeLoadValue>>("q_Power_changeLoadValue", null, (ObjectFlow<Power_System.SignalchangeLoadValue>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class, java.lang.Class.class), new Object[] { "q_Power_changeLoadValue", Power_System.SignalchangeLoadValue.class })).evaluate(true), this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void initPowerCollections() {
        parameters.add(classifierBehavior);
        parameters.add(q_Power_generation_ack);
        parameters.add(genProfile__17_0_2_1_edc0357_1360798765028_430905_20259);
        parameters.add(q_Power_receiveGenReading);
        parameters.add(q_Power_changeGenerationValue);
        parameters.add(powerAugmentation__17_0_2_1_edc0357_1360798765029_789574_20260);
        parameters.add(load__17_0_2_1_edc0357_1360798765025_607442_20254);
        parameters.add(x);
        parameters.add(power__17_0_2_1_edc0357_1360798765025_253187_20253);
        parameters.add(q_Power_receiveLoadReading);
        parameters.add(q_Power_changeLoadValue);
    }

    public void initPowerDependencies() {
    }

    public void initPowerElaborations() {
        initPowerDependencies();
    }
}
