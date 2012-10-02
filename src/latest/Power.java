package latest;

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
import gov.nasa.jpl.ae.event.TimeVaryingPlottableMap;
import gov.nasa.jpl.ae.util.Utils;
import java.util.Vector;
import java.util.Map;
import java.util.Queue;
import java.util.LinkedList;
import gov.nasa.jpl.ae.fuml.ObjectFlow;
import java.util.Set;
import java.util.HashSet;
import gov.nasa.jpl.ae.event.EffectFunction;

public class Power extends ParameterListenerImpl {

    public StringParameter classifierBehavior = null;

    public Parameter< ObjectFlow<Power_System.SignalreceiveGenReading> > q_Power_receiveGenReading = null;

    public Parameter< TimeVaryingMap<Integer> > power__17_0_5_edc0357_1345510113545_356064_13763 = null;

    public Parameter< ObjectFlow<Power_System.SignalchangeGenerationValue> > q_Power_changeGenerationValue = null;

    public Parameter< Power_System > x = null;

    public Parameter< TimeVaryingMap<Integer> > load__17_0_5_edc0357_1345510113546_825610_13764 = null;

    public Parameter< ObjectFlow<Power_System.SignalreceiveLoadReading> > q_Power_receiveLoadReading = null;

    public Parameter< ObjectFlow<Power_System.SignalchangeLoadValue> > q_Power_changeLoadValue = null;

    public void initPowerMembers() {
        try {
            classifierBehavior = new StringParameter("classifierBehavior", this);
            q_Power_receiveGenReading = new Parameter<ObjectFlow<Power_System.SignalreceiveGenReading>>("q_Power_receiveGenReading", null, new ObjectFlow("q_Power_receiveGenReading", Power_System.SignalreceiveGenReading.class), this);
            power__17_0_5_edc0357_1345510113545_356064_13763 = new Parameter<TimeVaryingMap<Integer>>("power__17_0_5_edc0357_1345510113545_356064_13763", null, new TimeVaryingMap("power"), this);
            q_Power_changeGenerationValue = new Parameter<ObjectFlow<Power_System.SignalchangeGenerationValue>>("q_Power_changeGenerationValue", null, new ObjectFlow("q_Power_changeGenerationValue", Power_System.SignalchangeGenerationValue.class), this);
            x = new Parameter<Power_System>("x", null, null, this);
            load__17_0_5_edc0357_1345510113546_825610_13764 = new Parameter<TimeVaryingMap<Integer>>("load__17_0_5_edc0357_1345510113546_825610_13764", null, new TimeVaryingMap("load"), this);
            q_Power_receiveLoadReading = new Parameter<ObjectFlow<Power_System.SignalreceiveLoadReading>>("q_Power_receiveLoadReading", null, new ObjectFlow("q_Power_receiveLoadReading", Power_System.SignalreceiveLoadReading.class), this);
            q_Power_changeLoadValue = new Parameter<ObjectFlow<Power_System.SignalchangeLoadValue>>("q_Power_changeLoadValue", null, new ObjectFlow("q_Power_changeLoadValue", Power_System.SignalchangeLoadValue.class), this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void initPowerCollections() {
        parameters.add(classifierBehavior);
        parameters.add(q_Power_receiveGenReading);
        parameters.add(power__17_0_5_edc0357_1345510113545_356064_13763);
        parameters.add(q_Power_changeGenerationValue);
        parameters.add(x);
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

        public Parameter< ObjectFlow<Integer> > sig_17_0_5_edc0357_1345510113902_380027_14003 = null;

        public IntegerParameter _17_0_5_edc0357_1345756501917_658961_12894 = null;

        public Parameter< ObjectFlow<Power> > sig_17_0_5_edc0357_1345510113902_184025_14005 = null;

        public Parameter< ObjectFlow<Power> > sig_17_0_5_edc0357_1345510113902_964176_14006 = null;

        public IntegerParameter invoke_time = null;

        public ElaborationRule elaborationRule185 = null;

        public ElaborationRule elaborationRule186 = null;

        public void init_17_0_5_edc0357_1345510113540_247161_13759Members() {
            try {
                sig_17_0_5_edc0357_1345510113902_380027_14003 = new Parameter<ObjectFlow<Integer>>("sig_17_0_5_edc0357_1345510113902_380027_14003", null, new ObjectFlow("sig_17_0_5_edc0357_1345510113902_380027_14003"), this);
                _17_0_5_edc0357_1345756501917_658961_12894 = new IntegerParameter("_17_0_5_edc0357_1345756501917_658961_12894", this);
                sig_17_0_5_edc0357_1345510113902_184025_14005 = new Parameter<ObjectFlow<Power>>("sig_17_0_5_edc0357_1345510113902_184025_14005", null, new ObjectFlow("sig_17_0_5_edc0357_1345510113902_184025_14005"), this);
                sig_17_0_5_edc0357_1345510113902_964176_14006 = new Parameter<ObjectFlow<Power>>("sig_17_0_5_edc0357_1345510113902_964176_14006", null, new ObjectFlow("sig_17_0_5_edc0357_1345510113902_964176_14006"), this);
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
            Expression<?>[] arguments185 = new Expression<?>[1];
            arguments185[0] = new Expression<Integer>(invoke_time);
            Expression<Boolean> condition185 = new Expression<Boolean>(true);
            elaborationRule185 = addElaborationRule(condition185, _17_0_5_edc0357_1345510113540_247161_13759.this, Power._17_0_5_edc0357_1345510113540_247161_13759._17_0_5_edc0357_1345510113898_758379_13994.class, "start_InitialNode_changeGenerationValue", arguments185);
            Expression<?>[] arguments186 = new Expression<?>[2];
            arguments186[0] = new Expression<Integer>(invoke_time);
            arguments186[1] = new Expression<Integer>(_17_0_5_edc0357_1345756501917_658961_12894);
            Expression<Boolean> condition186 = new Expression<Boolean>(true);
            elaborationRule186 = addElaborationRule(condition186, _17_0_5_edc0357_1345510113540_247161_13759.this, Power._17_0_5_edc0357_1345510113540_247161_13759._17_0_5_edc0357_1345756515226_314305_12896.class, "newGenVal_ActivityParameterNode_changeGenerationValue", arguments186);
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

            public Parameter< Power > _17_0_5_edc0357_1345510114739_739072_15022 = null;

            public ConstraintExpression constraint187 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510113901_55313_13999_existsDependency = null;

            public ElaborationRule elaborationRule188 = null;

            public void init_17_0_5_edc0357_1345510113895_502750_13992Members() {
                try {
                    _17_0_5_edc0357_1345510113898_758379_13994_endTime = new IntegerParameter("_17_0_5_edc0357_1345510113898_758379_13994_endTime", this);
                    _17_0_5_edc0357_1345510113901_55313_13999_exists = new BooleanParameter("_17_0_5_edc0357_1345510113901_55313_13999_exists", this);
                    _17_0_5_edc0357_1345510114739_739072_15022 = new Parameter<Power>("_17_0_5_edc0357_1345510114739_739072_15022", null, Power.this, this);
                    constraint187 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510113898_758379_13994_endTime)));
                    _17_0_5_edc0357_1345510113901_55313_13999_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510113901_55313_13999_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510113895_502750_13992Collections() {
                parameters.add(_17_0_5_edc0357_1345510113898_758379_13994_endTime);
                parameters.add(_17_0_5_edc0357_1345510113901_55313_13999_exists);
                parameters.add(_17_0_5_edc0357_1345510114739_739072_15022);
                constraintExpressions.add(constraint187);
                dependencies.add(_17_0_5_edc0357_1345510113901_55313_13999_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510113895_502750_13992Elaborations() {
                Expression<?>[] arguments188 = new Expression<?>[2];
                arguments188[0] = new Expression<Integer>(endTime);
                arguments188[1] = new Expression<Power>(_17_0_5_edc0357_1345510114739_739072_15022);
                Expression<Boolean> condition188 = new Expression<Boolean>(_17_0_5_edc0357_1345510113901_55313_13999_exists);
                elaborationRule188 = addElaborationRule(condition188, _17_0_5_edc0357_1345510113540_247161_13759.this, Power._17_0_5_edc0357_1345510113540_247161_13759._17_0_5_edc0357_1345510113901_55313_13999.class, "fork_self_ForkNode_changeGenerationValue", arguments188);
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

            public Parameter< Power > _17_0_5_edc0357_1345510114741_475296_15024 = null;

            public BooleanParameter _17_0_5_edc0357_1345510113900_906855_13997_exists = null;

            public IntegerParameter _17_0_5_edc0357_1345510114740_587173_15023 = null;

            public IntegerParameter _17_0_5_edc0357_1345510113901_940168_13998_endTime = null;

            public ConstraintExpression constraint189 = null;

            public Dependency< Power > _17_0_5_edc0357_1345510114741_475296_15024Dependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510113900_906855_13997_existsDependency = null;

            public Effect effect190 = null;

            public Parameter effect190Var = null;

            public ElaborationRule elaborationRule191 = null;

            public void init_17_0_5_edc0357_1345510113897_296616_13993Members() {
                try {
                    _17_0_5_edc0357_1345510114741_475296_15024 = new Parameter<Power>("_17_0_5_edc0357_1345510114741_475296_15024", null, null, this);
                    _17_0_5_edc0357_1345510113900_906855_13997_exists = new BooleanParameter("_17_0_5_edc0357_1345510113900_906855_13997_exists", this);
                    _17_0_5_edc0357_1345510114740_587173_15023 = new IntegerParameter("_17_0_5_edc0357_1345510114740_587173_15023", this);
                    _17_0_5_edc0357_1345510113901_940168_13998_endTime = new IntegerParameter("_17_0_5_edc0357_1345510113901_940168_13998_endTime", this);
                    constraint189 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510113901_940168_13998_endTime)));
                    _17_0_5_edc0357_1345510114741_475296_15024Dependency = new Dependency<Power>(_17_0_5_edc0357_1345510114741_475296_15024, new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510113902_184025_14005, Utils.getMethodForArgTypes("ObjectFlow<Power>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1345510113900_906855_13997_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510113900_906855_13997_exists, new Functions.And(new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510113902_380027_14003, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510113902_964176_14006, Utils.getMethodForArgTypes("ObjectFlow<Power>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) }))));
                    Object effect190VarV = power__17_0_5_edc0357_1345510113545_356064_13763;
                    effect190Var = new Parameter("effect190Var", null, null, this);
                    addDependency(effect190Var, new Expression(effect190VarV));
                    effect190 = new EffectFunction(new FunctionCall(effect190Var, Utils.getMethodForArgTypes("TimeVaryingMap<Integer>", "generated", "setValue", java.lang.Integer.class, java.lang.Object.class), new Object[] { startTime, _17_0_5_edc0357_1345510114740_587173_15023 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510113897_296616_13993Collections() {
                parameters.add(_17_0_5_edc0357_1345510114741_475296_15024);
                parameters.add(_17_0_5_edc0357_1345510113900_906855_13997_exists);
                parameters.add(_17_0_5_edc0357_1345510114740_587173_15023);
                parameters.add(_17_0_5_edc0357_1345510113901_940168_13998_endTime);
                constraintExpressions.add(constraint189);
                dependencies.add(_17_0_5_edc0357_1345510114741_475296_15024Dependency);
                dependencies.add(_17_0_5_edc0357_1345510113900_906855_13997_existsDependency);
                Set<Effect> effectsForeffect190Var = new HashSet<Effect>();
                effectsForeffect190Var.add(effect190);
                effects.put((Parameter<?>) effect190Var, effectsForeffect190Var);
            }

            public void init_17_0_5_edc0357_1345510113897_296616_13993Elaborations() {
                Expression<?>[] arguments191 = new Expression<?>[1];
                arguments191[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition191 = new Expression<Boolean>(_17_0_5_edc0357_1345510113900_906855_13997_exists);
                elaborationRule191 = addElaborationRule(condition191, _17_0_5_edc0357_1345510113540_247161_13759.this, Power._17_0_5_edc0357_1345510113540_247161_13759._17_0_5_edc0357_1345510113900_906855_13997.class, "send_generation_reading_SendSignalAction_changeGenerationValue", arguments191);
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

            public ElaborationRule elaborationRule192 = null;

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
                Expression<?>[] arguments192 = new Expression<?>[1];
                arguments192[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition192 = new Expression<Boolean>(_17_0_5_edc0357_1345510113895_502750_13992_exists);
                elaborationRule192 = addElaborationRule(condition192, _17_0_5_edc0357_1345510113540_247161_13759.this, Power._17_0_5_edc0357_1345510113540_247161_13759._17_0_5_edc0357_1345510113895_502750_13992.class, "readSelf_ReadSelfAction_changeGenerationValue", arguments192);
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

            public ConstraintExpression constraint193 = null;

            public void init_17_0_5_edc0357_1345510113898_26640_13995Members() {
                try {
                    _17_0_5_edc0357_1345510113900_906855_13997_endTime = new IntegerParameter("_17_0_5_edc0357_1345510113900_906855_13997_endTime", this);
                    constraint193 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510113900_906855_13997_endTime)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510113898_26640_13995Collections() {
                parameters.add(_17_0_5_edc0357_1345510113900_906855_13997_endTime);
                constraintExpressions.add(constraint193);
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

            public Parameter< Power > _17_0_5_edc0357_1345510114742_225045_15026 = null;

            public IntegerParameter _17_0_5_edc0357_1345510114743_924337_15027 = null;

            public IntegerParameter _17_0_5_edc0357_1345510113897_296616_13993_endTime = null;

            public ConstraintExpression constraint194 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510113898_26640_13995_existsDependency = null;

            public Dependency< Power > _17_0_5_edc0357_1345510114742_225045_15026Dependency = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114743_924337_15027Dependency = null;

            public Effect effect195 = null;

            public Parameter effect195Var = null;

            public ElaborationRule elaborationRule196 = null;

            public void init_17_0_5_edc0357_1345510113900_906855_13997Members() {
                try {
                    _17_0_5_edc0357_1345510113898_26640_13995_exists = new BooleanParameter("_17_0_5_edc0357_1345510113898_26640_13995_exists", this);
                    _17_0_5_edc0357_1345510114742_225045_15026 = new Parameter<Power>("_17_0_5_edc0357_1345510114742_225045_15026", null, null, this);
                    _17_0_5_edc0357_1345510114743_924337_15027 = new IntegerParameter("_17_0_5_edc0357_1345510114743_924337_15027", this);
                    _17_0_5_edc0357_1345510113897_296616_13993_endTime = new IntegerParameter("_17_0_5_edc0357_1345510113897_296616_13993_endTime", this);
                    constraint194 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510113897_296616_13993_endTime)));
                    _17_0_5_edc0357_1345510113898_26640_13995_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510113898_26640_13995_exists, new Expression<Boolean>(true));
                    _17_0_5_edc0357_1345510114742_225045_15026Dependency = new Dependency<Power>(_17_0_5_edc0357_1345510114742_225045_15026, new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510113902_964176_14006, Utils.getMethodForArgTypes("ObjectFlow<Power>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1345510114743_924337_15027Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114743_924337_15027, new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510113902_380027_14003, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    Object effect195VarV = new FunctionCall(null, Parameter.class, "getValue", (Object[]) null, new FunctionCall(x, Parameter.class, "getMember", new Object[] { "ss_17_0_5_edc0357_1345510113550_816176_13768_receiveGenReading" }));
                    effect195Var = new Parameter("effect195Var", null, null, this);
                    addDependency(effect195Var, new Expression(effect195VarV));
                    effect195 = new EffectFunction(new FunctionCall(null, Utils.getMethodForArgTypes("Power._17_0_5_edc0357_1345510113540_247161_13759._17_0_5_edc0357_1345510113900_906855_13997", "generated", "send"), new Object[] { x.getValue().new SignalreceiveGenReading(endTime.getValue(), _17_0_5_edc0357_1345510114743_924337_15027.getValue()), endTime }, effect195Var));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510113900_906855_13997Collections() {
                parameters.add(_17_0_5_edc0357_1345510113898_26640_13995_exists);
                parameters.add(_17_0_5_edc0357_1345510114742_225045_15026);
                parameters.add(_17_0_5_edc0357_1345510114743_924337_15027);
                parameters.add(_17_0_5_edc0357_1345510113897_296616_13993_endTime);
                constraintExpressions.add(constraint194);
                dependencies.add(_17_0_5_edc0357_1345510113898_26640_13995_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510114742_225045_15026Dependency);
                dependencies.add(_17_0_5_edc0357_1345510114743_924337_15027Dependency);
                Set<Effect> effectsForeffect195Var = new HashSet<Effect>();
                effectsForeffect195Var.add(effect195);
                effects.put((Parameter<?>) effect195Var, effectsForeffect195Var);
            }

            public void init_17_0_5_edc0357_1345510113900_906855_13997Elaborations() {
                Expression<?>[] arguments196 = new Expression<?>[1];
                arguments196[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition196 = new Expression<Boolean>(_17_0_5_edc0357_1345510113898_26640_13995_exists);
                elaborationRule196 = addElaborationRule(condition196, _17_0_5_edc0357_1345510113540_247161_13759.this, Power._17_0_5_edc0357_1345510113540_247161_13759._17_0_5_edc0357_1345510113898_26640_13995.class, "end_ActivityFinalNode_changeGenerationValue", arguments196);
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

            public ConstraintExpression constraint197 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510113897_296616_13993_existsDependency = null;

            public Effect effect198 = null;

            public Parameter effect198Var = null;

            public ElaborationRule elaborationRule199 = null;

            public void init_17_0_5_edc0357_1345510113901_940168_13998Members() {
                try {
                    _17_0_5_edc0357_1345756515226_314305_12896_endTime = new IntegerParameter("_17_0_5_edc0357_1345756515226_314305_12896_endTime", this);
                    _17_0_5_edc0357_1345510113897_296616_13993_exists = new BooleanParameter("_17_0_5_edc0357_1345510113897_296616_13993_exists", this);
                    objectToPass = new IntegerParameter("objectToPass", this);
                    constraint197 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345756515226_314305_12896_endTime)));
                    _17_0_5_edc0357_1345510113897_296616_13993_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510113897_296616_13993_exists, new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510113902_184025_14005, Utils.getMethodForArgTypes("ObjectFlow<Power>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })));
                    Object effect198VarV = sig_17_0_5_edc0357_1345510113902_380027_14003;
                    effect198Var = new Parameter("effect198Var", null, null, this);
                    addDependency(effect198Var, new Expression(effect198VarV));
                    effect198 = new EffectFunction(new FunctionCall(effect198Var, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510113901_940168_13998Collections() {
                parameters.add(_17_0_5_edc0357_1345756515226_314305_12896_endTime);
                parameters.add(_17_0_5_edc0357_1345510113897_296616_13993_exists);
                parameters.add(objectToPass);
                constraintExpressions.add(constraint197);
                dependencies.add(_17_0_5_edc0357_1345510113897_296616_13993_existsDependency);
                Set<Effect> effectsForeffect198Var = new HashSet<Effect>();
                effectsForeffect198Var.add(effect198);
                effects.put((Parameter<?>) effect198Var, effectsForeffect198Var);
            }

            public void init_17_0_5_edc0357_1345510113901_940168_13998Elaborations() {
                Expression<?>[] arguments199 = new Expression<?>[2];
                arguments199[0] = new Expression<Integer>(endTime);
                arguments199[1] = new Expression<Integer>(objectToPass);
                Expression<Boolean> condition199 = new Expression<Boolean>(_17_0_5_edc0357_1345510113897_296616_13993_exists);
                elaborationRule199 = addElaborationRule(condition199, _17_0_5_edc0357_1345510113540_247161_13759.this, Power._17_0_5_edc0357_1345510113540_247161_13759._17_0_5_edc0357_1345510113897_296616_13993.class, "add_struct_power_AddStructuralFeatureValueAction_changeGenerationValue", arguments199);
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

            public Parameter< Power > objectToPass = null;

            public ConstraintExpression constraint200 = null;

            public Effect effect201 = null;

            public Parameter effect201Var = null;

            public Effect effect202 = null;

            public Parameter effect202Var = null;

            public void init_17_0_5_edc0357_1345510113901_55313_13999Members() {
                try {
                    _17_0_5_edc0357_1345510113895_502750_13992_endTime = new IntegerParameter("_17_0_5_edc0357_1345510113895_502750_13992_endTime", this);
                    objectToPass = new Parameter<Power>("objectToPass", null, null, this);
                    constraint200 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510113895_502750_13992_endTime)));
                    Object effect201VarV = sig_17_0_5_edc0357_1345510113902_184025_14005;
                    effect201Var = new Parameter("effect201Var", null, null, this);
                    addDependency(effect201Var, new Expression(effect201VarV));
                    effect201 = new EffectFunction(new FunctionCall(effect201Var, Utils.getMethodForArgTypes("ObjectFlow<Power>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                    Object effect202VarV = sig_17_0_5_edc0357_1345510113902_964176_14006;
                    effect202Var = new Parameter("effect202Var", null, null, this);
                    addDependency(effect202Var, new Expression(effect202VarV));
                    effect202 = new EffectFunction(new FunctionCall(effect202Var, Utils.getMethodForArgTypes("ObjectFlow<Power>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510113901_55313_13999Collections() {
                parameters.add(_17_0_5_edc0357_1345510113895_502750_13992_endTime);
                parameters.add(objectToPass);
                constraintExpressions.add(constraint200);
                Set<Effect> effectsForeffect201Var = new HashSet<Effect>();
                effectsForeffect201Var.add(effect201);
                effects.put((Parameter<?>) effect201Var, effectsForeffect201Var);
                Set<Effect> effectsForeffect202Var = new HashSet<Effect>();
                effectsForeffect202Var.add(effect202);
                effects.put((Parameter<?>) effect202Var, effectsForeffect202Var);
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

            public ElaborationRule elaborationRule203 = null;

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
                Expression<?>[] arguments203 = new Expression<?>[2];
                arguments203[0] = new Expression<Integer>(endTime);
                arguments203[1] = new Expression<Integer>(_17_0_5_edc0357_1345756501917_658961_12894);
                Expression<Boolean> condition203 = new Expression<Boolean>(_17_0_5_edc0357_1345510113901_940168_13998_exists);
                elaborationRule203 = addElaborationRule(condition203, _17_0_5_edc0357_1345510113540_247161_13759.this, Power._17_0_5_edc0357_1345510113540_247161_13759._17_0_5_edc0357_1345510113901_940168_13998.class, "fork_val_ForkNode_changeGenerationValue", arguments203);
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

        public Parameter< ObjectFlow<Integer> > sig_17_0_5_edc0357_1345756813363_602852_13369 = null;

        public Parameter< ObjectFlow<Power> > sig_17_0_5_edc0357_1345510113922_694984_14041 = null;

        public ElaborationRule elaborationRule204 = null;

        public ElaborationRule elaborationRule205 = null;

        public void init_17_0_5_edc0357_1345510113541_858357_13760Members() {
            try {
                _17_0_5_edc0357_1345756720553_849612_12938 = new IntegerParameter("_17_0_5_edc0357_1345756720553_849612_12938", this);
                invoke_time = new IntegerParameter("invoke_time", this);
                sig_17_0_5_edc0357_1345756813363_602852_13369 = new Parameter<ObjectFlow<Integer>>("sig_17_0_5_edc0357_1345756813363_602852_13369", null, new ObjectFlow("sig_17_0_5_edc0357_1345756813363_602852_13369"), this);
                sig_17_0_5_edc0357_1345510113922_694984_14041 = new Parameter<ObjectFlow<Power>>("sig_17_0_5_edc0357_1345510113922_694984_14041", null, new ObjectFlow("sig_17_0_5_edc0357_1345510113922_694984_14041"), this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void init_17_0_5_edc0357_1345510113541_858357_13760Collections() {
            parameters.add(_17_0_5_edc0357_1345756720553_849612_12938);
            parameters.add(invoke_time);
            parameters.add(sig_17_0_5_edc0357_1345756813363_602852_13369);
            parameters.add(sig_17_0_5_edc0357_1345510113922_694984_14041);
        }

        public void init_17_0_5_edc0357_1345510113541_858357_13760Elaborations() {
            Expression<?>[] arguments204 = new Expression<?>[2];
            arguments204[0] = new Expression<Integer>(invoke_time);
            arguments204[1] = new Expression<Integer>(_17_0_5_edc0357_1345756720553_849612_12938);
            Expression<Boolean> condition204 = new Expression<Boolean>(true);
            elaborationRule204 = addElaborationRule(condition204, _17_0_5_edc0357_1345510113541_858357_13760.this, Power._17_0_5_edc0357_1345510113541_858357_13760._17_0_5_edc0357_1345756729509_790741_12940.class, "newLoadVal_ActivityParameterNode_changeLoadValue", arguments204);
            Expression<?>[] arguments205 = new Expression<?>[1];
            arguments205[0] = new Expression<Integer>(invoke_time);
            Expression<Boolean> condition205 = new Expression<Boolean>(true);
            elaborationRule205 = addElaborationRule(condition205, _17_0_5_edc0357_1345510113541_858357_13760.this, Power._17_0_5_edc0357_1345510113541_858357_13760._17_0_5_edc0357_1345510113920_652162_14033.class, "start_InitialNode_changeLoadValue", arguments205);
        }

        public _17_0_5_edc0357_1345510113541_858357_13760() {
            super();
            init_17_0_5_edc0357_1345510113541_858357_13760Members();
            init_17_0_5_edc0357_1345510113541_858357_13760Collections();
            init_17_0_5_edc0357_1345510113541_858357_13760Elaborations();
        }

        public class _17_0_5_edc0357_1345510113918_681912_14031 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1345510113919_761849_14032_exists = null;

            public Parameter< Power > _17_0_5_edc0357_1345510114746_758365_15038 = null;

            public IntegerParameter _17_0_5_edc0357_1345510113920_652162_14033_endTime = null;

            public ConstraintExpression constraint206 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510113919_761849_14032_existsDependency = null;

            public Effect effect207 = null;

            public Parameter effect207Var = null;

            public ElaborationRule elaborationRule208 = null;

            public void init_17_0_5_edc0357_1345510113918_681912_14031Members() {
                try {
                    _17_0_5_edc0357_1345510113919_761849_14032_exists = new BooleanParameter("_17_0_5_edc0357_1345510113919_761849_14032_exists", this);
                    _17_0_5_edc0357_1345510114746_758365_15038 = new Parameter<Power>("_17_0_5_edc0357_1345510114746_758365_15038", null, Power.this, this);
                    _17_0_5_edc0357_1345510113920_652162_14033_endTime = new IntegerParameter("_17_0_5_edc0357_1345510113920_652162_14033_endTime", this);
                    constraint206 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510113920_652162_14033_endTime)));
                    _17_0_5_edc0357_1345510113919_761849_14032_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510113919_761849_14032_exists, new Functions.And(new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510113922_694984_14041, Utils.getMethodForArgTypes("ObjectFlow<Power>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall(sig_17_0_5_edc0357_1345756813363_602852_13369, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) }))));
                    Object effect207VarV = sig_17_0_5_edc0357_1345510113922_694984_14041;
                    effect207Var = new Parameter("effect207Var", null, null, this);
                    addDependency(effect207Var, new Expression(effect207VarV));
                    effect207 = new EffectFunction(new FunctionCall(effect207Var, Utils.getMethodForArgTypes("ObjectFlow<Power>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { _17_0_5_edc0357_1345510114746_758365_15038, startTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510113918_681912_14031Collections() {
                parameters.add(_17_0_5_edc0357_1345510113919_761849_14032_exists);
                parameters.add(_17_0_5_edc0357_1345510114746_758365_15038);
                parameters.add(_17_0_5_edc0357_1345510113920_652162_14033_endTime);
                constraintExpressions.add(constraint206);
                dependencies.add(_17_0_5_edc0357_1345510113919_761849_14032_existsDependency);
                Set<Effect> effectsForeffect207Var = new HashSet<Effect>();
                effectsForeffect207Var.add(effect207);
                effects.put((Parameter<?>) effect207Var, effectsForeffect207Var);
            }

            public void init_17_0_5_edc0357_1345510113918_681912_14031Elaborations() {
                Expression<?>[] arguments208 = new Expression<?>[1];
                arguments208[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition208 = new Expression<Boolean>(_17_0_5_edc0357_1345510113919_761849_14032_exists);
                elaborationRule208 = addElaborationRule(condition208, _17_0_5_edc0357_1345510113541_858357_13760.this, Power._17_0_5_edc0357_1345510113541_858357_13760._17_0_5_edc0357_1345510113919_761849_14032.class, "add_struct_load_AddStructuralFeatureValueAction_changeLoadValue", arguments208);
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

            public Parameter< Power > _17_0_5_edc0357_1345510114748_254651_15040 = null;

            public BooleanParameter _17_0_5_edc0357_1345510113920_40124_14034_exists = null;

            public IntegerParameter _17_0_5_edc0357_1345510113918_681912_14031_endTime = null;

            public ConstraintExpression constraint209 = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114747_983362_15039Dependency = null;

            public Dependency< Power > _17_0_5_edc0357_1345510114748_254651_15040Dependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510113920_40124_14034_existsDependency = null;

            public Effect effect210 = null;

            public Parameter effect210Var = null;

            public ElaborationRule elaborationRule211 = null;

            public void init_17_0_5_edc0357_1345510113919_761849_14032Members() {
                try {
                    _17_0_5_edc0357_1345510114747_983362_15039 = new IntegerParameter("_17_0_5_edc0357_1345510114747_983362_15039", this);
                    _17_0_5_edc0357_1345510114748_254651_15040 = new Parameter<Power>("_17_0_5_edc0357_1345510114748_254651_15040", null, null, this);
                    _17_0_5_edc0357_1345510113920_40124_14034_exists = new BooleanParameter("_17_0_5_edc0357_1345510113920_40124_14034_exists", this);
                    _17_0_5_edc0357_1345510113918_681912_14031_endTime = new IntegerParameter("_17_0_5_edc0357_1345510113918_681912_14031_endTime", this);
                    constraint209 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510113918_681912_14031_endTime)));
                    _17_0_5_edc0357_1345510114747_983362_15039Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114747_983362_15039, new Expression(new FunctionCall(sig_17_0_5_edc0357_1345756813363_602852_13369, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1345510114748_254651_15040Dependency = new Dependency<Power>(_17_0_5_edc0357_1345510114748_254651_15040, new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510113922_694984_14041, Utils.getMethodForArgTypes("ObjectFlow<Power>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1345510113920_40124_14034_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510113920_40124_14034_exists, new Expression<Boolean>(true));
                    Object effect210VarV = load__17_0_5_edc0357_1345510113546_825610_13764;
                    effect210Var = new Parameter("effect210Var", null, null, this);
                    addDependency(effect210Var, new Expression(effect210VarV));
                    effect210 = new EffectFunction(new FunctionCall(effect210Var, Utils.getMethodForArgTypes("TimeVaryingMap<Integer>", "generated", "setValue", java.lang.Integer.class, java.lang.Object.class), new Object[] { startTime, _17_0_5_edc0357_1345510114747_983362_15039 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510113919_761849_14032Collections() {
                parameters.add(_17_0_5_edc0357_1345510114747_983362_15039);
                parameters.add(_17_0_5_edc0357_1345510114748_254651_15040);
                parameters.add(_17_0_5_edc0357_1345510113920_40124_14034_exists);
                parameters.add(_17_0_5_edc0357_1345510113918_681912_14031_endTime);
                constraintExpressions.add(constraint209);
                dependencies.add(_17_0_5_edc0357_1345510114747_983362_15039Dependency);
                dependencies.add(_17_0_5_edc0357_1345510114748_254651_15040Dependency);
                dependencies.add(_17_0_5_edc0357_1345510113920_40124_14034_existsDependency);
                Set<Effect> effectsForeffect210Var = new HashSet<Effect>();
                effectsForeffect210Var.add(effect210);
                effects.put((Parameter<?>) effect210Var, effectsForeffect210Var);
            }

            public void init_17_0_5_edc0357_1345510113919_761849_14032Elaborations() {
                Expression<?>[] arguments211 = new Expression<?>[1];
                arguments211[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition211 = new Expression<Boolean>(_17_0_5_edc0357_1345510113920_40124_14034_exists);
                elaborationRule211 = addElaborationRule(condition211, _17_0_5_edc0357_1345510113541_858357_13760.this, Power._17_0_5_edc0357_1345510113541_858357_13760._17_0_5_edc0357_1345510113920_40124_14034.class, "end_ActivityFinalNode_changeLoadValue", arguments211);
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

            public ElaborationRule elaborationRule212 = null;

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
                Expression<?>[] arguments212 = new Expression<?>[1];
                arguments212[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition212 = new Expression<Boolean>(_17_0_5_edc0357_1345510113918_681912_14031_exists);
                elaborationRule212 = addElaborationRule(condition212, _17_0_5_edc0357_1345510113541_858357_13760.this, Power._17_0_5_edc0357_1345510113541_858357_13760._17_0_5_edc0357_1345510113918_681912_14031.class, "readself_ReadSelfAction_changeLoadValue", arguments212);
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

            public ConstraintExpression constraint213 = null;

            public void init_17_0_5_edc0357_1345510113920_40124_14034Members() {
                try {
                    _17_0_5_edc0357_1345510113919_761849_14032_endTime = new IntegerParameter("_17_0_5_edc0357_1345510113919_761849_14032_endTime", this);
                    constraint213 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510113919_761849_14032_endTime)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510113920_40124_14034Collections() {
                parameters.add(_17_0_5_edc0357_1345510113919_761849_14032_endTime);
                constraintExpressions.add(constraint213);
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

            public Effect effect214 = null;

            public Parameter effect214Var = null;

            public void init_17_0_5_edc0357_1345756729509_790741_12940Members() {
                try {
                    _17_0_5_edc0357_1345756720553_849612_12938 = new IntegerParameter("_17_0_5_edc0357_1345756720553_849612_12938", this);
                    Object effect214VarV = sig_17_0_5_edc0357_1345756813363_602852_13369;
                    effect214Var = new Parameter("effect214Var", null, null, this);
                    addDependency(effect214Var, new Expression(effect214VarV));
                    effect214 = new EffectFunction(new FunctionCall(effect214Var, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { _17_0_5_edc0357_1345756720553_849612_12938, startTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345756729509_790741_12940Collections() {
                parameters.add(_17_0_5_edc0357_1345756720553_849612_12938);
                Set<Effect> effectsForeffect214Var = new HashSet<Effect>();
                effectsForeffect214Var.add(effect214);
                effects.put((Parameter<?>) effect214Var, effectsForeffect214Var);
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

        public Parameter< ObjectFlow<Power> > sig_17_0_5_edc0357_1345510113942_415807_14078 = null;

        public Parameter< ObjectFlow<Power> > sig_17_0_5_edc0357_1345510113942_807692_14079 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_5_edc0357_1345510113943_183272_14085 = null;

        public IntegerParameter invoke_time = null;

        public ElaborationRule elaborationRule215 = null;

        public void init_17_0_5_edc0357_1345510113542_666806_13761Members() {
            try {
                sig_17_0_5_edc0357_1345510113942_415807_14078 = new Parameter<ObjectFlow<Power>>("sig_17_0_5_edc0357_1345510113942_415807_14078", null, new ObjectFlow("sig_17_0_5_edc0357_1345510113942_415807_14078"), this);
                sig_17_0_5_edc0357_1345510113942_807692_14079 = new Parameter<ObjectFlow<Power>>("sig_17_0_5_edc0357_1345510113942_807692_14079", null, new ObjectFlow("sig_17_0_5_edc0357_1345510113942_807692_14079"), this);
                sig_17_0_5_edc0357_1345510113943_183272_14085 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_5_edc0357_1345510113943_183272_14085", null, new ObjectFlow("sig_17_0_5_edc0357_1345510113943_183272_14085"), this);
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
            Expression<?>[] arguments215 = new Expression<?>[1];
            arguments215[0] = new Expression<Integer>(invoke_time);
            Expression<Boolean> condition215 = new Expression<Boolean>(true);
            elaborationRule215 = addElaborationRule(condition215, _17_0_5_edc0357_1345510113542_666806_13761.this, Power._17_0_5_edc0357_1345510113542_666806_13761._17_0_5_edc0357_1345510113940_857370_14071.class, "_InitialNode_intialize", arguments215);
        }

        public _17_0_5_edc0357_1345510113542_666806_13761() {
            super();
            init_17_0_5_edc0357_1345510113542_666806_13761Members();
            init_17_0_5_edc0357_1345510113542_666806_13761Collections();
            init_17_0_5_edc0357_1345510113542_666806_13761Elaborations();
        }

        public class _17_0_5_edc0357_1345510113936_636752_14065 extends DurativeEvent {

            public Parameter< Power > _17_0_5_edc0357_1345510114753_527291_15047 = null;

            public BooleanParameter _17_0_5_edc0357_1345510113937_30533_14066_exists = null;

            public IntegerParameter _17_0_5_edc0357_1345510113940_364097_14072_endTime = null;

            public ConstraintExpression constraint216 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510113937_30533_14066_existsDependency = null;

            public ElaborationRule elaborationRule217 = null;

            public void init_17_0_5_edc0357_1345510113936_636752_14065Members() {
                try {
                    _17_0_5_edc0357_1345510114753_527291_15047 = new Parameter<Power>("_17_0_5_edc0357_1345510114753_527291_15047", null, Power.this, this);
                    _17_0_5_edc0357_1345510113937_30533_14066_exists = new BooleanParameter("_17_0_5_edc0357_1345510113937_30533_14066_exists", this);
                    _17_0_5_edc0357_1345510113940_364097_14072_endTime = new IntegerParameter("_17_0_5_edc0357_1345510113940_364097_14072_endTime", this);
                    constraint216 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510113940_364097_14072_endTime)));
                    _17_0_5_edc0357_1345510113937_30533_14066_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510113937_30533_14066_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510113936_636752_14065Collections() {
                parameters.add(_17_0_5_edc0357_1345510114753_527291_15047);
                parameters.add(_17_0_5_edc0357_1345510113937_30533_14066_exists);
                parameters.add(_17_0_5_edc0357_1345510113940_364097_14072_endTime);
                constraintExpressions.add(constraint216);
                dependencies.add(_17_0_5_edc0357_1345510113937_30533_14066_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510113936_636752_14065Elaborations() {
                Expression<?>[] arguments217 = new Expression<?>[2];
                arguments217[0] = new Expression<Integer>(endTime);
                arguments217[1] = new Expression<Power>(_17_0_5_edc0357_1345510114753_527291_15047);
                Expression<Boolean> condition217 = new Expression<Boolean>(_17_0_5_edc0357_1345510113937_30533_14066_exists);
                elaborationRule217 = addElaborationRule(condition217, _17_0_5_edc0357_1345510113542_666806_13761.this, Power._17_0_5_edc0357_1345510113542_666806_13761._17_0_5_edc0357_1345510113937_30533_14066.class, "self_fork_ForkNode_intialize", arguments217);
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

            public Parameter< Power > objectToPass = null;

            public ConstraintExpression constraint218 = null;

            public Effect effect219 = null;

            public Parameter effect219Var = null;

            public Effect effect220 = null;

            public Parameter effect220Var = null;

            public void init_17_0_5_edc0357_1345510113937_30533_14066Members() {
                try {
                    _17_0_5_edc0357_1345510113936_636752_14065_endTime = new IntegerParameter("_17_0_5_edc0357_1345510113936_636752_14065_endTime", this);
                    objectToPass = new Parameter<Power>("objectToPass", null, null, this);
                    constraint218 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510113936_636752_14065_endTime)));
                    Object effect219VarV = sig_17_0_5_edc0357_1345510113942_415807_14078;
                    effect219Var = new Parameter("effect219Var", null, null, this);
                    addDependency(effect219Var, new Expression(effect219VarV));
                    effect219 = new EffectFunction(new FunctionCall(effect219Var, Utils.getMethodForArgTypes("ObjectFlow<Power>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                    Object effect220VarV = sig_17_0_5_edc0357_1345510113942_807692_14079;
                    effect220Var = new Parameter("effect220Var", null, null, this);
                    addDependency(effect220Var, new Expression(effect220VarV));
                    effect220 = new EffectFunction(new FunctionCall(effect220Var, Utils.getMethodForArgTypes("ObjectFlow<Power>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510113937_30533_14066Collections() {
                parameters.add(_17_0_5_edc0357_1345510113936_636752_14065_endTime);
                parameters.add(objectToPass);
                constraintExpressions.add(constraint218);
                Set<Effect> effectsForeffect219Var = new HashSet<Effect>();
                effectsForeffect219Var.add(effect219);
                effects.put((Parameter<?>) effect219Var, effectsForeffect219Var);
                Set<Effect> effectsForeffect220Var = new HashSet<Effect>();
                effectsForeffect220Var.add(effect220);
                effects.put((Parameter<?>) effect220Var, effectsForeffect220Var);
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

            public Parameter< Power > _17_0_5_edc0357_1345510114754_415963_15049 = null;

            public IntegerParameter _17_0_5_edc0357_1345510114754_755936_15048 = null;

            public IntegerParameter _17_0_5_edc0357_1345510113940_348480_14070_endTime = null;

            public ConstraintExpression constraint221 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510113941_93056_14073_existsDependency = null;

            public Dependency< Power > _17_0_5_edc0357_1345510114754_415963_15049Dependency = null;

            public Effect effect222 = null;

            public Parameter effect222Var = null;

            public ElaborationRule elaborationRule223 = null;

            public void init_17_0_5_edc0357_1345510113938_215257_14067Members() {
                try {
                    _17_0_5_edc0357_1345510113941_93056_14073_exists = new BooleanParameter("_17_0_5_edc0357_1345510113941_93056_14073_exists", this);
                    _17_0_5_edc0357_1345510114754_415963_15049 = new Parameter<Power>("_17_0_5_edc0357_1345510114754_415963_15049", null, null, this);
                    _17_0_5_edc0357_1345510114754_755936_15048 = new IntegerParameter("_17_0_5_edc0357_1345510114754_755936_15048", this);
                    _17_0_5_edc0357_1345510113940_348480_14070_endTime = new IntegerParameter("_17_0_5_edc0357_1345510113940_348480_14070_endTime", this);
                    constraint221 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510113940_348480_14070_endTime)));
                    _17_0_5_edc0357_1345510113941_93056_14073_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510113941_93056_14073_exists, new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510113943_183272_14085, Utils.getMethodForArgTypes("ObjectFlow<Boolean>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })));
                    _17_0_5_edc0357_1345510114754_415963_15049Dependency = new Dependency<Power>(_17_0_5_edc0357_1345510114754_415963_15049, new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510113942_415807_14078, Utils.getMethodForArgTypes("ObjectFlow<Power>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    Object effect222VarV = load__17_0_5_edc0357_1345510113546_825610_13764;
                    effect222Var = new Parameter("effect222Var", null, null, this);
                    addDependency(effect222Var, new Expression(effect222VarV));
                    effect222 = new EffectFunction(new FunctionCall(effect222Var, Utils.getMethodForArgTypes("TimeVaryingMap<Integer>", "generated", "setValue", java.lang.Integer.class, java.lang.Object.class), new Object[] { startTime, _17_0_5_edc0357_1345510114754_755936_15048 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510113938_215257_14067Collections() {
                parameters.add(_17_0_5_edc0357_1345510113941_93056_14073_exists);
                parameters.add(_17_0_5_edc0357_1345510114754_415963_15049);
                parameters.add(_17_0_5_edc0357_1345510114754_755936_15048);
                parameters.add(_17_0_5_edc0357_1345510113940_348480_14070_endTime);
                constraintExpressions.add(constraint221);
                dependencies.add(_17_0_5_edc0357_1345510113941_93056_14073_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510114754_415963_15049Dependency);
                Set<Effect> effectsForeffect222Var = new HashSet<Effect>();
                effectsForeffect222Var.add(effect222);
                effects.put((Parameter<?>) effect222Var, effectsForeffect222Var);
            }

            public void init_17_0_5_edc0357_1345510113938_215257_14067Elaborations() {
                Expression<?>[] arguments223 = new Expression<?>[1];
                arguments223[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition223 = new Expression<Boolean>(_17_0_5_edc0357_1345510113941_93056_14073_exists);
                elaborationRule223 = addElaborationRule(condition223, _17_0_5_edc0357_1345510113542_666806_13761.this, Power._17_0_5_edc0357_1345510113542_666806_13761._17_0_5_edc0357_1345510113941_93056_14073.class, "end_join_JoinNode_intialize", arguments223);
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

            public Parameter< Power > _17_0_5_edc0357_1345510114756_517661_15051 = null;

            public ConstraintExpression constraint224 = null;

            public Dependency< Power > _17_0_5_edc0357_1345510114756_517661_15051Dependency = null;

            public Effect effect225 = null;

            public Parameter effect225Var = null;

            public Effect effect226 = null;

            public Parameter effect226Var = null;

            public void init_17_0_5_edc0357_1345510113938_889571_14068Members() {
                try {
                    _17_0_5_edc0357_1345510114755_802377_15050 = new IntegerParameter("_17_0_5_edc0357_1345510114755_802377_15050", this);
                    _17_0_5_edc0357_1345510113939_719662_14069_endTime = new IntegerParameter("_17_0_5_edc0357_1345510113939_719662_14069_endTime", this);
                    _17_0_5_edc0357_1345510114756_517661_15051 = new Parameter<Power>("_17_0_5_edc0357_1345510114756_517661_15051", null, null, this);
                    constraint224 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510113939_719662_14069_endTime)));
                    _17_0_5_edc0357_1345510114756_517661_15051Dependency = new Dependency<Power>(_17_0_5_edc0357_1345510114756_517661_15051, new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510113942_807692_14079, Utils.getMethodForArgTypes("ObjectFlow<Power>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    Object effect225VarV = sig_17_0_5_edc0357_1345510113943_183272_14085;
                    effect225Var = new Parameter("effect225Var", null, null, this);
                    addDependency(effect225Var, new Expression(effect225VarV));
                    effect225 = new EffectFunction(new FunctionCall(effect225Var, Utils.getMethodForArgTypes("ObjectFlow<Boolean>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { true, startTime }));
                    Object effect226VarV = power__17_0_5_edc0357_1345510113545_356064_13763;
                    effect226Var = new Parameter("effect226Var", null, null, this);
                    addDependency(effect226Var, new Expression(effect226VarV));
                    effect226 = new EffectFunction(new FunctionCall(effect226Var, Utils.getMethodForArgTypes("TimeVaryingMap<Integer>", "generated", "setValue", java.lang.Integer.class, java.lang.Object.class), new Object[] { startTime, _17_0_5_edc0357_1345510114755_802377_15050 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510113938_889571_14068Collections() {
                parameters.add(_17_0_5_edc0357_1345510114755_802377_15050);
                parameters.add(_17_0_5_edc0357_1345510113939_719662_14069_endTime);
                parameters.add(_17_0_5_edc0357_1345510114756_517661_15051);
                constraintExpressions.add(constraint224);
                dependencies.add(_17_0_5_edc0357_1345510114756_517661_15051Dependency);
                Set<Effect> effectsForeffect225Var = new HashSet<Effect>();
                effectsForeffect225Var.add(effect225);
                effects.put((Parameter<?>) effect225Var, effectsForeffect225Var);
                Set<Effect> effectsForeffect226Var = new HashSet<Effect>();
                effectsForeffect226Var.add(effect226);
                effects.put((Parameter<?>) effect226Var, effectsForeffect226Var);
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

            public ConstraintExpression constraint227 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510113938_889571_14068_existsDependency = null;

            public ElaborationRule elaborationRule228 = null;

            public void init_17_0_5_edc0357_1345510113939_719662_14069Members() {
                try {
                    _17_0_5_edc0357_1345510113938_889571_14068_exists = new BooleanParameter("_17_0_5_edc0357_1345510113938_889571_14068_exists", this);
                    _17_0_5_edc0357_1345510113940_364097_14072_endTime = new IntegerParameter("_17_0_5_edc0357_1345510113940_364097_14072_endTime", this);
                    _17_0_5_edc0357_1345510114757_68713_15053 = new IntegerParameter("_17_0_5_edc0357_1345510114757_68713_15053", this);
                    constraint227 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510113940_364097_14072_endTime)));
                    _17_0_5_edc0357_1345510113938_889571_14068_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510113938_889571_14068_exists, new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510113942_807692_14079, Utils.getMethodForArgTypes("ObjectFlow<Power>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510113939_719662_14069Collections() {
                parameters.add(_17_0_5_edc0357_1345510113938_889571_14068_exists);
                parameters.add(_17_0_5_edc0357_1345510113940_364097_14072_endTime);
                parameters.add(_17_0_5_edc0357_1345510114757_68713_15053);
                constraintExpressions.add(constraint227);
                dependencies.add(_17_0_5_edc0357_1345510113938_889571_14068_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510113939_719662_14069Elaborations() {
                Expression<?>[] arguments228 = new Expression<?>[2];
                arguments228[0] = new Expression<Integer>(endTime);
                arguments228[1] = new Expression<Integer>(_17_0_5_edc0357_1345510114757_68713_15053);
                Expression<Boolean> condition228 = new Expression<Boolean>(_17_0_5_edc0357_1345510113938_889571_14068_exists);
                elaborationRule228 = addElaborationRule(condition228, _17_0_5_edc0357_1345510113542_666806_13761.this, Power._17_0_5_edc0357_1345510113542_666806_13761._17_0_5_edc0357_1345510113938_889571_14068.class, "power_struct_add_AddStructuralFeatureValueAction_intialize", arguments228);
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

            public ConstraintExpression constraint229 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510113938_215257_14067_existsDependency = null;

            public ElaborationRule elaborationRule230 = null;

            public void init_17_0_5_edc0357_1345510113940_348480_14070Members() {
                try {
                    _17_0_5_edc0357_1345510113938_215257_14067_exists = new BooleanParameter("_17_0_5_edc0357_1345510113938_215257_14067_exists", this);
                    _17_0_5_edc0357_1345510114757_633104_15055 = new IntegerParameter("_17_0_5_edc0357_1345510114757_633104_15055", this);
                    _17_0_5_edc0357_1345510113940_364097_14072_endTime = new IntegerParameter("_17_0_5_edc0357_1345510113940_364097_14072_endTime", this);
                    constraint229 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510113940_364097_14072_endTime)));
                    _17_0_5_edc0357_1345510113938_215257_14067_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510113938_215257_14067_exists, new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510113942_415807_14078, Utils.getMethodForArgTypes("ObjectFlow<Power>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510113940_348480_14070Collections() {
                parameters.add(_17_0_5_edc0357_1345510113938_215257_14067_exists);
                parameters.add(_17_0_5_edc0357_1345510114757_633104_15055);
                parameters.add(_17_0_5_edc0357_1345510113940_364097_14072_endTime);
                constraintExpressions.add(constraint229);
                dependencies.add(_17_0_5_edc0357_1345510113938_215257_14067_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510113940_348480_14070Elaborations() {
                Expression<?>[] arguments230 = new Expression<?>[2];
                arguments230[0] = new Expression<Integer>(endTime);
                arguments230[1] = new Expression<Integer>(_17_0_5_edc0357_1345510114757_633104_15055);
                Expression<Boolean> condition230 = new Expression<Boolean>(_17_0_5_edc0357_1345510113938_215257_14067_exists);
                elaborationRule230 = addElaborationRule(condition230, _17_0_5_edc0357_1345510113542_666806_13761.this, Power._17_0_5_edc0357_1345510113542_666806_13761._17_0_5_edc0357_1345510113938_215257_14067.class, "load_struct_add_AddStructuralFeatureValueAction_intialize", arguments230);
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

            public ElaborationRule elaborationRule231 = null;

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
                Expression<?>[] arguments231 = new Expression<?>[1];
                arguments231[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition231 = new Expression<Boolean>(_17_0_5_edc0357_1345510113940_364097_14072_exists);
                elaborationRule231 = addElaborationRule(condition231, _17_0_5_edc0357_1345510113542_666806_13761.this, Power._17_0_5_edc0357_1345510113542_666806_13761._17_0_5_edc0357_1345510113940_364097_14072.class, "start_fok_ForkNode_intialize", arguments231);
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

            public ConstraintExpression constraint232 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510113940_348480_14070_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510113939_719662_14069_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510113936_636752_14065_existsDependency = null;

            public ElaborationRule elaborationRule233 = null;

            public ElaborationRule elaborationRule234 = null;

            public ElaborationRule elaborationRule235 = null;

            public void init_17_0_5_edc0357_1345510113940_364097_14072Members() {
                try {
                    _17_0_5_edc0357_1345510113940_857370_14071_endTime = new IntegerParameter("_17_0_5_edc0357_1345510113940_857370_14071_endTime", this);
                    _17_0_5_edc0357_1345510113940_348480_14070_exists = new BooleanParameter("_17_0_5_edc0357_1345510113940_348480_14070_exists", this);
                    _17_0_5_edc0357_1345510113939_719662_14069_exists = new BooleanParameter("_17_0_5_edc0357_1345510113939_719662_14069_exists", this);
                    _17_0_5_edc0357_1345510113936_636752_14065_exists = new BooleanParameter("_17_0_5_edc0357_1345510113936_636752_14065_exists", this);
                    constraint232 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510113940_857370_14071_endTime)));
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
                constraintExpressions.add(constraint232);
                dependencies.add(_17_0_5_edc0357_1345510113940_348480_14070_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510113939_719662_14069_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510113936_636752_14065_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510113940_364097_14072Elaborations() {
                Expression<?>[] arguments233 = new Expression<?>[1];
                arguments233[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition233 = new Expression<Boolean>(_17_0_5_edc0357_1345510113936_636752_14065_exists);
                elaborationRule233 = addElaborationRule(condition233, _17_0_5_edc0357_1345510113542_666806_13761.this, Power._17_0_5_edc0357_1345510113542_666806_13761._17_0_5_edc0357_1345510113936_636752_14065.class, "readSelf_ReadSelfAction_intialize", arguments233);
                Expression<?>[] arguments234 = new Expression<?>[1];
                arguments234[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition234 = new Expression<Boolean>(_17_0_5_edc0357_1345510113939_719662_14069_exists);
                elaborationRule234 = addElaborationRule(condition234, _17_0_5_edc0357_1345510113542_666806_13761.this, Power._17_0_5_edc0357_1345510113542_666806_13761._17_0_5_edc0357_1345510113939_719662_14069.class, "power_val_spec_ValueSpecificationAction_intialize", arguments234);
                Expression<?>[] arguments235 = new Expression<?>[1];
                arguments235[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition235 = new Expression<Boolean>(_17_0_5_edc0357_1345510113940_348480_14070_exists);
                elaborationRule235 = addElaborationRule(condition235, _17_0_5_edc0357_1345510113542_666806_13761.this, Power._17_0_5_edc0357_1345510113542_666806_13761._17_0_5_edc0357_1345510113940_348480_14070.class, "load_val_spec_ValueSpecificationAction_intialize", arguments235);
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

            public ConstraintExpression constraint236 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510113942_243779_14074_existsDependency = null;

            public ElaborationRule elaborationRule237 = null;

            public void init_17_0_5_edc0357_1345510113941_93056_14073Members() {
                try {
                    _17_0_5_edc0357_1345510113938_215257_14067_endTime = new IntegerParameter("_17_0_5_edc0357_1345510113938_215257_14067_endTime", this);
                    _17_0_5_edc0357_1345510113942_243779_14074_exists = new BooleanParameter("_17_0_5_edc0357_1345510113942_243779_14074_exists", this);
                    constraint236 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510113938_215257_14067_endTime)));
                    _17_0_5_edc0357_1345510113942_243779_14074_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510113942_243779_14074_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510113941_93056_14073Collections() {
                parameters.add(_17_0_5_edc0357_1345510113938_215257_14067_endTime);
                parameters.add(_17_0_5_edc0357_1345510113942_243779_14074_exists);
                constraintExpressions.add(constraint236);
                dependencies.add(_17_0_5_edc0357_1345510113942_243779_14074_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510113941_93056_14073Elaborations() {
                Expression<?>[] arguments237 = new Expression<?>[1];
                arguments237[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition237 = new Expression<Boolean>(_17_0_5_edc0357_1345510113942_243779_14074_exists);
                elaborationRule237 = addElaborationRule(condition237, _17_0_5_edc0357_1345510113542_666806_13761.this, Power._17_0_5_edc0357_1345510113542_666806_13761._17_0_5_edc0357_1345510113942_243779_14074.class, "_ActivityFinalNode_intialize", arguments237);
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

            public ConstraintExpression constraint238 = null;

            public void init_17_0_5_edc0357_1345510113942_243779_14074Members() {
                try {
                    _17_0_5_edc0357_1345510113941_93056_14073_endTime = new IntegerParameter("_17_0_5_edc0357_1345510113941_93056_14073_endTime", this);
                    constraint238 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510113941_93056_14073_endTime)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510113942_243779_14074Collections() {
                parameters.add(_17_0_5_edc0357_1345510113941_93056_14073_endTime);
                constraintExpressions.add(constraint238);
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

        public Parameter< ObjectFlow<Boolean> > sig_17_0_5_edc0357_1345510113965_962222_14133 = null;

        public Parameter< ObjectFlow<Power> > sig_17_0_5_edc0357_1345510113964_837067_14124 = null;

        public Parameter< ObjectFlow<Power> > sig_17_0_5_edc0357_1345510113964_881600_14125 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_5_edc0357_1345510113963_14719_14119 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_5_edc0357_1345510113964_263672_14120 = null;

        public Parameter< ObjectFlow<Power> > sig_17_0_5_edc0357_1345510113964_266967_14123 = null;

        public Parameter< ObjectFlow<Power> > sig_17_0_5_edc0357_1345510113964_922533_14122 = null;

        public IntegerParameter invoke_time = null;

        public ElaborationRule elaborationRule239 = null;

        public void init_17_0_5_edc0357_1345510113544_941994_13762Members() {
            try {
                sig_17_0_5_edc0357_1345510113965_962222_14133 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_5_edc0357_1345510113965_962222_14133", null, new ObjectFlow("sig_17_0_5_edc0357_1345510113965_962222_14133"), this);
                sig_17_0_5_edc0357_1345510113964_837067_14124 = new Parameter<ObjectFlow<Power>>("sig_17_0_5_edc0357_1345510113964_837067_14124", null, new ObjectFlow("sig_17_0_5_edc0357_1345510113964_837067_14124"), this);
                sig_17_0_5_edc0357_1345510113964_881600_14125 = new Parameter<ObjectFlow<Power>>("sig_17_0_5_edc0357_1345510113964_881600_14125", null, new ObjectFlow("sig_17_0_5_edc0357_1345510113964_881600_14125"), this);
                sig_17_0_5_edc0357_1345510113963_14719_14119 = new Parameter<ObjectFlow<Integer>>("sig_17_0_5_edc0357_1345510113963_14719_14119", null, new ObjectFlow("sig_17_0_5_edc0357_1345510113963_14719_14119"), this);
                sig_17_0_5_edc0357_1345510113964_263672_14120 = new Parameter<ObjectFlow<Integer>>("sig_17_0_5_edc0357_1345510113964_263672_14120", null, new ObjectFlow("sig_17_0_5_edc0357_1345510113964_263672_14120"), this);
                sig_17_0_5_edc0357_1345510113964_266967_14123 = new Parameter<ObjectFlow<Power>>("sig_17_0_5_edc0357_1345510113964_266967_14123", null, new ObjectFlow("sig_17_0_5_edc0357_1345510113964_266967_14123"), this);
                sig_17_0_5_edc0357_1345510113964_922533_14122 = new Parameter<ObjectFlow<Power>>("sig_17_0_5_edc0357_1345510113964_922533_14122", null, new ObjectFlow("sig_17_0_5_edc0357_1345510113964_922533_14122"), this);
                invoke_time = new IntegerParameter("invoke_time", this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void init_17_0_5_edc0357_1345510113544_941994_13762Collections() {
            parameters.add(sig_17_0_5_edc0357_1345510113965_962222_14133);
            parameters.add(sig_17_0_5_edc0357_1345510113964_837067_14124);
            parameters.add(sig_17_0_5_edc0357_1345510113964_881600_14125);
            parameters.add(sig_17_0_5_edc0357_1345510113963_14719_14119);
            parameters.add(sig_17_0_5_edc0357_1345510113964_263672_14120);
            parameters.add(sig_17_0_5_edc0357_1345510113964_266967_14123);
            parameters.add(sig_17_0_5_edc0357_1345510113964_922533_14122);
            parameters.add(invoke_time);
        }

        public void init_17_0_5_edc0357_1345510113544_941994_13762Elaborations() {
            Expression<?>[] arguments239 = new Expression<?>[1];
            arguments239[0] = new Expression<Integer>(invoke_time);
            Expression<Boolean> condition239 = new Expression<Boolean>(true);
            elaborationRule239 = addElaborationRule(condition239, _17_0_5_edc0357_1345510113544_941994_13762.this, Power._17_0_5_edc0357_1345510113544_941994_13762._17_0_5_edc0357_1345510113957_930465_14109.class, "start_InitialNode_generate", arguments239);
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

            public Parameter< Power > _17_0_5_edc0357_1345510114764_172311_15068 = null;

            public ConstraintExpression constraint240 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510113961_883060_14115_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510113963_167569_14117_existsDependency = null;

            public ElaborationRule elaborationRule241 = null;

            public ElaborationRule elaborationRule242 = null;

            public void init_17_0_5_edc0357_1345510113957_678472_14108Members() {
                try {
                    _17_0_5_edc0357_1345510113961_883060_14115_exists = new BooleanParameter("_17_0_5_edc0357_1345510113961_883060_14115_exists", this);
                    _17_0_5_edc0357_1345510113957_930465_14109_endTime = new IntegerParameter("_17_0_5_edc0357_1345510113957_930465_14109_endTime", this);
                    _17_0_5_edc0357_1345510113963_167569_14117_exists = new BooleanParameter("_17_0_5_edc0357_1345510113963_167569_14117_exists", this);
                    _17_0_5_edc0357_1345510114764_172311_15068 = new Parameter<Power>("_17_0_5_edc0357_1345510114764_172311_15068", null, Power.this, this);
                    constraint240 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510113957_930465_14109_endTime)));
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
                constraintExpressions.add(constraint240);
                dependencies.add(_17_0_5_edc0357_1345510113961_883060_14115_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510113963_167569_14117_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510113957_678472_14108Elaborations() {
                Expression<?>[] arguments241 = new Expression<?>[1];
                arguments241[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition241 = new Expression<Boolean>(_17_0_5_edc0357_1345510113963_167569_14117_exists);
                elaborationRule241 = addElaborationRule(condition241, _17_0_5_edc0357_1345510113544_941994_13762.this, Power._17_0_5_edc0357_1345510113544_941994_13762._17_0_5_edc0357_1345510113963_167569_14117.class, "control_fork_readself_ForkNode_generate", arguments241);
                Expression<?>[] arguments242 = new Expression<?>[2];
                arguments242[0] = new Expression<Integer>(endTime);
                arguments242[1] = new Expression<Power>(_17_0_5_edc0357_1345510114764_172311_15068);
                Expression<Boolean> condition242 = new Expression<Boolean>(_17_0_5_edc0357_1345510113961_883060_14115_exists);
                elaborationRule242 = addElaborationRule(condition242, _17_0_5_edc0357_1345510113544_941994_13762.this, Power._17_0_5_edc0357_1345510113544_941994_13762._17_0_5_edc0357_1345510113961_883060_14115.class, "ob_fork_self_ForkNode_generate", arguments242);
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

            public ElaborationRule elaborationRule243 = null;

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
                Expression<?>[] arguments243 = new Expression<?>[1];
                arguments243[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition243 = new Expression<Boolean>(_17_0_5_edc0357_1345510113957_678472_14108_exists);
                elaborationRule243 = addElaborationRule(condition243, _17_0_5_edc0357_1345510113544_941994_13762.this, Power._17_0_5_edc0357_1345510113544_941994_13762._17_0_5_edc0357_1345510113957_678472_14108.class, "readself_ReadSelfAction_generate", arguments243);
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

            public ConstraintExpression constraint244 = null;

            public void init_17_0_5_edc0357_1345510113958_150027_14110Members() {
                try {
                    _17_0_5_edc0357_1345510113962_750982_14116_endTime = new IntegerParameter("_17_0_5_edc0357_1345510113962_750982_14116_endTime", this);
                    constraint244 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510113962_750982_14116_endTime)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510113958_150027_14110Collections() {
                parameters.add(_17_0_5_edc0357_1345510113962_750982_14116_endTime);
                constraintExpressions.add(constraint244);
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

            public Parameter< Power > _17_0_5_edc0357_1345510114765_157490_15070 = null;

            public IntegerParameter _17_0_5_edc0357_1345510114765_286898_15069 = null;

            public BooleanParameter _17_0_5_edc0357_1345510113960_661152_14113_exists = null;

            public ConstraintExpression constraint245 = null;

            public Dependency< Power > _17_0_5_edc0357_1345510114765_157490_15070Dependency = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114765_286898_15069Dependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510113960_661152_14113_existsDependency = null;

            public Effect effect246 = null;

            public Parameter effect246Var = null;

            public ElaborationRule elaborationRule247 = null;

            public void init_17_0_5_edc0357_1345510113959_502637_14111Members() {
                try {
                    _17_0_5_edc0357_1345510113963_167569_14117_endTime = new IntegerParameter("_17_0_5_edc0357_1345510113963_167569_14117_endTime", this);
                    _17_0_5_edc0357_1345510114765_157490_15070 = new Parameter<Power>("_17_0_5_edc0357_1345510114765_157490_15070", null, null, this);
                    _17_0_5_edc0357_1345510114765_286898_15069 = new IntegerParameter("_17_0_5_edc0357_1345510114765_286898_15069", this);
                    _17_0_5_edc0357_1345510113960_661152_14113_exists = new BooleanParameter("_17_0_5_edc0357_1345510113960_661152_14113_exists", this);
                    constraint245 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510113963_167569_14117_endTime)));
                    _17_0_5_edc0357_1345510114765_157490_15070Dependency = new Dependency<Power>(_17_0_5_edc0357_1345510114765_157490_15070, new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510113964_922533_14122, Utils.getMethodForArgTypes("ObjectFlow<Power>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1345510114765_286898_15069Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114765_286898_15069, new Expression(new FunctionCall(null, Utils.getMethodForArgTypes("Power._17_0_5_edc0357_1345510113544_941994_13762._17_0_5_edc0357_1345510113959_502637_14111", "generated", "getValue"), new Object[] { new Expression<Integer>(startTime) }, new FunctionCall(null, Parameter.class, "getValue", (Object[]) null, new FunctionCall(_17_0_5_edc0357_1345510114765_157490_15070, Parameter.class, "getMember", new Object[] { "power__17_0_5_edc0357_1345510113545_356064_13763" })))));
                    _17_0_5_edc0357_1345510113960_661152_14113_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510113960_661152_14113_exists, new Functions.And(new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510113964_263672_14120, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510113964_837067_14124, Utils.getMethodForArgTypes("ObjectFlow<Power>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) }))));
                    Object effect246VarV = sig_17_0_5_edc0357_1345510113964_263672_14120;
                    effect246Var = new Parameter("effect246Var", null, null, this);
                    addDependency(effect246Var, new Expression(effect246VarV));
                    effect246 = new EffectFunction(new FunctionCall(effect246Var, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { _17_0_5_edc0357_1345510114765_286898_15069, startTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510113959_502637_14111Collections() {
                parameters.add(_17_0_5_edc0357_1345510113963_167569_14117_endTime);
                parameters.add(_17_0_5_edc0357_1345510114765_157490_15070);
                parameters.add(_17_0_5_edc0357_1345510114765_286898_15069);
                parameters.add(_17_0_5_edc0357_1345510113960_661152_14113_exists);
                constraintExpressions.add(constraint245);
                dependencies.add(_17_0_5_edc0357_1345510114765_157490_15070Dependency);
                dependencies.add(_17_0_5_edc0357_1345510114765_286898_15069Dependency);
                dependencies.add(_17_0_5_edc0357_1345510113960_661152_14113_existsDependency);
                Set<Effect> effectsForeffect246Var = new HashSet<Effect>();
                effectsForeffect246Var.add(effect246);
                effects.put((Parameter<?>) effect246Var, effectsForeffect246Var);
            }

            public void init_17_0_5_edc0357_1345510113959_502637_14111Elaborations() {
                Expression<?>[] arguments247 = new Expression<?>[1];
                arguments247[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition247 = new Expression<Boolean>(_17_0_5_edc0357_1345510113960_661152_14113_exists);
                elaborationRule247 = addElaborationRule(condition247, _17_0_5_edc0357_1345510113544_941994_13762.this, Power._17_0_5_edc0357_1345510113544_941994_13762._17_0_5_edc0357_1345510113960_661152_14113.class, "send_gen_reading_SendSignalAction_generate", arguments247);
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

            public Parameter< Power > _17_0_5_edc0357_1345510114767_768687_15072 = null;

            public BooleanParameter _17_0_5_edc0357_1345510113961_83686_14114_exists = null;

            public ConstraintExpression constraint248 = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114766_724321_15071Dependency = null;

            public Dependency< Power > _17_0_5_edc0357_1345510114767_768687_15072Dependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510113961_83686_14114_existsDependency = null;

            public Effect effect249 = null;

            public Parameter effect249Var = null;

            public ElaborationRule elaborationRule250 = null;

            public void init_17_0_5_edc0357_1345510113959_577677_14112Members() {
                try {
                    _17_0_5_edc0357_1345510113963_167569_14117_endTime = new IntegerParameter("_17_0_5_edc0357_1345510113963_167569_14117_endTime", this);
                    _17_0_5_edc0357_1345510114766_724321_15071 = new IntegerParameter("_17_0_5_edc0357_1345510114766_724321_15071", this);
                    _17_0_5_edc0357_1345510114767_768687_15072 = new Parameter<Power>("_17_0_5_edc0357_1345510114767_768687_15072", null, null, this);
                    _17_0_5_edc0357_1345510113961_83686_14114_exists = new BooleanParameter("_17_0_5_edc0357_1345510113961_83686_14114_exists", this);
                    constraint248 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510113963_167569_14117_endTime)));
                    _17_0_5_edc0357_1345510114766_724321_15071Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114766_724321_15071, new Expression(new FunctionCall(null, Utils.getMethodForArgTypes("Power._17_0_5_edc0357_1345510113544_941994_13762._17_0_5_edc0357_1345510113959_577677_14112", "generated", "getValue"), new Object[] { new Expression<Integer>(startTime) }, new FunctionCall(null, Parameter.class, "getValue", (Object[]) null, new FunctionCall(_17_0_5_edc0357_1345510114767_768687_15072, Parameter.class, "getMember", new Object[] { "load__17_0_5_edc0357_1345510113546_825610_13764" })))));
                    _17_0_5_edc0357_1345510114767_768687_15072Dependency = new Dependency<Power>(_17_0_5_edc0357_1345510114767_768687_15072, new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510113964_266967_14123, Utils.getMethodForArgTypes("ObjectFlow<Power>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1345510113961_83686_14114_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510113961_83686_14114_exists, new Functions.And(new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510113963_14719_14119, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510113964_881600_14125, Utils.getMethodForArgTypes("ObjectFlow<Power>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) }))));
                    Object effect249VarV = sig_17_0_5_edc0357_1345510113963_14719_14119;
                    effect249Var = new Parameter("effect249Var", null, null, this);
                    addDependency(effect249Var, new Expression(effect249VarV));
                    effect249 = new EffectFunction(new FunctionCall(effect249Var, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { _17_0_5_edc0357_1345510114766_724321_15071, startTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510113959_577677_14112Collections() {
                parameters.add(_17_0_5_edc0357_1345510113963_167569_14117_endTime);
                parameters.add(_17_0_5_edc0357_1345510114766_724321_15071);
                parameters.add(_17_0_5_edc0357_1345510114767_768687_15072);
                parameters.add(_17_0_5_edc0357_1345510113961_83686_14114_exists);
                constraintExpressions.add(constraint248);
                dependencies.add(_17_0_5_edc0357_1345510114766_724321_15071Dependency);
                dependencies.add(_17_0_5_edc0357_1345510114767_768687_15072Dependency);
                dependencies.add(_17_0_5_edc0357_1345510113961_83686_14114_existsDependency);
                Set<Effect> effectsForeffect249Var = new HashSet<Effect>();
                effectsForeffect249Var.add(effect249);
                effects.put((Parameter<?>) effect249Var, effectsForeffect249Var);
            }

            public void init_17_0_5_edc0357_1345510113959_577677_14112Elaborations() {
                Expression<?>[] arguments250 = new Expression<?>[1];
                arguments250[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition250 = new Expression<Boolean>(_17_0_5_edc0357_1345510113961_83686_14114_exists);
                elaborationRule250 = addElaborationRule(condition250, _17_0_5_edc0357_1345510113544_941994_13762.this, Power._17_0_5_edc0357_1345510113544_941994_13762._17_0_5_edc0357_1345510113961_83686_14114.class, "send_load_reading_SendSignalAction_generate", arguments250);
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

            public Parameter< Power > _17_0_5_edc0357_1345510114767_751097_15073 = null;

            public BooleanParameter _17_0_5_edc0357_1345510113962_750982_14116_exists = null;

            public IntegerParameter _17_0_5_edc0357_1345510113959_502637_14111_endTime = null;

            public ConstraintExpression constraint251 = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114768_446406_15074Dependency = null;

            public Dependency< Power > _17_0_5_edc0357_1345510114767_751097_15073Dependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510113962_750982_14116_existsDependency = null;

            public Effect effect252 = null;

            public Parameter effect252Var = null;

            public ElaborationRule elaborationRule253 = null;

            public void init_17_0_5_edc0357_1345510113960_661152_14113Members() {
                try {
                    _17_0_5_edc0357_1345510114768_446406_15074 = new IntegerParameter("_17_0_5_edc0357_1345510114768_446406_15074", this);
                    _17_0_5_edc0357_1345510114767_751097_15073 = new Parameter<Power>("_17_0_5_edc0357_1345510114767_751097_15073", null, null, this);
                    _17_0_5_edc0357_1345510113962_750982_14116_exists = new BooleanParameter("_17_0_5_edc0357_1345510113962_750982_14116_exists", this);
                    _17_0_5_edc0357_1345510113959_502637_14111_endTime = new IntegerParameter("_17_0_5_edc0357_1345510113959_502637_14111_endTime", this);
                    constraint251 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510113959_502637_14111_endTime)));
                    _17_0_5_edc0357_1345510114768_446406_15074Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114768_446406_15074, new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510113964_263672_14120, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1345510114767_751097_15073Dependency = new Dependency<Power>(_17_0_5_edc0357_1345510114767_751097_15073, new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510113964_837067_14124, Utils.getMethodForArgTypes("ObjectFlow<Power>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1345510113962_750982_14116_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510113962_750982_14116_exists, new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510113965_962222_14133, Utils.getMethodForArgTypes("ObjectFlow<Boolean>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })));
                    Object effect252VarV = new FunctionCall(null, Parameter.class, "getValue", (Object[]) null, new FunctionCall(x, Parameter.class, "getMember", new Object[] { "ss_17_0_5_edc0357_1345510113550_816176_13768_receiveGenReading" }));
                    effect252Var = new Parameter("effect252Var", null, null, this);
                    addDependency(effect252Var, new Expression(effect252VarV));
                    effect252 = new EffectFunction(new FunctionCall(null, Utils.getMethodForArgTypes("Power._17_0_5_edc0357_1345510113544_941994_13762._17_0_5_edc0357_1345510113960_661152_14113", "generated", "send"), new Object[] { x.getValue().new SignalreceiveGenReading(endTime.getValue(), _17_0_5_edc0357_1345510114768_446406_15074.getValue()), endTime }, effect252Var));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510113960_661152_14113Collections() {
                parameters.add(_17_0_5_edc0357_1345510114768_446406_15074);
                parameters.add(_17_0_5_edc0357_1345510114767_751097_15073);
                parameters.add(_17_0_5_edc0357_1345510113962_750982_14116_exists);
                parameters.add(_17_0_5_edc0357_1345510113959_502637_14111_endTime);
                constraintExpressions.add(constraint251);
                dependencies.add(_17_0_5_edc0357_1345510114768_446406_15074Dependency);
                dependencies.add(_17_0_5_edc0357_1345510114767_751097_15073Dependency);
                dependencies.add(_17_0_5_edc0357_1345510113962_750982_14116_existsDependency);
                Set<Effect> effectsForeffect252Var = new HashSet<Effect>();
                effectsForeffect252Var.add(effect252);
                effects.put((Parameter<?>) effect252Var, effectsForeffect252Var);
            }

            public void init_17_0_5_edc0357_1345510113960_661152_14113Elaborations() {
                Expression<?>[] arguments253 = new Expression<?>[1];
                arguments253[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition253 = new Expression<Boolean>(_17_0_5_edc0357_1345510113962_750982_14116_exists);
                elaborationRule253 = addElaborationRule(condition253, _17_0_5_edc0357_1345510113544_941994_13762.this, Power._17_0_5_edc0357_1345510113544_941994_13762._17_0_5_edc0357_1345510113962_750982_14116.class, "ctrl_fork_end_JoinNode_generate", arguments253);
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

            public Parameter< Power > _17_0_5_edc0357_1345510114769_461455_15075 = null;

            public IntegerParameter _17_0_5_edc0357_1345510113959_577677_14112_endTime = null;

            public ConstraintExpression constraint254 = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114769_966831_15076Dependency = null;

            public Dependency< Power > _17_0_5_edc0357_1345510114769_461455_15075Dependency = null;

            public Effect effect255 = null;

            public Parameter effect255Var = null;

            public Effect effect256 = null;

            public Parameter effect256Var = null;

            public void init_17_0_5_edc0357_1345510113961_83686_14114Members() {
                try {
                    _17_0_5_edc0357_1345510114769_966831_15076 = new IntegerParameter("_17_0_5_edc0357_1345510114769_966831_15076", this);
                    _17_0_5_edc0357_1345510114769_461455_15075 = new Parameter<Power>("_17_0_5_edc0357_1345510114769_461455_15075", null, null, this);
                    _17_0_5_edc0357_1345510113959_577677_14112_endTime = new IntegerParameter("_17_0_5_edc0357_1345510113959_577677_14112_endTime", this);
                    constraint254 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510113959_577677_14112_endTime)));
                    _17_0_5_edc0357_1345510114769_966831_15076Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114769_966831_15076, new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510113963_14719_14119, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1345510114769_461455_15075Dependency = new Dependency<Power>(_17_0_5_edc0357_1345510114769_461455_15075, new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510113964_881600_14125, Utils.getMethodForArgTypes("ObjectFlow<Power>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    Object effect255VarV = sig_17_0_5_edc0357_1345510113965_962222_14133;
                    effect255Var = new Parameter("effect255Var", null, null, this);
                    addDependency(effect255Var, new Expression(effect255VarV));
                    effect255 = new EffectFunction(new FunctionCall(effect255Var, Utils.getMethodForArgTypes("ObjectFlow<Boolean>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { true, startTime }));
                    Object effect256VarV = new FunctionCall(null, Parameter.class, "getValue", (Object[]) null, new FunctionCall(x, Parameter.class, "getMember", new Object[] { "ss_17_0_5_edc0357_1345510113549_985081_13767_receiveLoadReading" }));
                    effect256Var = new Parameter("effect256Var", null, null, this);
                    addDependency(effect256Var, new Expression(effect256VarV));
                    effect256 = new EffectFunction(new FunctionCall(null, Utils.getMethodForArgTypes("Power._17_0_5_edc0357_1345510113544_941994_13762._17_0_5_edc0357_1345510113961_83686_14114", "generated", "send"), new Object[] { x.getValue().new SignalreceiveLoadReading(endTime.getValue(), _17_0_5_edc0357_1345510114769_966831_15076.getValue()), endTime }, effect256Var));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510113961_83686_14114Collections() {
                parameters.add(_17_0_5_edc0357_1345510114769_966831_15076);
                parameters.add(_17_0_5_edc0357_1345510114769_461455_15075);
                parameters.add(_17_0_5_edc0357_1345510113959_577677_14112_endTime);
                constraintExpressions.add(constraint254);
                dependencies.add(_17_0_5_edc0357_1345510114769_966831_15076Dependency);
                dependencies.add(_17_0_5_edc0357_1345510114769_461455_15075Dependency);
                Set<Effect> effectsForeffect255Var = new HashSet<Effect>();
                effectsForeffect255Var.add(effect255);
                effects.put((Parameter<?>) effect255Var, effectsForeffect255Var);
                Set<Effect> effectsForeffect256Var = new HashSet<Effect>();
                effectsForeffect256Var.add(effect256);
                effects.put((Parameter<?>) effect256Var, effectsForeffect256Var);
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

            public Parameter< Power > objectToPass = null;

            public ConstraintExpression constraint257 = null;

            public Effect effect258 = null;

            public Parameter effect258Var = null;

            public Effect effect259 = null;

            public Parameter effect259Var = null;

            public Effect effect260 = null;

            public Parameter effect260Var = null;

            public Effect effect261 = null;

            public Parameter effect261Var = null;

            public void init_17_0_5_edc0357_1345510113961_883060_14115Members() {
                try {
                    _17_0_5_edc0357_1345510113957_678472_14108_endTime = new IntegerParameter("_17_0_5_edc0357_1345510113957_678472_14108_endTime", this);
                    objectToPass = new Parameter<Power>("objectToPass", null, null, this);
                    constraint257 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510113957_678472_14108_endTime)));
                    Object effect258VarV = sig_17_0_5_edc0357_1345510113964_922533_14122;
                    effect258Var = new Parameter("effect258Var", null, null, this);
                    addDependency(effect258Var, new Expression(effect258VarV));
                    effect258 = new EffectFunction(new FunctionCall(effect258Var, Utils.getMethodForArgTypes("ObjectFlow<Power>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                    Object effect259VarV = sig_17_0_5_edc0357_1345510113964_266967_14123;
                    effect259Var = new Parameter("effect259Var", null, null, this);
                    addDependency(effect259Var, new Expression(effect259VarV));
                    effect259 = new EffectFunction(new FunctionCall(effect259Var, Utils.getMethodForArgTypes("ObjectFlow<Power>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                    Object effect260VarV = sig_17_0_5_edc0357_1345510113964_837067_14124;
                    effect260Var = new Parameter("effect260Var", null, null, this);
                    addDependency(effect260Var, new Expression(effect260VarV));
                    effect260 = new EffectFunction(new FunctionCall(effect260Var, Utils.getMethodForArgTypes("ObjectFlow<Power>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                    Object effect261VarV = sig_17_0_5_edc0357_1345510113964_881600_14125;
                    effect261Var = new Parameter("effect261Var", null, null, this);
                    addDependency(effect261Var, new Expression(effect261VarV));
                    effect261 = new EffectFunction(new FunctionCall(effect261Var, Utils.getMethodForArgTypes("ObjectFlow<Power>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510113961_883060_14115Collections() {
                parameters.add(_17_0_5_edc0357_1345510113957_678472_14108_endTime);
                parameters.add(objectToPass);
                constraintExpressions.add(constraint257);
                Set<Effect> effectsForeffect258Var = new HashSet<Effect>();
                effectsForeffect258Var.add(effect258);
                effects.put((Parameter<?>) effect258Var, effectsForeffect258Var);
                Set<Effect> effectsForeffect259Var = new HashSet<Effect>();
                effectsForeffect259Var.add(effect259);
                effects.put((Parameter<?>) effect259Var, effectsForeffect259Var);
                Set<Effect> effectsForeffect260Var = new HashSet<Effect>();
                effectsForeffect260Var.add(effect260);
                effects.put((Parameter<?>) effect260Var, effectsForeffect260Var);
                Set<Effect> effectsForeffect261Var = new HashSet<Effect>();
                effectsForeffect261Var.add(effect261);
                effects.put((Parameter<?>) effect261Var, effectsForeffect261Var);
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

            public ConstraintExpression constraint262 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510113958_150027_14110_existsDependency = null;

            public ElaborationRule elaborationRule263 = null;

            public void init_17_0_5_edc0357_1345510113962_750982_14116Members() {
                try {
                    _17_0_5_edc0357_1345510113958_150027_14110_exists = new BooleanParameter("_17_0_5_edc0357_1345510113958_150027_14110_exists", this);
                    _17_0_5_edc0357_1345510113960_661152_14113_endTime = new IntegerParameter("_17_0_5_edc0357_1345510113960_661152_14113_endTime", this);
                    constraint262 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510113960_661152_14113_endTime)));
                    _17_0_5_edc0357_1345510113958_150027_14110_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510113958_150027_14110_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510113962_750982_14116Collections() {
                parameters.add(_17_0_5_edc0357_1345510113958_150027_14110_exists);
                parameters.add(_17_0_5_edc0357_1345510113960_661152_14113_endTime);
                constraintExpressions.add(constraint262);
                dependencies.add(_17_0_5_edc0357_1345510113958_150027_14110_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510113962_750982_14116Elaborations() {
                Expression<?>[] arguments263 = new Expression<?>[1];
                arguments263[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition263 = new Expression<Boolean>(_17_0_5_edc0357_1345510113958_150027_14110_exists);
                elaborationRule263 = addElaborationRule(condition263, _17_0_5_edc0357_1345510113544_941994_13762.this, Power._17_0_5_edc0357_1345510113544_941994_13762._17_0_5_edc0357_1345510113958_150027_14110.class, "end_ActivityFinalNode_generate", arguments263);
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

            public ConstraintExpression constraint264 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510113959_502637_14111_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510113959_577677_14112_existsDependency = null;

            public ElaborationRule elaborationRule265 = null;

            public ElaborationRule elaborationRule266 = null;

            public void init_17_0_5_edc0357_1345510113963_167569_14117Members() {
                try {
                    _17_0_5_edc0357_1345510113957_678472_14108_endTime = new IntegerParameter("_17_0_5_edc0357_1345510113957_678472_14108_endTime", this);
                    _17_0_5_edc0357_1345510113959_502637_14111_exists = new BooleanParameter("_17_0_5_edc0357_1345510113959_502637_14111_exists", this);
                    _17_0_5_edc0357_1345510113959_577677_14112_exists = new BooleanParameter("_17_0_5_edc0357_1345510113959_577677_14112_exists", this);
                    constraint264 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510113957_678472_14108_endTime)));
                    _17_0_5_edc0357_1345510113959_502637_14111_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510113959_502637_14111_exists, new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510113964_922533_14122, Utils.getMethodForArgTypes("ObjectFlow<Power>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })));
                    _17_0_5_edc0357_1345510113959_577677_14112_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510113959_577677_14112_exists, new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510113964_266967_14123, Utils.getMethodForArgTypes("ObjectFlow<Power>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510113963_167569_14117Collections() {
                parameters.add(_17_0_5_edc0357_1345510113957_678472_14108_endTime);
                parameters.add(_17_0_5_edc0357_1345510113959_502637_14111_exists);
                parameters.add(_17_0_5_edc0357_1345510113959_577677_14112_exists);
                constraintExpressions.add(constraint264);
                dependencies.add(_17_0_5_edc0357_1345510113959_502637_14111_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510113959_577677_14112_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510113963_167569_14117Elaborations() {
                Expression<?>[] arguments265 = new Expression<?>[1];
                arguments265[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition265 = new Expression<Boolean>(_17_0_5_edc0357_1345510113959_502637_14111_exists);
                elaborationRule265 = addElaborationRule(condition265, _17_0_5_edc0357_1345510113544_941994_13762.this, Power._17_0_5_edc0357_1345510113544_941994_13762._17_0_5_edc0357_1345510113959_502637_14111.class, "read_power_struct_ReadStructuralFeatureAction_generate", arguments265);
                Expression<?>[] arguments266 = new Expression<?>[1];
                arguments266[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition266 = new Expression<Boolean>(_17_0_5_edc0357_1345510113959_577677_14112_exists);
                elaborationRule266 = addElaborationRule(condition266, _17_0_5_edc0357_1345510113544_941994_13762.this, Power._17_0_5_edc0357_1345510113544_941994_13762._17_0_5_edc0357_1345510113959_577677_14112.class, "read_load_struct_ReadStructuralFeatureAction_generate", arguments266);
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

        public Parameter< ObjectFlow<Power_System.SignalchangeLoadValue> > sig_17_0_5_edc0357_1345510113316_772875_13610 = null;

        public Parameter< ObjectFlow<Power_System.SignalchangeGenerationValue> > sig_17_0_5_edc0357_1345510113315_214400_13609 = null;

        public IntegerParameter invoke_time = null;

        public ElaborationRule elaborationRule267 = null;

        public void init_17_0_5_edc0357_1345510155557_623464_18238Members() {
            try {
                sig_17_0_5_edc0357_1345510113316_772875_13610 = new Parameter<ObjectFlow<Power_System.SignalchangeLoadValue>>("sig_17_0_5_edc0357_1345510113316_772875_13610", null, new ObjectFlow("sig_17_0_5_edc0357_1345510113316_772875_13610"), this);
                sig_17_0_5_edc0357_1345510113315_214400_13609 = new Parameter<ObjectFlow<Power_System.SignalchangeGenerationValue>>("sig_17_0_5_edc0357_1345510113315_214400_13609", null, new ObjectFlow("sig_17_0_5_edc0357_1345510113315_214400_13609"), this);
                invoke_time = new IntegerParameter("invoke_time", this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void init_17_0_5_edc0357_1345510155557_623464_18238Collections() {
            parameters.add(sig_17_0_5_edc0357_1345510113316_772875_13610);
            parameters.add(sig_17_0_5_edc0357_1345510113315_214400_13609);
            parameters.add(invoke_time);
        }

        public void init_17_0_5_edc0357_1345510155557_623464_18238Elaborations() {
            Expression<?>[] arguments267 = new Expression<?>[1];
            arguments267[0] = new Expression<Integer>(invoke_time);
            Expression<Boolean> condition267 = new Expression<Boolean>(true);
            elaborationRule267 = addElaborationRule(condition267, _17_0_5_edc0357_1345510155557_623464_18238.this, Power._17_0_5_edc0357_1345510155557_623464_18238._17_0_5_edc0357_1345510189131_823332_18260.class, "_InitialNode_PowerCB", arguments267);
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

            public ElaborationRule elaborationRule268 = null;

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
                Expression<?>[] arguments268 = new Expression<?>[1];
                arguments268[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition268 = new Expression<Boolean>(_17_0_5_edc0357_1345510193521_82099_18271_exists);
                elaborationRule268 = addElaborationRule(condition268, _17_0_5_edc0357_1345510155557_623464_18238.this, Power._17_0_5_edc0357_1345510155557_623464_18238._17_0_5_edc0357_1345510193521_82099_18271.class, "_CallBehaviorAction_PowerCB", arguments268);
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

            public ConstraintExpression constraint269 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1348011599659_253029_14403_existsDependency = null;

            public ElaborationRule elaborationRule270 = null;

            public ElaborationRule elaborationRule271 = null;

            public void init_17_0_5_edc0357_1345510193521_82099_18271Members() {
                try {
                    _17_0_5_edc0357_1348011599659_253029_14403_exists = new BooleanParameter("_17_0_5_edc0357_1348011599659_253029_14403_exists", this);
                    _17_0_5_edc0357_1345510189131_823332_18260_endTime = new IntegerParameter("_17_0_5_edc0357_1345510189131_823332_18260_endTime", this);
                    cba_endTime = new IntegerParameter("cba_endTime", this);
                    constraint269 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510189131_823332_18260_endTime)));
                    _17_0_5_edc0357_1348011599659_253029_14403_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1348011599659_253029_14403_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510193521_82099_18271Collections() {
                parameters.add(_17_0_5_edc0357_1348011599659_253029_14403_exists);
                parameters.add(_17_0_5_edc0357_1345510189131_823332_18260_endTime);
                parameters.add(cba_endTime);
                constraintExpressions.add(constraint269);
                dependencies.add(_17_0_5_edc0357_1348011599659_253029_14403_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510193521_82099_18271Elaborations() {
                Expression<?>[] arguments270 = new Expression<?>[1];
                arguments270[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition270 = new Expression<Boolean>(_17_0_5_edc0357_1348011599659_253029_14403_exists);
                elaborationRule270 = addElaborationRule(condition270, _17_0_5_edc0357_1345510155557_623464_18238.this, Power._17_0_5_edc0357_1345510155557_623464_18238._17_0_5_edc0357_1348011599659_253029_14403.class, "_AcceptEventAction_PowerCB", arguments270);
                Expression<?>[] arguments271 = new Expression<?>[1];
                arguments271[0] = new Expression<Integer>(cba_endTime);
                Expression<Boolean> condition271 = new Expression<Boolean>(true);
                elaborationRule271 = addElaborationRule(condition271, Power.this, Power._17_0_5_edc0357_1345510113542_666806_13761.class, "intialize_Activity_Power", arguments271);
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

            public BooleanParameter _17_0_5_edc0357_1348162996982_701989_14540_exists = null;

            public BooleanParameter _17_0_5_edc0357_1345510646254_583840_18431_exists = null;

            public BooleanParameter _17_0_5_edc0357_1345510644119_150669_18425_exists = null;

            public BooleanParameter _17_0_5_edc0357_1345510642223_296237_18420_exists = null;

            public IntegerParameter _17_0_5_edc0357_1348011599659_253029_14403_endTime = null;

            public ConstraintExpression constraint272 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1348162996982_701989_14540_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510646254_583840_18431_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510644119_150669_18425_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510642223_296237_18420_existsDependency = null;

            public ElaborationRule elaborationRule273 = null;

            public ElaborationRule elaborationRule274 = null;

            public ElaborationRule elaborationRule275 = null;

            public ElaborationRule elaborationRule276 = null;

            public void init_17_0_5_edc0357_1345510201034_362860_18288Members() {
                try {
                    _17_0_5_edc0357_1348162996982_701989_14540_exists = new BooleanParameter("_17_0_5_edc0357_1348162996982_701989_14540_exists", this);
                    _17_0_5_edc0357_1345510646254_583840_18431_exists = new BooleanParameter("_17_0_5_edc0357_1345510646254_583840_18431_exists", this);
                    _17_0_5_edc0357_1345510644119_150669_18425_exists = new BooleanParameter("_17_0_5_edc0357_1345510644119_150669_18425_exists", this);
                    _17_0_5_edc0357_1345510642223_296237_18420_exists = new BooleanParameter("_17_0_5_edc0357_1345510642223_296237_18420_exists", this);
                    _17_0_5_edc0357_1348011599659_253029_14403_endTime = new IntegerParameter("_17_0_5_edc0357_1348011599659_253029_14403_endTime", this);
                    constraint272 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1348011599659_253029_14403_endTime)));
                    _17_0_5_edc0357_1348162996982_701989_14540_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1348162996982_701989_14540_exists, new Expression<Boolean>(true));
                    _17_0_5_edc0357_1345510646254_583840_18431_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510646254_583840_18431_exists, new Expression<Boolean>(true));
                    _17_0_5_edc0357_1345510644119_150669_18425_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510644119_150669_18425_exists, new Expression<Boolean>(true));
                    _17_0_5_edc0357_1345510642223_296237_18420_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510642223_296237_18420_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510201034_362860_18288Collections() {
                parameters.add(_17_0_5_edc0357_1348162996982_701989_14540_exists);
                parameters.add(_17_0_5_edc0357_1345510646254_583840_18431_exists);
                parameters.add(_17_0_5_edc0357_1345510644119_150669_18425_exists);
                parameters.add(_17_0_5_edc0357_1345510642223_296237_18420_exists);
                parameters.add(_17_0_5_edc0357_1348011599659_253029_14403_endTime);
                constraintExpressions.add(constraint272);
                dependencies.add(_17_0_5_edc0357_1348162996982_701989_14540_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510646254_583840_18431_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510644119_150669_18425_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510642223_296237_18420_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510201034_362860_18288Elaborations() {
                Expression<?>[] arguments273 = new Expression<?>[1];
                arguments273[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition273 = new Expression<Boolean>(_17_0_5_edc0357_1345510644119_150669_18425_exists);
                elaborationRule273 = addElaborationRule(condition273, _17_0_5_edc0357_1345510155557_623464_18238.this, Power._17_0_5_edc0357_1345510155557_623464_18238._17_0_5_edc0357_1345510644119_150669_18425.class, "_MergeNode_PowerCB", arguments273);
                Expression<?>[] arguments274 = new Expression<?>[1];
                arguments274[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition274 = new Expression<Boolean>(_17_0_5_edc0357_1348162996982_701989_14540_exists);
                elaborationRule274 = addElaborationRule(condition274, _17_0_5_edc0357_1345510155557_623464_18238.this, Power._17_0_5_edc0357_1345510155557_623464_18238._17_0_5_edc0357_1348162996982_701989_14540.class, "_AcceptEventAction_PowerCB", arguments274);
                Expression<?>[] arguments275 = new Expression<?>[1];
                arguments275[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition275 = new Expression<Boolean>(_17_0_5_edc0357_1345510646254_583840_18431_exists);
                elaborationRule275 = addElaborationRule(condition275, _17_0_5_edc0357_1345510155557_623464_18238.this, Power._17_0_5_edc0357_1345510155557_623464_18238._17_0_5_edc0357_1345510646254_583840_18431.class, "_MergeNode_PowerCB", arguments275);
                Expression<?>[] arguments276 = new Expression<?>[1];
                arguments276[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition276 = new Expression<Boolean>(_17_0_5_edc0357_1345510642223_296237_18420_exists);
                elaborationRule276 = addElaborationRule(condition276, _17_0_5_edc0357_1345510155557_623464_18238.this, Power._17_0_5_edc0357_1345510155557_623464_18238._17_0_5_edc0357_1345510642223_296237_18420.class, "_MergeNode_PowerCB", arguments276);
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

            public Parameter< Power_System.SignalchangeLoadValue > _17_0_5_edc0357_1345756714103_564856_12930 = null;

            public ConstraintExpression constraint277 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345763165599_575948_13389_existsDependency = null;

            public Dependency< Power_System.SignalchangeLoadValue > _17_0_5_edc0357_1345756714103_564856_12930Dependency = null;

            public ElaborationRule elaborationRule278 = null;

            public void init_17_0_5_edc0357_1345510469459_873081_18318Members() {
                try {
                    _17_0_5_edc0357_1345763165599_575948_13389_exists = new BooleanParameter("_17_0_5_edc0357_1345763165599_575948_13389_exists", this);
                    _17_0_5_edc0357_1345510644119_150669_18425_endTime = new IntegerParameter("_17_0_5_edc0357_1345510644119_150669_18425_endTime", this);
                    _17_0_5_edc0357_1345756714103_564856_12930 = new Parameter<Power_System.SignalchangeLoadValue>("_17_0_5_edc0357_1345756714103_564856_12930", null, null, this);
                    constraint277 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510644119_150669_18425_endTime)));
                    _17_0_5_edc0357_1345763165599_575948_13389_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345763165599_575948_13389_exists, new Expression<Boolean>(true));
                    _17_0_5_edc0357_1345756714103_564856_12930Dependency = new Dependency<Power_System.SignalchangeLoadValue>(_17_0_5_edc0357_1345756714103_564856_12930, new Expression(new FunctionCall(q_Power_changeLoadValue, Utils.getMethodForArgTypes("ObjectFlow<SignalchangeLoadValue>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510469459_873081_18318Collections() {
                parameters.add(_17_0_5_edc0357_1345763165599_575948_13389_exists);
                parameters.add(_17_0_5_edc0357_1345510644119_150669_18425_endTime);
                parameters.add(_17_0_5_edc0357_1345756714103_564856_12930);
                constraintExpressions.add(constraint277);
                dependencies.add(_17_0_5_edc0357_1345763165599_575948_13389_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345756714103_564856_12930Dependency);
            }

            public void init_17_0_5_edc0357_1345510469459_873081_18318Elaborations() {
                Expression<?>[] arguments278 = new Expression<?>[2];
                arguments278[0] = new Expression<Integer>(endTime);
                arguments278[1] = new Expression<Power_System.SignalchangeLoadValue>(_17_0_5_edc0357_1345756714103_564856_12930);
                Expression<Boolean> condition278 = new Expression<Boolean>(_17_0_5_edc0357_1345763165599_575948_13389_exists);
                elaborationRule278 = addElaborationRule(condition278, _17_0_5_edc0357_1345510155557_623464_18238.this, Power._17_0_5_edc0357_1345510155557_623464_18238._17_0_5_edc0357_1345763165599_575948_13389.class, "readLoadFromSignal_ReadStructuralFeatureAction_PowerCB", arguments278);
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

            public Parameter< Power_System.SignalchangeGenerationValue > _17_0_5_edc0357_1345756459554_558513_12886 = null;

            public BooleanParameter _17_0_5_edc0357_1345763300820_765750_13425_exists = null;

            public IntegerParameter _17_0_5_edc0357_1345510642223_296237_18420_endTime = null;

            public ConstraintExpression constraint279 = null;

            public Dependency< Power_System.SignalchangeGenerationValue > _17_0_5_edc0357_1345756459554_558513_12886Dependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345763300820_765750_13425_existsDependency = null;

            public ElaborationRule elaborationRule280 = null;

            public void init_17_0_5_edc0357_1345510471635_699632_18330Members() {
                try {
                    _17_0_5_edc0357_1345756459554_558513_12886 = new Parameter<Power_System.SignalchangeGenerationValue>("_17_0_5_edc0357_1345756459554_558513_12886", null, null, this);
                    _17_0_5_edc0357_1345763300820_765750_13425_exists = new BooleanParameter("_17_0_5_edc0357_1345763300820_765750_13425_exists", this);
                    _17_0_5_edc0357_1345510642223_296237_18420_endTime = new IntegerParameter("_17_0_5_edc0357_1345510642223_296237_18420_endTime", this);
                    constraint279 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510642223_296237_18420_endTime)));
                    _17_0_5_edc0357_1345756459554_558513_12886Dependency = new Dependency<Power_System.SignalchangeGenerationValue>(_17_0_5_edc0357_1345756459554_558513_12886, new Expression(new FunctionCall(q_Power_changeGenerationValue, Utils.getMethodForArgTypes("ObjectFlow<SignalchangeGenerationValue>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1345763300820_765750_13425_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345763300820_765750_13425_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510471635_699632_18330Collections() {
                parameters.add(_17_0_5_edc0357_1345756459554_558513_12886);
                parameters.add(_17_0_5_edc0357_1345763300820_765750_13425_exists);
                parameters.add(_17_0_5_edc0357_1345510642223_296237_18420_endTime);
                constraintExpressions.add(constraint279);
                dependencies.add(_17_0_5_edc0357_1345756459554_558513_12886Dependency);
                dependencies.add(_17_0_5_edc0357_1345763300820_765750_13425_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510471635_699632_18330Elaborations() {
                Expression<?>[] arguments280 = new Expression<?>[2];
                arguments280[0] = new Expression<Integer>(endTime);
                arguments280[1] = new Expression<Power_System.SignalchangeGenerationValue>(_17_0_5_edc0357_1345756459554_558513_12886);
                Expression<Boolean> condition280 = new Expression<Boolean>(_17_0_5_edc0357_1345763300820_765750_13425_exists);
                elaborationRule280 = addElaborationRule(condition280, _17_0_5_edc0357_1345510155557_623464_18238.this, Power._17_0_5_edc0357_1345510155557_623464_18238._17_0_5_edc0357_1345763300820_765750_13425.class, "readNewGenVal_ReadStructuralFeatureAction_PowerCB", arguments280);
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

            public ConstraintExpression constraint281 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510644119_150669_18425_existsDependency = null;

            public ElaborationRule elaborationRule282 = null;

            public ElaborationRule elaborationRule283 = null;

            public void init_17_0_5_edc0357_1345510492737_394609_18349Members() {
                try {
                    _17_0_5_edc0357_1345756720553_849612_12938 = new IntegerParameter("_17_0_5_edc0357_1345756720553_849612_12938", this);
                    _17_0_5_edc0357_1345510644119_150669_18425_exists = new BooleanParameter("_17_0_5_edc0357_1345510644119_150669_18425_exists", this);
                    _17_0_5_edc0357_1345763165599_575948_13389_endTime = new IntegerParameter("_17_0_5_edc0357_1345763165599_575948_13389_endTime", this);
                    cba_endTime = new IntegerParameter("cba_endTime", this);
                    constraint281 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345763165599_575948_13389_endTime)));
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
                constraintExpressions.add(constraint281);
                dependencies.add(_17_0_5_edc0357_1345510644119_150669_18425_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510492737_394609_18349Elaborations() {
                Expression<?>[] arguments282 = new Expression<?>[1];
                arguments282[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition282 = new Expression<Boolean>(_17_0_5_edc0357_1345510644119_150669_18425_exists);
                elaborationRule282 = addElaborationRule(condition282, _17_0_5_edc0357_1345510155557_623464_18238.this, Power._17_0_5_edc0357_1345510155557_623464_18238._17_0_5_edc0357_1345510644119_150669_18425.class, "_MergeNode_PowerCB", arguments282);
                Expression<?>[] arguments283 = new Expression<?>[2];
                arguments283[0] = new Expression<Integer>(cba_endTime);
                arguments283[1] = new Expression<Integer>(_17_0_5_edc0357_1345756720553_849612_12938);
                Expression<Boolean> condition283 = new Expression<Boolean>(true);
                elaborationRule283 = addElaborationRule(condition283, Power.this, Power._17_0_5_edc0357_1345510113541_858357_13760.class, "changeLoadValue_Activity_Power", arguments283);
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

            public ConstraintExpression constraint284 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510642223_296237_18420_existsDependency = null;

            public ElaborationRule elaborationRule285 = null;

            public ElaborationRule elaborationRule286 = null;

            public void init_17_0_5_edc0357_1345510501544_90462_18367Members() {
                try {
                    _17_0_5_edc0357_1345756501917_658961_12894 = new IntegerParameter("_17_0_5_edc0357_1345756501917_658961_12894", this);
                    _17_0_5_edc0357_1345763300820_765750_13425_endTime = new IntegerParameter("_17_0_5_edc0357_1345763300820_765750_13425_endTime", this);
                    _17_0_5_edc0357_1345510642223_296237_18420_exists = new BooleanParameter("_17_0_5_edc0357_1345510642223_296237_18420_exists", this);
                    cba_endTime = new IntegerParameter("cba_endTime", this);
                    constraint284 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345763300820_765750_13425_endTime)));
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
                constraintExpressions.add(constraint284);
                dependencies.add(_17_0_5_edc0357_1345510642223_296237_18420_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510501544_90462_18367Elaborations() {
                Expression<?>[] arguments285 = new Expression<?>[2];
                arguments285[0] = new Expression<Integer>(cba_endTime);
                arguments285[1] = new Expression<Integer>(_17_0_5_edc0357_1345756501917_658961_12894);
                Expression<Boolean> condition285 = new Expression<Boolean>(true);
                elaborationRule285 = addElaborationRule(condition285, Power.this, Power._17_0_5_edc0357_1345510113540_247161_13759.class, "changeGenerationValue_Activity_Power", arguments285);
                Expression<?>[] arguments286 = new Expression<?>[1];
                arguments286[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition286 = new Expression<Boolean>(_17_0_5_edc0357_1345510642223_296237_18420_exists);
                elaborationRule286 = addElaborationRule(condition286, _17_0_5_edc0357_1345510155557_623464_18238.this, Power._17_0_5_edc0357_1345510155557_623464_18238._17_0_5_edc0357_1345510642223_296237_18420.class, "_MergeNode_PowerCB", arguments286);
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

            public ConstraintExpression constraint287 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1348011630902_591447_14429_existsDependency = null;

            public ElaborationRule elaborationRule288 = null;

            public ElaborationRule elaborationRule289 = null;

            public void init_17_0_5_edc0357_1345510532104_703303_18387Members() {
                try {
                    _17_0_5_edc0357_1345510646254_583840_18431_endTime = new IntegerParameter("_17_0_5_edc0357_1345510646254_583840_18431_endTime", this);
                    cba_endTime = new IntegerParameter("cba_endTime", this);
                    _17_0_5_edc0357_1348011630902_591447_14429_exists = new BooleanParameter("_17_0_5_edc0357_1348011630902_591447_14429_exists", this);
                    constraint287 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510646254_583840_18431_endTime)));
                    _17_0_5_edc0357_1348011630902_591447_14429_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1348011630902_591447_14429_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510532104_703303_18387Collections() {
                parameters.add(_17_0_5_edc0357_1345510646254_583840_18431_endTime);
                parameters.add(cba_endTime);
                parameters.add(_17_0_5_edc0357_1348011630902_591447_14429_exists);
                constraintExpressions.add(constraint287);
                dependencies.add(_17_0_5_edc0357_1348011630902_591447_14429_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510532104_703303_18387Elaborations() {
                Expression<?>[] arguments288 = new Expression<?>[1];
                arguments288[0] = new Expression<Integer>(cba_endTime);
                Expression<Boolean> condition288 = new Expression<Boolean>(true);
                elaborationRule288 = addElaborationRule(condition288, Power.this, Power._17_0_5_edc0357_1345510113544_941994_13762.class, "generate_Activity_Power", arguments288);
                Expression<?>[] arguments289 = new Expression<?>[1];
                arguments289[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition289 = new Expression<Boolean>(_17_0_5_edc0357_1348011630902_591447_14429_exists);
                elaborationRule289 = addElaborationRule(condition289, _17_0_5_edc0357_1345510155557_623464_18238.this, Power._17_0_5_edc0357_1345510155557_623464_18238._17_0_5_edc0357_1348011630902_591447_14429.class, "_AcceptEventAction_PowerCB", arguments289);
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

            public ConstraintExpression constraint290 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510471635_699632_18330_existsDependency = null;

            public ElaborationRule elaborationRule291 = null;

            public void init_17_0_5_edc0357_1345510642223_296237_18420Members() {
                try {
                    invoker_endTime = new IntegerParameter("invoker_endTime", this);
                    _17_0_5_edc0357_1345510471635_699632_18330_exists = new BooleanParameter("_17_0_5_edc0357_1345510471635_699632_18330_exists", this);
                    constraint290 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(invoker_endTime)));
                    _17_0_5_edc0357_1345510471635_699632_18330_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510471635_699632_18330_exists, new Expression(new FunctionCall(q_Power_changeGenerationValue, Utils.getMethodForArgTypes("ObjectFlow<SignalchangeGenerationValue>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510642223_296237_18420Collections() {
                parameters.add(invoker_endTime);
                parameters.add(_17_0_5_edc0357_1345510471635_699632_18330_exists);
                constraintExpressions.add(constraint290);
                dependencies.add(_17_0_5_edc0357_1345510471635_699632_18330_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510642223_296237_18420Elaborations() {
                Expression<?>[] arguments291 = new Expression<?>[1];
                arguments291[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition291 = new Expression<Boolean>(_17_0_5_edc0357_1345510471635_699632_18330_exists);
                elaborationRule291 = addElaborationRule(condition291, _17_0_5_edc0357_1345510155557_623464_18238.this, Power._17_0_5_edc0357_1345510155557_623464_18238._17_0_5_edc0357_1345510471635_699632_18330.class, "cvg_sig_AcceptEventAction_PowerCB", arguments291);
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

            public ConstraintExpression constraint292 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510469459_873081_18318_existsDependency = null;

            public ElaborationRule elaborationRule293 = null;

            public void init_17_0_5_edc0357_1345510644119_150669_18425Members() {
                try {
                    invoker_endTime = new IntegerParameter("invoker_endTime", this);
                    _17_0_5_edc0357_1345510469459_873081_18318_exists = new BooleanParameter("_17_0_5_edc0357_1345510469459_873081_18318_exists", this);
                    constraint292 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(invoker_endTime)));
                    _17_0_5_edc0357_1345510469459_873081_18318_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510469459_873081_18318_exists, new Expression(new FunctionCall(q_Power_changeLoadValue, Utils.getMethodForArgTypes("ObjectFlow<SignalchangeLoadValue>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510644119_150669_18425Collections() {
                parameters.add(invoker_endTime);
                parameters.add(_17_0_5_edc0357_1345510469459_873081_18318_exists);
                constraintExpressions.add(constraint292);
                dependencies.add(_17_0_5_edc0357_1345510469459_873081_18318_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510644119_150669_18425Elaborations() {
                Expression<?>[] arguments293 = new Expression<?>[1];
                arguments293[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition293 = new Expression<Boolean>(_17_0_5_edc0357_1345510469459_873081_18318_exists);
                elaborationRule293 = addElaborationRule(condition293, _17_0_5_edc0357_1345510155557_623464_18238.this, Power._17_0_5_edc0357_1345510155557_623464_18238._17_0_5_edc0357_1345510469459_873081_18318.class, "clv_sig_AcceptEventAction_PowerCB", arguments293);
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

            public ConstraintExpression constraint294 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510532104_703303_18387_existsDependency = null;

            public ElaborationRule elaborationRule295 = null;

            public void init_17_0_5_edc0357_1345510646254_583840_18431Members() {
                try {
                    invoker_endTime = new IntegerParameter("invoker_endTime", this);
                    _17_0_5_edc0357_1345510532104_703303_18387_exists = new BooleanParameter("_17_0_5_edc0357_1345510532104_703303_18387_exists", this);
                    constraint294 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(invoker_endTime)));
                    _17_0_5_edc0357_1345510532104_703303_18387_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510532104_703303_18387_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510646254_583840_18431Collections() {
                parameters.add(invoker_endTime);
                parameters.add(_17_0_5_edc0357_1345510532104_703303_18387_exists);
                constraintExpressions.add(constraint294);
                dependencies.add(_17_0_5_edc0357_1345510532104_703303_18387_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510646254_583840_18431Elaborations() {
                Expression<?>[] arguments295 = new Expression<?>[1];
                arguments295[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition295 = new Expression<Boolean>(_17_0_5_edc0357_1345510532104_703303_18387_exists);
                elaborationRule295 = addElaborationRule(condition295, _17_0_5_edc0357_1345510155557_623464_18238.this, Power._17_0_5_edc0357_1345510155557_623464_18238._17_0_5_edc0357_1345510532104_703303_18387.class, "_CallBehaviorAction_PowerCB", arguments295);
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

        public class _17_0_5_edc0357_1345510765243_88318_18538 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1348162996982_701989_14540_endTime = null;

            public ConstraintExpression constraint296 = null;

            public void init_17_0_5_edc0357_1345510765243_88318_18538Members() {
                try {
                    _17_0_5_edc0357_1348162996982_701989_14540_endTime = new IntegerParameter("_17_0_5_edc0357_1348162996982_701989_14540_endTime", this);
                    constraint296 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1348162996982_701989_14540_endTime)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510765243_88318_18538Collections() {
                parameters.add(_17_0_5_edc0357_1348162996982_701989_14540_endTime);
                constraintExpressions.add(constraint296);
            }

            public void init_17_0_5_edc0357_1345510765243_88318_18538Elaborations() {
            }

            public _17_0_5_edc0357_1345510765243_88318_18538() {
                super();
                init_17_0_5_edc0357_1345510765243_88318_18538Members();
                init_17_0_5_edc0357_1345510765243_88318_18538Collections();
                init_17_0_5_edc0357_1345510765243_88318_18538Elaborations();
            }

            public _17_0_5_edc0357_1345510765243_88318_18538(Expression<Integer> _17_0_5_edc0357_1348162996982_701989_14540_endTime) {
                super();
                init_17_0_5_edc0357_1345510765243_88318_18538Members();
                init_17_0_5_edc0357_1345510765243_88318_18538Collections();
                addDependency(this._17_0_5_edc0357_1348162996982_701989_14540_endTime, _17_0_5_edc0357_1348162996982_701989_14540_endTime);
                init_17_0_5_edc0357_1345510765243_88318_18538Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345763165599_575948_13389 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1345763208746_548851_13403 = null;

            public BooleanParameter _17_0_5_edc0357_1345510492737_394609_18349_exists = null;

            public Parameter< Power_System.SignalchangeLoadValue > _17_0_5_edc0357_1345763176758_698915_13402 = null;

            public IntegerParameter _17_0_5_edc0357_1345510469459_873081_18318_endTime = null;

            public ConstraintExpression constraint297 = null;

            public Dependency< Integer > _17_0_5_edc0357_1345763208746_548851_13403Dependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510492737_394609_18349_existsDependency = null;

            public ElaborationRule elaborationRule298 = null;

            public void init_17_0_5_edc0357_1345763165599_575948_13389Members() {
                try {
                    _17_0_5_edc0357_1345763208746_548851_13403 = new IntegerParameter("_17_0_5_edc0357_1345763208746_548851_13403", this);
                    _17_0_5_edc0357_1345510492737_394609_18349_exists = new BooleanParameter("_17_0_5_edc0357_1345510492737_394609_18349_exists", this);
                    _17_0_5_edc0357_1345763176758_698915_13402 = new Parameter<Power_System.SignalchangeLoadValue>("_17_0_5_edc0357_1345763176758_698915_13402", null, null, this);
                    _17_0_5_edc0357_1345510469459_873081_18318_endTime = new IntegerParameter("_17_0_5_edc0357_1345510469459_873081_18318_endTime", this);
                    constraint297 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510469459_873081_18318_endTime)));
                    _17_0_5_edc0357_1345763208746_548851_13403Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345763208746_548851_13403, new Expression(new FunctionCall(null, Utils.getMethodForArgTypes("Power._17_0_5_edc0357_1345510155557_623464_18238._17_0_5_edc0357_1345763165599_575948_13389", "generated", "getValue"), new Object[] { new Expression<Integer>(startTime) }, new FunctionCall(null, Parameter.class, "getValue", (Object[]) null, new FunctionCall(_17_0_5_edc0357_1345763176758_698915_13402, Parameter.class, "getMember", new Object[] { "load__17_0_5_edc0357_1345510113551_910480_13770" })))));
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
                constraintExpressions.add(constraint297);
                dependencies.add(_17_0_5_edc0357_1345763208746_548851_13403Dependency);
                dependencies.add(_17_0_5_edc0357_1345510492737_394609_18349_existsDependency);
            }

            public void init_17_0_5_edc0357_1345763165599_575948_13389Elaborations() {
                Expression<?>[] arguments298 = new Expression<?>[2];
                arguments298[0] = new Expression<Integer>(endTime);
                arguments298[1] = new Expression<Integer>(_17_0_5_edc0357_1345763208746_548851_13403);
                Expression<Boolean> condition298 = new Expression<Boolean>(_17_0_5_edc0357_1345510492737_394609_18349_exists);
                elaborationRule298 = addElaborationRule(condition298, _17_0_5_edc0357_1345510155557_623464_18238.this, Power._17_0_5_edc0357_1345510155557_623464_18238._17_0_5_edc0357_1345510492737_394609_18349.class, "clv_CallBehaviorAction_PowerCB", arguments298);
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

            public Parameter< Power_System.SignalchangeGenerationValue > _17_0_5_edc0357_1345763308884_192100_13438 = null;

            public ConstraintExpression constraint299 = null;

            public Dependency< Integer > _17_0_5_edc0357_1345763333670_26957_13439Dependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510501544_90462_18367_existsDependency = null;

            public ElaborationRule elaborationRule300 = null;

            public void init_17_0_5_edc0357_1345763300820_765750_13425Members() {
                try {
                    _17_0_5_edc0357_1345763333670_26957_13439 = new IntegerParameter("_17_0_5_edc0357_1345763333670_26957_13439", this);
                    _17_0_5_edc0357_1345510471635_699632_18330_endTime = new IntegerParameter("_17_0_5_edc0357_1345510471635_699632_18330_endTime", this);
                    _17_0_5_edc0357_1345510501544_90462_18367_exists = new BooleanParameter("_17_0_5_edc0357_1345510501544_90462_18367_exists", this);
                    _17_0_5_edc0357_1345763308884_192100_13438 = new Parameter<Power_System.SignalchangeGenerationValue>("_17_0_5_edc0357_1345763308884_192100_13438", null, null, this);
                    constraint299 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510471635_699632_18330_endTime)));
                    _17_0_5_edc0357_1345763333670_26957_13439Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345763333670_26957_13439, new Expression(new FunctionCall(null, Utils.getMethodForArgTypes("Power._17_0_5_edc0357_1345510155557_623464_18238._17_0_5_edc0357_1345763300820_765750_13425", "generated", "getValue"), new Object[] { new Expression<Integer>(startTime) }, new FunctionCall(null, Parameter.class, "getValue", (Object[]) null, new FunctionCall(_17_0_5_edc0357_1345763308884_192100_13438, Parameter.class, "getMember", new Object[] { "newGenValue__17_0_5_edc0357_1345510113551_931345_13769" })))));
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
                constraintExpressions.add(constraint299);
                dependencies.add(_17_0_5_edc0357_1345763333670_26957_13439Dependency);
                dependencies.add(_17_0_5_edc0357_1345510501544_90462_18367_existsDependency);
            }

            public void init_17_0_5_edc0357_1345763300820_765750_13425Elaborations() {
                Expression<?>[] arguments300 = new Expression<?>[2];
                arguments300[0] = new Expression<Integer>(endTime);
                arguments300[1] = new Expression<Integer>(_17_0_5_edc0357_1345763333670_26957_13439);
                Expression<Boolean> condition300 = new Expression<Boolean>(_17_0_5_edc0357_1345510501544_90462_18367_exists);
                elaborationRule300 = addElaborationRule(condition300, _17_0_5_edc0357_1345510155557_623464_18238.this, Power._17_0_5_edc0357_1345510155557_623464_18238._17_0_5_edc0357_1345510501544_90462_18367.class, "cgv_CallBehaviorAction_PowerCB", arguments300);
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

            public ConstraintExpression constraint301 = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510201034_362860_18288_existsDependency = null;

            public ElaborationRule elaborationRule302 = null;

            public void init_17_0_5_edc0357_1348011599659_253029_14403Members() {
                try {
                    _17_0_5_edc0357_1345510193521_82099_18271_endTime = new IntegerParameter("_17_0_5_edc0357_1345510193521_82099_18271_endTime", this);
                    _17_0_5_edc0357_1345510201034_362860_18288_exists = new BooleanParameter("_17_0_5_edc0357_1345510201034_362860_18288_exists", this);
                    constraint301 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510193521_82099_18271_endTime)));
                    durationDependency = new Dependency<Integer>(duration, new Expression<Integer>(20));
                    _17_0_5_edc0357_1345510201034_362860_18288_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510201034_362860_18288_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1348011599659_253029_14403Collections() {
                parameters.add(_17_0_5_edc0357_1345510193521_82099_18271_endTime);
                parameters.add(_17_0_5_edc0357_1345510201034_362860_18288_exists);
                constraintExpressions.add(constraint301);
                removeDependency(duration);
                dependencies.add(durationDependency);
                dependencies.add(_17_0_5_edc0357_1345510201034_362860_18288_existsDependency);
            }

            public void init_17_0_5_edc0357_1348011599659_253029_14403Elaborations() {
                Expression<?>[] arguments302 = new Expression<?>[1];
                arguments302[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition302 = new Expression<Boolean>(_17_0_5_edc0357_1345510201034_362860_18288_exists);
                elaborationRule302 = addElaborationRule(condition302, _17_0_5_edc0357_1345510155557_623464_18238.this, Power._17_0_5_edc0357_1345510155557_623464_18238._17_0_5_edc0357_1345510201034_362860_18288.class, "_ForkNode_PowerCB", arguments302);
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

            public ConstraintExpression constraint303 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510646254_583840_18431_existsDependency = null;

            public Dependency< Integer > durationDependency = null;

            public ElaborationRule elaborationRule304 = null;

            public void init_17_0_5_edc0357_1348011630902_591447_14429Members() {
                try {
                    _17_0_5_edc0357_1345510646254_583840_18431_exists = new BooleanParameter("_17_0_5_edc0357_1345510646254_583840_18431_exists", this);
                    _17_0_5_edc0357_1345510532104_703303_18387_endTime = new IntegerParameter("_17_0_5_edc0357_1345510532104_703303_18387_endTime", this);
                    constraint303 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510532104_703303_18387_endTime)));
                    _17_0_5_edc0357_1345510646254_583840_18431_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510646254_583840_18431_exists, new Expression<Boolean>(true));
                    durationDependency = new Dependency<Integer>(duration, new Expression<Integer>(15));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1348011630902_591447_14429Collections() {
                parameters.add(_17_0_5_edc0357_1345510646254_583840_18431_exists);
                parameters.add(_17_0_5_edc0357_1345510532104_703303_18387_endTime);
                constraintExpressions.add(constraint303);
                removeDependency(duration);
                dependencies.add(_17_0_5_edc0357_1345510646254_583840_18431_existsDependency);
                dependencies.add(durationDependency);
            }

            public void init_17_0_5_edc0357_1348011630902_591447_14429Elaborations() {
                Expression<?>[] arguments304 = new Expression<?>[1];
                arguments304[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition304 = new Expression<Boolean>(_17_0_5_edc0357_1345510646254_583840_18431_exists);
                elaborationRule304 = addElaborationRule(condition304, _17_0_5_edc0357_1345510155557_623464_18238.this, Power._17_0_5_edc0357_1345510155557_623464_18238._17_0_5_edc0357_1345510646254_583840_18431.class, "_MergeNode_PowerCB", arguments304);
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

        public class _17_0_5_edc0357_1348162996982_701989_14540 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1345510201034_362860_18288_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1345510765243_88318_18538_exists = null;

            public ConstraintExpression constraint305 = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510765243_88318_18538_existsDependency = null;

            public ElaborationRule elaborationRule306 = null;

            public void init_17_0_5_edc0357_1348162996982_701989_14540Members() {
                try {
                    _17_0_5_edc0357_1345510201034_362860_18288_endTime = new IntegerParameter("_17_0_5_edc0357_1345510201034_362860_18288_endTime", this);
                    _17_0_5_edc0357_1345510765243_88318_18538_exists = new BooleanParameter("_17_0_5_edc0357_1345510765243_88318_18538_exists", this);
                    constraint305 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510201034_362860_18288_endTime)));
                    durationDependency = new Dependency<Integer>(duration, new Expression<Integer>(10000));
                    _17_0_5_edc0357_1345510765243_88318_18538_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510765243_88318_18538_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1348162996982_701989_14540Collections() {
                parameters.add(_17_0_5_edc0357_1345510201034_362860_18288_endTime);
                parameters.add(_17_0_5_edc0357_1345510765243_88318_18538_exists);
                constraintExpressions.add(constraint305);
                removeDependency(duration);
                dependencies.add(durationDependency);
                dependencies.add(_17_0_5_edc0357_1345510765243_88318_18538_existsDependency);
            }

            public void init_17_0_5_edc0357_1348162996982_701989_14540Elaborations() {
                Expression<?>[] arguments306 = new Expression<?>[1];
                arguments306[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition306 = new Expression<Boolean>(_17_0_5_edc0357_1345510765243_88318_18538_exists);
                elaborationRule306 = addElaborationRule(condition306, _17_0_5_edc0357_1345510155557_623464_18238.this, Power._17_0_5_edc0357_1345510155557_623464_18238._17_0_5_edc0357_1345510765243_88318_18538.class, "_ActivityFinalNode_PowerCB", arguments306);
            }

            public _17_0_5_edc0357_1348162996982_701989_14540() {
                super();
                init_17_0_5_edc0357_1348162996982_701989_14540Members();
                init_17_0_5_edc0357_1348162996982_701989_14540Collections();
                init_17_0_5_edc0357_1348162996982_701989_14540Elaborations();
            }

            public _17_0_5_edc0357_1348162996982_701989_14540(Expression<Integer> _17_0_5_edc0357_1345510201034_362860_18288_endTime) {
                super();
                init_17_0_5_edc0357_1348162996982_701989_14540Members();
                init_17_0_5_edc0357_1348162996982_701989_14540Collections();
                addDependency(this._17_0_5_edc0357_1345510201034_362860_18288_endTime, _17_0_5_edc0357_1345510201034_362860_18288_endTime);
                init_17_0_5_edc0357_1348162996982_701989_14540Elaborations();
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
        super();
        this.x = new Parameter<Power_System>("Power_System", null, x, null);
        initPowerMembers();
        initPowerCollections();
        initPowerElaborations();
    }
}
