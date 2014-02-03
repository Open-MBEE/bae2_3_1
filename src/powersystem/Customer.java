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

public class Customer extends ParameterListenerImpl {

    public Customer() {
        super();
        initCustomerMembers();
        initCustomerCollections();
        initCustomerElaborations();
    }

    public class _17_0_2_edc0357_1352328156638_770509_19615 extends DurativeEvent {

        public _17_0_2_edc0357_1352328156638_770509_19615() {
            super();
            init_17_0_2_edc0357_1352328156638_770509_19615Members();
            init_17_0_2_edc0357_1352328156638_770509_19615Collections();
            init_17_0_2_edc0357_1352328156638_770509_19615Elaborations();
        }

        public class _17_0_2_edc0357_1352328158272_257857_20196 extends DurativeEvent {

            public _17_0_2_edc0357_1352328158272_257857_20196() {
                super();
                init_17_0_2_edc0357_1352328158272_257857_20196Members();
                init_17_0_2_edc0357_1352328158272_257857_20196Collections();
                init_17_0_2_edc0357_1352328158272_257857_20196Elaborations();
            }

            public _17_0_2_edc0357_1352328158272_257857_20196(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328158272_257857_20196Members();
                init_17_0_2_edc0357_1352328158272_257857_20196Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328158272_257857_20196Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter objectToPass = null;

            public BooleanParameter _17_0_2_edc0357_1352328158272_534943_20197_exists = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158272_534943_20197_existsDependency = null;

            public Effect effect34 = null;

            public Parameter effect34Var = null;

            public ElaborationRule elaborationRule35 = null;

            public void init_17_0_2_edc0357_1352328158272_257857_20196Members() {
                try {
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (_17_0_2_edc0357_1352328158272_534943_20197_exists == null) _17_0_2_edc0357_1352328158272_534943_20197_exists = new BooleanParameter("_17_0_2_edc0357_1352328158272_534943_20197_exists", (Boolean) false, this);
                    Object effect34VarV = sig_17_0_2_edc0357_1352328158276_873292_20205;
                    effect34Var = new Parameter("effect34Var", null, null, this);
                    addDependency(effect34Var, new Expression(effect34VarV));
                    effect34 = new EffectFunction(new EffectFunction(effect34Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158272_257857_20196Collections() {
                parameters.add(objectToPass);
                parameters.add(_17_0_2_edc0357_1352328158272_534943_20197_exists);
                Set<Effect> effectsForeffect34Var = new TreeSet<Effect>();
                effectsForeffect34Var.add(effect34);
                addEffects((Parameter<?>) effect34Var, effectsForeffect34Var);
            }

            public void init_17_0_2_edc0357_1352328158272_257857_20196Dependencies() {
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(true));
                addDependency(_17_0_2_edc0357_1352328158272_534943_20197_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_edc0357_1352328158273_555088_20198_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_edc0357_1352328158273_555088_20198_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
            }

            public void init_17_0_2_edc0357_1352328158272_257857_20196Elaborations() {
                init_17_0_2_edc0357_1352328158272_257857_20196Dependencies();
                Expression<?>[] arguments35 = new Expression<?>[1];
                arguments35[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition35 = new Expression<Boolean>(_17_0_2_edc0357_1352328158272_534943_20197_exists);
                elaborationRule35 = addElaborationRule(condition35, _17_0_2_edc0357_1352328156638_770509_19615.this, Customer._17_0_2_edc0357_1352328156638_770509_19615._17_0_2_edc0357_1352328158272_534943_20197.class, "CUse_readself_ReadSelfAction_usePower", arguments35);
            }
        }

        public class _17_0_2_edc0357_1352328158272_534943_20197 extends DurativeEvent {

            public _17_0_2_edc0357_1352328158272_534943_20197() {
                super();
                init_17_0_2_edc0357_1352328158272_534943_20197Members();
                init_17_0_2_edc0357_1352328158272_534943_20197Collections();
                init_17_0_2_edc0357_1352328158272_534943_20197Elaborations();
            }

            public _17_0_2_edc0357_1352328158272_534943_20197(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328158272_534943_20197Members();
                init_17_0_2_edc0357_1352328158272_534943_20197Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328158272_534943_20197Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_edc0357_1352328158274_877259_20201_exists = null;

            public Parameter< Customer > _17_0_2_edc0357_1352328160533_21059_21384 = null;

            public BooleanParameter objectToPass = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158274_877259_20201_existsDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Effect effect36 = null;

            public Parameter effect36Var = null;

            public ElaborationRule elaborationRule37 = null;

            public void init_17_0_2_edc0357_1352328158272_534943_20197Members() {
                try {
                    if (_17_0_2_edc0357_1352328158274_877259_20201_exists == null) _17_0_2_edc0357_1352328158274_877259_20201_exists = new BooleanParameter("_17_0_2_edc0357_1352328158274_877259_20201_exists", (Boolean) false, this);
                    if (_17_0_2_edc0357_1352328160533_21059_21384 == null) _17_0_2_edc0357_1352328160533_21059_21384 = new Parameter<Customer>("_17_0_2_edc0357_1352328160533_21059_21384", null, (Customer) Customer.this, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect36VarV = sig_17_0_2_edc0357_1352328158276_279334_20206;
                    effect36Var = new Parameter("effect36Var", null, null, this);
                    addDependency(effect36Var, new Expression(effect36VarV));
                    effect36 = new EffectFunction(new EffectFunction(effect36Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { _17_0_2_edc0357_1352328160533_21059_21384, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158272_534943_20197Collections() {
                parameters.add(_17_0_2_edc0357_1352328158274_877259_20201_exists);
                parameters.add(_17_0_2_edc0357_1352328160533_21059_21384);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect36Var = new TreeSet<Effect>();
                effectsForeffect36Var.add(effect36);
                addEffects((Parameter<?>) effect36Var, effectsForeffect36Var);
            }

            public void init_17_0_2_edc0357_1352328158272_534943_20197Dependencies() {
                addDependency(_17_0_2_edc0357_1352328158274_877259_20201_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_edc0357_1352328158273_555088_20198_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_edc0357_1352328158273_555088_20198_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new EffectFunction(sig_17_0_2_edc0357_1352328158276_873292_20205, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_edc0357_1352328158272_534943_20197Elaborations() {
                init_17_0_2_edc0357_1352328158272_534943_20197Dependencies();
                Expression<?>[] arguments37 = new Expression<?>[1];
                arguments37[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition37 = new Expression<Boolean>(_17_0_2_edc0357_1352328158274_877259_20201_exists);
                elaborationRule37 = addElaborationRule(condition37, _17_0_2_edc0357_1352328156638_770509_19615.this, Customer._17_0_2_edc0357_1352328156638_770509_19615._17_0_2_edc0357_1352328158274_877259_20201.class, "CUse_forkself_ForkNode_usePower", arguments37);
            }
        }

        public class _17_0_2_edc0357_1352328158273_555088_20198 extends DurativeEvent {

            public _17_0_2_edc0357_1352328158273_555088_20198() {
                super();
                init_17_0_2_edc0357_1352328158273_555088_20198Members();
                init_17_0_2_edc0357_1352328158273_555088_20198Collections();
                init_17_0_2_edc0357_1352328158273_555088_20198Elaborations();
            }

            public _17_0_2_edc0357_1352328158273_555088_20198(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328158273_555088_20198Members();
                init_17_0_2_edc0357_1352328158273_555088_20198Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328158273_555088_20198Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter objectToPass = null;

            public Dependency< Integer > endTimeDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Dependency< Integer > finalNode_endTimeDependency = null;

            public void init_17_0_2_edc0357_1352328158273_555088_20198Members() {
                try {
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158273_555088_20198Collections() {
                parameters.add(objectToPass);
            }

            public void init_17_0_2_edc0357_1352328158273_555088_20198Dependencies() {
                addDependency(endTime, new Expression<Integer>(new Functions.Plus(new Expression<Integer>(startTime), new Expression<Integer>(duration))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new EffectFunction(sig_17_0_2_edc0357_1352328158277_748946_20214, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(finalNode_endTime, new Expression<Integer>(endTime));
            }

            public void init_17_0_2_edc0357_1352328158273_555088_20198Elaborations() {
                init_17_0_2_edc0357_1352328158273_555088_20198Dependencies();
            }
        }

        public class _17_0_2_edc0357_1352328158273_329618_20199 extends DurativeEvent {

            public _17_0_2_edc0357_1352328158273_329618_20199() {
                super();
                init_17_0_2_edc0357_1352328158273_329618_20199Members();
                init_17_0_2_edc0357_1352328158273_329618_20199Collections();
                init_17_0_2_edc0357_1352328158273_329618_20199Elaborations();
            }

            public _17_0_2_edc0357_1352328158273_329618_20199(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328158273_329618_20199Members();
                init_17_0_2_edc0357_1352328158273_329618_20199Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328158273_329618_20199Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_edc0357_1352328158275_94410_20203_exists = null;

            public Parameter< Customer > _17_0_2_edc0357_1352328160535_109318_21386 = null;

            public IntegerParameter _17_0_2_edc0357_1352328160534_502046_21385 = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158275_94410_20203_existsDependency = null;

            public Dependency< Customer > _17_0_2_edc0357_1352328160535_109318_21386Dependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Integer > _17_0_2_edc0357_1352328160534_502046_21385Dependency = null;

            public Effect effect38 = null;

            public Parameter effect38Var = null;

            public ElaborationRule elaborationRule39 = null;

            public void init_17_0_2_edc0357_1352328158273_329618_20199Members() {
                try {
                    if (_17_0_2_edc0357_1352328158275_94410_20203_exists == null) _17_0_2_edc0357_1352328158275_94410_20203_exists = new BooleanParameter("_17_0_2_edc0357_1352328158275_94410_20203_exists", (Boolean) false, this);
                    if (_17_0_2_edc0357_1352328160535_109318_21386 == null) _17_0_2_edc0357_1352328160535_109318_21386 = new Parameter<Customer>("_17_0_2_edc0357_1352328160535_109318_21386", null, (Customer) null, this);
                    if (_17_0_2_edc0357_1352328160534_502046_21385 == null) _17_0_2_edc0357_1352328160534_502046_21385 = new IntegerParameter("_17_0_2_edc0357_1352328160534_502046_21385", (Integer) null, this);
                    Object effect38VarV = sig_17_0_2_edc0357_1352328158277_819385_20210;
                    effect38Var = new Parameter("effect38Var", null, null, this);
                    addDependency(effect38Var, new Expression(effect38VarV));
                    effect38 = new EffectFunction(new EffectFunction(effect38Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { _17_0_2_edc0357_1352328160534_502046_21385, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158273_329618_20199Collections() {
                parameters.add(_17_0_2_edc0357_1352328158275_94410_20203_exists);
                parameters.add(_17_0_2_edc0357_1352328160535_109318_21386);
                parameters.add(_17_0_2_edc0357_1352328160534_502046_21385);
                Set<Effect> effectsForeffect38Var = new TreeSet<Effect>();
                effectsForeffect38Var.add(effect38);
                addEffects((Parameter<?>) effect38Var, effectsForeffect38Var);
            }

            public void init_17_0_2_edc0357_1352328158273_329618_20199Dependencies() {
                addDependency(_17_0_2_edc0357_1352328158275_94410_20203_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_edc0357_1352328158273_555088_20198_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_edc0357_1352328158273_555088_20198_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(_17_0_2_edc0357_1352328160535_109318_21386, new Expression<Customer>(new EffectFunction(sig_17_0_2_edc0357_1352328158276_305344_20207, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(_17_0_2_edc0357_1352328160534_502046_21385, new Expression<Integer>(new EffectFunction(null, ClassUtils.getMethodForArgTypes("TimeVaryingPlottableMap<Integer>", "powersystem", "getValue", java.lang.Integer.class), new Object[] { new Expression<Integer>(startTime) }, new FunctionCall(_17_0_2_edc0357_1352328160535_109318_21386, Parameter.class, "getMember", new Object[] { "usage__17_0_2_edc0357_1352328156649_216766_19620" }))));
            }

            public void init_17_0_2_edc0357_1352328158273_329618_20199Elaborations() {
                init_17_0_2_edc0357_1352328158273_329618_20199Dependencies();
                Expression<?>[] arguments39 = new Expression<?>[1];
                arguments39[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition39 = new Expression<Boolean>(_17_0_2_edc0357_1352328158275_94410_20203_exists);
                elaborationRule39 = addElaborationRule(condition39, _17_0_2_edc0357_1352328156638_770509_19615.this, Customer._17_0_2_edc0357_1352328156638_770509_19615._17_0_2_edc0357_1352328158275_94410_20203.class, "CUse_fork2_ForkNode_usePower", arguments39);
            }
        }

        public class _17_0_2_edc0357_1352328158273_110644_20200 extends DurativeEvent {

            public _17_0_2_edc0357_1352328158273_110644_20200() {
                super();
                init_17_0_2_edc0357_1352328158273_110644_20200Members();
                init_17_0_2_edc0357_1352328158273_110644_20200Collections();
                init_17_0_2_edc0357_1352328158273_110644_20200Elaborations();
            }

            public _17_0_2_edc0357_1352328158273_110644_20200(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328158273_110644_20200Members();
                init_17_0_2_edc0357_1352328158273_110644_20200Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328158273_110644_20200Elaborations();
                fixTimeDependencies();
            }

            public IntegerParameter _17_0_2_edc0357_1352328160536_968371_21388 = null;

            public BooleanParameter _17_0_2_edc0357_1352328158275_65316_20204_exists = null;

            public Parameter< Customer > _17_0_2_edc0357_1352328160535_385192_21387 = null;

            public IntegerParameter myDeciderID_decider_17_0_2_edc0357_1352328158275_65316_20204 = null;

            public BooleanParameter objectToPass = null;

            public Parameter< Power_System.SignalchangeLoadValue > signalObject = null;

            public Dependency< Integer > _17_0_2_edc0357_1352328160536_968371_21388Dependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158275_65316_20204_existsDependency = null;

            public Dependency< Customer > _17_0_2_edc0357_1352328160535_385192_21387Dependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Integer > myDeciderID_decider_17_0_2_edc0357_1352328158275_65316_20204Dependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Dependency< Power_System.SignalchangeLoadValue > signalObjectDependency = null;

            public Effect effect40 = null;

            public Parameter effect40Var = null;

            public Effect effect41 = null;

            public Parameter effect41Var = null;

            public Effect effect42 = null;

            public Parameter effect42Var = null;

            public Effect effect43 = null;

            public Parameter effect43Var = null;

            public ElaborationRule elaborationRule44 = null;

            public void init_17_0_2_edc0357_1352328158273_110644_20200Members() {
                try {
                    if (_17_0_2_edc0357_1352328160536_968371_21388 == null) _17_0_2_edc0357_1352328160536_968371_21388 = new IntegerParameter("_17_0_2_edc0357_1352328160536_968371_21388", (Integer) null, this);
                    if (_17_0_2_edc0357_1352328158275_65316_20204_exists == null) _17_0_2_edc0357_1352328158275_65316_20204_exists = new BooleanParameter("_17_0_2_edc0357_1352328158275_65316_20204_exists", (Boolean) false, this);
                    if (_17_0_2_edc0357_1352328160535_385192_21387 == null) _17_0_2_edc0357_1352328160535_385192_21387 = new Parameter<Customer>("_17_0_2_edc0357_1352328160535_385192_21387", null, (Customer) null, this);
                    if (myDeciderID_decider_17_0_2_edc0357_1352328158275_65316_20204 == null) myDeciderID_decider_17_0_2_edc0357_1352328158275_65316_20204 = new IntegerParameter("myDeciderID_decider_17_0_2_edc0357_1352328158275_65316_20204", (Integer) 1, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (signalObject == null) signalObject = new Parameter<Power_System.SignalchangeLoadValue>("signalObject", null, (Power_System.SignalchangeLoadValue) null, this);
                    Object effect40VarV = sig_17_0_2_edc0357_1352328158277_686827_20213;
                    effect40Var = new Parameter("effect40Var", null, null, this);
                    addDependency(effect40Var, new Expression(effect40VarV));
                    effect40 = new EffectFunction(new EffectFunction(effect40Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect41VarV = decider_17_0_2_edc0357_1352328158275_65316_20204;
                    effect41Var = new Parameter("effect41Var", null, null, this);
                    addDependency(effect41Var, new Expression(effect41VarV));
                    effect41 = new EffectFunction(new EffectFunction(effect41Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, myDeciderID_decider_17_0_2_edc0357_1352328158275_65316_20204 }));
                    Object effect42VarV = new FunctionCall(x, Parameter.class, "getMember", new Object[] { "ss_17_0_2_edc0357_1352328156650_180051_19621_changeLoadValue" });
                    effect42Var = new Parameter("effect42Var", null, null, this);
                    addDependency(effect42Var, new Expression(effect42VarV));
                    effect42 = new EffectFunction(new EffectFunction(null, ClassUtils.getMethodForArgTypes("ObjectFlow<SignalchangeLoadValue>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { signalObject, endTime }, effect42Var));
                    Object effect43VarV = new FunctionCall(signalObject, Parameter.class, "getMember", new Object[] { "load__17_0_2_edc0357_1352328156636_307114_19611" });
                    effect43Var = new Parameter("effect43Var", null, null, this);
                    addDependency(effect43Var, new Expression(effect43VarV));
                    effect43 = new EffectFunction(new EffectFunction(null, ClassUtils.getMethodForArgTypes("TimeVaryingMap<Integer>", "powersystem", "setValue", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, _17_0_2_edc0357_1352328160536_968371_21388 }, effect43Var));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158273_110644_20200Collections() {
                parameters.add(_17_0_2_edc0357_1352328160536_968371_21388);
                parameters.add(_17_0_2_edc0357_1352328158275_65316_20204_exists);
                parameters.add(_17_0_2_edc0357_1352328160535_385192_21387);
                parameters.add(myDeciderID_decider_17_0_2_edc0357_1352328158275_65316_20204);
                parameters.add(objectToPass);
                parameters.add(signalObject);
                Set<Effect> effectsForeffect40Var = new TreeSet<Effect>();
                effectsForeffect40Var.add(effect40);
                addEffects((Parameter<?>) effect40Var, effectsForeffect40Var);
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

            public void init_17_0_2_edc0357_1352328158273_110644_20200Dependencies() {
                addDependency(_17_0_2_edc0357_1352328160536_968371_21388, new Expression<Integer>(new EffectFunction(sig_17_0_2_edc0357_1352328158277_962194_20211, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_edc0357_1352328158275_65316_20204_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158275_65316_20204, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158275_65316_20204, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158275_65316_20204, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_edc0357_1352328158275_65316_20204)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_edc0357_1352328158273_555088_20198_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_edc0357_1352328158273_555088_20198_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))))));
                addDependency(_17_0_2_edc0357_1352328160535_385192_21387, new Expression<Customer>(new EffectFunction(sig_17_0_2_edc0357_1352328158277_91616_20208, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(myDeciderID_decider_17_0_2_edc0357_1352328158275_65316_20204, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(true));
                addDependency(signalObject, new Expression<Power_System.SignalchangeLoadValue>(new ConstructorCall(x, ClassUtils.getConstructorForArgTypes(Power_System.SignalchangeLoadValue.class), new Object[] {})));
            }

            public void init_17_0_2_edc0357_1352328158273_110644_20200Elaborations() {
                init_17_0_2_edc0357_1352328158273_110644_20200Dependencies();
                Expression<?>[] arguments44 = new Expression<?>[1];
                arguments44[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition44 = new Expression<Boolean>(_17_0_2_edc0357_1352328158275_65316_20204_exists);
                elaborationRule44 = addElaborationRule(condition44, _17_0_2_edc0357_1352328156638_770509_19615.this, Customer._17_0_2_edc0357_1352328156638_770509_19615._17_0_2_edc0357_1352328158275_65316_20204.class, "CUse_join_JoinNode_usePower", arguments44);
            }
        }

        public class _17_0_2_edc0357_1352328158274_877259_20201 extends DurativeEvent {

            public _17_0_2_edc0357_1352328158274_877259_20201() {
                super();
                init_17_0_2_edc0357_1352328158274_877259_20201Members();
                init_17_0_2_edc0357_1352328158274_877259_20201Collections();
                init_17_0_2_edc0357_1352328158274_877259_20201Elaborations();
            }

            public _17_0_2_edc0357_1352328158274_877259_20201(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328158274_877259_20201Members();
                init_17_0_2_edc0357_1352328158274_877259_20201Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328158274_877259_20201Elaborations();
                fixTimeDependencies();
            }

            public IntegerParameter myDeciderID_decider_17_0_2_edc0357_1352328158274_699982_20202 = null;

            public BooleanParameter _17_0_2_edc0357_1352328158274_699982_20202_exists = null;

            public IntegerParameter myDeciderID_decider_17_0_2_edc0357_1352328158273_110644_20200 = null;

            public BooleanParameter _17_0_2_edc0357_1352328158273_329618_20199_exists = null;

            public BooleanParameter _17_0_2_edc0357_1352328158273_110644_20200_exists = null;

            public Parameter< Customer > objectToPass = null;

            public Dependency< Integer > myDeciderID_decider_17_0_2_edc0357_1352328158274_699982_20202Dependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158274_699982_20202_existsDependency = null;

            public Dependency< Integer > myDeciderID_decider_17_0_2_edc0357_1352328158273_110644_20200Dependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158273_329618_20199_existsDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158273_110644_20200_existsDependency = null;

            public Dependency< Customer > objectToPassDependency = null;

            public Effect effect45 = null;

            public Parameter effect45Var = null;

            public Effect effect46 = null;

            public Parameter effect46Var = null;

            public Effect effect47 = null;

            public Parameter effect47Var = null;

            public Effect effect48 = null;

            public Parameter effect48Var = null;

            public Effect effect49 = null;

            public Parameter effect49Var = null;

            public ElaborationRule elaborationRule50 = null;

            public ElaborationRule elaborationRule51 = null;

            public ElaborationRule elaborationRule52 = null;

            public void init_17_0_2_edc0357_1352328158274_877259_20201Members() {
                try {
                    if (myDeciderID_decider_17_0_2_edc0357_1352328158274_699982_20202 == null) myDeciderID_decider_17_0_2_edc0357_1352328158274_699982_20202 = new IntegerParameter("myDeciderID_decider_17_0_2_edc0357_1352328158274_699982_20202", (Integer) 2, this);
                    if (_17_0_2_edc0357_1352328158274_699982_20202_exists == null) _17_0_2_edc0357_1352328158274_699982_20202_exists = new BooleanParameter("_17_0_2_edc0357_1352328158274_699982_20202_exists", (Boolean) false, this);
                    if (myDeciderID_decider_17_0_2_edc0357_1352328158273_110644_20200 == null) myDeciderID_decider_17_0_2_edc0357_1352328158273_110644_20200 = new IntegerParameter("myDeciderID_decider_17_0_2_edc0357_1352328158273_110644_20200", (Integer) 2, this);
                    if (_17_0_2_edc0357_1352328158273_329618_20199_exists == null) _17_0_2_edc0357_1352328158273_329618_20199_exists = new BooleanParameter("_17_0_2_edc0357_1352328158273_329618_20199_exists", (Boolean) false, this);
                    if (_17_0_2_edc0357_1352328158273_110644_20200_exists == null) _17_0_2_edc0357_1352328158273_110644_20200_exists = new BooleanParameter("_17_0_2_edc0357_1352328158273_110644_20200_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new Parameter<Customer>("objectToPass", null, (Customer) null, this);
                    Object effect45VarV = sig_17_0_2_edc0357_1352328158276_305344_20207;
                    effect45Var = new Parameter("effect45Var", null, null, this);
                    addDependency(effect45Var, new Expression(effect45VarV));
                    effect45 = new EffectFunction(new EffectFunction(effect45Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect46VarV = sig_17_0_2_edc0357_1352328158277_91616_20208;
                    effect46Var = new Parameter("effect46Var", null, null, this);
                    addDependency(effect46Var, new Expression(effect46VarV));
                    effect46 = new EffectFunction(new EffectFunction(effect46Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect47VarV = sig_17_0_2_edc0357_1352328158277_34448_20209;
                    effect47Var = new Parameter("effect47Var", null, null, this);
                    addDependency(effect47Var, new Expression(effect47VarV));
                    effect47 = new EffectFunction(new EffectFunction(effect47Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect48VarV = decider_17_0_2_edc0357_1352328158273_110644_20200;
                    effect48Var = new Parameter("effect48Var", null, null, this);
                    addDependency(effect48Var, new Expression(effect48VarV));
                    effect48 = new EffectFunction(new EffectFunction(effect48Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, myDeciderID_decider_17_0_2_edc0357_1352328158273_110644_20200 }));
                    Object effect49VarV = decider_17_0_2_edc0357_1352328158274_699982_20202;
                    effect49Var = new Parameter("effect49Var", null, null, this);
                    addDependency(effect49Var, new Expression(effect49VarV));
                    effect49 = new EffectFunction(new EffectFunction(effect49Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, myDeciderID_decider_17_0_2_edc0357_1352328158274_699982_20202 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158274_877259_20201Collections() {
                parameters.add(myDeciderID_decider_17_0_2_edc0357_1352328158274_699982_20202);
                parameters.add(_17_0_2_edc0357_1352328158274_699982_20202_exists);
                parameters.add(myDeciderID_decider_17_0_2_edc0357_1352328158273_110644_20200);
                parameters.add(_17_0_2_edc0357_1352328158273_329618_20199_exists);
                parameters.add(_17_0_2_edc0357_1352328158273_110644_20200_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect45Var = new TreeSet<Effect>();
                effectsForeffect45Var.add(effect45);
                addEffects((Parameter<?>) effect45Var, effectsForeffect45Var);
                Set<Effect> effectsForeffect46Var = new TreeSet<Effect>();
                effectsForeffect46Var.add(effect46);
                addEffects((Parameter<?>) effect46Var, effectsForeffect46Var);
                Set<Effect> effectsForeffect47Var = new TreeSet<Effect>();
                effectsForeffect47Var.add(effect47);
                addEffects((Parameter<?>) effect47Var, effectsForeffect47Var);
                Set<Effect> effectsForeffect48Var = new TreeSet<Effect>();
                effectsForeffect48Var.add(effect48);
                addEffects((Parameter<?>) effect48Var, effectsForeffect48Var);
                Set<Effect> effectsForeffect49Var = new TreeSet<Effect>();
                effectsForeffect49Var.add(effect49);
                addEffects((Parameter<?>) effect49Var, effectsForeffect49Var);
            }

            public void init_17_0_2_edc0357_1352328158274_877259_20201Dependencies() {
                addDependency(myDeciderID_decider_17_0_2_edc0357_1352328158274_699982_20202, new Expression<Integer>(2));
                addDependency(_17_0_2_edc0357_1352328158274_699982_20202_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158274_699982_20202, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158274_699982_20202, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158274_699982_20202, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_edc0357_1352328158274_699982_20202)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_edc0357_1352328158273_555088_20198_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_edc0357_1352328158273_555088_20198_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))))));
                addDependency(myDeciderID_decider_17_0_2_edc0357_1352328158273_110644_20200, new Expression<Integer>(2));
                addDependency(_17_0_2_edc0357_1352328158273_329618_20199_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_edc0357_1352328158273_555088_20198_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_edc0357_1352328158273_555088_20198_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(_17_0_2_edc0357_1352328158273_110644_20200_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158273_110644_20200, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158273_110644_20200, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158273_110644_20200, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_edc0357_1352328158273_110644_20200)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_edc0357_1352328158273_555088_20198_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_edc0357_1352328158273_555088_20198_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))))));
                addDependency(objectToPass, new Expression<Customer>(new EffectFunction(sig_17_0_2_edc0357_1352328158276_279334_20206, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_edc0357_1352328158274_877259_20201Elaborations() {
                init_17_0_2_edc0357_1352328158274_877259_20201Dependencies();
                Expression<?>[] arguments50 = new Expression<?>[1];
                arguments50[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition50 = new Expression<Boolean>(_17_0_2_edc0357_1352328158273_329618_20199_exists);
                elaborationRule50 = addElaborationRule(condition50, _17_0_2_edc0357_1352328156638_770509_19615.this, Customer._17_0_2_edc0357_1352328156638_770509_19615._17_0_2_edc0357_1352328158273_329618_20199.class, "CUse_readusageparam_ReadStructuralFeatureAction_usePower", arguments50);
                Expression<?>[] arguments51 = new Expression<?>[1];
                arguments51[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition51 = new Expression<Boolean>(_17_0_2_edc0357_1352328158273_110644_20200_exists);
                elaborationRule51 = addElaborationRule(condition51, _17_0_2_edc0357_1352328156638_770509_19615.this, Customer._17_0_2_edc0357_1352328156638_770509_19615._17_0_2_edc0357_1352328158273_110644_20200.class, "CUse_sendload_SendSignalAction_usePower", arguments51);
                Expression<?>[] arguments52 = new Expression<?>[1];
                arguments52[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition52 = new Expression<Boolean>(_17_0_2_edc0357_1352328158274_699982_20202_exists);
                elaborationRule52 = addElaborationRule(condition52, _17_0_2_edc0357_1352328156638_770509_19615.this, Customer._17_0_2_edc0357_1352328156638_770509_19615._17_0_2_edc0357_1352328158274_699982_20202.class, "CUse_sendmeter_SendSignalAction_usePower", arguments52);
            }
        }

        public class _17_0_2_edc0357_1352328158274_699982_20202 extends DurativeEvent {

            public _17_0_2_edc0357_1352328158274_699982_20202() {
                super();
                init_17_0_2_edc0357_1352328158274_699982_20202Members();
                init_17_0_2_edc0357_1352328158274_699982_20202Collections();
                init_17_0_2_edc0357_1352328158274_699982_20202Elaborations();
            }

            public _17_0_2_edc0357_1352328158274_699982_20202(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328158274_699982_20202Members();
                init_17_0_2_edc0357_1352328158274_699982_20202Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328158274_699982_20202Elaborations();
                fixTimeDependencies();
            }

            public Parameter< Customer > _17_0_2_edc0357_1352328160537_353128_21389 = null;

            public BooleanParameter _17_0_2_edc0357_1352328158275_65316_20204_exists = null;

            public IntegerParameter _17_0_2_edc0357_1352328160537_779478_21390 = null;

            public IntegerParameter myDeciderID_decider_17_0_2_edc0357_1352328158275_65316_20204 = null;

            public BooleanParameter objectToPass = null;

            public Parameter< Power_System.SignalreceiveMeterReading > signalObject = null;

            public Dependency< Customer > _17_0_2_edc0357_1352328160537_353128_21389Dependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158275_65316_20204_existsDependency = null;

            public Dependency< Integer > _17_0_2_edc0357_1352328160537_779478_21390Dependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Integer > myDeciderID_decider_17_0_2_edc0357_1352328158275_65316_20204Dependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Dependency< Power_System.SignalreceiveMeterReading > signalObjectDependency = null;

            public Effect effect53 = null;

            public Parameter effect53Var = null;

            public Effect effect54 = null;

            public Parameter effect54Var = null;

            public Effect effect55 = null;

            public Parameter effect55Var = null;

            public Effect effect56 = null;

            public Parameter effect56Var = null;

            public ElaborationRule elaborationRule57 = null;

            public void init_17_0_2_edc0357_1352328158274_699982_20202Members() {
                try {
                    if (_17_0_2_edc0357_1352328160537_353128_21389 == null) _17_0_2_edc0357_1352328160537_353128_21389 = new Parameter<Customer>("_17_0_2_edc0357_1352328160537_353128_21389", null, (Customer) null, this);
                    if (_17_0_2_edc0357_1352328158275_65316_20204_exists == null) _17_0_2_edc0357_1352328158275_65316_20204_exists = new BooleanParameter("_17_0_2_edc0357_1352328158275_65316_20204_exists", (Boolean) false, this);
                    if (_17_0_2_edc0357_1352328160537_779478_21390 == null) _17_0_2_edc0357_1352328160537_779478_21390 = new IntegerParameter("_17_0_2_edc0357_1352328160537_779478_21390", (Integer) null, this);
                    if (myDeciderID_decider_17_0_2_edc0357_1352328158275_65316_20204 == null) myDeciderID_decider_17_0_2_edc0357_1352328158275_65316_20204 = new IntegerParameter("myDeciderID_decider_17_0_2_edc0357_1352328158275_65316_20204", (Integer) 2, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (signalObject == null) signalObject = new Parameter<Power_System.SignalreceiveMeterReading>("signalObject", null, (Power_System.SignalreceiveMeterReading) null, this);
                    Object effect53VarV = sig_17_0_2_edc0357_1352328158277_963172_20215;
                    effect53Var = new Parameter("effect53Var", null, null, this);
                    addDependency(effect53Var, new Expression(effect53VarV));
                    effect53 = new EffectFunction(new EffectFunction(effect53Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect54VarV = decider_17_0_2_edc0357_1352328158275_65316_20204;
                    effect54Var = new Parameter("effect54Var", null, null, this);
                    addDependency(effect54Var, new Expression(effect54VarV));
                    effect54 = new EffectFunction(new EffectFunction(effect54Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, myDeciderID_decider_17_0_2_edc0357_1352328158275_65316_20204 }));
                    Object effect55VarV = new FunctionCall(x, Parameter.class, "getMember", new Object[] { "ss_17_0_2_edc0357_1352328156658_447229_19624_receiveMeterReading" });
                    effect55Var = new Parameter("effect55Var", null, null, this);
                    addDependency(effect55Var, new Expression(effect55VarV));
                    effect55 = new EffectFunction(new EffectFunction(null, ClassUtils.getMethodForArgTypes("ObjectFlow<SignalreceiveMeterReading>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { signalObject, endTime }, effect55Var));
                    Object effect56VarV = new FunctionCall(signalObject, Parameter.class, "getMember", new Object[] { "meter_value__17_0_2_edc0357_1352328156886_203596_19704" });
                    effect56Var = new Parameter("effect56Var", null, null, this);
                    addDependency(effect56Var, new Expression(effect56VarV));
                    effect56 = new EffectFunction(new EffectFunction(null, ClassUtils.getMethodForArgTypes("TimeVaryingMap<Integer>", "powersystem", "setValue", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, _17_0_2_edc0357_1352328160537_779478_21390 }, effect56Var));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158274_699982_20202Collections() {
                parameters.add(_17_0_2_edc0357_1352328160537_353128_21389);
                parameters.add(_17_0_2_edc0357_1352328158275_65316_20204_exists);
                parameters.add(_17_0_2_edc0357_1352328160537_779478_21390);
                parameters.add(myDeciderID_decider_17_0_2_edc0357_1352328158275_65316_20204);
                parameters.add(objectToPass);
                parameters.add(signalObject);
                Set<Effect> effectsForeffect53Var = new TreeSet<Effect>();
                effectsForeffect53Var.add(effect53);
                addEffects((Parameter<?>) effect53Var, effectsForeffect53Var);
                Set<Effect> effectsForeffect54Var = new TreeSet<Effect>();
                effectsForeffect54Var.add(effect54);
                addEffects((Parameter<?>) effect54Var, effectsForeffect54Var);
                Set<Effect> effectsForeffect55Var = new TreeSet<Effect>();
                effectsForeffect55Var.add(effect55);
                addEffects((Parameter<?>) effect55Var, effectsForeffect55Var);
                Set<Effect> effectsForeffect56Var = new TreeSet<Effect>();
                effectsForeffect56Var.add(effect56);
                addEffects((Parameter<?>) effect56Var, effectsForeffect56Var);
            }

            public void init_17_0_2_edc0357_1352328158274_699982_20202Dependencies() {
                addDependency(_17_0_2_edc0357_1352328160537_353128_21389, new Expression<Customer>(new EffectFunction(sig_17_0_2_edc0357_1352328158277_34448_20209, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_edc0357_1352328158275_65316_20204_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158275_65316_20204, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158275_65316_20204, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158275_65316_20204, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_edc0357_1352328158275_65316_20204)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_edc0357_1352328158273_555088_20198_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_edc0357_1352328158273_555088_20198_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))))));
                addDependency(_17_0_2_edc0357_1352328160537_779478_21390, new Expression<Integer>(new EffectFunction(sig_17_0_2_edc0357_1352328158277_912374_20212, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(myDeciderID_decider_17_0_2_edc0357_1352328158275_65316_20204, new Expression<Integer>(2));
                addDependency(objectToPass, new Expression<Boolean>(true));
                addDependency(signalObject, new Expression<Power_System.SignalreceiveMeterReading>(new ConstructorCall(x, ClassUtils.getConstructorForArgTypes(Power_System.SignalreceiveMeterReading.class), new Object[] {})));
            }

            public void init_17_0_2_edc0357_1352328158274_699982_20202Elaborations() {
                init_17_0_2_edc0357_1352328158274_699982_20202Dependencies();
                Expression<?>[] arguments57 = new Expression<?>[1];
                arguments57[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition57 = new Expression<Boolean>(_17_0_2_edc0357_1352328158275_65316_20204_exists);
                elaborationRule57 = addElaborationRule(condition57, _17_0_2_edc0357_1352328156638_770509_19615.this, Customer._17_0_2_edc0357_1352328156638_770509_19615._17_0_2_edc0357_1352328158275_65316_20204.class, "CUse_join_JoinNode_usePower", arguments57);
            }
        }

        public class _17_0_2_edc0357_1352328158275_94410_20203 extends DurativeEvent {

            public _17_0_2_edc0357_1352328158275_94410_20203() {
                super();
                init_17_0_2_edc0357_1352328158275_94410_20203Members();
                init_17_0_2_edc0357_1352328158275_94410_20203Collections();
                init_17_0_2_edc0357_1352328158275_94410_20203Elaborations();
            }

            public _17_0_2_edc0357_1352328158275_94410_20203(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328158275_94410_20203Members();
                init_17_0_2_edc0357_1352328158275_94410_20203Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328158275_94410_20203Elaborations();
                fixTimeDependencies();
            }

            public IntegerParameter myDeciderID_decider_17_0_2_edc0357_1352328158274_699982_20202 = null;

            public BooleanParameter _17_0_2_edc0357_1352328158274_699982_20202_exists = null;

            public IntegerParameter myDeciderID_decider_17_0_2_edc0357_1352328158273_110644_20200 = null;

            public BooleanParameter _17_0_2_edc0357_1352328158273_110644_20200_exists = null;

            public IntegerParameter objectToPass = null;

            public Dependency< Integer > myDeciderID_decider_17_0_2_edc0357_1352328158274_699982_20202Dependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158274_699982_20202_existsDependency = null;

            public Dependency< Integer > myDeciderID_decider_17_0_2_edc0357_1352328158273_110644_20200Dependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158273_110644_20200_existsDependency = null;

            public Dependency< Integer > objectToPassDependency = null;

            public Effect effect58 = null;

            public Parameter effect58Var = null;

            public Effect effect59 = null;

            public Parameter effect59Var = null;

            public Effect effect60 = null;

            public Parameter effect60Var = null;

            public Effect effect61 = null;

            public Parameter effect61Var = null;

            public ElaborationRule elaborationRule62 = null;

            public ElaborationRule elaborationRule63 = null;

            public void init_17_0_2_edc0357_1352328158275_94410_20203Members() {
                try {
                    if (myDeciderID_decider_17_0_2_edc0357_1352328158274_699982_20202 == null) myDeciderID_decider_17_0_2_edc0357_1352328158274_699982_20202 = new IntegerParameter("myDeciderID_decider_17_0_2_edc0357_1352328158274_699982_20202", (Integer) 1, this);
                    if (_17_0_2_edc0357_1352328158274_699982_20202_exists == null) _17_0_2_edc0357_1352328158274_699982_20202_exists = new BooleanParameter("_17_0_2_edc0357_1352328158274_699982_20202_exists", (Boolean) false, this);
                    if (myDeciderID_decider_17_0_2_edc0357_1352328158273_110644_20200 == null) myDeciderID_decider_17_0_2_edc0357_1352328158273_110644_20200 = new IntegerParameter("myDeciderID_decider_17_0_2_edc0357_1352328158273_110644_20200", (Integer) 1, this);
                    if (_17_0_2_edc0357_1352328158273_110644_20200_exists == null) _17_0_2_edc0357_1352328158273_110644_20200_exists = new BooleanParameter("_17_0_2_edc0357_1352328158273_110644_20200_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new IntegerParameter("objectToPass", (Integer) null, this);
                    Object effect58VarV = sig_17_0_2_edc0357_1352328158277_962194_20211;
                    effect58Var = new Parameter("effect58Var", null, null, this);
                    addDependency(effect58Var, new Expression(effect58VarV));
                    effect58 = new EffectFunction(new EffectFunction(effect58Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect59VarV = sig_17_0_2_edc0357_1352328158277_912374_20212;
                    effect59Var = new Parameter("effect59Var", null, null, this);
                    addDependency(effect59Var, new Expression(effect59VarV));
                    effect59 = new EffectFunction(new EffectFunction(effect59Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect60VarV = decider_17_0_2_edc0357_1352328158273_110644_20200;
                    effect60Var = new Parameter("effect60Var", null, null, this);
                    addDependency(effect60Var, new Expression(effect60VarV));
                    effect60 = new EffectFunction(new EffectFunction(effect60Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, myDeciderID_decider_17_0_2_edc0357_1352328158273_110644_20200 }));
                    Object effect61VarV = decider_17_0_2_edc0357_1352328158274_699982_20202;
                    effect61Var = new Parameter("effect61Var", null, null, this);
                    addDependency(effect61Var, new Expression(effect61VarV));
                    effect61 = new EffectFunction(new EffectFunction(effect61Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, myDeciderID_decider_17_0_2_edc0357_1352328158274_699982_20202 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158275_94410_20203Collections() {
                parameters.add(myDeciderID_decider_17_0_2_edc0357_1352328158274_699982_20202);
                parameters.add(_17_0_2_edc0357_1352328158274_699982_20202_exists);
                parameters.add(myDeciderID_decider_17_0_2_edc0357_1352328158273_110644_20200);
                parameters.add(_17_0_2_edc0357_1352328158273_110644_20200_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect58Var = new TreeSet<Effect>();
                effectsForeffect58Var.add(effect58);
                addEffects((Parameter<?>) effect58Var, effectsForeffect58Var);
                Set<Effect> effectsForeffect59Var = new TreeSet<Effect>();
                effectsForeffect59Var.add(effect59);
                addEffects((Parameter<?>) effect59Var, effectsForeffect59Var);
                Set<Effect> effectsForeffect60Var = new TreeSet<Effect>();
                effectsForeffect60Var.add(effect60);
                addEffects((Parameter<?>) effect60Var, effectsForeffect60Var);
                Set<Effect> effectsForeffect61Var = new TreeSet<Effect>();
                effectsForeffect61Var.add(effect61);
                addEffects((Parameter<?>) effect61Var, effectsForeffect61Var);
            }

            public void init_17_0_2_edc0357_1352328158275_94410_20203Dependencies() {
                addDependency(myDeciderID_decider_17_0_2_edc0357_1352328158274_699982_20202, new Expression<Integer>(1));
                addDependency(_17_0_2_edc0357_1352328158274_699982_20202_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158274_699982_20202, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158274_699982_20202, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158274_699982_20202, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_edc0357_1352328158274_699982_20202)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_edc0357_1352328158273_555088_20198_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_edc0357_1352328158273_555088_20198_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))))));
                addDependency(myDeciderID_decider_17_0_2_edc0357_1352328158273_110644_20200, new Expression<Integer>(1));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(_17_0_2_edc0357_1352328158273_110644_20200_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158273_110644_20200, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158273_110644_20200, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158273_110644_20200, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_edc0357_1352328158273_110644_20200)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_edc0357_1352328158273_555088_20198_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_edc0357_1352328158273_555088_20198_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))))));
                addDependency(objectToPass, new Expression<Integer>(new EffectFunction(sig_17_0_2_edc0357_1352328158277_819385_20210, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_edc0357_1352328158275_94410_20203Elaborations() {
                init_17_0_2_edc0357_1352328158275_94410_20203Dependencies();
                Expression<?>[] arguments62 = new Expression<?>[1];
                arguments62[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition62 = new Expression<Boolean>(_17_0_2_edc0357_1352328158273_110644_20200_exists);
                elaborationRule62 = addElaborationRule(condition62, _17_0_2_edc0357_1352328156638_770509_19615.this, Customer._17_0_2_edc0357_1352328156638_770509_19615._17_0_2_edc0357_1352328158273_110644_20200.class, "CUse_sendload_SendSignalAction_usePower", arguments62);
                Expression<?>[] arguments63 = new Expression<?>[1];
                arguments63[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition63 = new Expression<Boolean>(_17_0_2_edc0357_1352328158274_699982_20202_exists);
                elaborationRule63 = addElaborationRule(condition63, _17_0_2_edc0357_1352328156638_770509_19615.this, Customer._17_0_2_edc0357_1352328156638_770509_19615._17_0_2_edc0357_1352328158274_699982_20202.class, "CUse_sendmeter_SendSignalAction_usePower", arguments63);
            }
        }

        public class _17_0_2_edc0357_1352328158275_65316_20204 extends DurativeEvent {

            public _17_0_2_edc0357_1352328158275_65316_20204() {
                super();
                init_17_0_2_edc0357_1352328158275_65316_20204Members();
                init_17_0_2_edc0357_1352328158275_65316_20204Collections();
                init_17_0_2_edc0357_1352328158275_65316_20204Elaborations();
            }

            public _17_0_2_edc0357_1352328158275_65316_20204(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328158275_65316_20204Members();
                init_17_0_2_edc0357_1352328158275_65316_20204Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328158275_65316_20204Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter objectToPass1 = null;

            public BooleanParameter objectToPass = null;

            public Dependency< Boolean > objectToPass1Dependency = null;

            public Dependency< Integer > finalNode_startTimeDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Effect effect64 = null;

            public Parameter effect64Var = null;

            public void init_17_0_2_edc0357_1352328158275_65316_20204Members() {
                try {
                    if (objectToPass1 == null) objectToPass1 = new BooleanParameter("objectToPass1", (Boolean) null, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect64VarV = sig_17_0_2_edc0357_1352328158277_748946_20214;
                    effect64Var = new Parameter("effect64Var", null, null, this);
                    addDependency(effect64Var, new Expression(effect64VarV));
                    effect64 = new EffectFunction(new EffectFunction(effect64Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158275_65316_20204Collections() {
                parameters.add(objectToPass1);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect64Var = new TreeSet<Effect>();
                effectsForeffect64Var.add(effect64);
                addEffects((Parameter<?>) effect64Var, effectsForeffect64Var);
            }

            public void init_17_0_2_edc0357_1352328158275_65316_20204Dependencies() {
                addDependency(objectToPass1, new Expression<Boolean>(new EffectFunction(sig_17_0_2_edc0357_1352328158277_963172_20215, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(finalNode_startTime, new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new EffectFunction(sig_17_0_2_edc0357_1352328158277_686827_20213, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_edc0357_1352328158275_65316_20204Elaborations() {
                init_17_0_2_edc0357_1352328158275_65316_20204Dependencies();
            }
        }

        public _17_0_2_edc0357_1352328156638_770509_19615(Expression<Integer> startTime, Expression<DurativeEvent> caller) {
            super();
            init_17_0_2_edc0357_1352328156638_770509_19615Members();
            init_17_0_2_edc0357_1352328156638_770509_19615Collections();
            addDependency(this.startTime, startTime);
            addDependency(this.caller, caller);
            init_17_0_2_edc0357_1352328156638_770509_19615Elaborations();
            fixTimeDependencies();
        }

        public Parameter< TimeVaryingList<Integer> > decider_17_0_2_edc0357_1352328158274_699982_20202 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_edc0357_1352328158277_686827_20213 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_edc0357_1352328158277_963172_20215 = null;

        public Parameter< DurativeEvent > caller = null;

        public Parameter< ObjectFlow<Customer> > sig_17_0_2_edc0357_1352328158277_34448_20209 = null;

        public Parameter< ObjectFlow<Customer> > sig_17_0_2_edc0357_1352328158276_305344_20207 = null;

        public BooleanParameter _17_0_2_edc0357_1352328158273_555088_20198_exists = null;

        public Parameter< TimeVaryingList<Integer> > decider_17_0_2_edc0357_1352328158273_110644_20200 = null;

        public IntegerParameter finalNode_startTime = null;

        public Parameter< TimeVaryingList<Integer> > decider_17_0_2_edc0357_1352328158275_65316_20204 = null;

        public IntegerParameter finalNode_endTime = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_edc0357_1352328158277_748946_20214 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_2_edc0357_1352328158277_912374_20212 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_edc0357_1352328158276_873292_20205 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_2_edc0357_1352328158277_819385_20210 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_2_edc0357_1352328158277_962194_20211 = null;

        public Parameter< ObjectFlow<Customer> > sig_17_0_2_edc0357_1352328158277_91616_20208 = null;

        public Parameter< ObjectFlow<Customer> > sig_17_0_2_edc0357_1352328158276_279334_20206 = null;

        public Dependency< Integer > caller_endTimeDependency = null;

        public Dependency< Boolean > _17_0_2_edc0357_1352328158273_555088_20198_existsDependency = null;

        public Dependency< Integer > endTimeDependency = null;

        public ElaborationRule elaborationRule32 = null;

        public ElaborationRule elaborationRule33 = null;

        public void init_17_0_2_edc0357_1352328156638_770509_19615Members() {
            try {
                if (decider_17_0_2_edc0357_1352328158274_699982_20202 == null) decider_17_0_2_edc0357_1352328158274_699982_20202 = new Parameter<TimeVaryingList<Integer>>("decider_17_0_2_edc0357_1352328158274_699982_20202", null, (TimeVaryingList<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingList.class, java.lang.String.class, int.class), new Object[] { "decider_17_0_2_edc0357_1352328158274_699982_20202", 2 })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328158277_686827_20213 == null) sig_17_0_2_edc0357_1352328158277_686827_20213 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328158277_686827_20213", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158277_686827_20213" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328158277_963172_20215 == null) sig_17_0_2_edc0357_1352328158277_963172_20215 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328158277_963172_20215", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158277_963172_20215" })).evaluate(true), this);
                if (caller == null) caller = new Parameter<DurativeEvent>("caller", null, (DurativeEvent) null, this);
                if (sig_17_0_2_edc0357_1352328158277_34448_20209 == null) sig_17_0_2_edc0357_1352328158277_34448_20209 = new Parameter<ObjectFlow<Customer>>("sig_17_0_2_edc0357_1352328158277_34448_20209", null, (ObjectFlow<Customer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158277_34448_20209" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328158276_305344_20207 == null) sig_17_0_2_edc0357_1352328158276_305344_20207 = new Parameter<ObjectFlow<Customer>>("sig_17_0_2_edc0357_1352328158276_305344_20207", null, (ObjectFlow<Customer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158276_305344_20207" })).evaluate(true), this);
                if (_17_0_2_edc0357_1352328158273_555088_20198_exists == null) _17_0_2_edc0357_1352328158273_555088_20198_exists = new BooleanParameter("_17_0_2_edc0357_1352328158273_555088_20198_exists", (Boolean) false, this);
                if (decider_17_0_2_edc0357_1352328158273_110644_20200 == null) decider_17_0_2_edc0357_1352328158273_110644_20200 = new Parameter<TimeVaryingList<Integer>>("decider_17_0_2_edc0357_1352328158273_110644_20200", null, (TimeVaryingList<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingList.class, java.lang.String.class, int.class), new Object[] { "decider_17_0_2_edc0357_1352328158273_110644_20200", 2 })).evaluate(true), this);
                if (finalNode_startTime == null) finalNode_startTime = new IntegerParameter("finalNode_startTime", (Integer) null, this);
                if (decider_17_0_2_edc0357_1352328158275_65316_20204 == null) decider_17_0_2_edc0357_1352328158275_65316_20204 = new Parameter<TimeVaryingList<Integer>>("decider_17_0_2_edc0357_1352328158275_65316_20204", null, (TimeVaryingList<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingList.class, java.lang.String.class, int.class), new Object[] { "decider_17_0_2_edc0357_1352328158275_65316_20204", 2 })).evaluate(true), this);
                if (finalNode_endTime == null) finalNode_endTime = new IntegerParameter("finalNode_endTime", (Integer) null, this);
                if (sig_17_0_2_edc0357_1352328158277_748946_20214 == null) sig_17_0_2_edc0357_1352328158277_748946_20214 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328158277_748946_20214", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158277_748946_20214" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328158277_912374_20212 == null) sig_17_0_2_edc0357_1352328158277_912374_20212 = new Parameter<ObjectFlow<Integer>>("sig_17_0_2_edc0357_1352328158277_912374_20212", null, (ObjectFlow<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158277_912374_20212" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328158276_873292_20205 == null) sig_17_0_2_edc0357_1352328158276_873292_20205 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328158276_873292_20205", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158276_873292_20205" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328158277_819385_20210 == null) sig_17_0_2_edc0357_1352328158277_819385_20210 = new Parameter<ObjectFlow<Integer>>("sig_17_0_2_edc0357_1352328158277_819385_20210", null, (ObjectFlow<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158277_819385_20210" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328158277_962194_20211 == null) sig_17_0_2_edc0357_1352328158277_962194_20211 = new Parameter<ObjectFlow<Integer>>("sig_17_0_2_edc0357_1352328158277_962194_20211", null, (ObjectFlow<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158277_962194_20211" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328158277_91616_20208 == null) sig_17_0_2_edc0357_1352328158277_91616_20208 = new Parameter<ObjectFlow<Customer>>("sig_17_0_2_edc0357_1352328158277_91616_20208", null, (ObjectFlow<Customer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158277_91616_20208" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328158276_279334_20206 == null) sig_17_0_2_edc0357_1352328158276_279334_20206 = new Parameter<ObjectFlow<Customer>>("sig_17_0_2_edc0357_1352328158276_279334_20206", null, (ObjectFlow<Customer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158276_279334_20206" })).evaluate(true), this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void init_17_0_2_edc0357_1352328156638_770509_19615Collections() {
            parameters.add(decider_17_0_2_edc0357_1352328158274_699982_20202);
            parameters.add(sig_17_0_2_edc0357_1352328158277_686827_20213);
            parameters.add(sig_17_0_2_edc0357_1352328158277_963172_20215);
            parameters.add(caller);
            parameters.add(sig_17_0_2_edc0357_1352328158277_34448_20209);
            parameters.add(sig_17_0_2_edc0357_1352328158276_305344_20207);
            parameters.add(_17_0_2_edc0357_1352328158273_555088_20198_exists);
            parameters.add(decider_17_0_2_edc0357_1352328158273_110644_20200);
            parameters.add(finalNode_startTime);
            parameters.add(decider_17_0_2_edc0357_1352328158275_65316_20204);
            parameters.add(finalNode_endTime);
            parameters.add(sig_17_0_2_edc0357_1352328158277_748946_20214);
            parameters.add(sig_17_0_2_edc0357_1352328158277_912374_20212);
            parameters.add(sig_17_0_2_edc0357_1352328158276_873292_20205);
            parameters.add(sig_17_0_2_edc0357_1352328158277_819385_20210);
            parameters.add(sig_17_0_2_edc0357_1352328158277_962194_20211);
            parameters.add(sig_17_0_2_edc0357_1352328158277_91616_20208);
            parameters.add(sig_17_0_2_edc0357_1352328158276_279334_20206);
        }

        public void init_17_0_2_edc0357_1352328156638_770509_19615Dependencies() {
            caller.getValue(true).addDependency(caller.getValue(true).endTime, new Expression<Integer>(finalNode_endTime));
            addDependency(_17_0_2_edc0357_1352328158273_555088_20198_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.NotEquals(new Expression<Integer>(finalNode_startTime), null)), new Expression(new EffectFunction(sig_17_0_2_edc0357_1352328158277_748946_20214, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(new Functions.Minus(new Expression<Integer>(finalNode_startTime), new Expression<Integer>(1))) })))));
            addDependency(endTime, new Expression<Integer>(finalNode_endTime));
        }

        public void init_17_0_2_edc0357_1352328156638_770509_19615Elaborations() {
            init_17_0_2_edc0357_1352328156638_770509_19615Dependencies();
            Expression<?>[] arguments32 = new Expression<?>[1];
            arguments32[0] = new Expression<Integer>(finalNode_startTime);
            Expression<Boolean> condition32 = new Expression<Boolean>(_17_0_2_edc0357_1352328158273_555088_20198_exists);
            elaborationRule32 = addElaborationRule(condition32, _17_0_2_edc0357_1352328156638_770509_19615.this, Customer._17_0_2_edc0357_1352328156638_770509_19615._17_0_2_edc0357_1352328158273_555088_20198.class, "CUse_end_ActivityFinalNode_usePower", arguments32);
            Expression<?>[] arguments33 = new Expression<?>[1];
            arguments33[0] = new Expression<Integer>(startTime);
            Expression<Boolean> condition33 = new Expression<Boolean>(true);
            elaborationRule33 = addElaborationRule(condition33, _17_0_2_edc0357_1352328156638_770509_19615.this, Customer._17_0_2_edc0357_1352328156638_770509_19615._17_0_2_edc0357_1352328158272_257857_20196.class, "CUse_start_InitialNode_usePower", arguments33);
        }
    }

    public class _17_0_2_edc0357_1352328156639_660827_19616 extends DurativeEvent {

        public _17_0_2_edc0357_1352328156639_660827_19616() {
            super();
            init_17_0_2_edc0357_1352328156639_660827_19616Members();
            init_17_0_2_edc0357_1352328156639_660827_19616Collections();
            init_17_0_2_edc0357_1352328156639_660827_19616Elaborations();
        }

        public class _17_0_2_edc0357_1352328158425_536558_20249 extends DurativeEvent {

            public _17_0_2_edc0357_1352328158425_536558_20249() {
                super();
                init_17_0_2_edc0357_1352328158425_536558_20249Members();
                init_17_0_2_edc0357_1352328158425_536558_20249Collections();
                init_17_0_2_edc0357_1352328158425_536558_20249Elaborations();
            }

            public _17_0_2_edc0357_1352328158425_536558_20249(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328158425_536558_20249Members();
                init_17_0_2_edc0357_1352328158425_536558_20249Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328158425_536558_20249Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_edc0357_1352328158426_178002_20255_exists = null;

            public Parameter< Customer > _17_0_2_edc0357_1352328160547_987385_21408 = null;

            public BooleanParameter objectToPass = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158426_178002_20255_existsDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Effect effect67 = null;

            public Parameter effect67Var = null;

            public ElaborationRule elaborationRule68 = null;

            public void init_17_0_2_edc0357_1352328158425_536558_20249Members() {
                try {
                    if (_17_0_2_edc0357_1352328158426_178002_20255_exists == null) _17_0_2_edc0357_1352328158426_178002_20255_exists = new BooleanParameter("_17_0_2_edc0357_1352328158426_178002_20255_exists", (Boolean) false, this);
                    if (_17_0_2_edc0357_1352328160547_987385_21408 == null) _17_0_2_edc0357_1352328160547_987385_21408 = new Parameter<Customer>("_17_0_2_edc0357_1352328160547_987385_21408", null, (Customer) Customer.this, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect67VarV = sig_17_0_2_edc0357_1352328158429_502495_20265;
                    effect67Var = new Parameter("effect67Var", null, null, this);
                    addDependency(effect67Var, new Expression(effect67VarV));
                    effect67 = new EffectFunction(new EffectFunction(effect67Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { _17_0_2_edc0357_1352328160547_987385_21408, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158425_536558_20249Collections() {
                parameters.add(_17_0_2_edc0357_1352328158426_178002_20255_exists);
                parameters.add(_17_0_2_edc0357_1352328160547_987385_21408);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect67Var = new TreeSet<Effect>();
                effectsForeffect67Var.add(effect67);
                addEffects((Parameter<?>) effect67Var, effectsForeffect67Var);
            }

            public void init_17_0_2_edc0357_1352328158425_536558_20249Dependencies() {
                addDependency(_17_0_2_edc0357_1352328158426_178002_20255_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_edc0357_1352328158425_174981_20253_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_edc0357_1352328158425_174981_20253_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new EffectFunction(sig_17_0_2_edc0357_1352328158430_913443_20274, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_edc0357_1352328158425_536558_20249Elaborations() {
                init_17_0_2_edc0357_1352328158425_536558_20249Dependencies();
                Expression<?>[] arguments68 = new Expression<?>[1];
                arguments68[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition68 = new Expression<Boolean>(_17_0_2_edc0357_1352328158426_178002_20255_exists);
                elaborationRule68 = addElaborationRule(condition68, _17_0_2_edc0357_1352328156639_660827_19616.this, Customer._17_0_2_edc0357_1352328156639_660827_19616._17_0_2_edc0357_1352328158426_178002_20255.class, "CChange_fork2self_ForkNode_changePowerUsage", arguments68);
            }
        }

        public class _17_0_2_edc0357_1352328158425_562603_20250 extends DurativeEvent {

            public _17_0_2_edc0357_1352328158425_562603_20250() {
                super();
                init_17_0_2_edc0357_1352328158425_562603_20250Members();
                init_17_0_2_edc0357_1352328158425_562603_20250Collections();
                init_17_0_2_edc0357_1352328158425_562603_20250Elaborations();
            }

            public _17_0_2_edc0357_1352328158425_562603_20250(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328158425_562603_20250Members();
                init_17_0_2_edc0357_1352328158425_562603_20250Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328158425_562603_20250Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_edc0357_1352328158428_316417_20263_exists = null;

            public BooleanParameter objectToPass = null;

            public IntegerParameter _17_0_2_edc0357_1352328160548_131046_21410 = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158428_316417_20263_existsDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Dependency< Integer > _17_0_2_edc0357_1352328160548_131046_21410Dependency = null;

            public Effect effect69 = null;

            public Parameter effect69Var = null;

            public ElaborationRule elaborationRule70 = null;

            public void init_17_0_2_edc0357_1352328158425_562603_20250Members() {
                try {
                    if (_17_0_2_edc0357_1352328158428_316417_20263_exists == null) _17_0_2_edc0357_1352328158428_316417_20263_exists = new BooleanParameter("_17_0_2_edc0357_1352328158428_316417_20263_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (_17_0_2_edc0357_1352328160548_131046_21410 == null) _17_0_2_edc0357_1352328160548_131046_21410 = new IntegerParameter("_17_0_2_edc0357_1352328160548_131046_21410", (Integer) 15, this);
                    Object effect69VarV = sig_17_0_2_edc0357_1352328158431_275687_20282;
                    effect69Var = new Parameter("effect69Var", null, null, this);
                    addDependency(effect69Var, new Expression(effect69VarV));
                    effect69 = new EffectFunction(new EffectFunction(effect69Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { _17_0_2_edc0357_1352328160548_131046_21410, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158425_562603_20250Collections() {
                parameters.add(_17_0_2_edc0357_1352328158428_316417_20263_exists);
                parameters.add(objectToPass);
                parameters.add(_17_0_2_edc0357_1352328160548_131046_21410);
                Set<Effect> effectsForeffect69Var = new TreeSet<Effect>();
                effectsForeffect69Var.add(effect69);
                addEffects((Parameter<?>) effect69Var, effectsForeffect69Var);
            }

            public void init_17_0_2_edc0357_1352328158425_562603_20250Dependencies() {
                addDependency(_17_0_2_edc0357_1352328158428_316417_20263_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_edc0357_1352328158425_174981_20253_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_edc0357_1352328158425_174981_20253_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new EffectFunction(sig_17_0_2_edc0357_1352328158430_934674_20275, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_edc0357_1352328160548_131046_21410, new Expression<Integer>(15));
            }

            public void init_17_0_2_edc0357_1352328158425_562603_20250Elaborations() {
                init_17_0_2_edc0357_1352328158425_562603_20250Dependencies();
                Expression<?>[] arguments70 = new Expression<?>[1];
                arguments70[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition70 = new Expression<Boolean>(_17_0_2_edc0357_1352328158428_316417_20263_exists);
                elaborationRule70 = addElaborationRule(condition70, _17_0_2_edc0357_1352328156639_660827_19616.this, Customer._17_0_2_edc0357_1352328156639_660827_19616._17_0_2_edc0357_1352328158428_316417_20263.class, "CChange_fork4changeval_ForkNode_changePowerUsage", arguments70);
            }
        }

        public class _17_0_2_edc0357_1352328158425_844513_20251 extends DurativeEvent {

            public _17_0_2_edc0357_1352328158425_844513_20251() {
                super();
                init_17_0_2_edc0357_1352328158425_844513_20251Members();
                init_17_0_2_edc0357_1352328158425_844513_20251Collections();
                init_17_0_2_edc0357_1352328158425_844513_20251Elaborations();
            }

            public _17_0_2_edc0357_1352328158425_844513_20251(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328158425_844513_20251Members();
                init_17_0_2_edc0357_1352328158425_844513_20251Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328158425_844513_20251Elaborations();
                fixTimeDependencies();
            }

            public IntegerParameter _17_0_2_edc0357_1352328160549_391021_21411 = null;

            public BooleanParameter _17_0_2_edc0357_1352328158426_116757_20257_exists = null;

            public BooleanParameter objectToPass = null;

            public IntegerParameter myDeciderID_decider_17_0_2_edc0357_1352328158426_116757_20257 = null;

            public Parameter< Customer > _17_0_2_edc0357_1352328160550_581175_21412 = null;

            public Dependency< Integer > _17_0_2_edc0357_1352328160549_391021_21411Dependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158426_116757_20257_existsDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Dependency< Integer > myDeciderID_decider_17_0_2_edc0357_1352328158426_116757_20257Dependency = null;

            public Dependency< Customer > _17_0_2_edc0357_1352328160550_581175_21412Dependency = null;

            public Effect effect71 = null;

            public Parameter effect71Var = null;

            public Effect effect72 = null;

            public Parameter effect72Var = null;

            public Effect effect73 = null;

            public Parameter effect73Var = null;

            public ElaborationRule elaborationRule74 = null;

            public void init_17_0_2_edc0357_1352328158425_844513_20251Members() {
                try {
                    if (_17_0_2_edc0357_1352328160549_391021_21411 == null) _17_0_2_edc0357_1352328160549_391021_21411 = new IntegerParameter("_17_0_2_edc0357_1352328160549_391021_21411", (Integer) null, this);
                    if (_17_0_2_edc0357_1352328158426_116757_20257_exists == null) _17_0_2_edc0357_1352328158426_116757_20257_exists = new BooleanParameter("_17_0_2_edc0357_1352328158426_116757_20257_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (myDeciderID_decider_17_0_2_edc0357_1352328158426_116757_20257 == null) myDeciderID_decider_17_0_2_edc0357_1352328158426_116757_20257 = new IntegerParameter("myDeciderID_decider_17_0_2_edc0357_1352328158426_116757_20257", (Integer) 3, this);
                    if (_17_0_2_edc0357_1352328160550_581175_21412 == null) _17_0_2_edc0357_1352328160550_581175_21412 = new Parameter<Customer>("_17_0_2_edc0357_1352328160550_581175_21412", null, (Customer) null, this);
                    Object effect71VarV = sig_17_0_2_edc0357_1352328158430_24745_20272;
                    effect71Var = new Parameter("effect71Var", null, null, this);
                    addDependency(effect71Var, new Expression(effect71VarV));
                    effect71 = new EffectFunction(new EffectFunction(effect71Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect72VarV = decider_17_0_2_edc0357_1352328158426_116757_20257;
                    effect72Var = new Parameter("effect72Var", null, null, this);
                    addDependency(effect72Var, new Expression(effect72VarV));
                    effect72 = new EffectFunction(new EffectFunction(effect72Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, myDeciderID_decider_17_0_2_edc0357_1352328158426_116757_20257 }));
                    Object effect73VarV = usage__17_0_2_edc0357_1352328156649_216766_19620;
                    effect73Var = new Parameter("effect73Var", null, null, this);
                    addDependency(effect73Var, new Expression(effect73VarV));
                    effect73 = new EffectFunction(new EffectFunction(effect73Var, ClassUtils.getMethodForArgTypes("TimeVaryingPlottableMap<Integer>", "powersystem", "setValue", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { startTime, _17_0_2_edc0357_1352328160549_391021_21411 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158425_844513_20251Collections() {
                parameters.add(_17_0_2_edc0357_1352328160549_391021_21411);
                parameters.add(_17_0_2_edc0357_1352328158426_116757_20257_exists);
                parameters.add(objectToPass);
                parameters.add(myDeciderID_decider_17_0_2_edc0357_1352328158426_116757_20257);
                parameters.add(_17_0_2_edc0357_1352328160550_581175_21412);
                Set<Effect> effectsForeffect71Var = new TreeSet<Effect>();
                effectsForeffect71Var.add(effect71);
                addEffects((Parameter<?>) effect71Var, effectsForeffect71Var);
                Set<Effect> effectsForeffect72Var = new TreeSet<Effect>();
                effectsForeffect72Var.add(effect72);
                addEffects((Parameter<?>) effect72Var, effectsForeffect72Var);
                Set<Effect> effectsForeffect73Var = new TreeSet<Effect>();
                effectsForeffect73Var.add(effect73);
                addEffects((Parameter<?>) effect73Var, effectsForeffect73Var);
            }

            public void init_17_0_2_edc0357_1352328158425_844513_20251Dependencies() {
                addDependency(_17_0_2_edc0357_1352328160549_391021_21411, new Expression<Integer>(new EffectFunction(sig_17_0_2_edc0357_1352328158429_532173_20270, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_edc0357_1352328158426_116757_20257_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158426_116757_20257, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158426_116757_20257, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158426_116757_20257, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_edc0357_1352328158426_116757_20257)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_edc0357_1352328158425_174981_20253_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_edc0357_1352328158425_174981_20253_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(true));
                addDependency(myDeciderID_decider_17_0_2_edc0357_1352328158426_116757_20257, new Expression<Integer>(3));
                addDependency(_17_0_2_edc0357_1352328160550_581175_21412, new Expression<Customer>(new EffectFunction(sig_17_0_2_edc0357_1352328158429_694477_20266, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_edc0357_1352328158425_844513_20251Elaborations() {
                init_17_0_2_edc0357_1352328158425_844513_20251Dependencies();
                Expression<?>[] arguments74 = new Expression<?>[1];
                arguments74[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition74 = new Expression<Boolean>(_17_0_2_edc0357_1352328158426_116757_20257_exists);
                elaborationRule74 = addElaborationRule(condition74, _17_0_2_edc0357_1352328156639_660827_19616.this, Customer._17_0_2_edc0357_1352328156639_660827_19616._17_0_2_edc0357_1352328158426_116757_20257.class, "CChange_sendnewusage_SendSignalAction_changePowerUsage", arguments74);
            }
        }

        public class _17_0_2_edc0357_1352328158425_23694_20252 extends DurativeEvent {

            public _17_0_2_edc0357_1352328158425_23694_20252() {
                super();
                init_17_0_2_edc0357_1352328158425_23694_20252Members();
                init_17_0_2_edc0357_1352328158425_23694_20252Collections();
                init_17_0_2_edc0357_1352328158425_23694_20252Elaborations();
            }

            public _17_0_2_edc0357_1352328158425_23694_20252(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328158425_23694_20252Members();
                init_17_0_2_edc0357_1352328158425_23694_20252Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328158425_23694_20252Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter objectToPass = null;

            public BooleanParameter _17_0_2_edc0357_1352328158427_71576_20259_exists = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158427_71576_20259_existsDependency = null;

            public Effect effect75 = null;

            public Parameter effect75Var = null;

            public ElaborationRule elaborationRule76 = null;

            public void init_17_0_2_edc0357_1352328158425_23694_20252Members() {
                try {
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (_17_0_2_edc0357_1352328158427_71576_20259_exists == null) _17_0_2_edc0357_1352328158427_71576_20259_exists = new BooleanParameter("_17_0_2_edc0357_1352328158427_71576_20259_exists", (Boolean) false, this);
                    Object effect75VarV = sig_17_0_2_edc0357_1352328158430_229342_20273;
                    effect75Var = new Parameter("effect75Var", null, null, this);
                    addDependency(effect75Var, new Expression(effect75VarV));
                    effect75 = new EffectFunction(new EffectFunction(effect75Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158425_23694_20252Collections() {
                parameters.add(objectToPass);
                parameters.add(_17_0_2_edc0357_1352328158427_71576_20259_exists);
                Set<Effect> effectsForeffect75Var = new TreeSet<Effect>();
                effectsForeffect75Var.add(effect75);
                addEffects((Parameter<?>) effect75Var, effectsForeffect75Var);
            }

            public void init_17_0_2_edc0357_1352328158425_23694_20252Dependencies() {
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(true));
                addDependency(_17_0_2_edc0357_1352328158427_71576_20259_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_edc0357_1352328158425_174981_20253_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_edc0357_1352328158425_174981_20253_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
            }

            public void init_17_0_2_edc0357_1352328158425_23694_20252Elaborations() {
                init_17_0_2_edc0357_1352328158425_23694_20252Dependencies();
                Expression<?>[] arguments76 = new Expression<?>[1];
                arguments76[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition76 = new Expression<Boolean>(_17_0_2_edc0357_1352328158427_71576_20259_exists);
                elaborationRule76 = addElaborationRule(condition76, _17_0_2_edc0357_1352328156639_660827_19616.this, Customer._17_0_2_edc0357_1352328156639_660827_19616._17_0_2_edc0357_1352328158427_71576_20259.class, "CChange_fork1_ForkNode_changePowerUsage", arguments76);
            }
        }

        public class _17_0_2_edc0357_1352328158425_174981_20253 extends DurativeEvent {

            public _17_0_2_edc0357_1352328158425_174981_20253() {
                super();
                init_17_0_2_edc0357_1352328158425_174981_20253Members();
                init_17_0_2_edc0357_1352328158425_174981_20253Collections();
                init_17_0_2_edc0357_1352328158425_174981_20253Elaborations();
            }

            public _17_0_2_edc0357_1352328158425_174981_20253(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328158425_174981_20253Members();
                init_17_0_2_edc0357_1352328158425_174981_20253Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328158425_174981_20253Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter objectToPass = null;

            public Dependency< Integer > endTimeDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Dependency< Integer > finalNode_endTimeDependency = null;

            public void init_17_0_2_edc0357_1352328158425_174981_20253Members() {
                try {
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158425_174981_20253Collections() {
                parameters.add(objectToPass);
            }

            public void init_17_0_2_edc0357_1352328158425_174981_20253Dependencies() {
                addDependency(endTime, new Expression<Integer>(new Functions.Plus(new Expression<Integer>(startTime), new Expression<Integer>(duration))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new EffectFunction(sig_17_0_2_edc0357_1352328158429_418011_20269, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(finalNode_endTime, new Expression<Integer>(endTime));
            }

            public void init_17_0_2_edc0357_1352328158425_174981_20253Elaborations() {
                init_17_0_2_edc0357_1352328158425_174981_20253Dependencies();
            }
        }

        public class _17_0_2_edc0357_1352328158425_220398_20254 extends DurativeEvent {

            public _17_0_2_edc0357_1352328158425_220398_20254() {
                super();
                init_17_0_2_edc0357_1352328158425_220398_20254Members();
                init_17_0_2_edc0357_1352328158425_220398_20254Collections();
                init_17_0_2_edc0357_1352328158425_220398_20254Elaborations();
            }

            public _17_0_2_edc0357_1352328158425_220398_20254(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328158425_220398_20254Members();
                init_17_0_2_edc0357_1352328158425_220398_20254Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328158425_220398_20254Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_edc0357_1352328158428_38684_20262_exists = null;

            public IntegerParameter myDeciderID_decider_17_0_2_edc0357_1352328158428_38684_20262 = null;

            public Parameter< Customer > _17_0_2_edc0357_1352328160551_311379_21414 = null;

            public BooleanParameter objectToPass = null;

            public BooleanParameter _17_0_2_edc0357_1352328158428_668812_20261_exists = null;

            public IntegerParameter _17_0_2_edc0357_1352328160550_744442_21413 = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158428_38684_20262_existsDependency = null;

            public Dependency< Integer > myDeciderID_decider_17_0_2_edc0357_1352328158428_38684_20262Dependency = null;

            public Dependency< Customer > _17_0_2_edc0357_1352328160551_311379_21414Dependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158428_668812_20261_existsDependency = null;

            public Dependency< Integer > _17_0_2_edc0357_1352328160550_744442_21413Dependency = null;

            public Effect effect77 = null;

            public Parameter effect77Var = null;

            public Effect effect78 = null;

            public Parameter effect78Var = null;

            public Effect effect79 = null;

            public Parameter effect79Var = null;

            public ElaborationRule elaborationRule80 = null;

            public ElaborationRule elaborationRule81 = null;

            public void init_17_0_2_edc0357_1352328158425_220398_20254Members() {
                try {
                    if (_17_0_2_edc0357_1352328158428_38684_20262_exists == null) _17_0_2_edc0357_1352328158428_38684_20262_exists = new BooleanParameter("_17_0_2_edc0357_1352328158428_38684_20262_exists", (Boolean) false, this);
                    if (myDeciderID_decider_17_0_2_edc0357_1352328158428_38684_20262 == null) myDeciderID_decider_17_0_2_edc0357_1352328158428_38684_20262 = new IntegerParameter("myDeciderID_decider_17_0_2_edc0357_1352328158428_38684_20262", (Integer) 1, this);
                    if (_17_0_2_edc0357_1352328160551_311379_21414 == null) _17_0_2_edc0357_1352328160551_311379_21414 = new Parameter<Customer>("_17_0_2_edc0357_1352328160551_311379_21414", null, (Customer) null, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (_17_0_2_edc0357_1352328158428_668812_20261_exists == null) _17_0_2_edc0357_1352328158428_668812_20261_exists = new BooleanParameter("_17_0_2_edc0357_1352328158428_668812_20261_exists", (Boolean) false, this);
                    if (_17_0_2_edc0357_1352328160550_744442_21413 == null) _17_0_2_edc0357_1352328160550_744442_21413 = new IntegerParameter("_17_0_2_edc0357_1352328160550_744442_21413", (Integer) null, this);
                    Object effect77VarV = sig_17_0_2_edc0357_1352328158430_538124_20277;
                    effect77Var = new Parameter("effect77Var", null, null, this);
                    addDependency(effect77Var, new Expression(effect77VarV));
                    effect77 = new EffectFunction(new EffectFunction(effect77Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { _17_0_2_edc0357_1352328160550_744442_21413, endTime }));
                    Object effect78VarV = sig_17_0_2_edc0357_1352328158430_630436_20276;
                    effect78Var = new Parameter("effect78Var", null, null, this);
                    addDependency(effect78Var, new Expression(effect78VarV));
                    effect78 = new EffectFunction(new EffectFunction(effect78Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect79VarV = decider_17_0_2_edc0357_1352328158428_38684_20262;
                    effect79Var = new Parameter("effect79Var", null, null, this);
                    addDependency(effect79Var, new Expression(effect79VarV));
                    effect79 = new EffectFunction(new EffectFunction(effect79Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, myDeciderID_decider_17_0_2_edc0357_1352328158428_38684_20262 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158425_220398_20254Collections() {
                parameters.add(_17_0_2_edc0357_1352328158428_38684_20262_exists);
                parameters.add(myDeciderID_decider_17_0_2_edc0357_1352328158428_38684_20262);
                parameters.add(_17_0_2_edc0357_1352328160551_311379_21414);
                parameters.add(objectToPass);
                parameters.add(_17_0_2_edc0357_1352328158428_668812_20261_exists);
                parameters.add(_17_0_2_edc0357_1352328160550_744442_21413);
                Set<Effect> effectsForeffect77Var = new TreeSet<Effect>();
                effectsForeffect77Var.add(effect77);
                addEffects((Parameter<?>) effect77Var, effectsForeffect77Var);
                Set<Effect> effectsForeffect78Var = new TreeSet<Effect>();
                effectsForeffect78Var.add(effect78);
                addEffects((Parameter<?>) effect78Var, effectsForeffect78Var);
                Set<Effect> effectsForeffect79Var = new TreeSet<Effect>();
                effectsForeffect79Var.add(effect79);
                addEffects((Parameter<?>) effect79Var, effectsForeffect79Var);
            }

            public void init_17_0_2_edc0357_1352328158425_220398_20254Dependencies() {
                addDependency(_17_0_2_edc0357_1352328158428_38684_20262_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158428_38684_20262, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158428_38684_20262, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158428_38684_20262, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_edc0357_1352328158428_38684_20262)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_edc0357_1352328158425_174981_20253_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_edc0357_1352328158425_174981_20253_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))))));
                addDependency(myDeciderID_decider_17_0_2_edc0357_1352328158428_38684_20262, new Expression<Integer>(1));
                addDependency(_17_0_2_edc0357_1352328160551_311379_21414, new Expression<Customer>(new EffectFunction(sig_17_0_2_edc0357_1352328158429_395232_20267, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(true));
                addDependency(_17_0_2_edc0357_1352328158428_668812_20261_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_edc0357_1352328158425_174981_20253_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_edc0357_1352328158425_174981_20253_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(_17_0_2_edc0357_1352328160550_744442_21413, new Expression<Integer>(new EffectFunction(null, ClassUtils.getMethodForArgTypes("TimeVaryingPlottableMap<Integer>", "powersystem", "getValue", java.lang.Integer.class), new Object[] { new Expression<Integer>(startTime) }, new FunctionCall(_17_0_2_edc0357_1352328160551_311379_21414, Parameter.class, "getMember", new Object[] { "cap__17_0_2_edc0357_1352328156659_30024_19625" }))));
            }

            public void init_17_0_2_edc0357_1352328158425_220398_20254Elaborations() {
                init_17_0_2_edc0357_1352328158425_220398_20254Dependencies();
                Expression<?>[] arguments80 = new Expression<?>[1];
                arguments80[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition80 = new Expression<Boolean>(_17_0_2_edc0357_1352328158428_668812_20261_exists);
                elaborationRule80 = addElaborationRule(condition80, _17_0_2_edc0357_1352328156639_660827_19616.this, Customer._17_0_2_edc0357_1352328156639_660827_19616._17_0_2_edc0357_1352328158428_668812_20261.class, "CChange_fork3cap_ForkNode_changePowerUsage", arguments80);
                Expression<?>[] arguments81 = new Expression<?>[1];
                arguments81[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition81 = new Expression<Boolean>(_17_0_2_edc0357_1352328158428_38684_20262_exists);
                elaborationRule81 = addElaborationRule(condition81, _17_0_2_edc0357_1352328156639_660827_19616.this, Customer._17_0_2_edc0357_1352328156639_660827_19616._17_0_2_edc0357_1352328158428_38684_20262.class, "CChange_decidecap_DecisionNode_changePowerUsage", arguments81);
            }
        }

        public class _17_0_2_edc0357_1352328158426_178002_20255 extends DurativeEvent {

            public _17_0_2_edc0357_1352328158426_178002_20255() {
                super();
                init_17_0_2_edc0357_1352328158426_178002_20255Members();
                init_17_0_2_edc0357_1352328158426_178002_20255Collections();
                init_17_0_2_edc0357_1352328158426_178002_20255Elaborations();
            }

            public _17_0_2_edc0357_1352328158426_178002_20255(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328158426_178002_20255Members();
                init_17_0_2_edc0357_1352328158426_178002_20255Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328158426_178002_20255Elaborations();
                fixTimeDependencies();
            }

            public IntegerParameter myDeciderID_decider_17_0_2_edc0357_1352328158425_844513_20251 = null;

            public BooleanParameter _17_0_2_edc0357_1352328158426_116757_20257_exists = null;

            public Parameter< Customer > objectToPass = null;

            public IntegerParameter myDeciderID_decider_17_0_2_edc0357_1352328158426_116757_20257 = null;

            public BooleanParameter _17_0_2_edc0357_1352328158425_844513_20251_exists = null;

            public BooleanParameter _17_0_2_edc0357_1352328158425_220398_20254_exists = null;

            public Dependency< Integer > myDeciderID_decider_17_0_2_edc0357_1352328158425_844513_20251Dependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158426_116757_20257_existsDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Customer > objectToPassDependency = null;

            public Dependency< Integer > myDeciderID_decider_17_0_2_edc0357_1352328158426_116757_20257Dependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158425_844513_20251_existsDependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158425_220398_20254_existsDependency = null;

            public Effect effect82 = null;

            public Parameter effect82Var = null;

            public Effect effect83 = null;

            public Parameter effect83Var = null;

            public Effect effect84 = null;

            public Parameter effect84Var = null;

            public Effect effect85 = null;

            public Parameter effect85Var = null;

            public Effect effect86 = null;

            public Parameter effect86Var = null;

            public ElaborationRule elaborationRule87 = null;

            public ElaborationRule elaborationRule88 = null;

            public ElaborationRule elaborationRule89 = null;

            public void init_17_0_2_edc0357_1352328158426_178002_20255Members() {
                try {
                    if (myDeciderID_decider_17_0_2_edc0357_1352328158425_844513_20251 == null) myDeciderID_decider_17_0_2_edc0357_1352328158425_844513_20251 = new IntegerParameter("myDeciderID_decider_17_0_2_edc0357_1352328158425_844513_20251", (Integer) 1, this);
                    if (_17_0_2_edc0357_1352328158426_116757_20257_exists == null) _17_0_2_edc0357_1352328158426_116757_20257_exists = new BooleanParameter("_17_0_2_edc0357_1352328158426_116757_20257_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new Parameter<Customer>("objectToPass", null, (Customer) null, this);
                    if (myDeciderID_decider_17_0_2_edc0357_1352328158426_116757_20257 == null) myDeciderID_decider_17_0_2_edc0357_1352328158426_116757_20257 = new IntegerParameter("myDeciderID_decider_17_0_2_edc0357_1352328158426_116757_20257", (Integer) 2, this);
                    if (_17_0_2_edc0357_1352328158425_844513_20251_exists == null) _17_0_2_edc0357_1352328158425_844513_20251_exists = new BooleanParameter("_17_0_2_edc0357_1352328158425_844513_20251_exists", (Boolean) false, this);
                    if (_17_0_2_edc0357_1352328158425_220398_20254_exists == null) _17_0_2_edc0357_1352328158425_220398_20254_exists = new BooleanParameter("_17_0_2_edc0357_1352328158425_220398_20254_exists", (Boolean) false, this);
                    Object effect82VarV = sig_17_0_2_edc0357_1352328158429_694477_20266;
                    effect82Var = new Parameter("effect82Var", null, null, this);
                    addDependency(effect82Var, new Expression(effect82VarV));
                    effect82 = new EffectFunction(new EffectFunction(effect82Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect83VarV = sig_17_0_2_edc0357_1352328158429_395232_20267;
                    effect83Var = new Parameter("effect83Var", null, null, this);
                    addDependency(effect83Var, new Expression(effect83VarV));
                    effect83 = new EffectFunction(new EffectFunction(effect83Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect84VarV = sig_17_0_2_edc0357_1352328158429_197012_20268;
                    effect84Var = new Parameter("effect84Var", null, null, this);
                    addDependency(effect84Var, new Expression(effect84VarV));
                    effect84 = new EffectFunction(new EffectFunction(effect84Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect85VarV = decider_17_0_2_edc0357_1352328158425_844513_20251;
                    effect85Var = new Parameter("effect85Var", null, null, this);
                    addDependency(effect85Var, new Expression(effect85VarV));
                    effect85 = new EffectFunction(new EffectFunction(effect85Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, myDeciderID_decider_17_0_2_edc0357_1352328158425_844513_20251 }));
                    Object effect86VarV = decider_17_0_2_edc0357_1352328158426_116757_20257;
                    effect86Var = new Parameter("effect86Var", null, null, this);
                    addDependency(effect86Var, new Expression(effect86VarV));
                    effect86 = new EffectFunction(new EffectFunction(effect86Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, myDeciderID_decider_17_0_2_edc0357_1352328158426_116757_20257 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158426_178002_20255Collections() {
                parameters.add(myDeciderID_decider_17_0_2_edc0357_1352328158425_844513_20251);
                parameters.add(_17_0_2_edc0357_1352328158426_116757_20257_exists);
                parameters.add(objectToPass);
                parameters.add(myDeciderID_decider_17_0_2_edc0357_1352328158426_116757_20257);
                parameters.add(_17_0_2_edc0357_1352328158425_844513_20251_exists);
                parameters.add(_17_0_2_edc0357_1352328158425_220398_20254_exists);
                Set<Effect> effectsForeffect82Var = new TreeSet<Effect>();
                effectsForeffect82Var.add(effect82);
                addEffects((Parameter<?>) effect82Var, effectsForeffect82Var);
                Set<Effect> effectsForeffect83Var = new TreeSet<Effect>();
                effectsForeffect83Var.add(effect83);
                addEffects((Parameter<?>) effect83Var, effectsForeffect83Var);
                Set<Effect> effectsForeffect84Var = new TreeSet<Effect>();
                effectsForeffect84Var.add(effect84);
                addEffects((Parameter<?>) effect84Var, effectsForeffect84Var);
                Set<Effect> effectsForeffect85Var = new TreeSet<Effect>();
                effectsForeffect85Var.add(effect85);
                addEffects((Parameter<?>) effect85Var, effectsForeffect85Var);
                Set<Effect> effectsForeffect86Var = new TreeSet<Effect>();
                effectsForeffect86Var.add(effect86);
                addEffects((Parameter<?>) effect86Var, effectsForeffect86Var);
            }

            public void init_17_0_2_edc0357_1352328158426_178002_20255Dependencies() {
                addDependency(myDeciderID_decider_17_0_2_edc0357_1352328158425_844513_20251, new Expression<Integer>(1));
                addDependency(_17_0_2_edc0357_1352328158426_116757_20257_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158426_116757_20257, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158426_116757_20257, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158426_116757_20257, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_edc0357_1352328158426_116757_20257)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_edc0357_1352328158425_174981_20253_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_edc0357_1352328158425_174981_20253_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Customer>(new EffectFunction(sig_17_0_2_edc0357_1352328158429_502495_20265, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(myDeciderID_decider_17_0_2_edc0357_1352328158426_116757_20257, new Expression<Integer>(2));
                addDependency(_17_0_2_edc0357_1352328158425_844513_20251_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158425_844513_20251, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158425_844513_20251, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158425_844513_20251, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_edc0357_1352328158425_844513_20251)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_edc0357_1352328158425_174981_20253_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_edc0357_1352328158425_174981_20253_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))))));
                addDependency(_17_0_2_edc0357_1352328158425_220398_20254_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_edc0357_1352328158425_174981_20253_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_edc0357_1352328158425_174981_20253_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
            }

            public void init_17_0_2_edc0357_1352328158426_178002_20255Elaborations() {
                init_17_0_2_edc0357_1352328158426_178002_20255Dependencies();
                Expression<?>[] arguments87 = new Expression<?>[1];
                arguments87[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition87 = new Expression<Boolean>(_17_0_2_edc0357_1352328158425_844513_20251_exists);
                elaborationRule87 = addElaborationRule(condition87, _17_0_2_edc0357_1352328156639_660827_19616.this, Customer._17_0_2_edc0357_1352328156639_660827_19616._17_0_2_edc0357_1352328158425_844513_20251.class, "CChange_setnewusage_AddStructuralFeatureValueAction_changePowerUsage", arguments87);
                Expression<?>[] arguments88 = new Expression<?>[1];
                arguments88[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition88 = new Expression<Boolean>(_17_0_2_edc0357_1352328158425_220398_20254_exists);
                elaborationRule88 = addElaborationRule(condition88, _17_0_2_edc0357_1352328156639_660827_19616.this, Customer._17_0_2_edc0357_1352328156639_660827_19616._17_0_2_edc0357_1352328158425_220398_20254.class, "CChange_readcurrentcap_ReadStructuralFeatureAction_changePowerUsage", arguments88);
                Expression<?>[] arguments89 = new Expression<?>[1];
                arguments89[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition89 = new Expression<Boolean>(_17_0_2_edc0357_1352328158426_116757_20257_exists);
                elaborationRule89 = addElaborationRule(condition89, _17_0_2_edc0357_1352328156639_660827_19616.this, Customer._17_0_2_edc0357_1352328156639_660827_19616._17_0_2_edc0357_1352328158426_116757_20257.class, "CChange_sendnewusage_SendSignalAction_changePowerUsage", arguments89);
            }
        }

        public class _17_0_2_edc0357_1352328158426_903101_20256 extends DurativeEvent {

            public _17_0_2_edc0357_1352328158426_903101_20256() {
                super();
                init_17_0_2_edc0357_1352328158426_903101_20256Members();
                init_17_0_2_edc0357_1352328158426_903101_20256Collections();
                init_17_0_2_edc0357_1352328158426_903101_20256Elaborations();
            }

            public _17_0_2_edc0357_1352328158426_903101_20256(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328158426_903101_20256Members();
                init_17_0_2_edc0357_1352328158426_903101_20256Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328158426_903101_20256Elaborations();
                fixTimeDependencies();
            }

            public IntegerParameter _17_0_2_edc0357_1352328160553_159569_21417 = null;

            public IntegerParameter new_load = null;

            public BooleanParameter _17_0_2_edc0357_1352328158428_314626_20264_exists = null;

            public IntegerParameter cap = null;

            public IntegerParameter desired = null;

            public BooleanParameter objectToPass = null;

            public IntegerParameter _17_0_2_edc0357_1352328160552_993697_21416 = null;

            public IntegerParameter _17_0_2_edc0357_1352328160551_57517_21415 = null;

            public Dependency< Integer > _17_0_2_edc0357_1352328160553_159569_21417Dependency = null;

            public Dependency< Integer > new_loadDependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158428_314626_20264_existsDependency = null;

            public Dependency< Integer > capDependency = null;

            public Dependency< Integer > desiredDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Dependency< Integer > _17_0_2_edc0357_1352328160552_993697_21416Dependency = null;

            public Dependency< Integer > _17_0_2_edc0357_1352328160551_57517_21415Dependency = null;

            public Effect effect90 = null;

            public Parameter effect90Var = null;

            public ElaborationRule elaborationRule91 = null;

            public void init_17_0_2_edc0357_1352328158426_903101_20256Members() {
                try {
                    if (_17_0_2_edc0357_1352328160553_159569_21417 == null) _17_0_2_edc0357_1352328160553_159569_21417 = new IntegerParameter("_17_0_2_edc0357_1352328160553_159569_21417", (Integer) null, this);
                    if (new_load == null) new_load = new IntegerParameter("new_load", (Integer) null, this);
                    if (_17_0_2_edc0357_1352328158428_314626_20264_exists == null) _17_0_2_edc0357_1352328158428_314626_20264_exists = new BooleanParameter("_17_0_2_edc0357_1352328158428_314626_20264_exists", (Boolean) false, this);
                    if (cap == null) cap = new IntegerParameter("cap", (Integer) null, this);
                    if (desired == null) desired = new IntegerParameter("desired", (Integer) null, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (_17_0_2_edc0357_1352328160552_993697_21416 == null) _17_0_2_edc0357_1352328160552_993697_21416 = new IntegerParameter("_17_0_2_edc0357_1352328160552_993697_21416", (Integer) null, this);
                    if (_17_0_2_edc0357_1352328160551_57517_21415 == null) _17_0_2_edc0357_1352328160551_57517_21415 = new IntegerParameter("_17_0_2_edc0357_1352328160551_57517_21415", (Integer) null, this);
                    Object effect90VarV = sig_17_0_2_edc0357_1352328158431_565981_20286;
                    effect90Var = new Parameter("effect90Var", null, null, this);
                    addDependency(effect90Var, new Expression(effect90VarV));
                    effect90 = new EffectFunction(new EffectFunction(effect90Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { _17_0_2_edc0357_1352328160551_57517_21415, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158426_903101_20256Collections() {
                parameters.add(_17_0_2_edc0357_1352328160553_159569_21417);
                parameters.add(new_load);
                parameters.add(_17_0_2_edc0357_1352328158428_314626_20264_exists);
                parameters.add(cap);
                parameters.add(desired);
                parameters.add(objectToPass);
                parameters.add(_17_0_2_edc0357_1352328160552_993697_21416);
                parameters.add(_17_0_2_edc0357_1352328160551_57517_21415);
                Set<Effect> effectsForeffect90Var = new TreeSet<Effect>();
                effectsForeffect90Var.add(effect90);
                addEffects((Parameter<?>) effect90Var, effectsForeffect90Var);
            }

            public void init_17_0_2_edc0357_1352328158426_903101_20256Dependencies() {
                addDependency(_17_0_2_edc0357_1352328160553_159569_21417, new Expression<Integer>(new EffectFunction(sig_17_0_2_edc0357_1352328158430_389607_20279, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(new_load, new Expression<Integer>(new FunctionCall(null, ClassUtils.getMethodForArgTypes("java.lang.Math", "powersystem", "min", int.class, int.class), new Object[] { new Expression<Integer>(desired), new Expression<Integer>(cap) })));
                addDependency(_17_0_2_edc0357_1352328158428_314626_20264_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_edc0357_1352328158425_174981_20253_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_edc0357_1352328158425_174981_20253_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(cap, new Expression<Integer>(_17_0_2_edc0357_1352328160553_159569_21417));
                addDependency(desired, new Expression<Integer>(_17_0_2_edc0357_1352328160552_993697_21416));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new EffectFunction(sig_17_0_2_edc0357_1352328158431_806383_20280, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_edc0357_1352328160552_993697_21416, new Expression<Integer>(new EffectFunction(sig_17_0_2_edc0357_1352328158431_868302_20283, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_edc0357_1352328160551_57517_21415, new Expression<Integer>(new_load));
            }

            public void init_17_0_2_edc0357_1352328158426_903101_20256Elaborations() {
                init_17_0_2_edc0357_1352328158426_903101_20256Dependencies();
                Expression<?>[] arguments91 = new Expression<?>[2];
                arguments91[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                arguments91[1] = new Expression<ObjectFlow<Integer>>(sig_17_0_2_edc0357_1352328158431_565981_20286);
                Expression<Boolean> condition91 = new Expression<Boolean>(_17_0_2_edc0357_1352328158428_314626_20264_exists);
                elaborationRule91 = addElaborationRule(condition91, _17_0_2_edc0357_1352328156639_660827_19616.this, Customer._17_0_2_edc0357_1352328156639_660827_19616._17_0_2_edc0357_1352328158428_314626_20264.class, "CChange_merge_changeTo_MergeNode_changePowerUsage", arguments91);
            }
        }

        public class _17_0_2_edc0357_1352328158426_116757_20257 extends DurativeEvent {

            public _17_0_2_edc0357_1352328158426_116757_20257() {
                super();
                init_17_0_2_edc0357_1352328158426_116757_20257Members();
                init_17_0_2_edc0357_1352328158426_116757_20257Collections();
                init_17_0_2_edc0357_1352328158426_116757_20257Elaborations();
            }

            public _17_0_2_edc0357_1352328158426_116757_20257(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328158426_116757_20257Members();
                init_17_0_2_edc0357_1352328158426_116757_20257Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328158426_116757_20257Elaborations();
                fixTimeDependencies();
            }

            public IntegerParameter _17_0_2_edc0357_1352328160554_929788_21419 = null;

            public Parameter< Customer > _17_0_2_edc0357_1352328160553_902427_21418 = null;

            public BooleanParameter objectToPass = null;

            public Parameter< Power_System.SignalchangeLoadValue > signalObject = null;

            public Dependency< Integer > _17_0_2_edc0357_1352328160554_929788_21419Dependency = null;

            public Dependency< Customer > _17_0_2_edc0357_1352328160553_902427_21418Dependency = null;

            public Dependency< Integer > finalNode_startTimeDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Dependency< Power_System.SignalchangeLoadValue > signalObjectDependency = null;

            public Effect effect92 = null;

            public Parameter effect92Var = null;

            public Effect effect93 = null;

            public Parameter effect93Var = null;

            public Effect effect94 = null;

            public Parameter effect94Var = null;

            public void init_17_0_2_edc0357_1352328158426_116757_20257Members() {
                try {
                    if (_17_0_2_edc0357_1352328160554_929788_21419 == null) _17_0_2_edc0357_1352328160554_929788_21419 = new IntegerParameter("_17_0_2_edc0357_1352328160554_929788_21419", (Integer) null, this);
                    if (_17_0_2_edc0357_1352328160553_902427_21418 == null) _17_0_2_edc0357_1352328160553_902427_21418 = new Parameter<Customer>("_17_0_2_edc0357_1352328160553_902427_21418", null, (Customer) null, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (signalObject == null) signalObject = new Parameter<Power_System.SignalchangeLoadValue>("signalObject", null, (Power_System.SignalchangeLoadValue) null, this);
                    Object effect92VarV = sig_17_0_2_edc0357_1352328158429_418011_20269;
                    effect92Var = new Parameter("effect92Var", null, null, this);
                    addDependency(effect92Var, new Expression(effect92VarV));
                    effect92 = new EffectFunction(new EffectFunction(effect92Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect93VarV = new FunctionCall(x, Parameter.class, "getMember", new Object[] { "ss_17_0_2_edc0357_1352328156650_180051_19621_changeLoadValue" });
                    effect93Var = new Parameter("effect93Var", null, null, this);
                    addDependency(effect93Var, new Expression(effect93VarV));
                    effect93 = new EffectFunction(new EffectFunction(null, ClassUtils.getMethodForArgTypes("ObjectFlow<SignalchangeLoadValue>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { signalObject, endTime }, effect93Var));
                    Object effect94VarV = new FunctionCall(signalObject, Parameter.class, "getMember", new Object[] { "load__17_0_2_edc0357_1352328156636_307114_19611" });
                    effect94Var = new Parameter("effect94Var", null, null, this);
                    addDependency(effect94Var, new Expression(effect94VarV));
                    effect94 = new EffectFunction(new EffectFunction(null, ClassUtils.getMethodForArgTypes("TimeVaryingMap<Integer>", "powersystem", "setValue", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, _17_0_2_edc0357_1352328160554_929788_21419 }, effect94Var));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158426_116757_20257Collections() {
                parameters.add(_17_0_2_edc0357_1352328160554_929788_21419);
                parameters.add(_17_0_2_edc0357_1352328160553_902427_21418);
                parameters.add(objectToPass);
                parameters.add(signalObject);
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

            public void init_17_0_2_edc0357_1352328158426_116757_20257Dependencies() {
                addDependency(_17_0_2_edc0357_1352328160554_929788_21419, new Expression<Integer>(new EffectFunction(sig_17_0_2_edc0357_1352328158430_385741_20271, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_edc0357_1352328160553_902427_21418, new Expression<Customer>(new EffectFunction(sig_17_0_2_edc0357_1352328158429_197012_20268, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(finalNode_startTime, new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new EffectFunction(sig_17_0_2_edc0357_1352328158430_24745_20272, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(signalObject, new Expression<Power_System.SignalchangeLoadValue>(new ConstructorCall(x, ClassUtils.getConstructorForArgTypes(Power_System.SignalchangeLoadValue.class), new Object[] {})));
            }

            public void init_17_0_2_edc0357_1352328158426_116757_20257Elaborations() {
                init_17_0_2_edc0357_1352328158426_116757_20257Dependencies();
            }
        }

        public class _17_0_2_edc0357_1352328158427_233123_20258 extends DurativeEvent {

            public _17_0_2_edc0357_1352328158427_233123_20258() {
                super();
                init_17_0_2_edc0357_1352328158427_233123_20258Members();
                init_17_0_2_edc0357_1352328158427_233123_20258Collections();
                init_17_0_2_edc0357_1352328158427_233123_20258Elaborations();
            }

            public _17_0_2_edc0357_1352328158427_233123_20258(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328158427_233123_20258Members();
                init_17_0_2_edc0357_1352328158427_233123_20258Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328158427_233123_20258Elaborations();
                fixTimeDependencies();
            }

            public IntegerParameter myDeciderID_decider_17_0_2_edc0357_1352328158425_844513_20251 = null;

            public BooleanParameter _17_0_2_edc0357_1352328158426_116757_20257_exists = null;

            public IntegerParameter objectToPass = null;

            public IntegerParameter myDeciderID_decider_17_0_2_edc0357_1352328158426_116757_20257 = null;

            public BooleanParameter _17_0_2_edc0357_1352328158425_844513_20251_exists = null;

            public Dependency< Integer > myDeciderID_decider_17_0_2_edc0357_1352328158425_844513_20251Dependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158426_116757_20257_existsDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Integer > objectToPassDependency = null;

            public Dependency< Integer > myDeciderID_decider_17_0_2_edc0357_1352328158426_116757_20257Dependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158425_844513_20251_existsDependency = null;

            public Effect effect95 = null;

            public Parameter effect95Var = null;

            public Effect effect96 = null;

            public Parameter effect96Var = null;

            public Effect effect97 = null;

            public Parameter effect97Var = null;

            public Effect effect98 = null;

            public Parameter effect98Var = null;

            public ElaborationRule elaborationRule99 = null;

            public ElaborationRule elaborationRule100 = null;

            public void init_17_0_2_edc0357_1352328158427_233123_20258Members() {
                try {
                    if (myDeciderID_decider_17_0_2_edc0357_1352328158425_844513_20251 == null) myDeciderID_decider_17_0_2_edc0357_1352328158425_844513_20251 = new IntegerParameter("myDeciderID_decider_17_0_2_edc0357_1352328158425_844513_20251", (Integer) 2, this);
                    if (_17_0_2_edc0357_1352328158426_116757_20257_exists == null) _17_0_2_edc0357_1352328158426_116757_20257_exists = new BooleanParameter("_17_0_2_edc0357_1352328158426_116757_20257_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new IntegerParameter("objectToPass", (Integer) null, this);
                    if (myDeciderID_decider_17_0_2_edc0357_1352328158426_116757_20257 == null) myDeciderID_decider_17_0_2_edc0357_1352328158426_116757_20257 = new IntegerParameter("myDeciderID_decider_17_0_2_edc0357_1352328158426_116757_20257", (Integer) 1, this);
                    if (_17_0_2_edc0357_1352328158425_844513_20251_exists == null) _17_0_2_edc0357_1352328158425_844513_20251_exists = new BooleanParameter("_17_0_2_edc0357_1352328158425_844513_20251_exists", (Boolean) false, this);
                    Object effect95VarV = sig_17_0_2_edc0357_1352328158429_532173_20270;
                    effect95Var = new Parameter("effect95Var", null, null, this);
                    addDependency(effect95Var, new Expression(effect95VarV));
                    effect95 = new EffectFunction(new EffectFunction(effect95Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect96VarV = sig_17_0_2_edc0357_1352328158430_385741_20271;
                    effect96Var = new Parameter("effect96Var", null, null, this);
                    addDependency(effect96Var, new Expression(effect96VarV));
                    effect96 = new EffectFunction(new EffectFunction(effect96Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect97VarV = decider_17_0_2_edc0357_1352328158425_844513_20251;
                    effect97Var = new Parameter("effect97Var", null, null, this);
                    addDependency(effect97Var, new Expression(effect97VarV));
                    effect97 = new EffectFunction(new EffectFunction(effect97Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, myDeciderID_decider_17_0_2_edc0357_1352328158425_844513_20251 }));
                    Object effect98VarV = decider_17_0_2_edc0357_1352328158426_116757_20257;
                    effect98Var = new Parameter("effect98Var", null, null, this);
                    addDependency(effect98Var, new Expression(effect98VarV));
                    effect98 = new EffectFunction(new EffectFunction(effect98Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, myDeciderID_decider_17_0_2_edc0357_1352328158426_116757_20257 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158427_233123_20258Collections() {
                parameters.add(myDeciderID_decider_17_0_2_edc0357_1352328158425_844513_20251);
                parameters.add(_17_0_2_edc0357_1352328158426_116757_20257_exists);
                parameters.add(objectToPass);
                parameters.add(myDeciderID_decider_17_0_2_edc0357_1352328158426_116757_20257);
                parameters.add(_17_0_2_edc0357_1352328158425_844513_20251_exists);
                Set<Effect> effectsForeffect95Var = new TreeSet<Effect>();
                effectsForeffect95Var.add(effect95);
                addEffects((Parameter<?>) effect95Var, effectsForeffect95Var);
                Set<Effect> effectsForeffect96Var = new TreeSet<Effect>();
                effectsForeffect96Var.add(effect96);
                addEffects((Parameter<?>) effect96Var, effectsForeffect96Var);
                Set<Effect> effectsForeffect97Var = new TreeSet<Effect>();
                effectsForeffect97Var.add(effect97);
                addEffects((Parameter<?>) effect97Var, effectsForeffect97Var);
                Set<Effect> effectsForeffect98Var = new TreeSet<Effect>();
                effectsForeffect98Var.add(effect98);
                addEffects((Parameter<?>) effect98Var, effectsForeffect98Var);
            }

            public void init_17_0_2_edc0357_1352328158427_233123_20258Dependencies() {
                addDependency(myDeciderID_decider_17_0_2_edc0357_1352328158425_844513_20251, new Expression<Integer>(2));
                addDependency(_17_0_2_edc0357_1352328158426_116757_20257_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158426_116757_20257, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158426_116757_20257, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158426_116757_20257, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_edc0357_1352328158426_116757_20257)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_edc0357_1352328158425_174981_20253_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_edc0357_1352328158425_174981_20253_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Integer>(new EffectFunction(sig_17_0_2_edc0357_1352328158431_643907_20287, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(myDeciderID_decider_17_0_2_edc0357_1352328158426_116757_20257, new Expression<Integer>(1));
                addDependency(_17_0_2_edc0357_1352328158425_844513_20251_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158425_844513_20251, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158425_844513_20251, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158425_844513_20251, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_edc0357_1352328158425_844513_20251)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_edc0357_1352328158425_174981_20253_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_edc0357_1352328158425_174981_20253_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))))));
            }

            public void init_17_0_2_edc0357_1352328158427_233123_20258Elaborations() {
                init_17_0_2_edc0357_1352328158427_233123_20258Dependencies();
                Expression<?>[] arguments99 = new Expression<?>[1];
                arguments99[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition99 = new Expression<Boolean>(_17_0_2_edc0357_1352328158425_844513_20251_exists);
                elaborationRule99 = addElaborationRule(condition99, _17_0_2_edc0357_1352328156639_660827_19616.this, Customer._17_0_2_edc0357_1352328156639_660827_19616._17_0_2_edc0357_1352328158425_844513_20251.class, "CChange_setnewusage_AddStructuralFeatureValueAction_changePowerUsage", arguments99);
                Expression<?>[] arguments100 = new Expression<?>[1];
                arguments100[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition100 = new Expression<Boolean>(_17_0_2_edc0357_1352328158426_116757_20257_exists);
                elaborationRule100 = addElaborationRule(condition100, _17_0_2_edc0357_1352328156639_660827_19616.this, Customer._17_0_2_edc0357_1352328156639_660827_19616._17_0_2_edc0357_1352328158426_116757_20257.class, "CChange_sendnewusage_SendSignalAction_changePowerUsage", arguments100);
            }
        }

        public class _17_0_2_edc0357_1352328158427_71576_20259 extends DurativeEvent {

            public _17_0_2_edc0357_1352328158427_71576_20259() {
                super();
                init_17_0_2_edc0357_1352328158427_71576_20259Members();
                init_17_0_2_edc0357_1352328158427_71576_20259Collections();
                init_17_0_2_edc0357_1352328158427_71576_20259Elaborations();
            }

            public _17_0_2_edc0357_1352328158427_71576_20259(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328158427_71576_20259Members();
                init_17_0_2_edc0357_1352328158427_71576_20259Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328158427_71576_20259Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_edc0357_1352328158425_536558_20249_exists = null;

            public BooleanParameter _17_0_2_edc0357_1352328158425_562603_20250_exists = null;

            public BooleanParameter objectToPass = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158425_536558_20249_existsDependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158425_562603_20250_existsDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Effect effect101 = null;

            public Parameter effect101Var = null;

            public Effect effect102 = null;

            public Parameter effect102Var = null;

            public ElaborationRule elaborationRule103 = null;

            public ElaborationRule elaborationRule104 = null;

            public void init_17_0_2_edc0357_1352328158427_71576_20259Members() {
                try {
                    if (_17_0_2_edc0357_1352328158425_536558_20249_exists == null) _17_0_2_edc0357_1352328158425_536558_20249_exists = new BooleanParameter("_17_0_2_edc0357_1352328158425_536558_20249_exists", (Boolean) false, this);
                    if (_17_0_2_edc0357_1352328158425_562603_20250_exists == null) _17_0_2_edc0357_1352328158425_562603_20250_exists = new BooleanParameter("_17_0_2_edc0357_1352328158425_562603_20250_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect101VarV = sig_17_0_2_edc0357_1352328158430_913443_20274;
                    effect101Var = new Parameter("effect101Var", null, null, this);
                    addDependency(effect101Var, new Expression(effect101VarV));
                    effect101 = new EffectFunction(new EffectFunction(effect101Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect102VarV = sig_17_0_2_edc0357_1352328158430_934674_20275;
                    effect102Var = new Parameter("effect102Var", null, null, this);
                    addDependency(effect102Var, new Expression(effect102VarV));
                    effect102 = new EffectFunction(new EffectFunction(effect102Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158427_71576_20259Collections() {
                parameters.add(_17_0_2_edc0357_1352328158425_536558_20249_exists);
                parameters.add(_17_0_2_edc0357_1352328158425_562603_20250_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect101Var = new TreeSet<Effect>();
                effectsForeffect101Var.add(effect101);
                addEffects((Parameter<?>) effect101Var, effectsForeffect101Var);
                Set<Effect> effectsForeffect102Var = new TreeSet<Effect>();
                effectsForeffect102Var.add(effect102);
                addEffects((Parameter<?>) effect102Var, effectsForeffect102Var);
            }

            public void init_17_0_2_edc0357_1352328158427_71576_20259Dependencies() {
                addDependency(_17_0_2_edc0357_1352328158425_536558_20249_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_edc0357_1352328158425_174981_20253_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_edc0357_1352328158425_174981_20253_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(_17_0_2_edc0357_1352328158425_562603_20250_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_edc0357_1352328158425_174981_20253_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_edc0357_1352328158425_174981_20253_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new EffectFunction(sig_17_0_2_edc0357_1352328158430_229342_20273, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_edc0357_1352328158427_71576_20259Elaborations() {
                init_17_0_2_edc0357_1352328158427_71576_20259Dependencies();
                Expression<?>[] arguments103 = new Expression<?>[1];
                arguments103[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition103 = new Expression<Boolean>(_17_0_2_edc0357_1352328158425_536558_20249_exists);
                elaborationRule103 = addElaborationRule(condition103, _17_0_2_edc0357_1352328156639_660827_19616.this, Customer._17_0_2_edc0357_1352328156639_660827_19616._17_0_2_edc0357_1352328158425_536558_20249.class, "CChange_readself_ReadSelfAction_changePowerUsage", arguments103);
                Expression<?>[] arguments104 = new Expression<?>[1];
                arguments104[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition104 = new Expression<Boolean>(_17_0_2_edc0357_1352328158425_562603_20250_exists);
                elaborationRule104 = addElaborationRule(condition104, _17_0_2_edc0357_1352328156639_660827_19616.this, Customer._17_0_2_edc0357_1352328156639_660827_19616._17_0_2_edc0357_1352328158425_562603_20250.class, "CChange_spec15_ValueSpecificationAction_changePowerUsage", arguments104);
            }
        }

        public class _17_0_2_edc0357_1352328158427_744964_20260 extends DurativeEvent {

            public _17_0_2_edc0357_1352328158427_744964_20260() {
                super();
                init_17_0_2_edc0357_1352328158427_744964_20260Members();
                init_17_0_2_edc0357_1352328158427_744964_20260Collections();
                init_17_0_2_edc0357_1352328158427_744964_20260Elaborations();
            }

            public _17_0_2_edc0357_1352328158427_744964_20260(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328158427_744964_20260Members();
                init_17_0_2_edc0357_1352328158427_744964_20260Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328158427_744964_20260Elaborations();
                fixTimeDependencies();
            }

            public IntegerParameter new_load = null;

            public IntegerParameter _17_0_2_edc0357_1352328160555_982450_21420 = null;

            public BooleanParameter _17_0_2_edc0357_1352328158428_314626_20264_exists = null;

            public IntegerParameter _17_0_2_edc0357_1352328160555_212303_21421 = null;

            public IntegerParameter desired = null;

            public BooleanParameter objectToPass = null;

            public Dependency< Integer > _17_0_2_edc0357_1352328160555_982450_21420Dependency = null;

            public Dependency< Integer > new_loadDependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158428_314626_20264_existsDependency = null;

            public Dependency< Integer > _17_0_2_edc0357_1352328160555_212303_21421Dependency = null;

            public Dependency< Integer > desiredDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Effect effect105 = null;

            public Parameter effect105Var = null;

            public ElaborationRule elaborationRule106 = null;

            public void init_17_0_2_edc0357_1352328158427_744964_20260Members() {
                try {
                    if (new_load == null) new_load = new IntegerParameter("new_load", (Integer) null, this);
                    if (_17_0_2_edc0357_1352328160555_982450_21420 == null) _17_0_2_edc0357_1352328160555_982450_21420 = new IntegerParameter("_17_0_2_edc0357_1352328160555_982450_21420", (Integer) null, this);
                    if (_17_0_2_edc0357_1352328158428_314626_20264_exists == null) _17_0_2_edc0357_1352328158428_314626_20264_exists = new BooleanParameter("_17_0_2_edc0357_1352328158428_314626_20264_exists", (Boolean) false, this);
                    if (_17_0_2_edc0357_1352328160555_212303_21421 == null) _17_0_2_edc0357_1352328160555_212303_21421 = new IntegerParameter("_17_0_2_edc0357_1352328160555_212303_21421", (Integer) null, this);
                    if (desired == null) desired = new IntegerParameter("desired", (Integer) null, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect105VarV = sig_17_0_2_edc0357_1352328158431_441018_20285;
                    effect105Var = new Parameter("effect105Var", null, null, this);
                    addDependency(effect105Var, new Expression(effect105VarV));
                    effect105 = new EffectFunction(new EffectFunction(effect105Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { _17_0_2_edc0357_1352328160555_982450_21420, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158427_744964_20260Collections() {
                parameters.add(new_load);
                parameters.add(_17_0_2_edc0357_1352328160555_982450_21420);
                parameters.add(_17_0_2_edc0357_1352328158428_314626_20264_exists);
                parameters.add(_17_0_2_edc0357_1352328160555_212303_21421);
                parameters.add(desired);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect105Var = new TreeSet<Effect>();
                effectsForeffect105Var.add(effect105);
                addEffects((Parameter<?>) effect105Var, effectsForeffect105Var);
            }

            public void init_17_0_2_edc0357_1352328158427_744964_20260Dependencies() {
                addDependency(_17_0_2_edc0357_1352328160555_982450_21420, new Expression<Integer>(new_load));
                addDependency(new_load, new Expression<Integer>(desired));
                addDependency(_17_0_2_edc0357_1352328158428_314626_20264_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_edc0357_1352328158425_174981_20253_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_edc0357_1352328158425_174981_20253_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(_17_0_2_edc0357_1352328160555_212303_21421, new Expression<Integer>(new EffectFunction(sig_17_0_2_edc0357_1352328158431_555149_20284, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(desired, new Expression<Integer>(_17_0_2_edc0357_1352328160555_212303_21421));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new EffectFunction(sig_17_0_2_edc0357_1352328158431_279190_20281, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_edc0357_1352328158427_744964_20260Elaborations() {
                init_17_0_2_edc0357_1352328158427_744964_20260Dependencies();
                Expression<?>[] arguments106 = new Expression<?>[2];
                arguments106[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                arguments106[1] = new Expression<ObjectFlow<Integer>>(sig_17_0_2_edc0357_1352328158431_441018_20285);
                Expression<Boolean> condition106 = new Expression<Boolean>(_17_0_2_edc0357_1352328158428_314626_20264_exists);
                elaborationRule106 = addElaborationRule(condition106, _17_0_2_edc0357_1352328156639_660827_19616.this, Customer._17_0_2_edc0357_1352328156639_660827_19616._17_0_2_edc0357_1352328158428_314626_20264.class, "CChange_merge_changeTo_MergeNode_changePowerUsage", arguments106);
            }
        }

        public class _17_0_2_edc0357_1352328158428_668812_20261 extends DurativeEvent {

            public _17_0_2_edc0357_1352328158428_668812_20261() {
                super();
                init_17_0_2_edc0357_1352328158428_668812_20261Members();
                init_17_0_2_edc0357_1352328158428_668812_20261Collections();
                init_17_0_2_edc0357_1352328158428_668812_20261Elaborations();
            }

            public _17_0_2_edc0357_1352328158428_668812_20261(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328158428_668812_20261Members();
                init_17_0_2_edc0357_1352328158428_668812_20261Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328158428_668812_20261Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_edc0357_1352328158428_38684_20262_exists = null;

            public IntegerParameter myDeciderID_decider_17_0_2_edc0357_1352328158428_38684_20262 = null;

            public IntegerParameter myDeciderID_decider_17_0_2_edc0357_1352328158426_903101_20256 = null;

            public IntegerParameter objectToPass = null;

            public BooleanParameter _17_0_2_edc0357_1352328158426_903101_20256_exists = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158428_38684_20262_existsDependency = null;

            public Dependency< Integer > myDeciderID_decider_17_0_2_edc0357_1352328158428_38684_20262Dependency = null;

            public Dependency< Integer > myDeciderID_decider_17_0_2_edc0357_1352328158426_903101_20256Dependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Integer > objectToPassDependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158426_903101_20256_existsDependency = null;

            public Effect effect107 = null;

            public Parameter effect107Var = null;

            public Effect effect108 = null;

            public Parameter effect108Var = null;

            public Effect effect109 = null;

            public Parameter effect109Var = null;

            public Effect effect110 = null;

            public Parameter effect110Var = null;

            public ElaborationRule elaborationRule111 = null;

            public ElaborationRule elaborationRule112 = null;

            public void init_17_0_2_edc0357_1352328158428_668812_20261Members() {
                try {
                    if (_17_0_2_edc0357_1352328158428_38684_20262_exists == null) _17_0_2_edc0357_1352328158428_38684_20262_exists = new BooleanParameter("_17_0_2_edc0357_1352328158428_38684_20262_exists", (Boolean) false, this);
                    if (myDeciderID_decider_17_0_2_edc0357_1352328158428_38684_20262 == null) myDeciderID_decider_17_0_2_edc0357_1352328158428_38684_20262 = new IntegerParameter("myDeciderID_decider_17_0_2_edc0357_1352328158428_38684_20262", (Integer) 2, this);
                    if (myDeciderID_decider_17_0_2_edc0357_1352328158426_903101_20256 == null) myDeciderID_decider_17_0_2_edc0357_1352328158426_903101_20256 = new IntegerParameter("myDeciderID_decider_17_0_2_edc0357_1352328158426_903101_20256", (Integer) 2, this);
                    if (objectToPass == null) objectToPass = new IntegerParameter("objectToPass", (Integer) null, this);
                    if (_17_0_2_edc0357_1352328158426_903101_20256_exists == null) _17_0_2_edc0357_1352328158426_903101_20256_exists = new BooleanParameter("_17_0_2_edc0357_1352328158426_903101_20256_exists", (Boolean) false, this);
                    Object effect107VarV = sig_17_0_2_edc0357_1352328158430_40918_20278;
                    effect107Var = new Parameter("effect107Var", null, null, this);
                    addDependency(effect107Var, new Expression(effect107VarV));
                    effect107 = new EffectFunction(new EffectFunction(effect107Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect108VarV = sig_17_0_2_edc0357_1352328158430_389607_20279;
                    effect108Var = new Parameter("effect108Var", null, null, this);
                    addDependency(effect108Var, new Expression(effect108VarV));
                    effect108 = new EffectFunction(new EffectFunction(effect108Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect109VarV = decider_17_0_2_edc0357_1352328158426_903101_20256;
                    effect109Var = new Parameter("effect109Var", null, null, this);
                    addDependency(effect109Var, new Expression(effect109VarV));
                    effect109 = new EffectFunction(new EffectFunction(effect109Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, myDeciderID_decider_17_0_2_edc0357_1352328158426_903101_20256 }));
                    Object effect110VarV = decider_17_0_2_edc0357_1352328158428_38684_20262;
                    effect110Var = new Parameter("effect110Var", null, null, this);
                    addDependency(effect110Var, new Expression(effect110VarV));
                    effect110 = new EffectFunction(new EffectFunction(effect110Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, myDeciderID_decider_17_0_2_edc0357_1352328158428_38684_20262 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158428_668812_20261Collections() {
                parameters.add(_17_0_2_edc0357_1352328158428_38684_20262_exists);
                parameters.add(myDeciderID_decider_17_0_2_edc0357_1352328158428_38684_20262);
                parameters.add(myDeciderID_decider_17_0_2_edc0357_1352328158426_903101_20256);
                parameters.add(objectToPass);
                parameters.add(_17_0_2_edc0357_1352328158426_903101_20256_exists);
                Set<Effect> effectsForeffect107Var = new TreeSet<Effect>();
                effectsForeffect107Var.add(effect107);
                addEffects((Parameter<?>) effect107Var, effectsForeffect107Var);
                Set<Effect> effectsForeffect108Var = new TreeSet<Effect>();
                effectsForeffect108Var.add(effect108);
                addEffects((Parameter<?>) effect108Var, effectsForeffect108Var);
                Set<Effect> effectsForeffect109Var = new TreeSet<Effect>();
                effectsForeffect109Var.add(effect109);
                addEffects((Parameter<?>) effect109Var, effectsForeffect109Var);
                Set<Effect> effectsForeffect110Var = new TreeSet<Effect>();
                effectsForeffect110Var.add(effect110);
                addEffects((Parameter<?>) effect110Var, effectsForeffect110Var);
            }

            public void init_17_0_2_edc0357_1352328158428_668812_20261Dependencies() {
                addDependency(_17_0_2_edc0357_1352328158428_38684_20262_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158428_38684_20262, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158428_38684_20262, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158428_38684_20262, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_edc0357_1352328158428_38684_20262)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_edc0357_1352328158425_174981_20253_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_edc0357_1352328158425_174981_20253_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))))));
                addDependency(myDeciderID_decider_17_0_2_edc0357_1352328158428_38684_20262, new Expression<Integer>(2));
                addDependency(myDeciderID_decider_17_0_2_edc0357_1352328158426_903101_20256, new Expression<Integer>(2));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Integer>(new EffectFunction(sig_17_0_2_edc0357_1352328158430_538124_20277, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_edc0357_1352328158426_903101_20256_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158426_903101_20256, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158426_903101_20256, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158426_903101_20256, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_edc0357_1352328158426_903101_20256)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_edc0357_1352328158425_174981_20253_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_edc0357_1352328158425_174981_20253_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))))));
            }

            public void init_17_0_2_edc0357_1352328158428_668812_20261Elaborations() {
                init_17_0_2_edc0357_1352328158428_668812_20261Dependencies();
                Expression<?>[] arguments111 = new Expression<?>[1];
                arguments111[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition111 = new Expression<Boolean>(_17_0_2_edc0357_1352328158426_903101_20256_exists);
                elaborationRule111 = addElaborationRule(condition111, _17_0_2_edc0357_1352328156639_660827_19616.this, Customer._17_0_2_edc0357_1352328156639_660827_19616._17_0_2_edc0357_1352328158426_903101_20256.class, "CChange_calc_capmin_CallBehaviorAction_changePowerUsage", arguments111);
                Expression<?>[] arguments112 = new Expression<?>[1];
                arguments112[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition112 = new Expression<Boolean>(_17_0_2_edc0357_1352328158428_38684_20262_exists);
                elaborationRule112 = addElaborationRule(condition112, _17_0_2_edc0357_1352328156639_660827_19616.this, Customer._17_0_2_edc0357_1352328156639_660827_19616._17_0_2_edc0357_1352328158428_38684_20262.class, "CChange_decidecap_DecisionNode_changePowerUsage", arguments112);
            }
        }

        public class _17_0_2_edc0357_1352328158428_38684_20262 extends DurativeEvent {

            public _17_0_2_edc0357_1352328158428_38684_20262() {
                super();
                init_17_0_2_edc0357_1352328158428_38684_20262Members();
                init_17_0_2_edc0357_1352328158428_38684_20262Collections();
                init_17_0_2_edc0357_1352328158428_38684_20262Elaborations();
            }

            public _17_0_2_edc0357_1352328158428_38684_20262(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328158428_38684_20262Members();
                init_17_0_2_edc0357_1352328158428_38684_20262Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328158428_38684_20262Elaborations();
                fixTimeDependencies();
            }

            public IntegerParameter myDeciderID_decider_17_0_2_edc0357_1352328158427_744964_20260 = null;

            public IntegerParameter myDeciderID_decider_17_0_2_edc0357_1352328158426_903101_20256 = null;

            public BooleanParameter objectToPass = null;

            public BooleanParameter _17_0_2_edc0357_1352328158426_903101_20256_exists = null;

            public IntegerParameter decisionInput = null;

            public BooleanParameter _17_0_2_edc0357_1352328158427_744964_20260_exists = null;

            public Dependency< Integer > myDeciderID_decider_17_0_2_edc0357_1352328158427_744964_20260Dependency = null;

            public Dependency< Integer > myDeciderID_decider_17_0_2_edc0357_1352328158426_903101_20256Dependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158426_903101_20256_existsDependency = null;

            public Dependency< Integer > decisionInputDependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158427_744964_20260_existsDependency = null;

            public Effect effect113 = null;

            public Parameter effect113Var = null;

            public Effect effect114 = null;

            public Parameter effect114Var = null;

            public Effect effect115 = null;

            public Parameter effect115Var = null;

            public Effect effect116 = null;

            public Parameter effect116Var = null;

            public ElaborationRule elaborationRule117 = null;

            public ElaborationRule elaborationRule118 = null;

            public void init_17_0_2_edc0357_1352328158428_38684_20262Members() {
                try {
                    if (myDeciderID_decider_17_0_2_edc0357_1352328158427_744964_20260 == null) myDeciderID_decider_17_0_2_edc0357_1352328158427_744964_20260 = new IntegerParameter("myDeciderID_decider_17_0_2_edc0357_1352328158427_744964_20260", (Integer) 2, this);
                    if (myDeciderID_decider_17_0_2_edc0357_1352328158426_903101_20256 == null) myDeciderID_decider_17_0_2_edc0357_1352328158426_903101_20256 = new IntegerParameter("myDeciderID_decider_17_0_2_edc0357_1352328158426_903101_20256", (Integer) 3, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (_17_0_2_edc0357_1352328158426_903101_20256_exists == null) _17_0_2_edc0357_1352328158426_903101_20256_exists = new BooleanParameter("_17_0_2_edc0357_1352328158426_903101_20256_exists", (Boolean) false, this);
                    if (decisionInput == null) decisionInput = new IntegerParameter("decisionInput", (Integer) null, this);
                    if (_17_0_2_edc0357_1352328158427_744964_20260_exists == null) _17_0_2_edc0357_1352328158427_744964_20260_exists = new BooleanParameter("_17_0_2_edc0357_1352328158427_744964_20260_exists", (Boolean) false, this);
                    Object effect113VarV = sig_17_0_2_edc0357_1352328158431_806383_20280;
                    effect113Var = new Parameter("effect113Var", null, null, this);
                    addDependency(effect113Var, new Expression(effect113VarV));
                    effect113 = new EffectFunction(new EffectFunction(effect113Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "sendIf", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class, java.lang.Boolean.class), new Object[] { objectToPass, endTime, _17_0_2_edc0357_1352328158426_903101_20256_exists }));
                    Object effect114VarV = sig_17_0_2_edc0357_1352328158431_279190_20281;
                    effect114Var = new Parameter("effect114Var", null, null, this);
                    addDependency(effect114Var, new Expression(effect114VarV));
                    effect114 = new EffectFunction(new EffectFunction(effect114Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "sendIf", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class, java.lang.Boolean.class), new Object[] { objectToPass, endTime, _17_0_2_edc0357_1352328158427_744964_20260_exists }));
                    Object effect115VarV = decider_17_0_2_edc0357_1352328158426_903101_20256;
                    effect115Var = new Parameter("effect115Var", null, null, this);
                    addDependency(effect115Var, new Expression(effect115VarV));
                    effect115 = new EffectFunction(new EffectFunction(effect115Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, myDeciderID_decider_17_0_2_edc0357_1352328158426_903101_20256 }));
                    Object effect116VarV = decider_17_0_2_edc0357_1352328158427_744964_20260;
                    effect116Var = new Parameter("effect116Var", null, null, this);
                    addDependency(effect116Var, new Expression(effect116VarV));
                    effect116 = new EffectFunction(new EffectFunction(effect116Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, myDeciderID_decider_17_0_2_edc0357_1352328158427_744964_20260 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158428_38684_20262Collections() {
                parameters.add(myDeciderID_decider_17_0_2_edc0357_1352328158427_744964_20260);
                parameters.add(myDeciderID_decider_17_0_2_edc0357_1352328158426_903101_20256);
                parameters.add(objectToPass);
                parameters.add(_17_0_2_edc0357_1352328158426_903101_20256_exists);
                parameters.add(decisionInput);
                parameters.add(_17_0_2_edc0357_1352328158427_744964_20260_exists);
                Set<Effect> effectsForeffect113Var = new TreeSet<Effect>();
                effectsForeffect113Var.add(effect113);
                addEffects((Parameter<?>) effect113Var, effectsForeffect113Var);
                Set<Effect> effectsForeffect114Var = new TreeSet<Effect>();
                effectsForeffect114Var.add(effect114);
                addEffects((Parameter<?>) effect114Var, effectsForeffect114Var);
                Set<Effect> effectsForeffect115Var = new TreeSet<Effect>();
                effectsForeffect115Var.add(effect115);
                addEffects((Parameter<?>) effect115Var, effectsForeffect115Var);
                Set<Effect> effectsForeffect116Var = new TreeSet<Effect>();
                effectsForeffect116Var.add(effect116);
                addEffects((Parameter<?>) effect116Var, effectsForeffect116Var);
            }

            public void init_17_0_2_edc0357_1352328158428_38684_20262Dependencies() {
                addDependency(myDeciderID_decider_17_0_2_edc0357_1352328158427_744964_20260, new Expression<Integer>(2));
                addDependency(myDeciderID_decider_17_0_2_edc0357_1352328158426_903101_20256, new Expression<Integer>(3));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new EffectFunction(sig_17_0_2_edc0357_1352328158430_630436_20276, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_edc0357_1352328158426_903101_20256_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Greater(new Expression<Integer>(decisionInput), new Expression<Integer>(0))), new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158426_903101_20256, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158426_903101_20256, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158426_903101_20256, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_edc0357_1352328158426_903101_20256)))))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_edc0357_1352328158425_174981_20253_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_edc0357_1352328158425_174981_20253_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))))));
                addDependency(decisionInput, new Expression<Integer>(new EffectFunction(sig_17_0_2_edc0357_1352328158430_40918_20278, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_edc0357_1352328158427_744964_20260_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.LessEquals(new Expression<Integer>(decisionInput), new Expression<Integer>(0))), new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158427_744964_20260, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158427_744964_20260, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158427_744964_20260, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_edc0357_1352328158427_744964_20260)))))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_edc0357_1352328158425_174981_20253_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_edc0357_1352328158425_174981_20253_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))))));
            }

            public void init_17_0_2_edc0357_1352328158428_38684_20262Elaborations() {
                init_17_0_2_edc0357_1352328158428_38684_20262Dependencies();
                Expression<?>[] arguments117 = new Expression<?>[1];
                arguments117[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition117 = new Expression<Boolean>(_17_0_2_edc0357_1352328158426_903101_20256_exists);
                elaborationRule117 = addElaborationRule(condition117, _17_0_2_edc0357_1352328156639_660827_19616.this, Customer._17_0_2_edc0357_1352328156639_660827_19616._17_0_2_edc0357_1352328158426_903101_20256.class, "CChange_calc_capmin_CallBehaviorAction_changePowerUsage", arguments117);
                Expression<?>[] arguments118 = new Expression<?>[1];
                arguments118[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition118 = new Expression<Boolean>(_17_0_2_edc0357_1352328158427_744964_20260_exists);
                elaborationRule118 = addElaborationRule(condition118, _17_0_2_edc0357_1352328156639_660827_19616.this, Customer._17_0_2_edc0357_1352328156639_660827_19616._17_0_2_edc0357_1352328158427_744964_20260.class, "CChange_calc_desired_CallBehaviorAction_changePowerUsage", arguments118);
            }
        }

        public class _17_0_2_edc0357_1352328158428_316417_20263 extends DurativeEvent {

            public _17_0_2_edc0357_1352328158428_316417_20263() {
                super();
                init_17_0_2_edc0357_1352328158428_316417_20263Members();
                init_17_0_2_edc0357_1352328158428_316417_20263Collections();
                init_17_0_2_edc0357_1352328158428_316417_20263Elaborations();
            }

            public _17_0_2_edc0357_1352328158428_316417_20263(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328158428_316417_20263Members();
                init_17_0_2_edc0357_1352328158428_316417_20263Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328158428_316417_20263Elaborations();
                fixTimeDependencies();
            }

            public IntegerParameter myDeciderID_decider_17_0_2_edc0357_1352328158427_744964_20260 = null;

            public IntegerParameter myDeciderID_decider_17_0_2_edc0357_1352328158426_903101_20256 = null;

            public IntegerParameter objectToPass = null;

            public BooleanParameter _17_0_2_edc0357_1352328158426_903101_20256_exists = null;

            public BooleanParameter _17_0_2_edc0357_1352328158427_744964_20260_exists = null;

            public Dependency< Integer > myDeciderID_decider_17_0_2_edc0357_1352328158427_744964_20260Dependency = null;

            public Dependency< Integer > myDeciderID_decider_17_0_2_edc0357_1352328158426_903101_20256Dependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Integer > objectToPassDependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158426_903101_20256_existsDependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158427_744964_20260_existsDependency = null;

            public Effect effect119 = null;

            public Parameter effect119Var = null;

            public Effect effect120 = null;

            public Parameter effect120Var = null;

            public Effect effect121 = null;

            public Parameter effect121Var = null;

            public Effect effect122 = null;

            public Parameter effect122Var = null;

            public ElaborationRule elaborationRule123 = null;

            public ElaborationRule elaborationRule124 = null;

            public void init_17_0_2_edc0357_1352328158428_316417_20263Members() {
                try {
                    if (myDeciderID_decider_17_0_2_edc0357_1352328158427_744964_20260 == null) myDeciderID_decider_17_0_2_edc0357_1352328158427_744964_20260 = new IntegerParameter("myDeciderID_decider_17_0_2_edc0357_1352328158427_744964_20260", (Integer) 1, this);
                    if (myDeciderID_decider_17_0_2_edc0357_1352328158426_903101_20256 == null) myDeciderID_decider_17_0_2_edc0357_1352328158426_903101_20256 = new IntegerParameter("myDeciderID_decider_17_0_2_edc0357_1352328158426_903101_20256", (Integer) 1, this);
                    if (objectToPass == null) objectToPass = new IntegerParameter("objectToPass", (Integer) null, this);
                    if (_17_0_2_edc0357_1352328158426_903101_20256_exists == null) _17_0_2_edc0357_1352328158426_903101_20256_exists = new BooleanParameter("_17_0_2_edc0357_1352328158426_903101_20256_exists", (Boolean) false, this);
                    if (_17_0_2_edc0357_1352328158427_744964_20260_exists == null) _17_0_2_edc0357_1352328158427_744964_20260_exists = new BooleanParameter("_17_0_2_edc0357_1352328158427_744964_20260_exists", (Boolean) false, this);
                    Object effect119VarV = sig_17_0_2_edc0357_1352328158431_868302_20283;
                    effect119Var = new Parameter("effect119Var", null, null, this);
                    addDependency(effect119Var, new Expression(effect119VarV));
                    effect119 = new EffectFunction(new EffectFunction(effect119Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect120VarV = sig_17_0_2_edc0357_1352328158431_555149_20284;
                    effect120Var = new Parameter("effect120Var", null, null, this);
                    addDependency(effect120Var, new Expression(effect120VarV));
                    effect120 = new EffectFunction(new EffectFunction(effect120Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect121VarV = decider_17_0_2_edc0357_1352328158426_903101_20256;
                    effect121Var = new Parameter("effect121Var", null, null, this);
                    addDependency(effect121Var, new Expression(effect121VarV));
                    effect121 = new EffectFunction(new EffectFunction(effect121Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, myDeciderID_decider_17_0_2_edc0357_1352328158426_903101_20256 }));
                    Object effect122VarV = decider_17_0_2_edc0357_1352328158427_744964_20260;
                    effect122Var = new Parameter("effect122Var", null, null, this);
                    addDependency(effect122Var, new Expression(effect122VarV));
                    effect122 = new EffectFunction(new EffectFunction(effect122Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, myDeciderID_decider_17_0_2_edc0357_1352328158427_744964_20260 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158428_316417_20263Collections() {
                parameters.add(myDeciderID_decider_17_0_2_edc0357_1352328158427_744964_20260);
                parameters.add(myDeciderID_decider_17_0_2_edc0357_1352328158426_903101_20256);
                parameters.add(objectToPass);
                parameters.add(_17_0_2_edc0357_1352328158426_903101_20256_exists);
                parameters.add(_17_0_2_edc0357_1352328158427_744964_20260_exists);
                Set<Effect> effectsForeffect119Var = new TreeSet<Effect>();
                effectsForeffect119Var.add(effect119);
                addEffects((Parameter<?>) effect119Var, effectsForeffect119Var);
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

            public void init_17_0_2_edc0357_1352328158428_316417_20263Dependencies() {
                addDependency(myDeciderID_decider_17_0_2_edc0357_1352328158427_744964_20260, new Expression<Integer>(1));
                addDependency(myDeciderID_decider_17_0_2_edc0357_1352328158426_903101_20256, new Expression<Integer>(1));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Integer>(new EffectFunction(sig_17_0_2_edc0357_1352328158431_275687_20282, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_edc0357_1352328158426_903101_20256_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158426_903101_20256, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158426_903101_20256, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158426_903101_20256, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_edc0357_1352328158426_903101_20256)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_edc0357_1352328158425_174981_20253_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_edc0357_1352328158425_174981_20253_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))))));
                addDependency(_17_0_2_edc0357_1352328158427_744964_20260_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158427_744964_20260, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158427_744964_20260, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158427_744964_20260, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_edc0357_1352328158427_744964_20260)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_edc0357_1352328158425_174981_20253_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_edc0357_1352328158425_174981_20253_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))))));
            }

            public void init_17_0_2_edc0357_1352328158428_316417_20263Elaborations() {
                init_17_0_2_edc0357_1352328158428_316417_20263Dependencies();
                Expression<?>[] arguments123 = new Expression<?>[1];
                arguments123[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition123 = new Expression<Boolean>(_17_0_2_edc0357_1352328158426_903101_20256_exists);
                elaborationRule123 = addElaborationRule(condition123, _17_0_2_edc0357_1352328156639_660827_19616.this, Customer._17_0_2_edc0357_1352328156639_660827_19616._17_0_2_edc0357_1352328158426_903101_20256.class, "CChange_calc_capmin_CallBehaviorAction_changePowerUsage", arguments123);
                Expression<?>[] arguments124 = new Expression<?>[1];
                arguments124[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition124 = new Expression<Boolean>(_17_0_2_edc0357_1352328158427_744964_20260_exists);
                elaborationRule124 = addElaborationRule(condition124, _17_0_2_edc0357_1352328156639_660827_19616.this, Customer._17_0_2_edc0357_1352328156639_660827_19616._17_0_2_edc0357_1352328158427_744964_20260.class, "CChange_calc_desired_CallBehaviorAction_changePowerUsage", arguments124);
            }
        }

        public class _17_0_2_edc0357_1352328158428_314626_20264 extends DurativeEvent {

            public _17_0_2_edc0357_1352328158428_314626_20264() {
                super();
                init_17_0_2_edc0357_1352328158428_314626_20264Members();
                init_17_0_2_edc0357_1352328158428_314626_20264Collections();
                init_17_0_2_edc0357_1352328158428_314626_20264Elaborations();
            }

            public _17_0_2_edc0357_1352328158428_314626_20264(Expression<Integer> startTime, Expression<ObjectFlow<Integer>> receiveThis) {
                super();
                init_17_0_2_edc0357_1352328158428_314626_20264Members();
                init_17_0_2_edc0357_1352328158428_314626_20264Collections();
                addDependency(this.startTime, startTime);
                addDependency(this.receiveThis, receiveThis);
                init_17_0_2_edc0357_1352328158428_314626_20264Elaborations();
                fixTimeDependencies();
            }

            public Parameter< ObjectFlow<Integer> > receiveThis = null;

            public BooleanParameter _17_0_2_edc0357_1352328158427_233123_20258_exists = null;

            public IntegerParameter objectToPass = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158427_233123_20258_existsDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Integer > objectToPassDependency = null;

            public Effect effect125 = null;

            public Parameter effect125Var = null;

            public ElaborationRule elaborationRule126 = null;

            public void init_17_0_2_edc0357_1352328158428_314626_20264Members() {
                try {
                    if (receiveThis == null) receiveThis = new Parameter<ObjectFlow<Integer>>("receiveThis", null, (ObjectFlow<Integer>) null, this);
                    if (_17_0_2_edc0357_1352328158427_233123_20258_exists == null) _17_0_2_edc0357_1352328158427_233123_20258_exists = new BooleanParameter("_17_0_2_edc0357_1352328158427_233123_20258_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new IntegerParameter("objectToPass", (Integer) null, this);
                    Object effect125VarV = sig_17_0_2_edc0357_1352328158431_643907_20287;
                    effect125Var = new Parameter("effect125Var", null, null, this);
                    addDependency(effect125Var, new Expression(effect125VarV));
                    effect125 = new EffectFunction(new EffectFunction(effect125Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158428_314626_20264Collections() {
                parameters.add(receiveThis);
                parameters.add(_17_0_2_edc0357_1352328158427_233123_20258_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect125Var = new TreeSet<Effect>();
                effectsForeffect125Var.add(effect125);
                addEffects((Parameter<?>) effect125Var, effectsForeffect125Var);
            }

            public void init_17_0_2_edc0357_1352328158428_314626_20264Dependencies() {
                addDependency(_17_0_2_edc0357_1352328158427_233123_20258_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_edc0357_1352328158425_174981_20253_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_edc0357_1352328158425_174981_20253_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Integer>(new EffectFunction(receiveThis, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_edc0357_1352328158428_314626_20264Elaborations() {
                init_17_0_2_edc0357_1352328158428_314626_20264Dependencies();
                Expression<?>[] arguments126 = new Expression<?>[1];
                arguments126[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition126 = new Expression<Boolean>(_17_0_2_edc0357_1352328158427_233123_20258_exists);
                elaborationRule126 = addElaborationRule(condition126, _17_0_2_edc0357_1352328156639_660827_19616.this, Customer._17_0_2_edc0357_1352328156639_660827_19616._17_0_2_edc0357_1352328158427_233123_20258.class, "CChange_forkchangeto_ForkNode_changePowerUsage", arguments126);
            }
        }

        public _17_0_2_edc0357_1352328156639_660827_19616(Expression<Integer> startTime, Expression<DurativeEvent> caller) {
            super();
            init_17_0_2_edc0357_1352328156639_660827_19616Members();
            init_17_0_2_edc0357_1352328156639_660827_19616Collections();
            addDependency(this.startTime, startTime);
            addDependency(this.caller, caller);
            init_17_0_2_edc0357_1352328156639_660827_19616Elaborations();
            fixTimeDependencies();
        }

        public Parameter< DurativeEvent > caller = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_2_edc0357_1352328158430_389607_20279 = null;

        public BooleanParameter _17_0_2_edc0357_1352328158425_174981_20253_exists = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_2_edc0357_1352328160546_168484_21406 = null;

        public Parameter< TimeVaryingList<Integer> > decider_17_0_2_edc0357_1352328158425_844513_20251 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_2_edc0357_1352328158431_441018_20285 = null;

        public Parameter< ObjectFlow<Customer> > sig_17_0_2_edc0357_1352328158429_395232_20267 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_2_edc0357_1352328158431_643907_20287 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_edc0357_1352328158429_418011_20269 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_2_edc0357_1352328158429_532173_20270 = null;

        public Parameter< TimeVaryingList<Integer> > decider_17_0_2_edc0357_1352328158427_744964_20260 = null;

        public Parameter< TimeVaryingList<Integer> > decider_17_0_2_edc0357_1352328158426_116757_20257 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_2_edc0357_1352328158430_385741_20271 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_2_edc0357_1352328158430_538124_20277 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_edc0357_1352328158430_24745_20272 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_2_edc0357_1352328160546_596952_21405 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_edc0357_1352328158431_279190_20281 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_2_edc0357_1352328158431_555149_20284 = null;

        public Parameter< TimeVaryingList<Integer> > decider_17_0_2_edc0357_1352328158426_903101_20256 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_edc0357_1352328158430_913443_20274 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_edc0357_1352328158430_229342_20273 = null;

        public IntegerParameter finalNode_endTime = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_edc0357_1352328158431_806383_20280 = null;

        public Parameter< TimeVaryingList<Integer> > decider_17_0_2_edc0357_1352328158428_38684_20262 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_2_edc0357_1352328158431_565981_20286 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_edc0357_1352328158430_934674_20275 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_2_edc0357_1352328158431_868302_20283 = null;

        public IntegerParameter finalNode_startTime = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_edc0357_1352328158430_630436_20276 = null;

        public Parameter< ObjectFlow<Customer> > sig_17_0_2_edc0357_1352328158429_502495_20265 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_2_edc0357_1352328158430_40918_20278 = null;

        public Parameter< ObjectFlow<Customer> > sig_17_0_2_edc0357_1352328158429_694477_20266 = null;

        public Parameter< ObjectFlow<Customer> > sig_17_0_2_edc0357_1352328158429_197012_20268 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_2_edc0357_1352328158431_275687_20282 = null;

        public Dependency< Integer > caller_endTimeDependency = null;

        public Dependency< Boolean > _17_0_2_edc0357_1352328158425_174981_20253_existsDependency = null;

        public Dependency< Integer > endTimeDependency = null;

        public ElaborationRule elaborationRule65 = null;

        public ElaborationRule elaborationRule66 = null;

        public void init_17_0_2_edc0357_1352328156639_660827_19616Members() {
            try {
                if (caller == null) caller = new Parameter<DurativeEvent>("caller", null, (DurativeEvent) null, this);
                if (sig_17_0_2_edc0357_1352328158430_389607_20279 == null) sig_17_0_2_edc0357_1352328158430_389607_20279 = new Parameter<ObjectFlow<Integer>>("sig_17_0_2_edc0357_1352328158430_389607_20279", null, (ObjectFlow<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158430_389607_20279" })).evaluate(true), this);
                if (_17_0_2_edc0357_1352328158425_174981_20253_exists == null) _17_0_2_edc0357_1352328158425_174981_20253_exists = new BooleanParameter("_17_0_2_edc0357_1352328158425_174981_20253_exists", (Boolean) false, this);
                if (sig_17_0_2_edc0357_1352328160546_168484_21406 == null) sig_17_0_2_edc0357_1352328160546_168484_21406 = new Parameter<ObjectFlow<Integer>>("sig_17_0_2_edc0357_1352328160546_168484_21406", null, (ObjectFlow<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328160546_168484_21406" })).evaluate(true), this);
                if (decider_17_0_2_edc0357_1352328158425_844513_20251 == null) decider_17_0_2_edc0357_1352328158425_844513_20251 = new Parameter<TimeVaryingList<Integer>>("decider_17_0_2_edc0357_1352328158425_844513_20251", null, (TimeVaryingList<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingList.class, java.lang.String.class, int.class), new Object[] { "decider_17_0_2_edc0357_1352328158425_844513_20251", 2 })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328158431_441018_20285 == null) sig_17_0_2_edc0357_1352328158431_441018_20285 = new Parameter<ObjectFlow<Integer>>("sig_17_0_2_edc0357_1352328158431_441018_20285", null, (ObjectFlow<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158431_441018_20285" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328158429_395232_20267 == null) sig_17_0_2_edc0357_1352328158429_395232_20267 = new Parameter<ObjectFlow<Customer>>("sig_17_0_2_edc0357_1352328158429_395232_20267", null, (ObjectFlow<Customer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158429_395232_20267" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328158431_643907_20287 == null) sig_17_0_2_edc0357_1352328158431_643907_20287 = new Parameter<ObjectFlow<Integer>>("sig_17_0_2_edc0357_1352328158431_643907_20287", null, (ObjectFlow<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158431_643907_20287" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328158429_418011_20269 == null) sig_17_0_2_edc0357_1352328158429_418011_20269 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328158429_418011_20269", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158429_418011_20269" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328158429_532173_20270 == null) sig_17_0_2_edc0357_1352328158429_532173_20270 = new Parameter<ObjectFlow<Integer>>("sig_17_0_2_edc0357_1352328158429_532173_20270", null, (ObjectFlow<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158429_532173_20270" })).evaluate(true), this);
                if (decider_17_0_2_edc0357_1352328158427_744964_20260 == null) decider_17_0_2_edc0357_1352328158427_744964_20260 = new Parameter<TimeVaryingList<Integer>>("decider_17_0_2_edc0357_1352328158427_744964_20260", null, (TimeVaryingList<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingList.class, java.lang.String.class, int.class), new Object[] { "decider_17_0_2_edc0357_1352328158427_744964_20260", 2 })).evaluate(true), this);
                if (decider_17_0_2_edc0357_1352328158426_116757_20257 == null) decider_17_0_2_edc0357_1352328158426_116757_20257 = new Parameter<TimeVaryingList<Integer>>("decider_17_0_2_edc0357_1352328158426_116757_20257", null, (TimeVaryingList<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingList.class, java.lang.String.class, int.class), new Object[] { "decider_17_0_2_edc0357_1352328158426_116757_20257", 3 })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328158430_385741_20271 == null) sig_17_0_2_edc0357_1352328158430_385741_20271 = new Parameter<ObjectFlow<Integer>>("sig_17_0_2_edc0357_1352328158430_385741_20271", null, (ObjectFlow<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158430_385741_20271" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328158430_538124_20277 == null) sig_17_0_2_edc0357_1352328158430_538124_20277 = new Parameter<ObjectFlow<Integer>>("sig_17_0_2_edc0357_1352328158430_538124_20277", null, (ObjectFlow<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158430_538124_20277" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328158430_24745_20272 == null) sig_17_0_2_edc0357_1352328158430_24745_20272 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328158430_24745_20272", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158430_24745_20272" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328160546_596952_21405 == null) sig_17_0_2_edc0357_1352328160546_596952_21405 = new Parameter<ObjectFlow<Integer>>("sig_17_0_2_edc0357_1352328160546_596952_21405", null, (ObjectFlow<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328160546_596952_21405" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328158431_279190_20281 == null) sig_17_0_2_edc0357_1352328158431_279190_20281 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328158431_279190_20281", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158431_279190_20281" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328158431_555149_20284 == null) sig_17_0_2_edc0357_1352328158431_555149_20284 = new Parameter<ObjectFlow<Integer>>("sig_17_0_2_edc0357_1352328158431_555149_20284", null, (ObjectFlow<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158431_555149_20284" })).evaluate(true), this);
                if (decider_17_0_2_edc0357_1352328158426_903101_20256 == null) decider_17_0_2_edc0357_1352328158426_903101_20256 = new Parameter<TimeVaryingList<Integer>>("decider_17_0_2_edc0357_1352328158426_903101_20256", null, (TimeVaryingList<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingList.class, java.lang.String.class, int.class), new Object[] { "decider_17_0_2_edc0357_1352328158426_903101_20256", 3 })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328158430_913443_20274 == null) sig_17_0_2_edc0357_1352328158430_913443_20274 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328158430_913443_20274", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158430_913443_20274" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328158430_229342_20273 == null) sig_17_0_2_edc0357_1352328158430_229342_20273 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328158430_229342_20273", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158430_229342_20273" })).evaluate(true), this);
                if (finalNode_endTime == null) finalNode_endTime = new IntegerParameter("finalNode_endTime", (Integer) null, this);
                if (sig_17_0_2_edc0357_1352328158431_806383_20280 == null) sig_17_0_2_edc0357_1352328158431_806383_20280 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328158431_806383_20280", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158431_806383_20280" })).evaluate(true), this);
                if (decider_17_0_2_edc0357_1352328158428_38684_20262 == null) decider_17_0_2_edc0357_1352328158428_38684_20262 = new Parameter<TimeVaryingList<Integer>>("decider_17_0_2_edc0357_1352328158428_38684_20262", null, (TimeVaryingList<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingList.class, java.lang.String.class, int.class), new Object[] { "decider_17_0_2_edc0357_1352328158428_38684_20262", 2 })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328158431_565981_20286 == null) sig_17_0_2_edc0357_1352328158431_565981_20286 = new Parameter<ObjectFlow<Integer>>("sig_17_0_2_edc0357_1352328158431_565981_20286", null, (ObjectFlow<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158431_565981_20286" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328158430_934674_20275 == null) sig_17_0_2_edc0357_1352328158430_934674_20275 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328158430_934674_20275", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158430_934674_20275" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328158431_868302_20283 == null) sig_17_0_2_edc0357_1352328158431_868302_20283 = new Parameter<ObjectFlow<Integer>>("sig_17_0_2_edc0357_1352328158431_868302_20283", null, (ObjectFlow<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158431_868302_20283" })).evaluate(true), this);
                if (finalNode_startTime == null) finalNode_startTime = new IntegerParameter("finalNode_startTime", (Integer) null, this);
                if (sig_17_0_2_edc0357_1352328158430_630436_20276 == null) sig_17_0_2_edc0357_1352328158430_630436_20276 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328158430_630436_20276", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158430_630436_20276" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328158429_502495_20265 == null) sig_17_0_2_edc0357_1352328158429_502495_20265 = new Parameter<ObjectFlow<Customer>>("sig_17_0_2_edc0357_1352328158429_502495_20265", null, (ObjectFlow<Customer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158429_502495_20265" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328158430_40918_20278 == null) sig_17_0_2_edc0357_1352328158430_40918_20278 = new Parameter<ObjectFlow<Integer>>("sig_17_0_2_edc0357_1352328158430_40918_20278", null, (ObjectFlow<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158430_40918_20278" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328158429_694477_20266 == null) sig_17_0_2_edc0357_1352328158429_694477_20266 = new Parameter<ObjectFlow<Customer>>("sig_17_0_2_edc0357_1352328158429_694477_20266", null, (ObjectFlow<Customer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158429_694477_20266" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328158429_197012_20268 == null) sig_17_0_2_edc0357_1352328158429_197012_20268 = new Parameter<ObjectFlow<Customer>>("sig_17_0_2_edc0357_1352328158429_197012_20268", null, (ObjectFlow<Customer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158429_197012_20268" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328158431_275687_20282 == null) sig_17_0_2_edc0357_1352328158431_275687_20282 = new Parameter<ObjectFlow<Integer>>("sig_17_0_2_edc0357_1352328158431_275687_20282", null, (ObjectFlow<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158431_275687_20282" })).evaluate(true), this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void init_17_0_2_edc0357_1352328156639_660827_19616Collections() {
            parameters.add(caller);
            parameters.add(sig_17_0_2_edc0357_1352328158430_389607_20279);
            parameters.add(_17_0_2_edc0357_1352328158425_174981_20253_exists);
            parameters.add(sig_17_0_2_edc0357_1352328160546_168484_21406);
            parameters.add(decider_17_0_2_edc0357_1352328158425_844513_20251);
            parameters.add(sig_17_0_2_edc0357_1352328158431_441018_20285);
            parameters.add(sig_17_0_2_edc0357_1352328158429_395232_20267);
            parameters.add(sig_17_0_2_edc0357_1352328158431_643907_20287);
            parameters.add(sig_17_0_2_edc0357_1352328158429_418011_20269);
            parameters.add(sig_17_0_2_edc0357_1352328158429_532173_20270);
            parameters.add(decider_17_0_2_edc0357_1352328158427_744964_20260);
            parameters.add(decider_17_0_2_edc0357_1352328158426_116757_20257);
            parameters.add(sig_17_0_2_edc0357_1352328158430_385741_20271);
            parameters.add(sig_17_0_2_edc0357_1352328158430_538124_20277);
            parameters.add(sig_17_0_2_edc0357_1352328158430_24745_20272);
            parameters.add(sig_17_0_2_edc0357_1352328160546_596952_21405);
            parameters.add(sig_17_0_2_edc0357_1352328158431_279190_20281);
            parameters.add(sig_17_0_2_edc0357_1352328158431_555149_20284);
            parameters.add(decider_17_0_2_edc0357_1352328158426_903101_20256);
            parameters.add(sig_17_0_2_edc0357_1352328158430_913443_20274);
            parameters.add(sig_17_0_2_edc0357_1352328158430_229342_20273);
            parameters.add(finalNode_endTime);
            parameters.add(sig_17_0_2_edc0357_1352328158431_806383_20280);
            parameters.add(decider_17_0_2_edc0357_1352328158428_38684_20262);
            parameters.add(sig_17_0_2_edc0357_1352328158431_565981_20286);
            parameters.add(sig_17_0_2_edc0357_1352328158430_934674_20275);
            parameters.add(sig_17_0_2_edc0357_1352328158431_868302_20283);
            parameters.add(finalNode_startTime);
            parameters.add(sig_17_0_2_edc0357_1352328158430_630436_20276);
            parameters.add(sig_17_0_2_edc0357_1352328158429_502495_20265);
            parameters.add(sig_17_0_2_edc0357_1352328158430_40918_20278);
            parameters.add(sig_17_0_2_edc0357_1352328158429_694477_20266);
            parameters.add(sig_17_0_2_edc0357_1352328158429_197012_20268);
            parameters.add(sig_17_0_2_edc0357_1352328158431_275687_20282);
        }

        public void init_17_0_2_edc0357_1352328156639_660827_19616Dependencies() {
            caller.getValue(true).addDependency(caller.getValue(true).endTime, new Expression<Integer>(finalNode_endTime));
            addDependency(_17_0_2_edc0357_1352328158425_174981_20253_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.NotEquals(new Expression<Integer>(finalNode_startTime), null)), new Expression(new EffectFunction(sig_17_0_2_edc0357_1352328158429_418011_20269, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(new Functions.Minus(new Expression<Integer>(finalNode_startTime), new Expression<Integer>(1))) })))));
            addDependency(endTime, new Expression<Integer>(finalNode_endTime));
        }

        public void init_17_0_2_edc0357_1352328156639_660827_19616Elaborations() {
            init_17_0_2_edc0357_1352328156639_660827_19616Dependencies();
            Expression<?>[] arguments65 = new Expression<?>[1];
            arguments65[0] = new Expression<Integer>(finalNode_startTime);
            Expression<Boolean> condition65 = new Expression<Boolean>(_17_0_2_edc0357_1352328158425_174981_20253_exists);
            elaborationRule65 = addElaborationRule(condition65, _17_0_2_edc0357_1352328156639_660827_19616.this, Customer._17_0_2_edc0357_1352328156639_660827_19616._17_0_2_edc0357_1352328158425_174981_20253.class, "CChange_end_ActivityFinalNode_changePowerUsage", arguments65);
            Expression<?>[] arguments66 = new Expression<?>[1];
            arguments66[0] = new Expression<Integer>(startTime);
            Expression<Boolean> condition66 = new Expression<Boolean>(true);
            elaborationRule66 = addElaborationRule(condition66, _17_0_2_edc0357_1352328156639_660827_19616.this, Customer._17_0_2_edc0357_1352328156639_660827_19616._17_0_2_edc0357_1352328158425_23694_20252.class, "CChange_start_InitialNode_changePowerUsage", arguments66);
        }
    }

    public class _17_0_2_edc0357_1352328156644_264215_19617 extends DurativeEvent {

        public _17_0_2_edc0357_1352328156644_264215_19617() {
            super();
            init_17_0_2_edc0357_1352328156644_264215_19617Members();
            init_17_0_2_edc0357_1352328156644_264215_19617Collections();
            init_17_0_2_edc0357_1352328156644_264215_19617Elaborations();
        }

        public class _17_0_2_edc0357_1352328158532_777314_20319 extends DurativeEvent {

            public _17_0_2_edc0357_1352328158532_777314_20319() {
                super();
                init_17_0_2_edc0357_1352328158532_777314_20319Members();
                init_17_0_2_edc0357_1352328158532_777314_20319Collections();
                init_17_0_2_edc0357_1352328158532_777314_20319Elaborations();
            }

            public _17_0_2_edc0357_1352328158532_777314_20319(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328158532_777314_20319Members();
                init_17_0_2_edc0357_1352328158532_777314_20319Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328158532_777314_20319Elaborations();
                fixTimeDependencies();
            }

            public Parameter< Customer > _17_0_2_edc0357_1352328160561_291373_21448 = null;

            public BooleanParameter _17_0_2_edc0357_1352328158533_247450_20324_exists = null;

            public BooleanParameter objectToPass = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158533_247450_20324_existsDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Effect effect129 = null;

            public Parameter effect129Var = null;

            public ElaborationRule elaborationRule130 = null;

            public void init_17_0_2_edc0357_1352328158532_777314_20319Members() {
                try {
                    if (_17_0_2_edc0357_1352328160561_291373_21448 == null) _17_0_2_edc0357_1352328160561_291373_21448 = new Parameter<Customer>("_17_0_2_edc0357_1352328160561_291373_21448", null, (Customer) Customer.this, this);
                    if (_17_0_2_edc0357_1352328158533_247450_20324_exists == null) _17_0_2_edc0357_1352328158533_247450_20324_exists = new BooleanParameter("_17_0_2_edc0357_1352328158533_247450_20324_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect129VarV = sig_17_0_2_edc0357_1352328158534_268675_20330;
                    effect129Var = new Parameter("effect129Var", null, null, this);
                    addDependency(effect129Var, new Expression(effect129VarV));
                    effect129 = new EffectFunction(new EffectFunction(effect129Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { _17_0_2_edc0357_1352328160561_291373_21448, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158532_777314_20319Collections() {
                parameters.add(_17_0_2_edc0357_1352328160561_291373_21448);
                parameters.add(_17_0_2_edc0357_1352328158533_247450_20324_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect129Var = new TreeSet<Effect>();
                effectsForeffect129Var.add(effect129);
                addEffects((Parameter<?>) effect129Var, effectsForeffect129Var);
            }

            public void init_17_0_2_edc0357_1352328158532_777314_20319Dependencies() {
                addDependency(duration, new Expression<Integer>(1));
                addDependency(_17_0_2_edc0357_1352328158533_247450_20324_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_edc0357_1352328158533_74852_20323_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_edc0357_1352328158533_74852_20323_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(objectToPass, new Expression<Boolean>(new EffectFunction(sig_17_0_2_edc0357_1352328158535_401408_20338, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_edc0357_1352328158532_777314_20319Elaborations() {
                init_17_0_2_edc0357_1352328158532_777314_20319Dependencies();
                Expression<?>[] arguments130 = new Expression<?>[1];
                arguments130[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition130 = new Expression<Boolean>(_17_0_2_edc0357_1352328158533_247450_20324_exists);
                elaborationRule130 = addElaborationRule(condition130, _17_0_2_edc0357_1352328156644_264215_19617.this, Customer._17_0_2_edc0357_1352328156644_264215_19617._17_0_2_edc0357_1352328158533_247450_20324.class, "CI_forkself_ForkNode_initialize", arguments130);
            }
        }

        public class _17_0_2_edc0357_1352328158532_127805_20320 extends DurativeEvent {

            public _17_0_2_edc0357_1352328158532_127805_20320() {
                super();
                init_17_0_2_edc0357_1352328158532_127805_20320Members();
                init_17_0_2_edc0357_1352328158532_127805_20320Collections();
                init_17_0_2_edc0357_1352328158532_127805_20320Elaborations();
            }

            public _17_0_2_edc0357_1352328158532_127805_20320(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328158532_127805_20320Members();
                init_17_0_2_edc0357_1352328158532_127805_20320Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328158532_127805_20320Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_edc0357_1352328158532_578076_20321_exists = null;

            public BooleanParameter objectToPass = null;

            public IntegerParameter _17_0_2_edc0357_1352328160562_338541_21450 = null;

            public IntegerParameter myDeciderID_decider_17_0_2_edc0357_1352328158532_578076_20321 = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158532_578076_20321_existsDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Dependency< Integer > _17_0_2_edc0357_1352328160562_338541_21450Dependency = null;

            public Dependency< Integer > myDeciderID_decider_17_0_2_edc0357_1352328158532_578076_20321Dependency = null;

            public Effect effect131 = null;

            public Parameter effect131Var = null;

            public Effect effect132 = null;

            public Parameter effect132Var = null;

            public ElaborationRule elaborationRule133 = null;

            public void init_17_0_2_edc0357_1352328158532_127805_20320Members() {
                try {
                    if (_17_0_2_edc0357_1352328158532_578076_20321_exists == null) _17_0_2_edc0357_1352328158532_578076_20321_exists = new BooleanParameter("_17_0_2_edc0357_1352328158532_578076_20321_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (_17_0_2_edc0357_1352328160562_338541_21450 == null) _17_0_2_edc0357_1352328160562_338541_21450 = new IntegerParameter("_17_0_2_edc0357_1352328160562_338541_21450", (Integer) 4, this);
                    if (myDeciderID_decider_17_0_2_edc0357_1352328158532_578076_20321 == null) myDeciderID_decider_17_0_2_edc0357_1352328158532_578076_20321 = new IntegerParameter("myDeciderID_decider_17_0_2_edc0357_1352328158532_578076_20321", (Integer) 2, this);
                    Object effect131VarV = sig_17_0_2_edc0357_1352328158534_440722_20329;
                    effect131Var = new Parameter("effect131Var", null, null, this);
                    addDependency(effect131Var, new Expression(effect131VarV));
                    effect131 = new EffectFunction(new EffectFunction(effect131Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { _17_0_2_edc0357_1352328160562_338541_21450, endTime }));
                    Object effect132VarV = decider_17_0_2_edc0357_1352328158532_578076_20321;
                    effect132Var = new Parameter("effect132Var", null, null, this);
                    addDependency(effect132Var, new Expression(effect132VarV));
                    effect132 = new EffectFunction(new EffectFunction(effect132Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, myDeciderID_decider_17_0_2_edc0357_1352328158532_578076_20321 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158532_127805_20320Collections() {
                parameters.add(_17_0_2_edc0357_1352328158532_578076_20321_exists);
                parameters.add(objectToPass);
                parameters.add(_17_0_2_edc0357_1352328160562_338541_21450);
                parameters.add(myDeciderID_decider_17_0_2_edc0357_1352328158532_578076_20321);
                Set<Effect> effectsForeffect131Var = new TreeSet<Effect>();
                effectsForeffect131Var.add(effect131);
                addEffects((Parameter<?>) effect131Var, effectsForeffect131Var);
                Set<Effect> effectsForeffect132Var = new TreeSet<Effect>();
                effectsForeffect132Var.add(effect132);
                addEffects((Parameter<?>) effect132Var, effectsForeffect132Var);
            }

            public void init_17_0_2_edc0357_1352328158532_127805_20320Dependencies() {
                addDependency(_17_0_2_edc0357_1352328158532_578076_20321_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158532_578076_20321, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158532_578076_20321, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158532_578076_20321, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_edc0357_1352328158532_578076_20321)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_edc0357_1352328158533_74852_20323_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_edc0357_1352328158533_74852_20323_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new EffectFunction(sig_17_0_2_edc0357_1352328158535_735383_20339, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_edc0357_1352328160562_338541_21450, new Expression<Integer>(4));
                addDependency(myDeciderID_decider_17_0_2_edc0357_1352328158532_578076_20321, new Expression<Integer>(2));
            }

            public void init_17_0_2_edc0357_1352328158532_127805_20320Elaborations() {
                init_17_0_2_edc0357_1352328158532_127805_20320Dependencies();
                Expression<?>[] arguments133 = new Expression<?>[1];
                arguments133[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition133 = new Expression<Boolean>(_17_0_2_edc0357_1352328158532_578076_20321_exists);
                elaborationRule133 = addElaborationRule(condition133, _17_0_2_edc0357_1352328156644_264215_19617.this, Customer._17_0_2_edc0357_1352328156644_264215_19617._17_0_2_edc0357_1352328158532_578076_20321.class, "CI_setinitusage_AddStructuralFeatureValueAction_initialize", arguments133);
            }
        }

        public class _17_0_2_edc0357_1352328158532_578076_20321 extends DurativeEvent {

            public _17_0_2_edc0357_1352328158532_578076_20321() {
                super();
                init_17_0_2_edc0357_1352328158532_578076_20321Members();
                init_17_0_2_edc0357_1352328158532_578076_20321Collections();
                init_17_0_2_edc0357_1352328158532_578076_20321Elaborations();
            }

            public _17_0_2_edc0357_1352328158532_578076_20321(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328158532_578076_20321Members();
                init_17_0_2_edc0357_1352328158532_578076_20321Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328158532_578076_20321Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_edc0357_1352328158533_586682_20327_exists = null;

            public IntegerParameter myDeciderID_decider_17_0_2_edc0357_1352328158533_586682_20327 = null;

            public Parameter< Customer > _17_0_2_edc0357_1352328160563_822997_21452 = null;

            public BooleanParameter objectToPass = null;

            public IntegerParameter _17_0_2_edc0357_1352328160563_706626_21451 = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158533_586682_20327_existsDependency = null;

            public Dependency< Integer > myDeciderID_decider_17_0_2_edc0357_1352328158533_586682_20327Dependency = null;

            public Dependency< Customer > _17_0_2_edc0357_1352328160563_822997_21452Dependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Dependency< Integer > _17_0_2_edc0357_1352328160563_706626_21451Dependency = null;

            public Effect effect134 = null;

            public Parameter effect134Var = null;

            public Effect effect135 = null;

            public Parameter effect135Var = null;

            public Effect effect136 = null;

            public Parameter effect136Var = null;

            public ElaborationRule elaborationRule137 = null;

            public void init_17_0_2_edc0357_1352328158532_578076_20321Members() {
                try {
                    if (_17_0_2_edc0357_1352328158533_586682_20327_exists == null) _17_0_2_edc0357_1352328158533_586682_20327_exists = new BooleanParameter("_17_0_2_edc0357_1352328158533_586682_20327_exists", (Boolean) false, this);
                    if (myDeciderID_decider_17_0_2_edc0357_1352328158533_586682_20327 == null) myDeciderID_decider_17_0_2_edc0357_1352328158533_586682_20327 = new IntegerParameter("myDeciderID_decider_17_0_2_edc0357_1352328158533_586682_20327", (Integer) 1, this);
                    if (_17_0_2_edc0357_1352328160563_822997_21452 == null) _17_0_2_edc0357_1352328160563_822997_21452 = new Parameter<Customer>("_17_0_2_edc0357_1352328160563_822997_21452", null, (Customer) null, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (_17_0_2_edc0357_1352328160563_706626_21451 == null) _17_0_2_edc0357_1352328160563_706626_21451 = new IntegerParameter("_17_0_2_edc0357_1352328160563_706626_21451", (Integer) null, this);
                    Object effect134VarV = sig_17_0_2_edc0357_1352328158534_769162_20334;
                    effect134Var = new Parameter("effect134Var", null, null, this);
                    addDependency(effect134Var, new Expression(effect134VarV));
                    effect134 = new EffectFunction(new EffectFunction(effect134Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect135VarV = decider_17_0_2_edc0357_1352328158533_586682_20327;
                    effect135Var = new Parameter("effect135Var", null, null, this);
                    addDependency(effect135Var, new Expression(effect135VarV));
                    effect135 = new EffectFunction(new EffectFunction(effect135Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, myDeciderID_decider_17_0_2_edc0357_1352328158533_586682_20327 }));
                    Object effect136VarV = usage__17_0_2_edc0357_1352328156649_216766_19620;
                    effect136Var = new Parameter("effect136Var", null, null, this);
                    addDependency(effect136Var, new Expression(effect136VarV));
                    effect136 = new EffectFunction(new EffectFunction(effect136Var, ClassUtils.getMethodForArgTypes("TimeVaryingPlottableMap<Integer>", "powersystem", "setValue", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { startTime, _17_0_2_edc0357_1352328160563_706626_21451 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158532_578076_20321Collections() {
                parameters.add(_17_0_2_edc0357_1352328158533_586682_20327_exists);
                parameters.add(myDeciderID_decider_17_0_2_edc0357_1352328158533_586682_20327);
                parameters.add(_17_0_2_edc0357_1352328160563_822997_21452);
                parameters.add(objectToPass);
                parameters.add(_17_0_2_edc0357_1352328160563_706626_21451);
                Set<Effect> effectsForeffect134Var = new TreeSet<Effect>();
                effectsForeffect134Var.add(effect134);
                addEffects((Parameter<?>) effect134Var, effectsForeffect134Var);
                Set<Effect> effectsForeffect135Var = new TreeSet<Effect>();
                effectsForeffect135Var.add(effect135);
                addEffects((Parameter<?>) effect135Var, effectsForeffect135Var);
                Set<Effect> effectsForeffect136Var = new TreeSet<Effect>();
                effectsForeffect136Var.add(effect136);
                addEffects((Parameter<?>) effect136Var, effectsForeffect136Var);
            }

            public void init_17_0_2_edc0357_1352328158532_578076_20321Dependencies() {
                addDependency(_17_0_2_edc0357_1352328158533_586682_20327_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158533_586682_20327, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158533_586682_20327, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158533_586682_20327, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_edc0357_1352328158533_586682_20327)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_edc0357_1352328158533_74852_20323_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_edc0357_1352328158533_74852_20323_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))))));
                addDependency(myDeciderID_decider_17_0_2_edc0357_1352328158533_586682_20327, new Expression<Integer>(1));
                addDependency(_17_0_2_edc0357_1352328160563_822997_21452, new Expression<Customer>(new EffectFunction(sig_17_0_2_edc0357_1352328158534_699192_20331, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(true));
                addDependency(_17_0_2_edc0357_1352328160563_706626_21451, new Expression<Integer>(new EffectFunction(sig_17_0_2_edc0357_1352328158534_440722_20329, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_edc0357_1352328158532_578076_20321Elaborations() {
                init_17_0_2_edc0357_1352328158532_578076_20321Dependencies();
                Expression<?>[] arguments137 = new Expression<?>[1];
                arguments137[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition137 = new Expression<Boolean>(_17_0_2_edc0357_1352328158533_586682_20327_exists);
                elaborationRule137 = addElaborationRule(condition137, _17_0_2_edc0357_1352328156644_264215_19617.this, Customer._17_0_2_edc0357_1352328156644_264215_19617._17_0_2_edc0357_1352328158533_586682_20327.class, "CI_join_JoinNode_initialize", arguments137);
            }
        }

        public class _17_0_2_edc0357_1352328158532_946115_20322 extends DurativeEvent {

            public _17_0_2_edc0357_1352328158532_946115_20322() {
                super();
                init_17_0_2_edc0357_1352328158532_946115_20322Members();
                init_17_0_2_edc0357_1352328158532_946115_20322Collections();
                init_17_0_2_edc0357_1352328158532_946115_20322Elaborations();
            }

            public _17_0_2_edc0357_1352328158532_946115_20322(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328158532_946115_20322Members();
                init_17_0_2_edc0357_1352328158532_946115_20322Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328158532_946115_20322Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter objectToPass = null;

            public BooleanParameter _17_0_2_edc0357_1352328158533_797791_20328_exists = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158533_797791_20328_existsDependency = null;

            public Effect effect138 = null;

            public Parameter effect138Var = null;

            public ElaborationRule elaborationRule139 = null;

            public void init_17_0_2_edc0357_1352328158532_946115_20322Members() {
                try {
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (_17_0_2_edc0357_1352328158533_797791_20328_exists == null) _17_0_2_edc0357_1352328158533_797791_20328_exists = new BooleanParameter("_17_0_2_edc0357_1352328158533_797791_20328_exists", (Boolean) false, this);
                    Object effect138VarV = sig_17_0_2_edc0357_1352328158535_536324_20337;
                    effect138Var = new Parameter("effect138Var", null, null, this);
                    addDependency(effect138Var, new Expression(effect138VarV));
                    effect138 = new EffectFunction(new EffectFunction(effect138Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158532_946115_20322Collections() {
                parameters.add(objectToPass);
                parameters.add(_17_0_2_edc0357_1352328158533_797791_20328_exists);
                Set<Effect> effectsForeffect138Var = new TreeSet<Effect>();
                effectsForeffect138Var.add(effect138);
                addEffects((Parameter<?>) effect138Var, effectsForeffect138Var);
            }

            public void init_17_0_2_edc0357_1352328158532_946115_20322Dependencies() {
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(true));
                addDependency(_17_0_2_edc0357_1352328158533_797791_20328_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_edc0357_1352328158533_74852_20323_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_edc0357_1352328158533_74852_20323_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
            }

            public void init_17_0_2_edc0357_1352328158532_946115_20322Elaborations() {
                init_17_0_2_edc0357_1352328158532_946115_20322Dependencies();
                Expression<?>[] arguments139 = new Expression<?>[1];
                arguments139[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition139 = new Expression<Boolean>(_17_0_2_edc0357_1352328158533_797791_20328_exists);
                elaborationRule139 = addElaborationRule(condition139, _17_0_2_edc0357_1352328156644_264215_19617.this, Customer._17_0_2_edc0357_1352328156644_264215_19617._17_0_2_edc0357_1352328158533_797791_20328.class, "CI_fork1_ForkNode_initialize", arguments139);
            }
        }

        public class _17_0_2_edc0357_1352328158533_74852_20323 extends DurativeEvent {

            public _17_0_2_edc0357_1352328158533_74852_20323() {
                super();
                init_17_0_2_edc0357_1352328158533_74852_20323Members();
                init_17_0_2_edc0357_1352328158533_74852_20323Collections();
                init_17_0_2_edc0357_1352328158533_74852_20323Elaborations();
            }

            public _17_0_2_edc0357_1352328158533_74852_20323(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328158533_74852_20323Members();
                init_17_0_2_edc0357_1352328158533_74852_20323Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328158533_74852_20323Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter objectToPass = null;

            public Dependency< Integer > endTimeDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Dependency< Integer > finalNode_endTimeDependency = null;

            public void init_17_0_2_edc0357_1352328158533_74852_20323Members() {
                try {
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158533_74852_20323Collections() {
                parameters.add(objectToPass);
            }

            public void init_17_0_2_edc0357_1352328158533_74852_20323Dependencies() {
                addDependency(endTime, new Expression<Integer>(new Functions.Plus(new Expression<Integer>(startTime), new Expression<Integer>(duration))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new EffectFunction(sig_17_0_2_edc0357_1352328158534_557121_20335, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(finalNode_endTime, new Expression<Integer>(endTime));
            }

            public void init_17_0_2_edc0357_1352328158533_74852_20323Elaborations() {
                init_17_0_2_edc0357_1352328158533_74852_20323Dependencies();
            }
        }

        public class _17_0_2_edc0357_1352328158533_247450_20324 extends DurativeEvent {

            public _17_0_2_edc0357_1352328158533_247450_20324() {
                super();
                init_17_0_2_edc0357_1352328158533_247450_20324Members();
                init_17_0_2_edc0357_1352328158533_247450_20324Collections();
                init_17_0_2_edc0357_1352328158533_247450_20324Elaborations();
            }

            public _17_0_2_edc0357_1352328158533_247450_20324(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328158533_247450_20324Members();
                init_17_0_2_edc0357_1352328158533_247450_20324Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328158533_247450_20324Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_edc0357_1352328158532_578076_20321_exists = null;

            public IntegerParameter myDeciderID_decider_17_0_2_edc0357_1352328158533_93964_20325 = null;

            public Parameter< Customer > objectToPass = null;

            public BooleanParameter _17_0_2_edc0357_1352328158533_93964_20325_exists = null;

            public IntegerParameter myDeciderID_decider_17_0_2_edc0357_1352328158532_578076_20321 = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158532_578076_20321_existsDependency = null;

            public Dependency< Integer > myDeciderID_decider_17_0_2_edc0357_1352328158533_93964_20325Dependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Customer > objectToPassDependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158533_93964_20325_existsDependency = null;

            public Dependency< Integer > myDeciderID_decider_17_0_2_edc0357_1352328158532_578076_20321Dependency = null;

            public Effect effect140 = null;

            public Parameter effect140Var = null;

            public Effect effect141 = null;

            public Parameter effect141Var = null;

            public Effect effect142 = null;

            public Parameter effect142Var = null;

            public Effect effect143 = null;

            public Parameter effect143Var = null;

            public ElaborationRule elaborationRule144 = null;

            public ElaborationRule elaborationRule145 = null;

            public void init_17_0_2_edc0357_1352328158533_247450_20324Members() {
                try {
                    if (_17_0_2_edc0357_1352328158532_578076_20321_exists == null) _17_0_2_edc0357_1352328158532_578076_20321_exists = new BooleanParameter("_17_0_2_edc0357_1352328158532_578076_20321_exists", (Boolean) false, this);
                    if (myDeciderID_decider_17_0_2_edc0357_1352328158533_93964_20325 == null) myDeciderID_decider_17_0_2_edc0357_1352328158533_93964_20325 = new IntegerParameter("myDeciderID_decider_17_0_2_edc0357_1352328158533_93964_20325", (Integer) 1, this);
                    if (objectToPass == null) objectToPass = new Parameter<Customer>("objectToPass", null, (Customer) null, this);
                    if (_17_0_2_edc0357_1352328158533_93964_20325_exists == null) _17_0_2_edc0357_1352328158533_93964_20325_exists = new BooleanParameter("_17_0_2_edc0357_1352328158533_93964_20325_exists", (Boolean) false, this);
                    if (myDeciderID_decider_17_0_2_edc0357_1352328158532_578076_20321 == null) myDeciderID_decider_17_0_2_edc0357_1352328158532_578076_20321 = new IntegerParameter("myDeciderID_decider_17_0_2_edc0357_1352328158532_578076_20321", (Integer) 1, this);
                    Object effect140VarV = sig_17_0_2_edc0357_1352328158534_699192_20331;
                    effect140Var = new Parameter("effect140Var", null, null, this);
                    addDependency(effect140Var, new Expression(effect140VarV));
                    effect140 = new EffectFunction(new EffectFunction(effect140Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect141VarV = sig_17_0_2_edc0357_1352328158534_844780_20333;
                    effect141Var = new Parameter("effect141Var", null, null, this);
                    addDependency(effect141Var, new Expression(effect141VarV));
                    effect141 = new EffectFunction(new EffectFunction(effect141Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect142VarV = decider_17_0_2_edc0357_1352328158532_578076_20321;
                    effect142Var = new Parameter("effect142Var", null, null, this);
                    addDependency(effect142Var, new Expression(effect142VarV));
                    effect142 = new EffectFunction(new EffectFunction(effect142Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, myDeciderID_decider_17_0_2_edc0357_1352328158532_578076_20321 }));
                    Object effect143VarV = decider_17_0_2_edc0357_1352328158533_93964_20325;
                    effect143Var = new Parameter("effect143Var", null, null, this);
                    addDependency(effect143Var, new Expression(effect143VarV));
                    effect143 = new EffectFunction(new EffectFunction(effect143Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, myDeciderID_decider_17_0_2_edc0357_1352328158533_93964_20325 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158533_247450_20324Collections() {
                parameters.add(_17_0_2_edc0357_1352328158532_578076_20321_exists);
                parameters.add(myDeciderID_decider_17_0_2_edc0357_1352328158533_93964_20325);
                parameters.add(objectToPass);
                parameters.add(_17_0_2_edc0357_1352328158533_93964_20325_exists);
                parameters.add(myDeciderID_decider_17_0_2_edc0357_1352328158532_578076_20321);
                Set<Effect> effectsForeffect140Var = new TreeSet<Effect>();
                effectsForeffect140Var.add(effect140);
                addEffects((Parameter<?>) effect140Var, effectsForeffect140Var);
                Set<Effect> effectsForeffect141Var = new TreeSet<Effect>();
                effectsForeffect141Var.add(effect141);
                addEffects((Parameter<?>) effect141Var, effectsForeffect141Var);
                Set<Effect> effectsForeffect142Var = new TreeSet<Effect>();
                effectsForeffect142Var.add(effect142);
                addEffects((Parameter<?>) effect142Var, effectsForeffect142Var);
                Set<Effect> effectsForeffect143Var = new TreeSet<Effect>();
                effectsForeffect143Var.add(effect143);
                addEffects((Parameter<?>) effect143Var, effectsForeffect143Var);
            }

            public void init_17_0_2_edc0357_1352328158533_247450_20324Dependencies() {
                addDependency(_17_0_2_edc0357_1352328158532_578076_20321_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158532_578076_20321, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158532_578076_20321, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158532_578076_20321, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_edc0357_1352328158532_578076_20321)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_edc0357_1352328158533_74852_20323_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_edc0357_1352328158533_74852_20323_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))))));
                addDependency(myDeciderID_decider_17_0_2_edc0357_1352328158533_93964_20325, new Expression<Integer>(1));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Customer>(new EffectFunction(sig_17_0_2_edc0357_1352328158534_268675_20330, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_edc0357_1352328158533_93964_20325_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158533_93964_20325, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158533_93964_20325, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158533_93964_20325, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_edc0357_1352328158533_93964_20325)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_edc0357_1352328158533_74852_20323_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_edc0357_1352328158533_74852_20323_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))))));
                addDependency(myDeciderID_decider_17_0_2_edc0357_1352328158532_578076_20321, new Expression<Integer>(1));
            }

            public void init_17_0_2_edc0357_1352328158533_247450_20324Elaborations() {
                init_17_0_2_edc0357_1352328158533_247450_20324Dependencies();
                Expression<?>[] arguments144 = new Expression<?>[1];
                arguments144[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition144 = new Expression<Boolean>(_17_0_2_edc0357_1352328158532_578076_20321_exists);
                elaborationRule144 = addElaborationRule(condition144, _17_0_2_edc0357_1352328156644_264215_19617.this, Customer._17_0_2_edc0357_1352328156644_264215_19617._17_0_2_edc0357_1352328158532_578076_20321.class, "CI_setinitusage_AddStructuralFeatureValueAction_initialize", arguments144);
                Expression<?>[] arguments145 = new Expression<?>[1];
                arguments145[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition145 = new Expression<Boolean>(_17_0_2_edc0357_1352328158533_93964_20325_exists);
                elaborationRule145 = addElaborationRule(condition145, _17_0_2_edc0357_1352328156644_264215_19617.this, Customer._17_0_2_edc0357_1352328156644_264215_19617._17_0_2_edc0357_1352328158533_93964_20325.class, "CI_setinit_cap_AddStructuralFeatureValueAction_initialize", arguments145);
            }
        }

        public class _17_0_2_edc0357_1352328158533_93964_20325 extends DurativeEvent {

            public _17_0_2_edc0357_1352328158533_93964_20325() {
                super();
                init_17_0_2_edc0357_1352328158533_93964_20325Members();
                init_17_0_2_edc0357_1352328158533_93964_20325Collections();
                init_17_0_2_edc0357_1352328158533_93964_20325Elaborations();
            }

            public _17_0_2_edc0357_1352328158533_93964_20325(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328158533_93964_20325Members();
                init_17_0_2_edc0357_1352328158533_93964_20325Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328158533_93964_20325Elaborations();
                fixTimeDependencies();
            }

            public IntegerParameter _17_0_2_edc0357_1352328160564_30011_21453 = null;

            public BooleanParameter _17_0_2_edc0357_1352328158533_586682_20327_exists = null;

            public Parameter< Customer > _17_0_2_edc0357_1352328160565_564177_21454 = null;

            public IntegerParameter myDeciderID_decider_17_0_2_edc0357_1352328158533_586682_20327 = null;

            public BooleanParameter objectToPass = null;

            public Dependency< Integer > _17_0_2_edc0357_1352328160564_30011_21453Dependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158533_586682_20327_existsDependency = null;

            public Dependency< Customer > _17_0_2_edc0357_1352328160565_564177_21454Dependency = null;

            public Dependency< Integer > myDeciderID_decider_17_0_2_edc0357_1352328158533_586682_20327Dependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Effect effect146 = null;

            public Parameter effect146Var = null;

            public Effect effect147 = null;

            public Parameter effect147Var = null;

            public Effect effect148 = null;

            public Parameter effect148Var = null;

            public ElaborationRule elaborationRule149 = null;

            public void init_17_0_2_edc0357_1352328158533_93964_20325Members() {
                try {
                    if (_17_0_2_edc0357_1352328160564_30011_21453 == null) _17_0_2_edc0357_1352328160564_30011_21453 = new IntegerParameter("_17_0_2_edc0357_1352328160564_30011_21453", (Integer) null, this);
                    if (_17_0_2_edc0357_1352328158533_586682_20327_exists == null) _17_0_2_edc0357_1352328158533_586682_20327_exists = new BooleanParameter("_17_0_2_edc0357_1352328158533_586682_20327_exists", (Boolean) false, this);
                    if (_17_0_2_edc0357_1352328160565_564177_21454 == null) _17_0_2_edc0357_1352328160565_564177_21454 = new Parameter<Customer>("_17_0_2_edc0357_1352328160565_564177_21454", null, (Customer) null, this);
                    if (myDeciderID_decider_17_0_2_edc0357_1352328158533_586682_20327 == null) myDeciderID_decider_17_0_2_edc0357_1352328158533_586682_20327 = new IntegerParameter("myDeciderID_decider_17_0_2_edc0357_1352328158533_586682_20327", (Integer) 2, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect146VarV = sig_17_0_2_edc0357_1352328158534_989901_20336;
                    effect146Var = new Parameter("effect146Var", null, null, this);
                    addDependency(effect146Var, new Expression(effect146VarV));
                    effect146 = new EffectFunction(new EffectFunction(effect146Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect147VarV = decider_17_0_2_edc0357_1352328158533_586682_20327;
                    effect147Var = new Parameter("effect147Var", null, null, this);
                    addDependency(effect147Var, new Expression(effect147VarV));
                    effect147 = new EffectFunction(new EffectFunction(effect147Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, myDeciderID_decider_17_0_2_edc0357_1352328158533_586682_20327 }));
                    Object effect148VarV = cap__17_0_2_edc0357_1352328156659_30024_19625;
                    effect148Var = new Parameter("effect148Var", null, null, this);
                    addDependency(effect148Var, new Expression(effect148VarV));
                    effect148 = new EffectFunction(new EffectFunction(effect148Var, ClassUtils.getMethodForArgTypes("TimeVaryingPlottableMap<Integer>", "powersystem", "setValue", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { startTime, _17_0_2_edc0357_1352328160564_30011_21453 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158533_93964_20325Collections() {
                parameters.add(_17_0_2_edc0357_1352328160564_30011_21453);
                parameters.add(_17_0_2_edc0357_1352328158533_586682_20327_exists);
                parameters.add(_17_0_2_edc0357_1352328160565_564177_21454);
                parameters.add(myDeciderID_decider_17_0_2_edc0357_1352328158533_586682_20327);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect146Var = new TreeSet<Effect>();
                effectsForeffect146Var.add(effect146);
                addEffects((Parameter<?>) effect146Var, effectsForeffect146Var);
                Set<Effect> effectsForeffect147Var = new TreeSet<Effect>();
                effectsForeffect147Var.add(effect147);
                addEffects((Parameter<?>) effect147Var, effectsForeffect147Var);
                Set<Effect> effectsForeffect148Var = new TreeSet<Effect>();
                effectsForeffect148Var.add(effect148);
                addEffects((Parameter<?>) effect148Var, effectsForeffect148Var);
            }

            public void init_17_0_2_edc0357_1352328158533_93964_20325Dependencies() {
                addDependency(_17_0_2_edc0357_1352328160564_30011_21453, new Expression<Integer>(new EffectFunction(sig_17_0_2_edc0357_1352328158534_643944_20332, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_edc0357_1352328158533_586682_20327_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158533_586682_20327, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158533_586682_20327, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158533_586682_20327, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_edc0357_1352328158533_586682_20327)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_edc0357_1352328158533_74852_20323_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_edc0357_1352328158533_74852_20323_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))))));
                addDependency(_17_0_2_edc0357_1352328160565_564177_21454, new Expression<Customer>(new EffectFunction(sig_17_0_2_edc0357_1352328158534_844780_20333, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(myDeciderID_decider_17_0_2_edc0357_1352328158533_586682_20327, new Expression<Integer>(2));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(true));
            }

            public void init_17_0_2_edc0357_1352328158533_93964_20325Elaborations() {
                init_17_0_2_edc0357_1352328158533_93964_20325Dependencies();
                Expression<?>[] arguments149 = new Expression<?>[1];
                arguments149[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition149 = new Expression<Boolean>(_17_0_2_edc0357_1352328158533_586682_20327_exists);
                elaborationRule149 = addElaborationRule(condition149, _17_0_2_edc0357_1352328156644_264215_19617.this, Customer._17_0_2_edc0357_1352328156644_264215_19617._17_0_2_edc0357_1352328158533_586682_20327.class, "CI_join_JoinNode_initialize", arguments149);
            }
        }

        public class _17_0_2_edc0357_1352328158533_893356_20326 extends DurativeEvent {

            public _17_0_2_edc0357_1352328158533_893356_20326() {
                super();
                init_17_0_2_edc0357_1352328158533_893356_20326Members();
                init_17_0_2_edc0357_1352328158533_893356_20326Collections();
                init_17_0_2_edc0357_1352328158533_893356_20326Elaborations();
            }

            public _17_0_2_edc0357_1352328158533_893356_20326(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328158533_893356_20326Members();
                init_17_0_2_edc0357_1352328158533_893356_20326Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328158533_893356_20326Elaborations();
                fixTimeDependencies();
            }

            public IntegerParameter myDeciderID_decider_17_0_2_edc0357_1352328158533_93964_20325 = null;

            public BooleanParameter objectToPass = null;

            public IntegerParameter _17_0_2_edc0357_1352328160565_649347_21456 = null;

            public BooleanParameter _17_0_2_edc0357_1352328158533_93964_20325_exists = null;

            public Dependency< Integer > myDeciderID_decider_17_0_2_edc0357_1352328158533_93964_20325Dependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Dependency< Integer > _17_0_2_edc0357_1352328160565_649347_21456Dependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158533_93964_20325_existsDependency = null;

            public Effect effect150 = null;

            public Parameter effect150Var = null;

            public Effect effect151 = null;

            public Parameter effect151Var = null;

            public ElaborationRule elaborationRule152 = null;

            public void init_17_0_2_edc0357_1352328158533_893356_20326Members() {
                try {
                    if (myDeciderID_decider_17_0_2_edc0357_1352328158533_93964_20325 == null) myDeciderID_decider_17_0_2_edc0357_1352328158533_93964_20325 = new IntegerParameter("myDeciderID_decider_17_0_2_edc0357_1352328158533_93964_20325", (Integer) 2, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (_17_0_2_edc0357_1352328160565_649347_21456 == null) _17_0_2_edc0357_1352328160565_649347_21456 = new IntegerParameter("_17_0_2_edc0357_1352328160565_649347_21456", (Integer) 0, this);
                    if (_17_0_2_edc0357_1352328158533_93964_20325_exists == null) _17_0_2_edc0357_1352328158533_93964_20325_exists = new BooleanParameter("_17_0_2_edc0357_1352328158533_93964_20325_exists", (Boolean) false, this);
                    Object effect150VarV = sig_17_0_2_edc0357_1352328158534_643944_20332;
                    effect150Var = new Parameter("effect150Var", null, null, this);
                    addDependency(effect150Var, new Expression(effect150VarV));
                    effect150 = new EffectFunction(new EffectFunction(effect150Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { _17_0_2_edc0357_1352328160565_649347_21456, endTime }));
                    Object effect151VarV = decider_17_0_2_edc0357_1352328158533_93964_20325;
                    effect151Var = new Parameter("effect151Var", null, null, this);
                    addDependency(effect151Var, new Expression(effect151VarV));
                    effect151 = new EffectFunction(new EffectFunction(effect151Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, myDeciderID_decider_17_0_2_edc0357_1352328158533_93964_20325 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158533_893356_20326Collections() {
                parameters.add(myDeciderID_decider_17_0_2_edc0357_1352328158533_93964_20325);
                parameters.add(objectToPass);
                parameters.add(_17_0_2_edc0357_1352328160565_649347_21456);
                parameters.add(_17_0_2_edc0357_1352328158533_93964_20325_exists);
                Set<Effect> effectsForeffect150Var = new TreeSet<Effect>();
                effectsForeffect150Var.add(effect150);
                addEffects((Parameter<?>) effect150Var, effectsForeffect150Var);
                Set<Effect> effectsForeffect151Var = new TreeSet<Effect>();
                effectsForeffect151Var.add(effect151);
                addEffects((Parameter<?>) effect151Var, effectsForeffect151Var);
            }

            public void init_17_0_2_edc0357_1352328158533_893356_20326Dependencies() {
                addDependency(myDeciderID_decider_17_0_2_edc0357_1352328158533_93964_20325, new Expression<Integer>(2));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new EffectFunction(sig_17_0_2_edc0357_1352328158535_248302_20340, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_edc0357_1352328160565_649347_21456, new Expression<Integer>(0));
                addDependency(_17_0_2_edc0357_1352328158533_93964_20325_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158533_93964_20325, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158533_93964_20325, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158533_93964_20325, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_edc0357_1352328158533_93964_20325)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_edc0357_1352328158533_74852_20323_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_edc0357_1352328158533_74852_20323_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))))));
            }

            public void init_17_0_2_edc0357_1352328158533_893356_20326Elaborations() {
                init_17_0_2_edc0357_1352328158533_893356_20326Dependencies();
                Expression<?>[] arguments152 = new Expression<?>[1];
                arguments152[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition152 = new Expression<Boolean>(_17_0_2_edc0357_1352328158533_93964_20325_exists);
                elaborationRule152 = addElaborationRule(condition152, _17_0_2_edc0357_1352328156644_264215_19617.this, Customer._17_0_2_edc0357_1352328156644_264215_19617._17_0_2_edc0357_1352328158533_93964_20325.class, "CI_setinit_cap_AddStructuralFeatureValueAction_initialize", arguments152);
            }
        }

        public class _17_0_2_edc0357_1352328158533_586682_20327 extends DurativeEvent {

            public _17_0_2_edc0357_1352328158533_586682_20327() {
                super();
                init_17_0_2_edc0357_1352328158533_586682_20327Members();
                init_17_0_2_edc0357_1352328158533_586682_20327Collections();
                init_17_0_2_edc0357_1352328158533_586682_20327Elaborations();
            }

            public _17_0_2_edc0357_1352328158533_586682_20327(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328158533_586682_20327Members();
                init_17_0_2_edc0357_1352328158533_586682_20327Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328158533_586682_20327Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter objectToPass1 = null;

            public BooleanParameter objectToPass = null;

            public Dependency< Boolean > objectToPass1Dependency = null;

            public Dependency< Integer > finalNode_startTimeDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Effect effect153 = null;

            public Parameter effect153Var = null;

            public void init_17_0_2_edc0357_1352328158533_586682_20327Members() {
                try {
                    if (objectToPass1 == null) objectToPass1 = new BooleanParameter("objectToPass1", (Boolean) null, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect153VarV = sig_17_0_2_edc0357_1352328158534_557121_20335;
                    effect153Var = new Parameter("effect153Var", null, null, this);
                    addDependency(effect153Var, new Expression(effect153VarV));
                    effect153 = new EffectFunction(new EffectFunction(effect153Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158533_586682_20327Collections() {
                parameters.add(objectToPass1);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect153Var = new TreeSet<Effect>();
                effectsForeffect153Var.add(effect153);
                addEffects((Parameter<?>) effect153Var, effectsForeffect153Var);
            }

            public void init_17_0_2_edc0357_1352328158533_586682_20327Dependencies() {
                addDependency(objectToPass1, new Expression<Boolean>(new EffectFunction(sig_17_0_2_edc0357_1352328158534_989901_20336, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(finalNode_startTime, new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new EffectFunction(sig_17_0_2_edc0357_1352328158534_769162_20334, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_edc0357_1352328158533_586682_20327Elaborations() {
                init_17_0_2_edc0357_1352328158533_586682_20327Dependencies();
            }
        }

        public class _17_0_2_edc0357_1352328158533_797791_20328 extends DurativeEvent {

            public _17_0_2_edc0357_1352328158533_797791_20328() {
                super();
                init_17_0_2_edc0357_1352328158533_797791_20328Members();
                init_17_0_2_edc0357_1352328158533_797791_20328Collections();
                init_17_0_2_edc0357_1352328158533_797791_20328Elaborations();
            }

            public _17_0_2_edc0357_1352328158533_797791_20328(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328158533_797791_20328Members();
                init_17_0_2_edc0357_1352328158533_797791_20328Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328158533_797791_20328Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_edc0357_1352328158532_777314_20319_exists = null;

            public BooleanParameter _17_0_2_edc0357_1352328158532_127805_20320_exists = null;

            public BooleanParameter objectToPass = null;

            public BooleanParameter _17_0_2_edc0357_1352328158533_893356_20326_exists = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158532_777314_20319_existsDependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158532_127805_20320_existsDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158533_893356_20326_existsDependency = null;

            public Effect effect154 = null;

            public Parameter effect154Var = null;

            public Effect effect155 = null;

            public Parameter effect155Var = null;

            public Effect effect156 = null;

            public Parameter effect156Var = null;

            public ElaborationRule elaborationRule157 = null;

            public ElaborationRule elaborationRule158 = null;

            public ElaborationRule elaborationRule159 = null;

            public void init_17_0_2_edc0357_1352328158533_797791_20328Members() {
                try {
                    if (_17_0_2_edc0357_1352328158532_777314_20319_exists == null) _17_0_2_edc0357_1352328158532_777314_20319_exists = new BooleanParameter("_17_0_2_edc0357_1352328158532_777314_20319_exists", (Boolean) false, this);
                    if (_17_0_2_edc0357_1352328158532_127805_20320_exists == null) _17_0_2_edc0357_1352328158532_127805_20320_exists = new BooleanParameter("_17_0_2_edc0357_1352328158532_127805_20320_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (_17_0_2_edc0357_1352328158533_893356_20326_exists == null) _17_0_2_edc0357_1352328158533_893356_20326_exists = new BooleanParameter("_17_0_2_edc0357_1352328158533_893356_20326_exists", (Boolean) false, this);
                    Object effect154VarV = sig_17_0_2_edc0357_1352328158535_401408_20338;
                    effect154Var = new Parameter("effect154Var", null, null, this);
                    addDependency(effect154Var, new Expression(effect154VarV));
                    effect154 = new EffectFunction(new EffectFunction(effect154Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect155VarV = sig_17_0_2_edc0357_1352328158535_735383_20339;
                    effect155Var = new Parameter("effect155Var", null, null, this);
                    addDependency(effect155Var, new Expression(effect155VarV));
                    effect155 = new EffectFunction(new EffectFunction(effect155Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect156VarV = sig_17_0_2_edc0357_1352328158535_248302_20340;
                    effect156Var = new Parameter("effect156Var", null, null, this);
                    addDependency(effect156Var, new Expression(effect156VarV));
                    effect156 = new EffectFunction(new EffectFunction(effect156Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158533_797791_20328Collections() {
                parameters.add(_17_0_2_edc0357_1352328158532_777314_20319_exists);
                parameters.add(_17_0_2_edc0357_1352328158532_127805_20320_exists);
                parameters.add(objectToPass);
                parameters.add(_17_0_2_edc0357_1352328158533_893356_20326_exists);
                Set<Effect> effectsForeffect154Var = new TreeSet<Effect>();
                effectsForeffect154Var.add(effect154);
                addEffects((Parameter<?>) effect154Var, effectsForeffect154Var);
                Set<Effect> effectsForeffect155Var = new TreeSet<Effect>();
                effectsForeffect155Var.add(effect155);
                addEffects((Parameter<?>) effect155Var, effectsForeffect155Var);
                Set<Effect> effectsForeffect156Var = new TreeSet<Effect>();
                effectsForeffect156Var.add(effect156);
                addEffects((Parameter<?>) effect156Var, effectsForeffect156Var);
            }

            public void init_17_0_2_edc0357_1352328158533_797791_20328Dependencies() {
                addDependency(_17_0_2_edc0357_1352328158532_777314_20319_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_edc0357_1352328158533_74852_20323_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_edc0357_1352328158533_74852_20323_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(_17_0_2_edc0357_1352328158532_127805_20320_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_edc0357_1352328158533_74852_20323_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_edc0357_1352328158533_74852_20323_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new EffectFunction(sig_17_0_2_edc0357_1352328158535_536324_20337, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_edc0357_1352328158533_893356_20326_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_edc0357_1352328158533_74852_20323_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_edc0357_1352328158533_74852_20323_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
            }

            public void init_17_0_2_edc0357_1352328158533_797791_20328Elaborations() {
                init_17_0_2_edc0357_1352328158533_797791_20328Dependencies();
                Expression<?>[] arguments157 = new Expression<?>[1];
                arguments157[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition157 = new Expression<Boolean>(_17_0_2_edc0357_1352328158532_127805_20320_exists);
                elaborationRule157 = addElaborationRule(condition157, _17_0_2_edc0357_1352328156644_264215_19617.this, Customer._17_0_2_edc0357_1352328156644_264215_19617._17_0_2_edc0357_1352328158532_127805_20320.class, "CI_spec4_ValueSpecificationAction_initialize", arguments157);
                Expression<?>[] arguments158 = new Expression<?>[1];
                arguments158[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition158 = new Expression<Boolean>(_17_0_2_edc0357_1352328158533_893356_20326_exists);
                elaborationRule158 = addElaborationRule(condition158, _17_0_2_edc0357_1352328156644_264215_19617.this, Customer._17_0_2_edc0357_1352328156644_264215_19617._17_0_2_edc0357_1352328158533_893356_20326.class, "CI_spec0_ValueSpecificationAction_initialize", arguments158);
                Expression<?>[] arguments159 = new Expression<?>[1];
                arguments159[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition159 = new Expression<Boolean>(_17_0_2_edc0357_1352328158532_777314_20319_exists);
                elaborationRule159 = addElaborationRule(condition159, _17_0_2_edc0357_1352328156644_264215_19617.this, Customer._17_0_2_edc0357_1352328156644_264215_19617._17_0_2_edc0357_1352328158532_777314_20319.class, "CI_readself_ReadSelfAction_initialize", arguments159);
            }
        }

        public _17_0_2_edc0357_1352328156644_264215_19617(Expression<Integer> startTime, Expression<DurativeEvent> caller) {
            super();
            init_17_0_2_edc0357_1352328156644_264215_19617Members();
            init_17_0_2_edc0357_1352328156644_264215_19617Collections();
            addDependency(this.startTime, startTime);
            addDependency(this.caller, caller);
            init_17_0_2_edc0357_1352328156644_264215_19617Elaborations();
            fixTimeDependencies();
        }

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_edc0357_1352328158535_536324_20337 = null;

        public Parameter< TimeVaryingList<Integer> > decider_17_0_2_edc0357_1352328158532_578076_20321 = null;

        public Parameter< ObjectFlow<Customer> > sig_17_0_2_edc0357_1352328158534_844780_20333 = null;

        public Parameter< DurativeEvent > caller = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_edc0357_1352328158534_557121_20335 = null;

        public BooleanParameter _17_0_2_edc0357_1352328158533_74852_20323_exists = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_edc0357_1352328158534_769162_20334 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_edc0357_1352328158535_248302_20340 = null;

        public Parameter< TimeVaryingList<Integer> > decider_17_0_2_edc0357_1352328158533_586682_20327 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_edc0357_1352328158534_989901_20336 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_2_edc0357_1352328158534_440722_20329 = null;

        public Parameter< ObjectFlow<Customer> > sig_17_0_2_edc0357_1352328158534_699192_20331 = null;

        public IntegerParameter finalNode_startTime = null;

        public IntegerParameter finalNode_endTime = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_edc0357_1352328158535_401408_20338 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_edc0357_1352328158535_735383_20339 = null;

        public Parameter< TimeVaryingList<Integer> > decider_17_0_2_edc0357_1352328158533_93964_20325 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_2_edc0357_1352328158534_643944_20332 = null;

        public Parameter< ObjectFlow<Customer> > sig_17_0_2_edc0357_1352328158534_268675_20330 = null;

        public Dependency< Boolean > _17_0_2_edc0357_1352328158533_74852_20323_existsDependency = null;

        public Dependency< Integer > caller_endTimeDependency = null;

        public Dependency< Integer > endTimeDependency = null;

        public ElaborationRule elaborationRule127 = null;

        public ElaborationRule elaborationRule128 = null;

        public void init_17_0_2_edc0357_1352328156644_264215_19617Members() {
            try {
                if (sig_17_0_2_edc0357_1352328158535_536324_20337 == null) sig_17_0_2_edc0357_1352328158535_536324_20337 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328158535_536324_20337", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158535_536324_20337" })).evaluate(true), this);
                if (decider_17_0_2_edc0357_1352328158532_578076_20321 == null) decider_17_0_2_edc0357_1352328158532_578076_20321 = new Parameter<TimeVaryingList<Integer>>("decider_17_0_2_edc0357_1352328158532_578076_20321", null, (TimeVaryingList<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingList.class, java.lang.String.class, int.class), new Object[] { "decider_17_0_2_edc0357_1352328158532_578076_20321", 2 })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328158534_844780_20333 == null) sig_17_0_2_edc0357_1352328158534_844780_20333 = new Parameter<ObjectFlow<Customer>>("sig_17_0_2_edc0357_1352328158534_844780_20333", null, (ObjectFlow<Customer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158534_844780_20333" })).evaluate(true), this);
                if (caller == null) caller = new Parameter<DurativeEvent>("caller", null, (DurativeEvent) null, this);
                if (sig_17_0_2_edc0357_1352328158534_557121_20335 == null) sig_17_0_2_edc0357_1352328158534_557121_20335 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328158534_557121_20335", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158534_557121_20335" })).evaluate(true), this);
                if (_17_0_2_edc0357_1352328158533_74852_20323_exists == null) _17_0_2_edc0357_1352328158533_74852_20323_exists = new BooleanParameter("_17_0_2_edc0357_1352328158533_74852_20323_exists", (Boolean) false, this);
                if (sig_17_0_2_edc0357_1352328158534_769162_20334 == null) sig_17_0_2_edc0357_1352328158534_769162_20334 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328158534_769162_20334", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158534_769162_20334" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328158535_248302_20340 == null) sig_17_0_2_edc0357_1352328158535_248302_20340 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328158535_248302_20340", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158535_248302_20340" })).evaluate(true), this);
                if (decider_17_0_2_edc0357_1352328158533_586682_20327 == null) decider_17_0_2_edc0357_1352328158533_586682_20327 = new Parameter<TimeVaryingList<Integer>>("decider_17_0_2_edc0357_1352328158533_586682_20327", null, (TimeVaryingList<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingList.class, java.lang.String.class, int.class), new Object[] { "decider_17_0_2_edc0357_1352328158533_586682_20327", 2 })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328158534_989901_20336 == null) sig_17_0_2_edc0357_1352328158534_989901_20336 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328158534_989901_20336", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158534_989901_20336" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328158534_440722_20329 == null) sig_17_0_2_edc0357_1352328158534_440722_20329 = new Parameter<ObjectFlow<Integer>>("sig_17_0_2_edc0357_1352328158534_440722_20329", null, (ObjectFlow<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158534_440722_20329" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328158534_699192_20331 == null) sig_17_0_2_edc0357_1352328158534_699192_20331 = new Parameter<ObjectFlow<Customer>>("sig_17_0_2_edc0357_1352328158534_699192_20331", null, (ObjectFlow<Customer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158534_699192_20331" })).evaluate(true), this);
                if (finalNode_startTime == null) finalNode_startTime = new IntegerParameter("finalNode_startTime", (Integer) null, this);
                if (finalNode_endTime == null) finalNode_endTime = new IntegerParameter("finalNode_endTime", (Integer) null, this);
                if (sig_17_0_2_edc0357_1352328158535_401408_20338 == null) sig_17_0_2_edc0357_1352328158535_401408_20338 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328158535_401408_20338", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158535_401408_20338" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328158535_735383_20339 == null) sig_17_0_2_edc0357_1352328158535_735383_20339 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328158535_735383_20339", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158535_735383_20339" })).evaluate(true), this);
                if (decider_17_0_2_edc0357_1352328158533_93964_20325 == null) decider_17_0_2_edc0357_1352328158533_93964_20325 = new Parameter<TimeVaryingList<Integer>>("decider_17_0_2_edc0357_1352328158533_93964_20325", null, (TimeVaryingList<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingList.class, java.lang.String.class, int.class), new Object[] { "decider_17_0_2_edc0357_1352328158533_93964_20325", 2 })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328158534_643944_20332 == null) sig_17_0_2_edc0357_1352328158534_643944_20332 = new Parameter<ObjectFlow<Integer>>("sig_17_0_2_edc0357_1352328158534_643944_20332", null, (ObjectFlow<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158534_643944_20332" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328158534_268675_20330 == null) sig_17_0_2_edc0357_1352328158534_268675_20330 = new Parameter<ObjectFlow<Customer>>("sig_17_0_2_edc0357_1352328158534_268675_20330", null, (ObjectFlow<Customer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158534_268675_20330" })).evaluate(true), this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void init_17_0_2_edc0357_1352328156644_264215_19617Collections() {
            parameters.add(sig_17_0_2_edc0357_1352328158535_536324_20337);
            parameters.add(decider_17_0_2_edc0357_1352328158532_578076_20321);
            parameters.add(sig_17_0_2_edc0357_1352328158534_844780_20333);
            parameters.add(caller);
            parameters.add(sig_17_0_2_edc0357_1352328158534_557121_20335);
            parameters.add(_17_0_2_edc0357_1352328158533_74852_20323_exists);
            parameters.add(sig_17_0_2_edc0357_1352328158534_769162_20334);
            parameters.add(sig_17_0_2_edc0357_1352328158535_248302_20340);
            parameters.add(decider_17_0_2_edc0357_1352328158533_586682_20327);
            parameters.add(sig_17_0_2_edc0357_1352328158534_989901_20336);
            parameters.add(sig_17_0_2_edc0357_1352328158534_440722_20329);
            parameters.add(sig_17_0_2_edc0357_1352328158534_699192_20331);
            parameters.add(finalNode_startTime);
            parameters.add(finalNode_endTime);
            parameters.add(sig_17_0_2_edc0357_1352328158535_401408_20338);
            parameters.add(sig_17_0_2_edc0357_1352328158535_735383_20339);
            parameters.add(decider_17_0_2_edc0357_1352328158533_93964_20325);
            parameters.add(sig_17_0_2_edc0357_1352328158534_643944_20332);
            parameters.add(sig_17_0_2_edc0357_1352328158534_268675_20330);
        }

        public void init_17_0_2_edc0357_1352328156644_264215_19617Dependencies() {
            addDependency(_17_0_2_edc0357_1352328158533_74852_20323_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.NotEquals(new Expression<Integer>(finalNode_startTime), null)), new Expression(new EffectFunction(sig_17_0_2_edc0357_1352328158534_557121_20335, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(new Functions.Minus(new Expression<Integer>(finalNode_startTime), new Expression<Integer>(1))) })))));
            caller.getValue(true).addDependency(caller.getValue(true).endTime, new Expression<Integer>(finalNode_endTime));
            addDependency(endTime, new Expression<Integer>(finalNode_endTime));
        }

        public void init_17_0_2_edc0357_1352328156644_264215_19617Elaborations() {
            init_17_0_2_edc0357_1352328156644_264215_19617Dependencies();
            Expression<?>[] arguments127 = new Expression<?>[1];
            arguments127[0] = new Expression<Integer>(startTime);
            Expression<Boolean> condition127 = new Expression<Boolean>(true);
            elaborationRule127 = addElaborationRule(condition127, _17_0_2_edc0357_1352328156644_264215_19617.this, Customer._17_0_2_edc0357_1352328156644_264215_19617._17_0_2_edc0357_1352328158532_946115_20322.class, "CI_start_InitialNode_initialize", arguments127);
            Expression<?>[] arguments128 = new Expression<?>[1];
            arguments128[0] = new Expression<Integer>(finalNode_startTime);
            Expression<Boolean> condition128 = new Expression<Boolean>(_17_0_2_edc0357_1352328158533_74852_20323_exists);
            elaborationRule128 = addElaborationRule(condition128, _17_0_2_edc0357_1352328156644_264215_19617.this, Customer._17_0_2_edc0357_1352328156644_264215_19617._17_0_2_edc0357_1352328158533_74852_20323.class, "CI_end_ActivityFinalNode_initialize", arguments128);
        }
    }

    public class _17_0_2_edc0357_1352328156645_46192_19618 extends DurativeEvent {

        public _17_0_2_edc0357_1352328156645_46192_19618() {
            super();
            init_17_0_2_edc0357_1352328156645_46192_19618Members();
            init_17_0_2_edc0357_1352328156645_46192_19618Collections();
            init_17_0_2_edc0357_1352328156645_46192_19618Elaborations();
        }

        public class _17_0_2_edc0357_1352328158672_866631_20373 extends DurativeEvent {

            public _17_0_2_edc0357_1352328158672_866631_20373() {
                super();
                init_17_0_2_edc0357_1352328158672_866631_20373Members();
                init_17_0_2_edc0357_1352328158672_866631_20373Collections();
                init_17_0_2_edc0357_1352328158672_866631_20373Elaborations();
            }

            public _17_0_2_edc0357_1352328158672_866631_20373(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328158672_866631_20373Members();
                init_17_0_2_edc0357_1352328158672_866631_20373Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328158672_866631_20373Elaborations();
                fixTimeDependencies();
            }

            public Parameter< Customer > _17_0_2_edc0357_1352328160571_533840_21470 = null;

            public BooleanParameter _17_0_2_edc0357_1352328158675_718110_20377_exists = null;

            public BooleanParameter _17_0_2_edc0357_1352328158677_175784_20381_exists = null;

            public IntegerParameter myDeciderID_decider_17_0_2_edc0357_1352328158675_718110_20377 = null;

            public BooleanParameter objectToPass = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158675_718110_20377_existsDependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158677_175784_20381_existsDependency = null;

            public Dependency< Integer > myDeciderID_decider_17_0_2_edc0357_1352328158675_718110_20377Dependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Effect effect163 = null;

            public Parameter effect163Var = null;

            public Effect effect164 = null;

            public Parameter effect164Var = null;

            public Effect effect165 = null;

            public Parameter effect165Var = null;

            public ElaborationRule elaborationRule166 = null;

            public ElaborationRule elaborationRule167 = null;

            public void init_17_0_2_edc0357_1352328158672_866631_20373Members() {
                try {
                    if (_17_0_2_edc0357_1352328160571_533840_21470 == null) _17_0_2_edc0357_1352328160571_533840_21470 = new Parameter<Customer>("_17_0_2_edc0357_1352328160571_533840_21470", null, (Customer) Customer.this, this);
                    if (_17_0_2_edc0357_1352328158675_718110_20377_exists == null) _17_0_2_edc0357_1352328158675_718110_20377_exists = new BooleanParameter("_17_0_2_edc0357_1352328158675_718110_20377_exists", (Boolean) false, this);
                    if (_17_0_2_edc0357_1352328158677_175784_20381_exists == null) _17_0_2_edc0357_1352328158677_175784_20381_exists = new BooleanParameter("_17_0_2_edc0357_1352328158677_175784_20381_exists", (Boolean) false, this);
                    if (myDeciderID_decider_17_0_2_edc0357_1352328158675_718110_20377 == null) myDeciderID_decider_17_0_2_edc0357_1352328158675_718110_20377 = new IntegerParameter("myDeciderID_decider_17_0_2_edc0357_1352328158675_718110_20377", (Integer) 1, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect163VarV = sig_17_0_2_edc0357_1352328158680_29152_20391;
                    effect163Var = new Parameter("effect163Var", null, null, this);
                    addDependency(effect163Var, new Expression(effect163VarV));
                    effect163 = new EffectFunction(new EffectFunction(effect163Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { _17_0_2_edc0357_1352328160571_533840_21470, endTime }));
                    Object effect164VarV = sig_17_0_2_edc0357_1352328158680_247894_20395;
                    effect164Var = new Parameter("effect164Var", null, null, this);
                    addDependency(effect164Var, new Expression(effect164VarV));
                    effect164 = new EffectFunction(new EffectFunction(effect164Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect165VarV = decider_17_0_2_edc0357_1352328158675_718110_20377;
                    effect165Var = new Parameter("effect165Var", null, null, this);
                    addDependency(effect165Var, new Expression(effect165VarV));
                    effect165 = new EffectFunction(new EffectFunction(effect165Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, myDeciderID_decider_17_0_2_edc0357_1352328158675_718110_20377 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158672_866631_20373Collections() {
                parameters.add(_17_0_2_edc0357_1352328160571_533840_21470);
                parameters.add(_17_0_2_edc0357_1352328158675_718110_20377_exists);
                parameters.add(_17_0_2_edc0357_1352328158677_175784_20381_exists);
                parameters.add(myDeciderID_decider_17_0_2_edc0357_1352328158675_718110_20377);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect163Var = new TreeSet<Effect>();
                effectsForeffect163Var.add(effect163);
                addEffects((Parameter<?>) effect163Var, effectsForeffect163Var);
                Set<Effect> effectsForeffect164Var = new TreeSet<Effect>();
                effectsForeffect164Var.add(effect164);
                addEffects((Parameter<?>) effect164Var, effectsForeffect164Var);
                Set<Effect> effectsForeffect165Var = new TreeSet<Effect>();
                effectsForeffect165Var.add(effect165);
                addEffects((Parameter<?>) effect165Var, effectsForeffect165Var);
            }

            public void init_17_0_2_edc0357_1352328158672_866631_20373Dependencies() {
                addDependency(_17_0_2_edc0357_1352328158675_718110_20377_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158675_718110_20377, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158675_718110_20377, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158675_718110_20377, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_edc0357_1352328158675_718110_20377)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_edc0357_1352328158674_624952_20376_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_edc0357_1352328158674_624952_20376_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))))));
                addDependency(_17_0_2_edc0357_1352328158677_175784_20381_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_edc0357_1352328158674_624952_20376_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_edc0357_1352328158674_624952_20376_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(myDeciderID_decider_17_0_2_edc0357_1352328158675_718110_20377, new Expression<Integer>(1));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new EffectFunction(sig_17_0_2_edc0357_1352328158681_903819_20399, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_edc0357_1352328158672_866631_20373Elaborations() {
                init_17_0_2_edc0357_1352328158672_866631_20373Dependencies();
                Expression<?>[] arguments166 = new Expression<?>[1];
                arguments166[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition166 = new Expression<Boolean>(_17_0_2_edc0357_1352328158675_718110_20377_exists);
                elaborationRule166 = addElaborationRule(condition166, _17_0_2_edc0357_1352328156645_46192_19618.this, Customer._17_0_2_edc0357_1352328156645_46192_19618._17_0_2_edc0357_1352328158675_718110_20377.class, "CDR_decide_particip_DecisionNode_setDRCap", arguments166);
                Expression<?>[] arguments167 = new Expression<?>[1];
                arguments167[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition167 = new Expression<Boolean>(_17_0_2_edc0357_1352328158677_175784_20381_exists);
                elaborationRule167 = addElaborationRule(condition167, _17_0_2_edc0357_1352328156645_46192_19618.this, Customer._17_0_2_edc0357_1352328156645_46192_19618._17_0_2_edc0357_1352328158677_175784_20381.class, "CDR_forkself_ForkNode_setDRCap", arguments167);
            }
        }

        public class _17_0_2_edc0357_1352328158673_69485_20374 extends DurativeEvent {

            public _17_0_2_edc0357_1352328158673_69485_20374() {
                super();
                init_17_0_2_edc0357_1352328158673_69485_20374Members();
                init_17_0_2_edc0357_1352328158673_69485_20374Collections();
                init_17_0_2_edc0357_1352328158673_69485_20374Elaborations();
            }

            public _17_0_2_edc0357_1352328158673_69485_20374(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328158673_69485_20374Members();
                init_17_0_2_edc0357_1352328158673_69485_20374Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328158673_69485_20374Elaborations();
                fixTimeDependencies();
            }

            public IntegerParameter _17_0_2_edc0357_1352328160572_153680_21471 = null;

            public Parameter< Customer > _17_0_2_edc0357_1352328160573_976424_21472 = null;

            public IntegerParameter myDeciderID_decider_17_0_2_edc0357_1352328158677_26057_20380 = null;

            public BooleanParameter _17_0_2_edc0357_1352328158677_26057_20380_exists = null;

            public BooleanParameter objectToPass = null;

            public Dependency< Integer > _17_0_2_edc0357_1352328160572_153680_21471Dependency = null;

            public Dependency< Customer > _17_0_2_edc0357_1352328160573_976424_21472Dependency = null;

            public Dependency< Integer > myDeciderID_decider_17_0_2_edc0357_1352328158677_26057_20380Dependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158677_26057_20380_existsDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Effect effect168 = null;

            public Parameter effect168Var = null;

            public Effect effect169 = null;

            public Parameter effect169Var = null;

            public Effect effect170 = null;

            public Parameter effect170Var = null;

            public ElaborationRule elaborationRule171 = null;

            public void init_17_0_2_edc0357_1352328158673_69485_20374Members() {
                try {
                    if (_17_0_2_edc0357_1352328160572_153680_21471 == null) _17_0_2_edc0357_1352328160572_153680_21471 = new IntegerParameter("_17_0_2_edc0357_1352328160572_153680_21471", (Integer) null, this);
                    if (_17_0_2_edc0357_1352328160573_976424_21472 == null) _17_0_2_edc0357_1352328160573_976424_21472 = new Parameter<Customer>("_17_0_2_edc0357_1352328160573_976424_21472", null, (Customer) null, this);
                    if (myDeciderID_decider_17_0_2_edc0357_1352328158677_26057_20380 == null) myDeciderID_decider_17_0_2_edc0357_1352328158677_26057_20380 = new IntegerParameter("myDeciderID_decider_17_0_2_edc0357_1352328158677_26057_20380", (Integer) 3, this);
                    if (_17_0_2_edc0357_1352328158677_26057_20380_exists == null) _17_0_2_edc0357_1352328158677_26057_20380_exists = new BooleanParameter("_17_0_2_edc0357_1352328158677_26057_20380_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect168VarV = sig_17_0_2_edc0357_1352328158680_379016_20389;
                    effect168Var = new Parameter("effect168Var", null, null, this);
                    addDependency(effect168Var, new Expression(effect168VarV));
                    effect168 = new EffectFunction(new EffectFunction(effect168Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect169VarV = decider_17_0_2_edc0357_1352328158677_26057_20380;
                    effect169Var = new Parameter("effect169Var", null, null, this);
                    addDependency(effect169Var, new Expression(effect169VarV));
                    effect169 = new EffectFunction(new EffectFunction(effect169Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, myDeciderID_decider_17_0_2_edc0357_1352328158677_26057_20380 }));
                    Object effect170VarV = cap__17_0_2_edc0357_1352328156659_30024_19625;
                    effect170Var = new Parameter("effect170Var", null, null, this);
                    addDependency(effect170Var, new Expression(effect170VarV));
                    effect170 = new EffectFunction(new EffectFunction(effect170Var, ClassUtils.getMethodForArgTypes("TimeVaryingPlottableMap<Integer>", "powersystem", "setValue", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { startTime, _17_0_2_edc0357_1352328160572_153680_21471 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158673_69485_20374Collections() {
                parameters.add(_17_0_2_edc0357_1352328160572_153680_21471);
                parameters.add(_17_0_2_edc0357_1352328160573_976424_21472);
                parameters.add(myDeciderID_decider_17_0_2_edc0357_1352328158677_26057_20380);
                parameters.add(_17_0_2_edc0357_1352328158677_26057_20380_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect168Var = new TreeSet<Effect>();
                effectsForeffect168Var.add(effect168);
                addEffects((Parameter<?>) effect168Var, effectsForeffect168Var);
                Set<Effect> effectsForeffect169Var = new TreeSet<Effect>();
                effectsForeffect169Var.add(effect169);
                addEffects((Parameter<?>) effect169Var, effectsForeffect169Var);
                Set<Effect> effectsForeffect170Var = new TreeSet<Effect>();
                effectsForeffect170Var.add(effect170);
                addEffects((Parameter<?>) effect170Var, effectsForeffect170Var);
            }

            public void init_17_0_2_edc0357_1352328158673_69485_20374Dependencies() {
                addDependency(_17_0_2_edc0357_1352328160572_153680_21471, new Expression<Integer>(new EffectFunction(sig_17_0_2_edc0357_1352328158680_450048_20388, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_edc0357_1352328160573_976424_21472, new Expression<Customer>(new EffectFunction(sig_17_0_2_edc0357_1352328158680_802723_20392, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(myDeciderID_decider_17_0_2_edc0357_1352328158677_26057_20380, new Expression<Integer>(3));
                addDependency(_17_0_2_edc0357_1352328158677_26057_20380_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158677_26057_20380, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158677_26057_20380, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158677_26057_20380, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_edc0357_1352328158677_26057_20380)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_edc0357_1352328158674_624952_20376_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_edc0357_1352328158674_624952_20376_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new EffectFunction(sig_17_0_2_edc0357_1352328158679_596112_20386, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_edc0357_1352328158673_69485_20374Elaborations() {
                init_17_0_2_edc0357_1352328158673_69485_20374Dependencies();
                Expression<?>[] arguments171 = new Expression<?>[1];
                arguments171[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition171 = new Expression<Boolean>(_17_0_2_edc0357_1352328158677_26057_20380_exists);
                elaborationRule171 = addElaborationRule(condition171, _17_0_2_edc0357_1352328156645_46192_19618.this, Customer._17_0_2_edc0357_1352328156645_46192_19618._17_0_2_edc0357_1352328158677_26057_20380.class, "CDR_send_yes_SendSignalAction_setDRCap", arguments171);
            }
        }

        public class _17_0_2_edc0357_1352328158674_234414_20375 extends DurativeEvent {

            public _17_0_2_edc0357_1352328158674_234414_20375() {
                super();
                init_17_0_2_edc0357_1352328158674_234414_20375Members();
                init_17_0_2_edc0357_1352328158674_234414_20375Collections();
                init_17_0_2_edc0357_1352328158674_234414_20375Elaborations();
            }

            public _17_0_2_edc0357_1352328158674_234414_20375(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328158674_234414_20375Members();
                init_17_0_2_edc0357_1352328158674_234414_20375Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328158674_234414_20375Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_edc0357_1352328158679_514534_20384_exists = null;

            public BooleanParameter objectToPass = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158679_514534_20384_existsDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Effect effect172 = null;

            public Parameter effect172Var = null;

            public ElaborationRule elaborationRule173 = null;

            public void init_17_0_2_edc0357_1352328158674_234414_20375Members() {
                try {
                    if (_17_0_2_edc0357_1352328158679_514534_20384_exists == null) _17_0_2_edc0357_1352328158679_514534_20384_exists = new BooleanParameter("_17_0_2_edc0357_1352328158679_514534_20384_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect172VarV = sig_17_0_2_edc0357_1352328158681_221195_20398;
                    effect172Var = new Parameter("effect172Var", null, null, this);
                    addDependency(effect172Var, new Expression(effect172VarV));
                    effect172 = new EffectFunction(new EffectFunction(effect172Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158674_234414_20375Collections() {
                parameters.add(_17_0_2_edc0357_1352328158679_514534_20384_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect172Var = new TreeSet<Effect>();
                effectsForeffect172Var.add(effect172);
                addEffects((Parameter<?>) effect172Var, effectsForeffect172Var);
            }

            public void init_17_0_2_edc0357_1352328158674_234414_20375Dependencies() {
                addDependency(_17_0_2_edc0357_1352328158679_514534_20384_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_edc0357_1352328158674_624952_20376_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_edc0357_1352328158674_624952_20376_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(true));
            }

            public void init_17_0_2_edc0357_1352328158674_234414_20375Elaborations() {
                init_17_0_2_edc0357_1352328158674_234414_20375Dependencies();
                Expression<?>[] arguments173 = new Expression<?>[1];
                arguments173[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition173 = new Expression<Boolean>(_17_0_2_edc0357_1352328158679_514534_20384_exists);
                elaborationRule173 = addElaborationRule(condition173, _17_0_2_edc0357_1352328156645_46192_19618.this, Customer._17_0_2_edc0357_1352328156645_46192_19618._17_0_2_edc0357_1352328158679_514534_20384.class, "CDR_fork1_ForkNode_setDRCap", arguments173);
            }
        }

        public class _17_0_2_edc0357_1352328158674_624952_20376 extends DurativeEvent {

            public _17_0_2_edc0357_1352328158674_624952_20376() {
                super();
                init_17_0_2_edc0357_1352328158674_624952_20376Members();
                init_17_0_2_edc0357_1352328158674_624952_20376Collections();
                init_17_0_2_edc0357_1352328158674_624952_20376Elaborations();
            }

            public _17_0_2_edc0357_1352328158674_624952_20376(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328158674_624952_20376Members();
                init_17_0_2_edc0357_1352328158674_624952_20376Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328158674_624952_20376Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter objectToPass = null;

            public Dependency< Integer > endTimeDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Dependency< Integer > finalNode_endTimeDependency = null;

            public void init_17_0_2_edc0357_1352328158674_624952_20376Members() {
                try {
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158674_624952_20376Collections() {
                parameters.add(objectToPass);
            }

            public void init_17_0_2_edc0357_1352328158674_624952_20376Dependencies() {
                addDependency(endTime, new Expression<Integer>(new Functions.Plus(new Expression<Integer>(startTime), new Expression<Integer>(duration))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new EffectFunction(sig_17_0_2_edc0357_1352328158681_50786_20403, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(finalNode_endTime, new Expression<Integer>(endTime));
            }

            public void init_17_0_2_edc0357_1352328158674_624952_20376Elaborations() {
                init_17_0_2_edc0357_1352328158674_624952_20376Dependencies();
            }
        }

        public class _17_0_2_edc0357_1352328158675_718110_20377 extends DurativeEvent {

            public _17_0_2_edc0357_1352328158675_718110_20377() {
                super();
                init_17_0_2_edc0357_1352328158675_718110_20377Members();
                init_17_0_2_edc0357_1352328158675_718110_20377Collections();
                init_17_0_2_edc0357_1352328158675_718110_20377Elaborations();
            }

            public _17_0_2_edc0357_1352328158675_718110_20377(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328158675_718110_20377Members();
                init_17_0_2_edc0357_1352328158675_718110_20377Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328158675_718110_20377Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_edc0357_1352328158675_13172_20378_exists = null;

            public BooleanParameter objectToPass = null;

            public BooleanParameter decisionInput = null;

            public IntegerParameter myDeciderID_decider_17_0_2_edc0357_1352328158673_69485_20374 = null;

            public BooleanParameter _17_0_2_edc0357_1352328158673_69485_20374_exists = null;

            public IntegerParameter myDeciderID_decider_17_0_2_edc0357_1352328158675_13172_20378 = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > decisionInputDependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158673_69485_20374_existsDependency = null;

            public Dependency< Integer > myDeciderID_decider_17_0_2_edc0357_1352328158673_69485_20374Dependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158675_13172_20378_existsDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Dependency< Integer > myDeciderID_decider_17_0_2_edc0357_1352328158675_13172_20378Dependency = null;

            public Effect effect174 = null;

            public Parameter effect174Var = null;

            public Effect effect175 = null;

            public Parameter effect175Var = null;

            public Effect effect176 = null;

            public Parameter effect176Var = null;

            public Effect effect177 = null;

            public Parameter effect177Var = null;

            public ElaborationRule elaborationRule178 = null;

            public ElaborationRule elaborationRule179 = null;

            public void init_17_0_2_edc0357_1352328158675_718110_20377Members() {
                try {
                    if (_17_0_2_edc0357_1352328158675_13172_20378_exists == null) _17_0_2_edc0357_1352328158675_13172_20378_exists = new BooleanParameter("_17_0_2_edc0357_1352328158675_13172_20378_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (decisionInput == null) decisionInput = new BooleanParameter("decisionInput", (Boolean) null, this);
                    if (myDeciderID_decider_17_0_2_edc0357_1352328158673_69485_20374 == null) myDeciderID_decider_17_0_2_edc0357_1352328158673_69485_20374 = new IntegerParameter("myDeciderID_decider_17_0_2_edc0357_1352328158673_69485_20374", (Integer) 3, this);
                    if (_17_0_2_edc0357_1352328158673_69485_20374_exists == null) _17_0_2_edc0357_1352328158673_69485_20374_exists = new BooleanParameter("_17_0_2_edc0357_1352328158673_69485_20374_exists", (Boolean) false, this);
                    if (myDeciderID_decider_17_0_2_edc0357_1352328158675_13172_20378 == null) myDeciderID_decider_17_0_2_edc0357_1352328158675_13172_20378 = new IntegerParameter("myDeciderID_decider_17_0_2_edc0357_1352328158675_13172_20378", (Integer) 2, this);
                    Object effect174VarV = sig_17_0_2_edc0357_1352328158679_596112_20386;
                    effect174Var = new Parameter("effect174Var", null, null, this);
                    addDependency(effect174Var, new Expression(effect174VarV));
                    effect174 = new EffectFunction(new EffectFunction(effect174Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "sendIf", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class, java.lang.Boolean.class), new Object[] { objectToPass, endTime, _17_0_2_edc0357_1352328158673_69485_20374_exists }));
                    Object effect175VarV = sig_17_0_2_edc0357_1352328158679_76145_20387;
                    effect175Var = new Parameter("effect175Var", null, null, this);
                    addDependency(effect175Var, new Expression(effect175VarV));
                    effect175 = new EffectFunction(new EffectFunction(effect175Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "sendIf", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class, java.lang.Boolean.class), new Object[] { objectToPass, endTime, _17_0_2_edc0357_1352328158675_13172_20378_exists }));
                    Object effect176VarV = decider_17_0_2_edc0357_1352328158675_13172_20378;
                    effect176Var = new Parameter("effect176Var", null, null, this);
                    addDependency(effect176Var, new Expression(effect176VarV));
                    effect176 = new EffectFunction(new EffectFunction(effect176Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, myDeciderID_decider_17_0_2_edc0357_1352328158675_13172_20378 }));
                    Object effect177VarV = decider_17_0_2_edc0357_1352328158673_69485_20374;
                    effect177Var = new Parameter("effect177Var", null, null, this);
                    addDependency(effect177Var, new Expression(effect177VarV));
                    effect177 = new EffectFunction(new EffectFunction(effect177Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, myDeciderID_decider_17_0_2_edc0357_1352328158673_69485_20374 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158675_718110_20377Collections() {
                parameters.add(_17_0_2_edc0357_1352328158675_13172_20378_exists);
                parameters.add(objectToPass);
                parameters.add(decisionInput);
                parameters.add(myDeciderID_decider_17_0_2_edc0357_1352328158673_69485_20374);
                parameters.add(_17_0_2_edc0357_1352328158673_69485_20374_exists);
                parameters.add(myDeciderID_decider_17_0_2_edc0357_1352328158675_13172_20378);
                Set<Effect> effectsForeffect174Var = new TreeSet<Effect>();
                effectsForeffect174Var.add(effect174);
                addEffects((Parameter<?>) effect174Var, effectsForeffect174Var);
                Set<Effect> effectsForeffect175Var = new TreeSet<Effect>();
                effectsForeffect175Var.add(effect175);
                addEffects((Parameter<?>) effect175Var, effectsForeffect175Var);
                Set<Effect> effectsForeffect176Var = new TreeSet<Effect>();
                effectsForeffect176Var.add(effect176);
                addEffects((Parameter<?>) effect176Var, effectsForeffect176Var);
                Set<Effect> effectsForeffect177Var = new TreeSet<Effect>();
                effectsForeffect177Var.add(effect177);
                addEffects((Parameter<?>) effect177Var, effectsForeffect177Var);
            }

            public void init_17_0_2_edc0357_1352328158675_718110_20377Dependencies() {
                addDependency(duration, new Expression<Integer>(1));
                addDependency(decisionInput, new Expression<Boolean>(new EffectFunction(sig_17_0_2_edc0357_1352328158681_26576_20397, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_edc0357_1352328158673_69485_20374_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(decisionInput), new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158673_69485_20374, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158673_69485_20374, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158673_69485_20374, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_edc0357_1352328158673_69485_20374)))))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_edc0357_1352328158674_624952_20376_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_edc0357_1352328158674_624952_20376_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))))));
                addDependency(myDeciderID_decider_17_0_2_edc0357_1352328158673_69485_20374, new Expression<Integer>(3));
                addDependency(_17_0_2_edc0357_1352328158675_13172_20378_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(decisionInput))), new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158675_13172_20378, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158675_13172_20378, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158675_13172_20378, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_edc0357_1352328158675_13172_20378)))))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_edc0357_1352328158674_624952_20376_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_edc0357_1352328158674_624952_20376_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))))));
                addDependency(objectToPass, new Expression<Boolean>(new EffectFunction(sig_17_0_2_edc0357_1352328158680_247894_20395, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(myDeciderID_decider_17_0_2_edc0357_1352328158675_13172_20378, new Expression<Integer>(2));
            }

            public void init_17_0_2_edc0357_1352328158675_718110_20377Elaborations() {
                init_17_0_2_edc0357_1352328158675_718110_20377Dependencies();
                Expression<?>[] arguments178 = new Expression<?>[1];
                arguments178[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition178 = new Expression<Boolean>(_17_0_2_edc0357_1352328158675_13172_20378_exists);
                elaborationRule178 = addElaborationRule(condition178, _17_0_2_edc0357_1352328156645_46192_19618.this, Customer._17_0_2_edc0357_1352328156645_46192_19618._17_0_2_edc0357_1352328158675_13172_20378.class, "CDR_send_no_SendSignalAction_setDRCap", arguments178);
                Expression<?>[] arguments179 = new Expression<?>[1];
                arguments179[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition179 = new Expression<Boolean>(_17_0_2_edc0357_1352328158673_69485_20374_exists);
                elaborationRule179 = addElaborationRule(condition179, _17_0_2_edc0357_1352328156645_46192_19618.this, Customer._17_0_2_edc0357_1352328156645_46192_19618._17_0_2_edc0357_1352328158673_69485_20374.class, "CDR_set_cap_AddStructuralFeatureValueAction_setDRCap", arguments179);
            }
        }

        public class _17_0_2_edc0357_1352328158675_13172_20378 extends DurativeEvent {

            public _17_0_2_edc0357_1352328158675_13172_20378() {
                super();
                init_17_0_2_edc0357_1352328158675_13172_20378Members();
                init_17_0_2_edc0357_1352328158675_13172_20378Collections();
                init_17_0_2_edc0357_1352328158675_13172_20378Elaborations();
            }

            public _17_0_2_edc0357_1352328158675_13172_20378(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328158675_13172_20378Members();
                init_17_0_2_edc0357_1352328158675_13172_20378Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328158675_13172_20378Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_edc0357_1352328158679_407855_20385_exists = null;

            public Parameter< Customer > _17_0_2_edc0357_1352328160573_64130_21473 = null;

            public BooleanParameter objectToPass = null;

            public Parameter< Power_System.Signalno > signalObject = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158679_407855_20385_existsDependency = null;

            public Dependency< Customer > _17_0_2_edc0357_1352328160573_64130_21473Dependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Dependency< Power_System.Signalno > signalObjectDependency = null;

            public Effect effect180 = null;

            public Parameter effect180Var = null;

            public Effect effect181 = null;

            public Parameter effect181Var = null;

            public Effect effect182 = null;

            public Parameter effect182Var = null;

            public ElaborationRule elaborationRule183 = null;

            public void init_17_0_2_edc0357_1352328158675_13172_20378Members() {
                try {
                    if (_17_0_2_edc0357_1352328158679_407855_20385_exists == null) _17_0_2_edc0357_1352328158679_407855_20385_exists = new BooleanParameter("_17_0_2_edc0357_1352328158679_407855_20385_exists", (Boolean) false, this);
                    if (_17_0_2_edc0357_1352328160573_64130_21473 == null) _17_0_2_edc0357_1352328160573_64130_21473 = new Parameter<Customer>("_17_0_2_edc0357_1352328160573_64130_21473", null, (Customer) null, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (signalObject == null) signalObject = new Parameter<Power_System.Signalno>("signalObject", null, (Power_System.Signalno) null, this);
                    Object effect180VarV = sig_17_0_2_edc0357_1352328158681_384297_20401;
                    effect180Var = new Parameter("effect180Var", null, null, this);
                    addDependency(effect180Var, new Expression(effect180VarV));
                    effect180 = new EffectFunction(new EffectFunction(effect180Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect181VarV = new FunctionCall(x, Parameter.class, "getMember", new Object[] { "ss_17_0_2_edc0357_1352328156651_739611_19623_no" });
                    effect181Var = new Parameter("effect181Var", null, null, this);
                    addDependency(effect181Var, new Expression(effect181VarV));
                    effect181 = new EffectFunction(new EffectFunction(null, ClassUtils.getMethodForArgTypes("ObjectFlow<Signalno>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { signalObject, endTime }, effect181Var));
                    Object effect182VarV = new FunctionCall(signalObject, Parameter.class, "getMember", new Object[] { "control" });
                    effect182Var = new Parameter("effect182Var", null, null, this);
                    addDependency(effect182Var, new Expression(effect182VarV));
                    effect182 = new EffectFunction(new EffectFunction(null, ClassUtils.getMethodForArgTypes("TimeVaryingMap<Boolean>", "powersystem", "setValue", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, true }, effect182Var));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158675_13172_20378Collections() {
                parameters.add(_17_0_2_edc0357_1352328158679_407855_20385_exists);
                parameters.add(_17_0_2_edc0357_1352328160573_64130_21473);
                parameters.add(objectToPass);
                parameters.add(signalObject);
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

            public void init_17_0_2_edc0357_1352328158675_13172_20378Dependencies() {
                addDependency(_17_0_2_edc0357_1352328158679_407855_20385_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_edc0357_1352328158674_624952_20376_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_edc0357_1352328158674_624952_20376_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(_17_0_2_edc0357_1352328160573_64130_21473, new Expression<Customer>(new EffectFunction(sig_17_0_2_edc0357_1352328158680_928200_20394, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new EffectFunction(sig_17_0_2_edc0357_1352328158679_76145_20387, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(signalObject, new Expression<Power_System.Signalno>(new ConstructorCall(x, ClassUtils.getConstructorForArgTypes(Power_System.Signalno.class), new Object[] {})));
            }

            public void init_17_0_2_edc0357_1352328158675_13172_20378Elaborations() {
                init_17_0_2_edc0357_1352328158675_13172_20378Dependencies();
                Expression<?>[] arguments183 = new Expression<?>[2];
                arguments183[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                arguments183[1] = new Expression<ObjectFlow<Boolean>>(sig_17_0_2_edc0357_1352328158681_384297_20401);
                Expression<Boolean> condition183 = new Expression<Boolean>(_17_0_2_edc0357_1352328158679_407855_20385_exists);
                elaborationRule183 = addElaborationRule(condition183, _17_0_2_edc0357_1352328156645_46192_19618.this, Customer._17_0_2_edc0357_1352328156645_46192_19618._17_0_2_edc0357_1352328158679_407855_20385.class, "CDR_merge_response_MergeNode_setDRCap", arguments183);
            }
        }

        public class _17_0_2_edc0357_1352328158676_652582_20379 extends DurativeEvent {

            public _17_0_2_edc0357_1352328158676_652582_20379() {
                super();
                init_17_0_2_edc0357_1352328158676_652582_20379Members();
                init_17_0_2_edc0357_1352328158676_652582_20379Collections();
                init_17_0_2_edc0357_1352328158676_652582_20379Elaborations();
            }

            public _17_0_2_edc0357_1352328158676_652582_20379(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328158676_652582_20379Members();
                init_17_0_2_edc0357_1352328158676_652582_20379Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328158676_652582_20379Elaborations();
                fixTimeDependencies();
            }

            public IntegerParameter myDeciderID_decider_17_0_2_edc0357_1352328158677_26057_20380 = null;

            public BooleanParameter _17_0_2_edc0357_1352328158677_26057_20380_exists = null;

            public IntegerParameter objectToPass = null;

            public IntegerParameter myDeciderID_decider_17_0_2_edc0357_1352328158673_69485_20374 = null;

            public BooleanParameter _17_0_2_edc0357_1352328158673_69485_20374_exists = null;

            public Dependency< Integer > myDeciderID_decider_17_0_2_edc0357_1352328158677_26057_20380Dependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158677_26057_20380_existsDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Integer > objectToPassDependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158673_69485_20374_existsDependency = null;

            public Dependency< Integer > myDeciderID_decider_17_0_2_edc0357_1352328158673_69485_20374Dependency = null;

            public Effect effect184 = null;

            public Parameter effect184Var = null;

            public Effect effect185 = null;

            public Parameter effect185Var = null;

            public Effect effect186 = null;

            public Parameter effect186Var = null;

            public Effect effect187 = null;

            public Parameter effect187Var = null;

            public ElaborationRule elaborationRule188 = null;

            public ElaborationRule elaborationRule189 = null;

            public void init_17_0_2_edc0357_1352328158676_652582_20379Members() {
                try {
                    if (myDeciderID_decider_17_0_2_edc0357_1352328158677_26057_20380 == null) myDeciderID_decider_17_0_2_edc0357_1352328158677_26057_20380 = new IntegerParameter("myDeciderID_decider_17_0_2_edc0357_1352328158677_26057_20380", (Integer) 1, this);
                    if (_17_0_2_edc0357_1352328158677_26057_20380_exists == null) _17_0_2_edc0357_1352328158677_26057_20380_exists = new BooleanParameter("_17_0_2_edc0357_1352328158677_26057_20380_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new IntegerParameter("objectToPass", (Integer) null, this);
                    if (myDeciderID_decider_17_0_2_edc0357_1352328158673_69485_20374 == null) myDeciderID_decider_17_0_2_edc0357_1352328158673_69485_20374 = new IntegerParameter("myDeciderID_decider_17_0_2_edc0357_1352328158673_69485_20374", (Integer) 2, this);
                    if (_17_0_2_edc0357_1352328158673_69485_20374_exists == null) _17_0_2_edc0357_1352328158673_69485_20374_exists = new BooleanParameter("_17_0_2_edc0357_1352328158673_69485_20374_exists", (Boolean) false, this);
                    Object effect184VarV = sig_17_0_2_edc0357_1352328158680_450048_20388;
                    effect184Var = new Parameter("effect184Var", null, null, this);
                    addDependency(effect184Var, new Expression(effect184VarV));
                    effect184 = new EffectFunction(new EffectFunction(effect184Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect185VarV = sig_17_0_2_edc0357_1352328158680_232103_20390;
                    effect185Var = new Parameter("effect185Var", null, null, this);
                    addDependency(effect185Var, new Expression(effect185VarV));
                    effect185 = new EffectFunction(new EffectFunction(effect185Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect186VarV = decider_17_0_2_edc0357_1352328158677_26057_20380;
                    effect186Var = new Parameter("effect186Var", null, null, this);
                    addDependency(effect186Var, new Expression(effect186VarV));
                    effect186 = new EffectFunction(new EffectFunction(effect186Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, myDeciderID_decider_17_0_2_edc0357_1352328158677_26057_20380 }));
                    Object effect187VarV = decider_17_0_2_edc0357_1352328158673_69485_20374;
                    effect187Var = new Parameter("effect187Var", null, null, this);
                    addDependency(effect187Var, new Expression(effect187VarV));
                    effect187 = new EffectFunction(new EffectFunction(effect187Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, myDeciderID_decider_17_0_2_edc0357_1352328158673_69485_20374 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158676_652582_20379Collections() {
                parameters.add(myDeciderID_decider_17_0_2_edc0357_1352328158677_26057_20380);
                parameters.add(_17_0_2_edc0357_1352328158677_26057_20380_exists);
                parameters.add(objectToPass);
                parameters.add(myDeciderID_decider_17_0_2_edc0357_1352328158673_69485_20374);
                parameters.add(_17_0_2_edc0357_1352328158673_69485_20374_exists);
                Set<Effect> effectsForeffect184Var = new TreeSet<Effect>();
                effectsForeffect184Var.add(effect184);
                addEffects((Parameter<?>) effect184Var, effectsForeffect184Var);
                Set<Effect> effectsForeffect185Var = new TreeSet<Effect>();
                effectsForeffect185Var.add(effect185);
                addEffects((Parameter<?>) effect185Var, effectsForeffect185Var);
                Set<Effect> effectsForeffect186Var = new TreeSet<Effect>();
                effectsForeffect186Var.add(effect186);
                addEffects((Parameter<?>) effect186Var, effectsForeffect186Var);
                Set<Effect> effectsForeffect187Var = new TreeSet<Effect>();
                effectsForeffect187Var.add(effect187);
                addEffects((Parameter<?>) effect187Var, effectsForeffect187Var);
            }

            public void init_17_0_2_edc0357_1352328158676_652582_20379Dependencies() {
                addDependency(myDeciderID_decider_17_0_2_edc0357_1352328158677_26057_20380, new Expression<Integer>(1));
                addDependency(_17_0_2_edc0357_1352328158677_26057_20380_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158677_26057_20380, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158677_26057_20380, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158677_26057_20380, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_edc0357_1352328158677_26057_20380)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_edc0357_1352328158674_624952_20376_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_edc0357_1352328158674_624952_20376_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Integer>(new EffectFunction(sig_17_0_2_edc0357_1352328158681_29423_20396, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_edc0357_1352328158673_69485_20374_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158673_69485_20374, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158673_69485_20374, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158673_69485_20374, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_edc0357_1352328158673_69485_20374)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_edc0357_1352328158674_624952_20376_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_edc0357_1352328158674_624952_20376_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))))));
                addDependency(myDeciderID_decider_17_0_2_edc0357_1352328158673_69485_20374, new Expression<Integer>(2));
            }

            public void init_17_0_2_edc0357_1352328158676_652582_20379Elaborations() {
                init_17_0_2_edc0357_1352328158676_652582_20379Dependencies();
                Expression<?>[] arguments188 = new Expression<?>[1];
                arguments188[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition188 = new Expression<Boolean>(_17_0_2_edc0357_1352328158677_26057_20380_exists);
                elaborationRule188 = addElaborationRule(condition188, _17_0_2_edc0357_1352328156645_46192_19618.this, Customer._17_0_2_edc0357_1352328156645_46192_19618._17_0_2_edc0357_1352328158677_26057_20380.class, "CDR_send_yes_SendSignalAction_setDRCap", arguments188);
                Expression<?>[] arguments189 = new Expression<?>[1];
                arguments189[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition189 = new Expression<Boolean>(_17_0_2_edc0357_1352328158673_69485_20374_exists);
                elaborationRule189 = addElaborationRule(condition189, _17_0_2_edc0357_1352328156645_46192_19618.this, Customer._17_0_2_edc0357_1352328156645_46192_19618._17_0_2_edc0357_1352328158673_69485_20374.class, "CDR_set_cap_AddStructuralFeatureValueAction_setDRCap", arguments189);
            }
        }

        public class _17_0_2_edc0357_1352328158677_26057_20380 extends DurativeEvent {

            public _17_0_2_edc0357_1352328158677_26057_20380() {
                super();
                init_17_0_2_edc0357_1352328158677_26057_20380Members();
                init_17_0_2_edc0357_1352328158677_26057_20380Collections();
                init_17_0_2_edc0357_1352328158677_26057_20380Elaborations();
            }

            public _17_0_2_edc0357_1352328158677_26057_20380(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328158677_26057_20380Members();
                init_17_0_2_edc0357_1352328158677_26057_20380Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328158677_26057_20380Elaborations();
                fixTimeDependencies();
            }

            public Parameter< Customer > _17_0_2_edc0357_1352328160574_879011_21474 = null;

            public BooleanParameter _17_0_2_edc0357_1352328158679_407855_20385_exists = null;

            public IntegerParameter _17_0_2_edc0357_1352328160575_146405_21475 = null;

            public BooleanParameter objectToPass = null;

            public Parameter< Power_System.Signalyes > signalObject = null;

            public Dependency< Customer > _17_0_2_edc0357_1352328160574_879011_21474Dependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158679_407855_20385_existsDependency = null;

            public Dependency< Integer > _17_0_2_edc0357_1352328160575_146405_21475Dependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Dependency< Power_System.Signalyes > signalObjectDependency = null;

            public Effect effect190 = null;

            public Parameter effect190Var = null;

            public Effect effect191 = null;

            public Parameter effect191Var = null;

            public Effect effect192 = null;

            public Parameter effect192Var = null;

            public ElaborationRule elaborationRule193 = null;

            public void init_17_0_2_edc0357_1352328158677_26057_20380Members() {
                try {
                    if (_17_0_2_edc0357_1352328160574_879011_21474 == null) _17_0_2_edc0357_1352328160574_879011_21474 = new Parameter<Customer>("_17_0_2_edc0357_1352328160574_879011_21474", null, (Customer) null, this);
                    if (_17_0_2_edc0357_1352328158679_407855_20385_exists == null) _17_0_2_edc0357_1352328158679_407855_20385_exists = new BooleanParameter("_17_0_2_edc0357_1352328158679_407855_20385_exists", (Boolean) false, this);
                    if (_17_0_2_edc0357_1352328160575_146405_21475 == null) _17_0_2_edc0357_1352328160575_146405_21475 = new IntegerParameter("_17_0_2_edc0357_1352328160575_146405_21475", (Integer) null, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (signalObject == null) signalObject = new Parameter<Power_System.Signalyes>("signalObject", null, (Power_System.Signalyes) null, this);
                    Object effect190VarV = sig_17_0_2_edc0357_1352328158681_60367_20402;
                    effect190Var = new Parameter("effect190Var", null, null, this);
                    addDependency(effect190Var, new Expression(effect190VarV));
                    effect190 = new EffectFunction(new EffectFunction(effect190Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect191VarV = new FunctionCall(x, Parameter.class, "getMember", new Object[] { "ss_17_0_2_edc0357_1352328156651_739611_19623_yes" });
                    effect191Var = new Parameter("effect191Var", null, null, this);
                    addDependency(effect191Var, new Expression(effect191VarV));
                    effect191 = new EffectFunction(new EffectFunction(null, ClassUtils.getMethodForArgTypes("ObjectFlow<Signalyes>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { signalObject, endTime }, effect191Var));
                    Object effect192VarV = new FunctionCall(signalObject, Parameter.class, "getMember", new Object[] { "newLoad__17_0_2_edc0357_1352328156888_707483_19708" });
                    effect192Var = new Parameter("effect192Var", null, null, this);
                    addDependency(effect192Var, new Expression(effect192VarV));
                    effect192 = new EffectFunction(new EffectFunction(null, ClassUtils.getMethodForArgTypes("TimeVaryingMap<Integer>", "powersystem", "setValue", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, _17_0_2_edc0357_1352328160575_146405_21475 }, effect192Var));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158677_26057_20380Collections() {
                parameters.add(_17_0_2_edc0357_1352328160574_879011_21474);
                parameters.add(_17_0_2_edc0357_1352328158679_407855_20385_exists);
                parameters.add(_17_0_2_edc0357_1352328160575_146405_21475);
                parameters.add(objectToPass);
                parameters.add(signalObject);
                Set<Effect> effectsForeffect190Var = new TreeSet<Effect>();
                effectsForeffect190Var.add(effect190);
                addEffects((Parameter<?>) effect190Var, effectsForeffect190Var);
                Set<Effect> effectsForeffect191Var = new TreeSet<Effect>();
                effectsForeffect191Var.add(effect191);
                addEffects((Parameter<?>) effect191Var, effectsForeffect191Var);
                Set<Effect> effectsForeffect192Var = new TreeSet<Effect>();
                effectsForeffect192Var.add(effect192);
                addEffects((Parameter<?>) effect192Var, effectsForeffect192Var);
            }

            public void init_17_0_2_edc0357_1352328158677_26057_20380Dependencies() {
                addDependency(_17_0_2_edc0357_1352328160574_879011_21474, new Expression<Customer>(new EffectFunction(sig_17_0_2_edc0357_1352328158680_930496_20393, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_edc0357_1352328158679_407855_20385_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_edc0357_1352328158674_624952_20376_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_edc0357_1352328158674_624952_20376_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(_17_0_2_edc0357_1352328160575_146405_21475, new Expression<Integer>(new EffectFunction(sig_17_0_2_edc0357_1352328158680_232103_20390, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new EffectFunction(sig_17_0_2_edc0357_1352328158680_379016_20389, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(signalObject, new Expression<Power_System.Signalyes>(new ConstructorCall(x, ClassUtils.getConstructorForArgTypes(Power_System.Signalyes.class), new Object[] {})));
            }

            public void init_17_0_2_edc0357_1352328158677_26057_20380Elaborations() {
                init_17_0_2_edc0357_1352328158677_26057_20380Dependencies();
                Expression<?>[] arguments193 = new Expression<?>[2];
                arguments193[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                arguments193[1] = new Expression<ObjectFlow<Boolean>>(sig_17_0_2_edc0357_1352328158681_60367_20402);
                Expression<Boolean> condition193 = new Expression<Boolean>(_17_0_2_edc0357_1352328158679_407855_20385_exists);
                elaborationRule193 = addElaborationRule(condition193, _17_0_2_edc0357_1352328156645_46192_19618.this, Customer._17_0_2_edc0357_1352328156645_46192_19618._17_0_2_edc0357_1352328158679_407855_20385.class, "CDR_merge_response_MergeNode_setDRCap", arguments193);
            }
        }

        public class _17_0_2_edc0357_1352328158677_175784_20381 extends DurativeEvent {

            public _17_0_2_edc0357_1352328158677_175784_20381() {
                super();
                init_17_0_2_edc0357_1352328158677_175784_20381Members();
                init_17_0_2_edc0357_1352328158677_175784_20381Collections();
                init_17_0_2_edc0357_1352328158677_175784_20381Elaborations();
            }

            public _17_0_2_edc0357_1352328158677_175784_20381(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328158677_175784_20381Members();
                init_17_0_2_edc0357_1352328158677_175784_20381Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328158677_175784_20381Elaborations();
                fixTimeDependencies();
            }

            public IntegerParameter myDeciderID_decider_17_0_2_edc0357_1352328158677_26057_20380 = null;

            public BooleanParameter _17_0_2_edc0357_1352328158675_13172_20378_exists = null;

            public BooleanParameter _17_0_2_edc0357_1352328158677_26057_20380_exists = null;

            public Parameter< Customer > objectToPass = null;

            public IntegerParameter myDeciderID_decider_17_0_2_edc0357_1352328158673_69485_20374 = null;

            public BooleanParameter _17_0_2_edc0357_1352328158673_69485_20374_exists = null;

            public IntegerParameter myDeciderID_decider_17_0_2_edc0357_1352328158675_13172_20378 = null;

            public Dependency< Integer > myDeciderID_decider_17_0_2_edc0357_1352328158677_26057_20380Dependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158675_13172_20378_existsDependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158677_26057_20380_existsDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Customer > objectToPassDependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158673_69485_20374_existsDependency = null;

            public Dependency< Integer > myDeciderID_decider_17_0_2_edc0357_1352328158673_69485_20374Dependency = null;

            public Dependency< Integer > myDeciderID_decider_17_0_2_edc0357_1352328158675_13172_20378Dependency = null;

            public Effect effect194 = null;

            public Parameter effect194Var = null;

            public Effect effect195 = null;

            public Parameter effect195Var = null;

            public Effect effect196 = null;

            public Parameter effect196Var = null;

            public Effect effect197 = null;

            public Parameter effect197Var = null;

            public Effect effect198 = null;

            public Parameter effect198Var = null;

            public Effect effect199 = null;

            public Parameter effect199Var = null;

            public ElaborationRule elaborationRule200 = null;

            public ElaborationRule elaborationRule201 = null;

            public ElaborationRule elaborationRule202 = null;

            public void init_17_0_2_edc0357_1352328158677_175784_20381Members() {
                try {
                    if (myDeciderID_decider_17_0_2_edc0357_1352328158677_26057_20380 == null) myDeciderID_decider_17_0_2_edc0357_1352328158677_26057_20380 = new IntegerParameter("myDeciderID_decider_17_0_2_edc0357_1352328158677_26057_20380", (Integer) 2, this);
                    if (_17_0_2_edc0357_1352328158675_13172_20378_exists == null) _17_0_2_edc0357_1352328158675_13172_20378_exists = new BooleanParameter("_17_0_2_edc0357_1352328158675_13172_20378_exists", (Boolean) false, this);
                    if (_17_0_2_edc0357_1352328158677_26057_20380_exists == null) _17_0_2_edc0357_1352328158677_26057_20380_exists = new BooleanParameter("_17_0_2_edc0357_1352328158677_26057_20380_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new Parameter<Customer>("objectToPass", null, (Customer) null, this);
                    if (myDeciderID_decider_17_0_2_edc0357_1352328158673_69485_20374 == null) myDeciderID_decider_17_0_2_edc0357_1352328158673_69485_20374 = new IntegerParameter("myDeciderID_decider_17_0_2_edc0357_1352328158673_69485_20374", (Integer) 1, this);
                    if (_17_0_2_edc0357_1352328158673_69485_20374_exists == null) _17_0_2_edc0357_1352328158673_69485_20374_exists = new BooleanParameter("_17_0_2_edc0357_1352328158673_69485_20374_exists", (Boolean) false, this);
                    if (myDeciderID_decider_17_0_2_edc0357_1352328158675_13172_20378 == null) myDeciderID_decider_17_0_2_edc0357_1352328158675_13172_20378 = new IntegerParameter("myDeciderID_decider_17_0_2_edc0357_1352328158675_13172_20378", (Integer) 1, this);
                    Object effect194VarV = sig_17_0_2_edc0357_1352328158680_802723_20392;
                    effect194Var = new Parameter("effect194Var", null, null, this);
                    addDependency(effect194Var, new Expression(effect194VarV));
                    effect194 = new EffectFunction(new EffectFunction(effect194Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect195VarV = sig_17_0_2_edc0357_1352328158680_930496_20393;
                    effect195Var = new Parameter("effect195Var", null, null, this);
                    addDependency(effect195Var, new Expression(effect195VarV));
                    effect195 = new EffectFunction(new EffectFunction(effect195Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect196VarV = sig_17_0_2_edc0357_1352328158680_928200_20394;
                    effect196Var = new Parameter("effect196Var", null, null, this);
                    addDependency(effect196Var, new Expression(effect196VarV));
                    effect196 = new EffectFunction(new EffectFunction(effect196Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect197VarV = decider_17_0_2_edc0357_1352328158677_26057_20380;
                    effect197Var = new Parameter("effect197Var", null, null, this);
                    addDependency(effect197Var, new Expression(effect197VarV));
                    effect197 = new EffectFunction(new EffectFunction(effect197Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, myDeciderID_decider_17_0_2_edc0357_1352328158677_26057_20380 }));
                    Object effect198VarV = decider_17_0_2_edc0357_1352328158675_13172_20378;
                    effect198Var = new Parameter("effect198Var", null, null, this);
                    addDependency(effect198Var, new Expression(effect198VarV));
                    effect198 = new EffectFunction(new EffectFunction(effect198Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, myDeciderID_decider_17_0_2_edc0357_1352328158675_13172_20378 }));
                    Object effect199VarV = decider_17_0_2_edc0357_1352328158673_69485_20374;
                    effect199Var = new Parameter("effect199Var", null, null, this);
                    addDependency(effect199Var, new Expression(effect199VarV));
                    effect199 = new EffectFunction(new EffectFunction(effect199Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, myDeciderID_decider_17_0_2_edc0357_1352328158673_69485_20374 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158677_175784_20381Collections() {
                parameters.add(myDeciderID_decider_17_0_2_edc0357_1352328158677_26057_20380);
                parameters.add(_17_0_2_edc0357_1352328158675_13172_20378_exists);
                parameters.add(_17_0_2_edc0357_1352328158677_26057_20380_exists);
                parameters.add(objectToPass);
                parameters.add(myDeciderID_decider_17_0_2_edc0357_1352328158673_69485_20374);
                parameters.add(_17_0_2_edc0357_1352328158673_69485_20374_exists);
                parameters.add(myDeciderID_decider_17_0_2_edc0357_1352328158675_13172_20378);
                Set<Effect> effectsForeffect194Var = new TreeSet<Effect>();
                effectsForeffect194Var.add(effect194);
                addEffects((Parameter<?>) effect194Var, effectsForeffect194Var);
                Set<Effect> effectsForeffect195Var = new TreeSet<Effect>();
                effectsForeffect195Var.add(effect195);
                addEffects((Parameter<?>) effect195Var, effectsForeffect195Var);
                Set<Effect> effectsForeffect196Var = new TreeSet<Effect>();
                effectsForeffect196Var.add(effect196);
                addEffects((Parameter<?>) effect196Var, effectsForeffect196Var);
                Set<Effect> effectsForeffect197Var = new TreeSet<Effect>();
                effectsForeffect197Var.add(effect197);
                addEffects((Parameter<?>) effect197Var, effectsForeffect197Var);
                Set<Effect> effectsForeffect198Var = new TreeSet<Effect>();
                effectsForeffect198Var.add(effect198);
                addEffects((Parameter<?>) effect198Var, effectsForeffect198Var);
                Set<Effect> effectsForeffect199Var = new TreeSet<Effect>();
                effectsForeffect199Var.add(effect199);
                addEffects((Parameter<?>) effect199Var, effectsForeffect199Var);
            }

            public void init_17_0_2_edc0357_1352328158677_175784_20381Dependencies() {
                addDependency(myDeciderID_decider_17_0_2_edc0357_1352328158677_26057_20380, new Expression<Integer>(2));
                addDependency(_17_0_2_edc0357_1352328158675_13172_20378_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158675_13172_20378, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158675_13172_20378, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158675_13172_20378, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_edc0357_1352328158675_13172_20378)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_edc0357_1352328158674_624952_20376_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_edc0357_1352328158674_624952_20376_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))))));
                addDependency(_17_0_2_edc0357_1352328158677_26057_20380_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158677_26057_20380, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158677_26057_20380, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158677_26057_20380, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_edc0357_1352328158677_26057_20380)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_edc0357_1352328158674_624952_20376_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_edc0357_1352328158674_624952_20376_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Customer>(new EffectFunction(sig_17_0_2_edc0357_1352328158680_29152_20391, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_edc0357_1352328158673_69485_20374_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158673_69485_20374, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158673_69485_20374, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158673_69485_20374, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_edc0357_1352328158673_69485_20374)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_edc0357_1352328158674_624952_20376_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_edc0357_1352328158674_624952_20376_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))))));
                addDependency(myDeciderID_decider_17_0_2_edc0357_1352328158673_69485_20374, new Expression<Integer>(1));
                addDependency(myDeciderID_decider_17_0_2_edc0357_1352328158675_13172_20378, new Expression<Integer>(1));
            }

            public void init_17_0_2_edc0357_1352328158677_175784_20381Elaborations() {
                init_17_0_2_edc0357_1352328158677_175784_20381Dependencies();
                Expression<?>[] arguments200 = new Expression<?>[1];
                arguments200[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition200 = new Expression<Boolean>(_17_0_2_edc0357_1352328158677_26057_20380_exists);
                elaborationRule200 = addElaborationRule(condition200, _17_0_2_edc0357_1352328156645_46192_19618.this, Customer._17_0_2_edc0357_1352328156645_46192_19618._17_0_2_edc0357_1352328158677_26057_20380.class, "CDR_send_yes_SendSignalAction_setDRCap", arguments200);
                Expression<?>[] arguments201 = new Expression<?>[1];
                arguments201[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition201 = new Expression<Boolean>(_17_0_2_edc0357_1352328158675_13172_20378_exists);
                elaborationRule201 = addElaborationRule(condition201, _17_0_2_edc0357_1352328156645_46192_19618.this, Customer._17_0_2_edc0357_1352328156645_46192_19618._17_0_2_edc0357_1352328158675_13172_20378.class, "CDR_send_no_SendSignalAction_setDRCap", arguments201);
                Expression<?>[] arguments202 = new Expression<?>[1];
                arguments202[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition202 = new Expression<Boolean>(_17_0_2_edc0357_1352328158673_69485_20374_exists);
                elaborationRule202 = addElaborationRule(condition202, _17_0_2_edc0357_1352328156645_46192_19618.this, Customer._17_0_2_edc0357_1352328156645_46192_19618._17_0_2_edc0357_1352328158673_69485_20374.class, "CDR_set_cap_AddStructuralFeatureValueAction_setDRCap", arguments202);
            }
        }

        public class _17_0_2_edc0357_1352328158678_297502_20382 extends DurativeEvent {

            public _17_0_2_edc0357_1352328158678_297502_20382() {
                super();
                init_17_0_2_edc0357_1352328158678_297502_20382Members();
                init_17_0_2_edc0357_1352328158678_297502_20382Collections();
                init_17_0_2_edc0357_1352328158678_297502_20382Elaborations();
            }

            public _17_0_2_edc0357_1352328158678_297502_20382(Expression<Integer> startTime, Expression<Integer> _17_0_2_edc0357_1352328158535_616746_20341) {
                super();
                init_17_0_2_edc0357_1352328158678_297502_20382Members();
                init_17_0_2_edc0357_1352328158678_297502_20382Collections();
                addDependency(this.startTime, startTime);
                addDependency(this._17_0_2_edc0357_1352328158535_616746_20341, _17_0_2_edc0357_1352328158535_616746_20341);
                init_17_0_2_edc0357_1352328158678_297502_20382Elaborations();
                fixTimeDependencies();
            }

            public IntegerParameter _17_0_2_edc0357_1352328158535_616746_20341 = null;

            public BooleanParameter _17_0_2_edc0357_1352328158676_652582_20379_exists = null;

            public IntegerParameter objectToPass = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158676_652582_20379_existsDependency = null;

            public Dependency< Integer > objectToPassDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Effect effect203 = null;

            public Parameter effect203Var = null;

            public ElaborationRule elaborationRule204 = null;

            public void init_17_0_2_edc0357_1352328158678_297502_20382Members() {
                try {
                    if (_17_0_2_edc0357_1352328158535_616746_20341 == null) _17_0_2_edc0357_1352328158535_616746_20341 = new IntegerParameter("_17_0_2_edc0357_1352328158535_616746_20341", (Integer) null, this);
                    if (_17_0_2_edc0357_1352328158676_652582_20379_exists == null) _17_0_2_edc0357_1352328158676_652582_20379_exists = new BooleanParameter("_17_0_2_edc0357_1352328158676_652582_20379_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new IntegerParameter("objectToPass", (Integer) null, this);
                    Object effect203VarV = sig_17_0_2_edc0357_1352328158681_29423_20396;
                    effect203Var = new Parameter("effect203Var", null, null, this);
                    addDependency(effect203Var, new Expression(effect203VarV));
                    effect203 = new EffectFunction(new EffectFunction(effect203Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158678_297502_20382Collections() {
                parameters.add(_17_0_2_edc0357_1352328158535_616746_20341);
                parameters.add(_17_0_2_edc0357_1352328158676_652582_20379_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect203Var = new TreeSet<Effect>();
                effectsForeffect203Var.add(effect203);
                addEffects((Parameter<?>) effect203Var, effectsForeffect203Var);
            }

            public void init_17_0_2_edc0357_1352328158678_297502_20382Dependencies() {
                addDependency(_17_0_2_edc0357_1352328158676_652582_20379_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_edc0357_1352328158674_624952_20376_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_edc0357_1352328158674_624952_20376_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(objectToPass, new Expression<Integer>(_17_0_2_edc0357_1352328158535_616746_20341));
                addDependency(duration, new Expression<Integer>(1));
            }

            public void init_17_0_2_edc0357_1352328158678_297502_20382Elaborations() {
                init_17_0_2_edc0357_1352328158678_297502_20382Dependencies();
                Expression<?>[] arguments204 = new Expression<?>[1];
                arguments204[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition204 = new Expression<Boolean>(_17_0_2_edc0357_1352328158676_652582_20379_exists);
                elaborationRule204 = addElaborationRule(condition204, _17_0_2_edc0357_1352328156645_46192_19618.this, Customer._17_0_2_edc0357_1352328156645_46192_19618._17_0_2_edc0357_1352328158676_652582_20379.class, "CDR_fork2_ForkNode_setDRCap", arguments204);
            }
        }

        public class _17_0_2_edc0357_1352328158678_378610_20383 extends DurativeEvent {

            public _17_0_2_edc0357_1352328158678_378610_20383() {
                super();
                init_17_0_2_edc0357_1352328158678_378610_20383Members();
                init_17_0_2_edc0357_1352328158678_378610_20383Collections();
                init_17_0_2_edc0357_1352328158678_378610_20383Elaborations();
            }

            public _17_0_2_edc0357_1352328158678_378610_20383(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328158678_378610_20383Members();
                init_17_0_2_edc0357_1352328158678_378610_20383Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328158678_378610_20383Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_edc0357_1352328160575_575843_21477 = null;

            public BooleanParameter _17_0_2_edc0357_1352328158675_718110_20377_exists = null;

            public IntegerParameter myDeciderID_decider_17_0_2_edc0357_1352328158675_718110_20377 = null;

            public BooleanParameter objectToPass = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328160575_575843_21477Dependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158675_718110_20377_existsDependency = null;

            public Dependency< Integer > myDeciderID_decider_17_0_2_edc0357_1352328158675_718110_20377Dependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Effect effect205 = null;

            public Parameter effect205Var = null;

            public Effect effect206 = null;

            public Parameter effect206Var = null;

            public ElaborationRule elaborationRule207 = null;

            public void init_17_0_2_edc0357_1352328158678_378610_20383Members() {
                try {
                    if (_17_0_2_edc0357_1352328160575_575843_21477 == null) _17_0_2_edc0357_1352328160575_575843_21477 = new BooleanParameter("_17_0_2_edc0357_1352328160575_575843_21477", (Boolean) true, this);
                    if (_17_0_2_edc0357_1352328158675_718110_20377_exists == null) _17_0_2_edc0357_1352328158675_718110_20377_exists = new BooleanParameter("_17_0_2_edc0357_1352328158675_718110_20377_exists", (Boolean) false, this);
                    if (myDeciderID_decider_17_0_2_edc0357_1352328158675_718110_20377 == null) myDeciderID_decider_17_0_2_edc0357_1352328158675_718110_20377 = new IntegerParameter("myDeciderID_decider_17_0_2_edc0357_1352328158675_718110_20377", (Integer) 2, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect205VarV = sig_17_0_2_edc0357_1352328158681_26576_20397;
                    effect205Var = new Parameter("effect205Var", null, null, this);
                    addDependency(effect205Var, new Expression(effect205VarV));
                    effect205 = new EffectFunction(new EffectFunction(effect205Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { _17_0_2_edc0357_1352328160575_575843_21477, endTime }));
                    Object effect206VarV = decider_17_0_2_edc0357_1352328158675_718110_20377;
                    effect206Var = new Parameter("effect206Var", null, null, this);
                    addDependency(effect206Var, new Expression(effect206VarV));
                    effect206 = new EffectFunction(new EffectFunction(effect206Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, myDeciderID_decider_17_0_2_edc0357_1352328158675_718110_20377 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158678_378610_20383Collections() {
                parameters.add(_17_0_2_edc0357_1352328160575_575843_21477);
                parameters.add(_17_0_2_edc0357_1352328158675_718110_20377_exists);
                parameters.add(myDeciderID_decider_17_0_2_edc0357_1352328158675_718110_20377);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect205Var = new TreeSet<Effect>();
                effectsForeffect205Var.add(effect205);
                addEffects((Parameter<?>) effect205Var, effectsForeffect205Var);
                Set<Effect> effectsForeffect206Var = new TreeSet<Effect>();
                effectsForeffect206Var.add(effect206);
                addEffects((Parameter<?>) effect206Var, effectsForeffect206Var);
            }

            public void init_17_0_2_edc0357_1352328158678_378610_20383Dependencies() {
                addDependency(_17_0_2_edc0357_1352328160575_575843_21477, new Expression<Boolean>(true));
                addDependency(_17_0_2_edc0357_1352328158675_718110_20377_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158675_718110_20377, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158675_718110_20377, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_edc0357_1352328158675_718110_20377, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_edc0357_1352328158675_718110_20377)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_edc0357_1352328158674_624952_20376_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_edc0357_1352328158674_624952_20376_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))))));
                addDependency(myDeciderID_decider_17_0_2_edc0357_1352328158675_718110_20377, new Expression<Integer>(2));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new EffectFunction(sig_17_0_2_edc0357_1352328158681_489777_20400, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_edc0357_1352328158678_378610_20383Elaborations() {
                init_17_0_2_edc0357_1352328158678_378610_20383Dependencies();
                Expression<?>[] arguments207 = new Expression<?>[1];
                arguments207[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition207 = new Expression<Boolean>(_17_0_2_edc0357_1352328158675_718110_20377_exists);
                elaborationRule207 = addElaborationRule(condition207, _17_0_2_edc0357_1352328156645_46192_19618.this, Customer._17_0_2_edc0357_1352328156645_46192_19618._17_0_2_edc0357_1352328158675_718110_20377.class, "CDR_decide_particip_DecisionNode_setDRCap", arguments207);
            }
        }

        public class _17_0_2_edc0357_1352328158679_514534_20384 extends DurativeEvent {

            public _17_0_2_edc0357_1352328158679_514534_20384() {
                super();
                init_17_0_2_edc0357_1352328158679_514534_20384Members();
                init_17_0_2_edc0357_1352328158679_514534_20384Collections();
                init_17_0_2_edc0357_1352328158679_514534_20384Elaborations();
            }

            public _17_0_2_edc0357_1352328158679_514534_20384(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328158679_514534_20384Members();
                init_17_0_2_edc0357_1352328158679_514534_20384Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328158679_514534_20384Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_edc0357_1352328158672_866631_20373_exists = null;

            public BooleanParameter _17_0_2_edc0357_1352328158678_378610_20383_exists = null;

            public BooleanParameter objectToPass = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158672_866631_20373_existsDependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158678_378610_20383_existsDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Effect effect208 = null;

            public Parameter effect208Var = null;

            public Effect effect209 = null;

            public Parameter effect209Var = null;

            public ElaborationRule elaborationRule210 = null;

            public ElaborationRule elaborationRule211 = null;

            public void init_17_0_2_edc0357_1352328158679_514534_20384Members() {
                try {
                    if (_17_0_2_edc0357_1352328158672_866631_20373_exists == null) _17_0_2_edc0357_1352328158672_866631_20373_exists = new BooleanParameter("_17_0_2_edc0357_1352328158672_866631_20373_exists", (Boolean) false, this);
                    if (_17_0_2_edc0357_1352328158678_378610_20383_exists == null) _17_0_2_edc0357_1352328158678_378610_20383_exists = new BooleanParameter("_17_0_2_edc0357_1352328158678_378610_20383_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect208VarV = sig_17_0_2_edc0357_1352328158681_903819_20399;
                    effect208Var = new Parameter("effect208Var", null, null, this);
                    addDependency(effect208Var, new Expression(effect208VarV));
                    effect208 = new EffectFunction(new EffectFunction(effect208Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect209VarV = sig_17_0_2_edc0357_1352328158681_489777_20400;
                    effect209Var = new Parameter("effect209Var", null, null, this);
                    addDependency(effect209Var, new Expression(effect209VarV));
                    effect209 = new EffectFunction(new EffectFunction(effect209Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158679_514534_20384Collections() {
                parameters.add(_17_0_2_edc0357_1352328158672_866631_20373_exists);
                parameters.add(_17_0_2_edc0357_1352328158678_378610_20383_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect208Var = new TreeSet<Effect>();
                effectsForeffect208Var.add(effect208);
                addEffects((Parameter<?>) effect208Var, effectsForeffect208Var);
                Set<Effect> effectsForeffect209Var = new TreeSet<Effect>();
                effectsForeffect209Var.add(effect209);
                addEffects((Parameter<?>) effect209Var, effectsForeffect209Var);
            }

            public void init_17_0_2_edc0357_1352328158679_514534_20384Dependencies() {
                addDependency(_17_0_2_edc0357_1352328158672_866631_20373_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_edc0357_1352328158674_624952_20376_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_edc0357_1352328158674_624952_20376_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(_17_0_2_edc0357_1352328158678_378610_20383_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_edc0357_1352328158674_624952_20376_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_edc0357_1352328158674_624952_20376_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new EffectFunction(sig_17_0_2_edc0357_1352328158681_221195_20398, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_edc0357_1352328158679_514534_20384Elaborations() {
                init_17_0_2_edc0357_1352328158679_514534_20384Dependencies();
                Expression<?>[] arguments210 = new Expression<?>[1];
                arguments210[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition210 = new Expression<Boolean>(_17_0_2_edc0357_1352328158672_866631_20373_exists);
                elaborationRule210 = addElaborationRule(condition210, _17_0_2_edc0357_1352328156645_46192_19618.this, Customer._17_0_2_edc0357_1352328156645_46192_19618._17_0_2_edc0357_1352328158672_866631_20373.class, "CDR_readself_ReadSelfAction_setDRCap", arguments210);
                Expression<?>[] arguments211 = new Expression<?>[1];
                arguments211[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition211 = new Expression<Boolean>(_17_0_2_edc0357_1352328158678_378610_20383_exists);
                elaborationRule211 = addElaborationRule(condition211, _17_0_2_edc0357_1352328156645_46192_19618.this, Customer._17_0_2_edc0357_1352328156645_46192_19618._17_0_2_edc0357_1352328158678_378610_20383.class, "CDR_participation_spec_ValueSpecificationAction_setDRCap", arguments211);
            }
        }

        public class _17_0_2_edc0357_1352328158679_407855_20385 extends DurativeEvent {

            public _17_0_2_edc0357_1352328158679_407855_20385() {
                super();
                init_17_0_2_edc0357_1352328158679_407855_20385Members();
                init_17_0_2_edc0357_1352328158679_407855_20385Collections();
                init_17_0_2_edc0357_1352328158679_407855_20385Elaborations();
            }

            public _17_0_2_edc0357_1352328158679_407855_20385(Expression<Integer> startTime, Expression<ObjectFlow<Boolean>> receiveThis) {
                super();
                init_17_0_2_edc0357_1352328158679_407855_20385Members();
                init_17_0_2_edc0357_1352328158679_407855_20385Collections();
                addDependency(this.startTime, startTime);
                addDependency(this.receiveThis, receiveThis);
                init_17_0_2_edc0357_1352328158679_407855_20385Elaborations();
                fixTimeDependencies();
            }

            public Parameter< ObjectFlow<Boolean> > receiveThis = null;

            public BooleanParameter objectToPass = null;

            public Dependency< Integer > finalNode_startTimeDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Effect effect212 = null;

            public Parameter effect212Var = null;

            public void init_17_0_2_edc0357_1352328158679_407855_20385Members() {
                try {
                    if (receiveThis == null) receiveThis = new Parameter<ObjectFlow<Boolean>>("receiveThis", null, (ObjectFlow<Boolean>) null, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect212VarV = sig_17_0_2_edc0357_1352328158681_50786_20403;
                    effect212Var = new Parameter("effect212Var", null, null, this);
                    addDependency(effect212Var, new Expression(effect212VarV));
                    effect212 = new EffectFunction(new EffectFunction(effect212Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158679_407855_20385Collections() {
                parameters.add(receiveThis);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect212Var = new TreeSet<Effect>();
                effectsForeffect212Var.add(effect212);
                addEffects((Parameter<?>) effect212Var, effectsForeffect212Var);
            }

            public void init_17_0_2_edc0357_1352328158679_407855_20385Dependencies() {
                addDependency(finalNode_startTime, new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new EffectFunction(receiveThis, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_edc0357_1352328158679_407855_20385Elaborations() {
                init_17_0_2_edc0357_1352328158679_407855_20385Dependencies();
            }
        }

        public _17_0_2_edc0357_1352328156645_46192_19618(Expression<Integer> startTime, Expression<DurativeEvent> caller, Expression<Integer> _17_0_2_edc0357_1352328158535_616746_20341) {
            super();
            init_17_0_2_edc0357_1352328156645_46192_19618Members();
            init_17_0_2_edc0357_1352328156645_46192_19618Collections();
            addDependency(this.startTime, startTime);
            addDependency(this.caller, caller);
            addDependency(this._17_0_2_edc0357_1352328158535_616746_20341, _17_0_2_edc0357_1352328158535_616746_20341);
            init_17_0_2_edc0357_1352328156645_46192_19618Elaborations();
            fixTimeDependencies();
        }

        public Parameter< ObjectFlow<Customer> > sig_17_0_2_edc0357_1352328158680_29152_20391 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_2_edc0357_1352328158680_232103_20390 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_edc0357_1352328158681_221195_20398 = null;

        public Parameter< DurativeEvent > caller = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_edc0357_1352328158681_50786_20403 = null;

        public IntegerParameter _17_0_2_edc0357_1352328158535_616746_20341 = null;

        public Parameter< ObjectFlow<Customer> > sig_17_0_2_edc0357_1352328158680_802723_20392 = null;

        public Parameter< ObjectFlow<Customer> > sig_17_0_2_edc0357_1352328158680_928200_20394 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_edc0357_1352328158679_596112_20386 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_2_edc0357_1352328158681_29423_20396 = null;

        public Parameter< TimeVaryingList<Integer> > decider_17_0_2_edc0357_1352328158677_26057_20380 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_edc0357_1352328158681_384297_20401 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_edc0357_1352328158681_903819_20399 = null;

        public IntegerParameter _17_0_2_edc0357_1352328158678_297502_20382_startTime = null;

        public BooleanParameter _17_0_2_edc0357_1352328158674_624952_20376_exists = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_edc0357_1352328158680_379016_20389 = null;

        public IntegerParameter finalNode_endTime = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_edc0357_1352328158679_76145_20387 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_2_edc0357_1352328158680_450048_20388 = null;

        public Parameter< TimeVaryingList<Integer> > decider_17_0_2_edc0357_1352328158675_13172_20378 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_edc0357_1352328158680_247894_20395 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_edc0357_1352328158681_489777_20400 = null;

        public IntegerParameter finalNode_startTime = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_edc0357_1352328158681_60367_20402 = null;

        public Parameter< TimeVaryingList<Integer> > decider_17_0_2_edc0357_1352328158675_718110_20377 = null;

        public Parameter< TimeVaryingList<Integer> > decider_17_0_2_edc0357_1352328158673_69485_20374 = null;

        public Parameter< ObjectFlow<Customer> > sig_17_0_2_edc0357_1352328158680_930496_20393 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_edc0357_1352328158681_26576_20397 = null;

        public Dependency< Integer > caller_endTimeDependency = null;

        public Dependency< Integer > endTimeDependency = null;

        public Dependency< Boolean > _17_0_2_edc0357_1352328158674_624952_20376_existsDependency = null;

        public ElaborationRule elaborationRule160 = null;

        public ElaborationRule elaborationRule161 = null;

        public ElaborationRule elaborationRule162 = null;

        public void init_17_0_2_edc0357_1352328156645_46192_19618Members() {
            try {
                if (sig_17_0_2_edc0357_1352328158680_29152_20391 == null) sig_17_0_2_edc0357_1352328158680_29152_20391 = new Parameter<ObjectFlow<Customer>>("sig_17_0_2_edc0357_1352328158680_29152_20391", null, (ObjectFlow<Customer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158680_29152_20391" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328158680_232103_20390 == null) sig_17_0_2_edc0357_1352328158680_232103_20390 = new Parameter<ObjectFlow<Integer>>("sig_17_0_2_edc0357_1352328158680_232103_20390", null, (ObjectFlow<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158680_232103_20390" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328158681_221195_20398 == null) sig_17_0_2_edc0357_1352328158681_221195_20398 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328158681_221195_20398", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158681_221195_20398" })).evaluate(true), this);
                if (caller == null) caller = new Parameter<DurativeEvent>("caller", null, (DurativeEvent) null, this);
                if (sig_17_0_2_edc0357_1352328158681_50786_20403 == null) sig_17_0_2_edc0357_1352328158681_50786_20403 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328158681_50786_20403", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158681_50786_20403" })).evaluate(true), this);
                if (_17_0_2_edc0357_1352328158535_616746_20341 == null) _17_0_2_edc0357_1352328158535_616746_20341 = new IntegerParameter("_17_0_2_edc0357_1352328158535_616746_20341", (Integer) null, this);
                if (sig_17_0_2_edc0357_1352328158680_802723_20392 == null) sig_17_0_2_edc0357_1352328158680_802723_20392 = new Parameter<ObjectFlow<Customer>>("sig_17_0_2_edc0357_1352328158680_802723_20392", null, (ObjectFlow<Customer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158680_802723_20392" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328158680_928200_20394 == null) sig_17_0_2_edc0357_1352328158680_928200_20394 = new Parameter<ObjectFlow<Customer>>("sig_17_0_2_edc0357_1352328158680_928200_20394", null, (ObjectFlow<Customer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158680_928200_20394" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328158679_596112_20386 == null) sig_17_0_2_edc0357_1352328158679_596112_20386 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328158679_596112_20386", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158679_596112_20386" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328158681_29423_20396 == null) sig_17_0_2_edc0357_1352328158681_29423_20396 = new Parameter<ObjectFlow<Integer>>("sig_17_0_2_edc0357_1352328158681_29423_20396", null, (ObjectFlow<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158681_29423_20396" })).evaluate(true), this);
                if (decider_17_0_2_edc0357_1352328158677_26057_20380 == null) decider_17_0_2_edc0357_1352328158677_26057_20380 = new Parameter<TimeVaryingList<Integer>>("decider_17_0_2_edc0357_1352328158677_26057_20380", null, (TimeVaryingList<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingList.class, java.lang.String.class, int.class), new Object[] { "decider_17_0_2_edc0357_1352328158677_26057_20380", 3 })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328158681_384297_20401 == null) sig_17_0_2_edc0357_1352328158681_384297_20401 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328158681_384297_20401", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158681_384297_20401" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328158681_903819_20399 == null) sig_17_0_2_edc0357_1352328158681_903819_20399 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328158681_903819_20399", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158681_903819_20399" })).evaluate(true), this);
                if (_17_0_2_edc0357_1352328158678_297502_20382_startTime == null) _17_0_2_edc0357_1352328158678_297502_20382_startTime = new IntegerParameter("_17_0_2_edc0357_1352328158678_297502_20382_startTime", (Integer) null, this);
                if (_17_0_2_edc0357_1352328158674_624952_20376_exists == null) _17_0_2_edc0357_1352328158674_624952_20376_exists = new BooleanParameter("_17_0_2_edc0357_1352328158674_624952_20376_exists", (Boolean) false, this);
                if (sig_17_0_2_edc0357_1352328158680_379016_20389 == null) sig_17_0_2_edc0357_1352328158680_379016_20389 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328158680_379016_20389", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158680_379016_20389" })).evaluate(true), this);
                if (finalNode_endTime == null) finalNode_endTime = new IntegerParameter("finalNode_endTime", (Integer) null, this);
                if (sig_17_0_2_edc0357_1352328158679_76145_20387 == null) sig_17_0_2_edc0357_1352328158679_76145_20387 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328158679_76145_20387", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158679_76145_20387" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328158680_450048_20388 == null) sig_17_0_2_edc0357_1352328158680_450048_20388 = new Parameter<ObjectFlow<Integer>>("sig_17_0_2_edc0357_1352328158680_450048_20388", null, (ObjectFlow<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158680_450048_20388" })).evaluate(true), this);
                if (decider_17_0_2_edc0357_1352328158675_13172_20378 == null) decider_17_0_2_edc0357_1352328158675_13172_20378 = new Parameter<TimeVaryingList<Integer>>("decider_17_0_2_edc0357_1352328158675_13172_20378", null, (TimeVaryingList<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingList.class, java.lang.String.class, int.class), new Object[] { "decider_17_0_2_edc0357_1352328158675_13172_20378", 2 })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328158680_247894_20395 == null) sig_17_0_2_edc0357_1352328158680_247894_20395 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328158680_247894_20395", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158680_247894_20395" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328158681_489777_20400 == null) sig_17_0_2_edc0357_1352328158681_489777_20400 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328158681_489777_20400", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158681_489777_20400" })).evaluate(true), this);
                if (finalNode_startTime == null) finalNode_startTime = new IntegerParameter("finalNode_startTime", (Integer) null, this);
                if (sig_17_0_2_edc0357_1352328158681_60367_20402 == null) sig_17_0_2_edc0357_1352328158681_60367_20402 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328158681_60367_20402", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158681_60367_20402" })).evaluate(true), this);
                if (decider_17_0_2_edc0357_1352328158675_718110_20377 == null) decider_17_0_2_edc0357_1352328158675_718110_20377 = new Parameter<TimeVaryingList<Integer>>("decider_17_0_2_edc0357_1352328158675_718110_20377", null, (TimeVaryingList<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingList.class, java.lang.String.class, int.class), new Object[] { "decider_17_0_2_edc0357_1352328158675_718110_20377", 2 })).evaluate(true), this);
                if (decider_17_0_2_edc0357_1352328158673_69485_20374 == null) decider_17_0_2_edc0357_1352328158673_69485_20374 = new Parameter<TimeVaryingList<Integer>>("decider_17_0_2_edc0357_1352328158673_69485_20374", null, (TimeVaryingList<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingList.class, java.lang.String.class, int.class), new Object[] { "decider_17_0_2_edc0357_1352328158673_69485_20374", 3 })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328158680_930496_20393 == null) sig_17_0_2_edc0357_1352328158680_930496_20393 = new Parameter<ObjectFlow<Customer>>("sig_17_0_2_edc0357_1352328158680_930496_20393", null, (ObjectFlow<Customer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158680_930496_20393" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328158681_26576_20397 == null) sig_17_0_2_edc0357_1352328158681_26576_20397 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328158681_26576_20397", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158681_26576_20397" })).evaluate(true), this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void init_17_0_2_edc0357_1352328156645_46192_19618Collections() {
            parameters.add(sig_17_0_2_edc0357_1352328158680_29152_20391);
            parameters.add(sig_17_0_2_edc0357_1352328158680_232103_20390);
            parameters.add(sig_17_0_2_edc0357_1352328158681_221195_20398);
            parameters.add(caller);
            parameters.add(sig_17_0_2_edc0357_1352328158681_50786_20403);
            parameters.add(_17_0_2_edc0357_1352328158535_616746_20341);
            parameters.add(sig_17_0_2_edc0357_1352328158680_802723_20392);
            parameters.add(sig_17_0_2_edc0357_1352328158680_928200_20394);
            parameters.add(sig_17_0_2_edc0357_1352328158679_596112_20386);
            parameters.add(sig_17_0_2_edc0357_1352328158681_29423_20396);
            parameters.add(decider_17_0_2_edc0357_1352328158677_26057_20380);
            parameters.add(sig_17_0_2_edc0357_1352328158681_384297_20401);
            parameters.add(sig_17_0_2_edc0357_1352328158681_903819_20399);
            parameters.add(_17_0_2_edc0357_1352328158678_297502_20382_startTime);
            parameters.add(_17_0_2_edc0357_1352328158674_624952_20376_exists);
            parameters.add(sig_17_0_2_edc0357_1352328158680_379016_20389);
            parameters.add(finalNode_endTime);
            parameters.add(sig_17_0_2_edc0357_1352328158679_76145_20387);
            parameters.add(sig_17_0_2_edc0357_1352328158680_450048_20388);
            parameters.add(decider_17_0_2_edc0357_1352328158675_13172_20378);
            parameters.add(sig_17_0_2_edc0357_1352328158680_247894_20395);
            parameters.add(sig_17_0_2_edc0357_1352328158681_489777_20400);
            parameters.add(finalNode_startTime);
            parameters.add(sig_17_0_2_edc0357_1352328158681_60367_20402);
            parameters.add(decider_17_0_2_edc0357_1352328158675_718110_20377);
            parameters.add(decider_17_0_2_edc0357_1352328158673_69485_20374);
            parameters.add(sig_17_0_2_edc0357_1352328158680_930496_20393);
            parameters.add(sig_17_0_2_edc0357_1352328158681_26576_20397);
        }

        public void init_17_0_2_edc0357_1352328156645_46192_19618Dependencies() {
            caller.getValue(true).addDependency(caller.getValue(true).endTime, new Expression<Integer>(finalNode_endTime));
            addDependency(endTime, new Expression<Integer>(finalNode_endTime));
            addDependency(_17_0_2_edc0357_1352328158674_624952_20376_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.NotEquals(new Expression<Integer>(finalNode_startTime), null)), new Expression(new EffectFunction(sig_17_0_2_edc0357_1352328158681_50786_20403, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(new Functions.Minus(new Expression<Integer>(finalNode_startTime), new Expression<Integer>(1))) })))));
        }

        public void init_17_0_2_edc0357_1352328156645_46192_19618Elaborations() {
            init_17_0_2_edc0357_1352328156645_46192_19618Dependencies();
            Expression<?>[] arguments160 = new Expression<?>[1];
            arguments160[0] = new Expression<Integer>(startTime);
            Expression<Boolean> condition160 = new Expression<Boolean>(true);
            elaborationRule160 = addElaborationRule(condition160, _17_0_2_edc0357_1352328156645_46192_19618.this, Customer._17_0_2_edc0357_1352328156645_46192_19618._17_0_2_edc0357_1352328158674_234414_20375.class, "CDR_start_InitialNode_setDRCap", arguments160);
            Expression<?>[] arguments161 = new Expression<?>[2];
            arguments161[0] = new Expression<Integer>(startTime);
            arguments161[1] = new Expression<Integer>(_17_0_2_edc0357_1352328158535_616746_20341);
            Expression<Boolean> condition161 = new Expression<Boolean>(true);
            elaborationRule161 = addElaborationRule(condition161, _17_0_2_edc0357_1352328156645_46192_19618.this, Customer._17_0_2_edc0357_1352328156645_46192_19618._17_0_2_edc0357_1352328158678_297502_20382.class, "CDR_capval_ActivityParameterNode_setDRCap", arguments161);
            Expression<?>[] arguments162 = new Expression<?>[1];
            arguments162[0] = new Expression<Integer>(finalNode_startTime);
            Expression<Boolean> condition162 = new Expression<Boolean>(_17_0_2_edc0357_1352328158674_624952_20376_exists);
            elaborationRule162 = addElaborationRule(condition162, _17_0_2_edc0357_1352328156645_46192_19618.this, Customer._17_0_2_edc0357_1352328156645_46192_19618._17_0_2_edc0357_1352328158674_624952_20376.class, "CDR_final_ActivityFinalNode_setDRCap", arguments162);
        }
    }

    public class _17_0_2_edc0357_1352328156648_548830_19619 extends DurativeEvent {

        public _17_0_2_edc0357_1352328156648_548830_19619() {
            super();
            init_17_0_2_edc0357_1352328156648_548830_19619Members();
            init_17_0_2_edc0357_1352328156648_548830_19619Collections();
            init_17_0_2_edc0357_1352328156648_548830_19619Elaborations();
        }

        public class _17_0_2_edc0357_1352328158789_563871_20435 extends DurativeEvent {

            public _17_0_2_edc0357_1352328158789_563871_20435() {
                super();
                init_17_0_2_edc0357_1352328158789_563871_20435Members();
                init_17_0_2_edc0357_1352328158789_563871_20435Collections();
                init_17_0_2_edc0357_1352328158789_563871_20435Elaborations();
            }

            public _17_0_2_edc0357_1352328158789_563871_20435(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328158789_563871_20435Members();
                init_17_0_2_edc0357_1352328158789_563871_20435Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328158789_563871_20435Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_edc0357_1352328158790_99012_20436_exists = null;

            public BooleanParameter objectToPass = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158790_99012_20436_existsDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Effect effect215 = null;

            public Parameter effect215Var = null;

            public ElaborationRule elaborationRule216 = null;

            public void init_17_0_2_edc0357_1352328158789_563871_20435Members() {
                try {
                    if (_17_0_2_edc0357_1352328158790_99012_20436_exists == null) _17_0_2_edc0357_1352328158790_99012_20436_exists = new BooleanParameter("_17_0_2_edc0357_1352328158790_99012_20436_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect215VarV = sig_17_0_2_edc0357_1352328158795_231230_20458;
                    effect215Var = new Parameter("effect215Var", null, null, this);
                    addDependency(effect215Var, new Expression(effect215VarV));
                    effect215 = new EffectFunction(new EffectFunction(effect215Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158789_563871_20435Collections() {
                parameters.add(_17_0_2_edc0357_1352328158790_99012_20436_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect215Var = new TreeSet<Effect>();
                effectsForeffect215Var.add(effect215);
                addEffects((Parameter<?>) effect215Var, effectsForeffect215Var);
            }

            public void init_17_0_2_edc0357_1352328158789_563871_20435Dependencies() {
                addDependency(_17_0_2_edc0357_1352328158790_99012_20436_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_edc0357_1352328158792_106015_20443_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_edc0357_1352328158792_106015_20443_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(true));
            }

            public void init_17_0_2_edc0357_1352328158789_563871_20435Elaborations() {
                init_17_0_2_edc0357_1352328158789_563871_20435Dependencies();
                Expression<?>[] arguments216 = new Expression<?>[1];
                arguments216[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition216 = new Expression<Boolean>(_17_0_2_edc0357_1352328158790_99012_20436_exists);
                elaborationRule216 = addElaborationRule(condition216, _17_0_2_edc0357_1352328156648_548830_19619.this, Customer._17_0_2_edc0357_1352328156648_548830_19619._17_0_2_edc0357_1352328158790_99012_20436.class, "C_startup_activities_CallBehaviorAction_CustomerCB", arguments216);
            }
        }

        public class _17_0_2_edc0357_1352328158790_99012_20436 extends DurativeEvent {

            public _17_0_2_edc0357_1352328158790_99012_20436() {
                super();
                init_17_0_2_edc0357_1352328158790_99012_20436Members();
                init_17_0_2_edc0357_1352328158790_99012_20436Collections();
                init_17_0_2_edc0357_1352328158790_99012_20436Elaborations();
            }

            public _17_0_2_edc0357_1352328158790_99012_20436(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328158790_99012_20436Members();
                init_17_0_2_edc0357_1352328158790_99012_20436Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328158790_99012_20436Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_edc0357_1352328158793_123216_20448_exists = null;

            public BooleanParameter objectToPass = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158793_123216_20448_existsDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Effect effect217 = null;

            public Parameter effect217Var = null;

            public ElaborationRule elaborationRule218 = null;

            public ElaborationRule elaborationRule219 = null;

            public void init_17_0_2_edc0357_1352328158790_99012_20436Members() {
                try {
                    if (_17_0_2_edc0357_1352328158793_123216_20448_exists == null) _17_0_2_edc0357_1352328158793_123216_20448_exists = new BooleanParameter("_17_0_2_edc0357_1352328158793_123216_20448_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect217VarV = sig_17_0_2_edc0357_1352328158796_758419_20470;
                    effect217Var = new Parameter("effect217Var", null, null, this);
                    addDependency(effect217Var, new Expression(effect217VarV));
                    effect217 = new EffectFunction(new EffectFunction(effect217Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158790_99012_20436Collections() {
                parameters.add(_17_0_2_edc0357_1352328158793_123216_20448_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect217Var = new TreeSet<Effect>();
                effectsForeffect217Var.add(effect217);
                addEffects((Parameter<?>) effect217Var, effectsForeffect217Var);
            }

            public void init_17_0_2_edc0357_1352328158790_99012_20436Dependencies() {
                addDependency(_17_0_2_edc0357_1352328158793_123216_20448_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_edc0357_1352328158792_106015_20443_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_edc0357_1352328158792_106015_20443_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(objectToPass, new Expression<Boolean>(new EffectFunction(sig_17_0_2_edc0357_1352328158795_231230_20458, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_edc0357_1352328158790_99012_20436Elaborations() {
                init_17_0_2_edc0357_1352328158790_99012_20436Dependencies();
                Expression<?>[] arguments218 = new Expression<?>[1];
                arguments218[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition218 = new Expression<Boolean>(_17_0_2_edc0357_1352328158793_123216_20448_exists);
                elaborationRule218 = addElaborationRule(condition218, _17_0_2_edc0357_1352328156648_548830_19619.this, Customer._17_0_2_edc0357_1352328156648_548830_19619._17_0_2_edc0357_1352328158793_123216_20448.class, "C_wait_AcceptEventAction_CustomerCB", arguments218);
                Expression<?>[] arguments219 = new Expression<?>[2];
                arguments219[0] = new Expression<Integer>(startTime);
                arguments219[1] = new Expression<DurativeEvent>(this);
                Expression<Boolean> condition219 = new Expression<Boolean>(true);
                elaborationRule219 = addElaborationRule(condition219, Customer.this, Customer._17_0_2_edc0357_1352328156644_264215_19617.class, "initialize_Activity_Customer", arguments219);
            }
        }

        public class _17_0_2_edc0357_1352328158790_493227_20437 extends DurativeEvent {

            public _17_0_2_edc0357_1352328158790_493227_20437() {
                super();
                init_17_0_2_edc0357_1352328158790_493227_20437Members();
                init_17_0_2_edc0357_1352328158790_493227_20437Collections();
                init_17_0_2_edc0357_1352328158790_493227_20437Elaborations();
            }

            public _17_0_2_edc0357_1352328158790_493227_20437(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328158790_493227_20437Members();
                init_17_0_2_edc0357_1352328158790_493227_20437Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328158790_493227_20437Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_edc0357_1352328158793_331994_20446_exists = null;

            public BooleanParameter _17_0_2_edc0357_1352328158792_819832_20444_exists = null;

            public BooleanParameter _17_0_2_edc0357_1352328158793_68185_20449_exists = null;

            public BooleanParameter objectToPass = null;

            public BooleanParameter _17_0_2_edc0357_1352328158793_123095_20447_exists = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158793_331994_20446_existsDependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158792_819832_20444_existsDependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158793_68185_20449_existsDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158793_123095_20447_existsDependency = null;

            public Effect effect220 = null;

            public Parameter effect220Var = null;

            public Effect effect221 = null;

            public Parameter effect221Var = null;

            public Effect effect222 = null;

            public Parameter effect222Var = null;

            public Effect effect223 = null;

            public Parameter effect223Var = null;

            public ElaborationRule elaborationRule224 = null;

            public ElaborationRule elaborationRule225 = null;

            public ElaborationRule elaborationRule226 = null;

            public ElaborationRule elaborationRule227 = null;

            public void init_17_0_2_edc0357_1352328158790_493227_20437Members() {
                try {
                    if (_17_0_2_edc0357_1352328158793_331994_20446_exists == null) _17_0_2_edc0357_1352328158793_331994_20446_exists = new BooleanParameter("_17_0_2_edc0357_1352328158793_331994_20446_exists", (Boolean) false, this);
                    if (_17_0_2_edc0357_1352328158792_819832_20444_exists == null) _17_0_2_edc0357_1352328158792_819832_20444_exists = new BooleanParameter("_17_0_2_edc0357_1352328158792_819832_20444_exists", (Boolean) false, this);
                    if (_17_0_2_edc0357_1352328158793_68185_20449_exists == null) _17_0_2_edc0357_1352328158793_68185_20449_exists = new BooleanParameter("_17_0_2_edc0357_1352328158793_68185_20449_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (_17_0_2_edc0357_1352328158793_123095_20447_exists == null) _17_0_2_edc0357_1352328158793_123095_20447_exists = new BooleanParameter("_17_0_2_edc0357_1352328158793_123095_20447_exists", (Boolean) false, this);
                    Object effect220VarV = sig_17_0_2_edc0357_1352328158795_900235_20462;
                    effect220Var = new Parameter("effect220Var", null, null, this);
                    addDependency(effect220Var, new Expression(effect220VarV));
                    effect220 = new EffectFunction(new EffectFunction(effect220Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect221VarV = sig_17_0_2_edc0357_1352328158795_852079_20464;
                    effect221Var = new Parameter("effect221Var", null, null, this);
                    addDependency(effect221Var, new Expression(effect221VarV));
                    effect221 = new EffectFunction(new EffectFunction(effect221Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect222VarV = sig_17_0_2_edc0357_1352328158796_279864_20469;
                    effect222Var = new Parameter("effect222Var", null, null, this);
                    addDependency(effect222Var, new Expression(effect222VarV));
                    effect222 = new EffectFunction(new EffectFunction(effect222Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect223VarV = sig_17_0_2_edc0357_1352328158796_884417_20472;
                    effect223Var = new Parameter("effect223Var", null, null, this);
                    addDependency(effect223Var, new Expression(effect223VarV));
                    effect223 = new EffectFunction(new EffectFunction(effect223Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158790_493227_20437Collections() {
                parameters.add(_17_0_2_edc0357_1352328158793_331994_20446_exists);
                parameters.add(_17_0_2_edc0357_1352328158792_819832_20444_exists);
                parameters.add(_17_0_2_edc0357_1352328158793_68185_20449_exists);
                parameters.add(objectToPass);
                parameters.add(_17_0_2_edc0357_1352328158793_123095_20447_exists);
                Set<Effect> effectsForeffect220Var = new TreeSet<Effect>();
                effectsForeffect220Var.add(effect220);
                addEffects((Parameter<?>) effect220Var, effectsForeffect220Var);
                Set<Effect> effectsForeffect221Var = new TreeSet<Effect>();
                effectsForeffect221Var.add(effect221);
                addEffects((Parameter<?>) effect221Var, effectsForeffect221Var);
                Set<Effect> effectsForeffect222Var = new TreeSet<Effect>();
                effectsForeffect222Var.add(effect222);
                addEffects((Parameter<?>) effect222Var, effectsForeffect222Var);
                Set<Effect> effectsForeffect223Var = new TreeSet<Effect>();
                effectsForeffect223Var.add(effect223);
                addEffects((Parameter<?>) effect223Var, effectsForeffect223Var);
            }

            public void init_17_0_2_edc0357_1352328158790_493227_20437Dependencies() {
                addDependency(_17_0_2_edc0357_1352328158793_331994_20446_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_edc0357_1352328158792_106015_20443_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_edc0357_1352328158792_106015_20443_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(_17_0_2_edc0357_1352328158792_819832_20444_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_edc0357_1352328158792_106015_20443_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_edc0357_1352328158792_106015_20443_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(_17_0_2_edc0357_1352328158793_68185_20449_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_edc0357_1352328158792_106015_20443_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_edc0357_1352328158792_106015_20443_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new EffectFunction(sig_17_0_2_edc0357_1352328158796_290441_20471, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_edc0357_1352328158793_123095_20447_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_edc0357_1352328158792_106015_20443_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_edc0357_1352328158792_106015_20443_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
            }

            public void init_17_0_2_edc0357_1352328158790_493227_20437Elaborations() {
                init_17_0_2_edc0357_1352328158790_493227_20437Dependencies();
                Expression<?>[] arguments224 = new Expression<?>[2];
                arguments224[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                arguments224[1] = new Expression<ObjectFlow<Boolean>>(sig_17_0_2_edc0357_1352328158796_279864_20469);
                Expression<Boolean> condition224 = new Expression<Boolean>(_17_0_2_edc0357_1352328158793_123095_20447_exists);
                elaborationRule224 = addElaborationRule(condition224, _17_0_2_edc0357_1352328156648_548830_19619.this, Customer._17_0_2_edc0357_1352328156648_548830_19619._17_0_2_edc0357_1352328158793_123095_20447.class, "C_dr_m_MergeNode_CustomerCB", arguments224);
                Expression<?>[] arguments225 = new Expression<?>[2];
                arguments225[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                arguments225[1] = new Expression<ObjectFlow<Boolean>>(sig_17_0_2_edc0357_1352328158795_852079_20464);
                Expression<Boolean> condition225 = new Expression<Boolean>(_17_0_2_edc0357_1352328158793_331994_20446_exists);
                elaborationRule225 = addElaborationRule(condition225, _17_0_2_edc0357_1352328156648_548830_19619.this, Customer._17_0_2_edc0357_1352328156648_548830_19619._17_0_2_edc0357_1352328158793_331994_20446.class, "C_change_m_MergeNode_CustomerCB", arguments225);
                Expression<?>[] arguments226 = new Expression<?>[1];
                arguments226[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition226 = new Expression<Boolean>(_17_0_2_edc0357_1352328158793_68185_20449_exists);
                elaborationRule226 = addElaborationRule(condition226, _17_0_2_edc0357_1352328156648_548830_19619.this, Customer._17_0_2_edc0357_1352328156648_548830_19619._17_0_2_edc0357_1352328158793_68185_20449.class, "C_activity_timer_AcceptEventAction_CustomerCB", arguments226);
                Expression<?>[] arguments227 = new Expression<?>[2];
                arguments227[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                arguments227[1] = new Expression<ObjectFlow<Boolean>>(sig_17_0_2_edc0357_1352328158795_900235_20462);
                Expression<Boolean> condition227 = new Expression<Boolean>(_17_0_2_edc0357_1352328158792_819832_20444_exists);
                elaborationRule227 = addElaborationRule(condition227, _17_0_2_edc0357_1352328156648_548830_19619.this, Customer._17_0_2_edc0357_1352328156648_548830_19619._17_0_2_edc0357_1352328158792_819832_20444.class, "C_use_m_MergeNode_CustomerCB", arguments227);
            }
        }

        public class _17_0_2_edc0357_1352328158790_658031_20438 extends DurativeEvent {

            public _17_0_2_edc0357_1352328158790_658031_20438() {
                super();
                init_17_0_2_edc0357_1352328158790_658031_20438Members();
                init_17_0_2_edc0357_1352328158790_658031_20438Collections();
                init_17_0_2_edc0357_1352328158790_658031_20438Elaborations();
            }

            public _17_0_2_edc0357_1352328158790_658031_20438(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328158790_658031_20438Members();
                init_17_0_2_edc0357_1352328158790_658031_20438Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328158790_658031_20438Elaborations();
                fixTimeDependencies();
            }

            public Parameter< Power_System.Signaldr_request > _17_0_2_edc0357_1352328160582_109561_21500 = null;

            public BooleanParameter objectToPass = null;

            public BooleanParameter _17_0_2_edc0357_1352328158792_72920_20445_exists = null;

            public Dependency< Power_System.Signaldr_request > _17_0_2_edc0357_1352328160582_109561_21500Dependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158792_72920_20445_existsDependency = null;

            public Effect effect228 = null;

            public Parameter effect228Var = null;

            public ElaborationRule elaborationRule229 = null;

            public void init_17_0_2_edc0357_1352328158790_658031_20438Members() {
                try {
                    if (_17_0_2_edc0357_1352328160582_109561_21500 == null) _17_0_2_edc0357_1352328160582_109561_21500 = new Parameter<Power_System.Signaldr_request>("_17_0_2_edc0357_1352328160582_109561_21500", null, (Power_System.Signaldr_request) null, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (_17_0_2_edc0357_1352328158792_72920_20445_exists == null) _17_0_2_edc0357_1352328158792_72920_20445_exists = new BooleanParameter("_17_0_2_edc0357_1352328158792_72920_20445_exists", (Boolean) false, this);
                    Object effect228VarV = sig_17_0_2_edc0357_1352328158795_859697_20465;
                    effect228Var = new Parameter("effect228Var", null, null, this);
                    addDependency(effect228Var, new Expression(effect228VarV));
                    effect228 = new EffectFunction(new EffectFunction(effect228Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Signaldr_request>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { _17_0_2_edc0357_1352328160582_109561_21500, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158790_658031_20438Collections() {
                parameters.add(_17_0_2_edc0357_1352328160582_109561_21500);
                parameters.add(objectToPass);
                parameters.add(_17_0_2_edc0357_1352328158792_72920_20445_exists);
                Set<Effect> effectsForeffect228Var = new TreeSet<Effect>();
                effectsForeffect228Var.add(effect228);
                addEffects((Parameter<?>) effect228Var, effectsForeffect228Var);
            }

            public void init_17_0_2_edc0357_1352328158790_658031_20438Dependencies() {
                addDependency(_17_0_2_edc0357_1352328160582_109561_21500, new Expression<Power_System.Signaldr_request>(new EffectFunction(q_Customer_dr_request, ClassUtils.getMethodForArgTypes("ObjectFlow<Signaldr_request>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new EffectFunction(sig_17_0_2_edc0357_1352328158796_917263_20467, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_edc0357_1352328158792_72920_20445_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_edc0357_1352328158792_106015_20443_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_edc0357_1352328158792_106015_20443_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
            }

            public void init_17_0_2_edc0357_1352328158790_658031_20438Elaborations() {
                init_17_0_2_edc0357_1352328158790_658031_20438Dependencies();
                Expression<?>[] arguments229 = new Expression<?>[1];
                arguments229[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition229 = new Expression<Boolean>(_17_0_2_edc0357_1352328158792_72920_20445_exists);
                elaborationRule229 = addElaborationRule(condition229, _17_0_2_edc0357_1352328156648_548830_19619.this, Customer._17_0_2_edc0357_1352328156648_548830_19619._17_0_2_edc0357_1352328158792_72920_20445.class, "C_readCapLevel_ReadStructuralFeatureAction_CustomerCB", arguments229);
            }
        }

        public class _17_0_2_edc0357_1352328158791_311867_20439 extends DurativeEvent {

            public _17_0_2_edc0357_1352328158791_311867_20439() {
                super();
                init_17_0_2_edc0357_1352328158791_311867_20439Members();
                init_17_0_2_edc0357_1352328158791_311867_20439Collections();
                init_17_0_2_edc0357_1352328158791_311867_20439Elaborations();
            }

            public _17_0_2_edc0357_1352328158791_311867_20439(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328158791_311867_20439Members();
                init_17_0_2_edc0357_1352328158791_311867_20439Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328158791_311867_20439Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_edc0357_1352328158792_819832_20444_exists = null;

            public BooleanParameter objectToPass = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158792_819832_20444_existsDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Effect effect230 = null;

            public Parameter effect230Var = null;

            public ElaborationRule elaborationRule231 = null;

            public void init_17_0_2_edc0357_1352328158791_311867_20439Members() {
                try {
                    if (_17_0_2_edc0357_1352328158792_819832_20444_exists == null) _17_0_2_edc0357_1352328158792_819832_20444_exists = new BooleanParameter("_17_0_2_edc0357_1352328158792_819832_20444_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect230VarV = sig_17_0_2_edc0357_1352328158795_332074_20459;
                    effect230Var = new Parameter("effect230Var", null, null, this);
                    addDependency(effect230Var, new Expression(effect230VarV));
                    effect230 = new EffectFunction(new EffectFunction(effect230Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158791_311867_20439Collections() {
                parameters.add(_17_0_2_edc0357_1352328158792_819832_20444_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect230Var = new TreeSet<Effect>();
                effectsForeffect230Var.add(effect230);
                addEffects((Parameter<?>) effect230Var, effectsForeffect230Var);
            }

            public void init_17_0_2_edc0357_1352328158791_311867_20439Dependencies() {
                addDependency(_17_0_2_edc0357_1352328158792_819832_20444_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_edc0357_1352328158792_106015_20443_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_edc0357_1352328158792_106015_20443_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(30));
                addDependency(objectToPass, new Expression<Boolean>(new EffectFunction(sig_17_0_2_edc0357_1352328158795_593515_20461, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_edc0357_1352328158791_311867_20439Elaborations() {
                init_17_0_2_edc0357_1352328158791_311867_20439Dependencies();
                Expression<?>[] arguments231 = new Expression<?>[2];
                arguments231[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                arguments231[1] = new Expression<ObjectFlow<Boolean>>(sig_17_0_2_edc0357_1352328158795_332074_20459);
                Expression<Boolean> condition231 = new Expression<Boolean>(_17_0_2_edc0357_1352328158792_819832_20444_exists);
                elaborationRule231 = addElaborationRule(condition231, _17_0_2_edc0357_1352328156648_548830_19619.this, Customer._17_0_2_edc0357_1352328156648_548830_19619._17_0_2_edc0357_1352328158792_819832_20444.class, "C_use_m_MergeNode_CustomerCB", arguments231);
            }
        }

        public class _17_0_2_edc0357_1352328158791_77291_20440 extends DurativeEvent {

            public _17_0_2_edc0357_1352328158791_77291_20440() {
                super();
                init_17_0_2_edc0357_1352328158791_77291_20440Members();
                init_17_0_2_edc0357_1352328158791_77291_20440Collections();
                init_17_0_2_edc0357_1352328158791_77291_20440Elaborations();
            }

            public _17_0_2_edc0357_1352328158791_77291_20440(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328158791_77291_20440Members();
                init_17_0_2_edc0357_1352328158791_77291_20440Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328158791_77291_20440Elaborations();
                fixTimeDependencies();
            }

            public IntegerParameter _17_0_2_edc0357_1352328158535_616746_20341 = null;

            public BooleanParameter objectToPass = null;

            public IntegerParameter _17_0_2_edc0357_1352328160582_184647_21502 = null;

            public BooleanParameter _17_0_2_edc0357_1352328158793_123095_20447_exists = null;

            public Dependency< Integer > _17_0_2_edc0357_1352328158535_616746_20341Dependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Dependency< Integer > _17_0_2_edc0357_1352328160582_184647_21502Dependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158793_123095_20447_existsDependency = null;

            public Effect effect232 = null;

            public Parameter effect232Var = null;

            public ElaborationRule elaborationRule233 = null;

            public ElaborationRule elaborationRule234 = null;

            public void init_17_0_2_edc0357_1352328158791_77291_20440Members() {
                try {
                    if (_17_0_2_edc0357_1352328158535_616746_20341 == null) _17_0_2_edc0357_1352328158535_616746_20341 = new IntegerParameter("_17_0_2_edc0357_1352328158535_616746_20341", (Integer) null, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (_17_0_2_edc0357_1352328160582_184647_21502 == null) _17_0_2_edc0357_1352328160582_184647_21502 = new IntegerParameter("_17_0_2_edc0357_1352328160582_184647_21502", (Integer) null, this);
                    if (_17_0_2_edc0357_1352328158793_123095_20447_exists == null) _17_0_2_edc0357_1352328158793_123095_20447_exists = new BooleanParameter("_17_0_2_edc0357_1352328158793_123095_20447_exists", (Boolean) false, this);
                    Object effect232VarV = sig_17_0_2_edc0357_1352328158796_431791_20468;
                    effect232Var = new Parameter("effect232Var", null, null, this);
                    addDependency(effect232Var, new Expression(effect232VarV));
                    effect232 = new EffectFunction(new EffectFunction(effect232Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158791_77291_20440Collections() {
                parameters.add(_17_0_2_edc0357_1352328158535_616746_20341);
                parameters.add(objectToPass);
                parameters.add(_17_0_2_edc0357_1352328160582_184647_21502);
                parameters.add(_17_0_2_edc0357_1352328158793_123095_20447_exists);
                Set<Effect> effectsForeffect232Var = new TreeSet<Effect>();
                effectsForeffect232Var.add(effect232);
                addEffects((Parameter<?>) effect232Var, effectsForeffect232Var);
            }

            public void init_17_0_2_edc0357_1352328158791_77291_20440Dependencies() {
                addDependency(_17_0_2_edc0357_1352328158535_616746_20341, new Expression<Integer>(_17_0_2_edc0357_1352328160582_184647_21502));
                addDependency(objectToPass, new Expression<Boolean>(true));
                addDependency(_17_0_2_edc0357_1352328160582_184647_21502, new Expression<Integer>(new EffectFunction(sig_17_0_2_edc0357_1352328158796_989489_20466, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_edc0357_1352328158793_123095_20447_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_edc0357_1352328158792_106015_20443_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_edc0357_1352328158792_106015_20443_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
            }

            public void init_17_0_2_edc0357_1352328158791_77291_20440Elaborations() {
                init_17_0_2_edc0357_1352328158791_77291_20440Dependencies();
                Expression<?>[] arguments233 = new Expression<?>[2];
                arguments233[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                arguments233[1] = new Expression<ObjectFlow<Boolean>>(sig_17_0_2_edc0357_1352328158796_431791_20468);
                Expression<Boolean> condition233 = new Expression<Boolean>(_17_0_2_edc0357_1352328158793_123095_20447_exists);
                elaborationRule233 = addElaborationRule(condition233, _17_0_2_edc0357_1352328156648_548830_19619.this, Customer._17_0_2_edc0357_1352328156648_548830_19619._17_0_2_edc0357_1352328158793_123095_20447.class, "C_dr_m_MergeNode_CustomerCB", arguments233);
                Expression<?>[] arguments234 = new Expression<?>[3];
                arguments234[0] = new Expression<Integer>(startTime);
                arguments234[1] = new Expression<DurativeEvent>(this);
                arguments234[2] = new Expression<Integer>(_17_0_2_edc0357_1352328158535_616746_20341);
                Expression<Boolean> condition234 = new Expression<Boolean>(true);
                elaborationRule234 = addElaborationRule(condition234, Customer.this, Customer._17_0_2_edc0357_1352328156645_46192_19618.class, "setDRCap_Activity_Customer", arguments234);
            }
        }

        public class _17_0_2_edc0357_1352328158791_540176_20441 extends DurativeEvent {

            public _17_0_2_edc0357_1352328158791_540176_20441() {
                super();
                init_17_0_2_edc0357_1352328158791_540176_20441Members();
                init_17_0_2_edc0357_1352328158791_540176_20441Collections();
                init_17_0_2_edc0357_1352328158791_540176_20441Elaborations();
            }

            public _17_0_2_edc0357_1352328158791_540176_20441(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328158791_540176_20441Members();
                init_17_0_2_edc0357_1352328158791_540176_20441Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328158791_540176_20441Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_edc0357_1352328158793_331994_20446_exists = null;

            public BooleanParameter objectToPass = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158793_331994_20446_existsDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Effect effect235 = null;

            public Parameter effect235Var = null;

            public ElaborationRule elaborationRule236 = null;

            public ElaborationRule elaborationRule237 = null;

            public void init_17_0_2_edc0357_1352328158791_540176_20441Members() {
                try {
                    if (_17_0_2_edc0357_1352328158793_331994_20446_exists == null) _17_0_2_edc0357_1352328158793_331994_20446_exists = new BooleanParameter("_17_0_2_edc0357_1352328158793_331994_20446_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect235VarV = sig_17_0_2_edc0357_1352328158795_53444_20463;
                    effect235Var = new Parameter("effect235Var", null, null, this);
                    addDependency(effect235Var, new Expression(effect235VarV));
                    effect235 = new EffectFunction(new EffectFunction(effect235Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158791_540176_20441Collections() {
                parameters.add(_17_0_2_edc0357_1352328158793_331994_20446_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect235Var = new TreeSet<Effect>();
                effectsForeffect235Var.add(effect235);
                addEffects((Parameter<?>) effect235Var, effectsForeffect235Var);
            }

            public void init_17_0_2_edc0357_1352328158791_540176_20441Dependencies() {
                addDependency(_17_0_2_edc0357_1352328158793_331994_20446_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_edc0357_1352328158792_106015_20443_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_edc0357_1352328158792_106015_20443_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(objectToPass, new Expression<Boolean>(new EffectFunction(sig_17_0_2_edc0357_1352328158797_121818_20475, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_edc0357_1352328158791_540176_20441Elaborations() {
                init_17_0_2_edc0357_1352328158791_540176_20441Dependencies();
                Expression<?>[] arguments236 = new Expression<?>[2];
                arguments236[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                arguments236[1] = new Expression<ObjectFlow<Boolean>>(sig_17_0_2_edc0357_1352328158795_53444_20463);
                Expression<Boolean> condition236 = new Expression<Boolean>(_17_0_2_edc0357_1352328158793_331994_20446_exists);
                elaborationRule236 = addElaborationRule(condition236, _17_0_2_edc0357_1352328156648_548830_19619.this, Customer._17_0_2_edc0357_1352328156648_548830_19619._17_0_2_edc0357_1352328158793_331994_20446.class, "C_change_m_MergeNode_CustomerCB", arguments236);
                Expression<?>[] arguments237 = new Expression<?>[2];
                arguments237[0] = new Expression<Integer>(startTime);
                arguments237[1] = new Expression<DurativeEvent>(this);
                Expression<Boolean> condition237 = new Expression<Boolean>(true);
                elaborationRule237 = addElaborationRule(condition237, Customer.this, Customer._17_0_2_edc0357_1352328156639_660827_19616.class, "changePowerUsage_Activity_Customer", arguments237);
            }
        }

        public class _17_0_2_edc0357_1352328158791_37119_20442 extends DurativeEvent {

            public _17_0_2_edc0357_1352328158791_37119_20442() {
                super();
                init_17_0_2_edc0357_1352328158791_37119_20442Members();
                init_17_0_2_edc0357_1352328158791_37119_20442Collections();
                init_17_0_2_edc0357_1352328158791_37119_20442Elaborations();
            }

            public _17_0_2_edc0357_1352328158791_37119_20442(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328158791_37119_20442Members();
                init_17_0_2_edc0357_1352328158791_37119_20442Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328158791_37119_20442Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter objectToPass = null;

            public BooleanParameter _17_0_2_edc0357_1352328158791_311867_20439_exists = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158791_311867_20439_existsDependency = null;

            public Effect effect238 = null;

            public Parameter effect238Var = null;

            public ElaborationRule elaborationRule239 = null;

            public ElaborationRule elaborationRule240 = null;

            public void init_17_0_2_edc0357_1352328158791_37119_20442Members() {
                try {
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (_17_0_2_edc0357_1352328158791_311867_20439_exists == null) _17_0_2_edc0357_1352328158791_311867_20439_exists = new BooleanParameter("_17_0_2_edc0357_1352328158791_311867_20439_exists", (Boolean) false, this);
                    Object effect238VarV = sig_17_0_2_edc0357_1352328158795_593515_20461;
                    effect238Var = new Parameter("effect238Var", null, null, this);
                    addDependency(effect238Var, new Expression(effect238VarV));
                    effect238 = new EffectFunction(new EffectFunction(effect238Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158791_37119_20442Collections() {
                parameters.add(objectToPass);
                parameters.add(_17_0_2_edc0357_1352328158791_311867_20439_exists);
                Set<Effect> effectsForeffect238Var = new TreeSet<Effect>();
                effectsForeffect238Var.add(effect238);
                addEffects((Parameter<?>) effect238Var, effectsForeffect238Var);
            }

            public void init_17_0_2_edc0357_1352328158791_37119_20442Dependencies() {
                addDependency(objectToPass, new Expression<Boolean>(new EffectFunction(sig_17_0_2_edc0357_1352328158795_917007_20460, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_edc0357_1352328158791_311867_20439_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_edc0357_1352328158792_106015_20443_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_edc0357_1352328158792_106015_20443_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
            }

            public void init_17_0_2_edc0357_1352328158791_37119_20442Elaborations() {
                init_17_0_2_edc0357_1352328158791_37119_20442Dependencies();
                Expression<?>[] arguments239 = new Expression<?>[2];
                arguments239[0] = new Expression<Integer>(startTime);
                arguments239[1] = new Expression<DurativeEvent>(this);
                Expression<Boolean> condition239 = new Expression<Boolean>(true);
                elaborationRule239 = addElaborationRule(condition239, Customer.this, Customer._17_0_2_edc0357_1352328156638_770509_19615.class, "usePower_Activity_Customer", arguments239);
                Expression<?>[] arguments240 = new Expression<?>[1];
                arguments240[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition240 = new Expression<Boolean>(_17_0_2_edc0357_1352328158791_311867_20439_exists);
                elaborationRule240 = addElaborationRule(condition240, _17_0_2_edc0357_1352328156648_548830_19619.this, Customer._17_0_2_edc0357_1352328156648_548830_19619._17_0_2_edc0357_1352328158791_311867_20439.class, "C_use_timer_AcceptEventAction_CustomerCB", arguments240);
            }
        }

        public class _17_0_2_edc0357_1352328158792_106015_20443 extends DurativeEvent {

            public _17_0_2_edc0357_1352328158792_106015_20443() {
                super();
                init_17_0_2_edc0357_1352328158792_106015_20443Members();
                init_17_0_2_edc0357_1352328158792_106015_20443Collections();
                init_17_0_2_edc0357_1352328158792_106015_20443Elaborations();
            }

            public _17_0_2_edc0357_1352328158792_106015_20443(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328158792_106015_20443Members();
                init_17_0_2_edc0357_1352328158792_106015_20443Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328158792_106015_20443Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter objectToPass = null;

            public Dependency< Integer > endTimeDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Dependency< Integer > finalNode_endTimeDependency = null;

            public void init_17_0_2_edc0357_1352328158792_106015_20443Members() {
                try {
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158792_106015_20443Collections() {
                parameters.add(objectToPass);
            }

            public void init_17_0_2_edc0357_1352328158792_106015_20443Dependencies() {
                addDependency(endTime, new Expression<Integer>(new Functions.Plus(new Expression<Integer>(startTime), new Expression<Integer>(duration))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new EffectFunction(sig_17_0_2_edc0357_1352328158796_819586_20473, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(finalNode_endTime, new Expression<Integer>(endTime));
            }

            public void init_17_0_2_edc0357_1352328158792_106015_20443Elaborations() {
                init_17_0_2_edc0357_1352328158792_106015_20443Dependencies();
            }
        }

        public class _17_0_2_edc0357_1352328158792_819832_20444 extends DurativeEvent {

            public _17_0_2_edc0357_1352328158792_819832_20444() {
                super();
                init_17_0_2_edc0357_1352328158792_819832_20444Members();
                init_17_0_2_edc0357_1352328158792_819832_20444Collections();
                init_17_0_2_edc0357_1352328158792_819832_20444Elaborations();
            }

            public _17_0_2_edc0357_1352328158792_819832_20444(Expression<Integer> startTime, Expression<ObjectFlow<Boolean>> receiveThis) {
                super();
                init_17_0_2_edc0357_1352328158792_819832_20444Members();
                init_17_0_2_edc0357_1352328158792_819832_20444Collections();
                addDependency(this.startTime, startTime);
                addDependency(this.receiveThis, receiveThis);
                init_17_0_2_edc0357_1352328158792_819832_20444Elaborations();
                fixTimeDependencies();
            }

            public Parameter< ObjectFlow<Boolean> > receiveThis = null;

            public BooleanParameter _17_0_2_edc0357_1352328158791_37119_20442_exists = null;

            public BooleanParameter objectToPass = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158791_37119_20442_existsDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Effect effect241 = null;

            public Parameter effect241Var = null;

            public ElaborationRule elaborationRule242 = null;

            public void init_17_0_2_edc0357_1352328158792_819832_20444Members() {
                try {
                    if (receiveThis == null) receiveThis = new Parameter<ObjectFlow<Boolean>>("receiveThis", null, (ObjectFlow<Boolean>) null, this);
                    if (_17_0_2_edc0357_1352328158791_37119_20442_exists == null) _17_0_2_edc0357_1352328158791_37119_20442_exists = new BooleanParameter("_17_0_2_edc0357_1352328158791_37119_20442_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect241VarV = sig_17_0_2_edc0357_1352328158795_917007_20460;
                    effect241Var = new Parameter("effect241Var", null, null, this);
                    addDependency(effect241Var, new Expression(effect241VarV));
                    effect241 = new EffectFunction(new EffectFunction(effect241Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158792_819832_20444Collections() {
                parameters.add(receiveThis);
                parameters.add(_17_0_2_edc0357_1352328158791_37119_20442_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect241Var = new TreeSet<Effect>();
                effectsForeffect241Var.add(effect241);
                addEffects((Parameter<?>) effect241Var, effectsForeffect241Var);
            }

            public void init_17_0_2_edc0357_1352328158792_819832_20444Dependencies() {
                addDependency(_17_0_2_edc0357_1352328158791_37119_20442_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_edc0357_1352328158792_106015_20443_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_edc0357_1352328158792_106015_20443_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new EffectFunction(receiveThis, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_edc0357_1352328158792_819832_20444Elaborations() {
                init_17_0_2_edc0357_1352328158792_819832_20444Dependencies();
                Expression<?>[] arguments242 = new Expression<?>[1];
                arguments242[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition242 = new Expression<Boolean>(_17_0_2_edc0357_1352328158791_37119_20442_exists);
                elaborationRule242 = addElaborationRule(condition242, _17_0_2_edc0357_1352328156648_548830_19619.this, Customer._17_0_2_edc0357_1352328156648_548830_19619._17_0_2_edc0357_1352328158791_37119_20442.class, "C_use_CallBehaviorAction_CustomerCB", arguments242);
            }
        }

        public class _17_0_2_edc0357_1352328158792_72920_20445 extends DurativeEvent {

            public _17_0_2_edc0357_1352328158792_72920_20445() {
                super();
                init_17_0_2_edc0357_1352328158792_72920_20445Members();
                init_17_0_2_edc0357_1352328158792_72920_20445Collections();
                init_17_0_2_edc0357_1352328158792_72920_20445Elaborations();
            }

            public _17_0_2_edc0357_1352328158792_72920_20445(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328158792_72920_20445Members();
                init_17_0_2_edc0357_1352328158792_72920_20445Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328158792_72920_20445Elaborations();
                fixTimeDependencies();
            }

            public IntegerParameter _17_0_2_edc0357_1352328160583_288418_21503 = null;

            public Parameter< Power_System.Signaldr_request > _17_0_2_edc0357_1352328160584_590137_21504 = null;

            public BooleanParameter _17_0_2_edc0357_1352328158791_77291_20440_exists = null;

            public Dependency< Integer > _17_0_2_edc0357_1352328160583_288418_21503Dependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Power_System.Signaldr_request > _17_0_2_edc0357_1352328160584_590137_21504Dependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158791_77291_20440_existsDependency = null;

            public Effect effect243 = null;

            public Parameter effect243Var = null;

            public ElaborationRule elaborationRule244 = null;

            public void init_17_0_2_edc0357_1352328158792_72920_20445Members() {
                try {
                    if (_17_0_2_edc0357_1352328160583_288418_21503 == null) _17_0_2_edc0357_1352328160583_288418_21503 = new IntegerParameter("_17_0_2_edc0357_1352328160583_288418_21503", (Integer) null, this);
                    if (_17_0_2_edc0357_1352328160584_590137_21504 == null) _17_0_2_edc0357_1352328160584_590137_21504 = new Parameter<Power_System.Signaldr_request>("_17_0_2_edc0357_1352328160584_590137_21504", null, (Power_System.Signaldr_request) null, this);
                    if (_17_0_2_edc0357_1352328158791_77291_20440_exists == null) _17_0_2_edc0357_1352328158791_77291_20440_exists = new BooleanParameter("_17_0_2_edc0357_1352328158791_77291_20440_exists", (Boolean) false, this);
                    Object effect243VarV = sig_17_0_2_edc0357_1352328158796_989489_20466;
                    effect243Var = new Parameter("effect243Var", null, null, this);
                    addDependency(effect243Var, new Expression(effect243VarV));
                    effect243 = new EffectFunction(new EffectFunction(effect243Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { _17_0_2_edc0357_1352328160583_288418_21503, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158792_72920_20445Collections() {
                parameters.add(_17_0_2_edc0357_1352328160583_288418_21503);
                parameters.add(_17_0_2_edc0357_1352328160584_590137_21504);
                parameters.add(_17_0_2_edc0357_1352328158791_77291_20440_exists);
                Set<Effect> effectsForeffect243Var = new TreeSet<Effect>();
                effectsForeffect243Var.add(effect243);
                addEffects((Parameter<?>) effect243Var, effectsForeffect243Var);
            }

            public void init_17_0_2_edc0357_1352328158792_72920_20445Dependencies() {
                addDependency(_17_0_2_edc0357_1352328160583_288418_21503, new Expression<Integer>(new EffectFunction(null, ClassUtils.getMethodForArgTypes("TimeVaryingMap<Integer>", "powersystem", "getValue", java.lang.Integer.class), new Object[] { new Expression<Integer>(startTime) }, new FunctionCall(_17_0_2_edc0357_1352328160584_590137_21504, Parameter.class, "getMember", new Object[] { "cap__17_0_2_edc0357_1352328156887_758776_19707" }))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(_17_0_2_edc0357_1352328160584_590137_21504, new Expression<Power_System.Signaldr_request>(new EffectFunction(sig_17_0_2_edc0357_1352328158795_859697_20465, ClassUtils.getMethodForArgTypes("ObjectFlow<Signaldr_request>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_edc0357_1352328158791_77291_20440_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_edc0357_1352328158792_106015_20443_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_edc0357_1352328158792_106015_20443_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
            }

            public void init_17_0_2_edc0357_1352328158792_72920_20445Elaborations() {
                init_17_0_2_edc0357_1352328158792_72920_20445Dependencies();
                Expression<?>[] arguments244 = new Expression<?>[1];
                arguments244[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition244 = new Expression<Boolean>(_17_0_2_edc0357_1352328158791_77291_20440_exists);
                elaborationRule244 = addElaborationRule(condition244, _17_0_2_edc0357_1352328156648_548830_19619.this, Customer._17_0_2_edc0357_1352328156648_548830_19619._17_0_2_edc0357_1352328158791_77291_20440.class, "C_setcap_CallBehaviorAction_CustomerCB", arguments244);
            }
        }

        public class _17_0_2_edc0357_1352328158793_331994_20446 extends DurativeEvent {

            public _17_0_2_edc0357_1352328158793_331994_20446() {
                super();
                init_17_0_2_edc0357_1352328158793_331994_20446Members();
                init_17_0_2_edc0357_1352328158793_331994_20446Collections();
                init_17_0_2_edc0357_1352328158793_331994_20446Elaborations();
            }

            public _17_0_2_edc0357_1352328158793_331994_20446(Expression<Integer> startTime, Expression<ObjectFlow<Boolean>> receiveThis) {
                super();
                init_17_0_2_edc0357_1352328158793_331994_20446Members();
                init_17_0_2_edc0357_1352328158793_331994_20446Collections();
                addDependency(this.startTime, startTime);
                addDependency(this.receiveThis, receiveThis);
                init_17_0_2_edc0357_1352328158793_331994_20446Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_edc0357_1352328158794_405865_20450_exists = null;

            public Parameter< ObjectFlow<Boolean> > receiveThis = null;

            public BooleanParameter objectToPass = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158794_405865_20450_existsDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Effect effect245 = null;

            public Parameter effect245Var = null;

            public ElaborationRule elaborationRule246 = null;

            public void init_17_0_2_edc0357_1352328158793_331994_20446Members() {
                try {
                    if (_17_0_2_edc0357_1352328158794_405865_20450_exists == null) _17_0_2_edc0357_1352328158794_405865_20450_exists = new BooleanParameter("_17_0_2_edc0357_1352328158794_405865_20450_exists", (Boolean) false, this);
                    if (receiveThis == null) receiveThis = new Parameter<ObjectFlow<Boolean>>("receiveThis", null, (ObjectFlow<Boolean>) null, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect245VarV = sig_17_0_2_edc0357_1352328158796_890131_20474;
                    effect245Var = new Parameter("effect245Var", null, null, this);
                    addDependency(effect245Var, new Expression(effect245VarV));
                    effect245 = new EffectFunction(new EffectFunction(effect245Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158793_331994_20446Collections() {
                parameters.add(_17_0_2_edc0357_1352328158794_405865_20450_exists);
                parameters.add(receiveThis);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect245Var = new TreeSet<Effect>();
                effectsForeffect245Var.add(effect245);
                addEffects((Parameter<?>) effect245Var, effectsForeffect245Var);
            }

            public void init_17_0_2_edc0357_1352328158793_331994_20446Dependencies() {
                addDependency(_17_0_2_edc0357_1352328158794_405865_20450_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_edc0357_1352328158792_106015_20443_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_edc0357_1352328158792_106015_20443_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new EffectFunction(receiveThis, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_edc0357_1352328158793_331994_20446Elaborations() {
                init_17_0_2_edc0357_1352328158793_331994_20446Dependencies();
                Expression<?>[] arguments246 = new Expression<?>[1];
                arguments246[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition246 = new Expression<Boolean>(_17_0_2_edc0357_1352328158794_405865_20450_exists);
                elaborationRule246 = addElaborationRule(condition246, _17_0_2_edc0357_1352328156648_548830_19619.this, Customer._17_0_2_edc0357_1352328156648_548830_19619._17_0_2_edc0357_1352328158794_405865_20450.class, "C_change_timer_AcceptEventAction_CustomerCB", arguments246);
            }
        }

        public class _17_0_2_edc0357_1352328158793_123095_20447 extends DurativeEvent {

            public _17_0_2_edc0357_1352328158793_123095_20447() {
                super();
                init_17_0_2_edc0357_1352328158793_123095_20447Members();
                init_17_0_2_edc0357_1352328158793_123095_20447Collections();
                init_17_0_2_edc0357_1352328158793_123095_20447Elaborations();
            }

            public _17_0_2_edc0357_1352328158793_123095_20447(Expression<Integer> startTime, Expression<ObjectFlow<Boolean>> receiveThis) {
                super();
                init_17_0_2_edc0357_1352328158793_123095_20447Members();
                init_17_0_2_edc0357_1352328158793_123095_20447Collections();
                addDependency(this.startTime, startTime);
                addDependency(this.receiveThis, receiveThis);
                init_17_0_2_edc0357_1352328158793_123095_20447Elaborations();
                fixTimeDependencies();
            }

            public Parameter< ObjectFlow<Boolean> > receiveThis = null;

            public BooleanParameter _17_0_2_edc0357_1352328158790_658031_20438_exists = null;

            public BooleanParameter objectToPass = null;

            public Dependency< Integer > endTimeDependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158790_658031_20438_existsDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Effect effect247 = null;

            public Parameter effect247Var = null;

            public ElaborationRule elaborationRule248 = null;

            public void init_17_0_2_edc0357_1352328158793_123095_20447Members() {
                try {
                    if (receiveThis == null) receiveThis = new Parameter<ObjectFlow<Boolean>>("receiveThis", null, (ObjectFlow<Boolean>) null, this);
                    if (_17_0_2_edc0357_1352328158790_658031_20438_exists == null) _17_0_2_edc0357_1352328158790_658031_20438_exists = new BooleanParameter("_17_0_2_edc0357_1352328158790_658031_20438_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect247VarV = sig_17_0_2_edc0357_1352328158796_917263_20467;
                    effect247Var = new Parameter("effect247Var", null, null, this);
                    addDependency(effect247Var, new Expression(effect247VarV));
                    effect247 = new EffectFunction(new EffectFunction(effect247Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158793_123095_20447Collections() {
                parameters.add(receiveThis);
                parameters.add(_17_0_2_edc0357_1352328158790_658031_20438_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect247Var = new TreeSet<Effect>();
                effectsForeffect247Var.add(effect247);
                addEffects((Parameter<?>) effect247Var, effectsForeffect247Var);
            }

            public void init_17_0_2_edc0357_1352328158793_123095_20447Dependencies() {
                addDependency(endTime, new Expression<Integer>(new FunctionCall(null, ClassUtils.getMethodForArgTypes("Math", "powersystem", "min", int.class, int.class), new Object[] { new Expression(new EffectFunction(q_Customer_dr_request, ClassUtils.getMethodForArgTypes("ObjectFlow<Signaldr_request>", "powersystem", "nextTimeHasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(startTime) })), new Expression<Integer>(finalNode_endTime) })));
                addDependency(_17_0_2_edc0357_1352328158790_658031_20438_exists, new Expression<Boolean>(new Functions.And(new Expression(new EffectFunction(q_Customer_dr_request, ClassUtils.getMethodForArgTypes("ObjectFlow<Signaldr_request>", "powersystem", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_edc0357_1352328158792_106015_20443_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_edc0357_1352328158792_106015_20443_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))))));
                addDependency(objectToPass, new Expression<Boolean>(new EffectFunction(receiveThis, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_edc0357_1352328158793_123095_20447Elaborations() {
                init_17_0_2_edc0357_1352328158793_123095_20447Dependencies();
                Expression<?>[] arguments248 = new Expression<?>[1];
                arguments248[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition248 = new Expression<Boolean>(_17_0_2_edc0357_1352328158790_658031_20438_exists);
                elaborationRule248 = addElaborationRule(condition248, _17_0_2_edc0357_1352328156648_548830_19619.this, Customer._17_0_2_edc0357_1352328156648_548830_19619._17_0_2_edc0357_1352328158790_658031_20438.class, "C_get_drsig_AcceptEventAction_CustomerCB", arguments248);
            }
        }

        public class _17_0_2_edc0357_1352328158793_123216_20448 extends DurativeEvent {

            public _17_0_2_edc0357_1352328158793_123216_20448() {
                super();
                init_17_0_2_edc0357_1352328158793_123216_20448Members();
                init_17_0_2_edc0357_1352328158793_123216_20448Collections();
                init_17_0_2_edc0357_1352328158793_123216_20448Elaborations();
            }

            public _17_0_2_edc0357_1352328158793_123216_20448(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328158793_123216_20448Members();
                init_17_0_2_edc0357_1352328158793_123216_20448Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328158793_123216_20448Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_edc0357_1352328158790_493227_20437_exists = null;

            public BooleanParameter objectToPass = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158790_493227_20437_existsDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Effect effect249 = null;

            public Parameter effect249Var = null;

            public ElaborationRule elaborationRule250 = null;

            public void init_17_0_2_edc0357_1352328158793_123216_20448Members() {
                try {
                    if (_17_0_2_edc0357_1352328158790_493227_20437_exists == null) _17_0_2_edc0357_1352328158790_493227_20437_exists = new BooleanParameter("_17_0_2_edc0357_1352328158790_493227_20437_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect249VarV = sig_17_0_2_edc0357_1352328158796_290441_20471;
                    effect249Var = new Parameter("effect249Var", null, null, this);
                    addDependency(effect249Var, new Expression(effect249VarV));
                    effect249 = new EffectFunction(new EffectFunction(effect249Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158793_123216_20448Collections() {
                parameters.add(_17_0_2_edc0357_1352328158790_493227_20437_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect249Var = new TreeSet<Effect>();
                effectsForeffect249Var.add(effect249);
                addEffects((Parameter<?>) effect249Var, effectsForeffect249Var);
            }

            public void init_17_0_2_edc0357_1352328158793_123216_20448Dependencies() {
                addDependency(_17_0_2_edc0357_1352328158790_493227_20437_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_edc0357_1352328158792_106015_20443_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_edc0357_1352328158792_106015_20443_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(5));
                addDependency(objectToPass, new Expression<Boolean>(new EffectFunction(sig_17_0_2_edc0357_1352328158796_758419_20470, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_edc0357_1352328158793_123216_20448Elaborations() {
                init_17_0_2_edc0357_1352328158793_123216_20448Dependencies();
                Expression<?>[] arguments250 = new Expression<?>[1];
                arguments250[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition250 = new Expression<Boolean>(_17_0_2_edc0357_1352328158790_493227_20437_exists);
                elaborationRule250 = addElaborationRule(condition250, _17_0_2_edc0357_1352328156648_548830_19619.this, Customer._17_0_2_edc0357_1352328156648_548830_19619._17_0_2_edc0357_1352328158790_493227_20437.class, "C_fork_ForkNode_CustomerCB", arguments250);
            }
        }

        public class _17_0_2_edc0357_1352328158793_68185_20449 extends DurativeEvent {

            public _17_0_2_edc0357_1352328158793_68185_20449() {
                super();
                init_17_0_2_edc0357_1352328158793_68185_20449Members();
                init_17_0_2_edc0357_1352328158793_68185_20449Collections();
                init_17_0_2_edc0357_1352328158793_68185_20449Elaborations();
            }

            public _17_0_2_edc0357_1352328158793_68185_20449(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328158793_68185_20449Members();
                init_17_0_2_edc0357_1352328158793_68185_20449Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328158793_68185_20449Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter objectToPass = null;

            public Dependency< Integer > finalNode_startTimeDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Effect effect251 = null;

            public Parameter effect251Var = null;

            public void init_17_0_2_edc0357_1352328158793_68185_20449Members() {
                try {
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect251VarV = sig_17_0_2_edc0357_1352328158796_819586_20473;
                    effect251Var = new Parameter("effect251Var", null, null, this);
                    addDependency(effect251Var, new Expression(effect251VarV));
                    effect251 = new EffectFunction(new EffectFunction(effect251Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158793_68185_20449Collections() {
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect251Var = new TreeSet<Effect>();
                effectsForeffect251Var.add(effect251);
                addEffects((Parameter<?>) effect251Var, effectsForeffect251Var);
            }

            public void init_17_0_2_edc0357_1352328158793_68185_20449Dependencies() {
                addDependency(finalNode_startTime, new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))));
                addDependency(duration, new Expression<Integer>(300));
                addDependency(objectToPass, new Expression<Boolean>(new EffectFunction(sig_17_0_2_edc0357_1352328158796_884417_20472, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_edc0357_1352328158793_68185_20449Elaborations() {
                init_17_0_2_edc0357_1352328158793_68185_20449Dependencies();
            }
        }

        public class _17_0_2_edc0357_1352328158794_405865_20450 extends DurativeEvent {

            public _17_0_2_edc0357_1352328158794_405865_20450() {
                super();
                init_17_0_2_edc0357_1352328158794_405865_20450Members();
                init_17_0_2_edc0357_1352328158794_405865_20450Collections();
                init_17_0_2_edc0357_1352328158794_405865_20450Elaborations();
            }

            public _17_0_2_edc0357_1352328158794_405865_20450(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328158794_405865_20450Members();
                init_17_0_2_edc0357_1352328158794_405865_20450Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328158794_405865_20450Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_edc0357_1352328158791_540176_20441_exists = null;

            public BooleanParameter objectToPass = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158791_540176_20441_existsDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Effect effect252 = null;

            public Parameter effect252Var = null;

            public ElaborationRule elaborationRule253 = null;

            public void init_17_0_2_edc0357_1352328158794_405865_20450Members() {
                try {
                    if (_17_0_2_edc0357_1352328158791_540176_20441_exists == null) _17_0_2_edc0357_1352328158791_540176_20441_exists = new BooleanParameter("_17_0_2_edc0357_1352328158791_540176_20441_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect252VarV = sig_17_0_2_edc0357_1352328158797_121818_20475;
                    effect252Var = new Parameter("effect252Var", null, null, this);
                    addDependency(effect252Var, new Expression(effect252VarV));
                    effect252 = new EffectFunction(new EffectFunction(effect252Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158794_405865_20450Collections() {
                parameters.add(_17_0_2_edc0357_1352328158791_540176_20441_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect252Var = new TreeSet<Effect>();
                effectsForeffect252Var.add(effect252);
                addEffects((Parameter<?>) effect252Var, effectsForeffect252Var);
            }

            public void init_17_0_2_edc0357_1352328158794_405865_20450Dependencies() {
                addDependency(_17_0_2_edc0357_1352328158791_540176_20441_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_edc0357_1352328158792_106015_20443_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_edc0357_1352328158792_106015_20443_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(150));
                addDependency(objectToPass, new Expression<Boolean>(new EffectFunction(sig_17_0_2_edc0357_1352328158796_890131_20474, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_edc0357_1352328158794_405865_20450Elaborations() {
                init_17_0_2_edc0357_1352328158794_405865_20450Dependencies();
                Expression<?>[] arguments253 = new Expression<?>[1];
                arguments253[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition253 = new Expression<Boolean>(_17_0_2_edc0357_1352328158791_540176_20441_exists);
                elaborationRule253 = addElaborationRule(condition253, _17_0_2_edc0357_1352328156648_548830_19619.this, Customer._17_0_2_edc0357_1352328156648_548830_19619._17_0_2_edc0357_1352328158791_540176_20441.class, "C_change_CallBehaviorAction_CustomerCB", arguments253);
            }
        }

        public _17_0_2_edc0357_1352328156648_548830_19619(Expression<Integer> startTime) {
            super();
            init_17_0_2_edc0357_1352328156648_548830_19619Members();
            init_17_0_2_edc0357_1352328156648_548830_19619Collections();
            addDependency(this.startTime, startTime);
            init_17_0_2_edc0357_1352328156648_548830_19619Elaborations();
            fixTimeDependencies();
        }

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_edc0357_1352328158795_900235_20462 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_edc0357_1352328158796_431791_20468 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_edc0357_1352328158796_819586_20473 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_edc0357_1352328158795_231230_20458 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_edc0357_1352328158796_290441_20471 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_edc0357_1352328158796_279864_20469 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_edc0357_1352328158795_332074_20459 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_edc0357_1352328158797_121818_20475 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_edc0357_1352328158796_758419_20470 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_2_edc0357_1352328158796_989489_20466 = null;

        public IntegerParameter finalNode_startTime = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_edc0357_1352328158795_53444_20463 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_edc0357_1352328158796_884417_20472 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_edc0357_1352328158795_917007_20460 = null;

        public Parameter< ObjectFlow<Power_System.Signaldr_request> > sig_17_0_2_edc0357_1352328158795_859697_20465 = null;

        public IntegerParameter finalNode_endTime = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_edc0357_1352328158796_890131_20474 = null;

        public BooleanParameter _17_0_2_edc0357_1352328158792_106015_20443_exists = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_edc0357_1352328158796_917263_20467 = null;

        public Parameter< ObjectFlow<Power_System.Signaldr_request> > sig_17_0_2_edc0357_1352328155061_223729_19222 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_edc0357_1352328158795_593515_20461 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_edc0357_1352328158795_852079_20464 = null;

        public Dependency< Integer > endTimeDependency = null;

        public Dependency< Boolean > _17_0_2_edc0357_1352328158792_106015_20443_existsDependency = null;

        public ElaborationRule elaborationRule213 = null;

        public ElaborationRule elaborationRule214 = null;

        public void init_17_0_2_edc0357_1352328156648_548830_19619Members() {
            try {
                if (sig_17_0_2_edc0357_1352328158795_900235_20462 == null) sig_17_0_2_edc0357_1352328158795_900235_20462 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328158795_900235_20462", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158795_900235_20462" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328158796_431791_20468 == null) sig_17_0_2_edc0357_1352328158796_431791_20468 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328158796_431791_20468", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158796_431791_20468" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328158796_819586_20473 == null) sig_17_0_2_edc0357_1352328158796_819586_20473 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328158796_819586_20473", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158796_819586_20473" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328158795_231230_20458 == null) sig_17_0_2_edc0357_1352328158795_231230_20458 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328158795_231230_20458", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158795_231230_20458" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328158796_290441_20471 == null) sig_17_0_2_edc0357_1352328158796_290441_20471 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328158796_290441_20471", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158796_290441_20471" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328158796_279864_20469 == null) sig_17_0_2_edc0357_1352328158796_279864_20469 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328158796_279864_20469", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158796_279864_20469" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328158795_332074_20459 == null) sig_17_0_2_edc0357_1352328158795_332074_20459 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328158795_332074_20459", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158795_332074_20459" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328158797_121818_20475 == null) sig_17_0_2_edc0357_1352328158797_121818_20475 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328158797_121818_20475", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158797_121818_20475" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328158796_758419_20470 == null) sig_17_0_2_edc0357_1352328158796_758419_20470 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328158796_758419_20470", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158796_758419_20470" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328158796_989489_20466 == null) sig_17_0_2_edc0357_1352328158796_989489_20466 = new Parameter<ObjectFlow<Integer>>("sig_17_0_2_edc0357_1352328158796_989489_20466", null, (ObjectFlow<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158796_989489_20466" })).evaluate(true), this);
                if (finalNode_startTime == null) finalNode_startTime = new IntegerParameter("finalNode_startTime", (Integer) null, this);
                if (sig_17_0_2_edc0357_1352328158795_53444_20463 == null) sig_17_0_2_edc0357_1352328158795_53444_20463 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328158795_53444_20463", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158795_53444_20463" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328158796_884417_20472 == null) sig_17_0_2_edc0357_1352328158796_884417_20472 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328158796_884417_20472", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158796_884417_20472" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328158795_917007_20460 == null) sig_17_0_2_edc0357_1352328158795_917007_20460 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328158795_917007_20460", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158795_917007_20460" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328158795_859697_20465 == null) sig_17_0_2_edc0357_1352328158795_859697_20465 = new Parameter<ObjectFlow<Power_System.Signaldr_request>>("sig_17_0_2_edc0357_1352328158795_859697_20465", null, (ObjectFlow<Power_System.Signaldr_request>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158795_859697_20465" })).evaluate(true), this);
                if (finalNode_endTime == null) finalNode_endTime = new IntegerParameter("finalNode_endTime", (Integer) null, this);
                if (sig_17_0_2_edc0357_1352328158796_890131_20474 == null) sig_17_0_2_edc0357_1352328158796_890131_20474 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328158796_890131_20474", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158796_890131_20474" })).evaluate(true), this);
                if (_17_0_2_edc0357_1352328158792_106015_20443_exists == null) _17_0_2_edc0357_1352328158792_106015_20443_exists = new BooleanParameter("_17_0_2_edc0357_1352328158792_106015_20443_exists", (Boolean) false, this);
                if (sig_17_0_2_edc0357_1352328158796_917263_20467 == null) sig_17_0_2_edc0357_1352328158796_917263_20467 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328158796_917263_20467", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158796_917263_20467" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328155061_223729_19222 == null) sig_17_0_2_edc0357_1352328155061_223729_19222 = new Parameter<ObjectFlow<Power_System.Signaldr_request>>("sig_17_0_2_edc0357_1352328155061_223729_19222", null, (ObjectFlow<Power_System.Signaldr_request>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328155061_223729_19222" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328158795_593515_20461 == null) sig_17_0_2_edc0357_1352328158795_593515_20461 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328158795_593515_20461", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158795_593515_20461" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328158795_852079_20464 == null) sig_17_0_2_edc0357_1352328158795_852079_20464 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328158795_852079_20464", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158795_852079_20464" })).evaluate(true), this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void init_17_0_2_edc0357_1352328156648_548830_19619Collections() {
            parameters.add(sig_17_0_2_edc0357_1352328158795_900235_20462);
            parameters.add(sig_17_0_2_edc0357_1352328158796_431791_20468);
            parameters.add(sig_17_0_2_edc0357_1352328158796_819586_20473);
            parameters.add(sig_17_0_2_edc0357_1352328158795_231230_20458);
            parameters.add(sig_17_0_2_edc0357_1352328158796_290441_20471);
            parameters.add(sig_17_0_2_edc0357_1352328158796_279864_20469);
            parameters.add(sig_17_0_2_edc0357_1352328158795_332074_20459);
            parameters.add(sig_17_0_2_edc0357_1352328158797_121818_20475);
            parameters.add(sig_17_0_2_edc0357_1352328158796_758419_20470);
            parameters.add(sig_17_0_2_edc0357_1352328158796_989489_20466);
            parameters.add(finalNode_startTime);
            parameters.add(sig_17_0_2_edc0357_1352328158795_53444_20463);
            parameters.add(sig_17_0_2_edc0357_1352328158796_884417_20472);
            parameters.add(sig_17_0_2_edc0357_1352328158795_917007_20460);
            parameters.add(sig_17_0_2_edc0357_1352328158795_859697_20465);
            parameters.add(finalNode_endTime);
            parameters.add(sig_17_0_2_edc0357_1352328158796_890131_20474);
            parameters.add(_17_0_2_edc0357_1352328158792_106015_20443_exists);
            parameters.add(sig_17_0_2_edc0357_1352328158796_917263_20467);
            parameters.add(sig_17_0_2_edc0357_1352328155061_223729_19222);
            parameters.add(sig_17_0_2_edc0357_1352328158795_593515_20461);
            parameters.add(sig_17_0_2_edc0357_1352328158795_852079_20464);
        }

        public void init_17_0_2_edc0357_1352328156648_548830_19619Dependencies() {
            addDependency(endTime, new Expression<Integer>(finalNode_endTime));
            addDependency(_17_0_2_edc0357_1352328158792_106015_20443_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.NotEquals(new Expression<Integer>(finalNode_startTime), null)), new Expression(new EffectFunction(sig_17_0_2_edc0357_1352328158796_819586_20473, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(new Functions.Minus(new Expression<Integer>(finalNode_startTime), new Expression<Integer>(1))) })))));
        }

        public void init_17_0_2_edc0357_1352328156648_548830_19619Elaborations() {
            init_17_0_2_edc0357_1352328156648_548830_19619Dependencies();
            Expression<?>[] arguments213 = new Expression<?>[1];
            arguments213[0] = new Expression<Integer>(startTime);
            Expression<Boolean> condition213 = new Expression<Boolean>(true);
            elaborationRule213 = addElaborationRule(condition213, _17_0_2_edc0357_1352328156648_548830_19619.this, Customer._17_0_2_edc0357_1352328156648_548830_19619._17_0_2_edc0357_1352328158789_563871_20435.class, "C_start_InitialNode_CustomerCB", arguments213);
            Expression<?>[] arguments214 = new Expression<?>[1];
            arguments214[0] = new Expression<Integer>(finalNode_startTime);
            Expression<Boolean> condition214 = new Expression<Boolean>(_17_0_2_edc0357_1352328158792_106015_20443_exists);
            elaborationRule214 = addElaborationRule(condition214, _17_0_2_edc0357_1352328156648_548830_19619.this, Customer._17_0_2_edc0357_1352328156648_548830_19619._17_0_2_edc0357_1352328158792_106015_20443.class, "C_end_ActivityFinalNode_CustomerCB", arguments214);
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

    public Parameter< TimeVaryingPlottableMap<Integer> > usage__17_0_2_edc0357_1352328156649_216766_19620 = null;

    public Parameter< TimeVaryingPlottableMap<Integer> > cap__17_0_2_edc0357_1352328156659_30024_19625 = null;

    public Parameter< ObjectFlow<Power_System.Signaldr_request> > q_Customer_dr_request = null;

    public Parameter< Power_System > x = null;

    public Parameter< ObjectFlow<Power_System.Signalyes> > q_Customer_yes = null;

    public Parameter< ObjectFlow<Power_System.SignalchangeLoadValue> > q_Customer_changeLoadValue = null;

    public Parameter< ObjectFlow<Power_System.Signalno> > q_Customer_no = null;

    public void initCustomerMembers() {
        try {
            if (classifierBehavior == null) classifierBehavior = new StringParameter("classifierBehavior", (String) "_17_0_2_edc0357_1352328156648_548830_19619", this);
            if (q_Customer_receiveMeterReading == null) q_Customer_receiveMeterReading = new Parameter<ObjectFlow<Power_System.SignalreceiveMeterReading>>("q_Customer_receiveMeterReading", null, (ObjectFlow<Power_System.SignalreceiveMeterReading>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class, java.lang.Class.class), new Object[] { "q_Customer_receiveMeterReading", Power_System.SignalreceiveMeterReading.class })).evaluate(true), this);
            if (usage__17_0_2_edc0357_1352328156649_216766_19620 == null) usage__17_0_2_edc0357_1352328156649_216766_19620 = new Parameter<TimeVaryingPlottableMap<Integer>>("usage__17_0_2_edc0357_1352328156649_216766_19620", null, (TimeVaryingPlottableMap<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingPlottableMap.class, java.lang.String.class), new Object[] { "usage" })).evaluate(true), this);
            if (cap__17_0_2_edc0357_1352328156659_30024_19625 == null) cap__17_0_2_edc0357_1352328156659_30024_19625 = new Parameter<TimeVaryingPlottableMap<Integer>>("cap__17_0_2_edc0357_1352328156659_30024_19625", null, (TimeVaryingPlottableMap<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingPlottableMap.class, java.lang.String.class), new Object[] { "cap" })).evaluate(true), this);
            if (q_Customer_dr_request == null) q_Customer_dr_request = new Parameter<ObjectFlow<Power_System.Signaldr_request>>("q_Customer_dr_request", null, (ObjectFlow<Power_System.Signaldr_request>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class, java.lang.Class.class), new Object[] { "q_Customer_dr_request", Power_System.Signaldr_request.class })).evaluate(true), this);
            if (x == null) x = new Parameter<Power_System>("x", null, (Power_System) null, this);
            if (q_Customer_yes == null) q_Customer_yes = new Parameter<ObjectFlow<Power_System.Signalyes>>("q_Customer_yes", null, (ObjectFlow<Power_System.Signalyes>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class, java.lang.Class.class), new Object[] { "q_Customer_yes", Power_System.Signalyes.class })).evaluate(true), this);
            if (q_Customer_changeLoadValue == null) q_Customer_changeLoadValue = new Parameter<ObjectFlow<Power_System.SignalchangeLoadValue>>("q_Customer_changeLoadValue", null, (ObjectFlow<Power_System.SignalchangeLoadValue>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class, java.lang.Class.class), new Object[] { "q_Customer_changeLoadValue", Power_System.SignalchangeLoadValue.class })).evaluate(true), this);
            if (q_Customer_no == null) q_Customer_no = new Parameter<ObjectFlow<Power_System.Signalno>>("q_Customer_no", null, (ObjectFlow<Power_System.Signalno>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class, java.lang.Class.class), new Object[] { "q_Customer_no", Power_System.Signalno.class })).evaluate(true), this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void initCustomerCollections() {
        parameters.add(classifierBehavior);
        parameters.add(q_Customer_receiveMeterReading);
        parameters.add(usage__17_0_2_edc0357_1352328156649_216766_19620);
        parameters.add(cap__17_0_2_edc0357_1352328156659_30024_19625);
        parameters.add(q_Customer_dr_request);
        parameters.add(x);
        parameters.add(q_Customer_yes);
        parameters.add(q_Customer_changeLoadValue);
        parameters.add(q_Customer_no);
    }

    public void initCustomerDependencies() {
    }

    public void initCustomerElaborations() {
        initCustomerDependencies();
    }
}
