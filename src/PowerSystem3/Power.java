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

public class Power extends ParameterListenerImpl {

    public Power() {
        super();
        initPowerMembers();
        initPowerCollections();
        initPowerElaborations();
    }

    public class _17_0_2_2_edc0357_1357846886577_262509_19863 extends DurativeEvent {

        public _17_0_2_2_edc0357_1357846886577_262509_19863() {
            super();
            init_17_0_2_2_edc0357_1357846886577_262509_19863Members();
            init_17_0_2_2_edc0357_1357846886577_262509_19863Collections();
            init_17_0_2_2_edc0357_1357846886577_262509_19863Elaborations();
        }

        public class _17_0_2_2_edc0357_1357846886875_192849_20065 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846886875_192849_20065() {
                super();
                init_17_0_2_2_edc0357_1357846886875_192849_20065Members();
                init_17_0_2_2_edc0357_1357846886875_192849_20065Collections();
                init_17_0_2_2_edc0357_1357846886875_192849_20065Elaborations();
            }

            public _17_0_2_2_edc0357_1357846886875_192849_20065(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846886875_192849_20065Members();
                init_17_0_2_2_edc0357_1357846886875_192849_20065Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846886875_192849_20065Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_2_edc0357_1357846886879_399277_20071_exists = null;

            public Parameter< Power > _17_0_2_2_edc0357_1357846887393_533153_21260 = null;

            public BooleanParameter objectToPass = null;

            public Dependency _17_0_2_2_edc0357_1357846886879_399277_20071_existsDependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Effect effect301 = null;

            public Parameter effect301Var = null;

            public ElaborationRule elaborationRule302 = null;

            public void init_17_0_2_2_edc0357_1357846886875_192849_20065Members() {
                try {
                    if (_17_0_2_2_edc0357_1357846886879_399277_20071_exists == null) _17_0_2_2_edc0357_1357846886879_399277_20071_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846886879_399277_20071_exists", (Boolean) false, this);
                    if (_17_0_2_2_edc0357_1357846887393_533153_21260 == null) _17_0_2_2_edc0357_1357846887393_533153_21260 = new Parameter<Power>("_17_0_2_2_edc0357_1357846887393_533153_21260", null, (Power) Power.this, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect301VarV = sig_17_0_2_2_edc0357_1357846886880_502535_20075;
                    effect301Var = new Parameter("effect301Var", null, null, this);
                    addDependency(effect301Var, new Expression(effect301VarV));
                    effect301 = new EffectFunction(new EffectFunction(effect301Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Power>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { _17_0_2_2_edc0357_1357846887393_533153_21260, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846886875_192849_20065Collections() {
                parameters.add(_17_0_2_2_edc0357_1357846886879_399277_20071_exists);
                parameters.add(_17_0_2_2_edc0357_1357846887393_533153_21260);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect301Var = new TreeSet<Effect>();
                effectsForeffect301Var.add(effect301);
                addEffects((Parameter<?>) effect301Var, effectsForeffect301Var);
            }

            public void init_17_0_2_2_edc0357_1357846886875_192849_20065Dependencies() {
                addDependency(_17_0_2_2_edc0357_1357846886879_399277_20071_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886877_335300_20068_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886877_335300_20068_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846886881_299607_20081, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_2_edc0357_1357846886875_192849_20065Elaborations() {
                init_17_0_2_2_edc0357_1357846886875_192849_20065Dependencies();
                Expression<?>[] arguments302 = new Expression<?>[1];
                arguments302[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition302 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846886879_399277_20071_exists);
                elaborationRule302 = addElaborationRule(condition302, _17_0_2_2_edc0357_1357846886577_262509_19863.this, Power._17_0_2_2_edc0357_1357846886577_262509_19863._17_0_2_2_edc0357_1357846886879_399277_20071.class, "Pchange_fork_self_ForkNode_changeGenerationValue", arguments302);
            }
        }

        public class _17_0_2_2_edc0357_1357846886876_30151_20066 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846886876_30151_20066() {
                super();
                init_17_0_2_2_edc0357_1357846886876_30151_20066Members();
                init_17_0_2_2_edc0357_1357846886876_30151_20066Collections();
                init_17_0_2_2_edc0357_1357846886876_30151_20066Elaborations();
            }

            public _17_0_2_2_edc0357_1357846886876_30151_20066(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846886876_30151_20066Members();
                init_17_0_2_2_edc0357_1357846886876_30151_20066Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846886876_30151_20066Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter addToDecider__17_0_2_2_edc0357_1357846886877_16383_20069 = null;

            public Parameter< Power > _17_0_2_2_edc0357_1357846887394_340993_21262 = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846886877_16383_20069_exists = null;

            public BooleanParameter objectToPass = null;

            public IntegerParameter _17_0_2_2_edc0357_1357846887393_612505_21261 = null;

            public IntegerParameter myDeciderID_decider_17_0_2_2_edc0357_1357846886877_16383_20069 = null;

            public Dependency addToDecider__17_0_2_2_edc0357_1357846886877_16383_20069Dependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887394_340993_21262Dependency = null;

            public Dependency durationDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846886877_16383_20069_existsDependency = null;

            public Dependency objectToPassDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887393_612505_21261Dependency = null;

            public Dependency myDeciderID_decider_17_0_2_2_edc0357_1357846886877_16383_20069Dependency = null;

            public Effect effect303 = null;

            public Parameter effect303Var = null;

            public Effect effect304 = null;

            public Parameter effect304Var = null;

            public Effect effect305 = null;

            public Parameter effect305Var = null;

            public ElaborationRule elaborationRule306 = null;

            public void init_17_0_2_2_edc0357_1357846886876_30151_20066Members() {
                try {
                    if (addToDecider__17_0_2_2_edc0357_1357846886877_16383_20069 == null) addToDecider__17_0_2_2_edc0357_1357846886877_16383_20069 = new BooleanParameter("addToDecider__17_0_2_2_edc0357_1357846886877_16383_20069", (Boolean) false, this);
                    if (_17_0_2_2_edc0357_1357846887394_340993_21262 == null) _17_0_2_2_edc0357_1357846887394_340993_21262 = new Parameter<Power>("_17_0_2_2_edc0357_1357846887394_340993_21262", null, (Power) null, this);
                    if (_17_0_2_2_edc0357_1357846886877_16383_20069_exists == null) _17_0_2_2_edc0357_1357846886877_16383_20069_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846886877_16383_20069_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (_17_0_2_2_edc0357_1357846887393_612505_21261 == null) _17_0_2_2_edc0357_1357846887393_612505_21261 = new IntegerParameter("_17_0_2_2_edc0357_1357846887393_612505_21261", (Integer) null, this);
                    if (myDeciderID_decider_17_0_2_2_edc0357_1357846886877_16383_20069 == null) myDeciderID_decider_17_0_2_2_edc0357_1357846886877_16383_20069 = new IntegerParameter("myDeciderID_decider_17_0_2_2_edc0357_1357846886877_16383_20069", (Integer) 3, this);
                    Object effect303VarV = sig_17_0_2_2_edc0357_1357846886880_596148_20078;
                    effect303Var = new Parameter("effect303Var", null, null, this);
                    addDependency(effect303Var, new Expression(effect303VarV));
                    effect303 = new EffectFunction(new EffectFunction(effect303Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect304VarV = decider_17_0_2_2_edc0357_1357846886877_16383_20069;
                    effect304Var = new Parameter("effect304Var", null, null, this);
                    addDependency(effect304Var, new Expression(effect304VarV));
                    effect304 = new EffectFunction(new EffectFunction(effect304Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class, java.lang.Boolean.class), new Object[] { endTime, myDeciderID_decider_17_0_2_2_edc0357_1357846886877_16383_20069, addToDecider__17_0_2_2_edc0357_1357846886877_16383_20069 }));
                    Object effect305VarV = powerAugmentation__17_0_2_2_edc0357_1358276774925_778819_38488;
                    effect305Var = new Parameter("effect305Var", null, null, this);
                    addDependency(effect305Var, new Expression(effect305VarV));
                    effect305 = new EffectFunction(new EffectFunction(effect305Var, ClassUtils.getMethodForArgTypes("TimeVaryingPlottableMap<Integer>", "PowerSystem3", "setValue", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { startTime, _17_0_2_2_edc0357_1357846887393_612505_21261 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846886876_30151_20066Collections() {
                parameters.add(addToDecider__17_0_2_2_edc0357_1357846886877_16383_20069);
                parameters.add(_17_0_2_2_edc0357_1357846887394_340993_21262);
                parameters.add(_17_0_2_2_edc0357_1357846886877_16383_20069_exists);
                parameters.add(objectToPass);
                parameters.add(_17_0_2_2_edc0357_1357846887393_612505_21261);
                parameters.add(myDeciderID_decider_17_0_2_2_edc0357_1357846886877_16383_20069);
                Set<Effect> effectsForeffect303Var = new TreeSet<Effect>();
                effectsForeffect303Var.add(effect303);
                addEffects((Parameter<?>) effect303Var, effectsForeffect303Var);
                Set<Effect> effectsForeffect304Var = new TreeSet<Effect>();
                effectsForeffect304Var.add(effect304);
                addEffects((Parameter<?>) effect304Var, effectsForeffect304Var);
                Set<Effect> effectsForeffect305Var = new TreeSet<Effect>();
                effectsForeffect305Var.add(effect305);
                addEffects((Parameter<?>) effect305Var, effectsForeffect305Var);
            }

            public void init_17_0_2_2_edc0357_1357846886876_30151_20066Dependencies() {
                addDependency(addToDecider__17_0_2_2_edc0357_1357846886877_16383_20069, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886877_335300_20068_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886877_335300_20068_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(_17_0_2_2_edc0357_1357846887394_340993_21262, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846886880_601239_20076, ClassUtils.getMethodForArgTypes("ObjectFlow<Power>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(_17_0_2_2_edc0357_1357846886877_16383_20069_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886877_335300_20068_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886877_335300_20068_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846886877_16383_20069, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846886877_16383_20069, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846886877_16383_20069, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_2_edc0357_1357846886877_16383_20069)))))))));
                addDependency(objectToPass, new Expression<Boolean>(true));
                addDependency(_17_0_2_2_edc0357_1357846887393_612505_21261, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846886880_763857_20073, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(myDeciderID_decider_17_0_2_2_edc0357_1357846886877_16383_20069, new Expression<Integer>(3));
            }

            public void init_17_0_2_2_edc0357_1357846886876_30151_20066Elaborations() {
                init_17_0_2_2_edc0357_1357846886876_30151_20066Dependencies();
                Expression<?>[] arguments306 = new Expression<?>[1];
                arguments306[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition306 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846886877_16383_20069_exists);
                elaborationRule306 = addElaborationRule(condition306, _17_0_2_2_edc0357_1357846886577_262509_19863.this, Power._17_0_2_2_edc0357_1357846886577_262509_19863._17_0_2_2_edc0357_1357846886877_16383_20069.class, "Pchange_send_change_confirm_SendSignalAction_changeGenerationValue", arguments306);
            }
        }

        public class _17_0_2_2_edc0357_1357846886876_470136_20067 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846886876_470136_20067() {
                super();
                init_17_0_2_2_edc0357_1357846886876_470136_20067Members();
                init_17_0_2_2_edc0357_1357846886876_470136_20067Collections();
                init_17_0_2_2_edc0357_1357846886876_470136_20067Elaborations();
            }

            public _17_0_2_2_edc0357_1357846886876_470136_20067(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846886876_470136_20067Members();
                init_17_0_2_2_edc0357_1357846886876_470136_20067Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846886876_470136_20067Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_2_edc0357_1357846886875_192849_20065_exists = null;

            public BooleanParameter objectToPass = null;

            public Dependency _17_0_2_2_edc0357_1357846886875_192849_20065_existsDependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Effect effect307 = null;

            public Parameter effect307Var = null;

            public ElaborationRule elaborationRule308 = null;

            public void init_17_0_2_2_edc0357_1357846886876_470136_20067Members() {
                try {
                    if (_17_0_2_2_edc0357_1357846886875_192849_20065_exists == null) _17_0_2_2_edc0357_1357846886875_192849_20065_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846886875_192849_20065_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect307VarV = sig_17_0_2_2_edc0357_1357846886881_299607_20081;
                    effect307Var = new Parameter("effect307Var", null, null, this);
                    addDependency(effect307Var, new Expression(effect307VarV));
                    effect307 = new EffectFunction(new EffectFunction(effect307Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846886876_470136_20067Collections() {
                parameters.add(_17_0_2_2_edc0357_1357846886875_192849_20065_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect307Var = new TreeSet<Effect>();
                effectsForeffect307Var.add(effect307);
                addEffects((Parameter<?>) effect307Var, effectsForeffect307Var);
            }

            public void init_17_0_2_2_edc0357_1357846886876_470136_20067Dependencies() {
                addDependency(_17_0_2_2_edc0357_1357846886875_192849_20065_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886877_335300_20068_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886877_335300_20068_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(true));
            }

            public void init_17_0_2_2_edc0357_1357846886876_470136_20067Elaborations() {
                init_17_0_2_2_edc0357_1357846886876_470136_20067Dependencies();
                Expression<?>[] arguments308 = new Expression<?>[1];
                arguments308[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition308 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846886875_192849_20065_exists);
                elaborationRule308 = addElaborationRule(condition308, _17_0_2_2_edc0357_1357846886577_262509_19863.this, Power._17_0_2_2_edc0357_1357846886577_262509_19863._17_0_2_2_edc0357_1357846886875_192849_20065.class, "Pchange_readSelf_ReadSelfAction_changeGenerationValue", arguments308);
            }
        }

        public class _17_0_2_2_edc0357_1357846886877_335300_20068 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846886877_335300_20068() {
                super();
                init_17_0_2_2_edc0357_1357846886877_335300_20068Members();
                init_17_0_2_2_edc0357_1357846886877_335300_20068Collections();
                init_17_0_2_2_edc0357_1357846886877_335300_20068Elaborations();
            }

            public _17_0_2_2_edc0357_1357846886877_335300_20068(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846886877_335300_20068Members();
                init_17_0_2_2_edc0357_1357846886877_335300_20068Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846886877_335300_20068Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter objectToPass = null;

            public Dependency endTimeDependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Dependency finalNode_endTimeDependency = null;

            public void init_17_0_2_2_edc0357_1357846886877_335300_20068Members() {
                try {
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846886877_335300_20068Collections() {
                parameters.add(objectToPass);
            }

            public void init_17_0_2_2_edc0357_1357846886877_335300_20068Dependencies() {
                addDependency(endTime, new Expression<Integer>(new Functions.Plus(new Expression<Integer>(startTime), new Expression<Integer>(duration))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846886880_227877_20079, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(finalNode_endTime, new Expression<Integer>(endTime));
            }

            public void init_17_0_2_2_edc0357_1357846886877_335300_20068Elaborations() {
                init_17_0_2_2_edc0357_1357846886877_335300_20068Dependencies();
            }
        }

        public class _17_0_2_2_edc0357_1357846886877_16383_20069 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846886877_16383_20069() {
                super();
                init_17_0_2_2_edc0357_1357846886877_16383_20069Members();
                init_17_0_2_2_edc0357_1357846886877_16383_20069Collections();
                init_17_0_2_2_edc0357_1357846886877_16383_20069Elaborations();
            }

            public _17_0_2_2_edc0357_1357846886877_16383_20069(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846886877_16383_20069Members();
                init_17_0_2_2_edc0357_1357846886877_16383_20069Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846886877_16383_20069Elaborations();
                fixTimeDependencies();
            }

            public IntegerParameter _17_0_2_2_edc0357_1357846887395_247769_21264 = null;

            public Parameter< Power > _17_0_2_2_edc0357_1357846887395_750923_21263 = null;

            public BooleanParameter objectToPass = null;

            public Parameter< Power_System.SignalreceiveGenReading > signalObject = null;

            public Dependency _17_0_2_2_edc0357_1357846887395_247769_21264Dependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887395_750923_21263Dependency = null;

            public Dependency finalNode_startTimeDependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Dependency signalObjectDependency = null;

            public Effect effect309 = null;

            public Parameter effect309Var = null;

            public Effect effect310 = null;

            public Parameter effect310Var = null;

            public Effect effect311 = null;

            public Parameter effect311Var = null;

            public void init_17_0_2_2_edc0357_1357846886877_16383_20069Members() {
                try {
                    if (_17_0_2_2_edc0357_1357846887395_247769_21264 == null) _17_0_2_2_edc0357_1357846887395_247769_21264 = new IntegerParameter("_17_0_2_2_edc0357_1357846887395_247769_21264", (Integer) null, this);
                    if (_17_0_2_2_edc0357_1357846887395_750923_21263 == null) _17_0_2_2_edc0357_1357846887395_750923_21263 = new Parameter<Power>("_17_0_2_2_edc0357_1357846887395_750923_21263", null, (Power) null, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (signalObject == null) signalObject = new Parameter<Power_System.SignalreceiveGenReading>("signalObject", null, (Power_System.SignalreceiveGenReading) null, this);
                    Object effect309VarV = sig_17_0_2_2_edc0357_1357846886880_227877_20079;
                    effect309Var = new Parameter("effect309Var", null, null, this);
                    addDependency(effect309Var, new Expression(effect309VarV));
                    effect309 = new EffectFunction(new EffectFunction(effect309Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect310VarV = new FunctionCall(x, Parameter.class, "getMember", new Object[] { "ss_17_0_2_2_edc0357_1357846886585_816142_19873_receiveGenReading" });
                    effect310Var = new Parameter("effect310Var", null, null, this);
                    addDependency(effect310Var, new Expression(effect310VarV));
                    effect310 = new EffectFunction(new EffectFunction(null, ClassUtils.getMethodForArgTypes("ObjectFlow<SignalreceiveGenReading>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { signalObject, endTime }, effect310Var));
                    Object effect311VarV = new FunctionCall(signalObject, Parameter.class, "getMember", new Object[] { "actual_power__17_0_2_2_edc0357_1357846886651_479103_19962" });
                    effect311Var = new Parameter("effect311Var", null, null, this);
                    addDependency(effect311Var, new Expression(effect311VarV));
                    effect311 = new EffectFunction(new EffectFunction(null, ClassUtils.getMethodForArgTypes("TimeVaryingMap<Integer>", "PowerSystem3", "setValue", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, _17_0_2_2_edc0357_1357846887395_247769_21264 }, effect311Var));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846886877_16383_20069Collections() {
                parameters.add(_17_0_2_2_edc0357_1357846887395_247769_21264);
                parameters.add(_17_0_2_2_edc0357_1357846887395_750923_21263);
                parameters.add(objectToPass);
                parameters.add(signalObject);
                Set<Effect> effectsForeffect309Var = new TreeSet<Effect>();
                effectsForeffect309Var.add(effect309);
                addEffects((Parameter<?>) effect309Var, effectsForeffect309Var);
                Set<Effect> effectsForeffect310Var = new TreeSet<Effect>();
                effectsForeffect310Var.add(effect310);
                addEffects((Parameter<?>) effect310Var, effectsForeffect310Var);
                Set<Effect> effectsForeffect311Var = new TreeSet<Effect>();
                effectsForeffect311Var.add(effect311);
                addEffects((Parameter<?>) effect311Var, effectsForeffect311Var);
            }

            public void init_17_0_2_2_edc0357_1357846886877_16383_20069Dependencies() {
                addDependency(_17_0_2_2_edc0357_1357846887395_247769_21264, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846886880_865963_20074, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_2_edc0357_1357846887395_750923_21263, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846886880_491004_20077, ClassUtils.getMethodForArgTypes("ObjectFlow<Power>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(finalNode_startTime, new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846886880_596148_20078, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(signalObject, new Expression<Power_System.SignalreceiveGenReading>(new ConstructorCall(x, ClassUtils.getConstructorForArgTypes(Power_System.SignalreceiveGenReading.class), new Object[] {})));
            }

            public void init_17_0_2_2_edc0357_1357846886877_16383_20069Elaborations() {
                init_17_0_2_2_edc0357_1357846886877_16383_20069Dependencies();
            }
        }

        public class _17_0_2_2_edc0357_1357846886878_970119_20070 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846886878_970119_20070() {
                super();
                init_17_0_2_2_edc0357_1357846886878_970119_20070Members();
                init_17_0_2_2_edc0357_1357846886878_970119_20070Collections();
                init_17_0_2_2_edc0357_1357846886878_970119_20070Elaborations();
            }

            public _17_0_2_2_edc0357_1357846886878_970119_20070(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846886878_970119_20070Members();
                init_17_0_2_2_edc0357_1357846886878_970119_20070Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846886878_970119_20070Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter addToDecider__17_0_2_2_edc0357_1357846886876_30151_20066 = null;

            public IntegerParameter myDeciderID_decider_17_0_2_2_edc0357_1357846886876_30151_20066 = null;

            public BooleanParameter addToDecider__17_0_2_2_edc0357_1357846886877_16383_20069 = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846886876_30151_20066_exists = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846886877_16383_20069_exists = null;

            public IntegerParameter objectToPass = null;

            public IntegerParameter myDeciderID_decider_17_0_2_2_edc0357_1357846886877_16383_20069 = null;

            public Dependency addToDecider__17_0_2_2_edc0357_1357846886876_30151_20066Dependency = null;

            public Dependency myDeciderID_decider_17_0_2_2_edc0357_1357846886876_30151_20066Dependency = null;

            public Dependency addToDecider__17_0_2_2_edc0357_1357846886877_16383_20069Dependency = null;

            public Dependency _17_0_2_2_edc0357_1357846886876_30151_20066_existsDependency = null;

            public Dependency durationDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846886877_16383_20069_existsDependency = null;

            public Dependency objectToPassDependency = null;

            public Dependency myDeciderID_decider_17_0_2_2_edc0357_1357846886877_16383_20069Dependency = null;

            public Effect effect312 = null;

            public Parameter effect312Var = null;

            public Effect effect313 = null;

            public Parameter effect313Var = null;

            public Effect effect314 = null;

            public Parameter effect314Var = null;

            public Effect effect315 = null;

            public Parameter effect315Var = null;

            public ElaborationRule elaborationRule316 = null;

            public ElaborationRule elaborationRule317 = null;

            public void init_17_0_2_2_edc0357_1357846886878_970119_20070Members() {
                try {
                    if (addToDecider__17_0_2_2_edc0357_1357846886876_30151_20066 == null) addToDecider__17_0_2_2_edc0357_1357846886876_30151_20066 = new BooleanParameter("addToDecider__17_0_2_2_edc0357_1357846886876_30151_20066", (Boolean) false, this);
                    if (myDeciderID_decider_17_0_2_2_edc0357_1357846886876_30151_20066 == null) myDeciderID_decider_17_0_2_2_edc0357_1357846886876_30151_20066 = new IntegerParameter("myDeciderID_decider_17_0_2_2_edc0357_1357846886876_30151_20066", (Integer) 2, this);
                    if (addToDecider__17_0_2_2_edc0357_1357846886877_16383_20069 == null) addToDecider__17_0_2_2_edc0357_1357846886877_16383_20069 = new BooleanParameter("addToDecider__17_0_2_2_edc0357_1357846886877_16383_20069", (Boolean) false, this);
                    if (_17_0_2_2_edc0357_1357846886876_30151_20066_exists == null) _17_0_2_2_edc0357_1357846886876_30151_20066_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846886876_30151_20066_exists", (Boolean) false, this);
                    if (_17_0_2_2_edc0357_1357846886877_16383_20069_exists == null) _17_0_2_2_edc0357_1357846886877_16383_20069_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846886877_16383_20069_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new IntegerParameter("objectToPass", (Integer) null, this);
                    if (myDeciderID_decider_17_0_2_2_edc0357_1357846886877_16383_20069 == null) myDeciderID_decider_17_0_2_2_edc0357_1357846886877_16383_20069 = new IntegerParameter("myDeciderID_decider_17_0_2_2_edc0357_1357846886877_16383_20069", (Integer) 1, this);
                    Object effect312VarV = sig_17_0_2_2_edc0357_1357846886880_763857_20073;
                    effect312Var = new Parameter("effect312Var", null, null, this);
                    addDependency(effect312Var, new Expression(effect312VarV));
                    effect312 = new EffectFunction(new EffectFunction(effect312Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect313VarV = sig_17_0_2_2_edc0357_1357846886880_865963_20074;
                    effect313Var = new Parameter("effect313Var", null, null, this);
                    addDependency(effect313Var, new Expression(effect313VarV));
                    effect313 = new EffectFunction(new EffectFunction(effect313Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect314VarV = decider_17_0_2_2_edc0357_1357846886877_16383_20069;
                    effect314Var = new Parameter("effect314Var", null, null, this);
                    addDependency(effect314Var, new Expression(effect314VarV));
                    effect314 = new EffectFunction(new EffectFunction(effect314Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class, java.lang.Boolean.class), new Object[] { endTime, myDeciderID_decider_17_0_2_2_edc0357_1357846886877_16383_20069, addToDecider__17_0_2_2_edc0357_1357846886877_16383_20069 }));
                    Object effect315VarV = decider_17_0_2_2_edc0357_1357846886876_30151_20066;
                    effect315Var = new Parameter("effect315Var", null, null, this);
                    addDependency(effect315Var, new Expression(effect315VarV));
                    effect315 = new EffectFunction(new EffectFunction(effect315Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class, java.lang.Boolean.class), new Object[] { endTime, myDeciderID_decider_17_0_2_2_edc0357_1357846886876_30151_20066, addToDecider__17_0_2_2_edc0357_1357846886876_30151_20066 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846886878_970119_20070Collections() {
                parameters.add(addToDecider__17_0_2_2_edc0357_1357846886876_30151_20066);
                parameters.add(myDeciderID_decider_17_0_2_2_edc0357_1357846886876_30151_20066);
                parameters.add(addToDecider__17_0_2_2_edc0357_1357846886877_16383_20069);
                parameters.add(_17_0_2_2_edc0357_1357846886876_30151_20066_exists);
                parameters.add(_17_0_2_2_edc0357_1357846886877_16383_20069_exists);
                parameters.add(objectToPass);
                parameters.add(myDeciderID_decider_17_0_2_2_edc0357_1357846886877_16383_20069);
                Set<Effect> effectsForeffect312Var = new TreeSet<Effect>();
                effectsForeffect312Var.add(effect312);
                addEffects((Parameter<?>) effect312Var, effectsForeffect312Var);
                Set<Effect> effectsForeffect313Var = new TreeSet<Effect>();
                effectsForeffect313Var.add(effect313);
                addEffects((Parameter<?>) effect313Var, effectsForeffect313Var);
                Set<Effect> effectsForeffect314Var = new TreeSet<Effect>();
                effectsForeffect314Var.add(effect314);
                addEffects((Parameter<?>) effect314Var, effectsForeffect314Var);
                Set<Effect> effectsForeffect315Var = new TreeSet<Effect>();
                effectsForeffect315Var.add(effect315);
                addEffects((Parameter<?>) effect315Var, effectsForeffect315Var);
            }

            public void init_17_0_2_2_edc0357_1357846886878_970119_20070Dependencies() {
                addDependency(addToDecider__17_0_2_2_edc0357_1357846886876_30151_20066, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886877_335300_20068_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886877_335300_20068_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(myDeciderID_decider_17_0_2_2_edc0357_1357846886876_30151_20066, new Expression<Integer>(2));
                addDependency(addToDecider__17_0_2_2_edc0357_1357846886877_16383_20069, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886877_335300_20068_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886877_335300_20068_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(_17_0_2_2_edc0357_1357846886876_30151_20066_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886877_335300_20068_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886877_335300_20068_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846886876_30151_20066, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846886876_30151_20066, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846886876_30151_20066, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_2_edc0357_1357846886876_30151_20066)))))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(_17_0_2_2_edc0357_1357846886877_16383_20069_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886877_335300_20068_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886877_335300_20068_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846886877_16383_20069, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846886877_16383_20069, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846886877_16383_20069, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_2_edc0357_1357846886877_16383_20069)))))))));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846886881_357221_20080, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(myDeciderID_decider_17_0_2_2_edc0357_1357846886877_16383_20069, new Expression<Integer>(1));
            }

            public void init_17_0_2_2_edc0357_1357846886878_970119_20070Elaborations() {
                init_17_0_2_2_edc0357_1357846886878_970119_20070Dependencies();
                Expression<?>[] arguments316 = new Expression<?>[1];
                arguments316[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition316 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846886877_16383_20069_exists);
                elaborationRule316 = addElaborationRule(condition316, _17_0_2_2_edc0357_1357846886577_262509_19863.this, Power._17_0_2_2_edc0357_1357846886577_262509_19863._17_0_2_2_edc0357_1357846886877_16383_20069.class, "Pchange_send_change_confirm_SendSignalAction_changeGenerationValue", arguments316);
                Expression<?>[] arguments317 = new Expression<?>[1];
                arguments317[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition317 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846886876_30151_20066_exists);
                elaborationRule317 = addElaborationRule(condition317, _17_0_2_2_edc0357_1357846886577_262509_19863.this, Power._17_0_2_2_edc0357_1357846886577_262509_19863._17_0_2_2_edc0357_1357846886876_30151_20066.class, "Pchange_set_aug_AddStructuralFeatureValueAction_changeGenerationValue", arguments317);
            }
        }

        public class _17_0_2_2_edc0357_1357846886879_399277_20071 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846886879_399277_20071() {
                super();
                init_17_0_2_2_edc0357_1357846886879_399277_20071Members();
                init_17_0_2_2_edc0357_1357846886879_399277_20071Collections();
                init_17_0_2_2_edc0357_1357846886879_399277_20071Elaborations();
            }

            public _17_0_2_2_edc0357_1357846886879_399277_20071(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846886879_399277_20071Members();
                init_17_0_2_2_edc0357_1357846886879_399277_20071Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846886879_399277_20071Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter addToDecider__17_0_2_2_edc0357_1357846886876_30151_20066 = null;

            public IntegerParameter myDeciderID_decider_17_0_2_2_edc0357_1357846886876_30151_20066 = null;

            public BooleanParameter addToDecider__17_0_2_2_edc0357_1357846886877_16383_20069 = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846886876_30151_20066_exists = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846886877_16383_20069_exists = null;

            public Parameter< Power > objectToPass = null;

            public IntegerParameter myDeciderID_decider_17_0_2_2_edc0357_1357846886877_16383_20069 = null;

            public Dependency addToDecider__17_0_2_2_edc0357_1357846886876_30151_20066Dependency = null;

            public Dependency myDeciderID_decider_17_0_2_2_edc0357_1357846886876_30151_20066Dependency = null;

            public Dependency addToDecider__17_0_2_2_edc0357_1357846886877_16383_20069Dependency = null;

            public Dependency _17_0_2_2_edc0357_1357846886876_30151_20066_existsDependency = null;

            public Dependency durationDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846886877_16383_20069_existsDependency = null;

            public Dependency objectToPassDependency = null;

            public Dependency myDeciderID_decider_17_0_2_2_edc0357_1357846886877_16383_20069Dependency = null;

            public Effect effect318 = null;

            public Parameter effect318Var = null;

            public Effect effect319 = null;

            public Parameter effect319Var = null;

            public Effect effect320 = null;

            public Parameter effect320Var = null;

            public Effect effect321 = null;

            public Parameter effect321Var = null;

            public ElaborationRule elaborationRule322 = null;

            public ElaborationRule elaborationRule323 = null;

            public void init_17_0_2_2_edc0357_1357846886879_399277_20071Members() {
                try {
                    if (addToDecider__17_0_2_2_edc0357_1357846886876_30151_20066 == null) addToDecider__17_0_2_2_edc0357_1357846886876_30151_20066 = new BooleanParameter("addToDecider__17_0_2_2_edc0357_1357846886876_30151_20066", (Boolean) false, this);
                    if (myDeciderID_decider_17_0_2_2_edc0357_1357846886876_30151_20066 == null) myDeciderID_decider_17_0_2_2_edc0357_1357846886876_30151_20066 = new IntegerParameter("myDeciderID_decider_17_0_2_2_edc0357_1357846886876_30151_20066", (Integer) 1, this);
                    if (addToDecider__17_0_2_2_edc0357_1357846886877_16383_20069 == null) addToDecider__17_0_2_2_edc0357_1357846886877_16383_20069 = new BooleanParameter("addToDecider__17_0_2_2_edc0357_1357846886877_16383_20069", (Boolean) false, this);
                    if (_17_0_2_2_edc0357_1357846886876_30151_20066_exists == null) _17_0_2_2_edc0357_1357846886876_30151_20066_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846886876_30151_20066_exists", (Boolean) false, this);
                    if (_17_0_2_2_edc0357_1357846886877_16383_20069_exists == null) _17_0_2_2_edc0357_1357846886877_16383_20069_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846886877_16383_20069_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new Parameter<Power>("objectToPass", null, (Power) null, this);
                    if (myDeciderID_decider_17_0_2_2_edc0357_1357846886877_16383_20069 == null) myDeciderID_decider_17_0_2_2_edc0357_1357846886877_16383_20069 = new IntegerParameter("myDeciderID_decider_17_0_2_2_edc0357_1357846886877_16383_20069", (Integer) 2, this);
                    Object effect318VarV = sig_17_0_2_2_edc0357_1357846886880_601239_20076;
                    effect318Var = new Parameter("effect318Var", null, null, this);
                    addDependency(effect318Var, new Expression(effect318VarV));
                    effect318 = new EffectFunction(new EffectFunction(effect318Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Power>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect319VarV = sig_17_0_2_2_edc0357_1357846886880_491004_20077;
                    effect319Var = new Parameter("effect319Var", null, null, this);
                    addDependency(effect319Var, new Expression(effect319VarV));
                    effect319 = new EffectFunction(new EffectFunction(effect319Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Power>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect320VarV = decider_17_0_2_2_edc0357_1357846886877_16383_20069;
                    effect320Var = new Parameter("effect320Var", null, null, this);
                    addDependency(effect320Var, new Expression(effect320VarV));
                    effect320 = new EffectFunction(new EffectFunction(effect320Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class, java.lang.Boolean.class), new Object[] { endTime, myDeciderID_decider_17_0_2_2_edc0357_1357846886877_16383_20069, addToDecider__17_0_2_2_edc0357_1357846886877_16383_20069 }));
                    Object effect321VarV = decider_17_0_2_2_edc0357_1357846886876_30151_20066;
                    effect321Var = new Parameter("effect321Var", null, null, this);
                    addDependency(effect321Var, new Expression(effect321VarV));
                    effect321 = new EffectFunction(new EffectFunction(effect321Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class, java.lang.Boolean.class), new Object[] { endTime, myDeciderID_decider_17_0_2_2_edc0357_1357846886876_30151_20066, addToDecider__17_0_2_2_edc0357_1357846886876_30151_20066 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846886879_399277_20071Collections() {
                parameters.add(addToDecider__17_0_2_2_edc0357_1357846886876_30151_20066);
                parameters.add(myDeciderID_decider_17_0_2_2_edc0357_1357846886876_30151_20066);
                parameters.add(addToDecider__17_0_2_2_edc0357_1357846886877_16383_20069);
                parameters.add(_17_0_2_2_edc0357_1357846886876_30151_20066_exists);
                parameters.add(_17_0_2_2_edc0357_1357846886877_16383_20069_exists);
                parameters.add(objectToPass);
                parameters.add(myDeciderID_decider_17_0_2_2_edc0357_1357846886877_16383_20069);
                Set<Effect> effectsForeffect318Var = new TreeSet<Effect>();
                effectsForeffect318Var.add(effect318);
                addEffects((Parameter<?>) effect318Var, effectsForeffect318Var);
                Set<Effect> effectsForeffect319Var = new TreeSet<Effect>();
                effectsForeffect319Var.add(effect319);
                addEffects((Parameter<?>) effect319Var, effectsForeffect319Var);
                Set<Effect> effectsForeffect320Var = new TreeSet<Effect>();
                effectsForeffect320Var.add(effect320);
                addEffects((Parameter<?>) effect320Var, effectsForeffect320Var);
                Set<Effect> effectsForeffect321Var = new TreeSet<Effect>();
                effectsForeffect321Var.add(effect321);
                addEffects((Parameter<?>) effect321Var, effectsForeffect321Var);
            }

            public void init_17_0_2_2_edc0357_1357846886879_399277_20071Dependencies() {
                addDependency(addToDecider__17_0_2_2_edc0357_1357846886876_30151_20066, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886877_335300_20068_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886877_335300_20068_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(myDeciderID_decider_17_0_2_2_edc0357_1357846886876_30151_20066, new Expression<Integer>(1));
                addDependency(addToDecider__17_0_2_2_edc0357_1357846886877_16383_20069, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886877_335300_20068_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886877_335300_20068_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(_17_0_2_2_edc0357_1357846886876_30151_20066_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886877_335300_20068_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886877_335300_20068_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846886876_30151_20066, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846886876_30151_20066, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846886876_30151_20066, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_2_edc0357_1357846886876_30151_20066)))))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(_17_0_2_2_edc0357_1357846886877_16383_20069_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886877_335300_20068_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886877_335300_20068_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846886877_16383_20069, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846886877_16383_20069, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846886877_16383_20069, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_2_edc0357_1357846886877_16383_20069)))))))));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846886880_502535_20075, ClassUtils.getMethodForArgTypes("ObjectFlow<Power>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(myDeciderID_decider_17_0_2_2_edc0357_1357846886877_16383_20069, new Expression<Integer>(2));
            }

            public void init_17_0_2_2_edc0357_1357846886879_399277_20071Elaborations() {
                init_17_0_2_2_edc0357_1357846886879_399277_20071Dependencies();
                Expression<?>[] arguments322 = new Expression<?>[1];
                arguments322[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition322 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846886877_16383_20069_exists);
                elaborationRule322 = addElaborationRule(condition322, _17_0_2_2_edc0357_1357846886577_262509_19863.this, Power._17_0_2_2_edc0357_1357846886577_262509_19863._17_0_2_2_edc0357_1357846886877_16383_20069.class, "Pchange_send_change_confirm_SendSignalAction_changeGenerationValue", arguments322);
                Expression<?>[] arguments323 = new Expression<?>[1];
                arguments323[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition323 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846886876_30151_20066_exists);
                elaborationRule323 = addElaborationRule(condition323, _17_0_2_2_edc0357_1357846886577_262509_19863.this, Power._17_0_2_2_edc0357_1357846886577_262509_19863._17_0_2_2_edc0357_1357846886876_30151_20066.class, "Pchange_set_aug_AddStructuralFeatureValueAction_changeGenerationValue", arguments323);
            }
        }

        public class _17_0_2_2_edc0357_1357846886879_12839_20072 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846886879_12839_20072() {
                super();
                init_17_0_2_2_edc0357_1357846886879_12839_20072Members();
                init_17_0_2_2_edc0357_1357846886879_12839_20072Collections();
                init_17_0_2_2_edc0357_1357846886879_12839_20072Elaborations();
            }

            public _17_0_2_2_edc0357_1357846886879_12839_20072(Expression<Integer> startTime, Expression<Integer> _17_0_2_2_edc0357_1357846886862_684005_20043) {
                super();
                init_17_0_2_2_edc0357_1357846886879_12839_20072Members();
                init_17_0_2_2_edc0357_1357846886879_12839_20072Collections();
                addDependency(this.startTime, startTime);
                addDependency(this._17_0_2_2_edc0357_1357846886862_684005_20043, _17_0_2_2_edc0357_1357846886862_684005_20043);
                init_17_0_2_2_edc0357_1357846886879_12839_20072Elaborations();
                fixTimeDependencies();
            }

            public IntegerParameter _17_0_2_2_edc0357_1357846886862_684005_20043 = null;

            public IntegerParameter objectToPass = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846886878_970119_20070_exists = null;

            public Dependency objectToPassDependency = null;

            public Dependency durationDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846886878_970119_20070_existsDependency = null;

            public Effect effect324 = null;

            public Parameter effect324Var = null;

            public ElaborationRule elaborationRule325 = null;

            public void init_17_0_2_2_edc0357_1357846886879_12839_20072Members() {
                try {
                    if (_17_0_2_2_edc0357_1357846886862_684005_20043 == null) _17_0_2_2_edc0357_1357846886862_684005_20043 = new IntegerParameter("_17_0_2_2_edc0357_1357846886862_684005_20043", (Integer) null, this);
                    if (objectToPass == null) objectToPass = new IntegerParameter("objectToPass", (Integer) null, this);
                    if (_17_0_2_2_edc0357_1357846886878_970119_20070_exists == null) _17_0_2_2_edc0357_1357846886878_970119_20070_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846886878_970119_20070_exists", (Boolean) false, this);
                    Object effect324VarV = sig_17_0_2_2_edc0357_1357846886881_357221_20080;
                    effect324Var = new Parameter("effect324Var", null, null, this);
                    addDependency(effect324Var, new Expression(effect324VarV));
                    effect324 = new EffectFunction(new EffectFunction(effect324Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846886879_12839_20072Collections() {
                parameters.add(_17_0_2_2_edc0357_1357846886862_684005_20043);
                parameters.add(objectToPass);
                parameters.add(_17_0_2_2_edc0357_1357846886878_970119_20070_exists);
                Set<Effect> effectsForeffect324Var = new TreeSet<Effect>();
                effectsForeffect324Var.add(effect324);
                addEffects((Parameter<?>) effect324Var, effectsForeffect324Var);
            }

            public void init_17_0_2_2_edc0357_1357846886879_12839_20072Dependencies() {
                addDependency(objectToPass, new Expression<Integer>(_17_0_2_2_edc0357_1357846886862_684005_20043));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(_17_0_2_2_edc0357_1357846886878_970119_20070_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886877_335300_20068_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886877_335300_20068_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
            }

            public void init_17_0_2_2_edc0357_1357846886879_12839_20072Elaborations() {
                init_17_0_2_2_edc0357_1357846886879_12839_20072Dependencies();
                Expression<?>[] arguments325 = new Expression<?>[1];
                arguments325[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition325 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846886878_970119_20070_exists);
                elaborationRule325 = addElaborationRule(condition325, _17_0_2_2_edc0357_1357846886577_262509_19863.this, Power._17_0_2_2_edc0357_1357846886577_262509_19863._17_0_2_2_edc0357_1357846886878_970119_20070.class, "Pchange_param_fork_ForkNode_changeGenerationValue", arguments325);
            }
        }

        public _17_0_2_2_edc0357_1357846886577_262509_19863(Expression<Integer> startTime, Expression<_17_0_2_2_edc0357_1357846886577_262509_19863_interface> caller, Expression<Integer> _17_0_2_2_edc0357_1357846886862_684005_20043) {
            super();
            init_17_0_2_2_edc0357_1357846886577_262509_19863Members();
            init_17_0_2_2_edc0357_1357846886577_262509_19863Collections();
            addDependency(this.startTime, startTime);
            addDependency(this.caller, caller);
            addDependency(this._17_0_2_2_edc0357_1357846886862_684005_20043, _17_0_2_2_edc0357_1357846886862_684005_20043);
            init_17_0_2_2_edc0357_1357846886577_262509_19863Elaborations();
            fixTimeDependencies();
        }

        public Parameter< ObjectFlow<Integer> > sig_17_0_2_2_edc0357_1357846886880_763857_20073 = null;

        public Parameter< Power._17_0_2_2_edc0357_1357846886577_262509_19863_interface > caller = null;

        public Parameter< ObjectFlow<Power> > sig_17_0_2_2_edc0357_1357846886880_491004_20077 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_2_edc0357_1357846886880_227877_20079 = null;

        public BooleanParameter _17_0_2_2_edc0357_1357846886877_335300_20068_exists = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_2_edc0357_1357846886881_299607_20081 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_2_2_edc0357_1357846886881_357221_20080 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_2_2_edc0357_1357846886880_865963_20074 = null;

        public IntegerParameter _17_0_2_2_edc0357_1357846886862_684005_20043 = null;

        public IntegerParameter finalNode_startTime = null;

        public Parameter< TimeVaryingList<Integer> > decider_17_0_2_2_edc0357_1357846886876_30151_20066 = null;

        public Parameter< ObjectFlow<Power> > sig_17_0_2_2_edc0357_1357846886880_601239_20076 = null;

        public Parameter< ObjectFlow<Power> > sig_17_0_2_2_edc0357_1357846886880_502535_20075 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_2_edc0357_1357846886880_596148_20078 = null;

        public IntegerParameter finalNode_endTime = null;

        public Parameter< TimeVaryingList<Integer> > decider_17_0_2_2_edc0357_1357846886877_16383_20069 = null;

        public Dependency _17_0_2_2_edc0357_1357846886877_335300_20068_existsDependency = null;

        public Dependency caller_endTimeDependency = null;

        public Dependency endTimeDependency = null;

        public ElaborationRule elaborationRule298 = null;

        public ElaborationRule elaborationRule299 = null;

        public ElaborationRule elaborationRule300 = null;

        public void init_17_0_2_2_edc0357_1357846886577_262509_19863Members() {
            try {
                if (sig_17_0_2_2_edc0357_1357846886880_763857_20073 == null) sig_17_0_2_2_edc0357_1357846886880_763857_20073 = new Parameter<ObjectFlow<Integer>>("sig_17_0_2_2_edc0357_1357846886880_763857_20073", null, (ObjectFlow<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846886880_763857_20073" })).evaluate(true), this);
                if (caller == null) caller = new Parameter<Power._17_0_2_2_edc0357_1357846886577_262509_19863_interface>("caller", null, (Power._17_0_2_2_edc0357_1357846886577_262509_19863_interface) null, this);
                if (sig_17_0_2_2_edc0357_1357846886880_491004_20077 == null) sig_17_0_2_2_edc0357_1357846886880_491004_20077 = new Parameter<ObjectFlow<Power>>("sig_17_0_2_2_edc0357_1357846886880_491004_20077", null, (ObjectFlow<Power>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846886880_491004_20077" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846886880_227877_20079 == null) sig_17_0_2_2_edc0357_1357846886880_227877_20079 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_2_edc0357_1357846886880_227877_20079", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846886880_227877_20079" })).evaluate(true), this);
                if (_17_0_2_2_edc0357_1357846886877_335300_20068_exists == null) _17_0_2_2_edc0357_1357846886877_335300_20068_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846886877_335300_20068_exists", (Boolean) false, this);
                if (sig_17_0_2_2_edc0357_1357846886881_299607_20081 == null) sig_17_0_2_2_edc0357_1357846886881_299607_20081 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_2_edc0357_1357846886881_299607_20081", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846886881_299607_20081" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846886881_357221_20080 == null) sig_17_0_2_2_edc0357_1357846886881_357221_20080 = new Parameter<ObjectFlow<Integer>>("sig_17_0_2_2_edc0357_1357846886881_357221_20080", null, (ObjectFlow<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846886881_357221_20080" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846886880_865963_20074 == null) sig_17_0_2_2_edc0357_1357846886880_865963_20074 = new Parameter<ObjectFlow<Integer>>("sig_17_0_2_2_edc0357_1357846886880_865963_20074", null, (ObjectFlow<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846886880_865963_20074" })).evaluate(true), this);
                if (_17_0_2_2_edc0357_1357846886862_684005_20043 == null) _17_0_2_2_edc0357_1357846886862_684005_20043 = new IntegerParameter("_17_0_2_2_edc0357_1357846886862_684005_20043", (Integer) null, this);
                if (finalNode_startTime == null) finalNode_startTime = new IntegerParameter("finalNode_startTime", (Integer) null, this);
                if (decider_17_0_2_2_edc0357_1357846886876_30151_20066 == null) decider_17_0_2_2_edc0357_1357846886876_30151_20066 = new Parameter<TimeVaryingList<Integer>>("decider_17_0_2_2_edc0357_1357846886876_30151_20066", null, (TimeVaryingList<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingList.class, java.lang.String.class, int.class), new Object[] { "decider_17_0_2_2_edc0357_1357846886876_30151_20066", 2 })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846886880_601239_20076 == null) sig_17_0_2_2_edc0357_1357846886880_601239_20076 = new Parameter<ObjectFlow<Power>>("sig_17_0_2_2_edc0357_1357846886880_601239_20076", null, (ObjectFlow<Power>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846886880_601239_20076" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846886880_502535_20075 == null) sig_17_0_2_2_edc0357_1357846886880_502535_20075 = new Parameter<ObjectFlow<Power>>("sig_17_0_2_2_edc0357_1357846886880_502535_20075", null, (ObjectFlow<Power>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846886880_502535_20075" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846886880_596148_20078 == null) sig_17_0_2_2_edc0357_1357846886880_596148_20078 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_2_edc0357_1357846886880_596148_20078", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846886880_596148_20078" })).evaluate(true), this);
                if (finalNode_endTime == null) finalNode_endTime = new IntegerParameter("finalNode_endTime", (Integer) null, this);
                if (decider_17_0_2_2_edc0357_1357846886877_16383_20069 == null) decider_17_0_2_2_edc0357_1357846886877_16383_20069 = new Parameter<TimeVaryingList<Integer>>("decider_17_0_2_2_edc0357_1357846886877_16383_20069", null, (TimeVaryingList<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingList.class, java.lang.String.class, int.class), new Object[] { "decider_17_0_2_2_edc0357_1357846886877_16383_20069", 3 })).evaluate(true), this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void init_17_0_2_2_edc0357_1357846886577_262509_19863Collections() {
            parameters.add(sig_17_0_2_2_edc0357_1357846886880_763857_20073);
            parameters.add(caller);
            parameters.add(sig_17_0_2_2_edc0357_1357846886880_491004_20077);
            parameters.add(sig_17_0_2_2_edc0357_1357846886880_227877_20079);
            parameters.add(_17_0_2_2_edc0357_1357846886877_335300_20068_exists);
            parameters.add(sig_17_0_2_2_edc0357_1357846886881_299607_20081);
            parameters.add(sig_17_0_2_2_edc0357_1357846886881_357221_20080);
            parameters.add(sig_17_0_2_2_edc0357_1357846886880_865963_20074);
            parameters.add(_17_0_2_2_edc0357_1357846886862_684005_20043);
            parameters.add(finalNode_startTime);
            parameters.add(decider_17_0_2_2_edc0357_1357846886876_30151_20066);
            parameters.add(sig_17_0_2_2_edc0357_1357846886880_601239_20076);
            parameters.add(sig_17_0_2_2_edc0357_1357846886880_502535_20075);
            parameters.add(sig_17_0_2_2_edc0357_1357846886880_596148_20078);
            parameters.add(finalNode_endTime);
            parameters.add(decider_17_0_2_2_edc0357_1357846886877_16383_20069);
        }

        public void init_17_0_2_2_edc0357_1357846886577_262509_19863Dependencies() {
            addDependency(_17_0_2_2_edc0357_1357846886877_335300_20068_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.NotEquals(new Expression<Integer>(finalNode_startTime), null)), new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846886880_227877_20079, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(new Functions.Minus(new Expression<Integer>(finalNode_startTime), new Expression<Integer>(1))) })))));
            caller.getValue(true).addDependency(caller.getValue(true).endTime, new Expression<Integer>(finalNode_endTime));
            addDependency(endTime, new Expression<Integer>(finalNode_endTime));
        }

        public void init_17_0_2_2_edc0357_1357846886577_262509_19863Elaborations() {
            init_17_0_2_2_edc0357_1357846886577_262509_19863Dependencies();
            Expression<?>[] arguments298 = new Expression<?>[1];
            arguments298[0] = new Expression<Integer>(startTime);
            Expression<Boolean> condition298 = new Expression<Boolean>(true);
            elaborationRule298 = addElaborationRule(condition298, _17_0_2_2_edc0357_1357846886577_262509_19863.this, Power._17_0_2_2_edc0357_1357846886577_262509_19863._17_0_2_2_edc0357_1357846886876_470136_20067.class, "Pchange_start_InitialNode_changeGenerationValue", arguments298);
            Expression<?>[] arguments299 = new Expression<?>[2];
            arguments299[0] = new Expression<Integer>(startTime);
            arguments299[1] = new Expression<Integer>(_17_0_2_2_edc0357_1357846886862_684005_20043);
            Expression<Boolean> condition299 = new Expression<Boolean>(true);
            elaborationRule299 = addElaborationRule(condition299, _17_0_2_2_edc0357_1357846886577_262509_19863.this, Power._17_0_2_2_edc0357_1357846886577_262509_19863._17_0_2_2_edc0357_1357846886879_12839_20072.class, "Pchange_newGenVal_ActivityParameterNode_changeGenerationValue", arguments299);
            Expression<?>[] arguments300 = new Expression<?>[1];
            arguments300[0] = new Expression<Integer>(finalNode_startTime);
            Expression<Boolean> condition300 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846886877_335300_20068_exists);
            elaborationRule300 = addElaborationRule(condition300, _17_0_2_2_edc0357_1357846886577_262509_19863.this, Power._17_0_2_2_edc0357_1357846886577_262509_19863._17_0_2_2_edc0357_1357846886877_335300_20068.class, "Pchange_end_ActivityFinalNode_changeGenerationValue", arguments300);
        }
    }

    public class _17_0_2_2_edc0357_1357846886577_262509_19863_interface extends DurativeEvent {

        public _17_0_2_2_edc0357_1357846886577_262509_19863_interface() {
            super();
            init_17_0_2_2_edc0357_1357846886577_262509_19863_interfaceMembers();
            init_17_0_2_2_edc0357_1357846886577_262509_19863_interfaceCollections();
            init_17_0_2_2_edc0357_1357846886577_262509_19863_interfaceElaborations();
        }

        public IntegerParameter _17_0_2_2_edc0357_1357846886862_684005_20043 = null;

        public void init_17_0_2_2_edc0357_1357846886577_262509_19863_interfaceMembers() {
            try {
                if (_17_0_2_2_edc0357_1357846886862_684005_20043 == null) _17_0_2_2_edc0357_1357846886862_684005_20043 = new IntegerParameter("_17_0_2_2_edc0357_1357846886862_684005_20043", (Integer) null, this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void init_17_0_2_2_edc0357_1357846886577_262509_19863_interfaceCollections() {
            parameters.add(_17_0_2_2_edc0357_1357846886862_684005_20043);
        }

        public void init_17_0_2_2_edc0357_1357846886577_262509_19863_interfaceDependencies() {
        }

        public void init_17_0_2_2_edc0357_1357846886577_262509_19863_interfaceElaborations() {
            init_17_0_2_2_edc0357_1357846886577_262509_19863_interfaceDependencies();
        }
    }

    public class _17_0_2_2_edc0357_1357846886579_143744_19864 extends DurativeEvent {

        public _17_0_2_2_edc0357_1357846886579_143744_19864() {
            super();
            init_17_0_2_2_edc0357_1357846886579_143744_19864Members();
            init_17_0_2_2_edc0357_1357846886579_143744_19864Collections();
            init_17_0_2_2_edc0357_1357846886579_143744_19864Elaborations();
        }

        public class _17_0_2_2_edc0357_1357846886896_31635_20104 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846886896_31635_20104() {
                super();
                init_17_0_2_2_edc0357_1357846886896_31635_20104Members();
                init_17_0_2_2_edc0357_1357846886896_31635_20104Collections();
                init_17_0_2_2_edc0357_1357846886896_31635_20104Elaborations();
            }

            public _17_0_2_2_edc0357_1357846886896_31635_20104(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846886896_31635_20104Members();
                init_17_0_2_2_edc0357_1357846886896_31635_20104Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846886896_31635_20104Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter addToDecider__17_0_2_2_edc0357_1357846886896_830832_20105 = null;

            public Parameter< Power > _17_0_2_2_edc0357_1357846887398_507647_21275 = null;

            public IntegerParameter myDeciderID_decider_17_0_2_2_edc0357_1357846886896_830832_20105 = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846886896_830832_20105_exists = null;

            public BooleanParameter objectToPass = null;

            public Dependency addToDecider__17_0_2_2_edc0357_1357846886896_830832_20105Dependency = null;

            public Dependency myDeciderID_decider_17_0_2_2_edc0357_1357846886896_830832_20105Dependency = null;

            public Dependency durationDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846886896_830832_20105_existsDependency = null;

            public Dependency objectToPassDependency = null;

            public Effect effect329 = null;

            public Parameter effect329Var = null;

            public Effect effect330 = null;

            public Parameter effect330Var = null;

            public ElaborationRule elaborationRule331 = null;

            public void init_17_0_2_2_edc0357_1357846886896_31635_20104Members() {
                try {
                    if (addToDecider__17_0_2_2_edc0357_1357846886896_830832_20105 == null) addToDecider__17_0_2_2_edc0357_1357846886896_830832_20105 = new BooleanParameter("addToDecider__17_0_2_2_edc0357_1357846886896_830832_20105", (Boolean) false, this);
                    if (_17_0_2_2_edc0357_1357846887398_507647_21275 == null) _17_0_2_2_edc0357_1357846887398_507647_21275 = new Parameter<Power>("_17_0_2_2_edc0357_1357846887398_507647_21275", null, (Power) Power.this, this);
                    if (myDeciderID_decider_17_0_2_2_edc0357_1357846886896_830832_20105 == null) myDeciderID_decider_17_0_2_2_edc0357_1357846886896_830832_20105 = new IntegerParameter("myDeciderID_decider_17_0_2_2_edc0357_1357846886896_830832_20105", (Integer) 1, this);
                    if (_17_0_2_2_edc0357_1357846886896_830832_20105_exists == null) _17_0_2_2_edc0357_1357846886896_830832_20105_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846886896_830832_20105_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect329VarV = sig_17_0_2_2_edc0357_1357846886899_924277_20112;
                    effect329Var = new Parameter("effect329Var", null, null, this);
                    addDependency(effect329Var, new Expression(effect329VarV));
                    effect329 = new EffectFunction(new EffectFunction(effect329Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Power>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { _17_0_2_2_edc0357_1357846887398_507647_21275, endTime }));
                    Object effect330VarV = decider_17_0_2_2_edc0357_1357846886896_830832_20105;
                    effect330Var = new Parameter("effect330Var", null, null, this);
                    addDependency(effect330Var, new Expression(effect330VarV));
                    effect330 = new EffectFunction(new EffectFunction(effect330Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class, java.lang.Boolean.class), new Object[] { endTime, myDeciderID_decider_17_0_2_2_edc0357_1357846886896_830832_20105, addToDecider__17_0_2_2_edc0357_1357846886896_830832_20105 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846886896_31635_20104Collections() {
                parameters.add(addToDecider__17_0_2_2_edc0357_1357846886896_830832_20105);
                parameters.add(_17_0_2_2_edc0357_1357846887398_507647_21275);
                parameters.add(myDeciderID_decider_17_0_2_2_edc0357_1357846886896_830832_20105);
                parameters.add(_17_0_2_2_edc0357_1357846886896_830832_20105_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect329Var = new TreeSet<Effect>();
                effectsForeffect329Var.add(effect329);
                addEffects((Parameter<?>) effect329Var, effectsForeffect329Var);
                Set<Effect> effectsForeffect330Var = new TreeSet<Effect>();
                effectsForeffect330Var.add(effect330);
                addEffects((Parameter<?>) effect330Var, effectsForeffect330Var);
            }

            public void init_17_0_2_2_edc0357_1357846886896_31635_20104Dependencies() {
                addDependency(addToDecider__17_0_2_2_edc0357_1357846886896_830832_20105, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886897_862037_20107_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886897_862037_20107_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(myDeciderID_decider_17_0_2_2_edc0357_1357846886896_830832_20105, new Expression<Integer>(1));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(_17_0_2_2_edc0357_1357846886896_830832_20105_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886897_862037_20107_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886897_862037_20107_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846886896_830832_20105, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846886896_830832_20105, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846886896_830832_20105, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_2_edc0357_1357846886896_830832_20105)))))))));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846886899_209424_20115, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_2_edc0357_1357846886896_31635_20104Elaborations() {
                init_17_0_2_2_edc0357_1357846886896_31635_20104Dependencies();
                Expression<?>[] arguments331 = new Expression<?>[1];
                arguments331[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition331 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846886896_830832_20105_exists);
                elaborationRule331 = addElaborationRule(condition331, _17_0_2_2_edc0357_1357846886579_143744_19864.this, Power._17_0_2_2_edc0357_1357846886579_143744_19864._17_0_2_2_edc0357_1357846886896_830832_20105.class, "Pload_update_load_AddStructuralFeatureValueAction_changeLoadValue", arguments331);
            }
        }

        public class _17_0_2_2_edc0357_1357846886896_830832_20105 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846886896_830832_20105() {
                super();
                init_17_0_2_2_edc0357_1357846886896_830832_20105Members();
                init_17_0_2_2_edc0357_1357846886896_830832_20105Collections();
                init_17_0_2_2_edc0357_1357846886896_830832_20105Elaborations();
            }

            public _17_0_2_2_edc0357_1357846886896_830832_20105(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846886896_830832_20105Members();
                init_17_0_2_2_edc0357_1357846886896_830832_20105Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846886896_830832_20105Elaborations();
                fixTimeDependencies();
            }

            public IntegerParameter _17_0_2_2_edc0357_1357846887399_399074_21276 = null;

            public Parameter< Power > _17_0_2_2_edc0357_1357846887399_892752_21277 = null;

            public BooleanParameter objectToPass = null;

            public Dependency _17_0_2_2_edc0357_1357846887399_399074_21276Dependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887399_892752_21277Dependency = null;

            public Dependency finalNode_startTimeDependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Effect effect332 = null;

            public Parameter effect332Var = null;

            public Effect effect333 = null;

            public Parameter effect333Var = null;

            public void init_17_0_2_2_edc0357_1357846886896_830832_20105Members() {
                try {
                    if (_17_0_2_2_edc0357_1357846887399_399074_21276 == null) _17_0_2_2_edc0357_1357846887399_399074_21276 = new IntegerParameter("_17_0_2_2_edc0357_1357846887399_399074_21276", (Integer) null, this);
                    if (_17_0_2_2_edc0357_1357846887399_892752_21277 == null) _17_0_2_2_edc0357_1357846887399_892752_21277 = new Parameter<Power>("_17_0_2_2_edc0357_1357846887399_892752_21277", null, (Power) null, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect332VarV = sig_17_0_2_2_edc0357_1357846886899_325044_20113;
                    effect332Var = new Parameter("effect332Var", null, null, this);
                    addDependency(effect332Var, new Expression(effect332VarV));
                    effect332 = new EffectFunction(new EffectFunction(effect332Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect333VarV = load__17_0_2_2_edc0357_1357846886583_892776_19869;
                    effect333Var = new Parameter("effect333Var", null, null, this);
                    addDependency(effect333Var, new Expression(effect333VarV));
                    effect333 = new EffectFunction(new EffectFunction(effect333Var, ClassUtils.getMethodForArgTypes("TimeVaryingPlottableMap<Integer>", "PowerSystem3", "setValue", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { startTime, _17_0_2_2_edc0357_1357846887399_399074_21276 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846886896_830832_20105Collections() {
                parameters.add(_17_0_2_2_edc0357_1357846887399_399074_21276);
                parameters.add(_17_0_2_2_edc0357_1357846887399_892752_21277);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect332Var = new TreeSet<Effect>();
                effectsForeffect332Var.add(effect332);
                addEffects((Parameter<?>) effect332Var, effectsForeffect332Var);
                Set<Effect> effectsForeffect333Var = new TreeSet<Effect>();
                effectsForeffect333Var.add(effect333);
                addEffects((Parameter<?>) effect333Var, effectsForeffect333Var);
            }

            public void init_17_0_2_2_edc0357_1357846886896_830832_20105Dependencies() {
                addDependency(_17_0_2_2_edc0357_1357846887399_399074_21276, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846886899_503989_20114, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_2_edc0357_1357846887399_892752_21277, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846886899_924277_20112, ClassUtils.getMethodForArgTypes("ObjectFlow<Power>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(finalNode_startTime, new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(true));
            }

            public void init_17_0_2_2_edc0357_1357846886896_830832_20105Elaborations() {
                init_17_0_2_2_edc0357_1357846886896_830832_20105Dependencies();
            }
        }

        public class _17_0_2_2_edc0357_1357846886897_144575_20106 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846886897_144575_20106() {
                super();
                init_17_0_2_2_edc0357_1357846886897_144575_20106Members();
                init_17_0_2_2_edc0357_1357846886897_144575_20106Collections();
                init_17_0_2_2_edc0357_1357846886897_144575_20106Elaborations();
            }

            public _17_0_2_2_edc0357_1357846886897_144575_20106(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846886897_144575_20106Members();
                init_17_0_2_2_edc0357_1357846886897_144575_20106Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846886897_144575_20106Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_2_edc0357_1357846886896_31635_20104_exists = null;

            public BooleanParameter objectToPass = null;

            public Dependency _17_0_2_2_edc0357_1357846886896_31635_20104_existsDependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Effect effect334 = null;

            public Parameter effect334Var = null;

            public ElaborationRule elaborationRule335 = null;

            public void init_17_0_2_2_edc0357_1357846886897_144575_20106Members() {
                try {
                    if (_17_0_2_2_edc0357_1357846886896_31635_20104_exists == null) _17_0_2_2_edc0357_1357846886896_31635_20104_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846886896_31635_20104_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect334VarV = sig_17_0_2_2_edc0357_1357846886899_209424_20115;
                    effect334Var = new Parameter("effect334Var", null, null, this);
                    addDependency(effect334Var, new Expression(effect334VarV));
                    effect334 = new EffectFunction(new EffectFunction(effect334Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846886897_144575_20106Collections() {
                parameters.add(_17_0_2_2_edc0357_1357846886896_31635_20104_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect334Var = new TreeSet<Effect>();
                effectsForeffect334Var.add(effect334);
                addEffects((Parameter<?>) effect334Var, effectsForeffect334Var);
            }

            public void init_17_0_2_2_edc0357_1357846886897_144575_20106Dependencies() {
                addDependency(_17_0_2_2_edc0357_1357846886896_31635_20104_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886897_862037_20107_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886897_862037_20107_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(true));
            }

            public void init_17_0_2_2_edc0357_1357846886897_144575_20106Elaborations() {
                init_17_0_2_2_edc0357_1357846886897_144575_20106Dependencies();
                Expression<?>[] arguments335 = new Expression<?>[1];
                arguments335[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition335 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846886896_31635_20104_exists);
                elaborationRule335 = addElaborationRule(condition335, _17_0_2_2_edc0357_1357846886579_143744_19864.this, Power._17_0_2_2_edc0357_1357846886579_143744_19864._17_0_2_2_edc0357_1357846886896_31635_20104.class, "Pload_readself_ReadSelfAction_changeLoadValue", arguments335);
            }
        }

        public class _17_0_2_2_edc0357_1357846886897_862037_20107 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846886897_862037_20107() {
                super();
                init_17_0_2_2_edc0357_1357846886897_862037_20107Members();
                init_17_0_2_2_edc0357_1357846886897_862037_20107Collections();
                init_17_0_2_2_edc0357_1357846886897_862037_20107Elaborations();
            }

            public _17_0_2_2_edc0357_1357846886897_862037_20107(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846886897_862037_20107Members();
                init_17_0_2_2_edc0357_1357846886897_862037_20107Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846886897_862037_20107Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter objectToPass = null;

            public Dependency endTimeDependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Dependency finalNode_endTimeDependency = null;

            public void init_17_0_2_2_edc0357_1357846886897_862037_20107Members() {
                try {
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846886897_862037_20107Collections() {
                parameters.add(objectToPass);
            }

            public void init_17_0_2_2_edc0357_1357846886897_862037_20107Dependencies() {
                addDependency(endTime, new Expression<Integer>(new Functions.Plus(new Expression<Integer>(startTime), new Expression<Integer>(duration))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846886899_325044_20113, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(finalNode_endTime, new Expression<Integer>(endTime));
            }

            public void init_17_0_2_2_edc0357_1357846886897_862037_20107Elaborations() {
                init_17_0_2_2_edc0357_1357846886897_862037_20107Dependencies();
            }
        }

        public class _17_0_2_2_edc0357_1357846886898_182439_20108 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846886898_182439_20108() {
                super();
                init_17_0_2_2_edc0357_1357846886898_182439_20108Members();
                init_17_0_2_2_edc0357_1357846886898_182439_20108Collections();
                init_17_0_2_2_edc0357_1357846886898_182439_20108Elaborations();
            }

            public _17_0_2_2_edc0357_1357846886898_182439_20108(Expression<Integer> startTime, Expression<Integer> _17_0_2_2_edc0357_1357846886882_400011_20082) {
                super();
                init_17_0_2_2_edc0357_1357846886898_182439_20108Members();
                init_17_0_2_2_edc0357_1357846886898_182439_20108Collections();
                addDependency(this.startTime, startTime);
                addDependency(this._17_0_2_2_edc0357_1357846886882_400011_20082, _17_0_2_2_edc0357_1357846886882_400011_20082);
                init_17_0_2_2_edc0357_1357846886898_182439_20108Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter addToDecider__17_0_2_2_edc0357_1357846886896_830832_20105 = null;

            public IntegerParameter _17_0_2_2_edc0357_1357846886882_400011_20082 = null;

            public IntegerParameter myDeciderID_decider_17_0_2_2_edc0357_1357846886896_830832_20105 = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846886896_830832_20105_exists = null;

            public IntegerParameter objectToPass = null;

            public Dependency addToDecider__17_0_2_2_edc0357_1357846886896_830832_20105Dependency = null;

            public Dependency myDeciderID_decider_17_0_2_2_edc0357_1357846886896_830832_20105Dependency = null;

            public Dependency objectToPassDependency = null;

            public Dependency durationDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846886896_830832_20105_existsDependency = null;

            public Effect effect336 = null;

            public Parameter effect336Var = null;

            public Effect effect337 = null;

            public Parameter effect337Var = null;

            public ElaborationRule elaborationRule338 = null;

            public void init_17_0_2_2_edc0357_1357846886898_182439_20108Members() {
                try {
                    if (addToDecider__17_0_2_2_edc0357_1357846886896_830832_20105 == null) addToDecider__17_0_2_2_edc0357_1357846886896_830832_20105 = new BooleanParameter("addToDecider__17_0_2_2_edc0357_1357846886896_830832_20105", (Boolean) false, this);
                    if (_17_0_2_2_edc0357_1357846886882_400011_20082 == null) _17_0_2_2_edc0357_1357846886882_400011_20082 = new IntegerParameter("_17_0_2_2_edc0357_1357846886882_400011_20082", (Integer) null, this);
                    if (myDeciderID_decider_17_0_2_2_edc0357_1357846886896_830832_20105 == null) myDeciderID_decider_17_0_2_2_edc0357_1357846886896_830832_20105 = new IntegerParameter("myDeciderID_decider_17_0_2_2_edc0357_1357846886896_830832_20105", (Integer) 2, this);
                    if (_17_0_2_2_edc0357_1357846886896_830832_20105_exists == null) _17_0_2_2_edc0357_1357846886896_830832_20105_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846886896_830832_20105_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new IntegerParameter("objectToPass", (Integer) null, this);
                    Object effect336VarV = sig_17_0_2_2_edc0357_1357846886899_503989_20114;
                    effect336Var = new Parameter("effect336Var", null, null, this);
                    addDependency(effect336Var, new Expression(effect336VarV));
                    effect336 = new EffectFunction(new EffectFunction(effect336Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect337VarV = decider_17_0_2_2_edc0357_1357846886896_830832_20105;
                    effect337Var = new Parameter("effect337Var", null, null, this);
                    addDependency(effect337Var, new Expression(effect337VarV));
                    effect337 = new EffectFunction(new EffectFunction(effect337Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class, java.lang.Boolean.class), new Object[] { endTime, myDeciderID_decider_17_0_2_2_edc0357_1357846886896_830832_20105, addToDecider__17_0_2_2_edc0357_1357846886896_830832_20105 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846886898_182439_20108Collections() {
                parameters.add(addToDecider__17_0_2_2_edc0357_1357846886896_830832_20105);
                parameters.add(_17_0_2_2_edc0357_1357846886882_400011_20082);
                parameters.add(myDeciderID_decider_17_0_2_2_edc0357_1357846886896_830832_20105);
                parameters.add(_17_0_2_2_edc0357_1357846886896_830832_20105_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect336Var = new TreeSet<Effect>();
                effectsForeffect336Var.add(effect336);
                addEffects((Parameter<?>) effect336Var, effectsForeffect336Var);
                Set<Effect> effectsForeffect337Var = new TreeSet<Effect>();
                effectsForeffect337Var.add(effect337);
                addEffects((Parameter<?>) effect337Var, effectsForeffect337Var);
            }

            public void init_17_0_2_2_edc0357_1357846886898_182439_20108Dependencies() {
                addDependency(addToDecider__17_0_2_2_edc0357_1357846886896_830832_20105, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886897_862037_20107_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886897_862037_20107_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(myDeciderID_decider_17_0_2_2_edc0357_1357846886896_830832_20105, new Expression<Integer>(2));
                addDependency(objectToPass, new Expression<Integer>(_17_0_2_2_edc0357_1357846886882_400011_20082));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(_17_0_2_2_edc0357_1357846886896_830832_20105_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886897_862037_20107_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886897_862037_20107_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846886896_830832_20105, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846886896_830832_20105, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846886896_830832_20105, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_2_edc0357_1357846886896_830832_20105)))))))));
            }

            public void init_17_0_2_2_edc0357_1357846886898_182439_20108Elaborations() {
                init_17_0_2_2_edc0357_1357846886898_182439_20108Dependencies();
                Expression<?>[] arguments338 = new Expression<?>[1];
                arguments338[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition338 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846886896_830832_20105_exists);
                elaborationRule338 = addElaborationRule(condition338, _17_0_2_2_edc0357_1357846886579_143744_19864.this, Power._17_0_2_2_edc0357_1357846886579_143744_19864._17_0_2_2_edc0357_1357846886896_830832_20105.class, "Pload_update_load_AddStructuralFeatureValueAction_changeLoadValue", arguments338);
            }
        }

        public _17_0_2_2_edc0357_1357846886579_143744_19864(Expression<Integer> startTime, Expression<_17_0_2_2_edc0357_1357846886579_143744_19864_interface> caller, Expression<Integer> _17_0_2_2_edc0357_1357846886882_400011_20082) {
            super();
            init_17_0_2_2_edc0357_1357846886579_143744_19864Members();
            init_17_0_2_2_edc0357_1357846886579_143744_19864Collections();
            addDependency(this.startTime, startTime);
            addDependency(this.caller, caller);
            addDependency(this._17_0_2_2_edc0357_1357846886882_400011_20082, _17_0_2_2_edc0357_1357846886882_400011_20082);
            init_17_0_2_2_edc0357_1357846886579_143744_19864Elaborations();
            fixTimeDependencies();
        }

        public IntegerParameter _17_0_2_2_edc0357_1357846886882_400011_20082 = null;

        public BooleanParameter _17_0_2_2_edc0357_1357846886897_862037_20107_exists = null;

        public Parameter< Power._17_0_2_2_edc0357_1357846886579_143744_19864_interface > caller = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_2_2_edc0357_1357846886899_503989_20114 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_2_edc0357_1357846886899_209424_20115 = null;

        public Parameter< ObjectFlow<Power> > sig_17_0_2_2_edc0357_1357846886899_924277_20112 = null;

        public IntegerParameter finalNode_startTime = null;

        public Parameter< TimeVaryingList<Integer> > decider_17_0_2_2_edc0357_1357846886896_830832_20105 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_2_edc0357_1357846886899_325044_20113 = null;

        public IntegerParameter finalNode_endTime = null;

        public Dependency caller_endTimeDependency = null;

        public Dependency _17_0_2_2_edc0357_1357846886897_862037_20107_existsDependency = null;

        public Dependency endTimeDependency = null;

        public ElaborationRule elaborationRule326 = null;

        public ElaborationRule elaborationRule327 = null;

        public ElaborationRule elaborationRule328 = null;

        public void init_17_0_2_2_edc0357_1357846886579_143744_19864Members() {
            try {
                if (_17_0_2_2_edc0357_1357846886882_400011_20082 == null) _17_0_2_2_edc0357_1357846886882_400011_20082 = new IntegerParameter("_17_0_2_2_edc0357_1357846886882_400011_20082", (Integer) null, this);
                if (_17_0_2_2_edc0357_1357846886897_862037_20107_exists == null) _17_0_2_2_edc0357_1357846886897_862037_20107_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846886897_862037_20107_exists", (Boolean) false, this);
                if (caller == null) caller = new Parameter<Power._17_0_2_2_edc0357_1357846886579_143744_19864_interface>("caller", null, (Power._17_0_2_2_edc0357_1357846886579_143744_19864_interface) null, this);
                if (sig_17_0_2_2_edc0357_1357846886899_503989_20114 == null) sig_17_0_2_2_edc0357_1357846886899_503989_20114 = new Parameter<ObjectFlow<Integer>>("sig_17_0_2_2_edc0357_1357846886899_503989_20114", null, (ObjectFlow<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846886899_503989_20114" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846886899_209424_20115 == null) sig_17_0_2_2_edc0357_1357846886899_209424_20115 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_2_edc0357_1357846886899_209424_20115", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846886899_209424_20115" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846886899_924277_20112 == null) sig_17_0_2_2_edc0357_1357846886899_924277_20112 = new Parameter<ObjectFlow<Power>>("sig_17_0_2_2_edc0357_1357846886899_924277_20112", null, (ObjectFlow<Power>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846886899_924277_20112" })).evaluate(true), this);
                if (finalNode_startTime == null) finalNode_startTime = new IntegerParameter("finalNode_startTime", (Integer) null, this);
                if (decider_17_0_2_2_edc0357_1357846886896_830832_20105 == null) decider_17_0_2_2_edc0357_1357846886896_830832_20105 = new Parameter<TimeVaryingList<Integer>>("decider_17_0_2_2_edc0357_1357846886896_830832_20105", null, (TimeVaryingList<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingList.class, java.lang.String.class, int.class), new Object[] { "decider_17_0_2_2_edc0357_1357846886896_830832_20105", 2 })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846886899_325044_20113 == null) sig_17_0_2_2_edc0357_1357846886899_325044_20113 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_2_edc0357_1357846886899_325044_20113", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846886899_325044_20113" })).evaluate(true), this);
                if (finalNode_endTime == null) finalNode_endTime = new IntegerParameter("finalNode_endTime", (Integer) null, this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void init_17_0_2_2_edc0357_1357846886579_143744_19864Collections() {
            parameters.add(_17_0_2_2_edc0357_1357846886882_400011_20082);
            parameters.add(_17_0_2_2_edc0357_1357846886897_862037_20107_exists);
            parameters.add(caller);
            parameters.add(sig_17_0_2_2_edc0357_1357846886899_503989_20114);
            parameters.add(sig_17_0_2_2_edc0357_1357846886899_209424_20115);
            parameters.add(sig_17_0_2_2_edc0357_1357846886899_924277_20112);
            parameters.add(finalNode_startTime);
            parameters.add(decider_17_0_2_2_edc0357_1357846886896_830832_20105);
            parameters.add(sig_17_0_2_2_edc0357_1357846886899_325044_20113);
            parameters.add(finalNode_endTime);
        }

        public void init_17_0_2_2_edc0357_1357846886579_143744_19864Dependencies() {
            caller.getValue(true).addDependency(caller.getValue(true).endTime, new Expression<Integer>(finalNode_endTime));
            addDependency(_17_0_2_2_edc0357_1357846886897_862037_20107_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.NotEquals(new Expression<Integer>(finalNode_startTime), null)), new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846886899_325044_20113, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(new Functions.Minus(new Expression<Integer>(finalNode_startTime), new Expression<Integer>(1))) })))));
            addDependency(endTime, new Expression<Integer>(finalNode_endTime));
        }

        public void init_17_0_2_2_edc0357_1357846886579_143744_19864Elaborations() {
            init_17_0_2_2_edc0357_1357846886579_143744_19864Dependencies();
            Expression<?>[] arguments326 = new Expression<?>[2];
            arguments326[0] = new Expression<Integer>(startTime);
            arguments326[1] = new Expression<Integer>(_17_0_2_2_edc0357_1357846886882_400011_20082);
            Expression<Boolean> condition326 = new Expression<Boolean>(true);
            elaborationRule326 = addElaborationRule(condition326, _17_0_2_2_edc0357_1357846886579_143744_19864.this, Power._17_0_2_2_edc0357_1357846886579_143744_19864._17_0_2_2_edc0357_1357846886898_182439_20108.class, "Pload_newLoadVal_ActivityParameterNode_changeLoadValue", arguments326);
            Expression<?>[] arguments327 = new Expression<?>[1];
            arguments327[0] = new Expression<Integer>(finalNode_startTime);
            Expression<Boolean> condition327 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846886897_862037_20107_exists);
            elaborationRule327 = addElaborationRule(condition327, _17_0_2_2_edc0357_1357846886579_143744_19864.this, Power._17_0_2_2_edc0357_1357846886579_143744_19864._17_0_2_2_edc0357_1357846886897_862037_20107.class, "Pload_end_ActivityFinalNode_changeLoadValue", arguments327);
            Expression<?>[] arguments328 = new Expression<?>[1];
            arguments328[0] = new Expression<Integer>(startTime);
            Expression<Boolean> condition328 = new Expression<Boolean>(true);
            elaborationRule328 = addElaborationRule(condition328, _17_0_2_2_edc0357_1357846886579_143744_19864.this, Power._17_0_2_2_edc0357_1357846886579_143744_19864._17_0_2_2_edc0357_1357846886897_144575_20106.class, "Pload_start_InitialNode_changeLoadValue", arguments328);
        }
    }

    public class _17_0_2_2_edc0357_1357846886579_143744_19864_interface extends DurativeEvent {

        public _17_0_2_2_edc0357_1357846886579_143744_19864_interface() {
            super();
            init_17_0_2_2_edc0357_1357846886579_143744_19864_interfaceMembers();
            init_17_0_2_2_edc0357_1357846886579_143744_19864_interfaceCollections();
            init_17_0_2_2_edc0357_1357846886579_143744_19864_interfaceElaborations();
        }

        public IntegerParameter _17_0_2_2_edc0357_1357846886882_400011_20082 = null;

        public void init_17_0_2_2_edc0357_1357846886579_143744_19864_interfaceMembers() {
            try {
                if (_17_0_2_2_edc0357_1357846886882_400011_20082 == null) _17_0_2_2_edc0357_1357846886882_400011_20082 = new IntegerParameter("_17_0_2_2_edc0357_1357846886882_400011_20082", (Integer) null, this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void init_17_0_2_2_edc0357_1357846886579_143744_19864_interfaceCollections() {
            parameters.add(_17_0_2_2_edc0357_1357846886882_400011_20082);
        }

        public void init_17_0_2_2_edc0357_1357846886579_143744_19864_interfaceDependencies() {
        }

        public void init_17_0_2_2_edc0357_1357846886579_143744_19864_interfaceElaborations() {
            init_17_0_2_2_edc0357_1357846886579_143744_19864_interfaceDependencies();
        }
    }

    public class _17_0_2_2_edc0357_1357846886579_898473_19865 extends DurativeEvent {

        public _17_0_2_2_edc0357_1357846886579_898473_19865() {
            super();
            init_17_0_2_2_edc0357_1357846886579_898473_19865Members();
            init_17_0_2_2_edc0357_1357846886579_898473_19865Collections();
            init_17_0_2_2_edc0357_1357846886579_898473_19865Elaborations();
        }

        public class _17_0_2_2_edc0357_1357846886912_805616_20137 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846886912_805616_20137() {
                super();
                init_17_0_2_2_edc0357_1357846886912_805616_20137Members();
                init_17_0_2_2_edc0357_1357846886912_805616_20137Collections();
                init_17_0_2_2_edc0357_1357846886912_805616_20137Elaborations();
            }

            public _17_0_2_2_edc0357_1357846886912_805616_20137(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846886912_805616_20137Members();
                init_17_0_2_2_edc0357_1357846886912_805616_20137Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846886912_805616_20137Elaborations();
                fixTimeDependencies();
            }

            public Parameter< Power > _17_0_2_2_edc0357_1357846887404_260746_21283 = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846886912_136391_20138_exists = null;

            public BooleanParameter objectToPass = null;

            public Dependency _17_0_2_2_edc0357_1357846886912_136391_20138_existsDependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Effect effect341 = null;

            public Parameter effect341Var = null;

            public ElaborationRule elaborationRule342 = null;

            public void init_17_0_2_2_edc0357_1357846886912_805616_20137Members() {
                try {
                    if (_17_0_2_2_edc0357_1357846887404_260746_21283 == null) _17_0_2_2_edc0357_1357846887404_260746_21283 = new Parameter<Power>("_17_0_2_2_edc0357_1357846887404_260746_21283", null, (Power) Power.this, this);
                    if (_17_0_2_2_edc0357_1357846886912_136391_20138_exists == null) _17_0_2_2_edc0357_1357846886912_136391_20138_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846886912_136391_20138_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect341VarV = sig_17_0_2_2_edc0357_1357846886917_177170_20147;
                    effect341Var = new Parameter("effect341Var", null, null, this);
                    addDependency(effect341Var, new Expression(effect341VarV));
                    effect341 = new EffectFunction(new EffectFunction(effect341Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Power>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { _17_0_2_2_edc0357_1357846887404_260746_21283, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846886912_805616_20137Collections() {
                parameters.add(_17_0_2_2_edc0357_1357846887404_260746_21283);
                parameters.add(_17_0_2_2_edc0357_1357846886912_136391_20138_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect341Var = new TreeSet<Effect>();
                effectsForeffect341Var.add(effect341);
                addEffects((Parameter<?>) effect341Var, effectsForeffect341Var);
            }

            public void init_17_0_2_2_edc0357_1357846886912_805616_20137Dependencies() {
                addDependency(_17_0_2_2_edc0357_1357846886912_136391_20138_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886917_42845_20146_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886917_42845_20146_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846886918_279384_20158, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_2_edc0357_1357846886912_805616_20137Elaborations() {
                init_17_0_2_2_edc0357_1357846886912_805616_20137Dependencies();
                Expression<?>[] arguments342 = new Expression<?>[1];
                arguments342[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition342 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846886912_136391_20138_exists);
                elaborationRule342 = addElaborationRule(condition342, _17_0_2_2_edc0357_1357846886579_898473_19865.this, Power._17_0_2_2_edc0357_1357846886579_898473_19865._17_0_2_2_edc0357_1357846886912_136391_20138.class, "self_fork_ForkNode_intializeP", arguments342);
            }
        }

        public class _17_0_2_2_edc0357_1357846886912_136391_20138 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846886912_136391_20138() {
                super();
                init_17_0_2_2_edc0357_1357846886912_136391_20138Members();
                init_17_0_2_2_edc0357_1357846886912_136391_20138Collections();
                init_17_0_2_2_edc0357_1357846886912_136391_20138Elaborations();
            }

            public _17_0_2_2_edc0357_1357846886912_136391_20138(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846886912_136391_20138Members();
                init_17_0_2_2_edc0357_1357846886912_136391_20138Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846886912_136391_20138Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter addToDecider__17_0_2_2_edc0357_1357846886913_954037_20140 = null;

            public BooleanParameter addToDecider__17_0_2_2_edc0357_1357846886913_227952_20139 = null;

            public IntegerParameter myDeciderID_decider_17_0_2_2_edc0357_1357846886913_227952_20139 = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846886913_227952_20139_exists = null;

            public IntegerParameter myDeciderID_decider_17_0_2_2_edc0357_1357846886913_954037_20140 = null;

            public Parameter< Power > objectToPass = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846886913_954037_20140_exists = null;

            public Dependency addToDecider__17_0_2_2_edc0357_1357846886913_954037_20140Dependency = null;

            public Dependency addToDecider__17_0_2_2_edc0357_1357846886913_227952_20139Dependency = null;

            public Dependency myDeciderID_decider_17_0_2_2_edc0357_1357846886913_227952_20139Dependency = null;

            public Dependency _17_0_2_2_edc0357_1357846886913_227952_20139_existsDependency = null;

            public Dependency myDeciderID_decider_17_0_2_2_edc0357_1357846886913_954037_20140Dependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846886913_954037_20140_existsDependency = null;

            public Effect effect343 = null;

            public Parameter effect343Var = null;

            public Effect effect344 = null;

            public Parameter effect344Var = null;

            public Effect effect345 = null;

            public Parameter effect345Var = null;

            public Effect effect346 = null;

            public Parameter effect346Var = null;

            public ElaborationRule elaborationRule347 = null;

            public ElaborationRule elaborationRule348 = null;

            public void init_17_0_2_2_edc0357_1357846886912_136391_20138Members() {
                try {
                    if (addToDecider__17_0_2_2_edc0357_1357846886913_954037_20140 == null) addToDecider__17_0_2_2_edc0357_1357846886913_954037_20140 = new BooleanParameter("addToDecider__17_0_2_2_edc0357_1357846886913_954037_20140", (Boolean) false, this);
                    if (addToDecider__17_0_2_2_edc0357_1357846886913_227952_20139 == null) addToDecider__17_0_2_2_edc0357_1357846886913_227952_20139 = new BooleanParameter("addToDecider__17_0_2_2_edc0357_1357846886913_227952_20139", (Boolean) false, this);
                    if (myDeciderID_decider_17_0_2_2_edc0357_1357846886913_227952_20139 == null) myDeciderID_decider_17_0_2_2_edc0357_1357846886913_227952_20139 = new IntegerParameter("myDeciderID_decider_17_0_2_2_edc0357_1357846886913_227952_20139", (Integer) 1, this);
                    if (_17_0_2_2_edc0357_1357846886913_227952_20139_exists == null) _17_0_2_2_edc0357_1357846886913_227952_20139_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846886913_227952_20139_exists", (Boolean) false, this);
                    if (myDeciderID_decider_17_0_2_2_edc0357_1357846886913_954037_20140 == null) myDeciderID_decider_17_0_2_2_edc0357_1357846886913_954037_20140 = new IntegerParameter("myDeciderID_decider_17_0_2_2_edc0357_1357846886913_954037_20140", (Integer) 1, this);
                    if (objectToPass == null) objectToPass = new Parameter<Power>("objectToPass", null, (Power) null, this);
                    if (_17_0_2_2_edc0357_1357846886913_954037_20140_exists == null) _17_0_2_2_edc0357_1357846886913_954037_20140_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846886913_954037_20140_exists", (Boolean) false, this);
                    Object effect343VarV = sig_17_0_2_2_edc0357_1357846886917_872097_20150;
                    effect343Var = new Parameter("effect343Var", null, null, this);
                    addDependency(effect343Var, new Expression(effect343VarV));
                    effect343 = new EffectFunction(new EffectFunction(effect343Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Power>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect344VarV = sig_17_0_2_2_edc0357_1357846886918_883621_20151;
                    effect344Var = new Parameter("effect344Var", null, null, this);
                    addDependency(effect344Var, new Expression(effect344VarV));
                    effect344 = new EffectFunction(new EffectFunction(effect344Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Power>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect345VarV = decider_17_0_2_2_edc0357_1357846886913_227952_20139;
                    effect345Var = new Parameter("effect345Var", null, null, this);
                    addDependency(effect345Var, new Expression(effect345VarV));
                    effect345 = new EffectFunction(new EffectFunction(effect345Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class, java.lang.Boolean.class), new Object[] { endTime, myDeciderID_decider_17_0_2_2_edc0357_1357846886913_227952_20139, addToDecider__17_0_2_2_edc0357_1357846886913_227952_20139 }));
                    Object effect346VarV = decider_17_0_2_2_edc0357_1357846886913_954037_20140;
                    effect346Var = new Parameter("effect346Var", null, null, this);
                    addDependency(effect346Var, new Expression(effect346VarV));
                    effect346 = new EffectFunction(new EffectFunction(effect346Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class, java.lang.Boolean.class), new Object[] { endTime, myDeciderID_decider_17_0_2_2_edc0357_1357846886913_954037_20140, addToDecider__17_0_2_2_edc0357_1357846886913_954037_20140 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846886912_136391_20138Collections() {
                parameters.add(addToDecider__17_0_2_2_edc0357_1357846886913_954037_20140);
                parameters.add(addToDecider__17_0_2_2_edc0357_1357846886913_227952_20139);
                parameters.add(myDeciderID_decider_17_0_2_2_edc0357_1357846886913_227952_20139);
                parameters.add(_17_0_2_2_edc0357_1357846886913_227952_20139_exists);
                parameters.add(myDeciderID_decider_17_0_2_2_edc0357_1357846886913_954037_20140);
                parameters.add(objectToPass);
                parameters.add(_17_0_2_2_edc0357_1357846886913_954037_20140_exists);
                Set<Effect> effectsForeffect343Var = new TreeSet<Effect>();
                effectsForeffect343Var.add(effect343);
                addEffects((Parameter<?>) effect343Var, effectsForeffect343Var);
                Set<Effect> effectsForeffect344Var = new TreeSet<Effect>();
                effectsForeffect344Var.add(effect344);
                addEffects((Parameter<?>) effect344Var, effectsForeffect344Var);
                Set<Effect> effectsForeffect345Var = new TreeSet<Effect>();
                effectsForeffect345Var.add(effect345);
                addEffects((Parameter<?>) effect345Var, effectsForeffect345Var);
                Set<Effect> effectsForeffect346Var = new TreeSet<Effect>();
                effectsForeffect346Var.add(effect346);
                addEffects((Parameter<?>) effect346Var, effectsForeffect346Var);
            }

            public void init_17_0_2_2_edc0357_1357846886912_136391_20138Dependencies() {
                addDependency(addToDecider__17_0_2_2_edc0357_1357846886913_954037_20140, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886917_42845_20146_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886917_42845_20146_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(addToDecider__17_0_2_2_edc0357_1357846886913_227952_20139, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886917_42845_20146_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886917_42845_20146_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(myDeciderID_decider_17_0_2_2_edc0357_1357846886913_227952_20139, new Expression<Integer>(1));
                addDependency(_17_0_2_2_edc0357_1357846886913_227952_20139_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886917_42845_20146_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886917_42845_20146_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846886913_227952_20139, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846886913_227952_20139, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846886913_227952_20139, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_2_edc0357_1357846886913_227952_20139)))))))));
                addDependency(myDeciderID_decider_17_0_2_2_edc0357_1357846886913_954037_20140, new Expression<Integer>(1));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846886917_177170_20147, ClassUtils.getMethodForArgTypes("ObjectFlow<Power>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_2_edc0357_1357846886913_954037_20140_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886917_42845_20146_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886917_42845_20146_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846886913_954037_20140, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846886913_954037_20140, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846886913_954037_20140, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_2_edc0357_1357846886913_954037_20140)))))))));
            }

            public void init_17_0_2_2_edc0357_1357846886912_136391_20138Elaborations() {
                init_17_0_2_2_edc0357_1357846886912_136391_20138Dependencies();
                Expression<?>[] arguments347 = new Expression<?>[1];
                arguments347[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition347 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846886913_227952_20139_exists);
                elaborationRule347 = addElaborationRule(condition347, _17_0_2_2_edc0357_1357846886579_898473_19865.this, Power._17_0_2_2_edc0357_1357846886579_898473_19865._17_0_2_2_edc0357_1357846886913_227952_20139.class, "Pinit_set_load_AddStructuralFeatureValueAction_intializeP", arguments347);
                Expression<?>[] arguments348 = new Expression<?>[1];
                arguments348[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition348 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846886913_954037_20140_exists);
                elaborationRule348 = addElaborationRule(condition348, _17_0_2_2_edc0357_1357846886579_898473_19865.this, Power._17_0_2_2_edc0357_1357846886579_898473_19865._17_0_2_2_edc0357_1357846886913_954037_20140.class, "Pinit_set_aug_AddStructuralFeatureValueAction_intializeP", arguments348);
            }
        }

        public class _17_0_2_2_edc0357_1357846886913_227952_20139 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846886913_227952_20139() {
                super();
                init_17_0_2_2_edc0357_1357846886913_227952_20139Members();
                init_17_0_2_2_edc0357_1357846886913_227952_20139Collections();
                init_17_0_2_2_edc0357_1357846886913_227952_20139Elaborations();
            }

            public _17_0_2_2_edc0357_1357846886913_227952_20139(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846886913_227952_20139Members();
                init_17_0_2_2_edc0357_1357846886913_227952_20139Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846886913_227952_20139Elaborations();
                fixTimeDependencies();
            }

            public IntegerParameter _17_0_2_2_edc0357_1357846887404_867529_21284 = null;

            public BooleanParameter addToDecider__17_0_2_2_edc0357_1357846886916_945196_20145 = null;

            public Parameter< Power > _17_0_2_2_edc0357_1357846887405_537159_21285 = null;

            public IntegerParameter myDeciderID_decider_17_0_2_2_edc0357_1357846886916_945196_20145 = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846886916_945196_20145_exists = null;

            public BooleanParameter objectToPass = null;

            public Dependency _17_0_2_2_edc0357_1357846887404_867529_21284Dependency = null;

            public Dependency addToDecider__17_0_2_2_edc0357_1357846886916_945196_20145Dependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887405_537159_21285Dependency = null;

            public Dependency myDeciderID_decider_17_0_2_2_edc0357_1357846886916_945196_20145Dependency = null;

            public Dependency _17_0_2_2_edc0357_1357846886916_945196_20145_existsDependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Effect effect349 = null;

            public Parameter effect349Var = null;

            public Effect effect350 = null;

            public Parameter effect350Var = null;

            public Effect effect351 = null;

            public Parameter effect351Var = null;

            public ElaborationRule elaborationRule352 = null;

            public void init_17_0_2_2_edc0357_1357846886913_227952_20139Members() {
                try {
                    if (_17_0_2_2_edc0357_1357846887404_867529_21284 == null) _17_0_2_2_edc0357_1357846887404_867529_21284 = new IntegerParameter("_17_0_2_2_edc0357_1357846887404_867529_21284", (Integer) null, this);
                    if (addToDecider__17_0_2_2_edc0357_1357846886916_945196_20145 == null) addToDecider__17_0_2_2_edc0357_1357846886916_945196_20145 = new BooleanParameter("addToDecider__17_0_2_2_edc0357_1357846886916_945196_20145", (Boolean) false, this);
                    if (_17_0_2_2_edc0357_1357846887405_537159_21285 == null) _17_0_2_2_edc0357_1357846887405_537159_21285 = new Parameter<Power>("_17_0_2_2_edc0357_1357846887405_537159_21285", null, (Power) null, this);
                    if (myDeciderID_decider_17_0_2_2_edc0357_1357846886916_945196_20145 == null) myDeciderID_decider_17_0_2_2_edc0357_1357846886916_945196_20145 = new IntegerParameter("myDeciderID_decider_17_0_2_2_edc0357_1357846886916_945196_20145", (Integer) 1, this);
                    if (_17_0_2_2_edc0357_1357846886916_945196_20145_exists == null) _17_0_2_2_edc0357_1357846886916_945196_20145_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846886916_945196_20145_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect349VarV = sig_17_0_2_2_edc0357_1357846886918_977728_20153;
                    effect349Var = new Parameter("effect349Var", null, null, this);
                    addDependency(effect349Var, new Expression(effect349VarV));
                    effect349 = new EffectFunction(new EffectFunction(effect349Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect350VarV = decider_17_0_2_2_edc0357_1357846886916_945196_20145;
                    effect350Var = new Parameter("effect350Var", null, null, this);
                    addDependency(effect350Var, new Expression(effect350VarV));
                    effect350 = new EffectFunction(new EffectFunction(effect350Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class, java.lang.Boolean.class), new Object[] { endTime, myDeciderID_decider_17_0_2_2_edc0357_1357846886916_945196_20145, addToDecider__17_0_2_2_edc0357_1357846886916_945196_20145 }));
                    Object effect351VarV = load__17_0_2_2_edc0357_1357846886583_892776_19869;
                    effect351Var = new Parameter("effect351Var", null, null, this);
                    addDependency(effect351Var, new Expression(effect351VarV));
                    effect351 = new EffectFunction(new EffectFunction(effect351Var, ClassUtils.getMethodForArgTypes("TimeVaryingPlottableMap<Integer>", "PowerSystem3", "setValue", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { startTime, _17_0_2_2_edc0357_1357846887404_867529_21284 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846886913_227952_20139Collections() {
                parameters.add(_17_0_2_2_edc0357_1357846887404_867529_21284);
                parameters.add(addToDecider__17_0_2_2_edc0357_1357846886916_945196_20145);
                parameters.add(_17_0_2_2_edc0357_1357846887405_537159_21285);
                parameters.add(myDeciderID_decider_17_0_2_2_edc0357_1357846886916_945196_20145);
                parameters.add(_17_0_2_2_edc0357_1357846886916_945196_20145_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect349Var = new TreeSet<Effect>();
                effectsForeffect349Var.add(effect349);
                addEffects((Parameter<?>) effect349Var, effectsForeffect349Var);
                Set<Effect> effectsForeffect350Var = new TreeSet<Effect>();
                effectsForeffect350Var.add(effect350);
                addEffects((Parameter<?>) effect350Var, effectsForeffect350Var);
                Set<Effect> effectsForeffect351Var = new TreeSet<Effect>();
                effectsForeffect351Var.add(effect351);
                addEffects((Parameter<?>) effect351Var, effectsForeffect351Var);
            }

            public void init_17_0_2_2_edc0357_1357846886913_227952_20139Dependencies() {
                addDependency(_17_0_2_2_edc0357_1357846887404_867529_21284, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1358283929447_249637_39158, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(addToDecider__17_0_2_2_edc0357_1357846886916_945196_20145, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886917_42845_20146_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886917_42845_20146_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(_17_0_2_2_edc0357_1357846887405_537159_21285, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846886917_872097_20150, ClassUtils.getMethodForArgTypes("ObjectFlow<Power>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(myDeciderID_decider_17_0_2_2_edc0357_1357846886916_945196_20145, new Expression<Integer>(1));
                addDependency(_17_0_2_2_edc0357_1357846886916_945196_20145_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886917_42845_20146_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886917_42845_20146_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846886916_945196_20145, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846886916_945196_20145, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846886916_945196_20145, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_2_edc0357_1357846886916_945196_20145)))))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(true));
            }

            public void init_17_0_2_2_edc0357_1357846886913_227952_20139Elaborations() {
                init_17_0_2_2_edc0357_1357846886913_227952_20139Dependencies();
                Expression<?>[] arguments352 = new Expression<?>[1];
                arguments352[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition352 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846886916_945196_20145_exists);
                elaborationRule352 = addElaborationRule(condition352, _17_0_2_2_edc0357_1357846886579_898473_19865.this, Power._17_0_2_2_edc0357_1357846886579_898473_19865._17_0_2_2_edc0357_1357846886916_945196_20145.class, "Pinit_join_JoinNode_intializeP", arguments352);
            }
        }

        public class _17_0_2_2_edc0357_1357846886913_954037_20140 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846886913_954037_20140() {
                super();
                init_17_0_2_2_edc0357_1357846886913_954037_20140Members();
                init_17_0_2_2_edc0357_1357846886913_954037_20140Collections();
                init_17_0_2_2_edc0357_1357846886913_954037_20140Elaborations();
            }

            public _17_0_2_2_edc0357_1357846886913_954037_20140(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846886913_954037_20140Members();
                init_17_0_2_2_edc0357_1357846886913_954037_20140Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846886913_954037_20140Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter addToDecider__17_0_2_2_edc0357_1357846886916_945196_20145 = null;

            public IntegerParameter _17_0_2_2_edc0357_1357846887406_470373_21286 = null;

            public IntegerParameter myDeciderID_decider_17_0_2_2_edc0357_1357846886916_945196_20145 = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846886916_945196_20145_exists = null;

            public BooleanParameter objectToPass = null;

            public Parameter< Power > _17_0_2_2_edc0357_1357846887406_728878_21287 = null;

            public Dependency addToDecider__17_0_2_2_edc0357_1357846886916_945196_20145Dependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887406_470373_21286Dependency = null;

            public Dependency myDeciderID_decider_17_0_2_2_edc0357_1357846886916_945196_20145Dependency = null;

            public Dependency _17_0_2_2_edc0357_1357846886916_945196_20145_existsDependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887406_728878_21287Dependency = null;

            public Effect effect353 = null;

            public Parameter effect353Var = null;

            public Effect effect354 = null;

            public Parameter effect354Var = null;

            public Effect effect355 = null;

            public Parameter effect355Var = null;

            public ElaborationRule elaborationRule356 = null;

            public void init_17_0_2_2_edc0357_1357846886913_954037_20140Members() {
                try {
                    if (addToDecider__17_0_2_2_edc0357_1357846886916_945196_20145 == null) addToDecider__17_0_2_2_edc0357_1357846886916_945196_20145 = new BooleanParameter("addToDecider__17_0_2_2_edc0357_1357846886916_945196_20145", (Boolean) false, this);
                    if (_17_0_2_2_edc0357_1357846887406_470373_21286 == null) _17_0_2_2_edc0357_1357846887406_470373_21286 = new IntegerParameter("_17_0_2_2_edc0357_1357846887406_470373_21286", (Integer) null, this);
                    if (myDeciderID_decider_17_0_2_2_edc0357_1357846886916_945196_20145 == null) myDeciderID_decider_17_0_2_2_edc0357_1357846886916_945196_20145 = new IntegerParameter("myDeciderID_decider_17_0_2_2_edc0357_1357846886916_945196_20145", (Integer) 2, this);
                    if (_17_0_2_2_edc0357_1357846886916_945196_20145_exists == null) _17_0_2_2_edc0357_1357846886916_945196_20145_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846886916_945196_20145_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (_17_0_2_2_edc0357_1357846887406_728878_21287 == null) _17_0_2_2_edc0357_1357846887406_728878_21287 = new Parameter<Power>("_17_0_2_2_edc0357_1357846887406_728878_21287", null, (Power) null, this);
                    Object effect353VarV = sig_17_0_2_2_edc0357_1357846886918_832292_20154;
                    effect353Var = new Parameter("effect353Var", null, null, this);
                    addDependency(effect353Var, new Expression(effect353VarV));
                    effect353 = new EffectFunction(new EffectFunction(effect353Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect354VarV = decider_17_0_2_2_edc0357_1357846886916_945196_20145;
                    effect354Var = new Parameter("effect354Var", null, null, this);
                    addDependency(effect354Var, new Expression(effect354VarV));
                    effect354 = new EffectFunction(new EffectFunction(effect354Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class, java.lang.Boolean.class), new Object[] { endTime, myDeciderID_decider_17_0_2_2_edc0357_1357846886916_945196_20145, addToDecider__17_0_2_2_edc0357_1357846886916_945196_20145 }));
                    Object effect355VarV = powerAugmentation__17_0_2_2_edc0357_1358276774925_778819_38488;
                    effect355Var = new Parameter("effect355Var", null, null, this);
                    addDependency(effect355Var, new Expression(effect355VarV));
                    effect355 = new EffectFunction(new EffectFunction(effect355Var, ClassUtils.getMethodForArgTypes("TimeVaryingPlottableMap<Integer>", "PowerSystem3", "setValue", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { startTime, _17_0_2_2_edc0357_1357846887406_470373_21286 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846886913_954037_20140Collections() {
                parameters.add(addToDecider__17_0_2_2_edc0357_1357846886916_945196_20145);
                parameters.add(_17_0_2_2_edc0357_1357846887406_470373_21286);
                parameters.add(myDeciderID_decider_17_0_2_2_edc0357_1357846886916_945196_20145);
                parameters.add(_17_0_2_2_edc0357_1357846886916_945196_20145_exists);
                parameters.add(objectToPass);
                parameters.add(_17_0_2_2_edc0357_1357846887406_728878_21287);
                Set<Effect> effectsForeffect353Var = new TreeSet<Effect>();
                effectsForeffect353Var.add(effect353);
                addEffects((Parameter<?>) effect353Var, effectsForeffect353Var);
                Set<Effect> effectsForeffect354Var = new TreeSet<Effect>();
                effectsForeffect354Var.add(effect354);
                addEffects((Parameter<?>) effect354Var, effectsForeffect354Var);
                Set<Effect> effectsForeffect355Var = new TreeSet<Effect>();
                effectsForeffect355Var.add(effect355);
                addEffects((Parameter<?>) effect355Var, effectsForeffect355Var);
            }

            public void init_17_0_2_2_edc0357_1357846886913_954037_20140Dependencies() {
                addDependency(addToDecider__17_0_2_2_edc0357_1357846886916_945196_20145, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886917_42845_20146_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886917_42845_20146_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(_17_0_2_2_edc0357_1357846887406_470373_21286, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846886917_640040_20148, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(myDeciderID_decider_17_0_2_2_edc0357_1357846886916_945196_20145, new Expression<Integer>(2));
                addDependency(_17_0_2_2_edc0357_1357846886916_945196_20145_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886917_42845_20146_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886917_42845_20146_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846886916_945196_20145, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846886916_945196_20145, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846886916_945196_20145, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_2_edc0357_1357846886916_945196_20145)))))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(true));
                addDependency(_17_0_2_2_edc0357_1357846887406_728878_21287, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846886918_883621_20151, ClassUtils.getMethodForArgTypes("ObjectFlow<Power>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_2_edc0357_1357846886913_954037_20140Elaborations() {
                init_17_0_2_2_edc0357_1357846886913_954037_20140Dependencies();
                Expression<?>[] arguments356 = new Expression<?>[1];
                arguments356[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition356 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846886916_945196_20145_exists);
                elaborationRule356 = addElaborationRule(condition356, _17_0_2_2_edc0357_1357846886579_898473_19865.this, Power._17_0_2_2_edc0357_1357846886579_898473_19865._17_0_2_2_edc0357_1357846886916_945196_20145.class, "Pinit_join_JoinNode_intializeP", arguments356);
            }
        }

        public class _17_0_2_2_edc0357_1357846886914_99415_20141 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846886914_99415_20141() {
                super();
                init_17_0_2_2_edc0357_1357846886914_99415_20141Members();
                init_17_0_2_2_edc0357_1357846886914_99415_20141Collections();
                init_17_0_2_2_edc0357_1357846886914_99415_20141Elaborations();
            }

            public _17_0_2_2_edc0357_1357846886914_99415_20141(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846886914_99415_20141Members();
                init_17_0_2_2_edc0357_1357846886914_99415_20141Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846886914_99415_20141Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter objectToPass = null;

            public IntegerParameter _17_0_2_2_edc0357_1357846887407_551777_21289 = null;

            public BooleanParameter _17_0_2_2_edc0357_1358283925264_845182_39149_exists = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887407_551777_21289Dependency = null;

            public Dependency _17_0_2_2_edc0357_1358283925264_845182_39149_existsDependency = null;

            public Effect effect357 = null;

            public Parameter effect357Var = null;

            public ElaborationRule elaborationRule358 = null;

            public void init_17_0_2_2_edc0357_1357846886914_99415_20141Members() {
                try {
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (_17_0_2_2_edc0357_1357846887407_551777_21289 == null) _17_0_2_2_edc0357_1357846887407_551777_21289 = new IntegerParameter("_17_0_2_2_edc0357_1357846887407_551777_21289", (Integer) 0, this);
                    if (_17_0_2_2_edc0357_1358283925264_845182_39149_exists == null) _17_0_2_2_edc0357_1358283925264_845182_39149_exists = new BooleanParameter("_17_0_2_2_edc0357_1358283925264_845182_39149_exists", (Boolean) false, this);
                    Object effect357VarV = sig_17_0_2_2_edc0357_1358283926570_639411_39153;
                    effect357Var = new Parameter("effect357Var", null, null, this);
                    addDependency(effect357Var, new Expression(effect357VarV));
                    effect357 = new EffectFunction(new EffectFunction(effect357Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { _17_0_2_2_edc0357_1357846887407_551777_21289, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846886914_99415_20141Collections() {
                parameters.add(objectToPass);
                parameters.add(_17_0_2_2_edc0357_1357846887407_551777_21289);
                parameters.add(_17_0_2_2_edc0357_1358283925264_845182_39149_exists);
                Set<Effect> effectsForeffect357Var = new TreeSet<Effect>();
                effectsForeffect357Var.add(effect357);
                addEffects((Parameter<?>) effect357Var, effectsForeffect357Var);
            }

            public void init_17_0_2_2_edc0357_1357846886914_99415_20141Dependencies() {
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846886918_574220_20156, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_2_edc0357_1357846887407_551777_21289, new Expression<Integer>(0));
                addDependency(_17_0_2_2_edc0357_1358283925264_845182_39149_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886917_42845_20146_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886917_42845_20146_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
            }

            public void init_17_0_2_2_edc0357_1357846886914_99415_20141Elaborations() {
                init_17_0_2_2_edc0357_1357846886914_99415_20141Dependencies();
                Expression<?>[] arguments358 = new Expression<?>[1];
                arguments358[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition358 = new Expression<Boolean>(_17_0_2_2_edc0357_1358283925264_845182_39149_exists);
                elaborationRule358 = addElaborationRule(condition358, _17_0_2_2_edc0357_1357846886579_898473_19865.this, Power._17_0_2_2_edc0357_1357846886579_898473_19865._17_0_2_2_edc0357_1358283925264_845182_39149.class, "_ForkNode_intializeP", arguments358);
            }
        }

        public class _17_0_2_2_edc0357_1357846886915_245196_20143 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846886915_245196_20143() {
                super();
                init_17_0_2_2_edc0357_1357846886915_245196_20143Members();
                init_17_0_2_2_edc0357_1357846886915_245196_20143Collections();
                init_17_0_2_2_edc0357_1357846886915_245196_20143Elaborations();
            }

            public _17_0_2_2_edc0357_1357846886915_245196_20143(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846886915_245196_20143Members();
                init_17_0_2_2_edc0357_1357846886915_245196_20143Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846886915_245196_20143Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_2_edc0357_1357846886916_811595_20144_exists = null;

            public BooleanParameter objectToPass = null;

            public Dependency durationDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846886916_811595_20144_existsDependency = null;

            public Dependency objectToPassDependency = null;

            public Effect effect359 = null;

            public Parameter effect359Var = null;

            public ElaborationRule elaborationRule360 = null;

            public void init_17_0_2_2_edc0357_1357846886915_245196_20143Members() {
                try {
                    if (_17_0_2_2_edc0357_1357846886916_811595_20144_exists == null) _17_0_2_2_edc0357_1357846886916_811595_20144_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846886916_811595_20144_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect359VarV = sig_17_0_2_2_edc0357_1357846886918_548871_20157;
                    effect359Var = new Parameter("effect359Var", null, null, this);
                    addDependency(effect359Var, new Expression(effect359VarV));
                    effect359 = new EffectFunction(new EffectFunction(effect359Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846886915_245196_20143Collections() {
                parameters.add(_17_0_2_2_edc0357_1357846886916_811595_20144_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect359Var = new TreeSet<Effect>();
                effectsForeffect359Var.add(effect359);
                addEffects((Parameter<?>) effect359Var, effectsForeffect359Var);
            }

            public void init_17_0_2_2_edc0357_1357846886915_245196_20143Dependencies() {
                addDependency(duration, new Expression<Integer>(1));
                addDependency(_17_0_2_2_edc0357_1357846886916_811595_20144_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886917_42845_20146_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886917_42845_20146_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(objectToPass, new Expression<Boolean>(true));
            }

            public void init_17_0_2_2_edc0357_1357846886915_245196_20143Elaborations() {
                init_17_0_2_2_edc0357_1357846886915_245196_20143Dependencies();
                Expression<?>[] arguments360 = new Expression<?>[1];
                arguments360[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition360 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846886916_811595_20144_exists);
                elaborationRule360 = addElaborationRule(condition360, _17_0_2_2_edc0357_1357846886579_898473_19865.this, Power._17_0_2_2_edc0357_1357846886579_898473_19865._17_0_2_2_edc0357_1357846886916_811595_20144.class, "Pinit_fork1_ForkNode_intializeP", arguments360);
            }
        }

        public class _17_0_2_2_edc0357_1357846886916_811595_20144 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846886916_811595_20144() {
                super();
                init_17_0_2_2_edc0357_1357846886916_811595_20144Members();
                init_17_0_2_2_edc0357_1357846886916_811595_20144Collections();
                init_17_0_2_2_edc0357_1357846886916_811595_20144Elaborations();
            }

            public _17_0_2_2_edc0357_1357846886916_811595_20144(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846886916_811595_20144Members();
                init_17_0_2_2_edc0357_1357846886916_811595_20144Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846886916_811595_20144Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_2_edc0357_1357846886914_99415_20141_exists = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846886912_805616_20137_exists = null;

            public BooleanParameter objectToPass = null;

            public Dependency _17_0_2_2_edc0357_1357846886914_99415_20141_existsDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846886912_805616_20137_existsDependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Effect effect361 = null;

            public Parameter effect361Var = null;

            public Effect effect362 = null;

            public Parameter effect362Var = null;

            public ElaborationRule elaborationRule363 = null;

            public ElaborationRule elaborationRule364 = null;

            public void init_17_0_2_2_edc0357_1357846886916_811595_20144Members() {
                try {
                    if (_17_0_2_2_edc0357_1357846886914_99415_20141_exists == null) _17_0_2_2_edc0357_1357846886914_99415_20141_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846886914_99415_20141_exists", (Boolean) false, this);
                    if (_17_0_2_2_edc0357_1357846886912_805616_20137_exists == null) _17_0_2_2_edc0357_1357846886912_805616_20137_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846886912_805616_20137_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect361VarV = sig_17_0_2_2_edc0357_1357846886918_574220_20156;
                    effect361Var = new Parameter("effect361Var", null, null, this);
                    addDependency(effect361Var, new Expression(effect361VarV));
                    effect361 = new EffectFunction(new EffectFunction(effect361Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect362VarV = sig_17_0_2_2_edc0357_1357846886918_279384_20158;
                    effect362Var = new Parameter("effect362Var", null, null, this);
                    addDependency(effect362Var, new Expression(effect362VarV));
                    effect362 = new EffectFunction(new EffectFunction(effect362Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846886916_811595_20144Collections() {
                parameters.add(_17_0_2_2_edc0357_1357846886914_99415_20141_exists);
                parameters.add(_17_0_2_2_edc0357_1357846886912_805616_20137_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect361Var = new TreeSet<Effect>();
                effectsForeffect361Var.add(effect361);
                addEffects((Parameter<?>) effect361Var, effectsForeffect361Var);
                Set<Effect> effectsForeffect362Var = new TreeSet<Effect>();
                effectsForeffect362Var.add(effect362);
                addEffects((Parameter<?>) effect362Var, effectsForeffect362Var);
            }

            public void init_17_0_2_2_edc0357_1357846886916_811595_20144Dependencies() {
                addDependency(_17_0_2_2_edc0357_1357846886914_99415_20141_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886917_42845_20146_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886917_42845_20146_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(_17_0_2_2_edc0357_1357846886912_805616_20137_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886917_42845_20146_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886917_42845_20146_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846886918_548871_20157, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_2_edc0357_1357846886916_811595_20144Elaborations() {
                init_17_0_2_2_edc0357_1357846886916_811595_20144Dependencies();
                Expression<?>[] arguments363 = new Expression<?>[1];
                arguments363[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition363 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846886912_805616_20137_exists);
                elaborationRule363 = addElaborationRule(condition363, _17_0_2_2_edc0357_1357846886579_898473_19865.this, Power._17_0_2_2_edc0357_1357846886579_898473_19865._17_0_2_2_edc0357_1357846886912_805616_20137.class, "Pinit_readself_ReadSelfAction_intializeP", arguments363);
                Expression<?>[] arguments364 = new Expression<?>[1];
                arguments364[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition364 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846886914_99415_20141_exists);
                elaborationRule364 = addElaborationRule(condition364, _17_0_2_2_edc0357_1357846886579_898473_19865.this, Power._17_0_2_2_edc0357_1357846886579_898473_19865._17_0_2_2_edc0357_1357846886914_99415_20141.class, "Pinit_spec0_ValueSpecificationAction_intializeP", arguments364);
            }
        }

        public class _17_0_2_2_edc0357_1357846886916_945196_20145 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846886916_945196_20145() {
                super();
                init_17_0_2_2_edc0357_1357846886916_945196_20145Members();
                init_17_0_2_2_edc0357_1357846886916_945196_20145Collections();
                init_17_0_2_2_edc0357_1357846886916_945196_20145Elaborations();
            }

            public _17_0_2_2_edc0357_1357846886916_945196_20145(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846886916_945196_20145Members();
                init_17_0_2_2_edc0357_1357846886916_945196_20145Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846886916_945196_20145Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter objectToPass1 = null;

            public BooleanParameter objectToPass = null;

            public Dependency objectToPass1Dependency = null;

            public Dependency finalNode_startTimeDependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Effect effect365 = null;

            public Parameter effect365Var = null;

            public void init_17_0_2_2_edc0357_1357846886916_945196_20145Members() {
                try {
                    if (objectToPass1 == null) objectToPass1 = new BooleanParameter("objectToPass1", (Boolean) null, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect365VarV = sig_17_0_2_2_edc0357_1357846886918_968934_20152;
                    effect365Var = new Parameter("effect365Var", null, null, this);
                    addDependency(effect365Var, new Expression(effect365VarV));
                    effect365 = new EffectFunction(new EffectFunction(effect365Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846886916_945196_20145Collections() {
                parameters.add(objectToPass1);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect365Var = new TreeSet<Effect>();
                effectsForeffect365Var.add(effect365);
                addEffects((Parameter<?>) effect365Var, effectsForeffect365Var);
            }

            public void init_17_0_2_2_edc0357_1357846886916_945196_20145Dependencies() {
                addDependency(objectToPass1, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846886918_832292_20154, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(finalNode_startTime, new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846886918_977728_20153, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_2_edc0357_1357846886916_945196_20145Elaborations() {
                init_17_0_2_2_edc0357_1357846886916_945196_20145Dependencies();
            }
        }

        public class _17_0_2_2_edc0357_1357846886917_42845_20146 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846886917_42845_20146() {
                super();
                init_17_0_2_2_edc0357_1357846886917_42845_20146Members();
                init_17_0_2_2_edc0357_1357846886917_42845_20146Collections();
                init_17_0_2_2_edc0357_1357846886917_42845_20146Elaborations();
            }

            public _17_0_2_2_edc0357_1357846886917_42845_20146(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846886917_42845_20146Members();
                init_17_0_2_2_edc0357_1357846886917_42845_20146Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846886917_42845_20146Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter objectToPass = null;

            public Dependency endTimeDependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Dependency finalNode_endTimeDependency = null;

            public void init_17_0_2_2_edc0357_1357846886917_42845_20146Members() {
                try {
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846886917_42845_20146Collections() {
                parameters.add(objectToPass);
            }

            public void init_17_0_2_2_edc0357_1357846886917_42845_20146Dependencies() {
                addDependency(endTime, new Expression<Integer>(new Functions.Plus(new Expression<Integer>(startTime), new Expression<Integer>(duration))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846886918_968934_20152, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(finalNode_endTime, new Expression<Integer>(endTime));
            }

            public void init_17_0_2_2_edc0357_1357846886917_42845_20146Elaborations() {
                init_17_0_2_2_edc0357_1357846886917_42845_20146Dependencies();
            }
        }

        public class _17_0_2_2_edc0357_1358283925264_845182_39149 extends DurativeEvent {

            public _17_0_2_2_edc0357_1358283925264_845182_39149() {
                super();
                init_17_0_2_2_edc0357_1358283925264_845182_39149Members();
                init_17_0_2_2_edc0357_1358283925264_845182_39149Collections();
                init_17_0_2_2_edc0357_1358283925264_845182_39149Elaborations();
            }

            public _17_0_2_2_edc0357_1358283925264_845182_39149(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1358283925264_845182_39149Members();
                init_17_0_2_2_edc0357_1358283925264_845182_39149Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1358283925264_845182_39149Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter addToDecider__17_0_2_2_edc0357_1357846886913_954037_20140 = null;

            public BooleanParameter addToDecider__17_0_2_2_edc0357_1357846886913_227952_20139 = null;

            public IntegerParameter myDeciderID_decider_17_0_2_2_edc0357_1357846886913_227952_20139 = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846886913_227952_20139_exists = null;

            public IntegerParameter myDeciderID_decider_17_0_2_2_edc0357_1357846886913_954037_20140 = null;

            public IntegerParameter objectToPass = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846886913_954037_20140_exists = null;

            public Dependency addToDecider__17_0_2_2_edc0357_1357846886913_954037_20140Dependency = null;

            public Dependency addToDecider__17_0_2_2_edc0357_1357846886913_227952_20139Dependency = null;

            public Dependency myDeciderID_decider_17_0_2_2_edc0357_1357846886913_227952_20139Dependency = null;

            public Dependency _17_0_2_2_edc0357_1357846886913_227952_20139_existsDependency = null;

            public Dependency myDeciderID_decider_17_0_2_2_edc0357_1357846886913_954037_20140Dependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846886913_954037_20140_existsDependency = null;

            public Effect effect366 = null;

            public Parameter effect366Var = null;

            public Effect effect367 = null;

            public Parameter effect367Var = null;

            public Effect effect368 = null;

            public Parameter effect368Var = null;

            public Effect effect369 = null;

            public Parameter effect369Var = null;

            public ElaborationRule elaborationRule370 = null;

            public ElaborationRule elaborationRule371 = null;

            public void init_17_0_2_2_edc0357_1358283925264_845182_39149Members() {
                try {
                    if (addToDecider__17_0_2_2_edc0357_1357846886913_954037_20140 == null) addToDecider__17_0_2_2_edc0357_1357846886913_954037_20140 = new BooleanParameter("addToDecider__17_0_2_2_edc0357_1357846886913_954037_20140", (Boolean) false, this);
                    if (addToDecider__17_0_2_2_edc0357_1357846886913_227952_20139 == null) addToDecider__17_0_2_2_edc0357_1357846886913_227952_20139 = new BooleanParameter("addToDecider__17_0_2_2_edc0357_1357846886913_227952_20139", (Boolean) false, this);
                    if (myDeciderID_decider_17_0_2_2_edc0357_1357846886913_227952_20139 == null) myDeciderID_decider_17_0_2_2_edc0357_1357846886913_227952_20139 = new IntegerParameter("myDeciderID_decider_17_0_2_2_edc0357_1357846886913_227952_20139", (Integer) 2, this);
                    if (_17_0_2_2_edc0357_1357846886913_227952_20139_exists == null) _17_0_2_2_edc0357_1357846886913_227952_20139_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846886913_227952_20139_exists", (Boolean) false, this);
                    if (myDeciderID_decider_17_0_2_2_edc0357_1357846886913_954037_20140 == null) myDeciderID_decider_17_0_2_2_edc0357_1357846886913_954037_20140 = new IntegerParameter("myDeciderID_decider_17_0_2_2_edc0357_1357846886913_954037_20140", (Integer) 2, this);
                    if (objectToPass == null) objectToPass = new IntegerParameter("objectToPass", (Integer) null, this);
                    if (_17_0_2_2_edc0357_1357846886913_954037_20140_exists == null) _17_0_2_2_edc0357_1357846886913_954037_20140_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846886913_954037_20140_exists", (Boolean) false, this);
                    Object effect366VarV = sig_17_0_2_2_edc0357_1357846886917_640040_20148;
                    effect366Var = new Parameter("effect366Var", null, null, this);
                    addDependency(effect366Var, new Expression(effect366VarV));
                    effect366 = new EffectFunction(new EffectFunction(effect366Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect367VarV = sig_17_0_2_2_edc0357_1358283929447_249637_39158;
                    effect367Var = new Parameter("effect367Var", null, null, this);
                    addDependency(effect367Var, new Expression(effect367VarV));
                    effect367 = new EffectFunction(new EffectFunction(effect367Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect368VarV = decider_17_0_2_2_edc0357_1357846886913_227952_20139;
                    effect368Var = new Parameter("effect368Var", null, null, this);
                    addDependency(effect368Var, new Expression(effect368VarV));
                    effect368 = new EffectFunction(new EffectFunction(effect368Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class, java.lang.Boolean.class), new Object[] { endTime, myDeciderID_decider_17_0_2_2_edc0357_1357846886913_227952_20139, addToDecider__17_0_2_2_edc0357_1357846886913_227952_20139 }));
                    Object effect369VarV = decider_17_0_2_2_edc0357_1357846886913_954037_20140;
                    effect369Var = new Parameter("effect369Var", null, null, this);
                    addDependency(effect369Var, new Expression(effect369VarV));
                    effect369 = new EffectFunction(new EffectFunction(effect369Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class, java.lang.Boolean.class), new Object[] { endTime, myDeciderID_decider_17_0_2_2_edc0357_1357846886913_954037_20140, addToDecider__17_0_2_2_edc0357_1357846886913_954037_20140 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1358283925264_845182_39149Collections() {
                parameters.add(addToDecider__17_0_2_2_edc0357_1357846886913_954037_20140);
                parameters.add(addToDecider__17_0_2_2_edc0357_1357846886913_227952_20139);
                parameters.add(myDeciderID_decider_17_0_2_2_edc0357_1357846886913_227952_20139);
                parameters.add(_17_0_2_2_edc0357_1357846886913_227952_20139_exists);
                parameters.add(myDeciderID_decider_17_0_2_2_edc0357_1357846886913_954037_20140);
                parameters.add(objectToPass);
                parameters.add(_17_0_2_2_edc0357_1357846886913_954037_20140_exists);
                Set<Effect> effectsForeffect366Var = new TreeSet<Effect>();
                effectsForeffect366Var.add(effect366);
                addEffects((Parameter<?>) effect366Var, effectsForeffect366Var);
                Set<Effect> effectsForeffect367Var = new TreeSet<Effect>();
                effectsForeffect367Var.add(effect367);
                addEffects((Parameter<?>) effect367Var, effectsForeffect367Var);
                Set<Effect> effectsForeffect368Var = new TreeSet<Effect>();
                effectsForeffect368Var.add(effect368);
                addEffects((Parameter<?>) effect368Var, effectsForeffect368Var);
                Set<Effect> effectsForeffect369Var = new TreeSet<Effect>();
                effectsForeffect369Var.add(effect369);
                addEffects((Parameter<?>) effect369Var, effectsForeffect369Var);
            }

            public void init_17_0_2_2_edc0357_1358283925264_845182_39149Dependencies() {
                addDependency(addToDecider__17_0_2_2_edc0357_1357846886913_954037_20140, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886917_42845_20146_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886917_42845_20146_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(addToDecider__17_0_2_2_edc0357_1357846886913_227952_20139, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886917_42845_20146_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886917_42845_20146_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(myDeciderID_decider_17_0_2_2_edc0357_1357846886913_227952_20139, new Expression<Integer>(2));
                addDependency(_17_0_2_2_edc0357_1357846886913_227952_20139_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886917_42845_20146_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886917_42845_20146_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846886913_227952_20139, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846886913_227952_20139, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846886913_227952_20139, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_2_edc0357_1357846886913_227952_20139)))))))));
                addDependency(myDeciderID_decider_17_0_2_2_edc0357_1357846886913_954037_20140, new Expression<Integer>(2));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1358283926570_639411_39153, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_2_edc0357_1357846886913_954037_20140_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886917_42845_20146_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886917_42845_20146_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846886913_954037_20140, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846886913_954037_20140, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846886913_954037_20140, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_2_edc0357_1357846886913_954037_20140)))))))));
            }

            public void init_17_0_2_2_edc0357_1358283925264_845182_39149Elaborations() {
                init_17_0_2_2_edc0357_1358283925264_845182_39149Dependencies();
                Expression<?>[] arguments370 = new Expression<?>[1];
                arguments370[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition370 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846886913_227952_20139_exists);
                elaborationRule370 = addElaborationRule(condition370, _17_0_2_2_edc0357_1357846886579_898473_19865.this, Power._17_0_2_2_edc0357_1357846886579_898473_19865._17_0_2_2_edc0357_1357846886913_227952_20139.class, "Pinit_set_load_AddStructuralFeatureValueAction_intializeP", arguments370);
                Expression<?>[] arguments371 = new Expression<?>[1];
                arguments371[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition371 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846886913_954037_20140_exists);
                elaborationRule371 = addElaborationRule(condition371, _17_0_2_2_edc0357_1357846886579_898473_19865.this, Power._17_0_2_2_edc0357_1357846886579_898473_19865._17_0_2_2_edc0357_1357846886913_954037_20140.class, "Pinit_set_aug_AddStructuralFeatureValueAction_intializeP", arguments371);
            }
        }

        public _17_0_2_2_edc0357_1357846886579_898473_19865(Expression<Integer> startTime, Expression<DurativeEvent> caller) {
            super();
            init_17_0_2_2_edc0357_1357846886579_898473_19865Members();
            init_17_0_2_2_edc0357_1357846886579_898473_19865Collections();
            addDependency(this.startTime, startTime);
            addDependency(this.caller, caller);
            init_17_0_2_2_edc0357_1357846886579_898473_19865Elaborations();
            fixTimeDependencies();
        }

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_2_edc0357_1357846886918_574220_20156 = null;

        public Parameter< DurativeEvent > caller = null;

        public BooleanParameter _17_0_2_2_edc0357_1357846886917_42845_20146_exists = null;

        public Parameter< TimeVaryingList<Integer> > decider_17_0_2_2_edc0357_1357846886913_227952_20139 = null;

        public Parameter< TimeVaryingList<Integer> > decider_17_0_2_2_edc0357_1357846886916_945196_20145 = null;

        public IntegerParameter finalNode_endTime = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_2_2_edc0357_1358283929447_249637_39158 = null;

        public Parameter< ObjectFlow<Power> > sig_17_0_2_2_edc0357_1357846886918_883621_20151 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_2_edc0357_1357846886918_548871_20157 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_2_edc0357_1357846886918_977728_20153 = null;

        public Parameter< TimeVaryingList<Integer> > decider_17_0_2_2_edc0357_1357846886913_954037_20140 = null;

        public IntegerParameter finalNode_startTime = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_2_edc0357_1357846886918_832292_20154 = null;

        public Parameter< ObjectFlow<Power> > sig_17_0_2_2_edc0357_1357846886917_872097_20150 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_2_edc0357_1357846886918_968934_20152 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_2_2_edc0357_1358283926570_639411_39153 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_2_edc0357_1357846886918_279384_20158 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_2_2_edc0357_1357846886917_640040_20148 = null;

        public Parameter< ObjectFlow<Power> > sig_17_0_2_2_edc0357_1357846886917_177170_20147 = null;

        public Dependency caller_endTimeDependency = null;

        public Dependency _17_0_2_2_edc0357_1357846886917_42845_20146_existsDependency = null;

        public Dependency endTimeDependency = null;

        public ElaborationRule elaborationRule339 = null;

        public ElaborationRule elaborationRule340 = null;

        public void init_17_0_2_2_edc0357_1357846886579_898473_19865Members() {
            try {
                if (sig_17_0_2_2_edc0357_1357846886918_574220_20156 == null) sig_17_0_2_2_edc0357_1357846886918_574220_20156 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_2_edc0357_1357846886918_574220_20156", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846886918_574220_20156" })).evaluate(true), this);
                if (caller == null) caller = new Parameter<DurativeEvent>("caller", null, (DurativeEvent) null, this);
                if (_17_0_2_2_edc0357_1357846886917_42845_20146_exists == null) _17_0_2_2_edc0357_1357846886917_42845_20146_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846886917_42845_20146_exists", (Boolean) false, this);
                if (decider_17_0_2_2_edc0357_1357846886913_227952_20139 == null) decider_17_0_2_2_edc0357_1357846886913_227952_20139 = new Parameter<TimeVaryingList<Integer>>("decider_17_0_2_2_edc0357_1357846886913_227952_20139", null, (TimeVaryingList<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingList.class, java.lang.String.class, int.class), new Object[] { "decider_17_0_2_2_edc0357_1357846886913_227952_20139", 2 })).evaluate(true), this);
                if (decider_17_0_2_2_edc0357_1357846886916_945196_20145 == null) decider_17_0_2_2_edc0357_1357846886916_945196_20145 = new Parameter<TimeVaryingList<Integer>>("decider_17_0_2_2_edc0357_1357846886916_945196_20145", null, (TimeVaryingList<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingList.class, java.lang.String.class, int.class), new Object[] { "decider_17_0_2_2_edc0357_1357846886916_945196_20145", 2 })).evaluate(true), this);
                if (finalNode_endTime == null) finalNode_endTime = new IntegerParameter("finalNode_endTime", (Integer) null, this);
                if (sig_17_0_2_2_edc0357_1358283929447_249637_39158 == null) sig_17_0_2_2_edc0357_1358283929447_249637_39158 = new Parameter<ObjectFlow<Integer>>("sig_17_0_2_2_edc0357_1358283929447_249637_39158", null, (ObjectFlow<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1358283929447_249637_39158" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846886918_883621_20151 == null) sig_17_0_2_2_edc0357_1357846886918_883621_20151 = new Parameter<ObjectFlow<Power>>("sig_17_0_2_2_edc0357_1357846886918_883621_20151", null, (ObjectFlow<Power>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846886918_883621_20151" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846886918_548871_20157 == null) sig_17_0_2_2_edc0357_1357846886918_548871_20157 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_2_edc0357_1357846886918_548871_20157", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846886918_548871_20157" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846886918_977728_20153 == null) sig_17_0_2_2_edc0357_1357846886918_977728_20153 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_2_edc0357_1357846886918_977728_20153", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846886918_977728_20153" })).evaluate(true), this);
                if (decider_17_0_2_2_edc0357_1357846886913_954037_20140 == null) decider_17_0_2_2_edc0357_1357846886913_954037_20140 = new Parameter<TimeVaryingList<Integer>>("decider_17_0_2_2_edc0357_1357846886913_954037_20140", null, (TimeVaryingList<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingList.class, java.lang.String.class, int.class), new Object[] { "decider_17_0_2_2_edc0357_1357846886913_954037_20140", 2 })).evaluate(true), this);
                if (finalNode_startTime == null) finalNode_startTime = new IntegerParameter("finalNode_startTime", (Integer) null, this);
                if (sig_17_0_2_2_edc0357_1357846886918_832292_20154 == null) sig_17_0_2_2_edc0357_1357846886918_832292_20154 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_2_edc0357_1357846886918_832292_20154", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846886918_832292_20154" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846886917_872097_20150 == null) sig_17_0_2_2_edc0357_1357846886917_872097_20150 = new Parameter<ObjectFlow<Power>>("sig_17_0_2_2_edc0357_1357846886917_872097_20150", null, (ObjectFlow<Power>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846886917_872097_20150" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846886918_968934_20152 == null) sig_17_0_2_2_edc0357_1357846886918_968934_20152 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_2_edc0357_1357846886918_968934_20152", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846886918_968934_20152" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1358283926570_639411_39153 == null) sig_17_0_2_2_edc0357_1358283926570_639411_39153 = new Parameter<ObjectFlow<Integer>>("sig_17_0_2_2_edc0357_1358283926570_639411_39153", null, (ObjectFlow<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1358283926570_639411_39153" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846886918_279384_20158 == null) sig_17_0_2_2_edc0357_1357846886918_279384_20158 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_2_edc0357_1357846886918_279384_20158", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846886918_279384_20158" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846886917_640040_20148 == null) sig_17_0_2_2_edc0357_1357846886917_640040_20148 = new Parameter<ObjectFlow<Integer>>("sig_17_0_2_2_edc0357_1357846886917_640040_20148", null, (ObjectFlow<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846886917_640040_20148" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846886917_177170_20147 == null) sig_17_0_2_2_edc0357_1357846886917_177170_20147 = new Parameter<ObjectFlow<Power>>("sig_17_0_2_2_edc0357_1357846886917_177170_20147", null, (ObjectFlow<Power>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846886917_177170_20147" })).evaluate(true), this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void init_17_0_2_2_edc0357_1357846886579_898473_19865Collections() {
            parameters.add(sig_17_0_2_2_edc0357_1357846886918_574220_20156);
            parameters.add(caller);
            parameters.add(_17_0_2_2_edc0357_1357846886917_42845_20146_exists);
            parameters.add(decider_17_0_2_2_edc0357_1357846886913_227952_20139);
            parameters.add(decider_17_0_2_2_edc0357_1357846886916_945196_20145);
            parameters.add(finalNode_endTime);
            parameters.add(sig_17_0_2_2_edc0357_1358283929447_249637_39158);
            parameters.add(sig_17_0_2_2_edc0357_1357846886918_883621_20151);
            parameters.add(sig_17_0_2_2_edc0357_1357846886918_548871_20157);
            parameters.add(sig_17_0_2_2_edc0357_1357846886918_977728_20153);
            parameters.add(decider_17_0_2_2_edc0357_1357846886913_954037_20140);
            parameters.add(finalNode_startTime);
            parameters.add(sig_17_0_2_2_edc0357_1357846886918_832292_20154);
            parameters.add(sig_17_0_2_2_edc0357_1357846886917_872097_20150);
            parameters.add(sig_17_0_2_2_edc0357_1357846886918_968934_20152);
            parameters.add(sig_17_0_2_2_edc0357_1358283926570_639411_39153);
            parameters.add(sig_17_0_2_2_edc0357_1357846886918_279384_20158);
            parameters.add(sig_17_0_2_2_edc0357_1357846886917_640040_20148);
            parameters.add(sig_17_0_2_2_edc0357_1357846886917_177170_20147);
        }

        public void init_17_0_2_2_edc0357_1357846886579_898473_19865Dependencies() {
            caller.getValue(true).addDependency(caller.getValue(true).endTime, new Expression<Integer>(finalNode_endTime));
            addDependency(_17_0_2_2_edc0357_1357846886917_42845_20146_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.NotEquals(new Expression<Integer>(finalNode_startTime), null)), new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846886918_968934_20152, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(new Functions.Minus(new Expression<Integer>(finalNode_startTime), new Expression<Integer>(1))) })))));
            addDependency(endTime, new Expression<Integer>(finalNode_endTime));
        }

        public void init_17_0_2_2_edc0357_1357846886579_898473_19865Elaborations() {
            init_17_0_2_2_edc0357_1357846886579_898473_19865Dependencies();
            Expression<?>[] arguments339 = new Expression<?>[1];
            arguments339[0] = new Expression<Integer>(finalNode_startTime);
            Expression<Boolean> condition339 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846886917_42845_20146_exists);
            elaborationRule339 = addElaborationRule(condition339, _17_0_2_2_edc0357_1357846886579_898473_19865.this, Power._17_0_2_2_edc0357_1357846886579_898473_19865._17_0_2_2_edc0357_1357846886917_42845_20146.class, "Pinit_end_ActivityFinalNode_intializeP", arguments339);
            Expression<?>[] arguments340 = new Expression<?>[1];
            arguments340[0] = new Expression<Integer>(startTime);
            Expression<Boolean> condition340 = new Expression<Boolean>(true);
            elaborationRule340 = addElaborationRule(condition340, _17_0_2_2_edc0357_1357846886579_898473_19865.this, Power._17_0_2_2_edc0357_1357846886579_898473_19865._17_0_2_2_edc0357_1357846886915_245196_20143.class, "Pinit_start_InitialNode_intializeP", arguments340);
        }
    }

    public class _17_0_2_2_edc0357_1357846886580_140004_19866 extends DurativeEvent {

        public _17_0_2_2_edc0357_1357846886580_140004_19866() {
            super();
            init_17_0_2_2_edc0357_1357846886580_140004_19866Members();
            init_17_0_2_2_edc0357_1357846886580_140004_19866Collections();
            init_17_0_2_2_edc0357_1357846886580_140004_19866Elaborations();
        }

        public class _17_0_2_2_edc0357_1357846886931_741810_20180 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846886931_741810_20180() {
                super();
                init_17_0_2_2_edc0357_1357846886931_741810_20180Members();
                init_17_0_2_2_edc0357_1357846886931_741810_20180Collections();
                init_17_0_2_2_edc0357_1357846886931_741810_20180Elaborations();
            }

            public _17_0_2_2_edc0357_1357846886931_741810_20180(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846886931_741810_20180Members();
                init_17_0_2_2_edc0357_1357846886931_741810_20180Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846886931_741810_20180Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_2_edc0357_1357846886935_440735_20187_exists = null;

            public Parameter< Power > _17_0_2_2_edc0357_1357846887413_30661_21305 = null;

            public BooleanParameter objectToPass = null;

            public Dependency _17_0_2_2_edc0357_1357846886935_440735_20187_existsDependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Effect effect374 = null;

            public Parameter effect374Var = null;

            public ElaborationRule elaborationRule375 = null;

            public void init_17_0_2_2_edc0357_1357846886931_741810_20180Members() {
                try {
                    if (_17_0_2_2_edc0357_1357846886935_440735_20187_exists == null) _17_0_2_2_edc0357_1357846886935_440735_20187_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846886935_440735_20187_exists", (Boolean) false, this);
                    if (_17_0_2_2_edc0357_1357846887413_30661_21305 == null) _17_0_2_2_edc0357_1357846887413_30661_21305 = new Parameter<Power>("_17_0_2_2_edc0357_1357846887413_30661_21305", null, (Power) Power.this, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect374VarV = sig_17_0_2_2_edc0357_1357846886936_848239_20192;
                    effect374Var = new Parameter("effect374Var", null, null, this);
                    addDependency(effect374Var, new Expression(effect374VarV));
                    effect374 = new EffectFunction(new EffectFunction(effect374Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Power>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { _17_0_2_2_edc0357_1357846887413_30661_21305, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846886931_741810_20180Collections() {
                parameters.add(_17_0_2_2_edc0357_1357846886935_440735_20187_exists);
                parameters.add(_17_0_2_2_edc0357_1357846887413_30661_21305);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect374Var = new TreeSet<Effect>();
                effectsForeffect374Var.add(effect374);
                addEffects((Parameter<?>) effect374Var, effectsForeffect374Var);
            }

            public void init_17_0_2_2_edc0357_1357846886931_741810_20180Dependencies() {
                addDependency(_17_0_2_2_edc0357_1357846886935_440735_20187_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886932_836588_20182_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886932_836588_20182_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846886936_298417_20189, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_2_edc0357_1357846886931_741810_20180Elaborations() {
                init_17_0_2_2_edc0357_1357846886931_741810_20180Dependencies();
                Expression<?>[] arguments375 = new Expression<?>[1];
                arguments375[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition375 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846886935_440735_20187_exists);
                elaborationRule375 = addElaborationRule(condition375, _17_0_2_2_edc0357_1357846886580_140004_19866.this, Power._17_0_2_2_edc0357_1357846886580_140004_19866._17_0_2_2_edc0357_1357846886935_440735_20187.class, "PGen_fork_ForkNode_generate", arguments375);
            }
        }

        public class _17_0_2_2_edc0357_1357846886932_93917_20181 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846886932_93917_20181() {
                super();
                init_17_0_2_2_edc0357_1357846886932_93917_20181Members();
                init_17_0_2_2_edc0357_1357846886932_93917_20181Collections();
                init_17_0_2_2_edc0357_1357846886932_93917_20181Elaborations();
            }

            public _17_0_2_2_edc0357_1357846886932_93917_20181(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846886932_93917_20181Members();
                init_17_0_2_2_edc0357_1357846886932_93917_20181Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846886932_93917_20181Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_2_edc0357_1357846886931_741810_20180_exists = null;

            public BooleanParameter objectToPass = null;

            public Dependency _17_0_2_2_edc0357_1357846886931_741810_20180_existsDependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Effect effect376 = null;

            public Parameter effect376Var = null;

            public ElaborationRule elaborationRule377 = null;

            public void init_17_0_2_2_edc0357_1357846886932_93917_20181Members() {
                try {
                    if (_17_0_2_2_edc0357_1357846886931_741810_20180_exists == null) _17_0_2_2_edc0357_1357846886931_741810_20180_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846886931_741810_20180_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect376VarV = sig_17_0_2_2_edc0357_1357846886936_298417_20189;
                    effect376Var = new Parameter("effect376Var", null, null, this);
                    addDependency(effect376Var, new Expression(effect376VarV));
                    effect376 = new EffectFunction(new EffectFunction(effect376Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846886932_93917_20181Collections() {
                parameters.add(_17_0_2_2_edc0357_1357846886931_741810_20180_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect376Var = new TreeSet<Effect>();
                effectsForeffect376Var.add(effect376);
                addEffects((Parameter<?>) effect376Var, effectsForeffect376Var);
            }

            public void init_17_0_2_2_edc0357_1357846886932_93917_20181Dependencies() {
                addDependency(_17_0_2_2_edc0357_1357846886931_741810_20180_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886932_836588_20182_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886932_836588_20182_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(true));
            }

            public void init_17_0_2_2_edc0357_1357846886932_93917_20181Elaborations() {
                init_17_0_2_2_edc0357_1357846886932_93917_20181Dependencies();
                Expression<?>[] arguments377 = new Expression<?>[1];
                arguments377[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition377 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846886931_741810_20180_exists);
                elaborationRule377 = addElaborationRule(condition377, _17_0_2_2_edc0357_1357846886580_140004_19866.this, Power._17_0_2_2_edc0357_1357846886580_140004_19866._17_0_2_2_edc0357_1357846886931_741810_20180.class, "PGen_readself_ReadSelfAction_generate", arguments377);
            }
        }

        public class _17_0_2_2_edc0357_1357846886932_836588_20182 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846886932_836588_20182() {
                super();
                init_17_0_2_2_edc0357_1357846886932_836588_20182Members();
                init_17_0_2_2_edc0357_1357846886932_836588_20182Collections();
                init_17_0_2_2_edc0357_1357846886932_836588_20182Elaborations();
            }

            public _17_0_2_2_edc0357_1357846886932_836588_20182(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846886932_836588_20182Members();
                init_17_0_2_2_edc0357_1357846886932_836588_20182Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846886932_836588_20182Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter objectToPass = null;

            public Dependency endTimeDependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Dependency finalNode_endTimeDependency = null;

            public void init_17_0_2_2_edc0357_1357846886932_836588_20182Members() {
                try {
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846886932_836588_20182Collections() {
                parameters.add(objectToPass);
            }

            public void init_17_0_2_2_edc0357_1357846886932_836588_20182Dependencies() {
                addDependency(endTime, new Expression<Integer>(new Functions.Plus(new Expression<Integer>(startTime), new Expression<Integer>(duration))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1358277576291_904799_38811, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(finalNode_endTime, new Expression<Integer>(endTime));
            }

            public void init_17_0_2_2_edc0357_1357846886932_836588_20182Elaborations() {
                init_17_0_2_2_edc0357_1357846886932_836588_20182Dependencies();
            }
        }

        public class _17_0_2_2_edc0357_1357846886933_732644_20183 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846886933_732644_20183() {
                super();
                init_17_0_2_2_edc0357_1357846886933_732644_20183Members();
                init_17_0_2_2_edc0357_1357846886933_732644_20183Collections();
                init_17_0_2_2_edc0357_1357846886933_732644_20183Elaborations();
            }

            public _17_0_2_2_edc0357_1357846886933_732644_20183(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846886933_732644_20183Members();
                init_17_0_2_2_edc0357_1357846886933_732644_20183Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846886933_732644_20183Elaborations();
                fixTimeDependencies();
            }

            public IntegerParameter _17_0_2_2_edc0357_1357846887414_537778_21306 = null;

            public Parameter< Power > _17_0_2_2_edc0357_1357846887414_758742_21307 = null;

            public IntegerParameter myDeciderID_decider_17_0_2_2_edc0357_1358277276270_502536_38688 = null;

            public BooleanParameter addToDecider__17_0_2_2_edc0357_1358277276270_502536_38688 = null;

            public BooleanParameter _17_0_2_2_edc0357_1358277276270_502536_38688_exists = null;

            public Dependency _17_0_2_2_edc0357_1357846887414_537778_21306Dependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887414_758742_21307Dependency = null;

            public Dependency durationDependency = null;

            public Dependency myDeciderID_decider_17_0_2_2_edc0357_1358277276270_502536_38688Dependency = null;

            public Dependency _17_0_2_2_edc0357_1358277276270_502536_38688_existsDependency = null;

            public Dependency addToDecider__17_0_2_2_edc0357_1358277276270_502536_38688Dependency = null;

            public Effect effect378 = null;

            public Parameter effect378Var = null;

            public Effect effect379 = null;

            public Parameter effect379Var = null;

            public ElaborationRule elaborationRule380 = null;

            public void init_17_0_2_2_edc0357_1357846886933_732644_20183Members() {
                try {
                    if (_17_0_2_2_edc0357_1357846887414_537778_21306 == null) _17_0_2_2_edc0357_1357846887414_537778_21306 = new IntegerParameter("_17_0_2_2_edc0357_1357846887414_537778_21306", (Integer) null, this);
                    if (_17_0_2_2_edc0357_1357846887414_758742_21307 == null) _17_0_2_2_edc0357_1357846887414_758742_21307 = new Parameter<Power>("_17_0_2_2_edc0357_1357846887414_758742_21307", null, (Power) null, this);
                    if (myDeciderID_decider_17_0_2_2_edc0357_1358277276270_502536_38688 == null) myDeciderID_decider_17_0_2_2_edc0357_1358277276270_502536_38688 = new IntegerParameter("myDeciderID_decider_17_0_2_2_edc0357_1358277276270_502536_38688", (Integer) 1, this);
                    if (addToDecider__17_0_2_2_edc0357_1358277276270_502536_38688 == null) addToDecider__17_0_2_2_edc0357_1358277276270_502536_38688 = new BooleanParameter("addToDecider__17_0_2_2_edc0357_1358277276270_502536_38688", (Boolean) false, this);
                    if (_17_0_2_2_edc0357_1358277276270_502536_38688_exists == null) _17_0_2_2_edc0357_1358277276270_502536_38688_exists = new BooleanParameter("_17_0_2_2_edc0357_1358277276270_502536_38688_exists", (Boolean) false, this);
                    Object effect378VarV = sig_17_0_2_2_edc0357_1358277380386_823740_38739;
                    effect378Var = new Parameter("effect378Var", null, null, this);
                    addDependency(effect378Var, new Expression(effect378VarV));
                    effect378 = new EffectFunction(new EffectFunction(effect378Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { _17_0_2_2_edc0357_1357846887414_537778_21306, endTime }));
                    Object effect379VarV = decider_17_0_2_2_edc0357_1358277276270_502536_38688;
                    effect379Var = new Parameter("effect379Var", null, null, this);
                    addDependency(effect379Var, new Expression(effect379VarV));
                    effect379 = new EffectFunction(new EffectFunction(effect379Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class, java.lang.Boolean.class), new Object[] { endTime, myDeciderID_decider_17_0_2_2_edc0357_1358277276270_502536_38688, addToDecider__17_0_2_2_edc0357_1358277276270_502536_38688 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846886933_732644_20183Collections() {
                parameters.add(_17_0_2_2_edc0357_1357846887414_537778_21306);
                parameters.add(_17_0_2_2_edc0357_1357846887414_758742_21307);
                parameters.add(myDeciderID_decider_17_0_2_2_edc0357_1358277276270_502536_38688);
                parameters.add(addToDecider__17_0_2_2_edc0357_1358277276270_502536_38688);
                parameters.add(_17_0_2_2_edc0357_1358277276270_502536_38688_exists);
                Set<Effect> effectsForeffect378Var = new TreeSet<Effect>();
                effectsForeffect378Var.add(effect378);
                addEffects((Parameter<?>) effect378Var, effectsForeffect378Var);
                Set<Effect> effectsForeffect379Var = new TreeSet<Effect>();
                effectsForeffect379Var.add(effect379);
                addEffects((Parameter<?>) effect379Var, effectsForeffect379Var);
            }

            public void init_17_0_2_2_edc0357_1357846886933_732644_20183Dependencies() {
                addDependency(_17_0_2_2_edc0357_1357846887414_537778_21306, new Expression(new EffectFunction(null, ClassUtils.getMethodForArgTypes("TimeVaryingPlottableMap<Double>", "PowerSystem3", "getValue", java.lang.Integer.class), new Object[] { new Expression<Integer>(startTime) }, new FunctionCall(_17_0_2_2_edc0357_1357846887414_758742_21307, Parameter.class, "getMember", new Object[] { "genProfile__17_0_2_2_edc0357_1358276700262_461437_38482" }))));
                addDependency(_17_0_2_2_edc0357_1357846887414_758742_21307, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846886936_38505_20193, ClassUtils.getMethodForArgTypes("ObjectFlow<Power>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(myDeciderID_decider_17_0_2_2_edc0357_1358277276270_502536_38688, new Expression<Integer>(1));
                addDependency(_17_0_2_2_edc0357_1358277276270_502536_38688_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886932_836588_20182_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886932_836588_20182_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1358277276270_502536_38688, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1358277276270_502536_38688, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1358277276270_502536_38688, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_2_edc0357_1358277276270_502536_38688)))))))));
                addDependency(addToDecider__17_0_2_2_edc0357_1358277276270_502536_38688, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886932_836588_20182_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886932_836588_20182_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
            }

            public void init_17_0_2_2_edc0357_1357846886933_732644_20183Elaborations() {
                init_17_0_2_2_edc0357_1357846886933_732644_20183Dependencies();
                Expression<?>[] arguments380 = new Expression<?>[1];
                arguments380[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition380 = new Expression<Boolean>(_17_0_2_2_edc0357_1358277276270_502536_38688_exists);
                elaborationRule380 = addElaborationRule(condition380, _17_0_2_2_edc0357_1357846886580_140004_19866.this, Power._17_0_2_2_edc0357_1357846886580_140004_19866._17_0_2_2_edc0357_1358277276270_502536_38688.class, "_CallBehaviorAction_generate", arguments380);
            }
        }

        public class _17_0_2_2_edc0357_1357846886934_191509_20185 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846886934_191509_20185() {
                super();
                init_17_0_2_2_edc0357_1357846886934_191509_20185Members();
                init_17_0_2_2_edc0357_1357846886934_191509_20185Collections();
                init_17_0_2_2_edc0357_1357846886934_191509_20185Elaborations();
            }

            public _17_0_2_2_edc0357_1357846886934_191509_20185(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846886934_191509_20185Members();
                init_17_0_2_2_edc0357_1357846886934_191509_20185Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846886934_191509_20185Elaborations();
                fixTimeDependencies();
            }

            public IntegerParameter _17_0_2_2_edc0357_1357846887416_926173_21311 = null;

            public BooleanParameter objectToPass = null;

            public Parameter< Power_System.SignalreceiveGenReading > signalObject = null;

            public Parameter< Power > _17_0_2_2_edc0357_1357846887415_968978_21310 = null;

            public Dependency _17_0_2_2_edc0357_1357846887416_926173_21311Dependency = null;

            public Dependency finalNode_startTimeDependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Dependency signalObjectDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887415_968978_21310Dependency = null;

            public Effect effect381 = null;

            public Parameter effect381Var = null;

            public Effect effect382 = null;

            public Parameter effect382Var = null;

            public Effect effect383 = null;

            public Parameter effect383Var = null;

            public void init_17_0_2_2_edc0357_1357846886934_191509_20185Members() {
                try {
                    if (_17_0_2_2_edc0357_1357846887416_926173_21311 == null) _17_0_2_2_edc0357_1357846887416_926173_21311 = new IntegerParameter("_17_0_2_2_edc0357_1357846887416_926173_21311", (Integer) null, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (signalObject == null) signalObject = new Parameter<Power_System.SignalreceiveGenReading>("signalObject", null, (Power_System.SignalreceiveGenReading) null, this);
                    if (_17_0_2_2_edc0357_1357846887415_968978_21310 == null) _17_0_2_2_edc0357_1357846887415_968978_21310 = new Parameter<Power>("_17_0_2_2_edc0357_1357846887415_968978_21310", null, (Power) null, this);
                    Object effect381VarV = sig_17_0_2_2_edc0357_1358277576291_904799_38811;
                    effect381Var = new Parameter("effect381Var", null, null, this);
                    addDependency(effect381Var, new Expression(effect381VarV));
                    effect381 = new EffectFunction(new EffectFunction(effect381Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect382VarV = new FunctionCall(x, Parameter.class, "getMember", new Object[] { "ss_17_0_2_2_edc0357_1357846886585_816142_19873_receiveGenReading" });
                    effect382Var = new Parameter("effect382Var", null, null, this);
                    addDependency(effect382Var, new Expression(effect382VarV));
                    effect382 = new EffectFunction(new EffectFunction(null, ClassUtils.getMethodForArgTypes("ObjectFlow<SignalreceiveGenReading>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { signalObject, endTime }, effect382Var));
                    Object effect383VarV = new FunctionCall(signalObject, Parameter.class, "getMember", new Object[] { "actual_power__17_0_2_2_edc0357_1357846886651_479103_19962" });
                    effect383Var = new Parameter("effect383Var", null, null, this);
                    addDependency(effect383Var, new Expression(effect383VarV));
                    effect383 = new EffectFunction(new EffectFunction(null, ClassUtils.getMethodForArgTypes("TimeVaryingMap<Integer>", "PowerSystem3", "setValue", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, _17_0_2_2_edc0357_1357846887416_926173_21311 }, effect383Var));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846886934_191509_20185Collections() {
                parameters.add(_17_0_2_2_edc0357_1357846887416_926173_21311);
                parameters.add(objectToPass);
                parameters.add(signalObject);
                parameters.add(_17_0_2_2_edc0357_1357846887415_968978_21310);
                Set<Effect> effectsForeffect381Var = new TreeSet<Effect>();
                effectsForeffect381Var.add(effect381);
                addEffects((Parameter<?>) effect381Var, effectsForeffect381Var);
                Set<Effect> effectsForeffect382Var = new TreeSet<Effect>();
                effectsForeffect382Var.add(effect382);
                addEffects((Parameter<?>) effect382Var, effectsForeffect382Var);
                Set<Effect> effectsForeffect383Var = new TreeSet<Effect>();
                effectsForeffect383Var.add(effect383);
                addEffects((Parameter<?>) effect383Var, effectsForeffect383Var);
            }

            public void init_17_0_2_2_edc0357_1357846886934_191509_20185Dependencies() {
                addDependency(_17_0_2_2_edc0357_1357846887416_926173_21311, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846886936_674003_20191, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(finalNode_startTime, new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1358277571267_916379_38806, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(signalObject, new Expression<Power_System.SignalreceiveGenReading>(new ConstructorCall(x, ClassUtils.getConstructorForArgTypes(Power_System.SignalreceiveGenReading.class), new Object[] {})));
                addDependency(_17_0_2_2_edc0357_1357846887415_968978_21310, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846886936_304274_20195, ClassUtils.getMethodForArgTypes("ObjectFlow<Power>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_2_edc0357_1357846886934_191509_20185Elaborations() {
                init_17_0_2_2_edc0357_1357846886934_191509_20185Dependencies();
            }
        }

        public class _17_0_2_2_edc0357_1357846886935_440735_20187 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846886935_440735_20187() {
                super();
                init_17_0_2_2_edc0357_1357846886935_440735_20187Members();
                init_17_0_2_2_edc0357_1357846886935_440735_20187Collections();
                init_17_0_2_2_edc0357_1357846886935_440735_20187Elaborations();
            }

            public _17_0_2_2_edc0357_1357846886935_440735_20187(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846886935_440735_20187Members();
                init_17_0_2_2_edc0357_1357846886935_440735_20187Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846886935_440735_20187Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter addToDecider__17_0_2_2_edc0357_1357846886934_191509_20185 = null;

            public BooleanParameter _17_0_2_2_edc0357_1358277417331_36014_38761_exists = null;

            public IntegerParameter myDeciderID_decider_17_0_2_2_edc0357_1358277417331_36014_38761 = null;

            public IntegerParameter myDeciderID_decider_17_0_2_2_edc0357_1357846886934_191509_20185 = null;

            public BooleanParameter addToDecider__17_0_2_2_edc0357_1358277417331_36014_38761 = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846886934_191509_20185_exists = null;

            public Parameter< Power > objectToPass = null;

            public BooleanParameter _17_0_2_2_edc0357_1358277197432_891611_38660_exists = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846886933_732644_20183_exists = null;

            public Dependency addToDecider__17_0_2_2_edc0357_1357846886934_191509_20185Dependency = null;

            public Dependency _17_0_2_2_edc0357_1358277417331_36014_38761_existsDependency = null;

            public Dependency myDeciderID_decider_17_0_2_2_edc0357_1358277417331_36014_38761Dependency = null;

            public Dependency myDeciderID_decider_17_0_2_2_edc0357_1357846886934_191509_20185Dependency = null;

            public Dependency addToDecider__17_0_2_2_edc0357_1358277417331_36014_38761Dependency = null;

            public Dependency durationDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846886934_191509_20185_existsDependency = null;

            public Dependency objectToPassDependency = null;

            public Dependency _17_0_2_2_edc0357_1358277197432_891611_38660_existsDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846886933_732644_20183_existsDependency = null;

            public Effect effect384 = null;

            public Parameter effect384Var = null;

            public Effect effect385 = null;

            public Parameter effect385Var = null;

            public Effect effect386 = null;

            public Parameter effect386Var = null;

            public Effect effect387 = null;

            public Parameter effect387Var = null;

            public Effect effect388 = null;

            public Parameter effect388Var = null;

            public Effect effect389 = null;

            public Parameter effect389Var = null;

            public ElaborationRule elaborationRule390 = null;

            public ElaborationRule elaborationRule391 = null;

            public ElaborationRule elaborationRule392 = null;

            public ElaborationRule elaborationRule393 = null;

            public void init_17_0_2_2_edc0357_1357846886935_440735_20187Members() {
                try {
                    if (addToDecider__17_0_2_2_edc0357_1357846886934_191509_20185 == null) addToDecider__17_0_2_2_edc0357_1357846886934_191509_20185 = new BooleanParameter("addToDecider__17_0_2_2_edc0357_1357846886934_191509_20185", (Boolean) false, this);
                    if (_17_0_2_2_edc0357_1358277417331_36014_38761_exists == null) _17_0_2_2_edc0357_1358277417331_36014_38761_exists = new BooleanParameter("_17_0_2_2_edc0357_1358277417331_36014_38761_exists", (Boolean) false, this);
                    if (myDeciderID_decider_17_0_2_2_edc0357_1358277417331_36014_38761 == null) myDeciderID_decider_17_0_2_2_edc0357_1358277417331_36014_38761 = new IntegerParameter("myDeciderID_decider_17_0_2_2_edc0357_1358277417331_36014_38761", (Integer) 1, this);
                    if (myDeciderID_decider_17_0_2_2_edc0357_1357846886934_191509_20185 == null) myDeciderID_decider_17_0_2_2_edc0357_1357846886934_191509_20185 = new IntegerParameter("myDeciderID_decider_17_0_2_2_edc0357_1357846886934_191509_20185", (Integer) 2, this);
                    if (addToDecider__17_0_2_2_edc0357_1358277417331_36014_38761 == null) addToDecider__17_0_2_2_edc0357_1358277417331_36014_38761 = new BooleanParameter("addToDecider__17_0_2_2_edc0357_1358277417331_36014_38761", (Boolean) false, this);
                    if (_17_0_2_2_edc0357_1357846886934_191509_20185_exists == null) _17_0_2_2_edc0357_1357846886934_191509_20185_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846886934_191509_20185_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new Parameter<Power>("objectToPass", null, (Power) null, this);
                    if (_17_0_2_2_edc0357_1358277197432_891611_38660_exists == null) _17_0_2_2_edc0357_1358277197432_891611_38660_exists = new BooleanParameter("_17_0_2_2_edc0357_1358277197432_891611_38660_exists", (Boolean) false, this);
                    if (_17_0_2_2_edc0357_1357846886933_732644_20183_exists == null) _17_0_2_2_edc0357_1357846886933_732644_20183_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846886933_732644_20183_exists", (Boolean) false, this);
                    Object effect384VarV = sig_17_0_2_2_edc0357_1357846886936_38505_20193;
                    effect384Var = new Parameter("effect384Var", null, null, this);
                    addDependency(effect384Var, new Expression(effect384VarV));
                    effect384 = new EffectFunction(new EffectFunction(effect384Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Power>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect385VarV = sig_17_0_2_2_edc0357_1357846886936_304274_20195;
                    effect385Var = new Parameter("effect385Var", null, null, this);
                    addDependency(effect385Var, new Expression(effect385VarV));
                    effect385 = new EffectFunction(new EffectFunction(effect385Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Power>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect386VarV = sig_17_0_2_2_edc0357_1358277216425_708226_38675;
                    effect386Var = new Parameter("effect386Var", null, null, this);
                    addDependency(effect386Var, new Expression(effect386VarV));
                    effect386 = new EffectFunction(new EffectFunction(effect386Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Power>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect387VarV = sig_17_0_2_2_edc0357_1358277523870_756643_38780;
                    effect387Var = new Parameter("effect387Var", null, null, this);
                    addDependency(effect387Var, new Expression(effect387VarV));
                    effect387 = new EffectFunction(new EffectFunction(effect387Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Power>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect388VarV = decider_17_0_2_2_edc0357_1357846886934_191509_20185;
                    effect388Var = new Parameter("effect388Var", null, null, this);
                    addDependency(effect388Var, new Expression(effect388VarV));
                    effect388 = new EffectFunction(new EffectFunction(effect388Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class, java.lang.Boolean.class), new Object[] { endTime, myDeciderID_decider_17_0_2_2_edc0357_1357846886934_191509_20185, addToDecider__17_0_2_2_edc0357_1357846886934_191509_20185 }));
                    Object effect389VarV = decider_17_0_2_2_edc0357_1358277417331_36014_38761;
                    effect389Var = new Parameter("effect389Var", null, null, this);
                    addDependency(effect389Var, new Expression(effect389VarV));
                    effect389 = new EffectFunction(new EffectFunction(effect389Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class, java.lang.Boolean.class), new Object[] { endTime, myDeciderID_decider_17_0_2_2_edc0357_1358277417331_36014_38761, addToDecider__17_0_2_2_edc0357_1358277417331_36014_38761 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846886935_440735_20187Collections() {
                parameters.add(addToDecider__17_0_2_2_edc0357_1357846886934_191509_20185);
                parameters.add(_17_0_2_2_edc0357_1358277417331_36014_38761_exists);
                parameters.add(myDeciderID_decider_17_0_2_2_edc0357_1358277417331_36014_38761);
                parameters.add(myDeciderID_decider_17_0_2_2_edc0357_1357846886934_191509_20185);
                parameters.add(addToDecider__17_0_2_2_edc0357_1358277417331_36014_38761);
                parameters.add(_17_0_2_2_edc0357_1357846886934_191509_20185_exists);
                parameters.add(objectToPass);
                parameters.add(_17_0_2_2_edc0357_1358277197432_891611_38660_exists);
                parameters.add(_17_0_2_2_edc0357_1357846886933_732644_20183_exists);
                Set<Effect> effectsForeffect384Var = new TreeSet<Effect>();
                effectsForeffect384Var.add(effect384);
                addEffects((Parameter<?>) effect384Var, effectsForeffect384Var);
                Set<Effect> effectsForeffect385Var = new TreeSet<Effect>();
                effectsForeffect385Var.add(effect385);
                addEffects((Parameter<?>) effect385Var, effectsForeffect385Var);
                Set<Effect> effectsForeffect386Var = new TreeSet<Effect>();
                effectsForeffect386Var.add(effect386);
                addEffects((Parameter<?>) effect386Var, effectsForeffect386Var);
                Set<Effect> effectsForeffect387Var = new TreeSet<Effect>();
                effectsForeffect387Var.add(effect387);
                addEffects((Parameter<?>) effect387Var, effectsForeffect387Var);
                Set<Effect> effectsForeffect388Var = new TreeSet<Effect>();
                effectsForeffect388Var.add(effect388);
                addEffects((Parameter<?>) effect388Var, effectsForeffect388Var);
                Set<Effect> effectsForeffect389Var = new TreeSet<Effect>();
                effectsForeffect389Var.add(effect389);
                addEffects((Parameter<?>) effect389Var, effectsForeffect389Var);
            }

            public void init_17_0_2_2_edc0357_1357846886935_440735_20187Dependencies() {
                addDependency(addToDecider__17_0_2_2_edc0357_1357846886934_191509_20185, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886932_836588_20182_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886932_836588_20182_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(_17_0_2_2_edc0357_1358277417331_36014_38761_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886932_836588_20182_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886932_836588_20182_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1358277417331_36014_38761, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1358277417331_36014_38761, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1358277417331_36014_38761, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_2_edc0357_1358277417331_36014_38761)))))))));
                addDependency(myDeciderID_decider_17_0_2_2_edc0357_1358277417331_36014_38761, new Expression<Integer>(1));
                addDependency(myDeciderID_decider_17_0_2_2_edc0357_1357846886934_191509_20185, new Expression<Integer>(2));
                addDependency(addToDecider__17_0_2_2_edc0357_1358277417331_36014_38761, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886932_836588_20182_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886932_836588_20182_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(_17_0_2_2_edc0357_1357846886934_191509_20185_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886932_836588_20182_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886932_836588_20182_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846886934_191509_20185, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846886934_191509_20185, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846886934_191509_20185, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_2_edc0357_1357846886934_191509_20185)))))))));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846886936_848239_20192, ClassUtils.getMethodForArgTypes("ObjectFlow<Power>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_2_edc0357_1358277197432_891611_38660_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886932_836588_20182_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886932_836588_20182_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(_17_0_2_2_edc0357_1357846886933_732644_20183_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886932_836588_20182_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886932_836588_20182_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
            }

            public void init_17_0_2_2_edc0357_1357846886935_440735_20187Elaborations() {
                init_17_0_2_2_edc0357_1357846886935_440735_20187Dependencies();
                Expression<?>[] arguments390 = new Expression<?>[1];
                arguments390[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition390 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846886934_191509_20185_exists);
                elaborationRule390 = addElaborationRule(condition390, _17_0_2_2_edc0357_1357846886580_140004_19866.this, Power._17_0_2_2_edc0357_1357846886580_140004_19866._17_0_2_2_edc0357_1357846886934_191509_20185.class, "PGen_send_gen_SendSignalAction_generate", arguments390);
                Expression<?>[] arguments391 = new Expression<?>[1];
                arguments391[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition391 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846886933_732644_20183_exists);
                elaborationRule391 = addElaborationRule(condition391, _17_0_2_2_edc0357_1357846886580_140004_19866.this, Power._17_0_2_2_edc0357_1357846886580_140004_19866._17_0_2_2_edc0357_1357846886933_732644_20183.class, "PGen_read_profile_ReadStructuralFeatureAction_generate", arguments391);
                Expression<?>[] arguments392 = new Expression<?>[1];
                arguments392[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition392 = new Expression<Boolean>(_17_0_2_2_edc0357_1358277197432_891611_38660_exists);
                elaborationRule392 = addElaborationRule(condition392, _17_0_2_2_edc0357_1357846886580_140004_19866.this, Power._17_0_2_2_edc0357_1357846886580_140004_19866._17_0_2_2_edc0357_1358277197432_891611_38660.class, "PGen_read_augment_ReadStructuralFeatureAction_generate", arguments392);
                Expression<?>[] arguments393 = new Expression<?>[1];
                arguments393[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition393 = new Expression<Boolean>(_17_0_2_2_edc0357_1358277417331_36014_38761_exists);
                elaborationRule393 = addElaborationRule(condition393, _17_0_2_2_edc0357_1357846886580_140004_19866.this, Power._17_0_2_2_edc0357_1357846886580_140004_19866._17_0_2_2_edc0357_1358277417331_36014_38761.class, "setGeneration_AddStructuralFeatureValueAction_generate", arguments393);
            }
        }

        public class _17_0_2_2_edc0357_1358277197432_891611_38660 extends DurativeEvent {

            public _17_0_2_2_edc0357_1358277197432_891611_38660() {
                super();
                init_17_0_2_2_edc0357_1358277197432_891611_38660Members();
                init_17_0_2_2_edc0357_1358277197432_891611_38660Collections();
                init_17_0_2_2_edc0357_1358277197432_891611_38660Elaborations();
            }

            public _17_0_2_2_edc0357_1358277197432_891611_38660(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1358277197432_891611_38660Members();
                init_17_0_2_2_edc0357_1358277197432_891611_38660Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1358277197432_891611_38660Elaborations();
                fixTimeDependencies();
            }

            public Parameter< Power > _17_0_2_2_edc0357_1358277197435_126141_38662 = null;

            public IntegerParameter myDeciderID_decider_17_0_2_2_edc0357_1358277276270_502536_38688 = null;

            public IntegerParameter _17_0_2_2_edc0357_1358277197434_811880_38661 = null;

            public BooleanParameter addToDecider__17_0_2_2_edc0357_1358277276270_502536_38688 = null;

            public BooleanParameter _17_0_2_2_edc0357_1358277276270_502536_38688_exists = null;

            public Dependency _17_0_2_2_edc0357_1358277197435_126141_38662Dependency = null;

            public Dependency _17_0_2_2_edc0357_1358277197434_811880_38661Dependency = null;

            public Dependency durationDependency = null;

            public Dependency myDeciderID_decider_17_0_2_2_edc0357_1358277276270_502536_38688Dependency = null;

            public Dependency _17_0_2_2_edc0357_1358277276270_502536_38688_existsDependency = null;

            public Dependency addToDecider__17_0_2_2_edc0357_1358277276270_502536_38688Dependency = null;

            public Effect effect394 = null;

            public Parameter effect394Var = null;

            public Effect effect395 = null;

            public Parameter effect395Var = null;

            public ElaborationRule elaborationRule396 = null;

            public void init_17_0_2_2_edc0357_1358277197432_891611_38660Members() {
                try {
                    if (_17_0_2_2_edc0357_1358277197435_126141_38662 == null) _17_0_2_2_edc0357_1358277197435_126141_38662 = new Parameter<Power>("_17_0_2_2_edc0357_1358277197435_126141_38662", null, (Power) null, this);
                    if (myDeciderID_decider_17_0_2_2_edc0357_1358277276270_502536_38688 == null) myDeciderID_decider_17_0_2_2_edc0357_1358277276270_502536_38688 = new IntegerParameter("myDeciderID_decider_17_0_2_2_edc0357_1358277276270_502536_38688", (Integer) 2, this);
                    if (_17_0_2_2_edc0357_1358277197434_811880_38661 == null) _17_0_2_2_edc0357_1358277197434_811880_38661 = new IntegerParameter("_17_0_2_2_edc0357_1358277197434_811880_38661", (Integer) null, this);
                    if (addToDecider__17_0_2_2_edc0357_1358277276270_502536_38688 == null) addToDecider__17_0_2_2_edc0357_1358277276270_502536_38688 = new BooleanParameter("addToDecider__17_0_2_2_edc0357_1358277276270_502536_38688", (Boolean) false, this);
                    if (_17_0_2_2_edc0357_1358277276270_502536_38688_exists == null) _17_0_2_2_edc0357_1358277276270_502536_38688_exists = new BooleanParameter("_17_0_2_2_edc0357_1358277276270_502536_38688_exists", (Boolean) false, this);
                    Object effect394VarV = sig_17_0_2_2_edc0357_1358277383729_343862_38744;
                    effect394Var = new Parameter("effect394Var", null, null, this);
                    addDependency(effect394Var, new Expression(effect394VarV));
                    effect394 = new EffectFunction(new EffectFunction(effect394Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { _17_0_2_2_edc0357_1358277197434_811880_38661, endTime }));
                    Object effect395VarV = decider_17_0_2_2_edc0357_1358277276270_502536_38688;
                    effect395Var = new Parameter("effect395Var", null, null, this);
                    addDependency(effect395Var, new Expression(effect395VarV));
                    effect395 = new EffectFunction(new EffectFunction(effect395Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class, java.lang.Boolean.class), new Object[] { endTime, myDeciderID_decider_17_0_2_2_edc0357_1358277276270_502536_38688, addToDecider__17_0_2_2_edc0357_1358277276270_502536_38688 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1358277197432_891611_38660Collections() {
                parameters.add(_17_0_2_2_edc0357_1358277197435_126141_38662);
                parameters.add(myDeciderID_decider_17_0_2_2_edc0357_1358277276270_502536_38688);
                parameters.add(_17_0_2_2_edc0357_1358277197434_811880_38661);
                parameters.add(addToDecider__17_0_2_2_edc0357_1358277276270_502536_38688);
                parameters.add(_17_0_2_2_edc0357_1358277276270_502536_38688_exists);
                Set<Effect> effectsForeffect394Var = new TreeSet<Effect>();
                effectsForeffect394Var.add(effect394);
                addEffects((Parameter<?>) effect394Var, effectsForeffect394Var);
                Set<Effect> effectsForeffect395Var = new TreeSet<Effect>();
                effectsForeffect395Var.add(effect395);
                addEffects((Parameter<?>) effect395Var, effectsForeffect395Var);
            }

            public void init_17_0_2_2_edc0357_1358277197432_891611_38660Dependencies() {
                addDependency(_17_0_2_2_edc0357_1358277197435_126141_38662, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1358277216425_708226_38675, ClassUtils.getMethodForArgTypes("ObjectFlow<Power>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_2_edc0357_1358277197434_811880_38661, new Expression(new EffectFunction(null, ClassUtils.getMethodForArgTypes("TimeVaryingPlottableMap<Integer>", "PowerSystem3", "getValue", java.lang.Integer.class), new Object[] { new Expression<Integer>(startTime) }, new FunctionCall(_17_0_2_2_edc0357_1358277197435_126141_38662, Parameter.class, "getMember", new Object[] { "powerAugmentation__17_0_2_2_edc0357_1358276774925_778819_38488" }))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(myDeciderID_decider_17_0_2_2_edc0357_1358277276270_502536_38688, new Expression<Integer>(2));
                addDependency(_17_0_2_2_edc0357_1358277276270_502536_38688_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886932_836588_20182_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886932_836588_20182_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1358277276270_502536_38688, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1358277276270_502536_38688, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1358277276270_502536_38688, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_2_edc0357_1358277276270_502536_38688)))))))));
                addDependency(addToDecider__17_0_2_2_edc0357_1358277276270_502536_38688, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886932_836588_20182_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886932_836588_20182_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
            }

            public void init_17_0_2_2_edc0357_1358277197432_891611_38660Elaborations() {
                init_17_0_2_2_edc0357_1358277197432_891611_38660Dependencies();
                Expression<?>[] arguments396 = new Expression<?>[1];
                arguments396[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition396 = new Expression<Boolean>(_17_0_2_2_edc0357_1358277276270_502536_38688_exists);
                elaborationRule396 = addElaborationRule(condition396, _17_0_2_2_edc0357_1357846886580_140004_19866.this, Power._17_0_2_2_edc0357_1357846886580_140004_19866._17_0_2_2_edc0357_1358277276270_502536_38688.class, "_CallBehaviorAction_generate", arguments396);
            }
        }

        public class _17_0_2_2_edc0357_1358277276270_502536_38688 extends DurativeEvent {

            public _17_0_2_2_edc0357_1358277276270_502536_38688() {
                super();
                init_17_0_2_2_edc0357_1358277276270_502536_38688Members();
                init_17_0_2_2_edc0357_1358277276270_502536_38688Collections();
                init_17_0_2_2_edc0357_1358277276270_502536_38688Elaborations();
            }

            public _17_0_2_2_edc0357_1358277276270_502536_38688(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1358277276270_502536_38688Members();
                init_17_0_2_2_edc0357_1358277276270_502536_38688Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1358277276270_502536_38688Elaborations();
                fixTimeDependencies();
            }

            public IntegerParameter _17_0_2_2_edc0357_1358277276285_236939_38698 = null;

            public IntegerParameter total = null;

            public IntegerParameter aug = null;

            public IntegerParameter _17_0_2_2_edc0357_1358277337107_662020_38725 = null;

            public IntegerParameter _17_0_2_2_edc0357_1358277313640_95736_38710 = null;

            public BooleanParameter _17_0_2_2_edc0357_1358277398627_54096_38751_exists = null;

            public IntegerParameter gen = null;

            public Dependency _17_0_2_2_edc0357_1358277276285_236939_38698Dependency = null;

            public Dependency totalDependency = null;

            public Dependency _17_0_2_2_edc0357_1358277337107_662020_38725Dependency = null;

            public Dependency augDependency = null;

            public Dependency _17_0_2_2_edc0357_1358277313640_95736_38710Dependency = null;

            public Dependency _17_0_2_2_edc0357_1358277398627_54096_38751_existsDependency = null;

            public Dependency durationDependency = null;

            public Dependency genDependency = null;

            public Effect effect397 = null;

            public Parameter effect397Var = null;

            public ElaborationRule elaborationRule398 = null;

            public void init_17_0_2_2_edc0357_1358277276270_502536_38688Members() {
                try {
                    if (_17_0_2_2_edc0357_1358277276285_236939_38698 == null) _17_0_2_2_edc0357_1358277276285_236939_38698 = new IntegerParameter("_17_0_2_2_edc0357_1358277276285_236939_38698", (Integer) null, this);
                    if (total == null) total = new IntegerParameter("total", (Integer) null, this);
                    if (aug == null) aug = new IntegerParameter("aug", (Integer) null, this);
                    if (_17_0_2_2_edc0357_1358277337107_662020_38725 == null) _17_0_2_2_edc0357_1358277337107_662020_38725 = new IntegerParameter("_17_0_2_2_edc0357_1358277337107_662020_38725", (Integer) null, this);
                    if (_17_0_2_2_edc0357_1358277313640_95736_38710 == null) _17_0_2_2_edc0357_1358277313640_95736_38710 = new IntegerParameter("_17_0_2_2_edc0357_1358277313640_95736_38710", (Integer) null, this);
                    if (_17_0_2_2_edc0357_1358277398627_54096_38751_exists == null) _17_0_2_2_edc0357_1358277398627_54096_38751_exists = new BooleanParameter("_17_0_2_2_edc0357_1358277398627_54096_38751_exists", (Boolean) false, this);
                    if (gen == null) gen = new IntegerParameter("gen", (Integer) null, this);
                    Object effect397VarV = sig_17_0_2_2_edc0357_1358277399765_397589_38755;
                    effect397Var = new Parameter("effect397Var", null, null, this);
                    addDependency(effect397Var, new Expression(effect397VarV));
                    effect397 = new EffectFunction(new EffectFunction(effect397Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { _17_0_2_2_edc0357_1358277337107_662020_38725, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1358277276270_502536_38688Collections() {
                parameters.add(_17_0_2_2_edc0357_1358277276285_236939_38698);
                parameters.add(total);
                parameters.add(aug);
                parameters.add(_17_0_2_2_edc0357_1358277337107_662020_38725);
                parameters.add(_17_0_2_2_edc0357_1358277313640_95736_38710);
                parameters.add(_17_0_2_2_edc0357_1358277398627_54096_38751_exists);
                parameters.add(gen);
                Set<Effect> effectsForeffect397Var = new TreeSet<Effect>();
                effectsForeffect397Var.add(effect397);
                addEffects((Parameter<?>) effect397Var, effectsForeffect397Var);
            }

            public void init_17_0_2_2_edc0357_1358277276270_502536_38688Dependencies() {
                addDependency(_17_0_2_2_edc0357_1358277276285_236939_38698, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1358277380386_823740_38739, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(total, new Expression<Integer>(new Functions.Plus(new Expression<Integer>(gen), new Expression<Integer>(aug))));
                addDependency(_17_0_2_2_edc0357_1358277337107_662020_38725, new Expression<Integer>(total));
                addDependency(aug, new Expression<Integer>(_17_0_2_2_edc0357_1358277313640_95736_38710));
                addDependency(_17_0_2_2_edc0357_1358277313640_95736_38710, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1358277383729_343862_38744, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_2_edc0357_1358277398627_54096_38751_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886932_836588_20182_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886932_836588_20182_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(gen, new Expression<Integer>(_17_0_2_2_edc0357_1358277276285_236939_38698));
            }

            public void init_17_0_2_2_edc0357_1358277276270_502536_38688Elaborations() {
                init_17_0_2_2_edc0357_1358277276270_502536_38688Dependencies();
                Expression<?>[] arguments398 = new Expression<?>[1];
                arguments398[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition398 = new Expression<Boolean>(_17_0_2_2_edc0357_1358277398627_54096_38751_exists);
                elaborationRule398 = addElaborationRule(condition398, _17_0_2_2_edc0357_1357846886580_140004_19866.this, Power._17_0_2_2_edc0357_1357846886580_140004_19866._17_0_2_2_edc0357_1358277398627_54096_38751.class, "_ForkNode_generate", arguments398);
            }
        }

        public class _17_0_2_2_edc0357_1358277398627_54096_38751 extends DurativeEvent {

            public _17_0_2_2_edc0357_1358277398627_54096_38751() {
                super();
                init_17_0_2_2_edc0357_1358277398627_54096_38751Members();
                init_17_0_2_2_edc0357_1358277398627_54096_38751Collections();
                init_17_0_2_2_edc0357_1358277398627_54096_38751Elaborations();
            }

            public _17_0_2_2_edc0357_1358277398627_54096_38751(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1358277398627_54096_38751Members();
                init_17_0_2_2_edc0357_1358277398627_54096_38751Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1358277398627_54096_38751Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter addToDecider__17_0_2_2_edc0357_1357846886934_191509_20185 = null;

            public BooleanParameter _17_0_2_2_edc0357_1358277417331_36014_38761_exists = null;

            public IntegerParameter myDeciderID_decider_17_0_2_2_edc0357_1358277417331_36014_38761 = null;

            public IntegerParameter myDeciderID_decider_17_0_2_2_edc0357_1357846886934_191509_20185 = null;

            public BooleanParameter addToDecider__17_0_2_2_edc0357_1358277417331_36014_38761 = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846886934_191509_20185_exists = null;

            public IntegerParameter objectToPass = null;

            public Dependency addToDecider__17_0_2_2_edc0357_1357846886934_191509_20185Dependency = null;

            public Dependency _17_0_2_2_edc0357_1358277417331_36014_38761_existsDependency = null;

            public Dependency myDeciderID_decider_17_0_2_2_edc0357_1358277417331_36014_38761Dependency = null;

            public Dependency myDeciderID_decider_17_0_2_2_edc0357_1357846886934_191509_20185Dependency = null;

            public Dependency addToDecider__17_0_2_2_edc0357_1358277417331_36014_38761Dependency = null;

            public Dependency durationDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846886934_191509_20185_existsDependency = null;

            public Dependency objectToPassDependency = null;

            public Effect effect399 = null;

            public Parameter effect399Var = null;

            public Effect effect400 = null;

            public Parameter effect400Var = null;

            public Effect effect401 = null;

            public Parameter effect401Var = null;

            public Effect effect402 = null;

            public Parameter effect402Var = null;

            public ElaborationRule elaborationRule403 = null;

            public ElaborationRule elaborationRule404 = null;

            public void init_17_0_2_2_edc0357_1358277398627_54096_38751Members() {
                try {
                    if (addToDecider__17_0_2_2_edc0357_1357846886934_191509_20185 == null) addToDecider__17_0_2_2_edc0357_1357846886934_191509_20185 = new BooleanParameter("addToDecider__17_0_2_2_edc0357_1357846886934_191509_20185", (Boolean) false, this);
                    if (_17_0_2_2_edc0357_1358277417331_36014_38761_exists == null) _17_0_2_2_edc0357_1358277417331_36014_38761_exists = new BooleanParameter("_17_0_2_2_edc0357_1358277417331_36014_38761_exists", (Boolean) false, this);
                    if (myDeciderID_decider_17_0_2_2_edc0357_1358277417331_36014_38761 == null) myDeciderID_decider_17_0_2_2_edc0357_1358277417331_36014_38761 = new IntegerParameter("myDeciderID_decider_17_0_2_2_edc0357_1358277417331_36014_38761", (Integer) 2, this);
                    if (myDeciderID_decider_17_0_2_2_edc0357_1357846886934_191509_20185 == null) myDeciderID_decider_17_0_2_2_edc0357_1357846886934_191509_20185 = new IntegerParameter("myDeciderID_decider_17_0_2_2_edc0357_1357846886934_191509_20185", (Integer) 1, this);
                    if (addToDecider__17_0_2_2_edc0357_1358277417331_36014_38761 == null) addToDecider__17_0_2_2_edc0357_1358277417331_36014_38761 = new BooleanParameter("addToDecider__17_0_2_2_edc0357_1358277417331_36014_38761", (Boolean) false, this);
                    if (_17_0_2_2_edc0357_1357846886934_191509_20185_exists == null) _17_0_2_2_edc0357_1357846886934_191509_20185_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846886934_191509_20185_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new IntegerParameter("objectToPass", (Integer) null, this);
                    Object effect399VarV = sig_17_0_2_2_edc0357_1357846886936_674003_20191;
                    effect399Var = new Parameter("effect399Var", null, null, this);
                    addDependency(effect399Var, new Expression(effect399VarV));
                    effect399 = new EffectFunction(new EffectFunction(effect399Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect400VarV = sig_17_0_2_2_edc0357_1358277531744_78108_38785;
                    effect400Var = new Parameter("effect400Var", null, null, this);
                    addDependency(effect400Var, new Expression(effect400VarV));
                    effect400 = new EffectFunction(new EffectFunction(effect400Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect401VarV = decider_17_0_2_2_edc0357_1357846886934_191509_20185;
                    effect401Var = new Parameter("effect401Var", null, null, this);
                    addDependency(effect401Var, new Expression(effect401VarV));
                    effect401 = new EffectFunction(new EffectFunction(effect401Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class, java.lang.Boolean.class), new Object[] { endTime, myDeciderID_decider_17_0_2_2_edc0357_1357846886934_191509_20185, addToDecider__17_0_2_2_edc0357_1357846886934_191509_20185 }));
                    Object effect402VarV = decider_17_0_2_2_edc0357_1358277417331_36014_38761;
                    effect402Var = new Parameter("effect402Var", null, null, this);
                    addDependency(effect402Var, new Expression(effect402VarV));
                    effect402 = new EffectFunction(new EffectFunction(effect402Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class, java.lang.Boolean.class), new Object[] { endTime, myDeciderID_decider_17_0_2_2_edc0357_1358277417331_36014_38761, addToDecider__17_0_2_2_edc0357_1358277417331_36014_38761 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1358277398627_54096_38751Collections() {
                parameters.add(addToDecider__17_0_2_2_edc0357_1357846886934_191509_20185);
                parameters.add(_17_0_2_2_edc0357_1358277417331_36014_38761_exists);
                parameters.add(myDeciderID_decider_17_0_2_2_edc0357_1358277417331_36014_38761);
                parameters.add(myDeciderID_decider_17_0_2_2_edc0357_1357846886934_191509_20185);
                parameters.add(addToDecider__17_0_2_2_edc0357_1358277417331_36014_38761);
                parameters.add(_17_0_2_2_edc0357_1357846886934_191509_20185_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect399Var = new TreeSet<Effect>();
                effectsForeffect399Var.add(effect399);
                addEffects((Parameter<?>) effect399Var, effectsForeffect399Var);
                Set<Effect> effectsForeffect400Var = new TreeSet<Effect>();
                effectsForeffect400Var.add(effect400);
                addEffects((Parameter<?>) effect400Var, effectsForeffect400Var);
                Set<Effect> effectsForeffect401Var = new TreeSet<Effect>();
                effectsForeffect401Var.add(effect401);
                addEffects((Parameter<?>) effect401Var, effectsForeffect401Var);
                Set<Effect> effectsForeffect402Var = new TreeSet<Effect>();
                effectsForeffect402Var.add(effect402);
                addEffects((Parameter<?>) effect402Var, effectsForeffect402Var);
            }

            public void init_17_0_2_2_edc0357_1358277398627_54096_38751Dependencies() {
                addDependency(addToDecider__17_0_2_2_edc0357_1357846886934_191509_20185, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886932_836588_20182_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886932_836588_20182_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(_17_0_2_2_edc0357_1358277417331_36014_38761_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886932_836588_20182_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886932_836588_20182_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1358277417331_36014_38761, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1358277417331_36014_38761, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1358277417331_36014_38761, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_2_edc0357_1358277417331_36014_38761)))))))));
                addDependency(myDeciderID_decider_17_0_2_2_edc0357_1358277417331_36014_38761, new Expression<Integer>(2));
                addDependency(myDeciderID_decider_17_0_2_2_edc0357_1357846886934_191509_20185, new Expression<Integer>(1));
                addDependency(addToDecider__17_0_2_2_edc0357_1358277417331_36014_38761, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886932_836588_20182_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886932_836588_20182_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(_17_0_2_2_edc0357_1357846886934_191509_20185_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886932_836588_20182_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886932_836588_20182_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846886934_191509_20185, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846886934_191509_20185, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846886934_191509_20185, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_2_edc0357_1357846886934_191509_20185)))))))));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1358277399765_397589_38755, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_2_edc0357_1358277398627_54096_38751Elaborations() {
                init_17_0_2_2_edc0357_1358277398627_54096_38751Dependencies();
                Expression<?>[] arguments403 = new Expression<?>[1];
                arguments403[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition403 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846886934_191509_20185_exists);
                elaborationRule403 = addElaborationRule(condition403, _17_0_2_2_edc0357_1357846886580_140004_19866.this, Power._17_0_2_2_edc0357_1357846886580_140004_19866._17_0_2_2_edc0357_1357846886934_191509_20185.class, "PGen_send_gen_SendSignalAction_generate", arguments403);
                Expression<?>[] arguments404 = new Expression<?>[1];
                arguments404[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition404 = new Expression<Boolean>(_17_0_2_2_edc0357_1358277417331_36014_38761_exists);
                elaborationRule404 = addElaborationRule(condition404, _17_0_2_2_edc0357_1357846886580_140004_19866.this, Power._17_0_2_2_edc0357_1357846886580_140004_19866._17_0_2_2_edc0357_1358277417331_36014_38761.class, "setGeneration_AddStructuralFeatureValueAction_generate", arguments404);
            }
        }

        public class _17_0_2_2_edc0357_1358277417331_36014_38761 extends DurativeEvent {

            public _17_0_2_2_edc0357_1358277417331_36014_38761() {
                super();
                init_17_0_2_2_edc0357_1358277417331_36014_38761Members();
                init_17_0_2_2_edc0357_1358277417331_36014_38761Collections();
                init_17_0_2_2_edc0357_1358277417331_36014_38761Elaborations();
            }

            public _17_0_2_2_edc0357_1358277417331_36014_38761(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1358277417331_36014_38761Members();
                init_17_0_2_2_edc0357_1358277417331_36014_38761Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1358277417331_36014_38761Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter addToDecider__17_0_2_2_edc0357_1357846886934_191509_20185 = null;

            public IntegerParameter myDeciderID_decider_17_0_2_2_edc0357_1357846886934_191509_20185 = null;

            public Parameter< Power > _17_0_2_2_edc0357_1358277428182_784819_38772 = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846886934_191509_20185_exists = null;

            public BooleanParameter objectToPass = null;

            public IntegerParameter _17_0_2_2_edc0357_1358277444081_209036_38773 = null;

            public Dependency addToDecider__17_0_2_2_edc0357_1357846886934_191509_20185Dependency = null;

            public Dependency myDeciderID_decider_17_0_2_2_edc0357_1357846886934_191509_20185Dependency = null;

            public Dependency _17_0_2_2_edc0357_1358277428182_784819_38772Dependency = null;

            public Dependency durationDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846886934_191509_20185_existsDependency = null;

            public Dependency objectToPassDependency = null;

            public Dependency _17_0_2_2_edc0357_1358277444081_209036_38773Dependency = null;

            public Effect effect405 = null;

            public Parameter effect405Var = null;

            public Effect effect406 = null;

            public Parameter effect406Var = null;

            public Effect effect407 = null;

            public Parameter effect407Var = null;

            public ElaborationRule elaborationRule408 = null;

            public void init_17_0_2_2_edc0357_1358277417331_36014_38761Members() {
                try {
                    if (addToDecider__17_0_2_2_edc0357_1357846886934_191509_20185 == null) addToDecider__17_0_2_2_edc0357_1357846886934_191509_20185 = new BooleanParameter("addToDecider__17_0_2_2_edc0357_1357846886934_191509_20185", (Boolean) false, this);
                    if (myDeciderID_decider_17_0_2_2_edc0357_1357846886934_191509_20185 == null) myDeciderID_decider_17_0_2_2_edc0357_1357846886934_191509_20185 = new IntegerParameter("myDeciderID_decider_17_0_2_2_edc0357_1357846886934_191509_20185", (Integer) 3, this);
                    if (_17_0_2_2_edc0357_1358277428182_784819_38772 == null) _17_0_2_2_edc0357_1358277428182_784819_38772 = new Parameter<Power>("_17_0_2_2_edc0357_1358277428182_784819_38772", null, (Power) null, this);
                    if (_17_0_2_2_edc0357_1357846886934_191509_20185_exists == null) _17_0_2_2_edc0357_1357846886934_191509_20185_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846886934_191509_20185_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (_17_0_2_2_edc0357_1358277444081_209036_38773 == null) _17_0_2_2_edc0357_1358277444081_209036_38773 = new IntegerParameter("_17_0_2_2_edc0357_1358277444081_209036_38773", (Integer) null, this);
                    Object effect405VarV = sig_17_0_2_2_edc0357_1358277571267_916379_38806;
                    effect405Var = new Parameter("effect405Var", null, null, this);
                    addDependency(effect405Var, new Expression(effect405VarV));
                    effect405 = new EffectFunction(new EffectFunction(effect405Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect406VarV = decider_17_0_2_2_edc0357_1357846886934_191509_20185;
                    effect406Var = new Parameter("effect406Var", null, null, this);
                    addDependency(effect406Var, new Expression(effect406VarV));
                    effect406 = new EffectFunction(new EffectFunction(effect406Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class, java.lang.Boolean.class), new Object[] { endTime, myDeciderID_decider_17_0_2_2_edc0357_1357846886934_191509_20185, addToDecider__17_0_2_2_edc0357_1357846886934_191509_20185 }));
                    Object effect407VarV = power__17_0_2_2_edc0357_1357846886582_295221_19868;
                    effect407Var = new Parameter("effect407Var", null, null, this);
                    addDependency(effect407Var, new Expression(effect407VarV));
                    effect407 = new EffectFunction(new EffectFunction(effect407Var, ClassUtils.getMethodForArgTypes("TimeVaryingPlottableMap<Integer>", "PowerSystem3", "setValue", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { startTime, _17_0_2_2_edc0357_1358277444081_209036_38773 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1358277417331_36014_38761Collections() {
                parameters.add(addToDecider__17_0_2_2_edc0357_1357846886934_191509_20185);
                parameters.add(myDeciderID_decider_17_0_2_2_edc0357_1357846886934_191509_20185);
                parameters.add(_17_0_2_2_edc0357_1358277428182_784819_38772);
                parameters.add(_17_0_2_2_edc0357_1357846886934_191509_20185_exists);
                parameters.add(objectToPass);
                parameters.add(_17_0_2_2_edc0357_1358277444081_209036_38773);
                Set<Effect> effectsForeffect405Var = new TreeSet<Effect>();
                effectsForeffect405Var.add(effect405);
                addEffects((Parameter<?>) effect405Var, effectsForeffect405Var);
                Set<Effect> effectsForeffect406Var = new TreeSet<Effect>();
                effectsForeffect406Var.add(effect406);
                addEffects((Parameter<?>) effect406Var, effectsForeffect406Var);
                Set<Effect> effectsForeffect407Var = new TreeSet<Effect>();
                effectsForeffect407Var.add(effect407);
                addEffects((Parameter<?>) effect407Var, effectsForeffect407Var);
            }

            public void init_17_0_2_2_edc0357_1358277417331_36014_38761Dependencies() {
                addDependency(addToDecider__17_0_2_2_edc0357_1357846886934_191509_20185, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886932_836588_20182_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886932_836588_20182_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(myDeciderID_decider_17_0_2_2_edc0357_1357846886934_191509_20185, new Expression<Integer>(3));
                addDependency(_17_0_2_2_edc0357_1358277428182_784819_38772, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1358277523870_756643_38780, ClassUtils.getMethodForArgTypes("ObjectFlow<Power>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(_17_0_2_2_edc0357_1357846886934_191509_20185_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886932_836588_20182_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886932_836588_20182_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846886934_191509_20185, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846886934_191509_20185, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846886934_191509_20185, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_2_edc0357_1357846886934_191509_20185)))))))));
                addDependency(objectToPass, new Expression<Boolean>(true));
                addDependency(_17_0_2_2_edc0357_1358277444081_209036_38773, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1358277531744_78108_38785, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_2_edc0357_1358277417331_36014_38761Elaborations() {
                init_17_0_2_2_edc0357_1358277417331_36014_38761Dependencies();
                Expression<?>[] arguments408 = new Expression<?>[1];
                arguments408[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition408 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846886934_191509_20185_exists);
                elaborationRule408 = addElaborationRule(condition408, _17_0_2_2_edc0357_1357846886580_140004_19866.this, Power._17_0_2_2_edc0357_1357846886580_140004_19866._17_0_2_2_edc0357_1357846886934_191509_20185.class, "PGen_send_gen_SendSignalAction_generate", arguments408);
            }
        }

        public _17_0_2_2_edc0357_1357846886580_140004_19866(Expression<Integer> startTime, Expression<DurativeEvent> caller) {
            super();
            init_17_0_2_2_edc0357_1357846886580_140004_19866Members();
            init_17_0_2_2_edc0357_1357846886580_140004_19866Collections();
            addDependency(this.startTime, startTime);
            addDependency(this.caller, caller);
            init_17_0_2_2_edc0357_1357846886580_140004_19866Elaborations();
            fixTimeDependencies();
        }

        public Parameter< ObjectFlow<Integer> > sig_17_0_2_2_edc0357_1358277380386_823740_38739 = null;

        public Parameter< ObjectFlow<Power> > sig_17_0_2_2_edc0357_1358277216425_708226_38675 = null;

        public Parameter< ObjectFlow<Power> > sig_17_0_2_2_edc0357_1357846886936_304274_20195 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_2_2_edc0357_1357846886936_674003_20191 = null;

        public Parameter< DurativeEvent > caller = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_2_edc0357_1358277576291_904799_38811 = null;

        public BooleanParameter _17_0_2_2_edc0357_1357846886932_836588_20182_exists = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_2_2_edc0357_1358277383729_343862_38744 = null;

        public Parameter< TimeVaryingList<Integer> > decider_17_0_2_2_edc0357_1357846886934_191509_20185 = null;

        public Parameter< ObjectFlow<Power> > sig_17_0_2_2_edc0357_1357846886936_38505_20193 = null;

        public IntegerParameter finalNode_startTime = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_2_edc0357_1357846886936_298417_20189 = null;

        public Parameter< TimeVaryingList<Integer> > decider_17_0_2_2_edc0357_1358277417331_36014_38761 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_2_2_edc0357_1358277338307_579025_38726 = null;

        public Parameter< ObjectFlow<Power> > sig_17_0_2_2_edc0357_1358277523870_756643_38780 = null;

        public IntegerParameter finalNode_endTime = null;

        public Parameter< ObjectFlow<Power> > sig_17_0_2_2_edc0357_1357846886936_848239_20192 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_2_edc0357_1358277571267_916379_38806 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_2_2_edc0357_1358277531744_78108_38785 = null;

        public Parameter< TimeVaryingList<Integer> > decider_17_0_2_2_edc0357_1358277276270_502536_38688 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_2_2_edc0357_1358277399765_397589_38755 = null;

        public Dependency caller_endTimeDependency = null;

        public Dependency _17_0_2_2_edc0357_1357846886932_836588_20182_existsDependency = null;

        public Dependency endTimeDependency = null;

        public ElaborationRule elaborationRule372 = null;

        public ElaborationRule elaborationRule373 = null;

        public void init_17_0_2_2_edc0357_1357846886580_140004_19866Members() {
            try {
                if (sig_17_0_2_2_edc0357_1358277380386_823740_38739 == null) sig_17_0_2_2_edc0357_1358277380386_823740_38739 = new Parameter<ObjectFlow<Integer>>("sig_17_0_2_2_edc0357_1358277380386_823740_38739", null, (ObjectFlow<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1358277380386_823740_38739" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1358277216425_708226_38675 == null) sig_17_0_2_2_edc0357_1358277216425_708226_38675 = new Parameter<ObjectFlow<Power>>("sig_17_0_2_2_edc0357_1358277216425_708226_38675", null, (ObjectFlow<Power>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1358277216425_708226_38675" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846886936_304274_20195 == null) sig_17_0_2_2_edc0357_1357846886936_304274_20195 = new Parameter<ObjectFlow<Power>>("sig_17_0_2_2_edc0357_1357846886936_304274_20195", null, (ObjectFlow<Power>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846886936_304274_20195" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846886936_674003_20191 == null) sig_17_0_2_2_edc0357_1357846886936_674003_20191 = new Parameter<ObjectFlow<Integer>>("sig_17_0_2_2_edc0357_1357846886936_674003_20191", null, (ObjectFlow<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846886936_674003_20191" })).evaluate(true), this);
                if (caller == null) caller = new Parameter<DurativeEvent>("caller", null, (DurativeEvent) null, this);
                if (sig_17_0_2_2_edc0357_1358277576291_904799_38811 == null) sig_17_0_2_2_edc0357_1358277576291_904799_38811 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_2_edc0357_1358277576291_904799_38811", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1358277576291_904799_38811" })).evaluate(true), this);
                if (_17_0_2_2_edc0357_1357846886932_836588_20182_exists == null) _17_0_2_2_edc0357_1357846886932_836588_20182_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846886932_836588_20182_exists", (Boolean) false, this);
                if (sig_17_0_2_2_edc0357_1358277383729_343862_38744 == null) sig_17_0_2_2_edc0357_1358277383729_343862_38744 = new Parameter<ObjectFlow<Integer>>("sig_17_0_2_2_edc0357_1358277383729_343862_38744", null, (ObjectFlow<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1358277383729_343862_38744" })).evaluate(true), this);
                if (decider_17_0_2_2_edc0357_1357846886934_191509_20185 == null) decider_17_0_2_2_edc0357_1357846886934_191509_20185 = new Parameter<TimeVaryingList<Integer>>("decider_17_0_2_2_edc0357_1357846886934_191509_20185", null, (TimeVaryingList<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingList.class, java.lang.String.class, int.class), new Object[] { "decider_17_0_2_2_edc0357_1357846886934_191509_20185", 3 })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846886936_38505_20193 == null) sig_17_0_2_2_edc0357_1357846886936_38505_20193 = new Parameter<ObjectFlow<Power>>("sig_17_0_2_2_edc0357_1357846886936_38505_20193", null, (ObjectFlow<Power>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846886936_38505_20193" })).evaluate(true), this);
                if (finalNode_startTime == null) finalNode_startTime = new IntegerParameter("finalNode_startTime", (Integer) null, this);
                if (sig_17_0_2_2_edc0357_1357846886936_298417_20189 == null) sig_17_0_2_2_edc0357_1357846886936_298417_20189 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_2_edc0357_1357846886936_298417_20189", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846886936_298417_20189" })).evaluate(true), this);
                if (decider_17_0_2_2_edc0357_1358277417331_36014_38761 == null) decider_17_0_2_2_edc0357_1358277417331_36014_38761 = new Parameter<TimeVaryingList<Integer>>("decider_17_0_2_2_edc0357_1358277417331_36014_38761", null, (TimeVaryingList<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingList.class, java.lang.String.class, int.class), new Object[] { "decider_17_0_2_2_edc0357_1358277417331_36014_38761", 2 })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1358277338307_579025_38726 == null) sig_17_0_2_2_edc0357_1358277338307_579025_38726 = new Parameter<ObjectFlow<Integer>>("sig_17_0_2_2_edc0357_1358277338307_579025_38726", null, (ObjectFlow<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1358277338307_579025_38726" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1358277523870_756643_38780 == null) sig_17_0_2_2_edc0357_1358277523870_756643_38780 = new Parameter<ObjectFlow<Power>>("sig_17_0_2_2_edc0357_1358277523870_756643_38780", null, (ObjectFlow<Power>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1358277523870_756643_38780" })).evaluate(true), this);
                if (finalNode_endTime == null) finalNode_endTime = new IntegerParameter("finalNode_endTime", (Integer) null, this);
                if (sig_17_0_2_2_edc0357_1357846886936_848239_20192 == null) sig_17_0_2_2_edc0357_1357846886936_848239_20192 = new Parameter<ObjectFlow<Power>>("sig_17_0_2_2_edc0357_1357846886936_848239_20192", null, (ObjectFlow<Power>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846886936_848239_20192" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1358277571267_916379_38806 == null) sig_17_0_2_2_edc0357_1358277571267_916379_38806 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_2_edc0357_1358277571267_916379_38806", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1358277571267_916379_38806" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1358277531744_78108_38785 == null) sig_17_0_2_2_edc0357_1358277531744_78108_38785 = new Parameter<ObjectFlow<Integer>>("sig_17_0_2_2_edc0357_1358277531744_78108_38785", null, (ObjectFlow<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1358277531744_78108_38785" })).evaluate(true), this);
                if (decider_17_0_2_2_edc0357_1358277276270_502536_38688 == null) decider_17_0_2_2_edc0357_1358277276270_502536_38688 = new Parameter<TimeVaryingList<Integer>>("decider_17_0_2_2_edc0357_1358277276270_502536_38688", null, (TimeVaryingList<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingList.class, java.lang.String.class, int.class), new Object[] { "decider_17_0_2_2_edc0357_1358277276270_502536_38688", 2 })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1358277399765_397589_38755 == null) sig_17_0_2_2_edc0357_1358277399765_397589_38755 = new Parameter<ObjectFlow<Integer>>("sig_17_0_2_2_edc0357_1358277399765_397589_38755", null, (ObjectFlow<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1358277399765_397589_38755" })).evaluate(true), this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void init_17_0_2_2_edc0357_1357846886580_140004_19866Collections() {
            parameters.add(sig_17_0_2_2_edc0357_1358277380386_823740_38739);
            parameters.add(sig_17_0_2_2_edc0357_1358277216425_708226_38675);
            parameters.add(sig_17_0_2_2_edc0357_1357846886936_304274_20195);
            parameters.add(sig_17_0_2_2_edc0357_1357846886936_674003_20191);
            parameters.add(caller);
            parameters.add(sig_17_0_2_2_edc0357_1358277576291_904799_38811);
            parameters.add(_17_0_2_2_edc0357_1357846886932_836588_20182_exists);
            parameters.add(sig_17_0_2_2_edc0357_1358277383729_343862_38744);
            parameters.add(decider_17_0_2_2_edc0357_1357846886934_191509_20185);
            parameters.add(sig_17_0_2_2_edc0357_1357846886936_38505_20193);
            parameters.add(finalNode_startTime);
            parameters.add(sig_17_0_2_2_edc0357_1357846886936_298417_20189);
            parameters.add(decider_17_0_2_2_edc0357_1358277417331_36014_38761);
            parameters.add(sig_17_0_2_2_edc0357_1358277338307_579025_38726);
            parameters.add(sig_17_0_2_2_edc0357_1358277523870_756643_38780);
            parameters.add(finalNode_endTime);
            parameters.add(sig_17_0_2_2_edc0357_1357846886936_848239_20192);
            parameters.add(sig_17_0_2_2_edc0357_1358277571267_916379_38806);
            parameters.add(sig_17_0_2_2_edc0357_1358277531744_78108_38785);
            parameters.add(decider_17_0_2_2_edc0357_1358277276270_502536_38688);
            parameters.add(sig_17_0_2_2_edc0357_1358277399765_397589_38755);
        }

        public void init_17_0_2_2_edc0357_1357846886580_140004_19866Dependencies() {
            caller.getValue(true).addDependency(caller.getValue(true).endTime, new Expression<Integer>(finalNode_endTime));
            addDependency(_17_0_2_2_edc0357_1357846886932_836588_20182_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.NotEquals(new Expression<Integer>(finalNode_startTime), null)), new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1358277576291_904799_38811, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(new Functions.Minus(new Expression<Integer>(finalNode_startTime), new Expression<Integer>(1))) })))));
            addDependency(endTime, new Expression<Integer>(finalNode_endTime));
        }

        public void init_17_0_2_2_edc0357_1357846886580_140004_19866Elaborations() {
            init_17_0_2_2_edc0357_1357846886580_140004_19866Dependencies();
            Expression<?>[] arguments372 = new Expression<?>[1];
            arguments372[0] = new Expression<Integer>(startTime);
            Expression<Boolean> condition372 = new Expression<Boolean>(true);
            elaborationRule372 = addElaborationRule(condition372, _17_0_2_2_edc0357_1357846886580_140004_19866.this, Power._17_0_2_2_edc0357_1357846886580_140004_19866._17_0_2_2_edc0357_1357846886932_93917_20181.class, "PGen_start_InitialNode_generate", arguments372);
            Expression<?>[] arguments373 = new Expression<?>[1];
            arguments373[0] = new Expression<Integer>(finalNode_startTime);
            Expression<Boolean> condition373 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846886932_836588_20182_exists);
            elaborationRule373 = addElaborationRule(condition373, _17_0_2_2_edc0357_1357846886580_140004_19866.this, Power._17_0_2_2_edc0357_1357846886580_140004_19866._17_0_2_2_edc0357_1357846886932_836588_20182.class, "PGen_final_ActivityFinalNode_generate", arguments373);
        }
    }

    public class _17_0_2_2_edc0357_1357846886581_947081_19867 extends DurativeEvent {

        public _17_0_2_2_edc0357_1357846886581_947081_19867() {
            super();
            init_17_0_2_2_edc0357_1357846886581_947081_19867Members();
            init_17_0_2_2_edc0357_1357846886581_947081_19867Collections();
            init_17_0_2_2_edc0357_1357846886581_947081_19867Elaborations();
        }

        public class _17_0_2_2_edc0357_1357846886950_513623_20221 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846886950_513623_20221() {
                super();
                init_17_0_2_2_edc0357_1357846886950_513623_20221Members();
                init_17_0_2_2_edc0357_1357846886950_513623_20221Collections();
                init_17_0_2_2_edc0357_1357846886950_513623_20221Elaborations();
            }

            public _17_0_2_2_edc0357_1357846886950_513623_20221(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846886950_513623_20221Members();
                init_17_0_2_2_edc0357_1357846886950_513623_20221Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846886950_513623_20221Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_2_edc0357_1357846886951_131433_20222_exists = null;

            public BooleanParameter objectToPass = null;

            public Dependency _17_0_2_2_edc0357_1357846886951_131433_20222_existsDependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Effect effect411 = null;

            public Parameter effect411Var = null;

            public ElaborationRule elaborationRule412 = null;

            public void init_17_0_2_2_edc0357_1357846886950_513623_20221Members() {
                try {
                    if (_17_0_2_2_edc0357_1357846886951_131433_20222_exists == null) _17_0_2_2_edc0357_1357846886951_131433_20222_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846886951_131433_20222_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect411VarV = sig_17_0_2_2_edc0357_1357846886960_341556_20249;
                    effect411Var = new Parameter("effect411Var", null, null, this);
                    addDependency(effect411Var, new Expression(effect411VarV));
                    effect411 = new EffectFunction(new EffectFunction(effect411Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846886950_513623_20221Collections() {
                parameters.add(_17_0_2_2_edc0357_1357846886951_131433_20222_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect411Var = new TreeSet<Effect>();
                effectsForeffect411Var.add(effect411);
                addEffects((Parameter<?>) effect411Var, effectsForeffect411Var);
            }

            public void init_17_0_2_2_edc0357_1357846886950_513623_20221Dependencies() {
                addDependency(_17_0_2_2_edc0357_1357846886951_131433_20222_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886956_698207_20232_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886956_698207_20232_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(true));
            }

            public void init_17_0_2_2_edc0357_1357846886950_513623_20221Elaborations() {
                init_17_0_2_2_edc0357_1357846886950_513623_20221Dependencies();
                Expression<?>[] arguments412 = new Expression<?>[1];
                arguments412[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition412 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846886951_131433_20222_exists);
                elaborationRule412 = addElaborationRule(condition412, _17_0_2_2_edc0357_1357846886581_947081_19867.this, Power._17_0_2_2_edc0357_1357846886581_947081_19867._17_0_2_2_edc0357_1357846886951_131433_20222.class, "P_init_CallBehaviorAction_PowerCB", arguments412);
            }
        }

        public class _17_0_2_2_edc0357_1357846886951_131433_20222 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846886951_131433_20222() {
                super();
                init_17_0_2_2_edc0357_1357846886951_131433_20222Members();
                init_17_0_2_2_edc0357_1357846886951_131433_20222Collections();
                init_17_0_2_2_edc0357_1357846886951_131433_20222Elaborations();
            }

            public _17_0_2_2_edc0357_1357846886951_131433_20222(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846886951_131433_20222Members();
                init_17_0_2_2_edc0357_1357846886951_131433_20222Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846886951_131433_20222Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_2_edc0357_1357846886951_791656_20223_exists = null;

            public BooleanParameter objectToPass = null;

            public Dependency _17_0_2_2_edc0357_1357846886951_791656_20223_existsDependency = null;

            public Dependency objectToPassDependency = null;

            public Effect effect413 = null;

            public Parameter effect413Var = null;

            public ElaborationRule elaborationRule414 = null;

            public ElaborationRule elaborationRule415 = null;

            public void init_17_0_2_2_edc0357_1357846886951_131433_20222Members() {
                try {
                    if (_17_0_2_2_edc0357_1357846886951_791656_20223_exists == null) _17_0_2_2_edc0357_1357846886951_791656_20223_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846886951_791656_20223_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect413VarV = sig_17_0_2_2_edc0357_1357846886962_974598_20266;
                    effect413Var = new Parameter("effect413Var", null, null, this);
                    addDependency(effect413Var, new Expression(effect413VarV));
                    effect413 = new EffectFunction(new EffectFunction(effect413Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846886951_131433_20222Collections() {
                parameters.add(_17_0_2_2_edc0357_1357846886951_791656_20223_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect413Var = new TreeSet<Effect>();
                effectsForeffect413Var.add(effect413);
                addEffects((Parameter<?>) effect413Var, effectsForeffect413Var);
            }

            public void init_17_0_2_2_edc0357_1357846886951_131433_20222Dependencies() {
                addDependency(_17_0_2_2_edc0357_1357846886951_791656_20223_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886956_698207_20232_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886956_698207_20232_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846886960_341556_20249, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_2_edc0357_1357846886951_131433_20222Elaborations() {
                init_17_0_2_2_edc0357_1357846886951_131433_20222Dependencies();
                Expression<?>[] arguments414 = new Expression<?>[2];
                arguments414[0] = new Expression<Integer>(startTime);
                arguments414[1] = new Expression<DurativeEvent>(this);
                Expression<Boolean> condition414 = new Expression<Boolean>(true);
                elaborationRule414 = addElaborationRule(condition414, Power.this, Power._17_0_2_2_edc0357_1357846886579_898473_19865.class, "intializeP_Activity_Power", arguments414);
                Expression<?>[] arguments415 = new Expression<?>[1];
                arguments415[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition415 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846886951_791656_20223_exists);
                elaborationRule415 = addElaborationRule(condition415, _17_0_2_2_edc0357_1357846886581_947081_19867.this, Power._17_0_2_2_edc0357_1357846886581_947081_19867._17_0_2_2_edc0357_1357846886951_791656_20223.class, "_ForkNode_PowerCB", arguments415);
            }
        }

        public class _17_0_2_2_edc0357_1357846886951_791656_20223 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846886951_791656_20223() {
                super();
                init_17_0_2_2_edc0357_1357846886951_791656_20223Members();
                init_17_0_2_2_edc0357_1357846886951_791656_20223Collections();
                init_17_0_2_2_edc0357_1357846886951_791656_20223Elaborations();
            }

            public _17_0_2_2_edc0357_1357846886951_791656_20223(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846886951_791656_20223Members();
                init_17_0_2_2_edc0357_1357846886951_791656_20223Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846886951_791656_20223Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_2_edc0357_1358277779552_2828_38826_exists = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846886954_287683_20229_exists = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846886957_572229_20235_exists = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846886955_763631_20231_exists = null;

            public BooleanParameter objectToPass = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846886955_431338_20230_exists = null;

            public Dependency _17_0_2_2_edc0357_1358277779552_2828_38826_existsDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846886954_287683_20229_existsDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846886957_572229_20235_existsDependency = null;

            public Dependency durationDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846886955_763631_20231_existsDependency = null;

            public Dependency objectToPassDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846886955_431338_20230_existsDependency = null;

            public Effect effect416 = null;

            public Parameter effect416Var = null;

            public Effect effect417 = null;

            public Parameter effect417Var = null;

            public Effect effect418 = null;

            public Parameter effect418Var = null;

            public Effect effect419 = null;

            public Parameter effect419Var = null;

            public Effect effect420 = null;

            public Parameter effect420Var = null;

            public ElaborationRule elaborationRule421 = null;

            public ElaborationRule elaborationRule422 = null;

            public ElaborationRule elaborationRule423 = null;

            public ElaborationRule elaborationRule424 = null;

            public ElaborationRule elaborationRule425 = null;

            public void init_17_0_2_2_edc0357_1357846886951_791656_20223Members() {
                try {
                    if (_17_0_2_2_edc0357_1358277779552_2828_38826_exists == null) _17_0_2_2_edc0357_1358277779552_2828_38826_exists = new BooleanParameter("_17_0_2_2_edc0357_1358277779552_2828_38826_exists", (Boolean) false, this);
                    if (_17_0_2_2_edc0357_1357846886954_287683_20229_exists == null) _17_0_2_2_edc0357_1357846886954_287683_20229_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846886954_287683_20229_exists", (Boolean) false, this);
                    if (_17_0_2_2_edc0357_1357846886957_572229_20235_exists == null) _17_0_2_2_edc0357_1357846886957_572229_20235_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846886957_572229_20235_exists", (Boolean) false, this);
                    if (_17_0_2_2_edc0357_1357846886955_763631_20231_exists == null) _17_0_2_2_edc0357_1357846886955_763631_20231_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846886955_763631_20231_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (_17_0_2_2_edc0357_1357846886955_431338_20230_exists == null) _17_0_2_2_edc0357_1357846886955_431338_20230_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846886955_431338_20230_exists", (Boolean) false, this);
                    Object effect416VarV = sig_17_0_2_2_edc0357_1357846886961_263446_20250;
                    effect416Var = new Parameter("effect416Var", null, null, this);
                    addDependency(effect416Var, new Expression(effect416VarV));
                    effect416 = new EffectFunction(new EffectFunction(effect416Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect417VarV = sig_17_0_2_2_edc0357_1357846886961_679328_20251;
                    effect417Var = new Parameter("effect417Var", null, null, this);
                    addDependency(effect417Var, new Expression(effect417VarV));
                    effect417 = new EffectFunction(new EffectFunction(effect417Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect418VarV = sig_17_0_2_2_edc0357_1357846886962_562181_20262;
                    effect418Var = new Parameter("effect418Var", null, null, this);
                    addDependency(effect418Var, new Expression(effect418VarV));
                    effect418 = new EffectFunction(new EffectFunction(effect418Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect419VarV = sig_17_0_2_2_edc0357_1357846886962_893992_20268;
                    effect419Var = new Parameter("effect419Var", null, null, this);
                    addDependency(effect419Var, new Expression(effect419VarV));
                    effect419 = new EffectFunction(new EffectFunction(effect419Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect420VarV = sig_17_0_2_2_edc0357_1358277784216_673532_38832;
                    effect420Var = new Parameter("effect420Var", null, null, this);
                    addDependency(effect420Var, new Expression(effect420VarV));
                    effect420 = new EffectFunction(new EffectFunction(effect420Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846886951_791656_20223Collections() {
                parameters.add(_17_0_2_2_edc0357_1358277779552_2828_38826_exists);
                parameters.add(_17_0_2_2_edc0357_1357846886954_287683_20229_exists);
                parameters.add(_17_0_2_2_edc0357_1357846886957_572229_20235_exists);
                parameters.add(_17_0_2_2_edc0357_1357846886955_763631_20231_exists);
                parameters.add(objectToPass);
                parameters.add(_17_0_2_2_edc0357_1357846886955_431338_20230_exists);
                Set<Effect> effectsForeffect416Var = new TreeSet<Effect>();
                effectsForeffect416Var.add(effect416);
                addEffects((Parameter<?>) effect416Var, effectsForeffect416Var);
                Set<Effect> effectsForeffect417Var = new TreeSet<Effect>();
                effectsForeffect417Var.add(effect417);
                addEffects((Parameter<?>) effect417Var, effectsForeffect417Var);
                Set<Effect> effectsForeffect418Var = new TreeSet<Effect>();
                effectsForeffect418Var.add(effect418);
                addEffects((Parameter<?>) effect418Var, effectsForeffect418Var);
                Set<Effect> effectsForeffect419Var = new TreeSet<Effect>();
                effectsForeffect419Var.add(effect419);
                addEffects((Parameter<?>) effect419Var, effectsForeffect419Var);
                Set<Effect> effectsForeffect420Var = new TreeSet<Effect>();
                effectsForeffect420Var.add(effect420);
                addEffects((Parameter<?>) effect420Var, effectsForeffect420Var);
            }

            public void init_17_0_2_2_edc0357_1357846886951_791656_20223Dependencies() {
                addDependency(_17_0_2_2_edc0357_1358277779552_2828_38826_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886956_698207_20232_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886956_698207_20232_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(_17_0_2_2_edc0357_1357846886954_287683_20229_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886956_698207_20232_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886956_698207_20232_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(_17_0_2_2_edc0357_1357846886957_572229_20235_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886956_698207_20232_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886956_698207_20232_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(_17_0_2_2_edc0357_1357846886955_763631_20231_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886956_698207_20232_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886956_698207_20232_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846886962_974598_20266, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_2_edc0357_1357846886955_431338_20230_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886956_698207_20232_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886956_698207_20232_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
            }

            public void init_17_0_2_2_edc0357_1357846886951_791656_20223Elaborations() {
                init_17_0_2_2_edc0357_1357846886951_791656_20223Dependencies();
                Expression<?>[] arguments421 = new Expression<?>[2];
                arguments421[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                arguments421[1] = new Expression<ObjectFlow<Boolean>>(sig_17_0_2_2_edc0357_1357846886961_263446_20250);
                Expression<Boolean> condition421 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846886954_287683_20229_exists);
                elaborationRule421 = addElaborationRule(condition421, _17_0_2_2_edc0357_1357846886581_947081_19867.this, Power._17_0_2_2_edc0357_1357846886581_947081_19867._17_0_2_2_edc0357_1357846886954_287683_20229.class, "P_change_m_MergeNode_PowerCB", arguments421);
                Expression<?>[] arguments422 = new Expression<?>[2];
                arguments422[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                arguments422[1] = new Expression<ObjectFlow<Boolean>>(sig_17_0_2_2_edc0357_1357846886962_893992_20268);
                Expression<Boolean> condition422 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846886955_763631_20231_exists);
                elaborationRule422 = addElaborationRule(condition422, _17_0_2_2_edc0357_1357846886581_947081_19867.this, Power._17_0_2_2_edc0357_1357846886581_947081_19867._17_0_2_2_edc0357_1357846886955_763631_20231.class, "P_gen_merge_MergeNode_PowerCB", arguments422);
                Expression<?>[] arguments423 = new Expression<?>[2];
                arguments423[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                arguments423[1] = new Expression<ObjectFlow<Boolean>>(sig_17_0_2_2_edc0357_1358277784216_673532_38832);
                Expression<Boolean> condition423 = new Expression<Boolean>(_17_0_2_2_edc0357_1358277779552_2828_38826_exists);
                elaborationRule423 = addElaborationRule(condition423, _17_0_2_2_edc0357_1357846886581_947081_19867.this, Power._17_0_2_2_edc0357_1357846886581_947081_19867._17_0_2_2_edc0357_1358277779552_2828_38826.class, "P_merge_feeders_MergeNode_PowerCB", arguments423);
                Expression<?>[] arguments424 = new Expression<?>[2];
                arguments424[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                arguments424[1] = new Expression<ObjectFlow<Boolean>>(sig_17_0_2_2_edc0357_1357846886961_679328_20251);
                Expression<Boolean> condition424 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846886955_431338_20230_exists);
                elaborationRule424 = addElaborationRule(condition424, _17_0_2_2_edc0357_1357846886581_947081_19867.this, Power._17_0_2_2_edc0357_1357846886581_947081_19867._17_0_2_2_edc0357_1357846886955_431338_20230.class, "P_load_m_MergeNode_PowerCB", arguments424);
                Expression<?>[] arguments425 = new Expression<?>[1];
                arguments425[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition425 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846886957_572229_20235_exists);
                elaborationRule425 = addElaborationRule(condition425, _17_0_2_2_edc0357_1357846886581_947081_19867.this, Power._17_0_2_2_edc0357_1357846886581_947081_19867._17_0_2_2_edc0357_1357846886957_572229_20235.class, "powerCBTimer_AcceptEventAction_PowerCB", arguments425);
            }
        }

        public class _17_0_2_2_edc0357_1357846886951_889176_20224 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846886951_889176_20224() {
                super();
                init_17_0_2_2_edc0357_1357846886951_889176_20224Members();
                init_17_0_2_2_edc0357_1357846886951_889176_20224Collections();
                init_17_0_2_2_edc0357_1357846886951_889176_20224Elaborations();
            }

            public _17_0_2_2_edc0357_1357846886951_889176_20224(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846886951_889176_20224Members();
                init_17_0_2_2_edc0357_1357846886951_889176_20224Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846886951_889176_20224Elaborations();
                fixTimeDependencies();
            }

            public Parameter< Power_System.SignalchangeLoadValue > _17_0_2_2_edc0357_1357846887424_221413_21327 = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846886956_970455_20233_exists = null;

            public BooleanParameter objectToPass = null;

            public Dependency _17_0_2_2_edc0357_1357846887424_221413_21327Dependency = null;

            public Dependency _17_0_2_2_edc0357_1357846886956_970455_20233_existsDependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Effect effect426 = null;

            public Parameter effect426Var = null;

            public ElaborationRule elaborationRule427 = null;

            public void init_17_0_2_2_edc0357_1357846886951_889176_20224Members() {
                try {
                    if (_17_0_2_2_edc0357_1357846887424_221413_21327 == null) _17_0_2_2_edc0357_1357846887424_221413_21327 = new Parameter<Power_System.SignalchangeLoadValue>("_17_0_2_2_edc0357_1357846887424_221413_21327", null, (Power_System.SignalchangeLoadValue) null, this);
                    if (_17_0_2_2_edc0357_1357846886956_970455_20233_exists == null) _17_0_2_2_edc0357_1357846886956_970455_20233_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846886956_970455_20233_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect426VarV = sig_17_0_2_2_edc0357_1357846886961_519045_20258;
                    effect426Var = new Parameter("effect426Var", null, null, this);
                    addDependency(effect426Var, new Expression(effect426VarV));
                    effect426 = new EffectFunction(new EffectFunction(effect426Var, ClassUtils.getMethodForArgTypes("ObjectFlow<SignalchangeLoadValue>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { _17_0_2_2_edc0357_1357846887424_221413_21327, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846886951_889176_20224Collections() {
                parameters.add(_17_0_2_2_edc0357_1357846887424_221413_21327);
                parameters.add(_17_0_2_2_edc0357_1357846886956_970455_20233_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect426Var = new TreeSet<Effect>();
                effectsForeffect426Var.add(effect426);
                addEffects((Parameter<?>) effect426Var, effectsForeffect426Var);
            }

            public void init_17_0_2_2_edc0357_1357846886951_889176_20224Dependencies() {
                addDependency(_17_0_2_2_edc0357_1357846887424_221413_21327, new Expression(new EffectFunction(q_Power_changeLoadValue, ClassUtils.getMethodForArgTypes("ObjectFlow<SignalchangeLoadValue>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_2_edc0357_1357846886956_970455_20233_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886956_698207_20232_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886956_698207_20232_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846886961_494059_20254, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_2_edc0357_1357846886951_889176_20224Elaborations() {
                init_17_0_2_2_edc0357_1357846886951_889176_20224Dependencies();
                Expression<?>[] arguments427 = new Expression<?>[1];
                arguments427[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition427 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846886956_970455_20233_exists);
                elaborationRule427 = addElaborationRule(condition427, _17_0_2_2_edc0357_1357846886581_947081_19867.this, Power._17_0_2_2_edc0357_1357846886581_947081_19867._17_0_2_2_edc0357_1357846886956_970455_20233.class, "P_readLoadFromSignal_ReadStructuralFeatureAction_PowerCB", arguments427);
            }
        }

        public class _17_0_2_2_edc0357_1357846886952_430690_20225 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846886952_430690_20225() {
                super();
                init_17_0_2_2_edc0357_1357846886952_430690_20225Members();
                init_17_0_2_2_edc0357_1357846886952_430690_20225Collections();
                init_17_0_2_2_edc0357_1357846886952_430690_20225Elaborations();
            }

            public _17_0_2_2_edc0357_1357846886952_430690_20225(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846886952_430690_20225Members();
                init_17_0_2_2_edc0357_1357846886952_430690_20225Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846886952_430690_20225Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_2_edc0357_1357846886957_101011_20234_exists = null;

            public BooleanParameter objectToPass = null;

            public Parameter< Power_System.SignalchangeGenerationValue > _17_0_2_2_edc0357_1357846887425_612658_21329 = null;

            public Dependency _17_0_2_2_edc0357_1357846886957_101011_20234_existsDependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887425_612658_21329Dependency = null;

            public Effect effect428 = null;

            public Parameter effect428Var = null;

            public ElaborationRule elaborationRule429 = null;

            public void init_17_0_2_2_edc0357_1357846886952_430690_20225Members() {
                try {
                    if (_17_0_2_2_edc0357_1357846886957_101011_20234_exists == null) _17_0_2_2_edc0357_1357846886957_101011_20234_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846886957_101011_20234_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (_17_0_2_2_edc0357_1357846887425_612658_21329 == null) _17_0_2_2_edc0357_1357846887425_612658_21329 = new Parameter<Power_System.SignalchangeGenerationValue>("_17_0_2_2_edc0357_1357846887425_612658_21329", null, (Power_System.SignalchangeGenerationValue) null, this);
                    Object effect428VarV = sig_17_0_2_2_edc0357_1357846886962_137224_20260;
                    effect428Var = new Parameter("effect428Var", null, null, this);
                    addDependency(effect428Var, new Expression(effect428VarV));
                    effect428 = new EffectFunction(new EffectFunction(effect428Var, ClassUtils.getMethodForArgTypes("ObjectFlow<SignalchangeGenerationValue>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { _17_0_2_2_edc0357_1357846887425_612658_21329, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846886952_430690_20225Collections() {
                parameters.add(_17_0_2_2_edc0357_1357846886957_101011_20234_exists);
                parameters.add(objectToPass);
                parameters.add(_17_0_2_2_edc0357_1357846887425_612658_21329);
                Set<Effect> effectsForeffect428Var = new TreeSet<Effect>();
                effectsForeffect428Var.add(effect428);
                addEffects((Parameter<?>) effect428Var, effectsForeffect428Var);
            }

            public void init_17_0_2_2_edc0357_1357846886952_430690_20225Dependencies() {
                addDependency(_17_0_2_2_edc0357_1357846886957_101011_20234_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886956_698207_20232_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886956_698207_20232_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846886961_125803_20253, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_2_edc0357_1357846887425_612658_21329, new Expression(new EffectFunction(q_Power_changeGenerationValue, ClassUtils.getMethodForArgTypes("ObjectFlow<SignalchangeGenerationValue>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_2_edc0357_1357846886952_430690_20225Elaborations() {
                init_17_0_2_2_edc0357_1357846886952_430690_20225Dependencies();
                Expression<?>[] arguments429 = new Expression<?>[1];
                arguments429[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition429 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846886957_101011_20234_exists);
                elaborationRule429 = addElaborationRule(condition429, _17_0_2_2_edc0357_1357846886581_947081_19867.this, Power._17_0_2_2_edc0357_1357846886581_947081_19867._17_0_2_2_edc0357_1357846886957_101011_20234.class, "P_readChangeGenVal_ReadStructuralFeatureAction_PowerCB", arguments429);
            }
        }

        public class _17_0_2_2_edc0357_1357846886952_687661_20226 extends Power._17_0_2_2_edc0357_1357846886579_143744_19864_interface {

            public _17_0_2_2_edc0357_1357846886952_687661_20226() {
                super();
                init_17_0_2_2_edc0357_1357846886952_687661_20226Members();
                init_17_0_2_2_edc0357_1357846886952_687661_20226Collections();
                init_17_0_2_2_edc0357_1357846886952_687661_20226Elaborations();
            }

            public _17_0_2_2_edc0357_1357846886952_687661_20226(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846886952_687661_20226Members();
                init_17_0_2_2_edc0357_1357846886952_687661_20226Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846886952_687661_20226Elaborations();
                fixTimeDependencies();
            }

            public IntegerParameter _17_0_2_2_edc0357_1357846887425_43409_21330 = null;

            public BooleanParameter objectToPass = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846886955_431338_20230_exists = null;

            public Dependency _17_0_2_2_edc0357_1357846886882_400011_20082Dependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887425_43409_21330Dependency = null;

            public Dependency objectToPassDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846886955_431338_20230_existsDependency = null;

            public Effect effect430 = null;

            public Parameter effect430Var = null;

            public ElaborationRule elaborationRule431 = null;

            public ElaborationRule elaborationRule432 = null;

            public void init_17_0_2_2_edc0357_1357846886952_687661_20226Members() {
                try {
                    if (_17_0_2_2_edc0357_1357846887425_43409_21330 == null) _17_0_2_2_edc0357_1357846887425_43409_21330 = new IntegerParameter("_17_0_2_2_edc0357_1357846887425_43409_21330", (Integer) null, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (_17_0_2_2_edc0357_1357846886955_431338_20230_exists == null) _17_0_2_2_edc0357_1357846886955_431338_20230_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846886955_431338_20230_exists", (Boolean) false, this);
                    Object effect430VarV = sig_17_0_2_2_edc0357_1357846886961_875480_20257;
                    effect430Var = new Parameter("effect430Var", null, null, this);
                    addDependency(effect430Var, new Expression(effect430VarV));
                    effect430 = new EffectFunction(new EffectFunction(effect430Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846886952_687661_20226Collections() {
                parameters.add(_17_0_2_2_edc0357_1357846887425_43409_21330);
                parameters.add(objectToPass);
                parameters.add(_17_0_2_2_edc0357_1357846886955_431338_20230_exists);
                Set<Effect> effectsForeffect430Var = new TreeSet<Effect>();
                effectsForeffect430Var.add(effect430);
                addEffects((Parameter<?>) effect430Var, effectsForeffect430Var);
            }

            public void init_17_0_2_2_edc0357_1357846886952_687661_20226Dependencies() {
                addDependency(_17_0_2_2_edc0357_1357846886882_400011_20082, new Expression<Integer>(_17_0_2_2_edc0357_1357846887425_43409_21330));
                addDependency(_17_0_2_2_edc0357_1357846887425_43409_21330, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846886961_834620_20259, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(objectToPass, new Expression<Boolean>(true));
                addDependency(_17_0_2_2_edc0357_1357846886955_431338_20230_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886956_698207_20232_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886956_698207_20232_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
            }

            public void init_17_0_2_2_edc0357_1357846886952_687661_20226Elaborations() {
                init_17_0_2_2_edc0357_1357846886952_687661_20226Dependencies();
                Expression<?>[] arguments431 = new Expression<?>[3];
                arguments431[0] = new Expression<Integer>(startTime);
                arguments431[1] = new Expression<_17_0_2_2_edc0357_1357846886579_143744_19864_interface>(this);
                arguments431[2] = new Expression<Integer>(_17_0_2_2_edc0357_1357846886882_400011_20082);
                Expression<Boolean> condition431 = new Expression<Boolean>(true);
                elaborationRule431 = addElaborationRule(condition431, Power.this, Power._17_0_2_2_edc0357_1357846886579_143744_19864.class, "changeLoadValue_Activity_Power", arguments431);
                Expression<?>[] arguments432 = new Expression<?>[2];
                arguments432[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                arguments432[1] = new Expression<ObjectFlow<Boolean>>(sig_17_0_2_2_edc0357_1357846886961_875480_20257);
                Expression<Boolean> condition432 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846886955_431338_20230_exists);
                elaborationRule432 = addElaborationRule(condition432, _17_0_2_2_edc0357_1357846886581_947081_19867.this, Power._17_0_2_2_edc0357_1357846886581_947081_19867._17_0_2_2_edc0357_1357846886955_431338_20230.class, "P_load_m_MergeNode_PowerCB", arguments432);
            }
        }

        public class _17_0_2_2_edc0357_1357846886953_875787_20227 extends Power._17_0_2_2_edc0357_1357846886577_262509_19863_interface {

            public _17_0_2_2_edc0357_1357846886953_875787_20227() {
                super();
                init_17_0_2_2_edc0357_1357846886953_875787_20227Members();
                init_17_0_2_2_edc0357_1357846886953_875787_20227Collections();
                init_17_0_2_2_edc0357_1357846886953_875787_20227Elaborations();
            }

            public _17_0_2_2_edc0357_1357846886953_875787_20227(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846886953_875787_20227Members();
                init_17_0_2_2_edc0357_1357846886953_875787_20227Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846886953_875787_20227Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_2_edc0357_1357846886954_287683_20229_exists = null;

            public BooleanParameter objectToPass = null;

            public IntegerParameter _17_0_2_2_edc0357_1357846887426_662555_21331 = null;

            public Dependency _17_0_2_2_edc0357_1357846886954_287683_20229_existsDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846886862_684005_20043Dependency = null;

            public Dependency objectToPassDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887426_662555_21331Dependency = null;

            public Effect effect433 = null;

            public Parameter effect433Var = null;

            public ElaborationRule elaborationRule434 = null;

            public ElaborationRule elaborationRule435 = null;

            public void init_17_0_2_2_edc0357_1357846886953_875787_20227Members() {
                try {
                    if (_17_0_2_2_edc0357_1357846886954_287683_20229_exists == null) _17_0_2_2_edc0357_1357846886954_287683_20229_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846886954_287683_20229_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (_17_0_2_2_edc0357_1357846887426_662555_21331 == null) _17_0_2_2_edc0357_1357846887426_662555_21331 = new IntegerParameter("_17_0_2_2_edc0357_1357846887426_662555_21331", (Integer) null, this);
                    Object effect433VarV = sig_17_0_2_2_edc0357_1357846886961_840973_20256;
                    effect433Var = new Parameter("effect433Var", null, null, this);
                    addDependency(effect433Var, new Expression(effect433VarV));
                    effect433 = new EffectFunction(new EffectFunction(effect433Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846886953_875787_20227Collections() {
                parameters.add(_17_0_2_2_edc0357_1357846886954_287683_20229_exists);
                parameters.add(objectToPass);
                parameters.add(_17_0_2_2_edc0357_1357846887426_662555_21331);
                Set<Effect> effectsForeffect433Var = new TreeSet<Effect>();
                effectsForeffect433Var.add(effect433);
                addEffects((Parameter<?>) effect433Var, effectsForeffect433Var);
            }

            public void init_17_0_2_2_edc0357_1357846886953_875787_20227Dependencies() {
                addDependency(_17_0_2_2_edc0357_1357846886954_287683_20229_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886956_698207_20232_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886956_698207_20232_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(_17_0_2_2_edc0357_1357846886862_684005_20043, new Expression<Integer>(_17_0_2_2_edc0357_1357846887426_662555_21331));
                addDependency(objectToPass, new Expression<Boolean>(true));
                addDependency(_17_0_2_2_edc0357_1357846887426_662555_21331, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846886962_892721_20261, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_2_edc0357_1357846886953_875787_20227Elaborations() {
                init_17_0_2_2_edc0357_1357846886953_875787_20227Dependencies();
                Expression<?>[] arguments434 = new Expression<?>[3];
                arguments434[0] = new Expression<Integer>(startTime);
                arguments434[1] = new Expression<_17_0_2_2_edc0357_1357846886577_262509_19863_interface>(this);
                arguments434[2] = new Expression<Integer>(_17_0_2_2_edc0357_1357846886862_684005_20043);
                Expression<Boolean> condition434 = new Expression<Boolean>(true);
                elaborationRule434 = addElaborationRule(condition434, Power.this, Power._17_0_2_2_edc0357_1357846886577_262509_19863.class, "changeGenerationValue_Activity_Power", arguments434);
                Expression<?>[] arguments435 = new Expression<?>[2];
                arguments435[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                arguments435[1] = new Expression<ObjectFlow<Boolean>>(sig_17_0_2_2_edc0357_1357846886961_840973_20256);
                Expression<Boolean> condition435 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846886954_287683_20229_exists);
                elaborationRule435 = addElaborationRule(condition435, _17_0_2_2_edc0357_1357846886581_947081_19867.this, Power._17_0_2_2_edc0357_1357846886581_947081_19867._17_0_2_2_edc0357_1357846886954_287683_20229.class, "P_change_m_MergeNode_PowerCB", arguments435);
            }
        }

        public class _17_0_2_2_edc0357_1357846886954_788618_20228 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846886954_788618_20228() {
                super();
                init_17_0_2_2_edc0357_1357846886954_788618_20228Members();
                init_17_0_2_2_edc0357_1357846886954_788618_20228Collections();
                init_17_0_2_2_edc0357_1357846886954_788618_20228Elaborations();
            }

            public _17_0_2_2_edc0357_1357846886954_788618_20228(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846886954_788618_20228Members();
                init_17_0_2_2_edc0357_1357846886954_788618_20228Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846886954_788618_20228Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter objectToPass = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846886958_585120_20236_exists = null;

            public Dependency objectToPassDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846886958_585120_20236_existsDependency = null;

            public Effect effect436 = null;

            public Parameter effect436Var = null;

            public ElaborationRule elaborationRule437 = null;

            public ElaborationRule elaborationRule438 = null;

            public void init_17_0_2_2_edc0357_1357846886954_788618_20228Members() {
                try {
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (_17_0_2_2_edc0357_1357846886958_585120_20236_exists == null) _17_0_2_2_edc0357_1357846886958_585120_20236_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846886958_585120_20236_exists", (Boolean) false, this);
                    Object effect436VarV = sig_17_0_2_2_edc0357_1357846886962_832634_20264;
                    effect436Var = new Parameter("effect436Var", null, null, this);
                    addDependency(effect436Var, new Expression(effect436VarV));
                    effect436 = new EffectFunction(new EffectFunction(effect436Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846886954_788618_20228Collections() {
                parameters.add(objectToPass);
                parameters.add(_17_0_2_2_edc0357_1357846886958_585120_20236_exists);
                Set<Effect> effectsForeffect436Var = new TreeSet<Effect>();
                effectsForeffect436Var.add(effect436);
                addEffects((Parameter<?>) effect436Var, effectsForeffect436Var);
            }

            public void init_17_0_2_2_edc0357_1357846886954_788618_20228Dependencies() {
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846886961_66104_20255, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_2_edc0357_1357846886958_585120_20236_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886956_698207_20232_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886956_698207_20232_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
            }

            public void init_17_0_2_2_edc0357_1357846886954_788618_20228Elaborations() {
                init_17_0_2_2_edc0357_1357846886954_788618_20228Dependencies();
                Expression<?>[] arguments437 = new Expression<?>[2];
                arguments437[0] = new Expression<Integer>(startTime);
                arguments437[1] = new Expression<DurativeEvent>(this);
                Expression<Boolean> condition437 = new Expression<Boolean>(true);
                elaborationRule437 = addElaborationRule(condition437, Power.this, Power._17_0_2_2_edc0357_1357846886580_140004_19866.class, "generate_Activity_Power", arguments437);
                Expression<?>[] arguments438 = new Expression<?>[1];
                arguments438[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition438 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846886958_585120_20236_exists);
                elaborationRule438 = addElaborationRule(condition438, _17_0_2_2_edc0357_1357846886581_947081_19867.this, Power._17_0_2_2_edc0357_1357846886581_947081_19867._17_0_2_2_edc0357_1357846886958_585120_20236.class, "P_gen_timer_AcceptEventAction_PowerCB", arguments438);
            }
        }

        public class _17_0_2_2_edc0357_1357846886954_287683_20229 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846886954_287683_20229() {
                super();
                init_17_0_2_2_edc0357_1357846886954_287683_20229Members();
                init_17_0_2_2_edc0357_1357846886954_287683_20229Collections();
                init_17_0_2_2_edc0357_1357846886954_287683_20229Elaborations();
            }

            public _17_0_2_2_edc0357_1357846886954_287683_20229(Expression<Integer> startTime, Expression<ObjectFlow<Boolean>> receiveThis) {
                super();
                init_17_0_2_2_edc0357_1357846886954_287683_20229Members();
                init_17_0_2_2_edc0357_1357846886954_287683_20229Collections();
                addDependency(this.startTime, startTime);
                addDependency(this.receiveThis, receiveThis);
                init_17_0_2_2_edc0357_1357846886954_287683_20229Elaborations();
                fixTimeDependencies();
            }

            public Parameter< ObjectFlow<Boolean> > receiveThis = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846886952_430690_20225_exists = null;

            public BooleanParameter objectToPass = null;

            public Dependency endTimeDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846886952_430690_20225_existsDependency = null;

            public Dependency objectToPassDependency = null;

            public Effect effect439 = null;

            public Parameter effect439Var = null;

            public ElaborationRule elaborationRule440 = null;

            public void init_17_0_2_2_edc0357_1357846886954_287683_20229Members() {
                try {
                    if (receiveThis == null) receiveThis = new Parameter<ObjectFlow<Boolean>>("receiveThis", null, (ObjectFlow<Boolean>) null, this);
                    if (_17_0_2_2_edc0357_1357846886952_430690_20225_exists == null) _17_0_2_2_edc0357_1357846886952_430690_20225_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846886952_430690_20225_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect439VarV = sig_17_0_2_2_edc0357_1357846886961_125803_20253;
                    effect439Var = new Parameter("effect439Var", null, null, this);
                    addDependency(effect439Var, new Expression(effect439VarV));
                    effect439 = new EffectFunction(new EffectFunction(effect439Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846886954_287683_20229Collections() {
                parameters.add(receiveThis);
                parameters.add(_17_0_2_2_edc0357_1357846886952_430690_20225_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect439Var = new TreeSet<Effect>();
                effectsForeffect439Var.add(effect439);
                addEffects((Parameter<?>) effect439Var, effectsForeffect439Var);
            }

            public void init_17_0_2_2_edc0357_1357846886954_287683_20229Dependencies() {
                addDependency(endTime, new Expression(new FunctionCall(null, ClassUtils.getMethodForArgTypes("Math", "PowerSystem3", "min", int.class, int.class), new Object[] { new Expression(new EffectFunction(q_Power_changeGenerationValue, ClassUtils.getMethodForArgTypes("ObjectFlow<SignalchangeGenerationValue>", "PowerSystem3", "nextTimeHasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(startTime) })), new Expression<Integer>(finalNode_endTime) })));
                addDependency(_17_0_2_2_edc0357_1357846886952_430690_20225_exists, new Expression<Boolean>(new Functions.And(new Expression(new EffectFunction(q_Power_changeGenerationValue, ClassUtils.getMethodForArgTypes("ObjectFlow<SignalchangeGenerationValue>", "PowerSystem3", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886956_698207_20232_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886956_698207_20232_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))))));
                addDependency(objectToPass, new Expression(new EffectFunction(receiveThis, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_2_edc0357_1357846886954_287683_20229Elaborations() {
                init_17_0_2_2_edc0357_1357846886954_287683_20229Dependencies();
                Expression<?>[] arguments440 = new Expression<?>[1];
                arguments440[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition440 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846886952_430690_20225_exists);
                elaborationRule440 = addElaborationRule(condition440, _17_0_2_2_edc0357_1357846886581_947081_19867.this, Power._17_0_2_2_edc0357_1357846886581_947081_19867._17_0_2_2_edc0357_1357846886952_430690_20225.class, "P_receivecvg_sig_AcceptEventAction_PowerCB", arguments440);
            }
        }

        public class _17_0_2_2_edc0357_1357846886955_431338_20230 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846886955_431338_20230() {
                super();
                init_17_0_2_2_edc0357_1357846886955_431338_20230Members();
                init_17_0_2_2_edc0357_1357846886955_431338_20230Collections();
                init_17_0_2_2_edc0357_1357846886955_431338_20230Elaborations();
            }

            public _17_0_2_2_edc0357_1357846886955_431338_20230(Expression<Integer> startTime, Expression<ObjectFlow<Boolean>> receiveThis) {
                super();
                init_17_0_2_2_edc0357_1357846886955_431338_20230Members();
                init_17_0_2_2_edc0357_1357846886955_431338_20230Collections();
                addDependency(this.startTime, startTime);
                addDependency(this.receiveThis, receiveThis);
                init_17_0_2_2_edc0357_1357846886955_431338_20230Elaborations();
                fixTimeDependencies();
            }

            public Parameter< ObjectFlow<Boolean> > receiveThis = null;

            public BooleanParameter objectToPass = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846886951_889176_20224_exists = null;

            public Dependency endTimeDependency = null;

            public Dependency objectToPassDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846886951_889176_20224_existsDependency = null;

            public Effect effect441 = null;

            public Parameter effect441Var = null;

            public ElaborationRule elaborationRule442 = null;

            public void init_17_0_2_2_edc0357_1357846886955_431338_20230Members() {
                try {
                    if (receiveThis == null) receiveThis = new Parameter<ObjectFlow<Boolean>>("receiveThis", null, (ObjectFlow<Boolean>) null, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (_17_0_2_2_edc0357_1357846886951_889176_20224_exists == null) _17_0_2_2_edc0357_1357846886951_889176_20224_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846886951_889176_20224_exists", (Boolean) false, this);
                    Object effect441VarV = sig_17_0_2_2_edc0357_1357846886961_494059_20254;
                    effect441Var = new Parameter("effect441Var", null, null, this);
                    addDependency(effect441Var, new Expression(effect441VarV));
                    effect441 = new EffectFunction(new EffectFunction(effect441Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846886955_431338_20230Collections() {
                parameters.add(receiveThis);
                parameters.add(objectToPass);
                parameters.add(_17_0_2_2_edc0357_1357846886951_889176_20224_exists);
                Set<Effect> effectsForeffect441Var = new TreeSet<Effect>();
                effectsForeffect441Var.add(effect441);
                addEffects((Parameter<?>) effect441Var, effectsForeffect441Var);
            }

            public void init_17_0_2_2_edc0357_1357846886955_431338_20230Dependencies() {
                addDependency(endTime, new Expression(new FunctionCall(null, ClassUtils.getMethodForArgTypes("Math", "PowerSystem3", "min", int.class, int.class), new Object[] { new Expression(new EffectFunction(q_Power_changeLoadValue, ClassUtils.getMethodForArgTypes("ObjectFlow<SignalchangeLoadValue>", "PowerSystem3", "nextTimeHasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(startTime) })), new Expression<Integer>(finalNode_endTime) })));
                addDependency(objectToPass, new Expression(new EffectFunction(receiveThis, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_2_edc0357_1357846886951_889176_20224_exists, new Expression<Boolean>(new Functions.And(new Expression(new EffectFunction(q_Power_changeLoadValue, ClassUtils.getMethodForArgTypes("ObjectFlow<SignalchangeLoadValue>", "PowerSystem3", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886956_698207_20232_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886956_698207_20232_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))))));
            }

            public void init_17_0_2_2_edc0357_1357846886955_431338_20230Elaborations() {
                init_17_0_2_2_edc0357_1357846886955_431338_20230Dependencies();
                Expression<?>[] arguments442 = new Expression<?>[1];
                arguments442[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition442 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846886951_889176_20224_exists);
                elaborationRule442 = addElaborationRule(condition442, _17_0_2_2_edc0357_1357846886581_947081_19867.this, Power._17_0_2_2_edc0357_1357846886581_947081_19867._17_0_2_2_edc0357_1357846886951_889176_20224.class, "P_receiveclv_sig_AcceptEventAction_PowerCB", arguments442);
            }
        }

        public class _17_0_2_2_edc0357_1357846886955_763631_20231 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846886955_763631_20231() {
                super();
                init_17_0_2_2_edc0357_1357846886955_763631_20231Members();
                init_17_0_2_2_edc0357_1357846886955_763631_20231Collections();
                init_17_0_2_2_edc0357_1357846886955_763631_20231Elaborations();
            }

            public _17_0_2_2_edc0357_1357846886955_763631_20231(Expression<Integer> startTime, Expression<ObjectFlow<Boolean>> receiveThis) {
                super();
                init_17_0_2_2_edc0357_1357846886955_763631_20231Members();
                init_17_0_2_2_edc0357_1357846886955_763631_20231Collections();
                addDependency(this.startTime, startTime);
                addDependency(this.receiveThis, receiveThis);
                init_17_0_2_2_edc0357_1357846886955_763631_20231Elaborations();
                fixTimeDependencies();
            }

            public Parameter< ObjectFlow<Boolean> > receiveThis = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846886954_788618_20228_exists = null;

            public BooleanParameter objectToPass = null;

            public Dependency _17_0_2_2_edc0357_1357846886954_788618_20228_existsDependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Effect effect443 = null;

            public Parameter effect443Var = null;

            public ElaborationRule elaborationRule444 = null;

            public void init_17_0_2_2_edc0357_1357846886955_763631_20231Members() {
                try {
                    if (receiveThis == null) receiveThis = new Parameter<ObjectFlow<Boolean>>("receiveThis", null, (ObjectFlow<Boolean>) null, this);
                    if (_17_0_2_2_edc0357_1357846886954_788618_20228_exists == null) _17_0_2_2_edc0357_1357846886954_788618_20228_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846886954_788618_20228_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect443VarV = sig_17_0_2_2_edc0357_1357846886961_66104_20255;
                    effect443Var = new Parameter("effect443Var", null, null, this);
                    addDependency(effect443Var, new Expression(effect443VarV));
                    effect443 = new EffectFunction(new EffectFunction(effect443Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846886955_763631_20231Collections() {
                parameters.add(receiveThis);
                parameters.add(_17_0_2_2_edc0357_1357846886954_788618_20228_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect443Var = new TreeSet<Effect>();
                effectsForeffect443Var.add(effect443);
                addEffects((Parameter<?>) effect443Var, effectsForeffect443Var);
            }

            public void init_17_0_2_2_edc0357_1357846886955_763631_20231Dependencies() {
                addDependency(_17_0_2_2_edc0357_1357846886954_788618_20228_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886956_698207_20232_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886956_698207_20232_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression(new EffectFunction(receiveThis, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_2_edc0357_1357846886955_763631_20231Elaborations() {
                init_17_0_2_2_edc0357_1357846886955_763631_20231Dependencies();
                Expression<?>[] arguments444 = new Expression<?>[1];
                arguments444[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition444 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846886954_788618_20228_exists);
                elaborationRule444 = addElaborationRule(condition444, _17_0_2_2_edc0357_1357846886581_947081_19867.this, Power._17_0_2_2_edc0357_1357846886581_947081_19867._17_0_2_2_edc0357_1357846886954_788618_20228.class, "P_gen_CallBehaviorAction_PowerCB", arguments444);
            }
        }

        public class _17_0_2_2_edc0357_1357846886956_698207_20232 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846886956_698207_20232() {
                super();
                init_17_0_2_2_edc0357_1357846886956_698207_20232Members();
                init_17_0_2_2_edc0357_1357846886956_698207_20232Collections();
                init_17_0_2_2_edc0357_1357846886956_698207_20232Elaborations();
            }

            public _17_0_2_2_edc0357_1357846886956_698207_20232(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846886956_698207_20232Members();
                init_17_0_2_2_edc0357_1357846886956_698207_20232Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846886956_698207_20232Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter objectToPass = null;

            public Dependency endTimeDependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Dependency finalNode_endTimeDependency = null;

            public void init_17_0_2_2_edc0357_1357846886956_698207_20232Members() {
                try {
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846886956_698207_20232Collections() {
                parameters.add(objectToPass);
            }

            public void init_17_0_2_2_edc0357_1357846886956_698207_20232Dependencies() {
                addDependency(endTime, new Expression<Integer>(new Functions.Plus(new Expression<Integer>(startTime), new Expression<Integer>(duration))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846886962_289294_20263, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(finalNode_endTime, new Expression<Integer>(endTime));
            }

            public void init_17_0_2_2_edc0357_1357846886956_698207_20232Elaborations() {
                init_17_0_2_2_edc0357_1357846886956_698207_20232Dependencies();
            }
        }

        public class _17_0_2_2_edc0357_1357846886956_970455_20233 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846886956_970455_20233() {
                super();
                init_17_0_2_2_edc0357_1357846886956_970455_20233Members();
                init_17_0_2_2_edc0357_1357846886956_970455_20233Collections();
                init_17_0_2_2_edc0357_1357846886956_970455_20233Elaborations();
            }

            public _17_0_2_2_edc0357_1357846886956_970455_20233(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846886956_970455_20233Members();
                init_17_0_2_2_edc0357_1357846886956_970455_20233Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846886956_970455_20233Elaborations();
                fixTimeDependencies();
            }

            public Parameter< Power_System.SignalchangeLoadValue > _17_0_2_2_edc0357_1357846887427_620967_21333 = null;

            public IntegerParameter _17_0_2_2_edc0357_1357846887427_36982_21332 = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846886952_687661_20226_exists = null;

            public Dependency _17_0_2_2_edc0357_1357846887427_620967_21333Dependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887427_36982_21332Dependency = null;

            public Dependency durationDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846886952_687661_20226_existsDependency = null;

            public Effect effect445 = null;

            public Parameter effect445Var = null;

            public ElaborationRule elaborationRule446 = null;

            public void init_17_0_2_2_edc0357_1357846886956_970455_20233Members() {
                try {
                    if (_17_0_2_2_edc0357_1357846887427_620967_21333 == null) _17_0_2_2_edc0357_1357846887427_620967_21333 = new Parameter<Power_System.SignalchangeLoadValue>("_17_0_2_2_edc0357_1357846887427_620967_21333", null, (Power_System.SignalchangeLoadValue) null, this);
                    if (_17_0_2_2_edc0357_1357846887427_36982_21332 == null) _17_0_2_2_edc0357_1357846887427_36982_21332 = new IntegerParameter("_17_0_2_2_edc0357_1357846887427_36982_21332", (Integer) null, this);
                    if (_17_0_2_2_edc0357_1357846886952_687661_20226_exists == null) _17_0_2_2_edc0357_1357846886952_687661_20226_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846886952_687661_20226_exists", (Boolean) false, this);
                    Object effect445VarV = sig_17_0_2_2_edc0357_1357846886961_834620_20259;
                    effect445Var = new Parameter("effect445Var", null, null, this);
                    addDependency(effect445Var, new Expression(effect445VarV));
                    effect445 = new EffectFunction(new EffectFunction(effect445Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { _17_0_2_2_edc0357_1357846887427_36982_21332, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846886956_970455_20233Collections() {
                parameters.add(_17_0_2_2_edc0357_1357846887427_620967_21333);
                parameters.add(_17_0_2_2_edc0357_1357846887427_36982_21332);
                parameters.add(_17_0_2_2_edc0357_1357846886952_687661_20226_exists);
                Set<Effect> effectsForeffect445Var = new TreeSet<Effect>();
                effectsForeffect445Var.add(effect445);
                addEffects((Parameter<?>) effect445Var, effectsForeffect445Var);
            }

            public void init_17_0_2_2_edc0357_1357846886956_970455_20233Dependencies() {
                addDependency(_17_0_2_2_edc0357_1357846887427_620967_21333, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846886961_519045_20258, ClassUtils.getMethodForArgTypes("ObjectFlow<SignalchangeLoadValue>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_2_edc0357_1357846887427_36982_21332, new Expression(new EffectFunction(null, ClassUtils.getMethodForArgTypes("TimeVaryingMap<Integer>", "PowerSystem3", "getValue", java.lang.Integer.class), new Object[] { new Expression<Integer>(startTime) }, new FunctionCall(_17_0_2_2_edc0357_1357846887427_620967_21333, Parameter.class, "getMember", new Object[] { "load__17_0_2_2_edc0357_1357846886587_742419_19875" }))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(_17_0_2_2_edc0357_1357846886952_687661_20226_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886956_698207_20232_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886956_698207_20232_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
            }

            public void init_17_0_2_2_edc0357_1357846886956_970455_20233Elaborations() {
                init_17_0_2_2_edc0357_1357846886956_970455_20233Dependencies();
                Expression<?>[] arguments446 = new Expression<?>[1];
                arguments446[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition446 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846886952_687661_20226_exists);
                elaborationRule446 = addElaborationRule(condition446, _17_0_2_2_edc0357_1357846886581_947081_19867.this, Power._17_0_2_2_edc0357_1357846886581_947081_19867._17_0_2_2_edc0357_1357846886952_687661_20226.class, "P_change_load_CallBehaviorAction_PowerCB", arguments446);
            }
        }

        public class _17_0_2_2_edc0357_1357846886957_101011_20234 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846886957_101011_20234() {
                super();
                init_17_0_2_2_edc0357_1357846886957_101011_20234Members();
                init_17_0_2_2_edc0357_1357846886957_101011_20234Collections();
                init_17_0_2_2_edc0357_1357846886957_101011_20234Elaborations();
            }

            public _17_0_2_2_edc0357_1357846886957_101011_20234(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846886957_101011_20234Members();
                init_17_0_2_2_edc0357_1357846886957_101011_20234Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846886957_101011_20234Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_2_edc0357_1357846886953_875787_20227_exists = null;

            public IntegerParameter _17_0_2_2_edc0357_1357846887428_716951_21334 = null;

            public Parameter< Power_System.SignalchangeGenerationValue > _17_0_2_2_edc0357_1357846887428_704547_21335 = null;

            public Dependency _17_0_2_2_edc0357_1357846886953_875787_20227_existsDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887428_716951_21334Dependency = null;

            public Dependency durationDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887428_704547_21335Dependency = null;

            public Effect effect447 = null;

            public Parameter effect447Var = null;

            public ElaborationRule elaborationRule448 = null;

            public void init_17_0_2_2_edc0357_1357846886957_101011_20234Members() {
                try {
                    if (_17_0_2_2_edc0357_1357846886953_875787_20227_exists == null) _17_0_2_2_edc0357_1357846886953_875787_20227_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846886953_875787_20227_exists", (Boolean) false, this);
                    if (_17_0_2_2_edc0357_1357846887428_716951_21334 == null) _17_0_2_2_edc0357_1357846887428_716951_21334 = new IntegerParameter("_17_0_2_2_edc0357_1357846887428_716951_21334", (Integer) null, this);
                    if (_17_0_2_2_edc0357_1357846887428_704547_21335 == null) _17_0_2_2_edc0357_1357846887428_704547_21335 = new Parameter<Power_System.SignalchangeGenerationValue>("_17_0_2_2_edc0357_1357846887428_704547_21335", null, (Power_System.SignalchangeGenerationValue) null, this);
                    Object effect447VarV = sig_17_0_2_2_edc0357_1357846886962_892721_20261;
                    effect447Var = new Parameter("effect447Var", null, null, this);
                    addDependency(effect447Var, new Expression(effect447VarV));
                    effect447 = new EffectFunction(new EffectFunction(effect447Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { _17_0_2_2_edc0357_1357846887428_716951_21334, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846886957_101011_20234Collections() {
                parameters.add(_17_0_2_2_edc0357_1357846886953_875787_20227_exists);
                parameters.add(_17_0_2_2_edc0357_1357846887428_716951_21334);
                parameters.add(_17_0_2_2_edc0357_1357846887428_704547_21335);
                Set<Effect> effectsForeffect447Var = new TreeSet<Effect>();
                effectsForeffect447Var.add(effect447);
                addEffects((Parameter<?>) effect447Var, effectsForeffect447Var);
            }

            public void init_17_0_2_2_edc0357_1357846886957_101011_20234Dependencies() {
                addDependency(_17_0_2_2_edc0357_1357846886953_875787_20227_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886956_698207_20232_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886956_698207_20232_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(_17_0_2_2_edc0357_1357846887428_716951_21334, new Expression(new EffectFunction(null, ClassUtils.getMethodForArgTypes("TimeVaryingMap<Integer>", "PowerSystem3", "getValue", java.lang.Integer.class), new Object[] { new Expression<Integer>(startTime) }, new FunctionCall(_17_0_2_2_edc0357_1357846887428_704547_21335, Parameter.class, "getMember", new Object[] { "newGenValue__17_0_2_2_edc0357_1357846886586_181042_19874" }))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(_17_0_2_2_edc0357_1357846887428_704547_21335, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846886962_137224_20260, ClassUtils.getMethodForArgTypes("ObjectFlow<SignalchangeGenerationValue>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_2_edc0357_1357846886957_101011_20234Elaborations() {
                init_17_0_2_2_edc0357_1357846886957_101011_20234Dependencies();
                Expression<?>[] arguments448 = new Expression<?>[1];
                arguments448[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition448 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846886953_875787_20227_exists);
                elaborationRule448 = addElaborationRule(condition448, _17_0_2_2_edc0357_1357846886581_947081_19867.this, Power._17_0_2_2_edc0357_1357846886581_947081_19867._17_0_2_2_edc0357_1357846886953_875787_20227.class, "P_change_gen_CallBehaviorAction_PowerCB", arguments448);
            }
        }

        public class _17_0_2_2_edc0357_1357846886957_572229_20235 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846886957_572229_20235() {
                super();
                init_17_0_2_2_edc0357_1357846886957_572229_20235Members();
                init_17_0_2_2_edc0357_1357846886957_572229_20235Collections();
                init_17_0_2_2_edc0357_1357846886957_572229_20235Elaborations();
            }

            public _17_0_2_2_edc0357_1357846886957_572229_20235(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846886957_572229_20235Members();
                init_17_0_2_2_edc0357_1357846886957_572229_20235Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846886957_572229_20235Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter objectToPass = null;

            public Dependency finalNode_startTimeDependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Effect effect449 = null;

            public Parameter effect449Var = null;

            public void init_17_0_2_2_edc0357_1357846886957_572229_20235Members() {
                try {
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect449VarV = sig_17_0_2_2_edc0357_1357846886962_289294_20263;
                    effect449Var = new Parameter("effect449Var", null, null, this);
                    addDependency(effect449Var, new Expression(effect449VarV));
                    effect449 = new EffectFunction(new EffectFunction(effect449Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846886957_572229_20235Collections() {
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect449Var = new TreeSet<Effect>();
                effectsForeffect449Var.add(effect449);
                addEffects((Parameter<?>) effect449Var, effectsForeffect449Var);
            }

            public void init_17_0_2_2_edc0357_1357846886957_572229_20235Dependencies() {
                addDependency(finalNode_startTime, new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))));
                addDependency(duration, new Expression<Integer>(86400));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846886962_562181_20262, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_2_edc0357_1357846886957_572229_20235Elaborations() {
                init_17_0_2_2_edc0357_1357846886957_572229_20235Dependencies();
            }
        }

        public class _17_0_2_2_edc0357_1357846886958_585120_20236 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846886958_585120_20236() {
                super();
                init_17_0_2_2_edc0357_1357846886958_585120_20236Members();
                init_17_0_2_2_edc0357_1357846886958_585120_20236Collections();
                init_17_0_2_2_edc0357_1357846886958_585120_20236Elaborations();
            }

            public _17_0_2_2_edc0357_1357846886958_585120_20236(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846886958_585120_20236Members();
                init_17_0_2_2_edc0357_1357846886958_585120_20236Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846886958_585120_20236Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_2_edc0357_1357846886955_763631_20231_exists = null;

            public BooleanParameter objectToPass = null;

            public Dependency durationDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846886955_763631_20231_existsDependency = null;

            public Dependency objectToPassDependency = null;

            public Effect effect450 = null;

            public Parameter effect450Var = null;

            public ElaborationRule elaborationRule451 = null;

            public void init_17_0_2_2_edc0357_1357846886958_585120_20236Members() {
                try {
                    if (_17_0_2_2_edc0357_1357846886955_763631_20231_exists == null) _17_0_2_2_edc0357_1357846886955_763631_20231_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846886955_763631_20231_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect450VarV = sig_17_0_2_2_edc0357_1357846886962_700432_20265;
                    effect450Var = new Parameter("effect450Var", null, null, this);
                    addDependency(effect450Var, new Expression(effect450VarV));
                    effect450 = new EffectFunction(new EffectFunction(effect450Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846886958_585120_20236Collections() {
                parameters.add(_17_0_2_2_edc0357_1357846886955_763631_20231_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect450Var = new TreeSet<Effect>();
                effectsForeffect450Var.add(effect450);
                addEffects((Parameter<?>) effect450Var, effectsForeffect450Var);
            }

            public void init_17_0_2_2_edc0357_1357846886958_585120_20236Dependencies() {
                addDependency(duration, new Expression<Integer>(300));
                addDependency(_17_0_2_2_edc0357_1357846886955_763631_20231_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886956_698207_20232_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886956_698207_20232_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846886962_832634_20264, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_2_edc0357_1357846886958_585120_20236Elaborations() {
                init_17_0_2_2_edc0357_1357846886958_585120_20236Dependencies();
                Expression<?>[] arguments451 = new Expression<?>[2];
                arguments451[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                arguments451[1] = new Expression<ObjectFlow<Boolean>>(sig_17_0_2_2_edc0357_1357846886962_700432_20265);
                Expression<Boolean> condition451 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846886955_763631_20231_exists);
                elaborationRule451 = addElaborationRule(condition451, _17_0_2_2_edc0357_1357846886581_947081_19867.this, Power._17_0_2_2_edc0357_1357846886581_947081_19867._17_0_2_2_edc0357_1357846886955_763631_20231.class, "P_gen_merge_MergeNode_PowerCB", arguments451);
            }
        }

        public class _17_0_2_2_edc0357_1358277118980_372138_38646 extends DurativeEvent {

            public _17_0_2_2_edc0357_1358277118980_372138_38646() {
                super();
                init_17_0_2_2_edc0357_1358277118980_372138_38646Members();
                init_17_0_2_2_edc0357_1358277118980_372138_38646Collections();
                init_17_0_2_2_edc0357_1358277118980_372138_38646Elaborations();
            }

            public _17_0_2_2_edc0357_1358277118980_372138_38646(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1358277118980_372138_38646Members();
                init_17_0_2_2_edc0357_1358277118980_372138_38646Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1358277118980_372138_38646Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_2_edc0357_1358277792323_793810_38841_exists = null;

            public BooleanParameter objectToPass = null;

            public Dependency _17_0_2_2_edc0357_1358277792323_793810_38841_existsDependency = null;

            public Dependency objectToPassDependency = null;

            public Effect effect452 = null;

            public Parameter effect452Var = null;

            public ElaborationRule elaborationRule453 = null;

            public ElaborationRule elaborationRule454 = null;

            public void init_17_0_2_2_edc0357_1358277118980_372138_38646Members() {
                try {
                    if (_17_0_2_2_edc0357_1358277792323_793810_38841_exists == null) _17_0_2_2_edc0357_1358277792323_793810_38841_exists = new BooleanParameter("_17_0_2_2_edc0357_1358277792323_793810_38841_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect452VarV = sig_17_0_2_2_edc0357_1358277824538_406714_38857;
                    effect452Var = new Parameter("effect452Var", null, null, this);
                    addDependency(effect452Var, new Expression(effect452VarV));
                    effect452 = new EffectFunction(new EffectFunction(effect452Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1358277118980_372138_38646Collections() {
                parameters.add(_17_0_2_2_edc0357_1358277792323_793810_38841_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect452Var = new TreeSet<Effect>();
                effectsForeffect452Var.add(effect452);
                addEffects((Parameter<?>) effect452Var, effectsForeffect452Var);
            }

            public void init_17_0_2_2_edc0357_1358277118980_372138_38646Dependencies() {
                addDependency(_17_0_2_2_edc0357_1358277792323_793810_38841_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886956_698207_20232_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886956_698207_20232_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1358277789520_388247_38837, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_2_edc0357_1358277118980_372138_38646Elaborations() {
                init_17_0_2_2_edc0357_1358277118980_372138_38646Dependencies();
                Expression<?>[] arguments453 = new Expression<?>[2];
                arguments453[0] = new Expression<Integer>(startTime);
                arguments453[1] = new Expression<DurativeEvent>(this);
                Expression<Boolean> condition453 = new Expression<Boolean>(true);
                elaborationRule453 = addElaborationRule(condition453, Power.this, Power._17_0_2_2_edc0357_1358276938198_952298_38499.class, "monitorFeeders_Activity_Power", arguments453);
                Expression<?>[] arguments454 = new Expression<?>[1];
                arguments454[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition454 = new Expression<Boolean>(_17_0_2_2_edc0357_1358277792323_793810_38841_exists);
                elaborationRule454 = addElaborationRule(condition454, _17_0_2_2_edc0357_1357846886581_947081_19867.this, Power._17_0_2_2_edc0357_1357846886581_947081_19867._17_0_2_2_edc0357_1358277792323_793810_38841.class, "P_wait_feeders_AcceptEventAction_PowerCB", arguments454);
            }
        }

        public class _17_0_2_2_edc0357_1358277779552_2828_38826 extends DurativeEvent {

            public _17_0_2_2_edc0357_1358277779552_2828_38826() {
                super();
                init_17_0_2_2_edc0357_1358277779552_2828_38826Members();
                init_17_0_2_2_edc0357_1358277779552_2828_38826Collections();
                init_17_0_2_2_edc0357_1358277779552_2828_38826Elaborations();
            }

            public _17_0_2_2_edc0357_1358277779552_2828_38826(Expression<Integer> startTime, Expression<ObjectFlow<Boolean>> receiveThis) {
                super();
                init_17_0_2_2_edc0357_1358277779552_2828_38826Members();
                init_17_0_2_2_edc0357_1358277779552_2828_38826Collections();
                addDependency(this.startTime, startTime);
                addDependency(this.receiveThis, receiveThis);
                init_17_0_2_2_edc0357_1358277779552_2828_38826Elaborations();
                fixTimeDependencies();
            }

            public Parameter< ObjectFlow<Boolean> > receiveThis = null;

            public BooleanParameter _17_0_2_2_edc0357_1358277118980_372138_38646_exists = null;

            public BooleanParameter objectToPass = null;

            public Dependency _17_0_2_2_edc0357_1358277118980_372138_38646_existsDependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Effect effect455 = null;

            public Parameter effect455Var = null;

            public ElaborationRule elaborationRule456 = null;

            public void init_17_0_2_2_edc0357_1358277779552_2828_38826Members() {
                try {
                    if (receiveThis == null) receiveThis = new Parameter<ObjectFlow<Boolean>>("receiveThis", null, (ObjectFlow<Boolean>) null, this);
                    if (_17_0_2_2_edc0357_1358277118980_372138_38646_exists == null) _17_0_2_2_edc0357_1358277118980_372138_38646_exists = new BooleanParameter("_17_0_2_2_edc0357_1358277118980_372138_38646_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect455VarV = sig_17_0_2_2_edc0357_1358277789520_388247_38837;
                    effect455Var = new Parameter("effect455Var", null, null, this);
                    addDependency(effect455Var, new Expression(effect455VarV));
                    effect455 = new EffectFunction(new EffectFunction(effect455Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1358277779552_2828_38826Collections() {
                parameters.add(receiveThis);
                parameters.add(_17_0_2_2_edc0357_1358277118980_372138_38646_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect455Var = new TreeSet<Effect>();
                effectsForeffect455Var.add(effect455);
                addEffects((Parameter<?>) effect455Var, effectsForeffect455Var);
            }

            public void init_17_0_2_2_edc0357_1358277779552_2828_38826Dependencies() {
                addDependency(_17_0_2_2_edc0357_1358277118980_372138_38646_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886956_698207_20232_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886956_698207_20232_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression(new EffectFunction(receiveThis, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_2_edc0357_1358277779552_2828_38826Elaborations() {
                init_17_0_2_2_edc0357_1358277779552_2828_38826Dependencies();
                Expression<?>[] arguments456 = new Expression<?>[1];
                arguments456[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition456 = new Expression<Boolean>(_17_0_2_2_edc0357_1358277118980_372138_38646_exists);
                elaborationRule456 = addElaborationRule(condition456, _17_0_2_2_edc0357_1357846886581_947081_19867.this, Power._17_0_2_2_edc0357_1357846886581_947081_19867._17_0_2_2_edc0357_1358277118980_372138_38646.class, "P_mon_feed_CallBehaviorAction_PowerCB", arguments456);
            }
        }

        public class _17_0_2_2_edc0357_1358277792323_793810_38841 extends DurativeEvent {

            public _17_0_2_2_edc0357_1358277792323_793810_38841() {
                super();
                init_17_0_2_2_edc0357_1358277792323_793810_38841Members();
                init_17_0_2_2_edc0357_1358277792323_793810_38841Collections();
                init_17_0_2_2_edc0357_1358277792323_793810_38841Elaborations();
            }

            public _17_0_2_2_edc0357_1358277792323_793810_38841(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1358277792323_793810_38841Members();
                init_17_0_2_2_edc0357_1358277792323_793810_38841Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1358277792323_793810_38841Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_2_edc0357_1358277779552_2828_38826_exists = null;

            public BooleanParameter objectToPass = null;

            public Dependency _17_0_2_2_edc0357_1358277779552_2828_38826_existsDependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Effect effect457 = null;

            public Parameter effect457Var = null;

            public ElaborationRule elaborationRule458 = null;

            public void init_17_0_2_2_edc0357_1358277792323_793810_38841Members() {
                try {
                    if (_17_0_2_2_edc0357_1358277779552_2828_38826_exists == null) _17_0_2_2_edc0357_1358277779552_2828_38826_exists = new BooleanParameter("_17_0_2_2_edc0357_1358277779552_2828_38826_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect457VarV = sig_17_0_2_2_edc0357_1358277828348_426245_38862;
                    effect457Var = new Parameter("effect457Var", null, null, this);
                    addDependency(effect457Var, new Expression(effect457VarV));
                    effect457 = new EffectFunction(new EffectFunction(effect457Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1358277792323_793810_38841Collections() {
                parameters.add(_17_0_2_2_edc0357_1358277779552_2828_38826_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect457Var = new TreeSet<Effect>();
                effectsForeffect457Var.add(effect457);
                addEffects((Parameter<?>) effect457Var, effectsForeffect457Var);
            }

            public void init_17_0_2_2_edc0357_1358277792323_793810_38841Dependencies() {
                addDependency(_17_0_2_2_edc0357_1358277779552_2828_38826_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886956_698207_20232_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846886956_698207_20232_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(300));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1358277824538_406714_38857, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_2_edc0357_1358277792323_793810_38841Elaborations() {
                init_17_0_2_2_edc0357_1358277792323_793810_38841Dependencies();
                Expression<?>[] arguments458 = new Expression<?>[2];
                arguments458[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                arguments458[1] = new Expression<ObjectFlow<Boolean>>(sig_17_0_2_2_edc0357_1358277828348_426245_38862);
                Expression<Boolean> condition458 = new Expression<Boolean>(_17_0_2_2_edc0357_1358277779552_2828_38826_exists);
                elaborationRule458 = addElaborationRule(condition458, _17_0_2_2_edc0357_1357846886581_947081_19867.this, Power._17_0_2_2_edc0357_1357846886581_947081_19867._17_0_2_2_edc0357_1358277779552_2828_38826.class, "P_merge_feeders_MergeNode_PowerCB", arguments458);
            }
        }

        public _17_0_2_2_edc0357_1357846886581_947081_19867(Expression<Integer> startTime) {
            super();
            init_17_0_2_2_edc0357_1357846886581_947081_19867Members();
            init_17_0_2_2_edc0357_1357846886581_947081_19867Collections();
            addDependency(this.startTime, startTime);
            init_17_0_2_2_edc0357_1357846886581_947081_19867Elaborations();
            fixTimeDependencies();
        }

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_2_edc0357_1357846886962_832634_20264 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_2_edc0357_1357846886962_289294_20263 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_2_2_edc0357_1357846886962_892721_20261 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_2_edc0357_1357846886962_562181_20262 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_2_edc0357_1357846886961_679328_20251 = null;

        public BooleanParameter _17_0_2_2_edc0357_1357846886956_698207_20232_exists = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_2_edc0357_1358277828348_426245_38862 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_2_edc0357_1357846886962_700432_20265 = null;

        public Parameter< ObjectFlow<Power_System.SignalchangeLoadValue> > sig_17_0_2_2_edc0357_1357846886463_894586_19728 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_2_edc0357_1357846886962_974598_20266 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_2_edc0357_1357846886962_893992_20268 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_2_edc0357_1357846886961_66104_20255 = null;

        public Parameter< ObjectFlow<Power_System.SignalchangeGenerationValue> > sig_17_0_2_2_edc0357_1357846886962_137224_20260 = null;

        public Parameter< ObjectFlow<Power_System.SignalchangeGenerationValue> > sig_17_0_2_2_edc0357_1357846886462_463424_19727 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_2_edc0357_1358277824538_406714_38857 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_2_edc0357_1357846886961_840973_20256 = null;

        public IntegerParameter finalNode_endTime = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_2_2_edc0357_1357846886961_834620_20259 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_2_edc0357_1357846886961_875480_20257 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_2_edc0357_1357846886960_341556_20249 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_2_edc0357_1358277784216_673532_38832 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_2_edc0357_1357846886961_494059_20254 = null;

        public IntegerParameter finalNode_startTime = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_2_edc0357_1358277789520_388247_38837 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_2_edc0357_1357846886961_263446_20250 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_2_edc0357_1357846886961_125803_20253 = null;

        public Parameter< ObjectFlow<Power_System.SignalchangeLoadValue> > sig_17_0_2_2_edc0357_1357846886961_519045_20258 = null;

        public Dependency _17_0_2_2_edc0357_1357846886956_698207_20232_existsDependency = null;

        public Dependency endTimeDependency = null;

        public ElaborationRule elaborationRule409 = null;

        public ElaborationRule elaborationRule410 = null;

        public void init_17_0_2_2_edc0357_1357846886581_947081_19867Members() {
            try {
                if (sig_17_0_2_2_edc0357_1357846886962_832634_20264 == null) sig_17_0_2_2_edc0357_1357846886962_832634_20264 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_2_edc0357_1357846886962_832634_20264", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846886962_832634_20264" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846886962_289294_20263 == null) sig_17_0_2_2_edc0357_1357846886962_289294_20263 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_2_edc0357_1357846886962_289294_20263", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846886962_289294_20263" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846886962_892721_20261 == null) sig_17_0_2_2_edc0357_1357846886962_892721_20261 = new Parameter<ObjectFlow<Integer>>("sig_17_0_2_2_edc0357_1357846886962_892721_20261", null, (ObjectFlow<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846886962_892721_20261" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846886962_562181_20262 == null) sig_17_0_2_2_edc0357_1357846886962_562181_20262 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_2_edc0357_1357846886962_562181_20262", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846886962_562181_20262" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846886961_679328_20251 == null) sig_17_0_2_2_edc0357_1357846886961_679328_20251 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_2_edc0357_1357846886961_679328_20251", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846886961_679328_20251" })).evaluate(true), this);
                if (_17_0_2_2_edc0357_1357846886956_698207_20232_exists == null) _17_0_2_2_edc0357_1357846886956_698207_20232_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846886956_698207_20232_exists", (Boolean) false, this);
                if (sig_17_0_2_2_edc0357_1358277828348_426245_38862 == null) sig_17_0_2_2_edc0357_1358277828348_426245_38862 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_2_edc0357_1358277828348_426245_38862", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1358277828348_426245_38862" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846886962_700432_20265 == null) sig_17_0_2_2_edc0357_1357846886962_700432_20265 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_2_edc0357_1357846886962_700432_20265", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846886962_700432_20265" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846886463_894586_19728 == null) sig_17_0_2_2_edc0357_1357846886463_894586_19728 = new Parameter<ObjectFlow<Power_System.SignalchangeLoadValue>>("sig_17_0_2_2_edc0357_1357846886463_894586_19728", null, (ObjectFlow<Power_System.SignalchangeLoadValue>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846886463_894586_19728" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846886962_974598_20266 == null) sig_17_0_2_2_edc0357_1357846886962_974598_20266 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_2_edc0357_1357846886962_974598_20266", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846886962_974598_20266" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846886962_893992_20268 == null) sig_17_0_2_2_edc0357_1357846886962_893992_20268 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_2_edc0357_1357846886962_893992_20268", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846886962_893992_20268" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846886961_66104_20255 == null) sig_17_0_2_2_edc0357_1357846886961_66104_20255 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_2_edc0357_1357846886961_66104_20255", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846886961_66104_20255" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846886962_137224_20260 == null) sig_17_0_2_2_edc0357_1357846886962_137224_20260 = new Parameter<ObjectFlow<Power_System.SignalchangeGenerationValue>>("sig_17_0_2_2_edc0357_1357846886962_137224_20260", null, (ObjectFlow<Power_System.SignalchangeGenerationValue>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846886962_137224_20260" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846886462_463424_19727 == null) sig_17_0_2_2_edc0357_1357846886462_463424_19727 = new Parameter<ObjectFlow<Power_System.SignalchangeGenerationValue>>("sig_17_0_2_2_edc0357_1357846886462_463424_19727", null, (ObjectFlow<Power_System.SignalchangeGenerationValue>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846886462_463424_19727" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1358277824538_406714_38857 == null) sig_17_0_2_2_edc0357_1358277824538_406714_38857 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_2_edc0357_1358277824538_406714_38857", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1358277824538_406714_38857" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846886961_840973_20256 == null) sig_17_0_2_2_edc0357_1357846886961_840973_20256 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_2_edc0357_1357846886961_840973_20256", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846886961_840973_20256" })).evaluate(true), this);
                if (finalNode_endTime == null) finalNode_endTime = new IntegerParameter("finalNode_endTime", (Integer) null, this);
                if (sig_17_0_2_2_edc0357_1357846886961_834620_20259 == null) sig_17_0_2_2_edc0357_1357846886961_834620_20259 = new Parameter<ObjectFlow<Integer>>("sig_17_0_2_2_edc0357_1357846886961_834620_20259", null, (ObjectFlow<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846886961_834620_20259" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846886961_875480_20257 == null) sig_17_0_2_2_edc0357_1357846886961_875480_20257 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_2_edc0357_1357846886961_875480_20257", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846886961_875480_20257" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846886960_341556_20249 == null) sig_17_0_2_2_edc0357_1357846886960_341556_20249 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_2_edc0357_1357846886960_341556_20249", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846886960_341556_20249" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1358277784216_673532_38832 == null) sig_17_0_2_2_edc0357_1358277784216_673532_38832 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_2_edc0357_1358277784216_673532_38832", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1358277784216_673532_38832" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846886961_494059_20254 == null) sig_17_0_2_2_edc0357_1357846886961_494059_20254 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_2_edc0357_1357846886961_494059_20254", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846886961_494059_20254" })).evaluate(true), this);
                if (finalNode_startTime == null) finalNode_startTime = new IntegerParameter("finalNode_startTime", (Integer) null, this);
                if (sig_17_0_2_2_edc0357_1358277789520_388247_38837 == null) sig_17_0_2_2_edc0357_1358277789520_388247_38837 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_2_edc0357_1358277789520_388247_38837", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1358277789520_388247_38837" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846886961_263446_20250 == null) sig_17_0_2_2_edc0357_1357846886961_263446_20250 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_2_edc0357_1357846886961_263446_20250", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846886961_263446_20250" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846886961_125803_20253 == null) sig_17_0_2_2_edc0357_1357846886961_125803_20253 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_2_edc0357_1357846886961_125803_20253", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846886961_125803_20253" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846886961_519045_20258 == null) sig_17_0_2_2_edc0357_1357846886961_519045_20258 = new Parameter<ObjectFlow<Power_System.SignalchangeLoadValue>>("sig_17_0_2_2_edc0357_1357846886961_519045_20258", null, (ObjectFlow<Power_System.SignalchangeLoadValue>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846886961_519045_20258" })).evaluate(true), this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void init_17_0_2_2_edc0357_1357846886581_947081_19867Collections() {
            parameters.add(sig_17_0_2_2_edc0357_1357846886962_832634_20264);
            parameters.add(sig_17_0_2_2_edc0357_1357846886962_289294_20263);
            parameters.add(sig_17_0_2_2_edc0357_1357846886962_892721_20261);
            parameters.add(sig_17_0_2_2_edc0357_1357846886962_562181_20262);
            parameters.add(sig_17_0_2_2_edc0357_1357846886961_679328_20251);
            parameters.add(_17_0_2_2_edc0357_1357846886956_698207_20232_exists);
            parameters.add(sig_17_0_2_2_edc0357_1358277828348_426245_38862);
            parameters.add(sig_17_0_2_2_edc0357_1357846886962_700432_20265);
            parameters.add(sig_17_0_2_2_edc0357_1357846886463_894586_19728);
            parameters.add(sig_17_0_2_2_edc0357_1357846886962_974598_20266);
            parameters.add(sig_17_0_2_2_edc0357_1357846886962_893992_20268);
            parameters.add(sig_17_0_2_2_edc0357_1357846886961_66104_20255);
            parameters.add(sig_17_0_2_2_edc0357_1357846886962_137224_20260);
            parameters.add(sig_17_0_2_2_edc0357_1357846886462_463424_19727);
            parameters.add(sig_17_0_2_2_edc0357_1358277824538_406714_38857);
            parameters.add(sig_17_0_2_2_edc0357_1357846886961_840973_20256);
            parameters.add(finalNode_endTime);
            parameters.add(sig_17_0_2_2_edc0357_1357846886961_834620_20259);
            parameters.add(sig_17_0_2_2_edc0357_1357846886961_875480_20257);
            parameters.add(sig_17_0_2_2_edc0357_1357846886960_341556_20249);
            parameters.add(sig_17_0_2_2_edc0357_1358277784216_673532_38832);
            parameters.add(sig_17_0_2_2_edc0357_1357846886961_494059_20254);
            parameters.add(finalNode_startTime);
            parameters.add(sig_17_0_2_2_edc0357_1358277789520_388247_38837);
            parameters.add(sig_17_0_2_2_edc0357_1357846886961_263446_20250);
            parameters.add(sig_17_0_2_2_edc0357_1357846886961_125803_20253);
            parameters.add(sig_17_0_2_2_edc0357_1357846886961_519045_20258);
        }

        public void init_17_0_2_2_edc0357_1357846886581_947081_19867Dependencies() {
            addDependency(_17_0_2_2_edc0357_1357846886956_698207_20232_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.NotEquals(new Expression<Integer>(finalNode_startTime), null)), new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846886962_289294_20263, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(new Functions.Minus(new Expression<Integer>(finalNode_startTime), new Expression<Integer>(1))) })))));
            addDependency(endTime, new Expression<Integer>(finalNode_endTime));
        }

        public void init_17_0_2_2_edc0357_1357846886581_947081_19867Elaborations() {
            init_17_0_2_2_edc0357_1357846886581_947081_19867Dependencies();
            Expression<?>[] arguments409 = new Expression<?>[1];
            arguments409[0] = new Expression<Integer>(startTime);
            Expression<Boolean> condition409 = new Expression<Boolean>(true);
            elaborationRule409 = addElaborationRule(condition409, _17_0_2_2_edc0357_1357846886581_947081_19867.this, Power._17_0_2_2_edc0357_1357846886581_947081_19867._17_0_2_2_edc0357_1357846886950_513623_20221.class, "P_start_InitialNode_PowerCB", arguments409);
            Expression<?>[] arguments410 = new Expression<?>[1];
            arguments410[0] = new Expression<Integer>(finalNode_startTime);
            Expression<Boolean> condition410 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846886956_698207_20232_exists);
            elaborationRule410 = addElaborationRule(condition410, _17_0_2_2_edc0357_1357846886581_947081_19867.this, Power._17_0_2_2_edc0357_1357846886581_947081_19867._17_0_2_2_edc0357_1357846886956_698207_20232.class, "P_CBFinal_ActivityFinalNode_PowerCB", arguments410);
        }
    }

    public class _17_0_2_2_edc0357_1358276938198_952298_38499 extends DurativeEvent {

        public _17_0_2_2_edc0357_1358276938198_952298_38499() {
            super();
            init_17_0_2_2_edc0357_1358276938198_952298_38499Members();
            init_17_0_2_2_edc0357_1358276938198_952298_38499Collections();
            init_17_0_2_2_edc0357_1358276938198_952298_38499Elaborations();
        }

        public class _17_0_2_2_edc0357_1358276938633_988128_38521 extends DurativeEvent {

            public _17_0_2_2_edc0357_1358276938633_988128_38521() {
                super();
                init_17_0_2_2_edc0357_1358276938633_988128_38521Members();
                init_17_0_2_2_edc0357_1358276938633_988128_38521Collections();
                init_17_0_2_2_edc0357_1358276938633_988128_38521Elaborations();
            }

            public _17_0_2_2_edc0357_1358276938633_988128_38521(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1358276938633_988128_38521Members();
                init_17_0_2_2_edc0357_1358276938633_988128_38521Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1358276938633_988128_38521Elaborations();
                fixTimeDependencies();
            }

            public Parameter< Power > _17_0_2_2_edc0357_1358276938817_48300_38542 = null;

            public BooleanParameter _17_0_2_2_edc0357_1358276938695_85998_38528_exists = null;

            public BooleanParameter objectToPass = null;

            public Dependency _17_0_2_2_edc0357_1358276938695_85998_38528_existsDependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Effect effect461 = null;

            public Parameter effect461Var = null;

            public ElaborationRule elaborationRule462 = null;

            public void init_17_0_2_2_edc0357_1358276938633_988128_38521Members() {
                try {
                    if (_17_0_2_2_edc0357_1358276938817_48300_38542 == null) _17_0_2_2_edc0357_1358276938817_48300_38542 = new Parameter<Power>("_17_0_2_2_edc0357_1358276938817_48300_38542", null, (Power) Power.this, this);
                    if (_17_0_2_2_edc0357_1358276938695_85998_38528_exists == null) _17_0_2_2_edc0357_1358276938695_85998_38528_exists = new BooleanParameter("_17_0_2_2_edc0357_1358276938695_85998_38528_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect461VarV = sig_17_0_2_2_edc0357_1358276938724_206943_38533;
                    effect461Var = new Parameter("effect461Var", null, null, this);
                    addDependency(effect461Var, new Expression(effect461VarV));
                    effect461 = new EffectFunction(new EffectFunction(effect461Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Power>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { _17_0_2_2_edc0357_1358276938817_48300_38542, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1358276938633_988128_38521Collections() {
                parameters.add(_17_0_2_2_edc0357_1358276938817_48300_38542);
                parameters.add(_17_0_2_2_edc0357_1358276938695_85998_38528_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect461Var = new TreeSet<Effect>();
                effectsForeffect461Var.add(effect461);
                addEffects((Parameter<?>) effect461Var, effectsForeffect461Var);
            }

            public void init_17_0_2_2_edc0357_1358276938633_988128_38521Dependencies() {
                addDependency(_17_0_2_2_edc0357_1358276938695_85998_38528_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1358276938636_100276_38523_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1358276938636_100276_38523_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1358276938708_246458_38530, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_2_edc0357_1358276938633_988128_38521Elaborations() {
                init_17_0_2_2_edc0357_1358276938633_988128_38521Dependencies();
                Expression<?>[] arguments462 = new Expression<?>[1];
                arguments462[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition462 = new Expression<Boolean>(_17_0_2_2_edc0357_1358276938695_85998_38528_exists);
                elaborationRule462 = addElaborationRule(condition462, _17_0_2_2_edc0357_1358276938198_952298_38499.this, Power._17_0_2_2_edc0357_1358276938198_952298_38499._17_0_2_2_edc0357_1358276938695_85998_38528.class, "PGen_fork_ForkNode_monitorFeeders", arguments462);
            }
        }

        public class _17_0_2_2_edc0357_1358276938635_8125_38522 extends DurativeEvent {

            public _17_0_2_2_edc0357_1358276938635_8125_38522() {
                super();
                init_17_0_2_2_edc0357_1358276938635_8125_38522Members();
                init_17_0_2_2_edc0357_1358276938635_8125_38522Collections();
                init_17_0_2_2_edc0357_1358276938635_8125_38522Elaborations();
            }

            public _17_0_2_2_edc0357_1358276938635_8125_38522(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1358276938635_8125_38522Members();
                init_17_0_2_2_edc0357_1358276938635_8125_38522Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1358276938635_8125_38522Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_2_edc0357_1358276938633_988128_38521_exists = null;

            public BooleanParameter objectToPass = null;

            public Dependency _17_0_2_2_edc0357_1358276938633_988128_38521_existsDependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Effect effect463 = null;

            public Parameter effect463Var = null;

            public ElaborationRule elaborationRule464 = null;

            public void init_17_0_2_2_edc0357_1358276938635_8125_38522Members() {
                try {
                    if (_17_0_2_2_edc0357_1358276938633_988128_38521_exists == null) _17_0_2_2_edc0357_1358276938633_988128_38521_exists = new BooleanParameter("_17_0_2_2_edc0357_1358276938633_988128_38521_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect463VarV = sig_17_0_2_2_edc0357_1358276938708_246458_38530;
                    effect463Var = new Parameter("effect463Var", null, null, this);
                    addDependency(effect463Var, new Expression(effect463VarV));
                    effect463 = new EffectFunction(new EffectFunction(effect463Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1358276938635_8125_38522Collections() {
                parameters.add(_17_0_2_2_edc0357_1358276938633_988128_38521_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect463Var = new TreeSet<Effect>();
                effectsForeffect463Var.add(effect463);
                addEffects((Parameter<?>) effect463Var, effectsForeffect463Var);
            }

            public void init_17_0_2_2_edc0357_1358276938635_8125_38522Dependencies() {
                addDependency(_17_0_2_2_edc0357_1358276938633_988128_38521_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1358276938636_100276_38523_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1358276938636_100276_38523_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(true));
            }

            public void init_17_0_2_2_edc0357_1358276938635_8125_38522Elaborations() {
                init_17_0_2_2_edc0357_1358276938635_8125_38522Dependencies();
                Expression<?>[] arguments464 = new Expression<?>[1];
                arguments464[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition464 = new Expression<Boolean>(_17_0_2_2_edc0357_1358276938633_988128_38521_exists);
                elaborationRule464 = addElaborationRule(condition464, _17_0_2_2_edc0357_1358276938198_952298_38499.this, Power._17_0_2_2_edc0357_1358276938198_952298_38499._17_0_2_2_edc0357_1358276938633_988128_38521.class, "PGen_readself_ReadSelfAction_monitorFeeders", arguments464);
            }
        }

        public class _17_0_2_2_edc0357_1358276938636_100276_38523 extends DurativeEvent {

            public _17_0_2_2_edc0357_1358276938636_100276_38523() {
                super();
                init_17_0_2_2_edc0357_1358276938636_100276_38523Members();
                init_17_0_2_2_edc0357_1358276938636_100276_38523Collections();
                init_17_0_2_2_edc0357_1358276938636_100276_38523Elaborations();
            }

            public _17_0_2_2_edc0357_1358276938636_100276_38523(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1358276938636_100276_38523Members();
                init_17_0_2_2_edc0357_1358276938636_100276_38523Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1358276938636_100276_38523Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter objectToPass = null;

            public Dependency endTimeDependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Dependency finalNode_endTimeDependency = null;

            public void init_17_0_2_2_edc0357_1358276938636_100276_38523Members() {
                try {
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1358276938636_100276_38523Collections() {
                parameters.add(objectToPass);
            }

            public void init_17_0_2_2_edc0357_1358276938636_100276_38523Dependencies() {
                addDependency(endTime, new Expression<Integer>(new Functions.Plus(new Expression<Integer>(startTime), new Expression<Integer>(duration))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1358276938724_619501_38540, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(finalNode_endTime, new Expression<Integer>(endTime));
            }

            public void init_17_0_2_2_edc0357_1358276938636_100276_38523Elaborations() {
                init_17_0_2_2_edc0357_1358276938636_100276_38523Dependencies();
            }
        }

        public class _17_0_2_2_edc0357_1358276938663_447681_38525 extends DurativeEvent {

            public _17_0_2_2_edc0357_1358276938663_447681_38525() {
                super();
                init_17_0_2_2_edc0357_1358276938663_447681_38525Members();
                init_17_0_2_2_edc0357_1358276938663_447681_38525Collections();
                init_17_0_2_2_edc0357_1358276938663_447681_38525Elaborations();
            }

            public _17_0_2_2_edc0357_1358276938663_447681_38525(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1358276938663_447681_38525Members();
                init_17_0_2_2_edc0357_1358276938663_447681_38525Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1358276938663_447681_38525Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter addToDecider__17_0_2_2_edc0357_1358276938694_797479_38527 = null;

            public BooleanParameter _17_0_2_2_edc0357_1358276938694_797479_38527_exists = null;

            public IntegerParameter _17_0_2_2_edc0357_1358276938898_934226_38545 = null;

            public IntegerParameter myDeciderID_decider_17_0_2_2_edc0357_1358276938694_797479_38527 = null;

            public Parameter< Power > _17_0_2_2_edc0357_1358276938899_878275_38546 = null;

            public Dependency addToDecider__17_0_2_2_edc0357_1358276938694_797479_38527Dependency = null;

            public Dependency _17_0_2_2_edc0357_1358276938694_797479_38527_existsDependency = null;

            public Dependency _17_0_2_2_edc0357_1358276938898_934226_38545Dependency = null;

            public Dependency durationDependency = null;

            public Dependency myDeciderID_decider_17_0_2_2_edc0357_1358276938694_797479_38527Dependency = null;

            public Dependency _17_0_2_2_edc0357_1358276938899_878275_38546Dependency = null;

            public Effect effect465 = null;

            public Parameter effect465Var = null;

            public Effect effect466 = null;

            public Parameter effect466Var = null;

            public ElaborationRule elaborationRule467 = null;

            public void init_17_0_2_2_edc0357_1358276938663_447681_38525Members() {
                try {
                    if (addToDecider__17_0_2_2_edc0357_1358276938694_797479_38527 == null) addToDecider__17_0_2_2_edc0357_1358276938694_797479_38527 = new BooleanParameter("addToDecider__17_0_2_2_edc0357_1358276938694_797479_38527", (Boolean) false, this);
                    if (_17_0_2_2_edc0357_1358276938694_797479_38527_exists == null) _17_0_2_2_edc0357_1358276938694_797479_38527_exists = new BooleanParameter("_17_0_2_2_edc0357_1358276938694_797479_38527_exists", (Boolean) false, this);
                    if (_17_0_2_2_edc0357_1358276938898_934226_38545 == null) _17_0_2_2_edc0357_1358276938898_934226_38545 = new IntegerParameter("_17_0_2_2_edc0357_1358276938898_934226_38545", (Integer) null, this);
                    if (myDeciderID_decider_17_0_2_2_edc0357_1358276938694_797479_38527 == null) myDeciderID_decider_17_0_2_2_edc0357_1358276938694_797479_38527 = new IntegerParameter("myDeciderID_decider_17_0_2_2_edc0357_1358276938694_797479_38527", (Integer) 1, this);
                    if (_17_0_2_2_edc0357_1358276938899_878275_38546 == null) _17_0_2_2_edc0357_1358276938899_878275_38546 = new Parameter<Power>("_17_0_2_2_edc0357_1358276938899_878275_38546", null, (Power) null, this);
                    Object effect465VarV = sig_17_0_2_2_edc0357_1358276938718_266470_38531;
                    effect465Var = new Parameter("effect465Var", null, null, this);
                    addDependency(effect465Var, new Expression(effect465VarV));
                    effect465 = new EffectFunction(new EffectFunction(effect465Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { _17_0_2_2_edc0357_1358276938898_934226_38545, endTime }));
                    Object effect466VarV = decider_17_0_2_2_edc0357_1358276938694_797479_38527;
                    effect466Var = new Parameter("effect466Var", null, null, this);
                    addDependency(effect466Var, new Expression(effect466VarV));
                    effect466 = new EffectFunction(new EffectFunction(effect466Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class, java.lang.Boolean.class), new Object[] { endTime, myDeciderID_decider_17_0_2_2_edc0357_1358276938694_797479_38527, addToDecider__17_0_2_2_edc0357_1358276938694_797479_38527 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1358276938663_447681_38525Collections() {
                parameters.add(addToDecider__17_0_2_2_edc0357_1358276938694_797479_38527);
                parameters.add(_17_0_2_2_edc0357_1358276938694_797479_38527_exists);
                parameters.add(_17_0_2_2_edc0357_1358276938898_934226_38545);
                parameters.add(myDeciderID_decider_17_0_2_2_edc0357_1358276938694_797479_38527);
                parameters.add(_17_0_2_2_edc0357_1358276938899_878275_38546);
                Set<Effect> effectsForeffect465Var = new TreeSet<Effect>();
                effectsForeffect465Var.add(effect465);
                addEffects((Parameter<?>) effect465Var, effectsForeffect465Var);
                Set<Effect> effectsForeffect466Var = new TreeSet<Effect>();
                effectsForeffect466Var.add(effect466);
                addEffects((Parameter<?>) effect466Var, effectsForeffect466Var);
            }

            public void init_17_0_2_2_edc0357_1358276938663_447681_38525Dependencies() {
                addDependency(addToDecider__17_0_2_2_edc0357_1358276938694_797479_38527, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1358276938636_100276_38523_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1358276938636_100276_38523_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(_17_0_2_2_edc0357_1358276938694_797479_38527_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1358276938636_100276_38523_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1358276938636_100276_38523_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1358276938694_797479_38527, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1358276938694_797479_38527, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1358276938694_797479_38527, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_2_edc0357_1358276938694_797479_38527)))))))));
                addDependency(_17_0_2_2_edc0357_1358276938898_934226_38545, new Expression(new EffectFunction(null, ClassUtils.getMethodForArgTypes("TimeVaryingPlottableMap<Integer>", "PowerSystem3", "getValue", java.lang.Integer.class), new Object[] { new Expression<Integer>(startTime) }, new FunctionCall(_17_0_2_2_edc0357_1358276938899_878275_38546, Parameter.class, "getMember", new Object[] { "load__17_0_2_2_edc0357_1357846886583_892776_19869" }))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(myDeciderID_decider_17_0_2_2_edc0357_1358276938694_797479_38527, new Expression<Integer>(1));
                addDependency(_17_0_2_2_edc0357_1358276938899_878275_38546, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1358276938724_68571_38535, ClassUtils.getMethodForArgTypes("ObjectFlow<Power>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_2_edc0357_1358276938663_447681_38525Elaborations() {
                init_17_0_2_2_edc0357_1358276938663_447681_38525Dependencies();
                Expression<?>[] arguments467 = new Expression<?>[1];
                arguments467[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition467 = new Expression<Boolean>(_17_0_2_2_edc0357_1358276938694_797479_38527_exists);
                elaborationRule467 = addElaborationRule(condition467, _17_0_2_2_edc0357_1358276938198_952298_38499.this, Power._17_0_2_2_edc0357_1358276938198_952298_38499._17_0_2_2_edc0357_1358276938694_797479_38527.class, "PGen_send_load_SendSignalAction_monitorFeeders", arguments467);
            }
        }

        public class _17_0_2_2_edc0357_1358276938694_797479_38527 extends DurativeEvent {

            public _17_0_2_2_edc0357_1358276938694_797479_38527() {
                super();
                init_17_0_2_2_edc0357_1358276938694_797479_38527Members();
                init_17_0_2_2_edc0357_1358276938694_797479_38527Collections();
                init_17_0_2_2_edc0357_1358276938694_797479_38527Elaborations();
            }

            public _17_0_2_2_edc0357_1358276938694_797479_38527(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1358276938694_797479_38527Members();
                init_17_0_2_2_edc0357_1358276938694_797479_38527Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1358276938694_797479_38527Elaborations();
                fixTimeDependencies();
            }

            public Parameter< Power > _17_0_2_2_edc0357_1358276938911_245162_38549 = null;

            public IntegerParameter _17_0_2_2_edc0357_1358276938912_238581_38550 = null;

            public BooleanParameter objectToPass = null;

            public Parameter< Power_System.SignalreceiveLoadReading > signalObject = null;

            public Dependency _17_0_2_2_edc0357_1358276938911_245162_38549Dependency = null;

            public Dependency _17_0_2_2_edc0357_1358276938912_238581_38550Dependency = null;

            public Dependency finalNode_startTimeDependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Dependency signalObjectDependency = null;

            public Effect effect468 = null;

            public Parameter effect468Var = null;

            public Effect effect469 = null;

            public Parameter effect469Var = null;

            public Effect effect470 = null;

            public Parameter effect470Var = null;

            public void init_17_0_2_2_edc0357_1358276938694_797479_38527Members() {
                try {
                    if (_17_0_2_2_edc0357_1358276938911_245162_38549 == null) _17_0_2_2_edc0357_1358276938911_245162_38549 = new Parameter<Power>("_17_0_2_2_edc0357_1358276938911_245162_38549", null, (Power) null, this);
                    if (_17_0_2_2_edc0357_1358276938912_238581_38550 == null) _17_0_2_2_edc0357_1358276938912_238581_38550 = new IntegerParameter("_17_0_2_2_edc0357_1358276938912_238581_38550", (Integer) null, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (signalObject == null) signalObject = new Parameter<Power_System.SignalreceiveLoadReading>("signalObject", null, (Power_System.SignalreceiveLoadReading) null, this);
                    Object effect468VarV = sig_17_0_2_2_edc0357_1358276938724_619501_38540;
                    effect468Var = new Parameter("effect468Var", null, null, this);
                    addDependency(effect468Var, new Expression(effect468VarV));
                    effect468 = new EffectFunction(new EffectFunction(effect468Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect469VarV = new FunctionCall(x, Parameter.class, "getMember", new Object[] { "ss_17_0_2_2_edc0357_1357846886585_941053_19872_receiveLoadReading" });
                    effect469Var = new Parameter("effect469Var", null, null, this);
                    addDependency(effect469Var, new Expression(effect469VarV));
                    effect469 = new EffectFunction(new EffectFunction(null, ClassUtils.getMethodForArgTypes("ObjectFlow<SignalreceiveLoadReading>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { signalObject, endTime }, effect469Var));
                    Object effect470VarV = new FunctionCall(signalObject, Parameter.class, "getMember", new Object[] { "actual_load__17_0_2_2_edc0357_1357846886651_371591_19961" });
                    effect470Var = new Parameter("effect470Var", null, null, this);
                    addDependency(effect470Var, new Expression(effect470VarV));
                    effect470 = new EffectFunction(new EffectFunction(null, ClassUtils.getMethodForArgTypes("TimeVaryingMap<Integer>", "PowerSystem3", "setValue", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, _17_0_2_2_edc0357_1358276938912_238581_38550 }, effect470Var));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1358276938694_797479_38527Collections() {
                parameters.add(_17_0_2_2_edc0357_1358276938911_245162_38549);
                parameters.add(_17_0_2_2_edc0357_1358276938912_238581_38550);
                parameters.add(objectToPass);
                parameters.add(signalObject);
                Set<Effect> effectsForeffect468Var = new TreeSet<Effect>();
                effectsForeffect468Var.add(effect468);
                addEffects((Parameter<?>) effect468Var, effectsForeffect468Var);
                Set<Effect> effectsForeffect469Var = new TreeSet<Effect>();
                effectsForeffect469Var.add(effect469);
                addEffects((Parameter<?>) effect469Var, effectsForeffect469Var);
                Set<Effect> effectsForeffect470Var = new TreeSet<Effect>();
                effectsForeffect470Var.add(effect470);
                addEffects((Parameter<?>) effect470Var, effectsForeffect470Var);
            }

            public void init_17_0_2_2_edc0357_1358276938694_797479_38527Dependencies() {
                addDependency(_17_0_2_2_edc0357_1358276938911_245162_38549, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1358276938724_171846_38537, ClassUtils.getMethodForArgTypes("ObjectFlow<Power>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_2_edc0357_1358276938912_238581_38550, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1358276938718_266470_38531, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(finalNode_startTime, new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(true));
                addDependency(signalObject, new Expression<Power_System.SignalreceiveLoadReading>(new ConstructorCall(x, ClassUtils.getConstructorForArgTypes(Power_System.SignalreceiveLoadReading.class), new Object[] {})));
            }

            public void init_17_0_2_2_edc0357_1358276938694_797479_38527Elaborations() {
                init_17_0_2_2_edc0357_1358276938694_797479_38527Dependencies();
            }
        }

        public class _17_0_2_2_edc0357_1358276938695_85998_38528 extends DurativeEvent {

            public _17_0_2_2_edc0357_1358276938695_85998_38528() {
                super();
                init_17_0_2_2_edc0357_1358276938695_85998_38528Members();
                init_17_0_2_2_edc0357_1358276938695_85998_38528Collections();
                init_17_0_2_2_edc0357_1358276938695_85998_38528Elaborations();
            }

            public _17_0_2_2_edc0357_1358276938695_85998_38528(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1358276938695_85998_38528Members();
                init_17_0_2_2_edc0357_1358276938695_85998_38528Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1358276938695_85998_38528Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter addToDecider__17_0_2_2_edc0357_1358276938694_797479_38527 = null;

            public BooleanParameter _17_0_2_2_edc0357_1358276938694_797479_38527_exists = null;

            public BooleanParameter _17_0_2_2_edc0357_1358276938663_447681_38525_exists = null;

            public Parameter< Power > objectToPass = null;

            public IntegerParameter myDeciderID_decider_17_0_2_2_edc0357_1358276938694_797479_38527 = null;

            public Dependency addToDecider__17_0_2_2_edc0357_1358276938694_797479_38527Dependency = null;

            public Dependency _17_0_2_2_edc0357_1358276938694_797479_38527_existsDependency = null;

            public Dependency _17_0_2_2_edc0357_1358276938663_447681_38525_existsDependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Dependency myDeciderID_decider_17_0_2_2_edc0357_1358276938694_797479_38527Dependency = null;

            public Effect effect471 = null;

            public Parameter effect471Var = null;

            public Effect effect472 = null;

            public Parameter effect472Var = null;

            public Effect effect473 = null;

            public Parameter effect473Var = null;

            public ElaborationRule elaborationRule474 = null;

            public ElaborationRule elaborationRule475 = null;

            public void init_17_0_2_2_edc0357_1358276938695_85998_38528Members() {
                try {
                    if (addToDecider__17_0_2_2_edc0357_1358276938694_797479_38527 == null) addToDecider__17_0_2_2_edc0357_1358276938694_797479_38527 = new BooleanParameter("addToDecider__17_0_2_2_edc0357_1358276938694_797479_38527", (Boolean) false, this);
                    if (_17_0_2_2_edc0357_1358276938694_797479_38527_exists == null) _17_0_2_2_edc0357_1358276938694_797479_38527_exists = new BooleanParameter("_17_0_2_2_edc0357_1358276938694_797479_38527_exists", (Boolean) false, this);
                    if (_17_0_2_2_edc0357_1358276938663_447681_38525_exists == null) _17_0_2_2_edc0357_1358276938663_447681_38525_exists = new BooleanParameter("_17_0_2_2_edc0357_1358276938663_447681_38525_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new Parameter<Power>("objectToPass", null, (Power) null, this);
                    if (myDeciderID_decider_17_0_2_2_edc0357_1358276938694_797479_38527 == null) myDeciderID_decider_17_0_2_2_edc0357_1358276938694_797479_38527 = new IntegerParameter("myDeciderID_decider_17_0_2_2_edc0357_1358276938694_797479_38527", (Integer) 2, this);
                    Object effect471VarV = sig_17_0_2_2_edc0357_1358276938724_68571_38535;
                    effect471Var = new Parameter("effect471Var", null, null, this);
                    addDependency(effect471Var, new Expression(effect471VarV));
                    effect471 = new EffectFunction(new EffectFunction(effect471Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Power>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect472VarV = sig_17_0_2_2_edc0357_1358276938724_171846_38537;
                    effect472Var = new Parameter("effect472Var", null, null, this);
                    addDependency(effect472Var, new Expression(effect472VarV));
                    effect472 = new EffectFunction(new EffectFunction(effect472Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Power>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect473VarV = decider_17_0_2_2_edc0357_1358276938694_797479_38527;
                    effect473Var = new Parameter("effect473Var", null, null, this);
                    addDependency(effect473Var, new Expression(effect473VarV));
                    effect473 = new EffectFunction(new EffectFunction(effect473Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class, java.lang.Boolean.class), new Object[] { endTime, myDeciderID_decider_17_0_2_2_edc0357_1358276938694_797479_38527, addToDecider__17_0_2_2_edc0357_1358276938694_797479_38527 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1358276938695_85998_38528Collections() {
                parameters.add(addToDecider__17_0_2_2_edc0357_1358276938694_797479_38527);
                parameters.add(_17_0_2_2_edc0357_1358276938694_797479_38527_exists);
                parameters.add(_17_0_2_2_edc0357_1358276938663_447681_38525_exists);
                parameters.add(objectToPass);
                parameters.add(myDeciderID_decider_17_0_2_2_edc0357_1358276938694_797479_38527);
                Set<Effect> effectsForeffect471Var = new TreeSet<Effect>();
                effectsForeffect471Var.add(effect471);
                addEffects((Parameter<?>) effect471Var, effectsForeffect471Var);
                Set<Effect> effectsForeffect472Var = new TreeSet<Effect>();
                effectsForeffect472Var.add(effect472);
                addEffects((Parameter<?>) effect472Var, effectsForeffect472Var);
                Set<Effect> effectsForeffect473Var = new TreeSet<Effect>();
                effectsForeffect473Var.add(effect473);
                addEffects((Parameter<?>) effect473Var, effectsForeffect473Var);
            }

            public void init_17_0_2_2_edc0357_1358276938695_85998_38528Dependencies() {
                addDependency(addToDecider__17_0_2_2_edc0357_1358276938694_797479_38527, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1358276938636_100276_38523_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1358276938636_100276_38523_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(_17_0_2_2_edc0357_1358276938694_797479_38527_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1358276938636_100276_38523_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1358276938636_100276_38523_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1358276938694_797479_38527, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1358276938694_797479_38527, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1358276938694_797479_38527, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_2_edc0357_1358276938694_797479_38527)))))))));
                addDependency(_17_0_2_2_edc0357_1358276938663_447681_38525_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1358276938636_100276_38523_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1358276938636_100276_38523_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1358276938724_206943_38533, ClassUtils.getMethodForArgTypes("ObjectFlow<Power>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(myDeciderID_decider_17_0_2_2_edc0357_1358276938694_797479_38527, new Expression<Integer>(2));
            }

            public void init_17_0_2_2_edc0357_1358276938695_85998_38528Elaborations() {
                init_17_0_2_2_edc0357_1358276938695_85998_38528Dependencies();
                Expression<?>[] arguments474 = new Expression<?>[1];
                arguments474[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition474 = new Expression<Boolean>(_17_0_2_2_edc0357_1358276938663_447681_38525_exists);
                elaborationRule474 = addElaborationRule(condition474, _17_0_2_2_edc0357_1358276938198_952298_38499.this, Power._17_0_2_2_edc0357_1358276938198_952298_38499._17_0_2_2_edc0357_1358276938663_447681_38525.class, "PGen_read_load_struct_ReadStructuralFeatureAction_monitorFeeders", arguments474);
                Expression<?>[] arguments475 = new Expression<?>[1];
                arguments475[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition475 = new Expression<Boolean>(_17_0_2_2_edc0357_1358276938694_797479_38527_exists);
                elaborationRule475 = addElaborationRule(condition475, _17_0_2_2_edc0357_1358276938198_952298_38499.this, Power._17_0_2_2_edc0357_1358276938198_952298_38499._17_0_2_2_edc0357_1358276938694_797479_38527.class, "PGen_send_load_SendSignalAction_monitorFeeders", arguments475);
            }
        }

        public _17_0_2_2_edc0357_1358276938198_952298_38499(Expression<Integer> startTime, Expression<DurativeEvent> caller) {
            super();
            init_17_0_2_2_edc0357_1358276938198_952298_38499Members();
            init_17_0_2_2_edc0357_1358276938198_952298_38499Collections();
            addDependency(this.startTime, startTime);
            addDependency(this.caller, caller);
            init_17_0_2_2_edc0357_1358276938198_952298_38499Elaborations();
            fixTimeDependencies();
        }

        public Parameter< ObjectFlow<Integer> > sig_17_0_2_2_edc0357_1358276938718_266470_38531 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_2_edc0357_1358276938724_619501_38540 = null;

        public Parameter< ObjectFlow<Power> > sig_17_0_2_2_edc0357_1358276938724_171846_38537 = null;

        public Parameter< ObjectFlow<Power> > sig_17_0_2_2_edc0357_1358276938724_68571_38535 = null;

        public Parameter< DurativeEvent > caller = null;

        public Parameter< TimeVaryingList<Integer> > decider_17_0_2_2_edc0357_1358276938694_797479_38527 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_2_edc0357_1358276938708_246458_38530 = null;

        public Parameter< ObjectFlow<Power> > sig_17_0_2_2_edc0357_1358276938724_206943_38533 = null;

        public IntegerParameter finalNode_startTime = null;

        public IntegerParameter finalNode_endTime = null;

        public BooleanParameter _17_0_2_2_edc0357_1358276938636_100276_38523_exists = null;

        public Dependency caller_endTimeDependency = null;

        public Dependency endTimeDependency = null;

        public Dependency _17_0_2_2_edc0357_1358276938636_100276_38523_existsDependency = null;

        public ElaborationRule elaborationRule459 = null;

        public ElaborationRule elaborationRule460 = null;

        public void init_17_0_2_2_edc0357_1358276938198_952298_38499Members() {
            try {
                if (sig_17_0_2_2_edc0357_1358276938718_266470_38531 == null) sig_17_0_2_2_edc0357_1358276938718_266470_38531 = new Parameter<ObjectFlow<Integer>>("sig_17_0_2_2_edc0357_1358276938718_266470_38531", null, (ObjectFlow<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1358276938718_266470_38531" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1358276938724_619501_38540 == null) sig_17_0_2_2_edc0357_1358276938724_619501_38540 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_2_edc0357_1358276938724_619501_38540", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1358276938724_619501_38540" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1358276938724_171846_38537 == null) sig_17_0_2_2_edc0357_1358276938724_171846_38537 = new Parameter<ObjectFlow<Power>>("sig_17_0_2_2_edc0357_1358276938724_171846_38537", null, (ObjectFlow<Power>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1358276938724_171846_38537" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1358276938724_68571_38535 == null) sig_17_0_2_2_edc0357_1358276938724_68571_38535 = new Parameter<ObjectFlow<Power>>("sig_17_0_2_2_edc0357_1358276938724_68571_38535", null, (ObjectFlow<Power>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1358276938724_68571_38535" })).evaluate(true), this);
                if (caller == null) caller = new Parameter<DurativeEvent>("caller", null, (DurativeEvent) null, this);
                if (decider_17_0_2_2_edc0357_1358276938694_797479_38527 == null) decider_17_0_2_2_edc0357_1358276938694_797479_38527 = new Parameter<TimeVaryingList<Integer>>("decider_17_0_2_2_edc0357_1358276938694_797479_38527", null, (TimeVaryingList<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingList.class, java.lang.String.class, int.class), new Object[] { "decider_17_0_2_2_edc0357_1358276938694_797479_38527", 2 })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1358276938708_246458_38530 == null) sig_17_0_2_2_edc0357_1358276938708_246458_38530 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_2_edc0357_1358276938708_246458_38530", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1358276938708_246458_38530" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1358276938724_206943_38533 == null) sig_17_0_2_2_edc0357_1358276938724_206943_38533 = new Parameter<ObjectFlow<Power>>("sig_17_0_2_2_edc0357_1358276938724_206943_38533", null, (ObjectFlow<Power>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1358276938724_206943_38533" })).evaluate(true), this);
                if (finalNode_startTime == null) finalNode_startTime = new IntegerParameter("finalNode_startTime", (Integer) null, this);
                if (finalNode_endTime == null) finalNode_endTime = new IntegerParameter("finalNode_endTime", (Integer) null, this);
                if (_17_0_2_2_edc0357_1358276938636_100276_38523_exists == null) _17_0_2_2_edc0357_1358276938636_100276_38523_exists = new BooleanParameter("_17_0_2_2_edc0357_1358276938636_100276_38523_exists", (Boolean) false, this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void init_17_0_2_2_edc0357_1358276938198_952298_38499Collections() {
            parameters.add(sig_17_0_2_2_edc0357_1358276938718_266470_38531);
            parameters.add(sig_17_0_2_2_edc0357_1358276938724_619501_38540);
            parameters.add(sig_17_0_2_2_edc0357_1358276938724_171846_38537);
            parameters.add(sig_17_0_2_2_edc0357_1358276938724_68571_38535);
            parameters.add(caller);
            parameters.add(decider_17_0_2_2_edc0357_1358276938694_797479_38527);
            parameters.add(sig_17_0_2_2_edc0357_1358276938708_246458_38530);
            parameters.add(sig_17_0_2_2_edc0357_1358276938724_206943_38533);
            parameters.add(finalNode_startTime);
            parameters.add(finalNode_endTime);
            parameters.add(_17_0_2_2_edc0357_1358276938636_100276_38523_exists);
        }

        public void init_17_0_2_2_edc0357_1358276938198_952298_38499Dependencies() {
            caller.getValue(true).addDependency(caller.getValue(true).endTime, new Expression<Integer>(finalNode_endTime));
            addDependency(endTime, new Expression<Integer>(finalNode_endTime));
            addDependency(_17_0_2_2_edc0357_1358276938636_100276_38523_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.NotEquals(new Expression<Integer>(finalNode_startTime), null)), new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1358276938724_619501_38540, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(new Functions.Minus(new Expression<Integer>(finalNode_startTime), new Expression<Integer>(1))) })))));
        }

        public void init_17_0_2_2_edc0357_1358276938198_952298_38499Elaborations() {
            init_17_0_2_2_edc0357_1358276938198_952298_38499Dependencies();
            Expression<?>[] arguments459 = new Expression<?>[1];
            arguments459[0] = new Expression<Integer>(finalNode_startTime);
            Expression<Boolean> condition459 = new Expression<Boolean>(_17_0_2_2_edc0357_1358276938636_100276_38523_exists);
            elaborationRule459 = addElaborationRule(condition459, _17_0_2_2_edc0357_1358276938198_952298_38499.this, Power._17_0_2_2_edc0357_1358276938198_952298_38499._17_0_2_2_edc0357_1358276938636_100276_38523.class, "PGen_final_ActivityFinalNode_monitorFeeders", arguments459);
            Expression<?>[] arguments460 = new Expression<?>[1];
            arguments460[0] = new Expression<Integer>(startTime);
            Expression<Boolean> condition460 = new Expression<Boolean>(true);
            elaborationRule460 = addElaborationRule(condition460, _17_0_2_2_edc0357_1358276938198_952298_38499.this, Power._17_0_2_2_edc0357_1358276938198_952298_38499._17_0_2_2_edc0357_1358276938635_8125_38522.class, "PGen_start_InitialNode_monitorFeeders", arguments460);
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

    public Parameter< ObjectFlow<Power_System.SignalreceiveGenReading> > q_Power_receiveGenReading = null;

    public Parameter< TimeVaryingPlottableMap<Integer> > power__17_0_2_2_edc0357_1357846886582_295221_19868 = null;

    public Parameter< ObjectFlow<Power_System.SignalchangeGenerationValue> > q_Power_changeGenerationValue = null;

    public Parameter< Power_System > x = null;

    public Parameter< TimeVaryingPlottableMap<Integer> > powerAugmentation__17_0_2_2_edc0357_1358276774925_778819_38488 = null;

    public Parameter< TimeVaryingPlottableMap<Integer> > load__17_0_2_2_edc0357_1357846886583_892776_19869 = null;

    public Parameter< TimeVaryingPlottableMap<Double> > genProfile__17_0_2_2_edc0357_1358276700262_461437_38482 = null;

    public Parameter< ObjectFlow<Power_System.SignalreceiveLoadReading> > q_Power_receiveLoadReading = null;

    public Parameter< ObjectFlow<Power_System.SignalchangeLoadValue> > q_Power_changeLoadValue = null;

    public void initPowerMembers() {
        try {
            if (classifierBehavior == null) classifierBehavior = new StringParameter("classifierBehavior", (String) "_17_0_2_2_edc0357_1357846886581_947081_19867", this);
            if (q_Power_receiveGenReading == null) q_Power_receiveGenReading = new Parameter<ObjectFlow<Power_System.SignalreceiveGenReading>>("q_Power_receiveGenReading", null, (ObjectFlow<Power_System.SignalreceiveGenReading>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class, java.lang.Class.class), new Object[] { "q_Power_receiveGenReading", Power_System.SignalreceiveGenReading.class })).evaluate(true), this);
            if (power__17_0_2_2_edc0357_1357846886582_295221_19868 == null) power__17_0_2_2_edc0357_1357846886582_295221_19868 = new Parameter<TimeVaryingPlottableMap<Integer>>("power__17_0_2_2_edc0357_1357846886582_295221_19868", null, (TimeVaryingPlottableMap<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingPlottableMap.class, java.lang.String.class, java.lang.String.class, java.lang.Class.class, boolean.class), new Object[] { "power", null, Integer.class, false })).evaluate(true), this);
            if (q_Power_changeGenerationValue == null) q_Power_changeGenerationValue = new Parameter<ObjectFlow<Power_System.SignalchangeGenerationValue>>("q_Power_changeGenerationValue", null, (ObjectFlow<Power_System.SignalchangeGenerationValue>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class, java.lang.Class.class), new Object[] { "q_Power_changeGenerationValue", Power_System.SignalchangeGenerationValue.class })).evaluate(true), this);
            if (x == null) x = new Parameter<Power_System>("x", null, (Power_System) null, this);
            if (powerAugmentation__17_0_2_2_edc0357_1358276774925_778819_38488 == null) powerAugmentation__17_0_2_2_edc0357_1358276774925_778819_38488 = new Parameter<TimeVaryingPlottableMap<Integer>>("powerAugmentation__17_0_2_2_edc0357_1358276774925_778819_38488", null, (TimeVaryingPlottableMap<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingPlottableMap.class, java.lang.String.class, java.lang.String.class, java.lang.Class.class, boolean.class), new Object[] { "powerAugmentation", null, Integer.class, false })).evaluate(true), this);
            if (load__17_0_2_2_edc0357_1357846886583_892776_19869 == null) load__17_0_2_2_edc0357_1357846886583_892776_19869 = new Parameter<TimeVaryingPlottableMap<Integer>>("load__17_0_2_2_edc0357_1357846886583_892776_19869", null, (TimeVaryingPlottableMap<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingPlottableMap.class, java.lang.String.class, java.lang.String.class, java.lang.Class.class, boolean.class), new Object[] { "load", null, Integer.class, false })).evaluate(true), this);
            if (genProfile__17_0_2_2_edc0357_1358276700262_461437_38482 == null) genProfile__17_0_2_2_edc0357_1358276700262_461437_38482 = new Parameter<TimeVaryingPlottableMap<Double>>("genProfile__17_0_2_2_edc0357_1358276700262_461437_38482", null, (TimeVaryingPlottableMap<Double>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingPlottableMap.class, java.lang.String.class, java.lang.String.class, java.lang.Class.class, boolean.class), new Object[] { "genProfile", "aggregatePlan.csv", Double.class, true })).evaluate(true), this);
            if (q_Power_receiveLoadReading == null) q_Power_receiveLoadReading = new Parameter<ObjectFlow<Power_System.SignalreceiveLoadReading>>("q_Power_receiveLoadReading", null, (ObjectFlow<Power_System.SignalreceiveLoadReading>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class, java.lang.Class.class), new Object[] { "q_Power_receiveLoadReading", Power_System.SignalreceiveLoadReading.class })).evaluate(true), this);
            if (q_Power_changeLoadValue == null) q_Power_changeLoadValue = new Parameter<ObjectFlow<Power_System.SignalchangeLoadValue>>("q_Power_changeLoadValue", null, (ObjectFlow<Power_System.SignalchangeLoadValue>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class, java.lang.Class.class), new Object[] { "q_Power_changeLoadValue", Power_System.SignalchangeLoadValue.class })).evaluate(true), this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void initPowerCollections() {
        parameters.add(classifierBehavior);
        parameters.add(q_Power_receiveGenReading);
        parameters.add(power__17_0_2_2_edc0357_1357846886582_295221_19868);
        parameters.add(q_Power_changeGenerationValue);
        parameters.add(x);
        parameters.add(powerAugmentation__17_0_2_2_edc0357_1358276774925_778819_38488);
        parameters.add(load__17_0_2_2_edc0357_1357846886583_892776_19869);
        parameters.add(genProfile__17_0_2_2_edc0357_1358276700262_461437_38482);
        parameters.add(q_Power_receiveLoadReading);
        parameters.add(q_Power_changeLoadValue);
    }

    public void initPowerDependencies() {
    }

    public void initPowerElaborations() {
        initPowerDependencies();
    }
}
