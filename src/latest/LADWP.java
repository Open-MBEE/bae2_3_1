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

        public Parameter< Object > _17_0_5_edc0357_1346100922767_721473_13763 = null;

        public IntegerParameter invoke_time = null;

        public ElaborationRule elaborationRule303 = null;

        public ElaborationRule elaborationRule304 = null;

        public void init_17_0_5_edc0357_1345510113596_603128_13828Members() {
            try {
                sig_17_0_5_edc0357_1346100955782_830873_13796 = new Parameter<ObjectFlow<Integer>>("sig_17_0_5_edc0357_1346100955782_830873_13796", null, new ObjectFlow("sig_17_0_5_edc0357_1346100955782_830873_13796"), this);
                _17_0_5_edc0357_1346100922767_721473_13763 = new Parameter<Object>("_17_0_5_edc0357_1346100922767_721473_13763", null, null, this);
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
            Expression<?>[] arguments303 = new Expression<?>[2];
            arguments303[0] = new Expression<Integer>(invoke_time);
            arguments303[1] = new Expression<Object>(_17_0_5_edc0357_1346100922767_721473_13763);
            Expression<Boolean> condition303 = new Expression<Boolean>(true);
            elaborationRule303 = addElaborationRule(condition303, _17_0_5_edc0357_1345510113596_603128_13828.this, LADWP._17_0_5_edc0357_1345510113596_603128_13828._17_0_5_edc0357_1346100928357_373213_13765.class, "reportedLoad_ActivityParameterNode_updateReportedLoad", arguments303);
            Expression<?>[] arguments304 = new Expression<?>[1];
            arguments304[0] = new Expression<Integer>(invoke_time);
            Expression<Boolean> condition304 = new Expression<Boolean>(true);
            elaborationRule304 = addElaborationRule(condition304, _17_0_5_edc0357_1345510113596_603128_13828.this, LADWP._17_0_5_edc0357_1345510113596_603128_13828._17_0_5_edc0357_1345510114327_368286_14527.class, "_InitialNode_updateReportedLoad", arguments304);
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

            public ConstraintExpression constraint305 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114327_355904_14525_existsDependency = null;

            public ElaborationRule elaborationRule306 = null;

            public void init_17_0_5_edc0357_1345510114327_207025_14524Members() {
                try {
                    _17_0_5_edc0357_1345510114327_368286_14527_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114327_368286_14527_endTime", this);
                    _17_0_5_edc0357_1345510114327_355904_14525_exists = new BooleanParameter("_17_0_5_edc0357_1345510114327_355904_14525_exists", this);
                    _17_0_5_edc0357_1345510114869_550535_15247 = new Parameter<LADWP>("_17_0_5_edc0357_1345510114869_550535_15247", null, LADWP.this, this);
                    constraint305 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114327_368286_14527_endTime)));
                    _17_0_5_edc0357_1345510114327_355904_14525_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114327_355904_14525_exists, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1346100955782_830873_13796, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114327_207025_14524Collections() {
                parameters.add(_17_0_5_edc0357_1345510114327_368286_14527_endTime);
                parameters.add(_17_0_5_edc0357_1345510114327_355904_14525_exists);
                parameters.add(_17_0_5_edc0357_1345510114869_550535_15247);
                constraintExpressions.add(constraint305);
                dependencies.add(_17_0_5_edc0357_1345510114327_355904_14525_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510114327_207025_14524Elaborations() {
                Expression<?>[] arguments306 = new Expression<?>[2];
                arguments306[0] = new Expression<Integer>(endTime);
                arguments306[1] = new Expression<LADWP>(_17_0_5_edc0357_1345510114869_550535_15247);
                Expression<Boolean> condition306 = new Expression<Boolean>(_17_0_5_edc0357_1345510114327_355904_14525_exists);
                elaborationRule306 = addElaborationRule(condition306, _17_0_5_edc0357_1345510113596_603128_13828.this, LADWP._17_0_5_edc0357_1345510113596_603128_13828._17_0_5_edc0357_1345510114327_355904_14525.class, "_AddStructuralFeatureValueAction_updateReportedLoad", arguments306);
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

            public ConstraintExpression constraint307 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114328_982693_14528_existsDependency = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114870_148039_15248Dependency = null;

            public Effect effect308 = null;

            public Object effect308Var = null;

            public ElaborationRule elaborationRule309 = null;

            public void init_17_0_5_edc0357_1345510114327_355904_14525Members() {
                try {
                    _17_0_5_edc0357_1345510114872_767304_15249 = new Parameter<LADWP>("_17_0_5_edc0357_1345510114872_767304_15249", null, null, this);
                    _17_0_5_edc0357_1345510114327_207025_14524_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114327_207025_14524_endTime", this);
                    _17_0_5_edc0357_1345510114328_982693_14528_exists = new BooleanParameter("_17_0_5_edc0357_1345510114328_982693_14528_exists", this);
                    _17_0_5_edc0357_1345510114870_148039_15248 = new IntegerParameter("_17_0_5_edc0357_1345510114870_148039_15248", this);
                    constraint307 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114327_207025_14524_endTime)));
                    _17_0_5_edc0357_1345510114328_982693_14528_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114328_982693_14528_exists, new Expression<Boolean>(true));
                    _17_0_5_edc0357_1345510114870_148039_15248Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114870_148039_15248, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1346100955782_830873_13796, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    effect308Var = reported_load__17_0_5_edc0357_1345510113610_466337_13843;
                    effect308 = new EffectFunction(new FunctionCall((Object) effect308Var, Utils.getMethodForArgTypes("TimeVaryingMap<Integer>", "generated", "setValue", java.lang.Integer.class, java.lang.Object.class), new Object[] { startTime, _17_0_5_edc0357_1345510114870_148039_15248 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114327_355904_14525Collections() {
                parameters.add(_17_0_5_edc0357_1345510114872_767304_15249);
                parameters.add(_17_0_5_edc0357_1345510114327_207025_14524_endTime);
                parameters.add(_17_0_5_edc0357_1345510114328_982693_14528_exists);
                parameters.add(_17_0_5_edc0357_1345510114870_148039_15248);
                constraintExpressions.add(constraint307);
                dependencies.add(_17_0_5_edc0357_1345510114328_982693_14528_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510114870_148039_15248Dependency);
                Set<Effect> effectsForeffect308Var = new HashSet<Effect>();
                effectsForeffect308Var.add(effect308);
                effects.put((Parameter<?>) effect308Var, effectsForeffect308Var);
            }

            public void init_17_0_5_edc0357_1345510114327_355904_14525Elaborations() {
                Expression<?>[] arguments309 = new Expression<?>[1];
                arguments309[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition309 = new Expression<Boolean>(_17_0_5_edc0357_1345510114328_982693_14528_exists);
                elaborationRule309 = addElaborationRule(condition309, _17_0_5_edc0357_1345510113596_603128_13828.this, LADWP._17_0_5_edc0357_1345510113596_603128_13828._17_0_5_edc0357_1345510114328_982693_14528.class, "_ActivityFinalNode_updateReportedLoad", arguments309);
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

            public ElaborationRule elaborationRule310 = null;

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
                Expression<?>[] arguments310 = new Expression<?>[1];
                arguments310[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition310 = new Expression<Boolean>(_17_0_5_edc0357_1345510114327_207025_14524_exists);
                elaborationRule310 = addElaborationRule(condition310, _17_0_5_edc0357_1345510113596_603128_13828.this, LADWP._17_0_5_edc0357_1345510113596_603128_13828._17_0_5_edc0357_1345510114327_207025_14524.class, "_ReadSelfAction_updateReportedLoad", arguments310);
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

            public ConstraintExpression constraint311 = null;

            public void init_17_0_5_edc0357_1345510114328_982693_14528Members() {
                try {
                    _17_0_5_edc0357_1345510114327_355904_14525_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114327_355904_14525_endTime", this);
                    constraint311 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114327_355904_14525_endTime)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114328_982693_14528Collections() {
                parameters.add(_17_0_5_edc0357_1345510114327_355904_14525_endTime);
                constraintExpressions.add(constraint311);
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

            public Parameter< Object > _17_0_5_edc0357_1346100922767_721473_13763 = null;

            public Effect effect312 = null;

            public Object effect312Var = null;

            public void init_17_0_5_edc0357_1346100928357_373213_13765Members() {
                try {
                    _17_0_5_edc0357_1346100922767_721473_13763 = new Parameter<Object>("_17_0_5_edc0357_1346100922767_721473_13763", null, null, this);
                    effect312Var = sig_17_0_5_edc0357_1346100955782_830873_13796;
                    effect312 = new EffectFunction(new FunctionCall((Object) effect312Var, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { _17_0_5_edc0357_1346100922767_721473_13763, startTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346100928357_373213_13765Collections() {
                parameters.add(_17_0_5_edc0357_1346100922767_721473_13763);
                Set<Effect> effectsForeffect312Var = new HashSet<Effect>();
                effectsForeffect312Var.add(effect312);
                effects.put((Parameter<?>) effect312Var, effectsForeffect312Var);
            }

            public void init_17_0_5_edc0357_1346100928357_373213_13765Elaborations() {
            }

            public _17_0_5_edc0357_1346100928357_373213_13765() {
                super();
                init_17_0_5_edc0357_1346100928357_373213_13765Members();
                init_17_0_5_edc0357_1346100928357_373213_13765Collections();
                init_17_0_5_edc0357_1346100928357_373213_13765Elaborations();
            }

            public _17_0_5_edc0357_1346100928357_373213_13765(Expression<Integer> startTime, Expression<Object> _17_0_5_edc0357_1346100922767_721473_13763) {
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

        public _17_0_5_edc0357_1345510113596_603128_13828(Expression<Integer> endTime, Expression<Object> _17_0_5_edc0357_1346100922767_721473_13763) {
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

        public ElaborationRule elaborationRule313 = null;

        public ElaborationRule elaborationRule314 = null;

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
            Expression<?>[] arguments313 = new Expression<?>[1];
            arguments313[0] = new Expression<Integer>(invoke_time);
            Expression<Boolean> condition313 = new Expression<Boolean>(true);
            elaborationRule313 = addElaborationRule(condition313, _17_0_5_edc0357_1345510113597_14205_13829.this, LADWP._17_0_5_edc0357_1345510113597_14205_13829._17_0_5_edc0357_1345510114344_59836_14558.class, "initial_InitialNode_updateActualLoad", arguments313);
            Expression<?>[] arguments314 = new Expression<?>[2];
            arguments314[0] = new Expression<Integer>(invoke_time);
            arguments314[1] = new Expression<Integer>(_17_0_5_edc0357_1346101090161_713198_13875);
            Expression<Boolean> condition314 = new Expression<Boolean>(true);
            elaborationRule314 = addElaborationRule(condition314, _17_0_5_edc0357_1345510113597_14205_13829.this, LADWP._17_0_5_edc0357_1345510113597_14205_13829._17_0_5_edc0357_1346101105922_421213_13877.class, "actualLoad_ActivityParameterNode_updateActualLoad", arguments314);
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

            public ConstraintExpression constraint315 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114343_928274_14556_existsDependency = null;

            public ElaborationRule elaborationRule316 = null;

            public void init_17_0_5_edc0357_1345510114343_392589_14555Members() {
                try {
                    _17_0_5_edc0357_1345510114344_59836_14558_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114344_59836_14558_endTime", this);
                    _17_0_5_edc0357_1345510114343_928274_14556_exists = new BooleanParameter("_17_0_5_edc0357_1345510114343_928274_14556_exists", this);
                    _17_0_5_edc0357_1345510114875_564527_15256 = new Parameter<LADWP>("_17_0_5_edc0357_1345510114875_564527_15256", null, LADWP.this, this);
                    constraint315 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114344_59836_14558_endTime)));
                    _17_0_5_edc0357_1345510114343_928274_14556_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114343_928274_14556_exists, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1346101200222_265463_13908, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114343_392589_14555Collections() {
                parameters.add(_17_0_5_edc0357_1345510114344_59836_14558_endTime);
                parameters.add(_17_0_5_edc0357_1345510114343_928274_14556_exists);
                parameters.add(_17_0_5_edc0357_1345510114875_564527_15256);
                constraintExpressions.add(constraint315);
                dependencies.add(_17_0_5_edc0357_1345510114343_928274_14556_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510114343_392589_14555Elaborations() {
                Expression<?>[] arguments316 = new Expression<?>[2];
                arguments316[0] = new Expression<Integer>(endTime);
                arguments316[1] = new Expression<LADWP>(_17_0_5_edc0357_1345510114875_564527_15256);
                Expression<Boolean> condition316 = new Expression<Boolean>(_17_0_5_edc0357_1345510114343_928274_14556_exists);
                elaborationRule316 = addElaborationRule(condition316, _17_0_5_edc0357_1345510113597_14205_13829.this, LADWP._17_0_5_edc0357_1345510113597_14205_13829._17_0_5_edc0357_1345510114343_928274_14556.class, "readActLoad_AddStructuralFeatureValueAction_updateActualLoad", arguments316);
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

            public ConstraintExpression constraint317 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114344_743484_14559_existsDependency = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114876_2649_15257Dependency = null;

            public Effect effect318 = null;

            public Object effect318Var = null;

            public ElaborationRule elaborationRule319 = null;

            public void init_17_0_5_edc0357_1345510114343_928274_14556Members() {
                try {
                    _17_0_5_edc0357_1345510114344_743484_14559_exists = new BooleanParameter("_17_0_5_edc0357_1345510114344_743484_14559_exists", this);
                    _17_0_5_edc0357_1345510114876_2649_15257 = new IntegerParameter("_17_0_5_edc0357_1345510114876_2649_15257", this);
                    _17_0_5_edc0357_1345510114343_392589_14555_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114343_392589_14555_endTime", this);
                    _17_0_5_edc0357_1345510114877_268291_15258 = new Parameter<LADWP>("_17_0_5_edc0357_1345510114877_268291_15258", null, null, this);
                    constraint317 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114343_392589_14555_endTime)));
                    _17_0_5_edc0357_1345510114344_743484_14559_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114344_743484_14559_exists, new Expression<Boolean>(true));
                    _17_0_5_edc0357_1345510114876_2649_15257Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114876_2649_15257, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1346101200222_265463_13908, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    effect318Var = actual_load__17_0_5_edc0357_1345510113609_489064_13842;
                    effect318 = new EffectFunction(new FunctionCall((Object) effect318Var, Utils.getMethodForArgTypes("TimeVaryingMap<Integer>", "generated", "setValue", java.lang.Integer.class, java.lang.Object.class), new Object[] { startTime, _17_0_5_edc0357_1345510114876_2649_15257 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114343_928274_14556Collections() {
                parameters.add(_17_0_5_edc0357_1345510114344_743484_14559_exists);
                parameters.add(_17_0_5_edc0357_1345510114876_2649_15257);
                parameters.add(_17_0_5_edc0357_1345510114343_392589_14555_endTime);
                parameters.add(_17_0_5_edc0357_1345510114877_268291_15258);
                constraintExpressions.add(constraint317);
                dependencies.add(_17_0_5_edc0357_1345510114344_743484_14559_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510114876_2649_15257Dependency);
                Set<Effect> effectsForeffect318Var = new HashSet<Effect>();
                effectsForeffect318Var.add(effect318);
                effects.put((Parameter<?>) effect318Var, effectsForeffect318Var);
            }

            public void init_17_0_5_edc0357_1345510114343_928274_14556Elaborations() {
                Expression<?>[] arguments319 = new Expression<?>[1];
                arguments319[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition319 = new Expression<Boolean>(_17_0_5_edc0357_1345510114344_743484_14559_exists);
                elaborationRule319 = addElaborationRule(condition319, _17_0_5_edc0357_1345510113597_14205_13829.this, LADWP._17_0_5_edc0357_1345510113597_14205_13829._17_0_5_edc0357_1345510114344_743484_14559.class, "final_ActivityFinalNode_updateActualLoad", arguments319);
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

            public ElaborationRule elaborationRule320 = null;

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
                Expression<?>[] arguments320 = new Expression<?>[1];
                arguments320[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition320 = new Expression<Boolean>(_17_0_5_edc0357_1345510114343_392589_14555_exists);
                elaborationRule320 = addElaborationRule(condition320, _17_0_5_edc0357_1345510113597_14205_13829.this, LADWP._17_0_5_edc0357_1345510113597_14205_13829._17_0_5_edc0357_1345510114343_392589_14555.class, "rs_ReadSelfAction_updateActualLoad", arguments320);
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

            public ConstraintExpression constraint321 = null;

            public void init_17_0_5_edc0357_1345510114344_743484_14559Members() {
                try {
                    _17_0_5_edc0357_1345510114343_928274_14556_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114343_928274_14556_endTime", this);
                    constraint321 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114343_928274_14556_endTime)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114344_743484_14559Collections() {
                parameters.add(_17_0_5_edc0357_1345510114343_928274_14556_endTime);
                constraintExpressions.add(constraint321);
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

            public Effect effect322 = null;

            public Object effect322Var = null;

            public void init_17_0_5_edc0357_1346101105922_421213_13877Members() {
                try {
                    _17_0_5_edc0357_1346101090161_713198_13875 = new IntegerParameter("_17_0_5_edc0357_1346101090161_713198_13875", this);
                    effect322Var = sig_17_0_5_edc0357_1346101200222_265463_13908;
                    effect322 = new EffectFunction(new FunctionCall((Object) effect322Var, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { _17_0_5_edc0357_1346101090161_713198_13875, startTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346101105922_421213_13877Collections() {
                parameters.add(_17_0_5_edc0357_1346101090161_713198_13875);
                Set<Effect> effectsForeffect322Var = new HashSet<Effect>();
                effectsForeffect322Var.add(effect322);
                effects.put((Parameter<?>) effect322Var, effectsForeffect322Var);
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

        public ElaborationRule elaborationRule323 = null;

        public ElaborationRule elaborationRule324 = null;

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
            Expression<?>[] arguments323 = new Expression<?>[2];
            arguments323[0] = new Expression<Integer>(invoke_time);
            arguments323[1] = new Expression<Integer>(_17_0_5_edc0357_1346101437766_368293_14030);
            Expression<Boolean> condition323 = new Expression<Boolean>(true);
            elaborationRule323 = addElaborationRule(condition323, _17_0_5_edc0357_1345510113598_693480_13830.this, LADWP._17_0_5_edc0357_1345510113598_693480_13830._17_0_5_edc0357_1346101453400_976869_14032.class, "actualGeneration_ActivityParameterNode_updateGeneration", arguments323);
            Expression<?>[] arguments324 = new Expression<?>[1];
            arguments324[0] = new Expression<Integer>(invoke_time);
            Expression<Boolean> condition324 = new Expression<Boolean>(true);
            elaborationRule324 = addElaborationRule(condition324, _17_0_5_edc0357_1345510113598_693480_13830.this, LADWP._17_0_5_edc0357_1345510113598_693480_13830._17_0_5_edc0357_1345510114381_139867_14599.class, "_InitialNode_updateGeneration", arguments324);
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

            public ConstraintExpression constraint325 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114380_134530_14597_existsDependency = null;

            public ElaborationRule elaborationRule326 = null;

            public void init_17_0_5_edc0357_1345510114380_725893_14596Members() {
                try {
                    _17_0_5_edc0357_1345510114380_134530_14597_exists = new BooleanParameter("_17_0_5_edc0357_1345510114380_134530_14597_exists", this);
                    _17_0_5_edc0357_1345510114881_48570_15265 = new Parameter<LADWP>("_17_0_5_edc0357_1345510114881_48570_15265", null, LADWP.this, this);
                    _17_0_5_edc0357_1345510114381_139867_14599_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114381_139867_14599_endTime", this);
                    constraint325 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114381_139867_14599_endTime)));
                    _17_0_5_edc0357_1345510114380_134530_14597_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114380_134530_14597_exists, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1346101473960_270064_14063, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114380_725893_14596Collections() {
                parameters.add(_17_0_5_edc0357_1345510114380_134530_14597_exists);
                parameters.add(_17_0_5_edc0357_1345510114881_48570_15265);
                parameters.add(_17_0_5_edc0357_1345510114381_139867_14599_endTime);
                constraintExpressions.add(constraint325);
                dependencies.add(_17_0_5_edc0357_1345510114380_134530_14597_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510114380_725893_14596Elaborations() {
                Expression<?>[] arguments326 = new Expression<?>[2];
                arguments326[0] = new Expression<Integer>(endTime);
                arguments326[1] = new Expression<LADWP>(_17_0_5_edc0357_1345510114881_48570_15265);
                Expression<Boolean> condition326 = new Expression<Boolean>(_17_0_5_edc0357_1345510114380_134530_14597_exists);
                elaborationRule326 = addElaborationRule(condition326, _17_0_5_edc0357_1345510113598_693480_13830.this, LADWP._17_0_5_edc0357_1345510113598_693480_13830._17_0_5_edc0357_1345510114380_134530_14597.class, "_AddStructuralFeatureValueAction_updateGeneration", arguments326);
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

            public ConstraintExpression constraint327 = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114881_103661_15266Dependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114381_563515_14600_existsDependency = null;

            public Effect effect328 = null;

            public Object effect328Var = null;

            public ElaborationRule elaborationRule329 = null;

            public void init_17_0_5_edc0357_1345510114380_134530_14597Members() {
                try {
                    _17_0_5_edc0357_1345510114881_103661_15266 = new IntegerParameter("_17_0_5_edc0357_1345510114881_103661_15266", this);
                    _17_0_5_edc0357_1345510114380_725893_14596_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114380_725893_14596_endTime", this);
                    _17_0_5_edc0357_1345510114381_563515_14600_exists = new BooleanParameter("_17_0_5_edc0357_1345510114381_563515_14600_exists", this);
                    _17_0_5_edc0357_1345510114882_909563_15267 = new Parameter<LADWP>("_17_0_5_edc0357_1345510114882_909563_15267", null, null, this);
                    constraint327 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114380_725893_14596_endTime)));
                    _17_0_5_edc0357_1345510114881_103661_15266Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114881_103661_15266, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1346101473960_270064_14063, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1345510114381_563515_14600_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114381_563515_14600_exists, new Expression<Boolean>(true));
                    effect328Var = reported_generation__17_0_5_edc0357_1345510113612_851613_13846;
                    effect328 = new EffectFunction(new FunctionCall((Object) effect328Var, Utils.getMethodForArgTypes("TimeVaryingMap<Integer>", "generated", "setValue", java.lang.Integer.class, java.lang.Object.class), new Object[] { startTime, _17_0_5_edc0357_1345510114881_103661_15266 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114380_134530_14597Collections() {
                parameters.add(_17_0_5_edc0357_1345510114881_103661_15266);
                parameters.add(_17_0_5_edc0357_1345510114380_725893_14596_endTime);
                parameters.add(_17_0_5_edc0357_1345510114381_563515_14600_exists);
                parameters.add(_17_0_5_edc0357_1345510114882_909563_15267);
                constraintExpressions.add(constraint327);
                dependencies.add(_17_0_5_edc0357_1345510114881_103661_15266Dependency);
                dependencies.add(_17_0_5_edc0357_1345510114381_563515_14600_existsDependency);
                Set<Effect> effectsForeffect328Var = new HashSet<Effect>();
                effectsForeffect328Var.add(effect328);
                effects.put((Parameter<?>) effect328Var, effectsForeffect328Var);
            }

            public void init_17_0_5_edc0357_1345510114380_134530_14597Elaborations() {
                Expression<?>[] arguments329 = new Expression<?>[1];
                arguments329[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition329 = new Expression<Boolean>(_17_0_5_edc0357_1345510114381_563515_14600_exists);
                elaborationRule329 = addElaborationRule(condition329, _17_0_5_edc0357_1345510113598_693480_13830.this, LADWP._17_0_5_edc0357_1345510113598_693480_13830._17_0_5_edc0357_1345510114381_563515_14600.class, "_ActivityFinalNode_updateGeneration", arguments329);
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

            public ElaborationRule elaborationRule330 = null;

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
                Expression<?>[] arguments330 = new Expression<?>[1];
                arguments330[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition330 = new Expression<Boolean>(_17_0_5_edc0357_1345510114380_725893_14596_exists);
                elaborationRule330 = addElaborationRule(condition330, _17_0_5_edc0357_1345510113598_693480_13830.this, LADWP._17_0_5_edc0357_1345510113598_693480_13830._17_0_5_edc0357_1345510114380_725893_14596.class, "_ReadSelfAction_updateGeneration", arguments330);
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

            public ConstraintExpression constraint331 = null;

            public void init_17_0_5_edc0357_1345510114381_563515_14600Members() {
                try {
                    _17_0_5_edc0357_1345510114380_134530_14597_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114380_134530_14597_endTime", this);
                    constraint331 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114380_134530_14597_endTime)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114381_563515_14600Collections() {
                parameters.add(_17_0_5_edc0357_1345510114380_134530_14597_endTime);
                constraintExpressions.add(constraint331);
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

            public Effect effect332 = null;

            public Object effect332Var = null;

            public void init_17_0_5_edc0357_1346101453400_976869_14032Members() {
                try {
                    _17_0_5_edc0357_1346101437766_368293_14030 = new IntegerParameter("_17_0_5_edc0357_1346101437766_368293_14030", this);
                    effect332Var = sig_17_0_5_edc0357_1346101473960_270064_14063;
                    effect332 = new EffectFunction(new FunctionCall((Object) effect332Var, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { _17_0_5_edc0357_1346101437766_368293_14030, startTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346101453400_976869_14032Collections() {
                parameters.add(_17_0_5_edc0357_1346101437766_368293_14030);
                Set<Effect> effectsForeffect332Var = new HashSet<Effect>();
                effectsForeffect332Var.add(effect332);
                effects.put((Parameter<?>) effect332Var, effectsForeffect332Var);
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

        public Parameter< ObjectFlow<LADWP> > sig_17_0_5_edc0357_1345510114404_445687_14661 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_5_edc0357_1345510114404_840866_14653 = null;

        public Parameter< ObjectFlow<LADWP> > sig_17_0_5_edc0357_1345510114406_582786_14666 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_5_edc0357_1345510114404_967702_14654 = null;

        public IntegerParameter invoke_time = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_5_edc0357_1345510114406_650884_14669 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_5_edc0357_1345510114406_563398_14668 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_5_edc0357_1345510114408_335204_14678 = null;

        public Parameter< ObjectFlow<LADWP> > sig_17_0_5_edc0357_1345510114405_994113_14663 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_5_edc0357_1345510114406_67045_14672 = null;

        public Parameter< ObjectFlow<LADWP> > sig_17_0_5_edc0357_1345510114405_752265_14664 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_5_edc0357_1345510114407_219573_14673 = null;

        public ElaborationRule elaborationRule333 = null;

        public void init_17_0_5_edc0357_1345510113599_525430_13831Members() {
            try {
                sig_17_0_5_edc0357_1345510114404_348430_14652 = new Parameter<ObjectFlow<Integer>>("sig_17_0_5_edc0357_1345510114404_348430_14652", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114404_348430_14652"), this);
                sig_17_0_5_edc0357_1345510114899_683693_15279 = new Parameter<ObjectFlow<Integer>>("sig_17_0_5_edc0357_1345510114899_683693_15279", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114899_683693_15279"), this);
                sig_17_0_5_edc0357_1345510114408_563101_14679 = new Parameter<ObjectFlow<Integer>>("sig_17_0_5_edc0357_1345510114408_563101_14679", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114408_563101_14679"), this);
                sig_17_0_5_edc0357_1345510114897_165815_15276 = new Parameter<ObjectFlow<Integer>>("sig_17_0_5_edc0357_1345510114897_165815_15276", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114897_165815_15276"), this);
                sig_17_0_5_edc0357_1345510114404_34398_14651 = new Parameter<ObjectFlow<Integer>>("sig_17_0_5_edc0357_1345510114404_34398_14651", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114404_34398_14651"), this);
                sig_17_0_5_edc0357_1345510114404_445687_14661 = new Parameter<ObjectFlow<LADWP>>("sig_17_0_5_edc0357_1345510114404_445687_14661", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114404_445687_14661"), this);
                sig_17_0_5_edc0357_1345510114404_840866_14653 = new Parameter<ObjectFlow<Integer>>("sig_17_0_5_edc0357_1345510114404_840866_14653", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114404_840866_14653"), this);
                sig_17_0_5_edc0357_1345510114406_582786_14666 = new Parameter<ObjectFlow<LADWP>>("sig_17_0_5_edc0357_1345510114406_582786_14666", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114406_582786_14666"), this);
                sig_17_0_5_edc0357_1345510114404_967702_14654 = new Parameter<ObjectFlow<Integer>>("sig_17_0_5_edc0357_1345510114404_967702_14654", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114404_967702_14654"), this);
                invoke_time = new IntegerParameter("invoke_time", this);
                sig_17_0_5_edc0357_1345510114406_650884_14669 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_5_edc0357_1345510114406_650884_14669", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114406_650884_14669"), this);
                sig_17_0_5_edc0357_1345510114406_563398_14668 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_5_edc0357_1345510114406_563398_14668", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114406_563398_14668"), this);
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
            parameters.add(sig_17_0_5_edc0357_1345510114404_445687_14661);
            parameters.add(sig_17_0_5_edc0357_1345510114404_840866_14653);
            parameters.add(sig_17_0_5_edc0357_1345510114406_582786_14666);
            parameters.add(sig_17_0_5_edc0357_1345510114404_967702_14654);
            parameters.add(invoke_time);
            parameters.add(sig_17_0_5_edc0357_1345510114406_650884_14669);
            parameters.add(sig_17_0_5_edc0357_1345510114406_563398_14668);
            parameters.add(sig_17_0_5_edc0357_1345510114408_335204_14678);
            parameters.add(sig_17_0_5_edc0357_1345510114405_994113_14663);
            parameters.add(sig_17_0_5_edc0357_1345510114406_67045_14672);
            parameters.add(sig_17_0_5_edc0357_1345510114405_752265_14664);
            parameters.add(sig_17_0_5_edc0357_1345510114407_219573_14673);
        }

        public void init_17_0_5_edc0357_1345510113599_525430_13831Elaborations() {
            Expression<?>[] arguments333 = new Expression<?>[1];
            arguments333[0] = new Expression<Integer>(invoke_time);
            Expression<Boolean> condition333 = new Expression<Boolean>(true);
            elaborationRule333 = addElaborationRule(condition333, _17_0_5_edc0357_1345510113599_525430_13831.this, LADWP._17_0_5_edc0357_1345510113599_525430_13831._17_0_5_edc0357_1345510114402_767242_14639.class, "_InitialNode_monitor_system", arguments333);
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

            public ConstraintExpression constraint334 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114402_986311_14638_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114401_423840_14637_existsDependency = null;

            public ElaborationRule elaborationRule335 = null;

            public ElaborationRule elaborationRule336 = null;

            public void init_17_0_5_edc0357_1345510114400_220423_14629Members() {
                try {
                    _17_0_5_edc0357_1345510114900_877057_15280 = new Parameter<LADWP>("_17_0_5_edc0357_1345510114900_877057_15280", null, LADWP.this, this);
                    _17_0_5_edc0357_1345510114402_986311_14638_exists = new BooleanParameter("_17_0_5_edc0357_1345510114402_986311_14638_exists", this);
                    _17_0_5_edc0357_1348001886585_689962_14324_endTime = new IntegerParameter("_17_0_5_edc0357_1348001886585_689962_14324_endTime", this);
                    _17_0_5_edc0357_1345510114401_423840_14637_exists = new BooleanParameter("_17_0_5_edc0357_1345510114401_423840_14637_exists", this);
                    constraint334 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1348001886585_689962_14324_endTime)));
                    _17_0_5_edc0357_1345510114402_986311_14638_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114402_986311_14638_exists, new Expression<Boolean>(true));
                    _17_0_5_edc0357_1345510114401_423840_14637_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114401_423840_14637_exists, new Functions.And(new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114404_840866_14653, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114404_967702_14654, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) }))));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114400_220423_14629Collections() {
                parameters.add(_17_0_5_edc0357_1345510114900_877057_15280);
                parameters.add(_17_0_5_edc0357_1345510114402_986311_14638_exists);
                parameters.add(_17_0_5_edc0357_1348001886585_689962_14324_endTime);
                parameters.add(_17_0_5_edc0357_1345510114401_423840_14637_exists);
                constraintExpressions.add(constraint334);
                dependencies.add(_17_0_5_edc0357_1345510114402_986311_14638_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510114401_423840_14637_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510114400_220423_14629Elaborations() {
                Expression<?>[] arguments335 = new Expression<?>[1];
                arguments335[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition335 = new Expression<Boolean>(_17_0_5_edc0357_1345510114401_423840_14637_exists);
                elaborationRule335 = addElaborationRule(condition335, _17_0_5_edc0357_1345510113599_525430_13831.this, LADWP._17_0_5_edc0357_1345510113599_525430_13831._17_0_5_edc0357_1345510114401_423840_14637.class, "_CallBehaviorAction_monitor_system", arguments335);
                Expression<?>[] arguments336 = new Expression<?>[2];
                arguments336[0] = new Expression<Integer>(endTime);
                arguments336[1] = new Expression<LADWP>(_17_0_5_edc0357_1345510114900_877057_15280);
                Expression<Boolean> condition336 = new Expression<Boolean>(_17_0_5_edc0357_1345510114402_986311_14638_exists);
                elaborationRule336 = addElaborationRule(condition336, _17_0_5_edc0357_1345510113599_525430_13831.this, LADWP._17_0_5_edc0357_1345510113599_525430_13831._17_0_5_edc0357_1345510114402_986311_14638.class, "readself_fork_ForkNode_monitor_system", arguments336);
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

            public ConstraintExpression constraint337 = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114901_658987_15281Dependency = null;

            public Effect effect338 = null;

            public Object effect338Var = null;

            public void init_17_0_5_edc0357_1345510114400_746991_14630Members() {
                try {
                    _17_0_5_edc0357_1345510114901_658987_15281 = new IntegerParameter("_17_0_5_edc0357_1345510114901_658987_15281", this);
                    _17_0_5_edc0357_1345510114901_629279_15282 = new Parameter<LADWP>("_17_0_5_edc0357_1345510114901_629279_15282", null, null, this);
                    _17_0_5_edc0357_1345510114402_986311_14638_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114402_986311_14638_endTime", this);
                    constraint337 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114402_986311_14638_endTime)));
                    _17_0_5_edc0357_1345510114901_658987_15281Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114901_658987_15281, new Expression(new FunctionCall((Object) (((Parameter<?>) (_17_0_5_edc0357_1345510114901_629279_15282.getMember("actual_load__17_0_5_edc0357_1345510113609_489064_13842"))).getValue()), Utils.getMethodForArgTypes("LADWP._17_0_5_edc0357_1345510113599_525430_13831._17_0_5_edc0357_1345510114400_746991_14630", "generated", "getValue"), new Object[] { new Expression<Integer>(startTime) })));
                    effect338Var = sig_17_0_5_edc0357_1345510114404_840866_14653;
                    effect338 = new EffectFunction(new FunctionCall((Object) effect338Var, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { _17_0_5_edc0357_1345510114901_658987_15281, startTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114400_746991_14630Collections() {
                parameters.add(_17_0_5_edc0357_1345510114901_658987_15281);
                parameters.add(_17_0_5_edc0357_1345510114901_629279_15282);
                parameters.add(_17_0_5_edc0357_1345510114402_986311_14638_endTime);
                constraintExpressions.add(constraint337);
                dependencies.add(_17_0_5_edc0357_1345510114901_658987_15281Dependency);
                Set<Effect> effectsForeffect338Var = new HashSet<Effect>();
                effectsForeffect338Var.add(effect338);
                effects.put((Parameter<?>) effect338Var, effectsForeffect338Var);
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

            public ConstraintExpression constraint339 = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114902_370167_15283Dependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114401_464493_14634_existsDependency = null;

            public ElaborationRule elaborationRule340 = null;

            public void init_17_0_5_edc0357_1345510114400_398544_14631Members() {
                try {
                    _17_0_5_edc0357_1345510114902_370167_15283 = new IntegerParameter("_17_0_5_edc0357_1345510114902_370167_15283", this);
                    _17_0_5_edc0357_1345510114401_464493_14634_exists = new BooleanParameter("_17_0_5_edc0357_1345510114401_464493_14634_exists", this);
                    _17_0_5_edc0357_1345510114903_863541_15284 = new Parameter<LADWP>("_17_0_5_edc0357_1345510114903_863541_15284", null, null, this);
                    _17_0_5_edc0357_1345510114402_986311_14638_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114402_986311_14638_endTime", this);
                    constraint339 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114402_986311_14638_endTime)));
                    _17_0_5_edc0357_1345510114902_370167_15283Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114902_370167_15283, new Expression(new FunctionCall((Object) (((Parameter<?>) (_17_0_5_edc0357_1345510114903_863541_15284.getMember("reported_generation__17_0_5_edc0357_1345510113612_851613_13846"))).getValue()), Utils.getMethodForArgTypes("LADWP._17_0_5_edc0357_1345510113599_525430_13831._17_0_5_edc0357_1345510114400_398544_14631", "generated", "getValue"), new Object[] { new Expression<Integer>(startTime) })));
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
                constraintExpressions.add(constraint339);
                dependencies.add(_17_0_5_edc0357_1345510114902_370167_15283Dependency);
                dependencies.add(_17_0_5_edc0357_1345510114401_464493_14634_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510114400_398544_14631Elaborations() {
                Expression<?>[] arguments340 = new Expression<?>[2];
                arguments340[0] = new Expression<Integer>(endTime);
                arguments340[1] = new Expression<Integer>(_17_0_5_edc0357_1345510114902_370167_15283);
                Expression<Boolean> condition340 = new Expression<Boolean>(_17_0_5_edc0357_1345510114401_464493_14634_exists);
                elaborationRule340 = addElaborationRule(condition340, _17_0_5_edc0357_1345510113599_525430_13831.this, LADWP._17_0_5_edc0357_1345510113599_525430_13831._17_0_5_edc0357_1345510114401_464493_14634.class, "reported_fork_ForkNode_monitor_system", arguments340);
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

            public ConstraintExpression constraint341 = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114903_116782_15285Dependency = null;

            public Effect effect342 = null;

            public Object effect342Var = null;

            public void init_17_0_5_edc0357_1345510114400_400850_14632Members() {
                try {
                    _17_0_5_edc0357_1345510114402_986311_14638_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114402_986311_14638_endTime", this);
                    _17_0_5_edc0357_1345510114903_116782_15285 = new IntegerParameter("_17_0_5_edc0357_1345510114903_116782_15285", this);
                    _17_0_5_edc0357_1345510114904_450251_15286 = new Parameter<LADWP>("_17_0_5_edc0357_1345510114904_450251_15286", null, null, this);
                    constraint341 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114402_986311_14638_endTime)));
                    _17_0_5_edc0357_1345510114903_116782_15285Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114903_116782_15285, new Expression(new FunctionCall((Object) (((Parameter<?>) (_17_0_5_edc0357_1345510114904_450251_15286.getMember("expected_load__17_0_5_edc0357_1345510113611_416109_13845"))).getValue()), Utils.getMethodForArgTypes("LADWP._17_0_5_edc0357_1345510113599_525430_13831._17_0_5_edc0357_1345510114400_400850_14632", "generated", "getValue"), new Object[] { new Expression<Integer>(startTime) })));
                    effect342Var = sig_17_0_5_edc0357_1345510114404_348430_14652;
                    effect342 = new EffectFunction(new FunctionCall((Object) effect342Var, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { _17_0_5_edc0357_1345510114903_116782_15285, startTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114400_400850_14632Collections() {
                parameters.add(_17_0_5_edc0357_1345510114402_986311_14638_endTime);
                parameters.add(_17_0_5_edc0357_1345510114903_116782_15285);
                parameters.add(_17_0_5_edc0357_1345510114904_450251_15286);
                constraintExpressions.add(constraint341);
                dependencies.add(_17_0_5_edc0357_1345510114903_116782_15285Dependency);
                Set<Effect> effectsForeffect342Var = new HashSet<Effect>();
                effectsForeffect342Var.add(effect342);
                effects.put((Parameter<?>) effect342Var, effectsForeffect342Var);
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

            public ConstraintExpression constraint343 = null;

            public void init_17_0_5_edc0357_1345510114400_500051_14633Members() {
                try {
                    _17_0_5_edc0357_1346106020869_685207_15351_endTime = new IntegerParameter("_17_0_5_edc0357_1346106020869_685207_15351_endTime", this);
                    constraint343 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346106020869_685207_15351_endTime)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114400_500051_14633Collections() {
                parameters.add(_17_0_5_edc0357_1346106020869_685207_15351_endTime);
                constraintExpressions.add(constraint343);
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

            public ConstraintExpression constraint344 = null;

            public Effect effect345 = null;

            public Object effect345Var = null;

            public Effect effect346 = null;

            public Object effect346Var = null;

            public void init_17_0_5_edc0357_1345510114401_464493_14634Members() {
                try {
                    _17_0_5_edc0357_1345510114400_398544_14631_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114400_398544_14631_endTime", this);
                    objectToPass = new IntegerParameter("objectToPass", this);
                    constraint344 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114400_398544_14631_endTime)));
                    effect345Var = sig_17_0_5_edc0357_1345510114404_34398_14651;
                    effect345 = new EffectFunction(new FunctionCall((Object) effect345Var, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                    effect346Var = sig_17_0_5_edc0357_1345510114404_967702_14654;
                    effect346 = new EffectFunction(new FunctionCall((Object) effect346Var, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114401_464493_14634Collections() {
                parameters.add(_17_0_5_edc0357_1345510114400_398544_14631_endTime);
                parameters.add(objectToPass);
                constraintExpressions.add(constraint344);
                Set<Effect> effectsForeffect345Var = new HashSet<Effect>();
                effectsForeffect345Var.add(effect345);
                effects.put((Parameter<?>) effect345Var, effectsForeffect345Var);
                Set<Effect> effectsForeffect346Var = new HashSet<Effect>();
                effectsForeffect346Var.add(effect346);
                effects.put((Parameter<?>) effect346Var, effectsForeffect346Var);
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

            public ConstraintExpression constraint347 = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114905_557932_15287Dependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114403_226098_14648_existsDependency = null;

            public Dependency< LADWP > _17_0_5_edc0357_1345510114906_761156_15288Dependency = null;

            public Effect effect348 = null;

            public Object effect348Var = null;

            public ElaborationRule elaborationRule349 = null;

            public void init_17_0_5_edc0357_1345510114401_676911_14635Members() {
                try {
                    _17_0_5_edc0357_1345510114905_557932_15287 = new IntegerParameter("_17_0_5_edc0357_1345510114905_557932_15287", this);
                    _17_0_5_edc0357_1345510114401_569392_14636_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114401_569392_14636_endTime", this);
                    _17_0_5_edc0357_1345510114403_226098_14648_exists = new BooleanParameter("_17_0_5_edc0357_1345510114403_226098_14648_exists", this);
                    _17_0_5_edc0357_1345510114906_761156_15288 = new Parameter<LADWP>("_17_0_5_edc0357_1345510114906_761156_15288", null, null, this);
                    constraint347 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114401_569392_14636_endTime)));
                    _17_0_5_edc0357_1345510114905_557932_15287Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114905_557932_15287, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114408_335204_14678, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1345510114403_226098_14648_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114403_226098_14648_exists, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114408_563101_14679, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })));
                    _17_0_5_edc0357_1345510114906_761156_15288Dependency = new Dependency<LADWP>(_17_0_5_edc0357_1345510114906_761156_15288, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114404_445687_14661, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    effect348Var = expected_margin__17_0_5_edc0357_1345510113614_771928_13849;
                    effect348 = new EffectFunction(new FunctionCall((Object) effect348Var, Utils.getMethodForArgTypes("TimeVaryingMap<Integer>", "generated", "setValue", java.lang.Integer.class, java.lang.Object.class), new Object[] { startTime, _17_0_5_edc0357_1345510114905_557932_15287 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114401_676911_14635Collections() {
                parameters.add(_17_0_5_edc0357_1345510114905_557932_15287);
                parameters.add(_17_0_5_edc0357_1345510114401_569392_14636_endTime);
                parameters.add(_17_0_5_edc0357_1345510114403_226098_14648_exists);
                parameters.add(_17_0_5_edc0357_1345510114906_761156_15288);
                constraintExpressions.add(constraint347);
                dependencies.add(_17_0_5_edc0357_1345510114905_557932_15287Dependency);
                dependencies.add(_17_0_5_edc0357_1345510114403_226098_14648_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510114906_761156_15288Dependency);
                Set<Effect> effectsForeffect348Var = new HashSet<Effect>();
                effectsForeffect348Var.add(effect348);
                effects.put((Parameter<?>) effect348Var, effectsForeffect348Var);
            }

            public void init_17_0_5_edc0357_1345510114401_676911_14635Elaborations() {
                Expression<?>[] arguments349 = new Expression<?>[1];
                arguments349[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition349 = new Expression<Boolean>(_17_0_5_edc0357_1345510114403_226098_14648_exists);
                elaborationRule349 = addElaborationRule(condition349, _17_0_5_edc0357_1345510113599_525430_13831.this, LADWP._17_0_5_edc0357_1345510113599_525430_13831._17_0_5_edc0357_1345510114403_226098_14648.class, "decideDR_DecisionNode_monitor_system", arguments349);
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

            public ConstraintExpression constraint350 = null;

            public Dependency< Integer > expDependency = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114896_642880_15274Dependency = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114906_56003_15289Dependency = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114897_464730_15275Dependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114403_960787_14647_existsDependency = null;

            public Dependency< Integer > diffDependency = null;

            public Dependency< Integer > genDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114401_676911_14635_existsDependency = null;

            public ElaborationRule elaborationRule351 = null;

            public ElaborationRule elaborationRule352 = null;

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
                    constraint350 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114403_590610_14645_endTime)));
                    expDependency = new Dependency<Integer>(exp, new Expression<Integer>(_17_0_5_edc0357_1345510114897_464730_15275));
                    _17_0_5_edc0357_1345510114896_642880_15274Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114896_642880_15274, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114404_34398_14651, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1345510114906_56003_15289Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114906_56003_15289, new Expression<Integer>(diff));
                    _17_0_5_edc0357_1345510114897_464730_15275Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114897_464730_15275, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114404_348430_14652, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1345510114403_960787_14647_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114403_960787_14647_exists, new Expression<Boolean>(true));
                    diffDependency = new Dependency<Integer>(diff, new Functions.Minus(new Expression<Integer>(gen), new Expression<Integer>(exp)));
                    genDependency = new Dependency<Integer>(gen, new Expression<Integer>(_17_0_5_edc0357_1345510114896_642880_15274));
                    _17_0_5_edc0357_1345510114401_676911_14635_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114401_676911_14635_exists, new Functions.And(new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114404_445687_14661, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114408_335204_14678, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) }))));
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
                constraintExpressions.add(constraint350);
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
                Expression<?>[] arguments351 = new Expression<?>[2];
                arguments351[0] = new Expression<Integer>(endTime);
                arguments351[1] = new Expression<Integer>(_17_0_5_edc0357_1345510114906_56003_15289);
                Expression<Boolean> condition351 = new Expression<Boolean>(_17_0_5_edc0357_1345510114403_960787_14647_exists);
                elaborationRule351 = addElaborationRule(condition351, _17_0_5_edc0357_1345510113599_525430_13831.this, LADWP._17_0_5_edc0357_1345510113599_525430_13831._17_0_5_edc0357_1345510114403_960787_14647.class, "exp_margin_fork_ForkNode_monitor_system", arguments351);
                Expression<?>[] arguments352 = new Expression<?>[1];
                arguments352[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition352 = new Expression<Boolean>(_17_0_5_edc0357_1345510114401_676911_14635_exists);
                elaborationRule352 = addElaborationRule(condition352, _17_0_5_edc0357_1345510113599_525430_13831.this, LADWP._17_0_5_edc0357_1345510113599_525430_13831._17_0_5_edc0357_1345510114401_676911_14635.class, "_AddStructuralFeatureValueAction_monitor_system", arguments352);
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

            public ConstraintExpression constraint353 = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114908_64090_15292Dependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114403_773368_14646_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114402_549043_14640_existsDependency = null;

            public Dependency< Integer > sDependency = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114898_711162_15277Dependency = null;

            public Dependency< Integer > genDependency = null;

            public Dependency< Integer > actDependency = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114899_276069_15278Dependency = null;

            public ElaborationRule elaborationRule354 = null;

            public ElaborationRule elaborationRule355 = null;

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
                    constraint353 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114400_220423_14629_endTime)));
                    _17_0_5_edc0357_1345510114908_64090_15292Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114908_64090_15292, new Expression<Integer>(s));
                    _17_0_5_edc0357_1345510114403_773368_14646_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114403_773368_14646_exists, new Expression<Boolean>(true));
                    _17_0_5_edc0357_1345510114402_549043_14640_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114402_549043_14640_exists, new Functions.And(new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114405_994113_14663, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114406_67045_14672, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) }))));
                    sDependency = new Dependency<Integer>(s, new Functions.Minus(new Expression<Integer>(gen), new Expression<Integer>(act)));
                    _17_0_5_edc0357_1345510114898_711162_15277Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114898_711162_15277, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114404_840866_14653, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    genDependency = new Dependency<Integer>(gen, new Expression<Integer>(_17_0_5_edc0357_1345510114899_276069_15278));
                    actDependency = new Dependency<Integer>(act, new Expression<Integer>(_17_0_5_edc0357_1345510114898_711162_15277));
                    _17_0_5_edc0357_1345510114899_276069_15278Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114899_276069_15278, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114404_967702_14654, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
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
                constraintExpressions.add(constraint353);
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
                Expression<?>[] arguments354 = new Expression<?>[1];
                arguments354[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition354 = new Expression<Boolean>(_17_0_5_edc0357_1345510114402_549043_14640_exists);
                elaborationRule354 = addElaborationRule(condition354, _17_0_5_edc0357_1345510113599_525430_13831.this, LADWP._17_0_5_edc0357_1345510113599_525430_13831._17_0_5_edc0357_1345510114402_549043_14640.class, "_AddStructuralFeatureValueAction_monitor_system", arguments354);
                Expression<?>[] arguments355 = new Expression<?>[2];
                arguments355[0] = new Expression<Integer>(endTime);
                arguments355[1] = new Expression<Integer>(_17_0_5_edc0357_1345510114908_64090_15292);
                Expression<Boolean> condition355 = new Expression<Boolean>(_17_0_5_edc0357_1345510114403_773368_14646_exists);
                elaborationRule355 = addElaborationRule(condition355, _17_0_5_edc0357_1345510113599_525430_13831.this, LADWP._17_0_5_edc0357_1345510113599_525430_13831._17_0_5_edc0357_1345510114403_773368_14646.class, "current_margin_fork_ForkNode_monitor_system", arguments355);
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

            public ConstraintExpression constraint356 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114400_746991_14630_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114400_398544_14631_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114400_400850_14632_existsDependency = null;

            public Effect effect357 = null;

            public Object effect357Var = null;

            public Effect effect358 = null;

            public Object effect358Var = null;

            public Effect effect359 = null;

            public Object effect359Var = null;

            public Effect effect360 = null;

            public Object effect360Var = null;

            public ElaborationRule elaborationRule361 = null;

            public ElaborationRule elaborationRule362 = null;

            public ElaborationRule elaborationRule363 = null;

            public void init_17_0_5_edc0357_1345510114402_986311_14638Members() {
                try {
                    _17_0_5_edc0357_1345510114400_746991_14630_exists = new BooleanParameter("_17_0_5_edc0357_1345510114400_746991_14630_exists", this);
                    _17_0_5_edc0357_1345510114400_398544_14631_exists = new BooleanParameter("_17_0_5_edc0357_1345510114400_398544_14631_exists", this);
                    _17_0_5_edc0357_1345510114400_400850_14632_exists = new BooleanParameter("_17_0_5_edc0357_1345510114400_400850_14632_exists", this);
                    _17_0_5_edc0357_1345510114400_220423_14629_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114400_220423_14629_endTime", this);
                    objectToPass = new Parameter<LADWP>("objectToPass", null, null, this);
                    constraint356 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114400_220423_14629_endTime)));
                    _17_0_5_edc0357_1345510114400_746991_14630_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114400_746991_14630_exists, new Expression<Boolean>(true));
                    _17_0_5_edc0357_1345510114400_398544_14631_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114400_398544_14631_exists, new Expression<Boolean>(true));
                    _17_0_5_edc0357_1345510114400_400850_14632_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114400_400850_14632_exists, new Expression<Boolean>(true));
                    effect357Var = sig_17_0_5_edc0357_1345510114404_445687_14661;
                    effect357 = new EffectFunction(new FunctionCall((Object) effect357Var, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                    effect358Var = sig_17_0_5_edc0357_1345510114405_994113_14663;
                    effect358 = new EffectFunction(new FunctionCall((Object) effect358Var, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                    effect359Var = sig_17_0_5_edc0357_1345510114405_752265_14664;
                    effect359 = new EffectFunction(new FunctionCall((Object) effect359Var, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                    effect360Var = sig_17_0_5_edc0357_1345510114406_582786_14666;
                    effect360 = new EffectFunction(new FunctionCall((Object) effect360Var, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
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
                constraintExpressions.add(constraint356);
                dependencies.add(_17_0_5_edc0357_1345510114400_746991_14630_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510114400_398544_14631_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510114400_400850_14632_existsDependency);
                Set<Effect> effectsForeffect357Var = new HashSet<Effect>();
                effectsForeffect357Var.add(effect357);
                effects.put((Parameter<?>) effect357Var, effectsForeffect357Var);
                Set<Effect> effectsForeffect358Var = new HashSet<Effect>();
                effectsForeffect358Var.add(effect358);
                effects.put((Parameter<?>) effect358Var, effectsForeffect358Var);
                Set<Effect> effectsForeffect359Var = new HashSet<Effect>();
                effectsForeffect359Var.add(effect359);
                effects.put((Parameter<?>) effect359Var, effectsForeffect359Var);
                Set<Effect> effectsForeffect360Var = new HashSet<Effect>();
                effectsForeffect360Var.add(effect360);
                effects.put((Parameter<?>) effect360Var, effectsForeffect360Var);
            }

            public void init_17_0_5_edc0357_1345510114402_986311_14638Elaborations() {
                Expression<?>[] arguments361 = new Expression<?>[2];
                arguments361[0] = new Expression<Integer>(endTime);
                arguments361[1] = new Expression<LADWP>(objectToPass);
                Expression<Boolean> condition361 = new Expression<Boolean>(_17_0_5_edc0357_1345510114400_746991_14630_exists);
                elaborationRule361 = addElaborationRule(condition361, _17_0_5_edc0357_1345510113599_525430_13831.this, LADWP._17_0_5_edc0357_1345510113599_525430_13831._17_0_5_edc0357_1345510114400_746991_14630.class, "_ReadStructuralFeatureAction_monitor_system", arguments361);
                Expression<?>[] arguments362 = new Expression<?>[2];
                arguments362[0] = new Expression<Integer>(endTime);
                arguments362[1] = new Expression<LADWP>(objectToPass);
                Expression<Boolean> condition362 = new Expression<Boolean>(_17_0_5_edc0357_1345510114400_400850_14632_exists);
                elaborationRule362 = addElaborationRule(condition362, _17_0_5_edc0357_1345510113599_525430_13831.this, LADWP._17_0_5_edc0357_1345510113599_525430_13831._17_0_5_edc0357_1345510114400_400850_14632.class, "_ReadStructuralFeatureAction_monitor_system", arguments362);
                Expression<?>[] arguments363 = new Expression<?>[2];
                arguments363[0] = new Expression<Integer>(endTime);
                arguments363[1] = new Expression<LADWP>(objectToPass);
                Expression<Boolean> condition363 = new Expression<Boolean>(_17_0_5_edc0357_1345510114400_398544_14631_exists);
                elaborationRule363 = addElaborationRule(condition363, _17_0_5_edc0357_1345510113599_525430_13831.this, LADWP._17_0_5_edc0357_1345510113599_525430_13831._17_0_5_edc0357_1345510114400_398544_14631.class, "_ReadStructuralFeatureAction_monitor_system", arguments363);
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

            public ElaborationRule elaborationRule364 = null;

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
                Expression<?>[] arguments364 = new Expression<?>[1];
                arguments364[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition364 = new Expression<Boolean>(_17_0_5_edc0357_1348001886585_689962_14324_exists);
                elaborationRule364 = addElaborationRule(condition364, _17_0_5_edc0357_1345510113599_525430_13831.this, LADWP._17_0_5_edc0357_1345510113599_525430_13831._17_0_5_edc0357_1348001886585_689962_14324.class, "_ForkNode_monitor_system", arguments364);
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

            public ConstraintExpression constraint365 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114403_590610_14645_existsDependency = null;

            public Dependency< LADWP > _17_0_5_edc0357_1345510114911_249955_15296Dependency = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114910_287897_15295Dependency = null;

            public Effect effect366 = null;

            public Object effect366Var = null;

            public ElaborationRule elaborationRule367 = null;

            public void init_17_0_5_edc0357_1345510114402_549043_14640Members() {
                try {
                    _17_0_5_edc0357_1345510114403_590610_14645_exists = new BooleanParameter("_17_0_5_edc0357_1345510114403_590610_14645_exists", this);
                    _17_0_5_edc0357_1345510114401_423840_14637_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114401_423840_14637_endTime", this);
                    _17_0_5_edc0357_1345510114911_249955_15296 = new Parameter<LADWP>("_17_0_5_edc0357_1345510114911_249955_15296", null, null, this);
                    _17_0_5_edc0357_1345510114910_287897_15295 = new IntegerParameter("_17_0_5_edc0357_1345510114910_287897_15295", this);
                    constraint365 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114401_423840_14637_endTime)));
                    _17_0_5_edc0357_1345510114403_590610_14645_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114403_590610_14645_exists, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114407_219573_14673, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })));
                    _17_0_5_edc0357_1345510114911_249955_15296Dependency = new Dependency<LADWP>(_17_0_5_edc0357_1345510114911_249955_15296, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114405_994113_14663, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1345510114910_287897_15295Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114910_287897_15295, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114406_67045_14672, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    effect366Var = current_margin__17_0_5_edc0357_1345510113615_810190_13850;
                    effect366 = new EffectFunction(new FunctionCall((Object) effect366Var, Utils.getMethodForArgTypes("TimeVaryingMap<Integer>", "generated", "setValue", java.lang.Integer.class, java.lang.Object.class), new Object[] { startTime, _17_0_5_edc0357_1345510114910_287897_15295 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114402_549043_14640Collections() {
                parameters.add(_17_0_5_edc0357_1345510114403_590610_14645_exists);
                parameters.add(_17_0_5_edc0357_1345510114401_423840_14637_endTime);
                parameters.add(_17_0_5_edc0357_1345510114911_249955_15296);
                parameters.add(_17_0_5_edc0357_1345510114910_287897_15295);
                constraintExpressions.add(constraint365);
                dependencies.add(_17_0_5_edc0357_1345510114403_590610_14645_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510114911_249955_15296Dependency);
                dependencies.add(_17_0_5_edc0357_1345510114910_287897_15295Dependency);
                Set<Effect> effectsForeffect366Var = new HashSet<Effect>();
                effectsForeffect366Var.add(effect366);
                effects.put((Parameter<?>) effect366Var, effectsForeffect366Var);
            }

            public void init_17_0_5_edc0357_1345510114402_549043_14640Elaborations() {
                Expression<?>[] arguments367 = new Expression<?>[1];
                arguments367[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition367 = new Expression<Boolean>(_17_0_5_edc0357_1345510114403_590610_14645_exists);
                elaborationRule367 = addElaborationRule(condition367, _17_0_5_edc0357_1345510113599_525430_13831.this, LADWP._17_0_5_edc0357_1345510113599_525430_13831._17_0_5_edc0357_1345510114403_590610_14645.class, "decideShortage_DecisionNode_monitor_system", arguments367);
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

            public ConstraintExpression constraint368 = null;

            public Dependency< LADWP > _17_0_5_edc0357_1345510114912_644333_15298Dependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114912_558392_15297Dependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346106020869_685207_15351_existsDependency = null;

            public Effect effect369 = null;

            public Object effect369Var = null;

            public ElaborationRule elaborationRule370 = null;

            public void init_17_0_5_edc0357_1345510114402_483196_14641Members() {
                try {
                    _17_0_5_edc0357_1345510114912_644333_15298 = new Parameter<LADWP>("_17_0_5_edc0357_1345510114912_644333_15298", null, null, this);
                    _17_0_5_edc0357_1345510114403_226098_14648_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114403_226098_14648_endTime", this);
                    _17_0_5_edc0357_1345510114912_558392_15297 = new BooleanParameter("_17_0_5_edc0357_1345510114912_558392_15297", this);
                    _17_0_5_edc0357_1346106020869_685207_15351_exists = new BooleanParameter("_17_0_5_edc0357_1346106020869_685207_15351_exists", this);
                    constraint368 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114403_226098_14648_endTime)));
                    _17_0_5_edc0357_1345510114912_644333_15298Dependency = new Dependency<LADWP>(_17_0_5_edc0357_1345510114912_644333_15298, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114405_752265_14664, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1345510114912_558392_15297Dependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114912_558392_15297, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114406_563398_14668, Utils.getMethodForArgTypes("ObjectFlow<Boolean>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1346106020869_685207_15351_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346106020869_685207_15351_exists, new Expression<Boolean>(true));
                    effect369Var = demandResponse__17_0_5_edc0357_1345510113615_857599_13851;
                    effect369 = new EffectFunction(new FunctionCall((Object) effect369Var, Utils.getMethodForArgTypes("TimeVaryingMap<Boolean>", "generated", "setValue", java.lang.Integer.class, java.lang.Object.class), new Object[] { startTime, _17_0_5_edc0357_1345510114912_558392_15297 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114402_483196_14641Collections() {
                parameters.add(_17_0_5_edc0357_1345510114912_644333_15298);
                parameters.add(_17_0_5_edc0357_1345510114403_226098_14648_endTime);
                parameters.add(_17_0_5_edc0357_1345510114912_558392_15297);
                parameters.add(_17_0_5_edc0357_1346106020869_685207_15351_exists);
                constraintExpressions.add(constraint368);
                dependencies.add(_17_0_5_edc0357_1345510114912_644333_15298Dependency);
                dependencies.add(_17_0_5_edc0357_1345510114912_558392_15297Dependency);
                dependencies.add(_17_0_5_edc0357_1346106020869_685207_15351_existsDependency);
                Set<Effect> effectsForeffect369Var = new HashSet<Effect>();
                effectsForeffect369Var.add(effect369);
                effects.put((Parameter<?>) effect369Var, effectsForeffect369Var);
            }

            public void init_17_0_5_edc0357_1345510114402_483196_14641Elaborations() {
                Expression<?>[] arguments370 = new Expression<?>[1];
                arguments370[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition370 = new Expression<Boolean>(_17_0_5_edc0357_1346106020869_685207_15351_exists);
                elaborationRule370 = addElaborationRule(condition370, _17_0_5_edc0357_1345510113599_525430_13831.this, LADWP._17_0_5_edc0357_1345510113599_525430_13831._17_0_5_edc0357_1346106020869_685207_15351.class, "finalmerge_MergeNode_monitor_system", arguments370);
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

            public ConstraintExpression constraint371 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114403_20048_14644_existsDependency = null;

            public ElaborationRule elaborationRule372 = null;

            public void init_17_0_5_edc0357_1345510114402_958025_14642Members() {
                try {
                    _17_0_5_edc0357_1345510114403_20048_14644_exists = new BooleanParameter("_17_0_5_edc0357_1345510114403_20048_14644_exists", this);
                    _17_0_5_edc0357_1345510114913_204417_15300 = new BooleanParameter("_17_0_5_edc0357_1345510114913_204417_15300", this);
                    _17_0_5_edc0357_1348001886585_689962_14324_endTime = new IntegerParameter("_17_0_5_edc0357_1348001886585_689962_14324_endTime", this);
                    constraint371 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1348001886585_689962_14324_endTime)));
                    _17_0_5_edc0357_1345510114403_20048_14644_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114403_20048_14644_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114402_958025_14642Collections() {
                parameters.add(_17_0_5_edc0357_1345510114403_20048_14644_exists);
                parameters.add(_17_0_5_edc0357_1345510114913_204417_15300);
                parameters.add(_17_0_5_edc0357_1348001886585_689962_14324_endTime);
                constraintExpressions.add(constraint371);
                dependencies.add(_17_0_5_edc0357_1345510114403_20048_14644_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510114402_958025_14642Elaborations() {
                Expression<?>[] arguments372 = new Expression<?>[2];
                arguments372[0] = new Expression<Integer>(endTime);
                arguments372[1] = new Expression<Boolean>(_17_0_5_edc0357_1345510114913_204417_15300);
                Expression<Boolean> condition372 = new Expression<Boolean>(_17_0_5_edc0357_1345510114403_20048_14644_exists);
                elaborationRule372 = addElaborationRule(condition372, _17_0_5_edc0357_1345510113599_525430_13831.this, LADWP._17_0_5_edc0357_1345510113599_525430_13831._17_0_5_edc0357_1345510114403_20048_14644.class, "val_spec_fork_ForkNode_monitor_system", arguments372);
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

            public ConstraintExpression constraint373 = null;

            public Dependency< LADWP > _17_0_5_edc0357_1345510114915_875209_15302Dependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346106020869_685207_15351_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114914_734899_15301Dependency = null;

            public Effect effect374 = null;

            public Object effect374Var = null;

            public ElaborationRule elaborationRule375 = null;

            public void init_17_0_5_edc0357_1345510114402_392534_14643Members() {
                try {
                    _17_0_5_edc0357_1345510114915_875209_15302 = new Parameter<LADWP>("_17_0_5_edc0357_1345510114915_875209_15302", null, null, this);
                    _17_0_5_edc0357_1345510114403_590610_14645_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114403_590610_14645_endTime", this);
                    _17_0_5_edc0357_1346106020869_685207_15351_exists = new BooleanParameter("_17_0_5_edc0357_1346106020869_685207_15351_exists", this);
                    _17_0_5_edc0357_1345510114914_734899_15301 = new BooleanParameter("_17_0_5_edc0357_1345510114914_734899_15301", this);
                    constraint373 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114403_590610_14645_endTime)));
                    _17_0_5_edc0357_1345510114915_875209_15302Dependency = new Dependency<LADWP>(_17_0_5_edc0357_1345510114915_875209_15302, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114406_582786_14666, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1346106020869_685207_15351_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346106020869_685207_15351_exists, new Expression<Boolean>(true));
                    _17_0_5_edc0357_1345510114914_734899_15301Dependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114914_734899_15301, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114406_650884_14669, Utils.getMethodForArgTypes("ObjectFlow<Boolean>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    effect374Var = shortage__17_0_5_edc0357_1345510113616_127059_13852;
                    effect374 = new EffectFunction(new FunctionCall((Object) effect374Var, Utils.getMethodForArgTypes("TimeVaryingMap<Boolean>", "generated", "setValue", java.lang.Integer.class, java.lang.Object.class), new Object[] { startTime, _17_0_5_edc0357_1345510114914_734899_15301 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114402_392534_14643Collections() {
                parameters.add(_17_0_5_edc0357_1345510114915_875209_15302);
                parameters.add(_17_0_5_edc0357_1345510114403_590610_14645_endTime);
                parameters.add(_17_0_5_edc0357_1346106020869_685207_15351_exists);
                parameters.add(_17_0_5_edc0357_1345510114914_734899_15301);
                constraintExpressions.add(constraint373);
                dependencies.add(_17_0_5_edc0357_1345510114915_875209_15302Dependency);
                dependencies.add(_17_0_5_edc0357_1346106020869_685207_15351_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510114914_734899_15301Dependency);
                Set<Effect> effectsForeffect374Var = new HashSet<Effect>();
                effectsForeffect374Var.add(effect374);
                effects.put((Parameter<?>) effect374Var, effectsForeffect374Var);
            }

            public void init_17_0_5_edc0357_1345510114402_392534_14643Elaborations() {
                Expression<?>[] arguments375 = new Expression<?>[1];
                arguments375[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition375 = new Expression<Boolean>(_17_0_5_edc0357_1346106020869_685207_15351_exists);
                elaborationRule375 = addElaborationRule(condition375, _17_0_5_edc0357_1345510113599_525430_13831.this, LADWP._17_0_5_edc0357_1345510113599_525430_13831._17_0_5_edc0357_1346106020869_685207_15351.class, "finalmerge_MergeNode_monitor_system", arguments375);
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

            public ConstraintExpression constraint376 = null;

            public Effect effect377 = null;

            public Object effect377Var = null;

            public Effect effect378 = null;

            public Object effect378Var = null;

            public void init_17_0_5_edc0357_1345510114403_20048_14644Members() {
                try {
                    objectToPass = new BooleanParameter("objectToPass", this);
                    _17_0_5_edc0357_1345510114402_958025_14642_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114402_958025_14642_endTime", this);
                    constraint376 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114402_958025_14642_endTime)));
                    effect377Var = sig_17_0_5_edc0357_1345510114406_563398_14668;
                    effect377 = new EffectFunction(new FunctionCall((Object) effect377Var, Utils.getMethodForArgTypes("ObjectFlow<Boolean>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                    effect378Var = sig_17_0_5_edc0357_1345510114406_650884_14669;
                    effect378 = new EffectFunction(new FunctionCall((Object) effect378Var, Utils.getMethodForArgTypes("ObjectFlow<Boolean>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114403_20048_14644Collections() {
                parameters.add(objectToPass);
                parameters.add(_17_0_5_edc0357_1345510114402_958025_14642_endTime);
                constraintExpressions.add(constraint376);
                Set<Effect> effectsForeffect377Var = new HashSet<Effect>();
                effectsForeffect377Var.add(effect377);
                effects.put((Parameter<?>) effect377Var, effectsForeffect377Var);
                Set<Effect> effectsForeffect378Var = new HashSet<Effect>();
                effectsForeffect378Var.add(effect378);
                effects.put((Parameter<?>) effect378Var, effectsForeffect378Var);
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

            public ConstraintExpression constraint379 = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114407_219573_14673Dependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114401_569392_14636_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114402_392534_14643_existsDependency = null;

            public ElaborationRule elaborationRule380 = null;

            public ElaborationRule elaborationRule381 = null;

            public void init_17_0_5_edc0357_1345510114403_590610_14645Members() {
                try {
                    _17_0_5_edc0357_1345510114407_219573_14673 = new IntegerParameter("_17_0_5_edc0357_1345510114407_219573_14673", this);
                    _17_0_5_edc0357_1345510114401_569392_14636_exists = new BooleanParameter("_17_0_5_edc0357_1345510114401_569392_14636_exists", this);
                    _17_0_5_edc0357_1345510114402_392534_14643_exists = new BooleanParameter("_17_0_5_edc0357_1345510114402_392534_14643_exists", this);
                    _17_0_5_edc0357_1345510114402_549043_14640_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114402_549043_14640_endTime", this);
                    constraint379 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114402_549043_14640_endTime)));
                    _17_0_5_edc0357_1345510114407_219573_14673Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114407_219573_14673, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114407_219573_14673, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1345510114401_569392_14636_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114401_569392_14636_exists, new Functions.And(new Functions.And(new Functions.GreaterEquals(new Expression<Integer>(_17_0_5_edc0357_1345510114407_219573_14673), new Expression<Integer>(0)), new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114404_34398_14651, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) }))), new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114404_348430_14652, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) }))));
                    _17_0_5_edc0357_1345510114402_392534_14643_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114402_392534_14643_exists, new Functions.And(new Functions.And(new Functions.Less(new Expression<Integer>(_17_0_5_edc0357_1345510114407_219573_14673), new Expression<Integer>(0)), new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114406_582786_14666, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) }))), new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114406_650884_14669, Utils.getMethodForArgTypes("ObjectFlow<Boolean>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) }))));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114403_590610_14645Collections() {
                parameters.add(_17_0_5_edc0357_1345510114407_219573_14673);
                parameters.add(_17_0_5_edc0357_1345510114401_569392_14636_exists);
                parameters.add(_17_0_5_edc0357_1345510114402_392534_14643_exists);
                parameters.add(_17_0_5_edc0357_1345510114402_549043_14640_endTime);
                constraintExpressions.add(constraint379);
                dependencies.add(_17_0_5_edc0357_1345510114407_219573_14673Dependency);
                dependencies.add(_17_0_5_edc0357_1345510114401_569392_14636_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510114402_392534_14643_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510114403_590610_14645Elaborations() {
                Expression<?>[] arguments380 = new Expression<?>[1];
                arguments380[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition380 = new Expression<Boolean>(_17_0_5_edc0357_1345510114401_569392_14636_exists);
                elaborationRule380 = addElaborationRule(condition380, _17_0_5_edc0357_1345510113599_525430_13831.this, LADWP._17_0_5_edc0357_1345510113599_525430_13831._17_0_5_edc0357_1345510114401_569392_14636.class, "_CallBehaviorAction_monitor_system", arguments380);
                Expression<?>[] arguments381 = new Expression<?>[1];
                arguments381[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition381 = new Expression<Boolean>(_17_0_5_edc0357_1345510114402_392534_14643_exists);
                elaborationRule381 = addElaborationRule(condition381, _17_0_5_edc0357_1345510113599_525430_13831.this, LADWP._17_0_5_edc0357_1345510113599_525430_13831._17_0_5_edc0357_1345510114402_392534_14643.class, "_AddStructuralFeatureValueAction_monitor_system", arguments381);
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

            public ConstraintExpression constraint382 = null;

            public Effect effect383 = null;

            public Object effect383Var = null;

            public Effect effect384 = null;

            public Object effect384Var = null;

            public void init_17_0_5_edc0357_1345510114403_773368_14646Members() {
                try {
                    _17_0_5_edc0357_1345510114401_423840_14637_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114401_423840_14637_endTime", this);
                    objectToPass = new IntegerParameter("objectToPass", this);
                    constraint382 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114401_423840_14637_endTime)));
                    effect383Var = sig_17_0_5_edc0357_1345510114406_67045_14672;
                    effect383 = new EffectFunction(new FunctionCall((Object) effect383Var, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                    effect384Var = sig_17_0_5_edc0357_1345510114407_219573_14673;
                    effect384 = new EffectFunction(new FunctionCall((Object) effect384Var, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114403_773368_14646Collections() {
                parameters.add(_17_0_5_edc0357_1345510114401_423840_14637_endTime);
                parameters.add(objectToPass);
                constraintExpressions.add(constraint382);
                Set<Effect> effectsForeffect383Var = new HashSet<Effect>();
                effectsForeffect383Var.add(effect383);
                effects.put((Parameter<?>) effect383Var, effectsForeffect383Var);
                Set<Effect> effectsForeffect384Var = new HashSet<Effect>();
                effectsForeffect384Var.add(effect384);
                effects.put((Parameter<?>) effect384Var, effectsForeffect384Var);
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

            public ConstraintExpression constraint385 = null;

            public Effect effect386 = null;

            public Object effect386Var = null;

            public Effect effect387 = null;

            public Object effect387Var = null;

            public void init_17_0_5_edc0357_1345510114403_960787_14647Members() {
                try {
                    _17_0_5_edc0357_1345510114401_569392_14636_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114401_569392_14636_endTime", this);
                    objectToPass = new IntegerParameter("objectToPass", this);
                    constraint385 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114401_569392_14636_endTime)));
                    effect386Var = sig_17_0_5_edc0357_1345510114408_335204_14678;
                    effect386 = new EffectFunction(new FunctionCall((Object) effect386Var, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                    effect387Var = sig_17_0_5_edc0357_1345510114408_563101_14679;
                    effect387 = new EffectFunction(new FunctionCall((Object) effect387Var, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114403_960787_14647Collections() {
                parameters.add(_17_0_5_edc0357_1345510114401_569392_14636_endTime);
                parameters.add(objectToPass);
                constraintExpressions.add(constraint385);
                Set<Effect> effectsForeffect386Var = new HashSet<Effect>();
                effectsForeffect386Var.add(effect386);
                effects.put((Parameter<?>) effect386Var, effectsForeffect386Var);
                Set<Effect> effectsForeffect387Var = new HashSet<Effect>();
                effectsForeffect387Var.add(effect387);
                effects.put((Parameter<?>) effect387Var, effectsForeffect387Var);
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

            public ConstraintExpression constraint388 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114402_483196_14641_existsDependency = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114408_563101_14679Dependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346106020869_685207_15351_existsDependency = null;

            public ElaborationRule elaborationRule389 = null;

            public ElaborationRule elaborationRule390 = null;

            public void init_17_0_5_edc0357_1345510114403_226098_14648Members() {
                try {
                    _17_0_5_edc0357_1345510114401_676911_14635_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114401_676911_14635_endTime", this);
                    _17_0_5_edc0357_1345510114402_483196_14641_exists = new BooleanParameter("_17_0_5_edc0357_1345510114402_483196_14641_exists", this);
                    _17_0_5_edc0357_1345510114408_563101_14679 = new IntegerParameter("_17_0_5_edc0357_1345510114408_563101_14679", this);
                    _17_0_5_edc0357_1346106020869_685207_15351_exists = new BooleanParameter("_17_0_5_edc0357_1346106020869_685207_15351_exists", this);
                    constraint388 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114401_676911_14635_endTime)));
                    _17_0_5_edc0357_1345510114402_483196_14641_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114402_483196_14641_exists, new Functions.And(new Functions.And(new Functions.Less(new Expression<Integer>(_17_0_5_edc0357_1345510114408_563101_14679), new Expression<Integer>(0)), new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114405_752265_14664, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) }))), new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114406_563398_14668, Utils.getMethodForArgTypes("ObjectFlow<Boolean>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) }))));
                    _17_0_5_edc0357_1345510114408_563101_14679Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114408_563101_14679, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114408_563101_14679, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
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
                constraintExpressions.add(constraint388);
                dependencies.add(_17_0_5_edc0357_1345510114402_483196_14641_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510114408_563101_14679Dependency);
                dependencies.add(_17_0_5_edc0357_1346106020869_685207_15351_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510114403_226098_14648Elaborations() {
                Expression<?>[] arguments389 = new Expression<?>[1];
                arguments389[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition389 = new Expression<Boolean>(_17_0_5_edc0357_1346106020869_685207_15351_exists);
                elaborationRule389 = addElaborationRule(condition389, _17_0_5_edc0357_1345510113599_525430_13831.this, LADWP._17_0_5_edc0357_1345510113599_525430_13831._17_0_5_edc0357_1346106020869_685207_15351.class, "finalmerge_MergeNode_monitor_system", arguments389);
                Expression<?>[] arguments390 = new Expression<?>[1];
                arguments390[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition390 = new Expression<Boolean>(_17_0_5_edc0357_1345510114402_483196_14641_exists);
                elaborationRule390 = addElaborationRule(condition390, _17_0_5_edc0357_1345510113599_525430_13831.this, LADWP._17_0_5_edc0357_1345510113599_525430_13831._17_0_5_edc0357_1345510114402_483196_14641.class, "_AddStructuralFeatureValueAction_monitor_system", arguments390);
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

            public ConstraintExpression constraint391 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114400_500051_14633_existsDependency = null;

            public ElaborationRule elaborationRule392 = null;

            public void init_17_0_5_edc0357_1346106020869_685207_15351Members() {
                try {
                    invoker_endTime = new IntegerParameter("invoker_endTime", this);
                    _17_0_5_edc0357_1345510114400_500051_14633_exists = new BooleanParameter("_17_0_5_edc0357_1345510114400_500051_14633_exists", this);
                    constraint391 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(invoker_endTime)));
                    _17_0_5_edc0357_1345510114400_500051_14633_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114400_500051_14633_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346106020869_685207_15351Collections() {
                parameters.add(invoker_endTime);
                parameters.add(_17_0_5_edc0357_1345510114400_500051_14633_exists);
                constraintExpressions.add(constraint391);
                dependencies.add(_17_0_5_edc0357_1345510114400_500051_14633_existsDependency);
            }

            public void init_17_0_5_edc0357_1346106020869_685207_15351Elaborations() {
                Expression<?>[] arguments392 = new Expression<?>[1];
                arguments392[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition392 = new Expression<Boolean>(_17_0_5_edc0357_1345510114400_500051_14633_exists);
                elaborationRule392 = addElaborationRule(condition392, _17_0_5_edc0357_1345510113599_525430_13831.this, LADWP._17_0_5_edc0357_1345510113599_525430_13831._17_0_5_edc0357_1345510114400_500051_14633.class, "fin_ActivityFinalNode_monitor_system", arguments392);
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

            public ConstraintExpression constraint393 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114400_220423_14629_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114402_958025_14642_existsDependency = null;

            public ElaborationRule elaborationRule394 = null;

            public ElaborationRule elaborationRule395 = null;

            public void init_17_0_5_edc0357_1348001886585_689962_14324Members() {
                try {
                    _17_0_5_edc0357_1345510114400_220423_14629_exists = new BooleanParameter("_17_0_5_edc0357_1345510114400_220423_14629_exists", this);
                    _17_0_5_edc0357_1345510114402_767242_14639_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114402_767242_14639_endTime", this);
                    _17_0_5_edc0357_1345510114402_958025_14642_exists = new BooleanParameter("_17_0_5_edc0357_1345510114402_958025_14642_exists", this);
                    constraint393 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114402_767242_14639_endTime)));
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
                constraintExpressions.add(constraint393);
                dependencies.add(_17_0_5_edc0357_1345510114400_220423_14629_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510114402_958025_14642_existsDependency);
            }

            public void init_17_0_5_edc0357_1348001886585_689962_14324Elaborations() {
                Expression<?>[] arguments394 = new Expression<?>[1];
                arguments394[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition394 = new Expression<Boolean>(_17_0_5_edc0357_1345510114400_220423_14629_exists);
                elaborationRule394 = addElaborationRule(condition394, _17_0_5_edc0357_1345510113599_525430_13831.this, LADWP._17_0_5_edc0357_1345510113599_525430_13831._17_0_5_edc0357_1345510114400_220423_14629.class, "_ReadSelfAction_monitor_system", arguments394);
                Expression<?>[] arguments395 = new Expression<?>[1];
                arguments395[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition395 = new Expression<Boolean>(_17_0_5_edc0357_1345510114402_958025_14642_exists);
                elaborationRule395 = addElaborationRule(condition395, _17_0_5_edc0357_1345510113599_525430_13831.this, LADWP._17_0_5_edc0357_1345510113599_525430_13831._17_0_5_edc0357_1345510114402_958025_14642.class, "_ValueSpecificationAction_monitor_system", arguments395);
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

        public ElaborationRule elaborationRule396 = null;

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
            Expression<?>[] arguments396 = new Expression<?>[1];
            arguments396[0] = new Expression<Integer>(invoke_time);
            Expression<Boolean> condition396 = new Expression<Boolean>(true);
            elaborationRule396 = addElaborationRule(condition396, _17_0_5_edc0357_1345510113600_989123_13832.this, LADWP._17_0_5_edc0357_1345510113600_989123_13832._17_0_5_edc0357_1345510114498_570114_14719.class, "_InitialNode_demand_response", arguments396);
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

            public ConstraintExpression constraint397 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114498_54401_14718_existsDependency = null;

            public ElaborationRule elaborationRule398 = null;

            public void init_17_0_5_edc0357_1345510114498_546705_14715Members() {
                try {
                    _17_0_5_edc0357_1345510114498_54401_14718_exists = new BooleanParameter("_17_0_5_edc0357_1345510114498_54401_14718_exists", this);
                    _17_0_5_edc0357_1345510114923_47069_15342 = new Parameter<LADWP>("_17_0_5_edc0357_1345510114923_47069_15342", null, LADWP.this, this);
                    _17_0_5_edc0357_1345510114498_570114_14719_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114498_570114_14719_endTime", this);
                    constraint397 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114498_570114_14719_endTime)));
                    _17_0_5_edc0357_1345510114498_54401_14718_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114498_54401_14718_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114498_546705_14715Collections() {
                parameters.add(_17_0_5_edc0357_1345510114498_54401_14718_exists);
                parameters.add(_17_0_5_edc0357_1345510114923_47069_15342);
                parameters.add(_17_0_5_edc0357_1345510114498_570114_14719_endTime);
                constraintExpressions.add(constraint397);
                dependencies.add(_17_0_5_edc0357_1345510114498_54401_14718_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510114498_546705_14715Elaborations() {
                Expression<?>[] arguments398 = new Expression<?>[2];
                arguments398[0] = new Expression<Integer>(endTime);
                arguments398[1] = new Expression<LADWP>(_17_0_5_edc0357_1345510114923_47069_15342);
                Expression<Boolean> condition398 = new Expression<Boolean>(_17_0_5_edc0357_1345510114498_54401_14718_exists);
                elaborationRule398 = addElaborationRule(condition398, _17_0_5_edc0357_1345510113600_989123_13832.this, LADWP._17_0_5_edc0357_1345510113600_989123_13832._17_0_5_edc0357_1345510114498_54401_14718.class, "_ForkNode_demand_response", arguments398);
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

            public ConstraintExpression constraint399 = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114924_922147_15343Dependency = null;

            public Effect effect400 = null;

            public Object effect400Var = null;

            public void init_17_0_5_edc0357_1345510114498_752743_14716Members() {
                try {
                    _17_0_5_edc0357_1345510114498_54401_14718_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114498_54401_14718_endTime", this);
                    _17_0_5_edc0357_1345510114924_63685_15344 = new Parameter<LADWP>("_17_0_5_edc0357_1345510114924_63685_15344", null, null, this);
                    _17_0_5_edc0357_1345510114924_922147_15343 = new IntegerParameter("_17_0_5_edc0357_1345510114924_922147_15343", this);
                    constraint399 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114498_54401_14718_endTime)));
                    _17_0_5_edc0357_1345510114924_922147_15343Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114924_922147_15343, new Expression(new FunctionCall((Object) (((Parameter<?>) (_17_0_5_edc0357_1345510114924_63685_15344.getMember("reported_generation__17_0_5_edc0357_1345510113612_851613_13846"))).getValue()), Utils.getMethodForArgTypes("LADWP._17_0_5_edc0357_1345510113600_989123_13832._17_0_5_edc0357_1345510114498_752743_14716", "generated", "getValue"), new Object[] { new Expression<Integer>(startTime) })));
                    effect400Var = sig_17_0_5_edc0357_1345510114499_464852_14726;
                    effect400 = new EffectFunction(new FunctionCall((Object) effect400Var, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { _17_0_5_edc0357_1345510114924_922147_15343, startTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114498_752743_14716Collections() {
                parameters.add(_17_0_5_edc0357_1345510114498_54401_14718_endTime);
                parameters.add(_17_0_5_edc0357_1345510114924_63685_15344);
                parameters.add(_17_0_5_edc0357_1345510114924_922147_15343);
                constraintExpressions.add(constraint399);
                dependencies.add(_17_0_5_edc0357_1345510114924_922147_15343Dependency);
                Set<Effect> effectsForeffect400Var = new HashSet<Effect>();
                effectsForeffect400Var.add(effect400);
                effects.put((Parameter<?>) effect400Var, effectsForeffect400Var);
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

            public ConstraintExpression constraint401 = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114926_775601_15346Dependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114498_243498_14720_existsDependency = null;

            public Effect effect402 = null;

            public Object effect402Var = null;

            public ElaborationRule elaborationRule403 = null;

            public void init_17_0_5_edc0357_1345510114498_717969_14717Members() {
                try {
                    _17_0_5_edc0357_1345510114926_775601_15346 = new IntegerParameter("_17_0_5_edc0357_1345510114926_775601_15346", this);
                    _17_0_5_edc0357_1345510114498_54401_14718_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114498_54401_14718_endTime", this);
                    _17_0_5_edc0357_1345510114498_243498_14720_exists = new BooleanParameter("_17_0_5_edc0357_1345510114498_243498_14720_exists", this);
                    _17_0_5_edc0357_1345510114925_118349_15345 = new Parameter<LADWP>("_17_0_5_edc0357_1345510114925_118349_15345", null, null, this);
                    constraint401 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114498_54401_14718_endTime)));
                    _17_0_5_edc0357_1345510114926_775601_15346Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114926_775601_15346, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114499_464852_14726, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1345510114498_243498_14720_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114498_243498_14720_exists, new Expression<Boolean>(true));
                    effect402Var = (((Parameter<?>) (x.getMember("ss_17_0_5_edc0357_1345510113605_306486_13837_dr_request"))).getValue());
                    effect402 = new EffectFunction(new FunctionCall((Object) effect402Var, Utils.getMethodForArgTypes("LADWP._17_0_5_edc0357_1345510113600_989123_13832._17_0_5_edc0357_1345510114498_717969_14717", "generated", "send"), new Object[] { x.getValue().new Signaldr_request(_17_0_5_edc0357_1345510114926_775601_15346.getValue()), endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114498_717969_14717Collections() {
                parameters.add(_17_0_5_edc0357_1345510114926_775601_15346);
                parameters.add(_17_0_5_edc0357_1345510114498_54401_14718_endTime);
                parameters.add(_17_0_5_edc0357_1345510114498_243498_14720_exists);
                parameters.add(_17_0_5_edc0357_1345510114925_118349_15345);
                constraintExpressions.add(constraint401);
                dependencies.add(_17_0_5_edc0357_1345510114926_775601_15346Dependency);
                dependencies.add(_17_0_5_edc0357_1345510114498_243498_14720_existsDependency);
                Set<Effect> effectsForeffect402Var = new HashSet<Effect>();
                effectsForeffect402Var.add(effect402);
                effects.put((Parameter<?>) effect402Var, effectsForeffect402Var);
            }

            public void init_17_0_5_edc0357_1345510114498_717969_14717Elaborations() {
                Expression<?>[] arguments403 = new Expression<?>[1];
                arguments403[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition403 = new Expression<Boolean>(_17_0_5_edc0357_1345510114498_243498_14720_exists);
                elaborationRule403 = addElaborationRule(condition403, _17_0_5_edc0357_1345510113600_989123_13832.this, LADWP._17_0_5_edc0357_1345510113600_989123_13832._17_0_5_edc0357_1345510114498_243498_14720.class, "_ActivityFinalNode_demand_response", arguments403);
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

            public ConstraintExpression constraint404 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114498_717969_14717_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114498_752743_14716_existsDependency = null;

            public ElaborationRule elaborationRule405 = null;

            public ElaborationRule elaborationRule406 = null;

            public void init_17_0_5_edc0357_1345510114498_54401_14718Members() {
                try {
                    _17_0_5_edc0357_1345510114498_717969_14717_exists = new BooleanParameter("_17_0_5_edc0357_1345510114498_717969_14717_exists", this);
                    _17_0_5_edc0357_1345510114498_546705_14715_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114498_546705_14715_endTime", this);
                    _17_0_5_edc0357_1345510114498_752743_14716_exists = new BooleanParameter("_17_0_5_edc0357_1345510114498_752743_14716_exists", this);
                    objectToPass = new Parameter<LADWP>("objectToPass", null, null, this);
                    constraint404 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114498_546705_14715_endTime)));
                    _17_0_5_edc0357_1345510114498_717969_14717_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114498_717969_14717_exists, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114499_464852_14726, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })));
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
                constraintExpressions.add(constraint404);
                dependencies.add(_17_0_5_edc0357_1345510114498_717969_14717_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510114498_752743_14716_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510114498_54401_14718Elaborations() {
                Expression<?>[] arguments405 = new Expression<?>[2];
                arguments405[0] = new Expression<Integer>(endTime);
                arguments405[1] = new Expression<LADWP>(objectToPass);
                Expression<Boolean> condition405 = new Expression<Boolean>(_17_0_5_edc0357_1345510114498_717969_14717_exists);
                elaborationRule405 = addElaborationRule(condition405, _17_0_5_edc0357_1345510113600_989123_13832.this, LADWP._17_0_5_edc0357_1345510113600_989123_13832._17_0_5_edc0357_1345510114498_717969_14717.class, "_SendSignalAction_demand_response", arguments405);
                Expression<?>[] arguments406 = new Expression<?>[2];
                arguments406[0] = new Expression<Integer>(endTime);
                arguments406[1] = new Expression<LADWP>(objectToPass);
                Expression<Boolean> condition406 = new Expression<Boolean>(_17_0_5_edc0357_1345510114498_752743_14716_exists);
                elaborationRule406 = addElaborationRule(condition406, _17_0_5_edc0357_1345510113600_989123_13832.this, LADWP._17_0_5_edc0357_1345510113600_989123_13832._17_0_5_edc0357_1345510114498_752743_14716.class, "_ReadStructuralFeatureAction_demand_response", arguments406);
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

            public ElaborationRule elaborationRule407 = null;

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
                Expression<?>[] arguments407 = new Expression<?>[1];
                arguments407[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition407 = new Expression<Boolean>(_17_0_5_edc0357_1345510114498_546705_14715_exists);
                elaborationRule407 = addElaborationRule(condition407, _17_0_5_edc0357_1345510113600_989123_13832.this, LADWP._17_0_5_edc0357_1345510113600_989123_13832._17_0_5_edc0357_1345510114498_546705_14715.class, "_ReadSelfAction_demand_response", arguments407);
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

            public ConstraintExpression constraint408 = null;

            public void init_17_0_5_edc0357_1345510114498_243498_14720Members() {
                try {
                    _17_0_5_edc0357_1345510114498_717969_14717_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114498_717969_14717_endTime", this);
                    constraint408 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114498_717969_14717_endTime)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114498_243498_14720Collections() {
                parameters.add(_17_0_5_edc0357_1345510114498_717969_14717_endTime);
                constraintExpressions.add(constraint408);
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

        public ElaborationRule elaborationRule409 = null;

        public ElaborationRule elaborationRule410 = null;

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
            Expression<?>[] arguments409 = new Expression<?>[2];
            arguments409[0] = new Expression<Integer>(invoke_time);
            arguments409[1] = new Expression<Integer>(_17_0_5_edc0357_1348001555541_398388_14280);
            Expression<Boolean> condition409 = new Expression<Boolean>(true);
            elaborationRule409 = addElaborationRule(condition409, _17_0_5_edc0357_1345510113601_377571_13833.this, LADWP._17_0_5_edc0357_1345510113601_377571_13833._17_0_5_edc0357_1348001565573_115349_14282.class, "expectedLoad_ActivityParameterNode_updateExpectedLoad", arguments409);
            Expression<?>[] arguments410 = new Expression<?>[1];
            arguments410[0] = new Expression<Integer>(invoke_time);
            Expression<Boolean> condition410 = new Expression<Boolean>(true);
            elaborationRule410 = addElaborationRule(condition410, _17_0_5_edc0357_1345510113601_377571_13833.this, LADWP._17_0_5_edc0357_1345510113601_377571_13833._17_0_5_edc0357_1345510114536_703738_14767.class, "_InitialNode_updateExpectedLoad", arguments410);
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

            public ConstraintExpression constraint411 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114535_282655_14765_existsDependency = null;

            public ElaborationRule elaborationRule412 = null;

            public void init_17_0_5_edc0357_1345510114535_568767_14764Members() {
                try {
                    _17_0_5_edc0357_1345510114931_675927_15361 = new Parameter<LADWP>("_17_0_5_edc0357_1345510114931_675927_15361", null, LADWP.this, this);
                    _17_0_5_edc0357_1345510114536_703738_14767_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114536_703738_14767_endTime", this);
                    _17_0_5_edc0357_1345510114535_282655_14765_exists = new BooleanParameter("_17_0_5_edc0357_1345510114535_282655_14765_exists", this);
                    constraint411 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114536_703738_14767_endTime)));
                    _17_0_5_edc0357_1345510114535_282655_14765_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114535_282655_14765_exists, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1348001582336_313784_14297, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114535_568767_14764Collections() {
                parameters.add(_17_0_5_edc0357_1345510114931_675927_15361);
                parameters.add(_17_0_5_edc0357_1345510114536_703738_14767_endTime);
                parameters.add(_17_0_5_edc0357_1345510114535_282655_14765_exists);
                constraintExpressions.add(constraint411);
                dependencies.add(_17_0_5_edc0357_1345510114535_282655_14765_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510114535_568767_14764Elaborations() {
                Expression<?>[] arguments412 = new Expression<?>[2];
                arguments412[0] = new Expression<Integer>(endTime);
                arguments412[1] = new Expression<LADWP>(_17_0_5_edc0357_1345510114931_675927_15361);
                Expression<Boolean> condition412 = new Expression<Boolean>(_17_0_5_edc0357_1345510114535_282655_14765_exists);
                elaborationRule412 = addElaborationRule(condition412, _17_0_5_edc0357_1345510113601_377571_13833.this, LADWP._17_0_5_edc0357_1345510113601_377571_13833._17_0_5_edc0357_1345510114535_282655_14765.class, "_AddStructuralFeatureValueAction_updateExpectedLoad", arguments412);
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

            public ConstraintExpression constraint413 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114536_186015_14768_existsDependency = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114932_192686_15362Dependency = null;

            public Effect effect414 = null;

            public Object effect414Var = null;

            public ElaborationRule elaborationRule415 = null;

            public void init_17_0_5_edc0357_1345510114535_282655_14765Members() {
                try {
                    _17_0_5_edc0357_1345510114535_568767_14764_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114535_568767_14764_endTime", this);
                    _17_0_5_edc0357_1345510114536_186015_14768_exists = new BooleanParameter("_17_0_5_edc0357_1345510114536_186015_14768_exists", this);
                    _17_0_5_edc0357_1345510114932_192686_15362 = new IntegerParameter("_17_0_5_edc0357_1345510114932_192686_15362", this);
                    _17_0_5_edc0357_1345510114933_118299_15363 = new Parameter<LADWP>("_17_0_5_edc0357_1345510114933_118299_15363", null, null, this);
                    constraint413 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114535_568767_14764_endTime)));
                    _17_0_5_edc0357_1345510114536_186015_14768_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114536_186015_14768_exists, new Expression<Boolean>(true));
                    _17_0_5_edc0357_1345510114932_192686_15362Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114932_192686_15362, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1348001582336_313784_14297, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    effect414Var = expected_load__17_0_5_edc0357_1345510113611_416109_13845;
                    effect414 = new EffectFunction(new FunctionCall((Object) effect414Var, Utils.getMethodForArgTypes("TimeVaryingMap<Integer>", "generated", "setValue", java.lang.Integer.class, java.lang.Object.class), new Object[] { startTime, _17_0_5_edc0357_1345510114932_192686_15362 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114535_282655_14765Collections() {
                parameters.add(_17_0_5_edc0357_1345510114535_568767_14764_endTime);
                parameters.add(_17_0_5_edc0357_1345510114536_186015_14768_exists);
                parameters.add(_17_0_5_edc0357_1345510114932_192686_15362);
                parameters.add(_17_0_5_edc0357_1345510114933_118299_15363);
                constraintExpressions.add(constraint413);
                dependencies.add(_17_0_5_edc0357_1345510114536_186015_14768_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510114932_192686_15362Dependency);
                Set<Effect> effectsForeffect414Var = new HashSet<Effect>();
                effectsForeffect414Var.add(effect414);
                effects.put((Parameter<?>) effect414Var, effectsForeffect414Var);
            }

            public void init_17_0_5_edc0357_1345510114535_282655_14765Elaborations() {
                Expression<?>[] arguments415 = new Expression<?>[1];
                arguments415[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition415 = new Expression<Boolean>(_17_0_5_edc0357_1345510114536_186015_14768_exists);
                elaborationRule415 = addElaborationRule(condition415, _17_0_5_edc0357_1345510113601_377571_13833.this, LADWP._17_0_5_edc0357_1345510113601_377571_13833._17_0_5_edc0357_1345510114536_186015_14768.class, "_ActivityFinalNode_updateExpectedLoad", arguments415);
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

            public ElaborationRule elaborationRule416 = null;

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
                Expression<?>[] arguments416 = new Expression<?>[1];
                arguments416[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition416 = new Expression<Boolean>(_17_0_5_edc0357_1345510114535_568767_14764_exists);
                elaborationRule416 = addElaborationRule(condition416, _17_0_5_edc0357_1345510113601_377571_13833.this, LADWP._17_0_5_edc0357_1345510113601_377571_13833._17_0_5_edc0357_1345510114535_568767_14764.class, "_ReadSelfAction_updateExpectedLoad", arguments416);
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

            public ConstraintExpression constraint417 = null;

            public void init_17_0_5_edc0357_1345510114536_186015_14768Members() {
                try {
                    _17_0_5_edc0357_1345510114535_282655_14765_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114535_282655_14765_endTime", this);
                    constraint417 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114535_282655_14765_endTime)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114536_186015_14768Collections() {
                parameters.add(_17_0_5_edc0357_1345510114535_282655_14765_endTime);
                constraintExpressions.add(constraint417);
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

            public Effect effect418 = null;

            public Object effect418Var = null;

            public void init_17_0_5_edc0357_1348001565573_115349_14282Members() {
                try {
                    _17_0_5_edc0357_1348001555541_398388_14280 = new IntegerParameter("_17_0_5_edc0357_1348001555541_398388_14280", this);
                    effect418Var = sig_17_0_5_edc0357_1348001582336_313784_14297;
                    effect418 = new EffectFunction(new FunctionCall((Object) effect418Var, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { _17_0_5_edc0357_1348001555541_398388_14280, startTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1348001565573_115349_14282Collections() {
                parameters.add(_17_0_5_edc0357_1348001555541_398388_14280);
                Set<Effect> effectsForeffect418Var = new HashSet<Effect>();
                effectsForeffect418Var.add(effect418);
                effects.put((Parameter<?>) effect418Var, effectsForeffect418Var);
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

        public ElaborationRule elaborationRule419 = null;

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
            Expression<?>[] arguments419 = new Expression<?>[1];
            arguments419[0] = new Expression<Integer>(invoke_time);
            Expression<Boolean> condition419 = new Expression<Boolean>(true);
            elaborationRule419 = addElaborationRule(condition419, _17_0_5_edc0357_1345510113602_28656_13834.this, LADWP._17_0_5_edc0357_1345510113602_28656_13834._17_0_5_edc0357_1345510114622_850107_14812.class, "_InitialNode_generatePowerNow", arguments419);
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

            public ConstraintExpression constraint420 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114622_183257_14810_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114622_749484_14811_existsDependency = null;

            public ElaborationRule elaborationRule421 = null;

            public ElaborationRule elaborationRule422 = null;

            public void init_17_0_5_edc0357_1345510114621_721122_14806Members() {
                try {
                    _17_0_5_edc0357_1345510114622_183257_14810_exists = new BooleanParameter("_17_0_5_edc0357_1345510114622_183257_14810_exists", this);
                    _17_0_5_edc0357_1348001227222_390756_14207_endTime = new IntegerParameter("_17_0_5_edc0357_1348001227222_390756_14207_endTime", this);
                    _17_0_5_edc0357_1345510114943_970062_15373 = new Parameter<LADWP>("_17_0_5_edc0357_1345510114943_970062_15373", null, LADWP.this, this);
                    _17_0_5_edc0357_1345510114622_749484_14811_exists = new BooleanParameter("_17_0_5_edc0357_1345510114622_749484_14811_exists", this);
                    constraint420 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1348001227222_390756_14207_endTime)));
                    _17_0_5_edc0357_1345510114622_183257_14810_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114622_183257_14810_exists, new Functions.And(new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114623_673172_14818, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114623_885329_14820, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) }))));
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
                constraintExpressions.add(constraint420);
                dependencies.add(_17_0_5_edc0357_1345510114622_183257_14810_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510114622_749484_14811_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510114621_721122_14806Elaborations() {
                Expression<?>[] arguments421 = new Expression<?>[1];
                arguments421[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition421 = new Expression<Boolean>(_17_0_5_edc0357_1345510114622_183257_14810_exists);
                elaborationRule421 = addElaborationRule(condition421, _17_0_5_edc0357_1345510113602_28656_13834.this, LADWP._17_0_5_edc0357_1345510113602_28656_13834._17_0_5_edc0357_1345510114622_183257_14810.class, "_SendSignalAction_generatePowerNow", arguments421);
                Expression<?>[] arguments422 = new Expression<?>[2];
                arguments422[0] = new Expression<Integer>(endTime);
                arguments422[1] = new Expression<LADWP>(_17_0_5_edc0357_1345510114943_970062_15373);
                Expression<Boolean> condition422 = new Expression<Boolean>(_17_0_5_edc0357_1345510114622_749484_14811_exists);
                elaborationRule422 = addElaborationRule(condition422, _17_0_5_edc0357_1345510113602_28656_13834.this, LADWP._17_0_5_edc0357_1345510113602_28656_13834._17_0_5_edc0357_1345510114622_749484_14811.class, "_ForkNode_generatePowerNow", arguments422);
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

            public ConstraintExpression constraint423 = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114944_326266_15374Dependency = null;

            public Effect effect424 = null;

            public Object effect424Var = null;

            public void init_17_0_5_edc0357_1345510114621_963079_14807Members() {
                try {
                    _17_0_5_edc0357_1345510114945_289088_15375 = new Parameter<LADWP>("_17_0_5_edc0357_1345510114945_289088_15375", null, null, this);
                    _17_0_5_edc0357_1345510114622_749484_14811_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114622_749484_14811_endTime", this);
                    _17_0_5_edc0357_1345510114944_326266_15374 = new IntegerParameter("_17_0_5_edc0357_1345510114944_326266_15374", this);
                    constraint423 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114622_749484_14811_endTime)));
                    _17_0_5_edc0357_1345510114944_326266_15374Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114944_326266_15374, new Expression(new FunctionCall((Object) (((Parameter<?>) (_17_0_5_edc0357_1345510114945_289088_15375.getMember("reported_generation__17_0_5_edc0357_1345510113612_851613_13846"))).getValue()), Utils.getMethodForArgTypes("LADWP._17_0_5_edc0357_1345510113602_28656_13834._17_0_5_edc0357_1345510114621_963079_14807", "generated", "getValue"), new Object[] { new Expression<Integer>(startTime) })));
                    effect424Var = sig_17_0_5_edc0357_1345510114623_648729_14817;
                    effect424 = new EffectFunction(new FunctionCall((Object) effect424Var, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { _17_0_5_edc0357_1345510114944_326266_15374, startTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114621_963079_14807Collections() {
                parameters.add(_17_0_5_edc0357_1345510114945_289088_15375);
                parameters.add(_17_0_5_edc0357_1345510114622_749484_14811_endTime);
                parameters.add(_17_0_5_edc0357_1345510114944_326266_15374);
                constraintExpressions.add(constraint423);
                dependencies.add(_17_0_5_edc0357_1345510114944_326266_15374Dependency);
                Set<Effect> effectsForeffect424Var = new HashSet<Effect>();
                effectsForeffect424Var.add(effect424);
                effects.put((Parameter<?>) effect424Var, effectsForeffect424Var);
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

            public ConstraintExpression constraint425 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114622_312578_14809_existsDependency = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114945_243329_15376Dependency = null;

            public ElaborationRule elaborationRule426 = null;

            public void init_17_0_5_edc0357_1345510114622_317480_14808Members() {
                try {
                    _17_0_5_edc0357_1345510114946_838121_15377 = new Parameter<LADWP>("_17_0_5_edc0357_1345510114946_838121_15377", null, null, this);
                    _17_0_5_edc0357_1345510114622_749484_14811_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114622_749484_14811_endTime", this);
                    _17_0_5_edc0357_1345510114622_312578_14809_exists = new BooleanParameter("_17_0_5_edc0357_1345510114622_312578_14809_exists", this);
                    _17_0_5_edc0357_1345510114945_243329_15376 = new IntegerParameter("_17_0_5_edc0357_1345510114945_243329_15376", this);
                    constraint425 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114622_749484_14811_endTime)));
                    _17_0_5_edc0357_1345510114622_312578_14809_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114622_312578_14809_exists, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114623_648729_14817, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })));
                    _17_0_5_edc0357_1345510114945_243329_15376Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114945_243329_15376, new Expression(new FunctionCall((Object) (((Parameter<?>) (_17_0_5_edc0357_1345510114946_838121_15377.getMember("current_margin__17_0_5_edc0357_1345510113615_810190_13850"))).getValue()), Utils.getMethodForArgTypes("LADWP._17_0_5_edc0357_1345510113602_28656_13834._17_0_5_edc0357_1345510114622_317480_14808", "generated", "getValue"), new Object[] { new Expression<Integer>(startTime) })));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114622_317480_14808Collections() {
                parameters.add(_17_0_5_edc0357_1345510114946_838121_15377);
                parameters.add(_17_0_5_edc0357_1345510114622_749484_14811_endTime);
                parameters.add(_17_0_5_edc0357_1345510114622_312578_14809_exists);
                parameters.add(_17_0_5_edc0357_1345510114945_243329_15376);
                constraintExpressions.add(constraint425);
                dependencies.add(_17_0_5_edc0357_1345510114622_312578_14809_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510114945_243329_15376Dependency);
            }

            public void init_17_0_5_edc0357_1345510114622_317480_14808Elaborations() {
                Expression<?>[] arguments426 = new Expression<?>[2];
                arguments426[0] = new Expression<Integer>(endTime);
                arguments426[1] = new Expression<Integer>(_17_0_5_edc0357_1345510114945_243329_15376);
                Expression<Boolean> condition426 = new Expression<Boolean>(_17_0_5_edc0357_1345510114622_312578_14809_exists);
                elaborationRule426 = addElaborationRule(condition426, _17_0_5_edc0357_1345510113602_28656_13834.this, LADWP._17_0_5_edc0357_1345510113602_28656_13834._17_0_5_edc0357_1345510114622_312578_14809.class, "_CallBehaviorAction_generatePowerNow", arguments426);
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

            public ConstraintExpression constraint427 = null;

            public Dependency< Integer > new_generationDependency = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114942_15555_15371Dependency = null;

            public Dependency< Integer > currentDependency = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114947_257306_15378Dependency = null;

            public Dependency< Integer > marginDependency = null;

            public Effect effect428 = null;

            public Object effect428Var = null;

            public void init_17_0_5_edc0357_1345510114622_312578_14809Members() {
                try {
                    new_generation = new IntegerParameter("new_generation", this);
                    _17_0_5_edc0357_1345510114622_317480_14808_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114622_317480_14808_endTime", this);
                    _17_0_5_edc0357_1345510114942_15555_15371 = new IntegerParameter("_17_0_5_edc0357_1345510114942_15555_15371", this);
                    current = new IntegerParameter("current", this);
                    margin = new IntegerParameter("margin", this);
                    _17_0_5_edc0357_1345510114947_257306_15378 = new IntegerParameter("_17_0_5_edc0357_1345510114947_257306_15378", this);
                    _17_0_5_edc0357_1345510114941_778134_15370 = new IntegerParameter("_17_0_5_edc0357_1345510114941_778134_15370", this);
                    constraint427 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114622_317480_14808_endTime)));
                    new_generationDependency = new Dependency<Integer>(new_generation, new Functions.Minus(new Expression<Integer>(current), new Expression<Integer>(margin)));
                    _17_0_5_edc0357_1345510114942_15555_15371Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114942_15555_15371, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114623_648729_14817, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    currentDependency = new Dependency<Integer>(current, new Expression<Integer>(_17_0_5_edc0357_1345510114942_15555_15371));
                    _17_0_5_edc0357_1345510114947_257306_15378Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114947_257306_15378, new Expression<Integer>(new_generation));
                    marginDependency = new Dependency<Integer>(margin, new Expression<Integer>(_17_0_5_edc0357_1345510114941_778134_15370));
                    effect428Var = sig_17_0_5_edc0357_1345510114623_673172_14818;
                    effect428 = new EffectFunction(new FunctionCall((Object) effect428Var, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { _17_0_5_edc0357_1345510114947_257306_15378, startTime }));
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
                constraintExpressions.add(constraint427);
                dependencies.add(new_generationDependency);
                dependencies.add(_17_0_5_edc0357_1345510114942_15555_15371Dependency);
                dependencies.add(currentDependency);
                dependencies.add(_17_0_5_edc0357_1345510114947_257306_15378Dependency);
                dependencies.add(marginDependency);
                Set<Effect> effectsForeffect428Var = new HashSet<Effect>();
                effectsForeffect428Var.add(effect428);
                effects.put((Parameter<?>) effect428Var, effectsForeffect428Var);
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

            public ConstraintExpression constraint429 = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114950_379431_15382Dependency = null;

            public Dependency< LADWP > _17_0_5_edc0357_1345510114949_404158_15381Dependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114623_319984_14814_existsDependency = null;

            public Effect effect430 = null;

            public Object effect430Var = null;

            public ElaborationRule elaborationRule431 = null;

            public void init_17_0_5_edc0357_1345510114622_183257_14810Members() {
                try {
                    _17_0_5_edc0357_1345510114950_379431_15382 = new IntegerParameter("_17_0_5_edc0357_1345510114950_379431_15382", this);
                    _17_0_5_edc0357_1345510114949_404158_15381 = new Parameter<LADWP>("_17_0_5_edc0357_1345510114949_404158_15381", null, null, this);
                    _17_0_5_edc0357_1345510114621_721122_14806_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114621_721122_14806_endTime", this);
                    _17_0_5_edc0357_1345510114623_319984_14814_exists = new BooleanParameter("_17_0_5_edc0357_1345510114623_319984_14814_exists", this);
                    constraint429 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114621_721122_14806_endTime)));
                    _17_0_5_edc0357_1345510114950_379431_15382Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114950_379431_15382, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114623_673172_14818, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1345510114949_404158_15381Dependency = new Dependency<LADWP>(_17_0_5_edc0357_1345510114949_404158_15381, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114623_885329_14820, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1345510114623_319984_14814_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114623_319984_14814_exists, new Functions.And(new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114624_220759_14825, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114624_890227_14824, Utils.getMethodForArgTypes("ObjectFlow<Boolean>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) }))));
                    effect430Var = (((Parameter<?>) (x.getMember("ss_17_0_5_edc0357_1345510113607_182740_13840_changeGenerationValue"))).getValue());
                    effect430 = new EffectFunction(new FunctionCall((Object) effect430Var, Utils.getMethodForArgTypes("LADWP._17_0_5_edc0357_1345510113602_28656_13834._17_0_5_edc0357_1345510114622_183257_14810", "generated", "send"), new Object[] { x.getValue().new SignalchangeGenerationValue(_17_0_5_edc0357_1345510114950_379431_15382.getValue()), endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114622_183257_14810Collections() {
                parameters.add(_17_0_5_edc0357_1345510114950_379431_15382);
                parameters.add(_17_0_5_edc0357_1345510114949_404158_15381);
                parameters.add(_17_0_5_edc0357_1345510114621_721122_14806_endTime);
                parameters.add(_17_0_5_edc0357_1345510114623_319984_14814_exists);
                constraintExpressions.add(constraint429);
                dependencies.add(_17_0_5_edc0357_1345510114950_379431_15382Dependency);
                dependencies.add(_17_0_5_edc0357_1345510114949_404158_15381Dependency);
                dependencies.add(_17_0_5_edc0357_1345510114623_319984_14814_existsDependency);
                Set<Effect> effectsForeffect430Var = new HashSet<Effect>();
                effectsForeffect430Var.add(effect430);
                effects.put((Parameter<?>) effect430Var, effectsForeffect430Var);
            }

            public void init_17_0_5_edc0357_1345510114622_183257_14810Elaborations() {
                Expression<?>[] arguments431 = new Expression<?>[1];
                arguments431[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition431 = new Expression<Boolean>(_17_0_5_edc0357_1345510114623_319984_14814_exists);
                elaborationRule431 = addElaborationRule(condition431, _17_0_5_edc0357_1345510113602_28656_13834.this, LADWP._17_0_5_edc0357_1345510113602_28656_13834._17_0_5_edc0357_1345510114623_319984_14814.class, "_AddStructuralFeatureValueAction_generatePowerNow", arguments431);
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

            public ConstraintExpression constraint432 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114622_317480_14808_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114621_963079_14807_existsDependency = null;

            public Effect effect433 = null;

            public Object effect433Var = null;

            public Effect effect434 = null;

            public Object effect434Var = null;

            public ElaborationRule elaborationRule435 = null;

            public ElaborationRule elaborationRule436 = null;

            public void init_17_0_5_edc0357_1345510114622_749484_14811Members() {
                try {
                    _17_0_5_edc0357_1345510114622_317480_14808_exists = new BooleanParameter("_17_0_5_edc0357_1345510114622_317480_14808_exists", this);
                    _17_0_5_edc0357_1345510114621_963079_14807_exists = new BooleanParameter("_17_0_5_edc0357_1345510114621_963079_14807_exists", this);
                    objectToPass = new Parameter<LADWP>("objectToPass", null, null, this);
                    _17_0_5_edc0357_1345510114621_721122_14806_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114621_721122_14806_endTime", this);
                    constraint432 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114621_721122_14806_endTime)));
                    _17_0_5_edc0357_1345510114622_317480_14808_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114622_317480_14808_exists, new Expression<Boolean>(true));
                    _17_0_5_edc0357_1345510114621_963079_14807_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114621_963079_14807_exists, new Expression<Boolean>(true));
                    effect433Var = sig_17_0_5_edc0357_1345510114623_885329_14820;
                    effect433 = new EffectFunction(new FunctionCall((Object) effect433Var, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                    effect434Var = sig_17_0_5_edc0357_1345510114624_220759_14825;
                    effect434 = new EffectFunction(new FunctionCall((Object) effect434Var, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114622_749484_14811Collections() {
                parameters.add(_17_0_5_edc0357_1345510114622_317480_14808_exists);
                parameters.add(_17_0_5_edc0357_1345510114621_963079_14807_exists);
                parameters.add(objectToPass);
                parameters.add(_17_0_5_edc0357_1345510114621_721122_14806_endTime);
                constraintExpressions.add(constraint432);
                dependencies.add(_17_0_5_edc0357_1345510114622_317480_14808_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510114621_963079_14807_existsDependency);
                Set<Effect> effectsForeffect433Var = new HashSet<Effect>();
                effectsForeffect433Var.add(effect433);
                effects.put((Parameter<?>) effect433Var, effectsForeffect433Var);
                Set<Effect> effectsForeffect434Var = new HashSet<Effect>();
                effectsForeffect434Var.add(effect434);
                effects.put((Parameter<?>) effect434Var, effectsForeffect434Var);
            }

            public void init_17_0_5_edc0357_1345510114622_749484_14811Elaborations() {
                Expression<?>[] arguments435 = new Expression<?>[2];
                arguments435[0] = new Expression<Integer>(endTime);
                arguments435[1] = new Expression<LADWP>(objectToPass);
                Expression<Boolean> condition435 = new Expression<Boolean>(_17_0_5_edc0357_1345510114622_317480_14808_exists);
                elaborationRule435 = addElaborationRule(condition435, _17_0_5_edc0357_1345510113602_28656_13834.this, LADWP._17_0_5_edc0357_1345510113602_28656_13834._17_0_5_edc0357_1345510114622_317480_14808.class, "_ReadStructuralFeatureAction_generatePowerNow", arguments435);
                Expression<?>[] arguments436 = new Expression<?>[2];
                arguments436[0] = new Expression<Integer>(endTime);
                arguments436[1] = new Expression<LADWP>(objectToPass);
                Expression<Boolean> condition436 = new Expression<Boolean>(_17_0_5_edc0357_1345510114621_963079_14807_exists);
                elaborationRule436 = addElaborationRule(condition436, _17_0_5_edc0357_1345510113602_28656_13834.this, LADWP._17_0_5_edc0357_1345510113602_28656_13834._17_0_5_edc0357_1345510114621_963079_14807.class, "_ReadStructuralFeatureAction_generatePowerNow", arguments436);
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

            public ElaborationRule elaborationRule437 = null;

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
                Expression<?>[] arguments437 = new Expression<?>[1];
                arguments437[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition437 = new Expression<Boolean>(_17_0_5_edc0357_1348001227222_390756_14207_exists);
                elaborationRule437 = addElaborationRule(condition437, _17_0_5_edc0357_1345510113602_28656_13834.this, LADWP._17_0_5_edc0357_1345510113602_28656_13834._17_0_5_edc0357_1348001227222_390756_14207.class, "_ForkNode_generatePowerNow", arguments437);
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

            public ConstraintExpression constraint438 = null;

            public void init_17_0_5_edc0357_1345510114622_20234_14813Members() {
                try {
                    _17_0_5_edc0357_1345510114623_319984_14814_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114623_319984_14814_endTime", this);
                    constraint438 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114623_319984_14814_endTime)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114622_20234_14813Collections() {
                parameters.add(_17_0_5_edc0357_1345510114623_319984_14814_endTime);
                constraintExpressions.add(constraint438);
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

            public ConstraintExpression constraint439 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114950_81581_15383Dependency = null;

            public Dependency< LADWP > _17_0_5_edc0357_1345510114951_495912_15384Dependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114622_20234_14813_existsDependency = null;

            public Effect effect440 = null;

            public Object effect440Var = null;

            public ElaborationRule elaborationRule441 = null;

            public void init_17_0_5_edc0357_1345510114623_319984_14814Members() {
                try {
                    _17_0_5_edc0357_1345510114950_81581_15383 = new BooleanParameter("_17_0_5_edc0357_1345510114950_81581_15383", this);
                    _17_0_5_edc0357_1345510114951_495912_15384 = new Parameter<LADWP>("_17_0_5_edc0357_1345510114951_495912_15384", null, null, this);
                    _17_0_5_edc0357_1345510114622_183257_14810_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114622_183257_14810_endTime", this);
                    _17_0_5_edc0357_1345510114622_20234_14813_exists = new BooleanParameter("_17_0_5_edc0357_1345510114622_20234_14813_exists", this);
                    constraint439 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114622_183257_14810_endTime)));
                    _17_0_5_edc0357_1345510114950_81581_15383Dependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114950_81581_15383, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114624_890227_14824, Utils.getMethodForArgTypes("ObjectFlow<Boolean>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1345510114951_495912_15384Dependency = new Dependency<LADWP>(_17_0_5_edc0357_1345510114951_495912_15384, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114624_220759_14825, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1345510114622_20234_14813_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114622_20234_14813_exists, new Expression<Boolean>(true));
                    effect440Var = shortage__17_0_5_edc0357_1345510113616_127059_13852;
                    effect440 = new EffectFunction(new FunctionCall((Object) effect440Var, Utils.getMethodForArgTypes("TimeVaryingMap<Boolean>", "generated", "setValue", java.lang.Integer.class, java.lang.Object.class), new Object[] { startTime, _17_0_5_edc0357_1345510114950_81581_15383 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114623_319984_14814Collections() {
                parameters.add(_17_0_5_edc0357_1345510114950_81581_15383);
                parameters.add(_17_0_5_edc0357_1345510114951_495912_15384);
                parameters.add(_17_0_5_edc0357_1345510114622_183257_14810_endTime);
                parameters.add(_17_0_5_edc0357_1345510114622_20234_14813_exists);
                constraintExpressions.add(constraint439);
                dependencies.add(_17_0_5_edc0357_1345510114950_81581_15383Dependency);
                dependencies.add(_17_0_5_edc0357_1345510114951_495912_15384Dependency);
                dependencies.add(_17_0_5_edc0357_1345510114622_20234_14813_existsDependency);
                Set<Effect> effectsForeffect440Var = new HashSet<Effect>();
                effectsForeffect440Var.add(effect440);
                effects.put((Parameter<?>) effect440Var, effectsForeffect440Var);
            }

            public void init_17_0_5_edc0357_1345510114623_319984_14814Elaborations() {
                Expression<?>[] arguments441 = new Expression<?>[1];
                arguments441[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition441 = new Expression<Boolean>(_17_0_5_edc0357_1345510114622_20234_14813_exists);
                elaborationRule441 = addElaborationRule(condition441, _17_0_5_edc0357_1345510113602_28656_13834.this, LADWP._17_0_5_edc0357_1345510113602_28656_13834._17_0_5_edc0357_1345510114622_20234_14813.class, "_ActivityFinalNode_generatePowerNow", arguments441);
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

            public ConstraintExpression constraint442 = null;

            public Effect effect443 = null;

            public Object effect443Var = null;

            public void init_17_0_5_edc0357_1345510114623_127267_14815Members() {
                try {
                    _17_0_5_edc0357_1348001227222_390756_14207_endTime = new IntegerParameter("_17_0_5_edc0357_1348001227222_390756_14207_endTime", this);
                    _17_0_5_edc0357_1345510114952_208510_15386 = new BooleanParameter("_17_0_5_edc0357_1345510114952_208510_15386", this);
                    constraint442 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1348001227222_390756_14207_endTime)));
                    effect443Var = sig_17_0_5_edc0357_1345510114624_890227_14824;
                    effect443 = new EffectFunction(new FunctionCall((Object) effect443Var, Utils.getMethodForArgTypes("ObjectFlow<Boolean>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { _17_0_5_edc0357_1345510114952_208510_15386, startTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114623_127267_14815Collections() {
                parameters.add(_17_0_5_edc0357_1348001227222_390756_14207_endTime);
                parameters.add(_17_0_5_edc0357_1345510114952_208510_15386);
                constraintExpressions.add(constraint442);
                Set<Effect> effectsForeffect443Var = new HashSet<Effect>();
                effectsForeffect443Var.add(effect443);
                effects.put((Parameter<?>) effect443Var, effectsForeffect443Var);
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

            public ConstraintExpression constraint444 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114621_721122_14806_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114623_127267_14815_existsDependency = null;

            public ElaborationRule elaborationRule445 = null;

            public ElaborationRule elaborationRule446 = null;

            public void init_17_0_5_edc0357_1348001227222_390756_14207Members() {
                try {
                    _17_0_5_edc0357_1345510114622_850107_14812_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114622_850107_14812_endTime", this);
                    _17_0_5_edc0357_1345510114621_721122_14806_exists = new BooleanParameter("_17_0_5_edc0357_1345510114621_721122_14806_exists", this);
                    _17_0_5_edc0357_1345510114623_127267_14815_exists = new BooleanParameter("_17_0_5_edc0357_1345510114623_127267_14815_exists", this);
                    constraint444 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114622_850107_14812_endTime)));
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
                constraintExpressions.add(constraint444);
                dependencies.add(_17_0_5_edc0357_1345510114621_721122_14806_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510114623_127267_14815_existsDependency);
            }

            public void init_17_0_5_edc0357_1348001227222_390756_14207Elaborations() {
                Expression<?>[] arguments445 = new Expression<?>[1];
                arguments445[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition445 = new Expression<Boolean>(_17_0_5_edc0357_1345510114623_127267_14815_exists);
                elaborationRule445 = addElaborationRule(condition445, _17_0_5_edc0357_1345510113602_28656_13834.this, LADWP._17_0_5_edc0357_1345510113602_28656_13834._17_0_5_edc0357_1345510114623_127267_14815.class, "_ValueSpecificationAction_generatePowerNow", arguments445);
                Expression<?>[] arguments446 = new Expression<?>[1];
                arguments446[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition446 = new Expression<Boolean>(_17_0_5_edc0357_1345510114621_721122_14806_exists);
                elaborationRule446 = addElaborationRule(condition446, _17_0_5_edc0357_1345510113602_28656_13834.this, LADWP._17_0_5_edc0357_1345510113602_28656_13834._17_0_5_edc0357_1345510114621_721122_14806.class, "_ReadSelfAction_generatePowerNow", arguments446);
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

        public ElaborationRule elaborationRule447 = null;

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
            Expression<?>[] arguments447 = new Expression<?>[1];
            arguments447[0] = new Expression<Integer>(invoke_time);
            Expression<Boolean> condition447 = new Expression<Boolean>(true);
            elaborationRule447 = addElaborationRule(condition447, _17_0_5_edc0357_1345510113602_419995_13835.this, LADWP._17_0_5_edc0357_1345510113602_419995_13835._17_0_5_edc0357_1345510114692_731793_14868.class, "_InitialNode_generatePowerLater", arguments447);
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

            public ConstraintExpression constraint448 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114692_336380_14867_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114692_827724_14866_existsDependency = null;

            public ElaborationRule elaborationRule449 = null;

            public ElaborationRule elaborationRule450 = null;

            public void init_17_0_5_edc0357_1345510114691_687782_14862Members() {
                try {
                    _17_0_5_edc0357_1345510114692_336380_14867_exists = new BooleanParameter("_17_0_5_edc0357_1345510114692_336380_14867_exists", this);
                    _17_0_5_edc0357_1345510114692_731793_14868_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114692_731793_14868_endTime", this);
                    _17_0_5_edc0357_1345510114692_827724_14866_exists = new BooleanParameter("_17_0_5_edc0357_1345510114692_827724_14866_exists", this);
                    _17_0_5_edc0357_1345510114961_887718_15404 = new Parameter<LADWP>("_17_0_5_edc0357_1345510114961_887718_15404", null, LADWP.this, this);
                    constraint448 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114692_731793_14868_endTime)));
                    _17_0_5_edc0357_1345510114692_336380_14867_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114692_336380_14867_exists, new Expression<Boolean>(true));
                    _17_0_5_edc0357_1345510114692_827724_14866_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114692_827724_14866_exists, new Functions.And(new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114692_988619_14872, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114692_891792_14873, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) }))));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114691_687782_14862Collections() {
                parameters.add(_17_0_5_edc0357_1345510114692_336380_14867_exists);
                parameters.add(_17_0_5_edc0357_1345510114692_731793_14868_endTime);
                parameters.add(_17_0_5_edc0357_1345510114692_827724_14866_exists);
                parameters.add(_17_0_5_edc0357_1345510114961_887718_15404);
                constraintExpressions.add(constraint448);
                dependencies.add(_17_0_5_edc0357_1345510114692_336380_14867_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510114692_827724_14866_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510114691_687782_14862Elaborations() {
                Expression<?>[] arguments449 = new Expression<?>[1];
                arguments449[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition449 = new Expression<Boolean>(_17_0_5_edc0357_1345510114692_827724_14866_exists);
                elaborationRule449 = addElaborationRule(condition449, _17_0_5_edc0357_1345510113602_419995_13835.this, LADWP._17_0_5_edc0357_1345510113602_419995_13835._17_0_5_edc0357_1345510114692_827724_14866.class, "_SendSignalAction_generatePowerLater", arguments449);
                Expression<?>[] arguments450 = new Expression<?>[2];
                arguments450[0] = new Expression<Integer>(endTime);
                arguments450[1] = new Expression<LADWP>(_17_0_5_edc0357_1345510114961_887718_15404);
                Expression<Boolean> condition450 = new Expression<Boolean>(_17_0_5_edc0357_1345510114692_336380_14867_exists);
                elaborationRule450 = addElaborationRule(condition450, _17_0_5_edc0357_1345510113602_419995_13835.this, LADWP._17_0_5_edc0357_1345510113602_419995_13835._17_0_5_edc0357_1345510114692_336380_14867.class, "_ForkNode_generatePowerLater", arguments450);
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

            public ConstraintExpression constraint451 = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114962_204235_15405Dependency = null;

            public Effect effect452 = null;

            public Object effect452Var = null;

            public void init_17_0_5_edc0357_1345510114691_619007_14863Members() {
                try {
                    _17_0_5_edc0357_1345510114963_707579_15406 = new Parameter<LADWP>("_17_0_5_edc0357_1345510114963_707579_15406", null, null, this);
                    _17_0_5_edc0357_1345510114962_204235_15405 = new IntegerParameter("_17_0_5_edc0357_1345510114962_204235_15405", this);
                    _17_0_5_edc0357_1345510114692_336380_14867_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114692_336380_14867_endTime", this);
                    constraint451 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114692_336380_14867_endTime)));
                    _17_0_5_edc0357_1345510114962_204235_15405Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114962_204235_15405, new Expression(new FunctionCall((Object) (((Parameter<?>) (_17_0_5_edc0357_1345510114963_707579_15406.getMember("reported_generation__17_0_5_edc0357_1345510113612_851613_13846"))).getValue()), Utils.getMethodForArgTypes("LADWP._17_0_5_edc0357_1345510113602_419995_13835._17_0_5_edc0357_1345510114691_619007_14863", "generated", "getValue"), new Object[] { new Expression<Integer>(startTime) })));
                    effect452Var = sig_17_0_5_edc0357_1345510114692_973151_14871;
                    effect452 = new EffectFunction(new FunctionCall((Object) effect452Var, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { _17_0_5_edc0357_1345510114962_204235_15405, startTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114691_619007_14863Collections() {
                parameters.add(_17_0_5_edc0357_1345510114963_707579_15406);
                parameters.add(_17_0_5_edc0357_1345510114962_204235_15405);
                parameters.add(_17_0_5_edc0357_1345510114692_336380_14867_endTime);
                constraintExpressions.add(constraint451);
                dependencies.add(_17_0_5_edc0357_1345510114962_204235_15405Dependency);
                Set<Effect> effectsForeffect452Var = new HashSet<Effect>();
                effectsForeffect452Var.add(effect452);
                effects.put((Parameter<?>) effect452Var, effectsForeffect452Var);
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

            public ConstraintExpression constraint453 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114691_864657_14865_existsDependency = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114964_115792_15407Dependency = null;

            public ElaborationRule elaborationRule454 = null;

            public void init_17_0_5_edc0357_1345510114691_435966_14864Members() {
                try {
                    _17_0_5_edc0357_1345510114691_864657_14865_exists = new BooleanParameter("_17_0_5_edc0357_1345510114691_864657_14865_exists", this);
                    _17_0_5_edc0357_1345510114964_600674_15408 = new Parameter<LADWP>("_17_0_5_edc0357_1345510114964_600674_15408", null, null, this);
                    _17_0_5_edc0357_1345510114964_115792_15407 = new IntegerParameter("_17_0_5_edc0357_1345510114964_115792_15407", this);
                    _17_0_5_edc0357_1345510114692_336380_14867_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114692_336380_14867_endTime", this);
                    constraint453 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114692_336380_14867_endTime)));
                    _17_0_5_edc0357_1345510114691_864657_14865_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114691_864657_14865_exists, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114692_973151_14871, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })));
                    _17_0_5_edc0357_1345510114964_115792_15407Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114964_115792_15407, new Expression(new FunctionCall((Object) (((Parameter<?>) (_17_0_5_edc0357_1345510114964_600674_15408.getMember("expected_margin__17_0_5_edc0357_1345510113614_771928_13849"))).getValue()), Utils.getMethodForArgTypes("LADWP._17_0_5_edc0357_1345510113602_419995_13835._17_0_5_edc0357_1345510114691_435966_14864", "generated", "getValue"), new Object[] { new Expression<Integer>(startTime) })));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114691_435966_14864Collections() {
                parameters.add(_17_0_5_edc0357_1345510114691_864657_14865_exists);
                parameters.add(_17_0_5_edc0357_1345510114964_600674_15408);
                parameters.add(_17_0_5_edc0357_1345510114964_115792_15407);
                parameters.add(_17_0_5_edc0357_1345510114692_336380_14867_endTime);
                constraintExpressions.add(constraint453);
                dependencies.add(_17_0_5_edc0357_1345510114691_864657_14865_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510114964_115792_15407Dependency);
            }

            public void init_17_0_5_edc0357_1345510114691_435966_14864Elaborations() {
                Expression<?>[] arguments454 = new Expression<?>[2];
                arguments454[0] = new Expression<Integer>(endTime);
                arguments454[1] = new Expression<Integer>(_17_0_5_edc0357_1345510114964_115792_15407);
                Expression<Boolean> condition454 = new Expression<Boolean>(_17_0_5_edc0357_1345510114691_864657_14865_exists);
                elaborationRule454 = addElaborationRule(condition454, _17_0_5_edc0357_1345510113602_419995_13835.this, LADWP._17_0_5_edc0357_1345510113602_419995_13835._17_0_5_edc0357_1345510114691_864657_14865.class, "_CallBehaviorAction_generatePowerLater", arguments454);
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

            public ConstraintExpression constraint455 = null;

            public Dependency< Integer > new_generationDependency = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114965_375269_15409Dependency = null;

            public Dependency< Integer > currentDependency = null;

            public Dependency< Integer > marginDependency = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114960_284722_15402Dependency = null;

            public Effect effect456 = null;

            public Object effect456Var = null;

            public void init_17_0_5_edc0357_1345510114691_864657_14865Members() {
                try {
                    new_generation = new IntegerParameter("new_generation", this);
                    current = new IntegerParameter("current", this);
                    _17_0_5_edc0357_1345510114691_435966_14864_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114691_435966_14864_endTime", this);
                    _17_0_5_edc0357_1345510114965_375269_15409 = new IntegerParameter("_17_0_5_edc0357_1345510114965_375269_15409", this);
                    margin = new IntegerParameter("margin", this);
                    _17_0_5_edc0357_1345510114960_284722_15402 = new IntegerParameter("_17_0_5_edc0357_1345510114960_284722_15402", this);
                    _17_0_5_edc0357_1345510114959_575761_15401 = new IntegerParameter("_17_0_5_edc0357_1345510114959_575761_15401", this);
                    constraint455 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114691_435966_14864_endTime)));
                    new_generationDependency = new Dependency<Integer>(new_generation, new Functions.Minus(new Expression<Integer>(current), new Expression<Integer>(margin)));
                    _17_0_5_edc0357_1345510114965_375269_15409Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114965_375269_15409, new Expression<Integer>(new_generation));
                    currentDependency = new Dependency<Integer>(current, new Expression<Integer>(_17_0_5_edc0357_1345510114960_284722_15402));
                    marginDependency = new Dependency<Integer>(margin, new Expression<Integer>(_17_0_5_edc0357_1345510114959_575761_15401));
                    _17_0_5_edc0357_1345510114960_284722_15402Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114960_284722_15402, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114692_973151_14871, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    effect456Var = sig_17_0_5_edc0357_1345510114692_988619_14872;
                    effect456 = new EffectFunction(new FunctionCall((Object) effect456Var, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { _17_0_5_edc0357_1345510114965_375269_15409, startTime }));
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
                constraintExpressions.add(constraint455);
                dependencies.add(new_generationDependency);
                dependencies.add(_17_0_5_edc0357_1345510114965_375269_15409Dependency);
                dependencies.add(currentDependency);
                dependencies.add(marginDependency);
                dependencies.add(_17_0_5_edc0357_1345510114960_284722_15402Dependency);
                Set<Effect> effectsForeffect456Var = new HashSet<Effect>();
                effectsForeffect456Var.add(effect456);
                effects.put((Parameter<?>) effect456Var, effectsForeffect456Var);
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

            public ConstraintExpression constraint457 = null;

            public Dependency< LADWP > _17_0_5_edc0357_1345510114967_325736_15412Dependency = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114968_74650_15413Dependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114692_509502_14869_existsDependency = null;

            public Effect effect458 = null;

            public Object effect458Var = null;

            public ElaborationRule elaborationRule459 = null;

            public void init_17_0_5_edc0357_1345510114692_827724_14866Members() {
                try {
                    _17_0_5_edc0357_1345510114691_687782_14862_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114691_687782_14862_endTime", this);
                    _17_0_5_edc0357_1345510114967_325736_15412 = new Parameter<LADWP>("_17_0_5_edc0357_1345510114967_325736_15412", null, null, this);
                    _17_0_5_edc0357_1345510114968_74650_15413 = new IntegerParameter("_17_0_5_edc0357_1345510114968_74650_15413", this);
                    _17_0_5_edc0357_1345510114692_509502_14869_exists = new BooleanParameter("_17_0_5_edc0357_1345510114692_509502_14869_exists", this);
                    constraint457 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114691_687782_14862_endTime)));
                    _17_0_5_edc0357_1345510114967_325736_15412Dependency = new Dependency<LADWP>(_17_0_5_edc0357_1345510114967_325736_15412, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114692_891792_14873, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1345510114968_74650_15413Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114968_74650_15413, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114692_988619_14872, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1345510114692_509502_14869_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114692_509502_14869_exists, new Expression<Boolean>(true));
                    effect458Var = (((Parameter<?>) (x.getMember("ss_17_0_5_edc0357_1345510113607_182740_13840_changeGenerationValue"))).getValue());
                    effect458 = new EffectFunction(new FunctionCall((Object) effect458Var, Utils.getMethodForArgTypes("LADWP._17_0_5_edc0357_1345510113602_419995_13835._17_0_5_edc0357_1345510114692_827724_14866", "generated", "send"), new Object[] { x.getValue().new SignalchangeGenerationValue(_17_0_5_edc0357_1345510114968_74650_15413.getValue()), endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114692_827724_14866Collections() {
                parameters.add(_17_0_5_edc0357_1345510114691_687782_14862_endTime);
                parameters.add(_17_0_5_edc0357_1345510114967_325736_15412);
                parameters.add(_17_0_5_edc0357_1345510114968_74650_15413);
                parameters.add(_17_0_5_edc0357_1345510114692_509502_14869_exists);
                constraintExpressions.add(constraint457);
                dependencies.add(_17_0_5_edc0357_1345510114967_325736_15412Dependency);
                dependencies.add(_17_0_5_edc0357_1345510114968_74650_15413Dependency);
                dependencies.add(_17_0_5_edc0357_1345510114692_509502_14869_existsDependency);
                Set<Effect> effectsForeffect458Var = new HashSet<Effect>();
                effectsForeffect458Var.add(effect458);
                effects.put((Parameter<?>) effect458Var, effectsForeffect458Var);
            }

            public void init_17_0_5_edc0357_1345510114692_827724_14866Elaborations() {
                Expression<?>[] arguments459 = new Expression<?>[1];
                arguments459[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition459 = new Expression<Boolean>(_17_0_5_edc0357_1345510114692_509502_14869_exists);
                elaborationRule459 = addElaborationRule(condition459, _17_0_5_edc0357_1345510113602_419995_13835.this, LADWP._17_0_5_edc0357_1345510113602_419995_13835._17_0_5_edc0357_1345510114692_509502_14869.class, "_ActivityFinalNode_generatePowerLater", arguments459);
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

            public ConstraintExpression constraint460 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114691_435966_14864_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114691_619007_14863_existsDependency = null;

            public Effect effect461 = null;

            public Object effect461Var = null;

            public ElaborationRule elaborationRule462 = null;

            public ElaborationRule elaborationRule463 = null;

            public void init_17_0_5_edc0357_1345510114692_336380_14867Members() {
                try {
                    _17_0_5_edc0357_1345510114691_687782_14862_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114691_687782_14862_endTime", this);
                    _17_0_5_edc0357_1345510114691_435966_14864_exists = new BooleanParameter("_17_0_5_edc0357_1345510114691_435966_14864_exists", this);
                    _17_0_5_edc0357_1345510114691_619007_14863_exists = new BooleanParameter("_17_0_5_edc0357_1345510114691_619007_14863_exists", this);
                    objectToPass = new Parameter<LADWP>("objectToPass", null, null, this);
                    constraint460 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114691_687782_14862_endTime)));
                    _17_0_5_edc0357_1345510114691_435966_14864_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114691_435966_14864_exists, new Expression<Boolean>(true));
                    _17_0_5_edc0357_1345510114691_619007_14863_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114691_619007_14863_exists, new Expression<Boolean>(true));
                    effect461Var = sig_17_0_5_edc0357_1345510114692_891792_14873;
                    effect461 = new EffectFunction(new FunctionCall((Object) effect461Var, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114692_336380_14867Collections() {
                parameters.add(_17_0_5_edc0357_1345510114691_687782_14862_endTime);
                parameters.add(_17_0_5_edc0357_1345510114691_435966_14864_exists);
                parameters.add(_17_0_5_edc0357_1345510114691_619007_14863_exists);
                parameters.add(objectToPass);
                constraintExpressions.add(constraint460);
                dependencies.add(_17_0_5_edc0357_1345510114691_435966_14864_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510114691_619007_14863_existsDependency);
                Set<Effect> effectsForeffect461Var = new HashSet<Effect>();
                effectsForeffect461Var.add(effect461);
                effects.put((Parameter<?>) effect461Var, effectsForeffect461Var);
            }

            public void init_17_0_5_edc0357_1345510114692_336380_14867Elaborations() {
                Expression<?>[] arguments462 = new Expression<?>[2];
                arguments462[0] = new Expression<Integer>(endTime);
                arguments462[1] = new Expression<LADWP>(objectToPass);
                Expression<Boolean> condition462 = new Expression<Boolean>(_17_0_5_edc0357_1345510114691_435966_14864_exists);
                elaborationRule462 = addElaborationRule(condition462, _17_0_5_edc0357_1345510113602_419995_13835.this, LADWP._17_0_5_edc0357_1345510113602_419995_13835._17_0_5_edc0357_1345510114691_435966_14864.class, "_ReadStructuralFeatureAction_generatePowerLater", arguments462);
                Expression<?>[] arguments463 = new Expression<?>[2];
                arguments463[0] = new Expression<Integer>(endTime);
                arguments463[1] = new Expression<LADWP>(objectToPass);
                Expression<Boolean> condition463 = new Expression<Boolean>(_17_0_5_edc0357_1345510114691_619007_14863_exists);
                elaborationRule463 = addElaborationRule(condition463, _17_0_5_edc0357_1345510113602_419995_13835.this, LADWP._17_0_5_edc0357_1345510113602_419995_13835._17_0_5_edc0357_1345510114691_619007_14863.class, "_ReadStructuralFeatureAction_generatePowerLater", arguments463);
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

            public ElaborationRule elaborationRule464 = null;

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
                Expression<?>[] arguments464 = new Expression<?>[1];
                arguments464[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition464 = new Expression<Boolean>(_17_0_5_edc0357_1345510114691_687782_14862_exists);
                elaborationRule464 = addElaborationRule(condition464, _17_0_5_edc0357_1345510113602_419995_13835.this, LADWP._17_0_5_edc0357_1345510113602_419995_13835._17_0_5_edc0357_1345510114691_687782_14862.class, "_ReadSelfAction_generatePowerLater", arguments464);
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

            public ConstraintExpression constraint465 = null;

            public void init_17_0_5_edc0357_1345510114692_509502_14869Members() {
                try {
                    _17_0_5_edc0357_1345510114692_827724_14866_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114692_827724_14866_endTime", this);
                    constraint465 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114692_827724_14866_endTime)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114692_509502_14869Collections() {
                parameters.add(_17_0_5_edc0357_1345510114692_827724_14866_endTime);
                constraintExpressions.add(constraint465);
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

        public Parameter< ObjectFlow<Object> > sig_17_0_5_edc0357_1345510114714_123439_14955 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_5_edc0357_1345510114711_993419_14925 = null;

        public Parameter< ObjectFlow<LADWP> > sig_17_0_5_edc0357_1345510114713_927688_14938 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_5_edc0357_1345510114712_230967_14928 = null;

        public Parameter< ObjectFlow<LADWP> > sig_17_0_5_edc0357_1345510114716_642816_14967 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_5_edc0357_1345510114712_959438_14929 = null;

        public Parameter< ObjectFlow<Object> > sig_17_0_5_edc0357_1345510114714_694491_14954 = null;

        public Parameter< ObjectFlow<LADWP> > sig_17_0_5_edc0357_1345510114713_908211_14937 = null;

        public Parameter< ObjectFlow<Object> > sig_17_0_5_edc0357_1345510114715_405387_14958 = null;

        public Parameter< ObjectFlow<LADWP> > sig_17_0_5_edc0357_1345510114713_823361_14936 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_5_edc0357_1345510114712_662975_14930 = null;

        public Parameter< ObjectFlow<LADWP> > sig_17_0_5_edc0357_1345510114713_504410_14939 = null;

        public Parameter< ObjectFlow<LADWP> > sig_17_0_5_edc0357_1345510114713_433084_14940 = null;

        public Parameter< ObjectFlow<Object> > sig_17_0_5_edc0357_1345510114715_89246_14963 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_5_edc0357_1345510114712_405485_14932 = null;

        public Parameter< ObjectFlow<Object> > sig_17_0_5_edc0357_1345510114715_649476_14956 = null;

        public Parameter< ObjectFlow<Object> > sig_17_0_5_edc0357_1345510114715_444775_14957 = null;

        public Parameter< ObjectFlow<LADWP> > sig_17_0_5_edc0357_1345510114713_115246_14941 = null;

        public Parameter< ObjectFlow<Object> > sig_17_0_5_edc0357_1345510114714_139055_14953 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_5_edc0357_1345510114715_582865_14966 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_5_edc0357_1345510114712_394314_14933 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_5_edc0357_1345510114715_883477_14965 = null;

        public IntegerParameter invoke_time = null;

        public Parameter< ObjectFlow<LADWP> > sig_17_0_5_edc0357_1345510114713_943749_14942 = null;

        public Parameter< ObjectFlow<LADWP> > sig_17_0_5_edc0357_1345510114715_620321_14961 = null;

        public Parameter< ObjectFlow<Object> > sig_17_0_5_edc0357_1345510114716_469957_14969 = null;

        public Parameter< ObjectFlow<Object> > sig_17_0_5_edc0357_1345510114715_590269_14959 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_5_edc0357_1345510114712_343415_14926 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_5_edc0357_1345510114712_148053_14927 = null;

        public ElaborationRule elaborationRule466 = null;

        public void init_17_0_5_edc0357_1345510113603_796245_13836Members() {
            try {
                sig_17_0_5_edc0357_1345510114713_836896_14943 = new Parameter<ObjectFlow<LADWP>>("sig_17_0_5_edc0357_1345510114713_836896_14943", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114713_836896_14943"), this);
                sig_17_0_5_edc0357_1345510114714_123439_14955 = new Parameter<ObjectFlow<Object>>("sig_17_0_5_edc0357_1345510114714_123439_14955", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114714_123439_14955"), this);
                sig_17_0_5_edc0357_1345510114711_993419_14925 = new Parameter<ObjectFlow<Integer>>("sig_17_0_5_edc0357_1345510114711_993419_14925", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114711_993419_14925"), this);
                sig_17_0_5_edc0357_1345510114713_927688_14938 = new Parameter<ObjectFlow<LADWP>>("sig_17_0_5_edc0357_1345510114713_927688_14938", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114713_927688_14938"), this);
                sig_17_0_5_edc0357_1345510114712_230967_14928 = new Parameter<ObjectFlow<Integer>>("sig_17_0_5_edc0357_1345510114712_230967_14928", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114712_230967_14928"), this);
                sig_17_0_5_edc0357_1345510114716_642816_14967 = new Parameter<ObjectFlow<LADWP>>("sig_17_0_5_edc0357_1345510114716_642816_14967", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114716_642816_14967"), this);
                sig_17_0_5_edc0357_1345510114712_959438_14929 = new Parameter<ObjectFlow<Integer>>("sig_17_0_5_edc0357_1345510114712_959438_14929", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114712_959438_14929"), this);
                sig_17_0_5_edc0357_1345510114714_694491_14954 = new Parameter<ObjectFlow<Object>>("sig_17_0_5_edc0357_1345510114714_694491_14954", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114714_694491_14954"), this);
                sig_17_0_5_edc0357_1345510114713_908211_14937 = new Parameter<ObjectFlow<LADWP>>("sig_17_0_5_edc0357_1345510114713_908211_14937", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114713_908211_14937"), this);
                sig_17_0_5_edc0357_1345510114715_405387_14958 = new Parameter<ObjectFlow<Object>>("sig_17_0_5_edc0357_1345510114715_405387_14958", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114715_405387_14958"), this);
                sig_17_0_5_edc0357_1345510114713_823361_14936 = new Parameter<ObjectFlow<LADWP>>("sig_17_0_5_edc0357_1345510114713_823361_14936", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114713_823361_14936"), this);
                sig_17_0_5_edc0357_1345510114712_662975_14930 = new Parameter<ObjectFlow<Integer>>("sig_17_0_5_edc0357_1345510114712_662975_14930", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114712_662975_14930"), this);
                sig_17_0_5_edc0357_1345510114713_504410_14939 = new Parameter<ObjectFlow<LADWP>>("sig_17_0_5_edc0357_1345510114713_504410_14939", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114713_504410_14939"), this);
                sig_17_0_5_edc0357_1345510114713_433084_14940 = new Parameter<ObjectFlow<LADWP>>("sig_17_0_5_edc0357_1345510114713_433084_14940", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114713_433084_14940"), this);
                sig_17_0_5_edc0357_1345510114715_89246_14963 = new Parameter<ObjectFlow<Object>>("sig_17_0_5_edc0357_1345510114715_89246_14963", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114715_89246_14963"), this);
                sig_17_0_5_edc0357_1345510114712_405485_14932 = new Parameter<ObjectFlow<Integer>>("sig_17_0_5_edc0357_1345510114712_405485_14932", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114712_405485_14932"), this);
                sig_17_0_5_edc0357_1345510114715_649476_14956 = new Parameter<ObjectFlow<Object>>("sig_17_0_5_edc0357_1345510114715_649476_14956", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114715_649476_14956"), this);
                sig_17_0_5_edc0357_1345510114715_444775_14957 = new Parameter<ObjectFlow<Object>>("sig_17_0_5_edc0357_1345510114715_444775_14957", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114715_444775_14957"), this);
                sig_17_0_5_edc0357_1345510114713_115246_14941 = new Parameter<ObjectFlow<LADWP>>("sig_17_0_5_edc0357_1345510114713_115246_14941", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114713_115246_14941"), this);
                sig_17_0_5_edc0357_1345510114714_139055_14953 = new Parameter<ObjectFlow<Object>>("sig_17_0_5_edc0357_1345510114714_139055_14953", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114714_139055_14953"), this);
                sig_17_0_5_edc0357_1345510114715_582865_14966 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_5_edc0357_1345510114715_582865_14966", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114715_582865_14966"), this);
                sig_17_0_5_edc0357_1345510114712_394314_14933 = new Parameter<ObjectFlow<Integer>>("sig_17_0_5_edc0357_1345510114712_394314_14933", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114712_394314_14933"), this);
                sig_17_0_5_edc0357_1345510114715_883477_14965 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_5_edc0357_1345510114715_883477_14965", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114715_883477_14965"), this);
                invoke_time = new IntegerParameter("invoke_time", this);
                sig_17_0_5_edc0357_1345510114713_943749_14942 = new Parameter<ObjectFlow<LADWP>>("sig_17_0_5_edc0357_1345510114713_943749_14942", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114713_943749_14942"), this);
                sig_17_0_5_edc0357_1345510114715_620321_14961 = new Parameter<ObjectFlow<LADWP>>("sig_17_0_5_edc0357_1345510114715_620321_14961", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114715_620321_14961"), this);
                sig_17_0_5_edc0357_1345510114716_469957_14969 = new Parameter<ObjectFlow<Object>>("sig_17_0_5_edc0357_1345510114716_469957_14969", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114716_469957_14969"), this);
                sig_17_0_5_edc0357_1345510114715_590269_14959 = new Parameter<ObjectFlow<Object>>("sig_17_0_5_edc0357_1345510114715_590269_14959", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114715_590269_14959"), this);
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
            parameters.add(sig_17_0_5_edc0357_1345510114712_662975_14930);
            parameters.add(sig_17_0_5_edc0357_1345510114713_504410_14939);
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
            Expression<?>[] arguments466 = new Expression<?>[1];
            arguments466[0] = new Expression<Integer>(invoke_time);
            Expression<Boolean> condition466 = new Expression<Boolean>(true);
            elaborationRule466 = addElaborationRule(condition466, _17_0_5_edc0357_1345510113603_796245_13836.this, LADWP._17_0_5_edc0357_1345510113603_796245_13836._17_0_5_edc0357_1345510114707_609526_14902.class, "_InitialNode_InitializeLADWP", arguments466);
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

            public ElaborationRule elaborationRule467 = null;

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
                Expression<?>[] arguments467 = new Expression<?>[1];
                arguments467[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition467 = new Expression<Boolean>(_17_0_5_edc0357_1345510114710_630744_14916_exists);
                elaborationRule467 = addElaborationRule(condition467, _17_0_5_edc0357_1345510113603_796245_13836.this, LADWP._17_0_5_edc0357_1345510113603_796245_13836._17_0_5_edc0357_1345510114710_630744_14916.class, "_ForkNode_InitializeLADWP", arguments467);
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

            public ConstraintExpression constraint468 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114710_727728_14917_existsDependency = null;

            public ElaborationRule elaborationRule469 = null;

            public void init_17_0_5_edc0357_1345510114707_62220_14903Members() {
                try {
                    _17_0_5_edc0357_1345510114982_755950_15425 = new Parameter<LADWP>("_17_0_5_edc0357_1345510114982_755950_15425", null, LADWP.this, this);
                    _17_0_5_edc0357_1345510114710_630744_14916_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114710_630744_14916_endTime", this);
                    _17_0_5_edc0357_1345510114710_727728_14917_exists = new BooleanParameter("_17_0_5_edc0357_1345510114710_727728_14917_exists", this);
                    constraint468 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114710_630744_14916_endTime)));
                    _17_0_5_edc0357_1345510114710_727728_14917_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114710_727728_14917_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114707_62220_14903Collections() {
                parameters.add(_17_0_5_edc0357_1345510114982_755950_15425);
                parameters.add(_17_0_5_edc0357_1345510114710_630744_14916_endTime);
                parameters.add(_17_0_5_edc0357_1345510114710_727728_14917_exists);
                constraintExpressions.add(constraint468);
                dependencies.add(_17_0_5_edc0357_1345510114710_727728_14917_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510114707_62220_14903Elaborations() {
                Expression<?>[] arguments469 = new Expression<?>[2];
                arguments469[0] = new Expression<Integer>(endTime);
                arguments469[1] = new Expression<LADWP>(_17_0_5_edc0357_1345510114982_755950_15425);
                Expression<Boolean> condition469 = new Expression<Boolean>(_17_0_5_edc0357_1345510114710_727728_14917_exists);
                elaborationRule469 = addElaborationRule(condition469, _17_0_5_edc0357_1345510113603_796245_13836.this, LADWP._17_0_5_edc0357_1345510113603_796245_13836._17_0_5_edc0357_1345510114710_727728_14917.class, "_ForkNode_InitializeLADWP", arguments469);
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

            public ConstraintExpression constraint470 = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114983_337424_15426Dependency = null;

            public Dependency< LADWP > _17_0_5_edc0357_1345510114984_511435_15427Dependency = null;

            public Effect effect471 = null;

            public Object effect471Var = null;

            public Effect effect472 = null;

            public Object effect472Var = null;

            public void init_17_0_5_edc0357_1345510114707_796416_14904Members() {
                try {
                    _17_0_5_edc0357_1345510114983_337424_15426 = new IntegerParameter("_17_0_5_edc0357_1345510114983_337424_15426", this);
                    _17_0_5_edc0357_1345510114710_630744_14916_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114710_630744_14916_endTime", this);
                    _17_0_5_edc0357_1345510114984_511435_15427 = new Parameter<LADWP>("_17_0_5_edc0357_1345510114984_511435_15427", null, null, this);
                    constraint470 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114710_630744_14916_endTime)));
                    _17_0_5_edc0357_1345510114983_337424_15426Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114983_337424_15426, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114712_148053_14927, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1345510114984_511435_15427Dependency = new Dependency<LADWP>(_17_0_5_edc0357_1345510114984_511435_15427, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114713_115246_14941, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    effect471Var = sig_17_0_5_edc0357_1345510114715_444775_14957;
                    effect471 = new EffectFunction(new FunctionCall((Object) effect471Var, Utils.getMethodForArgTypes("ObjectFlow<Object>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { true, startTime }));
                    effect472Var = current_margin__17_0_5_edc0357_1345510113615_810190_13850;
                    effect472 = new EffectFunction(new FunctionCall((Object) effect472Var, Utils.getMethodForArgTypes("TimeVaryingMap<Integer>", "generated", "setValue", java.lang.Integer.class, java.lang.Object.class), new Object[] { startTime, _17_0_5_edc0357_1345510114983_337424_15426 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114707_796416_14904Collections() {
                parameters.add(_17_0_5_edc0357_1345510114983_337424_15426);
                parameters.add(_17_0_5_edc0357_1345510114710_630744_14916_endTime);
                parameters.add(_17_0_5_edc0357_1345510114984_511435_15427);
                constraintExpressions.add(constraint470);
                dependencies.add(_17_0_5_edc0357_1345510114983_337424_15426Dependency);
                dependencies.add(_17_0_5_edc0357_1345510114984_511435_15427Dependency);
                Set<Effect> effectsForeffect471Var = new HashSet<Effect>();
                effectsForeffect471Var.add(effect471);
                effects.put((Parameter<?>) effect471Var, effectsForeffect471Var);
                Set<Effect> effectsForeffect472Var = new HashSet<Effect>();
                effectsForeffect472Var.add(effect472);
                effects.put((Parameter<?>) effect472Var, effectsForeffect472Var);
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

            public ConstraintExpression constraint473 = null;

            public Dependency< LADWP > _17_0_5_edc0357_1345510114985_383319_15429Dependency = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114984_949321_15428Dependency = null;

            public Effect effect474 = null;

            public Object effect474Var = null;

            public Effect effect475 = null;

            public Object effect475Var = null;

            public void init_17_0_5_edc0357_1345510114708_845512_14905Members() {
                try {
                    _17_0_5_edc0357_1345510114985_383319_15429 = new Parameter<LADWP>("_17_0_5_edc0357_1345510114985_383319_15429", null, null, this);
                    _17_0_5_edc0357_1345510114710_630744_14916_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114710_630744_14916_endTime", this);
                    _17_0_5_edc0357_1345510114984_949321_15428 = new IntegerParameter("_17_0_5_edc0357_1345510114984_949321_15428", this);
                    constraint473 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114710_630744_14916_endTime)));
                    _17_0_5_edc0357_1345510114985_383319_15429Dependency = new Dependency<LADWP>(_17_0_5_edc0357_1345510114985_383319_15429, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114713_433084_14940, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1345510114984_949321_15428Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114984_949321_15428, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114712_343415_14926, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    effect474Var = sig_17_0_5_edc0357_1345510114715_649476_14956;
                    effect474 = new EffectFunction(new FunctionCall((Object) effect474Var, Utils.getMethodForArgTypes("ObjectFlow<Object>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { true, startTime }));
                    effect475Var = expected_margin__17_0_5_edc0357_1345510113614_771928_13849;
                    effect475 = new EffectFunction(new FunctionCall((Object) effect475Var, Utils.getMethodForArgTypes("TimeVaryingMap<Integer>", "generated", "setValue", java.lang.Integer.class, java.lang.Object.class), new Object[] { startTime, _17_0_5_edc0357_1345510114984_949321_15428 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114708_845512_14905Collections() {
                parameters.add(_17_0_5_edc0357_1345510114985_383319_15429);
                parameters.add(_17_0_5_edc0357_1345510114710_630744_14916_endTime);
                parameters.add(_17_0_5_edc0357_1345510114984_949321_15428);
                constraintExpressions.add(constraint473);
                dependencies.add(_17_0_5_edc0357_1345510114985_383319_15429Dependency);
                dependencies.add(_17_0_5_edc0357_1345510114984_949321_15428Dependency);
                Set<Effect> effectsForeffect474Var = new HashSet<Effect>();
                effectsForeffect474Var.add(effect474);
                effects.put((Parameter<?>) effect474Var, effectsForeffect474Var);
                Set<Effect> effectsForeffect475Var = new HashSet<Effect>();
                effectsForeffect475Var.add(effect475);
                effects.put((Parameter<?>) effect475Var, effectsForeffect475Var);
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

            public ConstraintExpression constraint476 = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114986_756803_15430Dependency = null;

            public Dependency< LADWP > _17_0_5_edc0357_1345510114986_143356_15431Dependency = null;

            public Effect effect477 = null;

            public Object effect477Var = null;

            public Effect effect478 = null;

            public Object effect478Var = null;

            public void init_17_0_5_edc0357_1345510114708_795071_14906Members() {
                try {
                    _17_0_5_edc0357_1345510114710_630744_14916_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114710_630744_14916_endTime", this);
                    _17_0_5_edc0357_1345510114986_756803_15430 = new IntegerParameter("_17_0_5_edc0357_1345510114986_756803_15430", this);
                    _17_0_5_edc0357_1345510114986_143356_15431 = new Parameter<LADWP>("_17_0_5_edc0357_1345510114986_143356_15431", null, null, this);
                    constraint476 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114710_630744_14916_endTime)));
                    _17_0_5_edc0357_1345510114986_756803_15430Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114986_756803_15430, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114712_394314_14933, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1345510114986_143356_15431Dependency = new Dependency<LADWP>(_17_0_5_edc0357_1345510114986_143356_15431, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114713_943749_14942, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    effect477Var = sig_17_0_5_edc0357_1345510114715_405387_14958;
                    effect477 = new EffectFunction(new FunctionCall((Object) effect477Var, Utils.getMethodForArgTypes("ObjectFlow<Object>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { true, startTime }));
                    effect478Var = expected_load__17_0_5_edc0357_1345510113611_416109_13845;
                    effect478 = new EffectFunction(new FunctionCall((Object) effect478Var, Utils.getMethodForArgTypes("TimeVaryingMap<Integer>", "generated", "setValue", java.lang.Integer.class, java.lang.Object.class), new Object[] { startTime, _17_0_5_edc0357_1345510114986_756803_15430 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114708_795071_14906Collections() {
                parameters.add(_17_0_5_edc0357_1345510114710_630744_14916_endTime);
                parameters.add(_17_0_5_edc0357_1345510114986_756803_15430);
                parameters.add(_17_0_5_edc0357_1345510114986_143356_15431);
                constraintExpressions.add(constraint476);
                dependencies.add(_17_0_5_edc0357_1345510114986_756803_15430Dependency);
                dependencies.add(_17_0_5_edc0357_1345510114986_143356_15431Dependency);
                Set<Effect> effectsForeffect477Var = new HashSet<Effect>();
                effectsForeffect477Var.add(effect477);
                effects.put((Parameter<?>) effect477Var, effectsForeffect477Var);
                Set<Effect> effectsForeffect478Var = new HashSet<Effect>();
                effectsForeffect478Var.add(effect478);
                effects.put((Parameter<?>) effect478Var, effectsForeffect478Var);
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

            public ConstraintExpression constraint479 = null;

            public Dependency< LADWP > _17_0_5_edc0357_1345510114988_339710_15433Dependency = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114987_821137_15432Dependency = null;

            public Effect effect480 = null;

            public Object effect480Var = null;

            public Effect effect481 = null;

            public Object effect481Var = null;

            public void init_17_0_5_edc0357_1345510114708_203082_14907Members() {
                try {
                    _17_0_5_edc0357_1345510114988_339710_15433 = new Parameter<LADWP>("_17_0_5_edc0357_1345510114988_339710_15433", null, null, this);
                    _17_0_5_edc0357_1345510114987_821137_15432 = new IntegerParameter("_17_0_5_edc0357_1345510114987_821137_15432", this);
                    _17_0_5_edc0357_1345510114710_630744_14916_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114710_630744_14916_endTime", this);
                    constraint479 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114710_630744_14916_endTime)));
                    _17_0_5_edc0357_1345510114988_339710_15433Dependency = new Dependency<LADWP>(_17_0_5_edc0357_1345510114988_339710_15433, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114713_908211_14937, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1345510114987_821137_15432Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114987_821137_15432, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114712_662975_14930, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    effect480Var = sig_17_0_5_edc0357_1345510114714_139055_14953;
                    effect480 = new EffectFunction(new FunctionCall((Object) effect480Var, Utils.getMethodForArgTypes("ObjectFlow<Object>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { true, startTime }));
                    effect481Var = reported_load__17_0_5_edc0357_1345510113610_466337_13843;
                    effect481 = new EffectFunction(new FunctionCall((Object) effect481Var, Utils.getMethodForArgTypes("TimeVaryingMap<Integer>", "generated", "setValue", java.lang.Integer.class, java.lang.Object.class), new Object[] { startTime, _17_0_5_edc0357_1345510114987_821137_15432 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114708_203082_14907Collections() {
                parameters.add(_17_0_5_edc0357_1345510114988_339710_15433);
                parameters.add(_17_0_5_edc0357_1345510114987_821137_15432);
                parameters.add(_17_0_5_edc0357_1345510114710_630744_14916_endTime);
                constraintExpressions.add(constraint479);
                dependencies.add(_17_0_5_edc0357_1345510114988_339710_15433Dependency);
                dependencies.add(_17_0_5_edc0357_1345510114987_821137_15432Dependency);
                Set<Effect> effectsForeffect480Var = new HashSet<Effect>();
                effectsForeffect480Var.add(effect480);
                effects.put((Parameter<?>) effect480Var, effectsForeffect480Var);
                Set<Effect> effectsForeffect481Var = new HashSet<Effect>();
                effectsForeffect481Var.add(effect481);
                effects.put((Parameter<?>) effect481Var, effectsForeffect481Var);
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

            public ConstraintExpression constraint482 = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114988_614192_15434Dependency = null;

            public Dependency< LADWP > _17_0_5_edc0357_1345510114989_580516_15435Dependency = null;

            public Effect effect483 = null;

            public Object effect483Var = null;

            public Effect effect484 = null;

            public Object effect484Var = null;

            public void init_17_0_5_edc0357_1345510114708_870746_14908Members() {
                try {
                    _17_0_5_edc0357_1345510114988_614192_15434 = new IntegerParameter("_17_0_5_edc0357_1345510114988_614192_15434", this);
                    _17_0_5_edc0357_1345510114989_580516_15435 = new Parameter<LADWP>("_17_0_5_edc0357_1345510114989_580516_15435", null, null, this);
                    _17_0_5_edc0357_1345510114710_630744_14916_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114710_630744_14916_endTime", this);
                    constraint482 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114710_630744_14916_endTime)));
                    _17_0_5_edc0357_1345510114988_614192_15434Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114988_614192_15434, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114711_993419_14925, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1345510114989_580516_15435Dependency = new Dependency<LADWP>(_17_0_5_edc0357_1345510114989_580516_15435, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114713_504410_14939, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    effect483Var = sig_17_0_5_edc0357_1345510114714_123439_14955;
                    effect483 = new EffectFunction(new FunctionCall((Object) effect483Var, Utils.getMethodForArgTypes("ObjectFlow<Object>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { true, startTime }));
                    effect484Var = reported_generation__17_0_5_edc0357_1345510113612_851613_13846;
                    effect484 = new EffectFunction(new FunctionCall((Object) effect484Var, Utils.getMethodForArgTypes("TimeVaryingMap<Integer>", "generated", "setValue", java.lang.Integer.class, java.lang.Object.class), new Object[] { startTime, _17_0_5_edc0357_1345510114988_614192_15434 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114708_870746_14908Collections() {
                parameters.add(_17_0_5_edc0357_1345510114988_614192_15434);
                parameters.add(_17_0_5_edc0357_1345510114989_580516_15435);
                parameters.add(_17_0_5_edc0357_1345510114710_630744_14916_endTime);
                constraintExpressions.add(constraint482);
                dependencies.add(_17_0_5_edc0357_1345510114988_614192_15434Dependency);
                dependencies.add(_17_0_5_edc0357_1345510114989_580516_15435Dependency);
                Set<Effect> effectsForeffect483Var = new HashSet<Effect>();
                effectsForeffect483Var.add(effect483);
                effects.put((Parameter<?>) effect483Var, effectsForeffect483Var);
                Set<Effect> effectsForeffect484Var = new HashSet<Effect>();
                effectsForeffect484Var.add(effect484);
                effects.put((Parameter<?>) effect484Var, effectsForeffect484Var);
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

            public ConstraintExpression constraint485 = null;

            public Dependency< LADWP > _17_0_5_edc0357_1345510114990_966028_15437Dependency = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114990_167194_15436Dependency = null;

            public Effect effect486 = null;

            public Object effect486Var = null;

            public Effect effect487 = null;

            public Object effect487Var = null;

            public void init_17_0_5_edc0357_1345510114709_602929_14909Members() {
                try {
                    _17_0_5_edc0357_1345510114990_966028_15437 = new Parameter<LADWP>("_17_0_5_edc0357_1345510114990_966028_15437", null, null, this);
                    _17_0_5_edc0357_1345510114990_167194_15436 = new IntegerParameter("_17_0_5_edc0357_1345510114990_167194_15436", this);
                    _17_0_5_edc0357_1345510114710_630744_14916_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114710_630744_14916_endTime", this);
                    constraint485 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114710_630744_14916_endTime)));
                    _17_0_5_edc0357_1345510114990_966028_15437Dependency = new Dependency<LADWP>(_17_0_5_edc0357_1345510114990_966028_15437, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114713_927688_14938, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1345510114990_167194_15436Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114990_167194_15436, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114712_230967_14928, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    effect486Var = sig_17_0_5_edc0357_1345510114714_694491_14954;
                    effect486 = new EffectFunction(new FunctionCall((Object) effect486Var, Utils.getMethodForArgTypes("ObjectFlow<Object>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { true, startTime }));
                    effect487Var = power_needed__17_0_5_edc0357_1345510113610_569612_13844;
                    effect487 = new EffectFunction(new FunctionCall((Object) effect487Var, Utils.getMethodForArgTypes("TimeVaryingMap<Integer>", "generated", "setValue", java.lang.Integer.class, java.lang.Object.class), new Object[] { startTime, _17_0_5_edc0357_1345510114990_167194_15436 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114709_602929_14909Collections() {
                parameters.add(_17_0_5_edc0357_1345510114990_966028_15437);
                parameters.add(_17_0_5_edc0357_1345510114990_167194_15436);
                parameters.add(_17_0_5_edc0357_1345510114710_630744_14916_endTime);
                constraintExpressions.add(constraint485);
                dependencies.add(_17_0_5_edc0357_1345510114990_966028_15437Dependency);
                dependencies.add(_17_0_5_edc0357_1345510114990_167194_15436Dependency);
                Set<Effect> effectsForeffect486Var = new HashSet<Effect>();
                effectsForeffect486Var.add(effect486);
                effects.put((Parameter<?>) effect486Var, effectsForeffect486Var);
                Set<Effect> effectsForeffect487Var = new HashSet<Effect>();
                effectsForeffect487Var.add(effect487);
                effects.put((Parameter<?>) effect487Var, effectsForeffect487Var);
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

            public ConstraintExpression constraint488 = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114991_200706_15438Dependency = null;

            public Dependency< LADWP > _17_0_5_edc0357_1345510114992_216784_15439Dependency = null;

            public Effect effect489 = null;

            public Object effect489Var = null;

            public Effect effect490 = null;

            public Object effect490Var = null;

            public void init_17_0_5_edc0357_1345510114709_561792_14910Members() {
                try {
                    _17_0_5_edc0357_1345510114991_200706_15438 = new IntegerParameter("_17_0_5_edc0357_1345510114991_200706_15438", this);
                    _17_0_5_edc0357_1345510114992_216784_15439 = new Parameter<LADWP>("_17_0_5_edc0357_1345510114992_216784_15439", null, null, this);
                    _17_0_5_edc0357_1345510114710_630744_14916_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114710_630744_14916_endTime", this);
                    constraint488 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114710_630744_14916_endTime)));
                    _17_0_5_edc0357_1345510114991_200706_15438Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114991_200706_15438, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114712_405485_14932, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1345510114992_216784_15439Dependency = new Dependency<LADWP>(_17_0_5_edc0357_1345510114992_216784_15439, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114713_836896_14943, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    effect489Var = sig_17_0_5_edc0357_1345510114715_590269_14959;
                    effect489 = new EffectFunction(new FunctionCall((Object) effect489Var, Utils.getMethodForArgTypes("ObjectFlow<Object>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { true, startTime }));
                    effect490Var = predicted_load__17_0_5_edc0357_1345510113613_464654_13847;
                    effect490 = new EffectFunction(new FunctionCall((Object) effect490Var, Utils.getMethodForArgTypes("TimeVaryingMap<Integer>", "generated", "setValue", java.lang.Integer.class, java.lang.Object.class), new Object[] { startTime, _17_0_5_edc0357_1345510114991_200706_15438 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114709_561792_14910Collections() {
                parameters.add(_17_0_5_edc0357_1345510114991_200706_15438);
                parameters.add(_17_0_5_edc0357_1345510114992_216784_15439);
                parameters.add(_17_0_5_edc0357_1345510114710_630744_14916_endTime);
                constraintExpressions.add(constraint488);
                dependencies.add(_17_0_5_edc0357_1345510114991_200706_15438Dependency);
                dependencies.add(_17_0_5_edc0357_1345510114992_216784_15439Dependency);
                Set<Effect> effectsForeffect489Var = new HashSet<Effect>();
                effectsForeffect489Var.add(effect489);
                effects.put((Parameter<?>) effect489Var, effectsForeffect489Var);
                Set<Effect> effectsForeffect490Var = new HashSet<Effect>();
                effectsForeffect490Var.add(effect490);
                effects.put((Parameter<?>) effect490Var, effectsForeffect490Var);
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

            public ConstraintExpression constraint491 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114709_107808_14912_existsDependency = null;

            public ElaborationRule elaborationRule492 = null;

            public void init_17_0_5_edc0357_1345510114709_635836_14911Members() {
                try {
                    _17_0_5_edc0357_1345510114709_107808_14912_exists = new BooleanParameter("_17_0_5_edc0357_1345510114709_107808_14912_exists", this);
                    _17_0_5_edc0357_1345510114710_630744_14916_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114710_630744_14916_endTime", this);
                    _17_0_5_edc0357_1345510114992_72825_15441 = new IntegerParameter("_17_0_5_edc0357_1345510114992_72825_15441", this);
                    constraint491 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114710_630744_14916_endTime)));
                    _17_0_5_edc0357_1345510114709_107808_14912_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114709_107808_14912_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114709_635836_14911Collections() {
                parameters.add(_17_0_5_edc0357_1345510114709_107808_14912_exists);
                parameters.add(_17_0_5_edc0357_1345510114710_630744_14916_endTime);
                parameters.add(_17_0_5_edc0357_1345510114992_72825_15441);
                constraintExpressions.add(constraint491);
                dependencies.add(_17_0_5_edc0357_1345510114709_107808_14912_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510114709_635836_14911Elaborations() {
                Expression<?>[] arguments492 = new Expression<?>[2];
                arguments492[0] = new Expression<Integer>(endTime);
                arguments492[1] = new Expression<Integer>(_17_0_5_edc0357_1345510114992_72825_15441);
                Expression<Boolean> condition492 = new Expression<Boolean>(_17_0_5_edc0357_1345510114709_107808_14912_exists);
                elaborationRule492 = addElaborationRule(condition492, _17_0_5_edc0357_1345510113603_796245_13836.this, LADWP._17_0_5_edc0357_1345510113603_796245_13836._17_0_5_edc0357_1345510114709_107808_14912.class, "_ForkNode_InitializeLADWP", arguments492);
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

            public ConstraintExpression constraint493 = null;

            public Effect effect494 = null;

            public Object effect494Var = null;

            public Effect effect495 = null;

            public Object effect495Var = null;

            public Effect effect496 = null;

            public Object effect496Var = null;

            public Effect effect497 = null;

            public Object effect497Var = null;

            public Effect effect498 = null;

            public Object effect498Var = null;

            public Effect effect499 = null;

            public Object effect499Var = null;

            public void init_17_0_5_edc0357_1345510114709_107808_14912Members() {
                try {
                    objectToPass = new IntegerParameter("objectToPass", this);
                    _17_0_5_edc0357_1345510114709_635836_14911_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114709_635836_14911_endTime", this);
                    constraint493 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114709_635836_14911_endTime)));
                    effect494Var = sig_17_0_5_edc0357_1345510114711_993419_14925;
                    effect494 = new EffectFunction(new FunctionCall((Object) effect494Var, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                    effect495Var = sig_17_0_5_edc0357_1345510114712_343415_14926;
                    effect495 = new EffectFunction(new FunctionCall((Object) effect495Var, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                    effect496Var = sig_17_0_5_edc0357_1345510114712_148053_14927;
                    effect496 = new EffectFunction(new FunctionCall((Object) effect496Var, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                    effect497Var = sig_17_0_5_edc0357_1345510114712_230967_14928;
                    effect497 = new EffectFunction(new FunctionCall((Object) effect497Var, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                    effect498Var = sig_17_0_5_edc0357_1345510114712_959438_14929;
                    effect498 = new EffectFunction(new FunctionCall((Object) effect498Var, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                    effect499Var = sig_17_0_5_edc0357_1345510114712_662975_14930;
                    effect499 = new EffectFunction(new FunctionCall((Object) effect499Var, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114709_107808_14912Collections() {
                parameters.add(objectToPass);
                parameters.add(_17_0_5_edc0357_1345510114709_635836_14911_endTime);
                constraintExpressions.add(constraint493);
                Set<Effect> effectsForeffect494Var = new HashSet<Effect>();
                effectsForeffect494Var.add(effect494);
                effects.put((Parameter<?>) effect494Var, effectsForeffect494Var);
                Set<Effect> effectsForeffect495Var = new HashSet<Effect>();
                effectsForeffect495Var.add(effect495);
                effects.put((Parameter<?>) effect495Var, effectsForeffect495Var);
                Set<Effect> effectsForeffect496Var = new HashSet<Effect>();
                effectsForeffect496Var.add(effect496);
                effects.put((Parameter<?>) effect496Var, effectsForeffect496Var);
                Set<Effect> effectsForeffect497Var = new HashSet<Effect>();
                effectsForeffect497Var.add(effect497);
                effects.put((Parameter<?>) effect497Var, effectsForeffect497Var);
                Set<Effect> effectsForeffect498Var = new HashSet<Effect>();
                effectsForeffect498Var.add(effect498);
                effects.put((Parameter<?>) effect498Var, effectsForeffect498Var);
                Set<Effect> effectsForeffect499Var = new HashSet<Effect>();
                effectsForeffect499Var.add(effect499);
                effects.put((Parameter<?>) effect499Var, effectsForeffect499Var);
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

            public ConstraintExpression constraint500 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114710_541046_14918_existsDependency = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114993_45749_15442Dependency = null;

            public Dependency< LADWP > _17_0_5_edc0357_1345510114994_895167_15443Dependency = null;

            public Effect effect501 = null;

            public Object effect501Var = null;

            public ElaborationRule elaborationRule502 = null;

            public void init_17_0_5_edc0357_1345510114709_216705_14913Members() {
                try {
                    _17_0_5_edc0357_1345510114710_541046_14918_exists = new BooleanParameter("_17_0_5_edc0357_1345510114710_541046_14918_exists", this);
                    _17_0_5_edc0357_1345510114993_45749_15442 = new IntegerParameter("_17_0_5_edc0357_1345510114993_45749_15442", this);
                    _17_0_5_edc0357_1345510114710_630744_14916_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114710_630744_14916_endTime", this);
                    _17_0_5_edc0357_1345510114994_895167_15443 = new Parameter<LADWP>("_17_0_5_edc0357_1345510114994_895167_15443", null, null, this);
                    constraint500 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114710_630744_14916_endTime)));
                    _17_0_5_edc0357_1345510114710_541046_14918_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114710_541046_14918_exists, new Functions.And(new Functions.And(new Functions.And(new Functions.And(new Functions.And(new Functions.And(new Functions.And(new Functions.And(new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114714_139055_14953, Utils.getMethodForArgTypes("ObjectFlow<Object>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114714_694491_14954, Utils.getMethodForArgTypes("ObjectFlow<Object>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) }))), new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114714_123439_14955, Utils.getMethodForArgTypes("ObjectFlow<Object>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) }))), new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114715_649476_14956, Utils.getMethodForArgTypes("ObjectFlow<Object>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) }))), new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114715_444775_14957, Utils.getMethodForArgTypes("ObjectFlow<Object>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) }))), new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114715_405387_14958, Utils.getMethodForArgTypes("ObjectFlow<Object>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) }))), new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114715_590269_14959, Utils.getMethodForArgTypes("ObjectFlow<Object>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) }))), new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114715_89246_14963, Utils.getMethodForArgTypes("ObjectFlow<Object>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) }))), new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114716_469957_14969, Utils.getMethodForArgTypes("ObjectFlow<Object>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) }))));
                    _17_0_5_edc0357_1345510114993_45749_15442Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114993_45749_15442, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114712_959438_14929, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1345510114994_895167_15443Dependency = new Dependency<LADWP>(_17_0_5_edc0357_1345510114994_895167_15443, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114713_823361_14936, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    effect501Var = actual_load__17_0_5_edc0357_1345510113609_489064_13842;
                    effect501 = new EffectFunction(new FunctionCall((Object) effect501Var, Utils.getMethodForArgTypes("TimeVaryingMap<Integer>", "generated", "setValue", java.lang.Integer.class, java.lang.Object.class), new Object[] { startTime, _17_0_5_edc0357_1345510114993_45749_15442 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114709_216705_14913Collections() {
                parameters.add(_17_0_5_edc0357_1345510114710_541046_14918_exists);
                parameters.add(_17_0_5_edc0357_1345510114993_45749_15442);
                parameters.add(_17_0_5_edc0357_1345510114710_630744_14916_endTime);
                parameters.add(_17_0_5_edc0357_1345510114994_895167_15443);
                constraintExpressions.add(constraint500);
                dependencies.add(_17_0_5_edc0357_1345510114710_541046_14918_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510114993_45749_15442Dependency);
                dependencies.add(_17_0_5_edc0357_1345510114994_895167_15443Dependency);
                Set<Effect> effectsForeffect501Var = new HashSet<Effect>();
                effectsForeffect501Var.add(effect501);
                effects.put((Parameter<?>) effect501Var, effectsForeffect501Var);
            }

            public void init_17_0_5_edc0357_1345510114709_216705_14913Elaborations() {
                Expression<?>[] arguments502 = new Expression<?>[1];
                arguments502[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition502 = new Expression<Boolean>(_17_0_5_edc0357_1345510114710_541046_14918_exists);
                elaborationRule502 = addElaborationRule(condition502, _17_0_5_edc0357_1345510113603_796245_13836.this, LADWP._17_0_5_edc0357_1345510113603_796245_13836._17_0_5_edc0357_1345510114710_541046_14918.class, "_JoinNode_InitializeLADWP", arguments502);
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

            public ConstraintExpression constraint503 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114710_54144_14915_existsDependency = null;

            public ElaborationRule elaborationRule504 = null;

            public void init_17_0_5_edc0357_1345510114710_121887_14914Members() {
                try {
                    _17_0_5_edc0357_1345510114710_630744_14916_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114710_630744_14916_endTime", this);
                    _17_0_5_edc0357_1345510114994_761954_15445 = new IntegerParameter("_17_0_5_edc0357_1345510114994_761954_15445", this);
                    _17_0_5_edc0357_1345510114710_54144_14915_exists = new BooleanParameter("_17_0_5_edc0357_1345510114710_54144_14915_exists", this);
                    constraint503 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114710_630744_14916_endTime)));
                    _17_0_5_edc0357_1345510114710_54144_14915_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114710_54144_14915_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114710_121887_14914Collections() {
                parameters.add(_17_0_5_edc0357_1345510114710_630744_14916_endTime);
                parameters.add(_17_0_5_edc0357_1345510114994_761954_15445);
                parameters.add(_17_0_5_edc0357_1345510114710_54144_14915_exists);
                constraintExpressions.add(constraint503);
                dependencies.add(_17_0_5_edc0357_1345510114710_54144_14915_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510114710_121887_14914Elaborations() {
                Expression<?>[] arguments504 = new Expression<?>[2];
                arguments504[0] = new Expression<Integer>(endTime);
                arguments504[1] = new Expression<Integer>(_17_0_5_edc0357_1345510114994_761954_15445);
                Expression<Boolean> condition504 = new Expression<Boolean>(_17_0_5_edc0357_1345510114710_54144_14915_exists);
                elaborationRule504 = addElaborationRule(condition504, _17_0_5_edc0357_1345510113603_796245_13836.this, LADWP._17_0_5_edc0357_1345510113603_796245_13836._17_0_5_edc0357_1345510114710_54144_14915.class, "_ForkNode_InitializeLADWP", arguments504);
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

            public ConstraintExpression constraint505 = null;

            public Effect effect506 = null;

            public Object effect506Var = null;

            public Effect effect507 = null;

            public Object effect507Var = null;

            public void init_17_0_5_edc0357_1345510114710_54144_14915Members() {
                try {
                    _17_0_5_edc0357_1345510114710_121887_14914_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114710_121887_14914_endTime", this);
                    objectToPass = new IntegerParameter("objectToPass", this);
                    constraint505 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114710_121887_14914_endTime)));
                    effect506Var = sig_17_0_5_edc0357_1345510114712_405485_14932;
                    effect506 = new EffectFunction(new FunctionCall((Object) effect506Var, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                    effect507Var = sig_17_0_5_edc0357_1345510114712_394314_14933;
                    effect507 = new EffectFunction(new FunctionCall((Object) effect507Var, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114710_54144_14915Collections() {
                parameters.add(_17_0_5_edc0357_1345510114710_121887_14914_endTime);
                parameters.add(objectToPass);
                constraintExpressions.add(constraint505);
                Set<Effect> effectsForeffect506Var = new HashSet<Effect>();
                effectsForeffect506Var.add(effect506);
                effects.put((Parameter<?>) effect506Var, effectsForeffect506Var);
                Set<Effect> effectsForeffect507Var = new HashSet<Effect>();
                effectsForeffect507Var.add(effect507);
                effects.put((Parameter<?>) effect507Var, effectsForeffect507Var);
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

            public ConstraintExpression constraint508 = null;

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

            public ElaborationRule elaborationRule509 = null;

            public ElaborationRule elaborationRule510 = null;

            public ElaborationRule elaborationRule511 = null;

            public ElaborationRule elaborationRule512 = null;

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
                    constraint508 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114707_609526_14902_endTime)));
                    _17_0_5_edc0357_1345510114709_602929_14909_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114709_602929_14909_exists, new Functions.And(new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114713_927688_14938, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114712_230967_14928, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) }))));
                    _17_0_5_edc0357_1345510114711_949447_14920_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114711_949447_14920_exists, new Functions.And(new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114715_620321_14961, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114715_883477_14965, Utils.getMethodForArgTypes("ObjectFlow<Boolean>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) }))));
                    _17_0_5_edc0357_1345510114709_635836_14911_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114709_635836_14911_exists, new Expression<Boolean>(true));
                    _17_0_5_edc0357_1345510114708_795071_14906_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114708_795071_14906_exists, new Functions.And(new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114713_943749_14942, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114712_394314_14933, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) }))));
                    _17_0_5_edc0357_1345510114709_561792_14910_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114709_561792_14910_exists, new Functions.And(new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114713_836896_14943, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114712_405485_14932, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) }))));
                    _17_0_5_edc0357_1345510114708_870746_14908_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114708_870746_14908_exists, new Functions.And(new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114713_504410_14939, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114711_993419_14925, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) }))));
                    _17_0_5_edc0357_1345510114711_88466_14922_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114711_88466_14922_exists, new Functions.And(new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114716_642816_14967, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114715_582865_14966, Utils.getMethodForArgTypes("ObjectFlow<Boolean>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) }))));
                    _17_0_5_edc0357_1345510114711_700135_14921_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114711_700135_14921_exists, new Expression<Boolean>(true));
                    _17_0_5_edc0357_1345510114707_796416_14904_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114707_796416_14904_exists, new Functions.And(new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114713_115246_14941, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114712_148053_14927, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) }))));
                    _17_0_5_edc0357_1345510114708_845512_14905_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114708_845512_14905_exists, new Functions.And(new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114713_433084_14940, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114712_343415_14926, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) }))));
                    _17_0_5_edc0357_1345510114708_203082_14907_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114708_203082_14907_exists, new Functions.And(new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114713_908211_14937, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114712_662975_14930, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) }))));
                    _17_0_5_edc0357_1345510114707_62220_14903_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114707_62220_14903_exists, new Expression<Boolean>(true));
                    _17_0_5_edc0357_1345510114709_216705_14913_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114709_216705_14913_exists, new Functions.And(new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114713_823361_14936, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114712_959438_14929, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) }))));
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
                constraintExpressions.add(constraint508);
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
                Expression<?>[] arguments509 = new Expression<?>[1];
                arguments509[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition509 = new Expression<Boolean>(_17_0_5_edc0357_1345510114710_121887_14914_exists);
                elaborationRule509 = addElaborationRule(condition509, _17_0_5_edc0357_1345510113603_796245_13836.this, LADWP._17_0_5_edc0357_1345510113603_796245_13836._17_0_5_edc0357_1345510114710_121887_14914.class, "vs10_ValueSpecificationAction_InitializeLADWP", arguments509);
                Expression<?>[] arguments510 = new Expression<?>[1];
                arguments510[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition510 = new Expression<Boolean>(_17_0_5_edc0357_1345510114708_203082_14907_exists);
                elaborationRule510 = addElaborationRule(condition510, _17_0_5_edc0357_1345510113603_796245_13836.this, LADWP._17_0_5_edc0357_1345510113603_796245_13836._17_0_5_edc0357_1345510114708_203082_14907.class, "_AddStructuralFeatureValueAction_InitializeLADWP", arguments510);
                Expression<?>[] arguments511 = new Expression<?>[1];
                arguments511[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition511 = new Expression<Boolean>(_17_0_5_edc0357_1345510114707_62220_14903_exists);
                elaborationRule511 = addElaborationRule(condition511, _17_0_5_edc0357_1345510113603_796245_13836.this, LADWP._17_0_5_edc0357_1345510113603_796245_13836._17_0_5_edc0357_1345510114707_62220_14903.class, "_ReadSelfAction_InitializeLADWP", arguments511);
                Expression<?>[] arguments512 = new Expression<?>[1];
                arguments512[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition512 = new Expression<Boolean>(_17_0_5_edc0357_1345510114708_845512_14905_exists);
                elaborationRule512 = addElaborationRule(condition512, _17_0_5_edc0357_1345510113603_796245_13836.this, LADWP._17_0_5_edc0357_1345510113603_796245_13836._17_0_5_edc0357_1345510114708_845512_14905.class, "_AddStructuralFeatureValueAction_InitializeLADWP", arguments512);
                Expression<?>[] arguments513 = new Expression<?>[1];
                arguments513[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition513 = new Expression<Boolean>(_17_0_5_edc0357_1345510114709_216705_14913_exists);
                elaborationRule513 = addElaborationRule(condition513, _17_0_5_edc0357_1345510113603_796245_13836.this, LADWP._17_0_5_edc0357_1345510113603_796245_13836._17_0_5_edc0357_1345510114709_216705_14913.class, "_AddStructuralFeatureValueAction_InitializeLADWP", arguments513);
                Expression<?>[] arguments514 = new Expression<?>[1];
                arguments514[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition514 = new Expression<Boolean>(_17_0_5_edc0357_1345510114709_635836_14911_exists);
                elaborationRule514 = addElaborationRule(condition514, _17_0_5_edc0357_1345510113603_796245_13836.this, LADWP._17_0_5_edc0357_1345510113603_796245_13836._17_0_5_edc0357_1345510114709_635836_14911.class, "vszero_ValueSpecificationAction_InitializeLADWP", arguments514);
                Expression<?>[] arguments515 = new Expression<?>[1];
                arguments515[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition515 = new Expression<Boolean>(_17_0_5_edc0357_1345510114711_949447_14920_exists);
                elaborationRule515 = addElaborationRule(condition515, _17_0_5_edc0357_1345510113603_796245_13836.this, LADWP._17_0_5_edc0357_1345510113603_796245_13836._17_0_5_edc0357_1345510114711_949447_14920.class, "_AddStructuralFeatureValueAction_InitializeLADWP", arguments515);
                Expression<?>[] arguments516 = new Expression<?>[1];
                arguments516[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition516 = new Expression<Boolean>(_17_0_5_edc0357_1345510114709_602929_14909_exists);
                elaborationRule516 = addElaborationRule(condition516, _17_0_5_edc0357_1345510113603_796245_13836.this, LADWP._17_0_5_edc0357_1345510113603_796245_13836._17_0_5_edc0357_1345510114709_602929_14909.class, "_AddStructuralFeatureValueAction_InitializeLADWP", arguments516);
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
                Expression<Boolean> condition519 = new Expression<Boolean>(_17_0_5_edc0357_1345510114711_88466_14922_exists);
                elaborationRule519 = addElaborationRule(condition519, _17_0_5_edc0357_1345510113603_796245_13836.this, LADWP._17_0_5_edc0357_1345510113603_796245_13836._17_0_5_edc0357_1345510114711_88466_14922.class, "_AddStructuralFeatureValueAction_InitializeLADWP", arguments519);
                Expression<?>[] arguments520 = new Expression<?>[1];
                arguments520[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition520 = new Expression<Boolean>(_17_0_5_edc0357_1345510114709_561792_14910_exists);
                elaborationRule520 = addElaborationRule(condition520, _17_0_5_edc0357_1345510113603_796245_13836.this, LADWP._17_0_5_edc0357_1345510113603_796245_13836._17_0_5_edc0357_1345510114709_561792_14910.class, "_AddStructuralFeatureValueAction_InitializeLADWP", arguments520);
                Expression<?>[] arguments521 = new Expression<?>[1];
                arguments521[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition521 = new Expression<Boolean>(_17_0_5_edc0357_1345510114708_795071_14906_exists);
                elaborationRule521 = addElaborationRule(condition521, _17_0_5_edc0357_1345510113603_796245_13836.this, LADWP._17_0_5_edc0357_1345510113603_796245_13836._17_0_5_edc0357_1345510114708_795071_14906.class, "_AddStructuralFeatureValueAction_InitializeLADWP", arguments521);
                Expression<?>[] arguments522 = new Expression<?>[1];
                arguments522[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition522 = new Expression<Boolean>(_17_0_5_edc0357_1345510114707_796416_14904_exists);
                elaborationRule522 = addElaborationRule(condition522, _17_0_5_edc0357_1345510113603_796245_13836.this, LADWP._17_0_5_edc0357_1345510113603_796245_13836._17_0_5_edc0357_1345510114707_796416_14904.class, "_AddStructuralFeatureValueAction_InitializeLADWP", arguments522);
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

            public ConstraintExpression constraint523 = null;

            public Effect effect524 = null;

            public Object effect524Var = null;

            public Effect effect525 = null;

            public Object effect525Var = null;

            public Effect effect526 = null;

            public Object effect526Var = null;

            public Effect effect527 = null;

            public Object effect527Var = null;

            public Effect effect528 = null;

            public Object effect528Var = null;

            public Effect effect529 = null;

            public Object effect529Var = null;

            public Effect effect530 = null;

            public Object effect530Var = null;

            public Effect effect531 = null;

            public Object effect531Var = null;

            public Effect effect532 = null;

            public Object effect532Var = null;

            public Effect effect533 = null;

            public Object effect533Var = null;

            public void init_17_0_5_edc0357_1345510114710_727728_14917Members() {
                try {
                    _17_0_5_edc0357_1345510114707_62220_14903_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114707_62220_14903_endTime", this);
                    objectToPass = new Parameter<LADWP>("objectToPass", null, null, this);
                    constraint523 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114707_62220_14903_endTime)));
                    effect524Var = sig_17_0_5_edc0357_1345510114713_823361_14936;
                    effect524 = new EffectFunction(new FunctionCall((Object) effect524Var, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                    effect525Var = sig_17_0_5_edc0357_1345510114713_908211_14937;
                    effect525 = new EffectFunction(new FunctionCall((Object) effect525Var, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                    effect526Var = sig_17_0_5_edc0357_1345510114713_927688_14938;
                    effect526 = new EffectFunction(new FunctionCall((Object) effect526Var, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                    effect527Var = sig_17_0_5_edc0357_1345510114713_504410_14939;
                    effect527 = new EffectFunction(new FunctionCall((Object) effect527Var, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                    effect528Var = sig_17_0_5_edc0357_1345510114713_433084_14940;
                    effect528 = new EffectFunction(new FunctionCall((Object) effect528Var, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                    effect529Var = sig_17_0_5_edc0357_1345510114713_115246_14941;
                    effect529 = new EffectFunction(new FunctionCall((Object) effect529Var, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                    effect530Var = sig_17_0_5_edc0357_1345510114713_943749_14942;
                    effect530 = new EffectFunction(new FunctionCall((Object) effect530Var, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                    effect531Var = sig_17_0_5_edc0357_1345510114713_836896_14943;
                    effect531 = new EffectFunction(new FunctionCall((Object) effect531Var, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                    effect532Var = sig_17_0_5_edc0357_1345510114715_620321_14961;
                    effect532 = new EffectFunction(new FunctionCall((Object) effect532Var, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                    effect533Var = sig_17_0_5_edc0357_1345510114716_642816_14967;
                    effect533 = new EffectFunction(new FunctionCall((Object) effect533Var, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114710_727728_14917Collections() {
                parameters.add(_17_0_5_edc0357_1345510114707_62220_14903_endTime);
                parameters.add(objectToPass);
                constraintExpressions.add(constraint523);
                Set<Effect> effectsForeffect524Var = new HashSet<Effect>();
                effectsForeffect524Var.add(effect524);
                effects.put((Parameter<?>) effect524Var, effectsForeffect524Var);
                Set<Effect> effectsForeffect525Var = new HashSet<Effect>();
                effectsForeffect525Var.add(effect525);
                effects.put((Parameter<?>) effect525Var, effectsForeffect525Var);
                Set<Effect> effectsForeffect526Var = new HashSet<Effect>();
                effectsForeffect526Var.add(effect526);
                effects.put((Parameter<?>) effect526Var, effectsForeffect526Var);
                Set<Effect> effectsForeffect527Var = new HashSet<Effect>();
                effectsForeffect527Var.add(effect527);
                effects.put((Parameter<?>) effect527Var, effectsForeffect527Var);
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

            public ConstraintExpression constraint534 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114711_311747_14919_existsDependency = null;

            public ElaborationRule elaborationRule535 = null;

            public void init_17_0_5_edc0357_1345510114710_541046_14918Members() {
                try {
                    _17_0_5_edc0357_1345510114711_311747_14919_exists = new BooleanParameter("_17_0_5_edc0357_1345510114711_311747_14919_exists", this);
                    _17_0_5_edc0357_1345510114709_216705_14913_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114709_216705_14913_endTime", this);
                    constraint534 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114709_216705_14913_endTime)));
                    _17_0_5_edc0357_1345510114711_311747_14919_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114711_311747_14919_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114710_541046_14918Collections() {
                parameters.add(_17_0_5_edc0357_1345510114711_311747_14919_exists);
                parameters.add(_17_0_5_edc0357_1345510114709_216705_14913_endTime);
                constraintExpressions.add(constraint534);
                dependencies.add(_17_0_5_edc0357_1345510114711_311747_14919_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510114710_541046_14918Elaborations() {
                Expression<?>[] arguments535 = new Expression<?>[1];
                arguments535[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition535 = new Expression<Boolean>(_17_0_5_edc0357_1345510114711_311747_14919_exists);
                elaborationRule535 = addElaborationRule(condition535, _17_0_5_edc0357_1345510113603_796245_13836.this, LADWP._17_0_5_edc0357_1345510113603_796245_13836._17_0_5_edc0357_1345510114711_311747_14919.class, "_ActivityFinalNode_InitializeLADWP", arguments535);
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

            public ConstraintExpression constraint536 = null;

            public void init_17_0_5_edc0357_1345510114711_311747_14919Members() {
                try {
                    _17_0_5_edc0357_1345510114710_541046_14918_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114710_541046_14918_endTime", this);
                    constraint536 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114710_541046_14918_endTime)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114711_311747_14919Collections() {
                parameters.add(_17_0_5_edc0357_1345510114710_541046_14918_endTime);
                constraintExpressions.add(constraint536);
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

            public ConstraintExpression constraint537 = null;

            public Dependency< LADWP > _17_0_5_edc0357_1345510114996_973134_15447Dependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114995_46510_15446Dependency = null;

            public Effect effect538 = null;

            public Object effect538Var = null;

            public Effect effect539 = null;

            public Object effect539Var = null;

            public void init_17_0_5_edc0357_1345510114711_949447_14920Members() {
                try {
                    _17_0_5_edc0357_1345510114996_973134_15447 = new Parameter<LADWP>("_17_0_5_edc0357_1345510114996_973134_15447", null, null, this);
                    _17_0_5_edc0357_1345510114710_630744_14916_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114710_630744_14916_endTime", this);
                    _17_0_5_edc0357_1345510114995_46510_15446 = new BooleanParameter("_17_0_5_edc0357_1345510114995_46510_15446", this);
                    constraint537 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114710_630744_14916_endTime)));
                    _17_0_5_edc0357_1345510114996_973134_15447Dependency = new Dependency<LADWP>(_17_0_5_edc0357_1345510114996_973134_15447, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114715_620321_14961, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1345510114995_46510_15446Dependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114995_46510_15446, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114715_883477_14965, Utils.getMethodForArgTypes("ObjectFlow<Boolean>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    effect538Var = sig_17_0_5_edc0357_1345510114715_89246_14963;
                    effect538 = new EffectFunction(new FunctionCall((Object) effect538Var, Utils.getMethodForArgTypes("ObjectFlow<Object>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { true, startTime }));
                    effect539Var = demandResponse__17_0_5_edc0357_1345510113615_857599_13851;
                    effect539 = new EffectFunction(new FunctionCall((Object) effect539Var, Utils.getMethodForArgTypes("TimeVaryingMap<Boolean>", "generated", "setValue", java.lang.Integer.class, java.lang.Object.class), new Object[] { startTime, _17_0_5_edc0357_1345510114995_46510_15446 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114711_949447_14920Collections() {
                parameters.add(_17_0_5_edc0357_1345510114996_973134_15447);
                parameters.add(_17_0_5_edc0357_1345510114710_630744_14916_endTime);
                parameters.add(_17_0_5_edc0357_1345510114995_46510_15446);
                constraintExpressions.add(constraint537);
                dependencies.add(_17_0_5_edc0357_1345510114996_973134_15447Dependency);
                dependencies.add(_17_0_5_edc0357_1345510114995_46510_15446Dependency);
                Set<Effect> effectsForeffect538Var = new HashSet<Effect>();
                effectsForeffect538Var.add(effect538);
                effects.put((Parameter<?>) effect538Var, effectsForeffect538Var);
                Set<Effect> effectsForeffect539Var = new HashSet<Effect>();
                effectsForeffect539Var.add(effect539);
                effects.put((Parameter<?>) effect539Var, effectsForeffect539Var);
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

            public ConstraintExpression constraint540 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114711_155853_14923_existsDependency = null;

            public ElaborationRule elaborationRule541 = null;

            public void init_17_0_5_edc0357_1345510114711_700135_14921Members() {
                try {
                    _17_0_5_edc0357_1345510114711_155853_14923_exists = new BooleanParameter("_17_0_5_edc0357_1345510114711_155853_14923_exists", this);
                    _17_0_5_edc0357_1345510114710_630744_14916_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114710_630744_14916_endTime", this);
                    _17_0_5_edc0357_1345510114997_841873_15449 = new BooleanParameter("_17_0_5_edc0357_1345510114997_841873_15449", this);
                    constraint540 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114710_630744_14916_endTime)));
                    _17_0_5_edc0357_1345510114711_155853_14923_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114711_155853_14923_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114711_700135_14921Collections() {
                parameters.add(_17_0_5_edc0357_1345510114711_155853_14923_exists);
                parameters.add(_17_0_5_edc0357_1345510114710_630744_14916_endTime);
                parameters.add(_17_0_5_edc0357_1345510114997_841873_15449);
                constraintExpressions.add(constraint540);
                dependencies.add(_17_0_5_edc0357_1345510114711_155853_14923_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510114711_700135_14921Elaborations() {
                Expression<?>[] arguments541 = new Expression<?>[2];
                arguments541[0] = new Expression<Integer>(endTime);
                arguments541[1] = new Expression<Boolean>(_17_0_5_edc0357_1345510114997_841873_15449);
                Expression<Boolean> condition541 = new Expression<Boolean>(_17_0_5_edc0357_1345510114711_155853_14923_exists);
                elaborationRule541 = addElaborationRule(condition541, _17_0_5_edc0357_1345510113603_796245_13836.this, LADWP._17_0_5_edc0357_1345510113603_796245_13836._17_0_5_edc0357_1345510114711_155853_14923.class, "_ForkNode_InitializeLADWP", arguments541);
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

            public ConstraintExpression constraint542 = null;

            public Dependency< LADWP > _17_0_5_edc0357_1345510114998_119629_15451Dependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114997_709943_15450Dependency = null;

            public Effect effect543 = null;

            public Object effect543Var = null;

            public Effect effect544 = null;

            public Object effect544Var = null;

            public void init_17_0_5_edc0357_1345510114711_88466_14922Members() {
                try {
                    _17_0_5_edc0357_1345510114998_119629_15451 = new Parameter<LADWP>("_17_0_5_edc0357_1345510114998_119629_15451", null, null, this);
                    _17_0_5_edc0357_1345510114997_709943_15450 = new BooleanParameter("_17_0_5_edc0357_1345510114997_709943_15450", this);
                    _17_0_5_edc0357_1345510114710_630744_14916_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114710_630744_14916_endTime", this);
                    constraint542 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114710_630744_14916_endTime)));
                    _17_0_5_edc0357_1345510114998_119629_15451Dependency = new Dependency<LADWP>(_17_0_5_edc0357_1345510114998_119629_15451, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114716_642816_14967, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1345510114997_709943_15450Dependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114997_709943_15450, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114715_582865_14966, Utils.getMethodForArgTypes("ObjectFlow<Boolean>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    effect543Var = sig_17_0_5_edc0357_1345510114716_469957_14969;
                    effect543 = new EffectFunction(new FunctionCall((Object) effect543Var, Utils.getMethodForArgTypes("ObjectFlow<Object>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { true, startTime }));
                    effect544Var = shortage__17_0_5_edc0357_1345510113616_127059_13852;
                    effect544 = new EffectFunction(new FunctionCall((Object) effect544Var, Utils.getMethodForArgTypes("TimeVaryingMap<Boolean>", "generated", "setValue", java.lang.Integer.class, java.lang.Object.class), new Object[] { startTime, _17_0_5_edc0357_1345510114997_709943_15450 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114711_88466_14922Collections() {
                parameters.add(_17_0_5_edc0357_1345510114998_119629_15451);
                parameters.add(_17_0_5_edc0357_1345510114997_709943_15450);
                parameters.add(_17_0_5_edc0357_1345510114710_630744_14916_endTime);
                constraintExpressions.add(constraint542);
                dependencies.add(_17_0_5_edc0357_1345510114998_119629_15451Dependency);
                dependencies.add(_17_0_5_edc0357_1345510114997_709943_15450Dependency);
                Set<Effect> effectsForeffect543Var = new HashSet<Effect>();
                effectsForeffect543Var.add(effect543);
                effects.put((Parameter<?>) effect543Var, effectsForeffect543Var);
                Set<Effect> effectsForeffect544Var = new HashSet<Effect>();
                effectsForeffect544Var.add(effect544);
                effects.put((Parameter<?>) effect544Var, effectsForeffect544Var);
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

            public ConstraintExpression constraint545 = null;

            public Effect effect546 = null;

            public Object effect546Var = null;

            public Effect effect547 = null;

            public Object effect547Var = null;

            public void init_17_0_5_edc0357_1345510114711_155853_14923Members() {
                try {
                    objectToPass = new BooleanParameter("objectToPass", this);
                    _17_0_5_edc0357_1345510114711_700135_14921_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114711_700135_14921_endTime", this);
                    constraint545 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114711_700135_14921_endTime)));
                    effect546Var = sig_17_0_5_edc0357_1345510114715_883477_14965;
                    effect546 = new EffectFunction(new FunctionCall((Object) effect546Var, Utils.getMethodForArgTypes("ObjectFlow<Boolean>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                    effect547Var = sig_17_0_5_edc0357_1345510114715_582865_14966;
                    effect547 = new EffectFunction(new FunctionCall((Object) effect547Var, Utils.getMethodForArgTypes("ObjectFlow<Boolean>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114711_155853_14923Collections() {
                parameters.add(objectToPass);
                parameters.add(_17_0_5_edc0357_1345510114711_700135_14921_endTime);
                constraintExpressions.add(constraint545);
                Set<Effect> effectsForeffect546Var = new HashSet<Effect>();
                effectsForeffect546Var.add(effect546);
                effects.put((Parameter<?>) effect546Var, effectsForeffect546Var);
                Set<Effect> effectsForeffect547Var = new HashSet<Effect>();
                effectsForeffect547Var.add(effect547);
                effects.put((Parameter<?>) effect547Var, effectsForeffect547Var);
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

        public ElaborationRule elaborationRule548 = null;

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
            Expression<?>[] arguments548 = new Expression<?>[1];
            arguments548[0] = new Expression<Integer>(invoke_time);
            Expression<Boolean> condition548 = new Expression<Boolean>(true);
            elaborationRule548 = addElaborationRule(condition548, _17_0_5_edc0357_1346100467245_506694_13577.this, LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1346100503665_742649_13627.class, "_InitialNode_LADWP_CB", arguments548);
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

            public ConstraintExpression constraint549 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1348008482216_500972_14383_existsDependency = null;

            public ElaborationRule elaborationRule550 = null;

            public ElaborationRule elaborationRule551 = null;

            public void init_17_0_5_edc0357_1346100496379_482519_13614Members() {
                try {
                    _17_0_5_edc0357_1348008482216_500972_14383_exists = new BooleanParameter("_17_0_5_edc0357_1348008482216_500972_14383_exists", this);
                    _17_0_5_edc0357_1346100503665_742649_13627_endTime = new IntegerParameter("_17_0_5_edc0357_1346100503665_742649_13627_endTime", this);
                    cba_endTime = new IntegerParameter("cba_endTime", this);
                    constraint549 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346100503665_742649_13627_endTime)));
                    _17_0_5_edc0357_1348008482216_500972_14383_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1348008482216_500972_14383_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346100496379_482519_13614Collections() {
                parameters.add(_17_0_5_edc0357_1348008482216_500972_14383_exists);
                parameters.add(_17_0_5_edc0357_1346100503665_742649_13627_endTime);
                parameters.add(cba_endTime);
                constraintExpressions.add(constraint549);
                dependencies.add(_17_0_5_edc0357_1348008482216_500972_14383_existsDependency);
            }

            public void init_17_0_5_edc0357_1346100496379_482519_13614Elaborations() {
                Expression<?>[] arguments550 = new Expression<?>[1];
                arguments550[0] = new Expression<Integer>(cba_endTime);
                Expression<Boolean> condition550 = new Expression<Boolean>(true);
                elaborationRule550 = addElaborationRule(condition550, LADWP.this, LADWP._17_0_5_edc0357_1345510113603_796245_13836.class, "InitializeLADWP_Activity_LADWP", arguments550);
                Expression<?>[] arguments551 = new Expression<?>[1];
                arguments551[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition551 = new Expression<Boolean>(_17_0_5_edc0357_1348008482216_500972_14383_exists);
                elaborationRule551 = addElaborationRule(condition551, _17_0_5_edc0357_1346100467245_506694_13577.this, LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1348008482216_500972_14383.class, "_AcceptEventAction_LADWP_CB", arguments551);
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

            public ElaborationRule elaborationRule552 = null;

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
                Expression<?>[] arguments552 = new Expression<?>[1];
                arguments552[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition552 = new Expression<Boolean>(_17_0_5_edc0357_1346100496379_482519_13614_exists);
                elaborationRule552 = addElaborationRule(condition552, _17_0_5_edc0357_1346100467245_506694_13577.this, LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1346100496379_482519_13614.class, "initLADWP_CallBehaviorAction_LADWP_CB", arguments552);
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

            public ConstraintExpression constraint553 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1348163055082_50872_14559_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346100882824_187464_13736_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346101486727_93891_14069_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346101253799_271072_13939_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1348001296069_146013_14222_existsDependency = null;

            public ElaborationRule elaborationRule554 = null;

            public ElaborationRule elaborationRule555 = null;

            public ElaborationRule elaborationRule556 = null;

            public ElaborationRule elaborationRule557 = null;

            public ElaborationRule elaborationRule558 = null;

            public void init_17_0_5_edc0357_1346100685814_732050_13654Members() {
                try {
                    _17_0_5_edc0357_1348008482216_500972_14383_endTime = new IntegerParameter("_17_0_5_edc0357_1348008482216_500972_14383_endTime", this);
                    _17_0_5_edc0357_1348163055082_50872_14559_exists = new BooleanParameter("_17_0_5_edc0357_1348163055082_50872_14559_exists", this);
                    _17_0_5_edc0357_1346100882824_187464_13736_exists = new BooleanParameter("_17_0_5_edc0357_1346100882824_187464_13736_exists", this);
                    _17_0_5_edc0357_1346101486727_93891_14069_exists = new BooleanParameter("_17_0_5_edc0357_1346101486727_93891_14069_exists", this);
                    _17_0_5_edc0357_1346101253799_271072_13939_exists = new BooleanParameter("_17_0_5_edc0357_1346101253799_271072_13939_exists", this);
                    _17_0_5_edc0357_1348001296069_146013_14222_exists = new BooleanParameter("_17_0_5_edc0357_1348001296069_146013_14222_exists", this);
                    constraint553 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1348008482216_500972_14383_endTime)));
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
                constraintExpressions.add(constraint553);
                dependencies.add(_17_0_5_edc0357_1348163055082_50872_14559_existsDependency);
                dependencies.add(_17_0_5_edc0357_1346100882824_187464_13736_existsDependency);
                dependencies.add(_17_0_5_edc0357_1346101486727_93891_14069_existsDependency);
                dependencies.add(_17_0_5_edc0357_1346101253799_271072_13939_existsDependency);
                dependencies.add(_17_0_5_edc0357_1348001296069_146013_14222_existsDependency);
            }

            public void init_17_0_5_edc0357_1346100685814_732050_13654Elaborations() {
                Expression<?>[] arguments554 = new Expression<?>[1];
                arguments554[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition554 = new Expression<Boolean>(_17_0_5_edc0357_1346101486727_93891_14069_exists);
                elaborationRule554 = addElaborationRule(condition554, _17_0_5_edc0357_1346100467245_506694_13577.this, LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1346101486727_93891_14069.class, "mrrgr_MergeNode_LADWP_CB", arguments554);
                Expression<?>[] arguments555 = new Expression<?>[1];
                arguments555[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition555 = new Expression<Boolean>(_17_0_5_edc0357_1346100882824_187464_13736_exists);
                elaborationRule555 = addElaborationRule(condition555, _17_0_5_edc0357_1346100467245_506694_13577.this, LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1346100882824_187464_13736.class, "mnrmr_MergeNode_LADWP_CB", arguments555);
                Expression<?>[] arguments556 = new Expression<?>[1];
                arguments556[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition556 = new Expression<Boolean>(_17_0_5_edc0357_1346101253799_271072_13939_exists);
                elaborationRule556 = addElaborationRule(condition556, _17_0_5_edc0357_1346100467245_506694_13577.this, LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1346101253799_271072_13939.class, "mnrlr_MergeNode_LADWP_CB", arguments556);
                Expression<?>[] arguments557 = new Expression<?>[1];
                arguments557[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition557 = new Expression<Boolean>(_17_0_5_edc0357_1348163055082_50872_14559_exists);
                elaborationRule557 = addElaborationRule(condition557, _17_0_5_edc0357_1346100467245_506694_13577.this, LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1348163055082_50872_14559.class, "_AcceptEventAction_LADWP_CB", arguments557);
                Expression<?>[] arguments558 = new Expression<?>[1];
                arguments558[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition558 = new Expression<Boolean>(_17_0_5_edc0357_1348001296069_146013_14222_exists);
                elaborationRule558 = addElaborationRule(condition558, _17_0_5_edc0357_1346100467245_506694_13577.this, LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1348001296069_146013_14222.class, "_AcceptEventAction_LADWP_CB", arguments558);
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

            public ConstraintExpression constraint559 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346100882824_187464_13736_existsDependency = null;

            public ElaborationRule elaborationRule560 = null;

            public ElaborationRule elaborationRule561 = null;

            public void init_17_0_5_edc0357_1346100727841_589225_13670Members() {
                try {
                    _17_0_5_edc0357_1346100882824_187464_13736_exists = new BooleanParameter("_17_0_5_edc0357_1346100882824_187464_13736_exists", this);
                    _17_0_5_edc0357_1346100922767_721473_13763 = new IntegerParameter("_17_0_5_edc0357_1346100922767_721473_13763", this);
                    _17_0_5_edc0357_1346100747196_888109_13697_endTime = new IntegerParameter("_17_0_5_edc0357_1346100747196_888109_13697_endTime", this);
                    cba_endTime = new IntegerParameter("cba_endTime", this);
                    constraint559 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346100747196_888109_13697_endTime)));
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
                constraintExpressions.add(constraint559);
                dependencies.add(_17_0_5_edc0357_1346100882824_187464_13736_existsDependency);
            }

            public void init_17_0_5_edc0357_1346100727841_589225_13670Elaborations() {
                Expression<?>[] arguments560 = new Expression<?>[1];
                arguments560[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition560 = new Expression<Boolean>(_17_0_5_edc0357_1346100882824_187464_13736_exists);
                elaborationRule560 = addElaborationRule(condition560, _17_0_5_edc0357_1346100467245_506694_13577.this, LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1346100882824_187464_13736.class, "mnrmr_MergeNode_LADWP_CB", arguments560);
                Expression<?>[] arguments561 = new Expression<?>[2];
                arguments561[0] = new Expression<Integer>(cba_endTime);
                arguments561[1] = new Expression<Object>(_17_0_5_edc0357_1346100922767_721473_13763);
                Expression<Boolean> condition561 = new Expression<Boolean>(true);
                elaborationRule561 = addElaborationRule(condition561, LADWP.this, LADWP._17_0_5_edc0357_1345510113596_603128_13828.class, "updateReportedLoad_Activity_LADWP", arguments561);
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

            public ConstraintExpression constraint562 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346100747196_888109_13697_existsDependency = null;

            public Dependency< Power_System.SignalreceiveMeterReading > _17_0_5_edc0357_1346100752836_305657_13711Dependency = null;

            public ElaborationRule elaborationRule563 = null;

            public void init_17_0_5_edc0357_1346100738628_763629_13683Members() {
                try {
                    _17_0_5_edc0357_1346100747196_888109_13697_exists = new BooleanParameter("_17_0_5_edc0357_1346100747196_888109_13697_exists", this);
                    _17_0_5_edc0357_1346100752836_305657_13711 = new Parameter<Power_System.SignalreceiveMeterReading>("_17_0_5_edc0357_1346100752836_305657_13711", null, null, this);
                    _17_0_5_edc0357_1346100882824_187464_13736_endTime = new IntegerParameter("_17_0_5_edc0357_1346100882824_187464_13736_endTime", this);
                    constraint562 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346100882824_187464_13736_endTime)));
                    _17_0_5_edc0357_1346100747196_888109_13697_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346100747196_888109_13697_exists, new Expression<Boolean>(true));
                    _17_0_5_edc0357_1346100752836_305657_13711Dependency = new Dependency<Power_System.SignalreceiveMeterReading>(_17_0_5_edc0357_1346100752836_305657_13711, new Expression(new FunctionCall((Object) q_LADWP_receiveMeterReading, Utils.getMethodForArgTypes("ObjectFlow<SignalreceiveMeterReading>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346100738628_763629_13683Collections() {
                parameters.add(_17_0_5_edc0357_1346100747196_888109_13697_exists);
                parameters.add(_17_0_5_edc0357_1346100752836_305657_13711);
                parameters.add(_17_0_5_edc0357_1346100882824_187464_13736_endTime);
                constraintExpressions.add(constraint562);
                dependencies.add(_17_0_5_edc0357_1346100747196_888109_13697_existsDependency);
                dependencies.add(_17_0_5_edc0357_1346100752836_305657_13711Dependency);
            }

            public void init_17_0_5_edc0357_1346100738628_763629_13683Elaborations() {
                Expression<?>[] arguments563 = new Expression<?>[2];
                arguments563[0] = new Expression<Integer>(endTime);
                arguments563[1] = new Expression<Power_System.SignalreceiveMeterReading>(_17_0_5_edc0357_1346100752836_305657_13711);
                Expression<Boolean> condition563 = new Expression<Boolean>(_17_0_5_edc0357_1346100747196_888109_13697_exists);
                elaborationRule563 = addElaborationRule(condition563, _17_0_5_edc0357_1346100467245_506694_13577.this, LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1346100747196_888109_13697.class, "readMeterVal_ReadStructuralFeatureAction_LADWP_CB", arguments563);
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

            public ConstraintExpression constraint564 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346100727841_589225_13670_existsDependency = null;

            public Dependency< Integer > _17_0_5_edc0357_1346100827593_120288_13720Dependency = null;

            public ElaborationRule elaborationRule565 = null;

            public void init_17_0_5_edc0357_1346100747196_888109_13697Members() {
                try {
                    _17_0_5_edc0357_1346100727841_589225_13670_exists = new BooleanParameter("_17_0_5_edc0357_1346100727841_589225_13670_exists", this);
                    _17_0_5_edc0357_1346100796330_896110_13719 = new Parameter<Power_System.SignalreceiveMeterReading>("_17_0_5_edc0357_1346100796330_896110_13719", null, null, this);
                    _17_0_5_edc0357_1346100738628_763629_13683_endTime = new IntegerParameter("_17_0_5_edc0357_1346100738628_763629_13683_endTime", this);
                    _17_0_5_edc0357_1346100827593_120288_13720 = new IntegerParameter("_17_0_5_edc0357_1346100827593_120288_13720", this);
                    constraint564 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346100738628_763629_13683_endTime)));
                    _17_0_5_edc0357_1346100727841_589225_13670_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346100727841_589225_13670_exists, new Expression<Boolean>(true));
                    _17_0_5_edc0357_1346100827593_120288_13720Dependency = new Dependency<Integer>(_17_0_5_edc0357_1346100827593_120288_13720, new Expression(new FunctionCall((Object) (((Parameter<?>) (_17_0_5_edc0357_1346100796330_896110_13719.getMember("meter_value__17_0_5_edc0357_1345510113616_372105_13853"))).getValue()), Utils.getMethodForArgTypes("LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1346100747196_888109_13697", "generated", "getValue"), new Object[] { new Expression<Integer>(startTime) })));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346100747196_888109_13697Collections() {
                parameters.add(_17_0_5_edc0357_1346100727841_589225_13670_exists);
                parameters.add(_17_0_5_edc0357_1346100796330_896110_13719);
                parameters.add(_17_0_5_edc0357_1346100738628_763629_13683_endTime);
                parameters.add(_17_0_5_edc0357_1346100827593_120288_13720);
                constraintExpressions.add(constraint564);
                dependencies.add(_17_0_5_edc0357_1346100727841_589225_13670_existsDependency);
                dependencies.add(_17_0_5_edc0357_1346100827593_120288_13720Dependency);
            }

            public void init_17_0_5_edc0357_1346100747196_888109_13697Elaborations() {
                Expression<?>[] arguments565 = new Expression<?>[2];
                arguments565[0] = new Expression<Integer>(endTime);
                arguments565[1] = new Expression<Integer>(_17_0_5_edc0357_1346100827593_120288_13720);
                Expression<Boolean> condition565 = new Expression<Boolean>(_17_0_5_edc0357_1346100727841_589225_13670_exists);
                elaborationRule565 = addElaborationRule(condition565, _17_0_5_edc0357_1346100467245_506694_13577.this, LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1346100727841_589225_13670.class, "updateRepLoad_CallBehaviorAction_LADWP_CB", arguments565);
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

            public ConstraintExpression constraint566 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346100738628_763629_13683_existsDependency = null;

            public ElaborationRule elaborationRule567 = null;

            public void init_17_0_5_edc0357_1346100882824_187464_13736Members() {
                try {
                    invoker_endTime = new IntegerParameter("invoker_endTime", this);
                    _17_0_5_edc0357_1346100738628_763629_13683_exists = new BooleanParameter("_17_0_5_edc0357_1346100738628_763629_13683_exists", this);
                    constraint566 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(invoker_endTime)));
                    _17_0_5_edc0357_1346100738628_763629_13683_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346100738628_763629_13683_exists, new Expression(new FunctionCall((Object) q_LADWP_receiveMeterReading, Utils.getMethodForArgTypes("ObjectFlow<SignalreceiveMeterReading>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346100882824_187464_13736Collections() {
                parameters.add(invoker_endTime);
                parameters.add(_17_0_5_edc0357_1346100738628_763629_13683_exists);
                constraintExpressions.add(constraint566);
                dependencies.add(_17_0_5_edc0357_1346100738628_763629_13683_existsDependency);
            }

            public void init_17_0_5_edc0357_1346100882824_187464_13736Elaborations() {
                Expression<?>[] arguments567 = new Expression<?>[1];
                arguments567[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition567 = new Expression<Boolean>(_17_0_5_edc0357_1346100738628_763629_13683_exists);
                elaborationRule567 = addElaborationRule(condition567, _17_0_5_edc0357_1346100467245_506694_13577.this, LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1346100738628_763629_13683.class, "_AcceptEventAction_LADWP_CB", arguments567);
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

            public ConstraintExpression constraint568 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346101253799_271072_13939_existsDependency = null;

            public ElaborationRule elaborationRule569 = null;

            public ElaborationRule elaborationRule570 = null;

            public void init_17_0_5_edc0357_1346100976618_530123_13805Members() {
                try {
                    _17_0_5_edc0357_1346100986924_922768_13821_endTime = new IntegerParameter("_17_0_5_edc0357_1346100986924_922768_13821_endTime", this);
                    _17_0_5_edc0357_1346101253799_271072_13939_exists = new BooleanParameter("_17_0_5_edc0357_1346101253799_271072_13939_exists", this);
                    _17_0_5_edc0357_1346101090161_713198_13875 = new IntegerParameter("_17_0_5_edc0357_1346101090161_713198_13875", this);
                    cba_endTime = new IntegerParameter("cba_endTime", this);
                    constraint568 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346100986924_922768_13821_endTime)));
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
                constraintExpressions.add(constraint568);
                dependencies.add(_17_0_5_edc0357_1346101253799_271072_13939_existsDependency);
            }

            public void init_17_0_5_edc0357_1346100976618_530123_13805Elaborations() {
                Expression<?>[] arguments569 = new Expression<?>[1];
                arguments569[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition569 = new Expression<Boolean>(_17_0_5_edc0357_1346101253799_271072_13939_exists);
                elaborationRule569 = addElaborationRule(condition569, _17_0_5_edc0357_1346100467245_506694_13577.this, LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1346101253799_271072_13939.class, "mnrlr_MergeNode_LADWP_CB", arguments569);
                Expression<?>[] arguments570 = new Expression<?>[2];
                arguments570[0] = new Expression<Integer>(cba_endTime);
                arguments570[1] = new Expression<Integer>(_17_0_5_edc0357_1346101090161_713198_13875);
                Expression<Boolean> condition570 = new Expression<Boolean>(true);
                elaborationRule570 = addElaborationRule(condition570, LADWP.this, LADWP._17_0_5_edc0357_1345510113597_14205_13829.class, "updateActualLoad_Activity_LADWP", arguments570);
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

            public ConstraintExpression constraint571 = null;

            public Dependency< Integer > _17_0_5_edc0357_1346101040421_157561_13857Dependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346100976618_530123_13805_existsDependency = null;

            public ElaborationRule elaborationRule572 = null;

            public void init_17_0_5_edc0357_1346100986924_922768_13821Members() {
                try {
                    _17_0_5_edc0357_1346100990557_575122_13835_endTime = new IntegerParameter("_17_0_5_edc0357_1346100990557_575122_13835_endTime", this);
                    _17_0_5_edc0357_1346101040421_157561_13857 = new IntegerParameter("_17_0_5_edc0357_1346101040421_157561_13857", this);
                    _17_0_5_edc0357_1346101057612_324428_13858 = new Parameter<Power_System.SignalreceiveLoadReading>("_17_0_5_edc0357_1346101057612_324428_13858", null, null, this);
                    _17_0_5_edc0357_1346100976618_530123_13805_exists = new BooleanParameter("_17_0_5_edc0357_1346100976618_530123_13805_exists", this);
                    constraint571 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346100990557_575122_13835_endTime)));
                    _17_0_5_edc0357_1346101040421_157561_13857Dependency = new Dependency<Integer>(_17_0_5_edc0357_1346101040421_157561_13857, new Expression(new FunctionCall((Object) (((Parameter<?>) (_17_0_5_edc0357_1346101057612_324428_13858.getMember("actual_load__17_0_5_edc0357_1345510113617_570582_13854"))).getValue()), Utils.getMethodForArgTypes("LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1346100986924_922768_13821", "generated", "getValue"), new Object[] { new Expression<Integer>(startTime) })));
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
                constraintExpressions.add(constraint571);
                dependencies.add(_17_0_5_edc0357_1346101040421_157561_13857Dependency);
                dependencies.add(_17_0_5_edc0357_1346100976618_530123_13805_existsDependency);
            }

            public void init_17_0_5_edc0357_1346100986924_922768_13821Elaborations() {
                Expression<?>[] arguments572 = new Expression<?>[2];
                arguments572[0] = new Expression<Integer>(endTime);
                arguments572[1] = new Expression<Integer>(_17_0_5_edc0357_1346101040421_157561_13857);
                Expression<Boolean> condition572 = new Expression<Boolean>(_17_0_5_edc0357_1346100976618_530123_13805_exists);
                elaborationRule572 = addElaborationRule(condition572, _17_0_5_edc0357_1346100467245_506694_13577.this, LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1346100976618_530123_13805.class, "updateActLoad_CallBehaviorAction_LADWP_CB", arguments572);
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

            public ConstraintExpression constraint573 = null;

            public Dependency< Power_System.SignalreceiveLoadReading > _17_0_5_edc0357_1346101014528_680095_13851Dependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346100986924_922768_13821_existsDependency = null;

            public ElaborationRule elaborationRule574 = null;

            public void init_17_0_5_edc0357_1346100990557_575122_13835Members() {
                try {
                    _17_0_5_edc0357_1346101014528_680095_13851 = new Parameter<Power_System.SignalreceiveLoadReading>("_17_0_5_edc0357_1346101014528_680095_13851", null, null, this);
                    _17_0_5_edc0357_1346100986924_922768_13821_exists = new BooleanParameter("_17_0_5_edc0357_1346100986924_922768_13821_exists", this);
                    _17_0_5_edc0357_1346101253799_271072_13939_endTime = new IntegerParameter("_17_0_5_edc0357_1346101253799_271072_13939_endTime", this);
                    constraint573 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346101253799_271072_13939_endTime)));
                    _17_0_5_edc0357_1346101014528_680095_13851Dependency = new Dependency<Power_System.SignalreceiveLoadReading>(_17_0_5_edc0357_1346101014528_680095_13851, new Expression(new FunctionCall((Object) q_LADWP_receiveLoadReading, Utils.getMethodForArgTypes("ObjectFlow<SignalreceiveLoadReading>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1346100986924_922768_13821_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346100986924_922768_13821_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346100990557_575122_13835Collections() {
                parameters.add(_17_0_5_edc0357_1346101014528_680095_13851);
                parameters.add(_17_0_5_edc0357_1346100986924_922768_13821_exists);
                parameters.add(_17_0_5_edc0357_1346101253799_271072_13939_endTime);
                constraintExpressions.add(constraint573);
                dependencies.add(_17_0_5_edc0357_1346101014528_680095_13851Dependency);
                dependencies.add(_17_0_5_edc0357_1346100986924_922768_13821_existsDependency);
            }

            public void init_17_0_5_edc0357_1346100990557_575122_13835Elaborations() {
                Expression<?>[] arguments574 = new Expression<?>[2];
                arguments574[0] = new Expression<Integer>(endTime);
                arguments574[1] = new Expression<Power_System.SignalreceiveLoadReading>(_17_0_5_edc0357_1346101014528_680095_13851);
                Expression<Boolean> condition574 = new Expression<Boolean>(_17_0_5_edc0357_1346100986924_922768_13821_exists);
                elaborationRule574 = addElaborationRule(condition574, _17_0_5_edc0357_1346100467245_506694_13577.this, LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1346100986924_922768_13821.class, "readActualLoad_ReadStructuralFeatureAction_LADWP_CB", arguments574);
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

            public ConstraintExpression constraint575 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346100990557_575122_13835_existsDependency = null;

            public ElaborationRule elaborationRule576 = null;

            public void init_17_0_5_edc0357_1346101253799_271072_13939Members() {
                try {
                    invoker_endTime = new IntegerParameter("invoker_endTime", this);
                    _17_0_5_edc0357_1346100990557_575122_13835_exists = new BooleanParameter("_17_0_5_edc0357_1346100990557_575122_13835_exists", this);
                    constraint575 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(invoker_endTime)));
                    _17_0_5_edc0357_1346100990557_575122_13835_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346100990557_575122_13835_exists, new Expression(new FunctionCall((Object) q_LADWP_receiveLoadReading, Utils.getMethodForArgTypes("ObjectFlow<SignalreceiveLoadReading>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346101253799_271072_13939Collections() {
                parameters.add(invoker_endTime);
                parameters.add(_17_0_5_edc0357_1346100990557_575122_13835_exists);
                constraintExpressions.add(constraint575);
                dependencies.add(_17_0_5_edc0357_1346100990557_575122_13835_existsDependency);
            }

            public void init_17_0_5_edc0357_1346101253799_271072_13939Elaborations() {
                Expression<?>[] arguments576 = new Expression<?>[1];
                arguments576[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition576 = new Expression<Boolean>(_17_0_5_edc0357_1346100990557_575122_13835_exists);
                elaborationRule576 = addElaborationRule(condition576, _17_0_5_edc0357_1346100467245_506694_13577.this, LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1346100990557_575122_13835.class, "_AcceptEventAction_LADWP_CB", arguments576);
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

            public ConstraintExpression constraint577 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346101350907_438807_13981_existsDependency = null;

            public Dependency< Power_System.SignalreceiveGenReading > _17_0_5_edc0357_1346101342221_614382_13971Dependency = null;

            public ElaborationRule elaborationRule578 = null;

            public void init_17_0_5_edc0357_1346101323493_940716_13958Members() {
                try {
                    _17_0_5_edc0357_1346101486727_93891_14069_endTime = new IntegerParameter("_17_0_5_edc0357_1346101486727_93891_14069_endTime", this);
                    _17_0_5_edc0357_1346101350907_438807_13981_exists = new BooleanParameter("_17_0_5_edc0357_1346101350907_438807_13981_exists", this);
                    _17_0_5_edc0357_1346101342221_614382_13971 = new Parameter<Power_System.SignalreceiveGenReading>("_17_0_5_edc0357_1346101342221_614382_13971", null, null, this);
                    constraint577 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346101486727_93891_14069_endTime)));
                    _17_0_5_edc0357_1346101350907_438807_13981_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346101350907_438807_13981_exists, new Expression<Boolean>(true));
                    _17_0_5_edc0357_1346101342221_614382_13971Dependency = new Dependency<Power_System.SignalreceiveGenReading>(_17_0_5_edc0357_1346101342221_614382_13971, new Expression(new FunctionCall((Object) q_LADWP_receiveGenReading, Utils.getMethodForArgTypes("ObjectFlow<SignalreceiveGenReading>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346101323493_940716_13958Collections() {
                parameters.add(_17_0_5_edc0357_1346101486727_93891_14069_endTime);
                parameters.add(_17_0_5_edc0357_1346101350907_438807_13981_exists);
                parameters.add(_17_0_5_edc0357_1346101342221_614382_13971);
                constraintExpressions.add(constraint577);
                dependencies.add(_17_0_5_edc0357_1346101350907_438807_13981_existsDependency);
                dependencies.add(_17_0_5_edc0357_1346101342221_614382_13971Dependency);
            }

            public void init_17_0_5_edc0357_1346101323493_940716_13958Elaborations() {
                Expression<?>[] arguments578 = new Expression<?>[2];
                arguments578[0] = new Expression<Integer>(endTime);
                arguments578[1] = new Expression<Power_System.SignalreceiveGenReading>(_17_0_5_edc0357_1346101342221_614382_13971);
                Expression<Boolean> condition578 = new Expression<Boolean>(_17_0_5_edc0357_1346101350907_438807_13981_exists);
                elaborationRule578 = addElaborationRule(condition578, _17_0_5_edc0357_1346100467245_506694_13577.this, LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1346101350907_438807_13981.class, "readActualPower_ReadStructuralFeatureAction_LADWP_CB", arguments578);
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

            public ConstraintExpression constraint579 = null;

            public Dependency< Integer > _17_0_5_edc0357_1346101376276_801916_13995Dependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346101429843_239121_14015_existsDependency = null;

            public ElaborationRule elaborationRule580 = null;

            public void init_17_0_5_edc0357_1346101350907_438807_13981Members() {
                try {
                    _17_0_5_edc0357_1346101323493_940716_13958_endTime = new IntegerParameter("_17_0_5_edc0357_1346101323493_940716_13958_endTime", this);
                    _17_0_5_edc0357_1346101376276_801916_13995 = new IntegerParameter("_17_0_5_edc0357_1346101376276_801916_13995", this);
                    _17_0_5_edc0357_1346101429843_239121_14015_exists = new BooleanParameter("_17_0_5_edc0357_1346101429843_239121_14015_exists", this);
                    _17_0_5_edc0357_1346101356139_450740_13994 = new Parameter<Power_System.SignalreceiveGenReading>("_17_0_5_edc0357_1346101356139_450740_13994", null, null, this);
                    constraint579 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346101323493_940716_13958_endTime)));
                    _17_0_5_edc0357_1346101376276_801916_13995Dependency = new Dependency<Integer>(_17_0_5_edc0357_1346101376276_801916_13995, new Expression(new FunctionCall((Object) (((Parameter<?>) (_17_0_5_edc0357_1346101356139_450740_13994.getMember("actual_power__17_0_5_edc0357_1345510113617_469482_13855"))).getValue()), Utils.getMethodForArgTypes("LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1346101350907_438807_13981", "generated", "getValue"), new Object[] { new Expression<Integer>(startTime) })));
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
                constraintExpressions.add(constraint579);
                dependencies.add(_17_0_5_edc0357_1346101376276_801916_13995Dependency);
                dependencies.add(_17_0_5_edc0357_1346101429843_239121_14015_existsDependency);
            }

            public void init_17_0_5_edc0357_1346101350907_438807_13981Elaborations() {
                Expression<?>[] arguments580 = new Expression<?>[2];
                arguments580[0] = new Expression<Integer>(endTime);
                arguments580[1] = new Expression<Integer>(_17_0_5_edc0357_1346101376276_801916_13995);
                Expression<Boolean> condition580 = new Expression<Boolean>(_17_0_5_edc0357_1346101429843_239121_14015_exists);
                elaborationRule580 = addElaborationRule(condition580, _17_0_5_edc0357_1346100467245_506694_13577.this, LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1346101429843_239121_14015.class, "updateGenVal_CallBehaviorAction_LADWP_CB", arguments580);
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

            public ConstraintExpression constraint581 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346101486727_93891_14069_existsDependency = null;

            public ElaborationRule elaborationRule582 = null;

            public ElaborationRule elaborationRule583 = null;

            public void init_17_0_5_edc0357_1346101429843_239121_14015Members() {
                try {
                    _17_0_5_edc0357_1346101486727_93891_14069_exists = new BooleanParameter("_17_0_5_edc0357_1346101486727_93891_14069_exists", this);
                    _17_0_5_edc0357_1346101437766_368293_14030 = new IntegerParameter("_17_0_5_edc0357_1346101437766_368293_14030", this);
                    _17_0_5_edc0357_1346101350907_438807_13981_endTime = new IntegerParameter("_17_0_5_edc0357_1346101350907_438807_13981_endTime", this);
                    cba_endTime = new IntegerParameter("cba_endTime", this);
                    constraint581 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346101350907_438807_13981_endTime)));
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
                constraintExpressions.add(constraint581);
                dependencies.add(_17_0_5_edc0357_1346101486727_93891_14069_existsDependency);
            }

            public void init_17_0_5_edc0357_1346101429843_239121_14015Elaborations() {
                Expression<?>[] arguments582 = new Expression<?>[1];
                arguments582[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition582 = new Expression<Boolean>(_17_0_5_edc0357_1346101486727_93891_14069_exists);
                elaborationRule582 = addElaborationRule(condition582, _17_0_5_edc0357_1346100467245_506694_13577.this, LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1346101486727_93891_14069.class, "mrrgr_MergeNode_LADWP_CB", arguments582);
                Expression<?>[] arguments583 = new Expression<?>[2];
                arguments583[0] = new Expression<Integer>(cba_endTime);
                arguments583[1] = new Expression<Integer>(_17_0_5_edc0357_1346101437766_368293_14030);
                Expression<Boolean> condition583 = new Expression<Boolean>(true);
                elaborationRule583 = addElaborationRule(condition583, LADWP.this, LADWP._17_0_5_edc0357_1345510113598_693480_13830.class, "updateGeneration_Activity_LADWP", arguments583);
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

            public ConstraintExpression constraint584 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346101323493_940716_13958_existsDependency = null;

            public ElaborationRule elaborationRule585 = null;

            public void init_17_0_5_edc0357_1346101486727_93891_14069Members() {
                try {
                    invoker_endTime = new IntegerParameter("invoker_endTime", this);
                    _17_0_5_edc0357_1346101323493_940716_13958_exists = new BooleanParameter("_17_0_5_edc0357_1346101323493_940716_13958_exists", this);
                    constraint584 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(invoker_endTime)));
                    _17_0_5_edc0357_1346101323493_940716_13958_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346101323493_940716_13958_exists, new Expression(new FunctionCall((Object) q_LADWP_receiveGenReading, Utils.getMethodForArgTypes("ObjectFlow<SignalreceiveGenReading>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346101486727_93891_14069Collections() {
                parameters.add(invoker_endTime);
                parameters.add(_17_0_5_edc0357_1346101323493_940716_13958_exists);
                constraintExpressions.add(constraint584);
                dependencies.add(_17_0_5_edc0357_1346101323493_940716_13958_existsDependency);
            }

            public void init_17_0_5_edc0357_1346101486727_93891_14069Elaborations() {
                Expression<?>[] arguments585 = new Expression<?>[1];
                arguments585[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition585 = new Expression<Boolean>(_17_0_5_edc0357_1346101323493_940716_13958_exists);
                elaborationRule585 = addElaborationRule(condition585, _17_0_5_edc0357_1346100467245_506694_13577.this, LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1346101323493_940716_13958.class, "_AcceptEventAction_LADWP_CB", arguments585);
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

            public ConstraintExpression constraint586 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346103876583_585391_14750_existsDependency = null;

            public ElaborationRule elaborationRule587 = null;

            public ElaborationRule elaborationRule588 = null;

            public void init_17_0_5_edc0357_1346101533989_302317_14094Members() {
                try {
                    _17_0_5_edc0357_1346103876583_585391_14750_exists = new BooleanParameter("_17_0_5_edc0357_1346103876583_585391_14750_exists", this);
                    _17_0_5_edc0357_1346101765241_743667_14225_endTime = new IntegerParameter("_17_0_5_edc0357_1346101765241_743667_14225_endTime", this);
                    cba_endTime = new IntegerParameter("cba_endTime", this);
                    constraint586 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346101765241_743667_14225_endTime)));
                    _17_0_5_edc0357_1346103876583_585391_14750_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346103876583_585391_14750_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346101533989_302317_14094Collections() {
                parameters.add(_17_0_5_edc0357_1346103876583_585391_14750_exists);
                parameters.add(_17_0_5_edc0357_1346101765241_743667_14225_endTime);
                parameters.add(cba_endTime);
                constraintExpressions.add(constraint586);
                dependencies.add(_17_0_5_edc0357_1346103876583_585391_14750_existsDependency);
            }

            public void init_17_0_5_edc0357_1346101533989_302317_14094Elaborations() {
                Expression<?>[] arguments587 = new Expression<?>[1];
                arguments587[0] = new Expression<Integer>(cba_endTime);
                Expression<Boolean> condition587 = new Expression<Boolean>(true);
                elaborationRule587 = addElaborationRule(condition587, LADWP.this, LADWP._17_0_5_edc0357_1345510113599_525430_13831.class, "monitor_system_Activity_LADWP", arguments587);
                Expression<?>[] arguments588 = new Expression<?>[1];
                arguments588[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition588 = new Expression<Boolean>(_17_0_5_edc0357_1346103876583_585391_14750_exists);
                elaborationRule588 = addElaborationRule(condition588, _17_0_5_edc0357_1346100467245_506694_13577.this, LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1346103876583_585391_14750.class, "readself_ReadSelfAction_LADWP_CB", arguments588);
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

            public ConstraintExpression constraint589 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346102209293_902398_14297_existsDependency = null;

            public ElaborationRule elaborationRule590 = null;

            public ElaborationRule elaborationRule591 = null;

            public void init_17_0_5_edc0357_1346101621154_926007_14131Members() {
                try {
                    _17_0_5_edc0357_1346103858168_44487_14734_endTime = new IntegerParameter("_17_0_5_edc0357_1346103858168_44487_14734_endTime", this);
                    _17_0_5_edc0357_1346102209293_902398_14297_exists = new BooleanParameter("_17_0_5_edc0357_1346102209293_902398_14297_exists", this);
                    cba_endTime = new IntegerParameter("cba_endTime", this);
                    constraint589 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346103858168_44487_14734_endTime)));
                    _17_0_5_edc0357_1346102209293_902398_14297_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346102209293_902398_14297_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346101621154_926007_14131Collections() {
                parameters.add(_17_0_5_edc0357_1346103858168_44487_14734_endTime);
                parameters.add(_17_0_5_edc0357_1346102209293_902398_14297_exists);
                parameters.add(cba_endTime);
                constraintExpressions.add(constraint589);
                dependencies.add(_17_0_5_edc0357_1346102209293_902398_14297_existsDependency);
            }

            public void init_17_0_5_edc0357_1346101621154_926007_14131Elaborations() {
                Expression<?>[] arguments590 = new Expression<?>[1];
                arguments590[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition590 = new Expression<Boolean>(_17_0_5_edc0357_1346102209293_902398_14297_exists);
                elaborationRule590 = addElaborationRule(condition590, _17_0_5_edc0357_1346100467245_506694_13577.this, LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1346102209293_902398_14297.class, "_ForkNode_LADWP_CB", arguments590);
                Expression<?>[] arguments591 = new Expression<?>[1];
                arguments591[0] = new Expression<Integer>(cba_endTime);
                Expression<Boolean> condition591 = new Expression<Boolean>(true);
                elaborationRule591 = addElaborationRule(condition591, LADWP.this, LADWP._17_0_5_edc0357_1345510113600_989123_13832.class, "demand_response_Activity_LADWP", arguments591);
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

            public ConstraintExpression constraint592 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346101765241_743667_14225_existsDependency = null;

            public ElaborationRule elaborationRule593 = null;

            public ElaborationRule elaborationRule594 = null;

            public void init_17_0_5_edc0357_1346101629323_369188_14149Members() {
                try {
                    _17_0_5_edc0357_1346101765241_743667_14225_exists = new BooleanParameter("_17_0_5_edc0357_1346101765241_743667_14225_exists", this);
                    _17_0_5_edc0357_1346101664083_228321_14184_endTime = new IntegerParameter("_17_0_5_edc0357_1346101664083_228321_14184_endTime", this);
                    cba_endTime = new IntegerParameter("cba_endTime", this);
                    constraint592 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346101664083_228321_14184_endTime)));
                    _17_0_5_edc0357_1346101765241_743667_14225_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346101765241_743667_14225_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346101629323_369188_14149Collections() {
                parameters.add(_17_0_5_edc0357_1346101765241_743667_14225_exists);
                parameters.add(_17_0_5_edc0357_1346101664083_228321_14184_endTime);
                parameters.add(cba_endTime);
                constraintExpressions.add(constraint592);
                dependencies.add(_17_0_5_edc0357_1346101765241_743667_14225_existsDependency);
            }

            public void init_17_0_5_edc0357_1346101629323_369188_14149Elaborations() {
                Expression<?>[] arguments593 = new Expression<?>[1];
                arguments593[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition593 = new Expression<Boolean>(_17_0_5_edc0357_1346101765241_743667_14225_exists);
                elaborationRule593 = addElaborationRule(condition593, _17_0_5_edc0357_1346100467245_506694_13577.this, LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1346101765241_743667_14225.class, "_MergeNode_LADWP_CB", arguments593);
                Expression<?>[] arguments594 = new Expression<?>[1];
                arguments594[0] = new Expression<Integer>(cba_endTime);
                Expression<Boolean> condition594 = new Expression<Boolean>(true);
                elaborationRule594 = addElaborationRule(condition594, LADWP.this, LADWP._17_0_5_edc0357_1345510113602_28656_13834.class, "generatePowerNow_Activity_LADWP", arguments594);
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

            public ConstraintExpression constraint595 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346106459827_751417_15369_existsDependency = null;

            public ElaborationRule elaborationRule596 = null;

            public ElaborationRule elaborationRule597 = null;

            public void init_17_0_5_edc0357_1346101631180_812549_14165Members() {
                try {
                    _17_0_5_edc0357_1346102275843_86387_14350_endTime = new IntegerParameter("_17_0_5_edc0357_1346102275843_86387_14350_endTime", this);
                    _17_0_5_edc0357_1346106459827_751417_15369_exists = new BooleanParameter("_17_0_5_edc0357_1346106459827_751417_15369_exists", this);
                    cba_endTime = new IntegerParameter("cba_endTime", this);
                    constraint595 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346102275843_86387_14350_endTime)));
                    _17_0_5_edc0357_1346106459827_751417_15369_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346106459827_751417_15369_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346101631180_812549_14165Collections() {
                parameters.add(_17_0_5_edc0357_1346102275843_86387_14350_endTime);
                parameters.add(_17_0_5_edc0357_1346106459827_751417_15369_exists);
                parameters.add(cba_endTime);
                constraintExpressions.add(constraint595);
                dependencies.add(_17_0_5_edc0357_1346106459827_751417_15369_existsDependency);
            }

            public void init_17_0_5_edc0357_1346101631180_812549_14165Elaborations() {
                Expression<?>[] arguments596 = new Expression<?>[1];
                arguments596[0] = new Expression<Integer>(cba_endTime);
                Expression<Boolean> condition596 = new Expression<Boolean>(true);
                elaborationRule596 = addElaborationRule(condition596, LADWP.this, LADWP._17_0_5_edc0357_1345510113602_419995_13835.class, "generatePowerLater_Activity_LADWP", arguments596);
                Expression<?>[] arguments597 = new Expression<?>[1];
                arguments597[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition597 = new Expression<Boolean>(_17_0_5_edc0357_1346106459827_751417_15369_exists);
                elaborationRule597 = addElaborationRule(condition597, _17_0_5_edc0357_1346100467245_506694_13577.this, LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1346106459827_751417_15369.class, "_MergeNode_LADWP_CB", arguments597);
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

            public ConstraintExpression constraint598 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346103858168_44487_14734_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346101629323_369188_14149_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346103964252_49821_14797Dependency = null;

            public ElaborationRule elaborationRule599 = null;

            public ElaborationRule elaborationRule600 = null;

            public void init_17_0_5_edc0357_1346101664083_228321_14184Members() {
                try {
                    _17_0_5_edc0357_1346103858168_44487_14734_exists = new BooleanParameter("_17_0_5_edc0357_1346103858168_44487_14734_exists", this);
                    _17_0_5_edc0357_1346103876583_585391_14750_endTime = new IntegerParameter("_17_0_5_edc0357_1346103876583_585391_14750_endTime", this);
                    _17_0_5_edc0357_1346101629323_369188_14149_exists = new BooleanParameter("_17_0_5_edc0357_1346101629323_369188_14149_exists", this);
                    _17_0_5_edc0357_1346103964252_49821_14797 = new BooleanParameter("_17_0_5_edc0357_1346103964252_49821_14797", this);
                    constraint598 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346103876583_585391_14750_endTime)));
                    _17_0_5_edc0357_1346103858168_44487_14734_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346103858168_44487_14734_exists, new Functions.And(new Functions.Equals(new Expression<Boolean>(_17_0_5_edc0357_1346103964252_49821_14797), new Expression<Boolean>(false)), new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1346104106512_507599_14867, Utils.getMethodForArgTypes("ObjectFlow<Boolean>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) }))));
                    _17_0_5_edc0357_1346101629323_369188_14149_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346101629323_369188_14149_exists, new Functions.Equals(new Expression<Boolean>(_17_0_5_edc0357_1346103964252_49821_14797), new Expression(true)));
                    _17_0_5_edc0357_1346103964252_49821_14797Dependency = new Dependency<Boolean>(_17_0_5_edc0357_1346103964252_49821_14797, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1346103964252_49821_14797, Utils.getMethodForArgTypes("ObjectFlow<Boolean>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346101664083_228321_14184Collections() {
                parameters.add(_17_0_5_edc0357_1346103858168_44487_14734_exists);
                parameters.add(_17_0_5_edc0357_1346103876583_585391_14750_endTime);
                parameters.add(_17_0_5_edc0357_1346101629323_369188_14149_exists);
                parameters.add(_17_0_5_edc0357_1346103964252_49821_14797);
                constraintExpressions.add(constraint598);
                dependencies.add(_17_0_5_edc0357_1346103858168_44487_14734_existsDependency);
                dependencies.add(_17_0_5_edc0357_1346101629323_369188_14149_existsDependency);
                dependencies.add(_17_0_5_edc0357_1346103964252_49821_14797Dependency);
            }

            public void init_17_0_5_edc0357_1346101664083_228321_14184Elaborations() {
                Expression<?>[] arguments599 = new Expression<?>[1];
                arguments599[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition599 = new Expression<Boolean>(_17_0_5_edc0357_1346103858168_44487_14734_exists);
                elaborationRule599 = addElaborationRule(condition599, _17_0_5_edc0357_1346100467245_506694_13577.this, LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1346103858168_44487_14734.class, "_DecisionNode_LADWP_CB", arguments599);
                Expression<?>[] arguments600 = new Expression<?>[1];
                arguments600[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition600 = new Expression<Boolean>(_17_0_5_edc0357_1346101629323_369188_14149_exists);
                elaborationRule600 = addElaborationRule(condition600, _17_0_5_edc0357_1346100467245_506694_13577.this, LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1346101629323_369188_14149.class, "genPowerNow_CallBehaviorAction_LADWP_CB", arguments600);
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

            public ConstraintExpression constraint601 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346101533989_302317_14094_existsDependency = null;

            public ElaborationRule elaborationRule602 = null;

            public void init_17_0_5_edc0357_1346101765241_743667_14225Members() {
                try {
                    invoker_endTime = new IntegerParameter("invoker_endTime", this);
                    _17_0_5_edc0357_1346101533989_302317_14094_exists = new BooleanParameter("_17_0_5_edc0357_1346101533989_302317_14094_exists", this);
                    constraint601 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(invoker_endTime)));
                    _17_0_5_edc0357_1346101533989_302317_14094_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346101533989_302317_14094_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346101765241_743667_14225Collections() {
                parameters.add(invoker_endTime);
                parameters.add(_17_0_5_edc0357_1346101533989_302317_14094_exists);
                constraintExpressions.add(constraint601);
                dependencies.add(_17_0_5_edc0357_1346101533989_302317_14094_existsDependency);
            }

            public void init_17_0_5_edc0357_1346101765241_743667_14225Elaborations() {
                Expression<?>[] arguments602 = new Expression<?>[1];
                arguments602[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition602 = new Expression<Boolean>(_17_0_5_edc0357_1346101533989_302317_14094_exists);
                elaborationRule602 = addElaborationRule(condition602, _17_0_5_edc0357_1346100467245_506694_13577.this, LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1346101533989_302317_14094.class, "monit_CallBehaviorAction_LADWP_CB", arguments602);
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

            public ConstraintExpression constraint603 = null;

            public Dependency< Integer > _17_0_5_edc0357_1346102888530_55753_14505Dependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1348001608547_58915_14306_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346102275843_86387_14350_existsDependency = null;

            public ElaborationRule elaborationRule604 = null;

            public ElaborationRule elaborationRule605 = null;

            public ElaborationRule elaborationRule606 = null;

            public void init_17_0_5_edc0357_1346102113184_840698_14284Members() {
                try {
                    _17_0_5_edc0357_1346102888530_55753_14505 = new IntegerParameter("_17_0_5_edc0357_1346102888530_55753_14505", this);
                    _17_0_5_edc0357_1346102209293_902398_14297_endTime = new IntegerParameter("_17_0_5_edc0357_1346102209293_902398_14297_endTime", this);
                    _17_0_5_edc0357_1348001608547_58915_14306_exists = new BooleanParameter("_17_0_5_edc0357_1348001608547_58915_14306_exists", this);
                    _17_0_5_edc0357_1346102275843_86387_14350_exists = new BooleanParameter("_17_0_5_edc0357_1346102275843_86387_14350_exists", this);
                    cba_endTime = new IntegerParameter("cba_endTime", this);
                    constraint603 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346102209293_902398_14297_endTime)));
                    _17_0_5_edc0357_1346102888530_55753_14505Dependency = new Dependency<Integer>(_17_0_5_edc0357_1346102888530_55753_14505, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1346102889543_283441_14507, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(endTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1348001608547_58915_14306_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1348001608547_58915_14306_exists, new Expression<Boolean>(true));
                    _17_0_5_edc0357_1346102275843_86387_14350_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346102275843_86387_14350_exists, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1348001609357_382848_14312, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })));
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
                constraintExpressions.add(constraint603);
                dependencies.add(_17_0_5_edc0357_1346102888530_55753_14505Dependency);
                dependencies.add(_17_0_5_edc0357_1348001608547_58915_14306_existsDependency);
                dependencies.add(_17_0_5_edc0357_1346102275843_86387_14350_existsDependency);
            }

            public void init_17_0_5_edc0357_1346102113184_840698_14284Elaborations() {
                Expression<?>[] arguments604 = new Expression<?>[1];
                arguments604[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition604 = new Expression<Boolean>(_17_0_5_edc0357_1346102275843_86387_14350_exists);
                elaborationRule604 = addElaborationRule(condition604, _17_0_5_edc0357_1346100467245_506694_13577.this, LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1346102275843_86387_14350.class, "mnmnnnn_DecisionNode_LADWP_CB", arguments604);
                Expression<?>[] arguments605 = new Expression<?>[2];
                arguments605[0] = new Expression<Integer>(cba_endTime);
                arguments605[1] = new Expression<ObjectFlow<Integer>>(sig_17_0_5_edc0357_1346102889543_283441_14507);
                Expression<Boolean> condition605 = new Expression<Boolean>(true);
                elaborationRule605 = addElaborationRule(condition605, LADWP.this, LADWP._17_0_5_edc0357_1346102095091_571012_14257.class, "waitForDRResponse_Activity_LADWP", arguments605);
                Expression<?>[] arguments606 = new Expression<?>[2];
                arguments606[0] = new Expression<Integer>(endTime);
                arguments606[1] = new Expression<Integer>(_17_0_5_edc0357_1346102888530_55753_14505);
                Expression<Boolean> condition606 = new Expression<Boolean>(_17_0_5_edc0357_1348001608547_58915_14306_exists);
                elaborationRule606 = addElaborationRule(condition606, _17_0_5_edc0357_1346100467245_506694_13577.this, LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1348001608547_58915_14306.class, "fnnn_ForkNode_LADWP_CB", arguments606);
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

            public ConstraintExpression constraint607 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346101765241_743667_14225_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346102113184_840698_14284_existsDependency = null;

            public ElaborationRule elaborationRule608 = null;

            public ElaborationRule elaborationRule609 = null;

            public void init_17_0_5_edc0357_1346102209293_902398_14297Members() {
                try {
                    _17_0_5_edc0357_1346101765241_743667_14225_exists = new BooleanParameter("_17_0_5_edc0357_1346101765241_743667_14225_exists", this);
                    _17_0_5_edc0357_1346101621154_926007_14131_endTime = new IntegerParameter("_17_0_5_edc0357_1346101621154_926007_14131_endTime", this);
                    _17_0_5_edc0357_1346102113184_840698_14284_exists = new BooleanParameter("_17_0_5_edc0357_1346102113184_840698_14284_exists", this);
                    constraint607 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346101621154_926007_14131_endTime)));
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
                constraintExpressions.add(constraint607);
                dependencies.add(_17_0_5_edc0357_1346101765241_743667_14225_existsDependency);
                dependencies.add(_17_0_5_edc0357_1346102113184_840698_14284_existsDependency);
            }

            public void init_17_0_5_edc0357_1346102209293_902398_14297Elaborations() {
                Expression<?>[] arguments608 = new Expression<?>[1];
                arguments608[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition608 = new Expression<Boolean>(_17_0_5_edc0357_1346101765241_743667_14225_exists);
                elaborationRule608 = addElaborationRule(condition608, _17_0_5_edc0357_1346100467245_506694_13577.this, LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1346101765241_743667_14225.class, "_MergeNode_LADWP_CB", arguments608);
                Expression<?>[] arguments609 = new Expression<?>[1];
                arguments609[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition609 = new Expression<Boolean>(_17_0_5_edc0357_1346102113184_840698_14284_exists);
                elaborationRule609 = addElaborationRule(condition609, _17_0_5_edc0357_1346100467245_506694_13577.this, LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1346102113184_840698_14284.class, "waitForDRResp_CallBehaviorAction_LADWP_CB", arguments609);
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

            public ConstraintExpression constraint610 = null;

            public Dependency< Integer > _17_0_5_edc0357_1348001555541_398388_14280Dependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346106459827_751417_15369_existsDependency = null;

            public ElaborationRule elaborationRule611 = null;

            public ElaborationRule elaborationRule612 = null;

            public void init_17_0_5_edc0357_1346102261726_372521_14337Members() {
                try {
                    _17_0_5_edc0357_1346102275843_86387_14350_endTime = new IntegerParameter("_17_0_5_edc0357_1346102275843_86387_14350_endTime", this);
                    _17_0_5_edc0357_1348001555541_398388_14280 = new IntegerParameter("_17_0_5_edc0357_1348001555541_398388_14280", this);
                    _17_0_5_edc0357_1346106459827_751417_15369_exists = new BooleanParameter("_17_0_5_edc0357_1346106459827_751417_15369_exists", this);
                    cba_endTime = new IntegerParameter("cba_endTime", this);
                    constraint610 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346102275843_86387_14350_endTime)));
                    _17_0_5_edc0357_1348001555541_398388_14280Dependency = new Dependency<Integer>(_17_0_5_edc0357_1348001555541_398388_14280, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1348001642582_861167_14319, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
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
                constraintExpressions.add(constraint610);
                dependencies.add(_17_0_5_edc0357_1348001555541_398388_14280Dependency);
                dependencies.add(_17_0_5_edc0357_1346106459827_751417_15369_existsDependency);
            }

            public void init_17_0_5_edc0357_1346102261726_372521_14337Elaborations() {
                Expression<?>[] arguments611 = new Expression<?>[2];
                arguments611[0] = new Expression<Integer>(cba_endTime);
                arguments611[1] = new Expression<Integer>(_17_0_5_edc0357_1348001555541_398388_14280);
                Expression<Boolean> condition611 = new Expression<Boolean>(true);
                elaborationRule611 = addElaborationRule(condition611, LADWP.this, LADWP._17_0_5_edc0357_1345510113601_377571_13833.class, "updateExpectedLoad_Activity_LADWP", arguments611);
                Expression<?>[] arguments612 = new Expression<?>[1];
                arguments612[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition612 = new Expression<Boolean>(_17_0_5_edc0357_1346106459827_751417_15369_exists);
                elaborationRule612 = addElaborationRule(condition612, _17_0_5_edc0357_1346100467245_506694_13577.this, LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1346106459827_751417_15369.class, "_MergeNode_LADWP_CB", arguments612);
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

            public ConstraintExpression constraint613 = null;

            public Dependency< Integer > _17_0_5_edc0357_1348001609357_382848_14312Dependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346102261726_372521_14337_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346101631180_812549_14165_existsDependency = null;

            public ElaborationRule elaborationRule614 = null;

            public ElaborationRule elaborationRule615 = null;

            public void init_17_0_5_edc0357_1346102275843_86387_14350Members() {
                try {
                    _17_0_5_edc0357_1348001609357_382848_14312 = new IntegerParameter("_17_0_5_edc0357_1348001609357_382848_14312", this);
                    _17_0_5_edc0357_1346102113184_840698_14284_endTime = new IntegerParameter("_17_0_5_edc0357_1346102113184_840698_14284_endTime", this);
                    _17_0_5_edc0357_1346102261726_372521_14337_exists = new BooleanParameter("_17_0_5_edc0357_1346102261726_372521_14337_exists", this);
                    _17_0_5_edc0357_1346101631180_812549_14165_exists = new BooleanParameter("_17_0_5_edc0357_1346101631180_812549_14165_exists", this);
                    constraint613 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346102113184_840698_14284_endTime)));
                    _17_0_5_edc0357_1348001609357_382848_14312Dependency = new Dependency<Integer>(_17_0_5_edc0357_1348001609357_382848_14312, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1348001609357_382848_14312, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1346102261726_372521_14337_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346102261726_372521_14337_exists, new Functions.And(new Functions.Greater(new Expression<Integer>(_17_0_5_edc0357_1348001609357_382848_14312), new Functions.Negative(new Expression<Integer>(1))), new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1348001642582_861167_14319, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) }))));
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
                constraintExpressions.add(constraint613);
                dependencies.add(_17_0_5_edc0357_1348001609357_382848_14312Dependency);
                dependencies.add(_17_0_5_edc0357_1346102261726_372521_14337_existsDependency);
                dependencies.add(_17_0_5_edc0357_1346101631180_812549_14165_existsDependency);
            }

            public void init_17_0_5_edc0357_1346102275843_86387_14350Elaborations() {
                Expression<?>[] arguments614 = new Expression<?>[1];
                arguments614[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition614 = new Expression<Boolean>(_17_0_5_edc0357_1346102261726_372521_14337_exists);
                elaborationRule614 = addElaborationRule(condition614, _17_0_5_edc0357_1346100467245_506694_13577.this, LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1346102261726_372521_14337.class, "updateExpLoad_CallBehaviorAction_LADWP_CB", arguments614);
                Expression<?>[] arguments615 = new Expression<?>[1];
                arguments615[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition615 = new Expression<Boolean>(_17_0_5_edc0357_1346101631180_812549_14165_exists);
                elaborationRule615 = addElaborationRule(condition615, _17_0_5_edc0357_1346100467245_506694_13577.this, LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1346101631180_812549_14165.class, "genPowerLater_CallBehaviorAction_LADWP_CB", arguments615);
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

            public ConstraintExpression constraint616 = null;

            public void init_17_0_5_edc0357_1346102390577_939825_14382Members() {
                try {
                    _17_0_5_edc0357_1346106459827_751417_15369_endTime = new IntegerParameter("_17_0_5_edc0357_1346106459827_751417_15369_endTime", this);
                    constraint616 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346106459827_751417_15369_endTime)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346102390577_939825_14382Collections() {
                parameters.add(_17_0_5_edc0357_1346106459827_751417_15369_endTime);
                constraintExpressions.add(constraint616);
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

            public ConstraintExpression constraint617 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1348001473382_273655_14257_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346104106512_507599_14867Dependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346101621154_926007_14131_existsDependency = null;

            public ElaborationRule elaborationRule618 = null;

            public ElaborationRule elaborationRule619 = null;

            public void init_17_0_5_edc0357_1346103858168_44487_14734Members() {
                try {
                    _17_0_5_edc0357_1348001473382_273655_14257_exists = new BooleanParameter("_17_0_5_edc0357_1348001473382_273655_14257_exists", this);
                    _17_0_5_edc0357_1346104106512_507599_14867 = new BooleanParameter("_17_0_5_edc0357_1346104106512_507599_14867", this);
                    _17_0_5_edc0357_1346101664083_228321_14184_endTime = new IntegerParameter("_17_0_5_edc0357_1346101664083_228321_14184_endTime", this);
                    _17_0_5_edc0357_1346101621154_926007_14131_exists = new BooleanParameter("_17_0_5_edc0357_1346101621154_926007_14131_exists", this);
                    constraint617 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346101664083_228321_14184_endTime)));
                    _17_0_5_edc0357_1348001473382_273655_14257_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1348001473382_273655_14257_exists, new Functions.Equals(new Expression<Boolean>(_17_0_5_edc0357_1346104106512_507599_14867), new Expression<Boolean>(false)));
                    _17_0_5_edc0357_1346104106512_507599_14867Dependency = new Dependency<Boolean>(_17_0_5_edc0357_1346104106512_507599_14867, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1346104106512_507599_14867, Utils.getMethodForArgTypes("ObjectFlow<Boolean>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
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
                constraintExpressions.add(constraint617);
                dependencies.add(_17_0_5_edc0357_1348001473382_273655_14257_existsDependency);
                dependencies.add(_17_0_5_edc0357_1346104106512_507599_14867Dependency);
                dependencies.add(_17_0_5_edc0357_1346101621154_926007_14131_existsDependency);
            }

            public void init_17_0_5_edc0357_1346103858168_44487_14734Elaborations() {
                Expression<?>[] arguments618 = new Expression<?>[1];
                arguments618[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition618 = new Expression<Boolean>(_17_0_5_edc0357_1346101621154_926007_14131_exists);
                elaborationRule618 = addElaborationRule(condition618, _17_0_5_edc0357_1346100467245_506694_13577.this, LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1346101621154_926007_14131.class, "sendDR_CallBehaviorAction_LADWP_CB", arguments618);
                Expression<?>[] arguments619 = new Expression<?>[1];
                arguments619[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition619 = new Expression<Boolean>(_17_0_5_edc0357_1348001473382_273655_14257_exists);
                elaborationRule619 = addElaborationRule(condition619, _17_0_5_edc0357_1346100467245_506694_13577.this, LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1348001473382_273655_14257.class, "_AcceptEventAction_LADWP_CB", arguments619);
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

            public ConstraintExpression constraint620 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346101664083_228321_14184_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346103985885_527964_14802_existsDependency = null;

            public ElaborationRule elaborationRule621 = null;

            public ElaborationRule elaborationRule622 = null;

            public void init_17_0_5_edc0357_1346103876583_585391_14750Members() {
                try {
                    _17_0_5_edc0357_1346101664083_228321_14184_exists = new BooleanParameter("_17_0_5_edc0357_1346101664083_228321_14184_exists", this);
                    _17_0_5_edc0357_1346103985885_527964_14802_exists = new BooleanParameter("_17_0_5_edc0357_1346103985885_527964_14802_exists", this);
                    _17_0_5_edc0357_1346101533989_302317_14094_endTime = new IntegerParameter("_17_0_5_edc0357_1346101533989_302317_14094_endTime", this);
                    _17_0_5_edc0357_1346103876583_218379_14751 = new Parameter<LADWP>("_17_0_5_edc0357_1346103876583_218379_14751", null, LADWP.this, this);
                    constraint620 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346101533989_302317_14094_endTime)));
                    _17_0_5_edc0357_1346101664083_228321_14184_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346101664083_228321_14184_exists, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1346103964252_49821_14797, Utils.getMethodForArgTypes("ObjectFlow<Boolean>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })));
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
                constraintExpressions.add(constraint620);
                dependencies.add(_17_0_5_edc0357_1346101664083_228321_14184_existsDependency);
                dependencies.add(_17_0_5_edc0357_1346103985885_527964_14802_existsDependency);
            }

            public void init_17_0_5_edc0357_1346103876583_585391_14750Elaborations() {
                Expression<?>[] arguments621 = new Expression<?>[1];
                arguments621[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition621 = new Expression<Boolean>(_17_0_5_edc0357_1346101664083_228321_14184_exists);
                elaborationRule621 = addElaborationRule(condition621, _17_0_5_edc0357_1346100467245_506694_13577.this, LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1346101664083_228321_14184.class, "_DecisionNode_LADWP_CB", arguments621);
                Expression<?>[] arguments622 = new Expression<?>[2];
                arguments622[0] = new Expression<Integer>(endTime);
                arguments622[1] = new Expression<LADWP>(_17_0_5_edc0357_1346103876583_218379_14751);
                Expression<Boolean> condition622 = new Expression<Boolean>(_17_0_5_edc0357_1346103985885_527964_14802_exists);
                elaborationRule622 = addElaborationRule(condition622, _17_0_5_edc0357_1346100467245_506694_13577.this, LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1346103985885_527964_14802.class, "_ForkNode_LADWP_CB", arguments622);
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

            public ConstraintExpression constraint623 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346103927444_968068_14786Dependency = null;

            public Effect effect624 = null;

            public Object effect624Var = null;

            public void init_17_0_5_edc0357_1346103897886_203198_14770Members() {
                try {
                    _17_0_5_edc0357_1346103927444_968068_14786 = new BooleanParameter("_17_0_5_edc0357_1346103927444_968068_14786", this);
                    _17_0_5_edc0357_1346103985885_527964_14802_endTime = new IntegerParameter("_17_0_5_edc0357_1346103985885_527964_14802_endTime", this);
                    _17_0_5_edc0357_1346103914949_580826_14785 = new Parameter<LADWP>("_17_0_5_edc0357_1346103914949_580826_14785", null, null, this);
                    constraint623 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346103985885_527964_14802_endTime)));
                    _17_0_5_edc0357_1346103927444_968068_14786Dependency = new Dependency<Boolean>(_17_0_5_edc0357_1346103927444_968068_14786, new Expression(new FunctionCall((Object) (((Parameter<?>) (_17_0_5_edc0357_1346103914949_580826_14785.getMember("shortage__17_0_5_edc0357_1345510113616_127059_13852"))).getValue()), Utils.getMethodForArgTypes("LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1346103897886_203198_14770", "generated", "getValue"), new Object[] { new Expression<Integer>(startTime) })));
                    effect624Var = sig_17_0_5_edc0357_1346103964252_49821_14797;
                    effect624 = new EffectFunction(new FunctionCall((Object) effect624Var, Utils.getMethodForArgTypes("ObjectFlow<Boolean>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { _17_0_5_edc0357_1346103927444_968068_14786, startTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346103897886_203198_14770Collections() {
                parameters.add(_17_0_5_edc0357_1346103927444_968068_14786);
                parameters.add(_17_0_5_edc0357_1346103985885_527964_14802_endTime);
                parameters.add(_17_0_5_edc0357_1346103914949_580826_14785);
                constraintExpressions.add(constraint623);
                dependencies.add(_17_0_5_edc0357_1346103927444_968068_14786Dependency);
                Set<Effect> effectsForeffect624Var = new HashSet<Effect>();
                effectsForeffect624Var.add(effect624);
                effects.put((Parameter<?>) effect624Var, effectsForeffect624Var);
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

            public ConstraintExpression constraint625 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346103897886_203198_14770_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346104054307_959096_14833_existsDependency = null;

            public ElaborationRule elaborationRule626 = null;

            public ElaborationRule elaborationRule627 = null;

            public void init_17_0_5_edc0357_1346103985885_527964_14802Members() {
                try {
                    _17_0_5_edc0357_1346103876583_585391_14750_endTime = new IntegerParameter("_17_0_5_edc0357_1346103876583_585391_14750_endTime", this);
                    _17_0_5_edc0357_1346103897886_203198_14770_exists = new BooleanParameter("_17_0_5_edc0357_1346103897886_203198_14770_exists", this);
                    _17_0_5_edc0357_1346104054307_959096_14833_exists = new BooleanParameter("_17_0_5_edc0357_1346104054307_959096_14833_exists", this);
                    objectToPass = new Parameter<LADWP>("objectToPass", null, null, this);
                    constraint625 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346103876583_585391_14750_endTime)));
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
                constraintExpressions.add(constraint625);
                dependencies.add(_17_0_5_edc0357_1346103897886_203198_14770_existsDependency);
                dependencies.add(_17_0_5_edc0357_1346104054307_959096_14833_existsDependency);
            }

            public void init_17_0_5_edc0357_1346103985885_527964_14802Elaborations() {
                Expression<?>[] arguments626 = new Expression<?>[2];
                arguments626[0] = new Expression<Integer>(endTime);
                arguments626[1] = new Expression<LADWP>(objectToPass);
                Expression<Boolean> condition626 = new Expression<Boolean>(_17_0_5_edc0357_1346103897886_203198_14770_exists);
                elaborationRule626 = addElaborationRule(condition626, _17_0_5_edc0357_1346100467245_506694_13577.this, LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1346103897886_203198_14770.class, "readShortage_ReadStructuralFeatureAction_LADWP_CB", arguments626);
                Expression<?>[] arguments627 = new Expression<?>[2];
                arguments627[0] = new Expression<Integer>(endTime);
                arguments627[1] = new Expression<LADWP>(objectToPass);
                Expression<Boolean> condition627 = new Expression<Boolean>(_17_0_5_edc0357_1346104054307_959096_14833_exists);
                elaborationRule627 = addElaborationRule(condition627, _17_0_5_edc0357_1346100467245_506694_13577.this, LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1346104054307_959096_14833.class, "readDemandResponse_ReadStructuralFeatureAction_LADWP_CB", arguments627);
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

            public ConstraintExpression constraint628 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346104069409_904806_14848Dependency = null;

            public Effect effect629 = null;

            public Object effect629Var = null;

            public void init_17_0_5_edc0357_1346104054307_959096_14833Members() {
                try {
                    _17_0_5_edc0357_1346103985885_527964_14802_endTime = new IntegerParameter("_17_0_5_edc0357_1346103985885_527964_14802_endTime", this);
                    _17_0_5_edc0357_1346104058977_172678_14847 = new Parameter<LADWP>("_17_0_5_edc0357_1346104058977_172678_14847", null, null, this);
                    _17_0_5_edc0357_1346104069409_904806_14848 = new BooleanParameter("_17_0_5_edc0357_1346104069409_904806_14848", this);
                    constraint628 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346103985885_527964_14802_endTime)));
                    _17_0_5_edc0357_1346104069409_904806_14848Dependency = new Dependency<Boolean>(_17_0_5_edc0357_1346104069409_904806_14848, new Expression(new FunctionCall((Object) (((Parameter<?>) (_17_0_5_edc0357_1346104058977_172678_14847.getMember("demandResponse__17_0_5_edc0357_1345510113615_857599_13851"))).getValue()), Utils.getMethodForArgTypes("LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1346104054307_959096_14833", "generated", "getValue"), new Object[] { new Expression<Integer>(startTime) })));
                    effect629Var = sig_17_0_5_edc0357_1346104106512_507599_14867;
                    effect629 = new EffectFunction(new FunctionCall((Object) effect629Var, Utils.getMethodForArgTypes("ObjectFlow<Boolean>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { _17_0_5_edc0357_1346104069409_904806_14848, startTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346104054307_959096_14833Collections() {
                parameters.add(_17_0_5_edc0357_1346103985885_527964_14802_endTime);
                parameters.add(_17_0_5_edc0357_1346104058977_172678_14847);
                parameters.add(_17_0_5_edc0357_1346104069409_904806_14848);
                constraintExpressions.add(constraint628);
                dependencies.add(_17_0_5_edc0357_1346104069409_904806_14848Dependency);
                Set<Effect> effectsForeffect629Var = new HashSet<Effect>();
                effectsForeffect629Var.add(effect629);
                effects.put((Parameter<?>) effect629Var, effectsForeffect629Var);
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

            public ConstraintExpression constraint630 = null;

            public void init_17_0_5_edc0357_1346104609060_805097_14926Members() {
                try {
                    _17_0_5_edc0357_1348163055082_50872_14559_endTime = new IntegerParameter("_17_0_5_edc0357_1348163055082_50872_14559_endTime", this);
                    constraint630 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1348163055082_50872_14559_endTime)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346104609060_805097_14926Collections() {
                parameters.add(_17_0_5_edc0357_1348163055082_50872_14559_endTime);
                constraintExpressions.add(constraint630);
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

            public ConstraintExpression constraint631 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346102390577_939825_14382_existsDependency = null;

            public ElaborationRule elaborationRule632 = null;

            public void init_17_0_5_edc0357_1346106459827_751417_15369Members() {
                try {
                    invoker_endTime = new IntegerParameter("invoker_endTime", this);
                    _17_0_5_edc0357_1346102390577_939825_14382_exists = new BooleanParameter("_17_0_5_edc0357_1346102390577_939825_14382_exists", this);
                    constraint631 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(invoker_endTime)));
                    _17_0_5_edc0357_1346102390577_939825_14382_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346102390577_939825_14382_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346106459827_751417_15369Collections() {
                parameters.add(invoker_endTime);
                parameters.add(_17_0_5_edc0357_1346102390577_939825_14382_exists);
                constraintExpressions.add(constraint631);
                dependencies.add(_17_0_5_edc0357_1346102390577_939825_14382_existsDependency);
            }

            public void init_17_0_5_edc0357_1346106459827_751417_15369Elaborations() {
                Expression<?>[] arguments632 = new Expression<?>[1];
                arguments632[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition632 = new Expression<Boolean>(_17_0_5_edc0357_1346102390577_939825_14382_exists);
                elaborationRule632 = addElaborationRule(condition632, _17_0_5_edc0357_1346100467245_506694_13577.this, LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1346102390577_939825_14382.class, "_FlowFinalNode_LADWP_CB", arguments632);
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

            public ConstraintExpression constraint633 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346101765241_743667_14225_existsDependency = null;

            public Dependency< Integer > durationDependency = null;

            public ElaborationRule elaborationRule634 = null;

            public void init_17_0_5_edc0357_1348001296069_146013_14222Members() {
                try {
                    _17_0_5_edc0357_1346101765241_743667_14225_exists = new BooleanParameter("_17_0_5_edc0357_1346101765241_743667_14225_exists", this);
                    _17_0_5_edc0357_1346100685814_732050_13654_endTime = new IntegerParameter("_17_0_5_edc0357_1346100685814_732050_13654_endTime", this);
                    constraint633 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346100685814_732050_13654_endTime)));
                    _17_0_5_edc0357_1346101765241_743667_14225_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346101765241_743667_14225_exists, new Expression<Boolean>(true));
                    durationDependency = new Dependency<Integer>(duration, new Expression<Integer>(30));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1348001296069_146013_14222Collections() {
                parameters.add(_17_0_5_edc0357_1346101765241_743667_14225_exists);
                parameters.add(_17_0_5_edc0357_1346100685814_732050_13654_endTime);
                constraintExpressions.add(constraint633);
                removeDependency(duration);
                dependencies.add(_17_0_5_edc0357_1346101765241_743667_14225_existsDependency);
                dependencies.add(durationDependency);
            }

            public void init_17_0_5_edc0357_1348001296069_146013_14222Elaborations() {
                Expression<?>[] arguments634 = new Expression<?>[1];
                arguments634[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition634 = new Expression<Boolean>(_17_0_5_edc0357_1346101765241_743667_14225_exists);
                elaborationRule634 = addElaborationRule(condition634, _17_0_5_edc0357_1346100467245_506694_13577.this, LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1346101765241_743667_14225.class, "_MergeNode_LADWP_CB", arguments634);
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

            public ConstraintExpression constraint635 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346101765241_743667_14225_existsDependency = null;

            public Dependency< Integer > durationDependency = null;

            public ElaborationRule elaborationRule636 = null;

            public void init_17_0_5_edc0357_1348001473382_273655_14257Members() {
                try {
                    _17_0_5_edc0357_1346103858168_44487_14734_endTime = new IntegerParameter("_17_0_5_edc0357_1346103858168_44487_14734_endTime", this);
                    _17_0_5_edc0357_1346101765241_743667_14225_exists = new BooleanParameter("_17_0_5_edc0357_1346101765241_743667_14225_exists", this);
                    constraint635 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346103858168_44487_14734_endTime)));
                    _17_0_5_edc0357_1346101765241_743667_14225_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346101765241_743667_14225_exists, new Expression<Boolean>(true));
                    durationDependency = new Dependency<Integer>(duration, new Expression<Integer>(45));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1348001473382_273655_14257Collections() {
                parameters.add(_17_0_5_edc0357_1346103858168_44487_14734_endTime);
                parameters.add(_17_0_5_edc0357_1346101765241_743667_14225_exists);
                constraintExpressions.add(constraint635);
                removeDependency(duration);
                dependencies.add(_17_0_5_edc0357_1346101765241_743667_14225_existsDependency);
                dependencies.add(durationDependency);
            }

            public void init_17_0_5_edc0357_1348001473382_273655_14257Elaborations() {
                Expression<?>[] arguments636 = new Expression<?>[1];
                arguments636[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition636 = new Expression<Boolean>(_17_0_5_edc0357_1346101765241_743667_14225_exists);
                elaborationRule636 = addElaborationRule(condition636, _17_0_5_edc0357_1346100467245_506694_13577.this, LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1346101765241_743667_14225.class, "_MergeNode_LADWP_CB", arguments636);
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

            public ConstraintExpression constraint637 = null;

            public Effect effect638 = null;

            public Object effect638Var = null;

            public Effect effect639 = null;

            public Object effect639Var = null;

            public void init_17_0_5_edc0357_1348001608547_58915_14306Members() {
                try {
                    _17_0_5_edc0357_1346102113184_840698_14284_endTime = new IntegerParameter("_17_0_5_edc0357_1346102113184_840698_14284_endTime", this);
                    objectToPass = new IntegerParameter("objectToPass", this);
                    constraint637 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346102113184_840698_14284_endTime)));
                    effect638Var = sig_17_0_5_edc0357_1348001609357_382848_14312;
                    effect638 = new EffectFunction(new FunctionCall((Object) effect638Var, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                    effect639Var = sig_17_0_5_edc0357_1348001642582_861167_14319;
                    effect639 = new EffectFunction(new FunctionCall((Object) effect639Var, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1348001608547_58915_14306Collections() {
                parameters.add(_17_0_5_edc0357_1346102113184_840698_14284_endTime);
                parameters.add(objectToPass);
                constraintExpressions.add(constraint637);
                Set<Effect> effectsForeffect638Var = new HashSet<Effect>();
                effectsForeffect638Var.add(effect638);
                effects.put((Parameter<?>) effect638Var, effectsForeffect638Var);
                Set<Effect> effectsForeffect639Var = new HashSet<Effect>();
                effectsForeffect639Var.add(effect639);
                effects.put((Parameter<?>) effect639Var, effectsForeffect639Var);
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

            public ConstraintExpression constraint640 = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346100685814_732050_13654_existsDependency = null;

            public ElaborationRule elaborationRule641 = null;

            public void init_17_0_5_edc0357_1348008482216_500972_14383Members() {
                try {
                    _17_0_5_edc0357_1346100496379_482519_13614_endTime = new IntegerParameter("_17_0_5_edc0357_1346100496379_482519_13614_endTime", this);
                    _17_0_5_edc0357_1346100685814_732050_13654_exists = new BooleanParameter("_17_0_5_edc0357_1346100685814_732050_13654_exists", this);
                    constraint640 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346100496379_482519_13614_endTime)));
                    durationDependency = new Dependency<Integer>(duration, new Expression<Integer>(20));
                    _17_0_5_edc0357_1346100685814_732050_13654_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346100685814_732050_13654_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1348008482216_500972_14383Collections() {
                parameters.add(_17_0_5_edc0357_1346100496379_482519_13614_endTime);
                parameters.add(_17_0_5_edc0357_1346100685814_732050_13654_exists);
                constraintExpressions.add(constraint640);
                removeDependency(duration);
                dependencies.add(durationDependency);
                dependencies.add(_17_0_5_edc0357_1346100685814_732050_13654_existsDependency);
            }

            public void init_17_0_5_edc0357_1348008482216_500972_14383Elaborations() {
                Expression<?>[] arguments641 = new Expression<?>[1];
                arguments641[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition641 = new Expression<Boolean>(_17_0_5_edc0357_1346100685814_732050_13654_exists);
                elaborationRule641 = addElaborationRule(condition641, _17_0_5_edc0357_1346100467245_506694_13577.this, LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1346100685814_732050_13654.class, "_ForkNode_LADWP_CB", arguments641);
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

            public ConstraintExpression constraint642 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346104609060_805097_14926_existsDependency = null;

            public Dependency< Integer > durationDependency = null;

            public ElaborationRule elaborationRule643 = null;

            public void init_17_0_5_edc0357_1348163055082_50872_14559Members() {
                try {
                    _17_0_5_edc0357_1346104609060_805097_14926_exists = new BooleanParameter("_17_0_5_edc0357_1346104609060_805097_14926_exists", this);
                    _17_0_5_edc0357_1346100685814_732050_13654_endTime = new IntegerParameter("_17_0_5_edc0357_1346100685814_732050_13654_endTime", this);
                    constraint642 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346100685814_732050_13654_endTime)));
                    _17_0_5_edc0357_1346104609060_805097_14926_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346104609060_805097_14926_exists, new Expression<Boolean>(true));
                    durationDependency = new Dependency<Integer>(duration, new Expression<Integer>(10));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1348163055082_50872_14559Collections() {
                parameters.add(_17_0_5_edc0357_1346104609060_805097_14926_exists);
                parameters.add(_17_0_5_edc0357_1346100685814_732050_13654_endTime);
                constraintExpressions.add(constraint642);
                removeDependency(duration);
                dependencies.add(_17_0_5_edc0357_1346104609060_805097_14926_existsDependency);
                dependencies.add(durationDependency);
            }

            public void init_17_0_5_edc0357_1348163055082_50872_14559Elaborations() {
                Expression<?>[] arguments643 = new Expression<?>[1];
                arguments643[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition643 = new Expression<Boolean>(_17_0_5_edc0357_1346104609060_805097_14926_exists);
                elaborationRule643 = addElaborationRule(condition643, _17_0_5_edc0357_1346100467245_506694_13577.this, LADWP._17_0_5_edc0357_1346100467245_506694_13577._17_0_5_edc0357_1346104609060_805097_14926.class, "_ActivityFinalNode_LADWP_CB", arguments643);
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

        public ElaborationRule elaborationRule644 = null;

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
            Expression<?>[] arguments644 = new Expression<?>[1];
            arguments644[0] = new Expression<Integer>(invoke_time);
            Expression<Boolean> condition644 = new Expression<Boolean>(true);
            elaborationRule644 = addElaborationRule(condition644, _17_0_5_edc0357_1346102095091_571012_14257.this, LADWP._17_0_5_edc0357_1346102095091_571012_14257._17_0_5_edc0357_1346102438886_844068_14398.class, "_InitialNode_waitForDRResponse", arguments644);
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

            public ElaborationRule elaborationRule645 = null;

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
                Expression<?>[] arguments645 = new Expression<?>[1];
                arguments645[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition645 = new Expression<Boolean>(_17_0_5_edc0357_1346102573506_761015_14458_exists);
                elaborationRule645 = addElaborationRule(condition645, _17_0_5_edc0357_1346102095091_571012_14257.this, LADWP._17_0_5_edc0357_1346102095091_571012_14257._17_0_5_edc0357_1346102573506_761015_14458.class, "_ForkNode_waitForDRResponse", arguments645);
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

            public ConstraintExpression constraint646 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346103062558_947732_14567_existsDependency = null;

            public Dependency< Power_System.Signalyes > _17_0_5_edc0357_1346102459862_624077_14418Dependency = null;

            public ElaborationRule elaborationRule647 = null;

            public void init_17_0_5_edc0357_1346102441989_709933_14404Members() {
                try {
                    _17_0_5_edc0357_1346103062558_947732_14567_exists = new BooleanParameter("_17_0_5_edc0357_1346103062558_947732_14567_exists", this);
                    _17_0_5_edc0357_1346102459862_624077_14418 = new Parameter<Power_System.Signalyes>("_17_0_5_edc0357_1346102459862_624077_14418", null, null, this);
                    _17_0_5_edc0357_1346102573506_761015_14458_endTime = new IntegerParameter("_17_0_5_edc0357_1346102573506_761015_14458_endTime", this);
                    constraint646 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346102573506_761015_14458_endTime)));
                    _17_0_5_edc0357_1346103062558_947732_14567_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346103062558_947732_14567_exists, new Expression<Boolean>(true));
                    _17_0_5_edc0357_1346102459862_624077_14418Dependency = new Dependency<Power_System.Signalyes>(_17_0_5_edc0357_1346102459862_624077_14418, new Expression(new FunctionCall((Object) q_LADWP_yes, Utils.getMethodForArgTypes("ObjectFlow<Signalyes>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346102441989_709933_14404Collections() {
                parameters.add(_17_0_5_edc0357_1346103062558_947732_14567_exists);
                parameters.add(_17_0_5_edc0357_1346102459862_624077_14418);
                parameters.add(_17_0_5_edc0357_1346102573506_761015_14458_endTime);
                constraintExpressions.add(constraint646);
                dependencies.add(_17_0_5_edc0357_1346103062558_947732_14567_existsDependency);
                dependencies.add(_17_0_5_edc0357_1346102459862_624077_14418Dependency);
            }

            public void init_17_0_5_edc0357_1346102441989_709933_14404Elaborations() {
                Expression<?>[] arguments647 = new Expression<?>[2];
                arguments647[0] = new Expression<Integer>(endTime);
                arguments647[1] = new Expression<Power_System.Signalyes>(_17_0_5_edc0357_1346102459862_624077_14418);
                Expression<Boolean> condition647 = new Expression<Boolean>(_17_0_5_edc0357_1346103062558_947732_14567_exists);
                elaborationRule647 = addElaborationRule(condition647, _17_0_5_edc0357_1346102095091_571012_14257.this, LADWP._17_0_5_edc0357_1346102095091_571012_14257._17_0_5_edc0357_1346103062558_947732_14567.class, "_ReadStructuralFeatureAction_waitForDRResponse", arguments647);
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

            public ConstraintExpression constraint648 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346103010487_691704_14535_existsDependency = null;

            public ElaborationRule elaborationRule649 = null;

            public void init_17_0_5_edc0357_1346102519738_328444_14424Members() {
                try {
                    _17_0_5_edc0357_1346102573506_761015_14458_endTime = new IntegerParameter("_17_0_5_edc0357_1346102573506_761015_14458_endTime", this);
                    _17_0_5_edc0357_1346103010487_691704_14535_exists = new BooleanParameter("_17_0_5_edc0357_1346103010487_691704_14535_exists", this);
                    constraint648 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346102573506_761015_14458_endTime)));
                    _17_0_5_edc0357_1346103010487_691704_14535_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346103010487_691704_14535_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346102519738_328444_14424Collections() {
                parameters.add(_17_0_5_edc0357_1346102573506_761015_14458_endTime);
                parameters.add(_17_0_5_edc0357_1346103010487_691704_14535_exists);
                constraintExpressions.add(constraint648);
                dependencies.add(_17_0_5_edc0357_1346103010487_691704_14535_existsDependency);
            }

            public void init_17_0_5_edc0357_1346102519738_328444_14424Elaborations() {
                Expression<?>[] arguments649 = new Expression<?>[1];
                arguments649[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition649 = new Expression<Boolean>(_17_0_5_edc0357_1346103010487_691704_14535_exists);
                elaborationRule649 = addElaborationRule(condition649, _17_0_5_edc0357_1346102095091_571012_14257.this, LADWP._17_0_5_edc0357_1346102095091_571012_14257._17_0_5_edc0357_1346103010487_691704_14535.class, "_MergeNode_waitForDRResponse", arguments649);
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

            public ConstraintExpression constraint650 = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346103010487_691704_14535_existsDependency = null;

            public ElaborationRule elaborationRule651 = null;

            public void init_17_0_5_edc0357_1346102549978_121685_14442Members() {
                try {
                    _17_0_5_edc0357_1346102573506_761015_14458_endTime = new IntegerParameter("_17_0_5_edc0357_1346102573506_761015_14458_endTime", this);
                    _17_0_5_edc0357_1346103010487_691704_14535_exists = new BooleanParameter("_17_0_5_edc0357_1346103010487_691704_14535_exists", this);
                    constraint650 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346102573506_761015_14458_endTime)));
                    durationDependency = new Dependency<Integer>(duration, new Expression<Integer>(240));
                    _17_0_5_edc0357_1346103010487_691704_14535_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346103010487_691704_14535_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346102549978_121685_14442Collections() {
                parameters.add(_17_0_5_edc0357_1346102573506_761015_14458_endTime);
                parameters.add(_17_0_5_edc0357_1346103010487_691704_14535_exists);
                constraintExpressions.add(constraint650);
                removeDependency(duration);
                dependencies.add(durationDependency);
                dependencies.add(_17_0_5_edc0357_1346103010487_691704_14535_existsDependency);
            }

            public void init_17_0_5_edc0357_1346102549978_121685_14442Elaborations() {
                Expression<?>[] arguments651 = new Expression<?>[1];
                arguments651[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition651 = new Expression<Boolean>(_17_0_5_edc0357_1346103010487_691704_14535_exists);
                elaborationRule651 = addElaborationRule(condition651, _17_0_5_edc0357_1346102095091_571012_14257.this, LADWP._17_0_5_edc0357_1346102095091_571012_14257._17_0_5_edc0357_1346103010487_691704_14535.class, "_MergeNode_waitForDRResponse", arguments651);
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

            public ConstraintExpression constraint652 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346102519738_328444_14424_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346102441989_709933_14404_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346102549978_121685_14442_existsDependency = null;

            public ElaborationRule elaborationRule653 = null;

            public ElaborationRule elaborationRule654 = null;

            public ElaborationRule elaborationRule655 = null;

            public void init_17_0_5_edc0357_1346102573506_761015_14458Members() {
                try {
                    _17_0_5_edc0357_1346102519738_328444_14424_exists = new BooleanParameter("_17_0_5_edc0357_1346102519738_328444_14424_exists", this);
                    _17_0_5_edc0357_1346102441989_709933_14404_exists = new BooleanParameter("_17_0_5_edc0357_1346102441989_709933_14404_exists", this);
                    _17_0_5_edc0357_1346102438886_844068_14398_endTime = new IntegerParameter("_17_0_5_edc0357_1346102438886_844068_14398_endTime", this);
                    _17_0_5_edc0357_1346102549978_121685_14442_exists = new BooleanParameter("_17_0_5_edc0357_1346102549978_121685_14442_exists", this);
                    constraint652 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346102438886_844068_14398_endTime)));
                    _17_0_5_edc0357_1346102519738_328444_14424_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346102519738_328444_14424_exists, new Expression(new FunctionCall((Object) q_LADWP_no, Utils.getMethodForArgTypes("ObjectFlow<Signalno>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })));
                    _17_0_5_edc0357_1346102441989_709933_14404_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346102441989_709933_14404_exists, new Expression(new FunctionCall((Object) q_LADWP_yes, Utils.getMethodForArgTypes("ObjectFlow<Signalyes>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })));
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
                constraintExpressions.add(constraint652);
                dependencies.add(_17_0_5_edc0357_1346102519738_328444_14424_existsDependency);
                dependencies.add(_17_0_5_edc0357_1346102441989_709933_14404_existsDependency);
                dependencies.add(_17_0_5_edc0357_1346102549978_121685_14442_existsDependency);
            }

            public void init_17_0_5_edc0357_1346102573506_761015_14458Elaborations() {
                Expression<?>[] arguments653 = new Expression<?>[1];
                arguments653[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition653 = new Expression<Boolean>(_17_0_5_edc0357_1346102441989_709933_14404_exists);
                elaborationRule653 = addElaborationRule(condition653, _17_0_5_edc0357_1346102095091_571012_14257.this, LADWP._17_0_5_edc0357_1346102095091_571012_14257._17_0_5_edc0357_1346102441989_709933_14404.class, "y_AcceptEventAction_waitForDRResponse", arguments653);
                Expression<?>[] arguments654 = new Expression<?>[1];
                arguments654[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition654 = new Expression<Boolean>(_17_0_5_edc0357_1346102549978_121685_14442_exists);
                elaborationRule654 = addElaborationRule(condition654, _17_0_5_edc0357_1346102095091_571012_14257.this, LADWP._17_0_5_edc0357_1346102095091_571012_14257._17_0_5_edc0357_1346102549978_121685_14442.class, "_AcceptEventAction_waitForDRResponse", arguments654);
                Expression<?>[] arguments655 = new Expression<?>[1];
                arguments655[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition655 = new Expression<Boolean>(_17_0_5_edc0357_1346102519738_328444_14424_exists);
                elaborationRule655 = addElaborationRule(condition655, _17_0_5_edc0357_1346102095091_571012_14257.this, LADWP._17_0_5_edc0357_1346102095091_571012_14257._17_0_5_edc0357_1346102519738_328444_14424.class, "n_AcceptEventAction_waitForDRResponse", arguments655);
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

            public ConstraintExpression constraint656 = null;

            public Effect effect657 = null;

            public Object effect657Var = null;

            public void init_17_0_5_edc0357_1346102903802_613014_14509Members() {
                try {
                    _17_0_5_edc0357_1346102889543_283441_14507 = new IntegerParameter("_17_0_5_edc0357_1346102889543_283441_14507", this);
                    _17_0_5_edc0357_1346103062558_947732_14567_endTime = new IntegerParameter("_17_0_5_edc0357_1346103062558_947732_14567_endTime", this);
                    constraint656 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346103062558_947732_14567_endTime)));
                    effect657Var = sig_17_0_5_edc0357_1346102889543_283441_14507;
                    effect657 = new EffectFunction(new FunctionCall((Object) effect657Var, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { _17_0_5_edc0357_1346102889543_283441_14507, endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346102903802_613014_14509Collections() {
                parameters.add(_17_0_5_edc0357_1346102889543_283441_14507);
                parameters.add(_17_0_5_edc0357_1346103062558_947732_14567_endTime);
                constraintExpressions.add(constraint656);
                Set<Effect> effectsForeffect657Var = new HashSet<Effect>();
                effectsForeffect657Var.add(effect657);
                effects.put((Parameter<?>) effect657Var, effectsForeffect657Var);
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

            public ConstraintExpression constraint658 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346103606797_653782_14706_existsDependency = null;

            public ElaborationRule elaborationRule659 = null;

            public void init_17_0_5_edc0357_1346103010487_691704_14535Members() {
                try {
                    invoker_endTime = new IntegerParameter("invoker_endTime", this);
                    _17_0_5_edc0357_1346103606797_653782_14706_exists = new BooleanParameter("_17_0_5_edc0357_1346103606797_653782_14706_exists", this);
                    constraint658 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(invoker_endTime)));
                    _17_0_5_edc0357_1346103606797_653782_14706_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346103606797_653782_14706_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346103010487_691704_14535Collections() {
                parameters.add(invoker_endTime);
                parameters.add(_17_0_5_edc0357_1346103606797_653782_14706_exists);
                constraintExpressions.add(constraint658);
                dependencies.add(_17_0_5_edc0357_1346103606797_653782_14706_existsDependency);
            }

            public void init_17_0_5_edc0357_1346103010487_691704_14535Elaborations() {
                Expression<?>[] arguments659 = new Expression<?>[1];
                arguments659[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition659 = new Expression<Boolean>(_17_0_5_edc0357_1346103606797_653782_14706_exists);
                elaborationRule659 = addElaborationRule(condition659, _17_0_5_edc0357_1346102095091_571012_14257.this, LADWP._17_0_5_edc0357_1346102095091_571012_14257._17_0_5_edc0357_1346103606797_653782_14706.class, "_ForkNode_waitForDRResponse", arguments659);
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

            public ConstraintExpression constraint660 = null;

            public void init_17_0_5_edc0357_1346103021014_230289_14555Members() {
                try {
                    _17_0_5_edc0357_1346103381981_27349_14621_endTime = new IntegerParameter("_17_0_5_edc0357_1346103381981_27349_14621_endTime", this);
                    constraint660 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346103381981_27349_14621_endTime)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346103021014_230289_14555Collections() {
                parameters.add(_17_0_5_edc0357_1346103381981_27349_14621_endTime);
                constraintExpressions.add(constraint660);
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

            public ConstraintExpression constraint661 = null;

            public Dependency< Integer > _17_0_5_edc0357_1346103096100_759763_14588Dependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346102903802_613014_14509_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346103010487_691704_14535_existsDependency = null;

            public ElaborationRule elaborationRule662 = null;

            public ElaborationRule elaborationRule663 = null;

            public void init_17_0_5_edc0357_1346103062558_947732_14567Members() {
                try {
                    _17_0_5_edc0357_1346103096100_759763_14588 = new IntegerParameter("_17_0_5_edc0357_1346103096100_759763_14588", this);
                    _17_0_5_edc0357_1346102441989_709933_14404_endTime = new IntegerParameter("_17_0_5_edc0357_1346102441989_709933_14404_endTime", this);
                    _17_0_5_edc0357_1346102903802_613014_14509_exists = new BooleanParameter("_17_0_5_edc0357_1346102903802_613014_14509_exists", this);
                    _17_0_5_edc0357_1346103082067_604440_14587 = new Parameter<Power_System.Signalyes>("_17_0_5_edc0357_1346103082067_604440_14587", null, null, this);
                    _17_0_5_edc0357_1346103010487_691704_14535_exists = new BooleanParameter("_17_0_5_edc0357_1346103010487_691704_14535_exists", this);
                    constraint661 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346102441989_709933_14404_endTime)));
                    _17_0_5_edc0357_1346103096100_759763_14588Dependency = new Dependency<Integer>(_17_0_5_edc0357_1346103096100_759763_14588, new Expression(new FunctionCall((Object) (((Parameter<?>) (_17_0_5_edc0357_1346103082067_604440_14587.getMember("newLoad__17_0_5_edc0357_1345510113619_149263_13857"))).getValue()), Utils.getMethodForArgTypes("LADWP._17_0_5_edc0357_1346102095091_571012_14257._17_0_5_edc0357_1346103062558_947732_14567", "generated", "getValue"), new Object[] { new Expression<Integer>(startTime) })));
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
                constraintExpressions.add(constraint661);
                dependencies.add(_17_0_5_edc0357_1346103096100_759763_14588Dependency);
                dependencies.add(_17_0_5_edc0357_1346102903802_613014_14509_existsDependency);
                dependencies.add(_17_0_5_edc0357_1346103010487_691704_14535_existsDependency);
            }

            public void init_17_0_5_edc0357_1346103062558_947732_14567Elaborations() {
                Expression<?>[] arguments662 = new Expression<?>[1];
                arguments662[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition662 = new Expression<Boolean>(_17_0_5_edc0357_1346103010487_691704_14535_exists);
                elaborationRule662 = addElaborationRule(condition662, _17_0_5_edc0357_1346102095091_571012_14257.this, LADWP._17_0_5_edc0357_1346102095091_571012_14257._17_0_5_edc0357_1346103010487_691704_14535.class, "_MergeNode_waitForDRResponse", arguments662);
                Expression<?>[] arguments663 = new Expression<?>[2];
                arguments663[0] = new Expression<Integer>(endTime);
                arguments663[1] = new Expression<Integer>(_17_0_5_edc0357_1346103096100_759763_14588);
                Expression<Boolean> condition663 = new Expression<Boolean>(_17_0_5_edc0357_1346102903802_613014_14509_exists);
                elaborationRule663 = addElaborationRule(condition663, _17_0_5_edc0357_1346102095091_571012_14257.this, LADWP._17_0_5_edc0357_1346102095091_571012_14257._17_0_5_edc0357_1346102903802_613014_14509.class, "result_ActivityParameterNode_waitForDRResponse", arguments663);
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

            public ConstraintExpression constraint664 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346103021014_230289_14555_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346103434426_385137_14640Dependency = null;

            public Effect effect665 = null;

            public Object effect665Var = null;

            public ElaborationRule elaborationRule666 = null;

            public void init_17_0_5_edc0357_1346103381981_27349_14621Members() {
                try {
                    _17_0_5_edc0357_1346103407692_242808_14639 = new Parameter<LADWP>("_17_0_5_edc0357_1346103407692_242808_14639", null, null, this);
                    _17_0_5_edc0357_1346103478833_548117_14651_endTime = new IntegerParameter("_17_0_5_edc0357_1346103478833_548117_14651_endTime", this);
                    _17_0_5_edc0357_1346103021014_230289_14555_exists = new BooleanParameter("_17_0_5_edc0357_1346103021014_230289_14555_exists", this);
                    _17_0_5_edc0357_1346103434426_385137_14640 = new BooleanParameter("_17_0_5_edc0357_1346103434426_385137_14640", this);
                    constraint664 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346103478833_548117_14651_endTime)));
                    _17_0_5_edc0357_1346103021014_230289_14555_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346103021014_230289_14555_exists, new Expression<Boolean>(true));
                    _17_0_5_edc0357_1346103434426_385137_14640Dependency = new Dependency<Boolean>(_17_0_5_edc0357_1346103434426_385137_14640, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1346103552856_270449_14698, Utils.getMethodForArgTypes("ObjectFlow<Boolean>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    effect665Var = demandResponse__17_0_5_edc0357_1345510113615_857599_13851;
                    effect665 = new EffectFunction(new FunctionCall((Object) effect665Var, Utils.getMethodForArgTypes("TimeVaryingMap<Boolean>", "generated", "setValue", java.lang.Integer.class, java.lang.Object.class), new Object[] { startTime, _17_0_5_edc0357_1346103434426_385137_14640 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346103381981_27349_14621Collections() {
                parameters.add(_17_0_5_edc0357_1346103407692_242808_14639);
                parameters.add(_17_0_5_edc0357_1346103478833_548117_14651_endTime);
                parameters.add(_17_0_5_edc0357_1346103021014_230289_14555_exists);
                parameters.add(_17_0_5_edc0357_1346103434426_385137_14640);
                constraintExpressions.add(constraint664);
                dependencies.add(_17_0_5_edc0357_1346103021014_230289_14555_existsDependency);
                dependencies.add(_17_0_5_edc0357_1346103434426_385137_14640Dependency);
                Set<Effect> effectsForeffect665Var = new HashSet<Effect>();
                effectsForeffect665Var.add(effect665);
                effects.put((Parameter<?>) effect665Var, effectsForeffect665Var);
            }

            public void init_17_0_5_edc0357_1346103381981_27349_14621Elaborations() {
                Expression<?>[] arguments666 = new Expression<?>[1];
                arguments666[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition666 = new Expression<Boolean>(_17_0_5_edc0357_1346103021014_230289_14555_exists);
                elaborationRule666 = addElaborationRule(condition666, _17_0_5_edc0357_1346102095091_571012_14257.this, LADWP._17_0_5_edc0357_1346102095091_571012_14257._17_0_5_edc0357_1346103021014_230289_14555.class, "_ActivityFinalNode_waitForDRResponse", arguments666);
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

            public ConstraintExpression constraint667 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346103381981_27349_14621_existsDependency = null;

            public ElaborationRule elaborationRule668 = null;

            public void init_17_0_5_edc0357_1346103478833_548117_14651Members() {
                try {
                    _17_0_5_edc0357_1346103478833_637965_14652 = new Parameter<LADWP>("_17_0_5_edc0357_1346103478833_637965_14652", null, LADWP.this, this);
                    _17_0_5_edc0357_1346103606797_653782_14706_endTime = new IntegerParameter("_17_0_5_edc0357_1346103606797_653782_14706_endTime", this);
                    _17_0_5_edc0357_1346103381981_27349_14621_exists = new BooleanParameter("_17_0_5_edc0357_1346103381981_27349_14621_exists", this);
                    constraint667 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346103606797_653782_14706_endTime)));
                    _17_0_5_edc0357_1346103381981_27349_14621_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346103381981_27349_14621_exists, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1346103552856_270449_14698, Utils.getMethodForArgTypes("ObjectFlow<Boolean>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346103478833_548117_14651Collections() {
                parameters.add(_17_0_5_edc0357_1346103478833_637965_14652);
                parameters.add(_17_0_5_edc0357_1346103606797_653782_14706_endTime);
                parameters.add(_17_0_5_edc0357_1346103381981_27349_14621_exists);
                constraintExpressions.add(constraint667);
                dependencies.add(_17_0_5_edc0357_1346103381981_27349_14621_existsDependency);
            }

            public void init_17_0_5_edc0357_1346103478833_548117_14651Elaborations() {
                Expression<?>[] arguments668 = new Expression<?>[2];
                arguments668[0] = new Expression<Integer>(endTime);
                arguments668[1] = new Expression<LADWP>(_17_0_5_edc0357_1346103478833_637965_14652);
                Expression<Boolean> condition668 = new Expression<Boolean>(_17_0_5_edc0357_1346103381981_27349_14621_exists);
                elaborationRule668 = addElaborationRule(condition668, _17_0_5_edc0357_1346102095091_571012_14257.this, LADWP._17_0_5_edc0357_1346102095091_571012_14257._17_0_5_edc0357_1346103381981_27349_14621.class, "_AddStructuralFeatureValueAction_waitForDRResponse", arguments668);
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

            public ConstraintExpression constraint669 = null;

            public Effect effect670 = null;

            public Object effect670Var = null;

            public void init_17_0_5_edc0357_1346103518161_525445_14678Members() {
                try {
                    _17_0_5_edc0357_1346103606797_653782_14706_endTime = new IntegerParameter("_17_0_5_edc0357_1346103606797_653782_14706_endTime", this);
                    _17_0_5_edc0357_1346103524024_311757_14690 = new BooleanParameter("_17_0_5_edc0357_1346103524024_311757_14690", this);
                    constraint669 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346103606797_653782_14706_endTime)));
                    effect670Var = sig_17_0_5_edc0357_1346103552856_270449_14698;
                    effect670 = new EffectFunction(new FunctionCall((Object) effect670Var, Utils.getMethodForArgTypes("ObjectFlow<Boolean>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { _17_0_5_edc0357_1346103524024_311757_14690, startTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346103518161_525445_14678Collections() {
                parameters.add(_17_0_5_edc0357_1346103606797_653782_14706_endTime);
                parameters.add(_17_0_5_edc0357_1346103524024_311757_14690);
                constraintExpressions.add(constraint669);
                Set<Effect> effectsForeffect670Var = new HashSet<Effect>();
                effectsForeffect670Var.add(effect670);
                effects.put((Parameter<?>) effect670Var, effectsForeffect670Var);
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

            public ConstraintExpression constraint671 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346103518161_525445_14678_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346103478833_548117_14651_existsDependency = null;

            public ElaborationRule elaborationRule672 = null;

            public ElaborationRule elaborationRule673 = null;

            public void init_17_0_5_edc0357_1346103606797_653782_14706Members() {
                try {
                    _17_0_5_edc0357_1346103518161_525445_14678_exists = new BooleanParameter("_17_0_5_edc0357_1346103518161_525445_14678_exists", this);
                    _17_0_5_edc0357_1346103010487_691704_14535_endTime = new IntegerParameter("_17_0_5_edc0357_1346103010487_691704_14535_endTime", this);
                    _17_0_5_edc0357_1346103478833_548117_14651_exists = new BooleanParameter("_17_0_5_edc0357_1346103478833_548117_14651_exists", this);
                    constraint671 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346103010487_691704_14535_endTime)));
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
                constraintExpressions.add(constraint671);
                dependencies.add(_17_0_5_edc0357_1346103518161_525445_14678_existsDependency);
                dependencies.add(_17_0_5_edc0357_1346103478833_548117_14651_existsDependency);
            }

            public void init_17_0_5_edc0357_1346103606797_653782_14706Elaborations() {
                Expression<?>[] arguments672 = new Expression<?>[1];
                arguments672[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition672 = new Expression<Boolean>(_17_0_5_edc0357_1346103518161_525445_14678_exists);
                elaborationRule672 = addElaborationRule(condition672, _17_0_5_edc0357_1346102095091_571012_14257.this, LADWP._17_0_5_edc0357_1346102095091_571012_14257._17_0_5_edc0357_1346103518161_525445_14678.class, "_ValueSpecificationAction_waitForDRResponse", arguments672);
                Expression<?>[] arguments673 = new Expression<?>[1];
                arguments673[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition673 = new Expression<Boolean>(_17_0_5_edc0357_1346103478833_548117_14651_exists);
                elaborationRule673 = addElaborationRule(condition673, _17_0_5_edc0357_1346102095091_571012_14257.this, LADWP._17_0_5_edc0357_1346102095091_571012_14257._17_0_5_edc0357_1346103478833_548117_14651.class, "_ReadSelfAction_waitForDRResponse", arguments673);
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
