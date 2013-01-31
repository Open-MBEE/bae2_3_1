package PowerSystem3;

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

public class Customer extends ParameterListenerImpl {

    public Customer() {
        super();
        initCustomerMembers();
        initCustomerCollections();
        initCustomerElaborations();
    }

    public class _17_0_2_2_edc0357_1357846886589_155109_19879 extends DurativeEvent {

        public _17_0_2_2_edc0357_1357846886589_155109_19879() {
            super();
            init_17_0_2_2_edc0357_1357846886589_155109_19879Members();
            init_17_0_2_2_edc0357_1357846886589_155109_19879Collections();
            init_17_0_2_2_edc0357_1357846886589_155109_19879Elaborations();
        }

        public class _17_0_2_2_edc0357_1357846886975_449623_20290 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846886975_449623_20290() {
                super();
                init_17_0_2_2_edc0357_1357846886975_449623_20290Members();
                init_17_0_2_2_edc0357_1357846886975_449623_20290Collections();
                init_17_0_2_2_edc0357_1357846886975_449623_20290Elaborations();
            }

            public _17_0_2_2_edc0357_1357846886975_449623_20290(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846886975_449623_20290Members();
                init_17_0_2_2_edc0357_1357846886975_449623_20290Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846886975_449623_20290Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter objectToPass = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846886976_750345_20291_exists = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846886976_750345_20291_existsDependency = null;

            public Effect effect34 = null;

            public Parameter effect34Var = null;

            public ElaborationRule elaborationRule35 = null;

            public void init_17_0_2_2_edc0357_1357846886975_449623_20290Members() {
                try {
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (_17_0_2_2_edc0357_1357846886976_750345_20291_exists == null) _17_0_2_2_edc0357_1357846886976_750345_20291_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846886976_750345_20291_exists", (Boolean) false, this);
                    Object effect34VarV = sig_17_0_2_2_edc0357_1357846886979_672158_20296;
                    effect34Var = new Parameter("effect34Var", null, null, this);
                    addDependency(effect34Var, new Expression(effect34VarV));
                    effect34 = new EffectFunction(new EffectFunction(effect34Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846886975_449623_20290Collections() {
                parameters.add(objectToPass);
                parameters.add(_17_0_2_2_edc0357_1357846886976_750345_20291_exists);
                Set<Effect> effectsForeffect34Var = new TreeSet<Effect>();
                effectsForeffect34Var.add(effect34);
                addEffects((Parameter<?>) effect34Var, effectsForeffect34Var);
            }

            public void init_17_0_2_2_edc0357_1357846886975_449623_20290Dependencies() {
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(true));
                addDependency(_17_0_2_2_edc0357_1357846886976_750345_20291_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886976_56779_20292_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886976_56779_20292_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
            }

            public void init_17_0_2_2_edc0357_1357846886975_449623_20290Elaborations() {
                init_17_0_2_2_edc0357_1357846886975_449623_20290Dependencies();
                Expression<?>[] arguments35 = new Expression<?>[1];
                arguments35[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition35 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846886976_750345_20291_exists);
                elaborationRule35 = addElaborationRule(condition35, _17_0_2_2_edc0357_1357846886589_155109_19879.this, Customer._17_0_2_2_edc0357_1357846886589_155109_19879._17_0_2_2_edc0357_1357846886976_750345_20291.class, "CUse_readself_ReadSelfAction_readMeter", arguments35);
            }
        }

        public class _17_0_2_2_edc0357_1357846886976_750345_20291 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846886976_750345_20291() {
                super();
                init_17_0_2_2_edc0357_1357846886976_750345_20291Members();
                init_17_0_2_2_edc0357_1357846886976_750345_20291Collections();
                init_17_0_2_2_edc0357_1357846886976_750345_20291Elaborations();
            }

            public _17_0_2_2_edc0357_1357846886976_750345_20291(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846886976_750345_20291Members();
                init_17_0_2_2_edc0357_1357846886976_750345_20291Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846886976_750345_20291Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_2_edc0357_1357846886978_946332_20294_exists = null;

            public BooleanParameter objectToPass = null;

            public Parameter< Customer > _17_0_2_2_edc0357_1357846887434_255114_21368 = null;

            public Dependency _17_0_2_2_edc0357_1357846886978_946332_20294_existsDependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Effect effect36 = null;

            public Parameter effect36Var = null;

            public ElaborationRule elaborationRule37 = null;

            public void init_17_0_2_2_edc0357_1357846886976_750345_20291Members() {
                try {
                    if (_17_0_2_2_edc0357_1357846886978_946332_20294_exists == null) _17_0_2_2_edc0357_1357846886978_946332_20294_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846886978_946332_20294_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (_17_0_2_2_edc0357_1357846887434_255114_21368 == null) _17_0_2_2_edc0357_1357846887434_255114_21368 = new Parameter<Customer>("_17_0_2_2_edc0357_1357846887434_255114_21368", null, (Customer) Customer.this, this);
                    Object effect36VarV = sig_17_0_2_2_edc0357_1357846886979_771050_20297;
                    effect36Var = new Parameter("effect36Var", null, null, this);
                    addDependency(effect36Var, new Expression(effect36VarV));
                    effect36 = new EffectFunction(new EffectFunction(effect36Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { _17_0_2_2_edc0357_1357846887434_255114_21368, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846886976_750345_20291Collections() {
                parameters.add(_17_0_2_2_edc0357_1357846886978_946332_20294_exists);
                parameters.add(objectToPass);
                parameters.add(_17_0_2_2_edc0357_1357846887434_255114_21368);
                Set<Effect> effectsForeffect36Var = new TreeSet<Effect>();
                effectsForeffect36Var.add(effect36);
                addEffects((Parameter<?>) effect36Var, effectsForeffect36Var);
            }

            public void init_17_0_2_2_edc0357_1357846886976_750345_20291Dependencies() {
                addDependency(_17_0_2_2_edc0357_1357846886978_946332_20294_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886976_56779_20292_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886976_56779_20292_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846886979_672158_20296, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_2_edc0357_1357846886976_750345_20291Elaborations() {
                init_17_0_2_2_edc0357_1357846886976_750345_20291Dependencies();
                Expression<?>[] arguments37 = new Expression<?>[1];
                arguments37[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition37 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846886978_946332_20294_exists);
                elaborationRule37 = addElaborationRule(condition37, _17_0_2_2_edc0357_1357846886589_155109_19879.this, Customer._17_0_2_2_edc0357_1357846886589_155109_19879._17_0_2_2_edc0357_1357846886978_946332_20294.class, "CUse_forkself_ForkNode_readMeter", arguments37);
            }
        }

        public class _17_0_2_2_edc0357_1357846886976_56779_20292 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846886976_56779_20292() {
                super();
                init_17_0_2_2_edc0357_1357846886976_56779_20292Members();
                init_17_0_2_2_edc0357_1357846886976_56779_20292Collections();
                init_17_0_2_2_edc0357_1357846886976_56779_20292Elaborations();
            }

            public _17_0_2_2_edc0357_1357846886976_56779_20292(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846886976_56779_20292Members();
                init_17_0_2_2_edc0357_1357846886976_56779_20292Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846886976_56779_20292Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter objectToPass = null;

            public Dependency endTimeDependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Dependency finalNode_endTimeDependency = null;

            public void init_17_0_2_2_edc0357_1357846886976_56779_20292Members() {
                try {
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846886976_56779_20292Collections() {
                parameters.add(objectToPass);
            }

            public void init_17_0_2_2_edc0357_1357846886976_56779_20292Dependencies() {
                addDependency(endTime, new Expression<Integer>(new Functions.Plus(new Expression<Integer>(startTime), new Expression<Integer>(duration))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846886980_561499_20301, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(finalNode_endTime, new Expression<Integer>(endTime));
            }

            public void init_17_0_2_2_edc0357_1357846886976_56779_20292Elaborations() {
                init_17_0_2_2_edc0357_1357846886976_56779_20292Dependencies();
            }
        }

        public class _17_0_2_2_edc0357_1357846886977_933314_20293 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846886977_933314_20293() {
                super();
                init_17_0_2_2_edc0357_1357846886977_933314_20293Members();
                init_17_0_2_2_edc0357_1357846886977_933314_20293Collections();
                init_17_0_2_2_edc0357_1357846886977_933314_20293Elaborations();
            }

            public _17_0_2_2_edc0357_1357846886977_933314_20293(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846886977_933314_20293Members();
                init_17_0_2_2_edc0357_1357846886977_933314_20293Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846886977_933314_20293Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter addToDecider__17_0_2_2_edc0357_1357846886978_453209_20295 = null;

            public Parameter< Customer > _17_0_2_2_edc0357_1357846887435_175801_21370 = null;

            public IntegerParameter myDeciderID_decider_17_0_2_2_edc0357_1357846886978_453209_20295 = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846886978_453209_20295_exists = null;

            public IntegerParameter _17_0_2_2_edc0357_1357846887435_802408_21369 = null;

            public Dependency addToDecider__17_0_2_2_edc0357_1357846886978_453209_20295Dependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887435_175801_21370Dependency = null;

            public Dependency myDeciderID_decider_17_0_2_2_edc0357_1357846886978_453209_20295Dependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887435_802408_21369Dependency = null;

            public Dependency durationDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846886978_453209_20295_existsDependency = null;

            public Effect effect38 = null;

            public Parameter effect38Var = null;

            public Effect effect39 = null;

            public Parameter effect39Var = null;

            public ElaborationRule elaborationRule40 = null;

            public void init_17_0_2_2_edc0357_1357846886977_933314_20293Members() {
                try {
                    if (addToDecider__17_0_2_2_edc0357_1357846886978_453209_20295 == null) addToDecider__17_0_2_2_edc0357_1357846886978_453209_20295 = new BooleanParameter("addToDecider__17_0_2_2_edc0357_1357846886978_453209_20295", (Boolean) false, this);
                    if (_17_0_2_2_edc0357_1357846887435_175801_21370 == null) _17_0_2_2_edc0357_1357846887435_175801_21370 = new Parameter<Customer>("_17_0_2_2_edc0357_1357846887435_175801_21370", null, (Customer) null, this);
                    if (myDeciderID_decider_17_0_2_2_edc0357_1357846886978_453209_20295 == null) myDeciderID_decider_17_0_2_2_edc0357_1357846886978_453209_20295 = new IntegerParameter("myDeciderID_decider_17_0_2_2_edc0357_1357846886978_453209_20295", (Integer) 1, this);
                    if (_17_0_2_2_edc0357_1357846886978_453209_20295_exists == null) _17_0_2_2_edc0357_1357846886978_453209_20295_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846886978_453209_20295_exists", (Boolean) false, this);
                    if (_17_0_2_2_edc0357_1357846887435_802408_21369 == null) _17_0_2_2_edc0357_1357846887435_802408_21369 = new IntegerParameter("_17_0_2_2_edc0357_1357846887435_802408_21369", (Integer) null, this);
                    Object effect38VarV = sig_17_0_2_2_edc0357_1357846886980_99191_20300;
                    effect38Var = new Parameter("effect38Var", null, null, this);
                    addDependency(effect38Var, new Expression(effect38VarV));
                    effect38 = new EffectFunction(new EffectFunction(effect38Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { _17_0_2_2_edc0357_1357846887435_802408_21369, endTime }));
                    Object effect39VarV = decider_17_0_2_2_edc0357_1357846886978_453209_20295;
                    effect39Var = new Parameter("effect39Var", null, null, this);
                    addDependency(effect39Var, new Expression(effect39VarV));
                    effect39 = new EffectFunction(new EffectFunction(effect39Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class, java.lang.Boolean.class), new Object[] { endTime, myDeciderID_decider_17_0_2_2_edc0357_1357846886978_453209_20295, addToDecider__17_0_2_2_edc0357_1357846886978_453209_20295 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846886977_933314_20293Collections() {
                parameters.add(addToDecider__17_0_2_2_edc0357_1357846886978_453209_20295);
                parameters.add(_17_0_2_2_edc0357_1357846887435_175801_21370);
                parameters.add(myDeciderID_decider_17_0_2_2_edc0357_1357846886978_453209_20295);
                parameters.add(_17_0_2_2_edc0357_1357846886978_453209_20295_exists);
                parameters.add(_17_0_2_2_edc0357_1357846887435_802408_21369);
                Set<Effect> effectsForeffect38Var = new TreeSet<Effect>();
                effectsForeffect38Var.add(effect38);
                addEffects((Parameter<?>) effect38Var, effectsForeffect38Var);
                Set<Effect> effectsForeffect39Var = new TreeSet<Effect>();
                effectsForeffect39Var.add(effect39);
                addEffects((Parameter<?>) effect39Var, effectsForeffect39Var);
            }

            public void init_17_0_2_2_edc0357_1357846886977_933314_20293Dependencies() {
                addDependency(addToDecider__17_0_2_2_edc0357_1357846886978_453209_20295, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886976_56779_20292_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886976_56779_20292_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(_17_0_2_2_edc0357_1357846887435_175801_21370, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846886979_400240_20298, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(myDeciderID_decider_17_0_2_2_edc0357_1357846886978_453209_20295, new Expression<Integer>(1));
                addDependency(_17_0_2_2_edc0357_1357846887435_802408_21369, new Expression(new EffectFunction(null, ClassUtils.getMethodForArgTypes("TimeVaryingPlottableMap<Integer>", "PowerSystem3", "getValue", java.lang.Integer.class), new Object[] { new Expression<Integer>(startTime) }, new FunctionCall(_17_0_2_2_edc0357_1357846887435_175801_21370, Parameter.class, "getMember", new Object[] { "usage__17_0_2_2_edc0357_1357846886595_378353_19885" }))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(_17_0_2_2_edc0357_1357846886978_453209_20295_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886976_56779_20292_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886976_56779_20292_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846886978_453209_20295, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846886978_453209_20295, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846886978_453209_20295, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_2_edc0357_1357846886978_453209_20295)))))))));
            }

            public void init_17_0_2_2_edc0357_1357846886977_933314_20293Elaborations() {
                init_17_0_2_2_edc0357_1357846886977_933314_20293Dependencies();
                Expression<?>[] arguments40 = new Expression<?>[1];
                arguments40[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition40 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846886978_453209_20295_exists);
                elaborationRule40 = addElaborationRule(condition40, _17_0_2_2_edc0357_1357846886589_155109_19879.this, Customer._17_0_2_2_edc0357_1357846886589_155109_19879._17_0_2_2_edc0357_1357846886978_453209_20295.class, "CUse_sendmeter_SendSignalAction_readMeter", arguments40);
            }
        }

        public class _17_0_2_2_edc0357_1357846886978_946332_20294 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846886978_946332_20294() {
                super();
                init_17_0_2_2_edc0357_1357846886978_946332_20294Members();
                init_17_0_2_2_edc0357_1357846886978_946332_20294Collections();
                init_17_0_2_2_edc0357_1357846886978_946332_20294Elaborations();
            }

            public _17_0_2_2_edc0357_1357846886978_946332_20294(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846886978_946332_20294Members();
                init_17_0_2_2_edc0357_1357846886978_946332_20294Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846886978_946332_20294Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_2_edc0357_1357846886977_933314_20293_exists = null;

            public BooleanParameter addToDecider__17_0_2_2_edc0357_1357846886978_453209_20295 = null;

            public IntegerParameter myDeciderID_decider_17_0_2_2_edc0357_1357846886978_453209_20295 = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846886978_453209_20295_exists = null;

            public Parameter< Customer > objectToPass = null;

            public Dependency _17_0_2_2_edc0357_1357846886977_933314_20293_existsDependency = null;

            public Dependency addToDecider__17_0_2_2_edc0357_1357846886978_453209_20295Dependency = null;

            public Dependency myDeciderID_decider_17_0_2_2_edc0357_1357846886978_453209_20295Dependency = null;

            public Dependency durationDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846886978_453209_20295_existsDependency = null;

            public Dependency objectToPassDependency = null;

            public Effect effect41 = null;

            public Parameter effect41Var = null;

            public Effect effect42 = null;

            public Parameter effect42Var = null;

            public Effect effect43 = null;

            public Parameter effect43Var = null;

            public ElaborationRule elaborationRule44 = null;

            public ElaborationRule elaborationRule45 = null;

            public void init_17_0_2_2_edc0357_1357846886978_946332_20294Members() {
                try {
                    if (_17_0_2_2_edc0357_1357846886977_933314_20293_exists == null) _17_0_2_2_edc0357_1357846886977_933314_20293_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846886977_933314_20293_exists", (Boolean) false, this);
                    if (addToDecider__17_0_2_2_edc0357_1357846886978_453209_20295 == null) addToDecider__17_0_2_2_edc0357_1357846886978_453209_20295 = new BooleanParameter("addToDecider__17_0_2_2_edc0357_1357846886978_453209_20295", (Boolean) false, this);
                    if (myDeciderID_decider_17_0_2_2_edc0357_1357846886978_453209_20295 == null) myDeciderID_decider_17_0_2_2_edc0357_1357846886978_453209_20295 = new IntegerParameter("myDeciderID_decider_17_0_2_2_edc0357_1357846886978_453209_20295", (Integer) 2, this);
                    if (_17_0_2_2_edc0357_1357846886978_453209_20295_exists == null) _17_0_2_2_edc0357_1357846886978_453209_20295_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846886978_453209_20295_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new Parameter<Customer>("objectToPass", null, (Customer) null, this);
                    Object effect41VarV = sig_17_0_2_2_edc0357_1357846886979_400240_20298;
                    effect41Var = new Parameter("effect41Var", null, null, this);
                    addDependency(effect41Var, new Expression(effect41VarV));
                    effect41 = new EffectFunction(new EffectFunction(effect41Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect42VarV = sig_17_0_2_2_edc0357_1357846886979_706245_20299;
                    effect42Var = new Parameter("effect42Var", null, null, this);
                    addDependency(effect42Var, new Expression(effect42VarV));
                    effect42 = new EffectFunction(new EffectFunction(effect42Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect43VarV = decider_17_0_2_2_edc0357_1357846886978_453209_20295;
                    effect43Var = new Parameter("effect43Var", null, null, this);
                    addDependency(effect43Var, new Expression(effect43VarV));
                    effect43 = new EffectFunction(new EffectFunction(effect43Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class, java.lang.Boolean.class), new Object[] { endTime, myDeciderID_decider_17_0_2_2_edc0357_1357846886978_453209_20295, addToDecider__17_0_2_2_edc0357_1357846886978_453209_20295 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846886978_946332_20294Collections() {
                parameters.add(_17_0_2_2_edc0357_1357846886977_933314_20293_exists);
                parameters.add(addToDecider__17_0_2_2_edc0357_1357846886978_453209_20295);
                parameters.add(myDeciderID_decider_17_0_2_2_edc0357_1357846886978_453209_20295);
                parameters.add(_17_0_2_2_edc0357_1357846886978_453209_20295_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect41Var = new TreeSet<Effect>();
                effectsForeffect41Var.add(effect41);
                addEffects((Parameter<?>) effect41Var, effectsForeffect41Var);
                Set<Effect> effectsForeffect42Var = new TreeSet<Effect>();
                effectsForeffect42Var.add(effect42);
                addEffects((Parameter<?>) effect42Var, effectsForeffect42Var);
                Set<Effect> effectsForeffect43Var = new TreeSet<Effect>();
                effectsForeffect43Var.add(effect43);
                addEffects((Parameter<?>) effect43Var, effectsForeffect43Var);
            }

            public void init_17_0_2_2_edc0357_1357846886978_946332_20294Dependencies() {
                addDependency(_17_0_2_2_edc0357_1357846886977_933314_20293_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886976_56779_20292_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886976_56779_20292_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(addToDecider__17_0_2_2_edc0357_1357846886978_453209_20295, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886976_56779_20292_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886976_56779_20292_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(myDeciderID_decider_17_0_2_2_edc0357_1357846886978_453209_20295, new Expression<Integer>(2));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(_17_0_2_2_edc0357_1357846886978_453209_20295_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886976_56779_20292_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886976_56779_20292_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846886978_453209_20295, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846886978_453209_20295, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846886978_453209_20295, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_2_edc0357_1357846886978_453209_20295)))))))));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846886979_771050_20297, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_2_edc0357_1357846886978_946332_20294Elaborations() {
                init_17_0_2_2_edc0357_1357846886978_946332_20294Dependencies();
                Expression<?>[] arguments44 = new Expression<?>[1];
                arguments44[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition44 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846886978_453209_20295_exists);
                elaborationRule44 = addElaborationRule(condition44, _17_0_2_2_edc0357_1357846886589_155109_19879.this, Customer._17_0_2_2_edc0357_1357846886589_155109_19879._17_0_2_2_edc0357_1357846886978_453209_20295.class, "CUse_sendmeter_SendSignalAction_readMeter", arguments44);
                Expression<?>[] arguments45 = new Expression<?>[1];
                arguments45[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition45 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846886977_933314_20293_exists);
                elaborationRule45 = addElaborationRule(condition45, _17_0_2_2_edc0357_1357846886589_155109_19879.this, Customer._17_0_2_2_edc0357_1357846886589_155109_19879._17_0_2_2_edc0357_1357846886977_933314_20293.class, "CUse_readusageparam_ReadStructuralFeatureAction_readMeter", arguments45);
            }
        }

        public class _17_0_2_2_edc0357_1357846886978_453209_20295 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846886978_453209_20295() {
                super();
                init_17_0_2_2_edc0357_1357846886978_453209_20295Members();
                init_17_0_2_2_edc0357_1357846886978_453209_20295Collections();
                init_17_0_2_2_edc0357_1357846886978_453209_20295Elaborations();
            }

            public _17_0_2_2_edc0357_1357846886978_453209_20295(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846886978_453209_20295Members();
                init_17_0_2_2_edc0357_1357846886978_453209_20295Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846886978_453209_20295Elaborations();
                fixTimeDependencies();
            }

            public IntegerParameter _17_0_2_2_edc0357_1357846887436_685244_21372 = null;

            public Parameter< Customer > _17_0_2_2_edc0357_1357846887436_374069_21371 = null;

            public BooleanParameter objectToPass = null;

            public Parameter< Power_System.SignalreceiveMeterReading > signalObject = null;

            public Dependency _17_0_2_2_edc0357_1357846887436_685244_21372Dependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887436_374069_21371Dependency = null;

            public Dependency finalNode_startTimeDependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Dependency signalObjectDependency = null;

            public Effect effect46 = null;

            public Parameter effect46Var = null;

            public Effect effect47 = null;

            public Parameter effect47Var = null;

            public Effect effect48 = null;

            public Parameter effect48Var = null;

            public void init_17_0_2_2_edc0357_1357846886978_453209_20295Members() {
                try {
                    if (_17_0_2_2_edc0357_1357846887436_685244_21372 == null) _17_0_2_2_edc0357_1357846887436_685244_21372 = new IntegerParameter("_17_0_2_2_edc0357_1357846887436_685244_21372", (Integer) null, this);
                    if (_17_0_2_2_edc0357_1357846887436_374069_21371 == null) _17_0_2_2_edc0357_1357846887436_374069_21371 = new Parameter<Customer>("_17_0_2_2_edc0357_1357846887436_374069_21371", null, (Customer) null, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (signalObject == null) signalObject = new Parameter<Power_System.SignalreceiveMeterReading>("signalObject", null, (Power_System.SignalreceiveMeterReading) null, this);
                    Object effect46VarV = sig_17_0_2_2_edc0357_1357846886980_561499_20301;
                    effect46Var = new Parameter("effect46Var", null, null, this);
                    addDependency(effect46Var, new Expression(effect46VarV));
                    effect46 = new EffectFunction(new EffectFunction(effect46Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect47VarV = new FunctionCall(x, Parameter.class, "getMember", new Object[] { "ss_17_0_2_2_edc0357_1357846886597_585102_19889_receiveMeterReading" });
                    effect47Var = new Parameter("effect47Var", null, null, this);
                    addDependency(effect47Var, new Expression(effect47VarV));
                    effect47 = new EffectFunction(new EffectFunction(null, ClassUtils.getMethodForArgTypes("ObjectFlow<SignalreceiveMeterReading>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { signalObject, endTime }, effect47Var));
                    Object effect48VarV = new FunctionCall(signalObject, Parameter.class, "getMember", new Object[] { "meter_value__17_0_2_2_edc0357_1357846886650_242616_19960" });
                    effect48Var = new Parameter("effect48Var", null, null, this);
                    addDependency(effect48Var, new Expression(effect48VarV));
                    effect48 = new EffectFunction(new EffectFunction(null, ClassUtils.getMethodForArgTypes("TimeVaryingMap<Integer>", "PowerSystem3", "setValue", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, _17_0_2_2_edc0357_1357846887436_685244_21372 }, effect48Var));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846886978_453209_20295Collections() {
                parameters.add(_17_0_2_2_edc0357_1357846887436_685244_21372);
                parameters.add(_17_0_2_2_edc0357_1357846887436_374069_21371);
                parameters.add(objectToPass);
                parameters.add(signalObject);
                Set<Effect> effectsForeffect46Var = new TreeSet<Effect>();
                effectsForeffect46Var.add(effect46);
                addEffects((Parameter<?>) effect46Var, effectsForeffect46Var);
                Set<Effect> effectsForeffect47Var = new TreeSet<Effect>();
                effectsForeffect47Var.add(effect47);
                addEffects((Parameter<?>) effect47Var, effectsForeffect47Var);
                Set<Effect> effectsForeffect48Var = new TreeSet<Effect>();
                effectsForeffect48Var.add(effect48);
                addEffects((Parameter<?>) effect48Var, effectsForeffect48Var);
            }

            public void init_17_0_2_2_edc0357_1357846886978_453209_20295Dependencies() {
                addDependency(_17_0_2_2_edc0357_1357846887436_685244_21372, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846886980_99191_20300, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_2_edc0357_1357846887436_374069_21371, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846886979_706245_20299, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(finalNode_startTime, new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(true));
                addDependency(signalObject, new Expression<Power_System.SignalreceiveMeterReading>(new ConstructorCall(x, ClassUtils.getConstructorForArgTypes(Power_System.SignalreceiveMeterReading.class), new Object[] {})));
            }

            public void init_17_0_2_2_edc0357_1357846886978_453209_20295Elaborations() {
                init_17_0_2_2_edc0357_1357846886978_453209_20295Dependencies();
            }
        }

        public _17_0_2_2_edc0357_1357846886589_155109_19879(Expression<Integer> startTime, Expression<DurativeEvent> caller) {
            super();
            init_17_0_2_2_edc0357_1357846886589_155109_19879Members();
            init_17_0_2_2_edc0357_1357846886589_155109_19879Collections();
            addDependency(this.startTime, startTime);
            addDependency(this.caller, caller);
            init_17_0_2_2_edc0357_1357846886589_155109_19879Elaborations();
            fixTimeDependencies();
        }

        public Parameter< ObjectFlow<Integer> > sig_17_0_2_2_edc0357_1357846886980_99191_20300 = null;

        public Parameter< DurativeEvent > caller = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_2_edc0357_1357846886980_561499_20301 = null;

        public Parameter< ObjectFlow<Customer> > sig_17_0_2_2_edc0357_1357846886979_771050_20297 = null;

        public Parameter< ObjectFlow<Customer> > sig_17_0_2_2_edc0357_1357846886979_706245_20299 = null;

        public IntegerParameter finalNode_startTime = null;

        public Parameter< ObjectFlow<Customer> > sig_17_0_2_2_edc0357_1357846886979_400240_20298 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_2_edc0357_1357846886979_672158_20296 = null;

        public Parameter< TimeVaryingList<Integer> > decider_17_0_2_2_edc0357_1357846886978_453209_20295 = null;

        public IntegerParameter finalNode_endTime = null;

        public BooleanParameter _17_0_2_2_edc0357_1357846886976_56779_20292_exists = null;

        public Dependency caller_endTimeDependency = null;

        public Dependency endTimeDependency = null;

        public Dependency _17_0_2_2_edc0357_1357846886976_56779_20292_existsDependency = null;

        public ElaborationRule elaborationRule32 = null;

        public ElaborationRule elaborationRule33 = null;

        public void init_17_0_2_2_edc0357_1357846886589_155109_19879Members() {
            try {
                if (sig_17_0_2_2_edc0357_1357846886980_99191_20300 == null) sig_17_0_2_2_edc0357_1357846886980_99191_20300 = new Parameter<ObjectFlow<Integer>>("sig_17_0_2_2_edc0357_1357846886980_99191_20300", null, (ObjectFlow<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846886980_99191_20300" })).evaluate(true), this);
                if (caller == null) caller = new Parameter<DurativeEvent>("caller", null, (DurativeEvent) null, this);
                if (sig_17_0_2_2_edc0357_1357846886980_561499_20301 == null) sig_17_0_2_2_edc0357_1357846886980_561499_20301 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_2_edc0357_1357846886980_561499_20301", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846886980_561499_20301" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846886979_771050_20297 == null) sig_17_0_2_2_edc0357_1357846886979_771050_20297 = new Parameter<ObjectFlow<Customer>>("sig_17_0_2_2_edc0357_1357846886979_771050_20297", null, (ObjectFlow<Customer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846886979_771050_20297" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846886979_706245_20299 == null) sig_17_0_2_2_edc0357_1357846886979_706245_20299 = new Parameter<ObjectFlow<Customer>>("sig_17_0_2_2_edc0357_1357846886979_706245_20299", null, (ObjectFlow<Customer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846886979_706245_20299" })).evaluate(true), this);
                if (finalNode_startTime == null) finalNode_startTime = new IntegerParameter("finalNode_startTime", (Integer) null, this);
                if (sig_17_0_2_2_edc0357_1357846886979_400240_20298 == null) sig_17_0_2_2_edc0357_1357846886979_400240_20298 = new Parameter<ObjectFlow<Customer>>("sig_17_0_2_2_edc0357_1357846886979_400240_20298", null, (ObjectFlow<Customer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846886979_400240_20298" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846886979_672158_20296 == null) sig_17_0_2_2_edc0357_1357846886979_672158_20296 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_2_edc0357_1357846886979_672158_20296", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846886979_672158_20296" })).evaluate(true), this);
                if (decider_17_0_2_2_edc0357_1357846886978_453209_20295 == null) decider_17_0_2_2_edc0357_1357846886978_453209_20295 = new Parameter<TimeVaryingList<Integer>>("decider_17_0_2_2_edc0357_1357846886978_453209_20295", null, (TimeVaryingList<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingList.class, java.lang.String.class, int.class), new Object[] { "decider_17_0_2_2_edc0357_1357846886978_453209_20295", 2 })).evaluate(true), this);
                if (finalNode_endTime == null) finalNode_endTime = new IntegerParameter("finalNode_endTime", (Integer) null, this);
                if (_17_0_2_2_edc0357_1357846886976_56779_20292_exists == null) _17_0_2_2_edc0357_1357846886976_56779_20292_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846886976_56779_20292_exists", (Boolean) false, this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void init_17_0_2_2_edc0357_1357846886589_155109_19879Collections() {
            parameters.add(sig_17_0_2_2_edc0357_1357846886980_99191_20300);
            parameters.add(caller);
            parameters.add(sig_17_0_2_2_edc0357_1357846886980_561499_20301);
            parameters.add(sig_17_0_2_2_edc0357_1357846886979_771050_20297);
            parameters.add(sig_17_0_2_2_edc0357_1357846886979_706245_20299);
            parameters.add(finalNode_startTime);
            parameters.add(sig_17_0_2_2_edc0357_1357846886979_400240_20298);
            parameters.add(sig_17_0_2_2_edc0357_1357846886979_672158_20296);
            parameters.add(decider_17_0_2_2_edc0357_1357846886978_453209_20295);
            parameters.add(finalNode_endTime);
            parameters.add(_17_0_2_2_edc0357_1357846886976_56779_20292_exists);
        }

        public void init_17_0_2_2_edc0357_1357846886589_155109_19879Dependencies() {
            caller.getValue(true).addDependency(caller.getValue(true).endTime, new Expression<Integer>(finalNode_endTime));
            addDependency(endTime, new Expression<Integer>(finalNode_endTime));
            addDependency(_17_0_2_2_edc0357_1357846886976_56779_20292_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.NotEquals(new Expression<Integer>(finalNode_startTime), null)), new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846886980_561499_20301, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(new Functions.Minus(new Expression<Integer>(finalNode_startTime), new Expression<Integer>(1))) })))));
        }

        public void init_17_0_2_2_edc0357_1357846886589_155109_19879Elaborations() {
            init_17_0_2_2_edc0357_1357846886589_155109_19879Dependencies();
            Expression<?>[] arguments32 = new Expression<?>[1];
            arguments32[0] = new Expression<Integer>(startTime);
            Expression<Boolean> condition32 = new Expression<Boolean>(true);
            elaborationRule32 = addElaborationRule(condition32, _17_0_2_2_edc0357_1357846886589_155109_19879.this, Customer._17_0_2_2_edc0357_1357846886589_155109_19879._17_0_2_2_edc0357_1357846886975_449623_20290.class, "CUse_start_InitialNode_readMeter", arguments32);
            Expression<?>[] arguments33 = new Expression<?>[1];
            arguments33[0] = new Expression<Integer>(finalNode_startTime);
            Expression<Boolean> condition33 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846886976_56779_20292_exists);
            elaborationRule33 = addElaborationRule(condition33, _17_0_2_2_edc0357_1357846886589_155109_19879.this, Customer._17_0_2_2_edc0357_1357846886589_155109_19879._17_0_2_2_edc0357_1357846886976_56779_20292.class, "CUse_end_ActivityFinalNode_readMeter", arguments33);
        }
    }

    public class _17_0_2_2_edc0357_1357846886590_580669_19880 extends DurativeEvent {

        public _17_0_2_2_edc0357_1357846886590_580669_19880() {
            super();
            init_17_0_2_2_edc0357_1357846886590_580669_19880Members();
            init_17_0_2_2_edc0357_1357846886590_580669_19880Collections();
            init_17_0_2_2_edc0357_1357846886590_580669_19880Elaborations();
        }

        public class _17_0_2_2_edc0357_1357846886995_216436_20325 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846886995_216436_20325() {
                super();
                init_17_0_2_2_edc0357_1357846886995_216436_20325Members();
                init_17_0_2_2_edc0357_1357846886995_216436_20325Collections();
                init_17_0_2_2_edc0357_1357846886995_216436_20325Elaborations();
            }

            public _17_0_2_2_edc0357_1357846886995_216436_20325(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846886995_216436_20325Members();
                init_17_0_2_2_edc0357_1357846886995_216436_20325Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846886995_216436_20325Elaborations();
                fixTimeDependencies();
            }

            public Parameter< Customer > _17_0_2_2_edc0357_1357846887446_606041_21385 = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846886998_555131_20331_exists = null;

            public BooleanParameter objectToPass = null;

            public Dependency _17_0_2_2_edc0357_1357846886998_555131_20331_existsDependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Effect effect51 = null;

            public Parameter effect51Var = null;

            public ElaborationRule elaborationRule52 = null;

            public void init_17_0_2_2_edc0357_1357846886995_216436_20325Members() {
                try {
                    if (_17_0_2_2_edc0357_1357846887446_606041_21385 == null) _17_0_2_2_edc0357_1357846887446_606041_21385 = new Parameter<Customer>("_17_0_2_2_edc0357_1357846887446_606041_21385", null, (Customer) Customer.this, this);
                    if (_17_0_2_2_edc0357_1357846886998_555131_20331_exists == null) _17_0_2_2_edc0357_1357846886998_555131_20331_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846886998_555131_20331_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect51VarV = sig_17_0_2_2_edc0357_1357846887004_211618_20341;
                    effect51Var = new Parameter("effect51Var", null, null, this);
                    addDependency(effect51Var, new Expression(effect51VarV));
                    effect51 = new EffectFunction(new EffectFunction(effect51Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { _17_0_2_2_edc0357_1357846887446_606041_21385, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846886995_216436_20325Collections() {
                parameters.add(_17_0_2_2_edc0357_1357846887446_606041_21385);
                parameters.add(_17_0_2_2_edc0357_1357846886998_555131_20331_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect51Var = new TreeSet<Effect>();
                effectsForeffect51Var.add(effect51);
                addEffects((Parameter<?>) effect51Var, effectsForeffect51Var);
            }

            public void init_17_0_2_2_edc0357_1357846886995_216436_20325Dependencies() {
                addDependency(_17_0_2_2_edc0357_1357846886998_555131_20331_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886997_31914_20329_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886997_31914_20329_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887005_653176_20350, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_2_edc0357_1357846886995_216436_20325Elaborations() {
                init_17_0_2_2_edc0357_1357846886995_216436_20325Dependencies();
                Expression<?>[] arguments52 = new Expression<?>[1];
                arguments52[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition52 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846886998_555131_20331_exists);
                elaborationRule52 = addElaborationRule(condition52, _17_0_2_2_edc0357_1357846886590_580669_19880.this, Customer._17_0_2_2_edc0357_1357846886590_580669_19880._17_0_2_2_edc0357_1357846886998_555131_20331.class, "CChange_fork2self_ForkNode_changePowerUsage", arguments52);
            }
        }

        public class _17_0_2_2_edc0357_1357846886995_638275_20326 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846886995_638275_20326() {
                super();
                init_17_0_2_2_edc0357_1357846886995_638275_20326Members();
                init_17_0_2_2_edc0357_1357846886995_638275_20326Collections();
                init_17_0_2_2_edc0357_1357846886995_638275_20326Elaborations();
            }

            public _17_0_2_2_edc0357_1357846886995_638275_20326(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846886995_638275_20326Members();
                init_17_0_2_2_edc0357_1357846886995_638275_20326Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846886995_638275_20326Elaborations();
                fixTimeDependencies();
            }

            public IntegerParameter _17_0_2_2_edc0357_1357846887447_801565_21387 = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846887003_521609_20339_exists = null;

            public BooleanParameter objectToPass = null;

            public Dependency _17_0_2_2_edc0357_1357846887447_801565_21387Dependency = null;

            public Dependency durationDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887003_521609_20339_existsDependency = null;

            public Dependency objectToPassDependency = null;

            public Effect effect53 = null;

            public Parameter effect53Var = null;

            public ElaborationRule elaborationRule54 = null;

            public void init_17_0_2_2_edc0357_1357846886995_638275_20326Members() {
                try {
                    if (_17_0_2_2_edc0357_1357846887447_801565_21387 == null) _17_0_2_2_edc0357_1357846887447_801565_21387 = new IntegerParameter("_17_0_2_2_edc0357_1357846887447_801565_21387", (Integer) 15, this);
                    if (_17_0_2_2_edc0357_1357846887003_521609_20339_exists == null) _17_0_2_2_edc0357_1357846887003_521609_20339_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887003_521609_20339_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect53VarV = sig_17_0_2_2_edc0357_1357846887006_611151_20358;
                    effect53Var = new Parameter("effect53Var", null, null, this);
                    addDependency(effect53Var, new Expression(effect53VarV));
                    effect53 = new EffectFunction(new EffectFunction(effect53Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { _17_0_2_2_edc0357_1357846887447_801565_21387, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846886995_638275_20326Collections() {
                parameters.add(_17_0_2_2_edc0357_1357846887447_801565_21387);
                parameters.add(_17_0_2_2_edc0357_1357846887003_521609_20339_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect53Var = new TreeSet<Effect>();
                effectsForeffect53Var.add(effect53);
                addEffects((Parameter<?>) effect53Var, effectsForeffect53Var);
            }

            public void init_17_0_2_2_edc0357_1357846886995_638275_20326Dependencies() {
                addDependency(_17_0_2_2_edc0357_1357846887447_801565_21387, new Expression<Integer>(15));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(_17_0_2_2_edc0357_1357846887003_521609_20339_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886997_31914_20329_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886997_31914_20329_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887005_721935_20351, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_2_edc0357_1357846886995_638275_20326Elaborations() {
                init_17_0_2_2_edc0357_1357846886995_638275_20326Dependencies();
                Expression<?>[] arguments54 = new Expression<?>[1];
                arguments54[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition54 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887003_521609_20339_exists);
                elaborationRule54 = addElaborationRule(condition54, _17_0_2_2_edc0357_1357846886590_580669_19880.this, Customer._17_0_2_2_edc0357_1357846886590_580669_19880._17_0_2_2_edc0357_1357846887003_521609_20339.class, "CChange_fork4changeval_ForkNode_changePowerUsage", arguments54);
            }
        }

        public class _17_0_2_2_edc0357_1357846886996_671670_20327 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846886996_671670_20327() {
                super();
                init_17_0_2_2_edc0357_1357846886996_671670_20327Members();
                init_17_0_2_2_edc0357_1357846886996_671670_20327Collections();
                init_17_0_2_2_edc0357_1357846886996_671670_20327Elaborations();
            }

            public _17_0_2_2_edc0357_1357846886996_671670_20327(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846886996_671670_20327Members();
                init_17_0_2_2_edc0357_1357846886996_671670_20327Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846886996_671670_20327Elaborations();
                fixTimeDependencies();
            }

            public IntegerParameter myDeciderID_decider_17_0_2_2_edc0357_1357846886999_888577_20333 = null;

            public BooleanParameter addToDecider__17_0_2_2_edc0357_1357846886999_888577_20333 = null;

            public IntegerParameter _17_0_2_2_edc0357_1357846887447_183508_21388 = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846886999_888577_20333_exists = null;

            public Parameter< Customer > _17_0_2_2_edc0357_1357846887448_871153_21389 = null;

            public BooleanParameter objectToPass = null;

            public Dependency myDeciderID_decider_17_0_2_2_edc0357_1357846886999_888577_20333Dependency = null;

            public Dependency addToDecider__17_0_2_2_edc0357_1357846886999_888577_20333Dependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887447_183508_21388Dependency = null;

            public Dependency _17_0_2_2_edc0357_1357846886999_888577_20333_existsDependency = null;

            public Dependency durationDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887448_871153_21389Dependency = null;

            public Dependency objectToPassDependency = null;

            public Effect effect55 = null;

            public Parameter effect55Var = null;

            public Effect effect56 = null;

            public Parameter effect56Var = null;

            public Effect effect57 = null;

            public Parameter effect57Var = null;

            public ElaborationRule elaborationRule58 = null;

            public void init_17_0_2_2_edc0357_1357846886996_671670_20327Members() {
                try {
                    if (myDeciderID_decider_17_0_2_2_edc0357_1357846886999_888577_20333 == null) myDeciderID_decider_17_0_2_2_edc0357_1357846886999_888577_20333 = new IntegerParameter("myDeciderID_decider_17_0_2_2_edc0357_1357846886999_888577_20333", (Integer) 3, this);
                    if (addToDecider__17_0_2_2_edc0357_1357846886999_888577_20333 == null) addToDecider__17_0_2_2_edc0357_1357846886999_888577_20333 = new BooleanParameter("addToDecider__17_0_2_2_edc0357_1357846886999_888577_20333", (Boolean) false, this);
                    if (_17_0_2_2_edc0357_1357846887447_183508_21388 == null) _17_0_2_2_edc0357_1357846887447_183508_21388 = new IntegerParameter("_17_0_2_2_edc0357_1357846887447_183508_21388", (Integer) null, this);
                    if (_17_0_2_2_edc0357_1357846886999_888577_20333_exists == null) _17_0_2_2_edc0357_1357846886999_888577_20333_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846886999_888577_20333_exists", (Boolean) false, this);
                    if (_17_0_2_2_edc0357_1357846887448_871153_21389 == null) _17_0_2_2_edc0357_1357846887448_871153_21389 = new Parameter<Customer>("_17_0_2_2_edc0357_1357846887448_871153_21389", null, (Customer) null, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect55VarV = sig_17_0_2_2_edc0357_1357846887005_288480_20348;
                    effect55Var = new Parameter("effect55Var", null, null, this);
                    addDependency(effect55Var, new Expression(effect55VarV));
                    effect55 = new EffectFunction(new EffectFunction(effect55Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect56VarV = decider_17_0_2_2_edc0357_1357846886999_888577_20333;
                    effect56Var = new Parameter("effect56Var", null, null, this);
                    addDependency(effect56Var, new Expression(effect56VarV));
                    effect56 = new EffectFunction(new EffectFunction(effect56Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class, java.lang.Boolean.class), new Object[] { endTime, myDeciderID_decider_17_0_2_2_edc0357_1357846886999_888577_20333, addToDecider__17_0_2_2_edc0357_1357846886999_888577_20333 }));
                    Object effect57VarV = usage__17_0_2_2_edc0357_1357846886595_378353_19885;
                    effect57Var = new Parameter("effect57Var", null, null, this);
                    addDependency(effect57Var, new Expression(effect57VarV));
                    effect57 = new EffectFunction(new EffectFunction(effect57Var, ClassUtils.getMethodForArgTypes("TimeVaryingPlottableMap<Integer>", "PowerSystem3", "setValue", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { startTime, _17_0_2_2_edc0357_1357846887447_183508_21388 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846886996_671670_20327Collections() {
                parameters.add(myDeciderID_decider_17_0_2_2_edc0357_1357846886999_888577_20333);
                parameters.add(addToDecider__17_0_2_2_edc0357_1357846886999_888577_20333);
                parameters.add(_17_0_2_2_edc0357_1357846887447_183508_21388);
                parameters.add(_17_0_2_2_edc0357_1357846886999_888577_20333_exists);
                parameters.add(_17_0_2_2_edc0357_1357846887448_871153_21389);
                parameters.add(objectToPass);
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

            public void init_17_0_2_2_edc0357_1357846886996_671670_20327Dependencies() {
                addDependency(myDeciderID_decider_17_0_2_2_edc0357_1357846886999_888577_20333, new Expression<Integer>(3));
                addDependency(addToDecider__17_0_2_2_edc0357_1357846886999_888577_20333, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886997_31914_20329_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886997_31914_20329_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(_17_0_2_2_edc0357_1357846887447_183508_21388, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887004_499762_20346, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_2_edc0357_1357846886999_888577_20333_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886997_31914_20329_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886997_31914_20329_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846886999_888577_20333, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846886999_888577_20333, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846886999_888577_20333, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_2_edc0357_1357846886999_888577_20333)))))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(_17_0_2_2_edc0357_1357846887448_871153_21389, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887004_581837_20342, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(objectToPass, new Expression<Boolean>(true));
            }

            public void init_17_0_2_2_edc0357_1357846886996_671670_20327Elaborations() {
                init_17_0_2_2_edc0357_1357846886996_671670_20327Dependencies();
                Expression<?>[] arguments58 = new Expression<?>[1];
                arguments58[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition58 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846886999_888577_20333_exists);
                elaborationRule58 = addElaborationRule(condition58, _17_0_2_2_edc0357_1357846886590_580669_19880.this, Customer._17_0_2_2_edc0357_1357846886590_580669_19880._17_0_2_2_edc0357_1357846886999_888577_20333.class, "CChange_sendnewusage_SendSignalAction_changePowerUsage", arguments58);
            }
        }

        public class _17_0_2_2_edc0357_1357846886997_606321_20328 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846886997_606321_20328() {
                super();
                init_17_0_2_2_edc0357_1357846886997_606321_20328Members();
                init_17_0_2_2_edc0357_1357846886997_606321_20328Collections();
                init_17_0_2_2_edc0357_1357846886997_606321_20328Elaborations();
            }

            public _17_0_2_2_edc0357_1357846886997_606321_20328(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846886997_606321_20328Members();
                init_17_0_2_2_edc0357_1357846886997_606321_20328Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846886997_606321_20328Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter objectToPass = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846887000_232381_20335_exists = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887000_232381_20335_existsDependency = null;

            public Effect effect59 = null;

            public Parameter effect59Var = null;

            public ElaborationRule elaborationRule60 = null;

            public void init_17_0_2_2_edc0357_1357846886997_606321_20328Members() {
                try {
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (_17_0_2_2_edc0357_1357846887000_232381_20335_exists == null) _17_0_2_2_edc0357_1357846887000_232381_20335_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887000_232381_20335_exists", (Boolean) false, this);
                    Object effect59VarV = sig_17_0_2_2_edc0357_1357846887005_830787_20349;
                    effect59Var = new Parameter("effect59Var", null, null, this);
                    addDependency(effect59Var, new Expression(effect59VarV));
                    effect59 = new EffectFunction(new EffectFunction(effect59Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846886997_606321_20328Collections() {
                parameters.add(objectToPass);
                parameters.add(_17_0_2_2_edc0357_1357846887000_232381_20335_exists);
                Set<Effect> effectsForeffect59Var = new TreeSet<Effect>();
                effectsForeffect59Var.add(effect59);
                addEffects((Parameter<?>) effect59Var, effectsForeffect59Var);
            }

            public void init_17_0_2_2_edc0357_1357846886997_606321_20328Dependencies() {
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(true));
                addDependency(_17_0_2_2_edc0357_1357846887000_232381_20335_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886997_31914_20329_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886997_31914_20329_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
            }

            public void init_17_0_2_2_edc0357_1357846886997_606321_20328Elaborations() {
                init_17_0_2_2_edc0357_1357846886997_606321_20328Dependencies();
                Expression<?>[] arguments60 = new Expression<?>[1];
                arguments60[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition60 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887000_232381_20335_exists);
                elaborationRule60 = addElaborationRule(condition60, _17_0_2_2_edc0357_1357846886590_580669_19880.this, Customer._17_0_2_2_edc0357_1357846886590_580669_19880._17_0_2_2_edc0357_1357846887000_232381_20335.class, "CChange_fork1_ForkNode_changePowerUsage", arguments60);
            }
        }

        public class _17_0_2_2_edc0357_1357846886997_31914_20329 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846886997_31914_20329() {
                super();
                init_17_0_2_2_edc0357_1357846886997_31914_20329Members();
                init_17_0_2_2_edc0357_1357846886997_31914_20329Collections();
                init_17_0_2_2_edc0357_1357846886997_31914_20329Elaborations();
            }

            public _17_0_2_2_edc0357_1357846886997_31914_20329(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846886997_31914_20329Members();
                init_17_0_2_2_edc0357_1357846886997_31914_20329Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846886997_31914_20329Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter objectToPass = null;

            public Dependency endTimeDependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Dependency finalNode_endTimeDependency = null;

            public void init_17_0_2_2_edc0357_1357846886997_31914_20329Members() {
                try {
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846886997_31914_20329Collections() {
                parameters.add(objectToPass);
            }

            public void init_17_0_2_2_edc0357_1357846886997_31914_20329Dependencies() {
                addDependency(endTime, new Expression<Integer>(new Functions.Plus(new Expression<Integer>(startTime), new Expression<Integer>(duration))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887004_40735_20345, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(finalNode_endTime, new Expression<Integer>(endTime));
            }

            public void init_17_0_2_2_edc0357_1357846886997_31914_20329Elaborations() {
                init_17_0_2_2_edc0357_1357846886997_31914_20329Dependencies();
            }
        }

        public class _17_0_2_2_edc0357_1357846886998_45707_20330 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846886998_45707_20330() {
                super();
                init_17_0_2_2_edc0357_1357846886998_45707_20330Members();
                init_17_0_2_2_edc0357_1357846886998_45707_20330Collections();
                init_17_0_2_2_edc0357_1357846886998_45707_20330Elaborations();
            }

            public _17_0_2_2_edc0357_1357846886998_45707_20330(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846886998_45707_20330Members();
                init_17_0_2_2_edc0357_1357846886998_45707_20330Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846886998_45707_20330Elaborations();
                fixTimeDependencies();
            }

            public Parameter< Customer > _17_0_2_2_edc0357_1357846887449_268353_21391 = null;

            public IntegerParameter myDeciderID_decider_17_0_2_2_edc0357_1357846887002_996884_20338 = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846887002_818297_20337_exists = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846887002_996884_20338_exists = null;

            public BooleanParameter objectToPass = null;

            public BooleanParameter addToDecider__17_0_2_2_edc0357_1357846887002_996884_20338 = null;

            public IntegerParameter _17_0_2_2_edc0357_1357846887449_213307_21390 = null;

            public Dependency _17_0_2_2_edc0357_1357846887449_268353_21391Dependency = null;

            public Dependency myDeciderID_decider_17_0_2_2_edc0357_1357846887002_996884_20338Dependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887002_818297_20337_existsDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887002_996884_20338_existsDependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Dependency addToDecider__17_0_2_2_edc0357_1357846887002_996884_20338Dependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887449_213307_21390Dependency = null;

            public Effect effect61 = null;

            public Parameter effect61Var = null;

            public Effect effect62 = null;

            public Parameter effect62Var = null;

            public Effect effect63 = null;

            public Parameter effect63Var = null;

            public ElaborationRule elaborationRule64 = null;

            public ElaborationRule elaborationRule65 = null;

            public void init_17_0_2_2_edc0357_1357846886998_45707_20330Members() {
                try {
                    if (_17_0_2_2_edc0357_1357846887449_268353_21391 == null) _17_0_2_2_edc0357_1357846887449_268353_21391 = new Parameter<Customer>("_17_0_2_2_edc0357_1357846887449_268353_21391", null, (Customer) null, this);
                    if (myDeciderID_decider_17_0_2_2_edc0357_1357846887002_996884_20338 == null) myDeciderID_decider_17_0_2_2_edc0357_1357846887002_996884_20338 = new IntegerParameter("myDeciderID_decider_17_0_2_2_edc0357_1357846887002_996884_20338", (Integer) 1, this);
                    if (_17_0_2_2_edc0357_1357846887002_818297_20337_exists == null) _17_0_2_2_edc0357_1357846887002_818297_20337_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887002_818297_20337_exists", (Boolean) false, this);
                    if (_17_0_2_2_edc0357_1357846887002_996884_20338_exists == null) _17_0_2_2_edc0357_1357846887002_996884_20338_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887002_996884_20338_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (addToDecider__17_0_2_2_edc0357_1357846887002_996884_20338 == null) addToDecider__17_0_2_2_edc0357_1357846887002_996884_20338 = new BooleanParameter("addToDecider__17_0_2_2_edc0357_1357846887002_996884_20338", (Boolean) false, this);
                    if (_17_0_2_2_edc0357_1357846887449_213307_21390 == null) _17_0_2_2_edc0357_1357846887449_213307_21390 = new IntegerParameter("_17_0_2_2_edc0357_1357846887449_213307_21390", (Integer) null, this);
                    Object effect61VarV = sig_17_0_2_2_edc0357_1357846887005_951410_20353;
                    effect61Var = new Parameter("effect61Var", null, null, this);
                    addDependency(effect61Var, new Expression(effect61VarV));
                    effect61 = new EffectFunction(new EffectFunction(effect61Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { _17_0_2_2_edc0357_1357846887449_213307_21390, endTime }));
                    Object effect62VarV = sig_17_0_2_2_edc0357_1357846887005_267810_20352;
                    effect62Var = new Parameter("effect62Var", null, null, this);
                    addDependency(effect62Var, new Expression(effect62VarV));
                    effect62 = new EffectFunction(new EffectFunction(effect62Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect63VarV = decider_17_0_2_2_edc0357_1357846887002_996884_20338;
                    effect63Var = new Parameter("effect63Var", null, null, this);
                    addDependency(effect63Var, new Expression(effect63VarV));
                    effect63 = new EffectFunction(new EffectFunction(effect63Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class, java.lang.Boolean.class), new Object[] { endTime, myDeciderID_decider_17_0_2_2_edc0357_1357846887002_996884_20338, addToDecider__17_0_2_2_edc0357_1357846887002_996884_20338 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846886998_45707_20330Collections() {
                parameters.add(_17_0_2_2_edc0357_1357846887449_268353_21391);
                parameters.add(myDeciderID_decider_17_0_2_2_edc0357_1357846887002_996884_20338);
                parameters.add(_17_0_2_2_edc0357_1357846887002_818297_20337_exists);
                parameters.add(_17_0_2_2_edc0357_1357846887002_996884_20338_exists);
                parameters.add(objectToPass);
                parameters.add(addToDecider__17_0_2_2_edc0357_1357846887002_996884_20338);
                parameters.add(_17_0_2_2_edc0357_1357846887449_213307_21390);
                Set<Effect> effectsForeffect61Var = new TreeSet<Effect>();
                effectsForeffect61Var.add(effect61);
                addEffects((Parameter<?>) effect61Var, effectsForeffect61Var);
                Set<Effect> effectsForeffect62Var = new TreeSet<Effect>();
                effectsForeffect62Var.add(effect62);
                addEffects((Parameter<?>) effect62Var, effectsForeffect62Var);
                Set<Effect> effectsForeffect63Var = new TreeSet<Effect>();
                effectsForeffect63Var.add(effect63);
                addEffects((Parameter<?>) effect63Var, effectsForeffect63Var);
            }

            public void init_17_0_2_2_edc0357_1357846886998_45707_20330Dependencies() {
                addDependency(_17_0_2_2_edc0357_1357846887449_268353_21391, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887004_475504_20343, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(myDeciderID_decider_17_0_2_2_edc0357_1357846887002_996884_20338, new Expression<Integer>(1));
                addDependency(_17_0_2_2_edc0357_1357846887002_818297_20337_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886997_31914_20329_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886997_31914_20329_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(_17_0_2_2_edc0357_1357846887002_996884_20338_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886997_31914_20329_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886997_31914_20329_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887002_996884_20338, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887002_996884_20338, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887002_996884_20338, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_2_edc0357_1357846887002_996884_20338)))))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(true));
                addDependency(addToDecider__17_0_2_2_edc0357_1357846887002_996884_20338, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886997_31914_20329_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886997_31914_20329_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(_17_0_2_2_edc0357_1357846887449_213307_21390, new Expression(new EffectFunction(null, ClassUtils.getMethodForArgTypes("TimeVaryingPlottableMap<Integer>", "PowerSystem3", "getValue", java.lang.Integer.class), new Object[] { new Expression<Integer>(startTime) }, new FunctionCall(_17_0_2_2_edc0357_1357846887449_268353_21391, Parameter.class, "getMember", new Object[] { "cap__17_0_2_2_edc0357_1357846886598_990111_19890" }))));
            }

            public void init_17_0_2_2_edc0357_1357846886998_45707_20330Elaborations() {
                init_17_0_2_2_edc0357_1357846886998_45707_20330Dependencies();
                Expression<?>[] arguments64 = new Expression<?>[1];
                arguments64[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition64 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887002_818297_20337_exists);
                elaborationRule64 = addElaborationRule(condition64, _17_0_2_2_edc0357_1357846886590_580669_19880.this, Customer._17_0_2_2_edc0357_1357846886590_580669_19880._17_0_2_2_edc0357_1357846887002_818297_20337.class, "CChange_fork3cap_ForkNode_changePowerUsage", arguments64);
                Expression<?>[] arguments65 = new Expression<?>[1];
                arguments65[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition65 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887002_996884_20338_exists);
                elaborationRule65 = addElaborationRule(condition65, _17_0_2_2_edc0357_1357846886590_580669_19880.this, Customer._17_0_2_2_edc0357_1357846886590_580669_19880._17_0_2_2_edc0357_1357846887002_996884_20338.class, "CChange_decidecap_DecisionNode_changePowerUsage", arguments65);
            }
        }

        public class _17_0_2_2_edc0357_1357846886998_555131_20331 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846886998_555131_20331() {
                super();
                init_17_0_2_2_edc0357_1357846886998_555131_20331Members();
                init_17_0_2_2_edc0357_1357846886998_555131_20331Collections();
                init_17_0_2_2_edc0357_1357846886998_555131_20331Elaborations();
            }

            public _17_0_2_2_edc0357_1357846886998_555131_20331(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846886998_555131_20331Members();
                init_17_0_2_2_edc0357_1357846886998_555131_20331Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846886998_555131_20331Elaborations();
                fixTimeDependencies();
            }

            public IntegerParameter myDeciderID_decider_17_0_2_2_edc0357_1357846886999_888577_20333 = null;

            public BooleanParameter addToDecider__17_0_2_2_edc0357_1357846886999_888577_20333 = null;

            public IntegerParameter myDeciderID_decider_17_0_2_2_edc0357_1357846886996_671670_20327 = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846886999_888577_20333_exists = null;

            public BooleanParameter addToDecider__17_0_2_2_edc0357_1357846886996_671670_20327 = null;

            public Parameter< Customer > objectToPass = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846886996_671670_20327_exists = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846886998_45707_20330_exists = null;

            public Dependency myDeciderID_decider_17_0_2_2_edc0357_1357846886999_888577_20333Dependency = null;

            public Dependency addToDecider__17_0_2_2_edc0357_1357846886999_888577_20333Dependency = null;

            public Dependency myDeciderID_decider_17_0_2_2_edc0357_1357846886996_671670_20327Dependency = null;

            public Dependency _17_0_2_2_edc0357_1357846886999_888577_20333_existsDependency = null;

            public Dependency addToDecider__17_0_2_2_edc0357_1357846886996_671670_20327Dependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846886996_671670_20327_existsDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846886998_45707_20330_existsDependency = null;

            public Effect effect66 = null;

            public Parameter effect66Var = null;

            public Effect effect67 = null;

            public Parameter effect67Var = null;

            public Effect effect68 = null;

            public Parameter effect68Var = null;

            public Effect effect69 = null;

            public Parameter effect69Var = null;

            public Effect effect70 = null;

            public Parameter effect70Var = null;

            public ElaborationRule elaborationRule71 = null;

            public ElaborationRule elaborationRule72 = null;

            public ElaborationRule elaborationRule73 = null;

            public void init_17_0_2_2_edc0357_1357846886998_555131_20331Members() {
                try {
                    if (myDeciderID_decider_17_0_2_2_edc0357_1357846886999_888577_20333 == null) myDeciderID_decider_17_0_2_2_edc0357_1357846886999_888577_20333 = new IntegerParameter("myDeciderID_decider_17_0_2_2_edc0357_1357846886999_888577_20333", (Integer) 2, this);
                    if (addToDecider__17_0_2_2_edc0357_1357846886999_888577_20333 == null) addToDecider__17_0_2_2_edc0357_1357846886999_888577_20333 = new BooleanParameter("addToDecider__17_0_2_2_edc0357_1357846886999_888577_20333", (Boolean) false, this);
                    if (myDeciderID_decider_17_0_2_2_edc0357_1357846886996_671670_20327 == null) myDeciderID_decider_17_0_2_2_edc0357_1357846886996_671670_20327 = new IntegerParameter("myDeciderID_decider_17_0_2_2_edc0357_1357846886996_671670_20327", (Integer) 1, this);
                    if (_17_0_2_2_edc0357_1357846886999_888577_20333_exists == null) _17_0_2_2_edc0357_1357846886999_888577_20333_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846886999_888577_20333_exists", (Boolean) false, this);
                    if (addToDecider__17_0_2_2_edc0357_1357846886996_671670_20327 == null) addToDecider__17_0_2_2_edc0357_1357846886996_671670_20327 = new BooleanParameter("addToDecider__17_0_2_2_edc0357_1357846886996_671670_20327", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new Parameter<Customer>("objectToPass", null, (Customer) null, this);
                    if (_17_0_2_2_edc0357_1357846886996_671670_20327_exists == null) _17_0_2_2_edc0357_1357846886996_671670_20327_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846886996_671670_20327_exists", (Boolean) false, this);
                    if (_17_0_2_2_edc0357_1357846886998_45707_20330_exists == null) _17_0_2_2_edc0357_1357846886998_45707_20330_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846886998_45707_20330_exists", (Boolean) false, this);
                    Object effect66VarV = sig_17_0_2_2_edc0357_1357846887004_581837_20342;
                    effect66Var = new Parameter("effect66Var", null, null, this);
                    addDependency(effect66Var, new Expression(effect66VarV));
                    effect66 = new EffectFunction(new EffectFunction(effect66Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect67VarV = sig_17_0_2_2_edc0357_1357846887004_475504_20343;
                    effect67Var = new Parameter("effect67Var", null, null, this);
                    addDependency(effect67Var, new Expression(effect67VarV));
                    effect67 = new EffectFunction(new EffectFunction(effect67Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect68VarV = sig_17_0_2_2_edc0357_1357846887004_21611_20344;
                    effect68Var = new Parameter("effect68Var", null, null, this);
                    addDependency(effect68Var, new Expression(effect68VarV));
                    effect68 = new EffectFunction(new EffectFunction(effect68Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect69VarV = decider_17_0_2_2_edc0357_1357846886996_671670_20327;
                    effect69Var = new Parameter("effect69Var", null, null, this);
                    addDependency(effect69Var, new Expression(effect69VarV));
                    effect69 = new EffectFunction(new EffectFunction(effect69Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class, java.lang.Boolean.class), new Object[] { endTime, myDeciderID_decider_17_0_2_2_edc0357_1357846886996_671670_20327, addToDecider__17_0_2_2_edc0357_1357846886996_671670_20327 }));
                    Object effect70VarV = decider_17_0_2_2_edc0357_1357846886999_888577_20333;
                    effect70Var = new Parameter("effect70Var", null, null, this);
                    addDependency(effect70Var, new Expression(effect70VarV));
                    effect70 = new EffectFunction(new EffectFunction(effect70Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class, java.lang.Boolean.class), new Object[] { endTime, myDeciderID_decider_17_0_2_2_edc0357_1357846886999_888577_20333, addToDecider__17_0_2_2_edc0357_1357846886999_888577_20333 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846886998_555131_20331Collections() {
                parameters.add(myDeciderID_decider_17_0_2_2_edc0357_1357846886999_888577_20333);
                parameters.add(addToDecider__17_0_2_2_edc0357_1357846886999_888577_20333);
                parameters.add(myDeciderID_decider_17_0_2_2_edc0357_1357846886996_671670_20327);
                parameters.add(_17_0_2_2_edc0357_1357846886999_888577_20333_exists);
                parameters.add(addToDecider__17_0_2_2_edc0357_1357846886996_671670_20327);
                parameters.add(objectToPass);
                parameters.add(_17_0_2_2_edc0357_1357846886996_671670_20327_exists);
                parameters.add(_17_0_2_2_edc0357_1357846886998_45707_20330_exists);
                Set<Effect> effectsForeffect66Var = new TreeSet<Effect>();
                effectsForeffect66Var.add(effect66);
                addEffects((Parameter<?>) effect66Var, effectsForeffect66Var);
                Set<Effect> effectsForeffect67Var = new TreeSet<Effect>();
                effectsForeffect67Var.add(effect67);
                addEffects((Parameter<?>) effect67Var, effectsForeffect67Var);
                Set<Effect> effectsForeffect68Var = new TreeSet<Effect>();
                effectsForeffect68Var.add(effect68);
                addEffects((Parameter<?>) effect68Var, effectsForeffect68Var);
                Set<Effect> effectsForeffect69Var = new TreeSet<Effect>();
                effectsForeffect69Var.add(effect69);
                addEffects((Parameter<?>) effect69Var, effectsForeffect69Var);
                Set<Effect> effectsForeffect70Var = new TreeSet<Effect>();
                effectsForeffect70Var.add(effect70);
                addEffects((Parameter<?>) effect70Var, effectsForeffect70Var);
            }

            public void init_17_0_2_2_edc0357_1357846886998_555131_20331Dependencies() {
                addDependency(myDeciderID_decider_17_0_2_2_edc0357_1357846886999_888577_20333, new Expression<Integer>(2));
                addDependency(addToDecider__17_0_2_2_edc0357_1357846886999_888577_20333, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886997_31914_20329_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886997_31914_20329_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(myDeciderID_decider_17_0_2_2_edc0357_1357846886996_671670_20327, new Expression<Integer>(1));
                addDependency(_17_0_2_2_edc0357_1357846886999_888577_20333_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886997_31914_20329_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886997_31914_20329_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846886999_888577_20333, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846886999_888577_20333, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846886999_888577_20333, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_2_edc0357_1357846886999_888577_20333)))))))));
                addDependency(addToDecider__17_0_2_2_edc0357_1357846886996_671670_20327, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886997_31914_20329_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886997_31914_20329_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887004_211618_20341, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_2_edc0357_1357846886996_671670_20327_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886997_31914_20329_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886997_31914_20329_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846886996_671670_20327, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846886996_671670_20327, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846886996_671670_20327, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_2_edc0357_1357846886996_671670_20327)))))))));
                addDependency(_17_0_2_2_edc0357_1357846886998_45707_20330_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886997_31914_20329_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886997_31914_20329_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
            }

            public void init_17_0_2_2_edc0357_1357846886998_555131_20331Elaborations() {
                init_17_0_2_2_edc0357_1357846886998_555131_20331Dependencies();
                Expression<?>[] arguments71 = new Expression<?>[1];
                arguments71[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition71 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846886996_671670_20327_exists);
                elaborationRule71 = addElaborationRule(condition71, _17_0_2_2_edc0357_1357846886590_580669_19880.this, Customer._17_0_2_2_edc0357_1357846886590_580669_19880._17_0_2_2_edc0357_1357846886996_671670_20327.class, "CChange_setnewusage_AddStructuralFeatureValueAction_changePowerUsage", arguments71);
                Expression<?>[] arguments72 = new Expression<?>[1];
                arguments72[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition72 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846886998_45707_20330_exists);
                elaborationRule72 = addElaborationRule(condition72, _17_0_2_2_edc0357_1357846886590_580669_19880.this, Customer._17_0_2_2_edc0357_1357846886590_580669_19880._17_0_2_2_edc0357_1357846886998_45707_20330.class, "CChange_readcurrentcap_ReadStructuralFeatureAction_changePowerUsage", arguments72);
                Expression<?>[] arguments73 = new Expression<?>[1];
                arguments73[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition73 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846886999_888577_20333_exists);
                elaborationRule73 = addElaborationRule(condition73, _17_0_2_2_edc0357_1357846886590_580669_19880.this, Customer._17_0_2_2_edc0357_1357846886590_580669_19880._17_0_2_2_edc0357_1357846886999_888577_20333.class, "CChange_sendnewusage_SendSignalAction_changePowerUsage", arguments73);
            }
        }

        public class _17_0_2_2_edc0357_1357846886999_949065_20332 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846886999_949065_20332() {
                super();
                init_17_0_2_2_edc0357_1357846886999_949065_20332Members();
                init_17_0_2_2_edc0357_1357846886999_949065_20332Collections();
                init_17_0_2_2_edc0357_1357846886999_949065_20332Elaborations();
            }

            public _17_0_2_2_edc0357_1357846886999_949065_20332(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846886999_949065_20332Members();
                init_17_0_2_2_edc0357_1357846886999_949065_20332Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846886999_949065_20332Elaborations();
                fixTimeDependencies();
            }

            public IntegerParameter new_load = null;

            public IntegerParameter _17_0_2_2_edc0357_1357846887450_957139_21392 = null;

            public IntegerParameter _17_0_2_2_edc0357_1357846887451_261263_21394 = null;

            public IntegerParameter cap = null;

            public IntegerParameter desired = null;

            public BooleanParameter objectToPass = null;

            public IntegerParameter _17_0_2_2_edc0357_1357846887450_488704_21393 = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846887003_903670_20340_exists = null;

            public Dependency new_loadDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887450_957139_21392Dependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887451_261263_21394Dependency = null;

            public Dependency capDependency = null;

            public Dependency desiredDependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887450_488704_21393Dependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887003_903670_20340_existsDependency = null;

            public Effect effect74 = null;

            public Parameter effect74Var = null;

            public ElaborationRule elaborationRule75 = null;

            public void init_17_0_2_2_edc0357_1357846886999_949065_20332Members() {
                try {
                    if (new_load == null) new_load = new IntegerParameter("new_load", (Integer) null, this);
                    if (_17_0_2_2_edc0357_1357846887450_957139_21392 == null) _17_0_2_2_edc0357_1357846887450_957139_21392 = new IntegerParameter("_17_0_2_2_edc0357_1357846887450_957139_21392", (Integer) null, this);
                    if (_17_0_2_2_edc0357_1357846887451_261263_21394 == null) _17_0_2_2_edc0357_1357846887451_261263_21394 = new IntegerParameter("_17_0_2_2_edc0357_1357846887451_261263_21394", (Integer) null, this);
                    if (cap == null) cap = new IntegerParameter("cap", (Integer) null, this);
                    if (desired == null) desired = new IntegerParameter("desired", (Integer) null, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (_17_0_2_2_edc0357_1357846887450_488704_21393 == null) _17_0_2_2_edc0357_1357846887450_488704_21393 = new IntegerParameter("_17_0_2_2_edc0357_1357846887450_488704_21393", (Integer) null, this);
                    if (_17_0_2_2_edc0357_1357846887003_903670_20340_exists == null) _17_0_2_2_edc0357_1357846887003_903670_20340_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887003_903670_20340_exists", (Boolean) false, this);
                    Object effect74VarV = sig_17_0_2_2_edc0357_1357846887006_249010_20362;
                    effect74Var = new Parameter("effect74Var", null, null, this);
                    addDependency(effect74Var, new Expression(effect74VarV));
                    effect74 = new EffectFunction(new EffectFunction(effect74Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { _17_0_2_2_edc0357_1357846887450_957139_21392, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846886999_949065_20332Collections() {
                parameters.add(new_load);
                parameters.add(_17_0_2_2_edc0357_1357846887450_957139_21392);
                parameters.add(_17_0_2_2_edc0357_1357846887451_261263_21394);
                parameters.add(cap);
                parameters.add(desired);
                parameters.add(objectToPass);
                parameters.add(_17_0_2_2_edc0357_1357846887450_488704_21393);
                parameters.add(_17_0_2_2_edc0357_1357846887003_903670_20340_exists);
                Set<Effect> effectsForeffect74Var = new TreeSet<Effect>();
                effectsForeffect74Var.add(effect74);
                addEffects((Parameter<?>) effect74Var, effectsForeffect74Var);
            }

            public void init_17_0_2_2_edc0357_1357846886999_949065_20332Dependencies() {
                addDependency(new_load, new Expression(new FunctionCall(null, ClassUtils.getMethodForArgTypes("java.lang.Math", "PowerSystem3", "min", int.class, int.class), new Object[] { new Expression<Integer>(desired), new Expression<Integer>(cap) })));
                addDependency(_17_0_2_2_edc0357_1357846887450_957139_21392, new Expression<Integer>(new_load));
                addDependency(_17_0_2_2_edc0357_1357846887451_261263_21394, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887005_828482_20355, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(cap, new Expression<Integer>(_17_0_2_2_edc0357_1357846887451_261263_21394));
                addDependency(desired, new Expression<Integer>(_17_0_2_2_edc0357_1357846887450_488704_21393));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887005_556108_20356, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_2_edc0357_1357846887450_488704_21393, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887006_587764_20359, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_2_edc0357_1357846887003_903670_20340_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886997_31914_20329_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886997_31914_20329_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
            }

            public void init_17_0_2_2_edc0357_1357846886999_949065_20332Elaborations() {
                init_17_0_2_2_edc0357_1357846886999_949065_20332Dependencies();
                Expression<?>[] arguments75 = new Expression<?>[2];
                arguments75[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                arguments75[1] = new Expression<ObjectFlow<Integer>>(sig_17_0_2_2_edc0357_1357846887006_249010_20362);
                Expression<Boolean> condition75 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887003_903670_20340_exists);
                elaborationRule75 = addElaborationRule(condition75, _17_0_2_2_edc0357_1357846886590_580669_19880.this, Customer._17_0_2_2_edc0357_1357846886590_580669_19880._17_0_2_2_edc0357_1357846887003_903670_20340.class, "CChange_merge_changeTo_MergeNode_changePowerUsage", arguments75);
            }
        }

        public class _17_0_2_2_edc0357_1357846886999_888577_20333 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846886999_888577_20333() {
                super();
                init_17_0_2_2_edc0357_1357846886999_888577_20333Members();
                init_17_0_2_2_edc0357_1357846886999_888577_20333Collections();
                init_17_0_2_2_edc0357_1357846886999_888577_20333Elaborations();
            }

            public _17_0_2_2_edc0357_1357846886999_888577_20333(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846886999_888577_20333Members();
                init_17_0_2_2_edc0357_1357846886999_888577_20333Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846886999_888577_20333Elaborations();
                fixTimeDependencies();
            }

            public Parameter< Customer > _17_0_2_2_edc0357_1357846887451_758543_21395 = null;

            public BooleanParameter objectToPass = null;

            public Parameter< Power_System.SignalchangeLoadValue > signalObject = null;

            public IntegerParameter _17_0_2_2_edc0357_1357846887452_297102_21396 = null;

            public Dependency _17_0_2_2_edc0357_1357846887451_758543_21395Dependency = null;

            public Dependency finalNode_startTimeDependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Dependency signalObjectDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887452_297102_21396Dependency = null;

            public Effect effect76 = null;

            public Parameter effect76Var = null;

            public Effect effect77 = null;

            public Parameter effect77Var = null;

            public Effect effect78 = null;

            public Parameter effect78Var = null;

            public void init_17_0_2_2_edc0357_1357846886999_888577_20333Members() {
                try {
                    if (_17_0_2_2_edc0357_1357846887451_758543_21395 == null) _17_0_2_2_edc0357_1357846887451_758543_21395 = new Parameter<Customer>("_17_0_2_2_edc0357_1357846887451_758543_21395", null, (Customer) null, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (signalObject == null) signalObject = new Parameter<Power_System.SignalchangeLoadValue>("signalObject", null, (Power_System.SignalchangeLoadValue) null, this);
                    if (_17_0_2_2_edc0357_1357846887452_297102_21396 == null) _17_0_2_2_edc0357_1357846887452_297102_21396 = new IntegerParameter("_17_0_2_2_edc0357_1357846887452_297102_21396", (Integer) null, this);
                    Object effect76VarV = sig_17_0_2_2_edc0357_1357846887004_40735_20345;
                    effect76Var = new Parameter("effect76Var", null, null, this);
                    addDependency(effect76Var, new Expression(effect76VarV));
                    effect76 = new EffectFunction(new EffectFunction(effect76Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect77VarV = new FunctionCall(x, Parameter.class, "getMember", new Object[] { "ss_17_0_2_2_edc0357_1357846886595_931685_19886_changeLoadValue" });
                    effect77Var = new Parameter("effect77Var", null, null, this);
                    addDependency(effect77Var, new Expression(effect77VarV));
                    effect77 = new EffectFunction(new EffectFunction(null, ClassUtils.getMethodForArgTypes("ObjectFlow<SignalchangeLoadValue>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { signalObject, endTime }, effect77Var));
                    Object effect78VarV = new FunctionCall(signalObject, Parameter.class, "getMember", new Object[] { "load__17_0_2_2_edc0357_1357846886587_742419_19875" });
                    effect78Var = new Parameter("effect78Var", null, null, this);
                    addDependency(effect78Var, new Expression(effect78VarV));
                    effect78 = new EffectFunction(new EffectFunction(null, ClassUtils.getMethodForArgTypes("TimeVaryingMap<Integer>", "PowerSystem3", "setValue", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, _17_0_2_2_edc0357_1357846887452_297102_21396 }, effect78Var));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846886999_888577_20333Collections() {
                parameters.add(_17_0_2_2_edc0357_1357846887451_758543_21395);
                parameters.add(objectToPass);
                parameters.add(signalObject);
                parameters.add(_17_0_2_2_edc0357_1357846887452_297102_21396);
                Set<Effect> effectsForeffect76Var = new TreeSet<Effect>();
                effectsForeffect76Var.add(effect76);
                addEffects((Parameter<?>) effect76Var, effectsForeffect76Var);
                Set<Effect> effectsForeffect77Var = new TreeSet<Effect>();
                effectsForeffect77Var.add(effect77);
                addEffects((Parameter<?>) effect77Var, effectsForeffect77Var);
                Set<Effect> effectsForeffect78Var = new TreeSet<Effect>();
                effectsForeffect78Var.add(effect78);
                addEffects((Parameter<?>) effect78Var, effectsForeffect78Var);
            }

            public void init_17_0_2_2_edc0357_1357846886999_888577_20333Dependencies() {
                addDependency(_17_0_2_2_edc0357_1357846887451_758543_21395, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887004_21611_20344, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(finalNode_startTime, new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887005_288480_20348, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(signalObject, new Expression<Power_System.SignalchangeLoadValue>(new ConstructorCall(x, ClassUtils.getConstructorForArgTypes(Power_System.SignalchangeLoadValue.class), new Object[] {})));
                addDependency(_17_0_2_2_edc0357_1357846887452_297102_21396, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887004_444568_20347, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_2_edc0357_1357846886999_888577_20333Elaborations() {
                init_17_0_2_2_edc0357_1357846886999_888577_20333Dependencies();
            }
        }

        public class _17_0_2_2_edc0357_1357846887000_897383_20334 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846887000_897383_20334() {
                super();
                init_17_0_2_2_edc0357_1357846887000_897383_20334Members();
                init_17_0_2_2_edc0357_1357846887000_897383_20334Collections();
                init_17_0_2_2_edc0357_1357846887000_897383_20334Elaborations();
            }

            public _17_0_2_2_edc0357_1357846887000_897383_20334(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846887000_897383_20334Members();
                init_17_0_2_2_edc0357_1357846887000_897383_20334Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846887000_897383_20334Elaborations();
                fixTimeDependencies();
            }

            public IntegerParameter myDeciderID_decider_17_0_2_2_edc0357_1357846886999_888577_20333 = null;

            public BooleanParameter addToDecider__17_0_2_2_edc0357_1357846886999_888577_20333 = null;

            public IntegerParameter myDeciderID_decider_17_0_2_2_edc0357_1357846886996_671670_20327 = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846886999_888577_20333_exists = null;

            public BooleanParameter addToDecider__17_0_2_2_edc0357_1357846886996_671670_20327 = null;

            public IntegerParameter objectToPass = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846886996_671670_20327_exists = null;

            public Dependency myDeciderID_decider_17_0_2_2_edc0357_1357846886999_888577_20333Dependency = null;

            public Dependency addToDecider__17_0_2_2_edc0357_1357846886999_888577_20333Dependency = null;

            public Dependency myDeciderID_decider_17_0_2_2_edc0357_1357846886996_671670_20327Dependency = null;

            public Dependency _17_0_2_2_edc0357_1357846886999_888577_20333_existsDependency = null;

            public Dependency addToDecider__17_0_2_2_edc0357_1357846886996_671670_20327Dependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846886996_671670_20327_existsDependency = null;

            public Effect effect79 = null;

            public Parameter effect79Var = null;

            public Effect effect80 = null;

            public Parameter effect80Var = null;

            public Effect effect81 = null;

            public Parameter effect81Var = null;

            public Effect effect82 = null;

            public Parameter effect82Var = null;

            public ElaborationRule elaborationRule83 = null;

            public ElaborationRule elaborationRule84 = null;

            public void init_17_0_2_2_edc0357_1357846887000_897383_20334Members() {
                try {
                    if (myDeciderID_decider_17_0_2_2_edc0357_1357846886999_888577_20333 == null) myDeciderID_decider_17_0_2_2_edc0357_1357846886999_888577_20333 = new IntegerParameter("myDeciderID_decider_17_0_2_2_edc0357_1357846886999_888577_20333", (Integer) 1, this);
                    if (addToDecider__17_0_2_2_edc0357_1357846886999_888577_20333 == null) addToDecider__17_0_2_2_edc0357_1357846886999_888577_20333 = new BooleanParameter("addToDecider__17_0_2_2_edc0357_1357846886999_888577_20333", (Boolean) false, this);
                    if (myDeciderID_decider_17_0_2_2_edc0357_1357846886996_671670_20327 == null) myDeciderID_decider_17_0_2_2_edc0357_1357846886996_671670_20327 = new IntegerParameter("myDeciderID_decider_17_0_2_2_edc0357_1357846886996_671670_20327", (Integer) 2, this);
                    if (_17_0_2_2_edc0357_1357846886999_888577_20333_exists == null) _17_0_2_2_edc0357_1357846886999_888577_20333_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846886999_888577_20333_exists", (Boolean) false, this);
                    if (addToDecider__17_0_2_2_edc0357_1357846886996_671670_20327 == null) addToDecider__17_0_2_2_edc0357_1357846886996_671670_20327 = new BooleanParameter("addToDecider__17_0_2_2_edc0357_1357846886996_671670_20327", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new IntegerParameter("objectToPass", (Integer) null, this);
                    if (_17_0_2_2_edc0357_1357846886996_671670_20327_exists == null) _17_0_2_2_edc0357_1357846886996_671670_20327_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846886996_671670_20327_exists", (Boolean) false, this);
                    Object effect79VarV = sig_17_0_2_2_edc0357_1357846887004_499762_20346;
                    effect79Var = new Parameter("effect79Var", null, null, this);
                    addDependency(effect79Var, new Expression(effect79VarV));
                    effect79 = new EffectFunction(new EffectFunction(effect79Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect80VarV = sig_17_0_2_2_edc0357_1357846887004_444568_20347;
                    effect80Var = new Parameter("effect80Var", null, null, this);
                    addDependency(effect80Var, new Expression(effect80VarV));
                    effect80 = new EffectFunction(new EffectFunction(effect80Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect81VarV = decider_17_0_2_2_edc0357_1357846886996_671670_20327;
                    effect81Var = new Parameter("effect81Var", null, null, this);
                    addDependency(effect81Var, new Expression(effect81VarV));
                    effect81 = new EffectFunction(new EffectFunction(effect81Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class, java.lang.Boolean.class), new Object[] { endTime, myDeciderID_decider_17_0_2_2_edc0357_1357846886996_671670_20327, addToDecider__17_0_2_2_edc0357_1357846886996_671670_20327 }));
                    Object effect82VarV = decider_17_0_2_2_edc0357_1357846886999_888577_20333;
                    effect82Var = new Parameter("effect82Var", null, null, this);
                    addDependency(effect82Var, new Expression(effect82VarV));
                    effect82 = new EffectFunction(new EffectFunction(effect82Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class, java.lang.Boolean.class), new Object[] { endTime, myDeciderID_decider_17_0_2_2_edc0357_1357846886999_888577_20333, addToDecider__17_0_2_2_edc0357_1357846886999_888577_20333 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846887000_897383_20334Collections() {
                parameters.add(myDeciderID_decider_17_0_2_2_edc0357_1357846886999_888577_20333);
                parameters.add(addToDecider__17_0_2_2_edc0357_1357846886999_888577_20333);
                parameters.add(myDeciderID_decider_17_0_2_2_edc0357_1357846886996_671670_20327);
                parameters.add(_17_0_2_2_edc0357_1357846886999_888577_20333_exists);
                parameters.add(addToDecider__17_0_2_2_edc0357_1357846886996_671670_20327);
                parameters.add(objectToPass);
                parameters.add(_17_0_2_2_edc0357_1357846886996_671670_20327_exists);
                Set<Effect> effectsForeffect79Var = new TreeSet<Effect>();
                effectsForeffect79Var.add(effect79);
                addEffects((Parameter<?>) effect79Var, effectsForeffect79Var);
                Set<Effect> effectsForeffect80Var = new TreeSet<Effect>();
                effectsForeffect80Var.add(effect80);
                addEffects((Parameter<?>) effect80Var, effectsForeffect80Var);
                Set<Effect> effectsForeffect81Var = new TreeSet<Effect>();
                effectsForeffect81Var.add(effect81);
                addEffects((Parameter<?>) effect81Var, effectsForeffect81Var);
                Set<Effect> effectsForeffect82Var = new TreeSet<Effect>();
                effectsForeffect82Var.add(effect82);
                addEffects((Parameter<?>) effect82Var, effectsForeffect82Var);
            }

            public void init_17_0_2_2_edc0357_1357846887000_897383_20334Dependencies() {
                addDependency(myDeciderID_decider_17_0_2_2_edc0357_1357846886999_888577_20333, new Expression<Integer>(1));
                addDependency(addToDecider__17_0_2_2_edc0357_1357846886999_888577_20333, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886997_31914_20329_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886997_31914_20329_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(myDeciderID_decider_17_0_2_2_edc0357_1357846886996_671670_20327, new Expression<Integer>(2));
                addDependency(_17_0_2_2_edc0357_1357846886999_888577_20333_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886997_31914_20329_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886997_31914_20329_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846886999_888577_20333, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846886999_888577_20333, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846886999_888577_20333, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_2_edc0357_1357846886999_888577_20333)))))))));
                addDependency(addToDecider__17_0_2_2_edc0357_1357846886996_671670_20327, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886997_31914_20329_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886997_31914_20329_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887006_887428_20363, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_2_edc0357_1357846886996_671670_20327_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886997_31914_20329_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886997_31914_20329_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846886996_671670_20327, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846886996_671670_20327, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846886996_671670_20327, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_2_edc0357_1357846886996_671670_20327)))))))));
            }

            public void init_17_0_2_2_edc0357_1357846887000_897383_20334Elaborations() {
                init_17_0_2_2_edc0357_1357846887000_897383_20334Dependencies();
                Expression<?>[] arguments83 = new Expression<?>[1];
                arguments83[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition83 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846886996_671670_20327_exists);
                elaborationRule83 = addElaborationRule(condition83, _17_0_2_2_edc0357_1357846886590_580669_19880.this, Customer._17_0_2_2_edc0357_1357846886590_580669_19880._17_0_2_2_edc0357_1357846886996_671670_20327.class, "CChange_setnewusage_AddStructuralFeatureValueAction_changePowerUsage", arguments83);
                Expression<?>[] arguments84 = new Expression<?>[1];
                arguments84[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition84 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846886999_888577_20333_exists);
                elaborationRule84 = addElaborationRule(condition84, _17_0_2_2_edc0357_1357846886590_580669_19880.this, Customer._17_0_2_2_edc0357_1357846886590_580669_19880._17_0_2_2_edc0357_1357846886999_888577_20333.class, "CChange_sendnewusage_SendSignalAction_changePowerUsage", arguments84);
            }
        }

        public class _17_0_2_2_edc0357_1357846887000_232381_20335 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846887000_232381_20335() {
                super();
                init_17_0_2_2_edc0357_1357846887000_232381_20335Members();
                init_17_0_2_2_edc0357_1357846887000_232381_20335Collections();
                init_17_0_2_2_edc0357_1357846887000_232381_20335Elaborations();
            }

            public _17_0_2_2_edc0357_1357846887000_232381_20335(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846887000_232381_20335Members();
                init_17_0_2_2_edc0357_1357846887000_232381_20335Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846887000_232381_20335Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_2_edc0357_1357846886995_216436_20325_exists = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846886995_638275_20326_exists = null;

            public BooleanParameter objectToPass = null;

            public Dependency _17_0_2_2_edc0357_1357846886995_216436_20325_existsDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846886995_638275_20326_existsDependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Effect effect85 = null;

            public Parameter effect85Var = null;

            public Effect effect86 = null;

            public Parameter effect86Var = null;

            public ElaborationRule elaborationRule87 = null;

            public ElaborationRule elaborationRule88 = null;

            public void init_17_0_2_2_edc0357_1357846887000_232381_20335Members() {
                try {
                    if (_17_0_2_2_edc0357_1357846886995_216436_20325_exists == null) _17_0_2_2_edc0357_1357846886995_216436_20325_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846886995_216436_20325_exists", (Boolean) false, this);
                    if (_17_0_2_2_edc0357_1357846886995_638275_20326_exists == null) _17_0_2_2_edc0357_1357846886995_638275_20326_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846886995_638275_20326_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect85VarV = sig_17_0_2_2_edc0357_1357846887005_653176_20350;
                    effect85Var = new Parameter("effect85Var", null, null, this);
                    addDependency(effect85Var, new Expression(effect85VarV));
                    effect85 = new EffectFunction(new EffectFunction(effect85Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect86VarV = sig_17_0_2_2_edc0357_1357846887005_721935_20351;
                    effect86Var = new Parameter("effect86Var", null, null, this);
                    addDependency(effect86Var, new Expression(effect86VarV));
                    effect86 = new EffectFunction(new EffectFunction(effect86Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846887000_232381_20335Collections() {
                parameters.add(_17_0_2_2_edc0357_1357846886995_216436_20325_exists);
                parameters.add(_17_0_2_2_edc0357_1357846886995_638275_20326_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect85Var = new TreeSet<Effect>();
                effectsForeffect85Var.add(effect85);
                addEffects((Parameter<?>) effect85Var, effectsForeffect85Var);
                Set<Effect> effectsForeffect86Var = new TreeSet<Effect>();
                effectsForeffect86Var.add(effect86);
                addEffects((Parameter<?>) effect86Var, effectsForeffect86Var);
            }

            public void init_17_0_2_2_edc0357_1357846887000_232381_20335Dependencies() {
                addDependency(_17_0_2_2_edc0357_1357846886995_216436_20325_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886997_31914_20329_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886997_31914_20329_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(_17_0_2_2_edc0357_1357846886995_638275_20326_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886997_31914_20329_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886997_31914_20329_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887005_830787_20349, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_2_edc0357_1357846887000_232381_20335Elaborations() {
                init_17_0_2_2_edc0357_1357846887000_232381_20335Dependencies();
                Expression<?>[] arguments87 = new Expression<?>[1];
                arguments87[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition87 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846886995_638275_20326_exists);
                elaborationRule87 = addElaborationRule(condition87, _17_0_2_2_edc0357_1357846886590_580669_19880.this, Customer._17_0_2_2_edc0357_1357846886590_580669_19880._17_0_2_2_edc0357_1357846886995_638275_20326.class, "CChange_spec15_ValueSpecificationAction_changePowerUsage", arguments87);
                Expression<?>[] arguments88 = new Expression<?>[1];
                arguments88[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition88 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846886995_216436_20325_exists);
                elaborationRule88 = addElaborationRule(condition88, _17_0_2_2_edc0357_1357846886590_580669_19880.this, Customer._17_0_2_2_edc0357_1357846886590_580669_19880._17_0_2_2_edc0357_1357846886995_216436_20325.class, "CChange_readself_ReadSelfAction_changePowerUsage", arguments88);
            }
        }

        public class _17_0_2_2_edc0357_1357846887001_670940_20336 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846887001_670940_20336() {
                super();
                init_17_0_2_2_edc0357_1357846887001_670940_20336Members();
                init_17_0_2_2_edc0357_1357846887001_670940_20336Collections();
                init_17_0_2_2_edc0357_1357846887001_670940_20336Elaborations();
            }

            public _17_0_2_2_edc0357_1357846887001_670940_20336(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846887001_670940_20336Members();
                init_17_0_2_2_edc0357_1357846887001_670940_20336Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846887001_670940_20336Elaborations();
                fixTimeDependencies();
            }

            public IntegerParameter new_load = null;

            public IntegerParameter _17_0_2_2_edc0357_1357846887453_481940_21398 = null;

            public IntegerParameter desired = null;

            public BooleanParameter objectToPass = null;

            public IntegerParameter _17_0_2_2_edc0357_1357846887453_100517_21397 = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846887003_903670_20340_exists = null;

            public Dependency new_loadDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887453_481940_21398Dependency = null;

            public Dependency desiredDependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887453_100517_21397Dependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887003_903670_20340_existsDependency = null;

            public Effect effect89 = null;

            public Parameter effect89Var = null;

            public ElaborationRule elaborationRule90 = null;

            public void init_17_0_2_2_edc0357_1357846887001_670940_20336Members() {
                try {
                    if (new_load == null) new_load = new IntegerParameter("new_load", (Integer) null, this);
                    if (_17_0_2_2_edc0357_1357846887453_481940_21398 == null) _17_0_2_2_edc0357_1357846887453_481940_21398 = new IntegerParameter("_17_0_2_2_edc0357_1357846887453_481940_21398", (Integer) null, this);
                    if (desired == null) desired = new IntegerParameter("desired", (Integer) null, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (_17_0_2_2_edc0357_1357846887453_100517_21397 == null) _17_0_2_2_edc0357_1357846887453_100517_21397 = new IntegerParameter("_17_0_2_2_edc0357_1357846887453_100517_21397", (Integer) null, this);
                    if (_17_0_2_2_edc0357_1357846887003_903670_20340_exists == null) _17_0_2_2_edc0357_1357846887003_903670_20340_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887003_903670_20340_exists", (Boolean) false, this);
                    Object effect89VarV = sig_17_0_2_2_edc0357_1357846887006_847328_20361;
                    effect89Var = new Parameter("effect89Var", null, null, this);
                    addDependency(effect89Var, new Expression(effect89VarV));
                    effect89 = new EffectFunction(new EffectFunction(effect89Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { _17_0_2_2_edc0357_1357846887453_100517_21397, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846887001_670940_20336Collections() {
                parameters.add(new_load);
                parameters.add(_17_0_2_2_edc0357_1357846887453_481940_21398);
                parameters.add(desired);
                parameters.add(objectToPass);
                parameters.add(_17_0_2_2_edc0357_1357846887453_100517_21397);
                parameters.add(_17_0_2_2_edc0357_1357846887003_903670_20340_exists);
                Set<Effect> effectsForeffect89Var = new TreeSet<Effect>();
                effectsForeffect89Var.add(effect89);
                addEffects((Parameter<?>) effect89Var, effectsForeffect89Var);
            }

            public void init_17_0_2_2_edc0357_1357846887001_670940_20336Dependencies() {
                addDependency(new_load, new Expression<Integer>(desired));
                addDependency(_17_0_2_2_edc0357_1357846887453_481940_21398, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887006_433864_20360, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(desired, new Expression<Integer>(_17_0_2_2_edc0357_1357846887453_481940_21398));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887006_938149_20357, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_2_edc0357_1357846887453_100517_21397, new Expression<Integer>(new_load));
                addDependency(_17_0_2_2_edc0357_1357846887003_903670_20340_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886997_31914_20329_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886997_31914_20329_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
            }

            public void init_17_0_2_2_edc0357_1357846887001_670940_20336Elaborations() {
                init_17_0_2_2_edc0357_1357846887001_670940_20336Dependencies();
                Expression<?>[] arguments90 = new Expression<?>[2];
                arguments90[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                arguments90[1] = new Expression<ObjectFlow<Integer>>(sig_17_0_2_2_edc0357_1357846887006_847328_20361);
                Expression<Boolean> condition90 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887003_903670_20340_exists);
                elaborationRule90 = addElaborationRule(condition90, _17_0_2_2_edc0357_1357846886590_580669_19880.this, Customer._17_0_2_2_edc0357_1357846886590_580669_19880._17_0_2_2_edc0357_1357846887003_903670_20340.class, "CChange_merge_changeTo_MergeNode_changePowerUsage", arguments90);
            }
        }

        public class _17_0_2_2_edc0357_1357846887002_818297_20337 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846887002_818297_20337() {
                super();
                init_17_0_2_2_edc0357_1357846887002_818297_20337Members();
                init_17_0_2_2_edc0357_1357846887002_818297_20337Collections();
                init_17_0_2_2_edc0357_1357846887002_818297_20337Elaborations();
            }

            public _17_0_2_2_edc0357_1357846887002_818297_20337(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846887002_818297_20337Members();
                init_17_0_2_2_edc0357_1357846887002_818297_20337Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846887002_818297_20337Elaborations();
                fixTimeDependencies();
            }

            public IntegerParameter myDeciderID_decider_17_0_2_2_edc0357_1357846886999_949065_20332 = null;

            public IntegerParameter myDeciderID_decider_17_0_2_2_edc0357_1357846887002_996884_20338 = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846887002_996884_20338_exists = null;

            public BooleanParameter addToDecider__17_0_2_2_edc0357_1357846886999_949065_20332 = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846886999_949065_20332_exists = null;

            public IntegerParameter objectToPass = null;

            public BooleanParameter addToDecider__17_0_2_2_edc0357_1357846887002_996884_20338 = null;

            public Dependency myDeciderID_decider_17_0_2_2_edc0357_1357846886999_949065_20332Dependency = null;

            public Dependency myDeciderID_decider_17_0_2_2_edc0357_1357846887002_996884_20338Dependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887002_996884_20338_existsDependency = null;

            public Dependency addToDecider__17_0_2_2_edc0357_1357846886999_949065_20332Dependency = null;

            public Dependency _17_0_2_2_edc0357_1357846886999_949065_20332_existsDependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Dependency addToDecider__17_0_2_2_edc0357_1357846887002_996884_20338Dependency = null;

            public Effect effect91 = null;

            public Parameter effect91Var = null;

            public Effect effect92 = null;

            public Parameter effect92Var = null;

            public Effect effect93 = null;

            public Parameter effect93Var = null;

            public Effect effect94 = null;

            public Parameter effect94Var = null;

            public ElaborationRule elaborationRule95 = null;

            public ElaborationRule elaborationRule96 = null;

            public void init_17_0_2_2_edc0357_1357846887002_818297_20337Members() {
                try {
                    if (myDeciderID_decider_17_0_2_2_edc0357_1357846886999_949065_20332 == null) myDeciderID_decider_17_0_2_2_edc0357_1357846886999_949065_20332 = new IntegerParameter("myDeciderID_decider_17_0_2_2_edc0357_1357846886999_949065_20332", (Integer) 2, this);
                    if (myDeciderID_decider_17_0_2_2_edc0357_1357846887002_996884_20338 == null) myDeciderID_decider_17_0_2_2_edc0357_1357846887002_996884_20338 = new IntegerParameter("myDeciderID_decider_17_0_2_2_edc0357_1357846887002_996884_20338", (Integer) 2, this);
                    if (_17_0_2_2_edc0357_1357846887002_996884_20338_exists == null) _17_0_2_2_edc0357_1357846887002_996884_20338_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887002_996884_20338_exists", (Boolean) false, this);
                    if (addToDecider__17_0_2_2_edc0357_1357846886999_949065_20332 == null) addToDecider__17_0_2_2_edc0357_1357846886999_949065_20332 = new BooleanParameter("addToDecider__17_0_2_2_edc0357_1357846886999_949065_20332", (Boolean) false, this);
                    if (_17_0_2_2_edc0357_1357846886999_949065_20332_exists == null) _17_0_2_2_edc0357_1357846886999_949065_20332_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846886999_949065_20332_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new IntegerParameter("objectToPass", (Integer) null, this);
                    if (addToDecider__17_0_2_2_edc0357_1357846887002_996884_20338 == null) addToDecider__17_0_2_2_edc0357_1357846887002_996884_20338 = new BooleanParameter("addToDecider__17_0_2_2_edc0357_1357846887002_996884_20338", (Boolean) false, this);
                    Object effect91VarV = sig_17_0_2_2_edc0357_1357846887005_907232_20354;
                    effect91Var = new Parameter("effect91Var", null, null, this);
                    addDependency(effect91Var, new Expression(effect91VarV));
                    effect91 = new EffectFunction(new EffectFunction(effect91Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect92VarV = sig_17_0_2_2_edc0357_1357846887005_828482_20355;
                    effect92Var = new Parameter("effect92Var", null, null, this);
                    addDependency(effect92Var, new Expression(effect92VarV));
                    effect92 = new EffectFunction(new EffectFunction(effect92Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect93VarV = decider_17_0_2_2_edc0357_1357846886999_949065_20332;
                    effect93Var = new Parameter("effect93Var", null, null, this);
                    addDependency(effect93Var, new Expression(effect93VarV));
                    effect93 = new EffectFunction(new EffectFunction(effect93Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class, java.lang.Boolean.class), new Object[] { endTime, myDeciderID_decider_17_0_2_2_edc0357_1357846886999_949065_20332, addToDecider__17_0_2_2_edc0357_1357846886999_949065_20332 }));
                    Object effect94VarV = decider_17_0_2_2_edc0357_1357846887002_996884_20338;
                    effect94Var = new Parameter("effect94Var", null, null, this);
                    addDependency(effect94Var, new Expression(effect94VarV));
                    effect94 = new EffectFunction(new EffectFunction(effect94Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class, java.lang.Boolean.class), new Object[] { endTime, myDeciderID_decider_17_0_2_2_edc0357_1357846887002_996884_20338, addToDecider__17_0_2_2_edc0357_1357846887002_996884_20338 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846887002_818297_20337Collections() {
                parameters.add(myDeciderID_decider_17_0_2_2_edc0357_1357846886999_949065_20332);
                parameters.add(myDeciderID_decider_17_0_2_2_edc0357_1357846887002_996884_20338);
                parameters.add(_17_0_2_2_edc0357_1357846887002_996884_20338_exists);
                parameters.add(addToDecider__17_0_2_2_edc0357_1357846886999_949065_20332);
                parameters.add(_17_0_2_2_edc0357_1357846886999_949065_20332_exists);
                parameters.add(objectToPass);
                parameters.add(addToDecider__17_0_2_2_edc0357_1357846887002_996884_20338);
                Set<Effect> effectsForeffect91Var = new TreeSet<Effect>();
                effectsForeffect91Var.add(effect91);
                addEffects((Parameter<?>) effect91Var, effectsForeffect91Var);
                Set<Effect> effectsForeffect92Var = new TreeSet<Effect>();
                effectsForeffect92Var.add(effect92);
                addEffects((Parameter<?>) effect92Var, effectsForeffect92Var);
                Set<Effect> effectsForeffect93Var = new TreeSet<Effect>();
                effectsForeffect93Var.add(effect93);
                addEffects((Parameter<?>) effect93Var, effectsForeffect93Var);
                Set<Effect> effectsForeffect94Var = new TreeSet<Effect>();
                effectsForeffect94Var.add(effect94);
                addEffects((Parameter<?>) effect94Var, effectsForeffect94Var);
            }

            public void init_17_0_2_2_edc0357_1357846887002_818297_20337Dependencies() {
                addDependency(myDeciderID_decider_17_0_2_2_edc0357_1357846886999_949065_20332, new Expression<Integer>(2));
                addDependency(myDeciderID_decider_17_0_2_2_edc0357_1357846887002_996884_20338, new Expression<Integer>(2));
                addDependency(_17_0_2_2_edc0357_1357846887002_996884_20338_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886997_31914_20329_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886997_31914_20329_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887002_996884_20338, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887002_996884_20338, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887002_996884_20338, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_2_edc0357_1357846887002_996884_20338)))))))));
                addDependency(addToDecider__17_0_2_2_edc0357_1357846886999_949065_20332, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886997_31914_20329_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886997_31914_20329_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(_17_0_2_2_edc0357_1357846886999_949065_20332_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886997_31914_20329_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886997_31914_20329_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846886999_949065_20332, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846886999_949065_20332, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846886999_949065_20332, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_2_edc0357_1357846886999_949065_20332)))))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887005_951410_20353, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(addToDecider__17_0_2_2_edc0357_1357846887002_996884_20338, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886997_31914_20329_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886997_31914_20329_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
            }

            public void init_17_0_2_2_edc0357_1357846887002_818297_20337Elaborations() {
                init_17_0_2_2_edc0357_1357846887002_818297_20337Dependencies();
                Expression<?>[] arguments95 = new Expression<?>[1];
                arguments95[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition95 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846886999_949065_20332_exists);
                elaborationRule95 = addElaborationRule(condition95, _17_0_2_2_edc0357_1357846886590_580669_19880.this, Customer._17_0_2_2_edc0357_1357846886590_580669_19880._17_0_2_2_edc0357_1357846886999_949065_20332.class, "CChange_calc_capmin_CallBehaviorAction_changePowerUsage", arguments95);
                Expression<?>[] arguments96 = new Expression<?>[1];
                arguments96[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition96 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887002_996884_20338_exists);
                elaborationRule96 = addElaborationRule(condition96, _17_0_2_2_edc0357_1357846886590_580669_19880.this, Customer._17_0_2_2_edc0357_1357846886590_580669_19880._17_0_2_2_edc0357_1357846887002_996884_20338.class, "CChange_decidecap_DecisionNode_changePowerUsage", arguments96);
            }
        }

        public class _17_0_2_2_edc0357_1357846887002_996884_20338 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846887002_996884_20338() {
                super();
                init_17_0_2_2_edc0357_1357846887002_996884_20338Members();
                init_17_0_2_2_edc0357_1357846887002_996884_20338Collections();
                init_17_0_2_2_edc0357_1357846887002_996884_20338Elaborations();
            }

            public _17_0_2_2_edc0357_1357846887002_996884_20338(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846887002_996884_20338Members();
                init_17_0_2_2_edc0357_1357846887002_996884_20338Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846887002_996884_20338Elaborations();
                fixTimeDependencies();
            }

            public IntegerParameter myDeciderID_decider_17_0_2_2_edc0357_1357846886999_949065_20332 = null;

            public BooleanParameter addToDecider__17_0_2_2_edc0357_1357846887001_670940_20336 = null;

            public IntegerParameter myDeciderID_decider_17_0_2_2_edc0357_1357846887001_670940_20336 = null;

            public BooleanParameter addToDecider__17_0_2_2_edc0357_1357846886999_949065_20332 = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846887001_670940_20336_exists = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846886999_949065_20332_exists = null;

            public BooleanParameter objectToPass = null;

            public IntegerParameter decisionInput = null;

            public Dependency myDeciderID_decider_17_0_2_2_edc0357_1357846886999_949065_20332Dependency = null;

            public Dependency addToDecider__17_0_2_2_edc0357_1357846887001_670940_20336Dependency = null;

            public Dependency myDeciderID_decider_17_0_2_2_edc0357_1357846887001_670940_20336Dependency = null;

            public Dependency addToDecider__17_0_2_2_edc0357_1357846886999_949065_20332Dependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887001_670940_20336_existsDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846886999_949065_20332_existsDependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Dependency decisionInputDependency = null;

            public Effect effect97 = null;

            public Parameter effect97Var = null;

            public Effect effect98 = null;

            public Parameter effect98Var = null;

            public Effect effect99 = null;

            public Parameter effect99Var = null;

            public Effect effect100 = null;

            public Parameter effect100Var = null;

            public ElaborationRule elaborationRule101 = null;

            public ElaborationRule elaborationRule102 = null;

            public void init_17_0_2_2_edc0357_1357846887002_996884_20338Members() {
                try {
                    if (myDeciderID_decider_17_0_2_2_edc0357_1357846886999_949065_20332 == null) myDeciderID_decider_17_0_2_2_edc0357_1357846886999_949065_20332 = new IntegerParameter("myDeciderID_decider_17_0_2_2_edc0357_1357846886999_949065_20332", (Integer) 3, this);
                    if (addToDecider__17_0_2_2_edc0357_1357846887001_670940_20336 == null) addToDecider__17_0_2_2_edc0357_1357846887001_670940_20336 = new BooleanParameter("addToDecider__17_0_2_2_edc0357_1357846887001_670940_20336", (Boolean) false, this);
                    if (myDeciderID_decider_17_0_2_2_edc0357_1357846887001_670940_20336 == null) myDeciderID_decider_17_0_2_2_edc0357_1357846887001_670940_20336 = new IntegerParameter("myDeciderID_decider_17_0_2_2_edc0357_1357846887001_670940_20336", (Integer) 2, this);
                    if (addToDecider__17_0_2_2_edc0357_1357846886999_949065_20332 == null) addToDecider__17_0_2_2_edc0357_1357846886999_949065_20332 = new BooleanParameter("addToDecider__17_0_2_2_edc0357_1357846886999_949065_20332", (Boolean) false, this);
                    if (_17_0_2_2_edc0357_1357846887001_670940_20336_exists == null) _17_0_2_2_edc0357_1357846887001_670940_20336_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887001_670940_20336_exists", (Boolean) false, this);
                    if (_17_0_2_2_edc0357_1357846886999_949065_20332_exists == null) _17_0_2_2_edc0357_1357846886999_949065_20332_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846886999_949065_20332_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (decisionInput == null) decisionInput = new IntegerParameter("decisionInput", (Integer) null, this);
                    Object effect97VarV = sig_17_0_2_2_edc0357_1357846887005_556108_20356;
                    effect97Var = new Parameter("effect97Var", null, null, this);
                    addDependency(effect97Var, new Expression(effect97VarV));
                    effect97 = new EffectFunction(new EffectFunction(effect97Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "sendIf", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class, java.lang.Boolean.class), new Object[] { objectToPass, endTime, _17_0_2_2_edc0357_1357846886999_949065_20332_exists }));
                    Object effect98VarV = sig_17_0_2_2_edc0357_1357846887006_938149_20357;
                    effect98Var = new Parameter("effect98Var", null, null, this);
                    addDependency(effect98Var, new Expression(effect98VarV));
                    effect98 = new EffectFunction(new EffectFunction(effect98Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "sendIf", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class, java.lang.Boolean.class), new Object[] { objectToPass, endTime, _17_0_2_2_edc0357_1357846887001_670940_20336_exists }));
                    Object effect99VarV = decider_17_0_2_2_edc0357_1357846887001_670940_20336;
                    effect99Var = new Parameter("effect99Var", null, null, this);
                    addDependency(effect99Var, new Expression(effect99VarV));
                    effect99 = new EffectFunction(new EffectFunction(effect99Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class, java.lang.Boolean.class), new Object[] { endTime, myDeciderID_decider_17_0_2_2_edc0357_1357846887001_670940_20336, addToDecider__17_0_2_2_edc0357_1357846887001_670940_20336 }));
                    Object effect100VarV = decider_17_0_2_2_edc0357_1357846886999_949065_20332;
                    effect100Var = new Parameter("effect100Var", null, null, this);
                    addDependency(effect100Var, new Expression(effect100VarV));
                    effect100 = new EffectFunction(new EffectFunction(effect100Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class, java.lang.Boolean.class), new Object[] { endTime, myDeciderID_decider_17_0_2_2_edc0357_1357846886999_949065_20332, addToDecider__17_0_2_2_edc0357_1357846886999_949065_20332 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846887002_996884_20338Collections() {
                parameters.add(myDeciderID_decider_17_0_2_2_edc0357_1357846886999_949065_20332);
                parameters.add(addToDecider__17_0_2_2_edc0357_1357846887001_670940_20336);
                parameters.add(myDeciderID_decider_17_0_2_2_edc0357_1357846887001_670940_20336);
                parameters.add(addToDecider__17_0_2_2_edc0357_1357846886999_949065_20332);
                parameters.add(_17_0_2_2_edc0357_1357846887001_670940_20336_exists);
                parameters.add(_17_0_2_2_edc0357_1357846886999_949065_20332_exists);
                parameters.add(objectToPass);
                parameters.add(decisionInput);
                Set<Effect> effectsForeffect100Var = new TreeSet<Effect>();
                effectsForeffect100Var.add(effect100);
                addEffects((Parameter<?>) effect100Var, effectsForeffect100Var);
                Set<Effect> effectsForeffect97Var = new TreeSet<Effect>();
                effectsForeffect97Var.add(effect97);
                addEffects((Parameter<?>) effect97Var, effectsForeffect97Var);
                Set<Effect> effectsForeffect98Var = new TreeSet<Effect>();
                effectsForeffect98Var.add(effect98);
                addEffects((Parameter<?>) effect98Var, effectsForeffect98Var);
                Set<Effect> effectsForeffect99Var = new TreeSet<Effect>();
                effectsForeffect99Var.add(effect99);
                addEffects((Parameter<?>) effect99Var, effectsForeffect99Var);
            }

            public void init_17_0_2_2_edc0357_1357846887002_996884_20338Dependencies() {
                addDependency(myDeciderID_decider_17_0_2_2_edc0357_1357846886999_949065_20332, new Expression<Integer>(3));
                addDependency(addToDecider__17_0_2_2_edc0357_1357846887001_670940_20336, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.LessEquals(new Expression<Integer>(decisionInput), new Expression<Integer>(0))), new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886997_31914_20329_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886997_31914_20329_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))))));
                addDependency(myDeciderID_decider_17_0_2_2_edc0357_1357846887001_670940_20336, new Expression<Integer>(2));
                addDependency(addToDecider__17_0_2_2_edc0357_1357846886999_949065_20332, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Greater(new Expression<Integer>(decisionInput), new Expression<Integer>(0))), new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886997_31914_20329_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886997_31914_20329_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))))));
                addDependency(_17_0_2_2_edc0357_1357846887001_670940_20336_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.LessEquals(new Expression<Integer>(decisionInput), new Expression<Integer>(0))), new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886997_31914_20329_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886997_31914_20329_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887001_670940_20336, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887001_670940_20336, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887001_670940_20336, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_2_edc0357_1357846887001_670940_20336)))))))));
                addDependency(_17_0_2_2_edc0357_1357846886999_949065_20332_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Greater(new Expression<Integer>(decisionInput), new Expression<Integer>(0))), new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886997_31914_20329_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886997_31914_20329_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846886999_949065_20332, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846886999_949065_20332, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846886999_949065_20332, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_2_edc0357_1357846886999_949065_20332)))))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887005_267810_20352, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(decisionInput, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887005_907232_20354, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_2_edc0357_1357846887002_996884_20338Elaborations() {
                init_17_0_2_2_edc0357_1357846887002_996884_20338Dependencies();
                Expression<?>[] arguments101 = new Expression<?>[1];
                arguments101[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition101 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887001_670940_20336_exists);
                elaborationRule101 = addElaborationRule(condition101, _17_0_2_2_edc0357_1357846886590_580669_19880.this, Customer._17_0_2_2_edc0357_1357846886590_580669_19880._17_0_2_2_edc0357_1357846887001_670940_20336.class, "CChange_calc_desired_CallBehaviorAction_changePowerUsage", arguments101);
                Expression<?>[] arguments102 = new Expression<?>[1];
                arguments102[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition102 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846886999_949065_20332_exists);
                elaborationRule102 = addElaborationRule(condition102, _17_0_2_2_edc0357_1357846886590_580669_19880.this, Customer._17_0_2_2_edc0357_1357846886590_580669_19880._17_0_2_2_edc0357_1357846886999_949065_20332.class, "CChange_calc_capmin_CallBehaviorAction_changePowerUsage", arguments102);
            }
        }

        public class _17_0_2_2_edc0357_1357846887003_521609_20339 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846887003_521609_20339() {
                super();
                init_17_0_2_2_edc0357_1357846887003_521609_20339Members();
                init_17_0_2_2_edc0357_1357846887003_521609_20339Collections();
                init_17_0_2_2_edc0357_1357846887003_521609_20339Elaborations();
            }

            public _17_0_2_2_edc0357_1357846887003_521609_20339(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846887003_521609_20339Members();
                init_17_0_2_2_edc0357_1357846887003_521609_20339Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846887003_521609_20339Elaborations();
                fixTimeDependencies();
            }

            public IntegerParameter myDeciderID_decider_17_0_2_2_edc0357_1357846886999_949065_20332 = null;

            public BooleanParameter addToDecider__17_0_2_2_edc0357_1357846887001_670940_20336 = null;

            public IntegerParameter myDeciderID_decider_17_0_2_2_edc0357_1357846887001_670940_20336 = null;

            public BooleanParameter addToDecider__17_0_2_2_edc0357_1357846886999_949065_20332 = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846887001_670940_20336_exists = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846886999_949065_20332_exists = null;

            public IntegerParameter objectToPass = null;

            public Dependency myDeciderID_decider_17_0_2_2_edc0357_1357846886999_949065_20332Dependency = null;

            public Dependency addToDecider__17_0_2_2_edc0357_1357846887001_670940_20336Dependency = null;

            public Dependency myDeciderID_decider_17_0_2_2_edc0357_1357846887001_670940_20336Dependency = null;

            public Dependency addToDecider__17_0_2_2_edc0357_1357846886999_949065_20332Dependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887001_670940_20336_existsDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846886999_949065_20332_existsDependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Effect effect103 = null;

            public Parameter effect103Var = null;

            public Effect effect104 = null;

            public Parameter effect104Var = null;

            public Effect effect105 = null;

            public Parameter effect105Var = null;

            public Effect effect106 = null;

            public Parameter effect106Var = null;

            public ElaborationRule elaborationRule107 = null;

            public ElaborationRule elaborationRule108 = null;

            public void init_17_0_2_2_edc0357_1357846887003_521609_20339Members() {
                try {
                    if (myDeciderID_decider_17_0_2_2_edc0357_1357846886999_949065_20332 == null) myDeciderID_decider_17_0_2_2_edc0357_1357846886999_949065_20332 = new IntegerParameter("myDeciderID_decider_17_0_2_2_edc0357_1357846886999_949065_20332", (Integer) 1, this);
                    if (addToDecider__17_0_2_2_edc0357_1357846887001_670940_20336 == null) addToDecider__17_0_2_2_edc0357_1357846887001_670940_20336 = new BooleanParameter("addToDecider__17_0_2_2_edc0357_1357846887001_670940_20336", (Boolean) false, this);
                    if (myDeciderID_decider_17_0_2_2_edc0357_1357846887001_670940_20336 == null) myDeciderID_decider_17_0_2_2_edc0357_1357846887001_670940_20336 = new IntegerParameter("myDeciderID_decider_17_0_2_2_edc0357_1357846887001_670940_20336", (Integer) 1, this);
                    if (addToDecider__17_0_2_2_edc0357_1357846886999_949065_20332 == null) addToDecider__17_0_2_2_edc0357_1357846886999_949065_20332 = new BooleanParameter("addToDecider__17_0_2_2_edc0357_1357846886999_949065_20332", (Boolean) false, this);
                    if (_17_0_2_2_edc0357_1357846887001_670940_20336_exists == null) _17_0_2_2_edc0357_1357846887001_670940_20336_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887001_670940_20336_exists", (Boolean) false, this);
                    if (_17_0_2_2_edc0357_1357846886999_949065_20332_exists == null) _17_0_2_2_edc0357_1357846886999_949065_20332_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846886999_949065_20332_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new IntegerParameter("objectToPass", (Integer) null, this);
                    Object effect103VarV = sig_17_0_2_2_edc0357_1357846887006_587764_20359;
                    effect103Var = new Parameter("effect103Var", null, null, this);
                    addDependency(effect103Var, new Expression(effect103VarV));
                    effect103 = new EffectFunction(new EffectFunction(effect103Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect104VarV = sig_17_0_2_2_edc0357_1357846887006_433864_20360;
                    effect104Var = new Parameter("effect104Var", null, null, this);
                    addDependency(effect104Var, new Expression(effect104VarV));
                    effect104 = new EffectFunction(new EffectFunction(effect104Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect105VarV = decider_17_0_2_2_edc0357_1357846887001_670940_20336;
                    effect105Var = new Parameter("effect105Var", null, null, this);
                    addDependency(effect105Var, new Expression(effect105VarV));
                    effect105 = new EffectFunction(new EffectFunction(effect105Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class, java.lang.Boolean.class), new Object[] { endTime, myDeciderID_decider_17_0_2_2_edc0357_1357846887001_670940_20336, addToDecider__17_0_2_2_edc0357_1357846887001_670940_20336 }));
                    Object effect106VarV = decider_17_0_2_2_edc0357_1357846886999_949065_20332;
                    effect106Var = new Parameter("effect106Var", null, null, this);
                    addDependency(effect106Var, new Expression(effect106VarV));
                    effect106 = new EffectFunction(new EffectFunction(effect106Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class, java.lang.Boolean.class), new Object[] { endTime, myDeciderID_decider_17_0_2_2_edc0357_1357846886999_949065_20332, addToDecider__17_0_2_2_edc0357_1357846886999_949065_20332 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846887003_521609_20339Collections() {
                parameters.add(myDeciderID_decider_17_0_2_2_edc0357_1357846886999_949065_20332);
                parameters.add(addToDecider__17_0_2_2_edc0357_1357846887001_670940_20336);
                parameters.add(myDeciderID_decider_17_0_2_2_edc0357_1357846887001_670940_20336);
                parameters.add(addToDecider__17_0_2_2_edc0357_1357846886999_949065_20332);
                parameters.add(_17_0_2_2_edc0357_1357846887001_670940_20336_exists);
                parameters.add(_17_0_2_2_edc0357_1357846886999_949065_20332_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect103Var = new TreeSet<Effect>();
                effectsForeffect103Var.add(effect103);
                addEffects((Parameter<?>) effect103Var, effectsForeffect103Var);
                Set<Effect> effectsForeffect104Var = new TreeSet<Effect>();
                effectsForeffect104Var.add(effect104);
                addEffects((Parameter<?>) effect104Var, effectsForeffect104Var);
                Set<Effect> effectsForeffect105Var = new TreeSet<Effect>();
                effectsForeffect105Var.add(effect105);
                addEffects((Parameter<?>) effect105Var, effectsForeffect105Var);
                Set<Effect> effectsForeffect106Var = new TreeSet<Effect>();
                effectsForeffect106Var.add(effect106);
                addEffects((Parameter<?>) effect106Var, effectsForeffect106Var);
            }

            public void init_17_0_2_2_edc0357_1357846887003_521609_20339Dependencies() {
                addDependency(myDeciderID_decider_17_0_2_2_edc0357_1357846886999_949065_20332, new Expression<Integer>(1));
                addDependency(addToDecider__17_0_2_2_edc0357_1357846887001_670940_20336, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886997_31914_20329_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886997_31914_20329_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(myDeciderID_decider_17_0_2_2_edc0357_1357846887001_670940_20336, new Expression<Integer>(1));
                addDependency(addToDecider__17_0_2_2_edc0357_1357846886999_949065_20332, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886997_31914_20329_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886997_31914_20329_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(_17_0_2_2_edc0357_1357846887001_670940_20336_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886997_31914_20329_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886997_31914_20329_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887001_670940_20336, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887001_670940_20336, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887001_670940_20336, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_2_edc0357_1357846887001_670940_20336)))))))));
                addDependency(_17_0_2_2_edc0357_1357846886999_949065_20332_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886997_31914_20329_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886997_31914_20329_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846886999_949065_20332, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846886999_949065_20332, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846886999_949065_20332, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_2_edc0357_1357846886999_949065_20332)))))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887006_611151_20358, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_2_edc0357_1357846887003_521609_20339Elaborations() {
                init_17_0_2_2_edc0357_1357846887003_521609_20339Dependencies();
                Expression<?>[] arguments107 = new Expression<?>[1];
                arguments107[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition107 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887001_670940_20336_exists);
                elaborationRule107 = addElaborationRule(condition107, _17_0_2_2_edc0357_1357846886590_580669_19880.this, Customer._17_0_2_2_edc0357_1357846886590_580669_19880._17_0_2_2_edc0357_1357846887001_670940_20336.class, "CChange_calc_desired_CallBehaviorAction_changePowerUsage", arguments107);
                Expression<?>[] arguments108 = new Expression<?>[1];
                arguments108[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition108 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846886999_949065_20332_exists);
                elaborationRule108 = addElaborationRule(condition108, _17_0_2_2_edc0357_1357846886590_580669_19880.this, Customer._17_0_2_2_edc0357_1357846886590_580669_19880._17_0_2_2_edc0357_1357846886999_949065_20332.class, "CChange_calc_capmin_CallBehaviorAction_changePowerUsage", arguments108);
            }
        }

        public class _17_0_2_2_edc0357_1357846887003_903670_20340 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846887003_903670_20340() {
                super();
                init_17_0_2_2_edc0357_1357846887003_903670_20340Members();
                init_17_0_2_2_edc0357_1357846887003_903670_20340Collections();
                init_17_0_2_2_edc0357_1357846887003_903670_20340Elaborations();
            }

            public _17_0_2_2_edc0357_1357846887003_903670_20340(Expression<Integer> startTime, Expression<ObjectFlow<Integer>> receiveThis) {
                super();
                init_17_0_2_2_edc0357_1357846887003_903670_20340Members();
                init_17_0_2_2_edc0357_1357846887003_903670_20340Collections();
                addDependency(this.startTime, startTime);
                addDependency(this.receiveThis, receiveThis);
                init_17_0_2_2_edc0357_1357846887003_903670_20340Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_2_edc0357_1357846887000_897383_20334_exists = null;

            public Parameter< ObjectFlow<Integer> > receiveThis = null;

            public IntegerParameter objectToPass = null;

            public Dependency _17_0_2_2_edc0357_1357846887000_897383_20334_existsDependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Effect effect109 = null;

            public Parameter effect109Var = null;

            public ElaborationRule elaborationRule110 = null;

            public void init_17_0_2_2_edc0357_1357846887003_903670_20340Members() {
                try {
                    if (_17_0_2_2_edc0357_1357846887000_897383_20334_exists == null) _17_0_2_2_edc0357_1357846887000_897383_20334_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887000_897383_20334_exists", (Boolean) false, this);
                    if (receiveThis == null) receiveThis = new Parameter<ObjectFlow<Integer>>("receiveThis", null, (ObjectFlow<Integer>) null, this);
                    if (objectToPass == null) objectToPass = new IntegerParameter("objectToPass", (Integer) null, this);
                    Object effect109VarV = sig_17_0_2_2_edc0357_1357846887006_887428_20363;
                    effect109Var = new Parameter("effect109Var", null, null, this);
                    addDependency(effect109Var, new Expression(effect109VarV));
                    effect109 = new EffectFunction(new EffectFunction(effect109Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846887003_903670_20340Collections() {
                parameters.add(_17_0_2_2_edc0357_1357846887000_897383_20334_exists);
                parameters.add(receiveThis);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect109Var = new TreeSet<Effect>();
                effectsForeffect109Var.add(effect109);
                addEffects((Parameter<?>) effect109Var, effectsForeffect109Var);
            }

            public void init_17_0_2_2_edc0357_1357846887003_903670_20340Dependencies() {
                addDependency(_17_0_2_2_edc0357_1357846887000_897383_20334_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886997_31914_20329_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886997_31914_20329_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression(new EffectFunction(receiveThis, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_2_edc0357_1357846887003_903670_20340Elaborations() {
                init_17_0_2_2_edc0357_1357846887003_903670_20340Dependencies();
                Expression<?>[] arguments110 = new Expression<?>[1];
                arguments110[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition110 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887000_897383_20334_exists);
                elaborationRule110 = addElaborationRule(condition110, _17_0_2_2_edc0357_1357846886590_580669_19880.this, Customer._17_0_2_2_edc0357_1357846886590_580669_19880._17_0_2_2_edc0357_1357846887000_897383_20334.class, "CChange_forkchangeto_ForkNode_changePowerUsage", arguments110);
            }
        }

        public Parameter< TimeVaryingList<Integer> > decider_17_0_2_2_edc0357_1357846886999_949065_20332 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_2_edc0357_1357846887006_938149_20357 = null;

        public Parameter< DurativeEvent > caller = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_2_2_edc0357_1357846887005_828482_20355 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_2_edc0357_1357846887005_556108_20356 = null;

        public Parameter< TimeVaryingList<Integer> > decider_17_0_2_2_edc0357_1357846886999_888577_20333 = null;

        public Parameter< ObjectFlow<Customer> > sig_17_0_2_2_edc0357_1357846887004_21611_20344 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_2_2_edc0357_1357846887005_907232_20354 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_2_2_edc0357_1357846887004_444568_20347 = null;

        public BooleanParameter _17_0_2_2_edc0357_1357846886997_31914_20329_exists = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_2_edc0357_1357846887005_653176_20350 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_2_edc0357_1357846887005_288480_20348 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_2_edc0357_1357846887004_40735_20345 = null;

        public Parameter< TimeVaryingList<Integer> > decider_17_0_2_2_edc0357_1357846886996_671670_20327 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_2_edc0357_1357846887005_830787_20349 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_2_2_edc0357_1357846887005_951410_20353 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_2_2_edc0357_1357846887006_433864_20360 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_2_2_edc0357_1357846887006_611151_20358 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_2_2_edc0357_1357846887004_499762_20346 = null;

        public Parameter< ObjectFlow<Customer> > sig_17_0_2_2_edc0357_1357846887004_211618_20341 = null;

        public IntegerParameter finalNode_endTime = null;

        public Parameter< ObjectFlow<Customer> > sig_17_0_2_2_edc0357_1357846887004_475504_20343 = null;

        public Parameter< TimeVaryingList<Integer> > decider_17_0_2_2_edc0357_1357846887002_996884_20338 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_2_2_edc0357_1357846887006_587764_20359 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_2_2_edc0357_1357846887445_270658_21383 = null;

        public IntegerParameter finalNode_startTime = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_2_2_edc0357_1357846887006_847328_20361 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_2_edc0357_1357846887005_721935_20351 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_2_2_edc0357_1357846887445_887380_21382 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_2_2_edc0357_1357846887006_249010_20362 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_2_2_edc0357_1357846887006_887428_20363 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_2_edc0357_1357846887005_267810_20352 = null;

        public Parameter< TimeVaryingList<Integer> > decider_17_0_2_2_edc0357_1357846887001_670940_20336 = null;

        public Parameter< ObjectFlow<Customer> > sig_17_0_2_2_edc0357_1357846887004_581837_20342 = null;

        public Dependency caller_endTimeDependency = null;

        public Dependency endTimeDependency = null;

        public Dependency _17_0_2_2_edc0357_1357846886997_31914_20329_existsDependency = null;

        public ElaborationRule elaborationRule49 = null;

        public ElaborationRule elaborationRule50 = null;

        public void init_17_0_2_2_edc0357_1357846886590_580669_19880Members() {
            try {
                if (decider_17_0_2_2_edc0357_1357846886999_949065_20332 == null) decider_17_0_2_2_edc0357_1357846886999_949065_20332 = new Parameter<TimeVaryingList<Integer>>("decider_17_0_2_2_edc0357_1357846886999_949065_20332", null, (TimeVaryingList<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingList.class, java.lang.String.class, int.class), new Object[] { "decider_17_0_2_2_edc0357_1357846886999_949065_20332", 3 })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846887006_938149_20357 == null) sig_17_0_2_2_edc0357_1357846887006_938149_20357 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_2_edc0357_1357846887006_938149_20357", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887006_938149_20357" })).evaluate(true), this);
                if (caller == null) caller = new Parameter<DurativeEvent>("caller", null, (DurativeEvent) null, this);
                if (sig_17_0_2_2_edc0357_1357846887005_828482_20355 == null) sig_17_0_2_2_edc0357_1357846887005_828482_20355 = new Parameter<ObjectFlow<Integer>>("sig_17_0_2_2_edc0357_1357846887005_828482_20355", null, (ObjectFlow<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887005_828482_20355" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846887005_556108_20356 == null) sig_17_0_2_2_edc0357_1357846887005_556108_20356 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_2_edc0357_1357846887005_556108_20356", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887005_556108_20356" })).evaluate(true), this);
                if (decider_17_0_2_2_edc0357_1357846886999_888577_20333 == null) decider_17_0_2_2_edc0357_1357846886999_888577_20333 = new Parameter<TimeVaryingList<Integer>>("decider_17_0_2_2_edc0357_1357846886999_888577_20333", null, (TimeVaryingList<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingList.class, java.lang.String.class, int.class), new Object[] { "decider_17_0_2_2_edc0357_1357846886999_888577_20333", 3 })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846887004_21611_20344 == null) sig_17_0_2_2_edc0357_1357846887004_21611_20344 = new Parameter<ObjectFlow<Customer>>("sig_17_0_2_2_edc0357_1357846887004_21611_20344", null, (ObjectFlow<Customer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887004_21611_20344" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846887005_907232_20354 == null) sig_17_0_2_2_edc0357_1357846887005_907232_20354 = new Parameter<ObjectFlow<Integer>>("sig_17_0_2_2_edc0357_1357846887005_907232_20354", null, (ObjectFlow<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887005_907232_20354" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846887004_444568_20347 == null) sig_17_0_2_2_edc0357_1357846887004_444568_20347 = new Parameter<ObjectFlow<Integer>>("sig_17_0_2_2_edc0357_1357846887004_444568_20347", null, (ObjectFlow<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887004_444568_20347" })).evaluate(true), this);
                if (_17_0_2_2_edc0357_1357846886997_31914_20329_exists == null) _17_0_2_2_edc0357_1357846886997_31914_20329_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846886997_31914_20329_exists", (Boolean) false, this);
                if (sig_17_0_2_2_edc0357_1357846887005_653176_20350 == null) sig_17_0_2_2_edc0357_1357846887005_653176_20350 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_2_edc0357_1357846887005_653176_20350", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887005_653176_20350" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846887005_288480_20348 == null) sig_17_0_2_2_edc0357_1357846887005_288480_20348 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_2_edc0357_1357846887005_288480_20348", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887005_288480_20348" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846887004_40735_20345 == null) sig_17_0_2_2_edc0357_1357846887004_40735_20345 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_2_edc0357_1357846887004_40735_20345", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887004_40735_20345" })).evaluate(true), this);
                if (decider_17_0_2_2_edc0357_1357846886996_671670_20327 == null) decider_17_0_2_2_edc0357_1357846886996_671670_20327 = new Parameter<TimeVaryingList<Integer>>("decider_17_0_2_2_edc0357_1357846886996_671670_20327", null, (TimeVaryingList<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingList.class, java.lang.String.class, int.class), new Object[] { "decider_17_0_2_2_edc0357_1357846886996_671670_20327", 2 })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846887005_830787_20349 == null) sig_17_0_2_2_edc0357_1357846887005_830787_20349 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_2_edc0357_1357846887005_830787_20349", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887005_830787_20349" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846887005_951410_20353 == null) sig_17_0_2_2_edc0357_1357846887005_951410_20353 = new Parameter<ObjectFlow<Integer>>("sig_17_0_2_2_edc0357_1357846887005_951410_20353", null, (ObjectFlow<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887005_951410_20353" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846887006_433864_20360 == null) sig_17_0_2_2_edc0357_1357846887006_433864_20360 = new Parameter<ObjectFlow<Integer>>("sig_17_0_2_2_edc0357_1357846887006_433864_20360", null, (ObjectFlow<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887006_433864_20360" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846887006_611151_20358 == null) sig_17_0_2_2_edc0357_1357846887006_611151_20358 = new Parameter<ObjectFlow<Integer>>("sig_17_0_2_2_edc0357_1357846887006_611151_20358", null, (ObjectFlow<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887006_611151_20358" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846887004_499762_20346 == null) sig_17_0_2_2_edc0357_1357846887004_499762_20346 = new Parameter<ObjectFlow<Integer>>("sig_17_0_2_2_edc0357_1357846887004_499762_20346", null, (ObjectFlow<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887004_499762_20346" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846887004_211618_20341 == null) sig_17_0_2_2_edc0357_1357846887004_211618_20341 = new Parameter<ObjectFlow<Customer>>("sig_17_0_2_2_edc0357_1357846887004_211618_20341", null, (ObjectFlow<Customer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887004_211618_20341" })).evaluate(true), this);
                if (finalNode_endTime == null) finalNode_endTime = new IntegerParameter("finalNode_endTime", (Integer) null, this);
                if (sig_17_0_2_2_edc0357_1357846887004_475504_20343 == null) sig_17_0_2_2_edc0357_1357846887004_475504_20343 = new Parameter<ObjectFlow<Customer>>("sig_17_0_2_2_edc0357_1357846887004_475504_20343", null, (ObjectFlow<Customer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887004_475504_20343" })).evaluate(true), this);
                if (decider_17_0_2_2_edc0357_1357846887002_996884_20338 == null) decider_17_0_2_2_edc0357_1357846887002_996884_20338 = new Parameter<TimeVaryingList<Integer>>("decider_17_0_2_2_edc0357_1357846887002_996884_20338", null, (TimeVaryingList<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingList.class, java.lang.String.class, int.class), new Object[] { "decider_17_0_2_2_edc0357_1357846887002_996884_20338", 2 })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846887006_587764_20359 == null) sig_17_0_2_2_edc0357_1357846887006_587764_20359 = new Parameter<ObjectFlow<Integer>>("sig_17_0_2_2_edc0357_1357846887006_587764_20359", null, (ObjectFlow<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887006_587764_20359" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846887445_270658_21383 == null) sig_17_0_2_2_edc0357_1357846887445_270658_21383 = new Parameter<ObjectFlow<Integer>>("sig_17_0_2_2_edc0357_1357846887445_270658_21383", null, (ObjectFlow<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887445_270658_21383" })).evaluate(true), this);
                if (finalNode_startTime == null) finalNode_startTime = new IntegerParameter("finalNode_startTime", (Integer) null, this);
                if (sig_17_0_2_2_edc0357_1357846887006_847328_20361 == null) sig_17_0_2_2_edc0357_1357846887006_847328_20361 = new Parameter<ObjectFlow<Integer>>("sig_17_0_2_2_edc0357_1357846887006_847328_20361", null, (ObjectFlow<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887006_847328_20361" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846887005_721935_20351 == null) sig_17_0_2_2_edc0357_1357846887005_721935_20351 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_2_edc0357_1357846887005_721935_20351", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887005_721935_20351" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846887445_887380_21382 == null) sig_17_0_2_2_edc0357_1357846887445_887380_21382 = new Parameter<ObjectFlow<Integer>>("sig_17_0_2_2_edc0357_1357846887445_887380_21382", null, (ObjectFlow<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887445_887380_21382" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846887006_249010_20362 == null) sig_17_0_2_2_edc0357_1357846887006_249010_20362 = new Parameter<ObjectFlow<Integer>>("sig_17_0_2_2_edc0357_1357846887006_249010_20362", null, (ObjectFlow<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887006_249010_20362" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846887006_887428_20363 == null) sig_17_0_2_2_edc0357_1357846887006_887428_20363 = new Parameter<ObjectFlow<Integer>>("sig_17_0_2_2_edc0357_1357846887006_887428_20363", null, (ObjectFlow<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887006_887428_20363" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846887005_267810_20352 == null) sig_17_0_2_2_edc0357_1357846887005_267810_20352 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_2_edc0357_1357846887005_267810_20352", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887005_267810_20352" })).evaluate(true), this);
                if (decider_17_0_2_2_edc0357_1357846887001_670940_20336 == null) decider_17_0_2_2_edc0357_1357846887001_670940_20336 = new Parameter<TimeVaryingList<Integer>>("decider_17_0_2_2_edc0357_1357846887001_670940_20336", null, (TimeVaryingList<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingList.class, java.lang.String.class, int.class), new Object[] { "decider_17_0_2_2_edc0357_1357846887001_670940_20336", 2 })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846887004_581837_20342 == null) sig_17_0_2_2_edc0357_1357846887004_581837_20342 = new Parameter<ObjectFlow<Customer>>("sig_17_0_2_2_edc0357_1357846887004_581837_20342", null, (ObjectFlow<Customer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887004_581837_20342" })).evaluate(true), this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void init_17_0_2_2_edc0357_1357846886590_580669_19880Collections() {
            parameters.add(decider_17_0_2_2_edc0357_1357846886999_949065_20332);
            parameters.add(sig_17_0_2_2_edc0357_1357846887006_938149_20357);
            parameters.add(caller);
            parameters.add(sig_17_0_2_2_edc0357_1357846887005_828482_20355);
            parameters.add(sig_17_0_2_2_edc0357_1357846887005_556108_20356);
            parameters.add(decider_17_0_2_2_edc0357_1357846886999_888577_20333);
            parameters.add(sig_17_0_2_2_edc0357_1357846887004_21611_20344);
            parameters.add(sig_17_0_2_2_edc0357_1357846887005_907232_20354);
            parameters.add(sig_17_0_2_2_edc0357_1357846887004_444568_20347);
            parameters.add(_17_0_2_2_edc0357_1357846886997_31914_20329_exists);
            parameters.add(sig_17_0_2_2_edc0357_1357846887005_653176_20350);
            parameters.add(sig_17_0_2_2_edc0357_1357846887005_288480_20348);
            parameters.add(sig_17_0_2_2_edc0357_1357846887004_40735_20345);
            parameters.add(decider_17_0_2_2_edc0357_1357846886996_671670_20327);
            parameters.add(sig_17_0_2_2_edc0357_1357846887005_830787_20349);
            parameters.add(sig_17_0_2_2_edc0357_1357846887005_951410_20353);
            parameters.add(sig_17_0_2_2_edc0357_1357846887006_433864_20360);
            parameters.add(sig_17_0_2_2_edc0357_1357846887006_611151_20358);
            parameters.add(sig_17_0_2_2_edc0357_1357846887004_499762_20346);
            parameters.add(sig_17_0_2_2_edc0357_1357846887004_211618_20341);
            parameters.add(finalNode_endTime);
            parameters.add(sig_17_0_2_2_edc0357_1357846887004_475504_20343);
            parameters.add(decider_17_0_2_2_edc0357_1357846887002_996884_20338);
            parameters.add(sig_17_0_2_2_edc0357_1357846887006_587764_20359);
            parameters.add(sig_17_0_2_2_edc0357_1357846887445_270658_21383);
            parameters.add(finalNode_startTime);
            parameters.add(sig_17_0_2_2_edc0357_1357846887006_847328_20361);
            parameters.add(sig_17_0_2_2_edc0357_1357846887005_721935_20351);
            parameters.add(sig_17_0_2_2_edc0357_1357846887445_887380_21382);
            parameters.add(sig_17_0_2_2_edc0357_1357846887006_249010_20362);
            parameters.add(sig_17_0_2_2_edc0357_1357846887006_887428_20363);
            parameters.add(sig_17_0_2_2_edc0357_1357846887005_267810_20352);
            parameters.add(decider_17_0_2_2_edc0357_1357846887001_670940_20336);
            parameters.add(sig_17_0_2_2_edc0357_1357846887004_581837_20342);
        }

        public void init_17_0_2_2_edc0357_1357846886590_580669_19880Dependencies() {
            caller.getValue(true).addDependency(caller.getValue(true).endTime, new Expression<Integer>(finalNode_endTime));
            addDependency(endTime, new Expression<Integer>(finalNode_endTime));
            addDependency(_17_0_2_2_edc0357_1357846886997_31914_20329_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.NotEquals(new Expression<Integer>(finalNode_startTime), null)), new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887004_40735_20345, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(new Functions.Minus(new Expression<Integer>(finalNode_startTime), new Expression<Integer>(1))) })))));
        }

        public void init_17_0_2_2_edc0357_1357846886590_580669_19880Elaborations() {
            init_17_0_2_2_edc0357_1357846886590_580669_19880Dependencies();
            Expression<?>[] arguments49 = new Expression<?>[1];
            arguments49[0] = new Expression<Integer>(finalNode_startTime);
            Expression<Boolean> condition49 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846886997_31914_20329_exists);
            elaborationRule49 = addElaborationRule(condition49, _17_0_2_2_edc0357_1357846886590_580669_19880.this, Customer._17_0_2_2_edc0357_1357846886590_580669_19880._17_0_2_2_edc0357_1357846886997_31914_20329.class, "CChange_end_ActivityFinalNode_changePowerUsage", arguments49);
            Expression<?>[] arguments50 = new Expression<?>[1];
            arguments50[0] = new Expression<Integer>(startTime);
            Expression<Boolean> condition50 = new Expression<Boolean>(true);
            elaborationRule50 = addElaborationRule(condition50, _17_0_2_2_edc0357_1357846886590_580669_19880.this, Customer._17_0_2_2_edc0357_1357846886590_580669_19880._17_0_2_2_edc0357_1357846886997_606321_20328.class, "CChange_start_InitialNode_changePowerUsage", arguments50);
        }
    }

    public class _17_0_2_2_edc0357_1357846886591_876034_19881 extends DurativeEvent {

        public _17_0_2_2_edc0357_1357846886591_876034_19881() {
            super();
            init_17_0_2_2_edc0357_1357846886591_876034_19881Members();
            init_17_0_2_2_edc0357_1357846886591_876034_19881Collections();
            init_17_0_2_2_edc0357_1357846886591_876034_19881Elaborations();
        }

        public class _17_0_2_2_edc0357_1357846887019_270831_20385 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846887019_270831_20385() {
                super();
                init_17_0_2_2_edc0357_1357846887019_270831_20385Members();
                init_17_0_2_2_edc0357_1357846887019_270831_20385Collections();
                init_17_0_2_2_edc0357_1357846887019_270831_20385Elaborations();
            }

            public _17_0_2_2_edc0357_1357846887019_270831_20385(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846887019_270831_20385Members();
                init_17_0_2_2_edc0357_1357846887019_270831_20385Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846887019_270831_20385Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_2_edc0357_1357846887022_816332_20390_exists = null;

            public Parameter< Customer > _17_0_2_2_edc0357_1357846887460_818388_21425 = null;

            public BooleanParameter objectToPass = null;

            public Dependency _17_0_2_2_edc0357_1357846887022_816332_20390_existsDependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Effect effect113 = null;

            public Parameter effect113Var = null;

            public ElaborationRule elaborationRule114 = null;

            public void init_17_0_2_2_edc0357_1357846887019_270831_20385Members() {
                try {
                    if (_17_0_2_2_edc0357_1357846887022_816332_20390_exists == null) _17_0_2_2_edc0357_1357846887022_816332_20390_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887022_816332_20390_exists", (Boolean) false, this);
                    if (_17_0_2_2_edc0357_1357846887460_818388_21425 == null) _17_0_2_2_edc0357_1357846887460_818388_21425 = new Parameter<Customer>("_17_0_2_2_edc0357_1357846887460_818388_21425", null, (Customer) Customer.this, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect113VarV = sig_17_0_2_2_edc0357_1357846887024_394807_20396;
                    effect113Var = new Parameter("effect113Var", null, null, this);
                    addDependency(effect113Var, new Expression(effect113VarV));
                    effect113 = new EffectFunction(new EffectFunction(effect113Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { _17_0_2_2_edc0357_1357846887460_818388_21425, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846887019_270831_20385Collections() {
                parameters.add(_17_0_2_2_edc0357_1357846887022_816332_20390_exists);
                parameters.add(_17_0_2_2_edc0357_1357846887460_818388_21425);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect113Var = new TreeSet<Effect>();
                effectsForeffect113Var.add(effect113);
                addEffects((Parameter<?>) effect113Var, effectsForeffect113Var);
            }

            public void init_17_0_2_2_edc0357_1357846887019_270831_20385Dependencies() {
                addDependency(_17_0_2_2_edc0357_1357846887022_816332_20390_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887021_860360_20389_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887021_860360_20389_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887025_483718_20404, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_2_edc0357_1357846887019_270831_20385Elaborations() {
                init_17_0_2_2_edc0357_1357846887019_270831_20385Dependencies();
                Expression<?>[] arguments114 = new Expression<?>[1];
                arguments114[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition114 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887022_816332_20390_exists);
                elaborationRule114 = addElaborationRule(condition114, _17_0_2_2_edc0357_1357846886591_876034_19881.this, Customer._17_0_2_2_edc0357_1357846886591_876034_19881._17_0_2_2_edc0357_1357846887022_816332_20390.class, "CI_forkself_ForkNode_initialize", arguments114);
            }
        }

        public class _17_0_2_2_edc0357_1357846887020_953530_20386 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846887020_953530_20386() {
                super();
                init_17_0_2_2_edc0357_1357846887020_953530_20386Members();
                init_17_0_2_2_edc0357_1357846887020_953530_20386Collections();
                init_17_0_2_2_edc0357_1357846887020_953530_20386Elaborations();
            }

            public _17_0_2_2_edc0357_1357846887020_953530_20386(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846887020_953530_20386Members();
                init_17_0_2_2_edc0357_1357846887020_953530_20386Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846887020_953530_20386Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_2_edc0357_1357846887020_273659_20387_exists = null;

            public BooleanParameter addToDecider__17_0_2_2_edc0357_1357846887020_273659_20387 = null;

            public BooleanParameter objectToPass = null;

            public IntegerParameter _17_0_2_2_edc0357_1357846887460_175647_21427 = null;

            public IntegerParameter myDeciderID_decider_17_0_2_2_edc0357_1357846887020_273659_20387 = null;

            public Dependency _17_0_2_2_edc0357_1357846887020_273659_20387_existsDependency = null;

            public Dependency addToDecider__17_0_2_2_edc0357_1357846887020_273659_20387Dependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887460_175647_21427Dependency = null;

            public Dependency myDeciderID_decider_17_0_2_2_edc0357_1357846887020_273659_20387Dependency = null;

            public Effect effect115 = null;

            public Parameter effect115Var = null;

            public Effect effect116 = null;

            public Parameter effect116Var = null;

            public ElaborationRule elaborationRule117 = null;

            public void init_17_0_2_2_edc0357_1357846887020_953530_20386Members() {
                try {
                    if (_17_0_2_2_edc0357_1357846887020_273659_20387_exists == null) _17_0_2_2_edc0357_1357846887020_273659_20387_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887020_273659_20387_exists", (Boolean) false, this);
                    if (addToDecider__17_0_2_2_edc0357_1357846887020_273659_20387 == null) addToDecider__17_0_2_2_edc0357_1357846887020_273659_20387 = new BooleanParameter("addToDecider__17_0_2_2_edc0357_1357846887020_273659_20387", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (_17_0_2_2_edc0357_1357846887460_175647_21427 == null) _17_0_2_2_edc0357_1357846887460_175647_21427 = new IntegerParameter("_17_0_2_2_edc0357_1357846887460_175647_21427", (Integer) 1, this);
                    if (myDeciderID_decider_17_0_2_2_edc0357_1357846887020_273659_20387 == null) myDeciderID_decider_17_0_2_2_edc0357_1357846887020_273659_20387 = new IntegerParameter("myDeciderID_decider_17_0_2_2_edc0357_1357846887020_273659_20387", (Integer) 2, this);
                    Object effect115VarV = sig_17_0_2_2_edc0357_1357846887024_593289_20395;
                    effect115Var = new Parameter("effect115Var", null, null, this);
                    addDependency(effect115Var, new Expression(effect115VarV));
                    effect115 = new EffectFunction(new EffectFunction(effect115Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { _17_0_2_2_edc0357_1357846887460_175647_21427, endTime }));
                    Object effect116VarV = decider_17_0_2_2_edc0357_1357846887020_273659_20387;
                    effect116Var = new Parameter("effect116Var", null, null, this);
                    addDependency(effect116Var, new Expression(effect116VarV));
                    effect116 = new EffectFunction(new EffectFunction(effect116Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class, java.lang.Boolean.class), new Object[] { endTime, myDeciderID_decider_17_0_2_2_edc0357_1357846887020_273659_20387, addToDecider__17_0_2_2_edc0357_1357846887020_273659_20387 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846887020_953530_20386Collections() {
                parameters.add(_17_0_2_2_edc0357_1357846887020_273659_20387_exists);
                parameters.add(addToDecider__17_0_2_2_edc0357_1357846887020_273659_20387);
                parameters.add(objectToPass);
                parameters.add(_17_0_2_2_edc0357_1357846887460_175647_21427);
                parameters.add(myDeciderID_decider_17_0_2_2_edc0357_1357846887020_273659_20387);
                Set<Effect> effectsForeffect115Var = new TreeSet<Effect>();
                effectsForeffect115Var.add(effect115);
                addEffects((Parameter<?>) effect115Var, effectsForeffect115Var);
                Set<Effect> effectsForeffect116Var = new TreeSet<Effect>();
                effectsForeffect116Var.add(effect116);
                addEffects((Parameter<?>) effect116Var, effectsForeffect116Var);
            }

            public void init_17_0_2_2_edc0357_1357846887020_953530_20386Dependencies() {
                addDependency(_17_0_2_2_edc0357_1357846887020_273659_20387_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887021_860360_20389_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887021_860360_20389_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887020_273659_20387, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887020_273659_20387, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887020_273659_20387, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_2_edc0357_1357846887020_273659_20387)))))))));
                addDependency(addToDecider__17_0_2_2_edc0357_1357846887020_273659_20387, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887021_860360_20389_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887021_860360_20389_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887025_577843_20405, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_2_edc0357_1357846887460_175647_21427, new Expression<Integer>(1));
                addDependency(myDeciderID_decider_17_0_2_2_edc0357_1357846887020_273659_20387, new Expression<Integer>(2));
            }

            public void init_17_0_2_2_edc0357_1357846887020_953530_20386Elaborations() {
                init_17_0_2_2_edc0357_1357846887020_953530_20386Dependencies();
                Expression<?>[] arguments117 = new Expression<?>[1];
                arguments117[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition117 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887020_273659_20387_exists);
                elaborationRule117 = addElaborationRule(condition117, _17_0_2_2_edc0357_1357846886591_876034_19881.this, Customer._17_0_2_2_edc0357_1357846886591_876034_19881._17_0_2_2_edc0357_1357846887020_273659_20387.class, "CI_setinitusage_AddStructuralFeatureValueAction_initialize", arguments117);
            }
        }

        public class _17_0_2_2_edc0357_1357846887020_273659_20387 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846887020_273659_20387() {
                super();
                init_17_0_2_2_edc0357_1357846887020_273659_20387Members();
                init_17_0_2_2_edc0357_1357846887020_273659_20387Collections();
                init_17_0_2_2_edc0357_1357846887020_273659_20387Elaborations();
            }

            public _17_0_2_2_edc0357_1357846887020_273659_20387(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846887020_273659_20387Members();
                init_17_0_2_2_edc0357_1357846887020_273659_20387Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846887020_273659_20387Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter addToDecider__17_0_2_2_edc0357_1357846887023_679302_20393 = null;

            public IntegerParameter myDeciderID_decider_17_0_2_2_edc0357_1357846887023_679302_20393 = null;

            public IntegerParameter _17_0_2_2_edc0357_1357846887461_386640_21428 = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846887023_679302_20393_exists = null;

            public BooleanParameter objectToPass = null;

            public Parameter< Customer > _17_0_2_2_edc0357_1357846887461_293297_21429 = null;

            public Dependency addToDecider__17_0_2_2_edc0357_1357846887023_679302_20393Dependency = null;

            public Dependency myDeciderID_decider_17_0_2_2_edc0357_1357846887023_679302_20393Dependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887461_386640_21428Dependency = null;

            public Dependency durationDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887023_679302_20393_existsDependency = null;

            public Dependency objectToPassDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887461_293297_21429Dependency = null;

            public Effect effect118 = null;

            public Parameter effect118Var = null;

            public Effect effect119 = null;

            public Parameter effect119Var = null;

            public Effect effect120 = null;

            public Parameter effect120Var = null;

            public ElaborationRule elaborationRule121 = null;

            public void init_17_0_2_2_edc0357_1357846887020_273659_20387Members() {
                try {
                    if (addToDecider__17_0_2_2_edc0357_1357846887023_679302_20393 == null) addToDecider__17_0_2_2_edc0357_1357846887023_679302_20393 = new BooleanParameter("addToDecider__17_0_2_2_edc0357_1357846887023_679302_20393", (Boolean) false, this);
                    if (myDeciderID_decider_17_0_2_2_edc0357_1357846887023_679302_20393 == null) myDeciderID_decider_17_0_2_2_edc0357_1357846887023_679302_20393 = new IntegerParameter("myDeciderID_decider_17_0_2_2_edc0357_1357846887023_679302_20393", (Integer) 1, this);
                    if (_17_0_2_2_edc0357_1357846887461_386640_21428 == null) _17_0_2_2_edc0357_1357846887461_386640_21428 = new IntegerParameter("_17_0_2_2_edc0357_1357846887461_386640_21428", (Integer) null, this);
                    if (_17_0_2_2_edc0357_1357846887023_679302_20393_exists == null) _17_0_2_2_edc0357_1357846887023_679302_20393_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887023_679302_20393_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (_17_0_2_2_edc0357_1357846887461_293297_21429 == null) _17_0_2_2_edc0357_1357846887461_293297_21429 = new Parameter<Customer>("_17_0_2_2_edc0357_1357846887461_293297_21429", null, (Customer) null, this);
                    Object effect118VarV = sig_17_0_2_2_edc0357_1357846887025_932000_20400;
                    effect118Var = new Parameter("effect118Var", null, null, this);
                    addDependency(effect118Var, new Expression(effect118VarV));
                    effect118 = new EffectFunction(new EffectFunction(effect118Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect119VarV = decider_17_0_2_2_edc0357_1357846887023_679302_20393;
                    effect119Var = new Parameter("effect119Var", null, null, this);
                    addDependency(effect119Var, new Expression(effect119VarV));
                    effect119 = new EffectFunction(new EffectFunction(effect119Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class, java.lang.Boolean.class), new Object[] { endTime, myDeciderID_decider_17_0_2_2_edc0357_1357846887023_679302_20393, addToDecider__17_0_2_2_edc0357_1357846887023_679302_20393 }));
                    Object effect120VarV = usage__17_0_2_2_edc0357_1357846886595_378353_19885;
                    effect120Var = new Parameter("effect120Var", null, null, this);
                    addDependency(effect120Var, new Expression(effect120VarV));
                    effect120 = new EffectFunction(new EffectFunction(effect120Var, ClassUtils.getMethodForArgTypes("TimeVaryingPlottableMap<Integer>", "PowerSystem3", "setValue", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { startTime, _17_0_2_2_edc0357_1357846887461_386640_21428 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846887020_273659_20387Collections() {
                parameters.add(addToDecider__17_0_2_2_edc0357_1357846887023_679302_20393);
                parameters.add(myDeciderID_decider_17_0_2_2_edc0357_1357846887023_679302_20393);
                parameters.add(_17_0_2_2_edc0357_1357846887461_386640_21428);
                parameters.add(_17_0_2_2_edc0357_1357846887023_679302_20393_exists);
                parameters.add(objectToPass);
                parameters.add(_17_0_2_2_edc0357_1357846887461_293297_21429);
                Set<Effect> effectsForeffect118Var = new TreeSet<Effect>();
                effectsForeffect118Var.add(effect118);
                addEffects((Parameter<?>) effect118Var, effectsForeffect118Var);
                Set<Effect> effectsForeffect119Var = new TreeSet<Effect>();
                effectsForeffect119Var.add(effect119);
                addEffects((Parameter<?>) effect119Var, effectsForeffect119Var);
                Set<Effect> effectsForeffect120Var = new TreeSet<Effect>();
                effectsForeffect120Var.add(effect120);
                addEffects((Parameter<?>) effect120Var, effectsForeffect120Var);
            }

            public void init_17_0_2_2_edc0357_1357846887020_273659_20387Dependencies() {
                addDependency(addToDecider__17_0_2_2_edc0357_1357846887023_679302_20393, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887021_860360_20389_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887021_860360_20389_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(myDeciderID_decider_17_0_2_2_edc0357_1357846887023_679302_20393, new Expression<Integer>(1));
                addDependency(_17_0_2_2_edc0357_1357846887461_386640_21428, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887024_593289_20395, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(_17_0_2_2_edc0357_1357846887023_679302_20393_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887021_860360_20389_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887021_860360_20389_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887023_679302_20393, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887023_679302_20393, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887023_679302_20393, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_2_edc0357_1357846887023_679302_20393)))))))));
                addDependency(objectToPass, new Expression<Boolean>(true));
                addDependency(_17_0_2_2_edc0357_1357846887461_293297_21429, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887025_251987_20397, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_2_edc0357_1357846887020_273659_20387Elaborations() {
                init_17_0_2_2_edc0357_1357846887020_273659_20387Dependencies();
                Expression<?>[] arguments121 = new Expression<?>[1];
                arguments121[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition121 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887023_679302_20393_exists);
                elaborationRule121 = addElaborationRule(condition121, _17_0_2_2_edc0357_1357846886591_876034_19881.this, Customer._17_0_2_2_edc0357_1357846886591_876034_19881._17_0_2_2_edc0357_1357846887023_679302_20393.class, "CI_join_JoinNode_initialize", arguments121);
            }
        }

        public class _17_0_2_2_edc0357_1357846887021_642269_20388 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846887021_642269_20388() {
                super();
                init_17_0_2_2_edc0357_1357846887021_642269_20388Members();
                init_17_0_2_2_edc0357_1357846887021_642269_20388Collections();
                init_17_0_2_2_edc0357_1357846887021_642269_20388Elaborations();
            }

            public _17_0_2_2_edc0357_1357846887021_642269_20388(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846887021_642269_20388Members();
                init_17_0_2_2_edc0357_1357846887021_642269_20388Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846887021_642269_20388Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_2_edc0357_1357846887024_734228_20394_exists = null;

            public BooleanParameter objectToPass = null;

            public Dependency _17_0_2_2_edc0357_1357846887024_734228_20394_existsDependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Effect effect122 = null;

            public Parameter effect122Var = null;

            public ElaborationRule elaborationRule123 = null;

            public void init_17_0_2_2_edc0357_1357846887021_642269_20388Members() {
                try {
                    if (_17_0_2_2_edc0357_1357846887024_734228_20394_exists == null) _17_0_2_2_edc0357_1357846887024_734228_20394_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887024_734228_20394_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect122VarV = sig_17_0_2_2_edc0357_1357846887025_552926_20403;
                    effect122Var = new Parameter("effect122Var", null, null, this);
                    addDependency(effect122Var, new Expression(effect122VarV));
                    effect122 = new EffectFunction(new EffectFunction(effect122Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846887021_642269_20388Collections() {
                parameters.add(_17_0_2_2_edc0357_1357846887024_734228_20394_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect122Var = new TreeSet<Effect>();
                effectsForeffect122Var.add(effect122);
                addEffects((Parameter<?>) effect122Var, effectsForeffect122Var);
            }

            public void init_17_0_2_2_edc0357_1357846887021_642269_20388Dependencies() {
                addDependency(_17_0_2_2_edc0357_1357846887024_734228_20394_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887021_860360_20389_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887021_860360_20389_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(true));
            }

            public void init_17_0_2_2_edc0357_1357846887021_642269_20388Elaborations() {
                init_17_0_2_2_edc0357_1357846887021_642269_20388Dependencies();
                Expression<?>[] arguments123 = new Expression<?>[1];
                arguments123[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition123 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887024_734228_20394_exists);
                elaborationRule123 = addElaborationRule(condition123, _17_0_2_2_edc0357_1357846886591_876034_19881.this, Customer._17_0_2_2_edc0357_1357846886591_876034_19881._17_0_2_2_edc0357_1357846887024_734228_20394.class, "CI_fork1_ForkNode_initialize", arguments123);
            }
        }

        public class _17_0_2_2_edc0357_1357846887021_860360_20389 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846887021_860360_20389() {
                super();
                init_17_0_2_2_edc0357_1357846887021_860360_20389Members();
                init_17_0_2_2_edc0357_1357846887021_860360_20389Collections();
                init_17_0_2_2_edc0357_1357846887021_860360_20389Elaborations();
            }

            public _17_0_2_2_edc0357_1357846887021_860360_20389(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846887021_860360_20389Members();
                init_17_0_2_2_edc0357_1357846887021_860360_20389Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846887021_860360_20389Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter objectToPass = null;

            public Dependency endTimeDependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Dependency finalNode_endTimeDependency = null;

            public void init_17_0_2_2_edc0357_1357846887021_860360_20389Members() {
                try {
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846887021_860360_20389Collections() {
                parameters.add(objectToPass);
            }

            public void init_17_0_2_2_edc0357_1357846887021_860360_20389Dependencies() {
                addDependency(endTime, new Expression<Integer>(new Functions.Plus(new Expression<Integer>(startTime), new Expression<Integer>(duration))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887025_780529_20401, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(finalNode_endTime, new Expression<Integer>(endTime));
            }

            public void init_17_0_2_2_edc0357_1357846887021_860360_20389Elaborations() {
                init_17_0_2_2_edc0357_1357846887021_860360_20389Dependencies();
            }
        }

        public class _17_0_2_2_edc0357_1357846887022_816332_20390 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846887022_816332_20390() {
                super();
                init_17_0_2_2_edc0357_1357846887022_816332_20390Members();
                init_17_0_2_2_edc0357_1357846887022_816332_20390Collections();
                init_17_0_2_2_edc0357_1357846887022_816332_20390Elaborations();
            }

            public _17_0_2_2_edc0357_1357846887022_816332_20390(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846887022_816332_20390Members();
                init_17_0_2_2_edc0357_1357846887022_816332_20390Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846887022_816332_20390Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_2_edc0357_1357846887020_273659_20387_exists = null;

            public BooleanParameter addToDecider__17_0_2_2_edc0357_1357846887020_273659_20387 = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846887022_826257_20391_exists = null;

            public IntegerParameter myDeciderID_decider_17_0_2_2_edc0357_1357846887022_826257_20391 = null;

            public Parameter< Customer > objectToPass = null;

            public BooleanParameter addToDecider__17_0_2_2_edc0357_1357846887022_826257_20391 = null;

            public IntegerParameter myDeciderID_decider_17_0_2_2_edc0357_1357846887020_273659_20387 = null;

            public Dependency _17_0_2_2_edc0357_1357846887020_273659_20387_existsDependency = null;

            public Dependency addToDecider__17_0_2_2_edc0357_1357846887020_273659_20387Dependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887022_826257_20391_existsDependency = null;

            public Dependency durationDependency = null;

            public Dependency myDeciderID_decider_17_0_2_2_edc0357_1357846887022_826257_20391Dependency = null;

            public Dependency objectToPassDependency = null;

            public Dependency addToDecider__17_0_2_2_edc0357_1357846887022_826257_20391Dependency = null;

            public Dependency myDeciderID_decider_17_0_2_2_edc0357_1357846887020_273659_20387Dependency = null;

            public Effect effect124 = null;

            public Parameter effect124Var = null;

            public Effect effect125 = null;

            public Parameter effect125Var = null;

            public Effect effect126 = null;

            public Parameter effect126Var = null;

            public Effect effect127 = null;

            public Parameter effect127Var = null;

            public ElaborationRule elaborationRule128 = null;

            public ElaborationRule elaborationRule129 = null;

            public void init_17_0_2_2_edc0357_1357846887022_816332_20390Members() {
                try {
                    if (_17_0_2_2_edc0357_1357846887020_273659_20387_exists == null) _17_0_2_2_edc0357_1357846887020_273659_20387_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887020_273659_20387_exists", (Boolean) false, this);
                    if (addToDecider__17_0_2_2_edc0357_1357846887020_273659_20387 == null) addToDecider__17_0_2_2_edc0357_1357846887020_273659_20387 = new BooleanParameter("addToDecider__17_0_2_2_edc0357_1357846887020_273659_20387", (Boolean) false, this);
                    if (_17_0_2_2_edc0357_1357846887022_826257_20391_exists == null) _17_0_2_2_edc0357_1357846887022_826257_20391_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887022_826257_20391_exists", (Boolean) false, this);
                    if (myDeciderID_decider_17_0_2_2_edc0357_1357846887022_826257_20391 == null) myDeciderID_decider_17_0_2_2_edc0357_1357846887022_826257_20391 = new IntegerParameter("myDeciderID_decider_17_0_2_2_edc0357_1357846887022_826257_20391", (Integer) 1, this);
                    if (objectToPass == null) objectToPass = new Parameter<Customer>("objectToPass", null, (Customer) null, this);
                    if (addToDecider__17_0_2_2_edc0357_1357846887022_826257_20391 == null) addToDecider__17_0_2_2_edc0357_1357846887022_826257_20391 = new BooleanParameter("addToDecider__17_0_2_2_edc0357_1357846887022_826257_20391", (Boolean) false, this);
                    if (myDeciderID_decider_17_0_2_2_edc0357_1357846887020_273659_20387 == null) myDeciderID_decider_17_0_2_2_edc0357_1357846887020_273659_20387 = new IntegerParameter("myDeciderID_decider_17_0_2_2_edc0357_1357846887020_273659_20387", (Integer) 1, this);
                    Object effect124VarV = sig_17_0_2_2_edc0357_1357846887025_251987_20397;
                    effect124Var = new Parameter("effect124Var", null, null, this);
                    addDependency(effect124Var, new Expression(effect124VarV));
                    effect124 = new EffectFunction(new EffectFunction(effect124Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect125VarV = sig_17_0_2_2_edc0357_1357846887025_604759_20399;
                    effect125Var = new Parameter("effect125Var", null, null, this);
                    addDependency(effect125Var, new Expression(effect125VarV));
                    effect125 = new EffectFunction(new EffectFunction(effect125Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect126VarV = decider_17_0_2_2_edc0357_1357846887020_273659_20387;
                    effect126Var = new Parameter("effect126Var", null, null, this);
                    addDependency(effect126Var, new Expression(effect126VarV));
                    effect126 = new EffectFunction(new EffectFunction(effect126Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class, java.lang.Boolean.class), new Object[] { endTime, myDeciderID_decider_17_0_2_2_edc0357_1357846887020_273659_20387, addToDecider__17_0_2_2_edc0357_1357846887020_273659_20387 }));
                    Object effect127VarV = decider_17_0_2_2_edc0357_1357846887022_826257_20391;
                    effect127Var = new Parameter("effect127Var", null, null, this);
                    addDependency(effect127Var, new Expression(effect127VarV));
                    effect127 = new EffectFunction(new EffectFunction(effect127Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class, java.lang.Boolean.class), new Object[] { endTime, myDeciderID_decider_17_0_2_2_edc0357_1357846887022_826257_20391, addToDecider__17_0_2_2_edc0357_1357846887022_826257_20391 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846887022_816332_20390Collections() {
                parameters.add(_17_0_2_2_edc0357_1357846887020_273659_20387_exists);
                parameters.add(addToDecider__17_0_2_2_edc0357_1357846887020_273659_20387);
                parameters.add(_17_0_2_2_edc0357_1357846887022_826257_20391_exists);
                parameters.add(myDeciderID_decider_17_0_2_2_edc0357_1357846887022_826257_20391);
                parameters.add(objectToPass);
                parameters.add(addToDecider__17_0_2_2_edc0357_1357846887022_826257_20391);
                parameters.add(myDeciderID_decider_17_0_2_2_edc0357_1357846887020_273659_20387);
                Set<Effect> effectsForeffect124Var = new TreeSet<Effect>();
                effectsForeffect124Var.add(effect124);
                addEffects((Parameter<?>) effect124Var, effectsForeffect124Var);
                Set<Effect> effectsForeffect125Var = new TreeSet<Effect>();
                effectsForeffect125Var.add(effect125);
                addEffects((Parameter<?>) effect125Var, effectsForeffect125Var);
                Set<Effect> effectsForeffect126Var = new TreeSet<Effect>();
                effectsForeffect126Var.add(effect126);
                addEffects((Parameter<?>) effect126Var, effectsForeffect126Var);
                Set<Effect> effectsForeffect127Var = new TreeSet<Effect>();
                effectsForeffect127Var.add(effect127);
                addEffects((Parameter<?>) effect127Var, effectsForeffect127Var);
            }

            public void init_17_0_2_2_edc0357_1357846887022_816332_20390Dependencies() {
                addDependency(_17_0_2_2_edc0357_1357846887020_273659_20387_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887021_860360_20389_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887021_860360_20389_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887020_273659_20387, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887020_273659_20387, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887020_273659_20387, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_2_edc0357_1357846887020_273659_20387)))))))));
                addDependency(addToDecider__17_0_2_2_edc0357_1357846887020_273659_20387, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887021_860360_20389_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887021_860360_20389_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(_17_0_2_2_edc0357_1357846887022_826257_20391_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887021_860360_20389_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887021_860360_20389_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887022_826257_20391, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887022_826257_20391, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887022_826257_20391, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_2_edc0357_1357846887022_826257_20391)))))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(myDeciderID_decider_17_0_2_2_edc0357_1357846887022_826257_20391, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887024_394807_20396, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(addToDecider__17_0_2_2_edc0357_1357846887022_826257_20391, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887021_860360_20389_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887021_860360_20389_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(myDeciderID_decider_17_0_2_2_edc0357_1357846887020_273659_20387, new Expression<Integer>(1));
            }

            public void init_17_0_2_2_edc0357_1357846887022_816332_20390Elaborations() {
                init_17_0_2_2_edc0357_1357846887022_816332_20390Dependencies();
                Expression<?>[] arguments128 = new Expression<?>[1];
                arguments128[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition128 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887020_273659_20387_exists);
                elaborationRule128 = addElaborationRule(condition128, _17_0_2_2_edc0357_1357846886591_876034_19881.this, Customer._17_0_2_2_edc0357_1357846886591_876034_19881._17_0_2_2_edc0357_1357846887020_273659_20387.class, "CI_setinitusage_AddStructuralFeatureValueAction_initialize", arguments128);
                Expression<?>[] arguments129 = new Expression<?>[1];
                arguments129[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition129 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887022_826257_20391_exists);
                elaborationRule129 = addElaborationRule(condition129, _17_0_2_2_edc0357_1357846886591_876034_19881.this, Customer._17_0_2_2_edc0357_1357846886591_876034_19881._17_0_2_2_edc0357_1357846887022_826257_20391.class, "CI_setinit_cap_AddStructuralFeatureValueAction_initialize", arguments129);
            }
        }

        public class _17_0_2_2_edc0357_1357846887022_826257_20391 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846887022_826257_20391() {
                super();
                init_17_0_2_2_edc0357_1357846887022_826257_20391Members();
                init_17_0_2_2_edc0357_1357846887022_826257_20391Collections();
                init_17_0_2_2_edc0357_1357846887022_826257_20391Elaborations();
            }

            public _17_0_2_2_edc0357_1357846887022_826257_20391(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846887022_826257_20391Members();
                init_17_0_2_2_edc0357_1357846887022_826257_20391Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846887022_826257_20391Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter addToDecider__17_0_2_2_edc0357_1357846887023_679302_20393 = null;

            public IntegerParameter myDeciderID_decider_17_0_2_2_edc0357_1357846887023_679302_20393 = null;

            public Parameter< Customer > _17_0_2_2_edc0357_1357846887463_478783_21431 = null;

            public IntegerParameter _17_0_2_2_edc0357_1357846887462_95896_21430 = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846887023_679302_20393_exists = null;

            public BooleanParameter objectToPass = null;

            public Dependency addToDecider__17_0_2_2_edc0357_1357846887023_679302_20393Dependency = null;

            public Dependency myDeciderID_decider_17_0_2_2_edc0357_1357846887023_679302_20393Dependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887463_478783_21431Dependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887462_95896_21430Dependency = null;

            public Dependency durationDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887023_679302_20393_existsDependency = null;

            public Dependency objectToPassDependency = null;

            public Effect effect130 = null;

            public Parameter effect130Var = null;

            public Effect effect131 = null;

            public Parameter effect131Var = null;

            public Effect effect132 = null;

            public Parameter effect132Var = null;

            public ElaborationRule elaborationRule133 = null;

            public void init_17_0_2_2_edc0357_1357846887022_826257_20391Members() {
                try {
                    if (addToDecider__17_0_2_2_edc0357_1357846887023_679302_20393 == null) addToDecider__17_0_2_2_edc0357_1357846887023_679302_20393 = new BooleanParameter("addToDecider__17_0_2_2_edc0357_1357846887023_679302_20393", (Boolean) false, this);
                    if (myDeciderID_decider_17_0_2_2_edc0357_1357846887023_679302_20393 == null) myDeciderID_decider_17_0_2_2_edc0357_1357846887023_679302_20393 = new IntegerParameter("myDeciderID_decider_17_0_2_2_edc0357_1357846887023_679302_20393", (Integer) 2, this);
                    if (_17_0_2_2_edc0357_1357846887463_478783_21431 == null) _17_0_2_2_edc0357_1357846887463_478783_21431 = new Parameter<Customer>("_17_0_2_2_edc0357_1357846887463_478783_21431", null, (Customer) null, this);
                    if (_17_0_2_2_edc0357_1357846887462_95896_21430 == null) _17_0_2_2_edc0357_1357846887462_95896_21430 = new IntegerParameter("_17_0_2_2_edc0357_1357846887462_95896_21430", (Integer) null, this);
                    if (_17_0_2_2_edc0357_1357846887023_679302_20393_exists == null) _17_0_2_2_edc0357_1357846887023_679302_20393_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887023_679302_20393_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect130VarV = sig_17_0_2_2_edc0357_1357846887025_790450_20402;
                    effect130Var = new Parameter("effect130Var", null, null, this);
                    addDependency(effect130Var, new Expression(effect130VarV));
                    effect130 = new EffectFunction(new EffectFunction(effect130Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect131VarV = decider_17_0_2_2_edc0357_1357846887023_679302_20393;
                    effect131Var = new Parameter("effect131Var", null, null, this);
                    addDependency(effect131Var, new Expression(effect131VarV));
                    effect131 = new EffectFunction(new EffectFunction(effect131Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class, java.lang.Boolean.class), new Object[] { endTime, myDeciderID_decider_17_0_2_2_edc0357_1357846887023_679302_20393, addToDecider__17_0_2_2_edc0357_1357846887023_679302_20393 }));
                    Object effect132VarV = cap__17_0_2_2_edc0357_1357846886598_990111_19890;
                    effect132Var = new Parameter("effect132Var", null, null, this);
                    addDependency(effect132Var, new Expression(effect132VarV));
                    effect132 = new EffectFunction(new EffectFunction(effect132Var, ClassUtils.getMethodForArgTypes("TimeVaryingPlottableMap<Integer>", "PowerSystem3", "setValue", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { startTime, _17_0_2_2_edc0357_1357846887462_95896_21430 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846887022_826257_20391Collections() {
                parameters.add(addToDecider__17_0_2_2_edc0357_1357846887023_679302_20393);
                parameters.add(myDeciderID_decider_17_0_2_2_edc0357_1357846887023_679302_20393);
                parameters.add(_17_0_2_2_edc0357_1357846887463_478783_21431);
                parameters.add(_17_0_2_2_edc0357_1357846887462_95896_21430);
                parameters.add(_17_0_2_2_edc0357_1357846887023_679302_20393_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect130Var = new TreeSet<Effect>();
                effectsForeffect130Var.add(effect130);
                addEffects((Parameter<?>) effect130Var, effectsForeffect130Var);
                Set<Effect> effectsForeffect131Var = new TreeSet<Effect>();
                effectsForeffect131Var.add(effect131);
                addEffects((Parameter<?>) effect131Var, effectsForeffect131Var);
                Set<Effect> effectsForeffect132Var = new TreeSet<Effect>();
                effectsForeffect132Var.add(effect132);
                addEffects((Parameter<?>) effect132Var, effectsForeffect132Var);
            }

            public void init_17_0_2_2_edc0357_1357846887022_826257_20391Dependencies() {
                addDependency(addToDecider__17_0_2_2_edc0357_1357846887023_679302_20393, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887021_860360_20389_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887021_860360_20389_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(myDeciderID_decider_17_0_2_2_edc0357_1357846887023_679302_20393, new Expression<Integer>(2));
                addDependency(_17_0_2_2_edc0357_1357846887463_478783_21431, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887025_604759_20399, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_2_edc0357_1357846887462_95896_21430, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887025_988397_20398, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(_17_0_2_2_edc0357_1357846887023_679302_20393_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887021_860360_20389_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887021_860360_20389_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887023_679302_20393, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887023_679302_20393, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887023_679302_20393, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_2_edc0357_1357846887023_679302_20393)))))))));
                addDependency(objectToPass, new Expression<Boolean>(true));
            }

            public void init_17_0_2_2_edc0357_1357846887022_826257_20391Elaborations() {
                init_17_0_2_2_edc0357_1357846887022_826257_20391Dependencies();
                Expression<?>[] arguments133 = new Expression<?>[1];
                arguments133[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition133 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887023_679302_20393_exists);
                elaborationRule133 = addElaborationRule(condition133, _17_0_2_2_edc0357_1357846886591_876034_19881.this, Customer._17_0_2_2_edc0357_1357846886591_876034_19881._17_0_2_2_edc0357_1357846887023_679302_20393.class, "CI_join_JoinNode_initialize", arguments133);
            }
        }

        public class _17_0_2_2_edc0357_1357846887023_125424_20392 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846887023_125424_20392() {
                super();
                init_17_0_2_2_edc0357_1357846887023_125424_20392Members();
                init_17_0_2_2_edc0357_1357846887023_125424_20392Collections();
                init_17_0_2_2_edc0357_1357846887023_125424_20392Elaborations();
            }

            public _17_0_2_2_edc0357_1357846887023_125424_20392(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846887023_125424_20392Members();
                init_17_0_2_2_edc0357_1357846887023_125424_20392Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846887023_125424_20392Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_2_edc0357_1357846887022_826257_20391_exists = null;

            public IntegerParameter _17_0_2_2_edc0357_1357846887463_782570_21433 = null;

            public IntegerParameter myDeciderID_decider_17_0_2_2_edc0357_1357846887022_826257_20391 = null;

            public BooleanParameter objectToPass = null;

            public BooleanParameter addToDecider__17_0_2_2_edc0357_1357846887022_826257_20391 = null;

            public Dependency _17_0_2_2_edc0357_1357846887022_826257_20391_existsDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887463_782570_21433Dependency = null;

            public Dependency durationDependency = null;

            public Dependency myDeciderID_decider_17_0_2_2_edc0357_1357846887022_826257_20391Dependency = null;

            public Dependency objectToPassDependency = null;

            public Dependency addToDecider__17_0_2_2_edc0357_1357846887022_826257_20391Dependency = null;

            public Effect effect134 = null;

            public Parameter effect134Var = null;

            public Effect effect135 = null;

            public Parameter effect135Var = null;

            public ElaborationRule elaborationRule136 = null;

            public void init_17_0_2_2_edc0357_1357846887023_125424_20392Members() {
                try {
                    if (_17_0_2_2_edc0357_1357846887022_826257_20391_exists == null) _17_0_2_2_edc0357_1357846887022_826257_20391_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887022_826257_20391_exists", (Boolean) false, this);
                    if (_17_0_2_2_edc0357_1357846887463_782570_21433 == null) _17_0_2_2_edc0357_1357846887463_782570_21433 = new IntegerParameter("_17_0_2_2_edc0357_1357846887463_782570_21433", (Integer) 0, this);
                    if (myDeciderID_decider_17_0_2_2_edc0357_1357846887022_826257_20391 == null) myDeciderID_decider_17_0_2_2_edc0357_1357846887022_826257_20391 = new IntegerParameter("myDeciderID_decider_17_0_2_2_edc0357_1357846887022_826257_20391", (Integer) 2, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (addToDecider__17_0_2_2_edc0357_1357846887022_826257_20391 == null) addToDecider__17_0_2_2_edc0357_1357846887022_826257_20391 = new BooleanParameter("addToDecider__17_0_2_2_edc0357_1357846887022_826257_20391", (Boolean) false, this);
                    Object effect134VarV = sig_17_0_2_2_edc0357_1357846887025_988397_20398;
                    effect134Var = new Parameter("effect134Var", null, null, this);
                    addDependency(effect134Var, new Expression(effect134VarV));
                    effect134 = new EffectFunction(new EffectFunction(effect134Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { _17_0_2_2_edc0357_1357846887463_782570_21433, endTime }));
                    Object effect135VarV = decider_17_0_2_2_edc0357_1357846887022_826257_20391;
                    effect135Var = new Parameter("effect135Var", null, null, this);
                    addDependency(effect135Var, new Expression(effect135VarV));
                    effect135 = new EffectFunction(new EffectFunction(effect135Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class, java.lang.Boolean.class), new Object[] { endTime, myDeciderID_decider_17_0_2_2_edc0357_1357846887022_826257_20391, addToDecider__17_0_2_2_edc0357_1357846887022_826257_20391 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846887023_125424_20392Collections() {
                parameters.add(_17_0_2_2_edc0357_1357846887022_826257_20391_exists);
                parameters.add(_17_0_2_2_edc0357_1357846887463_782570_21433);
                parameters.add(myDeciderID_decider_17_0_2_2_edc0357_1357846887022_826257_20391);
                parameters.add(objectToPass);
                parameters.add(addToDecider__17_0_2_2_edc0357_1357846887022_826257_20391);
                Set<Effect> effectsForeffect134Var = new TreeSet<Effect>();
                effectsForeffect134Var.add(effect134);
                addEffects((Parameter<?>) effect134Var, effectsForeffect134Var);
                Set<Effect> effectsForeffect135Var = new TreeSet<Effect>();
                effectsForeffect135Var.add(effect135);
                addEffects((Parameter<?>) effect135Var, effectsForeffect135Var);
            }

            public void init_17_0_2_2_edc0357_1357846887023_125424_20392Dependencies() {
                addDependency(_17_0_2_2_edc0357_1357846887022_826257_20391_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887021_860360_20389_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887021_860360_20389_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887022_826257_20391, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887022_826257_20391, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887022_826257_20391, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_2_edc0357_1357846887022_826257_20391)))))))));
                addDependency(_17_0_2_2_edc0357_1357846887463_782570_21433, new Expression<Integer>(0));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(myDeciderID_decider_17_0_2_2_edc0357_1357846887022_826257_20391, new Expression<Integer>(2));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887025_215125_20406, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(addToDecider__17_0_2_2_edc0357_1357846887022_826257_20391, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887021_860360_20389_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887021_860360_20389_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
            }

            public void init_17_0_2_2_edc0357_1357846887023_125424_20392Elaborations() {
                init_17_0_2_2_edc0357_1357846887023_125424_20392Dependencies();
                Expression<?>[] arguments136 = new Expression<?>[1];
                arguments136[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition136 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887022_826257_20391_exists);
                elaborationRule136 = addElaborationRule(condition136, _17_0_2_2_edc0357_1357846886591_876034_19881.this, Customer._17_0_2_2_edc0357_1357846886591_876034_19881._17_0_2_2_edc0357_1357846887022_826257_20391.class, "CI_setinit_cap_AddStructuralFeatureValueAction_initialize", arguments136);
            }
        }

        public class _17_0_2_2_edc0357_1357846887023_679302_20393 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846887023_679302_20393() {
                super();
                init_17_0_2_2_edc0357_1357846887023_679302_20393Members();
                init_17_0_2_2_edc0357_1357846887023_679302_20393Collections();
                init_17_0_2_2_edc0357_1357846887023_679302_20393Elaborations();
            }

            public _17_0_2_2_edc0357_1357846887023_679302_20393(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846887023_679302_20393Members();
                init_17_0_2_2_edc0357_1357846887023_679302_20393Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846887023_679302_20393Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter objectToPass1 = null;

            public BooleanParameter objectToPass = null;

            public Dependency objectToPass1Dependency = null;

            public Dependency finalNode_startTimeDependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Effect effect137 = null;

            public Parameter effect137Var = null;

            public void init_17_0_2_2_edc0357_1357846887023_679302_20393Members() {
                try {
                    if (objectToPass1 == null) objectToPass1 = new BooleanParameter("objectToPass1", (Boolean) null, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect137VarV = sig_17_0_2_2_edc0357_1357846887025_780529_20401;
                    effect137Var = new Parameter("effect137Var", null, null, this);
                    addDependency(effect137Var, new Expression(effect137VarV));
                    effect137 = new EffectFunction(new EffectFunction(effect137Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846887023_679302_20393Collections() {
                parameters.add(objectToPass1);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect137Var = new TreeSet<Effect>();
                effectsForeffect137Var.add(effect137);
                addEffects((Parameter<?>) effect137Var, effectsForeffect137Var);
            }

            public void init_17_0_2_2_edc0357_1357846887023_679302_20393Dependencies() {
                addDependency(objectToPass1, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887025_790450_20402, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(finalNode_startTime, new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887025_932000_20400, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_2_edc0357_1357846887023_679302_20393Elaborations() {
                init_17_0_2_2_edc0357_1357846887023_679302_20393Dependencies();
            }
        }

        public class _17_0_2_2_edc0357_1357846887024_734228_20394 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846887024_734228_20394() {
                super();
                init_17_0_2_2_edc0357_1357846887024_734228_20394Members();
                init_17_0_2_2_edc0357_1357846887024_734228_20394Collections();
                init_17_0_2_2_edc0357_1357846887024_734228_20394Elaborations();
            }

            public _17_0_2_2_edc0357_1357846887024_734228_20394(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846887024_734228_20394Members();
                init_17_0_2_2_edc0357_1357846887024_734228_20394Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846887024_734228_20394Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_2_edc0357_1357846887023_125424_20392_exists = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846887020_953530_20386_exists = null;

            public BooleanParameter objectToPass = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846887019_270831_20385_exists = null;

            public Dependency _17_0_2_2_edc0357_1357846887023_125424_20392_existsDependency = null;

            public Dependency durationDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887020_953530_20386_existsDependency = null;

            public Dependency objectToPassDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887019_270831_20385_existsDependency = null;

            public Effect effect138 = null;

            public Parameter effect138Var = null;

            public Effect effect139 = null;

            public Parameter effect139Var = null;

            public Effect effect140 = null;

            public Parameter effect140Var = null;

            public ElaborationRule elaborationRule141 = null;

            public ElaborationRule elaborationRule142 = null;

            public ElaborationRule elaborationRule143 = null;

            public void init_17_0_2_2_edc0357_1357846887024_734228_20394Members() {
                try {
                    if (_17_0_2_2_edc0357_1357846887023_125424_20392_exists == null) _17_0_2_2_edc0357_1357846887023_125424_20392_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887023_125424_20392_exists", (Boolean) false, this);
                    if (_17_0_2_2_edc0357_1357846887020_953530_20386_exists == null) _17_0_2_2_edc0357_1357846887020_953530_20386_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887020_953530_20386_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (_17_0_2_2_edc0357_1357846887019_270831_20385_exists == null) _17_0_2_2_edc0357_1357846887019_270831_20385_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887019_270831_20385_exists", (Boolean) false, this);
                    Object effect138VarV = sig_17_0_2_2_edc0357_1357846887025_483718_20404;
                    effect138Var = new Parameter("effect138Var", null, null, this);
                    addDependency(effect138Var, new Expression(effect138VarV));
                    effect138 = new EffectFunction(new EffectFunction(effect138Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect139VarV = sig_17_0_2_2_edc0357_1357846887025_577843_20405;
                    effect139Var = new Parameter("effect139Var", null, null, this);
                    addDependency(effect139Var, new Expression(effect139VarV));
                    effect139 = new EffectFunction(new EffectFunction(effect139Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect140VarV = sig_17_0_2_2_edc0357_1357846887025_215125_20406;
                    effect140Var = new Parameter("effect140Var", null, null, this);
                    addDependency(effect140Var, new Expression(effect140VarV));
                    effect140 = new EffectFunction(new EffectFunction(effect140Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846887024_734228_20394Collections() {
                parameters.add(_17_0_2_2_edc0357_1357846887023_125424_20392_exists);
                parameters.add(_17_0_2_2_edc0357_1357846887020_953530_20386_exists);
                parameters.add(objectToPass);
                parameters.add(_17_0_2_2_edc0357_1357846887019_270831_20385_exists);
                Set<Effect> effectsForeffect138Var = new TreeSet<Effect>();
                effectsForeffect138Var.add(effect138);
                addEffects((Parameter<?>) effect138Var, effectsForeffect138Var);
                Set<Effect> effectsForeffect139Var = new TreeSet<Effect>();
                effectsForeffect139Var.add(effect139);
                addEffects((Parameter<?>) effect139Var, effectsForeffect139Var);
                Set<Effect> effectsForeffect140Var = new TreeSet<Effect>();
                effectsForeffect140Var.add(effect140);
                addEffects((Parameter<?>) effect140Var, effectsForeffect140Var);
            }

            public void init_17_0_2_2_edc0357_1357846887024_734228_20394Dependencies() {
                addDependency(_17_0_2_2_edc0357_1357846887023_125424_20392_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887021_860360_20389_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887021_860360_20389_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(_17_0_2_2_edc0357_1357846887020_953530_20386_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887021_860360_20389_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887021_860360_20389_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887025_552926_20403, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_2_edc0357_1357846887019_270831_20385_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887021_860360_20389_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887021_860360_20389_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
            }

            public void init_17_0_2_2_edc0357_1357846887024_734228_20394Elaborations() {
                init_17_0_2_2_edc0357_1357846887024_734228_20394Dependencies();
                Expression<?>[] arguments141 = new Expression<?>[1];
                arguments141[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition141 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887019_270831_20385_exists);
                elaborationRule141 = addElaborationRule(condition141, _17_0_2_2_edc0357_1357846886591_876034_19881.this, Customer._17_0_2_2_edc0357_1357846886591_876034_19881._17_0_2_2_edc0357_1357846887019_270831_20385.class, "CI_readself_ReadSelfAction_initialize", arguments141);
                Expression<?>[] arguments142 = new Expression<?>[1];
                arguments142[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition142 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887020_953530_20386_exists);
                elaborationRule142 = addElaborationRule(condition142, _17_0_2_2_edc0357_1357846886591_876034_19881.this, Customer._17_0_2_2_edc0357_1357846886591_876034_19881._17_0_2_2_edc0357_1357846887020_953530_20386.class, "CI_spec1_ValueSpecificationAction_initialize", arguments142);
                Expression<?>[] arguments143 = new Expression<?>[1];
                arguments143[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition143 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887023_125424_20392_exists);
                elaborationRule143 = addElaborationRule(condition143, _17_0_2_2_edc0357_1357846886591_876034_19881.this, Customer._17_0_2_2_edc0357_1357846886591_876034_19881._17_0_2_2_edc0357_1357846887023_125424_20392.class, "CI_spec0_ValueSpecificationAction_initialize", arguments143);
            }
        }

        public _17_0_2_2_edc0357_1357846886591_876034_19881(Expression<Integer> startTime, Expression<DurativeEvent> caller) {
            super();
            init_17_0_2_2_edc0357_1357846886591_876034_19881Members();
            init_17_0_2_2_edc0357_1357846886591_876034_19881Collections();
            addDependency(this.startTime, startTime);
            addDependency(this.caller, caller);
            init_17_0_2_2_edc0357_1357846886591_876034_19881Elaborations();
            fixTimeDependencies();
        }

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_2_edc0357_1357846887025_790450_20402 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_2_edc0357_1357846887025_932000_20400 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_2_2_edc0357_1357846887025_988397_20398 = null;

        public Parameter< ObjectFlow<Customer> > sig_17_0_2_2_edc0357_1357846887024_394807_20396 = null;

        public Parameter< DurativeEvent > caller = null;

        public Parameter< ObjectFlow<Customer> > sig_17_0_2_2_edc0357_1357846887025_251987_20397 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_2_edc0357_1357846887025_780529_20401 = null;

        public Parameter< TimeVaryingList<Integer> > decider_17_0_2_2_edc0357_1357846887022_826257_20391 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_2_2_edc0357_1357846887024_593289_20395 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_2_edc0357_1357846887025_215125_20406 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_2_edc0357_1357846887025_552926_20403 = null;

        public IntegerParameter finalNode_startTime = null;

        public Parameter< TimeVaryingList<Integer> > decider_17_0_2_2_edc0357_1357846887023_679302_20393 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_2_edc0357_1357846887025_577843_20405 = null;

        public IntegerParameter finalNode_endTime = null;

        public Parameter< ObjectFlow<Customer> > sig_17_0_2_2_edc0357_1357846887025_604759_20399 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_2_edc0357_1357846887025_483718_20404 = null;

        public Parameter< TimeVaryingList<Integer> > decider_17_0_2_2_edc0357_1357846887020_273659_20387 = null;

        public BooleanParameter _17_0_2_2_edc0357_1357846887021_860360_20389_exists = null;

        public Dependency caller_endTimeDependency = null;

        public Dependency endTimeDependency = null;

        public Dependency _17_0_2_2_edc0357_1357846887021_860360_20389_existsDependency = null;

        public ElaborationRule elaborationRule111 = null;

        public ElaborationRule elaborationRule112 = null;

        public void init_17_0_2_2_edc0357_1357846886591_876034_19881Members() {
            try {
                if (sig_17_0_2_2_edc0357_1357846887025_790450_20402 == null) sig_17_0_2_2_edc0357_1357846887025_790450_20402 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_2_edc0357_1357846887025_790450_20402", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887025_790450_20402" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846887025_932000_20400 == null) sig_17_0_2_2_edc0357_1357846887025_932000_20400 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_2_edc0357_1357846887025_932000_20400", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887025_932000_20400" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846887025_988397_20398 == null) sig_17_0_2_2_edc0357_1357846887025_988397_20398 = new Parameter<ObjectFlow<Integer>>("sig_17_0_2_2_edc0357_1357846887025_988397_20398", null, (ObjectFlow<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887025_988397_20398" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846887024_394807_20396 == null) sig_17_0_2_2_edc0357_1357846887024_394807_20396 = new Parameter<ObjectFlow<Customer>>("sig_17_0_2_2_edc0357_1357846887024_394807_20396", null, (ObjectFlow<Customer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887024_394807_20396" })).evaluate(true), this);
                if (caller == null) caller = new Parameter<DurativeEvent>("caller", null, (DurativeEvent) null, this);
                if (sig_17_0_2_2_edc0357_1357846887025_251987_20397 == null) sig_17_0_2_2_edc0357_1357846887025_251987_20397 = new Parameter<ObjectFlow<Customer>>("sig_17_0_2_2_edc0357_1357846887025_251987_20397", null, (ObjectFlow<Customer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887025_251987_20397" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846887025_780529_20401 == null) sig_17_0_2_2_edc0357_1357846887025_780529_20401 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_2_edc0357_1357846887025_780529_20401", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887025_780529_20401" })).evaluate(true), this);
                if (decider_17_0_2_2_edc0357_1357846887022_826257_20391 == null) decider_17_0_2_2_edc0357_1357846887022_826257_20391 = new Parameter<TimeVaryingList<Integer>>("decider_17_0_2_2_edc0357_1357846887022_826257_20391", null, (TimeVaryingList<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingList.class, java.lang.String.class, int.class), new Object[] { "decider_17_0_2_2_edc0357_1357846887022_826257_20391", 2 })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846887024_593289_20395 == null) sig_17_0_2_2_edc0357_1357846887024_593289_20395 = new Parameter<ObjectFlow<Integer>>("sig_17_0_2_2_edc0357_1357846887024_593289_20395", null, (ObjectFlow<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887024_593289_20395" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846887025_215125_20406 == null) sig_17_0_2_2_edc0357_1357846887025_215125_20406 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_2_edc0357_1357846887025_215125_20406", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887025_215125_20406" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846887025_552926_20403 == null) sig_17_0_2_2_edc0357_1357846887025_552926_20403 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_2_edc0357_1357846887025_552926_20403", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887025_552926_20403" })).evaluate(true), this);
                if (finalNode_startTime == null) finalNode_startTime = new IntegerParameter("finalNode_startTime", (Integer) null, this);
                if (decider_17_0_2_2_edc0357_1357846887023_679302_20393 == null) decider_17_0_2_2_edc0357_1357846887023_679302_20393 = new Parameter<TimeVaryingList<Integer>>("decider_17_0_2_2_edc0357_1357846887023_679302_20393", null, (TimeVaryingList<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingList.class, java.lang.String.class, int.class), new Object[] { "decider_17_0_2_2_edc0357_1357846887023_679302_20393", 2 })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846887025_577843_20405 == null) sig_17_0_2_2_edc0357_1357846887025_577843_20405 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_2_edc0357_1357846887025_577843_20405", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887025_577843_20405" })).evaluate(true), this);
                if (finalNode_endTime == null) finalNode_endTime = new IntegerParameter("finalNode_endTime", (Integer) null, this);
                if (sig_17_0_2_2_edc0357_1357846887025_604759_20399 == null) sig_17_0_2_2_edc0357_1357846887025_604759_20399 = new Parameter<ObjectFlow<Customer>>("sig_17_0_2_2_edc0357_1357846887025_604759_20399", null, (ObjectFlow<Customer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887025_604759_20399" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846887025_483718_20404 == null) sig_17_0_2_2_edc0357_1357846887025_483718_20404 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_2_edc0357_1357846887025_483718_20404", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887025_483718_20404" })).evaluate(true), this);
                if (decider_17_0_2_2_edc0357_1357846887020_273659_20387 == null) decider_17_0_2_2_edc0357_1357846887020_273659_20387 = new Parameter<TimeVaryingList<Integer>>("decider_17_0_2_2_edc0357_1357846887020_273659_20387", null, (TimeVaryingList<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingList.class, java.lang.String.class, int.class), new Object[] { "decider_17_0_2_2_edc0357_1357846887020_273659_20387", 2 })).evaluate(true), this);
                if (_17_0_2_2_edc0357_1357846887021_860360_20389_exists == null) _17_0_2_2_edc0357_1357846887021_860360_20389_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887021_860360_20389_exists", (Boolean) false, this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void init_17_0_2_2_edc0357_1357846886591_876034_19881Collections() {
            parameters.add(sig_17_0_2_2_edc0357_1357846887025_790450_20402);
            parameters.add(sig_17_0_2_2_edc0357_1357846887025_932000_20400);
            parameters.add(sig_17_0_2_2_edc0357_1357846887025_988397_20398);
            parameters.add(sig_17_0_2_2_edc0357_1357846887024_394807_20396);
            parameters.add(caller);
            parameters.add(sig_17_0_2_2_edc0357_1357846887025_251987_20397);
            parameters.add(sig_17_0_2_2_edc0357_1357846887025_780529_20401);
            parameters.add(decider_17_0_2_2_edc0357_1357846887022_826257_20391);
            parameters.add(sig_17_0_2_2_edc0357_1357846887024_593289_20395);
            parameters.add(sig_17_0_2_2_edc0357_1357846887025_215125_20406);
            parameters.add(sig_17_0_2_2_edc0357_1357846887025_552926_20403);
            parameters.add(finalNode_startTime);
            parameters.add(decider_17_0_2_2_edc0357_1357846887023_679302_20393);
            parameters.add(sig_17_0_2_2_edc0357_1357846887025_577843_20405);
            parameters.add(finalNode_endTime);
            parameters.add(sig_17_0_2_2_edc0357_1357846887025_604759_20399);
            parameters.add(sig_17_0_2_2_edc0357_1357846887025_483718_20404);
            parameters.add(decider_17_0_2_2_edc0357_1357846887020_273659_20387);
            parameters.add(_17_0_2_2_edc0357_1357846887021_860360_20389_exists);
        }

        public void init_17_0_2_2_edc0357_1357846886591_876034_19881Dependencies() {
            caller.getValue(true).addDependency(caller.getValue(true).endTime, new Expression<Integer>(finalNode_endTime));
            addDependency(endTime, new Expression<Integer>(finalNode_endTime));
            addDependency(_17_0_2_2_edc0357_1357846887021_860360_20389_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.NotEquals(new Expression<Integer>(finalNode_startTime), null)), new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887025_780529_20401, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(new Functions.Minus(new Expression<Integer>(finalNode_startTime), new Expression<Integer>(1))) })))));
        }

        public void init_17_0_2_2_edc0357_1357846886591_876034_19881Elaborations() {
            init_17_0_2_2_edc0357_1357846886591_876034_19881Dependencies();
            Expression<?>[] arguments111 = new Expression<?>[1];
            arguments111[0] = new Expression<Integer>(startTime);
            Expression<Boolean> condition111 = new Expression<Boolean>(true);
            elaborationRule111 = addElaborationRule(condition111, _17_0_2_2_edc0357_1357846886591_876034_19881.this, Customer._17_0_2_2_edc0357_1357846886591_876034_19881._17_0_2_2_edc0357_1357846887021_642269_20388.class, "CI_start_InitialNode_initialize", arguments111);
            Expression<?>[] arguments112 = new Expression<?>[1];
            arguments112[0] = new Expression<Integer>(finalNode_startTime);
            Expression<Boolean> condition112 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887021_860360_20389_exists);
            elaborationRule112 = addElaborationRule(condition112, _17_0_2_2_edc0357_1357846886591_876034_19881.this, Customer._17_0_2_2_edc0357_1357846886591_876034_19881._17_0_2_2_edc0357_1357846887021_860360_20389.class, "CI_end_ActivityFinalNode_initialize", arguments112);
        }
    }

    public class _17_0_2_2_edc0357_1357846886592_253983_19882 extends DurativeEvent {

        public _17_0_2_2_edc0357_1357846886592_253983_19882() {
            super();
            init_17_0_2_2_edc0357_1357846886592_253983_19882Members();
            init_17_0_2_2_edc0357_1357846886592_253983_19882Collections();
            init_17_0_2_2_edc0357_1357846886592_253983_19882Elaborations();
        }

        public class _17_0_2_2_edc0357_1357846887039_144118_20429 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846887039_144118_20429() {
                super();
                init_17_0_2_2_edc0357_1357846887039_144118_20429Members();
                init_17_0_2_2_edc0357_1357846887039_144118_20429Collections();
                init_17_0_2_2_edc0357_1357846887039_144118_20429Elaborations();
            }

            public _17_0_2_2_edc0357_1357846887039_144118_20429(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846887039_144118_20429Members();
                init_17_0_2_2_edc0357_1357846887039_144118_20429Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846887039_144118_20429Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter addToDecider__17_0_2_2_edc0357_1357846887041_741375_20433 = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846887041_741375_20433_exists = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846887043_278131_20437_exists = null;

            public BooleanParameter objectToPass = null;

            public IntegerParameter myDeciderID_decider_17_0_2_2_edc0357_1357846887041_741375_20433 = null;

            public Parameter< Customer > _17_0_2_2_edc0357_1357846887469_803910_21447 = null;

            public Dependency addToDecider__17_0_2_2_edc0357_1357846887041_741375_20433Dependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887041_741375_20433_existsDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887043_278131_20437_existsDependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Dependency myDeciderID_decider_17_0_2_2_edc0357_1357846887041_741375_20433Dependency = null;

            public Effect effect147 = null;

            public Parameter effect147Var = null;

            public Effect effect148 = null;

            public Parameter effect148Var = null;

            public Effect effect149 = null;

            public Parameter effect149Var = null;

            public ElaborationRule elaborationRule150 = null;

            public ElaborationRule elaborationRule151 = null;

            public void init_17_0_2_2_edc0357_1357846887039_144118_20429Members() {
                try {
                    if (addToDecider__17_0_2_2_edc0357_1357846887041_741375_20433 == null) addToDecider__17_0_2_2_edc0357_1357846887041_741375_20433 = new BooleanParameter("addToDecider__17_0_2_2_edc0357_1357846887041_741375_20433", (Boolean) false, this);
                    if (_17_0_2_2_edc0357_1357846887041_741375_20433_exists == null) _17_0_2_2_edc0357_1357846887041_741375_20433_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887041_741375_20433_exists", (Boolean) false, this);
                    if (_17_0_2_2_edc0357_1357846887043_278131_20437_exists == null) _17_0_2_2_edc0357_1357846887043_278131_20437_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887043_278131_20437_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (myDeciderID_decider_17_0_2_2_edc0357_1357846887041_741375_20433 == null) myDeciderID_decider_17_0_2_2_edc0357_1357846887041_741375_20433 = new IntegerParameter("myDeciderID_decider_17_0_2_2_edc0357_1357846887041_741375_20433", (Integer) 1, this);
                    if (_17_0_2_2_edc0357_1357846887469_803910_21447 == null) _17_0_2_2_edc0357_1357846887469_803910_21447 = new Parameter<Customer>("_17_0_2_2_edc0357_1357846887469_803910_21447", null, (Customer) Customer.this, this);
                    Object effect147VarV = sig_17_0_2_2_edc0357_1357846887046_642363_20447;
                    effect147Var = new Parameter("effect147Var", null, null, this);
                    addDependency(effect147Var, new Expression(effect147VarV));
                    effect147 = new EffectFunction(new EffectFunction(effect147Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { _17_0_2_2_edc0357_1357846887469_803910_21447, endTime }));
                    Object effect148VarV = sig_17_0_2_2_edc0357_1357846887047_128067_20451;
                    effect148Var = new Parameter("effect148Var", null, null, this);
                    addDependency(effect148Var, new Expression(effect148VarV));
                    effect148 = new EffectFunction(new EffectFunction(effect148Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect149VarV = decider_17_0_2_2_edc0357_1357846887041_741375_20433;
                    effect149Var = new Parameter("effect149Var", null, null, this);
                    addDependency(effect149Var, new Expression(effect149VarV));
                    effect149 = new EffectFunction(new EffectFunction(effect149Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class, java.lang.Boolean.class), new Object[] { endTime, myDeciderID_decider_17_0_2_2_edc0357_1357846887041_741375_20433, addToDecider__17_0_2_2_edc0357_1357846887041_741375_20433 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846887039_144118_20429Collections() {
                parameters.add(addToDecider__17_0_2_2_edc0357_1357846887041_741375_20433);
                parameters.add(_17_0_2_2_edc0357_1357846887041_741375_20433_exists);
                parameters.add(_17_0_2_2_edc0357_1357846887043_278131_20437_exists);
                parameters.add(objectToPass);
                parameters.add(myDeciderID_decider_17_0_2_2_edc0357_1357846887041_741375_20433);
                parameters.add(_17_0_2_2_edc0357_1357846887469_803910_21447);
                Set<Effect> effectsForeffect147Var = new TreeSet<Effect>();
                effectsForeffect147Var.add(effect147);
                addEffects((Parameter<?>) effect147Var, effectsForeffect147Var);
                Set<Effect> effectsForeffect148Var = new TreeSet<Effect>();
                effectsForeffect148Var.add(effect148);
                addEffects((Parameter<?>) effect148Var, effectsForeffect148Var);
                Set<Effect> effectsForeffect149Var = new TreeSet<Effect>();
                effectsForeffect149Var.add(effect149);
                addEffects((Parameter<?>) effect149Var, effectsForeffect149Var);
            }

            public void init_17_0_2_2_edc0357_1357846887039_144118_20429Dependencies() {
                addDependency(addToDecider__17_0_2_2_edc0357_1357846887041_741375_20433, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887040_4366_20432_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887040_4366_20432_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(_17_0_2_2_edc0357_1357846887041_741375_20433_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887040_4366_20432_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887040_4366_20432_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887041_741375_20433, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887041_741375_20433, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887041_741375_20433, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_2_edc0357_1357846887041_741375_20433)))))))));
                addDependency(_17_0_2_2_edc0357_1357846887043_278131_20437_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887040_4366_20432_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887040_4366_20432_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887047_957196_20455, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(myDeciderID_decider_17_0_2_2_edc0357_1357846887041_741375_20433, new Expression<Integer>(1));
            }

            public void init_17_0_2_2_edc0357_1357846887039_144118_20429Elaborations() {
                init_17_0_2_2_edc0357_1357846887039_144118_20429Dependencies();
                Expression<?>[] arguments150 = new Expression<?>[1];
                arguments150[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition150 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887043_278131_20437_exists);
                elaborationRule150 = addElaborationRule(condition150, _17_0_2_2_edc0357_1357846886592_253983_19882.this, Customer._17_0_2_2_edc0357_1357846886592_253983_19882._17_0_2_2_edc0357_1357846887043_278131_20437.class, "CDR_forkself_ForkNode_setDRCap", arguments150);
                Expression<?>[] arguments151 = new Expression<?>[1];
                arguments151[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition151 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887041_741375_20433_exists);
                elaborationRule151 = addElaborationRule(condition151, _17_0_2_2_edc0357_1357846886592_253983_19882.this, Customer._17_0_2_2_edc0357_1357846886592_253983_19882._17_0_2_2_edc0357_1357846887041_741375_20433.class, "CDR_decide_particip_DecisionNode_setDRCap", arguments151);
            }
        }

        public class _17_0_2_2_edc0357_1357846887039_694624_20430 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846887039_694624_20430() {
                super();
                init_17_0_2_2_edc0357_1357846887039_694624_20430Members();
                init_17_0_2_2_edc0357_1357846887039_694624_20430Collections();
                init_17_0_2_2_edc0357_1357846887039_694624_20430Elaborations();
            }

            public _17_0_2_2_edc0357_1357846887039_694624_20430(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846887039_694624_20430Members();
                init_17_0_2_2_edc0357_1357846887039_694624_20430Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846887039_694624_20430Elaborations();
                fixTimeDependencies();
            }

            public IntegerParameter myDeciderID_decider_17_0_2_2_edc0357_1357846887042_384245_20436 = null;

            public BooleanParameter addToDecider__17_0_2_2_edc0357_1357846887042_384245_20436 = null;

            public Parameter< Customer > _17_0_2_2_edc0357_1357846887470_245436_21449 = null;

            public IntegerParameter _17_0_2_2_edc0357_1357846887470_737392_21448 = null;

            public BooleanParameter objectToPass = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846887042_384245_20436_exists = null;

            public Dependency myDeciderID_decider_17_0_2_2_edc0357_1357846887042_384245_20436Dependency = null;

            public Dependency addToDecider__17_0_2_2_edc0357_1357846887042_384245_20436Dependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887470_245436_21449Dependency = null;

            public Dependency durationDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887470_737392_21448Dependency = null;

            public Dependency objectToPassDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887042_384245_20436_existsDependency = null;

            public Effect effect152 = null;

            public Parameter effect152Var = null;

            public Effect effect153 = null;

            public Parameter effect153Var = null;

            public Effect effect154 = null;

            public Parameter effect154Var = null;

            public ElaborationRule elaborationRule155 = null;

            public void init_17_0_2_2_edc0357_1357846887039_694624_20430Members() {
                try {
                    if (myDeciderID_decider_17_0_2_2_edc0357_1357846887042_384245_20436 == null) myDeciderID_decider_17_0_2_2_edc0357_1357846887042_384245_20436 = new IntegerParameter("myDeciderID_decider_17_0_2_2_edc0357_1357846887042_384245_20436", (Integer) 3, this);
                    if (addToDecider__17_0_2_2_edc0357_1357846887042_384245_20436 == null) addToDecider__17_0_2_2_edc0357_1357846887042_384245_20436 = new BooleanParameter("addToDecider__17_0_2_2_edc0357_1357846887042_384245_20436", (Boolean) false, this);
                    if (_17_0_2_2_edc0357_1357846887470_245436_21449 == null) _17_0_2_2_edc0357_1357846887470_245436_21449 = new Parameter<Customer>("_17_0_2_2_edc0357_1357846887470_245436_21449", null, (Customer) null, this);
                    if (_17_0_2_2_edc0357_1357846887470_737392_21448 == null) _17_0_2_2_edc0357_1357846887470_737392_21448 = new IntegerParameter("_17_0_2_2_edc0357_1357846887470_737392_21448", (Integer) null, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (_17_0_2_2_edc0357_1357846887042_384245_20436_exists == null) _17_0_2_2_edc0357_1357846887042_384245_20436_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887042_384245_20436_exists", (Boolean) false, this);
                    Object effect152VarV = sig_17_0_2_2_edc0357_1357846887046_583925_20445;
                    effect152Var = new Parameter("effect152Var", null, null, this);
                    addDependency(effect152Var, new Expression(effect152VarV));
                    effect152 = new EffectFunction(new EffectFunction(effect152Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect153VarV = decider_17_0_2_2_edc0357_1357846887042_384245_20436;
                    effect153Var = new Parameter("effect153Var", null, null, this);
                    addDependency(effect153Var, new Expression(effect153VarV));
                    effect153 = new EffectFunction(new EffectFunction(effect153Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class, java.lang.Boolean.class), new Object[] { endTime, myDeciderID_decider_17_0_2_2_edc0357_1357846887042_384245_20436, addToDecider__17_0_2_2_edc0357_1357846887042_384245_20436 }));
                    Object effect154VarV = cap__17_0_2_2_edc0357_1357846886598_990111_19890;
                    effect154Var = new Parameter("effect154Var", null, null, this);
                    addDependency(effect154Var, new Expression(effect154VarV));
                    effect154 = new EffectFunction(new EffectFunction(effect154Var, ClassUtils.getMethodForArgTypes("TimeVaryingPlottableMap<Integer>", "PowerSystem3", "setValue", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { startTime, _17_0_2_2_edc0357_1357846887470_737392_21448 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846887039_694624_20430Collections() {
                parameters.add(myDeciderID_decider_17_0_2_2_edc0357_1357846887042_384245_20436);
                parameters.add(addToDecider__17_0_2_2_edc0357_1357846887042_384245_20436);
                parameters.add(_17_0_2_2_edc0357_1357846887470_245436_21449);
                parameters.add(_17_0_2_2_edc0357_1357846887470_737392_21448);
                parameters.add(objectToPass);
                parameters.add(_17_0_2_2_edc0357_1357846887042_384245_20436_exists);
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

            public void init_17_0_2_2_edc0357_1357846887039_694624_20430Dependencies() {
                addDependency(myDeciderID_decider_17_0_2_2_edc0357_1357846887042_384245_20436, new Expression<Integer>(3));
                addDependency(addToDecider__17_0_2_2_edc0357_1357846887042_384245_20436, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887040_4366_20432_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887040_4366_20432_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(_17_0_2_2_edc0357_1357846887470_245436_21449, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887046_187814_20448, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(_17_0_2_2_edc0357_1357846887470_737392_21448, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887046_32321_20444, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887046_889895_20442, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_2_edc0357_1357846887042_384245_20436_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887040_4366_20432_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887040_4366_20432_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887042_384245_20436, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887042_384245_20436, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887042_384245_20436, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_2_edc0357_1357846887042_384245_20436)))))))));
            }

            public void init_17_0_2_2_edc0357_1357846887039_694624_20430Elaborations() {
                init_17_0_2_2_edc0357_1357846887039_694624_20430Dependencies();
                Expression<?>[] arguments155 = new Expression<?>[1];
                arguments155[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition155 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887042_384245_20436_exists);
                elaborationRule155 = addElaborationRule(condition155, _17_0_2_2_edc0357_1357846886592_253983_19882.this, Customer._17_0_2_2_edc0357_1357846886592_253983_19882._17_0_2_2_edc0357_1357846887042_384245_20436.class, "CDR_send_yes_SendSignalAction_setDRCap", arguments155);
            }
        }

        public class _17_0_2_2_edc0357_1357846887040_5059_20431 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846887040_5059_20431() {
                super();
                init_17_0_2_2_edc0357_1357846887040_5059_20431Members();
                init_17_0_2_2_edc0357_1357846887040_5059_20431Collections();
                init_17_0_2_2_edc0357_1357846887040_5059_20431Elaborations();
            }

            public _17_0_2_2_edc0357_1357846887040_5059_20431(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846887040_5059_20431Members();
                init_17_0_2_2_edc0357_1357846887040_5059_20431Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846887040_5059_20431Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_2_edc0357_1357846887045_71209_20440_exists = null;

            public BooleanParameter objectToPass = null;

            public Dependency _17_0_2_2_edc0357_1357846887045_71209_20440_existsDependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Effect effect156 = null;

            public Parameter effect156Var = null;

            public ElaborationRule elaborationRule157 = null;

            public void init_17_0_2_2_edc0357_1357846887040_5059_20431Members() {
                try {
                    if (_17_0_2_2_edc0357_1357846887045_71209_20440_exists == null) _17_0_2_2_edc0357_1357846887045_71209_20440_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887045_71209_20440_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect156VarV = sig_17_0_2_2_edc0357_1357846887047_727669_20454;
                    effect156Var = new Parameter("effect156Var", null, null, this);
                    addDependency(effect156Var, new Expression(effect156VarV));
                    effect156 = new EffectFunction(new EffectFunction(effect156Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846887040_5059_20431Collections() {
                parameters.add(_17_0_2_2_edc0357_1357846887045_71209_20440_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect156Var = new TreeSet<Effect>();
                effectsForeffect156Var.add(effect156);
                addEffects((Parameter<?>) effect156Var, effectsForeffect156Var);
            }

            public void init_17_0_2_2_edc0357_1357846887040_5059_20431Dependencies() {
                addDependency(_17_0_2_2_edc0357_1357846887045_71209_20440_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887040_4366_20432_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887040_4366_20432_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(true));
            }

            public void init_17_0_2_2_edc0357_1357846887040_5059_20431Elaborations() {
                init_17_0_2_2_edc0357_1357846887040_5059_20431Dependencies();
                Expression<?>[] arguments157 = new Expression<?>[1];
                arguments157[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition157 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887045_71209_20440_exists);
                elaborationRule157 = addElaborationRule(condition157, _17_0_2_2_edc0357_1357846886592_253983_19882.this, Customer._17_0_2_2_edc0357_1357846886592_253983_19882._17_0_2_2_edc0357_1357846887045_71209_20440.class, "CDR_fork1_ForkNode_setDRCap", arguments157);
            }
        }

        public class _17_0_2_2_edc0357_1357846887040_4366_20432 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846887040_4366_20432() {
                super();
                init_17_0_2_2_edc0357_1357846887040_4366_20432Members();
                init_17_0_2_2_edc0357_1357846887040_4366_20432Collections();
                init_17_0_2_2_edc0357_1357846887040_4366_20432Elaborations();
            }

            public _17_0_2_2_edc0357_1357846887040_4366_20432(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846887040_4366_20432Members();
                init_17_0_2_2_edc0357_1357846887040_4366_20432Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846887040_4366_20432Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter objectToPass = null;

            public Dependency endTimeDependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Dependency finalNode_endTimeDependency = null;

            public void init_17_0_2_2_edc0357_1357846887040_4366_20432Members() {
                try {
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846887040_4366_20432Collections() {
                parameters.add(objectToPass);
            }

            public void init_17_0_2_2_edc0357_1357846887040_4366_20432Dependencies() {
                addDependency(endTime, new Expression<Integer>(new Functions.Plus(new Expression<Integer>(startTime), new Expression<Integer>(duration))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887047_738382_20459, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(finalNode_endTime, new Expression<Integer>(endTime));
            }

            public void init_17_0_2_2_edc0357_1357846887040_4366_20432Elaborations() {
                init_17_0_2_2_edc0357_1357846887040_4366_20432Dependencies();
            }
        }

        public class _17_0_2_2_edc0357_1357846887041_741375_20433 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846887041_741375_20433() {
                super();
                init_17_0_2_2_edc0357_1357846887041_741375_20433Members();
                init_17_0_2_2_edc0357_1357846887041_741375_20433Collections();
                init_17_0_2_2_edc0357_1357846887041_741375_20433Elaborations();
            }

            public _17_0_2_2_edc0357_1357846887041_741375_20433(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846887041_741375_20433Members();
                init_17_0_2_2_edc0357_1357846887041_741375_20433Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846887041_741375_20433Elaborations();
                fixTimeDependencies();
            }

            public IntegerParameter myDeciderID_decider_17_0_2_2_edc0357_1357846887041_36636_20434 = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846887041_36636_20434_exists = null;

            public BooleanParameter addToDecider__17_0_2_2_edc0357_1357846887039_694624_20430 = null;

            public IntegerParameter myDeciderID_decider_17_0_2_2_edc0357_1357846887039_694624_20430 = null;

            public BooleanParameter objectToPass = null;

            public BooleanParameter decisionInput = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846887039_694624_20430_exists = null;

            public BooleanParameter addToDecider__17_0_2_2_edc0357_1357846887041_36636_20434 = null;

            public Dependency myDeciderID_decider_17_0_2_2_edc0357_1357846887041_36636_20434Dependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887041_36636_20434_existsDependency = null;

            public Dependency addToDecider__17_0_2_2_edc0357_1357846887039_694624_20430Dependency = null;

            public Dependency durationDependency = null;

            public Dependency myDeciderID_decider_17_0_2_2_edc0357_1357846887039_694624_20430Dependency = null;

            public Dependency objectToPassDependency = null;

            public Dependency decisionInputDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887039_694624_20430_existsDependency = null;

            public Dependency addToDecider__17_0_2_2_edc0357_1357846887041_36636_20434Dependency = null;

            public Effect effect158 = null;

            public Parameter effect158Var = null;

            public Effect effect159 = null;

            public Parameter effect159Var = null;

            public Effect effect160 = null;

            public Parameter effect160Var = null;

            public Effect effect161 = null;

            public Parameter effect161Var = null;

            public ElaborationRule elaborationRule162 = null;

            public ElaborationRule elaborationRule163 = null;

            public void init_17_0_2_2_edc0357_1357846887041_741375_20433Members() {
                try {
                    if (myDeciderID_decider_17_0_2_2_edc0357_1357846887041_36636_20434 == null) myDeciderID_decider_17_0_2_2_edc0357_1357846887041_36636_20434 = new IntegerParameter("myDeciderID_decider_17_0_2_2_edc0357_1357846887041_36636_20434", (Integer) 2, this);
                    if (_17_0_2_2_edc0357_1357846887041_36636_20434_exists == null) _17_0_2_2_edc0357_1357846887041_36636_20434_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887041_36636_20434_exists", (Boolean) false, this);
                    if (addToDecider__17_0_2_2_edc0357_1357846887039_694624_20430 == null) addToDecider__17_0_2_2_edc0357_1357846887039_694624_20430 = new BooleanParameter("addToDecider__17_0_2_2_edc0357_1357846887039_694624_20430", (Boolean) false, this);
                    if (myDeciderID_decider_17_0_2_2_edc0357_1357846887039_694624_20430 == null) myDeciderID_decider_17_0_2_2_edc0357_1357846887039_694624_20430 = new IntegerParameter("myDeciderID_decider_17_0_2_2_edc0357_1357846887039_694624_20430", (Integer) 3, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (decisionInput == null) decisionInput = new BooleanParameter("decisionInput", (Boolean) null, this);
                    if (_17_0_2_2_edc0357_1357846887039_694624_20430_exists == null) _17_0_2_2_edc0357_1357846887039_694624_20430_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887039_694624_20430_exists", (Boolean) false, this);
                    if (addToDecider__17_0_2_2_edc0357_1357846887041_36636_20434 == null) addToDecider__17_0_2_2_edc0357_1357846887041_36636_20434 = new BooleanParameter("addToDecider__17_0_2_2_edc0357_1357846887041_36636_20434", (Boolean) false, this);
                    Object effect158VarV = sig_17_0_2_2_edc0357_1357846887046_889895_20442;
                    effect158Var = new Parameter("effect158Var", null, null, this);
                    addDependency(effect158Var, new Expression(effect158VarV));
                    effect158 = new EffectFunction(new EffectFunction(effect158Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "sendIf", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class, java.lang.Boolean.class), new Object[] { objectToPass, endTime, _17_0_2_2_edc0357_1357846887039_694624_20430_exists }));
                    Object effect159VarV = sig_17_0_2_2_edc0357_1357846887046_762014_20443;
                    effect159Var = new Parameter("effect159Var", null, null, this);
                    addDependency(effect159Var, new Expression(effect159VarV));
                    effect159 = new EffectFunction(new EffectFunction(effect159Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "sendIf", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class, java.lang.Boolean.class), new Object[] { objectToPass, endTime, _17_0_2_2_edc0357_1357846887041_36636_20434_exists }));
                    Object effect160VarV = decider_17_0_2_2_edc0357_1357846887039_694624_20430;
                    effect160Var = new Parameter("effect160Var", null, null, this);
                    addDependency(effect160Var, new Expression(effect160VarV));
                    effect160 = new EffectFunction(new EffectFunction(effect160Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class, java.lang.Boolean.class), new Object[] { endTime, myDeciderID_decider_17_0_2_2_edc0357_1357846887039_694624_20430, addToDecider__17_0_2_2_edc0357_1357846887039_694624_20430 }));
                    Object effect161VarV = decider_17_0_2_2_edc0357_1357846887041_36636_20434;
                    effect161Var = new Parameter("effect161Var", null, null, this);
                    addDependency(effect161Var, new Expression(effect161VarV));
                    effect161 = new EffectFunction(new EffectFunction(effect161Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class, java.lang.Boolean.class), new Object[] { endTime, myDeciderID_decider_17_0_2_2_edc0357_1357846887041_36636_20434, addToDecider__17_0_2_2_edc0357_1357846887041_36636_20434 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846887041_741375_20433Collections() {
                parameters.add(myDeciderID_decider_17_0_2_2_edc0357_1357846887041_36636_20434);
                parameters.add(_17_0_2_2_edc0357_1357846887041_36636_20434_exists);
                parameters.add(addToDecider__17_0_2_2_edc0357_1357846887039_694624_20430);
                parameters.add(myDeciderID_decider_17_0_2_2_edc0357_1357846887039_694624_20430);
                parameters.add(objectToPass);
                parameters.add(decisionInput);
                parameters.add(_17_0_2_2_edc0357_1357846887039_694624_20430_exists);
                parameters.add(addToDecider__17_0_2_2_edc0357_1357846887041_36636_20434);
                Set<Effect> effectsForeffect158Var = new TreeSet<Effect>();
                effectsForeffect158Var.add(effect158);
                addEffects((Parameter<?>) effect158Var, effectsForeffect158Var);
                Set<Effect> effectsForeffect159Var = new TreeSet<Effect>();
                effectsForeffect159Var.add(effect159);
                addEffects((Parameter<?>) effect159Var, effectsForeffect159Var);
                Set<Effect> effectsForeffect160Var = new TreeSet<Effect>();
                effectsForeffect160Var.add(effect160);
                addEffects((Parameter<?>) effect160Var, effectsForeffect160Var);
                Set<Effect> effectsForeffect161Var = new TreeSet<Effect>();
                effectsForeffect161Var.add(effect161);
                addEffects((Parameter<?>) effect161Var, effectsForeffect161Var);
            }

            public void init_17_0_2_2_edc0357_1357846887041_741375_20433Dependencies() {
                addDependency(myDeciderID_decider_17_0_2_2_edc0357_1357846887041_36636_20434, new Expression<Integer>(2));
                addDependency(_17_0_2_2_edc0357_1357846887041_36636_20434_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(decisionInput))), new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887040_4366_20432_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887040_4366_20432_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887041_36636_20434, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887041_36636_20434, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887041_36636_20434, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_2_edc0357_1357846887041_36636_20434)))))))));
                addDependency(addToDecider__17_0_2_2_edc0357_1357846887039_694624_20430, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(decisionInput), new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887040_4366_20432_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887040_4366_20432_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(myDeciderID_decider_17_0_2_2_edc0357_1357846887039_694624_20430, new Expression<Integer>(3));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887047_128067_20451, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(decisionInput, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887047_993678_20453, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_2_edc0357_1357846887039_694624_20430_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(decisionInput), new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887040_4366_20432_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887040_4366_20432_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887039_694624_20430, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887039_694624_20430, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887039_694624_20430, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_2_edc0357_1357846887039_694624_20430)))))))));
                addDependency(addToDecider__17_0_2_2_edc0357_1357846887041_36636_20434, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(decisionInput))), new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887040_4366_20432_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887040_4366_20432_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))))));
            }

            public void init_17_0_2_2_edc0357_1357846887041_741375_20433Elaborations() {
                init_17_0_2_2_edc0357_1357846887041_741375_20433Dependencies();
                Expression<?>[] arguments162 = new Expression<?>[1];
                arguments162[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition162 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887039_694624_20430_exists);
                elaborationRule162 = addElaborationRule(condition162, _17_0_2_2_edc0357_1357846886592_253983_19882.this, Customer._17_0_2_2_edc0357_1357846886592_253983_19882._17_0_2_2_edc0357_1357846887039_694624_20430.class, "CDR_set_cap_AddStructuralFeatureValueAction_setDRCap", arguments162);
                Expression<?>[] arguments163 = new Expression<?>[1];
                arguments163[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition163 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887041_36636_20434_exists);
                elaborationRule163 = addElaborationRule(condition163, _17_0_2_2_edc0357_1357846886592_253983_19882.this, Customer._17_0_2_2_edc0357_1357846886592_253983_19882._17_0_2_2_edc0357_1357846887041_36636_20434.class, "CDR_send_no_SendSignalAction_setDRCap", arguments163);
            }
        }

        public class _17_0_2_2_edc0357_1357846887041_36636_20434 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846887041_36636_20434() {
                super();
                init_17_0_2_2_edc0357_1357846887041_36636_20434Members();
                init_17_0_2_2_edc0357_1357846887041_36636_20434Collections();
                init_17_0_2_2_edc0357_1357846887041_36636_20434Elaborations();
            }

            public _17_0_2_2_edc0357_1357846887041_36636_20434(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846887041_36636_20434Members();
                init_17_0_2_2_edc0357_1357846887041_36636_20434Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846887041_36636_20434Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_2_edc0357_1357846887045_595974_20441_exists = null;

            public BooleanParameter objectToPass = null;

            public Parameter< Customer > _17_0_2_2_edc0357_1357846887471_420546_21450 = null;

            public Parameter< Power_System.Signalno > signalObject = null;

            public Dependency _17_0_2_2_edc0357_1357846887045_595974_20441_existsDependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887471_420546_21450Dependency = null;

            public Dependency signalObjectDependency = null;

            public Effect effect164 = null;

            public Parameter effect164Var = null;

            public Effect effect165 = null;

            public Parameter effect165Var = null;

            public Effect effect166 = null;

            public Parameter effect166Var = null;

            public ElaborationRule elaborationRule167 = null;

            public void init_17_0_2_2_edc0357_1357846887041_36636_20434Members() {
                try {
                    if (_17_0_2_2_edc0357_1357846887045_595974_20441_exists == null) _17_0_2_2_edc0357_1357846887045_595974_20441_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887045_595974_20441_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (_17_0_2_2_edc0357_1357846887471_420546_21450 == null) _17_0_2_2_edc0357_1357846887471_420546_21450 = new Parameter<Customer>("_17_0_2_2_edc0357_1357846887471_420546_21450", null, (Customer) null, this);
                    if (signalObject == null) signalObject = new Parameter<Power_System.Signalno>("signalObject", null, (Power_System.Signalno) null, this);
                    Object effect164VarV = sig_17_0_2_2_edc0357_1357846887047_977540_20457;
                    effect164Var = new Parameter("effect164Var", null, null, this);
                    addDependency(effect164Var, new Expression(effect164VarV));
                    effect164 = new EffectFunction(new EffectFunction(effect164Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect165VarV = new FunctionCall(x, Parameter.class, "getMember", new Object[] { "ss_17_0_2_2_edc0357_1357846886597_47094_19888_no" });
                    effect165Var = new Parameter("effect165Var", null, null, this);
                    addDependency(effect165Var, new Expression(effect165VarV));
                    effect165 = new EffectFunction(new EffectFunction(null, ClassUtils.getMethodForArgTypes("ObjectFlow<Signalno>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { signalObject, endTime }, effect165Var));
                    Object effect166VarV = new FunctionCall(signalObject, Parameter.class, "getMember", new Object[] { "control" });
                    effect166Var = new Parameter("effect166Var", null, null, this);
                    addDependency(effect166Var, new Expression(effect166VarV));
                    effect166 = new EffectFunction(new EffectFunction(null, ClassUtils.getMethodForArgTypes("TimeVaryingMap<Boolean>", "PowerSystem3", "setValue", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, true }, effect166Var));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846887041_36636_20434Collections() {
                parameters.add(_17_0_2_2_edc0357_1357846887045_595974_20441_exists);
                parameters.add(objectToPass);
                parameters.add(_17_0_2_2_edc0357_1357846887471_420546_21450);
                parameters.add(signalObject);
                Set<Effect> effectsForeffect164Var = new TreeSet<Effect>();
                effectsForeffect164Var.add(effect164);
                addEffects((Parameter<?>) effect164Var, effectsForeffect164Var);
                Set<Effect> effectsForeffect165Var = new TreeSet<Effect>();
                effectsForeffect165Var.add(effect165);
                addEffects((Parameter<?>) effect165Var, effectsForeffect165Var);
                Set<Effect> effectsForeffect166Var = new TreeSet<Effect>();
                effectsForeffect166Var.add(effect166);
                addEffects((Parameter<?>) effect166Var, effectsForeffect166Var);
            }

            public void init_17_0_2_2_edc0357_1357846887041_36636_20434Dependencies() {
                addDependency(_17_0_2_2_edc0357_1357846887045_595974_20441_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887040_4366_20432_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887040_4366_20432_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887046_762014_20443, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_2_edc0357_1357846887471_420546_21450, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887046_73630_20450, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(signalObject, new Expression<Power_System.Signalno>(new ConstructorCall(x, ClassUtils.getConstructorForArgTypes(Power_System.Signalno.class), new Object[] {})));
            }

            public void init_17_0_2_2_edc0357_1357846887041_36636_20434Elaborations() {
                init_17_0_2_2_edc0357_1357846887041_36636_20434Dependencies();
                Expression<?>[] arguments167 = new Expression<?>[2];
                arguments167[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                arguments167[1] = new Expression<ObjectFlow<Boolean>>(sig_17_0_2_2_edc0357_1357846887047_977540_20457);
                Expression<Boolean> condition167 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887045_595974_20441_exists);
                elaborationRule167 = addElaborationRule(condition167, _17_0_2_2_edc0357_1357846886592_253983_19882.this, Customer._17_0_2_2_edc0357_1357846886592_253983_19882._17_0_2_2_edc0357_1357846887045_595974_20441.class, "CDR_merge_response_MergeNode_setDRCap", arguments167);
            }
        }

        public class _17_0_2_2_edc0357_1357846887042_5499_20435 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846887042_5499_20435() {
                super();
                init_17_0_2_2_edc0357_1357846887042_5499_20435Members();
                init_17_0_2_2_edc0357_1357846887042_5499_20435Collections();
                init_17_0_2_2_edc0357_1357846887042_5499_20435Elaborations();
            }

            public _17_0_2_2_edc0357_1357846887042_5499_20435(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846887042_5499_20435Members();
                init_17_0_2_2_edc0357_1357846887042_5499_20435Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846887042_5499_20435Elaborations();
                fixTimeDependencies();
            }

            public IntegerParameter myDeciderID_decider_17_0_2_2_edc0357_1357846887042_384245_20436 = null;

            public BooleanParameter addToDecider__17_0_2_2_edc0357_1357846887042_384245_20436 = null;

            public BooleanParameter addToDecider__17_0_2_2_edc0357_1357846887039_694624_20430 = null;

            public IntegerParameter myDeciderID_decider_17_0_2_2_edc0357_1357846887039_694624_20430 = null;

            public IntegerParameter objectToPass = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846887042_384245_20436_exists = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846887039_694624_20430_exists = null;

            public Dependency myDeciderID_decider_17_0_2_2_edc0357_1357846887042_384245_20436Dependency = null;

            public Dependency addToDecider__17_0_2_2_edc0357_1357846887042_384245_20436Dependency = null;

            public Dependency addToDecider__17_0_2_2_edc0357_1357846887039_694624_20430Dependency = null;

            public Dependency durationDependency = null;

            public Dependency myDeciderID_decider_17_0_2_2_edc0357_1357846887039_694624_20430Dependency = null;

            public Dependency objectToPassDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887042_384245_20436_existsDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887039_694624_20430_existsDependency = null;

            public Effect effect168 = null;

            public Parameter effect168Var = null;

            public Effect effect169 = null;

            public Parameter effect169Var = null;

            public Effect effect170 = null;

            public Parameter effect170Var = null;

            public Effect effect171 = null;

            public Parameter effect171Var = null;

            public ElaborationRule elaborationRule172 = null;

            public ElaborationRule elaborationRule173 = null;

            public void init_17_0_2_2_edc0357_1357846887042_5499_20435Members() {
                try {
                    if (myDeciderID_decider_17_0_2_2_edc0357_1357846887042_384245_20436 == null) myDeciderID_decider_17_0_2_2_edc0357_1357846887042_384245_20436 = new IntegerParameter("myDeciderID_decider_17_0_2_2_edc0357_1357846887042_384245_20436", (Integer) 1, this);
                    if (addToDecider__17_0_2_2_edc0357_1357846887042_384245_20436 == null) addToDecider__17_0_2_2_edc0357_1357846887042_384245_20436 = new BooleanParameter("addToDecider__17_0_2_2_edc0357_1357846887042_384245_20436", (Boolean) false, this);
                    if (addToDecider__17_0_2_2_edc0357_1357846887039_694624_20430 == null) addToDecider__17_0_2_2_edc0357_1357846887039_694624_20430 = new BooleanParameter("addToDecider__17_0_2_2_edc0357_1357846887039_694624_20430", (Boolean) false, this);
                    if (myDeciderID_decider_17_0_2_2_edc0357_1357846887039_694624_20430 == null) myDeciderID_decider_17_0_2_2_edc0357_1357846887039_694624_20430 = new IntegerParameter("myDeciderID_decider_17_0_2_2_edc0357_1357846887039_694624_20430", (Integer) 2, this);
                    if (objectToPass == null) objectToPass = new IntegerParameter("objectToPass", (Integer) null, this);
                    if (_17_0_2_2_edc0357_1357846887042_384245_20436_exists == null) _17_0_2_2_edc0357_1357846887042_384245_20436_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887042_384245_20436_exists", (Boolean) false, this);
                    if (_17_0_2_2_edc0357_1357846887039_694624_20430_exists == null) _17_0_2_2_edc0357_1357846887039_694624_20430_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887039_694624_20430_exists", (Boolean) false, this);
                    Object effect168VarV = sig_17_0_2_2_edc0357_1357846887046_32321_20444;
                    effect168Var = new Parameter("effect168Var", null, null, this);
                    addDependency(effect168Var, new Expression(effect168VarV));
                    effect168 = new EffectFunction(new EffectFunction(effect168Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect169VarV = sig_17_0_2_2_edc0357_1357846887046_481600_20446;
                    effect169Var = new Parameter("effect169Var", null, null, this);
                    addDependency(effect169Var, new Expression(effect169VarV));
                    effect169 = new EffectFunction(new EffectFunction(effect169Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect170VarV = decider_17_0_2_2_edc0357_1357846887039_694624_20430;
                    effect170Var = new Parameter("effect170Var", null, null, this);
                    addDependency(effect170Var, new Expression(effect170VarV));
                    effect170 = new EffectFunction(new EffectFunction(effect170Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class, java.lang.Boolean.class), new Object[] { endTime, myDeciderID_decider_17_0_2_2_edc0357_1357846887039_694624_20430, addToDecider__17_0_2_2_edc0357_1357846887039_694624_20430 }));
                    Object effect171VarV = decider_17_0_2_2_edc0357_1357846887042_384245_20436;
                    effect171Var = new Parameter("effect171Var", null, null, this);
                    addDependency(effect171Var, new Expression(effect171VarV));
                    effect171 = new EffectFunction(new EffectFunction(effect171Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class, java.lang.Boolean.class), new Object[] { endTime, myDeciderID_decider_17_0_2_2_edc0357_1357846887042_384245_20436, addToDecider__17_0_2_2_edc0357_1357846887042_384245_20436 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846887042_5499_20435Collections() {
                parameters.add(myDeciderID_decider_17_0_2_2_edc0357_1357846887042_384245_20436);
                parameters.add(addToDecider__17_0_2_2_edc0357_1357846887042_384245_20436);
                parameters.add(addToDecider__17_0_2_2_edc0357_1357846887039_694624_20430);
                parameters.add(myDeciderID_decider_17_0_2_2_edc0357_1357846887039_694624_20430);
                parameters.add(objectToPass);
                parameters.add(_17_0_2_2_edc0357_1357846887042_384245_20436_exists);
                parameters.add(_17_0_2_2_edc0357_1357846887039_694624_20430_exists);
                Set<Effect> effectsForeffect168Var = new TreeSet<Effect>();
                effectsForeffect168Var.add(effect168);
                addEffects((Parameter<?>) effect168Var, effectsForeffect168Var);
                Set<Effect> effectsForeffect169Var = new TreeSet<Effect>();
                effectsForeffect169Var.add(effect169);
                addEffects((Parameter<?>) effect169Var, effectsForeffect169Var);
                Set<Effect> effectsForeffect170Var = new TreeSet<Effect>();
                effectsForeffect170Var.add(effect170);
                addEffects((Parameter<?>) effect170Var, effectsForeffect170Var);
                Set<Effect> effectsForeffect171Var = new TreeSet<Effect>();
                effectsForeffect171Var.add(effect171);
                addEffects((Parameter<?>) effect171Var, effectsForeffect171Var);
            }

            public void init_17_0_2_2_edc0357_1357846887042_5499_20435Dependencies() {
                addDependency(myDeciderID_decider_17_0_2_2_edc0357_1357846887042_384245_20436, new Expression<Integer>(1));
                addDependency(addToDecider__17_0_2_2_edc0357_1357846887042_384245_20436, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887040_4366_20432_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887040_4366_20432_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(addToDecider__17_0_2_2_edc0357_1357846887039_694624_20430, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887040_4366_20432_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887040_4366_20432_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(myDeciderID_decider_17_0_2_2_edc0357_1357846887039_694624_20430, new Expression<Integer>(2));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887047_969329_20452, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_2_edc0357_1357846887042_384245_20436_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887040_4366_20432_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887040_4366_20432_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887042_384245_20436, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887042_384245_20436, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887042_384245_20436, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_2_edc0357_1357846887042_384245_20436)))))))));
                addDependency(_17_0_2_2_edc0357_1357846887039_694624_20430_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887040_4366_20432_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887040_4366_20432_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887039_694624_20430, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887039_694624_20430, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887039_694624_20430, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_2_edc0357_1357846887039_694624_20430)))))))));
            }

            public void init_17_0_2_2_edc0357_1357846887042_5499_20435Elaborations() {
                init_17_0_2_2_edc0357_1357846887042_5499_20435Dependencies();
                Expression<?>[] arguments172 = new Expression<?>[1];
                arguments172[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition172 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887039_694624_20430_exists);
                elaborationRule172 = addElaborationRule(condition172, _17_0_2_2_edc0357_1357846886592_253983_19882.this, Customer._17_0_2_2_edc0357_1357846886592_253983_19882._17_0_2_2_edc0357_1357846887039_694624_20430.class, "CDR_set_cap_AddStructuralFeatureValueAction_setDRCap", arguments172);
                Expression<?>[] arguments173 = new Expression<?>[1];
                arguments173[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition173 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887042_384245_20436_exists);
                elaborationRule173 = addElaborationRule(condition173, _17_0_2_2_edc0357_1357846886592_253983_19882.this, Customer._17_0_2_2_edc0357_1357846886592_253983_19882._17_0_2_2_edc0357_1357846887042_384245_20436.class, "CDR_send_yes_SendSignalAction_setDRCap", arguments173);
            }
        }

        public class _17_0_2_2_edc0357_1357846887042_384245_20436 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846887042_384245_20436() {
                super();
                init_17_0_2_2_edc0357_1357846887042_384245_20436Members();
                init_17_0_2_2_edc0357_1357846887042_384245_20436Collections();
                init_17_0_2_2_edc0357_1357846887042_384245_20436Elaborations();
            }

            public _17_0_2_2_edc0357_1357846887042_384245_20436(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846887042_384245_20436Members();
                init_17_0_2_2_edc0357_1357846887042_384245_20436Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846887042_384245_20436Elaborations();
                fixTimeDependencies();
            }

            public Parameter< Customer > _17_0_2_2_edc0357_1357846887472_383588_21451 = null;

            public IntegerParameter _17_0_2_2_edc0357_1357846887472_259733_21452 = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846887045_595974_20441_exists = null;

            public BooleanParameter objectToPass = null;

            public Parameter< Power_System.Signalyes > signalObject = null;

            public Dependency _17_0_2_2_edc0357_1357846887472_383588_21451Dependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887472_259733_21452Dependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887045_595974_20441_existsDependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Dependency signalObjectDependency = null;

            public Effect effect174 = null;

            public Parameter effect174Var = null;

            public Effect effect175 = null;

            public Parameter effect175Var = null;

            public Effect effect176 = null;

            public Parameter effect176Var = null;

            public ElaborationRule elaborationRule177 = null;

            public void init_17_0_2_2_edc0357_1357846887042_384245_20436Members() {
                try {
                    if (_17_0_2_2_edc0357_1357846887472_383588_21451 == null) _17_0_2_2_edc0357_1357846887472_383588_21451 = new Parameter<Customer>("_17_0_2_2_edc0357_1357846887472_383588_21451", null, (Customer) null, this);
                    if (_17_0_2_2_edc0357_1357846887472_259733_21452 == null) _17_0_2_2_edc0357_1357846887472_259733_21452 = new IntegerParameter("_17_0_2_2_edc0357_1357846887472_259733_21452", (Integer) null, this);
                    if (_17_0_2_2_edc0357_1357846887045_595974_20441_exists == null) _17_0_2_2_edc0357_1357846887045_595974_20441_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887045_595974_20441_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (signalObject == null) signalObject = new Parameter<Power_System.Signalyes>("signalObject", null, (Power_System.Signalyes) null, this);
                    Object effect174VarV = sig_17_0_2_2_edc0357_1357846887047_421984_20458;
                    effect174Var = new Parameter("effect174Var", null, null, this);
                    addDependency(effect174Var, new Expression(effect174VarV));
                    effect174 = new EffectFunction(new EffectFunction(effect174Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect175VarV = new FunctionCall(x, Parameter.class, "getMember", new Object[] { "ss_17_0_2_2_edc0357_1357846886597_47094_19888_yes" });
                    effect175Var = new Parameter("effect175Var", null, null, this);
                    addDependency(effect175Var, new Expression(effect175VarV));
                    effect175 = new EffectFunction(new EffectFunction(null, ClassUtils.getMethodForArgTypes("ObjectFlow<Signalyes>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { signalObject, endTime }, effect175Var));
                    Object effect176VarV = new FunctionCall(signalObject, Parameter.class, "getMember", new Object[] { "newLoad__17_0_2_2_edc0357_1357846886652_849760_19964" });
                    effect176Var = new Parameter("effect176Var", null, null, this);
                    addDependency(effect176Var, new Expression(effect176VarV));
                    effect176 = new EffectFunction(new EffectFunction(null, ClassUtils.getMethodForArgTypes("TimeVaryingMap<Integer>", "PowerSystem3", "setValue", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, _17_0_2_2_edc0357_1357846887472_259733_21452 }, effect176Var));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846887042_384245_20436Collections() {
                parameters.add(_17_0_2_2_edc0357_1357846887472_383588_21451);
                parameters.add(_17_0_2_2_edc0357_1357846887472_259733_21452);
                parameters.add(_17_0_2_2_edc0357_1357846887045_595974_20441_exists);
                parameters.add(objectToPass);
                parameters.add(signalObject);
                Set<Effect> effectsForeffect174Var = new TreeSet<Effect>();
                effectsForeffect174Var.add(effect174);
                addEffects((Parameter<?>) effect174Var, effectsForeffect174Var);
                Set<Effect> effectsForeffect175Var = new TreeSet<Effect>();
                effectsForeffect175Var.add(effect175);
                addEffects((Parameter<?>) effect175Var, effectsForeffect175Var);
                Set<Effect> effectsForeffect176Var = new TreeSet<Effect>();
                effectsForeffect176Var.add(effect176);
                addEffects((Parameter<?>) effect176Var, effectsForeffect176Var);
            }

            public void init_17_0_2_2_edc0357_1357846887042_384245_20436Dependencies() {
                addDependency(_17_0_2_2_edc0357_1357846887472_383588_21451, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887046_680237_20449, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_2_edc0357_1357846887472_259733_21452, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887046_481600_20446, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_2_edc0357_1357846887045_595974_20441_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887040_4366_20432_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887040_4366_20432_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887046_583925_20445, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(signalObject, new Expression<Power_System.Signalyes>(new ConstructorCall(x, ClassUtils.getConstructorForArgTypes(Power_System.Signalyes.class), new Object[] {})));
            }

            public void init_17_0_2_2_edc0357_1357846887042_384245_20436Elaborations() {
                init_17_0_2_2_edc0357_1357846887042_384245_20436Dependencies();
                Expression<?>[] arguments177 = new Expression<?>[2];
                arguments177[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                arguments177[1] = new Expression<ObjectFlow<Boolean>>(sig_17_0_2_2_edc0357_1357846887047_421984_20458);
                Expression<Boolean> condition177 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887045_595974_20441_exists);
                elaborationRule177 = addElaborationRule(condition177, _17_0_2_2_edc0357_1357846886592_253983_19882.this, Customer._17_0_2_2_edc0357_1357846886592_253983_19882._17_0_2_2_edc0357_1357846887045_595974_20441.class, "CDR_merge_response_MergeNode_setDRCap", arguments177);
            }
        }

        public class _17_0_2_2_edc0357_1357846887043_278131_20437 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846887043_278131_20437() {
                super();
                init_17_0_2_2_edc0357_1357846887043_278131_20437Members();
                init_17_0_2_2_edc0357_1357846887043_278131_20437Collections();
                init_17_0_2_2_edc0357_1357846887043_278131_20437Elaborations();
            }

            public _17_0_2_2_edc0357_1357846887043_278131_20437(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846887043_278131_20437Members();
                init_17_0_2_2_edc0357_1357846887043_278131_20437Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846887043_278131_20437Elaborations();
                fixTimeDependencies();
            }

            public IntegerParameter myDeciderID_decider_17_0_2_2_edc0357_1357846887041_36636_20434 = null;

            public IntegerParameter myDeciderID_decider_17_0_2_2_edc0357_1357846887042_384245_20436 = null;

            public BooleanParameter addToDecider__17_0_2_2_edc0357_1357846887042_384245_20436 = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846887041_36636_20434_exists = null;

            public BooleanParameter addToDecider__17_0_2_2_edc0357_1357846887039_694624_20430 = null;

            public IntegerParameter myDeciderID_decider_17_0_2_2_edc0357_1357846887039_694624_20430 = null;

            public Parameter< Customer > objectToPass = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846887042_384245_20436_exists = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846887039_694624_20430_exists = null;

            public BooleanParameter addToDecider__17_0_2_2_edc0357_1357846887041_36636_20434 = null;

            public Dependency myDeciderID_decider_17_0_2_2_edc0357_1357846887041_36636_20434Dependency = null;

            public Dependency myDeciderID_decider_17_0_2_2_edc0357_1357846887042_384245_20436Dependency = null;

            public Dependency addToDecider__17_0_2_2_edc0357_1357846887042_384245_20436Dependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887041_36636_20434_existsDependency = null;

            public Dependency addToDecider__17_0_2_2_edc0357_1357846887039_694624_20430Dependency = null;

            public Dependency durationDependency = null;

            public Dependency myDeciderID_decider_17_0_2_2_edc0357_1357846887039_694624_20430Dependency = null;

            public Dependency objectToPassDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887042_384245_20436_existsDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887039_694624_20430_existsDependency = null;

            public Dependency addToDecider__17_0_2_2_edc0357_1357846887041_36636_20434Dependency = null;

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

            public Effect effect183 = null;

            public Parameter effect183Var = null;

            public ElaborationRule elaborationRule184 = null;

            public ElaborationRule elaborationRule185 = null;

            public ElaborationRule elaborationRule186 = null;

            public void init_17_0_2_2_edc0357_1357846887043_278131_20437Members() {
                try {
                    if (myDeciderID_decider_17_0_2_2_edc0357_1357846887041_36636_20434 == null) myDeciderID_decider_17_0_2_2_edc0357_1357846887041_36636_20434 = new IntegerParameter("myDeciderID_decider_17_0_2_2_edc0357_1357846887041_36636_20434", (Integer) 1, this);
                    if (myDeciderID_decider_17_0_2_2_edc0357_1357846887042_384245_20436 == null) myDeciderID_decider_17_0_2_2_edc0357_1357846887042_384245_20436 = new IntegerParameter("myDeciderID_decider_17_0_2_2_edc0357_1357846887042_384245_20436", (Integer) 2, this);
                    if (addToDecider__17_0_2_2_edc0357_1357846887042_384245_20436 == null) addToDecider__17_0_2_2_edc0357_1357846887042_384245_20436 = new BooleanParameter("addToDecider__17_0_2_2_edc0357_1357846887042_384245_20436", (Boolean) false, this);
                    if (_17_0_2_2_edc0357_1357846887041_36636_20434_exists == null) _17_0_2_2_edc0357_1357846887041_36636_20434_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887041_36636_20434_exists", (Boolean) false, this);
                    if (addToDecider__17_0_2_2_edc0357_1357846887039_694624_20430 == null) addToDecider__17_0_2_2_edc0357_1357846887039_694624_20430 = new BooleanParameter("addToDecider__17_0_2_2_edc0357_1357846887039_694624_20430", (Boolean) false, this);
                    if (myDeciderID_decider_17_0_2_2_edc0357_1357846887039_694624_20430 == null) myDeciderID_decider_17_0_2_2_edc0357_1357846887039_694624_20430 = new IntegerParameter("myDeciderID_decider_17_0_2_2_edc0357_1357846887039_694624_20430", (Integer) 1, this);
                    if (objectToPass == null) objectToPass = new Parameter<Customer>("objectToPass", null, (Customer) null, this);
                    if (_17_0_2_2_edc0357_1357846887042_384245_20436_exists == null) _17_0_2_2_edc0357_1357846887042_384245_20436_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887042_384245_20436_exists", (Boolean) false, this);
                    if (_17_0_2_2_edc0357_1357846887039_694624_20430_exists == null) _17_0_2_2_edc0357_1357846887039_694624_20430_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887039_694624_20430_exists", (Boolean) false, this);
                    if (addToDecider__17_0_2_2_edc0357_1357846887041_36636_20434 == null) addToDecider__17_0_2_2_edc0357_1357846887041_36636_20434 = new BooleanParameter("addToDecider__17_0_2_2_edc0357_1357846887041_36636_20434", (Boolean) false, this);
                    Object effect178VarV = sig_17_0_2_2_edc0357_1357846887046_187814_20448;
                    effect178Var = new Parameter("effect178Var", null, null, this);
                    addDependency(effect178Var, new Expression(effect178VarV));
                    effect178 = new EffectFunction(new EffectFunction(effect178Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect179VarV = sig_17_0_2_2_edc0357_1357846887046_680237_20449;
                    effect179Var = new Parameter("effect179Var", null, null, this);
                    addDependency(effect179Var, new Expression(effect179VarV));
                    effect179 = new EffectFunction(new EffectFunction(effect179Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect180VarV = sig_17_0_2_2_edc0357_1357846887046_73630_20450;
                    effect180Var = new Parameter("effect180Var", null, null, this);
                    addDependency(effect180Var, new Expression(effect180VarV));
                    effect180 = new EffectFunction(new EffectFunction(effect180Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect181VarV = decider_17_0_2_2_edc0357_1357846887039_694624_20430;
                    effect181Var = new Parameter("effect181Var", null, null, this);
                    addDependency(effect181Var, new Expression(effect181VarV));
                    effect181 = new EffectFunction(new EffectFunction(effect181Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class, java.lang.Boolean.class), new Object[] { endTime, myDeciderID_decider_17_0_2_2_edc0357_1357846887039_694624_20430, addToDecider__17_0_2_2_edc0357_1357846887039_694624_20430 }));
                    Object effect182VarV = decider_17_0_2_2_edc0357_1357846887042_384245_20436;
                    effect182Var = new Parameter("effect182Var", null, null, this);
                    addDependency(effect182Var, new Expression(effect182VarV));
                    effect182 = new EffectFunction(new EffectFunction(effect182Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class, java.lang.Boolean.class), new Object[] { endTime, myDeciderID_decider_17_0_2_2_edc0357_1357846887042_384245_20436, addToDecider__17_0_2_2_edc0357_1357846887042_384245_20436 }));
                    Object effect183VarV = decider_17_0_2_2_edc0357_1357846887041_36636_20434;
                    effect183Var = new Parameter("effect183Var", null, null, this);
                    addDependency(effect183Var, new Expression(effect183VarV));
                    effect183 = new EffectFunction(new EffectFunction(effect183Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class, java.lang.Boolean.class), new Object[] { endTime, myDeciderID_decider_17_0_2_2_edc0357_1357846887041_36636_20434, addToDecider__17_0_2_2_edc0357_1357846887041_36636_20434 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846887043_278131_20437Collections() {
                parameters.add(myDeciderID_decider_17_0_2_2_edc0357_1357846887041_36636_20434);
                parameters.add(myDeciderID_decider_17_0_2_2_edc0357_1357846887042_384245_20436);
                parameters.add(addToDecider__17_0_2_2_edc0357_1357846887042_384245_20436);
                parameters.add(_17_0_2_2_edc0357_1357846887041_36636_20434_exists);
                parameters.add(addToDecider__17_0_2_2_edc0357_1357846887039_694624_20430);
                parameters.add(myDeciderID_decider_17_0_2_2_edc0357_1357846887039_694624_20430);
                parameters.add(objectToPass);
                parameters.add(_17_0_2_2_edc0357_1357846887042_384245_20436_exists);
                parameters.add(_17_0_2_2_edc0357_1357846887039_694624_20430_exists);
                parameters.add(addToDecider__17_0_2_2_edc0357_1357846887041_36636_20434);
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
                Set<Effect> effectsForeffect183Var = new TreeSet<Effect>();
                effectsForeffect183Var.add(effect183);
                addEffects((Parameter<?>) effect183Var, effectsForeffect183Var);
            }

            public void init_17_0_2_2_edc0357_1357846887043_278131_20437Dependencies() {
                addDependency(myDeciderID_decider_17_0_2_2_edc0357_1357846887041_36636_20434, new Expression<Integer>(1));
                addDependency(myDeciderID_decider_17_0_2_2_edc0357_1357846887042_384245_20436, new Expression<Integer>(2));
                addDependency(addToDecider__17_0_2_2_edc0357_1357846887042_384245_20436, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887040_4366_20432_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887040_4366_20432_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(_17_0_2_2_edc0357_1357846887041_36636_20434_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887040_4366_20432_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887040_4366_20432_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887041_36636_20434, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887041_36636_20434, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887041_36636_20434, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_2_edc0357_1357846887041_36636_20434)))))))));
                addDependency(addToDecider__17_0_2_2_edc0357_1357846887039_694624_20430, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887040_4366_20432_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887040_4366_20432_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(myDeciderID_decider_17_0_2_2_edc0357_1357846887039_694624_20430, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887046_642363_20447, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_2_edc0357_1357846887042_384245_20436_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887040_4366_20432_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887040_4366_20432_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887042_384245_20436, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887042_384245_20436, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887042_384245_20436, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_2_edc0357_1357846887042_384245_20436)))))))));
                addDependency(_17_0_2_2_edc0357_1357846887039_694624_20430_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887040_4366_20432_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887040_4366_20432_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887039_694624_20430, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887039_694624_20430, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887039_694624_20430, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_2_edc0357_1357846887039_694624_20430)))))))));
                addDependency(addToDecider__17_0_2_2_edc0357_1357846887041_36636_20434, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887040_4366_20432_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887040_4366_20432_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
            }

            public void init_17_0_2_2_edc0357_1357846887043_278131_20437Elaborations() {
                init_17_0_2_2_edc0357_1357846887043_278131_20437Dependencies();
                Expression<?>[] arguments184 = new Expression<?>[1];
                arguments184[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition184 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887039_694624_20430_exists);
                elaborationRule184 = addElaborationRule(condition184, _17_0_2_2_edc0357_1357846886592_253983_19882.this, Customer._17_0_2_2_edc0357_1357846886592_253983_19882._17_0_2_2_edc0357_1357846887039_694624_20430.class, "CDR_set_cap_AddStructuralFeatureValueAction_setDRCap", arguments184);
                Expression<?>[] arguments185 = new Expression<?>[1];
                arguments185[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition185 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887042_384245_20436_exists);
                elaborationRule185 = addElaborationRule(condition185, _17_0_2_2_edc0357_1357846886592_253983_19882.this, Customer._17_0_2_2_edc0357_1357846886592_253983_19882._17_0_2_2_edc0357_1357846887042_384245_20436.class, "CDR_send_yes_SendSignalAction_setDRCap", arguments185);
                Expression<?>[] arguments186 = new Expression<?>[1];
                arguments186[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition186 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887041_36636_20434_exists);
                elaborationRule186 = addElaborationRule(condition186, _17_0_2_2_edc0357_1357846886592_253983_19882.this, Customer._17_0_2_2_edc0357_1357846886592_253983_19882._17_0_2_2_edc0357_1357846887041_36636_20434.class, "CDR_send_no_SendSignalAction_setDRCap", arguments186);
            }
        }

        public class _17_0_2_2_edc0357_1357846887043_980664_20438 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846887043_980664_20438() {
                super();
                init_17_0_2_2_edc0357_1357846887043_980664_20438Members();
                init_17_0_2_2_edc0357_1357846887043_980664_20438Collections();
                init_17_0_2_2_edc0357_1357846887043_980664_20438Elaborations();
            }

            public _17_0_2_2_edc0357_1357846887043_980664_20438(Expression<Integer> startTime, Expression<Integer> _17_0_2_2_edc0357_1357846887026_851341_20407) {
                super();
                init_17_0_2_2_edc0357_1357846887043_980664_20438Members();
                init_17_0_2_2_edc0357_1357846887043_980664_20438Collections();
                addDependency(this.startTime, startTime);
                addDependency(this._17_0_2_2_edc0357_1357846887026_851341_20407, _17_0_2_2_edc0357_1357846887026_851341_20407);
                init_17_0_2_2_edc0357_1357846887043_980664_20438Elaborations();
                fixTimeDependencies();
            }

            public IntegerParameter _17_0_2_2_edc0357_1357846887026_851341_20407 = null;

            public IntegerParameter objectToPass = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846887042_5499_20435_exists = null;

            public Dependency objectToPassDependency = null;

            public Dependency durationDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887042_5499_20435_existsDependency = null;

            public Effect effect187 = null;

            public Parameter effect187Var = null;

            public ElaborationRule elaborationRule188 = null;

            public void init_17_0_2_2_edc0357_1357846887043_980664_20438Members() {
                try {
                    if (_17_0_2_2_edc0357_1357846887026_851341_20407 == null) _17_0_2_2_edc0357_1357846887026_851341_20407 = new IntegerParameter("_17_0_2_2_edc0357_1357846887026_851341_20407", (Integer) null, this);
                    if (objectToPass == null) objectToPass = new IntegerParameter("objectToPass", (Integer) null, this);
                    if (_17_0_2_2_edc0357_1357846887042_5499_20435_exists == null) _17_0_2_2_edc0357_1357846887042_5499_20435_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887042_5499_20435_exists", (Boolean) false, this);
                    Object effect187VarV = sig_17_0_2_2_edc0357_1357846887047_969329_20452;
                    effect187Var = new Parameter("effect187Var", null, null, this);
                    addDependency(effect187Var, new Expression(effect187VarV));
                    effect187 = new EffectFunction(new EffectFunction(effect187Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846887043_980664_20438Collections() {
                parameters.add(_17_0_2_2_edc0357_1357846887026_851341_20407);
                parameters.add(objectToPass);
                parameters.add(_17_0_2_2_edc0357_1357846887042_5499_20435_exists);
                Set<Effect> effectsForeffect187Var = new TreeSet<Effect>();
                effectsForeffect187Var.add(effect187);
                addEffects((Parameter<?>) effect187Var, effectsForeffect187Var);
            }

            public void init_17_0_2_2_edc0357_1357846887043_980664_20438Dependencies() {
                addDependency(objectToPass, new Expression<Integer>(_17_0_2_2_edc0357_1357846887026_851341_20407));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(_17_0_2_2_edc0357_1357846887042_5499_20435_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887040_4366_20432_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887040_4366_20432_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
            }

            public void init_17_0_2_2_edc0357_1357846887043_980664_20438Elaborations() {
                init_17_0_2_2_edc0357_1357846887043_980664_20438Dependencies();
                Expression<?>[] arguments188 = new Expression<?>[1];
                arguments188[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition188 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887042_5499_20435_exists);
                elaborationRule188 = addElaborationRule(condition188, _17_0_2_2_edc0357_1357846886592_253983_19882.this, Customer._17_0_2_2_edc0357_1357846886592_253983_19882._17_0_2_2_edc0357_1357846887042_5499_20435.class, "CDR_fork2_ForkNode_setDRCap", arguments188);
            }
        }

        public class _17_0_2_2_edc0357_1357846887044_821300_20439 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846887044_821300_20439() {
                super();
                init_17_0_2_2_edc0357_1357846887044_821300_20439Members();
                init_17_0_2_2_edc0357_1357846887044_821300_20439Collections();
                init_17_0_2_2_edc0357_1357846887044_821300_20439Elaborations();
            }

            public _17_0_2_2_edc0357_1357846887044_821300_20439(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846887044_821300_20439Members();
                init_17_0_2_2_edc0357_1357846887044_821300_20439Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846887044_821300_20439Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter addToDecider__17_0_2_2_edc0357_1357846887041_741375_20433 = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846887473_750698_21454 = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846887041_741375_20433_exists = null;

            public BooleanParameter objectToPass = null;

            public IntegerParameter myDeciderID_decider_17_0_2_2_edc0357_1357846887041_741375_20433 = null;

            public Dependency addToDecider__17_0_2_2_edc0357_1357846887041_741375_20433Dependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887473_750698_21454Dependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887041_741375_20433_existsDependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Dependency myDeciderID_decider_17_0_2_2_edc0357_1357846887041_741375_20433Dependency = null;

            public Effect effect189 = null;

            public Parameter effect189Var = null;

            public Effect effect190 = null;

            public Parameter effect190Var = null;

            public ElaborationRule elaborationRule191 = null;

            public void init_17_0_2_2_edc0357_1357846887044_821300_20439Members() {
                try {
                    if (addToDecider__17_0_2_2_edc0357_1357846887041_741375_20433 == null) addToDecider__17_0_2_2_edc0357_1357846887041_741375_20433 = new BooleanParameter("addToDecider__17_0_2_2_edc0357_1357846887041_741375_20433", (Boolean) false, this);
                    if (_17_0_2_2_edc0357_1357846887473_750698_21454 == null) _17_0_2_2_edc0357_1357846887473_750698_21454 = new BooleanParameter("_17_0_2_2_edc0357_1357846887473_750698_21454", (Boolean) false, this);
                    if (_17_0_2_2_edc0357_1357846887041_741375_20433_exists == null) _17_0_2_2_edc0357_1357846887041_741375_20433_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887041_741375_20433_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (myDeciderID_decider_17_0_2_2_edc0357_1357846887041_741375_20433 == null) myDeciderID_decider_17_0_2_2_edc0357_1357846887041_741375_20433 = new IntegerParameter("myDeciderID_decider_17_0_2_2_edc0357_1357846887041_741375_20433", (Integer) 2, this);
                    Object effect189VarV = sig_17_0_2_2_edc0357_1357846887047_993678_20453;
                    effect189Var = new Parameter("effect189Var", null, null, this);
                    addDependency(effect189Var, new Expression(effect189VarV));
                    effect189 = new EffectFunction(new EffectFunction(effect189Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { _17_0_2_2_edc0357_1357846887473_750698_21454, endTime }));
                    Object effect190VarV = decider_17_0_2_2_edc0357_1357846887041_741375_20433;
                    effect190Var = new Parameter("effect190Var", null, null, this);
                    addDependency(effect190Var, new Expression(effect190VarV));
                    effect190 = new EffectFunction(new EffectFunction(effect190Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class, java.lang.Boolean.class), new Object[] { endTime, myDeciderID_decider_17_0_2_2_edc0357_1357846887041_741375_20433, addToDecider__17_0_2_2_edc0357_1357846887041_741375_20433 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846887044_821300_20439Collections() {
                parameters.add(addToDecider__17_0_2_2_edc0357_1357846887041_741375_20433);
                parameters.add(_17_0_2_2_edc0357_1357846887473_750698_21454);
                parameters.add(_17_0_2_2_edc0357_1357846887041_741375_20433_exists);
                parameters.add(objectToPass);
                parameters.add(myDeciderID_decider_17_0_2_2_edc0357_1357846887041_741375_20433);
                Set<Effect> effectsForeffect189Var = new TreeSet<Effect>();
                effectsForeffect189Var.add(effect189);
                addEffects((Parameter<?>) effect189Var, effectsForeffect189Var);
                Set<Effect> effectsForeffect190Var = new TreeSet<Effect>();
                effectsForeffect190Var.add(effect190);
                addEffects((Parameter<?>) effect190Var, effectsForeffect190Var);
            }

            public void init_17_0_2_2_edc0357_1357846887044_821300_20439Dependencies() {
                addDependency(addToDecider__17_0_2_2_edc0357_1357846887041_741375_20433, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887040_4366_20432_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887040_4366_20432_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(_17_0_2_2_edc0357_1357846887473_750698_21454, new Expression<Boolean>(false));
                addDependency(_17_0_2_2_edc0357_1357846887041_741375_20433_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887040_4366_20432_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887040_4366_20432_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887041_741375_20433, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887041_741375_20433, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887041_741375_20433, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_2_edc0357_1357846887041_741375_20433)))))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887047_168775_20456, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(myDeciderID_decider_17_0_2_2_edc0357_1357846887041_741375_20433, new Expression<Integer>(2));
            }

            public void init_17_0_2_2_edc0357_1357846887044_821300_20439Elaborations() {
                init_17_0_2_2_edc0357_1357846887044_821300_20439Dependencies();
                Expression<?>[] arguments191 = new Expression<?>[1];
                arguments191[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition191 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887041_741375_20433_exists);
                elaborationRule191 = addElaborationRule(condition191, _17_0_2_2_edc0357_1357846886592_253983_19882.this, Customer._17_0_2_2_edc0357_1357846886592_253983_19882._17_0_2_2_edc0357_1357846887041_741375_20433.class, "CDR_decide_particip_DecisionNode_setDRCap", arguments191);
            }
        }

        public class _17_0_2_2_edc0357_1357846887045_71209_20440 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846887045_71209_20440() {
                super();
                init_17_0_2_2_edc0357_1357846887045_71209_20440Members();
                init_17_0_2_2_edc0357_1357846887045_71209_20440Collections();
                init_17_0_2_2_edc0357_1357846887045_71209_20440Elaborations();
            }

            public _17_0_2_2_edc0357_1357846887045_71209_20440(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846887045_71209_20440Members();
                init_17_0_2_2_edc0357_1357846887045_71209_20440Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846887045_71209_20440Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_2_edc0357_1357846887039_144118_20429_exists = null;

            public BooleanParameter objectToPass = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846887044_821300_20439_exists = null;

            public Dependency _17_0_2_2_edc0357_1357846887039_144118_20429_existsDependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887044_821300_20439_existsDependency = null;

            public Effect effect192 = null;

            public Parameter effect192Var = null;

            public Effect effect193 = null;

            public Parameter effect193Var = null;

            public ElaborationRule elaborationRule194 = null;

            public ElaborationRule elaborationRule195 = null;

            public void init_17_0_2_2_edc0357_1357846887045_71209_20440Members() {
                try {
                    if (_17_0_2_2_edc0357_1357846887039_144118_20429_exists == null) _17_0_2_2_edc0357_1357846887039_144118_20429_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887039_144118_20429_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (_17_0_2_2_edc0357_1357846887044_821300_20439_exists == null) _17_0_2_2_edc0357_1357846887044_821300_20439_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887044_821300_20439_exists", (Boolean) false, this);
                    Object effect192VarV = sig_17_0_2_2_edc0357_1357846887047_957196_20455;
                    effect192Var = new Parameter("effect192Var", null, null, this);
                    addDependency(effect192Var, new Expression(effect192VarV));
                    effect192 = new EffectFunction(new EffectFunction(effect192Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect193VarV = sig_17_0_2_2_edc0357_1357846887047_168775_20456;
                    effect193Var = new Parameter("effect193Var", null, null, this);
                    addDependency(effect193Var, new Expression(effect193VarV));
                    effect193 = new EffectFunction(new EffectFunction(effect193Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846887045_71209_20440Collections() {
                parameters.add(_17_0_2_2_edc0357_1357846887039_144118_20429_exists);
                parameters.add(objectToPass);
                parameters.add(_17_0_2_2_edc0357_1357846887044_821300_20439_exists);
                Set<Effect> effectsForeffect192Var = new TreeSet<Effect>();
                effectsForeffect192Var.add(effect192);
                addEffects((Parameter<?>) effect192Var, effectsForeffect192Var);
                Set<Effect> effectsForeffect193Var = new TreeSet<Effect>();
                effectsForeffect193Var.add(effect193);
                addEffects((Parameter<?>) effect193Var, effectsForeffect193Var);
            }

            public void init_17_0_2_2_edc0357_1357846887045_71209_20440Dependencies() {
                addDependency(_17_0_2_2_edc0357_1357846887039_144118_20429_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887040_4366_20432_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887040_4366_20432_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887047_727669_20454, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_2_edc0357_1357846887044_821300_20439_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887040_4366_20432_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887040_4366_20432_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
            }

            public void init_17_0_2_2_edc0357_1357846887045_71209_20440Elaborations() {
                init_17_0_2_2_edc0357_1357846887045_71209_20440Dependencies();
                Expression<?>[] arguments194 = new Expression<?>[1];
                arguments194[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition194 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887044_821300_20439_exists);
                elaborationRule194 = addElaborationRule(condition194, _17_0_2_2_edc0357_1357846886592_253983_19882.this, Customer._17_0_2_2_edc0357_1357846886592_253983_19882._17_0_2_2_edc0357_1357846887044_821300_20439.class, "CDR_participation_spec_ValueSpecificationAction_setDRCap", arguments194);
                Expression<?>[] arguments195 = new Expression<?>[1];
                arguments195[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition195 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887039_144118_20429_exists);
                elaborationRule195 = addElaborationRule(condition195, _17_0_2_2_edc0357_1357846886592_253983_19882.this, Customer._17_0_2_2_edc0357_1357846886592_253983_19882._17_0_2_2_edc0357_1357846887039_144118_20429.class, "CDR_readself_ReadSelfAction_setDRCap", arguments195);
            }
        }

        public class _17_0_2_2_edc0357_1357846887045_595974_20441 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846887045_595974_20441() {
                super();
                init_17_0_2_2_edc0357_1357846887045_595974_20441Members();
                init_17_0_2_2_edc0357_1357846887045_595974_20441Collections();
                init_17_0_2_2_edc0357_1357846887045_595974_20441Elaborations();
            }

            public _17_0_2_2_edc0357_1357846887045_595974_20441(Expression<Integer> startTime, Expression<ObjectFlow<Boolean>> receiveThis) {
                super();
                init_17_0_2_2_edc0357_1357846887045_595974_20441Members();
                init_17_0_2_2_edc0357_1357846887045_595974_20441Collections();
                addDependency(this.startTime, startTime);
                addDependency(this.receiveThis, receiveThis);
                init_17_0_2_2_edc0357_1357846887045_595974_20441Elaborations();
                fixTimeDependencies();
            }

            public Parameter< ObjectFlow<Boolean> > receiveThis = null;

            public BooleanParameter objectToPass = null;

            public Dependency finalNode_startTimeDependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Effect effect196 = null;

            public Parameter effect196Var = null;

            public void init_17_0_2_2_edc0357_1357846887045_595974_20441Members() {
                try {
                    if (receiveThis == null) receiveThis = new Parameter<ObjectFlow<Boolean>>("receiveThis", null, (ObjectFlow<Boolean>) null, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect196VarV = sig_17_0_2_2_edc0357_1357846887047_738382_20459;
                    effect196Var = new Parameter("effect196Var", null, null, this);
                    addDependency(effect196Var, new Expression(effect196VarV));
                    effect196 = new EffectFunction(new EffectFunction(effect196Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846887045_595974_20441Collections() {
                parameters.add(receiveThis);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect196Var = new TreeSet<Effect>();
                effectsForeffect196Var.add(effect196);
                addEffects((Parameter<?>) effect196Var, effectsForeffect196Var);
            }

            public void init_17_0_2_2_edc0357_1357846887045_595974_20441Dependencies() {
                addDependency(finalNode_startTime, new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression(new EffectFunction(receiveThis, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_2_edc0357_1357846887045_595974_20441Elaborations() {
                init_17_0_2_2_edc0357_1357846887045_595974_20441Dependencies();
            }
        }

        public _17_0_2_2_edc0357_1357846886592_253983_19882(Expression<Integer> startTime, Expression<_17_0_2_2_edc0357_1357846886592_253983_19882_interface> caller, Expression<Integer> _17_0_2_2_edc0357_1357846887026_851341_20407) {
            super();
            init_17_0_2_2_edc0357_1357846886592_253983_19882Members();
            init_17_0_2_2_edc0357_1357846886592_253983_19882Collections();
            addDependency(this.startTime, startTime);
            addDependency(this.caller, caller);
            addDependency(this._17_0_2_2_edc0357_1357846887026_851341_20407, _17_0_2_2_edc0357_1357846887026_851341_20407);
            init_17_0_2_2_edc0357_1357846886592_253983_19882Elaborations();
            fixTimeDependencies();
        }

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_2_edc0357_1357846887046_762014_20443 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_2_edc0357_1357846887047_128067_20451 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_2_edc0357_1357846887047_727669_20454 = null;

        public Parameter< Customer._17_0_2_2_edc0357_1357846886592_253983_19882_interface > caller = null;

        public Parameter< TimeVaryingList<Integer> > decider_17_0_2_2_edc0357_1357846887042_384245_20436 = null;

        public Parameter< TimeVaryingList<Integer> > decider_17_0_2_2_edc0357_1357846887041_741375_20433 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_2_edc0357_1357846887047_993678_20453 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_2_edc0357_1357846887047_421984_20458 = null;

        public IntegerParameter _17_0_2_2_edc0357_1357846887026_851341_20407 = null;

        public Parameter< ObjectFlow<Customer> > sig_17_0_2_2_edc0357_1357846887046_187814_20448 = null;

        public Parameter< TimeVaryingList<Integer> > decider_17_0_2_2_edc0357_1357846887041_36636_20434 = null;

        public IntegerParameter finalNode_endTime = null;

        public Parameter< ObjectFlow<Customer> > sig_17_0_2_2_edc0357_1357846887046_680237_20449 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_2_edc0357_1357846887047_168775_20456 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_2_2_edc0357_1357846887046_481600_20446 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_2_edc0357_1357846887046_583925_20445 = null;

        public Parameter< ObjectFlow<Customer> > sig_17_0_2_2_edc0357_1357846887046_73630_20450 = null;

        public Parameter< TimeVaryingList<Integer> > decider_17_0_2_2_edc0357_1357846887039_694624_20430 = null;

        public IntegerParameter finalNode_startTime = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_2_edc0357_1357846887046_889895_20442 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_2_edc0357_1357846887047_957196_20455 = null;

        public BooleanParameter _17_0_2_2_edc0357_1357846887040_4366_20432_exists = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_2_2_edc0357_1357846887047_969329_20452 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_2_edc0357_1357846887047_738382_20459 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_2_edc0357_1357846887047_977540_20457 = null;

        public Parameter< ObjectFlow<Customer> > sig_17_0_2_2_edc0357_1357846887046_642363_20447 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_2_2_edc0357_1357846887046_32321_20444 = null;

        public Dependency caller_endTimeDependency = null;

        public Dependency endTimeDependency = null;

        public Dependency _17_0_2_2_edc0357_1357846887040_4366_20432_existsDependency = null;

        public ElaborationRule elaborationRule144 = null;

        public ElaborationRule elaborationRule145 = null;

        public ElaborationRule elaborationRule146 = null;

        public void init_17_0_2_2_edc0357_1357846886592_253983_19882Members() {
            try {
                if (sig_17_0_2_2_edc0357_1357846887046_762014_20443 == null) sig_17_0_2_2_edc0357_1357846887046_762014_20443 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_2_edc0357_1357846887046_762014_20443", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887046_762014_20443" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846887047_128067_20451 == null) sig_17_0_2_2_edc0357_1357846887047_128067_20451 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_2_edc0357_1357846887047_128067_20451", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887047_128067_20451" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846887047_727669_20454 == null) sig_17_0_2_2_edc0357_1357846887047_727669_20454 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_2_edc0357_1357846887047_727669_20454", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887047_727669_20454" })).evaluate(true), this);
                if (caller == null) caller = new Parameter<Customer._17_0_2_2_edc0357_1357846886592_253983_19882_interface>("caller", null, (Customer._17_0_2_2_edc0357_1357846886592_253983_19882_interface) null, this);
                if (decider_17_0_2_2_edc0357_1357846887042_384245_20436 == null) decider_17_0_2_2_edc0357_1357846887042_384245_20436 = new Parameter<TimeVaryingList<Integer>>("decider_17_0_2_2_edc0357_1357846887042_384245_20436", null, (TimeVaryingList<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingList.class, java.lang.String.class, int.class), new Object[] { "decider_17_0_2_2_edc0357_1357846887042_384245_20436", 3 })).evaluate(true), this);
                if (decider_17_0_2_2_edc0357_1357846887041_741375_20433 == null) decider_17_0_2_2_edc0357_1357846887041_741375_20433 = new Parameter<TimeVaryingList<Integer>>("decider_17_0_2_2_edc0357_1357846887041_741375_20433", null, (TimeVaryingList<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingList.class, java.lang.String.class, int.class), new Object[] { "decider_17_0_2_2_edc0357_1357846887041_741375_20433", 2 })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846887047_993678_20453 == null) sig_17_0_2_2_edc0357_1357846887047_993678_20453 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_2_edc0357_1357846887047_993678_20453", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887047_993678_20453" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846887047_421984_20458 == null) sig_17_0_2_2_edc0357_1357846887047_421984_20458 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_2_edc0357_1357846887047_421984_20458", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887047_421984_20458" })).evaluate(true), this);
                if (_17_0_2_2_edc0357_1357846887026_851341_20407 == null) _17_0_2_2_edc0357_1357846887026_851341_20407 = new IntegerParameter("_17_0_2_2_edc0357_1357846887026_851341_20407", (Integer) null, this);
                if (sig_17_0_2_2_edc0357_1357846887046_187814_20448 == null) sig_17_0_2_2_edc0357_1357846887046_187814_20448 = new Parameter<ObjectFlow<Customer>>("sig_17_0_2_2_edc0357_1357846887046_187814_20448", null, (ObjectFlow<Customer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887046_187814_20448" })).evaluate(true), this);
                if (decider_17_0_2_2_edc0357_1357846887041_36636_20434 == null) decider_17_0_2_2_edc0357_1357846887041_36636_20434 = new Parameter<TimeVaryingList<Integer>>("decider_17_0_2_2_edc0357_1357846887041_36636_20434", null, (TimeVaryingList<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingList.class, java.lang.String.class, int.class), new Object[] { "decider_17_0_2_2_edc0357_1357846887041_36636_20434", 2 })).evaluate(true), this);
                if (finalNode_endTime == null) finalNode_endTime = new IntegerParameter("finalNode_endTime", (Integer) null, this);
                if (sig_17_0_2_2_edc0357_1357846887046_680237_20449 == null) sig_17_0_2_2_edc0357_1357846887046_680237_20449 = new Parameter<ObjectFlow<Customer>>("sig_17_0_2_2_edc0357_1357846887046_680237_20449", null, (ObjectFlow<Customer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887046_680237_20449" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846887047_168775_20456 == null) sig_17_0_2_2_edc0357_1357846887047_168775_20456 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_2_edc0357_1357846887047_168775_20456", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887047_168775_20456" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846887046_481600_20446 == null) sig_17_0_2_2_edc0357_1357846887046_481600_20446 = new Parameter<ObjectFlow<Integer>>("sig_17_0_2_2_edc0357_1357846887046_481600_20446", null, (ObjectFlow<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887046_481600_20446" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846887046_583925_20445 == null) sig_17_0_2_2_edc0357_1357846887046_583925_20445 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_2_edc0357_1357846887046_583925_20445", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887046_583925_20445" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846887046_73630_20450 == null) sig_17_0_2_2_edc0357_1357846887046_73630_20450 = new Parameter<ObjectFlow<Customer>>("sig_17_0_2_2_edc0357_1357846887046_73630_20450", null, (ObjectFlow<Customer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887046_73630_20450" })).evaluate(true), this);
                if (decider_17_0_2_2_edc0357_1357846887039_694624_20430 == null) decider_17_0_2_2_edc0357_1357846887039_694624_20430 = new Parameter<TimeVaryingList<Integer>>("decider_17_0_2_2_edc0357_1357846887039_694624_20430", null, (TimeVaryingList<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingList.class, java.lang.String.class, int.class), new Object[] { "decider_17_0_2_2_edc0357_1357846887039_694624_20430", 3 })).evaluate(true), this);
                if (finalNode_startTime == null) finalNode_startTime = new IntegerParameter("finalNode_startTime", (Integer) null, this);
                if (sig_17_0_2_2_edc0357_1357846887046_889895_20442 == null) sig_17_0_2_2_edc0357_1357846887046_889895_20442 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_2_edc0357_1357846887046_889895_20442", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887046_889895_20442" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846887047_957196_20455 == null) sig_17_0_2_2_edc0357_1357846887047_957196_20455 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_2_edc0357_1357846887047_957196_20455", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887047_957196_20455" })).evaluate(true), this);
                if (_17_0_2_2_edc0357_1357846887040_4366_20432_exists == null) _17_0_2_2_edc0357_1357846887040_4366_20432_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887040_4366_20432_exists", (Boolean) false, this);
                if (sig_17_0_2_2_edc0357_1357846887047_969329_20452 == null) sig_17_0_2_2_edc0357_1357846887047_969329_20452 = new Parameter<ObjectFlow<Integer>>("sig_17_0_2_2_edc0357_1357846887047_969329_20452", null, (ObjectFlow<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887047_969329_20452" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846887047_738382_20459 == null) sig_17_0_2_2_edc0357_1357846887047_738382_20459 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_2_edc0357_1357846887047_738382_20459", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887047_738382_20459" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846887047_977540_20457 == null) sig_17_0_2_2_edc0357_1357846887047_977540_20457 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_2_edc0357_1357846887047_977540_20457", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887047_977540_20457" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846887046_642363_20447 == null) sig_17_0_2_2_edc0357_1357846887046_642363_20447 = new Parameter<ObjectFlow<Customer>>("sig_17_0_2_2_edc0357_1357846887046_642363_20447", null, (ObjectFlow<Customer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887046_642363_20447" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846887046_32321_20444 == null) sig_17_0_2_2_edc0357_1357846887046_32321_20444 = new Parameter<ObjectFlow<Integer>>("sig_17_0_2_2_edc0357_1357846887046_32321_20444", null, (ObjectFlow<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887046_32321_20444" })).evaluate(true), this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void init_17_0_2_2_edc0357_1357846886592_253983_19882Collections() {
            parameters.add(sig_17_0_2_2_edc0357_1357846887046_762014_20443);
            parameters.add(sig_17_0_2_2_edc0357_1357846887047_128067_20451);
            parameters.add(sig_17_0_2_2_edc0357_1357846887047_727669_20454);
            parameters.add(caller);
            parameters.add(decider_17_0_2_2_edc0357_1357846887042_384245_20436);
            parameters.add(decider_17_0_2_2_edc0357_1357846887041_741375_20433);
            parameters.add(sig_17_0_2_2_edc0357_1357846887047_993678_20453);
            parameters.add(sig_17_0_2_2_edc0357_1357846887047_421984_20458);
            parameters.add(_17_0_2_2_edc0357_1357846887026_851341_20407);
            parameters.add(sig_17_0_2_2_edc0357_1357846887046_187814_20448);
            parameters.add(decider_17_0_2_2_edc0357_1357846887041_36636_20434);
            parameters.add(finalNode_endTime);
            parameters.add(sig_17_0_2_2_edc0357_1357846887046_680237_20449);
            parameters.add(sig_17_0_2_2_edc0357_1357846887047_168775_20456);
            parameters.add(sig_17_0_2_2_edc0357_1357846887046_481600_20446);
            parameters.add(sig_17_0_2_2_edc0357_1357846887046_583925_20445);
            parameters.add(sig_17_0_2_2_edc0357_1357846887046_73630_20450);
            parameters.add(decider_17_0_2_2_edc0357_1357846887039_694624_20430);
            parameters.add(finalNode_startTime);
            parameters.add(sig_17_0_2_2_edc0357_1357846887046_889895_20442);
            parameters.add(sig_17_0_2_2_edc0357_1357846887047_957196_20455);
            parameters.add(_17_0_2_2_edc0357_1357846887040_4366_20432_exists);
            parameters.add(sig_17_0_2_2_edc0357_1357846887047_969329_20452);
            parameters.add(sig_17_0_2_2_edc0357_1357846887047_738382_20459);
            parameters.add(sig_17_0_2_2_edc0357_1357846887047_977540_20457);
            parameters.add(sig_17_0_2_2_edc0357_1357846887046_642363_20447);
            parameters.add(sig_17_0_2_2_edc0357_1357846887046_32321_20444);
        }

        public void init_17_0_2_2_edc0357_1357846886592_253983_19882Dependencies() {
            caller.getValue(true).addDependency(caller.getValue(true).endTime, new Expression<Integer>(finalNode_endTime));
            addDependency(endTime, new Expression<Integer>(finalNode_endTime));
            addDependency(_17_0_2_2_edc0357_1357846887040_4366_20432_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.NotEquals(new Expression<Integer>(finalNode_startTime), null)), new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887047_738382_20459, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(new Functions.Minus(new Expression<Integer>(finalNode_startTime), new Expression<Integer>(1))) })))));
        }

        public void init_17_0_2_2_edc0357_1357846886592_253983_19882Elaborations() {
            init_17_0_2_2_edc0357_1357846886592_253983_19882Dependencies();
            Expression<?>[] arguments144 = new Expression<?>[1];
            arguments144[0] = new Expression<Integer>(startTime);
            Expression<Boolean> condition144 = new Expression<Boolean>(true);
            elaborationRule144 = addElaborationRule(condition144, _17_0_2_2_edc0357_1357846886592_253983_19882.this, Customer._17_0_2_2_edc0357_1357846886592_253983_19882._17_0_2_2_edc0357_1357846887040_5059_20431.class, "CDR_start_InitialNode_setDRCap", arguments144);
            Expression<?>[] arguments145 = new Expression<?>[1];
            arguments145[0] = new Expression<Integer>(finalNode_startTime);
            Expression<Boolean> condition145 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887040_4366_20432_exists);
            elaborationRule145 = addElaborationRule(condition145, _17_0_2_2_edc0357_1357846886592_253983_19882.this, Customer._17_0_2_2_edc0357_1357846886592_253983_19882._17_0_2_2_edc0357_1357846887040_4366_20432.class, "CDR_final_ActivityFinalNode_setDRCap", arguments145);
            Expression<?>[] arguments146 = new Expression<?>[2];
            arguments146[0] = new Expression<Integer>(startTime);
            arguments146[1] = new Expression<Integer>(_17_0_2_2_edc0357_1357846887026_851341_20407);
            Expression<Boolean> condition146 = new Expression<Boolean>(true);
            elaborationRule146 = addElaborationRule(condition146, _17_0_2_2_edc0357_1357846886592_253983_19882.this, Customer._17_0_2_2_edc0357_1357846886592_253983_19882._17_0_2_2_edc0357_1357846887043_980664_20438.class, "CDR_capval_ActivityParameterNode_setDRCap", arguments146);
        }
    }

    public class _17_0_2_2_edc0357_1357846886592_253983_19882_interface extends DurativeEvent {

        public _17_0_2_2_edc0357_1357846886592_253983_19882_interface() {
            super();
            init_17_0_2_2_edc0357_1357846886592_253983_19882_interfaceMembers();
            init_17_0_2_2_edc0357_1357846886592_253983_19882_interfaceCollections();
            init_17_0_2_2_edc0357_1357846886592_253983_19882_interfaceElaborations();
        }

        public IntegerParameter _17_0_2_2_edc0357_1357846887026_851341_20407 = null;

        public void init_17_0_2_2_edc0357_1357846886592_253983_19882_interfaceMembers() {
            try {
                if (_17_0_2_2_edc0357_1357846887026_851341_20407 == null) _17_0_2_2_edc0357_1357846887026_851341_20407 = new IntegerParameter("_17_0_2_2_edc0357_1357846887026_851341_20407", (Integer) null, this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void init_17_0_2_2_edc0357_1357846886592_253983_19882_interfaceCollections() {
            parameters.add(_17_0_2_2_edc0357_1357846887026_851341_20407);
        }

        public void init_17_0_2_2_edc0357_1357846886592_253983_19882_interfaceDependencies() {
        }

        public void init_17_0_2_2_edc0357_1357846886592_253983_19882_interfaceElaborations() {
            init_17_0_2_2_edc0357_1357846886592_253983_19882_interfaceDependencies();
        }
    }

    public class _17_0_2_2_edc0357_1357846886593_851494_19883 extends DurativeEvent {

        public _17_0_2_2_edc0357_1357846886593_851494_19883() {
            super();
            init_17_0_2_2_edc0357_1357846886593_851494_19883Members();
            init_17_0_2_2_edc0357_1357846886593_851494_19883Collections();
            init_17_0_2_2_edc0357_1357846886593_851494_19883Elaborations();
        }

        public class _17_0_2_2_edc0357_1357846887060_476507_20481 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846887060_476507_20481() {
                super();
                init_17_0_2_2_edc0357_1357846887060_476507_20481Members();
                init_17_0_2_2_edc0357_1357846887060_476507_20481Collections();
                init_17_0_2_2_edc0357_1357846887060_476507_20481Elaborations();
            }

            public _17_0_2_2_edc0357_1357846887060_476507_20481(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846887060_476507_20481Members();
                init_17_0_2_2_edc0357_1357846887060_476507_20481Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846887060_476507_20481Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_2_edc0357_1357846887061_336124_20482_exists = null;

            public BooleanParameter objectToPass = null;

            public Dependency _17_0_2_2_edc0357_1357846887061_336124_20482_existsDependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Effect effect199 = null;

            public Parameter effect199Var = null;

            public ElaborationRule elaborationRule200 = null;

            public void init_17_0_2_2_edc0357_1357846887060_476507_20481Members() {
                try {
                    if (_17_0_2_2_edc0357_1357846887061_336124_20482_exists == null) _17_0_2_2_edc0357_1357846887061_336124_20482_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887061_336124_20482_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect199VarV = sig_17_0_2_2_edc0357_1357846887069_816508_20504;
                    effect199Var = new Parameter("effect199Var", null, null, this);
                    addDependency(effect199Var, new Expression(effect199VarV));
                    effect199 = new EffectFunction(new EffectFunction(effect199Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846887060_476507_20481Collections() {
                parameters.add(_17_0_2_2_edc0357_1357846887061_336124_20482_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect199Var = new TreeSet<Effect>();
                effectsForeffect199Var.add(effect199);
                addEffects((Parameter<?>) effect199Var, effectsForeffect199Var);
            }

            public void init_17_0_2_2_edc0357_1357846887060_476507_20481Dependencies() {
                addDependency(_17_0_2_2_edc0357_1357846887061_336124_20482_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887064_685566_20488_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887064_685566_20488_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(true));
            }

            public void init_17_0_2_2_edc0357_1357846887060_476507_20481Elaborations() {
                init_17_0_2_2_edc0357_1357846887060_476507_20481Dependencies();
                Expression<?>[] arguments200 = new Expression<?>[1];
                arguments200[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition200 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887061_336124_20482_exists);
                elaborationRule200 = addElaborationRule(condition200, _17_0_2_2_edc0357_1357846886593_851494_19883.this, Customer._17_0_2_2_edc0357_1357846886593_851494_19883._17_0_2_2_edc0357_1357846887061_336124_20482.class, "C_startup_activities_CallBehaviorAction_CustomerCB", arguments200);
            }
        }

        public class _17_0_2_2_edc0357_1357846887061_336124_20482 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846887061_336124_20482() {
                super();
                init_17_0_2_2_edc0357_1357846887061_336124_20482Members();
                init_17_0_2_2_edc0357_1357846887061_336124_20482Collections();
                init_17_0_2_2_edc0357_1357846887061_336124_20482Elaborations();
            }

            public _17_0_2_2_edc0357_1357846887061_336124_20482(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846887061_336124_20482Members();
                init_17_0_2_2_edc0357_1357846887061_336124_20482Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846887061_336124_20482Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_2_edc0357_1357846887067_165060_20493_exists = null;

            public BooleanParameter objectToPass = null;

            public Dependency _17_0_2_2_edc0357_1357846887067_165060_20493_existsDependency = null;

            public Dependency objectToPassDependency = null;

            public Effect effect201 = null;

            public Parameter effect201Var = null;

            public ElaborationRule elaborationRule202 = null;

            public ElaborationRule elaborationRule203 = null;

            public void init_17_0_2_2_edc0357_1357846887061_336124_20482Members() {
                try {
                    if (_17_0_2_2_edc0357_1357846887067_165060_20493_exists == null) _17_0_2_2_edc0357_1357846887067_165060_20493_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887067_165060_20493_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect201VarV = sig_17_0_2_2_edc0357_1357846887071_689896_20516;
                    effect201Var = new Parameter("effect201Var", null, null, this);
                    addDependency(effect201Var, new Expression(effect201VarV));
                    effect201 = new EffectFunction(new EffectFunction(effect201Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846887061_336124_20482Collections() {
                parameters.add(_17_0_2_2_edc0357_1357846887067_165060_20493_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect201Var = new TreeSet<Effect>();
                effectsForeffect201Var.add(effect201);
                addEffects((Parameter<?>) effect201Var, effectsForeffect201Var);
            }

            public void init_17_0_2_2_edc0357_1357846887061_336124_20482Dependencies() {
                addDependency(_17_0_2_2_edc0357_1357846887067_165060_20493_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887064_685566_20488_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887064_685566_20488_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887069_816508_20504, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_2_edc0357_1357846887061_336124_20482Elaborations() {
                init_17_0_2_2_edc0357_1357846887061_336124_20482Dependencies();
                Expression<?>[] arguments202 = new Expression<?>[2];
                arguments202[0] = new Expression<Integer>(startTime);
                arguments202[1] = new Expression<DurativeEvent>(this);
                Expression<Boolean> condition202 = new Expression<Boolean>(true);
                elaborationRule202 = addElaborationRule(condition202, Customer.this, Customer._17_0_2_2_edc0357_1357846886591_876034_19881.class, "initialize_Activity_Customer", arguments202);
                Expression<?>[] arguments203 = new Expression<?>[1];
                arguments203[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition203 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887067_165060_20493_exists);
                elaborationRule203 = addElaborationRule(condition203, _17_0_2_2_edc0357_1357846886593_851494_19883.this, Customer._17_0_2_2_edc0357_1357846886593_851494_19883._17_0_2_2_edc0357_1357846887067_165060_20493.class, "C_wait_AcceptEventAction_CustomerCB", arguments203);
            }
        }

        public class _17_0_2_2_edc0357_1357846887061_840256_20483 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846887061_840256_20483() {
                super();
                init_17_0_2_2_edc0357_1357846887061_840256_20483Members();
                init_17_0_2_2_edc0357_1357846887061_840256_20483Collections();
                init_17_0_2_2_edc0357_1357846887061_840256_20483Elaborations();
            }

            public _17_0_2_2_edc0357_1357846887061_840256_20483(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846887061_840256_20483Members();
                init_17_0_2_2_edc0357_1357846887061_840256_20483Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846887061_840256_20483Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_2_edc0357_1357846887067_962099_20494_exists = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846887065_743385_20491_exists = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846887066_116514_20492_exists = null;

            public BooleanParameter objectToPass = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846887064_603424_20489_exists = null;

            public Dependency _17_0_2_2_edc0357_1357846887067_962099_20494_existsDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887065_743385_20491_existsDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887066_116514_20492_existsDependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887064_603424_20489_existsDependency = null;

            public Effect effect204 = null;

            public Parameter effect204Var = null;

            public Effect effect205 = null;

            public Parameter effect205Var = null;

            public Effect effect206 = null;

            public Parameter effect206Var = null;

            public Effect effect207 = null;

            public Parameter effect207Var = null;

            public ElaborationRule elaborationRule208 = null;

            public ElaborationRule elaborationRule209 = null;

            public ElaborationRule elaborationRule210 = null;

            public ElaborationRule elaborationRule211 = null;

            public void init_17_0_2_2_edc0357_1357846887061_840256_20483Members() {
                try {
                    if (_17_0_2_2_edc0357_1357846887067_962099_20494_exists == null) _17_0_2_2_edc0357_1357846887067_962099_20494_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887067_962099_20494_exists", (Boolean) false, this);
                    if (_17_0_2_2_edc0357_1357846887065_743385_20491_exists == null) _17_0_2_2_edc0357_1357846887065_743385_20491_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887065_743385_20491_exists", (Boolean) false, this);
                    if (_17_0_2_2_edc0357_1357846887066_116514_20492_exists == null) _17_0_2_2_edc0357_1357846887066_116514_20492_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887066_116514_20492_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (_17_0_2_2_edc0357_1357846887064_603424_20489_exists == null) _17_0_2_2_edc0357_1357846887064_603424_20489_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887064_603424_20489_exists", (Boolean) false, this);
                    Object effect204VarV = sig_17_0_2_2_edc0357_1357846887070_663148_20508;
                    effect204Var = new Parameter("effect204Var", null, null, this);
                    addDependency(effect204Var, new Expression(effect204VarV));
                    effect204 = new EffectFunction(new EffectFunction(effect204Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect205VarV = sig_17_0_2_2_edc0357_1357846887070_351367_20510;
                    effect205Var = new Parameter("effect205Var", null, null, this);
                    addDependency(effect205Var, new Expression(effect205VarV));
                    effect205 = new EffectFunction(new EffectFunction(effect205Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect206VarV = sig_17_0_2_2_edc0357_1357846887071_114679_20515;
                    effect206Var = new Parameter("effect206Var", null, null, this);
                    addDependency(effect206Var, new Expression(effect206VarV));
                    effect206 = new EffectFunction(new EffectFunction(effect206Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect207VarV = sig_17_0_2_2_edc0357_1357846887071_561521_20518;
                    effect207Var = new Parameter("effect207Var", null, null, this);
                    addDependency(effect207Var, new Expression(effect207VarV));
                    effect207 = new EffectFunction(new EffectFunction(effect207Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846887061_840256_20483Collections() {
                parameters.add(_17_0_2_2_edc0357_1357846887067_962099_20494_exists);
                parameters.add(_17_0_2_2_edc0357_1357846887065_743385_20491_exists);
                parameters.add(_17_0_2_2_edc0357_1357846887066_116514_20492_exists);
                parameters.add(objectToPass);
                parameters.add(_17_0_2_2_edc0357_1357846887064_603424_20489_exists);
                Set<Effect> effectsForeffect204Var = new TreeSet<Effect>();
                effectsForeffect204Var.add(effect204);
                addEffects((Parameter<?>) effect204Var, effectsForeffect204Var);
                Set<Effect> effectsForeffect205Var = new TreeSet<Effect>();
                effectsForeffect205Var.add(effect205);
                addEffects((Parameter<?>) effect205Var, effectsForeffect205Var);
                Set<Effect> effectsForeffect206Var = new TreeSet<Effect>();
                effectsForeffect206Var.add(effect206);
                addEffects((Parameter<?>) effect206Var, effectsForeffect206Var);
                Set<Effect> effectsForeffect207Var = new TreeSet<Effect>();
                effectsForeffect207Var.add(effect207);
                addEffects((Parameter<?>) effect207Var, effectsForeffect207Var);
            }

            public void init_17_0_2_2_edc0357_1357846887061_840256_20483Dependencies() {
                addDependency(_17_0_2_2_edc0357_1357846887067_962099_20494_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887064_685566_20488_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887064_685566_20488_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(_17_0_2_2_edc0357_1357846887065_743385_20491_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887064_685566_20488_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887064_685566_20488_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(_17_0_2_2_edc0357_1357846887066_116514_20492_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887064_685566_20488_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887064_685566_20488_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887071_495356_20517, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_2_edc0357_1357846887064_603424_20489_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887064_685566_20488_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887064_685566_20488_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
            }

            public void init_17_0_2_2_edc0357_1357846887061_840256_20483Elaborations() {
                init_17_0_2_2_edc0357_1357846887061_840256_20483Dependencies();
                Expression<?>[] arguments208 = new Expression<?>[2];
                arguments208[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                arguments208[1] = new Expression<ObjectFlow<Boolean>>(sig_17_0_2_2_edc0357_1357846887071_114679_20515);
                Expression<Boolean> condition208 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887066_116514_20492_exists);
                elaborationRule208 = addElaborationRule(condition208, _17_0_2_2_edc0357_1357846886593_851494_19883.this, Customer._17_0_2_2_edc0357_1357846886593_851494_19883._17_0_2_2_edc0357_1357846887066_116514_20492.class, "C_dr_m_MergeNode_CustomerCB", arguments208);
                Expression<?>[] arguments209 = new Expression<?>[2];
                arguments209[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                arguments209[1] = new Expression<ObjectFlow<Boolean>>(sig_17_0_2_2_edc0357_1357846887070_663148_20508);
                Expression<Boolean> condition209 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887064_603424_20489_exists);
                elaborationRule209 = addElaborationRule(condition209, _17_0_2_2_edc0357_1357846886593_851494_19883.this, Customer._17_0_2_2_edc0357_1357846886593_851494_19883._17_0_2_2_edc0357_1357846887064_603424_20489.class, "C_use_m_MergeNode_CustomerCB", arguments209);
                Expression<?>[] arguments210 = new Expression<?>[2];
                arguments210[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                arguments210[1] = new Expression<ObjectFlow<Boolean>>(sig_17_0_2_2_edc0357_1357846887070_351367_20510);
                Expression<Boolean> condition210 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887065_743385_20491_exists);
                elaborationRule210 = addElaborationRule(condition210, _17_0_2_2_edc0357_1357846886593_851494_19883.this, Customer._17_0_2_2_edc0357_1357846886593_851494_19883._17_0_2_2_edc0357_1357846887065_743385_20491.class, "C_change_m_MergeNode_CustomerCB", arguments210);
                Expression<?>[] arguments211 = new Expression<?>[1];
                arguments211[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition211 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887067_962099_20494_exists);
                elaborationRule211 = addElaborationRule(condition211, _17_0_2_2_edc0357_1357846886593_851494_19883.this, Customer._17_0_2_2_edc0357_1357846886593_851494_19883._17_0_2_2_edc0357_1357846887067_962099_20494.class, "C_activity_timer_AcceptEventAction_CustomerCB", arguments211);
            }
        }

        public class _17_0_2_2_edc0357_1357846887062_536309_20484 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846887062_536309_20484() {
                super();
                init_17_0_2_2_edc0357_1357846887062_536309_20484Members();
                init_17_0_2_2_edc0357_1357846887062_536309_20484Collections();
                init_17_0_2_2_edc0357_1357846887062_536309_20484Elaborations();
            }

            public _17_0_2_2_edc0357_1357846887062_536309_20484(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846887062_536309_20484Members();
                init_17_0_2_2_edc0357_1357846887062_536309_20484Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846887062_536309_20484Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter objectToPass = null;

            public Parameter< Power_System.Signaldr_request > _17_0_2_2_edc0357_1357846887480_714344_21477 = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846887065_945394_20490_exists = null;

            public Dependency _17_0_2_2_edc0357_1357846887480_714344_21477Dependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887065_945394_20490_existsDependency = null;

            public Effect effect212 = null;

            public Parameter effect212Var = null;

            public ElaborationRule elaborationRule213 = null;

            public void init_17_0_2_2_edc0357_1357846887062_536309_20484Members() {
                try {
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (_17_0_2_2_edc0357_1357846887480_714344_21477 == null) _17_0_2_2_edc0357_1357846887480_714344_21477 = new Parameter<Power_System.Signaldr_request>("_17_0_2_2_edc0357_1357846887480_714344_21477", null, (Power_System.Signaldr_request) null, this);
                    if (_17_0_2_2_edc0357_1357846887065_945394_20490_exists == null) _17_0_2_2_edc0357_1357846887065_945394_20490_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887065_945394_20490_exists", (Boolean) false, this);
                    Object effect212VarV = sig_17_0_2_2_edc0357_1357846887070_804392_20511;
                    effect212Var = new Parameter("effect212Var", null, null, this);
                    addDependency(effect212Var, new Expression(effect212VarV));
                    effect212 = new EffectFunction(new EffectFunction(effect212Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Signaldr_request>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { _17_0_2_2_edc0357_1357846887480_714344_21477, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846887062_536309_20484Collections() {
                parameters.add(objectToPass);
                parameters.add(_17_0_2_2_edc0357_1357846887480_714344_21477);
                parameters.add(_17_0_2_2_edc0357_1357846887065_945394_20490_exists);
                Set<Effect> effectsForeffect212Var = new TreeSet<Effect>();
                effectsForeffect212Var.add(effect212);
                addEffects((Parameter<?>) effect212Var, effectsForeffect212Var);
            }

            public void init_17_0_2_2_edc0357_1357846887062_536309_20484Dependencies() {
                addDependency(_17_0_2_2_edc0357_1357846887480_714344_21477, new Expression(new EffectFunction(q_Customer_dr_request, ClassUtils.getMethodForArgTypes("ObjectFlow<Signaldr_request>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887070_48169_20513, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_2_edc0357_1357846887065_945394_20490_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887064_685566_20488_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887064_685566_20488_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
            }

            public void init_17_0_2_2_edc0357_1357846887062_536309_20484Elaborations() {
                init_17_0_2_2_edc0357_1357846887062_536309_20484Dependencies();
                Expression<?>[] arguments213 = new Expression<?>[1];
                arguments213[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition213 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887065_945394_20490_exists);
                elaborationRule213 = addElaborationRule(condition213, _17_0_2_2_edc0357_1357846886593_851494_19883.this, Customer._17_0_2_2_edc0357_1357846886593_851494_19883._17_0_2_2_edc0357_1357846887065_945394_20490.class, "C_readCapLevel_ReadStructuralFeatureAction_CustomerCB", arguments213);
            }
        }

        public class _17_0_2_2_edc0357_1357846887062_648648_20485 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846887062_648648_20485() {
                super();
                init_17_0_2_2_edc0357_1357846887062_648648_20485Members();
                init_17_0_2_2_edc0357_1357846887062_648648_20485Collections();
                init_17_0_2_2_edc0357_1357846887062_648648_20485Elaborations();
            }

            public _17_0_2_2_edc0357_1357846887062_648648_20485(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846887062_648648_20485Members();
                init_17_0_2_2_edc0357_1357846887062_648648_20485Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846887062_648648_20485Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter objectToPass = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846887064_603424_20489_exists = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887064_603424_20489_existsDependency = null;

            public Effect effect214 = null;

            public Parameter effect214Var = null;

            public ElaborationRule elaborationRule215 = null;

            public void init_17_0_2_2_edc0357_1357846887062_648648_20485Members() {
                try {
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (_17_0_2_2_edc0357_1357846887064_603424_20489_exists == null) _17_0_2_2_edc0357_1357846887064_603424_20489_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887064_603424_20489_exists", (Boolean) false, this);
                    Object effect214VarV = sig_17_0_2_2_edc0357_1357846887070_255454_20505;
                    effect214Var = new Parameter("effect214Var", null, null, this);
                    addDependency(effect214Var, new Expression(effect214VarV));
                    effect214 = new EffectFunction(new EffectFunction(effect214Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846887062_648648_20485Collections() {
                parameters.add(objectToPass);
                parameters.add(_17_0_2_2_edc0357_1357846887064_603424_20489_exists);
                Set<Effect> effectsForeffect214Var = new TreeSet<Effect>();
                effectsForeffect214Var.add(effect214);
                addEffects((Parameter<?>) effect214Var, effectsForeffect214Var);
            }

            public void init_17_0_2_2_edc0357_1357846887062_648648_20485Dependencies() {
                addDependency(duration, new Expression<Integer>(900));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887070_250209_20507, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_2_edc0357_1357846887064_603424_20489_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887064_685566_20488_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887064_685566_20488_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
            }

            public void init_17_0_2_2_edc0357_1357846887062_648648_20485Elaborations() {
                init_17_0_2_2_edc0357_1357846887062_648648_20485Dependencies();
                Expression<?>[] arguments215 = new Expression<?>[2];
                arguments215[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                arguments215[1] = new Expression<ObjectFlow<Boolean>>(sig_17_0_2_2_edc0357_1357846887070_255454_20505);
                Expression<Boolean> condition215 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887064_603424_20489_exists);
                elaborationRule215 = addElaborationRule(condition215, _17_0_2_2_edc0357_1357846886593_851494_19883.this, Customer._17_0_2_2_edc0357_1357846886593_851494_19883._17_0_2_2_edc0357_1357846887064_603424_20489.class, "C_use_m_MergeNode_CustomerCB", arguments215);
            }
        }

        public class _17_0_2_2_edc0357_1357846887063_263848_20486 extends Customer._17_0_2_2_edc0357_1357846886592_253983_19882_interface {

            public _17_0_2_2_edc0357_1357846887063_263848_20486() {
                super();
                init_17_0_2_2_edc0357_1357846887063_263848_20486Members();
                init_17_0_2_2_edc0357_1357846887063_263848_20486Collections();
                init_17_0_2_2_edc0357_1357846887063_263848_20486Elaborations();
            }

            public _17_0_2_2_edc0357_1357846887063_263848_20486(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846887063_263848_20486Members();
                init_17_0_2_2_edc0357_1357846887063_263848_20486Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846887063_263848_20486Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_2_edc0357_1357846887066_116514_20492_exists = null;

            public BooleanParameter objectToPass = null;

            public IntegerParameter _17_0_2_2_edc0357_1357846887480_656210_21479 = null;

            public Dependency _17_0_2_2_edc0357_1357846887026_851341_20407Dependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887066_116514_20492_existsDependency = null;

            public Dependency objectToPassDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887480_656210_21479Dependency = null;

            public Effect effect216 = null;

            public Parameter effect216Var = null;

            public ElaborationRule elaborationRule217 = null;

            public ElaborationRule elaborationRule218 = null;

            public void init_17_0_2_2_edc0357_1357846887063_263848_20486Members() {
                try {
                    if (_17_0_2_2_edc0357_1357846887066_116514_20492_exists == null) _17_0_2_2_edc0357_1357846887066_116514_20492_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887066_116514_20492_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (_17_0_2_2_edc0357_1357846887480_656210_21479 == null) _17_0_2_2_edc0357_1357846887480_656210_21479 = new IntegerParameter("_17_0_2_2_edc0357_1357846887480_656210_21479", (Integer) null, this);
                    Object effect216VarV = sig_17_0_2_2_edc0357_1357846887070_429172_20514;
                    effect216Var = new Parameter("effect216Var", null, null, this);
                    addDependency(effect216Var, new Expression(effect216VarV));
                    effect216 = new EffectFunction(new EffectFunction(effect216Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846887063_263848_20486Collections() {
                parameters.add(_17_0_2_2_edc0357_1357846887066_116514_20492_exists);
                parameters.add(objectToPass);
                parameters.add(_17_0_2_2_edc0357_1357846887480_656210_21479);
                Set<Effect> effectsForeffect216Var = new TreeSet<Effect>();
                effectsForeffect216Var.add(effect216);
                addEffects((Parameter<?>) effect216Var, effectsForeffect216Var);
            }

            public void init_17_0_2_2_edc0357_1357846887063_263848_20486Dependencies() {
                addDependency(_17_0_2_2_edc0357_1357846887026_851341_20407, new Expression<Integer>(_17_0_2_2_edc0357_1357846887480_656210_21479));
                addDependency(_17_0_2_2_edc0357_1357846887066_116514_20492_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887064_685566_20488_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887064_685566_20488_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(objectToPass, new Expression<Boolean>(true));
                addDependency(_17_0_2_2_edc0357_1357846887480_656210_21479, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887070_864895_20512, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_2_edc0357_1357846887063_263848_20486Elaborations() {
                init_17_0_2_2_edc0357_1357846887063_263848_20486Dependencies();
                Expression<?>[] arguments217 = new Expression<?>[2];
                arguments217[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                arguments217[1] = new Expression<ObjectFlow<Boolean>>(sig_17_0_2_2_edc0357_1357846887070_429172_20514);
                Expression<Boolean> condition217 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887066_116514_20492_exists);
                elaborationRule217 = addElaborationRule(condition217, _17_0_2_2_edc0357_1357846886593_851494_19883.this, Customer._17_0_2_2_edc0357_1357846886593_851494_19883._17_0_2_2_edc0357_1357846887066_116514_20492.class, "C_dr_m_MergeNode_CustomerCB", arguments217);
                Expression<?>[] arguments218 = new Expression<?>[3];
                arguments218[0] = new Expression<Integer>(startTime);
                arguments218[1] = new Expression<_17_0_2_2_edc0357_1357846886592_253983_19882_interface>(this);
                arguments218[2] = new Expression<Integer>(_17_0_2_2_edc0357_1357846887026_851341_20407);
                Expression<Boolean> condition218 = new Expression<Boolean>(true);
                elaborationRule218 = addElaborationRule(condition218, Customer.this, Customer._17_0_2_2_edc0357_1357846886592_253983_19882.class, "setDRCap_Activity_Customer", arguments218);
            }
        }

        public class _17_0_2_2_edc0357_1357846887063_301508_20487 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846887063_301508_20487() {
                super();
                init_17_0_2_2_edc0357_1357846887063_301508_20487Members();
                init_17_0_2_2_edc0357_1357846887063_301508_20487Collections();
                init_17_0_2_2_edc0357_1357846887063_301508_20487Elaborations();
            }

            public _17_0_2_2_edc0357_1357846887063_301508_20487(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846887063_301508_20487Members();
                init_17_0_2_2_edc0357_1357846887063_301508_20487Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846887063_301508_20487Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_2_edc0357_1357846887062_648648_20485_exists = null;

            public BooleanParameter objectToPass = null;

            public Dependency _17_0_2_2_edc0357_1357846887062_648648_20485_existsDependency = null;

            public Dependency objectToPassDependency = null;

            public Effect effect219 = null;

            public Parameter effect219Var = null;

            public ElaborationRule elaborationRule220 = null;

            public ElaborationRule elaborationRule221 = null;

            public void init_17_0_2_2_edc0357_1357846887063_301508_20487Members() {
                try {
                    if (_17_0_2_2_edc0357_1357846887062_648648_20485_exists == null) _17_0_2_2_edc0357_1357846887062_648648_20485_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887062_648648_20485_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect219VarV = sig_17_0_2_2_edc0357_1357846887070_250209_20507;
                    effect219Var = new Parameter("effect219Var", null, null, this);
                    addDependency(effect219Var, new Expression(effect219VarV));
                    effect219 = new EffectFunction(new EffectFunction(effect219Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846887063_301508_20487Collections() {
                parameters.add(_17_0_2_2_edc0357_1357846887062_648648_20485_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect219Var = new TreeSet<Effect>();
                effectsForeffect219Var.add(effect219);
                addEffects((Parameter<?>) effect219Var, effectsForeffect219Var);
            }

            public void init_17_0_2_2_edc0357_1357846887063_301508_20487Dependencies() {
                addDependency(_17_0_2_2_edc0357_1357846887062_648648_20485_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887064_685566_20488_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887064_685566_20488_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887070_856387_20506, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_2_edc0357_1357846887063_301508_20487Elaborations() {
                init_17_0_2_2_edc0357_1357846887063_301508_20487Dependencies();
                Expression<?>[] arguments220 = new Expression<?>[1];
                arguments220[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition220 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887062_648648_20485_exists);
                elaborationRule220 = addElaborationRule(condition220, _17_0_2_2_edc0357_1357846886593_851494_19883.this, Customer._17_0_2_2_edc0357_1357846886593_851494_19883._17_0_2_2_edc0357_1357846887062_648648_20485.class, "C_read_timer_AcceptEventAction_CustomerCB", arguments220);
                Expression<?>[] arguments221 = new Expression<?>[2];
                arguments221[0] = new Expression<Integer>(startTime);
                arguments221[1] = new Expression<DurativeEvent>(this);
                Expression<Boolean> condition221 = new Expression<Boolean>(true);
                elaborationRule221 = addElaborationRule(condition221, Customer.this, Customer._17_0_2_2_edc0357_1357846886589_155109_19879.class, "readMeter_Activity_Customer", arguments221);
            }
        }

        public class _17_0_2_2_edc0357_1357846887064_685566_20488 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846887064_685566_20488() {
                super();
                init_17_0_2_2_edc0357_1357846887064_685566_20488Members();
                init_17_0_2_2_edc0357_1357846887064_685566_20488Collections();
                init_17_0_2_2_edc0357_1357846887064_685566_20488Elaborations();
            }

            public _17_0_2_2_edc0357_1357846887064_685566_20488(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846887064_685566_20488Members();
                init_17_0_2_2_edc0357_1357846887064_685566_20488Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846887064_685566_20488Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter objectToPass = null;

            public Dependency endTimeDependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Dependency finalNode_endTimeDependency = null;

            public void init_17_0_2_2_edc0357_1357846887064_685566_20488Members() {
                try {
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846887064_685566_20488Collections() {
                parameters.add(objectToPass);
            }

            public void init_17_0_2_2_edc0357_1357846887064_685566_20488Dependencies() {
                addDependency(endTime, new Expression<Integer>(new Functions.Plus(new Expression<Integer>(startTime), new Expression<Integer>(duration))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887071_131721_20519, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(finalNode_endTime, new Expression<Integer>(endTime));
            }

            public void init_17_0_2_2_edc0357_1357846887064_685566_20488Elaborations() {
                init_17_0_2_2_edc0357_1357846887064_685566_20488Dependencies();
            }
        }

        public class _17_0_2_2_edc0357_1357846887064_603424_20489 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846887064_603424_20489() {
                super();
                init_17_0_2_2_edc0357_1357846887064_603424_20489Members();
                init_17_0_2_2_edc0357_1357846887064_603424_20489Collections();
                init_17_0_2_2_edc0357_1357846887064_603424_20489Elaborations();
            }

            public _17_0_2_2_edc0357_1357846887064_603424_20489(Expression<Integer> startTime, Expression<ObjectFlow<Boolean>> receiveThis) {
                super();
                init_17_0_2_2_edc0357_1357846887064_603424_20489Members();
                init_17_0_2_2_edc0357_1357846887064_603424_20489Collections();
                addDependency(this.startTime, startTime);
                addDependency(this.receiveThis, receiveThis);
                init_17_0_2_2_edc0357_1357846887064_603424_20489Elaborations();
                fixTimeDependencies();
            }

            public Parameter< ObjectFlow<Boolean> > receiveThis = null;

            public BooleanParameter objectToPass = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846887063_301508_20487_exists = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887063_301508_20487_existsDependency = null;

            public Effect effect222 = null;

            public Parameter effect222Var = null;

            public ElaborationRule elaborationRule223 = null;

            public void init_17_0_2_2_edc0357_1357846887064_603424_20489Members() {
                try {
                    if (receiveThis == null) receiveThis = new Parameter<ObjectFlow<Boolean>>("receiveThis", null, (ObjectFlow<Boolean>) null, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (_17_0_2_2_edc0357_1357846887063_301508_20487_exists == null) _17_0_2_2_edc0357_1357846887063_301508_20487_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887063_301508_20487_exists", (Boolean) false, this);
                    Object effect222VarV = sig_17_0_2_2_edc0357_1357846887070_856387_20506;
                    effect222Var = new Parameter("effect222Var", null, null, this);
                    addDependency(effect222Var, new Expression(effect222VarV));
                    effect222 = new EffectFunction(new EffectFunction(effect222Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846887064_603424_20489Collections() {
                parameters.add(receiveThis);
                parameters.add(objectToPass);
                parameters.add(_17_0_2_2_edc0357_1357846887063_301508_20487_exists);
                Set<Effect> effectsForeffect222Var = new TreeSet<Effect>();
                effectsForeffect222Var.add(effect222);
                addEffects((Parameter<?>) effect222Var, effectsForeffect222Var);
            }

            public void init_17_0_2_2_edc0357_1357846887064_603424_20489Dependencies() {
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression(new EffectFunction(receiveThis, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_2_edc0357_1357846887063_301508_20487_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887064_685566_20488_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887064_685566_20488_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
            }

            public void init_17_0_2_2_edc0357_1357846887064_603424_20489Elaborations() {
                init_17_0_2_2_edc0357_1357846887064_603424_20489Dependencies();
                Expression<?>[] arguments223 = new Expression<?>[1];
                arguments223[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition223 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887063_301508_20487_exists);
                elaborationRule223 = addElaborationRule(condition223, _17_0_2_2_edc0357_1357846886593_851494_19883.this, Customer._17_0_2_2_edc0357_1357846886593_851494_19883._17_0_2_2_edc0357_1357846887063_301508_20487.class, "C_readMeter_CallBehaviorAction_CustomerCB", arguments223);
            }
        }

        public class _17_0_2_2_edc0357_1357846887065_945394_20490 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846887065_945394_20490() {
                super();
                init_17_0_2_2_edc0357_1357846887065_945394_20490Members();
                init_17_0_2_2_edc0357_1357846887065_945394_20490Collections();
                init_17_0_2_2_edc0357_1357846887065_945394_20490Elaborations();
            }

            public _17_0_2_2_edc0357_1357846887065_945394_20490(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846887065_945394_20490Members();
                init_17_0_2_2_edc0357_1357846887065_945394_20490Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846887065_945394_20490Elaborations();
                fixTimeDependencies();
            }

            public Parameter< Power_System.Signaldr_request > _17_0_2_2_edc0357_1357846887481_406764_21481 = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846887063_263848_20486_exists = null;

            public IntegerParameter _17_0_2_2_edc0357_1357846887481_369139_21480 = null;

            public Dependency _17_0_2_2_edc0357_1357846887481_406764_21481Dependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887063_263848_20486_existsDependency = null;

            public Dependency durationDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887481_369139_21480Dependency = null;

            public Effect effect224 = null;

            public Parameter effect224Var = null;

            public ElaborationRule elaborationRule225 = null;

            public void init_17_0_2_2_edc0357_1357846887065_945394_20490Members() {
                try {
                    if (_17_0_2_2_edc0357_1357846887481_406764_21481 == null) _17_0_2_2_edc0357_1357846887481_406764_21481 = new Parameter<Power_System.Signaldr_request>("_17_0_2_2_edc0357_1357846887481_406764_21481", null, (Power_System.Signaldr_request) null, this);
                    if (_17_0_2_2_edc0357_1357846887063_263848_20486_exists == null) _17_0_2_2_edc0357_1357846887063_263848_20486_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887063_263848_20486_exists", (Boolean) false, this);
                    if (_17_0_2_2_edc0357_1357846887481_369139_21480 == null) _17_0_2_2_edc0357_1357846887481_369139_21480 = new IntegerParameter("_17_0_2_2_edc0357_1357846887481_369139_21480", (Integer) null, this);
                    Object effect224VarV = sig_17_0_2_2_edc0357_1357846887070_864895_20512;
                    effect224Var = new Parameter("effect224Var", null, null, this);
                    addDependency(effect224Var, new Expression(effect224VarV));
                    effect224 = new EffectFunction(new EffectFunction(effect224Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { _17_0_2_2_edc0357_1357846887481_369139_21480, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846887065_945394_20490Collections() {
                parameters.add(_17_0_2_2_edc0357_1357846887481_406764_21481);
                parameters.add(_17_0_2_2_edc0357_1357846887063_263848_20486_exists);
                parameters.add(_17_0_2_2_edc0357_1357846887481_369139_21480);
                Set<Effect> effectsForeffect224Var = new TreeSet<Effect>();
                effectsForeffect224Var.add(effect224);
                addEffects((Parameter<?>) effect224Var, effectsForeffect224Var);
            }

            public void init_17_0_2_2_edc0357_1357846887065_945394_20490Dependencies() {
                addDependency(_17_0_2_2_edc0357_1357846887481_406764_21481, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887070_804392_20511, ClassUtils.getMethodForArgTypes("ObjectFlow<Signaldr_request>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_2_edc0357_1357846887063_263848_20486_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887064_685566_20488_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887064_685566_20488_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(_17_0_2_2_edc0357_1357846887481_369139_21480, new Expression(new EffectFunction(null, ClassUtils.getMethodForArgTypes("TimeVaryingMap<Integer>", "PowerSystem3", "getValue", java.lang.Integer.class), new Object[] { new Expression<Integer>(startTime) }, new FunctionCall(_17_0_2_2_edc0357_1357846887481_406764_21481, Parameter.class, "getMember", new Object[] { "cap__17_0_2_2_edc0357_1357846886652_171397_19963" }))));
            }

            public void init_17_0_2_2_edc0357_1357846887065_945394_20490Elaborations() {
                init_17_0_2_2_edc0357_1357846887065_945394_20490Dependencies();
                Expression<?>[] arguments225 = new Expression<?>[1];
                arguments225[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition225 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887063_263848_20486_exists);
                elaborationRule225 = addElaborationRule(condition225, _17_0_2_2_edc0357_1357846886593_851494_19883.this, Customer._17_0_2_2_edc0357_1357846886593_851494_19883._17_0_2_2_edc0357_1357846887063_263848_20486.class, "C_setcap_CallBehaviorAction_CustomerCB", arguments225);
            }
        }

        public class _17_0_2_2_edc0357_1357846887065_743385_20491 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846887065_743385_20491() {
                super();
                init_17_0_2_2_edc0357_1357846887065_743385_20491Members();
                init_17_0_2_2_edc0357_1357846887065_743385_20491Collections();
                init_17_0_2_2_edc0357_1357846887065_743385_20491Elaborations();
            }

            public _17_0_2_2_edc0357_1357846887065_743385_20491(Expression<Integer> startTime, Expression<ObjectFlow<Boolean>> receiveThis) {
                super();
                init_17_0_2_2_edc0357_1357846887065_743385_20491Members();
                init_17_0_2_2_edc0357_1357846887065_743385_20491Collections();
                addDependency(this.startTime, startTime);
                addDependency(this.receiveThis, receiveThis);
                init_17_0_2_2_edc0357_1357846887065_743385_20491Elaborations();
                fixTimeDependencies();
            }

            public Parameter< ObjectFlow<Boolean> > receiveThis = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846887068_314096_20495_exists = null;

            public BooleanParameter objectToPass = null;

            public Dependency _17_0_2_2_edc0357_1357846887068_314096_20495_existsDependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Effect effect226 = null;

            public Parameter effect226Var = null;

            public ElaborationRule elaborationRule227 = null;

            public void init_17_0_2_2_edc0357_1357846887065_743385_20491Members() {
                try {
                    if (receiveThis == null) receiveThis = new Parameter<ObjectFlow<Boolean>>("receiveThis", null, (ObjectFlow<Boolean>) null, this);
                    if (_17_0_2_2_edc0357_1357846887068_314096_20495_exists == null) _17_0_2_2_edc0357_1357846887068_314096_20495_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887068_314096_20495_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect226VarV = sig_17_0_2_2_edc0357_1357846887071_671761_20520;
                    effect226Var = new Parameter("effect226Var", null, null, this);
                    addDependency(effect226Var, new Expression(effect226VarV));
                    effect226 = new EffectFunction(new EffectFunction(effect226Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846887065_743385_20491Collections() {
                parameters.add(receiveThis);
                parameters.add(_17_0_2_2_edc0357_1357846887068_314096_20495_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect226Var = new TreeSet<Effect>();
                effectsForeffect226Var.add(effect226);
                addEffects((Parameter<?>) effect226Var, effectsForeffect226Var);
            }

            public void init_17_0_2_2_edc0357_1357846887065_743385_20491Dependencies() {
                addDependency(_17_0_2_2_edc0357_1357846887068_314096_20495_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887064_685566_20488_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887064_685566_20488_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression(new EffectFunction(receiveThis, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_2_edc0357_1357846887065_743385_20491Elaborations() {
                init_17_0_2_2_edc0357_1357846887065_743385_20491Dependencies();
                Expression<?>[] arguments227 = new Expression<?>[1];
                arguments227[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition227 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887068_314096_20495_exists);
                elaborationRule227 = addElaborationRule(condition227, _17_0_2_2_edc0357_1357846886593_851494_19883.this, Customer._17_0_2_2_edc0357_1357846886593_851494_19883._17_0_2_2_edc0357_1357846887068_314096_20495.class, "C_change_timer_AcceptEventAction_CustomerCB", arguments227);
            }
        }

        public class _17_0_2_2_edc0357_1357846887066_116514_20492 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846887066_116514_20492() {
                super();
                init_17_0_2_2_edc0357_1357846887066_116514_20492Members();
                init_17_0_2_2_edc0357_1357846887066_116514_20492Collections();
                init_17_0_2_2_edc0357_1357846887066_116514_20492Elaborations();
            }

            public _17_0_2_2_edc0357_1357846887066_116514_20492(Expression<Integer> startTime, Expression<ObjectFlow<Boolean>> receiveThis) {
                super();
                init_17_0_2_2_edc0357_1357846887066_116514_20492Members();
                init_17_0_2_2_edc0357_1357846887066_116514_20492Collections();
                addDependency(this.startTime, startTime);
                addDependency(this.receiveThis, receiveThis);
                init_17_0_2_2_edc0357_1357846887066_116514_20492Elaborations();
                fixTimeDependencies();
            }

            public Parameter< ObjectFlow<Boolean> > receiveThis = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846887062_536309_20484_exists = null;

            public BooleanParameter objectToPass = null;

            public Dependency endTimeDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887062_536309_20484_existsDependency = null;

            public Dependency objectToPassDependency = null;

            public Effect effect228 = null;

            public Parameter effect228Var = null;

            public ElaborationRule elaborationRule229 = null;

            public void init_17_0_2_2_edc0357_1357846887066_116514_20492Members() {
                try {
                    if (receiveThis == null) receiveThis = new Parameter<ObjectFlow<Boolean>>("receiveThis", null, (ObjectFlow<Boolean>) null, this);
                    if (_17_0_2_2_edc0357_1357846887062_536309_20484_exists == null) _17_0_2_2_edc0357_1357846887062_536309_20484_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887062_536309_20484_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect228VarV = sig_17_0_2_2_edc0357_1357846887070_48169_20513;
                    effect228Var = new Parameter("effect228Var", null, null, this);
                    addDependency(effect228Var, new Expression(effect228VarV));
                    effect228 = new EffectFunction(new EffectFunction(effect228Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846887066_116514_20492Collections() {
                parameters.add(receiveThis);
                parameters.add(_17_0_2_2_edc0357_1357846887062_536309_20484_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect228Var = new TreeSet<Effect>();
                effectsForeffect228Var.add(effect228);
                addEffects((Parameter<?>) effect228Var, effectsForeffect228Var);
            }

            public void init_17_0_2_2_edc0357_1357846887066_116514_20492Dependencies() {
                addDependency(endTime, new Expression(new FunctionCall(null, ClassUtils.getMethodForArgTypes("Math", "PowerSystem3", "min", int.class, int.class), new Object[] { new Expression(new EffectFunction(q_Customer_dr_request, ClassUtils.getMethodForArgTypes("ObjectFlow<Signaldr_request>", "PowerSystem3", "nextTimeHasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(startTime) })), new Expression<Integer>(finalNode_endTime) })));
                addDependency(_17_0_2_2_edc0357_1357846887062_536309_20484_exists, new Expression<Boolean>(new Functions.And(new Expression(new EffectFunction(q_Customer_dr_request, ClassUtils.getMethodForArgTypes("ObjectFlow<Signaldr_request>", "PowerSystem3", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887064_685566_20488_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887064_685566_20488_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))))));
                addDependency(objectToPass, new Expression(new EffectFunction(receiveThis, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_2_edc0357_1357846887066_116514_20492Elaborations() {
                init_17_0_2_2_edc0357_1357846887066_116514_20492Dependencies();
                Expression<?>[] arguments229 = new Expression<?>[1];
                arguments229[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition229 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887062_536309_20484_exists);
                elaborationRule229 = addElaborationRule(condition229, _17_0_2_2_edc0357_1357846886593_851494_19883.this, Customer._17_0_2_2_edc0357_1357846886593_851494_19883._17_0_2_2_edc0357_1357846887062_536309_20484.class, "C_get_drsig_AcceptEventAction_CustomerCB", arguments229);
            }
        }

        public class _17_0_2_2_edc0357_1357846887067_165060_20493 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846887067_165060_20493() {
                super();
                init_17_0_2_2_edc0357_1357846887067_165060_20493Members();
                init_17_0_2_2_edc0357_1357846887067_165060_20493Collections();
                init_17_0_2_2_edc0357_1357846887067_165060_20493Elaborations();
            }

            public _17_0_2_2_edc0357_1357846887067_165060_20493(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846887067_165060_20493Members();
                init_17_0_2_2_edc0357_1357846887067_165060_20493Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846887067_165060_20493Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_2_edc0357_1357846887061_840256_20483_exists = null;

            public BooleanParameter objectToPass = null;

            public Dependency _17_0_2_2_edc0357_1357846887061_840256_20483_existsDependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Effect effect230 = null;

            public Parameter effect230Var = null;

            public ElaborationRule elaborationRule231 = null;

            public void init_17_0_2_2_edc0357_1357846887067_165060_20493Members() {
                try {
                    if (_17_0_2_2_edc0357_1357846887061_840256_20483_exists == null) _17_0_2_2_edc0357_1357846887061_840256_20483_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887061_840256_20483_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect230VarV = sig_17_0_2_2_edc0357_1357846887071_495356_20517;
                    effect230Var = new Parameter("effect230Var", null, null, this);
                    addDependency(effect230Var, new Expression(effect230VarV));
                    effect230 = new EffectFunction(new EffectFunction(effect230Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846887067_165060_20493Collections() {
                parameters.add(_17_0_2_2_edc0357_1357846887061_840256_20483_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect230Var = new TreeSet<Effect>();
                effectsForeffect230Var.add(effect230);
                addEffects((Parameter<?>) effect230Var, effectsForeffect230Var);
            }

            public void init_17_0_2_2_edc0357_1357846887067_165060_20493Dependencies() {
                addDependency(_17_0_2_2_edc0357_1357846887061_840256_20483_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887064_685566_20488_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887064_685566_20488_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(300));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887071_689896_20516, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_2_edc0357_1357846887067_165060_20493Elaborations() {
                init_17_0_2_2_edc0357_1357846887067_165060_20493Dependencies();
                Expression<?>[] arguments231 = new Expression<?>[1];
                arguments231[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition231 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887061_840256_20483_exists);
                elaborationRule231 = addElaborationRule(condition231, _17_0_2_2_edc0357_1357846886593_851494_19883.this, Customer._17_0_2_2_edc0357_1357846886593_851494_19883._17_0_2_2_edc0357_1357846887061_840256_20483.class, "C_fork_ForkNode_CustomerCB", arguments231);
            }
        }

        public class _17_0_2_2_edc0357_1357846887067_962099_20494 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846887067_962099_20494() {
                super();
                init_17_0_2_2_edc0357_1357846887067_962099_20494Members();
                init_17_0_2_2_edc0357_1357846887067_962099_20494Collections();
                init_17_0_2_2_edc0357_1357846887067_962099_20494Elaborations();
            }

            public _17_0_2_2_edc0357_1357846887067_962099_20494(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846887067_962099_20494Members();
                init_17_0_2_2_edc0357_1357846887067_962099_20494Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846887067_962099_20494Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter objectToPass = null;

            public Dependency finalNode_startTimeDependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Effect effect232 = null;

            public Parameter effect232Var = null;

            public void init_17_0_2_2_edc0357_1357846887067_962099_20494Members() {
                try {
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect232VarV = sig_17_0_2_2_edc0357_1357846887071_131721_20519;
                    effect232Var = new Parameter("effect232Var", null, null, this);
                    addDependency(effect232Var, new Expression(effect232VarV));
                    effect232 = new EffectFunction(new EffectFunction(effect232Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846887067_962099_20494Collections() {
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect232Var = new TreeSet<Effect>();
                effectsForeffect232Var.add(effect232);
                addEffects((Parameter<?>) effect232Var, effectsForeffect232Var);
            }

            public void init_17_0_2_2_edc0357_1357846887067_962099_20494Dependencies() {
                addDependency(finalNode_startTime, new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))));
                addDependency(duration, new Expression<Integer>(32400));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887071_561521_20518, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_2_edc0357_1357846887067_962099_20494Elaborations() {
                init_17_0_2_2_edc0357_1357846887067_962099_20494Dependencies();
            }
        }

        public class _17_0_2_2_edc0357_1357846887068_314096_20495 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846887068_314096_20495() {
                super();
                init_17_0_2_2_edc0357_1357846887068_314096_20495Members();
                init_17_0_2_2_edc0357_1357846887068_314096_20495Collections();
                init_17_0_2_2_edc0357_1357846887068_314096_20495Elaborations();
            }

            public _17_0_2_2_edc0357_1357846887068_314096_20495(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846887068_314096_20495Members();
                init_17_0_2_2_edc0357_1357846887068_314096_20495Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846887068_314096_20495Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_2_edc0357_1357846887068_648525_20496_exists = null;

            public BooleanParameter objectToPass = null;

            public Dependency _17_0_2_2_edc0357_1357846887068_648525_20496_existsDependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Effect effect233 = null;

            public Parameter effect233Var = null;

            public ElaborationRule elaborationRule234 = null;

            public void init_17_0_2_2_edc0357_1357846887068_314096_20495Members() {
                try {
                    if (_17_0_2_2_edc0357_1357846887068_648525_20496_exists == null) _17_0_2_2_edc0357_1357846887068_648525_20496_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887068_648525_20496_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect233VarV = sig_17_0_2_2_edc0357_1357846887071_481120_20521;
                    effect233Var = new Parameter("effect233Var", null, null, this);
                    addDependency(effect233Var, new Expression(effect233VarV));
                    effect233 = new EffectFunction(new EffectFunction(effect233Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846887068_314096_20495Collections() {
                parameters.add(_17_0_2_2_edc0357_1357846887068_648525_20496_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect233Var = new TreeSet<Effect>();
                effectsForeffect233Var.add(effect233);
                addEffects((Parameter<?>) effect233Var, effectsForeffect233Var);
            }

            public void init_17_0_2_2_edc0357_1357846887068_314096_20495Dependencies() {
                addDependency(_17_0_2_2_edc0357_1357846887068_648525_20496_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887064_685566_20488_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887064_685566_20488_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(240));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887071_671761_20520, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_2_edc0357_1357846887068_314096_20495Elaborations() {
                init_17_0_2_2_edc0357_1357846887068_314096_20495Dependencies();
                Expression<?>[] arguments234 = new Expression<?>[1];
                arguments234[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition234 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887068_648525_20496_exists);
                elaborationRule234 = addElaborationRule(condition234, _17_0_2_2_edc0357_1357846886593_851494_19883.this, Customer._17_0_2_2_edc0357_1357846886593_851494_19883._17_0_2_2_edc0357_1357846887068_648525_20496.class, "C_useWithCap_CallBehaviorAction_CustomerCB", arguments234);
            }
        }

        public class _17_0_2_2_edc0357_1357846887068_648525_20496 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846887068_648525_20496() {
                super();
                init_17_0_2_2_edc0357_1357846887068_648525_20496Members();
                init_17_0_2_2_edc0357_1357846887068_648525_20496Collections();
                init_17_0_2_2_edc0357_1357846887068_648525_20496Elaborations();
            }

            public _17_0_2_2_edc0357_1357846887068_648525_20496(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846887068_648525_20496Members();
                init_17_0_2_2_edc0357_1357846887068_648525_20496Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846887068_648525_20496Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_2_edc0357_1357846887065_743385_20491_exists = null;

            public BooleanParameter objectToPass = null;

            public Dependency _17_0_2_2_edc0357_1357846887065_743385_20491_existsDependency = null;

            public Dependency objectToPassDependency = null;

            public Effect effect235 = null;

            public Parameter effect235Var = null;

            public ElaborationRule elaborationRule236 = null;

            public ElaborationRule elaborationRule237 = null;

            public void init_17_0_2_2_edc0357_1357846887068_648525_20496Members() {
                try {
                    if (_17_0_2_2_edc0357_1357846887065_743385_20491_exists == null) _17_0_2_2_edc0357_1357846887065_743385_20491_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887065_743385_20491_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect235VarV = sig_17_0_2_2_edc0357_1357846887070_93350_20509;
                    effect235Var = new Parameter("effect235Var", null, null, this);
                    addDependency(effect235Var, new Expression(effect235VarV));
                    effect235 = new EffectFunction(new EffectFunction(effect235Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846887068_648525_20496Collections() {
                parameters.add(_17_0_2_2_edc0357_1357846887065_743385_20491_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect235Var = new TreeSet<Effect>();
                effectsForeffect235Var.add(effect235);
                addEffects((Parameter<?>) effect235Var, effectsForeffect235Var);
            }

            public void init_17_0_2_2_edc0357_1357846887068_648525_20496Dependencies() {
                addDependency(_17_0_2_2_edc0357_1357846887065_743385_20491_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887064_685566_20488_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887064_685566_20488_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887071_481120_20521, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_2_edc0357_1357846887068_648525_20496Elaborations() {
                init_17_0_2_2_edc0357_1357846887068_648525_20496Dependencies();
                Expression<?>[] arguments236 = new Expression<?>[2];
                arguments236[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                arguments236[1] = new Expression<ObjectFlow<Boolean>>(sig_17_0_2_2_edc0357_1357846887070_93350_20509);
                Expression<Boolean> condition236 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887065_743385_20491_exists);
                elaborationRule236 = addElaborationRule(condition236, _17_0_2_2_edc0357_1357846886593_851494_19883.this, Customer._17_0_2_2_edc0357_1357846886593_851494_19883._17_0_2_2_edc0357_1357846887065_743385_20491.class, "C_change_m_MergeNode_CustomerCB", arguments236);
                Expression<?>[] arguments237 = new Expression<?>[2];
                arguments237[0] = new Expression<Integer>(startTime);
                arguments237[1] = new Expression<DurativeEvent>(this);
                Expression<Boolean> condition237 = new Expression<Boolean>(true);
                elaborationRule237 = addElaborationRule(condition237, Customer.this, Customer._17_0_2_2_edc0357_1357846886594_431163_19884.class, "usePowerWIthCap_Activity_Customer", arguments237);
            }
        }

        public _17_0_2_2_edc0357_1357846886593_851494_19883(Expression<Integer> startTime) {
            super();
            init_17_0_2_2_edc0357_1357846886593_851494_19883Members();
            init_17_0_2_2_edc0357_1357846886593_851494_19883Collections();
            addDependency(this.startTime, startTime);
            init_17_0_2_2_edc0357_1357846886593_851494_19883Elaborations();
            fixTimeDependencies();
        }

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_2_edc0357_1357846887069_816508_20504 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_2_edc0357_1357846887070_48169_20513 = null;

        public Parameter< ObjectFlow<Power_System.Signaldr_request> > sig_17_0_2_2_edc0357_1357846886470_934121_19738 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_2_edc0357_1357846887071_114679_20515 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_2_edc0357_1357846887070_250209_20507 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_2_edc0357_1357846887070_351367_20510 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_2_edc0357_1357846887071_481120_20521 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_2_edc0357_1357846887071_131721_20519 = null;

        public IntegerParameter finalNode_endTime = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_2_edc0357_1357846887071_495356_20517 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_2_edc0357_1357846887071_671761_20520 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_2_edc0357_1357846887071_561521_20518 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_2_edc0357_1357846887070_429172_20514 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_2_edc0357_1357846887070_255454_20505 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_2_edc0357_1357846887070_856387_20506 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_2_edc0357_1357846887070_93350_20509 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_2_2_edc0357_1357846887070_864895_20512 = null;

        public IntegerParameter finalNode_startTime = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_2_edc0357_1357846887071_689896_20516 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_2_edc0357_1357846887070_663148_20508 = null;

        public BooleanParameter _17_0_2_2_edc0357_1357846887064_685566_20488_exists = null;

        public Parameter< ObjectFlow<Power_System.Signaldr_request> > sig_17_0_2_2_edc0357_1357846887070_804392_20511 = null;

        public Dependency endTimeDependency = null;

        public Dependency _17_0_2_2_edc0357_1357846887064_685566_20488_existsDependency = null;

        public ElaborationRule elaborationRule197 = null;

        public ElaborationRule elaborationRule198 = null;

        public void init_17_0_2_2_edc0357_1357846886593_851494_19883Members() {
            try {
                if (sig_17_0_2_2_edc0357_1357846887069_816508_20504 == null) sig_17_0_2_2_edc0357_1357846887069_816508_20504 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_2_edc0357_1357846887069_816508_20504", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887069_816508_20504" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846887070_48169_20513 == null) sig_17_0_2_2_edc0357_1357846887070_48169_20513 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_2_edc0357_1357846887070_48169_20513", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887070_48169_20513" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846886470_934121_19738 == null) sig_17_0_2_2_edc0357_1357846886470_934121_19738 = new Parameter<ObjectFlow<Power_System.Signaldr_request>>("sig_17_0_2_2_edc0357_1357846886470_934121_19738", null, (ObjectFlow<Power_System.Signaldr_request>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846886470_934121_19738" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846887071_114679_20515 == null) sig_17_0_2_2_edc0357_1357846887071_114679_20515 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_2_edc0357_1357846887071_114679_20515", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887071_114679_20515" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846887070_250209_20507 == null) sig_17_0_2_2_edc0357_1357846887070_250209_20507 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_2_edc0357_1357846887070_250209_20507", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887070_250209_20507" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846887070_351367_20510 == null) sig_17_0_2_2_edc0357_1357846887070_351367_20510 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_2_edc0357_1357846887070_351367_20510", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887070_351367_20510" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846887071_481120_20521 == null) sig_17_0_2_2_edc0357_1357846887071_481120_20521 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_2_edc0357_1357846887071_481120_20521", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887071_481120_20521" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846887071_131721_20519 == null) sig_17_0_2_2_edc0357_1357846887071_131721_20519 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_2_edc0357_1357846887071_131721_20519", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887071_131721_20519" })).evaluate(true), this);
                if (finalNode_endTime == null) finalNode_endTime = new IntegerParameter("finalNode_endTime", (Integer) null, this);
                if (sig_17_0_2_2_edc0357_1357846887071_495356_20517 == null) sig_17_0_2_2_edc0357_1357846887071_495356_20517 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_2_edc0357_1357846887071_495356_20517", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887071_495356_20517" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846887071_671761_20520 == null) sig_17_0_2_2_edc0357_1357846887071_671761_20520 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_2_edc0357_1357846887071_671761_20520", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887071_671761_20520" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846887071_561521_20518 == null) sig_17_0_2_2_edc0357_1357846887071_561521_20518 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_2_edc0357_1357846887071_561521_20518", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887071_561521_20518" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846887070_429172_20514 == null) sig_17_0_2_2_edc0357_1357846887070_429172_20514 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_2_edc0357_1357846887070_429172_20514", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887070_429172_20514" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846887070_255454_20505 == null) sig_17_0_2_2_edc0357_1357846887070_255454_20505 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_2_edc0357_1357846887070_255454_20505", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887070_255454_20505" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846887070_856387_20506 == null) sig_17_0_2_2_edc0357_1357846887070_856387_20506 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_2_edc0357_1357846887070_856387_20506", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887070_856387_20506" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846887070_93350_20509 == null) sig_17_0_2_2_edc0357_1357846887070_93350_20509 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_2_edc0357_1357846887070_93350_20509", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887070_93350_20509" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846887070_864895_20512 == null) sig_17_0_2_2_edc0357_1357846887070_864895_20512 = new Parameter<ObjectFlow<Integer>>("sig_17_0_2_2_edc0357_1357846887070_864895_20512", null, (ObjectFlow<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887070_864895_20512" })).evaluate(true), this);
                if (finalNode_startTime == null) finalNode_startTime = new IntegerParameter("finalNode_startTime", (Integer) null, this);
                if (sig_17_0_2_2_edc0357_1357846887071_689896_20516 == null) sig_17_0_2_2_edc0357_1357846887071_689896_20516 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_2_edc0357_1357846887071_689896_20516", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887071_689896_20516" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846887070_663148_20508 == null) sig_17_0_2_2_edc0357_1357846887070_663148_20508 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_2_edc0357_1357846887070_663148_20508", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887070_663148_20508" })).evaluate(true), this);
                if (_17_0_2_2_edc0357_1357846887064_685566_20488_exists == null) _17_0_2_2_edc0357_1357846887064_685566_20488_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887064_685566_20488_exists", (Boolean) false, this);
                if (sig_17_0_2_2_edc0357_1357846887070_804392_20511 == null) sig_17_0_2_2_edc0357_1357846887070_804392_20511 = new Parameter<ObjectFlow<Power_System.Signaldr_request>>("sig_17_0_2_2_edc0357_1357846887070_804392_20511", null, (ObjectFlow<Power_System.Signaldr_request>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887070_804392_20511" })).evaluate(true), this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void init_17_0_2_2_edc0357_1357846886593_851494_19883Collections() {
            parameters.add(sig_17_0_2_2_edc0357_1357846887069_816508_20504);
            parameters.add(sig_17_0_2_2_edc0357_1357846887070_48169_20513);
            parameters.add(sig_17_0_2_2_edc0357_1357846886470_934121_19738);
            parameters.add(sig_17_0_2_2_edc0357_1357846887071_114679_20515);
            parameters.add(sig_17_0_2_2_edc0357_1357846887070_250209_20507);
            parameters.add(sig_17_0_2_2_edc0357_1357846887070_351367_20510);
            parameters.add(sig_17_0_2_2_edc0357_1357846887071_481120_20521);
            parameters.add(sig_17_0_2_2_edc0357_1357846887071_131721_20519);
            parameters.add(finalNode_endTime);
            parameters.add(sig_17_0_2_2_edc0357_1357846887071_495356_20517);
            parameters.add(sig_17_0_2_2_edc0357_1357846887071_671761_20520);
            parameters.add(sig_17_0_2_2_edc0357_1357846887071_561521_20518);
            parameters.add(sig_17_0_2_2_edc0357_1357846887070_429172_20514);
            parameters.add(sig_17_0_2_2_edc0357_1357846887070_255454_20505);
            parameters.add(sig_17_0_2_2_edc0357_1357846887070_856387_20506);
            parameters.add(sig_17_0_2_2_edc0357_1357846887070_93350_20509);
            parameters.add(sig_17_0_2_2_edc0357_1357846887070_864895_20512);
            parameters.add(finalNode_startTime);
            parameters.add(sig_17_0_2_2_edc0357_1357846887071_689896_20516);
            parameters.add(sig_17_0_2_2_edc0357_1357846887070_663148_20508);
            parameters.add(_17_0_2_2_edc0357_1357846887064_685566_20488_exists);
            parameters.add(sig_17_0_2_2_edc0357_1357846887070_804392_20511);
        }

        public void init_17_0_2_2_edc0357_1357846886593_851494_19883Dependencies() {
            addDependency(endTime, new Expression<Integer>(finalNode_endTime));
            addDependency(_17_0_2_2_edc0357_1357846887064_685566_20488_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.NotEquals(new Expression<Integer>(finalNode_startTime), null)), new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887071_131721_20519, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(new Functions.Minus(new Expression<Integer>(finalNode_startTime), new Expression<Integer>(1))) })))));
        }

        public void init_17_0_2_2_edc0357_1357846886593_851494_19883Elaborations() {
            init_17_0_2_2_edc0357_1357846886593_851494_19883Dependencies();
            Expression<?>[] arguments197 = new Expression<?>[1];
            arguments197[0] = new Expression<Integer>(finalNode_startTime);
            Expression<Boolean> condition197 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887064_685566_20488_exists);
            elaborationRule197 = addElaborationRule(condition197, _17_0_2_2_edc0357_1357846886593_851494_19883.this, Customer._17_0_2_2_edc0357_1357846886593_851494_19883._17_0_2_2_edc0357_1357846887064_685566_20488.class, "C_end_ActivityFinalNode_CustomerCB", arguments197);
            Expression<?>[] arguments198 = new Expression<?>[1];
            arguments198[0] = new Expression<Integer>(startTime);
            Expression<Boolean> condition198 = new Expression<Boolean>(true);
            elaborationRule198 = addElaborationRule(condition198, _17_0_2_2_edc0357_1357846886593_851494_19883.this, Customer._17_0_2_2_edc0357_1357846886593_851494_19883._17_0_2_2_edc0357_1357846887060_476507_20481.class, "C_start_InitialNode_CustomerCB", arguments198);
        }
    }

    public class _17_0_2_2_edc0357_1357846886594_431163_19884 extends DurativeEvent {

        public _17_0_2_2_edc0357_1357846886594_431163_19884() {
            super();
            init_17_0_2_2_edc0357_1357846886594_431163_19884Members();
            init_17_0_2_2_edc0357_1357846886594_431163_19884Collections();
            init_17_0_2_2_edc0357_1357846886594_431163_19884Elaborations();
        }

        public class _17_0_2_2_edc0357_1357846887086_19365_20545 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846887086_19365_20545() {
                super();
                init_17_0_2_2_edc0357_1357846887086_19365_20545Members();
                init_17_0_2_2_edc0357_1357846887086_19365_20545Collections();
                init_17_0_2_2_edc0357_1357846887086_19365_20545Elaborations();
            }

            public _17_0_2_2_edc0357_1357846887086_19365_20545(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846887086_19365_20545Members();
                init_17_0_2_2_edc0357_1357846887086_19365_20545Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846887086_19365_20545Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_2_edc0357_1357846887089_106763_20550_exists = null;

            public BooleanParameter objectToPass = null;

            public Parameter< Customer > _17_0_2_2_edc0357_1357846887494_30367_21513 = null;

            public Dependency _17_0_2_2_edc0357_1357846887089_106763_20550_existsDependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Effect effect240 = null;

            public Parameter effect240Var = null;

            public ElaborationRule elaborationRule241 = null;

            public void init_17_0_2_2_edc0357_1357846887086_19365_20545Members() {
                try {
                    if (_17_0_2_2_edc0357_1357846887089_106763_20550_exists == null) _17_0_2_2_edc0357_1357846887089_106763_20550_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887089_106763_20550_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (_17_0_2_2_edc0357_1357846887494_30367_21513 == null) _17_0_2_2_edc0357_1357846887494_30367_21513 = new Parameter<Customer>("_17_0_2_2_edc0357_1357846887494_30367_21513", null, (Customer) Customer.this, this);
                    Object effect240VarV = sig_17_0_2_2_edc0357_1357846887094_979270_20560;
                    effect240Var = new Parameter("effect240Var", null, null, this);
                    addDependency(effect240Var, new Expression(effect240VarV));
                    effect240 = new EffectFunction(new EffectFunction(effect240Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { _17_0_2_2_edc0357_1357846887494_30367_21513, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846887086_19365_20545Collections() {
                parameters.add(_17_0_2_2_edc0357_1357846887089_106763_20550_exists);
                parameters.add(objectToPass);
                parameters.add(_17_0_2_2_edc0357_1357846887494_30367_21513);
                Set<Effect> effectsForeffect240Var = new TreeSet<Effect>();
                effectsForeffect240Var.add(effect240);
                addEffects((Parameter<?>) effect240Var, effectsForeffect240Var);
            }

            public void init_17_0_2_2_edc0357_1357846887086_19365_20545Dependencies() {
                addDependency(_17_0_2_2_edc0357_1357846887089_106763_20550_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887088_403174_20548_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887088_403174_20548_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887095_249792_20568, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_2_edc0357_1357846887086_19365_20545Elaborations() {
                init_17_0_2_2_edc0357_1357846887086_19365_20545Dependencies();
                Expression<?>[] arguments241 = new Expression<?>[1];
                arguments241[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition241 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887089_106763_20550_exists);
                elaborationRule241 = addElaborationRule(condition241, _17_0_2_2_edc0357_1357846886594_431163_19884.this, Customer._17_0_2_2_edc0357_1357846886594_431163_19884._17_0_2_2_edc0357_1357846887089_106763_20550.class, "CChange_fork2self_ForkNode_usePowerWIthCap", arguments241);
            }
        }

        public class _17_0_2_2_edc0357_1357846887087_156791_20546 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846887087_156791_20546() {
                super();
                init_17_0_2_2_edc0357_1357846887087_156791_20546Members();
                init_17_0_2_2_edc0357_1357846887087_156791_20546Collections();
                init_17_0_2_2_edc0357_1357846887087_156791_20546Elaborations();
            }

            public _17_0_2_2_edc0357_1357846887087_156791_20546(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846887087_156791_20546Members();
                init_17_0_2_2_edc0357_1357846887087_156791_20546Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846887087_156791_20546Elaborations();
                fixTimeDependencies();
            }

            public IntegerParameter myDeciderID_decider_17_0_2_2_edc0357_1357846887090_147895_20552 = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846887090_147895_20552_exists = null;

            public IntegerParameter _17_0_2_2_edc0357_1357846887494_469818_21514 = null;

            public Parameter< Customer > _17_0_2_2_edc0357_1357846887495_569021_21515 = null;

            public BooleanParameter objectToPass = null;

            public BooleanParameter addToDecider__17_0_2_2_edc0357_1357846887090_147895_20552 = null;

            public Dependency myDeciderID_decider_17_0_2_2_edc0357_1357846887090_147895_20552Dependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887090_147895_20552_existsDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887494_469818_21514Dependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887495_569021_21515Dependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Dependency addToDecider__17_0_2_2_edc0357_1357846887090_147895_20552Dependency = null;

            public Effect effect242 = null;

            public Parameter effect242Var = null;

            public Effect effect243 = null;

            public Parameter effect243Var = null;

            public Effect effect244 = null;

            public Parameter effect244Var = null;

            public ElaborationRule elaborationRule245 = null;

            public void init_17_0_2_2_edc0357_1357846887087_156791_20546Members() {
                try {
                    if (myDeciderID_decider_17_0_2_2_edc0357_1357846887090_147895_20552 == null) myDeciderID_decider_17_0_2_2_edc0357_1357846887090_147895_20552 = new IntegerParameter("myDeciderID_decider_17_0_2_2_edc0357_1357846887090_147895_20552", (Integer) 3, this);
                    if (_17_0_2_2_edc0357_1357846887090_147895_20552_exists == null) _17_0_2_2_edc0357_1357846887090_147895_20552_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887090_147895_20552_exists", (Boolean) false, this);
                    if (_17_0_2_2_edc0357_1357846887494_469818_21514 == null) _17_0_2_2_edc0357_1357846887494_469818_21514 = new IntegerParameter("_17_0_2_2_edc0357_1357846887494_469818_21514", (Integer) null, this);
                    if (_17_0_2_2_edc0357_1357846887495_569021_21515 == null) _17_0_2_2_edc0357_1357846887495_569021_21515 = new Parameter<Customer>("_17_0_2_2_edc0357_1357846887495_569021_21515", null, (Customer) null, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (addToDecider__17_0_2_2_edc0357_1357846887090_147895_20552 == null) addToDecider__17_0_2_2_edc0357_1357846887090_147895_20552 = new BooleanParameter("addToDecider__17_0_2_2_edc0357_1357846887090_147895_20552", (Boolean) false, this);
                    Object effect242VarV = sig_17_0_2_2_edc0357_1357846887095_557654_20567;
                    effect242Var = new Parameter("effect242Var", null, null, this);
                    addDependency(effect242Var, new Expression(effect242VarV));
                    effect242 = new EffectFunction(new EffectFunction(effect242Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect243VarV = decider_17_0_2_2_edc0357_1357846887090_147895_20552;
                    effect243Var = new Parameter("effect243Var", null, null, this);
                    addDependency(effect243Var, new Expression(effect243VarV));
                    effect243 = new EffectFunction(new EffectFunction(effect243Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class, java.lang.Boolean.class), new Object[] { endTime, myDeciderID_decider_17_0_2_2_edc0357_1357846887090_147895_20552, addToDecider__17_0_2_2_edc0357_1357846887090_147895_20552 }));
                    Object effect244VarV = usage__17_0_2_2_edc0357_1357846886595_378353_19885;
                    effect244Var = new Parameter("effect244Var", null, null, this);
                    addDependency(effect244Var, new Expression(effect244VarV));
                    effect244 = new EffectFunction(new EffectFunction(effect244Var, ClassUtils.getMethodForArgTypes("TimeVaryingPlottableMap<Integer>", "PowerSystem3", "setValue", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { startTime, _17_0_2_2_edc0357_1357846887494_469818_21514 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846887087_156791_20546Collections() {
                parameters.add(myDeciderID_decider_17_0_2_2_edc0357_1357846887090_147895_20552);
                parameters.add(_17_0_2_2_edc0357_1357846887090_147895_20552_exists);
                parameters.add(_17_0_2_2_edc0357_1357846887494_469818_21514);
                parameters.add(_17_0_2_2_edc0357_1357846887495_569021_21515);
                parameters.add(objectToPass);
                parameters.add(addToDecider__17_0_2_2_edc0357_1357846887090_147895_20552);
                Set<Effect> effectsForeffect242Var = new TreeSet<Effect>();
                effectsForeffect242Var.add(effect242);
                addEffects((Parameter<?>) effect242Var, effectsForeffect242Var);
                Set<Effect> effectsForeffect243Var = new TreeSet<Effect>();
                effectsForeffect243Var.add(effect243);
                addEffects((Parameter<?>) effect243Var, effectsForeffect243Var);
                Set<Effect> effectsForeffect244Var = new TreeSet<Effect>();
                effectsForeffect244Var.add(effect244);
                addEffects((Parameter<?>) effect244Var, effectsForeffect244Var);
            }

            public void init_17_0_2_2_edc0357_1357846887087_156791_20546Dependencies() {
                addDependency(myDeciderID_decider_17_0_2_2_edc0357_1357846887090_147895_20552, new Expression<Integer>(3));
                addDependency(_17_0_2_2_edc0357_1357846887090_147895_20552_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887088_403174_20548_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887088_403174_20548_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887090_147895_20552, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887090_147895_20552, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887090_147895_20552, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_2_edc0357_1357846887090_147895_20552)))))))));
                addDependency(_17_0_2_2_edc0357_1357846887494_469818_21514, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887095_325507_20565, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_2_edc0357_1357846887495_569021_21515, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887095_272551_20561, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(true));
                addDependency(addToDecider__17_0_2_2_edc0357_1357846887090_147895_20552, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887088_403174_20548_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887088_403174_20548_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
            }

            public void init_17_0_2_2_edc0357_1357846887087_156791_20546Elaborations() {
                init_17_0_2_2_edc0357_1357846887087_156791_20546Dependencies();
                Expression<?>[] arguments245 = new Expression<?>[1];
                arguments245[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition245 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887090_147895_20552_exists);
                elaborationRule245 = addElaborationRule(condition245, _17_0_2_2_edc0357_1357846886594_431163_19884.this, Customer._17_0_2_2_edc0357_1357846886594_431163_19884._17_0_2_2_edc0357_1357846887090_147895_20552.class, "CChange_sendnewusage_SendSignalAction_usePowerWIthCap", arguments245);
            }
        }

        public class _17_0_2_2_edc0357_1357846887087_881168_20547 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846887087_881168_20547() {
                super();
                init_17_0_2_2_edc0357_1357846887087_881168_20547Members();
                init_17_0_2_2_edc0357_1357846887087_881168_20547Collections();
                init_17_0_2_2_edc0357_1357846887087_881168_20547Elaborations();
            }

            public _17_0_2_2_edc0357_1357846887087_881168_20547(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846887087_881168_20547Members();
                init_17_0_2_2_edc0357_1357846887087_881168_20547Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846887087_881168_20547Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter objectToPass = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846887086_19365_20545_exists = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887086_19365_20545_existsDependency = null;

            public Effect effect246 = null;

            public Parameter effect246Var = null;

            public ElaborationRule elaborationRule247 = null;

            public void init_17_0_2_2_edc0357_1357846887087_881168_20547Members() {
                try {
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (_17_0_2_2_edc0357_1357846887086_19365_20545_exists == null) _17_0_2_2_edc0357_1357846887086_19365_20545_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887086_19365_20545_exists", (Boolean) false, this);
                    Object effect246VarV = sig_17_0_2_2_edc0357_1357846887095_249792_20568;
                    effect246Var = new Parameter("effect246Var", null, null, this);
                    addDependency(effect246Var, new Expression(effect246VarV));
                    effect246 = new EffectFunction(new EffectFunction(effect246Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846887087_881168_20547Collections() {
                parameters.add(objectToPass);
                parameters.add(_17_0_2_2_edc0357_1357846887086_19365_20545_exists);
                Set<Effect> effectsForeffect246Var = new TreeSet<Effect>();
                effectsForeffect246Var.add(effect246);
                addEffects((Parameter<?>) effect246Var, effectsForeffect246Var);
            }

            public void init_17_0_2_2_edc0357_1357846887087_881168_20547Dependencies() {
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(true));
                addDependency(_17_0_2_2_edc0357_1357846887086_19365_20545_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887088_403174_20548_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887088_403174_20548_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
            }

            public void init_17_0_2_2_edc0357_1357846887087_881168_20547Elaborations() {
                init_17_0_2_2_edc0357_1357846887087_881168_20547Dependencies();
                Expression<?>[] arguments247 = new Expression<?>[1];
                arguments247[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition247 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887086_19365_20545_exists);
                elaborationRule247 = addElaborationRule(condition247, _17_0_2_2_edc0357_1357846886594_431163_19884.this, Customer._17_0_2_2_edc0357_1357846886594_431163_19884._17_0_2_2_edc0357_1357846887086_19365_20545.class, "CChange_readself_ReadSelfAction_usePowerWIthCap", arguments247);
            }
        }

        public class _17_0_2_2_edc0357_1357846887088_403174_20548 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846887088_403174_20548() {
                super();
                init_17_0_2_2_edc0357_1357846887088_403174_20548Members();
                init_17_0_2_2_edc0357_1357846887088_403174_20548Collections();
                init_17_0_2_2_edc0357_1357846887088_403174_20548Elaborations();
            }

            public _17_0_2_2_edc0357_1357846887088_403174_20548(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846887088_403174_20548Members();
                init_17_0_2_2_edc0357_1357846887088_403174_20548Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846887088_403174_20548Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter objectToPass = null;

            public Dependency endTimeDependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Dependency finalNode_endTimeDependency = null;

            public void init_17_0_2_2_edc0357_1357846887088_403174_20548Members() {
                try {
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846887088_403174_20548Collections() {
                parameters.add(objectToPass);
            }

            public void init_17_0_2_2_edc0357_1357846887088_403174_20548Dependencies() {
                addDependency(endTime, new Expression<Integer>(new Functions.Plus(new Expression<Integer>(startTime), new Expression<Integer>(duration))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887095_579591_20564, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(finalNode_endTime, new Expression<Integer>(endTime));
            }

            public void init_17_0_2_2_edc0357_1357846887088_403174_20548Elaborations() {
                init_17_0_2_2_edc0357_1357846887088_403174_20548Dependencies();
            }
        }

        public class _17_0_2_2_edc0357_1357846887088_526304_20549 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846887088_526304_20549() {
                super();
                init_17_0_2_2_edc0357_1357846887088_526304_20549Members();
                init_17_0_2_2_edc0357_1357846887088_526304_20549Collections();
                init_17_0_2_2_edc0357_1357846887088_526304_20549Elaborations();
            }

            public _17_0_2_2_edc0357_1357846887088_526304_20549(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846887088_526304_20549Members();
                init_17_0_2_2_edc0357_1357846887088_526304_20549Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846887088_526304_20549Elaborations();
                fixTimeDependencies();
            }

            public IntegerParameter _17_0_2_2_edc0357_1357846887495_582664_21516 = null;

            public BooleanParameter addToDecider__17_0_2_2_edc0357_1357846887092_669043_20556 = null;

            public BooleanParameter objectToPass = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846887092_377927_20555_exists = null;

            public IntegerParameter myDeciderID_decider_17_0_2_2_edc0357_1357846887092_669043_20556 = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846887092_669043_20556_exists = null;

            public Parameter< Customer > _17_0_2_2_edc0357_1357846887496_746477_21517 = null;

            public Dependency _17_0_2_2_edc0357_1357846887495_582664_21516Dependency = null;

            public Dependency addToDecider__17_0_2_2_edc0357_1357846887092_669043_20556Dependency = null;

            public Dependency durationDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887092_669043_20556_existsDependency = null;

            public Dependency objectToPassDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887092_377927_20555_existsDependency = null;

            public Dependency myDeciderID_decider_17_0_2_2_edc0357_1357846887092_669043_20556Dependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887496_746477_21517Dependency = null;

            public Effect effect248 = null;

            public Parameter effect248Var = null;

            public Effect effect249 = null;

            public Parameter effect249Var = null;

            public Effect effect250 = null;

            public Parameter effect250Var = null;

            public ElaborationRule elaborationRule251 = null;

            public ElaborationRule elaborationRule252 = null;

            public void init_17_0_2_2_edc0357_1357846887088_526304_20549Members() {
                try {
                    if (_17_0_2_2_edc0357_1357846887495_582664_21516 == null) _17_0_2_2_edc0357_1357846887495_582664_21516 = new IntegerParameter("_17_0_2_2_edc0357_1357846887495_582664_21516", (Integer) null, this);
                    if (addToDecider__17_0_2_2_edc0357_1357846887092_669043_20556 == null) addToDecider__17_0_2_2_edc0357_1357846887092_669043_20556 = new BooleanParameter("addToDecider__17_0_2_2_edc0357_1357846887092_669043_20556", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (_17_0_2_2_edc0357_1357846887092_377927_20555_exists == null) _17_0_2_2_edc0357_1357846887092_377927_20555_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887092_377927_20555_exists", (Boolean) false, this);
                    if (myDeciderID_decider_17_0_2_2_edc0357_1357846887092_669043_20556 == null) myDeciderID_decider_17_0_2_2_edc0357_1357846887092_669043_20556 = new IntegerParameter("myDeciderID_decider_17_0_2_2_edc0357_1357846887092_669043_20556", (Integer) 1, this);
                    if (_17_0_2_2_edc0357_1357846887092_669043_20556_exists == null) _17_0_2_2_edc0357_1357846887092_669043_20556_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887092_669043_20556_exists", (Boolean) false, this);
                    if (_17_0_2_2_edc0357_1357846887496_746477_21517 == null) _17_0_2_2_edc0357_1357846887496_746477_21517 = new Parameter<Customer>("_17_0_2_2_edc0357_1357846887496_746477_21517", null, (Customer) null, this);
                    Object effect248VarV = sig_17_0_2_2_edc0357_1357846887096_400003_20570;
                    effect248Var = new Parameter("effect248Var", null, null, this);
                    addDependency(effect248Var, new Expression(effect248VarV));
                    effect248 = new EffectFunction(new EffectFunction(effect248Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { _17_0_2_2_edc0357_1357846887495_582664_21516, endTime }));
                    Object effect249VarV = sig_17_0_2_2_edc0357_1357846887095_989056_20569;
                    effect249Var = new Parameter("effect249Var", null, null, this);
                    addDependency(effect249Var, new Expression(effect249VarV));
                    effect249 = new EffectFunction(new EffectFunction(effect249Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect250VarV = decider_17_0_2_2_edc0357_1357846887092_669043_20556;
                    effect250Var = new Parameter("effect250Var", null, null, this);
                    addDependency(effect250Var, new Expression(effect250VarV));
                    effect250 = new EffectFunction(new EffectFunction(effect250Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class, java.lang.Boolean.class), new Object[] { endTime, myDeciderID_decider_17_0_2_2_edc0357_1357846887092_669043_20556, addToDecider__17_0_2_2_edc0357_1357846887092_669043_20556 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846887088_526304_20549Collections() {
                parameters.add(_17_0_2_2_edc0357_1357846887495_582664_21516);
                parameters.add(addToDecider__17_0_2_2_edc0357_1357846887092_669043_20556);
                parameters.add(objectToPass);
                parameters.add(_17_0_2_2_edc0357_1357846887092_377927_20555_exists);
                parameters.add(myDeciderID_decider_17_0_2_2_edc0357_1357846887092_669043_20556);
                parameters.add(_17_0_2_2_edc0357_1357846887092_669043_20556_exists);
                parameters.add(_17_0_2_2_edc0357_1357846887496_746477_21517);
                Set<Effect> effectsForeffect248Var = new TreeSet<Effect>();
                effectsForeffect248Var.add(effect248);
                addEffects((Parameter<?>) effect248Var, effectsForeffect248Var);
                Set<Effect> effectsForeffect249Var = new TreeSet<Effect>();
                effectsForeffect249Var.add(effect249);
                addEffects((Parameter<?>) effect249Var, effectsForeffect249Var);
                Set<Effect> effectsForeffect250Var = new TreeSet<Effect>();
                effectsForeffect250Var.add(effect250);
                addEffects((Parameter<?>) effect250Var, effectsForeffect250Var);
            }

            public void init_17_0_2_2_edc0357_1357846887088_526304_20549Dependencies() {
                addDependency(_17_0_2_2_edc0357_1357846887495_582664_21516, new Expression(new EffectFunction(null, ClassUtils.getMethodForArgTypes("TimeVaryingPlottableMap<Integer>", "PowerSystem3", "getValue", java.lang.Integer.class), new Object[] { new Expression<Integer>(startTime) }, new FunctionCall(_17_0_2_2_edc0357_1357846887496_746477_21517, Parameter.class, "getMember", new Object[] { "cap__17_0_2_2_edc0357_1357846886598_990111_19890" }))));
                addDependency(addToDecider__17_0_2_2_edc0357_1357846887092_669043_20556, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887088_403174_20548_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887088_403174_20548_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(_17_0_2_2_edc0357_1357846887092_669043_20556_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887088_403174_20548_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887088_403174_20548_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887092_669043_20556, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887092_669043_20556, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887092_669043_20556, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_2_edc0357_1357846887092_669043_20556)))))))));
                addDependency(objectToPass, new Expression<Boolean>(true));
                addDependency(_17_0_2_2_edc0357_1357846887092_377927_20555_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887088_403174_20548_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887088_403174_20548_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(myDeciderID_decider_17_0_2_2_edc0357_1357846887092_669043_20556, new Expression<Integer>(1));
                addDependency(_17_0_2_2_edc0357_1357846887496_746477_21517, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887095_741476_20562, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_2_edc0357_1357846887088_526304_20549Elaborations() {
                init_17_0_2_2_edc0357_1357846887088_526304_20549Dependencies();
                Expression<?>[] arguments251 = new Expression<?>[1];
                arguments251[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition251 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887092_669043_20556_exists);
                elaborationRule251 = addElaborationRule(condition251, _17_0_2_2_edc0357_1357846886594_431163_19884.this, Customer._17_0_2_2_edc0357_1357846886594_431163_19884._17_0_2_2_edc0357_1357846887092_669043_20556.class, "CChange_decidecap_DecisionNode_usePowerWIthCap", arguments251);
                Expression<?>[] arguments252 = new Expression<?>[1];
                arguments252[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition252 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887092_377927_20555_exists);
                elaborationRule252 = addElaborationRule(condition252, _17_0_2_2_edc0357_1357846886594_431163_19884.this, Customer._17_0_2_2_edc0357_1357846886594_431163_19884._17_0_2_2_edc0357_1357846887092_377927_20555.class, "CChange_fork3cap_ForkNode_usePowerWIthCap", arguments252);
            }
        }

        public class _17_0_2_2_edc0357_1357846887089_106763_20550 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846887089_106763_20550() {
                super();
                init_17_0_2_2_edc0357_1357846887089_106763_20550Members();
                init_17_0_2_2_edc0357_1357846887089_106763_20550Collections();
                init_17_0_2_2_edc0357_1357846887089_106763_20550Elaborations();
            }

            public _17_0_2_2_edc0357_1357846887089_106763_20550(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846887089_106763_20550Members();
                init_17_0_2_2_edc0357_1357846887089_106763_20550Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846887089_106763_20550Elaborations();
                fixTimeDependencies();
            }

            public IntegerParameter myDeciderID_decider_17_0_2_2_edc0357_1357846887090_147895_20552 = null;

            public IntegerParameter myDeciderID_decider_17_0_2_2_edc0357_1357846887087_156791_20546 = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846887088_526304_20549_exists = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846887090_147895_20552_exists = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846887087_156791_20546_exists = null;

            public BooleanParameter addToDecider__17_0_2_2_edc0357_1357846887087_156791_20546 = null;

            public Parameter< Customer > objectToPass = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846887094_993318_20559_exists = null;

            public BooleanParameter addToDecider__17_0_2_2_edc0357_1357846887090_147895_20552 = null;

            public Dependency myDeciderID_decider_17_0_2_2_edc0357_1357846887090_147895_20552Dependency = null;

            public Dependency myDeciderID_decider_17_0_2_2_edc0357_1357846887087_156791_20546Dependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887088_526304_20549_existsDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887090_147895_20552_existsDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887087_156791_20546_existsDependency = null;

            public Dependency addToDecider__17_0_2_2_edc0357_1357846887087_156791_20546Dependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887094_993318_20559_existsDependency = null;

            public Dependency addToDecider__17_0_2_2_edc0357_1357846887090_147895_20552Dependency = null;

            public Effect effect253 = null;

            public Parameter effect253Var = null;

            public Effect effect254 = null;

            public Parameter effect254Var = null;

            public Effect effect255 = null;

            public Parameter effect255Var = null;

            public Effect effect256 = null;

            public Parameter effect256Var = null;

            public Effect effect257 = null;

            public Parameter effect257Var = null;

            public Effect effect258 = null;

            public Parameter effect258Var = null;

            public ElaborationRule elaborationRule259 = null;

            public ElaborationRule elaborationRule260 = null;

            public ElaborationRule elaborationRule261 = null;

            public ElaborationRule elaborationRule262 = null;

            public void init_17_0_2_2_edc0357_1357846887089_106763_20550Members() {
                try {
                    if (myDeciderID_decider_17_0_2_2_edc0357_1357846887090_147895_20552 == null) myDeciderID_decider_17_0_2_2_edc0357_1357846887090_147895_20552 = new IntegerParameter("myDeciderID_decider_17_0_2_2_edc0357_1357846887090_147895_20552", (Integer) 2, this);
                    if (myDeciderID_decider_17_0_2_2_edc0357_1357846887087_156791_20546 == null) myDeciderID_decider_17_0_2_2_edc0357_1357846887087_156791_20546 = new IntegerParameter("myDeciderID_decider_17_0_2_2_edc0357_1357846887087_156791_20546", (Integer) 1, this);
                    if (_17_0_2_2_edc0357_1357846887088_526304_20549_exists == null) _17_0_2_2_edc0357_1357846887088_526304_20549_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887088_526304_20549_exists", (Boolean) false, this);
                    if (_17_0_2_2_edc0357_1357846887090_147895_20552_exists == null) _17_0_2_2_edc0357_1357846887090_147895_20552_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887090_147895_20552_exists", (Boolean) false, this);
                    if (_17_0_2_2_edc0357_1357846887087_156791_20546_exists == null) _17_0_2_2_edc0357_1357846887087_156791_20546_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887087_156791_20546_exists", (Boolean) false, this);
                    if (addToDecider__17_0_2_2_edc0357_1357846887087_156791_20546 == null) addToDecider__17_0_2_2_edc0357_1357846887087_156791_20546 = new BooleanParameter("addToDecider__17_0_2_2_edc0357_1357846887087_156791_20546", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new Parameter<Customer>("objectToPass", null, (Customer) null, this);
                    if (_17_0_2_2_edc0357_1357846887094_993318_20559_exists == null) _17_0_2_2_edc0357_1357846887094_993318_20559_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887094_993318_20559_exists", (Boolean) false, this);
                    if (addToDecider__17_0_2_2_edc0357_1357846887090_147895_20552 == null) addToDecider__17_0_2_2_edc0357_1357846887090_147895_20552 = new BooleanParameter("addToDecider__17_0_2_2_edc0357_1357846887090_147895_20552", (Boolean) false, this);
                    Object effect253VarV = sig_17_0_2_2_edc0357_1357846887095_272551_20561;
                    effect253Var = new Parameter("effect253Var", null, null, this);
                    addDependency(effect253Var, new Expression(effect253VarV));
                    effect253 = new EffectFunction(new EffectFunction(effect253Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect254VarV = sig_17_0_2_2_edc0357_1357846887095_741476_20562;
                    effect254Var = new Parameter("effect254Var", null, null, this);
                    addDependency(effect254Var, new Expression(effect254VarV));
                    effect254 = new EffectFunction(new EffectFunction(effect254Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect255VarV = sig_17_0_2_2_edc0357_1357846887095_160286_20563;
                    effect255Var = new Parameter("effect255Var", null, null, this);
                    addDependency(effect255Var, new Expression(effect255VarV));
                    effect255 = new EffectFunction(new EffectFunction(effect255Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect256VarV = sig_17_0_2_2_edc0357_1357846887097_610385_20581;
                    effect256Var = new Parameter("effect256Var", null, null, this);
                    addDependency(effect256Var, new Expression(effect256VarV));
                    effect256 = new EffectFunction(new EffectFunction(effect256Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect257VarV = decider_17_0_2_2_edc0357_1357846887087_156791_20546;
                    effect257Var = new Parameter("effect257Var", null, null, this);
                    addDependency(effect257Var, new Expression(effect257VarV));
                    effect257 = new EffectFunction(new EffectFunction(effect257Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class, java.lang.Boolean.class), new Object[] { endTime, myDeciderID_decider_17_0_2_2_edc0357_1357846887087_156791_20546, addToDecider__17_0_2_2_edc0357_1357846887087_156791_20546 }));
                    Object effect258VarV = decider_17_0_2_2_edc0357_1357846887090_147895_20552;
                    effect258Var = new Parameter("effect258Var", null, null, this);
                    addDependency(effect258Var, new Expression(effect258VarV));
                    effect258 = new EffectFunction(new EffectFunction(effect258Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class, java.lang.Boolean.class), new Object[] { endTime, myDeciderID_decider_17_0_2_2_edc0357_1357846887090_147895_20552, addToDecider__17_0_2_2_edc0357_1357846887090_147895_20552 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846887089_106763_20550Collections() {
                parameters.add(myDeciderID_decider_17_0_2_2_edc0357_1357846887090_147895_20552);
                parameters.add(myDeciderID_decider_17_0_2_2_edc0357_1357846887087_156791_20546);
                parameters.add(_17_0_2_2_edc0357_1357846887088_526304_20549_exists);
                parameters.add(_17_0_2_2_edc0357_1357846887090_147895_20552_exists);
                parameters.add(_17_0_2_2_edc0357_1357846887087_156791_20546_exists);
                parameters.add(addToDecider__17_0_2_2_edc0357_1357846887087_156791_20546);
                parameters.add(objectToPass);
                parameters.add(_17_0_2_2_edc0357_1357846887094_993318_20559_exists);
                parameters.add(addToDecider__17_0_2_2_edc0357_1357846887090_147895_20552);
                Set<Effect> effectsForeffect253Var = new TreeSet<Effect>();
                effectsForeffect253Var.add(effect253);
                addEffects((Parameter<?>) effect253Var, effectsForeffect253Var);
                Set<Effect> effectsForeffect254Var = new TreeSet<Effect>();
                effectsForeffect254Var.add(effect254);
                addEffects((Parameter<?>) effect254Var, effectsForeffect254Var);
                Set<Effect> effectsForeffect255Var = new TreeSet<Effect>();
                effectsForeffect255Var.add(effect255);
                addEffects((Parameter<?>) effect255Var, effectsForeffect255Var);
                Set<Effect> effectsForeffect256Var = new TreeSet<Effect>();
                effectsForeffect256Var.add(effect256);
                addEffects((Parameter<?>) effect256Var, effectsForeffect256Var);
                Set<Effect> effectsForeffect257Var = new TreeSet<Effect>();
                effectsForeffect257Var.add(effect257);
                addEffects((Parameter<?>) effect257Var, effectsForeffect257Var);
                Set<Effect> effectsForeffect258Var = new TreeSet<Effect>();
                effectsForeffect258Var.add(effect258);
                addEffects((Parameter<?>) effect258Var, effectsForeffect258Var);
            }

            public void init_17_0_2_2_edc0357_1357846887089_106763_20550Dependencies() {
                addDependency(myDeciderID_decider_17_0_2_2_edc0357_1357846887090_147895_20552, new Expression<Integer>(2));
                addDependency(myDeciderID_decider_17_0_2_2_edc0357_1357846887087_156791_20546, new Expression<Integer>(1));
                addDependency(_17_0_2_2_edc0357_1357846887088_526304_20549_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887088_403174_20548_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887088_403174_20548_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(_17_0_2_2_edc0357_1357846887090_147895_20552_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887088_403174_20548_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887088_403174_20548_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887090_147895_20552, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887090_147895_20552, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887090_147895_20552, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_2_edc0357_1357846887090_147895_20552)))))))));
                addDependency(_17_0_2_2_edc0357_1357846887087_156791_20546_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887088_403174_20548_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887088_403174_20548_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887087_156791_20546, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887087_156791_20546, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887087_156791_20546, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_2_edc0357_1357846887087_156791_20546)))))))));
                addDependency(addToDecider__17_0_2_2_edc0357_1357846887087_156791_20546, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887088_403174_20548_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887088_403174_20548_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887094_979270_20560, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_2_edc0357_1357846887094_993318_20559_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887088_403174_20548_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887088_403174_20548_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(addToDecider__17_0_2_2_edc0357_1357846887090_147895_20552, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887088_403174_20548_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887088_403174_20548_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
            }

            public void init_17_0_2_2_edc0357_1357846887089_106763_20550Elaborations() {
                init_17_0_2_2_edc0357_1357846887089_106763_20550Dependencies();
                Expression<?>[] arguments259 = new Expression<?>[1];
                arguments259[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition259 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887087_156791_20546_exists);
                elaborationRule259 = addElaborationRule(condition259, _17_0_2_2_edc0357_1357846886594_431163_19884.this, Customer._17_0_2_2_edc0357_1357846886594_431163_19884._17_0_2_2_edc0357_1357846887087_156791_20546.class, "CChange_setnewusage_AddStructuralFeatureValueAction_usePowerWIthCap", arguments259);
                Expression<?>[] arguments260 = new Expression<?>[1];
                arguments260[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition260 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887090_147895_20552_exists);
                elaborationRule260 = addElaborationRule(condition260, _17_0_2_2_edc0357_1357846886594_431163_19884.this, Customer._17_0_2_2_edc0357_1357846886594_431163_19884._17_0_2_2_edc0357_1357846887090_147895_20552.class, "CChange_sendnewusage_SendSignalAction_usePowerWIthCap", arguments260);
                Expression<?>[] arguments261 = new Expression<?>[1];
                arguments261[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition261 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887088_526304_20549_exists);
                elaborationRule261 = addElaborationRule(condition261, _17_0_2_2_edc0357_1357846886594_431163_19884.this, Customer._17_0_2_2_edc0357_1357846886594_431163_19884._17_0_2_2_edc0357_1357846887088_526304_20549.class, "CChange_readcurrentcap_ReadStructuralFeatureAction_usePowerWIthCap", arguments261);
                Expression<?>[] arguments262 = new Expression<?>[1];
                arguments262[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition262 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887094_993318_20559_exists);
                elaborationRule262 = addElaborationRule(condition262, _17_0_2_2_edc0357_1357846886594_431163_19884.this, Customer._17_0_2_2_edc0357_1357846886594_431163_19884._17_0_2_2_edc0357_1357846887094_993318_20559.class, "CUse_readusageparam_ReadStructuralFeatureAction_usePowerWIthCap", arguments262);
            }
        }

        public class _17_0_2_2_edc0357_1357846887090_54233_20551 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846887090_54233_20551() {
                super();
                init_17_0_2_2_edc0357_1357846887090_54233_20551Members();
                init_17_0_2_2_edc0357_1357846887090_54233_20551Collections();
                init_17_0_2_2_edc0357_1357846887090_54233_20551Elaborations();
            }

            public _17_0_2_2_edc0357_1357846887090_54233_20551(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846887090_54233_20551Members();
                init_17_0_2_2_edc0357_1357846887090_54233_20551Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846887090_54233_20551Elaborations();
                fixTimeDependencies();
            }

            public IntegerParameter _17_0_2_2_edc0357_1357846887497_504913_21519 = null;

            public IntegerParameter new_load = null;

            public IntegerParameter _17_0_2_2_edc0357_1357846887496_881565_21518 = null;

            public IntegerParameter cap = null;

            public IntegerParameter desired = null;

            public BooleanParameter objectToPass = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846887093_704898_20558_exists = null;

            public IntegerParameter _17_0_2_2_edc0357_1357846887498_724665_21520 = null;

            public Dependency _17_0_2_2_edc0357_1357846887497_504913_21519Dependency = null;

            public Dependency new_loadDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887496_881565_21518Dependency = null;

            public Dependency capDependency = null;

            public Dependency desiredDependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887093_704898_20558_existsDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887498_724665_21520Dependency = null;

            public Effect effect263 = null;

            public Parameter effect263Var = null;

            public ElaborationRule elaborationRule264 = null;

            public void init_17_0_2_2_edc0357_1357846887090_54233_20551Members() {
                try {
                    if (_17_0_2_2_edc0357_1357846887497_504913_21519 == null) _17_0_2_2_edc0357_1357846887497_504913_21519 = new IntegerParameter("_17_0_2_2_edc0357_1357846887497_504913_21519", (Integer) null, this);
                    if (new_load == null) new_load = new IntegerParameter("new_load", (Integer) null, this);
                    if (_17_0_2_2_edc0357_1357846887496_881565_21518 == null) _17_0_2_2_edc0357_1357846887496_881565_21518 = new IntegerParameter("_17_0_2_2_edc0357_1357846887496_881565_21518", (Integer) null, this);
                    if (cap == null) cap = new IntegerParameter("cap", (Integer) null, this);
                    if (desired == null) desired = new IntegerParameter("desired", (Integer) null, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (_17_0_2_2_edc0357_1357846887093_704898_20558_exists == null) _17_0_2_2_edc0357_1357846887093_704898_20558_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887093_704898_20558_exists", (Boolean) false, this);
                    if (_17_0_2_2_edc0357_1357846887498_724665_21520 == null) _17_0_2_2_edc0357_1357846887498_724665_21520 = new IntegerParameter("_17_0_2_2_edc0357_1357846887498_724665_21520", (Integer) null, this);
                    Object effect263VarV = sig_17_0_2_2_edc0357_1357846887097_558436_20579;
                    effect263Var = new Parameter("effect263Var", null, null, this);
                    addDependency(effect263Var, new Expression(effect263VarV));
                    effect263 = new EffectFunction(new EffectFunction(effect263Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { _17_0_2_2_edc0357_1357846887496_881565_21518, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846887090_54233_20551Collections() {
                parameters.add(_17_0_2_2_edc0357_1357846887497_504913_21519);
                parameters.add(new_load);
                parameters.add(_17_0_2_2_edc0357_1357846887496_881565_21518);
                parameters.add(cap);
                parameters.add(desired);
                parameters.add(objectToPass);
                parameters.add(_17_0_2_2_edc0357_1357846887093_704898_20558_exists);
                parameters.add(_17_0_2_2_edc0357_1357846887498_724665_21520);
                Set<Effect> effectsForeffect263Var = new TreeSet<Effect>();
                effectsForeffect263Var.add(effect263);
                addEffects((Parameter<?>) effect263Var, effectsForeffect263Var);
            }

            public void init_17_0_2_2_edc0357_1357846887090_54233_20551Dependencies() {
                addDependency(_17_0_2_2_edc0357_1357846887497_504913_21519, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887096_822062_20576, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(new_load, new Expression(new FunctionCall(null, ClassUtils.getMethodForArgTypes("java.lang.Math", "PowerSystem3", "min", int.class, int.class), new Object[] { new Expression<Integer>(desired), new Expression<Integer>(cap) })));
                addDependency(_17_0_2_2_edc0357_1357846887496_881565_21518, new Expression<Integer>(new_load));
                addDependency(cap, new Expression<Integer>(_17_0_2_2_edc0357_1357846887498_724665_21520));
                addDependency(desired, new Expression<Integer>(_17_0_2_2_edc0357_1357846887497_504913_21519));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887096_444324_20573, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_2_edc0357_1357846887093_704898_20558_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887088_403174_20548_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887088_403174_20548_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(_17_0_2_2_edc0357_1357846887498_724665_21520, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887096_473015_20572, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_2_edc0357_1357846887090_54233_20551Elaborations() {
                init_17_0_2_2_edc0357_1357846887090_54233_20551Dependencies();
                Expression<?>[] arguments264 = new Expression<?>[2];
                arguments264[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                arguments264[1] = new Expression<ObjectFlow<Integer>>(sig_17_0_2_2_edc0357_1357846887097_558436_20579);
                Expression<Boolean> condition264 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887093_704898_20558_exists);
                elaborationRule264 = addElaborationRule(condition264, _17_0_2_2_edc0357_1357846886594_431163_19884.this, Customer._17_0_2_2_edc0357_1357846886594_431163_19884._17_0_2_2_edc0357_1357846887093_704898_20558.class, "CChange_merge_changeTo_MergeNode_usePowerWIthCap", arguments264);
            }
        }

        public class _17_0_2_2_edc0357_1357846887090_147895_20552 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846887090_147895_20552() {
                super();
                init_17_0_2_2_edc0357_1357846887090_147895_20552Members();
                init_17_0_2_2_edc0357_1357846887090_147895_20552Collections();
                init_17_0_2_2_edc0357_1357846887090_147895_20552Elaborations();
            }

            public _17_0_2_2_edc0357_1357846887090_147895_20552(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846887090_147895_20552Members();
                init_17_0_2_2_edc0357_1357846887090_147895_20552Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846887090_147895_20552Elaborations();
                fixTimeDependencies();
            }

            public Parameter< Customer > _17_0_2_2_edc0357_1357846887498_343531_21521 = null;

            public BooleanParameter objectToPass = null;

            public Parameter< Power_System.SignalchangeLoadValue > signalObject = null;

            public IntegerParameter _17_0_2_2_edc0357_1357846887499_456329_21522 = null;

            public Dependency _17_0_2_2_edc0357_1357846887498_343531_21521Dependency = null;

            public Dependency finalNode_startTimeDependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Dependency signalObjectDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887499_456329_21522Dependency = null;

            public Effect effect265 = null;

            public Parameter effect265Var = null;

            public Effect effect266 = null;

            public Parameter effect266Var = null;

            public Effect effect267 = null;

            public Parameter effect267Var = null;

            public void init_17_0_2_2_edc0357_1357846887090_147895_20552Members() {
                try {
                    if (_17_0_2_2_edc0357_1357846887498_343531_21521 == null) _17_0_2_2_edc0357_1357846887498_343531_21521 = new Parameter<Customer>("_17_0_2_2_edc0357_1357846887498_343531_21521", null, (Customer) null, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (signalObject == null) signalObject = new Parameter<Power_System.SignalchangeLoadValue>("signalObject", null, (Power_System.SignalchangeLoadValue) null, this);
                    if (_17_0_2_2_edc0357_1357846887499_456329_21522 == null) _17_0_2_2_edc0357_1357846887499_456329_21522 = new IntegerParameter("_17_0_2_2_edc0357_1357846887499_456329_21522", (Integer) null, this);
                    Object effect265VarV = sig_17_0_2_2_edc0357_1357846887095_579591_20564;
                    effect265Var = new Parameter("effect265Var", null, null, this);
                    addDependency(effect265Var, new Expression(effect265VarV));
                    effect265 = new EffectFunction(new EffectFunction(effect265Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect266VarV = new FunctionCall(x, Parameter.class, "getMember", new Object[] { "ss_17_0_2_2_edc0357_1357846886595_931685_19886_changeLoadValue" });
                    effect266Var = new Parameter("effect266Var", null, null, this);
                    addDependency(effect266Var, new Expression(effect266VarV));
                    effect266 = new EffectFunction(new EffectFunction(null, ClassUtils.getMethodForArgTypes("ObjectFlow<SignalchangeLoadValue>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { signalObject, endTime }, effect266Var));
                    Object effect267VarV = new FunctionCall(signalObject, Parameter.class, "getMember", new Object[] { "load__17_0_2_2_edc0357_1357846886587_742419_19875" });
                    effect267Var = new Parameter("effect267Var", null, null, this);
                    addDependency(effect267Var, new Expression(effect267VarV));
                    effect267 = new EffectFunction(new EffectFunction(null, ClassUtils.getMethodForArgTypes("TimeVaryingMap<Integer>", "PowerSystem3", "setValue", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, _17_0_2_2_edc0357_1357846887499_456329_21522 }, effect267Var));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846887090_147895_20552Collections() {
                parameters.add(_17_0_2_2_edc0357_1357846887498_343531_21521);
                parameters.add(objectToPass);
                parameters.add(signalObject);
                parameters.add(_17_0_2_2_edc0357_1357846887499_456329_21522);
                Set<Effect> effectsForeffect265Var = new TreeSet<Effect>();
                effectsForeffect265Var.add(effect265);
                addEffects((Parameter<?>) effect265Var, effectsForeffect265Var);
                Set<Effect> effectsForeffect266Var = new TreeSet<Effect>();
                effectsForeffect266Var.add(effect266);
                addEffects((Parameter<?>) effect266Var, effectsForeffect266Var);
                Set<Effect> effectsForeffect267Var = new TreeSet<Effect>();
                effectsForeffect267Var.add(effect267);
                addEffects((Parameter<?>) effect267Var, effectsForeffect267Var);
            }

            public void init_17_0_2_2_edc0357_1357846887090_147895_20552Dependencies() {
                addDependency(_17_0_2_2_edc0357_1357846887498_343531_21521, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887095_160286_20563, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(finalNode_startTime, new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887095_557654_20567, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(signalObject, new Expression<Power_System.SignalchangeLoadValue>(new ConstructorCall(x, ClassUtils.getConstructorForArgTypes(Power_System.SignalchangeLoadValue.class), new Object[] {})));
                addDependency(_17_0_2_2_edc0357_1357846887499_456329_21522, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887095_696449_20566, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_2_edc0357_1357846887090_147895_20552Elaborations() {
                init_17_0_2_2_edc0357_1357846887090_147895_20552Dependencies();
            }
        }

        public class _17_0_2_2_edc0357_1357846887091_56284_20553 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846887091_56284_20553() {
                super();
                init_17_0_2_2_edc0357_1357846887091_56284_20553Members();
                init_17_0_2_2_edc0357_1357846887091_56284_20553Collections();
                init_17_0_2_2_edc0357_1357846887091_56284_20553Elaborations();
            }

            public _17_0_2_2_edc0357_1357846887091_56284_20553(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846887091_56284_20553Members();
                init_17_0_2_2_edc0357_1357846887091_56284_20553Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846887091_56284_20553Elaborations();
                fixTimeDependencies();
            }

            public IntegerParameter myDeciderID_decider_17_0_2_2_edc0357_1357846887090_147895_20552 = null;

            public IntegerParameter myDeciderID_decider_17_0_2_2_edc0357_1357846887087_156791_20546 = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846887090_147895_20552_exists = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846887087_156791_20546_exists = null;

            public BooleanParameter addToDecider__17_0_2_2_edc0357_1357846887087_156791_20546 = null;

            public IntegerParameter objectToPass = null;

            public BooleanParameter addToDecider__17_0_2_2_edc0357_1357846887090_147895_20552 = null;

            public Dependency myDeciderID_decider_17_0_2_2_edc0357_1357846887090_147895_20552Dependency = null;

            public Dependency myDeciderID_decider_17_0_2_2_edc0357_1357846887087_156791_20546Dependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887090_147895_20552_existsDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887087_156791_20546_existsDependency = null;

            public Dependency addToDecider__17_0_2_2_edc0357_1357846887087_156791_20546Dependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Dependency addToDecider__17_0_2_2_edc0357_1357846887090_147895_20552Dependency = null;

            public Effect effect268 = null;

            public Parameter effect268Var = null;

            public Effect effect269 = null;

            public Parameter effect269Var = null;

            public Effect effect270 = null;

            public Parameter effect270Var = null;

            public Effect effect271 = null;

            public Parameter effect271Var = null;

            public ElaborationRule elaborationRule272 = null;

            public ElaborationRule elaborationRule273 = null;

            public void init_17_0_2_2_edc0357_1357846887091_56284_20553Members() {
                try {
                    if (myDeciderID_decider_17_0_2_2_edc0357_1357846887090_147895_20552 == null) myDeciderID_decider_17_0_2_2_edc0357_1357846887090_147895_20552 = new IntegerParameter("myDeciderID_decider_17_0_2_2_edc0357_1357846887090_147895_20552", (Integer) 1, this);
                    if (myDeciderID_decider_17_0_2_2_edc0357_1357846887087_156791_20546 == null) myDeciderID_decider_17_0_2_2_edc0357_1357846887087_156791_20546 = new IntegerParameter("myDeciderID_decider_17_0_2_2_edc0357_1357846887087_156791_20546", (Integer) 2, this);
                    if (_17_0_2_2_edc0357_1357846887090_147895_20552_exists == null) _17_0_2_2_edc0357_1357846887090_147895_20552_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887090_147895_20552_exists", (Boolean) false, this);
                    if (_17_0_2_2_edc0357_1357846887087_156791_20546_exists == null) _17_0_2_2_edc0357_1357846887087_156791_20546_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887087_156791_20546_exists", (Boolean) false, this);
                    if (addToDecider__17_0_2_2_edc0357_1357846887087_156791_20546 == null) addToDecider__17_0_2_2_edc0357_1357846887087_156791_20546 = new BooleanParameter("addToDecider__17_0_2_2_edc0357_1357846887087_156791_20546", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new IntegerParameter("objectToPass", (Integer) null, this);
                    if (addToDecider__17_0_2_2_edc0357_1357846887090_147895_20552 == null) addToDecider__17_0_2_2_edc0357_1357846887090_147895_20552 = new BooleanParameter("addToDecider__17_0_2_2_edc0357_1357846887090_147895_20552", (Boolean) false, this);
                    Object effect268VarV = sig_17_0_2_2_edc0357_1357846887095_325507_20565;
                    effect268Var = new Parameter("effect268Var", null, null, this);
                    addDependency(effect268Var, new Expression(effect268VarV));
                    effect268 = new EffectFunction(new EffectFunction(effect268Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect269VarV = sig_17_0_2_2_edc0357_1357846887095_696449_20566;
                    effect269Var = new Parameter("effect269Var", null, null, this);
                    addDependency(effect269Var, new Expression(effect269VarV));
                    effect269 = new EffectFunction(new EffectFunction(effect269Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect270VarV = decider_17_0_2_2_edc0357_1357846887087_156791_20546;
                    effect270Var = new Parameter("effect270Var", null, null, this);
                    addDependency(effect270Var, new Expression(effect270VarV));
                    effect270 = new EffectFunction(new EffectFunction(effect270Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class, java.lang.Boolean.class), new Object[] { endTime, myDeciderID_decider_17_0_2_2_edc0357_1357846887087_156791_20546, addToDecider__17_0_2_2_edc0357_1357846887087_156791_20546 }));
                    Object effect271VarV = decider_17_0_2_2_edc0357_1357846887090_147895_20552;
                    effect271Var = new Parameter("effect271Var", null, null, this);
                    addDependency(effect271Var, new Expression(effect271VarV));
                    effect271 = new EffectFunction(new EffectFunction(effect271Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class, java.lang.Boolean.class), new Object[] { endTime, myDeciderID_decider_17_0_2_2_edc0357_1357846887090_147895_20552, addToDecider__17_0_2_2_edc0357_1357846887090_147895_20552 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846887091_56284_20553Collections() {
                parameters.add(myDeciderID_decider_17_0_2_2_edc0357_1357846887090_147895_20552);
                parameters.add(myDeciderID_decider_17_0_2_2_edc0357_1357846887087_156791_20546);
                parameters.add(_17_0_2_2_edc0357_1357846887090_147895_20552_exists);
                parameters.add(_17_0_2_2_edc0357_1357846887087_156791_20546_exists);
                parameters.add(addToDecider__17_0_2_2_edc0357_1357846887087_156791_20546);
                parameters.add(objectToPass);
                parameters.add(addToDecider__17_0_2_2_edc0357_1357846887090_147895_20552);
                Set<Effect> effectsForeffect268Var = new TreeSet<Effect>();
                effectsForeffect268Var.add(effect268);
                addEffects((Parameter<?>) effect268Var, effectsForeffect268Var);
                Set<Effect> effectsForeffect269Var = new TreeSet<Effect>();
                effectsForeffect269Var.add(effect269);
                addEffects((Parameter<?>) effect269Var, effectsForeffect269Var);
                Set<Effect> effectsForeffect270Var = new TreeSet<Effect>();
                effectsForeffect270Var.add(effect270);
                addEffects((Parameter<?>) effect270Var, effectsForeffect270Var);
                Set<Effect> effectsForeffect271Var = new TreeSet<Effect>();
                effectsForeffect271Var.add(effect271);
                addEffects((Parameter<?>) effect271Var, effectsForeffect271Var);
            }

            public void init_17_0_2_2_edc0357_1357846887091_56284_20553Dependencies() {
                addDependency(myDeciderID_decider_17_0_2_2_edc0357_1357846887090_147895_20552, new Expression<Integer>(1));
                addDependency(myDeciderID_decider_17_0_2_2_edc0357_1357846887087_156791_20546, new Expression<Integer>(2));
                addDependency(_17_0_2_2_edc0357_1357846887090_147895_20552_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887088_403174_20548_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887088_403174_20548_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887090_147895_20552, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887090_147895_20552, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887090_147895_20552, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_2_edc0357_1357846887090_147895_20552)))))))));
                addDependency(_17_0_2_2_edc0357_1357846887087_156791_20546_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887088_403174_20548_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887088_403174_20548_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887087_156791_20546, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887087_156791_20546, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887087_156791_20546, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_2_edc0357_1357846887087_156791_20546)))))))));
                addDependency(addToDecider__17_0_2_2_edc0357_1357846887087_156791_20546, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887088_403174_20548_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887088_403174_20548_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887097_514850_20580, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(addToDecider__17_0_2_2_edc0357_1357846887090_147895_20552, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887088_403174_20548_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887088_403174_20548_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
            }

            public void init_17_0_2_2_edc0357_1357846887091_56284_20553Elaborations() {
                init_17_0_2_2_edc0357_1357846887091_56284_20553Dependencies();
                Expression<?>[] arguments272 = new Expression<?>[1];
                arguments272[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition272 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887087_156791_20546_exists);
                elaborationRule272 = addElaborationRule(condition272, _17_0_2_2_edc0357_1357846886594_431163_19884.this, Customer._17_0_2_2_edc0357_1357846886594_431163_19884._17_0_2_2_edc0357_1357846887087_156791_20546.class, "CChange_setnewusage_AddStructuralFeatureValueAction_usePowerWIthCap", arguments272);
                Expression<?>[] arguments273 = new Expression<?>[1];
                arguments273[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition273 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887090_147895_20552_exists);
                elaborationRule273 = addElaborationRule(condition273, _17_0_2_2_edc0357_1357846886594_431163_19884.this, Customer._17_0_2_2_edc0357_1357846886594_431163_19884._17_0_2_2_edc0357_1357846887090_147895_20552.class, "CChange_sendnewusage_SendSignalAction_usePowerWIthCap", arguments273);
            }
        }

        public class _17_0_2_2_edc0357_1357846887091_449842_20554 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846887091_449842_20554() {
                super();
                init_17_0_2_2_edc0357_1357846887091_449842_20554Members();
                init_17_0_2_2_edc0357_1357846887091_449842_20554Collections();
                init_17_0_2_2_edc0357_1357846887091_449842_20554Elaborations();
            }

            public _17_0_2_2_edc0357_1357846887091_449842_20554(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846887091_449842_20554Members();
                init_17_0_2_2_edc0357_1357846887091_449842_20554Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846887091_449842_20554Elaborations();
                fixTimeDependencies();
            }

            public IntegerParameter new_load = null;

            public IntegerParameter _17_0_2_2_edc0357_1357846887500_382214_21524 = null;

            public IntegerParameter _17_0_2_2_edc0357_1357846887499_868598_21523 = null;

            public IntegerParameter desired = null;

            public BooleanParameter objectToPass = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846887093_704898_20558_exists = null;

            public Dependency new_loadDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887499_868598_21523Dependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887500_382214_21524Dependency = null;

            public Dependency desiredDependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887093_704898_20558_existsDependency = null;

            public Effect effect274 = null;

            public Parameter effect274Var = null;

            public ElaborationRule elaborationRule275 = null;

            public void init_17_0_2_2_edc0357_1357846887091_449842_20554Members() {
                try {
                    if (new_load == null) new_load = new IntegerParameter("new_load", (Integer) null, this);
                    if (_17_0_2_2_edc0357_1357846887500_382214_21524 == null) _17_0_2_2_edc0357_1357846887500_382214_21524 = new IntegerParameter("_17_0_2_2_edc0357_1357846887500_382214_21524", (Integer) null, this);
                    if (_17_0_2_2_edc0357_1357846887499_868598_21523 == null) _17_0_2_2_edc0357_1357846887499_868598_21523 = new IntegerParameter("_17_0_2_2_edc0357_1357846887499_868598_21523", (Integer) null, this);
                    if (desired == null) desired = new IntegerParameter("desired", (Integer) null, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (_17_0_2_2_edc0357_1357846887093_704898_20558_exists == null) _17_0_2_2_edc0357_1357846887093_704898_20558_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887093_704898_20558_exists", (Boolean) false, this);
                    Object effect274VarV = sig_17_0_2_2_edc0357_1357846887096_582848_20578;
                    effect274Var = new Parameter("effect274Var", null, null, this);
                    addDependency(effect274Var, new Expression(effect274VarV));
                    effect274 = new EffectFunction(new EffectFunction(effect274Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { _17_0_2_2_edc0357_1357846887499_868598_21523, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846887091_449842_20554Collections() {
                parameters.add(new_load);
                parameters.add(_17_0_2_2_edc0357_1357846887500_382214_21524);
                parameters.add(_17_0_2_2_edc0357_1357846887499_868598_21523);
                parameters.add(desired);
                parameters.add(objectToPass);
                parameters.add(_17_0_2_2_edc0357_1357846887093_704898_20558_exists);
                Set<Effect> effectsForeffect274Var = new TreeSet<Effect>();
                effectsForeffect274Var.add(effect274);
                addEffects((Parameter<?>) effect274Var, effectsForeffect274Var);
            }

            public void init_17_0_2_2_edc0357_1357846887091_449842_20554Dependencies() {
                addDependency(new_load, new Expression<Integer>(desired));
                addDependency(_17_0_2_2_edc0357_1357846887499_868598_21523, new Expression<Integer>(new_load));
                addDependency(_17_0_2_2_edc0357_1357846887500_382214_21524, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887096_159549_20577, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(desired, new Expression<Integer>(_17_0_2_2_edc0357_1357846887500_382214_21524));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887096_475919_20574, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_2_edc0357_1357846887093_704898_20558_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887088_403174_20548_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887088_403174_20548_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
            }

            public void init_17_0_2_2_edc0357_1357846887091_449842_20554Elaborations() {
                init_17_0_2_2_edc0357_1357846887091_449842_20554Dependencies();
                Expression<?>[] arguments275 = new Expression<?>[2];
                arguments275[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                arguments275[1] = new Expression<ObjectFlow<Integer>>(sig_17_0_2_2_edc0357_1357846887096_582848_20578);
                Expression<Boolean> condition275 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887093_704898_20558_exists);
                elaborationRule275 = addElaborationRule(condition275, _17_0_2_2_edc0357_1357846886594_431163_19884.this, Customer._17_0_2_2_edc0357_1357846886594_431163_19884._17_0_2_2_edc0357_1357846887093_704898_20558.class, "CChange_merge_changeTo_MergeNode_usePowerWIthCap", arguments275);
            }
        }

        public class _17_0_2_2_edc0357_1357846887092_377927_20555 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846887092_377927_20555() {
                super();
                init_17_0_2_2_edc0357_1357846887092_377927_20555Members();
                init_17_0_2_2_edc0357_1357846887092_377927_20555Collections();
                init_17_0_2_2_edc0357_1357846887092_377927_20555Elaborations();
            }

            public _17_0_2_2_edc0357_1357846887092_377927_20555(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846887092_377927_20555Members();
                init_17_0_2_2_edc0357_1357846887092_377927_20555Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846887092_377927_20555Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter addToDecider__17_0_2_2_edc0357_1357846887090_54233_20551 = null;

            public IntegerParameter myDeciderID_decider_17_0_2_2_edc0357_1357846887090_54233_20551 = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846887090_54233_20551_exists = null;

            public BooleanParameter addToDecider__17_0_2_2_edc0357_1357846887092_669043_20556 = null;

            public IntegerParameter objectToPass = null;

            public IntegerParameter myDeciderID_decider_17_0_2_2_edc0357_1357846887092_669043_20556 = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846887092_669043_20556_exists = null;

            public Dependency addToDecider__17_0_2_2_edc0357_1357846887090_54233_20551Dependency = null;

            public Dependency myDeciderID_decider_17_0_2_2_edc0357_1357846887090_54233_20551Dependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887090_54233_20551_existsDependency = null;

            public Dependency addToDecider__17_0_2_2_edc0357_1357846887092_669043_20556Dependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Dependency myDeciderID_decider_17_0_2_2_edc0357_1357846887092_669043_20556Dependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887092_669043_20556_existsDependency = null;

            public Effect effect276 = null;

            public Parameter effect276Var = null;

            public Effect effect277 = null;

            public Parameter effect277Var = null;

            public Effect effect278 = null;

            public Parameter effect278Var = null;

            public Effect effect279 = null;

            public Parameter effect279Var = null;

            public ElaborationRule elaborationRule280 = null;

            public ElaborationRule elaborationRule281 = null;

            public void init_17_0_2_2_edc0357_1357846887092_377927_20555Members() {
                try {
                    if (addToDecider__17_0_2_2_edc0357_1357846887090_54233_20551 == null) addToDecider__17_0_2_2_edc0357_1357846887090_54233_20551 = new BooleanParameter("addToDecider__17_0_2_2_edc0357_1357846887090_54233_20551", (Boolean) false, this);
                    if (myDeciderID_decider_17_0_2_2_edc0357_1357846887090_54233_20551 == null) myDeciderID_decider_17_0_2_2_edc0357_1357846887090_54233_20551 = new IntegerParameter("myDeciderID_decider_17_0_2_2_edc0357_1357846887090_54233_20551", (Integer) 2, this);
                    if (_17_0_2_2_edc0357_1357846887090_54233_20551_exists == null) _17_0_2_2_edc0357_1357846887090_54233_20551_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887090_54233_20551_exists", (Boolean) false, this);
                    if (addToDecider__17_0_2_2_edc0357_1357846887092_669043_20556 == null) addToDecider__17_0_2_2_edc0357_1357846887092_669043_20556 = new BooleanParameter("addToDecider__17_0_2_2_edc0357_1357846887092_669043_20556", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new IntegerParameter("objectToPass", (Integer) null, this);
                    if (myDeciderID_decider_17_0_2_2_edc0357_1357846887092_669043_20556 == null) myDeciderID_decider_17_0_2_2_edc0357_1357846887092_669043_20556 = new IntegerParameter("myDeciderID_decider_17_0_2_2_edc0357_1357846887092_669043_20556", (Integer) 2, this);
                    if (_17_0_2_2_edc0357_1357846887092_669043_20556_exists == null) _17_0_2_2_edc0357_1357846887092_669043_20556_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887092_669043_20556_exists", (Boolean) false, this);
                    Object effect276VarV = sig_17_0_2_2_edc0357_1357846887096_982806_20571;
                    effect276Var = new Parameter("effect276Var", null, null, this);
                    addDependency(effect276Var, new Expression(effect276VarV));
                    effect276 = new EffectFunction(new EffectFunction(effect276Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect277VarV = sig_17_0_2_2_edc0357_1357846887096_473015_20572;
                    effect277Var = new Parameter("effect277Var", null, null, this);
                    addDependency(effect277Var, new Expression(effect277VarV));
                    effect277 = new EffectFunction(new EffectFunction(effect277Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect278VarV = decider_17_0_2_2_edc0357_1357846887092_669043_20556;
                    effect278Var = new Parameter("effect278Var", null, null, this);
                    addDependency(effect278Var, new Expression(effect278VarV));
                    effect278 = new EffectFunction(new EffectFunction(effect278Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class, java.lang.Boolean.class), new Object[] { endTime, myDeciderID_decider_17_0_2_2_edc0357_1357846887092_669043_20556, addToDecider__17_0_2_2_edc0357_1357846887092_669043_20556 }));
                    Object effect279VarV = decider_17_0_2_2_edc0357_1357846887090_54233_20551;
                    effect279Var = new Parameter("effect279Var", null, null, this);
                    addDependency(effect279Var, new Expression(effect279VarV));
                    effect279 = new EffectFunction(new EffectFunction(effect279Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class, java.lang.Boolean.class), new Object[] { endTime, myDeciderID_decider_17_0_2_2_edc0357_1357846887090_54233_20551, addToDecider__17_0_2_2_edc0357_1357846887090_54233_20551 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846887092_377927_20555Collections() {
                parameters.add(addToDecider__17_0_2_2_edc0357_1357846887090_54233_20551);
                parameters.add(myDeciderID_decider_17_0_2_2_edc0357_1357846887090_54233_20551);
                parameters.add(_17_0_2_2_edc0357_1357846887090_54233_20551_exists);
                parameters.add(addToDecider__17_0_2_2_edc0357_1357846887092_669043_20556);
                parameters.add(objectToPass);
                parameters.add(myDeciderID_decider_17_0_2_2_edc0357_1357846887092_669043_20556);
                parameters.add(_17_0_2_2_edc0357_1357846887092_669043_20556_exists);
                Set<Effect> effectsForeffect276Var = new TreeSet<Effect>();
                effectsForeffect276Var.add(effect276);
                addEffects((Parameter<?>) effect276Var, effectsForeffect276Var);
                Set<Effect> effectsForeffect277Var = new TreeSet<Effect>();
                effectsForeffect277Var.add(effect277);
                addEffects((Parameter<?>) effect277Var, effectsForeffect277Var);
                Set<Effect> effectsForeffect278Var = new TreeSet<Effect>();
                effectsForeffect278Var.add(effect278);
                addEffects((Parameter<?>) effect278Var, effectsForeffect278Var);
                Set<Effect> effectsForeffect279Var = new TreeSet<Effect>();
                effectsForeffect279Var.add(effect279);
                addEffects((Parameter<?>) effect279Var, effectsForeffect279Var);
            }

            public void init_17_0_2_2_edc0357_1357846887092_377927_20555Dependencies() {
                addDependency(addToDecider__17_0_2_2_edc0357_1357846887090_54233_20551, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887088_403174_20548_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887088_403174_20548_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(myDeciderID_decider_17_0_2_2_edc0357_1357846887090_54233_20551, new Expression<Integer>(2));
                addDependency(_17_0_2_2_edc0357_1357846887090_54233_20551_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887088_403174_20548_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887088_403174_20548_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887090_54233_20551, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887090_54233_20551, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887090_54233_20551, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_2_edc0357_1357846887090_54233_20551)))))))));
                addDependency(addToDecider__17_0_2_2_edc0357_1357846887092_669043_20556, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887088_403174_20548_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887088_403174_20548_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887096_400003_20570, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(myDeciderID_decider_17_0_2_2_edc0357_1357846887092_669043_20556, new Expression<Integer>(2));
                addDependency(_17_0_2_2_edc0357_1357846887092_669043_20556_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887088_403174_20548_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887088_403174_20548_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887092_669043_20556, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887092_669043_20556, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887092_669043_20556, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_2_edc0357_1357846887092_669043_20556)))))))));
            }

            public void init_17_0_2_2_edc0357_1357846887092_377927_20555Elaborations() {
                init_17_0_2_2_edc0357_1357846887092_377927_20555Dependencies();
                Expression<?>[] arguments280 = new Expression<?>[1];
                arguments280[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition280 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887092_669043_20556_exists);
                elaborationRule280 = addElaborationRule(condition280, _17_0_2_2_edc0357_1357846886594_431163_19884.this, Customer._17_0_2_2_edc0357_1357846886594_431163_19884._17_0_2_2_edc0357_1357846887092_669043_20556.class, "CChange_decidecap_DecisionNode_usePowerWIthCap", arguments280);
                Expression<?>[] arguments281 = new Expression<?>[1];
                arguments281[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition281 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887090_54233_20551_exists);
                elaborationRule281 = addElaborationRule(condition281, _17_0_2_2_edc0357_1357846886594_431163_19884.this, Customer._17_0_2_2_edc0357_1357846886594_431163_19884._17_0_2_2_edc0357_1357846887090_54233_20551.class, "CChange_calc_capmin_CallBehaviorAction_usePowerWIthCap", arguments281);
            }
        }

        public class _17_0_2_2_edc0357_1357846887092_669043_20556 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846887092_669043_20556() {
                super();
                init_17_0_2_2_edc0357_1357846887092_669043_20556Members();
                init_17_0_2_2_edc0357_1357846887092_669043_20556Collections();
                init_17_0_2_2_edc0357_1357846887092_669043_20556Elaborations();
            }

            public _17_0_2_2_edc0357_1357846887092_669043_20556(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846887092_669043_20556Members();
                init_17_0_2_2_edc0357_1357846887092_669043_20556Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846887092_669043_20556Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter addToDecider__17_0_2_2_edc0357_1357846887090_54233_20551 = null;

            public IntegerParameter myDeciderID_decider_17_0_2_2_edc0357_1357846887091_449842_20554 = null;

            public IntegerParameter myDeciderID_decider_17_0_2_2_edc0357_1357846887090_54233_20551 = null;

            public BooleanParameter addToDecider__17_0_2_2_edc0357_1357846887091_449842_20554 = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846887090_54233_20551_exists = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846887091_449842_20554_exists = null;

            public BooleanParameter objectToPass = null;

            public IntegerParameter decisionInput = null;

            public Dependency addToDecider__17_0_2_2_edc0357_1357846887090_54233_20551Dependency = null;

            public Dependency myDeciderID_decider_17_0_2_2_edc0357_1357846887091_449842_20554Dependency = null;

            public Dependency myDeciderID_decider_17_0_2_2_edc0357_1357846887090_54233_20551Dependency = null;

            public Dependency addToDecider__17_0_2_2_edc0357_1357846887091_449842_20554Dependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887090_54233_20551_existsDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887091_449842_20554_existsDependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Dependency decisionInputDependency = null;

            public Effect effect282 = null;

            public Parameter effect282Var = null;

            public Effect effect283 = null;

            public Parameter effect283Var = null;

            public Effect effect284 = null;

            public Parameter effect284Var = null;

            public Effect effect285 = null;

            public Parameter effect285Var = null;

            public ElaborationRule elaborationRule286 = null;

            public ElaborationRule elaborationRule287 = null;

            public void init_17_0_2_2_edc0357_1357846887092_669043_20556Members() {
                try {
                    if (addToDecider__17_0_2_2_edc0357_1357846887090_54233_20551 == null) addToDecider__17_0_2_2_edc0357_1357846887090_54233_20551 = new BooleanParameter("addToDecider__17_0_2_2_edc0357_1357846887090_54233_20551", (Boolean) false, this);
                    if (myDeciderID_decider_17_0_2_2_edc0357_1357846887091_449842_20554 == null) myDeciderID_decider_17_0_2_2_edc0357_1357846887091_449842_20554 = new IntegerParameter("myDeciderID_decider_17_0_2_2_edc0357_1357846887091_449842_20554", (Integer) 2, this);
                    if (myDeciderID_decider_17_0_2_2_edc0357_1357846887090_54233_20551 == null) myDeciderID_decider_17_0_2_2_edc0357_1357846887090_54233_20551 = new IntegerParameter("myDeciderID_decider_17_0_2_2_edc0357_1357846887090_54233_20551", (Integer) 3, this);
                    if (addToDecider__17_0_2_2_edc0357_1357846887091_449842_20554 == null) addToDecider__17_0_2_2_edc0357_1357846887091_449842_20554 = new BooleanParameter("addToDecider__17_0_2_2_edc0357_1357846887091_449842_20554", (Boolean) false, this);
                    if (_17_0_2_2_edc0357_1357846887090_54233_20551_exists == null) _17_0_2_2_edc0357_1357846887090_54233_20551_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887090_54233_20551_exists", (Boolean) false, this);
                    if (_17_0_2_2_edc0357_1357846887091_449842_20554_exists == null) _17_0_2_2_edc0357_1357846887091_449842_20554_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887091_449842_20554_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (decisionInput == null) decisionInput = new IntegerParameter("decisionInput", (Integer) null, this);
                    Object effect282VarV = sig_17_0_2_2_edc0357_1357846887096_444324_20573;
                    effect282Var = new Parameter("effect282Var", null, null, this);
                    addDependency(effect282Var, new Expression(effect282VarV));
                    effect282 = new EffectFunction(new EffectFunction(effect282Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "sendIf", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class, java.lang.Boolean.class), new Object[] { objectToPass, endTime, _17_0_2_2_edc0357_1357846887090_54233_20551_exists }));
                    Object effect283VarV = sig_17_0_2_2_edc0357_1357846887096_475919_20574;
                    effect283Var = new Parameter("effect283Var", null, null, this);
                    addDependency(effect283Var, new Expression(effect283VarV));
                    effect283 = new EffectFunction(new EffectFunction(effect283Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "sendIf", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class, java.lang.Boolean.class), new Object[] { objectToPass, endTime, _17_0_2_2_edc0357_1357846887091_449842_20554_exists }));
                    Object effect284VarV = decider_17_0_2_2_edc0357_1357846887091_449842_20554;
                    effect284Var = new Parameter("effect284Var", null, null, this);
                    addDependency(effect284Var, new Expression(effect284VarV));
                    effect284 = new EffectFunction(new EffectFunction(effect284Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class, java.lang.Boolean.class), new Object[] { endTime, myDeciderID_decider_17_0_2_2_edc0357_1357846887091_449842_20554, addToDecider__17_0_2_2_edc0357_1357846887091_449842_20554 }));
                    Object effect285VarV = decider_17_0_2_2_edc0357_1357846887090_54233_20551;
                    effect285Var = new Parameter("effect285Var", null, null, this);
                    addDependency(effect285Var, new Expression(effect285VarV));
                    effect285 = new EffectFunction(new EffectFunction(effect285Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class, java.lang.Boolean.class), new Object[] { endTime, myDeciderID_decider_17_0_2_2_edc0357_1357846887090_54233_20551, addToDecider__17_0_2_2_edc0357_1357846887090_54233_20551 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846887092_669043_20556Collections() {
                parameters.add(addToDecider__17_0_2_2_edc0357_1357846887090_54233_20551);
                parameters.add(myDeciderID_decider_17_0_2_2_edc0357_1357846887091_449842_20554);
                parameters.add(myDeciderID_decider_17_0_2_2_edc0357_1357846887090_54233_20551);
                parameters.add(addToDecider__17_0_2_2_edc0357_1357846887091_449842_20554);
                parameters.add(_17_0_2_2_edc0357_1357846887090_54233_20551_exists);
                parameters.add(_17_0_2_2_edc0357_1357846887091_449842_20554_exists);
                parameters.add(objectToPass);
                parameters.add(decisionInput);
                Set<Effect> effectsForeffect282Var = new TreeSet<Effect>();
                effectsForeffect282Var.add(effect282);
                addEffects((Parameter<?>) effect282Var, effectsForeffect282Var);
                Set<Effect> effectsForeffect283Var = new TreeSet<Effect>();
                effectsForeffect283Var.add(effect283);
                addEffects((Parameter<?>) effect283Var, effectsForeffect283Var);
                Set<Effect> effectsForeffect284Var = new TreeSet<Effect>();
                effectsForeffect284Var.add(effect284);
                addEffects((Parameter<?>) effect284Var, effectsForeffect284Var);
                Set<Effect> effectsForeffect285Var = new TreeSet<Effect>();
                effectsForeffect285Var.add(effect285);
                addEffects((Parameter<?>) effect285Var, effectsForeffect285Var);
            }

            public void init_17_0_2_2_edc0357_1357846887092_669043_20556Dependencies() {
                addDependency(addToDecider__17_0_2_2_edc0357_1357846887090_54233_20551, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Greater(new Expression<Integer>(decisionInput), new Expression<Integer>(0))), new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887088_403174_20548_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887088_403174_20548_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))))));
                addDependency(myDeciderID_decider_17_0_2_2_edc0357_1357846887091_449842_20554, new Expression<Integer>(2));
                addDependency(myDeciderID_decider_17_0_2_2_edc0357_1357846887090_54233_20551, new Expression<Integer>(3));
                addDependency(addToDecider__17_0_2_2_edc0357_1357846887091_449842_20554, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.LessEquals(new Expression<Integer>(decisionInput), new Expression<Integer>(0))), new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887088_403174_20548_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887088_403174_20548_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))))));
                addDependency(_17_0_2_2_edc0357_1357846887090_54233_20551_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Greater(new Expression<Integer>(decisionInput), new Expression<Integer>(0))), new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887088_403174_20548_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887088_403174_20548_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887090_54233_20551, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887090_54233_20551, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887090_54233_20551, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_2_edc0357_1357846887090_54233_20551)))))))));
                addDependency(_17_0_2_2_edc0357_1357846887091_449842_20554_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.LessEquals(new Expression<Integer>(decisionInput), new Expression<Integer>(0))), new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887088_403174_20548_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887088_403174_20548_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887091_449842_20554, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887091_449842_20554, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887091_449842_20554, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_2_edc0357_1357846887091_449842_20554)))))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887095_989056_20569, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(decisionInput, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887096_982806_20571, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_2_edc0357_1357846887092_669043_20556Elaborations() {
                init_17_0_2_2_edc0357_1357846887092_669043_20556Dependencies();
                Expression<?>[] arguments286 = new Expression<?>[1];
                arguments286[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition286 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887091_449842_20554_exists);
                elaborationRule286 = addElaborationRule(condition286, _17_0_2_2_edc0357_1357846886594_431163_19884.this, Customer._17_0_2_2_edc0357_1357846886594_431163_19884._17_0_2_2_edc0357_1357846887091_449842_20554.class, "CChange_calc_desired_CallBehaviorAction_usePowerWIthCap", arguments286);
                Expression<?>[] arguments287 = new Expression<?>[1];
                arguments287[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition287 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887090_54233_20551_exists);
                elaborationRule287 = addElaborationRule(condition287, _17_0_2_2_edc0357_1357846886594_431163_19884.this, Customer._17_0_2_2_edc0357_1357846886594_431163_19884._17_0_2_2_edc0357_1357846887090_54233_20551.class, "CChange_calc_capmin_CallBehaviorAction_usePowerWIthCap", arguments287);
            }
        }

        public class _17_0_2_2_edc0357_1357846887093_996466_20557 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846887093_996466_20557() {
                super();
                init_17_0_2_2_edc0357_1357846887093_996466_20557Members();
                init_17_0_2_2_edc0357_1357846887093_996466_20557Collections();
                init_17_0_2_2_edc0357_1357846887093_996466_20557Elaborations();
            }

            public _17_0_2_2_edc0357_1357846887093_996466_20557(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846887093_996466_20557Members();
                init_17_0_2_2_edc0357_1357846887093_996466_20557Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846887093_996466_20557Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter addToDecider__17_0_2_2_edc0357_1357846887090_54233_20551 = null;

            public IntegerParameter myDeciderID_decider_17_0_2_2_edc0357_1357846887091_449842_20554 = null;

            public IntegerParameter myDeciderID_decider_17_0_2_2_edc0357_1357846887090_54233_20551 = null;

            public BooleanParameter addToDecider__17_0_2_2_edc0357_1357846887091_449842_20554 = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846887090_54233_20551_exists = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846887091_449842_20554_exists = null;

            public IntegerParameter objectToPass = null;

            public Dependency addToDecider__17_0_2_2_edc0357_1357846887090_54233_20551Dependency = null;

            public Dependency myDeciderID_decider_17_0_2_2_edc0357_1357846887091_449842_20554Dependency = null;

            public Dependency myDeciderID_decider_17_0_2_2_edc0357_1357846887090_54233_20551Dependency = null;

            public Dependency addToDecider__17_0_2_2_edc0357_1357846887091_449842_20554Dependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887090_54233_20551_existsDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887091_449842_20554_existsDependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Effect effect288 = null;

            public Parameter effect288Var = null;

            public Effect effect289 = null;

            public Parameter effect289Var = null;

            public Effect effect290 = null;

            public Parameter effect290Var = null;

            public Effect effect291 = null;

            public Parameter effect291Var = null;

            public ElaborationRule elaborationRule292 = null;

            public ElaborationRule elaborationRule293 = null;

            public void init_17_0_2_2_edc0357_1357846887093_996466_20557Members() {
                try {
                    if (addToDecider__17_0_2_2_edc0357_1357846887090_54233_20551 == null) addToDecider__17_0_2_2_edc0357_1357846887090_54233_20551 = new BooleanParameter("addToDecider__17_0_2_2_edc0357_1357846887090_54233_20551", (Boolean) false, this);
                    if (myDeciderID_decider_17_0_2_2_edc0357_1357846887091_449842_20554 == null) myDeciderID_decider_17_0_2_2_edc0357_1357846887091_449842_20554 = new IntegerParameter("myDeciderID_decider_17_0_2_2_edc0357_1357846887091_449842_20554", (Integer) 1, this);
                    if (myDeciderID_decider_17_0_2_2_edc0357_1357846887090_54233_20551 == null) myDeciderID_decider_17_0_2_2_edc0357_1357846887090_54233_20551 = new IntegerParameter("myDeciderID_decider_17_0_2_2_edc0357_1357846887090_54233_20551", (Integer) 1, this);
                    if (addToDecider__17_0_2_2_edc0357_1357846887091_449842_20554 == null) addToDecider__17_0_2_2_edc0357_1357846887091_449842_20554 = new BooleanParameter("addToDecider__17_0_2_2_edc0357_1357846887091_449842_20554", (Boolean) false, this);
                    if (_17_0_2_2_edc0357_1357846887090_54233_20551_exists == null) _17_0_2_2_edc0357_1357846887090_54233_20551_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887090_54233_20551_exists", (Boolean) false, this);
                    if (_17_0_2_2_edc0357_1357846887091_449842_20554_exists == null) _17_0_2_2_edc0357_1357846887091_449842_20554_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887091_449842_20554_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new IntegerParameter("objectToPass", (Integer) null, this);
                    Object effect288VarV = sig_17_0_2_2_edc0357_1357846887096_822062_20576;
                    effect288Var = new Parameter("effect288Var", null, null, this);
                    addDependency(effect288Var, new Expression(effect288VarV));
                    effect288 = new EffectFunction(new EffectFunction(effect288Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect289VarV = sig_17_0_2_2_edc0357_1357846887096_159549_20577;
                    effect289Var = new Parameter("effect289Var", null, null, this);
                    addDependency(effect289Var, new Expression(effect289VarV));
                    effect289 = new EffectFunction(new EffectFunction(effect289Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect290VarV = decider_17_0_2_2_edc0357_1357846887091_449842_20554;
                    effect290Var = new Parameter("effect290Var", null, null, this);
                    addDependency(effect290Var, new Expression(effect290VarV));
                    effect290 = new EffectFunction(new EffectFunction(effect290Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class, java.lang.Boolean.class), new Object[] { endTime, myDeciderID_decider_17_0_2_2_edc0357_1357846887091_449842_20554, addToDecider__17_0_2_2_edc0357_1357846887091_449842_20554 }));
                    Object effect291VarV = decider_17_0_2_2_edc0357_1357846887090_54233_20551;
                    effect291Var = new Parameter("effect291Var", null, null, this);
                    addDependency(effect291Var, new Expression(effect291VarV));
                    effect291 = new EffectFunction(new EffectFunction(effect291Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class, java.lang.Boolean.class), new Object[] { endTime, myDeciderID_decider_17_0_2_2_edc0357_1357846887090_54233_20551, addToDecider__17_0_2_2_edc0357_1357846887090_54233_20551 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846887093_996466_20557Collections() {
                parameters.add(addToDecider__17_0_2_2_edc0357_1357846887090_54233_20551);
                parameters.add(myDeciderID_decider_17_0_2_2_edc0357_1357846887091_449842_20554);
                parameters.add(myDeciderID_decider_17_0_2_2_edc0357_1357846887090_54233_20551);
                parameters.add(addToDecider__17_0_2_2_edc0357_1357846887091_449842_20554);
                parameters.add(_17_0_2_2_edc0357_1357846887090_54233_20551_exists);
                parameters.add(_17_0_2_2_edc0357_1357846887091_449842_20554_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect288Var = new TreeSet<Effect>();
                effectsForeffect288Var.add(effect288);
                addEffects((Parameter<?>) effect288Var, effectsForeffect288Var);
                Set<Effect> effectsForeffect289Var = new TreeSet<Effect>();
                effectsForeffect289Var.add(effect289);
                addEffects((Parameter<?>) effect289Var, effectsForeffect289Var);
                Set<Effect> effectsForeffect290Var = new TreeSet<Effect>();
                effectsForeffect290Var.add(effect290);
                addEffects((Parameter<?>) effect290Var, effectsForeffect290Var);
                Set<Effect> effectsForeffect291Var = new TreeSet<Effect>();
                effectsForeffect291Var.add(effect291);
                addEffects((Parameter<?>) effect291Var, effectsForeffect291Var);
            }

            public void init_17_0_2_2_edc0357_1357846887093_996466_20557Dependencies() {
                addDependency(addToDecider__17_0_2_2_edc0357_1357846887090_54233_20551, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887088_403174_20548_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887088_403174_20548_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(myDeciderID_decider_17_0_2_2_edc0357_1357846887091_449842_20554, new Expression<Integer>(1));
                addDependency(myDeciderID_decider_17_0_2_2_edc0357_1357846887090_54233_20551, new Expression<Integer>(1));
                addDependency(addToDecider__17_0_2_2_edc0357_1357846887091_449842_20554, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887088_403174_20548_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887088_403174_20548_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(_17_0_2_2_edc0357_1357846887090_54233_20551_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887088_403174_20548_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887088_403174_20548_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887090_54233_20551, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887090_54233_20551, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887090_54233_20551, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_2_edc0357_1357846887090_54233_20551)))))))));
                addDependency(_17_0_2_2_edc0357_1357846887091_449842_20554_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887088_403174_20548_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887088_403174_20548_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887091_449842_20554, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887091_449842_20554, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887091_449842_20554, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_2_edc0357_1357846887091_449842_20554)))))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887096_35914_20575, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_2_edc0357_1357846887093_996466_20557Elaborations() {
                init_17_0_2_2_edc0357_1357846887093_996466_20557Dependencies();
                Expression<?>[] arguments292 = new Expression<?>[1];
                arguments292[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition292 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887091_449842_20554_exists);
                elaborationRule292 = addElaborationRule(condition292, _17_0_2_2_edc0357_1357846886594_431163_19884.this, Customer._17_0_2_2_edc0357_1357846886594_431163_19884._17_0_2_2_edc0357_1357846887091_449842_20554.class, "CChange_calc_desired_CallBehaviorAction_usePowerWIthCap", arguments292);
                Expression<?>[] arguments293 = new Expression<?>[1];
                arguments293[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition293 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887090_54233_20551_exists);
                elaborationRule293 = addElaborationRule(condition293, _17_0_2_2_edc0357_1357846886594_431163_19884.this, Customer._17_0_2_2_edc0357_1357846886594_431163_19884._17_0_2_2_edc0357_1357846887090_54233_20551.class, "CChange_calc_capmin_CallBehaviorAction_usePowerWIthCap", arguments293);
            }
        }

        public class _17_0_2_2_edc0357_1357846887093_704898_20558 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846887093_704898_20558() {
                super();
                init_17_0_2_2_edc0357_1357846887093_704898_20558Members();
                init_17_0_2_2_edc0357_1357846887093_704898_20558Collections();
                init_17_0_2_2_edc0357_1357846887093_704898_20558Elaborations();
            }

            public _17_0_2_2_edc0357_1357846887093_704898_20558(Expression<Integer> startTime, Expression<ObjectFlow<Integer>> receiveThis) {
                super();
                init_17_0_2_2_edc0357_1357846887093_704898_20558Members();
                init_17_0_2_2_edc0357_1357846887093_704898_20558Collections();
                addDependency(this.startTime, startTime);
                addDependency(this.receiveThis, receiveThis);
                init_17_0_2_2_edc0357_1357846887093_704898_20558Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_2_edc0357_1357846887091_56284_20553_exists = null;

            public Parameter< ObjectFlow<Integer> > receiveThis = null;

            public IntegerParameter objectToPass = null;

            public Dependency _17_0_2_2_edc0357_1357846887091_56284_20553_existsDependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Effect effect294 = null;

            public Parameter effect294Var = null;

            public ElaborationRule elaborationRule295 = null;

            public void init_17_0_2_2_edc0357_1357846887093_704898_20558Members() {
                try {
                    if (_17_0_2_2_edc0357_1357846887091_56284_20553_exists == null) _17_0_2_2_edc0357_1357846887091_56284_20553_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887091_56284_20553_exists", (Boolean) false, this);
                    if (receiveThis == null) receiveThis = new Parameter<ObjectFlow<Integer>>("receiveThis", null, (ObjectFlow<Integer>) null, this);
                    if (objectToPass == null) objectToPass = new IntegerParameter("objectToPass", (Integer) null, this);
                    Object effect294VarV = sig_17_0_2_2_edc0357_1357846887097_514850_20580;
                    effect294Var = new Parameter("effect294Var", null, null, this);
                    addDependency(effect294Var, new Expression(effect294VarV));
                    effect294 = new EffectFunction(new EffectFunction(effect294Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846887093_704898_20558Collections() {
                parameters.add(_17_0_2_2_edc0357_1357846887091_56284_20553_exists);
                parameters.add(receiveThis);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect294Var = new TreeSet<Effect>();
                effectsForeffect294Var.add(effect294);
                addEffects((Parameter<?>) effect294Var, effectsForeffect294Var);
            }

            public void init_17_0_2_2_edc0357_1357846887093_704898_20558Dependencies() {
                addDependency(_17_0_2_2_edc0357_1357846887091_56284_20553_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887088_403174_20548_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887088_403174_20548_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression(new EffectFunction(receiveThis, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_2_edc0357_1357846887093_704898_20558Elaborations() {
                init_17_0_2_2_edc0357_1357846887093_704898_20558Dependencies();
                Expression<?>[] arguments295 = new Expression<?>[1];
                arguments295[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition295 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887091_56284_20553_exists);
                elaborationRule295 = addElaborationRule(condition295, _17_0_2_2_edc0357_1357846886594_431163_19884.this, Customer._17_0_2_2_edc0357_1357846886594_431163_19884._17_0_2_2_edc0357_1357846887091_56284_20553.class, "CChange_forkchangeto_ForkNode_usePowerWIthCap", arguments295);
            }
        }

        public class _17_0_2_2_edc0357_1357846887094_993318_20559 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846887094_993318_20559() {
                super();
                init_17_0_2_2_edc0357_1357846887094_993318_20559Members();
                init_17_0_2_2_edc0357_1357846887094_993318_20559Collections();
                init_17_0_2_2_edc0357_1357846887094_993318_20559Elaborations();
            }

            public _17_0_2_2_edc0357_1357846887094_993318_20559(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846887094_993318_20559Members();
                init_17_0_2_2_edc0357_1357846887094_993318_20559Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846887094_993318_20559Elaborations();
                fixTimeDependencies();
            }

            public IntegerParameter _17_0_2_2_edc0357_1357846887500_639470_21525 = null;

            public Parameter< Customer > _17_0_2_2_edc0357_1357846887501_741352_21526 = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846887093_996466_20557_exists = null;

            public Dependency _17_0_2_2_edc0357_1357846887500_639470_21525Dependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887501_741352_21526Dependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887093_996466_20557_existsDependency = null;

            public Dependency durationDependency = null;

            public Effect effect296 = null;

            public Parameter effect296Var = null;

            public ElaborationRule elaborationRule297 = null;

            public void init_17_0_2_2_edc0357_1357846887094_993318_20559Members() {
                try {
                    if (_17_0_2_2_edc0357_1357846887500_639470_21525 == null) _17_0_2_2_edc0357_1357846887500_639470_21525 = new IntegerParameter("_17_0_2_2_edc0357_1357846887500_639470_21525", (Integer) null, this);
                    if (_17_0_2_2_edc0357_1357846887501_741352_21526 == null) _17_0_2_2_edc0357_1357846887501_741352_21526 = new Parameter<Customer>("_17_0_2_2_edc0357_1357846887501_741352_21526", null, (Customer) null, this);
                    if (_17_0_2_2_edc0357_1357846887093_996466_20557_exists == null) _17_0_2_2_edc0357_1357846887093_996466_20557_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887093_996466_20557_exists", (Boolean) false, this);
                    Object effect296VarV = sig_17_0_2_2_edc0357_1357846887096_35914_20575;
                    effect296Var = new Parameter("effect296Var", null, null, this);
                    addDependency(effect296Var, new Expression(effect296VarV));
                    effect296 = new EffectFunction(new EffectFunction(effect296Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { _17_0_2_2_edc0357_1357846887500_639470_21525, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846887094_993318_20559Collections() {
                parameters.add(_17_0_2_2_edc0357_1357846887500_639470_21525);
                parameters.add(_17_0_2_2_edc0357_1357846887501_741352_21526);
                parameters.add(_17_0_2_2_edc0357_1357846887093_996466_20557_exists);
                Set<Effect> effectsForeffect296Var = new TreeSet<Effect>();
                effectsForeffect296Var.add(effect296);
                addEffects((Parameter<?>) effect296Var, effectsForeffect296Var);
            }

            public void init_17_0_2_2_edc0357_1357846887094_993318_20559Dependencies() {
                addDependency(_17_0_2_2_edc0357_1357846887500_639470_21525, new Expression(new EffectFunction(null, ClassUtils.getMethodForArgTypes("TimeVaryingPlottableMap<Double>", "PowerSystem3", "getValue", java.lang.Integer.class), new Object[] { new Expression<Integer>(startTime) }, new FunctionCall(_17_0_2_2_edc0357_1357846887501_741352_21526, Parameter.class, "getMember", new Object[] { "loadProfile__17_0_2_2_edc0357_1357846886599_502905_19891" }))));
                addDependency(_17_0_2_2_edc0357_1357846887501_741352_21526, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887097_610385_20581, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_2_edc0357_1357846887093_996466_20557_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887088_403174_20548_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887088_403174_20548_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(1));
            }

            public void init_17_0_2_2_edc0357_1357846887094_993318_20559Elaborations() {
                init_17_0_2_2_edc0357_1357846887094_993318_20559Dependencies();
                Expression<?>[] arguments297 = new Expression<?>[1];
                arguments297[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition297 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887093_996466_20557_exists);
                elaborationRule297 = addElaborationRule(condition297, _17_0_2_2_edc0357_1357846886594_431163_19884.this, Customer._17_0_2_2_edc0357_1357846886594_431163_19884._17_0_2_2_edc0357_1357846887093_996466_20557.class, "CChange_fork4changeval_ForkNode_usePowerWIthCap", arguments297);
            }
        }

        public _17_0_2_2_edc0357_1357846886594_431163_19884(Expression<Integer> startTime, Expression<DurativeEvent> caller) {
            super();
            init_17_0_2_2_edc0357_1357846886594_431163_19884Members();
            init_17_0_2_2_edc0357_1357846886594_431163_19884Collections();
            addDependency(this.startTime, startTime);
            addDependency(this.caller, caller);
            init_17_0_2_2_edc0357_1357846886594_431163_19884Elaborations();
            fixTimeDependencies();
        }

        public Parameter< ObjectFlow<Customer> > sig_17_0_2_2_edc0357_1357846887097_610385_20581 = null;

        public Parameter< TimeVaryingList<Integer> > decider_17_0_2_2_edc0357_1357846887090_147895_20552 = null;

        public Parameter< DurativeEvent > caller = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_2_2_edc0357_1357846887096_982806_20571 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_2_edc0357_1357846887096_444324_20573 = null;

        public Parameter< TimeVaryingList<Integer> > decider_17_0_2_2_edc0357_1357846887087_156791_20546 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_2_2_edc0357_1357846887096_582848_20578 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_2_edc0357_1357846887095_557654_20567 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_2_edc0357_1357846887095_249792_20568 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_2_2_edc0357_1357846887096_400003_20570 = null;

        public Parameter< TimeVaryingList<Integer> > decider_17_0_2_2_edc0357_1357846887090_54233_20551 = null;

        public BooleanParameter _17_0_2_2_edc0357_1357846887088_403174_20548_exists = null;

        public Parameter< ObjectFlow<Customer> > sig_17_0_2_2_edc0357_1357846887094_979270_20560 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_2_2_edc0357_1357846887492_546127_21510 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_2_2_edc0357_1357846887095_325507_20565 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_2_2_edc0357_1357846887097_514850_20580 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_2_2_edc0357_1357846887096_35914_20575 = null;

        public Parameter< TimeVaryingList<Integer> > decider_17_0_2_2_edc0357_1357846887092_669043_20556 = null;

        public Parameter< ObjectFlow<Customer> > sig_17_0_2_2_edc0357_1357846887095_272551_20561 = null;

        public IntegerParameter finalNode_endTime = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_2_edc0357_1357846887096_475919_20574 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_2_2_edc0357_1357846887097_558436_20579 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_2_2_edc0357_1357846887096_473015_20572 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_2_2_edc0357_1357846887493_344325_21511 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_2_edc0357_1357846887095_989056_20569 = null;

        public IntegerParameter finalNode_startTime = null;

        public Parameter< ObjectFlow<Customer> > sig_17_0_2_2_edc0357_1357846887095_160286_20563 = null;

        public Parameter< ObjectFlow<Customer> > sig_17_0_2_2_edc0357_1357846887095_741476_20562 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_2_2_edc0357_1357846887095_696449_20566 = null;

        public Parameter< TimeVaryingList<Integer> > decider_17_0_2_2_edc0357_1357846887091_449842_20554 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_2_2_edc0357_1357846887096_822062_20576 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_2_edc0357_1357846887095_579591_20564 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_2_2_edc0357_1357846887096_159549_20577 = null;

        public Dependency caller_endTimeDependency = null;

        public Dependency _17_0_2_2_edc0357_1357846887088_403174_20548_existsDependency = null;

        public Dependency endTimeDependency = null;

        public ElaborationRule elaborationRule238 = null;

        public ElaborationRule elaborationRule239 = null;

        public void init_17_0_2_2_edc0357_1357846886594_431163_19884Members() {
            try {
                if (sig_17_0_2_2_edc0357_1357846887097_610385_20581 == null) sig_17_0_2_2_edc0357_1357846887097_610385_20581 = new Parameter<ObjectFlow<Customer>>("sig_17_0_2_2_edc0357_1357846887097_610385_20581", null, (ObjectFlow<Customer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887097_610385_20581" })).evaluate(true), this);
                if (decider_17_0_2_2_edc0357_1357846887090_147895_20552 == null) decider_17_0_2_2_edc0357_1357846887090_147895_20552 = new Parameter<TimeVaryingList<Integer>>("decider_17_0_2_2_edc0357_1357846887090_147895_20552", null, (TimeVaryingList<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingList.class, java.lang.String.class, int.class), new Object[] { "decider_17_0_2_2_edc0357_1357846887090_147895_20552", 3 })).evaluate(true), this);
                if (caller == null) caller = new Parameter<DurativeEvent>("caller", null, (DurativeEvent) null, this);
                if (sig_17_0_2_2_edc0357_1357846887096_982806_20571 == null) sig_17_0_2_2_edc0357_1357846887096_982806_20571 = new Parameter<ObjectFlow<Integer>>("sig_17_0_2_2_edc0357_1357846887096_982806_20571", null, (ObjectFlow<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887096_982806_20571" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846887096_444324_20573 == null) sig_17_0_2_2_edc0357_1357846887096_444324_20573 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_2_edc0357_1357846887096_444324_20573", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887096_444324_20573" })).evaluate(true), this);
                if (decider_17_0_2_2_edc0357_1357846887087_156791_20546 == null) decider_17_0_2_2_edc0357_1357846887087_156791_20546 = new Parameter<TimeVaryingList<Integer>>("decider_17_0_2_2_edc0357_1357846887087_156791_20546", null, (TimeVaryingList<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingList.class, java.lang.String.class, int.class), new Object[] { "decider_17_0_2_2_edc0357_1357846887087_156791_20546", 2 })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846887096_582848_20578 == null) sig_17_0_2_2_edc0357_1357846887096_582848_20578 = new Parameter<ObjectFlow<Integer>>("sig_17_0_2_2_edc0357_1357846887096_582848_20578", null, (ObjectFlow<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887096_582848_20578" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846887095_557654_20567 == null) sig_17_0_2_2_edc0357_1357846887095_557654_20567 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_2_edc0357_1357846887095_557654_20567", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887095_557654_20567" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846887095_249792_20568 == null) sig_17_0_2_2_edc0357_1357846887095_249792_20568 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_2_edc0357_1357846887095_249792_20568", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887095_249792_20568" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846887096_400003_20570 == null) sig_17_0_2_2_edc0357_1357846887096_400003_20570 = new Parameter<ObjectFlow<Integer>>("sig_17_0_2_2_edc0357_1357846887096_400003_20570", null, (ObjectFlow<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887096_400003_20570" })).evaluate(true), this);
                if (decider_17_0_2_2_edc0357_1357846887090_54233_20551 == null) decider_17_0_2_2_edc0357_1357846887090_54233_20551 = new Parameter<TimeVaryingList<Integer>>("decider_17_0_2_2_edc0357_1357846887090_54233_20551", null, (TimeVaryingList<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingList.class, java.lang.String.class, int.class), new Object[] { "decider_17_0_2_2_edc0357_1357846887090_54233_20551", 3 })).evaluate(true), this);
                if (_17_0_2_2_edc0357_1357846887088_403174_20548_exists == null) _17_0_2_2_edc0357_1357846887088_403174_20548_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887088_403174_20548_exists", (Boolean) false, this);
                if (sig_17_0_2_2_edc0357_1357846887094_979270_20560 == null) sig_17_0_2_2_edc0357_1357846887094_979270_20560 = new Parameter<ObjectFlow<Customer>>("sig_17_0_2_2_edc0357_1357846887094_979270_20560", null, (ObjectFlow<Customer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887094_979270_20560" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846887492_546127_21510 == null) sig_17_0_2_2_edc0357_1357846887492_546127_21510 = new Parameter<ObjectFlow<Integer>>("sig_17_0_2_2_edc0357_1357846887492_546127_21510", null, (ObjectFlow<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887492_546127_21510" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846887095_325507_20565 == null) sig_17_0_2_2_edc0357_1357846887095_325507_20565 = new Parameter<ObjectFlow<Integer>>("sig_17_0_2_2_edc0357_1357846887095_325507_20565", null, (ObjectFlow<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887095_325507_20565" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846887097_514850_20580 == null) sig_17_0_2_2_edc0357_1357846887097_514850_20580 = new Parameter<ObjectFlow<Integer>>("sig_17_0_2_2_edc0357_1357846887097_514850_20580", null, (ObjectFlow<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887097_514850_20580" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846887096_35914_20575 == null) sig_17_0_2_2_edc0357_1357846887096_35914_20575 = new Parameter<ObjectFlow<Integer>>("sig_17_0_2_2_edc0357_1357846887096_35914_20575", null, (ObjectFlow<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887096_35914_20575" })).evaluate(true), this);
                if (decider_17_0_2_2_edc0357_1357846887092_669043_20556 == null) decider_17_0_2_2_edc0357_1357846887092_669043_20556 = new Parameter<TimeVaryingList<Integer>>("decider_17_0_2_2_edc0357_1357846887092_669043_20556", null, (TimeVaryingList<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingList.class, java.lang.String.class, int.class), new Object[] { "decider_17_0_2_2_edc0357_1357846887092_669043_20556", 2 })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846887095_272551_20561 == null) sig_17_0_2_2_edc0357_1357846887095_272551_20561 = new Parameter<ObjectFlow<Customer>>("sig_17_0_2_2_edc0357_1357846887095_272551_20561", null, (ObjectFlow<Customer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887095_272551_20561" })).evaluate(true), this);
                if (finalNode_endTime == null) finalNode_endTime = new IntegerParameter("finalNode_endTime", (Integer) null, this);
                if (sig_17_0_2_2_edc0357_1357846887096_475919_20574 == null) sig_17_0_2_2_edc0357_1357846887096_475919_20574 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_2_edc0357_1357846887096_475919_20574", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887096_475919_20574" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846887097_558436_20579 == null) sig_17_0_2_2_edc0357_1357846887097_558436_20579 = new Parameter<ObjectFlow<Integer>>("sig_17_0_2_2_edc0357_1357846887097_558436_20579", null, (ObjectFlow<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887097_558436_20579" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846887096_473015_20572 == null) sig_17_0_2_2_edc0357_1357846887096_473015_20572 = new Parameter<ObjectFlow<Integer>>("sig_17_0_2_2_edc0357_1357846887096_473015_20572", null, (ObjectFlow<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887096_473015_20572" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846887493_344325_21511 == null) sig_17_0_2_2_edc0357_1357846887493_344325_21511 = new Parameter<ObjectFlow<Integer>>("sig_17_0_2_2_edc0357_1357846887493_344325_21511", null, (ObjectFlow<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887493_344325_21511" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846887095_989056_20569 == null) sig_17_0_2_2_edc0357_1357846887095_989056_20569 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_2_edc0357_1357846887095_989056_20569", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887095_989056_20569" })).evaluate(true), this);
                if (finalNode_startTime == null) finalNode_startTime = new IntegerParameter("finalNode_startTime", (Integer) null, this);
                if (sig_17_0_2_2_edc0357_1357846887095_160286_20563 == null) sig_17_0_2_2_edc0357_1357846887095_160286_20563 = new Parameter<ObjectFlow<Customer>>("sig_17_0_2_2_edc0357_1357846887095_160286_20563", null, (ObjectFlow<Customer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887095_160286_20563" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846887095_741476_20562 == null) sig_17_0_2_2_edc0357_1357846887095_741476_20562 = new Parameter<ObjectFlow<Customer>>("sig_17_0_2_2_edc0357_1357846887095_741476_20562", null, (ObjectFlow<Customer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887095_741476_20562" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846887095_696449_20566 == null) sig_17_0_2_2_edc0357_1357846887095_696449_20566 = new Parameter<ObjectFlow<Integer>>("sig_17_0_2_2_edc0357_1357846887095_696449_20566", null, (ObjectFlow<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887095_696449_20566" })).evaluate(true), this);
                if (decider_17_0_2_2_edc0357_1357846887091_449842_20554 == null) decider_17_0_2_2_edc0357_1357846887091_449842_20554 = new Parameter<TimeVaryingList<Integer>>("decider_17_0_2_2_edc0357_1357846887091_449842_20554", null, (TimeVaryingList<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingList.class, java.lang.String.class, int.class), new Object[] { "decider_17_0_2_2_edc0357_1357846887091_449842_20554", 2 })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846887096_822062_20576 == null) sig_17_0_2_2_edc0357_1357846887096_822062_20576 = new Parameter<ObjectFlow<Integer>>("sig_17_0_2_2_edc0357_1357846887096_822062_20576", null, (ObjectFlow<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887096_822062_20576" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846887095_579591_20564 == null) sig_17_0_2_2_edc0357_1357846887095_579591_20564 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_2_edc0357_1357846887095_579591_20564", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887095_579591_20564" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846887096_159549_20577 == null) sig_17_0_2_2_edc0357_1357846887096_159549_20577 = new Parameter<ObjectFlow<Integer>>("sig_17_0_2_2_edc0357_1357846887096_159549_20577", null, (ObjectFlow<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887096_159549_20577" })).evaluate(true), this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void init_17_0_2_2_edc0357_1357846886594_431163_19884Collections() {
            parameters.add(sig_17_0_2_2_edc0357_1357846887097_610385_20581);
            parameters.add(decider_17_0_2_2_edc0357_1357846887090_147895_20552);
            parameters.add(caller);
            parameters.add(sig_17_0_2_2_edc0357_1357846887096_982806_20571);
            parameters.add(sig_17_0_2_2_edc0357_1357846887096_444324_20573);
            parameters.add(decider_17_0_2_2_edc0357_1357846887087_156791_20546);
            parameters.add(sig_17_0_2_2_edc0357_1357846887096_582848_20578);
            parameters.add(sig_17_0_2_2_edc0357_1357846887095_557654_20567);
            parameters.add(sig_17_0_2_2_edc0357_1357846887095_249792_20568);
            parameters.add(sig_17_0_2_2_edc0357_1357846887096_400003_20570);
            parameters.add(decider_17_0_2_2_edc0357_1357846887090_54233_20551);
            parameters.add(_17_0_2_2_edc0357_1357846887088_403174_20548_exists);
            parameters.add(sig_17_0_2_2_edc0357_1357846887094_979270_20560);
            parameters.add(sig_17_0_2_2_edc0357_1357846887492_546127_21510);
            parameters.add(sig_17_0_2_2_edc0357_1357846887095_325507_20565);
            parameters.add(sig_17_0_2_2_edc0357_1357846887097_514850_20580);
            parameters.add(sig_17_0_2_2_edc0357_1357846887096_35914_20575);
            parameters.add(decider_17_0_2_2_edc0357_1357846887092_669043_20556);
            parameters.add(sig_17_0_2_2_edc0357_1357846887095_272551_20561);
            parameters.add(finalNode_endTime);
            parameters.add(sig_17_0_2_2_edc0357_1357846887096_475919_20574);
            parameters.add(sig_17_0_2_2_edc0357_1357846887097_558436_20579);
            parameters.add(sig_17_0_2_2_edc0357_1357846887096_473015_20572);
            parameters.add(sig_17_0_2_2_edc0357_1357846887493_344325_21511);
            parameters.add(sig_17_0_2_2_edc0357_1357846887095_989056_20569);
            parameters.add(finalNode_startTime);
            parameters.add(sig_17_0_2_2_edc0357_1357846887095_160286_20563);
            parameters.add(sig_17_0_2_2_edc0357_1357846887095_741476_20562);
            parameters.add(sig_17_0_2_2_edc0357_1357846887095_696449_20566);
            parameters.add(decider_17_0_2_2_edc0357_1357846887091_449842_20554);
            parameters.add(sig_17_0_2_2_edc0357_1357846887096_822062_20576);
            parameters.add(sig_17_0_2_2_edc0357_1357846887095_579591_20564);
            parameters.add(sig_17_0_2_2_edc0357_1357846887096_159549_20577);
        }

        public void init_17_0_2_2_edc0357_1357846886594_431163_19884Dependencies() {
            caller.getValue(true).addDependency(caller.getValue(true).endTime, new Expression<Integer>(finalNode_endTime));
            addDependency(_17_0_2_2_edc0357_1357846887088_403174_20548_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.NotEquals(new Expression<Integer>(finalNode_startTime), null)), new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887095_579591_20564, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(new Functions.Minus(new Expression<Integer>(finalNode_startTime), new Expression<Integer>(1))) })))));
            addDependency(endTime, new Expression<Integer>(finalNode_endTime));
        }

        public void init_17_0_2_2_edc0357_1357846886594_431163_19884Elaborations() {
            init_17_0_2_2_edc0357_1357846886594_431163_19884Dependencies();
            Expression<?>[] arguments238 = new Expression<?>[1];
            arguments238[0] = new Expression<Integer>(finalNode_startTime);
            Expression<Boolean> condition238 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887088_403174_20548_exists);
            elaborationRule238 = addElaborationRule(condition238, _17_0_2_2_edc0357_1357846886594_431163_19884.this, Customer._17_0_2_2_edc0357_1357846886594_431163_19884._17_0_2_2_edc0357_1357846887088_403174_20548.class, "CChange_end_ActivityFinalNode_usePowerWIthCap", arguments238);
            Expression<?>[] arguments239 = new Expression<?>[1];
            arguments239[0] = new Expression<Integer>(startTime);
            Expression<Boolean> condition239 = new Expression<Boolean>(true);
            elaborationRule239 = addElaborationRule(condition239, _17_0_2_2_edc0357_1357846886594_431163_19884.this, Customer._17_0_2_2_edc0357_1357846886594_431163_19884._17_0_2_2_edc0357_1357846887087_881168_20547.class, "CChange_start_InitialNode_usePowerWIthCap", arguments239);
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

    public Parameter< TimeVaryingPlottableMap<Integer> > cap__17_0_2_2_edc0357_1357846886598_990111_19890 = null;

    public Parameter< ObjectFlow<Power_System.Signaldr_request> > q_Customer_dr_request = null;

    public Parameter< Power_System > x = null;

    public Parameter< ObjectFlow<Power_System.Signalyes> > q_Customer_yes = null;

    public Parameter< ObjectFlow<Power_System.SignalchangeLoadValue> > q_Customer_changeLoadValue = null;

    public Parameter< TimeVaryingPlottableMap<Double> > loadProfile__17_0_2_2_edc0357_1357846886599_502905_19891 = null;

    public Parameter< ObjectFlow<Power_System.Signalno> > q_Customer_no = null;

    public Parameter< TimeVaryingPlottableMap<Integer> > usage__17_0_2_2_edc0357_1357846886595_378353_19885 = null;

    public void initCustomerMembers() {
        try {
            if (classifierBehavior == null) classifierBehavior = new StringParameter("classifierBehavior", (String) "_17_0_2_2_edc0357_1357846886593_851494_19883", this);
            if (q_Customer_receiveMeterReading == null) q_Customer_receiveMeterReading = new Parameter<ObjectFlow<Power_System.SignalreceiveMeterReading>>("q_Customer_receiveMeterReading", null, (ObjectFlow<Power_System.SignalreceiveMeterReading>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class, java.lang.Class.class), new Object[] { "q_Customer_receiveMeterReading", Power_System.SignalreceiveMeterReading.class })).evaluate(true), this);
            if (cap__17_0_2_2_edc0357_1357846886598_990111_19890 == null) cap__17_0_2_2_edc0357_1357846886598_990111_19890 = new Parameter<TimeVaryingPlottableMap<Integer>>("cap__17_0_2_2_edc0357_1357846886598_990111_19890", null, (TimeVaryingPlottableMap<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingPlottableMap.class, java.lang.String.class, java.lang.String.class, java.lang.Class.class, boolean.class), new Object[] { "cap", null, Integer.class, false })).evaluate(true), this);
            if (q_Customer_dr_request == null) q_Customer_dr_request = new Parameter<ObjectFlow<Power_System.Signaldr_request>>("q_Customer_dr_request", null, (ObjectFlow<Power_System.Signaldr_request>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class, java.lang.Class.class), new Object[] { "q_Customer_dr_request", Power_System.Signaldr_request.class })).evaluate(true), this);
            if (x == null) x = new Parameter<Power_System>("x", null, (Power_System) null, this);
            if (q_Customer_yes == null) q_Customer_yes = new Parameter<ObjectFlow<Power_System.Signalyes>>("q_Customer_yes", null, (ObjectFlow<Power_System.Signalyes>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class, java.lang.Class.class), new Object[] { "q_Customer_yes", Power_System.Signalyes.class })).evaluate(true), this);
            if (q_Customer_changeLoadValue == null) q_Customer_changeLoadValue = new Parameter<ObjectFlow<Power_System.SignalchangeLoadValue>>("q_Customer_changeLoadValue", null, (ObjectFlow<Power_System.SignalchangeLoadValue>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class, java.lang.Class.class), new Object[] { "q_Customer_changeLoadValue", Power_System.SignalchangeLoadValue.class })).evaluate(true), this);
            if (loadProfile__17_0_2_2_edc0357_1357846886599_502905_19891 == null) loadProfile__17_0_2_2_edc0357_1357846886599_502905_19891 = new Parameter<TimeVaryingPlottableMap<Double>>("loadProfile__17_0_2_2_edc0357_1357846886599_502905_19891", null, (TimeVaryingPlottableMap<Double>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingPlottableMap.class, java.lang.String.class, java.lang.String.class, java.lang.Class.class, boolean.class), new Object[] { "loadProfile", "aggregateLoad.csv", Double.class, true })).evaluate(true), this);
            if (q_Customer_no == null) q_Customer_no = new Parameter<ObjectFlow<Power_System.Signalno>>("q_Customer_no", null, (ObjectFlow<Power_System.Signalno>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class, java.lang.Class.class), new Object[] { "q_Customer_no", Power_System.Signalno.class })).evaluate(true), this);
            if (usage__17_0_2_2_edc0357_1357846886595_378353_19885 == null) usage__17_0_2_2_edc0357_1357846886595_378353_19885 = new Parameter<TimeVaryingPlottableMap<Integer>>("usage__17_0_2_2_edc0357_1357846886595_378353_19885", null, (TimeVaryingPlottableMap<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingPlottableMap.class, java.lang.String.class, java.lang.String.class, java.lang.Class.class, boolean.class), new Object[] { "usage", null, Integer.class, false })).evaluate(true), this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void initCustomerCollections() {
        parameters.add(classifierBehavior);
        parameters.add(q_Customer_receiveMeterReading);
        parameters.add(cap__17_0_2_2_edc0357_1357846886598_990111_19890);
        parameters.add(q_Customer_dr_request);
        parameters.add(x);
        parameters.add(q_Customer_yes);
        parameters.add(q_Customer_changeLoadValue);
        parameters.add(loadProfile__17_0_2_2_edc0357_1357846886599_502905_19891);
        parameters.add(q_Customer_no);
        parameters.add(usage__17_0_2_2_edc0357_1357846886595_378353_19885);
    }

    public void initCustomerDependencies() {
    }

    public void initCustomerElaborations() {
        initCustomerDependencies();
    }
}
