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

public class Power_System extends ParameterListenerImpl {

    public class _17_0_2_2_edc0357_1357846886615_521269_19920 extends DurativeEvent {

        public _17_0_2_2_edc0357_1357846886615_521269_19920() {
            super();
            init_17_0_2_2_edc0357_1357846886615_521269_19920Members();
            init_17_0_2_2_edc0357_1357846886615_521269_19920Collections();
            init_17_0_2_2_edc0357_1357846886615_521269_19920Elaborations();
        }

        public class _17_0_2_2_edc0357_1357846887115_291670_20619 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846887115_291670_20619() {
                super();
                init_17_0_2_2_edc0357_1357846887115_291670_20619Members();
                init_17_0_2_2_edc0357_1357846887115_291670_20619Collections();
                init_17_0_2_2_edc0357_1357846887115_291670_20619Elaborations();
            }

            public _17_0_2_2_edc0357_1357846887115_291670_20619(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846887115_291670_20619Members();
                init_17_0_2_2_edc0357_1357846887115_291670_20619Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846887115_291670_20619Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter objectToPass = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846887115_953901_20620_exists = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887115_953901_20620_existsDependency = null;

            public Effect effect2 = null;

            public Parameter effect2Var = null;

            public ElaborationRule elaborationRule3 = null;

            public void init_17_0_2_2_edc0357_1357846887115_291670_20619Members() {
                try {
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (_17_0_2_2_edc0357_1357846887115_953901_20620_exists == null) _17_0_2_2_edc0357_1357846887115_953901_20620_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887115_953901_20620_exists", (Boolean) false, this);
                    Object effect2VarV = sig_17_0_2_2_edc0357_1357846887121_57958_20632;
                    effect2Var = new Parameter("effect2Var", null, null, this);
                    addDependency(effect2Var, new Expression(effect2VarV));
                    effect2 = new EffectFunction(new EffectFunction(effect2Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846887115_291670_20619Collections() {
                parameters.add(objectToPass);
                parameters.add(_17_0_2_2_edc0357_1357846887115_953901_20620_exists);
                Set<Effect> effectsForeffect2Var = new TreeSet<Effect>();
                effectsForeffect2Var.add(effect2);
                addEffects((Parameter<?>) effect2Var, effectsForeffect2Var);
            }

            public void init_17_0_2_2_edc0357_1357846887115_291670_20619Dependencies() {
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(true));
                addDependency(_17_0_2_2_edc0357_1357846887115_953901_20620_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887121_272221_20629_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887121_272221_20629_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
            }

            public void init_17_0_2_2_edc0357_1357846887115_291670_20619Elaborations() {
                init_17_0_2_2_edc0357_1357846887115_291670_20619Dependencies();
                Expression<?>[] arguments3 = new Expression<?>[1];
                arguments3[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition3 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887115_953901_20620_exists);
                elaborationRule3 = addElaborationRule(condition3, _17_0_2_2_edc0357_1357846886615_521269_19920.this, Power_System._17_0_2_2_edc0357_1357846886615_521269_19920._17_0_2_2_edc0357_1357846887115_953901_20620.class, "Sys_readself_ReadSelfAction_start_system", arguments3);
            }
        }

        public class _17_0_2_2_edc0357_1357846887115_953901_20620 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846887115_953901_20620() {
                super();
                init_17_0_2_2_edc0357_1357846887115_953901_20620Members();
                init_17_0_2_2_edc0357_1357846887115_953901_20620Collections();
                init_17_0_2_2_edc0357_1357846887115_953901_20620Elaborations();
            }

            public _17_0_2_2_edc0357_1357846887115_953901_20620(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846887115_953901_20620Members();
                init_17_0_2_2_edc0357_1357846887115_953901_20620Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846887115_953901_20620Elaborations();
                fixTimeDependencies();
            }

            public Parameter< Power_System > _17_0_2_2_edc0357_1357846887511_471163_21571 = null;

            public BooleanParameter objectToPass = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846887116_500035_20621_exists = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887116_500035_20621_existsDependency = null;

            public Effect effect4 = null;

            public Parameter effect4Var = null;

            public ElaborationRule elaborationRule5 = null;

            public void init_17_0_2_2_edc0357_1357846887115_953901_20620Members() {
                try {
                    if (_17_0_2_2_edc0357_1357846887511_471163_21571 == null) _17_0_2_2_edc0357_1357846887511_471163_21571 = new Parameter<Power_System>("_17_0_2_2_edc0357_1357846887511_471163_21571", null, (Power_System) Power_System.this, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (_17_0_2_2_edc0357_1357846887116_500035_20621_exists == null) _17_0_2_2_edc0357_1357846887116_500035_20621_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887116_500035_20621_exists", (Boolean) false, this);
                    Object effect4VarV = sig_17_0_2_2_edc0357_1357846887121_127072_20633;
                    effect4Var = new Parameter("effect4Var", null, null, this);
                    addDependency(effect4Var, new Expression(effect4VarV));
                    effect4 = new EffectFunction(new EffectFunction(effect4Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Power_System>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { _17_0_2_2_edc0357_1357846887511_471163_21571, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846887115_953901_20620Collections() {
                parameters.add(_17_0_2_2_edc0357_1357846887511_471163_21571);
                parameters.add(objectToPass);
                parameters.add(_17_0_2_2_edc0357_1357846887116_500035_20621_exists);
                Set<Effect> effectsForeffect4Var = new TreeSet<Effect>();
                effectsForeffect4Var.add(effect4);
                addEffects((Parameter<?>) effect4Var, effectsForeffect4Var);
            }

            public void init_17_0_2_2_edc0357_1357846887115_953901_20620Dependencies() {
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887121_57958_20632, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_2_edc0357_1357846887116_500035_20621_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887121_272221_20629_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887121_272221_20629_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
            }

            public void init_17_0_2_2_edc0357_1357846887115_953901_20620Elaborations() {
                init_17_0_2_2_edc0357_1357846887115_953901_20620Dependencies();
                Expression<?>[] arguments5 = new Expression<?>[1];
                arguments5[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition5 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887116_500035_20621_exists);
                elaborationRule5 = addElaborationRule(condition5, _17_0_2_2_edc0357_1357846886615_521269_19920.this, Power_System._17_0_2_2_edc0357_1357846886615_521269_19920._17_0_2_2_edc0357_1357846887116_500035_20621.class, "Sys_fork_ForkNode_start_system", arguments5);
            }
        }

        public class _17_0_2_2_edc0357_1357846887116_500035_20621 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846887116_500035_20621() {
                super();
                init_17_0_2_2_edc0357_1357846887116_500035_20621Members();
                init_17_0_2_2_edc0357_1357846887116_500035_20621Collections();
                init_17_0_2_2_edc0357_1357846887116_500035_20621Elaborations();
            }

            public _17_0_2_2_edc0357_1357846887116_500035_20621(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846887116_500035_20621Members();
                init_17_0_2_2_edc0357_1357846887116_500035_20621Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846887116_500035_20621Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter _17_0_2_2_edc0357_1357846887117_923873_20623_exists = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846887116_100180_20622_exists = null;

            public Parameter< Power_System > objectToPass = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846887119_132697_20626_exists = null;

            public Dependency _17_0_2_2_edc0357_1357846887117_923873_20623_existsDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887116_100180_20622_existsDependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887119_132697_20626_existsDependency = null;

            public Effect effect6 = null;

            public Parameter effect6Var = null;

            public Effect effect7 = null;

            public Parameter effect7Var = null;

            public Effect effect8 = null;

            public Parameter effect8Var = null;

            public ElaborationRule elaborationRule9 = null;

            public ElaborationRule elaborationRule10 = null;

            public ElaborationRule elaborationRule11 = null;

            public void init_17_0_2_2_edc0357_1357846887116_500035_20621Members() {
                try {
                    if (_17_0_2_2_edc0357_1357846887117_923873_20623_exists == null) _17_0_2_2_edc0357_1357846887117_923873_20623_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887117_923873_20623_exists", (Boolean) false, this);
                    if (_17_0_2_2_edc0357_1357846887116_100180_20622_exists == null) _17_0_2_2_edc0357_1357846887116_100180_20622_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887116_100180_20622_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new Parameter<Power_System>("objectToPass", null, (Power_System) null, this);
                    if (_17_0_2_2_edc0357_1357846887119_132697_20626_exists == null) _17_0_2_2_edc0357_1357846887119_132697_20626_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887119_132697_20626_exists", (Boolean) false, this);
                    Object effect6VarV = sig_17_0_2_2_edc0357_1357846887122_499839_20634;
                    effect6Var = new Parameter("effect6Var", null, null, this);
                    addDependency(effect6Var, new Expression(effect6VarV));
                    effect6 = new EffectFunction(new EffectFunction(effect6Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Power_System>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect7VarV = sig_17_0_2_2_edc0357_1357846887122_3334_20635;
                    effect7Var = new Parameter("effect7Var", null, null, this);
                    addDependency(effect7Var, new Expression(effect7VarV));
                    effect7 = new EffectFunction(new EffectFunction(effect7Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Power_System>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect8VarV = sig_17_0_2_2_edc0357_1357846887122_115492_20639;
                    effect8Var = new Parameter("effect8Var", null, null, this);
                    addDependency(effect8Var, new Expression(effect8VarV));
                    effect8 = new EffectFunction(new EffectFunction(effect8Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Power_System>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846887116_500035_20621Collections() {
                parameters.add(_17_0_2_2_edc0357_1357846887117_923873_20623_exists);
                parameters.add(_17_0_2_2_edc0357_1357846887116_100180_20622_exists);
                parameters.add(objectToPass);
                parameters.add(_17_0_2_2_edc0357_1357846887119_132697_20626_exists);
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

            public void init_17_0_2_2_edc0357_1357846887116_500035_20621Dependencies() {
                addDependency(_17_0_2_2_edc0357_1357846887117_923873_20623_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887121_272221_20629_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887121_272221_20629_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(_17_0_2_2_edc0357_1357846887116_100180_20622_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887121_272221_20629_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887121_272221_20629_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887121_127072_20633, ClassUtils.getMethodForArgTypes("ObjectFlow<Power_System>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_2_edc0357_1357846887119_132697_20626_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887121_272221_20629_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887121_272221_20629_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
            }

            public void init_17_0_2_2_edc0357_1357846887116_500035_20621Elaborations() {
                init_17_0_2_2_edc0357_1357846887116_500035_20621Dependencies();
                Expression<?>[] arguments9 = new Expression<?>[1];
                arguments9[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition9 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887119_132697_20626_exists);
                elaborationRule9 = addElaborationRule(condition9, _17_0_2_2_edc0357_1357846886615_521269_19920.this, Power_System._17_0_2_2_edc0357_1357846886615_521269_19920._17_0_2_2_edc0357_1357846887119_132697_20626.class, "Sys_readl_ReadStructuralFeatureAction_start_system", arguments9);
                Expression<?>[] arguments10 = new Expression<?>[1];
                arguments10[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition10 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887117_923873_20623_exists);
                elaborationRule10 = addElaborationRule(condition10, _17_0_2_2_edc0357_1357846886615_521269_19920.this, Power_System._17_0_2_2_edc0357_1357846886615_521269_19920._17_0_2_2_edc0357_1357846887117_923873_20623.class, "Sys_readp_ReadStructuralFeatureAction_start_system", arguments10);
                Expression<?>[] arguments11 = new Expression<?>[1];
                arguments11[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition11 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887116_100180_20622_exists);
                elaborationRule11 = addElaborationRule(condition11, _17_0_2_2_edc0357_1357846886615_521269_19920.this, Power_System._17_0_2_2_edc0357_1357846886615_521269_19920._17_0_2_2_edc0357_1357846887116_100180_20622.class, "Sys_readc_ReadStructuralFeatureAction_start_system", arguments11);
            }
        }

        public class _17_0_2_2_edc0357_1357846887116_100180_20622 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846887116_100180_20622() {
                super();
                init_17_0_2_2_edc0357_1357846887116_100180_20622Members();
                init_17_0_2_2_edc0357_1357846887116_100180_20622Collections();
                init_17_0_2_2_edc0357_1357846887116_100180_20622Elaborations();
            }

            public _17_0_2_2_edc0357_1357846887116_100180_20622(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846887116_100180_20622Members();
                init_17_0_2_2_edc0357_1357846887116_100180_20622Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846887116_100180_20622Elaborations();
                fixTimeDependencies();
            }

            public Parameter< Customer > _17_0_2_2_edc0357_1357846887512_91100_21572 = null;

            public Parameter< Power_System > _17_0_2_2_edc0357_1357846887512_72385_21573 = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846887118_5971_20624_exists = null;

            public Dependency _17_0_2_2_edc0357_1357846887512_91100_21572Dependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887512_72385_21573Dependency = null;

            public Dependency durationDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887118_5971_20624_existsDependency = null;

            public Effect effect12 = null;

            public Parameter effect12Var = null;

            public ElaborationRule elaborationRule13 = null;

            public void init_17_0_2_2_edc0357_1357846887116_100180_20622Members() {
                try {
                    if (_17_0_2_2_edc0357_1357846887512_91100_21572 == null) _17_0_2_2_edc0357_1357846887512_91100_21572 = new Parameter<Customer>("_17_0_2_2_edc0357_1357846887512_91100_21572", null, (Customer) null, this);
                    if (_17_0_2_2_edc0357_1357846887512_72385_21573 == null) _17_0_2_2_edc0357_1357846887512_72385_21573 = new Parameter<Power_System>("_17_0_2_2_edc0357_1357846887512_72385_21573", null, (Power_System) null, this);
                    if (_17_0_2_2_edc0357_1357846887118_5971_20624_exists == null) _17_0_2_2_edc0357_1357846887118_5971_20624_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887118_5971_20624_exists", (Boolean) false, this);
                    Object effect12VarV = sig_17_0_2_2_edc0357_1357846887122_214571_20636;
                    effect12Var = new Parameter("effect12Var", null, null, this);
                    addDependency(effect12Var, new Expression(effect12VarV));
                    effect12 = new EffectFunction(new EffectFunction(effect12Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { _17_0_2_2_edc0357_1357846887512_91100_21572, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846887116_100180_20622Collections() {
                parameters.add(_17_0_2_2_edc0357_1357846887512_91100_21572);
                parameters.add(_17_0_2_2_edc0357_1357846887512_72385_21573);
                parameters.add(_17_0_2_2_edc0357_1357846887118_5971_20624_exists);
                Set<Effect> effectsForeffect12Var = new TreeSet<Effect>();
                effectsForeffect12Var.add(effect12);
                addEffects((Parameter<?>) effect12Var, effectsForeffect12Var);
            }

            public void init_17_0_2_2_edc0357_1357846887116_100180_20622Dependencies() {
                addDependency(_17_0_2_2_edc0357_1357846887512_91100_21572, new Expression<Customer>(new FunctionCall(_17_0_2_2_edc0357_1357846887512_72385_21573, Parameter.class, "getMember", new Object[] { "c" })));
                addDependency(_17_0_2_2_edc0357_1357846887512_72385_21573, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887122_499839_20634, ClassUtils.getMethodForArgTypes("ObjectFlow<Power_System>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(_17_0_2_2_edc0357_1357846887118_5971_20624_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887121_272221_20629_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887121_272221_20629_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
            }

            public void init_17_0_2_2_edc0357_1357846887116_100180_20622Elaborations() {
                init_17_0_2_2_edc0357_1357846887116_100180_20622Dependencies();
                Expression<?>[] arguments13 = new Expression<?>[1];
                arguments13[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition13 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887118_5971_20624_exists);
                elaborationRule13 = addElaborationRule(condition13, _17_0_2_2_edc0357_1357846886615_521269_19920.this, Power_System._17_0_2_2_edc0357_1357846886615_521269_19920._17_0_2_2_edc0357_1357846887118_5971_20624.class, "sobc_StartObjectBehaviorAction_start_system", arguments13);
            }
        }

        public class _17_0_2_2_edc0357_1357846887117_923873_20623 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846887117_923873_20623() {
                super();
                init_17_0_2_2_edc0357_1357846887117_923873_20623Members();
                init_17_0_2_2_edc0357_1357846887117_923873_20623Collections();
                init_17_0_2_2_edc0357_1357846887117_923873_20623Elaborations();
            }

            public _17_0_2_2_edc0357_1357846887117_923873_20623(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846887117_923873_20623Members();
                init_17_0_2_2_edc0357_1357846887117_923873_20623Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846887117_923873_20623Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter addToDecider__17_0_2_2_edc0357_1357846887118_697774_20625 = null;

            public IntegerParameter myDeciderID_decider_17_0_2_2_edc0357_1357846887118_697774_20625 = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846887118_697774_20625_exists = null;

            public Parameter< Power_System > _17_0_2_2_edc0357_1357846887513_758853_21575 = null;

            public Parameter< Power > _17_0_2_2_edc0357_1357846887513_75363_21574 = null;

            public Dependency addToDecider__17_0_2_2_edc0357_1357846887118_697774_20625Dependency = null;

            public Dependency myDeciderID_decider_17_0_2_2_edc0357_1357846887118_697774_20625Dependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887118_697774_20625_existsDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887513_758853_21575Dependency = null;

            public Dependency durationDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887513_75363_21574Dependency = null;

            public Effect effect14 = null;

            public Parameter effect14Var = null;

            public Effect effect15 = null;

            public Parameter effect15Var = null;

            public ElaborationRule elaborationRule16 = null;

            public void init_17_0_2_2_edc0357_1357846887117_923873_20623Members() {
                try {
                    if (addToDecider__17_0_2_2_edc0357_1357846887118_697774_20625 == null) addToDecider__17_0_2_2_edc0357_1357846887118_697774_20625 = new BooleanParameter("addToDecider__17_0_2_2_edc0357_1357846887118_697774_20625", (Boolean) false, this);
                    if (myDeciderID_decider_17_0_2_2_edc0357_1357846887118_697774_20625 == null) myDeciderID_decider_17_0_2_2_edc0357_1357846887118_697774_20625 = new IntegerParameter("myDeciderID_decider_17_0_2_2_edc0357_1357846887118_697774_20625", (Integer) 1, this);
                    if (_17_0_2_2_edc0357_1357846887118_697774_20625_exists == null) _17_0_2_2_edc0357_1357846887118_697774_20625_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887118_697774_20625_exists", (Boolean) false, this);
                    if (_17_0_2_2_edc0357_1357846887513_758853_21575 == null) _17_0_2_2_edc0357_1357846887513_758853_21575 = new Parameter<Power_System>("_17_0_2_2_edc0357_1357846887513_758853_21575", null, (Power_System) null, this);
                    if (_17_0_2_2_edc0357_1357846887513_75363_21574 == null) _17_0_2_2_edc0357_1357846887513_75363_21574 = new Parameter<Power>("_17_0_2_2_edc0357_1357846887513_75363_21574", null, (Power) null, this);
                    Object effect14VarV = sig_17_0_2_2_edc0357_1357846887122_201381_20637;
                    effect14Var = new Parameter("effect14Var", null, null, this);
                    addDependency(effect14Var, new Expression(effect14VarV));
                    effect14 = new EffectFunction(new EffectFunction(effect14Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Power>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { _17_0_2_2_edc0357_1357846887513_75363_21574, endTime }));
                    Object effect15VarV = decider_17_0_2_2_edc0357_1357846887118_697774_20625;
                    effect15Var = new Parameter("effect15Var", null, null, this);
                    addDependency(effect15Var, new Expression(effect15VarV));
                    effect15 = new EffectFunction(new EffectFunction(effect15Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class, java.lang.Boolean.class), new Object[] { endTime, myDeciderID_decider_17_0_2_2_edc0357_1357846887118_697774_20625, addToDecider__17_0_2_2_edc0357_1357846887118_697774_20625 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846887117_923873_20623Collections() {
                parameters.add(addToDecider__17_0_2_2_edc0357_1357846887118_697774_20625);
                parameters.add(myDeciderID_decider_17_0_2_2_edc0357_1357846887118_697774_20625);
                parameters.add(_17_0_2_2_edc0357_1357846887118_697774_20625_exists);
                parameters.add(_17_0_2_2_edc0357_1357846887513_758853_21575);
                parameters.add(_17_0_2_2_edc0357_1357846887513_75363_21574);
                Set<Effect> effectsForeffect14Var = new TreeSet<Effect>();
                effectsForeffect14Var.add(effect14);
                addEffects((Parameter<?>) effect14Var, effectsForeffect14Var);
                Set<Effect> effectsForeffect15Var = new TreeSet<Effect>();
                effectsForeffect15Var.add(effect15);
                addEffects((Parameter<?>) effect15Var, effectsForeffect15Var);
            }

            public void init_17_0_2_2_edc0357_1357846887117_923873_20623Dependencies() {
                addDependency(addToDecider__17_0_2_2_edc0357_1357846887118_697774_20625, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887121_272221_20629_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887121_272221_20629_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(myDeciderID_decider_17_0_2_2_edc0357_1357846887118_697774_20625, new Expression<Integer>(1));
                addDependency(_17_0_2_2_edc0357_1357846887118_697774_20625_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887121_272221_20629_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887121_272221_20629_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887118_697774_20625, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887118_697774_20625, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887118_697774_20625, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_2_edc0357_1357846887118_697774_20625)))))))));
                addDependency(_17_0_2_2_edc0357_1357846887513_758853_21575, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887122_3334_20635, ClassUtils.getMethodForArgTypes("ObjectFlow<Power_System>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(_17_0_2_2_edc0357_1357846887513_75363_21574, new Expression<Power>(new FunctionCall(_17_0_2_2_edc0357_1357846887513_758853_21575, Parameter.class, "getMember", new Object[] { "p" })));
            }

            public void init_17_0_2_2_edc0357_1357846887117_923873_20623Elaborations() {
                init_17_0_2_2_edc0357_1357846887117_923873_20623Dependencies();
                Expression<?>[] arguments16 = new Expression<?>[1];
                arguments16[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition16 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887118_697774_20625_exists);
                elaborationRule16 = addElaborationRule(condition16, _17_0_2_2_edc0357_1357846886615_521269_19920.this, Power_System._17_0_2_2_edc0357_1357846886615_521269_19920._17_0_2_2_edc0357_1357846887118_697774_20625.class, "sobp_StartObjectBehaviorAction_start_system", arguments16);
            }
        }

        public class _17_0_2_2_edc0357_1357846887118_5971_20624 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846887118_5971_20624() {
                super();
                init_17_0_2_2_edc0357_1357846887118_5971_20624Members();
                init_17_0_2_2_edc0357_1357846887118_5971_20624Collections();
                init_17_0_2_2_edc0357_1357846887118_5971_20624Elaborations();
            }

            public _17_0_2_2_edc0357_1357846887118_5971_20624(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846887118_5971_20624Members();
                init_17_0_2_2_edc0357_1357846887118_5971_20624Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846887118_5971_20624Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter addToDecider__17_0_2_2_edc0357_1357846887118_697774_20625 = null;

            public IntegerParameter myDeciderID_decider_17_0_2_2_edc0357_1357846887118_697774_20625 = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846887118_697774_20625_exists = null;

            public BooleanParameter objectToPass = null;

            public Parameter< Customer > _17_0_2_2_edc0357_1357846887514_994191_21576 = null;

            public Dependency addToDecider__17_0_2_2_edc0357_1357846887118_697774_20625Dependency = null;

            public Dependency myDeciderID_decider_17_0_2_2_edc0357_1357846887118_697774_20625Dependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887118_697774_20625_existsDependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887514_994191_21576Dependency = null;

            public Effect effect17 = null;

            public Parameter effect17Var = null;

            public Effect effect18 = null;

            public Parameter effect18Var = null;

            public ElaborationRule elaborationRule19 = null;

            public ElaborationRule elaborationRule20 = null;

            public void init_17_0_2_2_edc0357_1357846887118_5971_20624Members() {
                try {
                    if (addToDecider__17_0_2_2_edc0357_1357846887118_697774_20625 == null) addToDecider__17_0_2_2_edc0357_1357846887118_697774_20625 = new BooleanParameter("addToDecider__17_0_2_2_edc0357_1357846887118_697774_20625", (Boolean) false, this);
                    if (myDeciderID_decider_17_0_2_2_edc0357_1357846887118_697774_20625 == null) myDeciderID_decider_17_0_2_2_edc0357_1357846887118_697774_20625 = new IntegerParameter("myDeciderID_decider_17_0_2_2_edc0357_1357846887118_697774_20625", (Integer) 2, this);
                    if (_17_0_2_2_edc0357_1357846887118_697774_20625_exists == null) _17_0_2_2_edc0357_1357846887118_697774_20625_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887118_697774_20625_exists", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (_17_0_2_2_edc0357_1357846887514_994191_21576 == null) _17_0_2_2_edc0357_1357846887514_994191_21576 = new Parameter<Customer>("_17_0_2_2_edc0357_1357846887514_994191_21576", null, (Customer) null, this);
                    Object effect17VarV = sig_17_0_2_2_edc0357_1357846887122_87910_20638;
                    effect17Var = new Parameter("effect17Var", null, null, this);
                    addDependency(effect17Var, new Expression(effect17VarV));
                    effect17 = new EffectFunction(new EffectFunction(effect17Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect18VarV = decider_17_0_2_2_edc0357_1357846887118_697774_20625;
                    effect18Var = new Parameter("effect18Var", null, null, this);
                    addDependency(effect18Var, new Expression(effect18VarV));
                    effect18 = new EffectFunction(new EffectFunction(effect18Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class, java.lang.Boolean.class), new Object[] { endTime, myDeciderID_decider_17_0_2_2_edc0357_1357846887118_697774_20625, addToDecider__17_0_2_2_edc0357_1357846887118_697774_20625 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846887118_5971_20624Collections() {
                parameters.add(addToDecider__17_0_2_2_edc0357_1357846887118_697774_20625);
                parameters.add(myDeciderID_decider_17_0_2_2_edc0357_1357846887118_697774_20625);
                parameters.add(_17_0_2_2_edc0357_1357846887118_697774_20625_exists);
                parameters.add(objectToPass);
                parameters.add(_17_0_2_2_edc0357_1357846887514_994191_21576);
                Set<Effect> effectsForeffect17Var = new TreeSet<Effect>();
                effectsForeffect17Var.add(effect17);
                addEffects((Parameter<?>) effect17Var, effectsForeffect17Var);
                Set<Effect> effectsForeffect18Var = new TreeSet<Effect>();
                effectsForeffect18Var.add(effect18);
                addEffects((Parameter<?>) effect18Var, effectsForeffect18Var);
            }

            public void init_17_0_2_2_edc0357_1357846887118_5971_20624Dependencies() {
                addDependency(addToDecider__17_0_2_2_edc0357_1357846887118_697774_20625, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887121_272221_20629_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887121_272221_20629_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(myDeciderID_decider_17_0_2_2_edc0357_1357846887118_697774_20625, new Expression<Integer>(2));
                addDependency(_17_0_2_2_edc0357_1357846887118_697774_20625_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887121_272221_20629_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887121_272221_20629_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887118_697774_20625, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887118_697774_20625, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887118_697774_20625, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_2_edc0357_1357846887118_697774_20625)))))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression<Boolean>(true));
                addDependency(_17_0_2_2_edc0357_1357846887514_994191_21576, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887122_214571_20636, ClassUtils.getMethodForArgTypes("ObjectFlow<Customer>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_2_edc0357_1357846887118_5971_20624Elaborations() {
                init_17_0_2_2_edc0357_1357846887118_5971_20624Dependencies();
                Expression<?>[] arguments19 = new Expression<?>[1];
                arguments19[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition19 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887118_697774_20625_exists);
                elaborationRule19 = addElaborationRule(condition19, _17_0_2_2_edc0357_1357846886615_521269_19920.this, Power_System._17_0_2_2_edc0357_1357846886615_521269_19920._17_0_2_2_edc0357_1357846887118_697774_20625.class, "sobp_StartObjectBehaviorAction_start_system", arguments19);
                Expression<?>[] arguments20 = new Expression<?>[1];
                arguments20[0] = new Expression<Integer>(startTime);
                Expression<Boolean> condition20 = new Expression<Boolean>(true);
                elaborationRule20 = addElaborationRule(condition20, c, Customer._17_0_2_2_edc0357_1357846886593_851494_19883.class, "CustomerCB_Activity_Customer", arguments20);
            }
        }

        public class _17_0_2_2_edc0357_1357846887118_697774_20625 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846887118_697774_20625() {
                super();
                init_17_0_2_2_edc0357_1357846887118_697774_20625Members();
                init_17_0_2_2_edc0357_1357846887118_697774_20625Collections();
                init_17_0_2_2_edc0357_1357846887118_697774_20625Elaborations();
            }

            public _17_0_2_2_edc0357_1357846887118_697774_20625(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846887118_697774_20625Members();
                init_17_0_2_2_edc0357_1357846887118_697774_20625Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846887118_697774_20625Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter addToDecider__17_0_2_2_edc0357_1357846887119_879758_20627 = null;

            public BooleanParameter objectToPass = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846887119_879758_20627_exists = null;

            public IntegerParameter myDeciderID_decider_17_0_2_2_edc0357_1357846887119_879758_20627 = null;

            public Parameter< Power > _17_0_2_2_edc0357_1357846887514_133885_21577 = null;

            public Dependency addToDecider__17_0_2_2_edc0357_1357846887119_879758_20627Dependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887119_879758_20627_existsDependency = null;

            public Dependency myDeciderID_decider_17_0_2_2_edc0357_1357846887119_879758_20627Dependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887514_133885_21577Dependency = null;

            public Effect effect21 = null;

            public Parameter effect21Var = null;

            public Effect effect22 = null;

            public Parameter effect22Var = null;

            public ElaborationRule elaborationRule23 = null;

            public ElaborationRule elaborationRule24 = null;

            public void init_17_0_2_2_edc0357_1357846887118_697774_20625Members() {
                try {
                    if (addToDecider__17_0_2_2_edc0357_1357846887119_879758_20627 == null) addToDecider__17_0_2_2_edc0357_1357846887119_879758_20627 = new BooleanParameter("addToDecider__17_0_2_2_edc0357_1357846887119_879758_20627", (Boolean) false, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (_17_0_2_2_edc0357_1357846887119_879758_20627_exists == null) _17_0_2_2_edc0357_1357846887119_879758_20627_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887119_879758_20627_exists", (Boolean) false, this);
                    if (myDeciderID_decider_17_0_2_2_edc0357_1357846887119_879758_20627 == null) myDeciderID_decider_17_0_2_2_edc0357_1357846887119_879758_20627 = new IntegerParameter("myDeciderID_decider_17_0_2_2_edc0357_1357846887119_879758_20627", (Integer) 2, this);
                    if (_17_0_2_2_edc0357_1357846887514_133885_21577 == null) _17_0_2_2_edc0357_1357846887514_133885_21577 = new Parameter<Power>("_17_0_2_2_edc0357_1357846887514_133885_21577", null, (Power) null, this);
                    Object effect21VarV = sig_17_0_2_2_edc0357_1357846887122_124309_20641;
                    effect21Var = new Parameter("effect21Var", null, null, this);
                    addDependency(effect21Var, new Expression(effect21VarV));
                    effect21 = new EffectFunction(new EffectFunction(effect21Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                    Object effect22VarV = decider_17_0_2_2_edc0357_1357846887119_879758_20627;
                    effect22Var = new Parameter("effect22Var", null, null, this);
                    addDependency(effect22Var, new Expression(effect22VarV));
                    effect22 = new EffectFunction(new EffectFunction(effect22Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class, java.lang.Boolean.class), new Object[] { endTime, myDeciderID_decider_17_0_2_2_edc0357_1357846887119_879758_20627, addToDecider__17_0_2_2_edc0357_1357846887119_879758_20627 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846887118_697774_20625Collections() {
                parameters.add(addToDecider__17_0_2_2_edc0357_1357846887119_879758_20627);
                parameters.add(objectToPass);
                parameters.add(_17_0_2_2_edc0357_1357846887119_879758_20627_exists);
                parameters.add(myDeciderID_decider_17_0_2_2_edc0357_1357846887119_879758_20627);
                parameters.add(_17_0_2_2_edc0357_1357846887514_133885_21577);
                Set<Effect> effectsForeffect21Var = new TreeSet<Effect>();
                effectsForeffect21Var.add(effect21);
                addEffects((Parameter<?>) effect21Var, effectsForeffect21Var);
                Set<Effect> effectsForeffect22Var = new TreeSet<Effect>();
                effectsForeffect22Var.add(effect22);
                addEffects((Parameter<?>) effect22Var, effectsForeffect22Var);
            }

            public void init_17_0_2_2_edc0357_1357846887118_697774_20625Dependencies() {
                addDependency(addToDecider__17_0_2_2_edc0357_1357846887119_879758_20627, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887121_272221_20629_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887121_272221_20629_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887122_87910_20638, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_2_edc0357_1357846887119_879758_20627_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887121_272221_20629_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887121_272221_20629_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887119_879758_20627, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887119_879758_20627, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887119_879758_20627, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_2_edc0357_1357846887119_879758_20627)))))))));
                addDependency(myDeciderID_decider_17_0_2_2_edc0357_1357846887119_879758_20627, new Expression<Integer>(2));
                addDependency(_17_0_2_2_edc0357_1357846887514_133885_21577, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887122_201381_20637, ClassUtils.getMethodForArgTypes("ObjectFlow<Power>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_2_edc0357_1357846887118_697774_20625Elaborations() {
                init_17_0_2_2_edc0357_1357846887118_697774_20625Dependencies();
                Expression<?>[] arguments23 = new Expression<?>[1];
                arguments23[0] = new Expression<Integer>(startTime);
                Expression<Boolean> condition23 = new Expression<Boolean>(true);
                elaborationRule23 = addElaborationRule(condition23, p, Power._17_0_2_2_edc0357_1357846886581_947081_19867.class, "PowerCB_Activity_Power", arguments23);
                Expression<?>[] arguments24 = new Expression<?>[1];
                arguments24[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition24 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887119_879758_20627_exists);
                elaborationRule24 = addElaborationRule(condition24, _17_0_2_2_edc0357_1357846886615_521269_19920.this, Power_System._17_0_2_2_edc0357_1357846886615_521269_19920._17_0_2_2_edc0357_1357846887119_879758_20627.class, "sobl_StartObjectBehaviorAction_start_system", arguments24);
            }
        }

        public class _17_0_2_2_edc0357_1357846887119_132697_20626 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846887119_132697_20626() {
                super();
                init_17_0_2_2_edc0357_1357846887119_132697_20626Members();
                init_17_0_2_2_edc0357_1357846887119_132697_20626Collections();
                init_17_0_2_2_edc0357_1357846887119_132697_20626Elaborations();
            }

            public _17_0_2_2_edc0357_1357846887119_132697_20626(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846887119_132697_20626Members();
                init_17_0_2_2_edc0357_1357846887119_132697_20626Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846887119_132697_20626Elaborations();
                fixTimeDependencies();
            }

            public Parameter< LADWP > _17_0_2_2_edc0357_1357846887515_657207_21578 = null;

            public BooleanParameter addToDecider__17_0_2_2_edc0357_1357846887119_879758_20627 = null;

            public Parameter< Power_System > _17_0_2_2_edc0357_1357846887516_607187_21579 = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846887119_879758_20627_exists = null;

            public IntegerParameter myDeciderID_decider_17_0_2_2_edc0357_1357846887119_879758_20627 = null;

            public Dependency _17_0_2_2_edc0357_1357846887515_657207_21578Dependency = null;

            public Dependency addToDecider__17_0_2_2_edc0357_1357846887119_879758_20627Dependency = null;

            public Dependency durationDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887516_607187_21579Dependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887119_879758_20627_existsDependency = null;

            public Dependency myDeciderID_decider_17_0_2_2_edc0357_1357846887119_879758_20627Dependency = null;

            public Effect effect25 = null;

            public Parameter effect25Var = null;

            public Effect effect26 = null;

            public Parameter effect26Var = null;

            public ElaborationRule elaborationRule27 = null;

            public void init_17_0_2_2_edc0357_1357846887119_132697_20626Members() {
                try {
                    if (_17_0_2_2_edc0357_1357846887515_657207_21578 == null) _17_0_2_2_edc0357_1357846887515_657207_21578 = new Parameter<LADWP>("_17_0_2_2_edc0357_1357846887515_657207_21578", null, (LADWP) null, this);
                    if (addToDecider__17_0_2_2_edc0357_1357846887119_879758_20627 == null) addToDecider__17_0_2_2_edc0357_1357846887119_879758_20627 = new BooleanParameter("addToDecider__17_0_2_2_edc0357_1357846887119_879758_20627", (Boolean) false, this);
                    if (_17_0_2_2_edc0357_1357846887516_607187_21579 == null) _17_0_2_2_edc0357_1357846887516_607187_21579 = new Parameter<Power_System>("_17_0_2_2_edc0357_1357846887516_607187_21579", null, (Power_System) null, this);
                    if (_17_0_2_2_edc0357_1357846887119_879758_20627_exists == null) _17_0_2_2_edc0357_1357846887119_879758_20627_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887119_879758_20627_exists", (Boolean) false, this);
                    if (myDeciderID_decider_17_0_2_2_edc0357_1357846887119_879758_20627 == null) myDeciderID_decider_17_0_2_2_edc0357_1357846887119_879758_20627 = new IntegerParameter("myDeciderID_decider_17_0_2_2_edc0357_1357846887119_879758_20627", (Integer) 1, this);
                    Object effect25VarV = sig_17_0_2_2_edc0357_1357846887122_680089_20640;
                    effect25Var = new Parameter("effect25Var", null, null, this);
                    addDependency(effect25Var, new Expression(effect25VarV));
                    effect25 = new EffectFunction(new EffectFunction(effect25Var, ClassUtils.getMethodForArgTypes("ObjectFlow<LADWP>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { _17_0_2_2_edc0357_1357846887515_657207_21578, endTime }));
                    Object effect26VarV = decider_17_0_2_2_edc0357_1357846887119_879758_20627;
                    effect26Var = new Parameter("effect26Var", null, null, this);
                    addDependency(effect26Var, new Expression(effect26VarV));
                    effect26 = new EffectFunction(new EffectFunction(effect26Var, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "addIfNotContained", gov.nasa.jpl.ae.event.Parameter.class, java.lang.Object.class, java.lang.Boolean.class), new Object[] { endTime, myDeciderID_decider_17_0_2_2_edc0357_1357846887119_879758_20627, addToDecider__17_0_2_2_edc0357_1357846887119_879758_20627 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846887119_132697_20626Collections() {
                parameters.add(_17_0_2_2_edc0357_1357846887515_657207_21578);
                parameters.add(addToDecider__17_0_2_2_edc0357_1357846887119_879758_20627);
                parameters.add(_17_0_2_2_edc0357_1357846887516_607187_21579);
                parameters.add(_17_0_2_2_edc0357_1357846887119_879758_20627_exists);
                parameters.add(myDeciderID_decider_17_0_2_2_edc0357_1357846887119_879758_20627);
                Set<Effect> effectsForeffect25Var = new TreeSet<Effect>();
                effectsForeffect25Var.add(effect25);
                addEffects((Parameter<?>) effect25Var, effectsForeffect25Var);
                Set<Effect> effectsForeffect26Var = new TreeSet<Effect>();
                effectsForeffect26Var.add(effect26);
                addEffects((Parameter<?>) effect26Var, effectsForeffect26Var);
            }

            public void init_17_0_2_2_edc0357_1357846887119_132697_20626Dependencies() {
                addDependency(_17_0_2_2_edc0357_1357846887515_657207_21578, new Expression<LADWP>(new FunctionCall(_17_0_2_2_edc0357_1357846887516_607187_21579, Parameter.class, "getMember", new Object[] { "l" })));
                addDependency(addToDecider__17_0_2_2_edc0357_1357846887119_879758_20627, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887121_272221_20629_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887121_272221_20629_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(_17_0_2_2_edc0357_1357846887516_607187_21579, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887122_115492_20639, ClassUtils.getMethodForArgTypes("ObjectFlow<Power_System>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_2_edc0357_1357846887119_879758_20627_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887121_272221_20629_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887121_272221_20629_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))), new Expression<Boolean>(new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887119_879758_20627, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "size", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887119_879758_20627, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "maxSize"), new Object[] {})))), new Expression<Boolean>(new Functions.Equals(new Expression(new EffectFunction(decider_17_0_2_2_edc0357_1357846887119_879758_20627, ClassUtils.getMethodForArgTypes("TimeVaryingList<Integer>", "PowerSystem3", "lastElement", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(endTime) })), new Expression<Integer>(myDeciderID_decider_17_0_2_2_edc0357_1357846887119_879758_20627)))))))));
                addDependency(myDeciderID_decider_17_0_2_2_edc0357_1357846887119_879758_20627, new Expression<Integer>(1));
            }

            public void init_17_0_2_2_edc0357_1357846887119_132697_20626Elaborations() {
                init_17_0_2_2_edc0357_1357846887119_132697_20626Dependencies();
                Expression<?>[] arguments27 = new Expression<?>[1];
                arguments27[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition27 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887119_879758_20627_exists);
                elaborationRule27 = addElaborationRule(condition27, _17_0_2_2_edc0357_1357846886615_521269_19920.this, Power_System._17_0_2_2_edc0357_1357846886615_521269_19920._17_0_2_2_edc0357_1357846887119_879758_20627.class, "sobl_StartObjectBehaviorAction_start_system", arguments27);
            }
        }

        public class _17_0_2_2_edc0357_1357846887119_879758_20627 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846887119_879758_20627() {
                super();
                init_17_0_2_2_edc0357_1357846887119_879758_20627Members();
                init_17_0_2_2_edc0357_1357846887119_879758_20627Collections();
                init_17_0_2_2_edc0357_1357846887119_879758_20627Elaborations();
            }

            public _17_0_2_2_edc0357_1357846887119_879758_20627(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846887119_879758_20627Members();
                init_17_0_2_2_edc0357_1357846887119_879758_20627Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846887119_879758_20627Elaborations();
                fixTimeDependencies();
            }

            public Parameter< LADWP > _17_0_2_2_edc0357_1357846887516_349394_21580 = null;

            public BooleanParameter objectToPass = null;

            public BooleanParameter _17_0_2_2_edc0357_1357846887120_383342_20628_exists = null;

            public Dependency _17_0_2_2_edc0357_1357846887516_349394_21580Dependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Dependency _17_0_2_2_edc0357_1357846887120_383342_20628_existsDependency = null;

            public Effect effect28 = null;

            public Parameter effect28Var = null;

            public ElaborationRule elaborationRule29 = null;

            public ElaborationRule elaborationRule30 = null;

            public void init_17_0_2_2_edc0357_1357846887119_879758_20627Members() {
                try {
                    if (_17_0_2_2_edc0357_1357846887516_349394_21580 == null) _17_0_2_2_edc0357_1357846887516_349394_21580 = new Parameter<LADWP>("_17_0_2_2_edc0357_1357846887516_349394_21580", null, (LADWP) null, this);
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    if (_17_0_2_2_edc0357_1357846887120_383342_20628_exists == null) _17_0_2_2_edc0357_1357846887120_383342_20628_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887120_383342_20628_exists", (Boolean) false, this);
                    Object effect28VarV = sig_17_0_2_2_edc0357_1357846887122_254883_20642;
                    effect28Var = new Parameter("effect28Var", null, null, this);
                    addDependency(effect28Var, new Expression(effect28VarV));
                    effect28 = new EffectFunction(new EffectFunction(effect28Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846887119_879758_20627Collections() {
                parameters.add(_17_0_2_2_edc0357_1357846887516_349394_21580);
                parameters.add(objectToPass);
                parameters.add(_17_0_2_2_edc0357_1357846887120_383342_20628_exists);
                Set<Effect> effectsForeffect28Var = new TreeSet<Effect>();
                effectsForeffect28Var.add(effect28);
                addEffects((Parameter<?>) effect28Var, effectsForeffect28Var);
            }

            public void init_17_0_2_2_edc0357_1357846887119_879758_20627Dependencies() {
                addDependency(_17_0_2_2_edc0357_1357846887516_349394_21580, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887122_680089_20640, ClassUtils.getMethodForArgTypes("ObjectFlow<LADWP>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887122_124309_20641, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(_17_0_2_2_edc0357_1357846887120_383342_20628_exists, new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Expression<Boolean>(new Functions.Or(new Expression<Boolean>(new Functions.Equals(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887121_272221_20629_exists), null)), new Expression<Boolean>(new Functions.Not(new Expression<Boolean>(_17_0_2_2_edc0357_1357846887121_272221_20629_exists)))))), new Expression<Boolean>(new Functions.Less(new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))), new Expression<Integer>(finalNode_startTime)))))));
            }

            public void init_17_0_2_2_edc0357_1357846887119_879758_20627Elaborations() {
                init_17_0_2_2_edc0357_1357846887119_879758_20627Dependencies();
                Expression<?>[] arguments29 = new Expression<?>[1];
                arguments29[0] = new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2)));
                Expression<Boolean> condition29 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887120_383342_20628_exists);
                elaborationRule29 = addElaborationRule(condition29, _17_0_2_2_edc0357_1357846886615_521269_19920.this, Power_System._17_0_2_2_edc0357_1357846886615_521269_19920._17_0_2_2_edc0357_1357846887120_383342_20628.class, "Sys_timer_AcceptEventAction_start_system", arguments29);
                Expression<?>[] arguments30 = new Expression<?>[1];
                arguments30[0] = new Expression<Integer>(startTime);
                Expression<Boolean> condition30 = new Expression<Boolean>(true);
                elaborationRule30 = addElaborationRule(condition30, l, LADWP._17_0_2_2_edc0357_1357846886631_482214_19942.class, "LADWP_CB_Activity_LADWP", arguments30);
            }
        }

        public class _17_0_2_2_edc0357_1357846887120_383342_20628 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846887120_383342_20628() {
                super();
                init_17_0_2_2_edc0357_1357846887120_383342_20628Members();
                init_17_0_2_2_edc0357_1357846887120_383342_20628Collections();
                init_17_0_2_2_edc0357_1357846887120_383342_20628Elaborations();
            }

            public _17_0_2_2_edc0357_1357846887120_383342_20628(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846887120_383342_20628Members();
                init_17_0_2_2_edc0357_1357846887120_383342_20628Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846887120_383342_20628Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter objectToPass = null;

            public Dependency finalNode_startTimeDependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Effect effect31 = null;

            public Parameter effect31Var = null;

            public void init_17_0_2_2_edc0357_1357846887120_383342_20628Members() {
                try {
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                    Object effect31VarV = sig_17_0_2_2_edc0357_1357846887123_606358_20643;
                    effect31Var = new Parameter("effect31Var", null, null, this);
                    addDependency(effect31Var, new Expression(effect31VarV));
                    effect31 = new EffectFunction(new EffectFunction(effect31Var, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "send", java.lang.Object.class, gov.nasa.jpl.ae.event.Parameter.class), new Object[] { objectToPass, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846887120_383342_20628Collections() {
                parameters.add(objectToPass);
                Set<Effect> effectsForeffect31Var = new TreeSet<Effect>();
                effectsForeffect31Var.add(effect31);
                addEffects((Parameter<?>) effect31Var, effectsForeffect31Var);
            }

            public void init_17_0_2_2_edc0357_1357846887120_383342_20628Dependencies() {
                addDependency(finalNode_startTime, new Expression<Integer>(new Functions.Plus(new Expression<Integer>(endTime), new Expression<Integer>(2))));
                addDependency(duration, new Expression<Integer>(36000));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887122_254883_20642, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
            }

            public void init_17_0_2_2_edc0357_1357846887120_383342_20628Elaborations() {
                init_17_0_2_2_edc0357_1357846887120_383342_20628Dependencies();
            }
        }

        public class _17_0_2_2_edc0357_1357846887121_272221_20629 extends DurativeEvent {

            public _17_0_2_2_edc0357_1357846887121_272221_20629() {
                super();
                init_17_0_2_2_edc0357_1357846887121_272221_20629Members();
                init_17_0_2_2_edc0357_1357846887121_272221_20629Collections();
                init_17_0_2_2_edc0357_1357846887121_272221_20629Elaborations();
            }

            public _17_0_2_2_edc0357_1357846887121_272221_20629(Expression<Integer> startTime) {
                super();
                init_17_0_2_2_edc0357_1357846887121_272221_20629Members();
                init_17_0_2_2_edc0357_1357846887121_272221_20629Collections();
                addDependency(this.startTime, startTime);
                init_17_0_2_2_edc0357_1357846887121_272221_20629Elaborations();
                fixTimeDependencies();
            }

            public BooleanParameter objectToPass = null;

            public Dependency endTimeDependency = null;

            public Dependency durationDependency = null;

            public Dependency objectToPassDependency = null;

            public Dependency finalNode_endTimeDependency = null;

            public void init_17_0_2_2_edc0357_1357846887121_272221_20629Members() {
                try {
                    if (objectToPass == null) objectToPass = new BooleanParameter("objectToPass", (Boolean) null, this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_2_2_edc0357_1357846887121_272221_20629Collections() {
                parameters.add(objectToPass);
            }

            public void init_17_0_2_2_edc0357_1357846887121_272221_20629Dependencies() {
                addDependency(endTime, new Expression<Integer>(new Functions.Plus(new Expression<Integer>(startTime), new Expression<Integer>(duration))));
                addDependency(duration, new Expression<Integer>(1));
                addDependency(objectToPass, new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887123_606358_20643, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "receive", gov.nasa.jpl.ae.event.Parameter.class), new Object[] { new Expression<Integer>(startTime) })));
                addDependency(finalNode_endTime, new Expression<Integer>(endTime));
            }

            public void init_17_0_2_2_edc0357_1357846887121_272221_20629Elaborations() {
                init_17_0_2_2_edc0357_1357846887121_272221_20629Dependencies();
            }
        }

        public _17_0_2_2_edc0357_1357846886615_521269_19920(Expression<DurativeEvent> caller, Expression<Integer> startTime) {
            super();
            init_17_0_2_2_edc0357_1357846886615_521269_19920Members();
            init_17_0_2_2_edc0357_1357846886615_521269_19920Collections();
            addDependency(this.caller, caller);
            addDependency(this.startTime, startTime);
            init_17_0_2_2_edc0357_1357846886615_521269_19920Elaborations();
            fixTimeDependencies();
        }

        public Parameter< TimeVaryingList<Integer> > decider_17_0_2_2_edc0357_1357846887118_697774_20625 = null;

        public Parameter< ObjectFlow<Power_System> > sig_17_0_2_2_edc0357_1357846887122_3334_20635 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_2_edc0357_1357846887121_57958_20632 = null;

        public BooleanParameter _17_0_2_2_edc0357_1357846887121_272221_20629_exists = null;

        public Parameter< DurativeEvent > caller = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_2_edc0357_1357846887122_87910_20638 = null;

        public Parameter< TimeVaryingList<Integer> > decider_17_0_2_2_edc0357_1357846887119_879758_20627 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_2_edc0357_1357846887123_606358_20643 = null;

        public Parameter< ObjectFlow<Power_System> > sig_17_0_2_2_edc0357_1357846887121_127072_20633 = null;

        public Parameter< ObjectFlow<Power> > sig_17_0_2_2_edc0357_1357846887122_201381_20637 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_2_edc0357_1357846887122_124309_20641 = null;

        public IntegerParameter finalNode_startTime = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_2_2_edc0357_1357846887122_254883_20642 = null;

        public IntegerParameter finalNode_endTime = null;

        public Parameter< ObjectFlow<LADWP> > sig_17_0_2_2_edc0357_1357846887122_680089_20640 = null;

        public Parameter< ObjectFlow<Power_System> > sig_17_0_2_2_edc0357_1357846887122_115492_20639 = null;

        public Parameter< ObjectFlow<Customer> > sig_17_0_2_2_edc0357_1357846887122_214571_20636 = null;

        public Parameter< ObjectFlow<Power_System> > sig_17_0_2_2_edc0357_1357846887122_499839_20634 = null;

        public Dependency caller_endTimeDependency = null;

        public Dependency _17_0_2_2_edc0357_1357846887121_272221_20629_existsDependency = null;

        public Dependency endTimeDependency = null;

        public ElaborationRule elaborationRule0 = null;

        public ElaborationRule elaborationRule1 = null;

        public void init_17_0_2_2_edc0357_1357846886615_521269_19920Members() {
            try {
                if (decider_17_0_2_2_edc0357_1357846887118_697774_20625 == null) decider_17_0_2_2_edc0357_1357846887118_697774_20625 = new Parameter<TimeVaryingList<Integer>>("decider_17_0_2_2_edc0357_1357846887118_697774_20625", null, (TimeVaryingList<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingList.class, java.lang.String.class, int.class), new Object[] { "decider_17_0_2_2_edc0357_1357846887118_697774_20625", 2 })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846887122_3334_20635 == null) sig_17_0_2_2_edc0357_1357846887122_3334_20635 = new Parameter<ObjectFlow<Power_System>>("sig_17_0_2_2_edc0357_1357846887122_3334_20635", null, (ObjectFlow<Power_System>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887122_3334_20635" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846887121_57958_20632 == null) sig_17_0_2_2_edc0357_1357846887121_57958_20632 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_2_edc0357_1357846887121_57958_20632", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887121_57958_20632" })).evaluate(true), this);
                if (_17_0_2_2_edc0357_1357846887121_272221_20629_exists == null) _17_0_2_2_edc0357_1357846887121_272221_20629_exists = new BooleanParameter("_17_0_2_2_edc0357_1357846887121_272221_20629_exists", (Boolean) false, this);
                if (caller == null) caller = new Parameter<DurativeEvent>("caller", null, (DurativeEvent) null, this);
                if (sig_17_0_2_2_edc0357_1357846887122_87910_20638 == null) sig_17_0_2_2_edc0357_1357846887122_87910_20638 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_2_edc0357_1357846887122_87910_20638", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887122_87910_20638" })).evaluate(true), this);
                if (decider_17_0_2_2_edc0357_1357846887119_879758_20627 == null) decider_17_0_2_2_edc0357_1357846887119_879758_20627 = new Parameter<TimeVaryingList<Integer>>("decider_17_0_2_2_edc0357_1357846887119_879758_20627", null, (TimeVaryingList<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingList.class, java.lang.String.class, int.class), new Object[] { "decider_17_0_2_2_edc0357_1357846887119_879758_20627", 2 })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846887123_606358_20643 == null) sig_17_0_2_2_edc0357_1357846887123_606358_20643 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_2_edc0357_1357846887123_606358_20643", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887123_606358_20643" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846887121_127072_20633 == null) sig_17_0_2_2_edc0357_1357846887121_127072_20633 = new Parameter<ObjectFlow<Power_System>>("sig_17_0_2_2_edc0357_1357846887121_127072_20633", null, (ObjectFlow<Power_System>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887121_127072_20633" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846887122_201381_20637 == null) sig_17_0_2_2_edc0357_1357846887122_201381_20637 = new Parameter<ObjectFlow<Power>>("sig_17_0_2_2_edc0357_1357846887122_201381_20637", null, (ObjectFlow<Power>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887122_201381_20637" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846887122_124309_20641 == null) sig_17_0_2_2_edc0357_1357846887122_124309_20641 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_2_edc0357_1357846887122_124309_20641", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887122_124309_20641" })).evaluate(true), this);
                if (finalNode_startTime == null) finalNode_startTime = new IntegerParameter("finalNode_startTime", (Integer) null, this);
                if (sig_17_0_2_2_edc0357_1357846887122_254883_20642 == null) sig_17_0_2_2_edc0357_1357846887122_254883_20642 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_2_2_edc0357_1357846887122_254883_20642", null, (ObjectFlow<Boolean>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887122_254883_20642" })).evaluate(true), this);
                if (finalNode_endTime == null) finalNode_endTime = new IntegerParameter("finalNode_endTime", (Integer) null, this);
                if (sig_17_0_2_2_edc0357_1357846887122_680089_20640 == null) sig_17_0_2_2_edc0357_1357846887122_680089_20640 = new Parameter<ObjectFlow<LADWP>>("sig_17_0_2_2_edc0357_1357846887122_680089_20640", null, (ObjectFlow<LADWP>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887122_680089_20640" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846887122_115492_20639 == null) sig_17_0_2_2_edc0357_1357846887122_115492_20639 = new Parameter<ObjectFlow<Power_System>>("sig_17_0_2_2_edc0357_1357846887122_115492_20639", null, (ObjectFlow<Power_System>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887122_115492_20639" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846887122_214571_20636 == null) sig_17_0_2_2_edc0357_1357846887122_214571_20636 = new Parameter<ObjectFlow<Customer>>("sig_17_0_2_2_edc0357_1357846887122_214571_20636", null, (ObjectFlow<Customer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887122_214571_20636" })).evaluate(true), this);
                if (sig_17_0_2_2_edc0357_1357846887122_499839_20634 == null) sig_17_0_2_2_edc0357_1357846887122_499839_20634 = new Parameter<ObjectFlow<Power_System>>("sig_17_0_2_2_edc0357_1357846887122_499839_20634", null, (ObjectFlow<Power_System>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class), new Object[] { "sig_17_0_2_2_edc0357_1357846887122_499839_20634" })).evaluate(true), this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void init_17_0_2_2_edc0357_1357846886615_521269_19920Collections() {
            parameters.add(decider_17_0_2_2_edc0357_1357846887118_697774_20625);
            parameters.add(sig_17_0_2_2_edc0357_1357846887122_3334_20635);
            parameters.add(sig_17_0_2_2_edc0357_1357846887121_57958_20632);
            parameters.add(_17_0_2_2_edc0357_1357846887121_272221_20629_exists);
            parameters.add(caller);
            parameters.add(sig_17_0_2_2_edc0357_1357846887122_87910_20638);
            parameters.add(decider_17_0_2_2_edc0357_1357846887119_879758_20627);
            parameters.add(sig_17_0_2_2_edc0357_1357846887123_606358_20643);
            parameters.add(sig_17_0_2_2_edc0357_1357846887121_127072_20633);
            parameters.add(sig_17_0_2_2_edc0357_1357846887122_201381_20637);
            parameters.add(sig_17_0_2_2_edc0357_1357846887122_124309_20641);
            parameters.add(finalNode_startTime);
            parameters.add(sig_17_0_2_2_edc0357_1357846887122_254883_20642);
            parameters.add(finalNode_endTime);
            parameters.add(sig_17_0_2_2_edc0357_1357846887122_680089_20640);
            parameters.add(sig_17_0_2_2_edc0357_1357846887122_115492_20639);
            parameters.add(sig_17_0_2_2_edc0357_1357846887122_214571_20636);
            parameters.add(sig_17_0_2_2_edc0357_1357846887122_499839_20634);
        }

        public void init_17_0_2_2_edc0357_1357846886615_521269_19920Dependencies() {
            caller.getValue(true).addDependency(caller.getValue(true).endTime, new Expression<Integer>(finalNode_endTime));
            addDependency(_17_0_2_2_edc0357_1357846887121_272221_20629_exists, new Expression<Boolean>(new Functions.And(new Expression<Boolean>(new Functions.NotEquals(new Expression<Integer>(finalNode_startTime), null)), new Expression(new EffectFunction(sig_17_0_2_2_edc0357_1357846887123_606358_20643, ClassUtils.getMethodForArgTypes("ObjectFlow<Boolean>", "PowerSystem3", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(new Functions.Minus(new Expression<Integer>(finalNode_startTime), new Expression<Integer>(1))) })))));
            addDependency(endTime, new Expression<Integer>(finalNode_endTime));
        }

        public void init_17_0_2_2_edc0357_1357846886615_521269_19920Elaborations() {
            init_17_0_2_2_edc0357_1357846886615_521269_19920Dependencies();
            Expression<?>[] arguments0 = new Expression<?>[1];
            arguments0[0] = new Expression<Integer>(finalNode_startTime);
            Expression<Boolean> condition0 = new Expression<Boolean>(_17_0_2_2_edc0357_1357846887121_272221_20629_exists);
            elaborationRule0 = addElaborationRule(condition0, _17_0_2_2_edc0357_1357846886615_521269_19920.this, Power_System._17_0_2_2_edc0357_1357846886615_521269_19920._17_0_2_2_edc0357_1357846887121_272221_20629.class, "Sys_end_ActivityFinalNode_start_system", arguments0);
            Expression<?>[] arguments1 = new Expression<?>[1];
            arguments1[0] = new Expression<Integer>(startTime);
            Expression<Boolean> condition1 = new Expression<Boolean>(true);
            elaborationRule1 = addElaborationRule(condition1, _17_0_2_2_edc0357_1357846886615_521269_19920.this, Power_System._17_0_2_2_edc0357_1357846886615_521269_19920._17_0_2_2_edc0357_1357846887115_291670_20619.class, "Sys_init_InitialNode_start_system", arguments1);
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
            load__17_0_2_2_edc0357_1357846886587_742419_19875.getValue().setValue(t, x);
        }

        public Parameter< TimeVaryingMap<Integer> > load__17_0_2_2_edc0357_1357846886587_742419_19875 = null;

        public void initSignalchangeLoadValueMembers() {
            try {
                if (load__17_0_2_2_edc0357_1357846886587_742419_19875 == null) load__17_0_2_2_edc0357_1357846886587_742419_19875 = new Parameter<TimeVaryingMap<Integer>>("load__17_0_2_2_edc0357_1357846886587_742419_19875", null, (TimeVaryingMap<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingMap.class, java.lang.String.class, java.lang.String.class, java.lang.Class.class), new Object[] { "load", null, Integer.class })).evaluate(true), this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void initSignalchangeLoadValueCollections() {
            parameters.add(load__17_0_2_2_edc0357_1357846886587_742419_19875);
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
            newGenValue__17_0_2_2_edc0357_1357846886586_181042_19874.getValue().setValue(t, x);
        }

        public Parameter< TimeVaryingMap<Integer> > newGenValue__17_0_2_2_edc0357_1357846886586_181042_19874 = null;

        public void initSignalchangeGenerationValueMembers() {
            try {
                if (newGenValue__17_0_2_2_edc0357_1357846886586_181042_19874 == null) newGenValue__17_0_2_2_edc0357_1357846886586_181042_19874 = new Parameter<TimeVaryingMap<Integer>>("newGenValue__17_0_2_2_edc0357_1357846886586_181042_19874", null, (TimeVaryingMap<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingMap.class, java.lang.String.class, java.lang.String.class, java.lang.Class.class), new Object[] { "newGenValue", null, Integer.class })).evaluate(true), this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void initSignalchangeGenerationValueCollections() {
            parameters.add(newGenValue__17_0_2_2_edc0357_1357846886586_181042_19874);
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
            cap__17_0_2_2_edc0357_1357846886652_171397_19963.getValue().setValue(t, x);
        }

        public Parameter< TimeVaryingMap<Integer> > cap__17_0_2_2_edc0357_1357846886652_171397_19963 = null;

        public void initSignaldr_requestMembers() {
            try {
                if (cap__17_0_2_2_edc0357_1357846886652_171397_19963 == null) cap__17_0_2_2_edc0357_1357846886652_171397_19963 = new Parameter<TimeVaryingMap<Integer>>("cap__17_0_2_2_edc0357_1357846886652_171397_19963", null, (TimeVaryingMap<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingMap.class, java.lang.String.class, java.lang.String.class, java.lang.Class.class), new Object[] { "cap", null, Integer.class })).evaluate(true), this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void initSignaldr_requestCollections() {
            parameters.add(cap__17_0_2_2_edc0357_1357846886652_171397_19963);
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
            newLoad__17_0_2_2_edc0357_1357846886652_849760_19964.getValue().setValue(t, x);
        }

        public Parameter< TimeVaryingMap<Integer> > newLoad__17_0_2_2_edc0357_1357846886652_849760_19964 = null;

        public void initSignalyesMembers() {
            try {
                if (newLoad__17_0_2_2_edc0357_1357846886652_849760_19964 == null) newLoad__17_0_2_2_edc0357_1357846886652_849760_19964 = new Parameter<TimeVaryingMap<Integer>>("newLoad__17_0_2_2_edc0357_1357846886652_849760_19964", null, (TimeVaryingMap<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingMap.class, java.lang.String.class, java.lang.String.class, java.lang.Class.class), new Object[] { "newLoad", null, Integer.class })).evaluate(true), this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void initSignalyesCollections() {
            parameters.add(newLoad__17_0_2_2_edc0357_1357846886652_849760_19964);
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
            meter_value__17_0_2_2_edc0357_1357846886650_242616_19960.getValue().setValue(t, x);
        }

        public Parameter< TimeVaryingMap<Integer> > meter_value__17_0_2_2_edc0357_1357846886650_242616_19960 = null;

        public void initSignalreceiveMeterReadingMembers() {
            try {
                if (meter_value__17_0_2_2_edc0357_1357846886650_242616_19960 == null) meter_value__17_0_2_2_edc0357_1357846886650_242616_19960 = new Parameter<TimeVaryingMap<Integer>>("meter_value__17_0_2_2_edc0357_1357846886650_242616_19960", null, (TimeVaryingMap<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingMap.class, java.lang.String.class, java.lang.String.class, java.lang.Class.class), new Object[] { "meter_value", null, Integer.class })).evaluate(true), this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void initSignalreceiveMeterReadingCollections() {
            parameters.add(meter_value__17_0_2_2_edc0357_1357846886650_242616_19960);
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
            actual_load__17_0_2_2_edc0357_1357846886651_371591_19961.getValue().setValue(t, x);
        }

        public Parameter< TimeVaryingMap<Integer> > actual_load__17_0_2_2_edc0357_1357846886651_371591_19961 = null;

        public void initSignalreceiveLoadReadingMembers() {
            try {
                if (actual_load__17_0_2_2_edc0357_1357846886651_371591_19961 == null) actual_load__17_0_2_2_edc0357_1357846886651_371591_19961 = new Parameter<TimeVaryingMap<Integer>>("actual_load__17_0_2_2_edc0357_1357846886651_371591_19961", null, (TimeVaryingMap<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingMap.class, java.lang.String.class, java.lang.String.class, java.lang.Class.class), new Object[] { "actual_load", null, Integer.class })).evaluate(true), this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void initSignalreceiveLoadReadingCollections() {
            parameters.add(actual_load__17_0_2_2_edc0357_1357846886651_371591_19961);
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
            actual_power__17_0_2_2_edc0357_1357846886651_479103_19962.getValue().setValue(t, x);
        }

        public Parameter< TimeVaryingMap<Integer> > actual_power__17_0_2_2_edc0357_1357846886651_479103_19962 = null;

        public void initSignalreceiveGenReadingMembers() {
            try {
                if (actual_power__17_0_2_2_edc0357_1357846886651_479103_19962 == null) actual_power__17_0_2_2_edc0357_1357846886651_479103_19962 = new Parameter<TimeVaryingMap<Integer>>("actual_power__17_0_2_2_edc0357_1357846886651_479103_19962", null, (TimeVaryingMap<Integer>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(TimeVaryingMap.class, java.lang.String.class, java.lang.String.class, java.lang.Class.class), new Object[] { "actual_power", null, Integer.class })).evaluate(true), this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void initSignalreceiveGenReadingCollections() {
            parameters.add(actual_power__17_0_2_2_edc0357_1357846886651_479103_19962);
        }

        public void initSignalreceiveGenReadingDependencies() {
        }

        public void initSignalreceiveGenReadingElaborations() {
            initSignalreceiveGenReadingDependencies();
        }
    }

    public Power_System() {
        super();
        initPower_SystemMembers();
        initPower_SystemCollections();
        initPower_SystemElaborations();
        ((ObjectFlow) ss_17_0_2_2_edc0357_1357846886595_931685_19886_changeLoadValue.getValue()).addListener(((ObjectFlow) (p.getValue()).q_Power_changeLoadValue.getValue()));
        ((ObjectFlow) ss_17_0_2_2_edc0357_1357846886643_772919_19947_changeGenerationValue.getValue()).addListener(((ObjectFlow) (p.getValue()).q_Power_changeGenerationValue.getValue()));
        ((ObjectFlow) ss_17_0_2_2_edc0357_1357846886639_657720_19944_dr_request.getValue()).addListener(((ObjectFlow) (c.getValue()).q_Customer_dr_request.getValue()));
        ((ObjectFlow) ss_17_0_2_2_edc0357_1357846886597_47094_19888_yes.getValue()).addListener(((ObjectFlow) (l.getValue()).q_LADWP_yes.getValue()));
        ((ObjectFlow) ss_17_0_2_2_edc0357_1357846886597_47094_19888_no.getValue()).addListener(((ObjectFlow) (l.getValue()).q_LADWP_no.getValue()));
        ((ObjectFlow) ss_17_0_2_2_edc0357_1357846886597_585102_19889_receiveMeterReading.getValue()).addListener(((ObjectFlow) (l.getValue()).q_LADWP_receiveMeterReading.getValue()));
        ((ObjectFlow) ss_17_0_2_2_edc0357_1357846886585_941053_19872_receiveLoadReading.getValue()).addListener(((ObjectFlow) (l.getValue()).q_LADWP_receiveLoadReading.getValue()));
        ((ObjectFlow) ss_17_0_2_2_edc0357_1357846886585_816142_19873_receiveGenReading.getValue()).addListener(((ObjectFlow) (l.getValue()).q_LADWP_receiveGenReading.getValue()));
    }

    public Parameter< ObjectFlow<Power_System.SignalchangeLoadValue> > ss_17_0_2_2_edc0357_1357846886595_931685_19886_changeLoadValue = null;

    public Parameter< ObjectFlow<Power_System.SignalreceiveLoadReading> > ss_17_0_2_2_edc0357_1357846886585_941053_19872_receiveLoadReading = null;

    public StringParameter classifierBehavior = null;

    public Parameter< ObjectFlow<Power_System.Signalyes> > ss_17_0_2_2_edc0357_1357846886597_47094_19888_yes = null;

    public Parameter< ObjectFlow<Power_System.Signaldr_request> > ss_17_0_2_2_edc0357_1357846886639_657720_19944_dr_request = null;

    public Parameter< Customer > c = null;

    public Parameter< ObjectFlow<Power_System.Signalno> > ss_17_0_2_2_edc0357_1357846886597_47094_19888_no = null;

    public Parameter< LADWP > l = null;

    public Parameter< ObjectFlow<Power_System.SignalreceiveMeterReading> > ss_17_0_2_2_edc0357_1357846886597_585102_19889_receiveMeterReading = null;

    public Parameter< ObjectFlow<Power_System.SignalreceiveGenReading> > ss_17_0_2_2_edc0357_1357846886585_816142_19873_receiveGenReading = null;

    public Parameter< Power > p = null;

    public Parameter< ObjectFlow<Power_System.SignalchangeGenerationValue> > ss_17_0_2_2_edc0357_1357846886643_772919_19947_changeGenerationValue = null;

    public void initPower_SystemMembers() {
        try {
            if (ss_17_0_2_2_edc0357_1357846886595_931685_19886_changeLoadValue == null) ss_17_0_2_2_edc0357_1357846886595_931685_19886_changeLoadValue = new Parameter<ObjectFlow<Power_System.SignalchangeLoadValue>>("ss_17_0_2_2_edc0357_1357846886595_931685_19886_changeLoadValue", null, (ObjectFlow<Power_System.SignalchangeLoadValue>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class, java.lang.Class.class), new Object[] { "ss_17_0_2_2_edc0357_1357846886595_931685_19886_changeLoadValue", Power_System.SignalchangeLoadValue.class })).evaluate(true), this);
            if (ss_17_0_2_2_edc0357_1357846886585_941053_19872_receiveLoadReading == null) ss_17_0_2_2_edc0357_1357846886585_941053_19872_receiveLoadReading = new Parameter<ObjectFlow<Power_System.SignalreceiveLoadReading>>("ss_17_0_2_2_edc0357_1357846886585_941053_19872_receiveLoadReading", null, (ObjectFlow<Power_System.SignalreceiveLoadReading>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class, java.lang.Class.class), new Object[] { "ss_17_0_2_2_edc0357_1357846886585_941053_19872_receiveLoadReading", Power_System.SignalreceiveLoadReading.class })).evaluate(true), this);
            if (classifierBehavior == null) classifierBehavior = new StringParameter("classifierBehavior", (String) "_17_0_2_2_edc0357_1357846886615_521269_19920", this);
            if (ss_17_0_2_2_edc0357_1357846886597_47094_19888_yes == null) ss_17_0_2_2_edc0357_1357846886597_47094_19888_yes = new Parameter<ObjectFlow<Power_System.Signalyes>>("ss_17_0_2_2_edc0357_1357846886597_47094_19888_yes", null, (ObjectFlow<Power_System.Signalyes>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class, java.lang.Class.class), new Object[] { "ss_17_0_2_2_edc0357_1357846886597_47094_19888_yes", Power_System.Signalyes.class })).evaluate(true), this);
            if (ss_17_0_2_2_edc0357_1357846886639_657720_19944_dr_request == null) ss_17_0_2_2_edc0357_1357846886639_657720_19944_dr_request = new Parameter<ObjectFlow<Power_System.Signaldr_request>>("ss_17_0_2_2_edc0357_1357846886639_657720_19944_dr_request", null, (ObjectFlow<Power_System.Signaldr_request>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class, java.lang.Class.class), new Object[] { "ss_17_0_2_2_edc0357_1357846886639_657720_19944_dr_request", Power_System.Signaldr_request.class })).evaluate(true), this);
            if (c == null) c = new Parameter<Customer>("c", null, (Customer) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(Customer.class, Power_System.class), new Object[] { this })).evaluate(true), this);
            if (ss_17_0_2_2_edc0357_1357846886597_47094_19888_no == null) ss_17_0_2_2_edc0357_1357846886597_47094_19888_no = new Parameter<ObjectFlow<Power_System.Signalno>>("ss_17_0_2_2_edc0357_1357846886597_47094_19888_no", null, (ObjectFlow<Power_System.Signalno>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class, java.lang.Class.class), new Object[] { "ss_17_0_2_2_edc0357_1357846886597_47094_19888_no", Power_System.Signalno.class })).evaluate(true), this);
            if (l == null) l = new Parameter<LADWP>("l", null, (LADWP) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(LADWP.class, Power_System.class), new Object[] { this })).evaluate(true), this);
            if (ss_17_0_2_2_edc0357_1357846886597_585102_19889_receiveMeterReading == null) ss_17_0_2_2_edc0357_1357846886597_585102_19889_receiveMeterReading = new Parameter<ObjectFlow<Power_System.SignalreceiveMeterReading>>("ss_17_0_2_2_edc0357_1357846886597_585102_19889_receiveMeterReading", null, (ObjectFlow<Power_System.SignalreceiveMeterReading>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class, java.lang.Class.class), new Object[] { "ss_17_0_2_2_edc0357_1357846886597_585102_19889_receiveMeterReading", Power_System.SignalreceiveMeterReading.class })).evaluate(true), this);
            if (ss_17_0_2_2_edc0357_1357846886585_816142_19873_receiveGenReading == null) ss_17_0_2_2_edc0357_1357846886585_816142_19873_receiveGenReading = new Parameter<ObjectFlow<Power_System.SignalreceiveGenReading>>("ss_17_0_2_2_edc0357_1357846886585_816142_19873_receiveGenReading", null, (ObjectFlow<Power_System.SignalreceiveGenReading>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class, java.lang.Class.class), new Object[] { "ss_17_0_2_2_edc0357_1357846886585_816142_19873_receiveGenReading", Power_System.SignalreceiveGenReading.class })).evaluate(true), this);
            if (p == null) p = new Parameter<Power>("p", null, (Power) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(Power.class, Power_System.class), new Object[] { this })).evaluate(true), this);
            if (ss_17_0_2_2_edc0357_1357846886643_772919_19947_changeGenerationValue == null) ss_17_0_2_2_edc0357_1357846886643_772919_19947_changeGenerationValue = new Parameter<ObjectFlow<Power_System.SignalchangeGenerationValue>>("ss_17_0_2_2_edc0357_1357846886643_772919_19947_changeGenerationValue", null, (ObjectFlow<Power_System.SignalchangeGenerationValue>) (new ConstructorCall(null, ClassUtils.getConstructorForArgTypes(ObjectFlow.class, java.lang.String.class, java.lang.Class.class), new Object[] { "ss_17_0_2_2_edc0357_1357846886643_772919_19947_changeGenerationValue", Power_System.SignalchangeGenerationValue.class })).evaluate(true), this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void initPower_SystemCollections() {
        parameters.add(ss_17_0_2_2_edc0357_1357846886595_931685_19886_changeLoadValue);
        parameters.add(ss_17_0_2_2_edc0357_1357846886585_941053_19872_receiveLoadReading);
        parameters.add(classifierBehavior);
        parameters.add(ss_17_0_2_2_edc0357_1357846886597_47094_19888_yes);
        parameters.add(ss_17_0_2_2_edc0357_1357846886639_657720_19944_dr_request);
        parameters.add(c);
        parameters.add(ss_17_0_2_2_edc0357_1357846886597_47094_19888_no);
        parameters.add(l);
        parameters.add(ss_17_0_2_2_edc0357_1357846886597_585102_19889_receiveMeterReading);
        parameters.add(ss_17_0_2_2_edc0357_1357846886585_816142_19873_receiveGenReading);
        parameters.add(p);
        parameters.add(ss_17_0_2_2_edc0357_1357846886643_772919_19947_changeGenerationValue);
    }

    public void initPower_SystemDependencies() {
    }

    public void initPower_SystemElaborations() {
        initPower_SystemDependencies();
    }
}
