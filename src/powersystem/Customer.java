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
                    effect34 = new EffectFunction(new FunctionCall(effect34Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
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
                addDependency(_17_0_2_edc0357_1352328158272_534943_20197_exists, new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall));
            }

            public void init_17_0_2_edc0357_1352328158272_257857_20196Elaborations() {
                init_17_0_2_edc0357_1352328158272_257857_20196Dependencies();
                Expression<?>[] arguments35 = new Expression<?>[1];
                arguments35[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition35 = new Expression<Boolean>(_17_0_2_edc0357_1352328158272_534943_20197_exists);
                elaborationRule35 = addElaborationRule(condition35, _17_0_2_edc0357_1352328156638_770509_19615.this, Customer._17_0_2_edc0357_1352328156638_770509_19615._17_0_2_edc0357_1352328158272_534943_20197.class, "_ReadSelfAction_usePower", arguments35);
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
                    effect36 = new EffectFunction(new FunctionCall(effect36Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { _17_0_2_edc0357_1352328160533_21059_21384, endTime }));
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
                addDependency(_17_0_2_edc0357_1352328158274_877259_20201_exists, new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new FunctionCall(sig_17_0_2_edc0357_1352328158276_873292_20205, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_edc0357_1352328158272_534943_20197Elaborations() {
                init_17_0_2_edc0357_1352328158272_534943_20197Dependencies();
                Expression<?>[] arguments37 = new Expression<?>[1];
                arguments37[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition37 = new Expression<Boolean>(_17_0_2_edc0357_1352328158274_877259_20201_exists);
                elaborationRule37 = addElaborationRule(condition37, _17_0_2_edc0357_1352328156638_770509_19615.this, Customer._17_0_2_edc0357_1352328156638_770509_19615._17_0_2_edc0357_1352328158274_877259_20201.class, "_ForkNode_usePower", arguments37);
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
                addDependency(endTime, new Expression<Integer>((new Functions.Plus(new Expression<Integer>(startTime), new Expression<Integer>(duration))).functionCall));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new FunctionCall(sig_17_0_2_edc0357_1352328158277_748946_20214, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
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
                    effect38 = new EffectFunction(new FunctionCall(effect38Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { _17_0_2_edc0357_1352328160534_502046_21385, endTime }));
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
                addDependency(_17_0_2_edc0357_1352328158275_94410_20203_exists, new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall));
                addDependency(_17_0_2_edc0357_1352328160535_109318_21386, new Expression<Customer>(new FunctionCall(sig_17_0_2_edc0357_1352328158276_305344_20207, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(_17_0_2_edc0357_1352328160534_502046_21385, new Expression<Integer>(new FunctionCall(null, ClassUtils.getMethodForArgTypes("TimeVaryingMap<Integer>", "powersystem", "getValue", java.lang.Integer.class), new Object[] { new Expression<Integer>(startTime) }, new FunctionCall(_17_0_2_edc0357_1352328160535_109318_21386, Parameter.class, "getMember", new Object[] { "usage__17_0_2_edc0357_1352328156649_216766_19620" }))));
            }

            public void init_17_0_2_edc0357_1352328158273_329618_20199Elaborations() {
                init_17_0_2_edc0357_1352328158273_329618_20199Dependencies();
                Expression<?>[] arguments39 = new Expression<?>[1];
                arguments39[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition39 = new Expression<Boolean>(_17_0_2_edc0357_1352328158275_94410_20203_exists);
                elaborationRule39 = addElaborationRule(condition39, _17_0_2_edc0357_1352328156638_770509_19615.this, Customer._17_0_2_edc0357_1352328156638_770509_19615._17_0_2_edc0357_1352328158275_94410_20203.class, "fn2_ForkNode_usePower", arguments39);
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

            public Dependency< Integer > _17_0_2_edc0357_1352328160536_968371_21388Dependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158275_65316_20204_existsDependency = null;

            public Dependency< Customer > _17_0_2_edc0357_1352328160535_385192_21387Dependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Integer > myDeciderID_decider_17_0_2_edc0357_1352328158275_65316_20204Dependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Effect effect40 = null;

            public Parameter effect40Var = null;

            public Effect effect41 = null;

            public Parameter effect41Var = null;

            public Effect effect42 = null;

            public Parameter effect42Var = null;

            public ElaborationRule elaborationRule43 = null;

            public void init_17_0_2_edc0357_1352328158273_110644_20200Members() {
                try {
                    if (_17_0_2_edc0357_1352328160536_968371_21388 == null) _17_0_2_edc0357_1352328160536_968371_21388 = new IntegerParameter("_17_0_2_edc0357_1352328160536_968371_21388", (Integer) null, this);
                    if (_17_0_2_edc0357_1352328158275_65316_20204_exists == null) _17_0_2_edc0357_1352328158275_65316_20204_exists = new BooleanParameter("_17_0_2_edc0357_1352328158275_65316_20204_exists", (Boolean) false, this);
                    if (_17_0_2_edc0357_1352328160535_385192_21387 == null) _17_0_2_edc0357_1352328160535_385192_21387 = new Parameter<Customer>("_17_0_2_edc0357_1352328160535_385192_21387", null, (Customer) null, this);
                    if (myDeciderID_decider_17_0_2_edc0357_1352328158275_65316_20204 == null) myDeciderID_decider_17_0_2_edc0357_1352328158275_65316_20204 = new IntegerParameter("myDeciderID_decider_17_0_2_edc0357_1352328158275_65316_20204", (Integer) 1, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect40VarV = sig_17_0_2_edc0357_1352328158277_686827_20213;
                    effect40Var = new Parameter("effect40Var", null, null, this);
                    addDependency(effect40Var, new Expression(effect40VarV));
                    effect40 = new EffectFunction(new FunctionCall(effect40Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect41VarV = decider_17_0_2_edc0357_1352328158275_65316_20204;
                    effect41Var = new Parameter("effect41Var", null, null, this);
                    addDependency(effect41Var, new Expression(effect41VarV));
                    effect41 = new EffectFunction(new FunctionCall(effect41Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, myDeciderID_decider_17_0_2_edc0357_1352328158275_65316_20204 }));
                    Object effect42VarV = new FunctionCall(x, Parameter.class, "getMember", new Object[] { "ss_17_0_2_edc0357_1352328156650_180051_19621_changeLoadValue" });
                    effect42Var = new Parameter("effect42Var", null, null, this);
                    addDependency(effect42Var, new Expression(effect42VarV));
                    effect42 = new EffectFunction(new FunctionCall(null, ClassUtils.getMethodForArgTypes("ObjectFlow<SignalchangeLoadValue>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { x.getValue().new SignalchangeLoadValue(endTime, _17_0_2_edc0357_1352328160536_968371_21388.getValue()), endTime }, effect42Var));
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

            public void init_17_0_2_edc0357_1352328158273_110644_20200Dependencies() {
                addDependency(_17_0_2_edc0357_1352328160536_968371_21388, new Expression<Integer>(new FunctionCall(sig_17_0_2_edc0357_1352328158277_962194_20211, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_edc0357_1352328158275_65316_20204_exists, new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158275_65316_20204, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158275_65316_20204, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "maxSize"), new Object[] {})))).functionCall), new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158275_65316_20204, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_edc0357_1352328158275_65316_20204))).functionCall))).functionCall), new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall))).functionCall));
                addDependency(_17_0_2_edc0357_1352328160535_385192_21387, new Expression<Customer>(new FunctionCall(sig_17_0_2_edc0357_1352328158277_91616_20208, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(myDeciderID_decider_17_0_2_edc0357_1352328158275_65316_20204, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(true));
            }

            public void init_17_0_2_edc0357_1352328158273_110644_20200Elaborations() {
                init_17_0_2_edc0357_1352328158273_110644_20200Dependencies();
                Expression<?>[] arguments43 = new Expression<?>[1];
                arguments43[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition43 = new Expression<Boolean>(_17_0_2_edc0357_1352328158275_65316_20204_exists);
                elaborationRule43 = addElaborationRule(condition43, _17_0_2_edc0357_1352328156638_770509_19615.this, Customer._17_0_2_edc0357_1352328156638_770509_19615._17_0_2_edc0357_1352328158275_65316_20204.class, "fnn1_JoinNode_usePower", arguments43);
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

            public Effect effect44 = null;

            public Parameter effect44Var = null;

            public Effect effect45 = null;

            public Parameter effect45Var = null;

            public Effect effect46 = null;

            public Parameter effect46Var = null;

            public Effect effect47 = null;

            public Parameter effect47Var = null;

            public Effect effect48 = null;

            public Parameter effect48Var = null;

            public ElaborationRule elaborationRule49 = null;

            public ElaborationRule elaborationRule50 = null;

            public ElaborationRule elaborationRule51 = null;

            public void init_17_0_2_edc0357_1352328158274_877259_20201Members() {
                try {
                    if (myDeciderID_decider_17_0_2_edc0357_1352328158274_699982_20202 == null) myDeciderID_decider_17_0_2_edc0357_1352328158274_699982_20202 = new IntegerParameter("myDeciderID_decider_17_0_2_edc0357_1352328158274_699982_20202", (Integer) 2, this);
                    if (_17_0_2_edc0357_1352328158274_699982_20202_exists == null) _17_0_2_edc0357_1352328158274_699982_20202_exists = new BooleanParameter("_17_0_2_edc0357_1352328158274_699982_20202_exists", (Boolean) false, this);
                    if (myDeciderID_decider_17_0_2_edc0357_1352328158273_110644_20200 == null) myDeciderID_decider_17_0_2_edc0357_1352328158273_110644_20200 = new IntegerParameter("myDeciderID_decider_17_0_2_edc0357_1352328158273_110644_20200", (Integer) 2, this);
                    if (_17_0_2_edc0357_1352328158273_329618_20199_exists == null) _17_0_2_edc0357_1352328158273_329618_20199_exists = new BooleanParameter("_17_0_2_edc0357_1352328158273_329618_20199_exists", (Boolean) false, this);
                    if (_17_0_2_edc0357_1352328158273_110644_20200_exists == null) _17_0_2_edc0357_1352328158273_110644_20200_exists = new BooleanParameter("_17_0_2_edc0357_1352328158273_110644_20200_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new Parameter<Customer>("objectToPass", null, (Customer) null, this);
                    Object effect44VarV = sig_17_0_2_edc0357_1352328158276_305344_20207;
                    effect44Var = new Parameter("effect44Var", null, null, this);
                    addDependency(effect44Var, new Expression(effect44VarV));
                    effect44 = new EffectFunction(new FunctionCall(effect44Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect45VarV = sig_17_0_2_edc0357_1352328158277_91616_20208;
                    effect45Var = new Parameter("effect45Var", null, null, this);
                    addDependency(effect45Var, new Expression(effect45VarV));
                    effect45 = new EffectFunction(new FunctionCall(effect45Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect46VarV = sig_17_0_2_edc0357_1352328158277_34448_20209;
                    effect46Var = new Parameter("effect46Var", null, null, this);
                    addDependency(effect46Var, new Expression(effect46VarV));
                    effect46 = new EffectFunction(new FunctionCall(effect46Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect47VarV = decider_17_0_2_edc0357_1352328158273_110644_20200;
                    effect47Var = new Parameter("effect47Var", null, null, this);
                    addDependency(effect47Var, new Expression(effect47VarV));
                    effect47 = new EffectFunction(new FunctionCall(effect47Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, myDeciderID_decider_17_0_2_edc0357_1352328158273_110644_20200 }));
                    Object effect48VarV = decider_17_0_2_edc0357_1352328158274_699982_20202;
                    effect48Var = new Parameter("effect48Var", null, null, this);
                    addDependency(effect48Var, new Expression(effect48VarV));
                    effect48 = new EffectFunction(new FunctionCall(effect48Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, myDeciderID_decider_17_0_2_edc0357_1352328158274_699982_20202 }));
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
                Set<Effect> effectsForeffect44Var = new TreeSet<Effect>();
                effectsForeffect44Var.add(effect44);
                addEffects((Parameter<?>) effect44Var, effectsForeffect44Var);
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
            }

            public void init_17_0_2_edc0357_1352328158274_877259_20201Dependencies() {
                addDependency(myDeciderID_decider_17_0_2_edc0357_1352328158274_699982_20202, new Expression<Integer>(2));
                addDependency(_17_0_2_edc0357_1352328158274_699982_20202_exists, new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158274_699982_20202, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158274_699982_20202, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "maxSize"), new Object[] {})))).functionCall), new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158274_699982_20202, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_edc0357_1352328158274_699982_20202))).functionCall))).functionCall), new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall))).functionCall));
                addDependency(myDeciderID_decider_17_0_2_edc0357_1352328158273_110644_20200, new Expression<Integer>(2));
                addDependency(_17_0_2_edc0357_1352328158273_329618_20199_exists, new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(_17_0_2_edc0357_1352328158273_110644_20200_exists, new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158273_110644_20200, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158273_110644_20200, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "maxSize"), new Object[] {})))).functionCall), new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158273_110644_20200, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_edc0357_1352328158273_110644_20200))).functionCall))).functionCall), new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall))).functionCall));
                addDependency(objectToPass, new Expression<Customer>(new FunctionCall(sig_17_0_2_edc0357_1352328158276_279334_20206, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_edc0357_1352328158274_877259_20201Elaborations() {
                init_17_0_2_edc0357_1352328158274_877259_20201Dependencies();
                Expression<?>[] arguments49 = new Expression<?>[1];
                arguments49[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition49 = new Expression<Boolean>(_17_0_2_edc0357_1352328158273_329618_20199_exists);
                elaborationRule49 = addElaborationRule(condition49, _17_0_2_edc0357_1352328156638_770509_19615.this, Customer._17_0_2_edc0357_1352328156638_770509_19615._17_0_2_edc0357_1352328158273_329618_20199.class, "rsfa_up_ReadStructuralFeatureAction_usePower", arguments49);
                Expression<?>[] arguments50 = new Expression<?>[1];
                arguments50[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition50 = new Expression<Boolean>(_17_0_2_edc0357_1352328158273_110644_20200_exists);
                elaborationRule50 = addElaborationRule(condition50, _17_0_2_edc0357_1352328156638_770509_19615.this, Customer._17_0_2_edc0357_1352328156638_770509_19615._17_0_2_edc0357_1352328158273_110644_20200.class, "clvsend_SendSignalAction_usePower", arguments50);
                Expression<?>[] arguments51 = new Expression<?>[1];
                arguments51[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition51 = new Expression<Boolean>(_17_0_2_edc0357_1352328158274_699982_20202_exists);
                elaborationRule51 = addElaborationRule(condition51, _17_0_2_edc0357_1352328156638_770509_19615.this, Customer._17_0_2_edc0357_1352328156638_770509_19615._17_0_2_edc0357_1352328158274_699982_20202.class, "rmr1_SendSignalAction_usePower", arguments51);
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

            public Dependency< Customer > _17_0_2_edc0357_1352328160537_353128_21389Dependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158275_65316_20204_existsDependency = null;

            public Dependency< Integer > _17_0_2_edc0357_1352328160537_779478_21390Dependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Integer > myDeciderID_decider_17_0_2_edc0357_1352328158275_65316_20204Dependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Effect effect52 = null;

            public Parameter effect52Var = null;

            public Effect effect53 = null;

            public Parameter effect53Var = null;

            public Effect effect54 = null;

            public Parameter effect54Var = null;

            public ElaborationRule elaborationRule55 = null;

            public void init_17_0_2_edc0357_1352328158274_699982_20202Members() {
                try {
                    if (_17_0_2_edc0357_1352328160537_353128_21389 == null) _17_0_2_edc0357_1352328160537_353128_21389 = new Parameter<Customer>("_17_0_2_edc0357_1352328160537_353128_21389", null, (Customer) null, this);
                    if (_17_0_2_edc0357_1352328158275_65316_20204_exists == null) _17_0_2_edc0357_1352328158275_65316_20204_exists = new BooleanParameter("_17_0_2_edc0357_1352328158275_65316_20204_exists", (Boolean) false, this);
                    if (_17_0_2_edc0357_1352328160537_779478_21390 == null) _17_0_2_edc0357_1352328160537_779478_21390 = new IntegerParameter("_17_0_2_edc0357_1352328160537_779478_21390", (Integer) null, this);
                    if (myDeciderID_decider_17_0_2_edc0357_1352328158275_65316_20204 == null) myDeciderID_decider_17_0_2_edc0357_1352328158275_65316_20204 = new IntegerParameter("myDeciderID_decider_17_0_2_edc0357_1352328158275_65316_20204", (Integer) 2, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect52VarV = sig_17_0_2_edc0357_1352328158277_963172_20215;
                    effect52Var = new Parameter("effect52Var", null, null, this);
                    addDependency(effect52Var, new Expression(effect52VarV));
                    effect52 = new EffectFunction(new FunctionCall(effect52Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect53VarV = decider_17_0_2_edc0357_1352328158275_65316_20204;
                    effect53Var = new Parameter("effect53Var", null, null, this);
                    addDependency(effect53Var, new Expression(effect53VarV));
                    effect53 = new EffectFunction(new FunctionCall(effect53Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, myDeciderID_decider_17_0_2_edc0357_1352328158275_65316_20204 }));
                    Object effect54VarV = new FunctionCall(x, Parameter.class, "getMember", new Object[] { "ss_17_0_2_edc0357_1352328156658_447229_19624_receiveMeterReading" });
                    effect54Var = new Parameter("effect54Var", null, null, this);
                    addDependency(effect54Var, new Expression(effect54VarV));
                    effect54 = new EffectFunction(new FunctionCall(null, ClassUtils.getMethodForArgTypes("ObjectFlow<SignalreceiveMeterReading>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { x.getValue().new SignalreceiveMeterReading(endTime, _17_0_2_edc0357_1352328160537_779478_21390.getValue()), endTime }, effect54Var));
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
                Set<Effect> effectsForeffect52Var = new TreeSet<Effect>();
                effectsForeffect52Var.add(effect52);
                addEffects((Parameter<?>) effect52Var, effectsForeffect52Var);
                Set<Effect> effectsForeffect53Var = new TreeSet<Effect>();
                effectsForeffect53Var.add(effect53);
                addEffects((Parameter<?>) effect53Var, effectsForeffect53Var);
                Set<Effect> effectsForeffect54Var = new TreeSet<Effect>();
                effectsForeffect54Var.add(effect54);
                addEffects((Parameter<?>) effect54Var, effectsForeffect54Var);
            }

            public void init_17_0_2_edc0357_1352328158274_699982_20202Dependencies() {
                addDependency(_17_0_2_edc0357_1352328160537_353128_21389, new Expression<Customer>(new FunctionCall(sig_17_0_2_edc0357_1352328158277_34448_20209, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_edc0357_1352328158275_65316_20204_exists, new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158275_65316_20204, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158275_65316_20204, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "maxSize"), new Object[] {})))).functionCall), new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158275_65316_20204, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_edc0357_1352328158275_65316_20204))).functionCall))).functionCall), new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall))).functionCall));
                addDependency(_17_0_2_edc0357_1352328160537_779478_21390, new Expression<Integer>(new FunctionCall(sig_17_0_2_edc0357_1352328158277_912374_20212, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(myDeciderID_decider_17_0_2_edc0357_1352328158275_65316_20204, new Expression<Integer>(2));
                addDependency(objectToPass, new Expression<Boolean>(true));
            }

            public void init_17_0_2_edc0357_1352328158274_699982_20202Elaborations() {
                init_17_0_2_edc0357_1352328158274_699982_20202Dependencies();
                Expression<?>[] arguments55 = new Expression<?>[1];
                arguments55[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition55 = new Expression<Boolean>(_17_0_2_edc0357_1352328158275_65316_20204_exists);
                elaborationRule55 = addElaborationRule(condition55, _17_0_2_edc0357_1352328156638_770509_19615.this, Customer._17_0_2_edc0357_1352328156638_770509_19615._17_0_2_edc0357_1352328158275_65316_20204.class, "fnn1_JoinNode_usePower", arguments55);
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

            public Effect effect56 = null;

            public Parameter effect56Var = null;

            public Effect effect57 = null;

            public Parameter effect57Var = null;

            public Effect effect58 = null;

            public Parameter effect58Var = null;

            public Effect effect59 = null;

            public Parameter effect59Var = null;

            public ElaborationRule elaborationRule60 = null;

            public ElaborationRule elaborationRule61 = null;

            public void init_17_0_2_edc0357_1352328158275_94410_20203Members() {
                try {
                    if (myDeciderID_decider_17_0_2_edc0357_1352328158274_699982_20202 == null) myDeciderID_decider_17_0_2_edc0357_1352328158274_699982_20202 = new IntegerParameter("myDeciderID_decider_17_0_2_edc0357_1352328158274_699982_20202", (Integer) 1, this);
                    if (_17_0_2_edc0357_1352328158274_699982_20202_exists == null) _17_0_2_edc0357_1352328158274_699982_20202_exists = new BooleanParameter("_17_0_2_edc0357_1352328158274_699982_20202_exists", (Boolean) false, this);
                    if (myDeciderID_decider_17_0_2_edc0357_1352328158273_110644_20200 == null) myDeciderID_decider_17_0_2_edc0357_1352328158273_110644_20200 = new IntegerParameter("myDeciderID_decider_17_0_2_edc0357_1352328158273_110644_20200", (Integer) 1, this);
                    if (_17_0_2_edc0357_1352328158273_110644_20200_exists == null) _17_0_2_edc0357_1352328158273_110644_20200_exists = new BooleanParameter("_17_0_2_edc0357_1352328158273_110644_20200_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new IntegerParameter("objectToPass", (Integer) null, this);
                    Object effect56VarV = sig_17_0_2_edc0357_1352328158277_962194_20211;
                    effect56Var = new Parameter("effect56Var", null, null, this);
                    addDependency(effect56Var, new Expression(effect56VarV));
                    effect56 = new EffectFunction(new FunctionCall(effect56Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect57VarV = sig_17_0_2_edc0357_1352328158277_912374_20212;
                    effect57Var = new Parameter("effect57Var", null, null, this);
                    addDependency(effect57Var, new Expression(effect57VarV));
                    effect57 = new EffectFunction(new FunctionCall(effect57Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect58VarV = decider_17_0_2_edc0357_1352328158273_110644_20200;
                    effect58Var = new Parameter("effect58Var", null, null, this);
                    addDependency(effect58Var, new Expression(effect58VarV));
                    effect58 = new EffectFunction(new FunctionCall(effect58Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, myDeciderID_decider_17_0_2_edc0357_1352328158273_110644_20200 }));
                    Object effect59VarV = decider_17_0_2_edc0357_1352328158274_699982_20202;
                    effect59Var = new Parameter("effect59Var", null, null, this);
                    addDependency(effect59Var, new Expression(effect59VarV));
                    effect59 = new EffectFunction(new FunctionCall(effect59Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, myDeciderID_decider_17_0_2_edc0357_1352328158274_699982_20202 }));
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
                Set<Effect> effectsForeffect56Var = new TreeSet<Effect>();
                effectsForeffect56Var.add(effect56);
                addEffects((Parameter<?>) effect56Var, effectsForeffect56Var);
                Set<Effect> effectsForeffect57Var = new TreeSet<Effect>();
                effectsForeffect57Var.add(effect57);
                addEffects((Parameter<?>) effect57Var, effectsForeffect57Var);
                Set<Effect> effectsForeffect58Var = new TreeSet<Effect>();
                effectsForeffect58Var.add(effect58);
                addEffects((Parameter<?>) effect58Var, effectsForeffect58Var);
                Set<Effect> effectsForeffect59Var = new TreeSet<Effect>();
                effectsForeffect59Var.add(effect59);
                addEffects((Parameter<?>) effect59Var, effectsForeffect59Var);
            }

            public void init_17_0_2_edc0357_1352328158275_94410_20203Dependencies() {
                addDependency(myDeciderID_decider_17_0_2_edc0357_1352328158274_699982_20202, new Expression<Integer>(1));
                addDependency(_17_0_2_edc0357_1352328158274_699982_20202_exists, new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158274_699982_20202, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158274_699982_20202, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "maxSize"), new Object[] {})))).functionCall), new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158274_699982_20202, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_edc0357_1352328158274_699982_20202))).functionCall))).functionCall), new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall))).functionCall));
                addDependency(myDeciderID_decider_17_0_2_edc0357_1352328158273_110644_20200, new Expression<Integer>(1));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(_17_0_2_edc0357_1352328158273_110644_20200_exists, new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158273_110644_20200, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158273_110644_20200, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "maxSize"), new Object[] {})))).functionCall), new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158273_110644_20200, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_edc0357_1352328158273_110644_20200))).functionCall))).functionCall), new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall))).functionCall));
                addDependency(objectToPass, new Expression<Integer>(new FunctionCall(sig_17_0_2_edc0357_1352328158277_819385_20210, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_edc0357_1352328158275_94410_20203Elaborations() {
                init_17_0_2_edc0357_1352328158275_94410_20203Dependencies();
                Expression<?>[] arguments60 = new Expression<?>[1];
                arguments60[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition60 = new Expression<Boolean>(_17_0_2_edc0357_1352328158273_110644_20200_exists);
                elaborationRule60 = addElaborationRule(condition60, _17_0_2_edc0357_1352328156638_770509_19615.this, Customer._17_0_2_edc0357_1352328156638_770509_19615._17_0_2_edc0357_1352328158273_110644_20200.class, "clvsend_SendSignalAction_usePower", arguments60);
                Expression<?>[] arguments61 = new Expression<?>[1];
                arguments61[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition61 = new Expression<Boolean>(_17_0_2_edc0357_1352328158274_699982_20202_exists);
                elaborationRule61 = addElaborationRule(condition61, _17_0_2_edc0357_1352328156638_770509_19615.this, Customer._17_0_2_edc0357_1352328156638_770509_19615._17_0_2_edc0357_1352328158274_699982_20202.class, "rmr1_SendSignalAction_usePower", arguments61);
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

            public Effect effect62 = null;

            public Parameter effect62Var = null;

            public void init_17_0_2_edc0357_1352328158275_65316_20204Members() {
                try {
                    if (objectToPass1 == null) objectToPass1 = new BooleanParameter("objectToPass1", (Boolean) null, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect62VarV = sig_17_0_2_edc0357_1352328158277_748946_20214;
                    effect62Var = new Parameter("effect62Var", null, null, this);
                    addDependency(effect62Var, new Expression(effect62VarV));
                    effect62 = new EffectFunction(new FunctionCall(effect62Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158275_65316_20204Collections() {
                parameters.add(objectToPass1);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect62Var = new TreeSet<Effect>();
                effectsForeffect62Var.add(effect62);
                addEffects((Parameter<?>) effect62Var, effectsForeffect62Var);
            }

            public void init_17_0_2_edc0357_1352328158275_65316_20204Dependencies() {
                addDependency(objectToPass1, new Expression<Boolean>(new FunctionCall(sig_17_0_2_edc0357_1352328158277_963172_20215, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(finalNode_startTime, new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new FunctionCall(sig_17_0_2_edc0357_1352328158277_686827_20213, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
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

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_edc0357_1352328158277_963172_20215 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_edc0357_1352328158277_686827_20213 = null;

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
                if (sig_17_0_2_edc0357_1352328158277_963172_20215 == null) sig_17_0_2_edc0357_1352328158277_963172_20215 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328158277_963172_20215", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158277_963172_20215" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328158277_686827_20213 == null) sig_17_0_2_edc0357_1352328158277_686827_20213 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328158277_686827_20213", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158277_686827_20213" })).evaluate(true), this);
                if (caller == null) caller = new Parameter<DurativeEvent>("caller", null, (DurativeEvent) null, this);
                if (sig_17_0_2_edc0357_1352328158277_34448_20209 == null) sig_17_0_2_edc0357_1352328158277_34448_20209 = new Parameter<ObjectFlow<Customer>>("sig_17_0_2_edc0357_1352328158277_34448_20209", null, (ObjectFlow<Customer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158277_34448_20209" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328158276_305344_20207 == null) sig_17_0_2_edc0357_1352328158276_305344_20207 = new Parameter<ObjectFlow<Customer>>("sig_17_0_2_edc0357_1352328158276_305344_20207", null, (ObjectFlow<Customer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158276_305344_20207" })).evaluate(true), this);
                if (_17_0_2_edc0357_1352328158273_555088_20198_exists == null) _17_0_2_edc0357_1352328158273_555088_20198_exists = new BooleanParameter("_17_0_2_edc0357_1352328158273_555088_20198_exists", (Boolean) false, this);
                if (decider_17_0_2_edc0357_1352328158273_110644_20200 == null) decider_17_0_2_edc0357_1352328158273_110644_20200 = new Parameter<TimeVaryingList<Integer>>("decider_17_0_2_edc0357_1352328158273_110644_20200", null, (TimeVaryingList<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingList.class, java.lang.String.class, int.class), new Object[] { "decider_17_0_2_edc0357_1352328158273_110644_20200", 2 })).evaluate(true), this);
                if (finalNode_startTime == null) finalNode_startTime = new IntegerParameter("finalNode_startTime", (Integer) 84000, this);
                if (decider_17_0_2_edc0357_1352328158275_65316_20204 == null) decider_17_0_2_edc0357_1352328158275_65316_20204 = new Parameter<TimeVaryingList<Integer>>("decider_17_0_2_edc0357_1352328158275_65316_20204", null, (TimeVaryingList<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingList.class, java.lang.String.class, int.class), new Object[] { "decider_17_0_2_edc0357_1352328158275_65316_20204", 2 })).evaluate(true), this);
                if (finalNode_endTime == null) finalNode_endTime = new IntegerParameter("finalNode_endTime", (Integer) 84000, this);
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
            parameters.add(sig_17_0_2_edc0357_1352328158277_963172_20215);
            parameters.add(sig_17_0_2_edc0357_1352328158277_686827_20213);
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
            addDependency(_17_0_2_edc0357_1352328158273_555088_20198_exists, new Expression<Boolean>(new FunctionCall(sig_17_0_2_edc0357_1352328158277_748946_20214, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>((new Functions.Minus(new Expression<Integer>(finalNode_startTime), new Expression<Integer>(1))).functionCall) })));
            addDependency(endTime, new Expression<Integer>(finalNode_endTime));
        }

        public void init_17_0_2_edc0357_1352328156638_770509_19615Elaborations() {
            init_17_0_2_edc0357_1352328156638_770509_19615Dependencies();
            Expression<?>[] arguments32 = new Expression<?>[1];
            arguments32[0] = new Expression<Integer>(startTime);
            Expression<Boolean> condition32 = new Expression<Boolean>(true);
            elaborationRule32 = addElaborationRule(condition32, _17_0_2_edc0357_1352328156638_770509_19615.this, Customer._17_0_2_edc0357_1352328156638_770509_19615._17_0_2_edc0357_1352328158272_257857_20196.class, "_InitialNode_usePower", arguments32);
            Expression<?>[] arguments33 = new Expression<?>[1];
            arguments33[0] = new Expression<Integer>(finalNode_startTime);
            Expression<Boolean> condition33 = new Expression<Boolean>(_17_0_2_edc0357_1352328158273_555088_20198_exists);
            elaborationRule33 = addElaborationRule(condition33, _17_0_2_edc0357_1352328156638_770509_19615.this, Customer._17_0_2_edc0357_1352328156638_770509_19615._17_0_2_edc0357_1352328158273_555088_20198.class, "_ActivityFinalNode_usePower", arguments33);
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

            public Effect effect65 = null;

            public Parameter effect65Var = null;

            public ElaborationRule elaborationRule66 = null;

            public void init_17_0_2_edc0357_1352328158425_536558_20249Members() {
                try {
                    if (_17_0_2_edc0357_1352328158426_178002_20255_exists == null) _17_0_2_edc0357_1352328158426_178002_20255_exists = new BooleanParameter("_17_0_2_edc0357_1352328158426_178002_20255_exists", (Boolean) false, this);
                    if (_17_0_2_edc0357_1352328160547_987385_21408 == null) _17_0_2_edc0357_1352328160547_987385_21408 = new Parameter<Customer>("_17_0_2_edc0357_1352328160547_987385_21408", null, (Customer) Customer.this, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect65VarV = sig_17_0_2_edc0357_1352328158429_502495_20265;
                    effect65Var = new Parameter("effect65Var", null, null, this);
                    addDependency(effect65Var, new Expression(effect65VarV));
                    effect65 = new EffectFunction(new FunctionCall(effect65Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { _17_0_2_edc0357_1352328160547_987385_21408, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158425_536558_20249Collections() {
                parameters.add(_17_0_2_edc0357_1352328158426_178002_20255_exists);
                parameters.add(_17_0_2_edc0357_1352328160547_987385_21408);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect65Var = new TreeSet<Effect>();
                effectsForeffect65Var.add(effect65);
                addEffects((Parameter<?>) effect65Var, effectsForeffect65Var);
            }

            public void init_17_0_2_edc0357_1352328158425_536558_20249Dependencies() {
                addDependency(_17_0_2_edc0357_1352328158426_178002_20255_exists, new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new FunctionCall(sig_17_0_2_edc0357_1352328158430_913443_20274, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_edc0357_1352328158425_536558_20249Elaborations() {
                init_17_0_2_edc0357_1352328158425_536558_20249Dependencies();
                Expression<?>[] arguments66 = new Expression<?>[1];
                arguments66[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition66 = new Expression<Boolean>(_17_0_2_edc0357_1352328158426_178002_20255_exists);
                elaborationRule66 = addElaborationRule(condition66, _17_0_2_edc0357_1352328156639_660827_19616.this, Customer._17_0_2_edc0357_1352328156639_660827_19616._17_0_2_edc0357_1352328158426_178002_20255.class, "_ForkNode_changePowerUsage", arguments66);
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

            public Effect effect67 = null;

            public Parameter effect67Var = null;

            public ElaborationRule elaborationRule68 = null;

            public void init_17_0_2_edc0357_1352328158425_562603_20250Members() {
                try {
                    if (_17_0_2_edc0357_1352328158428_316417_20263_exists == null) _17_0_2_edc0357_1352328158428_316417_20263_exists = new BooleanParameter("_17_0_2_edc0357_1352328158428_316417_20263_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (_17_0_2_edc0357_1352328160548_131046_21410 == null) _17_0_2_edc0357_1352328160548_131046_21410 = new IntegerParameter("_17_0_2_edc0357_1352328160548_131046_21410", (Integer) 15, this);
                    Object effect67VarV = sig_17_0_2_edc0357_1352328158431_275687_20282;
                    effect67Var = new Parameter("effect67Var", null, null, this);
                    addDependency(effect67Var, new Expression(effect67VarV));
                    effect67 = new EffectFunction(new FunctionCall(effect67Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { _17_0_2_edc0357_1352328160548_131046_21410, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158425_562603_20250Collections() {
                parameters.add(_17_0_2_edc0357_1352328158428_316417_20263_exists);
                parameters.add(objectToPass);
                parameters.add(_17_0_2_edc0357_1352328160548_131046_21410);
                Set<Effect> effectsForeffect67Var = new TreeSet<Effect>();
                effectsForeffect67Var.add(effect67);
                addEffects((Parameter<?>) effect67Var, effectsForeffect67Var);
            }

            public void init_17_0_2_edc0357_1352328158425_562603_20250Dependencies() {
                addDependency(_17_0_2_edc0357_1352328158428_316417_20263_exists, new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new FunctionCall(sig_17_0_2_edc0357_1352328158430_934674_20275, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_edc0357_1352328160548_131046_21410, new Expression<Integer>(15));
            }

            public void init_17_0_2_edc0357_1352328158425_562603_20250Elaborations() {
                init_17_0_2_edc0357_1352328158425_562603_20250Dependencies();
                Expression<?>[] arguments68 = new Expression<?>[1];
                arguments68[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition68 = new Expression<Boolean>(_17_0_2_edc0357_1352328158428_316417_20263_exists);
                elaborationRule68 = addElaborationRule(condition68, _17_0_2_edc0357_1352328156639_660827_19616.this, Customer._17_0_2_edc0357_1352328156639_660827_19616._17_0_2_edc0357_1352328158428_316417_20263.class, "_ForkNode_changePowerUsage", arguments68);
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

            public Effect effect69 = null;

            public Parameter effect69Var = null;

            public Effect effect70 = null;

            public Parameter effect70Var = null;

            public Effect effect71 = null;

            public Parameter effect71Var = null;

            public ElaborationRule elaborationRule72 = null;

            public void init_17_0_2_edc0357_1352328158425_844513_20251Members() {
                try {
                    if (_17_0_2_edc0357_1352328160549_391021_21411 == null) _17_0_2_edc0357_1352328160549_391021_21411 = new IntegerParameter("_17_0_2_edc0357_1352328160549_391021_21411", (Integer) null, this);
                    if (_17_0_2_edc0357_1352328158426_116757_20257_exists == null) _17_0_2_edc0357_1352328158426_116757_20257_exists = new BooleanParameter("_17_0_2_edc0357_1352328158426_116757_20257_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (myDeciderID_decider_17_0_2_edc0357_1352328158426_116757_20257 == null) myDeciderID_decider_17_0_2_edc0357_1352328158426_116757_20257 = new IntegerParameter("myDeciderID_decider_17_0_2_edc0357_1352328158426_116757_20257", (Integer) 3, this);
                    if (_17_0_2_edc0357_1352328160550_581175_21412 == null) _17_0_2_edc0357_1352328160550_581175_21412 = new Parameter<Customer>("_17_0_2_edc0357_1352328160550_581175_21412", null, (Customer) null, this);
                    Object effect69VarV = sig_17_0_2_edc0357_1352328158430_24745_20272;
                    effect69Var = new Parameter("effect69Var", null, null, this);
                    addDependency(effect69Var, new Expression(effect69VarV));
                    effect69 = new EffectFunction(new FunctionCall(effect69Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect70VarV = decider_17_0_2_edc0357_1352328158426_116757_20257;
                    effect70Var = new Parameter("effect70Var", null, null, this);
                    addDependency(effect70Var, new Expression(effect70VarV));
                    effect70 = new EffectFunction(new FunctionCall(effect70Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, myDeciderID_decider_17_0_2_edc0357_1352328158426_116757_20257 }));
                    Object effect71VarV = usage__17_0_2_edc0357_1352328156649_216766_19620;
                    effect71Var = new Parameter("effect71Var", null, null, this);
                    addDependency(effect71Var, new Expression(effect71VarV));
                    effect71 = new EffectFunction(new FunctionCall(effect71Var, ClassUtils.getMethodForArgTypes("TimeVaryingMap<Integer>", "powersystem", "setValue", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { startTime, _17_0_2_edc0357_1352328160549_391021_21411 }));
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

            public void init_17_0_2_edc0357_1352328158425_844513_20251Dependencies() {
                addDependency(_17_0_2_edc0357_1352328160549_391021_21411, new Expression<Integer>(new FunctionCall(sig_17_0_2_edc0357_1352328158429_532173_20270, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_edc0357_1352328158426_116757_20257_exists, new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158426_116757_20257, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158426_116757_20257, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "maxSize"), new Object[] {})))).functionCall), new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158426_116757_20257, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_edc0357_1352328158426_116757_20257))).functionCall))).functionCall), new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall))).functionCall));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(true));
                addDependency(myDeciderID_decider_17_0_2_edc0357_1352328158426_116757_20257, new Expression<Integer>(3));
                addDependency(_17_0_2_edc0357_1352328160550_581175_21412, new Expression<Customer>(new FunctionCall(sig_17_0_2_edc0357_1352328158429_694477_20266, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_edc0357_1352328158425_844513_20251Elaborations() {
                init_17_0_2_edc0357_1352328158425_844513_20251Dependencies();
                Expression<?>[] arguments72 = new Expression<?>[1];
                arguments72[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition72 = new Expression<Boolean>(_17_0_2_edc0357_1352328158426_116757_20257_exists);
                elaborationRule72 = addElaborationRule(condition72, _17_0_2_edc0357_1352328156639_660827_19616.this, Customer._17_0_2_edc0357_1352328156639_660827_19616._17_0_2_edc0357_1352328158426_116757_20257.class, "_SendSignalAction_changePowerUsage", arguments72);
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

            public Effect effect73 = null;

            public Parameter effect73Var = null;

            public ElaborationRule elaborationRule74 = null;

            public void init_17_0_2_edc0357_1352328158425_23694_20252Members() {
                try {
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (_17_0_2_edc0357_1352328158427_71576_20259_exists == null) _17_0_2_edc0357_1352328158427_71576_20259_exists = new BooleanParameter("_17_0_2_edc0357_1352328158427_71576_20259_exists", (Boolean) false, this);
                    Object effect73VarV = sig_17_0_2_edc0357_1352328158430_229342_20273;
                    effect73Var = new Parameter("effect73Var", null, null, this);
                    addDependency(effect73Var, new Expression(effect73VarV));
                    effect73 = new EffectFunction(new FunctionCall(effect73Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158425_23694_20252Collections() {
                parameters.add(objectToPass);
                parameters.add(_17_0_2_edc0357_1352328158427_71576_20259_exists);
                Set<Effect> effectsForeffect73Var = new TreeSet<Effect>();
                effectsForeffect73Var.add(effect73);
                addEffects((Parameter<?>) effect73Var, effectsForeffect73Var);
            }

            public void init_17_0_2_edc0357_1352328158425_23694_20252Dependencies() {
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(true));
                addDependency(_17_0_2_edc0357_1352328158427_71576_20259_exists, new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall));
            }

            public void init_17_0_2_edc0357_1352328158425_23694_20252Elaborations() {
                init_17_0_2_edc0357_1352328158425_23694_20252Dependencies();
                Expression<?>[] arguments74 = new Expression<?>[1];
                arguments74[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition74 = new Expression<Boolean>(_17_0_2_edc0357_1352328158427_71576_20259_exists);
                elaborationRule74 = addElaborationRule(condition74, _17_0_2_edc0357_1352328156639_660827_19616.this, Customer._17_0_2_edc0357_1352328156639_660827_19616._17_0_2_edc0357_1352328158427_71576_20259.class, "_ForkNode_changePowerUsage", arguments74);
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
                addDependency(endTime, new Expression<Integer>((new Functions.Plus(new Expression<Integer>(startTime), new Expression<Integer>(duration))).functionCall));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new FunctionCall(sig_17_0_2_edc0357_1352328158429_418011_20269, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
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

            public Effect effect75 = null;

            public Parameter effect75Var = null;

            public Effect effect76 = null;

            public Parameter effect76Var = null;

            public Effect effect77 = null;

            public Parameter effect77Var = null;

            public ElaborationRule elaborationRule78 = null;

            public ElaborationRule elaborationRule79 = null;

            public void init_17_0_2_edc0357_1352328158425_220398_20254Members() {
                try {
                    if (_17_0_2_edc0357_1352328158428_38684_20262_exists == null) _17_0_2_edc0357_1352328158428_38684_20262_exists = new BooleanParameter("_17_0_2_edc0357_1352328158428_38684_20262_exists", (Boolean) false, this);
                    if (myDeciderID_decider_17_0_2_edc0357_1352328158428_38684_20262 == null) myDeciderID_decider_17_0_2_edc0357_1352328158428_38684_20262 = new IntegerParameter("myDeciderID_decider_17_0_2_edc0357_1352328158428_38684_20262", (Integer) 1, this);
                    if (_17_0_2_edc0357_1352328160551_311379_21414 == null) _17_0_2_edc0357_1352328160551_311379_21414 = new Parameter<Customer>("_17_0_2_edc0357_1352328160551_311379_21414", null, (Customer) null, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (_17_0_2_edc0357_1352328158428_668812_20261_exists == null) _17_0_2_edc0357_1352328158428_668812_20261_exists = new BooleanParameter("_17_0_2_edc0357_1352328158428_668812_20261_exists", (Boolean) false, this);
                    if (_17_0_2_edc0357_1352328160550_744442_21413 == null) _17_0_2_edc0357_1352328160550_744442_21413 = new IntegerParameter("_17_0_2_edc0357_1352328160550_744442_21413", (Integer) null, this);
                    Object effect75VarV = sig_17_0_2_edc0357_1352328158430_538124_20277;
                    effect75Var = new Parameter("effect75Var", null, null, this);
                    addDependency(effect75Var, new Expression(effect75VarV));
                    effect75 = new EffectFunction(new FunctionCall(effect75Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { _17_0_2_edc0357_1352328160550_744442_21413, endTime }));
                    Object effect76VarV = sig_17_0_2_edc0357_1352328158430_630436_20276;
                    effect76Var = new Parameter("effect76Var", null, null, this);
                    addDependency(effect76Var, new Expression(effect76VarV));
                    effect76 = new EffectFunction(new FunctionCall(effect76Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect77VarV = decider_17_0_2_edc0357_1352328158428_38684_20262;
                    effect77Var = new Parameter("effect77Var", null, null, this);
                    addDependency(effect77Var, new Expression(effect77VarV));
                    effect77 = new EffectFunction(new FunctionCall(effect77Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, myDeciderID_decider_17_0_2_edc0357_1352328158428_38684_20262 }));
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
                Set<Effect> effectsForeffect75Var = new TreeSet<Effect>();
                effectsForeffect75Var.add(effect75);
                addEffects((Parameter<?>) effect75Var, effectsForeffect75Var);
                Set<Effect> effectsForeffect76Var = new TreeSet<Effect>();
                effectsForeffect76Var.add(effect76);
                addEffects((Parameter<?>) effect76Var, effectsForeffect76Var);
                Set<Effect> effectsForeffect77Var = new TreeSet<Effect>();
                effectsForeffect77Var.add(effect77);
                addEffects((Parameter<?>) effect77Var, effectsForeffect77Var);
            }

            public void init_17_0_2_edc0357_1352328158425_220398_20254Dependencies() {
                addDependency(_17_0_2_edc0357_1352328158428_38684_20262_exists, new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158428_38684_20262, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158428_38684_20262, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "maxSize"), new Object[] {})))).functionCall), new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158428_38684_20262, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_edc0357_1352328158428_38684_20262))).functionCall))).functionCall), new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall))).functionCall));
                addDependency(myDeciderID_decider_17_0_2_edc0357_1352328158428_38684_20262, new Expression<Integer>(1));
                addDependency(_17_0_2_edc0357_1352328160551_311379_21414, new Expression<Customer>(new FunctionCall(sig_17_0_2_edc0357_1352328158429_395232_20267, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(true));
                addDependency(_17_0_2_edc0357_1352328158428_668812_20261_exists, new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall));
                addDependency(_17_0_2_edc0357_1352328160550_744442_21413, new Expression<Integer>(new FunctionCall(null, ClassUtils.getMethodForArgTypes("TimeVaryingMap<Integer>", "powersystem", "getValue", java.lang.Integer.class), new Object[] { new Expression<Integer>(startTime) }, new FunctionCall(_17_0_2_edc0357_1352328160551_311379_21414, Parameter.class, "getMember", new Object[] { "cap__17_0_2_edc0357_1352328156659_30024_19625" }))));
            }

            public void init_17_0_2_edc0357_1352328158425_220398_20254Elaborations() {
                init_17_0_2_edc0357_1352328158425_220398_20254Dependencies();
                Expression<?>[] arguments78 = new Expression<?>[1];
                arguments78[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition78 = new Expression<Boolean>(_17_0_2_edc0357_1352328158428_668812_20261_exists);
                elaborationRule78 = addElaborationRule(condition78, _17_0_2_edc0357_1352328156639_660827_19616.this, Customer._17_0_2_edc0357_1352328156639_660827_19616._17_0_2_edc0357_1352328158428_668812_20261.class, "_ForkNode_changePowerUsage", arguments78);
                Expression<?>[] arguments79 = new Expression<?>[1];
                arguments79[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition79 = new Expression<Boolean>(_17_0_2_edc0357_1352328158428_38684_20262_exists);
                elaborationRule79 = addElaborationRule(condition79, _17_0_2_edc0357_1352328156639_660827_19616.this, Customer._17_0_2_edc0357_1352328156639_660827_19616._17_0_2_edc0357_1352328158428_38684_20262.class, "_DecisionNode_changePowerUsage", arguments79);
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

            public Effect effect80 = null;

            public Parameter effect80Var = null;

            public Effect effect81 = null;

            public Parameter effect81Var = null;

            public Effect effect82 = null;

            public Parameter effect82Var = null;

            public Effect effect83 = null;

            public Parameter effect83Var = null;

            public Effect effect84 = null;

            public Parameter effect84Var = null;

            public ElaborationRule elaborationRule85 = null;

            public ElaborationRule elaborationRule86 = null;

            public ElaborationRule elaborationRule87 = null;

            public void init_17_0_2_edc0357_1352328158426_178002_20255Members() {
                try {
                    if (myDeciderID_decider_17_0_2_edc0357_1352328158425_844513_20251 == null) myDeciderID_decider_17_0_2_edc0357_1352328158425_844513_20251 = new IntegerParameter("myDeciderID_decider_17_0_2_edc0357_1352328158425_844513_20251", (Integer) 1, this);
                    if (_17_0_2_edc0357_1352328158426_116757_20257_exists == null) _17_0_2_edc0357_1352328158426_116757_20257_exists = new BooleanParameter("_17_0_2_edc0357_1352328158426_116757_20257_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new Parameter<Customer>("objectToPass", null, (Customer) null, this);
                    if (myDeciderID_decider_17_0_2_edc0357_1352328158426_116757_20257 == null) myDeciderID_decider_17_0_2_edc0357_1352328158426_116757_20257 = new IntegerParameter("myDeciderID_decider_17_0_2_edc0357_1352328158426_116757_20257", (Integer) 2, this);
                    if (_17_0_2_edc0357_1352328158425_844513_20251_exists == null) _17_0_2_edc0357_1352328158425_844513_20251_exists = new BooleanParameter("_17_0_2_edc0357_1352328158425_844513_20251_exists", (Boolean) false, this);
                    if (_17_0_2_edc0357_1352328158425_220398_20254_exists == null) _17_0_2_edc0357_1352328158425_220398_20254_exists = new BooleanParameter("_17_0_2_edc0357_1352328158425_220398_20254_exists", (Boolean) false, this);
                    Object effect80VarV = sig_17_0_2_edc0357_1352328158429_694477_20266;
                    effect80Var = new Parameter("effect80Var", null, null, this);
                    addDependency(effect80Var, new Expression(effect80VarV));
                    effect80 = new EffectFunction(new FunctionCall(effect80Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect81VarV = sig_17_0_2_edc0357_1352328158429_395232_20267;
                    effect81Var = new Parameter("effect81Var", null, null, this);
                    addDependency(effect81Var, new Expression(effect81VarV));
                    effect81 = new EffectFunction(new FunctionCall(effect81Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect82VarV = sig_17_0_2_edc0357_1352328158429_197012_20268;
                    effect82Var = new Parameter("effect82Var", null, null, this);
                    addDependency(effect82Var, new Expression(effect82VarV));
                    effect82 = new EffectFunction(new FunctionCall(effect82Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect83VarV = decider_17_0_2_edc0357_1352328158426_116757_20257;
                    effect83Var = new Parameter("effect83Var", null, null, this);
                    addDependency(effect83Var, new Expression(effect83VarV));
                    effect83 = new EffectFunction(new FunctionCall(effect83Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, myDeciderID_decider_17_0_2_edc0357_1352328158426_116757_20257 }));
                    Object effect84VarV = decider_17_0_2_edc0357_1352328158425_844513_20251;
                    effect84Var = new Parameter("effect84Var", null, null, this);
                    addDependency(effect84Var, new Expression(effect84VarV));
                    effect84 = new EffectFunction(new FunctionCall(effect84Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, myDeciderID_decider_17_0_2_edc0357_1352328158425_844513_20251 }));
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
                Set<Effect> effectsForeffect80Var = new TreeSet<Effect>();
                effectsForeffect80Var.add(effect80);
                addEffects((Parameter<?>) effect80Var, effectsForeffect80Var);
                Set<Effect> effectsForeffect81Var = new TreeSet<Effect>();
                effectsForeffect81Var.add(effect81);
                addEffects((Parameter<?>) effect81Var, effectsForeffect81Var);
                Set<Effect> effectsForeffect82Var = new TreeSet<Effect>();
                effectsForeffect82Var.add(effect82);
                addEffects((Parameter<?>) effect82Var, effectsForeffect82Var);
                Set<Effect> effectsForeffect83Var = new TreeSet<Effect>();
                effectsForeffect83Var.add(effect83);
                addEffects((Parameter<?>) effect83Var, effectsForeffect83Var);
                Set<Effect> effectsForeffect84Var = new TreeSet<Effect>();
                effectsForeffect84Var.add(effect84);
                addEffects((Parameter<?>) effect84Var, effectsForeffect84Var);
            }

            public void init_17_0_2_edc0357_1352328158426_178002_20255Dependencies() {
                addDependency(myDeciderID_decider_17_0_2_edc0357_1352328158425_844513_20251, new Expression<Integer>(1));
                addDependency(_17_0_2_edc0357_1352328158426_116757_20257_exists, new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158426_116757_20257, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158426_116757_20257, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "maxSize"), new Object[] {})))).functionCall), new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158426_116757_20257, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_edc0357_1352328158426_116757_20257))).functionCall))).functionCall), new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall))).functionCall));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Customer>(new FunctionCall(sig_17_0_2_edc0357_1352328158429_502495_20265, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(myDeciderID_decider_17_0_2_edc0357_1352328158426_116757_20257, new Expression<Integer>(2));
                addDependency(_17_0_2_edc0357_1352328158425_844513_20251_exists, new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158425_844513_20251, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158425_844513_20251, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "maxSize"), new Object[] {})))).functionCall), new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158425_844513_20251, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_edc0357_1352328158425_844513_20251))).functionCall))).functionCall), new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall))).functionCall));
                addDependency(_17_0_2_edc0357_1352328158425_220398_20254_exists, new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall));
            }

            public void init_17_0_2_edc0357_1352328158426_178002_20255Elaborations() {
                init_17_0_2_edc0357_1352328158426_178002_20255Dependencies();
                Expression<?>[] arguments85 = new Expression<?>[1];
                arguments85[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition85 = new Expression<Boolean>(_17_0_2_edc0357_1352328158426_116757_20257_exists);
                elaborationRule85 = addElaborationRule(condition85, _17_0_2_edc0357_1352328156639_660827_19616.this, Customer._17_0_2_edc0357_1352328156639_660827_19616._17_0_2_edc0357_1352328158426_116757_20257.class, "_SendSignalAction_changePowerUsage", arguments85);
                Expression<?>[] arguments86 = new Expression<?>[1];
                arguments86[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition86 = new Expression<Boolean>(_17_0_2_edc0357_1352328158425_220398_20254_exists);
                elaborationRule86 = addElaborationRule(condition86, _17_0_2_edc0357_1352328156639_660827_19616.this, Customer._17_0_2_edc0357_1352328156639_660827_19616._17_0_2_edc0357_1352328158425_220398_20254.class, "_ReadStructuralFeatureAction_changePowerUsage", arguments86);
                Expression<?>[] arguments87 = new Expression<?>[1];
                arguments87[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition87 = new Expression<Boolean>(_17_0_2_edc0357_1352328158425_844513_20251_exists);
                elaborationRule87 = addElaborationRule(condition87, _17_0_2_edc0357_1352328156639_660827_19616.this, Customer._17_0_2_edc0357_1352328156639_660827_19616._17_0_2_edc0357_1352328158425_844513_20251.class, "_AddStructuralFeatureValueAction_changePowerUsage", arguments87);
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

            public Effect effect88 = null;

            public Parameter effect88Var = null;

            public ElaborationRule elaborationRule89 = null;

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
                    Object effect88VarV = sig_17_0_2_edc0357_1352328158431_565981_20286;
                    effect88Var = new Parameter("effect88Var", null, null, this);
                    addDependency(effect88Var, new Expression(effect88VarV));
                    effect88 = new EffectFunction(new FunctionCall(effect88Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { _17_0_2_edc0357_1352328160551_57517_21415, endTime }));
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
                Set<Effect> effectsForeffect88Var = new TreeSet<Effect>();
                effectsForeffect88Var.add(effect88);
                addEffects((Parameter<?>) effect88Var, effectsForeffect88Var);
            }

            public void init_17_0_2_edc0357_1352328158426_903101_20256Dependencies() {
                addDependency(_17_0_2_edc0357_1352328160553_159569_21417, new Expression<Integer>(new FunctionCall(sig_17_0_2_edc0357_1352328158430_389607_20279, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(new_load, new Expression<Integer>(new FunctionCall(null, ClassUtils.getMethodForArgTypes("java.lang.Math", "powersystem", "min", int.class, int.class), new Object[] { new Expression<Integer>(desired), new Expression<Integer>(cap) })));
                addDependency(_17_0_2_edc0357_1352328158428_314626_20264_exists, new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall));
                addDependency(cap, new Expression<Integer>(_17_0_2_edc0357_1352328160553_159569_21417));
                addDependency(desired, new Expression<Integer>(_17_0_2_edc0357_1352328160552_993697_21416));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new FunctionCall(sig_17_0_2_edc0357_1352328158431_806383_20280, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_edc0357_1352328160552_993697_21416, new Expression<Integer>(new FunctionCall(sig_17_0_2_edc0357_1352328158431_868302_20283, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_edc0357_1352328160551_57517_21415, new Expression<Integer>(new_load));
            }

            public void init_17_0_2_edc0357_1352328158426_903101_20256Elaborations() {
                init_17_0_2_edc0357_1352328158426_903101_20256Dependencies();
                Expression<?>[] arguments89 = new Expression<?>[2];
                arguments89[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                arguments89[1] = new Expression<ObjectFlow<Integer>>(sig_17_0_2_edc0357_1352328158431_565981_20286);
                Expression<Boolean> condition89 = new Expression<Boolean>(_17_0_2_edc0357_1352328158428_314626_20264_exists);
                elaborationRule89 = addElaborationRule(condition89, _17_0_2_edc0357_1352328156639_660827_19616.this, Customer._17_0_2_edc0357_1352328156639_660827_19616._17_0_2_edc0357_1352328158428_314626_20264.class, "mergeynode_MergeNode_changePowerUsage", arguments89);
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

            public Dependency< Integer > _17_0_2_edc0357_1352328160554_929788_21419Dependency = null;

            public Dependency< Customer > _17_0_2_edc0357_1352328160553_902427_21418Dependency = null;

            public Dependency< Integer > finalNode_startTimeDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Effect effect90 = null;

            public Parameter effect90Var = null;

            public Effect effect91 = null;

            public Parameter effect91Var = null;

            public void init_17_0_2_edc0357_1352328158426_116757_20257Members() {
                try {
                    if (_17_0_2_edc0357_1352328160554_929788_21419 == null) _17_0_2_edc0357_1352328160554_929788_21419 = new IntegerParameter("_17_0_2_edc0357_1352328160554_929788_21419", (Integer) null, this);
                    if (_17_0_2_edc0357_1352328160553_902427_21418 == null) _17_0_2_edc0357_1352328160553_902427_21418 = new Parameter<Customer>("_17_0_2_edc0357_1352328160553_902427_21418", null, (Customer) null, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect90VarV = sig_17_0_2_edc0357_1352328158429_418011_20269;
                    effect90Var = new Parameter("effect90Var", null, null, this);
                    addDependency(effect90Var, new Expression(effect90VarV));
                    effect90 = new EffectFunction(new FunctionCall(effect90Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect91VarV = new FunctionCall(x, Parameter.class, "getMember", new Object[] { "ss_17_0_2_edc0357_1352328156650_180051_19621_changeLoadValue" });
                    effect91Var = new Parameter("effect91Var", null, null, this);
                    addDependency(effect91Var, new Expression(effect91VarV));
                    effect91 = new EffectFunction(new FunctionCall(null, ClassUtils.getMethodForArgTypes("ObjectFlow<SignalchangeLoadValue>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { x.getValue().new SignalchangeLoadValue(endTime, _17_0_2_edc0357_1352328160554_929788_21419.getValue()), endTime }, effect91Var));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158426_116757_20257Collections() {
                parameters.add(_17_0_2_edc0357_1352328160554_929788_21419);
                parameters.add(_17_0_2_edc0357_1352328160553_902427_21418);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect90Var = new TreeSet<Effect>();
                effectsForeffect90Var.add(effect90);
                addEffects((Parameter<?>) effect90Var, effectsForeffect90Var);
                Set<Effect> effectsForeffect91Var = new TreeSet<Effect>();
                effectsForeffect91Var.add(effect91);
                addEffects((Parameter<?>) effect91Var, effectsForeffect91Var);
            }

            public void init_17_0_2_edc0357_1352328158426_116757_20257Dependencies() {
                addDependency(_17_0_2_edc0357_1352328160554_929788_21419, new Expression<Integer>(new FunctionCall(sig_17_0_2_edc0357_1352328158430_385741_20271, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_edc0357_1352328160553_902427_21418, new Expression<Customer>(new FunctionCall(sig_17_0_2_edc0357_1352328158429_197012_20268, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(finalNode_startTime, new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new FunctionCall(sig_17_0_2_edc0357_1352328158430_24745_20272, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
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

            public Effect effect92 = null;

            public Parameter effect92Var = null;

            public Effect effect93 = null;

            public Parameter effect93Var = null;

            public Effect effect94 = null;

            public Parameter effect94Var = null;

            public Effect effect95 = null;

            public Parameter effect95Var = null;

            public ElaborationRule elaborationRule96 = null;

            public ElaborationRule elaborationRule97 = null;

            public void init_17_0_2_edc0357_1352328158427_233123_20258Members() {
                try {
                    if (myDeciderID_decider_17_0_2_edc0357_1352328158425_844513_20251 == null) myDeciderID_decider_17_0_2_edc0357_1352328158425_844513_20251 = new IntegerParameter("myDeciderID_decider_17_0_2_edc0357_1352328158425_844513_20251", (Integer) 2, this);
                    if (_17_0_2_edc0357_1352328158426_116757_20257_exists == null) _17_0_2_edc0357_1352328158426_116757_20257_exists = new BooleanParameter("_17_0_2_edc0357_1352328158426_116757_20257_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new IntegerParameter("objectToPass", (Integer) null, this);
                    if (myDeciderID_decider_17_0_2_edc0357_1352328158426_116757_20257 == null) myDeciderID_decider_17_0_2_edc0357_1352328158426_116757_20257 = new IntegerParameter("myDeciderID_decider_17_0_2_edc0357_1352328158426_116757_20257", (Integer) 1, this);
                    if (_17_0_2_edc0357_1352328158425_844513_20251_exists == null) _17_0_2_edc0357_1352328158425_844513_20251_exists = new BooleanParameter("_17_0_2_edc0357_1352328158425_844513_20251_exists", (Boolean) false, this);
                    Object effect92VarV = sig_17_0_2_edc0357_1352328158429_532173_20270;
                    effect92Var = new Parameter("effect92Var", null, null, this);
                    addDependency(effect92Var, new Expression(effect92VarV));
                    effect92 = new EffectFunction(new FunctionCall(effect92Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect93VarV = sig_17_0_2_edc0357_1352328158430_385741_20271;
                    effect93Var = new Parameter("effect93Var", null, null, this);
                    addDependency(effect93Var, new Expression(effect93VarV));
                    effect93 = new EffectFunction(new FunctionCall(effect93Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect94VarV = decider_17_0_2_edc0357_1352328158426_116757_20257;
                    effect94Var = new Parameter("effect94Var", null, null, this);
                    addDependency(effect94Var, new Expression(effect94VarV));
                    effect94 = new EffectFunction(new FunctionCall(effect94Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, myDeciderID_decider_17_0_2_edc0357_1352328158426_116757_20257 }));
                    Object effect95VarV = decider_17_0_2_edc0357_1352328158425_844513_20251;
                    effect95Var = new Parameter("effect95Var", null, null, this);
                    addDependency(effect95Var, new Expression(effect95VarV));
                    effect95 = new EffectFunction(new FunctionCall(effect95Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, myDeciderID_decider_17_0_2_edc0357_1352328158425_844513_20251 }));
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
                Set<Effect> effectsForeffect92Var = new TreeSet<Effect>();
                effectsForeffect92Var.add(effect92);
                addEffects((Parameter<?>) effect92Var, effectsForeffect92Var);
                Set<Effect> effectsForeffect93Var = new TreeSet<Effect>();
                effectsForeffect93Var.add(effect93);
                addEffects((Parameter<?>) effect93Var, effectsForeffect93Var);
                Set<Effect> effectsForeffect94Var = new TreeSet<Effect>();
                effectsForeffect94Var.add(effect94);
                addEffects((Parameter<?>) effect94Var, effectsForeffect94Var);
                Set<Effect> effectsForeffect95Var = new TreeSet<Effect>();
                effectsForeffect95Var.add(effect95);
                addEffects((Parameter<?>) effect95Var, effectsForeffect95Var);
            }

            public void init_17_0_2_edc0357_1352328158427_233123_20258Dependencies() {
                addDependency(myDeciderID_decider_17_0_2_edc0357_1352328158425_844513_20251, new Expression<Integer>(2));
                addDependency(_17_0_2_edc0357_1352328158426_116757_20257_exists, new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158426_116757_20257, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158426_116757_20257, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "maxSize"), new Object[] {})))).functionCall), new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158426_116757_20257, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_edc0357_1352328158426_116757_20257))).functionCall))).functionCall), new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall))).functionCall));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Integer>(new FunctionCall(sig_17_0_2_edc0357_1352328158431_643907_20287, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(myDeciderID_decider_17_0_2_edc0357_1352328158426_116757_20257, new Expression<Integer>(1));
                addDependency(_17_0_2_edc0357_1352328158425_844513_20251_exists, new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158425_844513_20251, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158425_844513_20251, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "maxSize"), new Object[] {})))).functionCall), new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158425_844513_20251, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_edc0357_1352328158425_844513_20251))).functionCall))).functionCall), new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall))).functionCall));
            }

            public void init_17_0_2_edc0357_1352328158427_233123_20258Elaborations() {
                init_17_0_2_edc0357_1352328158427_233123_20258Dependencies();
                Expression<?>[] arguments96 = new Expression<?>[1];
                arguments96[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition96 = new Expression<Boolean>(_17_0_2_edc0357_1352328158426_116757_20257_exists);
                elaborationRule96 = addElaborationRule(condition96, _17_0_2_edc0357_1352328156639_660827_19616.this, Customer._17_0_2_edc0357_1352328156639_660827_19616._17_0_2_edc0357_1352328158426_116757_20257.class, "_SendSignalAction_changePowerUsage", arguments96);
                Expression<?>[] arguments97 = new Expression<?>[1];
                arguments97[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition97 = new Expression<Boolean>(_17_0_2_edc0357_1352328158425_844513_20251_exists);
                elaborationRule97 = addElaborationRule(condition97, _17_0_2_edc0357_1352328156639_660827_19616.this, Customer._17_0_2_edc0357_1352328156639_660827_19616._17_0_2_edc0357_1352328158425_844513_20251.class, "_AddStructuralFeatureValueAction_changePowerUsage", arguments97);
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

            public BooleanParameter _17_0_2_edc0357_1352328158425_562603_20250_exists = null;

            public BooleanParameter _17_0_2_edc0357_1352328158425_536558_20249_exists = null;

            public BooleanParameter objectToPass = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158425_562603_20250_existsDependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158425_536558_20249_existsDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Effect effect98 = null;

            public Parameter effect98Var = null;

            public Effect effect99 = null;

            public Parameter effect99Var = null;

            public ElaborationRule elaborationRule100 = null;

            public ElaborationRule elaborationRule101 = null;

            public void init_17_0_2_edc0357_1352328158427_71576_20259Members() {
                try {
                    if (_17_0_2_edc0357_1352328158425_562603_20250_exists == null) _17_0_2_edc0357_1352328158425_562603_20250_exists = new BooleanParameter("_17_0_2_edc0357_1352328158425_562603_20250_exists", (Boolean) false, this);
                    if (_17_0_2_edc0357_1352328158425_536558_20249_exists == null) _17_0_2_edc0357_1352328158425_536558_20249_exists = new BooleanParameter("_17_0_2_edc0357_1352328158425_536558_20249_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect98VarV = sig_17_0_2_edc0357_1352328158430_913443_20274;
                    effect98Var = new Parameter("effect98Var", null, null, this);
                    addDependency(effect98Var, new Expression(effect98VarV));
                    effect98 = new EffectFunction(new FunctionCall(effect98Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect99VarV = sig_17_0_2_edc0357_1352328158430_934674_20275;
                    effect99Var = new Parameter("effect99Var", null, null, this);
                    addDependency(effect99Var, new Expression(effect99VarV));
                    effect99 = new EffectFunction(new FunctionCall(effect99Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158427_71576_20259Collections() {
                parameters.add(_17_0_2_edc0357_1352328158425_562603_20250_exists);
                parameters.add(_17_0_2_edc0357_1352328158425_536558_20249_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect98Var = new TreeSet<Effect>();
                effectsForeffect98Var.add(effect98);
                addEffects((Parameter<?>) effect98Var, effectsForeffect98Var);
                Set<Effect> effectsForeffect99Var = new TreeSet<Effect>();
                effectsForeffect99Var.add(effect99);
                addEffects((Parameter<?>) effect99Var, effectsForeffect99Var);
            }

            public void init_17_0_2_edc0357_1352328158427_71576_20259Dependencies() {
                addDependency(_17_0_2_edc0357_1352328158425_562603_20250_exists, new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall));
                addDependency(_17_0_2_edc0357_1352328158425_536558_20249_exists, new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new FunctionCall(sig_17_0_2_edc0357_1352328158430_229342_20273, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_edc0357_1352328158427_71576_20259Elaborations() {
                init_17_0_2_edc0357_1352328158427_71576_20259Dependencies();
                Expression<?>[] arguments100 = new Expression<?>[1];
                arguments100[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition100 = new Expression<Boolean>(_17_0_2_edc0357_1352328158425_536558_20249_exists);
                elaborationRule100 = addElaborationRule(condition100, _17_0_2_edc0357_1352328156639_660827_19616.this, Customer._17_0_2_edc0357_1352328156639_660827_19616._17_0_2_edc0357_1352328158425_536558_20249.class, "_ReadSelfAction_changePowerUsage", arguments100);
                Expression<?>[] arguments101 = new Expression<?>[1];
                arguments101[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition101 = new Expression<Boolean>(_17_0_2_edc0357_1352328158425_562603_20250_exists);
                elaborationRule101 = addElaborationRule(condition101, _17_0_2_edc0357_1352328156639_660827_19616.this, Customer._17_0_2_edc0357_1352328156639_660827_19616._17_0_2_edc0357_1352328158425_562603_20250.class, "_ValueSpecificationAction_changePowerUsage", arguments101);
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

            public Effect effect102 = null;

            public Parameter effect102Var = null;

            public ElaborationRule elaborationRule103 = null;

            public void init_17_0_2_edc0357_1352328158427_744964_20260Members() {
                try {
                    if (new_load == null) new_load = new IntegerParameter("new_load", (Integer) null, this);
                    if (_17_0_2_edc0357_1352328160555_982450_21420 == null) _17_0_2_edc0357_1352328160555_982450_21420 = new IntegerParameter("_17_0_2_edc0357_1352328160555_982450_21420", (Integer) null, this);
                    if (_17_0_2_edc0357_1352328158428_314626_20264_exists == null) _17_0_2_edc0357_1352328158428_314626_20264_exists = new BooleanParameter("_17_0_2_edc0357_1352328158428_314626_20264_exists", (Boolean) false, this);
                    if (_17_0_2_edc0357_1352328160555_212303_21421 == null) _17_0_2_edc0357_1352328160555_212303_21421 = new IntegerParameter("_17_0_2_edc0357_1352328160555_212303_21421", (Integer) null, this);
                    if (desired == null) desired = new IntegerParameter("desired", (Integer) null, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect102VarV = sig_17_0_2_edc0357_1352328158431_441018_20285;
                    effect102Var = new Parameter("effect102Var", null, null, this);
                    addDependency(effect102Var, new Expression(effect102VarV));
                    effect102 = new EffectFunction(new FunctionCall(effect102Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { _17_0_2_edc0357_1352328160555_982450_21420, endTime }));
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
                Set<Effect> effectsForeffect102Var = new TreeSet<Effect>();
                effectsForeffect102Var.add(effect102);
                addEffects((Parameter<?>) effect102Var, effectsForeffect102Var);
            }

            public void init_17_0_2_edc0357_1352328158427_744964_20260Dependencies() {
                addDependency(_17_0_2_edc0357_1352328160555_982450_21420, new Expression<Integer>(new_load));
                addDependency(new_load, new Expression<Integer>(desired));
                addDependency(_17_0_2_edc0357_1352328158428_314626_20264_exists, new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall));
                addDependency(_17_0_2_edc0357_1352328160555_212303_21421, new Expression<Integer>(new FunctionCall(sig_17_0_2_edc0357_1352328158431_555149_20284, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(desired, new Expression<Integer>(_17_0_2_edc0357_1352328160555_212303_21421));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new FunctionCall(sig_17_0_2_edc0357_1352328158431_279190_20281, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_edc0357_1352328158427_744964_20260Elaborations() {
                init_17_0_2_edc0357_1352328158427_744964_20260Dependencies();
                Expression<?>[] arguments103 = new Expression<?>[2];
                arguments103[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                arguments103[1] = new Expression<ObjectFlow<Integer>>(sig_17_0_2_edc0357_1352328158431_441018_20285);
                Expression<Boolean> condition103 = new Expression<Boolean>(_17_0_2_edc0357_1352328158428_314626_20264_exists);
                elaborationRule103 = addElaborationRule(condition103, _17_0_2_edc0357_1352328156639_660827_19616.this, Customer._17_0_2_edc0357_1352328156639_660827_19616._17_0_2_edc0357_1352328158428_314626_20264.class, "mergeynode_MergeNode_changePowerUsage", arguments103);
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

            public Effect effect104 = null;

            public Parameter effect104Var = null;

            public Effect effect105 = null;

            public Parameter effect105Var = null;

            public Effect effect106 = null;

            public Parameter effect106Var = null;

            public Effect effect107 = null;

            public Parameter effect107Var = null;

            public ElaborationRule elaborationRule108 = null;

            public ElaborationRule elaborationRule109 = null;

            public void init_17_0_2_edc0357_1352328158428_668812_20261Members() {
                try {
                    if (_17_0_2_edc0357_1352328158428_38684_20262_exists == null) _17_0_2_edc0357_1352328158428_38684_20262_exists = new BooleanParameter("_17_0_2_edc0357_1352328158428_38684_20262_exists", (Boolean) false, this);
                    if (myDeciderID_decider_17_0_2_edc0357_1352328158428_38684_20262 == null) myDeciderID_decider_17_0_2_edc0357_1352328158428_38684_20262 = new IntegerParameter("myDeciderID_decider_17_0_2_edc0357_1352328158428_38684_20262", (Integer) 2, this);
                    if (myDeciderID_decider_17_0_2_edc0357_1352328158426_903101_20256 == null) myDeciderID_decider_17_0_2_edc0357_1352328158426_903101_20256 = new IntegerParameter("myDeciderID_decider_17_0_2_edc0357_1352328158426_903101_20256", (Integer) 2, this);
                    if (objectToPass == null) objectToPass = new IntegerParameter("objectToPass", (Integer) null, this);
                    if (_17_0_2_edc0357_1352328158426_903101_20256_exists == null) _17_0_2_edc0357_1352328158426_903101_20256_exists = new BooleanParameter("_17_0_2_edc0357_1352328158426_903101_20256_exists", (Boolean) false, this);
                    Object effect104VarV = sig_17_0_2_edc0357_1352328158430_40918_20278;
                    effect104Var = new Parameter("effect104Var", null, null, this);
                    addDependency(effect104Var, new Expression(effect104VarV));
                    effect104 = new EffectFunction(new FunctionCall(effect104Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect105VarV = sig_17_0_2_edc0357_1352328158430_389607_20279;
                    effect105Var = new Parameter("effect105Var", null, null, this);
                    addDependency(effect105Var, new Expression(effect105VarV));
                    effect105 = new EffectFunction(new FunctionCall(effect105Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect106VarV = decider_17_0_2_edc0357_1352328158426_903101_20256;
                    effect106Var = new Parameter("effect106Var", null, null, this);
                    addDependency(effect106Var, new Expression(effect106VarV));
                    effect106 = new EffectFunction(new FunctionCall(effect106Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, myDeciderID_decider_17_0_2_edc0357_1352328158426_903101_20256 }));
                    Object effect107VarV = decider_17_0_2_edc0357_1352328158428_38684_20262;
                    effect107Var = new Parameter("effect107Var", null, null, this);
                    addDependency(effect107Var, new Expression(effect107VarV));
                    effect107 = new EffectFunction(new FunctionCall(effect107Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, myDeciderID_decider_17_0_2_edc0357_1352328158428_38684_20262 }));
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
                Set<Effect> effectsForeffect104Var = new TreeSet<Effect>();
                effectsForeffect104Var.add(effect104);
                addEffects((Parameter<?>) effect104Var, effectsForeffect104Var);
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

            public void init_17_0_2_edc0357_1352328158428_668812_20261Dependencies() {
                addDependency(_17_0_2_edc0357_1352328158428_38684_20262_exists, new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158428_38684_20262, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158428_38684_20262, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "maxSize"), new Object[] {})))).functionCall), new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158428_38684_20262, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_edc0357_1352328158428_38684_20262))).functionCall))).functionCall), new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall))).functionCall));
                addDependency(myDeciderID_decider_17_0_2_edc0357_1352328158428_38684_20262, new Expression<Integer>(2));
                addDependency(myDeciderID_decider_17_0_2_edc0357_1352328158426_903101_20256, new Expression<Integer>(2));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Integer>(new FunctionCall(sig_17_0_2_edc0357_1352328158430_538124_20277, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_edc0357_1352328158426_903101_20256_exists, new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158426_903101_20256, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158426_903101_20256, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "maxSize"), new Object[] {})))).functionCall), new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158426_903101_20256, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_edc0357_1352328158426_903101_20256))).functionCall))).functionCall), new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall))).functionCall));
            }

            public void init_17_0_2_edc0357_1352328158428_668812_20261Elaborations() {
                init_17_0_2_edc0357_1352328158428_668812_20261Dependencies();
                Expression<?>[] arguments108 = new Expression<?>[1];
                arguments108[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition108 = new Expression<Boolean>(_17_0_2_edc0357_1352328158426_903101_20256_exists);
                elaborationRule108 = addElaborationRule(condition108, _17_0_2_edc0357_1352328156639_660827_19616.this, Customer._17_0_2_edc0357_1352328156639_660827_19616._17_0_2_edc0357_1352328158426_903101_20256.class, "min_CallBehaviorAction_changePowerUsage", arguments108);
                Expression<?>[] arguments109 = new Expression<?>[1];
                arguments109[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition109 = new Expression<Boolean>(_17_0_2_edc0357_1352328158428_38684_20262_exists);
                elaborationRule109 = addElaborationRule(condition109, _17_0_2_edc0357_1352328156639_660827_19616.this, Customer._17_0_2_edc0357_1352328156639_660827_19616._17_0_2_edc0357_1352328158428_38684_20262.class, "_DecisionNode_changePowerUsage", arguments109);
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

            public Effect effect110 = null;

            public Parameter effect110Var = null;

            public Effect effect111 = null;

            public Parameter effect111Var = null;

            public Effect effect112 = null;

            public Parameter effect112Var = null;

            public Effect effect113 = null;

            public Parameter effect113Var = null;

            public ElaborationRule elaborationRule114 = null;

            public ElaborationRule elaborationRule115 = null;

            public void init_17_0_2_edc0357_1352328158428_38684_20262Members() {
                try {
                    if (myDeciderID_decider_17_0_2_edc0357_1352328158427_744964_20260 == null) myDeciderID_decider_17_0_2_edc0357_1352328158427_744964_20260 = new IntegerParameter("myDeciderID_decider_17_0_2_edc0357_1352328158427_744964_20260", (Integer) 2, this);
                    if (myDeciderID_decider_17_0_2_edc0357_1352328158426_903101_20256 == null) myDeciderID_decider_17_0_2_edc0357_1352328158426_903101_20256 = new IntegerParameter("myDeciderID_decider_17_0_2_edc0357_1352328158426_903101_20256", (Integer) 3, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (_17_0_2_edc0357_1352328158426_903101_20256_exists == null) _17_0_2_edc0357_1352328158426_903101_20256_exists = new BooleanParameter("_17_0_2_edc0357_1352328158426_903101_20256_exists", (Boolean) false, this);
                    if (decisionInput == null) decisionInput = new IntegerParameter("decisionInput", (Integer) null, this);
                    if (_17_0_2_edc0357_1352328158427_744964_20260_exists == null) _17_0_2_edc0357_1352328158427_744964_20260_exists = new BooleanParameter("_17_0_2_edc0357_1352328158427_744964_20260_exists", (Boolean) false, this);
                    Object effect110VarV = sig_17_0_2_edc0357_1352328158431_806383_20280;
                    effect110Var = new Parameter("effect110Var", null, null, this);
                    addDependency(effect110Var, new Expression(effect110VarV));
                    effect110 = new EffectFunction(new FunctionCall(effect110Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "sendIf", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class, java.lang.Boolean.class), new Object[] { objectToPass, endTime, _17_0_2_edc0357_1352328158426_903101_20256_exists }));
                    Object effect111VarV = sig_17_0_2_edc0357_1352328158431_279190_20281;
                    effect111Var = new Parameter("effect111Var", null, null, this);
                    addDependency(effect111Var, new Expression(effect111VarV));
                    effect111 = new EffectFunction(new FunctionCall(effect111Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "sendIf", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class, java.lang.Boolean.class), new Object[] { objectToPass, endTime, _17_0_2_edc0357_1352328158427_744964_20260_exists }));
                    Object effect112VarV = decider_17_0_2_edc0357_1352328158426_903101_20256;
                    effect112Var = new Parameter("effect112Var", null, null, this);
                    addDependency(effect112Var, new Expression(effect112VarV));
                    effect112 = new EffectFunction(new FunctionCall(effect112Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, myDeciderID_decider_17_0_2_edc0357_1352328158426_903101_20256 }));
                    Object effect113VarV = decider_17_0_2_edc0357_1352328158427_744964_20260;
                    effect113Var = new Parameter("effect113Var", null, null, this);
                    addDependency(effect113Var, new Expression(effect113VarV));
                    effect113 = new EffectFunction(new FunctionCall(effect113Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, myDeciderID_decider_17_0_2_edc0357_1352328158427_744964_20260 }));
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
                Set<Effect> effectsForeffect110Var = new TreeSet<Effect>();
                effectsForeffect110Var.add(effect110);
                addEffects((Parameter<?>) effect110Var, effectsForeffect110Var);
                Set<Effect> effectsForeffect111Var = new TreeSet<Effect>();
                effectsForeffect111Var.add(effect111);
                addEffects((Parameter<?>) effect111Var, effectsForeffect111Var);
                Set<Effect> effectsForeffect112Var = new TreeSet<Effect>();
                effectsForeffect112Var.add(effect112);
                addEffects((Parameter<?>) effect112Var, effectsForeffect112Var);
                Set<Effect> effectsForeffect113Var = new TreeSet<Effect>();
                effectsForeffect113Var.add(effect113);
                addEffects((Parameter<?>) effect113Var, effectsForeffect113Var);
            }

            public void init_17_0_2_edc0357_1352328158428_38684_20262Dependencies() {
                addDependency(myDeciderID_decider_17_0_2_edc0357_1352328158427_744964_20260, new Expression<Integer>(2));
                addDependency(myDeciderID_decider_17_0_2_edc0357_1352328158426_903101_20256, new Expression<Integer>(3));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new FunctionCall(sig_17_0_2_edc0357_1352328158430_630436_20276, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_edc0357_1352328158426_903101_20256_exists, new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.Greater(new Expression<Integer>(decisionInput), new Expression<Integer>(0))).functionCall), new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158426_903101_20256, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158426_903101_20256, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "maxSize"), new Object[] {})))).functionCall))).functionCall), new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158426_903101_20256, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_edc0357_1352328158426_903101_20256))).functionCall))).functionCall), new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall))).functionCall));
                addDependency(decisionInput, new Expression<Integer>(new FunctionCall(sig_17_0_2_edc0357_1352328158430_40918_20278, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_edc0357_1352328158427_744964_20260_exists, new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.LessEquals(new Expression<Integer>(decisionInput), new Expression<Integer>(0))).functionCall), new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158427_744964_20260, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158427_744964_20260, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "maxSize"), new Object[] {})))).functionCall))).functionCall), new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158427_744964_20260, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_edc0357_1352328158427_744964_20260))).functionCall))).functionCall), new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall))).functionCall));
            }

            public void init_17_0_2_edc0357_1352328158428_38684_20262Elaborations() {
                init_17_0_2_edc0357_1352328158428_38684_20262Dependencies();
                Expression<?>[] arguments114 = new Expression<?>[1];
                arguments114[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition114 = new Expression<Boolean>(_17_0_2_edc0357_1352328158426_903101_20256_exists);
                elaborationRule114 = addElaborationRule(condition114, _17_0_2_edc0357_1352328156639_660827_19616.this, Customer._17_0_2_edc0357_1352328156639_660827_19616._17_0_2_edc0357_1352328158426_903101_20256.class, "min_CallBehaviorAction_changePowerUsage", arguments114);
                Expression<?>[] arguments115 = new Expression<?>[1];
                arguments115[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition115 = new Expression<Boolean>(_17_0_2_edc0357_1352328158427_744964_20260_exists);
                elaborationRule115 = addElaborationRule(condition115, _17_0_2_edc0357_1352328156639_660827_19616.this, Customer._17_0_2_edc0357_1352328156639_660827_19616._17_0_2_edc0357_1352328158427_744964_20260.class, "setd_CallBehaviorAction_changePowerUsage", arguments115);
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

            public Effect effect116 = null;

            public Parameter effect116Var = null;

            public Effect effect117 = null;

            public Parameter effect117Var = null;

            public Effect effect118 = null;

            public Parameter effect118Var = null;

            public Effect effect119 = null;

            public Parameter effect119Var = null;

            public ElaborationRule elaborationRule120 = null;

            public ElaborationRule elaborationRule121 = null;

            public void init_17_0_2_edc0357_1352328158428_316417_20263Members() {
                try {
                    if (myDeciderID_decider_17_0_2_edc0357_1352328158427_744964_20260 == null) myDeciderID_decider_17_0_2_edc0357_1352328158427_744964_20260 = new IntegerParameter("myDeciderID_decider_17_0_2_edc0357_1352328158427_744964_20260", (Integer) 1, this);
                    if (myDeciderID_decider_17_0_2_edc0357_1352328158426_903101_20256 == null) myDeciderID_decider_17_0_2_edc0357_1352328158426_903101_20256 = new IntegerParameter("myDeciderID_decider_17_0_2_edc0357_1352328158426_903101_20256", (Integer) 1, this);
                    if (objectToPass == null) objectToPass = new IntegerParameter("objectToPass", (Integer) null, this);
                    if (_17_0_2_edc0357_1352328158426_903101_20256_exists == null) _17_0_2_edc0357_1352328158426_903101_20256_exists = new BooleanParameter("_17_0_2_edc0357_1352328158426_903101_20256_exists", (Boolean) false, this);
                    if (_17_0_2_edc0357_1352328158427_744964_20260_exists == null) _17_0_2_edc0357_1352328158427_744964_20260_exists = new BooleanParameter("_17_0_2_edc0357_1352328158427_744964_20260_exists", (Boolean) false, this);
                    Object effect116VarV = sig_17_0_2_edc0357_1352328158431_868302_20283;
                    effect116Var = new Parameter("effect116Var", null, null, this);
                    addDependency(effect116Var, new Expression(effect116VarV));
                    effect116 = new EffectFunction(new FunctionCall(effect116Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect117VarV = sig_17_0_2_edc0357_1352328158431_555149_20284;
                    effect117Var = new Parameter("effect117Var", null, null, this);
                    addDependency(effect117Var, new Expression(effect117VarV));
                    effect117 = new EffectFunction(new FunctionCall(effect117Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect118VarV = decider_17_0_2_edc0357_1352328158426_903101_20256;
                    effect118Var = new Parameter("effect118Var", null, null, this);
                    addDependency(effect118Var, new Expression(effect118VarV));
                    effect118 = new EffectFunction(new FunctionCall(effect118Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, myDeciderID_decider_17_0_2_edc0357_1352328158426_903101_20256 }));
                    Object effect119VarV = decider_17_0_2_edc0357_1352328158427_744964_20260;
                    effect119Var = new Parameter("effect119Var", null, null, this);
                    addDependency(effect119Var, new Expression(effect119VarV));
                    effect119 = new EffectFunction(new FunctionCall(effect119Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, myDeciderID_decider_17_0_2_edc0357_1352328158427_744964_20260 }));
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
                Set<Effect> effectsForeffect116Var = new TreeSet<Effect>();
                effectsForeffect116Var.add(effect116);
                addEffects((Parameter<?>) effect116Var, effectsForeffect116Var);
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

            public void init_17_0_2_edc0357_1352328158428_316417_20263Dependencies() {
                addDependency(myDeciderID_decider_17_0_2_edc0357_1352328158427_744964_20260, new Expression<Integer>(1));
                addDependency(myDeciderID_decider_17_0_2_edc0357_1352328158426_903101_20256, new Expression<Integer>(1));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Integer>(new FunctionCall(sig_17_0_2_edc0357_1352328158431_275687_20282, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_edc0357_1352328158426_903101_20256_exists, new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158426_903101_20256, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158426_903101_20256, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "maxSize"), new Object[] {})))).functionCall), new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158426_903101_20256, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_edc0357_1352328158426_903101_20256))).functionCall))).functionCall), new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall))).functionCall));
                addDependency(_17_0_2_edc0357_1352328158427_744964_20260_exists, new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158427_744964_20260, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158427_744964_20260, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "maxSize"), new Object[] {})))).functionCall), new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158427_744964_20260, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_edc0357_1352328158427_744964_20260))).functionCall))).functionCall), new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall))).functionCall));
            }

            public void init_17_0_2_edc0357_1352328158428_316417_20263Elaborations() {
                init_17_0_2_edc0357_1352328158428_316417_20263Dependencies();
                Expression<?>[] arguments120 = new Expression<?>[1];
                arguments120[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition120 = new Expression<Boolean>(_17_0_2_edc0357_1352328158426_903101_20256_exists);
                elaborationRule120 = addElaborationRule(condition120, _17_0_2_edc0357_1352328156639_660827_19616.this, Customer._17_0_2_edc0357_1352328156639_660827_19616._17_0_2_edc0357_1352328158426_903101_20256.class, "min_CallBehaviorAction_changePowerUsage", arguments120);
                Expression<?>[] arguments121 = new Expression<?>[1];
                arguments121[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition121 = new Expression<Boolean>(_17_0_2_edc0357_1352328158427_744964_20260_exists);
                elaborationRule121 = addElaborationRule(condition121, _17_0_2_edc0357_1352328156639_660827_19616.this, Customer._17_0_2_edc0357_1352328156639_660827_19616._17_0_2_edc0357_1352328158427_744964_20260.class, "setd_CallBehaviorAction_changePowerUsage", arguments121);
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

            public Effect effect122 = null;

            public Parameter effect122Var = null;

            public ElaborationRule elaborationRule123 = null;

            public void init_17_0_2_edc0357_1352328158428_314626_20264Members() {
                try {
                    if (receiveThis == null) receiveThis = new Parameter<ObjectFlow<Integer>>("receiveThis", null, (ObjectFlow<Integer>) null, this);
                    if (_17_0_2_edc0357_1352328158427_233123_20258_exists == null) _17_0_2_edc0357_1352328158427_233123_20258_exists = new BooleanParameter("_17_0_2_edc0357_1352328158427_233123_20258_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new IntegerParameter("objectToPass", (Integer) null, this);
                    Object effect122VarV = sig_17_0_2_edc0357_1352328158431_643907_20287;
                    effect122Var = new Parameter("effect122Var", null, null, this);
                    addDependency(effect122Var, new Expression(effect122VarV));
                    effect122 = new EffectFunction(new FunctionCall(effect122Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158428_314626_20264Collections() {
                parameters.add(receiveThis);
                parameters.add(_17_0_2_edc0357_1352328158427_233123_20258_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect122Var = new TreeSet<Effect>();
                effectsForeffect122Var.add(effect122);
                addEffects((Parameter<?>) effect122Var, effectsForeffect122Var);
            }

            public void init_17_0_2_edc0357_1352328158428_314626_20264Dependencies() {
                addDependency(_17_0_2_edc0357_1352328158427_233123_20258_exists, new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Integer>(new FunctionCall(receiveThis, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_edc0357_1352328158428_314626_20264Elaborations() {
                init_17_0_2_edc0357_1352328158428_314626_20264Dependencies();
                Expression<?>[] arguments123 = new Expression<?>[1];
                arguments123[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition123 = new Expression<Boolean>(_17_0_2_edc0357_1352328158427_233123_20258_exists);
                elaborationRule123 = addElaborationRule(condition123, _17_0_2_edc0357_1352328156639_660827_19616.this, Customer._17_0_2_edc0357_1352328156639_660827_19616._17_0_2_edc0357_1352328158427_233123_20258.class, "fork_ForkNode_changePowerUsage", arguments123);
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

        public Parameter< ObjectFlow<Integer> > sig_17_0_2_edc0357_1352328158431_643907_20287 = null;

        public Parameter< ObjectFlow<Customer> > sig_17_0_2_edc0357_1352328158429_395232_20267 = null;

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

        public ElaborationRule elaborationRule63 = null;

        public ElaborationRule elaborationRule64 = null;

        public void init_17_0_2_edc0357_1352328156639_660827_19616Members() {
            try {
                if (caller == null) caller = new Parameter<DurativeEvent>("caller", null, (DurativeEvent) null, this);
                if (sig_17_0_2_edc0357_1352328158430_389607_20279 == null) sig_17_0_2_edc0357_1352328158430_389607_20279 = new Parameter<ObjectFlow<Integer>>("sig_17_0_2_edc0357_1352328158430_389607_20279", null, (ObjectFlow<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158430_389607_20279" })).evaluate(true), this);
                if (_17_0_2_edc0357_1352328158425_174981_20253_exists == null) _17_0_2_edc0357_1352328158425_174981_20253_exists = new BooleanParameter("_17_0_2_edc0357_1352328158425_174981_20253_exists", (Boolean) false, this);
                if (sig_17_0_2_edc0357_1352328160546_168484_21406 == null) sig_17_0_2_edc0357_1352328160546_168484_21406 = new Parameter<ObjectFlow<Integer>>("sig_17_0_2_edc0357_1352328160546_168484_21406", null, (ObjectFlow<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328160546_168484_21406" })).evaluate(true), this);
                if (decider_17_0_2_edc0357_1352328158425_844513_20251 == null) decider_17_0_2_edc0357_1352328158425_844513_20251 = new Parameter<TimeVaryingList<Integer>>("decider_17_0_2_edc0357_1352328158425_844513_20251", null, (TimeVaryingList<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingList.class, java.lang.String.class, int.class), new Object[] { "decider_17_0_2_edc0357_1352328158425_844513_20251", 2 })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328158431_441018_20285 == null) sig_17_0_2_edc0357_1352328158431_441018_20285 = new Parameter<ObjectFlow<Integer>>("sig_17_0_2_edc0357_1352328158431_441018_20285", null, (ObjectFlow<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158431_441018_20285" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328158431_643907_20287 == null) sig_17_0_2_edc0357_1352328158431_643907_20287 = new Parameter<ObjectFlow<Integer>>("sig_17_0_2_edc0357_1352328158431_643907_20287", null, (ObjectFlow<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158431_643907_20287" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328158429_395232_20267 == null) sig_17_0_2_edc0357_1352328158429_395232_20267 = new Parameter<ObjectFlow<Customer>>("sig_17_0_2_edc0357_1352328158429_395232_20267", null, (ObjectFlow<Customer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158429_395232_20267" })).evaluate(true), this);
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
                if (finalNode_endTime == null) finalNode_endTime = new IntegerParameter("finalNode_endTime", (Integer) 84000, this);
                if (sig_17_0_2_edc0357_1352328158431_806383_20280 == null) sig_17_0_2_edc0357_1352328158431_806383_20280 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328158431_806383_20280", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158431_806383_20280" })).evaluate(true), this);
                if (decider_17_0_2_edc0357_1352328158428_38684_20262 == null) decider_17_0_2_edc0357_1352328158428_38684_20262 = new Parameter<TimeVaryingList<Integer>>("decider_17_0_2_edc0357_1352328158428_38684_20262", null, (TimeVaryingList<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingList.class, java.lang.String.class, int.class), new Object[] { "decider_17_0_2_edc0357_1352328158428_38684_20262", 2 })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328158431_565981_20286 == null) sig_17_0_2_edc0357_1352328158431_565981_20286 = new Parameter<ObjectFlow<Integer>>("sig_17_0_2_edc0357_1352328158431_565981_20286", null, (ObjectFlow<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158431_565981_20286" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328158430_934674_20275 == null) sig_17_0_2_edc0357_1352328158430_934674_20275 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328158430_934674_20275", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158430_934674_20275" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328158431_868302_20283 == null) sig_17_0_2_edc0357_1352328158431_868302_20283 = new Parameter<ObjectFlow<Integer>>("sig_17_0_2_edc0357_1352328158431_868302_20283", null, (ObjectFlow<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158431_868302_20283" })).evaluate(true), this);
                if (finalNode_startTime == null) finalNode_startTime = new IntegerParameter("finalNode_startTime", (Integer) 84000, this);
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
            parameters.add(sig_17_0_2_edc0357_1352328158431_643907_20287);
            parameters.add(sig_17_0_2_edc0357_1352328158429_395232_20267);
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
            addDependency(_17_0_2_edc0357_1352328158425_174981_20253_exists, new Expression<Boolean>(new FunctionCall(sig_17_0_2_edc0357_1352328158429_418011_20269, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>((new Functions.Minus(new Expression<Integer>(finalNode_startTime), new Expression<Integer>(1))).functionCall) })));
            addDependency(endTime, new Expression<Integer>(finalNode_endTime));
        }

        public void init_17_0_2_edc0357_1352328156639_660827_19616Elaborations() {
            init_17_0_2_edc0357_1352328156639_660827_19616Dependencies();
            Expression<?>[] arguments63 = new Expression<?>[1];
            arguments63[0] = new Expression<Integer>(startTime);
            Expression<Boolean> condition63 = new Expression<Boolean>(true);
            elaborationRule63 = addElaborationRule(condition63, _17_0_2_edc0357_1352328156639_660827_19616.this, Customer._17_0_2_edc0357_1352328156639_660827_19616._17_0_2_edc0357_1352328158425_23694_20252.class, "_InitialNode_changePowerUsage", arguments63);
            Expression<?>[] arguments64 = new Expression<?>[1];
            arguments64[0] = new Expression<Integer>(finalNode_startTime);
            Expression<Boolean> condition64 = new Expression<Boolean>(_17_0_2_edc0357_1352328158425_174981_20253_exists);
            elaborationRule64 = addElaborationRule(condition64, _17_0_2_edc0357_1352328156639_660827_19616.this, Customer._17_0_2_edc0357_1352328156639_660827_19616._17_0_2_edc0357_1352328158425_174981_20253.class, "_ActivityFinalNode_changePowerUsage", arguments64);
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

            public Effect effect126 = null;

            public Parameter effect126Var = null;

            public ElaborationRule elaborationRule127 = null;

            public void init_17_0_2_edc0357_1352328158532_777314_20319Members() {
                try {
                    if (_17_0_2_edc0357_1352328160561_291373_21448 == null) _17_0_2_edc0357_1352328160561_291373_21448 = new Parameter<Customer>("_17_0_2_edc0357_1352328160561_291373_21448", null, (Customer) Customer.this, this);
                    if (_17_0_2_edc0357_1352328158533_247450_20324_exists == null) _17_0_2_edc0357_1352328158533_247450_20324_exists = new BooleanParameter("_17_0_2_edc0357_1352328158533_247450_20324_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect126VarV = sig_17_0_2_edc0357_1352328158534_268675_20330;
                    effect126Var = new Parameter("effect126Var", null, null, this);
                    addDependency(effect126Var, new Expression(effect126VarV));
                    effect126 = new EffectFunction(new FunctionCall(effect126Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { _17_0_2_edc0357_1352328160561_291373_21448, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158532_777314_20319Collections() {
                parameters.add(_17_0_2_edc0357_1352328160561_291373_21448);
                parameters.add(_17_0_2_edc0357_1352328158533_247450_20324_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect126Var = new TreeSet<Effect>();
                effectsForeffect126Var.add(effect126);
                addEffects((Parameter<?>) effect126Var, effectsForeffect126Var);
            }

            public void init_17_0_2_edc0357_1352328158532_777314_20319Dependencies() {
                addDependency(duration, new Expression<Integer>(1));
                addDependency(_17_0_2_edc0357_1352328158533_247450_20324_exists, new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall));
                addDependency(objectToPass, new Expression<Boolean>(new FunctionCall(sig_17_0_2_edc0357_1352328158535_401408_20338, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_edc0357_1352328158532_777314_20319Elaborations() {
                init_17_0_2_edc0357_1352328158532_777314_20319Dependencies();
                Expression<?>[] arguments127 = new Expression<?>[1];
                arguments127[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition127 = new Expression<Boolean>(_17_0_2_edc0357_1352328158533_247450_20324_exists);
                elaborationRule127 = addElaborationRule(condition127, _17_0_2_edc0357_1352328156644_264215_19617.this, Customer._17_0_2_edc0357_1352328156644_264215_19617._17_0_2_edc0357_1352328158533_247450_20324.class, "_ForkNode_initialize", arguments127);
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

            public Effect effect128 = null;

            public Parameter effect128Var = null;

            public Effect effect129 = null;

            public Parameter effect129Var = null;

            public ElaborationRule elaborationRule130 = null;

            public void init_17_0_2_edc0357_1352328158532_127805_20320Members() {
                try {
                    if (_17_0_2_edc0357_1352328158532_578076_20321_exists == null) _17_0_2_edc0357_1352328158532_578076_20321_exists = new BooleanParameter("_17_0_2_edc0357_1352328158532_578076_20321_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (_17_0_2_edc0357_1352328160562_338541_21450 == null) _17_0_2_edc0357_1352328160562_338541_21450 = new IntegerParameter("_17_0_2_edc0357_1352328160562_338541_21450", (Integer) 4, this);
                    if (myDeciderID_decider_17_0_2_edc0357_1352328158532_578076_20321 == null) myDeciderID_decider_17_0_2_edc0357_1352328158532_578076_20321 = new IntegerParameter("myDeciderID_decider_17_0_2_edc0357_1352328158532_578076_20321", (Integer) 2, this);
                    Object effect128VarV = sig_17_0_2_edc0357_1352328158534_440722_20329;
                    effect128Var = new Parameter("effect128Var", null, null, this);
                    addDependency(effect128Var, new Expression(effect128VarV));
                    effect128 = new EffectFunction(new FunctionCall(effect128Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { _17_0_2_edc0357_1352328160562_338541_21450, endTime }));
                    Object effect129VarV = decider_17_0_2_edc0357_1352328158532_578076_20321;
                    effect129Var = new Parameter("effect129Var", null, null, this);
                    addDependency(effect129Var, new Expression(effect129VarV));
                    effect129 = new EffectFunction(new FunctionCall(effect129Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, myDeciderID_decider_17_0_2_edc0357_1352328158532_578076_20321 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158532_127805_20320Collections() {
                parameters.add(_17_0_2_edc0357_1352328158532_578076_20321_exists);
                parameters.add(objectToPass);
                parameters.add(_17_0_2_edc0357_1352328160562_338541_21450);
                parameters.add(myDeciderID_decider_17_0_2_edc0357_1352328158532_578076_20321);
                Set<Effect> effectsForeffect128Var = new TreeSet<Effect>();
                effectsForeffect128Var.add(effect128);
                addEffects((Parameter<?>) effect128Var, effectsForeffect128Var);
                Set<Effect> effectsForeffect129Var = new TreeSet<Effect>();
                effectsForeffect129Var.add(effect129);
                addEffects((Parameter<?>) effect129Var, effectsForeffect129Var);
            }

            public void init_17_0_2_edc0357_1352328158532_127805_20320Dependencies() {
                addDependency(_17_0_2_edc0357_1352328158532_578076_20321_exists, new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158532_578076_20321, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158532_578076_20321, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "maxSize"), new Object[] {})))).functionCall), new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158532_578076_20321, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_edc0357_1352328158532_578076_20321))).functionCall))).functionCall), new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall))).functionCall));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new FunctionCall(sig_17_0_2_edc0357_1352328158535_735383_20339, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_edc0357_1352328160562_338541_21450, new Expression<Integer>(4));
                addDependency(myDeciderID_decider_17_0_2_edc0357_1352328158532_578076_20321, new Expression<Integer>(2));
            }

            public void init_17_0_2_edc0357_1352328158532_127805_20320Elaborations() {
                init_17_0_2_edc0357_1352328158532_127805_20320Dependencies();
                Expression<?>[] arguments130 = new Expression<?>[1];
                arguments130[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition130 = new Expression<Boolean>(_17_0_2_edc0357_1352328158532_578076_20321_exists);
                elaborationRule130 = addElaborationRule(condition130, _17_0_2_edc0357_1352328156644_264215_19617.this, Customer._17_0_2_edc0357_1352328156644_264215_19617._17_0_2_edc0357_1352328158532_578076_20321.class, "_AddStructuralFeatureValueAction_initialize", arguments130);
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

            public Effect effect131 = null;

            public Parameter effect131Var = null;

            public Effect effect132 = null;

            public Parameter effect132Var = null;

            public Effect effect133 = null;

            public Parameter effect133Var = null;

            public ElaborationRule elaborationRule134 = null;

            public void init_17_0_2_edc0357_1352328158532_578076_20321Members() {
                try {
                    if (_17_0_2_edc0357_1352328158533_586682_20327_exists == null) _17_0_2_edc0357_1352328158533_586682_20327_exists = new BooleanParameter("_17_0_2_edc0357_1352328158533_586682_20327_exists", (Boolean) false, this);
                    if (myDeciderID_decider_17_0_2_edc0357_1352328158533_586682_20327 == null) myDeciderID_decider_17_0_2_edc0357_1352328158533_586682_20327 = new IntegerParameter("myDeciderID_decider_17_0_2_edc0357_1352328158533_586682_20327", (Integer) 1, this);
                    if (_17_0_2_edc0357_1352328160563_822997_21452 == null) _17_0_2_edc0357_1352328160563_822997_21452 = new Parameter<Customer>("_17_0_2_edc0357_1352328160563_822997_21452", null, (Customer) null, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (_17_0_2_edc0357_1352328160563_706626_21451 == null) _17_0_2_edc0357_1352328160563_706626_21451 = new IntegerParameter("_17_0_2_edc0357_1352328160563_706626_21451", (Integer) null, this);
                    Object effect131VarV = sig_17_0_2_edc0357_1352328158534_769162_20334;
                    effect131Var = new Parameter("effect131Var", null, null, this);
                    addDependency(effect131Var, new Expression(effect131VarV));
                    effect131 = new EffectFunction(new FunctionCall(effect131Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect132VarV = decider_17_0_2_edc0357_1352328158533_586682_20327;
                    effect132Var = new Parameter("effect132Var", null, null, this);
                    addDependency(effect132Var, new Expression(effect132VarV));
                    effect132 = new EffectFunction(new FunctionCall(effect132Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, myDeciderID_decider_17_0_2_edc0357_1352328158533_586682_20327 }));
                    Object effect133VarV = usage__17_0_2_edc0357_1352328156649_216766_19620;
                    effect133Var = new Parameter("effect133Var", null, null, this);
                    addDependency(effect133Var, new Expression(effect133VarV));
                    effect133 = new EffectFunction(new FunctionCall(effect133Var, ClassUtils.getMethodForArgTypes("TimeVaryingMap<Integer>", "powersystem", "setValue", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { startTime, _17_0_2_edc0357_1352328160563_706626_21451 }));
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
                Set<Effect> effectsForeffect131Var = new TreeSet<Effect>();
                effectsForeffect131Var.add(effect131);
                addEffects((Parameter<?>) effect131Var, effectsForeffect131Var);
                Set<Effect> effectsForeffect132Var = new TreeSet<Effect>();
                effectsForeffect132Var.add(effect132);
                addEffects((Parameter<?>) effect132Var, effectsForeffect132Var);
                Set<Effect> effectsForeffect133Var = new TreeSet<Effect>();
                effectsForeffect133Var.add(effect133);
                addEffects((Parameter<?>) effect133Var, effectsForeffect133Var);
            }

            public void init_17_0_2_edc0357_1352328158532_578076_20321Dependencies() {
                addDependency(_17_0_2_edc0357_1352328158533_586682_20327_exists, new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158533_586682_20327, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158533_586682_20327, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "maxSize"), new Object[] {})))).functionCall), new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158533_586682_20327, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_edc0357_1352328158533_586682_20327))).functionCall))).functionCall), new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall))).functionCall));
                addDependency(myDeciderID_decider_17_0_2_edc0357_1352328158533_586682_20327, new Expression<Integer>(1));
                addDependency(_17_0_2_edc0357_1352328160563_822997_21452, new Expression<Customer>(new FunctionCall(sig_17_0_2_edc0357_1352328158534_699192_20331, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(true));
                addDependency(_17_0_2_edc0357_1352328160563_706626_21451, new Expression<Integer>(new FunctionCall(sig_17_0_2_edc0357_1352328158534_440722_20329, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_edc0357_1352328158532_578076_20321Elaborations() {
                init_17_0_2_edc0357_1352328158532_578076_20321Dependencies();
                Expression<?>[] arguments134 = new Expression<?>[1];
                arguments134[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition134 = new Expression<Boolean>(_17_0_2_edc0357_1352328158533_586682_20327_exists);
                elaborationRule134 = addElaborationRule(condition134, _17_0_2_edc0357_1352328156644_264215_19617.this, Customer._17_0_2_edc0357_1352328156644_264215_19617._17_0_2_edc0357_1352328158533_586682_20327.class, "_JoinNode_initialize", arguments134);
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

            public Effect effect135 = null;

            public Parameter effect135Var = null;

            public ElaborationRule elaborationRule136 = null;

            public void init_17_0_2_edc0357_1352328158532_946115_20322Members() {
                try {
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (_17_0_2_edc0357_1352328158533_797791_20328_exists == null) _17_0_2_edc0357_1352328158533_797791_20328_exists = new BooleanParameter("_17_0_2_edc0357_1352328158533_797791_20328_exists", (Boolean) false, this);
                    Object effect135VarV = sig_17_0_2_edc0357_1352328158535_536324_20337;
                    effect135Var = new Parameter("effect135Var", null, null, this);
                    addDependency(effect135Var, new Expression(effect135VarV));
                    effect135 = new EffectFunction(new FunctionCall(effect135Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158532_946115_20322Collections() {
                parameters.add(objectToPass);
                parameters.add(_17_0_2_edc0357_1352328158533_797791_20328_exists);
                Set<Effect> effectsForeffect135Var = new TreeSet<Effect>();
                effectsForeffect135Var.add(effect135);
                addEffects((Parameter<?>) effect135Var, effectsForeffect135Var);
            }

            public void init_17_0_2_edc0357_1352328158532_946115_20322Dependencies() {
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(true));
                addDependency(_17_0_2_edc0357_1352328158533_797791_20328_exists, new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall));
            }

            public void init_17_0_2_edc0357_1352328158532_946115_20322Elaborations() {
                init_17_0_2_edc0357_1352328158532_946115_20322Dependencies();
                Expression<?>[] arguments136 = new Expression<?>[1];
                arguments136[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition136 = new Expression<Boolean>(_17_0_2_edc0357_1352328158533_797791_20328_exists);
                elaborationRule136 = addElaborationRule(condition136, _17_0_2_edc0357_1352328156644_264215_19617.this, Customer._17_0_2_edc0357_1352328156644_264215_19617._17_0_2_edc0357_1352328158533_797791_20328.class, "_ForkNode_initialize", arguments136);
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
                addDependency(endTime, new Expression<Integer>((new Functions.Plus(new Expression<Integer>(startTime), new Expression<Integer>(duration))).functionCall));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new FunctionCall(sig_17_0_2_edc0357_1352328158534_557121_20335, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
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

            public IntegerParameter myDeciderID_decider_17_0_2_edc0357_1352328158532_578076_20321 = null;

            public BooleanParameter _17_0_2_edc0357_1352328158533_93964_20325_exists = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158532_578076_20321_existsDependency = null;

            public Dependency< Integer > myDeciderID_decider_17_0_2_edc0357_1352328158533_93964_20325Dependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Customer > objectToPassDependency = null;

            public Dependency< Integer > myDeciderID_decider_17_0_2_edc0357_1352328158532_578076_20321Dependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158533_93964_20325_existsDependency = null;

            public Effect effect137 = null;

            public Parameter effect137Var = null;

            public Effect effect138 = null;

            public Parameter effect138Var = null;

            public Effect effect139 = null;

            public Parameter effect139Var = null;

            public Effect effect140 = null;

            public Parameter effect140Var = null;

            public ElaborationRule elaborationRule141 = null;

            public ElaborationRule elaborationRule142 = null;

            public void init_17_0_2_edc0357_1352328158533_247450_20324Members() {
                try {
                    if (_17_0_2_edc0357_1352328158532_578076_20321_exists == null) _17_0_2_edc0357_1352328158532_578076_20321_exists = new BooleanParameter("_17_0_2_edc0357_1352328158532_578076_20321_exists", (Boolean) false, this);
                    if (myDeciderID_decider_17_0_2_edc0357_1352328158533_93964_20325 == null) myDeciderID_decider_17_0_2_edc0357_1352328158533_93964_20325 = new IntegerParameter("myDeciderID_decider_17_0_2_edc0357_1352328158533_93964_20325", (Integer) 1, this);
                    if (objectToPass == null) objectToPass = new Parameter<Customer>("objectToPass", null, (Customer) null, this);
                    if (myDeciderID_decider_17_0_2_edc0357_1352328158532_578076_20321 == null) myDeciderID_decider_17_0_2_edc0357_1352328158532_578076_20321 = new IntegerParameter("myDeciderID_decider_17_0_2_edc0357_1352328158532_578076_20321", (Integer) 1, this);
                    if (_17_0_2_edc0357_1352328158533_93964_20325_exists == null) _17_0_2_edc0357_1352328158533_93964_20325_exists = new BooleanParameter("_17_0_2_edc0357_1352328158533_93964_20325_exists", (Boolean) false, this);
                    Object effect137VarV = sig_17_0_2_edc0357_1352328158534_699192_20331;
                    effect137Var = new Parameter("effect137Var", null, null, this);
                    addDependency(effect137Var, new Expression(effect137VarV));
                    effect137 = new EffectFunction(new FunctionCall(effect137Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect138VarV = sig_17_0_2_edc0357_1352328158534_844780_20333;
                    effect138Var = new Parameter("effect138Var", null, null, this);
                    addDependency(effect138Var, new Expression(effect138VarV));
                    effect138 = new EffectFunction(new FunctionCall(effect138Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect139VarV = decider_17_0_2_edc0357_1352328158533_93964_20325;
                    effect139Var = new Parameter("effect139Var", null, null, this);
                    addDependency(effect139Var, new Expression(effect139VarV));
                    effect139 = new EffectFunction(new FunctionCall(effect139Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, myDeciderID_decider_17_0_2_edc0357_1352328158533_93964_20325 }));
                    Object effect140VarV = decider_17_0_2_edc0357_1352328158532_578076_20321;
                    effect140Var = new Parameter("effect140Var", null, null, this);
                    addDependency(effect140Var, new Expression(effect140VarV));
                    effect140 = new EffectFunction(new FunctionCall(effect140Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, myDeciderID_decider_17_0_2_edc0357_1352328158532_578076_20321 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158533_247450_20324Collections() {
                parameters.add(_17_0_2_edc0357_1352328158532_578076_20321_exists);
                parameters.add(myDeciderID_decider_17_0_2_edc0357_1352328158533_93964_20325);
                parameters.add(objectToPass);
                parameters.add(myDeciderID_decider_17_0_2_edc0357_1352328158532_578076_20321);
                parameters.add(_17_0_2_edc0357_1352328158533_93964_20325_exists);
                Set<Effect> effectsForeffect137Var = new TreeSet<Effect>();
                effectsForeffect137Var.add(effect137);
                addEffects((Parameter<?>) effect137Var, effectsForeffect137Var);
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

            public void init_17_0_2_edc0357_1352328158533_247450_20324Dependencies() {
                addDependency(_17_0_2_edc0357_1352328158532_578076_20321_exists, new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158532_578076_20321, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158532_578076_20321, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "maxSize"), new Object[] {})))).functionCall), new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158532_578076_20321, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_edc0357_1352328158532_578076_20321))).functionCall))).functionCall), new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall))).functionCall));
                addDependency(myDeciderID_decider_17_0_2_edc0357_1352328158533_93964_20325, new Expression<Integer>(1));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Customer>(new FunctionCall(sig_17_0_2_edc0357_1352328158534_268675_20330, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(myDeciderID_decider_17_0_2_edc0357_1352328158532_578076_20321, new Expression<Integer>(1));
                addDependency(_17_0_2_edc0357_1352328158533_93964_20325_exists, new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158533_93964_20325, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158533_93964_20325, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "maxSize"), new Object[] {})))).functionCall), new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158533_93964_20325, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_edc0357_1352328158533_93964_20325))).functionCall))).functionCall), new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall))).functionCall));
            }

            public void init_17_0_2_edc0357_1352328158533_247450_20324Elaborations() {
                init_17_0_2_edc0357_1352328158533_247450_20324Dependencies();
                Expression<?>[] arguments141 = new Expression<?>[1];
                arguments141[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition141 = new Expression<Boolean>(_17_0_2_edc0357_1352328158533_93964_20325_exists);
                elaborationRule141 = addElaborationRule(condition141, _17_0_2_edc0357_1352328156644_264215_19617.this, Customer._17_0_2_edc0357_1352328156644_264215_19617._17_0_2_edc0357_1352328158533_93964_20325.class, "_AddStructuralFeatureValueAction_initialize", arguments141);
                Expression<?>[] arguments142 = new Expression<?>[1];
                arguments142[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition142 = new Expression<Boolean>(_17_0_2_edc0357_1352328158532_578076_20321_exists);
                elaborationRule142 = addElaborationRule(condition142, _17_0_2_edc0357_1352328156644_264215_19617.this, Customer._17_0_2_edc0357_1352328156644_264215_19617._17_0_2_edc0357_1352328158532_578076_20321.class, "_AddStructuralFeatureValueAction_initialize", arguments142);
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

            public Effect effect143 = null;

            public Parameter effect143Var = null;

            public Effect effect144 = null;

            public Parameter effect144Var = null;

            public Effect effect145 = null;

            public Parameter effect145Var = null;

            public ElaborationRule elaborationRule146 = null;

            public void init_17_0_2_edc0357_1352328158533_93964_20325Members() {
                try {
                    if (_17_0_2_edc0357_1352328160564_30011_21453 == null) _17_0_2_edc0357_1352328160564_30011_21453 = new IntegerParameter("_17_0_2_edc0357_1352328160564_30011_21453", (Integer) null, this);
                    if (_17_0_2_edc0357_1352328158533_586682_20327_exists == null) _17_0_2_edc0357_1352328158533_586682_20327_exists = new BooleanParameter("_17_0_2_edc0357_1352328158533_586682_20327_exists", (Boolean) false, this);
                    if (_17_0_2_edc0357_1352328160565_564177_21454 == null) _17_0_2_edc0357_1352328160565_564177_21454 = new Parameter<Customer>("_17_0_2_edc0357_1352328160565_564177_21454", null, (Customer) null, this);
                    if (myDeciderID_decider_17_0_2_edc0357_1352328158533_586682_20327 == null) myDeciderID_decider_17_0_2_edc0357_1352328158533_586682_20327 = new IntegerParameter("myDeciderID_decider_17_0_2_edc0357_1352328158533_586682_20327", (Integer) 2, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect143VarV = sig_17_0_2_edc0357_1352328158534_989901_20336;
                    effect143Var = new Parameter("effect143Var", null, null, this);
                    addDependency(effect143Var, new Expression(effect143VarV));
                    effect143 = new EffectFunction(new FunctionCall(effect143Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect144VarV = decider_17_0_2_edc0357_1352328158533_586682_20327;
                    effect144Var = new Parameter("effect144Var", null, null, this);
                    addDependency(effect144Var, new Expression(effect144VarV));
                    effect144 = new EffectFunction(new FunctionCall(effect144Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, myDeciderID_decider_17_0_2_edc0357_1352328158533_586682_20327 }));
                    Object effect145VarV = cap__17_0_2_edc0357_1352328156659_30024_19625;
                    effect145Var = new Parameter("effect145Var", null, null, this);
                    addDependency(effect145Var, new Expression(effect145VarV));
                    effect145 = new EffectFunction(new FunctionCall(effect145Var, ClassUtils.getMethodForArgTypes("TimeVaryingMap<Integer>", "powersystem", "setValue", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { startTime, _17_0_2_edc0357_1352328160564_30011_21453 }));
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
                Set<Effect> effectsForeffect143Var = new TreeSet<Effect>();
                effectsForeffect143Var.add(effect143);
                addEffects((Parameter<?>) effect143Var, effectsForeffect143Var);
                Set<Effect> effectsForeffect144Var = new TreeSet<Effect>();
                effectsForeffect144Var.add(effect144);
                addEffects((Parameter<?>) effect144Var, effectsForeffect144Var);
                Set<Effect> effectsForeffect145Var = new TreeSet<Effect>();
                effectsForeffect145Var.add(effect145);
                addEffects((Parameter<?>) effect145Var, effectsForeffect145Var);
            }

            public void init_17_0_2_edc0357_1352328158533_93964_20325Dependencies() {
                addDependency(_17_0_2_edc0357_1352328160564_30011_21453, new Expression<Integer>(new FunctionCall(sig_17_0_2_edc0357_1352328158534_643944_20332, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_edc0357_1352328158533_586682_20327_exists, new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158533_586682_20327, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158533_586682_20327, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "maxSize"), new Object[] {})))).functionCall), new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158533_586682_20327, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_edc0357_1352328158533_586682_20327))).functionCall))).functionCall), new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall))).functionCall));
                addDependency(_17_0_2_edc0357_1352328160565_564177_21454, new Expression<Customer>(new FunctionCall(sig_17_0_2_edc0357_1352328158534_844780_20333, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(myDeciderID_decider_17_0_2_edc0357_1352328158533_586682_20327, new Expression<Integer>(2));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(true));
            }

            public void init_17_0_2_edc0357_1352328158533_93964_20325Elaborations() {
                init_17_0_2_edc0357_1352328158533_93964_20325Dependencies();
                Expression<?>[] arguments146 = new Expression<?>[1];
                arguments146[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition146 = new Expression<Boolean>(_17_0_2_edc0357_1352328158533_586682_20327_exists);
                elaborationRule146 = addElaborationRule(condition146, _17_0_2_edc0357_1352328156644_264215_19617.this, Customer._17_0_2_edc0357_1352328156644_264215_19617._17_0_2_edc0357_1352328158533_586682_20327.class, "_JoinNode_initialize", arguments146);
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

            public Effect effect147 = null;

            public Parameter effect147Var = null;

            public Effect effect148 = null;

            public Parameter effect148Var = null;

            public ElaborationRule elaborationRule149 = null;

            public void init_17_0_2_edc0357_1352328158533_893356_20326Members() {
                try {
                    if (myDeciderID_decider_17_0_2_edc0357_1352328158533_93964_20325 == null) myDeciderID_decider_17_0_2_edc0357_1352328158533_93964_20325 = new IntegerParameter("myDeciderID_decider_17_0_2_edc0357_1352328158533_93964_20325", (Integer) 2, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (_17_0_2_edc0357_1352328160565_649347_21456 == null) _17_0_2_edc0357_1352328160565_649347_21456 = new IntegerParameter("_17_0_2_edc0357_1352328160565_649347_21456", (Integer) 0, this);
                    if (_17_0_2_edc0357_1352328158533_93964_20325_exists == null) _17_0_2_edc0357_1352328158533_93964_20325_exists = new BooleanParameter("_17_0_2_edc0357_1352328158533_93964_20325_exists", (Boolean) false, this);
                    Object effect147VarV = sig_17_0_2_edc0357_1352328158534_643944_20332;
                    effect147Var = new Parameter("effect147Var", null, null, this);
                    addDependency(effect147Var, new Expression(effect147VarV));
                    effect147 = new EffectFunction(new FunctionCall(effect147Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { _17_0_2_edc0357_1352328160565_649347_21456, endTime }));
                    Object effect148VarV = decider_17_0_2_edc0357_1352328158533_93964_20325;
                    effect148Var = new Parameter("effect148Var", null, null, this);
                    addDependency(effect148Var, new Expression(effect148VarV));
                    effect148 = new EffectFunction(new FunctionCall(effect148Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, myDeciderID_decider_17_0_2_edc0357_1352328158533_93964_20325 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158533_893356_20326Collections() {
                parameters.add(myDeciderID_decider_17_0_2_edc0357_1352328158533_93964_20325);
                parameters.add(objectToPass);
                parameters.add(_17_0_2_edc0357_1352328160565_649347_21456);
                parameters.add(_17_0_2_edc0357_1352328158533_93964_20325_exists);
                Set<Effect> effectsForeffect147Var = new TreeSet<Effect>();
                effectsForeffect147Var.add(effect147);
                addEffects((Parameter<?>) effect147Var, effectsForeffect147Var);
                Set<Effect> effectsForeffect148Var = new TreeSet<Effect>();
                effectsForeffect148Var.add(effect148);
                addEffects((Parameter<?>) effect148Var, effectsForeffect148Var);
            }

            public void init_17_0_2_edc0357_1352328158533_893356_20326Dependencies() {
                addDependency(myDeciderID_decider_17_0_2_edc0357_1352328158533_93964_20325, new Expression<Integer>(2));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new FunctionCall(sig_17_0_2_edc0357_1352328158535_248302_20340, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_edc0357_1352328160565_649347_21456, new Expression<Integer>(0));
                addDependency(_17_0_2_edc0357_1352328158533_93964_20325_exists, new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158533_93964_20325, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158533_93964_20325, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "maxSize"), new Object[] {})))).functionCall), new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158533_93964_20325, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_edc0357_1352328158533_93964_20325))).functionCall))).functionCall), new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall))).functionCall));
            }

            public void init_17_0_2_edc0357_1352328158533_893356_20326Elaborations() {
                init_17_0_2_edc0357_1352328158533_893356_20326Dependencies();
                Expression<?>[] arguments149 = new Expression<?>[1];
                arguments149[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition149 = new Expression<Boolean>(_17_0_2_edc0357_1352328158533_93964_20325_exists);
                elaborationRule149 = addElaborationRule(condition149, _17_0_2_edc0357_1352328156644_264215_19617.this, Customer._17_0_2_edc0357_1352328156644_264215_19617._17_0_2_edc0357_1352328158533_93964_20325.class, "_AddStructuralFeatureValueAction_initialize", arguments149);
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

            public Effect effect150 = null;

            public Parameter effect150Var = null;

            public void init_17_0_2_edc0357_1352328158533_586682_20327Members() {
                try {
                    if (objectToPass1 == null) objectToPass1 = new BooleanParameter("objectToPass1", (Boolean) null, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect150VarV = sig_17_0_2_edc0357_1352328158534_557121_20335;
                    effect150Var = new Parameter("effect150Var", null, null, this);
                    addDependency(effect150Var, new Expression(effect150VarV));
                    effect150 = new EffectFunction(new FunctionCall(effect150Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158533_586682_20327Collections() {
                parameters.add(objectToPass1);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect150Var = new TreeSet<Effect>();
                effectsForeffect150Var.add(effect150);
                addEffects((Parameter<?>) effect150Var, effectsForeffect150Var);
            }

            public void init_17_0_2_edc0357_1352328158533_586682_20327Dependencies() {
                addDependency(objectToPass1, new Expression<Boolean>(new FunctionCall(sig_17_0_2_edc0357_1352328158534_989901_20336, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(finalNode_startTime, new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new FunctionCall(sig_17_0_2_edc0357_1352328158534_769162_20334, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
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

            public Effect effect151 = null;

            public Parameter effect151Var = null;

            public Effect effect152 = null;

            public Parameter effect152Var = null;

            public Effect effect153 = null;

            public Parameter effect153Var = null;

            public ElaborationRule elaborationRule154 = null;

            public ElaborationRule elaborationRule155 = null;

            public ElaborationRule elaborationRule156 = null;

            public void init_17_0_2_edc0357_1352328158533_797791_20328Members() {
                try {
                    if (_17_0_2_edc0357_1352328158532_777314_20319_exists == null) _17_0_2_edc0357_1352328158532_777314_20319_exists = new BooleanParameter("_17_0_2_edc0357_1352328158532_777314_20319_exists", (Boolean) false, this);
                    if (_17_0_2_edc0357_1352328158532_127805_20320_exists == null) _17_0_2_edc0357_1352328158532_127805_20320_exists = new BooleanParameter("_17_0_2_edc0357_1352328158532_127805_20320_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (_17_0_2_edc0357_1352328158533_893356_20326_exists == null) _17_0_2_edc0357_1352328158533_893356_20326_exists = new BooleanParameter("_17_0_2_edc0357_1352328158533_893356_20326_exists", (Boolean) false, this);
                    Object effect151VarV = sig_17_0_2_edc0357_1352328158535_401408_20338;
                    effect151Var = new Parameter("effect151Var", null, null, this);
                    addDependency(effect151Var, new Expression(effect151VarV));
                    effect151 = new EffectFunction(new FunctionCall(effect151Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect152VarV = sig_17_0_2_edc0357_1352328158535_735383_20339;
                    effect152Var = new Parameter("effect152Var", null, null, this);
                    addDependency(effect152Var, new Expression(effect152VarV));
                    effect152 = new EffectFunction(new FunctionCall(effect152Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect153VarV = sig_17_0_2_edc0357_1352328158535_248302_20340;
                    effect153Var = new Parameter("effect153Var", null, null, this);
                    addDependency(effect153Var, new Expression(effect153VarV));
                    effect153 = new EffectFunction(new FunctionCall(effect153Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158533_797791_20328Collections() {
                parameters.add(_17_0_2_edc0357_1352328158532_777314_20319_exists);
                parameters.add(_17_0_2_edc0357_1352328158532_127805_20320_exists);
                parameters.add(objectToPass);
                parameters.add(_17_0_2_edc0357_1352328158533_893356_20326_exists);
                Set<Effect> effectsForeffect151Var = new TreeSet<Effect>();
                effectsForeffect151Var.add(effect151);
                addEffects((Parameter<?>) effect151Var, effectsForeffect151Var);
                Set<Effect> effectsForeffect152Var = new TreeSet<Effect>();
                effectsForeffect152Var.add(effect152);
                addEffects((Parameter<?>) effect152Var, effectsForeffect152Var);
                Set<Effect> effectsForeffect153Var = new TreeSet<Effect>();
                effectsForeffect153Var.add(effect153);
                addEffects((Parameter<?>) effect153Var, effectsForeffect153Var);
            }

            public void init_17_0_2_edc0357_1352328158533_797791_20328Dependencies() {
                addDependency(_17_0_2_edc0357_1352328158532_777314_20319_exists, new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall));
                addDependency(_17_0_2_edc0357_1352328158532_127805_20320_exists, new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new FunctionCall(sig_17_0_2_edc0357_1352328158535_536324_20337, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_edc0357_1352328158533_893356_20326_exists, new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall));
            }

            public void init_17_0_2_edc0357_1352328158533_797791_20328Elaborations() {
                init_17_0_2_edc0357_1352328158533_797791_20328Dependencies();
                Expression<?>[] arguments154 = new Expression<?>[1];
                arguments154[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition154 = new Expression<Boolean>(_17_0_2_edc0357_1352328158532_127805_20320_exists);
                elaborationRule154 = addElaborationRule(condition154, _17_0_2_edc0357_1352328156644_264215_19617.this, Customer._17_0_2_edc0357_1352328156644_264215_19617._17_0_2_edc0357_1352328158532_127805_20320.class, "_ValueSpecificationAction_initialize", arguments154);
                Expression<?>[] arguments155 = new Expression<?>[1];
                arguments155[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition155 = new Expression<Boolean>(_17_0_2_edc0357_1352328158533_893356_20326_exists);
                elaborationRule155 = addElaborationRule(condition155, _17_0_2_edc0357_1352328156644_264215_19617.this, Customer._17_0_2_edc0357_1352328156644_264215_19617._17_0_2_edc0357_1352328158533_893356_20326.class, "_ValueSpecificationAction_initialize", arguments155);
                Expression<?>[] arguments156 = new Expression<?>[1];
                arguments156[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition156 = new Expression<Boolean>(_17_0_2_edc0357_1352328158532_777314_20319_exists);
                elaborationRule156 = addElaborationRule(condition156, _17_0_2_edc0357_1352328156644_264215_19617.this, Customer._17_0_2_edc0357_1352328156644_264215_19617._17_0_2_edc0357_1352328158532_777314_20319.class, "_ReadSelfAction_initialize", arguments156);
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

        public ElaborationRule elaborationRule124 = null;

        public ElaborationRule elaborationRule125 = null;

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
                if (finalNode_startTime == null) finalNode_startTime = new IntegerParameter("finalNode_startTime", (Integer) 84000, this);
                if (finalNode_endTime == null) finalNode_endTime = new IntegerParameter("finalNode_endTime", (Integer) 84000, this);
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
            addDependency(_17_0_2_edc0357_1352328158533_74852_20323_exists, new Expression<Boolean>(new FunctionCall(sig_17_0_2_edc0357_1352328158534_557121_20335, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>((new Functions.Minus(new Expression<Integer>(finalNode_startTime), new Expression<Integer>(1))).functionCall) })));
            caller.getValue(true).addDependency(caller.getValue(true).endTime, new Expression<Integer>(finalNode_endTime));
            addDependency(endTime, new Expression<Integer>(finalNode_endTime));
        }

        public void init_17_0_2_edc0357_1352328156644_264215_19617Elaborations() {
            init_17_0_2_edc0357_1352328156644_264215_19617Dependencies();
            Expression<?>[] arguments124 = new Expression<?>[1];
            arguments124[0] = new Expression<Integer>(startTime);
            Expression<Boolean> condition124 = new Expression<Boolean>(true);
            elaborationRule124 = addElaborationRule(condition124, _17_0_2_edc0357_1352328156644_264215_19617.this, Customer._17_0_2_edc0357_1352328156644_264215_19617._17_0_2_edc0357_1352328158532_946115_20322.class, "_InitialNode_initialize", arguments124);
            Expression<?>[] arguments125 = new Expression<?>[1];
            arguments125[0] = new Expression<Integer>(finalNode_startTime);
            Expression<Boolean> condition125 = new Expression<Boolean>(_17_0_2_edc0357_1352328158533_74852_20323_exists);
            elaborationRule125 = addElaborationRule(condition125, _17_0_2_edc0357_1352328156644_264215_19617.this, Customer._17_0_2_edc0357_1352328156644_264215_19617._17_0_2_edc0357_1352328158533_74852_20323.class, "_ActivityFinalNode_initialize", arguments125);
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

            public Effect effect160 = null;

            public Parameter effect160Var = null;

            public Effect effect161 = null;

            public Parameter effect161Var = null;

            public Effect effect162 = null;

            public Parameter effect162Var = null;

            public ElaborationRule elaborationRule163 = null;

            public ElaborationRule elaborationRule164 = null;

            public void init_17_0_2_edc0357_1352328158672_866631_20373Members() {
                try {
                    if (_17_0_2_edc0357_1352328160571_533840_21470 == null) _17_0_2_edc0357_1352328160571_533840_21470 = new Parameter<Customer>("_17_0_2_edc0357_1352328160571_533840_21470", null, (Customer) Customer.this, this);
                    if (_17_0_2_edc0357_1352328158675_718110_20377_exists == null) _17_0_2_edc0357_1352328158675_718110_20377_exists = new BooleanParameter("_17_0_2_edc0357_1352328158675_718110_20377_exists", (Boolean) false, this);
                    if (_17_0_2_edc0357_1352328158677_175784_20381_exists == null) _17_0_2_edc0357_1352328158677_175784_20381_exists = new BooleanParameter("_17_0_2_edc0357_1352328158677_175784_20381_exists", (Boolean) false, this);
                    if (myDeciderID_decider_17_0_2_edc0357_1352328158675_718110_20377 == null) myDeciderID_decider_17_0_2_edc0357_1352328158675_718110_20377 = new IntegerParameter("myDeciderID_decider_17_0_2_edc0357_1352328158675_718110_20377", (Integer) 1, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect160VarV = sig_17_0_2_edc0357_1352328158680_29152_20391;
                    effect160Var = new Parameter("effect160Var", null, null, this);
                    addDependency(effect160Var, new Expression(effect160VarV));
                    effect160 = new EffectFunction(new FunctionCall(effect160Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { _17_0_2_edc0357_1352328160571_533840_21470, endTime }));
                    Object effect161VarV = sig_17_0_2_edc0357_1352328158680_247894_20395;
                    effect161Var = new Parameter("effect161Var", null, null, this);
                    addDependency(effect161Var, new Expression(effect161VarV));
                    effect161 = new EffectFunction(new FunctionCall(effect161Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect162VarV = decider_17_0_2_edc0357_1352328158675_718110_20377;
                    effect162Var = new Parameter("effect162Var", null, null, this);
                    addDependency(effect162Var, new Expression(effect162VarV));
                    effect162 = new EffectFunction(new FunctionCall(effect162Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, myDeciderID_decider_17_0_2_edc0357_1352328158675_718110_20377 }));
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
                Set<Effect> effectsForeffect160Var = new TreeSet<Effect>();
                effectsForeffect160Var.add(effect160);
                addEffects((Parameter<?>) effect160Var, effectsForeffect160Var);
                Set<Effect> effectsForeffect161Var = new TreeSet<Effect>();
                effectsForeffect161Var.add(effect161);
                addEffects((Parameter<?>) effect161Var, effectsForeffect161Var);
                Set<Effect> effectsForeffect162Var = new TreeSet<Effect>();
                effectsForeffect162Var.add(effect162);
                addEffects((Parameter<?>) effect162Var, effectsForeffect162Var);
            }

            public void init_17_0_2_edc0357_1352328158672_866631_20373Dependencies() {
                addDependency(_17_0_2_edc0357_1352328158675_718110_20377_exists, new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158675_718110_20377, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158675_718110_20377, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "maxSize"), new Object[] {})))).functionCall), new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158675_718110_20377, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_edc0357_1352328158675_718110_20377))).functionCall))).functionCall), new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall))).functionCall));
                addDependency(_17_0_2_edc0357_1352328158677_175784_20381_exists, new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall));
                addDependency(myDeciderID_decider_17_0_2_edc0357_1352328158675_718110_20377, new Expression<Integer>(1));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new FunctionCall(sig_17_0_2_edc0357_1352328158681_903819_20399, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_edc0357_1352328158672_866631_20373Elaborations() {
                init_17_0_2_edc0357_1352328158672_866631_20373Dependencies();
                Expression<?>[] arguments163 = new Expression<?>[1];
                arguments163[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition163 = new Expression<Boolean>(_17_0_2_edc0357_1352328158675_718110_20377_exists);
                elaborationRule163 = addElaborationRule(condition163, _17_0_2_edc0357_1352328156645_46192_19618.this, Customer._17_0_2_edc0357_1352328156645_46192_19618._17_0_2_edc0357_1352328158675_718110_20377.class, "customerDecideParticipation_DecisionNode_setDRCap", arguments163);
                Expression<?>[] arguments164 = new Expression<?>[1];
                arguments164[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition164 = new Expression<Boolean>(_17_0_2_edc0357_1352328158677_175784_20381_exists);
                elaborationRule164 = addElaborationRule(condition164, _17_0_2_edc0357_1352328156645_46192_19618.this, Customer._17_0_2_edc0357_1352328156645_46192_19618._17_0_2_edc0357_1352328158677_175784_20381.class, "forkNodeReadSelf_ForkNode_setDRCap", arguments164);
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

            public Effect effect165 = null;

            public Parameter effect165Var = null;

            public Effect effect166 = null;

            public Parameter effect166Var = null;

            public Effect effect167 = null;

            public Parameter effect167Var = null;

            public ElaborationRule elaborationRule168 = null;

            public void init_17_0_2_edc0357_1352328158673_69485_20374Members() {
                try {
                    if (_17_0_2_edc0357_1352328160572_153680_21471 == null) _17_0_2_edc0357_1352328160572_153680_21471 = new IntegerParameter("_17_0_2_edc0357_1352328160572_153680_21471", (Integer) null, this);
                    if (_17_0_2_edc0357_1352328160573_976424_21472 == null) _17_0_2_edc0357_1352328160573_976424_21472 = new Parameter<Customer>("_17_0_2_edc0357_1352328160573_976424_21472", null, (Customer) null, this);
                    if (myDeciderID_decider_17_0_2_edc0357_1352328158677_26057_20380 == null) myDeciderID_decider_17_0_2_edc0357_1352328158677_26057_20380 = new IntegerParameter("myDeciderID_decider_17_0_2_edc0357_1352328158677_26057_20380", (Integer) 3, this);
                    if (_17_0_2_edc0357_1352328158677_26057_20380_exists == null) _17_0_2_edc0357_1352328158677_26057_20380_exists = new BooleanParameter("_17_0_2_edc0357_1352328158677_26057_20380_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect165VarV = sig_17_0_2_edc0357_1352328158680_379016_20389;
                    effect165Var = new Parameter("effect165Var", null, null, this);
                    addDependency(effect165Var, new Expression(effect165VarV));
                    effect165 = new EffectFunction(new FunctionCall(effect165Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect166VarV = decider_17_0_2_edc0357_1352328158677_26057_20380;
                    effect166Var = new Parameter("effect166Var", null, null, this);
                    addDependency(effect166Var, new Expression(effect166VarV));
                    effect166 = new EffectFunction(new FunctionCall(effect166Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, myDeciderID_decider_17_0_2_edc0357_1352328158677_26057_20380 }));
                    Object effect167VarV = cap__17_0_2_edc0357_1352328156659_30024_19625;
                    effect167Var = new Parameter("effect167Var", null, null, this);
                    addDependency(effect167Var, new Expression(effect167VarV));
                    effect167 = new EffectFunction(new FunctionCall(effect167Var, ClassUtils.getMethodForArgTypes("TimeVaryingMap<Integer>", "powersystem", "setValue", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { startTime, _17_0_2_edc0357_1352328160572_153680_21471 }));
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

            public void init_17_0_2_edc0357_1352328158673_69485_20374Dependencies() {
                addDependency(_17_0_2_edc0357_1352328160572_153680_21471, new Expression<Integer>(new FunctionCall(sig_17_0_2_edc0357_1352328158680_450048_20388, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_edc0357_1352328160573_976424_21472, new Expression<Customer>(new FunctionCall(sig_17_0_2_edc0357_1352328158680_802723_20392, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(myDeciderID_decider_17_0_2_edc0357_1352328158677_26057_20380, new Expression<Integer>(3));
                addDependency(_17_0_2_edc0357_1352328158677_26057_20380_exists, new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158677_26057_20380, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158677_26057_20380, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "maxSize"), new Object[] {})))).functionCall), new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158677_26057_20380, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_edc0357_1352328158677_26057_20380))).functionCall))).functionCall), new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall))).functionCall));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new FunctionCall(sig_17_0_2_edc0357_1352328158679_596112_20386, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_edc0357_1352328158673_69485_20374Elaborations() {
                init_17_0_2_edc0357_1352328158673_69485_20374Dependencies();
                Expression<?>[] arguments168 = new Expression<?>[1];
                arguments168[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition168 = new Expression<Boolean>(_17_0_2_edc0357_1352328158677_26057_20380_exists);
                elaborationRule168 = addElaborationRule(condition168, _17_0_2_edc0357_1352328156645_46192_19618.this, Customer._17_0_2_edc0357_1352328156645_46192_19618._17_0_2_edc0357_1352328158677_26057_20380.class, "sendSignalYes_SendSignalAction_setDRCap", arguments168);
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

            public Effect effect169 = null;

            public Parameter effect169Var = null;

            public ElaborationRule elaborationRule170 = null;

            public void init_17_0_2_edc0357_1352328158674_234414_20375Members() {
                try {
                    if (_17_0_2_edc0357_1352328158679_514534_20384_exists == null) _17_0_2_edc0357_1352328158679_514534_20384_exists = new BooleanParameter("_17_0_2_edc0357_1352328158679_514534_20384_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect169VarV = sig_17_0_2_edc0357_1352328158681_221195_20398;
                    effect169Var = new Parameter("effect169Var", null, null, this);
                    addDependency(effect169Var, new Expression(effect169VarV));
                    effect169 = new EffectFunction(new FunctionCall(effect169Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158674_234414_20375Collections() {
                parameters.add(_17_0_2_edc0357_1352328158679_514534_20384_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect169Var = new TreeSet<Effect>();
                effectsForeffect169Var.add(effect169);
                addEffects((Parameter<?>) effect169Var, effectsForeffect169Var);
            }

            public void init_17_0_2_edc0357_1352328158674_234414_20375Dependencies() {
                addDependency(_17_0_2_edc0357_1352328158679_514534_20384_exists, new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(true));
            }

            public void init_17_0_2_edc0357_1352328158674_234414_20375Elaborations() {
                init_17_0_2_edc0357_1352328158674_234414_20375Dependencies();
                Expression<?>[] arguments170 = new Expression<?>[1];
                arguments170[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition170 = new Expression<Boolean>(_17_0_2_edc0357_1352328158679_514534_20384_exists);
                elaborationRule170 = addElaborationRule(condition170, _17_0_2_edc0357_1352328156645_46192_19618.this, Customer._17_0_2_edc0357_1352328156645_46192_19618._17_0_2_edc0357_1352328158679_514534_20384.class, "_ForkNode_setDRCap", arguments170);
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
                addDependency(endTime, new Expression<Integer>((new Functions.Plus(new Expression<Integer>(startTime), new Expression<Integer>(duration))).functionCall));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new FunctionCall(sig_17_0_2_edc0357_1352328158681_50786_20403, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
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

            public Effect effect171 = null;

            public Parameter effect171Var = null;

            public Effect effect172 = null;

            public Parameter effect172Var = null;

            public Effect effect173 = null;

            public Parameter effect173Var = null;

            public Effect effect174 = null;

            public Parameter effect174Var = null;

            public ElaborationRule elaborationRule175 = null;

            public ElaborationRule elaborationRule176 = null;

            public void init_17_0_2_edc0357_1352328158675_718110_20377Members() {
                try {
                    if (_17_0_2_edc0357_1352328158675_13172_20378_exists == null) _17_0_2_edc0357_1352328158675_13172_20378_exists = new BooleanParameter("_17_0_2_edc0357_1352328158675_13172_20378_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (decisionInput == null) decisionInput = new BooleanParameter("decisionInput", (Boolean) null, this);
                    if (myDeciderID_decider_17_0_2_edc0357_1352328158673_69485_20374 == null) myDeciderID_decider_17_0_2_edc0357_1352328158673_69485_20374 = new IntegerParameter("myDeciderID_decider_17_0_2_edc0357_1352328158673_69485_20374", (Integer) 3, this);
                    if (_17_0_2_edc0357_1352328158673_69485_20374_exists == null) _17_0_2_edc0357_1352328158673_69485_20374_exists = new BooleanParameter("_17_0_2_edc0357_1352328158673_69485_20374_exists", (Boolean) false, this);
                    if (myDeciderID_decider_17_0_2_edc0357_1352328158675_13172_20378 == null) myDeciderID_decider_17_0_2_edc0357_1352328158675_13172_20378 = new IntegerParameter("myDeciderID_decider_17_0_2_edc0357_1352328158675_13172_20378", (Integer) 2, this);
                    Object effect171VarV = sig_17_0_2_edc0357_1352328158679_596112_20386;
                    effect171Var = new Parameter("effect171Var", null, null, this);
                    addDependency(effect171Var, new Expression(effect171VarV));
                    effect171 = new EffectFunction(new FunctionCall(effect171Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "sendIf", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class, java.lang.Boolean.class), new Object[] { objectToPass, endTime, _17_0_2_edc0357_1352328158673_69485_20374_exists }));
                    Object effect172VarV = sig_17_0_2_edc0357_1352328158679_76145_20387;
                    effect172Var = new Parameter("effect172Var", null, null, this);
                    addDependency(effect172Var, new Expression(effect172VarV));
                    effect172 = new EffectFunction(new FunctionCall(effect172Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "sendIf", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class, java.lang.Boolean.class), new Object[] { objectToPass, endTime, _17_0_2_edc0357_1352328158675_13172_20378_exists }));
                    Object effect173VarV = decider_17_0_2_edc0357_1352328158673_69485_20374;
                    effect173Var = new Parameter("effect173Var", null, null, this);
                    addDependency(effect173Var, new Expression(effect173VarV));
                    effect173 = new EffectFunction(new FunctionCall(effect173Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, myDeciderID_decider_17_0_2_edc0357_1352328158673_69485_20374 }));
                    Object effect174VarV = decider_17_0_2_edc0357_1352328158675_13172_20378;
                    effect174Var = new Parameter("effect174Var", null, null, this);
                    addDependency(effect174Var, new Expression(effect174VarV));
                    effect174 = new EffectFunction(new FunctionCall(effect174Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, myDeciderID_decider_17_0_2_edc0357_1352328158675_13172_20378 }));
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
                Set<Effect> effectsForeffect171Var = new TreeSet<Effect>();
                effectsForeffect171Var.add(effect171);
                addEffects((Parameter<?>) effect171Var, effectsForeffect171Var);
                Set<Effect> effectsForeffect172Var = new TreeSet<Effect>();
                effectsForeffect172Var.add(effect172);
                addEffects((Parameter<?>) effect172Var, effectsForeffect172Var);
                Set<Effect> effectsForeffect173Var = new TreeSet<Effect>();
                effectsForeffect173Var.add(effect173);
                addEffects((Parameter<?>) effect173Var, effectsForeffect173Var);
                Set<Effect> effectsForeffect174Var = new TreeSet<Effect>();
                effectsForeffect174Var.add(effect174);
                addEffects((Parameter<?>) effect174Var, effectsForeffect174Var);
            }

            public void init_17_0_2_edc0357_1352328158675_718110_20377Dependencies() {
                addDependency(duration, new Expression<Integer>(1));
                addDependency(decisionInput, new Expression<Boolean>(new FunctionCall(sig_17_0_2_edc0357_1352328158681_26576_20397, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_edc0357_1352328158673_69485_20374_exists, new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.And(new Expression<Boolean>(decisionInput), new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158673_69485_20374, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158673_69485_20374, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "maxSize"), new Object[] {})))).functionCall))).functionCall), new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158673_69485_20374, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_edc0357_1352328158673_69485_20374))).functionCall))).functionCall), new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall))).functionCall));
                addDependency(myDeciderID_decider_17_0_2_edc0357_1352328158673_69485_20374, new Expression<Integer>(3));
                addDependency(_17_0_2_edc0357_1352328158675_13172_20378_exists, new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.And(new Functions.Not(new Expression<Boolean>(decisionInput)), new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158675_13172_20378, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158675_13172_20378, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "maxSize"), new Object[] {})))).functionCall))).functionCall), new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158675_13172_20378, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_edc0357_1352328158675_13172_20378))).functionCall))).functionCall), new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall))).functionCall));
                addDependency(objectToPass, new Expression<Boolean>(new FunctionCall(sig_17_0_2_edc0357_1352328158680_247894_20395, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(myDeciderID_decider_17_0_2_edc0357_1352328158675_13172_20378, new Expression<Integer>(2));
            }

            public void init_17_0_2_edc0357_1352328158675_718110_20377Elaborations() {
                init_17_0_2_edc0357_1352328158675_718110_20377Dependencies();
                Expression<?>[] arguments175 = new Expression<?>[1];
                arguments175[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition175 = new Expression<Boolean>(_17_0_2_edc0357_1352328158673_69485_20374_exists);
                elaborationRule175 = addElaborationRule(condition175, _17_0_2_edc0357_1352328156645_46192_19618.this, Customer._17_0_2_edc0357_1352328156645_46192_19618._17_0_2_edc0357_1352328158673_69485_20374.class, "addStructuralFeatureCap_AddStructuralFeatureValueAction_setDRCap", arguments175);
                Expression<?>[] arguments176 = new Expression<?>[1];
                arguments176[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition176 = new Expression<Boolean>(_17_0_2_edc0357_1352328158675_13172_20378_exists);
                elaborationRule176 = addElaborationRule(condition176, _17_0_2_edc0357_1352328156645_46192_19618.this, Customer._17_0_2_edc0357_1352328156645_46192_19618._17_0_2_edc0357_1352328158675_13172_20378.class, "sendSignalNo_SendSignalAction_setDRCap", arguments176);
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

            public Dependency< Boolean > _17_0_2_edc0357_1352328158679_407855_20385_existsDependency = null;

            public Dependency< Customer > _17_0_2_edc0357_1352328160573_64130_21473Dependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Effect effect177 = null;

            public Parameter effect177Var = null;

            public Effect effect178 = null;

            public Parameter effect178Var = null;

            public ElaborationRule elaborationRule179 = null;

            public void init_17_0_2_edc0357_1352328158675_13172_20378Members() {
                try {
                    if (_17_0_2_edc0357_1352328158679_407855_20385_exists == null) _17_0_2_edc0357_1352328158679_407855_20385_exists = new BooleanParameter("_17_0_2_edc0357_1352328158679_407855_20385_exists", (Boolean) false, this);
                    if (_17_0_2_edc0357_1352328160573_64130_21473 == null) _17_0_2_edc0357_1352328160573_64130_21473 = new Parameter<Customer>("_17_0_2_edc0357_1352328160573_64130_21473", null, (Customer) null, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect177VarV = sig_17_0_2_edc0357_1352328158681_384297_20401;
                    effect177Var = new Parameter("effect177Var", null, null, this);
                    addDependency(effect177Var, new Expression(effect177VarV));
                    effect177 = new EffectFunction(new FunctionCall(effect177Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect178VarV = new FunctionCall(x, Parameter.class, "getMember", new Object[] { "ss_17_0_2_edc0357_1352328156651_739611_19623_no" });
                    effect178Var = new Parameter("effect178Var", null, null, this);
                    addDependency(effect178Var, new Expression(effect178VarV));
                    effect178 = new EffectFunction(new FunctionCall(null, ClassUtils.getMethodForArgTypes("ObjectFlow<Signalno>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { x.getValue().new Signalno(endTime, true), endTime }, effect178Var));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158675_13172_20378Collections() {
                parameters.add(_17_0_2_edc0357_1352328158679_407855_20385_exists);
                parameters.add(_17_0_2_edc0357_1352328160573_64130_21473);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect177Var = new TreeSet<Effect>();
                effectsForeffect177Var.add(effect177);
                addEffects((Parameter<?>) effect177Var, effectsForeffect177Var);
                Set<Effect> effectsForeffect178Var = new TreeSet<Effect>();
                effectsForeffect178Var.add(effect178);
                addEffects((Parameter<?>) effect178Var, effectsForeffect178Var);
            }

            public void init_17_0_2_edc0357_1352328158675_13172_20378Dependencies() {
                addDependency(_17_0_2_edc0357_1352328158679_407855_20385_exists, new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall));
                addDependency(_17_0_2_edc0357_1352328160573_64130_21473, new Expression<Customer>(new FunctionCall(sig_17_0_2_edc0357_1352328158680_928200_20394, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new FunctionCall(sig_17_0_2_edc0357_1352328158679_76145_20387, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_edc0357_1352328158675_13172_20378Elaborations() {
                init_17_0_2_edc0357_1352328158675_13172_20378Dependencies();
                Expression<?>[] arguments179 = new Expression<?>[2];
                arguments179[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                arguments179[1] = new Expression<ObjectFlow<Boolean>>(sig_17_0_2_edc0357_1352328158681_384297_20401);
                Expression<Boolean> condition179 = new Expression<Boolean>(_17_0_2_edc0357_1352328158679_407855_20385_exists);
                elaborationRule179 = addElaborationRule(condition179, _17_0_2_edc0357_1352328156645_46192_19618.this, Customer._17_0_2_edc0357_1352328156645_46192_19618._17_0_2_edc0357_1352328158679_407855_20385.class, "_MergeNode_setDRCap", arguments179);
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

            public void init_17_0_2_edc0357_1352328158676_652582_20379Members() {
                try {
                    if (myDeciderID_decider_17_0_2_edc0357_1352328158677_26057_20380 == null) myDeciderID_decider_17_0_2_edc0357_1352328158677_26057_20380 = new IntegerParameter("myDeciderID_decider_17_0_2_edc0357_1352328158677_26057_20380", (Integer) 1, this);
                    if (_17_0_2_edc0357_1352328158677_26057_20380_exists == null) _17_0_2_edc0357_1352328158677_26057_20380_exists = new BooleanParameter("_17_0_2_edc0357_1352328158677_26057_20380_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new IntegerParameter("objectToPass", (Integer) null, this);
                    if (myDeciderID_decider_17_0_2_edc0357_1352328158673_69485_20374 == null) myDeciderID_decider_17_0_2_edc0357_1352328158673_69485_20374 = new IntegerParameter("myDeciderID_decider_17_0_2_edc0357_1352328158673_69485_20374", (Integer) 2, this);
                    if (_17_0_2_edc0357_1352328158673_69485_20374_exists == null) _17_0_2_edc0357_1352328158673_69485_20374_exists = new BooleanParameter("_17_0_2_edc0357_1352328158673_69485_20374_exists", (Boolean) false, this);
                    Object effect180VarV = sig_17_0_2_edc0357_1352328158680_450048_20388;
                    effect180Var = new Parameter("effect180Var", null, null, this);
                    addDependency(effect180Var, new Expression(effect180VarV));
                    effect180 = new EffectFunction(new FunctionCall(effect180Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect181VarV = sig_17_0_2_edc0357_1352328158680_232103_20390;
                    effect181Var = new Parameter("effect181Var", null, null, this);
                    addDependency(effect181Var, new Expression(effect181VarV));
                    effect181 = new EffectFunction(new FunctionCall(effect181Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect182VarV = decider_17_0_2_edc0357_1352328158673_69485_20374;
                    effect182Var = new Parameter("effect182Var", null, null, this);
                    addDependency(effect182Var, new Expression(effect182VarV));
                    effect182 = new EffectFunction(new FunctionCall(effect182Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, myDeciderID_decider_17_0_2_edc0357_1352328158673_69485_20374 }));
                    Object effect183VarV = decider_17_0_2_edc0357_1352328158677_26057_20380;
                    effect183Var = new Parameter("effect183Var", null, null, this);
                    addDependency(effect183Var, new Expression(effect183VarV));
                    effect183 = new EffectFunction(new FunctionCall(effect183Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, myDeciderID_decider_17_0_2_edc0357_1352328158677_26057_20380 }));
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

            public void init_17_0_2_edc0357_1352328158676_652582_20379Dependencies() {
                addDependency(myDeciderID_decider_17_0_2_edc0357_1352328158677_26057_20380, new Expression<Integer>(1));
                addDependency(_17_0_2_edc0357_1352328158677_26057_20380_exists, new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158677_26057_20380, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158677_26057_20380, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "maxSize"), new Object[] {})))).functionCall), new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158677_26057_20380, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_edc0357_1352328158677_26057_20380))).functionCall))).functionCall), new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall))).functionCall));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Integer>(new FunctionCall(sig_17_0_2_edc0357_1352328158681_29423_20396, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_edc0357_1352328158673_69485_20374_exists, new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158673_69485_20374, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158673_69485_20374, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "maxSize"), new Object[] {})))).functionCall), new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158673_69485_20374, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_edc0357_1352328158673_69485_20374))).functionCall))).functionCall), new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall))).functionCall));
                addDependency(myDeciderID_decider_17_0_2_edc0357_1352328158673_69485_20374, new Expression<Integer>(2));
            }

            public void init_17_0_2_edc0357_1352328158676_652582_20379Elaborations() {
                init_17_0_2_edc0357_1352328158676_652582_20379Dependencies();
                Expression<?>[] arguments184 = new Expression<?>[1];
                arguments184[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition184 = new Expression<Boolean>(_17_0_2_edc0357_1352328158673_69485_20374_exists);
                elaborationRule184 = addElaborationRule(condition184, _17_0_2_edc0357_1352328156645_46192_19618.this, Customer._17_0_2_edc0357_1352328156645_46192_19618._17_0_2_edc0357_1352328158673_69485_20374.class, "addStructuralFeatureCap_AddStructuralFeatureValueAction_setDRCap", arguments184);
                Expression<?>[] arguments185 = new Expression<?>[1];
                arguments185[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition185 = new Expression<Boolean>(_17_0_2_edc0357_1352328158677_26057_20380_exists);
                elaborationRule185 = addElaborationRule(condition185, _17_0_2_edc0357_1352328156645_46192_19618.this, Customer._17_0_2_edc0357_1352328156645_46192_19618._17_0_2_edc0357_1352328158677_26057_20380.class, "sendSignalYes_SendSignalAction_setDRCap", arguments185);
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

            public Dependency< Customer > _17_0_2_edc0357_1352328160574_879011_21474Dependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158679_407855_20385_existsDependency = null;

            public Dependency< Integer > _17_0_2_edc0357_1352328160575_146405_21475Dependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Effect effect186 = null;

            public Parameter effect186Var = null;

            public Effect effect187 = null;

            public Parameter effect187Var = null;

            public ElaborationRule elaborationRule188 = null;

            public void init_17_0_2_edc0357_1352328158677_26057_20380Members() {
                try {
                    if (_17_0_2_edc0357_1352328160574_879011_21474 == null) _17_0_2_edc0357_1352328160574_879011_21474 = new Parameter<Customer>("_17_0_2_edc0357_1352328160574_879011_21474", null, (Customer) null, this);
                    if (_17_0_2_edc0357_1352328158679_407855_20385_exists == null) _17_0_2_edc0357_1352328158679_407855_20385_exists = new BooleanParameter("_17_0_2_edc0357_1352328158679_407855_20385_exists", (Boolean) false, this);
                    if (_17_0_2_edc0357_1352328160575_146405_21475 == null) _17_0_2_edc0357_1352328160575_146405_21475 = new IntegerParameter("_17_0_2_edc0357_1352328160575_146405_21475", (Integer) null, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect186VarV = sig_17_0_2_edc0357_1352328158681_60367_20402;
                    effect186Var = new Parameter("effect186Var", null, null, this);
                    addDependency(effect186Var, new Expression(effect186VarV));
                    effect186 = new EffectFunction(new FunctionCall(effect186Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect187VarV = new FunctionCall(x, Parameter.class, "getMember", new Object[] { "ss_17_0_2_edc0357_1352328156651_739611_19623_yes" });
                    effect187Var = new Parameter("effect187Var", null, null, this);
                    addDependency(effect187Var, new Expression(effect187VarV));
                    effect187 = new EffectFunction(new FunctionCall(null, ClassUtils.getMethodForArgTypes("ObjectFlow<Signalyes>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { x.getValue().new Signalyes(endTime, _17_0_2_edc0357_1352328160575_146405_21475.getValue()), endTime }, effect187Var));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158677_26057_20380Collections() {
                parameters.add(_17_0_2_edc0357_1352328160574_879011_21474);
                parameters.add(_17_0_2_edc0357_1352328158679_407855_20385_exists);
                parameters.add(_17_0_2_edc0357_1352328160575_146405_21475);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect186Var = new TreeSet<Effect>();
                effectsForeffect186Var.add(effect186);
                addEffects((Parameter<?>) effect186Var, effectsForeffect186Var);
                Set<Effect> effectsForeffect187Var = new TreeSet<Effect>();
                effectsForeffect187Var.add(effect187);
                addEffects((Parameter<?>) effect187Var, effectsForeffect187Var);
            }

            public void init_17_0_2_edc0357_1352328158677_26057_20380Dependencies() {
                addDependency(_17_0_2_edc0357_1352328160574_879011_21474, new Expression<Customer>(new FunctionCall(sig_17_0_2_edc0357_1352328158680_930496_20393, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_edc0357_1352328158679_407855_20385_exists, new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall));
                addDependency(_17_0_2_edc0357_1352328160575_146405_21475, new Expression<Integer>(new FunctionCall(sig_17_0_2_edc0357_1352328158680_232103_20390, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new FunctionCall(sig_17_0_2_edc0357_1352328158680_379016_20389, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_edc0357_1352328158677_26057_20380Elaborations() {
                init_17_0_2_edc0357_1352328158677_26057_20380Dependencies();
                Expression<?>[] arguments188 = new Expression<?>[2];
                arguments188[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                arguments188[1] = new Expression<ObjectFlow<Boolean>>(sig_17_0_2_edc0357_1352328158681_60367_20402);
                Expression<Boolean> condition188 = new Expression<Boolean>(_17_0_2_edc0357_1352328158679_407855_20385_exists);
                elaborationRule188 = addElaborationRule(condition188, _17_0_2_edc0357_1352328156645_46192_19618.this, Customer._17_0_2_edc0357_1352328156645_46192_19618._17_0_2_edc0357_1352328158679_407855_20385.class, "_MergeNode_setDRCap", arguments188);
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

            public Effect effect189 = null;

            public Parameter effect189Var = null;

            public Effect effect190 = null;

            public Parameter effect190Var = null;

            public Effect effect191 = null;

            public Parameter effect191Var = null;

            public Effect effect192 = null;

            public Parameter effect192Var = null;

            public Effect effect193 = null;

            public Parameter effect193Var = null;

            public Effect effect194 = null;

            public Parameter effect194Var = null;

            public ElaborationRule elaborationRule195 = null;

            public ElaborationRule elaborationRule196 = null;

            public ElaborationRule elaborationRule197 = null;

            public void init_17_0_2_edc0357_1352328158677_175784_20381Members() {
                try {
                    if (myDeciderID_decider_17_0_2_edc0357_1352328158677_26057_20380 == null) myDeciderID_decider_17_0_2_edc0357_1352328158677_26057_20380 = new IntegerParameter("myDeciderID_decider_17_0_2_edc0357_1352328158677_26057_20380", (Integer) 2, this);
                    if (_17_0_2_edc0357_1352328158675_13172_20378_exists == null) _17_0_2_edc0357_1352328158675_13172_20378_exists = new BooleanParameter("_17_0_2_edc0357_1352328158675_13172_20378_exists", (Boolean) false, this);
                    if (_17_0_2_edc0357_1352328158677_26057_20380_exists == null) _17_0_2_edc0357_1352328158677_26057_20380_exists = new BooleanParameter("_17_0_2_edc0357_1352328158677_26057_20380_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new Parameter<Customer>("objectToPass", null, (Customer) null, this);
                    if (myDeciderID_decider_17_0_2_edc0357_1352328158673_69485_20374 == null) myDeciderID_decider_17_0_2_edc0357_1352328158673_69485_20374 = new IntegerParameter("myDeciderID_decider_17_0_2_edc0357_1352328158673_69485_20374", (Integer) 1, this);
                    if (_17_0_2_edc0357_1352328158673_69485_20374_exists == null) _17_0_2_edc0357_1352328158673_69485_20374_exists = new BooleanParameter("_17_0_2_edc0357_1352328158673_69485_20374_exists", (Boolean) false, this);
                    if (myDeciderID_decider_17_0_2_edc0357_1352328158675_13172_20378 == null) myDeciderID_decider_17_0_2_edc0357_1352328158675_13172_20378 = new IntegerParameter("myDeciderID_decider_17_0_2_edc0357_1352328158675_13172_20378", (Integer) 1, this);
                    Object effect189VarV = sig_17_0_2_edc0357_1352328158680_802723_20392;
                    effect189Var = new Parameter("effect189Var", null, null, this);
                    addDependency(effect189Var, new Expression(effect189VarV));
                    effect189 = new EffectFunction(new FunctionCall(effect189Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect190VarV = sig_17_0_2_edc0357_1352328158680_930496_20393;
                    effect190Var = new Parameter("effect190Var", null, null, this);
                    addDependency(effect190Var, new Expression(effect190VarV));
                    effect190 = new EffectFunction(new FunctionCall(effect190Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect191VarV = sig_17_0_2_edc0357_1352328158680_928200_20394;
                    effect191Var = new Parameter("effect191Var", null, null, this);
                    addDependency(effect191Var, new Expression(effect191VarV));
                    effect191 = new EffectFunction(new FunctionCall(effect191Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect192VarV = decider_17_0_2_edc0357_1352328158673_69485_20374;
                    effect192Var = new Parameter("effect192Var", null, null, this);
                    addDependency(effect192Var, new Expression(effect192VarV));
                    effect192 = new EffectFunction(new FunctionCall(effect192Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, myDeciderID_decider_17_0_2_edc0357_1352328158673_69485_20374 }));
                    Object effect193VarV = decider_17_0_2_edc0357_1352328158677_26057_20380;
                    effect193Var = new Parameter("effect193Var", null, null, this);
                    addDependency(effect193Var, new Expression(effect193VarV));
                    effect193 = new EffectFunction(new FunctionCall(effect193Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, myDeciderID_decider_17_0_2_edc0357_1352328158677_26057_20380 }));
                    Object effect194VarV = decider_17_0_2_edc0357_1352328158675_13172_20378;
                    effect194Var = new Parameter("effect194Var", null, null, this);
                    addDependency(effect194Var, new Expression(effect194VarV));
                    effect194 = new EffectFunction(new FunctionCall(effect194Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, myDeciderID_decider_17_0_2_edc0357_1352328158675_13172_20378 }));
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
                Set<Effect> effectsForeffect189Var = new TreeSet<Effect>();
                effectsForeffect189Var.add(effect189);
                addEffects((Parameter<?>) effect189Var, effectsForeffect189Var);
                Set<Effect> effectsForeffect190Var = new TreeSet<Effect>();
                effectsForeffect190Var.add(effect190);
                addEffects((Parameter<?>) effect190Var, effectsForeffect190Var);
                Set<Effect> effectsForeffect191Var = new TreeSet<Effect>();
                effectsForeffect191Var.add(effect191);
                addEffects((Parameter<?>) effect191Var, effectsForeffect191Var);
                Set<Effect> effectsForeffect192Var = new TreeSet<Effect>();
                effectsForeffect192Var.add(effect192);
                addEffects((Parameter<?>) effect192Var, effectsForeffect192Var);
                Set<Effect> effectsForeffect193Var = new TreeSet<Effect>();
                effectsForeffect193Var.add(effect193);
                addEffects((Parameter<?>) effect193Var, effectsForeffect193Var);
                Set<Effect> effectsForeffect194Var = new TreeSet<Effect>();
                effectsForeffect194Var.add(effect194);
                addEffects((Parameter<?>) effect194Var, effectsForeffect194Var);
            }

            public void init_17_0_2_edc0357_1352328158677_175784_20381Dependencies() {
                addDependency(myDeciderID_decider_17_0_2_edc0357_1352328158677_26057_20380, new Expression<Integer>(2));
                addDependency(_17_0_2_edc0357_1352328158675_13172_20378_exists, new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158675_13172_20378, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158675_13172_20378, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "maxSize"), new Object[] {})))).functionCall), new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158675_13172_20378, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_edc0357_1352328158675_13172_20378))).functionCall))).functionCall), new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall))).functionCall));
                addDependency(_17_0_2_edc0357_1352328158677_26057_20380_exists, new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158677_26057_20380, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158677_26057_20380, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "maxSize"), new Object[] {})))).functionCall), new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158677_26057_20380, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_edc0357_1352328158677_26057_20380))).functionCall))).functionCall), new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall))).functionCall));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Customer>(new FunctionCall(sig_17_0_2_edc0357_1352328158680_29152_20391, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_edc0357_1352328158673_69485_20374_exists, new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158673_69485_20374, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158673_69485_20374, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "maxSize"), new Object[] {})))).functionCall), new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158673_69485_20374, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_edc0357_1352328158673_69485_20374))).functionCall))).functionCall), new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall))).functionCall));
                addDependency(myDeciderID_decider_17_0_2_edc0357_1352328158673_69485_20374, new Expression<Integer>(1));
                addDependency(myDeciderID_decider_17_0_2_edc0357_1352328158675_13172_20378, new Expression<Integer>(1));
            }

            public void init_17_0_2_edc0357_1352328158677_175784_20381Elaborations() {
                init_17_0_2_edc0357_1352328158677_175784_20381Dependencies();
                Expression<?>[] arguments195 = new Expression<?>[1];
                arguments195[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition195 = new Expression<Boolean>(_17_0_2_edc0357_1352328158673_69485_20374_exists);
                elaborationRule195 = addElaborationRule(condition195, _17_0_2_edc0357_1352328156645_46192_19618.this, Customer._17_0_2_edc0357_1352328156645_46192_19618._17_0_2_edc0357_1352328158673_69485_20374.class, "addStructuralFeatureCap_AddStructuralFeatureValueAction_setDRCap", arguments195);
                Expression<?>[] arguments196 = new Expression<?>[1];
                arguments196[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition196 = new Expression<Boolean>(_17_0_2_edc0357_1352328158677_26057_20380_exists);
                elaborationRule196 = addElaborationRule(condition196, _17_0_2_edc0357_1352328156645_46192_19618.this, Customer._17_0_2_edc0357_1352328156645_46192_19618._17_0_2_edc0357_1352328158677_26057_20380.class, "sendSignalYes_SendSignalAction_setDRCap", arguments196);
                Expression<?>[] arguments197 = new Expression<?>[1];
                arguments197[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition197 = new Expression<Boolean>(_17_0_2_edc0357_1352328158675_13172_20378_exists);
                elaborationRule197 = addElaborationRule(condition197, _17_0_2_edc0357_1352328156645_46192_19618.this, Customer._17_0_2_edc0357_1352328156645_46192_19618._17_0_2_edc0357_1352328158675_13172_20378.class, "sendSignalNo_SendSignalAction_setDRCap", arguments197);
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

            public Effect effect198 = null;

            public Parameter effect198Var = null;

            public ElaborationRule elaborationRule199 = null;

            public void init_17_0_2_edc0357_1352328158678_297502_20382Members() {
                try {
                    if (_17_0_2_edc0357_1352328158535_616746_20341 == null) _17_0_2_edc0357_1352328158535_616746_20341 = new IntegerParameter("_17_0_2_edc0357_1352328158535_616746_20341", (Integer) null, this);
                    if (_17_0_2_edc0357_1352328158676_652582_20379_exists == null) _17_0_2_edc0357_1352328158676_652582_20379_exists = new BooleanParameter("_17_0_2_edc0357_1352328158676_652582_20379_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new IntegerParameter("objectToPass", (Integer) null, this);
                    Object effect198VarV = sig_17_0_2_edc0357_1352328158681_29423_20396;
                    effect198Var = new Parameter("effect198Var", null, null, this);
                    addDependency(effect198Var, new Expression(effect198VarV));
                    effect198 = new EffectFunction(new FunctionCall(effect198Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158678_297502_20382Collections() {
                parameters.add(_17_0_2_edc0357_1352328158535_616746_20341);
                parameters.add(_17_0_2_edc0357_1352328158676_652582_20379_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect198Var = new TreeSet<Effect>();
                effectsForeffect198Var.add(effect198);
                addEffects((Parameter<?>) effect198Var, effectsForeffect198Var);
            }

            public void init_17_0_2_edc0357_1352328158678_297502_20382Dependencies() {
                addDependency(_17_0_2_edc0357_1352328158676_652582_20379_exists, new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall));
                addDependency(objectToPass, new Expression<Integer>(_17_0_2_edc0357_1352328158535_616746_20341));
                addDependency(duration, new Expression<Integer>(1));
            }

            public void init_17_0_2_edc0357_1352328158678_297502_20382Elaborations() {
                init_17_0_2_edc0357_1352328158678_297502_20382Dependencies();
                Expression<?>[] arguments199 = new Expression<?>[1];
                arguments199[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition199 = new Expression<Boolean>(_17_0_2_edc0357_1352328158676_652582_20379_exists);
                elaborationRule199 = addElaborationRule(condition199, _17_0_2_edc0357_1352328156645_46192_19618.this, Customer._17_0_2_edc0357_1352328156645_46192_19618._17_0_2_edc0357_1352328158676_652582_20379.class, "forkNodeLastSignalValue_ForkNode_setDRCap", arguments199);
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

            public Effect effect200 = null;

            public Parameter effect200Var = null;

            public Effect effect201 = null;

            public Parameter effect201Var = null;

            public ElaborationRule elaborationRule202 = null;

            public void init_17_0_2_edc0357_1352328158678_378610_20383Members() {
                try {
                    if (_17_0_2_edc0357_1352328160575_575843_21477 == null) _17_0_2_edc0357_1352328160575_575843_21477 = new BooleanParameter("_17_0_2_edc0357_1352328160575_575843_21477", (Boolean) true, this);
                    if (_17_0_2_edc0357_1352328158675_718110_20377_exists == null) _17_0_2_edc0357_1352328158675_718110_20377_exists = new BooleanParameter("_17_0_2_edc0357_1352328158675_718110_20377_exists", (Boolean) false, this);
                    if (myDeciderID_decider_17_0_2_edc0357_1352328158675_718110_20377 == null) myDeciderID_decider_17_0_2_edc0357_1352328158675_718110_20377 = new IntegerParameter("myDeciderID_decider_17_0_2_edc0357_1352328158675_718110_20377", (Integer) 2, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect200VarV = sig_17_0_2_edc0357_1352328158681_26576_20397;
                    effect200Var = new Parameter("effect200Var", null, null, this);
                    addDependency(effect200Var, new Expression(effect200VarV));
                    effect200 = new EffectFunction(new FunctionCall(effect200Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { _17_0_2_edc0357_1352328160575_575843_21477, endTime }));
                    Object effect201VarV = decider_17_0_2_edc0357_1352328158675_718110_20377;
                    effect201Var = new Parameter("effect201Var", null, null, this);
                    addDependency(effect201Var, new Expression(effect201VarV));
                    effect201 = new EffectFunction(new FunctionCall(effect201Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, myDeciderID_decider_17_0_2_edc0357_1352328158675_718110_20377 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158678_378610_20383Collections() {
                parameters.add(_17_0_2_edc0357_1352328160575_575843_21477);
                parameters.add(_17_0_2_edc0357_1352328158675_718110_20377_exists);
                parameters.add(myDeciderID_decider_17_0_2_edc0357_1352328158675_718110_20377);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect200Var = new TreeSet<Effect>();
                effectsForeffect200Var.add(effect200);
                addEffects((Parameter<?>) effect200Var, effectsForeffect200Var);
                Set<Effect> effectsForeffect201Var = new TreeSet<Effect>();
                effectsForeffect201Var.add(effect201);
                addEffects((Parameter<?>) effect201Var, effectsForeffect201Var);
            }

            public void init_17_0_2_edc0357_1352328158678_378610_20383Dependencies() {
                addDependency(_17_0_2_edc0357_1352328160575_575843_21477, new Expression<Boolean>(true));
                addDependency(_17_0_2_edc0357_1352328158675_718110_20377_exists, new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158675_718110_20377, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158675_718110_20377, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "maxSize"), new Object[] {})))).functionCall), new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328158675_718110_20377, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_edc0357_1352328158675_718110_20377))).functionCall))).functionCall), new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall))).functionCall));
                addDependency(myDeciderID_decider_17_0_2_edc0357_1352328158675_718110_20377, new Expression<Integer>(2));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new FunctionCall(sig_17_0_2_edc0357_1352328158681_489777_20400, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_edc0357_1352328158678_378610_20383Elaborations() {
                init_17_0_2_edc0357_1352328158678_378610_20383Dependencies();
                Expression<?>[] arguments202 = new Expression<?>[1];
                arguments202[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition202 = new Expression<Boolean>(_17_0_2_edc0357_1352328158675_718110_20377_exists);
                elaborationRule202 = addElaborationRule(condition202, _17_0_2_edc0357_1352328156645_46192_19618.this, Customer._17_0_2_edc0357_1352328156645_46192_19618._17_0_2_edc0357_1352328158675_718110_20377.class, "customerDecideParticipation_DecisionNode_setDRCap", arguments202);
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

            public Effect effect203 = null;

            public Parameter effect203Var = null;

            public Effect effect204 = null;

            public Parameter effect204Var = null;

            public ElaborationRule elaborationRule205 = null;

            public ElaborationRule elaborationRule206 = null;

            public void init_17_0_2_edc0357_1352328158679_514534_20384Members() {
                try {
                    if (_17_0_2_edc0357_1352328158672_866631_20373_exists == null) _17_0_2_edc0357_1352328158672_866631_20373_exists = new BooleanParameter("_17_0_2_edc0357_1352328158672_866631_20373_exists", (Boolean) false, this);
                    if (_17_0_2_edc0357_1352328158678_378610_20383_exists == null) _17_0_2_edc0357_1352328158678_378610_20383_exists = new BooleanParameter("_17_0_2_edc0357_1352328158678_378610_20383_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect203VarV = sig_17_0_2_edc0357_1352328158681_903819_20399;
                    effect203Var = new Parameter("effect203Var", null, null, this);
                    addDependency(effect203Var, new Expression(effect203VarV));
                    effect203 = new EffectFunction(new FunctionCall(effect203Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect204VarV = sig_17_0_2_edc0357_1352328158681_489777_20400;
                    effect204Var = new Parameter("effect204Var", null, null, this);
                    addDependency(effect204Var, new Expression(effect204VarV));
                    effect204 = new EffectFunction(new FunctionCall(effect204Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158679_514534_20384Collections() {
                parameters.add(_17_0_2_edc0357_1352328158672_866631_20373_exists);
                parameters.add(_17_0_2_edc0357_1352328158678_378610_20383_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect203Var = new TreeSet<Effect>();
                effectsForeffect203Var.add(effect203);
                addEffects((Parameter<?>) effect203Var, effectsForeffect203Var);
                Set<Effect> effectsForeffect204Var = new TreeSet<Effect>();
                effectsForeffect204Var.add(effect204);
                addEffects((Parameter<?>) effect204Var, effectsForeffect204Var);
            }

            public void init_17_0_2_edc0357_1352328158679_514534_20384Dependencies() {
                addDependency(_17_0_2_edc0357_1352328158672_866631_20373_exists, new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall));
                addDependency(_17_0_2_edc0357_1352328158678_378610_20383_exists, new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new FunctionCall(sig_17_0_2_edc0357_1352328158681_221195_20398, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_edc0357_1352328158679_514534_20384Elaborations() {
                init_17_0_2_edc0357_1352328158679_514534_20384Dependencies();
                Expression<?>[] arguments205 = new Expression<?>[1];
                arguments205[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition205 = new Expression<Boolean>(_17_0_2_edc0357_1352328158672_866631_20373_exists);
                elaborationRule205 = addElaborationRule(condition205, _17_0_2_edc0357_1352328156645_46192_19618.this, Customer._17_0_2_edc0357_1352328156645_46192_19618._17_0_2_edc0357_1352328158672_866631_20373.class, "customerReadSelf_ReadSelfAction_setDRCap", arguments205);
                Expression<?>[] arguments206 = new Expression<?>[1];
                arguments206[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition206 = new Expression<Boolean>(_17_0_2_edc0357_1352328158678_378610_20383_exists);
                elaborationRule206 = addElaborationRule(condition206, _17_0_2_edc0357_1352328156645_46192_19618.this, Customer._17_0_2_edc0357_1352328156645_46192_19618._17_0_2_edc0357_1352328158678_378610_20383.class, "custParticip_ValueSpecificationAction_setDRCap", arguments206);
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

            public Effect effect207 = null;

            public Parameter effect207Var = null;

            public void init_17_0_2_edc0357_1352328158679_407855_20385Members() {
                try {
                    if (receiveThis == null) receiveThis = new Parameter<ObjectFlow<Boolean>>("receiveThis", null, (ObjectFlow<Boolean>) null, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect207VarV = sig_17_0_2_edc0357_1352328158681_50786_20403;
                    effect207Var = new Parameter("effect207Var", null, null, this);
                    addDependency(effect207Var, new Expression(effect207VarV));
                    effect207 = new EffectFunction(new FunctionCall(effect207Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158679_407855_20385Collections() {
                parameters.add(receiveThis);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect207Var = new TreeSet<Effect>();
                effectsForeffect207Var.add(effect207);
                addEffects((Parameter<?>) effect207Var, effectsForeffect207Var);
            }

            public void init_17_0_2_edc0357_1352328158679_407855_20385Dependencies() {
                addDependency(finalNode_startTime, new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new FunctionCall(receiveThis, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
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

        public Parameter< ObjectFlow<Integer> > sig_17_0_2_edc0357_1352328158680_232103_20390 = null;

        public Parameter< ObjectFlow<Customer> > sig_17_0_2_edc0357_1352328158680_29152_20391 = null;

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

        public Parameter< ObjectFlow<Integer> > sig_17_0_2_edc0357_1352328158680_450048_20388 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_edc0357_1352328158679_76145_20387 = null;

        public Parameter< TimeVaryingList<Integer> > decider_17_0_2_edc0357_1352328158675_13172_20378 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_edc0357_1352328158681_489777_20400 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_edc0357_1352328158680_247894_20395 = null;

        public IntegerParameter finalNode_startTime = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_edc0357_1352328158681_60367_20402 = null;

        public Parameter< TimeVaryingList<Integer> > decider_17_0_2_edc0357_1352328158675_718110_20377 = null;

        public Parameter< TimeVaryingList<Integer> > decider_17_0_2_edc0357_1352328158673_69485_20374 = null;

        public Parameter< ObjectFlow<Customer> > sig_17_0_2_edc0357_1352328158680_930496_20393 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_edc0357_1352328158681_26576_20397 = null;

        public Dependency< Integer > caller_endTimeDependency = null;

        public Dependency< Integer > endTimeDependency = null;

        public Dependency< Boolean > _17_0_2_edc0357_1352328158674_624952_20376_existsDependency = null;

        public ElaborationRule elaborationRule157 = null;

        public ElaborationRule elaborationRule158 = null;

        public ElaborationRule elaborationRule159 = null;

        public void init_17_0_2_edc0357_1352328156645_46192_19618Members() {
            try {
                if (sig_17_0_2_edc0357_1352328158680_232103_20390 == null) sig_17_0_2_edc0357_1352328158680_232103_20390 = new Parameter<ObjectFlow<Integer>>("sig_17_0_2_edc0357_1352328158680_232103_20390", null, (ObjectFlow<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158680_232103_20390" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328158680_29152_20391 == null) sig_17_0_2_edc0357_1352328158680_29152_20391 = new Parameter<ObjectFlow<Customer>>("sig_17_0_2_edc0357_1352328158680_29152_20391", null, (ObjectFlow<Customer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158680_29152_20391" })).evaluate(true), this);
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
                if (finalNode_endTime == null) finalNode_endTime = new IntegerParameter("finalNode_endTime", (Integer) 84000, this);
                if (sig_17_0_2_edc0357_1352328158680_450048_20388 == null) sig_17_0_2_edc0357_1352328158680_450048_20388 = new Parameter<ObjectFlow<Integer>>("sig_17_0_2_edc0357_1352328158680_450048_20388", null, (ObjectFlow<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158680_450048_20388" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328158679_76145_20387 == null) sig_17_0_2_edc0357_1352328158679_76145_20387 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328158679_76145_20387", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158679_76145_20387" })).evaluate(true), this);
                if (decider_17_0_2_edc0357_1352328158675_13172_20378 == null) decider_17_0_2_edc0357_1352328158675_13172_20378 = new Parameter<TimeVaryingList<Integer>>("decider_17_0_2_edc0357_1352328158675_13172_20378", null, (TimeVaryingList<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingList.class, java.lang.String.class, int.class), new Object[] { "decider_17_0_2_edc0357_1352328158675_13172_20378", 2 })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328158681_489777_20400 == null) sig_17_0_2_edc0357_1352328158681_489777_20400 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328158681_489777_20400", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158681_489777_20400" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328158680_247894_20395 == null) sig_17_0_2_edc0357_1352328158680_247894_20395 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328158680_247894_20395", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158680_247894_20395" })).evaluate(true), this);
                if (finalNode_startTime == null) finalNode_startTime = new IntegerParameter("finalNode_startTime", (Integer) 84000, this);
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
            parameters.add(sig_17_0_2_edc0357_1352328158680_232103_20390);
            parameters.add(sig_17_0_2_edc0357_1352328158680_29152_20391);
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
            parameters.add(sig_17_0_2_edc0357_1352328158680_450048_20388);
            parameters.add(sig_17_0_2_edc0357_1352328158679_76145_20387);
            parameters.add(decider_17_0_2_edc0357_1352328158675_13172_20378);
            parameters.add(sig_17_0_2_edc0357_1352328158681_489777_20400);
            parameters.add(sig_17_0_2_edc0357_1352328158680_247894_20395);
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
            addDependency(_17_0_2_edc0357_1352328158674_624952_20376_exists, new Expression<Boolean>(new FunctionCall(sig_17_0_2_edc0357_1352328158681_50786_20403, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>((new Functions.Minus(new Expression<Integer>(finalNode_startTime), new Expression<Integer>(1))).functionCall) })));
        }

        public void init_17_0_2_edc0357_1352328156645_46192_19618Elaborations() {
            init_17_0_2_edc0357_1352328156645_46192_19618Dependencies();
            Expression<?>[] arguments157 = new Expression<?>[2];
            arguments157[0] = new Expression<Integer>(startTime);
            arguments157[1] = new Expression<Integer>(_17_0_2_edc0357_1352328158535_616746_20341);
            Expression<Boolean> condition157 = new Expression<Boolean>(true);
            elaborationRule157 = addElaborationRule(condition157, _17_0_2_edc0357_1352328156645_46192_19618.this, Customer._17_0_2_edc0357_1352328156645_46192_19618._17_0_2_edc0357_1352328158678_297502_20382.class, "capval_ActivityParameterNode_setDRCap", arguments157);
            Expression<?>[] arguments158 = new Expression<?>[1];
            arguments158[0] = new Expression<Integer>(finalNode_startTime);
            Expression<Boolean> condition158 = new Expression<Boolean>(_17_0_2_edc0357_1352328158674_624952_20376_exists);
            elaborationRule158 = addElaborationRule(condition158, _17_0_2_edc0357_1352328156645_46192_19618.this, Customer._17_0_2_edc0357_1352328156645_46192_19618._17_0_2_edc0357_1352328158674_624952_20376.class, "customerFinal_ActivityFinalNode_setDRCap", arguments158);
            Expression<?>[] arguments159 = new Expression<?>[1];
            arguments159[0] = new Expression<Integer>(startTime);
            Expression<Boolean> condition159 = new Expression<Boolean>(true);
            elaborationRule159 = addElaborationRule(condition159, _17_0_2_edc0357_1352328156645_46192_19618.this, Customer._17_0_2_edc0357_1352328156645_46192_19618._17_0_2_edc0357_1352328158674_234414_20375.class, "customerInitial_InitialNode_setDRCap", arguments159);
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

            public Effect effect210 = null;

            public Parameter effect210Var = null;

            public ElaborationRule elaborationRule211 = null;

            public void init_17_0_2_edc0357_1352328158789_563871_20435Members() {
                try {
                    if (_17_0_2_edc0357_1352328158790_99012_20436_exists == null) _17_0_2_edc0357_1352328158790_99012_20436_exists = new BooleanParameter("_17_0_2_edc0357_1352328158790_99012_20436_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect210VarV = sig_17_0_2_edc0357_1352328158795_231230_20458;
                    effect210Var = new Parameter("effect210Var", null, null, this);
                    addDependency(effect210Var, new Expression(effect210VarV));
                    effect210 = new EffectFunction(new FunctionCall(effect210Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158789_563871_20435Collections() {
                parameters.add(_17_0_2_edc0357_1352328158790_99012_20436_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect210Var = new TreeSet<Effect>();
                effectsForeffect210Var.add(effect210);
                addEffects((Parameter<?>) effect210Var, effectsForeffect210Var);
            }

            public void init_17_0_2_edc0357_1352328158789_563871_20435Dependencies() {
                addDependency(_17_0_2_edc0357_1352328158790_99012_20436_exists, new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(true));
            }

            public void init_17_0_2_edc0357_1352328158789_563871_20435Elaborations() {
                init_17_0_2_edc0357_1352328158789_563871_20435Dependencies();
                Expression<?>[] arguments211 = new Expression<?>[1];
                arguments211[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition211 = new Expression<Boolean>(_17_0_2_edc0357_1352328158790_99012_20436_exists);
                elaborationRule211 = addElaborationRule(condition211, _17_0_2_edc0357_1352328156648_548830_19619.this, Customer._17_0_2_edc0357_1352328156648_548830_19619._17_0_2_edc0357_1352328158790_99012_20436.class, "startup_activities_CallBehaviorAction_CustomerCB", arguments211);
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

            public IntegerParameter duration = null;

            public BooleanParameter objectToPass = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158793_123216_20448_existsDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Effect effect212 = null;

            public Parameter effect212Var = null;

            public ElaborationRule elaborationRule213 = null;

            public ElaborationRule elaborationRule214 = null;

            public void init_17_0_2_edc0357_1352328158790_99012_20436Members() {
                try {
                    if (_17_0_2_edc0357_1352328158793_123216_20448_exists == null) _17_0_2_edc0357_1352328158793_123216_20448_exists = new BooleanParameter("_17_0_2_edc0357_1352328158793_123216_20448_exists", (Boolean) false, this);
                    if (duration == null) duration = new IntegerParameter("duration", (Integer) 45, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect212VarV = sig_17_0_2_edc0357_1352328158796_758419_20470;
                    effect212Var = new Parameter("effect212Var", null, null, this);
                    addDependency(effect212Var, new Expression(effect212VarV));
                    effect212 = new EffectFunction(new FunctionCall(effect212Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158790_99012_20436Collections() {
                parameters.add(_17_0_2_edc0357_1352328158793_123216_20448_exists);
                parameters.add(duration);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect212Var = new TreeSet<Effect>();
                effectsForeffect212Var.add(effect212);
                addEffects((Parameter<?>) effect212Var, effectsForeffect212Var);
            }

            public void init_17_0_2_edc0357_1352328158790_99012_20436Dependencies() {
                addDependency(_17_0_2_edc0357_1352328158793_123216_20448_exists, new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall));
                addDependency(objectToPass, new Expression<Boolean>(new FunctionCall(sig_17_0_2_edc0357_1352328158795_231230_20458, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_edc0357_1352328158790_99012_20436Elaborations() {
                init_17_0_2_edc0357_1352328158790_99012_20436Dependencies();
                Expression<?>[] arguments213 = new Expression<?>[2];
                arguments213[0] = new Expression<Integer>(startTime);
                arguments213[1] = new Expression<DurativeEvent>(this);
                Expression<Boolean> condition213 = new Expression<Boolean>(true);
                elaborationRule213 = addElaborationRule(condition213, Customer.this, Customer._17_0_2_edc0357_1352328156644_264215_19617.class, "initialize_Activity_Customer", arguments213);
                Expression<?>[] arguments214 = new Expression<?>[1];
                arguments214[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition214 = new Expression<Boolean>(_17_0_2_edc0357_1352328158793_123216_20448_exists);
                elaborationRule214 = addElaborationRule(condition214, _17_0_2_edc0357_1352328156648_548830_19619.this, Customer._17_0_2_edc0357_1352328156648_548830_19619._17_0_2_edc0357_1352328158793_123216_20448.class, "_AcceptEventAction_CustomerCB", arguments214);
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

            public Effect effect215 = null;

            public Parameter effect215Var = null;

            public Effect effect216 = null;

            public Parameter effect216Var = null;

            public Effect effect217 = null;

            public Parameter effect217Var = null;

            public Effect effect218 = null;

            public Parameter effect218Var = null;

            public ElaborationRule elaborationRule219 = null;

            public ElaborationRule elaborationRule220 = null;

            public ElaborationRule elaborationRule221 = null;

            public ElaborationRule elaborationRule222 = null;

            public void init_17_0_2_edc0357_1352328158790_493227_20437Members() {
                try {
                    if (_17_0_2_edc0357_1352328158793_331994_20446_exists == null) _17_0_2_edc0357_1352328158793_331994_20446_exists = new BooleanParameter("_17_0_2_edc0357_1352328158793_331994_20446_exists", (Boolean) false, this);
                    if (_17_0_2_edc0357_1352328158792_819832_20444_exists == null) _17_0_2_edc0357_1352328158792_819832_20444_exists = new BooleanParameter("_17_0_2_edc0357_1352328158792_819832_20444_exists", (Boolean) false, this);
                    if (_17_0_2_edc0357_1352328158793_68185_20449_exists == null) _17_0_2_edc0357_1352328158793_68185_20449_exists = new BooleanParameter("_17_0_2_edc0357_1352328158793_68185_20449_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (_17_0_2_edc0357_1352328158793_123095_20447_exists == null) _17_0_2_edc0357_1352328158793_123095_20447_exists = new BooleanParameter("_17_0_2_edc0357_1352328158793_123095_20447_exists", (Boolean) false, this);
                    Object effect215VarV = sig_17_0_2_edc0357_1352328158795_900235_20462;
                    effect215Var = new Parameter("effect215Var", null, null, this);
                    addDependency(effect215Var, new Expression(effect215VarV));
                    effect215 = new EffectFunction(new FunctionCall(effect215Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect216VarV = sig_17_0_2_edc0357_1352328158795_852079_20464;
                    effect216Var = new Parameter("effect216Var", null, null, this);
                    addDependency(effect216Var, new Expression(effect216VarV));
                    effect216 = new EffectFunction(new FunctionCall(effect216Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect217VarV = sig_17_0_2_edc0357_1352328158796_279864_20469;
                    effect217Var = new Parameter("effect217Var", null, null, this);
                    addDependency(effect217Var, new Expression(effect217VarV));
                    effect217 = new EffectFunction(new FunctionCall(effect217Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect218VarV = sig_17_0_2_edc0357_1352328158796_884417_20472;
                    effect218Var = new Parameter("effect218Var", null, null, this);
                    addDependency(effect218Var, new Expression(effect218VarV));
                    effect218 = new EffectFunction(new FunctionCall(effect218Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
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
                Set<Effect> effectsForeffect215Var = new TreeSet<Effect>();
                effectsForeffect215Var.add(effect215);
                addEffects((Parameter<?>) effect215Var, effectsForeffect215Var);
                Set<Effect> effectsForeffect216Var = new TreeSet<Effect>();
                effectsForeffect216Var.add(effect216);
                addEffects((Parameter<?>) effect216Var, effectsForeffect216Var);
                Set<Effect> effectsForeffect217Var = new TreeSet<Effect>();
                effectsForeffect217Var.add(effect217);
                addEffects((Parameter<?>) effect217Var, effectsForeffect217Var);
                Set<Effect> effectsForeffect218Var = new TreeSet<Effect>();
                effectsForeffect218Var.add(effect218);
                addEffects((Parameter<?>) effect218Var, effectsForeffect218Var);
            }

            public void init_17_0_2_edc0357_1352328158790_493227_20437Dependencies() {
                addDependency(_17_0_2_edc0357_1352328158793_331994_20446_exists, new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall));
                addDependency(_17_0_2_edc0357_1352328158792_819832_20444_exists, new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall));
                addDependency(_17_0_2_edc0357_1352328158793_68185_20449_exists, new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new FunctionCall(sig_17_0_2_edc0357_1352328158796_290441_20471, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_edc0357_1352328158793_123095_20447_exists, new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall));
            }

            public void init_17_0_2_edc0357_1352328158790_493227_20437Elaborations() {
                init_17_0_2_edc0357_1352328158790_493227_20437Dependencies();
                Expression<?>[] arguments219 = new Expression<?>[2];
                arguments219[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                arguments219[1] = new Expression<ObjectFlow<Boolean>>(sig_17_0_2_edc0357_1352328158796_279864_20469);
                Expression<Boolean> condition219 = new Expression<Boolean>(_17_0_2_edc0357_1352328158793_123095_20447_exists);
                elaborationRule219 = addElaborationRule(condition219, _17_0_2_edc0357_1352328156648_548830_19619.this, Customer._17_0_2_edc0357_1352328156648_548830_19619._17_0_2_edc0357_1352328158793_123095_20447.class, "_MergeNode_CustomerCB", arguments219);
                Expression<?>[] arguments220 = new Expression<?>[2];
                arguments220[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                arguments220[1] = new Expression<ObjectFlow<Boolean>>(sig_17_0_2_edc0357_1352328158795_852079_20464);
                Expression<Boolean> condition220 = new Expression<Boolean>(_17_0_2_edc0357_1352328158793_331994_20446_exists);
                elaborationRule220 = addElaborationRule(condition220, _17_0_2_edc0357_1352328156648_548830_19619.this, Customer._17_0_2_edc0357_1352328156648_548830_19619._17_0_2_edc0357_1352328158793_331994_20446.class, "_MergeNode_CustomerCB", arguments220);
                Expression<?>[] arguments221 = new Expression<?>[1];
                arguments221[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition221 = new Expression<Boolean>(_17_0_2_edc0357_1352328158793_68185_20449_exists);
                elaborationRule221 = addElaborationRule(condition221, _17_0_2_edc0357_1352328156648_548830_19619.this, Customer._17_0_2_edc0357_1352328156648_548830_19619._17_0_2_edc0357_1352328158793_68185_20449.class, "length_of_test_AcceptEventAction_CustomerCB", arguments221);
                Expression<?>[] arguments222 = new Expression<?>[2];
                arguments222[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                arguments222[1] = new Expression<ObjectFlow<Boolean>>(sig_17_0_2_edc0357_1352328158795_900235_20462);
                Expression<Boolean> condition222 = new Expression<Boolean>(_17_0_2_edc0357_1352328158792_819832_20444_exists);
                elaborationRule222 = addElaborationRule(condition222, _17_0_2_edc0357_1352328156648_548830_19619.this, Customer._17_0_2_edc0357_1352328156648_548830_19619._17_0_2_edc0357_1352328158792_819832_20444.class, "_MergeNode_CustomerCB", arguments222);
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

            public Effect effect223 = null;

            public Parameter effect223Var = null;

            public ElaborationRule elaborationRule224 = null;

            public void init_17_0_2_edc0357_1352328158790_658031_20438Members() {
                try {
                    if (_17_0_2_edc0357_1352328160582_109561_21500 == null) _17_0_2_edc0357_1352328160582_109561_21500 = new Parameter<Power_System.Signaldr_request>("_17_0_2_edc0357_1352328160582_109561_21500", null, (Power_System.Signaldr_request) null, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (_17_0_2_edc0357_1352328158792_72920_20445_exists == null) _17_0_2_edc0357_1352328158792_72920_20445_exists = new BooleanParameter("_17_0_2_edc0357_1352328158792_72920_20445_exists", (Boolean) false, this);
                    Object effect223VarV = sig_17_0_2_edc0357_1352328158795_859697_20465;
                    effect223Var = new Parameter("effect223Var", null, null, this);
                    addDependency(effect223Var, new Expression(effect223VarV));
                    effect223 = new EffectFunction(new FunctionCall(effect223Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Signaldr_request>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { _17_0_2_edc0357_1352328160582_109561_21500, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158790_658031_20438Collections() {
                parameters.add(_17_0_2_edc0357_1352328160582_109561_21500);
                parameters.add(objectToPass);
                parameters.add(_17_0_2_edc0357_1352328158792_72920_20445_exists);
                Set<Effect> effectsForeffect223Var = new TreeSet<Effect>();
                effectsForeffect223Var.add(effect223);
                addEffects((Parameter<?>) effect223Var, effectsForeffect223Var);
            }

            public void init_17_0_2_edc0357_1352328158790_658031_20438Dependencies() {
                addDependency(_17_0_2_edc0357_1352328160582_109561_21500, new Expression<Power_System.Signaldr_request>(new FunctionCall(q_Customer_dr_request, ClassUtils.getMethodForArgTypes("ObjectFlow<Signaldr_request>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new FunctionCall(sig_17_0_2_edc0357_1352328158796_917263_20467, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_edc0357_1352328158792_72920_20445_exists, new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall));
            }

            public void init_17_0_2_edc0357_1352328158790_658031_20438Elaborations() {
                init_17_0_2_edc0357_1352328158790_658031_20438Dependencies();
                Expression<?>[] arguments224 = new Expression<?>[1];
                arguments224[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition224 = new Expression<Boolean>(_17_0_2_edc0357_1352328158792_72920_20445_exists);
                elaborationRule224 = addElaborationRule(condition224, _17_0_2_edc0357_1352328156648_548830_19619.this, Customer._17_0_2_edc0357_1352328156648_548830_19619._17_0_2_edc0357_1352328158792_72920_20445.class, "determineDRCapLevel_ReadStructuralFeatureAction_CustomerCB", arguments224);
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

            public BooleanParameter _17_0_2_edc0357_1352328158791_37119_20442_exists = null;

            public BooleanParameter objectToPass = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158791_37119_20442_existsDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Effect effect225 = null;

            public Parameter effect225Var = null;

            public ElaborationRule elaborationRule226 = null;

            public void init_17_0_2_edc0357_1352328158791_311867_20439Members() {
                try {
                    if (_17_0_2_edc0357_1352328158791_37119_20442_exists == null) _17_0_2_edc0357_1352328158791_37119_20442_exists = new BooleanParameter("_17_0_2_edc0357_1352328158791_37119_20442_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect225VarV = sig_17_0_2_edc0357_1352328158795_332074_20459;
                    effect225Var = new Parameter("effect225Var", null, null, this);
                    addDependency(effect225Var, new Expression(effect225VarV));
                    effect225 = new EffectFunction(new FunctionCall(effect225Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158791_311867_20439Collections() {
                parameters.add(_17_0_2_edc0357_1352328158791_37119_20442_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect225Var = new TreeSet<Effect>();
                effectsForeffect225Var.add(effect225);
                addEffects((Parameter<?>) effect225Var, effectsForeffect225Var);
            }

            public void init_17_0_2_edc0357_1352328158791_311867_20439Dependencies() {
                addDependency(_17_0_2_edc0357_1352328158791_37119_20442_exists, new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall));
                addDependency(duration, new Expression<Integer>(30));
                addDependency(objectToPass, new Expression<Boolean>(new FunctionCall(sig_17_0_2_edc0357_1352328158795_917007_20460, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_edc0357_1352328158791_311867_20439Elaborations() {
                init_17_0_2_edc0357_1352328158791_311867_20439Dependencies();
                Expression<?>[] arguments226 = new Expression<?>[1];
                arguments226[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition226 = new Expression<Boolean>(_17_0_2_edc0357_1352328158791_37119_20442_exists);
                elaborationRule226 = addElaborationRule(condition226, _17_0_2_edc0357_1352328156648_548830_19619.this, Customer._17_0_2_edc0357_1352328156648_548830_19619._17_0_2_edc0357_1352328158791_37119_20442.class, "_CallBehaviorAction_CustomerCB", arguments226);
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

            public IntegerParameter duration = null;

            public BooleanParameter objectToPass = null;

            public IntegerParameter _17_0_2_edc0357_1352328160582_184647_21502 = null;

            public BooleanParameter _17_0_2_edc0357_1352328158793_123095_20447_exists = null;

            public Dependency< Integer > _17_0_2_edc0357_1352328158535_616746_20341Dependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Dependency< Integer > _17_0_2_edc0357_1352328160582_184647_21502Dependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158793_123095_20447_existsDependency = null;

            public Effect effect227 = null;

            public Parameter effect227Var = null;

            public ElaborationRule elaborationRule228 = null;

            public ElaborationRule elaborationRule229 = null;

            public void init_17_0_2_edc0357_1352328158791_77291_20440Members() {
                try {
                    if (_17_0_2_edc0357_1352328158535_616746_20341 == null) _17_0_2_edc0357_1352328158535_616746_20341 = new IntegerParameter("_17_0_2_edc0357_1352328158535_616746_20341", (Integer) null, this);
                    if (duration == null) duration = new IntegerParameter("duration", (Integer) 45, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (_17_0_2_edc0357_1352328160582_184647_21502 == null) _17_0_2_edc0357_1352328160582_184647_21502 = new IntegerParameter("_17_0_2_edc0357_1352328160582_184647_21502", (Integer) null, this);
                    if (_17_0_2_edc0357_1352328158793_123095_20447_exists == null) _17_0_2_edc0357_1352328158793_123095_20447_exists = new BooleanParameter("_17_0_2_edc0357_1352328158793_123095_20447_exists", (Boolean) false, this);
                    Object effect227VarV = sig_17_0_2_edc0357_1352328158796_431791_20468;
                    effect227Var = new Parameter("effect227Var", null, null, this);
                    addDependency(effect227Var, new Expression(effect227VarV));
                    effect227 = new EffectFunction(new FunctionCall(effect227Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158791_77291_20440Collections() {
                parameters.add(_17_0_2_edc0357_1352328158535_616746_20341);
                parameters.add(duration);
                parameters.add(objectToPass);
                parameters.add(_17_0_2_edc0357_1352328160582_184647_21502);
                parameters.add(_17_0_2_edc0357_1352328158793_123095_20447_exists);
                Set<Effect> effectsForeffect227Var = new TreeSet<Effect>();
                effectsForeffect227Var.add(effect227);
                addEffects((Parameter<?>) effect227Var, effectsForeffect227Var);
            }

            public void init_17_0_2_edc0357_1352328158791_77291_20440Dependencies() {
                addDependency(_17_0_2_edc0357_1352328158535_616746_20341, new Expression<Integer>(_17_0_2_edc0357_1352328160582_184647_21502));
                addDependency(objectToPass, new Expression<Boolean>(true));
                addDependency(_17_0_2_edc0357_1352328160582_184647_21502, new Expression<Integer>(new FunctionCall(sig_17_0_2_edc0357_1352328158796_989489_20466, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_edc0357_1352328158793_123095_20447_exists, new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall));
            }

            public void init_17_0_2_edc0357_1352328158791_77291_20440Elaborations() {
                init_17_0_2_edc0357_1352328158791_77291_20440Dependencies();
                Expression<?>[] arguments228 = new Expression<?>[2];
                arguments228[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                arguments228[1] = new Expression<ObjectFlow<Boolean>>(sig_17_0_2_edc0357_1352328158796_431791_20468);
                Expression<Boolean> condition228 = new Expression<Boolean>(_17_0_2_edc0357_1352328158793_123095_20447_exists);
                elaborationRule228 = addElaborationRule(condition228, _17_0_2_edc0357_1352328156648_548830_19619.this, Customer._17_0_2_edc0357_1352328156648_548830_19619._17_0_2_edc0357_1352328158793_123095_20447.class, "_MergeNode_CustomerCB", arguments228);
                Expression<?>[] arguments229 = new Expression<?>[3];
                arguments229[0] = new Expression<Integer>(startTime);
                arguments229[1] = new Expression<DurativeEvent>(this);
                arguments229[2] = new Expression<Integer>(_17_0_2_edc0357_1352328158535_616746_20341);
                Expression<Boolean> condition229 = new Expression<Boolean>(true);
                elaborationRule229 = addElaborationRule(condition229, Customer.this, Customer._17_0_2_edc0357_1352328156645_46192_19618.class, "setDRCap_Activity_Customer", arguments229);
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

            public IntegerParameter duration = null;

            public BooleanParameter objectToPass = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158793_331994_20446_existsDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Effect effect230 = null;

            public Parameter effect230Var = null;

            public ElaborationRule elaborationRule231 = null;

            public ElaborationRule elaborationRule232 = null;

            public void init_17_0_2_edc0357_1352328158791_540176_20441Members() {
                try {
                    if (_17_0_2_edc0357_1352328158793_331994_20446_exists == null) _17_0_2_edc0357_1352328158793_331994_20446_exists = new BooleanParameter("_17_0_2_edc0357_1352328158793_331994_20446_exists", (Boolean) false, this);
                    if (duration == null) duration = new IntegerParameter("duration", (Integer) 45, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect230VarV = sig_17_0_2_edc0357_1352328158795_53444_20463;
                    effect230Var = new Parameter("effect230Var", null, null, this);
                    addDependency(effect230Var, new Expression(effect230VarV));
                    effect230 = new EffectFunction(new FunctionCall(effect230Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158791_540176_20441Collections() {
                parameters.add(_17_0_2_edc0357_1352328158793_331994_20446_exists);
                parameters.add(duration);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect230Var = new TreeSet<Effect>();
                effectsForeffect230Var.add(effect230);
                addEffects((Parameter<?>) effect230Var, effectsForeffect230Var);
            }

            public void init_17_0_2_edc0357_1352328158791_540176_20441Dependencies() {
                addDependency(_17_0_2_edc0357_1352328158793_331994_20446_exists, new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall));
                addDependency(objectToPass, new Expression<Boolean>(new FunctionCall(sig_17_0_2_edc0357_1352328158797_121818_20475, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_edc0357_1352328158791_540176_20441Elaborations() {
                init_17_0_2_edc0357_1352328158791_540176_20441Dependencies();
                Expression<?>[] arguments231 = new Expression<?>[2];
                arguments231[0] = new Expression<Integer>(startTime);
                arguments231[1] = new Expression<DurativeEvent>(this);
                Expression<Boolean> condition231 = new Expression<Boolean>(true);
                elaborationRule231 = addElaborationRule(condition231, Customer.this, Customer._17_0_2_edc0357_1352328156639_660827_19616.class, "changePowerUsage_Activity_Customer", arguments231);
                Expression<?>[] arguments232 = new Expression<?>[2];
                arguments232[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                arguments232[1] = new Expression<ObjectFlow<Boolean>>(sig_17_0_2_edc0357_1352328158795_53444_20463);
                Expression<Boolean> condition232 = new Expression<Boolean>(_17_0_2_edc0357_1352328158793_331994_20446_exists);
                elaborationRule232 = addElaborationRule(condition232, _17_0_2_edc0357_1352328156648_548830_19619.this, Customer._17_0_2_edc0357_1352328156648_548830_19619._17_0_2_edc0357_1352328158793_331994_20446.class, "_MergeNode_CustomerCB", arguments232);
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

            public BooleanParameter _17_0_2_edc0357_1352328158792_819832_20444_exists = null;

            public IntegerParameter duration = null;

            public BooleanParameter objectToPass = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158792_819832_20444_existsDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Effect effect233 = null;

            public Parameter effect233Var = null;

            public ElaborationRule elaborationRule234 = null;

            public ElaborationRule elaborationRule235 = null;

            public void init_17_0_2_edc0357_1352328158791_37119_20442Members() {
                try {
                    if (_17_0_2_edc0357_1352328158792_819832_20444_exists == null) _17_0_2_edc0357_1352328158792_819832_20444_exists = new BooleanParameter("_17_0_2_edc0357_1352328158792_819832_20444_exists", (Boolean) false, this);
                    if (duration == null) duration = new IntegerParameter("duration", (Integer) 45, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect233VarV = sig_17_0_2_edc0357_1352328158795_593515_20461;
                    effect233Var = new Parameter("effect233Var", null, null, this);
                    addDependency(effect233Var, new Expression(effect233VarV));
                    effect233 = new EffectFunction(new FunctionCall(effect233Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158791_37119_20442Collections() {
                parameters.add(_17_0_2_edc0357_1352328158792_819832_20444_exists);
                parameters.add(duration);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect233Var = new TreeSet<Effect>();
                effectsForeffect233Var.add(effect233);
                addEffects((Parameter<?>) effect233Var, effectsForeffect233Var);
            }

            public void init_17_0_2_edc0357_1352328158791_37119_20442Dependencies() {
                addDependency(_17_0_2_edc0357_1352328158792_819832_20444_exists, new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall));
                addDependency(objectToPass, new Expression<Boolean>(new FunctionCall(sig_17_0_2_edc0357_1352328158795_332074_20459, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_edc0357_1352328158791_37119_20442Elaborations() {
                init_17_0_2_edc0357_1352328158791_37119_20442Dependencies();
                Expression<?>[] arguments234 = new Expression<?>[2];
                arguments234[0] = new Expression<Integer>(startTime);
                arguments234[1] = new Expression<DurativeEvent>(this);
                Expression<Boolean> condition234 = new Expression<Boolean>(true);
                elaborationRule234 = addElaborationRule(condition234, Customer.this, Customer._17_0_2_edc0357_1352328156638_770509_19615.class, "usePower_Activity_Customer", arguments234);
                Expression<?>[] arguments235 = new Expression<?>[2];
                arguments235[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                arguments235[1] = new Expression<ObjectFlow<Boolean>>(sig_17_0_2_edc0357_1352328158795_593515_20461);
                Expression<Boolean> condition235 = new Expression<Boolean>(_17_0_2_edc0357_1352328158792_819832_20444_exists);
                elaborationRule235 = addElaborationRule(condition235, _17_0_2_edc0357_1352328156648_548830_19619.this, Customer._17_0_2_edc0357_1352328156648_548830_19619._17_0_2_edc0357_1352328158792_819832_20444.class, "_MergeNode_CustomerCB", arguments235);
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
                addDependency(endTime, new Expression<Integer>((new Functions.Plus(new Expression<Integer>(startTime), new Expression<Integer>(duration))).functionCall));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new FunctionCall(sig_17_0_2_edc0357_1352328158796_819586_20473, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
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

            public BooleanParameter objectToPass = null;

            public BooleanParameter _17_0_2_edc0357_1352328158791_311867_20439_exists = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328158791_311867_20439_existsDependency = null;

            public Effect effect236 = null;

            public Parameter effect236Var = null;

            public ElaborationRule elaborationRule237 = null;

            public void init_17_0_2_edc0357_1352328158792_819832_20444Members() {
                try {
                    if (receiveThis == null) receiveThis = new Parameter<ObjectFlow<Boolean>>("receiveThis", null, (ObjectFlow<Boolean>) null, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (_17_0_2_edc0357_1352328158791_311867_20439_exists == null) _17_0_2_edc0357_1352328158791_311867_20439_exists = new BooleanParameter("_17_0_2_edc0357_1352328158791_311867_20439_exists", (Boolean) false, this);
                    Object effect236VarV = sig_17_0_2_edc0357_1352328158795_917007_20460;
                    effect236Var = new Parameter("effect236Var", null, null, this);
                    addDependency(effect236Var, new Expression(effect236VarV));
                    effect236 = new EffectFunction(new FunctionCall(effect236Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158792_819832_20444Collections() {
                parameters.add(receiveThis);
                parameters.add(objectToPass);
                parameters.add(_17_0_2_edc0357_1352328158791_311867_20439_exists);
                Set<Effect> effectsForeffect236Var = new TreeSet<Effect>();
                effectsForeffect236Var.add(effect236);
                addEffects((Parameter<?>) effect236Var, effectsForeffect236Var);
            }

            public void init_17_0_2_edc0357_1352328158792_819832_20444Dependencies() {
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new FunctionCall(receiveThis, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_edc0357_1352328158791_311867_20439_exists, new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall));
            }

            public void init_17_0_2_edc0357_1352328158792_819832_20444Elaborations() {
                init_17_0_2_edc0357_1352328158792_819832_20444Dependencies();
                Expression<?>[] arguments237 = new Expression<?>[1];
                arguments237[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition237 = new Expression<Boolean>(_17_0_2_edc0357_1352328158791_311867_20439_exists);
                elaborationRule237 = addElaborationRule(condition237, _17_0_2_edc0357_1352328156648_548830_19619.this, Customer._17_0_2_edc0357_1352328156648_548830_19619._17_0_2_edc0357_1352328158791_311867_20439.class, "power_polling_interval_AcceptEventAction_CustomerCB", arguments237);
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

            public Effect effect238 = null;

            public Parameter effect238Var = null;

            public ElaborationRule elaborationRule239 = null;

            public void init_17_0_2_edc0357_1352328158792_72920_20445Members() {
                try {
                    if (_17_0_2_edc0357_1352328160583_288418_21503 == null) _17_0_2_edc0357_1352328160583_288418_21503 = new IntegerParameter("_17_0_2_edc0357_1352328160583_288418_21503", (Integer) null, this);
                    if (_17_0_2_edc0357_1352328160584_590137_21504 == null) _17_0_2_edc0357_1352328160584_590137_21504 = new Parameter<Power_System.Signaldr_request>("_17_0_2_edc0357_1352328160584_590137_21504", null, (Power_System.Signaldr_request) null, this);
                    if (_17_0_2_edc0357_1352328158791_77291_20440_exists == null) _17_0_2_edc0357_1352328158791_77291_20440_exists = new BooleanParameter("_17_0_2_edc0357_1352328158791_77291_20440_exists", (Boolean) false, this);
                    Object effect238VarV = sig_17_0_2_edc0357_1352328158796_989489_20466;
                    effect238Var = new Parameter("effect238Var", null, null, this);
                    addDependency(effect238Var, new Expression(effect238VarV));
                    effect238 = new EffectFunction(new FunctionCall(effect238Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { _17_0_2_edc0357_1352328160583_288418_21503, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158792_72920_20445Collections() {
                parameters.add(_17_0_2_edc0357_1352328160583_288418_21503);
                parameters.add(_17_0_2_edc0357_1352328160584_590137_21504);
                parameters.add(_17_0_2_edc0357_1352328158791_77291_20440_exists);
                Set<Effect> effectsForeffect238Var = new TreeSet<Effect>();
                effectsForeffect238Var.add(effect238);
                addEffects((Parameter<?>) effect238Var, effectsForeffect238Var);
            }

            public void init_17_0_2_edc0357_1352328158792_72920_20445Dependencies() {
                addDependency(_17_0_2_edc0357_1352328160583_288418_21503, new Expression<Integer>(new FunctionCall(null, ClassUtils.getMethodForArgTypes("TimeVaryingMap<Integer>", "powersystem", "getValue", java.lang.Integer.class), new Object[] { new Expression<Integer>(startTime) }, new FunctionCall(_17_0_2_edc0357_1352328160584_590137_21504, Parameter.class, "getMember", new Object[] { "cap__17_0_2_edc0357_1352328156887_758776_19707" }))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(_17_0_2_edc0357_1352328160584_590137_21504, new Expression<Power_System.Signaldr_request>(new FunctionCall(sig_17_0_2_edc0357_1352328158795_859697_20465, ClassUtils.getMethodForArgTypes("ObjectFlow<Signaldr_request>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_edc0357_1352328158791_77291_20440_exists, new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall));
            }

            public void init_17_0_2_edc0357_1352328158792_72920_20445Elaborations() {
                init_17_0_2_edc0357_1352328158792_72920_20445Dependencies();
                Expression<?>[] arguments239 = new Expression<?>[1];
                arguments239[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition239 = new Expression<Boolean>(_17_0_2_edc0357_1352328158791_77291_20440_exists);
                elaborationRule239 = addElaborationRule(condition239, _17_0_2_edc0357_1352328156648_548830_19619.this, Customer._17_0_2_edc0357_1352328156648_548830_19619._17_0_2_edc0357_1352328158791_77291_20440.class, "_CallBehaviorAction_CustomerCB", arguments239);
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

            public Effect effect240 = null;

            public Parameter effect240Var = null;

            public ElaborationRule elaborationRule241 = null;

            public void init_17_0_2_edc0357_1352328158793_331994_20446Members() {
                try {
                    if (_17_0_2_edc0357_1352328158794_405865_20450_exists == null) _17_0_2_edc0357_1352328158794_405865_20450_exists = new BooleanParameter("_17_0_2_edc0357_1352328158794_405865_20450_exists", (Boolean) false, this);
                    if (receiveThis == null) receiveThis = new Parameter<ObjectFlow<Boolean>>("receiveThis", null, (ObjectFlow<Boolean>) null, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect240VarV = sig_17_0_2_edc0357_1352328158796_890131_20474;
                    effect240Var = new Parameter("effect240Var", null, null, this);
                    addDependency(effect240Var, new Expression(effect240VarV));
                    effect240 = new EffectFunction(new FunctionCall(effect240Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158793_331994_20446Collections() {
                parameters.add(_17_0_2_edc0357_1352328158794_405865_20450_exists);
                parameters.add(receiveThis);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect240Var = new TreeSet<Effect>();
                effectsForeffect240Var.add(effect240);
                addEffects((Parameter<?>) effect240Var, effectsForeffect240Var);
            }

            public void init_17_0_2_edc0357_1352328158793_331994_20446Dependencies() {
                addDependency(_17_0_2_edc0357_1352328158794_405865_20450_exists, new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new FunctionCall(receiveThis, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_edc0357_1352328158793_331994_20446Elaborations() {
                init_17_0_2_edc0357_1352328158793_331994_20446Dependencies();
                Expression<?>[] arguments241 = new Expression<?>[1];
                arguments241[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition241 = new Expression<Boolean>(_17_0_2_edc0357_1352328158794_405865_20450_exists);
                elaborationRule241 = addElaborationRule(condition241, _17_0_2_edc0357_1352328156648_548830_19619.this, Customer._17_0_2_edc0357_1352328156648_548830_19619._17_0_2_edc0357_1352328158794_405865_20450.class, "_AcceptEventAction_CustomerCB", arguments241);
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

            public Effect effect242 = null;

            public Parameter effect242Var = null;

            public ElaborationRule elaborationRule243 = null;

            public void init_17_0_2_edc0357_1352328158793_123095_20447Members() {
                try {
                    if (receiveThis == null) receiveThis = new Parameter<ObjectFlow<Boolean>>("receiveThis", null, (ObjectFlow<Boolean>) null, this);
                    if (_17_0_2_edc0357_1352328158790_658031_20438_exists == null) _17_0_2_edc0357_1352328158790_658031_20438_exists = new BooleanParameter("_17_0_2_edc0357_1352328158790_658031_20438_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect242VarV = sig_17_0_2_edc0357_1352328158796_917263_20467;
                    effect242Var = new Parameter("effect242Var", null, null, this);
                    addDependency(effect242Var, new Expression(effect242VarV));
                    effect242 = new EffectFunction(new FunctionCall(effect242Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158793_123095_20447Collections() {
                parameters.add(receiveThis);
                parameters.add(_17_0_2_edc0357_1352328158790_658031_20438_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect242Var = new TreeSet<Effect>();
                effectsForeffect242Var.add(effect242);
                addEffects((Parameter<?>) effect242Var, effectsForeffect242Var);
            }

            public void init_17_0_2_edc0357_1352328158793_123095_20447Dependencies() {
                addDependency(endTime, new Expression<Integer>(new FunctionCall(null, ClassUtils.getMethodForArgTypes("java.lang.Math", "powersystem", "min", int.class, int.class), new Object[] { new Expression(new FunctionCall(q_Customer_dr_request, ClassUtils.getMethodForArgTypes("ObjectFlow<Signaldr_request>", "powersystem", "nextTimeHasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(startTime) })), new Expression<Integer>(finalNode_endTime) })));
                addDependency(_17_0_2_edc0357_1352328158790_658031_20438_exists, new Expression<Boolean>((new Functions.And(new Expression(new FunctionCall(q_Customer_dr_request, ClassUtils.getMethodForArgTypes("ObjectFlow<Signaldr_request>", "powersystem", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall))).functionCall));
                addDependency(objectToPass, new Expression<Boolean>(new FunctionCall(receiveThis, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_edc0357_1352328158793_123095_20447Elaborations() {
                init_17_0_2_edc0357_1352328158793_123095_20447Dependencies();
                Expression<?>[] arguments243 = new Expression<?>[1];
                arguments243[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition243 = new Expression<Boolean>(_17_0_2_edc0357_1352328158790_658031_20438_exists);
                elaborationRule243 = addElaborationRule(condition243, _17_0_2_edc0357_1352328156648_548830_19619.this, Customer._17_0_2_edc0357_1352328156648_548830_19619._17_0_2_edc0357_1352328158790_658031_20438.class, "_AcceptEventAction_CustomerCB", arguments243);
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

            public Effect effect244 = null;

            public Parameter effect244Var = null;

            public ElaborationRule elaborationRule245 = null;

            public void init_17_0_2_edc0357_1352328158793_123216_20448Members() {
                try {
                    if (_17_0_2_edc0357_1352328158790_493227_20437_exists == null) _17_0_2_edc0357_1352328158790_493227_20437_exists = new BooleanParameter("_17_0_2_edc0357_1352328158790_493227_20437_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect244VarV = sig_17_0_2_edc0357_1352328158796_290441_20471;
                    effect244Var = new Parameter("effect244Var", null, null, this);
                    addDependency(effect244Var, new Expression(effect244VarV));
                    effect244 = new EffectFunction(new FunctionCall(effect244Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158793_123216_20448Collections() {
                parameters.add(_17_0_2_edc0357_1352328158790_493227_20437_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect244Var = new TreeSet<Effect>();
                effectsForeffect244Var.add(effect244);
                addEffects((Parameter<?>) effect244Var, effectsForeffect244Var);
            }

            public void init_17_0_2_edc0357_1352328158793_123216_20448Dependencies() {
                addDependency(_17_0_2_edc0357_1352328158790_493227_20437_exists, new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall));
                addDependency(duration, new Expression<Integer>(20));
                addDependency(objectToPass, new Expression<Boolean>(new FunctionCall(sig_17_0_2_edc0357_1352328158796_758419_20470, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_edc0357_1352328158793_123216_20448Elaborations() {
                init_17_0_2_edc0357_1352328158793_123216_20448Dependencies();
                Expression<?>[] arguments245 = new Expression<?>[1];
                arguments245[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition245 = new Expression<Boolean>(_17_0_2_edc0357_1352328158790_493227_20437_exists);
                elaborationRule245 = addElaborationRule(condition245, _17_0_2_edc0357_1352328156648_548830_19619.this, Customer._17_0_2_edc0357_1352328156648_548830_19619._17_0_2_edc0357_1352328158790_493227_20437.class, "_ForkNode_CustomerCB", arguments245);
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

            public Effect effect246 = null;

            public Parameter effect246Var = null;

            public void init_17_0_2_edc0357_1352328158793_68185_20449Members() {
                try {
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect246VarV = sig_17_0_2_edc0357_1352328158796_819586_20473;
                    effect246Var = new Parameter("effect246Var", null, null, this);
                    addDependency(effect246Var, new Expression(effect246VarV));
                    effect246 = new EffectFunction(new FunctionCall(effect246Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158793_68185_20449Collections() {
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect246Var = new TreeSet<Effect>();
                effectsForeffect246Var.add(effect246);
                addEffects((Parameter<?>) effect246Var, effectsForeffect246Var);
            }

            public void init_17_0_2_edc0357_1352328158793_68185_20449Dependencies() {
                addDependency(finalNode_startTime, new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall));
                addDependency(duration, new Expression<Integer>(5000));
                addDependency(objectToPass, new Expression<Boolean>(new FunctionCall(sig_17_0_2_edc0357_1352328158796_884417_20472, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
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

            public Effect effect247 = null;

            public Parameter effect247Var = null;

            public ElaborationRule elaborationRule248 = null;

            public void init_17_0_2_edc0357_1352328158794_405865_20450Members() {
                try {
                    if (_17_0_2_edc0357_1352328158791_540176_20441_exists == null) _17_0_2_edc0357_1352328158791_540176_20441_exists = new BooleanParameter("_17_0_2_edc0357_1352328158791_540176_20441_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect247VarV = sig_17_0_2_edc0357_1352328158797_121818_20475;
                    effect247Var = new Parameter("effect247Var", null, null, this);
                    addDependency(effect247Var, new Expression(effect247VarV));
                    effect247 = new EffectFunction(new FunctionCall(effect247Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328158794_405865_20450Collections() {
                parameters.add(_17_0_2_edc0357_1352328158791_540176_20441_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect247Var = new TreeSet<Effect>();
                effectsForeffect247Var.add(effect247);
                addEffects((Parameter<?>) effect247Var, effectsForeffect247Var);
            }

            public void init_17_0_2_edc0357_1352328158794_405865_20450Dependencies() {
                addDependency(_17_0_2_edc0357_1352328158791_540176_20441_exists, new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall));
                addDependency(duration, new Expression<Integer>(60));
                addDependency(objectToPass, new Expression<Boolean>(new FunctionCall(sig_17_0_2_edc0357_1352328158796_890131_20474, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_edc0357_1352328158794_405865_20450Elaborations() {
                init_17_0_2_edc0357_1352328158794_405865_20450Dependencies();
                Expression<?>[] arguments248 = new Expression<?>[1];
                arguments248[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition248 = new Expression<Boolean>(_17_0_2_edc0357_1352328158791_540176_20441_exists);
                elaborationRule248 = addElaborationRule(condition248, _17_0_2_edc0357_1352328156648_548830_19619.this, Customer._17_0_2_edc0357_1352328156648_548830_19619._17_0_2_edc0357_1352328158791_540176_20441.class, "_CallBehaviorAction_CustomerCB", arguments248);
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

        public ElaborationRule elaborationRule208 = null;

        public ElaborationRule elaborationRule209 = null;

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
                if (finalNode_startTime == null) finalNode_startTime = new IntegerParameter("finalNode_startTime", (Integer) 84000, this);
                if (sig_17_0_2_edc0357_1352328158795_53444_20463 == null) sig_17_0_2_edc0357_1352328158795_53444_20463 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328158795_53444_20463", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158795_53444_20463" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328158796_884417_20472 == null) sig_17_0_2_edc0357_1352328158796_884417_20472 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328158796_884417_20472", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158796_884417_20472" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328158795_917007_20460 == null) sig_17_0_2_edc0357_1352328158795_917007_20460 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328158795_917007_20460", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158795_917007_20460" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328158795_859697_20465 == null) sig_17_0_2_edc0357_1352328158795_859697_20465 = new Parameter<ObjectFlow<Power_System.Signaldr_request>>("sig_17_0_2_edc0357_1352328158795_859697_20465", null, (ObjectFlow<Power_System.Signaldr_request>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328158795_859697_20465" })).evaluate(true), this);
                if (finalNode_endTime == null) finalNode_endTime = new IntegerParameter("finalNode_endTime", (Integer) 84000, this);
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
            addDependency(_17_0_2_edc0357_1352328158792_106015_20443_exists, new Expression<Boolean>(new FunctionCall(sig_17_0_2_edc0357_1352328158796_819586_20473, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>((new Functions.Minus(new Expression<Integer>(finalNode_startTime), new Expression<Integer>(1))).functionCall) })));
        }

        public void init_17_0_2_edc0357_1352328156648_548830_19619Elaborations() {
            init_17_0_2_edc0357_1352328156648_548830_19619Dependencies();
            Expression<?>[] arguments208 = new Expression<?>[1];
            arguments208[0] = new Expression<Integer>(finalNode_startTime);
            Expression<Boolean> condition208 = new Expression<Boolean>(_17_0_2_edc0357_1352328158792_106015_20443_exists);
            elaborationRule208 = addElaborationRule(condition208, _17_0_2_edc0357_1352328156648_548830_19619.this, Customer._17_0_2_edc0357_1352328156648_548830_19619._17_0_2_edc0357_1352328158792_106015_20443.class, "_ActivityFinalNode_CustomerCB", arguments208);
            Expression<?>[] arguments209 = new Expression<?>[1];
            arguments209[0] = new Expression<Integer>(startTime);
            Expression<Boolean> condition209 = new Expression<Boolean>(true);
            elaborationRule209 = addElaborationRule(condition209, _17_0_2_edc0357_1352328156648_548830_19619.this, Customer._17_0_2_edc0357_1352328156648_548830_19619._17_0_2_edc0357_1352328158789_563871_20435.class, "_InitialNode_CustomerCB", arguments209);
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

    public Parameter< TimeVaryingMap<Integer> > usage__17_0_2_edc0357_1352328156649_216766_19620 = null;

    public Parameter< TimeVaryingMap<Integer> > cap__17_0_2_edc0357_1352328156659_30024_19625 = null;

    public Parameter< ObjectFlow<Power_System.Signaldr_request> > q_Customer_dr_request = null;

    public Parameter< Power_System > x = null;

    public Parameter< ObjectFlow<Power_System.Signalyes> > q_Customer_yes = null;

    public Parameter< ObjectFlow<Power_System.SignalchangeLoadValue> > q_Customer_changeLoadValue = null;

    public Parameter< ObjectFlow<Power_System.Signalno> > q_Customer_no = null;

    public void initCustomerMembers() {
        try {
            if (classifierBehavior == null) classifierBehavior = new StringParameter("classifierBehavior", (String) "_17_0_2_edc0357_1352328156648_548830_19619", this);
            if (q_Customer_receiveMeterReading == null) q_Customer_receiveMeterReading = new Parameter<ObjectFlow<Power_System.SignalreceiveMeterReading>>("q_Customer_receiveMeterReading", null, (ObjectFlow<Power_System.SignalreceiveMeterReading>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class, java.lang.Class.class), new Object[] { "q_Customer_receiveMeterReading", Power_System.SignalreceiveMeterReading.class })).evaluate(true), this);
            if (usage__17_0_2_edc0357_1352328156649_216766_19620 == null) usage__17_0_2_edc0357_1352328156649_216766_19620 = new Parameter<TimeVaryingMap<Integer>>("usage__17_0_2_edc0357_1352328156649_216766_19620", null, (TimeVaryingMap<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingMap.class, java.lang.String.class), new Object[] { "usage" })).evaluate(true), this);
            if (cap__17_0_2_edc0357_1352328156659_30024_19625 == null) cap__17_0_2_edc0357_1352328156659_30024_19625 = new Parameter<TimeVaryingMap<Integer>>("cap__17_0_2_edc0357_1352328156659_30024_19625", null, (TimeVaryingMap<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingMap.class, java.lang.String.class), new Object[] { "cap" })).evaluate(true), this);
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
