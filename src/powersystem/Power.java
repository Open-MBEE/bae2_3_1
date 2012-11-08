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

public class Power extends ParameterListenerImpl {

    public Power() {
        super();
        initPowerMembers();
        initPowerCollections();
        initPowerElaborations();
    }

    public class _17_0_2_edc0357_1352328156584_903842_19596 extends DurativeEvent {

        public _17_0_2_edc0357_1352328156584_903842_19596() {
            super();
            init_17_0_2_edc0357_1352328156584_903842_19596Members();
            init_17_0_2_edc0357_1352328156584_903842_19596Collections();
            init_17_0_2_edc0357_1352328156584_903842_19596Elaborations();
        }

        public class _17_0_2_edc0357_1352328157333_170355_19818 extends DurativeEvent {

            public _17_0_2_edc0357_1352328157333_170355_19818() {
                super();
                init_17_0_2_edc0357_1352328157333_170355_19818Members();
                init_17_0_2_edc0357_1352328157333_170355_19818Collections();
                init_17_0_2_edc0357_1352328157333_170355_19818Elaborations();
            }

            public _17_0_2_edc0357_1352328157333_170355_19818(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328157333_170355_19818Members();
                init_17_0_2_edc0357_1352328157333_170355_19818Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328157333_170355_19818Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_edc0357_1352328157343_160764_19824_exists = null;

            public Parameter< Power > _17_0_2_edc0357_1352328160470_546246_21272 = null;

            public BooleanParameter objectToPass = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328157343_160764_19824_existsDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Effect effect252 = null;

            public Parameter effect252Var = null;

            public ElaborationRule elaborationRule253 = null;

            public void init_17_0_2_edc0357_1352328157333_170355_19818Members() {
                try {
                    if (_17_0_2_edc0357_1352328157343_160764_19824_exists == null) _17_0_2_edc0357_1352328157343_160764_19824_exists = new BooleanParameter("_17_0_2_edc0357_1352328157343_160764_19824_exists", (Boolean) false, this);
                    if (_17_0_2_edc0357_1352328160470_546246_21272 == null) _17_0_2_edc0357_1352328160470_546246_21272 = new Parameter<Power>("_17_0_2_edc0357_1352328160470_546246_21272", null, (Power) Power.this, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect252VarV = sig_17_0_2_edc0357_1352328157344_282724_19828;
                    effect252Var = new Parameter("effect252Var", null, null, this);
                    addDependency(effect252Var, new Expression(effect252VarV));
                    effect252 = new EffectFunction(new FunctionCall(effect252Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Power>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { _17_0_2_edc0357_1352328160470_546246_21272, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328157333_170355_19818Collections() {
                parameters.add(_17_0_2_edc0357_1352328157343_160764_19824_exists);
                parameters.add(_17_0_2_edc0357_1352328160470_546246_21272);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect252Var = new TreeSet<Effect>();
                effectsForeffect252Var.add(effect252);
                addEffects((Parameter<?>) effect252Var, effectsForeffect252Var);
            }

            public void init_17_0_2_edc0357_1352328157333_170355_19818Dependencies() {
                addDependency(_17_0_2_edc0357_1352328157343_160764_19824_exists, new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new FunctionCall(sig_17_0_2_edc0357_1352328157345_326515_19834, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_edc0357_1352328157333_170355_19818Elaborations() {
                init_17_0_2_edc0357_1352328157333_170355_19818Dependencies();
                Expression<?>[] arguments253 = new Expression<?>[1];
                arguments253[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition253 = new Expression<Boolean>(_17_0_2_edc0357_1352328157343_160764_19824_exists);
                elaborationRule253 = addElaborationRule(condition253, _17_0_2_edc0357_1352328156584_903842_19596.this, Power._17_0_2_edc0357_1352328156584_903842_19596._17_0_2_edc0357_1352328157343_160764_19824.class, "fork_self_ForkNode_changeGenerationValue", arguments253);
            }
        }

        public class _17_0_2_edc0357_1352328157336_41937_19819 extends DurativeEvent {

            public _17_0_2_edc0357_1352328157336_41937_19819() {
                super();
                init_17_0_2_edc0357_1352328157336_41937_19819Members();
                init_17_0_2_edc0357_1352328157336_41937_19819Collections();
                init_17_0_2_edc0357_1352328157336_41937_19819Elaborations();
            }

            public _17_0_2_edc0357_1352328157336_41937_19819(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328157336_41937_19819Members();
                init_17_0_2_edc0357_1352328157336_41937_19819Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328157336_41937_19819Elaborations();
                fixTimeDependencies();
            }

            public IntegerParameter myDeciderID_decider_17_0_2_edc0357_1352328157341_638587_19822 = null;

            public BooleanParameter _17_0_2_edc0357_1352328157341_638587_19822_exists = null;

            public IntegerParameter _17_0_2_edc0357_1352328160483_46350_21273 = null;

            public Parameter< Power > _17_0_2_edc0357_1352328160485_840187_21274 = null;

            public BooleanParameter objectToPass = null;

            public Dependency< Integer > myDeciderID_decider_17_0_2_edc0357_1352328157341_638587_19822Dependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328157341_638587_19822_existsDependency = null;

            public Dependency< Integer > _17_0_2_edc0357_1352328160483_46350_21273Dependency = null;

            public Dependency< Power > _17_0_2_edc0357_1352328160485_840187_21274Dependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Effect effect254 = null;

            public Parameter effect254Var = null;

            public Effect effect255 = null;

            public Parameter effect255Var = null;

            public Effect effect256 = null;

            public Parameter effect256Var = null;

            public ElaborationRule elaborationRule257 = null;

            public void init_17_0_2_edc0357_1352328157336_41937_19819Members() {
                try {
                    if (myDeciderID_decider_17_0_2_edc0357_1352328157341_638587_19822 == null) myDeciderID_decider_17_0_2_edc0357_1352328157341_638587_19822 = new IntegerParameter("myDeciderID_decider_17_0_2_edc0357_1352328157341_638587_19822", (Integer) 3, this);
                    if (_17_0_2_edc0357_1352328157341_638587_19822_exists == null) _17_0_2_edc0357_1352328157341_638587_19822_exists = new BooleanParameter("_17_0_2_edc0357_1352328157341_638587_19822_exists", (Boolean) false, this);
                    if (_17_0_2_edc0357_1352328160483_46350_21273 == null) _17_0_2_edc0357_1352328160483_46350_21273 = new IntegerParameter("_17_0_2_edc0357_1352328160483_46350_21273", (Integer) null, this);
                    if (_17_0_2_edc0357_1352328160485_840187_21274 == null) _17_0_2_edc0357_1352328160485_840187_21274 = new Parameter<Power>("_17_0_2_edc0357_1352328160485_840187_21274", null, (Power) null, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect254VarV = sig_17_0_2_edc0357_1352328157345_561594_19831;
                    effect254Var = new Parameter("effect254Var", null, null, this);
                    addDependency(effect254Var, new Expression(effect254VarV));
                    effect254 = new EffectFunction(new FunctionCall(effect254Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect255VarV = decider_17_0_2_edc0357_1352328157341_638587_19822;
                    effect255Var = new Parameter("effect255Var", null, null, this);
                    addDependency(effect255Var, new Expression(effect255VarV));
                    effect255 = new EffectFunction(new FunctionCall(effect255Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, myDeciderID_decider_17_0_2_edc0357_1352328157341_638587_19822 }));
                    Object effect256VarV = power__17_0_2_edc0357_1352328156609_70990_19604;
                    effect256Var = new Parameter("effect256Var", null, null, this);
                    addDependency(effect256Var, new Expression(effect256VarV));
                    effect256 = new EffectFunction(new FunctionCall(effect256Var, ClassUtils.getMethodForArgTypes("TimeVaryingMap<Integer>", "powersystem", "setValue", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { startTime, _17_0_2_edc0357_1352328160483_46350_21273 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328157336_41937_19819Collections() {
                parameters.add(myDeciderID_decider_17_0_2_edc0357_1352328157341_638587_19822);
                parameters.add(_17_0_2_edc0357_1352328157341_638587_19822_exists);
                parameters.add(_17_0_2_edc0357_1352328160483_46350_21273);
                parameters.add(_17_0_2_edc0357_1352328160485_840187_21274);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect254Var = new TreeSet<Effect>();
                effectsForeffect254Var.add(effect254);
                addEffects((Parameter<?>) effect254Var, effectsForeffect254Var);
                Set<Effect> effectsForeffect255Var = new TreeSet<Effect>();
                effectsForeffect255Var.add(effect255);
                addEffects((Parameter<?>) effect255Var, effectsForeffect255Var);
                Set<Effect> effectsForeffect256Var = new TreeSet<Effect>();
                effectsForeffect256Var.add(effect256);
                addEffects((Parameter<?>) effect256Var, effectsForeffect256Var);
            }

            public void init_17_0_2_edc0357_1352328157336_41937_19819Dependencies() {
                addDependency(myDeciderID_decider_17_0_2_edc0357_1352328157341_638587_19822, new Expression<Integer>(3));
                addDependency(_17_0_2_edc0357_1352328157341_638587_19822_exists, new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328157341_638587_19822, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328157341_638587_19822, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "maxSize"), new Object[] {})))).functionCall), new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328157341_638587_19822, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_edc0357_1352328157341_638587_19822))).functionCall))).functionCall), new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall))).functionCall));
                addDependency(_17_0_2_edc0357_1352328160483_46350_21273, new Expression<Integer>(new FunctionCall(sig_17_0_2_edc0357_1352328157344_617920_19826, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_edc0357_1352328160485_840187_21274, new Expression<Power>(new FunctionCall(sig_17_0_2_edc0357_1352328157344_89916_19829, ClassUtils.getMethodForArgTypes("ObjectFlow<Power>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(true));
            }

            public void init_17_0_2_edc0357_1352328157336_41937_19819Elaborations() {
                init_17_0_2_edc0357_1352328157336_41937_19819Dependencies();
                Expression<?>[] arguments257 = new Expression<?>[1];
                arguments257[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition257 = new Expression<Boolean>(_17_0_2_edc0357_1352328157341_638587_19822_exists);
                elaborationRule257 = addElaborationRule(condition257, _17_0_2_edc0357_1352328156584_903842_19596.this, Power._17_0_2_edc0357_1352328156584_903842_19596._17_0_2_edc0357_1352328157341_638587_19822.class, "send_generation_reading_SendSignalAction_changeGenerationValue", arguments257);
            }
        }

        public class _17_0_2_edc0357_1352328157337_305808_19820 extends DurativeEvent {

            public _17_0_2_edc0357_1352328157337_305808_19820() {
                super();
                init_17_0_2_edc0357_1352328157337_305808_19820Members();
                init_17_0_2_edc0357_1352328157337_305808_19820Collections();
                init_17_0_2_edc0357_1352328157337_305808_19820Elaborations();
            }

            public _17_0_2_edc0357_1352328157337_305808_19820(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328157337_305808_19820Members();
                init_17_0_2_edc0357_1352328157337_305808_19820Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328157337_305808_19820Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter objectToPass = null;

            public BooleanParameter _17_0_2_edc0357_1352328157333_170355_19818_exists = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328157333_170355_19818_existsDependency = null;

            public Effect effect258 = null;

            public Parameter effect258Var = null;

            public ElaborationRule elaborationRule259 = null;

            public void init_17_0_2_edc0357_1352328157337_305808_19820Members() {
                try {
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (_17_0_2_edc0357_1352328157333_170355_19818_exists == null) _17_0_2_edc0357_1352328157333_170355_19818_exists = new BooleanParameter("_17_0_2_edc0357_1352328157333_170355_19818_exists", (Boolean) false, this);
                    Object effect258VarV = sig_17_0_2_edc0357_1352328157345_326515_19834;
                    effect258Var = new Parameter("effect258Var", null, null, this);
                    addDependency(effect258Var, new Expression(effect258VarV));
                    effect258 = new EffectFunction(new FunctionCall(effect258Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328157337_305808_19820Collections() {
                parameters.add(objectToPass);
                parameters.add(_17_0_2_edc0357_1352328157333_170355_19818_exists);
                Set<Effect> effectsForeffect258Var = new TreeSet<Effect>();
                effectsForeffect258Var.add(effect258);
                addEffects((Parameter<?>) effect258Var, effectsForeffect258Var);
            }

            public void init_17_0_2_edc0357_1352328157337_305808_19820Dependencies() {
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(true));
                addDependency(_17_0_2_edc0357_1352328157333_170355_19818_exists, new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall));
            }

            public void init_17_0_2_edc0357_1352328157337_305808_19820Elaborations() {
                init_17_0_2_edc0357_1352328157337_305808_19820Dependencies();
                Expression<?>[] arguments259 = new Expression<?>[1];
                arguments259[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition259 = new Expression<Boolean>(_17_0_2_edc0357_1352328157333_170355_19818_exists);
                elaborationRule259 = addElaborationRule(condition259, _17_0_2_edc0357_1352328156584_903842_19596.this, Power._17_0_2_edc0357_1352328156584_903842_19596._17_0_2_edc0357_1352328157333_170355_19818.class, "readSelf_ReadSelfAction_changeGenerationValue", arguments259);
            }
        }

        public class _17_0_2_edc0357_1352328157337_145284_19821 extends DurativeEvent {

            public _17_0_2_edc0357_1352328157337_145284_19821() {
                super();
                init_17_0_2_edc0357_1352328157337_145284_19821Members();
                init_17_0_2_edc0357_1352328157337_145284_19821Collections();
                init_17_0_2_edc0357_1352328157337_145284_19821Elaborations();
            }

            public _17_0_2_edc0357_1352328157337_145284_19821(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328157337_145284_19821Members();
                init_17_0_2_edc0357_1352328157337_145284_19821Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328157337_145284_19821Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter objectToPass = null;

            public Dependency< Integer > endTimeDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Dependency< Integer > finalNode_endTimeDependency = null;

            public void init_17_0_2_edc0357_1352328157337_145284_19821Members() {
                try {
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328157337_145284_19821Collections() {
                parameters.add(objectToPass);
            }

            public void init_17_0_2_edc0357_1352328157337_145284_19821Dependencies() {
                addDependency(endTime, new Expression<Integer>((new Functions.Plus(new Expression<Integer>(startTime), new Expression<Integer>(duration))).functionCall));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new FunctionCall(sig_17_0_2_edc0357_1352328157345_257214_19832, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(finalNode_endTime, new Expression<Integer>(endTime));
            }

            public void init_17_0_2_edc0357_1352328157337_145284_19821Elaborations() {
                init_17_0_2_edc0357_1352328157337_145284_19821Dependencies();
            }
        }

        public class _17_0_2_edc0357_1352328157341_638587_19822 extends DurativeEvent {

            public _17_0_2_edc0357_1352328157341_638587_19822() {
                super();
                init_17_0_2_edc0357_1352328157341_638587_19822Members();
                init_17_0_2_edc0357_1352328157341_638587_19822Collections();
                init_17_0_2_edc0357_1352328157341_638587_19822Elaborations();
            }

            public _17_0_2_edc0357_1352328157341_638587_19822(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328157341_638587_19822Members();
                init_17_0_2_edc0357_1352328157341_638587_19822Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328157341_638587_19822Elaborations();
                fixTimeDependencies();
            }

            public IntegerParameter _17_0_2_edc0357_1352328160486_819634_21276 = null;

            public Parameter< Power > _17_0_2_edc0357_1352328160485_601824_21275 = null;

            public BooleanParameter objectToPass = null;

            public Dependency< Integer > _17_0_2_edc0357_1352328160486_819634_21276Dependency = null;

            public Dependency< Power > _17_0_2_edc0357_1352328160485_601824_21275Dependency = null;

            public Dependency< Integer > finalNode_startTimeDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Effect effect260 = null;

            public Parameter effect260Var = null;

            public Effect effect261 = null;

            public Parameter effect261Var = null;

            public void init_17_0_2_edc0357_1352328157341_638587_19822Members() {
                try {
                    if (_17_0_2_edc0357_1352328160486_819634_21276 == null) _17_0_2_edc0357_1352328160486_819634_21276 = new IntegerParameter("_17_0_2_edc0357_1352328160486_819634_21276", (Integer) null, this);
                    if (_17_0_2_edc0357_1352328160485_601824_21275 == null) _17_0_2_edc0357_1352328160485_601824_21275 = new Parameter<Power>("_17_0_2_edc0357_1352328160485_601824_21275", null, (Power) null, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect260VarV = sig_17_0_2_edc0357_1352328157345_257214_19832;
                    effect260Var = new Parameter("effect260Var", null, null, this);
                    addDependency(effect260Var, new Expression(effect260VarV));
                    effect260 = new EffectFunction(new FunctionCall(effect260Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect261VarV = new FunctionCall(x, Parameter.class, "getMember", new Object[] { "ss_17_0_2_edc0357_1352328156634_627908_19609_receiveGenReading" });
                    effect261Var = new Parameter("effect261Var", null, null, this);
                    addDependency(effect261Var, new Expression(effect261VarV));
                    effect261 = new EffectFunction(new FunctionCall(null, ClassUtils.getMethodForArgTypes("ObjectFlow<SignalreceiveGenReading>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { x.getValue().new SignalreceiveGenReading(endTime, _17_0_2_edc0357_1352328160486_819634_21276.getValue()), endTime }, effect261Var));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328157341_638587_19822Collections() {
                parameters.add(_17_0_2_edc0357_1352328160486_819634_21276);
                parameters.add(_17_0_2_edc0357_1352328160485_601824_21275);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect260Var = new TreeSet<Effect>();
                effectsForeffect260Var.add(effect260);
                addEffects((Parameter<?>) effect260Var, effectsForeffect260Var);
                Set<Effect> effectsForeffect261Var = new TreeSet<Effect>();
                effectsForeffect261Var.add(effect261);
                addEffects((Parameter<?>) effect261Var, effectsForeffect261Var);
            }

            public void init_17_0_2_edc0357_1352328157341_638587_19822Dependencies() {
                addDependency(_17_0_2_edc0357_1352328160486_819634_21276, new Expression<Integer>(new FunctionCall(sig_17_0_2_edc0357_1352328157344_273649_19827, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_edc0357_1352328160485_601824_21275, new Expression<Power>(new FunctionCall(sig_17_0_2_edc0357_1352328157344_158805_19830, ClassUtils.getMethodForArgTypes("ObjectFlow<Power>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(finalNode_startTime, new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new FunctionCall(sig_17_0_2_edc0357_1352328157345_561594_19831, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_edc0357_1352328157341_638587_19822Elaborations() {
                init_17_0_2_edc0357_1352328157341_638587_19822Dependencies();
            }
        }

        public class _17_0_2_edc0357_1352328157342_115889_19823 extends DurativeEvent {

            public _17_0_2_edc0357_1352328157342_115889_19823() {
                super();
                init_17_0_2_edc0357_1352328157342_115889_19823Members();
                init_17_0_2_edc0357_1352328157342_115889_19823Collections();
                init_17_0_2_edc0357_1352328157342_115889_19823Elaborations();
            }

            public _17_0_2_edc0357_1352328157342_115889_19823(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328157342_115889_19823Members();
                init_17_0_2_edc0357_1352328157342_115889_19823Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328157342_115889_19823Elaborations();
                fixTimeDependencies();
            }

            public IntegerParameter myDeciderID_decider_17_0_2_edc0357_1352328157341_638587_19822 = null;

            public BooleanParameter _17_0_2_edc0357_1352328157341_638587_19822_exists = null;

            public BooleanParameter _17_0_2_edc0357_1352328157336_41937_19819_exists = null;

            public IntegerParameter objectToPass = null;

            public IntegerParameter myDeciderID_decider_17_0_2_edc0357_1352328157336_41937_19819 = null;

            public Dependency< Integer > myDeciderID_decider_17_0_2_edc0357_1352328157341_638587_19822Dependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328157341_638587_19822_existsDependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328157336_41937_19819_existsDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Integer > objectToPassDependency = null;

            public Dependency< Integer > myDeciderID_decider_17_0_2_edc0357_1352328157336_41937_19819Dependency = null;

            public Effect effect262 = null;

            public Parameter effect262Var = null;

            public Effect effect263 = null;

            public Parameter effect263Var = null;

            public Effect effect264 = null;

            public Parameter effect264Var = null;

            public Effect effect265 = null;

            public Parameter effect265Var = null;

            public ElaborationRule elaborationRule266 = null;

            public ElaborationRule elaborationRule267 = null;

            public void init_17_0_2_edc0357_1352328157342_115889_19823Members() {
                try {
                    if (myDeciderID_decider_17_0_2_edc0357_1352328157341_638587_19822 == null) myDeciderID_decider_17_0_2_edc0357_1352328157341_638587_19822 = new IntegerParameter("myDeciderID_decider_17_0_2_edc0357_1352328157341_638587_19822", (Integer) 1, this);
                    if (_17_0_2_edc0357_1352328157341_638587_19822_exists == null) _17_0_2_edc0357_1352328157341_638587_19822_exists = new BooleanParameter("_17_0_2_edc0357_1352328157341_638587_19822_exists", (Boolean) false, this);
                    if (_17_0_2_edc0357_1352328157336_41937_19819_exists == null) _17_0_2_edc0357_1352328157336_41937_19819_exists = new BooleanParameter("_17_0_2_edc0357_1352328157336_41937_19819_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new IntegerParameter("objectToPass", (Integer) null, this);
                    if (myDeciderID_decider_17_0_2_edc0357_1352328157336_41937_19819 == null) myDeciderID_decider_17_0_2_edc0357_1352328157336_41937_19819 = new IntegerParameter("myDeciderID_decider_17_0_2_edc0357_1352328157336_41937_19819", (Integer) 2, this);
                    Object effect262VarV = sig_17_0_2_edc0357_1352328157344_617920_19826;
                    effect262Var = new Parameter("effect262Var", null, null, this);
                    addDependency(effect262Var, new Expression(effect262VarV));
                    effect262 = new EffectFunction(new FunctionCall(effect262Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect263VarV = sig_17_0_2_edc0357_1352328157344_273649_19827;
                    effect263Var = new Parameter("effect263Var", null, null, this);
                    addDependency(effect263Var, new Expression(effect263VarV));
                    effect263 = new EffectFunction(new FunctionCall(effect263Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect264VarV = decider_17_0_2_edc0357_1352328157336_41937_19819;
                    effect264Var = new Parameter("effect264Var", null, null, this);
                    addDependency(effect264Var, new Expression(effect264VarV));
                    effect264 = new EffectFunction(new FunctionCall(effect264Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, myDeciderID_decider_17_0_2_edc0357_1352328157336_41937_19819 }));
                    Object effect265VarV = decider_17_0_2_edc0357_1352328157341_638587_19822;
                    effect265Var = new Parameter("effect265Var", null, null, this);
                    addDependency(effect265Var, new Expression(effect265VarV));
                    effect265 = new EffectFunction(new FunctionCall(effect265Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, myDeciderID_decider_17_0_2_edc0357_1352328157341_638587_19822 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328157342_115889_19823Collections() {
                parameters.add(myDeciderID_decider_17_0_2_edc0357_1352328157341_638587_19822);
                parameters.add(_17_0_2_edc0357_1352328157341_638587_19822_exists);
                parameters.add(_17_0_2_edc0357_1352328157336_41937_19819_exists);
                parameters.add(objectToPass);
                parameters.add(myDeciderID_decider_17_0_2_edc0357_1352328157336_41937_19819);
                Set<Effect> effectsForeffect262Var = new TreeSet<Effect>();
                effectsForeffect262Var.add(effect262);
                addEffects((Parameter<?>) effect262Var, effectsForeffect262Var);
                Set<Effect> effectsForeffect263Var = new TreeSet<Effect>();
                effectsForeffect263Var.add(effect263);
                addEffects((Parameter<?>) effect263Var, effectsForeffect263Var);
                Set<Effect> effectsForeffect264Var = new TreeSet<Effect>();
                effectsForeffect264Var.add(effect264);
                addEffects((Parameter<?>) effect264Var, effectsForeffect264Var);
                Set<Effect> effectsForeffect265Var = new TreeSet<Effect>();
                effectsForeffect265Var.add(effect265);
                addEffects((Parameter<?>) effect265Var, effectsForeffect265Var);
            }

            public void init_17_0_2_edc0357_1352328157342_115889_19823Dependencies() {
                addDependency(myDeciderID_decider_17_0_2_edc0357_1352328157341_638587_19822, new Expression<Integer>(1));
                addDependency(_17_0_2_edc0357_1352328157341_638587_19822_exists, new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328157341_638587_19822, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328157341_638587_19822, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "maxSize"), new Object[] {})))).functionCall), new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328157341_638587_19822, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_edc0357_1352328157341_638587_19822))).functionCall))).functionCall), new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall))).functionCall));
                addDependency(_17_0_2_edc0357_1352328157336_41937_19819_exists, new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328157336_41937_19819, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328157336_41937_19819, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "maxSize"), new Object[] {})))).functionCall), new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328157336_41937_19819, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_edc0357_1352328157336_41937_19819))).functionCall))).functionCall), new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall))).functionCall));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Integer>(new FunctionCall(sig_17_0_2_edc0357_1352328157345_517284_19833, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(myDeciderID_decider_17_0_2_edc0357_1352328157336_41937_19819, new Expression<Integer>(2));
            }

            public void init_17_0_2_edc0357_1352328157342_115889_19823Elaborations() {
                init_17_0_2_edc0357_1352328157342_115889_19823Dependencies();
                Expression<?>[] arguments266 = new Expression<?>[1];
                arguments266[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition266 = new Expression<Boolean>(_17_0_2_edc0357_1352328157336_41937_19819_exists);
                elaborationRule266 = addElaborationRule(condition266, _17_0_2_edc0357_1352328156584_903842_19596.this, Power._17_0_2_edc0357_1352328156584_903842_19596._17_0_2_edc0357_1352328157336_41937_19819.class, "add_struct_power_AddStructuralFeatureValueAction_changeGenerationValue", arguments266);
                Expression<?>[] arguments267 = new Expression<?>[1];
                arguments267[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition267 = new Expression<Boolean>(_17_0_2_edc0357_1352328157341_638587_19822_exists);
                elaborationRule267 = addElaborationRule(condition267, _17_0_2_edc0357_1352328156584_903842_19596.this, Power._17_0_2_edc0357_1352328156584_903842_19596._17_0_2_edc0357_1352328157341_638587_19822.class, "send_generation_reading_SendSignalAction_changeGenerationValue", arguments267);
            }
        }

        public class _17_0_2_edc0357_1352328157343_160764_19824 extends DurativeEvent {

            public _17_0_2_edc0357_1352328157343_160764_19824() {
                super();
                init_17_0_2_edc0357_1352328157343_160764_19824Members();
                init_17_0_2_edc0357_1352328157343_160764_19824Collections();
                init_17_0_2_edc0357_1352328157343_160764_19824Elaborations();
            }

            public _17_0_2_edc0357_1352328157343_160764_19824(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328157343_160764_19824Members();
                init_17_0_2_edc0357_1352328157343_160764_19824Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328157343_160764_19824Elaborations();
                fixTimeDependencies();
            }

            public IntegerParameter myDeciderID_decider_17_0_2_edc0357_1352328157341_638587_19822 = null;

            public BooleanParameter _17_0_2_edc0357_1352328157341_638587_19822_exists = null;

            public BooleanParameter _17_0_2_edc0357_1352328157336_41937_19819_exists = null;

            public Parameter< Power > objectToPass = null;

            public IntegerParameter myDeciderID_decider_17_0_2_edc0357_1352328157336_41937_19819 = null;

            public Dependency< Integer > myDeciderID_decider_17_0_2_edc0357_1352328157341_638587_19822Dependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328157341_638587_19822_existsDependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328157336_41937_19819_existsDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Power > objectToPassDependency = null;

            public Dependency< Integer > myDeciderID_decider_17_0_2_edc0357_1352328157336_41937_19819Dependency = null;

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

            public void init_17_0_2_edc0357_1352328157343_160764_19824Members() {
                try {
                    if (myDeciderID_decider_17_0_2_edc0357_1352328157341_638587_19822 == null) myDeciderID_decider_17_0_2_edc0357_1352328157341_638587_19822 = new IntegerParameter("myDeciderID_decider_17_0_2_edc0357_1352328157341_638587_19822", (Integer) 2, this);
                    if (_17_0_2_edc0357_1352328157341_638587_19822_exists == null) _17_0_2_edc0357_1352328157341_638587_19822_exists = new BooleanParameter("_17_0_2_edc0357_1352328157341_638587_19822_exists", (Boolean) false, this);
                    if (_17_0_2_edc0357_1352328157336_41937_19819_exists == null) _17_0_2_edc0357_1352328157336_41937_19819_exists = new BooleanParameter("_17_0_2_edc0357_1352328157336_41937_19819_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new Parameter<Power>("objectToPass", null, (Power) null, this);
                    if (myDeciderID_decider_17_0_2_edc0357_1352328157336_41937_19819 == null) myDeciderID_decider_17_0_2_edc0357_1352328157336_41937_19819 = new IntegerParameter("myDeciderID_decider_17_0_2_edc0357_1352328157336_41937_19819", (Integer) 1, this);
                    Object effect268VarV = sig_17_0_2_edc0357_1352328157344_89916_19829;
                    effect268Var = new Parameter("effect268Var", null, null, this);
                    addDependency(effect268Var, new Expression(effect268VarV));
                    effect268 = new EffectFunction(new FunctionCall(effect268Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Power>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect269VarV = sig_17_0_2_edc0357_1352328157344_158805_19830;
                    effect269Var = new Parameter("effect269Var", null, null, this);
                    addDependency(effect269Var, new Expression(effect269VarV));
                    effect269 = new EffectFunction(new FunctionCall(effect269Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Power>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect270VarV = decider_17_0_2_edc0357_1352328157336_41937_19819;
                    effect270Var = new Parameter("effect270Var", null, null, this);
                    addDependency(effect270Var, new Expression(effect270VarV));
                    effect270 = new EffectFunction(new FunctionCall(effect270Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, myDeciderID_decider_17_0_2_edc0357_1352328157336_41937_19819 }));
                    Object effect271VarV = decider_17_0_2_edc0357_1352328157341_638587_19822;
                    effect271Var = new Parameter("effect271Var", null, null, this);
                    addDependency(effect271Var, new Expression(effect271VarV));
                    effect271 = new EffectFunction(new FunctionCall(effect271Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, myDeciderID_decider_17_0_2_edc0357_1352328157341_638587_19822 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328157343_160764_19824Collections() {
                parameters.add(myDeciderID_decider_17_0_2_edc0357_1352328157341_638587_19822);
                parameters.add(_17_0_2_edc0357_1352328157341_638587_19822_exists);
                parameters.add(_17_0_2_edc0357_1352328157336_41937_19819_exists);
                parameters.add(objectToPass);
                parameters.add(myDeciderID_decider_17_0_2_edc0357_1352328157336_41937_19819);
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

            public void init_17_0_2_edc0357_1352328157343_160764_19824Dependencies() {
                addDependency(myDeciderID_decider_17_0_2_edc0357_1352328157341_638587_19822, new Expression<Integer>(2));
                addDependency(_17_0_2_edc0357_1352328157341_638587_19822_exists, new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328157341_638587_19822, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328157341_638587_19822, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "maxSize"), new Object[] {})))).functionCall), new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328157341_638587_19822, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_edc0357_1352328157341_638587_19822))).functionCall))).functionCall), new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall))).functionCall));
                addDependency(_17_0_2_edc0357_1352328157336_41937_19819_exists, new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328157336_41937_19819, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328157336_41937_19819, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "maxSize"), new Object[] {})))).functionCall), new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328157336_41937_19819, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_edc0357_1352328157336_41937_19819))).functionCall))).functionCall), new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall))).functionCall));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Power>(new FunctionCall(sig_17_0_2_edc0357_1352328157344_282724_19828, ClassUtils.getMethodForArgTypes("ObjectFlow<Power>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(myDeciderID_decider_17_0_2_edc0357_1352328157336_41937_19819, new Expression<Integer>(1));
            }

            public void init_17_0_2_edc0357_1352328157343_160764_19824Elaborations() {
                init_17_0_2_edc0357_1352328157343_160764_19824Dependencies();
                Expression<?>[] arguments272 = new Expression<?>[1];
                arguments272[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition272 = new Expression<Boolean>(_17_0_2_edc0357_1352328157336_41937_19819_exists);
                elaborationRule272 = addElaborationRule(condition272, _17_0_2_edc0357_1352328156584_903842_19596.this, Power._17_0_2_edc0357_1352328156584_903842_19596._17_0_2_edc0357_1352328157336_41937_19819.class, "add_struct_power_AddStructuralFeatureValueAction_changeGenerationValue", arguments272);
                Expression<?>[] arguments273 = new Expression<?>[1];
                arguments273[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition273 = new Expression<Boolean>(_17_0_2_edc0357_1352328157341_638587_19822_exists);
                elaborationRule273 = addElaborationRule(condition273, _17_0_2_edc0357_1352328156584_903842_19596.this, Power._17_0_2_edc0357_1352328156584_903842_19596._17_0_2_edc0357_1352328157341_638587_19822.class, "send_generation_reading_SendSignalAction_changeGenerationValue", arguments273);
            }
        }

        public class _17_0_2_edc0357_1352328157343_79095_19825 extends DurativeEvent {

            public _17_0_2_edc0357_1352328157343_79095_19825() {
                super();
                init_17_0_2_edc0357_1352328157343_79095_19825Members();
                init_17_0_2_edc0357_1352328157343_79095_19825Collections();
                init_17_0_2_edc0357_1352328157343_79095_19825Elaborations();
            }

            public _17_0_2_edc0357_1352328157343_79095_19825(Expression<Integer> startTime, Expression<Integer> _17_0_2_edc0357_1352328157191_364910_19786) {
                super();
                init_17_0_2_edc0357_1352328157343_79095_19825Members();
                init_17_0_2_edc0357_1352328157343_79095_19825Collections();
                addDependency(this.startTime, startTime);
                addDependency(this._17_0_2_edc0357_1352328157191_364910_19786, _17_0_2_edc0357_1352328157191_364910_19786);
                init_17_0_2_edc0357_1352328157343_79095_19825Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_edc0357_1352328157342_115889_19823_exists = null;

            public IntegerParameter objectToPass = null;

            public IntegerParameter _17_0_2_edc0357_1352328157191_364910_19786 = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328157342_115889_19823_existsDependency = null;

            public Dependency< Integer > objectToPassDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Effect effect274 = null;

            public Parameter effect274Var = null;

            public ElaborationRule elaborationRule275 = null;

            public void init_17_0_2_edc0357_1352328157343_79095_19825Members() {
                try {
                    if (_17_0_2_edc0357_1352328157342_115889_19823_exists == null) _17_0_2_edc0357_1352328157342_115889_19823_exists = new BooleanParameter("_17_0_2_edc0357_1352328157342_115889_19823_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new IntegerParameter("objectToPass", (Integer) null, this);
                    if (_17_0_2_edc0357_1352328157191_364910_19786 == null) _17_0_2_edc0357_1352328157191_364910_19786 = new IntegerParameter("_17_0_2_edc0357_1352328157191_364910_19786", (Integer) null, this);
                    Object effect274VarV = sig_17_0_2_edc0357_1352328157345_517284_19833;
                    effect274Var = new Parameter("effect274Var", null, null, this);
                    addDependency(effect274Var, new Expression(effect274VarV));
                    effect274 = new EffectFunction(new FunctionCall(effect274Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328157343_79095_19825Collections() {
                parameters.add(_17_0_2_edc0357_1352328157342_115889_19823_exists);
                parameters.add(objectToPass);
                parameters.add(_17_0_2_edc0357_1352328157191_364910_19786);
                Set<Effect> effectsForeffect274Var = new TreeSet<Effect>();
                effectsForeffect274Var.add(effect274);
                addEffects((Parameter<?>) effect274Var, effectsForeffect274Var);
            }

            public void init_17_0_2_edc0357_1352328157343_79095_19825Dependencies() {
                addDependency(_17_0_2_edc0357_1352328157342_115889_19823_exists, new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall));
                addDependency(objectToPass, new Expression<Integer>(_17_0_2_edc0357_1352328157191_364910_19786));
                addDependency(duration, new Expression<Integer>(1));
            }

            public void init_17_0_2_edc0357_1352328157343_79095_19825Elaborations() {
                init_17_0_2_edc0357_1352328157343_79095_19825Dependencies();
                Expression<?>[] arguments275 = new Expression<?>[1];
                arguments275[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition275 = new Expression<Boolean>(_17_0_2_edc0357_1352328157342_115889_19823_exists);
                elaborationRule275 = addElaborationRule(condition275, _17_0_2_edc0357_1352328156584_903842_19596.this, Power._17_0_2_edc0357_1352328156584_903842_19596._17_0_2_edc0357_1352328157342_115889_19823.class, "fork_val_ForkNode_changeGenerationValue", arguments275);
            }
        }

        public _17_0_2_edc0357_1352328156584_903842_19596(Expression<Integer> startTime, Expression<DurativeEvent> caller, Expression<Integer> _17_0_2_edc0357_1352328157191_364910_19786) {
            super();
            init_17_0_2_edc0357_1352328156584_903842_19596Members();
            init_17_0_2_edc0357_1352328156584_903842_19596Collections();
            addDependency(this.startTime, startTime);
            addDependency(this.caller, caller);
            addDependency(this._17_0_2_edc0357_1352328157191_364910_19786, _17_0_2_edc0357_1352328157191_364910_19786);
            init_17_0_2_edc0357_1352328156584_903842_19596Elaborations();
            fixTimeDependencies();
        }

        public IntegerParameter _17_0_2_edc0357_1352328157343_79095_19825_startTime = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_edc0357_1352328157345_561594_19831 = null;

        public Parameter< DurativeEvent > caller = null;

        public Parameter< TimeVaryingList<Integer> > decider_17_0_2_edc0357_1352328157341_638587_19822 = null;

        public IntegerParameter finalNode_startTime = null;

        public Parameter< ObjectFlow<Power> > sig_17_0_2_edc0357_1352328157344_282724_19828 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_edc0357_1352328157345_257214_19832 = null;

        public IntegerParameter finalNode_endTime = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_2_edc0357_1352328157344_273649_19827 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_edc0357_1352328157345_326515_19834 = null;

        public Parameter< ObjectFlow<Power> > sig_17_0_2_edc0357_1352328157344_89916_19829 = null;

        public Parameter< TimeVaryingList<Integer> > decider_17_0_2_edc0357_1352328157336_41937_19819 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_2_edc0357_1352328157345_517284_19833 = null;

        public IntegerParameter _17_0_2_edc0357_1352328157191_364910_19786 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_2_edc0357_1352328157344_617920_19826 = null;

        public BooleanParameter _17_0_2_edc0357_1352328157337_145284_19821_exists = null;

        public Parameter< ObjectFlow<Power> > sig_17_0_2_edc0357_1352328157344_158805_19830 = null;

        public Dependency< Integer > caller_endTimeDependency = null;

        public Dependency< Integer > endTimeDependency = null;

        public Dependency< Boolean > _17_0_2_edc0357_1352328157337_145284_19821_existsDependency = null;

        public ElaborationRule elaborationRule249 = null;

        public ElaborationRule elaborationRule250 = null;

        public ElaborationRule elaborationRule251 = null;

        public void init_17_0_2_edc0357_1352328156584_903842_19596Members() {
            try {
                if (_17_0_2_edc0357_1352328157343_79095_19825_startTime == null) _17_0_2_edc0357_1352328157343_79095_19825_startTime = new IntegerParameter("_17_0_2_edc0357_1352328157343_79095_19825_startTime", (Integer) null, this);
                if (sig_17_0_2_edc0357_1352328157345_561594_19831 == null) sig_17_0_2_edc0357_1352328157345_561594_19831 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328157345_561594_19831", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328157345_561594_19831" })).evaluate(true), this);
                if (caller == null) caller = new Parameter<DurativeEvent>("caller", null, (DurativeEvent) null, this);
                if (decider_17_0_2_edc0357_1352328157341_638587_19822 == null) decider_17_0_2_edc0357_1352328157341_638587_19822 = new Parameter<TimeVaryingList<Integer>>("decider_17_0_2_edc0357_1352328157341_638587_19822", null, (TimeVaryingList<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingList.class, java.lang.String.class, int.class), new Object[] { "decider_17_0_2_edc0357_1352328157341_638587_19822", 3 })).evaluate(true), this);
                if (finalNode_startTime == null) finalNode_startTime = new IntegerParameter("finalNode_startTime", (Integer) 84000, this);
                if (sig_17_0_2_edc0357_1352328157344_282724_19828 == null) sig_17_0_2_edc0357_1352328157344_282724_19828 = new Parameter<ObjectFlow<Power>>("sig_17_0_2_edc0357_1352328157344_282724_19828", null, (ObjectFlow<Power>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328157344_282724_19828" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328157345_257214_19832 == null) sig_17_0_2_edc0357_1352328157345_257214_19832 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328157345_257214_19832", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328157345_257214_19832" })).evaluate(true), this);
                if (finalNode_endTime == null) finalNode_endTime = new IntegerParameter("finalNode_endTime", (Integer) 84000, this);
                if (sig_17_0_2_edc0357_1352328157344_273649_19827 == null) sig_17_0_2_edc0357_1352328157344_273649_19827 = new Parameter<ObjectFlow<Integer>>("sig_17_0_2_edc0357_1352328157344_273649_19827", null, (ObjectFlow<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328157344_273649_19827" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328157345_326515_19834 == null) sig_17_0_2_edc0357_1352328157345_326515_19834 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328157345_326515_19834", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328157345_326515_19834" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328157344_89916_19829 == null) sig_17_0_2_edc0357_1352328157344_89916_19829 = new Parameter<ObjectFlow<Power>>("sig_17_0_2_edc0357_1352328157344_89916_19829", null, (ObjectFlow<Power>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328157344_89916_19829" })).evaluate(true), this);
                if (decider_17_0_2_edc0357_1352328157336_41937_19819 == null) decider_17_0_2_edc0357_1352328157336_41937_19819 = new Parameter<TimeVaryingList<Integer>>("decider_17_0_2_edc0357_1352328157336_41937_19819", null, (TimeVaryingList<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingList.class, java.lang.String.class, int.class), new Object[] { "decider_17_0_2_edc0357_1352328157336_41937_19819", 2 })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328157345_517284_19833 == null) sig_17_0_2_edc0357_1352328157345_517284_19833 = new Parameter<ObjectFlow<Integer>>("sig_17_0_2_edc0357_1352328157345_517284_19833", null, (ObjectFlow<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328157345_517284_19833" })).evaluate(true), this);
                if (_17_0_2_edc0357_1352328157191_364910_19786 == null) _17_0_2_edc0357_1352328157191_364910_19786 = new IntegerParameter("_17_0_2_edc0357_1352328157191_364910_19786", (Integer) null, this);
                if (sig_17_0_2_edc0357_1352328157344_617920_19826 == null) sig_17_0_2_edc0357_1352328157344_617920_19826 = new Parameter<ObjectFlow<Integer>>("sig_17_0_2_edc0357_1352328157344_617920_19826", null, (ObjectFlow<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328157344_617920_19826" })).evaluate(true), this);
                if (_17_0_2_edc0357_1352328157337_145284_19821_exists == null) _17_0_2_edc0357_1352328157337_145284_19821_exists = new BooleanParameter("_17_0_2_edc0357_1352328157337_145284_19821_exists", (Boolean) false, this);
                if (sig_17_0_2_edc0357_1352328157344_158805_19830 == null) sig_17_0_2_edc0357_1352328157344_158805_19830 = new Parameter<ObjectFlow<Power>>("sig_17_0_2_edc0357_1352328157344_158805_19830", null, (ObjectFlow<Power>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328157344_158805_19830" })).evaluate(true), this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void init_17_0_2_edc0357_1352328156584_903842_19596Collections() {
            parameters.add(_17_0_2_edc0357_1352328157343_79095_19825_startTime);
            parameters.add(sig_17_0_2_edc0357_1352328157345_561594_19831);
            parameters.add(caller);
            parameters.add(decider_17_0_2_edc0357_1352328157341_638587_19822);
            parameters.add(finalNode_startTime);
            parameters.add(sig_17_0_2_edc0357_1352328157344_282724_19828);
            parameters.add(sig_17_0_2_edc0357_1352328157345_257214_19832);
            parameters.add(finalNode_endTime);
            parameters.add(sig_17_0_2_edc0357_1352328157344_273649_19827);
            parameters.add(sig_17_0_2_edc0357_1352328157345_326515_19834);
            parameters.add(sig_17_0_2_edc0357_1352328157344_89916_19829);
            parameters.add(decider_17_0_2_edc0357_1352328157336_41937_19819);
            parameters.add(sig_17_0_2_edc0357_1352328157345_517284_19833);
            parameters.add(_17_0_2_edc0357_1352328157191_364910_19786);
            parameters.add(sig_17_0_2_edc0357_1352328157344_617920_19826);
            parameters.add(_17_0_2_edc0357_1352328157337_145284_19821_exists);
            parameters.add(sig_17_0_2_edc0357_1352328157344_158805_19830);
        }

        public void init_17_0_2_edc0357_1352328156584_903842_19596Dependencies() {
            caller.getValue(true).addDependency(caller.getValue(true).endTime, new Expression<Integer>(finalNode_endTime));
            addDependency(endTime, new Expression<Integer>(finalNode_endTime));
            addDependency(_17_0_2_edc0357_1352328157337_145284_19821_exists, new Expression<Boolean>(new FunctionCall(sig_17_0_2_edc0357_1352328157345_257214_19832, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>((new Functions.Minus(new Expression<Integer>(finalNode_startTime), new Expression<Integer>(1))).functionCall) })));
        }

        public void init_17_0_2_edc0357_1352328156584_903842_19596Elaborations() {
            init_17_0_2_edc0357_1352328156584_903842_19596Dependencies();
            Expression<?>[] arguments249 = new Expression<?>[1];
            arguments249[0] = new Expression<Integer>(startTime);
            Expression<Boolean> condition249 = new Expression<Boolean>(true);
            elaborationRule249 = addElaborationRule(condition249, _17_0_2_edc0357_1352328156584_903842_19596.this, Power._17_0_2_edc0357_1352328156584_903842_19596._17_0_2_edc0357_1352328157337_305808_19820.class, "start_InitialNode_changeGenerationValue", arguments249);
            Expression<?>[] arguments250 = new Expression<?>[2];
            arguments250[0] = new Expression<Integer>(startTime);
            arguments250[1] = new Expression<Integer>(_17_0_2_edc0357_1352328157191_364910_19786);
            Expression<Boolean> condition250 = new Expression<Boolean>(true);
            elaborationRule250 = addElaborationRule(condition250, _17_0_2_edc0357_1352328156584_903842_19596.this, Power._17_0_2_edc0357_1352328156584_903842_19596._17_0_2_edc0357_1352328157343_79095_19825.class, "newGenVal_ActivityParameterNode_changeGenerationValue", arguments250);
            Expression<?>[] arguments251 = new Expression<?>[1];
            arguments251[0] = new Expression<Integer>(finalNode_startTime);
            Expression<Boolean> condition251 = new Expression<Boolean>(_17_0_2_edc0357_1352328157337_145284_19821_exists);
            elaborationRule251 = addElaborationRule(condition251, _17_0_2_edc0357_1352328156584_903842_19596.this, Power._17_0_2_edc0357_1352328156584_903842_19596._17_0_2_edc0357_1352328157337_145284_19821.class, "end_ActivityFinalNode_changeGenerationValue", arguments251);
        }
    }

    public class _17_0_2_edc0357_1352328156603_274620_19597 extends DurativeEvent {

        public _17_0_2_edc0357_1352328156603_274620_19597() {
            super();
            init_17_0_2_edc0357_1352328156603_274620_19597Members();
            init_17_0_2_edc0357_1352328156603_274620_19597Collections();
            init_17_0_2_edc0357_1352328156603_274620_19597Elaborations();
        }

        public class _17_0_2_edc0357_1352328157413_239445_19867 extends DurativeEvent {

            public _17_0_2_edc0357_1352328157413_239445_19867() {
                super();
                init_17_0_2_edc0357_1352328157413_239445_19867Members();
                init_17_0_2_edc0357_1352328157413_239445_19867Collections();
                init_17_0_2_edc0357_1352328157413_239445_19867Elaborations();
            }

            public _17_0_2_edc0357_1352328157413_239445_19867(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328157413_239445_19867Members();
                init_17_0_2_edc0357_1352328157413_239445_19867Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328157413_239445_19867Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_edc0357_1352328157413_220121_19868_exists = null;

            public Parameter< Power > _17_0_2_edc0357_1352328160489_391729_21287 = null;

            public BooleanParameter objectToPass = null;

            public IntegerParameter myDeciderID_decider_17_0_2_edc0357_1352328157413_220121_19868 = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328157413_220121_19868_existsDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Dependency< Integer > myDeciderID_decider_17_0_2_edc0357_1352328157413_220121_19868Dependency = null;

            public Effect effect279 = null;

            public Parameter effect279Var = null;

            public Effect effect280 = null;

            public Parameter effect280Var = null;

            public ElaborationRule elaborationRule281 = null;

            public void init_17_0_2_edc0357_1352328157413_239445_19867Members() {
                try {
                    if (_17_0_2_edc0357_1352328157413_220121_19868_exists == null) _17_0_2_edc0357_1352328157413_220121_19868_exists = new BooleanParameter("_17_0_2_edc0357_1352328157413_220121_19868_exists", (Boolean) false, this);
                    if (_17_0_2_edc0357_1352328160489_391729_21287 == null) _17_0_2_edc0357_1352328160489_391729_21287 = new Parameter<Power>("_17_0_2_edc0357_1352328160489_391729_21287", null, (Power) Power.this, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (myDeciderID_decider_17_0_2_edc0357_1352328157413_220121_19868 == null) myDeciderID_decider_17_0_2_edc0357_1352328157413_220121_19868 = new IntegerParameter("myDeciderID_decider_17_0_2_edc0357_1352328157413_220121_19868", (Integer) 1, this);
                    Object effect279VarV = sig_17_0_2_edc0357_1352328157416_492875_19875;
                    effect279Var = new Parameter("effect279Var", null, null, this);
                    addDependency(effect279Var, new Expression(effect279VarV));
                    effect279 = new EffectFunction(new FunctionCall(effect279Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Power>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { _17_0_2_edc0357_1352328160489_391729_21287, endTime }));
                    Object effect280VarV = decider_17_0_2_edc0357_1352328157413_220121_19868;
                    effect280Var = new Parameter("effect280Var", null, null, this);
                    addDependency(effect280Var, new Expression(effect280VarV));
                    effect280 = new EffectFunction(new FunctionCall(effect280Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, myDeciderID_decider_17_0_2_edc0357_1352328157413_220121_19868 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328157413_239445_19867Collections() {
                parameters.add(_17_0_2_edc0357_1352328157413_220121_19868_exists);
                parameters.add(_17_0_2_edc0357_1352328160489_391729_21287);
                parameters.add(objectToPass);
                parameters.add(myDeciderID_decider_17_0_2_edc0357_1352328157413_220121_19868);
                Set<Effect> effectsForeffect279Var = new TreeSet<Effect>();
                effectsForeffect279Var.add(effect279);
                addEffects((Parameter<?>) effect279Var, effectsForeffect279Var);
                Set<Effect> effectsForeffect280Var = new TreeSet<Effect>();
                effectsForeffect280Var.add(effect280);
                addEffects((Parameter<?>) effect280Var, effectsForeffect280Var);
            }

            public void init_17_0_2_edc0357_1352328157413_239445_19867Dependencies() {
                addDependency(_17_0_2_edc0357_1352328157413_220121_19868_exists, new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328157413_220121_19868, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328157413_220121_19868, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "maxSize"), new Object[] {})))).functionCall), new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328157413_220121_19868, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_edc0357_1352328157413_220121_19868))).functionCall))).functionCall), new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall))).functionCall));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new FunctionCall(sig_17_0_2_edc0357_1352328157416_719734_19878, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(myDeciderID_decider_17_0_2_edc0357_1352328157413_220121_19868, new Expression<Integer>(1));
            }

            public void init_17_0_2_edc0357_1352328157413_239445_19867Elaborations() {
                init_17_0_2_edc0357_1352328157413_239445_19867Dependencies();
                Expression<?>[] arguments281 = new Expression<?>[1];
                arguments281[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition281 = new Expression<Boolean>(_17_0_2_edc0357_1352328157413_220121_19868_exists);
                elaborationRule281 = addElaborationRule(condition281, _17_0_2_edc0357_1352328156603_274620_19597.this, Power._17_0_2_edc0357_1352328156603_274620_19597._17_0_2_edc0357_1352328157413_220121_19868.class, "add_struct_load_AddStructuralFeatureValueAction_changeLoadValue", arguments281);
            }
        }

        public class _17_0_2_edc0357_1352328157413_220121_19868 extends DurativeEvent {

            public _17_0_2_edc0357_1352328157413_220121_19868() {
                super();
                init_17_0_2_edc0357_1352328157413_220121_19868Members();
                init_17_0_2_edc0357_1352328157413_220121_19868Collections();
                init_17_0_2_edc0357_1352328157413_220121_19868Elaborations();
            }

            public _17_0_2_edc0357_1352328157413_220121_19868(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328157413_220121_19868Members();
                init_17_0_2_edc0357_1352328157413_220121_19868Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328157413_220121_19868Elaborations();
                fixTimeDependencies();
            }

            public IntegerParameter _17_0_2_edc0357_1352328160490_382013_21288 = null;

            public BooleanParameter objectToPass = null;

            public Parameter< Power > _17_0_2_edc0357_1352328160491_327118_21289 = null;

            public Dependency< Integer > finalNode_startTimeDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Integer > _17_0_2_edc0357_1352328160490_382013_21288Dependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Dependency< Power > _17_0_2_edc0357_1352328160491_327118_21289Dependency = null;

            public Effect effect282 = null;

            public Parameter effect282Var = null;

            public Effect effect283 = null;

            public Parameter effect283Var = null;

            public void init_17_0_2_edc0357_1352328157413_220121_19868Members() {
                try {
                    if (_17_0_2_edc0357_1352328160490_382013_21288 == null) _17_0_2_edc0357_1352328160490_382013_21288 = new IntegerParameter("_17_0_2_edc0357_1352328160490_382013_21288", (Integer) null, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (_17_0_2_edc0357_1352328160491_327118_21289 == null) _17_0_2_edc0357_1352328160491_327118_21289 = new Parameter<Power>("_17_0_2_edc0357_1352328160491_327118_21289", null, (Power) null, this);
                    Object effect282VarV = sig_17_0_2_edc0357_1352328157416_750018_19876;
                    effect282Var = new Parameter("effect282Var", null, null, this);
                    addDependency(effect282Var, new Expression(effect282VarV));
                    effect282 = new EffectFunction(new FunctionCall(effect282Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect283VarV = load__17_0_2_edc0357_1352328156622_89989_19605;
                    effect283Var = new Parameter("effect283Var", null, null, this);
                    addDependency(effect283Var, new Expression(effect283VarV));
                    effect283 = new EffectFunction(new FunctionCall(effect283Var, ClassUtils.getMethodForArgTypes("TimeVaryingMap<Integer>", "powersystem", "setValue", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { startTime, _17_0_2_edc0357_1352328160490_382013_21288 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328157413_220121_19868Collections() {
                parameters.add(_17_0_2_edc0357_1352328160490_382013_21288);
                parameters.add(objectToPass);
                parameters.add(_17_0_2_edc0357_1352328160491_327118_21289);
                Set<Effect> effectsForeffect282Var = new TreeSet<Effect>();
                effectsForeffect282Var.add(effect282);
                addEffects((Parameter<?>) effect282Var, effectsForeffect282Var);
                Set<Effect> effectsForeffect283Var = new TreeSet<Effect>();
                effectsForeffect283Var.add(effect283);
                addEffects((Parameter<?>) effect283Var, effectsForeffect283Var);
            }

            public void init_17_0_2_edc0357_1352328157413_220121_19868Dependencies() {
                addDependency(finalNode_startTime, new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(_17_0_2_edc0357_1352328160490_382013_21288, new Expression<Integer>(new FunctionCall(sig_17_0_2_edc0357_1352328157416_118498_19877, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(objectToPass, new Expression<Boolean>(true));
                addDependency(_17_0_2_edc0357_1352328160491_327118_21289, new Expression<Power>(new FunctionCall(sig_17_0_2_edc0357_1352328157416_492875_19875, ClassUtils.getMethodForArgTypes("ObjectFlow<Power>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_edc0357_1352328157413_220121_19868Elaborations() {
                init_17_0_2_edc0357_1352328157413_220121_19868Dependencies();
            }
        }

        public class _17_0_2_edc0357_1352328157414_438864_19869 extends DurativeEvent {

            public _17_0_2_edc0357_1352328157414_438864_19869() {
                super();
                init_17_0_2_edc0357_1352328157414_438864_19869Members();
                init_17_0_2_edc0357_1352328157414_438864_19869Collections();
                init_17_0_2_edc0357_1352328157414_438864_19869Elaborations();
            }

            public _17_0_2_edc0357_1352328157414_438864_19869(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328157414_438864_19869Members();
                init_17_0_2_edc0357_1352328157414_438864_19869Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328157414_438864_19869Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_edc0357_1352328157413_239445_19867_exists = null;

            public BooleanParameter objectToPass = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328157413_239445_19867_existsDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Effect effect284 = null;

            public Parameter effect284Var = null;

            public ElaborationRule elaborationRule285 = null;

            public void init_17_0_2_edc0357_1352328157414_438864_19869Members() {
                try {
                    if (_17_0_2_edc0357_1352328157413_239445_19867_exists == null) _17_0_2_edc0357_1352328157413_239445_19867_exists = new BooleanParameter("_17_0_2_edc0357_1352328157413_239445_19867_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect284VarV = sig_17_0_2_edc0357_1352328157416_719734_19878;
                    effect284Var = new Parameter("effect284Var", null, null, this);
                    addDependency(effect284Var, new Expression(effect284VarV));
                    effect284 = new EffectFunction(new FunctionCall(effect284Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328157414_438864_19869Collections() {
                parameters.add(_17_0_2_edc0357_1352328157413_239445_19867_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect284Var = new TreeSet<Effect>();
                effectsForeffect284Var.add(effect284);
                addEffects((Parameter<?>) effect284Var, effectsForeffect284Var);
            }

            public void init_17_0_2_edc0357_1352328157414_438864_19869Dependencies() {
                addDependency(_17_0_2_edc0357_1352328157413_239445_19867_exists, new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(true));
            }

            public void init_17_0_2_edc0357_1352328157414_438864_19869Elaborations() {
                init_17_0_2_edc0357_1352328157414_438864_19869Dependencies();
                Expression<?>[] arguments285 = new Expression<?>[1];
                arguments285[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition285 = new Expression<Boolean>(_17_0_2_edc0357_1352328157413_239445_19867_exists);
                elaborationRule285 = addElaborationRule(condition285, _17_0_2_edc0357_1352328156603_274620_19597.this, Power._17_0_2_edc0357_1352328156603_274620_19597._17_0_2_edc0357_1352328157413_239445_19867.class, "readself_ReadSelfAction_changeLoadValue", arguments285);
            }
        }

        public class _17_0_2_edc0357_1352328157415_510573_19870 extends DurativeEvent {

            public _17_0_2_edc0357_1352328157415_510573_19870() {
                super();
                init_17_0_2_edc0357_1352328157415_510573_19870Members();
                init_17_0_2_edc0357_1352328157415_510573_19870Collections();
                init_17_0_2_edc0357_1352328157415_510573_19870Elaborations();
            }

            public _17_0_2_edc0357_1352328157415_510573_19870(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328157415_510573_19870Members();
                init_17_0_2_edc0357_1352328157415_510573_19870Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328157415_510573_19870Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter objectToPass = null;

            public Dependency< Integer > endTimeDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Dependency< Integer > finalNode_endTimeDependency = null;

            public void init_17_0_2_edc0357_1352328157415_510573_19870Members() {
                try {
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328157415_510573_19870Collections() {
                parameters.add(objectToPass);
            }

            public void init_17_0_2_edc0357_1352328157415_510573_19870Dependencies() {
                addDependency(endTime, new Expression<Integer>((new Functions.Plus(new Expression<Integer>(startTime), new Expression<Integer>(duration))).functionCall));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new FunctionCall(sig_17_0_2_edc0357_1352328157416_750018_19876, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(finalNode_endTime, new Expression<Integer>(endTime));
            }

            public void init_17_0_2_edc0357_1352328157415_510573_19870Elaborations() {
                init_17_0_2_edc0357_1352328157415_510573_19870Dependencies();
            }
        }

        public class _17_0_2_edc0357_1352328157415_974164_19871 extends DurativeEvent {

            public _17_0_2_edc0357_1352328157415_974164_19871() {
                super();
                init_17_0_2_edc0357_1352328157415_974164_19871Members();
                init_17_0_2_edc0357_1352328157415_974164_19871Collections();
                init_17_0_2_edc0357_1352328157415_974164_19871Elaborations();
            }

            public _17_0_2_edc0357_1352328157415_974164_19871(Expression<Integer> startTime, Expression<Integer> _17_0_2_edc0357_1352328157345_388531_19835) {
                super();
                init_17_0_2_edc0357_1352328157415_974164_19871Members();
                init_17_0_2_edc0357_1352328157415_974164_19871Collections();
                addDependency(this.startTime, startTime);
                addDependency(this._17_0_2_edc0357_1352328157345_388531_19835, _17_0_2_edc0357_1352328157345_388531_19835);
                init_17_0_2_edc0357_1352328157415_974164_19871Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_edc0357_1352328157413_220121_19868_exists = null;

            public IntegerParameter _17_0_2_edc0357_1352328157345_388531_19835 = null;

            public IntegerParameter objectToPass = null;

            public IntegerParameter myDeciderID_decider_17_0_2_edc0357_1352328157413_220121_19868 = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328157413_220121_19868_existsDependency = null;

            public Dependency< Integer > objectToPassDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Integer > myDeciderID_decider_17_0_2_edc0357_1352328157413_220121_19868Dependency = null;

            public Effect effect286 = null;

            public Parameter effect286Var = null;

            public Effect effect287 = null;

            public Parameter effect287Var = null;

            public ElaborationRule elaborationRule288 = null;

            public void init_17_0_2_edc0357_1352328157415_974164_19871Members() {
                try {
                    if (_17_0_2_edc0357_1352328157413_220121_19868_exists == null) _17_0_2_edc0357_1352328157413_220121_19868_exists = new BooleanParameter("_17_0_2_edc0357_1352328157413_220121_19868_exists", (Boolean) false, this);
                    if (_17_0_2_edc0357_1352328157345_388531_19835 == null) _17_0_2_edc0357_1352328157345_388531_19835 = new IntegerParameter("_17_0_2_edc0357_1352328157345_388531_19835", (Integer) null, this);
                    if (objectToPass == null) objectToPass = new IntegerParameter("objectToPass", (Integer) null, this);
                    if (myDeciderID_decider_17_0_2_edc0357_1352328157413_220121_19868 == null) myDeciderID_decider_17_0_2_edc0357_1352328157413_220121_19868 = new IntegerParameter("myDeciderID_decider_17_0_2_edc0357_1352328157413_220121_19868", (Integer) 2, this);
                    Object effect286VarV = sig_17_0_2_edc0357_1352328157416_118498_19877;
                    effect286Var = new Parameter("effect286Var", null, null, this);
                    addDependency(effect286Var, new Expression(effect286VarV));
                    effect286 = new EffectFunction(new FunctionCall(effect286Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect287VarV = decider_17_0_2_edc0357_1352328157413_220121_19868;
                    effect287Var = new Parameter("effect287Var", null, null, this);
                    addDependency(effect287Var, new Expression(effect287VarV));
                    effect287 = new EffectFunction(new FunctionCall(effect287Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, myDeciderID_decider_17_0_2_edc0357_1352328157413_220121_19868 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328157415_974164_19871Collections() {
                parameters.add(_17_0_2_edc0357_1352328157413_220121_19868_exists);
                parameters.add(_17_0_2_edc0357_1352328157345_388531_19835);
                parameters.add(objectToPass);
                parameters.add(myDeciderID_decider_17_0_2_edc0357_1352328157413_220121_19868);
                Set<Effect> effectsForeffect286Var = new TreeSet<Effect>();
                effectsForeffect286Var.add(effect286);
                addEffects((Parameter<?>) effect286Var, effectsForeffect286Var);
                Set<Effect> effectsForeffect287Var = new TreeSet<Effect>();
                effectsForeffect287Var.add(effect287);
                addEffects((Parameter<?>) effect287Var, effectsForeffect287Var);
            }

            public void init_17_0_2_edc0357_1352328157415_974164_19871Dependencies() {
                addDependency(_17_0_2_edc0357_1352328157413_220121_19868_exists, new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328157413_220121_19868, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328157413_220121_19868, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "maxSize"), new Object[] {})))).functionCall), new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328157413_220121_19868, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_edc0357_1352328157413_220121_19868))).functionCall))).functionCall), new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall))).functionCall));
                addDependency(objectToPass, new Expression<Integer>(_17_0_2_edc0357_1352328157345_388531_19835));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(myDeciderID_decider_17_0_2_edc0357_1352328157413_220121_19868, new Expression<Integer>(2));
            }

            public void init_17_0_2_edc0357_1352328157415_974164_19871Elaborations() {
                init_17_0_2_edc0357_1352328157415_974164_19871Dependencies();
                Expression<?>[] arguments288 = new Expression<?>[1];
                arguments288[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition288 = new Expression<Boolean>(_17_0_2_edc0357_1352328157413_220121_19868_exists);
                elaborationRule288 = addElaborationRule(condition288, _17_0_2_edc0357_1352328156603_274620_19597.this, Power._17_0_2_edc0357_1352328156603_274620_19597._17_0_2_edc0357_1352328157413_220121_19868.class, "add_struct_load_AddStructuralFeatureValueAction_changeLoadValue", arguments288);
            }
        }

        public _17_0_2_edc0357_1352328156603_274620_19597(Expression<Integer> startTime, Expression<DurativeEvent> caller, Expression<Integer> _17_0_2_edc0357_1352328157345_388531_19835) {
            super();
            init_17_0_2_edc0357_1352328156603_274620_19597Members();
            init_17_0_2_edc0357_1352328156603_274620_19597Collections();
            addDependency(this.startTime, startTime);
            addDependency(this.caller, caller);
            addDependency(this._17_0_2_edc0357_1352328157345_388531_19835, _17_0_2_edc0357_1352328157345_388531_19835);
            init_17_0_2_edc0357_1352328156603_274620_19597Elaborations();
            fixTimeDependencies();
        }

        public Parameter< TimeVaryingList<Integer> > decider_17_0_2_edc0357_1352328157413_220121_19868 = null;

        public Parameter< DurativeEvent > caller = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_2_edc0357_1352328157416_118498_19877 = null;

        public Parameter< ObjectFlow<Power> > sig_17_0_2_edc0357_1352328157416_492875_19875 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_edc0357_1352328157416_719734_19878 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_edc0357_1352328157416_750018_19876 = null;

        public IntegerParameter finalNode_startTime = null;

        public IntegerParameter _17_0_2_edc0357_1352328157345_388531_19835 = null;

        public BooleanParameter _17_0_2_edc0357_1352328157415_510573_19870_exists = null;

        public IntegerParameter _17_0_2_edc0357_1352328157415_974164_19871_startTime = null;

        public IntegerParameter finalNode_endTime = null;

        public Dependency< Integer > caller_endTimeDependency = null;

        public Dependency< Integer > endTimeDependency = null;

        public Dependency< Boolean > _17_0_2_edc0357_1352328157415_510573_19870_existsDependency = null;

        public ElaborationRule elaborationRule276 = null;

        public ElaborationRule elaborationRule277 = null;

        public ElaborationRule elaborationRule278 = null;

        public void init_17_0_2_edc0357_1352328156603_274620_19597Members() {
            try {
                if (decider_17_0_2_edc0357_1352328157413_220121_19868 == null) decider_17_0_2_edc0357_1352328157413_220121_19868 = new Parameter<TimeVaryingList<Integer>>("decider_17_0_2_edc0357_1352328157413_220121_19868", null, (TimeVaryingList<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingList.class, java.lang.String.class, int.class), new Object[] { "decider_17_0_2_edc0357_1352328157413_220121_19868", 2 })).evaluate(true), this);
                if (caller == null) caller = new Parameter<DurativeEvent>("caller", null, (DurativeEvent) null, this);
                if (sig_17_0_2_edc0357_1352328157416_118498_19877 == null) sig_17_0_2_edc0357_1352328157416_118498_19877 = new Parameter<ObjectFlow<Integer>>("sig_17_0_2_edc0357_1352328157416_118498_19877", null, (ObjectFlow<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328157416_118498_19877" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328157416_492875_19875 == null) sig_17_0_2_edc0357_1352328157416_492875_19875 = new Parameter<ObjectFlow<Power>>("sig_17_0_2_edc0357_1352328157416_492875_19875", null, (ObjectFlow<Power>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328157416_492875_19875" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328157416_719734_19878 == null) sig_17_0_2_edc0357_1352328157416_719734_19878 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328157416_719734_19878", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328157416_719734_19878" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328157416_750018_19876 == null) sig_17_0_2_edc0357_1352328157416_750018_19876 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328157416_750018_19876", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328157416_750018_19876" })).evaluate(true), this);
                if (finalNode_startTime == null) finalNode_startTime = new IntegerParameter("finalNode_startTime", (Integer) 84000, this);
                if (_17_0_2_edc0357_1352328157345_388531_19835 == null) _17_0_2_edc0357_1352328157345_388531_19835 = new IntegerParameter("_17_0_2_edc0357_1352328157345_388531_19835", (Integer) null, this);
                if (_17_0_2_edc0357_1352328157415_510573_19870_exists == null) _17_0_2_edc0357_1352328157415_510573_19870_exists = new BooleanParameter("_17_0_2_edc0357_1352328157415_510573_19870_exists", (Boolean) false, this);
                if (_17_0_2_edc0357_1352328157415_974164_19871_startTime == null) _17_0_2_edc0357_1352328157415_974164_19871_startTime = new IntegerParameter("_17_0_2_edc0357_1352328157415_974164_19871_startTime", (Integer) null, this);
                if (finalNode_endTime == null) finalNode_endTime = new IntegerParameter("finalNode_endTime", (Integer) 84000, this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void init_17_0_2_edc0357_1352328156603_274620_19597Collections() {
            parameters.add(decider_17_0_2_edc0357_1352328157413_220121_19868);
            parameters.add(caller);
            parameters.add(sig_17_0_2_edc0357_1352328157416_118498_19877);
            parameters.add(sig_17_0_2_edc0357_1352328157416_492875_19875);
            parameters.add(sig_17_0_2_edc0357_1352328157416_719734_19878);
            parameters.add(sig_17_0_2_edc0357_1352328157416_750018_19876);
            parameters.add(finalNode_startTime);
            parameters.add(_17_0_2_edc0357_1352328157345_388531_19835);
            parameters.add(_17_0_2_edc0357_1352328157415_510573_19870_exists);
            parameters.add(_17_0_2_edc0357_1352328157415_974164_19871_startTime);
            parameters.add(finalNode_endTime);
        }

        public void init_17_0_2_edc0357_1352328156603_274620_19597Dependencies() {
            caller.getValue(true).addDependency(caller.getValue(true).endTime, new Expression<Integer>(finalNode_endTime));
            addDependency(endTime, new Expression<Integer>(finalNode_endTime));
            addDependency(_17_0_2_edc0357_1352328157415_510573_19870_exists, new Expression<Boolean>(new FunctionCall(sig_17_0_2_edc0357_1352328157416_750018_19876, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>((new Functions.Minus(new Expression<Integer>(finalNode_startTime), new Expression<Integer>(1))).functionCall) })));
        }

        public void init_17_0_2_edc0357_1352328156603_274620_19597Elaborations() {
            init_17_0_2_edc0357_1352328156603_274620_19597Dependencies();
            Expression<?>[] arguments276 = new Expression<?>[1];
            arguments276[0] = new Expression<Integer>(finalNode_startTime);
            Expression<Boolean> condition276 = new Expression<Boolean>(_17_0_2_edc0357_1352328157415_510573_19870_exists);
            elaborationRule276 = addElaborationRule(condition276, _17_0_2_edc0357_1352328156603_274620_19597.this, Power._17_0_2_edc0357_1352328156603_274620_19597._17_0_2_edc0357_1352328157415_510573_19870.class, "end_ActivityFinalNode_changeLoadValue", arguments276);
            Expression<?>[] arguments277 = new Expression<?>[1];
            arguments277[0] = new Expression<Integer>(startTime);
            Expression<Boolean> condition277 = new Expression<Boolean>(true);
            elaborationRule277 = addElaborationRule(condition277, _17_0_2_edc0357_1352328156603_274620_19597.this, Power._17_0_2_edc0357_1352328156603_274620_19597._17_0_2_edc0357_1352328157414_438864_19869.class, "start_InitialNode_changeLoadValue", arguments277);
            Expression<?>[] arguments278 = new Expression<?>[2];
            arguments278[0] = new Expression<Integer>(startTime);
            arguments278[1] = new Expression<Integer>(_17_0_2_edc0357_1352328157345_388531_19835);
            Expression<Boolean> condition278 = new Expression<Boolean>(true);
            elaborationRule278 = addElaborationRule(condition278, _17_0_2_edc0357_1352328156603_274620_19597.this, Power._17_0_2_edc0357_1352328156603_274620_19597._17_0_2_edc0357_1352328157415_974164_19871.class, "newLoadVal_ActivityParameterNode_changeLoadValue", arguments278);
        }
    }

    public class _17_0_2_edc0357_1352328156604_3589_19598 extends DurativeEvent {

        public _17_0_2_edc0357_1352328156604_3589_19598() {
            super();
            init_17_0_2_edc0357_1352328156604_3589_19598Members();
            init_17_0_2_edc0357_1352328156604_3589_19598Collections();
            init_17_0_2_edc0357_1352328156604_3589_19598Elaborations();
        }

        public class _17_0_2_edc0357_1352328157556_683256_19910 extends DurativeEvent {

            public _17_0_2_edc0357_1352328157556_683256_19910() {
                super();
                init_17_0_2_edc0357_1352328157556_683256_19910Members();
                init_17_0_2_edc0357_1352328157556_683256_19910Collections();
                init_17_0_2_edc0357_1352328157556_683256_19910Elaborations();
            }

            public _17_0_2_edc0357_1352328157556_683256_19910(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328157556_683256_19910Members();
                init_17_0_2_edc0357_1352328157556_683256_19910Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328157556_683256_19910Elaborations();
                fixTimeDependencies();
            }

            public Parameter< Power > _17_0_2_edc0357_1352328160495_242942_21295 = null;

            public BooleanParameter _17_0_2_edc0357_1352328157556_158058_19911_exists = null;

            public BooleanParameter objectToPass = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328157556_158058_19911_existsDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Effect effect291 = null;

            public Parameter effect291Var = null;

            public ElaborationRule elaborationRule292 = null;

            public void init_17_0_2_edc0357_1352328157556_683256_19910Members() {
                try {
                    if (_17_0_2_edc0357_1352328160495_242942_21295 == null) _17_0_2_edc0357_1352328160495_242942_21295 = new Parameter<Power>("_17_0_2_edc0357_1352328160495_242942_21295", null, (Power) Power.this, this);
                    if (_17_0_2_edc0357_1352328157556_158058_19911_exists == null) _17_0_2_edc0357_1352328157556_158058_19911_exists = new BooleanParameter("_17_0_2_edc0357_1352328157556_158058_19911_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect291VarV = sig_17_0_2_edc0357_1352328157560_555636_19920;
                    effect291Var = new Parameter("effect291Var", null, null, this);
                    addDependency(effect291Var, new Expression(effect291VarV));
                    effect291 = new EffectFunction(new FunctionCall(effect291Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Power>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { _17_0_2_edc0357_1352328160495_242942_21295, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328157556_683256_19910Collections() {
                parameters.add(_17_0_2_edc0357_1352328160495_242942_21295);
                parameters.add(_17_0_2_edc0357_1352328157556_158058_19911_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect291Var = new TreeSet<Effect>();
                effectsForeffect291Var.add(effect291);
                addEffects((Parameter<?>) effect291Var, effectsForeffect291Var);
            }

            public void init_17_0_2_edc0357_1352328157556_683256_19910Dependencies() {
                addDependency(_17_0_2_edc0357_1352328157556_158058_19911_exists, new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new FunctionCall(sig_17_0_2_edc0357_1352328157561_582031_19931, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_edc0357_1352328157556_683256_19910Elaborations() {
                init_17_0_2_edc0357_1352328157556_683256_19910Dependencies();
                Expression<?>[] arguments292 = new Expression<?>[1];
                arguments292[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition292 = new Expression<Boolean>(_17_0_2_edc0357_1352328157556_158058_19911_exists);
                elaborationRule292 = addElaborationRule(condition292, _17_0_2_edc0357_1352328156604_3589_19598.this, Power._17_0_2_edc0357_1352328156604_3589_19598._17_0_2_edc0357_1352328157556_158058_19911.class, "self_fork_ForkNode_intialize", arguments292);
            }
        }

        public class _17_0_2_edc0357_1352328157556_158058_19911 extends DurativeEvent {

            public _17_0_2_edc0357_1352328157556_158058_19911() {
                super();
                init_17_0_2_edc0357_1352328157556_158058_19911Members();
                init_17_0_2_edc0357_1352328157556_158058_19911Collections();
                init_17_0_2_edc0357_1352328157556_158058_19911Elaborations();
            }

            public _17_0_2_edc0357_1352328157556_158058_19911(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328157556_158058_19911Members();
                init_17_0_2_edc0357_1352328157556_158058_19911Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328157556_158058_19911Elaborations();
                fixTimeDependencies();
            }

            public IntegerParameter myDeciderID_decider_17_0_2_edc0357_1352328157557_569409_19913 = null;

            public BooleanParameter _17_0_2_edc0357_1352328157557_569409_19913_exists = null;

            public IntegerParameter myDeciderID_decider_17_0_2_edc0357_1352328157557_375441_19912 = null;

            public BooleanParameter _17_0_2_edc0357_1352328157557_375441_19912_exists = null;

            public Parameter< Power > objectToPass = null;

            public Dependency< Integer > myDeciderID_decider_17_0_2_edc0357_1352328157557_569409_19913Dependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328157557_569409_19913_existsDependency = null;

            public Dependency< Integer > myDeciderID_decider_17_0_2_edc0357_1352328157557_375441_19912Dependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328157557_375441_19912_existsDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Power > objectToPassDependency = null;

            public Effect effect293 = null;

            public Parameter effect293Var = null;

            public Effect effect294 = null;

            public Parameter effect294Var = null;

            public Effect effect295 = null;

            public Parameter effect295Var = null;

            public Effect effect296 = null;

            public Parameter effect296Var = null;

            public ElaborationRule elaborationRule297 = null;

            public ElaborationRule elaborationRule298 = null;

            public void init_17_0_2_edc0357_1352328157556_158058_19911Members() {
                try {
                    if (myDeciderID_decider_17_0_2_edc0357_1352328157557_569409_19913 == null) myDeciderID_decider_17_0_2_edc0357_1352328157557_569409_19913 = new IntegerParameter("myDeciderID_decider_17_0_2_edc0357_1352328157557_569409_19913", (Integer) 1, this);
                    if (_17_0_2_edc0357_1352328157557_569409_19913_exists == null) _17_0_2_edc0357_1352328157557_569409_19913_exists = new BooleanParameter("_17_0_2_edc0357_1352328157557_569409_19913_exists", (Boolean) false, this);
                    if (myDeciderID_decider_17_0_2_edc0357_1352328157557_375441_19912 == null) myDeciderID_decider_17_0_2_edc0357_1352328157557_375441_19912 = new IntegerParameter("myDeciderID_decider_17_0_2_edc0357_1352328157557_375441_19912", (Integer) 1, this);
                    if (_17_0_2_edc0357_1352328157557_375441_19912_exists == null) _17_0_2_edc0357_1352328157557_375441_19912_exists = new BooleanParameter("_17_0_2_edc0357_1352328157557_375441_19912_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new Parameter<Power>("objectToPass", null, (Power) null, this);
                    Object effect293VarV = sig_17_0_2_edc0357_1352328157561_747651_19923;
                    effect293Var = new Parameter("effect293Var", null, null, this);
                    addDependency(effect293Var, new Expression(effect293VarV));
                    effect293 = new EffectFunction(new FunctionCall(effect293Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Power>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect294VarV = sig_17_0_2_edc0357_1352328157561_782696_19924;
                    effect294Var = new Parameter("effect294Var", null, null, this);
                    addDependency(effect294Var, new Expression(effect294VarV));
                    effect294 = new EffectFunction(new FunctionCall(effect294Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Power>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect295VarV = decider_17_0_2_edc0357_1352328157557_569409_19913;
                    effect295Var = new Parameter("effect295Var", null, null, this);
                    addDependency(effect295Var, new Expression(effect295VarV));
                    effect295 = new EffectFunction(new FunctionCall(effect295Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, myDeciderID_decider_17_0_2_edc0357_1352328157557_569409_19913 }));
                    Object effect296VarV = decider_17_0_2_edc0357_1352328157557_375441_19912;
                    effect296Var = new Parameter("effect296Var", null, null, this);
                    addDependency(effect296Var, new Expression(effect296VarV));
                    effect296 = new EffectFunction(new FunctionCall(effect296Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, myDeciderID_decider_17_0_2_edc0357_1352328157557_375441_19912 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328157556_158058_19911Collections() {
                parameters.add(myDeciderID_decider_17_0_2_edc0357_1352328157557_569409_19913);
                parameters.add(_17_0_2_edc0357_1352328157557_569409_19913_exists);
                parameters.add(myDeciderID_decider_17_0_2_edc0357_1352328157557_375441_19912);
                parameters.add(_17_0_2_edc0357_1352328157557_375441_19912_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect293Var = new TreeSet<Effect>();
                effectsForeffect293Var.add(effect293);
                addEffects((Parameter<?>) effect293Var, effectsForeffect293Var);
                Set<Effect> effectsForeffect294Var = new TreeSet<Effect>();
                effectsForeffect294Var.add(effect294);
                addEffects((Parameter<?>) effect294Var, effectsForeffect294Var);
                Set<Effect> effectsForeffect295Var = new TreeSet<Effect>();
                effectsForeffect295Var.add(effect295);
                addEffects((Parameter<?>) effect295Var, effectsForeffect295Var);
                Set<Effect> effectsForeffect296Var = new TreeSet<Effect>();
                effectsForeffect296Var.add(effect296);
                addEffects((Parameter<?>) effect296Var, effectsForeffect296Var);
            }

            public void init_17_0_2_edc0357_1352328157556_158058_19911Dependencies() {
                addDependency(myDeciderID_decider_17_0_2_edc0357_1352328157557_569409_19913, new Expression<Integer>(1));
                addDependency(_17_0_2_edc0357_1352328157557_569409_19913_exists, new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328157557_569409_19913, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328157557_569409_19913, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "maxSize"), new Object[] {})))).functionCall), new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328157557_569409_19913, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_edc0357_1352328157557_569409_19913))).functionCall))).functionCall), new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall))).functionCall));
                addDependency(myDeciderID_decider_17_0_2_edc0357_1352328157557_375441_19912, new Expression<Integer>(1));
                addDependency(_17_0_2_edc0357_1352328157557_375441_19912_exists, new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328157557_375441_19912, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328157557_375441_19912, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "maxSize"), new Object[] {})))).functionCall), new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328157557_375441_19912, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_edc0357_1352328157557_375441_19912))).functionCall))).functionCall), new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall))).functionCall));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Power>(new FunctionCall(sig_17_0_2_edc0357_1352328157560_555636_19920, ClassUtils.getMethodForArgTypes("ObjectFlow<Power>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_edc0357_1352328157556_158058_19911Elaborations() {
                init_17_0_2_edc0357_1352328157556_158058_19911Dependencies();
                Expression<?>[] arguments297 = new Expression<?>[1];
                arguments297[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition297 = new Expression<Boolean>(_17_0_2_edc0357_1352328157557_569409_19913_exists);
                elaborationRule297 = addElaborationRule(condition297, _17_0_2_edc0357_1352328156604_3589_19598.this, Power._17_0_2_edc0357_1352328156604_3589_19598._17_0_2_edc0357_1352328157557_569409_19913.class, "power_struct_add_AddStructuralFeatureValueAction_intialize", arguments297);
                Expression<?>[] arguments298 = new Expression<?>[1];
                arguments298[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition298 = new Expression<Boolean>(_17_0_2_edc0357_1352328157557_375441_19912_exists);
                elaborationRule298 = addElaborationRule(condition298, _17_0_2_edc0357_1352328156604_3589_19598.this, Power._17_0_2_edc0357_1352328156604_3589_19598._17_0_2_edc0357_1352328157557_375441_19912.class, "load_struct_add_AddStructuralFeatureValueAction_intialize", arguments298);
            }
        }

        public class _17_0_2_edc0357_1352328157557_375441_19912 extends DurativeEvent {

            public _17_0_2_edc0357_1352328157557_375441_19912() {
                super();
                init_17_0_2_edc0357_1352328157557_375441_19912Members();
                init_17_0_2_edc0357_1352328157557_375441_19912Collections();
                init_17_0_2_edc0357_1352328157557_375441_19912Elaborations();
            }

            public _17_0_2_edc0357_1352328157557_375441_19912(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328157557_375441_19912Members();
                init_17_0_2_edc0357_1352328157557_375441_19912Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328157557_375441_19912Elaborations();
                fixTimeDependencies();
            }

            public IntegerParameter _17_0_2_edc0357_1352328160496_235264_21296 = null;

            public BooleanParameter _17_0_2_edc0357_1352328157560_221675_19918_exists = null;

            public IntegerParameter myDeciderID_decider_17_0_2_edc0357_1352328157560_221675_19918 = null;

            public Parameter< Power > _17_0_2_edc0357_1352328160497_809525_21297 = null;

            public BooleanParameter objectToPass = null;

            public Dependency< Integer > _17_0_2_edc0357_1352328160496_235264_21296Dependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328157560_221675_19918_existsDependency = null;

            public Dependency< Integer > myDeciderID_decider_17_0_2_edc0357_1352328157560_221675_19918Dependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Power > _17_0_2_edc0357_1352328160497_809525_21297Dependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Effect effect299 = null;

            public Parameter effect299Var = null;

            public Effect effect300 = null;

            public Parameter effect300Var = null;

            public Effect effect301 = null;

            public Parameter effect301Var = null;

            public ElaborationRule elaborationRule302 = null;

            public void init_17_0_2_edc0357_1352328157557_375441_19912Members() {
                try {
                    if (_17_0_2_edc0357_1352328160496_235264_21296 == null) _17_0_2_edc0357_1352328160496_235264_21296 = new IntegerParameter("_17_0_2_edc0357_1352328160496_235264_21296", (Integer) null, this);
                    if (_17_0_2_edc0357_1352328157560_221675_19918_exists == null) _17_0_2_edc0357_1352328157560_221675_19918_exists = new BooleanParameter("_17_0_2_edc0357_1352328157560_221675_19918_exists", (Boolean) false, this);
                    if (myDeciderID_decider_17_0_2_edc0357_1352328157560_221675_19918 == null) myDeciderID_decider_17_0_2_edc0357_1352328157560_221675_19918 = new IntegerParameter("myDeciderID_decider_17_0_2_edc0357_1352328157560_221675_19918", (Integer) 1, this);
                    if (_17_0_2_edc0357_1352328160497_809525_21297 == null) _17_0_2_edc0357_1352328160497_809525_21297 = new Parameter<Power>("_17_0_2_edc0357_1352328160497_809525_21297", null, (Power) null, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect299VarV = sig_17_0_2_edc0357_1352328157561_874082_19926;
                    effect299Var = new Parameter("effect299Var", null, null, this);
                    addDependency(effect299Var, new Expression(effect299VarV));
                    effect299 = new EffectFunction(new FunctionCall(effect299Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect300VarV = decider_17_0_2_edc0357_1352328157560_221675_19918;
                    effect300Var = new Parameter("effect300Var", null, null, this);
                    addDependency(effect300Var, new Expression(effect300VarV));
                    effect300 = new EffectFunction(new FunctionCall(effect300Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, myDeciderID_decider_17_0_2_edc0357_1352328157560_221675_19918 }));
                    Object effect301VarV = load__17_0_2_edc0357_1352328156622_89989_19605;
                    effect301Var = new Parameter("effect301Var", null, null, this);
                    addDependency(effect301Var, new Expression(effect301VarV));
                    effect301 = new EffectFunction(new FunctionCall(effect301Var, ClassUtils.getMethodForArgTypes("TimeVaryingMap<Integer>", "powersystem", "setValue", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { startTime, _17_0_2_edc0357_1352328160496_235264_21296 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328157557_375441_19912Collections() {
                parameters.add(_17_0_2_edc0357_1352328160496_235264_21296);
                parameters.add(_17_0_2_edc0357_1352328157560_221675_19918_exists);
                parameters.add(myDeciderID_decider_17_0_2_edc0357_1352328157560_221675_19918);
                parameters.add(_17_0_2_edc0357_1352328160497_809525_21297);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect299Var = new TreeSet<Effect>();
                effectsForeffect299Var.add(effect299);
                addEffects((Parameter<?>) effect299Var, effectsForeffect299Var);
                Set<Effect> effectsForeffect300Var = new TreeSet<Effect>();
                effectsForeffect300Var.add(effect300);
                addEffects((Parameter<?>) effect300Var, effectsForeffect300Var);
                Set<Effect> effectsForeffect301Var = new TreeSet<Effect>();
                effectsForeffect301Var.add(effect301);
                addEffects((Parameter<?>) effect301Var, effectsForeffect301Var);
            }

            public void init_17_0_2_edc0357_1352328157557_375441_19912Dependencies() {
                addDependency(_17_0_2_edc0357_1352328160496_235264_21296, new Expression<Integer>(new FunctionCall(sig_17_0_2_edc0357_1352328157560_366387_19922, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_edc0357_1352328157560_221675_19918_exists, new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328157560_221675_19918, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328157560_221675_19918, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "maxSize"), new Object[] {})))).functionCall), new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328157560_221675_19918, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_edc0357_1352328157560_221675_19918))).functionCall))).functionCall), new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall))).functionCall));
                addDependency(myDeciderID_decider_17_0_2_edc0357_1352328157560_221675_19918, new Expression<Integer>(1));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(_17_0_2_edc0357_1352328160497_809525_21297, new Expression<Power>(new FunctionCall(sig_17_0_2_edc0357_1352328157561_747651_19923, ClassUtils.getMethodForArgTypes("ObjectFlow<Power>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(objectToPass, new Expression<Boolean>(true));
            }

            public void init_17_0_2_edc0357_1352328157557_375441_19912Elaborations() {
                init_17_0_2_edc0357_1352328157557_375441_19912Dependencies();
                Expression<?>[] arguments302 = new Expression<?>[1];
                arguments302[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition302 = new Expression<Boolean>(_17_0_2_edc0357_1352328157560_221675_19918_exists);
                elaborationRule302 = addElaborationRule(condition302, _17_0_2_edc0357_1352328156604_3589_19598.this, Power._17_0_2_edc0357_1352328156604_3589_19598._17_0_2_edc0357_1352328157560_221675_19918.class, "end_join_JoinNode_intialize", arguments302);
            }
        }

        public class _17_0_2_edc0357_1352328157557_569409_19913 extends DurativeEvent {

            public _17_0_2_edc0357_1352328157557_569409_19913() {
                super();
                init_17_0_2_edc0357_1352328157557_569409_19913Members();
                init_17_0_2_edc0357_1352328157557_569409_19913Collections();
                init_17_0_2_edc0357_1352328157557_569409_19913Elaborations();
            }

            public _17_0_2_edc0357_1352328157557_569409_19913(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328157557_569409_19913Members();
                init_17_0_2_edc0357_1352328157557_569409_19913Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328157557_569409_19913Elaborations();
                fixTimeDependencies();
            }

            public Parameter< Power > _17_0_2_edc0357_1352328160498_862342_21299 = null;

            public BooleanParameter _17_0_2_edc0357_1352328157560_221675_19918_exists = null;

            public IntegerParameter myDeciderID_decider_17_0_2_edc0357_1352328157560_221675_19918 = null;

            public IntegerParameter _17_0_2_edc0357_1352328160497_756409_21298 = null;

            public BooleanParameter objectToPass = null;

            public Dependency< Power > _17_0_2_edc0357_1352328160498_862342_21299Dependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328157560_221675_19918_existsDependency = null;

            public Dependency< Integer > myDeciderID_decider_17_0_2_edc0357_1352328157560_221675_19918Dependency = null;

            public Dependency< Integer > _17_0_2_edc0357_1352328160497_756409_21298Dependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Effect effect303 = null;

            public Parameter effect303Var = null;

            public Effect effect304 = null;

            public Parameter effect304Var = null;

            public Effect effect305 = null;

            public Parameter effect305Var = null;

            public ElaborationRule elaborationRule306 = null;

            public void init_17_0_2_edc0357_1352328157557_569409_19913Members() {
                try {
                    if (_17_0_2_edc0357_1352328160498_862342_21299 == null) _17_0_2_edc0357_1352328160498_862342_21299 = new Parameter<Power>("_17_0_2_edc0357_1352328160498_862342_21299", null, (Power) null, this);
                    if (_17_0_2_edc0357_1352328157560_221675_19918_exists == null) _17_0_2_edc0357_1352328157560_221675_19918_exists = new BooleanParameter("_17_0_2_edc0357_1352328157560_221675_19918_exists", (Boolean) false, this);
                    if (myDeciderID_decider_17_0_2_edc0357_1352328157560_221675_19918 == null) myDeciderID_decider_17_0_2_edc0357_1352328157560_221675_19918 = new IntegerParameter("myDeciderID_decider_17_0_2_edc0357_1352328157560_221675_19918", (Integer) 2, this);
                    if (_17_0_2_edc0357_1352328160497_756409_21298 == null) _17_0_2_edc0357_1352328160497_756409_21298 = new IntegerParameter("_17_0_2_edc0357_1352328160497_756409_21298", (Integer) null, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect303VarV = sig_17_0_2_edc0357_1352328157561_793351_19927;
                    effect303Var = new Parameter("effect303Var", null, null, this);
                    addDependency(effect303Var, new Expression(effect303VarV));
                    effect303 = new EffectFunction(new FunctionCall(effect303Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect304VarV = decider_17_0_2_edc0357_1352328157560_221675_19918;
                    effect304Var = new Parameter("effect304Var", null, null, this);
                    addDependency(effect304Var, new Expression(effect304VarV));
                    effect304 = new EffectFunction(new FunctionCall(effect304Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, myDeciderID_decider_17_0_2_edc0357_1352328157560_221675_19918 }));
                    Object effect305VarV = power__17_0_2_edc0357_1352328156609_70990_19604;
                    effect305Var = new Parameter("effect305Var", null, null, this);
                    addDependency(effect305Var, new Expression(effect305VarV));
                    effect305 = new EffectFunction(new FunctionCall(effect305Var, ClassUtils.getMethodForArgTypes("TimeVaryingMap<Integer>", "powersystem", "setValue", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { startTime, _17_0_2_edc0357_1352328160497_756409_21298 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328157557_569409_19913Collections() {
                parameters.add(_17_0_2_edc0357_1352328160498_862342_21299);
                parameters.add(_17_0_2_edc0357_1352328157560_221675_19918_exists);
                parameters.add(myDeciderID_decider_17_0_2_edc0357_1352328157560_221675_19918);
                parameters.add(_17_0_2_edc0357_1352328160497_756409_21298);
                parameters.add(objectToPass);
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

            public void init_17_0_2_edc0357_1352328157557_569409_19913Dependencies() {
                addDependency(_17_0_2_edc0357_1352328160498_862342_21299, new Expression<Power>(new FunctionCall(sig_17_0_2_edc0357_1352328157561_782696_19924, ClassUtils.getMethodForArgTypes("ObjectFlow<Power>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_edc0357_1352328157560_221675_19918_exists, new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328157560_221675_19918, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328157560_221675_19918, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "maxSize"), new Object[] {})))).functionCall), new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328157560_221675_19918, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_edc0357_1352328157560_221675_19918))).functionCall))).functionCall), new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall))).functionCall));
                addDependency(myDeciderID_decider_17_0_2_edc0357_1352328157560_221675_19918, new Expression<Integer>(2));
                addDependency(_17_0_2_edc0357_1352328160497_756409_21298, new Expression<Integer>(new FunctionCall(sig_17_0_2_edc0357_1352328157560_839047_19921, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(true));
            }

            public void init_17_0_2_edc0357_1352328157557_569409_19913Elaborations() {
                init_17_0_2_edc0357_1352328157557_569409_19913Dependencies();
                Expression<?>[] arguments306 = new Expression<?>[1];
                arguments306[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition306 = new Expression<Boolean>(_17_0_2_edc0357_1352328157560_221675_19918_exists);
                elaborationRule306 = addElaborationRule(condition306, _17_0_2_edc0357_1352328156604_3589_19598.this, Power._17_0_2_edc0357_1352328156604_3589_19598._17_0_2_edc0357_1352328157560_221675_19918.class, "end_join_JoinNode_intialize", arguments306);
            }
        }

        public class _17_0_2_edc0357_1352328157558_917938_19914 extends DurativeEvent {

            public _17_0_2_edc0357_1352328157558_917938_19914() {
                super();
                init_17_0_2_edc0357_1352328157558_917938_19914Members();
                init_17_0_2_edc0357_1352328157558_917938_19914Collections();
                init_17_0_2_edc0357_1352328157558_917938_19914Elaborations();
            }

            public _17_0_2_edc0357_1352328157558_917938_19914(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328157558_917938_19914Members();
                init_17_0_2_edc0357_1352328157558_917938_19914Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328157558_917938_19914Elaborations();
                fixTimeDependencies();
            }

            public IntegerParameter myDeciderID_decider_17_0_2_edc0357_1352328157557_569409_19913 = null;

            public BooleanParameter _17_0_2_edc0357_1352328157557_569409_19913_exists = null;

            public IntegerParameter _17_0_2_edc0357_1352328160499_949403_21301 = null;

            public BooleanParameter objectToPass = null;

            public Dependency< Integer > myDeciderID_decider_17_0_2_edc0357_1352328157557_569409_19913Dependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328157557_569409_19913_existsDependency = null;

            public Dependency< Integer > _17_0_2_edc0357_1352328160499_949403_21301Dependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Effect effect307 = null;

            public Parameter effect307Var = null;

            public Effect effect308 = null;

            public Parameter effect308Var = null;

            public ElaborationRule elaborationRule309 = null;

            public void init_17_0_2_edc0357_1352328157558_917938_19914Members() {
                try {
                    if (myDeciderID_decider_17_0_2_edc0357_1352328157557_569409_19913 == null) myDeciderID_decider_17_0_2_edc0357_1352328157557_569409_19913 = new IntegerParameter("myDeciderID_decider_17_0_2_edc0357_1352328157557_569409_19913", (Integer) 2, this);
                    if (_17_0_2_edc0357_1352328157557_569409_19913_exists == null) _17_0_2_edc0357_1352328157557_569409_19913_exists = new BooleanParameter("_17_0_2_edc0357_1352328157557_569409_19913_exists", (Boolean) false, this);
                    if (_17_0_2_edc0357_1352328160499_949403_21301 == null) _17_0_2_edc0357_1352328160499_949403_21301 = new IntegerParameter("_17_0_2_edc0357_1352328160499_949403_21301", (Integer) 5, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect307VarV = sig_17_0_2_edc0357_1352328157560_839047_19921;
                    effect307Var = new Parameter("effect307Var", null, null, this);
                    addDependency(effect307Var, new Expression(effect307VarV));
                    effect307 = new EffectFunction(new FunctionCall(effect307Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { _17_0_2_edc0357_1352328160499_949403_21301, endTime }));
                    Object effect308VarV = decider_17_0_2_edc0357_1352328157557_569409_19913;
                    effect308Var = new Parameter("effect308Var", null, null, this);
                    addDependency(effect308Var, new Expression(effect308VarV));
                    effect308 = new EffectFunction(new FunctionCall(effect308Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, myDeciderID_decider_17_0_2_edc0357_1352328157557_569409_19913 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328157558_917938_19914Collections() {
                parameters.add(myDeciderID_decider_17_0_2_edc0357_1352328157557_569409_19913);
                parameters.add(_17_0_2_edc0357_1352328157557_569409_19913_exists);
                parameters.add(_17_0_2_edc0357_1352328160499_949403_21301);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect307Var = new TreeSet<Effect>();
                effectsForeffect307Var.add(effect307);
                addEffects((Parameter<?>) effect307Var, effectsForeffect307Var);
                Set<Effect> effectsForeffect308Var = new TreeSet<Effect>();
                effectsForeffect308Var.add(effect308);
                addEffects((Parameter<?>) effect308Var, effectsForeffect308Var);
            }

            public void init_17_0_2_edc0357_1352328157558_917938_19914Dependencies() {
                addDependency(myDeciderID_decider_17_0_2_edc0357_1352328157557_569409_19913, new Expression<Integer>(2));
                addDependency(_17_0_2_edc0357_1352328157557_569409_19913_exists, new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328157557_569409_19913, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328157557_569409_19913, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "maxSize"), new Object[] {})))).functionCall), new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328157557_569409_19913, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_edc0357_1352328157557_569409_19913))).functionCall))).functionCall), new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall))).functionCall));
                addDependency(_17_0_2_edc0357_1352328160499_949403_21301, new Expression<Integer>(5));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new FunctionCall(sig_17_0_2_edc0357_1352328157561_414533_19929, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_edc0357_1352328157558_917938_19914Elaborations() {
                init_17_0_2_edc0357_1352328157558_917938_19914Dependencies();
                Expression<?>[] arguments309 = new Expression<?>[1];
                arguments309[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition309 = new Expression<Boolean>(_17_0_2_edc0357_1352328157557_569409_19913_exists);
                elaborationRule309 = addElaborationRule(condition309, _17_0_2_edc0357_1352328156604_3589_19598.this, Power._17_0_2_edc0357_1352328156604_3589_19598._17_0_2_edc0357_1352328157557_569409_19913.class, "power_struct_add_AddStructuralFeatureValueAction_intialize", arguments309);
            }
        }

        public class _17_0_2_edc0357_1352328157558_803430_19915 extends DurativeEvent {

            public _17_0_2_edc0357_1352328157558_803430_19915() {
                super();
                init_17_0_2_edc0357_1352328157558_803430_19915Members();
                init_17_0_2_edc0357_1352328157558_803430_19915Collections();
                init_17_0_2_edc0357_1352328157558_803430_19915Elaborations();
            }

            public _17_0_2_edc0357_1352328157558_803430_19915(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328157558_803430_19915Members();
                init_17_0_2_edc0357_1352328157558_803430_19915Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328157558_803430_19915Elaborations();
                fixTimeDependencies();
            }

            public IntegerParameter myDeciderID_decider_17_0_2_edc0357_1352328157557_375441_19912 = null;

            public BooleanParameter _17_0_2_edc0357_1352328157557_375441_19912_exists = null;

            public BooleanParameter objectToPass = null;

            public IntegerParameter _17_0_2_edc0357_1352328160500_312417_21303 = null;

            public Dependency< Integer > myDeciderID_decider_17_0_2_edc0357_1352328157557_375441_19912Dependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328157557_375441_19912_existsDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Dependency< Integer > _17_0_2_edc0357_1352328160500_312417_21303Dependency = null;

            public Effect effect310 = null;

            public Parameter effect310Var = null;

            public Effect effect311 = null;

            public Parameter effect311Var = null;

            public ElaborationRule elaborationRule312 = null;

            public void init_17_0_2_edc0357_1352328157558_803430_19915Members() {
                try {
                    if (myDeciderID_decider_17_0_2_edc0357_1352328157557_375441_19912 == null) myDeciderID_decider_17_0_2_edc0357_1352328157557_375441_19912 = new IntegerParameter("myDeciderID_decider_17_0_2_edc0357_1352328157557_375441_19912", (Integer) 2, this);
                    if (_17_0_2_edc0357_1352328157557_375441_19912_exists == null) _17_0_2_edc0357_1352328157557_375441_19912_exists = new BooleanParameter("_17_0_2_edc0357_1352328157557_375441_19912_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (_17_0_2_edc0357_1352328160500_312417_21303 == null) _17_0_2_edc0357_1352328160500_312417_21303 = new IntegerParameter("_17_0_2_edc0357_1352328160500_312417_21303", (Integer) 3, this);
                    Object effect310VarV = sig_17_0_2_edc0357_1352328157560_366387_19922;
                    effect310Var = new Parameter("effect310Var", null, null, this);
                    addDependency(effect310Var, new Expression(effect310VarV));
                    effect310 = new EffectFunction(new FunctionCall(effect310Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { _17_0_2_edc0357_1352328160500_312417_21303, endTime }));
                    Object effect311VarV = decider_17_0_2_edc0357_1352328157557_375441_19912;
                    effect311Var = new Parameter("effect311Var", null, null, this);
                    addDependency(effect311Var, new Expression(effect311VarV));
                    effect311 = new EffectFunction(new FunctionCall(effect311Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, myDeciderID_decider_17_0_2_edc0357_1352328157557_375441_19912 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328157558_803430_19915Collections() {
                parameters.add(myDeciderID_decider_17_0_2_edc0357_1352328157557_375441_19912);
                parameters.add(_17_0_2_edc0357_1352328157557_375441_19912_exists);
                parameters.add(objectToPass);
                parameters.add(_17_0_2_edc0357_1352328160500_312417_21303);
                Set<Effect> effectsForeffect310Var = new TreeSet<Effect>();
                effectsForeffect310Var.add(effect310);
                addEffects((Parameter<?>) effect310Var, effectsForeffect310Var);
                Set<Effect> effectsForeffect311Var = new TreeSet<Effect>();
                effectsForeffect311Var.add(effect311);
                addEffects((Parameter<?>) effect311Var, effectsForeffect311Var);
            }

            public void init_17_0_2_edc0357_1352328157558_803430_19915Dependencies() {
                addDependency(myDeciderID_decider_17_0_2_edc0357_1352328157557_375441_19912, new Expression<Integer>(2));
                addDependency(_17_0_2_edc0357_1352328157557_375441_19912_exists, new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328157557_375441_19912, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328157557_375441_19912, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "maxSize"), new Object[] {})))).functionCall), new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328157557_375441_19912, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_edc0357_1352328157557_375441_19912))).functionCall))).functionCall), new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall))).functionCall));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new FunctionCall(sig_17_0_2_edc0357_1352328157561_418883_19928, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_edc0357_1352328160500_312417_21303, new Expression<Integer>(3));
            }

            public void init_17_0_2_edc0357_1352328157558_803430_19915Elaborations() {
                init_17_0_2_edc0357_1352328157558_803430_19915Dependencies();
                Expression<?>[] arguments312 = new Expression<?>[1];
                arguments312[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition312 = new Expression<Boolean>(_17_0_2_edc0357_1352328157557_375441_19912_exists);
                elaborationRule312 = addElaborationRule(condition312, _17_0_2_edc0357_1352328156604_3589_19598.this, Power._17_0_2_edc0357_1352328156604_3589_19598._17_0_2_edc0357_1352328157557_375441_19912.class, "load_struct_add_AddStructuralFeatureValueAction_intialize", arguments312);
            }
        }

        public class _17_0_2_edc0357_1352328157559_338063_19916 extends DurativeEvent {

            public _17_0_2_edc0357_1352328157559_338063_19916() {
                super();
                init_17_0_2_edc0357_1352328157559_338063_19916Members();
                init_17_0_2_edc0357_1352328157559_338063_19916Collections();
                init_17_0_2_edc0357_1352328157559_338063_19916Elaborations();
            }

            public _17_0_2_edc0357_1352328157559_338063_19916(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328157559_338063_19916Members();
                init_17_0_2_edc0357_1352328157559_338063_19916Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328157559_338063_19916Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter objectToPass = null;

            public BooleanParameter _17_0_2_edc0357_1352328157559_489289_19917_exists = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328157559_489289_19917_existsDependency = null;

            public Effect effect313 = null;

            public Parameter effect313Var = null;

            public ElaborationRule elaborationRule314 = null;

            public void init_17_0_2_edc0357_1352328157559_338063_19916Members() {
                try {
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (_17_0_2_edc0357_1352328157559_489289_19917_exists == null) _17_0_2_edc0357_1352328157559_489289_19917_exists = new BooleanParameter("_17_0_2_edc0357_1352328157559_489289_19917_exists", (Boolean) false, this);
                    Object effect313VarV = sig_17_0_2_edc0357_1352328157561_196813_19930;
                    effect313Var = new Parameter("effect313Var", null, null, this);
                    addDependency(effect313Var, new Expression(effect313VarV));
                    effect313 = new EffectFunction(new FunctionCall(effect313Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328157559_338063_19916Collections() {
                parameters.add(objectToPass);
                parameters.add(_17_0_2_edc0357_1352328157559_489289_19917_exists);
                Set<Effect> effectsForeffect313Var = new TreeSet<Effect>();
                effectsForeffect313Var.add(effect313);
                addEffects((Parameter<?>) effect313Var, effectsForeffect313Var);
            }

            public void init_17_0_2_edc0357_1352328157559_338063_19916Dependencies() {
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(true));
                addDependency(_17_0_2_edc0357_1352328157559_489289_19917_exists, new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall));
            }

            public void init_17_0_2_edc0357_1352328157559_338063_19916Elaborations() {
                init_17_0_2_edc0357_1352328157559_338063_19916Dependencies();
                Expression<?>[] arguments314 = new Expression<?>[1];
                arguments314[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition314 = new Expression<Boolean>(_17_0_2_edc0357_1352328157559_489289_19917_exists);
                elaborationRule314 = addElaborationRule(condition314, _17_0_2_edc0357_1352328156604_3589_19598.this, Power._17_0_2_edc0357_1352328156604_3589_19598._17_0_2_edc0357_1352328157559_489289_19917.class, "start_fok_ForkNode_intialize", arguments314);
            }
        }

        public class _17_0_2_edc0357_1352328157559_489289_19917 extends DurativeEvent {

            public _17_0_2_edc0357_1352328157559_489289_19917() {
                super();
                init_17_0_2_edc0357_1352328157559_489289_19917Members();
                init_17_0_2_edc0357_1352328157559_489289_19917Collections();
                init_17_0_2_edc0357_1352328157559_489289_19917Elaborations();
            }

            public _17_0_2_edc0357_1352328157559_489289_19917(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328157559_489289_19917Members();
                init_17_0_2_edc0357_1352328157559_489289_19917Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328157559_489289_19917Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_edc0357_1352328157558_917938_19914_exists = null;

            public BooleanParameter _17_0_2_edc0357_1352328157558_803430_19915_exists = null;

            public BooleanParameter _17_0_2_edc0357_1352328157556_683256_19910_exists = null;

            public BooleanParameter objectToPass = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328157558_917938_19914_existsDependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328157558_803430_19915_existsDependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328157556_683256_19910_existsDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Effect effect315 = null;

            public Parameter effect315Var = null;

            public Effect effect316 = null;

            public Parameter effect316Var = null;

            public Effect effect317 = null;

            public Parameter effect317Var = null;

            public ElaborationRule elaborationRule318 = null;

            public ElaborationRule elaborationRule319 = null;

            public ElaborationRule elaborationRule320 = null;

            public void init_17_0_2_edc0357_1352328157559_489289_19917Members() {
                try {
                    if (_17_0_2_edc0357_1352328157558_917938_19914_exists == null) _17_0_2_edc0357_1352328157558_917938_19914_exists = new BooleanParameter("_17_0_2_edc0357_1352328157558_917938_19914_exists", (Boolean) false, this);
                    if (_17_0_2_edc0357_1352328157558_803430_19915_exists == null) _17_0_2_edc0357_1352328157558_803430_19915_exists = new BooleanParameter("_17_0_2_edc0357_1352328157558_803430_19915_exists", (Boolean) false, this);
                    if (_17_0_2_edc0357_1352328157556_683256_19910_exists == null) _17_0_2_edc0357_1352328157556_683256_19910_exists = new BooleanParameter("_17_0_2_edc0357_1352328157556_683256_19910_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect315VarV = sig_17_0_2_edc0357_1352328157561_418883_19928;
                    effect315Var = new Parameter("effect315Var", null, null, this);
                    addDependency(effect315Var, new Expression(effect315VarV));
                    effect315 = new EffectFunction(new FunctionCall(effect315Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect316VarV = sig_17_0_2_edc0357_1352328157561_414533_19929;
                    effect316Var = new Parameter("effect316Var", null, null, this);
                    addDependency(effect316Var, new Expression(effect316VarV));
                    effect316 = new EffectFunction(new FunctionCall(effect316Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect317VarV = sig_17_0_2_edc0357_1352328157561_582031_19931;
                    effect317Var = new Parameter("effect317Var", null, null, this);
                    addDependency(effect317Var, new Expression(effect317VarV));
                    effect317 = new EffectFunction(new FunctionCall(effect317Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328157559_489289_19917Collections() {
                parameters.add(_17_0_2_edc0357_1352328157558_917938_19914_exists);
                parameters.add(_17_0_2_edc0357_1352328157558_803430_19915_exists);
                parameters.add(_17_0_2_edc0357_1352328157556_683256_19910_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect315Var = new TreeSet<Effect>();
                effectsForeffect315Var.add(effect315);
                addEffects((Parameter<?>) effect315Var, effectsForeffect315Var);
                Set<Effect> effectsForeffect316Var = new TreeSet<Effect>();
                effectsForeffect316Var.add(effect316);
                addEffects((Parameter<?>) effect316Var, effectsForeffect316Var);
                Set<Effect> effectsForeffect317Var = new TreeSet<Effect>();
                effectsForeffect317Var.add(effect317);
                addEffects((Parameter<?>) effect317Var, effectsForeffect317Var);
            }

            public void init_17_0_2_edc0357_1352328157559_489289_19917Dependencies() {
                addDependency(_17_0_2_edc0357_1352328157558_917938_19914_exists, new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall));
                addDependency(_17_0_2_edc0357_1352328157558_803430_19915_exists, new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall));
                addDependency(_17_0_2_edc0357_1352328157556_683256_19910_exists, new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new FunctionCall(sig_17_0_2_edc0357_1352328157561_196813_19930, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_edc0357_1352328157559_489289_19917Elaborations() {
                init_17_0_2_edc0357_1352328157559_489289_19917Dependencies();
                Expression<?>[] arguments318 = new Expression<?>[1];
                arguments318[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition318 = new Expression<Boolean>(_17_0_2_edc0357_1352328157558_917938_19914_exists);
                elaborationRule318 = addElaborationRule(condition318, _17_0_2_edc0357_1352328156604_3589_19598.this, Power._17_0_2_edc0357_1352328156604_3589_19598._17_0_2_edc0357_1352328157558_917938_19914.class, "power_val_spec_ValueSpecificationAction_intialize", arguments318);
                Expression<?>[] arguments319 = new Expression<?>[1];
                arguments319[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition319 = new Expression<Boolean>(_17_0_2_edc0357_1352328157556_683256_19910_exists);
                elaborationRule319 = addElaborationRule(condition319, _17_0_2_edc0357_1352328156604_3589_19598.this, Power._17_0_2_edc0357_1352328156604_3589_19598._17_0_2_edc0357_1352328157556_683256_19910.class, "readSelf_ReadSelfAction_intialize", arguments319);
                Expression<?>[] arguments320 = new Expression<?>[1];
                arguments320[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition320 = new Expression<Boolean>(_17_0_2_edc0357_1352328157558_803430_19915_exists);
                elaborationRule320 = addElaborationRule(condition320, _17_0_2_edc0357_1352328156604_3589_19598.this, Power._17_0_2_edc0357_1352328156604_3589_19598._17_0_2_edc0357_1352328157558_803430_19915.class, "load_val_spec_ValueSpecificationAction_intialize", arguments320);
            }
        }

        public class _17_0_2_edc0357_1352328157560_221675_19918 extends DurativeEvent {

            public _17_0_2_edc0357_1352328157560_221675_19918() {
                super();
                init_17_0_2_edc0357_1352328157560_221675_19918Members();
                init_17_0_2_edc0357_1352328157560_221675_19918Collections();
                init_17_0_2_edc0357_1352328157560_221675_19918Elaborations();
            }

            public _17_0_2_edc0357_1352328157560_221675_19918(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328157560_221675_19918Members();
                init_17_0_2_edc0357_1352328157560_221675_19918Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328157560_221675_19918Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter objectToPass1 = null;

            public BooleanParameter objectToPass = null;

            public Dependency< Boolean > objectToPass1Dependency = null;

            public Dependency< Integer > finalNode_startTimeDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Effect effect321 = null;

            public Parameter effect321Var = null;

            public void init_17_0_2_edc0357_1352328157560_221675_19918Members() {
                try {
                    if (objectToPass1 == null) objectToPass1 = new BooleanParameter("objectToPass1", (Boolean) null, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect321VarV = sig_17_0_2_edc0357_1352328157561_306367_19925;
                    effect321Var = new Parameter("effect321Var", null, null, this);
                    addDependency(effect321Var, new Expression(effect321VarV));
                    effect321 = new EffectFunction(new FunctionCall(effect321Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328157560_221675_19918Collections() {
                parameters.add(objectToPass1);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect321Var = new TreeSet<Effect>();
                effectsForeffect321Var.add(effect321);
                addEffects((Parameter<?>) effect321Var, effectsForeffect321Var);
            }

            public void init_17_0_2_edc0357_1352328157560_221675_19918Dependencies() {
                addDependency(objectToPass1, new Expression<Boolean>(new FunctionCall(sig_17_0_2_edc0357_1352328157561_793351_19927, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(finalNode_startTime, new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new FunctionCall(sig_17_0_2_edc0357_1352328157561_874082_19926, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_edc0357_1352328157560_221675_19918Elaborations() {
                init_17_0_2_edc0357_1352328157560_221675_19918Dependencies();
            }
        }

        public class _17_0_2_edc0357_1352328157560_444814_19919 extends DurativeEvent {

            public _17_0_2_edc0357_1352328157560_444814_19919() {
                super();
                init_17_0_2_edc0357_1352328157560_444814_19919Members();
                init_17_0_2_edc0357_1352328157560_444814_19919Collections();
                init_17_0_2_edc0357_1352328157560_444814_19919Elaborations();
            }

            public _17_0_2_edc0357_1352328157560_444814_19919(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328157560_444814_19919Members();
                init_17_0_2_edc0357_1352328157560_444814_19919Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328157560_444814_19919Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter objectToPass = null;

            public Dependency< Integer > endTimeDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Dependency< Integer > finalNode_endTimeDependency = null;

            public void init_17_0_2_edc0357_1352328157560_444814_19919Members() {
                try {
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328157560_444814_19919Collections() {
                parameters.add(objectToPass);
            }

            public void init_17_0_2_edc0357_1352328157560_444814_19919Dependencies() {
                addDependency(endTime, new Expression<Integer>((new Functions.Plus(new Expression<Integer>(startTime), new Expression<Integer>(duration))).functionCall));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new FunctionCall(sig_17_0_2_edc0357_1352328157561_306367_19925, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(finalNode_endTime, new Expression<Integer>(endTime));
            }

            public void init_17_0_2_edc0357_1352328157560_444814_19919Elaborations() {
                init_17_0_2_edc0357_1352328157560_444814_19919Dependencies();
            }
        }

        public _17_0_2_edc0357_1352328156604_3589_19598(Expression<Integer> startTime, Expression<DurativeEvent> caller) {
            super();
            init_17_0_2_edc0357_1352328156604_3589_19598Members();
            init_17_0_2_edc0357_1352328156604_3589_19598Collections();
            addDependency(this.startTime, startTime);
            addDependency(this.caller, caller);
            init_17_0_2_edc0357_1352328156604_3589_19598Elaborations();
            fixTimeDependencies();
        }

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_edc0357_1352328157561_418883_19928 = null;

        public Parameter< ObjectFlow<Power> > sig_17_0_2_edc0357_1352328157560_555636_19920 = null;

        public Parameter< TimeVaryingList<Integer> > decider_17_0_2_edc0357_1352328157557_375441_19912 = null;

        public Parameter< DurativeEvent > caller = null;

        public BooleanParameter _17_0_2_edc0357_1352328157560_444814_19919_exists = null;

        public Parameter< ObjectFlow<Power> > sig_17_0_2_edc0357_1352328157561_782696_19924 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_edc0357_1352328157561_582031_19931 = null;

        public IntegerParameter finalNode_startTime = null;

        public Parameter< TimeVaryingList<Integer> > decider_17_0_2_edc0357_1352328157557_569409_19913 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_edc0357_1352328157561_793351_19927 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_2_edc0357_1352328157560_366387_19922 = null;

        public IntegerParameter finalNode_endTime = null;

        public Parameter< ObjectFlow<Power> > sig_17_0_2_edc0357_1352328157561_747651_19923 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_edc0357_1352328157561_874082_19926 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_2_edc0357_1352328157560_839047_19921 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_edc0357_1352328157561_196813_19930 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_edc0357_1352328157561_414533_19929 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_edc0357_1352328157561_306367_19925 = null;

        public Parameter< TimeVaryingList<Integer> > decider_17_0_2_edc0357_1352328157560_221675_19918 = null;

        public Dependency< Integer > caller_endTimeDependency = null;

        public Dependency< Boolean > _17_0_2_edc0357_1352328157560_444814_19919_existsDependency = null;

        public Dependency< Integer > endTimeDependency = null;

        public ElaborationRule elaborationRule289 = null;

        public ElaborationRule elaborationRule290 = null;

        public void init_17_0_2_edc0357_1352328156604_3589_19598Members() {
            try {
                if (sig_17_0_2_edc0357_1352328157561_418883_19928 == null) sig_17_0_2_edc0357_1352328157561_418883_19928 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328157561_418883_19928", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328157561_418883_19928" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328157560_555636_19920 == null) sig_17_0_2_edc0357_1352328157560_555636_19920 = new Parameter<ObjectFlow<Power>>("sig_17_0_2_edc0357_1352328157560_555636_19920", null, (ObjectFlow<Power>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328157560_555636_19920" })).evaluate(true), this);
                if (decider_17_0_2_edc0357_1352328157557_375441_19912 == null) decider_17_0_2_edc0357_1352328157557_375441_19912 = new Parameter<TimeVaryingList<Integer>>("decider_17_0_2_edc0357_1352328157557_375441_19912", null, (TimeVaryingList<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingList.class, java.lang.String.class, int.class), new Object[] { "decider_17_0_2_edc0357_1352328157557_375441_19912", 2 })).evaluate(true), this);
                if (caller == null) caller = new Parameter<DurativeEvent>("caller", null, (DurativeEvent) null, this);
                if (_17_0_2_edc0357_1352328157560_444814_19919_exists == null) _17_0_2_edc0357_1352328157560_444814_19919_exists = new BooleanParameter("_17_0_2_edc0357_1352328157560_444814_19919_exists", (Boolean) false, this);
                if (sig_17_0_2_edc0357_1352328157561_782696_19924 == null) sig_17_0_2_edc0357_1352328157561_782696_19924 = new Parameter<ObjectFlow<Power>>("sig_17_0_2_edc0357_1352328157561_782696_19924", null, (ObjectFlow<Power>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328157561_782696_19924" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328157561_582031_19931 == null) sig_17_0_2_edc0357_1352328157561_582031_19931 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328157561_582031_19931", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328157561_582031_19931" })).evaluate(true), this);
                if (finalNode_startTime == null) finalNode_startTime = new IntegerParameter("finalNode_startTime", (Integer) 84000, this);
                if (decider_17_0_2_edc0357_1352328157557_569409_19913 == null) decider_17_0_2_edc0357_1352328157557_569409_19913 = new Parameter<TimeVaryingList<Integer>>("decider_17_0_2_edc0357_1352328157557_569409_19913", null, (TimeVaryingList<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingList.class, java.lang.String.class, int.class), new Object[] { "decider_17_0_2_edc0357_1352328157557_569409_19913", 2 })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328157561_793351_19927 == null) sig_17_0_2_edc0357_1352328157561_793351_19927 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328157561_793351_19927", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328157561_793351_19927" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328157560_366387_19922 == null) sig_17_0_2_edc0357_1352328157560_366387_19922 = new Parameter<ObjectFlow<Integer>>("sig_17_0_2_edc0357_1352328157560_366387_19922", null, (ObjectFlow<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328157560_366387_19922" })).evaluate(true), this);
                if (finalNode_endTime == null) finalNode_endTime = new IntegerParameter("finalNode_endTime", (Integer) 84000, this);
                if (sig_17_0_2_edc0357_1352328157561_747651_19923 == null) sig_17_0_2_edc0357_1352328157561_747651_19923 = new Parameter<ObjectFlow<Power>>("sig_17_0_2_edc0357_1352328157561_747651_19923", null, (ObjectFlow<Power>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328157561_747651_19923" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328157561_874082_19926 == null) sig_17_0_2_edc0357_1352328157561_874082_19926 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328157561_874082_19926", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328157561_874082_19926" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328157560_839047_19921 == null) sig_17_0_2_edc0357_1352328157560_839047_19921 = new Parameter<ObjectFlow<Integer>>("sig_17_0_2_edc0357_1352328157560_839047_19921", null, (ObjectFlow<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328157560_839047_19921" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328157561_196813_19930 == null) sig_17_0_2_edc0357_1352328157561_196813_19930 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328157561_196813_19930", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328157561_196813_19930" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328157561_414533_19929 == null) sig_17_0_2_edc0357_1352328157561_414533_19929 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328157561_414533_19929", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328157561_414533_19929" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328157561_306367_19925 == null) sig_17_0_2_edc0357_1352328157561_306367_19925 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328157561_306367_19925", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328157561_306367_19925" })).evaluate(true), this);
                if (decider_17_0_2_edc0357_1352328157560_221675_19918 == null) decider_17_0_2_edc0357_1352328157560_221675_19918 = new Parameter<TimeVaryingList<Integer>>("decider_17_0_2_edc0357_1352328157560_221675_19918", null, (TimeVaryingList<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingList.class, java.lang.String.class, int.class), new Object[] { "decider_17_0_2_edc0357_1352328157560_221675_19918", 2 })).evaluate(true), this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void init_17_0_2_edc0357_1352328156604_3589_19598Collections() {
            parameters.add(sig_17_0_2_edc0357_1352328157561_418883_19928);
            parameters.add(sig_17_0_2_edc0357_1352328157560_555636_19920);
            parameters.add(decider_17_0_2_edc0357_1352328157557_375441_19912);
            parameters.add(caller);
            parameters.add(_17_0_2_edc0357_1352328157560_444814_19919_exists);
            parameters.add(sig_17_0_2_edc0357_1352328157561_782696_19924);
            parameters.add(sig_17_0_2_edc0357_1352328157561_582031_19931);
            parameters.add(finalNode_startTime);
            parameters.add(decider_17_0_2_edc0357_1352328157557_569409_19913);
            parameters.add(sig_17_0_2_edc0357_1352328157561_793351_19927);
            parameters.add(sig_17_0_2_edc0357_1352328157560_366387_19922);
            parameters.add(finalNode_endTime);
            parameters.add(sig_17_0_2_edc0357_1352328157561_747651_19923);
            parameters.add(sig_17_0_2_edc0357_1352328157561_874082_19926);
            parameters.add(sig_17_0_2_edc0357_1352328157560_839047_19921);
            parameters.add(sig_17_0_2_edc0357_1352328157561_196813_19930);
            parameters.add(sig_17_0_2_edc0357_1352328157561_414533_19929);
            parameters.add(sig_17_0_2_edc0357_1352328157561_306367_19925);
            parameters.add(decider_17_0_2_edc0357_1352328157560_221675_19918);
        }

        public void init_17_0_2_edc0357_1352328156604_3589_19598Dependencies() {
            caller.getValue(true).addDependency(caller.getValue(true).endTime, new Expression<Integer>(finalNode_endTime));
            addDependency(_17_0_2_edc0357_1352328157560_444814_19919_exists, new Expression<Boolean>(new FunctionCall(sig_17_0_2_edc0357_1352328157561_306367_19925, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>((new Functions.Minus(new Expression<Integer>(finalNode_startTime), new Expression<Integer>(1))).functionCall) })));
            addDependency(endTime, new Expression<Integer>(finalNode_endTime));
        }

        public void init_17_0_2_edc0357_1352328156604_3589_19598Elaborations() {
            init_17_0_2_edc0357_1352328156604_3589_19598Dependencies();
            Expression<?>[] arguments289 = new Expression<?>[1];
            arguments289[0] = new Expression<Integer>(startTime);
            Expression<Boolean> condition289 = new Expression<Boolean>(true);
            elaborationRule289 = addElaborationRule(condition289, _17_0_2_edc0357_1352328156604_3589_19598.this, Power._17_0_2_edc0357_1352328156604_3589_19598._17_0_2_edc0357_1352328157559_338063_19916.class, "_InitialNode_intialize", arguments289);
            Expression<?>[] arguments290 = new Expression<?>[1];
            arguments290[0] = new Expression<Integer>(finalNode_startTime);
            Expression<Boolean> condition290 = new Expression<Boolean>(_17_0_2_edc0357_1352328157560_444814_19919_exists);
            elaborationRule290 = addElaborationRule(condition290, _17_0_2_edc0357_1352328156604_3589_19598.this, Power._17_0_2_edc0357_1352328156604_3589_19598._17_0_2_edc0357_1352328157560_444814_19919.class, "_ActivityFinalNode_intialize", arguments290);
        }
    }

    public class _17_0_2_edc0357_1352328156605_909758_19599 extends DurativeEvent {

        public _17_0_2_edc0357_1352328156605_909758_19599() {
            super();
            init_17_0_2_edc0357_1352328156605_909758_19599Members();
            init_17_0_2_edc0357_1352328156605_909758_19599Collections();
            init_17_0_2_edc0357_1352328156605_909758_19599Elaborations();
        }

        public class _17_0_2_edc0357_1352328157734_471476_19963 extends DurativeEvent {

            public _17_0_2_edc0357_1352328157734_471476_19963() {
                super();
                init_17_0_2_edc0357_1352328157734_471476_19963Members();
                init_17_0_2_edc0357_1352328157734_471476_19963Collections();
                init_17_0_2_edc0357_1352328157734_471476_19963Elaborations();
            }

            public _17_0_2_edc0357_1352328157734_471476_19963(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328157734_471476_19963Members();
                init_17_0_2_edc0357_1352328157734_471476_19963Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328157734_471476_19963Elaborations();
                fixTimeDependencies();
            }

            public Parameter< Power > _17_0_2_edc0357_1352328160505_205739_21317 = null;

            public BooleanParameter objectToPass = null;

            public BooleanParameter _17_0_2_edc0357_1352328157739_887416_19970_exists = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328157739_887416_19970_existsDependency = null;

            public Effect effect324 = null;

            public Parameter effect324Var = null;

            public ElaborationRule elaborationRule325 = null;

            public void init_17_0_2_edc0357_1352328157734_471476_19963Members() {
                try {
                    if (_17_0_2_edc0357_1352328160505_205739_21317 == null) _17_0_2_edc0357_1352328160505_205739_21317 = new Parameter<Power>("_17_0_2_edc0357_1352328160505_205739_21317", null, (Power) Power.this, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (_17_0_2_edc0357_1352328157739_887416_19970_exists == null) _17_0_2_edc0357_1352328157739_887416_19970_exists = new BooleanParameter("_17_0_2_edc0357_1352328157739_887416_19970_exists", (Boolean) false, this);
                    Object effect324VarV = sig_17_0_2_edc0357_1352328157741_404429_19975;
                    effect324Var = new Parameter("effect324Var", null, null, this);
                    addDependency(effect324Var, new Expression(effect324VarV));
                    effect324 = new EffectFunction(new FunctionCall(effect324Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Power>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { _17_0_2_edc0357_1352328160505_205739_21317, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328157734_471476_19963Collections() {
                parameters.add(_17_0_2_edc0357_1352328160505_205739_21317);
                parameters.add(objectToPass);
                parameters.add(_17_0_2_edc0357_1352328157739_887416_19970_exists);
                Set<Effect> effectsForeffect324Var = new TreeSet<Effect>();
                effectsForeffect324Var.add(effect324);
                addEffects((Parameter<?>) effect324Var, effectsForeffect324Var);
            }

            public void init_17_0_2_edc0357_1352328157734_471476_19963Dependencies() {
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new FunctionCall(sig_17_0_2_edc0357_1352328157741_586958_19972, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_edc0357_1352328157739_887416_19970_exists, new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall));
            }

            public void init_17_0_2_edc0357_1352328157734_471476_19963Elaborations() {
                init_17_0_2_edc0357_1352328157734_471476_19963Dependencies();
                Expression<?>[] arguments325 = new Expression<?>[1];
                arguments325[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition325 = new Expression<Boolean>(_17_0_2_edc0357_1352328157739_887416_19970_exists);
                elaborationRule325 = addElaborationRule(condition325, _17_0_2_edc0357_1352328156605_909758_19599.this, Power._17_0_2_edc0357_1352328156605_909758_19599._17_0_2_edc0357_1352328157739_887416_19970.class, "ob_fork_self_ForkNode_generate", arguments325);
            }
        }

        public class _17_0_2_edc0357_1352328157736_750916_19964 extends DurativeEvent {

            public _17_0_2_edc0357_1352328157736_750916_19964() {
                super();
                init_17_0_2_edc0357_1352328157736_750916_19964Members();
                init_17_0_2_edc0357_1352328157736_750916_19964Collections();
                init_17_0_2_edc0357_1352328157736_750916_19964Elaborations();
            }

            public _17_0_2_edc0357_1352328157736_750916_19964(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328157736_750916_19964Members();
                init_17_0_2_edc0357_1352328157736_750916_19964Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328157736_750916_19964Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter objectToPass = null;

            public BooleanParameter _17_0_2_edc0357_1352328157734_471476_19963_exists = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328157734_471476_19963_existsDependency = null;

            public Effect effect326 = null;

            public Parameter effect326Var = null;

            public ElaborationRule elaborationRule327 = null;

            public void init_17_0_2_edc0357_1352328157736_750916_19964Members() {
                try {
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (_17_0_2_edc0357_1352328157734_471476_19963_exists == null) _17_0_2_edc0357_1352328157734_471476_19963_exists = new BooleanParameter("_17_0_2_edc0357_1352328157734_471476_19963_exists", (Boolean) false, this);
                    Object effect326VarV = sig_17_0_2_edc0357_1352328157741_586958_19972;
                    effect326Var = new Parameter("effect326Var", null, null, this);
                    addDependency(effect326Var, new Expression(effect326VarV));
                    effect326 = new EffectFunction(new FunctionCall(effect326Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328157736_750916_19964Collections() {
                parameters.add(objectToPass);
                parameters.add(_17_0_2_edc0357_1352328157734_471476_19963_exists);
                Set<Effect> effectsForeffect326Var = new TreeSet<Effect>();
                effectsForeffect326Var.add(effect326);
                addEffects((Parameter<?>) effect326Var, effectsForeffect326Var);
            }

            public void init_17_0_2_edc0357_1352328157736_750916_19964Dependencies() {
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(true));
                addDependency(_17_0_2_edc0357_1352328157734_471476_19963_exists, new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall));
            }

            public void init_17_0_2_edc0357_1352328157736_750916_19964Elaborations() {
                init_17_0_2_edc0357_1352328157736_750916_19964Dependencies();
                Expression<?>[] arguments327 = new Expression<?>[1];
                arguments327[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition327 = new Expression<Boolean>(_17_0_2_edc0357_1352328157734_471476_19963_exists);
                elaborationRule327 = addElaborationRule(condition327, _17_0_2_edc0357_1352328156605_909758_19599.this, Power._17_0_2_edc0357_1352328156605_909758_19599._17_0_2_edc0357_1352328157734_471476_19963.class, "readself_ReadSelfAction_generate", arguments327);
            }
        }

        public class _17_0_2_edc0357_1352328157737_61300_19965 extends DurativeEvent {

            public _17_0_2_edc0357_1352328157737_61300_19965() {
                super();
                init_17_0_2_edc0357_1352328157737_61300_19965Members();
                init_17_0_2_edc0357_1352328157737_61300_19965Collections();
                init_17_0_2_edc0357_1352328157737_61300_19965Elaborations();
            }

            public _17_0_2_edc0357_1352328157737_61300_19965(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328157737_61300_19965Members();
                init_17_0_2_edc0357_1352328157737_61300_19965Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328157737_61300_19965Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter objectToPass = null;

            public Dependency< Integer > endTimeDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Dependency< Integer > finalNode_endTimeDependency = null;

            public void init_17_0_2_edc0357_1352328157737_61300_19965Members() {
                try {
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328157737_61300_19965Collections() {
                parameters.add(objectToPass);
            }

            public void init_17_0_2_edc0357_1352328157737_61300_19965Dependencies() {
                addDependency(endTime, new Expression<Integer>((new Functions.Plus(new Expression<Integer>(startTime), new Expression<Integer>(duration))).functionCall));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new FunctionCall(sig_17_0_2_edc0357_1352328157742_748196_19980, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(finalNode_endTime, new Expression<Integer>(endTime));
            }

            public void init_17_0_2_edc0357_1352328157737_61300_19965Elaborations() {
                init_17_0_2_edc0357_1352328157737_61300_19965Dependencies();
            }
        }

        public class _17_0_2_edc0357_1352328157737_363375_19966 extends DurativeEvent {

            public _17_0_2_edc0357_1352328157737_363375_19966() {
                super();
                init_17_0_2_edc0357_1352328157737_363375_19966Members();
                init_17_0_2_edc0357_1352328157737_363375_19966Collections();
                init_17_0_2_edc0357_1352328157737_363375_19966Elaborations();
            }

            public _17_0_2_edc0357_1352328157737_363375_19966(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328157737_363375_19966Members();
                init_17_0_2_edc0357_1352328157737_363375_19966Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328157737_363375_19966Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_edc0357_1352328157738_937602_19968_exists = null;

            public IntegerParameter _17_0_2_edc0357_1352328160505_781200_21318 = null;

            public IntegerParameter myDeciderID_decider_17_0_2_edc0357_1352328157738_937602_19968 = null;

            public Parameter< Power > _17_0_2_edc0357_1352328160506_678376_21319 = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328157738_937602_19968_existsDependency = null;

            public Dependency< Integer > _17_0_2_edc0357_1352328160505_781200_21318Dependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Integer > myDeciderID_decider_17_0_2_edc0357_1352328157738_937602_19968Dependency = null;

            public Dependency< Power > _17_0_2_edc0357_1352328160506_678376_21319Dependency = null;

            public Effect effect328 = null;

            public Parameter effect328Var = null;

            public Effect effect329 = null;

            public Parameter effect329Var = null;

            public ElaborationRule elaborationRule330 = null;

            public void init_17_0_2_edc0357_1352328157737_363375_19966Members() {
                try {
                    if (_17_0_2_edc0357_1352328157738_937602_19968_exists == null) _17_0_2_edc0357_1352328157738_937602_19968_exists = new BooleanParameter("_17_0_2_edc0357_1352328157738_937602_19968_exists", (Boolean) false, this);
                    if (_17_0_2_edc0357_1352328160505_781200_21318 == null) _17_0_2_edc0357_1352328160505_781200_21318 = new IntegerParameter("_17_0_2_edc0357_1352328160505_781200_21318", (Integer) null, this);
                    if (myDeciderID_decider_17_0_2_edc0357_1352328157738_937602_19968 == null) myDeciderID_decider_17_0_2_edc0357_1352328157738_937602_19968 = new IntegerParameter("myDeciderID_decider_17_0_2_edc0357_1352328157738_937602_19968", (Integer) 1, this);
                    if (_17_0_2_edc0357_1352328160506_678376_21319 == null) _17_0_2_edc0357_1352328160506_678376_21319 = new Parameter<Power>("_17_0_2_edc0357_1352328160506_678376_21319", null, (Power) null, this);
                    Object effect328VarV = sig_17_0_2_edc0357_1352328157741_771288_19974;
                    effect328Var = new Parameter("effect328Var", null, null, this);
                    addDependency(effect328Var, new Expression(effect328VarV));
                    effect328 = new EffectFunction(new FunctionCall(effect328Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { _17_0_2_edc0357_1352328160505_781200_21318, endTime }));
                    Object effect329VarV = decider_17_0_2_edc0357_1352328157738_937602_19968;
                    effect329Var = new Parameter("effect329Var", null, null, this);
                    addDependency(effect329Var, new Expression(effect329VarV));
                    effect329 = new EffectFunction(new FunctionCall(effect329Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, myDeciderID_decider_17_0_2_edc0357_1352328157738_937602_19968 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328157737_363375_19966Collections() {
                parameters.add(_17_0_2_edc0357_1352328157738_937602_19968_exists);
                parameters.add(_17_0_2_edc0357_1352328160505_781200_21318);
                parameters.add(myDeciderID_decider_17_0_2_edc0357_1352328157738_937602_19968);
                parameters.add(_17_0_2_edc0357_1352328160506_678376_21319);
                Set<Effect> effectsForeffect328Var = new TreeSet<Effect>();
                effectsForeffect328Var.add(effect328);
                addEffects((Parameter<?>) effect328Var, effectsForeffect328Var);
                Set<Effect> effectsForeffect329Var = new TreeSet<Effect>();
                effectsForeffect329Var.add(effect329);
                addEffects((Parameter<?>) effect329Var, effectsForeffect329Var);
            }

            public void init_17_0_2_edc0357_1352328157737_363375_19966Dependencies() {
                addDependency(_17_0_2_edc0357_1352328157738_937602_19968_exists, new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328157738_937602_19968, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328157738_937602_19968, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "maxSize"), new Object[] {})))).functionCall), new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328157738_937602_19968, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_edc0357_1352328157738_937602_19968))).functionCall))).functionCall), new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall))).functionCall));
                addDependency(_17_0_2_edc0357_1352328160505_781200_21318, new Expression<Integer>(new FunctionCall(null, ClassUtils.getMethodForArgTypes("TimeVaryingMap<Integer>", "powersystem", "getValue", java.lang.Integer.class), new Object[] { new Expression<Integer>(startTime) }, new FunctionCall(_17_0_2_edc0357_1352328160506_678376_21319, Parameter.class, "getMember", new Object[] { "power__17_0_2_edc0357_1352328156609_70990_19604" }))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(myDeciderID_decider_17_0_2_edc0357_1352328157738_937602_19968, new Expression<Integer>(1));
                addDependency(_17_0_2_edc0357_1352328160506_678376_21319, new Expression<Power>(new FunctionCall(sig_17_0_2_edc0357_1352328157741_537079_19976, ClassUtils.getMethodForArgTypes("ObjectFlow<Power>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_edc0357_1352328157737_363375_19966Elaborations() {
                init_17_0_2_edc0357_1352328157737_363375_19966Dependencies();
                Expression<?>[] arguments330 = new Expression<?>[1];
                arguments330[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition330 = new Expression<Boolean>(_17_0_2_edc0357_1352328157738_937602_19968_exists);
                elaborationRule330 = addElaborationRule(condition330, _17_0_2_edc0357_1352328156605_909758_19599.this, Power._17_0_2_edc0357_1352328156605_909758_19599._17_0_2_edc0357_1352328157738_937602_19968.class, "send_gen_reading_SendSignalAction_generate", arguments330);
            }
        }

        public class _17_0_2_edc0357_1352328157738_122248_19967 extends DurativeEvent {

            public _17_0_2_edc0357_1352328157738_122248_19967() {
                super();
                init_17_0_2_edc0357_1352328157738_122248_19967Members();
                init_17_0_2_edc0357_1352328157738_122248_19967Collections();
                init_17_0_2_edc0357_1352328157738_122248_19967Elaborations();
            }

            public _17_0_2_edc0357_1352328157738_122248_19967(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328157738_122248_19967Members();
                init_17_0_2_edc0357_1352328157738_122248_19967Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328157738_122248_19967Elaborations();
                fixTimeDependencies();
            }

            public IntegerParameter _17_0_2_edc0357_1352328160506_320730_21320 = null;

            public IntegerParameter myDeciderID_decider_17_0_2_edc0357_1352328157739_502244_19969 = null;

            public Parameter< Power > _17_0_2_edc0357_1352328160507_748441_21321 = null;

            public BooleanParameter _17_0_2_edc0357_1352328157739_502244_19969_exists = null;

            public Dependency< Integer > _17_0_2_edc0357_1352328160506_320730_21320Dependency = null;

            public Dependency< Integer > myDeciderID_decider_17_0_2_edc0357_1352328157739_502244_19969Dependency = null;

            public Dependency< Power > _17_0_2_edc0357_1352328160507_748441_21321Dependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328157739_502244_19969_existsDependency = null;

            public Effect effect331 = null;

            public Parameter effect331Var = null;

            public Effect effect332 = null;

            public Parameter effect332Var = null;

            public ElaborationRule elaborationRule333 = null;

            public void init_17_0_2_edc0357_1352328157738_122248_19967Members() {
                try {
                    if (_17_0_2_edc0357_1352328160506_320730_21320 == null) _17_0_2_edc0357_1352328160506_320730_21320 = new IntegerParameter("_17_0_2_edc0357_1352328160506_320730_21320", (Integer) null, this);
                    if (myDeciderID_decider_17_0_2_edc0357_1352328157739_502244_19969 == null) myDeciderID_decider_17_0_2_edc0357_1352328157739_502244_19969 = new IntegerParameter("myDeciderID_decider_17_0_2_edc0357_1352328157739_502244_19969", (Integer) 1, this);
                    if (_17_0_2_edc0357_1352328160507_748441_21321 == null) _17_0_2_edc0357_1352328160507_748441_21321 = new Parameter<Power>("_17_0_2_edc0357_1352328160507_748441_21321", null, (Power) null, this);
                    if (_17_0_2_edc0357_1352328157739_502244_19969_exists == null) _17_0_2_edc0357_1352328157739_502244_19969_exists = new BooleanParameter("_17_0_2_edc0357_1352328157739_502244_19969_exists", (Boolean) false, this);
                    Object effect331VarV = sig_17_0_2_edc0357_1352328157741_145596_19973;
                    effect331Var = new Parameter("effect331Var", null, null, this);
                    addDependency(effect331Var, new Expression(effect331VarV));
                    effect331 = new EffectFunction(new FunctionCall(effect331Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { _17_0_2_edc0357_1352328160506_320730_21320, endTime }));
                    Object effect332VarV = decider_17_0_2_edc0357_1352328157739_502244_19969;
                    effect332Var = new Parameter("effect332Var", null, null, this);
                    addDependency(effect332Var, new Expression(effect332VarV));
                    effect332 = new EffectFunction(new FunctionCall(effect332Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, myDeciderID_decider_17_0_2_edc0357_1352328157739_502244_19969 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328157738_122248_19967Collections() {
                parameters.add(_17_0_2_edc0357_1352328160506_320730_21320);
                parameters.add(myDeciderID_decider_17_0_2_edc0357_1352328157739_502244_19969);
                parameters.add(_17_0_2_edc0357_1352328160507_748441_21321);
                parameters.add(_17_0_2_edc0357_1352328157739_502244_19969_exists);
                Set<Effect> effectsForeffect331Var = new TreeSet<Effect>();
                effectsForeffect331Var.add(effect331);
                addEffects((Parameter<?>) effect331Var, effectsForeffect331Var);
                Set<Effect> effectsForeffect332Var = new TreeSet<Effect>();
                effectsForeffect332Var.add(effect332);
                addEffects((Parameter<?>) effect332Var, effectsForeffect332Var);
            }

            public void init_17_0_2_edc0357_1352328157738_122248_19967Dependencies() {
                addDependency(_17_0_2_edc0357_1352328160506_320730_21320, new Expression<Integer>(new FunctionCall(null, ClassUtils.getMethodForArgTypes("TimeVaryingMap<Integer>", "powersystem", "getValue", java.lang.Integer.class), new Object[] { new Expression<Integer>(startTime) }, new FunctionCall(_17_0_2_edc0357_1352328160507_748441_21321, Parameter.class, "getMember", new Object[] { "load__17_0_2_edc0357_1352328156622_89989_19605" }))));
                addDependency(myDeciderID_decider_17_0_2_edc0357_1352328157739_502244_19969, new Expression<Integer>(1));
                addDependency(_17_0_2_edc0357_1352328160507_748441_21321, new Expression<Power>(new FunctionCall(sig_17_0_2_edc0357_1352328157741_85045_19977, ClassUtils.getMethodForArgTypes("ObjectFlow<Power>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(_17_0_2_edc0357_1352328157739_502244_19969_exists, new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328157739_502244_19969, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328157739_502244_19969, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "maxSize"), new Object[] {})))).functionCall), new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328157739_502244_19969, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_edc0357_1352328157739_502244_19969))).functionCall))).functionCall), new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall))).functionCall));
            }

            public void init_17_0_2_edc0357_1352328157738_122248_19967Elaborations() {
                init_17_0_2_edc0357_1352328157738_122248_19967Dependencies();
                Expression<?>[] arguments333 = new Expression<?>[1];
                arguments333[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition333 = new Expression<Boolean>(_17_0_2_edc0357_1352328157739_502244_19969_exists);
                elaborationRule333 = addElaborationRule(condition333, _17_0_2_edc0357_1352328156605_909758_19599.this, Power._17_0_2_edc0357_1352328156605_909758_19599._17_0_2_edc0357_1352328157739_502244_19969.class, "send_load_reading_SendSignalAction_generate", arguments333);
            }
        }

        public class _17_0_2_edc0357_1352328157738_937602_19968 extends DurativeEvent {

            public _17_0_2_edc0357_1352328157738_937602_19968() {
                super();
                init_17_0_2_edc0357_1352328157738_937602_19968Members();
                init_17_0_2_edc0357_1352328157738_937602_19968Collections();
                init_17_0_2_edc0357_1352328157738_937602_19968Elaborations();
            }

            public _17_0_2_edc0357_1352328157738_937602_19968(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328157738_937602_19968Members();
                init_17_0_2_edc0357_1352328157738_937602_19968Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328157738_937602_19968Elaborations();
                fixTimeDependencies();
            }

            public IntegerParameter myDeciderID_decider_17_0_2_edc0357_1352328157740_711091_19971 = null;

            public IntegerParameter _17_0_2_edc0357_1352328160508_790273_21323 = null;

            public BooleanParameter _17_0_2_edc0357_1352328157740_711091_19971_exists = null;

            public Parameter< Power > _17_0_2_edc0357_1352328160507_594578_21322 = null;

            public BooleanParameter objectToPass = null;

            public Dependency< Integer > myDeciderID_decider_17_0_2_edc0357_1352328157740_711091_19971Dependency = null;

            public Dependency< Integer > _17_0_2_edc0357_1352328160508_790273_21323Dependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328157740_711091_19971_existsDependency = null;

            public Dependency< Power > _17_0_2_edc0357_1352328160507_594578_21322Dependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Effect effect334 = null;

            public Parameter effect334Var = null;

            public Effect effect335 = null;

            public Parameter effect335Var = null;

            public Effect effect336 = null;

            public Parameter effect336Var = null;

            public ElaborationRule elaborationRule337 = null;

            public void init_17_0_2_edc0357_1352328157738_937602_19968Members() {
                try {
                    if (myDeciderID_decider_17_0_2_edc0357_1352328157740_711091_19971 == null) myDeciderID_decider_17_0_2_edc0357_1352328157740_711091_19971 = new IntegerParameter("myDeciderID_decider_17_0_2_edc0357_1352328157740_711091_19971", (Integer) 1, this);
                    if (_17_0_2_edc0357_1352328160508_790273_21323 == null) _17_0_2_edc0357_1352328160508_790273_21323 = new IntegerParameter("_17_0_2_edc0357_1352328160508_790273_21323", (Integer) null, this);
                    if (_17_0_2_edc0357_1352328157740_711091_19971_exists == null) _17_0_2_edc0357_1352328157740_711091_19971_exists = new BooleanParameter("_17_0_2_edc0357_1352328157740_711091_19971_exists", (Boolean) false, this);
                    if (_17_0_2_edc0357_1352328160507_594578_21322 == null) _17_0_2_edc0357_1352328160507_594578_21322 = new Parameter<Power>("_17_0_2_edc0357_1352328160507_594578_21322", null, (Power) null, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect334VarV = sig_17_0_2_edc0357_1352328157742_535567_19981;
                    effect334Var = new Parameter("effect334Var", null, null, this);
                    addDependency(effect334Var, new Expression(effect334VarV));
                    effect334 = new EffectFunction(new FunctionCall(effect334Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect335VarV = decider_17_0_2_edc0357_1352328157740_711091_19971;
                    effect335Var = new Parameter("effect335Var", null, null, this);
                    addDependency(effect335Var, new Expression(effect335VarV));
                    effect335 = new EffectFunction(new FunctionCall(effect335Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, myDeciderID_decider_17_0_2_edc0357_1352328157740_711091_19971 }));
                    Object effect336VarV = new FunctionCall(x, Parameter.class, "getMember", new Object[] { "ss_17_0_2_edc0357_1352328156634_627908_19609_receiveGenReading" });
                    effect336Var = new Parameter("effect336Var", null, null, this);
                    addDependency(effect336Var, new Expression(effect336VarV));
                    effect336 = new EffectFunction(new FunctionCall(null, ClassUtils.getMethodForArgTypes("ObjectFlow<SignalreceiveGenReading>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { x.getValue().new SignalreceiveGenReading(endTime, _17_0_2_edc0357_1352328160508_790273_21323.getValue()), endTime }, effect336Var));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328157738_937602_19968Collections() {
                parameters.add(myDeciderID_decider_17_0_2_edc0357_1352328157740_711091_19971);
                parameters.add(_17_0_2_edc0357_1352328160508_790273_21323);
                parameters.add(_17_0_2_edc0357_1352328157740_711091_19971_exists);
                parameters.add(_17_0_2_edc0357_1352328160507_594578_21322);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect334Var = new TreeSet<Effect>();
                effectsForeffect334Var.add(effect334);
                addEffects((Parameter<?>) effect334Var, effectsForeffect334Var);
                Set<Effect> effectsForeffect335Var = new TreeSet<Effect>();
                effectsForeffect335Var.add(effect335);
                addEffects((Parameter<?>) effect335Var, effectsForeffect335Var);
                Set<Effect> effectsForeffect336Var = new TreeSet<Effect>();
                effectsForeffect336Var.add(effect336);
                addEffects((Parameter<?>) effect336Var, effectsForeffect336Var);
            }

            public void init_17_0_2_edc0357_1352328157738_937602_19968Dependencies() {
                addDependency(myDeciderID_decider_17_0_2_edc0357_1352328157740_711091_19971, new Expression<Integer>(1));
                addDependency(_17_0_2_edc0357_1352328160508_790273_21323, new Expression<Integer>(new FunctionCall(sig_17_0_2_edc0357_1352328157741_771288_19974, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_edc0357_1352328157740_711091_19971_exists, new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328157740_711091_19971, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328157740_711091_19971, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "maxSize"), new Object[] {})))).functionCall), new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328157740_711091_19971, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_edc0357_1352328157740_711091_19971))).functionCall))).functionCall), new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall))).functionCall));
                addDependency(_17_0_2_edc0357_1352328160507_594578_21322, new Expression<Power>(new FunctionCall(sig_17_0_2_edc0357_1352328157741_6483_19978, ClassUtils.getMethodForArgTypes("ObjectFlow<Power>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(true));
            }

            public void init_17_0_2_edc0357_1352328157738_937602_19968Elaborations() {
                init_17_0_2_edc0357_1352328157738_937602_19968Dependencies();
                Expression<?>[] arguments337 = new Expression<?>[1];
                arguments337[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition337 = new Expression<Boolean>(_17_0_2_edc0357_1352328157740_711091_19971_exists);
                elaborationRule337 = addElaborationRule(condition337, _17_0_2_edc0357_1352328156605_909758_19599.this, Power._17_0_2_edc0357_1352328156605_909758_19599._17_0_2_edc0357_1352328157740_711091_19971.class, "ctrl_fork_end_JoinNode_generate", arguments337);
            }
        }

        public class _17_0_2_edc0357_1352328157739_502244_19969 extends DurativeEvent {

            public _17_0_2_edc0357_1352328157739_502244_19969() {
                super();
                init_17_0_2_edc0357_1352328157739_502244_19969Members();
                init_17_0_2_edc0357_1352328157739_502244_19969Collections();
                init_17_0_2_edc0357_1352328157739_502244_19969Elaborations();
            }

            public _17_0_2_edc0357_1352328157739_502244_19969(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328157739_502244_19969Members();
                init_17_0_2_edc0357_1352328157739_502244_19969Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328157739_502244_19969Elaborations();
                fixTimeDependencies();
            }

            public IntegerParameter myDeciderID_decider_17_0_2_edc0357_1352328157740_711091_19971 = null;

            public Parameter< Power > _17_0_2_edc0357_1352328160508_11297_21324 = null;

            public BooleanParameter _17_0_2_edc0357_1352328157740_711091_19971_exists = null;

            public IntegerParameter _17_0_2_edc0357_1352328160509_463185_21325 = null;

            public BooleanParameter objectToPass = null;

            public Dependency< Integer > myDeciderID_decider_17_0_2_edc0357_1352328157740_711091_19971Dependency = null;

            public Dependency< Power > _17_0_2_edc0357_1352328160508_11297_21324Dependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328157740_711091_19971_existsDependency = null;

            public Dependency< Integer > _17_0_2_edc0357_1352328160509_463185_21325Dependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Effect effect338 = null;

            public Parameter effect338Var = null;

            public Effect effect339 = null;

            public Parameter effect339Var = null;

            public Effect effect340 = null;

            public Parameter effect340Var = null;

            public ElaborationRule elaborationRule341 = null;

            public void init_17_0_2_edc0357_1352328157739_502244_19969Members() {
                try {
                    if (myDeciderID_decider_17_0_2_edc0357_1352328157740_711091_19971 == null) myDeciderID_decider_17_0_2_edc0357_1352328157740_711091_19971 = new IntegerParameter("myDeciderID_decider_17_0_2_edc0357_1352328157740_711091_19971", (Integer) 2, this);
                    if (_17_0_2_edc0357_1352328160508_11297_21324 == null) _17_0_2_edc0357_1352328160508_11297_21324 = new Parameter<Power>("_17_0_2_edc0357_1352328160508_11297_21324", null, (Power) null, this);
                    if (_17_0_2_edc0357_1352328157740_711091_19971_exists == null) _17_0_2_edc0357_1352328157740_711091_19971_exists = new BooleanParameter("_17_0_2_edc0357_1352328157740_711091_19971_exists", (Boolean) false, this);
                    if (_17_0_2_edc0357_1352328160509_463185_21325 == null) _17_0_2_edc0357_1352328160509_463185_21325 = new IntegerParameter("_17_0_2_edc0357_1352328160509_463185_21325", (Integer) null, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect338VarV = sig_17_0_2_edc0357_1352328157742_875391_19982;
                    effect338Var = new Parameter("effect338Var", null, null, this);
                    addDependency(effect338Var, new Expression(effect338VarV));
                    effect338 = new EffectFunction(new FunctionCall(effect338Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect339VarV = decider_17_0_2_edc0357_1352328157740_711091_19971;
                    effect339Var = new Parameter("effect339Var", null, null, this);
                    addDependency(effect339Var, new Expression(effect339VarV));
                    effect339 = new EffectFunction(new FunctionCall(effect339Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, myDeciderID_decider_17_0_2_edc0357_1352328157740_711091_19971 }));
                    Object effect340VarV = new FunctionCall(x, Parameter.class, "getMember", new Object[] { "ss_17_0_2_edc0357_1352328156634_484825_19608_receiveLoadReading" });
                    effect340Var = new Parameter("effect340Var", null, null, this);
                    addDependency(effect340Var, new Expression(effect340VarV));
                    effect340 = new EffectFunction(new FunctionCall(null, ClassUtils.getMethodForArgTypes("ObjectFlow<SignalreceiveLoadReading>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { x.getValue().new SignalreceiveLoadReading(endTime, _17_0_2_edc0357_1352328160509_463185_21325.getValue()), endTime }, effect340Var));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328157739_502244_19969Collections() {
                parameters.add(myDeciderID_decider_17_0_2_edc0357_1352328157740_711091_19971);
                parameters.add(_17_0_2_edc0357_1352328160508_11297_21324);
                parameters.add(_17_0_2_edc0357_1352328157740_711091_19971_exists);
                parameters.add(_17_0_2_edc0357_1352328160509_463185_21325);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect338Var = new TreeSet<Effect>();
                effectsForeffect338Var.add(effect338);
                addEffects((Parameter<?>) effect338Var, effectsForeffect338Var);
                Set<Effect> effectsForeffect339Var = new TreeSet<Effect>();
                effectsForeffect339Var.add(effect339);
                addEffects((Parameter<?>) effect339Var, effectsForeffect339Var);
                Set<Effect> effectsForeffect340Var = new TreeSet<Effect>();
                effectsForeffect340Var.add(effect340);
                addEffects((Parameter<?>) effect340Var, effectsForeffect340Var);
            }

            public void init_17_0_2_edc0357_1352328157739_502244_19969Dependencies() {
                addDependency(myDeciderID_decider_17_0_2_edc0357_1352328157740_711091_19971, new Expression<Integer>(2));
                addDependency(_17_0_2_edc0357_1352328160508_11297_21324, new Expression<Power>(new FunctionCall(sig_17_0_2_edc0357_1352328157741_668511_19979, ClassUtils.getMethodForArgTypes("ObjectFlow<Power>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_edc0357_1352328157740_711091_19971_exists, new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328157740_711091_19971, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328157740_711091_19971, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "maxSize"), new Object[] {})))).functionCall), new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328157740_711091_19971, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_edc0357_1352328157740_711091_19971))).functionCall))).functionCall), new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall))).functionCall));
                addDependency(_17_0_2_edc0357_1352328160509_463185_21325, new Expression<Integer>(new FunctionCall(sig_17_0_2_edc0357_1352328157741_145596_19973, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(true));
            }

            public void init_17_0_2_edc0357_1352328157739_502244_19969Elaborations() {
                init_17_0_2_edc0357_1352328157739_502244_19969Dependencies();
                Expression<?>[] arguments341 = new Expression<?>[1];
                arguments341[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition341 = new Expression<Boolean>(_17_0_2_edc0357_1352328157740_711091_19971_exists);
                elaborationRule341 = addElaborationRule(condition341, _17_0_2_edc0357_1352328156605_909758_19599.this, Power._17_0_2_edc0357_1352328156605_909758_19599._17_0_2_edc0357_1352328157740_711091_19971.class, "ctrl_fork_end_JoinNode_generate", arguments341);
            }
        }

        public class _17_0_2_edc0357_1352328157739_887416_19970 extends DurativeEvent {

            public _17_0_2_edc0357_1352328157739_887416_19970() {
                super();
                init_17_0_2_edc0357_1352328157739_887416_19970Members();
                init_17_0_2_edc0357_1352328157739_887416_19970Collections();
                init_17_0_2_edc0357_1352328157739_887416_19970Elaborations();
            }

            public _17_0_2_edc0357_1352328157739_887416_19970(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328157739_887416_19970Members();
                init_17_0_2_edc0357_1352328157739_887416_19970Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328157739_887416_19970Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_edc0357_1352328157738_937602_19968_exists = null;

            public BooleanParameter _17_0_2_edc0357_1352328157738_122248_19967_exists = null;

            public BooleanParameter _17_0_2_edc0357_1352328157737_363375_19966_exists = null;

            public IntegerParameter myDeciderID_decider_17_0_2_edc0357_1352328157739_502244_19969 = null;

            public IntegerParameter myDeciderID_decider_17_0_2_edc0357_1352328157738_937602_19968 = null;

            public Parameter< Power > objectToPass = null;

            public BooleanParameter _17_0_2_edc0357_1352328157739_502244_19969_exists = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328157738_937602_19968_existsDependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328157738_122248_19967_existsDependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328157737_363375_19966_existsDependency = null;

            public Dependency< Integer > myDeciderID_decider_17_0_2_edc0357_1352328157739_502244_19969Dependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Integer > myDeciderID_decider_17_0_2_edc0357_1352328157738_937602_19968Dependency = null;

            public Dependency< Power > objectToPassDependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328157739_502244_19969_existsDependency = null;

            public Effect effect342 = null;

            public Parameter effect342Var = null;

            public Effect effect343 = null;

            public Parameter effect343Var = null;

            public Effect effect344 = null;

            public Parameter effect344Var = null;

            public Effect effect345 = null;

            public Parameter effect345Var = null;

            public Effect effect346 = null;

            public Parameter effect346Var = null;

            public Effect effect347 = null;

            public Parameter effect347Var = null;

            public ElaborationRule elaborationRule348 = null;

            public ElaborationRule elaborationRule349 = null;

            public ElaborationRule elaborationRule350 = null;

            public ElaborationRule elaborationRule351 = null;

            public void init_17_0_2_edc0357_1352328157739_887416_19970Members() {
                try {
                    if (_17_0_2_edc0357_1352328157738_937602_19968_exists == null) _17_0_2_edc0357_1352328157738_937602_19968_exists = new BooleanParameter("_17_0_2_edc0357_1352328157738_937602_19968_exists", (Boolean) false, this);
                    if (_17_0_2_edc0357_1352328157738_122248_19967_exists == null) _17_0_2_edc0357_1352328157738_122248_19967_exists = new BooleanParameter("_17_0_2_edc0357_1352328157738_122248_19967_exists", (Boolean) false, this);
                    if (_17_0_2_edc0357_1352328157737_363375_19966_exists == null) _17_0_2_edc0357_1352328157737_363375_19966_exists = new BooleanParameter("_17_0_2_edc0357_1352328157737_363375_19966_exists", (Boolean) false, this);
                    if (myDeciderID_decider_17_0_2_edc0357_1352328157739_502244_19969 == null) myDeciderID_decider_17_0_2_edc0357_1352328157739_502244_19969 = new IntegerParameter("myDeciderID_decider_17_0_2_edc0357_1352328157739_502244_19969", (Integer) 2, this);
                    if (myDeciderID_decider_17_0_2_edc0357_1352328157738_937602_19968 == null) myDeciderID_decider_17_0_2_edc0357_1352328157738_937602_19968 = new IntegerParameter("myDeciderID_decider_17_0_2_edc0357_1352328157738_937602_19968", (Integer) 2, this);
                    if (objectToPass == null) objectToPass = new Parameter<Power>("objectToPass", null, (Power) null, this);
                    if (_17_0_2_edc0357_1352328157739_502244_19969_exists == null) _17_0_2_edc0357_1352328157739_502244_19969_exists = new BooleanParameter("_17_0_2_edc0357_1352328157739_502244_19969_exists", (Boolean) false, this);
                    Object effect342VarV = sig_17_0_2_edc0357_1352328157741_537079_19976;
                    effect342Var = new Parameter("effect342Var", null, null, this);
                    addDependency(effect342Var, new Expression(effect342VarV));
                    effect342 = new EffectFunction(new FunctionCall(effect342Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Power>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect343VarV = sig_17_0_2_edc0357_1352328157741_85045_19977;
                    effect343Var = new Parameter("effect343Var", null, null, this);
                    addDependency(effect343Var, new Expression(effect343VarV));
                    effect343 = new EffectFunction(new FunctionCall(effect343Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Power>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect344VarV = sig_17_0_2_edc0357_1352328157741_6483_19978;
                    effect344Var = new Parameter("effect344Var", null, null, this);
                    addDependency(effect344Var, new Expression(effect344VarV));
                    effect344 = new EffectFunction(new FunctionCall(effect344Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Power>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect345VarV = sig_17_0_2_edc0357_1352328157741_668511_19979;
                    effect345Var = new Parameter("effect345Var", null, null, this);
                    addDependency(effect345Var, new Expression(effect345VarV));
                    effect345 = new EffectFunction(new FunctionCall(effect345Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Power>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect346VarV = decider_17_0_2_edc0357_1352328157739_502244_19969;
                    effect346Var = new Parameter("effect346Var", null, null, this);
                    addDependency(effect346Var, new Expression(effect346VarV));
                    effect346 = new EffectFunction(new FunctionCall(effect346Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, myDeciderID_decider_17_0_2_edc0357_1352328157739_502244_19969 }));
                    Object effect347VarV = decider_17_0_2_edc0357_1352328157738_937602_19968;
                    effect347Var = new Parameter("effect347Var", null, null, this);
                    addDependency(effect347Var, new Expression(effect347VarV));
                    effect347 = new EffectFunction(new FunctionCall(effect347Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class), new Object[] { endTime, myDeciderID_decider_17_0_2_edc0357_1352328157738_937602_19968 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328157739_887416_19970Collections() {
                parameters.add(_17_0_2_edc0357_1352328157738_937602_19968_exists);
                parameters.add(_17_0_2_edc0357_1352328157738_122248_19967_exists);
                parameters.add(_17_0_2_edc0357_1352328157737_363375_19966_exists);
                parameters.add(myDeciderID_decider_17_0_2_edc0357_1352328157739_502244_19969);
                parameters.add(myDeciderID_decider_17_0_2_edc0357_1352328157738_937602_19968);
                parameters.add(objectToPass);
                parameters.add(_17_0_2_edc0357_1352328157739_502244_19969_exists);
                Set<Effect> effectsForeffect342Var = new TreeSet<Effect>();
                effectsForeffect342Var.add(effect342);
                addEffects((Parameter<?>) effect342Var, effectsForeffect342Var);
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
                Set<Effect> effectsForeffect347Var = new TreeSet<Effect>();
                effectsForeffect347Var.add(effect347);
                addEffects((Parameter<?>) effect347Var, effectsForeffect347Var);
            }

            public void init_17_0_2_edc0357_1352328157739_887416_19970Dependencies() {
                addDependency(_17_0_2_edc0357_1352328157738_937602_19968_exists, new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328157738_937602_19968, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328157738_937602_19968, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "maxSize"), new Object[] {})))).functionCall), new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328157738_937602_19968, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_edc0357_1352328157738_937602_19968))).functionCall))).functionCall), new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall))).functionCall));
                addDependency(_17_0_2_edc0357_1352328157738_122248_19967_exists, new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall));
                addDependency(_17_0_2_edc0357_1352328157737_363375_19966_exists, new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall));
                addDependency(myDeciderID_decider_17_0_2_edc0357_1352328157739_502244_19969, new Expression<Integer>(2));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(myDeciderID_decider_17_0_2_edc0357_1352328157738_937602_19968, new Expression<Integer>(2));
                addDependency(objectToPass, new Expression<Power>(new FunctionCall(sig_17_0_2_edc0357_1352328157741_404429_19975, ClassUtils.getMethodForArgTypes("ObjectFlow<Power>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_edc0357_1352328157739_502244_19969_exists, new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.And(new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328157739_502244_19969, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328157739_502244_19969, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "maxSize"), new Object[] {})))).functionCall), new Expression<Boolean>((new Functions.Equals(new Expression(new FunctionCall(decider_17_0_2_edc0357_1352328157739_502244_19969, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "powersystem", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_edc0357_1352328157739_502244_19969))).functionCall))).functionCall), new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall))).functionCall));
            }

            public void init_17_0_2_edc0357_1352328157739_887416_19970Elaborations() {
                init_17_0_2_edc0357_1352328157739_887416_19970Dependencies();
                Expression<?>[] arguments348 = new Expression<?>[1];
                arguments348[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition348 = new Expression<Boolean>(_17_0_2_edc0357_1352328157737_363375_19966_exists);
                elaborationRule348 = addElaborationRule(condition348, _17_0_2_edc0357_1352328156605_909758_19599.this, Power._17_0_2_edc0357_1352328156605_909758_19599._17_0_2_edc0357_1352328157737_363375_19966.class, "read_power_struct_ReadStructuralFeatureAction_generate", arguments348);
                Expression<?>[] arguments349 = new Expression<?>[1];
                arguments349[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition349 = new Expression<Boolean>(_17_0_2_edc0357_1352328157738_122248_19967_exists);
                elaborationRule349 = addElaborationRule(condition349, _17_0_2_edc0357_1352328156605_909758_19599.this, Power._17_0_2_edc0357_1352328156605_909758_19599._17_0_2_edc0357_1352328157738_122248_19967.class, "read_load_struct_ReadStructuralFeatureAction_generate", arguments349);
                Expression<?>[] arguments350 = new Expression<?>[1];
                arguments350[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition350 = new Expression<Boolean>(_17_0_2_edc0357_1352328157738_937602_19968_exists);
                elaborationRule350 = addElaborationRule(condition350, _17_0_2_edc0357_1352328156605_909758_19599.this, Power._17_0_2_edc0357_1352328156605_909758_19599._17_0_2_edc0357_1352328157738_937602_19968.class, "send_gen_reading_SendSignalAction_generate", arguments350);
                Expression<?>[] arguments351 = new Expression<?>[1];
                arguments351[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition351 = new Expression<Boolean>(_17_0_2_edc0357_1352328157739_502244_19969_exists);
                elaborationRule351 = addElaborationRule(condition351, _17_0_2_edc0357_1352328156605_909758_19599.this, Power._17_0_2_edc0357_1352328156605_909758_19599._17_0_2_edc0357_1352328157739_502244_19969.class, "send_load_reading_SendSignalAction_generate", arguments351);
            }
        }

        public class _17_0_2_edc0357_1352328157740_711091_19971 extends DurativeEvent {

            public _17_0_2_edc0357_1352328157740_711091_19971() {
                super();
                init_17_0_2_edc0357_1352328157740_711091_19971Members();
                init_17_0_2_edc0357_1352328157740_711091_19971Collections();
                init_17_0_2_edc0357_1352328157740_711091_19971Elaborations();
            }

            public _17_0_2_edc0357_1352328157740_711091_19971(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328157740_711091_19971Members();
                init_17_0_2_edc0357_1352328157740_711091_19971Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328157740_711091_19971Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter objectToPass1 = null;

            public BooleanParameter objectToPass = null;

            public Dependency< Boolean > objectToPass1Dependency = null;

            public Dependency< Integer > finalNode_startTimeDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Effect effect352 = null;

            public Parameter effect352Var = null;

            public void init_17_0_2_edc0357_1352328157740_711091_19971Members() {
                try {
                    if (objectToPass1 == null) objectToPass1 = new BooleanParameter("objectToPass1", (Boolean) null, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect352VarV = sig_17_0_2_edc0357_1352328157742_748196_19980;
                    effect352Var = new Parameter("effect352Var", null, null, this);
                    addDependency(effect352Var, new Expression(effect352VarV));
                    effect352 = new EffectFunction(new FunctionCall(effect352Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328157740_711091_19971Collections() {
                parameters.add(objectToPass1);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect352Var = new TreeSet<Effect>();
                effectsForeffect352Var.add(effect352);
                addEffects((Parameter<?>) effect352Var, effectsForeffect352Var);
            }

            public void init_17_0_2_edc0357_1352328157740_711091_19971Dependencies() {
                addDependency(objectToPass1, new Expression<Boolean>(new FunctionCall(sig_17_0_2_edc0357_1352328157742_875391_19982, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(finalNode_startTime, new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new FunctionCall(sig_17_0_2_edc0357_1352328157742_535567_19981, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_edc0357_1352328157740_711091_19971Elaborations() {
                init_17_0_2_edc0357_1352328157740_711091_19971Dependencies();
            }
        }

        public _17_0_2_edc0357_1352328156605_909758_19599(Expression<Integer> startTime, Expression<DurativeEvent> caller) {
            super();
            init_17_0_2_edc0357_1352328156605_909758_19599Members();
            init_17_0_2_edc0357_1352328156605_909758_19599Collections();
            addDependency(this.startTime, startTime);
            addDependency(this.caller, caller);
            init_17_0_2_edc0357_1352328156605_909758_19599Elaborations();
            fixTimeDependencies();
        }

        public Parameter< ObjectFlow<Power> > sig_17_0_2_edc0357_1352328157741_537079_19976 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_edc0357_1352328157741_586958_19972 = null;

        public Parameter< DurativeEvent > caller = null;

        public Parameter< ObjectFlow<Power> > sig_17_0_2_edc0357_1352328157741_404429_19975 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_2_edc0357_1352328157741_771288_19974 = null;

        public Parameter< ObjectFlow<Power> > sig_17_0_2_edc0357_1352328157741_668511_19979 = null;

        public Parameter< ObjectFlow<Power> > sig_17_0_2_edc0357_1352328157741_6483_19978 = null;

        public Parameter< TimeVaryingList<Integer> > decider_17_0_2_edc0357_1352328157740_711091_19971 = null;

        public Parameter< TimeVaryingList<Integer> > decider_17_0_2_edc0357_1352328157739_502244_19969 = null;

        public IntegerParameter finalNode_startTime = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_edc0357_1352328157742_535567_19981 = null;

        public IntegerParameter finalNode_endTime = null;

        public BooleanParameter _17_0_2_edc0357_1352328157737_61300_19965_exists = null;

        public Parameter< TimeVaryingList<Integer> > decider_17_0_2_edc0357_1352328157738_937602_19968 = null;

        public Parameter< ObjectFlow<Power> > sig_17_0_2_edc0357_1352328157741_85045_19977 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_edc0357_1352328157742_875391_19982 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_2_edc0357_1352328157741_145596_19973 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_edc0357_1352328157742_748196_19980 = null;

        public Dependency< Integer > caller_endTimeDependency = null;

        public Dependency< Integer > endTimeDependency = null;

        public Dependency< Boolean > _17_0_2_edc0357_1352328157737_61300_19965_existsDependency = null;

        public ElaborationRule elaborationRule322 = null;

        public ElaborationRule elaborationRule323 = null;

        public void init_17_0_2_edc0357_1352328156605_909758_19599Members() {
            try {
                if (sig_17_0_2_edc0357_1352328157741_537079_19976 == null) sig_17_0_2_edc0357_1352328157741_537079_19976 = new Parameter<ObjectFlow<Power>>("sig_17_0_2_edc0357_1352328157741_537079_19976", null, (ObjectFlow<Power>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328157741_537079_19976" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328157741_586958_19972 == null) sig_17_0_2_edc0357_1352328157741_586958_19972 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328157741_586958_19972", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328157741_586958_19972" })).evaluate(true), this);
                if (caller == null) caller = new Parameter<DurativeEvent>("caller", null, (DurativeEvent) null, this);
                if (sig_17_0_2_edc0357_1352328157741_404429_19975 == null) sig_17_0_2_edc0357_1352328157741_404429_19975 = new Parameter<ObjectFlow<Power>>("sig_17_0_2_edc0357_1352328157741_404429_19975", null, (ObjectFlow<Power>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328157741_404429_19975" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328157741_771288_19974 == null) sig_17_0_2_edc0357_1352328157741_771288_19974 = new Parameter<ObjectFlow<Integer>>("sig_17_0_2_edc0357_1352328157741_771288_19974", null, (ObjectFlow<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328157741_771288_19974" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328157741_668511_19979 == null) sig_17_0_2_edc0357_1352328157741_668511_19979 = new Parameter<ObjectFlow<Power>>("sig_17_0_2_edc0357_1352328157741_668511_19979", null, (ObjectFlow<Power>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328157741_668511_19979" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328157741_6483_19978 == null) sig_17_0_2_edc0357_1352328157741_6483_19978 = new Parameter<ObjectFlow<Power>>("sig_17_0_2_edc0357_1352328157741_6483_19978", null, (ObjectFlow<Power>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328157741_6483_19978" })).evaluate(true), this);
                if (decider_17_0_2_edc0357_1352328157740_711091_19971 == null) decider_17_0_2_edc0357_1352328157740_711091_19971 = new Parameter<TimeVaryingList<Integer>>("decider_17_0_2_edc0357_1352328157740_711091_19971", null, (TimeVaryingList<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingList.class, java.lang.String.class, int.class), new Object[] { "decider_17_0_2_edc0357_1352328157740_711091_19971", 2 })).evaluate(true), this);
                if (decider_17_0_2_edc0357_1352328157739_502244_19969 == null) decider_17_0_2_edc0357_1352328157739_502244_19969 = new Parameter<TimeVaryingList<Integer>>("decider_17_0_2_edc0357_1352328157739_502244_19969", null, (TimeVaryingList<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingList.class, java.lang.String.class, int.class), new Object[] { "decider_17_0_2_edc0357_1352328157739_502244_19969", 2 })).evaluate(true), this);
                if (finalNode_startTime == null) finalNode_startTime = new IntegerParameter("finalNode_startTime", (Integer) 84000, this);
                if (sig_17_0_2_edc0357_1352328157742_535567_19981 == null) sig_17_0_2_edc0357_1352328157742_535567_19981 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328157742_535567_19981", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328157742_535567_19981" })).evaluate(true), this);
                if (finalNode_endTime == null) finalNode_endTime = new IntegerParameter("finalNode_endTime", (Integer) 84000, this);
                if (_17_0_2_edc0357_1352328157737_61300_19965_exists == null) _17_0_2_edc0357_1352328157737_61300_19965_exists = new BooleanParameter("_17_0_2_edc0357_1352328157737_61300_19965_exists", (Boolean) false, this);
                if (decider_17_0_2_edc0357_1352328157738_937602_19968 == null) decider_17_0_2_edc0357_1352328157738_937602_19968 = new Parameter<TimeVaryingList<Integer>>("decider_17_0_2_edc0357_1352328157738_937602_19968", null, (TimeVaryingList<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingList.class, java.lang.String.class, int.class), new Object[] { "decider_17_0_2_edc0357_1352328157738_937602_19968", 2 })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328157741_85045_19977 == null) sig_17_0_2_edc0357_1352328157741_85045_19977 = new Parameter<ObjectFlow<Power>>("sig_17_0_2_edc0357_1352328157741_85045_19977", null, (ObjectFlow<Power>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328157741_85045_19977" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328157742_875391_19982 == null) sig_17_0_2_edc0357_1352328157742_875391_19982 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328157742_875391_19982", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328157742_875391_19982" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328157741_145596_19973 == null) sig_17_0_2_edc0357_1352328157741_145596_19973 = new Parameter<ObjectFlow<Integer>>("sig_17_0_2_edc0357_1352328157741_145596_19973", null, (ObjectFlow<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328157741_145596_19973" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328157742_748196_19980 == null) sig_17_0_2_edc0357_1352328157742_748196_19980 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328157742_748196_19980", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328157742_748196_19980" })).evaluate(true), this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void init_17_0_2_edc0357_1352328156605_909758_19599Collections() {
            parameters.add(sig_17_0_2_edc0357_1352328157741_537079_19976);
            parameters.add(sig_17_0_2_edc0357_1352328157741_586958_19972);
            parameters.add(caller);
            parameters.add(sig_17_0_2_edc0357_1352328157741_404429_19975);
            parameters.add(sig_17_0_2_edc0357_1352328157741_771288_19974);
            parameters.add(sig_17_0_2_edc0357_1352328157741_668511_19979);
            parameters.add(sig_17_0_2_edc0357_1352328157741_6483_19978);
            parameters.add(decider_17_0_2_edc0357_1352328157740_711091_19971);
            parameters.add(decider_17_0_2_edc0357_1352328157739_502244_19969);
            parameters.add(finalNode_startTime);
            parameters.add(sig_17_0_2_edc0357_1352328157742_535567_19981);
            parameters.add(finalNode_endTime);
            parameters.add(_17_0_2_edc0357_1352328157737_61300_19965_exists);
            parameters.add(decider_17_0_2_edc0357_1352328157738_937602_19968);
            parameters.add(sig_17_0_2_edc0357_1352328157741_85045_19977);
            parameters.add(sig_17_0_2_edc0357_1352328157742_875391_19982);
            parameters.add(sig_17_0_2_edc0357_1352328157741_145596_19973);
            parameters.add(sig_17_0_2_edc0357_1352328157742_748196_19980);
        }

        public void init_17_0_2_edc0357_1352328156605_909758_19599Dependencies() {
            caller.getValue(true).addDependency(caller.getValue(true).endTime, new Expression<Integer>(finalNode_endTime));
            addDependency(endTime, new Expression<Integer>(finalNode_endTime));
            addDependency(_17_0_2_edc0357_1352328157737_61300_19965_exists, new Expression<Boolean>(new FunctionCall(sig_17_0_2_edc0357_1352328157742_748196_19980, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>((new Functions.Minus(new Expression<Integer>(finalNode_startTime), new Expression<Integer>(1))).functionCall) })));
        }

        public void init_17_0_2_edc0357_1352328156605_909758_19599Elaborations() {
            init_17_0_2_edc0357_1352328156605_909758_19599Dependencies();
            Expression<?>[] arguments322 = new Expression<?>[1];
            arguments322[0] = new Expression<Integer>(finalNode_startTime);
            Expression<Boolean> condition322 = new Expression<Boolean>(_17_0_2_edc0357_1352328157737_61300_19965_exists);
            elaborationRule322 = addElaborationRule(condition322, _17_0_2_edc0357_1352328156605_909758_19599.this, Power._17_0_2_edc0357_1352328156605_909758_19599._17_0_2_edc0357_1352328157737_61300_19965.class, "end_ActivityFinalNode_generate", arguments322);
            Expression<?>[] arguments323 = new Expression<?>[1];
            arguments323[0] = new Expression<Integer>(startTime);
            Expression<Boolean> condition323 = new Expression<Boolean>(true);
            elaborationRule323 = addElaborationRule(condition323, _17_0_2_edc0357_1352328156605_909758_19599.this, Power._17_0_2_edc0357_1352328156605_909758_19599._17_0_2_edc0357_1352328157736_750916_19964.class, "start_InitialNode_generate", arguments323);
        }
    }

    public class _17_0_2_edc0357_1352328156606_330566_19600 extends DurativeEvent {

        public _17_0_2_edc0357_1352328156606_330566_19600() {
            super();
            init_17_0_2_edc0357_1352328156606_330566_19600Members();
            init_17_0_2_edc0357_1352328156606_330566_19600Collections();
            init_17_0_2_edc0357_1352328156606_330566_19600Elaborations();
        }

        public class _17_0_2_edc0357_1352328157868_170657_20014 extends DurativeEvent {

            public _17_0_2_edc0357_1352328157868_170657_20014() {
                super();
                init_17_0_2_edc0357_1352328157868_170657_20014Members();
                init_17_0_2_edc0357_1352328157868_170657_20014Collections();
                init_17_0_2_edc0357_1352328157868_170657_20014Elaborations();
            }

            public _17_0_2_edc0357_1352328157868_170657_20014(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328157868_170657_20014Members();
                init_17_0_2_edc0357_1352328157868_170657_20014Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328157868_170657_20014Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_edc0357_1352328157868_858401_20015_exists = null;

            public BooleanParameter objectToPass = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328157868_858401_20015_existsDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Effect effect355 = null;

            public Parameter effect355Var = null;

            public ElaborationRule elaborationRule356 = null;

            public void init_17_0_2_edc0357_1352328157868_170657_20014Members() {
                try {
                    if (_17_0_2_edc0357_1352328157868_858401_20015_exists == null) _17_0_2_edc0357_1352328157868_858401_20015_exists = new BooleanParameter("_17_0_2_edc0357_1352328157868_858401_20015_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect355VarV = sig_17_0_2_edc0357_1352328157890_167680_20037;
                    effect355Var = new Parameter("effect355Var", null, null, this);
                    addDependency(effect355Var, new Expression(effect355VarV));
                    effect355 = new EffectFunction(new FunctionCall(effect355Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328157868_170657_20014Collections() {
                parameters.add(_17_0_2_edc0357_1352328157868_858401_20015_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect355Var = new TreeSet<Effect>();
                effectsForeffect355Var.add(effect355);
                addEffects((Parameter<?>) effect355Var, effectsForeffect355Var);
            }

            public void init_17_0_2_edc0357_1352328157868_170657_20014Dependencies() {
                addDependency(_17_0_2_edc0357_1352328157868_858401_20015_exists, new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(true));
            }

            public void init_17_0_2_edc0357_1352328157868_170657_20014Elaborations() {
                init_17_0_2_edc0357_1352328157868_170657_20014Dependencies();
                Expression<?>[] arguments356 = new Expression<?>[1];
                arguments356[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition356 = new Expression<Boolean>(_17_0_2_edc0357_1352328157868_858401_20015_exists);
                elaborationRule356 = addElaborationRule(condition356, _17_0_2_edc0357_1352328156606_330566_19600.this, Power._17_0_2_edc0357_1352328156606_330566_19600._17_0_2_edc0357_1352328157868_858401_20015.class, "_CallBehaviorAction_PowerCB", arguments356);
            }
        }

        public class _17_0_2_edc0357_1352328157868_858401_20015 extends DurativeEvent {

            public _17_0_2_edc0357_1352328157868_858401_20015() {
                super();
                init_17_0_2_edc0357_1352328157868_858401_20015Members();
                init_17_0_2_edc0357_1352328157868_858401_20015Collections();
                init_17_0_2_edc0357_1352328157868_858401_20015Elaborations();
            }

            public _17_0_2_edc0357_1352328157868_858401_20015(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328157868_858401_20015Members();
                init_17_0_2_edc0357_1352328157868_858401_20015Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328157868_858401_20015Elaborations();
                fixTimeDependencies();
            }

            public IntegerParameter duration = null;

            public BooleanParameter objectToPass = null;

            public BooleanParameter _17_0_2_edc0357_1352328378867_561466_27925_exists = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328378867_561466_27925_existsDependency = null;

            public Effect effect357 = null;

            public Parameter effect357Var = null;

            public ElaborationRule elaborationRule358 = null;

            public ElaborationRule elaborationRule359 = null;

            public void init_17_0_2_edc0357_1352328157868_858401_20015Members() {
                try {
                    if (duration == null) duration = new IntegerParameter("duration", (Integer) 45, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (_17_0_2_edc0357_1352328378867_561466_27925_exists == null) _17_0_2_edc0357_1352328378867_561466_27925_exists = new BooleanParameter("_17_0_2_edc0357_1352328378867_561466_27925_exists", (Boolean) false, this);
                    Object effect357VarV = sig_17_0_2_edc0357_1352328408365_648034_27942;
                    effect357Var = new Parameter("effect357Var", null, null, this);
                    addDependency(effect357Var, new Expression(effect357VarV));
                    effect357 = new EffectFunction(new FunctionCall(effect357Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328157868_858401_20015Collections() {
                parameters.add(duration);
                parameters.add(objectToPass);
                parameters.add(_17_0_2_edc0357_1352328378867_561466_27925_exists);
                Set<Effect> effectsForeffect357Var = new TreeSet<Effect>();
                effectsForeffect357Var.add(effect357);
                addEffects((Parameter<?>) effect357Var, effectsForeffect357Var);
            }

            public void init_17_0_2_edc0357_1352328157868_858401_20015Dependencies() {
                addDependency(objectToPass, new Expression<Boolean>(new FunctionCall(sig_17_0_2_edc0357_1352328157890_167680_20037, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_edc0357_1352328378867_561466_27925_exists, new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall));
            }

            public void init_17_0_2_edc0357_1352328157868_858401_20015Elaborations() {
                init_17_0_2_edc0357_1352328157868_858401_20015Dependencies();
                Expression<?>[] arguments358 = new Expression<?>[2];
                arguments358[0] = new Expression<Integer>(startTime);
                arguments358[1] = new Expression<DurativeEvent>(this);
                Expression<Boolean> condition358 = new Expression<Boolean>(true);
                elaborationRule358 = addElaborationRule(condition358, Power.this, Power._17_0_2_edc0357_1352328156604_3589_19598.class, "intialize_Activity_Power", arguments358);
                Expression<?>[] arguments359 = new Expression<?>[1];
                arguments359[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition359 = new Expression<Boolean>(_17_0_2_edc0357_1352328378867_561466_27925_exists);
                elaborationRule359 = addElaborationRule(condition359, _17_0_2_edc0357_1352328156606_330566_19600.this, Power._17_0_2_edc0357_1352328156606_330566_19600._17_0_2_edc0357_1352328378867_561466_27925.class, "_AcceptEventAction_PowerCB", arguments359);
            }
        }

        public class _17_0_2_edc0357_1352328157869_383435_20016 extends DurativeEvent {

            public _17_0_2_edc0357_1352328157869_383435_20016() {
                super();
                init_17_0_2_edc0357_1352328157869_383435_20016Members();
                init_17_0_2_edc0357_1352328157869_383435_20016Collections();
                init_17_0_2_edc0357_1352328157869_383435_20016Elaborations();
            }

            public _17_0_2_edc0357_1352328157869_383435_20016(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328157869_383435_20016Members();
                init_17_0_2_edc0357_1352328157869_383435_20016Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328157869_383435_20016Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_edc0357_1352328157887_645472_20022_exists = null;

            public BooleanParameter _17_0_2_edc0357_1352328157887_486_20023_exists = null;

            public BooleanParameter _17_0_2_edc0357_1352328337903_895019_27876_exists = null;

            public BooleanParameter _17_0_2_edc0357_1352328157887_650991_20024_exists = null;

            public BooleanParameter objectToPass = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328157887_645472_20022_existsDependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328157887_486_20023_existsDependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328337903_895019_27876_existsDependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328157887_650991_20024_existsDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Effect effect360 = null;

            public Parameter effect360Var = null;

            public Effect effect361 = null;

            public Parameter effect361Var = null;

            public Effect effect362 = null;

            public Parameter effect362Var = null;

            public Effect effect363 = null;

            public Parameter effect363Var = null;

            public ElaborationRule elaborationRule364 = null;

            public ElaborationRule elaborationRule365 = null;

            public ElaborationRule elaborationRule366 = null;

            public ElaborationRule elaborationRule367 = null;

            public void init_17_0_2_edc0357_1352328157869_383435_20016Members() {
                try {
                    if (_17_0_2_edc0357_1352328157887_645472_20022_exists == null) _17_0_2_edc0357_1352328157887_645472_20022_exists = new BooleanParameter("_17_0_2_edc0357_1352328157887_645472_20022_exists", (Boolean) false, this);
                    if (_17_0_2_edc0357_1352328157887_486_20023_exists == null) _17_0_2_edc0357_1352328157887_486_20023_exists = new BooleanParameter("_17_0_2_edc0357_1352328157887_486_20023_exists", (Boolean) false, this);
                    if (_17_0_2_edc0357_1352328337903_895019_27876_exists == null) _17_0_2_edc0357_1352328337903_895019_27876_exists = new BooleanParameter("_17_0_2_edc0357_1352328337903_895019_27876_exists", (Boolean) false, this);
                    if (_17_0_2_edc0357_1352328157887_650991_20024_exists == null) _17_0_2_edc0357_1352328157887_650991_20024_exists = new BooleanParameter("_17_0_2_edc0357_1352328157887_650991_20024_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect360VarV = sig_17_0_2_edc0357_1352328157890_223924_20038;
                    effect360Var = new Parameter("effect360Var", null, null, this);
                    addDependency(effect360Var, new Expression(effect360VarV));
                    effect360 = new EffectFunction(new FunctionCall(effect360Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect361VarV = sig_17_0_2_edc0357_1352328157890_558165_20039;
                    effect361Var = new Parameter("effect361Var", null, null, this);
                    addDependency(effect361Var, new Expression(effect361VarV));
                    effect361 = new EffectFunction(new FunctionCall(effect361Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect362VarV = sig_17_0_2_edc0357_1352328157890_321909_20040;
                    effect362Var = new Parameter("effect362Var", null, null, this);
                    addDependency(effect362Var, new Expression(effect362VarV));
                    effect362 = new EffectFunction(new FunctionCall(effect362Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect363VarV = sig_17_0_2_edc0357_1352328341605_92100_27892;
                    effect363Var = new Parameter("effect363Var", null, null, this);
                    addDependency(effect363Var, new Expression(effect363VarV));
                    effect363 = new EffectFunction(new FunctionCall(effect363Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328157869_383435_20016Collections() {
                parameters.add(_17_0_2_edc0357_1352328157887_645472_20022_exists);
                parameters.add(_17_0_2_edc0357_1352328157887_486_20023_exists);
                parameters.add(_17_0_2_edc0357_1352328337903_895019_27876_exists);
                parameters.add(_17_0_2_edc0357_1352328157887_650991_20024_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect360Var = new TreeSet<Effect>();
                effectsForeffect360Var.add(effect360);
                addEffects((Parameter<?>) effect360Var, effectsForeffect360Var);
                Set<Effect> effectsForeffect361Var = new TreeSet<Effect>();
                effectsForeffect361Var.add(effect361);
                addEffects((Parameter<?>) effect361Var, effectsForeffect361Var);
                Set<Effect> effectsForeffect362Var = new TreeSet<Effect>();
                effectsForeffect362Var.add(effect362);
                addEffects((Parameter<?>) effect362Var, effectsForeffect362Var);
                Set<Effect> effectsForeffect363Var = new TreeSet<Effect>();
                effectsForeffect363Var.add(effect363);
                addEffects((Parameter<?>) effect363Var, effectsForeffect363Var);
            }

            public void init_17_0_2_edc0357_1352328157869_383435_20016Dependencies() {
                addDependency(_17_0_2_edc0357_1352328157887_645472_20022_exists, new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall));
                addDependency(_17_0_2_edc0357_1352328157887_486_20023_exists, new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall));
                addDependency(_17_0_2_edc0357_1352328337903_895019_27876_exists, new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall));
                addDependency(_17_0_2_edc0357_1352328157887_650991_20024_exists, new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new FunctionCall(sig_17_0_2_edc0357_1352328411901_931136_27947, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_edc0357_1352328157869_383435_20016Elaborations() {
                init_17_0_2_edc0357_1352328157869_383435_20016Dependencies();
                Expression<?>[] arguments364 = new Expression<?>[2];
                arguments364[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                arguments364[1] = new Expression<ObjectFlow<Boolean>>(sig_17_0_2_edc0357_1352328157890_321909_20040);
                Expression<Boolean> condition364 = new Expression<Boolean>(_17_0_2_edc0357_1352328157887_650991_20024_exists);
                elaborationRule364 = addElaborationRule(condition364, _17_0_2_edc0357_1352328156606_330566_19600.this, Power._17_0_2_edc0357_1352328156606_330566_19600._17_0_2_edc0357_1352328157887_650991_20024.class, "_MergeNode_PowerCB", arguments364);
                Expression<?>[] arguments365 = new Expression<?>[2];
                arguments365[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                arguments365[1] = new Expression<ObjectFlow<Boolean>>(sig_17_0_2_edc0357_1352328157890_558165_20039);
                Expression<Boolean> condition365 = new Expression<Boolean>(_17_0_2_edc0357_1352328157887_486_20023_exists);
                elaborationRule365 = addElaborationRule(condition365, _17_0_2_edc0357_1352328156606_330566_19600.this, Power._17_0_2_edc0357_1352328156606_330566_19600._17_0_2_edc0357_1352328157887_486_20023.class, "_MergeNode_PowerCB", arguments365);
                Expression<?>[] arguments366 = new Expression<?>[2];
                arguments366[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                arguments366[1] = new Expression<ObjectFlow<Boolean>>(sig_17_0_2_edc0357_1352328157890_223924_20038);
                Expression<Boolean> condition366 = new Expression<Boolean>(_17_0_2_edc0357_1352328157887_645472_20022_exists);
                elaborationRule366 = addElaborationRule(condition366, _17_0_2_edc0357_1352328156606_330566_19600.this, Power._17_0_2_edc0357_1352328156606_330566_19600._17_0_2_edc0357_1352328157887_645472_20022.class, "_MergeNode_PowerCB", arguments366);
                Expression<?>[] arguments367 = new Expression<?>[1];
                arguments367[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition367 = new Expression<Boolean>(_17_0_2_edc0357_1352328337903_895019_27876_exists);
                elaborationRule367 = addElaborationRule(condition367, _17_0_2_edc0357_1352328156606_330566_19600.this, Power._17_0_2_edc0357_1352328156606_330566_19600._17_0_2_edc0357_1352328337903_895019_27876.class, "_AcceptEventAction_PowerCB", arguments367);
            }
        }

        public class _17_0_2_edc0357_1352328157869_571518_20017 extends DurativeEvent {

            public _17_0_2_edc0357_1352328157869_571518_20017() {
                super();
                init_17_0_2_edc0357_1352328157869_571518_20017Members();
                init_17_0_2_edc0357_1352328157869_571518_20017Collections();
                init_17_0_2_edc0357_1352328157869_571518_20017Elaborations();
            }

            public _17_0_2_edc0357_1352328157869_571518_20017(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328157869_571518_20017Members();
                init_17_0_2_edc0357_1352328157869_571518_20017Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328157869_571518_20017Elaborations();
                fixTimeDependencies();
            }

            public Parameter< Power_System.SignalchangeLoadValue > _17_0_2_edc0357_1352328160515_830437_21339 = null;

            public BooleanParameter _17_0_2_edc0357_1352328157887_551523_20026_exists = null;

            public BooleanParameter objectToPass = null;

            public Dependency< Power_System.SignalchangeLoadValue > _17_0_2_edc0357_1352328160515_830437_21339Dependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328157887_551523_20026_existsDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Effect effect368 = null;

            public Parameter effect368Var = null;

            public ElaborationRule elaborationRule369 = null;

            public void init_17_0_2_edc0357_1352328157869_571518_20017Members() {
                try {
                    if (_17_0_2_edc0357_1352328160515_830437_21339 == null) _17_0_2_edc0357_1352328160515_830437_21339 = new Parameter<Power_System.SignalchangeLoadValue>("_17_0_2_edc0357_1352328160515_830437_21339", null, (Power_System.SignalchangeLoadValue) null, this);
                    if (_17_0_2_edc0357_1352328157887_551523_20026_exists == null) _17_0_2_edc0357_1352328157887_551523_20026_exists = new BooleanParameter("_17_0_2_edc0357_1352328157887_551523_20026_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect368VarV = sig_17_0_2_edc0357_1352328157891_604127_20046;
                    effect368Var = new Parameter("effect368Var", null, null, this);
                    addDependency(effect368Var, new Expression(effect368VarV));
                    effect368 = new EffectFunction(new FunctionCall(effect368Var, ClassUtils.getMethodForArgTypes("ObjectFlow<SignalchangeLoadValue>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { _17_0_2_edc0357_1352328160515_830437_21339, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328157869_571518_20017Collections() {
                parameters.add(_17_0_2_edc0357_1352328160515_830437_21339);
                parameters.add(_17_0_2_edc0357_1352328157887_551523_20026_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect368Var = new TreeSet<Effect>();
                effectsForeffect368Var.add(effect368);
                addEffects((Parameter<?>) effect368Var, effectsForeffect368Var);
            }

            public void init_17_0_2_edc0357_1352328157869_571518_20017Dependencies() {
                addDependency(_17_0_2_edc0357_1352328160515_830437_21339, new Expression<Power_System.SignalchangeLoadValue>(new FunctionCall(q_Power_changeLoadValue, ClassUtils.getMethodForArgTypes("ObjectFlow<SignalchangeLoadValue>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_edc0357_1352328157887_551523_20026_exists, new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new FunctionCall(sig_17_0_2_edc0357_1352328157890_774517_20042, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_edc0357_1352328157869_571518_20017Elaborations() {
                init_17_0_2_edc0357_1352328157869_571518_20017Dependencies();
                Expression<?>[] arguments369 = new Expression<?>[1];
                arguments369[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition369 = new Expression<Boolean>(_17_0_2_edc0357_1352328157887_551523_20026_exists);
                elaborationRule369 = addElaborationRule(condition369, _17_0_2_edc0357_1352328156606_330566_19600.this, Power._17_0_2_edc0357_1352328156606_330566_19600._17_0_2_edc0357_1352328157887_551523_20026.class, "readLoadFromSignal_ReadStructuralFeatureAction_PowerCB", arguments369);
            }
        }

        public class _17_0_2_edc0357_1352328157885_967574_20018 extends DurativeEvent {

            public _17_0_2_edc0357_1352328157885_967574_20018() {
                super();
                init_17_0_2_edc0357_1352328157885_967574_20018Members();
                init_17_0_2_edc0357_1352328157885_967574_20018Collections();
                init_17_0_2_edc0357_1352328157885_967574_20018Elaborations();
            }

            public _17_0_2_edc0357_1352328157885_967574_20018(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328157885_967574_20018Members();
                init_17_0_2_edc0357_1352328157885_967574_20018Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328157885_967574_20018Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_edc0357_1352328157888_384561_20027_exists = null;

            public BooleanParameter objectToPass = null;

            public Parameter< Power_System.SignalchangeGenerationValue > _17_0_2_edc0357_1352328160516_543489_21341 = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328157888_384561_20027_existsDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Dependency< Power_System.SignalchangeGenerationValue > _17_0_2_edc0357_1352328160516_543489_21341Dependency = null;

            public Effect effect370 = null;

            public Parameter effect370Var = null;

            public ElaborationRule elaborationRule371 = null;

            public void init_17_0_2_edc0357_1352328157885_967574_20018Members() {
                try {
                    if (_17_0_2_edc0357_1352328157888_384561_20027_exists == null) _17_0_2_edc0357_1352328157888_384561_20027_exists = new BooleanParameter("_17_0_2_edc0357_1352328157888_384561_20027_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (_17_0_2_edc0357_1352328160516_543489_21341 == null) _17_0_2_edc0357_1352328160516_543489_21341 = new Parameter<Power_System.SignalchangeGenerationValue>("_17_0_2_edc0357_1352328160516_543489_21341", null, (Power_System.SignalchangeGenerationValue) null, this);
                    Object effect370VarV = sig_17_0_2_edc0357_1352328157891_306212_20048;
                    effect370Var = new Parameter("effect370Var", null, null, this);
                    addDependency(effect370Var, new Expression(effect370VarV));
                    effect370 = new EffectFunction(new FunctionCall(effect370Var, ClassUtils.getMethodForArgTypes("ObjectFlow<SignalchangeGenerationValue>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { _17_0_2_edc0357_1352328160516_543489_21341, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328157885_967574_20018Collections() {
                parameters.add(_17_0_2_edc0357_1352328157888_384561_20027_exists);
                parameters.add(objectToPass);
                parameters.add(_17_0_2_edc0357_1352328160516_543489_21341);
                Set<Effect> effectsForeffect370Var = new TreeSet<Effect>();
                effectsForeffect370Var.add(effect370);
                addEffects((Parameter<?>) effect370Var, effectsForeffect370Var);
            }

            public void init_17_0_2_edc0357_1352328157885_967574_20018Dependencies() {
                addDependency(_17_0_2_edc0357_1352328157888_384561_20027_exists, new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new FunctionCall(sig_17_0_2_edc0357_1352328157890_967898_20041, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_edc0357_1352328160516_543489_21341, new Expression<Power_System.SignalchangeGenerationValue>(new FunctionCall(q_Power_changeGenerationValue, ClassUtils.getMethodForArgTypes("ObjectFlow<SignalchangeGenerationValue>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_edc0357_1352328157885_967574_20018Elaborations() {
                init_17_0_2_edc0357_1352328157885_967574_20018Dependencies();
                Expression<?>[] arguments371 = new Expression<?>[1];
                arguments371[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition371 = new Expression<Boolean>(_17_0_2_edc0357_1352328157888_384561_20027_exists);
                elaborationRule371 = addElaborationRule(condition371, _17_0_2_edc0357_1352328156606_330566_19600.this, Power._17_0_2_edc0357_1352328156606_330566_19600._17_0_2_edc0357_1352328157888_384561_20027.class, "readNewGenVal_ReadStructuralFeatureAction_PowerCB", arguments371);
            }
        }

        public class _17_0_2_edc0357_1352328157885_663753_20019 extends DurativeEvent {

            public _17_0_2_edc0357_1352328157885_663753_20019() {
                super();
                init_17_0_2_edc0357_1352328157885_663753_20019Members();
                init_17_0_2_edc0357_1352328157885_663753_20019Collections();
                init_17_0_2_edc0357_1352328157885_663753_20019Elaborations();
            }

            public _17_0_2_edc0357_1352328157885_663753_20019(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328157885_663753_20019Members();
                init_17_0_2_edc0357_1352328157885_663753_20019Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328157885_663753_20019Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_edc0357_1352328157887_486_20023_exists = null;

            public IntegerParameter _17_0_2_edc0357_1352328157345_388531_19835 = null;

            public IntegerParameter duration = null;

            public BooleanParameter objectToPass = null;

            public IntegerParameter _17_0_2_edc0357_1352328160516_660349_21342 = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328157887_486_20023_existsDependency = null;

            public Dependency< Integer > _17_0_2_edc0357_1352328157345_388531_19835Dependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Dependency< Integer > _17_0_2_edc0357_1352328160516_660349_21342Dependency = null;

            public Effect effect372 = null;

            public Parameter effect372Var = null;

            public ElaborationRule elaborationRule373 = null;

            public ElaborationRule elaborationRule374 = null;

            public void init_17_0_2_edc0357_1352328157885_663753_20019Members() {
                try {
                    if (_17_0_2_edc0357_1352328157887_486_20023_exists == null) _17_0_2_edc0357_1352328157887_486_20023_exists = new BooleanParameter("_17_0_2_edc0357_1352328157887_486_20023_exists", (Boolean) false, this);
                    if (_17_0_2_edc0357_1352328157345_388531_19835 == null) _17_0_2_edc0357_1352328157345_388531_19835 = new IntegerParameter("_17_0_2_edc0357_1352328157345_388531_19835", (Integer) null, this);
                    if (duration == null) duration = new IntegerParameter("duration", (Integer) 45, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (_17_0_2_edc0357_1352328160516_660349_21342 == null) _17_0_2_edc0357_1352328160516_660349_21342 = new IntegerParameter("_17_0_2_edc0357_1352328160516_660349_21342", (Integer) null, this);
                    Object effect372VarV = sig_17_0_2_edc0357_1352328157890_575806_20045;
                    effect372Var = new Parameter("effect372Var", null, null, this);
                    addDependency(effect372Var, new Expression(effect372VarV));
                    effect372 = new EffectFunction(new FunctionCall(effect372Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328157885_663753_20019Collections() {
                parameters.add(_17_0_2_edc0357_1352328157887_486_20023_exists);
                parameters.add(_17_0_2_edc0357_1352328157345_388531_19835);
                parameters.add(duration);
                parameters.add(objectToPass);
                parameters.add(_17_0_2_edc0357_1352328160516_660349_21342);
                Set<Effect> effectsForeffect372Var = new TreeSet<Effect>();
                effectsForeffect372Var.add(effect372);
                addEffects((Parameter<?>) effect372Var, effectsForeffect372Var);
            }

            public void init_17_0_2_edc0357_1352328157885_663753_20019Dependencies() {
                addDependency(_17_0_2_edc0357_1352328157887_486_20023_exists, new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall));
                addDependency(_17_0_2_edc0357_1352328157345_388531_19835, new Expression<Integer>(_17_0_2_edc0357_1352328160516_660349_21342));
                addDependency(objectToPass, new Expression<Boolean>(true));
                addDependency(_17_0_2_edc0357_1352328160516_660349_21342, new Expression<Integer>(new FunctionCall(sig_17_0_2_edc0357_1352328157891_490819_20047, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_edc0357_1352328157885_663753_20019Elaborations() {
                init_17_0_2_edc0357_1352328157885_663753_20019Dependencies();
                Expression<?>[] arguments373 = new Expression<?>[3];
                arguments373[0] = new Expression<Integer>(startTime);
                arguments373[1] = new Expression<DurativeEvent>(this);
                arguments373[2] = new Expression<Integer>(_17_0_2_edc0357_1352328157345_388531_19835);
                Expression<Boolean> condition373 = new Expression<Boolean>(true);
                elaborationRule373 = addElaborationRule(condition373, Power.this, Power._17_0_2_edc0357_1352328156603_274620_19597.class, "changeLoadValue_Activity_Power", arguments373);
                Expression<?>[] arguments374 = new Expression<?>[2];
                arguments374[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                arguments374[1] = new Expression<ObjectFlow<Boolean>>(sig_17_0_2_edc0357_1352328157890_575806_20045);
                Expression<Boolean> condition374 = new Expression<Boolean>(_17_0_2_edc0357_1352328157887_486_20023_exists);
                elaborationRule374 = addElaborationRule(condition374, _17_0_2_edc0357_1352328156606_330566_19600.this, Power._17_0_2_edc0357_1352328156606_330566_19600._17_0_2_edc0357_1352328157887_486_20023.class, "_MergeNode_PowerCB", arguments374);
            }
        }

        public class _17_0_2_edc0357_1352328157886_986405_20020 extends DurativeEvent {

            public _17_0_2_edc0357_1352328157886_986405_20020() {
                super();
                init_17_0_2_edc0357_1352328157886_986405_20020Members();
                init_17_0_2_edc0357_1352328157886_986405_20020Collections();
                init_17_0_2_edc0357_1352328157886_986405_20020Elaborations();
            }

            public _17_0_2_edc0357_1352328157886_986405_20020(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328157886_986405_20020Members();
                init_17_0_2_edc0357_1352328157886_986405_20020Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328157886_986405_20020Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_edc0357_1352328157887_645472_20022_exists = null;

            public IntegerParameter duration = null;

            public BooleanParameter objectToPass = null;

            public IntegerParameter _17_0_2_edc0357_1352328157191_364910_19786 = null;

            public IntegerParameter _17_0_2_edc0357_1352328160517_550520_21343 = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328157887_645472_20022_existsDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Dependency< Integer > _17_0_2_edc0357_1352328157191_364910_19786Dependency = null;

            public Dependency< Integer > _17_0_2_edc0357_1352328160517_550520_21343Dependency = null;

            public Effect effect375 = null;

            public Parameter effect375Var = null;

            public ElaborationRule elaborationRule376 = null;

            public ElaborationRule elaborationRule377 = null;

            public void init_17_0_2_edc0357_1352328157886_986405_20020Members() {
                try {
                    if (_17_0_2_edc0357_1352328157887_645472_20022_exists == null) _17_0_2_edc0357_1352328157887_645472_20022_exists = new BooleanParameter("_17_0_2_edc0357_1352328157887_645472_20022_exists", (Boolean) false, this);
                    if (duration == null) duration = new IntegerParameter("duration", (Integer) 45, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (_17_0_2_edc0357_1352328157191_364910_19786 == null) _17_0_2_edc0357_1352328157191_364910_19786 = new IntegerParameter("_17_0_2_edc0357_1352328157191_364910_19786", (Integer) null, this);
                    if (_17_0_2_edc0357_1352328160517_550520_21343 == null) _17_0_2_edc0357_1352328160517_550520_21343 = new IntegerParameter("_17_0_2_edc0357_1352328160517_550520_21343", (Integer) null, this);
                    Object effect375VarV = sig_17_0_2_edc0357_1352328157890_191195_20044;
                    effect375Var = new Parameter("effect375Var", null, null, this);
                    addDependency(effect375Var, new Expression(effect375VarV));
                    effect375 = new EffectFunction(new FunctionCall(effect375Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328157886_986405_20020Collections() {
                parameters.add(_17_0_2_edc0357_1352328157887_645472_20022_exists);
                parameters.add(duration);
                parameters.add(objectToPass);
                parameters.add(_17_0_2_edc0357_1352328157191_364910_19786);
                parameters.add(_17_0_2_edc0357_1352328160517_550520_21343);
                Set<Effect> effectsForeffect375Var = new TreeSet<Effect>();
                effectsForeffect375Var.add(effect375);
                addEffects((Parameter<?>) effect375Var, effectsForeffect375Var);
            }

            public void init_17_0_2_edc0357_1352328157886_986405_20020Dependencies() {
                addDependency(_17_0_2_edc0357_1352328157887_645472_20022_exists, new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall));
                addDependency(objectToPass, new Expression<Boolean>(true));
                addDependency(_17_0_2_edc0357_1352328157191_364910_19786, new Expression<Integer>(_17_0_2_edc0357_1352328160517_550520_21343));
                addDependency(_17_0_2_edc0357_1352328160517_550520_21343, new Expression<Integer>(new FunctionCall(sig_17_0_2_edc0357_1352328157891_658508_20049, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_edc0357_1352328157886_986405_20020Elaborations() {
                init_17_0_2_edc0357_1352328157886_986405_20020Dependencies();
                Expression<?>[] arguments376 = new Expression<?>[3];
                arguments376[0] = new Expression<Integer>(startTime);
                arguments376[1] = new Expression<DurativeEvent>(this);
                arguments376[2] = new Expression<Integer>(_17_0_2_edc0357_1352328157191_364910_19786);
                Expression<Boolean> condition376 = new Expression<Boolean>(true);
                elaborationRule376 = addElaborationRule(condition376, Power.this, Power._17_0_2_edc0357_1352328156584_903842_19596.class, "changeGenerationValue_Activity_Power", arguments376);
                Expression<?>[] arguments377 = new Expression<?>[2];
                arguments377[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                arguments377[1] = new Expression<ObjectFlow<Boolean>>(sig_17_0_2_edc0357_1352328157890_191195_20044);
                Expression<Boolean> condition377 = new Expression<Boolean>(_17_0_2_edc0357_1352328157887_645472_20022_exists);
                elaborationRule377 = addElaborationRule(condition377, _17_0_2_edc0357_1352328156606_330566_19600.this, Power._17_0_2_edc0357_1352328156606_330566_19600._17_0_2_edc0357_1352328157887_645472_20022.class, "_MergeNode_PowerCB", arguments377);
            }
        }

        public class _17_0_2_edc0357_1352328157887_1425_20021 extends DurativeEvent {

            public _17_0_2_edc0357_1352328157887_1425_20021() {
                super();
                init_17_0_2_edc0357_1352328157887_1425_20021Members();
                init_17_0_2_edc0357_1352328157887_1425_20021Collections();
                init_17_0_2_edc0357_1352328157887_1425_20021Elaborations();
            }

            public _17_0_2_edc0357_1352328157887_1425_20021(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328157887_1425_20021Members();
                init_17_0_2_edc0357_1352328157887_1425_20021Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328157887_1425_20021Elaborations();
                fixTimeDependencies();
            }

            public IntegerParameter duration = null;

            public BooleanParameter _17_0_2_edc0357_1352328362977_584291_27902_exists = null;

            public BooleanParameter objectToPass = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328362977_584291_27902_existsDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Effect effect378 = null;

            public Parameter effect378Var = null;

            public ElaborationRule elaborationRule379 = null;

            public ElaborationRule elaborationRule380 = null;

            public void init_17_0_2_edc0357_1352328157887_1425_20021Members() {
                try {
                    if (duration == null) duration = new IntegerParameter("duration", (Integer) 45, this);
                    if (_17_0_2_edc0357_1352328362977_584291_27902_exists == null) _17_0_2_edc0357_1352328362977_584291_27902_exists = new BooleanParameter("_17_0_2_edc0357_1352328362977_584291_27902_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect378VarV = sig_17_0_2_edc0357_1352328365961_83832_27916;
                    effect378Var = new Parameter("effect378Var", null, null, this);
                    addDependency(effect378Var, new Expression(effect378VarV));
                    effect378 = new EffectFunction(new FunctionCall(effect378Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328157887_1425_20021Collections() {
                parameters.add(duration);
                parameters.add(_17_0_2_edc0357_1352328362977_584291_27902_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect378Var = new TreeSet<Effect>();
                effectsForeffect378Var.add(effect378);
                addEffects((Parameter<?>) effect378Var, effectsForeffect378Var);
            }

            public void init_17_0_2_edc0357_1352328157887_1425_20021Dependencies() {
                addDependency(_17_0_2_edc0357_1352328362977_584291_27902_exists, new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall));
                addDependency(objectToPass, new Expression<Boolean>(new FunctionCall(sig_17_0_2_edc0357_1352328157890_22043_20043, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_edc0357_1352328157887_1425_20021Elaborations() {
                init_17_0_2_edc0357_1352328157887_1425_20021Dependencies();
                Expression<?>[] arguments379 = new Expression<?>[1];
                arguments379[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition379 = new Expression<Boolean>(_17_0_2_edc0357_1352328362977_584291_27902_exists);
                elaborationRule379 = addElaborationRule(condition379, _17_0_2_edc0357_1352328156606_330566_19600.this, Power._17_0_2_edc0357_1352328156606_330566_19600._17_0_2_edc0357_1352328362977_584291_27902.class, "_AcceptEventAction_PowerCB", arguments379);
                Expression<?>[] arguments380 = new Expression<?>[2];
                arguments380[0] = new Expression<Integer>(startTime);
                arguments380[1] = new Expression<DurativeEvent>(this);
                Expression<Boolean> condition380 = new Expression<Boolean>(true);
                elaborationRule380 = addElaborationRule(condition380, Power.this, Power._17_0_2_edc0357_1352328156605_909758_19599.class, "generate_Activity_Power", arguments380);
            }
        }

        public class _17_0_2_edc0357_1352328157887_645472_20022 extends DurativeEvent {

            public _17_0_2_edc0357_1352328157887_645472_20022() {
                super();
                init_17_0_2_edc0357_1352328157887_645472_20022Members();
                init_17_0_2_edc0357_1352328157887_645472_20022Collections();
                init_17_0_2_edc0357_1352328157887_645472_20022Elaborations();
            }

            public _17_0_2_edc0357_1352328157887_645472_20022(Expression<Integer> startTime, Expression<ObjectFlow<Boolean>> receiveThis) {
                super();
                init_17_0_2_edc0357_1352328157887_645472_20022Members();
                init_17_0_2_edc0357_1352328157887_645472_20022Collections();
                addDependency(this.startTime, startTime);
                addDependency(this.receiveThis, receiveThis);
                init_17_0_2_edc0357_1352328157887_645472_20022Elaborations();
                fixTimeDependencies();
            }

            public Parameter< ObjectFlow<Boolean> > receiveThis = null;

            public BooleanParameter _17_0_2_edc0357_1352328157885_967574_20018_exists = null;

            public BooleanParameter objectToPass = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328157885_967574_20018_existsDependency = null;

            public Dependency< Integer > endTimeDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Effect effect381 = null;

            public Parameter effect381Var = null;

            public ElaborationRule elaborationRule382 = null;

            public void init_17_0_2_edc0357_1352328157887_645472_20022Members() {
                try {
                    if (receiveThis == null) receiveThis = new Parameter<ObjectFlow<Boolean>>("receiveThis", null, (ObjectFlow<Boolean>) null, this);
                    if (_17_0_2_edc0357_1352328157885_967574_20018_exists == null) _17_0_2_edc0357_1352328157885_967574_20018_exists = new BooleanParameter("_17_0_2_edc0357_1352328157885_967574_20018_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect381VarV = sig_17_0_2_edc0357_1352328157890_967898_20041;
                    effect381Var = new Parameter("effect381Var", null, null, this);
                    addDependency(effect381Var, new Expression(effect381VarV));
                    effect381 = new EffectFunction(new FunctionCall(effect381Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328157887_645472_20022Collections() {
                parameters.add(receiveThis);
                parameters.add(_17_0_2_edc0357_1352328157885_967574_20018_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect381Var = new TreeSet<Effect>();
                effectsForeffect381Var.add(effect381);
                addEffects((Parameter<?>) effect381Var, effectsForeffect381Var);
            }

            public void init_17_0_2_edc0357_1352328157887_645472_20022Dependencies() {
                addDependency(_17_0_2_edc0357_1352328157885_967574_20018_exists, new Expression<Boolean>((new Functions.And(new Expression(new FunctionCall(q_Power_changeGenerationValue, ClassUtils.getMethodForArgTypes("ObjectFlow<SignalchangeGenerationValue>", "powersystem", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall))).functionCall));
                addDependency(endTime, new Expression<Integer>(new FunctionCall(null, ClassUtils.getMethodForArgTypes("java.lang.Math", "powersystem", "min", int.class, int.class), new Object[] { new Expression(new FunctionCall(q_Power_changeGenerationValue, ClassUtils.getMethodForArgTypes("ObjectFlow<SignalchangeGenerationValue>", "powersystem", "nextTimeHasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(startTime) })), new Expression<Integer>(finalNode_endTime) })));
                addDependency(objectToPass, new Expression<Boolean>(new FunctionCall(receiveThis, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_edc0357_1352328157887_645472_20022Elaborations() {
                init_17_0_2_edc0357_1352328157887_645472_20022Dependencies();
                Expression<?>[] arguments382 = new Expression<?>[1];
                arguments382[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition382 = new Expression<Boolean>(_17_0_2_edc0357_1352328157885_967574_20018_exists);
                elaborationRule382 = addElaborationRule(condition382, _17_0_2_edc0357_1352328156606_330566_19600.this, Power._17_0_2_edc0357_1352328156606_330566_19600._17_0_2_edc0357_1352328157885_967574_20018.class, "cvg_sig_AcceptEventAction_PowerCB", arguments382);
            }
        }

        public class _17_0_2_edc0357_1352328157887_486_20023 extends DurativeEvent {

            public _17_0_2_edc0357_1352328157887_486_20023() {
                super();
                init_17_0_2_edc0357_1352328157887_486_20023Members();
                init_17_0_2_edc0357_1352328157887_486_20023Collections();
                init_17_0_2_edc0357_1352328157887_486_20023Elaborations();
            }

            public _17_0_2_edc0357_1352328157887_486_20023(Expression<Integer> startTime, Expression<ObjectFlow<Boolean>> receiveThis) {
                super();
                init_17_0_2_edc0357_1352328157887_486_20023Members();
                init_17_0_2_edc0357_1352328157887_486_20023Collections();
                addDependency(this.startTime, startTime);
                addDependency(this.receiveThis, receiveThis);
                init_17_0_2_edc0357_1352328157887_486_20023Elaborations();
                fixTimeDependencies();
            }

            public Parameter< ObjectFlow<Boolean> > receiveThis = null;

            public BooleanParameter objectToPass = null;

            public BooleanParameter _17_0_2_edc0357_1352328157869_571518_20017_exists = null;

            public Dependency< Integer > endTimeDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328157869_571518_20017_existsDependency = null;

            public Effect effect383 = null;

            public Parameter effect383Var = null;

            public ElaborationRule elaborationRule384 = null;

            public void init_17_0_2_edc0357_1352328157887_486_20023Members() {
                try {
                    if (receiveThis == null) receiveThis = new Parameter<ObjectFlow<Boolean>>("receiveThis", null, (ObjectFlow<Boolean>) null, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (_17_0_2_edc0357_1352328157869_571518_20017_exists == null) _17_0_2_edc0357_1352328157869_571518_20017_exists = new BooleanParameter("_17_0_2_edc0357_1352328157869_571518_20017_exists", (Boolean) false, this);
                    Object effect383VarV = sig_17_0_2_edc0357_1352328157890_774517_20042;
                    effect383Var = new Parameter("effect383Var", null, null, this);
                    addDependency(effect383Var, new Expression(effect383VarV));
                    effect383 = new EffectFunction(new FunctionCall(effect383Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328157887_486_20023Collections() {
                parameters.add(receiveThis);
                parameters.add(objectToPass);
                parameters.add(_17_0_2_edc0357_1352328157869_571518_20017_exists);
                Set<Effect> effectsForeffect383Var = new TreeSet<Effect>();
                effectsForeffect383Var.add(effect383);
                addEffects((Parameter<?>) effect383Var, effectsForeffect383Var);
            }

            public void init_17_0_2_edc0357_1352328157887_486_20023Dependencies() {
                addDependency(endTime, new Expression<Integer>(new FunctionCall(null, ClassUtils.getMethodForArgTypes("java.lang.Math", "powersystem", "min", int.class, int.class), new Object[] { new Expression(new FunctionCall(q_Power_changeLoadValue, ClassUtils.getMethodForArgTypes("ObjectFlow<SignalchangeLoadValue>", "powersystem", "nextTimeHasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(startTime) })), new Expression<Integer>(finalNode_endTime) })));
                addDependency(objectToPass, new Expression<Boolean>(new FunctionCall(receiveThis, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_edc0357_1352328157869_571518_20017_exists, new Expression<Boolean>((new Functions.And(new Expression(new FunctionCall(q_Power_changeLoadValue, ClassUtils.getMethodForArgTypes("ObjectFlow<SignalchangeLoadValue>", "powersystem", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall))).functionCall));
            }

            public void init_17_0_2_edc0357_1352328157887_486_20023Elaborations() {
                init_17_0_2_edc0357_1352328157887_486_20023Dependencies();
                Expression<?>[] arguments384 = new Expression<?>[1];
                arguments384[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition384 = new Expression<Boolean>(_17_0_2_edc0357_1352328157869_571518_20017_exists);
                elaborationRule384 = addElaborationRule(condition384, _17_0_2_edc0357_1352328156606_330566_19600.this, Power._17_0_2_edc0357_1352328156606_330566_19600._17_0_2_edc0357_1352328157869_571518_20017.class, "clv_sig_AcceptEventAction_PowerCB", arguments384);
            }
        }

        public class _17_0_2_edc0357_1352328157887_650991_20024 extends DurativeEvent {

            public _17_0_2_edc0357_1352328157887_650991_20024() {
                super();
                init_17_0_2_edc0357_1352328157887_650991_20024Members();
                init_17_0_2_edc0357_1352328157887_650991_20024Collections();
                init_17_0_2_edc0357_1352328157887_650991_20024Elaborations();
            }

            public _17_0_2_edc0357_1352328157887_650991_20024(Expression<Integer> startTime, Expression<ObjectFlow<Boolean>> receiveThis) {
                super();
                init_17_0_2_edc0357_1352328157887_650991_20024Members();
                init_17_0_2_edc0357_1352328157887_650991_20024Collections();
                addDependency(this.startTime, startTime);
                addDependency(this.receiveThis, receiveThis);
                init_17_0_2_edc0357_1352328157887_650991_20024Elaborations();
                fixTimeDependencies();
            }

            public Parameter< ObjectFlow<Boolean> > receiveThis = null;

            public BooleanParameter _17_0_2_edc0357_1352328157887_1425_20021_exists = null;

            public BooleanParameter objectToPass = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328157887_1425_20021_existsDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Effect effect385 = null;

            public Parameter effect385Var = null;

            public ElaborationRule elaborationRule386 = null;

            public void init_17_0_2_edc0357_1352328157887_650991_20024Members() {
                try {
                    if (receiveThis == null) receiveThis = new Parameter<ObjectFlow<Boolean>>("receiveThis", null, (ObjectFlow<Boolean>) null, this);
                    if (_17_0_2_edc0357_1352328157887_1425_20021_exists == null) _17_0_2_edc0357_1352328157887_1425_20021_exists = new BooleanParameter("_17_0_2_edc0357_1352328157887_1425_20021_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect385VarV = sig_17_0_2_edc0357_1352328157890_22043_20043;
                    effect385Var = new Parameter("effect385Var", null, null, this);
                    addDependency(effect385Var, new Expression(effect385VarV));
                    effect385 = new EffectFunction(new FunctionCall(effect385Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328157887_650991_20024Collections() {
                parameters.add(receiveThis);
                parameters.add(_17_0_2_edc0357_1352328157887_1425_20021_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect385Var = new TreeSet<Effect>();
                effectsForeffect385Var.add(effect385);
                addEffects((Parameter<?>) effect385Var, effectsForeffect385Var);
            }

            public void init_17_0_2_edc0357_1352328157887_650991_20024Dependencies() {
                addDependency(_17_0_2_edc0357_1352328157887_1425_20021_exists, new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new FunctionCall(receiveThis, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_edc0357_1352328157887_650991_20024Elaborations() {
                init_17_0_2_edc0357_1352328157887_650991_20024Dependencies();
                Expression<?>[] arguments386 = new Expression<?>[1];
                arguments386[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition386 = new Expression<Boolean>(_17_0_2_edc0357_1352328157887_1425_20021_exists);
                elaborationRule386 = addElaborationRule(condition386, _17_0_2_edc0357_1352328156606_330566_19600.this, Power._17_0_2_edc0357_1352328156606_330566_19600._17_0_2_edc0357_1352328157887_1425_20021.class, "_CallBehaviorAction_PowerCB", arguments386);
            }
        }

        public class _17_0_2_edc0357_1352328157887_127545_20025 extends DurativeEvent {

            public _17_0_2_edc0357_1352328157887_127545_20025() {
                super();
                init_17_0_2_edc0357_1352328157887_127545_20025Members();
                init_17_0_2_edc0357_1352328157887_127545_20025Collections();
                init_17_0_2_edc0357_1352328157887_127545_20025Elaborations();
            }

            public _17_0_2_edc0357_1352328157887_127545_20025(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328157887_127545_20025Members();
                init_17_0_2_edc0357_1352328157887_127545_20025Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328157887_127545_20025Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter objectToPass = null;

            public Dependency< Integer > endTimeDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Dependency< Integer > finalNode_endTimeDependency = null;

            public void init_17_0_2_edc0357_1352328157887_127545_20025Members() {
                try {
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328157887_127545_20025Collections() {
                parameters.add(objectToPass);
            }

            public void init_17_0_2_edc0357_1352328157887_127545_20025Dependencies() {
                addDependency(endTime, new Expression<Integer>((new Functions.Plus(new Expression<Integer>(startTime), new Expression<Integer>(duration))).functionCall));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(new FunctionCall(sig_17_0_2_edc0357_1352328345871_599890_27897, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(finalNode_endTime, new Expression<Integer>(endTime));
            }

            public void init_17_0_2_edc0357_1352328157887_127545_20025Elaborations() {
                init_17_0_2_edc0357_1352328157887_127545_20025Dependencies();
            }
        }

        public class _17_0_2_edc0357_1352328157887_551523_20026 extends DurativeEvent {

            public _17_0_2_edc0357_1352328157887_551523_20026() {
                super();
                init_17_0_2_edc0357_1352328157887_551523_20026Members();
                init_17_0_2_edc0357_1352328157887_551523_20026Collections();
                init_17_0_2_edc0357_1352328157887_551523_20026Elaborations();
            }

            public _17_0_2_edc0357_1352328157887_551523_20026(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328157887_551523_20026Members();
                init_17_0_2_edc0357_1352328157887_551523_20026Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328157887_551523_20026Elaborations();
                fixTimeDependencies();
            }

            public IntegerParameter _17_0_2_edc0357_1352328160518_55671_21344 = null;

            public Parameter< Power_System.SignalchangeLoadValue > _17_0_2_edc0357_1352328160518_281281_21345 = null;

            public BooleanParameter _17_0_2_edc0357_1352328157885_663753_20019_exists = null;

            public Dependency< Integer > _17_0_2_edc0357_1352328160518_55671_21344Dependency = null;

            public Dependency< Power_System.SignalchangeLoadValue > _17_0_2_edc0357_1352328160518_281281_21345Dependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328157885_663753_20019_existsDependency = null;

            public Effect effect387 = null;

            public Parameter effect387Var = null;

            public ElaborationRule elaborationRule388 = null;

            public void init_17_0_2_edc0357_1352328157887_551523_20026Members() {
                try {
                    if (_17_0_2_edc0357_1352328160518_55671_21344 == null) _17_0_2_edc0357_1352328160518_55671_21344 = new IntegerParameter("_17_0_2_edc0357_1352328160518_55671_21344", (Integer) null, this);
                    if (_17_0_2_edc0357_1352328160518_281281_21345 == null) _17_0_2_edc0357_1352328160518_281281_21345 = new Parameter<Power_System.SignalchangeLoadValue>("_17_0_2_edc0357_1352328160518_281281_21345", null, (Power_System.SignalchangeLoadValue) null, this);
                    if (_17_0_2_edc0357_1352328157885_663753_20019_exists == null) _17_0_2_edc0357_1352328157885_663753_20019_exists = new BooleanParameter("_17_0_2_edc0357_1352328157885_663753_20019_exists", (Boolean) false, this);
                    Object effect387VarV = sig_17_0_2_edc0357_1352328157891_490819_20047;
                    effect387Var = new Parameter("effect387Var", null, null, this);
                    addDependency(effect387Var, new Expression(effect387VarV));
                    effect387 = new EffectFunction(new FunctionCall(effect387Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { _17_0_2_edc0357_1352328160518_55671_21344, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328157887_551523_20026Collections() {
                parameters.add(_17_0_2_edc0357_1352328160518_55671_21344);
                parameters.add(_17_0_2_edc0357_1352328160518_281281_21345);
                parameters.add(_17_0_2_edc0357_1352328157885_663753_20019_exists);
                Set<Effect> effectsForeffect387Var = new TreeSet<Effect>();
                effectsForeffect387Var.add(effect387);
                addEffects((Parameter<?>) effect387Var, effectsForeffect387Var);
            }

            public void init_17_0_2_edc0357_1352328157887_551523_20026Dependencies() {
                addDependency(_17_0_2_edc0357_1352328160518_55671_21344, new Expression<Integer>(new FunctionCall(null, ClassUtils.getMethodForArgTypes("TimeVaryingMap<Integer>", "powersystem", "getValue", java.lang.Integer.class), new Object[] { new Expression<Integer>(startTime) }, new FunctionCall(_17_0_2_edc0357_1352328160518_281281_21345, Parameter.class, "getMember", new Object[] { "load__17_0_2_edc0357_1352328156636_307114_19611" }))));
                addDependency(_17_0_2_edc0357_1352328160518_281281_21345, new Expression<Power_System.SignalchangeLoadValue>(new FunctionCall(sig_17_0_2_edc0357_1352328157891_604127_20046, ClassUtils.getMethodForArgTypes("ObjectFlow<SignalchangeLoadValue>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(_17_0_2_edc0357_1352328157885_663753_20019_exists, new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall));
            }

            public void init_17_0_2_edc0357_1352328157887_551523_20026Elaborations() {
                init_17_0_2_edc0357_1352328157887_551523_20026Dependencies();
                Expression<?>[] arguments388 = new Expression<?>[1];
                arguments388[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition388 = new Expression<Boolean>(_17_0_2_edc0357_1352328157885_663753_20019_exists);
                elaborationRule388 = addElaborationRule(condition388, _17_0_2_edc0357_1352328156606_330566_19600.this, Power._17_0_2_edc0357_1352328156606_330566_19600._17_0_2_edc0357_1352328157885_663753_20019.class, "clv_CallBehaviorAction_PowerCB", arguments388);
            }
        }

        public class _17_0_2_edc0357_1352328157888_384561_20027 extends DurativeEvent {

            public _17_0_2_edc0357_1352328157888_384561_20027() {
                super();
                init_17_0_2_edc0357_1352328157888_384561_20027Members();
                init_17_0_2_edc0357_1352328157888_384561_20027Collections();
                init_17_0_2_edc0357_1352328157888_384561_20027Elaborations();
            }

            public _17_0_2_edc0357_1352328157888_384561_20027(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328157888_384561_20027Members();
                init_17_0_2_edc0357_1352328157888_384561_20027Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328157888_384561_20027Elaborations();
                fixTimeDependencies();
            }

            public IntegerParameter _17_0_2_edc0357_1352328160519_980653_21346 = null;

            public BooleanParameter _17_0_2_edc0357_1352328157886_986405_20020_exists = null;

            public Parameter< Power_System.SignalchangeGenerationValue > _17_0_2_edc0357_1352328160520_704534_21347 = null;

            public Dependency< Integer > _17_0_2_edc0357_1352328160519_980653_21346Dependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328157886_986405_20020_existsDependency = null;

            public Dependency< Power_System.SignalchangeGenerationValue > _17_0_2_edc0357_1352328160520_704534_21347Dependency = null;

            public Effect effect389 = null;

            public Parameter effect389Var = null;

            public ElaborationRule elaborationRule390 = null;

            public void init_17_0_2_edc0357_1352328157888_384561_20027Members() {
                try {
                    if (_17_0_2_edc0357_1352328160519_980653_21346 == null) _17_0_2_edc0357_1352328160519_980653_21346 = new IntegerParameter("_17_0_2_edc0357_1352328160519_980653_21346", (Integer) null, this);
                    if (_17_0_2_edc0357_1352328157886_986405_20020_exists == null) _17_0_2_edc0357_1352328157886_986405_20020_exists = new BooleanParameter("_17_0_2_edc0357_1352328157886_986405_20020_exists", (Boolean) false, this);
                    if (_17_0_2_edc0357_1352328160520_704534_21347 == null) _17_0_2_edc0357_1352328160520_704534_21347 = new Parameter<Power_System.SignalchangeGenerationValue>("_17_0_2_edc0357_1352328160520_704534_21347", null, (Power_System.SignalchangeGenerationValue) null, this);
                    Object effect389VarV = sig_17_0_2_edc0357_1352328157891_658508_20049;
                    effect389Var = new Parameter("effect389Var", null, null, this);
                    addDependency(effect389Var, new Expression(effect389VarV));
                    effect389 = new EffectFunction(new FunctionCall(effect389Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Integer>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { _17_0_2_edc0357_1352328160519_980653_21346, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328157888_384561_20027Collections() {
                parameters.add(_17_0_2_edc0357_1352328160519_980653_21346);
                parameters.add(_17_0_2_edc0357_1352328157886_986405_20020_exists);
                parameters.add(_17_0_2_edc0357_1352328160520_704534_21347);
                Set<Effect> effectsForeffect389Var = new TreeSet<Effect>();
                effectsForeffect389Var.add(effect389);
                addEffects((Parameter<?>) effect389Var, effectsForeffect389Var);
            }

            public void init_17_0_2_edc0357_1352328157888_384561_20027Dependencies() {
                addDependency(_17_0_2_edc0357_1352328160519_980653_21346, new Expression<Integer>(new FunctionCall(null, ClassUtils.getMethodForArgTypes("TimeVaryingMap<Integer>", "powersystem", "getValue", java.lang.Integer.class), new Object[] { new Expression<Integer>(startTime) }, new FunctionCall(_17_0_2_edc0357_1352328160520_704534_21347, Parameter.class, "getMember", new Object[] { "newGenValue__17_0_2_edc0357_1352328156635_470763_19610" }))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(_17_0_2_edc0357_1352328157886_986405_20020_exists, new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall));
                addDependency(_17_0_2_edc0357_1352328160520_704534_21347, new Expression<Power_System.SignalchangeGenerationValue>(new FunctionCall(sig_17_0_2_edc0357_1352328157891_306212_20048, ClassUtils.getMethodForArgTypes("ObjectFlow<SignalchangeGenerationValue>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_edc0357_1352328157888_384561_20027Elaborations() {
                init_17_0_2_edc0357_1352328157888_384561_20027Dependencies();
                Expression<?>[] arguments390 = new Expression<?>[1];
                arguments390[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition390 = new Expression<Boolean>(_17_0_2_edc0357_1352328157886_986405_20020_exists);
                elaborationRule390 = addElaborationRule(condition390, _17_0_2_edc0357_1352328156606_330566_19600.this, Power._17_0_2_edc0357_1352328156606_330566_19600._17_0_2_edc0357_1352328157886_986405_20020.class, "cgv_CallBehaviorAction_PowerCB", arguments390);
            }
        }

        public class _17_0_2_edc0357_1352328337903_895019_27876 extends DurativeEvent {

            public _17_0_2_edc0357_1352328337903_895019_27876() {
                super();
                init_17_0_2_edc0357_1352328337903_895019_27876Members();
                init_17_0_2_edc0357_1352328337903_895019_27876Collections();
                init_17_0_2_edc0357_1352328337903_895019_27876Elaborations();
            }

            public _17_0_2_edc0357_1352328337903_895019_27876(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328337903_895019_27876Members();
                init_17_0_2_edc0357_1352328337903_895019_27876Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328337903_895019_27876Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter objectToPass = null;

            public Dependency< Integer > finalNode_startTimeDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Effect effect391 = null;

            public Parameter effect391Var = null;

            public void init_17_0_2_edc0357_1352328337903_895019_27876Members() {
                try {
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect391VarV = sig_17_0_2_edc0357_1352328345871_599890_27897;
                    effect391Var = new Parameter("effect391Var", null, null, this);
                    addDependency(effect391Var, new Expression(effect391VarV));
                    effect391 = new EffectFunction(new FunctionCall(effect391Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328337903_895019_27876Collections() {
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect391Var = new TreeSet<Effect>();
                effectsForeffect391Var.add(effect391);
                addEffects((Parameter<?>) effect391Var, effectsForeffect391Var);
            }

            public void init_17_0_2_edc0357_1352328337903_895019_27876Dependencies() {
                addDependency(finalNode_startTime, new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall));
                addDependency(duration, new Expression<Integer>(5000));
                addDependency(objectToPass, new Expression<Boolean>(new FunctionCall(sig_17_0_2_edc0357_1352328341605_92100_27892, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_edc0357_1352328337903_895019_27876Elaborations() {
                init_17_0_2_edc0357_1352328337903_895019_27876Dependencies();
            }
        }

        public class _17_0_2_edc0357_1352328362977_584291_27902 extends DurativeEvent {

            public _17_0_2_edc0357_1352328362977_584291_27902() {
                super();
                init_17_0_2_edc0357_1352328362977_584291_27902Members();
                init_17_0_2_edc0357_1352328362977_584291_27902Collections();
                init_17_0_2_edc0357_1352328362977_584291_27902Elaborations();
            }

            public _17_0_2_edc0357_1352328362977_584291_27902(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328362977_584291_27902Members();
                init_17_0_2_edc0357_1352328362977_584291_27902Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328362977_584291_27902Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_edc0357_1352328157887_650991_20024_exists = null;

            public BooleanParameter objectToPass = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328157887_650991_20024_existsDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Effect effect392 = null;

            public Parameter effect392Var = null;

            public ElaborationRule elaborationRule393 = null;

            public void init_17_0_2_edc0357_1352328362977_584291_27902Members() {
                try {
                    if (_17_0_2_edc0357_1352328157887_650991_20024_exists == null) _17_0_2_edc0357_1352328157887_650991_20024_exists = new BooleanParameter("_17_0_2_edc0357_1352328157887_650991_20024_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect392VarV = sig_17_0_2_edc0357_1352328369601_81218_27921;
                    effect392Var = new Parameter("effect392Var", null, null, this);
                    addDependency(effect392Var, new Expression(effect392VarV));
                    effect392 = new EffectFunction(new FunctionCall(effect392Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328362977_584291_27902Collections() {
                parameters.add(_17_0_2_edc0357_1352328157887_650991_20024_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect392Var = new TreeSet<Effect>();
                effectsForeffect392Var.add(effect392);
                addEffects((Parameter<?>) effect392Var, effectsForeffect392Var);
            }

            public void init_17_0_2_edc0357_1352328362977_584291_27902Dependencies() {
                addDependency(_17_0_2_edc0357_1352328157887_650991_20024_exists, new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall));
                addDependency(duration, new Expression<Integer>(20));
                addDependency(objectToPass, new Expression<Boolean>(new FunctionCall(sig_17_0_2_edc0357_1352328365961_83832_27916, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_edc0357_1352328362977_584291_27902Elaborations() {
                init_17_0_2_edc0357_1352328362977_584291_27902Dependencies();
                Expression<?>[] arguments393 = new Expression<?>[2];
                arguments393[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                arguments393[1] = new Expression<ObjectFlow<Boolean>>(sig_17_0_2_edc0357_1352328369601_81218_27921);
                Expression<Boolean> condition393 = new Expression<Boolean>(_17_0_2_edc0357_1352328157887_650991_20024_exists);
                elaborationRule393 = addElaborationRule(condition393, _17_0_2_edc0357_1352328156606_330566_19600.this, Power._17_0_2_edc0357_1352328156606_330566_19600._17_0_2_edc0357_1352328157887_650991_20024.class, "_MergeNode_PowerCB", arguments393);
            }
        }

        public class _17_0_2_edc0357_1352328378867_561466_27925 extends DurativeEvent {

            public _17_0_2_edc0357_1352328378867_561466_27925() {
                super();
                init_17_0_2_edc0357_1352328378867_561466_27925Members();
                init_17_0_2_edc0357_1352328378867_561466_27925Collections();
                init_17_0_2_edc0357_1352328378867_561466_27925Elaborations();
            }

            public _17_0_2_edc0357_1352328378867_561466_27925(Expression<Integer> startTime) {
                super();
                init_17_0_2_edc0357_1352328378867_561466_27925Members();
                init_17_0_2_edc0357_1352328378867_561466_27925Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_edc0357_1352328378867_561466_27925Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_edc0357_1352328157869_383435_20016_exists = null;

            public BooleanParameter objectToPass = null;

            public Dependency< Boolean > _17_0_2_edc0357_1352328157869_383435_20016_existsDependency = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > objectToPassDependency = null;

            public Effect effect394 = null;

            public Parameter effect394Var = null;

            public ElaborationRule elaborationRule395 = null;

            public void init_17_0_2_edc0357_1352328378867_561466_27925Members() {
                try {
                    if (_17_0_2_edc0357_1352328157869_383435_20016_exists == null) _17_0_2_edc0357_1352328157869_383435_20016_exists = new BooleanParameter("_17_0_2_edc0357_1352328157869_383435_20016_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect394VarV = sig_17_0_2_edc0357_1352328411901_931136_27947;
                    effect394Var = new Parameter("effect394Var", null, null, this);
                    addDependency(effect394Var, new Expression(effect394VarV));
                    effect394 = new EffectFunction(new FunctionCall(effect394Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_edc0357_1352328378867_561466_27925Collections() {
                parameters.add(_17_0_2_edc0357_1352328157869_383435_20016_exists);
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect394Var = new TreeSet<Effect>();
                effectsForeffect394Var.add(effect394);
                addEffects((Parameter<?>) effect394Var, effectsForeffect394Var);
            }

            public void init_17_0_2_edc0357_1352328378867_561466_27925Dependencies() {
                addDependency(_17_0_2_edc0357_1352328157869_383435_20016_exists, new Expression<Boolean>((new Functions.Less(new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall), new Expression<Integer>(finalNode_startTime))).functionCall));
                addDependency(duration, new Expression<Integer>(5));
                addDependency(objectToPass, new Expression<Boolean>(new FunctionCall(sig_17_0_2_edc0357_1352328408365_648034_27942, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_edc0357_1352328378867_561466_27925Elaborations() {
                init_17_0_2_edc0357_1352328378867_561466_27925Dependencies();
                Expression<?>[] arguments395 = new Expression<?>[1];
                arguments395[0] = new Expression<Integer>((new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))).functionCall);
                Expression<Boolean> condition395 = new Expression<Boolean>(_17_0_2_edc0357_1352328157869_383435_20016_exists);
                elaborationRule395 = addElaborationRule(condition395, _17_0_2_edc0357_1352328156606_330566_19600.this, Power._17_0_2_edc0357_1352328156606_330566_19600._17_0_2_edc0357_1352328157869_383435_20016.class, "_ForkNode_PowerCB", arguments395);
            }
        }

        public _17_0_2_edc0357_1352328156606_330566_19600(Expression<Integer> startTime) {
            super();
            init_17_0_2_edc0357_1352328156606_330566_19600Members();
            init_17_0_2_edc0357_1352328156606_330566_19600Collections();
            addDependency(this.startTime, startTime);
            init_17_0_2_edc0357_1352328156606_330566_19600Elaborations();
            fixTimeDependencies();
        }

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_edc0357_1352328341605_92100_27892 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_edc0357_1352328157890_22043_20043 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_edc0357_1352328157890_191195_20044 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_2_edc0357_1352328157891_490819_20047 = null;

        public Parameter< ObjectFlow<Power_System.SignalchangeLoadValue> > sig_17_0_2_edc0357_1352328157891_604127_20046 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_edc0357_1352328345871_599890_27897 = null;

        public Parameter< ObjectFlow<Power_System.SignalchangeLoadValue> > sig_17_0_2_edc0357_1352328155026_529309_19212 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_edc0357_1352328408365_648034_27942 = null;

        public BooleanParameter _17_0_2_edc0357_1352328157887_127545_20025_exists = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_edc0357_1352328365961_83832_27916 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_edc0357_1352328157890_967898_20041 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_edc0357_1352328157890_167680_20037 = null;

        public IntegerParameter finalNode_startTime = null;

        public Parameter< ObjectFlow<Power_System.SignalchangeGenerationValue> > sig_17_0_2_edc0357_1352328155026_654214_19211 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_edc0357_1352328157890_558165_20039 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_2_edc0357_1352328157891_658508_20049 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_edc0357_1352328157890_223924_20038 = null;

        public IntegerParameter finalNode_endTime = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_edc0357_1352328411901_931136_27947 = null;

        public Parameter< ObjectFlow<Power_System.SignalchangeGenerationValue> > sig_17_0_2_edc0357_1352328157891_306212_20048 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_edc0357_1352328157890_774517_20042 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_edc0357_1352328157890_575806_20045 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_edc0357_1352328157890_321909_20040 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_edc0357_1352328369601_81218_27921 = null;

        public Dependency< Boolean > _17_0_2_edc0357_1352328157887_127545_20025_existsDependency = null;

        public Dependency< Integer > endTimeDependency = null;

        public ElaborationRule elaborationRule353 = null;

        public ElaborationRule elaborationRule354 = null;

        public void init_17_0_2_edc0357_1352328156606_330566_19600Members() {
            try {
                if (sig_17_0_2_edc0357_1352328341605_92100_27892 == null) sig_17_0_2_edc0357_1352328341605_92100_27892 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328341605_92100_27892", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328341605_92100_27892" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328157890_22043_20043 == null) sig_17_0_2_edc0357_1352328157890_22043_20043 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328157890_22043_20043", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328157890_22043_20043" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328157890_191195_20044 == null) sig_17_0_2_edc0357_1352328157890_191195_20044 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328157890_191195_20044", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328157890_191195_20044" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328157891_490819_20047 == null) sig_17_0_2_edc0357_1352328157891_490819_20047 = new Parameter<ObjectFlow<Integer>>("sig_17_0_2_edc0357_1352328157891_490819_20047", null, (ObjectFlow<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328157891_490819_20047" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328157891_604127_20046 == null) sig_17_0_2_edc0357_1352328157891_604127_20046 = new Parameter<ObjectFlow<Power_System.SignalchangeLoadValue>>("sig_17_0_2_edc0357_1352328157891_604127_20046", null, (ObjectFlow<Power_System.SignalchangeLoadValue>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328157891_604127_20046" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328345871_599890_27897 == null) sig_17_0_2_edc0357_1352328345871_599890_27897 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328345871_599890_27897", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328345871_599890_27897" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328155026_529309_19212 == null) sig_17_0_2_edc0357_1352328155026_529309_19212 = new Parameter<ObjectFlow<Power_System.SignalchangeLoadValue>>("sig_17_0_2_edc0357_1352328155026_529309_19212", null, (ObjectFlow<Power_System.SignalchangeLoadValue>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328155026_529309_19212" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328408365_648034_27942 == null) sig_17_0_2_edc0357_1352328408365_648034_27942 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328408365_648034_27942", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328408365_648034_27942" })).evaluate(true), this);
                if (_17_0_2_edc0357_1352328157887_127545_20025_exists == null) _17_0_2_edc0357_1352328157887_127545_20025_exists = new BooleanParameter("_17_0_2_edc0357_1352328157887_127545_20025_exists", (Boolean) false, this);
                if (sig_17_0_2_edc0357_1352328365961_83832_27916 == null) sig_17_0_2_edc0357_1352328365961_83832_27916 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328365961_83832_27916", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328365961_83832_27916" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328157890_967898_20041 == null) sig_17_0_2_edc0357_1352328157890_967898_20041 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328157890_967898_20041", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328157890_967898_20041" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328157890_167680_20037 == null) sig_17_0_2_edc0357_1352328157890_167680_20037 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328157890_167680_20037", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328157890_167680_20037" })).evaluate(true), this);
                if (finalNode_startTime == null) finalNode_startTime = new IntegerParameter("finalNode_startTime", (Integer) 84000, this);
                if (sig_17_0_2_edc0357_1352328155026_654214_19211 == null) sig_17_0_2_edc0357_1352328155026_654214_19211 = new Parameter<ObjectFlow<Power_System.SignalchangeGenerationValue>>("sig_17_0_2_edc0357_1352328155026_654214_19211", null, (ObjectFlow<Power_System.SignalchangeGenerationValue>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328155026_654214_19211" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328157890_558165_20039 == null) sig_17_0_2_edc0357_1352328157890_558165_20039 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328157890_558165_20039", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328157890_558165_20039" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328157891_658508_20049 == null) sig_17_0_2_edc0357_1352328157891_658508_20049 = new Parameter<ObjectFlow<Integer>>("sig_17_0_2_edc0357_1352328157891_658508_20049", null, (ObjectFlow<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328157891_658508_20049" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328157890_223924_20038 == null) sig_17_0_2_edc0357_1352328157890_223924_20038 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328157890_223924_20038", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328157890_223924_20038" })).evaluate(true), this);
                if (finalNode_endTime == null) finalNode_endTime = new IntegerParameter("finalNode_endTime", (Integer) 84000, this);
                if (sig_17_0_2_edc0357_1352328411901_931136_27947 == null) sig_17_0_2_edc0357_1352328411901_931136_27947 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328411901_931136_27947", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328411901_931136_27947" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328157891_306212_20048 == null) sig_17_0_2_edc0357_1352328157891_306212_20048 = new Parameter<ObjectFlow<Power_System.SignalchangeGenerationValue>>("sig_17_0_2_edc0357_1352328157891_306212_20048", null, (ObjectFlow<Power_System.SignalchangeGenerationValue>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328157891_306212_20048" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328157890_774517_20042 == null) sig_17_0_2_edc0357_1352328157890_774517_20042 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328157890_774517_20042", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328157890_774517_20042" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328157890_575806_20045 == null) sig_17_0_2_edc0357_1352328157890_575806_20045 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328157890_575806_20045", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328157890_575806_20045" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328157890_321909_20040 == null) sig_17_0_2_edc0357_1352328157890_321909_20040 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328157890_321909_20040", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328157890_321909_20040" })).evaluate(true), this);
                if (sig_17_0_2_edc0357_1352328369601_81218_27921 == null) sig_17_0_2_edc0357_1352328369601_81218_27921 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_edc0357_1352328369601_81218_27921", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_edc0357_1352328369601_81218_27921" })).evaluate(true), this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void init_17_0_2_edc0357_1352328156606_330566_19600Collections() {
            parameters.add(sig_17_0_2_edc0357_1352328341605_92100_27892);
            parameters.add(sig_17_0_2_edc0357_1352328157890_22043_20043);
            parameters.add(sig_17_0_2_edc0357_1352328157890_191195_20044);
            parameters.add(sig_17_0_2_edc0357_1352328157891_490819_20047);
            parameters.add(sig_17_0_2_edc0357_1352328157891_604127_20046);
            parameters.add(sig_17_0_2_edc0357_1352328345871_599890_27897);
            parameters.add(sig_17_0_2_edc0357_1352328155026_529309_19212);
            parameters.add(sig_17_0_2_edc0357_1352328408365_648034_27942);
            parameters.add(_17_0_2_edc0357_1352328157887_127545_20025_exists);
            parameters.add(sig_17_0_2_edc0357_1352328365961_83832_27916);
            parameters.add(sig_17_0_2_edc0357_1352328157890_967898_20041);
            parameters.add(sig_17_0_2_edc0357_1352328157890_167680_20037);
            parameters.add(finalNode_startTime);
            parameters.add(sig_17_0_2_edc0357_1352328155026_654214_19211);
            parameters.add(sig_17_0_2_edc0357_1352328157890_558165_20039);
            parameters.add(sig_17_0_2_edc0357_1352328157891_658508_20049);
            parameters.add(sig_17_0_2_edc0357_1352328157890_223924_20038);
            parameters.add(finalNode_endTime);
            parameters.add(sig_17_0_2_edc0357_1352328411901_931136_27947);
            parameters.add(sig_17_0_2_edc0357_1352328157891_306212_20048);
            parameters.add(sig_17_0_2_edc0357_1352328157890_774517_20042);
            parameters.add(sig_17_0_2_edc0357_1352328157890_575806_20045);
            parameters.add(sig_17_0_2_edc0357_1352328157890_321909_20040);
            parameters.add(sig_17_0_2_edc0357_1352328369601_81218_27921);
        }

        public void init_17_0_2_edc0357_1352328156606_330566_19600Dependencies() {
            addDependency(_17_0_2_edc0357_1352328157887_127545_20025_exists, new Expression<Boolean>(new FunctionCall(sig_17_0_2_edc0357_1352328345871_599890_27897, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "powersystem", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>((new Functions.Minus(new Expression<Integer>(finalNode_startTime), new Expression<Integer>(1))).functionCall) })));
            addDependency(endTime, new Expression<Integer>(finalNode_endTime));
        }

        public void init_17_0_2_edc0357_1352328156606_330566_19600Elaborations() {
            init_17_0_2_edc0357_1352328156606_330566_19600Dependencies();
            Expression<?>[] arguments353 = new Expression<?>[1];
            arguments353[0] = new Expression<Integer>(finalNode_startTime);
            Expression<Boolean> condition353 = new Expression<Boolean>(_17_0_2_edc0357_1352328157887_127545_20025_exists);
            elaborationRule353 = addElaborationRule(condition353, _17_0_2_edc0357_1352328156606_330566_19600.this, Power._17_0_2_edc0357_1352328156606_330566_19600._17_0_2_edc0357_1352328157887_127545_20025.class, "_ActivityFinalNode_PowerCB", arguments353);
            Expression<?>[] arguments354 = new Expression<?>[1];
            arguments354[0] = new Expression<Integer>(startTime);
            Expression<Boolean> condition354 = new Expression<Boolean>(true);
            elaborationRule354 = addElaborationRule(condition354, _17_0_2_edc0357_1352328156606_330566_19600.this, Power._17_0_2_edc0357_1352328156606_330566_19600._17_0_2_edc0357_1352328157868_170657_20014.class, "_InitialNode_PowerCB", arguments354);
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

    public Parameter< TimeVaryingMap<Integer> > load__17_0_2_edc0357_1352328156622_89989_19605 = null;

    public Parameter< ObjectFlow<Power_System.SignalchangeGenerationValue> > q_Power_changeGenerationValue = null;

    public Parameter< Power_System > x = null;

    public Parameter< TimeVaryingMap<Integer> > power__17_0_2_edc0357_1352328156609_70990_19604 = null;

    public Parameter< ObjectFlow<Power_System.SignalreceiveLoadReading> > q_Power_receiveLoadReading = null;

    public Parameter< ObjectFlow<Power_System.SignalchangeLoadValue> > q_Power_changeLoadValue = null;

    public void initPowerMembers() {
        try {
            if (classifierBehavior == null) classifierBehavior = new StringParameter("classifierBehavior", (String) "_17_0_2_edc0357_1352328156606_330566_19600", this);
            if (q_Power_receiveGenReading == null) q_Power_receiveGenReading = new Parameter<ObjectFlow<Power_System.SignalreceiveGenReading>>("q_Power_receiveGenReading", null, (ObjectFlow<Power_System.SignalreceiveGenReading>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class, java.lang.Class.class), new Object[] { "q_Power_receiveGenReading", Power_System.SignalreceiveGenReading.class })).evaluate(true), this);
            if (load__17_0_2_edc0357_1352328156622_89989_19605 == null) load__17_0_2_edc0357_1352328156622_89989_19605 = new Parameter<TimeVaryingMap<Integer>>("load__17_0_2_edc0357_1352328156622_89989_19605", null, (TimeVaryingMap<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingMap.class, java.lang.String.class), new Object[] { "load" })).evaluate(true), this);
            if (q_Power_changeGenerationValue == null) q_Power_changeGenerationValue = new Parameter<ObjectFlow<Power_System.SignalchangeGenerationValue>>("q_Power_changeGenerationValue", null, (ObjectFlow<Power_System.SignalchangeGenerationValue>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class, java.lang.Class.class), new Object[] { "q_Power_changeGenerationValue", Power_System.SignalchangeGenerationValue.class })).evaluate(true), this);
            if (x == null) x = new Parameter<Power_System>("x", null, (Power_System) null, this);
            if (power__17_0_2_edc0357_1352328156609_70990_19604 == null) power__17_0_2_edc0357_1352328156609_70990_19604 = new Parameter<TimeVaryingMap<Integer>>("power__17_0_2_edc0357_1352328156609_70990_19604", null, (TimeVaryingMap<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingMap.class, java.lang.String.class), new Object[] { "power" })).evaluate(true), this);
            if (q_Power_receiveLoadReading == null) q_Power_receiveLoadReading = new Parameter<ObjectFlow<Power_System.SignalreceiveLoadReading>>("q_Power_receiveLoadReading", null, (ObjectFlow<Power_System.SignalreceiveLoadReading>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class, java.lang.Class.class), new Object[] { "q_Power_receiveLoadReading", Power_System.SignalreceiveLoadReading.class })).evaluate(true), this);
            if (q_Power_changeLoadValue == null) q_Power_changeLoadValue = new Parameter<ObjectFlow<Power_System.SignalchangeLoadValue>>("q_Power_changeLoadValue", null, (ObjectFlow<Power_System.SignalchangeLoadValue>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class, java.lang.Class.class), new Object[] { "q_Power_changeLoadValue", Power_System.SignalchangeLoadValue.class })).evaluate(true), this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void initPowerCollections() {
        parameters.add(classifierBehavior);
        parameters.add(q_Power_receiveGenReading);
        parameters.add(load__17_0_2_edc0357_1352328156622_89989_19605);
        parameters.add(q_Power_changeGenerationValue);
        parameters.add(x);
        parameters.add(power__17_0_2_edc0357_1352328156609_70990_19604);
        parameters.add(q_Power_receiveLoadReading);
        parameters.add(q_Power_changeLoadValue);
    }

    public void initPowerDependencies() {
    }

    public void initPowerElaborations() {
        initPowerDependencies();
    }
}
