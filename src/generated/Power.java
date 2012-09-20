package generated;

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
import gov.nasa.jpl.ae.util.Utils;
import java.util.Vector;
import java.util.Map;
import java.util.Queue;
import java.util.LinkedList;
import gov.nasa.jpl.ae.event.Timepoint;
import gov.nasa.jpl.ae.fuml.ObjectFlow;
import java.util.Set;
import java.util.HashSet;
import gov.nasa.jpl.ae.event.EffectFunction;
import java.util.Set;
import java.util.HashSet;
import gov.nasa.jpl.ae.event.EffectFunction;
import java.util.Set;
import java.util.HashSet;
import gov.nasa.jpl.ae.event.EffectFunction;
import java.util.Set;
import java.util.HashSet;
import gov.nasa.jpl.ae.event.EffectFunction;
import java.util.Set;
import java.util.HashSet;
import gov.nasa.jpl.ae.event.EffectFunction;
import java.util.Set;
import java.util.HashSet;
import gov.nasa.jpl.ae.event.EffectFunction;
import java.util.Set;
import java.util.HashSet;
import gov.nasa.jpl.ae.event.EffectFunction;
import java.util.Set;
import java.util.HashSet;
import gov.nasa.jpl.ae.event.EffectFunction;
import java.util.Set;
import java.util.HashSet;
import gov.nasa.jpl.ae.event.EffectFunction;
import java.util.Set;
import java.util.HashSet;
import gov.nasa.jpl.ae.event.EffectFunction;
import java.util.Set;
import java.util.HashSet;
import gov.nasa.jpl.ae.event.EffectFunction;
import java.util.Set;
import java.util.HashSet;
import gov.nasa.jpl.ae.event.EffectFunction;
import java.util.Set;
import java.util.HashSet;
import gov.nasa.jpl.ae.event.EffectFunction;
import java.util.Set;
import java.util.HashSet;
import gov.nasa.jpl.ae.event.EffectFunction;
import java.util.Set;
import java.util.HashSet;
import gov.nasa.jpl.ae.event.EffectFunction;
import java.util.Queue;
import java.util.LinkedList;
import gov.nasa.jpl.ae.event.Timepoint;
import gov.nasa.jpl.ae.fuml.ObjectFlow;
import java.util.Queue;
import java.util.LinkedList;
import gov.nasa.jpl.ae.event.Timepoint;
import gov.nasa.jpl.ae.fuml.ObjectFlow;
import java.util.Queue;
import java.util.LinkedList;
import gov.nasa.jpl.ae.event.Timepoint;
import gov.nasa.jpl.ae.fuml.ObjectFlow;
import java.util.Queue;
import java.util.LinkedList;
import gov.nasa.jpl.ae.event.Timepoint;
import gov.nasa.jpl.ae.fuml.ObjectFlow;

public class Power extends ParameterListenerImpl {

    public StringParameter classifierBehavior = null;

    public Parameter q_Power_receiveGenReading = null;

    public Parameter power__17_0_5_edc0357_1345510113545_356064_13763 = null;

    public Parameter q_Power_changeGenerationValue = null;

    public Power_System x = null;

    public Parameter load__17_0_5_edc0357_1345510113546_825610_13764 = null;

    public Parameter q_Power_receiveLoadReading = null;

    public Parameter q_Power_changeLoadValue = null;

    public void initPowerMembers() {
        try {
            classifierBehavior = new StringParameter("classifierBehavior", this);
            q_Power_receiveGenReading = new Parameter("q_Power_receiveGenReading", null, new ObjectFlow("q_Power_receiveGenReading", Power_System.SignalreceiveGenReading.class), this);
            power__17_0_5_edc0357_1345510113545_356064_13763 = new Parameter("power__17_0_5_edc0357_1345510113545_356064_13763", null, new TimeVaryingMap("power"), this);
            q_Power_changeGenerationValue = new Parameter("q_Power_changeGenerationValue", null, new ObjectFlow("q_Power_changeGenerationValue", Power_System.SignalchangeGenerationValue.class), this);
            //x = new Parameter("x", null, null, this);
            load__17_0_5_edc0357_1345510113546_825610_13764 = new Parameter("load__17_0_5_edc0357_1345510113546_825610_13764", null, new TimeVaryingMap("load"), this);
            q_Power_receiveLoadReading = new Parameter("q_Power_receiveLoadReading", null, new ObjectFlow("q_Power_receiveLoadReading", Power_System.SignalreceiveLoadReading.class), this);
            q_Power_changeLoadValue = new Parameter("q_Power_changeLoadValue", null, new ObjectFlow("q_Power_changeLoadValue", Power_System.SignalchangeLoadValue.class), this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void initPowerCollections() {
        parameters.add(classifierBehavior);
        parameters.add(q_Power_receiveGenReading);
        parameters.add(power__17_0_5_edc0357_1345510113545_356064_13763);
        parameters.add(q_Power_changeGenerationValue);
        //parameters.add(x);
        parameters.add(load__17_0_5_edc0357_1345510113546_825610_13764);
        parameters.add(q_Power_receiveLoadReading);
        parameters.add(q_Power_changeLoadValue);
    }

    public void initPowerElaborations() {
    }

    public Power() {
        super();
        initPowerMembers();
        initPowerCollections();
        initPowerElaborations();
    }

    public class _17_0_5_edc0357_1345510113540_247161_13759 extends DurativeEvent {

        public Parameter sig_17_0_5_edc0357_1345510113902_380027_14003 = null;

        public IntegerParameter _17_0_5_edc0357_1345756501917_658961_12894 = null;

        public Parameter sig_17_0_5_edc0357_1345510113902_184025_14005 = null;

        public Parameter sig_17_0_5_edc0357_1345510113902_964176_14006 = null;

        public IntegerParameter invoke_time = null;

        public ElaborationRule elaborationRule159 = null;

        public ElaborationRule elaborationRule160 = null;

        public void init_17_0_5_edc0357_1345510113540_247161_13759Members() {
            try {
                sig_17_0_5_edc0357_1345510113902_380027_14003 = new Parameter("sig_17_0_5_edc0357_1345510113902_380027_14003", null, new ObjectFlow("sig_17_0_5_edc0357_1345510113902_380027_14003"), this);
                _17_0_5_edc0357_1345756501917_658961_12894 = new IntegerParameter("_17_0_5_edc0357_1345756501917_658961_12894", this);
                sig_17_0_5_edc0357_1345510113902_184025_14005 = new Parameter("sig_17_0_5_edc0357_1345510113902_184025_14005", null, new ObjectFlow("sig_17_0_5_edc0357_1345510113902_184025_14005"), this);
                sig_17_0_5_edc0357_1345510113902_964176_14006 = new Parameter("sig_17_0_5_edc0357_1345510113902_964176_14006", null, new ObjectFlow("sig_17_0_5_edc0357_1345510113902_964176_14006"), this);
                invoke_time = new IntegerParameter("invoke_time", this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void init_17_0_5_edc0357_1345510113540_247161_13759Collections() {
            parameters.add(sig_17_0_5_edc0357_1345510113902_380027_14003);
            parameters.add(_17_0_5_edc0357_1345756501917_658961_12894);
            parameters.add(sig_17_0_5_edc0357_1345510113902_184025_14005);
            parameters.add(sig_17_0_5_edc0357_1345510113902_964176_14006);
            parameters.add(invoke_time);
        }

        public void init_17_0_5_edc0357_1345510113540_247161_13759Elaborations() {
            Expression<?>[] arguments159 = new Expression<?>[2];
            arguments159[0] = new Expression<Integer>(invoke_time);
            arguments159[1] = new Expression<Integer>(_17_0_5_edc0357_1345756501917_658961_12894);
            Expression<Boolean> condition159 = new Expression<Boolean>(true);
            elaborationRule159 = addElaborationRule(condition159, _17_0_5_edc0357_1345510113540_247161_13759.this, Power._17_0_5_edc0357_1345510113540_247161_13759._17_0_5_edc0357_1345756515226_314305_12896.class, "newGenVal_ActivityParameterNode_changeGenerationValue", arguments159);
            Expression<?>[] arguments160 = new Expression<?>[1];
            arguments160[0] = new Expression<Integer>(invoke_time);
            Expression<Boolean> condition160 = new Expression<Boolean>(true);
            elaborationRule160 = addElaborationRule(condition160, _17_0_5_edc0357_1345510113540_247161_13759.this, Power._17_0_5_edc0357_1345510113540_247161_13759._17_0_5_edc0357_1345510113898_758379_13994.class, "start_InitialNode_changeGenerationValue", arguments160);
        }

        public _17_0_5_edc0357_1345510113540_247161_13759() {
            super();
            init_17_0_5_edc0357_1345510113540_247161_13759Members();
            init_17_0_5_edc0357_1345510113540_247161_13759Collections();
            init_17_0_5_edc0357_1345510113540_247161_13759Elaborations();
        }

        public class _17_0_5_edc0357_1345510113895_502750_13992 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1345510113898_758379_13994_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1345510113901_55313_13999_exists = null;

            public Parameter _17_0_5_edc0357_1345510114739_739072_15022 = null;

            public ConstraintExpression constraint161 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510113901_55313_13999_existsDependency = null;

            public ElaborationRule elaborationRule162 = null;

            public void init_17_0_5_edc0357_1345510113895_502750_13992Members() {
                try {
                    _17_0_5_edc0357_1345510113898_758379_13994_endTime = new IntegerParameter("_17_0_5_edc0357_1345510113898_758379_13994_endTime", this);
                    _17_0_5_edc0357_1345510113901_55313_13999_exists = new BooleanParameter("_17_0_5_edc0357_1345510113901_55313_13999_exists", this);
                    _17_0_5_edc0357_1345510114739_739072_15022 = new Parameter("_17_0_5_edc0357_1345510114739_739072_15022", null, Power.this, this);
                    constraint161 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510113898_758379_13994_endTime)));
                    _17_0_5_edc0357_1345510113901_55313_13999_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510113901_55313_13999_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510113895_502750_13992Collections() {
                parameters.add(_17_0_5_edc0357_1345510113898_758379_13994_endTime);
                parameters.add(_17_0_5_edc0357_1345510113901_55313_13999_exists);
                parameters.add(_17_0_5_edc0357_1345510114739_739072_15022);
                constraintExpressions.add(constraint161);
                dependencies.add(_17_0_5_edc0357_1345510113901_55313_13999_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510113895_502750_13992Elaborations() {
                Expression<?>[] arguments162 = new Expression<?>[2];
                arguments162[0] = new Expression<Integer>(endTime);
                arguments162[1] = new Expression<Power>(_17_0_5_edc0357_1345510114739_739072_15022);
                Expression<Boolean> condition162 = new Expression<Boolean>(_17_0_5_edc0357_1345510113901_55313_13999_exists);
                elaborationRule162 = addElaborationRule(condition162, _17_0_5_edc0357_1345510113540_247161_13759.this, Power._17_0_5_edc0357_1345510113540_247161_13759._17_0_5_edc0357_1345510113901_55313_13999.class, "fork_self_ForkNode_changeGenerationValue", arguments162);
            }

            public _17_0_5_edc0357_1345510113895_502750_13992() {
                super();
                init_17_0_5_edc0357_1345510113895_502750_13992Members();
                init_17_0_5_edc0357_1345510113895_502750_13992Collections();
                init_17_0_5_edc0357_1345510113895_502750_13992Elaborations();
            }

            public _17_0_5_edc0357_1345510113895_502750_13992(Expression<Integer> _17_0_5_edc0357_1345510113898_758379_13994_endTime) {
                super();
                init_17_0_5_edc0357_1345510113895_502750_13992Members();
                init_17_0_5_edc0357_1345510113895_502750_13992Collections();
                addDependency(this._17_0_5_edc0357_1345510113898_758379_13994_endTime, _17_0_5_edc0357_1345510113898_758379_13994_endTime);
                init_17_0_5_edc0357_1345510113895_502750_13992Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510113897_296616_13993 extends DurativeEvent {

            public Parameter _17_0_5_edc0357_1345510114741_475296_15024 = null;

            public BooleanParameter _17_0_5_edc0357_1345510113900_906855_13997_exists = null;

            public IntegerParameter _17_0_5_edc0357_1345510114740_587173_15023 = null;

            public IntegerParameter _17_0_5_edc0357_1345510113901_940168_13998_endTime = null;

            public ConstraintExpression constraint163 = null;

            public Dependency< Power > _17_0_5_edc0357_1345510114741_475296_15024Dependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510113900_906855_13997_existsDependency = null;

            public Effect effect164 = null;

            public ElaborationRule elaborationRule165 = null;

            public void init_17_0_5_edc0357_1345510113897_296616_13993Members() {
                try {
                    _17_0_5_edc0357_1345510114741_475296_15024 = new Parameter("_17_0_5_edc0357_1345510114741_475296_15024", null, null, this);
                    _17_0_5_edc0357_1345510113900_906855_13997_exists = new BooleanParameter("_17_0_5_edc0357_1345510113900_906855_13997_exists", this);
                    _17_0_5_edc0357_1345510114740_587173_15023 = new IntegerParameter("_17_0_5_edc0357_1345510114740_587173_15023", this);
                    _17_0_5_edc0357_1345510113901_940168_13998_endTime = new IntegerParameter("_17_0_5_edc0357_1345510113901_940168_13998_endTime", this);
                    constraint163 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510113901_940168_13998_endTime)));
                    _17_0_5_edc0357_1345510114741_475296_15024Dependency = new Dependency<Power>(_17_0_5_edc0357_1345510114741_475296_15024, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510113902_184025_14005, Utils.getMethodForArgTypes("ObjectFlow<Power>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1345510113900_906855_13997_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510113900_906855_13997_exists, new Functions.And(new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510113902_380027_14003, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510113902_964176_14006, Utils.getMethodForArgTypes("ObjectFlow<Power>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) }))));
                    effect164 = new EffectFunction(new FunctionCall((Object) power__17_0_5_edc0357_1345510113545_356064_13763, Utils.getMethodForArgTypes("TimeVaryingMap<Integer>", "generated", "setValue", java.lang.Integer.class, java.lang.Object.class), new Object[] { startTime, _17_0_5_edc0357_1345510114740_587173_15023 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510113897_296616_13993Collections() {
                parameters.add(_17_0_5_edc0357_1345510114741_475296_15024);
                parameters.add(_17_0_5_edc0357_1345510113900_906855_13997_exists);
                parameters.add(_17_0_5_edc0357_1345510114740_587173_15023);
                parameters.add(_17_0_5_edc0357_1345510113901_940168_13998_endTime);
                constraintExpressions.add(constraint163);
                dependencies.add(_17_0_5_edc0357_1345510114741_475296_15024Dependency);
                dependencies.add(_17_0_5_edc0357_1345510113900_906855_13997_existsDependency);
                Set<Effect> effectsForpower__17_0_5_edc0357_1345510113545_356064_13763 = new HashSet<Effect>();
                effectsForpower__17_0_5_edc0357_1345510113545_356064_13763.add(effect164);
                effects.put((Parameter<?>) power__17_0_5_edc0357_1345510113545_356064_13763, effectsForpower__17_0_5_edc0357_1345510113545_356064_13763);
            }

            public void init_17_0_5_edc0357_1345510113897_296616_13993Elaborations() {
                Expression<?>[] arguments165 = new Expression<?>[1];
                arguments165[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition165 = new Expression<Boolean>(_17_0_5_edc0357_1345510113900_906855_13997_exists);
                elaborationRule165 = addElaborationRule(condition165, _17_0_5_edc0357_1345510113540_247161_13759.this, Power._17_0_5_edc0357_1345510113540_247161_13759._17_0_5_edc0357_1345510113900_906855_13997.class, "send_generation_reading_SendSignalAction_changeGenerationValue", arguments165);
            }

            public _17_0_5_edc0357_1345510113897_296616_13993() {
                super();
                init_17_0_5_edc0357_1345510113897_296616_13993Members();
                init_17_0_5_edc0357_1345510113897_296616_13993Collections();
                init_17_0_5_edc0357_1345510113897_296616_13993Elaborations();
            }

            public _17_0_5_edc0357_1345510113897_296616_13993(Expression<Integer> _17_0_5_edc0357_1345510113901_940168_13998_endTime, Expression<Integer> _17_0_5_edc0357_1345510114740_587173_15023) {
                super();
                init_17_0_5_edc0357_1345510113897_296616_13993Members();
                init_17_0_5_edc0357_1345510113897_296616_13993Collections();
                addDependency(this._17_0_5_edc0357_1345510113901_940168_13998_endTime, _17_0_5_edc0357_1345510113901_940168_13998_endTime);
                addDependency(this._17_0_5_edc0357_1345510114740_587173_15023, _17_0_5_edc0357_1345510114740_587173_15023);
                init_17_0_5_edc0357_1345510113897_296616_13993Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510113898_758379_13994 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1345510113895_502750_13992_exists = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510113895_502750_13992_existsDependency = null;

            public ElaborationRule elaborationRule166 = null;

            public void init_17_0_5_edc0357_1345510113898_758379_13994Members() {
                try {
                    _17_0_5_edc0357_1345510113895_502750_13992_exists = new BooleanParameter("_17_0_5_edc0357_1345510113895_502750_13992_exists", this);
                    _17_0_5_edc0357_1345510113895_502750_13992_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510113895_502750_13992_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510113898_758379_13994Collections() {
                parameters.add(_17_0_5_edc0357_1345510113895_502750_13992_exists);
                dependencies.add(_17_0_5_edc0357_1345510113895_502750_13992_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510113898_758379_13994Elaborations() {
                Expression<?>[] arguments166 = new Expression<?>[1];
                arguments166[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition166 = new Expression<Boolean>(_17_0_5_edc0357_1345510113895_502750_13992_exists);
                elaborationRule166 = addElaborationRule(condition166, _17_0_5_edc0357_1345510113540_247161_13759.this, Power._17_0_5_edc0357_1345510113540_247161_13759._17_0_5_edc0357_1345510113895_502750_13992.class, "readSelf_ReadSelfAction_changeGenerationValue", arguments166);
            }

            public _17_0_5_edc0357_1345510113898_758379_13994() {
                super();
                init_17_0_5_edc0357_1345510113898_758379_13994Members();
                init_17_0_5_edc0357_1345510113898_758379_13994Collections();
                init_17_0_5_edc0357_1345510113898_758379_13994Elaborations();
            }

            public _17_0_5_edc0357_1345510113898_758379_13994(Expression<Integer> startTime) {
                super();
                init_17_0_5_edc0357_1345510113898_758379_13994Members();
                init_17_0_5_edc0357_1345510113898_758379_13994Collections();
                removeDependency(this.startTime);
                addDependency(this.startTime, startTime);
                init_17_0_5_edc0357_1345510113898_758379_13994Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510113898_26640_13995 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1345510113900_906855_13997_endTime = null;

            public ConstraintExpression constraint167 = null;

            public void init_17_0_5_edc0357_1345510113898_26640_13995Members() {
                try {
                    _17_0_5_edc0357_1345510113900_906855_13997_endTime = new IntegerParameter("_17_0_5_edc0357_1345510113900_906855_13997_endTime", this);
                    constraint167 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510113900_906855_13997_endTime)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510113898_26640_13995Collections() {
                parameters.add(_17_0_5_edc0357_1345510113900_906855_13997_endTime);
                constraintExpressions.add(constraint167);
            }

            public void init_17_0_5_edc0357_1345510113898_26640_13995Elaborations() {
            }

            public _17_0_5_edc0357_1345510113898_26640_13995() {
                super();
                init_17_0_5_edc0357_1345510113898_26640_13995Members();
                init_17_0_5_edc0357_1345510113898_26640_13995Collections();
                init_17_0_5_edc0357_1345510113898_26640_13995Elaborations();
            }

            public _17_0_5_edc0357_1345510113898_26640_13995(Expression<Integer> _17_0_5_edc0357_1345510113900_906855_13997_endTime) {
                super();
                init_17_0_5_edc0357_1345510113898_26640_13995Members();
                init_17_0_5_edc0357_1345510113898_26640_13995Collections();
                addDependency(this._17_0_5_edc0357_1345510113900_906855_13997_endTime, _17_0_5_edc0357_1345510113900_906855_13997_endTime);
                init_17_0_5_edc0357_1345510113898_26640_13995Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510113900_906855_13997 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1345510113898_26640_13995_exists = null;

            public Parameter _17_0_5_edc0357_1345510114742_225045_15026 = null;

            public IntegerParameter _17_0_5_edc0357_1345510114743_924337_15027 = null;

            public IntegerParameter _17_0_5_edc0357_1345510113897_296616_13993_endTime = null;

            public ConstraintExpression constraint168 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510113898_26640_13995_existsDependency = null;

            public Dependency< Power > _17_0_5_edc0357_1345510114742_225045_15026Dependency = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114743_924337_15027Dependency = null;

            public Effect effect169 = null;

            public ElaborationRule elaborationRule170 = null;

            public void init_17_0_5_edc0357_1345510113900_906855_13997Members() {
                try {
                    _17_0_5_edc0357_1345510113898_26640_13995_exists = new BooleanParameter("_17_0_5_edc0357_1345510113898_26640_13995_exists", this);
                    _17_0_5_edc0357_1345510114742_225045_15026 = new Parameter("_17_0_5_edc0357_1345510114742_225045_15026", null, null, this);
                    _17_0_5_edc0357_1345510114743_924337_15027 = new IntegerParameter("_17_0_5_edc0357_1345510114743_924337_15027", this);
                    _17_0_5_edc0357_1345510113897_296616_13993_endTime = new IntegerParameter("_17_0_5_edc0357_1345510113897_296616_13993_endTime", this);
                    constraint168 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510113897_296616_13993_endTime)));
                    _17_0_5_edc0357_1345510113898_26640_13995_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510113898_26640_13995_exists, new Expression<Boolean>(true));
                    _17_0_5_edc0357_1345510114742_225045_15026Dependency = new Dependency<Power>(_17_0_5_edc0357_1345510114742_225045_15026, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510113902_964176_14006, Utils.getMethodForArgTypes("ObjectFlow<Power>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1345510114743_924337_15027Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114743_924337_15027, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510113902_380027_14003, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    effect169 = new EffectFunction(new FunctionCall((Object) ((Power_System) x).ss_17_0_5_edc0357_1345510113550_816176_13768_receiveGenReading.getValue(), Utils.getMethodForArgTypes("Power._17_0_5_edc0357_1345510113540_247161_13759._17_0_5_edc0357_1345510113900_906855_13997", "generated", "send"), new Object[] { x.new SignalreceiveGenReading(_17_0_5_edc0357_1345510114743_924337_15027.getValue()), endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510113900_906855_13997Collections() {
                parameters.add(_17_0_5_edc0357_1345510113898_26640_13995_exists);
                parameters.add(_17_0_5_edc0357_1345510114742_225045_15026);
                parameters.add(_17_0_5_edc0357_1345510114743_924337_15027);
                parameters.add(_17_0_5_edc0357_1345510113897_296616_13993_endTime);
                constraintExpressions.add(constraint168);
                dependencies.add(_17_0_5_edc0357_1345510113898_26640_13995_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510114742_225045_15026Dependency);
                dependencies.add(_17_0_5_edc0357_1345510114743_924337_15027Dependency);
            }

            public void init_17_0_5_edc0357_1345510113900_906855_13997Elaborations() {
                Expression<?>[] arguments170 = new Expression<?>[1];
                arguments170[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition170 = new Expression<Boolean>(_17_0_5_edc0357_1345510113898_26640_13995_exists);
                elaborationRule170 = addElaborationRule(condition170, _17_0_5_edc0357_1345510113540_247161_13759.this, Power._17_0_5_edc0357_1345510113540_247161_13759._17_0_5_edc0357_1345510113898_26640_13995.class, "end_ActivityFinalNode_changeGenerationValue", arguments170);
            }

            public _17_0_5_edc0357_1345510113900_906855_13997() {
                super();
                init_17_0_5_edc0357_1345510113900_906855_13997Members();
                init_17_0_5_edc0357_1345510113900_906855_13997Collections();
                init_17_0_5_edc0357_1345510113900_906855_13997Elaborations();
            }

            public _17_0_5_edc0357_1345510113900_906855_13997(Expression<Integer> _17_0_5_edc0357_1345510113897_296616_13993_endTime) {
                super();
                init_17_0_5_edc0357_1345510113900_906855_13997Members();
                init_17_0_5_edc0357_1345510113900_906855_13997Collections();
                addDependency(this._17_0_5_edc0357_1345510113897_296616_13993_endTime, _17_0_5_edc0357_1345510113897_296616_13993_endTime);
                init_17_0_5_edc0357_1345510113900_906855_13997Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510113901_940168_13998 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1345756515226_314305_12896_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1345510113897_296616_13993_exists = null;

            public IntegerParameter objectToPass = null;

            public ConstraintExpression constraint171 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510113897_296616_13993_existsDependency = null;

            public Effect effect172 = null;

            public ElaborationRule elaborationRule173 = null;

            public void init_17_0_5_edc0357_1345510113901_940168_13998Members() {
                try {
                    _17_0_5_edc0357_1345756515226_314305_12896_endTime = new IntegerParameter("_17_0_5_edc0357_1345756515226_314305_12896_endTime", this);
                    _17_0_5_edc0357_1345510113897_296616_13993_exists = new BooleanParameter("_17_0_5_edc0357_1345510113897_296616_13993_exists", this);
                    objectToPass = new IntegerParameter("objectToPass", this);
                    constraint171 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345756515226_314305_12896_endTime)));
                    _17_0_5_edc0357_1345510113897_296616_13993_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510113897_296616_13993_exists, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510113902_184025_14005, Utils.getMethodForArgTypes("ObjectFlow<Power>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })));
                    effect172 = new EffectFunction(new FunctionCall((Object) sig_17_0_5_edc0357_1345510113902_380027_14003, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510113901_940168_13998Collections() {
                parameters.add(_17_0_5_edc0357_1345756515226_314305_12896_endTime);
                parameters.add(_17_0_5_edc0357_1345510113897_296616_13993_exists);
                parameters.add(objectToPass);
                constraintExpressions.add(constraint171);
                dependencies.add(_17_0_5_edc0357_1345510113897_296616_13993_existsDependency);
                Set<Effect> effectsForsig_17_0_5_edc0357_1345510113902_380027_14003 = new HashSet<Effect>();
                effectsForsig_17_0_5_edc0357_1345510113902_380027_14003.add(effect172);
                effects.put((Parameter<?>) sig_17_0_5_edc0357_1345510113902_380027_14003, effectsForsig_17_0_5_edc0357_1345510113902_380027_14003);
            }

            public void init_17_0_5_edc0357_1345510113901_940168_13998Elaborations() {
                Expression<?>[] arguments173 = new Expression<?>[2];
                arguments173[0] = new Expression<Integer>(endTime);
                arguments173[1] = new Expression<Integer>(objectToPass);
                Expression<Boolean> condition173 = new Expression<Boolean>(_17_0_5_edc0357_1345510113897_296616_13993_exists);
                elaborationRule173 = addElaborationRule(condition173, _17_0_5_edc0357_1345510113540_247161_13759.this, Power._17_0_5_edc0357_1345510113540_247161_13759._17_0_5_edc0357_1345510113897_296616_13993.class, "add_struct_power_AddStructuralFeatureValueAction_changeGenerationValue", arguments173);
            }

            public _17_0_5_edc0357_1345510113901_940168_13998() {
                super();
                init_17_0_5_edc0357_1345510113901_940168_13998Members();
                init_17_0_5_edc0357_1345510113901_940168_13998Collections();
                init_17_0_5_edc0357_1345510113901_940168_13998Elaborations();
            }

            public _17_0_5_edc0357_1345510113901_940168_13998(Expression<Integer> _17_0_5_edc0357_1345756515226_314305_12896_endTime, Expression<Integer> objectToPass) {
                super();
                init_17_0_5_edc0357_1345510113901_940168_13998Members();
                init_17_0_5_edc0357_1345510113901_940168_13998Collections();
                addDependency(this._17_0_5_edc0357_1345756515226_314305_12896_endTime, _17_0_5_edc0357_1345756515226_314305_12896_endTime);
                addDependency(this.objectToPass, objectToPass);
                init_17_0_5_edc0357_1345510113901_940168_13998Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510113901_55313_13999 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1345510113895_502750_13992_endTime = null;

            public Parameter objectToPass = null;

            public ConstraintExpression constraint174 = null;

            public Effect effect175 = null;

            public Effect effect176 = null;

            public void init_17_0_5_edc0357_1345510113901_55313_13999Members() {
                try {
                    _17_0_5_edc0357_1345510113895_502750_13992_endTime = new IntegerParameter("_17_0_5_edc0357_1345510113895_502750_13992_endTime", this);
                    objectToPass = new Parameter("objectToPass", null, null, this);
                    constraint174 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510113895_502750_13992_endTime)));
                    effect175 = new EffectFunction(new FunctionCall((Object) sig_17_0_5_edc0357_1345510113902_184025_14005, Utils.getMethodForArgTypes("ObjectFlow<Power>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                    effect176 = new EffectFunction(new FunctionCall((Object) sig_17_0_5_edc0357_1345510113902_964176_14006, Utils.getMethodForArgTypes("ObjectFlow<Power>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510113901_55313_13999Collections() {
                parameters.add(_17_0_5_edc0357_1345510113895_502750_13992_endTime);
                parameters.add(objectToPass);
                constraintExpressions.add(constraint174);
                Set<Effect> effectsForsig_17_0_5_edc0357_1345510113902_184025_14005 = new HashSet<Effect>();
                effectsForsig_17_0_5_edc0357_1345510113902_184025_14005.add(effect175);
                effects.put((Parameter<?>) sig_17_0_5_edc0357_1345510113902_184025_14005, effectsForsig_17_0_5_edc0357_1345510113902_184025_14005);
                Set<Effect> effectsForsig_17_0_5_edc0357_1345510113902_964176_14006 = new HashSet<Effect>();
                effectsForsig_17_0_5_edc0357_1345510113902_964176_14006.add(effect176);
                effects.put((Parameter<?>) sig_17_0_5_edc0357_1345510113902_964176_14006, effectsForsig_17_0_5_edc0357_1345510113902_964176_14006);
            }

            public void init_17_0_5_edc0357_1345510113901_55313_13999Elaborations() {
            }

            public _17_0_5_edc0357_1345510113901_55313_13999() {
                super();
                init_17_0_5_edc0357_1345510113901_55313_13999Members();
                init_17_0_5_edc0357_1345510113901_55313_13999Collections();
                init_17_0_5_edc0357_1345510113901_55313_13999Elaborations();
            }

            public _17_0_5_edc0357_1345510113901_55313_13999(Expression<Integer> _17_0_5_edc0357_1345510113895_502750_13992_endTime, Expression<Power> objectToPass) {
                super();
                init_17_0_5_edc0357_1345510113901_55313_13999Members();
                init_17_0_5_edc0357_1345510113901_55313_13999Collections();
                addDependency(this._17_0_5_edc0357_1345510113895_502750_13992_endTime, _17_0_5_edc0357_1345510113895_502750_13992_endTime);
                addDependency(this.objectToPass, objectToPass);
                init_17_0_5_edc0357_1345510113901_55313_13999Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345756515226_314305_12896 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1345756501917_658961_12894 = null;

            public BooleanParameter _17_0_5_edc0357_1345510113901_940168_13998_exists = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510113901_940168_13998_existsDependency = null;

            public ElaborationRule elaborationRule177 = null;

            public void init_17_0_5_edc0357_1345756515226_314305_12896Members() {
                try {
                    _17_0_5_edc0357_1345756501917_658961_12894 = new IntegerParameter("_17_0_5_edc0357_1345756501917_658961_12894", this);
                    _17_0_5_edc0357_1345510113901_940168_13998_exists = new BooleanParameter("_17_0_5_edc0357_1345510113901_940168_13998_exists", this);
                    _17_0_5_edc0357_1345510113901_940168_13998_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510113901_940168_13998_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345756515226_314305_12896Collections() {
                parameters.add(_17_0_5_edc0357_1345756501917_658961_12894);
                parameters.add(_17_0_5_edc0357_1345510113901_940168_13998_exists);
                dependencies.add(_17_0_5_edc0357_1345510113901_940168_13998_existsDependency);
            }

            public void init_17_0_5_edc0357_1345756515226_314305_12896Elaborations() {
                Expression<?>[] arguments177 = new Expression<?>[2];
                arguments177[0] = new Expression<Integer>(endTime);
               // arguments177[1] = new Expression<Integer>(objectToPass);
                Expression<Boolean> condition177 = new Expression<Boolean>(_17_0_5_edc0357_1345510113901_940168_13998_exists);
                elaborationRule177 = addElaborationRule(condition177, _17_0_5_edc0357_1345510113540_247161_13759.this, Power._17_0_5_edc0357_1345510113540_247161_13759._17_0_5_edc0357_1345510113901_940168_13998.class, "fork_val_ForkNode_changeGenerationValue", arguments177);
            }

            public _17_0_5_edc0357_1345756515226_314305_12896() {
                super();
                init_17_0_5_edc0357_1345756515226_314305_12896Members();
                init_17_0_5_edc0357_1345756515226_314305_12896Collections();
                init_17_0_5_edc0357_1345756515226_314305_12896Elaborations();
            }

            public _17_0_5_edc0357_1345756515226_314305_12896(Expression<Integer> startTime, Expression<Integer> _17_0_5_edc0357_1345756501917_658961_12894) {
                super();
                init_17_0_5_edc0357_1345756515226_314305_12896Members();
                init_17_0_5_edc0357_1345756515226_314305_12896Collections();
                removeDependency(this.startTime);
                addDependency(this.startTime, startTime);
                addDependency(this._17_0_5_edc0357_1345756501917_658961_12894, _17_0_5_edc0357_1345756501917_658961_12894);
                init_17_0_5_edc0357_1345756515226_314305_12896Elaborations();
                fixTimeDependencies();
            }
        }

        public _17_0_5_edc0357_1345510113540_247161_13759(Expression<Integer> endTime, Expression<Integer> _17_0_5_edc0357_1345756501917_658961_12894) {
            super();
            init_17_0_5_edc0357_1345510113540_247161_13759Members();
            init_17_0_5_edc0357_1345510113540_247161_13759Collections();
            removeDependency(this.endTime);
            addDependency(this.endTime, endTime);
            addDependency(this._17_0_5_edc0357_1345756501917_658961_12894, _17_0_5_edc0357_1345756501917_658961_12894);
            init_17_0_5_edc0357_1345510113540_247161_13759Elaborations();
            fixTimeDependencies();
        }
    }

    public class _17_0_5_edc0357_1345510113541_858357_13760 extends DurativeEvent {

        public IntegerParameter _17_0_5_edc0357_1345756720553_849612_12938 = null;

        public IntegerParameter invoke_time = null;

        public Parameter sig_17_0_5_edc0357_1345510113922_694984_14041 = null;

        public Parameter sig_17_0_5_edc0357_1345756813363_602852_13369 = null;

        public ElaborationRule elaborationRule178 = null;

        public ElaborationRule elaborationRule179 = null;

        public void init_17_0_5_edc0357_1345510113541_858357_13760Members() {
            try {
                _17_0_5_edc0357_1345756720553_849612_12938 = new IntegerParameter("_17_0_5_edc0357_1345756720553_849612_12938", this);
                invoke_time = new IntegerParameter("invoke_time", this);
                sig_17_0_5_edc0357_1345510113922_694984_14041 = new Parameter("sig_17_0_5_edc0357_1345510113922_694984_14041", null, new ObjectFlow("sig_17_0_5_edc0357_1345510113922_694984_14041"), this);
                sig_17_0_5_edc0357_1345756813363_602852_13369 = new Parameter("sig_17_0_5_edc0357_1345756813363_602852_13369", null, new ObjectFlow("sig_17_0_5_edc0357_1345756813363_602852_13369"), this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void init_17_0_5_edc0357_1345510113541_858357_13760Collections() {
            parameters.add(_17_0_5_edc0357_1345756720553_849612_12938);
            parameters.add(invoke_time);
            parameters.add(sig_17_0_5_edc0357_1345510113922_694984_14041);
            parameters.add(sig_17_0_5_edc0357_1345756813363_602852_13369);
        }

        public void init_17_0_5_edc0357_1345510113541_858357_13760Elaborations() {
            Expression<?>[] arguments178 = new Expression<?>[2];
            arguments178[0] = new Expression<Integer>(invoke_time);
            arguments178[1] = new Expression<Integer>(_17_0_5_edc0357_1345756720553_849612_12938);
            Expression<Boolean> condition178 = new Expression<Boolean>(true);
            elaborationRule178 = addElaborationRule(condition178, _17_0_5_edc0357_1345510113541_858357_13760.this, Power._17_0_5_edc0357_1345510113541_858357_13760._17_0_5_edc0357_1345756729509_790741_12940.class, "newLoadVal_ActivityParameterNode_changeLoadValue", arguments178);
            Expression<?>[] arguments179 = new Expression<?>[1];
            arguments179[0] = new Expression<Integer>(invoke_time);
            Expression<Boolean> condition179 = new Expression<Boolean>(true);
            elaborationRule179 = addElaborationRule(condition179, _17_0_5_edc0357_1345510113541_858357_13760.this, Power._17_0_5_edc0357_1345510113541_858357_13760._17_0_5_edc0357_1345510113920_652162_14033.class, "start_InitialNode_changeLoadValue", arguments179);
        }

        public _17_0_5_edc0357_1345510113541_858357_13760() {
            super();
            init_17_0_5_edc0357_1345510113541_858357_13760Members();
            init_17_0_5_edc0357_1345510113541_858357_13760Collections();
            init_17_0_5_edc0357_1345510113541_858357_13760Elaborations();
        }

        public class _17_0_5_edc0357_1345510113918_681912_14031 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1345510113919_761849_14032_exists = null;

            public Parameter _17_0_5_edc0357_1345510114746_758365_15038 = null;

            public IntegerParameter _17_0_5_edc0357_1345510113920_652162_14033_endTime = null;

            public ConstraintExpression constraint180 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510113919_761849_14032_existsDependency = null;

            public Effect effect181 = null;

            public ElaborationRule elaborationRule182 = null;

            public void init_17_0_5_edc0357_1345510113918_681912_14031Members() {
                try {
                    _17_0_5_edc0357_1345510113919_761849_14032_exists = new BooleanParameter("_17_0_5_edc0357_1345510113919_761849_14032_exists", this);
                    _17_0_5_edc0357_1345510114746_758365_15038 = new Parameter("_17_0_5_edc0357_1345510114746_758365_15038", null, Power.this, this);
                    _17_0_5_edc0357_1345510113920_652162_14033_endTime = new IntegerParameter("_17_0_5_edc0357_1345510113920_652162_14033_endTime", this);
                    constraint180 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510113920_652162_14033_endTime)));
                    _17_0_5_edc0357_1345510113919_761849_14032_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510113919_761849_14032_exists, new Functions.And(new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510113922_694984_14041, Utils.getMethodForArgTypes("ObjectFlow<Power>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345756813363_602852_13369, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) }))));
                    effect181 = new EffectFunction(new FunctionCall((Object) sig_17_0_5_edc0357_1345510113922_694984_14041, Utils.getMethodForArgTypes("ObjectFlow<Power>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { _17_0_5_edc0357_1345510114746_758365_15038, startTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510113918_681912_14031Collections() {
                parameters.add(_17_0_5_edc0357_1345510113919_761849_14032_exists);
                parameters.add(_17_0_5_edc0357_1345510114746_758365_15038);
                parameters.add(_17_0_5_edc0357_1345510113920_652162_14033_endTime);
                constraintExpressions.add(constraint180);
                dependencies.add(_17_0_5_edc0357_1345510113919_761849_14032_existsDependency);
                Set<Effect> effectsForsig_17_0_5_edc0357_1345510113922_694984_14041 = new HashSet<Effect>();
                effectsForsig_17_0_5_edc0357_1345510113922_694984_14041.add(effect181);
                effects.put((Parameter<?>) sig_17_0_5_edc0357_1345510113922_694984_14041, effectsForsig_17_0_5_edc0357_1345510113922_694984_14041);
            }

            public void init_17_0_5_edc0357_1345510113918_681912_14031Elaborations() {
                Expression<?>[] arguments182 = new Expression<?>[1];
                arguments182[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition182 = new Expression<Boolean>(_17_0_5_edc0357_1345510113919_761849_14032_exists);
                elaborationRule182 = addElaborationRule(condition182, _17_0_5_edc0357_1345510113541_858357_13760.this, Power._17_0_5_edc0357_1345510113541_858357_13760._17_0_5_edc0357_1345510113919_761849_14032.class, "add_struct_load_AddStructuralFeatureValueAction_changeLoadValue", arguments182);
            }

            public _17_0_5_edc0357_1345510113918_681912_14031() {
                super();
                init_17_0_5_edc0357_1345510113918_681912_14031Members();
                init_17_0_5_edc0357_1345510113918_681912_14031Collections();
                init_17_0_5_edc0357_1345510113918_681912_14031Elaborations();
            }

            public _17_0_5_edc0357_1345510113918_681912_14031(Expression<Integer> _17_0_5_edc0357_1345510113920_652162_14033_endTime) {
                super();
                init_17_0_5_edc0357_1345510113918_681912_14031Members();
                init_17_0_5_edc0357_1345510113918_681912_14031Collections();
                addDependency(this._17_0_5_edc0357_1345510113920_652162_14033_endTime, _17_0_5_edc0357_1345510113920_652162_14033_endTime);
                init_17_0_5_edc0357_1345510113918_681912_14031Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510113919_761849_14032 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1345510114747_983362_15039 = null;

            public Parameter _17_0_5_edc0357_1345510114748_254651_15040 = null;

            public BooleanParameter _17_0_5_edc0357_1345510113920_40124_14034_exists = null;

            public IntegerParameter _17_0_5_edc0357_1345510113918_681912_14031_endTime = null;

            public ConstraintExpression constraint183 = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114747_983362_15039Dependency = null;

            public Dependency< Power > _17_0_5_edc0357_1345510114748_254651_15040Dependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510113920_40124_14034_existsDependency = null;

            public Effect effect184 = null;

            public ElaborationRule elaborationRule185 = null;

            public void init_17_0_5_edc0357_1345510113919_761849_14032Members() {
                try {
                    _17_0_5_edc0357_1345510114747_983362_15039 = new IntegerParameter("_17_0_5_edc0357_1345510114747_983362_15039", this);
                    _17_0_5_edc0357_1345510114748_254651_15040 = new Parameter("_17_0_5_edc0357_1345510114748_254651_15040", null, null, this);
                    _17_0_5_edc0357_1345510113920_40124_14034_exists = new BooleanParameter("_17_0_5_edc0357_1345510113920_40124_14034_exists", this);
                    _17_0_5_edc0357_1345510113918_681912_14031_endTime = new IntegerParameter("_17_0_5_edc0357_1345510113918_681912_14031_endTime", this);
                    constraint183 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510113918_681912_14031_endTime)));
                    _17_0_5_edc0357_1345510114747_983362_15039Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114747_983362_15039, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345756813363_602852_13369, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1345510114748_254651_15040Dependency = new Dependency<Power>(_17_0_5_edc0357_1345510114748_254651_15040, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510113922_694984_14041, Utils.getMethodForArgTypes("ObjectFlow<Power>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1345510113920_40124_14034_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510113920_40124_14034_exists, new Expression<Boolean>(true));
                    effect184 = new EffectFunction(new FunctionCall((Object) load__17_0_5_edc0357_1345510113546_825610_13764, Utils.getMethodForArgTypes("TimeVaryingMap<Integer>", "generated", "setValue", java.lang.Integer.class, java.lang.Object.class), new Object[] { startTime, _17_0_5_edc0357_1345510114747_983362_15039 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510113919_761849_14032Collections() {
                parameters.add(_17_0_5_edc0357_1345510114747_983362_15039);
                parameters.add(_17_0_5_edc0357_1345510114748_254651_15040);
                parameters.add(_17_0_5_edc0357_1345510113920_40124_14034_exists);
                parameters.add(_17_0_5_edc0357_1345510113918_681912_14031_endTime);
                constraintExpressions.add(constraint183);
                dependencies.add(_17_0_5_edc0357_1345510114747_983362_15039Dependency);
                dependencies.add(_17_0_5_edc0357_1345510114748_254651_15040Dependency);
                dependencies.add(_17_0_5_edc0357_1345510113920_40124_14034_existsDependency);
                Set<Effect> effectsForload__17_0_5_edc0357_1345510113546_825610_13764 = new HashSet<Effect>();
                effectsForload__17_0_5_edc0357_1345510113546_825610_13764.add(effect184);
                effects.put((Parameter<?>) load__17_0_5_edc0357_1345510113546_825610_13764, effectsForload__17_0_5_edc0357_1345510113546_825610_13764);
            }

            public void init_17_0_5_edc0357_1345510113919_761849_14032Elaborations() {
                Expression<?>[] arguments185 = new Expression<?>[1];
                arguments185[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition185 = new Expression<Boolean>(_17_0_5_edc0357_1345510113920_40124_14034_exists);
                elaborationRule185 = addElaborationRule(condition185, _17_0_5_edc0357_1345510113541_858357_13760.this, Power._17_0_5_edc0357_1345510113541_858357_13760._17_0_5_edc0357_1345510113920_40124_14034.class, "end_ActivityFinalNode_changeLoadValue", arguments185);
            }

            public _17_0_5_edc0357_1345510113919_761849_14032() {
                super();
                init_17_0_5_edc0357_1345510113919_761849_14032Members();
                init_17_0_5_edc0357_1345510113919_761849_14032Collections();
                init_17_0_5_edc0357_1345510113919_761849_14032Elaborations();
            }

            public _17_0_5_edc0357_1345510113919_761849_14032(Expression<Integer> _17_0_5_edc0357_1345510113918_681912_14031_endTime) {
                super();
                init_17_0_5_edc0357_1345510113919_761849_14032Members();
                init_17_0_5_edc0357_1345510113919_761849_14032Collections();
                addDependency(this._17_0_5_edc0357_1345510113918_681912_14031_endTime, _17_0_5_edc0357_1345510113918_681912_14031_endTime);
                init_17_0_5_edc0357_1345510113919_761849_14032Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510113920_652162_14033 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1345510113918_681912_14031_exists = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510113918_681912_14031_existsDependency = null;

            public ElaborationRule elaborationRule186 = null;

            public void init_17_0_5_edc0357_1345510113920_652162_14033Members() {
                try {
                    _17_0_5_edc0357_1345510113918_681912_14031_exists = new BooleanParameter("_17_0_5_edc0357_1345510113918_681912_14031_exists", this);
                    _17_0_5_edc0357_1345510113918_681912_14031_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510113918_681912_14031_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510113920_652162_14033Collections() {
                parameters.add(_17_0_5_edc0357_1345510113918_681912_14031_exists);
                dependencies.add(_17_0_5_edc0357_1345510113918_681912_14031_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510113920_652162_14033Elaborations() {
                Expression<?>[] arguments186 = new Expression<?>[1];
                arguments186[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition186 = new Expression<Boolean>(_17_0_5_edc0357_1345510113918_681912_14031_exists);
                elaborationRule186 = addElaborationRule(condition186, _17_0_5_edc0357_1345510113541_858357_13760.this, Power._17_0_5_edc0357_1345510113541_858357_13760._17_0_5_edc0357_1345510113918_681912_14031.class, "readself_ReadSelfAction_changeLoadValue", arguments186);
            }

            public _17_0_5_edc0357_1345510113920_652162_14033() {
                super();
                init_17_0_5_edc0357_1345510113920_652162_14033Members();
                init_17_0_5_edc0357_1345510113920_652162_14033Collections();
                init_17_0_5_edc0357_1345510113920_652162_14033Elaborations();
            }

            public _17_0_5_edc0357_1345510113920_652162_14033(Expression<Integer> startTime) {
                super();
                init_17_0_5_edc0357_1345510113920_652162_14033Members();
                init_17_0_5_edc0357_1345510113920_652162_14033Collections();
                removeDependency(this.startTime);
                addDependency(this.startTime, startTime);
                init_17_0_5_edc0357_1345510113920_652162_14033Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510113920_40124_14034 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1345510113919_761849_14032_endTime = null;

            public ConstraintExpression constraint187 = null;

            public void init_17_0_5_edc0357_1345510113920_40124_14034Members() {
                try {
                    _17_0_5_edc0357_1345510113919_761849_14032_endTime = new IntegerParameter("_17_0_5_edc0357_1345510113919_761849_14032_endTime", this);
                    constraint187 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510113919_761849_14032_endTime)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510113920_40124_14034Collections() {
                parameters.add(_17_0_5_edc0357_1345510113919_761849_14032_endTime);
                constraintExpressions.add(constraint187);
            }

            public void init_17_0_5_edc0357_1345510113920_40124_14034Elaborations() {
            }

            public _17_0_5_edc0357_1345510113920_40124_14034() {
                super();
                init_17_0_5_edc0357_1345510113920_40124_14034Members();
                init_17_0_5_edc0357_1345510113920_40124_14034Collections();
                init_17_0_5_edc0357_1345510113920_40124_14034Elaborations();
            }

            public _17_0_5_edc0357_1345510113920_40124_14034(Expression<Integer> _17_0_5_edc0357_1345510113919_761849_14032_endTime) {
                super();
                init_17_0_5_edc0357_1345510113920_40124_14034Members();
                init_17_0_5_edc0357_1345510113920_40124_14034Collections();
                addDependency(this._17_0_5_edc0357_1345510113919_761849_14032_endTime, _17_0_5_edc0357_1345510113919_761849_14032_endTime);
                init_17_0_5_edc0357_1345510113920_40124_14034Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345756729509_790741_12940 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1345756720553_849612_12938 = null;

            public Effect effect188 = null;

            public void init_17_0_5_edc0357_1345756729509_790741_12940Members() {
                try {
                    _17_0_5_edc0357_1345756720553_849612_12938 = new IntegerParameter("_17_0_5_edc0357_1345756720553_849612_12938", this);
                    effect188 = new EffectFunction(new FunctionCall((Object) sig_17_0_5_edc0357_1345756813363_602852_13369, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { _17_0_5_edc0357_1345756720553_849612_12938, startTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345756729509_790741_12940Collections() {
                parameters.add(_17_0_5_edc0357_1345756720553_849612_12938);
                Set<Effect> effectsForsig_17_0_5_edc0357_1345756813363_602852_13369 = new HashSet<Effect>();
                effectsForsig_17_0_5_edc0357_1345756813363_602852_13369.add(effect188);
                effects.put((Parameter<?>) sig_17_0_5_edc0357_1345756813363_602852_13369, effectsForsig_17_0_5_edc0357_1345756813363_602852_13369);
            }

            public void init_17_0_5_edc0357_1345756729509_790741_12940Elaborations() {
            }

            public _17_0_5_edc0357_1345756729509_790741_12940() {
                super();
                init_17_0_5_edc0357_1345756729509_790741_12940Members();
                init_17_0_5_edc0357_1345756729509_790741_12940Collections();
                init_17_0_5_edc0357_1345756729509_790741_12940Elaborations();
            }

            public _17_0_5_edc0357_1345756729509_790741_12940(Expression<Integer> startTime, Expression<Integer> _17_0_5_edc0357_1345756720553_849612_12938) {
                super();
                init_17_0_5_edc0357_1345756729509_790741_12940Members();
                init_17_0_5_edc0357_1345756729509_790741_12940Collections();
                removeDependency(this.startTime);
                addDependency(this.startTime, startTime);
                addDependency(this._17_0_5_edc0357_1345756720553_849612_12938, _17_0_5_edc0357_1345756720553_849612_12938);
                init_17_0_5_edc0357_1345756729509_790741_12940Elaborations();
                fixTimeDependencies();
            }
        }

        public _17_0_5_edc0357_1345510113541_858357_13760(Expression<Integer> endTime, Expression<Integer> _17_0_5_edc0357_1345756720553_849612_12938) {
            super();
            init_17_0_5_edc0357_1345510113541_858357_13760Members();
            init_17_0_5_edc0357_1345510113541_858357_13760Collections();
            removeDependency(this.endTime);
            addDependency(this.endTime, endTime);
            addDependency(this._17_0_5_edc0357_1345756720553_849612_12938, _17_0_5_edc0357_1345756720553_849612_12938);
            init_17_0_5_edc0357_1345510113541_858357_13760Elaborations();
            fixTimeDependencies();
        }
    }

    public class _17_0_5_edc0357_1345510113542_666806_13761 extends DurativeEvent {

        public Parameter sig_17_0_5_edc0357_1345510113942_415807_14078 = null;

        public Parameter sig_17_0_5_edc0357_1345510113942_807692_14079 = null;

        public Parameter sig_17_0_5_edc0357_1345510113943_183272_14085 = null;

        public IntegerParameter invoke_time = null;

        public ElaborationRule elaborationRule189 = null;

        public void init_17_0_5_edc0357_1345510113542_666806_13761Members() {
            try {
                sig_17_0_5_edc0357_1345510113942_415807_14078 = new Parameter("sig_17_0_5_edc0357_1345510113942_415807_14078", null, new ObjectFlow("sig_17_0_5_edc0357_1345510113942_415807_14078"), this);
                sig_17_0_5_edc0357_1345510113942_807692_14079 = new Parameter("sig_17_0_5_edc0357_1345510113942_807692_14079", null, new ObjectFlow("sig_17_0_5_edc0357_1345510113942_807692_14079"), this);
                sig_17_0_5_edc0357_1345510113943_183272_14085 = new Parameter("sig_17_0_5_edc0357_1345510113943_183272_14085", null, new ObjectFlow("sig_17_0_5_edc0357_1345510113943_183272_14085"), this);
                invoke_time = new IntegerParameter("invoke_time", this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void init_17_0_5_edc0357_1345510113542_666806_13761Collections() {
            parameters.add(sig_17_0_5_edc0357_1345510113942_415807_14078);
            parameters.add(sig_17_0_5_edc0357_1345510113942_807692_14079);
            parameters.add(sig_17_0_5_edc0357_1345510113943_183272_14085);
            parameters.add(invoke_time);
        }

        public void init_17_0_5_edc0357_1345510113542_666806_13761Elaborations() {
            Expression<?>[] arguments189 = new Expression<?>[1];
            arguments189[0] = new Expression<Integer>(invoke_time);
            Expression<Boolean> condition189 = new Expression<Boolean>(true);
            elaborationRule189 = addElaborationRule(condition189, _17_0_5_edc0357_1345510113542_666806_13761.this, Power._17_0_5_edc0357_1345510113542_666806_13761._17_0_5_edc0357_1345510113940_857370_14071.class, "_InitialNode_intialize", arguments189);
        }

        public _17_0_5_edc0357_1345510113542_666806_13761() {
            super();
            init_17_0_5_edc0357_1345510113542_666806_13761Members();
            init_17_0_5_edc0357_1345510113542_666806_13761Collections();
            init_17_0_5_edc0357_1345510113542_666806_13761Elaborations();
        }

        public class _17_0_5_edc0357_1345510113936_636752_14065 extends DurativeEvent {

            public Parameter _17_0_5_edc0357_1345510114753_527291_15047 = null;

            public BooleanParameter _17_0_5_edc0357_1345510113937_30533_14066_exists = null;

            public IntegerParameter _17_0_5_edc0357_1345510113940_364097_14072_endTime = null;

            public ConstraintExpression constraint190 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510113937_30533_14066_existsDependency = null;

            public ElaborationRule elaborationRule191 = null;

            public void init_17_0_5_edc0357_1345510113936_636752_14065Members() {
                try {
                    _17_0_5_edc0357_1345510114753_527291_15047 = new Parameter("_17_0_5_edc0357_1345510114753_527291_15047", null, Power.this, this);
                    _17_0_5_edc0357_1345510113937_30533_14066_exists = new BooleanParameter("_17_0_5_edc0357_1345510113937_30533_14066_exists", this);
                    _17_0_5_edc0357_1345510113940_364097_14072_endTime = new IntegerParameter("_17_0_5_edc0357_1345510113940_364097_14072_endTime", this);
                    constraint190 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510113940_364097_14072_endTime)));
                    _17_0_5_edc0357_1345510113937_30533_14066_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510113937_30533_14066_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510113936_636752_14065Collections() {
                parameters.add(_17_0_5_edc0357_1345510114753_527291_15047);
                parameters.add(_17_0_5_edc0357_1345510113937_30533_14066_exists);
                parameters.add(_17_0_5_edc0357_1345510113940_364097_14072_endTime);
                constraintExpressions.add(constraint190);
                dependencies.add(_17_0_5_edc0357_1345510113937_30533_14066_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510113936_636752_14065Elaborations() {
                Expression<?>[] arguments191 = new Expression<?>[2];
                arguments191[0] = new Expression<Integer>(endTime);
                arguments191[1] = new Expression<Power>(_17_0_5_edc0357_1345510114753_527291_15047);
                Expression<Boolean> condition191 = new Expression<Boolean>(_17_0_5_edc0357_1345510113937_30533_14066_exists);
                elaborationRule191 = addElaborationRule(condition191, _17_0_5_edc0357_1345510113542_666806_13761.this, Power._17_0_5_edc0357_1345510113542_666806_13761._17_0_5_edc0357_1345510113937_30533_14066.class, "self_fork_ForkNode_intialize", arguments191);
            }

            public _17_0_5_edc0357_1345510113936_636752_14065() {
                super();
                init_17_0_5_edc0357_1345510113936_636752_14065Members();
                init_17_0_5_edc0357_1345510113936_636752_14065Collections();
                init_17_0_5_edc0357_1345510113936_636752_14065Elaborations();
            }

            public _17_0_5_edc0357_1345510113936_636752_14065(Expression<Integer> _17_0_5_edc0357_1345510113940_364097_14072_endTime) {
                super();
                init_17_0_5_edc0357_1345510113936_636752_14065Members();
                init_17_0_5_edc0357_1345510113936_636752_14065Collections();
                addDependency(this._17_0_5_edc0357_1345510113940_364097_14072_endTime, _17_0_5_edc0357_1345510113940_364097_14072_endTime);
                init_17_0_5_edc0357_1345510113936_636752_14065Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510113937_30533_14066 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1345510113936_636752_14065_endTime = null;

            public Parameter objectToPass = null;

            public ConstraintExpression constraint192 = null;

            public Effect effect193 = null;

            public Effect effect194 = null;

            public void init_17_0_5_edc0357_1345510113937_30533_14066Members() {
                try {
                    _17_0_5_edc0357_1345510113936_636752_14065_endTime = new IntegerParameter("_17_0_5_edc0357_1345510113936_636752_14065_endTime", this);
                    objectToPass = new Parameter("objectToPass", null, null, this);
                    constraint192 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510113936_636752_14065_endTime)));
                    effect193 = new EffectFunction(new FunctionCall((Object) sig_17_0_5_edc0357_1345510113942_415807_14078, Utils.getMethodForArgTypes("ObjectFlow<Power>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                    effect194 = new EffectFunction(new FunctionCall((Object) sig_17_0_5_edc0357_1345510113942_807692_14079, Utils.getMethodForArgTypes("ObjectFlow<Power>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510113937_30533_14066Collections() {
                parameters.add(_17_0_5_edc0357_1345510113936_636752_14065_endTime);
                parameters.add(objectToPass);
                constraintExpressions.add(constraint192);
                Set<Effect> effectsForsig_17_0_5_edc0357_1345510113942_415807_14078 = new HashSet<Effect>();
                effectsForsig_17_0_5_edc0357_1345510113942_415807_14078.add(effect193);
                effects.put((Parameter<?>) sig_17_0_5_edc0357_1345510113942_415807_14078, effectsForsig_17_0_5_edc0357_1345510113942_415807_14078);
                Set<Effect> effectsForsig_17_0_5_edc0357_1345510113942_807692_14079 = new HashSet<Effect>();
                effectsForsig_17_0_5_edc0357_1345510113942_807692_14079.add(effect194);
                effects.put((Parameter<?>) sig_17_0_5_edc0357_1345510113942_807692_14079, effectsForsig_17_0_5_edc0357_1345510113942_807692_14079);
            }

            public void init_17_0_5_edc0357_1345510113937_30533_14066Elaborations() {
            }

            public _17_0_5_edc0357_1345510113937_30533_14066() {
                super();
                init_17_0_5_edc0357_1345510113937_30533_14066Members();
                init_17_0_5_edc0357_1345510113937_30533_14066Collections();
                init_17_0_5_edc0357_1345510113937_30533_14066Elaborations();
            }

            public _17_0_5_edc0357_1345510113937_30533_14066(Expression<Integer> _17_0_5_edc0357_1345510113936_636752_14065_endTime, Expression<Power> objectToPass) {
                super();
                init_17_0_5_edc0357_1345510113937_30533_14066Members();
                init_17_0_5_edc0357_1345510113937_30533_14066Collections();
                addDependency(this._17_0_5_edc0357_1345510113936_636752_14065_endTime, _17_0_5_edc0357_1345510113936_636752_14065_endTime);
                addDependency(this.objectToPass, objectToPass);
                init_17_0_5_edc0357_1345510113937_30533_14066Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510113938_215257_14067 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1345510113941_93056_14073_exists = null;

            public Parameter _17_0_5_edc0357_1345510114754_415963_15049 = null;

            public IntegerParameter _17_0_5_edc0357_1345510114754_755936_15048 = null;

            public IntegerParameter _17_0_5_edc0357_1345510113940_348480_14070_endTime = null;

            public ConstraintExpression constraint195 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510113941_93056_14073_existsDependency = null;

            public Dependency< Power > _17_0_5_edc0357_1345510114754_415963_15049Dependency = null;

            public Effect effect196 = null;

            public ElaborationRule elaborationRule197 = null;

            public void init_17_0_5_edc0357_1345510113938_215257_14067Members() {
                try {
                    _17_0_5_edc0357_1345510113941_93056_14073_exists = new BooleanParameter("_17_0_5_edc0357_1345510113941_93056_14073_exists", this);
                    _17_0_5_edc0357_1345510114754_415963_15049 = new Parameter("_17_0_5_edc0357_1345510114754_415963_15049", null, null, this);
                    _17_0_5_edc0357_1345510114754_755936_15048 = new IntegerParameter("_17_0_5_edc0357_1345510114754_755936_15048", this);
                    _17_0_5_edc0357_1345510113940_348480_14070_endTime = new IntegerParameter("_17_0_5_edc0357_1345510113940_348480_14070_endTime", this);
                    constraint195 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510113940_348480_14070_endTime)));
                    _17_0_5_edc0357_1345510113941_93056_14073_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510113941_93056_14073_exists, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510113943_183272_14085, Utils.getMethodForArgTypes("ObjectFlow<Object>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })));
                    _17_0_5_edc0357_1345510114754_415963_15049Dependency = new Dependency<Power>(_17_0_5_edc0357_1345510114754_415963_15049, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510113942_415807_14078, Utils.getMethodForArgTypes("ObjectFlow<Power>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    effect196 = new EffectFunction(new FunctionCall((Object) load__17_0_5_edc0357_1345510113546_825610_13764, Utils.getMethodForArgTypes("TimeVaryingMap<Integer>", "generated", "setValue", java.lang.Integer.class, java.lang.Object.class), new Object[] { startTime, _17_0_5_edc0357_1345510114754_755936_15048 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510113938_215257_14067Collections() {
                parameters.add(_17_0_5_edc0357_1345510113941_93056_14073_exists);
                parameters.add(_17_0_5_edc0357_1345510114754_415963_15049);
                parameters.add(_17_0_5_edc0357_1345510114754_755936_15048);
                parameters.add(_17_0_5_edc0357_1345510113940_348480_14070_endTime);
                constraintExpressions.add(constraint195);
                dependencies.add(_17_0_5_edc0357_1345510113941_93056_14073_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510114754_415963_15049Dependency);
                Set<Effect> effectsForload__17_0_5_edc0357_1345510113546_825610_13764 = new HashSet<Effect>();
                effectsForload__17_0_5_edc0357_1345510113546_825610_13764.add(effect196);
                effects.put((Parameter<?>) load__17_0_5_edc0357_1345510113546_825610_13764, effectsForload__17_0_5_edc0357_1345510113546_825610_13764);
            }

            public void init_17_0_5_edc0357_1345510113938_215257_14067Elaborations() {
                Expression<?>[] arguments197 = new Expression<?>[1];
                arguments197[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition197 = new Expression<Boolean>(_17_0_5_edc0357_1345510113941_93056_14073_exists);
                elaborationRule197 = addElaborationRule(condition197, _17_0_5_edc0357_1345510113542_666806_13761.this, Power._17_0_5_edc0357_1345510113542_666806_13761._17_0_5_edc0357_1345510113941_93056_14073.class, "end_join_JoinNode_intialize", arguments197);
            }

            public _17_0_5_edc0357_1345510113938_215257_14067() {
                super();
                init_17_0_5_edc0357_1345510113938_215257_14067Members();
                init_17_0_5_edc0357_1345510113938_215257_14067Collections();
                init_17_0_5_edc0357_1345510113938_215257_14067Elaborations();
            }

            public _17_0_5_edc0357_1345510113938_215257_14067(Expression<Integer> _17_0_5_edc0357_1345510113940_348480_14070_endTime, Expression<Integer> _17_0_5_edc0357_1345510114754_755936_15048) {
                super();
                init_17_0_5_edc0357_1345510113938_215257_14067Members();
                init_17_0_5_edc0357_1345510113938_215257_14067Collections();
                addDependency(this._17_0_5_edc0357_1345510113940_348480_14070_endTime, _17_0_5_edc0357_1345510113940_348480_14070_endTime);
                addDependency(this._17_0_5_edc0357_1345510114754_755936_15048, _17_0_5_edc0357_1345510114754_755936_15048);
                init_17_0_5_edc0357_1345510113938_215257_14067Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510113938_889571_14068 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1345510114755_802377_15050 = null;

            public IntegerParameter _17_0_5_edc0357_1345510113939_719662_14069_endTime = null;

            public Parameter _17_0_5_edc0357_1345510114756_517661_15051 = null;

            public ConstraintExpression constraint198 = null;

            public Dependency< Power > _17_0_5_edc0357_1345510114756_517661_15051Dependency = null;

            public Effect effect199 = null;

            public Effect effect200 = null;

            public void init_17_0_5_edc0357_1345510113938_889571_14068Members() {
                try {
                    _17_0_5_edc0357_1345510114755_802377_15050 = new IntegerParameter("_17_0_5_edc0357_1345510114755_802377_15050", this);
                    _17_0_5_edc0357_1345510113939_719662_14069_endTime = new IntegerParameter("_17_0_5_edc0357_1345510113939_719662_14069_endTime", this);
                    _17_0_5_edc0357_1345510114756_517661_15051 = new Parameter("_17_0_5_edc0357_1345510114756_517661_15051", null, null, this);
                    constraint198 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510113939_719662_14069_endTime)));
                    _17_0_5_edc0357_1345510114756_517661_15051Dependency = new Dependency<Power>(_17_0_5_edc0357_1345510114756_517661_15051, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510113942_807692_14079, Utils.getMethodForArgTypes("ObjectFlow<Power>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    effect199 = new EffectFunction(new FunctionCall((Object) sig_17_0_5_edc0357_1345510113943_183272_14085, Utils.getMethodForArgTypes("ObjectFlow<Object>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { true, startTime }));
                    effect200 = new EffectFunction(new FunctionCall((Object) power__17_0_5_edc0357_1345510113545_356064_13763, Utils.getMethodForArgTypes("TimeVaryingMap<Integer>", "generated", "setValue", java.lang.Integer.class, java.lang.Object.class), new Object[] { startTime, _17_0_5_edc0357_1345510114755_802377_15050 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510113938_889571_14068Collections() {
                parameters.add(_17_0_5_edc0357_1345510114755_802377_15050);
                parameters.add(_17_0_5_edc0357_1345510113939_719662_14069_endTime);
                parameters.add(_17_0_5_edc0357_1345510114756_517661_15051);
                constraintExpressions.add(constraint198);
                dependencies.add(_17_0_5_edc0357_1345510114756_517661_15051Dependency);
                Set<Effect> effectsForpower__17_0_5_edc0357_1345510113545_356064_13763 = new HashSet<Effect>();
                effectsForpower__17_0_5_edc0357_1345510113545_356064_13763.add(effect200);
                effects.put((Parameter<?>) power__17_0_5_edc0357_1345510113545_356064_13763, effectsForpower__17_0_5_edc0357_1345510113545_356064_13763);
                Set<Effect> effectsForsig_17_0_5_edc0357_1345510113943_183272_14085 = new HashSet<Effect>();
                effectsForsig_17_0_5_edc0357_1345510113943_183272_14085.add(effect199);
                effects.put((Parameter<?>) sig_17_0_5_edc0357_1345510113943_183272_14085, effectsForsig_17_0_5_edc0357_1345510113943_183272_14085);
            }

            public void init_17_0_5_edc0357_1345510113938_889571_14068Elaborations() {
            }

            public _17_0_5_edc0357_1345510113938_889571_14068() {
                super();
                init_17_0_5_edc0357_1345510113938_889571_14068Members();
                init_17_0_5_edc0357_1345510113938_889571_14068Collections();
                init_17_0_5_edc0357_1345510113938_889571_14068Elaborations();
            }

            public _17_0_5_edc0357_1345510113938_889571_14068(Expression<Integer> _17_0_5_edc0357_1345510113939_719662_14069_endTime, Expression<Integer> _17_0_5_edc0357_1345510114755_802377_15050) {
                super();
                init_17_0_5_edc0357_1345510113938_889571_14068Members();
                init_17_0_5_edc0357_1345510113938_889571_14068Collections();
                addDependency(this._17_0_5_edc0357_1345510113939_719662_14069_endTime, _17_0_5_edc0357_1345510113939_719662_14069_endTime);
                addDependency(this._17_0_5_edc0357_1345510114755_802377_15050, _17_0_5_edc0357_1345510114755_802377_15050);
                init_17_0_5_edc0357_1345510113938_889571_14068Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510113939_719662_14069 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1345510113938_889571_14068_exists = null;

            public IntegerParameter _17_0_5_edc0357_1345510113940_364097_14072_endTime = null;

            public IntegerParameter _17_0_5_edc0357_1345510114757_68713_15053 = null;

            public ConstraintExpression constraint201 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510113938_889571_14068_existsDependency = null;

            public ElaborationRule elaborationRule202 = null;

            public void init_17_0_5_edc0357_1345510113939_719662_14069Members() {
                try {
                    _17_0_5_edc0357_1345510113938_889571_14068_exists = new BooleanParameter("_17_0_5_edc0357_1345510113938_889571_14068_exists", this);
                    _17_0_5_edc0357_1345510113940_364097_14072_endTime = new IntegerParameter("_17_0_5_edc0357_1345510113940_364097_14072_endTime", this);
                    _17_0_5_edc0357_1345510114757_68713_15053 = new IntegerParameter("_17_0_5_edc0357_1345510114757_68713_15053", this);
                    constraint201 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510113940_364097_14072_endTime)));
                    _17_0_5_edc0357_1345510113938_889571_14068_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510113938_889571_14068_exists, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510113942_807692_14079, Utils.getMethodForArgTypes("ObjectFlow<Power>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510113939_719662_14069Collections() {
                parameters.add(_17_0_5_edc0357_1345510113938_889571_14068_exists);
                parameters.add(_17_0_5_edc0357_1345510113940_364097_14072_endTime);
                parameters.add(_17_0_5_edc0357_1345510114757_68713_15053);
                constraintExpressions.add(constraint201);
                dependencies.add(_17_0_5_edc0357_1345510113938_889571_14068_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510113939_719662_14069Elaborations() {
                Expression<?>[] arguments202 = new Expression<?>[2];
                arguments202[0] = new Expression<Integer>(endTime);
                arguments202[1] = new Expression<Integer>(_17_0_5_edc0357_1345510114757_68713_15053);
                Expression<Boolean> condition202 = new Expression<Boolean>(_17_0_5_edc0357_1345510113938_889571_14068_exists);
                elaborationRule202 = addElaborationRule(condition202, _17_0_5_edc0357_1345510113542_666806_13761.this, Power._17_0_5_edc0357_1345510113542_666806_13761._17_0_5_edc0357_1345510113938_889571_14068.class, "power_struct_add_AddStructuralFeatureValueAction_intialize", arguments202);
            }

            public _17_0_5_edc0357_1345510113939_719662_14069() {
                super();
                init_17_0_5_edc0357_1345510113939_719662_14069Members();
                init_17_0_5_edc0357_1345510113939_719662_14069Collections();
                init_17_0_5_edc0357_1345510113939_719662_14069Elaborations();
            }

            public _17_0_5_edc0357_1345510113939_719662_14069(Expression<Integer> _17_0_5_edc0357_1345510113940_364097_14072_endTime) {
                super();
                init_17_0_5_edc0357_1345510113939_719662_14069Members();
                init_17_0_5_edc0357_1345510113939_719662_14069Collections();
                addDependency(this._17_0_5_edc0357_1345510113940_364097_14072_endTime, _17_0_5_edc0357_1345510113940_364097_14072_endTime);
                init_17_0_5_edc0357_1345510113939_719662_14069Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510113940_348480_14070 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1345510113938_215257_14067_exists = null;

            public IntegerParameter _17_0_5_edc0357_1345510114757_633104_15055 = null;

            public IntegerParameter _17_0_5_edc0357_1345510113940_364097_14072_endTime = null;

            public ConstraintExpression constraint203 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510113938_215257_14067_existsDependency = null;

            public ElaborationRule elaborationRule204 = null;

            public void init_17_0_5_edc0357_1345510113940_348480_14070Members() {
                try {
                    _17_0_5_edc0357_1345510113938_215257_14067_exists = new BooleanParameter("_17_0_5_edc0357_1345510113938_215257_14067_exists", this);
                    _17_0_5_edc0357_1345510114757_633104_15055 = new IntegerParameter("_17_0_5_edc0357_1345510114757_633104_15055", this);
                    _17_0_5_edc0357_1345510113940_364097_14072_endTime = new IntegerParameter("_17_0_5_edc0357_1345510113940_364097_14072_endTime", this);
                    constraint203 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510113940_364097_14072_endTime)));
                    _17_0_5_edc0357_1345510113938_215257_14067_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510113938_215257_14067_exists, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510113942_415807_14078, Utils.getMethodForArgTypes("ObjectFlow<Power>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510113940_348480_14070Collections() {
                parameters.add(_17_0_5_edc0357_1345510113938_215257_14067_exists);
                parameters.add(_17_0_5_edc0357_1345510114757_633104_15055);
                parameters.add(_17_0_5_edc0357_1345510113940_364097_14072_endTime);
                constraintExpressions.add(constraint203);
                dependencies.add(_17_0_5_edc0357_1345510113938_215257_14067_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510113940_348480_14070Elaborations() {
                Expression<?>[] arguments204 = new Expression<?>[2];
                arguments204[0] = new Expression<Integer>(endTime);
                arguments204[1] = new Expression<Integer>(_17_0_5_edc0357_1345510114757_633104_15055);
                Expression<Boolean> condition204 = new Expression<Boolean>(_17_0_5_edc0357_1345510113938_215257_14067_exists);
                elaborationRule204 = addElaborationRule(condition204, _17_0_5_edc0357_1345510113542_666806_13761.this, Power._17_0_5_edc0357_1345510113542_666806_13761._17_0_5_edc0357_1345510113938_215257_14067.class, "load_struct_add_AddStructuralFeatureValueAction_intialize", arguments204);
            }

            public _17_0_5_edc0357_1345510113940_348480_14070() {
                super();
                init_17_0_5_edc0357_1345510113940_348480_14070Members();
                init_17_0_5_edc0357_1345510113940_348480_14070Collections();
                init_17_0_5_edc0357_1345510113940_348480_14070Elaborations();
            }

            public _17_0_5_edc0357_1345510113940_348480_14070(Expression<Integer> _17_0_5_edc0357_1345510113940_364097_14072_endTime) {
                super();
                init_17_0_5_edc0357_1345510113940_348480_14070Members();
                init_17_0_5_edc0357_1345510113940_348480_14070Collections();
                addDependency(this._17_0_5_edc0357_1345510113940_364097_14072_endTime, _17_0_5_edc0357_1345510113940_364097_14072_endTime);
                init_17_0_5_edc0357_1345510113940_348480_14070Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510113940_857370_14071 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1345510113940_364097_14072_exists = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510113940_364097_14072_existsDependency = null;

            public ElaborationRule elaborationRule205 = null;

            public void init_17_0_5_edc0357_1345510113940_857370_14071Members() {
                try {
                    _17_0_5_edc0357_1345510113940_364097_14072_exists = new BooleanParameter("_17_0_5_edc0357_1345510113940_364097_14072_exists", this);
                    _17_0_5_edc0357_1345510113940_364097_14072_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510113940_364097_14072_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510113940_857370_14071Collections() {
                parameters.add(_17_0_5_edc0357_1345510113940_364097_14072_exists);
                dependencies.add(_17_0_5_edc0357_1345510113940_364097_14072_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510113940_857370_14071Elaborations() {
                Expression<?>[] arguments205 = new Expression<?>[1];
                arguments205[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition205 = new Expression<Boolean>(_17_0_5_edc0357_1345510113940_364097_14072_exists);
                elaborationRule205 = addElaborationRule(condition205, _17_0_5_edc0357_1345510113542_666806_13761.this, Power._17_0_5_edc0357_1345510113542_666806_13761._17_0_5_edc0357_1345510113940_364097_14072.class, "start_fok_ForkNode_intialize", arguments205);
            }

            public _17_0_5_edc0357_1345510113940_857370_14071() {
                super();
                init_17_0_5_edc0357_1345510113940_857370_14071Members();
                init_17_0_5_edc0357_1345510113940_857370_14071Collections();
                init_17_0_5_edc0357_1345510113940_857370_14071Elaborations();
            }

            public _17_0_5_edc0357_1345510113940_857370_14071(Expression<Integer> startTime) {
                super();
                init_17_0_5_edc0357_1345510113940_857370_14071Members();
                init_17_0_5_edc0357_1345510113940_857370_14071Collections();
                removeDependency(this.startTime);
                addDependency(this.startTime, startTime);
                init_17_0_5_edc0357_1345510113940_857370_14071Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510113940_364097_14072 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1345510113940_857370_14071_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1345510113940_348480_14070_exists = null;

            public BooleanParameter _17_0_5_edc0357_1345510113939_719662_14069_exists = null;

            public BooleanParameter _17_0_5_edc0357_1345510113936_636752_14065_exists = null;

            public ConstraintExpression constraint206 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510113940_348480_14070_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510113939_719662_14069_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510113936_636752_14065_existsDependency = null;

            public ElaborationRule elaborationRule207 = null;

            public ElaborationRule elaborationRule208 = null;

            public ElaborationRule elaborationRule209 = null;

            public void init_17_0_5_edc0357_1345510113940_364097_14072Members() {
                try {
                    _17_0_5_edc0357_1345510113940_857370_14071_endTime = new IntegerParameter("_17_0_5_edc0357_1345510113940_857370_14071_endTime", this);
                    _17_0_5_edc0357_1345510113940_348480_14070_exists = new BooleanParameter("_17_0_5_edc0357_1345510113940_348480_14070_exists", this);
                    _17_0_5_edc0357_1345510113939_719662_14069_exists = new BooleanParameter("_17_0_5_edc0357_1345510113939_719662_14069_exists", this);
                    _17_0_5_edc0357_1345510113936_636752_14065_exists = new BooleanParameter("_17_0_5_edc0357_1345510113936_636752_14065_exists", this);
                    constraint206 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510113940_857370_14071_endTime)));
                    _17_0_5_edc0357_1345510113940_348480_14070_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510113940_348480_14070_exists, new Expression<Boolean>(true));
                    _17_0_5_edc0357_1345510113939_719662_14069_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510113939_719662_14069_exists, new Expression<Boolean>(true));
                    _17_0_5_edc0357_1345510113936_636752_14065_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510113936_636752_14065_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510113940_364097_14072Collections() {
                parameters.add(_17_0_5_edc0357_1345510113940_857370_14071_endTime);
                parameters.add(_17_0_5_edc0357_1345510113940_348480_14070_exists);
                parameters.add(_17_0_5_edc0357_1345510113939_719662_14069_exists);
                parameters.add(_17_0_5_edc0357_1345510113936_636752_14065_exists);
                constraintExpressions.add(constraint206);
                dependencies.add(_17_0_5_edc0357_1345510113940_348480_14070_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510113939_719662_14069_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510113936_636752_14065_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510113940_364097_14072Elaborations() {
                Expression<?>[] arguments207 = new Expression<?>[1];
                arguments207[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition207 = new Expression<Boolean>(_17_0_5_edc0357_1345510113936_636752_14065_exists);
                elaborationRule207 = addElaborationRule(condition207, _17_0_5_edc0357_1345510113542_666806_13761.this, Power._17_0_5_edc0357_1345510113542_666806_13761._17_0_5_edc0357_1345510113936_636752_14065.class, "readSelf_ReadSelfAction_intialize", arguments207);
                Expression<?>[] arguments208 = new Expression<?>[1];
                arguments208[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition208 = new Expression<Boolean>(_17_0_5_edc0357_1345510113939_719662_14069_exists);
                elaborationRule208 = addElaborationRule(condition208, _17_0_5_edc0357_1345510113542_666806_13761.this, Power._17_0_5_edc0357_1345510113542_666806_13761._17_0_5_edc0357_1345510113939_719662_14069.class, "power_val_spec_ValueSpecificationAction_intialize", arguments208);
                Expression<?>[] arguments209 = new Expression<?>[1];
                arguments209[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition209 = new Expression<Boolean>(_17_0_5_edc0357_1345510113940_348480_14070_exists);
                elaborationRule209 = addElaborationRule(condition209, _17_0_5_edc0357_1345510113542_666806_13761.this, Power._17_0_5_edc0357_1345510113542_666806_13761._17_0_5_edc0357_1345510113940_348480_14070.class, "load_val_spec_ValueSpecificationAction_intialize", arguments209);
            }

            public _17_0_5_edc0357_1345510113940_364097_14072() {
                super();
                init_17_0_5_edc0357_1345510113940_364097_14072Members();
                init_17_0_5_edc0357_1345510113940_364097_14072Collections();
                init_17_0_5_edc0357_1345510113940_364097_14072Elaborations();
            }

            public _17_0_5_edc0357_1345510113940_364097_14072(Expression<Integer> _17_0_5_edc0357_1345510113940_857370_14071_endTime) {
                super();
                init_17_0_5_edc0357_1345510113940_364097_14072Members();
                init_17_0_5_edc0357_1345510113940_364097_14072Collections();
                addDependency(this._17_0_5_edc0357_1345510113940_857370_14071_endTime, _17_0_5_edc0357_1345510113940_857370_14071_endTime);
                init_17_0_5_edc0357_1345510113940_364097_14072Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510113941_93056_14073 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1345510113938_215257_14067_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1345510113942_243779_14074_exists = null;

            public ConstraintExpression constraint210 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510113942_243779_14074_existsDependency = null;

            public ElaborationRule elaborationRule211 = null;

            public void init_17_0_5_edc0357_1345510113941_93056_14073Members() {
                try {
                    _17_0_5_edc0357_1345510113938_215257_14067_endTime = new IntegerParameter("_17_0_5_edc0357_1345510113938_215257_14067_endTime", this);
                    _17_0_5_edc0357_1345510113942_243779_14074_exists = new BooleanParameter("_17_0_5_edc0357_1345510113942_243779_14074_exists", this);
                    constraint210 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510113938_215257_14067_endTime)));
                    _17_0_5_edc0357_1345510113942_243779_14074_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510113942_243779_14074_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510113941_93056_14073Collections() {
                parameters.add(_17_0_5_edc0357_1345510113938_215257_14067_endTime);
                parameters.add(_17_0_5_edc0357_1345510113942_243779_14074_exists);
                constraintExpressions.add(constraint210);
                dependencies.add(_17_0_5_edc0357_1345510113942_243779_14074_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510113941_93056_14073Elaborations() {
                Expression<?>[] arguments211 = new Expression<?>[1];
                arguments211[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition211 = new Expression<Boolean>(_17_0_5_edc0357_1345510113942_243779_14074_exists);
                elaborationRule211 = addElaborationRule(condition211, _17_0_5_edc0357_1345510113542_666806_13761.this, Power._17_0_5_edc0357_1345510113542_666806_13761._17_0_5_edc0357_1345510113942_243779_14074.class, "_ActivityFinalNode_intialize", arguments211);
            }

            public _17_0_5_edc0357_1345510113941_93056_14073() {
                super();
                init_17_0_5_edc0357_1345510113941_93056_14073Members();
                init_17_0_5_edc0357_1345510113941_93056_14073Collections();
                init_17_0_5_edc0357_1345510113941_93056_14073Elaborations();
            }

            public _17_0_5_edc0357_1345510113941_93056_14073(Expression<Integer> _17_0_5_edc0357_1345510113938_215257_14067_endTime) {
                super();
                init_17_0_5_edc0357_1345510113941_93056_14073Members();
                init_17_0_5_edc0357_1345510113941_93056_14073Collections();
                addDependency(this._17_0_5_edc0357_1345510113938_215257_14067_endTime, _17_0_5_edc0357_1345510113938_215257_14067_endTime);
                init_17_0_5_edc0357_1345510113941_93056_14073Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510113942_243779_14074 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1345510113941_93056_14073_endTime = null;

            public ConstraintExpression constraint212 = null;

            public void init_17_0_5_edc0357_1345510113942_243779_14074Members() {
                try {
                    _17_0_5_edc0357_1345510113941_93056_14073_endTime = new IntegerParameter("_17_0_5_edc0357_1345510113941_93056_14073_endTime", this);
                    constraint212 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510113941_93056_14073_endTime)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510113942_243779_14074Collections() {
                parameters.add(_17_0_5_edc0357_1345510113941_93056_14073_endTime);
                constraintExpressions.add(constraint212);
            }

            public void init_17_0_5_edc0357_1345510113942_243779_14074Elaborations() {
            }

            public _17_0_5_edc0357_1345510113942_243779_14074() {
                super();
                init_17_0_5_edc0357_1345510113942_243779_14074Members();
                init_17_0_5_edc0357_1345510113942_243779_14074Collections();
                init_17_0_5_edc0357_1345510113942_243779_14074Elaborations();
            }

            public _17_0_5_edc0357_1345510113942_243779_14074(Expression<Integer> _17_0_5_edc0357_1345510113941_93056_14073_endTime) {
                super();
                init_17_0_5_edc0357_1345510113942_243779_14074Members();
                init_17_0_5_edc0357_1345510113942_243779_14074Collections();
                addDependency(this._17_0_5_edc0357_1345510113941_93056_14073_endTime, _17_0_5_edc0357_1345510113941_93056_14073_endTime);
                init_17_0_5_edc0357_1345510113942_243779_14074Elaborations();
                fixTimeDependencies();
            }
        }

        public _17_0_5_edc0357_1345510113542_666806_13761(Expression<Integer> endTime) {
            super();
            init_17_0_5_edc0357_1345510113542_666806_13761Members();
            init_17_0_5_edc0357_1345510113542_666806_13761Collections();
            removeDependency(this.endTime);
            addDependency(this.endTime, endTime);
            init_17_0_5_edc0357_1345510113542_666806_13761Elaborations();
            fixTimeDependencies();
        }
    }

    public class _17_0_5_edc0357_1345510113544_941994_13762 extends DurativeEvent {

        public Parameter sig_17_0_5_edc0357_1345510113965_962222_14133 = null;

        public Parameter sig_17_0_5_edc0357_1345510113963_14719_14119 = null;

        public Parameter sig_17_0_5_edc0357_1345510113964_837067_14124 = null;

        public Parameter sig_17_0_5_edc0357_1345510113964_881600_14125 = null;

        public Parameter sig_17_0_5_edc0357_1345510113964_263672_14120 = null;

        public Parameter sig_17_0_5_edc0357_1345510113964_922533_14122 = null;

        public Parameter sig_17_0_5_edc0357_1345510113964_266967_14123 = null;

        public IntegerParameter invoke_time = null;

        public ElaborationRule elaborationRule213 = null;

        public void init_17_0_5_edc0357_1345510113544_941994_13762Members() {
            try {
                sig_17_0_5_edc0357_1345510113965_962222_14133 = new Parameter("sig_17_0_5_edc0357_1345510113965_962222_14133", null, new ObjectFlow("sig_17_0_5_edc0357_1345510113965_962222_14133"), this);
                sig_17_0_5_edc0357_1345510113963_14719_14119 = new Parameter("sig_17_0_5_edc0357_1345510113963_14719_14119", null, new ObjectFlow("sig_17_0_5_edc0357_1345510113963_14719_14119"), this);
                sig_17_0_5_edc0357_1345510113964_837067_14124 = new Parameter("sig_17_0_5_edc0357_1345510113964_837067_14124", null, new ObjectFlow("sig_17_0_5_edc0357_1345510113964_837067_14124"), this);
                sig_17_0_5_edc0357_1345510113964_881600_14125 = new Parameter("sig_17_0_5_edc0357_1345510113964_881600_14125", null, new ObjectFlow("sig_17_0_5_edc0357_1345510113964_881600_14125"), this);
                sig_17_0_5_edc0357_1345510113964_263672_14120 = new Parameter("sig_17_0_5_edc0357_1345510113964_263672_14120", null, new ObjectFlow("sig_17_0_5_edc0357_1345510113964_263672_14120"), this);
                sig_17_0_5_edc0357_1345510113964_922533_14122 = new Parameter("sig_17_0_5_edc0357_1345510113964_922533_14122", null, new ObjectFlow("sig_17_0_5_edc0357_1345510113964_922533_14122"), this);
                sig_17_0_5_edc0357_1345510113964_266967_14123 = new Parameter("sig_17_0_5_edc0357_1345510113964_266967_14123", null, new ObjectFlow("sig_17_0_5_edc0357_1345510113964_266967_14123"), this);
                invoke_time = new IntegerParameter("invoke_time", this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void init_17_0_5_edc0357_1345510113544_941994_13762Collections() {
            parameters.add(sig_17_0_5_edc0357_1345510113965_962222_14133);
            parameters.add(sig_17_0_5_edc0357_1345510113963_14719_14119);
            parameters.add(sig_17_0_5_edc0357_1345510113964_837067_14124);
            parameters.add(sig_17_0_5_edc0357_1345510113964_881600_14125);
            parameters.add(sig_17_0_5_edc0357_1345510113964_263672_14120);
            parameters.add(sig_17_0_5_edc0357_1345510113964_922533_14122);
            parameters.add(sig_17_0_5_edc0357_1345510113964_266967_14123);
            parameters.add(invoke_time);
        }

        public void init_17_0_5_edc0357_1345510113544_941994_13762Elaborations() {
            Expression<?>[] arguments213 = new Expression<?>[1];
            arguments213[0] = new Expression<Integer>(invoke_time);
            Expression<Boolean> condition213 = new Expression<Boolean>(true);
            elaborationRule213 = addElaborationRule(condition213, _17_0_5_edc0357_1345510113544_941994_13762.this, Power._17_0_5_edc0357_1345510113544_941994_13762._17_0_5_edc0357_1345510113957_930465_14109.class, "start_InitialNode_generate", arguments213);
        }

        public _17_0_5_edc0357_1345510113544_941994_13762() {
            super();
            init_17_0_5_edc0357_1345510113544_941994_13762Members();
            init_17_0_5_edc0357_1345510113544_941994_13762Collections();
            init_17_0_5_edc0357_1345510113544_941994_13762Elaborations();
        }

        public class _17_0_5_edc0357_1345510113957_678472_14108 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1345510113961_883060_14115_exists = null;

            public IntegerParameter _17_0_5_edc0357_1345510113957_930465_14109_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1345510113963_167569_14117_exists = null;

            public Parameter _17_0_5_edc0357_1345510114764_172311_15068 = null;

            public ConstraintExpression constraint214 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510113961_883060_14115_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510113963_167569_14117_existsDependency = null;

            public ElaborationRule elaborationRule215 = null;

            public ElaborationRule elaborationRule216 = null;

            public void init_17_0_5_edc0357_1345510113957_678472_14108Members() {
                try {
                    _17_0_5_edc0357_1345510113961_883060_14115_exists = new BooleanParameter("_17_0_5_edc0357_1345510113961_883060_14115_exists", this);
                    _17_0_5_edc0357_1345510113957_930465_14109_endTime = new IntegerParameter("_17_0_5_edc0357_1345510113957_930465_14109_endTime", this);
                    _17_0_5_edc0357_1345510113963_167569_14117_exists = new BooleanParameter("_17_0_5_edc0357_1345510113963_167569_14117_exists", this);
                    _17_0_5_edc0357_1345510114764_172311_15068 = new Parameter("_17_0_5_edc0357_1345510114764_172311_15068", null, Power.this, this);
                    constraint214 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510113957_930465_14109_endTime)));
                    _17_0_5_edc0357_1345510113961_883060_14115_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510113961_883060_14115_exists, new Expression<Boolean>(true));
                    _17_0_5_edc0357_1345510113963_167569_14117_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510113963_167569_14117_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510113957_678472_14108Collections() {
                parameters.add(_17_0_5_edc0357_1345510113961_883060_14115_exists);
                parameters.add(_17_0_5_edc0357_1345510113957_930465_14109_endTime);
                parameters.add(_17_0_5_edc0357_1345510113963_167569_14117_exists);
                parameters.add(_17_0_5_edc0357_1345510114764_172311_15068);
                constraintExpressions.add(constraint214);
                dependencies.add(_17_0_5_edc0357_1345510113961_883060_14115_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510113963_167569_14117_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510113957_678472_14108Elaborations() {
                Expression<?>[] arguments215 = new Expression<?>[2];
                arguments215[0] = new Expression<Integer>(endTime);
                arguments215[1] = new Expression<Power>(_17_0_5_edc0357_1345510114764_172311_15068);
                Expression<Boolean> condition215 = new Expression<Boolean>(_17_0_5_edc0357_1345510113961_883060_14115_exists);
                elaborationRule215 = addElaborationRule(condition215, _17_0_5_edc0357_1345510113544_941994_13762.this, Power._17_0_5_edc0357_1345510113544_941994_13762._17_0_5_edc0357_1345510113961_883060_14115.class, "ob_fork_self_ForkNode_generate", arguments215);
                Expression<?>[] arguments216 = new Expression<?>[1];
                arguments216[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition216 = new Expression<Boolean>(_17_0_5_edc0357_1345510113963_167569_14117_exists);
                elaborationRule216 = addElaborationRule(condition216, _17_0_5_edc0357_1345510113544_941994_13762.this, Power._17_0_5_edc0357_1345510113544_941994_13762._17_0_5_edc0357_1345510113963_167569_14117.class, "control_fork_readself_ForkNode_generate", arguments216);
            }

            public _17_0_5_edc0357_1345510113957_678472_14108() {
                super();
                init_17_0_5_edc0357_1345510113957_678472_14108Members();
                init_17_0_5_edc0357_1345510113957_678472_14108Collections();
                init_17_0_5_edc0357_1345510113957_678472_14108Elaborations();
            }

            public _17_0_5_edc0357_1345510113957_678472_14108(Expression<Integer> _17_0_5_edc0357_1345510113957_930465_14109_endTime) {
                super();
                init_17_0_5_edc0357_1345510113957_678472_14108Members();
                init_17_0_5_edc0357_1345510113957_678472_14108Collections();
                addDependency(this._17_0_5_edc0357_1345510113957_930465_14109_endTime, _17_0_5_edc0357_1345510113957_930465_14109_endTime);
                init_17_0_5_edc0357_1345510113957_678472_14108Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510113957_930465_14109 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1345510113957_678472_14108_exists = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510113957_678472_14108_existsDependency = null;

            public ElaborationRule elaborationRule217 = null;

            public void init_17_0_5_edc0357_1345510113957_930465_14109Members() {
                try {
                    _17_0_5_edc0357_1345510113957_678472_14108_exists = new BooleanParameter("_17_0_5_edc0357_1345510113957_678472_14108_exists", this);
                    _17_0_5_edc0357_1345510113957_678472_14108_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510113957_678472_14108_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510113957_930465_14109Collections() {
                parameters.add(_17_0_5_edc0357_1345510113957_678472_14108_exists);
                dependencies.add(_17_0_5_edc0357_1345510113957_678472_14108_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510113957_930465_14109Elaborations() {
                Expression<?>[] arguments217 = new Expression<?>[1];
                arguments217[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition217 = new Expression<Boolean>(_17_0_5_edc0357_1345510113957_678472_14108_exists);
                elaborationRule217 = addElaborationRule(condition217, _17_0_5_edc0357_1345510113544_941994_13762.this, Power._17_0_5_edc0357_1345510113544_941994_13762._17_0_5_edc0357_1345510113957_678472_14108.class, "readself_ReadSelfAction_generate", arguments217);
            }

            public _17_0_5_edc0357_1345510113957_930465_14109() {
                super();
                init_17_0_5_edc0357_1345510113957_930465_14109Members();
                init_17_0_5_edc0357_1345510113957_930465_14109Collections();
                init_17_0_5_edc0357_1345510113957_930465_14109Elaborations();
            }

            public _17_0_5_edc0357_1345510113957_930465_14109(Expression<Integer> startTime) {
                super();
                init_17_0_5_edc0357_1345510113957_930465_14109Members();
                init_17_0_5_edc0357_1345510113957_930465_14109Collections();
                removeDependency(this.startTime);
                addDependency(this.startTime, startTime);
                init_17_0_5_edc0357_1345510113957_930465_14109Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510113958_150027_14110 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1345510113962_750982_14116_endTime = null;

            public ConstraintExpression constraint218 = null;

            public void init_17_0_5_edc0357_1345510113958_150027_14110Members() {
                try {
                    _17_0_5_edc0357_1345510113962_750982_14116_endTime = new IntegerParameter("_17_0_5_edc0357_1345510113962_750982_14116_endTime", this);
                    constraint218 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510113962_750982_14116_endTime)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510113958_150027_14110Collections() {
                parameters.add(_17_0_5_edc0357_1345510113962_750982_14116_endTime);
                constraintExpressions.add(constraint218);
            }

            public void init_17_0_5_edc0357_1345510113958_150027_14110Elaborations() {
            }

            public _17_0_5_edc0357_1345510113958_150027_14110() {
                super();
                init_17_0_5_edc0357_1345510113958_150027_14110Members();
                init_17_0_5_edc0357_1345510113958_150027_14110Collections();
                init_17_0_5_edc0357_1345510113958_150027_14110Elaborations();
            }

            public _17_0_5_edc0357_1345510113958_150027_14110(Expression<Integer> _17_0_5_edc0357_1345510113962_750982_14116_endTime) {
                super();
                init_17_0_5_edc0357_1345510113958_150027_14110Members();
                init_17_0_5_edc0357_1345510113958_150027_14110Collections();
                addDependency(this._17_0_5_edc0357_1345510113962_750982_14116_endTime, _17_0_5_edc0357_1345510113962_750982_14116_endTime);
                init_17_0_5_edc0357_1345510113958_150027_14110Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510113959_502637_14111 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1345510113963_167569_14117_endTime = null;

            public Parameter _17_0_5_edc0357_1345510114765_157490_15070 = null;

            public IntegerParameter _17_0_5_edc0357_1345510114765_286898_15069 = null;

            public BooleanParameter _17_0_5_edc0357_1345510113960_661152_14113_exists = null;

            public ConstraintExpression constraint219 = null;

            public Dependency< Power > _17_0_5_edc0357_1345510114765_157490_15070Dependency = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114765_286898_15069Dependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510113960_661152_14113_existsDependency = null;

            public Effect effect220 = null;

            public ElaborationRule elaborationRule221 = null;

            public void init_17_0_5_edc0357_1345510113959_502637_14111Members() {
                try {
                    _17_0_5_edc0357_1345510113963_167569_14117_endTime = new IntegerParameter("_17_0_5_edc0357_1345510113963_167569_14117_endTime", this);
                    _17_0_5_edc0357_1345510114765_157490_15070 = new Parameter("_17_0_5_edc0357_1345510114765_157490_15070", null, null, this);
                    _17_0_5_edc0357_1345510114765_286898_15069 = new IntegerParameter("_17_0_5_edc0357_1345510114765_286898_15069", this);
                    _17_0_5_edc0357_1345510113960_661152_14113_exists = new BooleanParameter("_17_0_5_edc0357_1345510113960_661152_14113_exists", this);
                    constraint219 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510113963_167569_14117_endTime)));
                    _17_0_5_edc0357_1345510114765_157490_15070Dependency = new Dependency<Power>(_17_0_5_edc0357_1345510114765_157490_15070, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510113964_922533_14122, Utils.getMethodForArgTypes("ObjectFlow<Power>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1345510114765_286898_15069Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114765_286898_15069, new Expression(new FunctionCall((Object) ((Power) _17_0_5_edc0357_1345510114765_157490_15070.getValue()).power__17_0_5_edc0357_1345510113545_356064_13763.getValue(), Utils.getMethodForArgTypes("Power._17_0_5_edc0357_1345510113544_941994_13762._17_0_5_edc0357_1345510113959_502637_14111", "generated", "getValue"), new Object[] { new Expression<Integer>(startTime) })));
                    _17_0_5_edc0357_1345510113960_661152_14113_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510113960_661152_14113_exists, new Functions.And(new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510113964_263672_14120, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510113964_837067_14124, Utils.getMethodForArgTypes("ObjectFlow<Power>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) }))));
                    effect220 = new EffectFunction(new FunctionCall((Object) sig_17_0_5_edc0357_1345510113964_263672_14120, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { _17_0_5_edc0357_1345510114765_286898_15069, startTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510113959_502637_14111Collections() {
                parameters.add(_17_0_5_edc0357_1345510113963_167569_14117_endTime);
                parameters.add(_17_0_5_edc0357_1345510114765_157490_15070);
                parameters.add(_17_0_5_edc0357_1345510114765_286898_15069);
                parameters.add(_17_0_5_edc0357_1345510113960_661152_14113_exists);
                constraintExpressions.add(constraint219);
                dependencies.add(_17_0_5_edc0357_1345510114765_157490_15070Dependency);
                dependencies.add(_17_0_5_edc0357_1345510114765_286898_15069Dependency);
                dependencies.add(_17_0_5_edc0357_1345510113960_661152_14113_existsDependency);
                Set<Effect> effectsForsig_17_0_5_edc0357_1345510113964_263672_14120 = new HashSet<Effect>();
                effectsForsig_17_0_5_edc0357_1345510113964_263672_14120.add(effect220);
                effects.put((Parameter<?>) sig_17_0_5_edc0357_1345510113964_263672_14120, effectsForsig_17_0_5_edc0357_1345510113964_263672_14120);
            }

            public void init_17_0_5_edc0357_1345510113959_502637_14111Elaborations() {
                Expression<?>[] arguments221 = new Expression<?>[1];
                arguments221[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition221 = new Expression<Boolean>(_17_0_5_edc0357_1345510113960_661152_14113_exists);
                elaborationRule221 = addElaborationRule(condition221, _17_0_5_edc0357_1345510113544_941994_13762.this, Power._17_0_5_edc0357_1345510113544_941994_13762._17_0_5_edc0357_1345510113960_661152_14113.class, "send_gen_reading_SendSignalAction_generate", arguments221);
            }

            public _17_0_5_edc0357_1345510113959_502637_14111() {
                super();
                init_17_0_5_edc0357_1345510113959_502637_14111Members();
                init_17_0_5_edc0357_1345510113959_502637_14111Collections();
                init_17_0_5_edc0357_1345510113959_502637_14111Elaborations();
            }

            public _17_0_5_edc0357_1345510113959_502637_14111(Expression<Integer> _17_0_5_edc0357_1345510113963_167569_14117_endTime) {
                super();
                init_17_0_5_edc0357_1345510113959_502637_14111Members();
                init_17_0_5_edc0357_1345510113959_502637_14111Collections();
                addDependency(this._17_0_5_edc0357_1345510113963_167569_14117_endTime, _17_0_5_edc0357_1345510113963_167569_14117_endTime);
                init_17_0_5_edc0357_1345510113959_502637_14111Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510113959_577677_14112 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1345510113963_167569_14117_endTime = null;

            public IntegerParameter _17_0_5_edc0357_1345510114766_724321_15071 = null;

            public Parameter _17_0_5_edc0357_1345510114767_768687_15072 = null;

            public BooleanParameter _17_0_5_edc0357_1345510113961_83686_14114_exists = null;

            public ConstraintExpression constraint222 = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114766_724321_15071Dependency = null;

            public Dependency< Power > _17_0_5_edc0357_1345510114767_768687_15072Dependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510113961_83686_14114_existsDependency = null;

            public Effect effect223 = null;

            public ElaborationRule elaborationRule224 = null;

            public void init_17_0_5_edc0357_1345510113959_577677_14112Members() {
                try {
                    _17_0_5_edc0357_1345510113963_167569_14117_endTime = new IntegerParameter("_17_0_5_edc0357_1345510113963_167569_14117_endTime", this);
                    _17_0_5_edc0357_1345510114766_724321_15071 = new IntegerParameter("_17_0_5_edc0357_1345510114766_724321_15071", this);
                    _17_0_5_edc0357_1345510114767_768687_15072 = new Parameter("_17_0_5_edc0357_1345510114767_768687_15072", null, null, this);
                    _17_0_5_edc0357_1345510113961_83686_14114_exists = new BooleanParameter("_17_0_5_edc0357_1345510113961_83686_14114_exists", this);
                    constraint222 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510113963_167569_14117_endTime)));
                    _17_0_5_edc0357_1345510114766_724321_15071Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114766_724321_15071, new Expression(new FunctionCall((Object) ((Power) _17_0_5_edc0357_1345510114767_768687_15072.getValue()).load__17_0_5_edc0357_1345510113546_825610_13764.getValue(), Utils.getMethodForArgTypes("Power._17_0_5_edc0357_1345510113544_941994_13762._17_0_5_edc0357_1345510113959_577677_14112", "generated", "getValue"), new Object[] { new Expression<Integer>(startTime) })));
                    _17_0_5_edc0357_1345510114767_768687_15072Dependency = new Dependency<Power>(_17_0_5_edc0357_1345510114767_768687_15072, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510113964_266967_14123, Utils.getMethodForArgTypes("ObjectFlow<Power>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1345510113961_83686_14114_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510113961_83686_14114_exists, new Functions.And(new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510113963_14719_14119, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510113964_881600_14125, Utils.getMethodForArgTypes("ObjectFlow<Power>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) }))));
                    effect223 = new EffectFunction(new FunctionCall((Object) sig_17_0_5_edc0357_1345510113963_14719_14119, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { _17_0_5_edc0357_1345510114766_724321_15071, startTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510113959_577677_14112Collections() {
                parameters.add(_17_0_5_edc0357_1345510113963_167569_14117_endTime);
                parameters.add(_17_0_5_edc0357_1345510114766_724321_15071);
                parameters.add(_17_0_5_edc0357_1345510114767_768687_15072);
                parameters.add(_17_0_5_edc0357_1345510113961_83686_14114_exists);
                constraintExpressions.add(constraint222);
                dependencies.add(_17_0_5_edc0357_1345510114766_724321_15071Dependency);
                dependencies.add(_17_0_5_edc0357_1345510114767_768687_15072Dependency);
                dependencies.add(_17_0_5_edc0357_1345510113961_83686_14114_existsDependency);
                Set<Effect> effectsForsig_17_0_5_edc0357_1345510113963_14719_14119 = new HashSet<Effect>();
                effectsForsig_17_0_5_edc0357_1345510113963_14719_14119.add(effect223);
                effects.put((Parameter<?>) sig_17_0_5_edc0357_1345510113963_14719_14119, effectsForsig_17_0_5_edc0357_1345510113963_14719_14119);
            }

            public void init_17_0_5_edc0357_1345510113959_577677_14112Elaborations() {
                Expression<?>[] arguments224 = new Expression<?>[1];
                arguments224[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition224 = new Expression<Boolean>(_17_0_5_edc0357_1345510113961_83686_14114_exists);
                elaborationRule224 = addElaborationRule(condition224, _17_0_5_edc0357_1345510113544_941994_13762.this, Power._17_0_5_edc0357_1345510113544_941994_13762._17_0_5_edc0357_1345510113961_83686_14114.class, "send_load_reading_SendSignalAction_generate", arguments224);
            }

            public _17_0_5_edc0357_1345510113959_577677_14112() {
                super();
                init_17_0_5_edc0357_1345510113959_577677_14112Members();
                init_17_0_5_edc0357_1345510113959_577677_14112Collections();
                init_17_0_5_edc0357_1345510113959_577677_14112Elaborations();
            }

            public _17_0_5_edc0357_1345510113959_577677_14112(Expression<Integer> _17_0_5_edc0357_1345510113963_167569_14117_endTime) {
                super();
                init_17_0_5_edc0357_1345510113959_577677_14112Members();
                init_17_0_5_edc0357_1345510113959_577677_14112Collections();
                addDependency(this._17_0_5_edc0357_1345510113963_167569_14117_endTime, _17_0_5_edc0357_1345510113963_167569_14117_endTime);
                init_17_0_5_edc0357_1345510113959_577677_14112Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510113960_661152_14113 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1345510114768_446406_15074 = null;

            public Parameter _17_0_5_edc0357_1345510114767_751097_15073 = null;

            public BooleanParameter _17_0_5_edc0357_1345510113962_750982_14116_exists = null;

            public IntegerParameter _17_0_5_edc0357_1345510113959_502637_14111_endTime = null;

            public ConstraintExpression constraint225 = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114768_446406_15074Dependency = null;

            public Dependency< Power > _17_0_5_edc0357_1345510114767_751097_15073Dependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510113962_750982_14116_existsDependency = null;

            public Effect effect226 = null;

            public ElaborationRule elaborationRule227 = null;

            public void init_17_0_5_edc0357_1345510113960_661152_14113Members() {
                try {
                    _17_0_5_edc0357_1345510114768_446406_15074 = new IntegerParameter("_17_0_5_edc0357_1345510114768_446406_15074", this);
                    _17_0_5_edc0357_1345510114767_751097_15073 = new Parameter("_17_0_5_edc0357_1345510114767_751097_15073", null, null, this);
                    _17_0_5_edc0357_1345510113962_750982_14116_exists = new BooleanParameter("_17_0_5_edc0357_1345510113962_750982_14116_exists", this);
                    _17_0_5_edc0357_1345510113959_502637_14111_endTime = new IntegerParameter("_17_0_5_edc0357_1345510113959_502637_14111_endTime", this);
                    constraint225 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510113959_502637_14111_endTime)));
                    _17_0_5_edc0357_1345510114768_446406_15074Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114768_446406_15074, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510113964_263672_14120, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1345510114767_751097_15073Dependency = new Dependency<Power>(_17_0_5_edc0357_1345510114767_751097_15073, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510113964_837067_14124, Utils.getMethodForArgTypes("ObjectFlow<Power>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1345510113962_750982_14116_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510113962_750982_14116_exists, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510113965_962222_14133, Utils.getMethodForArgTypes("ObjectFlow<Object>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })));
                    effect226 = new EffectFunction(new FunctionCall((Object) ((Power_System) x).ss_17_0_5_edc0357_1345510113550_816176_13768_receiveGenReading.getValue(), Utils.getMethodForArgTypes("Power._17_0_5_edc0357_1345510113544_941994_13762._17_0_5_edc0357_1345510113960_661152_14113", "generated", "send"), new Object[] { x.new SignalreceiveGenReading(_17_0_5_edc0357_1345510114768_446406_15074.getValue()), endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510113960_661152_14113Collections() {
                parameters.add(_17_0_5_edc0357_1345510114768_446406_15074);
                parameters.add(_17_0_5_edc0357_1345510114767_751097_15073);
                parameters.add(_17_0_5_edc0357_1345510113962_750982_14116_exists);
                parameters.add(_17_0_5_edc0357_1345510113959_502637_14111_endTime);
                constraintExpressions.add(constraint225);
                dependencies.add(_17_0_5_edc0357_1345510114768_446406_15074Dependency);
                dependencies.add(_17_0_5_edc0357_1345510114767_751097_15073Dependency);
                dependencies.add(_17_0_5_edc0357_1345510113962_750982_14116_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510113960_661152_14113Elaborations() {
                Expression<?>[] arguments227 = new Expression<?>[1];
                arguments227[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition227 = new Expression<Boolean>(_17_0_5_edc0357_1345510113962_750982_14116_exists);
                elaborationRule227 = addElaborationRule(condition227, _17_0_5_edc0357_1345510113544_941994_13762.this, Power._17_0_5_edc0357_1345510113544_941994_13762._17_0_5_edc0357_1345510113962_750982_14116.class, "ctrl_fork_end_JoinNode_generate", arguments227);
            }

            public _17_0_5_edc0357_1345510113960_661152_14113() {
                super();
                init_17_0_5_edc0357_1345510113960_661152_14113Members();
                init_17_0_5_edc0357_1345510113960_661152_14113Collections();
                init_17_0_5_edc0357_1345510113960_661152_14113Elaborations();
            }

            public _17_0_5_edc0357_1345510113960_661152_14113(Expression<Integer> _17_0_5_edc0357_1345510113959_502637_14111_endTime) {
                super();
                init_17_0_5_edc0357_1345510113960_661152_14113Members();
                init_17_0_5_edc0357_1345510113960_661152_14113Collections();
                addDependency(this._17_0_5_edc0357_1345510113959_502637_14111_endTime, _17_0_5_edc0357_1345510113959_502637_14111_endTime);
                init_17_0_5_edc0357_1345510113960_661152_14113Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510113961_83686_14114 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1345510114769_966831_15076 = null;

            public Parameter _17_0_5_edc0357_1345510114769_461455_15075 = null;

            public IntegerParameter _17_0_5_edc0357_1345510113959_577677_14112_endTime = null;

            public ConstraintExpression constraint228 = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114769_966831_15076Dependency = null;

            public Dependency< Power > _17_0_5_edc0357_1345510114769_461455_15075Dependency = null;

            public Effect effect229 = null;

            public Effect effect230 = null;

            public void init_17_0_5_edc0357_1345510113961_83686_14114Members() {
                try {
                    _17_0_5_edc0357_1345510114769_966831_15076 = new IntegerParameter("_17_0_5_edc0357_1345510114769_966831_15076", this);
                    _17_0_5_edc0357_1345510114769_461455_15075 = new Parameter("_17_0_5_edc0357_1345510114769_461455_15075", null, null, this);
                    _17_0_5_edc0357_1345510113959_577677_14112_endTime = new IntegerParameter("_17_0_5_edc0357_1345510113959_577677_14112_endTime", this);
                    constraint228 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510113959_577677_14112_endTime)));
                    _17_0_5_edc0357_1345510114769_966831_15076Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114769_966831_15076, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510113963_14719_14119, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1345510114769_461455_15075Dependency = new Dependency<Power>(_17_0_5_edc0357_1345510114769_461455_15075, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510113964_881600_14125, Utils.getMethodForArgTypes("ObjectFlow<Power>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    effect229 = new EffectFunction(new FunctionCall((Object) sig_17_0_5_edc0357_1345510113965_962222_14133, Utils.getMethodForArgTypes("ObjectFlow<Object>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { true, startTime }));
                    effect230 = new EffectFunction(new FunctionCall((Object) ((Power_System) x).ss_17_0_5_edc0357_1345510113549_985081_13767_receiveLoadReading.getValue(), Utils.getMethodForArgTypes("Power._17_0_5_edc0357_1345510113544_941994_13762._17_0_5_edc0357_1345510113961_83686_14114", "generated", "send"), new Object[] { x.new SignalreceiveLoadReading(_17_0_5_edc0357_1345510114769_966831_15076.getValue()), endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510113961_83686_14114Collections() {
                parameters.add(_17_0_5_edc0357_1345510114769_966831_15076);
                parameters.add(_17_0_5_edc0357_1345510114769_461455_15075);
                parameters.add(_17_0_5_edc0357_1345510113959_577677_14112_endTime);
                constraintExpressions.add(constraint228);
                dependencies.add(_17_0_5_edc0357_1345510114769_966831_15076Dependency);
                dependencies.add(_17_0_5_edc0357_1345510114769_461455_15075Dependency);
            }

            public void init_17_0_5_edc0357_1345510113961_83686_14114Elaborations() {
            }

            public _17_0_5_edc0357_1345510113961_83686_14114() {
                super();
                init_17_0_5_edc0357_1345510113961_83686_14114Members();
                init_17_0_5_edc0357_1345510113961_83686_14114Collections();
                init_17_0_5_edc0357_1345510113961_83686_14114Elaborations();
            }

            public _17_0_5_edc0357_1345510113961_83686_14114(Expression<Integer> _17_0_5_edc0357_1345510113959_577677_14112_endTime) {
                super();
                init_17_0_5_edc0357_1345510113961_83686_14114Members();
                init_17_0_5_edc0357_1345510113961_83686_14114Collections();
                addDependency(this._17_0_5_edc0357_1345510113959_577677_14112_endTime, _17_0_5_edc0357_1345510113959_577677_14112_endTime);
                init_17_0_5_edc0357_1345510113961_83686_14114Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510113961_883060_14115 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1345510113957_678472_14108_endTime = null;

            public Parameter objectToPass = null;

            public ConstraintExpression constraint231 = null;

            public Effect effect232 = null;

            public Effect effect233 = null;

            public Effect effect234 = null;

            public Effect effect235 = null;

            public void init_17_0_5_edc0357_1345510113961_883060_14115Members() {
                try {
                    _17_0_5_edc0357_1345510113957_678472_14108_endTime = new IntegerParameter("_17_0_5_edc0357_1345510113957_678472_14108_endTime", this);
                    objectToPass = new Parameter("objectToPass", null, null, this);
                    constraint231 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510113957_678472_14108_endTime)));
                    effect232 = new EffectFunction(new FunctionCall((Object) sig_17_0_5_edc0357_1345510113964_922533_14122, Utils.getMethodForArgTypes("ObjectFlow<Power>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                    effect233 = new EffectFunction(new FunctionCall((Object) sig_17_0_5_edc0357_1345510113964_266967_14123, Utils.getMethodForArgTypes("ObjectFlow<Power>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                    effect234 = new EffectFunction(new FunctionCall((Object) sig_17_0_5_edc0357_1345510113964_837067_14124, Utils.getMethodForArgTypes("ObjectFlow<Power>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                    effect235 = new EffectFunction(new FunctionCall((Object) sig_17_0_5_edc0357_1345510113964_881600_14125, Utils.getMethodForArgTypes("ObjectFlow<Power>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510113961_883060_14115Collections() {
                parameters.add(_17_0_5_edc0357_1345510113957_678472_14108_endTime);
                parameters.add(objectToPass);
                constraintExpressions.add(constraint231);
                Set<Effect> effectsForsig_17_0_5_edc0357_1345510113964_266967_14123 = new HashSet<Effect>();
                effectsForsig_17_0_5_edc0357_1345510113964_266967_14123.add(effect233);
                effects.put((Parameter<?>) sig_17_0_5_edc0357_1345510113964_266967_14123, effectsForsig_17_0_5_edc0357_1345510113964_266967_14123);
                Set<Effect> effectsForsig_17_0_5_edc0357_1345510113964_837067_14124 = new HashSet<Effect>();
                effectsForsig_17_0_5_edc0357_1345510113964_837067_14124.add(effect234);
                effects.put((Parameter<?>) sig_17_0_5_edc0357_1345510113964_837067_14124, effectsForsig_17_0_5_edc0357_1345510113964_837067_14124);
                Set<Effect> effectsForsig_17_0_5_edc0357_1345510113964_881600_14125 = new HashSet<Effect>();
                effectsForsig_17_0_5_edc0357_1345510113964_881600_14125.add(effect235);
                effects.put((Parameter<?>) sig_17_0_5_edc0357_1345510113964_881600_14125, effectsForsig_17_0_5_edc0357_1345510113964_881600_14125);
                Set<Effect> effectsForsig_17_0_5_edc0357_1345510113964_922533_14122 = new HashSet<Effect>();
                effectsForsig_17_0_5_edc0357_1345510113964_922533_14122.add(effect232);
                effects.put((Parameter<?>) sig_17_0_5_edc0357_1345510113964_922533_14122, effectsForsig_17_0_5_edc0357_1345510113964_922533_14122);
            }

            public void init_17_0_5_edc0357_1345510113961_883060_14115Elaborations() {
            }

            public _17_0_5_edc0357_1345510113961_883060_14115() {
                super();
                init_17_0_5_edc0357_1345510113961_883060_14115Members();
                init_17_0_5_edc0357_1345510113961_883060_14115Collections();
                init_17_0_5_edc0357_1345510113961_883060_14115Elaborations();
            }

            public _17_0_5_edc0357_1345510113961_883060_14115(Expression<Integer> _17_0_5_edc0357_1345510113957_678472_14108_endTime, Expression<Power> objectToPass) {
                super();
                init_17_0_5_edc0357_1345510113961_883060_14115Members();
                init_17_0_5_edc0357_1345510113961_883060_14115Collections();
                addDependency(this._17_0_5_edc0357_1345510113957_678472_14108_endTime, _17_0_5_edc0357_1345510113957_678472_14108_endTime);
                addDependency(this.objectToPass, objectToPass);
                init_17_0_5_edc0357_1345510113961_883060_14115Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510113962_750982_14116 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1345510113958_150027_14110_exists = null;

            public IntegerParameter _17_0_5_edc0357_1345510113960_661152_14113_endTime = null;

            public ConstraintExpression constraint236 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510113958_150027_14110_existsDependency = null;

            public ElaborationRule elaborationRule237 = null;

            public void init_17_0_5_edc0357_1345510113962_750982_14116Members() {
                try {
                    _17_0_5_edc0357_1345510113958_150027_14110_exists = new BooleanParameter("_17_0_5_edc0357_1345510113958_150027_14110_exists", this);
                    _17_0_5_edc0357_1345510113960_661152_14113_endTime = new IntegerParameter("_17_0_5_edc0357_1345510113960_661152_14113_endTime", this);
                    constraint236 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510113960_661152_14113_endTime)));
                    _17_0_5_edc0357_1345510113958_150027_14110_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510113958_150027_14110_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510113962_750982_14116Collections() {
                parameters.add(_17_0_5_edc0357_1345510113958_150027_14110_exists);
                parameters.add(_17_0_5_edc0357_1345510113960_661152_14113_endTime);
                constraintExpressions.add(constraint236);
                dependencies.add(_17_0_5_edc0357_1345510113958_150027_14110_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510113962_750982_14116Elaborations() {
                Expression<?>[] arguments237 = new Expression<?>[1];
                arguments237[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition237 = new Expression<Boolean>(_17_0_5_edc0357_1345510113958_150027_14110_exists);
                elaborationRule237 = addElaborationRule(condition237, _17_0_5_edc0357_1345510113544_941994_13762.this, Power._17_0_5_edc0357_1345510113544_941994_13762._17_0_5_edc0357_1345510113958_150027_14110.class, "end_ActivityFinalNode_generate", arguments237);
            }

            public _17_0_5_edc0357_1345510113962_750982_14116() {
                super();
                init_17_0_5_edc0357_1345510113962_750982_14116Members();
                init_17_0_5_edc0357_1345510113962_750982_14116Collections();
                init_17_0_5_edc0357_1345510113962_750982_14116Elaborations();
            }

            public _17_0_5_edc0357_1345510113962_750982_14116(Expression<Integer> _17_0_5_edc0357_1345510113960_661152_14113_endTime) {
                super();
                init_17_0_5_edc0357_1345510113962_750982_14116Members();
                init_17_0_5_edc0357_1345510113962_750982_14116Collections();
                addDependency(this._17_0_5_edc0357_1345510113960_661152_14113_endTime, _17_0_5_edc0357_1345510113960_661152_14113_endTime);
                init_17_0_5_edc0357_1345510113962_750982_14116Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510113963_167569_14117 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1345510113957_678472_14108_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1345510113959_502637_14111_exists = null;

            public BooleanParameter _17_0_5_edc0357_1345510113959_577677_14112_exists = null;

            public ConstraintExpression constraint238 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510113959_502637_14111_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510113959_577677_14112_existsDependency = null;

            public ElaborationRule elaborationRule239 = null;

            public ElaborationRule elaborationRule240 = null;

            public void init_17_0_5_edc0357_1345510113963_167569_14117Members() {
                try {
                    _17_0_5_edc0357_1345510113957_678472_14108_endTime = new IntegerParameter("_17_0_5_edc0357_1345510113957_678472_14108_endTime", this);
                    _17_0_5_edc0357_1345510113959_502637_14111_exists = new BooleanParameter("_17_0_5_edc0357_1345510113959_502637_14111_exists", this);
                    _17_0_5_edc0357_1345510113959_577677_14112_exists = new BooleanParameter("_17_0_5_edc0357_1345510113959_577677_14112_exists", this);
                    constraint238 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510113957_678472_14108_endTime)));
                    _17_0_5_edc0357_1345510113959_502637_14111_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510113959_502637_14111_exists, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510113964_922533_14122, Utils.getMethodForArgTypes("ObjectFlow<Power>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })));
                    _17_0_5_edc0357_1345510113959_577677_14112_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510113959_577677_14112_exists, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510113964_266967_14123, Utils.getMethodForArgTypes("ObjectFlow<Power>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510113963_167569_14117Collections() {
                parameters.add(_17_0_5_edc0357_1345510113957_678472_14108_endTime);
                parameters.add(_17_0_5_edc0357_1345510113959_502637_14111_exists);
                parameters.add(_17_0_5_edc0357_1345510113959_577677_14112_exists);
                constraintExpressions.add(constraint238);
                dependencies.add(_17_0_5_edc0357_1345510113959_502637_14111_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510113959_577677_14112_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510113963_167569_14117Elaborations() {
                Expression<?>[] arguments239 = new Expression<?>[1];
                arguments239[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition239 = new Expression<Boolean>(_17_0_5_edc0357_1345510113959_577677_14112_exists);
                elaborationRule239 = addElaborationRule(condition239, _17_0_5_edc0357_1345510113544_941994_13762.this, Power._17_0_5_edc0357_1345510113544_941994_13762._17_0_5_edc0357_1345510113959_577677_14112.class, "read_load_struct_ReadStructuralFeatureAction_generate", arguments239);
                Expression<?>[] arguments240 = new Expression<?>[1];
                arguments240[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition240 = new Expression<Boolean>(_17_0_5_edc0357_1345510113959_502637_14111_exists);
                elaborationRule240 = addElaborationRule(condition240, _17_0_5_edc0357_1345510113544_941994_13762.this, Power._17_0_5_edc0357_1345510113544_941994_13762._17_0_5_edc0357_1345510113959_502637_14111.class, "read_power_struct_ReadStructuralFeatureAction_generate", arguments240);
            }

            public _17_0_5_edc0357_1345510113963_167569_14117() {
                super();
                init_17_0_5_edc0357_1345510113963_167569_14117Members();
                init_17_0_5_edc0357_1345510113963_167569_14117Collections();
                init_17_0_5_edc0357_1345510113963_167569_14117Elaborations();
            }

            public _17_0_5_edc0357_1345510113963_167569_14117(Expression<Integer> _17_0_5_edc0357_1345510113957_678472_14108_endTime) {
                super();
                init_17_0_5_edc0357_1345510113963_167569_14117Members();
                init_17_0_5_edc0357_1345510113963_167569_14117Collections();
                addDependency(this._17_0_5_edc0357_1345510113957_678472_14108_endTime, _17_0_5_edc0357_1345510113957_678472_14108_endTime);
                init_17_0_5_edc0357_1345510113963_167569_14117Elaborations();
                fixTimeDependencies();
            }
        }

        public _17_0_5_edc0357_1345510113544_941994_13762(Expression<Integer> endTime) {
            super();
            init_17_0_5_edc0357_1345510113544_941994_13762Members();
            init_17_0_5_edc0357_1345510113544_941994_13762Collections();
            removeDependency(this.endTime);
            addDependency(this.endTime, endTime);
            init_17_0_5_edc0357_1345510113544_941994_13762Elaborations();
            fixTimeDependencies();
        }
    }

    public class _17_0_5_edc0357_1345510155557_623464_18238 extends DurativeEvent {

        public Parameter sig_17_0_5_edc0357_1345510113316_772875_13610 = null;

        public Parameter sig_17_0_5_edc0357_1345510113315_214400_13609 = null;

        public IntegerParameter invoke_time = null;

        public Parameter sig_17_0_5_edc0357_1340656734213_808349_11756 = null;

        public ElaborationRule elaborationRule241 = null;

        public void init_17_0_5_edc0357_1345510155557_623464_18238Members() {
            try {
                sig_17_0_5_edc0357_1345510113316_772875_13610 = new Parameter("sig_17_0_5_edc0357_1345510113316_772875_13610", null, new ObjectFlow("sig_17_0_5_edc0357_1345510113316_772875_13610"), this);
                sig_17_0_5_edc0357_1345510113315_214400_13609 = new Parameter("sig_17_0_5_edc0357_1345510113315_214400_13609", null, new ObjectFlow("sig_17_0_5_edc0357_1345510113315_214400_13609"), this);
                invoke_time = new IntegerParameter("invoke_time", this);
                sig_17_0_5_edc0357_1340656734213_808349_11756 = new Parameter("sig_17_0_5_edc0357_1340656734213_808349_11756", null, new ObjectFlow("sig_17_0_5_edc0357_1340656734213_808349_11756"), this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void init_17_0_5_edc0357_1345510155557_623464_18238Collections() {
            parameters.add(sig_17_0_5_edc0357_1345510113316_772875_13610);
            parameters.add(sig_17_0_5_edc0357_1345510113315_214400_13609);
            parameters.add(invoke_time);
            parameters.add(sig_17_0_5_edc0357_1340656734213_808349_11756);
        }

        public void init_17_0_5_edc0357_1345510155557_623464_18238Elaborations() {
            Expression<?>[] arguments241 = new Expression<?>[1];
            arguments241[0] = new Expression<Integer>(invoke_time);
            Expression<Boolean> condition241 = new Expression<Boolean>(true);
            elaborationRule241 = addElaborationRule(condition241, _17_0_5_edc0357_1345510155557_623464_18238.this, Power._17_0_5_edc0357_1345510155557_623464_18238._17_0_5_edc0357_1345510189131_823332_18260.class, "_InitialNode_PowerCB", arguments241);
        }

        public _17_0_5_edc0357_1345510155557_623464_18238() {
            super();
            init_17_0_5_edc0357_1345510155557_623464_18238Members();
            init_17_0_5_edc0357_1345510155557_623464_18238Collections();
            init_17_0_5_edc0357_1345510155557_623464_18238Elaborations();
        }

        public class _17_0_5_edc0357_1345510189131_823332_18260 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1345510193521_82099_18271_exists = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510193521_82099_18271_existsDependency = null;

            public ElaborationRule elaborationRule242 = null;

            public void init_17_0_5_edc0357_1345510189131_823332_18260Members() {
                try {
                    _17_0_5_edc0357_1345510193521_82099_18271_exists = new BooleanParameter("_17_0_5_edc0357_1345510193521_82099_18271_exists", this);
                    _17_0_5_edc0357_1345510193521_82099_18271_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510193521_82099_18271_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510189131_823332_18260Collections() {
                parameters.add(_17_0_5_edc0357_1345510193521_82099_18271_exists);
                dependencies.add(_17_0_5_edc0357_1345510193521_82099_18271_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510189131_823332_18260Elaborations() {
                Expression<?>[] arguments242 = new Expression<?>[1];
                arguments242[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition242 = new Expression<Boolean>(_17_0_5_edc0357_1345510193521_82099_18271_exists);
                elaborationRule242 = addElaborationRule(condition242, _17_0_5_edc0357_1345510155557_623464_18238.this, Power._17_0_5_edc0357_1345510155557_623464_18238._17_0_5_edc0357_1345510193521_82099_18271.class, "_CallBehaviorAction_PowerCB", arguments242);
            }

            public _17_0_5_edc0357_1345510189131_823332_18260() {
                super();
                init_17_0_5_edc0357_1345510189131_823332_18260Members();
                init_17_0_5_edc0357_1345510189131_823332_18260Collections();
                init_17_0_5_edc0357_1345510189131_823332_18260Elaborations();
            }

            public _17_0_5_edc0357_1345510189131_823332_18260(Expression<Integer> startTime) {
                super();
                init_17_0_5_edc0357_1345510189131_823332_18260Members();
                init_17_0_5_edc0357_1345510189131_823332_18260Collections();
                removeDependency(this.startTime);
                addDependency(this.startTime, startTime);
                init_17_0_5_edc0357_1345510189131_823332_18260Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510193521_82099_18271 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1348011599659_253029_14403_exists = null;

            public IntegerParameter _17_0_5_edc0357_1345510189131_823332_18260_endTime = null;

            public IntegerParameter cba_endTime = null;

            public ConstraintExpression constraint243 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1348011599659_253029_14403_existsDependency = null;

            public ElaborationRule elaborationRule244 = null;

            public ElaborationRule elaborationRule245 = null;

            public void init_17_0_5_edc0357_1345510193521_82099_18271Members() {
                try {
                    _17_0_5_edc0357_1348011599659_253029_14403_exists = new BooleanParameter("_17_0_5_edc0357_1348011599659_253029_14403_exists", this);
                    _17_0_5_edc0357_1345510189131_823332_18260_endTime = new IntegerParameter("_17_0_5_edc0357_1345510189131_823332_18260_endTime", this);
                    cba_endTime = new IntegerParameter("cba_endTime", this);
                    constraint243 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510189131_823332_18260_endTime)));
                    _17_0_5_edc0357_1348011599659_253029_14403_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1348011599659_253029_14403_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510193521_82099_18271Collections() {
                parameters.add(_17_0_5_edc0357_1348011599659_253029_14403_exists);
                parameters.add(_17_0_5_edc0357_1345510189131_823332_18260_endTime);
                parameters.add(cba_endTime);
                constraintExpressions.add(constraint243);
                dependencies.add(_17_0_5_edc0357_1348011599659_253029_14403_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510193521_82099_18271Elaborations() {
                Expression<?>[] arguments244 = new Expression<?>[1];
                arguments244[0] = new Expression<Integer>(cba_endTime);
                Expression<Boolean> condition244 = new Expression<Boolean>(true);
                elaborationRule244 = addElaborationRule(condition244, Power.this, Power._17_0_5_edc0357_1345510113542_666806_13761.class, "intialize_Activity_Power", arguments244);
                Expression<?>[] arguments245 = new Expression<?>[1];
                arguments245[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition245 = new Expression<Boolean>(_17_0_5_edc0357_1348011599659_253029_14403_exists);
                elaborationRule245 = addElaborationRule(condition245, _17_0_5_edc0357_1345510155557_623464_18238.this, Power._17_0_5_edc0357_1345510155557_623464_18238._17_0_5_edc0357_1348011599659_253029_14403.class, "_AcceptEventAction_PowerCB", arguments245);
            }

            public _17_0_5_edc0357_1345510193521_82099_18271() {
                super();
                init_17_0_5_edc0357_1345510193521_82099_18271Members();
                init_17_0_5_edc0357_1345510193521_82099_18271Collections();
                init_17_0_5_edc0357_1345510193521_82099_18271Elaborations();
            }

            public _17_0_5_edc0357_1345510193521_82099_18271(Expression<Integer> _17_0_5_edc0357_1345510189131_823332_18260_endTime) {
                super();
                init_17_0_5_edc0357_1345510193521_82099_18271Members();
                init_17_0_5_edc0357_1345510193521_82099_18271Collections();
                addDependency(this._17_0_5_edc0357_1345510189131_823332_18260_endTime, _17_0_5_edc0357_1345510189131_823332_18260_endTime);
                init_17_0_5_edc0357_1345510193521_82099_18271Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510201034_362860_18288 extends DurativeEvent {

            //public BooleanParameter _17_0_5_edc0357_1345510746643_574371_18524_exists = null;

            public BooleanParameter _17_0_5_edc0357_1345510646254_583840_18431_exists = null;

            public BooleanParameter _17_0_5_edc0357_1345510644119_150669_18425_exists = null;

            public BooleanParameter _17_0_5_edc0357_1345510642223_296237_18420_exists = null;

            public IntegerParameter _17_0_5_edc0357_1348011599659_253029_14403_endTime = null;

            public ConstraintExpression constraint246 = null;

            //public Dependency< Boolean > _17_0_5_edc0357_1345510746643_574371_18524_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510646254_583840_18431_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510644119_150669_18425_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510642223_296237_18420_existsDependency = null;

            public ElaborationRule elaborationRule247 = null;

            public ElaborationRule elaborationRule248 = null;

            public ElaborationRule elaborationRule249 = null;

            public ElaborationRule elaborationRule250 = null;

            public void init_17_0_5_edc0357_1345510201034_362860_18288Members() {
                try {
                    //_17_0_5_edc0357_1345510746643_574371_18524_exists = new BooleanParameter("_17_0_5_edc0357_1345510746643_574371_18524_exists", this);
                    _17_0_5_edc0357_1345510646254_583840_18431_exists = new BooleanParameter("_17_0_5_edc0357_1345510646254_583840_18431_exists", this);
                    _17_0_5_edc0357_1345510644119_150669_18425_exists = new BooleanParameter("_17_0_5_edc0357_1345510644119_150669_18425_exists", this);
                    _17_0_5_edc0357_1345510642223_296237_18420_exists = new BooleanParameter("_17_0_5_edc0357_1345510642223_296237_18420_exists", this);
                    _17_0_5_edc0357_1348011599659_253029_14403_endTime = new IntegerParameter("_17_0_5_edc0357_1348011599659_253029_14403_endTime", this);
                    constraint246 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1348011599659_253029_14403_endTime)));
                    //_17_0_5_edc0357_1345510746643_574371_18524_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510746643_574371_18524_exists, new Expression(new FunctionCall((Object) q_Power_stop, Utils.getMethodForArgTypes("Power._17_0_5_edc0357_1345510155557_623464_18238._17_0_5_edc0357_1345510201034_362860_18288", "generated", "hasStuff"), new Object[] { new Expression<Integer>(endTime) })));
                    _17_0_5_edc0357_1345510646254_583840_18431_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510646254_583840_18431_exists, new Expression<Boolean>(true));
                    _17_0_5_edc0357_1345510644119_150669_18425_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510644119_150669_18425_exists, new Expression<Boolean>(true));
                    _17_0_5_edc0357_1345510642223_296237_18420_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510642223_296237_18420_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510201034_362860_18288Collections() {
                //parameters.add(_17_0_5_edc0357_1345510746643_574371_//_exists);
                parameters.add(_17_0_5_edc0357_1345510646254_583840_18431_exists);
                parameters.add(_17_0_5_edc0357_1345510644119_150669_18425_exists);
                parameters.add(_17_0_5_edc0357_1345510642223_296237_18420_exists);
                parameters.add(_17_0_5_edc0357_1348011599659_253029_14403_endTime);
                constraintExpressions.add(constraint246);
                //dependencies.add(_17_0_5_edc0357_1345510746643_574371_18524_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510646254_583840_18431_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510644119_150669_18425_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510642223_296237_18420_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510201034_362860_18288Elaborations() {
                Expression<?>[] arguments247 = new Expression<?>[1];
                arguments247[0] = new Expression<Integer>(endTime);
                //Expression<Boolean> condition247 = new Expression<Boolean>(_17_0_5_edc0357_1345510746643_574371_18524_exists);
                //elaborationRule247 = addElaborationRule(condition247, _17_0_5_edc0357_1345510155557_623464_18238.this, Power._17_0_5_edc0357_1345510155557_623464_18238._17_0_5_edc0357_1345510746643_574371_18524.class, "_AcceptEventAction_PowerCB", arguments247);
                Expression<?>[] arguments248 = new Expression<?>[1];
                arguments248[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition248 = new Expression<Boolean>(_17_0_5_edc0357_1345510644119_150669_18425_exists);
                elaborationRule248 = addElaborationRule(condition248, _17_0_5_edc0357_1345510155557_623464_18238.this, Power._17_0_5_edc0357_1345510155557_623464_18238._17_0_5_edc0357_1345510644119_150669_18425.class, "_MergeNode_PowerCB", arguments248);
                Expression<?>[] arguments249 = new Expression<?>[1];
                arguments249[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition249 = new Expression<Boolean>(_17_0_5_edc0357_1345510646254_583840_18431_exists);
                elaborationRule249 = addElaborationRule(condition249, _17_0_5_edc0357_1345510155557_623464_18238.this, Power._17_0_5_edc0357_1345510155557_623464_18238._17_0_5_edc0357_1345510646254_583840_18431.class, "_MergeNode_PowerCB", arguments249);
                Expression<?>[] arguments250 = new Expression<?>[1];
                arguments250[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition250 = new Expression<Boolean>(_17_0_5_edc0357_1345510642223_296237_18420_exists);
                elaborationRule250 = addElaborationRule(condition250, _17_0_5_edc0357_1345510155557_623464_18238.this, Power._17_0_5_edc0357_1345510155557_623464_18238._17_0_5_edc0357_1345510642223_296237_18420.class, "_MergeNode_PowerCB", arguments250);
            }

            public _17_0_5_edc0357_1345510201034_362860_18288() {
                super();
                init_17_0_5_edc0357_1345510201034_362860_18288Members();
                init_17_0_5_edc0357_1345510201034_362860_18288Collections();
                init_17_0_5_edc0357_1345510201034_362860_18288Elaborations();
            }

            public _17_0_5_edc0357_1345510201034_362860_18288(Expression<Integer> _17_0_5_edc0357_1348011599659_253029_14403_endTime) {
                super();
                init_17_0_5_edc0357_1345510201034_362860_18288Members();
                init_17_0_5_edc0357_1345510201034_362860_18288Collections();
                addDependency(this._17_0_5_edc0357_1348011599659_253029_14403_endTime, _17_0_5_edc0357_1348011599659_253029_14403_endTime);
                init_17_0_5_edc0357_1345510201034_362860_18288Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510469459_873081_18318 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1345763165599_575948_13389_exists = null;

            public IntegerParameter _17_0_5_edc0357_1345510644119_150669_18425_endTime = null;

            public Parameter _17_0_5_edc0357_1345756714103_564856_12930 = null;

            public ConstraintExpression constraint251 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345763165599_575948_13389_existsDependency = null;

            public Dependency< Power_System.SignalchangeLoadValue > _17_0_5_edc0357_1345756714103_564856_12930Dependency = null;

            public ElaborationRule elaborationRule252 = null;

            public void init_17_0_5_edc0357_1345510469459_873081_18318Members() {
                try {
                    _17_0_5_edc0357_1345763165599_575948_13389_exists = new BooleanParameter("_17_0_5_edc0357_1345763165599_575948_13389_exists", this);
                    _17_0_5_edc0357_1345510644119_150669_18425_endTime = new IntegerParameter("_17_0_5_edc0357_1345510644119_150669_18425_endTime", this);
                    _17_0_5_edc0357_1345756714103_564856_12930 = new Parameter("_17_0_5_edc0357_1345756714103_564856_12930", null, null, this);
                    constraint251 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510644119_150669_18425_endTime)));
                    _17_0_5_edc0357_1345763165599_575948_13389_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345763165599_575948_13389_exists, new Expression<Boolean>(true));
                    _17_0_5_edc0357_1345756714103_564856_12930Dependency = new Dependency<Power_System.SignalchangeLoadValue>(_17_0_5_edc0357_1345756714103_564856_12930, new Expression(new FunctionCall((Object) q_Power_changeLoadValue, Utils.getMethodForArgTypes("ObjectFlow<Power_System.SignalchangeLoadValue>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510469459_873081_18318Collections() {
                parameters.add(_17_0_5_edc0357_1345763165599_575948_13389_exists);
                parameters.add(_17_0_5_edc0357_1345510644119_150669_18425_endTime);
                parameters.add(_17_0_5_edc0357_1345756714103_564856_12930);
                constraintExpressions.add(constraint251);
                dependencies.add(_17_0_5_edc0357_1345763165599_575948_13389_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345756714103_564856_12930Dependency);
            }

            public void init_17_0_5_edc0357_1345510469459_873081_18318Elaborations() {
                Expression<?>[] arguments252 = new Expression<?>[2];
                arguments252[0] = new Expression<Integer>(endTime);
                arguments252[1] = new Expression<Power_System.SignalchangeLoadValue>(_17_0_5_edc0357_1345756714103_564856_12930);
                Expression<Boolean> condition252 = new Expression<Boolean>(_17_0_5_edc0357_1345763165599_575948_13389_exists);
                elaborationRule252 = addElaborationRule(condition252, _17_0_5_edc0357_1345510155557_623464_18238.this, Power._17_0_5_edc0357_1345510155557_623464_18238._17_0_5_edc0357_1345763165599_575948_13389.class, "readLoadFromSignal_ReadStructuralFeatureAction_PowerCB", arguments252);
            }

            public _17_0_5_edc0357_1345510469459_873081_18318() {
                super();
                init_17_0_5_edc0357_1345510469459_873081_18318Members();
                init_17_0_5_edc0357_1345510469459_873081_18318Collections();
                init_17_0_5_edc0357_1345510469459_873081_18318Elaborations();
            }

            public _17_0_5_edc0357_1345510469459_873081_18318(Expression<Integer> _17_0_5_edc0357_1345510644119_150669_18425_endTime) {
                super();
                init_17_0_5_edc0357_1345510469459_873081_18318Members();
                init_17_0_5_edc0357_1345510469459_873081_18318Collections();
                addDependency(this._17_0_5_edc0357_1345510644119_150669_18425_endTime, _17_0_5_edc0357_1345510644119_150669_18425_endTime);
                init_17_0_5_edc0357_1345510469459_873081_18318Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510471635_699632_18330 extends DurativeEvent {

            public Parameter _17_0_5_edc0357_1345756459554_558513_12886 = null;

            public BooleanParameter _17_0_5_edc0357_1345763300820_765750_13425_exists = null;

            public IntegerParameter _17_0_5_edc0357_1345510642223_296237_18420_endTime = null;

            public ConstraintExpression constraint253 = null;

            public Dependency< Power_System.SignalchangeGenerationValue > _17_0_5_edc0357_1345756459554_558513_12886Dependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345763300820_765750_13425_existsDependency = null;

            public ElaborationRule elaborationRule254 = null;

            public void init_17_0_5_edc0357_1345510471635_699632_18330Members() {
                try {
                    _17_0_5_edc0357_1345756459554_558513_12886 = new Parameter("_17_0_5_edc0357_1345756459554_558513_12886", null, null, this);
                    _17_0_5_edc0357_1345763300820_765750_13425_exists = new BooleanParameter("_17_0_5_edc0357_1345763300820_765750_13425_exists", this);
                    _17_0_5_edc0357_1345510642223_296237_18420_endTime = new IntegerParameter("_17_0_5_edc0357_1345510642223_296237_18420_endTime", this);
                    constraint253 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510642223_296237_18420_endTime)));
                    _17_0_5_edc0357_1345756459554_558513_12886Dependency = new Dependency<Power_System.SignalchangeGenerationValue>(_17_0_5_edc0357_1345756459554_558513_12886, new Expression(new FunctionCall((Object) q_Power_changeGenerationValue, Utils.getMethodForArgTypes("ObjectFlow<Power_System.SignalchangeGenerationValue>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1345763300820_765750_13425_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345763300820_765750_13425_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510471635_699632_18330Collections() {
                parameters.add(_17_0_5_edc0357_1345756459554_558513_12886);
                parameters.add(_17_0_5_edc0357_1345763300820_765750_13425_exists);
                parameters.add(_17_0_5_edc0357_1345510642223_296237_18420_endTime);
                constraintExpressions.add(constraint253);
                dependencies.add(_17_0_5_edc0357_1345756459554_558513_12886Dependency);
                dependencies.add(_17_0_5_edc0357_1345763300820_765750_13425_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510471635_699632_18330Elaborations() {
                Expression<?>[] arguments254 = new Expression<?>[2];
                arguments254[0] = new Expression<Integer>(endTime);
                arguments254[1] = new Expression<Power_System.SignalchangeGenerationValue>(_17_0_5_edc0357_1345756459554_558513_12886);
                Expression<Boolean> condition254 = new Expression<Boolean>(_17_0_5_edc0357_1345763300820_765750_13425_exists);
                elaborationRule254 = addElaborationRule(condition254, _17_0_5_edc0357_1345510155557_623464_18238.this, Power._17_0_5_edc0357_1345510155557_623464_18238._17_0_5_edc0357_1345763300820_765750_13425.class, "readNewGenVal_ReadStructuralFeatureAction_PowerCB", arguments254);
            }

            public _17_0_5_edc0357_1345510471635_699632_18330() {
                super();
                init_17_0_5_edc0357_1345510471635_699632_18330Members();
                init_17_0_5_edc0357_1345510471635_699632_18330Collections();
                init_17_0_5_edc0357_1345510471635_699632_18330Elaborations();
            }

            public _17_0_5_edc0357_1345510471635_699632_18330(Expression<Integer> _17_0_5_edc0357_1345510642223_296237_18420_endTime) {
                super();
                init_17_0_5_edc0357_1345510471635_699632_18330Members();
                init_17_0_5_edc0357_1345510471635_699632_18330Collections();
                addDependency(this._17_0_5_edc0357_1345510642223_296237_18420_endTime, _17_0_5_edc0357_1345510642223_296237_18420_endTime);
                init_17_0_5_edc0357_1345510471635_699632_18330Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510492737_394609_18349 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1345756720553_849612_12938 = null;

            public BooleanParameter _17_0_5_edc0357_1345510644119_150669_18425_exists = null;

            public IntegerParameter _17_0_5_edc0357_1345763165599_575948_13389_endTime = null;

            public IntegerParameter cba_endTime = null;

            public ConstraintExpression constraint255 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510644119_150669_18425_existsDependency = null;

            public ElaborationRule elaborationRule256 = null;

            public ElaborationRule elaborationRule257 = null;

            public void init_17_0_5_edc0357_1345510492737_394609_18349Members() {
                try {
                    _17_0_5_edc0357_1345756720553_849612_12938 = new IntegerParameter("_17_0_5_edc0357_1345756720553_849612_12938", this);
                    _17_0_5_edc0357_1345510644119_150669_18425_exists = new BooleanParameter("_17_0_5_edc0357_1345510644119_150669_18425_exists", this);
                    _17_0_5_edc0357_1345763165599_575948_13389_endTime = new IntegerParameter("_17_0_5_edc0357_1345763165599_575948_13389_endTime", this);
                    cba_endTime = new IntegerParameter("cba_endTime", this);
                    constraint255 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345763165599_575948_13389_endTime)));
                    _17_0_5_edc0357_1345510644119_150669_18425_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510644119_150669_18425_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510492737_394609_18349Collections() {
                parameters.add(_17_0_5_edc0357_1345756720553_849612_12938);
                parameters.add(_17_0_5_edc0357_1345510644119_150669_18425_exists);
                parameters.add(_17_0_5_edc0357_1345763165599_575948_13389_endTime);
                parameters.add(cba_endTime);
                constraintExpressions.add(constraint255);
                dependencies.add(_17_0_5_edc0357_1345510644119_150669_18425_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510492737_394609_18349Elaborations() {
                Expression<?>[] arguments256 = new Expression<?>[1];
                arguments256[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition256 = new Expression<Boolean>(_17_0_5_edc0357_1345510644119_150669_18425_exists);
                elaborationRule256 = addElaborationRule(condition256, _17_0_5_edc0357_1345510155557_623464_18238.this, Power._17_0_5_edc0357_1345510155557_623464_18238._17_0_5_edc0357_1345510644119_150669_18425.class, "_MergeNode_PowerCB", arguments256);
                Expression<?>[] arguments257 = new Expression<?>[2];
                arguments257[0] = new Expression<Integer>(cba_endTime);
                arguments257[1] = new Expression<Integer>(_17_0_5_edc0357_1345756720553_849612_12938);
                Expression<Boolean> condition257 = new Expression<Boolean>(true);
                elaborationRule257 = addElaborationRule(condition257, Power.this, Power._17_0_5_edc0357_1345510113541_858357_13760.class, "changeLoadValue_Activity_Power", arguments257);
            }

            public _17_0_5_edc0357_1345510492737_394609_18349() {
                super();
                init_17_0_5_edc0357_1345510492737_394609_18349Members();
                init_17_0_5_edc0357_1345510492737_394609_18349Collections();
                init_17_0_5_edc0357_1345510492737_394609_18349Elaborations();
            }

            public _17_0_5_edc0357_1345510492737_394609_18349(Expression<Integer> _17_0_5_edc0357_1345763165599_575948_13389_endTime, Expression<Integer> _17_0_5_edc0357_1345756720553_849612_12938) {
                super();
                init_17_0_5_edc0357_1345510492737_394609_18349Members();
                init_17_0_5_edc0357_1345510492737_394609_18349Collections();
                addDependency(this._17_0_5_edc0357_1345763165599_575948_13389_endTime, _17_0_5_edc0357_1345763165599_575948_13389_endTime);
                addDependency(this._17_0_5_edc0357_1345756720553_849612_12938, _17_0_5_edc0357_1345756720553_849612_12938);
                init_17_0_5_edc0357_1345510492737_394609_18349Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510501544_90462_18367 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1345756501917_658961_12894 = null;

            public IntegerParameter _17_0_5_edc0357_1345763300820_765750_13425_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1345510642223_296237_18420_exists = null;

            public IntegerParameter cba_endTime = null;

            public ConstraintExpression constraint258 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510642223_296237_18420_existsDependency = null;

            public ElaborationRule elaborationRule259 = null;

            public ElaborationRule elaborationRule260 = null;

            public void init_17_0_5_edc0357_1345510501544_90462_18367Members() {
                try {
                    _17_0_5_edc0357_1345756501917_658961_12894 = new IntegerParameter("_17_0_5_edc0357_1345756501917_658961_12894", this);
                    _17_0_5_edc0357_1345763300820_765750_13425_endTime = new IntegerParameter("_17_0_5_edc0357_1345763300820_765750_13425_endTime", this);
                    _17_0_5_edc0357_1345510642223_296237_18420_exists = new BooleanParameter("_17_0_5_edc0357_1345510642223_296237_18420_exists", this);
                    cba_endTime = new IntegerParameter("cba_endTime", this);
                    constraint258 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345763300820_765750_13425_endTime)));
                    _17_0_5_edc0357_1345510642223_296237_18420_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510642223_296237_18420_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510501544_90462_18367Collections() {
                parameters.add(_17_0_5_edc0357_1345756501917_658961_12894);
                parameters.add(_17_0_5_edc0357_1345763300820_765750_13425_endTime);
                parameters.add(_17_0_5_edc0357_1345510642223_296237_18420_exists);
                parameters.add(cba_endTime);
                constraintExpressions.add(constraint258);
                dependencies.add(_17_0_5_edc0357_1345510642223_296237_18420_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510501544_90462_18367Elaborations() {
                Expression<?>[] arguments259 = new Expression<?>[2];
                arguments259[0] = new Expression<Integer>(cba_endTime);
                arguments259[1] = new Expression<Integer>(_17_0_5_edc0357_1345756501917_658961_12894);
                Expression<Boolean> condition259 = new Expression<Boolean>(true);
                elaborationRule259 = addElaborationRule(condition259, Power.this, Power._17_0_5_edc0357_1345510113540_247161_13759.class, "changeGenerationValue_Activity_Power", arguments259);
                Expression<?>[] arguments260 = new Expression<?>[1];
                arguments260[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition260 = new Expression<Boolean>(_17_0_5_edc0357_1345510642223_296237_18420_exists);
                elaborationRule260 = addElaborationRule(condition260, _17_0_5_edc0357_1345510155557_623464_18238.this, Power._17_0_5_edc0357_1345510155557_623464_18238._17_0_5_edc0357_1345510642223_296237_18420.class, "_MergeNode_PowerCB", arguments260);
            }

            public _17_0_5_edc0357_1345510501544_90462_18367() {
                super();
                init_17_0_5_edc0357_1345510501544_90462_18367Members();
                init_17_0_5_edc0357_1345510501544_90462_18367Collections();
                init_17_0_5_edc0357_1345510501544_90462_18367Elaborations();
            }

            public _17_0_5_edc0357_1345510501544_90462_18367(Expression<Integer> _17_0_5_edc0357_1345763300820_765750_13425_endTime, Expression<Integer> _17_0_5_edc0357_1345756501917_658961_12894) {
                super();
                init_17_0_5_edc0357_1345510501544_90462_18367Members();
                init_17_0_5_edc0357_1345510501544_90462_18367Collections();
                addDependency(this._17_0_5_edc0357_1345763300820_765750_13425_endTime, _17_0_5_edc0357_1345763300820_765750_13425_endTime);
                addDependency(this._17_0_5_edc0357_1345756501917_658961_12894, _17_0_5_edc0357_1345756501917_658961_12894);
                init_17_0_5_edc0357_1345510501544_90462_18367Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510532104_703303_18387 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1345510646254_583840_18431_endTime = null;

            public IntegerParameter cba_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1348011630902_591447_14429_exists = null;

            public ConstraintExpression constraint261 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1348011630902_591447_14429_existsDependency = null;

            public ElaborationRule elaborationRule262 = null;

            public ElaborationRule elaborationRule263 = null;

            public void init_17_0_5_edc0357_1345510532104_703303_18387Members() {
                try {
                    _17_0_5_edc0357_1345510646254_583840_18431_endTime = new IntegerParameter("_17_0_5_edc0357_1345510646254_583840_18431_endTime", this);
                    cba_endTime = new IntegerParameter("cba_endTime", this);
                    _17_0_5_edc0357_1348011630902_591447_14429_exists = new BooleanParameter("_17_0_5_edc0357_1348011630902_591447_14429_exists", this);
                    constraint261 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510646254_583840_18431_endTime)));
                    _17_0_5_edc0357_1348011630902_591447_14429_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1348011630902_591447_14429_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510532104_703303_18387Collections() {
                parameters.add(_17_0_5_edc0357_1345510646254_583840_18431_endTime);
                parameters.add(cba_endTime);
                parameters.add(_17_0_5_edc0357_1348011630902_591447_14429_exists);
                constraintExpressions.add(constraint261);
                dependencies.add(_17_0_5_edc0357_1348011630902_591447_14429_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510532104_703303_18387Elaborations() {
                Expression<?>[] arguments262 = new Expression<?>[1];
                arguments262[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition262 = new Expression<Boolean>(_17_0_5_edc0357_1348011630902_591447_14429_exists);
                elaborationRule262 = addElaborationRule(condition262, _17_0_5_edc0357_1345510155557_623464_18238.this, Power._17_0_5_edc0357_1345510155557_623464_18238._17_0_5_edc0357_1348011630902_591447_14429.class, "_AcceptEventAction_PowerCB", arguments262);
                Expression<?>[] arguments263 = new Expression<?>[1];
                arguments263[0] = new Expression<Integer>(cba_endTime);
                Expression<Boolean> condition263 = new Expression<Boolean>(true);
                elaborationRule263 = addElaborationRule(condition263, Power.this, Power._17_0_5_edc0357_1345510113544_941994_13762.class, "generate_Activity_Power", arguments263);
            }

            public _17_0_5_edc0357_1345510532104_703303_18387() {
                super();
                init_17_0_5_edc0357_1345510532104_703303_18387Members();
                init_17_0_5_edc0357_1345510532104_703303_18387Collections();
                init_17_0_5_edc0357_1345510532104_703303_18387Elaborations();
            }

            public _17_0_5_edc0357_1345510532104_703303_18387(Expression<Integer> _17_0_5_edc0357_1345510646254_583840_18431_endTime) {
                super();
                init_17_0_5_edc0357_1345510532104_703303_18387Members();
                init_17_0_5_edc0357_1345510532104_703303_18387Collections();
                addDependency(this._17_0_5_edc0357_1345510646254_583840_18431_endTime, _17_0_5_edc0357_1345510646254_583840_18431_endTime);
                init_17_0_5_edc0357_1345510532104_703303_18387Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510642223_296237_18420 extends DurativeEvent {

            public IntegerParameter invoker_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1345510471635_699632_18330_exists = null;

            public ConstraintExpression constraint264 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510471635_699632_18330_existsDependency = null;

            public ElaborationRule elaborationRule265 = null;

            public void init_17_0_5_edc0357_1345510642223_296237_18420Members() {
                try {
                    invoker_endTime = new IntegerParameter("invoker_endTime", this);
                    _17_0_5_edc0357_1345510471635_699632_18330_exists = new BooleanParameter("_17_0_5_edc0357_1345510471635_699632_18330_exists", this);
                    constraint264 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(invoker_endTime)));
                    _17_0_5_edc0357_1345510471635_699632_18330_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510471635_699632_18330_exists, new Expression(new FunctionCall((Object) q_Power_changeGenerationValue, Utils.getMethodForArgTypes("ObjectFlow<Power_System.SignalchangeGenerationValue>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510642223_296237_18420Collections() {
                parameters.add(invoker_endTime);
                parameters.add(_17_0_5_edc0357_1345510471635_699632_18330_exists);
                constraintExpressions.add(constraint264);
                dependencies.add(_17_0_5_edc0357_1345510471635_699632_18330_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510642223_296237_18420Elaborations() {
                Expression<?>[] arguments265 = new Expression<?>[1];
                arguments265[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition265 = new Expression<Boolean>(_17_0_5_edc0357_1345510471635_699632_18330_exists);
                elaborationRule265 = addElaborationRule(condition265, _17_0_5_edc0357_1345510155557_623464_18238.this, Power._17_0_5_edc0357_1345510155557_623464_18238._17_0_5_edc0357_1345510471635_699632_18330.class, "cvg_sig_AcceptEventAction_PowerCB", arguments265);
            }

            public _17_0_5_edc0357_1345510642223_296237_18420() {
                super();
                init_17_0_5_edc0357_1345510642223_296237_18420Members();
                init_17_0_5_edc0357_1345510642223_296237_18420Collections();
                init_17_0_5_edc0357_1345510642223_296237_18420Elaborations();
            }

            public _17_0_5_edc0357_1345510642223_296237_18420(Expression<Integer> invoker_endTime) {
                super();
                init_17_0_5_edc0357_1345510642223_296237_18420Members();
                init_17_0_5_edc0357_1345510642223_296237_18420Collections();
                addDependency(this.invoker_endTime, invoker_endTime);
                init_17_0_5_edc0357_1345510642223_296237_18420Elaborations();
                fixTimeDependencies();
            }

           
        }

        public class _17_0_5_edc0357_1345510644119_150669_18425 extends DurativeEvent {

            public IntegerParameter invoker_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1345510469459_873081_18318_exists = null;

            public ConstraintExpression constraint266 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510469459_873081_18318_existsDependency = null;

            public ElaborationRule elaborationRule267 = null;

            public void init_17_0_5_edc0357_1345510644119_150669_18425Members() {
                try {
                    invoker_endTime = new IntegerParameter("invoker_endTime", this);
                    _17_0_5_edc0357_1345510469459_873081_18318_exists = new BooleanParameter("_17_0_5_edc0357_1345510469459_873081_18318_exists", this);
                    constraint266 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(invoker_endTime)));
                    _17_0_5_edc0357_1345510469459_873081_18318_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510469459_873081_18318_exists, new Expression(new FunctionCall((Object) q_Power_changeLoadValue, Utils.getMethodForArgTypes("ObjectFlow<Power_System.SignalchangeLoadValue>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510644119_150669_18425Collections() {
                parameters.add(invoker_endTime);
                parameters.add(_17_0_5_edc0357_1345510469459_873081_18318_exists);
                constraintExpressions.add(constraint266);
                dependencies.add(_17_0_5_edc0357_1345510469459_873081_18318_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510644119_150669_18425Elaborations() {
                Expression<?>[] arguments267 = new Expression<?>[1];
                arguments267[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition267 = new Expression<Boolean>(_17_0_5_edc0357_1345510469459_873081_18318_exists);
                elaborationRule267 = addElaborationRule(condition267, _17_0_5_edc0357_1345510155557_623464_18238.this, Power._17_0_5_edc0357_1345510155557_623464_18238._17_0_5_edc0357_1345510469459_873081_18318.class, "clv_sig_AcceptEventAction_PowerCB", arguments267);
            }

            public _17_0_5_edc0357_1345510644119_150669_18425() {
                super();
                init_17_0_5_edc0357_1345510644119_150669_18425Members();
                init_17_0_5_edc0357_1345510644119_150669_18425Collections();
                init_17_0_5_edc0357_1345510644119_150669_18425Elaborations();
            }

            public _17_0_5_edc0357_1345510644119_150669_18425(Expression<Integer> invoker_endTime) {
                super();
                init_17_0_5_edc0357_1345510644119_150669_18425Members();
                init_17_0_5_edc0357_1345510644119_150669_18425Collections();
                addDependency(this.invoker_endTime, invoker_endTime);
                init_17_0_5_edc0357_1345510644119_150669_18425Elaborations();
                fixTimeDependencies();
            }

        }

        public class _17_0_5_edc0357_1345510646254_583840_18431 extends DurativeEvent {

            public IntegerParameter invoker_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1345510532104_703303_18387_exists = null;

            public ConstraintExpression constraint268 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510532104_703303_18387_existsDependency = null;

            public ElaborationRule elaborationRule269 = null;

            public void init_17_0_5_edc0357_1345510646254_583840_18431Members() {
                try {
                    invoker_endTime = new IntegerParameter("invoker_endTime", this);
                    _17_0_5_edc0357_1345510532104_703303_18387_exists = new BooleanParameter("_17_0_5_edc0357_1345510532104_703303_18387_exists", this);
                    constraint268 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(invoker_endTime)));
                    _17_0_5_edc0357_1345510532104_703303_18387_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510532104_703303_18387_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510646254_583840_18431Collections() {
                parameters.add(invoker_endTime);
                parameters.add(_17_0_5_edc0357_1345510532104_703303_18387_exists);
                constraintExpressions.add(constraint268);
                dependencies.add(_17_0_5_edc0357_1345510532104_703303_18387_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510646254_583840_18431Elaborations() {
                Expression<?>[] arguments269 = new Expression<?>[1];
                arguments269[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition269 = new Expression<Boolean>(_17_0_5_edc0357_1345510532104_703303_18387_exists);
                elaborationRule269 = addElaborationRule(condition269, _17_0_5_edc0357_1345510155557_623464_18238.this, Power._17_0_5_edc0357_1345510155557_623464_18238._17_0_5_edc0357_1345510532104_703303_18387.class, "_CallBehaviorAction_PowerCB", arguments269);
            }

            public _17_0_5_edc0357_1345510646254_583840_18431() {
                super();
                init_17_0_5_edc0357_1345510646254_583840_18431Members();
                init_17_0_5_edc0357_1345510646254_583840_18431Collections();
                init_17_0_5_edc0357_1345510646254_583840_18431Elaborations();
            }

            public _17_0_5_edc0357_1345510646254_583840_18431(Expression<Integer> invoker_endTime) {
                super();
                init_17_0_5_edc0357_1345510646254_583840_18431Members();
                init_17_0_5_edc0357_1345510646254_583840_18431Collections();
                addDependency(this.invoker_endTime, invoker_endTime);
                init_17_0_5_edc0357_1345510646254_583840_18431Elaborations();
                fixTimeDependencies();
            }

        }
/*
        public class _17_0_5_edc0357_1345510746643_574371_18524 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1345510201034_362860_18288_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1345510765243_88318_18538_exists = null;

            public ConstraintExpression constraint270 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510765243_88318_18538_existsDependency = null;

            public ElaborationRule elaborationRule271 = null;

            public void init_17_0_5_edc0357_1345510746643_574371_18524Members() {
                try {
                    _17_0_5_edc0357_1345510201034_362860_18288_endTime = new IntegerParameter("_17_0_5_edc0357_1345510201034_362860_18288_endTime", this);
                    _17_0_5_edc0357_1345510765243_88318_18538_exists = new BooleanParameter("_17_0_5_edc0357_1345510765243_88318_18538_exists", this);
                    constraint270 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510201034_362860_18288_endTime)));
                    _17_0_5_edc0357_1345510765243_88318_18538_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510765243_88318_18538_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510746643_574371_18524Collections() {
                parameters.add(_17_0_5_edc0357_1345510201034_362860_18288_endTime);
                parameters.add(_17_0_5_edc0357_1345510765243_88318_18538_exists);
                constraintExpressions.add(constraint270);
                dependencies.add(_17_0_5_edc0357_1345510765243_88318_18538_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510746643_574371_18524Elaborations() {
                Expression<?>[] arguments271 = new Expression<?>[1];
                arguments271[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition271 = new Expression<Boolean>(_17_0_5_edc0357_1345510765243_88318_18538_exists);
                elaborationRule271 = addElaborationRule(condition271, _17_0_5_edc0357_1345510155557_623464_18238.this, Power._17_0_5_edc0357_1345510155557_623464_18238._17_0_5_edc0357_1345510765243_88318_18538.class, "_ActivityFinalNode_PowerCB", arguments271);
            }

            public _17_0_5_edc0357_1345510746643_574371_18524() {
                super();
                init_17_0_5_edc0357_1345510746643_574371_18524Members();
                init_17_0_5_edc0357_1345510746643_574371_18524Collections();
                init_17_0_5_edc0357_1345510746643_574371_18524Elaborations();
            }

            public _17_0_5_edc0357_1345510746643_574371_18524(Expression<Integer> _17_0_5_edc0357_1345510201034_362860_18288_endTime) {
                super();
                init_17_0_5_edc0357_1345510746643_574371_18524Members();
                init_17_0_5_edc0357_1345510746643_574371_18524Collections();
                addDependency(this._17_0_5_edc0357_1345510201034_362860_18288_endTime, _17_0_5_edc0357_1345510201034_362860_18288_endTime);
                init_17_0_5_edc0357_1345510746643_574371_18524Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510765243_88318_18538 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1345510746643_574371_18524_endTime = null;

            public ConstraintExpression constraint272 = null;

            public void init_17_0_5_edc0357_1345510765243_88318_18538Members() {
                try {
                    _17_0_5_edc0357_1345510746643_574371_18524_endTime = new IntegerParameter("_17_0_5_edc0357_1345510746643_574371_18524_endTime", this);
                    constraint272 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510746643_574371_18524_endTime)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510765243_88318_18538Collections() {
                parameters.add(_17_0_5_edc0357_1345510746643_574371_18524_endTime);
                constraintExpressions.add(constraint272);
            }

            public void init_17_0_5_edc0357_1345510765243_88318_18538Elaborations() {
            }

            public _17_0_5_edc0357_1345510765243_88318_18538() {
                super();
                init_17_0_5_edc0357_1345510765243_88318_18538Members();
                init_17_0_5_edc0357_1345510765243_88318_18538Collections();
                init_17_0_5_edc0357_1345510765243_88318_18538Elaborations();
            }

            public _17_0_5_edc0357_1345510765243_88318_18538(Expression<Integer> _17_0_5_edc0357_1345510746643_574371_18524_endTime) {
                super();
                init_17_0_5_edc0357_1345510765243_88318_18538Members();
                init_17_0_5_edc0357_1345510765243_88318_18538Collections();
                addDependency(this._17_0_5_edc0357_1345510746643_574371_18524_endTime, _17_0_5_edc0357_1345510746643_574371_18524_endTime);
                init_17_0_5_edc0357_1345510765243_88318_18538Elaborations();
                fixTimeDependencies();
            }
        }
*/
        public class _17_0_5_edc0357_1345763165599_575948_13389 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1345763208746_548851_13403 = null;

            public BooleanParameter _17_0_5_edc0357_1345510492737_394609_18349_exists = null;

            public Parameter _17_0_5_edc0357_1345763176758_698915_13402 = null;

            public IntegerParameter _17_0_5_edc0357_1345510469459_873081_18318_endTime = null;

            public ConstraintExpression constraint273 = null;

            public Dependency< Integer > _17_0_5_edc0357_1345763208746_548851_13403Dependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510492737_394609_18349_existsDependency = null;

            public ElaborationRule elaborationRule274 = null;

            public void init_17_0_5_edc0357_1345763165599_575948_13389Members() {
                try {
                    _17_0_5_edc0357_1345763208746_548851_13403 = new IntegerParameter("_17_0_5_edc0357_1345763208746_548851_13403", this);
                    _17_0_5_edc0357_1345510492737_394609_18349_exists = new BooleanParameter("_17_0_5_edc0357_1345510492737_394609_18349_exists", this);
                    _17_0_5_edc0357_1345763176758_698915_13402 = new Parameter("_17_0_5_edc0357_1345763176758_698915_13402", null, null, this);
                    _17_0_5_edc0357_1345510469459_873081_18318_endTime = new IntegerParameter("_17_0_5_edc0357_1345510469459_873081_18318_endTime", this);
                    constraint273 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510469459_873081_18318_endTime)));
                    _17_0_5_edc0357_1345763208746_548851_13403Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345763208746_548851_13403, new Expression<Integer>((Integer) ((Power_System.SignalchangeLoadValue) _17_0_5_edc0357_1345763176758_698915_13402.getValue()).load.getValue()));
                    _17_0_5_edc0357_1345510492737_394609_18349_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510492737_394609_18349_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345763165599_575948_13389Collections() {
                parameters.add(_17_0_5_edc0357_1345763208746_548851_13403);
                parameters.add(_17_0_5_edc0357_1345510492737_394609_18349_exists);
                parameters.add(_17_0_5_edc0357_1345763176758_698915_13402);
                parameters.add(_17_0_5_edc0357_1345510469459_873081_18318_endTime);
                constraintExpressions.add(constraint273);
                dependencies.add(_17_0_5_edc0357_1345763208746_548851_13403Dependency);
                dependencies.add(_17_0_5_edc0357_1345510492737_394609_18349_existsDependency);
            }

            public void init_17_0_5_edc0357_1345763165599_575948_13389Elaborations() {
                Expression<?>[] arguments274 = new Expression<?>[2];
                arguments274[0] = new Expression<Integer>(endTime);
                arguments274[1] = new Expression<Integer>(_17_0_5_edc0357_1345763208746_548851_13403);
                Expression<Boolean> condition274 = new Expression<Boolean>(_17_0_5_edc0357_1345510492737_394609_18349_exists);
                elaborationRule274 = addElaborationRule(condition274, _17_0_5_edc0357_1345510155557_623464_18238.this, Power._17_0_5_edc0357_1345510155557_623464_18238._17_0_5_edc0357_1345510492737_394609_18349.class, "clv_CallBehaviorAction_PowerCB", arguments274);
            }

            public _17_0_5_edc0357_1345763165599_575948_13389() {
                super();
                init_17_0_5_edc0357_1345763165599_575948_13389Members();
                init_17_0_5_edc0357_1345763165599_575948_13389Collections();
                init_17_0_5_edc0357_1345763165599_575948_13389Elaborations();
            }

            public _17_0_5_edc0357_1345763165599_575948_13389(Expression<Integer> _17_0_5_edc0357_1345510469459_873081_18318_endTime, Expression<Power_System.SignalchangeLoadValue> _17_0_5_edc0357_1345763176758_698915_13402) {
                super();
                init_17_0_5_edc0357_1345763165599_575948_13389Members();
                init_17_0_5_edc0357_1345763165599_575948_13389Collections();
                addDependency(this._17_0_5_edc0357_1345510469459_873081_18318_endTime, _17_0_5_edc0357_1345510469459_873081_18318_endTime);
                addDependency(this._17_0_5_edc0357_1345763176758_698915_13402, _17_0_5_edc0357_1345763176758_698915_13402);
                init_17_0_5_edc0357_1345763165599_575948_13389Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345763300820_765750_13425 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1345763333670_26957_13439 = null;

            public IntegerParameter _17_0_5_edc0357_1345510471635_699632_18330_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1345510501544_90462_18367_exists = null;

            public Parameter _17_0_5_edc0357_1345763308884_192100_13438 = null;

            public ConstraintExpression constraint275 = null;

            public Dependency< Integer > _17_0_5_edc0357_1345763333670_26957_13439Dependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510501544_90462_18367_existsDependency = null;

            public ElaborationRule elaborationRule276 = null;

            public void init_17_0_5_edc0357_1345763300820_765750_13425Members() {
                try {
                    _17_0_5_edc0357_1345763333670_26957_13439 = new IntegerParameter("_17_0_5_edc0357_1345763333670_26957_13439", this);
                    _17_0_5_edc0357_1345510471635_699632_18330_endTime = new IntegerParameter("_17_0_5_edc0357_1345510471635_699632_18330_endTime", this);
                    _17_0_5_edc0357_1345510501544_90462_18367_exists = new BooleanParameter("_17_0_5_edc0357_1345510501544_90462_18367_exists", this);
                    _17_0_5_edc0357_1345763308884_192100_13438 = new Parameter("_17_0_5_edc0357_1345763308884_192100_13438", null, null, this);
                    constraint275 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510471635_699632_18330_endTime)));
                    _17_0_5_edc0357_1345763333670_26957_13439Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345763333670_26957_13439, new Expression(new FunctionCall((Object) ((Power_System.SignalchangeGenerationValue) _17_0_5_edc0357_1345763308884_192100_13438.getValue()).newGenValue__17_0_5_edc0357_1345510113551_931345_13769.getValue(), Utils.getMethodForArgTypes("Power._17_0_5_edc0357_1345510155557_623464_18238._17_0_5_edc0357_1345763300820_765750_13425", "generated", "getValue"), new Object[] { new Expression<Integer>(startTime) })));
                    _17_0_5_edc0357_1345510501544_90462_18367_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510501544_90462_18367_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345763300820_765750_13425Collections() {
                parameters.add(_17_0_5_edc0357_1345763333670_26957_13439);
                parameters.add(_17_0_5_edc0357_1345510471635_699632_18330_endTime);
                parameters.add(_17_0_5_edc0357_1345510501544_90462_18367_exists);
                parameters.add(_17_0_5_edc0357_1345763308884_192100_13438);
                constraintExpressions.add(constraint275);
                dependencies.add(_17_0_5_edc0357_1345763333670_26957_13439Dependency);
                dependencies.add(_17_0_5_edc0357_1345510501544_90462_18367_existsDependency);
            }

            public void init_17_0_5_edc0357_1345763300820_765750_13425Elaborations() {
                Expression<?>[] arguments276 = new Expression<?>[2];
                arguments276[0] = new Expression<Integer>(endTime);
                arguments276[1] = new Expression<Integer>(_17_0_5_edc0357_1345763333670_26957_13439);
                Expression<Boolean> condition276 = new Expression<Boolean>(_17_0_5_edc0357_1345510501544_90462_18367_exists);
                elaborationRule276 = addElaborationRule(condition276, _17_0_5_edc0357_1345510155557_623464_18238.this, Power._17_0_5_edc0357_1345510155557_623464_18238._17_0_5_edc0357_1345510501544_90462_18367.class, "cgv_CallBehaviorAction_PowerCB", arguments276);
            }

            public _17_0_5_edc0357_1345763300820_765750_13425() {
                super();
                init_17_0_5_edc0357_1345763300820_765750_13425Members();
                init_17_0_5_edc0357_1345763300820_765750_13425Collections();
                init_17_0_5_edc0357_1345763300820_765750_13425Elaborations();
            }

            public _17_0_5_edc0357_1345763300820_765750_13425(Expression<Integer> _17_0_5_edc0357_1345510471635_699632_18330_endTime, Expression<Power_System.SignalchangeGenerationValue> _17_0_5_edc0357_1345763308884_192100_13438) {
                super();
                init_17_0_5_edc0357_1345763300820_765750_13425Members();
                init_17_0_5_edc0357_1345763300820_765750_13425Collections();
                addDependency(this._17_0_5_edc0357_1345510471635_699632_18330_endTime, _17_0_5_edc0357_1345510471635_699632_18330_endTime);
                addDependency(this._17_0_5_edc0357_1345763308884_192100_13438, _17_0_5_edc0357_1345763308884_192100_13438);
                init_17_0_5_edc0357_1345763300820_765750_13425Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1348011599659_253029_14403 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1345510193521_82099_18271_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1345510201034_362860_18288_exists = null;

            public ConstraintExpression constraint277 = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510201034_362860_18288_existsDependency = null;

            public ElaborationRule elaborationRule278 = null;

            public void init_17_0_5_edc0357_1348011599659_253029_14403Members() {
                try {
                    _17_0_5_edc0357_1345510193521_82099_18271_endTime = new IntegerParameter("_17_0_5_edc0357_1345510193521_82099_18271_endTime", this);
                    _17_0_5_edc0357_1345510201034_362860_18288_exists = new BooleanParameter("_17_0_5_edc0357_1345510201034_362860_18288_exists", this);
                    constraint277 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510193521_82099_18271_endTime)));
                    durationDependency = new Dependency<Integer>(duration, new Expression<Integer>(20));
                    _17_0_5_edc0357_1345510201034_362860_18288_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510201034_362860_18288_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1348011599659_253029_14403Collections() {
                parameters.add(_17_0_5_edc0357_1345510193521_82099_18271_endTime);
                parameters.add(_17_0_5_edc0357_1345510201034_362860_18288_exists);
                constraintExpressions.add(constraint277);
                removeDependency(duration);
                dependencies.add(durationDependency);
                dependencies.add(_17_0_5_edc0357_1345510201034_362860_18288_existsDependency);
            }

            public void init_17_0_5_edc0357_1348011599659_253029_14403Elaborations() {
                Expression<?>[] arguments278 = new Expression<?>[1];
                arguments278[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition278 = new Expression<Boolean>(_17_0_5_edc0357_1345510201034_362860_18288_exists);
                elaborationRule278 = addElaborationRule(condition278, _17_0_5_edc0357_1345510155557_623464_18238.this, Power._17_0_5_edc0357_1345510155557_623464_18238._17_0_5_edc0357_1345510201034_362860_18288.class, "_ForkNode_PowerCB", arguments278);
            }

            public _17_0_5_edc0357_1348011599659_253029_14403() {
                super();
                init_17_0_5_edc0357_1348011599659_253029_14403Members();
                init_17_0_5_edc0357_1348011599659_253029_14403Collections();
                init_17_0_5_edc0357_1348011599659_253029_14403Elaborations();
            }

            public _17_0_5_edc0357_1348011599659_253029_14403(Expression<Integer> _17_0_5_edc0357_1345510193521_82099_18271_endTime) {
                super();
                init_17_0_5_edc0357_1348011599659_253029_14403Members();
                init_17_0_5_edc0357_1348011599659_253029_14403Collections();
                addDependency(this._17_0_5_edc0357_1345510193521_82099_18271_endTime, _17_0_5_edc0357_1345510193521_82099_18271_endTime);
                init_17_0_5_edc0357_1348011599659_253029_14403Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1348011630902_591447_14429 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1345510646254_583840_18431_exists = null;

            public IntegerParameter _17_0_5_edc0357_1345510532104_703303_18387_endTime = null;

            public ConstraintExpression constraint279 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510646254_583840_18431_existsDependency = null;

            public Dependency< Integer > durationDependency = null;

            public ElaborationRule elaborationRule280 = null;

            public void init_17_0_5_edc0357_1348011630902_591447_14429Members() {
                try {
                    _17_0_5_edc0357_1345510646254_583840_18431_exists = new BooleanParameter("_17_0_5_edc0357_1345510646254_583840_18431_exists", this);
                    _17_0_5_edc0357_1345510532104_703303_18387_endTime = new IntegerParameter("_17_0_5_edc0357_1345510532104_703303_18387_endTime", this);
                    constraint279 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510532104_703303_18387_endTime)));
                    _17_0_5_edc0357_1345510646254_583840_18431_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510646254_583840_18431_exists, new Expression<Boolean>(true));
                    durationDependency = new Dependency<Integer>(duration, new Expression<Integer>(15));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1348011630902_591447_14429Collections() {
                parameters.add(_17_0_5_edc0357_1345510646254_583840_18431_exists);
                parameters.add(_17_0_5_edc0357_1345510532104_703303_18387_endTime);
                constraintExpressions.add(constraint279);
                removeDependency(duration);
                dependencies.add(_17_0_5_edc0357_1345510646254_583840_18431_existsDependency);
                dependencies.add(durationDependency);
            }

            public void init_17_0_5_edc0357_1348011630902_591447_14429Elaborations() {
                Expression<?>[] arguments280 = new Expression<?>[1];
                arguments280[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition280 = new Expression<Boolean>(_17_0_5_edc0357_1345510646254_583840_18431_exists);
                elaborationRule280 = addElaborationRule(condition280, _17_0_5_edc0357_1345510155557_623464_18238.this, Power._17_0_5_edc0357_1345510155557_623464_18238._17_0_5_edc0357_1345510646254_583840_18431.class, "_MergeNode_PowerCB", arguments280);
            }

            public _17_0_5_edc0357_1348011630902_591447_14429() {
                super();
                init_17_0_5_edc0357_1348011630902_591447_14429Members();
                init_17_0_5_edc0357_1348011630902_591447_14429Collections();
                init_17_0_5_edc0357_1348011630902_591447_14429Elaborations();
            }

            public _17_0_5_edc0357_1348011630902_591447_14429(Expression<Integer> _17_0_5_edc0357_1345510532104_703303_18387_endTime) {
                super();
                init_17_0_5_edc0357_1348011630902_591447_14429Members();
                init_17_0_5_edc0357_1348011630902_591447_14429Collections();
                addDependency(this._17_0_5_edc0357_1345510532104_703303_18387_endTime, _17_0_5_edc0357_1345510532104_703303_18387_endTime);
                init_17_0_5_edc0357_1348011630902_591447_14429Elaborations();
                fixTimeDependencies();
            }
        }

        public _17_0_5_edc0357_1345510155557_623464_18238(Expression<Integer> invoke_time) {
            super();
            init_17_0_5_edc0357_1345510155557_623464_18238Members();
            init_17_0_5_edc0357_1345510155557_623464_18238Collections();
            addDependency(this.invoke_time, invoke_time);
            init_17_0_5_edc0357_1345510155557_623464_18238Elaborations();
            fixTimeDependencies();
        }
    }


    

    public Power(Power_System x) {
        this.x = x;
    }
}
