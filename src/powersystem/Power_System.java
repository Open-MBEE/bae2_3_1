package powersystem;

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
import java.lang.Math;
import java.util.Set;
import java.util.TreeSet;
import gov.nasa.jpl.ae.event.EffectFunction;

public class Power_System extends ParameterListenerImpl {

    public Power_System() {
        super();
        initPower_SystemMembers();
        initPower_SystemCollections();
        initPower_SystemElaborations();
        ((ObjectFlow) ss_17_0_2_edc0357_1352328156650_180051_19621_changeLoadValue.getValue()).addListener(((ObjectFlow) (p.getValue()).q_Power_changeLoadValue.getValue()));
        ((ObjectFlow) ss_17_0_2_edc0357_1352328156880_813680_19691_changeGenerationValue.getValue()).addListener(((ObjectFlow) (p.getValue()).q_Power_changeGenerationValue.getValue()));
        ((ObjectFlow) ss_17_0_2_edc0357_1352328156878_93426_19688_dr_request.getValue()).addListener(((ObjectFlow) (c.getValue()).q_Customer_dr_request.getValue()));
        ((ObjectFlow) ss_17_0_2_edc0357_1352328156651_739611_19623_yes.getValue()).addListener(((ObjectFlow) (l.getValue()).q_LADWP_yes.getValue()));
        ((ObjectFlow) ss_17_0_2_edc0357_1352328156651_739611_19623_no.getValue()).addListener(((ObjectFlow) (l.getValue()).q_LADWP_no.getValue()));
        ((ObjectFlow) ss_17_0_2_edc0357_1352328156658_447229_19624_receiveMeterReading.getValue()).addListener(((ObjectFlow) (l.getValue()).q_LADWP_receiveMeterReading.getValue()));
        ((ObjectFlow) ss_17_0_2_edc0357_1352328156634_484825_19608_receiveLoadReading.getValue()).addListener(((ObjectFlow) (l.getValue()).q_LADWP_receiveLoadReading.getValue()));
        ((ObjectFlow) ss_17_0_2_edc0357_1352328156634_627908_19609_receiveGenReading.getValue()).addListener(((ObjectFlow) (l.getValue()).q_LADWP_receiveGenReading.getValue()));
       
    }

    public class _17_0_2_edc0357_1352328156860_564082_19664 extends DurativeEvent {

        public _17_0_2_edc0357_1352328156860_564082_19664() {
            super();
            init_17_0_2_edc0357_1352328156860_564082_19664Members();
            init_17_0_2_edc0357_1352328156860_564082_19664Collections();
            init_17_0_2_edc0357_1352328156860_564082_19664Elaborations();
        }

        public class _17_0_2_edc0357_1352328158898_944741_20522 extends DurativeEvent {

            public _17_0_2_edc0357_1352328158898_944741_20522() {
                super();
                init_17_0_2_edc0357_1352328158898_944741_20522Members();
                init_17_0_2_edc0357_1352328158898_944741_20522Collections();
                init_17_0_2_edc0357_1352328158898_944741_20522Elaborations();
            }

            public _17_0_2_edc0357_1352328158898_944741_20522(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328158898_944741_20522Members();
                init_17_0_2_edc0357_1352328158898_944741_20522Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328158898_944741_20522Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_edc0357_1352328158899_697852_20523_exists = null;

            public BooleanParameter objectToPass = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158899_697852_20523_existsDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Effect effect2 = null;

            public Parameter effect2Var = null;

            public ElaborationRule elaborationRule3 = null;

            public void init_17_0_2_edc0357_1352328158898_944741_20522Members() {
                try {
                    if (_17_0_2_edc0357_1352328158899_697852_20523_exists == null) _17_0_2_edc0357_1352328158899_697852_20523_exists = new BooleanParameter("_17_0_2_edc0357_1352328158899_697852_20523_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect2VarV = sig_17_0_2_edc0357_1352328158902_909422_20535;
                    effect2Var = new Parameter("effect2Var", null, null, this);
                    addDependency(effect2Var, new Expression(effect2VarV));
                    effect2 = new EffectFunction(new FunctionCall(effect2Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158898_944741_20522Collections() {
                parameters.add(_17_0_2_edc0357_1352328158899_697852_20523_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect2Var = new TreeSet<Effect>();
                effectsForeffect2Var.add(effect2);
                addEffects((Parameter<?>) effect2Var, effectsForeffect2Var);
            }

            public void init_17_0_2_edc0357_1352328158898_944741_20522Dependencies() {
                addDependency(_17_0_2_edc0357_1352328158899_697852_20523_exists, new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(true));
            }

            public void init_17_0_2_edc0357_1352328158898_944741_20522Elaborations() {
                init_17_0_2_edc0357_1352328158898_944741_20522Dependencies();
                Expression<?>[] arguments3 = new Expression<?>[1];
                arguments3[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition3 = new Expression<Boolean>(_17_0_2_edc0357_1352328158899_697852_20523_exists);
                elaborationRule3 = addElaborationRule(condition3, _17_0_2_edc0357_1352328156860_564082_19664.this, Power_System._17_0_2_edc0357_1352328156860_564082_19664._17_0_2_edc0357_1352328158899_697852_20523.class, "_ReadSelfAction_start_system", arguments3);
            }
        }

        public class _17_0_2_edc0357_1352328158899_697852_20523 extends DurativeEvent {

            public _17_0_2_edc0357_1352328158899_697852_20523() {
                super();
                init_17_0_2_edc0357_1352328158899_697852_20523Members();
                init_17_0_2_edc0357_1352328158899_697852_20523Collections();
                init_17_0_2_edc0357_1352328158899_697852_20523Elaborations();
            }

            public _17_0_2_edc0357_1352328158899_697852_20523(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328158899_697852_20523Members();
                init_17_0_2_edc0357_1352328158899_697852_20523Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328158899_697852_20523Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_edc0357_1352328158899_466748_20524_exists = null;

            public BooleanParameter objectToPass = null;

            public Parameter< Power_System > _17_0_2_edc0357_1352328160592_994026_21549 = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158899_466748_20524_existsDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Effect effect4 = null;

            public Parameter effect4Var = null;

            public ElaborationRule elaborationRule5 = null;

            public void init_17_0_2_edc0357_1352328158899_697852_20523Members() {
                try {
                    if (_17_0_2_edc0357_1352328158899_466748_20524_exists == null) _17_0_2_edc0357_1352328158899_466748_20524_exists = new BooleanParameter("_17_0_2_edc0357_1352328158899_466748_20524_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (_17_0_2_edc0357_1352328160592_994026_21549 == null) _17_0_2_edc0357_1352328160592_994026_21549 = new Parameter<Power_System>("_17_0_2_edc0357_1352328160592_994026_21549", null, (Power_System) Power_System.this, this);
                    Object effect4VarV = sig_17_0_2_edc0357_1352328158902_646081_20536;
                    effect4Var = new Parameter("effect4Var", null, null, this);
                    addDependency(effect4Var, new Expression(effect4VarV));
                    effect4 = new EffectFunction(new FunctionCall(effect4Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Power_System>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { _17_0_2_edc0357_1352328160592_994026_21549, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158899_697852_20523Collections() {
                parameters.add(_17_0_2_edc0357_1352328158899_466748_20524_exists);
                parameters.add(objectToPass);
                parameters.add(_17_0_2_edc0357_1352328160592_994026_21549);
                Set<Effect> effectsForeffect4Var = new TreeSet<Effect>();
                effectsForeffect4Var.add(effect4);
                addEffects((Parameter<?>) effect4Var, effectsForeffect4Var);
            }

            public void init_17_0_2_edc0357_1352328158899_697852_20523Dependencies() {
                addDependency(_17_0_2_edc0357_1352328158899_466748_20524_exists, new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new FunctionCall(sig_17_0_2_edc0357_1352328158902_909422_20535, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_edc0357_1352328158899_697852_20523Elaborations() {
                init_17_0_2_edc0357_1352328158899_697852_20523Dependencies();
                Expression<?>[] arguments5 = new Expression<?>[1];
                arguments5[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition5 = new Expression<Boolean>(_17_0_2_edc0357_1352328158899_466748_20524_exists);
                elaborationRule5 = addElaborationRule(condition5, _17_0_2_edc0357_1352328156860_564082_19664.this, Power_System._17_0_2_edc0357_1352328156860_564082_19664._17_0_2_edc0357_1352328158899_466748_20524.class, "_ForkNode_start_system", arguments5);
            }
        }

        public class _17_0_2_edc0357_1352328158899_466748_20524 extends DurativeEvent {

            public _17_0_2_edc0357_1352328158899_466748_20524() {
                super();
                init_17_0_2_edc0357_1352328158899_466748_20524Members();
                init_17_0_2_edc0357_1352328158899_466748_20524Collections();
                init_17_0_2_edc0357_1352328158899_466748_20524Elaborations();
            }

            public _17_0_2_edc0357_1352328158899_466748_20524(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328158899_466748_20524Members();
                init_17_0_2_edc0357_1352328158899_466748_20524Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328158899_466748_20524Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_edc0357_1352328158899_839106_20525_exists = null;

            public BooleanParameter _17_0_2_edc0357_1352328158899_755559_20526_exists = null;

            public BooleanParameter _17_0_2_edc0357_1352328158901_612739_20529_exists = null;

            public Parameter< Power_System > objectToPass = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158899_839106_20525_existsDependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158899_755559_20526_existsDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158901_612739_20529_existsDependency = null;

            public Dependency< Power_System > objectToPassDependency = null;

            public Effect effect6 = null;

            public Parameter effect6Var = null;

            public Effect effect7 = null;

            public Parameter effect7Var = null;

            public Effect effect8 = null;

            public Parameter effect8Var = null;

            public ElaborationRule elaborationRule9 = null;

            public ElaborationRule elaborationRule10 = null;

            public ElaborationRule elaborationRule11 = null;

            public void init_17_0_2_edc0357_1352328158899_466748_20524Members() {
                try {
                    if (_17_0_2_edc0357_1352328158899_839106_20525_exists == null) _17_0_2_edc0357_1352328158899_839106_20525_exists = new BooleanParameter("_17_0_2_edc0357_1352328158899_839106_20525_exists", (Boolean) false, this);
                    if (_17_0_2_edc0357_1352328158899_755559_20526_exists == null) _17_0_2_edc0357_1352328158899_755559_20526_exists = new BooleanParameter("_17_0_2_edc0357_1352328158899_755559_20526_exists", (Boolean) false, this);
                    if (_17_0_2_edc0357_1352328158901_612739_20529_exists == null) _17_0_2_edc0357_1352328158901_612739_20529_exists = new BooleanParameter("_17_0_2_edc0357_1352328158901_612739_20529_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new Parameter<Power_System>("objectToPass", null, (Power_System) null, this);
                    Object effect6VarV = sig_17_0_2_edc0357_1352328158902_808129_20537;
                    effect6Var = new Parameter("effect6Var", null, null, this);
                    addDependency(effect6Var, new Expression(effect6VarV));
                    effect6 = new EffectFunction(new FunctionCall(effect6Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Power_System>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect7VarV = sig_17_0_2_edc0357_1352328158903_140352_20538;
                    effect7Var = new Parameter("effect7Var", null, null, this);
                    addDependency(effect7Var, new Expression(effect7VarV));
                    effect7 = new EffectFunction(new FunctionCall(effect7Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Power_System>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect8VarV = sig_17_0_2_edc0357_1352328158903_395129_20542;
                    effect8Var = new Parameter("effect8Var", null, null, this);
                    addDependency(effect8Var, new Expression(effect8VarV));
                    effect8 = new EffectFunction(new FunctionCall(effect8Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Power_System>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158899_466748_20524Collections() {
                parameters.add(_17_0_2_edc0357_1352328158899_839106_20525_exists);
                parameters.add(_17_0_2_edc0357_1352328158899_755559_20526_exists);
                parameters.add(_17_0_2_edc0357_1352328158901_612739_20529_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect6Var = new TreeSet<Effect>();
                effectsForeffect6Var.add(effect6);
                addEffects((Parameter<?>) effect6Var, effectsForeffect6Var);
                Set<Effect> effectsForeffect7Var = new TreeSet<Effect>();
                effectsForeffect7Var.add(effect7);
                addEffects((Parameter<?>) effect7Var, effectsForeffect7Var);
                Set<Effect> effectsForeffect8Var = new TreeSet<Effect>();
                effectsForeffect8Var.add(effect8);
                addEffects((Parameter<?>) effect8Var, effectsForeffect8Var);
            }

            public void init_17_0_2_edc0357_1352328158899_466748_20524Dependencies() {
                addDependency(_17_0_2_edc0357_1352328158899_839106_20525_exists, new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall));
                addDependency(_17_0_2_edc0357_1352328158899_755559_20526_exists, new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(_17_0_2_edc0357_1352328158901_612739_20529_exists, new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall));
                addDependency(objectToPass, new Expression<Power_System>(new FunctionCall(sig_17_0_2_edc0357_1352328158902_646081_20536, ClassUtils.getMethodForArgTypes("ObjectFlow<Power_System>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_edc0357_1352328158899_466748_20524Elaborations() {
                init_17_0_2_edc0357_1352328158899_466748_20524Dependencies();
                Expression<?>[] arguments9 = new Expression<?>[1];
                arguments9[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition9 = new Expression<Boolean>(_17_0_2_edc0357_1352328158899_839106_20525_exists);
                elaborationRule9 = addElaborationRule(condition9, _17_0_2_edc0357_1352328156860_564082_19664.this, Power_System._17_0_2_edc0357_1352328156860_564082_19664._17_0_2_edc0357_1352328158899_839106_20525.class, "_ReadStructuralFeatureAction_start_system", arguments9);
                Expression<?>[] arguments10 = new Expression<?>[1];
                arguments10[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition10 = new Expression<Boolean>(_17_0_2_edc0357_1352328158899_755559_20526_exists);
                elaborationRule10 = addElaborationRule(condition10, _17_0_2_edc0357_1352328156860_564082_19664.this, Power_System._17_0_2_edc0357_1352328156860_564082_19664._17_0_2_edc0357_1352328158899_755559_20526.class, "_ReadStructuralFeatureAction_start_system", arguments10);
                Expression<?>[] arguments11 = new Expression<?>[1];
                arguments11[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition11 = new Expression<Boolean>(_17_0_2_edc0357_1352328158901_612739_20529_exists);
                elaborationRule11 = addElaborationRule(condition11, _17_0_2_edc0357_1352328156860_564082_19664.this, Power_System._17_0_2_edc0357_1352328156860_564082_19664._17_0_2_edc0357_1352328158901_612739_20529.class, "_ReadStructuralFeatureAction_start_system", arguments11);
            }
        }

        public class _17_0_2_edc0357_1352328158899_839106_20525 extends DurativeEvent {

            public _17_0_2_edc0357_1352328158899_839106_20525() {
                super();
                init_17_0_2_edc0357_1352328158899_839106_20525Members();
                init_17_0_2_edc0357_1352328158899_839106_20525Collections();
                init_17_0_2_edc0357_1352328158899_839106_20525Elaborations();
            }

            public _17_0_2_edc0357_1352328158899_839106_20525(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328158899_839106_20525Members();
                init_17_0_2_edc0357_1352328158899_839106_20525Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328158899_839106_20525Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_edc0357_1352328158899_583129_20527_exists = null;

            public Parameter< Customer > _17_0_2_edc0357_1352328160593_459942_21550 = null;

            public Parameter< Power_System > _17_0_2_edc0357_1352328160594_771935_21551 = null;

            public Dependency< Customer > _17_0_2_edc0357_1352328160593_459942_21550Dependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158899_583129_20527_existsDependency = null;

            public Dependency< Power_System > _17_0_2_edc0357_1352328160594_771935_21551Dependency = null;

            public Dependency< Integer > durationDependency = null;

            public Effect effect12 = null;

            public Parameter effect12Var = null;

            public ElaborationRule elaborationRule13 = null;

            public void init_17_0_2_edc0357_1352328158899_839106_20525Members() {
                try {
                    if (_17_0_2_edc0357_1352328158899_583129_20527_exists == null) _17_0_2_edc0357_1352328158899_583129_20527_exists = new BooleanParameter("_17_0_2_edc0357_1352328158899_583129_20527_exists", (Boolean) false, this);
                    if (_17_0_2_edc0357_1352328160593_459942_21550 == null) _17_0_2_edc0357_1352328160593_459942_21550 = new Parameter<Customer>("_17_0_2_edc0357_1352328160593_459942_21550", null, (Customer) null, this);
                    if (_17_0_2_edc0357_1352328160594_771935_21551 == null) _17_0_2_edc0357_1352328160594_771935_21551 = new Parameter<Power_System>("_17_0_2_edc0357_1352328160594_771935_21551", null, (Power_System) null, this);
                    Object effect12VarV = sig_17_0_2_edc0357_1352328158903_115744_20539;
                    effect12Var = new Parameter("effect12Var", null, null, this);
                    addDependency(effect12Var, new Expression(effect12VarV));
                    effect12 = new EffectFunction(new FunctionCall(effect12Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { _17_0_2_edc0357_1352328160593_459942_21550, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158899_839106_20525Collections() {
                parameters.add(_17_0_2_edc0357_1352328158899_583129_20527_exists);
                parameters.add(_17_0_2_edc0357_1352328160593_459942_21550);
                parameters.add(_17_0_2_edc0357_1352328160594_771935_21551);
                Set<Effect> effectsForeffect12Var = new TreeSet<Effect>();
                effectsForeffect12Var.add(effect12);
                addEffects((Parameter<?>) effect12Var, effectsForeffect12Var);
            }

            public void init_17_0_2_edc0357_1352328158899_839106_20525Dependencies() {
                addDependency(_17_0_2_edc0357_1352328160593_459942_21550, new Expression<Customer>(new FunctionCall(_17_0_2_edc0357_1352328160594_771935_21551, Parameter.class, "getMember", new Object[] { "c" })));
                addDependency(_17_0_2_edc0357_1352328158899_583129_20527_exists, new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall));
                addDependency(_17_0_2_edc0357_1352328160594_771935_21551, new Expression<Power_System>(new FunctionCall(sig_17_0_2_edc0357_1352328158902_808129_20537, ClassUtils.getMethodForArgTypes("ObjectFlow<Power_System>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(duration, new Expression<Integer>(1));
            }

            public void init_17_0_2_edc0357_1352328158899_839106_20525Elaborations() {
                init_17_0_2_edc0357_1352328158899_839106_20525Dependencies();
                Expression<?>[] arguments13 = new Expression<?>[1];
                arguments13[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition13 = new Expression<Boolean>(_17_0_2_edc0357_1352328158899_583129_20527_exists);
                elaborationRule13 = addElaborationRule(condition13, _17_0_2_edc0357_1352328156860_564082_19664.this, Power_System._17_0_2_edc0357_1352328156860_564082_19664._17_0_2_edc0357_1352328158899_583129_20527.class, "sobc_StartObjectBehaviorAction_start_system", arguments13);
            }
        }

        public class _17_0_2_edc0357_1352328158899_755559_20526 extends DurativeEvent {

            public _17_0_2_edc0357_1352328158899_755559_20526() {
                super();
                init_17_0_2_edc0357_1352328158899_755559_20526Members();
                init_17_0_2_edc0357_1352328158899_755559_20526Collections();
                init_17_0_2_edc0357_1352328158899_755559_20526Elaborations();
            }

            public _17_0_2_edc0357_1352328158899_755559_20526(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328158899_755559_20526Members();
                init_17_0_2_edc0357_1352328158899_755559_20526Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328158899_755559_20526Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_edc0357_1352328158900_885776_20528_exists = null;

            public Parameter< Power_System > _17_0_2_edc0357_1352328160595_899962_21553 = null;

            public Parameter< Power > _17_0_2_edc0357_1352328160594_3535_21552 = null;

            public IntegerParameter myDeciderID_decider_17_0_2_edc0357_1352328158900_885776_20528 = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158900_885776_20528_existsDependency = null;

            public Dependency< Power_System > _17_0_2_edc0357_1352328160595_899962_21553Dependency = null;

            public Dependency< Power > _17_0_2_edc0357_1352328160594_3535_21552Dependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Integer > myDeciderID_decider_17_0_2_edc0357_1352328158900_885776_20528Dependency = null;

            public Effect effect14 = null;

            public Parameter effect14Var = null;

            public Effect effect15 = null;

            public Parameter effect15Var = null;

            public ElaborationRule elaborationRule16 = null;

            public void init_17_0_2_edc0357_1352328158899_755559_20526Members() {
                try {
                    if (_17_0_2_edc0357_1352328158900_885776_20528_exists == null) _17_0_2_edc0357_1352328158900_885776_20528_exists = new BooleanParameter("_17_0_2_edc0357_1352328158900_885776_20528_exists", (Boolean) false, this);
                    if (_17_0_2_edc0357_1352328160595_899962_21553 == null) _17_0_2_edc0357_1352328160595_899962_21553 = new Parameter<Power_System>("_17_0_2_edc0357_1352328160595_899962_21553", null, (Power_System) null, this);
                    if (_17_0_2_edc0357_1352328160594_3535_21552 == null) _17_0_2_edc0357_1352328160594_3535_21552 = new Parameter<Power>("_17_0_2_edc0357_1352328160594_3535_21552", null, (Power) null, this);
                    if (myDeciderID_decider_17_0_2_edc0357_1352328158900_885776_20528 == null) myDeciderID_decider_17_0_2_edc0357_1352328158900_885776_20528 = new IntegerParameter("myDeciderID_decider_17_0_2_edc0357_1352328158900_885776_20528", (Integer) 1, this);
                    Object effect14VarV = sig_17_0_2_edc0357_1352328158903_950445_20540;
                    effect14Var = new Parameter("effect14Var", null, null, this);
                    addDependency(effect14Var, new Expression(effect14VarV));
                    effect14 = new EffectFunction(new FunctionCall(effect14Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Power>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { _17_0_2_edc0357_1352328160594_3535_21552, endTime }));
                    Object effect15VarV = decider_17_0_2_edc0357_1352328158900_885776_20528;
                    effect15Var = new Parameter("effect15Var", null, null, this);
                    addDependency(effect15Var, new Expression(effect15VarV));
                    effect15 = new EffectFunction(new FunctionCall(effect15Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, myDeciderID_decider_17_0_2_edc0357_1352328158900_885776_20528 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158899_755559_20526Collections() {
                parameters.add(_17_0_2_edc0357_1352328158900_885776_20528_exists);
                parameters.add(_17_0_2_edc0357_1352328160595_899962_21553);
                parameters.add(_17_0_2_edc0357_1352328160594_3535_21552);
                parameters.add(myDeciderID_decider_17_0_2_edc0357_1352328158900_885776_20528);
                Set<Effect> effectsForeffect14Var = new TreeSet<Effect>();
                effectsForeffect14Var.add(effect14);
                addEffects((Parameter<?>) effect14Var, effectsForeffect14Var);
                Set<Effect> effectsForeffect15Var = new TreeSet<Effect>();
                effectsForeffect15Var.add(effect15);
                addEffects((Parameter<?>) effect15Var, effectsForeffect15Var);
            }

            public void init_17_0_2_edc0357_1352328158899_755559_20526Dependencies() {
                addDependency(_17_0_2_edc0357_1352328158900_885776_20528_exists, new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158900_885776_20528, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158900_885776_20528, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "maxSize"), new Object[] {})))).functionCall), new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158900_885776_20528, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_edc0357_1352328158900_885776_20528))).functionCall))).functionCall), new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall))).functionCall));
                addDependency(_17_0_2_edc0357_1352328160595_899962_21553, new Expression<Power_System>(new FunctionCall(sig_17_0_2_edc0357_1352328158903_140352_20538, ClassUtils.getMethodForArgTypes("ObjectFlow<Power_System>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_edc0357_1352328160594_3535_21552, new Expression<Power>(new FunctionCall(_17_0_2_edc0357_1352328160595_899962_21553, Parameter.class, "getMember", new Object[] { "p" })));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(myDeciderID_decider_17_0_2_edc0357_1352328158900_885776_20528, new Expression<Integer>(1));
            }

            public void init_17_0_2_edc0357_1352328158899_755559_20526Elaborations() {
                init_17_0_2_edc0357_1352328158899_755559_20526Dependencies();
                Expression<?>[] arguments16 = new Expression<?>[1];
                arguments16[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition16 = new Expression<Boolean>(_17_0_2_edc0357_1352328158900_885776_20528_exists);
                elaborationRule16 = addElaborationRule(condition16, _17_0_2_edc0357_1352328156860_564082_19664.this, Power_System._17_0_2_edc0357_1352328156860_564082_19664._17_0_2_edc0357_1352328158900_885776_20528.class, "sobp_StartObjectBehaviorAction_start_system", arguments16);
            }
        }

        public class _17_0_2_edc0357_1352328158899_583129_20527 extends DurativeEvent {

            public _17_0_2_edc0357_1352328158899_583129_20527() {
                super();
                init_17_0_2_edc0357_1352328158899_583129_20527Members();
                init_17_0_2_edc0357_1352328158899_583129_20527Collections();
                init_17_0_2_edc0357_1352328158899_583129_20527Elaborations();
            }

            public _17_0_2_edc0357_1352328158899_583129_20527(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328158899_583129_20527Members();
                init_17_0_2_edc0357_1352328158899_583129_20527Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328158899_583129_20527Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_edc0357_1352328158900_885776_20528_exists = null;

            public Parameter< Customer > _17_0_2_edc0357_1352328160596_100645_21554 = null;

            public BooleanParameter objectToPass = null;

            public IntegerParameter myDeciderID_decider_17_0_2_edc0357_1352328158900_885776_20528 = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158900_885776_20528_existsDependency = null;

            public Dependency< Customer > _17_0_2_edc0357_1352328160596_100645_21554Dependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Dependency< Integer > myDeciderID_decider_17_0_2_edc0357_1352328158900_885776_20528Dependency = null;

            public Effect effect17 = null;

            public Parameter effect17Var = null;

            public Effect effect18 = null;

            public Parameter effect18Var = null;

            public ElaborationRule elaborationRule19 = null;

            public ElaborationRule elaborationRule20 = null;

            public void init_17_0_2_edc0357_1352328158899_583129_20527Members() {
                try {
                    if (_17_0_2_edc0357_1352328158900_885776_20528_exists == null) _17_0_2_edc0357_1352328158900_885776_20528_exists = new BooleanParameter("_17_0_2_edc0357_1352328158900_885776_20528_exists", (Boolean) false, this);
                    if (_17_0_2_edc0357_1352328160596_100645_21554 == null) _17_0_2_edc0357_1352328160596_100645_21554 = new Parameter<Customer>("_17_0_2_edc0357_1352328160596_100645_21554", null, (Customer) null, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (myDeciderID_decider_17_0_2_edc0357_1352328158900_885776_20528 == null) myDeciderID_decider_17_0_2_edc0357_1352328158900_885776_20528 = new IntegerParameter("myDeciderID_decider_17_0_2_edc0357_1352328158900_885776_20528", (Integer) 2, this);
                    Object effect17VarV = sig_17_0_2_edc0357_1352328158903_225177_20541;
                    effect17Var = new Parameter("effect17Var", null, null, this);
                    addDependency(effect17Var, new Expression(effect17VarV));
                    effect17 = new EffectFunction(new FunctionCall(effect17Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect18VarV = decider_17_0_2_edc0357_1352328158900_885776_20528;
                    effect18Var = new Parameter("effect18Var", null, null, this);
                    addDependency(effect18Var, new Expression(effect18VarV));
                    effect18 = new EffectFunction(new FunctionCall(effect18Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, myDeciderID_decider_17_0_2_edc0357_1352328158900_885776_20528 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158899_583129_20527Collections() {
                parameters.add(_17_0_2_edc0357_1352328158900_885776_20528_exists);
                parameters.add(_17_0_2_edc0357_1352328160596_100645_21554);
                parameters.add(objectToPass);
                parameters.add(myDeciderID_decider_17_0_2_edc0357_1352328158900_885776_20528);
                Set<Effect> effectsForeffect17Var = new TreeSet<Effect>();
                effectsForeffect17Var.add(effect17);
                addEffects((Parameter<?>) effect17Var, effectsForeffect17Var);
                Set<Effect> effectsForeffect18Var = new TreeSet<Effect>();
                effectsForeffect18Var.add(effect18);
                addEffects((Parameter<?>) effect18Var, effectsForeffect18Var);
            }

            public void init_17_0_2_edc0357_1352328158899_583129_20527Dependencies() {
                addDependency(_17_0_2_edc0357_1352328158900_885776_20528_exists, new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158900_885776_20528, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158900_885776_20528, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "maxSize"), new Object[] {})))).functionCall), new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158900_885776_20528, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_edc0357_1352328158900_885776_20528))).functionCall))).functionCall), new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall))).functionCall));
                addDependency(_17_0_2_edc0357_1352328160596_100645_21554, new Expression<Customer>(new FunctionCall(sig_17_0_2_edc0357_1352328158903_115744_20539, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(true));
                addDependency(myDeciderID_decider_17_0_2_edc0357_1352328158900_885776_20528, new Expression<Integer>(2));
            }

            public void init_17_0_2_edc0357_1352328158899_583129_20527Elaborations() {
                init_17_0_2_edc0357_1352328158899_583129_20527Dependencies();
                Expression<?>[] arguments19 = new Expression<?>[1];
                arguments19[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition19 = new Expression<Boolean>(_17_0_2_edc0357_1352328158900_885776_20528_exists);
                elaborationRule19 = addElaborationRule(condition19, _17_0_2_edc0357_1352328156860_564082_19664.this, Power_System._17_0_2_edc0357_1352328156860_564082_19664._17_0_2_edc0357_1352328158900_885776_20528.class, "sobp_StartObjectBehaviorAction_start_system", arguments19);
                Expression<?>[] arguments20 = new Expression<?>[1];
                arguments20[0] = new Expression<Integer>(startTime);
                Expression<Boolean> condition20 = new Expression<Boolean>(true);
                elaborationRule20 = addElaborationRule(condition20, c, Customer._17_0_2_edc0357_1352328156648_548830_19619.class, "CustomerCB_Activity_Customer", arguments20);
            }
        }

        public class _17_0_2_edc0357_1352328158900_885776_20528 extends DurativeEvent {

            public _17_0_2_edc0357_1352328158900_885776_20528() {
                super();
                init_17_0_2_edc0357_1352328158900_885776_20528Members();
                init_17_0_2_edc0357_1352328158900_885776_20528Collections();
                init_17_0_2_edc0357_1352328158900_885776_20528Elaborations();
            }

            public _17_0_2_edc0357_1352328158900_885776_20528(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328158900_885776_20528Members();
                init_17_0_2_edc0357_1352328158900_885776_20528Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328158900_885776_20528Elaborations();
                fixTimeDependencies();
            }

            public IntegerParameter myDeciderID_decider_17_0_2_edc0357_1352328158901_766276_20530 = null;

            public BooleanParameter _17_0_2_edc0357_1352328158901_766276_20530_exists = null;

            public Parameter< Power > _17_0_2_edc0357_1352328160597_889826_21555 = null;

            public BooleanParameter objectToPass = null;

            public Dependency< Integer > myDeciderID_decider_17_0_2_edc0357_1352328158901_766276_20530Dependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158901_766276_20530_existsDependency = null;

            public Dependency< Power > _17_0_2_edc0357_1352328160597_889826_21555Dependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Effect effect21 = null;

            public Parameter effect21Var = null;

            public Effect effect22 = null;

            public Parameter effect22Var = null;

            public ElaborationRule elaborationRule23 = null;

            public ElaborationRule elaborationRule24 = null;

            public void init_17_0_2_edc0357_1352328158900_885776_20528Members() {
                try {
                    if (myDeciderID_decider_17_0_2_edc0357_1352328158901_766276_20530 == null) myDeciderID_decider_17_0_2_edc0357_1352328158901_766276_20530 = new IntegerParameter("myDeciderID_decider_17_0_2_edc0357_1352328158901_766276_20530", (Integer) 2, this);
                    if (_17_0_2_edc0357_1352328158901_766276_20530_exists == null) _17_0_2_edc0357_1352328158901_766276_20530_exists = new BooleanParameter("_17_0_2_edc0357_1352328158901_766276_20530_exists", (Boolean) false, this);
                    if (_17_0_2_edc0357_1352328160597_889826_21555 == null) _17_0_2_edc0357_1352328160597_889826_21555 = new Parameter<Power>("_17_0_2_edc0357_1352328160597_889826_21555", null, (Power) null, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect21VarV = sig_17_0_2_edc0357_1352328158903_342012_20544;
                    effect21Var = new Parameter("effect21Var", null, null, this);
                    addDependency(effect21Var, new Expression(effect21VarV));
                    effect21 = new EffectFunction(new FunctionCall(effect21Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect22VarV = decider_17_0_2_edc0357_1352328158901_766276_20530;
                    effect22Var = new Parameter("effect22Var", null, null, this);
                    addDependency(effect22Var, new Expression(effect22VarV));
                    effect22 = new EffectFunction(new FunctionCall(effect22Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, myDeciderID_decider_17_0_2_edc0357_1352328158901_766276_20530 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158900_885776_20528Collections() {
                parameters.add(myDeciderID_decider_17_0_2_edc0357_1352328158901_766276_20530);
                parameters.add(_17_0_2_edc0357_1352328158901_766276_20530_exists);
                parameters.add(_17_0_2_edc0357_1352328160597_889826_21555);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect21Var = new TreeSet<Effect>();
                effectsForeffect21Var.add(effect21);
                addEffects((Parameter<?>) effect21Var, effectsForeffect21Var);
                Set<Effect> effectsForeffect22Var = new TreeSet<Effect>();
                effectsForeffect22Var.add(effect22);
                addEffects((Parameter<?>) effect22Var, effectsForeffect22Var);
            }

            public void init_17_0_2_edc0357_1352328158900_885776_20528Dependencies() {
                addDependency(myDeciderID_decider_17_0_2_edc0357_1352328158901_766276_20530, new Expression<Integer>(2));
                addDependency(_17_0_2_edc0357_1352328158901_766276_20530_exists, new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158901_766276_20530, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158901_766276_20530, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "maxSize"), new Object[] {})))).functionCall), new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158901_766276_20530, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_edc0357_1352328158901_766276_20530))).functionCall))).functionCall), new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall))).functionCall));
                addDependency(_17_0_2_edc0357_1352328160597_889826_21555, new Expression<Power>(new FunctionCall(sig_17_0_2_edc0357_1352328158903_950445_20540, ClassUtils.getMethodForArgTypes("ObjectFlow<Power>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new FunctionCall(sig_17_0_2_edc0357_1352328158903_225177_20541, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_edc0357_1352328158900_885776_20528Elaborations() {
                init_17_0_2_edc0357_1352328158900_885776_20528Dependencies();
                Expression<?>[] arguments23 = new Expression<?>[1];
                arguments23[0] = new Expression<Integer>(startTime);
                Expression<Boolean> condition23 = new Expression<Boolean>(true);
                elaborationRule23 = addElaborationRule(condition23, p, Power._17_0_2_edc0357_1352328156606_330566_19600.class, "PowerCB_Activity_Power", arguments23);
                Expression<?>[] arguments24 = new Expression<?>[1];
                arguments24[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition24 = new Expression<Boolean>(_17_0_2_edc0357_1352328158901_766276_20530_exists);
                elaborationRule24 = addElaborationRule(condition24, _17_0_2_edc0357_1352328156860_564082_19664.this, Power_System._17_0_2_edc0357_1352328156860_564082_19664._17_0_2_edc0357_1352328158901_766276_20530.class, "sobl_StartObjectBehaviorAction_start_system", arguments24);
            }
        }

        public class _17_0_2_edc0357_1352328158901_612739_20529 extends DurativeEvent {

            public _17_0_2_edc0357_1352328158901_612739_20529() {
                super();
                init_17_0_2_edc0357_1352328158901_612739_20529Members();
                init_17_0_2_edc0357_1352328158901_612739_20529Collections();
                init_17_0_2_edc0357_1352328158901_612739_20529Elaborations();
            }

            public _17_0_2_edc0357_1352328158901_612739_20529(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328158901_612739_20529Members();
                init_17_0_2_edc0357_1352328158901_612739_20529Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328158901_612739_20529Elaborations();
                fixTimeDependencies();
            }

            public IntegerParameter myDeciderID_decider_17_0_2_edc0357_1352328158901_766276_20530 = null;

            public Parameter< Power_System > _17_0_2_edc0357_1352328160598_207518_21557 = null;

            public BooleanParameter _17_0_2_edc0357_1352328158901_766276_20530_exists = null;

            public Parameter< LADWP > _17_0_2_edc0357_1352328160597_402739_21556 = null;

            public Dependency< Integer > myDeciderID_decider_17_0_2_edc0357_1352328158901_766276_20530Dependency = null;

            public Dependency< Power_System > _17_0_2_edc0357_1352328160598_207518_21557Dependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158901_766276_20530_existsDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< LADWP > _17_0_2_edc0357_1352328160597_402739_21556Dependency = null;

            public Effect effect25 = null;

            public Parameter effect25Var = null;

            public Effect effect26 = null;

            public Parameter effect26Var = null;

            public ElaborationRule elaborationRule27 = null;

            public void init_17_0_2_edc0357_1352328158901_612739_20529Members() {
                try {
                    if (myDeciderID_decider_17_0_2_edc0357_1352328158901_766276_20530 == null) myDeciderID_decider_17_0_2_edc0357_1352328158901_766276_20530 = new IntegerParameter("myDeciderID_decider_17_0_2_edc0357_1352328158901_766276_20530", (Integer) 1, this);
                    if (_17_0_2_edc0357_1352328160598_207518_21557 == null) _17_0_2_edc0357_1352328160598_207518_21557 = new Parameter<Power_System>("_17_0_2_edc0357_1352328160598_207518_21557", null, (Power_System) null, this);
                    if (_17_0_2_edc0357_1352328158901_766276_20530_exists == null) _17_0_2_edc0357_1352328158901_766276_20530_exists = new BooleanParameter("_17_0_2_edc0357_1352328158901_766276_20530_exists", (Boolean) false, this);
                    if (_17_0_2_edc0357_1352328160597_402739_21556 == null) _17_0_2_edc0357_1352328160597_402739_21556 = new Parameter<LADWP>("_17_0_2_edc0357_1352328160597_402739_21556", null, (LADWP) null, this);
                    Object effect25VarV = sig_17_0_2_edc0357_1352328158903_996128_20543;
                    effect25Var = new Parameter("effect25Var", null, null, this);
                    addDependency(effect25Var, new Expression(effect25VarV));
                    effect25 = new EffectFunction(new FunctionCall(effect25Var, ClassUtils.getMethodForArgTypes("ObjectFlow<LADWP>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { _17_0_2_edc0357_1352328160597_402739_21556, endTime }));
                    Object effect26VarV = decider_17_0_2_edc0357_1352328158901_766276_20530;
                    effect26Var = new Parameter("effect26Var", null, null, this);
                    addDependency(effect26Var, new Expression(effect26VarV));
                    effect26 = new EffectFunction(new FunctionCall(effect26Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, myDeciderID_decider_17_0_2_edc0357_1352328158901_766276_20530 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158901_612739_20529Collections() {
                parameters.add(myDeciderID_decider_17_0_2_edc0357_1352328158901_766276_20530);
                parameters.add(_17_0_2_edc0357_1352328160598_207518_21557);
                parameters.add(_17_0_2_edc0357_1352328158901_766276_20530_exists);
                parameters.add(_17_0_2_edc0357_1352328160597_402739_21556);
                Set<Effect> effectsForeffect25Var = new TreeSet<Effect>();
                effectsForeffect25Var.add(effect25);
                addEffects((Parameter<?>) effect25Var, effectsForeffect25Var);
                Set<Effect> effectsForeffect26Var = new TreeSet<Effect>();
                effectsForeffect26Var.add(effect26);
                addEffects((Parameter<?>) effect26Var, effectsForeffect26Var);
            }

            public void init_17_0_2_edc0357_1352328158901_612739_20529Dependencies() {
                addDependency(myDeciderID_decider_17_0_2_edc0357_1352328158901_766276_20530, new Expression<Integer>(1));
                addDependency(_17_0_2_edc0357_1352328160598_207518_21557, new Expression<Power_System>(new FunctionCall(sig_17_0_2_edc0357_1352328158903_395129_20542, ClassUtils.getMethodForArgTypes("ObjectFlow<Power_System>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_edc0357_1352328158901_766276_20530_exists, new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158901_766276_20530, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158901_766276_20530, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "maxSize"), new Object[] {})))).functionCall), new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158901_766276_20530, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_edc0357_1352328158901_766276_20530))).functionCall))).functionCall), new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall))).functionCall));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(_17_0_2_edc0357_1352328160597_402739_21556, new Expression<LADWP>(new FunctionCall(_17_0_2_edc0357_1352328160598_207518_21557, Parameter.class, "getMember", new Object[] { "l" })));
            }

            public void init_17_0_2_edc0357_1352328158901_612739_20529Elaborations() {
                init_17_0_2_edc0357_1352328158901_612739_20529Dependencies();
                Expression<?>[] arguments27 = new Expression<?>[1];
                arguments27[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition27 = new Expression<Boolean>(_17_0_2_edc0357_1352328158901_766276_20530_exists);
                elaborationRule27 = addElaborationRule(condition27, _17_0_2_edc0357_1352328156860_564082_19664.this, Power_System._17_0_2_edc0357_1352328156860_564082_19664._17_0_2_edc0357_1352328158901_766276_20530.class, "sobl_StartObjectBehaviorAction_start_system", arguments27);
            }
        }

        public class _17_0_2_edc0357_1352328158901_766276_20530 extends DurativeEvent {

            public _17_0_2_edc0357_1352328158901_766276_20530() {
                super();
                init_17_0_2_edc0357_1352328158901_766276_20530Members();
                init_17_0_2_edc0357_1352328158901_766276_20530Collections();
                init_17_0_2_edc0357_1352328158901_766276_20530Elaborations();
            }

            public _17_0_2_edc0357_1352328158901_766276_20530(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328158901_766276_20530Members();
                init_17_0_2_edc0357_1352328158901_766276_20530Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328158901_766276_20530Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_edc0357_1352328158902_127360_20531_exists = null;

            public BooleanParameter objectToPass = null;

            public Parameter< LADWP > _17_0_2_edc0357_1352328160598_767034_21558 = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158902_127360_20531_existsDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Dependency< LADWP > _17_0_2_edc0357_1352328160598_767034_21558Dependency = null;

            public Effect effect28 = null;

            public Parameter effect28Var = null;

            public ElaborationRule elaborationRule29 = null;

            public ElaborationRule elaborationRule30 = null;

            public void init_17_0_2_edc0357_1352328158901_766276_20530Members() {
                try {
                    if (_17_0_2_edc0357_1352328158902_127360_20531_exists == null) _17_0_2_edc0357_1352328158902_127360_20531_exists = new BooleanParameter("_17_0_2_edc0357_1352328158902_127360_20531_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (_17_0_2_edc0357_1352328160598_767034_21558 == null) _17_0_2_edc0357_1352328160598_767034_21558 = new Parameter<LADWP>("_17_0_2_edc0357_1352328160598_767034_21558", null, (LADWP) null, this);
                    Object effect28VarV = sig_17_0_2_edc0357_1352328158903_253903_20545;
                    effect28Var = new Parameter("effect28Var", null, null, this);
                    addDependency(effect28Var, new Expression(effect28VarV));
                    effect28 = new EffectFunction(new FunctionCall(effect28Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158901_766276_20530Collections() {
                parameters.add(_17_0_2_edc0357_1352328158902_127360_20531_exists);
                parameters.add(objectToPass);
                parameters.add(_17_0_2_edc0357_1352328160598_767034_21558);
                Set<Effect> effectsForeffect28Var = new TreeSet<Effect>();
                effectsForeffect28Var.add(effect28);
                addEffects((Parameter<?>) effect28Var, effectsForeffect28Var);
            }

            public void init_17_0_2_edc0357_1352328158901_766276_20530Dependencies() {
                addDependency(_17_0_2_edc0357_1352328158902_127360_20531_exists, new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new FunctionCall(sig_17_0_2_edc0357_1352328158903_342012_20544, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_edc0357_1352328160598_767034_21558, new Expression<LADWP>(new FunctionCall(sig_17_0_2_edc0357_1352328158903_996128_20543, ClassUtils.getMethodForArgTypes("ObjectFlow<LADWP>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_edc0357_1352328158901_766276_20530Elaborations() {
                init_17_0_2_edc0357_1352328158901_766276_20530Dependencies();
                Expression<?>[] arguments29 = new Expression<?>[1];
                arguments29[0] = new Expression<Integer>(startTime);
                Expression<Boolean> condition29 = new Expression<Boolean>(true);
                elaborationRule29 = addElaborationRule(condition29, l, LADWP._17_0_2_edc0357_1352328156875_238120_19686.class, "LADWP_CB_Activity_LADWP", arguments29);
                Expression<?>[] arguments30 = new Expression<?>[1];
                arguments30[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition30 = new Expression<Boolean>(_17_0_2_edc0357_1352328158902_127360_20531_exists);
                elaborationRule30 = addElaborationRule(condition30, _17_0_2_edc0357_1352328156860_564082_19664.this, Power_System._17_0_2_edc0357_1352328156860_564082_19664._17_0_2_edc0357_1352328158902_127360_20531.class, "_AcceptEventAction_start_system", arguments30);
            }
        }

        public class _17_0_2_edc0357_1352328158902_127360_20531 extends DurativeEvent {

            public _17_0_2_edc0357_1352328158902_127360_20531() {
                super();
                init_17_0_2_edc0357_1352328158902_127360_20531Members();
                init_17_0_2_edc0357_1352328158902_127360_20531Collections();
                init_17_0_2_edc0357_1352328158902_127360_20531Elaborations();
            }

            public _17_0_2_edc0357_1352328158902_127360_20531(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328158902_127360_20531Members();
                init_17_0_2_edc0357_1352328158902_127360_20531Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328158902_127360_20531Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter objectToPass = null;

            public Dependency< Integer > finalNode_startTimeDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Effect effect31 = null;

            public Parameter effect31Var = null;

            public void init_17_0_2_edc0357_1352328158902_127360_20531Members() {
                try {
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect31VarV = sig_17_0_2_edc0357_1352328158904_860755_20546;
                    effect31Var = new Parameter("effect31Var", null, null, this);
                    addDependency(effect31Var, new Expression(effect31VarV));
                    effect31 = new EffectFunction(new FunctionCall(effect31Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158902_127360_20531Collections() {
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect31Var = new TreeSet<Effect>();
                effectsForeffect31Var.add(effect31);
                addEffects((Parameter<?>) effect31Var, effectsForeffect31Var);
            }

            public void init_17_0_2_edc0357_1352328158902_127360_20531Dependencies() {
                addDependency(finalNode_startTime, new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall));
                addDependency(duration, new Expression<Integer>(2500));
                addDependency(objectToPass, new Expression<Boolean>(new FunctionCall(sig_17_0_2_edc0357_1352328158903_253903_20545, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_edc0357_1352328158902_127360_20531Elaborations() {
                init_17_0_2_edc0357_1352328158902_127360_20531Dependencies();
            }
        }

        public class _17_0_2_edc0357_1352328158902_583528_20532 extends DurativeEvent {

            public _17_0_2_edc0357_1352328158902_583528_20532() {
                super();
                init_17_0_2_edc0357_1352328158902_583528_20532Members();
                init_17_0_2_edc0357_1352328158902_583528_20532Collections();
                init_17_0_2_edc0357_1352328158902_583528_20532Elaborations();
            }

            public _17_0_2_edc0357_1352328158902_583528_20532(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328158902_583528_20532Members();
                init_17_0_2_edc0357_1352328158902_583528_20532Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328158902_583528_20532Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter objectToPass = null;

            public Dependency< Integer > endTimeDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Dependency< Integer > finalNode_endTimeDependency = null;

            public void init_17_0_2_edc0357_1352328158902_583528_20532Members() {
                try {
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158902_583528_20532Collections() {
                parameters.add(objectToPass);
            }

            public void init_17_0_2_edc0357_1352328158902_583528_20532Dependencies() {
                addDependency(endTime, new Expression<Integer>((new Functions.Plus(new Expression<Integer>(startTime), new Expression<Integer>(duration))).functionCall));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new FunctionCall(sig_17_0_2_edc0357_1352328158904_860755_20546, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(finalNode_endTime, new Expression<Integer>(endTime));
            }

            public void init_17_0_2_edc0357_1352328158902_583528_20532Elaborations() {
                init_17_0_2_edc0357_1352328158902_583528_20532Dependencies();
            }
        }

        public _17_0_2_edc0357_1352328156860_564082_19664(Expression<Integer> startTime) {
            super();
            init_17_0_2_edc0357_1352328156860_564082_19664Members();
            init_17_0_2_edc0357_1352328156860_564082_19664Collections();
            addDependency(this.startTime, startTime);
            init_17_0_2_edc0357_1352328156860_564082_19664Elaborations();
            fixTimeDependencies();
        }

        public Parameter< TimeVaryingList<Integer> > decider_17_0_2_edc0357_1352328158901_766276_20530 = null;

        public Parameter< ObjectFlow<Power_System> > sig_17_0_2_edc0357_1352328158902_646081_20536 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_edc0357_1352328158903_342012_20544 = null;

        public Parameter< ObjectFlow<Power> > sig_17_0_2_edc0357_1352328158903_950445_20540 = null;

        public Parameter< ObjectFlow<Power_System> > sig_17_0_2_edc0357_1352328158903_395129_20542 = null;

        public BooleanParameter _17_0_2_edc0357_1352328158902_583528_20532_exists = null;

        public Parameter< TimeVaryingList<Integer> > decider_17_0_2_edc0357_1352328158900_885776_20528 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_edc0357_1352328158904_860755_20546 = null;

        public Parameter< ObjectFlow<LADWP> > sig_17_0_2_edc0357_1352328158903_996128_20543 = null;

        public Parameter< ObjectFlow<Power_System> > sig_17_0_2_edc0357_1352328158903_140352_20538 = null;

        public IntegerParameter finalNode_startTime = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_edc0357_1352328158903_225177_20541 = null;

        public Parameter< ObjectFlow<Power_System> > sig_17_0_2_edc0357_1352328158902_808129_20537 = null;

        public IntegerParameter finalNode_endTime = null;

        public Parameter< ObjectFlow<Customer> > sig_17_0_2_edc0357_1352328158903_115744_20539 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_edc0357_1352328158903_253903_20545 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_edc0357_1352328158902_909422_20535 = null;

        public Dependency< Boolean > _17_0_2_edc0357_1352328158902_583528_20532_existsDependency = null;

        public Dependency< Integer > endTimeDependency = null;

        public ElaborationRule elaborationRule0 = null;

        public ElaborationRule elaborationRule1 = null;

        public void init_17_0_2_edc0357_1352328156860_564082_19664Members() {
            try {
                if (decider_17_0_2_edc0357_1352328158901_766276_20530 == null) decider_17_0_2_edc0357_1352328158901_766276_20530 = new Parameter<TimeVaryingList<Integer>>("decider_17_0_2_edc0357_1352328158901_766276_20530", null, (TimeVaryingList<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingList.class, java.lang.String.class, int.class), new Object[] { "decider_17_0_2_edc0357_1352328158901_766276_20530", 2 })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328158902_646081_20536 == null) sig_17_0_2_edc0357_1352328158902_646081_20536 = new Parameter<ObjectFlow<Power_System>>("sig_17_0_2_edc0357_1352328158902_646081_20536", null, (ObjectFlow<Power_System>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158902_646081_20536" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328158903_342012_20544 == null) sig_17_0_2_edc0357_1352328158903_342012_20544 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328158903_342012_20544", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158903_342012_20544" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328158903_950445_20540 == null) sig_17_0_2_edc0357_1352328158903_950445_20540 = new Parameter<ObjectFlow<Power>>("sig_17_0_2_edc0357_1352328158903_950445_20540", null, (ObjectFlow<Power>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158903_950445_20540" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328158903_395129_20542 == null) sig_17_0_2_edc0357_1352328158903_395129_20542 = new Parameter<ObjectFlow<Power_System>>("sig_17_0_2_edc0357_1352328158903_395129_20542", null, (ObjectFlow<Power_System>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158903_395129_20542" })).evaluate(true), this);
                if (_17_0_2_edc0357_1352328158902_583528_20532_exists == null) _17_0_2_edc0357_1352328158902_583528_20532_exists = new BooleanParameter("_17_0_2_edc0357_1352328158902_583528_20532_exists", (Boolean) false, this);
                if (decider_17_0_2_edc0357_1352328158900_885776_20528 == null) decider_17_0_2_edc0357_1352328158900_885776_20528 = new Parameter<TimeVaryingList<Integer>>("decider_17_0_2_edc0357_1352328158900_885776_20528", null, (TimeVaryingList<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingList.class, java.lang.String.class, int.class), new Object[] { "decider_17_0_2_edc0357_1352328158900_885776_20528", 2 })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328158904_860755_20546 == null) sig_17_0_2_edc0357_1352328158904_860755_20546 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328158904_860755_20546", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158904_860755_20546" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328158903_996128_20543 == null) sig_17_0_2_edc0357_1352328158903_996128_20543 = new Parameter<ObjectFlow<LADWP>>("sig_17_0_2_edc0357_1352328158903_996128_20543", null, (ObjectFlow<LADWP>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158903_996128_20543" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328158903_140352_20538 == null) sig_17_0_2_edc0357_1352328158903_140352_20538 = new Parameter<ObjectFlow<Power_System>>("sig_17_0_2_edc0357_1352328158903_140352_20538", null, (ObjectFlow<Power_System>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158903_140352_20538" })).evaluate(true), this);
                if (finalNode_startTime == null) finalNode_startTime = new IntegerParameter("finalNode_startTime", (Integer) 84000, this);
                if (sig_17_0_2_edc0357_1352328158903_225177_20541 == null) sig_17_0_2_edc0357_1352328158903_225177_20541 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328158903_225177_20541", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158903_225177_20541" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328158902_808129_20537 == null) sig_17_0_2_edc0357_1352328158902_808129_20537 = new Parameter<ObjectFlow<Power_System>>("sig_17_0_2_edc0357_1352328158902_808129_20537", null, (ObjectFlow<Power_System>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158902_808129_20537" })).evaluate(true), this);
                if (finalNode_endTime == null) finalNode_endTime = new IntegerParameter("finalNode_endTime", (Integer) 84000, this);
                if (sig_17_0_2_edc0357_1352328158903_115744_20539 == null) sig_17_0_2_edc0357_1352328158903_115744_20539 = new Parameter<ObjectFlow<Customer>>("sig_17_0_2_edc0357_1352328158903_115744_20539", null, (ObjectFlow<Customer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158903_115744_20539" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328158903_253903_20545 == null) sig_17_0_2_edc0357_1352328158903_253903_20545 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328158903_253903_20545", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158903_253903_20545" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328158902_909422_20535 == null) sig_17_0_2_edc0357_1352328158902_909422_20535 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328158902_909422_20535", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158902_909422_20535" })).evaluate(true), this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void init_17_0_2_edc0357_1352328156860_564082_19664Collections() {
            parameters.add(decider_17_0_2_edc0357_1352328158901_766276_20530);
            parameters.add(sig_17_0_2_edc0357_1352328158902_646081_20536);
            parameters.add(sig_17_0_2_edc0357_1352328158903_342012_20544);
            parameters.add(sig_17_0_2_edc0357_1352328158903_950445_20540);
            parameters.add(sig_17_0_2_edc0357_1352328158903_395129_20542);
            parameters.add(_17_0_2_edc0357_1352328158902_583528_20532_exists);
            parameters.add(decider_17_0_2_edc0357_1352328158900_885776_20528);
            parameters.add(sig_17_0_2_edc0357_1352328158904_860755_20546);
            parameters.add(sig_17_0_2_edc0357_1352328158903_996128_20543);
            parameters.add(sig_17_0_2_edc0357_1352328158903_140352_20538);
            parameters.add(finalNode_startTime);
            parameters.add(sig_17_0_2_edc0357_1352328158903_225177_20541);
            parameters.add(sig_17_0_2_edc0357_1352328158902_808129_20537);
            parameters.add(finalNode_endTime);
            parameters.add(sig_17_0_2_edc0357_1352328158903_115744_20539);
            parameters.add(sig_17_0_2_edc0357_1352328158903_253903_20545);
            parameters.add(sig_17_0_2_edc0357_1352328158902_909422_20535);
        }

        public void init_17_0_2_edc0357_1352328156860_564082_19664Dependencies() {
            addDependency(_17_0_2_edc0357_1352328158902_583528_20532_exists, new Expression<Boolean>(new FunctionCall(sig_17_0_2_edc0357_1352328158904_860755_20546, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>((new Functions.Minus(new Expression<Integer>(finalNode_startTime), new Expression<Integer>(1))).functionCall) })));
            addDependency(endTime, new Expression<Integer>(finalNode_endTime));
        }

        public void init_17_0_2_edc0357_1352328156860_564082_19664Elaborations() {
            init_17_0_2_edc0357_1352328156860_564082_19664Dependencies();
            Expression<?>[] arguments0 = new Expression<?>[1];
            arguments0[0] = new Expression<Integer>(startTime);
            Expression<Boolean> condition0 = new Expression<Boolean>(true);
            elaborationRule0 = addElaborationRule(condition0, _17_0_2_edc0357_1352328156860_564082_19664.this, Power_System._17_0_2_edc0357_1352328156860_564082_19664._17_0_2_edc0357_1352328158898_944741_20522.class, "_InitialNode_start_system", arguments0);
            Expression<?>[] arguments1 = new Expression<?>[1];
            arguments1[0] = new Expression<Integer>(finalNode_startTime);
            Expression<Boolean> condition1 = new Expression<Boolean>(_17_0_2_edc0357_1352328158902_583528_20532_exists);
            elaborationRule1 = addElaborationRule(condition1, _17_0_2_edc0357_1352328156860_564082_19664.this, Power_System._17_0_2_edc0357_1352328156860_564082_19664._17_0_2_edc0357_1352328158902_583528_20532.class, "_ActivityFinalNode_start_system", arguments1);
        }
    }

    public class SignalchangeLoadValue extends ParameterListenerImpl {

        public SignalchangeLoadValue() {
            super();
            initSignalchangeLoadValueMembers();
            initSignalchangeLoadValueCollections();
            initSignalchangeLoadValueElaborations();
        }

        public SignalchangeLoadValue(Timepoint t, Integer x) {
            super();
            initSignalchangeLoadValueMembers();
            initSignalchangeLoadValueCollections();
            initSignalchangeLoadValueElaborations();
            load__17_0_2_edc0357_1352328156636_307114_19611.getValue().setValue(t, x);
        }

        public Parameter< TimeVaryingMap<Integer> > load__17_0_2_edc0357_1352328156636_307114_19611 = null;

        public void initSignalchangeLoadValueMembers() {
            try {
                if (load__17_0_2_edc0357_1352328156636_307114_19611 == null) load__17_0_2_edc0357_1352328156636_307114_19611 = new Parameter<TimeVaryingMap<Integer>>("load__17_0_2_edc0357_1352328156636_307114_19611", null, (TimeVaryingMap<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingMap.class, java.lang.String.class), new Object[] { "load" })).evaluate(true), this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void initSignalchangeLoadValueCollections() {
            parameters.add(load__17_0_2_edc0357_1352328156636_307114_19611);
        }

        public void initSignalchangeLoadValueDependencies() {
        }

        public void initSignalchangeLoadValueElaborations() {
            initSignalchangeLoadValueDependencies();
        }
    }

    public class SignalchangeGenerationValue extends ParameterListenerImpl {

        public SignalchangeGenerationValue() {
            super();
            initSignalchangeGenerationValueMembers();
            initSignalchangeGenerationValueCollections();
            initSignalchangeGenerationValueElaborations();
        }

        public SignalchangeGenerationValue(Timepoint t, Integer x) {
            super();
            initSignalchangeGenerationValueMembers();
            initSignalchangeGenerationValueCollections();
            initSignalchangeGenerationValueElaborations();
            newGenValue__17_0_2_edc0357_1352328156635_470763_19610.getValue().setValue(t, x);
        }

        public Parameter< TimeVaryingMap<Integer> > newGenValue__17_0_2_edc0357_1352328156635_470763_19610 = null;

        public void initSignalchangeGenerationValueMembers() {
            try {
                if (newGenValue__17_0_2_edc0357_1352328156635_470763_19610 == null) newGenValue__17_0_2_edc0357_1352328156635_470763_19610 = new Parameter<TimeVaryingMap<Integer>>("newGenValue__17_0_2_edc0357_1352328156635_470763_19610", null, (TimeVaryingMap<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingMap.class, java.lang.String.class), new Object[] { "newGenValue" })).evaluate(true), this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void initSignalchangeGenerationValueCollections() {
            parameters.add(newGenValue__17_0_2_edc0357_1352328156635_470763_19610);
        }

        public void initSignalchangeGenerationValueDependencies() {
        }

        public void initSignalchangeGenerationValueElaborations() {
            initSignalchangeGenerationValueDependencies();
        }
    }

    public class Signaldr_request extends ParameterListenerImpl {

        public Signaldr_request() {
            super();
            initSignaldr_requestMembers();
            initSignaldr_requestCollections();
            initSignaldr_requestElaborations();
        }

        public Signaldr_request(Timepoint t, Integer x) {
            super();
            initSignaldr_requestMembers();
            initSignaldr_requestCollections();
            initSignaldr_requestElaborations();
            cap__17_0_2_edc0357_1352328156887_758776_19707.getValue().setValue(t, x);
        }

        public Parameter< TimeVaryingMap<Integer> > cap__17_0_2_edc0357_1352328156887_758776_19707 = null;

        public void initSignaldr_requestMembers() {
            try {
                if (cap__17_0_2_edc0357_1352328156887_758776_19707 == null) cap__17_0_2_edc0357_1352328156887_758776_19707 = new Parameter<TimeVaryingMap<Integer>>("cap__17_0_2_edc0357_1352328156887_758776_19707", null, (TimeVaryingMap<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingMap.class, java.lang.String.class), new Object[] { "cap" })).evaluate(true), this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void initSignaldr_requestCollections() {
            parameters.add(cap__17_0_2_edc0357_1352328156887_758776_19707);
        }

        public void initSignaldr_requestDependencies() {
        }

        public void initSignaldr_requestElaborations() {
            initSignaldr_requestDependencies();
        }
    }

    public class Signalyes extends ParameterListenerImpl {

        public Signalyes() {
            super();
            initSignalyesMembers();
            initSignalyesCollections();
            initSignalyesElaborations();
        }

        public Signalyes(Timepoint t, Integer x) {
            super();
            initSignalyesMembers();
            initSignalyesCollections();
            initSignalyesElaborations();
            newLoad__17_0_2_edc0357_1352328156888_707483_19708.getValue().setValue(t, x);
        }

        public Parameter< TimeVaryingMap<Integer> > newLoad__17_0_2_edc0357_1352328156888_707483_19708 = null;

        public void initSignalyesMembers() {
            try {
                if (newLoad__17_0_2_edc0357_1352328156888_707483_19708 == null) newLoad__17_0_2_edc0357_1352328156888_707483_19708 = new Parameter<TimeVaryingMap<Integer>>("newLoad__17_0_2_edc0357_1352328156888_707483_19708", null, (TimeVaryingMap<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingMap.class, java.lang.String.class), new Object[] { "newLoad" })).evaluate(true), this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void initSignalyesCollections() {
            parameters.add(newLoad__17_0_2_edc0357_1352328156888_707483_19708);
        }

        public void initSignalyesDependencies() {
        }

        public void initSignalyesElaborations() {
            initSignalyesDependencies();
        }
    }

    public class Signalno extends ParameterListenerImpl {

        public Signalno() {
            super();
            initSignalnoMembers();
            initSignalnoCollections();
            initSignalnoElaborations();
        }

        public Signalno(Timepoint t, Boolean x) {
            super();
            initSignalnoMembers();
            initSignalnoCollections();
            initSignalnoElaborations();
            control.getValue().setValue(t, x);
        }

        public Parameter< TimeVaryingMap<Boolean> > control = null;

        public void initSignalnoMembers() {
            try {
                if (control == null) control = new Parameter<TimeVaryingMap<Boolean>>("control", null, (TimeVaryingMap<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingMap.class, java.lang.String.class), new Object[] { "control" })).evaluate(true), this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void initSignalnoCollections() {
            parameters.add(control);
        }

        public void initSignalnoDependencies() {
        }

        public void initSignalnoElaborations() {
            initSignalnoDependencies();
        }
    }

    public class SignalreceiveMeterReading extends ParameterListenerImpl {

        public SignalreceiveMeterReading() {
            super();
            initSignalreceiveMeterReadingMembers();
            initSignalreceiveMeterReadingCollections();
            initSignalreceiveMeterReadingElaborations();
        }

        public SignalreceiveMeterReading(Timepoint t, Integer x) {
            super();
            initSignalreceiveMeterReadingMembers();
            initSignalreceiveMeterReadingCollections();
            initSignalreceiveMeterReadingElaborations();
            meter_value__17_0_2_edc0357_1352328156886_203596_19704.getValue().setValue(t, x);
        }

        public Parameter< TimeVaryingMap<Integer> > meter_value__17_0_2_edc0357_1352328156886_203596_19704 = null;

        public void initSignalreceiveMeterReadingMembers() {
            try {
                if (meter_value__17_0_2_edc0357_1352328156886_203596_19704 == null) meter_value__17_0_2_edc0357_1352328156886_203596_19704 = new Parameter<TimeVaryingMap<Integer>>("meter_value__17_0_2_edc0357_1352328156886_203596_19704", null, (TimeVaryingMap<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingMap.class, java.lang.String.class), new Object[] { "meter_value" })).evaluate(true), this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void initSignalreceiveMeterReadingCollections() {
            parameters.add(meter_value__17_0_2_edc0357_1352328156886_203596_19704);
        }

        public void initSignalreceiveMeterReadingDependencies() {
        }

        public void initSignalreceiveMeterReadingElaborations() {
            initSignalreceiveMeterReadingDependencies();
        }
    }

    public class SignalreceiveLoadReading extends ParameterListenerImpl {

        public SignalreceiveLoadReading() {
            super();
            initSignalreceiveLoadReadingMembers();
            initSignalreceiveLoadReadingCollections();
            initSignalreceiveLoadReadingElaborations();
        }

        public SignalreceiveLoadReading(Timepoint t, Integer x) {
            super();
            initSignalreceiveLoadReadingMembers();
            initSignalreceiveLoadReadingCollections();
            initSignalreceiveLoadReadingElaborations();
            actual_load__17_0_2_edc0357_1352328156887_380316_19705.getValue().setValue(t, x);
        }

        public Parameter< TimeVaryingMap<Integer> > actual_load__17_0_2_edc0357_1352328156887_380316_19705 = null;

        public void initSignalreceiveLoadReadingMembers() {
            try {
                if (actual_load__17_0_2_edc0357_1352328156887_380316_19705 == null) actual_load__17_0_2_edc0357_1352328156887_380316_19705 = new Parameter<TimeVaryingMap<Integer>>("actual_load__17_0_2_edc0357_1352328156887_380316_19705", null, (TimeVaryingMap<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingMap.class, java.lang.String.class), new Object[] { "actual_load" })).evaluate(true), this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void initSignalreceiveLoadReadingCollections() {
            parameters.add(actual_load__17_0_2_edc0357_1352328156887_380316_19705);
        }

        public void initSignalreceiveLoadReadingDependencies() {
        }

        public void initSignalreceiveLoadReadingElaborations() {
            initSignalreceiveLoadReadingDependencies();
        }
    }

    public class SignalreceiveGenReading extends ParameterListenerImpl {

        public SignalreceiveGenReading() {
            super();
            initSignalreceiveGenReadingMembers();
            initSignalreceiveGenReadingCollections();
            initSignalreceiveGenReadingElaborations();
        }

        public SignalreceiveGenReading(Timepoint t, Integer x) {
            super();
            initSignalreceiveGenReadingMembers();
            initSignalreceiveGenReadingCollections();
            initSignalreceiveGenReadingElaborations();
            actual_power__17_0_2_edc0357_1352328156887_822556_19706.getValue().setValue(t, x);
        }

        public Parameter< TimeVaryingMap<Integer> > actual_power__17_0_2_edc0357_1352328156887_822556_19706 = null;

        public void initSignalreceiveGenReadingMembers() {
            try {
                if (actual_power__17_0_2_edc0357_1352328156887_822556_19706 == null) actual_power__17_0_2_edc0357_1352328156887_822556_19706 = new Parameter<TimeVaryingMap<Integer>>("actual_power__17_0_2_edc0357_1352328156887_822556_19706", null, (TimeVaryingMap<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingMap.class, java.lang.String.class), new Object[] { "actual_power" })).evaluate(true), this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void initSignalreceiveGenReadingCollections() {
            parameters.add(actual_power__17_0_2_edc0357_1352328156887_822556_19706);
        }

        public void initSignalreceiveGenReadingDependencies() {
        }

        public void initSignalreceiveGenReadingElaborations() {
            initSignalreceiveGenReadingDependencies();
        }
    }

    public Parameter< ObjectFlow<Power_System.SignalreceiveLoadReading> > ss_17_0_2_edc0357_1352328156634_484825_19608_receiveLoadReading = null;

    public Parameter< ObjectFlow<Power_System.Signalno> > ss_17_0_2_edc0357_1352328156651_739611_19623_no = null;

    public Parameter< ObjectFlow<Power_System.Signalyes> > ss_17_0_2_edc0357_1352328156651_739611_19623_yes = null;

    public StringParameter classifierBehavior = null;

    public Parameter< ObjectFlow<Power_System.SignalreceiveMeterReading> > ss_17_0_2_edc0357_1352328156658_447229_19624_receiveMeterReading = null;

    public Parameter< ObjectFlow<Power_System.SignalchangeLoadValue> > ss_17_0_2_edc0357_1352328156650_180051_19621_changeLoadValue = null;

    public Parameter< Customer > c = null;

    public Parameter< LADWP > l = null;

    public Parameter< Power > p = null;

    public Parameter< ObjectFlow<Power_System.SignalchangeGenerationValue> > ss_17_0_2_edc0357_1352328156880_813680_19691_changeGenerationValue = null;

    public Parameter< ObjectFlow<Power_System.Signaldr_request> > ss_17_0_2_edc0357_1352328156878_93426_19688_dr_request = null;

    public Parameter< ObjectFlow<Power_System.SignalreceiveGenReading> > ss_17_0_2_edc0357_1352328156634_627908_19609_receiveGenReading = null;

    public void initPower_SystemMembers() {
        try {
            if (ss_17_0_2_edc0357_1352328156634_484825_19608_receiveLoadReading == null) ss_17_0_2_edc0357_1352328156634_484825_19608_receiveLoadReading = new Parameter<ObjectFlow<Power_System.SignalreceiveLoadReading>>("ss_17_0_2_edc0357_1352328156634_484825_19608_receiveLoadReading", null, (ObjectFlow<Power_System.SignalreceiveLoadReading>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class, java.lang.Class.class), new Object[] { "ss_17_0_2_edc0357_1352328156634_484825_19608_receiveLoadReading", Power_System.SignalreceiveLoadReading.class })).evaluate(true), this);
            if (ss_17_0_2_edc0357_1352328156651_739611_19623_no == null) ss_17_0_2_edc0357_1352328156651_739611_19623_no = new Parameter<ObjectFlow<Power_System.Signalno>>("ss_17_0_2_edc0357_1352328156651_739611_19623_no", null, (ObjectFlow<Power_System.Signalno>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class, java.lang.Class.class), new Object[] { "ss_17_0_2_edc0357_1352328156651_739611_19623_no", Power_System.Signalno.class })).evaluate(true), this);
            if (ss_17_0_2_edc0357_1352328156651_739611_19623_yes == null) ss_17_0_2_edc0357_1352328156651_739611_19623_yes = new Parameter<ObjectFlow<Power_System.Signalyes>>("ss_17_0_2_edc0357_1352328156651_739611_19623_yes", null, (ObjectFlow<Power_System.Signalyes>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class, java.lang.Class.class), new Object[] { "ss_17_0_2_edc0357_1352328156651_739611_19623_yes", Power_System.Signalyes.class })).evaluate(true), this);
            if (classifierBehavior == null) classifierBehavior = new StringParameter("classifierBehavior", (String) "_17_0_2_edc0357_1352328156860_564082_19664", this);
            if (ss_17_0_2_edc0357_1352328156658_447229_19624_receiveMeterReading == null) ss_17_0_2_edc0357_1352328156658_447229_19624_receiveMeterReading = new Parameter<ObjectFlow<Power_System.SignalreceiveMeterReading>>("ss_17_0_2_edc0357_1352328156658_447229_19624_receiveMeterReading", null, (ObjectFlow<Power_System.SignalreceiveMeterReading>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class, java.lang.Class.class), new Object[] { "ss_17_0_2_edc0357_1352328156658_447229_19624_receiveMeterReading", Power_System.SignalreceiveMeterReading.class })).evaluate(true), this);
            if (ss_17_0_2_edc0357_1352328156650_180051_19621_changeLoadValue == null) ss_17_0_2_edc0357_1352328156650_180051_19621_changeLoadValue = new Parameter<ObjectFlow<Power_System.SignalchangeLoadValue>>("ss_17_0_2_edc0357_1352328156650_180051_19621_changeLoadValue", null, (ObjectFlow<Power_System.SignalchangeLoadValue>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class, java.lang.Class.class), new Object[] { "ss_17_0_2_edc0357_1352328156650_180051_19621_changeLoadValue", Power_System.SignalchangeLoadValue.class })).evaluate(true), this);
            if (c == null) c = new Parameter<Customer>("c", null, (Customer) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(Customer.class, Power_System.class), new Object[] { this })).evaluate(true), this);
            if (l == null) l = new Parameter<LADWP>("l", null, (LADWP) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(LADWP.class, Power_System.class), new Object[] { this })).evaluate(true), this);
            if (p == null) p = new Parameter<Power>("p", null, (Power) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(Power.class, Power_System.class), new Object[] { this })).evaluate(true), this);
            if (ss_17_0_2_edc0357_1352328156880_813680_19691_changeGenerationValue == null) ss_17_0_2_edc0357_1352328156880_813680_19691_changeGenerationValue = new Parameter<ObjectFlow<Power_System.SignalchangeGenerationValue>>("ss_17_0_2_edc0357_1352328156880_813680_19691_changeGenerationValue", null, (ObjectFlow<Power_System.SignalchangeGenerationValue>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class, java.lang.Class.class), new Object[] { "ss_17_0_2_edc0357_1352328156880_813680_19691_changeGenerationValue", Power_System.SignalchangeGenerationValue.class })).evaluate(true), this);
            if (ss_17_0_2_edc0357_1352328156878_93426_19688_dr_request == null) ss_17_0_2_edc0357_1352328156878_93426_19688_dr_request = new Parameter<ObjectFlow<Power_System.Signaldr_request>>("ss_17_0_2_edc0357_1352328156878_93426_19688_dr_request", null, (ObjectFlow<Power_System.Signaldr_request>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class, java.lang.Class.class), new Object[] { "ss_17_0_2_edc0357_1352328156878_93426_19688_dr_request", Power_System.Signaldr_request.class })).evaluate(true), this);
            if (ss_17_0_2_edc0357_1352328156634_627908_19609_receiveGenReading == null) ss_17_0_2_edc0357_1352328156634_627908_19609_receiveGenReading = new Parameter<ObjectFlow<Power_System.SignalreceiveGenReading>>("ss_17_0_2_edc0357_1352328156634_627908_19609_receiveGenReading", null, (ObjectFlow<Power_System.SignalreceiveGenReading>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class, java.lang.Class.class), new Object[] { "ss_17_0_2_edc0357_1352328156634_627908_19609_receiveGenReading", Power_System.SignalreceiveGenReading.class })).evaluate(true), this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void initPower_SystemCollections() {
        parameters.add(ss_17_0_2_edc0357_1352328156634_484825_19608_receiveLoadReading);
        parameters.add(ss_17_0_2_edc0357_1352328156651_739611_19623_no);
        parameters.add(ss_17_0_2_edc0357_1352328156651_739611_19623_yes);
        parameters.add(classifierBehavior);
        parameters.add(ss_17_0_2_edc0357_1352328156658_447229_19624_receiveMeterReading);
        parameters.add(ss_17_0_2_edc0357_1352328156650_180051_19621_changeLoadValue);
        parameters.add(c);
        parameters.add(l);
        parameters.add(p);
        parameters.add(ss_17_0_2_edc0357_1352328156880_813680_19691_changeGenerationValue);
        parameters.add(ss_17_0_2_edc0357_1352328156878_93426_19688_dr_request);
        parameters.add(ss_17_0_2_edc0357_1352328156634_627908_19609_receiveGenReading);
    }

    public void initPower_SystemDependencies() {
    }

    public void initPower_SystemElaborations() {
        initPower_SystemDependencies();
    }
}
