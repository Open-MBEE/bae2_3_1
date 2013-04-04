package ROUND_TRIP_TEST;

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

    public class _17_0_2_1_edc0357_1360798765060_634347_20307 extends DurativeEvent {

        public _17_0_2_1_edc0357_1360798765060_634347_20307() {
            super();
            init_17_0_2_1_edc0357_1360798765060_634347_20307Members();
            init_17_0_2_1_edc0357_1360798765060_634347_20307Collections();
            init_17_0_2_1_edc0357_1360798765060_634347_20307Elaborations();
        }

        public class _17_0_2_1_edc0357_1360798765799_670470_21061 extends DurativeEvent {

            public _17_0_2_1_edc0357_1360798765799_670470_21061() {
                super();
                init_17_0_2_1_edc0357_1360798765799_670470_21061Members();
                init_17_0_2_1_edc0357_1360798765799_670470_21061Collections();
                init_17_0_2_1_edc0357_1360798765799_670470_21061Elaborations();
            }

            public _17_0_2_1_edc0357_1360798765799_670470_21061(Expression<Integer> startTime) {
                super();
                init_17_0_2_1_edc0357_1360798765799_670470_21061Members();
                init_17_0_2_1_edc0357_1360798765799_670470_21061Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_1_edc0357_1360798765799_670470_21061Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_1_edc0357_1360958856427_568429_21872_exists = null;

            public BooleanParameter objectToPass = null;

            public Dependency< Boolean > _17_0_2_1_edc0357_1360958856427_568429_21872_existsDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Effect effect2 = null;

            public Parameter effect2Var = null;

            public ElaborationRule elaborationRule3 = null;

            public void init_17_0_2_1_edc0357_1360798765799_670470_21061Members() {
                try {
                    if (_17_0_2_1_edc0357_1360958856427_568429_21872_exists == null) _17_0_2_1_edc0357_1360958856427_568429_21872_exists = new BooleanParameter("_17_0_2_1_edc0357_1360958856427_568429_21872_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect2VarV = sig_17_0_2_1_edc0357_1360958857864_402573_21876;
                    effect2Var = new Parameter("effect2Var", null, null, this);
                    addDependency(effect2Var, new Expression(effect2VarV));
                    effect2 = new EffectFunction(new EffectFunction(effect2Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "ROUND_TRIP_TEST", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Boolean>(objectToPass), new Expression<Integer>(endTime) }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_1_edc0357_1360798765799_670470_21061Collections() {
                parameters.add(_17_0_2_1_edc0357_1360958856427_568429_21872_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect2Var = new TreeSet<Effect>();
                effectsForeffect2Var.add(effect2);
                addEffects((Parameter<?>) effect2Var, effectsForeffect2Var);
            }

            public void init_17_0_2_1_edc0357_1360798765799_670470_21061Dependencies() {
                addDependency(_17_0_2_1_edc0357_1360958856427_568429_21872_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765806_270620_21071_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765806_270620_21071_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(true));
            }

            public void init_17_0_2_1_edc0357_1360798765799_670470_21061Elaborations() {
                init_17_0_2_1_edc0357_1360798765799_670470_21061Dependencies();
                Expression<?>[] arguments3 = new Expression<?>[1];
                arguments3[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition3 = new Expression<Boolean>(_17_0_2_1_edc0357_1360958856427_568429_21872_exists);
                elaborationRule3 = addElaborationRule(condition3, _17_0_2_1_edc0357_1360798765060_634347_20307.this, Power_System._17_0_2_1_edc0357_1360798765060_634347_20307._17_0_2_1_edc0357_1360958856427_568429_21872.class, "f_start__ForkNode__start_system__Power_System", arguments3);
            }
        }

        public class _17_0_2_1_edc0357_1360798765801_883801_21064 extends DurativeEvent {

            public _17_0_2_1_edc0357_1360798765801_883801_21064() {
                super();
                init_17_0_2_1_edc0357_1360798765801_883801_21064Members();
                init_17_0_2_1_edc0357_1360798765801_883801_21064Collections();
                init_17_0_2_1_edc0357_1360798765801_883801_21064Elaborations();
            }

            public _17_0_2_1_edc0357_1360798765801_883801_21064(Expression<Integer> startTime) {
                super();
                init_17_0_2_1_edc0357_1360798765801_883801_21064Members();
                init_17_0_2_1_edc0357_1360798765801_883801_21064Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_1_edc0357_1360798765801_883801_21064Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_1_edc0357_1360798765803_386941_21066_exists = null;

            public Parameter< Customer > _17_0_2_1_edc0357_1360798766470_27157_22167 = null;

            public BooleanParameter objectToPass = null;

            public Dependency< Boolean > _17_0_2_1_edc0357_1360798765803_386941_21066_existsDependency = null;

            public Dependency< Customer > _17_0_2_1_edc0357_1360798766470_27157_22167Dependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Effect effect4 = null;

            public Parameter effect4Var = null;

            public ElaborationRule elaborationRule5 = null;

            public void init_17_0_2_1_edc0357_1360798765801_883801_21064Members() {
                try {
                    if (_17_0_2_1_edc0357_1360798765803_386941_21066_exists == null) _17_0_2_1_edc0357_1360798765803_386941_21066_exists = new BooleanParameter("_17_0_2_1_edc0357_1360798765803_386941_21066_exists", (Boolean) false, this);
                    if (_17_0_2_1_edc0357_1360798766470_27157_22167 == null) _17_0_2_1_edc0357_1360798766470_27157_22167 = new Parameter<Customer>("_17_0_2_1_edc0357_1360798766470_27157_22167", null, (Customer) null, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect4VarV = sig_17_0_2_1_edc0357_1360798765807_756954_21078;
                    effect4Var = new Parameter("effect4Var", null, null, this);
                    addDependency(effect4Var, new Expression(effect4VarV));
                    effect4 = new EffectFunction(new EffectFunction(effect4Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "ROUND_TRIP_TEST", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Customer>(_17_0_2_1_edc0357_1360798766470_27157_22167), new Expression<Integer>(endTime) }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_1_edc0357_1360798765801_883801_21064Collections() {
                parameters.add(_17_0_2_1_edc0357_1360798765803_386941_21066_exists);
                parameters.add(_17_0_2_1_edc0357_1360798766470_27157_22167);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect4Var = new TreeSet<Effect>();
                effectsForeffect4Var.add(effect4);
                addEffects((Parameter<?>) effect4Var, effectsForeffect4Var);
            }

            public void init_17_0_2_1_edc0357_1360798765801_883801_21064Dependencies() {
            	//changed ordering by hand
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new EffectFunction(sig_17_0_2_1_edc0357_1360958739383_541838_21858, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "ROUND_TRIP_TEST", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_1_edc0357_1360798765803_386941_21066_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765806_270620_21071_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765806_270620_21071_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(_17_0_2_1_edc0357_1360798766470_27157_22167, new Expression<Customer>(c));
            }

            public void init_17_0_2_1_edc0357_1360798765801_883801_21064Elaborations() {
                init_17_0_2_1_edc0357_1360798765801_883801_21064Dependencies();
                Expression<?>[] arguments5 = new Expression<?>[1];
                arguments5[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition5 = new Expression<Boolean>(_17_0_2_1_edc0357_1360798765803_386941_21066_exists);
                elaborationRule5 = addElaborationRule(condition5, _17_0_2_1_edc0357_1360798765060_634347_20307.this, Power_System._17_0_2_1_edc0357_1360798765060_634347_20307._17_0_2_1_edc0357_1360798765803_386941_21066.class, "sobc__StartObjectBehaviorAction__start_system__Power_System", arguments5);
            }
        }

        public class _17_0_2_1_edc0357_1360798765802_132157_21065 extends DurativeEvent {

            public _17_0_2_1_edc0357_1360798765802_132157_21065() {
                super();
                init_17_0_2_1_edc0357_1360798765802_132157_21065Members();
                init_17_0_2_1_edc0357_1360798765802_132157_21065Collections();
                init_17_0_2_1_edc0357_1360798765802_132157_21065Elaborations();
            }

            public _17_0_2_1_edc0357_1360798765802_132157_21065(Expression<Integer> startTime) {
                super();
                init_17_0_2_1_edc0357_1360798765802_132157_21065Members();
                init_17_0_2_1_edc0357_1360798765802_132157_21065Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_1_edc0357_1360798765802_132157_21065Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_1_edc0357_1360798765803_815585_21067_exists = null;

            public Parameter< Power > _17_0_2_1_edc0357_1360798766471_289951_22169 = null;

            public BooleanParameter objectToPass = null;

            public Dependency< Boolean > _17_0_2_1_edc0357_1360798765803_815585_21067_existsDependency = null;

            public Dependency< Power > _17_0_2_1_edc0357_1360798766471_289951_22169Dependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Effect effect6 = null;

            public Parameter effect6Var = null;

            public ElaborationRule elaborationRule7 = null;

            public void init_17_0_2_1_edc0357_1360798765802_132157_21065Members() {
                try {
                    if (_17_0_2_1_edc0357_1360798765803_815585_21067_exists == null) _17_0_2_1_edc0357_1360798765803_815585_21067_exists = new BooleanParameter("_17_0_2_1_edc0357_1360798765803_815585_21067_exists", (Boolean) false, this);
                    if (_17_0_2_1_edc0357_1360798766471_289951_22169 == null) _17_0_2_1_edc0357_1360798766471_289951_22169 = new Parameter<Power>("_17_0_2_1_edc0357_1360798766471_289951_22169", null, (Power) null, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect6VarV = sig_17_0_2_1_edc0357_1360798765808_512362_21079;
                    effect6Var = new Parameter("effect6Var", null, null, this);
                    addDependency(effect6Var, new Expression(effect6VarV));
                    effect6 = new EffectFunction(new EffectFunction(effect6Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Power>", "ROUND_TRIP_TEST", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Power>(_17_0_2_1_edc0357_1360798766471_289951_22169), new Expression<Integer>(endTime) }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_1_edc0357_1360798765802_132157_21065Collections() {
                parameters.add(_17_0_2_1_edc0357_1360798765803_815585_21067_exists);
                parameters.add(_17_0_2_1_edc0357_1360798766471_289951_22169);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect6Var = new TreeSet<Effect>();
                effectsForeffect6Var.add(effect6);
                addEffects((Parameter<?>) effect6Var, effectsForeffect6Var);
            }

            public void init_17_0_2_1_edc0357_1360798765802_132157_21065Dependencies() {
                addDependency(_17_0_2_1_edc0357_1360798765803_815585_21067_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765806_270620_21071_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765806_270620_21071_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(_17_0_2_1_edc0357_1360798766471_289951_22169, new Expression<Power>(p));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new EffectFunction(sig_17_0_2_1_edc0357_1360958861130_631052_21881, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "ROUND_TRIP_TEST", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_1_edc0357_1360798765802_132157_21065Elaborations() {
                init_17_0_2_1_edc0357_1360798765802_132157_21065Dependencies();
                Expression<?>[] arguments7 = new Expression<?>[1];
                arguments7[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition7 = new Expression<Boolean>(_17_0_2_1_edc0357_1360798765803_815585_21067_exists);
                elaborationRule7 = addElaborationRule(condition7, _17_0_2_1_edc0357_1360798765060_634347_20307.this, Power_System._17_0_2_1_edc0357_1360798765060_634347_20307._17_0_2_1_edc0357_1360798765803_815585_21067.class, "sobp__StartObjectBehaviorAction__start_system__Power_System", arguments7);
            }
        }

        public class _17_0_2_1_edc0357_1360798765803_386941_21066 extends DurativeEvent {

            public _17_0_2_1_edc0357_1360798765803_386941_21066() {
                super();
                init_17_0_2_1_edc0357_1360798765803_386941_21066Members();
                init_17_0_2_1_edc0357_1360798765803_386941_21066Collections();
                init_17_0_2_1_edc0357_1360798765803_386941_21066Elaborations();
            }

            public _17_0_2_1_edc0357_1360798765803_386941_21066(Expression<Integer> startTime) {
                super();
                init_17_0_2_1_edc0357_1360798765803_386941_21066Members();
                init_17_0_2_1_edc0357_1360798765803_386941_21066Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_1_edc0357_1360798765803_386941_21066Elaborations();
                fixTimeDependencies();
            }

            public Parameter< Customer > _17_0_2_1_edc0357_1360798766472_955915_22171 = null;

            public BooleanParameter _17_0_2_1_edc0357_1360958878627_104372_21890_exists = null;

            public BooleanParameter addToDecider__17_0_2_1_edc0357_1360958878627_104372_21890 = null;

            public IntegerParameter myDeciderID_decider_17_0_2_1_edc0357_1360958878627_104372_21890 = null;

            public BooleanParameter objectToPass = null;

            public Dependency< Customer > _17_0_2_1_edc0357_1360798766472_955915_22171Dependency = null;

            public Dependency< Boolean > _17_0_2_1_edc0357_1360958878627_104372_21890_existsDependency = null;

            public Dependency< Boolean > addToDecider__17_0_2_1_edc0357_1360958878627_104372_21890Dependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Integer > myDeciderID_decider_17_0_2_1_edc0357_1360958878627_104372_21890Dependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Effect effect8 = null;

            public Parameter effect8Var = null;

            public Effect effect9 = null;

            public Parameter effect9Var = null;

            public ElaborationRule elaborationRule10 = null;

            public ElaborationRule elaborationRule11 = null;

            public void init_17_0_2_1_edc0357_1360798765803_386941_21066Members() {
                try {
                    if (_17_0_2_1_edc0357_1360798766472_955915_22171 == null) _17_0_2_1_edc0357_1360798766472_955915_22171 = new Parameter<Customer>("_17_0_2_1_edc0357_1360798766472_955915_22171", null, (Customer) null, this);
                    if (_17_0_2_1_edc0357_1360958878627_104372_21890_exists == null) _17_0_2_1_edc0357_1360958878627_104372_21890_exists = new BooleanParameter("_17_0_2_1_edc0357_1360958878627_104372_21890_exists", (Boolean) false, this);
                    if (addToDecider__17_0_2_1_edc0357_1360958878627_104372_21890 == null) addToDecider__17_0_2_1_edc0357_1360958878627_104372_21890 = new BooleanParameter("addToDecider__17_0_2_1_edc0357_1360958878627_104372_21890", (Boolean) false, this);
                    if (myDeciderID_decider_17_0_2_1_edc0357_1360958878627_104372_21890 == null) myDeciderID_decider_17_0_2_1_edc0357_1360958878627_104372_21890 = new IntegerParameter("myDeciderID_decider_17_0_2_1_edc0357_1360958878627_104372_21890", (Integer) 3, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect8VarV = decider_17_0_2_1_edc0357_1360958878627_104372_21890;
                    effect8Var = new Parameter("effect8Var", null, null, this);
                    addDependency(effect8Var, new Expression(effect8VarV));
                    effect8 = new EffectFunction(new EffectFunction(effect8Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "ROUND_TRIP_TEST", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class, java.lang.Boolean.class), new Object[] { new Expression<Integer>(endTime), new Expression<Integer>(myDeciderID_decider_17_0_2_1_edc0357_1360958878627_104372_21890), new Expression<Boolean>(addToDecider__17_0_2_1_edc0357_1360958878627_104372_21890) }));
                    Object effect9VarV = sig_17_0_2_1_edc0357_1360958897718_677891_21905;
                    effect9Var = new Parameter("effect9Var", null, null, this);
                    addDependency(effect9Var, new Expression(effect9VarV));
                    effect9 = new EffectFunction(new EffectFunction(effect9Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "ROUND_TRIP_TEST", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Boolean>(objectToPass), new Expression<Integer>(endTime) }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_1_edc0357_1360798765803_386941_21066Collections() {
                parameters.add(_17_0_2_1_edc0357_1360798766472_955915_22171);
                parameters.add(_17_0_2_1_edc0357_1360958878627_104372_21890_exists);
                parameters.add(addToDecider__17_0_2_1_edc0357_1360958878627_104372_21890);
                parameters.add(myDeciderID_decider_17_0_2_1_edc0357_1360958878627_104372_21890);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect8Var = new TreeSet<Effect>();
                effectsForeffect8Var.add(effect8);
                addEffects((Parameter<?>) effect8Var, effectsForeffect8Var);
                Set<Effect> effectsForeffect9Var = new TreeSet<Effect>();
                effectsForeffect9Var.add(effect9);
                addEffects((Parameter<?>) effect9Var, effectsForeffect9Var);
            }

            public void init_17_0_2_1_edc0357_1360798765803_386941_21066Dependencies() {
                addDependency(_17_0_2_1_edc0357_1360798766472_955915_22171, new Expression<Customer>(new EffectFunction(sig_17_0_2_1_edc0357_1360798765807_756954_21078, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "ROUND_TRIP_TEST", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_1_edc0357_1360958878627_104372_21890_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765806_270620_21071_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765806_270620_21071_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_1_edc0357_1360958878627_104372_21890, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "ROUND_TRIP_TEST", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_1_edc0357_1360958878627_104372_21890, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "ROUND_TRIP_TEST", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_1_edc0357_1360958878627_104372_21890, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "ROUND_TRIP_TEST", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_1_edc0357_1360958878627_104372_21890)))))))));
                addDependency(addToDecider__17_0_2_1_edc0357_1360958878627_104372_21890, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765806_270620_21071_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765806_270620_21071_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(myDeciderID_decider_17_0_2_1_edc0357_1360958878627_104372_21890, new Expression<Integer>(3));
                addDependency(objectToPass, new Expression<Boolean>(true));
            }

            public void init_17_0_2_1_edc0357_1360798765803_386941_21066Elaborations() {
                init_17_0_2_1_edc0357_1360798765803_386941_21066Dependencies();
                Expression<?>[] arguments10 = new Expression<?>[1];
                arguments10[0] = new Expression<Integer>(startTime);
                Expression<Boolean> condition10 = new Expression<Boolean>(true);
                elaborationRule10 = addElaborationRule(condition10, c, Customer._17_0_2_1_edc0357_1360798765037_222001_20270.class, "CustomerCB__Activity__Customer__Customer", arguments10);
                Expression<?>[] arguments11 = new Expression<?>[1];
                arguments11[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition11 = new Expression<Boolean>(_17_0_2_1_edc0357_1360958878627_104372_21890_exists);
                elaborationRule11 = addElaborationRule(condition11, _17_0_2_1_edc0357_1360798765060_634347_20307.this, Power_System._17_0_2_1_edc0357_1360798765060_634347_20307._17_0_2_1_edc0357_1360958878627_104372_21890.class, "f_end__JoinNode__start_system__Power_System", arguments11);
            }
        }

        public class _17_0_2_1_edc0357_1360798765803_815585_21067 extends DurativeEvent {

            public _17_0_2_1_edc0357_1360798765803_815585_21067() {
                super();
                init_17_0_2_1_edc0357_1360798765803_815585_21067Members();
                init_17_0_2_1_edc0357_1360798765803_815585_21067Collections();
                init_17_0_2_1_edc0357_1360798765803_815585_21067Elaborations();
            }

            public _17_0_2_1_edc0357_1360798765803_815585_21067(Expression<Integer> startTime) {
                super();
                init_17_0_2_1_edc0357_1360798765803_815585_21067Members();
                init_17_0_2_1_edc0357_1360798765803_815585_21067Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_1_edc0357_1360798765803_815585_21067Elaborations();
                fixTimeDependencies();
            }

            public Parameter< Power > _17_0_2_1_edc0357_1360798766473_964434_22172 = null;

            public BooleanParameter _17_0_2_1_edc0357_1360958878627_104372_21890_exists = null;

            public BooleanParameter addToDecider__17_0_2_1_edc0357_1360958878627_104372_21890 = null;

            public IntegerParameter myDeciderID_decider_17_0_2_1_edc0357_1360958878627_104372_21890 = null;

            public BooleanParameter objectToPass = null;

            public Dependency< Power > _17_0_2_1_edc0357_1360798766473_964434_22172Dependency = null;

            public Dependency< Boolean > _17_0_2_1_edc0357_1360958878627_104372_21890_existsDependency = null;

            public Dependency< Boolean > addToDecider__17_0_2_1_edc0357_1360958878627_104372_21890Dependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Integer > myDeciderID_decider_17_0_2_1_edc0357_1360958878627_104372_21890Dependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Effect effect12 = null;

            public Parameter effect12Var = null;

            public Effect effect13 = null;

            public Parameter effect13Var = null;

            public ElaborationRule elaborationRule14 = null;

            public ElaborationRule elaborationRule15 = null;

            public void init_17_0_2_1_edc0357_1360798765803_815585_21067Members() {
                try {
                    if (_17_0_2_1_edc0357_1360798766473_964434_22172 == null) _17_0_2_1_edc0357_1360798766473_964434_22172 = new Parameter<Power>("_17_0_2_1_edc0357_1360798766473_964434_22172", null, (Power) null, this);
                    if (_17_0_2_1_edc0357_1360958878627_104372_21890_exists == null) _17_0_2_1_edc0357_1360958878627_104372_21890_exists = new BooleanParameter("_17_0_2_1_edc0357_1360958878627_104372_21890_exists", (Boolean) false, this);
                    if (addToDecider__17_0_2_1_edc0357_1360958878627_104372_21890 == null) addToDecider__17_0_2_1_edc0357_1360958878627_104372_21890 = new BooleanParameter("addToDecider__17_0_2_1_edc0357_1360958878627_104372_21890", (Boolean) false, this);
                    if (myDeciderID_decider_17_0_2_1_edc0357_1360958878627_104372_21890 == null) myDeciderID_decider_17_0_2_1_edc0357_1360958878627_104372_21890 = new IntegerParameter("myDeciderID_decider_17_0_2_1_edc0357_1360958878627_104372_21890", (Integer) 2, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect12VarV = decider_17_0_2_1_edc0357_1360958878627_104372_21890;
                    effect12Var = new Parameter("effect12Var", null, null, this);
                    addDependency(effect12Var, new Expression(effect12VarV));
                    effect12 = new EffectFunction(new EffectFunction(effect12Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "ROUND_TRIP_TEST", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class, java.lang.Boolean.class), new Object[] { new Expression<Integer>(endTime), new Expression<Integer>(myDeciderID_decider_17_0_2_1_edc0357_1360958878627_104372_21890), new Expression<Boolean>(addToDecider__17_0_2_1_edc0357_1360958878627_104372_21890) }));
                    Object effect13VarV = sig_17_0_2_1_edc0357_1360958894126_393790_21900;
                    effect13Var = new Parameter("effect13Var", null, null, this);
                    addDependency(effect13Var, new Expression(effect13VarV));
                    effect13 = new EffectFunction(new EffectFunction(effect13Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "ROUND_TRIP_TEST", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Boolean>(objectToPass), new Expression<Integer>(endTime) }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_1_edc0357_1360798765803_815585_21067Collections() {
                parameters.add(_17_0_2_1_edc0357_1360798766473_964434_22172);
                parameters.add(_17_0_2_1_edc0357_1360958878627_104372_21890_exists);
                parameters.add(addToDecider__17_0_2_1_edc0357_1360958878627_104372_21890);
                parameters.add(myDeciderID_decider_17_0_2_1_edc0357_1360958878627_104372_21890);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect12Var = new TreeSet<Effect>();
                effectsForeffect12Var.add(effect12);
                addEffects((Parameter<?>) effect12Var, effectsForeffect12Var);
                Set<Effect> effectsForeffect13Var = new TreeSet<Effect>();
                effectsForeffect13Var.add(effect13);
                addEffects((Parameter<?>) effect13Var, effectsForeffect13Var);
            }

            public void init_17_0_2_1_edc0357_1360798765803_815585_21067Dependencies() {
                addDependency(_17_0_2_1_edc0357_1360798766473_964434_22172, new Expression<Power>(new EffectFunction(sig_17_0_2_1_edc0357_1360798765808_512362_21079, ClassUtils.getMethodForArgTypes("ObjectFlow<Power>", "ROUND_TRIP_TEST", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_1_edc0357_1360958878627_104372_21890_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765806_270620_21071_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765806_270620_21071_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_1_edc0357_1360958878627_104372_21890, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "ROUND_TRIP_TEST", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_1_edc0357_1360958878627_104372_21890, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "ROUND_TRIP_TEST", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_1_edc0357_1360958878627_104372_21890, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "ROUND_TRIP_TEST", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_1_edc0357_1360958878627_104372_21890)))))))));
                addDependency(addToDecider__17_0_2_1_edc0357_1360958878627_104372_21890, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765806_270620_21071_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765806_270620_21071_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(myDeciderID_decider_17_0_2_1_edc0357_1360958878627_104372_21890, new Expression<Integer>(2));
                addDependency(objectToPass, new Expression<Boolean>(true));
            }

            public void init_17_0_2_1_edc0357_1360798765803_815585_21067Elaborations() {
                init_17_0_2_1_edc0357_1360798765803_815585_21067Dependencies();
                Expression<?>[] arguments14 = new Expression<?>[1];
                arguments14[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition14 = new Expression<Boolean>(_17_0_2_1_edc0357_1360958878627_104372_21890_exists);
                elaborationRule14 = addElaborationRule(condition14, _17_0_2_1_edc0357_1360798765060_634347_20307.this, Power_System._17_0_2_1_edc0357_1360798765060_634347_20307._17_0_2_1_edc0357_1360958878627_104372_21890.class, "f_end__JoinNode__start_system__Power_System", arguments14);
                Expression<?>[] arguments15 = new Expression<?>[1];
                arguments15[0] = new Expression<Integer>(startTime);
                Expression<Boolean> condition15 = new Expression<Boolean>(true);
                elaborationRule15 = addElaborationRule(condition15, p, Power._17_0_2_1_edc0357_1360798765022_187244_20251.class, "PowerCB__Activity__Power__Power", arguments15);
            }
        }

        public class _17_0_2_1_edc0357_1360798765804_814855_21068 extends DurativeEvent {

            public _17_0_2_1_edc0357_1360798765804_814855_21068() {
                super();
                init_17_0_2_1_edc0357_1360798765804_814855_21068Members();
                init_17_0_2_1_edc0357_1360798765804_814855_21068Collections();
                init_17_0_2_1_edc0357_1360798765804_814855_21068Elaborations();
            }

            public _17_0_2_1_edc0357_1360798765804_814855_21068(Expression<Integer> startTime) {
                super();
                init_17_0_2_1_edc0357_1360798765804_814855_21068Members();
                init_17_0_2_1_edc0357_1360798765804_814855_21068Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_1_edc0357_1360798765804_814855_21068Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_1_edc0357_1360798765805_217755_21069_exists = null;

            public Parameter< LADWP > _17_0_2_1_edc0357_1360798766473_160708_22173 = null;

            public BooleanParameter objectToPass = null;

            public Dependency< Boolean > _17_0_2_1_edc0357_1360798765805_217755_21069_existsDependency = null;

            public Dependency< LADWP > _17_0_2_1_edc0357_1360798766473_160708_22173Dependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Effect effect16 = null;

            public Parameter effect16Var = null;

            public ElaborationRule elaborationRule17 = null;

            public void init_17_0_2_1_edc0357_1360798765804_814855_21068Members() {
                try {
                    if (_17_0_2_1_edc0357_1360798765805_217755_21069_exists == null) _17_0_2_1_edc0357_1360798765805_217755_21069_exists = new BooleanParameter("_17_0_2_1_edc0357_1360798765805_217755_21069_exists", (Boolean) false, this);
                    if (_17_0_2_1_edc0357_1360798766473_160708_22173 == null) _17_0_2_1_edc0357_1360798766473_160708_22173 = new Parameter<LADWP>("_17_0_2_1_edc0357_1360798766473_160708_22173", null, (LADWP) null, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect16VarV = sig_17_0_2_1_edc0357_1360798765808_997160_21082;
                    effect16Var = new Parameter("effect16Var", null, null, this);
                    addDependency(effect16Var, new Expression(effect16VarV));
                    effect16 = new EffectFunction(new EffectFunction(effect16Var, ClassUtils.getMethodForArgTypes("ObjectFlow<LADWP>", "ROUND_TRIP_TEST", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<LADWP>(_17_0_2_1_edc0357_1360798766473_160708_22173), new Expression<Integer>(endTime) }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_1_edc0357_1360798765804_814855_21068Collections() {
                parameters.add(_17_0_2_1_edc0357_1360798765805_217755_21069_exists);
                parameters.add(_17_0_2_1_edc0357_1360798766473_160708_22173);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect16Var = new TreeSet<Effect>();
                effectsForeffect16Var.add(effect16);
                addEffects((Parameter<?>) effect16Var, effectsForeffect16Var);
            }

            public void init_17_0_2_1_edc0357_1360798765804_814855_21068Dependencies() {
                addDependency(_17_0_2_1_edc0357_1360798765805_217755_21069_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765806_270620_21071_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765806_270620_21071_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(_17_0_2_1_edc0357_1360798766473_160708_22173, new Expression<LADWP>(l));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new EffectFunction(sig_17_0_2_1_edc0357_1360958866507_436590_21886, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "ROUND_TRIP_TEST", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_1_edc0357_1360798765804_814855_21068Elaborations() {
                init_17_0_2_1_edc0357_1360798765804_814855_21068Dependencies();
                Expression<?>[] arguments17 = new Expression<?>[1];
                arguments17[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition17 = new Expression<Boolean>(_17_0_2_1_edc0357_1360798765805_217755_21069_exists);
                elaborationRule17 = addElaborationRule(condition17, _17_0_2_1_edc0357_1360798765060_634347_20307.this, Power_System._17_0_2_1_edc0357_1360798765060_634347_20307._17_0_2_1_edc0357_1360798765805_217755_21069.class, "sobl__StartObjectBehaviorAction__start_system__Power_System", arguments17);
            }
        }

        public class _17_0_2_1_edc0357_1360798765805_217755_21069 extends DurativeEvent {

            public _17_0_2_1_edc0357_1360798765805_217755_21069() {
                super();
                init_17_0_2_1_edc0357_1360798765805_217755_21069Members();
                init_17_0_2_1_edc0357_1360798765805_217755_21069Collections();
                init_17_0_2_1_edc0357_1360798765805_217755_21069Elaborations();
            }

            public _17_0_2_1_edc0357_1360798765805_217755_21069(Expression<Integer> startTime) {
                super();
                init_17_0_2_1_edc0357_1360798765805_217755_21069Members();
                init_17_0_2_1_edc0357_1360798765805_217755_21069Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_1_edc0357_1360798765805_217755_21069Elaborations();
                fixTimeDependencies();
            }

            public Parameter< LADWP > _17_0_2_1_edc0357_1360798766475_673138_22175 = null;

            public BooleanParameter _17_0_2_1_edc0357_1360958878627_104372_21890_exists = null;

            public BooleanParameter addToDecider__17_0_2_1_edc0357_1360958878627_104372_21890 = null;

            public IntegerParameter myDeciderID_decider_17_0_2_1_edc0357_1360958878627_104372_21890 = null;

            public BooleanParameter objectToPass = null;

            public Dependency< LADWP > _17_0_2_1_edc0357_1360798766475_673138_22175Dependency = null;

            public Dependency< Boolean > _17_0_2_1_edc0357_1360958878627_104372_21890_existsDependency = null;

            public Dependency< Boolean > addToDecider__17_0_2_1_edc0357_1360958878627_104372_21890Dependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Integer > myDeciderID_decider_17_0_2_1_edc0357_1360958878627_104372_21890Dependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Effect effect18 = null;

            public Parameter effect18Var = null;

            public Effect effect19 = null;

            public Parameter effect19Var = null;

            public ElaborationRule elaborationRule20 = null;

            public ElaborationRule elaborationRule21 = null;

            public void init_17_0_2_1_edc0357_1360798765805_217755_21069Members() {
                try {
                    if (_17_0_2_1_edc0357_1360798766475_673138_22175 == null) _17_0_2_1_edc0357_1360798766475_673138_22175 = new Parameter<LADWP>("_17_0_2_1_edc0357_1360798766475_673138_22175", null, (LADWP) null, this);
                    if (_17_0_2_1_edc0357_1360958878627_104372_21890_exists == null) _17_0_2_1_edc0357_1360958878627_104372_21890_exists = new BooleanParameter("_17_0_2_1_edc0357_1360958878627_104372_21890_exists", (Boolean) false, this);
                    if (addToDecider__17_0_2_1_edc0357_1360958878627_104372_21890 == null) addToDecider__17_0_2_1_edc0357_1360958878627_104372_21890 = new BooleanParameter("addToDecider__17_0_2_1_edc0357_1360958878627_104372_21890", (Boolean) false, this);
                    if (myDeciderID_decider_17_0_2_1_edc0357_1360958878627_104372_21890 == null) myDeciderID_decider_17_0_2_1_edc0357_1360958878627_104372_21890 = new IntegerParameter("myDeciderID_decider_17_0_2_1_edc0357_1360958878627_104372_21890", (Integer) 1, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect18VarV = decider_17_0_2_1_edc0357_1360958878627_104372_21890;
                    effect18Var = new Parameter("effect18Var", null, null, this);
                    addDependency(effect18Var, new Expression(effect18VarV));
                    effect18 = new EffectFunction(new EffectFunction(effect18Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "ROUND_TRIP_TEST", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class, java.lang.Boolean.class), new Object[] { new Expression<Integer>(endTime), new Expression<Integer>(myDeciderID_decider_17_0_2_1_edc0357_1360958878627_104372_21890), new Expression<Boolean>(addToDecider__17_0_2_1_edc0357_1360958878627_104372_21890) }));
                    Object effect19VarV = sig_17_0_2_1_edc0357_1360958889738_677596_21895;
                    effect19Var = new Parameter("effect19Var", null, null, this);
                    addDependency(effect19Var, new Expression(effect19VarV));
                    effect19 = new EffectFunction(new EffectFunction(effect19Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "ROUND_TRIP_TEST", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Boolean>(objectToPass), new Expression<Integer>(endTime) }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_1_edc0357_1360798765805_217755_21069Collections() {
                parameters.add(_17_0_2_1_edc0357_1360798766475_673138_22175);
                parameters.add(_17_0_2_1_edc0357_1360958878627_104372_21890_exists);
                parameters.add(addToDecider__17_0_2_1_edc0357_1360958878627_104372_21890);
                parameters.add(myDeciderID_decider_17_0_2_1_edc0357_1360958878627_104372_21890);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect18Var = new TreeSet<Effect>();
                effectsForeffect18Var.add(effect18);
                addEffects((Parameter<?>) effect18Var, effectsForeffect18Var);
                Set<Effect> effectsForeffect19Var = new TreeSet<Effect>();
                effectsForeffect19Var.add(effect19);
                addEffects((Parameter<?>) effect19Var, effectsForeffect19Var);
            }

            public void init_17_0_2_1_edc0357_1360798765805_217755_21069Dependencies() {
                addDependency(_17_0_2_1_edc0357_1360798766475_673138_22175, new Expression<LADWP>(new EffectFunction(sig_17_0_2_1_edc0357_1360798765808_997160_21082, ClassUtils.getMethodForArgTypes("ObjectFlow<LADWP>", "ROUND_TRIP_TEST", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_1_edc0357_1360958878627_104372_21890_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765806_270620_21071_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765806_270620_21071_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_1_edc0357_1360958878627_104372_21890, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "ROUND_TRIP_TEST", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_1_edc0357_1360958878627_104372_21890, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "ROUND_TRIP_TEST", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_1_edc0357_1360958878627_104372_21890, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "ROUND_TRIP_TEST", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_1_edc0357_1360958878627_104372_21890)))))))));
                addDependency(addToDecider__17_0_2_1_edc0357_1360958878627_104372_21890, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765806_270620_21071_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765806_270620_21071_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(myDeciderID_decider_17_0_2_1_edc0357_1360958878627_104372_21890, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(true));
            }

            public void init_17_0_2_1_edc0357_1360798765805_217755_21069Elaborations() {
                init_17_0_2_1_edc0357_1360798765805_217755_21069Dependencies();
                Expression<?>[] arguments20 = new Expression<?>[1];
                arguments20[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition20 = new Expression<Boolean>(_17_0_2_1_edc0357_1360958878627_104372_21890_exists);
                elaborationRule20 = addElaborationRule(condition20, _17_0_2_1_edc0357_1360798765060_634347_20307.this, Power_System._17_0_2_1_edc0357_1360798765060_634347_20307._17_0_2_1_edc0357_1360958878627_104372_21890.class, "f_end__JoinNode__start_system__Power_System", arguments20);
                Expression<?>[] arguments21 = new Expression<?>[1];
                arguments21[0] = new Expression<Integer>(startTime);
                Expression<Boolean> condition21 = new Expression<Boolean>(true);
                elaborationRule21 = addElaborationRule(condition21, l, LADWP._17_0_2_1_edc0357_1360798765078_727139_20329.class, "LADWP_CB__Activity__LADWP__LADWP", arguments21);
            }
        }

        public class _17_0_2_1_edc0357_1360798765805_710001_21070 extends DurativeEvent {

            public _17_0_2_1_edc0357_1360798765805_710001_21070() {
                super();
                init_17_0_2_1_edc0357_1360798765805_710001_21070Members();
                init_17_0_2_1_edc0357_1360798765805_710001_21070Collections();
                init_17_0_2_1_edc0357_1360798765805_710001_21070Elaborations();
            }

            public _17_0_2_1_edc0357_1360798765805_710001_21070(Expression<Integer> startTime) {
                super();
                init_17_0_2_1_edc0357_1360798765805_710001_21070Members();
                init_17_0_2_1_edc0357_1360798765805_710001_21070Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_1_edc0357_1360798765805_710001_21070Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter objectToPass = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Integer > finalNode_startTimeDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Effect effect22 = null;

            public Parameter effect22Var = null;

            public void init_17_0_2_1_edc0357_1360798765805_710001_21070Members() {
                try {
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect22VarV = sig_17_0_2_1_edc0357_1360798765808_557409_21085;
                    effect22Var = new Parameter("effect22Var", null, null, this);
                    addDependency(effect22Var, new Expression(effect22VarV));
                    effect22 = new EffectFunction(new EffectFunction(effect22Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "ROUND_TRIP_TEST", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Boolean>(objectToPass), new Expression<Integer>(endTime) }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_1_edc0357_1360798765805_710001_21070Collections() {
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect22Var = new TreeSet<Effect>();
                effectsForeffect22Var.add(effect22);
                addEffects((Parameter<?>) effect22Var, effectsForeffect22Var);
            }

            public void init_17_0_2_1_edc0357_1360798765805_710001_21070Dependencies() {
                addDependency(duration, new Expression<Integer>(18000));
                addDependency(finalNode_startTime, new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))));
                addDependency(objectToPass, new Expression<Boolean>(new EffectFunction(sig_17_0_2_1_edc0357_1360798765808_571002_21084, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "ROUND_TRIP_TEST", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_1_edc0357_1360798765805_710001_21070Elaborations() {
                init_17_0_2_1_edc0357_1360798765805_710001_21070Dependencies();
            }
        }

        public class _17_0_2_1_edc0357_1360798765806_270620_21071 extends DurativeEvent {

            public _17_0_2_1_edc0357_1360798765806_270620_21071() {
                super();
                init_17_0_2_1_edc0357_1360798765806_270620_21071Members();
                init_17_0_2_1_edc0357_1360798765806_270620_21071Collections();
                init_17_0_2_1_edc0357_1360798765806_270620_21071Elaborations();
            }

            public _17_0_2_1_edc0357_1360798765806_270620_21071(Expression<Integer> startTime) {
                super();
                init_17_0_2_1_edc0357_1360798765806_270620_21071Members();
                init_17_0_2_1_edc0357_1360798765806_270620_21071Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_1_edc0357_1360798765806_270620_21071Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter objectToPass = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Integer > endTimeDependency = null;

            public Dependency< Integer > finalNode_endTimeDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public void init_17_0_2_1_edc0357_1360798765806_270620_21071Members() {
                try {
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_1_edc0357_1360798765806_270620_21071Collections() {
                parameters.add(objectToPass);
            }

            public void init_17_0_2_1_edc0357_1360798765806_270620_21071Dependencies() {
                addDependency(duration, new Expression<Integer>(1));
                addDependency(endTime, new Expression<Integer>(new Functions.Plus(new Expression<Integer>(startTime), new Expression<Integer>(duration))));
                addDependency(finalNode_endTime, new Expression<Integer>(endTime));
                addDependency(objectToPass, new Expression<Boolean>(new EffectFunction(sig_17_0_2_1_edc0357_1360798765808_557409_21085, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "ROUND_TRIP_TEST", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_1_edc0357_1360798765806_270620_21071Elaborations() {
                init_17_0_2_1_edc0357_1360798765806_270620_21071Dependencies();
            }
        }

        public class _17_0_2_1_edc0357_1360958856427_568429_21872 extends DurativeEvent {

            public _17_0_2_1_edc0357_1360958856427_568429_21872() {
                super();
                init_17_0_2_1_edc0357_1360958856427_568429_21872Members();
                init_17_0_2_1_edc0357_1360958856427_568429_21872Collections();
                init_17_0_2_1_edc0357_1360958856427_568429_21872Elaborations();
            }

            public _17_0_2_1_edc0357_1360958856427_568429_21872(Expression<Integer> startTime) {
                super();
                init_17_0_2_1_edc0357_1360958856427_568429_21872Members();
                init_17_0_2_1_edc0357_1360958856427_568429_21872Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_1_edc0357_1360958856427_568429_21872Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_1_edc0357_1360798765801_883801_21064_exists = null;

            public BooleanParameter _17_0_2_1_edc0357_1360798765802_132157_21065_exists = null;

            public BooleanParameter _17_0_2_1_edc0357_1360798765804_814855_21068_exists = null;

            public BooleanParameter objectToPass = null;

            public Dependency< Boolean > _17_0_2_1_edc0357_1360798765801_883801_21064_existsDependency = null;

            public Dependency< Boolean > _17_0_2_1_edc0357_1360798765802_132157_21065_existsDependency = null;

            public Dependency< Boolean > _17_0_2_1_edc0357_1360798765804_814855_21068_existsDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Effect effect23 = null;

            public Parameter effect23Var = null;

            public Effect effect24 = null;

            public Parameter effect24Var = null;

            public Effect effect25 = null;

            public Parameter effect25Var = null;

            public ElaborationRule elaborationRule26 = null;

            public ElaborationRule elaborationRule27 = null;

            public ElaborationRule elaborationRule28 = null;

            public void init_17_0_2_1_edc0357_1360958856427_568429_21872Members() {
                try {
                    if (_17_0_2_1_edc0357_1360798765801_883801_21064_exists == null) _17_0_2_1_edc0357_1360798765801_883801_21064_exists = new BooleanParameter("_17_0_2_1_edc0357_1360798765801_883801_21064_exists", (Boolean) false, this);
                    if (_17_0_2_1_edc0357_1360798765802_132157_21065_exists == null) _17_0_2_1_edc0357_1360798765802_132157_21065_exists = new BooleanParameter("_17_0_2_1_edc0357_1360798765802_132157_21065_exists", (Boolean) false, this);
                    if (_17_0_2_1_edc0357_1360798765804_814855_21068_exists == null) _17_0_2_1_edc0357_1360798765804_814855_21068_exists = new BooleanParameter("_17_0_2_1_edc0357_1360798765804_814855_21068_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect23VarV = sig_17_0_2_1_edc0357_1360958739383_541838_21858;
                    effect23Var = new Parameter("effect23Var", null, null, this);
                    addDependency(effect23Var, new Expression(effect23VarV));
                    effect23 = new EffectFunction(new EffectFunction(effect23Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "ROUND_TRIP_TEST", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Boolean>(objectToPass), new Expression<Integer>(endTime) }));
                    Object effect24VarV = sig_17_0_2_1_edc0357_1360958861130_631052_21881;
                    effect24Var = new Parameter("effect24Var", null, null, this);
                    addDependency(effect24Var, new Expression(effect24VarV));
                    effect24 = new EffectFunction(new EffectFunction(effect24Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "ROUND_TRIP_TEST", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Boolean>(objectToPass), new Expression<Integer>(endTime) }));
                    Object effect25VarV = sig_17_0_2_1_edc0357_1360958866507_436590_21886;
                    effect25Var = new Parameter("effect25Var", null, null, this);
                    addDependency(effect25Var, new Expression(effect25VarV));
                    effect25 = new EffectFunction(new EffectFunction(effect25Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "ROUND_TRIP_TEST", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Boolean>(objectToPass), new Expression<Integer>(endTime) }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_1_edc0357_1360958856427_568429_21872Collections() {
                parameters.add(_17_0_2_1_edc0357_1360798765801_883801_21064_exists);
                parameters.add(_17_0_2_1_edc0357_1360798765802_132157_21065_exists);
                parameters.add(_17_0_2_1_edc0357_1360798765804_814855_21068_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect23Var = new TreeSet<Effect>();
                effectsForeffect23Var.add(effect23);
                addEffects((Parameter<?>) effect23Var, effectsForeffect23Var);
                Set<Effect> effectsForeffect24Var = new TreeSet<Effect>();
                effectsForeffect24Var.add(effect24);
                addEffects((Parameter<?>) effect24Var, effectsForeffect24Var);
                Set<Effect> effectsForeffect25Var = new TreeSet<Effect>();
                effectsForeffect25Var.add(effect25);
                addEffects((Parameter<?>) effect25Var, effectsForeffect25Var);
            }

            public void init_17_0_2_1_edc0357_1360958856427_568429_21872Dependencies() {
                addDependency(_17_0_2_1_edc0357_1360798765801_883801_21064_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765806_270620_21071_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765806_270620_21071_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(_17_0_2_1_edc0357_1360798765802_132157_21065_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765806_270620_21071_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765806_270620_21071_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(_17_0_2_1_edc0357_1360798765804_814855_21068_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765806_270620_21071_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765806_270620_21071_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new EffectFunction(sig_17_0_2_1_edc0357_1360958857864_402573_21876, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "ROUND_TRIP_TEST", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_1_edc0357_1360958856427_568429_21872Elaborations() {
                init_17_0_2_1_edc0357_1360958856427_568429_21872Dependencies();
                Expression<?>[] arguments26 = new Expression<?>[1];
                arguments26[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition26 = new Expression<Boolean>(_17_0_2_1_edc0357_1360798765801_883801_21064_exists);
                elaborationRule26 = addElaborationRule(condition26, _17_0_2_1_edc0357_1360798765060_634347_20307.this, Power_System._17_0_2_1_edc0357_1360798765060_634347_20307._17_0_2_1_edc0357_1360798765801_883801_21064.class, "Sys_readc__ReadStructuralFeatureAction__start_system__Power_System", arguments26);
                Expression<?>[] arguments27 = new Expression<?>[1];
                arguments27[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition27 = new Expression<Boolean>(_17_0_2_1_edc0357_1360798765804_814855_21068_exists);
                elaborationRule27 = addElaborationRule(condition27, _17_0_2_1_edc0357_1360798765060_634347_20307.this, Power_System._17_0_2_1_edc0357_1360798765060_634347_20307._17_0_2_1_edc0357_1360798765804_814855_21068.class, "Sys_readl__ReadStructuralFeatureAction__start_system__Power_System", arguments27);
                Expression<?>[] arguments28 = new Expression<?>[1];
                arguments28[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition28 = new Expression<Boolean>(_17_0_2_1_edc0357_1360798765802_132157_21065_exists);
                elaborationRule28 = addElaborationRule(condition28, _17_0_2_1_edc0357_1360798765060_634347_20307.this, Power_System._17_0_2_1_edc0357_1360798765060_634347_20307._17_0_2_1_edc0357_1360798765802_132157_21065.class, "Sys_readp__ReadStructuralFeatureAction__start_system__Power_System", arguments28);
            }
        }

        public class _17_0_2_1_edc0357_1360958878627_104372_21890 extends DurativeEvent {

            public _17_0_2_1_edc0357_1360958878627_104372_21890() {
                super();
                init_17_0_2_1_edc0357_1360958878627_104372_21890Members();
                init_17_0_2_1_edc0357_1360958878627_104372_21890Collections();
                init_17_0_2_1_edc0357_1360958878627_104372_21890Elaborations();
            }

            public _17_0_2_1_edc0357_1360958878627_104372_21890(Expression<Integer> startTime) {
                super();
                init_17_0_2_1_edc0357_1360958878627_104372_21890Members();
                init_17_0_2_1_edc0357_1360958878627_104372_21890Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_1_edc0357_1360958878627_104372_21890Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_1_edc0357_1360798765805_710001_21070_exists = null;

            public BooleanParameter objectToPass = null;

            public BooleanParameter objectToPass1 = null;

            public BooleanParameter objectToPass12 = null;

            public Dependency< Boolean > _17_0_2_1_edc0357_1360798765805_710001_21070_existsDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Dependency< Boolean > objectToPass1Dependency = null;

            public Dependency< Boolean > objectToPass12Dependency = null;

            public Effect effect29 = null;

            public Parameter effect29Var = null;

            public ElaborationRule elaborationRule30 = null;

            public void init_17_0_2_1_edc0357_1360958878627_104372_21890Members() {
                try {
                    if (_17_0_2_1_edc0357_1360798765805_710001_21070_exists == null) _17_0_2_1_edc0357_1360798765805_710001_21070_exists = new BooleanParameter("_17_0_2_1_edc0357_1360798765805_710001_21070_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (objectToPass1 == null) objectToPass1 = new BooleanParameter("objectToPass1", (Boolean) null, this);
                    if (objectToPass12 == null) objectToPass12 = new BooleanParameter("objectToPass12", (Boolean) null, this);
                    Object effect29VarV = sig_17_0_2_1_edc0357_1360798765808_571002_21084;
                    effect29Var = new Parameter("effect29Var", null, null, this);
                    addDependency(effect29Var, new Expression(effect29VarV));
                    effect29 = new EffectFunction(new EffectFunction(effect29Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "ROUND_TRIP_TEST", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Boolean>(objectToPass), new Expression<Integer>(endTime) }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_1_edc0357_1360958878627_104372_21890Collections() {
                parameters.add(_17_0_2_1_edc0357_1360798765805_710001_21070_exists);
                parameters.add(objectToPass);
                parameters.add(objectToPass1);
                parameters.add(objectToPass12);
                Set<Effect> effectsForeffect29Var = new TreeSet<Effect>();
                effectsForeffect29Var.add(effect29);
                addEffects((Parameter<?>) effect29Var, effectsForeffect29Var);
            }

            public void init_17_0_2_1_edc0357_1360958878627_104372_21890Dependencies() {
                addDependency(_17_0_2_1_edc0357_1360798765805_710001_21070_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765806_270620_21071_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_1_edc0357_1360798765806_270620_21071_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new EffectFunction(sig_17_0_2_1_edc0357_1360958889738_677596_21895, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "ROUND_TRIP_TEST", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(objectToPass1, new Expression<Boolean>(new EffectFunction(sig_17_0_2_1_edc0357_1360958894126_393790_21900, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "ROUND_TRIP_TEST", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(objectToPass12, new Expression<Boolean>(new EffectFunction(sig_17_0_2_1_edc0357_1360958897718_677891_21905, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "ROUND_TRIP_TEST", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_1_edc0357_1360958878627_104372_21890Elaborations() {
                init_17_0_2_1_edc0357_1360958878627_104372_21890Dependencies();
                Expression<?>[] arguments30 = new Expression<?>[1];
                arguments30[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition30 = new Expression<Boolean>(_17_0_2_1_edc0357_1360798765805_710001_21070_exists);
                elaborationRule30 = addElaborationRule(condition30, _17_0_2_1_edc0357_1360798765060_634347_20307.this, Power_System._17_0_2_1_edc0357_1360798765060_634347_20307._17_0_2_1_edc0357_1360798765805_710001_21070.class, "Sys_timer__AcceptEventAction__start_system__Power_System", arguments30);
            }
        }

        public _17_0_2_1_edc0357_1360798765060_634347_20307(Expression<DurativeEvent> caller, Expression<Integer> startTime) {
            super();
            init_17_0_2_1_edc0357_1360798765060_634347_20307Members();
            init_17_0_2_1_edc0357_1360798765060_634347_20307Collections();
            addDependency(this.caller, caller);
            addDependency(this.startTime, startTime);
            init_17_0_2_1_edc0357_1360798765060_634347_20307Elaborations();
            fixTimeDependencies();
        }

        public BooleanParameter _17_0_2_1_edc0357_1360798765806_270620_21071_exists = null;

        public Parameter< DurativeEvent > caller = null;

        public Parameter< TimeVaryingList<Integer> > decider_17_0_2_1_edc0357_1360958878627_104372_21890 = null;

        public IntegerParameter finalNode_endTime = null;

        public IntegerParameter finalNode_startTime = null;

        public Parameter< ObjectFlow<Customer> > sig_17_0_2_1_edc0357_1360798765807_756954_21078 = null;

        public Parameter< ObjectFlow<Power> > sig_17_0_2_1_edc0357_1360798765808_512362_21079 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_1_edc0357_1360798765808_557409_21085 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_1_edc0357_1360798765808_571002_21084 = null;

        public Parameter< ObjectFlow<LADWP> > sig_17_0_2_1_edc0357_1360798765808_997160_21082 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_1_edc0357_1360958739383_541838_21858 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_1_edc0357_1360958857864_402573_21876 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_1_edc0357_1360958861130_631052_21881 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_1_edc0357_1360958866507_436590_21886 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_1_edc0357_1360958889738_677596_21895 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_1_edc0357_1360958894126_393790_21900 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_1_edc0357_1360958897718_677891_21905 = null;

        public Dependency< Boolean > _17_0_2_1_edc0357_1360798765806_270620_21071_existsDependency = null;

        public Dependency< Integer > caller_endTimeDependency = null;

        public Dependency< Integer > endTimeDependency = null;

        public ElaborationRule elaborationRule0 = null;

        public ElaborationRule elaborationRule1 = null;

        public void init_17_0_2_1_edc0357_1360798765060_634347_20307Members() {
            try {
                if (_17_0_2_1_edc0357_1360798765806_270620_21071_exists == null) _17_0_2_1_edc0357_1360798765806_270620_21071_exists = new BooleanParameter("_17_0_2_1_edc0357_1360798765806_270620_21071_exists", (Boolean) false, this);
                if (caller == null) caller = new Parameter<DurativeEvent>("caller", null, (DurativeEvent) null, this);
                if (decider_17_0_2_1_edc0357_1360958878627_104372_21890 == null) decider_17_0_2_1_edc0357_1360958878627_104372_21890 = new Parameter<TimeVaryingList<Integer>>("decider_17_0_2_1_edc0357_1360958878627_104372_21890", null, (TimeVaryingList<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingList.class, java.lang.String.class, int.class), new Object[] { "decider_17_0_2_1_edc0357_1360958878627_104372_21890", 3 })).evaluate(true), this);
                if (finalNode_endTime == null) finalNode_endTime = new IntegerParameter("finalNode_endTime", (Integer) null, this);
                if (finalNode_startTime == null) finalNode_startTime = new IntegerParameter("finalNode_startTime", (Integer) null, this);
                if (sig_17_0_2_1_edc0357_1360798765807_756954_21078 == null) sig_17_0_2_1_edc0357_1360798765807_756954_21078 = new Parameter<ObjectFlow<Customer>>("sig_17_0_2_1_edc0357_1360798765807_756954_21078", null, (ObjectFlow<Customer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360798765807_756954_21078" })).evaluate(true), this);
                if (sig_17_0_2_1_edc0357_1360798765808_512362_21079 == null) sig_17_0_2_1_edc0357_1360798765808_512362_21079 = new Parameter<ObjectFlow<Power>>("sig_17_0_2_1_edc0357_1360798765808_512362_21079", null, (ObjectFlow<Power>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360798765808_512362_21079" })).evaluate(true), this);
                if (sig_17_0_2_1_edc0357_1360798765808_557409_21085 == null) sig_17_0_2_1_edc0357_1360798765808_557409_21085 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_1_edc0357_1360798765808_557409_21085", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360798765808_557409_21085" })).evaluate(true), this);
                if (sig_17_0_2_1_edc0357_1360798765808_571002_21084 == null) sig_17_0_2_1_edc0357_1360798765808_571002_21084 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_1_edc0357_1360798765808_571002_21084", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360798765808_571002_21084" })).evaluate(true), this);
                if (sig_17_0_2_1_edc0357_1360798765808_997160_21082 == null) sig_17_0_2_1_edc0357_1360798765808_997160_21082 = new Parameter<ObjectFlow<LADWP>>("sig_17_0_2_1_edc0357_1360798765808_997160_21082", null, (ObjectFlow<LADWP>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360798765808_997160_21082" })).evaluate(true), this);
                if (sig_17_0_2_1_edc0357_1360958739383_541838_21858 == null) sig_17_0_2_1_edc0357_1360958739383_541838_21858 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_1_edc0357_1360958739383_541838_21858", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360958739383_541838_21858" })).evaluate(true), this);
                if (sig_17_0_2_1_edc0357_1360958857864_402573_21876 == null) sig_17_0_2_1_edc0357_1360958857864_402573_21876 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_1_edc0357_1360958857864_402573_21876", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360958857864_402573_21876" })).evaluate(true), this);
                if (sig_17_0_2_1_edc0357_1360958861130_631052_21881 == null) sig_17_0_2_1_edc0357_1360958861130_631052_21881 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_1_edc0357_1360958861130_631052_21881", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360958861130_631052_21881" })).evaluate(true), this);
                if (sig_17_0_2_1_edc0357_1360958866507_436590_21886 == null) sig_17_0_2_1_edc0357_1360958866507_436590_21886 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_1_edc0357_1360958866507_436590_21886", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360958866507_436590_21886" })).evaluate(true), this);
                if (sig_17_0_2_1_edc0357_1360958889738_677596_21895 == null) sig_17_0_2_1_edc0357_1360958889738_677596_21895 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_1_edc0357_1360958889738_677596_21895", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360958889738_677596_21895" })).evaluate(true), this);
                if (sig_17_0_2_1_edc0357_1360958894126_393790_21900 == null) sig_17_0_2_1_edc0357_1360958894126_393790_21900 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_1_edc0357_1360958894126_393790_21900", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360958894126_393790_21900" })).evaluate(true), this);
                if (sig_17_0_2_1_edc0357_1360958897718_677891_21905 == null) sig_17_0_2_1_edc0357_1360958897718_677891_21905 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_1_edc0357_1360958897718_677891_21905", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_1_edc0357_1360958897718_677891_21905" })).evaluate(true), this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void init_17_0_2_1_edc0357_1360798765060_634347_20307Collections() {
            parameters.add(_17_0_2_1_edc0357_1360798765806_270620_21071_exists);
            parameters.add(caller);
            parameters.add(decider_17_0_2_1_edc0357_1360958878627_104372_21890);
            parameters.add(finalNode_endTime);
            parameters.add(finalNode_startTime);
            parameters.add(sig_17_0_2_1_edc0357_1360798765807_756954_21078);
            parameters.add(sig_17_0_2_1_edc0357_1360798765808_512362_21079);
            parameters.add(sig_17_0_2_1_edc0357_1360798765808_557409_21085);
            parameters.add(sig_17_0_2_1_edc0357_1360798765808_571002_21084);
            parameters.add(sig_17_0_2_1_edc0357_1360798765808_997160_21082);
            parameters.add(sig_17_0_2_1_edc0357_1360958739383_541838_21858);
            parameters.add(sig_17_0_2_1_edc0357_1360958857864_402573_21876);
            parameters.add(sig_17_0_2_1_edc0357_1360958861130_631052_21881);
            parameters.add(sig_17_0_2_1_edc0357_1360958866507_436590_21886);
            parameters.add(sig_17_0_2_1_edc0357_1360958889738_677596_21895);
            parameters.add(sig_17_0_2_1_edc0357_1360958894126_393790_21900);
            parameters.add(sig_17_0_2_1_edc0357_1360958897718_677891_21905);
        }

        public void init_17_0_2_1_edc0357_1360798765060_634347_20307Dependencies() {
            addDependency(_17_0_2_1_edc0357_1360798765806_270620_21071_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.NotEquals(new Expression<Integer>(finalNode_startTime), null)), new Expression(new EffectFunction(sig_17_0_2_1_edc0357_1360798765808_557409_21085, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "ROUND_TRIP_TEST", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(new Functions.Minus(new Expression<Integer>(finalNode_startTime), new Expression<Integer>(1))) })))));
            caller.getValue(true).addDependency(caller.getValue(true).endTime, new Expression<Integer>(finalNode_endTime));
            addDependency(endTime, new Expression<Integer>(finalNode_endTime));
        }

        public void init_17_0_2_1_edc0357_1360798765060_634347_20307Elaborations() {
            init_17_0_2_1_edc0357_1360798765060_634347_20307Dependencies();
            Expression<?>[] arguments0 = new Expression<?>[1];
            arguments0[0] = new Expression<Integer>(finalNode_startTime);
            Expression<Boolean> condition0 = new Expression<Boolean>(_17_0_2_1_edc0357_1360798765806_270620_21071_exists);
            elaborationRule0 = addElaborationRule(condition0, _17_0_2_1_edc0357_1360798765060_634347_20307.this, Power_System._17_0_2_1_edc0357_1360798765060_634347_20307._17_0_2_1_edc0357_1360798765806_270620_21071.class, "Sys_end__ActivityFinalNode__start_system__Power_System", arguments0);
            Expression<?>[] arguments1 = new Expression<?>[1];
            arguments1[0] = new Expression<Integer>(startTime);
            Expression<Boolean> condition1 = new Expression<Boolean>(true);
            elaborationRule1 = addElaborationRule(condition1, _17_0_2_1_edc0357_1360798765060_634347_20307.this, Power_System._17_0_2_1_edc0357_1360798765060_634347_20307._17_0_2_1_edc0357_1360798765799_670470_21061.class, "Sys_init__InitialNode__start_system__Power_System", arguments1);
        }
    }

    public class SignalchangeLoadValue extends ParameterListenerImpl {

        public SignalchangeLoadValue() {
            super();
            initSignalchangeLoadValueMembers();
            initSignalchangeLoadValueCollections();
            initSignalchangeLoadValueElaborations();
        }

        public SignalchangeLoadValue(Timepoint t, Float load) {
            super();
            initSignalchangeLoadValueMembers();
            initSignalchangeLoadValueCollections();
            initSignalchangeLoadValueElaborations();
            load__17_0_2_1_edc0357_1360798765030_737782_20262.getValue().setValue(t, load);
        }

        public Parameter< TimeVaryingMap<Float> > load__17_0_2_1_edc0357_1360798765030_737782_20262 = null;

        public void initSignalchangeLoadValueMembers() {
            try {
                if (load__17_0_2_1_edc0357_1360798765030_737782_20262 == null) load__17_0_2_1_edc0357_1360798765030_737782_20262 = new Parameter<TimeVaryingMap<Float>>("load__17_0_2_1_edc0357_1360798765030_737782_20262", null, (TimeVaryingMap<Float>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingMap.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Class.class), new Object[] { "load", null, null, Float.class })).evaluate(true), this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void initSignalchangeLoadValueCollections() {
            parameters.add(load__17_0_2_1_edc0357_1360798765030_737782_20262);
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

        public SignalchangeGenerationValue(Timepoint t, Float newGenValue) {
            super();
            initSignalchangeGenerationValueMembers();
            initSignalchangeGenerationValueCollections();
            initSignalchangeGenerationValueElaborations();
            newGenValue__17_0_2_1_edc0357_1360798765030_16604_20261.getValue().setValue(t, newGenValue);
        }

        public Parameter< TimeVaryingMap<Float> > newGenValue__17_0_2_1_edc0357_1360798765030_16604_20261 = null;

        public void initSignalchangeGenerationValueMembers() {
            try {
                if (newGenValue__17_0_2_1_edc0357_1360798765030_16604_20261 == null) newGenValue__17_0_2_1_edc0357_1360798765030_16604_20261 = new Parameter<TimeVaryingMap<Float>>("newGenValue__17_0_2_1_edc0357_1360798765030_16604_20261", null, (TimeVaryingMap<Float>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingMap.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Class.class), new Object[] { "newGenValue", null, null, Float.class })).evaluate(true), this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void initSignalchangeGenerationValueCollections() {
            parameters.add(newGenValue__17_0_2_1_edc0357_1360798765030_16604_20261);
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

        public Signaldr_request(Timepoint t, Integer endTime, Float reduction_percent, Integer startTime) {
            super();
            initSignaldr_requestMembers();
            initSignaldr_requestCollections();
            initSignaldr_requestElaborations();
            reduction_percent__17_0_2_1_edc0357_1360798765096_200759_20353.getValue().setValue(t, reduction_percent);
            startTime__17_0_2_1_edc0357_1360951246626_674842_20288.getValue().setValue(t, startTime);
            endTime__17_0_2_1_edc0357_1360951280962_119825_20290.getValue().setValue(t, endTime);
        }

        public Parameter< TimeVaryingMap<Integer> > endTime__17_0_2_1_edc0357_1360951280962_119825_20290 = null;

        public Parameter< TimeVaryingMap<Float> > reduction_percent__17_0_2_1_edc0357_1360798765096_200759_20353 = null;

        public Parameter< TimeVaryingMap<Integer> > startTime__17_0_2_1_edc0357_1360951246626_674842_20288 = null;

        public void initSignaldr_requestMembers() {
            try {
                if (endTime__17_0_2_1_edc0357_1360951280962_119825_20290 == null) endTime__17_0_2_1_edc0357_1360951280962_119825_20290 = new Parameter<TimeVaryingMap<Integer>>("endTime__17_0_2_1_edc0357_1360951280962_119825_20290", null, (TimeVaryingMap<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingMap.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Class.class), new Object[] { "endTime", null, null, Integer.class })).evaluate(true), this);
                if (reduction_percent__17_0_2_1_edc0357_1360798765096_200759_20353 == null) reduction_percent__17_0_2_1_edc0357_1360798765096_200759_20353 = new Parameter<TimeVaryingMap<Float>>("reduction_percent__17_0_2_1_edc0357_1360798765096_200759_20353", null, (TimeVaryingMap<Float>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingMap.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Class.class), new Object[] { "reduction_percent", null, null, Float.class })).evaluate(true), this);
                if (startTime__17_0_2_1_edc0357_1360951246626_674842_20288 == null) startTime__17_0_2_1_edc0357_1360951246626_674842_20288 = new Parameter<TimeVaryingMap<Integer>>("startTime__17_0_2_1_edc0357_1360951246626_674842_20288", null, (TimeVaryingMap<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingMap.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Class.class), new Object[] { "startTime", null, null, Integer.class })).evaluate(true), this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void initSignaldr_requestCollections() {
            parameters.add(endTime__17_0_2_1_edc0357_1360951280962_119825_20290);
            parameters.add(reduction_percent__17_0_2_1_edc0357_1360798765096_200759_20353);
            parameters.add(startTime__17_0_2_1_edc0357_1360951246626_674842_20288);
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

        public Signalyes(Timepoint t, Float newLoad) {
            super();
            initSignalyesMembers();
            initSignalyesCollections();
            initSignalyesElaborations();
            newLoad__17_0_2_1_edc0357_1360798765097_803626_20354.getValue().setValue(t, newLoad);
        }

        public Parameter< TimeVaryingMap<Float> > newLoad__17_0_2_1_edc0357_1360798765097_803626_20354 = null;

        public void initSignalyesMembers() {
            try {
                if (newLoad__17_0_2_1_edc0357_1360798765097_803626_20354 == null) newLoad__17_0_2_1_edc0357_1360798765097_803626_20354 = new Parameter<TimeVaryingMap<Float>>("newLoad__17_0_2_1_edc0357_1360798765097_803626_20354", null, (TimeVaryingMap<Float>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingMap.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Class.class), new Object[] { "newLoad", null, null, Float.class })).evaluate(true), this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void initSignalyesCollections() {
            parameters.add(newLoad__17_0_2_1_edc0357_1360798765097_803626_20354);
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

        public SignalreceiveMeterReading(Timepoint t, Float meter_value) {
            super();
            initSignalreceiveMeterReadingMembers();
            initSignalreceiveMeterReadingCollections();
            initSignalreceiveMeterReadingElaborations();
            meter_value__17_0_2_1_edc0357_1360798765095_360811_20350.getValue().setValue(t, meter_value);
        }

        public Parameter< TimeVaryingMap<Float> > meter_value__17_0_2_1_edc0357_1360798765095_360811_20350 = null;

        public void initSignalreceiveMeterReadingMembers() {
            try {
                if (meter_value__17_0_2_1_edc0357_1360798765095_360811_20350 == null) meter_value__17_0_2_1_edc0357_1360798765095_360811_20350 = new Parameter<TimeVaryingMap<Float>>("meter_value__17_0_2_1_edc0357_1360798765095_360811_20350", null, (TimeVaryingMap<Float>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingMap.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Class.class), new Object[] { "meter_value", null, null, Float.class })).evaluate(true), this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void initSignalreceiveMeterReadingCollections() {
            parameters.add(meter_value__17_0_2_1_edc0357_1360798765095_360811_20350);
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

        public SignalreceiveLoadReading(Timepoint t, Float actual_load) {
            super();
            initSignalreceiveLoadReadingMembers();
            initSignalreceiveLoadReadingCollections();
            initSignalreceiveLoadReadingElaborations();
            actual_load__17_0_2_1_edc0357_1360798765095_91554_20351.getValue().setValue(t, actual_load);
        }

        public Parameter< TimeVaryingMap<Float> > actual_load__17_0_2_1_edc0357_1360798765095_91554_20351 = null;

        public void initSignalreceiveLoadReadingMembers() {
            try {
                if (actual_load__17_0_2_1_edc0357_1360798765095_91554_20351 == null) actual_load__17_0_2_1_edc0357_1360798765095_91554_20351 = new Parameter<TimeVaryingMap<Float>>("actual_load__17_0_2_1_edc0357_1360798765095_91554_20351", null, (TimeVaryingMap<Float>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingMap.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Class.class), new Object[] { "actual_load", null, null, Float.class })).evaluate(true), this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void initSignalreceiveLoadReadingCollections() {
            parameters.add(actual_load__17_0_2_1_edc0357_1360798765095_91554_20351);
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

        public SignalreceiveGenReading(Timepoint t, Float actual_power) {
            super();
            initSignalreceiveGenReadingMembers();
            initSignalreceiveGenReadingCollections();
            initSignalreceiveGenReadingElaborations();
            actual_power__17_0_2_1_edc0357_1360798765096_597525_20352.getValue().setValue(t, actual_power);
        }

        public Parameter< TimeVaryingMap<Float> > actual_power__17_0_2_1_edc0357_1360798765096_597525_20352 = null;

        public void initSignalreceiveGenReadingMembers() {
            try {
                if (actual_power__17_0_2_1_edc0357_1360798765096_597525_20352 == null) actual_power__17_0_2_1_edc0357_1360798765096_597525_20352 = new Parameter<TimeVaryingMap<Float>>("actual_power__17_0_2_1_edc0357_1360798765096_597525_20352", null, (TimeVaryingMap<Float>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingMap.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Class.class), new Object[] { "actual_power", null, null, Float.class })).evaluate(true), this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void initSignalreceiveGenReadingCollections() {
            parameters.add(actual_power__17_0_2_1_edc0357_1360798765096_597525_20352);
        }

        public void initSignalreceiveGenReadingDependencies() {
        }

        public void initSignalreceiveGenReadingElaborations() {
            initSignalreceiveGenReadingDependencies();
        }
    }

    public class Signalgeneration_ack extends ParameterListenerImpl {

        public Signalgeneration_ack() {
            super();
            initSignalgeneration_ackMembers();
            initSignalgeneration_ackCollections();
            initSignalgeneration_ackElaborations();
        }

        public Signalgeneration_ack(Timepoint t, Boolean x) {
            super();
            initSignalgeneration_ackMembers();
            initSignalgeneration_ackCollections();
            initSignalgeneration_ackElaborations();
            control.getValue().setValue(t, x);
        }

        public Parameter< TimeVaryingMap<Boolean> > control = null;

        public void initSignalgeneration_ackMembers() {
            try {
                if (control == null) control = new Parameter<TimeVaryingMap<Boolean>>("control", null, (TimeVaryingMap<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingMap.class, java.lang.String.class), new Object[] { "control" })).evaluate(true), this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void initSignalgeneration_ackCollections() {
            parameters.add(control);
        }

        public void initSignalgeneration_ackDependencies() {
        }

        public void initSignalgeneration_ackElaborations() {
            initSignalgeneration_ackDependencies();
        }
    }

    public Power_System() {
        super();
        initPower_SystemMembers();
        initPower_SystemCollections();
        initPower_SystemElaborations();
        ((ObjectFlow) ss_17_0_2_1_edc0357_1360798765040_940520_20273_changeLoadValue.getValue()).addListener(((ObjectFlow) (p.getValue()).q_Power_changeLoadValue.getValue()));
        ((ObjectFlow) ss_17_0_2_1_edc0357_1360798765087_614926_20337_changeGenerationValue.getValue()).addListener(((ObjectFlow) (p.getValue()).q_Power_changeGenerationValue.getValue()));
        ((ObjectFlow) ss_17_0_2_1_edc0357_1360798765085_887966_20334_dr_request.getValue()).addListener(((ObjectFlow) (c.getValue()).q_Customer_dr_request.getValue()));
        ((ObjectFlow) ss_17_0_2_1_edc0357_1360798765041_950141_20275_yes.getValue()).addListener(((ObjectFlow) (l.getValue()).q_LADWP_yes.getValue()));
        ((ObjectFlow) ss_17_0_2_1_edc0357_1360798765041_950141_20275_no.getValue()).addListener(((ObjectFlow) (l.getValue()).q_LADWP_no.getValue()));
        ((ObjectFlow) ss_17_0_2_1_edc0357_1360798765042_106286_20276_receiveMeterReading.getValue()).addListener(((ObjectFlow) (l.getValue()).q_LADWP_receiveMeterReading.getValue()));
        ((ObjectFlow) ss_17_0_2_1_edc0357_1360798765027_973745_20257_receiveLoadReading.getValue()).addListener(((ObjectFlow) (l.getValue()).q_LADWP_receiveLoadReading.getValue()));
        ((ObjectFlow) ss_17_0_2_1_edc0357_1360798765028_640858_20258_receiveGenReading.getValue()).addListener(((ObjectFlow) (l.getValue()).q_LADWP_receiveGenReading.getValue()));
        ((ObjectFlow) ss_17_0_2_1_edc0357_1360961106142_903091_22490_generation_ack.getValue()).addListener(((ObjectFlow) (l.getValue()).q_LADWP_generation_ack.getValue()));
    }

    public Parameter< Customer > c = null;

    public StringParameter classifierBehavior = null;

    public Parameter< LADWP > l = null;

    public Parameter< Power > p = null;

    public Parameter< ObjectFlow<Power_System.SignalreceiveLoadReading> > ss_17_0_2_1_edc0357_1360798765027_973745_20257_receiveLoadReading = null;

    public Parameter< ObjectFlow<Power_System.SignalreceiveGenReading> > ss_17_0_2_1_edc0357_1360798765028_640858_20258_receiveGenReading = null;

    public Parameter< ObjectFlow<Power_System.SignalchangeLoadValue> > ss_17_0_2_1_edc0357_1360798765040_940520_20273_changeLoadValue = null;

    public Parameter< ObjectFlow<Power_System.Signalno> > ss_17_0_2_1_edc0357_1360798765041_950141_20275_no = null;

    public Parameter< ObjectFlow<Power_System.Signalyes> > ss_17_0_2_1_edc0357_1360798765041_950141_20275_yes = null;

    public Parameter< ObjectFlow<Power_System.SignalreceiveMeterReading> > ss_17_0_2_1_edc0357_1360798765042_106286_20276_receiveMeterReading = null;

    public Parameter< ObjectFlow<Power_System.Signaldr_request> > ss_17_0_2_1_edc0357_1360798765085_887966_20334_dr_request = null;

    public Parameter< ObjectFlow<Power_System.SignalchangeGenerationValue> > ss_17_0_2_1_edc0357_1360798765087_614926_20337_changeGenerationValue = null;

    public Parameter< ObjectFlow<Power_System.Signalgeneration_ack> > ss_17_0_2_1_edc0357_1360961106142_903091_22490_generation_ack = null;

    public void initPower_SystemMembers() {
        try {
            if (c == null) c = new Parameter<Customer>("c", null, (Customer) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(Customer.class, Power_System.class), new Object[] { this })).evaluate(true), this);
            if (classifierBehavior == null) classifierBehavior = new StringParameter("classifierBehavior", (String) "_17_0_2_1_edc0357_1360798765060_634347_20307", this);
            if (l == null) l = new Parameter<LADWP>("l", null, (LADWP) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(LADWP.class, Power_System.class), new Object[] { this })).evaluate(true), this);
            if (p == null) p = new Parameter<Power>("p", null, (Power) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(Power.class, Power_System.class), new Object[] { this })).evaluate(true), this);
            if (ss_17_0_2_1_edc0357_1360798765027_973745_20257_receiveLoadReading == null) ss_17_0_2_1_edc0357_1360798765027_973745_20257_receiveLoadReading = new Parameter<ObjectFlow<Power_System.SignalreceiveLoadReading>>("ss_17_0_2_1_edc0357_1360798765027_973745_20257_receiveLoadReading", null, (ObjectFlow<Power_System.SignalreceiveLoadReading>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class, java.lang.Class.class), new Object[] { "ss_17_0_2_1_edc0357_1360798765027_973745_20257_receiveLoadReading", Power_System.SignalreceiveLoadReading.class })).evaluate(true), this);
            if (ss_17_0_2_1_edc0357_1360798765028_640858_20258_receiveGenReading == null) ss_17_0_2_1_edc0357_1360798765028_640858_20258_receiveGenReading = new Parameter<ObjectFlow<Power_System.SignalreceiveGenReading>>("ss_17_0_2_1_edc0357_1360798765028_640858_20258_receiveGenReading", null, (ObjectFlow<Power_System.SignalreceiveGenReading>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class, java.lang.Class.class), new Object[] { "ss_17_0_2_1_edc0357_1360798765028_640858_20258_receiveGenReading", Power_System.SignalreceiveGenReading.class })).evaluate(true), this);
            if (ss_17_0_2_1_edc0357_1360798765040_940520_20273_changeLoadValue == null) ss_17_0_2_1_edc0357_1360798765040_940520_20273_changeLoadValue = new Parameter<ObjectFlow<Power_System.SignalchangeLoadValue>>("ss_17_0_2_1_edc0357_1360798765040_940520_20273_changeLoadValue", null, (ObjectFlow<Power_System.SignalchangeLoadValue>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class, java.lang.Class.class), new Object[] { "ss_17_0_2_1_edc0357_1360798765040_940520_20273_changeLoadValue", Power_System.SignalchangeLoadValue.class })).evaluate(true), this);
            if (ss_17_0_2_1_edc0357_1360798765041_950141_20275_no == null) ss_17_0_2_1_edc0357_1360798765041_950141_20275_no = new Parameter<ObjectFlow<Power_System.Signalno>>("ss_17_0_2_1_edc0357_1360798765041_950141_20275_no", null, (ObjectFlow<Power_System.Signalno>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class, java.lang.Class.class), new Object[] { "ss_17_0_2_1_edc0357_1360798765041_950141_20275_no", Power_System.Signalno.class })).evaluate(true), this);
            if (ss_17_0_2_1_edc0357_1360798765041_950141_20275_yes == null) ss_17_0_2_1_edc0357_1360798765041_950141_20275_yes = new Parameter<ObjectFlow<Power_System.Signalyes>>("ss_17_0_2_1_edc0357_1360798765041_950141_20275_yes", null, (ObjectFlow<Power_System.Signalyes>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class, java.lang.Class.class), new Object[] { "ss_17_0_2_1_edc0357_1360798765041_950141_20275_yes", Power_System.Signalyes.class })).evaluate(true), this);
            if (ss_17_0_2_1_edc0357_1360798765042_106286_20276_receiveMeterReading == null) ss_17_0_2_1_edc0357_1360798765042_106286_20276_receiveMeterReading = new Parameter<ObjectFlow<Power_System.SignalreceiveMeterReading>>("ss_17_0_2_1_edc0357_1360798765042_106286_20276_receiveMeterReading", null, (ObjectFlow<Power_System.SignalreceiveMeterReading>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class, java.lang.Class.class), new Object[] { "ss_17_0_2_1_edc0357_1360798765042_106286_20276_receiveMeterReading", Power_System.SignalreceiveMeterReading.class })).evaluate(true), this);
            if (ss_17_0_2_1_edc0357_1360798765085_887966_20334_dr_request == null) ss_17_0_2_1_edc0357_1360798765085_887966_20334_dr_request = new Parameter<ObjectFlow<Power_System.Signaldr_request>>("ss_17_0_2_1_edc0357_1360798765085_887966_20334_dr_request", null, (ObjectFlow<Power_System.Signaldr_request>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class, java.lang.Class.class), new Object[] { "ss_17_0_2_1_edc0357_1360798765085_887966_20334_dr_request", Power_System.Signaldr_request.class })).evaluate(true), this);
            if (ss_17_0_2_1_edc0357_1360798765087_614926_20337_changeGenerationValue == null) ss_17_0_2_1_edc0357_1360798765087_614926_20337_changeGenerationValue = new Parameter<ObjectFlow<Power_System.SignalchangeGenerationValue>>("ss_17_0_2_1_edc0357_1360798765087_614926_20337_changeGenerationValue", null, (ObjectFlow<Power_System.SignalchangeGenerationValue>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class, java.lang.Class.class), new Object[] { "ss_17_0_2_1_edc0357_1360798765087_614926_20337_changeGenerationValue", Power_System.SignalchangeGenerationValue.class })).evaluate(true), this);
            if (ss_17_0_2_1_edc0357_1360961106142_903091_22490_generation_ack == null) ss_17_0_2_1_edc0357_1360961106142_903091_22490_generation_ack = new Parameter<ObjectFlow<Power_System.Signalgeneration_ack>>("ss_17_0_2_1_edc0357_1360961106142_903091_22490_generation_ack", null, (ObjectFlow<Power_System.Signalgeneration_ack>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class, java.lang.Class.class), new Object[] { "ss_17_0_2_1_edc0357_1360961106142_903091_22490_generation_ack", Power_System.Signalgeneration_ack.class })).evaluate(true), this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void initPower_SystemCollections() {
        parameters.add(c);
        parameters.add(classifierBehavior);
        parameters.add(l);
        parameters.add(p);
        parameters.add(ss_17_0_2_1_edc0357_1360798765027_973745_20257_receiveLoadReading);
        parameters.add(ss_17_0_2_1_edc0357_1360798765028_640858_20258_receiveGenReading);
        parameters.add(ss_17_0_2_1_edc0357_1360798765040_940520_20273_changeLoadValue);
        parameters.add(ss_17_0_2_1_edc0357_1360798765041_950141_20275_no);
        parameters.add(ss_17_0_2_1_edc0357_1360798765041_950141_20275_yes);
        parameters.add(ss_17_0_2_1_edc0357_1360798765042_106286_20276_receiveMeterReading);
        parameters.add(ss_17_0_2_1_edc0357_1360798765085_887966_20334_dr_request);
        parameters.add(ss_17_0_2_1_edc0357_1360798765087_614926_20337_changeGenerationValue);
        parameters.add(ss_17_0_2_1_edc0357_1360961106142_903091_22490_generation_ack);
    }

    public void initPower_SystemDependencies() {
    }

    public void initPower_SystemElaborations() {
        initPower_SystemDependencies();
    }
}
