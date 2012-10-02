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

public class LADWP extends ParameterListenerImpl {

    public Parameter< TimeVaryingMap<Boolean> > demandResponse__17_0_5_edc0357_1345510113615_857599_13851 = null;

    public Parameter< ObjectFlow<Power_System.Signaldr_request> > q_LADWP_dr_request = null;

    public Parameter< TimeVaryingMap<Boolean> > shortage__17_0_5_edc0357_1345510113616_127059_13852 = null;

    public Parameter< TimeVaryingMap<Integer> > reported_generation__17_0_5_edc0357_1345510113612_851613_13846 = null;

    public Parameter< ObjectFlow<Power_System.SignalreceiveLoadReading> > q_LADWP_receiveLoadReading = null;

    public StringParameter classifierBehavior = null;

    public Parameter< TimeVaryingMap<Integer> > reported_load__17_0_5_edc0357_1345510113610_466337_13843 = null;

    public Parameter< ObjectFlow<Power_System.SignalchangeGenerationValue> > q_LADWP_changeGenerationValue = null;

    public Parameter< ObjectFlow<Power_System.SignalreceiveGenReading> > q_LADWP_receiveGenReading = null;

    public Parameter< TimeVaryingMap<Integer> > expected_margin__17_0_5_edc0357_1345510113614_771928_13849 = null;

    public Parameter< TimeVaryingMap<Integer> > predicted_load__17_0_5_edc0357_1345510113613_464654_13847 = null;

    public Parameter< TimeVaryingMap<Integer> > actual_load__17_0_5_edc0357_1345510113609_489064_13842 = null;

    public Parameter< TimeVaryingMap<Integer> > current_margin__17_0_5_edc0357_1345510113615_810190_13850 = null;

    public Parameter< TimeVaryingMap<Integer> > power_needed__17_0_5_edc0357_1345510113610_569612_13844 = null;

    public Parameter< ObjectFlow<Power_System.SignalreceiveMeterReading> > q_LADWP_receiveMeterReading = null;

    public Parameter< ObjectFlow<Power_System.Signalyes> > q_LADWP_yes = null;

    public Parameter< Power_System > x = null;

    public Parameter< TimeVaryingMap<Integer> > expected_load__17_0_5_edc0357_1345510113611_416109_13845 = null;

    public Parameter< ObjectFlow<Power_System.Signalno> > q_LADWP_no = null;

    public void initLADWPMembers() {
        try {
            demandResponse__17_0_5_edc0357_1345510113615_857599_13851 = new Parameter<TimeVaryingMap<Boolean>>("demandResponse__17_0_5_edc0357_1345510113615_857599_13851", null, new TimeVaryingMap("demandResponse"), this);
            q_LADWP_dr_request = new Parameter<ObjectFlow<Power_System.Signaldr_request>>("q_LADWP_dr_request", null, new ObjectFlow("q_LADWP_dr_request", Power_System.Signaldr_request.class), this);
            shortage__17_0_5_edc0357_1345510113616_127059_13852 = new Parameter<TimeVaryingMap<Boolean>>("shortage__17_0_5_edc0357_1345510113616_127059_13852", null, new TimeVaryingMap("shortage"), this);
            reported_generation__17_0_5_edc0357_1345510113612_851613_13846 = new Parameter<TimeVaryingMap<Integer>>("reported_generation__17_0_5_edc0357_1345510113612_851613_13846", null, new TimeVaryingMap("reported_generation"), this);
            q_LADWP_receiveLoadReading = new Parameter<ObjectFlow<Power_System.SignalreceiveLoadReading>>("q_LADWP_receiveLoadReading", null, new ObjectFlow("q_LADWP_receiveLoadReading", Power_System.SignalreceiveLoadReading.class), this);
            classifierBehavior = new StringParameter("classifierBehavior", this);
            reported_load__17_0_5_edc0357_1345510113610_466337_13843 = new Parameter<TimeVaryingMap<Integer>>("reported_load__17_0_5_edc0357_1345510113610_466337_13843", null, new TimeVaryingMap("reported_load"), this);
            q_LADWP_changeGenerationValue = new Parameter<ObjectFlow<Power_System.SignalchangeGenerationValue>>("q_LADWP_changeGenerationValue", null, new ObjectFlow("q_LADWP_changeGenerationValue", Power_System.SignalchangeGenerationValue.class), this);
            q_LADWP_receiveGenReading = new Parameter<ObjectFlow<Power_System.SignalreceiveGenReading>>("q_LADWP_receiveGenReading", null, new ObjectFlow("q_LADWP_receiveGenReading", Power_System.SignalreceiveGenReading.class), this);
            expected_margin__17_0_5_edc0357_1345510113614_771928_13849 = new Parameter<TimeVaryingMap<Integer>>("expected_margin__17_0_5_edc0357_1345510113614_771928_13849", null, new TimeVaryingMap("expected_margin"), this);
            predicted_load__17_0_5_edc0357_1345510113613_464654_13847 = new Parameter<TimeVaryingMap<Integer>>("predicted_load__17_0_5_edc0357_1345510113613_464654_13847", null, new TimeVaryingMap("predicted_load"), this);
            actual_load__17_0_5_edc0357_1345510113609_489064_13842 = new Parameter<TimeVaryingMap<Integer>>("actual_load__17_0_5_edc0357_1345510113609_489064_13842", null, new TimeVaryingMap("actual_load"), this);
            current_margin__17_0_5_edc0357_1345510113615_810190_13850 = new Parameter<TimeVaryingMap<Integer>>("current_margin__17_0_5_edc0357_1345510113615_810190_13850", null, new TimeVaryingMap("current_margin"), this);
            power_needed__17_0_5_edc0357_1345510113610_569612_13844 = new Parameter<TimeVaryingMap<Integer>>("power_needed__17_0_5_edc0357_1345510113610_569612_13844", null, new TimeVaryingMap("power_needed"), this);
            q_LADWP_receiveMeterReading = new Parameter<ObjectFlow<Power_System.SignalreceiveMeterReading>>("q_LADWP_receiveMeterReading", null, new ObjectFlow("q_LADWP_receiveMeterReading", Power_System.SignalreceiveMeterReading.class), this);
            q_LADWP_yes = new Parameter<ObjectFlow<Power_System.Signalyes>>("q_LADWP_yes", null, new ObjectFlow("q_LADWP_yes", Power_System.Signalyes.class), this);
            x = new Parameter<Power_System>("x", null, null, this);
            expected_load__17_0_5_edc0357_1345510113611_416109_13845 = new Parameter<TimeVaryingMap<Integer>>("expected_load__17_0_5_edc0357_1345510113611_416109_13845", null, new TimeVaryingMap("expected_load"), this);
            q_LADWP_no = new Parameter<ObjectFlow<Power_System.Signalno>>("q_LADWP_no", null, new ObjectFlow("q_LADWP_no", Power_System.Signalno.class), this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void initLADWPCollections() {
        parameters.add(demandResponse__17_0_5_edc0357_1345510113615_857599_13851);
        parameters.add(q_LADWP_dr_request);
        parameters.add(shortage__17_0_5_edc0357_1345510113616_127059_13852);
        parameters.add(reported_generation__17_0_5_edc0357_1345510113612_851613_13846);
        parameters.add(q_LADWP_receiveLoadReading);
        parameters.add(classifierBehavior);
        parameters.add(reported_load__17_0_5_edc0357_1345510113610_466337_13843);
        parameters.add(q_LADWP_changeGenerationValue);
        parameters.add(q_LADWP_receiveGenReading);
        parameters.add(expected_margin__17_0_5_edc0357_1345510113614_771928_13849);
        parameters.add(predicted_load__17_0_5_edc0357_1345510113613_464654_13847);
        parameters.add(actual_load__17_0_5_edc0357_1345510113609_489064_13842);
        parameters.add(current_margin__17_0_5_edc0357_1345510113615_810190_13850);
        parameters.add(power_needed__17_0_5_edc0357_1345510113610_569612_13844);
        parameters.add(q_LADWP_receiveMeterReading);
        parameters.add(q_LADWP_yes);
        parameters.add(x);
        parameters.add(expected_load__17_0_5_edc0357_1345510113611_416109_13845);
        parameters.add(q_LADWP_no);
    }

    public void initLADWPElaborations() {
    }

    public LADWP() {
        super();
        initLADWPMembers();
        initLADWPCollections();
        initLADWPElaborations();
    }

    public class _17_0_5_edc0357_1345510113596_603128_13828 extends DurativeEvent {

        public Parameter< ObjectFlow<Integer> > sig_17_0_5_edc0357_1346100955782_830873_13796 = null;

        public IntegerParameter _17_0_5_edc0357_1346100922767_721473_13763 = null;

        public IntegerParameter invoke_time = null;

        public ElaborationRule elaborationRule307 = null;

        public ElaborationRule elaborationRule308 = null;

        public void init_17_0_5_edc0357_1345510113596_603128_13828Members() {
            try {
                sig_17_0_5_edc0357_1346100955782_830873_13796 = new Parameter<ObjectFlow<Integer>>("sig_17_0_5_edc0357_1346100955782_830873_13796", null, new ObjectFlow("sig_17_0_5_edc0357_1346100955782_830873_13796"), this);
                _17_0_5_edc0357_1346100922767_721473_13763 = new IntegerParameter("_17_0_5_edc0357_1346100922767_721473_13763", this);
                invoke_time = new IntegerParameter("invoke_time", this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void init_17_0_5_edc0357_1345510113596_603128_13828Collections() {
            parameters.add(sig_17_0_5_edc0357_1346100955782_830873_13796);
            parameters.add(_17_0_5_edc0357_1346100922767_721473_13763);
            parameters.add(invoke_time);
        }

        public void init_17_0_5_edc0357_1345510113596_603128_13828Elaborations() {
            Expression<?>[] arguments307 = new Expression<?>[1];
            arguments307[0] = new Expression<Integer>(invoke_time);
            Expression<Boolean> condition307 = new Expression<Boolean>(true);
            elaborationRule307 = addElaborationRule(condition307, _17_0_5_edc0357_1345510113596_603128_13828.this, LADWP._17_0_5_edc0357_1345510113596_603128_13828._17_0_5_edc0357_1345510114327_368286_14527.class, "_InitialNode_updateReportedLoad", arguments307);
            Expression<?>[] arguments308 = new Expression<?>[2];
            arguments308[0] = new Expression<Integer>(invoke_time);
            arguments308[1] = new Expression<Integer>(_17_0_5_edc0357_1346100922767_721473_13763);
            Expression<Boolean> condition308 = new Expression<Boolean>(true);
            elaborationRule308 = addElaborationRule(condition308, _17_0_5_edc0357_1345510113596_603128_13828.this, LADWP._17_0_5_edc0357_1345510113596_603128_13828._17_0_5_edc0357_1346100928357_373213_13765.class, "reportedLoad_ActivityParameterNode_updateReportedLoad", arguments308);
        }

        public _17_0_5_edc0357_1345510113596_603128_13828() {
            super();
            init_17_0_5_edc0357_1345510113596_603128_13828Members();
            init_17_0_5_edc0357_1345510113596_603128_13828Collections();
            init_17_0_5_edc0357_1345510113596_603128_13828Elaborations();
        }

        public class _17_0_5_edc0357_1345510114327_207025_14524 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1345510114327_368286_14527_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1345510114327_355904_14525_exists = null;

            public Parameter< LADWP > _17_0_5_edc0357_1345510114869_550535_15247 = null;

            public ConstraintExpression constraint309 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114327_355904_14525_existsDependency = null;

            public ElaborationRule elaborationRule310 = null;

            public void init_17_0_5_edc0357_1345510114327_207025_14524Members() {
                try {
                    _17_0_5_edc0357_1345510114327_368286_14527_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114327_368286_14527_endTime", this);
                    _17_0_5_edc0357_1345510114327_355904_14525_exists = new BooleanParameter("_17_0_5_edc0357_1345510114327_355904_14525_exists", this);
                    _17_0_5_edc0357_1345510114869_550535_15247 = new Parameter<LADWP>("_17_0_5_edc0357_1345510114869_550535_15247", null, LADWP.this, this);
                    constraint309 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114327_368286_14527_endTime)));
                    _17_0_5_edc0357_1345510114327_355904_14525_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114327_355904_14525_exists, new Expression(new FunctionCall(sig_17_0_5_edc0357_1346100955782_830873_13796, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114327_207025_14524Collections() {
                parameters.add(_17_0_5_edc0357_1345510114327_368286_14527_endTime);
                parameters.add(_17_0_5_edc0357_1345510114327_355904_14525_exists);
                parameters.add(_17_0_5_edc0357_1345510114869_550535_15247);
                constraintExpressions.add(constraint309);
                dependencies.add(_17_0_5_edc0357_1345510114327_355904_14525_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510114327_207025_14524Elaborations() {
                Expression<?>[] arguments310 = new Expression<?>[2];
                arguments310[0] = new Expression<Integer>(endTime);
                arguments310[1] = new Expression<LADWP>(_17_0_5_edc0357_1345510114869_550535_15247);
                Expression<Boolean> condition310 = new Expression<Boolean>(_17_0_5_edc0357_1345510114327_355904_14525_exists);
                elaborationRule310 = addElaborationRule(condition310, _17_0_5_edc0357_1345510113596_603128_13828.this, LADWP._17_0_5_edc0357_1345510113596_603128_13828._17_0_5_edc0357_1345510114327_355904_14525.class, "_AddStructuralFeatureValueAction_updateReportedLoad", arguments310);
            }

            public _17_0_5_edc0357_1345510114327_207025_14524() {
                super();
                init_17_0_5_edc0357_1345510114327_207025_14524Members();
                init_17_0_5_edc0357_1345510114327_207025_14524Collections();
                init_17_0_5_edc0357_1345510114327_207025_14524Elaborations();
            }

            public _17_0_5_edc0357_1345510114327_207025_14524(Expression<Integer> _17_0_5_edc0357_1345510114327_368286_14527_endTime) {
                super();
                init_17_0_5_edc0357_1345510114327_207025_14524Members();
                init_17_0_5_edc0357_1345510114327_207025_14524Collections();
                addDependency(this._17_0_5_edc0357_1345510114327_368286_14527_endTime, _17_0_5_edc0357_1345510114327_368286_14527_endTime);
                init_17_0_5_edc0357_1345510114327_207025_14524Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114327_355904_14525 extends DurativeEvent {

            public Parameter< LADWP > _17_0_5_edc0357_1345510114872_767304_15249 = null;

            public IntegerParameter _17_0_5_edc0357_1345510114327_207025_14524_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1345510114328_982693_14528_exists = null;

            public IntegerParameter _17_0_5_edc0357_1345510114870_148039_15248 = null;

            public ConstraintExpression constraint311 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114328_982693_14528_existsDependency = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114870_148039_15248Dependency = null;

            public Effect effect312 = null;

            public Parameter effect312Var = null;

            public ElaborationRule elaborationRule313 = null;

            public void init_17_0_5_edc0357_1345510114327_355904_14525Members() {
                try {
                    _17_0_5_edc0357_1345510114872_767304_15249 = new Parameter<LADWP>("_17_0_5_edc0357_1345510114872_767304_15249", null, null, this);
                    _17_0_5_edc0357_1345510114327_207025_14524_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114327_207025_14524_endTime", this);
                    _17_0_5_edc0357_1345510114328_982693_14528_exists = new BooleanParameter("_17_0_5_edc0357_1345510114328_982693_14528_exists", this);
                    _17_0_5_edc0357_1345510114870_148039_15248 = new IntegerParameter("_17_0_5_edc0357_1345510114870_148039_15248", this);
                    constraint311 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114327_207025_14524_endTime)));
                    _17_0_5_edc0357_1345510114328_982693_14528_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114328_982693_14528_exists, new Expression<Boolean>(true));
                    _17_0_5_edc0357_1345510114870_148039_15248Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114870_148039_15248, new Expression(new FunctionCall(sig_17_0_5_edc0357_1346100955782_830873_13796, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    Object effect312VarV = reported_load__17_0_5_edc0357_1345510113610_466337_13843;
                    effect312Var = new Parameter("effect312Var", null, null, this);
                    addDependency(effect312Var, new Expression(effect312VarV));
                    effect312 = new EffectFunction(new FunctionCall(effect312Var, Utils.getMethodForArgTypes("TimeVaryingMap<Integer>", "generated", "setValue", java.lang.Integer.class, java.lang.Object.class), new Object[] { startTime, _17_0_5_edc0357_1345510114870_148039_15248 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114327_355904_14525Collections() {
                parameters.add(_17_0_5_edc0357_1345510114872_767304_15249);
                parameters.add(_17_0_5_edc0357_1345510114327_207025_14524_endTime);
                parameters.add(_17_0_5_edc0357_1345510114328_982693_14528_exists);
                parameters.add(_17_0_5_edc0357_1345510114870_148039_15248);
                constraintExpressions.add(constraint311);
                dependencies.add(_17_0_5_edc0357_1345510114328_982693_14528_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510114870_148039_15248Dependency);
                Set<Effect> effectsForeffect312Var = new HashSet<Effect>();
                effectsForeffect312Var.add(effect312);
                effects.put((Parameter<?>) effect312Var, effectsForeffect312Var);
            }

            public void init_17_0_5_edc0357_1345510114327_355904_14525Elaborations() {
                Expression<?>[] arguments313 = new Expression<?>[1];
                arguments313[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition313 = new Expression<Boolean>(_17_0_5_edc0357_1345510114328_982693_14528_exists);
                elaborationRule313 = addElaborationRule(condition313, _17_0_5_edc0357_1345510113596_603128_13828.this, LADWP._17_0_5_edc0357_1345510113596_603128_13828._17_0_5_edc0357_1345510114328_982693_14528.class, "_ActivityFinalNode_updateReportedLoad", arguments313);
            }

            public _17_0_5_edc0357_1345510114327_355904_14525() {
                super();
                init_17_0_5_edc0357_1345510114327_355904_14525Members();
                init_17_0_5_edc0357_1345510114327_355904_14525Collections();
                init_17_0_5_edc0357_1345510114327_355904_14525Elaborations();
            }

            public _17_0_5_edc0357_1345510114327_355904_14525(Expression<Integer> _17_0_5_edc0357_1345510114327_207025_14524_endTime, Expression<LADWP> _17_0_5_edc0357_1345510114872_767304_15249) {
                super();
                init_17_0_5_edc0357_1345510114327_355904_14525Members();
                init_17_0_5_edc0357_1345510114327_355904_14525Collections();
                addDependency(this._17_0_5_edc0357_1345510114327_207025_14524_endTime, _17_0_5_edc0357_1345510114327_207025_14524_endTime);
                addDependency(this._17_0_5_edc0357_1345510114872_767304_15249, _17_0_5_edc0357_1345510114872_767304_15249);
                init_17_0_5_edc0357_1345510114327_355904_14525Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114327_368286_14527 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1345510114327_207025_14524_exists = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114327_207025_14524_existsDependency = null;

            public ElaborationRule elaborationRule314 = null;

            public void init_17_0_5_edc0357_1345510114327_368286_14527Members() {
                try {
                    _17_0_5_edc0357_1345510114327_207025_14524_exists = new BooleanParameter("_17_0_5_edc0357_1345510114327_207025_14524_exists", this);
                    _17_0_5_edc0357_1345510114327_207025_14524_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114327_207025_14524_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114327_368286_14527Collections() {
                parameters.add(_17_0_5_edc0357_1345510114327_207025_14524_exists);
                dependencies.add(_17_0_5_edc0357_1345510114327_207025_14524_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510114327_368286_14527Elaborations() {
                Expression<?>[] arguments314 = new Expression<?>[1];
                arguments314[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition314 = new Expression<Boolean>(_17_0_5_edc0357_1345510114327_207025_14524_exists);
                elaborationRule314 = addElaborationRule(condition314, _17_0_5_edc0357_1345510113596_603128_13828.this, LADWP._17_0_5_edc0357_1345510113596_603128_13828._17_0_5_edc0357_1345510114327_207025_14524.class, "_ReadSelfAction_updateReportedLoad", arguments314);
            }

            public _17_0_5_edc0357_1345510114327_368286_14527() {
                super();
                init_17_0_5_edc0357_1345510114327_368286_14527Members();
                init_17_0_5_edc0357_1345510114327_368286_14527Collections();
                init_17_0_5_edc0357_1345510114327_368286_14527Elaborations();
            }

            public _17_0_5_edc0357_1345510114327_368286_14527(Expression<Integer> startTime) {
                super();
                init_17_0_5_edc0357_1345510114327_368286_14527Members();
                init_17_0_5_edc0357_1345510114327_368286_14527Collections();
                removeDependency(this.startTime);
                addDependency(this.startTime, startTime);
                init_17_0_5_edc0357_1345510114327_368286_14527Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114328_982693_14528 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1345510114327_355904_14525_endTime = null;

            public ConstraintExpression constraint315 = null;

            public void init_17_0_5_edc0357_1345510114328_982693_14528Members() {
                try {
                    _17_0_5_edc0357_1345510114327_355904_14525_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114327_355904_14525_endTime", this);
                    constraint315 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114327_355904_14525_endTime)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114328_982693_14528Collections() {
                parameters.add(_17_0_5_edc0357_1345510114327_355904_14525_endTime);
                constraintExpressions.add(constraint315);
            }

            public void init_17_0_5_edc0357_1345510114328_982693_14528Elaborations() {
            }

            public _17_0_5_edc0357_1345510114328_982693_14528() {
                super();
                init_17_0_5_edc0357_1345510114328_982693_14528Members();
                init_17_0_5_edc0357_1345510114328_982693_14528Collections();
                init_17_0_5_edc0357_1345510114328_982693_14528Elaborations();
            }

            public _17_0_5_edc0357_1345510114328_982693_14528(Expression<Integer> _17_0_5_edc0357_1345510114327_355904_14525_endTime) {
                super();
                init_17_0_5_edc0357_1345510114328_982693_14528Members();
                init_17_0_5_edc0357_1345510114328_982693_14528Collections();
                addDependency(this._17_0_5_edc0357_1345510114327_355904_14525_endTime, _17_0_5_edc0357_1345510114327_355904_14525_endTime);
                init_17_0_5_edc0357_1345510114328_982693_14528Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1346100928357_373213_13765 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1346100922767_721473_13763 = null;

            public Effect effect316 = null;

            public Parameter effect316Var = null;

            public void init_17_0_5_edc0357_1346100928357_373213_13765Members() {
                try {
                    _17_0_5_edc0357_1346100922767_721473_13763 = new IntegerParameter("_17_0_5_edc0357_1346100922767_721473_13763", this);
                    Object effect316VarV = sig_17_0_5_edc0357_1346100955782_830873_13796;
                    effect316Var = new Parameter("effect316Var", null, null, this);
                    addDependency(effect316Var, new Expression(effect316VarV));
                    effect316 = new EffectFunction(new FunctionCall(effect316Var, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { _17_0_5_edc0357_1346100922767_721473_13763, startTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346100928357_373213_13765Collections() {
                parameters.add(_17_0_5_edc0357_1346100922767_721473_13763);
                Set<Effect> effectsForeffect316Var = new HashSet<Effect>();
                effectsForeffect316Var.add(effect316);
                effects.put((Parameter<?>) effect316Var, effectsForeffect316Var);
            }

            public void init_17_0_5_edc0357_1346100928357_373213_13765Elaborations() {
            }

            public _17_0_5_edc0357_1346100928357_373213_13765() {
                super();
                init_17_0_5_edc0357_1346100928357_373213_13765Members();
                init_17_0_5_edc0357_1346100928357_373213_13765Collections();
                init_17_0_5_edc0357_1346100928357_373213_13765Elaborations();
            }

            public _17_0_5_edc0357_1346100928357_373213_13765(Expression<Integer> startTime, Expression<Integer> _17_0_5_edc0357_1346100922767_721473_13763) {
                super();
                init_17_0_5_edc0357_1346100928357_373213_13765Members();
                init_17_0_5_edc0357_1346100928357_373213_13765Collections();
                removeDependency(this.startTime);
                addDependency(this.startTime, startTime);
                addDependency(this._17_0_5_edc0357_1346100922767_721473_13763, _17_0_5_edc0357_1346100922767_721473_13763);
                init_17_0_5_edc0357_1346100928357_373213_13765Elaborations();
                fixTimeDependencies();
            }
        }

        public _17_0_5_edc0357_1345510113596_603128_13828(Expression<Integer> endTime, Expression<Integer> _17_0_5_edc0357_1346100922767_721473_13763) {
            super();
            init_17_0_5_edc0357_1345510113596_603128_13828Members();
            init_17_0_5_edc0357_1345510113596_603128_13828Collections();
            removeDependency(this.endTime);
            addDependency(this.endTime, endTime);
            addDependency(this._17_0_5_edc0357_1346100922767_721473_13763, _17_0_5_edc0357_1346100922767_721473_13763);
            init_17_0_5_edc0357_1345510113596_603128_13828Elaborations();
            fixTimeDependencies();
        }
    }

    public class _17_0_5_edc0357_1345510113597_14205_13829 extends DurativeEvent {

        public Parameter< ObjectFlow<Integer> > sig_17_0_5_edc0357_1346101200222_265463_13908 = null;

        public IntegerParameter invoke_time = null;

        public IntegerParameter _17_0_5_edc0357_1346101090161_713198_13875 = null;

        public ElaborationRule elaborationRule317 = null;

        public ElaborationRule elaborationRule318 = null;

        public void init_17_0_5_edc0357_1345510113597_14205_13829Members() {
            try {
                sig_17_0_5_edc0357_1346101200222_265463_13908 = new Parameter<ObjectFlow<Integer>>("sig_17_0_5_edc0357_1346101200222_265463_13908", null, new ObjectFlow("sig_17_0_5_edc0357_1346101200222_265463_13908"), this);
                invoke_time = new IntegerParameter("invoke_time", this);
                _17_0_5_edc0357_1346101090161_713198_13875 = new IntegerParameter("_17_0_5_edc0357_1346101090161_713198_13875", this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void init_17_0_5_edc0357_1345510113597_14205_13829Collections() {
            parameters.add(sig_17_0_5_edc0357_1346101200222_265463_13908);
            parameters.add(invoke_time);
            parameters.add(_17_0_5_edc0357_1346101090161_713198_13875);
        }

        public void init_17_0_5_edc0357_1345510113597_14205_13829Elaborations() {
            Expression<?>[] arguments317 = new Expression<?>[2];
            arguments317[0] = new Expression<Integer>(invoke_time);
            arguments317[1] = new Expression<Integer>(_17_0_5_edc0357_1346101090161_713198_13875);
            Expression<Boolean> condition317 = new Expression<Boolean>(true);
            elaborationRule317 = addElaborationRule(condition317, _17_0_5_edc0357_1345510113597_14205_13829.this, LADWP._17_0_5_edc0357_1345510113597_14205_13829._17_0_5_edc0357_1346101105922_421213_13877.class, "actualLoad_ActivityParameterNode_updateActualLoad", arguments317);
            Expression<?>[] arguments318 = new Expression<?>[1];
            arguments318[0] = new Expression<Integer>(invoke_time);
            Expression<Boolean> condition318 = new Expression<Boolean>(true);
            elaborationRule318 = addElaborationRule(condition318, _17_0_5_edc0357_1345510113597_14205_13829.this, LADWP._17_0_5_edc0357_1345510113597_14205_13829._17_0_5_edc0357_1345510114344_59836_14558.class, "initial_InitialNode_updateActualLoad", arguments318);
        }

        public _17_0_5_edc0357_1345510113597_14205_13829() {
            super();
            init_17_0_5_edc0357_1345510113597_14205_13829Members();
            init_17_0_5_edc0357_1345510113597_14205_13829Collections();
            init_17_0_5_edc0357_1345510113597_14205_13829Elaborations();
        }

        public class _17_0_5_edc0357_1345510114343_392589_14555 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1345510114344_59836_14558_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1345510114343_928274_14556_exists = null;

            public Parameter< LADWP > _17_0_5_edc0357_1345510114875_564527_15256 = null;

            public ConstraintExpression constraint319 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114343_928274_14556_existsDependency = null;

            public ElaborationRule elaborationRule320 = null;

            public void init_17_0_5_edc0357_1345510114343_392589_14555Members() {
                try {
                    _17_0_5_edc0357_1345510114344_59836_14558_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114344_59836_14558_endTime", this);
                    _17_0_5_edc0357_1345510114343_928274_14556_exists = new BooleanParameter("_17_0_5_edc0357_1345510114343_928274_14556_exists", this);
                    _17_0_5_edc0357_1345510114875_564527_15256 = new Parameter<LADWP>("_17_0_5_edc0357_1345510114875_564527_15256", null, LADWP.this, this);
                    constraint319 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114344_59836_14558_endTime)));
                    _17_0_5_edc0357_1345510114343_928274_14556_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114343_928274_14556_exists, new Expression(new FunctionCall(sig_17_0_5_edc0357_1346101200222_265463_13908, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114343_392589_14555Collections() {
                parameters.add(_17_0_5_edc0357_1345510114344_59836_14558_endTime);
                parameters.add(_17_0_5_edc0357_1345510114343_928274_14556_exists);
                parameters.add(_17_0_5_edc0357_1345510114875_564527_15256);
                constraintExpressions.add(constraint319);
                dependencies.add(_17_0_5_edc0357_1345510114343_928274_14556_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510114343_392589_14555Elaborations() {
                Expression<?>[] arguments320 = new Expression<?>[2];
                arguments320[0] = new Expression<Integer>(endTime);
                arguments320[1] = new Expression<LADWP>(_17_0_5_edc0357_1345510114875_564527_15256);
                Expression<Boolean> condition320 = new Expression<Boolean>(_17_0_5_edc0357_1345510114343_928274_14556_exists);
                elaborationRule320 = addElaborationRule(condition320, _17_0_5_edc0357_1345510113597_14205_13829.this, LADWP._17_0_5_edc0357_1345510113597_14205_13829._17_0_5_edc0357_1345510114343_928274_14556.class, "readActLoad_AddStructuralFeatureValueAction_updateActualLoad", arguments320);
            }

            public _17_0_5_edc0357_1345510114343_392589_14555() {
                super();
                init_17_0_5_edc0357_1345510114343_392589_14555Members();
                init_17_0_5_edc0357_1345510114343_392589_14555Collections();
                init_17_0_5_edc0357_1345510114343_392589_14555Elaborations();
            }

            public _17_0_5_edc0357_1345510114343_392589_14555(Expression<Integer> _17_0_5_edc0357_1345510114344_59836_14558_endTime) {
                super();
                init_17_0_5_edc0357_1345510114343_392589_14555Members();
                init_17_0_5_edc0357_1345510114343_392589_14555Collections();
                addDependency(this._17_0_5_edc0357_1345510114344_59836_14558_endTime, _17_0_5_edc0357_1345510114344_59836_14558_endTime);
                init_17_0_5_edc0357_1345510114343_392589_14555Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114343_928274_14556 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1345510114344_743484_14559_exists = null;

            public IntegerParameter _17_0_5_edc0357_1345510114876_2649_15257 = null;

            public IntegerParameter _17_0_5_edc0357_1345510114343_392589_14555_endTime = null;

            public Parameter< LADWP > _17_0_5_edc0357_1345510114877_268291_15258 = null;

            public ConstraintExpression constraint321 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114344_743484_14559_existsDependency = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114876_2649_15257Dependency = null;

            public Effect effect322 = null;

            public Parameter effect322Var = null;

            public ElaborationRule elaborationRule323 = null;

            public void init_17_0_5_edc0357_1345510114343_928274_14556Members() {
                try {
                    _17_0_5_edc0357_1345510114344_743484_14559_exists = new BooleanParameter("_17_0_5_edc0357_1345510114344_743484_14559_exists", this);
                    _17_0_5_edc0357_1345510114876_2649_15257 = new IntegerParameter("_17_0_5_edc0357_1345510114876_2649_15257", this);
                    _17_0_5_edc0357_1345510114343_392589_14555_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114343_392589_14555_endTime", this);
                    _17_0_5_edc0357_1345510114877_268291_15258 = new Parameter<LADWP>("_17_0_5_edc0357_1345510114877_268291_15258", null, null, this);
                    constraint321 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114343_392589_14555_endTime)));
                    _17_0_5_edc0357_1345510114344_743484_14559_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114344_743484_14559_exists, new Expression<Boolean>(true));
                    _17_0_5_edc0357_1345510114876_2649_15257Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114876_2649_15257, new Expression(new FunctionCall(sig_17_0_5_edc0357_1346101200222_265463_13908, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    Object effect322VarV = actual_load__17_0_5_edc0357_1345510113609_489064_13842;
                    effect322Var = new Parameter("effect322Var", null, null, this);
                    addDependency(effect322Var, new Expression(effect322VarV));
                    effect322 = new EffectFunction(new FunctionCall(effect322Var, Utils.getMethodForArgTypes("TimeVaryingMap<Integer>", "generated", "setValue", java.lang.Integer.class, java.lang.Object.class), new Object[] { startTime, _17_0_5_edc0357_1345510114876_2649_15257 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114343_928274_14556Collections() {
                parameters.add(_17_0_5_edc0357_1345510114344_743484_14559_exists);
                parameters.add(_17_0_5_edc0357_1345510114876_2649_15257);
                parameters.add(_17_0_5_edc0357_1345510114343_392589_14555_endTime);
                parameters.add(_17_0_5_edc0357_1345510114877_268291_15258);
                constraintExpressions.add(constraint321);
                dependencies.add(_17_0_5_edc0357_1345510114344_743484_14559_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510114876_2649_15257Dependency);
                Set<Effect> effectsForeffect322Var = new HashSet<Effect>();
                effectsForeffect322Var.add(effect322);
                effects.put((Parameter<?>) effect322Var, effectsForeffect322Var);
            }

            public void init_17_0_5_edc0357_1345510114343_928274_14556Elaborations() {
                Expression<?>[] arguments323 = new Expression<?>[1];
                arguments323[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition323 = new Expression<Boolean>(_17_0_5_edc0357_1345510114344_743484_14559_exists);
                elaborationRule323 = addElaborationRule(condition323, _17_0_5_edc0357_1345510113597_14205_13829.this, LADWP._17_0_5_edc0357_1345510113597_14205_13829._17_0_5_edc0357_1345510114344_743484_14559.class, "final_ActivityFinalNode_updateActualLoad", arguments323);
            }

            public _17_0_5_edc0357_1345510114343_928274_14556() {
                super();
                init_17_0_5_edc0357_1345510114343_928274_14556Members();
                init_17_0_5_edc0357_1345510114343_928274_14556Collections();
                init_17_0_5_edc0357_1345510114343_928274_14556Elaborations();
            }

            public _17_0_5_edc0357_1345510114343_928274_14556(Expression<Integer> _17_0_5_edc0357_1345510114343_392589_14555_endTime, Expression<LADWP> _17_0_5_edc0357_1345510114877_268291_15258) {
                super();
                init_17_0_5_edc0357_1345510114343_928274_14556Members();
                init_17_0_5_edc0357_1345510114343_928274_14556Collections();
                addDependency(this._17_0_5_edc0357_1345510114343_392589_14555_endTime, _17_0_5_edc0357_1345510114343_392589_14555_endTime);
                addDependency(this._17_0_5_edc0357_1345510114877_268291_15258, _17_0_5_edc0357_1345510114877_268291_15258);
                init_17_0_5_edc0357_1345510114343_928274_14556Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114344_59836_14558 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1345510114343_392589_14555_exists = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114343_392589_14555_existsDependency = null;

            public ElaborationRule elaborationRule324 = null;

            public void init_17_0_5_edc0357_1345510114344_59836_14558Members() {
                try {
                    _17_0_5_edc0357_1345510114343_392589_14555_exists = new BooleanParameter("_17_0_5_edc0357_1345510114343_392589_14555_exists", this);
                    _17_0_5_edc0357_1345510114343_392589_14555_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114343_392589_14555_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114344_59836_14558Collections() {
                parameters.add(_17_0_5_edc0357_1345510114343_392589_14555_exists);
                dependencies.add(_17_0_5_edc0357_1345510114343_392589_14555_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510114344_59836_14558Elaborations() {
                Expression<?>[] arguments324 = new Expression<?>[1];
                arguments324[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition324 = new Expression<Boolean>(_17_0_5_edc0357_1345510114343_392589_14555_exists);
                elaborationRule324 = addElaborationRule(condition324, _17_0_5_edc0357_1345510113597_14205_13829.this, LADWP._17_0_5_edc0357_1345510113597_14205_13829._17_0_5_edc0357_1345510114343_392589_14555.class, "rs_ReadSelfAction_updateActualLoad", arguments324);
            }

            public _17_0_5_edc0357_1345510114344_59836_14558() {
                super();
                init_17_0_5_edc0357_1345510114344_59836_14558Members();
                init_17_0_5_edc0357_1345510114344_59836_14558Collections();
                init_17_0_5_edc0357_1345510114344_59836_14558Elaborations();
            }

            public _17_0_5_edc0357_1345510114344_59836_14558(Expression<Integer> startTime) {
                super();
                init_17_0_5_edc0357_1345510114344_59836_14558Members();
                init_17_0_5_edc0357_1345510114344_59836_14558Collections();
                removeDependency(this.startTime);
                addDependency(this.startTime, startTime);
                init_17_0_5_edc0357_1345510114344_59836_14558Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114344_743484_14559 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1345510114343_928274_14556_endTime = null;

            public ConstraintExpression constraint325 = null;

            public void init_17_0_5_edc0357_1345510114344_743484_14559Members() {
                try {
                    _17_0_5_edc0357_1345510114343_928274_14556_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114343_928274_14556_endTime", this);
                    constraint325 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114343_928274_14556_endTime)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114344_743484_14559Collections() {
                parameters.add(_17_0_5_edc0357_1345510114343_928274_14556_endTime);
                constraintExpressions.add(constraint325);
            }

            public void init_17_0_5_edc0357_1345510114344_743484_14559Elaborations() {
            }

            public _17_0_5_edc0357_1345510114344_743484_14559() {
                super();
                init_17_0_5_edc0357_1345510114344_743484_14559Members();
                init_17_0_5_edc0357_1345510114344_743484_14559Collections();
                init_17_0_5_edc0357_1345510114344_743484_14559Elaborations();
            }

            public _17_0_5_edc0357_1345510114344_743484_14559(Expression<Integer> _17_0_5_edc0357_1345510114343_928274_14556_endTime) {
                super();
                init_17_0_5_edc0357_1345510114344_743484_14559Members();
                init_17_0_5_edc0357_1345510114344_743484_14559Collections();
                addDependency(this._17_0_5_edc0357_1345510114343_928274_14556_endTime, _17_0_5_edc0357_1345510114343_928274_14556_endTime);
                init_17_0_5_edc0357_1345510114344_743484_14559Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1346101105922_421213_13877 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1346101090161_713198_13875 = null;

            public Effect effect326 = null;

            public Parameter effect326Var = null;

            public void init_17_0_5_edc0357_1346101105922_421213_13877Members() {
                try {
                    _17_0_5_edc0357_1346101090161_713198_13875 = new IntegerParameter("_17_0_5_edc0357_1346101090161_713198_13875", this);
                    Object effect326VarV = sig_17_0_5_edc0357_1346101200222_265463_13908;
                    effect326Var = new Parameter("effect326Var", null, null, this);
                    addDependency(effect326Var, new Expression(effect326VarV));
                    effect326 = new EffectFunction(new FunctionCall(effect326Var, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { _17_0_5_edc0357_1346101090161_713198_13875, startTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346101105922_421213_13877Collections() {
                parameters.add(_17_0_5_edc0357_1346101090161_713198_13875);
                Set<Effect> effectsForeffect326Var = new HashSet<Effect>();
                effectsForeffect326Var.add(effect326);
                effects.put((Parameter<?>) effect326Var, effectsForeffect326Var);
            }

            public void init_17_0_5_edc0357_1346101105922_421213_13877Elaborations() {
            }

            public _17_0_5_edc0357_1346101105922_421213_13877() {
                super();
                init_17_0_5_edc0357_1346101105922_421213_13877Members();
                init_17_0_5_edc0357_1346101105922_421213_13877Collections();
                init_17_0_5_edc0357_1346101105922_421213_13877Elaborations();
            }

            public _17_0_5_edc0357_1346101105922_421213_13877(Expression<Integer> startTime, Expression<Integer> _17_0_5_edc0357_1346101090161_713198_13875) {
                super();
                init_17_0_5_edc0357_1346101105922_421213_13877Members();
                init_17_0_5_edc0357_1346101105922_421213_13877Collections();
                removeDependency(this.startTime);
                addDependency(this.startTime, startTime);
                addDependency(this._17_0_5_edc0357_1346101090161_713198_13875, _17_0_5_edc0357_1346101090161_713198_13875);
                init_17_0_5_edc0357_1346101105922_421213_13877Elaborations();
                fixTimeDependencies();
            }
        }

        public _17_0_5_edc0357_1345510113597_14205_13829(Expression<Integer> endTime, Expression<Integer> _17_0_5_edc0357_1346101090161_713198_13875) {
            super();
            init_17_0_5_edc0357_1345510113597_14205_13829Members();
            init_17_0_5_edc0357_1345510113597_14205_13829Collections();
            removeDependency(this.endTime);
            addDependency(this.endTime, endTime);
            addDependency(this._17_0_5_edc0357_1346101090161_713198_13875, _17_0_5_edc0357_1346101090161_713198_13875);
            init_17_0_5_edc0357_1345510113597_14205_13829Elaborations();
            fixTimeDependencies();
        }
    }

    public class _17_0_5_edc0357_1345510113598_693480_13830 extends DurativeEvent {

        public Parameter< ObjectFlow<Integer> > sig_17_0_5_edc0357_1346101473960_270064_14063 = null;

        public IntegerParameter invoke_time = null;

        public IntegerParameter _17_0_5_edc0357_1346101437766_368293_14030 = null;

        public ElaborationRule elaborationRule327 = null;

        public ElaborationRule elaborationRule328 = null;

        public void init_17_0_5_edc0357_1345510113598_693480_13830Members() {
            try {
                sig_17_0_5_edc0357_1346101473960_270064_14063 = new Parameter<ObjectFlow<Integer>>("sig_17_0_5_edc0357_1346101473960_270064_14063", null, new ObjectFlow("sig_17_0_5_edc0357_1346101473960_270064_14063"), this);
                invoke_time = new IntegerParameter("invoke_time", this);
                _17_0_5_edc0357_1346101437766_368293_14030 = new IntegerParameter("_17_0_5_edc0357_1346101437766_368293_14030", this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void init_17_0_5_edc0357_1345510113598_693480_13830Collections() {
            parameters.add(sig_17_0_5_edc0357_1346101473960_270064_14063);
            parameters.add(invoke_time);
            parameters.add(_17_0_5_edc0357_1346101437766_368293_14030);
        }

        public void init_17_0_5_edc0357_1345510113598_693480_13830Elaborations() {
            Expression<?>[] arguments327 = new Expression<?>[1];
            arguments327[0] = new Expression<Integer>(invoke_time);
            Expression<Boolean> condition327 = new Expression<Boolean>(true);
            elaborationRule327 = addElaborationRule(condition327, _17_0_5_edc0357_1345510113598_693480_13830.this, LADWP._17_0_5_edc0357_1345510113598_693480_13830._17_0_5_edc0357_1345510114381_139867_14599.class, "_InitialNode_updateGeneration", arguments327);
            Expression<?>[] arguments328 = new Expression<?>[2];
            arguments328[0] = new Expression<Integer>(invoke_time);
            arguments328[1] = new Expression<Integer>(_17_0_5_edc0357_1346101437766_368293_14030);
            Expression<Boolean> condition328 = new Expression<Boolean>(true);
            elaborationRule328 = addElaborationRule(condition328, _17_0_5_edc0357_1345510113598_693480_13830.this, LADWP._17_0_5_edc0357_1345510113598_693480_13830._17_0_5_edc0357_1346101453400_976869_14032.class, "actualGeneration_ActivityParameterNode_updateGeneration", arguments328);
        }

        public _17_0_5_edc0357_1345510113598_693480_13830() {
            super();
            init_17_0_5_edc0357_1345510113598_693480_13830Members();
            init_17_0_5_edc0357_1345510113598_693480_13830Collections();
            init_17_0_5_edc0357_1345510113598_693480_13830Elaborations();
        }

        public class _17_0_5_edc0357_1345510114380_725893_14596 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1345510114380_134530_14597_exists = null;

            public Parameter< LADWP > _17_0_5_edc0357_1345510114881_48570_15265 = null;

            public IntegerParameter _17_0_5_edc0357_1345510114381_139867_14599_endTime = null;

            public ConstraintExpression constraint329 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114380_134530_14597_existsDependency = null;

            public ElaborationRule elaborationRule330 = null;

            public void init_17_0_5_edc0357_1345510114380_725893_14596Members() {
                try {
                    _17_0_5_edc0357_1345510114380_134530_14597_exists = new BooleanParameter("_17_0_5_edc0357_1345510114380_134530_14597_exists", this);
                    _17_0_5_edc0357_1345510114881_48570_15265 = new Parameter<LADWP>("_17_0_5_edc0357_1345510114881_48570_15265", null, LADWP.this, this);
                    _17_0_5_edc0357_1345510114381_139867_14599_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114381_139867_14599_endTime", this);
                    constraint329 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114381_139867_14599_endTime)));
                    _17_0_5_edc0357_1345510114380_134530_14597_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114380_134530_14597_exists, new Expression(new FunctionCall(sig_17_0_5_edc0357_1346101473960_270064_14063, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114380_725893_14596Collections() {
                parameters.add(_17_0_5_edc0357_1345510114380_134530_14597_exists);
                parameters.add(_17_0_5_edc0357_1345510114881_48570_15265);
                parameters.add(_17_0_5_edc0357_1345510114381_139867_14599_endTime);
                constraintExpressions.add(constraint329);
                dependencies.add(_17_0_5_edc0357_1345510114380_134530_14597_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510114380_725893_14596Elaborations() {
                Expression<?>[] arguments330 = new Expression<?>[2];
                arguments330[0] = new Expression<Integer>(endTime);
                arguments330[1] = new Expression<LADWP>(_17_0_5_edc0357_1345510114881_48570_15265);
                Expression<Boolean> condition330 = new Expression<Boolean>(_17_0_5_edc0357_1345510114380_134530_14597_exists);
                elaborationRule330 = addElaborationRule(condition330, _17_0_5_edc0357_1345510113598_693480_13830.this, LADWP._17_0_5_edc0357_1345510113598_693480_13830._17_0_5_edc0357_1345510114380_134530_14597.class, "_AddStructuralFeatureValueAction_updateGeneration", arguments330);
            }

            public _17_0_5_edc0357_1345510114380_725893_14596() {
                super();
                init_17_0_5_edc0357_1345510114380_725893_14596Members();
                init_17_0_5_edc0357_1345510114380_725893_14596Collections();
                init_17_0_5_edc0357_1345510114380_725893_14596Elaborations();
            }

            public _17_0_5_edc0357_1345510114380_725893_14596(Expression<Integer> _17_0_5_edc0357_1345510114381_139867_14599_endTime) {
                super();
                init_17_0_5_edc0357_1345510114380_725893_14596Members();
                init_17_0_5_edc0357_1345510114380_725893_14596Collections();
                addDependency(this._17_0_5_edc0357_1345510114381_139867_14599_endTime, _17_0_5_edc0357_1345510114381_139867_14599_endTime);
                init_17_0_5_edc0357_1345510114380_725893_14596Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114380_134530_14597 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1345510114881_103661_15266 = null;

            public IntegerParameter _17_0_5_edc0357_1345510114380_725893_14596_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1345510114381_563515_14600_exists = null;

            public Parameter< LADWP > _17_0_5_edc0357_1345510114882_909563_15267 = null;

            public ConstraintExpression constraint331 = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114881_103661_15266Dependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114381_563515_14600_existsDependency = null;

            public Effect effect332 = null;

            public Parameter effect332Var = null;

            public ElaborationRule elaborationRule333 = null;

            public void init_17_0_5_edc0357_1345510114380_134530_14597Members() {
                try {
                    _17_0_5_edc0357_1345510114881_103661_15266 = new IntegerParameter("_17_0_5_edc0357_1345510114881_103661_15266", this);
                    _17_0_5_edc0357_1345510114380_725893_14596_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114380_725893_14596_endTime", this);
                    _17_0_5_edc0357_1345510114381_563515_14600_exists = new BooleanParameter("_17_0_5_edc0357_1345510114381_563515_14600_exists", this);
                    _17_0_5_edc0357_1345510114882_909563_15267 = new Parameter<LADWP>("_17_0_5_edc0357_1345510114882_909563_15267", null, null, this);
                    constraint331 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114380_725893_14596_endTime)));
                    _17_0_5_edc0357_1345510114881_103661_15266Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114881_103661_15266, new Expression(new FunctionCall(sig_17_0_5_edc0357_1346101473960_270064_14063, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1345510114381_563515_14600_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114381_563515_14600_exists, new Expression<Boolean>(true));
                    Object effect332VarV = reported_generation__17_0_5_edc0357_1345510113612_851613_13846;
                    effect332Var = new Parameter("effect332Var", null, null, this);
                    addDependency(effect332Var, new Expression(effect332VarV));
                    effect332 = new EffectFunction(new FunctionCall(effect332Var, Utils.getMethodForArgTypes("TimeVaryingMap<Integer>", "generated", "setValue", java.lang.Integer.class, java.lang.Object.class), new Object[] { startTime, _17_0_5_edc0357_1345510114881_103661_15266 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114380_134530_14597Collections() {
                parameters.add(_17_0_5_edc0357_1345510114881_103661_15266);
                parameters.add(_17_0_5_edc0357_1345510114380_725893_14596_endTime);
                parameters.add(_17_0_5_edc0357_1345510114381_563515_14600_exists);
                parameters.add(_17_0_5_edc0357_1345510114882_909563_15267);
                constraintExpressions.add(constraint331);
                dependencies.add(_17_0_5_edc0357_1345510114881_103661_15266Dependency);
                dependencies.add(_17_0_5_edc0357_1345510114381_563515_14600_existsDependency);
                Set<Effect> effectsForeffect332Var = new HashSet<Effect>();
                effectsForeffect332Var.add(effect332);
                effects.put((Parameter<?>) effect332Var, effectsForeffect332Var);
            }

            public void init_17_0_5_edc0357_1345510114380_134530_14597Elaborations() {
                Expression<?>[] arguments333 = new Expression<?>[1];
                arguments333[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition333 = new Expression<Boolean>(_17_0_5_edc0357_1345510114381_563515_14600_exists);
                elaborationRule333 = addElaborationRule(condition333, _17_0_5_edc0357_1345510113598_693480_13830.this, LADWP._17_0_5_edc0357_1345510113598_693480_13830._17_0_5_edc0357_1345510114381_563515_14600.class, "_ActivityFinalNode_updateGeneration", arguments333);
            }

            public _17_0_5_edc0357_1345510114380_134530_14597() {
                super();
                init_17_0_5_edc0357_1345510114380_134530_14597Members();
                init_17_0_5_edc0357_1345510114380_134530_14597Collections();
                init_17_0_5_edc0357_1345510114380_134530_14597Elaborations();
            }

            public _17_0_5_edc0357_1345510114380_134530_14597(Expression<Integer> _17_0_5_edc0357_1345510114380_725893_14596_endTime, Expression<LADWP> _17_0_5_edc0357_1345510114882_909563_15267) {
                super();
                init_17_0_5_edc0357_1345510114380_134530_14597Members();
                init_17_0_5_edc0357_1345510114380_134530_14597Collections();
                addDependency(this._17_0_5_edc0357_1345510114380_725893_14596_endTime, _17_0_5_edc0357_1345510114380_725893_14596_endTime);
                addDependency(this._17_0_5_edc0357_1345510114882_909563_15267, _17_0_5_edc0357_1345510114882_909563_15267);
                init_17_0_5_edc0357_1345510114380_134530_14597Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114381_139867_14599 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1345510114380_725893_14596_exists = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114380_725893_14596_existsDependency = null;

            public ElaborationRule elaborationRule334 = null;

            public void init_17_0_5_edc0357_1345510114381_139867_14599Members() {
                try {
                    _17_0_5_edc0357_1345510114380_725893_14596_exists = new BooleanParameter("_17_0_5_edc0357_1345510114380_725893_14596_exists", this);
                    _17_0_5_edc0357_1345510114380_725893_14596_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114380_725893_14596_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114381_139867_14599Collections() {
                parameters.add(_17_0_5_edc0357_1345510114380_725893_14596_exists);
                dependencies.add(_17_0_5_edc0357_1345510114380_725893_14596_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510114381_139867_14599Elaborations() {
                Expression<?>[] arguments334 = new Expression<?>[1];
                arguments334[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition334 = new Expression<Boolean>(_17_0_5_edc0357_1345510114380_725893_14596_exists);
                elaborationRule334 = addElaborationRule(condition334, _17_0_5_edc0357_1345510113598_693480_13830.this, LADWP._17_0_5_edc0357_1345510113598_693480_13830._17_0_5_edc0357_1345510114380_725893_14596.class, "_ReadSelfAction_updateGeneration", arguments334);
            }

            public _17_0_5_edc0357_1345510114381_139867_14599() {
                super();
                init_17_0_5_edc0357_1345510114381_139867_14599Members();
                init_17_0_5_edc0357_1345510114381_139867_14599Collections();
                init_17_0_5_edc0357_1345510114381_139867_14599Elaborations();
            }

            public _17_0_5_edc0357_1345510114381_139867_14599(Expression<Integer> startTime) {
                super();
                init_17_0_5_edc0357_1345510114381_139867_14599Members();
                init_17_0_5_edc0357_1345510114381_139867_14599Collections();
                removeDependency(this.startTime);
                addDependency(this.startTime, startTime);
                init_17_0_5_edc0357_1345510114381_139867_14599Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114381_563515_14600 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1345510114380_134530_14597_endTime = null;

            public ConstraintExpression constraint335 = null;

            public void init_17_0_5_edc0357_1345510114381_563515_14600Members() {
                try {
                    _17_0_5_edc0357_1345510114380_134530_14597_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114380_134530_14597_endTime", this);
                    constraint335 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114380_134530_14597_endTime)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114381_563515_14600Collections() {
                parameters.add(_17_0_5_edc0357_1345510114380_134530_14597_endTime);
                constraintExpressions.add(constraint335);
            }

            public void init_17_0_5_edc0357_1345510114381_563515_14600Elaborations() {
            }

            public _17_0_5_edc0357_1345510114381_563515_14600() {
                super();
                init_17_0_5_edc0357_1345510114381_563515_14600Members();
                init_17_0_5_edc0357_1345510114381_563515_14600Collections();
                init_17_0_5_edc0357_1345510114381_563515_14600Elaborations();
            }

            public _17_0_5_edc0357_1345510114381_563515_14600(Expression<Integer> _17_0_5_edc0357_1345510114380_134530_14597_endTime) {
                super();
                init_17_0_5_edc0357_1345510114381_563515_14600Members();
                init_17_0_5_edc0357_1345510114381_563515_14600Collections();
                addDependency(this._17_0_5_edc0357_1345510114380_134530_14597_endTime, _17_0_5_edc0357_1345510114380_134530_14597_endTime);
                init_17_0_5_edc0357_1345510114381_563515_14600Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1346101453400_976869_14032 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1346101437766_368293_14030 = null;

            public Effect effect336 = null;

            public Parameter effect336Var = null;

            public void init_17_0_5_edc0357_1346101453400_976869_14032Members() {
                try {
                    _17_0_5_edc0357_1346101437766_368293_14030 = new IntegerParameter("_17_0_5_edc0357_1346101437766_368293_14030", this);
                    Object effect336VarV = sig_17_0_5_edc0357_1346101473960_270064_14063;
                    effect336Var = new Parameter("effect336Var", null, null, this);
                    addDependency(effect336Var, new Expression(effect336VarV));
                    effect336 = new EffectFunction(new FunctionCall(effect336Var, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { _17_0_5_edc0357_1346101437766_368293_14030, startTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346101453400_976869_14032Collections() {
                parameters.add(_17_0_5_edc0357_1346101437766_368293_14030);
                Set<Effect> effectsForeffect336Var = new HashSet<Effect>();
                effectsForeffect336Var.add(effect336);
                effects.put((Parameter<?>) effect336Var, effectsForeffect336Var);
            }

            public void init_17_0_5_edc0357_1346101453400_976869_14032Elaborations() {
            }

            public _17_0_5_edc0357_1346101453400_976869_14032() {
                super();
                init_17_0_5_edc0357_1346101453400_976869_14032Members();
                init_17_0_5_edc0357_1346101453400_976869_14032Collections();
                init_17_0_5_edc0357_1346101453400_976869_14032Elaborations();
            }

            public _17_0_5_edc0357_1346101453400_976869_14032(Expression<Integer> startTime, Expression<Integer> _17_0_5_edc0357_1346101437766_368293_14030) {
                super();
                init_17_0_5_edc0357_1346101453400_976869_14032Members();
                init_17_0_5_edc0357_1346101453400_976869_14032Collections();
                removeDependency(this.startTime);
                addDependency(this.startTime, startTime);
                addDependency(this._17_0_5_edc0357_1346101437766_368293_14030, _17_0_5_edc0357_1346101437766_368293_14030);
                init_17_0_5_edc0357_1346101453400_976869_14032Elaborations();
                fixTimeDependencies();
            }
        }

        public _17_0_5_edc0357_1345510113598_693480_13830(Expression<Integer> endTime, Expression<Integer> _17_0_5_edc0357_1346101437766_368293_14030) {
            super();
            init_17_0_5_edc0357_1345510113598_693480_13830Members();
            init_17_0_5_edc0357_1345510113598_693480_13830Collections();
            removeDependency(this.endTime);
            addDependency(this.endTime, endTime);
            addDependency(this._17_0_5_edc0357_1346101437766_368293_14030, _17_0_5_edc0357_1346101437766_368293_14030);
            init_17_0_5_edc0357_1345510113598_693480_13830Elaborations();
            fixTimeDependencies();
        }
    }

    public class _17_0_5_edc0357_1345510113599_525430_13831 extends DurativeEvent {

        public Parameter< ObjectFlow<Integer> > sig_17_0_5_edc0357_1345510114404_348430_14652 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_5_edc0357_1345510114899_683693_15279 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_5_edc0357_1345510114408_563101_14679 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_5_edc0357_1345510114897_165815_15276 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_5_edc0357_1345510114404_34398_14651 = null;

        public Parameter< ObjectFlow<LADWP> > sig_17_0_5_edc0357_1345510114406_582786_14666 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_5_edc0357_1345510114404_840866_14653 = null;

        public Parameter< ObjectFlow<LADWP> > sig_17_0_5_edc0357_1345510114404_445687_14661 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_5_edc0357_1345510114404_967702_14654 = null;

        public IntegerParameter invoke_time = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_5_edc0357_1345510114406_563398_14668 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_5_edc0357_1345510114406_650884_14669 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_5_edc0357_1345510114408_335204_14678 = null;

        public Parameter< ObjectFlow<LADWP> > sig_17_0_5_edc0357_1345510114405_994113_14663 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_5_edc0357_1345510114406_67045_14672 = null;

        public Parameter< ObjectFlow<LADWP> > sig_17_0_5_edc0357_1345510114405_752265_14664 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_5_edc0357_1345510114407_219573_14673 = null;

        public ElaborationRule elaborationRule337 = null;

        public void init_17_0_5_edc0357_1345510113599_525430_13831Members() {
            try {
                sig_17_0_5_edc0357_1345510114404_348430_14652 = new Parameter<ObjectFlow<Integer>>("sig_17_0_5_edc0357_1345510114404_348430_14652", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114404_348430_14652"), this);
                sig_17_0_5_edc0357_1345510114899_683693_15279 = new Parameter<ObjectFlow<Integer>>("sig_17_0_5_edc0357_1345510114899_683693_15279", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114899_683693_15279"), this);
                sig_17_0_5_edc0357_1345510114408_563101_14679 = new Parameter<ObjectFlow<Integer>>("sig_17_0_5_edc0357_1345510114408_563101_14679", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114408_563101_14679"), this);
                sig_17_0_5_edc0357_1345510114897_165815_15276 = new Parameter<ObjectFlow<Integer>>("sig_17_0_5_edc0357_1345510114897_165815_15276", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114897_165815_15276"), this);
                sig_17_0_5_edc0357_1345510114404_34398_14651 = new Parameter<ObjectFlow<Integer>>("sig_17_0_5_edc0357_1345510114404_34398_14651", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114404_34398_14651"), this);
                sig_17_0_5_edc0357_1345510114406_582786_14666 = new Parameter<ObjectFlow<LADWP>>("sig_17_0_5_edc0357_1345510114406_582786_14666", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114406_582786_14666"), this);
                sig_17_0_5_edc0357_1345510114404_840866_14653 = new Parameter<ObjectFlow<Integer>>("sig_17_0_5_edc0357_1345510114404_840866_14653", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114404_840866_14653"), this);
                sig_17_0_5_edc0357_1345510114404_445687_14661 = new Parameter<ObjectFlow<LADWP>>("sig_17_0_5_edc0357_1345510114404_445687_14661", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114404_445687_14661"), this);
                sig_17_0_5_edc0357_1345510114404_967702_14654 = new Parameter<ObjectFlow<Integer>>("sig_17_0_5_edc0357_1345510114404_967702_14654", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114404_967702_14654"), this);
                invoke_time = new IntegerParameter("invoke_time", this);
                sig_17_0_5_edc0357_1345510114406_563398_14668 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_5_edc0357_1345510114406_563398_14668", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114406_563398_14668"), this);
                sig_17_0_5_edc0357_1345510114406_650884_14669 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_5_edc0357_1345510114406_650884_14669", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114406_650884_14669"), this);
                sig_17_0_5_edc0357_1345510114408_335204_14678 = new Parameter<ObjectFlow<Integer>>("sig_17_0_5_edc0357_1345510114408_335204_14678", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114408_335204_14678"), this);
                sig_17_0_5_edc0357_1345510114405_994113_14663 = new Parameter<ObjectFlow<LADWP>>("sig_17_0_5_edc0357_1345510114405_994113_14663", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114405_994113_14663"), this);
                sig_17_0_5_edc0357_1345510114406_67045_14672 = new Parameter<ObjectFlow<Integer>>("sig_17_0_5_edc0357_1345510114406_67045_14672", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114406_67045_14672"), this);
                sig_17_0_5_edc0357_1345510114405_752265_14664 = new Parameter<ObjectFlow<LADWP>>("sig_17_0_5_edc0357_1345510114405_752265_14664", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114405_752265_14664"), this);
                sig_17_0_5_edc0357_1345510114407_219573_14673 = new Parameter<ObjectFlow<Integer>>("sig_17_0_5_edc0357_1345510114407_219573_14673", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114407_219573_14673"), this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void init_17_0_5_edc0357_1345510113599_525430_13831Collections() {
            parameters.add(sig_17_0_5_edc0357_1345510114404_348430_14652);
            parameters.add(sig_17_0_5_edc0357_1345510114899_683693_15279);
            parameters.add(sig_17_0_5_edc0357_1345510114408_563101_14679);
            parameters.add(sig_17_0_5_edc0357_1345510114897_165815_15276);
            parameters.add(sig_17_0_5_edc0357_1345510114404_34398_14651);
            parameters.add(sig_17_0_5_edc0357_1345510114406_582786_14666);
            parameters.add(sig_17_0_5_edc0357_1345510114404_840866_14653);
            parameters.add(sig_17_0_5_edc0357_1345510114404_445687_14661);
            parameters.add(sig_17_0_5_edc0357_1345510114404_967702_14654);
            parameters.add(invoke_time);
            parameters.add(sig_17_0_5_edc0357_1345510114406_563398_14668);
            parameters.add(sig_17_0_5_edc0357_1345510114406_650884_14669);
            parameters.add(sig_17_0_5_edc0357_1345510114408_335204_14678);
            parameters.add(sig_17_0_5_edc0357_1345510114405_994113_14663);
            parameters.add(sig_17_0_5_edc0357_1345510114406_67045_14672);
            parameters.add(sig_17_0_5_edc0357_1345510114405_752265_14664);
            parameters.add(sig_17_0_5_edc0357_1345510114407_219573_14673);
        }

        public void init_17_0_5_edc0357_1345510113599_525430_13831Elaborations() {
            Expression<?>[] arguments337 = new Expression<?>[1];
            arguments337[0] = new Expression<Integer>(invoke_time);
            Expression<Boolean> condition337 = new Expression<Boolean>(true);
            elaborationRule337 = addElaborationRule(condition337, _17_0_5_edc0357_1345510113599_525430_13831.this, LADWP._17_0_5_edc0357_1345510113599_525430_13831._17_0_5_edc0357_1345510114402_767242_14639.class, "_InitialNode_monitor_system", arguments337);
        }

        public _17_0_5_edc0357_1345510113599_525430_13831() {
            super();
            init_17_0_5_edc0357_1345510113599_525430_13831Members();
            init_17_0_5_edc0357_1345510113599_525430_13831Collections();
            init_17_0_5_edc0357_1345510113599_525430_13831Elaborations();
        }

        public class _17_0_5_edc0357_1345510114400_220423_14629 extends DurativeEvent {

            public Parameter< LADWP > _17_0_5_edc0357_1345510114900_877057_15280 = null;

            public BooleanParameter _17_0_5_edc0357_1345510114402_986311_14638_exists = null;

            public IntegerParameter _17_0_5_edc0357_1348001886585_689962_14324_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1345510114401_423840_14637_exists = null;

            public ConstraintExpression constraint338 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114402_986311_14638_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114401_423840_14637_existsDependency = null;

            public ElaborationRule elaborationRule339 = null;

            public ElaborationRule elaborationRule340 = null;

            public void init_17_0_5_edc0357_1345510114400_220423_14629Members() {
                try {
                    _17_0_5_edc0357_1345510114900_877057_15280 = new Parameter<LADWP>("_17_0_5_edc0357_1345510114900_877057_15280", null, LADWP.this, this);
                    _17_0_5_edc0357_1345510114402_986311_14638_exists = new BooleanParameter("_17_0_5_edc0357_1345510114402_986311_14638_exists", this);
                    _17_0_5_edc0357_1348001886585_689962_14324_endTime = new IntegerParameter("_17_0_5_edc0357_1348001886585_689962_14324_endTime", this);
                    _17_0_5_edc0357_1345510114401_423840_14637_exists = new BooleanParameter("_17_0_5_edc0357_1345510114401_423840_14637_exists", this);
                    constraint338 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1348001886585_689962_14324_endTime)));
                    _17_0_5_edc0357_1345510114402_986311_14638_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114402_986311_14638_exists, new Expression<Boolean>(true));
                    _17_0_5_edc0357_1345510114401_423840_14637_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114401_423840_14637_exists, new Functions.And(new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114404_840866_14653, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114404_967702_14654, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) }))));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114400_220423_14629Collections() {
                parameters.add(_17_0_5_edc0357_1345510114900_877057_15280);
                parameters.add(_17_0_5_edc0357_1345510114402_986311_14638_exists);
                parameters.add(_17_0_5_edc0357_1348001886585_689962_14324_endTime);
                parameters.add(_17_0_5_edc0357_1345510114401_423840_14637_exists);
                constraintExpressions.add(constraint338);
                dependencies.add(_17_0_5_edc0357_1345510114402_986311_14638_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510114401_423840_14637_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510114400_220423_14629Elaborations() {
                Expression<?>[] arguments339 = new Expression<?>[1];
                arguments339[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition339 = new Expression<Boolean>(_17_0_5_edc0357_1345510114401_423840_14637_exists);
                elaborationRule339 = addElaborationRule(condition339, _17_0_5_edc0357_1345510113599_525430_13831.this, LADWP._17_0_5_edc0357_1345510113599_525430_13831._17_0_5_edc0357_1345510114401_423840_14637.class, "_CallBehaviorAction_monitor_system", arguments339);
                Expression<?>[] arguments340 = new Expression<?>[2];
                arguments340[0] = new Expression<Integer>(endTime);
                arguments340[1] = new Expression<LADWP>(_17_0_5_edc0357_1345510114900_877057_15280);
                Expression<Boolean> condition340 = new Expression<Boolean>(_17_0_5_edc0357_1345510114402_986311_14638_exists);
                elaborationRule340 = addElaborationRule(condition340, _17_0_5_edc0357_1345510113599_525430_13831.this, LADWP._17_0_5_edc0357_1345510113599_525430_13831._17_0_5_edc0357_1345510114402_986311_14638.class, "readself_fork_ForkNode_monitor_system", arguments340);
            }

            public _17_0_5_edc0357_1345510114400_220423_14629() {
                super();
                init_17_0_5_edc0357_1345510114400_220423_14629Members();
                init_17_0_5_edc0357_1345510114400_220423_14629Collections();
                init_17_0_5_edc0357_1345510114400_220423_14629Elaborations();
            }

            public _17_0_5_edc0357_1345510114400_220423_14629(Expression<Integer> _17_0_5_edc0357_1348001886585_689962_14324_endTime) {
                super();
                init_17_0_5_edc0357_1345510114400_220423_14629Members();
                init_17_0_5_edc0357_1345510114400_220423_14629Collections();
                addDependency(this._17_0_5_edc0357_1348001886585_689962_14324_endTime, _17_0_5_edc0357_1348001886585_689962_14324_endTime);
                init_17_0_5_edc0357_1345510114400_220423_14629Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114400_746991_14630 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1345510114901_658987_15281 = null;

            public Parameter< LADWP > _17_0_5_edc0357_1345510114901_629279_15282 = null;

            public IntegerParameter _17_0_5_edc0357_1345510114402_986311_14638_endTime = null;

            public ConstraintExpression constraint341 = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114901_658987_15281Dependency = null;

            public Effect effect342 = null;

            public Parameter effect342Var = null;

            public void init_17_0_5_edc0357_1345510114400_746991_14630Members() {
                try {
                    _17_0_5_edc0357_1345510114901_658987_15281 = new IntegerParameter("_17_0_5_edc0357_1345510114901_658987_15281", this);
                    _17_0_5_edc0357_1345510114901_629279_15282 = new Parameter<LADWP>("_17_0_5_edc0357_1345510114901_629279_15282", null, null, this);
                    _17_0_5_edc0357_1345510114402_986311_14638_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114402_986311_14638_endTime", this);
                    constraint341 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114402_986311_14638_endTime)));
                    _17_0_5_edc0357_1345510114901_658987_15281Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114901_658987_15281, new Expression(new FunctionCall(null, Utils.getMethodForArgTypes("LADWP._17_0_5_edc0357_1345510113599_525430_13831._17_0_5_edc0357_1345510114400_746991_14630", "generated", "getValue"), new Object[] { new Expression<Integer>(startTime) }, new FunctionCall(null, Parameter.class, "getValue", (Object[]) null, new FunctionCall(_17_0_5_edc0357_1345510114901_629279_15282, Parameter.class, "getMember", new Object[] { "actual_load__17_0_5_edc0357_1345510113609_489064_13842" })))));
                    Object effect342VarV = sig_17_0_5_edc0357_1345510114404_840866_14653;
                    effect342Var = new Parameter("effect342Var", null, null, this);
                    addDependency(effect342Var, new Expression(effect342VarV));
                    effect342 = new EffectFunction(new FunctionCall(effect342Var, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { _17_0_5_edc0357_1345510114901_658987_15281, startTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114400_746991_14630Collections() {
                parameters.add(_17_0_5_edc0357_1345510114901_658987_15281);
                parameters.add(_17_0_5_edc0357_1345510114901_629279_15282);
                parameters.add(_17_0_5_edc0357_1345510114402_986311_14638_endTime);
                constraintExpressions.add(constraint341);
                dependencies.add(_17_0_5_edc0357_1345510114901_658987_15281Dependency);
                Set<Effect> effectsForeffect342Var = new HashSet<Effect>();
                effectsForeffect342Var.add(effect342);
                effects.put((Parameter<?>) effect342Var, effectsForeffect342Var);
            }

            public void init_17_0_5_edc0357_1345510114400_746991_14630Elaborations() {
            }

            public _17_0_5_edc0357_1345510114400_746991_14630() {
                super();
                init_17_0_5_edc0357_1345510114400_746991_14630Members();
                init_17_0_5_edc0357_1345510114400_746991_14630Collections();
                init_17_0_5_edc0357_1345510114400_746991_14630Elaborations();
            }

            public _17_0_5_edc0357_1345510114400_746991_14630(Expression<Integer> _17_0_5_edc0357_1345510114402_986311_14638_endTime, Expression<LADWP> _17_0_5_edc0357_1345510114901_629279_15282) {
                super();
                init_17_0_5_edc0357_1345510114400_746991_14630Members();
                init_17_0_5_edc0357_1345510114400_746991_14630Collections();
                addDependency(this._17_0_5_edc0357_1345510114402_986311_14638_endTime, _17_0_5_edc0357_1345510114402_986311_14638_endTime);
                addDependency(this._17_0_5_edc0357_1345510114901_629279_15282, _17_0_5_edc0357_1345510114901_629279_15282);
                init_17_0_5_edc0357_1345510114400_746991_14630Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114400_398544_14631 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1345510114902_370167_15283 = null;

            public BooleanParameter _17_0_5_edc0357_1345510114401_464493_14634_exists = null;

            public Parameter< LADWP > _17_0_5_edc0357_1345510114903_863541_15284 = null;

            public IntegerParameter _17_0_5_edc0357_1345510114402_986311_14638_endTime = null;

            public ConstraintExpression constraint343 = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114902_370167_15283Dependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114401_464493_14634_existsDependency = null;

            public ElaborationRule elaborationRule344 = null;

            public void init_17_0_5_edc0357_1345510114400_398544_14631Members() {
                try {
                    _17_0_5_edc0357_1345510114902_370167_15283 = new IntegerParameter("_17_0_5_edc0357_1345510114902_370167_15283", this);
                    _17_0_5_edc0357_1345510114401_464493_14634_exists = new BooleanParameter("_17_0_5_edc0357_1345510114401_464493_14634_exists", this);
                    _17_0_5_edc0357_1345510114903_863541_15284 = new Parameter<LADWP>("_17_0_5_edc0357_1345510114903_863541_15284", null, null, this);
                    _17_0_5_edc0357_1345510114402_986311_14638_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114402_986311_14638_endTime", this);
                    constraint343 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114402_986311_14638_endTime)));
                    _17_0_5_edc0357_1345510114902_370167_15283Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114902_370167_15283, new Expression(new FunctionCall(null, Utils.getMethodForArgTypes("LADWP._17_0_5_edc0357_1345510113599_525430_13831._17_0_5_edc0357_1345510114400_398544_14631", "generated", "getValue"), new Object[] { new Expression<Integer>(startTime) }, new FunctionCall(null, Parameter.class, "getValue", (Object[]) null, new FunctionCall(_17_0_5_edc0357_1345510114903_863541_15284, Parameter.class, "getMember", new Object[] { "reported_generation__17_0_5_edc0357_1345510113612_851613_13846" })))));
                    _17_0_5_edc0357_1345510114401_464493_14634_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114401_464493_14634_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114400_398544_14631Collections() {
                parameters.add(_17_0_5_edc0357_1345510114902_370167_15283);
                parameters.add(_17_0_5_edc0357_1345510114401_464493_14634_exists);
                parameters.add(_17_0_5_edc0357_1345510114903_863541_15284);
                parameters.add(_17_0_5_edc0357_1345510114402_986311_14638_endTime);
                constraintExpressions.add(constraint343);
                dependencies.add(_17_0_5_edc0357_1345510114902_370167_15283Dependency);
                dependencies.add(_17_0_5_edc0357_1345510114401_464493_14634_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510114400_398544_14631Elaborations() {
                Expression<?>[] arguments344 = new Expression<?>[2];
                arguments344[0] = new Expression<Integer>(endTime);
                arguments344[1] = new Expression<Integer>(_17_0_5_edc0357_1345510114902_370167_15283);
                Expression<Boolean> condition344 = new Expression<Boolean>(_17_0_5_edc0357_1345510114401_464493_14634_exists);
                elaborationRule344 = addElaborationRule(condition344, _17_0_5_edc0357_1345510113599_525430_13831.this, LADWP._17_0_5_edc0357_1345510113599_525430_13831._17_0_5_edc0357_1345510114401_464493_14634.class, "reported_fork_ForkNode_monitor_system", arguments344);
            }

            public _17_0_5_edc0357_1345510114400_398544_14631() {
                super();
                init_17_0_5_edc0357_1345510114400_398544_14631Members();
                init_17_0_5_edc0357_1345510114400_398544_14631Collections();
                init_17_0_5_edc0357_1345510114400_398544_14631Elaborations();
            }

            public _17_0_5_edc0357_1345510114400_398544_14631(Expression<Integer> _17_0_5_edc0357_1345510114402_986311_14638_endTime, Expression<LADWP> _17_0_5_edc0357_1345510114903_863541_15284) {
                super();
                init_17_0_5_edc0357_1345510114400_398544_14631Members();
                init_17_0_5_edc0357_1345510114400_398544_14631Collections();
                addDependency(this._17_0_5_edc0357_1345510114402_986311_14638_endTime, _17_0_5_edc0357_1345510114402_986311_14638_endTime);
                addDependency(this._17_0_5_edc0357_1345510114903_863541_15284, _17_0_5_edc0357_1345510114903_863541_15284);
                init_17_0_5_edc0357_1345510114400_398544_14631Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114400_400850_14632 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1345510114402_986311_14638_endTime = null;

            public IntegerParameter _17_0_5_edc0357_1345510114903_116782_15285 = null;

            public Parameter< LADWP > _17_0_5_edc0357_1345510114904_450251_15286 = null;

            public ConstraintExpression constraint345 = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114903_116782_15285Dependency = null;

            public Effect effect346 = null;

            public Parameter effect346Var = null;

            public void init_17_0_5_edc0357_1345510114400_400850_14632Members() {
                try {
                    _17_0_5_edc0357_1345510114402_986311_14638_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114402_986311_14638_endTime", this);
                    _17_0_5_edc0357_1345510114903_116782_15285 = new IntegerParameter("_17_0_5_edc0357_1345510114903_116782_15285", this);
                    _17_0_5_edc0357_1345510114904_450251_15286 = new Parameter<LADWP>("_17_0_5_edc0357_1345510114904_450251_15286", null, null, this);
                    constraint345 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114402_986311_14638_endTime)));
                    _17_0_5_edc0357_1345510114903_116782_15285Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114903_116782_15285, new Expression(new FunctionCall(null, Utils.getMethodForArgTypes("LADWP._17_0_5_edc0357_1345510113599_525430_13831._17_0_5_edc0357_1345510114400_400850_14632", "generated", "getValue"), new Object[] { new Expression<Integer>(startTime) }, new FunctionCall(null, Parameter.class, "getValue", (Object[]) null, new FunctionCall(_17_0_5_edc0357_1345510114904_450251_15286, Parameter.class, "getMember", new Object[] { "expected_load__17_0_5_edc0357_1345510113611_416109_13845" })))));
                    Object effect346VarV = sig_17_0_5_edc0357_1345510114404_348430_14652;
                    effect346Var = new Parameter("effect346Var", null, null, this);
                    addDependency(effect346Var, new Expression(effect346VarV));
                    effect346 = new EffectFunction(new FunctionCall(effect346Var, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { _17_0_5_edc0357_1345510114903_116782_15285, startTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114400_400850_14632Collections() {
                parameters.add(_17_0_5_edc0357_1345510114402_986311_14638_endTime);
                parameters.add(_17_0_5_edc0357_1345510114903_116782_15285);
                parameters.add(_17_0_5_edc0357_1345510114904_450251_15286);
                constraintExpressions.add(constraint345);
                dependencies.add(_17_0_5_edc0357_1345510114903_116782_15285Dependency);
                Set<Effect> effectsForeffect346Var = new HashSet<Effect>();
                effectsForeffect346Var.add(effect346);
                effects.put((Parameter<?>) effect346Var, effectsForeffect346Var);
            }

            public void init_17_0_5_edc0357_1345510114400_400850_14632Elaborations() {
            }

            public _17_0_5_edc0357_1345510114400_400850_14632() {
                super();
                init_17_0_5_edc0357_1345510114400_400850_14632Members();
                init_17_0_5_edc0357_1345510114400_400850_14632Collections();
                init_17_0_5_edc0357_1345510114400_400850_14632Elaborations();
            }

            public _17_0_5_edc0357_1345510114400_400850_14632(Expression<Integer> _17_0_5_edc0357_1345510114402_986311_14638_endTime, Expression<LADWP> _17_0_5_edc0357_1345510114904_450251_15286) {
                super();
                init_17_0_5_edc0357_1345510114400_400850_14632Members();
                init_17_0_5_edc0357_1345510114400_400850_14632Collections();
                addDependency(this._17_0_5_edc0357_1345510114402_986311_14638_endTime, _17_0_5_edc0357_1345510114402_986311_14638_endTime);
                addDependency(this._17_0_5_edc0357_1345510114904_450251_15286, _17_0_5_edc0357_1345510114904_450251_15286);
                init_17_0_5_edc0357_1345510114400_400850_14632Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114400_500051_14633 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1346106020869_685207_15351_endTime = null;

            public ConstraintExpression constraint347 = null;

            public void init_17_0_5_edc0357_1345510114400_500051_14633Members() {
                try {
                    _17_0_5_edc0357_1346106020869_685207_15351_endTime = new IntegerParameter("_17_0_5_edc0357_1346106020869_685207_15351_endTime", this);
                    constraint347 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346106020869_685207_15351_endTime)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114400_500051_14633Collections() {
                parameters.add(_17_0_5_edc0357_1346106020869_685207_15351_endTime);
                constraintExpressions.add(constraint347);
            }

            public void init_17_0_5_edc0357_1345510114400_500051_14633Elaborations() {
            }

            public _17_0_5_edc0357_1345510114400_500051_14633() {
                super();
                init_17_0_5_edc0357_1345510114400_500051_14633Members();
                init_17_0_5_edc0357_1345510114400_500051_14633Collections();
                init_17_0_5_edc0357_1345510114400_500051_14633Elaborations();
            }

            public _17_0_5_edc0357_1345510114400_500051_14633(Expression<Integer> _17_0_5_edc0357_1346106020869_685207_15351_endTime) {
                super();
                init_17_0_5_edc0357_1345510114400_500051_14633Members();
                init_17_0_5_edc0357_1345510114400_500051_14633Collections();
                addDependency(this._17_0_5_edc0357_1346106020869_685207_15351_endTime, _17_0_5_edc0357_1346106020869_685207_15351_endTime);
                init_17_0_5_edc0357_1345510114400_500051_14633Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114401_464493_14634 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1345510114400_398544_14631_endTime = null;

            public IntegerParameter objectToPass = null;

            public ConstraintExpression constraint348 = null;

            public Effect effect349 = null;

            public Parameter effect349Var = null;

            public Effect effect350 = null;

            public Parameter effect350Var = null;

            public void init_17_0_5_edc0357_1345510114401_464493_14634Members() {
                try {
                    _17_0_5_edc0357_1345510114400_398544_14631_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114400_398544_14631_endTime", this);
                    objectToPass = new IntegerParameter("objectToPass", this);
                    constraint348 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114400_398544_14631_endTime)));
                    Object effect349VarV = sig_17_0_5_edc0357_1345510114404_34398_14651;
                    effect349Var = new Parameter("effect349Var", null, null, this);
                    addDependency(effect349Var, new Expression(effect349VarV));
                    effect349 = new EffectFunction(new FunctionCall(effect349Var, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                    Object effect350VarV = sig_17_0_5_edc0357_1345510114404_967702_14654;
                    effect350Var = new Parameter("effect350Var", null, null, this);
                    addDependency(effect350Var, new Expression(effect350VarV));
                    effect350 = new EffectFunction(new FunctionCall(effect350Var, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114401_464493_14634Collections() {
                parameters.add(_17_0_5_edc0357_1345510114400_398544_14631_endTime);
                parameters.add(objectToPass);
                constraintExpressions.add(constraint348);
                Set<Effect> effectsForeffect349Var = new HashSet<Effect>();
                effectsForeffect349Var.add(effect349);
                effects.put((Parameter<?>) effect349Var, effectsForeffect349Var);
                Set<Effect> effectsForeffect350Var = new HashSet<Effect>();
                effectsForeffect350Var.add(effect350);
                effects.put((Parameter<?>) effect350Var, effectsForeffect350Var);
            }

            public void init_17_0_5_edc0357_1345510114401_464493_14634Elaborations() {
            }

            public _17_0_5_edc0357_1345510114401_464493_14634() {
                super();
                init_17_0_5_edc0357_1345510114401_464493_14634Members();
                init_17_0_5_edc0357_1345510114401_464493_14634Collections();
                init_17_0_5_edc0357_1345510114401_464493_14634Elaborations();
            }

            public _17_0_5_edc0357_1345510114401_464493_14634(Expression<Integer> _17_0_5_edc0357_1345510114400_398544_14631_endTime, Expression<Integer> objectToPass) {
                super();
                init_17_0_5_edc0357_1345510114401_464493_14634Members();
                init_17_0_5_edc0357_1345510114401_464493_14634Collections();
                addDependency(this._17_0_5_edc0357_1345510114400_398544_14631_endTime, _17_0_5_edc0357_1345510114400_398544_14631_endTime);
                addDependency(this.objectToPass, objectToPass);
                init_17_0_5_edc0357_1345510114401_464493_14634Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114401_676911_14635 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1345510114905_557932_15287 = null;

            public IntegerParameter _17_0_5_edc0357_1345510114401_569392_14636_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1345510114403_226098_14648_exists = null;

            public Parameter< LADWP > _17_0_5_edc0357_1345510114906_761156_15288 = null;

            public ConstraintExpression constraint351 = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114905_557932_15287Dependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114403_226098_14648_existsDependency = null;

            public Dependency< LADWP > _17_0_5_edc0357_1345510114906_761156_15288Dependency = null;

            public Effect effect352 = null;

            public Parameter effect352Var = null;

            public ElaborationRule elaborationRule353 = null;

            public void init_17_0_5_edc0357_1345510114401_676911_14635Members() {
                try {
                    _17_0_5_edc0357_1345510114905_557932_15287 = new IntegerParameter("_17_0_5_edc0357_1345510114905_557932_15287", this);
                    _17_0_5_edc0357_1345510114401_569392_14636_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114401_569392_14636_endTime", this);
                    _17_0_5_edc0357_1345510114403_226098_14648_exists = new BooleanParameter("_17_0_5_edc0357_1345510114403_226098_14648_exists", this);
                    _17_0_5_edc0357_1345510114906_761156_15288 = new Parameter<LADWP>("_17_0_5_edc0357_1345510114906_761156_15288", null, null, this);
                    constraint351 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114401_569392_14636_endTime)));
                    _17_0_5_edc0357_1345510114905_557932_15287Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114905_557932_15287, new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114408_335204_14678, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1345510114403_226098_14648_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114403_226098_14648_exists, new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114408_563101_14679, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })));
                    _17_0_5_edc0357_1345510114906_761156_15288Dependency = new Dependency<LADWP>(_17_0_5_edc0357_1345510114906_761156_15288, new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114404_445687_14661, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    Object effect352VarV = expected_margin__17_0_5_edc0357_1345510113614_771928_13849;
                    effect352Var = new Parameter("effect352Var", null, null, this);
                    addDependency(effect352Var, new Expression(effect352VarV));
                    effect352 = new EffectFunction(new FunctionCall(effect352Var, Utils.getMethodForArgTypes("TimeVaryingMap<Integer>", "generated", "setValue", java.lang.Integer.class, java.lang.Object.class), new Object[] { startTime, _17_0_5_edc0357_1345510114905_557932_15287 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114401_676911_14635Collections() {
                parameters.add(_17_0_5_edc0357_1345510114905_557932_15287);
                parameters.add(_17_0_5_edc0357_1345510114401_569392_14636_endTime);
                parameters.add(_17_0_5_edc0357_1345510114403_226098_14648_exists);
                parameters.add(_17_0_5_edc0357_1345510114906_761156_15288);
                constraintExpressions.add(constraint351);
                dependencies.add(_17_0_5_edc0357_1345510114905_557932_15287Dependency);
                dependencies.add(_17_0_5_edc0357_1345510114403_226098_14648_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510114906_761156_15288Dependency);
                Set<Effect> effectsForeffect352Var = new HashSet<Effect>();
                effectsForeffect352Var.add(effect352);
                effects.put((Parameter<?>) effect352Var, effectsForeffect352Var);
            }

            public void init_17_0_5_edc0357_1345510114401_676911_14635Elaborations() {
                Expression<?>[] arguments353 = new Expression<?>[1];
                arguments353[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition353 = new Expression<Boolean>(_17_0_5_edc0357_1345510114403_226098_14648_exists);
                elaborationRule353 = addElaborationRule(condition353, _17_0_5_edc0357_1345510113599_525430_13831.this, LADWP._17_0_5_edc0357_1345510113599_525430_13831._17_0_5_edc0357_1345510114403_226098_14648.class, "decideDR_DecisionNode_monitor_system", arguments353);
            }

            public _17_0_5_edc0357_1345510114401_676911_14635() {
                super();
                init_17_0_5_edc0357_1345510114401_676911_14635Members();
                init_17_0_5_edc0357_1345510114401_676911_14635Collections();
                init_17_0_5_edc0357_1345510114401_676911_14635Elaborations();
            }

            public _17_0_5_edc0357_1345510114401_676911_14635(Expression<Integer> _17_0_5_edc0357_1345510114401_569392_14636_endTime) {
                super();
                init_17_0_5_edc0357_1345510114401_676911_14635Members();
                init_17_0_5_edc0357_1345510114401_676911_14635Collections();
                addDependency(this._17_0_5_edc0357_1345510114401_569392_14636_endTime, _17_0_5_edc0357_1345510114401_569392_14636_endTime);
                init_17_0_5_edc0357_1345510114401_676911_14635Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114401_569392_14636 extends DurativeEvent {

            public IntegerParameter exp = null;

            public IntegerParameter _17_0_5_edc0357_1345510114896_642880_15274 = null;

            public IntegerParameter _17_0_5_edc0357_1345510114906_56003_15289 = null;

            public IntegerParameter _17_0_5_edc0357_1345510114897_464730_15275 = null;

            public IntegerParameter _17_0_5_edc0357_1345510114403_590610_14645_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1345510114403_960787_14647_exists = null;

            public IntegerParameter diff = null;

            public IntegerParameter gen = null;

            public BooleanParameter _17_0_5_edc0357_1345510114401_676911_14635_exists = null;

            public ConstraintExpression constraint354 = null;

            public Dependency< Integer > expDependency = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114896_642880_15274Dependency = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114906_56003_15289Dependency = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114897_464730_15275Dependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114403_960787_14647_existsDependency = null;

            public Dependency< Integer > diffDependency = null;

            public Dependency< Integer > genDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114401_676911_14635_existsDependency = null;

            public ElaborationRule elaborationRule355 = null;

            public ElaborationRule elaborationRule356 = null;

            public void init_17_0_5_edc0357_1345510114401_569392_14636Members() {
                try {
                    exp = new IntegerParameter("exp", this);
                    _17_0_5_edc0357_1345510114896_642880_15274 = new IntegerParameter("_17_0_5_edc0357_1345510114896_642880_15274", this);
                    _17_0_5_edc0357_1345510114906_56003_15289 = new IntegerParameter("_17_0_5_edc0357_1345510114906_56003_15289", this);
                    _17_0_5_edc0357_1345510114897_464730_15275 = new IntegerParameter("_17_0_5_edc0357_1345510114897_464730_15275", this);
                    _17_0_5_edc0357_1345510114403_590610_14645_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114403_590610_14645_endTime", this);
                    _17_0_5_edc0357_1345510114403_960787_14647_exists = new BooleanParameter("_17_0_5_edc0357_1345510114403_960787_14647_exists", this);
                    diff = new IntegerParameter("diff", this);
                    gen = new IntegerParameter("gen", this);
                    _17_0_5_edc0357_1345510114401_676911_14635_exists = new BooleanParameter("_17_0_5_edc0357_1345510114401_676911_14635_exists", this);
                    constraint354 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114403_590610_14645_endTime)));
                    expDependency = new Dependency<Integer>(exp, new Expression<Integer>(_17_0_5_edc0357_1345510114897_464730_15275));
                    _17_0_5_edc0357_1345510114896_642880_15274Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114896_642880_15274, new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114404_34398_14651, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1345510114906_56003_15289Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114906_56003_15289, new Expression<Integer>(diff));
                    _17_0_5_edc0357_1345510114897_464730_15275Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114897_464730_15275, new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114404_348430_14652, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1345510114403_960787_14647_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114403_960787_14647_exists, new Expression<Boolean>(true));
                    diffDependency = new Dependency<Integer>(diff, new Functions.Minus(new Expression<Integer>(gen), new Expression<Integer>(exp)));
                    genDependency = new Dependency<Integer>(gen, new Expression<Integer>(_17_0_5_edc0357_1345510114896_642880_15274));
                    _17_0_5_edc0357_1345510114401_676911_14635_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114401_676911_14635_exists, new Functions.And(new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114404_445687_14661, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114408_335204_14678, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) }))));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114401_569392_14636Collections() {
                parameters.add(exp);
                parameters.add(_17_0_5_edc0357_1345510114896_642880_15274);
                parameters.add(_17_0_5_edc0357_1345510114906_56003_15289);
                parameters.add(_17_0_5_edc0357_1345510114897_464730_15275);
                parameters.add(_17_0_5_edc0357_1345510114403_590610_14645_endTime);
                parameters.add(_17_0_5_edc0357_1345510114403_960787_14647_exists);
                parameters.add(diff);
                parameters.add(gen);
                parameters.add(_17_0_5_edc0357_1345510114401_676911_14635_exists);
                constraintExpressions.add(constraint354);
                dependencies.add(expDependency);
                dependencies.add(_17_0_5_edc0357_1345510114896_642880_15274Dependency);
                dependencies.add(_17_0_5_edc0357_1345510114906_56003_15289Dependency);
                dependencies.add(_17_0_5_edc0357_1345510114897_464730_15275Dependency);
                dependencies.add(_17_0_5_edc0357_1345510114403_960787_14647_existsDependency);
                dependencies.add(diffDependency);
                dependencies.add(genDependency);
                dependencies.add(_17_0_5_edc0357_1345510114401_676911_14635_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510114401_569392_14636Elaborations() {
                Expression<?>[] arguments355 = new Expression<?>[2];
                arguments355[0] = new Expression<Integer>(endTime);
                arguments355[1] = new Expression<Integer>(_17_0_5_edc0357_1345510114906_56003_15289);
                Expression<Boolean> condition355 = new Expression<Boolean>(_17_0_5_edc0357_1345510114403_960787_14647_exists);
                elaborationRule355 = addElaborationRule(condition355, _17_0_5_edc0357_1345510113599_525430_13831.this, LADWP._17_0_5_edc0357_1345510113599_525430_13831._17_0_5_edc0357_1345510114403_960787_14647.class, "exp_margin_fork_ForkNode_monitor_system", arguments355);
                Expression<?>[] arguments356 = new Expression<?>[1];
                arguments356[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition356 = new Expression<Boolean>(_17_0_5_edc0357_1345510114401_676911_14635_exists);
                elaborationRule356 = addElaborationRule(condition356, _17_0_5_edc0357_1345510113599_525430_13831.this, LADWP._17_0_5_edc0357_1345510113599_525430_13831._17_0_5_edc0357_1345510114401_676911_14635.class, "_AddStructuralFeatureValueAction_monitor_system", arguments356);
            }

            public _17_0_5_edc0357_1345510114401_569392_14636() {
                super();
                init_17_0_5_edc0357_1345510114401_569392_14636Members();
                init_17_0_5_edc0357_1345510114401_569392_14636Collections();
                init_17_0_5_edc0357_1345510114401_569392_14636Elaborations();
            }

            public _17_0_5_edc0357_1345510114401_569392_14636(Expression<Integer> _17_0_5_edc0357_1345510114403_590610_14645_endTime) {
                super();
                init_17_0_5_edc0357_1345510114401_569392_14636Members();
                init_17_0_5_edc0357_1345510114401_569392_14636Collections();
                addDependency(this._17_0_5_edc0357_1345510114403_590610_14645_endTime, _17_0_5_edc0357_1345510114403_590610_14645_endTime);
                init_17_0_5_edc0357_1345510114401_569392_14636Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114401_423840_14637 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1345510114908_64090_15292 = null;

            public BooleanParameter _17_0_5_edc0357_1345510114403_773368_14646_exists = null;

            public IntegerParameter _17_0_5_edc0357_1345510114400_220423_14629_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1345510114402_549043_14640_exists = null;

            public IntegerParameter s = null;

            public IntegerParameter _17_0_5_edc0357_1345510114898_711162_15277 = null;

            public IntegerParameter gen = null;

            public IntegerParameter act = null;

            public IntegerParameter _17_0_5_edc0357_1345510114899_276069_15278 = null;

            public ConstraintExpression constraint357 = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114908_64090_15292Dependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114403_773368_14646_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114402_549043_14640_existsDependency = null;

            public Dependency< Integer > sDependency = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114898_711162_15277Dependency = null;

            public Dependency< Integer > genDependency = null;

            public Dependency< Integer > actDependency = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114899_276069_15278Dependency = null;

            public ElaborationRule elaborationRule358 = null;

            public ElaborationRule elaborationRule359 = null;

            public void init_17_0_5_edc0357_1345510114401_423840_14637Members() {
                try {
                    _17_0_5_edc0357_1345510114908_64090_15292 = new IntegerParameter("_17_0_5_edc0357_1345510114908_64090_15292", this);
                    _17_0_5_edc0357_1345510114403_773368_14646_exists = new BooleanParameter("_17_0_5_edc0357_1345510114403_773368_14646_exists", this);
                    _17_0_5_edc0357_1345510114400_220423_14629_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114400_220423_14629_endTime", this);
                    _17_0_5_edc0357_1345510114402_549043_14640_exists = new BooleanParameter("_17_0_5_edc0357_1345510114402_549043_14640_exists", this);
                    s = new IntegerParameter("s", this);
                    _17_0_5_edc0357_1345510114898_711162_15277 = new IntegerParameter("_17_0_5_edc0357_1345510114898_711162_15277", this);
                    gen = new IntegerParameter("gen", this);
                    act = new IntegerParameter("act", this);
                    _17_0_5_edc0357_1345510114899_276069_15278 = new IntegerParameter("_17_0_5_edc0357_1345510114899_276069_15278", this);
                    constraint357 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114400_220423_14629_endTime)));
                    _17_0_5_edc0357_1345510114908_64090_15292Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114908_64090_15292, new Expression<Integer>(s));
                    _17_0_5_edc0357_1345510114403_773368_14646_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114403_773368_14646_exists, new Expression<Boolean>(true));
                    _17_0_5_edc0357_1345510114402_549043_14640_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114402_549043_14640_exists, new Functions.And(new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114405_994113_14663, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114406_67045_14672, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) }))));
                    sDependency = new Dependency<Integer>(s, new Functions.Minus(new Expression<Integer>(gen), new Expression<Integer>(act)));
                    _17_0_5_edc0357_1345510114898_711162_15277Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114898_711162_15277, new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114404_840866_14653, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    genDependency = new Dependency<Integer>(gen, new Expression<Integer>(_17_0_5_edc0357_1345510114899_276069_15278));
                    actDependency = new Dependency<Integer>(act, new Expression<Integer>(_17_0_5_edc0357_1345510114898_711162_15277));
                    _17_0_5_edc0357_1345510114899_276069_15278Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114899_276069_15278, new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114404_967702_14654, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114401_423840_14637Collections() {
                parameters.add(_17_0_5_edc0357_1345510114908_64090_15292);
                parameters.add(_17_0_5_edc0357_1345510114403_773368_14646_exists);
                parameters.add(_17_0_5_edc0357_1345510114400_220423_14629_endTime);
                parameters.add(_17_0_5_edc0357_1345510114402_549043_14640_exists);
                parameters.add(s);
                parameters.add(_17_0_5_edc0357_1345510114898_711162_15277);
                parameters.add(gen);
                parameters.add(act);
                parameters.add(_17_0_5_edc0357_1345510114899_276069_15278);
                constraintExpressions.add(constraint357);
                dependencies.add(_17_0_5_edc0357_1345510114908_64090_15292Dependency);
                dependencies.add(_17_0_5_edc0357_1345510114403_773368_14646_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510114402_549043_14640_existsDependency);
                dependencies.add(sDependency);
                dependencies.add(_17_0_5_edc0357_1345510114898_711162_15277Dependency);
                dependencies.add(genDependency);
                dependencies.add(actDependency);
                dependencies.add(_17_0_5_edc0357_1345510114899_276069_15278Dependency);
            }

            public void init_17_0_5_edc0357_1345510114401_423840_14637Elaborations() {
                Expression<?>[] arguments358 = new Expression<?>[1];
                arguments358[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition358 = new Expression<Boolean>(_17_0_5_edc0357_1345510114402_549043_14640_exists);
                elaborationRule358 = addElaborationRule(condition358, _17_0_5_edc0357_1345510113599_525430_13831.this, LADWP._17_0_5_edc0357_1345510113599_525430_13831._17_0_5_edc0357_1345510114402_549043_14640.class, "_AddStructuralFeatureValueAction_monitor_system", arguments358);
                Expression<?>[] arguments359 = new Expression<?>[2];
                arguments359[0] = new Expression<Integer>(endTime);
                arguments359[1] = new Expression<Integer>(_17_0_5_edc0357_1345510114908_64090_15292);
                Expression<Boolean> condition359 = new Expression<Boolean>(_17_0_5_edc0357_1345510114403_773368_14646_exists);
                elaborationRule359 = addElaborationRule(condition359, _17_0_5_edc0357_1345510113599_525430_13831.this, LADWP._17_0_5_edc0357_1345510113599_525430_13831._17_0_5_edc0357_1345510114403_773368_14646.class, "current_margin_fork_ForkNode_monitor_system", arguments359);
            }

            public _17_0_5_edc0357_1345510114401_423840_14637() {
                super();
                init_17_0_5_edc0357_1345510114401_423840_14637Members();
                init_17_0_5_edc0357_1345510114401_423840_14637Collections();
                init_17_0_5_edc0357_1345510114401_423840_14637Elaborations();
            }

            public _17_0_5_edc0357_1345510114401_423840_14637(Expression<Integer> _17_0_5_edc0357_1345510114400_220423_14629_endTime) {
                super();
                init_17_0_5_edc0357_1345510114401_423840_14637Members();
                init_17_0_5_edc0357_1345510114401_423840_14637Collections();
                addDependency(this._17_0_5_edc0357_1345510114400_220423_14629_endTime, _17_0_5_edc0357_1345510114400_220423_14629_endTime);
                init_17_0_5_edc0357_1345510114401_423840_14637Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114402_986311_14638 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1345510114400_746991_14630_exists = null;

            public BooleanParameter _17_0_5_edc0357_1345510114400_398544_14631_exists = null;

            public BooleanParameter _17_0_5_edc0357_1345510114400_400850_14632_exists = null;

            public IntegerParameter _17_0_5_edc0357_1345510114400_220423_14629_endTime = null;

            public Parameter< LADWP > objectToPass = null;

            public ConstraintExpression constraint360 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114400_746991_14630_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114400_398544_14631_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114400_400850_14632_existsDependency = null;

            public Effect effect361 = null;

            public Parameter effect361Var = null;

            public Effect effect362 = null;

            public Parameter effect362Var = null;

            public Effect effect363 = null;

            public Parameter effect363Var = null;

            public Effect effect364 = null;

            public Parameter effect364Var = null;

            public ElaborationRule elaborationRule365 = null;

            public ElaborationRule elaborationRule366 = null;

            public ElaborationRule elaborationRule367 = null;

            public void init_17_0_5_edc0357_1345510114402_986311_14638Members() {
                try {
                    _17_0_5_edc0357_1345510114400_746991_14630_exists = new BooleanParameter("_17_0_5_edc0357_1345510114400_746991_14630_exists", this);
                    _17_0_5_edc0357_1345510114400_398544_14631_exists = new BooleanParameter("_17_0_5_edc0357_1345510114400_398544_14631_exists", this);
                    _17_0_5_edc0357_1345510114400_400850_14632_exists = new BooleanParameter("_17_0_5_edc0357_1345510114400_400850_14632_exists", this);
                    _17_0_5_edc0357_1345510114400_220423_14629_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114400_220423_14629_endTime", this);
                    objectToPass = new Parameter<LADWP>("objectToPass", null, null, this);
                    constraint360 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114400_220423_14629_endTime)));
                    _17_0_5_edc0357_1345510114400_746991_14630_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114400_746991_14630_exists, new Expression<Boolean>(true));
                    _17_0_5_edc0357_1345510114400_398544_14631_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114400_398544_14631_exists, new Expression<Boolean>(true));
                    _17_0_5_edc0357_1345510114400_400850_14632_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114400_400850_14632_exists, new Expression<Boolean>(true));
                    Object effect361VarV = sig_17_0_5_edc0357_1345510114404_445687_14661;
                    effect361Var = new Parameter("effect361Var", null, null, this);
                    addDependency(effect361Var, new Expression(effect361VarV));
                    effect361 = new EffectFunction(new FunctionCall(effect361Var, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                    Object effect362VarV = sig_17_0_5_edc0357_1345510114405_994113_14663;
                    effect362Var = new Parameter("effect362Var", null, null, this);
                    addDependency(effect362Var, new Expression(effect362VarV));
                    effect362 = new EffectFunction(new FunctionCall(effect362Var, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                    Object effect363VarV = sig_17_0_5_edc0357_1345510114405_752265_14664;
                    effect363Var = new Parameter("effect363Var", null, null, this);
                    addDependency(effect363Var, new Expression(effect363VarV));
                    effect363 = new EffectFunction(new FunctionCall(effect363Var, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                    Object effect364VarV = sig_17_0_5_edc0357_1345510114406_582786_14666;
                    effect364Var = new Parameter("effect364Var", null, null, this);
                    addDependency(effect364Var, new Expression(effect364VarV));
                    effect364 = new EffectFunction(new FunctionCall(effect364Var, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114402_986311_14638Collections() {
                parameters.add(_17_0_5_edc0357_1345510114400_746991_14630_exists);
                parameters.add(_17_0_5_edc0357_1345510114400_398544_14631_exists);
                parameters.add(_17_0_5_edc0357_1345510114400_400850_14632_exists);
                parameters.add(_17_0_5_edc0357_1345510114400_220423_14629_endTime);
                parameters.add(objectToPass);
                constraintExpressions.add(constraint360);
                dependencies.add(_17_0_5_edc0357_1345510114400_746991_14630_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510114400_398544_14631_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510114400_400850_14632_existsDependency);
                Set<Effect> effectsForeffect361Var = new HashSet<Effect>();
                effectsForeffect361Var.add(effect361);
                effects.put((Parameter<?>) effect361Var, effectsForeffect361Var);
                Set<Effect> effectsForeffect362Var = new HashSet<Effect>();
                effectsForeffect362Var.add(effect362);
                effects.put((Parameter<?>) effect362Var, effectsForeffect362Var);
                Set<Effect> effectsForeffect363Var = new HashSet<Effect>();
                effectsForeffect363Var.add(effect363);
                effects.put((Parameter<?>) effect363Var, effectsForeffect363Var);
                Set<Effect> effectsForeffect364Var = new HashSet<Effect>();
                effectsForeffect364Var.add(effect364);
                effects.put((Parameter<?>) effect364Var, effectsForeffect364Var);
            }

            public void init_17_0_5_edc0357_1345510114402_986311_14638Elaborations() {
                Expression<?>[] arguments365 = new Expression<?>[2];
                arguments365[0] = new Expression<Integer>(endTime);
                arguments365[1] = new Expression<LADWP>(objectToPass);
                Expression<Boolean> condition365 = new Expression<Boolean>(_17_0_5_edc0357_1345510114400_398544_14631_exists);
                elaborationRule365 = addElaborationRule(condition365, _17_0_5_edc0357_1345510113599_525430_13831.this, LADWP._17_0_5_edc0357_1345510113599_525430_13831._17_0_5_edc0357_1345510114400_398544_14631.class, "_ReadStructuralFeatureAction_monitor_system", arguments365);
                Expression<?>[] arguments366 = new Expression<?>[2];
                arguments366[0] = new Expression<Integer>(endTime);
                arguments366[1] = new Expression<LADWP>(objectToPass);
                Expression<Boolean> condition366 = new Expression<Boolean>(_17_0_5_edc0357_1345510114400_746991_14630_exists);
                elaborationRule366 = addElaborationRule(condition366, _17_0_5_edc0357_1345510113599_525430_13831.this, LADWP._17_0_5_edc0357_1345510113599_525430_13831._17_0_5_edc0357_1345510114400_746991_14630.class, "_ReadStructuralFeatureAction_monitor_system", arguments366);
                Expression<?>[] arguments367 = new Expression<?>[2];
                arguments367[0] = new Expression<Integer>(endTime);
                arguments367[1] = new Expression<LADWP>(objectToPass);
                Expression<Boolean> condition367 = new Expression<Boolean>(_17_0_5_edc0357_1345510114400_400850_14632_exists);
                elaborationRule367 = addElaborationRule(condition367, _17_0_5_edc0357_1345510113599_525430_13831.this, LADWP._17_0_5_edc0357_1345510113599_525430_13831._17_0_5_edc0357_1345510114400_400850_14632.class, "_ReadStructuralFeatureAction_monitor_system", arguments367);
            }

            public _17_0_5_edc0357_1345510114402_986311_14638() {
                super();
                init_17_0_5_edc0357_1345510114402_986311_14638Members();
                init_17_0_5_edc0357_1345510114402_986311_14638Collections();
                init_17_0_5_edc0357_1345510114402_986311_14638Elaborations();
            }

            public _17_0_5_edc0357_1345510114402_986311_14638(Expression<Integer> _17_0_5_edc0357_1345510114400_220423_14629_endTime, Expression<LADWP> objectToPass) {
                super();
                init_17_0_5_edc0357_1345510114402_986311_14638Members();
                init_17_0_5_edc0357_1345510114402_986311_14638Collections();
                addDependency(this._17_0_5_edc0357_1345510114400_220423_14629_endTime, _17_0_5_edc0357_1345510114400_220423_14629_endTime);
                addDependency(this.objectToPass, objectToPass);
                init_17_0_5_edc0357_1345510114402_986311_14638Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114402_767242_14639 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1348001886585_689962_14324_exists = null;

            public Dependency< Boolean > _17_0_5_edc0357_1348001886585_689962_14324_existsDependency = null;

            public ElaborationRule elaborationRule368 = null;

            public void init_17_0_5_edc0357_1345510114402_767242_14639Members() {
                try {
                    _17_0_5_edc0357_1348001886585_689962_14324_exists = new BooleanParameter("_17_0_5_edc0357_1348001886585_689962_14324_exists", this);
                    _17_0_5_edc0357_1348001886585_689962_14324_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1348001886585_689962_14324_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114402_767242_14639Collections() {
                parameters.add(_17_0_5_edc0357_1348001886585_689962_14324_exists);
                dependencies.add(_17_0_5_edc0357_1348001886585_689962_14324_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510114402_767242_14639Elaborations() {
                Expression<?>[] arguments368 = new Expression<?>[1];
                arguments368[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition368 = new Expression<Boolean>(_17_0_5_edc0357_1348001886585_689962_14324_exists);
                elaborationRule368 = addElaborationRule(condition368, _17_0_5_edc0357_1345510113599_525430_13831.this, LADWP._17_0_5_edc0357_1345510113599_525430_13831._17_0_5_edc0357_1348001886585_689962_14324.class, "_ForkNode_monitor_system", arguments368);
            }

            public _17_0_5_edc0357_1345510114402_767242_14639() {
                super();
                init_17_0_5_edc0357_1345510114402_767242_14639Members();
                init_17_0_5_edc0357_1345510114402_767242_14639Collections();
                init_17_0_5_edc0357_1345510114402_767242_14639Elaborations();
            }

            public _17_0_5_edc0357_1345510114402_767242_14639(Expression<Integer> startTime) {
                super();
                init_17_0_5_edc0357_1345510114402_767242_14639Members();
                init_17_0_5_edc0357_1345510114402_767242_14639Collections();
                removeDependency(this.startTime);
                addDependency(this.startTime, startTime);
                init_17_0_5_edc0357_1345510114402_767242_14639Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114402_549043_14640 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1345510114403_590610_14645_exists = null;

            public IntegerParameter _17_0_5_edc0357_1345510114401_423840_14637_endTime = null;

            public Parameter< LADWP > _17_0_5_edc0357_1345510114911_249955_15296 = null;

            public IntegerParameter _17_0_5_edc0357_1345510114910_287897_15295 = null;

            public ConstraintExpression constraint369 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114403_590610_14645_existsDependency = null;

            public Dependency< LADWP > _17_0_5_edc0357_1345510114911_249955_15296Dependency = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114910_287897_15295Dependency = null;

            public Effect effect370 = null;

            public Parameter effect370Var = null;

            public ElaborationRule elaborationRule371 = null;

            public void init_17_0_5_edc0357_1345510114402_549043_14640Members() {
                try {
                    _17_0_5_edc0357_1345510114403_590610_14645_exists = new BooleanParameter("_17_0_5_edc0357_1345510114403_590610_14645_exists", this);
                    _17_0_5_edc0357_1345510114401_423840_14637_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114401_423840_14637_endTime", this);
                    _17_0_5_edc0357_1345510114911_249955_15296 = new Parameter<LADWP>("_17_0_5_edc0357_1345510114911_249955_15296", null, null, this);
                    _17_0_5_edc0357_1345510114910_287897_15295 = new IntegerParameter("_17_0_5_edc0357_1345510114910_287897_15295", this);
                    constraint369 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114401_423840_14637_endTime)));
                    _17_0_5_edc0357_1345510114403_590610_14645_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114403_590610_14645_exists, new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114407_219573_14673, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })));
                    _17_0_5_edc0357_1345510114911_249955_15296Dependency = new Dependency<LADWP>(_17_0_5_edc0357_1345510114911_249955_15296, new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114405_994113_14663, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1345510114910_287897_15295Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114910_287897_15295, new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114406_67045_14672, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    Object effect370VarV = current_margin__17_0_5_edc0357_1345510113615_810190_13850;
                    effect370Var = new Parameter("effect370Var", null, null, this);
                    addDependency(effect370Var, new Expression(effect370VarV));
                    effect370 = new EffectFunction(new FunctionCall(effect370Var, Utils.getMethodForArgTypes("TimeVaryingMap<Integer>", "generated", "setValue", java.lang.Integer.class, java.lang.Object.class), new Object[] { startTime, _17_0_5_edc0357_1345510114910_287897_15295 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114402_549043_14640Collections() {
                parameters.add(_17_0_5_edc0357_1345510114403_590610_14645_exists);
                parameters.add(_17_0_5_edc0357_1345510114401_423840_14637_endTime);
                parameters.add(_17_0_5_edc0357_1345510114911_249955_15296);
                parameters.add(_17_0_5_edc0357_1345510114910_287897_15295);
                constraintExpressions.add(constraint369);
                dependencies.add(_17_0_5_edc0357_1345510114403_590610_14645_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510114911_249955_15296Dependency);
                dependencies.add(_17_0_5_edc0357_1345510114910_287897_15295Dependency);
                Set<Effect> effectsForeffect370Var = new HashSet<Effect>();
                effectsForeffect370Var.add(effect370);
                effects.put((Parameter<?>) effect370Var, effectsForeffect370Var);
            }

            public void init_17_0_5_edc0357_1345510114402_549043_14640Elaborations() {
                Expression<?>[] arguments371 = new Expression<?>[1];
                arguments371[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition371 = new Expression<Boolean>(_17_0_5_edc0357_1345510114403_590610_14645_exists);
                elaborationRule371 = addElaborationRule(condition371, _17_0_5_edc0357_1345510113599_525430_13831.this, LADWP._17_0_5_edc0357_1345510113599_525430_13831._17_0_5_edc0357_1345510114403_590610_14645.class, "decideShortage_DecisionNode_monitor_system", arguments371);
            }

            public _17_0_5_edc0357_1345510114402_549043_14640() {
                super();
                init_17_0_5_edc0357_1345510114402_549043_14640Members();
                init_17_0_5_edc0357_1345510114402_549043_14640Collections();
                init_17_0_5_edc0357_1345510114402_549043_14640Elaborations();
            }

            public _17_0_5_edc0357_1345510114402_549043_14640(Expression<Integer> _17_0_5_edc0357_1345510114401_423840_14637_endTime) {
                super();
                init_17_0_5_edc0357_1345510114402_549043_14640Members();
                init_17_0_5_edc0357_1345510114402_549043_14640Collections();
                addDependency(this._17_0_5_edc0357_1345510114401_423840_14637_endTime, _17_0_5_edc0357_1345510114401_423840_14637_endTime);
                init_17_0_5_edc0357_1345510114402_549043_14640Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114402_483196_14641 extends DurativeEvent {

            public Parameter< LADWP > _17_0_5_edc0357_1345510114912_644333_15298 = null;

            public IntegerParameter _17_0_5_edc0357_1345510114403_226098_14648_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1345510114912_558392_15297 = null;

            public BooleanParameter _17_0_5_edc0357_1346106020869_685207_15351_exists = null;

            public ConstraintExpression constraint372 = null;

            public Dependency< LADWP > _17_0_5_edc0357_1345510114912_644333_15298Dependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114912_558392_15297Dependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346106020869_685207_15351_existsDependency = null;

            public Effect effect373 = null;

            public Parameter effect373Var = null;

            public ElaborationRule elaborationRule374 = null;

            public void init_17_0_5_edc0357_1345510114402_483196_14641Members() {
                try {
                    _17_0_5_edc0357_1345510114912_644333_15298 = new Parameter<LADWP>("_17_0_5_edc0357_1345510114912_644333_15298", null, null, this);
                    _17_0_5_edc0357_1345510114403_226098_14648_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114403_226098_14648_endTime", this);
                    _17_0_5_edc0357_1345510114912_558392_15297 = new BooleanParameter("_17_0_5_edc0357_1345510114912_558392_15297", this);
                    _17_0_5_edc0357_1346106020869_685207_15351_exists = new BooleanParameter("_17_0_5_edc0357_1346106020869_685207_15351_exists", this);
                    constraint372 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114403_226098_14648_endTime)));
                    _17_0_5_edc0357_1345510114912_644333_15298Dependency = new Dependency<LADWP>(_17_0_5_edc0357_1345510114912_644333_15298, new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114405_752265_14664, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1345510114912_558392_15297Dependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114912_558392_15297, new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114406_563398_14668, Utils.getMethodForArgTypes("ObjectFlow<Boolean>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1346106020869_685207_15351_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346106020869_685207_15351_exists, new Expression<Boolean>(true));
                    Object effect373VarV = demandResponse__17_0_5_edc0357_1345510113615_857599_13851;
                    effect373Var = new Parameter("effect373Var", null, null, this);
                    addDependency(effect373Var, new Expression(effect373VarV));
                    effect373 = new EffectFunction(new FunctionCall(effect373Var, Utils.getMethodForArgTypes("TimeVaryingMap<Boolean>", "generated", "setValue", java.lang.Integer.class, java.lang.Object.class), new Object[] { startTime, _17_0_5_edc0357_1345510114912_558392_15297 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114402_483196_14641Collections() {
                parameters.add(_17_0_5_edc0357_1345510114912_644333_15298);
                parameters.add(_17_0_5_edc0357_1345510114403_226098_14648_endTime);
                parameters.add(_17_0_5_edc0357_1345510114912_558392_15297);
                parameters.add(_17_0_5_edc0357_1346106020869_685207_15351_exists);
                constraintExpressions.add(constraint372);
                dependencies.add(_17_0_5_edc0357_1345510114912_644333_15298Dependency);
                dependencies.add(_17_0_5_edc0357_1345510114912_558392_15297Dependency);
                dependencies.add(_17_0_5_edc0357_1346106020869_685207_15351_existsDependency);
                Set<Effect> effectsForeffect373Var = new HashSet<Effect>();
                effectsForeffect373Var.add(effect373);
                effects.put((Parameter<?>) effect373Var, effectsForeffect373Var);
            }

            public void init_17_0_5_edc0357_1345510114402_483196_14641Elaborations() {
                Expression<?>[] arguments374 = new Expression<?>[1];
                arguments374[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition374 = new Expression<Boolean>(_17_0_5_edc0357_1346106020869_685207_15351_exists);
                elaborationRule374 = addElaborationRule(condition374, _17_0_5_edc0357_1345510113599_525430_13831.this, LADWP._17_0_5_edc0357_1345510113599_525430_13831._17_0_5_edc0357_1346106020869_685207_15351.class, "finalmerge_MergeNode_monitor_system", arguments374);
            }

            public _17_0_5_edc0357_1345510114402_483196_14641() {
                super();
                init_17_0_5_edc0357_1345510114402_483196_14641Members();
                init_17_0_5_edc0357_1345510114402_483196_14641Collections();
                init_17_0_5_edc0357_1345510114402_483196_14641Elaborations();
            }

            public _17_0_5_edc0357_1345510114402_483196_14641(Expression<Integer> _17_0_5_edc0357_1345510114403_226098_14648_endTime) {
                super();
                init_17_0_5_edc0357_1345510114402_483196_14641Members();
                init_17_0_5_edc0357_1345510114402_483196_14641Collections();
                addDependency(this._17_0_5_edc0357_1345510114403_226098_14648_endTime, _17_0_5_edc0357_1345510114403_226098_14648_endTime);
                init_17_0_5_edc0357_1345510114402_483196_14641Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114402_958025_14642 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1345510114403_20048_14644_exists = null;

            public BooleanParameter _17_0_5_edc0357_1345510114913_204417_15300 = null;

            public IntegerParameter _17_0_5_edc0357_1348001886585_689962_14324_endTime = null;

            public ConstraintExpression constraint375 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114403_20048_14644_existsDependency = null;

            public ElaborationRule elaborationRule376 = null;

            public void init_17_0_5_edc0357_1345510114402_958025_14642Members() {
                try {
                    _17_0_5_edc0357_1345510114403_20048_14644_exists = new BooleanParameter("_17_0_5_edc0357_1345510114403_20048_14644_exists", this);
                    _17_0_5_edc0357_1345510114913_204417_15300 = new BooleanParameter("_17_0_5_edc0357_1345510114913_204417_15300", this);
                    _17_0_5_edc0357_1348001886585_689962_14324_endTime = new IntegerParameter("_17_0_5_edc0357_1348001886585_689962_14324_endTime", this);
                    constraint375 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1348001886585_689962_14324_endTime)));
                    _17_0_5_edc0357_1345510114403_20048_14644_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114403_20048_14644_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114402_958025_14642Collections() {
                parameters.add(_17_0_5_edc0357_1345510114403_20048_14644_exists);
                parameters.add(_17_0_5_edc0357_1345510114913_204417_15300);
                parameters.add(_17_0_5_edc0357_1348001886585_689962_14324_endTime);
                constraintExpressions.add(constraint375);
                dependencies.add(_17_0_5_edc0357_1345510114403_20048_14644_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510114402_958025_14642Elaborations() {
                Expression<?>[] arguments376 = new Expression<?>[2];
                arguments376[0] = new Expression<Integer>(endTime);
                arguments376[1] = new Expression<Boolean>(_17_0_5_edc0357_1345510114913_204417_15300);
                Expression<Boolean> condition376 = new Expression<Boolean>(_17_0_5_edc0357_1345510114403_20048_14644_exists);
                elaborationRule376 = addElaborationRule(condition376, _17_0_5_edc0357_1345510113599_525430_13831.this, LADWP._17_0_5_edc0357_1345510113599_525430_13831._17_0_5_edc0357_1345510114403_20048_14644.class, "val_spec_fork_ForkNode_monitor_system", arguments376);
            }

            public _17_0_5_edc0357_1345510114402_958025_14642() {
                super();
                init_17_0_5_edc0357_1345510114402_958025_14642Members();
                init_17_0_5_edc0357_1345510114402_958025_14642Collections();
                init_17_0_5_edc0357_1345510114402_958025_14642Elaborations();
            }

            public _17_0_5_edc0357_1345510114402_958025_14642(Expression<Integer> _17_0_5_edc0357_1348001886585_689962_14324_endTime) {
                super();
                init_17_0_5_edc0357_1345510114402_958025_14642Members();
                init_17_0_5_edc0357_1345510114402_958025_14642Collections();
                addDependency(this._17_0_5_edc0357_1348001886585_689962_14324_endTime, _17_0_5_edc0357_1348001886585_689962_14324_endTime);
                init_17_0_5_edc0357_1345510114402_958025_14642Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114402_392534_14643 extends DurativeEvent {

            public Parameter< LADWP > _17_0_5_edc0357_1345510114915_875209_15302 = null;

            public IntegerParameter _17_0_5_edc0357_1345510114403_590610_14645_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1346106020869_685207_15351_exists = null;

            public BooleanParameter _17_0_5_edc0357_1345510114914_734899_15301 = null;

            public ConstraintExpression constraint377 = null;

            public Dependency< LADWP > _17_0_5_edc0357_1345510114915_875209_15302Dependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346106020869_685207_15351_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114914_734899_15301Dependency = null;

            public Effect effect378 = null;

            public Parameter effect378Var = null;

            public ElaborationRule elaborationRule379 = null;

            public void init_17_0_5_edc0357_1345510114402_392534_14643Members() {
                try {
                    _17_0_5_edc0357_1345510114915_875209_15302 = new Parameter<LADWP>("_17_0_5_edc0357_1345510114915_875209_15302", null, null, this);
                    _17_0_5_edc0357_1345510114403_590610_14645_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114403_590610_14645_endTime", this);
                    _17_0_5_edc0357_1346106020869_685207_15351_exists = new BooleanParameter("_17_0_5_edc0357_1346106020869_685207_15351_exists", this);
                    _17_0_5_edc0357_1345510114914_734899_15301 = new BooleanParameter("_17_0_5_edc0357_1345510114914_734899_15301", this);
                    constraint377 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114403_590610_14645_endTime)));
                    _17_0_5_edc0357_1345510114915_875209_15302Dependency = new Dependency<LADWP>(_17_0_5_edc0357_1345510114915_875209_15302, new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114406_582786_14666, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1346106020869_685207_15351_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346106020869_685207_15351_exists, new Expression<Boolean>(true));
                    _17_0_5_edc0357_1345510114914_734899_15301Dependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114914_734899_15301, new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114406_650884_14669, Utils.getMethodForArgTypes("ObjectFlow<Boolean>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    Object effect378VarV = shortage__17_0_5_edc0357_1345510113616_127059_13852;
                    effect378Var = new Parameter("effect378Var", null, null, this);
                    addDependency(effect378Var, new Expression(effect378VarV));
                    effect378 = new EffectFunction(new FunctionCall(effect378Var, Utils.getMethodForArgTypes("TimeVaryingMap<Boolean>", "generated", "setValue", java.lang.Integer.class, java.lang.Object.class), new Object[] { startTime, _17_0_5_edc0357_1345510114914_734899_15301 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114402_392534_14643Collections() {
                parameters.add(_17_0_5_edc0357_1345510114915_875209_15302);
                parameters.add(_17_0_5_edc0357_1345510114403_590610_14645_endTime);
                parameters.add(_17_0_5_edc0357_1346106020869_685207_15351_exists);
                parameters.add(_17_0_5_edc0357_1345510114914_734899_15301);
                constraintExpressions.add(constraint377);
                dependencies.add(_17_0_5_edc0357_1345510114915_875209_15302Dependency);
                dependencies.add(_17_0_5_edc0357_1346106020869_685207_15351_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510114914_734899_15301Dependency);
                Set<Effect> effectsForeffect378Var = new HashSet<Effect>();
                effectsForeffect378Var.add(effect378);
                effects.put((Parameter<?>) effect378Var, effectsForeffect378Var);
            }

            public void init_17_0_5_edc0357_1345510114402_392534_14643Elaborations() {
                Expression<?>[] arguments379 = new Expression<?>[1];
                arguments379[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition379 = new Expression<Boolean>(_17_0_5_edc0357_1346106020869_685207_15351_exists);
                elaborationRule379 = addElaborationRule(condition379, _17_0_5_edc0357_1345510113599_525430_13831.this, LADWP._17_0_5_edc0357_1345510113599_525430_13831._17_0_5_edc0357_1346106020869_685207_15351.class, "finalmerge_MergeNode_monitor_system", arguments379);
            }

            public _17_0_5_edc0357_1345510114402_392534_14643() {
                super();
                init_17_0_5_edc0357_1345510114402_392534_14643Members();
                init_17_0_5_edc0357_1345510114402_392534_14643Collections();
                init_17_0_5_edc0357_1345510114402_392534_14643Elaborations();
            }

            public _17_0_5_edc0357_1345510114402_392534_14643(Expression<Integer> _17_0_5_edc0357_1345510114403_590610_14645_endTime) {
                super();
                init_17_0_5_edc0357_1345510114402_392534_14643Members();
                init_17_0_5_edc0357_1345510114402_392534_14643Collections();
                addDependency(this._17_0_5_edc0357_1345510114403_590610_14645_endTime, _17_0_5_edc0357_1345510114403_590610_14645_endTime);
                init_17_0_5_edc0357_1345510114402_392534_14643Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114403_20048_14644 extends DurativeEvent {

            public BooleanParameter objectToPass = null;

            public IntegerParameter _17_0_5_edc0357_1345510114402_958025_14642_endTime = null;

            public ConstraintExpression constraint380 = null;

            public Effect effect381 = null;

            public Parameter effect381Var = null;

            public Effect effect382 = null;

            public Parameter effect382Var = null;

            public void init_17_0_5_edc0357_1345510114403_20048_14644Members() {
                try {
                    objectToPass = new BooleanParameter("objectToPass", this);
                    _17_0_5_edc0357_1345510114402_958025_14642_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114402_958025_14642_endTime", this);
                    constraint380 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114402_958025_14642_endTime)));
                    Object effect381VarV = sig_17_0_5_edc0357_1345510114406_563398_14668;
                    effect381Var = new Parameter("effect381Var", null, null, this);
                    addDependency(effect381Var, new Expression(effect381VarV));
                    effect381 = new EffectFunction(new FunctionCall(effect381Var, Utils.getMethodForArgTypes("ObjectFlow<Boolean>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                    Object effect382VarV = sig_17_0_5_edc0357_1345510114406_650884_14669;
                    effect382Var = new Parameter("effect382Var", null, null, this);
                    addDependency(effect382Var, new Expression(effect382VarV));
                    effect382 = new EffectFunction(new FunctionCall(effect382Var, Utils.getMethodForArgTypes("ObjectFlow<Boolean>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114403_20048_14644Collections() {
                parameters.add(objectToPass);
                parameters.add(_17_0_5_edc0357_1345510114402_958025_14642_endTime);
                constraintExpressions.add(constraint380);
                Set<Effect> effectsForeffect381Var = new HashSet<Effect>();
                effectsForeffect381Var.add(effect381);
                effects.put((Parameter<?>) effect381Var, effectsForeffect381Var);
                Set<Effect> effectsForeffect382Var = new HashSet<Effect>();
                effectsForeffect382Var.add(effect382);
                effects.put((Parameter<?>) effect382Var, effectsForeffect382Var);
            }

            public void init_17_0_5_edc0357_1345510114403_20048_14644Elaborations() {
            }

            public _17_0_5_edc0357_1345510114403_20048_14644() {
                super();
                init_17_0_5_edc0357_1345510114403_20048_14644Members();
                init_17_0_5_edc0357_1345510114403_20048_14644Collections();
                init_17_0_5_edc0357_1345510114403_20048_14644Elaborations();
            }

            public _17_0_5_edc0357_1345510114403_20048_14644(Expression<Integer> _17_0_5_edc0357_1345510114402_958025_14642_endTime, Expression<Boolean> objectToPass) {
                super();
                init_17_0_5_edc0357_1345510114403_20048_14644Members();
                init_17_0_5_edc0357_1345510114403_20048_14644Collections();
                addDependency(this._17_0_5_edc0357_1345510114402_958025_14642_endTime, _17_0_5_edc0357_1345510114402_958025_14642_endTime);
                addDependency(this.objectToPass, objectToPass);
                init_17_0_5_edc0357_1345510114403_20048_14644Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114403_590610_14645 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1345510114407_219573_14673 = null;

            public BooleanParameter _17_0_5_edc0357_1345510114401_569392_14636_exists = null;

            public BooleanParameter _17_0_5_edc0357_1345510114402_392534_14643_exists = null;

            public IntegerParameter _17_0_5_edc0357_1345510114402_549043_14640_endTime = null;

            public ConstraintExpression constraint383 = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114407_219573_14673Dependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114401_569392_14636_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114402_392534_14643_existsDependency = null;

            public ElaborationRule elaborationRule384 = null;

            public ElaborationRule elaborationRule385 = null;

            public void init_17_0_5_edc0357_1345510114403_590610_14645Members() {
                try {
                    _17_0_5_edc0357_1345510114407_219573_14673 = new IntegerParameter("_17_0_5_edc0357_1345510114407_219573_14673", this);
                    _17_0_5_edc0357_1345510114401_569392_14636_exists = new BooleanParameter("_17_0_5_edc0357_1345510114401_569392_14636_exists", this);
                    _17_0_5_edc0357_1345510114402_392534_14643_exists = new BooleanParameter("_17_0_5_edc0357_1345510114402_392534_14643_exists", this);
                    _17_0_5_edc0357_1345510114402_549043_14640_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114402_549043_14640_endTime", this);
                    constraint383 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114402_549043_14640_endTime)));
                    _17_0_5_edc0357_1345510114407_219573_14673Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114407_219573_14673, new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114407_219573_14673, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1345510114401_569392_14636_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114401_569392_14636_exists, new Functions.And(new Functions.And(new Functions.GreaterEquals(new Expression<Integer>(_17_0_5_edc0357_1345510114407_219573_14673), new Expression<Integer>(0)), new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114404_34398_14651, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) }))), new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114404_348430_14652, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) }))));
                    _17_0_5_edc0357_1345510114402_392534_14643_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114402_392534_14643_exists, new Functions.And(new Functions.And(new Functions.Less(new Expression<Integer>(_17_0_5_edc0357_1345510114407_219573_14673), new Expression<Integer>(0)), new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114406_582786_14666, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) }))), new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114406_650884_14669, Utils.getMethodForArgTypes("ObjectFlow<Boolean>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) }))));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114403_590610_14645Collections() {
                parameters.add(_17_0_5_edc0357_1345510114407_219573_14673);
                parameters.add(_17_0_5_edc0357_1345510114401_569392_14636_exists);
                parameters.add(_17_0_5_edc0357_1345510114402_392534_14643_exists);
                parameters.add(_17_0_5_edc0357_1345510114402_549043_14640_endTime);
                constraintExpressions.add(constraint383);
                dependencies.add(_17_0_5_edc0357_1345510114407_219573_14673Dependency);
                dependencies.add(_17_0_5_edc0357_1345510114401_569392_14636_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510114402_392534_14643_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510114403_590610_14645Elaborations() {
                Expression<?>[] arguments384 = new Expression<?>[1];
                arguments384[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition384 = new Expression<Boolean>(_17_0_5_edc0357_1345510114402_392534_14643_exists);
                elaborationRule384 = addElaborationRule(condition384, _17_0_5_edc0357_1345510113599_525430_13831.this, LADWP._17_0_5_edc0357_1345510113599_525430_13831._17_0_5_edc0357_1345510114402_392534_14643.class, "_AddStructuralFeatureValueAction_monitor_system", arguments384);
                Expression<?>[] arguments385 = new Expression<?>[1];
                arguments385[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition385 = new Expression<Boolean>(_17_0_5_edc0357_1345510114401_569392_14636_exists);
                elaborationRule385 = addElaborationRule(condition385, _17_0_5_edc0357_1345510113599_525430_13831.this, LADWP._17_0_5_edc0357_1345510113599_525430_13831._17_0_5_edc0357_1345510114401_569392_14636.class, "_CallBehaviorAction_monitor_system", arguments385);
            }

            public _17_0_5_edc0357_1345510114403_590610_14645() {
                super();
                init_17_0_5_edc0357_1345510114403_590610_14645Members();
                init_17_0_5_edc0357_1345510114403_590610_14645Collections();
                init_17_0_5_edc0357_1345510114403_590610_14645Elaborations();
            }

            public _17_0_5_edc0357_1345510114403_590610_14645(Expression<Integer> _17_0_5_edc0357_1345510114402_549043_14640_endTime) {
                super();
                init_17_0_5_edc0357_1345510114403_590610_14645Members();
                init_17_0_5_edc0357_1345510114403_590610_14645Collections();
                addDependency(this._17_0_5_edc0357_1345510114402_549043_14640_endTime, _17_0_5_edc0357_1345510114402_549043_14640_endTime);
                init_17_0_5_edc0357_1345510114403_590610_14645Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114403_773368_14646 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1345510114401_423840_14637_endTime = null;

            public IntegerParameter objectToPass = null;

            public ConstraintExpression constraint386 = null;

            public Effect effect387 = null;

            public Parameter effect387Var = null;

            public Effect effect388 = null;

            public Parameter effect388Var = null;

            public void init_17_0_5_edc0357_1345510114403_773368_14646Members() {
                try {
                    _17_0_5_edc0357_1345510114401_423840_14637_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114401_423840_14637_endTime", this);
                    objectToPass = new IntegerParameter("objectToPass", this);
                    constraint386 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114401_423840_14637_endTime)));
                    Object effect387VarV = sig_17_0_5_edc0357_1345510114406_67045_14672;
                    effect387Var = new Parameter("effect387Var", null, null, this);
                    addDependency(effect387Var, new Expression(effect387VarV));
                    effect387 = new EffectFunction(new FunctionCall(effect387Var, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                    Object effect388VarV = sig_17_0_5_edc0357_1345510114407_219573_14673;
                    effect388Var = new Parameter("effect388Var", null, null, this);
                    addDependency(effect388Var, new Expression(effect388VarV));
                    effect388 = new EffectFunction(new FunctionCall(effect388Var, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114403_773368_14646Collections() {
                parameters.add(_17_0_5_edc0357_1345510114401_423840_14637_endTime);
                parameters.add(objectToPass);
                constraintExpressions.add(constraint386);
                Set<Effect> effectsForeffect387Var = new HashSet<Effect>();
                effectsForeffect387Var.add(effect387);
                effects.put((Parameter<?>) effect387Var, effectsForeffect387Var);
                Set<Effect> effectsForeffect388Var = new HashSet<Effect>();
                effectsForeffect388Var.add(effect388);
                effects.put((Parameter<?>) effect388Var, effectsForeffect388Var);
            }

            public void init_17_0_5_edc0357_1345510114403_773368_14646Elaborations() {
            }

            public _17_0_5_edc0357_1345510114403_773368_14646() {
                super();
                init_17_0_5_edc0357_1345510114403_773368_14646Members();
                init_17_0_5_edc0357_1345510114403_773368_14646Collections();
                init_17_0_5_edc0357_1345510114403_773368_14646Elaborations();
            }

            public _17_0_5_edc0357_1345510114403_773368_14646(Expression<Integer> _17_0_5_edc0357_1345510114401_423840_14637_endTime, Expression<Integer> objectToPass) {
                super();
                init_17_0_5_edc0357_1345510114403_773368_14646Members();
                init_17_0_5_edc0357_1345510114403_773368_14646Collections();
                addDependency(this._17_0_5_edc0357_1345510114401_423840_14637_endTime, _17_0_5_edc0357_1345510114401_423840_14637_endTime);
                addDependency(this.objectToPass, objectToPass);
                init_17_0_5_edc0357_1345510114403_773368_14646Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114403_960787_14647 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1345510114401_569392_14636_endTime = null;

            public IntegerParameter objectToPass = null;

            public ConstraintExpression constraint389 = null;

            public Effect effect390 = null;

            public Parameter effect390Var = null;

            public Effect effect391 = null;

            public Parameter effect391Var = null;

            public void init_17_0_5_edc0357_1345510114403_960787_14647Members() {
                try {
                    _17_0_5_edc0357_1345510114401_569392_14636_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114401_569392_14636_endTime", this);
                    objectToPass = new IntegerParameter("objectToPass", this);
                    constraint389 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114401_569392_14636_endTime)));
                    Object effect390VarV = sig_17_0_5_edc0357_1345510114408_335204_14678;
                    effect390Var = new Parameter("effect390Var", null, null, this);
                    addDependency(effect390Var, new Expression(effect390VarV));
                    effect390 = new EffectFunction(new FunctionCall(effect390Var, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                    Object effect391VarV = sig_17_0_5_edc0357_1345510114408_563101_14679;
                    effect391Var = new Parameter("effect391Var", null, null, this);
                    addDependency(effect391Var, new Expression(effect391VarV));
                    effect391 = new EffectFunction(new FunctionCall(effect391Var, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114403_960787_14647Collections() {
                parameters.add(_17_0_5_edc0357_1345510114401_569392_14636_endTime);
                parameters.add(objectToPass);
                constraintExpressions.add(constraint389);
                Set<Effect> effectsForeffect390Var = new HashSet<Effect>();
                effectsForeffect390Var.add(effect390);
                effects.put((Parameter<?>) effect390Var, effectsForeffect390Var);
                Set<Effect> effectsForeffect391Var = new HashSet<Effect>();
                effectsForeffect391Var.add(effect391);
                effects.put((Parameter<?>) effect391Var, effectsForeffect391Var);
            }

            public void init_17_0_5_edc0357_1345510114403_960787_14647Elaborations() {
            }

            public _17_0_5_edc0357_1345510114403_960787_14647() {
                super();
                init_17_0_5_edc0357_1345510114403_960787_14647Members();
                init_17_0_5_edc0357_1345510114403_960787_14647Collections();
                init_17_0_5_edc0357_1345510114403_960787_14647Elaborations();
            }

            public _17_0_5_edc0357_1345510114403_960787_14647(Expression<Integer> _17_0_5_edc0357_1345510114401_569392_14636_endTime, Expression<Integer> objectToPass) {
                super();
                init_17_0_5_edc0357_1345510114403_960787_14647Members();
                init_17_0_5_edc0357_1345510114403_960787_14647Collections();
                addDependency(this._17_0_5_edc0357_1345510114401_569392_14636_endTime, _17_0_5_edc0357_1345510114401_569392_14636_endTime);
                addDependency(this.objectToPass, objectToPass);
                init_17_0_5_edc0357_1345510114403_960787_14647Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114403_226098_14648 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1345510114401_676911_14635_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1345510114402_483196_14641_exists = null;

            public IntegerParameter _17_0_5_edc0357_1345510114408_563101_14679 = null;

            public BooleanParameter _17_0_5_edc0357_1346106020869_685207_15351_exists = null;

            public ConstraintExpression constraint392 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114402_483196_14641_existsDependency = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114408_563101_14679Dependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346106020869_685207_15351_existsDependency = null;

            public ElaborationRule elaborationRule393 = null;

            public ElaborationRule elaborationRule394 = null;

            public void init_17_0_5_edc0357_1345510114403_226098_14648Members() {
                try {
                    _17_0_5_edc0357_1345510114401_676911_14635_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114401_676911_14635_endTime", this);
                    _17_0_5_edc0357_1345510114402_483196_14641_exists = new BooleanParameter("_17_0_5_edc0357_1345510114402_483196_14641_exists", this);
                    _17_0_5_edc0357_1345510114408_563101_14679 = new IntegerParameter("_17_0_5_edc0357_1345510114408_563101_14679", this);
                    _17_0_5_edc0357_1346106020869_685207_15351_exists = new BooleanParameter("_17_0_5_edc0357_1346106020869_685207_15351_exists", this);
                    constraint392 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114401_676911_14635_endTime)));
                    _17_0_5_edc0357_1345510114402_483196_14641_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114402_483196_14641_exists, new Functions.And(new Functions.And(new Functions.Less(new Expression<Integer>(_17_0_5_edc0357_1345510114408_563101_14679), new Expression<Integer>(0)), new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114405_752265_14664, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) }))), new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114406_563398_14668, Utils.getMethodForArgTypes("ObjectFlow<Boolean>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) }))));
                    _17_0_5_edc0357_1345510114408_563101_14679Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114408_563101_14679, new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114408_563101_14679, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1346106020869_685207_15351_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346106020869_685207_15351_exists, new Functions.GreaterEquals(new Expression<Integer>(_17_0_5_edc0357_1345510114408_563101_14679), new Expression<Integer>(0)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114403_226098_14648Collections() {
                parameters.add(_17_0_5_edc0357_1345510114401_676911_14635_endTime);
                parameters.add(_17_0_5_edc0357_1345510114402_483196_14641_exists);
                parameters.add(_17_0_5_edc0357_1345510114408_563101_14679);
                parameters.add(_17_0_5_edc0357_1346106020869_685207_15351_exists);
                constraintExpressions.add(constraint392);
                dependencies.add(_17_0_5_edc0357_1345510114402_483196_14641_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510114408_563101_14679Dependency);
                dependencies.add(_17_0_5_edc0357_1346106020869_685207_15351_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510114403_226098_14648Elaborations() {
                Expression<?>[] arguments393 = new Expression<?>[1];
                arguments393[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition393 = new Expression<Boolean>(_17_0_5_edc0357_1345510114402_483196_14641_exists);
                elaborationRule393 = addElaborationRule(condition393, _17_0_5_edc0357_1345510113599_525430_13831.this, LADWP._17_0_5_edc0357_1345510113599_525430_13831._17_0_5_edc0357_1345510114402_483196_14641.class, "_AddStructuralFeatureValueAction_monitor_system", arguments393);
                Expression<?>[] arguments394 = new Expression<?>[1];
                arguments394[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition394 = new Expression<Boolean>(_17_0_5_edc0357_1346106020869_685207_15351_exists);
                elaborationRule394 = addElaborationRule(condition394, _17_0_5_edc0357_1345510113599_525430_13831.this, LADWP._17_0_5_edc0357_1345510113599_525430_13831._17_0_5_edc0357_1346106020869_685207_15351.class, "finalmerge_MergeNode_monitor_system", arguments394);
            }

            public _17_0_5_edc0357_1345510114403_226098_14648() {
                super();
                init_17_0_5_edc0357_1345510114403_226098_14648Members();
                init_17_0_5_edc0357_1345510114403_226098_14648Collections();
                init_17_0_5_edc0357_1345510114403_226098_14648Elaborations();
            }

            public _17_0_5_edc0357_1345510114403_226098_14648(Expression<Integer> _17_0_5_edc0357_1345510114401_676911_14635_endTime) {
                super();
                init_17_0_5_edc0357_1345510114403_226098_14648Members();
                init_17_0_5_edc0357_1345510114403_226098_14648Collections();
                addDependency(this._17_0_5_edc0357_1345510114401_676911_14635_endTime, _17_0_5_edc0357_1345510114401_676911_14635_endTime);
                init_17_0_5_edc0357_1345510114403_226098_14648Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1346106020869_685207_15351 extends DurativeEvent {

            public IntegerParameter invoker_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1345510114400_500051_14633_exists = null;

            public ConstraintExpression constraint395 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114400_500051_14633_existsDependency = null;

            public ElaborationRule elaborationRule396 = null;

            public void init_17_0_5_edc0357_1346106020869_685207_15351Members() {
                try {
                    invoker_endTime = new IntegerParameter("invoker_endTime", this);
                    _17_0_5_edc0357_1345510114400_500051_14633_exists = new BooleanParameter("_17_0_5_edc0357_1345510114400_500051_14633_exists", this);
                    constraint395 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(invoker_endTime)));
                    _17_0_5_edc0357_1345510114400_500051_14633_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114400_500051_14633_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346106020869_685207_15351Collections() {
                parameters.add(invoker_endTime);
                parameters.add(_17_0_5_edc0357_1345510114400_500051_14633_exists);
                constraintExpressions.add(constraint395);
                dependencies.add(_17_0_5_edc0357_1345510114400_500051_14633_existsDependency);
            }

            public void init_17_0_5_edc0357_1346106020869_685207_15351Elaborations() {
                Expression<?>[] arguments396 = new Expression<?>[1];
                arguments396[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition396 = new Expression<Boolean>(_17_0_5_edc0357_1345510114400_500051_14633_exists);
                elaborationRule396 = addElaborationRule(condition396, _17_0_5_edc0357_1345510113599_525430_13831.this, LADWP._17_0_5_edc0357_1345510113599_525430_13831._17_0_5_edc0357_1345510114400_500051_14633.class, "fin_ActivityFinalNode_monitor_system", arguments396);
            }

            public _17_0_5_edc0357_1346106020869_685207_15351() {
                super();
                init_17_0_5_edc0357_1346106020869_685207_15351Members();
                init_17_0_5_edc0357_1346106020869_685207_15351Collections();
                init_17_0_5_edc0357_1346106020869_685207_15351Elaborations();
            }

            public _17_0_5_edc0357_1346106020869_685207_15351(Expression<Integer> invoker_endTime) {
                super();
                init_17_0_5_edc0357_1346106020869_685207_15351Members();
                init_17_0_5_edc0357_1346106020869_685207_15351Collections();
                addDependency(this.invoker_endTime, invoker_endTime);
                init_17_0_5_edc0357_1346106020869_685207_15351Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1348001886585_689962_14324 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1345510114400_220423_14629_exists = null;

            public IntegerParameter _17_0_5_edc0357_1345510114402_767242_14639_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1345510114402_958025_14642_exists = null;

            public ConstraintExpression constraint397 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114400_220423_14629_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114402_958025_14642_existsDependency = null;

            public ElaborationRule elaborationRule398 = null;

            public ElaborationRule elaborationRule399 = null;

            public void init_17_0_5_edc0357_1348001886585_689962_14324Members() {
                try {
                    _17_0_5_edc0357_1345510114400_220423_14629_exists = new BooleanParameter("_17_0_5_edc0357_1345510114400_220423_14629_exists", this);
                    _17_0_5_edc0357_1345510114402_767242_14639_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114402_767242_14639_endTime", this);
                    _17_0_5_edc0357_1345510114402_958025_14642_exists = new BooleanParameter("_17_0_5_edc0357_1345510114402_958025_14642_exists", this);
                    constraint397 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114402_767242_14639_endTime)));
                    _17_0_5_edc0357_1345510114400_220423_14629_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114400_220423_14629_exists, new Expression<Boolean>(true));
                    _17_0_5_edc0357_1345510114402_958025_14642_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114402_958025_14642_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1348001886585_689962_14324Collections() {
                parameters.add(_17_0_5_edc0357_1345510114400_220423_14629_exists);
                parameters.add(_17_0_5_edc0357_1345510114402_767242_14639_endTime);
                parameters.add(_17_0_5_edc0357_1345510114402_958025_14642_exists);
                constraintExpressions.add(constraint397);
                dependencies.add(_17_0_5_edc0357_1345510114400_220423_14629_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510114402_958025_14642_existsDependency);
            }

            public void init_17_0_5_edc0357_1348001886585_689962_14324Elaborations() {
                Expression<?>[] arguments398 = new Expression<?>[1];
                arguments398[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition398 = new Expression<Boolean>(_17_0_5_edc0357_1345510114400_220423_14629_exists);
                elaborationRule398 = addElaborationRule(condition398, _17_0_5_edc0357_1345510113599_525430_13831.this, LADWP._17_0_5_edc0357_1345510113599_525430_13831._17_0_5_edc0357_1345510114400_220423_14629.class, "_ReadSelfAction_monitor_system", arguments398);
                Expression<?>[] arguments399 = new Expression<?>[1];
                arguments399[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition399 = new Expression<Boolean>(_17_0_5_edc0357_1345510114402_958025_14642_exists);
                elaborationRule399 = addElaborationRule(condition399, _17_0_5_edc0357_1345510113599_525430_13831.this, LADWP._17_0_5_edc0357_1345510113599_525430_13831._17_0_5_edc0357_1345510114402_958025_14642.class, "_ValueSpecificationAction_monitor_system", arguments399);
            }

            public _17_0_5_edc0357_1348001886585_689962_14324() {
                super();
                init_17_0_5_edc0357_1348001886585_689962_14324Members();
                init_17_0_5_edc0357_1348001886585_689962_14324Collections();
                init_17_0_5_edc0357_1348001886585_689962_14324Elaborations();
            }

            public _17_0_5_edc0357_1348001886585_689962_14324(Expression<Integer> _17_0_5_edc0357_1345510114402_767242_14639_endTime) {
                super();
                init_17_0_5_edc0357_1348001886585_689962_14324Members();
                init_17_0_5_edc0357_1348001886585_689962_14324Collections();
                addDependency(this._17_0_5_edc0357_1345510114402_767242_14639_endTime, _17_0_5_edc0357_1345510114402_767242_14639_endTime);
                init_17_0_5_edc0357_1348001886585_689962_14324Elaborations();
                fixTimeDependencies();
            }
        }

        public _17_0_5_edc0357_1345510113599_525430_13831(Expression<Integer> endTime) {
            super();
            init_17_0_5_edc0357_1345510113599_525430_13831Members();
            init_17_0_5_edc0357_1345510113599_525430_13831Collections();
            removeDependency(this.endTime);
            addDependency(this.endTime, endTime);
            init_17_0_5_edc0357_1345510113599_525430_13831Elaborations();
            fixTimeDependencies();
        }
    }

    public class _17_0_5_edc0357_1345510113600_989123_13832 extends DurativeEvent {

        public Parameter< ObjectFlow<Integer> > sig_17_0_5_edc0357_1345510114499_464852_14726 = null;

        public IntegerParameter invoke_time = null;

        public ElaborationRule elaborationRule400 = null;

        public void init_17_0_5_edc0357_1345510113600_989123_13832Members() {
            try {
                sig_17_0_5_edc0357_1345510114499_464852_14726 = new Parameter<ObjectFlow<Integer>>("sig_17_0_5_edc0357_1345510114499_464852_14726", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114499_464852_14726"), this);
                invoke_time = new IntegerParameter("invoke_time", this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void init_17_0_5_edc0357_1345510113600_989123_13832Collections() {
            parameters.add(sig_17_0_5_edc0357_1345510114499_464852_14726);
            parameters.add(invoke_time);
        }

        public void init_17_0_5_edc0357_1345510113600_989123_13832Elaborations() {
            Expression<?>[] arguments400 = new Expression<?>[1];
            arguments400[0] = new Expression<Integer>(invoke_time);
            Expression<Boolean> condition400 = new Expression<Boolean>(true);
            elaborationRule400 = addElaborationRule(condition400, _17_0_5_edc0357_1345510113600_989123_13832.this, LADWP._17_0_5_edc0357_1345510113600_989123_13832._17_0_5_edc0357_1345510114498_570114_14719.class, "_InitialNode_demand_response", arguments400);
        }

        public _17_0_5_edc0357_1345510113600_989123_13832() {
            super();
            init_17_0_5_edc0357_1345510113600_989123_13832Members();
            init_17_0_5_edc0357_1345510113600_989123_13832Collections();
            init_17_0_5_edc0357_1345510113600_989123_13832Elaborations();
        }

        public class _17_0_5_edc0357_1345510114498_546705_14715 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1345510114498_54401_14718_exists = null;

            public Parameter< LADWP > _17_0_5_edc0357_1345510114923_47069_15342 = null;

            public IntegerParameter _17_0_5_edc0357_1345510114498_570114_14719_endTime = null;

            public ConstraintExpression constraint401 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114498_54401_14718_existsDependency = null;

            public ElaborationRule elaborationRule402 = null;

            public void init_17_0_5_edc0357_1345510114498_546705_14715Members() {
                try {
                    _17_0_5_edc0357_1345510114498_54401_14718_exists = new BooleanParameter("_17_0_5_edc0357_1345510114498_54401_14718_exists", this);
                    _17_0_5_edc0357_1345510114923_47069_15342 = new Parameter<LADWP>("_17_0_5_edc0357_1345510114923_47069_15342", null, LADWP.this, this);
                    _17_0_5_edc0357_1345510114498_570114_14719_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114498_570114_14719_endTime", this);
                    constraint401 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114498_570114_14719_endTime)));
                    _17_0_5_edc0357_1345510114498_54401_14718_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114498_54401_14718_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114498_546705_14715Collections() {
                parameters.add(_17_0_5_edc0357_1345510114498_54401_14718_exists);
                parameters.add(_17_0_5_edc0357_1345510114923_47069_15342);
                parameters.add(_17_0_5_edc0357_1345510114498_570114_14719_endTime);
                constraintExpressions.add(constraint401);
                dependencies.add(_17_0_5_edc0357_1345510114498_54401_14718_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510114498_546705_14715Elaborations() {
                Expression<?>[] arguments402 = new Expression<?>[2];
                arguments402[0] = new Expression<Integer>(endTime);
                arguments402[1] = new Expression<LADWP>(_17_0_5_edc0357_1345510114923_47069_15342);
                Expression<Boolean> condition402 = new Expression<Boolean>(_17_0_5_edc0357_1345510114498_54401_14718_exists);
                elaborationRule402 = addElaborationRule(condition402, _17_0_5_edc0357_1345510113600_989123_13832.this, LADWP._17_0_5_edc0357_1345510113600_989123_13832._17_0_5_edc0357_1345510114498_54401_14718.class, "_ForkNode_demand_response", arguments402);
            }

            public _17_0_5_edc0357_1345510114498_546705_14715() {
                super();
                init_17_0_5_edc0357_1345510114498_546705_14715Members();
                init_17_0_5_edc0357_1345510114498_546705_14715Collections();
                init_17_0_5_edc0357_1345510114498_546705_14715Elaborations();
            }

            public _17_0_5_edc0357_1345510114498_546705_14715(Expression<Integer> _17_0_5_edc0357_1345510114498_570114_14719_endTime) {
                super();
                init_17_0_5_edc0357_1345510114498_546705_14715Members();
                init_17_0_5_edc0357_1345510114498_546705_14715Collections();
                addDependency(this._17_0_5_edc0357_1345510114498_570114_14719_endTime, _17_0_5_edc0357_1345510114498_570114_14719_endTime);
                init_17_0_5_edc0357_1345510114498_546705_14715Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114498_752743_14716 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1345510114498_54401_14718_endTime = null;

            public Parameter< LADWP > _17_0_5_edc0357_1345510114924_63685_15344 = null;

            public IntegerParameter _17_0_5_edc0357_1345510114924_922147_15343 = null;

            public ConstraintExpression constraint403 = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114924_922147_15343Dependency = null;

            public Effect effect404 = null;

            public Parameter effect404Var = null;

            public void init_17_0_5_edc0357_1345510114498_752743_14716Members() {
                try {
                    _17_0_5_edc0357_1345510114498_54401_14718_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114498_54401_14718_endTime", this);
                    _17_0_5_edc0357_1345510114924_63685_15344 = new Parameter<LADWP>("_17_0_5_edc0357_1345510114924_63685_15344", null, null, this);
                    _17_0_5_edc0357_1345510114924_922147_15343 = new IntegerParameter("_17_0_5_edc0357_1345510114924_922147_15343", this);
                    constraint403 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114498_54401_14718_endTime)));
                    _17_0_5_edc0357_1345510114924_922147_15343Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114924_922147_15343, new Expression(new FunctionCall(null, Utils.getMethodForArgTypes("LADWP._17_0_5_edc0357_1345510113600_989123_13832._17_0_5_edc0357_1345510114498_752743_14716", "generated", "getValue"), new Object[] { new Expression<Integer>(startTime) }, new FunctionCall(null, Parameter.class, "getValue", (Object[]) null, new FunctionCall(_17_0_5_edc0357_1345510114924_63685_15344, Parameter.class, "getMember", new Object[] { "reported_generation__17_0_5_edc0357_1345510113612_851613_13846" })))));
                    Object effect404VarV = sig_17_0_5_edc0357_1345510114499_464852_14726;
                    effect404Var = new Parameter("effect404Var", null, null, this);
                    addDependency(effect404Var, new Expression(effect404VarV));
                    effect404 = new EffectFunction(new FunctionCall(effect404Var, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { _17_0_5_edc0357_1345510114924_922147_15343, startTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114498_752743_14716Collections() {
                parameters.add(_17_0_5_edc0357_1345510114498_54401_14718_endTime);
                parameters.add(_17_0_5_edc0357_1345510114924_63685_15344);
                parameters.add(_17_0_5_edc0357_1345510114924_922147_15343);
                constraintExpressions.add(constraint403);
                dependencies.add(_17_0_5_edc0357_1345510114924_922147_15343Dependency);
                Set<Effect> effectsForeffect404Var = new HashSet<Effect>();
                effectsForeffect404Var.add(effect404);
                effects.put((Parameter<?>) effect404Var, effectsForeffect404Var);
            }

            public void init_17_0_5_edc0357_1345510114498_752743_14716Elaborations() {
            }

            public _17_0_5_edc0357_1345510114498_752743_14716() {
                super();
                init_17_0_5_edc0357_1345510114498_752743_14716Members();
                init_17_0_5_edc0357_1345510114498_752743_14716Collections();
                init_17_0_5_edc0357_1345510114498_752743_14716Elaborations();
            }

            public _17_0_5_edc0357_1345510114498_752743_14716(Expression<Integer> _17_0_5_edc0357_1345510114498_54401_14718_endTime, Expression<LADWP> _17_0_5_edc0357_1345510114924_63685_15344) {
                super();
                init_17_0_5_edc0357_1345510114498_752743_14716Members();
                init_17_0_5_edc0357_1345510114498_752743_14716Collections();
                addDependency(this._17_0_5_edc0357_1345510114498_54401_14718_endTime, _17_0_5_edc0357_1345510114498_54401_14718_endTime);
                addDependency(this._17_0_5_edc0357_1345510114924_63685_15344, _17_0_5_edc0357_1345510114924_63685_15344);
                init_17_0_5_edc0357_1345510114498_752743_14716Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114498_717969_14717 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1345510114926_775601_15346 = null;

            public IntegerParameter _17_0_5_edc0357_1345510114498_54401_14718_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1345510114498_243498_14720_exists = null;

            public Parameter< LADWP > _17_0_5_edc0357_1345510114925_118349_15345 = null;

            public ConstraintExpression constraint405 = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114926_775601_15346Dependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114498_243498_14720_existsDependency = null;

            public Effect effect406 = null;

            public Parameter effect406Var = null;

            public ElaborationRule elaborationRule407 = null;

            public void init_17_0_5_edc0357_1345510114498_717969_14717Members() {
                try {
                    _17_0_5_edc0357_1345510114926_775601_15346 = new IntegerParameter("_17_0_5_edc0357_1345510114926_775601_15346", this);
                    _17_0_5_edc0357_1345510114498_54401_14718_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114498_54401_14718_endTime", this);
                    _17_0_5_edc0357_1345510114498_243498_14720_exists = new BooleanParameter("_17_0_5_edc0357_1345510114498_243498_14720_exists", this);
                    _17_0_5_edc0357_1345510114925_118349_15345 = new Parameter<LADWP>("_17_0_5_edc0357_1345510114925_118349_15345", null, null, this);
                    constraint405 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114498_54401_14718_endTime)));
                    _17_0_5_edc0357_1345510114926_775601_15346Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114926_775601_15346, new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114499_464852_14726, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1345510114498_243498_14720_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114498_243498_14720_exists, new Expression<Boolean>(true));
                    Object effect406VarV = new FunctionCall(null, Parameter.class, "getValue", (Object[]) null, new FunctionCall(x, Parameter.class, "getMember", new Object[] { "ss_17_0_5_edc0357_1345510113605_306486_13837_dr_request" }));
                    effect406Var = new Parameter("effect406Var", null, null, this);
                    addDependency(effect406Var, new Expression(effect406VarV));
                    effect406 = new EffectFunction(new FunctionCall(null, Utils.getMethodForArgTypes("LADWP._17_0_5_edc0357_1345510113600_989123_13832._17_0_5_edc0357_1345510114498_717969_14717", "generated", "send"), new Object[] { x.getValue().new Signaldr_request(endTime.getValue(), _17_0_5_edc0357_1345510114926_775601_15346.getValue()), endTime }, effect406Var));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114498_717969_14717Collections() {
                parameters.add(_17_0_5_edc0357_1345510114926_775601_15346);
                parameters.add(_17_0_5_edc0357_1345510114498_54401_14718_endTime);
                parameters.add(_17_0_5_edc0357_1345510114498_243498_14720_exists);
                parameters.add(_17_0_5_edc0357_1345510114925_118349_15345);
                constraintExpressions.add(constraint405);
                dependencies.add(_17_0_5_edc0357_1345510114926_775601_15346Dependency);
                dependencies.add(_17_0_5_edc0357_1345510114498_243498_14720_existsDependency);
                Set<Effect> effectsForeffect406Var = new HashSet<Effect>();
                effectsForeffect406Var.add(effect406);
                effects.put((Parameter<?>) effect406Var, effectsForeffect406Var);
            }

            public void init_17_0_5_edc0357_1345510114498_717969_14717Elaborations() {
                Expression<?>[] arguments407 = new Expression<?>[1];
                arguments407[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition407 = new Expression<Boolean>(_17_0_5_edc0357_1345510114498_243498_14720_exists);
                elaborationRule407 = addElaborationRule(condition407, _17_0_5_edc0357_1345510113600_989123_13832.this, LADWP._17_0_5_edc0357_1345510113600_989123_13832._17_0_5_edc0357_1345510114498_243498_14720.class, "_ActivityFinalNode_demand_response", arguments407);
            }

            public _17_0_5_edc0357_1345510114498_717969_14717() {
                super();
                init_17_0_5_edc0357_1345510114498_717969_14717Members();
                init_17_0_5_edc0357_1345510114498_717969_14717Collections();
                init_17_0_5_edc0357_1345510114498_717969_14717Elaborations();
            }

            public _17_0_5_edc0357_1345510114498_717969_14717(Expression<Integer> _17_0_5_edc0357_1345510114498_54401_14718_endTime, Expression<LADWP> _17_0_5_edc0357_1345510114925_118349_15345) {
                super();
                init_17_0_5_edc0357_1345510114498_717969_14717Members();
                init_17_0_5_edc0357_1345510114498_717969_14717Collections();
                addDependency(this._17_0_5_edc0357_1345510114498_54401_14718_endTime, _17_0_5_edc0357_1345510114498_54401_14718_endTime);
                addDependency(this._17_0_5_edc0357_1345510114925_118349_15345, _17_0_5_edc0357_1345510114925_118349_15345);
                init_17_0_5_edc0357_1345510114498_717969_14717Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114498_54401_14718 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1345510114498_717969_14717_exists = null;

            public IntegerParameter _17_0_5_edc0357_1345510114498_546705_14715_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1345510114498_752743_14716_exists = null;

            public Parameter< LADWP > objectToPass = null;

            public ConstraintExpression constraint408 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114498_717969_14717_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114498_752743_14716_existsDependency = null;

            public ElaborationRule elaborationRule409 = null;

            public ElaborationRule elaborationRule410 = null;

            public void init_17_0_5_edc0357_1345510114498_54401_14718Members() {
                try {
                    _17_0_5_edc0357_1345510114498_717969_14717_exists = new BooleanParameter("_17_0_5_edc0357_1345510114498_717969_14717_exists", this);
                    _17_0_5_edc0357_1345510114498_546705_14715_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114498_546705_14715_endTime", this);
                    _17_0_5_edc0357_1345510114498_752743_14716_exists = new BooleanParameter("_17_0_5_edc0357_1345510114498_752743_14716_exists", this);
                    objectToPass = new Parameter<LADWP>("objectToPass", null, null, this);
                    constraint408 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114498_546705_14715_endTime)));
                    _17_0_5_edc0357_1345510114498_717969_14717_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114498_717969_14717_exists, new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114499_464852_14726, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })));
                    _17_0_5_edc0357_1345510114498_752743_14716_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114498_752743_14716_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114498_54401_14718Collections() {
                parameters.add(_17_0_5_edc0357_1345510114498_717969_14717_exists);
                parameters.add(_17_0_5_edc0357_1345510114498_546705_14715_endTime);
                parameters.add(_17_0_5_edc0357_1345510114498_752743_14716_exists);
                parameters.add(objectToPass);
                constraintExpressions.add(constraint408);
                dependencies.add(_17_0_5_edc0357_1345510114498_717969_14717_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510114498_752743_14716_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510114498_54401_14718Elaborations() {
                Expression<?>[] arguments409 = new Expression<?>[2];
                arguments409[0] = new Expression<Integer>(endTime);
                arguments409[1] = new Expression<LADWP>(objectToPass);
                Expression<Boolean> condition409 = new Expression<Boolean>(_17_0_5_edc0357_1345510114498_717969_14717_exists);
                elaborationRule409 = addElaborationRule(condition409, _17_0_5_edc0357_1345510113600_989123_13832.this, LADWP._17_0_5_edc0357_1345510113600_989123_13832._17_0_5_edc0357_1345510114498_717969_14717.class, "_SendSignalAction_demand_response", arguments409);
                Expression<?>[] arguments410 = new Expression<?>[2];
                arguments410[0] = new Expression<Integer>(endTime);
                arguments410[1] = new Expression<LADWP>(objectToPass);
                Expression<Boolean> condition410 = new Expression<Boolean>(_17_0_5_edc0357_1345510114498_752743_14716_exists);
                elaborationRule410 = addElaborationRule(condition410, _17_0_5_edc0357_1345510113600_989123_13832.this, LADWP._17_0_5_edc0357_1345510113600_989123_13832._17_0_5_edc0357_1345510114498_752743_14716.class, "_ReadStructuralFeatureAction_demand_response", arguments410);
            }

            public _17_0_5_edc0357_1345510114498_54401_14718() {
                super();
                init_17_0_5_edc0357_1345510114498_54401_14718Members();
                init_17_0_5_edc0357_1345510114498_54401_14718Collections();
                init_17_0_5_edc0357_1345510114498_54401_14718Elaborations();
            }

            public _17_0_5_edc0357_1345510114498_54401_14718(Expression<Integer> _17_0_5_edc0357_1345510114498_546705_14715_endTime, Expression<LADWP> objectToPass) {
                super();
                init_17_0_5_edc0357_1345510114498_54401_14718Members();
                init_17_0_5_edc0357_1345510114498_54401_14718Collections();
                addDependency(this._17_0_5_edc0357_1345510114498_546705_14715_endTime, _17_0_5_edc0357_1345510114498_546705_14715_endTime);
                addDependency(this.objectToPass, objectToPass);
                init_17_0_5_edc0357_1345510114498_54401_14718Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114498_570114_14719 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1345510114498_546705_14715_exists = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114498_546705_14715_existsDependency = null;

            public ElaborationRule elaborationRule411 = null;

            public void init_17_0_5_edc0357_1345510114498_570114_14719Members() {
                try {
                    _17_0_5_edc0357_1345510114498_546705_14715_exists = new BooleanParameter("_17_0_5_edc0357_1345510114498_546705_14715_exists", this);
                    _17_0_5_edc0357_1345510114498_546705_14715_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114498_546705_14715_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114498_570114_14719Collections() {
                parameters.add(_17_0_5_edc0357_1345510114498_546705_14715_exists);
                dependencies.add(_17_0_5_edc0357_1345510114498_546705_14715_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510114498_570114_14719Elaborations() {
                Expression<?>[] arguments411 = new Expression<?>[1];
                arguments411[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition411 = new Expression<Boolean>(_17_0_5_edc0357_1345510114498_546705_14715_exists);
                elaborationRule411 = addElaborationRule(condition411, _17_0_5_edc0357_1345510113600_989123_13832.this, LADWP._17_0_5_edc0357_1345510113600_989123_13832._17_0_5_edc0357_1345510114498_546705_14715.class, "_ReadSelfAction_demand_response", arguments411);
            }

            public _17_0_5_edc0357_1345510114498_570114_14719() {
                super();
                init_17_0_5_edc0357_1345510114498_570114_14719Members();
                init_17_0_5_edc0357_1345510114498_570114_14719Collections();
                init_17_0_5_edc0357_1345510114498_570114_14719Elaborations();
            }

            public _17_0_5_edc0357_1345510114498_570114_14719(Expression<Integer> startTime) {
                super();
                init_17_0_5_edc0357_1345510114498_570114_14719Members();
                init_17_0_5_edc0357_1345510114498_570114_14719Collections();
                removeDependency(this.startTime);
                addDependency(this.startTime, startTime);
                init_17_0_5_edc0357_1345510114498_570114_14719Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114498_243498_14720 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1345510114498_717969_14717_endTime = null;

            public ConstraintExpression constraint412 = null;

            public void init_17_0_5_edc0357_1345510114498_243498_14720Members() {
                try {
                    _17_0_5_edc0357_1345510114498_717969_14717_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114498_717969_14717_endTime", this);
                    constraint412 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114498_717969_14717_endTime)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114498_243498_14720Collections() {
                parameters.add(_17_0_5_edc0357_1345510114498_717969_14717_endTime);
                constraintExpressions.add(constraint412);
            }

            public void init_17_0_5_edc0357_1345510114498_243498_14720Elaborations() {
            }

            public _17_0_5_edc0357_1345510114498_243498_14720() {
                super();
                init_17_0_5_edc0357_1345510114498_243498_14720Members();
                init_17_0_5_edc0357_1345510114498_243498_14720Collections();
                init_17_0_5_edc0357_1345510114498_243498_14720Elaborations();
            }

            public _17_0_5_edc0357_1345510114498_243498_14720(Expression<Integer> _17_0_5_edc0357_1345510114498_717969_14717_endTime) {
                super();
                init_17_0_5_edc0357_1345510114498_243498_14720Members();
                init_17_0_5_edc0357_1345510114498_243498_14720Collections();
                addDependency(this._17_0_5_edc0357_1345510114498_717969_14717_endTime, _17_0_5_edc0357_1345510114498_717969_14717_endTime);
                init_17_0_5_edc0357_1345510114498_243498_14720Elaborations();
                fixTimeDependencies();
            }
        }

        public _17_0_5_edc0357_1345510113600_989123_13832(Expression<Integer> endTime) {
            super();
            init_17_0_5_edc0357_1345510113600_989123_13832Members();
            init_17_0_5_edc0357_1345510113600_989123_13832Collections();
            removeDependency(this.endTime);
            addDependency(this.endTime, endTime);
            init_17_0_5_edc0357_1345510113600_989123_13832Elaborations();
            fixTimeDependencies();
        }
    }

    public class _17_0_5_edc0357_1345510113601_377571_13833 extends DurativeEvent {

        public IntegerParameter _17_0_5_edc0357_1348001555541_398388_14280 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_5_edc0357_1348001582336_313784_14297 = null;

        public IntegerParameter invoke_time = null;

        public ElaborationRule elaborationRule413 = null;

        public ElaborationRule elaborationRule414 = null;

        public void init_17_0_5_edc0357_1345510113601_377571_13833Members() {
            try {
                _17_0_5_edc0357_1348001555541_398388_14280 = new IntegerParameter("_17_0_5_edc0357_1348001555541_398388_14280", this);
                sig_17_0_5_edc0357_1348001582336_313784_14297 = new Parameter<ObjectFlow<Integer>>("sig_17_0_5_edc0357_1348001582336_313784_14297", null, new ObjectFlow("sig_17_0_5_edc0357_1348001582336_313784_14297"), this);
                invoke_time = new IntegerParameter("invoke_time", this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void init_17_0_5_edc0357_1345510113601_377571_13833Collections() {
            parameters.add(_17_0_5_edc0357_1348001555541_398388_14280);
            parameters.add(sig_17_0_5_edc0357_1348001582336_313784_14297);
            parameters.add(invoke_time);
        }

        public void init_17_0_5_edc0357_1345510113601_377571_13833Elaborations() {
            Expression<?>[] arguments413 = new Expression<?>[1];
            arguments413[0] = new Expression<Integer>(invoke_time);
            Expression<Boolean> condition413 = new Expression<Boolean>(true);
            elaborationRule413 = addElaborationRule(condition413, _17_0_5_edc0357_1345510113601_377571_13833.this, LADWP._17_0_5_edc0357_1345510113601_377571_13833._17_0_5_edc0357_1345510114536_703738_14767.class, "_InitialNode_updateExpectedLoad", arguments413);
            Expression<?>[] arguments414 = new Expression<?>[2];
            arguments414[0] = new Expression<Integer>(invoke_time);
            arguments414[1] = new Expression<Integer>(_17_0_5_edc0357_1348001555541_398388_14280);
            Expression<Boolean> condition414 = new Expression<Boolean>(true);
            elaborationRule414 = addElaborationRule(condition414, _17_0_5_edc0357_1345510113601_377571_13833.this, LADWP._17_0_5_edc0357_1345510113601_377571_13833._17_0_5_edc0357_1348001565573_115349_14282.class, "expectedLoad_ActivityParameterNode_updateExpectedLoad", arguments414);
        }

        public _17_0_5_edc0357_1345510113601_377571_13833() {
            super();
            init_17_0_5_edc0357_1345510113601_377571_13833Members();
            init_17_0_5_edc0357_1345510113601_377571_13833Collections();
            init_17_0_5_edc0357_1345510113601_377571_13833Elaborations();
        }

        public class _17_0_5_edc0357_1345510114535_568767_14764 extends DurativeEvent {

            public Parameter< LADWP > _17_0_5_edc0357_1345510114931_675927_15361 = null;

            public IntegerParameter _17_0_5_edc0357_1345510114536_703738_14767_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1345510114535_282655_14765_exists = null;

            public ConstraintExpression constraint415 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114535_282655_14765_existsDependency = null;

            public ElaborationRule elaborationRule416 = null;

            public void init_17_0_5_edc0357_1345510114535_568767_14764Members() {
                try {
                    _17_0_5_edc0357_1345510114931_675927_15361 = new Parameter<LADWP>("_17_0_5_edc0357_1345510114931_675927_15361", null, LADWP.this, this);
                    _17_0_5_edc0357_1345510114536_703738_14767_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114536_703738_14767_endTime", this);
                    _17_0_5_edc0357_1345510114535_282655_14765_exists = new BooleanParameter("_17_0_5_edc0357_1345510114535_282655_14765_exists", this);
                    constraint415 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114536_703738_14767_endTime)));
                    _17_0_5_edc0357_1345510114535_282655_14765_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114535_282655_14765_exists, new Expression(new FunctionCall(sig_17_0_5_edc0357_1348001582336_313784_14297, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114535_568767_14764Collections() {
                parameters.add(_17_0_5_edc0357_1345510114931_675927_15361);
                parameters.add(_17_0_5_edc0357_1345510114536_703738_14767_endTime);
                parameters.add(_17_0_5_edc0357_1345510114535_282655_14765_exists);
                constraintExpressions.add(constraint415);
                dependencies.add(_17_0_5_edc0357_1345510114535_282655_14765_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510114535_568767_14764Elaborations() {
                Expression<?>[] arguments416 = new Expression<?>[2];
                arguments416[0] = new Expression<Integer>(endTime);
                arguments416[1] = new Expression<LADWP>(_17_0_5_edc0357_1345510114931_675927_15361);
                Expression<Boolean> condition416 = new Expression<Boolean>(_17_0_5_edc0357_1345510114535_282655_14765_exists);
                elaborationRule416 = addElaborationRule(condition416, _17_0_5_edc0357_1345510113601_377571_13833.this, LADWP._17_0_5_edc0357_1345510113601_377571_13833._17_0_5_edc0357_1345510114535_282655_14765.class, "_AddStructuralFeatureValueAction_updateExpectedLoad", arguments416);
            }

            public _17_0_5_edc0357_1345510114535_568767_14764() {
                super();
                init_17_0_5_edc0357_1345510114535_568767_14764Members();
                init_17_0_5_edc0357_1345510114535_568767_14764Collections();
                init_17_0_5_edc0357_1345510114535_568767_14764Elaborations();
            }

            public _17_0_5_edc0357_1345510114535_568767_14764(Expression<Integer> _17_0_5_edc0357_1345510114536_703738_14767_endTime) {
                super();
                init_17_0_5_edc0357_1345510114535_568767_14764Members();
                init_17_0_5_edc0357_1345510114535_568767_14764Collections();
                addDependency(this._17_0_5_edc0357_1345510114536_703738_14767_endTime, _17_0_5_edc0357_1345510114536_703738_14767_endTime);
                init_17_0_5_edc0357_1345510114535_568767_14764Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114535_282655_14765 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1345510114535_568767_14764_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1345510114536_186015_14768_exists = null;

            public IntegerParameter _17_0_5_edc0357_1345510114932_192686_15362 = null;

            public Parameter< LADWP > _17_0_5_edc0357_1345510114933_118299_15363 = null;

            public ConstraintExpression constraint417 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114536_186015_14768_existsDependency = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114932_192686_15362Dependency = null;

            public Effect effect418 = null;

            public Parameter effect418Var = null;

            public ElaborationRule elaborationRule419 = null;

            public void init_17_0_5_edc0357_1345510114535_282655_14765Members() {
                try {
                    _17_0_5_edc0357_1345510114535_568767_14764_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114535_568767_14764_endTime", this);
                    _17_0_5_edc0357_1345510114536_186015_14768_exists = new BooleanParameter("_17_0_5_edc0357_1345510114536_186015_14768_exists", this);
                    _17_0_5_edc0357_1345510114932_192686_15362 = new IntegerParameter("_17_0_5_edc0357_1345510114932_192686_15362", this);
                    _17_0_5_edc0357_1345510114933_118299_15363 = new Parameter<LADWP>("_17_0_5_edc0357_1345510114933_118299_15363", null, null, this);
                    constraint417 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114535_568767_14764_endTime)));
                    _17_0_5_edc0357_1345510114536_186015_14768_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114536_186015_14768_exists, new Expression<Boolean>(true));
                    _17_0_5_edc0357_1345510114932_192686_15362Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114932_192686_15362, new Expression(new FunctionCall(sig_17_0_5_edc0357_1348001582336_313784_14297, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    Object effect418VarV = expected_load__17_0_5_edc0357_1345510113611_416109_13845;
                    effect418Var = new Parameter("effect418Var", null, null, this);
                    addDependency(effect418Var, new Expression(effect418VarV));
                    effect418 = new EffectFunction(new FunctionCall(effect418Var, Utils.getMethodForArgTypes("TimeVaryingMap<Integer>", "generated", "setValue", java.lang.Integer.class, java.lang.Object.class), new Object[] { startTime, _17_0_5_edc0357_1345510114932_192686_15362 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114535_282655_14765Collections() {
                parameters.add(_17_0_5_edc0357_1345510114535_568767_14764_endTime);
                parameters.add(_17_0_5_edc0357_1345510114536_186015_14768_exists);
                parameters.add(_17_0_5_edc0357_1345510114932_192686_15362);
                parameters.add(_17_0_5_edc0357_1345510114933_118299_15363);
                constraintExpressions.add(constraint417);
                dependencies.add(_17_0_5_edc0357_1345510114536_186015_14768_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510114932_192686_15362Dependency);
                Set<Effect> effectsForeffect418Var = new HashSet<Effect>();
                effectsForeffect418Var.add(effect418);
                effects.put((Parameter<?>) effect418Var, effectsForeffect418Var);
            }

            public void init_17_0_5_edc0357_1345510114535_282655_14765Elaborations() {
                Expression<?>[] arguments419 = new Expression<?>[1];
                arguments419[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition419 = new Expression<Boolean>(_17_0_5_edc0357_1345510114536_186015_14768_exists);
                elaborationRule419 = addElaborationRule(condition419, _17_0_5_edc0357_1345510113601_377571_13833.this, LADWP._17_0_5_edc0357_1345510113601_377571_13833._17_0_5_edc0357_1345510114536_186015_14768.class, "_ActivityFinalNode_updateExpectedLoad", arguments419);
            }

            public _17_0_5_edc0357_1345510114535_282655_14765() {
                super();
                init_17_0_5_edc0357_1345510114535_282655_14765Members();
                init_17_0_5_edc0357_1345510114535_282655_14765Collections();
                init_17_0_5_edc0357_1345510114535_282655_14765Elaborations();
            }

            public _17_0_5_edc0357_1345510114535_282655_14765(Expression<Integer> _17_0_5_edc0357_1345510114535_568767_14764_endTime, Expression<LADWP> _17_0_5_edc0357_1345510114933_118299_15363) {
                super();
                init_17_0_5_edc0357_1345510114535_282655_14765Members();
                init_17_0_5_edc0357_1345510114535_282655_14765Collections();
                addDependency(this._17_0_5_edc0357_1345510114535_568767_14764_endTime, _17_0_5_edc0357_1345510114535_568767_14764_endTime);
                addDependency(this._17_0_5_edc0357_1345510114933_118299_15363, _17_0_5_edc0357_1345510114933_118299_15363);
                init_17_0_5_edc0357_1345510114535_282655_14765Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114536_703738_14767 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1345510114535_568767_14764_exists = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114535_568767_14764_existsDependency = null;

            public ElaborationRule elaborationRule420 = null;

            public void init_17_0_5_edc0357_1345510114536_703738_14767Members() {
                try {
                    _17_0_5_edc0357_1345510114535_568767_14764_exists = new BooleanParameter("_17_0_5_edc0357_1345510114535_568767_14764_exists", this);
                    _17_0_5_edc0357_1345510114535_568767_14764_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114535_568767_14764_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114536_703738_14767Collections() {
                parameters.add(_17_0_5_edc0357_1345510114535_568767_14764_exists);
                dependencies.add(_17_0_5_edc0357_1345510114535_568767_14764_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510114536_703738_14767Elaborations() {
                Expression<?>[] arguments420 = new Expression<?>[1];
                arguments420[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition420 = new Expression<Boolean>(_17_0_5_edc0357_1345510114535_568767_14764_exists);
                elaborationRule420 = addElaborationRule(condition420, _17_0_5_edc0357_1345510113601_377571_13833.this, LADWP._17_0_5_edc0357_1345510113601_377571_13833._17_0_5_edc0357_1345510114535_568767_14764.class, "_ReadSelfAction_updateExpectedLoad", arguments420);
            }

            public _17_0_5_edc0357_1345510114536_703738_14767() {
                super();
                init_17_0_5_edc0357_1345510114536_703738_14767Members();
                init_17_0_5_edc0357_1345510114536_703738_14767Collections();
                init_17_0_5_edc0357_1345510114536_703738_14767Elaborations();
            }

            public _17_0_5_edc0357_1345510114536_703738_14767(Expression<Integer> startTime) {
                super();
                init_17_0_5_edc0357_1345510114536_703738_14767Members();
                init_17_0_5_edc0357_1345510114536_703738_14767Collections();
                removeDependency(this.startTime);
                addDependency(this.startTime, startTime);
                init_17_0_5_edc0357_1345510114536_703738_14767Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114536_186015_14768 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1345510114535_282655_14765_endTime = null;

            public ConstraintExpression constraint421 = null;

            public void init_17_0_5_edc0357_1345510114536_186015_14768Members() {
                try {
                    _17_0_5_edc0357_1345510114535_282655_14765_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114535_282655_14765_endTime", this);
                    constraint421 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114535_282655_14765_endTime)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114536_186015_14768Collections() {
                parameters.add(_17_0_5_edc0357_1345510114535_282655_14765_endTime);
                constraintExpressions.add(constraint421);
            }

            public void init_17_0_5_edc0357_1345510114536_186015_14768Elaborations() {
            }

            public _17_0_5_edc0357_1345510114536_186015_14768() {
                super();
                init_17_0_5_edc0357_1345510114536_186015_14768Members();
                init_17_0_5_edc0357_1345510114536_186015_14768Collections();
                init_17_0_5_edc0357_1345510114536_186015_14768Elaborations();
            }

            public _17_0_5_edc0357_1345510114536_186015_14768(Expression<Integer> _17_0_5_edc0357_1345510114535_282655_14765_endTime) {
                super();
                init_17_0_5_edc0357_1345510114536_186015_14768Members();
                init_17_0_5_edc0357_1345510114536_186015_14768Collections();
                addDependency(this._17_0_5_edc0357_1345510114535_282655_14765_endTime, _17_0_5_edc0357_1345510114535_282655_14765_endTime);
                init_17_0_5_edc0357_1345510114536_186015_14768Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1348001565573_115349_14282 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1348001555541_398388_14280 = null;

            public Effect effect422 = null;

            public Parameter effect422Var = null;

            public void init_17_0_5_edc0357_1348001565573_115349_14282Members() {
                try {
                    _17_0_5_edc0357_1348001555541_398388_14280 = new IntegerParameter("_17_0_5_edc0357_1348001555541_398388_14280", this);
                    Object effect422VarV = sig_17_0_5_edc0357_1348001582336_313784_14297;
                    effect422Var = new Parameter("effect422Var", null, null, this);
                    addDependency(effect422Var, new Expression(effect422VarV));
                    effect422 = new EffectFunction(new FunctionCall(effect422Var, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { _17_0_5_edc0357_1348001555541_398388_14280, startTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1348001565573_115349_14282Collections() {
                parameters.add(_17_0_5_edc0357_1348001555541_398388_14280);
                Set<Effect> effectsForeffect422Var = new HashSet<Effect>();
                effectsForeffect422Var.add(effect422);
                effects.put((Parameter<?>) effect422Var, effectsForeffect422Var);
            }

            public void init_17_0_5_edc0357_1348001565573_115349_14282Elaborations() {
            }

            public _17_0_5_edc0357_1348001565573_115349_14282() {
                super();
                init_17_0_5_edc0357_1348001565573_115349_14282Members();
                init_17_0_5_edc0357_1348001565573_115349_14282Collections();
                init_17_0_5_edc0357_1348001565573_115349_14282Elaborations();
            }

            public _17_0_5_edc0357_1348001565573_115349_14282(Expression<Integer> startTime, Expression<Integer> _17_0_5_edc0357_1348001555541_398388_14280) {
                super();
                init_17_0_5_edc0357_1348001565573_115349_14282Members();
                init_17_0_5_edc0357_1348001565573_115349_14282Collections();
                removeDependency(this.startTime);
                addDependency(this.startTime, startTime);
                addDependency(this._17_0_5_edc0357_1348001555541_398388_14280, _17_0_5_edc0357_1348001555541_398388_14280);
                init_17_0_5_edc0357_1348001565573_115349_14282Elaborations();
                fixTimeDependencies();
            }
        }

        public _17_0_5_edc0357_1345510113601_377571_13833(Expression<Integer> endTime, Expression<Integer> _17_0_5_edc0357_1348001555541_398388_14280) {
            super();
            init_17_0_5_edc0357_1345510113601_377571_13833Members();
            init_17_0_5_edc0357_1345510113601_377571_13833Collections();
            removeDependency(this.endTime);
            addDependency(this.endTime, endTime);
            addDependency(this._17_0_5_edc0357_1348001555541_398388_14280, _17_0_5_edc0357_1348001555541_398388_14280);
            init_17_0_5_edc0357_1345510113601_377571_13833Elaborations();
            fixTimeDependencies();
        }
    }

    public class _17_0_5_edc0357_1345510113602_28656_13834 extends DurativeEvent {

        public Parameter< ObjectFlow<Integer> > sig_17_0_5_edc0357_1345510114623_673172_14818 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_5_edc0357_1345510114624_890227_14824 = null;

        public Parameter< ObjectFlow<LADWP> > sig_17_0_5_edc0357_1345510114623_885329_14820 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_5_edc0357_1345510114943_995460_15372 = null;

        public IntegerParameter invoke_time = null;

        public Parameter< ObjectFlow<LADWP> > sig_17_0_5_edc0357_1345510114624_220759_14825 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_5_edc0357_1345510114623_648729_14817 = null;

        public ElaborationRule elaborationRule423 = null;

        public void init_17_0_5_edc0357_1345510113602_28656_13834Members() {
            try {
                sig_17_0_5_edc0357_1345510114623_673172_14818 = new Parameter<ObjectFlow<Integer>>("sig_17_0_5_edc0357_1345510114623_673172_14818", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114623_673172_14818"), this);
                sig_17_0_5_edc0357_1345510114624_890227_14824 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_5_edc0357_1345510114624_890227_14824", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114624_890227_14824"), this);
                sig_17_0_5_edc0357_1345510114623_885329_14820 = new Parameter<ObjectFlow<LADWP>>("sig_17_0_5_edc0357_1345510114623_885329_14820", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114623_885329_14820"), this);
                sig_17_0_5_edc0357_1345510114943_995460_15372 = new Parameter<ObjectFlow<Integer>>("sig_17_0_5_edc0357_1345510114943_995460_15372", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114943_995460_15372"), this);
                invoke_time = new IntegerParameter("invoke_time", this);
                sig_17_0_5_edc0357_1345510114624_220759_14825 = new Parameter<ObjectFlow<LADWP>>("sig_17_0_5_edc0357_1345510114624_220759_14825", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114624_220759_14825"), this);
                sig_17_0_5_edc0357_1345510114623_648729_14817 = new Parameter<ObjectFlow<Integer>>("sig_17_0_5_edc0357_1345510114623_648729_14817", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114623_648729_14817"), this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void init_17_0_5_edc0357_1345510113602_28656_13834Collections() {
            parameters.add(sig_17_0_5_edc0357_1345510114623_673172_14818);
            parameters.add(sig_17_0_5_edc0357_1345510114624_890227_14824);
            parameters.add(sig_17_0_5_edc0357_1345510114623_885329_14820);
            parameters.add(sig_17_0_5_edc0357_1345510114943_995460_15372);
            parameters.add(invoke_time);
            parameters.add(sig_17_0_5_edc0357_1345510114624_220759_14825);
            parameters.add(sig_17_0_5_edc0357_1345510114623_648729_14817);
        }

        public void init_17_0_5_edc0357_1345510113602_28656_13834Elaborations() {
            Expression<?>[] arguments423 = new Expression<?>[1];
            arguments423[0] = new Expression<Integer>(invoke_time);
            Expression<Boolean> condition423 = new Expression<Boolean>(true);
            elaborationRule423 = addElaborationRule(condition423, _17_0_5_edc0357_1345510113602_28656_13834.this, LADWP._17_0_5_edc0357_1345510113602_28656_13834._17_0_5_edc0357_1345510114622_850107_14812.class, "_InitialNode_generatePowerNow", arguments423);
        }

        public _17_0_5_edc0357_1345510113602_28656_13834() {
            super();
            init_17_0_5_edc0357_1345510113602_28656_13834Members();
            init_17_0_5_edc0357_1345510113602_28656_13834Collections();
            init_17_0_5_edc0357_1345510113602_28656_13834Elaborations();
        }

        public class _17_0_5_edc0357_1345510114621_721122_14806 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1345510114622_183257_14810_exists = null;

            public IntegerParameter _17_0_5_edc0357_1348001227222_390756_14207_endTime = null;

            public Parameter< LADWP > _17_0_5_edc0357_1345510114943_970062_15373 = null;

            public BooleanParameter _17_0_5_edc0357_1345510114622_749484_14811_exists = null;

            public ConstraintExpression constraint424 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114622_183257_14810_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114622_749484_14811_existsDependency = null;

            public ElaborationRule elaborationRule425 = null;

            public ElaborationRule elaborationRule426 = null;

            public void init_17_0_5_edc0357_1345510114621_721122_14806Members() {
                try {
                    _17_0_5_edc0357_1345510114622_183257_14810_exists = new BooleanParameter("_17_0_5_edc0357_1345510114622_183257_14810_exists", this);
                    _17_0_5_edc0357_1348001227222_390756_14207_endTime = new IntegerParameter("_17_0_5_edc0357_1348001227222_390756_14207_endTime", this);
                    _17_0_5_edc0357_1345510114943_970062_15373 = new Parameter<LADWP>("_17_0_5_edc0357_1345510114943_970062_15373", null, LADWP.this, this);
                    _17_0_5_edc0357_1345510114622_749484_14811_exists = new BooleanParameter("_17_0_5_edc0357_1345510114622_749484_14811_exists", this);
                    constraint424 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1348001227222_390756_14207_endTime)));
                    _17_0_5_edc0357_1345510114622_183257_14810_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114622_183257_14810_exists, new Functions.And(new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114623_673172_14818, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114623_885329_14820, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) }))));
                    _17_0_5_edc0357_1345510114622_749484_14811_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114622_749484_14811_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114621_721122_14806Collections() {
                parameters.add(_17_0_5_edc0357_1345510114622_183257_14810_exists);
                parameters.add(_17_0_5_edc0357_1348001227222_390756_14207_endTime);
                parameters.add(_17_0_5_edc0357_1345510114943_970062_15373);
                parameters.add(_17_0_5_edc0357_1345510114622_749484_14811_exists);
                constraintExpressions.add(constraint424);
                dependencies.add(_17_0_5_edc0357_1345510114622_183257_14810_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510114622_749484_14811_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510114621_721122_14806Elaborations() {
                Expression<?>[] arguments425 = new Expression<?>[1];
                arguments425[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition425 = new Expression<Boolean>(_17_0_5_edc0357_1345510114622_183257_14810_exists);
                elaborationRule425 = addElaborationRule(condition425, _17_0_5_edc0357_1345510113602_28656_13834.this, LADWP._17_0_5_edc0357_1345510113602_28656_13834._17_0_5_edc0357_1345510114622_183257_14810.class, "_SendSignalAction_generatePowerNow", arguments425);
                Expression<?>[] arguments426 = new Expression<?>[2];
                arguments426[0] = new Expression<Integer>(endTime);
                arguments426[1] = new Expression<LADWP>(_17_0_5_edc0357_1345510114943_970062_15373);
                Expression<Boolean> condition426 = new Expression<Boolean>(_17_0_5_edc0357_1345510114622_749484_14811_exists);
                elaborationRule426 = addElaborationRule(condition426, _17_0_5_edc0357_1345510113602_28656_13834.this, LADWP._17_0_5_edc0357_1345510113602_28656_13834._17_0_5_edc0357_1345510114622_749484_14811.class, "_ForkNode_generatePowerNow", arguments426);
            }

            public _17_0_5_edc0357_1345510114621_721122_14806() {
                super();
                init_17_0_5_edc0357_1345510114621_721122_14806Members();
                init_17_0_5_edc0357_1345510114621_721122_14806Collections();
                init_17_0_5_edc0357_1345510114621_721122_14806Elaborations();
            }

            public _17_0_5_edc0357_1345510114621_721122_14806(Expression<Integer> _17_0_5_edc0357_1348001227222_390756_14207_endTime) {
                super();
                init_17_0_5_edc0357_1345510114621_721122_14806Members();
                init_17_0_5_edc0357_1345510114621_721122_14806Collections();
                addDependency(this._17_0_5_edc0357_1348001227222_390756_14207_endTime, _17_0_5_edc0357_1348001227222_390756_14207_endTime);
                init_17_0_5_edc0357_1345510114621_721122_14806Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114621_963079_14807 extends DurativeEvent {

            public Parameter< LADWP > _17_0_5_edc0357_1345510114945_289088_15375 = null;

            public IntegerParameter _17_0_5_edc0357_1345510114622_749484_14811_endTime = null;

            public IntegerParameter _17_0_5_edc0357_1345510114944_326266_15374 = null;

            public ConstraintExpression constraint427 = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114944_326266_15374Dependency = null;

            public Effect effect428 = null;

            public Parameter effect428Var = null;

            public void init_17_0_5_edc0357_1345510114621_963079_14807Members() {
                try {
                    _17_0_5_edc0357_1345510114945_289088_15375 = new Parameter<LADWP>("_17_0_5_edc0357_1345510114945_289088_15375", null, null, this);
                    _17_0_5_edc0357_1345510114622_749484_14811_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114622_749484_14811_endTime", this);
                    _17_0_5_edc0357_1345510114944_326266_15374 = new IntegerParameter("_17_0_5_edc0357_1345510114944_326266_15374", this);
                    constraint427 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114622_749484_14811_endTime)));
                    _17_0_5_edc0357_1345510114944_326266_15374Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114944_326266_15374, new Expression(new FunctionCall(null, Utils.getMethodForArgTypes("LADWP._17_0_5_edc0357_1345510113602_28656_13834._17_0_5_edc0357_1345510114621_963079_14807", "generated", "getValue"), new Object[] { new Expression<Integer>(startTime) }, new FunctionCall(null, Parameter.class, "getValue", (Object[]) null, new FunctionCall(_17_0_5_edc0357_1345510114945_289088_15375, Parameter.class, "getMember", new Object[] { "reported_generation__17_0_5_edc0357_1345510113612_851613_13846" })))));
                    Object effect428VarV = sig_17_0_5_edc0357_1345510114623_648729_14817;
                    effect428Var = new Parameter("effect428Var", null, null, this);
                    addDependency(effect428Var, new Expression(effect428VarV));
                    effect428 = new EffectFunction(new FunctionCall(effect428Var, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { _17_0_5_edc0357_1345510114944_326266_15374, startTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114621_963079_14807Collections() {
                parameters.add(_17_0_5_edc0357_1345510114945_289088_15375);
                parameters.add(_17_0_5_edc0357_1345510114622_749484_14811_endTime);
                parameters.add(_17_0_5_edc0357_1345510114944_326266_15374);
                constraintExpressions.add(constraint427);
                dependencies.add(_17_0_5_edc0357_1345510114944_326266_15374Dependency);
                Set<Effect> effectsForeffect428Var = new HashSet<Effect>();
                effectsForeffect428Var.add(effect428);
                effects.put((Parameter<?>) effect428Var, effectsForeffect428Var);
            }

            public void init_17_0_5_edc0357_1345510114621_963079_14807Elaborations() {
            }

            public _17_0_5_edc0357_1345510114621_963079_14807() {
                super();
                init_17_0_5_edc0357_1345510114621_963079_14807Members();
                init_17_0_5_edc0357_1345510114621_963079_14807Collections();
                init_17_0_5_edc0357_1345510114621_963079_14807Elaborations();
            }

            public _17_0_5_edc0357_1345510114621_963079_14807(Expression<Integer> _17_0_5_edc0357_1345510114622_749484_14811_endTime, Expression<LADWP> _17_0_5_edc0357_1345510114945_289088_15375) {
                super();
                init_17_0_5_edc0357_1345510114621_963079_14807Members();
                init_17_0_5_edc0357_1345510114621_963079_14807Collections();
                addDependency(this._17_0_5_edc0357_1345510114622_749484_14811_endTime, _17_0_5_edc0357_1345510114622_749484_14811_endTime);
                addDependency(this._17_0_5_edc0357_1345510114945_289088_15375, _17_0_5_edc0357_1345510114945_289088_15375);
                init_17_0_5_edc0357_1345510114621_963079_14807Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114622_317480_14808 extends DurativeEvent {

            public Parameter< LADWP > _17_0_5_edc0357_1345510114946_838121_15377 = null;

            public IntegerParameter _17_0_5_edc0357_1345510114622_749484_14811_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1345510114622_312578_14809_exists = null;

            public IntegerParameter _17_0_5_edc0357_1345510114945_243329_15376 = null;

            public ConstraintExpression constraint429 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114622_312578_14809_existsDependency = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114945_243329_15376Dependency = null;

            public ElaborationRule elaborationRule430 = null;

            public void init_17_0_5_edc0357_1345510114622_317480_14808Members() {
                try {
                    _17_0_5_edc0357_1345510114946_838121_15377 = new Parameter<LADWP>("_17_0_5_edc0357_1345510114946_838121_15377", null, null, this);
                    _17_0_5_edc0357_1345510114622_749484_14811_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114622_749484_14811_endTime", this);
                    _17_0_5_edc0357_1345510114622_312578_14809_exists = new BooleanParameter("_17_0_5_edc0357_1345510114622_312578_14809_exists", this);
                    _17_0_5_edc0357_1345510114945_243329_15376 = new IntegerParameter("_17_0_5_edc0357_1345510114945_243329_15376", this);
                    constraint429 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114622_749484_14811_endTime)));
                    _17_0_5_edc0357_1345510114622_312578_14809_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114622_312578_14809_exists, new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114623_648729_14817, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })));
                    _17_0_5_edc0357_1345510114945_243329_15376Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114945_243329_15376, new Expression(new FunctionCall(null, Utils.getMethodForArgTypes("LADWP._17_0_5_edc0357_1345510113602_28656_13834._17_0_5_edc0357_1345510114622_317480_14808", "generated", "getValue"), new Object[] { new Expression<Integer>(startTime) }, new FunctionCall(null, Parameter.class, "getValue", (Object[]) null, new FunctionCall(_17_0_5_edc0357_1345510114946_838121_15377, Parameter.class, "getMember", new Object[] { "current_margin__17_0_5_edc0357_1345510113615_810190_13850" })))));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114622_317480_14808Collections() {
                parameters.add(_17_0_5_edc0357_1345510114946_838121_15377);
                parameters.add(_17_0_5_edc0357_1345510114622_749484_14811_endTime);
                parameters.add(_17_0_5_edc0357_1345510114622_312578_14809_exists);
                parameters.add(_17_0_5_edc0357_1345510114945_243329_15376);
                constraintExpressions.add(constraint429);
                dependencies.add(_17_0_5_edc0357_1345510114622_312578_14809_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510114945_243329_15376Dependency);
            }

            public void init_17_0_5_edc0357_1345510114622_317480_14808Elaborations() {
                Expression<?>[] arguments430 = new Expression<?>[2];
                arguments430[0] = new Expression<Integer>(endTime);
                arguments430[1] = new Expression<Integer>(_17_0_5_edc0357_1345510114945_243329_15376);
                Expression<Boolean> condition430 = new Expression<Boolean>(_17_0_5_edc0357_1345510114622_312578_14809_exists);
                elaborationRule430 = addElaborationRule(condition430, _17_0_5_edc0357_1345510113602_28656_13834.this, LADWP._17_0_5_edc0357_1345510113602_28656_13834._17_0_5_edc0357_1345510114622_312578_14809.class, "_CallBehaviorAction_generatePowerNow", arguments430);
            }

            public _17_0_5_edc0357_1345510114622_317480_14808() {
                super();
                init_17_0_5_edc0357_1345510114622_317480_14808Members();
                init_17_0_5_edc0357_1345510114622_317480_14808Collections();
                init_17_0_5_edc0357_1345510114622_317480_14808Elaborations();
            }

            public _17_0_5_edc0357_1345510114622_317480_14808(Expression<Integer> _17_0_5_edc0357_1345510114622_749484_14811_endTime, Expression<LADWP> _17_0_5_edc0357_1345510114946_838121_15377) {
                super();
                init_17_0_5_edc0357_1345510114622_317480_14808Members();
                init_17_0_5_edc0357_1345510114622_317480_14808Collections();
                addDependency(this._17_0_5_edc0357_1345510114622_749484_14811_endTime, _17_0_5_edc0357_1345510114622_749484_14811_endTime);
                addDependency(this._17_0_5_edc0357_1345510114946_838121_15377, _17_0_5_edc0357_1345510114946_838121_15377);
                init_17_0_5_edc0357_1345510114622_317480_14808Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114622_312578_14809 extends DurativeEvent {

            public IntegerParameter new_generation = null;

            public IntegerParameter _17_0_5_edc0357_1345510114622_317480_14808_endTime = null;

            public IntegerParameter _17_0_5_edc0357_1345510114942_15555_15371 = null;

            public IntegerParameter current = null;

            public IntegerParameter margin = null;

            public IntegerParameter _17_0_5_edc0357_1345510114947_257306_15378 = null;

            public IntegerParameter _17_0_5_edc0357_1345510114941_778134_15370 = null;

            public ConstraintExpression constraint431 = null;

            public Dependency< Integer > new_generationDependency = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114942_15555_15371Dependency = null;

            public Dependency< Integer > currentDependency = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114947_257306_15378Dependency = null;

            public Dependency< Integer > marginDependency = null;

            public Effect effect432 = null;

            public Parameter effect432Var = null;

            public void init_17_0_5_edc0357_1345510114622_312578_14809Members() {
                try {
                    new_generation = new IntegerParameter("new_generation", this);
                    _17_0_5_edc0357_1345510114622_317480_14808_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114622_317480_14808_endTime", this);
                    _17_0_5_edc0357_1345510114942_15555_15371 = new IntegerParameter("_17_0_5_edc0357_1345510114942_15555_15371", this);
                    current = new IntegerParameter("current", this);
                    margin = new IntegerParameter("margin", this);
                    _17_0_5_edc0357_1345510114947_257306_15378 = new IntegerParameter("_17_0_5_edc0357_1345510114947_257306_15378", this);
                    _17_0_5_edc0357_1345510114941_778134_15370 = new IntegerParameter("_17_0_5_edc0357_1345510114941_778134_15370", this);
                    constraint431 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114622_317480_14808_endTime)));
                    new_generationDependency = new Dependency<Integer>(new_generation, new Functions.Minus(new Expression<Integer>(current), new Expression<Integer>(margin)));
                    _17_0_5_edc0357_1345510114942_15555_15371Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114942_15555_15371, new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114623_648729_14817, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    currentDependency = new Dependency<Integer>(current, new Expression<Integer>(_17_0_5_edc0357_1345510114942_15555_15371));
                    _17_0_5_edc0357_1345510114947_257306_15378Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114947_257306_15378, new Expression<Integer>(new_generation));
                    marginDependency = new Dependency<Integer>(margin, new Expression<Integer>(_17_0_5_edc0357_1345510114941_778134_15370));
                    Object effect432VarV = sig_17_0_5_edc0357_1345510114623_673172_14818;
                    effect432Var = new Parameter("effect432Var", null, null, this);
                    addDependency(effect432Var, new Expression(effect432VarV));
                    effect432 = new EffectFunction(new FunctionCall(effect432Var, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { _17_0_5_edc0357_1345510114947_257306_15378, startTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114622_312578_14809Collections() {
                parameters.add(new_generation);
                parameters.add(_17_0_5_edc0357_1345510114622_317480_14808_endTime);
                parameters.add(_17_0_5_edc0357_1345510114942_15555_15371);
                parameters.add(current);
                parameters.add(margin);
                parameters.add(_17_0_5_edc0357_1345510114947_257306_15378);
                parameters.add(_17_0_5_edc0357_1345510114941_778134_15370);
                constraintExpressions.add(constraint431);
                dependencies.add(new_generationDependency);
                dependencies.add(_17_0_5_edc0357_1345510114942_15555_15371Dependency);
                dependencies.add(currentDependency);
                dependencies.add(_17_0_5_edc0357_1345510114947_257306_15378Dependency);
                dependencies.add(marginDependency);
                Set<Effect> effectsForeffect432Var = new HashSet<Effect>();
                effectsForeffect432Var.add(effect432);
                effects.put((Parameter<?>) effect432Var, effectsForeffect432Var);
            }

            public void init_17_0_5_edc0357_1345510114622_312578_14809Elaborations() {
            }

            public _17_0_5_edc0357_1345510114622_312578_14809() {
                super();
                init_17_0_5_edc0357_1345510114622_312578_14809Members();
                init_17_0_5_edc0357_1345510114622_312578_14809Collections();
                init_17_0_5_edc0357_1345510114622_312578_14809Elaborations();
            }

            public _17_0_5_edc0357_1345510114622_312578_14809(Expression<Integer> _17_0_5_edc0357_1345510114622_317480_14808_endTime, Expression<Integer> _17_0_5_edc0357_1345510114941_778134_15370) {
                super();
                init_17_0_5_edc0357_1345510114622_312578_14809Members();
                init_17_0_5_edc0357_1345510114622_312578_14809Collections();
                addDependency(this._17_0_5_edc0357_1345510114622_317480_14808_endTime, _17_0_5_edc0357_1345510114622_317480_14808_endTime);
                addDependency(this._17_0_5_edc0357_1345510114941_778134_15370, _17_0_5_edc0357_1345510114941_778134_15370);
                init_17_0_5_edc0357_1345510114622_312578_14809Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114622_183257_14810 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1345510114950_379431_15382 = null;

            public Parameter< LADWP > _17_0_5_edc0357_1345510114949_404158_15381 = null;

            public IntegerParameter _17_0_5_edc0357_1345510114621_721122_14806_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1345510114623_319984_14814_exists = null;

            public ConstraintExpression constraint433 = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114950_379431_15382Dependency = null;

            public Dependency< LADWP > _17_0_5_edc0357_1345510114949_404158_15381Dependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114623_319984_14814_existsDependency = null;

            public Effect effect434 = null;

            public Parameter effect434Var = null;

            public ElaborationRule elaborationRule435 = null;

            public void init_17_0_5_edc0357_1345510114622_183257_14810Members() {
                try {
                    _17_0_5_edc0357_1345510114950_379431_15382 = new IntegerParameter("_17_0_5_edc0357_1345510114950_379431_15382", this);
                    _17_0_5_edc0357_1345510114949_404158_15381 = new Parameter<LADWP>("_17_0_5_edc0357_1345510114949_404158_15381", null, null, this);
                    _17_0_5_edc0357_1345510114621_721122_14806_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114621_721122_14806_endTime", this);
                    _17_0_5_edc0357_1345510114623_319984_14814_exists = new BooleanParameter("_17_0_5_edc0357_1345510114623_319984_14814_exists", this);
                    constraint433 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114621_721122_14806_endTime)));
                    _17_0_5_edc0357_1345510114950_379431_15382Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114950_379431_15382, new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114623_673172_14818, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1345510114949_404158_15381Dependency = new Dependency<LADWP>(_17_0_5_edc0357_1345510114949_404158_15381, new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114623_885329_14820, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1345510114623_319984_14814_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114623_319984_14814_exists, new Functions.And(new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114624_220759_14825, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114624_890227_14824, Utils.getMethodForArgTypes("ObjectFlow<Boolean>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) }))));
                    Object effect434VarV = new FunctionCall(null, Parameter.class, "getValue", (Object[]) null, new FunctionCall(x, Parameter.class, "getMember", new Object[] { "ss_17_0_5_edc0357_1345510113607_182740_13840_changeGenerationValue" }));
                    effect434Var = new Parameter("effect434Var", null, null, this);
                    addDependency(effect434Var, new Expression(effect434VarV));
                    effect434 = new EffectFunction(new FunctionCall(null, Utils.getMethodForArgTypes("LADWP._17_0_5_edc0357_1345510113602_28656_13834._17_0_5_edc0357_1345510114622_183257_14810", "generated", "send"), new Object[] { x.getValue().new SignalchangeGenerationValue(endTime.getValue(), _17_0_5_edc0357_1345510114950_379431_15382.getValue()), endTime }, effect434Var));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114622_183257_14810Collections() {
                parameters.add(_17_0_5_edc0357_1345510114950_379431_15382);
                parameters.add(_17_0_5_edc0357_1345510114949_404158_15381);
                parameters.add(_17_0_5_edc0357_1345510114621_721122_14806_endTime);
                parameters.add(_17_0_5_edc0357_1345510114623_319984_14814_exists);
                constraintExpressions.add(constraint433);
                dependencies.add(_17_0_5_edc0357_1345510114950_379431_15382Dependency);
                dependencies.add(_17_0_5_edc0357_1345510114949_404158_15381Dependency);
                dependencies.add(_17_0_5_edc0357_1345510114623_319984_14814_existsDependency);
                Set<Effect> effectsForeffect434Var = new HashSet<Effect>();
                effectsForeffect434Var.add(effect434);
                effects.put((Parameter<?>) effect434Var, effectsForeffect434Var);
            }

            public void init_17_0_5_edc0357_1345510114622_183257_14810Elaborations() {
                Expression<?>[] arguments435 = new Expression<?>[1];
                arguments435[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition435 = new Expression<Boolean>(_17_0_5_edc0357_1345510114623_319984_14814_exists);
                elaborationRule435 = addElaborationRule(condition435, _17_0_5_edc0357_1345510113602_28656_13834.this, LADWP._17_0_5_edc0357_1345510113602_28656_13834._17_0_5_edc0357_1345510114623_319984_14814.class, "_AddStructuralFeatureValueAction_generatePowerNow", arguments435);
            }

            public _17_0_5_edc0357_1345510114622_183257_14810() {
                super();
                init_17_0_5_edc0357_1345510114622_183257_14810Members();
                init_17_0_5_edc0357_1345510114622_183257_14810Collections();
                init_17_0_5_edc0357_1345510114622_183257_14810Elaborations();
            }

            public _17_0_5_edc0357_1345510114622_183257_14810(Expression<Integer> _17_0_5_edc0357_1345510114621_721122_14806_endTime) {
                super();
                init_17_0_5_edc0357_1345510114622_183257_14810Members();
                init_17_0_5_edc0357_1345510114622_183257_14810Collections();
                addDependency(this._17_0_5_edc0357_1345510114621_721122_14806_endTime, _17_0_5_edc0357_1345510114621_721122_14806_endTime);
                init_17_0_5_edc0357_1345510114622_183257_14810Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114622_749484_14811 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1345510114622_317480_14808_exists = null;

            public BooleanParameter _17_0_5_edc0357_1345510114621_963079_14807_exists = null;

            public Parameter< LADWP > objectToPass = null;

            public IntegerParameter _17_0_5_edc0357_1345510114621_721122_14806_endTime = null;

            public ConstraintExpression constraint436 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114622_317480_14808_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114621_963079_14807_existsDependency = null;

            public Effect effect437 = null;

            public Parameter effect437Var = null;

            public Effect effect438 = null;

            public Parameter effect438Var = null;

            public ElaborationRule elaborationRule439 = null;

            public ElaborationRule elaborationRule440 = null;

            public void init_17_0_5_edc0357_1345510114622_749484_14811Members() {
                try {
                    _17_0_5_edc0357_1345510114622_317480_14808_exists = new BooleanParameter("_17_0_5_edc0357_1345510114622_317480_14808_exists", this);
                    _17_0_5_edc0357_1345510114621_963079_14807_exists = new BooleanParameter("_17_0_5_edc0357_1345510114621_963079_14807_exists", this);
                    objectToPass = new Parameter<LADWP>("objectToPass", null, null, this);
                    _17_0_5_edc0357_1345510114621_721122_14806_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114621_721122_14806_endTime", this);
                    constraint436 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114621_721122_14806_endTime)));
                    _17_0_5_edc0357_1345510114622_317480_14808_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114622_317480_14808_exists, new Expression<Boolean>(true));
                    _17_0_5_edc0357_1345510114621_963079_14807_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114621_963079_14807_exists, new Expression<Boolean>(true));
                    Object effect437VarV = sig_17_0_5_edc0357_1345510114623_885329_14820;
                    effect437Var = new Parameter("effect437Var", null, null, this);
                    addDependency(effect437Var, new Expression(effect437VarV));
                    effect437 = new EffectFunction(new FunctionCall(effect437Var, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                    Object effect438VarV = sig_17_0_5_edc0357_1345510114624_220759_14825;
                    effect438Var = new Parameter("effect438Var", null, null, this);
                    addDependency(effect438Var, new Expression(effect438VarV));
                    effect438 = new EffectFunction(new FunctionCall(effect438Var, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114622_749484_14811Collections() {
                parameters.add(_17_0_5_edc0357_1345510114622_317480_14808_exists);
                parameters.add(_17_0_5_edc0357_1345510114621_963079_14807_exists);
                parameters.add(objectToPass);
                parameters.add(_17_0_5_edc0357_1345510114621_721122_14806_endTime);
                constraintExpressions.add(constraint436);
                dependencies.add(_17_0_5_edc0357_1345510114622_317480_14808_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510114621_963079_14807_existsDependency);
                Set<Effect> effectsForeffect437Var = new HashSet<Effect>();
                effectsForeffect437Var.add(effect437);
                effects.put((Parameter<?>) effect437Var, effectsForeffect437Var);
                Set<Effect> effectsForeffect438Var = new HashSet<Effect>();
                effectsForeffect438Var.add(effect438);
                effects.put((Parameter<?>) effect438Var, effectsForeffect438Var);
            }

            public void init_17_0_5_edc0357_1345510114622_749484_14811Elaborations() {
                Expression<?>[] arguments439 = new Expression<?>[2];
                arguments439[0] = new Expression<Integer>(endTime);
                arguments439[1] = new Expression<LADWP>(objectToPass);
                Expression<Boolean> condition439 = new Expression<Boolean>(_17_0_5_edc0357_1345510114622_317480_14808_exists);
                elaborationRule439 = addElaborationRule(condition439, _17_0_5_edc0357_1345510113602_28656_13834.this, LADWP._17_0_5_edc0357_1345510113602_28656_13834._17_0_5_edc0357_1345510114622_317480_14808.class, "_ReadStructuralFeatureAction_generatePowerNow", arguments439);
                Expression<?>[] arguments440 = new Expression<?>[2];
                arguments440[0] = new Expression<Integer>(endTime);
                arguments440[1] = new Expression<LADWP>(objectToPass);
                Expression<Boolean> condition440 = new Expression<Boolean>(_17_0_5_edc0357_1345510114621_963079_14807_exists);
                elaborationRule440 = addElaborationRule(condition440, _17_0_5_edc0357_1345510113602_28656_13834.this, LADWP._17_0_5_edc0357_1345510113602_28656_13834._17_0_5_edc0357_1345510114621_963079_14807.class, "_ReadStructuralFeatureAction_generatePowerNow", arguments440);
            }

            public _17_0_5_edc0357_1345510114622_749484_14811() {
                super();
                init_17_0_5_edc0357_1345510114622_749484_14811Members();
                init_17_0_5_edc0357_1345510114622_749484_14811Collections();
                init_17_0_5_edc0357_1345510114622_749484_14811Elaborations();
            }

            public _17_0_5_edc0357_1345510114622_749484_14811(Expression<Integer> _17_0_5_edc0357_1345510114621_721122_14806_endTime, Expression<LADWP> objectToPass) {
                super();
                init_17_0_5_edc0357_1345510114622_749484_14811Members();
                init_17_0_5_edc0357_1345510114622_749484_14811Collections();
                addDependency(this._17_0_5_edc0357_1345510114621_721122_14806_endTime, _17_0_5_edc0357_1345510114621_721122_14806_endTime);
                addDependency(this.objectToPass, objectToPass);
                init_17_0_5_edc0357_1345510114622_749484_14811Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114622_850107_14812 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1348001227222_390756_14207_exists = null;

            public Dependency< Boolean > _17_0_5_edc0357_1348001227222_390756_14207_existsDependency = null;

            public ElaborationRule elaborationRule441 = null;

            public void init_17_0_5_edc0357_1345510114622_850107_14812Members() {
                try {
                    _17_0_5_edc0357_1348001227222_390756_14207_exists = new BooleanParameter("_17_0_5_edc0357_1348001227222_390756_14207_exists", this);
                    _17_0_5_edc0357_1348001227222_390756_14207_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1348001227222_390756_14207_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114622_850107_14812Collections() {
                parameters.add(_17_0_5_edc0357_1348001227222_390756_14207_exists);
                dependencies.add(_17_0_5_edc0357_1348001227222_390756_14207_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510114622_850107_14812Elaborations() {
                Expression<?>[] arguments441 = new Expression<?>[1];
                arguments441[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition441 = new Expression<Boolean>(_17_0_5_edc0357_1348001227222_390756_14207_exists);
                elaborationRule441 = addElaborationRule(condition441, _17_0_5_edc0357_1345510113602_28656_13834.this, LADWP._17_0_5_edc0357_1345510113602_28656_13834._17_0_5_edc0357_1348001227222_390756_14207.class, "_ForkNode_generatePowerNow", arguments441);
            }

            public _17_0_5_edc0357_1345510114622_850107_14812() {
                super();
                init_17_0_5_edc0357_1345510114622_850107_14812Members();
                init_17_0_5_edc0357_1345510114622_850107_14812Collections();
                init_17_0_5_edc0357_1345510114622_850107_14812Elaborations();
            }

            public _17_0_5_edc0357_1345510114622_850107_14812(Expression<Integer> startTime) {
                super();
                init_17_0_5_edc0357_1345510114622_850107_14812Members();
                init_17_0_5_edc0357_1345510114622_850107_14812Collections();
                removeDependency(this.startTime);
                addDependency(this.startTime, startTime);
                init_17_0_5_edc0357_1345510114622_850107_14812Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114622_20234_14813 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1345510114623_319984_14814_endTime = null;

            public ConstraintExpression constraint442 = null;

            public void init_17_0_5_edc0357_1345510114622_20234_14813Members() {
                try {
                    _17_0_5_edc0357_1345510114623_319984_14814_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114623_319984_14814_endTime", this);
                    constraint442 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114623_319984_14814_endTime)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114622_20234_14813Collections() {
                parameters.add(_17_0_5_edc0357_1345510114623_319984_14814_endTime);
                constraintExpressions.add(constraint442);
            }

            public void init_17_0_5_edc0357_1345510114622_20234_14813Elaborations() {
            }

            public _17_0_5_edc0357_1345510114622_20234_14813() {
                super();
                init_17_0_5_edc0357_1345510114622_20234_14813Members();
                init_17_0_5_edc0357_1345510114622_20234_14813Collections();
                init_17_0_5_edc0357_1345510114622_20234_14813Elaborations();
            }

            public _17_0_5_edc0357_1345510114622_20234_14813(Expression<Integer> _17_0_5_edc0357_1345510114623_319984_14814_endTime) {
                super();
                init_17_0_5_edc0357_1345510114622_20234_14813Members();
                init_17_0_5_edc0357_1345510114622_20234_14813Collections();
                addDependency(this._17_0_5_edc0357_1345510114623_319984_14814_endTime, _17_0_5_edc0357_1345510114623_319984_14814_endTime);
                init_17_0_5_edc0357_1345510114622_20234_14813Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114623_319984_14814 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1345510114950_81581_15383 = null;

            public Parameter< LADWP > _17_0_5_edc0357_1345510114951_495912_15384 = null;

            public IntegerParameter _17_0_5_edc0357_1345510114622_183257_14810_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1345510114622_20234_14813_exists = null;

            public ConstraintExpression constraint443 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114950_81581_15383Dependency = null;

            public Dependency< LADWP > _17_0_5_edc0357_1345510114951_495912_15384Dependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114622_20234_14813_existsDependency = null;

            public Effect effect444 = null;

            public Parameter effect444Var = null;

            public ElaborationRule elaborationRule445 = null;

            public void init_17_0_5_edc0357_1345510114623_319984_14814Members() {
                try {
                    _17_0_5_edc0357_1345510114950_81581_15383 = new BooleanParameter("_17_0_5_edc0357_1345510114950_81581_15383", this);
                    _17_0_5_edc0357_1345510114951_495912_15384 = new Parameter<LADWP>("_17_0_5_edc0357_1345510114951_495912_15384", null, null, this);
                    _17_0_5_edc0357_1345510114622_183257_14810_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114622_183257_14810_endTime", this);
                    _17_0_5_edc0357_1345510114622_20234_14813_exists = new BooleanParameter("_17_0_5_edc0357_1345510114622_20234_14813_exists", this);
                    constraint443 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114622_183257_14810_endTime)));
                    _17_0_5_edc0357_1345510114950_81581_15383Dependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114950_81581_15383, new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114624_890227_14824, Utils.getMethodForArgTypes("ObjectFlow<Boolean>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1345510114951_495912_15384Dependency = new Dependency<LADWP>(_17_0_5_edc0357_1345510114951_495912_15384, new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114624_220759_14825, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1345510114622_20234_14813_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114622_20234_14813_exists, new Expression<Boolean>(true));
                    Object effect444VarV = shortage__17_0_5_edc0357_1345510113616_127059_13852;
                    effect444Var = new Parameter("effect444Var", null, null, this);
                    addDependency(effect444Var, new Expression(effect444VarV));
                    effect444 = new EffectFunction(new FunctionCall(effect444Var, Utils.getMethodForArgTypes("TimeVaryingMap<Boolean>", "generated", "setValue", java.lang.Integer.class, java.lang.Object.class), new Object[] { startTime, _17_0_5_edc0357_1345510114950_81581_15383 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114623_319984_14814Collections() {
                parameters.add(_17_0_5_edc0357_1345510114950_81581_15383);
                parameters.add(_17_0_5_edc0357_1345510114951_495912_15384);
                parameters.add(_17_0_5_edc0357_1345510114622_183257_14810_endTime);
                parameters.add(_17_0_5_edc0357_1345510114622_20234_14813_exists);
                constraintExpressions.add(constraint443);
                dependencies.add(_17_0_5_edc0357_1345510114950_81581_15383Dependency);
                dependencies.add(_17_0_5_edc0357_1345510114951_495912_15384Dependency);
                dependencies.add(_17_0_5_edc0357_1345510114622_20234_14813_existsDependency);
                Set<Effect> effectsForeffect444Var = new HashSet<Effect>();
                effectsForeffect444Var.add(effect444);
                effects.put((Parameter<?>) effect444Var, effectsForeffect444Var);
            }

            public void init_17_0_5_edc0357_1345510114623_319984_14814Elaborations() {
                Expression<?>[] arguments445 = new Expression<?>[1];
                arguments445[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition445 = new Expression<Boolean>(_17_0_5_edc0357_1345510114622_20234_14813_exists);
                elaborationRule445 = addElaborationRule(condition445, _17_0_5_edc0357_1345510113602_28656_13834.this, LADWP._17_0_5_edc0357_1345510113602_28656_13834._17_0_5_edc0357_1345510114622_20234_14813.class, "_ActivityFinalNode_generatePowerNow", arguments445);
            }

            public _17_0_5_edc0357_1345510114623_319984_14814() {
                super();
                init_17_0_5_edc0357_1345510114623_319984_14814Members();
                init_17_0_5_edc0357_1345510114623_319984_14814Collections();
                init_17_0_5_edc0357_1345510114623_319984_14814Elaborations();
            }

            public _17_0_5_edc0357_1345510114623_319984_14814(Expression<Integer> _17_0_5_edc0357_1345510114622_183257_14810_endTime) {
                super();
                init_17_0_5_edc0357_1345510114623_319984_14814Members();
                init_17_0_5_edc0357_1345510114623_319984_14814Collections();
                addDependency(this._17_0_5_edc0357_1345510114622_183257_14810_endTime, _17_0_5_edc0357_1345510114622_183257_14810_endTime);
                init_17_0_5_edc0357_1345510114623_319984_14814Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114623_127267_14815 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1348001227222_390756_14207_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1345510114952_208510_15386 = null;

            public ConstraintExpression constraint446 = null;

            public Effect effect447 = null;

            public Parameter effect447Var = null;

            public void init_17_0_5_edc0357_1345510114623_127267_14815Members() {
                try {
                    _17_0_5_edc0357_1348001227222_390756_14207_endTime = new IntegerParameter("_17_0_5_edc0357_1348001227222_390756_14207_endTime", this);
                    _17_0_5_edc0357_1345510114952_208510_15386 = new BooleanParameter("_17_0_5_edc0357_1345510114952_208510_15386", this);
                    constraint446 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1348001227222_390756_14207_endTime)));
                    Object effect447VarV = sig_17_0_5_edc0357_1345510114624_890227_14824;
                    effect447Var = new Parameter("effect447Var", null, null, this);
                    addDependency(effect447Var, new Expression(effect447VarV));
                    effect447 = new EffectFunction(new FunctionCall(effect447Var, Utils.getMethodForArgTypes("ObjectFlow<Boolean>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { _17_0_5_edc0357_1345510114952_208510_15386, startTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114623_127267_14815Collections() {
                parameters.add(_17_0_5_edc0357_1348001227222_390756_14207_endTime);
                parameters.add(_17_0_5_edc0357_1345510114952_208510_15386);
                constraintExpressions.add(constraint446);
                Set<Effect> effectsForeffect447Var = new HashSet<Effect>();
                effectsForeffect447Var.add(effect447);
                effects.put((Parameter<?>) effect447Var, effectsForeffect447Var);
            }

            public void init_17_0_5_edc0357_1345510114623_127267_14815Elaborations() {
            }

            public _17_0_5_edc0357_1345510114623_127267_14815() {
                super();
                init_17_0_5_edc0357_1345510114623_127267_14815Members();
                init_17_0_5_edc0357_1345510114623_127267_14815Collections();
                init_17_0_5_edc0357_1345510114623_127267_14815Elaborations();
            }

            public _17_0_5_edc0357_1345510114623_127267_14815(Expression<Integer> _17_0_5_edc0357_1348001227222_390756_14207_endTime) {
                super();
                init_17_0_5_edc0357_1345510114623_127267_14815Members();
                init_17_0_5_edc0357_1345510114623_127267_14815Collections();
                addDependency(this._17_0_5_edc0357_1348001227222_390756_14207_endTime, _17_0_5_edc0357_1348001227222_390756_14207_endTime);
                init_17_0_5_edc0357_1345510114623_127267_14815Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1348001227222_390756_14207 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1345510114622_850107_14812_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1345510114621_721122_14806_exists = null;

            public BooleanParameter _17_0_5_edc0357_1345510114623_127267_14815_exists = null;

            public ConstraintExpression constraint448 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114621_721122_14806_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114623_127267_14815_existsDependency = null;

            public ElaborationRule elaborationRule449 = null;

            public ElaborationRule elaborationRule450 = null;

            public void init_17_0_5_edc0357_1348001227222_390756_14207Members() {
                try {
                    _17_0_5_edc0357_1345510114622_850107_14812_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114622_850107_14812_endTime", this);
                    _17_0_5_edc0357_1345510114621_721122_14806_exists = new BooleanParameter("_17_0_5_edc0357_1345510114621_721122_14806_exists", this);
                    _17_0_5_edc0357_1345510114623_127267_14815_exists = new BooleanParameter("_17_0_5_edc0357_1345510114623_127267_14815_exists", this);
                    constraint448 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114622_850107_14812_endTime)));
                    _17_0_5_edc0357_1345510114621_721122_14806_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114621_721122_14806_exists, new Expression<Boolean>(true));
                    _17_0_5_edc0357_1345510114623_127267_14815_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114623_127267_14815_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1348001227222_390756_14207Collections() {
                parameters.add(_17_0_5_edc0357_1345510114622_850107_14812_endTime);
                parameters.add(_17_0_5_edc0357_1345510114621_721122_14806_exists);
                parameters.add(_17_0_5_edc0357_1345510114623_127267_14815_exists);
                constraintExpressions.add(constraint448);
                dependencies.add(_17_0_5_edc0357_1345510114621_721122_14806_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510114623_127267_14815_existsDependency);
            }

            public void init_17_0_5_edc0357_1348001227222_390756_14207Elaborations() {
                Expression<?>[] arguments449 = new Expression<?>[1];
                arguments449[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition449 = new Expression<Boolean>(_17_0_5_edc0357_1345510114621_721122_14806_exists);
                elaborationRule449 = addElaborationRule(condition449, _17_0_5_edc0357_1345510113602_28656_13834.this, LADWP._17_0_5_edc0357_1345510113602_28656_13834._17_0_5_edc0357_1345510114621_721122_14806.class, "_ReadSelfAction_generatePowerNow", arguments449);
                Expression<?>[] arguments450 = new Expression<?>[1];
                arguments450[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition450 = new Expression<Boolean>(_17_0_5_edc0357_1345510114623_127267_14815_exists);
                elaborationRule450 = addElaborationRule(condition450, _17_0_5_edc0357_1345510113602_28656_13834.this, LADWP._17_0_5_edc0357_1345510113602_28656_13834._17_0_5_edc0357_1345510114623_127267_14815.class, "_ValueSpecificationAction_generatePowerNow", arguments450);
            }

            public _17_0_5_edc0357_1348001227222_390756_14207() {
                super();
                init_17_0_5_edc0357_1348001227222_390756_14207Members();
                init_17_0_5_edc0357_1348001227222_390756_14207Collections();
                init_17_0_5_edc0357_1348001227222_390756_14207Elaborations();
            }

            public _17_0_5_edc0357_1348001227222_390756_14207(Expression<Integer> _17_0_5_edc0357_1345510114622_850107_14812_endTime) {
                super();
                init_17_0_5_edc0357_1348001227222_390756_14207Members();
                init_17_0_5_edc0357_1348001227222_390756_14207Collections();
                addDependency(this._17_0_5_edc0357_1345510114622_850107_14812_endTime, _17_0_5_edc0357_1345510114622_850107_14812_endTime);
                init_17_0_5_edc0357_1348001227222_390756_14207Elaborations();
                fixTimeDependencies();
            }
        }

        public _17_0_5_edc0357_1345510113602_28656_13834(Expression<Integer> endTime) {
            super();
            init_17_0_5_edc0357_1345510113602_28656_13834Members();
            init_17_0_5_edc0357_1345510113602_28656_13834Collections();
            removeDependency(this.endTime);
            addDependency(this.endTime, endTime);
            init_17_0_5_edc0357_1345510113602_28656_13834Elaborations();
            fixTimeDependencies();
        }
    }

    public class _17_0_5_edc0357_1345510113602_419995_13835 extends DurativeEvent {

        public Parameter< ObjectFlow<Integer> > sig_17_0_5_edc0357_1345510114692_973151_14871 = null;

        public Parameter< ObjectFlow<LADWP> > sig_17_0_5_edc0357_1345510114692_891792_14873 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_5_edc0357_1345510114961_753751_15403 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_5_edc0357_1345510114692_988619_14872 = null;

        public IntegerParameter invoke_time = null;

        public ElaborationRule elaborationRule451 = null;

        public void init_17_0_5_edc0357_1345510113602_419995_13835Members() {
            try {
                sig_17_0_5_edc0357_1345510114692_973151_14871 = new Parameter<ObjectFlow<Integer>>("sig_17_0_5_edc0357_1345510114692_973151_14871", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114692_973151_14871"), this);
                sig_17_0_5_edc0357_1345510114692_891792_14873 = new Parameter<ObjectFlow<LADWP>>("sig_17_0_5_edc0357_1345510114692_891792_14873", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114692_891792_14873"), this);
                sig_17_0_5_edc0357_1345510114961_753751_15403 = new Parameter<ObjectFlow<Integer>>("sig_17_0_5_edc0357_1345510114961_753751_15403", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114961_753751_15403"), this);
                sig_17_0_5_edc0357_1345510114692_988619_14872 = new Parameter<ObjectFlow<Integer>>("sig_17_0_5_edc0357_1345510114692_988619_14872", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114692_988619_14872"), this);
                invoke_time = new IntegerParameter("invoke_time", this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void init_17_0_5_edc0357_1345510113602_419995_13835Collections() {
            parameters.add(sig_17_0_5_edc0357_1345510114692_973151_14871);
            parameters.add(sig_17_0_5_edc0357_1345510114692_891792_14873);
            parameters.add(sig_17_0_5_edc0357_1345510114961_753751_15403);
            parameters.add(sig_17_0_5_edc0357_1345510114692_988619_14872);
            parameters.add(invoke_time);
        }

        public void init_17_0_5_edc0357_1345510113602_419995_13835Elaborations() {
            Expression<?>[] arguments451 = new Expression<?>[1];
            arguments451[0] = new Expression<Integer>(invoke_time);
            Expression<Boolean> condition451 = new Expression<Boolean>(true);
            elaborationRule451 = addElaborationRule(condition451, _17_0_5_edc0357_1345510113602_419995_13835.this, LADWP._17_0_5_edc0357_1345510113602_419995_13835._17_0_5_edc0357_1345510114692_731793_14868.class, "_InitialNode_generatePowerLater", arguments451);
        }

        public _17_0_5_edc0357_1345510113602_419995_13835() {
            super();
            init_17_0_5_edc0357_1345510113602_419995_13835Members();
            init_17_0_5_edc0357_1345510113602_419995_13835Collections();
            init_17_0_5_edc0357_1345510113602_419995_13835Elaborations();
        }

        public class _17_0_5_edc0357_1345510114691_687782_14862 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1345510114692_336380_14867_exists = null;

            public IntegerParameter _17_0_5_edc0357_1345510114692_731793_14868_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1345510114692_827724_14866_exists = null;

            public Parameter< LADWP > _17_0_5_edc0357_1345510114961_887718_15404 = null;

            public ConstraintExpression constraint452 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114692_336380_14867_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114692_827724_14866_existsDependency = null;

            public ElaborationRule elaborationRule453 = null;

            public ElaborationRule elaborationRule454 = null;

            public void init_17_0_5_edc0357_1345510114691_687782_14862Members() {
                try {
                    _17_0_5_edc0357_1345510114692_336380_14867_exists = new BooleanParameter("_17_0_5_edc0357_1345510114692_336380_14867_exists", this);
                    _17_0_5_edc0357_1345510114692_731793_14868_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114692_731793_14868_endTime", this);
                    _17_0_5_edc0357_1345510114692_827724_14866_exists = new BooleanParameter("_17_0_5_edc0357_1345510114692_827724_14866_exists", this);
                    _17_0_5_edc0357_1345510114961_887718_15404 = new Parameter<LADWP>("_17_0_5_edc0357_1345510114961_887718_15404", null, LADWP.this, this);
                    constraint452 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114692_731793_14868_endTime)));
                    _17_0_5_edc0357_1345510114692_336380_14867_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114692_336380_14867_exists, new Expression<Boolean>(true));
                    _17_0_5_edc0357_1345510114692_827724_14866_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114692_827724_14866_exists, new Functions.And(new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114692_988619_14872, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114692_891792_14873, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) }))));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114691_687782_14862Collections() {
                parameters.add(_17_0_5_edc0357_1345510114692_336380_14867_exists);
                parameters.add(_17_0_5_edc0357_1345510114692_731793_14868_endTime);
                parameters.add(_17_0_5_edc0357_1345510114692_827724_14866_exists);
                parameters.add(_17_0_5_edc0357_1345510114961_887718_15404);
                constraintExpressions.add(constraint452);
                dependencies.add(_17_0_5_edc0357_1345510114692_336380_14867_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510114692_827724_14866_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510114691_687782_14862Elaborations() {
                Expression<?>[] arguments453 = new Expression<?>[2];
                arguments453[0] = new Expression<Integer>(endTime);
                arguments453[1] = new Expression<LADWP>(_17_0_5_edc0357_1345510114961_887718_15404);
                Expression<Boolean> condition453 = new Expression<Boolean>(_17_0_5_edc0357_1345510114692_336380_14867_exists);
                elaborationRule453 = addElaborationRule(condition453, _17_0_5_edc0357_1345510113602_419995_13835.this, LADWP._17_0_5_edc0357_1345510113602_419995_13835._17_0_5_edc0357_1345510114692_336380_14867.class, "_ForkNode_generatePowerLater", arguments453);
                Expression<?>[] arguments454 = new Expression<?>[1];
                arguments454[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition454 = new Expression<Boolean>(_17_0_5_edc0357_1345510114692_827724_14866_exists);
                elaborationRule454 = addElaborationRule(condition454, _17_0_5_edc0357_1345510113602_419995_13835.this, LADWP._17_0_5_edc0357_1345510113602_419995_13835._17_0_5_edc0357_1345510114692_827724_14866.class, "_SendSignalAction_generatePowerLater", arguments454);
            }

            public _17_0_5_edc0357_1345510114691_687782_14862() {
                super();
                init_17_0_5_edc0357_1345510114691_687782_14862Members();
                init_17_0_5_edc0357_1345510114691_687782_14862Collections();
                init_17_0_5_edc0357_1345510114691_687782_14862Elaborations();
            }

            public _17_0_5_edc0357_1345510114691_687782_14862(Expression<Integer> _17_0_5_edc0357_1345510114692_731793_14868_endTime) {
                super();
                init_17_0_5_edc0357_1345510114691_687782_14862Members();
                init_17_0_5_edc0357_1345510114691_687782_14862Collections();
                addDependency(this._17_0_5_edc0357_1345510114692_731793_14868_endTime, _17_0_5_edc0357_1345510114692_731793_14868_endTime);
                init_17_0_5_edc0357_1345510114691_687782_14862Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114691_619007_14863 extends DurativeEvent {

            public Parameter< LADWP > _17_0_5_edc0357_1345510114963_707579_15406 = null;

            public IntegerParameter _17_0_5_edc0357_1345510114962_204235_15405 = null;

            public IntegerParameter _17_0_5_edc0357_1345510114692_336380_14867_endTime = null;

            public ConstraintExpression constraint455 = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114962_204235_15405Dependency = null;

            public Effect effect456 = null;

            public Parameter effect456Var = null;

            public void init_17_0_5_edc0357_1345510114691_619007_14863Members() {
                try {
                    _17_0_5_edc0357_1345510114963_707579_15406 = new Parameter<LADWP>("_17_0_5_edc0357_1345510114963_707579_15406", null, null, this);
                    _17_0_5_edc0357_1345510114962_204235_15405 = new IntegerParameter("_17_0_5_edc0357_1345510114962_204235_15405", this);
                    _17_0_5_edc0357_1345510114692_336380_14867_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114692_336380_14867_endTime", this);
                    constraint455 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114692_336380_14867_endTime)));
                    _17_0_5_edc0357_1345510114962_204235_15405Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114962_204235_15405, new Expression(new FunctionCall(null, Utils.getMethodForArgTypes("LADWP._17_0_5_edc0357_1345510113602_419995_13835._17_0_5_edc0357_1345510114691_619007_14863", "generated", "getValue"), new Object[] { new Expression<Integer>(startTime) }, new FunctionCall(null, Parameter.class, "getValue", (Object[]) null, new FunctionCall(_17_0_5_edc0357_1345510114963_707579_15406, Parameter.class, "getMember", new Object[] { "reported_generation__17_0_5_edc0357_1345510113612_851613_13846" })))));
                    Object effect456VarV = sig_17_0_5_edc0357_1345510114692_973151_14871;
                    effect456Var = new Parameter("effect456Var", null, null, this);
                    addDependency(effect456Var, new Expression(effect456VarV));
                    effect456 = new EffectFunction(new FunctionCall(effect456Var, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { _17_0_5_edc0357_1345510114962_204235_15405, startTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114691_619007_14863Collections() {
                parameters.add(_17_0_5_edc0357_1345510114963_707579_15406);
                parameters.add(_17_0_5_edc0357_1345510114962_204235_15405);
                parameters.add(_17_0_5_edc0357_1345510114692_336380_14867_endTime);
                constraintExpressions.add(constraint455);
                dependencies.add(_17_0_5_edc0357_1345510114962_204235_15405Dependency);
                Set<Effect> effectsForeffect456Var = new HashSet<Effect>();
                effectsForeffect456Var.add(effect456);
                effects.put((Parameter<?>) effect456Var, effectsForeffect456Var);
            }

            public void init_17_0_5_edc0357_1345510114691_619007_14863Elaborations() {
            }

            public _17_0_5_edc0357_1345510114691_619007_14863() {
                super();
                init_17_0_5_edc0357_1345510114691_619007_14863Members();
                init_17_0_5_edc0357_1345510114691_619007_14863Collections();
                init_17_0_5_edc0357_1345510114691_619007_14863Elaborations();
            }

            public _17_0_5_edc0357_1345510114691_619007_14863(Expression<Integer> _17_0_5_edc0357_1345510114692_336380_14867_endTime, Expression<LADWP> _17_0_5_edc0357_1345510114963_707579_15406) {
                super();
                init_17_0_5_edc0357_1345510114691_619007_14863Members();
                init_17_0_5_edc0357_1345510114691_619007_14863Collections();
                addDependency(this._17_0_5_edc0357_1345510114692_336380_14867_endTime, _17_0_5_edc0357_1345510114692_336380_14867_endTime);
                addDependency(this._17_0_5_edc0357_1345510114963_707579_15406, _17_0_5_edc0357_1345510114963_707579_15406);
                init_17_0_5_edc0357_1345510114691_619007_14863Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114691_435966_14864 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1345510114691_864657_14865_exists = null;

            public Parameter< LADWP > _17_0_5_edc0357_1345510114964_600674_15408 = null;

            public IntegerParameter _17_0_5_edc0357_1345510114964_115792_15407 = null;

            public IntegerParameter _17_0_5_edc0357_1345510114692_336380_14867_endTime = null;

            public ConstraintExpression constraint457 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114691_864657_14865_existsDependency = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114964_115792_15407Dependency = null;

            public ElaborationRule elaborationRule458 = null;

            public void init_17_0_5_edc0357_1345510114691_435966_14864Members() {
                try {
                    _17_0_5_edc0357_1345510114691_864657_14865_exists = new BooleanParameter("_17_0_5_edc0357_1345510114691_864657_14865_exists", this);
                    _17_0_5_edc0357_1345510114964_600674_15408 = new Parameter<LADWP>("_17_0_5_edc0357_1345510114964_600674_15408", null, null, this);
                    _17_0_5_edc0357_1345510114964_115792_15407 = new IntegerParameter("_17_0_5_edc0357_1345510114964_115792_15407", this);
                    _17_0_5_edc0357_1345510114692_336380_14867_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114692_336380_14867_endTime", this);
                    constraint457 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114692_336380_14867_endTime)));
                    _17_0_5_edc0357_1345510114691_864657_14865_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114691_864657_14865_exists, new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114692_973151_14871, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })));
                    _17_0_5_edc0357_1345510114964_115792_15407Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114964_115792_15407, new Expression(new FunctionCall(null, Utils.getMethodForArgTypes("LADWP._17_0_5_edc0357_1345510113602_419995_13835._17_0_5_edc0357_1345510114691_435966_14864", "generated", "getValue"), new Object[] { new Expression<Integer>(startTime) }, new FunctionCall(null, Parameter.class, "getValue", (Object[]) null, new FunctionCall(_17_0_5_edc0357_1345510114964_600674_15408, Parameter.class, "getMember", new Object[] { "expected_margin__17_0_5_edc0357_1345510113614_771928_13849" })))));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114691_435966_14864Collections() {
                parameters.add(_17_0_5_edc0357_1345510114691_864657_14865_exists);
                parameters.add(_17_0_5_edc0357_1345510114964_600674_15408);
                parameters.add(_17_0_5_edc0357_1345510114964_115792_15407);
                parameters.add(_17_0_5_edc0357_1345510114692_336380_14867_endTime);
                constraintExpressions.add(constraint457);
                dependencies.add(_17_0_5_edc0357_1345510114691_864657_14865_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510114964_115792_15407Dependency);
            }

            public void init_17_0_5_edc0357_1345510114691_435966_14864Elaborations() {
                Expression<?>[] arguments458 = new Expression<?>[2];
                arguments458[0] = new Expression<Integer>(endTime);
                arguments458[1] = new Expression<Integer>(_17_0_5_edc0357_1345510114964_115792_15407);
                Expression<Boolean> condition458 = new Expression<Boolean>(_17_0_5_edc0357_1345510114691_864657_14865_exists);
                elaborationRule458 = addElaborationRule(condition458, _17_0_5_edc0357_1345510113602_419995_13835.this, LADWP._17_0_5_edc0357_1345510113602_419995_13835._17_0_5_edc0357_1345510114691_864657_14865.class, "_CallBehaviorAction_generatePowerLater", arguments458);
            }

            public _17_0_5_edc0357_1345510114691_435966_14864() {
                super();
                init_17_0_5_edc0357_1345510114691_435966_14864Members();
                init_17_0_5_edc0357_1345510114691_435966_14864Collections();
                init_17_0_5_edc0357_1345510114691_435966_14864Elaborations();
            }

            public _17_0_5_edc0357_1345510114691_435966_14864(Expression<Integer> _17_0_5_edc0357_1345510114692_336380_14867_endTime, Expression<LADWP> _17_0_5_edc0357_1345510114964_600674_15408) {
                super();
                init_17_0_5_edc0357_1345510114691_435966_14864Members();
                init_17_0_5_edc0357_1345510114691_435966_14864Collections();
                addDependency(this._17_0_5_edc0357_1345510114692_336380_14867_endTime, _17_0_5_edc0357_1345510114692_336380_14867_endTime);
                addDependency(this._17_0_5_edc0357_1345510114964_600674_15408, _17_0_5_edc0357_1345510114964_600674_15408);
                init_17_0_5_edc0357_1345510114691_435966_14864Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114691_864657_14865 extends DurativeEvent {

            public IntegerParameter new_generation = null;

            public IntegerParameter current = null;

            public IntegerParameter _17_0_5_edc0357_1345510114691_435966_14864_endTime = null;

            public IntegerParameter _17_0_5_edc0357_1345510114965_375269_15409 = null;

            public IntegerParameter margin = null;

            public IntegerParameter _17_0_5_edc0357_1345510114960_284722_15402 = null;

            public IntegerParameter _17_0_5_edc0357_1345510114959_575761_15401 = null;

            public ConstraintExpression constraint459 = null;

            public Dependency< Integer > new_generationDependency = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114965_375269_15409Dependency = null;

            public Dependency< Integer > currentDependency = null;

            public Dependency< Integer > marginDependency = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114960_284722_15402Dependency = null;

            public Effect effect460 = null;

            public Parameter effect460Var = null;

            public void init_17_0_5_edc0357_1345510114691_864657_14865Members() {
                try {
                    new_generation = new IntegerParameter("new_generation", this);
                    current = new IntegerParameter("current", this);
                    _17_0_5_edc0357_1345510114691_435966_14864_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114691_435966_14864_endTime", this);
                    _17_0_5_edc0357_1345510114965_375269_15409 = new IntegerParameter("_17_0_5_edc0357_1345510114965_375269_15409", this);
                    margin = new IntegerParameter("margin", this);
                    _17_0_5_edc0357_1345510114960_284722_15402 = new IntegerParameter("_17_0_5_edc0357_1345510114960_284722_15402", this);
                    _17_0_5_edc0357_1345510114959_575761_15401 = new IntegerParameter("_17_0_5_edc0357_1345510114959_575761_15401", this);
                    constraint459 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114691_435966_14864_endTime)));
                    new_generationDependency = new Dependency<Integer>(new_generation, new Functions.Minus(new Expression<Integer>(current), new Expression<Integer>(margin)));
                    _17_0_5_edc0357_1345510114965_375269_15409Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114965_375269_15409, new Expression<Integer>(new_generation));
                    currentDependency = new Dependency<Integer>(current, new Expression<Integer>(_17_0_5_edc0357_1345510114960_284722_15402));
                    marginDependency = new Dependency<Integer>(margin, new Expression<Integer>(_17_0_5_edc0357_1345510114959_575761_15401));
                    _17_0_5_edc0357_1345510114960_284722_15402Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114960_284722_15402, new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114692_973151_14871, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    Object effect460VarV = sig_17_0_5_edc0357_1345510114692_988619_14872;
                    effect460Var = new Parameter("effect460Var", null, null, this);
                    addDependency(effect460Var, new Expression(effect460VarV));
                    effect460 = new EffectFunction(new FunctionCall(effect460Var, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { _17_0_5_edc0357_1345510114965_375269_15409, startTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114691_864657_14865Collections() {
                parameters.add(new_generation);
                parameters.add(current);
                parameters.add(_17_0_5_edc0357_1345510114691_435966_14864_endTime);
                parameters.add(_17_0_5_edc0357_1345510114965_375269_15409);
                parameters.add(margin);
                parameters.add(_17_0_5_edc0357_1345510114960_284722_15402);
                parameters.add(_17_0_5_edc0357_1345510114959_575761_15401);
                constraintExpressions.add(constraint459);
                dependencies.add(new_generationDependency);
                dependencies.add(_17_0_5_edc0357_1345510114965_375269_15409Dependency);
                dependencies.add(currentDependency);
                dependencies.add(marginDependency);
                dependencies.add(_17_0_5_edc0357_1345510114960_284722_15402Dependency);
                Set<Effect> effectsForeffect460Var = new HashSet<Effect>();
                effectsForeffect460Var.add(effect460);
                effects.put((Parameter<?>) effect460Var, effectsForeffect460Var);
            }

            public void init_17_0_5_edc0357_1345510114691_864657_14865Elaborations() {
            }

            public _17_0_5_edc0357_1345510114691_864657_14865() {
                super();
                init_17_0_5_edc0357_1345510114691_864657_14865Members();
                init_17_0_5_edc0357_1345510114691_864657_14865Collections();
                init_17_0_5_edc0357_1345510114691_864657_14865Elaborations();
            }

            public _17_0_5_edc0357_1345510114691_864657_14865(Expression<Integer> _17_0_5_edc0357_1345510114691_435966_14864_endTime, Expression<Integer> _17_0_5_edc0357_1345510114959_575761_15401) {
                super();
                init_17_0_5_edc0357_1345510114691_864657_14865Members();
                init_17_0_5_edc0357_1345510114691_864657_14865Collections();
                addDependency(this._17_0_5_edc0357_1345510114691_435966_14864_endTime, _17_0_5_edc0357_1345510114691_435966_14864_endTime);
                addDependency(this._17_0_5_edc0357_1345510114959_575761_15401, _17_0_5_edc0357_1345510114959_575761_15401);
                init_17_0_5_edc0357_1345510114691_864657_14865Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114692_827724_14866 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1345510114691_687782_14862_endTime = null;

            public Parameter< LADWP > _17_0_5_edc0357_1345510114967_325736_15412 = null;

            public IntegerParameter _17_0_5_edc0357_1345510114968_74650_15413 = null;

            public BooleanParameter _17_0_5_edc0357_1345510114692_509502_14869_exists = null;

            public ConstraintExpression constraint461 = null;

            public Dependency< LADWP > _17_0_5_edc0357_1345510114967_325736_15412Dependency = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114968_74650_15413Dependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114692_509502_14869_existsDependency = null;

            public Effect effect462 = null;

            public Parameter effect462Var = null;

            public ElaborationRule elaborationRule463 = null;

            public void init_17_0_5_edc0357_1345510114692_827724_14866Members() {
                try {
                    _17_0_5_edc0357_1345510114691_687782_14862_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114691_687782_14862_endTime", this);
                    _17_0_5_edc0357_1345510114967_325736_15412 = new Parameter<LADWP>("_17_0_5_edc0357_1345510114967_325736_15412", null, null, this);
                    _17_0_5_edc0357_1345510114968_74650_15413 = new IntegerParameter("_17_0_5_edc0357_1345510114968_74650_15413", this);
                    _17_0_5_edc0357_1345510114692_509502_14869_exists = new BooleanParameter("_17_0_5_edc0357_1345510114692_509502_14869_exists", this);
                    constraint461 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114691_687782_14862_endTime)));
                    _17_0_5_edc0357_1345510114967_325736_15412Dependency = new Dependency<LADWP>(_17_0_5_edc0357_1345510114967_325736_15412, new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114692_891792_14873, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1345510114968_74650_15413Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114968_74650_15413, new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114692_988619_14872, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1345510114692_509502_14869_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114692_509502_14869_exists, new Expression<Boolean>(true));
                    Object effect462VarV = new FunctionCall(null, Parameter.class, "getValue", (Object[]) null, new FunctionCall(x, Parameter.class, "getMember", new Object[] { "ss_17_0_5_edc0357_1345510113607_182740_13840_changeGenerationValue" }));
                    effect462Var = new Parameter("effect462Var", null, null, this);
                    addDependency(effect462Var, new Expression(effect462VarV));
                    effect462 = new EffectFunction(new FunctionCall(null, Utils.getMethodForArgTypes("LADWP._17_0_5_edc0357_1345510113602_419995_13835._17_0_5_edc0357_1345510114692_827724_14866", "generated", "send"), new Object[] { x.getValue().new SignalchangeGenerationValue(endTime.getValue(), _17_0_5_edc0357_1345510114968_74650_15413.getValue()), endTime }, effect462Var));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114692_827724_14866Collections() {
                parameters.add(_17_0_5_edc0357_1345510114691_687782_14862_endTime);
                parameters.add(_17_0_5_edc0357_1345510114967_325736_15412);
                parameters.add(_17_0_5_edc0357_1345510114968_74650_15413);
                parameters.add(_17_0_5_edc0357_1345510114692_509502_14869_exists);
                constraintExpressions.add(constraint461);
                dependencies.add(_17_0_5_edc0357_1345510114967_325736_15412Dependency);
                dependencies.add(_17_0_5_edc0357_1345510114968_74650_15413Dependency);
                dependencies.add(_17_0_5_edc0357_1345510114692_509502_14869_existsDependency);
                Set<Effect> effectsForeffect462Var = new HashSet<Effect>();
                effectsForeffect462Var.add(effect462);
                effects.put((Parameter<?>) effect462Var, effectsForeffect462Var);
            }

            public void init_17_0_5_edc0357_1345510114692_827724_14866Elaborations() {
                Expression<?>[] arguments463 = new Expression<?>[1];
                arguments463[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition463 = new Expression<Boolean>(_17_0_5_edc0357_1345510114692_509502_14869_exists);
                elaborationRule463 = addElaborationRule(condition463, _17_0_5_edc0357_1345510113602_419995_13835.this, LADWP._17_0_5_edc0357_1345510113602_419995_13835._17_0_5_edc0357_1345510114692_509502_14869.class, "_ActivityFinalNode_generatePowerLater", arguments463);
            }

            public _17_0_5_edc0357_1345510114692_827724_14866() {
                super();
                init_17_0_5_edc0357_1345510114692_827724_14866Members();
                init_17_0_5_edc0357_1345510114692_827724_14866Collections();
                init_17_0_5_edc0357_1345510114692_827724_14866Elaborations();
            }

            public _17_0_5_edc0357_1345510114692_827724_14866(Expression<Integer> _17_0_5_edc0357_1345510114691_687782_14862_endTime) {
                super();
                init_17_0_5_edc0357_1345510114692_827724_14866Members();
                init_17_0_5_edc0357_1345510114692_827724_14866Collections();
                addDependency(this._17_0_5_edc0357_1345510114691_687782_14862_endTime, _17_0_5_edc0357_1345510114691_687782_14862_endTime);
                init_17_0_5_edc0357_1345510114692_827724_14866Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114692_336380_14867 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1345510114691_687782_14862_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1345510114691_435966_14864_exists = null;

            public BooleanParameter _17_0_5_edc0357_1345510114691_619007_14863_exists = null;

            public Parameter< LADWP > objectToPass = null;

            public ConstraintExpression constraint464 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114691_435966_14864_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114691_619007_14863_existsDependency = null;

            public Effect effect465 = null;

            public Parameter effect465Var = null;

            public ElaborationRule elaborationRule466 = null;

            public ElaborationRule elaborationRule467 = null;

            public void init_17_0_5_edc0357_1345510114692_336380_14867Members() {
                try {
                    _17_0_5_edc0357_1345510114691_687782_14862_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114691_687782_14862_endTime", this);
                    _17_0_5_edc0357_1345510114691_435966_14864_exists = new BooleanParameter("_17_0_5_edc0357_1345510114691_435966_14864_exists", this);
                    _17_0_5_edc0357_1345510114691_619007_14863_exists = new BooleanParameter("_17_0_5_edc0357_1345510114691_619007_14863_exists", this);
                    objectToPass = new Parameter<LADWP>("objectToPass", null, null, this);
                    constraint464 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114691_687782_14862_endTime)));
                    _17_0_5_edc0357_1345510114691_435966_14864_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114691_435966_14864_exists, new Expression<Boolean>(true));
                    _17_0_5_edc0357_1345510114691_619007_14863_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114691_619007_14863_exists, new Expression<Boolean>(true));
                    Object effect465VarV = sig_17_0_5_edc0357_1345510114692_891792_14873;
                    effect465Var = new Parameter("effect465Var", null, null, this);
                    addDependency(effect465Var, new Expression(effect465VarV));
                    effect465 = new EffectFunction(new FunctionCall(effect465Var, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114692_336380_14867Collections() {
                parameters.add(_17_0_5_edc0357_1345510114691_687782_14862_endTime);
                parameters.add(_17_0_5_edc0357_1345510114691_435966_14864_exists);
                parameters.add(_17_0_5_edc0357_1345510114691_619007_14863_exists);
                parameters.add(objectToPass);
                constraintExpressions.add(constraint464);
                dependencies.add(_17_0_5_edc0357_1345510114691_435966_14864_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510114691_619007_14863_existsDependency);
                Set<Effect> effectsForeffect465Var = new HashSet<Effect>();
                effectsForeffect465Var.add(effect465);
                effects.put((Parameter<?>) effect465Var, effectsForeffect465Var);
            }

            public void init_17_0_5_edc0357_1345510114692_336380_14867Elaborations() {
                Expression<?>[] arguments466 = new Expression<?>[2];
                arguments466[0] = new Expression<Integer>(endTime);
                arguments466[1] = new Expression<LADWP>(objectToPass);
                Expression<Boolean> condition466 = new Expression<Boolean>(_17_0_5_edc0357_1345510114691_435966_14864_exists);
                elaborationRule466 = addElaborationRule(condition466, _17_0_5_edc0357_1345510113602_419995_13835.this, LADWP._17_0_5_edc0357_1345510113602_419995_13835._17_0_5_edc0357_1345510114691_435966_14864.class, "_ReadStructuralFeatureAction_generatePowerLater", arguments466);
                Expression<?>[] arguments467 = new Expression<?>[2];
                arguments467[0] = new Expression<Integer>(endTime);
                arguments467[1] = new Expression<LADWP>(objectToPass);
                Expression<Boolean> condition467 = new Expression<Boolean>(_17_0_5_edc0357_1345510114691_619007_14863_exists);
                elaborationRule467 = addElaborationRule(condition467, _17_0_5_edc0357_1345510113602_419995_13835.this, LADWP._17_0_5_edc0357_1345510113602_419995_13835._17_0_5_edc0357_1345510114691_619007_14863.class, "_ReadStructuralFeatureAction_generatePowerLater", arguments467);
            }

            public _17_0_5_edc0357_1345510114692_336380_14867() {
                super();
                init_17_0_5_edc0357_1345510114692_336380_14867Members();
                init_17_0_5_edc0357_1345510114692_336380_14867Collections();
                init_17_0_5_edc0357_1345510114692_336380_14867Elaborations();
            }

            public _17_0_5_edc0357_1345510114692_336380_14867(Expression<Integer> _17_0_5_edc0357_1345510114691_687782_14862_endTime, Expression<LADWP> objectToPass) {
                super();
                init_17_0_5_edc0357_1345510114692_336380_14867Members();
                init_17_0_5_edc0357_1345510114692_336380_14867Collections();
                addDependency(this._17_0_5_edc0357_1345510114691_687782_14862_endTime, _17_0_5_edc0357_1345510114691_687782_14862_endTime);
                addDependency(this.objectToPass, objectToPass);
                init_17_0_5_edc0357_1345510114692_336380_14867Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114692_731793_14868 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1345510114691_687782_14862_exists = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114691_687782_14862_existsDependency = null;

            public ElaborationRule elaborationRule468 = null;

            public void init_17_0_5_edc0357_1345510114692_731793_14868Members() {
                try {
                    _17_0_5_edc0357_1345510114691_687782_14862_exists = new BooleanParameter("_17_0_5_edc0357_1345510114691_687782_14862_exists", this);
                    _17_0_5_edc0357_1345510114691_687782_14862_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114691_687782_14862_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114692_731793_14868Collections() {
                parameters.add(_17_0_5_edc0357_1345510114691_687782_14862_exists);
                dependencies.add(_17_0_5_edc0357_1345510114691_687782_14862_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510114692_731793_14868Elaborations() {
                Expression<?>[] arguments468 = new Expression<?>[1];
                arguments468[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition468 = new Expression<Boolean>(_17_0_5_edc0357_1345510114691_687782_14862_exists);
                elaborationRule468 = addElaborationRule(condition468, _17_0_5_edc0357_1345510113602_419995_13835.this, LADWP._17_0_5_edc0357_1345510113602_419995_13835._17_0_5_edc0357_1345510114691_687782_14862.class, "_ReadSelfAction_generatePowerLater", arguments468);
            }

            public _17_0_5_edc0357_1345510114692_731793_14868() {
                super();
                init_17_0_5_edc0357_1345510114692_731793_14868Members();
                init_17_0_5_edc0357_1345510114692_731793_14868Collections();
                init_17_0_5_edc0357_1345510114692_731793_14868Elaborations();
            }

            public _17_0_5_edc0357_1345510114692_731793_14868(Expression<Integer> startTime) {
                super();
                init_17_0_5_edc0357_1345510114692_731793_14868Members();
                init_17_0_5_edc0357_1345510114692_731793_14868Collections();
                removeDependency(this.startTime);
                addDependency(this.startTime, startTime);
                init_17_0_5_edc0357_1345510114692_731793_14868Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114692_509502_14869 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1345510114692_827724_14866_endTime = null;

            public ConstraintExpression constraint469 = null;

            public void init_17_0_5_edc0357_1345510114692_509502_14869Members() {
                try {
                    _17_0_5_edc0357_1345510114692_827724_14866_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114692_827724_14866_endTime", this);
                    constraint469 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114692_827724_14866_endTime)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114692_509502_14869Collections() {
                parameters.add(_17_0_5_edc0357_1345510114692_827724_14866_endTime);
                constraintExpressions.add(constraint469);
            }

            public void init_17_0_5_edc0357_1345510114692_509502_14869Elaborations() {
            }

            public _17_0_5_edc0357_1345510114692_509502_14869() {
                super();
                init_17_0_5_edc0357_1345510114692_509502_14869Members();
                init_17_0_5_edc0357_1345510114692_509502_14869Collections();
                init_17_0_5_edc0357_1345510114692_509502_14869Elaborations();
            }

            public _17_0_5_edc0357_1345510114692_509502_14869(Expression<Integer> _17_0_5_edc0357_1345510114692_827724_14866_endTime) {
                super();
                init_17_0_5_edc0357_1345510114692_509502_14869Members();
                init_17_0_5_edc0357_1345510114692_509502_14869Collections();
                addDependency(this._17_0_5_edc0357_1345510114692_827724_14866_endTime, _17_0_5_edc0357_1345510114692_827724_14866_endTime);
                init_17_0_5_edc0357_1345510114692_509502_14869Elaborations();
                fixTimeDependencies();
            }
        }

        public _17_0_5_edc0357_1345510113602_419995_13835(Expression<Integer> endTime) {
            super();
            init_17_0_5_edc0357_1345510113602_419995_13835Members();
            init_17_0_5_edc0357_1345510113602_419995_13835Collections();
            removeDependency(this.endTime);
            addDependency(this.endTime, endTime);
            init_17_0_5_edc0357_1345510113602_419995_13835Elaborations();
            fixTimeDependencies();
        }
    }

    public class _17_0_5_edc0357_1345510113603_796245_13836 extends DurativeEvent {

        public Parameter< ObjectFlow<LADWP> > sig_17_0_5_edc0357_1345510114713_836896_14943 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_5_edc0357_1345510114714_123439_14955 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_5_edc0357_1345510114711_993419_14925 = null;

        public Parameter< ObjectFlow<LADWP> > sig_17_0_5_edc0357_1345510114713_927688_14938 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_5_edc0357_1345510114712_230967_14928 = null;

        public Parameter< ObjectFlow<LADWP> > sig_17_0_5_edc0357_1345510114716_642816_14967 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_5_edc0357_1345510114712_959438_14929 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_5_edc0357_1345510114714_694491_14954 = null;

        public Parameter< ObjectFlow<LADWP> > sig_17_0_5_edc0357_1345510114713_908211_14937 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_5_edc0357_1345510114715_405387_14958 = null;

        public Parameter< ObjectFlow<LADWP> > sig_17_0_5_edc0357_1345510114713_823361_14936 = null;

        public Parameter< ObjectFlow<LADWP> > sig_17_0_5_edc0357_1345510114713_504410_14939 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_5_edc0357_1345510114712_662975_14930 = null;

        public Parameter< ObjectFlow<LADWP> > sig_17_0_5_edc0357_1345510114713_433084_14940 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_5_edc0357_1345510114715_89246_14963 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_5_edc0357_1345510114712_405485_14932 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_5_edc0357_1345510114715_649476_14956 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_5_edc0357_1345510114715_444775_14957 = null;

        public Parameter< ObjectFlow<LADWP> > sig_17_0_5_edc0357_1345510114713_115246_14941 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_5_edc0357_1345510114714_139055_14953 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_5_edc0357_1345510114715_582865_14966 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_5_edc0357_1345510114712_394314_14933 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_5_edc0357_1345510114715_883477_14965 = null;

        public IntegerParameter invoke_time = null;

        public Parameter< ObjectFlow<LADWP> > sig_17_0_5_edc0357_1345510114713_943749_14942 = null;

        public Parameter< ObjectFlow<LADWP> > sig_17_0_5_edc0357_1345510114715_620321_14961 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_5_edc0357_1345510114716_469957_14969 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_5_edc0357_1345510114715_590269_14959 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_5_edc0357_1345510114712_343415_14926 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_5_edc0357_1345510114712_148053_14927 = null;

        public ElaborationRule elaborationRule470 = null;

        public void init_17_0_5_edc0357_1345510113603_796245_13836Members() {
            try {
                sig_17_0_5_edc0357_1345510114713_836896_14943 = new Parameter<ObjectFlow<LADWP>>("sig_17_0_5_edc0357_1345510114713_836896_14943", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114713_836896_14943"), this);
                sig_17_0_5_edc0357_1345510114714_123439_14955 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_5_edc0357_1345510114714_123439_14955", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114714_123439_14955"), this);
                sig_17_0_5_edc0357_1345510114711_993419_14925 = new Parameter<ObjectFlow<Integer>>("sig_17_0_5_edc0357_1345510114711_993419_14925", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114711_993419_14925"), this);
                sig_17_0_5_edc0357_1345510114713_927688_14938 = new Parameter<ObjectFlow<LADWP>>("sig_17_0_5_edc0357_1345510114713_927688_14938", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114713_927688_14938"), this);
                sig_17_0_5_edc0357_1345510114712_230967_14928 = new Parameter<ObjectFlow<Integer>>("sig_17_0_5_edc0357_1345510114712_230967_14928", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114712_230967_14928"), this);
                sig_17_0_5_edc0357_1345510114716_642816_14967 = new Parameter<ObjectFlow<LADWP>>("sig_17_0_5_edc0357_1345510114716_642816_14967", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114716_642816_14967"), this);
                sig_17_0_5_edc0357_1345510114712_959438_14929 = new Parameter<ObjectFlow<Integer>>("sig_17_0_5_edc0357_1345510114712_959438_14929", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114712_959438_14929"), this);
                sig_17_0_5_edc0357_1345510114714_694491_14954 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_5_edc0357_1345510114714_694491_14954", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114714_694491_14954"), this);
                sig_17_0_5_edc0357_1345510114713_908211_14937 = new Parameter<ObjectFlow<LADWP>>("sig_17_0_5_edc0357_1345510114713_908211_14937", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114713_908211_14937"), this);
                sig_17_0_5_edc0357_1345510114715_405387_14958 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_5_edc0357_1345510114715_405387_14958", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114715_405387_14958"), this);
                sig_17_0_5_edc0357_1345510114713_823361_14936 = new Parameter<ObjectFlow<LADWP>>("sig_17_0_5_edc0357_1345510114713_823361_14936", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114713_823361_14936"), this);
                sig_17_0_5_edc0357_1345510114713_504410_14939 = new Parameter<ObjectFlow<LADWP>>("sig_17_0_5_edc0357_1345510114713_504410_14939", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114713_504410_14939"), this);
                sig_17_0_5_edc0357_1345510114712_662975_14930 = new Parameter<ObjectFlow<Integer>>("sig_17_0_5_edc0357_1345510114712_662975_14930", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114712_662975_14930"), this);
                sig_17_0_5_edc0357_1345510114713_433084_14940 = new Parameter<ObjectFlow<LADWP>>("sig_17_0_5_edc0357_1345510114713_433084_14940", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114713_433084_14940"), this);
                sig_17_0_5_edc0357_1345510114715_89246_14963 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_5_edc0357_1345510114715_89246_14963", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114715_89246_14963"), this);
                sig_17_0_5_edc0357_1345510114712_405485_14932 = new Parameter<ObjectFlow<Integer>>("sig_17_0_5_edc0357_1345510114712_405485_14932", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114712_405485_14932"), this);
                sig_17_0_5_edc0357_1345510114715_649476_14956 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_5_edc0357_1345510114715_649476_14956", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114715_649476_14956"), this);
                sig_17_0_5_edc0357_1345510114715_444775_14957 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_5_edc0357_1345510114715_444775_14957", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114715_444775_14957"), this);
                sig_17_0_5_edc0357_1345510114713_115246_14941 = new Parameter<ObjectFlow<LADWP>>("sig_17_0_5_edc0357_1345510114713_115246_14941", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114713_115246_14941"), this);
                sig_17_0_5_edc0357_1345510114714_139055_14953 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_5_edc0357_1345510114714_139055_14953", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114714_139055_14953"), this);
                sig_17_0_5_edc0357_1345510114715_582865_14966 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_5_edc0357_1345510114715_582865_14966", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114715_582865_14966"), this);
                sig_17_0_5_edc0357_1345510114712_394314_14933 = new Parameter<ObjectFlow<Integer>>("sig_17_0_5_edc0357_1345510114712_394314_14933", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114712_394314_14933"), this);
                sig_17_0_5_edc0357_1345510114715_883477_14965 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_5_edc0357_1345510114715_883477_14965", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114715_883477_14965"), this);
                invoke_time = new IntegerParameter("invoke_time", this);
                sig_17_0_5_edc0357_1345510114713_943749_14942 = new Parameter<ObjectFlow<LADWP>>("sig_17_0_5_edc0357_1345510114713_943749_14942", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114713_943749_14942"), this);
                sig_17_0_5_edc0357_1345510114715_620321_14961 = new Parameter<ObjectFlow<LADWP>>("sig_17_0_5_edc0357_1345510114715_620321_14961", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114715_620321_14961"), this);
                sig_17_0_5_edc0357_1345510114716_469957_14969 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_5_edc0357_1345510114716_469957_14969", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114716_469957_14969"), this);
                sig_17_0_5_edc0357_1345510114715_590269_14959 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_5_edc0357_1345510114715_590269_14959", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114715_590269_14959"), this);
                sig_17_0_5_edc0357_1345510114712_343415_14926 = new Parameter<ObjectFlow<Integer>>("sig_17_0_5_edc0357_1345510114712_343415_14926", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114712_343415_14926"), this);
                sig_17_0_5_edc0357_1345510114712_148053_14927 = new Parameter<ObjectFlow<Integer>>("sig_17_0_5_edc0357_1345510114712_148053_14927", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114712_148053_14927"), this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void init_17_0_5_edc0357_1345510113603_796245_13836Collections() {
            parameters.add(sig_17_0_5_edc0357_1345510114713_836896_14943);
            parameters.add(sig_17_0_5_edc0357_1345510114714_123439_14955);
            parameters.add(sig_17_0_5_edc0357_1345510114711_993419_14925);
            parameters.add(sig_17_0_5_edc0357_1345510114713_927688_14938);
            parameters.add(sig_17_0_5_edc0357_1345510114712_230967_14928);
            parameters.add(sig_17_0_5_edc0357_1345510114716_642816_14967);
            parameters.add(sig_17_0_5_edc0357_1345510114712_959438_14929);
            parameters.add(sig_17_0_5_edc0357_1345510114714_694491_14954);
            parameters.add(sig_17_0_5_edc0357_1345510114713_908211_14937);
            parameters.add(sig_17_0_5_edc0357_1345510114715_405387_14958);
            parameters.add(sig_17_0_5_edc0357_1345510114713_823361_14936);
            parameters.add(sig_17_0_5_edc0357_1345510114713_504410_14939);
            parameters.add(sig_17_0_5_edc0357_1345510114712_662975_14930);
            parameters.add(sig_17_0_5_edc0357_1345510114713_433084_14940);
            parameters.add(sig_17_0_5_edc0357_1345510114715_89246_14963);
            parameters.add(sig_17_0_5_edc0357_1345510114712_405485_14932);
            parameters.add(sig_17_0_5_edc0357_1345510114715_649476_14956);
            parameters.add(sig_17_0_5_edc0357_1345510114715_444775_14957);
            parameters.add(sig_17_0_5_edc0357_1345510114713_115246_14941);
            parameters.add(sig_17_0_5_edc0357_1345510114714_139055_14953);
            parameters.add(sig_17_0_5_edc0357_1345510114715_582865_14966);
            parameters.add(sig_17_0_5_edc0357_1345510114712_394314_14933);
            parameters.add(sig_17_0_5_edc0357_1345510114715_883477_14965);
            parameters.add(invoke_time);
            parameters.add(sig_17_0_5_edc0357_1345510114713_943749_14942);
            parameters.add(sig_17_0_5_edc0357_1345510114715_620321_14961);
            parameters.add(sig_17_0_5_edc0357_1345510114716_469957_14969);
            parameters.add(sig_17_0_5_edc0357_1345510114715_590269_14959);
            parameters.add(sig_17_0_5_edc0357_1345510114712_343415_14926);
            parameters.add(sig_17_0_5_edc0357_1345510114712_148053_14927);
        }

        public void init_17_0_5_edc0357_1345510113603_796245_13836Elaborations() {
            Expression<?>[] arguments470 = new Expression<?>[1];
            arguments470[0] = new Expression<Integer>(invoke_time);
            Expression<Boolean> condition470 = new Expression<Boolean>(true);
            elaborationRule470 = addElaborationRule(condition470, _17_0_5_edc0357_1345510113603_796245_13836.this, LADWP._17_0_5_edc0357_1345510113603_796245_13836._17_0_5_edc0357_1345510114707_609526_14902.class, "_InitialNode_InitializeLADWP", arguments470);
        }

        public _17_0_5_edc0357_1345510113603_796245_13836() {
            super();
            init_17_0_5_edc0357_1345510113603_796245_13836Members();
            init_17_0_5_edc0357_1345510113603_796245_13836Collections();
            init_17_0_5_edc0357_1345510113603_796245_13836Elaborations();
        }

        public class _17_0_5_edc0357_1345510114707_609526_14902 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1345510114710_630744_14916_exists = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114710_630744_14916_existsDependency = null;

            public ElaborationRule elaborationRule471 = null;

            public void init_17_0_5_edc0357_1345510114707_609526_14902Members() {
                try {
                    _17_0_5_edc0357_1345510114710_630744_14916_exists = new BooleanParameter("_17_0_5_edc0357_1345510114710_630744_14916_exists", this);
                    _17_0_5_edc0357_1345510114710_630744_14916_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114710_630744_14916_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114707_609526_14902Collections() {
                parameters.add(_17_0_5_edc0357_1345510114710_630744_14916_exists);
                dependencies.add(_17_0_5_edc0357_1345510114710_630744_14916_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510114707_609526_14902Elaborations() {
                Expression<?>[] arguments471 = new Expression<?>[1];
                arguments471[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition471 = new Expression<Boolean>(_17_0_5_edc0357_1345510114710_630744_14916_exists);
                elaborationRule471 = addElaborationRule(condition471, _17_0_5_edc0357_1345510113603_796245_13836.this, LADWP._17_0_5_edc0357_1345510113603_796245_13836._17_0_5_edc0357_1345510114710_630744_14916.class, "_ForkNode_InitializeLADWP", arguments471);
            }

            public _17_0_5_edc0357_1345510114707_609526_14902() {
                super();
                init_17_0_5_edc0357_1345510114707_609526_14902Members();
                init_17_0_5_edc0357_1345510114707_609526_14902Collections();
                init_17_0_5_edc0357_1345510114707_609526_14902Elaborations();
            }

            public _17_0_5_edc0357_1345510114707_609526_14902(Expression<Integer> startTime) {
                super();
                init_17_0_5_edc0357_1345510114707_609526_14902Members();
                init_17_0_5_edc0357_1345510114707_609526_14902Collections();
                removeDependency(this.startTime);
                addDependency(this.startTime, startTime);
                init_17_0_5_edc0357_1345510114707_609526_14902Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114707_62220_14903 extends DurativeEvent {

            public Parameter< LADWP > _17_0_5_edc0357_1345510114982_755950_15425 = null;

            public IntegerParameter _17_0_5_edc0357_1345510114710_630744_14916_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1345510114710_727728_14917_exists = null;

            public ConstraintExpression constraint472 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114710_727728_14917_existsDependency = null;

            public ElaborationRule elaborationRule473 = null;

            public void init_17_0_5_edc0357_1345510114707_62220_14903Members() {
                try {
                    _17_0_5_edc0357_1345510114982_755950_15425 = new Parameter<LADWP>("_17_0_5_edc0357_1345510114982_755950_15425", null, LADWP.this, this);
                    _17_0_5_edc0357_1345510114710_630744_14916_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114710_630744_14916_endTime", this);
                    _17_0_5_edc0357_1345510114710_727728_14917_exists = new BooleanParameter("_17_0_5_edc0357_1345510114710_727728_14917_exists", this);
                    constraint472 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114710_630744_14916_endTime)));
                    _17_0_5_edc0357_1345510114710_727728_14917_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114710_727728_14917_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114707_62220_14903Collections() {
                parameters.add(_17_0_5_edc0357_1345510114982_755950_15425);
                parameters.add(_17_0_5_edc0357_1345510114710_630744_14916_endTime);
                parameters.add(_17_0_5_edc0357_1345510114710_727728_14917_exists);
                constraintExpressions.add(constraint472);
                dependencies.add(_17_0_5_edc0357_1345510114710_727728_14917_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510114707_62220_14903Elaborations() {
                Expression<?>[] arguments473 = new Expression<?>[2];
                arguments473[0] = new Expression<Integer>(endTime);
                arguments473[1] = new Expression<LADWP>(_17_0_5_edc0357_1345510114982_755950_15425);
                Expression<Boolean> condition473 = new Expression<Boolean>(_17_0_5_edc0357_1345510114710_727728_14917_exists);
                elaborationRule473 = addElaborationRule(condition473, _17_0_5_edc0357_1345510113603_796245_13836.this, LADWP._17_0_5_edc0357_1345510113603_796245_13836._17_0_5_edc0357_1345510114710_727728_14917.class, "_ForkNode_InitializeLADWP", arguments473);
            }

            public _17_0_5_edc0357_1345510114707_62220_14903() {
                super();
                init_17_0_5_edc0357_1345510114707_62220_14903Members();
                init_17_0_5_edc0357_1345510114707_62220_14903Collections();
                init_17_0_5_edc0357_1345510114707_62220_14903Elaborations();
            }

            public _17_0_5_edc0357_1345510114707_62220_14903(Expression<Integer> _17_0_5_edc0357_1345510114710_630744_14916_endTime) {
                super();
                init_17_0_5_edc0357_1345510114707_62220_14903Members();
                init_17_0_5_edc0357_1345510114707_62220_14903Collections();
                addDependency(this._17_0_5_edc0357_1345510114710_630744_14916_endTime, _17_0_5_edc0357_1345510114710_630744_14916_endTime);
                init_17_0_5_edc0357_1345510114707_62220_14903Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114707_796416_14904 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1345510114983_337424_15426 = null;

            public IntegerParameter _17_0_5_edc0357_1345510114710_630744_14916_endTime = null;

            public Parameter< LADWP > _17_0_5_edc0357_1345510114984_511435_15427 = null;

            public ConstraintExpression constraint474 = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114983_337424_15426Dependency = null;

            public Dependency< LADWP > _17_0_5_edc0357_1345510114984_511435_15427Dependency = null;

            public Effect effect475 = null;

            public Parameter effect475Var = null;

            public Effect effect476 = null;

            public Parameter effect476Var = null;

            public void init_17_0_5_edc0357_1345510114707_796416_14904Members() {
                try {
                    _17_0_5_edc0357_1345510114983_337424_15426 = new IntegerParameter("_17_0_5_edc0357_1345510114983_337424_15426", this);
                    _17_0_5_edc0357_1345510114710_630744_14916_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114710_630744_14916_endTime", this);
                    _17_0_5_edc0357_1345510114984_511435_15427 = new Parameter<LADWP>("_17_0_5_edc0357_1345510114984_511435_15427", null, null, this);
                    constraint474 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114710_630744_14916_endTime)));
                    _17_0_5_edc0357_1345510114983_337424_15426Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114983_337424_15426, new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114712_148053_14927, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1345510114984_511435_15427Dependency = new Dependency<LADWP>(_17_0_5_edc0357_1345510114984_511435_15427, new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114713_115246_14941, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    Object effect475VarV = sig_17_0_5_edc0357_1345510114715_444775_14957;
                    effect475Var = new Parameter("effect475Var", null, null, this);
                    addDependency(effect475Var, new Expression(effect475VarV));
                    effect475 = new EffectFunction(new FunctionCall(effect475Var, Utils.getMethodForArgTypes("ObjectFlow<Boolean>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { true, startTime }));
                    Object effect476VarV = current_margin__17_0_5_edc0357_1345510113615_810190_13850;
                    effect476Var = new Parameter("effect476Var", null, null, this);
                    addDependency(effect476Var, new Expression(effect476VarV));
                    effect476 = new EffectFunction(new FunctionCall(effect476Var, Utils.getMethodForArgTypes("TimeVaryingMap<Integer>", "generated", "setValue", java.lang.Integer.class, java.lang.Object.class), new Object[] { startTime, _17_0_5_edc0357_1345510114983_337424_15426 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114707_796416_14904Collections() {
                parameters.add(_17_0_5_edc0357_1345510114983_337424_15426);
                parameters.add(_17_0_5_edc0357_1345510114710_630744_14916_endTime);
                parameters.add(_17_0_5_edc0357_1345510114984_511435_15427);
                constraintExpressions.add(constraint474);
                dependencies.add(_17_0_5_edc0357_1345510114983_337424_15426Dependency);
                dependencies.add(_17_0_5_edc0357_1345510114984_511435_15427Dependency);
                Set<Effect> effectsForeffect475Var = new HashSet<Effect>();
                effectsForeffect475Var.add(effect475);
                effects.put((Parameter<?>) effect475Var, effectsForeffect475Var);
                Set<Effect> effectsForeffect476Var = new HashSet<Effect>();
                effectsForeffect476Var.add(effect476);
                effects.put((Parameter<?>) effect476Var, effectsForeffect476Var);
            }

            public void init_17_0_5_edc0357_1345510114707_796416_14904Elaborations() {
            }

            public _17_0_5_edc0357_1345510114707_796416_14904() {
                super();
                init_17_0_5_edc0357_1345510114707_796416_14904Members();
                init_17_0_5_edc0357_1345510114707_796416_14904Collections();
                init_17_0_5_edc0357_1345510114707_796416_14904Elaborations();
            }

            public _17_0_5_edc0357_1345510114707_796416_14904(Expression<Integer> _17_0_5_edc0357_1345510114710_630744_14916_endTime) {
                super();
                init_17_0_5_edc0357_1345510114707_796416_14904Members();
                init_17_0_5_edc0357_1345510114707_796416_14904Collections();
                addDependency(this._17_0_5_edc0357_1345510114710_630744_14916_endTime, _17_0_5_edc0357_1345510114710_630744_14916_endTime);
                init_17_0_5_edc0357_1345510114707_796416_14904Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114708_845512_14905 extends DurativeEvent {

            public Parameter< LADWP > _17_0_5_edc0357_1345510114985_383319_15429 = null;

            public IntegerParameter _17_0_5_edc0357_1345510114710_630744_14916_endTime = null;

            public IntegerParameter _17_0_5_edc0357_1345510114984_949321_15428 = null;

            public ConstraintExpression constraint477 = null;

            public Dependency< LADWP > _17_0_5_edc0357_1345510114985_383319_15429Dependency = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114984_949321_15428Dependency = null;

            public Effect effect478 = null;

            public Parameter effect478Var = null;

            public Effect effect479 = null;

            public Parameter effect479Var = null;

            public void init_17_0_5_edc0357_1345510114708_845512_14905Members() {
                try {
                    _17_0_5_edc0357_1345510114985_383319_15429 = new Parameter<LADWP>("_17_0_5_edc0357_1345510114985_383319_15429", null, null, this);
                    _17_0_5_edc0357_1345510114710_630744_14916_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114710_630744_14916_endTime", this);
                    _17_0_5_edc0357_1345510114984_949321_15428 = new IntegerParameter("_17_0_5_edc0357_1345510114984_949321_15428", this);
                    constraint477 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114710_630744_14916_endTime)));
                    _17_0_5_edc0357_1345510114985_383319_15429Dependency = new Dependency<LADWP>(_17_0_5_edc0357_1345510114985_383319_15429, new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114713_433084_14940, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1345510114984_949321_15428Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114984_949321_15428, new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114712_343415_14926, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    Object effect478VarV = sig_17_0_5_edc0357_1345510114715_649476_14956;
                    effect478Var = new Parameter("effect478Var", null, null, this);
                    addDependency(effect478Var, new Expression(effect478VarV));
                    effect478 = new EffectFunction(new FunctionCall(effect478Var, Utils.getMethodForArgTypes("ObjectFlow<Boolean>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { true, startTime }));
                    Object effect479VarV = expected_margin__17_0_5_edc0357_1345510113614_771928_13849;
                    effect479Var = new Parameter("effect479Var", null, null, this);
                    addDependency(effect479Var, new Expression(effect479VarV));
                    effect479 = new EffectFunction(new FunctionCall(effect479Var, Utils.getMethodForArgTypes("TimeVaryingMap<Integer>", "generated", "setValue", java.lang.Integer.class, java.lang.Object.class), new Object[] { startTime, _17_0_5_edc0357_1345510114984_949321_15428 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114708_845512_14905Collections() {
                parameters.add(_17_0_5_edc0357_1345510114985_383319_15429);
                parameters.add(_17_0_5_edc0357_1345510114710_630744_14916_endTime);
                parameters.add(_17_0_5_edc0357_1345510114984_949321_15428);
                constraintExpressions.add(constraint477);
                dependencies.add(_17_0_5_edc0357_1345510114985_383319_15429Dependency);
                dependencies.add(_17_0_5_edc0357_1345510114984_949321_15428Dependency);
                Set<Effect> effectsForeffect478Var = new HashSet<Effect>();
                effectsForeffect478Var.add(effect478);
                effects.put((Parameter<?>) effect478Var, effectsForeffect478Var);
                Set<Effect> effectsForeffect479Var = new HashSet<Effect>();
                effectsForeffect479Var.add(effect479);
                effects.put((Parameter<?>) effect479Var, effectsForeffect479Var);
            }

            public void init_17_0_5_edc0357_1345510114708_845512_14905Elaborations() {
            }

            public _17_0_5_edc0357_1345510114708_845512_14905() {
                super();
                init_17_0_5_edc0357_1345510114708_845512_14905Members();
                init_17_0_5_edc0357_1345510114708_845512_14905Collections();
                init_17_0_5_edc0357_1345510114708_845512_14905Elaborations();
            }

            public _17_0_5_edc0357_1345510114708_845512_14905(Expression<Integer> _17_0_5_edc0357_1345510114710_630744_14916_endTime) {
                super();
                init_17_0_5_edc0357_1345510114708_845512_14905Members();
                init_17_0_5_edc0357_1345510114708_845512_14905Collections();
                addDependency(this._17_0_5_edc0357_1345510114710_630744_14916_endTime, _17_0_5_edc0357_1345510114710_630744_14916_endTime);
                init_17_0_5_edc0357_1345510114708_845512_14905Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114708_795071_14906 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1345510114710_630744_14916_endTime = null;

            public IntegerParameter _17_0_5_edc0357_1345510114986_756803_15430 = null;

            public Parameter< LADWP > _17_0_5_edc0357_1345510114986_143356_15431 = null;

            public ConstraintExpression constraint480 = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114986_756803_15430Dependency = null;

            public Dependency< LADWP > _17_0_5_edc0357_1345510114986_143356_15431Dependency = null;

            public Effect effect481 = null;

            public Parameter effect481Var = null;

            public Effect effect482 = null;

            public Parameter effect482Var = null;

            public void init_17_0_5_edc0357_1345510114708_795071_14906Members() {
                try {
                    _17_0_5_edc0357_1345510114710_630744_14916_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114710_630744_14916_endTime", this);
                    _17_0_5_edc0357_1345510114986_756803_15430 = new IntegerParameter("_17_0_5_edc0357_1345510114986_756803_15430", this);
                    _17_0_5_edc0357_1345510114986_143356_15431 = new Parameter<LADWP>("_17_0_5_edc0357_1345510114986_143356_15431", null, null, this);
                    constraint480 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114710_630744_14916_endTime)));
                    _17_0_5_edc0357_1345510114986_756803_15430Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114986_756803_15430, new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114712_394314_14933, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1345510114986_143356_15431Dependency = new Dependency<LADWP>(_17_0_5_edc0357_1345510114986_143356_15431, new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114713_943749_14942, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    Object effect481VarV = sig_17_0_5_edc0357_1345510114715_405387_14958;
                    effect481Var = new Parameter("effect481Var", null, null, this);
                    addDependency(effect481Var, new Expression(effect481VarV));
                    effect481 = new EffectFunction(new FunctionCall(effect481Var, Utils.getMethodForArgTypes("ObjectFlow<Boolean>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { true, startTime }));
                    Object effect482VarV = expected_load__17_0_5_edc0357_1345510113611_416109_13845;
                    effect482Var = new Parameter("effect482Var", null, null, this);
                    addDependency(effect482Var, new Expression(effect482VarV));
                    effect482 = new EffectFunction(new FunctionCall(effect482Var, Utils.getMethodForArgTypes("TimeVaryingMap<Integer>", "generated", "setValue", java.lang.Integer.class, java.lang.Object.class), new Object[] { startTime, _17_0_5_edc0357_1345510114986_756803_15430 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114708_795071_14906Collections() {
                parameters.add(_17_0_5_edc0357_1345510114710_630744_14916_endTime);
                parameters.add(_17_0_5_edc0357_1345510114986_756803_15430);
                parameters.add(_17_0_5_edc0357_1345510114986_143356_15431);
                constraintExpressions.add(constraint480);
                dependencies.add(_17_0_5_edc0357_1345510114986_756803_15430Dependency);
                dependencies.add(_17_0_5_edc0357_1345510114986_143356_15431Dependency);
                Set<Effect> effectsForeffect481Var = new HashSet<Effect>();
                effectsForeffect481Var.add(effect481);
                effects.put((Parameter<?>) effect481Var, effectsForeffect481Var);
                Set<Effect> effectsForeffect482Var = new HashSet<Effect>();
                effectsForeffect482Var.add(effect482);
                effects.put((Parameter<?>) effect482Var, effectsForeffect482Var);
            }

            public void init_17_0_5_edc0357_1345510114708_795071_14906Elaborations() {
            }

            public _17_0_5_edc0357_1345510114708_795071_14906() {
                super();
                init_17_0_5_edc0357_1345510114708_795071_14906Members();
                init_17_0_5_edc0357_1345510114708_795071_14906Collections();
                init_17_0_5_edc0357_1345510114708_795071_14906Elaborations();
            }

            public _17_0_5_edc0357_1345510114708_795071_14906(Expression<Integer> _17_0_5_edc0357_1345510114710_630744_14916_endTime) {
                super();
                init_17_0_5_edc0357_1345510114708_795071_14906Members();
                init_17_0_5_edc0357_1345510114708_795071_14906Collections();
                addDependency(this._17_0_5_edc0357_1345510114710_630744_14916_endTime, _17_0_5_edc0357_1345510114710_630744_14916_endTime);
                init_17_0_5_edc0357_1345510114708_795071_14906Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114708_203082_14907 extends DurativeEvent {

            public Parameter< LADWP > _17_0_5_edc0357_1345510114988_339710_15433 = null;

            public IntegerParameter _17_0_5_edc0357_1345510114987_821137_15432 = null;

            public IntegerParameter _17_0_5_edc0357_1345510114710_630744_14916_endTime = null;

            public ConstraintExpression constraint483 = null;

            public Dependency< LADWP > _17_0_5_edc0357_1345510114988_339710_15433Dependency = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114987_821137_15432Dependency = null;

            public Effect effect484 = null;

            public Parameter effect484Var = null;

            public Effect effect485 = null;

            public Parameter effect485Var = null;

            public void init_17_0_5_edc0357_1345510114708_203082_14907Members() {
                try {
                    _17_0_5_edc0357_1345510114988_339710_15433 = new Parameter<LADWP>("_17_0_5_edc0357_1345510114988_339710_15433", null, null, this);
                    _17_0_5_edc0357_1345510114987_821137_15432 = new IntegerParameter("_17_0_5_edc0357_1345510114987_821137_15432", this);
                    _17_0_5_edc0357_1345510114710_630744_14916_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114710_630744_14916_endTime", this);
                    constraint483 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114710_630744_14916_endTime)));
                    _17_0_5_edc0357_1345510114988_339710_15433Dependency = new Dependency<LADWP>(_17_0_5_edc0357_1345510114988_339710_15433, new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114713_908211_14937, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1345510114987_821137_15432Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114987_821137_15432, new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114712_662975_14930, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    Object effect484VarV = sig_17_0_5_edc0357_1345510114714_139055_14953;
                    effect484Var = new Parameter("effect484Var", null, null, this);
                    addDependency(effect484Var, new Expression(effect484VarV));
                    effect484 = new EffectFunction(new FunctionCall(effect484Var, Utils.getMethodForArgTypes("ObjectFlow<Boolean>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { true, startTime }));
                    Object effect485VarV = reported_load__17_0_5_edc0357_1345510113610_466337_13843;
                    effect485Var = new Parameter("effect485Var", null, null, this);
                    addDependency(effect485Var, new Expression(effect485VarV));
                    effect485 = new EffectFunction(new FunctionCall(effect485Var, Utils.getMethodForArgTypes("TimeVaryingMap<Integer>", "generated", "setValue", java.lang.Integer.class, java.lang.Object.class), new Object[] { startTime, _17_0_5_edc0357_1345510114987_821137_15432 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114708_203082_14907Collections() {
                parameters.add(_17_0_5_edc0357_1345510114988_339710_15433);
                parameters.add(_17_0_5_edc0357_1345510114987_821137_15432);
                parameters.add(_17_0_5_edc0357_1345510114710_630744_14916_endTime);
                constraintExpressions.add(constraint483);
                dependencies.add(_17_0_5_edc0357_1345510114988_339710_15433Dependency);
                dependencies.add(_17_0_5_edc0357_1345510114987_821137_15432Dependency);
                Set<Effect> effectsForeffect484Var = new HashSet<Effect>();
                effectsForeffect484Var.add(effect484);
                effects.put((Parameter<?>) effect484Var, effectsForeffect484Var);
                Set<Effect> effectsForeffect485Var = new HashSet<Effect>();
                effectsForeffect485Var.add(effect485);
                effects.put((Parameter<?>) effect485Var, effectsForeffect485Var);
            }

            public void init_17_0_5_edc0357_1345510114708_203082_14907Elaborations() {
            }

            public _17_0_5_edc0357_1345510114708_203082_14907() {
                super();
                init_17_0_5_edc0357_1345510114708_203082_14907Members();
                init_17_0_5_edc0357_1345510114708_203082_14907Collections();
                init_17_0_5_edc0357_1345510114708_203082_14907Elaborations();
            }

            public _17_0_5_edc0357_1345510114708_203082_14907(Expression<Integer> _17_0_5_edc0357_1345510114710_630744_14916_endTime) {
                super();
                init_17_0_5_edc0357_1345510114708_203082_14907Members();
                init_17_0_5_edc0357_1345510114708_203082_14907Collections();
                addDependency(this._17_0_5_edc0357_1345510114710_630744_14916_endTime, _17_0_5_edc0357_1345510114710_630744_14916_endTime);
                init_17_0_5_edc0357_1345510114708_203082_14907Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114708_870746_14908 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1345510114988_614192_15434 = null;

            public Parameter< LADWP > _17_0_5_edc0357_1345510114989_580516_15435 = null;

            public IntegerParameter _17_0_5_edc0357_1345510114710_630744_14916_endTime = null;

            public ConstraintExpression constraint486 = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114988_614192_15434Dependency = null;

            public Dependency< LADWP > _17_0_5_edc0357_1345510114989_580516_15435Dependency = null;

            public Effect effect487 = null;

            public Parameter effect487Var = null;

            public Effect effect488 = null;

            public Parameter effect488Var = null;

            public void init_17_0_5_edc0357_1345510114708_870746_14908Members() {
                try {
                    _17_0_5_edc0357_1345510114988_614192_15434 = new IntegerParameter("_17_0_5_edc0357_1345510114988_614192_15434", this);
                    _17_0_5_edc0357_1345510114989_580516_15435 = new Parameter<LADWP>("_17_0_5_edc0357_1345510114989_580516_15435", null, null, this);
                    _17_0_5_edc0357_1345510114710_630744_14916_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114710_630744_14916_endTime", this);
                    constraint486 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114710_630744_14916_endTime)));
                    _17_0_5_edc0357_1345510114988_614192_15434Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114988_614192_15434, new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114711_993419_14925, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1345510114989_580516_15435Dependency = new Dependency<LADWP>(_17_0_5_edc0357_1345510114989_580516_15435, new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114713_504410_14939, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    Object effect487VarV = sig_17_0_5_edc0357_1345510114714_123439_14955;
                    effect487Var = new Parameter("effect487Var", null, null, this);
                    addDependency(effect487Var, new Expression(effect487VarV));
                    effect487 = new EffectFunction(new FunctionCall(effect487Var, Utils.getMethodForArgTypes("ObjectFlow<Boolean>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { true, startTime }));
                    Object effect488VarV = reported_generation__17_0_5_edc0357_1345510113612_851613_13846;
                    effect488Var = new Parameter("effect488Var", null, null, this);
                    addDependency(effect488Var, new Expression(effect488VarV));
                    effect488 = new EffectFunction(new FunctionCall(effect488Var, Utils.getMethodForArgTypes("TimeVaryingMap<Integer>", "generated", "setValue", java.lang.Integer.class, java.lang.Object.class), new Object[] { startTime, _17_0_5_edc0357_1345510114988_614192_15434 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114708_870746_14908Collections() {
                parameters.add(_17_0_5_edc0357_1345510114988_614192_15434);
                parameters.add(_17_0_5_edc0357_1345510114989_580516_15435);
                parameters.add(_17_0_5_edc0357_1345510114710_630744_14916_endTime);
                constraintExpressions.add(constraint486);
                dependencies.add(_17_0_5_edc0357_1345510114988_614192_15434Dependency);
                dependencies.add(_17_0_5_edc0357_1345510114989_580516_15435Dependency);
                Set<Effect> effectsForeffect487Var = new HashSet<Effect>();
                effectsForeffect487Var.add(effect487);
                effects.put((Parameter<?>) effect487Var, effectsForeffect487Var);
                Set<Effect> effectsForeffect488Var = new HashSet<Effect>();
                effectsForeffect488Var.add(effect488);
                effects.put((Parameter<?>) effect488Var, effectsForeffect488Var);
            }

            public void init_17_0_5_edc0357_1345510114708_870746_14908Elaborations() {
            }

            public _17_0_5_edc0357_1345510114708_870746_14908() {
                super();
                init_17_0_5_edc0357_1345510114708_870746_14908Members();
                init_17_0_5_edc0357_1345510114708_870746_14908Collections();
                init_17_0_5_edc0357_1345510114708_870746_14908Elaborations();
            }

            public _17_0_5_edc0357_1345510114708_870746_14908(Expression<Integer> _17_0_5_edc0357_1345510114710_630744_14916_endTime) {
                super();
                init_17_0_5_edc0357_1345510114708_870746_14908Members();
                init_17_0_5_edc0357_1345510114708_870746_14908Collections();
                addDependency(this._17_0_5_edc0357_1345510114710_630744_14916_endTime, _17_0_5_edc0357_1345510114710_630744_14916_endTime);
                init_17_0_5_edc0357_1345510114708_870746_14908Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114709_602929_14909 extends DurativeEvent {

            public Parameter< LADWP > _17_0_5_edc0357_1345510114990_966028_15437 = null;

            public IntegerParameter _17_0_5_edc0357_1345510114990_167194_15436 = null;

            public IntegerParameter _17_0_5_edc0357_1345510114710_630744_14916_endTime = null;

            public ConstraintExpression constraint489 = null;

            public Dependency< LADWP > _17_0_5_edc0357_1345510114990_966028_15437Dependency = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114990_167194_15436Dependency = null;

            public Effect effect490 = null;

            public Parameter effect490Var = null;

            public Effect effect491 = null;

            public Parameter effect491Var = null;

            public void init_17_0_5_edc0357_1345510114709_602929_14909Members() {
                try {
                    _17_0_5_edc0357_1345510114990_966028_15437 = new Parameter<LADWP>("_17_0_5_edc0357_1345510114990_966028_15437", null, null, this);
                    _17_0_5_edc0357_1345510114990_167194_15436 = new IntegerParameter("_17_0_5_edc0357_1345510114990_167194_15436", this);
                    _17_0_5_edc0357_1345510114710_630744_14916_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114710_630744_14916_endTime", this);
                    constraint489 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114710_630744_14916_endTime)));
                    _17_0_5_edc0357_1345510114990_966028_15437Dependency = new Dependency<LADWP>(_17_0_5_edc0357_1345510114990_966028_15437, new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114713_927688_14938, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1345510114990_167194_15436Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114990_167194_15436, new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114712_230967_14928, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    Object effect490VarV = sig_17_0_5_edc0357_1345510114714_694491_14954;
                    effect490Var = new Parameter("effect490Var", null, null, this);
                    addDependency(effect490Var, new Expression(effect490VarV));
                    effect490 = new EffectFunction(new FunctionCall(effect490Var, Utils.getMethodForArgTypes("ObjectFlow<Boolean>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { true, startTime }));
                    Object effect491VarV = power_needed__17_0_5_edc0357_1345510113610_569612_13844;
                    effect491Var = new Parameter("effect491Var", null, null, this);
                    addDependency(effect491Var, new Expression(effect491VarV));
                    effect491 = new EffectFunction(new FunctionCall(effect491Var, Utils.getMethodForArgTypes("TimeVaryingMap<Integer>", "generated", "setValue", java.lang.Integer.class, java.lang.Object.class), new Object[] { startTime, _17_0_5_edc0357_1345510114990_167194_15436 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114709_602929_14909Collections() {
                parameters.add(_17_0_5_edc0357_1345510114990_966028_15437);
                parameters.add(_17_0_5_edc0357_1345510114990_167194_15436);
                parameters.add(_17_0_5_edc0357_1345510114710_630744_14916_endTime);
                constraintExpressions.add(constraint489);
                dependencies.add(_17_0_5_edc0357_1345510114990_966028_15437Dependency);
                dependencies.add(_17_0_5_edc0357_1345510114990_167194_15436Dependency);
                Set<Effect> effectsForeffect490Var = new HashSet<Effect>();
                effectsForeffect490Var.add(effect490);
                effects.put((Parameter<?>) effect490Var, effectsForeffect490Var);
                Set<Effect> effectsForeffect491Var = new HashSet<Effect>();
                effectsForeffect491Var.add(effect491);
                effects.put((Parameter<?>) effect491Var, effectsForeffect491Var);
            }

            public void init_17_0_5_edc0357_1345510114709_602929_14909Elaborations() {
            }

            public _17_0_5_edc0357_1345510114709_602929_14909() {
                super();
                init_17_0_5_edc0357_1345510114709_602929_14909Members();
                init_17_0_5_edc0357_1345510114709_602929_14909Collections();
                init_17_0_5_edc0357_1345510114709_602929_14909Elaborations();
            }

            public _17_0_5_edc0357_1345510114709_602929_14909(Expression<Integer> _17_0_5_edc0357_1345510114710_630744_14916_endTime) {
                super();
                init_17_0_5_edc0357_1345510114709_602929_14909Members();
                init_17_0_5_edc0357_1345510114709_602929_14909Collections();
                addDependency(this._17_0_5_edc0357_1345510114710_630744_14916_endTime, _17_0_5_edc0357_1345510114710_630744_14916_endTime);
                init_17_0_5_edc0357_1345510114709_602929_14909Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114709_561792_14910 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1345510114991_200706_15438 = null;

            public Parameter< LADWP > _17_0_5_edc0357_1345510114992_216784_15439 = null;

            public IntegerParameter _17_0_5_edc0357_1345510114710_630744_14916_endTime = null;

            public ConstraintExpression constraint492 = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114991_200706_15438Dependency = null;

            public Dependency< LADWP > _17_0_5_edc0357_1345510114992_216784_15439Dependency = null;

            public Effect effect493 = null;

            public Parameter effect493Var = null;

            public Effect effect494 = null;

            public Parameter effect494Var = null;

            public void init_17_0_5_edc0357_1345510114709_561792_14910Members() {
                try {
                    _17_0_5_edc0357_1345510114991_200706_15438 = new IntegerParameter("_17_0_5_edc0357_1345510114991_200706_15438", this);
                    _17_0_5_edc0357_1345510114992_216784_15439 = new Parameter<LADWP>("_17_0_5_edc0357_1345510114992_216784_15439", null, null, this);
                    _17_0_5_edc0357_1345510114710_630744_14916_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114710_630744_14916_endTime", this);
                    constraint492 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114710_630744_14916_endTime)));
                    _17_0_5_edc0357_1345510114991_200706_15438Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114991_200706_15438, new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114712_405485_14932, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1345510114992_216784_15439Dependency = new Dependency<LADWP>(_17_0_5_edc0357_1345510114992_216784_15439, new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114713_836896_14943, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    Object effect493VarV = sig_17_0_5_edc0357_1345510114715_590269_14959;
                    effect493Var = new Parameter("effect493Var", null, null, this);
                    addDependency(effect493Var, new Expression(effect493VarV));
                    effect493 = new EffectFunction(new FunctionCall(effect493Var, Utils.getMethodForArgTypes("ObjectFlow<Boolean>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { true, startTime }));
                    Object effect494VarV = predicted_load__17_0_5_edc0357_1345510113613_464654_13847;
                    effect494Var = new Parameter("effect494Var", null, null, this);
                    addDependency(effect494Var, new Expression(effect494VarV));
                    effect494 = new EffectFunction(new FunctionCall(effect494Var, Utils.getMethodForArgTypes("TimeVaryingMap<Integer>", "generated", "setValue", java.lang.Integer.class, java.lang.Object.class), new Object[] { startTime, _17_0_5_edc0357_1345510114991_200706_15438 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114709_561792_14910Collections() {
                parameters.add(_17_0_5_edc0357_1345510114991_200706_15438);
                parameters.add(_17_0_5_edc0357_1345510114992_216784_15439);
                parameters.add(_17_0_5_edc0357_1345510114710_630744_14916_endTime);
                constraintExpressions.add(constraint492);
                dependencies.add(_17_0_5_edc0357_1345510114991_200706_15438Dependency);
                dependencies.add(_17_0_5_edc0357_1345510114992_216784_15439Dependency);
                Set<Effect> effectsForeffect493Var = new HashSet<Effect>();
                effectsForeffect493Var.add(effect493);
                effects.put((Parameter<?>) effect493Var, effectsForeffect493Var);
                Set<Effect> effectsForeffect494Var = new HashSet<Effect>();
                effectsForeffect494Var.add(effect494);
                effects.put((Parameter<?>) effect494Var, effectsForeffect494Var);
            }

            public void init_17_0_5_edc0357_1345510114709_561792_14910Elaborations() {
            }

            public _17_0_5_edc0357_1345510114709_561792_14910() {
                super();
                init_17_0_5_edc0357_1345510114709_561792_14910Members();
                init_17_0_5_edc0357_1345510114709_561792_14910Collections();
                init_17_0_5_edc0357_1345510114709_561792_14910Elaborations();
            }

            public _17_0_5_edc0357_1345510114709_561792_14910(Expression<Integer> _17_0_5_edc0357_1345510114710_630744_14916_endTime) {
                super();
                init_17_0_5_edc0357_1345510114709_561792_14910Members();
                init_17_0_5_edc0357_1345510114709_561792_14910Collections();
                addDependency(this._17_0_5_edc0357_1345510114710_630744_14916_endTime, _17_0_5_edc0357_1345510114710_630744_14916_endTime);
                init_17_0_5_edc0357_1345510114709_561792_14910Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114709_635836_14911 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1345510114709_107808_14912_exists = null;

            public IntegerParameter _17_0_5_edc0357_1345510114710_630744_14916_endTime = null;

            public IntegerParameter _17_0_5_edc0357_1345510114992_72825_15441 = null;

            public ConstraintExpression constraint495 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114709_107808_14912_existsDependency = null;

            public ElaborationRule elaborationRule496 = null;

            public void init_17_0_5_edc0357_1345510114709_635836_14911Members() {
                try {
                    _17_0_5_edc0357_1345510114709_107808_14912_exists = new BooleanParameter("_17_0_5_edc0357_1345510114709_107808_14912_exists", this);
                    _17_0_5_edc0357_1345510114710_630744_14916_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114710_630744_14916_endTime", this);
                    _17_0_5_edc0357_1345510114992_72825_15441 = new IntegerParameter("_17_0_5_edc0357_1345510114992_72825_15441", this);
                    constraint495 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114710_630744_14916_endTime)));
                    _17_0_5_edc0357_1345510114709_107808_14912_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114709_107808_14912_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114709_635836_14911Collections() {
                parameters.add(_17_0_5_edc0357_1345510114709_107808_14912_exists);
                parameters.add(_17_0_5_edc0357_1345510114710_630744_14916_endTime);
                parameters.add(_17_0_5_edc0357_1345510114992_72825_15441);
                constraintExpressions.add(constraint495);
                dependencies.add(_17_0_5_edc0357_1345510114709_107808_14912_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510114709_635836_14911Elaborations() {
                Expression<?>[] arguments496 = new Expression<?>[2];
                arguments496[0] = new Expression<Integer>(endTime);
                arguments496[1] = new Expression<Integer>(_17_0_5_edc0357_1345510114992_72825_15441);
                Expression<Boolean> condition496 = new Expression<Boolean>(_17_0_5_edc0357_1345510114709_107808_14912_exists);
                elaborationRule496 = addElaborationRule(condition496, _17_0_5_edc0357_1345510113603_796245_13836.this, LADWP._17_0_5_edc0357_1345510113603_796245_13836._17_0_5_edc0357_1345510114709_107808_14912.class, "_ForkNode_InitializeLADWP", arguments496);
            }

            public _17_0_5_edc0357_1345510114709_635836_14911() {
                super();
                init_17_0_5_edc0357_1345510114709_635836_14911Members();
                init_17_0_5_edc0357_1345510114709_635836_14911Collections();
                init_17_0_5_edc0357_1345510114709_635836_14911Elaborations();
            }

            public _17_0_5_edc0357_1345510114709_635836_14911(Expression<Integer> _17_0_5_edc0357_1345510114710_630744_14916_endTime) {
                super();
                init_17_0_5_edc0357_1345510114709_635836_14911Members();
                init_17_0_5_edc0357_1345510114709_635836_14911Collections();
                addDependency(this._17_0_5_edc0357_1345510114710_630744_14916_endTime, _17_0_5_edc0357_1345510114710_630744_14916_endTime);
                init_17_0_5_edc0357_1345510114709_635836_14911Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114709_107808_14912 extends DurativeEvent {

            public IntegerParameter objectToPass = null;

            public IntegerParameter _17_0_5_edc0357_1345510114709_635836_14911_endTime = null;

            public ConstraintExpression constraint497 = null;

            public Effect effect498 = null;

            public Parameter effect498Var = null;

            public Effect effect499 = null;

            public Parameter effect499Var = null;

            public Effect effect500 = null;

            public Parameter effect500Var = null;

            public Effect effect501 = null;

            public Parameter effect501Var = null;

            public Effect effect502 = null;

            public Parameter effect502Var = null;

            public Effect effect503 = null;

            public Parameter effect503Var = null;

            public void init_17_0_5_edc0357_1345510114709_107808_14912Members() {
                try {
                    objectToPass = new IntegerParameter("objectToPass", this);
                    _17_0_5_edc0357_1345510114709_635836_14911_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114709_635836_14911_endTime", this);
                    constraint497 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114709_635836_14911_endTime)));
                    Object effect498VarV = sig_17_0_5_edc0357_1345510114711_993419_14925;
                    effect498Var = new Parameter("effect498Var", null, null, this);
                    addDependency(effect498Var, new Expression(effect498VarV));
                    effect498 = new EffectFunction(new FunctionCall(effect498Var, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                    Object effect499VarV = sig_17_0_5_edc0357_1345510114712_343415_14926;
                    effect499Var = new Parameter("effect499Var", null, null, this);
                    addDependency(effect499Var, new Expression(effect499VarV));
                    effect499 = new EffectFunction(new FunctionCall(effect499Var, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                    Object effect500VarV = sig_17_0_5_edc0357_1345510114712_148053_14927;
                    effect500Var = new Parameter("effect500Var", null, null, this);
                    addDependency(effect500Var, new Expression(effect500VarV));
                    effect500 = new EffectFunction(new FunctionCall(effect500Var, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                    Object effect501VarV = sig_17_0_5_edc0357_1345510114712_230967_14928;
                    effect501Var = new Parameter("effect501Var", null, null, this);
                    addDependency(effect501Var, new Expression(effect501VarV));
                    effect501 = new EffectFunction(new FunctionCall(effect501Var, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                    Object effect502VarV = sig_17_0_5_edc0357_1345510114712_959438_14929;
                    effect502Var = new Parameter("effect502Var", null, null, this);
                    addDependency(effect502Var, new Expression(effect502VarV));
                    effect502 = new EffectFunction(new FunctionCall(effect502Var, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                    Object effect503VarV = sig_17_0_5_edc0357_1345510114712_662975_14930;
                    effect503Var = new Parameter("effect503Var", null, null, this);
                    addDependency(effect503Var, new Expression(effect503VarV));
                    effect503 = new EffectFunction(new FunctionCall(effect503Var, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114709_107808_14912Collections() {
                parameters.add(objectToPass);
                parameters.add(_17_0_5_edc0357_1345510114709_635836_14911_endTime);
                constraintExpressions.add(constraint497);
                Set<Effect> effectsForeffect498Var = new HashSet<Effect>();
                effectsForeffect498Var.add(effect498);
                effects.put((Parameter<?>) effect498Var, effectsForeffect498Var);
                Set<Effect> effectsForeffect499Var = new HashSet<Effect>();
                effectsForeffect499Var.add(effect499);
                effects.put((Parameter<?>) effect499Var, effectsForeffect499Var);
                Set<Effect> effectsForeffect500Var = new HashSet<Effect>();
                effectsForeffect500Var.add(effect500);
                effects.put((Parameter<?>) effect500Var, effectsForeffect500Var);
                Set<Effect> effectsForeffect501Var = new HashSet<Effect>();
                effectsForeffect501Var.add(effect501);
                effects.put((Parameter<?>) effect501Var, effectsForeffect501Var);
                Set<Effect> effectsForeffect502Var = new HashSet<Effect>();
                effectsForeffect502Var.add(effect502);
                effects.put((Parameter<?>) effect502Var, effectsForeffect502Var);
                Set<Effect> effectsForeffect503Var = new HashSet<Effect>();
                effectsForeffect503Var.add(effect503);
                effects.put((Parameter<?>) effect503Var, effectsForeffect503Var);
            }

            public void init_17_0_5_edc0357_1345510114709_107808_14912Elaborations() {
            }

            public _17_0_5_edc0357_1345510114709_107808_14912() {
                super();
                init_17_0_5_edc0357_1345510114709_107808_14912Members();
                init_17_0_5_edc0357_1345510114709_107808_14912Collections();
                init_17_0_5_edc0357_1345510114709_107808_14912Elaborations();
            }

            public _17_0_5_edc0357_1345510114709_107808_14912(Expression<Integer> _17_0_5_edc0357_1345510114709_635836_14911_endTime, Expression<Integer> objectToPass) {
                super();
                init_17_0_5_edc0357_1345510114709_107808_14912Members();
                init_17_0_5_edc0357_1345510114709_107808_14912Collections();
                addDependency(this._17_0_5_edc0357_1345510114709_635836_14911_endTime, _17_0_5_edc0357_1345510114709_635836_14911_endTime);
                addDependency(this.objectToPass, objectToPass);
                init_17_0_5_edc0357_1345510114709_107808_14912Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114709_216705_14913 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1345510114710_541046_14918_exists = null;

            public IntegerParameter _17_0_5_edc0357_1345510114993_45749_15442 = null;

            public IntegerParameter _17_0_5_edc0357_1345510114710_630744_14916_endTime = null;

            public Parameter< LADWP > _17_0_5_edc0357_1345510114994_895167_15443 = null;

            public ConstraintExpression constraint504 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114710_541046_14918_existsDependency = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114993_45749_15442Dependency = null;

            public Dependency< LADWP > _17_0_5_edc0357_1345510114994_895167_15443Dependency = null;

            public Effect effect505 = null;

            public Parameter effect505Var = null;

            public ElaborationRule elaborationRule506 = null;

            public void init_17_0_5_edc0357_1345510114709_216705_14913Members() {
                try {
                    _17_0_5_edc0357_1345510114710_541046_14918_exists = new BooleanParameter("_17_0_5_edc0357_1345510114710_541046_14918_exists", this);
                    _17_0_5_edc0357_1345510114993_45749_15442 = new IntegerParameter("_17_0_5_edc0357_1345510114993_45749_15442", this);
                    _17_0_5_edc0357_1345510114710_630744_14916_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114710_630744_14916_endTime", this);
                    _17_0_5_edc0357_1345510114994_895167_15443 = new Parameter<LADWP>("_17_0_5_edc0357_1345510114994_895167_15443", null, null, this);
                    constraint504 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114710_630744_14916_endTime)));
                    _17_0_5_edc0357_1345510114710_541046_14918_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114710_541046_14918_exists, new Functions.And(new Functions.And(new Functions.And(new Functions.And(new Functions.And(new Functions.And(new Functions.And(new Functions.And(new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114714_139055_14953, Utils.getMethodForArgTypes("ObjectFlow<Boolean>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114714_694491_14954, Utils.getMethodForArgTypes("ObjectFlow<Boolean>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) }))), new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114714_123439_14955, Utils.getMethodForArgTypes("ObjectFlow<Boolean>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) }))), new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114715_649476_14956, Utils.getMethodForArgTypes("ObjectFlow<Boolean>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) }))), new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114715_444775_14957, Utils.getMethodForArgTypes("ObjectFlow<Boolean>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) }))), new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114715_405387_14958, Utils.getMethodForArgTypes("ObjectFlow<Boolean>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) }))), new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114715_590269_14959, Utils.getMethodForArgTypes("ObjectFlow<Boolean>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) }))), new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114715_89246_14963, Utils.getMethodForArgTypes("ObjectFlow<Boolean>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) }))), new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114716_469957_14969, Utils.getMethodForArgTypes("ObjectFlow<Boolean>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) }))));
                    _17_0_5_edc0357_1345510114993_45749_15442Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114993_45749_15442, new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114712_959438_14929, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1345510114994_895167_15443Dependency = new Dependency<LADWP>(_17_0_5_edc0357_1345510114994_895167_15443, new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114713_823361_14936, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    Object effect505VarV = actual_load__17_0_5_edc0357_1345510113609_489064_13842;
                    effect505Var = new Parameter("effect505Var", null, null, this);
                    addDependency(effect505Var, new Expression(effect505VarV));
                    effect505 = new EffectFunction(new FunctionCall(effect505Var, Utils.getMethodForArgTypes("TimeVaryingMap<Integer>", "generated", "setValue", java.lang.Integer.class, java.lang.Object.class), new Object[] { startTime, _17_0_5_edc0357_1345510114993_45749_15442 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114709_216705_14913Collections() {
                parameters.add(_17_0_5_edc0357_1345510114710_541046_14918_exists);
                parameters.add(_17_0_5_edc0357_1345510114993_45749_15442);
                parameters.add(_17_0_5_edc0357_1345510114710_630744_14916_endTime);
                parameters.add(_17_0_5_edc0357_1345510114994_895167_15443);
                constraintExpressions.add(constraint504);
                dependencies.add(_17_0_5_edc0357_1345510114710_541046_14918_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510114993_45749_15442Dependency);
                dependencies.add(_17_0_5_edc0357_1345510114994_895167_15443Dependency);
                Set<Effect> effectsForeffect505Var = new HashSet<Effect>();
                effectsForeffect505Var.add(effect505);
                effects.put((Parameter<?>) effect505Var, effectsForeffect505Var);
            }

            public void init_17_0_5_edc0357_1345510114709_216705_14913Elaborations() {
                Expression<?>[] arguments506 = new Expression<?>[1];
                arguments506[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition506 = new Expression<Boolean>(_17_0_5_edc0357_1345510114710_541046_14918_exists);
                elaborationRule506 = addElaborationRule(condition506, _17_0_5_edc0357_1345510113603_796245_13836.this, LADWP._17_0_5_edc0357_1345510113603_796245_13836._17_0_5_edc0357_1345510114710_541046_14918.class, "_JoinNode_InitializeLADWP", arguments506);
            }

            public _17_0_5_edc0357_1345510114709_216705_14913() {
                super();
                init_17_0_5_edc0357_1345510114709_216705_14913Members();
                init_17_0_5_edc0357_1345510114709_216705_14913Collections();
                init_17_0_5_edc0357_1345510114709_216705_14913Elaborations();
            }

            public _17_0_5_edc0357_1345510114709_216705_14913(Expression<Integer> _17_0_5_edc0357_1345510114710_630744_14916_endTime) {
                super();
                init_17_0_5_edc0357_1345510114709_216705_14913Members();
                init_17_0_5_edc0357_1345510114709_216705_14913Collections();
                addDependency(this._17_0_5_edc0357_1345510114710_630744_14916_endTime, _17_0_5_edc0357_1345510114710_630744_14916_endTime);
                init_17_0_5_edc0357_1345510114709_216705_14913Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114710_121887_14914 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1345510114710_630744_14916_endTime = null;

            public IntegerParameter _17_0_5_edc0357_1345510114994_761954_15445 = null;

            public BooleanParameter _17_0_5_edc0357_1345510114710_54144_14915_exists = null;

            public ConstraintExpression constraint507 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114710_54144_14915_existsDependency = null;

            public ElaborationRule elaborationRule508 = null;

            public void init_17_0_5_edc0357_1345510114710_121887_14914Members() {
                try {
                    _17_0_5_edc0357_1345510114710_630744_14916_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114710_630744_14916_endTime", this);
                    _17_0_5_edc0357_1345510114994_761954_15445 = new IntegerParameter("_17_0_5_edc0357_1345510114994_761954_15445", this);
                    _17_0_5_edc0357_1345510114710_54144_14915_exists = new BooleanParameter("_17_0_5_edc0357_1345510114710_54144_14915_exists", this);
                    constraint507 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114710_630744_14916_endTime)));
                    _17_0_5_edc0357_1345510114710_54144_14915_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114710_54144_14915_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114710_121887_14914Collections() {
                parameters.add(_17_0_5_edc0357_1345510114710_630744_14916_endTime);
                parameters.add(_17_0_5_edc0357_1345510114994_761954_15445);
                parameters.add(_17_0_5_edc0357_1345510114710_54144_14915_exists);
                constraintExpressions.add(constraint507);
                dependencies.add(_17_0_5_edc0357_1345510114710_54144_14915_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510114710_121887_14914Elaborations() {
                Expression<?>[] arguments508 = new Expression<?>[2];
                arguments508[0] = new Expression<Integer>(endTime);
                arguments508[1] = new Expression<Integer>(_17_0_5_edc0357_1345510114994_761954_15445);
                Expression<Boolean> condition508 = new Expression<Boolean>(_17_0_5_edc0357_1345510114710_54144_14915_exists);
                elaborationRule508 = addElaborationRule(condition508, _17_0_5_edc0357_1345510113603_796245_13836.this, LADWP._17_0_5_edc0357_1345510113603_796245_13836._17_0_5_edc0357_1345510114710_54144_14915.class, "_ForkNode_InitializeLADWP", arguments508);
            }

            public _17_0_5_edc0357_1345510114710_121887_14914() {
                super();
                init_17_0_5_edc0357_1345510114710_121887_14914Members();
                init_17_0_5_edc0357_1345510114710_121887_14914Collections();
                init_17_0_5_edc0357_1345510114710_121887_14914Elaborations();
            }

            public _17_0_5_edc0357_1345510114710_121887_14914(Expression<Integer> _17_0_5_edc0357_1345510114710_630744_14916_endTime) {
                super();
                init_17_0_5_edc0357_1345510114710_121887_14914Members();
                init_17_0_5_edc0357_1345510114710_121887_14914Collections();
                addDependency(this._17_0_5_edc0357_1345510114710_630744_14916_endTime, _17_0_5_edc0357_1345510114710_630744_14916_endTime);
                init_17_0_5_edc0357_1345510114710_121887_14914Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114710_54144_14915 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1345510114710_121887_14914_endTime = null;

            public IntegerParameter objectToPass = null;

            public ConstraintExpression constraint509 = null;

            public Effect effect510 = null;

            public Parameter effect510Var = null;

            public Effect effect511 = null;

            public Parameter effect511Var = null;

            public void init_17_0_5_edc0357_1345510114710_54144_14915Members() {
                try {
                    _17_0_5_edc0357_1345510114710_121887_14914_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114710_121887_14914_endTime", this);
                    objectToPass = new IntegerParameter("objectToPass", this);
                    constraint509 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114710_121887_14914_endTime)));
                    Object effect510VarV = sig_17_0_5_edc0357_1345510114712_405485_14932;
                    effect510Var = new Parameter("effect510Var", null, null, this);
                    addDependency(effect510Var, new Expression(effect510VarV));
                    effect510 = new EffectFunction(new FunctionCall(effect510Var, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                    Object effect511VarV = sig_17_0_5_edc0357_1345510114712_394314_14933;
                    effect511Var = new Parameter("effect511Var", null, null, this);
                    addDependency(effect511Var, new Expression(effect511VarV));
                    effect511 = new EffectFunction(new FunctionCall(effect511Var, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114710_54144_14915Collections() {
                parameters.add(_17_0_5_edc0357_1345510114710_121887_14914_endTime);
                parameters.add(objectToPass);
                constraintExpressions.add(constraint509);
                Set<Effect> effectsForeffect510Var = new HashSet<Effect>();
                effectsForeffect510Var.add(effect510);
                effects.put((Parameter<?>) effect510Var, effectsForeffect510Var);
                Set<Effect> effectsForeffect511Var = new HashSet<Effect>();
                effectsForeffect511Var.add(effect511);
                effects.put((Parameter<?>) effect511Var, effectsForeffect511Var);
            }

            public void init_17_0_5_edc0357_1345510114710_54144_14915Elaborations() {
            }

            public _17_0_5_edc0357_1345510114710_54144_14915() {
                super();
                init_17_0_5_edc0357_1345510114710_54144_14915Members();
                init_17_0_5_edc0357_1345510114710_54144_14915Collections();
                init_17_0_5_edc0357_1345510114710_54144_14915Elaborations();
            }

            public _17_0_5_edc0357_1345510114710_54144_14915(Expression<Integer> _17_0_5_edc0357_1345510114710_121887_14914_endTime, Expression<Integer> objectToPass) {
                super();
                init_17_0_5_edc0357_1345510114710_54144_14915Members();
                init_17_0_5_edc0357_1345510114710_54144_14915Collections();
                addDependency(this._17_0_5_edc0357_1345510114710_121887_14914_endTime, _17_0_5_edc0357_1345510114710_121887_14914_endTime);
                addDependency(this.objectToPass, objectToPass);
                init_17_0_5_edc0357_1345510114710_54144_14915Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114710_630744_14916 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1345510114709_602929_14909_exists = null;

            public BooleanParameter _17_0_5_edc0357_1345510114711_949447_14920_exists = null;

            public BooleanParameter _17_0_5_edc0357_1345510114709_635836_14911_exists = null;

            public BooleanParameter _17_0_5_edc0357_1345510114708_795071_14906_exists = null;

            public BooleanParameter _17_0_5_edc0357_1345510114709_561792_14910_exists = null;

            public BooleanParameter _17_0_5_edc0357_1345510114708_870746_14908_exists = null;

            public BooleanParameter _17_0_5_edc0357_1345510114711_88466_14922_exists = null;

            public BooleanParameter _17_0_5_edc0357_1345510114711_700135_14921_exists = null;

            public BooleanParameter _17_0_5_edc0357_1345510114707_796416_14904_exists = null;

            public BooleanParameter _17_0_5_edc0357_1345510114708_845512_14905_exists = null;

            public BooleanParameter _17_0_5_edc0357_1345510114708_203082_14907_exists = null;

            public IntegerParameter _17_0_5_edc0357_1345510114707_609526_14902_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1345510114707_62220_14903_exists = null;

            public BooleanParameter _17_0_5_edc0357_1345510114709_216705_14913_exists = null;

            public BooleanParameter _17_0_5_edc0357_1345510114710_121887_14914_exists = null;

            public ConstraintExpression constraint512 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114709_602929_14909_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114711_949447_14920_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114709_635836_14911_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114708_795071_14906_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114709_561792_14910_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114708_870746_14908_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114711_88466_14922_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114711_700135_14921_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114707_796416_14904_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114708_845512_14905_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114708_203082_14907_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114707_62220_14903_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114709_216705_14913_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114710_121887_14914_existsDependency = null;

            public ElaborationRule elaborationRule513 = null;

            public ElaborationRule elaborationRule514 = null;

            public ElaborationRule elaborationRule515 = null;

            public ElaborationRule elaborationRule516 = null;

            public ElaborationRule elaborationRule517 = null;

            public ElaborationRule elaborationRule518 = null;

            public ElaborationRule elaborationRule519 = null;

            public ElaborationRule elaborationRule520 = null;

            public ElaborationRule elaborationRule521 = null;

            public ElaborationRule elaborationRule522 = null;

            public ElaborationRule elaborationRule523 = null;

            public ElaborationRule elaborationRule524 = null;

            public ElaborationRule elaborationRule525 = null;

            public ElaborationRule elaborationRule526 = null;

            public void init_17_0_5_edc0357_1345510114710_630744_14916Members() {
                try {
                    _17_0_5_edc0357_1345510114709_602929_14909_exists = new BooleanParameter("_17_0_5_edc0357_1345510114709_602929_14909_exists", this);
                    _17_0_5_edc0357_1345510114711_949447_14920_exists = new BooleanParameter("_17_0_5_edc0357_1345510114711_949447_14920_exists", this);
                    _17_0_5_edc0357_1345510114709_635836_14911_exists = new BooleanParameter("_17_0_5_edc0357_1345510114709_635836_14911_exists", this);
                    _17_0_5_edc0357_1345510114708_795071_14906_exists = new BooleanParameter("_17_0_5_edc0357_1345510114708_795071_14906_exists", this);
                    _17_0_5_edc0357_1345510114709_561792_14910_exists = new BooleanParameter("_17_0_5_edc0357_1345510114709_561792_14910_exists", this);
                    _17_0_5_edc0357_1345510114708_870746_14908_exists = new BooleanParameter("_17_0_5_edc0357_1345510114708_870746_14908_exists", this);
                    _17_0_5_edc0357_1345510114711_88466_14922_exists = new BooleanParameter("_17_0_5_edc0357_1345510114711_88466_14922_exists", this);
                    _17_0_5_edc0357_1345510114711_700135_14921_exists = new BooleanParameter("_17_0_5_edc0357_1345510114711_700135_14921_exists", this);
                    _17_0_5_edc0357_1345510114707_796416_14904_exists = new BooleanParameter("_17_0_5_edc0357_1345510114707_796416_14904_exists", this);
                    _17_0_5_edc0357_1345510114708_845512_14905_exists = new BooleanParameter("_17_0_5_edc0357_1345510114708_845512_14905_exists", this);
                    _17_0_5_edc0357_1345510114708_203082_14907_exists = new BooleanParameter("_17_0_5_edc0357_1345510114708_203082_14907_exists", this);
                    _17_0_5_edc0357_1345510114707_609526_14902_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114707_609526_14902_endTime", this);
                    _17_0_5_edc0357_1345510114707_62220_14903_exists = new BooleanParameter("_17_0_5_edc0357_1345510114707_62220_14903_exists", this);
                    _17_0_5_edc0357_1345510114709_216705_14913_exists = new BooleanParameter("_17_0_5_edc0357_1345510114709_216705_14913_exists", this);
                    _17_0_5_edc0357_1345510114710_121887_14914_exists = new BooleanParameter("_17_0_5_edc0357_1345510114710_121887_14914_exists", this);
                    constraint512 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114707_609526_14902_endTime)));
                    _17_0_5_edc0357_1345510114709_602929_14909_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114709_602929_14909_exists, new Functions.And(new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114713_927688_14938, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114712_230967_14928, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) }))));
                    _17_0_5_edc0357_1345510114711_949447_14920_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114711_949447_14920_exists, new Functions.And(new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114715_620321_14961, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114715_883477_14965, Utils.getMethodForArgTypes("ObjectFlow<Boolean>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) }))));
                    _17_0_5_edc0357_1345510114709_635836_14911_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114709_635836_14911_exists, new Expression<Boolean>(true));
                    _17_0_5_edc0357_1345510114708_795071_14906_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114708_795071_14906_exists, new Functions.And(new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114713_943749_14942, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114712_394314_14933, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) }))));
                    _17_0_5_edc0357_1345510114709_561792_14910_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114709_561792_14910_exists, new Functions.And(new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114713_836896_14943, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114712_405485_14932, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) }))));
                    _17_0_5_edc0357_1345510114708_870746_14908_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114708_870746_14908_exists, new Functions.And(new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114713_504410_14939, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114711_993419_14925, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) }))));
                    _17_0_5_edc0357_1345510114711_88466_14922_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114711_88466_14922_exists, new Functions.And(new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114716_642816_14967, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114715_582865_14966, Utils.getMethodForArgTypes("ObjectFlow<Boolean>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) }))));
                    _17_0_5_edc0357_1345510114711_700135_14921_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114711_700135_14921_exists, new Expression<Boolean>(true));
                    _17_0_5_edc0357_1345510114707_796416_14904_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114707_796416_14904_exists, new Functions.And(new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114713_115246_14941, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114712_148053_14927, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) }))));
                    _17_0_5_edc0357_1345510114708_845512_14905_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114708_845512_14905_exists, new Functions.And(new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114713_433084_14940, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114712_343415_14926, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) }))));
                    _17_0_5_edc0357_1345510114708_203082_14907_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114708_203082_14907_exists, new Functions.And(new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114713_908211_14937, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114712_662975_14930, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) }))));
                    _17_0_5_edc0357_1345510114707_62220_14903_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114707_62220_14903_exists, new Expression<Boolean>(true));
                    _17_0_5_edc0357_1345510114709_216705_14913_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114709_216705_14913_exists, new Functions.And(new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114713_823361_14936, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114712_959438_14929, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) }))));
                    _17_0_5_edc0357_1345510114710_121887_14914_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114710_121887_14914_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114710_630744_14916Collections() {
                parameters.add(_17_0_5_edc0357_1345510114709_602929_14909_exists);
                parameters.add(_17_0_5_edc0357_1345510114711_949447_14920_exists);
                parameters.add(_17_0_5_edc0357_1345510114709_635836_14911_exists);
                parameters.add(_17_0_5_edc0357_1345510114708_795071_14906_exists);
                parameters.add(_17_0_5_edc0357_1345510114709_561792_14910_exists);
                parameters.add(_17_0_5_edc0357_1345510114708_870746_14908_exists);
                parameters.add(_17_0_5_edc0357_1345510114711_88466_14922_exists);
                parameters.add(_17_0_5_edc0357_1345510114711_700135_14921_exists);
                parameters.add(_17_0_5_edc0357_1345510114707_796416_14904_exists);
                parameters.add(_17_0_5_edc0357_1345510114708_845512_14905_exists);
                parameters.add(_17_0_5_edc0357_1345510114708_203082_14907_exists);
                parameters.add(_17_0_5_edc0357_1345510114707_609526_14902_endTime);
                parameters.add(_17_0_5_edc0357_1345510114707_62220_14903_exists);
                parameters.add(_17_0_5_edc0357_1345510114709_216705_14913_exists);
                parameters.add(_17_0_5_edc0357_1345510114710_121887_14914_exists);
                constraintExpressions.add(constraint512);
                dependencies.add(_17_0_5_edc0357_1345510114709_602929_14909_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510114711_949447_14920_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510114709_635836_14911_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510114708_795071_14906_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510114709_561792_14910_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510114708_870746_14908_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510114711_88466_14922_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510114711_700135_14921_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510114707_796416_14904_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510114708_845512_14905_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510114708_203082_14907_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510114707_62220_14903_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510114709_216705_14913_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510114710_121887_14914_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510114710_630744_14916Elaborations() {
                Expression<?>[] arguments513 = new Expression<?>[1];
                arguments513[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition513 = new Expression<Boolean>(_17_0_5_edc0357_1345510114707_796416_14904_exists);
                elaborationRule513 = addElaborationRule(condition513, _17_0_5_edc0357_1345510113603_796245_13836.this, LADWP._17_0_5_edc0357_1345510113603_796245_13836._17_0_5_edc0357_1345510114707_796416_14904.class, "_AddStructuralFeatureValueAction_InitializeLADWP", arguments513);
                Expression<?>[] arguments514 = new Expression<?>[1];
                arguments514[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition514 = new Expression<Boolean>(_17_0_5_edc0357_1345510114708_845512_14905_exists);
                elaborationRule514 = addElaborationRule(condition514, _17_0_5_edc0357_1345510113603_796245_13836.this, LADWP._17_0_5_edc0357_1345510113603_796245_13836._17_0_5_edc0357_1345510114708_845512_14905.class, "_AddStructuralFeatureValueAction_InitializeLADWP", arguments514);
                Expression<?>[] arguments515 = new Expression<?>[1];
                arguments515[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition515 = new Expression<Boolean>(_17_0_5_edc0357_1345510114709_635836_14911_exists);
                elaborationRule515 = addElaborationRule(condition515, _17_0_5_edc0357_1345510113603_796245_13836.this, LADWP._17_0_5_edc0357_1345510113603_796245_13836._17_0_5_edc0357_1345510114709_635836_14911.class, "vszero_ValueSpecificationAction_InitializeLADWP", arguments515);
                Expression<?>[] arguments516 = new Expression<?>[1];
                arguments516[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition516 = new Expression<Boolean>(_17_0_5_edc0357_1345510114709_561792_14910_exists);
                elaborationRule516 = addElaborationRule(condition516, _17_0_5_edc0357_1345510113603_796245_13836.this, LADWP._17_0_5_edc0357_1345510113603_796245_13836._17_0_5_edc0357_1345510114709_561792_14910.class, "_AddStructuralFeatureValueAction_InitializeLADWP", arguments516);
                Expression<?>[] arguments517 = new Expression<?>[1];
                arguments517[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition517 = new Expression<Boolean>(_17_0_5_edc0357_1345510114708_870746_14908_exists);
                elaborationRule517 = addElaborationRule(condition517, _17_0_5_edc0357_1345510113603_796245_13836.this, LADWP._17_0_5_edc0357_1345510113603_796245_13836._17_0_5_edc0357_1345510114708_870746_14908.class, "_AddStructuralFeatureValueAction_InitializeLADWP", arguments517);
                Expression<?>[] arguments518 = new Expression<?>[1];
                arguments518[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition518 = new Expression<Boolean>(_17_0_5_edc0357_1345510114711_700135_14921_exists);
                elaborationRule518 = addElaborationRule(condition518, _17_0_5_edc0357_1345510113603_796245_13836.this, LADWP._17_0_5_edc0357_1345510113603_796245_13836._17_0_5_edc0357_1345510114711_700135_14921.class, "_ValueSpecificationAction_InitializeLADWP", arguments518);
                Expression<?>[] arguments519 = new Expression<?>[1];
                arguments519[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition519 = new Expression<Boolean>(_17_0_5_edc0357_1345510114710_121887_14914_exists);
                elaborationRule519 = addElaborationRule(condition519, _17_0_5_edc0357_1345510113603_796245_13836.this, LADWP._17_0_5_edc0357_1345510113603_796245_13836._17_0_5_edc0357_1345510114710_121887_14914.class, "vs10_ValueSpecificationAction_InitializeLADWP", arguments519);
                Expression<?>[] arguments520 = new Expression<?>[1];
                arguments520[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition520 = new Expression<Boolean>(_17_0_5_edc0357_1345510114708_795071_14906_exists);
                elaborationRule520 = addElaborationRule(condition520, _17_0_5_edc0357_1345510113603_796245_13836.this, LADWP._17_0_5_edc0357_1345510113603_796245_13836._17_0_5_edc0357_1345510114708_795071_14906.class, "_AddStructuralFeatureValueAction_InitializeLADWP", arguments520);
                Expression<?>[] arguments521 = new Expression<?>[1];
                arguments521[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition521 = new Expression<Boolean>(_17_0_5_edc0357_1345510114711_949447_14920_exists);
                elaborationRule521 = addElaborationRule(condition521, _17_0_5_edc0357_1345510113603_796245_13836.this, LADWP._17_0_5_edc0357_1345510113603_796245_13836._17_0_5_edc0357_1345510114711_949447_14920.class, "_AddStructuralFeatureValueAction_InitializeLADWP", arguments521);
                Expression<?>[] arguments522 = new Expression<?>[1];
                arguments522[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition522 = new Expression<Boolean>(_17_0_5_edc0357_1345510114708_203082_14907_exists);
                elaborationRule522 = addElaborationRule(condition522, _17_0_5_edc0357_1345510113603_796245_13836.this, LADWP._17_0_5_edc0357_1345510113603_796245_13836._17_0_5_edc0357_1345510114708_203082_14907.class, "_AddStructuralFeatureValueAction_InitializeLADWP", arguments522);
                Expression<?>[] arguments523 = new Expression<?>[1];
                arguments523[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition523 = new Expression<Boolean>(_17_0_5_edc0357_1345510114709_216705_14913_exists);
                elaborationRule523 = addElaborationRule(condition523, _17_0_5_edc0357_1345510113603_796245_13836.this, LADWP._17_0_5_edc0357_1345510113603_796245_13836._17_0_5_edc0357_1345510114709_216705_14913.class, "_AddStructuralFeatureValueAction_InitializeLADWP", arguments523);
                Expression<?>[] arguments524 = new Expression<?>[1];
                arguments524[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition524 = new Expression<Boolean>(_17_0_5_edc0357_1345510114707_62220_14903_exists);
                elaborationRule524 = addElaborationRule(condition524, _17_0_5_edc0357_1345510113603_796245_13836.this, LADWP._17_0_5_edc0357_1345510113603_796245_13836._17_0_5_edc0357_1345510114707_62220_14903.class, "_ReadSelfAction_InitializeLADWP", arguments524);
                Expression<?>[] arguments525 = new Expression<?>[1];
                arguments525[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition525 = new Expression<Boolean>(_17_0_5_edc0357_1345510114709_602929_14909_exists);
                elaborationRule525 = addElaborationRule(condition525, _17_0_5_edc0357_1345510113603_796245_13836.this, LADWP._17_0_5_edc0357_1345510113603_796245_13836._17_0_5_edc0357_1345510114709_602929_14909.class, "_AddStructuralFeatureValueAction_InitializeLADWP", arguments525);
                Expression<?>[] arguments526 = new Expression<?>[1];
                arguments526[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition526 = new Expression<Boolean>(_17_0_5_edc0357_1345510114711_88466_14922_exists);
                elaborationRule526 = addElaborationRule(condition526, _17_0_5_edc0357_1345510113603_796245_13836.this, LADWP._17_0_5_edc0357_1345510113603_796245_13836._17_0_5_edc0357_1345510114711_88466_14922.class, "_AddStructuralFeatureValueAction_InitializeLADWP", arguments526);
            }

            public _17_0_5_edc0357_1345510114710_630744_14916() {
                super();
                init_17_0_5_edc0357_1345510114710_630744_14916Members();
                init_17_0_5_edc0357_1345510114710_630744_14916Collections();
                init_17_0_5_edc0357_1345510114710_630744_14916Elaborations();
            }

            public _17_0_5_edc0357_1345510114710_630744_14916(Expression<Integer> _17_0_5_edc0357_1345510114707_609526_14902_endTime) {
                super();
                init_17_0_5_edc0357_1345510114710_630744_14916Members();
                init_17_0_5_edc0357_1345510114710_630744_14916Collections();
                addDependency(this._17_0_5_edc0357_1345510114707_609526_14902_endTime, _17_0_5_edc0357_1345510114707_609526_14902_endTime);
                init_17_0_5_edc0357_1345510114710_630744_14916Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114710_727728_14917 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1345510114707_62220_14903_endTime = null;

            public Parameter< LADWP > objectToPass = null;

            public ConstraintExpression constraint527 = null;

            public Effect effect528 = null;

            public Parameter effect528Var = null;

            public Effect effect529 = null;

            public Parameter effect529Var = null;

            public Effect effect530 = null;

            public Parameter effect530Var = null;

            public Effect effect531 = null;

            public Parameter effect531Var = null;

            public Effect effect532 = null;

            public Parameter effect532Var = null;

            public Effect effect533 = null;

            public Parameter effect533Var = null;

            public Effect effect534 = null;

            public Parameter effect534Var = null;

            public Effect effect535 = null;

            public Parameter effect535Var = null;

            public Effect effect536 = null;

            public Parameter effect536Var = null;

            public Effect effect537 = null;

            public Parameter effect537Var = null;

            public void init_17_0_5_edc0357_1345510114710_727728_14917Members() {
                try {
                    _17_0_5_edc0357_1345510114707_62220_14903_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114707_62220_14903_endTime", this);
                    objectToPass = new Parameter<LADWP>("objectToPass", null, null, this);
                    constraint527 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114707_62220_14903_endTime)));
                    Object effect528VarV = sig_17_0_5_edc0357_1345510114713_823361_14936;
                    effect528Var = new Parameter("effect528Var", null, null, this);
                    addDependency(effect528Var, new Expression(effect528VarV));
                    effect528 = new EffectFunction(new FunctionCall(effect528Var, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                    Object effect529VarV = sig_17_0_5_edc0357_1345510114713_908211_14937;
                    effect529Var = new Parameter("effect529Var", null, null, this);
                    addDependency(effect529Var, new Expression(effect529VarV));
                    effect529 = new EffectFunction(new FunctionCall(effect529Var, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                    Object effect530VarV = sig_17_0_5_edc0357_1345510114713_927688_14938;
                    effect530Var = new Parameter("effect530Var", null, null, this);
                    addDependency(effect530Var, new Expression(effect530VarV));
                    effect530 = new EffectFunction(new FunctionCall(effect530Var, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                    Object effect531VarV = sig_17_0_5_edc0357_1345510114713_504410_14939;
                    effect531Var = new Parameter("effect531Var", null, null, this);
                    addDependency(effect531Var, new Expression(effect531VarV));
                    effect531 = new EffectFunction(new FunctionCall(effect531Var, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                    Object effect532VarV = sig_17_0_5_edc0357_1345510114713_433084_14940;
                    effect532Var = new Parameter("effect532Var", null, null, this);
                    addDependency(effect532Var, new Expression(effect532VarV));
                    effect532 = new EffectFunction(new FunctionCall(effect532Var, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                    Object effect533VarV = sig_17_0_5_edc0357_1345510114713_115246_14941;
                    effect533Var = new Parameter("effect533Var", null, null, this);
                    addDependency(effect533Var, new Expression(effect533VarV));
                    effect533 = new EffectFunction(new FunctionCall(effect533Var, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                    Object effect534VarV = sig_17_0_5_edc0357_1345510114713_943749_14942;
                    effect534Var = new Parameter("effect534Var", null, null, this);
                    addDependency(effect534Var, new Expression(effect534VarV));
                    effect534 = new EffectFunction(new FunctionCall(effect534Var, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                    Object effect535VarV = sig_17_0_5_edc0357_1345510114713_836896_14943;
                    effect535Var = new Parameter("effect535Var", null, null, this);
                    addDependency(effect535Var, new Expression(effect535VarV));
                    effect535 = new EffectFunction(new FunctionCall(effect535Var, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                    Object effect536VarV = sig_17_0_5_edc0357_1345510114715_620321_14961;
                    effect536Var = new Parameter("effect536Var", null, null, this);
                    addDependency(effect536Var, new Expression(effect536VarV));
                    effect536 = new EffectFunction(new FunctionCall(effect536Var, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                    Object effect537VarV = sig_17_0_5_edc0357_1345510114716_642816_14967;
                    effect537Var = new Parameter("effect537Var", null, null, this);
                    addDependency(effect537Var, new Expression(effect537VarV));
                    effect537 = new EffectFunction(new FunctionCall(effect537Var, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114710_727728_14917Collections() {
                parameters.add(_17_0_5_edc0357_1345510114707_62220_14903_endTime);
                parameters.add(objectToPass);
                constraintExpressions.add(constraint527);
                Set<Effect> effectsForeffect528Var = new HashSet<Effect>();
                effectsForeffect528Var.add(effect528);
                effects.put((Parameter<?>) effect528Var, effectsForeffect528Var);
                Set<Effect> effectsForeffect529Var = new HashSet<Effect>();
                effectsForeffect529Var.add(effect529);
                effects.put((Parameter<?>) effect529Var, effectsForeffect529Var);
                Set<Effect> effectsForeffect530Var = new HashSet<Effect>();
                effectsForeffect530Var.add(effect530);
                effects.put((Parameter<?>) effect530Var, effectsForeffect530Var);
                Set<Effect> effectsForeffect531Var = new HashSet<Effect>();
                effectsForeffect531Var.add(effect531);
                effects.put((Parameter<?>) effect531Var, effectsForeffect531Var);
                Set<Effect> effectsForeffect532Var = new HashSet<Effect>();
                effectsForeffect532Var.add(effect532);
                effects.put((Parameter<?>) effect532Var, effectsForeffect532Var);
                Set<Effect> effectsForeffect533Var = new HashSet<Effect>();
                effectsForeffect533Var.add(effect533);
                effects.put((Parameter<?>) effect533Var, effectsForeffect533Var);
                Set<Effect> effectsForeffect534Var = new HashSet<Effect>();
                effectsForeffect534Var.add(effect534);
                effects.put((Parameter<?>) effect534Var, effectsForeffect534Var);
                Set<Effect> effectsForeffect535Var = new HashSet<Effect>();
                effectsForeffect535Var.add(effect535);
                effects.put((Parameter<?>) effect535Var, effectsForeffect535Var);
                Set<Effect> effectsForeffect536Var = new HashSet<Effect>();
                effectsForeffect536Var.add(effect536);
                effects.put((Parameter<?>) effect536Var, effectsForeffect536Var);
                Set<Effect> effectsForeffect537Var = new HashSet<Effect>();
                effectsForeffect537Var.add(effect537);
                effects.put((Parameter<?>) effect537Var, effectsForeffect537Var);
            }

            public void init_17_0_5_edc0357_1345510114710_727728_14917Elaborations() {
            }

            public _17_0_5_edc0357_1345510114710_727728_14917() {
                super();
                init_17_0_5_edc0357_1345510114710_727728_14917Members();
                init_17_0_5_edc0357_1345510114710_727728_14917Collections();
                init_17_0_5_edc0357_1345510114710_727728_14917Elaborations();
            }

            public _17_0_5_edc0357_1345510114710_727728_14917(Expression<Integer> _17_0_5_edc0357_1345510114707_62220_14903_endTime, Expression<LADWP> objectToPass) {
                super();
                init_17_0_5_edc0357_1345510114710_727728_14917Members();
                init_17_0_5_edc0357_1345510114710_727728_14917Collections();
                addDependency(this._17_0_5_edc0357_1345510114707_62220_14903_endTime, _17_0_5_edc0357_1345510114707_62220_14903_endTime);
                addDependency(this.objectToPass, objectToPass);
                init_17_0_5_edc0357_1345510114710_727728_14917Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114710_541046_14918 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1345510114711_311747_14919_exists = null;

            public IntegerParameter _17_0_5_edc0357_1345510114709_216705_14913_endTime = null;

            public ConstraintExpression constraint538 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114711_311747_14919_existsDependency = null;

            public ElaborationRule elaborationRule539 = null;

            public void init_17_0_5_edc0357_1345510114710_541046_14918Members() {
                try {
                    _17_0_5_edc0357_1345510114711_311747_14919_exists = new BooleanParameter("_17_0_5_edc0357_1345510114711_311747_14919_exists", this);
                    _17_0_5_edc0357_1345510114709_216705_14913_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114709_216705_14913_endTime", this);
                    constraint538 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114709_216705_14913_endTime)));
                    _17_0_5_edc0357_1345510114711_311747_14919_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114711_311747_14919_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114710_541046_14918Collections() {
                parameters.add(_17_0_5_edc0357_1345510114711_311747_14919_exists);
                parameters.add(_17_0_5_edc0357_1345510114709_216705_14913_endTime);
                constraintExpressions.add(constraint538);
                dependencies.add(_17_0_5_edc0357_1345510114711_311747_14919_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510114710_541046_14918Elaborations() {
                Expression<?>[] arguments539 = new Expression<?>[1];
                arguments539[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition539 = new Expression<Boolean>(_17_0_5_edc0357_1345510114711_311747_14919_exists);
                elaborationRule539 = addElaborationRule(condition539, _17_0_5_edc0357_1345510113603_796245_13836.this, LADWP._17_0_5_edc0357_1345510113603_796245_13836._17_0_5_edc0357_1345510114711_311747_14919.class, "_ActivityFinalNode_InitializeLADWP", arguments539);
            }

            public _17_0_5_edc0357_1345510114710_541046_14918() {
                super();
                init_17_0_5_edc0357_1345510114710_541046_14918Members();
                init_17_0_5_edc0357_1345510114710_541046_14918Collections();
                init_17_0_5_edc0357_1345510114710_541046_14918Elaborations();
            }

            public _17_0_5_edc0357_1345510114710_541046_14918(Expression<Integer> _17_0_5_edc0357_1345510114709_216705_14913_endTime) {
                super();
                init_17_0_5_edc0357_1345510114710_541046_14918Members();
                init_17_0_5_edc0357_1345510114710_541046_14918Collections();
                addDependency(this._17_0_5_edc0357_1345510114709_216705_14913_endTime, _17_0_5_edc0357_1345510114709_216705_14913_endTime);
                init_17_0_5_edc0357_1345510114710_541046_14918Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114711_311747_14919 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1345510114710_541046_14918_endTime = null;

            public ConstraintExpression constraint540 = null;

            public void init_17_0_5_edc0357_1345510114711_311747_14919Members() {
                try {
                    _17_0_5_edc0357_1345510114710_541046_14918_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114710_541046_14918_endTime", this);
                    constraint540 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114710_541046_14918_endTime)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114711_311747_14919Collections() {
                parameters.add(_17_0_5_edc0357_1345510114710_541046_14918_endTime);
                constraintExpressions.add(constraint540);
            }

            public void init_17_0_5_edc0357_1345510114711_311747_14919Elaborations() {
            }

            public _17_0_5_edc0357_1345510114711_311747_14919() {
                super();
                init_17_0_5_edc0357_1345510114711_311747_14919Members();
                init_17_0_5_edc0357_1345510114711_311747_14919Collections();
                init_17_0_5_edc0357_1345510114711_311747_14919Elaborations();
            }

            public _17_0_5_edc0357_1345510114711_311747_14919(Expression<Integer> _17_0_5_edc0357_1345510114710_541046_14918_endTime) {
                super();
                init_17_0_5_edc0357_1345510114711_311747_14919Members();
                init_17_0_5_edc0357_1345510114711_311747_14919Collections();
                addDependency(this._17_0_5_edc0357_1345510114710_541046_14918_endTime, _17_0_5_edc0357_1345510114710_541046_14918_endTime);
                init_17_0_5_edc0357_1345510114711_311747_14919Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114711_949447_14920 extends DurativeEvent {

            public Parameter< LADWP > _17_0_5_edc0357_1345510114996_973134_15447 = null;

            public IntegerParameter _17_0_5_edc0357_1345510114710_630744_14916_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1345510114995_46510_15446 = null;

            public ConstraintExpression constraint541 = null;

            public Dependency< LADWP > _17_0_5_edc0357_1345510114996_973134_15447Dependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114995_46510_15446Dependency = null;

            public Effect effect542 = null;

            public Parameter effect542Var = null;

            public Effect effect543 = null;

            public Parameter effect543Var = null;

            public void init_17_0_5_edc0357_1345510114711_949447_14920Members() {
                try {
                    _17_0_5_edc0357_1345510114996_973134_15447 = new Parameter<LADWP>("_17_0_5_edc0357_1345510114996_973134_15447", null, null, this);
                    _17_0_5_edc0357_1345510114710_630744_14916_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114710_630744_14916_endTime", this);
                    _17_0_5_edc0357_1345510114995_46510_15446 = new BooleanParameter("_17_0_5_edc0357_1345510114995_46510_15446", this);
                    constraint541 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114710_630744_14916_endTime)));
                    _17_0_5_edc0357_1345510114996_973134_15447Dependency = new Dependency<LADWP>(_17_0_5_edc0357_1345510114996_973134_15447, new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114715_620321_14961, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1345510114995_46510_15446Dependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114995_46510_15446, new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114715_883477_14965, Utils.getMethodForArgTypes("ObjectFlow<Boolean>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    Object effect542VarV = sig_17_0_5_edc0357_1345510114715_89246_14963;
                    effect542Var = new Parameter("effect542Var", null, null, this);
                    addDependency(effect542Var, new Expression(effect542VarV));
                    effect542 = new EffectFunction(new FunctionCall(effect542Var, Utils.getMethodForArgTypes("ObjectFlow<Boolean>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { true, startTime }));
                    Object effect543VarV = demandResponse__17_0_5_edc0357_1345510113615_857599_13851;
                    effect543Var = new Parameter("effect543Var", null, null, this);
                    addDependency(effect543Var, new Expression(effect543VarV));
                    effect543 = new EffectFunction(new FunctionCall(effect543Var, Utils.getMethodForArgTypes("TimeVaryingMap<Boolean>", "generated", "setValue", java.lang.Integer.class, java.lang.Object.class), new Object[] { startTime, _17_0_5_edc0357_1345510114995_46510_15446 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114711_949447_14920Collections() {
                parameters.add(_17_0_5_edc0357_1345510114996_973134_15447);
                parameters.add(_17_0_5_edc0357_1345510114710_630744_14916_endTime);
                parameters.add(_17_0_5_edc0357_1345510114995_46510_15446);
                constraintExpressions.add(constraint541);
                dependencies.add(_17_0_5_edc0357_1345510114996_973134_15447Dependency);
                dependencies.add(_17_0_5_edc0357_1345510114995_46510_15446Dependency);
                Set<Effect> effectsForeffect542Var = new HashSet<Effect>();
                effectsForeffect542Var.add(effect542);
                effects.put((Parameter<?>) effect542Var, effectsForeffect542Var);
                Set<Effect> effectsForeffect543Var = new HashSet<Effect>();
                effectsForeffect543Var.add(effect543);
                effects.put((Parameter<?>) effect543Var, effectsForeffect543Var);
            }

            public void init_17_0_5_edc0357_1345510114711_949447_14920Elaborations() {
            }

            public _17_0_5_edc0357_1345510114711_949447_14920() {
                super();
                init_17_0_5_edc0357_1345510114711_949447_14920Members();
                init_17_0_5_edc0357_1345510114711_949447_14920Collections();
                init_17_0_5_edc0357_1345510114711_949447_14920Elaborations();
            }

            public _17_0_5_edc0357_1345510114711_949447_14920(Expression<Integer> _17_0_5_edc0357_1345510114710_630744_14916_endTime) {
                super();
                init_17_0_5_edc0357_1345510114711_949447_14920Members();
                init_17_0_5_edc0357_1345510114711_949447_14920Collections();
                addDependency(this._17_0_5_edc0357_1345510114710_630744_14916_endTime, _17_0_5_edc0357_1345510114710_630744_14916_endTime);
                init_17_0_5_edc0357_1345510114711_949447_14920Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114711_700135_14921 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1345510114711_155853_14923_exists = null;

            public IntegerParameter _17_0_5_edc0357_1345510114710_630744_14916_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1345510114997_841873_15449 = null;

            public ConstraintExpression constraint544 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114711_155853_14923_existsDependency = null;

            public ElaborationRule elaborationRule545 = null;

            public void init_17_0_5_edc0357_1345510114711_700135_14921Members() {
                try {
                    _17_0_5_edc0357_1345510114711_155853_14923_exists = new BooleanParameter("_17_0_5_edc0357_1345510114711_155853_14923_exists", this);
                    _17_0_5_edc0357_1345510114710_630744_14916_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114710_630744_14916_endTime", this);
                    _17_0_5_edc0357_1345510114997_841873_15449 = new BooleanParameter("_17_0_5_edc0357_1345510114997_841873_15449", this);
                    constraint544 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114710_630744_14916_endTime)));
                    _17_0_5_edc0357_1345510114711_155853_14923_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114711_155853_14923_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114711_700135_14921Collections() {
                parameters.add(_17_0_5_edc0357_1345510114711_155853_14923_exists);
                parameters.add(_17_0_5_edc0357_1345510114710_630744_14916_endTime);
                parameters.add(_17_0_5_edc0357_1345510114997_841873_15449);
                constraintExpressions.add(constraint544);
                dependencies.add(_17_0_5_edc0357_1345510114711_155853_14923_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510114711_700135_14921Elaborations() {
                Expression<?>[] arguments545 = new Expression<?>[2];
                arguments545[0] = new Expression<Integer>(endTime);
                arguments545[1] = new Expression<Boolean>(_17_0_5_edc0357_1345510114997_841873_15449);
                Expression<Boolean> condition545 = new Expression<Boolean>(_17_0_5_edc0357_1345510114711_155853_14923_exists);
                elaborationRule545 = addElaborationRule(condition545, _17_0_5_edc0357_1345510113603_796245_13836.this, LADWP._17_0_5_edc0357_1345510113603_796245_13836._17_0_5_edc0357_1345510114711_155853_14923.class, "_ForkNode_InitializeLADWP", arguments545);
            }

            public _17_0_5_edc0357_1345510114711_700135_14921() {
                super();
                init_17_0_5_edc0357_1345510114711_700135_14921Members();
                init_17_0_5_edc0357_1345510114711_700135_14921Collections();
                init_17_0_5_edc0357_1345510114711_700135_14921Elaborations();
            }

            public _17_0_5_edc0357_1345510114711_700135_14921(Expression<Integer> _17_0_5_edc0357_1345510114710_630744_14916_endTime) {
                super();
                init_17_0_5_edc0357_1345510114711_700135_14921Members();
                init_17_0_5_edc0357_1345510114711_700135_14921Collections();
                addDependency(this._17_0_5_edc0357_1345510114710_630744_14916_endTime, _17_0_5_edc0357_1345510114710_630744_14916_endTime);
                init_17_0_5_edc0357_1345510114711_700135_14921Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114711_88466_14922 extends DurativeEvent {

            public Parameter< LADWP > _17_0_5_edc0357_1345510114998_119629_15451 = null;

            public BooleanParameter _17_0_5_edc0357_1345510114997_709943_15450 = null;

            public IntegerParameter _17_0_5_edc0357_1345510114710_630744_14916_endTime = null;

            public ConstraintExpression constraint546 = null;

            public Dependency< LADWP > _17_0_5_edc0357_1345510114998_119629_15451Dependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114997_709943_15450Dependency = null;

            public Effect effect547 = null;

            public Parameter effect547Var = null;

            public Effect effect548 = null;

            public Parameter effect548Var = null;

            public void init_17_0_5_edc0357_1345510114711_88466_14922Members() {
                try {
                    _17_0_5_edc0357_1345510114998_119629_15451 = new Parameter<LADWP>("_17_0_5_edc0357_1345510114998_119629_15451", null, null, this);
                    _17_0_5_edc0357_1345510114997_709943_15450 = new BooleanParameter("_17_0_5_edc0357_1345510114997_709943_15450", this);
                    _17_0_5_edc0357_1345510114710_630744_14916_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114710_630744_14916_endTime", this);
                    constraint546 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114710_630744_14916_endTime)));
                    _17_0_5_edc0357_1345510114998_119629_15451Dependency = new Dependency<LADWP>(_17_0_5_edc0357_1345510114998_119629_15451, new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114716_642816_14967, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1345510114997_709943_15450Dependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114997_709943_15450, new Expression(new FunctionCall(sig_17_0_5_edc0357_1345510114715_582865_14966, Utils.getMethodForArgTypes("ObjectFlow<Boolean>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    Object effect547VarV = sig_17_0_5_edc0357_1345510114716_469957_14969;
                    effect547Var = new Parameter("effect547Var", null, null, this);
                    addDependency(effect547Var, new Expression(effect547VarV));
                    effect547 = new EffectFunction(new FunctionCall(effect547Var, Utils.getMethodForArgTypes("ObjectFlow<Boolean>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { true, startTime }));
                    Object effect548VarV = shortage__17_0_5_edc0357_1345510113616_127059_13852;
                    effect548Var = new Parameter("effect548Var", null, null, this);
                    addDependency(effect548Var, new Expression(effect548VarV));
                    effect548 = new EffectFunction(new FunctionCall(effect548Var, Utils.getMethodForArgTypes("TimeVaryingMap<Boolean>", "generated", "setValue", java.lang.Integer.class, java.lang.Object.class), new Object[] { startTime, _17_0_5_edc0357_1345510114997_709943_15450 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114711_88466_14922Collections() {
                parameters.add(_17_0_5_edc0357_1345510114998_119629_15451);
                parameters.add(_17_0_5_edc0357_1345510114997_709943_15450);
                parameters.add(_17_0_5_edc0357_1345510114710_630744_14916_endTime);
                constraintExpressions.add(constraint546);
                dependencies.add(_17_0_5_edc0357_1345510114998_119629_15451Dependency);
                dependencies.add(_17_0_5_edc0357_1345510114997_709943_15450Dependency);
                Set<Effect> effectsForeffect547Var = new HashSet<Effect>();
                effectsForeffect547Var.add(effect547);
                effects.put((Parameter<?>) effect547Var, effectsForeffect547Var);
                Set<Effect> effectsForeffect548Var = new HashSet<Effect>();
                effectsForeffect548Var.add(effect548);
                effects.put((Parameter<?>) effect548Var, effectsForeffect548Var);
            }

            public void init_17_0_5_edc0357_1345510114711_88466_14922Elaborations() {
            }

            public _17_0_5_edc0357_1345510114711_88466_14922() {
                super();
                init_17_0_5_edc0357_1345510114711_88466_14922Members();
                init_17_0_5_edc0357_1345510114711_88466_14922Collections();
                init_17_0_5_edc0357_1345510114711_88466_14922Elaborations();
            }

            public _17_0_5_edc0357_1345510114711_88466_14922(Expression<Integer> _17_0_5_edc0357_1345510114710_630744_14916_endTime) {
                super();
                init_17_0_5_edc0357_1345510114711_88466_14922Members();
                init_17_0_5_edc0357_1345510114711_88466_14922Collections();
                addDependency(this._17_0_5_edc0357_1345510114710_630744_14916_endTime, _17_0_5_edc0357_1345510114710_630744_14916_endTime);
                init_17_0_5_edc0357_1345510114711_88466_14922Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114711_155853_14923 extends DurativeEvent {

            public BooleanParameter objectToPass = null;

            public IntegerParameter _17_0_5_edc0357_1345510114711_700135_14921_endTime = null;

            public ConstraintExpression constraint549 = null;

            public Effect effect550 = null;

            public Parameter effect550Var = null;

            public Effect effect551 = null;

            public Parameter effect551Var = null;

            public void init_17_0_5_edc0357_1345510114711_155853_14923Members() {
                try {
                    objectToPass = new BooleanParameter("objectToPass", this);
                    _17_0_5_edc0357_1345510114711_700135_14921_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114711_700135_14921_endTime", this);
                    constraint549 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114711_700135_14921_endTime)));
                    Object effect550VarV = sig_17_0_5_edc0357_1345510114715_883477_14965;
                    effect550Var = new Parameter("effect550Var", null, null, this);
                    addDependency(effect550Var, new Expression(effect550VarV));
                    effect550 = new EffectFunction(new FunctionCall(effect550Var, Utils.getMethodForArgTypes("ObjectFlow<Boolean>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                    Object effect551VarV = sig_17_0_5_edc0357_1345510114715_582865_14966;
                    effect551Var = new Parameter("effect551Var", null, null, this);
                    addDependency(effect551Var, new Expression(effect551VarV));
                    effect551 = new EffectFunction(new FunctionCall(effect551Var, Utils.getMethodForArgTypes("ObjectFlow<Boolean>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114711_155853_14923Collections() {
                parameters.add(objectToPass);
                parameters.add(_17_0_5_edc0357_1345510114711_700135_14921_endTime);
                constraintExpressions.add(constraint549);
                Set<Effect> effectsForeffect550Var = new HashSet<Effect>();
                effectsForeffect550Var.add(effect550);
                effects.put((Parameter<?>) effect550Var, effectsForeffect550Var);
                Set<Effect> effectsForeffect551Var = new HashSet<Effect>();
                effectsForeffect551Var.add(effect551);
                effects.put((Parameter<?>) effect551Var, effectsForeffect551Var);
            }

            public void init_17_0_5_edc0357_1345510114711_155853_14923Elaborations() {
            }

            public _17_0_5_edc0357_1345510114711_155853_14923() {
                super();
                init_17_0_5_edc0357_1345510114711_155853_14923Members();
                init_17_0_5_edc0357_1345510114711_155853_14923Collections();
                init_17_0_5_edc0357_1345510114711_155853_14923Elaborations();
            }

            public _17_0_5_edc0357_1345510114711_155853_14923(Expression<Integer> _17_0_5_edc0357_1345510114711_700135_14921_endTime, Expression<Boolean> objectToPass) {
                super();
                init_17_0_5_edc0357_1345510114711_155853_14923Members();
                init_17_0_5_edc0357_1345510114711_155853_14923Collections();
                addDependency(this._17_0_5_edc0357_1345510114711_700135_14921_endTime, _17_0_5_edc0357_1345510114711_700135_14921_endTime);
                addDependency(this.objectToPass, objectToPass);
                init_17_0_5_edc0357_1345510114711_155853_14923Elaborations();
                fixTimeDependencies();
            }
        }

        public _17_0_5_edc0357_1345510113603_796245_13836(Expression<Integer> endTime) {
            super();
            init_17_0_5_edc0357_1345510113603_796245_13836Members();
            init_17_0_5_edc0357_1345510113603_796245_13836Collections();
            removeDependency(this.endTime);
            addDependency(this.endTime, endTime);
            init_17_0_5_edc0357_1345510113603_796245_13836Elaborations();
            fixTimeDependencies();
        }
    }

    public class _17_0_5_edc0357_1346100467245_506694_13577 extends DurativeEvent {

        public Parameter< ObjectFlow<Boolean> > sig_17_0_5_edc0357_1346104106512_507599_14867 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_5_edc0357_1346103964252_49821_14797 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_5_edc0357_1348001642582_861167_14319 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_5_edc0357_1346102889543_283441_14507 = null;

        public Parameter< ObjectFlow<Power_System.SignalreceiveGenReading> > sig_17_0_5_edc0357_1345510113323_8275_13619 = null;

        public IntegerParameter invoke_time = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_5_edc0357_1348001609357_382848_14312 = null;

        public Parameter< ObjectFlow<Power_System.SignalreceiveLoadReading> > sig_17_0_5_edc0357_1345510113322_187440_13618 = null;

        public Parameter< ObjectFlow<Power_System.SignalreceiveMeterReading> > sig_17_0_5_edc0357_1345510113321_763949_13617 = null;

        public ElaborationRule elaborationRule552 = null;

        public void init_17_0_5_edc0357_1346100467245_506694_13577Members() {
            try {
                sig_17_0_5_edc0357_1346104106512_507599_14867 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_5_edc0357_1346104106512_507599_14867", null, new ObjectFlow("sig_17_0_5_edc0357_1346104106512_507599_14867"), this);
                sig_17_0_5_edc0357_1346103964252_49821_14797 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_5_edc0357_1346103964252_49821_14797", null, new ObjectFlow("sig_17_0_5_edc0357_1346103964252_49821_14797"), this);
                sig_17_0_5_edc0357_1348001642582_861167_14319 = new Parameter<ObjectFlow<Integer>>("sig_17_0_5_edc0357_1348001642582_861167_14319", null, new ObjectFlow("sig_17_0_5_edc0357_1348001642582_861167_14319"), this);
                sig_17_0_5_edc0357_1346102889543_283441_14507 = new Parameter<ObjectFlow<Integer>>("sig_17_0_5_edc0357_1346102889543_283441_14507", null, new ObjectFlow("sig_17_0_5_edc0357_1346102889543_283441_14507"), this);
                sig_17_0_5_edc0357_1345510113323_8275_13619 = new Parameter<ObjectFlow<Power_System.SignalreceiveGenReading>>("sig_17_0_5_edc0357_1345510113323_8275_13619", null, new ObjectFlow("sig_17_0_5_edc0357_1345510113323_8275_13619"), this);
                invoke_time = new IntegerParameter("invoke_time", this);
                sig_17_0_5_edc0357_1348001609357_382848_14312 = new Parameter<ObjectFlow<Integer>>("sig_17_0_5_edc0357_1348001609357_382848_14312", null, new ObjectFlow("sig_17_0_5_edc0357_1348001609357_382848_14312"), this);
                sig_17_0_5_edc0357_1345510113322_187440_13618 = new Parameter<ObjectFlow<Power_System.SignalreceiveLoadReading>>("sig_17_0_5_edc0357_1345510113322_187440_13618", null, new ObjectFlow("sig_17_0_5_edc0357_1345510113322_187440_13618"), this);
                sig_17_0_5_edc0357_1345510113321_763949_13617 = new Parameter<ObjectFlow<Power_System.SignalreceiveMeterReading>>("sig_17_0_5_edc0357_1345510113321_763949_13617", null, new ObjectFlow("sig_17_0_5_edc0357_1345510113321_763949_13617"), this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void init_17_0_5_edc0357_1346100467245_506694_13577Collections() {
            parameters.add(sig_17_0_5_edc0357_1346104106512_507599_14867);
            parameters.add(sig_17_0_5_edc0357_1346103964252_49821_14797);
            parameters.add(sig_17_0_5_edc0357_1348001642582_861167_14319);
            parameters.add(sig_17_0_5_edc0357_1346102889543_283441_14507);
            parameters.add(sig_17_0_5_edc0357_1345510113323_8275_13619);
            parameters.add(invoke_time);
            parameters.add(sig_17_0_5_edc0357_1348001609357_382848_14312);
            parameters.add(sig_17_0_5_edc0357_1345510113322_187440_13618);
            parameters.add(sig_17_0_5_edc0357_1345510113321_763949_13617);
        }

        public void init_17_0_5_edc0357_1346100467245_506694_13577Elaborations() {
            Expression<?>[] arguments552 = new Expression<?>[1];
            arguments552[0] = new Expression<Integer>(invoke_time);
            Expression<Boolean> condition552 = new Expression<Boolean>(true);
            elaborationRule552 = addElaborationRule(condition552, _17_0_5_edc0357_1346100467245_506694_13577.this, LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1346100503665_742649_13627.class, "_InitialNode_LADWP_CB", arguments552);
        }

        public _17_0_5_edc0357_1346100467245_506694_13577() {
            super();
            init_17_0_5_edc0357_1346100467245_506694_13577Members();
            init_17_0_5_edc0357_1346100467245_506694_13577Collections();
            init_17_0_5_edc0357_1346100467245_506694_13577Elaborations();
        }

        public class _17_0_5_edc0357_1346100496379_482519_13614 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1348008482216_500972_14383_exists = null;

            public IntegerParameter _17_0_5_edc0357_1346100503665_742649_13627_endTime = null;

            public IntegerParameter cba_endTime = null;

            public ConstraintExpression constraint553 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1348008482216_500972_14383_existsDependency = null;

            public ElaborationRule elaborationRule554 = null;

            public ElaborationRule elaborationRule555 = null;

            public void init_17_0_5_edc0357_1346100496379_482519_13614Members() {
                try {
                    _17_0_5_edc0357_1348008482216_500972_14383_exists = new BooleanParameter("_17_0_5_edc0357_1348008482216_500972_14383_exists", this);
                    _17_0_5_edc0357_1346100503665_742649_13627_endTime = new IntegerParameter("_17_0_5_edc0357_1346100503665_742649_13627_endTime", this);
                    cba_endTime = new IntegerParameter("cba_endTime", this);
                    constraint553 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346100503665_742649_13627_endTime)));
                    _17_0_5_edc0357_1348008482216_500972_14383_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1348008482216_500972_14383_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346100496379_482519_13614Collections() {
                parameters.add(_17_0_5_edc0357_1348008482216_500972_14383_exists);
                parameters.add(_17_0_5_edc0357_1346100503665_742649_13627_endTime);
                parameters.add(cba_endTime);
                constraintExpressions.add(constraint553);
                dependencies.add(_17_0_5_edc0357_1348008482216_500972_14383_existsDependency);
            }

            public void init_17_0_5_edc0357_1346100496379_482519_13614Elaborations() {
                Expression<?>[] arguments554 = new Expression<?>[1];
                arguments554[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition554 = new Expression<Boolean>(_17_0_5_edc0357_1348008482216_500972_14383_exists);
                elaborationRule554 = addElaborationRule(condition554, _17_0_5_edc0357_1346100467245_506694_13577.this, LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1348008482216_500972_14383.class, "_AcceptEventAction_LADWP_CB", arguments554);
                Expression<?>[] arguments555 = new Expression<?>[1];
                arguments555[0] = new Expression<Integer>(cba_endTime);
                Expression<Boolean> condition555 = new Expression<Boolean>(true);
                elaborationRule555 = addElaborationRule(condition555, LADWP.this, LADWP._17_0_5_edc0357_1345510113603_796245_13836.class, "InitializeLADWP_Activity_LADWP", arguments555);
            }

            public _17_0_5_edc0357_1346100496379_482519_13614() {
                super();
                init_17_0_5_edc0357_1346100496379_482519_13614Members();
                init_17_0_5_edc0357_1346100496379_482519_13614Collections();
                init_17_0_5_edc0357_1346100496379_482519_13614Elaborations();
            }

            public _17_0_5_edc0357_1346100496379_482519_13614(Expression<Integer> _17_0_5_edc0357_1346100503665_742649_13627_endTime) {
                super();
                init_17_0_5_edc0357_1346100496379_482519_13614Members();
                init_17_0_5_edc0357_1346100496379_482519_13614Collections();
                addDependency(this._17_0_5_edc0357_1346100503665_742649_13627_endTime, _17_0_5_edc0357_1346100503665_742649_13627_endTime);
                init_17_0_5_edc0357_1346100496379_482519_13614Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1346100503665_742649_13627 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1346100496379_482519_13614_exists = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346100496379_482519_13614_existsDependency = null;

            public ElaborationRule elaborationRule556 = null;

            public void init_17_0_5_edc0357_1346100503665_742649_13627Members() {
                try {
                    _17_0_5_edc0357_1346100496379_482519_13614_exists = new BooleanParameter("_17_0_5_edc0357_1346100496379_482519_13614_exists", this);
                    _17_0_5_edc0357_1346100496379_482519_13614_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346100496379_482519_13614_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346100503665_742649_13627Collections() {
                parameters.add(_17_0_5_edc0357_1346100496379_482519_13614_exists);
                dependencies.add(_17_0_5_edc0357_1346100496379_482519_13614_existsDependency);
            }

            public void init_17_0_5_edc0357_1346100503665_742649_13627Elaborations() {
                Expression<?>[] arguments556 = new Expression<?>[1];
                arguments556[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition556 = new Expression<Boolean>(_17_0_5_edc0357_1346100496379_482519_13614_exists);
                elaborationRule556 = addElaborationRule(condition556, _17_0_5_edc0357_1346100467245_506694_13577.this, LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1346100496379_482519_13614.class, "initLADWP_CallBehaviorAction_LADWP_CB", arguments556);
            }

            public _17_0_5_edc0357_1346100503665_742649_13627() {
                super();
                init_17_0_5_edc0357_1346100503665_742649_13627Members();
                init_17_0_5_edc0357_1346100503665_742649_13627Collections();
                init_17_0_5_edc0357_1346100503665_742649_13627Elaborations();
            }

            public _17_0_5_edc0357_1346100503665_742649_13627(Expression<Integer> startTime) {
                super();
                init_17_0_5_edc0357_1346100503665_742649_13627Members();
                init_17_0_5_edc0357_1346100503665_742649_13627Collections();
                removeDependency(this.startTime);
                addDependency(this.startTime, startTime);
                init_17_0_5_edc0357_1346100503665_742649_13627Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1346100685814_732050_13654 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1348008482216_500972_14383_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1348163055082_50872_14559_exists = null;

            public BooleanParameter _17_0_5_edc0357_1346100882824_187464_13736_exists = null;

            public BooleanParameter _17_0_5_edc0357_1346101486727_93891_14069_exists = null;

            public BooleanParameter _17_0_5_edc0357_1346101253799_271072_13939_exists = null;

            public BooleanParameter _17_0_5_edc0357_1348001296069_146013_14222_exists = null;

            public ConstraintExpression constraint557 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1348163055082_50872_14559_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346100882824_187464_13736_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346101486727_93891_14069_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346101253799_271072_13939_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1348001296069_146013_14222_existsDependency = null;

            public ElaborationRule elaborationRule558 = null;

            public ElaborationRule elaborationRule559 = null;

            public ElaborationRule elaborationRule560 = null;

            public ElaborationRule elaborationRule561 = null;

            public ElaborationRule elaborationRule562 = null;

            public void init_17_0_5_edc0357_1346100685814_732050_13654Members() {
                try {
                    _17_0_5_edc0357_1348008482216_500972_14383_endTime = new IntegerParameter("_17_0_5_edc0357_1348008482216_500972_14383_endTime", this);
                    _17_0_5_edc0357_1348163055082_50872_14559_exists = new BooleanParameter("_17_0_5_edc0357_1348163055082_50872_14559_exists", this);
                    _17_0_5_edc0357_1346100882824_187464_13736_exists = new BooleanParameter("_17_0_5_edc0357_1346100882824_187464_13736_exists", this);
                    _17_0_5_edc0357_1346101486727_93891_14069_exists = new BooleanParameter("_17_0_5_edc0357_1346101486727_93891_14069_exists", this);
                    _17_0_5_edc0357_1346101253799_271072_13939_exists = new BooleanParameter("_17_0_5_edc0357_1346101253799_271072_13939_exists", this);
                    _17_0_5_edc0357_1348001296069_146013_14222_exists = new BooleanParameter("_17_0_5_edc0357_1348001296069_146013_14222_exists", this);
                    constraint557 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1348008482216_500972_14383_endTime)));
                    _17_0_5_edc0357_1348163055082_50872_14559_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1348163055082_50872_14559_exists, new Expression<Boolean>(true));
                    _17_0_5_edc0357_1346100882824_187464_13736_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346100882824_187464_13736_exists, new Expression<Boolean>(true));
                    _17_0_5_edc0357_1346101486727_93891_14069_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346101486727_93891_14069_exists, new Expression<Boolean>(true));
                    _17_0_5_edc0357_1346101253799_271072_13939_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346101253799_271072_13939_exists, new Expression<Boolean>(true));
                    _17_0_5_edc0357_1348001296069_146013_14222_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1348001296069_146013_14222_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346100685814_732050_13654Collections() {
                parameters.add(_17_0_5_edc0357_1348008482216_500972_14383_endTime);
                parameters.add(_17_0_5_edc0357_1348163055082_50872_14559_exists);
                parameters.add(_17_0_5_edc0357_1346100882824_187464_13736_exists);
                parameters.add(_17_0_5_edc0357_1346101486727_93891_14069_exists);
                parameters.add(_17_0_5_edc0357_1346101253799_271072_13939_exists);
                parameters.add(_17_0_5_edc0357_1348001296069_146013_14222_exists);
                constraintExpressions.add(constraint557);
                dependencies.add(_17_0_5_edc0357_1348163055082_50872_14559_existsDependency);
                dependencies.add(_17_0_5_edc0357_1346100882824_187464_13736_existsDependency);
                dependencies.add(_17_0_5_edc0357_1346101486727_93891_14069_existsDependency);
                dependencies.add(_17_0_5_edc0357_1346101253799_271072_13939_existsDependency);
                dependencies.add(_17_0_5_edc0357_1348001296069_146013_14222_existsDependency);
            }

            public void init_17_0_5_edc0357_1346100685814_732050_13654Elaborations() {
                Expression<?>[] arguments558 = new Expression<?>[1];
                arguments558[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition558 = new Expression<Boolean>(_17_0_5_edc0357_1346101253799_271072_13939_exists);
                elaborationRule558 = addElaborationRule(condition558, _17_0_5_edc0357_1346100467245_506694_13577.this, LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1346101253799_271072_13939.class, "mnrlr_MergeNode_LADWP_CB", arguments558);
                Expression<?>[] arguments559 = new Expression<?>[1];
                arguments559[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition559 = new Expression<Boolean>(_17_0_5_edc0357_1348163055082_50872_14559_exists);
                elaborationRule559 = addElaborationRule(condition559, _17_0_5_edc0357_1346100467245_506694_13577.this, LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1348163055082_50872_14559.class, "_AcceptEventAction_LADWP_CB", arguments559);
                Expression<?>[] arguments560 = new Expression<?>[1];
                arguments560[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition560 = new Expression<Boolean>(_17_0_5_edc0357_1346101486727_93891_14069_exists);
                elaborationRule560 = addElaborationRule(condition560, _17_0_5_edc0357_1346100467245_506694_13577.this, LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1346101486727_93891_14069.class, "mrrgr_MergeNode_LADWP_CB", arguments560);
                Expression<?>[] arguments561 = new Expression<?>[1];
                arguments561[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition561 = new Expression<Boolean>(_17_0_5_edc0357_1348001296069_146013_14222_exists);
                elaborationRule561 = addElaborationRule(condition561, _17_0_5_edc0357_1346100467245_506694_13577.this, LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1348001296069_146013_14222.class, "_AcceptEventAction_LADWP_CB", arguments561);
                Expression<?>[] arguments562 = new Expression<?>[1];
                arguments562[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition562 = new Expression<Boolean>(_17_0_5_edc0357_1346100882824_187464_13736_exists);
                elaborationRule562 = addElaborationRule(condition562, _17_0_5_edc0357_1346100467245_506694_13577.this, LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1346100882824_187464_13736.class, "mnrmr_MergeNode_LADWP_CB", arguments562);
            }

            public _17_0_5_edc0357_1346100685814_732050_13654() {
                super();
                init_17_0_5_edc0357_1346100685814_732050_13654Members();
                init_17_0_5_edc0357_1346100685814_732050_13654Collections();
                init_17_0_5_edc0357_1346100685814_732050_13654Elaborations();
            }

            public _17_0_5_edc0357_1346100685814_732050_13654(Expression<Integer> _17_0_5_edc0357_1348008482216_500972_14383_endTime) {
                super();
                init_17_0_5_edc0357_1346100685814_732050_13654Members();
                init_17_0_5_edc0357_1346100685814_732050_13654Collections();
                addDependency(this._17_0_5_edc0357_1348008482216_500972_14383_endTime, _17_0_5_edc0357_1348008482216_500972_14383_endTime);
                init_17_0_5_edc0357_1346100685814_732050_13654Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1346100727841_589225_13670 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1346100882824_187464_13736_exists = null;

            public IntegerParameter _17_0_5_edc0357_1346100922767_721473_13763 = null;

            public IntegerParameter _17_0_5_edc0357_1346100747196_888109_13697_endTime = null;

            public IntegerParameter cba_endTime = null;

            public ConstraintExpression constraint563 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346100882824_187464_13736_existsDependency = null;

            public ElaborationRule elaborationRule564 = null;

            public ElaborationRule elaborationRule565 = null;

            public void init_17_0_5_edc0357_1346100727841_589225_13670Members() {
                try {
                    _17_0_5_edc0357_1346100882824_187464_13736_exists = new BooleanParameter("_17_0_5_edc0357_1346100882824_187464_13736_exists", this);
                    _17_0_5_edc0357_1346100922767_721473_13763 = new IntegerParameter("_17_0_5_edc0357_1346100922767_721473_13763", this);
                    _17_0_5_edc0357_1346100747196_888109_13697_endTime = new IntegerParameter("_17_0_5_edc0357_1346100747196_888109_13697_endTime", this);
                    cba_endTime = new IntegerParameter("cba_endTime", this);
                    constraint563 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346100747196_888109_13697_endTime)));
                    _17_0_5_edc0357_1346100882824_187464_13736_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346100882824_187464_13736_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346100727841_589225_13670Collections() {
                parameters.add(_17_0_5_edc0357_1346100882824_187464_13736_exists);
                parameters.add(_17_0_5_edc0357_1346100922767_721473_13763);
                parameters.add(_17_0_5_edc0357_1346100747196_888109_13697_endTime);
                parameters.add(cba_endTime);
                constraintExpressions.add(constraint563);
                dependencies.add(_17_0_5_edc0357_1346100882824_187464_13736_existsDependency);
            }

            public void init_17_0_5_edc0357_1346100727841_589225_13670Elaborations() {
                Expression<?>[] arguments564 = new Expression<?>[2];
                arguments564[0] = new Expression<Integer>(cba_endTime);
                arguments564[1] = new Expression<Integer>(_17_0_5_edc0357_1346100922767_721473_13763);
                Expression<Boolean> condition564 = new Expression<Boolean>(true);
                elaborationRule564 = addElaborationRule(condition564, LADWP.this, LADWP._17_0_5_edc0357_1345510113596_603128_13828.class, "updateReportedLoad_Activity_LADWP", arguments564);
                Expression<?>[] arguments565 = new Expression<?>[1];
                arguments565[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition565 = new Expression<Boolean>(_17_0_5_edc0357_1346100882824_187464_13736_exists);
                elaborationRule565 = addElaborationRule(condition565, _17_0_5_edc0357_1346100467245_506694_13577.this, LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1346100882824_187464_13736.class, "mnrmr_MergeNode_LADWP_CB", arguments565);
            }

            public _17_0_5_edc0357_1346100727841_589225_13670() {
                super();
                init_17_0_5_edc0357_1346100727841_589225_13670Members();
                init_17_0_5_edc0357_1346100727841_589225_13670Collections();
                init_17_0_5_edc0357_1346100727841_589225_13670Elaborations();
            }

            public _17_0_5_edc0357_1346100727841_589225_13670(Expression<Integer> _17_0_5_edc0357_1346100747196_888109_13697_endTime, Expression<Integer> _17_0_5_edc0357_1346100922767_721473_13763) {
                super();
                init_17_0_5_edc0357_1346100727841_589225_13670Members();
                init_17_0_5_edc0357_1346100727841_589225_13670Collections();
                addDependency(this._17_0_5_edc0357_1346100747196_888109_13697_endTime, _17_0_5_edc0357_1346100747196_888109_13697_endTime);
                addDependency(this._17_0_5_edc0357_1346100922767_721473_13763, _17_0_5_edc0357_1346100922767_721473_13763);
                init_17_0_5_edc0357_1346100727841_589225_13670Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1346100738628_763629_13683 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1346100747196_888109_13697_exists = null;

            public Parameter< Power_System.SignalreceiveMeterReading > _17_0_5_edc0357_1346100752836_305657_13711 = null;

            public IntegerParameter _17_0_5_edc0357_1346100882824_187464_13736_endTime = null;

            public ConstraintExpression constraint566 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346100747196_888109_13697_existsDependency = null;

            public Dependency< Power_System.SignalreceiveMeterReading > _17_0_5_edc0357_1346100752836_305657_13711Dependency = null;

            public ElaborationRule elaborationRule567 = null;

            public void init_17_0_5_edc0357_1346100738628_763629_13683Members() {
                try {
                    _17_0_5_edc0357_1346100747196_888109_13697_exists = new BooleanParameter("_17_0_5_edc0357_1346100747196_888109_13697_exists", this);
                    _17_0_5_edc0357_1346100752836_305657_13711 = new Parameter<Power_System.SignalreceiveMeterReading>("_17_0_5_edc0357_1346100752836_305657_13711", null, null, this);
                    _17_0_5_edc0357_1346100882824_187464_13736_endTime = new IntegerParameter("_17_0_5_edc0357_1346100882824_187464_13736_endTime", this);
                    constraint566 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346100882824_187464_13736_endTime)));
                    _17_0_5_edc0357_1346100747196_888109_13697_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346100747196_888109_13697_exists, new Expression<Boolean>(true));
                    _17_0_5_edc0357_1346100752836_305657_13711Dependency = new Dependency<Power_System.SignalreceiveMeterReading>(_17_0_5_edc0357_1346100752836_305657_13711, new Expression(new FunctionCall(q_LADWP_receiveMeterReading, Utils.getMethodForArgTypes("ObjectFlow<SignalreceiveMeterReading>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346100738628_763629_13683Collections() {
                parameters.add(_17_0_5_edc0357_1346100747196_888109_13697_exists);
                parameters.add(_17_0_5_edc0357_1346100752836_305657_13711);
                parameters.add(_17_0_5_edc0357_1346100882824_187464_13736_endTime);
                constraintExpressions.add(constraint566);
                dependencies.add(_17_0_5_edc0357_1346100747196_888109_13697_existsDependency);
                dependencies.add(_17_0_5_edc0357_1346100752836_305657_13711Dependency);
            }

            public void init_17_0_5_edc0357_1346100738628_763629_13683Elaborations() {
                Expression<?>[] arguments567 = new Expression<?>[2];
                arguments567[0] = new Expression<Integer>(endTime);
                arguments567[1] = new Expression<Power_System.SignalreceiveMeterReading>(_17_0_5_edc0357_1346100752836_305657_13711);
                Expression<Boolean> condition567 = new Expression<Boolean>(_17_0_5_edc0357_1346100747196_888109_13697_exists);
                elaborationRule567 = addElaborationRule(condition567, _17_0_5_edc0357_1346100467245_506694_13577.this, LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1346100747196_888109_13697.class, "readMeterVal_ReadStructuralFeatureAction_LADWP_CB", arguments567);
            }

            public _17_0_5_edc0357_1346100738628_763629_13683() {
                super();
                init_17_0_5_edc0357_1346100738628_763629_13683Members();
                init_17_0_5_edc0357_1346100738628_763629_13683Collections();
                init_17_0_5_edc0357_1346100738628_763629_13683Elaborations();
            }

            public _17_0_5_edc0357_1346100738628_763629_13683(Expression<Integer> _17_0_5_edc0357_1346100882824_187464_13736_endTime) {
                super();
                init_17_0_5_edc0357_1346100738628_763629_13683Members();
                init_17_0_5_edc0357_1346100738628_763629_13683Collections();
                addDependency(this._17_0_5_edc0357_1346100882824_187464_13736_endTime, _17_0_5_edc0357_1346100882824_187464_13736_endTime);
                init_17_0_5_edc0357_1346100738628_763629_13683Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1346100747196_888109_13697 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1346100727841_589225_13670_exists = null;

            public Parameter< Power_System.SignalreceiveMeterReading > _17_0_5_edc0357_1346100796330_896110_13719 = null;

            public IntegerParameter _17_0_5_edc0357_1346100738628_763629_13683_endTime = null;

            public IntegerParameter _17_0_5_edc0357_1346100827593_120288_13720 = null;

            public ConstraintExpression constraint568 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346100727841_589225_13670_existsDependency = null;

            public Dependency< Integer > _17_0_5_edc0357_1346100827593_120288_13720Dependency = null;

            public ElaborationRule elaborationRule569 = null;

            public void init_17_0_5_edc0357_1346100747196_888109_13697Members() {
                try {
                    _17_0_5_edc0357_1346100727841_589225_13670_exists = new BooleanParameter("_17_0_5_edc0357_1346100727841_589225_13670_exists", this);
                    _17_0_5_edc0357_1346100796330_896110_13719 = new Parameter<Power_System.SignalreceiveMeterReading>("_17_0_5_edc0357_1346100796330_896110_13719", null, null, this);
                    _17_0_5_edc0357_1346100738628_763629_13683_endTime = new IntegerParameter("_17_0_5_edc0357_1346100738628_763629_13683_endTime", this);
                    _17_0_5_edc0357_1346100827593_120288_13720 = new IntegerParameter("_17_0_5_edc0357_1346100827593_120288_13720", this);
                    constraint568 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346100738628_763629_13683_endTime)));
                    _17_0_5_edc0357_1346100727841_589225_13670_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346100727841_589225_13670_exists, new Expression<Boolean>(true));
                    _17_0_5_edc0357_1346100827593_120288_13720Dependency = new Dependency<Integer>(_17_0_5_edc0357_1346100827593_120288_13720, new Expression(new FunctionCall(null, Utils.getMethodForArgTypes("LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1346100747196_888109_13697", "generated", "getValue"), new Object[] { new Expression<Integer>(startTime) }, new FunctionCall(null, Parameter.class, "getValue", (Object[]) null, new FunctionCall(_17_0_5_edc0357_1346100796330_896110_13719, Parameter.class, "getMember", new Object[] { "meter_value__17_0_5_edc0357_1345510113616_372105_13853" })))));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346100747196_888109_13697Collections() {
                parameters.add(_17_0_5_edc0357_1346100727841_589225_13670_exists);
                parameters.add(_17_0_5_edc0357_1346100796330_896110_13719);
                parameters.add(_17_0_5_edc0357_1346100738628_763629_13683_endTime);
                parameters.add(_17_0_5_edc0357_1346100827593_120288_13720);
                constraintExpressions.add(constraint568);
                dependencies.add(_17_0_5_edc0357_1346100727841_589225_13670_existsDependency);
                dependencies.add(_17_0_5_edc0357_1346100827593_120288_13720Dependency);
            }

            public void init_17_0_5_edc0357_1346100747196_888109_13697Elaborations() {
                Expression<?>[] arguments569 = new Expression<?>[2];
                arguments569[0] = new Expression<Integer>(endTime);
                arguments569[1] = new Expression<Integer>(_17_0_5_edc0357_1346100827593_120288_13720);
                Expression<Boolean> condition569 = new Expression<Boolean>(_17_0_5_edc0357_1346100727841_589225_13670_exists);
                elaborationRule569 = addElaborationRule(condition569, _17_0_5_edc0357_1346100467245_506694_13577.this, LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1346100727841_589225_13670.class, "updateRepLoad_CallBehaviorAction_LADWP_CB", arguments569);
            }

            public _17_0_5_edc0357_1346100747196_888109_13697() {
                super();
                init_17_0_5_edc0357_1346100747196_888109_13697Members();
                init_17_0_5_edc0357_1346100747196_888109_13697Collections();
                init_17_0_5_edc0357_1346100747196_888109_13697Elaborations();
            }

            public _17_0_5_edc0357_1346100747196_888109_13697(Expression<Integer> _17_0_5_edc0357_1346100738628_763629_13683_endTime, Expression<Power_System.SignalreceiveMeterReading> _17_0_5_edc0357_1346100796330_896110_13719) {
                super();
                init_17_0_5_edc0357_1346100747196_888109_13697Members();
                init_17_0_5_edc0357_1346100747196_888109_13697Collections();
                addDependency(this._17_0_5_edc0357_1346100738628_763629_13683_endTime, _17_0_5_edc0357_1346100738628_763629_13683_endTime);
                addDependency(this._17_0_5_edc0357_1346100796330_896110_13719, _17_0_5_edc0357_1346100796330_896110_13719);
                init_17_0_5_edc0357_1346100747196_888109_13697Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1346100882824_187464_13736 extends DurativeEvent {

            public IntegerParameter invoker_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1346100738628_763629_13683_exists = null;

            public ConstraintExpression constraint570 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346100738628_763629_13683_existsDependency = null;

            public ElaborationRule elaborationRule571 = null;

            public void init_17_0_5_edc0357_1346100882824_187464_13736Members() {
                try {
                    invoker_endTime = new IntegerParameter("invoker_endTime", this);
                    _17_0_5_edc0357_1346100738628_763629_13683_exists = new BooleanParameter("_17_0_5_edc0357_1346100738628_763629_13683_exists", this);
                    constraint570 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(invoker_endTime)));
                    _17_0_5_edc0357_1346100738628_763629_13683_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346100738628_763629_13683_exists, new Expression(new FunctionCall(q_LADWP_receiveMeterReading, Utils.getMethodForArgTypes("ObjectFlow<SignalreceiveMeterReading>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346100882824_187464_13736Collections() {
                parameters.add(invoker_endTime);
                parameters.add(_17_0_5_edc0357_1346100738628_763629_13683_exists);
                constraintExpressions.add(constraint570);
                dependencies.add(_17_0_5_edc0357_1346100738628_763629_13683_existsDependency);
            }

            public void init_17_0_5_edc0357_1346100882824_187464_13736Elaborations() {
                Expression<?>[] arguments571 = new Expression<?>[1];
                arguments571[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition571 = new Expression<Boolean>(_17_0_5_edc0357_1346100738628_763629_13683_exists);
                elaborationRule571 = addElaborationRule(condition571, _17_0_5_edc0357_1346100467245_506694_13577.this, LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1346100738628_763629_13683.class, "_AcceptEventAction_LADWP_CB", arguments571);
            }

            public _17_0_5_edc0357_1346100882824_187464_13736() {
                super();
                init_17_0_5_edc0357_1346100882824_187464_13736Members();
                init_17_0_5_edc0357_1346100882824_187464_13736Collections();
                init_17_0_5_edc0357_1346100882824_187464_13736Elaborations();
            }

            public _17_0_5_edc0357_1346100882824_187464_13736(Expression<Integer> invoker_endTime) {
                super();
                init_17_0_5_edc0357_1346100882824_187464_13736Members();
                init_17_0_5_edc0357_1346100882824_187464_13736Collections();
                addDependency(this.invoker_endTime, invoker_endTime);
                init_17_0_5_edc0357_1346100882824_187464_13736Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1346100976618_530123_13805 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1346100986924_922768_13821_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1346101253799_271072_13939_exists = null;

            public IntegerParameter _17_0_5_edc0357_1346101090161_713198_13875 = null;

            public IntegerParameter cba_endTime = null;

            public ConstraintExpression constraint572 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346101253799_271072_13939_existsDependency = null;

            public ElaborationRule elaborationRule573 = null;

            public ElaborationRule elaborationRule574 = null;

            public void init_17_0_5_edc0357_1346100976618_530123_13805Members() {
                try {
                    _17_0_5_edc0357_1346100986924_922768_13821_endTime = new IntegerParameter("_17_0_5_edc0357_1346100986924_922768_13821_endTime", this);
                    _17_0_5_edc0357_1346101253799_271072_13939_exists = new BooleanParameter("_17_0_5_edc0357_1346101253799_271072_13939_exists", this);
                    _17_0_5_edc0357_1346101090161_713198_13875 = new IntegerParameter("_17_0_5_edc0357_1346101090161_713198_13875", this);
                    cba_endTime = new IntegerParameter("cba_endTime", this);
                    constraint572 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346100986924_922768_13821_endTime)));
                    _17_0_5_edc0357_1346101253799_271072_13939_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346101253799_271072_13939_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346100976618_530123_13805Collections() {
                parameters.add(_17_0_5_edc0357_1346100986924_922768_13821_endTime);
                parameters.add(_17_0_5_edc0357_1346101253799_271072_13939_exists);
                parameters.add(_17_0_5_edc0357_1346101090161_713198_13875);
                parameters.add(cba_endTime);
                constraintExpressions.add(constraint572);
                dependencies.add(_17_0_5_edc0357_1346101253799_271072_13939_existsDependency);
            }

            public void init_17_0_5_edc0357_1346100976618_530123_13805Elaborations() {
                Expression<?>[] arguments573 = new Expression<?>[1];
                arguments573[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition573 = new Expression<Boolean>(_17_0_5_edc0357_1346101253799_271072_13939_exists);
                elaborationRule573 = addElaborationRule(condition573, _17_0_5_edc0357_1346100467245_506694_13577.this, LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1346101253799_271072_13939.class, "mnrlr_MergeNode_LADWP_CB", arguments573);
                Expression<?>[] arguments574 = new Expression<?>[2];
                arguments574[0] = new Expression<Integer>(cba_endTime);
                arguments574[1] = new Expression<Integer>(_17_0_5_edc0357_1346101090161_713198_13875);
                Expression<Boolean> condition574 = new Expression<Boolean>(true);
                elaborationRule574 = addElaborationRule(condition574, LADWP.this, LADWP._17_0_5_edc0357_1345510113597_14205_13829.class, "updateActualLoad_Activity_LADWP", arguments574);
            }

            public _17_0_5_edc0357_1346100976618_530123_13805() {
                super();
                init_17_0_5_edc0357_1346100976618_530123_13805Members();
                init_17_0_5_edc0357_1346100976618_530123_13805Collections();
                init_17_0_5_edc0357_1346100976618_530123_13805Elaborations();
            }

            public _17_0_5_edc0357_1346100976618_530123_13805(Expression<Integer> _17_0_5_edc0357_1346100986924_922768_13821_endTime, Expression<Integer> _17_0_5_edc0357_1346101090161_713198_13875) {
                super();
                init_17_0_5_edc0357_1346100976618_530123_13805Members();
                init_17_0_5_edc0357_1346100976618_530123_13805Collections();
                addDependency(this._17_0_5_edc0357_1346100986924_922768_13821_endTime, _17_0_5_edc0357_1346100986924_922768_13821_endTime);
                addDependency(this._17_0_5_edc0357_1346101090161_713198_13875, _17_0_5_edc0357_1346101090161_713198_13875);
                init_17_0_5_edc0357_1346100976618_530123_13805Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1346100986924_922768_13821 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1346100990557_575122_13835_endTime = null;

            public IntegerParameter _17_0_5_edc0357_1346101040421_157561_13857 = null;

            public Parameter< Power_System.SignalreceiveLoadReading > _17_0_5_edc0357_1346101057612_324428_13858 = null;

            public BooleanParameter _17_0_5_edc0357_1346100976618_530123_13805_exists = null;

            public ConstraintExpression constraint575 = null;

            public Dependency< Integer > _17_0_5_edc0357_1346101040421_157561_13857Dependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346100976618_530123_13805_existsDependency = null;

            public ElaborationRule elaborationRule576 = null;

            public void init_17_0_5_edc0357_1346100986924_922768_13821Members() {
                try {
                    _17_0_5_edc0357_1346100990557_575122_13835_endTime = new IntegerParameter("_17_0_5_edc0357_1346100990557_575122_13835_endTime", this);
                    _17_0_5_edc0357_1346101040421_157561_13857 = new IntegerParameter("_17_0_5_edc0357_1346101040421_157561_13857", this);
                    _17_0_5_edc0357_1346101057612_324428_13858 = new Parameter<Power_System.SignalreceiveLoadReading>("_17_0_5_edc0357_1346101057612_324428_13858", null, null, this);
                    _17_0_5_edc0357_1346100976618_530123_13805_exists = new BooleanParameter("_17_0_5_edc0357_1346100976618_530123_13805_exists", this);
                    constraint575 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346100990557_575122_13835_endTime)));
                    _17_0_5_edc0357_1346101040421_157561_13857Dependency = new Dependency<Integer>(_17_0_5_edc0357_1346101040421_157561_13857, new Expression(new FunctionCall(null, Utils.getMethodForArgTypes("LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1346100986924_922768_13821", "generated", "getValue"), new Object[] { new Expression<Integer>(startTime) }, new FunctionCall(null, Parameter.class, "getValue", (Object[]) null, new FunctionCall(_17_0_5_edc0357_1346101057612_324428_13858, Parameter.class, "getMember", new Object[] { "actual_load__17_0_5_edc0357_1345510113617_570582_13854" })))));
                    _17_0_5_edc0357_1346100976618_530123_13805_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346100976618_530123_13805_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346100986924_922768_13821Collections() {
                parameters.add(_17_0_5_edc0357_1346100990557_575122_13835_endTime);
                parameters.add(_17_0_5_edc0357_1346101040421_157561_13857);
                parameters.add(_17_0_5_edc0357_1346101057612_324428_13858);
                parameters.add(_17_0_5_edc0357_1346100976618_530123_13805_exists);
                constraintExpressions.add(constraint575);
                dependencies.add(_17_0_5_edc0357_1346101040421_157561_13857Dependency);
                dependencies.add(_17_0_5_edc0357_1346100976618_530123_13805_existsDependency);
            }

            public void init_17_0_5_edc0357_1346100986924_922768_13821Elaborations() {
                Expression<?>[] arguments576 = new Expression<?>[2];
                arguments576[0] = new Expression<Integer>(endTime);
                arguments576[1] = new Expression<Integer>(_17_0_5_edc0357_1346101040421_157561_13857);
                Expression<Boolean> condition576 = new Expression<Boolean>(_17_0_5_edc0357_1346100976618_530123_13805_exists);
                elaborationRule576 = addElaborationRule(condition576, _17_0_5_edc0357_1346100467245_506694_13577.this, LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1346100976618_530123_13805.class, "updateActLoad_CallBehaviorAction_LADWP_CB", arguments576);
            }

            public _17_0_5_edc0357_1346100986924_922768_13821() {
                super();
                init_17_0_5_edc0357_1346100986924_922768_13821Members();
                init_17_0_5_edc0357_1346100986924_922768_13821Collections();
                init_17_0_5_edc0357_1346100986924_922768_13821Elaborations();
            }

            public _17_0_5_edc0357_1346100986924_922768_13821(Expression<Integer> _17_0_5_edc0357_1346100990557_575122_13835_endTime, Expression<Power_System.SignalreceiveLoadReading> _17_0_5_edc0357_1346101057612_324428_13858) {
                super();
                init_17_0_5_edc0357_1346100986924_922768_13821Members();
                init_17_0_5_edc0357_1346100986924_922768_13821Collections();
                addDependency(this._17_0_5_edc0357_1346100990557_575122_13835_endTime, _17_0_5_edc0357_1346100990557_575122_13835_endTime);
                addDependency(this._17_0_5_edc0357_1346101057612_324428_13858, _17_0_5_edc0357_1346101057612_324428_13858);
                init_17_0_5_edc0357_1346100986924_922768_13821Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1346100990557_575122_13835 extends DurativeEvent {

            public Parameter< Power_System.SignalreceiveLoadReading > _17_0_5_edc0357_1346101014528_680095_13851 = null;

            public BooleanParameter _17_0_5_edc0357_1346100986924_922768_13821_exists = null;

            public IntegerParameter _17_0_5_edc0357_1346101253799_271072_13939_endTime = null;

            public ConstraintExpression constraint577 = null;

            public Dependency< Power_System.SignalreceiveLoadReading > _17_0_5_edc0357_1346101014528_680095_13851Dependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346100986924_922768_13821_existsDependency = null;

            public ElaborationRule elaborationRule578 = null;

            public void init_17_0_5_edc0357_1346100990557_575122_13835Members() {
                try {
                    _17_0_5_edc0357_1346101014528_680095_13851 = new Parameter<Power_System.SignalreceiveLoadReading>("_17_0_5_edc0357_1346101014528_680095_13851", null, null, this);
                    _17_0_5_edc0357_1346100986924_922768_13821_exists = new BooleanParameter("_17_0_5_edc0357_1346100986924_922768_13821_exists", this);
                    _17_0_5_edc0357_1346101253799_271072_13939_endTime = new IntegerParameter("_17_0_5_edc0357_1346101253799_271072_13939_endTime", this);
                    constraint577 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346101253799_271072_13939_endTime)));
                    _17_0_5_edc0357_1346101014528_680095_13851Dependency = new Dependency<Power_System.SignalreceiveLoadReading>(_17_0_5_edc0357_1346101014528_680095_13851, new Expression(new FunctionCall(q_LADWP_receiveLoadReading, Utils.getMethodForArgTypes("ObjectFlow<SignalreceiveLoadReading>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1346100986924_922768_13821_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346100986924_922768_13821_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346100990557_575122_13835Collections() {
                parameters.add(_17_0_5_edc0357_1346101014528_680095_13851);
                parameters.add(_17_0_5_edc0357_1346100986924_922768_13821_exists);
                parameters.add(_17_0_5_edc0357_1346101253799_271072_13939_endTime);
                constraintExpressions.add(constraint577);
                dependencies.add(_17_0_5_edc0357_1346101014528_680095_13851Dependency);
                dependencies.add(_17_0_5_edc0357_1346100986924_922768_13821_existsDependency);
            }

            public void init_17_0_5_edc0357_1346100990557_575122_13835Elaborations() {
                Expression<?>[] arguments578 = new Expression<?>[2];
                arguments578[0] = new Expression<Integer>(endTime);
                arguments578[1] = new Expression<Power_System.SignalreceiveLoadReading>(_17_0_5_edc0357_1346101014528_680095_13851);
                Expression<Boolean> condition578 = new Expression<Boolean>(_17_0_5_edc0357_1346100986924_922768_13821_exists);
                elaborationRule578 = addElaborationRule(condition578, _17_0_5_edc0357_1346100467245_506694_13577.this, LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1346100986924_922768_13821.class, "readActualLoad_ReadStructuralFeatureAction_LADWP_CB", arguments578);
            }

            public _17_0_5_edc0357_1346100990557_575122_13835() {
                super();
                init_17_0_5_edc0357_1346100990557_575122_13835Members();
                init_17_0_5_edc0357_1346100990557_575122_13835Collections();
                init_17_0_5_edc0357_1346100990557_575122_13835Elaborations();
            }

            public _17_0_5_edc0357_1346100990557_575122_13835(Expression<Integer> _17_0_5_edc0357_1346101253799_271072_13939_endTime) {
                super();
                init_17_0_5_edc0357_1346100990557_575122_13835Members();
                init_17_0_5_edc0357_1346100990557_575122_13835Collections();
                addDependency(this._17_0_5_edc0357_1346101253799_271072_13939_endTime, _17_0_5_edc0357_1346101253799_271072_13939_endTime);
                init_17_0_5_edc0357_1346100990557_575122_13835Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1346101253799_271072_13939 extends DurativeEvent {

            public IntegerParameter invoker_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1346100990557_575122_13835_exists = null;

            public ConstraintExpression constraint579 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346100990557_575122_13835_existsDependency = null;

            public ElaborationRule elaborationRule580 = null;

            public void init_17_0_5_edc0357_1346101253799_271072_13939Members() {
                try {
                    invoker_endTime = new IntegerParameter("invoker_endTime", this);
                    _17_0_5_edc0357_1346100990557_575122_13835_exists = new BooleanParameter("_17_0_5_edc0357_1346100990557_575122_13835_exists", this);
                    constraint579 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(invoker_endTime)));
                    _17_0_5_edc0357_1346100990557_575122_13835_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346100990557_575122_13835_exists, new Expression(new FunctionCall(q_LADWP_receiveLoadReading, Utils.getMethodForArgTypes("ObjectFlow<SignalreceiveLoadReading>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346101253799_271072_13939Collections() {
                parameters.add(invoker_endTime);
                parameters.add(_17_0_5_edc0357_1346100990557_575122_13835_exists);
                constraintExpressions.add(constraint579);
                dependencies.add(_17_0_5_edc0357_1346100990557_575122_13835_existsDependency);
            }

            public void init_17_0_5_edc0357_1346101253799_271072_13939Elaborations() {
                Expression<?>[] arguments580 = new Expression<?>[1];
                arguments580[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition580 = new Expression<Boolean>(_17_0_5_edc0357_1346100990557_575122_13835_exists);
                elaborationRule580 = addElaborationRule(condition580, _17_0_5_edc0357_1346100467245_506694_13577.this, LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1346100990557_575122_13835.class, "_AcceptEventAction_LADWP_CB", arguments580);
            }

            public _17_0_5_edc0357_1346101253799_271072_13939() {
                super();
                init_17_0_5_edc0357_1346101253799_271072_13939Members();
                init_17_0_5_edc0357_1346101253799_271072_13939Collections();
                init_17_0_5_edc0357_1346101253799_271072_13939Elaborations();
            }

            public _17_0_5_edc0357_1346101253799_271072_13939(Expression<Integer> invoker_endTime) {
                super();
                init_17_0_5_edc0357_1346101253799_271072_13939Members();
                init_17_0_5_edc0357_1346101253799_271072_13939Collections();
                addDependency(this.invoker_endTime, invoker_endTime);
                init_17_0_5_edc0357_1346101253799_271072_13939Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1346101323493_940716_13958 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1346101486727_93891_14069_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1346101350907_438807_13981_exists = null;

            public Parameter< Power_System.SignalreceiveGenReading > _17_0_5_edc0357_1346101342221_614382_13971 = null;

            public ConstraintExpression constraint581 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346101350907_438807_13981_existsDependency = null;

            public Dependency< Power_System.SignalreceiveGenReading > _17_0_5_edc0357_1346101342221_614382_13971Dependency = null;

            public ElaborationRule elaborationRule582 = null;

            public void init_17_0_5_edc0357_1346101323493_940716_13958Members() {
                try {
                    _17_0_5_edc0357_1346101486727_93891_14069_endTime = new IntegerParameter("_17_0_5_edc0357_1346101486727_93891_14069_endTime", this);
                    _17_0_5_edc0357_1346101350907_438807_13981_exists = new BooleanParameter("_17_0_5_edc0357_1346101350907_438807_13981_exists", this);
                    _17_0_5_edc0357_1346101342221_614382_13971 = new Parameter<Power_System.SignalreceiveGenReading>("_17_0_5_edc0357_1346101342221_614382_13971", null, null, this);
                    constraint581 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346101486727_93891_14069_endTime)));
                    _17_0_5_edc0357_1346101350907_438807_13981_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346101350907_438807_13981_exists, new Expression<Boolean>(true));
                    _17_0_5_edc0357_1346101342221_614382_13971Dependency = new Dependency<Power_System.SignalreceiveGenReading>(_17_0_5_edc0357_1346101342221_614382_13971, new Expression(new FunctionCall(q_LADWP_receiveGenReading, Utils.getMethodForArgTypes("ObjectFlow<SignalreceiveGenReading>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346101323493_940716_13958Collections() {
                parameters.add(_17_0_5_edc0357_1346101486727_93891_14069_endTime);
                parameters.add(_17_0_5_edc0357_1346101350907_438807_13981_exists);
                parameters.add(_17_0_5_edc0357_1346101342221_614382_13971);
                constraintExpressions.add(constraint581);
                dependencies.add(_17_0_5_edc0357_1346101350907_438807_13981_existsDependency);
                dependencies.add(_17_0_5_edc0357_1346101342221_614382_13971Dependency);
            }

            public void init_17_0_5_edc0357_1346101323493_940716_13958Elaborations() {
                Expression<?>[] arguments582 = new Expression<?>[2];
                arguments582[0] = new Expression<Integer>(endTime);
                arguments582[1] = new Expression<Power_System.SignalreceiveGenReading>(_17_0_5_edc0357_1346101342221_614382_13971);
                Expression<Boolean> condition582 = new Expression<Boolean>(_17_0_5_edc0357_1346101350907_438807_13981_exists);
                elaborationRule582 = addElaborationRule(condition582, _17_0_5_edc0357_1346100467245_506694_13577.this, LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1346101350907_438807_13981.class, "readActualPower_ReadStructuralFeatureAction_LADWP_CB", arguments582);
            }

            public _17_0_5_edc0357_1346101323493_940716_13958() {
                super();
                init_17_0_5_edc0357_1346101323493_940716_13958Members();
                init_17_0_5_edc0357_1346101323493_940716_13958Collections();
                init_17_0_5_edc0357_1346101323493_940716_13958Elaborations();
            }

            public _17_0_5_edc0357_1346101323493_940716_13958(Expression<Integer> _17_0_5_edc0357_1346101486727_93891_14069_endTime) {
                super();
                init_17_0_5_edc0357_1346101323493_940716_13958Members();
                init_17_0_5_edc0357_1346101323493_940716_13958Collections();
                addDependency(this._17_0_5_edc0357_1346101486727_93891_14069_endTime, _17_0_5_edc0357_1346101486727_93891_14069_endTime);
                init_17_0_5_edc0357_1346101323493_940716_13958Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1346101350907_438807_13981 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1346101323493_940716_13958_endTime = null;

            public IntegerParameter _17_0_5_edc0357_1346101376276_801916_13995 = null;

            public BooleanParameter _17_0_5_edc0357_1346101429843_239121_14015_exists = null;

            public Parameter< Power_System.SignalreceiveGenReading > _17_0_5_edc0357_1346101356139_450740_13994 = null;

            public ConstraintExpression constraint583 = null;

            public Dependency< Integer > _17_0_5_edc0357_1346101376276_801916_13995Dependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346101429843_239121_14015_existsDependency = null;

            public ElaborationRule elaborationRule584 = null;

            public void init_17_0_5_edc0357_1346101350907_438807_13981Members() {
                try {
                    _17_0_5_edc0357_1346101323493_940716_13958_endTime = new IntegerParameter("_17_0_5_edc0357_1346101323493_940716_13958_endTime", this);
                    _17_0_5_edc0357_1346101376276_801916_13995 = new IntegerParameter("_17_0_5_edc0357_1346101376276_801916_13995", this);
                    _17_0_5_edc0357_1346101429843_239121_14015_exists = new BooleanParameter("_17_0_5_edc0357_1346101429843_239121_14015_exists", this);
                    _17_0_5_edc0357_1346101356139_450740_13994 = new Parameter<Power_System.SignalreceiveGenReading>("_17_0_5_edc0357_1346101356139_450740_13994", null, null, this);
                    constraint583 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346101323493_940716_13958_endTime)));
                    _17_0_5_edc0357_1346101376276_801916_13995Dependency = new Dependency<Integer>(_17_0_5_edc0357_1346101376276_801916_13995, new Expression(new FunctionCall(null, Utils.getMethodForArgTypes("LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1346101350907_438807_13981", "generated", "getValue"), new Object[] { new Expression<Integer>(startTime) }, new FunctionCall(null, Parameter.class, "getValue", (Object[]) null, new FunctionCall(_17_0_5_edc0357_1346101356139_450740_13994, Parameter.class, "getMember", new Object[] { "actual_power__17_0_5_edc0357_1345510113617_469482_13855" })))));
                    _17_0_5_edc0357_1346101429843_239121_14015_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346101429843_239121_14015_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346101350907_438807_13981Collections() {
                parameters.add(_17_0_5_edc0357_1346101323493_940716_13958_endTime);
                parameters.add(_17_0_5_edc0357_1346101376276_801916_13995);
                parameters.add(_17_0_5_edc0357_1346101429843_239121_14015_exists);
                parameters.add(_17_0_5_edc0357_1346101356139_450740_13994);
                constraintExpressions.add(constraint583);
                dependencies.add(_17_0_5_edc0357_1346101376276_801916_13995Dependency);
                dependencies.add(_17_0_5_edc0357_1346101429843_239121_14015_existsDependency);
            }

            public void init_17_0_5_edc0357_1346101350907_438807_13981Elaborations() {
                Expression<?>[] arguments584 = new Expression<?>[2];
                arguments584[0] = new Expression<Integer>(endTime);
                arguments584[1] = new Expression<Integer>(_17_0_5_edc0357_1346101376276_801916_13995);
                Expression<Boolean> condition584 = new Expression<Boolean>(_17_0_5_edc0357_1346101429843_239121_14015_exists);
                elaborationRule584 = addElaborationRule(condition584, _17_0_5_edc0357_1346100467245_506694_13577.this, LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1346101429843_239121_14015.class, "updateGenVal_CallBehaviorAction_LADWP_CB", arguments584);
            }

            public _17_0_5_edc0357_1346101350907_438807_13981() {
                super();
                init_17_0_5_edc0357_1346101350907_438807_13981Members();
                init_17_0_5_edc0357_1346101350907_438807_13981Collections();
                init_17_0_5_edc0357_1346101350907_438807_13981Elaborations();
            }

            public _17_0_5_edc0357_1346101350907_438807_13981(Expression<Integer> _17_0_5_edc0357_1346101323493_940716_13958_endTime, Expression<Power_System.SignalreceiveGenReading> _17_0_5_edc0357_1346101356139_450740_13994) {
                super();
                init_17_0_5_edc0357_1346101350907_438807_13981Members();
                init_17_0_5_edc0357_1346101350907_438807_13981Collections();
                addDependency(this._17_0_5_edc0357_1346101323493_940716_13958_endTime, _17_0_5_edc0357_1346101323493_940716_13958_endTime);
                addDependency(this._17_0_5_edc0357_1346101356139_450740_13994, _17_0_5_edc0357_1346101356139_450740_13994);
                init_17_0_5_edc0357_1346101350907_438807_13981Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1346101429843_239121_14015 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1346101486727_93891_14069_exists = null;

            public IntegerParameter _17_0_5_edc0357_1346101437766_368293_14030 = null;

            public IntegerParameter _17_0_5_edc0357_1346101350907_438807_13981_endTime = null;

            public IntegerParameter cba_endTime = null;

            public ConstraintExpression constraint585 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346101486727_93891_14069_existsDependency = null;

            public ElaborationRule elaborationRule586 = null;

            public ElaborationRule elaborationRule587 = null;

            public void init_17_0_5_edc0357_1346101429843_239121_14015Members() {
                try {
                    _17_0_5_edc0357_1346101486727_93891_14069_exists = new BooleanParameter("_17_0_5_edc0357_1346101486727_93891_14069_exists", this);
                    _17_0_5_edc0357_1346101437766_368293_14030 = new IntegerParameter("_17_0_5_edc0357_1346101437766_368293_14030", this);
                    _17_0_5_edc0357_1346101350907_438807_13981_endTime = new IntegerParameter("_17_0_5_edc0357_1346101350907_438807_13981_endTime", this);
                    cba_endTime = new IntegerParameter("cba_endTime", this);
                    constraint585 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346101350907_438807_13981_endTime)));
                    _17_0_5_edc0357_1346101486727_93891_14069_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346101486727_93891_14069_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346101429843_239121_14015Collections() {
                parameters.add(_17_0_5_edc0357_1346101486727_93891_14069_exists);
                parameters.add(_17_0_5_edc0357_1346101437766_368293_14030);
                parameters.add(_17_0_5_edc0357_1346101350907_438807_13981_endTime);
                parameters.add(cba_endTime);
                constraintExpressions.add(constraint585);
                dependencies.add(_17_0_5_edc0357_1346101486727_93891_14069_existsDependency);
            }

            public void init_17_0_5_edc0357_1346101429843_239121_14015Elaborations() {
                Expression<?>[] arguments586 = new Expression<?>[2];
                arguments586[0] = new Expression<Integer>(cba_endTime);
                arguments586[1] = new Expression<Integer>(_17_0_5_edc0357_1346101437766_368293_14030);
                Expression<Boolean> condition586 = new Expression<Boolean>(true);
                elaborationRule586 = addElaborationRule(condition586, LADWP.this, LADWP._17_0_5_edc0357_1345510113598_693480_13830.class, "updateGeneration_Activity_LADWP", arguments586);
                Expression<?>[] arguments587 = new Expression<?>[1];
                arguments587[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition587 = new Expression<Boolean>(_17_0_5_edc0357_1346101486727_93891_14069_exists);
                elaborationRule587 = addElaborationRule(condition587, _17_0_5_edc0357_1346100467245_506694_13577.this, LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1346101486727_93891_14069.class, "mrrgr_MergeNode_LADWP_CB", arguments587);
            }

            public _17_0_5_edc0357_1346101429843_239121_14015() {
                super();
                init_17_0_5_edc0357_1346101429843_239121_14015Members();
                init_17_0_5_edc0357_1346101429843_239121_14015Collections();
                init_17_0_5_edc0357_1346101429843_239121_14015Elaborations();
            }

            public _17_0_5_edc0357_1346101429843_239121_14015(Expression<Integer> _17_0_5_edc0357_1346101350907_438807_13981_endTime, Expression<Integer> _17_0_5_edc0357_1346101437766_368293_14030) {
                super();
                init_17_0_5_edc0357_1346101429843_239121_14015Members();
                init_17_0_5_edc0357_1346101429843_239121_14015Collections();
                addDependency(this._17_0_5_edc0357_1346101350907_438807_13981_endTime, _17_0_5_edc0357_1346101350907_438807_13981_endTime);
                addDependency(this._17_0_5_edc0357_1346101437766_368293_14030, _17_0_5_edc0357_1346101437766_368293_14030);
                init_17_0_5_edc0357_1346101429843_239121_14015Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1346101486727_93891_14069 extends DurativeEvent {

            public IntegerParameter invoker_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1346101323493_940716_13958_exists = null;

            public ConstraintExpression constraint588 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346101323493_940716_13958_existsDependency = null;

            public ElaborationRule elaborationRule589 = null;

            public void init_17_0_5_edc0357_1346101486727_93891_14069Members() {
                try {
                    invoker_endTime = new IntegerParameter("invoker_endTime", this);
                    _17_0_5_edc0357_1346101323493_940716_13958_exists = new BooleanParameter("_17_0_5_edc0357_1346101323493_940716_13958_exists", this);
                    constraint588 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(invoker_endTime)));
                    _17_0_5_edc0357_1346101323493_940716_13958_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346101323493_940716_13958_exists, new Expression(new FunctionCall(q_LADWP_receiveGenReading, Utils.getMethodForArgTypes("ObjectFlow<SignalreceiveGenReading>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346101486727_93891_14069Collections() {
                parameters.add(invoker_endTime);
                parameters.add(_17_0_5_edc0357_1346101323493_940716_13958_exists);
                constraintExpressions.add(constraint588);
                dependencies.add(_17_0_5_edc0357_1346101323493_940716_13958_existsDependency);
            }

            public void init_17_0_5_edc0357_1346101486727_93891_14069Elaborations() {
                Expression<?>[] arguments589 = new Expression<?>[1];
                arguments589[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition589 = new Expression<Boolean>(_17_0_5_edc0357_1346101323493_940716_13958_exists);
                elaborationRule589 = addElaborationRule(condition589, _17_0_5_edc0357_1346100467245_506694_13577.this, LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1346101323493_940716_13958.class, "_AcceptEventAction_LADWP_CB", arguments589);
            }

            public _17_0_5_edc0357_1346101486727_93891_14069() {
                super();
                init_17_0_5_edc0357_1346101486727_93891_14069Members();
                init_17_0_5_edc0357_1346101486727_93891_14069Collections();
                init_17_0_5_edc0357_1346101486727_93891_14069Elaborations();
            }

            public _17_0_5_edc0357_1346101486727_93891_14069(Expression<Integer> invoker_endTime) {
                super();
                init_17_0_5_edc0357_1346101486727_93891_14069Members();
                init_17_0_5_edc0357_1346101486727_93891_14069Collections();
                addDependency(this.invoker_endTime, invoker_endTime);
                init_17_0_5_edc0357_1346101486727_93891_14069Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1346101533989_302317_14094 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1346103876583_585391_14750_exists = null;

            public IntegerParameter _17_0_5_edc0357_1346101765241_743667_14225_endTime = null;

            public IntegerParameter cba_endTime = null;

            public ConstraintExpression constraint590 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346103876583_585391_14750_existsDependency = null;

            public ElaborationRule elaborationRule591 = null;

            public ElaborationRule elaborationRule592 = null;

            public void init_17_0_5_edc0357_1346101533989_302317_14094Members() {
                try {
                    _17_0_5_edc0357_1346103876583_585391_14750_exists = new BooleanParameter("_17_0_5_edc0357_1346103876583_585391_14750_exists", this);
                    _17_0_5_edc0357_1346101765241_743667_14225_endTime = new IntegerParameter("_17_0_5_edc0357_1346101765241_743667_14225_endTime", this);
                    cba_endTime = new IntegerParameter("cba_endTime", this);
                    constraint590 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346101765241_743667_14225_endTime)));
                    _17_0_5_edc0357_1346103876583_585391_14750_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346103876583_585391_14750_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346101533989_302317_14094Collections() {
                parameters.add(_17_0_5_edc0357_1346103876583_585391_14750_exists);
                parameters.add(_17_0_5_edc0357_1346101765241_743667_14225_endTime);
                parameters.add(cba_endTime);
                constraintExpressions.add(constraint590);
                dependencies.add(_17_0_5_edc0357_1346103876583_585391_14750_existsDependency);
            }

            public void init_17_0_5_edc0357_1346101533989_302317_14094Elaborations() {
                Expression<?>[] arguments591 = new Expression<?>[1];
                arguments591[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition591 = new Expression<Boolean>(_17_0_5_edc0357_1346103876583_585391_14750_exists);
                elaborationRule591 = addElaborationRule(condition591, _17_0_5_edc0357_1346100467245_506694_13577.this, LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1346103876583_585391_14750.class, "readself_ReadSelfAction_LADWP_CB", arguments591);
                Expression<?>[] arguments592 = new Expression<?>[1];
                arguments592[0] = new Expression<Integer>(cba_endTime);
                Expression<Boolean> condition592 = new Expression<Boolean>(true);
                elaborationRule592 = addElaborationRule(condition592, LADWP.this, LADWP._17_0_5_edc0357_1345510113599_525430_13831.class, "monitor_system_Activity_LADWP", arguments592);
            }

            public _17_0_5_edc0357_1346101533989_302317_14094() {
                super();
                init_17_0_5_edc0357_1346101533989_302317_14094Members();
                init_17_0_5_edc0357_1346101533989_302317_14094Collections();
                init_17_0_5_edc0357_1346101533989_302317_14094Elaborations();
            }

            public _17_0_5_edc0357_1346101533989_302317_14094(Expression<Integer> _17_0_5_edc0357_1346101765241_743667_14225_endTime) {
                super();
                init_17_0_5_edc0357_1346101533989_302317_14094Members();
                init_17_0_5_edc0357_1346101533989_302317_14094Collections();
                addDependency(this._17_0_5_edc0357_1346101765241_743667_14225_endTime, _17_0_5_edc0357_1346101765241_743667_14225_endTime);
                init_17_0_5_edc0357_1346101533989_302317_14094Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1346101621154_926007_14131 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1346103858168_44487_14734_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1346102209293_902398_14297_exists = null;

            public IntegerParameter cba_endTime = null;

            public ConstraintExpression constraint593 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346102209293_902398_14297_existsDependency = null;

            public ElaborationRule elaborationRule594 = null;

            public ElaborationRule elaborationRule595 = null;

            public void init_17_0_5_edc0357_1346101621154_926007_14131Members() {
                try {
                    _17_0_5_edc0357_1346103858168_44487_14734_endTime = new IntegerParameter("_17_0_5_edc0357_1346103858168_44487_14734_endTime", this);
                    _17_0_5_edc0357_1346102209293_902398_14297_exists = new BooleanParameter("_17_0_5_edc0357_1346102209293_902398_14297_exists", this);
                    cba_endTime = new IntegerParameter("cba_endTime", this);
                    constraint593 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346103858168_44487_14734_endTime)));
                    _17_0_5_edc0357_1346102209293_902398_14297_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346102209293_902398_14297_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346101621154_926007_14131Collections() {
                parameters.add(_17_0_5_edc0357_1346103858168_44487_14734_endTime);
                parameters.add(_17_0_5_edc0357_1346102209293_902398_14297_exists);
                parameters.add(cba_endTime);
                constraintExpressions.add(constraint593);
                dependencies.add(_17_0_5_edc0357_1346102209293_902398_14297_existsDependency);
            }

            public void init_17_0_5_edc0357_1346101621154_926007_14131Elaborations() {
                Expression<?>[] arguments594 = new Expression<?>[1];
                arguments594[0] = new Expression<Integer>(cba_endTime);
                Expression<Boolean> condition594 = new Expression<Boolean>(true);
                elaborationRule594 = addElaborationRule(condition594, LADWP.this, LADWP._17_0_5_edc0357_1345510113600_989123_13832.class, "demand_response_Activity_LADWP", arguments594);
                Expression<?>[] arguments595 = new Expression<?>[1];
                arguments595[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition595 = new Expression<Boolean>(_17_0_5_edc0357_1346102209293_902398_14297_exists);
                elaborationRule595 = addElaborationRule(condition595, _17_0_5_edc0357_1346100467245_506694_13577.this, LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1346102209293_902398_14297.class, "_ForkNode_LADWP_CB", arguments595);
            }

            public _17_0_5_edc0357_1346101621154_926007_14131() {
                super();
                init_17_0_5_edc0357_1346101621154_926007_14131Members();
                init_17_0_5_edc0357_1346101621154_926007_14131Collections();
                init_17_0_5_edc0357_1346101621154_926007_14131Elaborations();
            }

            public _17_0_5_edc0357_1346101621154_926007_14131(Expression<Integer> _17_0_5_edc0357_1346103858168_44487_14734_endTime) {
                super();
                init_17_0_5_edc0357_1346101621154_926007_14131Members();
                init_17_0_5_edc0357_1346101621154_926007_14131Collections();
                addDependency(this._17_0_5_edc0357_1346103858168_44487_14734_endTime, _17_0_5_edc0357_1346103858168_44487_14734_endTime);
                init_17_0_5_edc0357_1346101621154_926007_14131Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1346101629323_369188_14149 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1346101765241_743667_14225_exists = null;

            public IntegerParameter _17_0_5_edc0357_1346101664083_228321_14184_endTime = null;

            public IntegerParameter cba_endTime = null;

            public ConstraintExpression constraint596 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346101765241_743667_14225_existsDependency = null;

            public ElaborationRule elaborationRule597 = null;

            public ElaborationRule elaborationRule598 = null;

            public void init_17_0_5_edc0357_1346101629323_369188_14149Members() {
                try {
                    _17_0_5_edc0357_1346101765241_743667_14225_exists = new BooleanParameter("_17_0_5_edc0357_1346101765241_743667_14225_exists", this);
                    _17_0_5_edc0357_1346101664083_228321_14184_endTime = new IntegerParameter("_17_0_5_edc0357_1346101664083_228321_14184_endTime", this);
                    cba_endTime = new IntegerParameter("cba_endTime", this);
                    constraint596 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346101664083_228321_14184_endTime)));
                    _17_0_5_edc0357_1346101765241_743667_14225_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346101765241_743667_14225_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346101629323_369188_14149Collections() {
                parameters.add(_17_0_5_edc0357_1346101765241_743667_14225_exists);
                parameters.add(_17_0_5_edc0357_1346101664083_228321_14184_endTime);
                parameters.add(cba_endTime);
                constraintExpressions.add(constraint596);
                dependencies.add(_17_0_5_edc0357_1346101765241_743667_14225_existsDependency);
            }

            public void init_17_0_5_edc0357_1346101629323_369188_14149Elaborations() {
                Expression<?>[] arguments597 = new Expression<?>[1];
                arguments597[0] = new Expression<Integer>(cba_endTime);
                Expression<Boolean> condition597 = new Expression<Boolean>(true);
                elaborationRule597 = addElaborationRule(condition597, LADWP.this, LADWP._17_0_5_edc0357_1345510113602_28656_13834.class, "generatePowerNow_Activity_LADWP", arguments597);
                Expression<?>[] arguments598 = new Expression<?>[1];
                arguments598[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition598 = new Expression<Boolean>(_17_0_5_edc0357_1346101765241_743667_14225_exists);
                elaborationRule598 = addElaborationRule(condition598, _17_0_5_edc0357_1346100467245_506694_13577.this, LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1346101765241_743667_14225.class, "_MergeNode_LADWP_CB", arguments598);
            }

            public _17_0_5_edc0357_1346101629323_369188_14149() {
                super();
                init_17_0_5_edc0357_1346101629323_369188_14149Members();
                init_17_0_5_edc0357_1346101629323_369188_14149Collections();
                init_17_0_5_edc0357_1346101629323_369188_14149Elaborations();
            }

            public _17_0_5_edc0357_1346101629323_369188_14149(Expression<Integer> _17_0_5_edc0357_1346101664083_228321_14184_endTime) {
                super();
                init_17_0_5_edc0357_1346101629323_369188_14149Members();
                init_17_0_5_edc0357_1346101629323_369188_14149Collections();
                addDependency(this._17_0_5_edc0357_1346101664083_228321_14184_endTime, _17_0_5_edc0357_1346101664083_228321_14184_endTime);
                init_17_0_5_edc0357_1346101629323_369188_14149Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1346101631180_812549_14165 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1346102275843_86387_14350_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1346106459827_751417_15369_exists = null;

            public IntegerParameter cba_endTime = null;

            public ConstraintExpression constraint599 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346106459827_751417_15369_existsDependency = null;

            public ElaborationRule elaborationRule600 = null;

            public ElaborationRule elaborationRule601 = null;

            public void init_17_0_5_edc0357_1346101631180_812549_14165Members() {
                try {
                    _17_0_5_edc0357_1346102275843_86387_14350_endTime = new IntegerParameter("_17_0_5_edc0357_1346102275843_86387_14350_endTime", this);
                    _17_0_5_edc0357_1346106459827_751417_15369_exists = new BooleanParameter("_17_0_5_edc0357_1346106459827_751417_15369_exists", this);
                    cba_endTime = new IntegerParameter("cba_endTime", this);
                    constraint599 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346102275843_86387_14350_endTime)));
                    _17_0_5_edc0357_1346106459827_751417_15369_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346106459827_751417_15369_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346101631180_812549_14165Collections() {
                parameters.add(_17_0_5_edc0357_1346102275843_86387_14350_endTime);
                parameters.add(_17_0_5_edc0357_1346106459827_751417_15369_exists);
                parameters.add(cba_endTime);
                constraintExpressions.add(constraint599);
                dependencies.add(_17_0_5_edc0357_1346106459827_751417_15369_existsDependency);
            }

            public void init_17_0_5_edc0357_1346101631180_812549_14165Elaborations() {
                Expression<?>[] arguments600 = new Expression<?>[1];
                arguments600[0] = new Expression<Integer>(cba_endTime);
                Expression<Boolean> condition600 = new Expression<Boolean>(true);
                elaborationRule600 = addElaborationRule(condition600, LADWP.this, LADWP._17_0_5_edc0357_1345510113602_419995_13835.class, "generatePowerLater_Activity_LADWP", arguments600);
                Expression<?>[] arguments601 = new Expression<?>[1];
                arguments601[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition601 = new Expression<Boolean>(_17_0_5_edc0357_1346106459827_751417_15369_exists);
                elaborationRule601 = addElaborationRule(condition601, _17_0_5_edc0357_1346100467245_506694_13577.this, LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1346106459827_751417_15369.class, "_MergeNode_LADWP_CB", arguments601);
            }

            public _17_0_5_edc0357_1346101631180_812549_14165() {
                super();
                init_17_0_5_edc0357_1346101631180_812549_14165Members();
                init_17_0_5_edc0357_1346101631180_812549_14165Collections();
                init_17_0_5_edc0357_1346101631180_812549_14165Elaborations();
            }

            public _17_0_5_edc0357_1346101631180_812549_14165(Expression<Integer> _17_0_5_edc0357_1346102275843_86387_14350_endTime) {
                super();
                init_17_0_5_edc0357_1346101631180_812549_14165Members();
                init_17_0_5_edc0357_1346101631180_812549_14165Collections();
                addDependency(this._17_0_5_edc0357_1346102275843_86387_14350_endTime, _17_0_5_edc0357_1346102275843_86387_14350_endTime);
                init_17_0_5_edc0357_1346101631180_812549_14165Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1346101664083_228321_14184 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1346103858168_44487_14734_exists = null;

            public IntegerParameter _17_0_5_edc0357_1346103876583_585391_14750_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1346101629323_369188_14149_exists = null;

            public BooleanParameter _17_0_5_edc0357_1346103964252_49821_14797 = null;

            public ConstraintExpression constraint602 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346103858168_44487_14734_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346101629323_369188_14149_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346103964252_49821_14797Dependency = null;

            public ElaborationRule elaborationRule603 = null;

            public ElaborationRule elaborationRule604 = null;

            public void init_17_0_5_edc0357_1346101664083_228321_14184Members() {
                try {
                    _17_0_5_edc0357_1346103858168_44487_14734_exists = new BooleanParameter("_17_0_5_edc0357_1346103858168_44487_14734_exists", this);
                    _17_0_5_edc0357_1346103876583_585391_14750_endTime = new IntegerParameter("_17_0_5_edc0357_1346103876583_585391_14750_endTime", this);
                    _17_0_5_edc0357_1346101629323_369188_14149_exists = new BooleanParameter("_17_0_5_edc0357_1346101629323_369188_14149_exists", this);
                    _17_0_5_edc0357_1346103964252_49821_14797 = new BooleanParameter("_17_0_5_edc0357_1346103964252_49821_14797", this);
                    constraint602 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346103876583_585391_14750_endTime)));
                    _17_0_5_edc0357_1346103858168_44487_14734_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346103858168_44487_14734_exists, new Functions.And(new Functions.Equals(new Expression<Boolean>(_17_0_5_edc0357_1346103964252_49821_14797), new Expression<Boolean>(false)), new Expression(new FunctionCall(sig_17_0_5_edc0357_1346104106512_507599_14867, Utils.getMethodForArgTypes("ObjectFlow<Boolean>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) }))));
                    _17_0_5_edc0357_1346101629323_369188_14149_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346101629323_369188_14149_exists, new Expression<Boolean>(_17_0_5_edc0357_1346103964252_49821_14797));
                    _17_0_5_edc0357_1346103964252_49821_14797Dependency = new Dependency<Boolean>(_17_0_5_edc0357_1346103964252_49821_14797, new Expression(new FunctionCall(sig_17_0_5_edc0357_1346103964252_49821_14797, Utils.getMethodForArgTypes("ObjectFlow<Boolean>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346101664083_228321_14184Collections() {
                parameters.add(_17_0_5_edc0357_1346103858168_44487_14734_exists);
                parameters.add(_17_0_5_edc0357_1346103876583_585391_14750_endTime);
                parameters.add(_17_0_5_edc0357_1346101629323_369188_14149_exists);
                parameters.add(_17_0_5_edc0357_1346103964252_49821_14797);
                constraintExpressions.add(constraint602);
                dependencies.add(_17_0_5_edc0357_1346103858168_44487_14734_existsDependency);
                dependencies.add(_17_0_5_edc0357_1346101629323_369188_14149_existsDependency);
                dependencies.add(_17_0_5_edc0357_1346103964252_49821_14797Dependency);
            }

            public void init_17_0_5_edc0357_1346101664083_228321_14184Elaborations() {
                Expression<?>[] arguments603 = new Expression<?>[1];
                arguments603[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition603 = new Expression<Boolean>(_17_0_5_edc0357_1346101629323_369188_14149_exists);
                elaborationRule603 = addElaborationRule(condition603, _17_0_5_edc0357_1346100467245_506694_13577.this, LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1346101629323_369188_14149.class, "genPowerNow_CallBehaviorAction_LADWP_CB", arguments603);
                Expression<?>[] arguments604 = new Expression<?>[1];
                arguments604[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition604 = new Expression<Boolean>(_17_0_5_edc0357_1346103858168_44487_14734_exists);
                elaborationRule604 = addElaborationRule(condition604, _17_0_5_edc0357_1346100467245_506694_13577.this, LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1346103858168_44487_14734.class, "_DecisionNode_LADWP_CB", arguments604);
            }

            public _17_0_5_edc0357_1346101664083_228321_14184() {
                super();
                init_17_0_5_edc0357_1346101664083_228321_14184Members();
                init_17_0_5_edc0357_1346101664083_228321_14184Collections();
                init_17_0_5_edc0357_1346101664083_228321_14184Elaborations();
            }

            public _17_0_5_edc0357_1346101664083_228321_14184(Expression<Integer> _17_0_5_edc0357_1346103876583_585391_14750_endTime) {
                super();
                init_17_0_5_edc0357_1346101664083_228321_14184Members();
                init_17_0_5_edc0357_1346101664083_228321_14184Collections();
                addDependency(this._17_0_5_edc0357_1346103876583_585391_14750_endTime, _17_0_5_edc0357_1346103876583_585391_14750_endTime);
                init_17_0_5_edc0357_1346101664083_228321_14184Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1346101765241_743667_14225 extends DurativeEvent {

            public IntegerParameter invoker_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1346101533989_302317_14094_exists = null;

            public ConstraintExpression constraint605 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346101533989_302317_14094_existsDependency = null;

            public ElaborationRule elaborationRule606 = null;

            public void init_17_0_5_edc0357_1346101765241_743667_14225Members() {
                try {
                    invoker_endTime = new IntegerParameter("invoker_endTime", this);
                    _17_0_5_edc0357_1346101533989_302317_14094_exists = new BooleanParameter("_17_0_5_edc0357_1346101533989_302317_14094_exists", this);
                    constraint605 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(invoker_endTime)));
                    _17_0_5_edc0357_1346101533989_302317_14094_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346101533989_302317_14094_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346101765241_743667_14225Collections() {
                parameters.add(invoker_endTime);
                parameters.add(_17_0_5_edc0357_1346101533989_302317_14094_exists);
                constraintExpressions.add(constraint605);
                dependencies.add(_17_0_5_edc0357_1346101533989_302317_14094_existsDependency);
            }

            public void init_17_0_5_edc0357_1346101765241_743667_14225Elaborations() {
                Expression<?>[] arguments606 = new Expression<?>[1];
                arguments606[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition606 = new Expression<Boolean>(_17_0_5_edc0357_1346101533989_302317_14094_exists);
                elaborationRule606 = addElaborationRule(condition606, _17_0_5_edc0357_1346100467245_506694_13577.this, LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1346101533989_302317_14094.class, "monit_CallBehaviorAction_LADWP_CB", arguments606);
            }

            public _17_0_5_edc0357_1346101765241_743667_14225() {
                super();
                init_17_0_5_edc0357_1346101765241_743667_14225Members();
                init_17_0_5_edc0357_1346101765241_743667_14225Collections();
                init_17_0_5_edc0357_1346101765241_743667_14225Elaborations();
            }

            public _17_0_5_edc0357_1346101765241_743667_14225(Expression<Integer> invoker_endTime) {
                super();
                init_17_0_5_edc0357_1346101765241_743667_14225Members();
                init_17_0_5_edc0357_1346101765241_743667_14225Collections();
                addDependency(this.invoker_endTime, invoker_endTime);
                init_17_0_5_edc0357_1346101765241_743667_14225Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1346102113184_840698_14284 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1346102888530_55753_14505 = null;

            public IntegerParameter _17_0_5_edc0357_1346102209293_902398_14297_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1348001608547_58915_14306_exists = null;

            public BooleanParameter _17_0_5_edc0357_1346102275843_86387_14350_exists = null;

            public IntegerParameter cba_endTime = null;

            public ConstraintExpression constraint607 = null;

            public Dependency< Integer > _17_0_5_edc0357_1346102888530_55753_14505Dependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1348001608547_58915_14306_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346102275843_86387_14350_existsDependency = null;

            public ElaborationRule elaborationRule608 = null;

            public ElaborationRule elaborationRule609 = null;

            public ElaborationRule elaborationRule610 = null;

            public void init_17_0_5_edc0357_1346102113184_840698_14284Members() {
                try {
                    _17_0_5_edc0357_1346102888530_55753_14505 = new IntegerParameter("_17_0_5_edc0357_1346102888530_55753_14505", this);
                    _17_0_5_edc0357_1346102209293_902398_14297_endTime = new IntegerParameter("_17_0_5_edc0357_1346102209293_902398_14297_endTime", this);
                    _17_0_5_edc0357_1348001608547_58915_14306_exists = new BooleanParameter("_17_0_5_edc0357_1348001608547_58915_14306_exists", this);
                    _17_0_5_edc0357_1346102275843_86387_14350_exists = new BooleanParameter("_17_0_5_edc0357_1346102275843_86387_14350_exists", this);
                    cba_endTime = new IntegerParameter("cba_endTime", this);
                    constraint607 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346102209293_902398_14297_endTime)));
                    _17_0_5_edc0357_1346102888530_55753_14505Dependency = new Dependency<Integer>(_17_0_5_edc0357_1346102888530_55753_14505, new Expression(new FunctionCall(sig_17_0_5_edc0357_1346102889543_283441_14507, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(endTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1348001608547_58915_14306_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1348001608547_58915_14306_exists, new Expression<Boolean>(true));
                    _17_0_5_edc0357_1346102275843_86387_14350_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346102275843_86387_14350_exists, new Expression(new FunctionCall(sig_17_0_5_edc0357_1348001609357_382848_14312, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346102113184_840698_14284Collections() {
                parameters.add(_17_0_5_edc0357_1346102888530_55753_14505);
                parameters.add(_17_0_5_edc0357_1346102209293_902398_14297_endTime);
                parameters.add(_17_0_5_edc0357_1348001608547_58915_14306_exists);
                parameters.add(_17_0_5_edc0357_1346102275843_86387_14350_exists);
                parameters.add(cba_endTime);
                constraintExpressions.add(constraint607);
                dependencies.add(_17_0_5_edc0357_1346102888530_55753_14505Dependency);
                dependencies.add(_17_0_5_edc0357_1348001608547_58915_14306_existsDependency);
                dependencies.add(_17_0_5_edc0357_1346102275843_86387_14350_existsDependency);
            }

            public void init_17_0_5_edc0357_1346102113184_840698_14284Elaborations() {
                Expression<?>[] arguments608 = new Expression<?>[1];
                arguments608[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition608 = new Expression<Boolean>(_17_0_5_edc0357_1346102275843_86387_14350_exists);
                elaborationRule608 = addElaborationRule(condition608, _17_0_5_edc0357_1346100467245_506694_13577.this, LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1346102275843_86387_14350.class, "mnmnnnn_DecisionNode_LADWP_CB", arguments608);
                Expression<?>[] arguments609 = new Expression<?>[2];
                arguments609[0] = new Expression<Integer>(endTime);
                arguments609[1] = new Expression<Integer>(_17_0_5_edc0357_1346102888530_55753_14505);
                Expression<Boolean> condition609 = new Expression<Boolean>(_17_0_5_edc0357_1348001608547_58915_14306_exists);
                elaborationRule609 = addElaborationRule(condition609, _17_0_5_edc0357_1346100467245_506694_13577.this, LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1348001608547_58915_14306.class, "fnnn_ForkNode_LADWP_CB", arguments609);
                Expression<?>[] arguments610 = new Expression<?>[2];
                arguments610[0] = new Expression<Integer>(cba_endTime);
                arguments610[1] = new Expression<ObjectFlow<Integer>>(sig_17_0_5_edc0357_1346102889543_283441_14507);
                Expression<Boolean> condition610 = new Expression<Boolean>(true);
                elaborationRule610 = addElaborationRule(condition610, LADWP.this, LADWP._17_0_5_edc0357_1346102095091_571012_14257.class, "waitForDRResponse_Activity_LADWP", arguments610);
            }

            public _17_0_5_edc0357_1346102113184_840698_14284() {
                super();
                init_17_0_5_edc0357_1346102113184_840698_14284Members();
                init_17_0_5_edc0357_1346102113184_840698_14284Collections();
                init_17_0_5_edc0357_1346102113184_840698_14284Elaborations();
            }

            public _17_0_5_edc0357_1346102113184_840698_14284(Expression<Integer> _17_0_5_edc0357_1346102209293_902398_14297_endTime) {
                super();
                init_17_0_5_edc0357_1346102113184_840698_14284Members();
                init_17_0_5_edc0357_1346102113184_840698_14284Collections();
                addDependency(this._17_0_5_edc0357_1346102209293_902398_14297_endTime, _17_0_5_edc0357_1346102209293_902398_14297_endTime);
                init_17_0_5_edc0357_1346102113184_840698_14284Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1346102209293_902398_14297 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1346101765241_743667_14225_exists = null;

            public IntegerParameter _17_0_5_edc0357_1346101621154_926007_14131_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1346102113184_840698_14284_exists = null;

            public ConstraintExpression constraint611 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346101765241_743667_14225_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346102113184_840698_14284_existsDependency = null;

            public ElaborationRule elaborationRule612 = null;

            public ElaborationRule elaborationRule613 = null;

            public void init_17_0_5_edc0357_1346102209293_902398_14297Members() {
                try {
                    _17_0_5_edc0357_1346101765241_743667_14225_exists = new BooleanParameter("_17_0_5_edc0357_1346101765241_743667_14225_exists", this);
                    _17_0_5_edc0357_1346101621154_926007_14131_endTime = new IntegerParameter("_17_0_5_edc0357_1346101621154_926007_14131_endTime", this);
                    _17_0_5_edc0357_1346102113184_840698_14284_exists = new BooleanParameter("_17_0_5_edc0357_1346102113184_840698_14284_exists", this);
                    constraint611 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346101621154_926007_14131_endTime)));
                    _17_0_5_edc0357_1346101765241_743667_14225_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346101765241_743667_14225_exists, new Expression<Boolean>(true));
                    _17_0_5_edc0357_1346102113184_840698_14284_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346102113184_840698_14284_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346102209293_902398_14297Collections() {
                parameters.add(_17_0_5_edc0357_1346101765241_743667_14225_exists);
                parameters.add(_17_0_5_edc0357_1346101621154_926007_14131_endTime);
                parameters.add(_17_0_5_edc0357_1346102113184_840698_14284_exists);
                constraintExpressions.add(constraint611);
                dependencies.add(_17_0_5_edc0357_1346101765241_743667_14225_existsDependency);
                dependencies.add(_17_0_5_edc0357_1346102113184_840698_14284_existsDependency);
            }

            public void init_17_0_5_edc0357_1346102209293_902398_14297Elaborations() {
                Expression<?>[] arguments612 = new Expression<?>[1];
                arguments612[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition612 = new Expression<Boolean>(_17_0_5_edc0357_1346101765241_743667_14225_exists);
                elaborationRule612 = addElaborationRule(condition612, _17_0_5_edc0357_1346100467245_506694_13577.this, LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1346101765241_743667_14225.class, "_MergeNode_LADWP_CB", arguments612);
                Expression<?>[] arguments613 = new Expression<?>[1];
                arguments613[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition613 = new Expression<Boolean>(_17_0_5_edc0357_1346102113184_840698_14284_exists);
                elaborationRule613 = addElaborationRule(condition613, _17_0_5_edc0357_1346100467245_506694_13577.this, LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1346102113184_840698_14284.class, "waitForDRResp_CallBehaviorAction_LADWP_CB", arguments613);
            }

            public _17_0_5_edc0357_1346102209293_902398_14297() {
                super();
                init_17_0_5_edc0357_1346102209293_902398_14297Members();
                init_17_0_5_edc0357_1346102209293_902398_14297Collections();
                init_17_0_5_edc0357_1346102209293_902398_14297Elaborations();
            }

            public _17_0_5_edc0357_1346102209293_902398_14297(Expression<Integer> _17_0_5_edc0357_1346101621154_926007_14131_endTime) {
                super();
                init_17_0_5_edc0357_1346102209293_902398_14297Members();
                init_17_0_5_edc0357_1346102209293_902398_14297Collections();
                addDependency(this._17_0_5_edc0357_1346101621154_926007_14131_endTime, _17_0_5_edc0357_1346101621154_926007_14131_endTime);
                init_17_0_5_edc0357_1346102209293_902398_14297Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1346102261726_372521_14337 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1346102275843_86387_14350_endTime = null;

            public IntegerParameter _17_0_5_edc0357_1348001555541_398388_14280 = null;

            public BooleanParameter _17_0_5_edc0357_1346106459827_751417_15369_exists = null;

            public IntegerParameter cba_endTime = null;

            public ConstraintExpression constraint614 = null;

            public Dependency< Integer > _17_0_5_edc0357_1348001555541_398388_14280Dependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346106459827_751417_15369_existsDependency = null;

            public ElaborationRule elaborationRule615 = null;

            public ElaborationRule elaborationRule616 = null;

            public void init_17_0_5_edc0357_1346102261726_372521_14337Members() {
                try {
                    _17_0_5_edc0357_1346102275843_86387_14350_endTime = new IntegerParameter("_17_0_5_edc0357_1346102275843_86387_14350_endTime", this);
                    _17_0_5_edc0357_1348001555541_398388_14280 = new IntegerParameter("_17_0_5_edc0357_1348001555541_398388_14280", this);
                    _17_0_5_edc0357_1346106459827_751417_15369_exists = new BooleanParameter("_17_0_5_edc0357_1346106459827_751417_15369_exists", this);
                    cba_endTime = new IntegerParameter("cba_endTime", this);
                    constraint614 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346102275843_86387_14350_endTime)));
                    _17_0_5_edc0357_1348001555541_398388_14280Dependency = new Dependency<Integer>(_17_0_5_edc0357_1348001555541_398388_14280, new Expression(new FunctionCall(sig_17_0_5_edc0357_1348001642582_861167_14319, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1346106459827_751417_15369_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346106459827_751417_15369_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346102261726_372521_14337Collections() {
                parameters.add(_17_0_5_edc0357_1346102275843_86387_14350_endTime);
                parameters.add(_17_0_5_edc0357_1348001555541_398388_14280);
                parameters.add(_17_0_5_edc0357_1346106459827_751417_15369_exists);
                parameters.add(cba_endTime);
                constraintExpressions.add(constraint614);
                dependencies.add(_17_0_5_edc0357_1348001555541_398388_14280Dependency);
                dependencies.add(_17_0_5_edc0357_1346106459827_751417_15369_existsDependency);
            }

            public void init_17_0_5_edc0357_1346102261726_372521_14337Elaborations() {
                Expression<?>[] arguments615 = new Expression<?>[2];
                arguments615[0] = new Expression<Integer>(cba_endTime);
                arguments615[1] = new Expression<Integer>(_17_0_5_edc0357_1348001555541_398388_14280);
                Expression<Boolean> condition615 = new Expression<Boolean>(true);
                elaborationRule615 = addElaborationRule(condition615, LADWP.this, LADWP._17_0_5_edc0357_1345510113601_377571_13833.class, "updateExpectedLoad_Activity_LADWP", arguments615);
                Expression<?>[] arguments616 = new Expression<?>[1];
                arguments616[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition616 = new Expression<Boolean>(_17_0_5_edc0357_1346106459827_751417_15369_exists);
                elaborationRule616 = addElaborationRule(condition616, _17_0_5_edc0357_1346100467245_506694_13577.this, LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1346106459827_751417_15369.class, "_MergeNode_LADWP_CB", arguments616);
            }

            public _17_0_5_edc0357_1346102261726_372521_14337() {
                super();
                init_17_0_5_edc0357_1346102261726_372521_14337Members();
                init_17_0_5_edc0357_1346102261726_372521_14337Collections();
                init_17_0_5_edc0357_1346102261726_372521_14337Elaborations();
            }

            public _17_0_5_edc0357_1346102261726_372521_14337(Expression<Integer> _17_0_5_edc0357_1346102275843_86387_14350_endTime) {
                super();
                init_17_0_5_edc0357_1346102261726_372521_14337Members();
                init_17_0_5_edc0357_1346102261726_372521_14337Collections();
                addDependency(this._17_0_5_edc0357_1346102275843_86387_14350_endTime, _17_0_5_edc0357_1346102275843_86387_14350_endTime);
                init_17_0_5_edc0357_1346102261726_372521_14337Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1346102275843_86387_14350 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1348001609357_382848_14312 = null;

            public IntegerParameter _17_0_5_edc0357_1346102113184_840698_14284_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1346102261726_372521_14337_exists = null;

            public BooleanParameter _17_0_5_edc0357_1346101631180_812549_14165_exists = null;

            public ConstraintExpression constraint617 = null;

            public Dependency< Integer > _17_0_5_edc0357_1348001609357_382848_14312Dependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346102261726_372521_14337_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346101631180_812549_14165_existsDependency = null;

            public ElaborationRule elaborationRule618 = null;

            public ElaborationRule elaborationRule619 = null;

            public void init_17_0_5_edc0357_1346102275843_86387_14350Members() {
                try {
                    _17_0_5_edc0357_1348001609357_382848_14312 = new IntegerParameter("_17_0_5_edc0357_1348001609357_382848_14312", this);
                    _17_0_5_edc0357_1346102113184_840698_14284_endTime = new IntegerParameter("_17_0_5_edc0357_1346102113184_840698_14284_endTime", this);
                    _17_0_5_edc0357_1346102261726_372521_14337_exists = new BooleanParameter("_17_0_5_edc0357_1346102261726_372521_14337_exists", this);
                    _17_0_5_edc0357_1346101631180_812549_14165_exists = new BooleanParameter("_17_0_5_edc0357_1346101631180_812549_14165_exists", this);
                    constraint617 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346102113184_840698_14284_endTime)));
                    _17_0_5_edc0357_1348001609357_382848_14312Dependency = new Dependency<Integer>(_17_0_5_edc0357_1348001609357_382848_14312, new Expression(new FunctionCall(sig_17_0_5_edc0357_1348001609357_382848_14312, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1346102261726_372521_14337_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346102261726_372521_14337_exists, new Functions.And(new Functions.Greater(new Expression<Integer>(_17_0_5_edc0357_1348001609357_382848_14312), new Functions.Negative(new Expression<Integer>(1))), new Expression(new FunctionCall(sig_17_0_5_edc0357_1348001642582_861167_14319, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) }))));
                    _17_0_5_edc0357_1346101631180_812549_14165_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346101631180_812549_14165_exists, new Functions.Less(new Expression<Integer>(_17_0_5_edc0357_1348001609357_382848_14312), new Expression<Integer>(0)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346102275843_86387_14350Collections() {
                parameters.add(_17_0_5_edc0357_1348001609357_382848_14312);
                parameters.add(_17_0_5_edc0357_1346102113184_840698_14284_endTime);
                parameters.add(_17_0_5_edc0357_1346102261726_372521_14337_exists);
                parameters.add(_17_0_5_edc0357_1346101631180_812549_14165_exists);
                constraintExpressions.add(constraint617);
                dependencies.add(_17_0_5_edc0357_1348001609357_382848_14312Dependency);
                dependencies.add(_17_0_5_edc0357_1346102261726_372521_14337_existsDependency);
                dependencies.add(_17_0_5_edc0357_1346101631180_812549_14165_existsDependency);
            }

            public void init_17_0_5_edc0357_1346102275843_86387_14350Elaborations() {
                Expression<?>[] arguments618 = new Expression<?>[1];
                arguments618[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition618 = new Expression<Boolean>(_17_0_5_edc0357_1346102261726_372521_14337_exists);
                elaborationRule618 = addElaborationRule(condition618, _17_0_5_edc0357_1346100467245_506694_13577.this, LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1346102261726_372521_14337.class, "updateExpLoad_CallBehaviorAction_LADWP_CB", arguments618);
                Expression<?>[] arguments619 = new Expression<?>[1];
                arguments619[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition619 = new Expression<Boolean>(_17_0_5_edc0357_1346101631180_812549_14165_exists);
                elaborationRule619 = addElaborationRule(condition619, _17_0_5_edc0357_1346100467245_506694_13577.this, LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1346101631180_812549_14165.class, "genPowerLater_CallBehaviorAction_LADWP_CB", arguments619);
            }

            public _17_0_5_edc0357_1346102275843_86387_14350() {
                super();
                init_17_0_5_edc0357_1346102275843_86387_14350Members();
                init_17_0_5_edc0357_1346102275843_86387_14350Collections();
                init_17_0_5_edc0357_1346102275843_86387_14350Elaborations();
            }

            public _17_0_5_edc0357_1346102275843_86387_14350(Expression<Integer> _17_0_5_edc0357_1346102113184_840698_14284_endTime) {
                super();
                init_17_0_5_edc0357_1346102275843_86387_14350Members();
                init_17_0_5_edc0357_1346102275843_86387_14350Collections();
                addDependency(this._17_0_5_edc0357_1346102113184_840698_14284_endTime, _17_0_5_edc0357_1346102113184_840698_14284_endTime);
                init_17_0_5_edc0357_1346102275843_86387_14350Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1346102390577_939825_14382 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1346106459827_751417_15369_endTime = null;

            public ConstraintExpression constraint620 = null;

            public void init_17_0_5_edc0357_1346102390577_939825_14382Members() {
                try {
                    _17_0_5_edc0357_1346106459827_751417_15369_endTime = new IntegerParameter("_17_0_5_edc0357_1346106459827_751417_15369_endTime", this);
                    constraint620 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346106459827_751417_15369_endTime)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346102390577_939825_14382Collections() {
                parameters.add(_17_0_5_edc0357_1346106459827_751417_15369_endTime);
                constraintExpressions.add(constraint620);
            }

            public void init_17_0_5_edc0357_1346102390577_939825_14382Elaborations() {
            }

            public _17_0_5_edc0357_1346102390577_939825_14382() {
                super();
                init_17_0_5_edc0357_1346102390577_939825_14382Members();
                init_17_0_5_edc0357_1346102390577_939825_14382Collections();
                init_17_0_5_edc0357_1346102390577_939825_14382Elaborations();
            }

            public _17_0_5_edc0357_1346102390577_939825_14382(Expression<Integer> _17_0_5_edc0357_1346106459827_751417_15369_endTime) {
                super();
                init_17_0_5_edc0357_1346102390577_939825_14382Members();
                init_17_0_5_edc0357_1346102390577_939825_14382Collections();
                addDependency(this._17_0_5_edc0357_1346106459827_751417_15369_endTime, _17_0_5_edc0357_1346106459827_751417_15369_endTime);
                init_17_0_5_edc0357_1346102390577_939825_14382Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1346103858168_44487_14734 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1348001473382_273655_14257_exists = null;

            public BooleanParameter _17_0_5_edc0357_1346104106512_507599_14867 = null;

            public IntegerParameter _17_0_5_edc0357_1346101664083_228321_14184_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1346101621154_926007_14131_exists = null;

            public ConstraintExpression constraint621 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1348001473382_273655_14257_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346104106512_507599_14867Dependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346101621154_926007_14131_existsDependency = null;

            public ElaborationRule elaborationRule622 = null;

            public ElaborationRule elaborationRule623 = null;

            public void init_17_0_5_edc0357_1346103858168_44487_14734Members() {
                try {
                    _17_0_5_edc0357_1348001473382_273655_14257_exists = new BooleanParameter("_17_0_5_edc0357_1348001473382_273655_14257_exists", this);
                    _17_0_5_edc0357_1346104106512_507599_14867 = new BooleanParameter("_17_0_5_edc0357_1346104106512_507599_14867", this);
                    _17_0_5_edc0357_1346101664083_228321_14184_endTime = new IntegerParameter("_17_0_5_edc0357_1346101664083_228321_14184_endTime", this);
                    _17_0_5_edc0357_1346101621154_926007_14131_exists = new BooleanParameter("_17_0_5_edc0357_1346101621154_926007_14131_exists", this);
                    constraint621 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346101664083_228321_14184_endTime)));
                    _17_0_5_edc0357_1348001473382_273655_14257_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1348001473382_273655_14257_exists, new Functions.Equals(new Expression<Boolean>(_17_0_5_edc0357_1346104106512_507599_14867), new Expression<Boolean>(false)));
                    _17_0_5_edc0357_1346104106512_507599_14867Dependency = new Dependency<Boolean>(_17_0_5_edc0357_1346104106512_507599_14867, new Expression(new FunctionCall(sig_17_0_5_edc0357_1346104106512_507599_14867, Utils.getMethodForArgTypes("ObjectFlow<Boolean>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1346101621154_926007_14131_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346101621154_926007_14131_exists, new Functions.Equals(new Expression<Boolean>(_17_0_5_edc0357_1346104106512_507599_14867), new Expression<Boolean>(true)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346103858168_44487_14734Collections() {
                parameters.add(_17_0_5_edc0357_1348001473382_273655_14257_exists);
                parameters.add(_17_0_5_edc0357_1346104106512_507599_14867);
                parameters.add(_17_0_5_edc0357_1346101664083_228321_14184_endTime);
                parameters.add(_17_0_5_edc0357_1346101621154_926007_14131_exists);
                constraintExpressions.add(constraint621);
                dependencies.add(_17_0_5_edc0357_1348001473382_273655_14257_existsDependency);
                dependencies.add(_17_0_5_edc0357_1346104106512_507599_14867Dependency);
                dependencies.add(_17_0_5_edc0357_1346101621154_926007_14131_existsDependency);
            }

            public void init_17_0_5_edc0357_1346103858168_44487_14734Elaborations() {
                Expression<?>[] arguments622 = new Expression<?>[1];
                arguments622[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition622 = new Expression<Boolean>(_17_0_5_edc0357_1346101621154_926007_14131_exists);
                elaborationRule622 = addElaborationRule(condition622, _17_0_5_edc0357_1346100467245_506694_13577.this, LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1346101621154_926007_14131.class, "sendDR_CallBehaviorAction_LADWP_CB", arguments622);
                Expression<?>[] arguments623 = new Expression<?>[1];
                arguments623[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition623 = new Expression<Boolean>(_17_0_5_edc0357_1348001473382_273655_14257_exists);
                elaborationRule623 = addElaborationRule(condition623, _17_0_5_edc0357_1346100467245_506694_13577.this, LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1348001473382_273655_14257.class, "_AcceptEventAction_LADWP_CB", arguments623);
            }

            public _17_0_5_edc0357_1346103858168_44487_14734() {
                super();
                init_17_0_5_edc0357_1346103858168_44487_14734Members();
                init_17_0_5_edc0357_1346103858168_44487_14734Collections();
                init_17_0_5_edc0357_1346103858168_44487_14734Elaborations();
            }

            public _17_0_5_edc0357_1346103858168_44487_14734(Expression<Integer> _17_0_5_edc0357_1346101664083_228321_14184_endTime) {
                super();
                init_17_0_5_edc0357_1346103858168_44487_14734Members();
                init_17_0_5_edc0357_1346103858168_44487_14734Collections();
                addDependency(this._17_0_5_edc0357_1346101664083_228321_14184_endTime, _17_0_5_edc0357_1346101664083_228321_14184_endTime);
                init_17_0_5_edc0357_1346103858168_44487_14734Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1346103876583_585391_14750 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1346101664083_228321_14184_exists = null;

            public BooleanParameter _17_0_5_edc0357_1346103985885_527964_14802_exists = null;

            public IntegerParameter _17_0_5_edc0357_1346101533989_302317_14094_endTime = null;

            public Parameter< LADWP > _17_0_5_edc0357_1346103876583_218379_14751 = null;

            public ConstraintExpression constraint624 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346101664083_228321_14184_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346103985885_527964_14802_existsDependency = null;

            public ElaborationRule elaborationRule625 = null;

            public ElaborationRule elaborationRule626 = null;

            public void init_17_0_5_edc0357_1346103876583_585391_14750Members() {
                try {
                    _17_0_5_edc0357_1346101664083_228321_14184_exists = new BooleanParameter("_17_0_5_edc0357_1346101664083_228321_14184_exists", this);
                    _17_0_5_edc0357_1346103985885_527964_14802_exists = new BooleanParameter("_17_0_5_edc0357_1346103985885_527964_14802_exists", this);
                    _17_0_5_edc0357_1346101533989_302317_14094_endTime = new IntegerParameter("_17_0_5_edc0357_1346101533989_302317_14094_endTime", this);
                    _17_0_5_edc0357_1346103876583_218379_14751 = new Parameter<LADWP>("_17_0_5_edc0357_1346103876583_218379_14751", null, LADWP.this, this);
                    constraint624 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346101533989_302317_14094_endTime)));
                    _17_0_5_edc0357_1346101664083_228321_14184_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346101664083_228321_14184_exists, new Expression(new FunctionCall(sig_17_0_5_edc0357_1346103964252_49821_14797, Utils.getMethodForArgTypes("ObjectFlow<Boolean>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })));
                    _17_0_5_edc0357_1346103985885_527964_14802_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346103985885_527964_14802_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346103876583_585391_14750Collections() {
                parameters.add(_17_0_5_edc0357_1346101664083_228321_14184_exists);
                parameters.add(_17_0_5_edc0357_1346103985885_527964_14802_exists);
                parameters.add(_17_0_5_edc0357_1346101533989_302317_14094_endTime);
                parameters.add(_17_0_5_edc0357_1346103876583_218379_14751);
                constraintExpressions.add(constraint624);
                dependencies.add(_17_0_5_edc0357_1346101664083_228321_14184_existsDependency);
                dependencies.add(_17_0_5_edc0357_1346103985885_527964_14802_existsDependency);
            }

            public void init_17_0_5_edc0357_1346103876583_585391_14750Elaborations() {
                Expression<?>[] arguments625 = new Expression<?>[1];
                arguments625[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition625 = new Expression<Boolean>(_17_0_5_edc0357_1346101664083_228321_14184_exists);
                elaborationRule625 = addElaborationRule(condition625, _17_0_5_edc0357_1346100467245_506694_13577.this, LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1346101664083_228321_14184.class, "_DecisionNode_LADWP_CB", arguments625);
                Expression<?>[] arguments626 = new Expression<?>[2];
                arguments626[0] = new Expression<Integer>(endTime);
                arguments626[1] = new Expression<LADWP>(_17_0_5_edc0357_1346103876583_218379_14751);
                Expression<Boolean> condition626 = new Expression<Boolean>(_17_0_5_edc0357_1346103985885_527964_14802_exists);
                elaborationRule626 = addElaborationRule(condition626, _17_0_5_edc0357_1346100467245_506694_13577.this, LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1346103985885_527964_14802.class, "_ForkNode_LADWP_CB", arguments626);
            }

            public _17_0_5_edc0357_1346103876583_585391_14750() {
                super();
                init_17_0_5_edc0357_1346103876583_585391_14750Members();
                init_17_0_5_edc0357_1346103876583_585391_14750Collections();
                init_17_0_5_edc0357_1346103876583_585391_14750Elaborations();
            }

            public _17_0_5_edc0357_1346103876583_585391_14750(Expression<Integer> _17_0_5_edc0357_1346101533989_302317_14094_endTime) {
                super();
                init_17_0_5_edc0357_1346103876583_585391_14750Members();
                init_17_0_5_edc0357_1346103876583_585391_14750Collections();
                addDependency(this._17_0_5_edc0357_1346101533989_302317_14094_endTime, _17_0_5_edc0357_1346101533989_302317_14094_endTime);
                init_17_0_5_edc0357_1346103876583_585391_14750Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1346103897886_203198_14770 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1346103927444_968068_14786 = null;

            public IntegerParameter _17_0_5_edc0357_1346103985885_527964_14802_endTime = null;

            public Parameter< LADWP > _17_0_5_edc0357_1346103914949_580826_14785 = null;

            public ConstraintExpression constraint627 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346103927444_968068_14786Dependency = null;

            public Effect effect628 = null;

            public Parameter effect628Var = null;

            public void init_17_0_5_edc0357_1346103897886_203198_14770Members() {
                try {
                    _17_0_5_edc0357_1346103927444_968068_14786 = new BooleanParameter("_17_0_5_edc0357_1346103927444_968068_14786", this);
                    _17_0_5_edc0357_1346103985885_527964_14802_endTime = new IntegerParameter("_17_0_5_edc0357_1346103985885_527964_14802_endTime", this);
                    _17_0_5_edc0357_1346103914949_580826_14785 = new Parameter<LADWP>("_17_0_5_edc0357_1346103914949_580826_14785", null, null, this);
                    constraint627 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346103985885_527964_14802_endTime)));
                    _17_0_5_edc0357_1346103927444_968068_14786Dependency = new Dependency<Boolean>(_17_0_5_edc0357_1346103927444_968068_14786, new Expression(new FunctionCall(null, Utils.getMethodForArgTypes("LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1346103897886_203198_14770", "generated", "getValue"), new Object[] { new Expression<Integer>(startTime) }, new FunctionCall(null, Parameter.class, "getValue", (Object[]) null, new FunctionCall(_17_0_5_edc0357_1346103914949_580826_14785, Parameter.class, "getMember", new Object[] { "shortage__17_0_5_edc0357_1345510113616_127059_13852" })))));
                    Object effect628VarV = sig_17_0_5_edc0357_1346103964252_49821_14797;
                    effect628Var = new Parameter("effect628Var", null, null, this);
                    addDependency(effect628Var, new Expression(effect628VarV));
                    effect628 = new EffectFunction(new FunctionCall(effect628Var, Utils.getMethodForArgTypes("ObjectFlow<Boolean>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { _17_0_5_edc0357_1346103927444_968068_14786, startTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346103897886_203198_14770Collections() {
                parameters.add(_17_0_5_edc0357_1346103927444_968068_14786);
                parameters.add(_17_0_5_edc0357_1346103985885_527964_14802_endTime);
                parameters.add(_17_0_5_edc0357_1346103914949_580826_14785);
                constraintExpressions.add(constraint627);
                dependencies.add(_17_0_5_edc0357_1346103927444_968068_14786Dependency);
                Set<Effect> effectsForeffect628Var = new HashSet<Effect>();
                effectsForeffect628Var.add(effect628);
                effects.put((Parameter<?>) effect628Var, effectsForeffect628Var);
            }

            public void init_17_0_5_edc0357_1346103897886_203198_14770Elaborations() {
            }

            public _17_0_5_edc0357_1346103897886_203198_14770() {
                super();
                init_17_0_5_edc0357_1346103897886_203198_14770Members();
                init_17_0_5_edc0357_1346103897886_203198_14770Collections();
                init_17_0_5_edc0357_1346103897886_203198_14770Elaborations();
            }

            public _17_0_5_edc0357_1346103897886_203198_14770(Expression<Integer> _17_0_5_edc0357_1346103985885_527964_14802_endTime, Expression<LADWP> _17_0_5_edc0357_1346103914949_580826_14785) {
                super();
                init_17_0_5_edc0357_1346103897886_203198_14770Members();
                init_17_0_5_edc0357_1346103897886_203198_14770Collections();
                addDependency(this._17_0_5_edc0357_1346103985885_527964_14802_endTime, _17_0_5_edc0357_1346103985885_527964_14802_endTime);
                addDependency(this._17_0_5_edc0357_1346103914949_580826_14785, _17_0_5_edc0357_1346103914949_580826_14785);
                init_17_0_5_edc0357_1346103897886_203198_14770Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1346103985885_527964_14802 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1346103876583_585391_14750_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1346103897886_203198_14770_exists = null;

            public BooleanParameter _17_0_5_edc0357_1346104054307_959096_14833_exists = null;

            public Parameter< LADWP > objectToPass = null;

            public ConstraintExpression constraint629 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346103897886_203198_14770_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346104054307_959096_14833_existsDependency = null;

            public ElaborationRule elaborationRule630 = null;

            public ElaborationRule elaborationRule631 = null;

            public void init_17_0_5_edc0357_1346103985885_527964_14802Members() {
                try {
                    _17_0_5_edc0357_1346103876583_585391_14750_endTime = new IntegerParameter("_17_0_5_edc0357_1346103876583_585391_14750_endTime", this);
                    _17_0_5_edc0357_1346103897886_203198_14770_exists = new BooleanParameter("_17_0_5_edc0357_1346103897886_203198_14770_exists", this);
                    _17_0_5_edc0357_1346104054307_959096_14833_exists = new BooleanParameter("_17_0_5_edc0357_1346104054307_959096_14833_exists", this);
                    objectToPass = new Parameter<LADWP>("objectToPass", null, null, this);
                    constraint629 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346103876583_585391_14750_endTime)));
                    _17_0_5_edc0357_1346103897886_203198_14770_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346103897886_203198_14770_exists, new Expression<Boolean>(true));
                    _17_0_5_edc0357_1346104054307_959096_14833_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346104054307_959096_14833_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346103985885_527964_14802Collections() {
                parameters.add(_17_0_5_edc0357_1346103876583_585391_14750_endTime);
                parameters.add(_17_0_5_edc0357_1346103897886_203198_14770_exists);
                parameters.add(_17_0_5_edc0357_1346104054307_959096_14833_exists);
                parameters.add(objectToPass);
                constraintExpressions.add(constraint629);
                dependencies.add(_17_0_5_edc0357_1346103897886_203198_14770_existsDependency);
                dependencies.add(_17_0_5_edc0357_1346104054307_959096_14833_existsDependency);
            }

            public void init_17_0_5_edc0357_1346103985885_527964_14802Elaborations() {
                Expression<?>[] arguments630 = new Expression<?>[2];
                arguments630[0] = new Expression<Integer>(endTime);
                arguments630[1] = new Expression<LADWP>(objectToPass);
                Expression<Boolean> condition630 = new Expression<Boolean>(_17_0_5_edc0357_1346104054307_959096_14833_exists);
                elaborationRule630 = addElaborationRule(condition630, _17_0_5_edc0357_1346100467245_506694_13577.this, LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1346104054307_959096_14833.class, "readDemandResponse_ReadStructuralFeatureAction_LADWP_CB", arguments630);
                Expression<?>[] arguments631 = new Expression<?>[2];
                arguments631[0] = new Expression<Integer>(endTime);
                arguments631[1] = new Expression<LADWP>(objectToPass);
                Expression<Boolean> condition631 = new Expression<Boolean>(_17_0_5_edc0357_1346103897886_203198_14770_exists);
                elaborationRule631 = addElaborationRule(condition631, _17_0_5_edc0357_1346100467245_506694_13577.this, LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1346103897886_203198_14770.class, "readShortage_ReadStructuralFeatureAction_LADWP_CB", arguments631);
            }

            public _17_0_5_edc0357_1346103985885_527964_14802() {
                super();
                init_17_0_5_edc0357_1346103985885_527964_14802Members();
                init_17_0_5_edc0357_1346103985885_527964_14802Collections();
                init_17_0_5_edc0357_1346103985885_527964_14802Elaborations();
            }

            public _17_0_5_edc0357_1346103985885_527964_14802(Expression<Integer> _17_0_5_edc0357_1346103876583_585391_14750_endTime, Expression<LADWP> objectToPass) {
                super();
                init_17_0_5_edc0357_1346103985885_527964_14802Members();
                init_17_0_5_edc0357_1346103985885_527964_14802Collections();
                addDependency(this._17_0_5_edc0357_1346103876583_585391_14750_endTime, _17_0_5_edc0357_1346103876583_585391_14750_endTime);
                addDependency(this.objectToPass, objectToPass);
                init_17_0_5_edc0357_1346103985885_527964_14802Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1346104054307_959096_14833 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1346103985885_527964_14802_endTime = null;

            public Parameter< LADWP > _17_0_5_edc0357_1346104058977_172678_14847 = null;

            public BooleanParameter _17_0_5_edc0357_1346104069409_904806_14848 = null;

            public ConstraintExpression constraint632 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346104069409_904806_14848Dependency = null;

            public Effect effect633 = null;

            public Parameter effect633Var = null;

            public void init_17_0_5_edc0357_1346104054307_959096_14833Members() {
                try {
                    _17_0_5_edc0357_1346103985885_527964_14802_endTime = new IntegerParameter("_17_0_5_edc0357_1346103985885_527964_14802_endTime", this);
                    _17_0_5_edc0357_1346104058977_172678_14847 = new Parameter<LADWP>("_17_0_5_edc0357_1346104058977_172678_14847", null, null, this);
                    _17_0_5_edc0357_1346104069409_904806_14848 = new BooleanParameter("_17_0_5_edc0357_1346104069409_904806_14848", this);
                    constraint632 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346103985885_527964_14802_endTime)));
                    _17_0_5_edc0357_1346104069409_904806_14848Dependency = new Dependency<Boolean>(_17_0_5_edc0357_1346104069409_904806_14848, new Expression(new FunctionCall(null, Utils.getMethodForArgTypes("LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1346104054307_959096_14833", "generated", "getValue"), new Object[] { new Expression<Integer>(startTime) }, new FunctionCall(null, Parameter.class, "getValue", (Object[]) null, new FunctionCall(_17_0_5_edc0357_1346104058977_172678_14847, Parameter.class, "getMember", new Object[] { "demandResponse__17_0_5_edc0357_1345510113615_857599_13851" })))));
                    Object effect633VarV = sig_17_0_5_edc0357_1346104106512_507599_14867;
                    effect633Var = new Parameter("effect633Var", null, null, this);
                    addDependency(effect633Var, new Expression(effect633VarV));
                    effect633 = new EffectFunction(new FunctionCall(effect633Var, Utils.getMethodForArgTypes("ObjectFlow<Boolean>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { _17_0_5_edc0357_1346104069409_904806_14848, startTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346104054307_959096_14833Collections() {
                parameters.add(_17_0_5_edc0357_1346103985885_527964_14802_endTime);
                parameters.add(_17_0_5_edc0357_1346104058977_172678_14847);
                parameters.add(_17_0_5_edc0357_1346104069409_904806_14848);
                constraintExpressions.add(constraint632);
                dependencies.add(_17_0_5_edc0357_1346104069409_904806_14848Dependency);
                Set<Effect> effectsForeffect633Var = new HashSet<Effect>();
                effectsForeffect633Var.add(effect633);
                effects.put((Parameter<?>) effect633Var, effectsForeffect633Var);
            }

            public void init_17_0_5_edc0357_1346104054307_959096_14833Elaborations() {
            }

            public _17_0_5_edc0357_1346104054307_959096_14833() {
                super();
                init_17_0_5_edc0357_1346104054307_959096_14833Members();
                init_17_0_5_edc0357_1346104054307_959096_14833Collections();
                init_17_0_5_edc0357_1346104054307_959096_14833Elaborations();
            }

            public _17_0_5_edc0357_1346104054307_959096_14833(Expression<Integer> _17_0_5_edc0357_1346103985885_527964_14802_endTime, Expression<LADWP> _17_0_5_edc0357_1346104058977_172678_14847) {
                super();
                init_17_0_5_edc0357_1346104054307_959096_14833Members();
                init_17_0_5_edc0357_1346104054307_959096_14833Collections();
                addDependency(this._17_0_5_edc0357_1346103985885_527964_14802_endTime, _17_0_5_edc0357_1346103985885_527964_14802_endTime);
                addDependency(this._17_0_5_edc0357_1346104058977_172678_14847, _17_0_5_edc0357_1346104058977_172678_14847);
                init_17_0_5_edc0357_1346104054307_959096_14833Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1346104609060_805097_14926 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1348163055082_50872_14559_endTime = null;

            public ConstraintExpression constraint634 = null;

            public void init_17_0_5_edc0357_1346104609060_805097_14926Members() {
                try {
                    _17_0_5_edc0357_1348163055082_50872_14559_endTime = new IntegerParameter("_17_0_5_edc0357_1348163055082_50872_14559_endTime", this);
                    constraint634 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1348163055082_50872_14559_endTime)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346104609060_805097_14926Collections() {
                parameters.add(_17_0_5_edc0357_1348163055082_50872_14559_endTime);
                constraintExpressions.add(constraint634);
            }

            public void init_17_0_5_edc0357_1346104609060_805097_14926Elaborations() {
            }

            public _17_0_5_edc0357_1346104609060_805097_14926() {
                super();
                init_17_0_5_edc0357_1346104609060_805097_14926Members();
                init_17_0_5_edc0357_1346104609060_805097_14926Collections();
                init_17_0_5_edc0357_1346104609060_805097_14926Elaborations();
            }

            public _17_0_5_edc0357_1346104609060_805097_14926(Expression<Integer> _17_0_5_edc0357_1348163055082_50872_14559_endTime) {
                super();
                init_17_0_5_edc0357_1346104609060_805097_14926Members();
                init_17_0_5_edc0357_1346104609060_805097_14926Collections();
                addDependency(this._17_0_5_edc0357_1348163055082_50872_14559_endTime, _17_0_5_edc0357_1348163055082_50872_14559_endTime);
                init_17_0_5_edc0357_1346104609060_805097_14926Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1346106459827_751417_15369 extends DurativeEvent {

            public IntegerParameter invoker_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1346102390577_939825_14382_exists = null;

            public ConstraintExpression constraint635 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346102390577_939825_14382_existsDependency = null;

            public ElaborationRule elaborationRule636 = null;

            public void init_17_0_5_edc0357_1346106459827_751417_15369Members() {
                try {
                    invoker_endTime = new IntegerParameter("invoker_endTime", this);
                    _17_0_5_edc0357_1346102390577_939825_14382_exists = new BooleanParameter("_17_0_5_edc0357_1346102390577_939825_14382_exists", this);
                    constraint635 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(invoker_endTime)));
                    _17_0_5_edc0357_1346102390577_939825_14382_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346102390577_939825_14382_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346106459827_751417_15369Collections() {
                parameters.add(invoker_endTime);
                parameters.add(_17_0_5_edc0357_1346102390577_939825_14382_exists);
                constraintExpressions.add(constraint635);
                dependencies.add(_17_0_5_edc0357_1346102390577_939825_14382_existsDependency);
            }

            public void init_17_0_5_edc0357_1346106459827_751417_15369Elaborations() {
                Expression<?>[] arguments636 = new Expression<?>[1];
                arguments636[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition636 = new Expression<Boolean>(_17_0_5_edc0357_1346102390577_939825_14382_exists);
                elaborationRule636 = addElaborationRule(condition636, _17_0_5_edc0357_1346100467245_506694_13577.this, LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1346102390577_939825_14382.class, "_FlowFinalNode_LADWP_CB", arguments636);
            }

            public _17_0_5_edc0357_1346106459827_751417_15369() {
                super();
                init_17_0_5_edc0357_1346106459827_751417_15369Members();
                init_17_0_5_edc0357_1346106459827_751417_15369Collections();
                init_17_0_5_edc0357_1346106459827_751417_15369Elaborations();
            }

            public _17_0_5_edc0357_1346106459827_751417_15369(Expression<Integer> invoker_endTime) {
                super();
                init_17_0_5_edc0357_1346106459827_751417_15369Members();
                init_17_0_5_edc0357_1346106459827_751417_15369Collections();
                addDependency(this.invoker_endTime, invoker_endTime);
                init_17_0_5_edc0357_1346106459827_751417_15369Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1348001296069_146013_14222 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1346101765241_743667_14225_exists = null;

            public IntegerParameter _17_0_5_edc0357_1346100685814_732050_13654_endTime = null;

            public ConstraintExpression constraint637 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346101765241_743667_14225_existsDependency = null;

            public Dependency< Integer > durationDependency = null;

            public ElaborationRule elaborationRule638 = null;

            public void init_17_0_5_edc0357_1348001296069_146013_14222Members() {
                try {
                    _17_0_5_edc0357_1346101765241_743667_14225_exists = new BooleanParameter("_17_0_5_edc0357_1346101765241_743667_14225_exists", this);
                    _17_0_5_edc0357_1346100685814_732050_13654_endTime = new IntegerParameter("_17_0_5_edc0357_1346100685814_732050_13654_endTime", this);
                    constraint637 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346100685814_732050_13654_endTime)));
                    _17_0_5_edc0357_1346101765241_743667_14225_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346101765241_743667_14225_exists, new Expression<Boolean>(true));
                    durationDependency = new Dependency<Integer>(duration, new Expression<Integer>(30));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1348001296069_146013_14222Collections() {
                parameters.add(_17_0_5_edc0357_1346101765241_743667_14225_exists);
                parameters.add(_17_0_5_edc0357_1346100685814_732050_13654_endTime);
                constraintExpressions.add(constraint637);
                removeDependency(duration);
                dependencies.add(_17_0_5_edc0357_1346101765241_743667_14225_existsDependency);
                dependencies.add(durationDependency);
            }

            public void init_17_0_5_edc0357_1348001296069_146013_14222Elaborations() {
                Expression<?>[] arguments638 = new Expression<?>[1];
                arguments638[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition638 = new Expression<Boolean>(_17_0_5_edc0357_1346101765241_743667_14225_exists);
                elaborationRule638 = addElaborationRule(condition638, _17_0_5_edc0357_1346100467245_506694_13577.this, LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1346101765241_743667_14225.class, "_MergeNode_LADWP_CB", arguments638);
            }

            public _17_0_5_edc0357_1348001296069_146013_14222() {
                super();
                init_17_0_5_edc0357_1348001296069_146013_14222Members();
                init_17_0_5_edc0357_1348001296069_146013_14222Collections();
                init_17_0_5_edc0357_1348001296069_146013_14222Elaborations();
            }

            public _17_0_5_edc0357_1348001296069_146013_14222(Expression<Integer> _17_0_5_edc0357_1346100685814_732050_13654_endTime) {
                super();
                init_17_0_5_edc0357_1348001296069_146013_14222Members();
                init_17_0_5_edc0357_1348001296069_146013_14222Collections();
                addDependency(this._17_0_5_edc0357_1346100685814_732050_13654_endTime, _17_0_5_edc0357_1346100685814_732050_13654_endTime);
                init_17_0_5_edc0357_1348001296069_146013_14222Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1348001473382_273655_14257 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1346103858168_44487_14734_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1346101765241_743667_14225_exists = null;

            public ConstraintExpression constraint639 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346101765241_743667_14225_existsDependency = null;

            public Dependency< Integer > durationDependency = null;

            public ElaborationRule elaborationRule640 = null;

            public void init_17_0_5_edc0357_1348001473382_273655_14257Members() {
                try {
                    _17_0_5_edc0357_1346103858168_44487_14734_endTime = new IntegerParameter("_17_0_5_edc0357_1346103858168_44487_14734_endTime", this);
                    _17_0_5_edc0357_1346101765241_743667_14225_exists = new BooleanParameter("_17_0_5_edc0357_1346101765241_743667_14225_exists", this);
                    constraint639 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346103858168_44487_14734_endTime)));
                    _17_0_5_edc0357_1346101765241_743667_14225_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346101765241_743667_14225_exists, new Expression<Boolean>(true));
                    durationDependency = new Dependency<Integer>(duration, new Expression<Integer>(45));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1348001473382_273655_14257Collections() {
                parameters.add(_17_0_5_edc0357_1346103858168_44487_14734_endTime);
                parameters.add(_17_0_5_edc0357_1346101765241_743667_14225_exists);
                constraintExpressions.add(constraint639);
                removeDependency(duration);
                dependencies.add(_17_0_5_edc0357_1346101765241_743667_14225_existsDependency);
                dependencies.add(durationDependency);
            }

            public void init_17_0_5_edc0357_1348001473382_273655_14257Elaborations() {
                Expression<?>[] arguments640 = new Expression<?>[1];
                arguments640[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition640 = new Expression<Boolean>(_17_0_5_edc0357_1346101765241_743667_14225_exists);
                elaborationRule640 = addElaborationRule(condition640, _17_0_5_edc0357_1346100467245_506694_13577.this, LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1346101765241_743667_14225.class, "_MergeNode_LADWP_CB", arguments640);
            }

            public _17_0_5_edc0357_1348001473382_273655_14257() {
                super();
                init_17_0_5_edc0357_1348001473382_273655_14257Members();
                init_17_0_5_edc0357_1348001473382_273655_14257Collections();
                init_17_0_5_edc0357_1348001473382_273655_14257Elaborations();
            }

            public _17_0_5_edc0357_1348001473382_273655_14257(Expression<Integer> _17_0_5_edc0357_1346103858168_44487_14734_endTime) {
                super();
                init_17_0_5_edc0357_1348001473382_273655_14257Members();
                init_17_0_5_edc0357_1348001473382_273655_14257Collections();
                addDependency(this._17_0_5_edc0357_1346103858168_44487_14734_endTime, _17_0_5_edc0357_1346103858168_44487_14734_endTime);
                init_17_0_5_edc0357_1348001473382_273655_14257Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1348001608547_58915_14306 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1346102113184_840698_14284_endTime = null;

            public IntegerParameter objectToPass = null;

            public ConstraintExpression constraint641 = null;

            public Effect effect642 = null;

            public Parameter effect642Var = null;

            public Effect effect643 = null;

            public Parameter effect643Var = null;

            public void init_17_0_5_edc0357_1348001608547_58915_14306Members() {
                try {
                    _17_0_5_edc0357_1346102113184_840698_14284_endTime = new IntegerParameter("_17_0_5_edc0357_1346102113184_840698_14284_endTime", this);
                    objectToPass = new IntegerParameter("objectToPass", this);
                    constraint641 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346102113184_840698_14284_endTime)));
                    Object effect642VarV = sig_17_0_5_edc0357_1348001609357_382848_14312;
                    effect642Var = new Parameter("effect642Var", null, null, this);
                    addDependency(effect642Var, new Expression(effect642VarV));
                    effect642 = new EffectFunction(new FunctionCall(effect642Var, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                    Object effect643VarV = sig_17_0_5_edc0357_1348001642582_861167_14319;
                    effect643Var = new Parameter("effect643Var", null, null, this);
                    addDependency(effect643Var, new Expression(effect643VarV));
                    effect643 = new EffectFunction(new FunctionCall(effect643Var, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1348001608547_58915_14306Collections() {
                parameters.add(_17_0_5_edc0357_1346102113184_840698_14284_endTime);
                parameters.add(objectToPass);
                constraintExpressions.add(constraint641);
                Set<Effect> effectsForeffect642Var = new HashSet<Effect>();
                effectsForeffect642Var.add(effect642);
                effects.put((Parameter<?>) effect642Var, effectsForeffect642Var);
                Set<Effect> effectsForeffect643Var = new HashSet<Effect>();
                effectsForeffect643Var.add(effect643);
                effects.put((Parameter<?>) effect643Var, effectsForeffect643Var);
            }

            public void init_17_0_5_edc0357_1348001608547_58915_14306Elaborations() {
            }

            public _17_0_5_edc0357_1348001608547_58915_14306() {
                super();
                init_17_0_5_edc0357_1348001608547_58915_14306Members();
                init_17_0_5_edc0357_1348001608547_58915_14306Collections();
                init_17_0_5_edc0357_1348001608547_58915_14306Elaborations();
            }

            public _17_0_5_edc0357_1348001608547_58915_14306(Expression<Integer> _17_0_5_edc0357_1346102113184_840698_14284_endTime, Expression<Integer> objectToPass) {
                super();
                init_17_0_5_edc0357_1348001608547_58915_14306Members();
                init_17_0_5_edc0357_1348001608547_58915_14306Collections();
                addDependency(this._17_0_5_edc0357_1346102113184_840698_14284_endTime, _17_0_5_edc0357_1346102113184_840698_14284_endTime);
                addDependency(this.objectToPass, objectToPass);
                init_17_0_5_edc0357_1348001608547_58915_14306Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1348008482216_500972_14383 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1346100496379_482519_13614_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1346100685814_732050_13654_exists = null;

            public ConstraintExpression constraint644 = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346100685814_732050_13654_existsDependency = null;

            public ElaborationRule elaborationRule645 = null;

            public void init_17_0_5_edc0357_1348008482216_500972_14383Members() {
                try {
                    _17_0_5_edc0357_1346100496379_482519_13614_endTime = new IntegerParameter("_17_0_5_edc0357_1346100496379_482519_13614_endTime", this);
                    _17_0_5_edc0357_1346100685814_732050_13654_exists = new BooleanParameter("_17_0_5_edc0357_1346100685814_732050_13654_exists", this);
                    constraint644 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346100496379_482519_13614_endTime)));
                    durationDependency = new Dependency<Integer>(duration, new Expression<Integer>(20));
                    _17_0_5_edc0357_1346100685814_732050_13654_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346100685814_732050_13654_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1348008482216_500972_14383Collections() {
                parameters.add(_17_0_5_edc0357_1346100496379_482519_13614_endTime);
                parameters.add(_17_0_5_edc0357_1346100685814_732050_13654_exists);
                constraintExpressions.add(constraint644);
                removeDependency(duration);
                dependencies.add(durationDependency);
                dependencies.add(_17_0_5_edc0357_1346100685814_732050_13654_existsDependency);
            }

            public void init_17_0_5_edc0357_1348008482216_500972_14383Elaborations() {
                Expression<?>[] arguments645 = new Expression<?>[1];
                arguments645[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition645 = new Expression<Boolean>(_17_0_5_edc0357_1346100685814_732050_13654_exists);
                elaborationRule645 = addElaborationRule(condition645, _17_0_5_edc0357_1346100467245_506694_13577.this, LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1346100685814_732050_13654.class, "_ForkNode_LADWP_CB", arguments645);
            }

            public _17_0_5_edc0357_1348008482216_500972_14383() {
                super();
                init_17_0_5_edc0357_1348008482216_500972_14383Members();
                init_17_0_5_edc0357_1348008482216_500972_14383Collections();
                init_17_0_5_edc0357_1348008482216_500972_14383Elaborations();
            }

            public _17_0_5_edc0357_1348008482216_500972_14383(Expression<Integer> _17_0_5_edc0357_1346100496379_482519_13614_endTime) {
                super();
                init_17_0_5_edc0357_1348008482216_500972_14383Members();
                init_17_0_5_edc0357_1348008482216_500972_14383Collections();
                addDependency(this._17_0_5_edc0357_1346100496379_482519_13614_endTime, _17_0_5_edc0357_1346100496379_482519_13614_endTime);
                init_17_0_5_edc0357_1348008482216_500972_14383Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1348163055082_50872_14559 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1346104609060_805097_14926_exists = null;

            public IntegerParameter _17_0_5_edc0357_1346100685814_732050_13654_endTime = null;

            public ConstraintExpression constraint646 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346104609060_805097_14926_existsDependency = null;

            public Dependency< Integer > durationDependency = null;

            public ElaborationRule elaborationRule647 = null;

            public void init_17_0_5_edc0357_1348163055082_50872_14559Members() {
                try {
                    _17_0_5_edc0357_1346104609060_805097_14926_exists = new BooleanParameter("_17_0_5_edc0357_1346104609060_805097_14926_exists", this);
                    _17_0_5_edc0357_1346100685814_732050_13654_endTime = new IntegerParameter("_17_0_5_edc0357_1346100685814_732050_13654_endTime", this);
                    constraint646 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346100685814_732050_13654_endTime)));
                    _17_0_5_edc0357_1346104609060_805097_14926_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346104609060_805097_14926_exists, new Expression<Boolean>(true));
                    durationDependency = new Dependency<Integer>(duration, new Expression<Integer>(10));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1348163055082_50872_14559Collections() {
                parameters.add(_17_0_5_edc0357_1346104609060_805097_14926_exists);
                parameters.add(_17_0_5_edc0357_1346100685814_732050_13654_endTime);
                constraintExpressions.add(constraint646);
                removeDependency(duration);
                dependencies.add(_17_0_5_edc0357_1346104609060_805097_14926_existsDependency);
                dependencies.add(durationDependency);
            }

            public void init_17_0_5_edc0357_1348163055082_50872_14559Elaborations() {
                Expression<?>[] arguments647 = new Expression<?>[1];
                arguments647[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition647 = new Expression<Boolean>(_17_0_5_edc0357_1346104609060_805097_14926_exists);
                elaborationRule647 = addElaborationRule(condition647, _17_0_5_edc0357_1346100467245_506694_13577.this, LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1346104609060_805097_14926.class, "_ActivityFinalNode_LADWP_CB", arguments647);
            }

            public _17_0_5_edc0357_1348163055082_50872_14559() {
                super();
                init_17_0_5_edc0357_1348163055082_50872_14559Members();
                init_17_0_5_edc0357_1348163055082_50872_14559Collections();
                init_17_0_5_edc0357_1348163055082_50872_14559Elaborations();
            }

            public _17_0_5_edc0357_1348163055082_50872_14559(Expression<Integer> _17_0_5_edc0357_1346100685814_732050_13654_endTime) {
                super();
                init_17_0_5_edc0357_1348163055082_50872_14559Members();
                init_17_0_5_edc0357_1348163055082_50872_14559Collections();
                addDependency(this._17_0_5_edc0357_1346100685814_732050_13654_endTime, _17_0_5_edc0357_1346100685814_732050_13654_endTime);
                init_17_0_5_edc0357_1348163055082_50872_14559Elaborations();
                fixTimeDependencies();
            }
        }

        public _17_0_5_edc0357_1346100467245_506694_13577(Expression<Integer> invoke_time) {
            super();
            init_17_0_5_edc0357_1346100467245_506694_13577Members();
            init_17_0_5_edc0357_1346100467245_506694_13577Collections();
            addDependency(this.invoke_time, invoke_time);
            init_17_0_5_edc0357_1346100467245_506694_13577Elaborations();
            fixTimeDependencies();
        }
    }

    public class _17_0_5_edc0357_1346102095091_571012_14257 extends DurativeEvent {

        public Parameter< ObjectFlow<Integer> > sig_17_0_5_edc0357_1346102889543_283441_14507 = null;

        public IntegerParameter _17_0_5_edc0357_1346102889543_283441_14507 = null;

        public IntegerParameter invoke_time = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_5_edc0357_1346103552856_270449_14698 = null;

        public Parameter< ObjectFlow<Power_System.Signalno> > sig_17_0_5_edc0357_1345510113325_236143_13622 = null;

        public Parameter< ObjectFlow<Power_System.Signalyes> > sig_17_0_5_edc0357_1345510113324_308013_13621 = null;

        public ElaborationRule elaborationRule648 = null;

        public void init_17_0_5_edc0357_1346102095091_571012_14257Members() {
            try {
                sig_17_0_5_edc0357_1346102889543_283441_14507 = new Parameter<ObjectFlow<Integer>>("sig_17_0_5_edc0357_1346102889543_283441_14507", null, null, this);
                _17_0_5_edc0357_1346102889543_283441_14507 = new IntegerParameter("_17_0_5_edc0357_1346102889543_283441_14507", this);
                invoke_time = new IntegerParameter("invoke_time", this);
                sig_17_0_5_edc0357_1346103552856_270449_14698 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_5_edc0357_1346103552856_270449_14698", null, new ObjectFlow("sig_17_0_5_edc0357_1346103552856_270449_14698"), this);
                sig_17_0_5_edc0357_1345510113325_236143_13622 = new Parameter<ObjectFlow<Power_System.Signalno>>("sig_17_0_5_edc0357_1345510113325_236143_13622", null, new ObjectFlow("sig_17_0_5_edc0357_1345510113325_236143_13622"), this);
                sig_17_0_5_edc0357_1345510113324_308013_13621 = new Parameter<ObjectFlow<Power_System.Signalyes>>("sig_17_0_5_edc0357_1345510113324_308013_13621", null, new ObjectFlow("sig_17_0_5_edc0357_1345510113324_308013_13621"), this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void init_17_0_5_edc0357_1346102095091_571012_14257Collections() {
            parameters.add(sig_17_0_5_edc0357_1346102889543_283441_14507);
            parameters.add(_17_0_5_edc0357_1346102889543_283441_14507);
            parameters.add(invoke_time);
            parameters.add(sig_17_0_5_edc0357_1346103552856_270449_14698);
            parameters.add(sig_17_0_5_edc0357_1345510113325_236143_13622);
            parameters.add(sig_17_0_5_edc0357_1345510113324_308013_13621);
        }

        public void init_17_0_5_edc0357_1346102095091_571012_14257Elaborations() {
            Expression<?>[] arguments648 = new Expression<?>[1];
            arguments648[0] = new Expression<Integer>(invoke_time);
            Expression<Boolean> condition648 = new Expression<Boolean>(true);
            elaborationRule648 = addElaborationRule(condition648, _17_0_5_edc0357_1346102095091_571012_14257.this, LADWP._17_0_5_edc0357_1346102095091_571012_14257._17_0_5_edc0357_1346102438886_844068_14398.class, "_InitialNode_waitForDRResponse", arguments648);
        }

        public _17_0_5_edc0357_1346102095091_571012_14257() {
            super();
            init_17_0_5_edc0357_1346102095091_571012_14257Members();
            init_17_0_5_edc0357_1346102095091_571012_14257Collections();
            init_17_0_5_edc0357_1346102095091_571012_14257Elaborations();
        }

        public class _17_0_5_edc0357_1346102438886_844068_14398 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1346102573506_761015_14458_exists = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346102573506_761015_14458_existsDependency = null;

            public ElaborationRule elaborationRule649 = null;

            public void init_17_0_5_edc0357_1346102438886_844068_14398Members() {
                try {
                    _17_0_5_edc0357_1346102573506_761015_14458_exists = new BooleanParameter("_17_0_5_edc0357_1346102573506_761015_14458_exists", this);
                    _17_0_5_edc0357_1346102573506_761015_14458_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346102573506_761015_14458_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346102438886_844068_14398Collections() {
                parameters.add(_17_0_5_edc0357_1346102573506_761015_14458_exists);
                dependencies.add(_17_0_5_edc0357_1346102573506_761015_14458_existsDependency);
            }

            public void init_17_0_5_edc0357_1346102438886_844068_14398Elaborations() {
                Expression<?>[] arguments649 = new Expression<?>[1];
                arguments649[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition649 = new Expression<Boolean>(_17_0_5_edc0357_1346102573506_761015_14458_exists);
                elaborationRule649 = addElaborationRule(condition649, _17_0_5_edc0357_1346102095091_571012_14257.this, LADWP._17_0_5_edc0357_1346102095091_571012_14257._17_0_5_edc0357_1346102573506_761015_14458.class, "_ForkNode_waitForDRResponse", arguments649);
            }

            public _17_0_5_edc0357_1346102438886_844068_14398() {
                super();
                init_17_0_5_edc0357_1346102438886_844068_14398Members();
                init_17_0_5_edc0357_1346102438886_844068_14398Collections();
                init_17_0_5_edc0357_1346102438886_844068_14398Elaborations();
            }

            public _17_0_5_edc0357_1346102438886_844068_14398(Expression<Integer> startTime) {
                super();
                init_17_0_5_edc0357_1346102438886_844068_14398Members();
                init_17_0_5_edc0357_1346102438886_844068_14398Collections();
                removeDependency(this.startTime);
                addDependency(this.startTime, startTime);
                init_17_0_5_edc0357_1346102438886_844068_14398Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1346102441989_709933_14404 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1346103062558_947732_14567_exists = null;

            public Parameter< Power_System.Signalyes > _17_0_5_edc0357_1346102459862_624077_14418 = null;

            public IntegerParameter _17_0_5_edc0357_1346102573506_761015_14458_endTime = null;

            public ConstraintExpression constraint650 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346103062558_947732_14567_existsDependency = null;

            public Dependency< Power_System.Signalyes > _17_0_5_edc0357_1346102459862_624077_14418Dependency = null;

            public ElaborationRule elaborationRule651 = null;

            public void init_17_0_5_edc0357_1346102441989_709933_14404Members() {
                try {
                    _17_0_5_edc0357_1346103062558_947732_14567_exists = new BooleanParameter("_17_0_5_edc0357_1346103062558_947732_14567_exists", this);
                    _17_0_5_edc0357_1346102459862_624077_14418 = new Parameter<Power_System.Signalyes>("_17_0_5_edc0357_1346102459862_624077_14418", null, null, this);
                    _17_0_5_edc0357_1346102573506_761015_14458_endTime = new IntegerParameter("_17_0_5_edc0357_1346102573506_761015_14458_endTime", this);
                    constraint650 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346102573506_761015_14458_endTime)));
                    _17_0_5_edc0357_1346103062558_947732_14567_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346103062558_947732_14567_exists, new Expression<Boolean>(true));
                    _17_0_5_edc0357_1346102459862_624077_14418Dependency = new Dependency<Power_System.Signalyes>(_17_0_5_edc0357_1346102459862_624077_14418, new Expression(new FunctionCall(q_LADWP_yes, Utils.getMethodForArgTypes("ObjectFlow<Signalyes>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346102441989_709933_14404Collections() {
                parameters.add(_17_0_5_edc0357_1346103062558_947732_14567_exists);
                parameters.add(_17_0_5_edc0357_1346102459862_624077_14418);
                parameters.add(_17_0_5_edc0357_1346102573506_761015_14458_endTime);
                constraintExpressions.add(constraint650);
                dependencies.add(_17_0_5_edc0357_1346103062558_947732_14567_existsDependency);
                dependencies.add(_17_0_5_edc0357_1346102459862_624077_14418Dependency);
            }

            public void init_17_0_5_edc0357_1346102441989_709933_14404Elaborations() {
                Expression<?>[] arguments651 = new Expression<?>[2];
                arguments651[0] = new Expression<Integer>(endTime);
                arguments651[1] = new Expression<Power_System.Signalyes>(_17_0_5_edc0357_1346102459862_624077_14418);
                Expression<Boolean> condition651 = new Expression<Boolean>(_17_0_5_edc0357_1346103062558_947732_14567_exists);
                elaborationRule651 = addElaborationRule(condition651, _17_0_5_edc0357_1346102095091_571012_14257.this, LADWP._17_0_5_edc0357_1346102095091_571012_14257._17_0_5_edc0357_1346103062558_947732_14567.class, "_ReadStructuralFeatureAction_waitForDRResponse", arguments651);
            }

            public _17_0_5_edc0357_1346102441989_709933_14404() {
                super();
                init_17_0_5_edc0357_1346102441989_709933_14404Members();
                init_17_0_5_edc0357_1346102441989_709933_14404Collections();
                init_17_0_5_edc0357_1346102441989_709933_14404Elaborations();
            }

            public _17_0_5_edc0357_1346102441989_709933_14404(Expression<Integer> _17_0_5_edc0357_1346102573506_761015_14458_endTime) {
                super();
                init_17_0_5_edc0357_1346102441989_709933_14404Members();
                init_17_0_5_edc0357_1346102441989_709933_14404Collections();
                addDependency(this._17_0_5_edc0357_1346102573506_761015_14458_endTime, _17_0_5_edc0357_1346102573506_761015_14458_endTime);
                init_17_0_5_edc0357_1346102441989_709933_14404Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1346102519738_328444_14424 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1346102573506_761015_14458_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1346103010487_691704_14535_exists = null;

            public ConstraintExpression constraint652 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346103010487_691704_14535_existsDependency = null;

            public ElaborationRule elaborationRule653 = null;

            public void init_17_0_5_edc0357_1346102519738_328444_14424Members() {
                try {
                    _17_0_5_edc0357_1346102573506_761015_14458_endTime = new IntegerParameter("_17_0_5_edc0357_1346102573506_761015_14458_endTime", this);
                    _17_0_5_edc0357_1346103010487_691704_14535_exists = new BooleanParameter("_17_0_5_edc0357_1346103010487_691704_14535_exists", this);
                    constraint652 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346102573506_761015_14458_endTime)));
                    _17_0_5_edc0357_1346103010487_691704_14535_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346103010487_691704_14535_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346102519738_328444_14424Collections() {
                parameters.add(_17_0_5_edc0357_1346102573506_761015_14458_endTime);
                parameters.add(_17_0_5_edc0357_1346103010487_691704_14535_exists);
                constraintExpressions.add(constraint652);
                dependencies.add(_17_0_5_edc0357_1346103010487_691704_14535_existsDependency);
            }

            public void init_17_0_5_edc0357_1346102519738_328444_14424Elaborations() {
                Expression<?>[] arguments653 = new Expression<?>[1];
                arguments653[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition653 = new Expression<Boolean>(_17_0_5_edc0357_1346103010487_691704_14535_exists);
                elaborationRule653 = addElaborationRule(condition653, _17_0_5_edc0357_1346102095091_571012_14257.this, LADWP._17_0_5_edc0357_1346102095091_571012_14257._17_0_5_edc0357_1346103010487_691704_14535.class, "_MergeNode_waitForDRResponse", arguments653);
            }

            public _17_0_5_edc0357_1346102519738_328444_14424() {
                super();
                init_17_0_5_edc0357_1346102519738_328444_14424Members();
                init_17_0_5_edc0357_1346102519738_328444_14424Collections();
                init_17_0_5_edc0357_1346102519738_328444_14424Elaborations();
            }

            public _17_0_5_edc0357_1346102519738_328444_14424(Expression<Integer> _17_0_5_edc0357_1346102573506_761015_14458_endTime) {
                super();
                init_17_0_5_edc0357_1346102519738_328444_14424Members();
                init_17_0_5_edc0357_1346102519738_328444_14424Collections();
                addDependency(this._17_0_5_edc0357_1346102573506_761015_14458_endTime, _17_0_5_edc0357_1346102573506_761015_14458_endTime);
                init_17_0_5_edc0357_1346102519738_328444_14424Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1346102549978_121685_14442 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1346102573506_761015_14458_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1346103010487_691704_14535_exists = null;

            public ConstraintExpression constraint654 = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346103010487_691704_14535_existsDependency = null;

            public ElaborationRule elaborationRule655 = null;

            public void init_17_0_5_edc0357_1346102549978_121685_14442Members() {
                try {
                    _17_0_5_edc0357_1346102573506_761015_14458_endTime = new IntegerParameter("_17_0_5_edc0357_1346102573506_761015_14458_endTime", this);
                    _17_0_5_edc0357_1346103010487_691704_14535_exists = new BooleanParameter("_17_0_5_edc0357_1346103010487_691704_14535_exists", this);
                    constraint654 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346102573506_761015_14458_endTime)));
                    durationDependency = new Dependency<Integer>(duration, new Expression<Integer>(240));
                    _17_0_5_edc0357_1346103010487_691704_14535_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346103010487_691704_14535_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346102549978_121685_14442Collections() {
                parameters.add(_17_0_5_edc0357_1346102573506_761015_14458_endTime);
                parameters.add(_17_0_5_edc0357_1346103010487_691704_14535_exists);
                constraintExpressions.add(constraint654);
                removeDependency(duration);
                dependencies.add(durationDependency);
                dependencies.add(_17_0_5_edc0357_1346103010487_691704_14535_existsDependency);
            }

            public void init_17_0_5_edc0357_1346102549978_121685_14442Elaborations() {
                Expression<?>[] arguments655 = new Expression<?>[1];
                arguments655[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition655 = new Expression<Boolean>(_17_0_5_edc0357_1346103010487_691704_14535_exists);
                elaborationRule655 = addElaborationRule(condition655, _17_0_5_edc0357_1346102095091_571012_14257.this, LADWP._17_0_5_edc0357_1346102095091_571012_14257._17_0_5_edc0357_1346103010487_691704_14535.class, "_MergeNode_waitForDRResponse", arguments655);
            }

            public _17_0_5_edc0357_1346102549978_121685_14442() {
                super();
                init_17_0_5_edc0357_1346102549978_121685_14442Members();
                init_17_0_5_edc0357_1346102549978_121685_14442Collections();
                init_17_0_5_edc0357_1346102549978_121685_14442Elaborations();
            }

            public _17_0_5_edc0357_1346102549978_121685_14442(Expression<Integer> _17_0_5_edc0357_1346102573506_761015_14458_endTime) {
                super();
                init_17_0_5_edc0357_1346102549978_121685_14442Members();
                init_17_0_5_edc0357_1346102549978_121685_14442Collections();
                addDependency(this._17_0_5_edc0357_1346102573506_761015_14458_endTime, _17_0_5_edc0357_1346102573506_761015_14458_endTime);
                init_17_0_5_edc0357_1346102549978_121685_14442Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1346102573506_761015_14458 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1346102519738_328444_14424_exists = null;

            public BooleanParameter _17_0_5_edc0357_1346102441989_709933_14404_exists = null;

            public IntegerParameter _17_0_5_edc0357_1346102438886_844068_14398_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1346102549978_121685_14442_exists = null;

            public ConstraintExpression constraint656 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346102519738_328444_14424_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346102441989_709933_14404_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346102549978_121685_14442_existsDependency = null;

            public ElaborationRule elaborationRule657 = null;

            public ElaborationRule elaborationRule658 = null;

            public ElaborationRule elaborationRule659 = null;

            public void init_17_0_5_edc0357_1346102573506_761015_14458Members() {
                try {
                    _17_0_5_edc0357_1346102519738_328444_14424_exists = new BooleanParameter("_17_0_5_edc0357_1346102519738_328444_14424_exists", this);
                    _17_0_5_edc0357_1346102441989_709933_14404_exists = new BooleanParameter("_17_0_5_edc0357_1346102441989_709933_14404_exists", this);
                    _17_0_5_edc0357_1346102438886_844068_14398_endTime = new IntegerParameter("_17_0_5_edc0357_1346102438886_844068_14398_endTime", this);
                    _17_0_5_edc0357_1346102549978_121685_14442_exists = new BooleanParameter("_17_0_5_edc0357_1346102549978_121685_14442_exists", this);
                    constraint656 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346102438886_844068_14398_endTime)));
                    _17_0_5_edc0357_1346102519738_328444_14424_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346102519738_328444_14424_exists, new Expression(new FunctionCall(q_LADWP_no, Utils.getMethodForArgTypes("ObjectFlow<Signalno>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })));
                    _17_0_5_edc0357_1346102441989_709933_14404_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346102441989_709933_14404_exists, new Expression(new FunctionCall(q_LADWP_yes, Utils.getMethodForArgTypes("ObjectFlow<Signalyes>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })));
                    _17_0_5_edc0357_1346102549978_121685_14442_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346102549978_121685_14442_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346102573506_761015_14458Collections() {
                parameters.add(_17_0_5_edc0357_1346102519738_328444_14424_exists);
                parameters.add(_17_0_5_edc0357_1346102441989_709933_14404_exists);
                parameters.add(_17_0_5_edc0357_1346102438886_844068_14398_endTime);
                parameters.add(_17_0_5_edc0357_1346102549978_121685_14442_exists);
                constraintExpressions.add(constraint656);
                dependencies.add(_17_0_5_edc0357_1346102519738_328444_14424_existsDependency);
                dependencies.add(_17_0_5_edc0357_1346102441989_709933_14404_existsDependency);
                dependencies.add(_17_0_5_edc0357_1346102549978_121685_14442_existsDependency);
            }

            public void init_17_0_5_edc0357_1346102573506_761015_14458Elaborations() {
                Expression<?>[] arguments657 = new Expression<?>[1];
                arguments657[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition657 = new Expression<Boolean>(_17_0_5_edc0357_1346102549978_121685_14442_exists);
                elaborationRule657 = addElaborationRule(condition657, _17_0_5_edc0357_1346102095091_571012_14257.this, LADWP._17_0_5_edc0357_1346102095091_571012_14257._17_0_5_edc0357_1346102549978_121685_14442.class, "_AcceptEventAction_waitForDRResponse", arguments657);
                Expression<?>[] arguments658 = new Expression<?>[1];
                arguments658[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition658 = new Expression<Boolean>(_17_0_5_edc0357_1346102519738_328444_14424_exists);
                elaborationRule658 = addElaborationRule(condition658, _17_0_5_edc0357_1346102095091_571012_14257.this, LADWP._17_0_5_edc0357_1346102095091_571012_14257._17_0_5_edc0357_1346102519738_328444_14424.class, "n_AcceptEventAction_waitForDRResponse", arguments658);
                Expression<?>[] arguments659 = new Expression<?>[1];
                arguments659[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition659 = new Expression<Boolean>(_17_0_5_edc0357_1346102441989_709933_14404_exists);
                elaborationRule659 = addElaborationRule(condition659, _17_0_5_edc0357_1346102095091_571012_14257.this, LADWP._17_0_5_edc0357_1346102095091_571012_14257._17_0_5_edc0357_1346102441989_709933_14404.class, "y_AcceptEventAction_waitForDRResponse", arguments659);
            }

            public _17_0_5_edc0357_1346102573506_761015_14458() {
                super();
                init_17_0_5_edc0357_1346102573506_761015_14458Members();
                init_17_0_5_edc0357_1346102573506_761015_14458Collections();
                init_17_0_5_edc0357_1346102573506_761015_14458Elaborations();
            }

            public _17_0_5_edc0357_1346102573506_761015_14458(Expression<Integer> _17_0_5_edc0357_1346102438886_844068_14398_endTime) {
                super();
                init_17_0_5_edc0357_1346102573506_761015_14458Members();
                init_17_0_5_edc0357_1346102573506_761015_14458Collections();
                addDependency(this._17_0_5_edc0357_1346102438886_844068_14398_endTime, _17_0_5_edc0357_1346102438886_844068_14398_endTime);
                init_17_0_5_edc0357_1346102573506_761015_14458Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1346102903802_613014_14509 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1346102889543_283441_14507 = null;

            public IntegerParameter _17_0_5_edc0357_1346103062558_947732_14567_endTime = null;

            public ConstraintExpression constraint660 = null;

            public Effect effect661 = null;

            public Parameter effect661Var = null;

            public void init_17_0_5_edc0357_1346102903802_613014_14509Members() {
                try {
                    _17_0_5_edc0357_1346102889543_283441_14507 = new IntegerParameter("_17_0_5_edc0357_1346102889543_283441_14507", this);
                    _17_0_5_edc0357_1346103062558_947732_14567_endTime = new IntegerParameter("_17_0_5_edc0357_1346103062558_947732_14567_endTime", this);
                    constraint660 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346103062558_947732_14567_endTime)));
                    Object effect661VarV = sig_17_0_5_edc0357_1346102889543_283441_14507;
                    effect661Var = new Parameter("effect661Var", null, null, this);
                    addDependency(effect661Var, new Expression(effect661VarV));
                    effect661 = new EffectFunction(new FunctionCall(effect661Var, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { _17_0_5_edc0357_1346102889543_283441_14507, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346102903802_613014_14509Collections() {
                parameters.add(_17_0_5_edc0357_1346102889543_283441_14507);
                parameters.add(_17_0_5_edc0357_1346103062558_947732_14567_endTime);
                constraintExpressions.add(constraint660);
                Set<Effect> effectsForeffect661Var = new HashSet<Effect>();
                effectsForeffect661Var.add(effect661);
                effects.put((Parameter<?>) effect661Var, effectsForeffect661Var);
            }

            public void init_17_0_5_edc0357_1346102903802_613014_14509Elaborations() {
            }

            public _17_0_5_edc0357_1346102903802_613014_14509() {
                super();
                init_17_0_5_edc0357_1346102903802_613014_14509Members();
                init_17_0_5_edc0357_1346102903802_613014_14509Collections();
                init_17_0_5_edc0357_1346102903802_613014_14509Elaborations();
            }

            public _17_0_5_edc0357_1346102903802_613014_14509(Expression<Integer> _17_0_5_edc0357_1346103062558_947732_14567_endTime, Expression<Integer> _17_0_5_edc0357_1346102889543_283441_14507) {
                super();
                init_17_0_5_edc0357_1346102903802_613014_14509Members();
                init_17_0_5_edc0357_1346102903802_613014_14509Collections();
                addDependency(this._17_0_5_edc0357_1346103062558_947732_14567_endTime, _17_0_5_edc0357_1346103062558_947732_14567_endTime);
                addDependency(this._17_0_5_edc0357_1346102889543_283441_14507, _17_0_5_edc0357_1346102889543_283441_14507);
                init_17_0_5_edc0357_1346102903802_613014_14509Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1346103010487_691704_14535 extends DurativeEvent {

            public IntegerParameter invoker_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1346103606797_653782_14706_exists = null;

            public ConstraintExpression constraint662 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346103606797_653782_14706_existsDependency = null;

            public ElaborationRule elaborationRule663 = null;

            public void init_17_0_5_edc0357_1346103010487_691704_14535Members() {
                try {
                    invoker_endTime = new IntegerParameter("invoker_endTime", this);
                    _17_0_5_edc0357_1346103606797_653782_14706_exists = new BooleanParameter("_17_0_5_edc0357_1346103606797_653782_14706_exists", this);
                    constraint662 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(invoker_endTime)));
                    _17_0_5_edc0357_1346103606797_653782_14706_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346103606797_653782_14706_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346103010487_691704_14535Collections() {
                parameters.add(invoker_endTime);
                parameters.add(_17_0_5_edc0357_1346103606797_653782_14706_exists);
                constraintExpressions.add(constraint662);
                dependencies.add(_17_0_5_edc0357_1346103606797_653782_14706_existsDependency);
            }

            public void init_17_0_5_edc0357_1346103010487_691704_14535Elaborations() {
                Expression<?>[] arguments663 = new Expression<?>[1];
                arguments663[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition663 = new Expression<Boolean>(_17_0_5_edc0357_1346103606797_653782_14706_exists);
                elaborationRule663 = addElaborationRule(condition663, _17_0_5_edc0357_1346102095091_571012_14257.this, LADWP._17_0_5_edc0357_1346102095091_571012_14257._17_0_5_edc0357_1346103606797_653782_14706.class, "_ForkNode_waitForDRResponse", arguments663);
            }

            public _17_0_5_edc0357_1346103010487_691704_14535() {
                super();
                init_17_0_5_edc0357_1346103010487_691704_14535Members();
                init_17_0_5_edc0357_1346103010487_691704_14535Collections();
                init_17_0_5_edc0357_1346103010487_691704_14535Elaborations();
            }

            public _17_0_5_edc0357_1346103010487_691704_14535(Expression<Integer> invoker_endTime) {
                super();
                init_17_0_5_edc0357_1346103010487_691704_14535Members();
                init_17_0_5_edc0357_1346103010487_691704_14535Collections();
                addDependency(this.invoker_endTime, invoker_endTime);
                init_17_0_5_edc0357_1346103010487_691704_14535Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1346103021014_230289_14555 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1346103381981_27349_14621_endTime = null;

            public ConstraintExpression constraint664 = null;

            public void init_17_0_5_edc0357_1346103021014_230289_14555Members() {
                try {
                    _17_0_5_edc0357_1346103381981_27349_14621_endTime = new IntegerParameter("_17_0_5_edc0357_1346103381981_27349_14621_endTime", this);
                    constraint664 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346103381981_27349_14621_endTime)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346103021014_230289_14555Collections() {
                parameters.add(_17_0_5_edc0357_1346103381981_27349_14621_endTime);
                constraintExpressions.add(constraint664);
            }

            public void init_17_0_5_edc0357_1346103021014_230289_14555Elaborations() {
            }

            public _17_0_5_edc0357_1346103021014_230289_14555() {
                super();
                init_17_0_5_edc0357_1346103021014_230289_14555Members();
                init_17_0_5_edc0357_1346103021014_230289_14555Collections();
                init_17_0_5_edc0357_1346103021014_230289_14555Elaborations();
            }

            public _17_0_5_edc0357_1346103021014_230289_14555(Expression<Integer> _17_0_5_edc0357_1346103381981_27349_14621_endTime) {
                super();
                init_17_0_5_edc0357_1346103021014_230289_14555Members();
                init_17_0_5_edc0357_1346103021014_230289_14555Collections();
                addDependency(this._17_0_5_edc0357_1346103381981_27349_14621_endTime, _17_0_5_edc0357_1346103381981_27349_14621_endTime);
                init_17_0_5_edc0357_1346103021014_230289_14555Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1346103062558_947732_14567 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1346103096100_759763_14588 = null;

            public IntegerParameter _17_0_5_edc0357_1346102441989_709933_14404_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1346102903802_613014_14509_exists = null;

            public Parameter< Power_System.Signalyes > _17_0_5_edc0357_1346103082067_604440_14587 = null;

            public BooleanParameter _17_0_5_edc0357_1346103010487_691704_14535_exists = null;

            public ConstraintExpression constraint665 = null;

            public Dependency< Integer > _17_0_5_edc0357_1346103096100_759763_14588Dependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346102903802_613014_14509_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346103010487_691704_14535_existsDependency = null;

            public ElaborationRule elaborationRule666 = null;

            public ElaborationRule elaborationRule667 = null;

            public void init_17_0_5_edc0357_1346103062558_947732_14567Members() {
                try {
                    _17_0_5_edc0357_1346103096100_759763_14588 = new IntegerParameter("_17_0_5_edc0357_1346103096100_759763_14588", this);
                    _17_0_5_edc0357_1346102441989_709933_14404_endTime = new IntegerParameter("_17_0_5_edc0357_1346102441989_709933_14404_endTime", this);
                    _17_0_5_edc0357_1346102903802_613014_14509_exists = new BooleanParameter("_17_0_5_edc0357_1346102903802_613014_14509_exists", this);
                    _17_0_5_edc0357_1346103082067_604440_14587 = new Parameter<Power_System.Signalyes>("_17_0_5_edc0357_1346103082067_604440_14587", null, null, this);
                    _17_0_5_edc0357_1346103010487_691704_14535_exists = new BooleanParameter("_17_0_5_edc0357_1346103010487_691704_14535_exists", this);
                    constraint665 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346102441989_709933_14404_endTime)));
                    _17_0_5_edc0357_1346103096100_759763_14588Dependency = new Dependency<Integer>(_17_0_5_edc0357_1346103096100_759763_14588, new Expression(new FunctionCall(null, Utils.getMethodForArgTypes("LADWP._17_0_5_edc0357_1346102095091_571012_14257._17_0_5_edc0357_1346103062558_947732_14567", "generated", "getValue"), new Object[] { new Expression<Integer>(startTime) }, new FunctionCall(null, Parameter.class, "getValue", (Object[]) null, new FunctionCall(_17_0_5_edc0357_1346103082067_604440_14587, Parameter.class, "getMember", new Object[] { "newLoad__17_0_5_edc0357_1345510113619_149263_13857" })))));
                    _17_0_5_edc0357_1346102903802_613014_14509_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346102903802_613014_14509_exists, new Expression<Boolean>(true));
                    _17_0_5_edc0357_1346103010487_691704_14535_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346103010487_691704_14535_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346103062558_947732_14567Collections() {
                parameters.add(_17_0_5_edc0357_1346103096100_759763_14588);
                parameters.add(_17_0_5_edc0357_1346102441989_709933_14404_endTime);
                parameters.add(_17_0_5_edc0357_1346102903802_613014_14509_exists);
                parameters.add(_17_0_5_edc0357_1346103082067_604440_14587);
                parameters.add(_17_0_5_edc0357_1346103010487_691704_14535_exists);
                constraintExpressions.add(constraint665);
                dependencies.add(_17_0_5_edc0357_1346103096100_759763_14588Dependency);
                dependencies.add(_17_0_5_edc0357_1346102903802_613014_14509_existsDependency);
                dependencies.add(_17_0_5_edc0357_1346103010487_691704_14535_existsDependency);
            }

            public void init_17_0_5_edc0357_1346103062558_947732_14567Elaborations() {
                Expression<?>[] arguments666 = new Expression<?>[2];
                arguments666[0] = new Expression<Integer>(endTime);
                arguments666[1] = new Expression<Integer>(_17_0_5_edc0357_1346103096100_759763_14588);
                Expression<Boolean> condition666 = new Expression<Boolean>(_17_0_5_edc0357_1346102903802_613014_14509_exists);
                elaborationRule666 = addElaborationRule(condition666, _17_0_5_edc0357_1346102095091_571012_14257.this, LADWP._17_0_5_edc0357_1346102095091_571012_14257._17_0_5_edc0357_1346102903802_613014_14509.class, "result_ActivityParameterNode_waitForDRResponse", arguments666);
                Expression<?>[] arguments667 = new Expression<?>[1];
                arguments667[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition667 = new Expression<Boolean>(_17_0_5_edc0357_1346103010487_691704_14535_exists);
                elaborationRule667 = addElaborationRule(condition667, _17_0_5_edc0357_1346102095091_571012_14257.this, LADWP._17_0_5_edc0357_1346102095091_571012_14257._17_0_5_edc0357_1346103010487_691704_14535.class, "_MergeNode_waitForDRResponse", arguments667);
            }

            public _17_0_5_edc0357_1346103062558_947732_14567() {
                super();
                init_17_0_5_edc0357_1346103062558_947732_14567Members();
                init_17_0_5_edc0357_1346103062558_947732_14567Collections();
                init_17_0_5_edc0357_1346103062558_947732_14567Elaborations();
            }

            public _17_0_5_edc0357_1346103062558_947732_14567(Expression<Integer> _17_0_5_edc0357_1346102441989_709933_14404_endTime, Expression<Power_System.Signalyes> _17_0_5_edc0357_1346103082067_604440_14587) {
                super();
                init_17_0_5_edc0357_1346103062558_947732_14567Members();
                init_17_0_5_edc0357_1346103062558_947732_14567Collections();
                addDependency(this._17_0_5_edc0357_1346102441989_709933_14404_endTime, _17_0_5_edc0357_1346102441989_709933_14404_endTime);
                addDependency(this._17_0_5_edc0357_1346103082067_604440_14587, _17_0_5_edc0357_1346103082067_604440_14587);
                init_17_0_5_edc0357_1346103062558_947732_14567Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1346103381981_27349_14621 extends DurativeEvent {

            public Parameter< LADWP > _17_0_5_edc0357_1346103407692_242808_14639 = null;

            public IntegerParameter _17_0_5_edc0357_1346103478833_548117_14651_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1346103021014_230289_14555_exists = null;

            public BooleanParameter _17_0_5_edc0357_1346103434426_385137_14640 = null;

            public ConstraintExpression constraint668 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346103021014_230289_14555_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346103434426_385137_14640Dependency = null;

            public Effect effect669 = null;

            public Parameter effect669Var = null;

            public ElaborationRule elaborationRule670 = null;

            public void init_17_0_5_edc0357_1346103381981_27349_14621Members() {
                try {
                    _17_0_5_edc0357_1346103407692_242808_14639 = new Parameter<LADWP>("_17_0_5_edc0357_1346103407692_242808_14639", null, null, this);
                    _17_0_5_edc0357_1346103478833_548117_14651_endTime = new IntegerParameter("_17_0_5_edc0357_1346103478833_548117_14651_endTime", this);
                    _17_0_5_edc0357_1346103021014_230289_14555_exists = new BooleanParameter("_17_0_5_edc0357_1346103021014_230289_14555_exists", this);
                    _17_0_5_edc0357_1346103434426_385137_14640 = new BooleanParameter("_17_0_5_edc0357_1346103434426_385137_14640", this);
                    constraint668 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346103478833_548117_14651_endTime)));
                    _17_0_5_edc0357_1346103021014_230289_14555_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346103021014_230289_14555_exists, new Expression<Boolean>(true));
                    _17_0_5_edc0357_1346103434426_385137_14640Dependency = new Dependency<Boolean>(_17_0_5_edc0357_1346103434426_385137_14640, new Expression(new FunctionCall(sig_17_0_5_edc0357_1346103552856_270449_14698, Utils.getMethodForArgTypes("ObjectFlow<Boolean>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    Object effect669VarV = demandResponse__17_0_5_edc0357_1345510113615_857599_13851;
                    effect669Var = new Parameter("effect669Var", null, null, this);
                    addDependency(effect669Var, new Expression(effect669VarV));
                    effect669 = new EffectFunction(new FunctionCall(effect669Var, Utils.getMethodForArgTypes("TimeVaryingMap<Boolean>", "generated", "setValue", java.lang.Integer.class, java.lang.Object.class), new Object[] { startTime, _17_0_5_edc0357_1346103434426_385137_14640 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346103381981_27349_14621Collections() {
                parameters.add(_17_0_5_edc0357_1346103407692_242808_14639);
                parameters.add(_17_0_5_edc0357_1346103478833_548117_14651_endTime);
                parameters.add(_17_0_5_edc0357_1346103021014_230289_14555_exists);
                parameters.add(_17_0_5_edc0357_1346103434426_385137_14640);
                constraintExpressions.add(constraint668);
                dependencies.add(_17_0_5_edc0357_1346103021014_230289_14555_existsDependency);
                dependencies.add(_17_0_5_edc0357_1346103434426_385137_14640Dependency);
                Set<Effect> effectsForeffect669Var = new HashSet<Effect>();
                effectsForeffect669Var.add(effect669);
                effects.put((Parameter<?>) effect669Var, effectsForeffect669Var);
            }

            public void init_17_0_5_edc0357_1346103381981_27349_14621Elaborations() {
                Expression<?>[] arguments670 = new Expression<?>[1];
                arguments670[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition670 = new Expression<Boolean>(_17_0_5_edc0357_1346103021014_230289_14555_exists);
                elaborationRule670 = addElaborationRule(condition670, _17_0_5_edc0357_1346102095091_571012_14257.this, LADWP._17_0_5_edc0357_1346102095091_571012_14257._17_0_5_edc0357_1346103021014_230289_14555.class, "_ActivityFinalNode_waitForDRResponse", arguments670);
            }

            public _17_0_5_edc0357_1346103381981_27349_14621() {
                super();
                init_17_0_5_edc0357_1346103381981_27349_14621Members();
                init_17_0_5_edc0357_1346103381981_27349_14621Collections();
                init_17_0_5_edc0357_1346103381981_27349_14621Elaborations();
            }

            public _17_0_5_edc0357_1346103381981_27349_14621(Expression<Integer> _17_0_5_edc0357_1346103478833_548117_14651_endTime, Expression<LADWP> _17_0_5_edc0357_1346103407692_242808_14639) {
                super();
                init_17_0_5_edc0357_1346103381981_27349_14621Members();
                init_17_0_5_edc0357_1346103381981_27349_14621Collections();
                addDependency(this._17_0_5_edc0357_1346103478833_548117_14651_endTime, _17_0_5_edc0357_1346103478833_548117_14651_endTime);
                addDependency(this._17_0_5_edc0357_1346103407692_242808_14639, _17_0_5_edc0357_1346103407692_242808_14639);
                init_17_0_5_edc0357_1346103381981_27349_14621Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1346103478833_548117_14651 extends DurativeEvent {

            public Parameter< LADWP > _17_0_5_edc0357_1346103478833_637965_14652 = null;

            public IntegerParameter _17_0_5_edc0357_1346103606797_653782_14706_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1346103381981_27349_14621_exists = null;

            public ConstraintExpression constraint671 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346103381981_27349_14621_existsDependency = null;

            public ElaborationRule elaborationRule672 = null;

            public void init_17_0_5_edc0357_1346103478833_548117_14651Members() {
                try {
                    _17_0_5_edc0357_1346103478833_637965_14652 = new Parameter<LADWP>("_17_0_5_edc0357_1346103478833_637965_14652", null, LADWP.this, this);
                    _17_0_5_edc0357_1346103606797_653782_14706_endTime = new IntegerParameter("_17_0_5_edc0357_1346103606797_653782_14706_endTime", this);
                    _17_0_5_edc0357_1346103381981_27349_14621_exists = new BooleanParameter("_17_0_5_edc0357_1346103381981_27349_14621_exists", this);
                    constraint671 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346103606797_653782_14706_endTime)));
                    _17_0_5_edc0357_1346103381981_27349_14621_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346103381981_27349_14621_exists, new Expression(new FunctionCall(sig_17_0_5_edc0357_1346103552856_270449_14698, Utils.getMethodForArgTypes("ObjectFlow<Boolean>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346103478833_548117_14651Collections() {
                parameters.add(_17_0_5_edc0357_1346103478833_637965_14652);
                parameters.add(_17_0_5_edc0357_1346103606797_653782_14706_endTime);
                parameters.add(_17_0_5_edc0357_1346103381981_27349_14621_exists);
                constraintExpressions.add(constraint671);
                dependencies.add(_17_0_5_edc0357_1346103381981_27349_14621_existsDependency);
            }

            public void init_17_0_5_edc0357_1346103478833_548117_14651Elaborations() {
                Expression<?>[] arguments672 = new Expression<?>[2];
                arguments672[0] = new Expression<Integer>(endTime);
                arguments672[1] = new Expression<LADWP>(_17_0_5_edc0357_1346103478833_637965_14652);
                Expression<Boolean> condition672 = new Expression<Boolean>(_17_0_5_edc0357_1346103381981_27349_14621_exists);
                elaborationRule672 = addElaborationRule(condition672, _17_0_5_edc0357_1346102095091_571012_14257.this, LADWP._17_0_5_edc0357_1346102095091_571012_14257._17_0_5_edc0357_1346103381981_27349_14621.class, "_AddStructuralFeatureValueAction_waitForDRResponse", arguments672);
            }

            public _17_0_5_edc0357_1346103478833_548117_14651() {
                super();
                init_17_0_5_edc0357_1346103478833_548117_14651Members();
                init_17_0_5_edc0357_1346103478833_548117_14651Collections();
                init_17_0_5_edc0357_1346103478833_548117_14651Elaborations();
            }

            public _17_0_5_edc0357_1346103478833_548117_14651(Expression<Integer> _17_0_5_edc0357_1346103606797_653782_14706_endTime) {
                super();
                init_17_0_5_edc0357_1346103478833_548117_14651Members();
                init_17_0_5_edc0357_1346103478833_548117_14651Collections();
                addDependency(this._17_0_5_edc0357_1346103606797_653782_14706_endTime, _17_0_5_edc0357_1346103606797_653782_14706_endTime);
                init_17_0_5_edc0357_1346103478833_548117_14651Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1346103518161_525445_14678 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1346103606797_653782_14706_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1346103524024_311757_14690 = null;

            public ConstraintExpression constraint673 = null;

            public Effect effect674 = null;

            public Parameter effect674Var = null;

            public void init_17_0_5_edc0357_1346103518161_525445_14678Members() {
                try {
                    _17_0_5_edc0357_1346103606797_653782_14706_endTime = new IntegerParameter("_17_0_5_edc0357_1346103606797_653782_14706_endTime", this);
                    _17_0_5_edc0357_1346103524024_311757_14690 = new BooleanParameter("_17_0_5_edc0357_1346103524024_311757_14690", this);
                    constraint673 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346103606797_653782_14706_endTime)));
                    Object effect674VarV = sig_17_0_5_edc0357_1346103552856_270449_14698;
                    effect674Var = new Parameter("effect674Var", null, null, this);
                    addDependency(effect674Var, new Expression(effect674VarV));
                    effect674 = new EffectFunction(new FunctionCall(effect674Var, Utils.getMethodForArgTypes("ObjectFlow<Boolean>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { _17_0_5_edc0357_1346103524024_311757_14690, startTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346103518161_525445_14678Collections() {
                parameters.add(_17_0_5_edc0357_1346103606797_653782_14706_endTime);
                parameters.add(_17_0_5_edc0357_1346103524024_311757_14690);
                constraintExpressions.add(constraint673);
                Set<Effect> effectsForeffect674Var = new HashSet<Effect>();
                effectsForeffect674Var.add(effect674);
                effects.put((Parameter<?>) effect674Var, effectsForeffect674Var);
            }

            public void init_17_0_5_edc0357_1346103518161_525445_14678Elaborations() {
            }

            public _17_0_5_edc0357_1346103518161_525445_14678() {
                super();
                init_17_0_5_edc0357_1346103518161_525445_14678Members();
                init_17_0_5_edc0357_1346103518161_525445_14678Collections();
                init_17_0_5_edc0357_1346103518161_525445_14678Elaborations();
            }

            public _17_0_5_edc0357_1346103518161_525445_14678(Expression<Integer> _17_0_5_edc0357_1346103606797_653782_14706_endTime) {
                super();
                init_17_0_5_edc0357_1346103518161_525445_14678Members();
                init_17_0_5_edc0357_1346103518161_525445_14678Collections();
                addDependency(this._17_0_5_edc0357_1346103606797_653782_14706_endTime, _17_0_5_edc0357_1346103606797_653782_14706_endTime);
                init_17_0_5_edc0357_1346103518161_525445_14678Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1346103606797_653782_14706 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1346103518161_525445_14678_exists = null;

            public IntegerParameter _17_0_5_edc0357_1346103010487_691704_14535_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1346103478833_548117_14651_exists = null;

            public ConstraintExpression constraint675 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346103518161_525445_14678_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346103478833_548117_14651_existsDependency = null;

            public ElaborationRule elaborationRule676 = null;

            public ElaborationRule elaborationRule677 = null;

            public void init_17_0_5_edc0357_1346103606797_653782_14706Members() {
                try {
                    _17_0_5_edc0357_1346103518161_525445_14678_exists = new BooleanParameter("_17_0_5_edc0357_1346103518161_525445_14678_exists", this);
                    _17_0_5_edc0357_1346103010487_691704_14535_endTime = new IntegerParameter("_17_0_5_edc0357_1346103010487_691704_14535_endTime", this);
                    _17_0_5_edc0357_1346103478833_548117_14651_exists = new BooleanParameter("_17_0_5_edc0357_1346103478833_548117_14651_exists", this);
                    constraint675 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346103010487_691704_14535_endTime)));
                    _17_0_5_edc0357_1346103518161_525445_14678_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346103518161_525445_14678_exists, new Expression<Boolean>(true));
                    _17_0_5_edc0357_1346103478833_548117_14651_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346103478833_548117_14651_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346103606797_653782_14706Collections() {
                parameters.add(_17_0_5_edc0357_1346103518161_525445_14678_exists);
                parameters.add(_17_0_5_edc0357_1346103010487_691704_14535_endTime);
                parameters.add(_17_0_5_edc0357_1346103478833_548117_14651_exists);
                constraintExpressions.add(constraint675);
                dependencies.add(_17_0_5_edc0357_1346103518161_525445_14678_existsDependency);
                dependencies.add(_17_0_5_edc0357_1346103478833_548117_14651_existsDependency);
            }

            public void init_17_0_5_edc0357_1346103606797_653782_14706Elaborations() {
                Expression<?>[] arguments676 = new Expression<?>[1];
                arguments676[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition676 = new Expression<Boolean>(_17_0_5_edc0357_1346103518161_525445_14678_exists);
                elaborationRule676 = addElaborationRule(condition676, _17_0_5_edc0357_1346102095091_571012_14257.this, LADWP._17_0_5_edc0357_1346102095091_571012_14257._17_0_5_edc0357_1346103518161_525445_14678.class, "_ValueSpecificationAction_waitForDRResponse", arguments676);
                Expression<?>[] arguments677 = new Expression<?>[1];
                arguments677[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition677 = new Expression<Boolean>(_17_0_5_edc0357_1346103478833_548117_14651_exists);
                elaborationRule677 = addElaborationRule(condition677, _17_0_5_edc0357_1346102095091_571012_14257.this, LADWP._17_0_5_edc0357_1346102095091_571012_14257._17_0_5_edc0357_1346103478833_548117_14651.class, "_ReadSelfAction_waitForDRResponse", arguments677);
            }

            public _17_0_5_edc0357_1346103606797_653782_14706() {
                super();
                init_17_0_5_edc0357_1346103606797_653782_14706Members();
                init_17_0_5_edc0357_1346103606797_653782_14706Collections();
                init_17_0_5_edc0357_1346103606797_653782_14706Elaborations();
            }

            public _17_0_5_edc0357_1346103606797_653782_14706(Expression<Integer> _17_0_5_edc0357_1346103010487_691704_14535_endTime) {
                super();
                init_17_0_5_edc0357_1346103606797_653782_14706Members();
                init_17_0_5_edc0357_1346103606797_653782_14706Collections();
                addDependency(this._17_0_5_edc0357_1346103010487_691704_14535_endTime, _17_0_5_edc0357_1346103010487_691704_14535_endTime);
                init_17_0_5_edc0357_1346103606797_653782_14706Elaborations();
                fixTimeDependencies();
            }
        }

        public _17_0_5_edc0357_1346102095091_571012_14257(Expression<Integer> endTime, Expression<ObjectFlow<Integer>> sig_17_0_5_edc0357_1346102889543_283441_14507) {
            super();
            init_17_0_5_edc0357_1346102095091_571012_14257Members();
            init_17_0_5_edc0357_1346102095091_571012_14257Collections();
            removeDependency(this.endTime);
            addDependency(this.endTime, endTime);
            addDependency(this.sig_17_0_5_edc0357_1346102889543_283441_14507, sig_17_0_5_edc0357_1346102889543_283441_14507);
            init_17_0_5_edc0357_1346102095091_571012_14257Elaborations();
            fixTimeDependencies();
        }
    }

    public LADWP(Power_System x) {
        super();
        this.x = new Parameter<Power_System>("Power_System", null, x, null);
        initLADWPMembers();
        initLADWPCollections();
        initLADWPElaborations();
    }
}
