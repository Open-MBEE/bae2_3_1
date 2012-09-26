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

public class Customer extends ParameterListenerImpl {

    public StringParameter classifierBehavior = null;

    public Parameter< ObjectFlow<Power_System.SignalreceiveMeterReading> > q_Customer_receiveMeterReading = null;

    public Parameter< TimeVaryingMap<Integer> > usage__17_0_5_edc0357_1345510113561_560076_13779 = null;

    public Parameter< ObjectFlow<Power_System.Signaldr_request> > q_Customer_dr_request = null;

    public Parameter< TimeVaryingMap<Integer> > cap__17_0_5_edc0357_1345510113566_55150_13784 = null;

    public Parameter< Power_System > x = null;

    public Parameter< ObjectFlow<Power_System.Signalyes> > q_Customer_yes = null;

    public Parameter< ObjectFlow<Power_System.SignalchangeLoadValue> > q_Customer_changeLoadValue = null;

    public Parameter< ObjectFlow<Power_System.Signalno> > q_Customer_no = null;

    public void initCustomerMembers() {
        try {
            classifierBehavior = new StringParameter("classifierBehavior", this);
            q_Customer_receiveMeterReading = new Parameter<ObjectFlow<Power_System.SignalreceiveMeterReading>>("q_Customer_receiveMeterReading", null, new ObjectFlow("q_Customer_receiveMeterReading", Power_System.SignalreceiveMeterReading.class), this);
            usage__17_0_5_edc0357_1345510113561_560076_13779 = new Parameter<TimeVaryingMap<Integer>>("usage__17_0_5_edc0357_1345510113561_560076_13779", null, new TimeVaryingMap("usage"), this);
            q_Customer_dr_request = new Parameter<ObjectFlow<Power_System.Signaldr_request>>("q_Customer_dr_request", null, new ObjectFlow("q_Customer_dr_request", Power_System.Signaldr_request.class), this);
            cap__17_0_5_edc0357_1345510113566_55150_13784 = new Parameter<TimeVaryingMap<Integer>>("cap__17_0_5_edc0357_1345510113566_55150_13784", null, new TimeVaryingMap("cap"), this);
            x = new Parameter<Power_System>("x", null, null, this);
            q_Customer_yes = new Parameter<ObjectFlow<Power_System.Signalyes>>("q_Customer_yes", null, new ObjectFlow("q_Customer_yes", Power_System.Signalyes.class), this);
            q_Customer_changeLoadValue = new Parameter<ObjectFlow<Power_System.SignalchangeLoadValue>>("q_Customer_changeLoadValue", null, new ObjectFlow("q_Customer_changeLoadValue", Power_System.SignalchangeLoadValue.class), this);
            q_Customer_no = new Parameter<ObjectFlow<Power_System.Signalno>>("q_Customer_no", null, new ObjectFlow("q_Customer_no", Power_System.Signalno.class), this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void initCustomerCollections() {
        parameters.add(classifierBehavior);
        parameters.add(q_Customer_receiveMeterReading);
        parameters.add(usage__17_0_5_edc0357_1345510113561_560076_13779);
        parameters.add(q_Customer_dr_request);
        parameters.add(cap__17_0_5_edc0357_1345510113566_55150_13784);
        parameters.add(x);
        parameters.add(q_Customer_yes);
        parameters.add(q_Customer_changeLoadValue);
        parameters.add(q_Customer_no);
    }

    public void initCustomerElaborations() {
    }

    public Customer() {
        super();
        initCustomerMembers();
        initCustomerCollections();
        initCustomerElaborations();
    }

    public class _17_0_5_edc0357_1345510113556_623276_13775 extends DurativeEvent {

        public Parameter< ObjectFlow<Object> > sig_17_0_5_edc0357_1346280185062_487679_12810 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_5_edc0357_1345510114028_727317_14215 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_5_edc0357_1345510114028_129199_14214 = null;

        public IntegerParameter invoke_time = null;

        public ElaborationRule elaborationRule26 = null;

        public void init_17_0_5_edc0357_1345510113556_623276_13775Members() {
            try {
                sig_17_0_5_edc0357_1346280185062_487679_12810 = new Parameter<ObjectFlow<Object>>("sig_17_0_5_edc0357_1346280185062_487679_12810", null, new ObjectFlow("sig_17_0_5_edc0357_1346280185062_487679_12810"), this);
                sig_17_0_5_edc0357_1345510114028_727317_14215 = new Parameter<ObjectFlow<Integer>>("sig_17_0_5_edc0357_1345510114028_727317_14215", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114028_727317_14215"), this);
                sig_17_0_5_edc0357_1345510114028_129199_14214 = new Parameter<ObjectFlow<Integer>>("sig_17_0_5_edc0357_1345510114028_129199_14214", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114028_129199_14214"), this);
                invoke_time = new IntegerParameter("invoke_time", this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void init_17_0_5_edc0357_1345510113556_623276_13775Collections() {
            parameters.add(sig_17_0_5_edc0357_1346280185062_487679_12810);
            parameters.add(sig_17_0_5_edc0357_1345510114028_727317_14215);
            parameters.add(sig_17_0_5_edc0357_1345510114028_129199_14214);
            parameters.add(invoke_time);
        }

        public void init_17_0_5_edc0357_1345510113556_623276_13775Elaborations() {
            Expression<?>[] arguments26 = new Expression<?>[1];
            arguments26[0] = new Expression<Integer>(invoke_time);
            Expression<Boolean> condition26 = new Expression<Boolean>(true);
            elaborationRule26 = addElaborationRule(condition26, _17_0_5_edc0357_1345510113556_623276_13775.this, Customer._17_0_5_edc0357_1345510113556_623276_13775._17_0_5_edc0357_1345510114026_43123_14199.class, "_InitialNode_usePower", arguments26);
        }

        public _17_0_5_edc0357_1345510113556_623276_13775() {
            super();
            init_17_0_5_edc0357_1345510113556_623276_13775Members();
            init_17_0_5_edc0357_1345510113556_623276_13775Collections();
            init_17_0_5_edc0357_1345510113556_623276_13775Elaborations();
        }

        public class _17_0_5_edc0357_1345510114026_43123_14199 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1345510114026_592814_14200_exists = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114026_592814_14200_existsDependency = null;

            public ElaborationRule elaborationRule27 = null;

            public void init_17_0_5_edc0357_1345510114026_43123_14199Members() {
                try {
                    _17_0_5_edc0357_1345510114026_592814_14200_exists = new BooleanParameter("_17_0_5_edc0357_1345510114026_592814_14200_exists", this);
                    _17_0_5_edc0357_1345510114026_592814_14200_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114026_592814_14200_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114026_43123_14199Collections() {
                parameters.add(_17_0_5_edc0357_1345510114026_592814_14200_exists);
                dependencies.add(_17_0_5_edc0357_1345510114026_592814_14200_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510114026_43123_14199Elaborations() {
                Expression<?>[] arguments27 = new Expression<?>[1];
                arguments27[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition27 = new Expression<Boolean>(_17_0_5_edc0357_1345510114026_592814_14200_exists);
                elaborationRule27 = addElaborationRule(condition27, _17_0_5_edc0357_1345510113556_623276_13775.this, Customer._17_0_5_edc0357_1345510113556_623276_13775._17_0_5_edc0357_1345510114026_592814_14200.class, "_ReadSelfAction_usePower", arguments27);
            }

            public _17_0_5_edc0357_1345510114026_43123_14199() {
                super();
                init_17_0_5_edc0357_1345510114026_43123_14199Members();
                init_17_0_5_edc0357_1345510114026_43123_14199Collections();
                init_17_0_5_edc0357_1345510114026_43123_14199Elaborations();
            }

            public _17_0_5_edc0357_1345510114026_43123_14199(Expression<Integer> startTime) {
                super();
                init_17_0_5_edc0357_1345510114026_43123_14199Members();
                init_17_0_5_edc0357_1345510114026_43123_14199Collections();
                removeDependency(this.startTime);
                addDependency(this.startTime, startTime);
                init_17_0_5_edc0357_1345510114026_43123_14199Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114026_592814_14200 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1345510114026_43123_14199_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1345510114027_337563_14204_exists = null;

            public Parameter< Customer > _17_0_5_edc0357_1345510114780_387271_15102 = null;

            public ConstraintExpression constraint28 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114027_337563_14204_existsDependency = null;

            public ElaborationRule elaborationRule29 = null;

            public void init_17_0_5_edc0357_1345510114026_592814_14200Members() {
                try {
                    _17_0_5_edc0357_1345510114026_43123_14199_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114026_43123_14199_endTime", this);
                    _17_0_5_edc0357_1345510114027_337563_14204_exists = new BooleanParameter("_17_0_5_edc0357_1345510114027_337563_14204_exists", this);
                    _17_0_5_edc0357_1345510114780_387271_15102 = new Parameter<Customer>("_17_0_5_edc0357_1345510114780_387271_15102", null, Customer.this, this);
                    constraint28 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114026_43123_14199_endTime)));
                    _17_0_5_edc0357_1345510114027_337563_14204_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114027_337563_14204_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114026_592814_14200Collections() {
                parameters.add(_17_0_5_edc0357_1345510114026_43123_14199_endTime);
                parameters.add(_17_0_5_edc0357_1345510114027_337563_14204_exists);
                parameters.add(_17_0_5_edc0357_1345510114780_387271_15102);
                constraintExpressions.add(constraint28);
                dependencies.add(_17_0_5_edc0357_1345510114027_337563_14204_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510114026_592814_14200Elaborations() {
                Expression<?>[] arguments29 = new Expression<?>[2];
                arguments29[0] = new Expression<Integer>(endTime);
                arguments29[1] = new Expression<Customer>(_17_0_5_edc0357_1345510114780_387271_15102);
                Expression<Boolean> condition29 = new Expression<Boolean>(_17_0_5_edc0357_1345510114027_337563_14204_exists);
                elaborationRule29 = addElaborationRule(condition29, _17_0_5_edc0357_1345510113556_623276_13775.this, Customer._17_0_5_edc0357_1345510113556_623276_13775._17_0_5_edc0357_1345510114027_337563_14204.class, "_ForkNode_usePower", arguments29);
            }

            public _17_0_5_edc0357_1345510114026_592814_14200() {
                super();
                init_17_0_5_edc0357_1345510114026_592814_14200Members();
                init_17_0_5_edc0357_1345510114026_592814_14200Collections();
                init_17_0_5_edc0357_1345510114026_592814_14200Elaborations();
            }

            public _17_0_5_edc0357_1345510114026_592814_14200(Expression<Integer> _17_0_5_edc0357_1345510114026_43123_14199_endTime) {
                super();
                init_17_0_5_edc0357_1345510114026_592814_14200Members();
                init_17_0_5_edc0357_1345510114026_592814_14200Collections();
                addDependency(this._17_0_5_edc0357_1345510114026_43123_14199_endTime, _17_0_5_edc0357_1345510114026_43123_14199_endTime);
                init_17_0_5_edc0357_1345510114026_592814_14200Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114026_22734_14201 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1346280179065_311539_12799_endTime = null;

            public ConstraintExpression constraint30 = null;

            public void init_17_0_5_edc0357_1345510114026_22734_14201Members() {
                try {
                    _17_0_5_edc0357_1346280179065_311539_12799_endTime = new IntegerParameter("_17_0_5_edc0357_1346280179065_311539_12799_endTime", this);
                    constraint30 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346280179065_311539_12799_endTime)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114026_22734_14201Collections() {
                parameters.add(_17_0_5_edc0357_1346280179065_311539_12799_endTime);
                constraintExpressions.add(constraint30);
            }

            public void init_17_0_5_edc0357_1345510114026_22734_14201Elaborations() {
            }

            public _17_0_5_edc0357_1345510114026_22734_14201() {
                super();
                init_17_0_5_edc0357_1345510114026_22734_14201Members();
                init_17_0_5_edc0357_1345510114026_22734_14201Collections();
                init_17_0_5_edc0357_1345510114026_22734_14201Elaborations();
            }

            public _17_0_5_edc0357_1345510114026_22734_14201(Expression<Integer> _17_0_5_edc0357_1346280179065_311539_12799_endTime) {
                super();
                init_17_0_5_edc0357_1345510114026_22734_14201Members();
                init_17_0_5_edc0357_1345510114026_22734_14201Collections();
                addDependency(this._17_0_5_edc0357_1346280179065_311539_12799_endTime, _17_0_5_edc0357_1346280179065_311539_12799_endTime);
                init_17_0_5_edc0357_1345510114026_22734_14201Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114027_454308_14202 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1345510114781_279299_15103 = null;

            public Parameter< Customer > _17_0_5_edc0357_1345510114781_225445_15104 = null;

            public IntegerParameter _17_0_5_edc0357_1345510114027_337563_14204_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1345510114027_851146_14206_exists = null;

            public ConstraintExpression constraint31 = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114781_279299_15103Dependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114027_851146_14206_existsDependency = null;

            public ElaborationRule elaborationRule32 = null;

            public void init_17_0_5_edc0357_1345510114027_454308_14202Members() {
                try {
                    _17_0_5_edc0357_1345510114781_279299_15103 = new IntegerParameter("_17_0_5_edc0357_1345510114781_279299_15103", this);
                    _17_0_5_edc0357_1345510114781_225445_15104 = new Parameter<Customer>("_17_0_5_edc0357_1345510114781_225445_15104", null, null, this);
                    _17_0_5_edc0357_1345510114027_337563_14204_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114027_337563_14204_endTime", this);
                    _17_0_5_edc0357_1345510114027_851146_14206_exists = new BooleanParameter("_17_0_5_edc0357_1345510114027_851146_14206_exists", this);
                    constraint31 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114027_337563_14204_endTime)));
                    FunctionCall nestedCall3 = new FunctionCall(null, TimeVaryingMap.class, "getValue", new Object[] { startTime } );
                    FunctionCall nestedCall2 = new FunctionCall(null, Parameter.class, "getValue", (Object[]) null, nestedCall3 );
                    FunctionCall call1 = new FunctionCall(_17_0_5_edc0357_1345510114781_225445_15104, Parameter.class, "getMember", new Object[] { "usage__17_0_5_edc0357_1345510113561_560076_13779" }, nestedCall2 );
                    _17_0_5_edc0357_1345510114781_279299_15103Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114781_279299_15103, new Expression< Integer >(call1));
                    // Above 4 lines replaces line below; note that the TimeVaryingMap.class is substituted for the wrong class name, "Customer...14202," below.
                    //_17_0_5_edc0357_1345510114781_279299_15103Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114781_279299_15103, new Expression(new FunctionCall((Object) (((Parameter<?>) (_17_0_5_edc0357_1345510114781_225445_15104.getMember("usage__17_0_5_edc0357_1345510113561_560076_13779"))).getValue()), Utils.getMethodForArgTypes("Customer._17_0_5_edc0357_1345510113556_623276_13775._17_0_5_edc0357_1345510114027_454308_14202", "generated", "getValue"), new Object[] { new Expression<Integer>(startTime) })));
                    _17_0_5_edc0357_1345510114027_851146_14206_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114027_851146_14206_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114027_454308_14202Collections() {
                parameters.add(_17_0_5_edc0357_1345510114781_279299_15103);
                parameters.add(_17_0_5_edc0357_1345510114781_225445_15104);
                parameters.add(_17_0_5_edc0357_1345510114027_337563_14204_endTime);
                parameters.add(_17_0_5_edc0357_1345510114027_851146_14206_exists);
                constraintExpressions.add(constraint31);
                dependencies.add(_17_0_5_edc0357_1345510114781_279299_15103Dependency);
                dependencies.add(_17_0_5_edc0357_1345510114027_851146_14206_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510114027_454308_14202Elaborations() {
                Expression<?>[] arguments32 = new Expression<?>[2];
                arguments32[0] = new Expression<Integer>(endTime);
                arguments32[1] = new Expression<Integer>(_17_0_5_edc0357_1345510114781_279299_15103);
                Expression<Boolean> condition32 = new Expression<Boolean>(_17_0_5_edc0357_1345510114027_851146_14206_exists);
                elaborationRule32 = addElaborationRule(condition32, _17_0_5_edc0357_1345510113556_623276_13775.this, Customer._17_0_5_edc0357_1345510113556_623276_13775._17_0_5_edc0357_1345510114027_851146_14206.class, "fn2_ForkNode_usePower", arguments32);
            }

            public _17_0_5_edc0357_1345510114027_454308_14202() {
                super();
                init_17_0_5_edc0357_1345510114027_454308_14202Members();
                init_17_0_5_edc0357_1345510114027_454308_14202Collections();
                init_17_0_5_edc0357_1345510114027_454308_14202Elaborations();
            }

            public _17_0_5_edc0357_1345510114027_454308_14202(Expression<Integer> _17_0_5_edc0357_1345510114027_337563_14204_endTime, Expression<Customer> _17_0_5_edc0357_1345510114781_225445_15104) {
                super();
                init_17_0_5_edc0357_1345510114027_454308_14202Members();
                init_17_0_5_edc0357_1345510114027_454308_14202Collections();
                addDependency(this._17_0_5_edc0357_1345510114027_337563_14204_endTime, _17_0_5_edc0357_1345510114027_337563_14204_endTime);
                addDependency(this._17_0_5_edc0357_1345510114781_225445_15104, _17_0_5_edc0357_1345510114781_225445_15104);
                init_17_0_5_edc0357_1345510114027_454308_14202Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114027_512113_14203 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1345510114027_337563_14204_endTime = null;

            public IntegerParameter _17_0_5_edc0357_1345510114783_59952_15106 = null;

            public Parameter< Customer > _17_0_5_edc0357_1345510114782_358183_15105 = null;

            public BooleanParameter _17_0_5_edc0357_1346280179065_311539_12799_exists = null;

            public ConstraintExpression constraint33 = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114783_59952_15106Dependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346280179065_311539_12799_existsDependency = null;

            public Effect effect34 = null;

            public Object effect34Var = null;

            public ElaborationRule elaborationRule35 = null;

            public void init_17_0_5_edc0357_1345510114027_512113_14203Members() {
                try {
                    _17_0_5_edc0357_1345510114027_337563_14204_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114027_337563_14204_endTime", this);
                    _17_0_5_edc0357_1345510114783_59952_15106 = new IntegerParameter("_17_0_5_edc0357_1345510114783_59952_15106", this);
                    _17_0_5_edc0357_1345510114782_358183_15105 = new Parameter<Customer>("_17_0_5_edc0357_1345510114782_358183_15105", null, null, this);
                    _17_0_5_edc0357_1346280179065_311539_12799_exists = new BooleanParameter("_17_0_5_edc0357_1346280179065_311539_12799_exists", this);
                    constraint33 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114027_337563_14204_endTime)));
                    _17_0_5_edc0357_1345510114783_59952_15106Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114783_59952_15106, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114028_129199_14214, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1346280179065_311539_12799_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346280179065_311539_12799_exists, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1346280185062_487679_12810, Utils.getMethodForArgTypes("ObjectFlow<Object>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })));
                    effect34Var = (((Parameter<?>) (x.getMember("ss_17_0_5_edc0357_1345510113562_98635_13780_changeLoadValue"))).getValue());
                    effect34 = new EffectFunction(new FunctionCall((Object) effect34Var, Utils.getMethodForArgTypes("Customer._17_0_5_edc0357_1345510113556_623276_13775._17_0_5_edc0357_1345510114027_512113_14203", "generated", "send"), new Object[] { x.getValue().new SignalchangeLoadValue(_17_0_5_edc0357_1345510114783_59952_15106.getValue()), endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114027_512113_14203Collections() {
                parameters.add(_17_0_5_edc0357_1345510114027_337563_14204_endTime);
                parameters.add(_17_0_5_edc0357_1345510114783_59952_15106);
                parameters.add(_17_0_5_edc0357_1345510114782_358183_15105);
                parameters.add(_17_0_5_edc0357_1346280179065_311539_12799_exists);
                constraintExpressions.add(constraint33);
                dependencies.add(_17_0_5_edc0357_1345510114783_59952_15106Dependency);
                dependencies.add(_17_0_5_edc0357_1346280179065_311539_12799_existsDependency);
                Set<Effect> effectsForeffect34Var = new HashSet<Effect>();
                effectsForeffect34Var.add(effect34);
                effects.put((Parameter<?>) effect34Var, effectsForeffect34Var);
            }

            public void init_17_0_5_edc0357_1345510114027_512113_14203Elaborations() {
                Expression<?>[] arguments35 = new Expression<?>[1];
                arguments35[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition35 = new Expression<Boolean>(_17_0_5_edc0357_1346280179065_311539_12799_exists);
                elaborationRule35 = addElaborationRule(condition35, _17_0_5_edc0357_1345510113556_623276_13775.this, Customer._17_0_5_edc0357_1345510113556_623276_13775._17_0_5_edc0357_1346280179065_311539_12799.class, "fnn1_JoinNode_usePower", arguments35);
            }

            public _17_0_5_edc0357_1345510114027_512113_14203() {
                super();
                init_17_0_5_edc0357_1345510114027_512113_14203Members();
                init_17_0_5_edc0357_1345510114027_512113_14203Collections();
                init_17_0_5_edc0357_1345510114027_512113_14203Elaborations();
            }

            public _17_0_5_edc0357_1345510114027_512113_14203(Expression<Integer> _17_0_5_edc0357_1345510114027_337563_14204_endTime, Expression<Customer> _17_0_5_edc0357_1345510114782_358183_15105) {
                super();
                init_17_0_5_edc0357_1345510114027_512113_14203Members();
                init_17_0_5_edc0357_1345510114027_512113_14203Collections();
                addDependency(this._17_0_5_edc0357_1345510114027_337563_14204_endTime, _17_0_5_edc0357_1345510114027_337563_14204_endTime);
                addDependency(this._17_0_5_edc0357_1345510114782_358183_15105, _17_0_5_edc0357_1345510114782_358183_15105);
                init_17_0_5_edc0357_1345510114027_512113_14203Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114027_337563_14204 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1345510114027_454308_14202_exists = null;

            public Parameter< Customer > objectToPass = null;

            public BooleanParameter _17_0_5_edc0357_1345510114027_512113_14203_exists = null;

            public IntegerParameter _17_0_5_edc0357_1345510114026_592814_14200_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1345510114027_379047_14205_exists = null;

            public ConstraintExpression constraint36 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114027_454308_14202_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114027_512113_14203_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114027_379047_14205_existsDependency = null;

            public ElaborationRule elaborationRule37 = null;

            public ElaborationRule elaborationRule38 = null;

            public ElaborationRule elaborationRule39 = null;

            public void init_17_0_5_edc0357_1345510114027_337563_14204Members() {
                try {
                    _17_0_5_edc0357_1345510114027_454308_14202_exists = new BooleanParameter("_17_0_5_edc0357_1345510114027_454308_14202_exists", this);
                    objectToPass = new Parameter<Customer>("objectToPass", null, null, this);
                    _17_0_5_edc0357_1345510114027_512113_14203_exists = new BooleanParameter("_17_0_5_edc0357_1345510114027_512113_14203_exists", this);
                    _17_0_5_edc0357_1345510114026_592814_14200_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114026_592814_14200_endTime", this);
                    _17_0_5_edc0357_1345510114027_379047_14205_exists = new BooleanParameter("_17_0_5_edc0357_1345510114027_379047_14205_exists", this);
                    constraint36 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114026_592814_14200_endTime)));
                    _17_0_5_edc0357_1345510114027_454308_14202_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114027_454308_14202_exists, new Expression<Boolean>(true));
                    _17_0_5_edc0357_1345510114027_512113_14203_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114027_512113_14203_exists, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114028_129199_14214, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })));
                    _17_0_5_edc0357_1345510114027_379047_14205_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114027_379047_14205_exists, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114028_727317_14215, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114027_337563_14204Collections() {
                parameters.add(_17_0_5_edc0357_1345510114027_454308_14202_exists);
                parameters.add(objectToPass);
                parameters.add(_17_0_5_edc0357_1345510114027_512113_14203_exists);
                parameters.add(_17_0_5_edc0357_1345510114026_592814_14200_endTime);
                parameters.add(_17_0_5_edc0357_1345510114027_379047_14205_exists);
                constraintExpressions.add(constraint36);
                dependencies.add(_17_0_5_edc0357_1345510114027_454308_14202_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510114027_512113_14203_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510114027_379047_14205_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510114027_337563_14204Elaborations() {
                Expression<?>[] arguments37 = new Expression<?>[2];
                arguments37[0] = new Expression<Integer>(endTime);
                arguments37[1] = new Expression<Customer>(objectToPass);
                Expression<Boolean> condition37 = new Expression<Boolean>(_17_0_5_edc0357_1345510114027_454308_14202_exists);
                elaborationRule37 = addElaborationRule(condition37, _17_0_5_edc0357_1345510113556_623276_13775.this, Customer._17_0_5_edc0357_1345510113556_623276_13775._17_0_5_edc0357_1345510114027_454308_14202.class, "rsfa_up_ReadStructuralFeatureAction_usePower", arguments37);
                Expression<?>[] arguments38 = new Expression<?>[2];
                arguments38[0] = new Expression<Integer>(endTime);
                arguments38[1] = new Expression<Customer>(objectToPass);
                Expression<Boolean> condition38 = new Expression<Boolean>(_17_0_5_edc0357_1345510114027_379047_14205_exists);
                elaborationRule38 = addElaborationRule(condition38, _17_0_5_edc0357_1345510113556_623276_13775.this, Customer._17_0_5_edc0357_1345510113556_623276_13775._17_0_5_edc0357_1345510114027_379047_14205.class, "rmr1_SendSignalAction_usePower", arguments38);
                Expression<?>[] arguments39 = new Expression<?>[2];
                arguments39[0] = new Expression<Integer>(endTime);
                arguments39[1] = new Expression<Customer>(objectToPass);
                Expression<Boolean> condition39 = new Expression<Boolean>(_17_0_5_edc0357_1345510114027_512113_14203_exists);
                elaborationRule39 = addElaborationRule(condition39, _17_0_5_edc0357_1345510113556_623276_13775.this, Customer._17_0_5_edc0357_1345510113556_623276_13775._17_0_5_edc0357_1345510114027_512113_14203.class, "clvsend_SendSignalAction_usePower", arguments39);
            }

            public _17_0_5_edc0357_1345510114027_337563_14204() {
                super();
                init_17_0_5_edc0357_1345510114027_337563_14204Members();
                init_17_0_5_edc0357_1345510114027_337563_14204Collections();
                init_17_0_5_edc0357_1345510114027_337563_14204Elaborations();
            }

            public _17_0_5_edc0357_1345510114027_337563_14204(Expression<Integer> _17_0_5_edc0357_1345510114026_592814_14200_endTime, Expression<Customer> objectToPass) {
                super();
                init_17_0_5_edc0357_1345510114027_337563_14204Members();
                init_17_0_5_edc0357_1345510114027_337563_14204Collections();
                addDependency(this._17_0_5_edc0357_1345510114026_592814_14200_endTime, _17_0_5_edc0357_1345510114026_592814_14200_endTime);
                addDependency(this.objectToPass, objectToPass);
                init_17_0_5_edc0357_1345510114027_337563_14204Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114027_379047_14205 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1345510114027_337563_14204_endTime = null;

            public Parameter< Customer > _17_0_5_edc0357_1345510114784_30882_15107 = null;

            public IntegerParameter _17_0_5_edc0357_1345510114784_286838_15108 = null;

            public ConstraintExpression constraint40 = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114784_286838_15108Dependency = null;

            public Effect effect41 = null;

            public Object effect41Var = null;

            public Effect effect42 = null;

            public Object effect42Var = null;

            public void init_17_0_5_edc0357_1345510114027_379047_14205Members() {
                try {
                    _17_0_5_edc0357_1345510114027_337563_14204_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114027_337563_14204_endTime", this);
                    _17_0_5_edc0357_1345510114784_30882_15107 = new Parameter<Customer>("_17_0_5_edc0357_1345510114784_30882_15107", null, null, this);
                    _17_0_5_edc0357_1345510114784_286838_15108 = new IntegerParameter("_17_0_5_edc0357_1345510114784_286838_15108", this);
                    constraint40 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114027_337563_14204_endTime)));
                    _17_0_5_edc0357_1345510114784_286838_15108Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114784_286838_15108, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114028_727317_14215, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    effect41Var = sig_17_0_5_edc0357_1346280185062_487679_12810;
                    effect41 = new EffectFunction(new FunctionCall((Object) effect41Var, Utils.getMethodForArgTypes("ObjectFlow<Object>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { true, startTime }));
                    effect42Var = (((Parameter<?>) (x.getMember("ss_17_0_5_edc0357_1345510113565_310611_13783_receiveMeterReading"))).getValue());
                    effect42 = new EffectFunction(new FunctionCall((Object) effect42Var, Utils.getMethodForArgTypes("Customer._17_0_5_edc0357_1345510113556_623276_13775._17_0_5_edc0357_1345510114027_379047_14205", "generated", "send"), new Object[] { x.getValue().new SignalreceiveMeterReading(_17_0_5_edc0357_1345510114784_286838_15108.getValue()), endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114027_379047_14205Collections() {
                parameters.add(_17_0_5_edc0357_1345510114027_337563_14204_endTime);
                parameters.add(_17_0_5_edc0357_1345510114784_30882_15107);
                parameters.add(_17_0_5_edc0357_1345510114784_286838_15108);
                constraintExpressions.add(constraint40);
                dependencies.add(_17_0_5_edc0357_1345510114784_286838_15108Dependency);
                Set<Effect> effectsForeffect41Var = new HashSet<Effect>();
                effectsForeffect41Var.add(effect41);
                effects.put((Parameter<?>) effect41Var, effectsForeffect41Var);
                Set<Effect> effectsForeffect42Var = new HashSet<Effect>();
                effectsForeffect42Var.add(effect42);
                effects.put((Parameter<?>) effect42Var, effectsForeffect42Var);
            }

            public void init_17_0_5_edc0357_1345510114027_379047_14205Elaborations() {
            }

            public _17_0_5_edc0357_1345510114027_379047_14205() {
                super();
                init_17_0_5_edc0357_1345510114027_379047_14205Members();
                init_17_0_5_edc0357_1345510114027_379047_14205Collections();
                init_17_0_5_edc0357_1345510114027_379047_14205Elaborations();
            }

            public _17_0_5_edc0357_1345510114027_379047_14205(Expression<Integer> _17_0_5_edc0357_1345510114027_337563_14204_endTime, Expression<Customer> _17_0_5_edc0357_1345510114784_30882_15107) {
                super();
                init_17_0_5_edc0357_1345510114027_379047_14205Members();
                init_17_0_5_edc0357_1345510114027_379047_14205Collections();
                addDependency(this._17_0_5_edc0357_1345510114027_337563_14204_endTime, _17_0_5_edc0357_1345510114027_337563_14204_endTime);
                addDependency(this._17_0_5_edc0357_1345510114784_30882_15107, _17_0_5_edc0357_1345510114784_30882_15107);
                init_17_0_5_edc0357_1345510114027_379047_14205Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114027_851146_14206 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1345510114027_454308_14202_endTime = null;

            public IntegerParameter objectToPass = null;

            public ConstraintExpression constraint43 = null;

            public Effect effect44 = null;

            public Object effect44Var = null;

            public Effect effect45 = null;

            public Object effect45Var = null;

            public void init_17_0_5_edc0357_1345510114027_851146_14206Members() {
                try {
                    _17_0_5_edc0357_1345510114027_454308_14202_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114027_454308_14202_endTime", this);
                    objectToPass = new IntegerParameter("objectToPass", this);
                    constraint43 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114027_454308_14202_endTime)));
                    effect44Var = sig_17_0_5_edc0357_1345510114028_129199_14214;
                    effect44 = new EffectFunction(new FunctionCall((Object) effect44Var, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                    effect45Var = sig_17_0_5_edc0357_1345510114028_727317_14215;
                    effect45 = new EffectFunction(new FunctionCall((Object) effect45Var, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114027_851146_14206Collections() {
                parameters.add(_17_0_5_edc0357_1345510114027_454308_14202_endTime);
                parameters.add(objectToPass);
                constraintExpressions.add(constraint43);
                Set<Effect> effectsForeffect44Var = new HashSet<Effect>();
                effectsForeffect44Var.add(effect44);
                effects.put((Parameter<?>) effect44Var, effectsForeffect44Var);
                Set<Effect> effectsForeffect45Var = new HashSet<Effect>();
                effectsForeffect45Var.add(effect45);
                effects.put((Parameter<?>) effect45Var, effectsForeffect45Var);
            }

            public void init_17_0_5_edc0357_1345510114027_851146_14206Elaborations() {
            }

            public _17_0_5_edc0357_1345510114027_851146_14206() {
                super();
                init_17_0_5_edc0357_1345510114027_851146_14206Members();
                init_17_0_5_edc0357_1345510114027_851146_14206Collections();
                init_17_0_5_edc0357_1345510114027_851146_14206Elaborations();
            }

            public _17_0_5_edc0357_1345510114027_851146_14206(Expression<Integer> _17_0_5_edc0357_1345510114027_454308_14202_endTime, Expression<Integer> objectToPass) {
                super();
                init_17_0_5_edc0357_1345510114027_851146_14206Members();
                init_17_0_5_edc0357_1345510114027_851146_14206Collections();
                addDependency(this._17_0_5_edc0357_1345510114027_454308_14202_endTime, _17_0_5_edc0357_1345510114027_454308_14202_endTime);
                addDependency(this.objectToPass, objectToPass);
                init_17_0_5_edc0357_1345510114027_851146_14206Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1346280179065_311539_12799 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1345510114026_22734_14201_exists = null;

            public IntegerParameter _17_0_5_edc0357_1345510114027_512113_14203_endTime = null;

            public ConstraintExpression constraint46 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114026_22734_14201_existsDependency = null;

            public ElaborationRule elaborationRule47 = null;

            public void init_17_0_5_edc0357_1346280179065_311539_12799Members() {
                try {
                    _17_0_5_edc0357_1345510114026_22734_14201_exists = new BooleanParameter("_17_0_5_edc0357_1345510114026_22734_14201_exists", this);
                    _17_0_5_edc0357_1345510114027_512113_14203_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114027_512113_14203_endTime", this);
                    constraint46 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114027_512113_14203_endTime)));
                    _17_0_5_edc0357_1345510114026_22734_14201_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114026_22734_14201_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346280179065_311539_12799Collections() {
                parameters.add(_17_0_5_edc0357_1345510114026_22734_14201_exists);
                parameters.add(_17_0_5_edc0357_1345510114027_512113_14203_endTime);
                constraintExpressions.add(constraint46);
                dependencies.add(_17_0_5_edc0357_1345510114026_22734_14201_existsDependency);
            }

            public void init_17_0_5_edc0357_1346280179065_311539_12799Elaborations() {
                Expression<?>[] arguments47 = new Expression<?>[1];
                arguments47[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition47 = new Expression<Boolean>(_17_0_5_edc0357_1345510114026_22734_14201_exists);
                elaborationRule47 = addElaborationRule(condition47, _17_0_5_edc0357_1345510113556_623276_13775.this, Customer._17_0_5_edc0357_1345510113556_623276_13775._17_0_5_edc0357_1345510114026_22734_14201.class, "_ActivityFinalNode_usePower", arguments47);
            }

            public _17_0_5_edc0357_1346280179065_311539_12799() {
                super();
                init_17_0_5_edc0357_1346280179065_311539_12799Members();
                init_17_0_5_edc0357_1346280179065_311539_12799Collections();
                init_17_0_5_edc0357_1346280179065_311539_12799Elaborations();
            }

            public _17_0_5_edc0357_1346280179065_311539_12799(Expression<Integer> _17_0_5_edc0357_1345510114027_512113_14203_endTime) {
                super();
                init_17_0_5_edc0357_1346280179065_311539_12799Members();
                init_17_0_5_edc0357_1346280179065_311539_12799Collections();
                addDependency(this._17_0_5_edc0357_1345510114027_512113_14203_endTime, _17_0_5_edc0357_1345510114027_512113_14203_endTime);
                init_17_0_5_edc0357_1346280179065_311539_12799Elaborations();
                fixTimeDependencies();
            }
        }

        public _17_0_5_edc0357_1345510113556_623276_13775(Expression<Integer> endTime) {
            super();
            init_17_0_5_edc0357_1345510113556_623276_13775Members();
            init_17_0_5_edc0357_1345510113556_623276_13775Collections();
            removeDependency(this.endTime);
            addDependency(this.endTime, endTime);
            init_17_0_5_edc0357_1345510113556_623276_13775Elaborations();
            fixTimeDependencies();
        }
    }

    public class _17_0_5_edc0357_1345510113557_625793_13776 extends DurativeEvent {

        public Parameter< ObjectFlow<Integer> > sig_17_0_5_edc0357_1348163389494_378042_14636 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_5_edc0357_1345510114793_324647_15121 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_5_edc0357_1348163531723_681524_14685 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_5_edc0357_1348163666246_377742_14732 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_5_edc0357_1345510114089_103639_14268 = null;

        public IntegerParameter invoke_time = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_5_edc0357_1348163519133_975185_14677 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_5_edc0357_1348163663482_249339_14727 = null;

        public Parameter< ObjectFlow<Customer> > sig_17_0_5_edc0357_1345510114089_297842_14265 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_5_edc0357_1345510114089_58426_14269 = null;

        public ElaborationRule elaborationRule48 = null;

        public void init_17_0_5_edc0357_1345510113557_625793_13776Members() {
            try {
                sig_17_0_5_edc0357_1348163389494_378042_14636 = new Parameter<ObjectFlow<Integer>>("sig_17_0_5_edc0357_1348163389494_378042_14636", null, new ObjectFlow("sig_17_0_5_edc0357_1348163389494_378042_14636"), this);
                sig_17_0_5_edc0357_1345510114793_324647_15121 = new Parameter<ObjectFlow<Integer>>("sig_17_0_5_edc0357_1345510114793_324647_15121", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114793_324647_15121"), this);
                sig_17_0_5_edc0357_1348163531723_681524_14685 = new Parameter<ObjectFlow<Integer>>("sig_17_0_5_edc0357_1348163531723_681524_14685", null, new ObjectFlow("sig_17_0_5_edc0357_1348163531723_681524_14685"), this);
                sig_17_0_5_edc0357_1348163666246_377742_14732 = new Parameter<ObjectFlow<Integer>>("sig_17_0_5_edc0357_1348163666246_377742_14732", null, new ObjectFlow("sig_17_0_5_edc0357_1348163666246_377742_14732"), this);
                sig_17_0_5_edc0357_1345510114089_103639_14268 = new Parameter<ObjectFlow<Integer>>("sig_17_0_5_edc0357_1345510114089_103639_14268", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114089_103639_14268"), this);
                invoke_time = new IntegerParameter("invoke_time", this);
                sig_17_0_5_edc0357_1348163519133_975185_14677 = new Parameter<ObjectFlow<Integer>>("sig_17_0_5_edc0357_1348163519133_975185_14677", null, new ObjectFlow("sig_17_0_5_edc0357_1348163519133_975185_14677"), this);
                sig_17_0_5_edc0357_1348163663482_249339_14727 = new Parameter<ObjectFlow<Integer>>("sig_17_0_5_edc0357_1348163663482_249339_14727", null, new ObjectFlow("sig_17_0_5_edc0357_1348163663482_249339_14727"), this);
                sig_17_0_5_edc0357_1345510114089_297842_14265 = new Parameter<ObjectFlow<Customer>>("sig_17_0_5_edc0357_1345510114089_297842_14265", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114089_297842_14265"), this);
                sig_17_0_5_edc0357_1345510114089_58426_14269 = new Parameter<ObjectFlow<Integer>>("sig_17_0_5_edc0357_1345510114089_58426_14269", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114089_58426_14269"), this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void init_17_0_5_edc0357_1345510113557_625793_13776Collections() {
            parameters.add(sig_17_0_5_edc0357_1348163389494_378042_14636);
            parameters.add(sig_17_0_5_edc0357_1345510114793_324647_15121);
            parameters.add(sig_17_0_5_edc0357_1348163531723_681524_14685);
            parameters.add(sig_17_0_5_edc0357_1348163666246_377742_14732);
            parameters.add(sig_17_0_5_edc0357_1345510114089_103639_14268);
            parameters.add(invoke_time);
            parameters.add(sig_17_0_5_edc0357_1348163519133_975185_14677);
            parameters.add(sig_17_0_5_edc0357_1348163663482_249339_14727);
            parameters.add(sig_17_0_5_edc0357_1345510114089_297842_14265);
            parameters.add(sig_17_0_5_edc0357_1345510114089_58426_14269);
        }

        public void init_17_0_5_edc0357_1345510113557_625793_13776Elaborations() {
            Expression<?>[] arguments48 = new Expression<?>[1];
            arguments48[0] = new Expression<Integer>(invoke_time);
            Expression<Boolean> condition48 = new Expression<Boolean>(true);
            elaborationRule48 = addElaborationRule(condition48, _17_0_5_edc0357_1345510113557_625793_13776.this, Customer._17_0_5_edc0357_1345510113557_625793_13776._17_0_5_edc0357_1345510114087_35215_14252.class, "_InitialNode_changePowerUsage", arguments48);
        }

        public _17_0_5_edc0357_1345510113557_625793_13776() {
            super();
            init_17_0_5_edc0357_1345510113557_625793_13776Members();
            init_17_0_5_edc0357_1345510113557_625793_13776Collections();
            init_17_0_5_edc0357_1345510113557_625793_13776Elaborations();
        }

        public class _17_0_5_edc0357_1345510114087_484087_14249 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1348000726471_590820_13942_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1345510114088_487345_14255_exists = null;

            public Parameter< Customer > _17_0_5_edc0357_1345510114793_215722_15122 = null;

            public ConstraintExpression constraint49 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114088_487345_14255_existsDependency = null;

            public ElaborationRule elaborationRule50 = null;

            public void init_17_0_5_edc0357_1345510114087_484087_14249Members() {
                try {
                    _17_0_5_edc0357_1348000726471_590820_13942_endTime = new IntegerParameter("_17_0_5_edc0357_1348000726471_590820_13942_endTime", this);
                    _17_0_5_edc0357_1345510114088_487345_14255_exists = new BooleanParameter("_17_0_5_edc0357_1345510114088_487345_14255_exists", this);
                    _17_0_5_edc0357_1345510114793_215722_15122 = new Parameter<Customer>("_17_0_5_edc0357_1345510114793_215722_15122", null, Customer.this, this);
                    constraint49 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1348000726471_590820_13942_endTime)));
                    _17_0_5_edc0357_1345510114088_487345_14255_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114088_487345_14255_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114087_484087_14249Collections() {
                parameters.add(_17_0_5_edc0357_1348000726471_590820_13942_endTime);
                parameters.add(_17_0_5_edc0357_1345510114088_487345_14255_exists);
                parameters.add(_17_0_5_edc0357_1345510114793_215722_15122);
                constraintExpressions.add(constraint49);
                dependencies.add(_17_0_5_edc0357_1345510114088_487345_14255_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510114087_484087_14249Elaborations() {
                Expression<?>[] arguments50 = new Expression<?>[2];
                arguments50[0] = new Expression<Integer>(endTime);
                arguments50[1] = new Expression<Customer>(_17_0_5_edc0357_1345510114793_215722_15122);
                Expression<Boolean> condition50 = new Expression<Boolean>(_17_0_5_edc0357_1345510114088_487345_14255_exists);
                elaborationRule50 = addElaborationRule(condition50, _17_0_5_edc0357_1345510113557_625793_13776.this, Customer._17_0_5_edc0357_1345510113557_625793_13776._17_0_5_edc0357_1345510114088_487345_14255.class, "_ForkNode_changePowerUsage", arguments50);
            }

            public _17_0_5_edc0357_1345510114087_484087_14249() {
                super();
                init_17_0_5_edc0357_1345510114087_484087_14249Members();
                init_17_0_5_edc0357_1345510114087_484087_14249Collections();
                init_17_0_5_edc0357_1345510114087_484087_14249Elaborations();
            }

            public _17_0_5_edc0357_1345510114087_484087_14249(Expression<Integer> _17_0_5_edc0357_1348000726471_590820_13942_endTime) {
                super();
                init_17_0_5_edc0357_1345510114087_484087_14249Members();
                init_17_0_5_edc0357_1345510114087_484087_14249Collections();
                addDependency(this._17_0_5_edc0357_1348000726471_590820_13942_endTime, _17_0_5_edc0357_1348000726471_590820_13942_endTime);
                init_17_0_5_edc0357_1345510114087_484087_14249Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114087_224243_14250 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1348000726471_590820_13942_endTime = null;

            public IntegerParameter _17_0_5_edc0357_1345510114794_965367_15124 = null;

            public BooleanParameter _17_0_5_edc0357_1348163662246_240262_14722_exists = null;

            public ConstraintExpression constraint51 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1348163662246_240262_14722_existsDependency = null;

            public ElaborationRule elaborationRule52 = null;

            public void init_17_0_5_edc0357_1345510114087_224243_14250Members() {
                try {
                    _17_0_5_edc0357_1348000726471_590820_13942_endTime = new IntegerParameter("_17_0_5_edc0357_1348000726471_590820_13942_endTime", this);
                    _17_0_5_edc0357_1345510114794_965367_15124 = new IntegerParameter("_17_0_5_edc0357_1345510114794_965367_15124", this);
                    _17_0_5_edc0357_1348163662246_240262_14722_exists = new BooleanParameter("_17_0_5_edc0357_1348163662246_240262_14722_exists", this);
                    constraint51 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1348000726471_590820_13942_endTime)));
                    _17_0_5_edc0357_1348163662246_240262_14722_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1348163662246_240262_14722_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114087_224243_14250Collections() {
                parameters.add(_17_0_5_edc0357_1348000726471_590820_13942_endTime);
                parameters.add(_17_0_5_edc0357_1345510114794_965367_15124);
                parameters.add(_17_0_5_edc0357_1348163662246_240262_14722_exists);
                constraintExpressions.add(constraint51);
                dependencies.add(_17_0_5_edc0357_1348163662246_240262_14722_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510114087_224243_14250Elaborations() {
                Expression<?>[] arguments52 = new Expression<?>[2];
                arguments52[0] = new Expression<Integer>(endTime);
                arguments52[1] = new Expression<Integer>(_17_0_5_edc0357_1345510114794_965367_15124);
                Expression<Boolean> condition52 = new Expression<Boolean>(_17_0_5_edc0357_1348163662246_240262_14722_exists);
                elaborationRule52 = addElaborationRule(condition52, _17_0_5_edc0357_1345510113557_625793_13776.this, Customer._17_0_5_edc0357_1345510113557_625793_13776._17_0_5_edc0357_1348163662246_240262_14722.class, "_ForkNode_changePowerUsage", arguments52);
            }

            public _17_0_5_edc0357_1345510114087_224243_14250() {
                super();
                init_17_0_5_edc0357_1345510114087_224243_14250Members();
                init_17_0_5_edc0357_1345510114087_224243_14250Collections();
                init_17_0_5_edc0357_1345510114087_224243_14250Elaborations();
            }

            public _17_0_5_edc0357_1345510114087_224243_14250(Expression<Integer> _17_0_5_edc0357_1348000726471_590820_13942_endTime) {
                super();
                init_17_0_5_edc0357_1345510114087_224243_14250Members();
                init_17_0_5_edc0357_1345510114087_224243_14250Collections();
                addDependency(this._17_0_5_edc0357_1348000726471_590820_13942_endTime, _17_0_5_edc0357_1348000726471_590820_13942_endTime);
                init_17_0_5_edc0357_1345510114087_224243_14250Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114087_795044_14251 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1345510114795_154720_15125 = null;

            public Parameter< Customer > _17_0_5_edc0357_1345510114796_230370_15126 = null;

            public BooleanParameter _17_0_5_edc0357_1345510114088_571377_14257_exists = null;

            public IntegerParameter _17_0_5_edc0357_1345510114088_487345_14255_endTime = null;

            public ConstraintExpression constraint53 = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114795_154720_15125Dependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114088_571377_14257_existsDependency = null;

            public Effect effect54 = null;

            public Object effect54Var = null;

            public ElaborationRule elaborationRule55 = null;

            public void init_17_0_5_edc0357_1345510114087_795044_14251Members() {
                try {
                    _17_0_5_edc0357_1345510114795_154720_15125 = new IntegerParameter("_17_0_5_edc0357_1345510114795_154720_15125", this);
                    _17_0_5_edc0357_1345510114796_230370_15126 = new Parameter<Customer>("_17_0_5_edc0357_1345510114796_230370_15126", null, null, this);
                    _17_0_5_edc0357_1345510114088_571377_14257_exists = new BooleanParameter("_17_0_5_edc0357_1345510114088_571377_14257_exists", this);
                    _17_0_5_edc0357_1345510114088_487345_14255_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114088_487345_14255_endTime", this);
                    constraint53 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114088_487345_14255_endTime)));
                    _17_0_5_edc0357_1345510114795_154720_15125Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114795_154720_15125, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114089_103639_14268, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1345510114088_571377_14257_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114088_571377_14257_exists, new Functions.And(new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114089_58426_14269, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114089_297842_14265, Utils.getMethodForArgTypes("ObjectFlow<Customer>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) }))));
                    effect54Var = usage__17_0_5_edc0357_1345510113561_560076_13779;
                    effect54 = new EffectFunction(new FunctionCall((Object) effect54Var, Utils.getMethodForArgTypes("TimeVaryingMap<Integer>", "generated", "setValue", java.lang.Integer.class, java.lang.Object.class), new Object[] { startTime, _17_0_5_edc0357_1345510114795_154720_15125 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114087_795044_14251Collections() {
                parameters.add(_17_0_5_edc0357_1345510114795_154720_15125);
                parameters.add(_17_0_5_edc0357_1345510114796_230370_15126);
                parameters.add(_17_0_5_edc0357_1345510114088_571377_14257_exists);
                parameters.add(_17_0_5_edc0357_1345510114088_487345_14255_endTime);
                constraintExpressions.add(constraint53);
                dependencies.add(_17_0_5_edc0357_1345510114795_154720_15125Dependency);
                dependencies.add(_17_0_5_edc0357_1345510114088_571377_14257_existsDependency);
                Set<Effect> effectsForeffect54Var = new HashSet<Effect>();
                effectsForeffect54Var.add(effect54);
                effects.put((Parameter<?>) effect54Var, effectsForeffect54Var);
            }

            public void init_17_0_5_edc0357_1345510114087_795044_14251Elaborations() {
                Expression<?>[] arguments55 = new Expression<?>[1];
                arguments55[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition55 = new Expression<Boolean>(_17_0_5_edc0357_1345510114088_571377_14257_exists);
                elaborationRule55 = addElaborationRule(condition55, _17_0_5_edc0357_1345510113557_625793_13776.this, Customer._17_0_5_edc0357_1345510113557_625793_13776._17_0_5_edc0357_1345510114088_571377_14257.class, "_SendSignalAction_changePowerUsage", arguments55);
            }

            public _17_0_5_edc0357_1345510114087_795044_14251() {
                super();
                init_17_0_5_edc0357_1345510114087_795044_14251Members();
                init_17_0_5_edc0357_1345510114087_795044_14251Collections();
                init_17_0_5_edc0357_1345510114087_795044_14251Elaborations();
            }

            public _17_0_5_edc0357_1345510114087_795044_14251(Expression<Integer> _17_0_5_edc0357_1345510114088_487345_14255_endTime, Expression<Customer> _17_0_5_edc0357_1345510114796_230370_15126) {
                super();
                init_17_0_5_edc0357_1345510114087_795044_14251Members();
                init_17_0_5_edc0357_1345510114087_795044_14251Collections();
                addDependency(this._17_0_5_edc0357_1345510114088_487345_14255_endTime, _17_0_5_edc0357_1345510114088_487345_14255_endTime);
                addDependency(this._17_0_5_edc0357_1345510114796_230370_15126, _17_0_5_edc0357_1345510114796_230370_15126);
                init_17_0_5_edc0357_1345510114087_795044_14251Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114087_35215_14252 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1348000726471_590820_13942_exists = null;

            public Dependency< Boolean > _17_0_5_edc0357_1348000726471_590820_13942_existsDependency = null;

            public ElaborationRule elaborationRule56 = null;

            public void init_17_0_5_edc0357_1345510114087_35215_14252Members() {
                try {
                    _17_0_5_edc0357_1348000726471_590820_13942_exists = new BooleanParameter("_17_0_5_edc0357_1348000726471_590820_13942_exists", this);
                    _17_0_5_edc0357_1348000726471_590820_13942_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1348000726471_590820_13942_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114087_35215_14252Collections() {
                parameters.add(_17_0_5_edc0357_1348000726471_590820_13942_exists);
                dependencies.add(_17_0_5_edc0357_1348000726471_590820_13942_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510114087_35215_14252Elaborations() {
                Expression<?>[] arguments56 = new Expression<?>[1];
                arguments56[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition56 = new Expression<Boolean>(_17_0_5_edc0357_1348000726471_590820_13942_exists);
                elaborationRule56 = addElaborationRule(condition56, _17_0_5_edc0357_1345510113557_625793_13776.this, Customer._17_0_5_edc0357_1345510113557_625793_13776._17_0_5_edc0357_1348000726471_590820_13942.class, "_ForkNode_changePowerUsage", arguments56);
            }

            public _17_0_5_edc0357_1345510114087_35215_14252() {
                super();
                init_17_0_5_edc0357_1345510114087_35215_14252Members();
                init_17_0_5_edc0357_1345510114087_35215_14252Collections();
                init_17_0_5_edc0357_1345510114087_35215_14252Elaborations();
            }

            public _17_0_5_edc0357_1345510114087_35215_14252(Expression<Integer> startTime) {
                super();
                init_17_0_5_edc0357_1345510114087_35215_14252Members();
                init_17_0_5_edc0357_1345510114087_35215_14252Collections();
                removeDependency(this.startTime);
                addDependency(this.startTime, startTime);
                init_17_0_5_edc0357_1345510114087_35215_14252Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114087_791191_14253 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1345510114088_571377_14257_endTime = null;

            public ConstraintExpression constraint57 = null;

            public void init_17_0_5_edc0357_1345510114087_791191_14253Members() {
                try {
                    _17_0_5_edc0357_1345510114088_571377_14257_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114088_571377_14257_endTime", this);
                    constraint57 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114088_571377_14257_endTime)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114087_791191_14253Collections() {
                parameters.add(_17_0_5_edc0357_1345510114088_571377_14257_endTime);
                constraintExpressions.add(constraint57);
            }

            public void init_17_0_5_edc0357_1345510114087_791191_14253Elaborations() {
            }

            public _17_0_5_edc0357_1345510114087_791191_14253() {
                super();
                init_17_0_5_edc0357_1345510114087_791191_14253Members();
                init_17_0_5_edc0357_1345510114087_791191_14253Collections();
                init_17_0_5_edc0357_1345510114087_791191_14253Elaborations();
            }

            public _17_0_5_edc0357_1345510114087_791191_14253(Expression<Integer> _17_0_5_edc0357_1345510114088_571377_14257_endTime) {
                super();
                init_17_0_5_edc0357_1345510114087_791191_14253Members();
                init_17_0_5_edc0357_1345510114087_791191_14253Collections();
                addDependency(this._17_0_5_edc0357_1345510114088_571377_14257_endTime, _17_0_5_edc0357_1345510114088_571377_14257_endTime);
                init_17_0_5_edc0357_1345510114087_791191_14253Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114087_731407_14254 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1345510114796_365668_15127 = null;

            public Parameter< Customer > _17_0_5_edc0357_1345510114797_445623_15128 = null;

            public BooleanParameter _17_0_5_edc0357_1348163517879_726154_14671_exists = null;

            public BooleanParameter _17_0_5_edc0357_1348163580677_177144_14706_exists = null;

            public IntegerParameter _17_0_5_edc0357_1345510114088_487345_14255_endTime = null;

            public ConstraintExpression constraint58 = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114796_365668_15127Dependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1348163517879_726154_14671_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1348163580677_177144_14706_existsDependency = null;

            public ElaborationRule elaborationRule59 = null;

            public ElaborationRule elaborationRule60 = null;

            public void init_17_0_5_edc0357_1345510114087_731407_14254Members() {
                try {
                    _17_0_5_edc0357_1345510114796_365668_15127 = new IntegerParameter("_17_0_5_edc0357_1345510114796_365668_15127", this);
                    _17_0_5_edc0357_1345510114797_445623_15128 = new Parameter<Customer>("_17_0_5_edc0357_1345510114797_445623_15128", null, null, this);
                    _17_0_5_edc0357_1348163517879_726154_14671_exists = new BooleanParameter("_17_0_5_edc0357_1348163517879_726154_14671_exists", this);
                    _17_0_5_edc0357_1348163580677_177144_14706_exists = new BooleanParameter("_17_0_5_edc0357_1348163580677_177144_14706_exists", this);
                    _17_0_5_edc0357_1345510114088_487345_14255_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114088_487345_14255_endTime", this);
                    constraint58 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114088_487345_14255_endTime)));
                    FunctionCall nestedCall3 = new FunctionCall(null, TimeVaryingMap.class, "getValue", new Object[] { startTime } );
                    FunctionCall nestedCall2 = new FunctionCall(null, Parameter.class, "getValue", (Object[]) null, nestedCall3 );
                    FunctionCall call1 = new FunctionCall(_17_0_5_edc0357_1345510114797_445623_15128, Parameter.class, "getMember", new Object[] { "cap__17_0_5_edc0357_1345510113566_55150_13784" }, nestedCall2 );
                    _17_0_5_edc0357_1345510114796_365668_15127Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114796_365668_15127, new Expression( call1 ) );
                    //_17_0_5_edc0357_1345510114796_365668_15127Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114796_365668_15127, new Expression(new FunctionCall((Object) (((Parameter<?>) (_17_0_5_edc0357_1345510114797_445623_15128.getMember("cap__17_0_5_edc0357_1345510113566_55150_13784"))).getValue()), Utils.getMethodForArgTypes("Customer._17_0_5_edc0357_1345510113557_625793_13776._17_0_5_edc0357_1345510114087_731407_14254", "generated", "getValue"), new Object[] { new Expression<Integer>(startTime) })));
                    _17_0_5_edc0357_1348163517879_726154_14671_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1348163517879_726154_14671_exists, new Expression<Boolean>(true));
                    _17_0_5_edc0357_1348163580677_177144_14706_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1348163580677_177144_14706_exists, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1348163519133_975185_14677, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114087_731407_14254Collections() {
                parameters.add(_17_0_5_edc0357_1345510114796_365668_15127);
                parameters.add(_17_0_5_edc0357_1345510114797_445623_15128);
                parameters.add(_17_0_5_edc0357_1348163517879_726154_14671_exists);
                parameters.add(_17_0_5_edc0357_1348163580677_177144_14706_exists);
                parameters.add(_17_0_5_edc0357_1345510114088_487345_14255_endTime);
                constraintExpressions.add(constraint58);
                dependencies.add(_17_0_5_edc0357_1345510114796_365668_15127Dependency);
                dependencies.add(_17_0_5_edc0357_1348163517879_726154_14671_existsDependency);
                dependencies.add(_17_0_5_edc0357_1348163580677_177144_14706_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510114087_731407_14254Elaborations() {
                Expression<?>[] arguments59 = new Expression<?>[2];
                arguments59[0] = new Expression<Integer>(endTime);
                arguments59[1] = new Expression<Integer>(_17_0_5_edc0357_1345510114796_365668_15127);
                Expression<Boolean> condition59 = new Expression<Boolean>(_17_0_5_edc0357_1348163517879_726154_14671_exists);
                elaborationRule59 = addElaborationRule(condition59, _17_0_5_edc0357_1345510113557_625793_13776.this, Customer._17_0_5_edc0357_1345510113557_625793_13776._17_0_5_edc0357_1348163517879_726154_14671.class, "_ForkNode_changePowerUsage", arguments59);
                Expression<?>[] arguments60 = new Expression<?>[1];
                arguments60[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition60 = new Expression<Boolean>(_17_0_5_edc0357_1348163580677_177144_14706_exists);
                elaborationRule60 = addElaborationRule(condition60, _17_0_5_edc0357_1345510113557_625793_13776.this, Customer._17_0_5_edc0357_1345510113557_625793_13776._17_0_5_edc0357_1348163580677_177144_14706.class, "_DecisionNode_changePowerUsage", arguments60);
            }

            public _17_0_5_edc0357_1345510114087_731407_14254() {
                super();
                init_17_0_5_edc0357_1345510114087_731407_14254Members();
                init_17_0_5_edc0357_1345510114087_731407_14254Collections();
                init_17_0_5_edc0357_1345510114087_731407_14254Elaborations();
            }

            public _17_0_5_edc0357_1345510114087_731407_14254(Expression<Integer> _17_0_5_edc0357_1345510114088_487345_14255_endTime, Expression<Customer> _17_0_5_edc0357_1345510114797_445623_15128) {
                super();
                init_17_0_5_edc0357_1345510114087_731407_14254Members();
                init_17_0_5_edc0357_1345510114087_731407_14254Collections();
                addDependency(this._17_0_5_edc0357_1345510114088_487345_14255_endTime, _17_0_5_edc0357_1345510114088_487345_14255_endTime);
                addDependency(this._17_0_5_edc0357_1345510114797_445623_15128, _17_0_5_edc0357_1345510114797_445623_15128);
                init_17_0_5_edc0357_1345510114087_731407_14254Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114088_487345_14255 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1345510114087_795044_14251_exists = null;

            public Parameter< Customer > objectToPass = null;

            public BooleanParameter _17_0_5_edc0357_1345510114087_731407_14254_exists = null;

            public IntegerParameter _17_0_5_edc0357_1345510114087_484087_14249_endTime = null;

            public ConstraintExpression constraint61 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114087_795044_14251_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114087_731407_14254_existsDependency = null;

            public Effect effect62 = null;

            public Object effect62Var = null;

            public ElaborationRule elaborationRule63 = null;

            public ElaborationRule elaborationRule64 = null;

            public void init_17_0_5_edc0357_1345510114088_487345_14255Members() {
                try {
                    _17_0_5_edc0357_1345510114087_795044_14251_exists = new BooleanParameter("_17_0_5_edc0357_1345510114087_795044_14251_exists", this);
                    objectToPass = new Parameter<Customer>("objectToPass", null, null, this);
                    _17_0_5_edc0357_1345510114087_731407_14254_exists = new BooleanParameter("_17_0_5_edc0357_1345510114087_731407_14254_exists", this);
                    _17_0_5_edc0357_1345510114087_484087_14249_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114087_484087_14249_endTime", this);
                    constraint61 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114087_484087_14249_endTime)));
                    _17_0_5_edc0357_1345510114087_795044_14251_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114087_795044_14251_exists, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114089_103639_14268, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })));
                    _17_0_5_edc0357_1345510114087_731407_14254_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114087_731407_14254_exists, new Expression<Boolean>(true));
                    effect62Var = sig_17_0_5_edc0357_1345510114089_297842_14265;
                    effect62 = new EffectFunction(new FunctionCall((Object) effect62Var, Utils.getMethodForArgTypes("ObjectFlow<Customer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114088_487345_14255Collections() {
                parameters.add(_17_0_5_edc0357_1345510114087_795044_14251_exists);
                parameters.add(objectToPass);
                parameters.add(_17_0_5_edc0357_1345510114087_731407_14254_exists);
                parameters.add(_17_0_5_edc0357_1345510114087_484087_14249_endTime);
                constraintExpressions.add(constraint61);
                dependencies.add(_17_0_5_edc0357_1345510114087_795044_14251_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510114087_731407_14254_existsDependency);
                Set<Effect> effectsForeffect62Var = new HashSet<Effect>();
                effectsForeffect62Var.add(effect62);
                effects.put((Parameter<?>) effect62Var, effectsForeffect62Var);
            }

            public void init_17_0_5_edc0357_1345510114088_487345_14255Elaborations() {
                Expression<?>[] arguments63 = new Expression<?>[2];
                arguments63[0] = new Expression<Integer>(endTime);
                arguments63[1] = new Expression<Customer>(objectToPass);
                Expression<Boolean> condition63 = new Expression<Boolean>(_17_0_5_edc0357_1345510114087_795044_14251_exists);
                elaborationRule63 = addElaborationRule(condition63, _17_0_5_edc0357_1345510113557_625793_13776.this, Customer._17_0_5_edc0357_1345510113557_625793_13776._17_0_5_edc0357_1345510114087_795044_14251.class, "_AddStructuralFeatureValueAction_changePowerUsage", arguments63);
                Expression<?>[] arguments64 = new Expression<?>[2];
                arguments64[0] = new Expression<Integer>(endTime);
                arguments64[1] = new Expression<Customer>(objectToPass);
                Expression<Boolean> condition64 = new Expression<Boolean>(_17_0_5_edc0357_1345510114087_731407_14254_exists);
                elaborationRule64 = addElaborationRule(condition64, _17_0_5_edc0357_1345510113557_625793_13776.this, Customer._17_0_5_edc0357_1345510113557_625793_13776._17_0_5_edc0357_1345510114087_731407_14254.class, "_ReadStructuralFeatureAction_changePowerUsage", arguments64);
            }

            public _17_0_5_edc0357_1345510114088_487345_14255() {
                super();
                init_17_0_5_edc0357_1345510114088_487345_14255Members();
                init_17_0_5_edc0357_1345510114088_487345_14255Collections();
                init_17_0_5_edc0357_1345510114088_487345_14255Elaborations();
            }

            public _17_0_5_edc0357_1345510114088_487345_14255(Expression<Integer> _17_0_5_edc0357_1345510114087_484087_14249_endTime, Expression<Customer> objectToPass) {
                super();
                init_17_0_5_edc0357_1345510114088_487345_14255Members();
                init_17_0_5_edc0357_1345510114088_487345_14255Collections();
                addDependency(this._17_0_5_edc0357_1345510114087_484087_14249_endTime, _17_0_5_edc0357_1345510114087_484087_14249_endTime);
                addDependency(this.objectToPass, objectToPass);
                init_17_0_5_edc0357_1345510114088_487345_14255Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114088_357828_14256 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1345510114792_358855_15120 = null;

            public IntegerParameter new_load = null;

            public BooleanParameter _17_0_5_edc0357_1348165551875_6141_14745_exists = null;

            public IntegerParameter _17_0_5_edc0357_1345510114791_46408_15119 = null;

            public IntegerParameter _17_0_5_edc0357_1348163580677_177144_14706_endTime = null;

            public IntegerParameter cap = null;

            public IntegerParameter desired = null;

            public IntegerParameter _17_0_5_edc0357_1345510114798_618420_15129 = null;

            public ConstraintExpression constraint65 = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114792_358855_15120Dependency = null;

            public Dependency< Integer > new_loadDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1348165551875_6141_14745_existsDependency = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114791_46408_15119Dependency = null;

            public Dependency< Integer > capDependency = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114798_618420_15129Dependency = null;

            public Dependency< Integer > desiredDependency = null;

            public ElaborationRule elaborationRule66 = null;

            public void init_17_0_5_edc0357_1345510114088_357828_14256Members() {
                try {
                    _17_0_5_edc0357_1345510114792_358855_15120 = new IntegerParameter("_17_0_5_edc0357_1345510114792_358855_15120", this);
                    new_load = new IntegerParameter("new_load", this);
                    _17_0_5_edc0357_1348165551875_6141_14745_exists = new BooleanParameter("_17_0_5_edc0357_1348165551875_6141_14745_exists", this);
                    _17_0_5_edc0357_1345510114791_46408_15119 = new IntegerParameter("_17_0_5_edc0357_1345510114791_46408_15119", this);
                    _17_0_5_edc0357_1348163580677_177144_14706_endTime = new IntegerParameter("_17_0_5_edc0357_1348163580677_177144_14706_endTime", this);
                    cap = new IntegerParameter("cap", this);
                    desired = new IntegerParameter("desired", this);
                    _17_0_5_edc0357_1345510114798_618420_15129 = new IntegerParameter("_17_0_5_edc0357_1345510114798_618420_15129", this);
                    constraint65 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1348163580677_177144_14706_endTime)));
                    _17_0_5_edc0357_1345510114792_358855_15120Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114792_358855_15120, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1348163531723_681524_14685, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    new_loadDependency = new Dependency<Integer>(new_load, new Expression(new FunctionCall((Object) this, Utils.getMethodForArgTypes("Customer._17_0_5_edc0357_1345510113557_625793_13776._17_0_5_edc0357_1345510114088_357828_14256", "generated", "min"), new Object[] { new Expression<Integer>(desired), new Expression<Integer>(cap) })));
                    _17_0_5_edc0357_1348165551875_6141_14745_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1348165551875_6141_14745_exists, new Expression<Boolean>(true));
                    _17_0_5_edc0357_1345510114791_46408_15119Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114791_46408_15119, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1348163663482_249339_14727, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    capDependency = new Dependency<Integer>(cap, new Expression<Integer>(_17_0_5_edc0357_1345510114792_358855_15120));
                    _17_0_5_edc0357_1345510114798_618420_15129Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114798_618420_15129, new Expression<Integer>(new_load));
                    desiredDependency = new Dependency<Integer>(desired, new Expression<Integer>(_17_0_5_edc0357_1345510114791_46408_15119));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114088_357828_14256Collections() {
                parameters.add(_17_0_5_edc0357_1345510114792_358855_15120);
                parameters.add(new_load);
                parameters.add(_17_0_5_edc0357_1348165551875_6141_14745_exists);
                parameters.add(_17_0_5_edc0357_1345510114791_46408_15119);
                parameters.add(_17_0_5_edc0357_1348163580677_177144_14706_endTime);
                parameters.add(cap);
                parameters.add(desired);
                parameters.add(_17_0_5_edc0357_1345510114798_618420_15129);
                constraintExpressions.add(constraint65);
                dependencies.add(_17_0_5_edc0357_1345510114792_358855_15120Dependency);
                dependencies.add(new_loadDependency);
                dependencies.add(_17_0_5_edc0357_1348165551875_6141_14745_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510114791_46408_15119Dependency);
                dependencies.add(capDependency);
                dependencies.add(_17_0_5_edc0357_1345510114798_618420_15129Dependency);
                dependencies.add(desiredDependency);
            }

            public void init_17_0_5_edc0357_1345510114088_357828_14256Elaborations() {
                Expression<?>[] arguments66 = new Expression<?>[2];
                arguments66[0] = new Expression<Integer>(endTime);
                arguments66[1] = new Expression<Integer>(_17_0_5_edc0357_1345510114798_618420_15129);
                Expression<Boolean> condition66 = new Expression<Boolean>(_17_0_5_edc0357_1348165551875_6141_14745_exists);
                elaborationRule66 = addElaborationRule(condition66, _17_0_5_edc0357_1345510113557_625793_13776.this, Customer._17_0_5_edc0357_1345510113557_625793_13776._17_0_5_edc0357_1348165551875_6141_14745.class, "mergeynode_MergeNode_changePowerUsage", arguments66);
            }

            public _17_0_5_edc0357_1345510114088_357828_14256() {
                super();
                init_17_0_5_edc0357_1345510114088_357828_14256Members();
                init_17_0_5_edc0357_1345510114088_357828_14256Collections();
                init_17_0_5_edc0357_1345510114088_357828_14256Elaborations();
            }

            public _17_0_5_edc0357_1345510114088_357828_14256(Expression<Integer> _17_0_5_edc0357_1348163580677_177144_14706_endTime) {
                super();
                init_17_0_5_edc0357_1345510114088_357828_14256Members();
                init_17_0_5_edc0357_1345510114088_357828_14256Collections();
                addDependency(this._17_0_5_edc0357_1348163580677_177144_14706_endTime, _17_0_5_edc0357_1348163580677_177144_14706_endTime);
                init_17_0_5_edc0357_1345510114088_357828_14256Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114088_571377_14257 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1345510114801_45672_15133 = null;

            public IntegerParameter _17_0_5_edc0357_1345510114087_795044_14251_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1345510114087_791191_14253_exists = null;

            public Parameter< Customer > _17_0_5_edc0357_1345510114800_96164_15132 = null;

            public ConstraintExpression constraint67 = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114801_45672_15133Dependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114087_791191_14253_existsDependency = null;

            public Dependency< Customer > _17_0_5_edc0357_1345510114800_96164_15132Dependency = null;

            public Effect effect68 = null;

            public Object effect68Var = null;

            public ElaborationRule elaborationRule69 = null;

            public void init_17_0_5_edc0357_1345510114088_571377_14257Members() {
                try {
                    _17_0_5_edc0357_1345510114801_45672_15133 = new IntegerParameter("_17_0_5_edc0357_1345510114801_45672_15133", this);
                    _17_0_5_edc0357_1345510114087_795044_14251_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114087_795044_14251_endTime", this);
                    _17_0_5_edc0357_1345510114087_791191_14253_exists = new BooleanParameter("_17_0_5_edc0357_1345510114087_791191_14253_exists", this);
                    _17_0_5_edc0357_1345510114800_96164_15132 = new Parameter<Customer>("_17_0_5_edc0357_1345510114800_96164_15132", null, null, this);
                    constraint67 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114087_795044_14251_endTime)));
                    _17_0_5_edc0357_1345510114801_45672_15133Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114801_45672_15133, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114089_58426_14269, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1345510114087_791191_14253_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114087_791191_14253_exists, new Expression<Boolean>(true));
                    _17_0_5_edc0357_1345510114800_96164_15132Dependency = new Dependency<Customer>(_17_0_5_edc0357_1345510114800_96164_15132, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114089_297842_14265, Utils.getMethodForArgTypes("ObjectFlow<Customer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    effect68Var = (((Parameter<?>) (x.getMember("ss_17_0_5_edc0357_1345510113562_98635_13780_changeLoadValue"))).getValue());
                    effect68 = new EffectFunction(new FunctionCall((Object) effect68Var, Utils.getMethodForArgTypes("Customer._17_0_5_edc0357_1345510113557_625793_13776._17_0_5_edc0357_1345510114088_571377_14257", "generated", "send"), new Object[] { x.getValue().new SignalchangeLoadValue(_17_0_5_edc0357_1345510114801_45672_15133.getValue()), endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114088_571377_14257Collections() {
                parameters.add(_17_0_5_edc0357_1345510114801_45672_15133);
                parameters.add(_17_0_5_edc0357_1345510114087_795044_14251_endTime);
                parameters.add(_17_0_5_edc0357_1345510114087_791191_14253_exists);
                parameters.add(_17_0_5_edc0357_1345510114800_96164_15132);
                constraintExpressions.add(constraint67);
                dependencies.add(_17_0_5_edc0357_1345510114801_45672_15133Dependency);
                dependencies.add(_17_0_5_edc0357_1345510114087_791191_14253_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510114800_96164_15132Dependency);
                Set<Effect> effectsForeffect68Var = new HashSet<Effect>();
                effectsForeffect68Var.add(effect68);
                effects.put((Parameter<?>) effect68Var, effectsForeffect68Var);
            }

            public void init_17_0_5_edc0357_1345510114088_571377_14257Elaborations() {
                Expression<?>[] arguments69 = new Expression<?>[1];
                arguments69[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition69 = new Expression<Boolean>(_17_0_5_edc0357_1345510114087_791191_14253_exists);
                elaborationRule69 = addElaborationRule(condition69, _17_0_5_edc0357_1345510113557_625793_13776.this, Customer._17_0_5_edc0357_1345510113557_625793_13776._17_0_5_edc0357_1345510114087_791191_14253.class, "_ActivityFinalNode_changePowerUsage", arguments69);
            }

            public _17_0_5_edc0357_1345510114088_571377_14257() {
                super();
                init_17_0_5_edc0357_1345510114088_571377_14257Members();
                init_17_0_5_edc0357_1345510114088_571377_14257Collections();
                init_17_0_5_edc0357_1345510114088_571377_14257Elaborations();
            }

            public _17_0_5_edc0357_1345510114088_571377_14257(Expression<Integer> _17_0_5_edc0357_1345510114087_795044_14251_endTime) {
                super();
                init_17_0_5_edc0357_1345510114088_571377_14257Members();
                init_17_0_5_edc0357_1345510114088_571377_14257Collections();
                addDependency(this._17_0_5_edc0357_1345510114087_795044_14251_endTime, _17_0_5_edc0357_1345510114087_795044_14251_endTime);
                init_17_0_5_edc0357_1345510114088_571377_14257Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114088_999820_14258 extends DurativeEvent {

            public IntegerParameter objectToPass = null;

            public IntegerParameter _17_0_5_edc0357_1348165551875_6141_14745_endTime = null;

            public ConstraintExpression constraint70 = null;

            public Effect effect71 = null;

            public Object effect71Var = null;

            public Effect effect72 = null;

            public Object effect72Var = null;

            public void init_17_0_5_edc0357_1345510114088_999820_14258Members() {
                try {
                    objectToPass = new IntegerParameter("objectToPass", this);
                    _17_0_5_edc0357_1348165551875_6141_14745_endTime = new IntegerParameter("_17_0_5_edc0357_1348165551875_6141_14745_endTime", this);
                    constraint70 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1348165551875_6141_14745_endTime)));
                    effect71Var = sig_17_0_5_edc0357_1345510114089_103639_14268;
                    effect71 = new EffectFunction(new FunctionCall((Object) effect71Var, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                    effect72Var = sig_17_0_5_edc0357_1345510114089_58426_14269;
                    effect72 = new EffectFunction(new FunctionCall((Object) effect72Var, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114088_999820_14258Collections() {
                parameters.add(objectToPass);
                parameters.add(_17_0_5_edc0357_1348165551875_6141_14745_endTime);
                constraintExpressions.add(constraint70);
                Set<Effect> effectsForeffect71Var = new HashSet<Effect>();
                effectsForeffect71Var.add(effect71);
                effects.put((Parameter<?>) effect71Var, effectsForeffect71Var);
                Set<Effect> effectsForeffect72Var = new HashSet<Effect>();
                effectsForeffect72Var.add(effect72);
                effects.put((Parameter<?>) effect72Var, effectsForeffect72Var);
            }

            public void init_17_0_5_edc0357_1345510114088_999820_14258Elaborations() {
            }

            public _17_0_5_edc0357_1345510114088_999820_14258() {
                super();
                init_17_0_5_edc0357_1345510114088_999820_14258Members();
                init_17_0_5_edc0357_1345510114088_999820_14258Collections();
                init_17_0_5_edc0357_1345510114088_999820_14258Elaborations();
            }

            public _17_0_5_edc0357_1345510114088_999820_14258(Expression<Integer> _17_0_5_edc0357_1348165551875_6141_14745_endTime, Expression<Integer> objectToPass) {
                super();
                init_17_0_5_edc0357_1345510114088_999820_14258Members();
                init_17_0_5_edc0357_1345510114088_999820_14258Collections();
                addDependency(this._17_0_5_edc0357_1348165551875_6141_14745_endTime, _17_0_5_edc0357_1348165551875_6141_14745_endTime);
                addDependency(this.objectToPass, objectToPass);
                init_17_0_5_edc0357_1345510114088_999820_14258Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1348000726471_590820_13942 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1345510114087_35215_14252_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1345510114087_224243_14250_exists = null;

            public BooleanParameter _17_0_5_edc0357_1345510114087_484087_14249_exists = null;

            public ConstraintExpression constraint73 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114087_224243_14250_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114087_484087_14249_existsDependency = null;

            public ElaborationRule elaborationRule74 = null;

            public ElaborationRule elaborationRule75 = null;

            public void init_17_0_5_edc0357_1348000726471_590820_13942Members() {
                try {
                    _17_0_5_edc0357_1345510114087_35215_14252_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114087_35215_14252_endTime", this);
                    _17_0_5_edc0357_1345510114087_224243_14250_exists = new BooleanParameter("_17_0_5_edc0357_1345510114087_224243_14250_exists", this);
                    _17_0_5_edc0357_1345510114087_484087_14249_exists = new BooleanParameter("_17_0_5_edc0357_1345510114087_484087_14249_exists", this);
                    constraint73 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114087_35215_14252_endTime)));
                    _17_0_5_edc0357_1345510114087_224243_14250_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114087_224243_14250_exists, new Expression<Boolean>(true));
                    _17_0_5_edc0357_1345510114087_484087_14249_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114087_484087_14249_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1348000726471_590820_13942Collections() {
                parameters.add(_17_0_5_edc0357_1345510114087_35215_14252_endTime);
                parameters.add(_17_0_5_edc0357_1345510114087_224243_14250_exists);
                parameters.add(_17_0_5_edc0357_1345510114087_484087_14249_exists);
                constraintExpressions.add(constraint73);
                dependencies.add(_17_0_5_edc0357_1345510114087_224243_14250_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510114087_484087_14249_existsDependency);
            }

            public void init_17_0_5_edc0357_1348000726471_590820_13942Elaborations() {
                Expression<?>[] arguments74 = new Expression<?>[1];
                arguments74[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition74 = new Expression<Boolean>(_17_0_5_edc0357_1345510114087_224243_14250_exists);
                elaborationRule74 = addElaborationRule(condition74, _17_0_5_edc0357_1345510113557_625793_13776.this, Customer._17_0_5_edc0357_1345510113557_625793_13776._17_0_5_edc0357_1345510114087_224243_14250.class, "_ValueSpecificationAction_changePowerUsage", arguments74);
                Expression<?>[] arguments75 = new Expression<?>[1];
                arguments75[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition75 = new Expression<Boolean>(_17_0_5_edc0357_1345510114087_484087_14249_exists);
                elaborationRule75 = addElaborationRule(condition75, _17_0_5_edc0357_1345510113557_625793_13776.this, Customer._17_0_5_edc0357_1345510113557_625793_13776._17_0_5_edc0357_1345510114087_484087_14249.class, "_ReadSelfAction_changePowerUsage", arguments75);
            }

            public _17_0_5_edc0357_1348000726471_590820_13942() {
                super();
                init_17_0_5_edc0357_1348000726471_590820_13942Members();
                init_17_0_5_edc0357_1348000726471_590820_13942Collections();
                init_17_0_5_edc0357_1348000726471_590820_13942Elaborations();
            }

            public _17_0_5_edc0357_1348000726471_590820_13942(Expression<Integer> _17_0_5_edc0357_1345510114087_35215_14252_endTime) {
                super();
                init_17_0_5_edc0357_1348000726471_590820_13942Members();
                init_17_0_5_edc0357_1348000726471_590820_13942Collections();
                addDependency(this._17_0_5_edc0357_1345510114087_35215_14252_endTime, _17_0_5_edc0357_1345510114087_35215_14252_endTime);
                init_17_0_5_edc0357_1348000726471_590820_13942Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1348163392370_265702_14643 extends DurativeEvent {

            public IntegerParameter new_load = null;

            public IntegerParameter _17_0_5_edc0357_1348163632207_233130_14717 = null;

            public IntegerParameter _17_0_5_edc0357_1348163392389_228149_14655 = null;

            public BooleanParameter _17_0_5_edc0357_1348165551875_6141_14745_exists = null;

            public IntegerParameter _17_0_5_edc0357_1348163580677_177144_14706_endTime = null;

            public IntegerParameter desired = null;

            public ConstraintExpression constraint76 = null;

            public Dependency< Integer > new_loadDependency = null;

            public Dependency< Integer > _17_0_5_edc0357_1348163392389_228149_14655Dependency = null;

            public Dependency< Integer > _17_0_5_edc0357_1348163632207_233130_14717Dependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1348165551875_6141_14745_existsDependency = null;

            public Dependency< Integer > desiredDependency = null;

            public ElaborationRule elaborationRule77 = null;

            public void init_17_0_5_edc0357_1348163392370_265702_14643Members() {
                try {
                    new_load = new IntegerParameter("new_load", this);
                    _17_0_5_edc0357_1348163632207_233130_14717 = new IntegerParameter("_17_0_5_edc0357_1348163632207_233130_14717", this);
                    _17_0_5_edc0357_1348163392389_228149_14655 = new IntegerParameter("_17_0_5_edc0357_1348163392389_228149_14655", this);
                    _17_0_5_edc0357_1348165551875_6141_14745_exists = new BooleanParameter("_17_0_5_edc0357_1348165551875_6141_14745_exists", this);
                    _17_0_5_edc0357_1348163580677_177144_14706_endTime = new IntegerParameter("_17_0_5_edc0357_1348163580677_177144_14706_endTime", this);
                    desired = new IntegerParameter("desired", this);
                    constraint76 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1348163580677_177144_14706_endTime)));
                    new_loadDependency = new Dependency<Integer>(new_load, new Expression<Integer>(desired));
                    _17_0_5_edc0357_1348163392389_228149_14655Dependency = new Dependency<Integer>(_17_0_5_edc0357_1348163392389_228149_14655, new Expression<Integer>(new_load));
                    _17_0_5_edc0357_1348163632207_233130_14717Dependency = new Dependency<Integer>(_17_0_5_edc0357_1348163632207_233130_14717, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1348163666246_377742_14732, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1348165551875_6141_14745_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1348165551875_6141_14745_exists, new Expression<Boolean>(true));
                    desiredDependency = new Dependency<Integer>(desired, new Expression<Integer>(_17_0_5_edc0357_1348163632207_233130_14717));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1348163392370_265702_14643Collections() {
                parameters.add(new_load);
                parameters.add(_17_0_5_edc0357_1348163632207_233130_14717);
                parameters.add(_17_0_5_edc0357_1348163392389_228149_14655);
                parameters.add(_17_0_5_edc0357_1348165551875_6141_14745_exists);
                parameters.add(_17_0_5_edc0357_1348163580677_177144_14706_endTime);
                parameters.add(desired);
                constraintExpressions.add(constraint76);
                dependencies.add(new_loadDependency);
                dependencies.add(_17_0_5_edc0357_1348163392389_228149_14655Dependency);
                dependencies.add(_17_0_5_edc0357_1348163632207_233130_14717Dependency);
                dependencies.add(_17_0_5_edc0357_1348165551875_6141_14745_existsDependency);
                dependencies.add(desiredDependency);
            }

            public void init_17_0_5_edc0357_1348163392370_265702_14643Elaborations() {
                Expression<?>[] arguments77 = new Expression<?>[2];
                arguments77[0] = new Expression<Integer>(endTime);
                arguments77[1] = new Expression<Integer>(_17_0_5_edc0357_1348163392389_228149_14655);
                Expression<Boolean> condition77 = new Expression<Boolean>(_17_0_5_edc0357_1348165551875_6141_14745_exists);
                elaborationRule77 = addElaborationRule(condition77, _17_0_5_edc0357_1345510113557_625793_13776.this, Customer._17_0_5_edc0357_1345510113557_625793_13776._17_0_5_edc0357_1348165551875_6141_14745.class, "mergeynode_MergeNode_changePowerUsage", arguments77);
            }

            public _17_0_5_edc0357_1348163392370_265702_14643() {
                super();
                init_17_0_5_edc0357_1348163392370_265702_14643Members();
                init_17_0_5_edc0357_1348163392370_265702_14643Collections();
                init_17_0_5_edc0357_1348163392370_265702_14643Elaborations();
            }

            public _17_0_5_edc0357_1348163392370_265702_14643(Expression<Integer> _17_0_5_edc0357_1348163580677_177144_14706_endTime) {
                super();
                init_17_0_5_edc0357_1348163392370_265702_14643Members();
                init_17_0_5_edc0357_1348163392370_265702_14643Collections();
                addDependency(this._17_0_5_edc0357_1348163580677_177144_14706_endTime, _17_0_5_edc0357_1348163580677_177144_14706_endTime);
                init_17_0_5_edc0357_1348163392370_265702_14643Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1348163517879_726154_14671 extends DurativeEvent {

            public IntegerParameter objectToPass = null;

            public IntegerParameter _17_0_5_edc0357_1345510114087_731407_14254_endTime = null;

            public ConstraintExpression constraint78 = null;

            public Effect effect79 = null;

            public Object effect79Var = null;

            public Effect effect80 = null;

            public Object effect80Var = null;

            public void init_17_0_5_edc0357_1348163517879_726154_14671Members() {
                try {
                    objectToPass = new IntegerParameter("objectToPass", this);
                    _17_0_5_edc0357_1345510114087_731407_14254_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114087_731407_14254_endTime", this);
                    constraint78 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114087_731407_14254_endTime)));
                    effect79Var = sig_17_0_5_edc0357_1348163519133_975185_14677;
                    effect79 = new EffectFunction(new FunctionCall((Object) effect79Var, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                    effect80Var = sig_17_0_5_edc0357_1348163531723_681524_14685;
                    effect80 = new EffectFunction(new FunctionCall((Object) effect80Var, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1348163517879_726154_14671Collections() {
                parameters.add(objectToPass);
                parameters.add(_17_0_5_edc0357_1345510114087_731407_14254_endTime);
                constraintExpressions.add(constraint78);
                Set<Effect> effectsForeffect79Var = new HashSet<Effect>();
                effectsForeffect79Var.add(effect79);
                effects.put((Parameter<?>) effect79Var, effectsForeffect79Var);
                Set<Effect> effectsForeffect80Var = new HashSet<Effect>();
                effectsForeffect80Var.add(effect80);
                effects.put((Parameter<?>) effect80Var, effectsForeffect80Var);
            }

            public void init_17_0_5_edc0357_1348163517879_726154_14671Elaborations() {
            }

            public _17_0_5_edc0357_1348163517879_726154_14671() {
                super();
                init_17_0_5_edc0357_1348163517879_726154_14671Members();
                init_17_0_5_edc0357_1348163517879_726154_14671Collections();
                init_17_0_5_edc0357_1348163517879_726154_14671Elaborations();
            }

            public _17_0_5_edc0357_1348163517879_726154_14671(Expression<Integer> _17_0_5_edc0357_1345510114087_731407_14254_endTime, Expression<Integer> objectToPass) {
                super();
                init_17_0_5_edc0357_1348163517879_726154_14671Members();
                init_17_0_5_edc0357_1348163517879_726154_14671Collections();
                addDependency(this._17_0_5_edc0357_1345510114087_731407_14254_endTime, _17_0_5_edc0357_1345510114087_731407_14254_endTime);
                addDependency(this.objectToPass, objectToPass);
                init_17_0_5_edc0357_1348163517879_726154_14671Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1348163580677_177144_14706 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1348163519133_975185_14677 = null;

            public BooleanParameter _17_0_5_edc0357_1348163392370_265702_14643_exists = null;

            public BooleanParameter _17_0_5_edc0357_1345510114088_357828_14256_exists = null;

            public IntegerParameter _17_0_5_edc0357_1345510114087_731407_14254_endTime = null;

            public ConstraintExpression constraint81 = null;

            public Dependency< Integer > _17_0_5_edc0357_1348163519133_975185_14677Dependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1348163392370_265702_14643_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114088_357828_14256_existsDependency = null;

            public ElaborationRule elaborationRule82 = null;

            public ElaborationRule elaborationRule83 = null;

            public void init_17_0_5_edc0357_1348163580677_177144_14706Members() {
                try {
                    _17_0_5_edc0357_1348163519133_975185_14677 = new IntegerParameter("_17_0_5_edc0357_1348163519133_975185_14677", this);
                    _17_0_5_edc0357_1348163392370_265702_14643_exists = new BooleanParameter("_17_0_5_edc0357_1348163392370_265702_14643_exists", this);
                    _17_0_5_edc0357_1345510114088_357828_14256_exists = new BooleanParameter("_17_0_5_edc0357_1345510114088_357828_14256_exists", this);
                    _17_0_5_edc0357_1345510114087_731407_14254_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114087_731407_14254_endTime", this);
                    constraint81 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114087_731407_14254_endTime)));
                    _17_0_5_edc0357_1348163519133_975185_14677Dependency = new Dependency<Integer>(_17_0_5_edc0357_1348163519133_975185_14677, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1348163519133_975185_14677, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1348163392370_265702_14643_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1348163392370_265702_14643_exists, new Functions.And(new Functions.LessEquals(new Expression<Integer>(_17_0_5_edc0357_1348163519133_975185_14677), new Expression<Integer>(0)), new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1348163666246_377742_14732, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) }))));
                    _17_0_5_edc0357_1345510114088_357828_14256_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114088_357828_14256_exists, new Functions.And(new Functions.And(new Functions.Greater(new Expression<Integer>(_17_0_5_edc0357_1348163519133_975185_14677), new Expression<Integer>(0)), new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1348163663482_249339_14727, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) }))), new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1348163531723_681524_14685, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) }))));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1348163580677_177144_14706Collections() {
                parameters.add(_17_0_5_edc0357_1348163519133_975185_14677);
                parameters.add(_17_0_5_edc0357_1348163392370_265702_14643_exists);
                parameters.add(_17_0_5_edc0357_1345510114088_357828_14256_exists);
                parameters.add(_17_0_5_edc0357_1345510114087_731407_14254_endTime);
                constraintExpressions.add(constraint81);
                dependencies.add(_17_0_5_edc0357_1348163519133_975185_14677Dependency);
                dependencies.add(_17_0_5_edc0357_1348163392370_265702_14643_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510114088_357828_14256_existsDependency);
            }

            public void init_17_0_5_edc0357_1348163580677_177144_14706Elaborations() {
                Expression<?>[] arguments82 = new Expression<?>[1];
                arguments82[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition82 = new Expression<Boolean>(_17_0_5_edc0357_1345510114088_357828_14256_exists);
                elaborationRule82 = addElaborationRule(condition82, _17_0_5_edc0357_1345510113557_625793_13776.this, Customer._17_0_5_edc0357_1345510113557_625793_13776._17_0_5_edc0357_1345510114088_357828_14256.class, "min_CallBehaviorAction_changePowerUsage", arguments82);
                Expression<?>[] arguments83 = new Expression<?>[1];
                arguments83[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition83 = new Expression<Boolean>(_17_0_5_edc0357_1348163392370_265702_14643_exists);
                elaborationRule83 = addElaborationRule(condition83, _17_0_5_edc0357_1345510113557_625793_13776.this, Customer._17_0_5_edc0357_1345510113557_625793_13776._17_0_5_edc0357_1348163392370_265702_14643.class, "setd_CallBehaviorAction_changePowerUsage", arguments83);
            }

            public _17_0_5_edc0357_1348163580677_177144_14706() {
                super();
                init_17_0_5_edc0357_1348163580677_177144_14706Members();
                init_17_0_5_edc0357_1348163580677_177144_14706Collections();
                init_17_0_5_edc0357_1348163580677_177144_14706Elaborations();
            }

            public _17_0_5_edc0357_1348163580677_177144_14706(Expression<Integer> _17_0_5_edc0357_1345510114087_731407_14254_endTime) {
                super();
                init_17_0_5_edc0357_1348163580677_177144_14706Members();
                init_17_0_5_edc0357_1348163580677_177144_14706Collections();
                addDependency(this._17_0_5_edc0357_1345510114087_731407_14254_endTime, _17_0_5_edc0357_1345510114087_731407_14254_endTime);
                init_17_0_5_edc0357_1348163580677_177144_14706Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1348163662246_240262_14722 extends DurativeEvent {

            public IntegerParameter objectToPass = null;

            public IntegerParameter _17_0_5_edc0357_1345510114087_224243_14250_endTime = null;

            public ConstraintExpression constraint84 = null;

            public Effect effect85 = null;

            public Object effect85Var = null;

            public Effect effect86 = null;

            public Object effect86Var = null;

            public void init_17_0_5_edc0357_1348163662246_240262_14722Members() {
                try {
                    objectToPass = new IntegerParameter("objectToPass", this);
                    _17_0_5_edc0357_1345510114087_224243_14250_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114087_224243_14250_endTime", this);
                    constraint84 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114087_224243_14250_endTime)));
                    effect85Var = sig_17_0_5_edc0357_1348163663482_249339_14727;
                    effect85 = new EffectFunction(new FunctionCall((Object) effect85Var, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                    effect86Var = sig_17_0_5_edc0357_1348163666246_377742_14732;
                    effect86 = new EffectFunction(new FunctionCall((Object) effect86Var, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1348163662246_240262_14722Collections() {
                parameters.add(objectToPass);
                parameters.add(_17_0_5_edc0357_1345510114087_224243_14250_endTime);
                constraintExpressions.add(constraint84);
                Set<Effect> effectsForeffect85Var = new HashSet<Effect>();
                effectsForeffect85Var.add(effect85);
                effects.put((Parameter<?>) effect85Var, effectsForeffect85Var);
                Set<Effect> effectsForeffect86Var = new HashSet<Effect>();
                effectsForeffect86Var.add(effect86);
                effects.put((Parameter<?>) effect86Var, effectsForeffect86Var);
            }

            public void init_17_0_5_edc0357_1348163662246_240262_14722Elaborations() {
            }

            public _17_0_5_edc0357_1348163662246_240262_14722() {
                super();
                init_17_0_5_edc0357_1348163662246_240262_14722Members();
                init_17_0_5_edc0357_1348163662246_240262_14722Collections();
                init_17_0_5_edc0357_1348163662246_240262_14722Elaborations();
            }

            public _17_0_5_edc0357_1348163662246_240262_14722(Expression<Integer> _17_0_5_edc0357_1345510114087_224243_14250_endTime, Expression<Integer> objectToPass) {
                super();
                init_17_0_5_edc0357_1348163662246_240262_14722Members();
                init_17_0_5_edc0357_1348163662246_240262_14722Collections();
                addDependency(this._17_0_5_edc0357_1345510114087_224243_14250_endTime, _17_0_5_edc0357_1345510114087_224243_14250_endTime);
                addDependency(this.objectToPass, objectToPass);
                init_17_0_5_edc0357_1348163662246_240262_14722Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1348165551875_6141_14745 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1345510114088_999820_14258_exists = null;

            public IntegerParameter invoker_endTime = null;

            public IntegerParameter objectToPass = null;

            public ConstraintExpression constraint87 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114088_999820_14258_existsDependency = null;

            public ElaborationRule elaborationRule88 = null;

            public void init_17_0_5_edc0357_1348165551875_6141_14745Members() {
                try {
                    _17_0_5_edc0357_1345510114088_999820_14258_exists = new BooleanParameter("_17_0_5_edc0357_1345510114088_999820_14258_exists", this);
                    invoker_endTime = new IntegerParameter("invoker_endTime", this);
                    objectToPass = new IntegerParameter("objectToPass", this);
                    constraint87 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(invoker_endTime)));
                    _17_0_5_edc0357_1345510114088_999820_14258_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114088_999820_14258_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1348165551875_6141_14745Collections() {
                parameters.add(_17_0_5_edc0357_1345510114088_999820_14258_exists);
                parameters.add(invoker_endTime);
                parameters.add(objectToPass);
                constraintExpressions.add(constraint87);
                dependencies.add(_17_0_5_edc0357_1345510114088_999820_14258_existsDependency);
            }

            public void init_17_0_5_edc0357_1348165551875_6141_14745Elaborations() {
                Expression<?>[] arguments88 = new Expression<?>[2];
                arguments88[0] = new Expression<Integer>(endTime);
                arguments88[1] = new Expression<Integer>(objectToPass);
                Expression<Boolean> condition88 = new Expression<Boolean>(_17_0_5_edc0357_1345510114088_999820_14258_exists);
                elaborationRule88 = addElaborationRule(condition88, _17_0_5_edc0357_1345510113557_625793_13776.this, Customer._17_0_5_edc0357_1345510113557_625793_13776._17_0_5_edc0357_1345510114088_999820_14258.class, "fork_ForkNode_changePowerUsage", arguments88);
            }

            public _17_0_5_edc0357_1348165551875_6141_14745() {
                super();
                init_17_0_5_edc0357_1348165551875_6141_14745Members();
                init_17_0_5_edc0357_1348165551875_6141_14745Collections();
                init_17_0_5_edc0357_1348165551875_6141_14745Elaborations();
            }

            public _17_0_5_edc0357_1348165551875_6141_14745(Expression<Integer> invoker_endTime, Expression<Integer> objectToPass) {
                super();
                init_17_0_5_edc0357_1348165551875_6141_14745Members();
                init_17_0_5_edc0357_1348165551875_6141_14745Collections();
                addDependency(this.invoker_endTime, invoker_endTime);
                addDependency(this.objectToPass, objectToPass);
                init_17_0_5_edc0357_1348165551875_6141_14745Elaborations();
                fixTimeDependencies();
            }
        }

        public _17_0_5_edc0357_1345510113557_625793_13776(Expression<Integer> endTime) {
            super();
            init_17_0_5_edc0357_1345510113557_625793_13776Members();
            init_17_0_5_edc0357_1345510113557_625793_13776Collections();
            removeDependency(this.endTime);
            addDependency(this.endTime, endTime);
            init_17_0_5_edc0357_1345510113557_625793_13776Elaborations();
            fixTimeDependencies();
        }
    }

    public class _17_0_5_edc0357_1345510113559_180117_13777 extends DurativeEvent {

        public Parameter< ObjectFlow<Customer> > sig_17_0_5_edc0357_1345510114105_705998_14305 = null;

        public Parameter< ObjectFlow<Customer> > sig_17_0_5_edc0357_1345510114105_129800_14307 = null;

        public Parameter< ObjectFlow<Object> > sig_17_0_5_edc0357_1345510114106_840970_14313 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_5_edc0357_1345510114105_724841_14306 = null;

        public IntegerParameter invoke_time = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_5_edc0357_1345510114105_938474_14303 = null;

        public ElaborationRule elaborationRule89 = null;

        public void init_17_0_5_edc0357_1345510113559_180117_13777Members() {
            try {
                sig_17_0_5_edc0357_1345510114105_705998_14305 = new Parameter<ObjectFlow<Customer>>("sig_17_0_5_edc0357_1345510114105_705998_14305", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114105_705998_14305"), this);
                sig_17_0_5_edc0357_1345510114105_129800_14307 = new Parameter<ObjectFlow<Customer>>("sig_17_0_5_edc0357_1345510114105_129800_14307", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114105_129800_14307"), this);
                sig_17_0_5_edc0357_1345510114106_840970_14313 = new Parameter<ObjectFlow<Object>>("sig_17_0_5_edc0357_1345510114106_840970_14313", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114106_840970_14313"), this);
                sig_17_0_5_edc0357_1345510114105_724841_14306 = new Parameter<ObjectFlow<Integer>>("sig_17_0_5_edc0357_1345510114105_724841_14306", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114105_724841_14306"), this);
                invoke_time = new IntegerParameter("invoke_time", this);
                sig_17_0_5_edc0357_1345510114105_938474_14303 = new Parameter<ObjectFlow<Integer>>("sig_17_0_5_edc0357_1345510114105_938474_14303", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114105_938474_14303"), this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void init_17_0_5_edc0357_1345510113559_180117_13777Collections() {
            parameters.add(sig_17_0_5_edc0357_1345510114105_705998_14305);
            parameters.add(sig_17_0_5_edc0357_1345510114105_129800_14307);
            parameters.add(sig_17_0_5_edc0357_1345510114106_840970_14313);
            parameters.add(sig_17_0_5_edc0357_1345510114105_724841_14306);
            parameters.add(invoke_time);
            parameters.add(sig_17_0_5_edc0357_1345510114105_938474_14303);
        }

        public void init_17_0_5_edc0357_1345510113559_180117_13777Elaborations() {
            Expression<?>[] arguments89 = new Expression<?>[1];
            arguments89[0] = new Expression<Integer>(invoke_time);
            Expression<Boolean> condition89 = new Expression<Boolean>(true);
            elaborationRule89 = addElaborationRule(condition89, _17_0_5_edc0357_1345510113559_180117_13777.this, Customer._17_0_5_edc0357_1345510113559_180117_13777._17_0_5_edc0357_1345510114103_561395_14296.class, "_InitialNode_initialize", arguments89);
        }

        public _17_0_5_edc0357_1345510113559_180117_13777() {
            super();
            init_17_0_5_edc0357_1345510113559_180117_13777Members();
            init_17_0_5_edc0357_1345510113559_180117_13777Collections();
            init_17_0_5_edc0357_1345510113559_180117_13777Elaborations();
        }

        public class _17_0_5_edc0357_1345510114103_775997_14293 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1345510114104_596689_14298_exists = null;

            public Parameter< Customer > _17_0_5_edc0357_1345510114806_956292_15147 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114104_596689_14298_existsDependency = null;

            public ElaborationRule elaborationRule90 = null;

            public void init_17_0_5_edc0357_1345510114103_775997_14293Members() {
                try {
                    _17_0_5_edc0357_1345510114104_596689_14298_exists = new BooleanParameter("_17_0_5_edc0357_1345510114104_596689_14298_exists", this);
                    _17_0_5_edc0357_1345510114806_956292_15147 = new Parameter<Customer>("_17_0_5_edc0357_1345510114806_956292_15147", null, Customer.this, this);
                    _17_0_5_edc0357_1345510114104_596689_14298_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114104_596689_14298_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114103_775997_14293Collections() {
                parameters.add(_17_0_5_edc0357_1345510114104_596689_14298_exists);
                parameters.add(_17_0_5_edc0357_1345510114806_956292_15147);
                dependencies.add(_17_0_5_edc0357_1345510114104_596689_14298_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510114103_775997_14293Elaborations() {
                Expression<?>[] arguments90 = new Expression<?>[2];
                arguments90[0] = new Expression<Integer>(endTime);
                arguments90[1] = new Expression<Customer>(_17_0_5_edc0357_1345510114806_956292_15147);
                Expression<Boolean> condition90 = new Expression<Boolean>(_17_0_5_edc0357_1345510114104_596689_14298_exists);
                elaborationRule90 = addElaborationRule(condition90, _17_0_5_edc0357_1345510113559_180117_13777.this, Customer._17_0_5_edc0357_1345510113559_180117_13777._17_0_5_edc0357_1345510114104_596689_14298.class, "_ForkNode_initialize", arguments90);
            }

            public _17_0_5_edc0357_1345510114103_775997_14293() {
                super();
                init_17_0_5_edc0357_1345510114103_775997_14293Members();
                init_17_0_5_edc0357_1345510114103_775997_14293Collections();
                init_17_0_5_edc0357_1345510114103_775997_14293Elaborations();
            }
        }

        public class _17_0_5_edc0357_1345510114103_36875_14294 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1345510114807_983002_15149 = null;

            public Effect effect91 = null;

            public Object effect91Var = null;

            public void init_17_0_5_edc0357_1345510114103_36875_14294Members() {
                try {
                    _17_0_5_edc0357_1345510114807_983002_15149 = new IntegerParameter("_17_0_5_edc0357_1345510114807_983002_15149", this);
                    effect91Var = sig_17_0_5_edc0357_1345510114105_938474_14303;
                    effect91 = new EffectFunction(new FunctionCall((Object) effect91Var, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { _17_0_5_edc0357_1345510114807_983002_15149, startTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114103_36875_14294Collections() {
                parameters.add(_17_0_5_edc0357_1345510114807_983002_15149);
                Set<Effect> effectsForeffect91Var = new HashSet<Effect>();
                effectsForeffect91Var.add(effect91);
                effects.put((Parameter<?>) effect91Var, effectsForeffect91Var);
            }

            public void init_17_0_5_edc0357_1345510114103_36875_14294Elaborations() {
            }

            public _17_0_5_edc0357_1345510114103_36875_14294() {
                super();
                init_17_0_5_edc0357_1345510114103_36875_14294Members();
                init_17_0_5_edc0357_1345510114103_36875_14294Collections();
                init_17_0_5_edc0357_1345510114103_36875_14294Elaborations();
            }
        }

        public class _17_0_5_edc0357_1345510114103_810338_14295 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1345510114808_431003_15150 = null;

            public IntegerParameter _17_0_5_edc0357_1345510114104_812599_14301_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1345510114104_720146_14302_exists = null;

            public Parameter< Customer > _17_0_5_edc0357_1345510114809_763294_15151 = null;

            public ConstraintExpression constraint92 = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114808_431003_15150Dependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114104_720146_14302_existsDependency = null;

            public Dependency< Customer > _17_0_5_edc0357_1345510114809_763294_15151Dependency = null;

            public Effect effect93 = null;

            public Object effect93Var = null;

            public ElaborationRule elaborationRule94 = null;

            public void init_17_0_5_edc0357_1345510114103_810338_14295Members() {
                try {
                    _17_0_5_edc0357_1345510114808_431003_15150 = new IntegerParameter("_17_0_5_edc0357_1345510114808_431003_15150", this);
                    _17_0_5_edc0357_1345510114104_812599_14301_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114104_812599_14301_endTime", this);
                    _17_0_5_edc0357_1345510114104_720146_14302_exists = new BooleanParameter("_17_0_5_edc0357_1345510114104_720146_14302_exists", this);
                    _17_0_5_edc0357_1345510114809_763294_15151 = new Parameter<Customer>("_17_0_5_edc0357_1345510114809_763294_15151", null, null, this);
                    constraint92 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114104_812599_14301_endTime)));
                    _17_0_5_edc0357_1345510114808_431003_15150Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114808_431003_15150, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114105_938474_14303, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1345510114104_720146_14302_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114104_720146_14302_exists, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114106_840970_14313, Utils.getMethodForArgTypes("ObjectFlow<Object>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })));
                    _17_0_5_edc0357_1345510114809_763294_15151Dependency = new Dependency<Customer>(_17_0_5_edc0357_1345510114809_763294_15151, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114105_705998_14305, Utils.getMethodForArgTypes("ObjectFlow<Customer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    effect93Var = usage__17_0_5_edc0357_1345510113561_560076_13779;
                    effect93 = new EffectFunction(new FunctionCall((Object) effect93Var, Utils.getMethodForArgTypes("TimeVaryingMap<Integer>", "generated", "setValue", java.lang.Integer.class, java.lang.Object.class), new Object[] { startTime, _17_0_5_edc0357_1345510114808_431003_15150 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114103_810338_14295Collections() {
                parameters.add(_17_0_5_edc0357_1345510114808_431003_15150);
                parameters.add(_17_0_5_edc0357_1345510114104_812599_14301_endTime);
                parameters.add(_17_0_5_edc0357_1345510114104_720146_14302_exists);
                parameters.add(_17_0_5_edc0357_1345510114809_763294_15151);
                constraintExpressions.add(constraint92);
                dependencies.add(_17_0_5_edc0357_1345510114808_431003_15150Dependency);
                dependencies.add(_17_0_5_edc0357_1345510114104_720146_14302_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510114809_763294_15151Dependency);
                Set<Effect> effectsForeffect93Var = new HashSet<Effect>();
                effectsForeffect93Var.add(effect93);
                effects.put((Parameter<?>) effect93Var, effectsForeffect93Var);
            }

            public void init_17_0_5_edc0357_1345510114103_810338_14295Elaborations() {
                Expression<?>[] arguments94 = new Expression<?>[1];
                arguments94[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition94 = new Expression<Boolean>(_17_0_5_edc0357_1345510114104_720146_14302_exists);
                elaborationRule94 = addElaborationRule(condition94, _17_0_5_edc0357_1345510113559_180117_13777.this, Customer._17_0_5_edc0357_1345510113559_180117_13777._17_0_5_edc0357_1345510114104_720146_14302.class, "_JoinNode_initialize", arguments94);
            }

            public _17_0_5_edc0357_1345510114103_810338_14295() {
                super();
                init_17_0_5_edc0357_1345510114103_810338_14295Members();
                init_17_0_5_edc0357_1345510114103_810338_14295Collections();
                init_17_0_5_edc0357_1345510114103_810338_14295Elaborations();
            }

            public _17_0_5_edc0357_1345510114103_810338_14295(Expression<Integer> _17_0_5_edc0357_1345510114104_812599_14301_endTime) {
                super();
                init_17_0_5_edc0357_1345510114103_810338_14295Members();
                init_17_0_5_edc0357_1345510114103_810338_14295Collections();
                addDependency(this._17_0_5_edc0357_1345510114104_812599_14301_endTime, _17_0_5_edc0357_1345510114104_812599_14301_endTime);
                init_17_0_5_edc0357_1345510114103_810338_14295Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114103_561395_14296 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1345510114104_812599_14301_exists = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114104_812599_14301_existsDependency = null;

            public ElaborationRule elaborationRule95 = null;

            public void init_17_0_5_edc0357_1345510114103_561395_14296Members() {
                try {
                    _17_0_5_edc0357_1345510114104_812599_14301_exists = new BooleanParameter("_17_0_5_edc0357_1345510114104_812599_14301_exists", this);
                    _17_0_5_edc0357_1345510114104_812599_14301_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114104_812599_14301_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114103_561395_14296Collections() {
                parameters.add(_17_0_5_edc0357_1345510114104_812599_14301_exists);
                dependencies.add(_17_0_5_edc0357_1345510114104_812599_14301_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510114103_561395_14296Elaborations() {
                Expression<?>[] arguments95 = new Expression<?>[1];
                arguments95[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition95 = new Expression<Boolean>(_17_0_5_edc0357_1345510114104_812599_14301_exists);
                elaborationRule95 = addElaborationRule(condition95, _17_0_5_edc0357_1345510113559_180117_13777.this, Customer._17_0_5_edc0357_1345510113559_180117_13777._17_0_5_edc0357_1345510114104_812599_14301.class, "_ForkNode_initialize", arguments95);
            }

            public _17_0_5_edc0357_1345510114103_561395_14296() {
                super();
                init_17_0_5_edc0357_1345510114103_561395_14296Members();
                init_17_0_5_edc0357_1345510114103_561395_14296Collections();
                init_17_0_5_edc0357_1345510114103_561395_14296Elaborations();
            }

            public _17_0_5_edc0357_1345510114103_561395_14296(Expression<Integer> startTime) {
                super();
                init_17_0_5_edc0357_1345510114103_561395_14296Members();
                init_17_0_5_edc0357_1345510114103_561395_14296Collections();
                removeDependency(this.startTime);
                addDependency(this.startTime, startTime);
                init_17_0_5_edc0357_1345510114103_561395_14296Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114103_977080_14297 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1345510114104_720146_14302_endTime = null;

            public ConstraintExpression constraint96 = null;

            public void init_17_0_5_edc0357_1345510114103_977080_14297Members() {
                try {
                    _17_0_5_edc0357_1345510114104_720146_14302_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114104_720146_14302_endTime", this);
                    constraint96 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114104_720146_14302_endTime)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114103_977080_14297Collections() {
                parameters.add(_17_0_5_edc0357_1345510114104_720146_14302_endTime);
                constraintExpressions.add(constraint96);
            }

            public void init_17_0_5_edc0357_1345510114103_977080_14297Elaborations() {
            }

            public _17_0_5_edc0357_1345510114103_977080_14297() {
                super();
                init_17_0_5_edc0357_1345510114103_977080_14297Members();
                init_17_0_5_edc0357_1345510114103_977080_14297Collections();
                init_17_0_5_edc0357_1345510114103_977080_14297Elaborations();
            }

            public _17_0_5_edc0357_1345510114103_977080_14297(Expression<Integer> _17_0_5_edc0357_1345510114104_720146_14302_endTime) {
                super();
                init_17_0_5_edc0357_1345510114103_977080_14297Members();
                init_17_0_5_edc0357_1345510114103_977080_14297Collections();
                addDependency(this._17_0_5_edc0357_1345510114104_720146_14302_endTime, _17_0_5_edc0357_1345510114104_720146_14302_endTime);
                init_17_0_5_edc0357_1345510114103_977080_14297Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114104_596689_14298 extends DurativeEvent {

            public Parameter< Customer > objectToPass = null;

            public IntegerParameter _17_0_5_edc0357_1345510114103_775997_14293_endTime = null;

            public ConstraintExpression constraint97 = null;

            public Effect effect98 = null;

            public Object effect98Var = null;

            public Effect effect99 = null;

            public Object effect99Var = null;

            public void init_17_0_5_edc0357_1345510114104_596689_14298Members() {
                try {
                    objectToPass = new Parameter<Customer>("objectToPass", null, null, this);
                    _17_0_5_edc0357_1345510114103_775997_14293_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114103_775997_14293_endTime", this);
                    constraint97 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114103_775997_14293_endTime)));
                    effect98Var = sig_17_0_5_edc0357_1345510114105_705998_14305;
                    effect98 = new EffectFunction(new FunctionCall((Object) effect98Var, Utils.getMethodForArgTypes("ObjectFlow<Customer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                    effect99Var = sig_17_0_5_edc0357_1345510114105_129800_14307;
                    effect99 = new EffectFunction(new FunctionCall((Object) effect99Var, Utils.getMethodForArgTypes("ObjectFlow<Customer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114104_596689_14298Collections() {
                parameters.add(objectToPass);
                parameters.add(_17_0_5_edc0357_1345510114103_775997_14293_endTime);
                constraintExpressions.add(constraint97);
                Set<Effect> effectsForeffect98Var = new HashSet<Effect>();
                effectsForeffect98Var.add(effect98);
                effects.put((Parameter<?>) effect98Var, effectsForeffect98Var);
                Set<Effect> effectsForeffect99Var = new HashSet<Effect>();
                effectsForeffect99Var.add(effect99);
                effects.put((Parameter<?>) effect99Var, effectsForeffect99Var);
            }

            public void init_17_0_5_edc0357_1345510114104_596689_14298Elaborations() {
            }

            public _17_0_5_edc0357_1345510114104_596689_14298() {
                super();
                init_17_0_5_edc0357_1345510114104_596689_14298Members();
                init_17_0_5_edc0357_1345510114104_596689_14298Collections();
                init_17_0_5_edc0357_1345510114104_596689_14298Elaborations();
            }

            public _17_0_5_edc0357_1345510114104_596689_14298(Expression<Integer> _17_0_5_edc0357_1345510114103_775997_14293_endTime, Expression<Customer> objectToPass) {
                super();
                init_17_0_5_edc0357_1345510114104_596689_14298Members();
                init_17_0_5_edc0357_1345510114104_596689_14298Collections();
                addDependency(this._17_0_5_edc0357_1345510114103_775997_14293_endTime, _17_0_5_edc0357_1345510114103_775997_14293_endTime);
                addDependency(this.objectToPass, objectToPass);
                init_17_0_5_edc0357_1345510114104_596689_14298Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114104_663398_14299 extends DurativeEvent {

            public Parameter< Customer > _17_0_5_edc0357_1345510114810_534712_15153 = null;

            public IntegerParameter _17_0_5_edc0357_1345510114104_812599_14301_endTime = null;

            public IntegerParameter _17_0_5_edc0357_1345510114810_895741_15152 = null;

            public ConstraintExpression constraint100 = null;

            public Dependency< Customer > _17_0_5_edc0357_1345510114810_534712_15153Dependency = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114810_895741_15152Dependency = null;

            public Effect effect101 = null;

            public Object effect101Var = null;

            public Effect effect102 = null;

            public Object effect102Var = null;

            public void init_17_0_5_edc0357_1345510114104_663398_14299Members() {
                try {
                    _17_0_5_edc0357_1345510114810_534712_15153 = new Parameter<Customer>("_17_0_5_edc0357_1345510114810_534712_15153", null, null, this);
                    _17_0_5_edc0357_1345510114104_812599_14301_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114104_812599_14301_endTime", this);
                    _17_0_5_edc0357_1345510114810_895741_15152 = new IntegerParameter("_17_0_5_edc0357_1345510114810_895741_15152", this);
                    constraint100 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114104_812599_14301_endTime)));
                    _17_0_5_edc0357_1345510114810_534712_15153Dependency = new Dependency<Customer>(_17_0_5_edc0357_1345510114810_534712_15153, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114105_129800_14307, Utils.getMethodForArgTypes("ObjectFlow<Customer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1345510114810_895741_15152Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114810_895741_15152, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114105_724841_14306, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    effect101Var = sig_17_0_5_edc0357_1345510114106_840970_14313;
                    effect101 = new EffectFunction(new FunctionCall((Object) effect101Var, Utils.getMethodForArgTypes("ObjectFlow<Object>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { true, startTime }));
                    effect102Var = cap__17_0_5_edc0357_1345510113566_55150_13784;
                    effect102 = new EffectFunction(new FunctionCall((Object) effect102Var, Utils.getMethodForArgTypes("TimeVaryingMap<Integer>", "generated", "setValue", java.lang.Integer.class, java.lang.Object.class), new Object[] { startTime, _17_0_5_edc0357_1345510114810_895741_15152 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114104_663398_14299Collections() {
                parameters.add(_17_0_5_edc0357_1345510114810_534712_15153);
                parameters.add(_17_0_5_edc0357_1345510114104_812599_14301_endTime);
                parameters.add(_17_0_5_edc0357_1345510114810_895741_15152);
                constraintExpressions.add(constraint100);
                dependencies.add(_17_0_5_edc0357_1345510114810_534712_15153Dependency);
                dependencies.add(_17_0_5_edc0357_1345510114810_895741_15152Dependency);
                Set<Effect> effectsForeffect101Var = new HashSet<Effect>();
                effectsForeffect101Var.add(effect101);
                effects.put((Parameter<?>) effect101Var, effectsForeffect101Var);
                Set<Effect> effectsForeffect102Var = new HashSet<Effect>();
                effectsForeffect102Var.add(effect102);
                effects.put((Parameter<?>) effect102Var, effectsForeffect102Var);
            }

            public void init_17_0_5_edc0357_1345510114104_663398_14299Elaborations() {
            }

            public _17_0_5_edc0357_1345510114104_663398_14299() {
                super();
                init_17_0_5_edc0357_1345510114104_663398_14299Members();
                init_17_0_5_edc0357_1345510114104_663398_14299Collections();
                init_17_0_5_edc0357_1345510114104_663398_14299Elaborations();
            }

            public _17_0_5_edc0357_1345510114104_663398_14299(Expression<Integer> _17_0_5_edc0357_1345510114104_812599_14301_endTime) {
                super();
                init_17_0_5_edc0357_1345510114104_663398_14299Members();
                init_17_0_5_edc0357_1345510114104_663398_14299Collections();
                addDependency(this._17_0_5_edc0357_1345510114104_812599_14301_endTime, _17_0_5_edc0357_1345510114104_812599_14301_endTime);
                init_17_0_5_edc0357_1345510114104_663398_14299Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114104_334510_14300 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1345510114811_932135_15155 = null;

            public Effect effect103 = null;

            public Object effect103Var = null;

            public void init_17_0_5_edc0357_1345510114104_334510_14300Members() {
                try {
                    _17_0_5_edc0357_1345510114811_932135_15155 = new IntegerParameter("_17_0_5_edc0357_1345510114811_932135_15155", this);
                    effect103Var = sig_17_0_5_edc0357_1345510114105_724841_14306;
                    effect103 = new EffectFunction(new FunctionCall((Object) effect103Var, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { _17_0_5_edc0357_1345510114811_932135_15155, startTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114104_334510_14300Collections() {
                parameters.add(_17_0_5_edc0357_1345510114811_932135_15155);
                Set<Effect> effectsForeffect103Var = new HashSet<Effect>();
                effectsForeffect103Var.add(effect103);
                effects.put((Parameter<?>) effect103Var, effectsForeffect103Var);
            }

            public void init_17_0_5_edc0357_1345510114104_334510_14300Elaborations() {
            }

            public _17_0_5_edc0357_1345510114104_334510_14300() {
                super();
                init_17_0_5_edc0357_1345510114104_334510_14300Members();
                init_17_0_5_edc0357_1345510114104_334510_14300Collections();
                init_17_0_5_edc0357_1345510114104_334510_14300Elaborations();
            }
        }

        public class _17_0_5_edc0357_1345510114104_812599_14301 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1345510114103_810338_14295_exists = null;

            public IntegerParameter _17_0_5_edc0357_1345510114103_561395_14296_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1345510114104_663398_14299_exists = null;

            public ConstraintExpression constraint104 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114103_810338_14295_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114104_663398_14299_existsDependency = null;

            public ElaborationRule elaborationRule105 = null;

            public ElaborationRule elaborationRule106 = null;

            public void init_17_0_5_edc0357_1345510114104_812599_14301Members() {
                try {
                    _17_0_5_edc0357_1345510114103_810338_14295_exists = new BooleanParameter("_17_0_5_edc0357_1345510114103_810338_14295_exists", this);
                    _17_0_5_edc0357_1345510114103_561395_14296_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114103_561395_14296_endTime", this);
                    _17_0_5_edc0357_1345510114104_663398_14299_exists = new BooleanParameter("_17_0_5_edc0357_1345510114104_663398_14299_exists", this);
                    constraint104 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114103_561395_14296_endTime)));
                    _17_0_5_edc0357_1345510114103_810338_14295_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114103_810338_14295_exists, new Functions.And(new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114105_705998_14305, Utils.getMethodForArgTypes("ObjectFlow<Customer>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114105_938474_14303, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) }))));
                    _17_0_5_edc0357_1345510114104_663398_14299_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114104_663398_14299_exists, new Functions.And(new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114105_129800_14307, Utils.getMethodForArgTypes("ObjectFlow<Customer>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114105_724841_14306, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) }))));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114104_812599_14301Collections() {
                parameters.add(_17_0_5_edc0357_1345510114103_810338_14295_exists);
                parameters.add(_17_0_5_edc0357_1345510114103_561395_14296_endTime);
                parameters.add(_17_0_5_edc0357_1345510114104_663398_14299_exists);
                constraintExpressions.add(constraint104);
                dependencies.add(_17_0_5_edc0357_1345510114103_810338_14295_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510114104_663398_14299_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510114104_812599_14301Elaborations() {
                Expression<?>[] arguments105 = new Expression<?>[1];
                arguments105[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition105 = new Expression<Boolean>(_17_0_5_edc0357_1345510114104_663398_14299_exists);
                elaborationRule105 = addElaborationRule(condition105, _17_0_5_edc0357_1345510113559_180117_13777.this, Customer._17_0_5_edc0357_1345510113559_180117_13777._17_0_5_edc0357_1345510114104_663398_14299.class, "_AddStructuralFeatureValueAction_initialize", arguments105);
                Expression<?>[] arguments106 = new Expression<?>[1];
                arguments106[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition106 = new Expression<Boolean>(_17_0_5_edc0357_1345510114103_810338_14295_exists);
                elaborationRule106 = addElaborationRule(condition106, _17_0_5_edc0357_1345510113559_180117_13777.this, Customer._17_0_5_edc0357_1345510113559_180117_13777._17_0_5_edc0357_1345510114103_810338_14295.class, "_AddStructuralFeatureValueAction_initialize", arguments106);
            }

            public _17_0_5_edc0357_1345510114104_812599_14301() {
                super();
                init_17_0_5_edc0357_1345510114104_812599_14301Members();
                init_17_0_5_edc0357_1345510114104_812599_14301Collections();
                init_17_0_5_edc0357_1345510114104_812599_14301Elaborations();
            }

            public _17_0_5_edc0357_1345510114104_812599_14301(Expression<Integer> _17_0_5_edc0357_1345510114103_561395_14296_endTime) {
                super();
                init_17_0_5_edc0357_1345510114104_812599_14301Members();
                init_17_0_5_edc0357_1345510114104_812599_14301Collections();
                addDependency(this._17_0_5_edc0357_1345510114103_561395_14296_endTime, _17_0_5_edc0357_1345510114103_561395_14296_endTime);
                init_17_0_5_edc0357_1345510114104_812599_14301Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114104_720146_14302 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1345510114103_810338_14295_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1345510114103_977080_14297_exists = null;

            public ConstraintExpression constraint107 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114103_977080_14297_existsDependency = null;

            public ElaborationRule elaborationRule108 = null;

            public void init_17_0_5_edc0357_1345510114104_720146_14302Members() {
                try {
                    _17_0_5_edc0357_1345510114103_810338_14295_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114103_810338_14295_endTime", this);
                    _17_0_5_edc0357_1345510114103_977080_14297_exists = new BooleanParameter("_17_0_5_edc0357_1345510114103_977080_14297_exists", this);
                    constraint107 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114103_810338_14295_endTime)));
                    _17_0_5_edc0357_1345510114103_977080_14297_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114103_977080_14297_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114104_720146_14302Collections() {
                parameters.add(_17_0_5_edc0357_1345510114103_810338_14295_endTime);
                parameters.add(_17_0_5_edc0357_1345510114103_977080_14297_exists);
                constraintExpressions.add(constraint107);
                dependencies.add(_17_0_5_edc0357_1345510114103_977080_14297_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510114104_720146_14302Elaborations() {
                Expression<?>[] arguments108 = new Expression<?>[1];
                arguments108[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition108 = new Expression<Boolean>(_17_0_5_edc0357_1345510114103_977080_14297_exists);
                elaborationRule108 = addElaborationRule(condition108, _17_0_5_edc0357_1345510113559_180117_13777.this, Customer._17_0_5_edc0357_1345510113559_180117_13777._17_0_5_edc0357_1345510114103_977080_14297.class, "_ActivityFinalNode_initialize", arguments108);
            }

            public _17_0_5_edc0357_1345510114104_720146_14302() {
                super();
                init_17_0_5_edc0357_1345510114104_720146_14302Members();
                init_17_0_5_edc0357_1345510114104_720146_14302Collections();
                init_17_0_5_edc0357_1345510114104_720146_14302Elaborations();
            }

            public _17_0_5_edc0357_1345510114104_720146_14302(Expression<Integer> _17_0_5_edc0357_1345510114103_810338_14295_endTime) {
                super();
                init_17_0_5_edc0357_1345510114104_720146_14302Members();
                init_17_0_5_edc0357_1345510114104_720146_14302Collections();
                addDependency(this._17_0_5_edc0357_1345510114103_810338_14295_endTime, _17_0_5_edc0357_1345510114103_810338_14295_endTime);
                init_17_0_5_edc0357_1345510114104_720146_14302Elaborations();
                fixTimeDependencies();
            }
        }

        public _17_0_5_edc0357_1345510113559_180117_13777(Expression<Integer> endTime) {
            super();
            init_17_0_5_edc0357_1345510113559_180117_13777Members();
            init_17_0_5_edc0357_1345510113559_180117_13777Collections();
            removeDependency(this.endTime);
            addDependency(this.endTime, endTime);
            init_17_0_5_edc0357_1345510113559_180117_13777Elaborations();
            fixTimeDependencies();
        }
    }

    public class _17_0_5_edc0357_1345510113560_522513_13778 extends DurativeEvent {

        public Parameter< ObjectFlow<Integer> > sig_17_0_5_edc0357_1345510114167_567578_14359 = null;

        public IntegerParameter _17_0_5_edc0357_1346105157074_69873_15283 = null;

        public Parameter< ObjectFlow<Boolean> > sig_17_0_5_edc0357_1348187257705_321405_14980 = null;

        public Parameter< ObjectFlow<Customer> > sig_17_0_5_edc0357_1345510114168_746941_14366 = null;

        public Parameter< ObjectFlow<Customer> > sig_17_0_5_edc0357_1345510114168_147606_14365 = null;

        public Parameter< ObjectFlow<Integer> > sig_17_0_5_edc0357_1345510114167_676201_14362 = null;

        public IntegerParameter invoke_time = null;

        public Parameter< ObjectFlow<Customer> > sig_17_0_5_edc0357_1345510114167_732692_14364 = null;

        public ElaborationRule elaborationRule109 = null;

        public ElaborationRule elaborationRule110 = null;

        public void init_17_0_5_edc0357_1345510113560_522513_13778Members() {
            try {
                sig_17_0_5_edc0357_1345510114167_567578_14359 = new Parameter<ObjectFlow<Integer>>("sig_17_0_5_edc0357_1345510114167_567578_14359", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114167_567578_14359"), this);
                _17_0_5_edc0357_1346105157074_69873_15283 = new IntegerParameter("_17_0_5_edc0357_1346105157074_69873_15283", this);
                sig_17_0_5_edc0357_1348187257705_321405_14980 = new Parameter<ObjectFlow<Boolean>>("sig_17_0_5_edc0357_1348187257705_321405_14980", null, new ObjectFlow("sig_17_0_5_edc0357_1348187257705_321405_14980"), this);
                sig_17_0_5_edc0357_1345510114168_746941_14366 = new Parameter<ObjectFlow<Customer>>("sig_17_0_5_edc0357_1345510114168_746941_14366", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114168_746941_14366"), this);
                sig_17_0_5_edc0357_1345510114168_147606_14365 = new Parameter<ObjectFlow<Customer>>("sig_17_0_5_edc0357_1345510114168_147606_14365", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114168_147606_14365"), this);
                sig_17_0_5_edc0357_1345510114167_676201_14362 = new Parameter<ObjectFlow<Integer>>("sig_17_0_5_edc0357_1345510114167_676201_14362", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114167_676201_14362"), this);
                invoke_time = new IntegerParameter("invoke_time", this);
                sig_17_0_5_edc0357_1345510114167_732692_14364 = new Parameter<ObjectFlow<Customer>>("sig_17_0_5_edc0357_1345510114167_732692_14364", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114167_732692_14364"), this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void init_17_0_5_edc0357_1345510113560_522513_13778Collections() {
            parameters.add(sig_17_0_5_edc0357_1345510114167_567578_14359);
            parameters.add(_17_0_5_edc0357_1346105157074_69873_15283);
            parameters.add(sig_17_0_5_edc0357_1348187257705_321405_14980);
            parameters.add(sig_17_0_5_edc0357_1345510114168_746941_14366);
            parameters.add(sig_17_0_5_edc0357_1345510114168_147606_14365);
            parameters.add(sig_17_0_5_edc0357_1345510114167_676201_14362);
            parameters.add(invoke_time);
            parameters.add(sig_17_0_5_edc0357_1345510114167_732692_14364);
        }

        public void init_17_0_5_edc0357_1345510113560_522513_13778Elaborations() {
            Expression<?>[] arguments109 = new Expression<?>[2];
            arguments109[0] = new Expression<Integer>(invoke_time);
            arguments109[1] = new Expression<Integer>(_17_0_5_edc0357_1346105157074_69873_15283);
            Expression<Boolean> condition109 = new Expression<Boolean>(true);
            elaborationRule109 = addElaborationRule(condition109, _17_0_5_edc0357_1345510113560_522513_13778.this, Customer._17_0_5_edc0357_1345510113560_522513_13778._17_0_5_edc0357_1346105164389_404592_15285.class, "capval_ActivityParameterNode_setDRCap", arguments109);
            Expression<?>[] arguments110 = new Expression<?>[1];
            arguments110[0] = new Expression<Integer>(invoke_time);
            Expression<Boolean> condition110 = new Expression<Boolean>(true);
            elaborationRule110 = addElaborationRule(condition110, _17_0_5_edc0357_1345510113560_522513_13778.this, Customer._17_0_5_edc0357_1345510113560_522513_13778._17_0_5_edc0357_1345510114161_34701_14349.class, "customerInitial_InitialNode_setDRCap", arguments110);
        }

        public _17_0_5_edc0357_1345510113560_522513_13778() {
            super();
            init_17_0_5_edc0357_1345510113560_522513_13778Members();
            init_17_0_5_edc0357_1345510113560_522513_13778Collections();
            init_17_0_5_edc0357_1345510113560_522513_13778Elaborations();
        }

        public class _17_0_5_edc0357_1345510114159_726624_14346 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1345510114163_254081_14351_exists = null;

            public Parameter< Customer > _17_0_5_edc0357_1345510114817_17394_15168 = null;

            public BooleanParameter _17_0_5_edc0357_1345510114166_387809_14355_exists = null;

            public IntegerParameter _17_0_5_edc0357_1348187326552_305667_14984_endTime = null;

            public ConstraintExpression constraint111 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114163_254081_14351_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114166_387809_14355_existsDependency = null;

            public ElaborationRule elaborationRule112 = null;

            public ElaborationRule elaborationRule113 = null;

            public void init_17_0_5_edc0357_1345510114159_726624_14346Members() {
                try {
                    _17_0_5_edc0357_1345510114163_254081_14351_exists = new BooleanParameter("_17_0_5_edc0357_1345510114163_254081_14351_exists", this);
                    _17_0_5_edc0357_1345510114817_17394_15168 = new Parameter<Customer>("_17_0_5_edc0357_1345510114817_17394_15168", null, Customer.this, this);
                    _17_0_5_edc0357_1345510114166_387809_14355_exists = new BooleanParameter("_17_0_5_edc0357_1345510114166_387809_14355_exists", this);
                    _17_0_5_edc0357_1348187326552_305667_14984_endTime = new IntegerParameter("_17_0_5_edc0357_1348187326552_305667_14984_endTime", this);
                    constraint111 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1348187326552_305667_14984_endTime)));
                    _17_0_5_edc0357_1345510114163_254081_14351_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114163_254081_14351_exists, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1348187257705_321405_14980, Utils.getMethodForArgTypes("ObjectFlow<Boolean>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })));
                    _17_0_5_edc0357_1345510114166_387809_14355_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114166_387809_14355_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114159_726624_14346Collections() {
                parameters.add(_17_0_5_edc0357_1345510114163_254081_14351_exists);
                parameters.add(_17_0_5_edc0357_1345510114817_17394_15168);
                parameters.add(_17_0_5_edc0357_1345510114166_387809_14355_exists);
                parameters.add(_17_0_5_edc0357_1348187326552_305667_14984_endTime);
                constraintExpressions.add(constraint111);
                dependencies.add(_17_0_5_edc0357_1345510114163_254081_14351_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510114166_387809_14355_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510114159_726624_14346Elaborations() {
                Expression<?>[] arguments112 = new Expression<?>[2];
                arguments112[0] = new Expression<Integer>(endTime);
                arguments112[1] = new Expression<Customer>(_17_0_5_edc0357_1345510114817_17394_15168);
                Expression<Boolean> condition112 = new Expression<Boolean>(_17_0_5_edc0357_1345510114166_387809_14355_exists);
                elaborationRule112 = addElaborationRule(condition112, _17_0_5_edc0357_1345510113560_522513_13778.this, Customer._17_0_5_edc0357_1345510113560_522513_13778._17_0_5_edc0357_1345510114166_387809_14355.class, "forkNodeReadSelf_ForkNode_setDRCap", arguments112);
                Expression<?>[] arguments113 = new Expression<?>[1];
                arguments113[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition113 = new Expression<Boolean>(_17_0_5_edc0357_1345510114163_254081_14351_exists);
                elaborationRule113 = addElaborationRule(condition113, _17_0_5_edc0357_1345510113560_522513_13778.this, Customer._17_0_5_edc0357_1345510113560_522513_13778._17_0_5_edc0357_1345510114163_254081_14351.class, "customerDecideParticipation_DecisionNode_setDRCap", arguments113);
            }

            public _17_0_5_edc0357_1345510114159_726624_14346() {
                super();
                init_17_0_5_edc0357_1345510114159_726624_14346Members();
                init_17_0_5_edc0357_1345510114159_726624_14346Collections();
                init_17_0_5_edc0357_1345510114159_726624_14346Elaborations();
            }

            public _17_0_5_edc0357_1345510114159_726624_14346(Expression<Integer> _17_0_5_edc0357_1348187326552_305667_14984_endTime) {
                super();
                init_17_0_5_edc0357_1345510114159_726624_14346Members();
                init_17_0_5_edc0357_1345510114159_726624_14346Collections();
                addDependency(this._17_0_5_edc0357_1348187326552_305667_14984_endTime, _17_0_5_edc0357_1348187326552_305667_14984_endTime);
                init_17_0_5_edc0357_1345510114159_726624_14346Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114160_29879_14347 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1345510114165_641732_14354_exists = null;

            public IntegerParameter _17_0_5_edc0357_1345510114818_835880_15169 = null;

            public Parameter< Customer > _17_0_5_edc0357_1345510114819_229911_15170 = null;

            public IntegerParameter _17_0_5_edc0357_1345510114163_254081_14351_endTime = null;

            public ConstraintExpression constraint114 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114165_641732_14354_existsDependency = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114818_835880_15169Dependency = null;

            public Dependency< Customer > _17_0_5_edc0357_1345510114819_229911_15170Dependency = null;

            public Effect effect115 = null;

            public Object effect115Var = null;

            public ElaborationRule elaborationRule116 = null;

            public void init_17_0_5_edc0357_1345510114160_29879_14347Members() {
                try {
                    _17_0_5_edc0357_1345510114165_641732_14354_exists = new BooleanParameter("_17_0_5_edc0357_1345510114165_641732_14354_exists", this);
                    _17_0_5_edc0357_1345510114818_835880_15169 = new IntegerParameter("_17_0_5_edc0357_1345510114818_835880_15169", this);
                    _17_0_5_edc0357_1345510114819_229911_15170 = new Parameter<Customer>("_17_0_5_edc0357_1345510114819_229911_15170", null, null, this);
                    _17_0_5_edc0357_1345510114163_254081_14351_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114163_254081_14351_endTime", this);
                    constraint114 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114163_254081_14351_endTime)));
                    _17_0_5_edc0357_1345510114165_641732_14354_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114165_641732_14354_exists, new Functions.And(new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114167_676201_14362, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114168_147606_14365, Utils.getMethodForArgTypes("ObjectFlow<Customer>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) }))));
                    _17_0_5_edc0357_1345510114818_835880_15169Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114818_835880_15169, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114167_567578_14359, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1345510114819_229911_15170Dependency = new Dependency<Customer>(_17_0_5_edc0357_1345510114819_229911_15170, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114167_732692_14364, Utils.getMethodForArgTypes("ObjectFlow<Customer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    effect115Var = cap__17_0_5_edc0357_1345510113566_55150_13784;
                    effect115 = new EffectFunction(new FunctionCall((Object) effect115Var, Utils.getMethodForArgTypes("TimeVaryingMap<Integer>", "generated", "setValue", java.lang.Integer.class, java.lang.Object.class), new Object[] { startTime, _17_0_5_edc0357_1345510114818_835880_15169 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114160_29879_14347Collections() {
                parameters.add(_17_0_5_edc0357_1345510114165_641732_14354_exists);
                parameters.add(_17_0_5_edc0357_1345510114818_835880_15169);
                parameters.add(_17_0_5_edc0357_1345510114819_229911_15170);
                parameters.add(_17_0_5_edc0357_1345510114163_254081_14351_endTime);
                constraintExpressions.add(constraint114);
                dependencies.add(_17_0_5_edc0357_1345510114165_641732_14354_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510114818_835880_15169Dependency);
                dependencies.add(_17_0_5_edc0357_1345510114819_229911_15170Dependency);
                Set<Effect> effectsForeffect115Var = new HashSet<Effect>();
                effectsForeffect115Var.add(effect115);
                effects.put((Parameter<?>) effect115Var, effectsForeffect115Var);
            }

            public void init_17_0_5_edc0357_1345510114160_29879_14347Elaborations() {
                Expression<?>[] arguments116 = new Expression<?>[1];
                arguments116[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition116 = new Expression<Boolean>(_17_0_5_edc0357_1345510114165_641732_14354_exists);
                elaborationRule116 = addElaborationRule(condition116, _17_0_5_edc0357_1345510113560_522513_13778.this, Customer._17_0_5_edc0357_1345510113560_522513_13778._17_0_5_edc0357_1345510114165_641732_14354.class, "sendSignalYes_SendSignalAction_setDRCap", arguments116);
            }

            public _17_0_5_edc0357_1345510114160_29879_14347() {
                super();
                init_17_0_5_edc0357_1345510114160_29879_14347Members();
                init_17_0_5_edc0357_1345510114160_29879_14347Collections();
                init_17_0_5_edc0357_1345510114160_29879_14347Elaborations();
            }

            public _17_0_5_edc0357_1345510114160_29879_14347(Expression<Integer> _17_0_5_edc0357_1345510114163_254081_14351_endTime) {
                super();
                init_17_0_5_edc0357_1345510114160_29879_14347Members();
                init_17_0_5_edc0357_1345510114160_29879_14347Collections();
                addDependency(this._17_0_5_edc0357_1345510114163_254081_14351_endTime, _17_0_5_edc0357_1345510114163_254081_14351_endTime);
                init_17_0_5_edc0357_1345510114160_29879_14347Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114161_34701_14349 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1348187326552_305667_14984_exists = null;

            public Dependency< Boolean > _17_0_5_edc0357_1348187326552_305667_14984_existsDependency = null;

            public ElaborationRule elaborationRule117 = null;

            public void init_17_0_5_edc0357_1345510114161_34701_14349Members() {
                try {
                    _17_0_5_edc0357_1348187326552_305667_14984_exists = new BooleanParameter("_17_0_5_edc0357_1348187326552_305667_14984_exists", this);
                    _17_0_5_edc0357_1348187326552_305667_14984_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1348187326552_305667_14984_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114161_34701_14349Collections() {
                parameters.add(_17_0_5_edc0357_1348187326552_305667_14984_exists);
                dependencies.add(_17_0_5_edc0357_1348187326552_305667_14984_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510114161_34701_14349Elaborations() {
                Expression<?>[] arguments117 = new Expression<?>[1];
                arguments117[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition117 = new Expression<Boolean>(_17_0_5_edc0357_1348187326552_305667_14984_exists);
                elaborationRule117 = addElaborationRule(condition117, _17_0_5_edc0357_1345510113560_522513_13778.this, Customer._17_0_5_edc0357_1345510113560_522513_13778._17_0_5_edc0357_1348187326552_305667_14984.class, "_ForkNode_setDRCap", arguments117);
            }

            public _17_0_5_edc0357_1345510114161_34701_14349() {
                super();
                init_17_0_5_edc0357_1345510114161_34701_14349Members();
                init_17_0_5_edc0357_1345510114161_34701_14349Collections();
                init_17_0_5_edc0357_1345510114161_34701_14349Elaborations();
            }

            public _17_0_5_edc0357_1345510114161_34701_14349(Expression<Integer> startTime) {
                super();
                init_17_0_5_edc0357_1345510114161_34701_14349Members();
                init_17_0_5_edc0357_1345510114161_34701_14349Collections();
                removeDependency(this.startTime);
                addDependency(this.startTime, startTime);
                init_17_0_5_edc0357_1345510114161_34701_14349Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114162_717249_14350 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1348188047453_317575_14999_endTime = null;

            public ConstraintExpression constraint118 = null;

            public void init_17_0_5_edc0357_1345510114162_717249_14350Members() {
                try {
                    _17_0_5_edc0357_1348188047453_317575_14999_endTime = new IntegerParameter("_17_0_5_edc0357_1348188047453_317575_14999_endTime", this);
                    constraint118 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1348188047453_317575_14999_endTime)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114162_717249_14350Collections() {
                parameters.add(_17_0_5_edc0357_1348188047453_317575_14999_endTime);
                constraintExpressions.add(constraint118);
            }

            public void init_17_0_5_edc0357_1345510114162_717249_14350Elaborations() {
            }

            public _17_0_5_edc0357_1345510114162_717249_14350() {
                super();
                init_17_0_5_edc0357_1345510114162_717249_14350Members();
                init_17_0_5_edc0357_1345510114162_717249_14350Collections();
                init_17_0_5_edc0357_1345510114162_717249_14350Elaborations();
            }

            public _17_0_5_edc0357_1345510114162_717249_14350(Expression<Integer> _17_0_5_edc0357_1348188047453_317575_14999_endTime) {
                super();
                init_17_0_5_edc0357_1345510114162_717249_14350Members();
                init_17_0_5_edc0357_1345510114162_717249_14350Collections();
                addDependency(this._17_0_5_edc0357_1348188047453_317575_14999_endTime, _17_0_5_edc0357_1348188047453_317575_14999_endTime);
                init_17_0_5_edc0357_1345510114162_717249_14350Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114163_254081_14351 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1348187257705_321405_14980 = null;

            public BooleanParameter _17_0_5_edc0357_1345510114163_833189_14352_exists = null;

            public IntegerParameter _17_0_5_edc0357_1345510114159_726624_14346_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1345510114160_29879_14347_exists = null;

            public ConstraintExpression constraint119 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1348187257705_321405_14980Dependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114163_833189_14352_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114160_29879_14347_existsDependency = null;

            public ElaborationRule elaborationRule120 = null;

            public ElaborationRule elaborationRule121 = null;

            public void init_17_0_5_edc0357_1345510114163_254081_14351Members() {
                try {
                    _17_0_5_edc0357_1348187257705_321405_14980 = new BooleanParameter("_17_0_5_edc0357_1348187257705_321405_14980", this);
                    _17_0_5_edc0357_1345510114163_833189_14352_exists = new BooleanParameter("_17_0_5_edc0357_1345510114163_833189_14352_exists", this);
                    _17_0_5_edc0357_1345510114159_726624_14346_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114159_726624_14346_endTime", this);
                    _17_0_5_edc0357_1345510114160_29879_14347_exists = new BooleanParameter("_17_0_5_edc0357_1345510114160_29879_14347_exists", this);
                    constraint119 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114159_726624_14346_endTime)));
                    _17_0_5_edc0357_1348187257705_321405_14980Dependency = new Dependency<Boolean>(_17_0_5_edc0357_1348187257705_321405_14980, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1348187257705_321405_14980, Utils.getMethodForArgTypes("ObjectFlow<Boolean>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1345510114163_833189_14352_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114163_833189_14352_exists, new Functions.And(new Functions.Equals(new Expression<Boolean>(_17_0_5_edc0357_1348187257705_321405_14980), new Expression<Boolean>(false)), new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114168_746941_14366, Utils.getMethodForArgTypes("ObjectFlow<Customer>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) }))));
                    _17_0_5_edc0357_1345510114160_29879_14347_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114160_29879_14347_exists, new Functions.And(new Functions.And(new Functions.Equals(new Expression<Boolean>(_17_0_5_edc0357_1348187257705_321405_14980), new Expression<Boolean>(true)), new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114167_732692_14364, Utils.getMethodForArgTypes("ObjectFlow<Customer>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) }))), new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114167_567578_14359, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) }))));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114163_254081_14351Collections() {
                parameters.add(_17_0_5_edc0357_1348187257705_321405_14980);
                parameters.add(_17_0_5_edc0357_1345510114163_833189_14352_exists);
                parameters.add(_17_0_5_edc0357_1345510114159_726624_14346_endTime);
                parameters.add(_17_0_5_edc0357_1345510114160_29879_14347_exists);
                constraintExpressions.add(constraint119);
                dependencies.add(_17_0_5_edc0357_1348187257705_321405_14980Dependency);
                dependencies.add(_17_0_5_edc0357_1345510114163_833189_14352_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510114160_29879_14347_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510114163_254081_14351Elaborations() {
                Expression<?>[] arguments120 = new Expression<?>[1];
                arguments120[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition120 = new Expression<Boolean>(_17_0_5_edc0357_1345510114160_29879_14347_exists);
                elaborationRule120 = addElaborationRule(condition120, _17_0_5_edc0357_1345510113560_522513_13778.this, Customer._17_0_5_edc0357_1345510113560_522513_13778._17_0_5_edc0357_1345510114160_29879_14347.class, "addStructuralFeatureCap_AddStructuralFeatureValueAction_setDRCap", arguments120);
                Expression<?>[] arguments121 = new Expression<?>[1];
                arguments121[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition121 = new Expression<Boolean>(_17_0_5_edc0357_1345510114163_833189_14352_exists);
                elaborationRule121 = addElaborationRule(condition121, _17_0_5_edc0357_1345510113560_522513_13778.this, Customer._17_0_5_edc0357_1345510113560_522513_13778._17_0_5_edc0357_1345510114163_833189_14352.class, "sendSignalNo_SendSignalAction_setDRCap", arguments121);
            }

            public _17_0_5_edc0357_1345510114163_254081_14351() {
                super();
                init_17_0_5_edc0357_1345510114163_254081_14351Members();
                init_17_0_5_edc0357_1345510114163_254081_14351Collections();
                init_17_0_5_edc0357_1345510114163_254081_14351Elaborations();
            }

            public _17_0_5_edc0357_1345510114163_254081_14351(Expression<Integer> _17_0_5_edc0357_1345510114159_726624_14346_endTime) {
                super();
                init_17_0_5_edc0357_1345510114163_254081_14351Members();
                init_17_0_5_edc0357_1345510114163_254081_14351Collections();
                addDependency(this._17_0_5_edc0357_1345510114159_726624_14346_endTime, _17_0_5_edc0357_1345510114159_726624_14346_endTime);
                init_17_0_5_edc0357_1345510114163_254081_14351Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114163_833189_14352 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1348188047453_317575_14999_exists = null;

            public Parameter< Customer > _17_0_5_edc0357_1345510114820_809313_15172 = null;

            public IntegerParameter _17_0_5_edc0357_1345510114163_254081_14351_endTime = null;

            public ConstraintExpression constraint122 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1348188047453_317575_14999_existsDependency = null;

            public Dependency< Customer > _17_0_5_edc0357_1345510114820_809313_15172Dependency = null;

            public Effect effect123 = null;

            public Object effect123Var = null;

            public ElaborationRule elaborationRule124 = null;

            public void init_17_0_5_edc0357_1345510114163_833189_14352Members() {
                try {
                    _17_0_5_edc0357_1348188047453_317575_14999_exists = new BooleanParameter("_17_0_5_edc0357_1348188047453_317575_14999_exists", this);
                    _17_0_5_edc0357_1345510114820_809313_15172 = new Parameter<Customer>("_17_0_5_edc0357_1345510114820_809313_15172", null, null, this);
                    _17_0_5_edc0357_1345510114163_254081_14351_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114163_254081_14351_endTime", this);
                    constraint122 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114163_254081_14351_endTime)));
                    _17_0_5_edc0357_1348188047453_317575_14999_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1348188047453_317575_14999_exists, new Expression<Boolean>(true));
                    _17_0_5_edc0357_1345510114820_809313_15172Dependency = new Dependency<Customer>(_17_0_5_edc0357_1345510114820_809313_15172, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114168_746941_14366, Utils.getMethodForArgTypes("ObjectFlow<Customer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    effect123Var = (((Parameter<?>) (x.getMember("ss_17_0_5_edc0357_1345510113564_305135_13782_no"))).getValue());
                    effect123 = new EffectFunction(new FunctionCall((Object) effect123Var, Utils.getMethodForArgTypes("Customer._17_0_5_edc0357_1345510113560_522513_13778._17_0_5_edc0357_1345510114163_833189_14352", "generated", "send"), new Object[] { x.getValue().new Signalno(), endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114163_833189_14352Collections() {
                parameters.add(_17_0_5_edc0357_1348188047453_317575_14999_exists);
                parameters.add(_17_0_5_edc0357_1345510114820_809313_15172);
                parameters.add(_17_0_5_edc0357_1345510114163_254081_14351_endTime);
                constraintExpressions.add(constraint122);
                dependencies.add(_17_0_5_edc0357_1348188047453_317575_14999_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510114820_809313_15172Dependency);
                Set<Effect> effectsForeffect123Var = new HashSet<Effect>();
                effectsForeffect123Var.add(effect123);
                effects.put((Parameter<?>) effect123Var, effectsForeffect123Var);
            }

            public void init_17_0_5_edc0357_1345510114163_833189_14352Elaborations() {
                Expression<?>[] arguments124 = new Expression<?>[1];
                arguments124[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition124 = new Expression<Boolean>(_17_0_5_edc0357_1348188047453_317575_14999_exists);
                elaborationRule124 = addElaborationRule(condition124, _17_0_5_edc0357_1345510113560_522513_13778.this, Customer._17_0_5_edc0357_1345510113560_522513_13778._17_0_5_edc0357_1348188047453_317575_14999.class, "_MergeNode_setDRCap", arguments124);
            }

            public _17_0_5_edc0357_1345510114163_833189_14352() {
                super();
                init_17_0_5_edc0357_1345510114163_833189_14352Members();
                init_17_0_5_edc0357_1345510114163_833189_14352Collections();
                init_17_0_5_edc0357_1345510114163_833189_14352Elaborations();
            }

            public _17_0_5_edc0357_1345510114163_833189_14352(Expression<Integer> _17_0_5_edc0357_1345510114163_254081_14351_endTime) {
                super();
                init_17_0_5_edc0357_1345510114163_833189_14352Members();
                init_17_0_5_edc0357_1345510114163_833189_14352Collections();
                addDependency(this._17_0_5_edc0357_1345510114163_254081_14351_endTime, _17_0_5_edc0357_1345510114163_254081_14351_endTime);
                init_17_0_5_edc0357_1345510114163_833189_14352Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114164_830320_14353 extends DurativeEvent {

            public IntegerParameter objectToPass = null;

            public IntegerParameter _17_0_5_edc0357_1346105164389_404592_15285_endTime = null;

            public ConstraintExpression constraint125 = null;

            public Effect effect126 = null;

            public Object effect126Var = null;

            public Effect effect127 = null;

            public Object effect127Var = null;

            public void init_17_0_5_edc0357_1345510114164_830320_14353Members() {
                try {
                    objectToPass = new IntegerParameter("objectToPass", this);
                    _17_0_5_edc0357_1346105164389_404592_15285_endTime = new IntegerParameter("_17_0_5_edc0357_1346105164389_404592_15285_endTime", this);
                    constraint125 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346105164389_404592_15285_endTime)));
                    effect126Var = sig_17_0_5_edc0357_1345510114167_567578_14359;
                    effect126 = new EffectFunction(new FunctionCall((Object) effect126Var, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                    effect127Var = sig_17_0_5_edc0357_1345510114167_676201_14362;
                    effect127 = new EffectFunction(new FunctionCall((Object) effect127Var, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114164_830320_14353Collections() {
                parameters.add(objectToPass);
                parameters.add(_17_0_5_edc0357_1346105164389_404592_15285_endTime);
                constraintExpressions.add(constraint125);
                Set<Effect> effectsForeffect126Var = new HashSet<Effect>();
                effectsForeffect126Var.add(effect126);
                effects.put((Parameter<?>) effect126Var, effectsForeffect126Var);
                Set<Effect> effectsForeffect127Var = new HashSet<Effect>();
                effectsForeffect127Var.add(effect127);
                effects.put((Parameter<?>) effect127Var, effectsForeffect127Var);
            }

            public void init_17_0_5_edc0357_1345510114164_830320_14353Elaborations() {
            }

            public _17_0_5_edc0357_1345510114164_830320_14353() {
                super();
                init_17_0_5_edc0357_1345510114164_830320_14353Members();
                init_17_0_5_edc0357_1345510114164_830320_14353Collections();
                init_17_0_5_edc0357_1345510114164_830320_14353Elaborations();
            }

            public _17_0_5_edc0357_1345510114164_830320_14353(Expression<Integer> _17_0_5_edc0357_1346105164389_404592_15285_endTime, Expression<Integer> objectToPass) {
                super();
                init_17_0_5_edc0357_1345510114164_830320_14353Members();
                init_17_0_5_edc0357_1345510114164_830320_14353Collections();
                addDependency(this._17_0_5_edc0357_1346105164389_404592_15285_endTime, _17_0_5_edc0357_1346105164389_404592_15285_endTime);
                addDependency(this.objectToPass, objectToPass);
                init_17_0_5_edc0357_1345510114164_830320_14353Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114165_641732_14354 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1345510114160_29879_14347_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1348188047453_317575_14999_exists = null;

            public IntegerParameter _17_0_5_edc0357_1345510114822_185942_15174 = null;

            public Parameter< Customer > _17_0_5_edc0357_1345510114821_349321_15173 = null;

            public ConstraintExpression constraint128 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1348188047453_317575_14999_existsDependency = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114822_185942_15174Dependency = null;

            public Dependency< Customer > _17_0_5_edc0357_1345510114821_349321_15173Dependency = null;

            public Effect effect129 = null;

            public Object effect129Var = null;

            public ElaborationRule elaborationRule130 = null;

            public void init_17_0_5_edc0357_1345510114165_641732_14354Members() {
                try {
                    _17_0_5_edc0357_1345510114160_29879_14347_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114160_29879_14347_endTime", this);
                    _17_0_5_edc0357_1348188047453_317575_14999_exists = new BooleanParameter("_17_0_5_edc0357_1348188047453_317575_14999_exists", this);
                    _17_0_5_edc0357_1345510114822_185942_15174 = new IntegerParameter("_17_0_5_edc0357_1345510114822_185942_15174", this);
                    _17_0_5_edc0357_1345510114821_349321_15173 = new Parameter<Customer>("_17_0_5_edc0357_1345510114821_349321_15173", null, null, this);
                    constraint128 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114160_29879_14347_endTime)));
                    _17_0_5_edc0357_1348188047453_317575_14999_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1348188047453_317575_14999_exists, new Expression<Boolean>(true));
                    _17_0_5_edc0357_1345510114822_185942_15174Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114822_185942_15174, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114167_676201_14362, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1345510114821_349321_15173Dependency = new Dependency<Customer>(_17_0_5_edc0357_1345510114821_349321_15173, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114168_147606_14365, Utils.getMethodForArgTypes("ObjectFlow<Customer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    effect129Var = (((Parameter<?>) (x.getMember("ss_17_0_5_edc0357_1345510113564_305135_13782_yes"))).getValue());
                    effect129 = new EffectFunction(new FunctionCall((Object) effect129Var, Utils.getMethodForArgTypes("Customer._17_0_5_edc0357_1345510113560_522513_13778._17_0_5_edc0357_1345510114165_641732_14354", "generated", "send"), new Object[] { x.getValue().new Signalyes(_17_0_5_edc0357_1345510114822_185942_15174.getValue()), endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114165_641732_14354Collections() {
                parameters.add(_17_0_5_edc0357_1345510114160_29879_14347_endTime);
                parameters.add(_17_0_5_edc0357_1348188047453_317575_14999_exists);
                parameters.add(_17_0_5_edc0357_1345510114822_185942_15174);
                parameters.add(_17_0_5_edc0357_1345510114821_349321_15173);
                constraintExpressions.add(constraint128);
                dependencies.add(_17_0_5_edc0357_1348188047453_317575_14999_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510114822_185942_15174Dependency);
                dependencies.add(_17_0_5_edc0357_1345510114821_349321_15173Dependency);
                Set<Effect> effectsForeffect129Var = new HashSet<Effect>();
                effectsForeffect129Var.add(effect129);
                effects.put((Parameter<?>) effect129Var, effectsForeffect129Var);
            }

            public void init_17_0_5_edc0357_1345510114165_641732_14354Elaborations() {
                Expression<?>[] arguments130 = new Expression<?>[1];
                arguments130[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition130 = new Expression<Boolean>(_17_0_5_edc0357_1348188047453_317575_14999_exists);
                elaborationRule130 = addElaborationRule(condition130, _17_0_5_edc0357_1345510113560_522513_13778.this, Customer._17_0_5_edc0357_1345510113560_522513_13778._17_0_5_edc0357_1348188047453_317575_14999.class, "_MergeNode_setDRCap", arguments130);
            }

            public _17_0_5_edc0357_1345510114165_641732_14354() {
                super();
                init_17_0_5_edc0357_1345510114165_641732_14354Members();
                init_17_0_5_edc0357_1345510114165_641732_14354Collections();
                init_17_0_5_edc0357_1345510114165_641732_14354Elaborations();
            }

            public _17_0_5_edc0357_1345510114165_641732_14354(Expression<Integer> _17_0_5_edc0357_1345510114160_29879_14347_endTime) {
                super();
                init_17_0_5_edc0357_1345510114165_641732_14354Members();
                init_17_0_5_edc0357_1345510114165_641732_14354Collections();
                addDependency(this._17_0_5_edc0357_1345510114160_29879_14347_endTime, _17_0_5_edc0357_1345510114160_29879_14347_endTime);
                init_17_0_5_edc0357_1345510114165_641732_14354Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114166_387809_14355 extends DurativeEvent {

            public Parameter< Customer > objectToPass = null;

            public IntegerParameter _17_0_5_edc0357_1345510114159_726624_14346_endTime = null;

            public ConstraintExpression constraint131 = null;

            public Effect effect132 = null;

            public Object effect132Var = null;

            public Effect effect133 = null;

            public Object effect133Var = null;

            public Effect effect134 = null;

            public Object effect134Var = null;

            public void init_17_0_5_edc0357_1345510114166_387809_14355Members() {
                try {
                    objectToPass = new Parameter<Customer>("objectToPass", null, null, this);
                    _17_0_5_edc0357_1345510114159_726624_14346_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114159_726624_14346_endTime", this);
                    constraint131 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114159_726624_14346_endTime)));
                    effect132Var = sig_17_0_5_edc0357_1345510114167_732692_14364;
                    effect132 = new EffectFunction(new FunctionCall((Object) effect132Var, Utils.getMethodForArgTypes("ObjectFlow<Customer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                    effect133Var = sig_17_0_5_edc0357_1345510114168_147606_14365;
                    effect133 = new EffectFunction(new FunctionCall((Object) effect133Var, Utils.getMethodForArgTypes("ObjectFlow<Customer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                    effect134Var = sig_17_0_5_edc0357_1345510114168_746941_14366;
                    effect134 = new EffectFunction(new FunctionCall((Object) effect134Var, Utils.getMethodForArgTypes("ObjectFlow<Customer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114166_387809_14355Collections() {
                parameters.add(objectToPass);
                parameters.add(_17_0_5_edc0357_1345510114159_726624_14346_endTime);
                constraintExpressions.add(constraint131);
                Set<Effect> effectsForeffect132Var = new HashSet<Effect>();
                effectsForeffect132Var.add(effect132);
                effects.put((Parameter<?>) effect132Var, effectsForeffect132Var);
                Set<Effect> effectsForeffect133Var = new HashSet<Effect>();
                effectsForeffect133Var.add(effect133);
                effects.put((Parameter<?>) effect133Var, effectsForeffect133Var);
                Set<Effect> effectsForeffect134Var = new HashSet<Effect>();
                effectsForeffect134Var.add(effect134);
                effects.put((Parameter<?>) effect134Var, effectsForeffect134Var);
            }

            public void init_17_0_5_edc0357_1345510114166_387809_14355Elaborations() {
            }

            public _17_0_5_edc0357_1345510114166_387809_14355() {
                super();
                init_17_0_5_edc0357_1345510114166_387809_14355Members();
                init_17_0_5_edc0357_1345510114166_387809_14355Collections();
                init_17_0_5_edc0357_1345510114166_387809_14355Elaborations();
            }

            public _17_0_5_edc0357_1345510114166_387809_14355(Expression<Integer> _17_0_5_edc0357_1345510114159_726624_14346_endTime, Expression<Customer> objectToPass) {
                super();
                init_17_0_5_edc0357_1345510114166_387809_14355Members();
                init_17_0_5_edc0357_1345510114166_387809_14355Collections();
                addDependency(this._17_0_5_edc0357_1345510114159_726624_14346_endTime, _17_0_5_edc0357_1345510114159_726624_14346_endTime);
                addDependency(this.objectToPass, objectToPass);
                init_17_0_5_edc0357_1345510114166_387809_14355Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1346105164389_404592_15285 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1346105157074_69873_15283 = null;

            public BooleanParameter _17_0_5_edc0357_1345510114164_830320_14353_exists = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114164_830320_14353_existsDependency = null;

            public ElaborationRule elaborationRule135 = null;

            public void init_17_0_5_edc0357_1346105164389_404592_15285Members() {
                try {
                    _17_0_5_edc0357_1346105157074_69873_15283 = new IntegerParameter("_17_0_5_edc0357_1346105157074_69873_15283", this);
                    _17_0_5_edc0357_1345510114164_830320_14353_exists = new BooleanParameter("_17_0_5_edc0357_1345510114164_830320_14353_exists", this);
                    _17_0_5_edc0357_1345510114164_830320_14353_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114164_830320_14353_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346105164389_404592_15285Collections() {
                parameters.add(_17_0_5_edc0357_1346105157074_69873_15283);
                parameters.add(_17_0_5_edc0357_1345510114164_830320_14353_exists);
                dependencies.add(_17_0_5_edc0357_1345510114164_830320_14353_existsDependency);
            }

            public void init_17_0_5_edc0357_1346105164389_404592_15285Elaborations() {
                Expression<?>[] arguments135 = new Expression<?>[2];
                arguments135[0] = new Expression<Integer>(endTime);
                arguments135[1] = new Expression<Integer>(_17_0_5_edc0357_1346105157074_69873_15283);
                Expression<Boolean> condition135 = new Expression<Boolean>(_17_0_5_edc0357_1345510114164_830320_14353_exists);
                elaborationRule135 = addElaborationRule(condition135, _17_0_5_edc0357_1345510113560_522513_13778.this, Customer._17_0_5_edc0357_1345510113560_522513_13778._17_0_5_edc0357_1345510114164_830320_14353.class, "forkNodeLastSignalValue_ForkNode_setDRCap", arguments135);
            }

            public _17_0_5_edc0357_1346105164389_404592_15285() {
                super();
                init_17_0_5_edc0357_1346105164389_404592_15285Members();
                init_17_0_5_edc0357_1346105164389_404592_15285Collections();
                init_17_0_5_edc0357_1346105164389_404592_15285Elaborations();
            }

            public _17_0_5_edc0357_1346105164389_404592_15285(Expression<Integer> startTime, Expression<Integer> _17_0_5_edc0357_1346105157074_69873_15283) {
                super();
                init_17_0_5_edc0357_1346105164389_404592_15285Members();
                init_17_0_5_edc0357_1346105164389_404592_15285Collections();
                removeDependency(this.startTime);
                addDependency(this.startTime, startTime);
                addDependency(this._17_0_5_edc0357_1346105157074_69873_15283, _17_0_5_edc0357_1346105157074_69873_15283);
                init_17_0_5_edc0357_1346105164389_404592_15285Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1348187213310_199425_14961 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1348187213310_584836_14962 = null;

            public IntegerParameter _17_0_5_edc0357_1348187326552_305667_14984_endTime = null;

            public ConstraintExpression constraint136 = null;

            public Effect effect137 = null;

            public Object effect137Var = null;

            public void init_17_0_5_edc0357_1348187213310_199425_14961Members() {
                try {
                    _17_0_5_edc0357_1348187213310_584836_14962 = new BooleanParameter("_17_0_5_edc0357_1348187213310_584836_14962", this);
                    _17_0_5_edc0357_1348187326552_305667_14984_endTime = new IntegerParameter("_17_0_5_edc0357_1348187326552_305667_14984_endTime", this);
                    constraint136 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1348187326552_305667_14984_endTime)));
                    effect137Var = sig_17_0_5_edc0357_1348187257705_321405_14980;
                    effect137 = new EffectFunction(new FunctionCall((Object) effect137Var, Utils.getMethodForArgTypes("ObjectFlow<Boolean>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { _17_0_5_edc0357_1348187213310_584836_14962, startTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1348187213310_199425_14961Collections() {
                parameters.add(_17_0_5_edc0357_1348187213310_584836_14962);
                parameters.add(_17_0_5_edc0357_1348187326552_305667_14984_endTime);
                constraintExpressions.add(constraint136);
                Set<Effect> effectsForeffect137Var = new HashSet<Effect>();
                effectsForeffect137Var.add(effect137);
                effects.put((Parameter<?>) effect137Var, effectsForeffect137Var);
            }

            public void init_17_0_5_edc0357_1348187213310_199425_14961Elaborations() {
            }

            public _17_0_5_edc0357_1348187213310_199425_14961() {
                super();
                init_17_0_5_edc0357_1348187213310_199425_14961Members();
                init_17_0_5_edc0357_1348187213310_199425_14961Collections();
                init_17_0_5_edc0357_1348187213310_199425_14961Elaborations();
            }

            public _17_0_5_edc0357_1348187213310_199425_14961(Expression<Integer> _17_0_5_edc0357_1348187326552_305667_14984_endTime) {
                super();
                init_17_0_5_edc0357_1348187213310_199425_14961Members();
                init_17_0_5_edc0357_1348187213310_199425_14961Collections();
                addDependency(this._17_0_5_edc0357_1348187326552_305667_14984_endTime, _17_0_5_edc0357_1348187326552_305667_14984_endTime);
                init_17_0_5_edc0357_1348187213310_199425_14961Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1348187326552_305667_14984 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1345510114159_726624_14346_exists = null;

            public IntegerParameter _17_0_5_edc0357_1345510114161_34701_14349_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1348187213310_199425_14961_exists = null;

            public ConstraintExpression constraint138 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114159_726624_14346_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1348187213310_199425_14961_existsDependency = null;

            public ElaborationRule elaborationRule139 = null;

            public ElaborationRule elaborationRule140 = null;

            public void init_17_0_5_edc0357_1348187326552_305667_14984Members() {
                try {
                    _17_0_5_edc0357_1345510114159_726624_14346_exists = new BooleanParameter("_17_0_5_edc0357_1345510114159_726624_14346_exists", this);
                    _17_0_5_edc0357_1345510114161_34701_14349_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114161_34701_14349_endTime", this);
                    _17_0_5_edc0357_1348187213310_199425_14961_exists = new BooleanParameter("_17_0_5_edc0357_1348187213310_199425_14961_exists", this);
                    constraint138 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114161_34701_14349_endTime)));
                    _17_0_5_edc0357_1345510114159_726624_14346_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114159_726624_14346_exists, new Expression<Boolean>(true));
                    _17_0_5_edc0357_1348187213310_199425_14961_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1348187213310_199425_14961_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1348187326552_305667_14984Collections() {
                parameters.add(_17_0_5_edc0357_1345510114159_726624_14346_exists);
                parameters.add(_17_0_5_edc0357_1345510114161_34701_14349_endTime);
                parameters.add(_17_0_5_edc0357_1348187213310_199425_14961_exists);
                constraintExpressions.add(constraint138);
                dependencies.add(_17_0_5_edc0357_1345510114159_726624_14346_existsDependency);
                dependencies.add(_17_0_5_edc0357_1348187213310_199425_14961_existsDependency);
            }

            public void init_17_0_5_edc0357_1348187326552_305667_14984Elaborations() {
                Expression<?>[] arguments139 = new Expression<?>[1];
                arguments139[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition139 = new Expression<Boolean>(_17_0_5_edc0357_1345510114159_726624_14346_exists);
                elaborationRule139 = addElaborationRule(condition139, _17_0_5_edc0357_1345510113560_522513_13778.this, Customer._17_0_5_edc0357_1345510113560_522513_13778._17_0_5_edc0357_1345510114159_726624_14346.class, "customerReadSelf_ReadSelfAction_setDRCap", arguments139);
                Expression<?>[] arguments140 = new Expression<?>[1];
                arguments140[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition140 = new Expression<Boolean>(_17_0_5_edc0357_1348187213310_199425_14961_exists);
                elaborationRule140 = addElaborationRule(condition140, _17_0_5_edc0357_1345510113560_522513_13778.this, Customer._17_0_5_edc0357_1345510113560_522513_13778._17_0_5_edc0357_1348187213310_199425_14961.class, "custParticip_ValueSpecificationAction_setDRCap", arguments140);
            }

            public _17_0_5_edc0357_1348187326552_305667_14984() {
                super();
                init_17_0_5_edc0357_1348187326552_305667_14984Members();
                init_17_0_5_edc0357_1348187326552_305667_14984Collections();
                init_17_0_5_edc0357_1348187326552_305667_14984Elaborations();
            }

            public _17_0_5_edc0357_1348187326552_305667_14984(Expression<Integer> _17_0_5_edc0357_1345510114161_34701_14349_endTime) {
                super();
                init_17_0_5_edc0357_1348187326552_305667_14984Members();
                init_17_0_5_edc0357_1348187326552_305667_14984Collections();
                addDependency(this._17_0_5_edc0357_1345510114161_34701_14349_endTime, _17_0_5_edc0357_1345510114161_34701_14349_endTime);
                init_17_0_5_edc0357_1348187326552_305667_14984Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1348188047453_317575_14999 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1345510114162_717249_14350_exists = null;

            public IntegerParameter invoker_endTime = null;

            public ConstraintExpression constraint141 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114162_717249_14350_existsDependency = null;

            public ElaborationRule elaborationRule142 = null;

            public void init_17_0_5_edc0357_1348188047453_317575_14999Members() {
                try {
                    _17_0_5_edc0357_1345510114162_717249_14350_exists = new BooleanParameter("_17_0_5_edc0357_1345510114162_717249_14350_exists", this);
                    invoker_endTime = new IntegerParameter("invoker_endTime", this);
                    constraint141 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(invoker_endTime)));
                    _17_0_5_edc0357_1345510114162_717249_14350_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114162_717249_14350_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1348188047453_317575_14999Collections() {
                parameters.add(_17_0_5_edc0357_1345510114162_717249_14350_exists);
                parameters.add(invoker_endTime);
                constraintExpressions.add(constraint141);
                dependencies.add(_17_0_5_edc0357_1345510114162_717249_14350_existsDependency);
            }

            public void init_17_0_5_edc0357_1348188047453_317575_14999Elaborations() {
                Expression<?>[] arguments142 = new Expression<?>[1];
                arguments142[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition142 = new Expression<Boolean>(_17_0_5_edc0357_1345510114162_717249_14350_exists);
                elaborationRule142 = addElaborationRule(condition142, _17_0_5_edc0357_1345510113560_522513_13778.this, Customer._17_0_5_edc0357_1345510113560_522513_13778._17_0_5_edc0357_1345510114162_717249_14350.class, "customerFinal_ActivityFinalNode_setDRCap", arguments142);
            }

            public _17_0_5_edc0357_1348188047453_317575_14999() {
                super();
                init_17_0_5_edc0357_1348188047453_317575_14999Members();
                init_17_0_5_edc0357_1348188047453_317575_14999Collections();
                init_17_0_5_edc0357_1348188047453_317575_14999Elaborations();
            }

            public _17_0_5_edc0357_1348188047453_317575_14999(Expression<Integer> invoker_endTime) {
                super();
                init_17_0_5_edc0357_1348188047453_317575_14999Members();
                init_17_0_5_edc0357_1348188047453_317575_14999Collections();
                addDependency(this.invoker_endTime, invoker_endTime);
                init_17_0_5_edc0357_1348188047453_317575_14999Elaborations();
                fixTimeDependencies();
            }
        }

        public _17_0_5_edc0357_1345510113560_522513_13778(Expression<Integer> endTime, Expression<Integer> _17_0_5_edc0357_1346105157074_69873_15283) {
            super();
            init_17_0_5_edc0357_1345510113560_522513_13778Members();
            init_17_0_5_edc0357_1345510113560_522513_13778Collections();
            removeDependency(this.endTime);
            addDependency(this.endTime, endTime);
            addDependency(this._17_0_5_edc0357_1346105157074_69873_15283, _17_0_5_edc0357_1346105157074_69873_15283);
            init_17_0_5_edc0357_1345510113560_522513_13778Elaborations();
            fixTimeDependencies();
        }
    }

    public class _17_0_5_edc0357_1346104706708_917454_14957 extends DurativeEvent {

        public Parameter< ObjectFlow<Power_System.Signaldr_request> > sig_17_0_5_edc0357_1345510113323_942738_13620 = null;

        public IntegerParameter invoke_time = null;

        public ElaborationRule elaborationRule143 = null;

        public void init_17_0_5_edc0357_1346104706708_917454_14957Members() {
            try {
                sig_17_0_5_edc0357_1345510113323_942738_13620 = new Parameter<ObjectFlow<Power_System.Signaldr_request>>("sig_17_0_5_edc0357_1345510113323_942738_13620", null, new ObjectFlow("sig_17_0_5_edc0357_1345510113323_942738_13620"), this);
                invoke_time = new IntegerParameter("invoke_time", this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void init_17_0_5_edc0357_1346104706708_917454_14957Collections() {
            parameters.add(sig_17_0_5_edc0357_1345510113323_942738_13620);
            parameters.add(invoke_time);
        }

        public void init_17_0_5_edc0357_1346104706708_917454_14957Elaborations() {
            Expression<?>[] arguments143 = new Expression<?>[1];
            arguments143[0] = new Expression<Integer>(invoke_time);
            Expression<Boolean> condition143 = new Expression<Boolean>(true);
            elaborationRule143 = addElaborationRule(condition143, _17_0_5_edc0357_1346104706708_917454_14957.this, Customer._17_0_5_edc0357_1346104706708_917454_14957._17_0_5_edc0357_1346104724327_278485_14979.class, "_InitialNode_CustomerCB", arguments143);
        }

        public _17_0_5_edc0357_1346104706708_917454_14957() {
            super();
            init_17_0_5_edc0357_1346104706708_917454_14957Members();
            init_17_0_5_edc0357_1346104706708_917454_14957Collections();
            init_17_0_5_edc0357_1346104706708_917454_14957Elaborations();
        }

        public class _17_0_5_edc0357_1346104724327_278485_14979 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1346104727721_22566_14990_exists = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346104727721_22566_14990_existsDependency = null;

            public ElaborationRule elaborationRule144 = null;

            public void init_17_0_5_edc0357_1346104724327_278485_14979Members() {
                try {
                    _17_0_5_edc0357_1346104727721_22566_14990_exists = new BooleanParameter("_17_0_5_edc0357_1346104727721_22566_14990_exists", this);
                    _17_0_5_edc0357_1346104727721_22566_14990_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346104727721_22566_14990_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346104724327_278485_14979Collections() {
                parameters.add(_17_0_5_edc0357_1346104727721_22566_14990_exists);
                dependencies.add(_17_0_5_edc0357_1346104727721_22566_14990_existsDependency);
            }

            public void init_17_0_5_edc0357_1346104724327_278485_14979Elaborations() {
                Expression<?>[] arguments144 = new Expression<?>[1];
                arguments144[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition144 = new Expression<Boolean>(_17_0_5_edc0357_1346104727721_22566_14990_exists);
                elaborationRule144 = addElaborationRule(condition144, _17_0_5_edc0357_1346104706708_917454_14957.this, Customer._17_0_5_edc0357_1346104706708_917454_14957._17_0_5_edc0357_1346104727721_22566_14990.class, "_CallBehaviorAction_CustomerCB", arguments144);
            }

            public _17_0_5_edc0357_1346104724327_278485_14979() {
                super();
                init_17_0_5_edc0357_1346104724327_278485_14979Members();
                init_17_0_5_edc0357_1346104724327_278485_14979Collections();
                init_17_0_5_edc0357_1346104724327_278485_14979Elaborations();
            }

            public _17_0_5_edc0357_1346104724327_278485_14979(Expression<Integer> startTime) {
                super();
                init_17_0_5_edc0357_1346104724327_278485_14979Members();
                init_17_0_5_edc0357_1346104724327_278485_14979Collections();
                removeDependency(this.startTime);
                addDependency(this.startTime, startTime);
                init_17_0_5_edc0357_1346104724327_278485_14979Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1346104727721_22566_14990 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1348000884142_707118_14045_exists = null;

            public IntegerParameter _17_0_5_edc0357_1346104724327_278485_14979_endTime = null;

            public IntegerParameter cba_endTime = null;

            public ConstraintExpression constraint145 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1348000884142_707118_14045_existsDependency = null;

            public ElaborationRule elaborationRule146 = null;

            public ElaborationRule elaborationRule147 = null;

            public void init_17_0_5_edc0357_1346104727721_22566_14990Members() {
                try {
                    _17_0_5_edc0357_1348000884142_707118_14045_exists = new BooleanParameter("_17_0_5_edc0357_1348000884142_707118_14045_exists", this);
                    _17_0_5_edc0357_1346104724327_278485_14979_endTime = new IntegerParameter("_17_0_5_edc0357_1346104724327_278485_14979_endTime", this);
                    cba_endTime = new IntegerParameter("cba_endTime", this);
                    constraint145 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346104724327_278485_14979_endTime)));
                    _17_0_5_edc0357_1348000884142_707118_14045_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1348000884142_707118_14045_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346104727721_22566_14990Collections() {
                parameters.add(_17_0_5_edc0357_1348000884142_707118_14045_exists);
                parameters.add(_17_0_5_edc0357_1346104724327_278485_14979_endTime);
                parameters.add(cba_endTime);
                constraintExpressions.add(constraint145);
                dependencies.add(_17_0_5_edc0357_1348000884142_707118_14045_existsDependency);
            }

            public void init_17_0_5_edc0357_1346104727721_22566_14990Elaborations() {
                Expression<?>[] arguments146 = new Expression<?>[1];
                arguments146[0] = new Expression<Integer>(cba_endTime);
                Expression<Boolean> condition146 = new Expression<Boolean>(true);
                elaborationRule146 = addElaborationRule(condition146, Customer.this, Customer._17_0_5_edc0357_1345510113559_180117_13777.class, "initialize_Activity_Customer", arguments146);
                Expression<?>[] arguments147 = new Expression<?>[1];
                arguments147[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition147 = new Expression<Boolean>(_17_0_5_edc0357_1348000884142_707118_14045_exists);
                elaborationRule147 = addElaborationRule(condition147, _17_0_5_edc0357_1346104706708_917454_14957.this, Customer._17_0_5_edc0357_1346104706708_917454_14957._17_0_5_edc0357_1348000884142_707118_14045.class, "_AcceptEventAction_CustomerCB", arguments147);
            }

            public _17_0_5_edc0357_1346104727721_22566_14990() {
                super();
                init_17_0_5_edc0357_1346104727721_22566_14990Members();
                init_17_0_5_edc0357_1346104727721_22566_14990Collections();
                init_17_0_5_edc0357_1346104727721_22566_14990Elaborations();
            }

            public _17_0_5_edc0357_1346104727721_22566_14990(Expression<Integer> _17_0_5_edc0357_1346104724327_278485_14979_endTime) {
                super();
                init_17_0_5_edc0357_1346104727721_22566_14990Members();
                init_17_0_5_edc0357_1346104727721_22566_14990Collections();
                addDependency(this._17_0_5_edc0357_1346104724327_278485_14979_endTime, _17_0_5_edc0357_1346104724327_278485_14979_endTime);
                init_17_0_5_edc0357_1346104727721_22566_14990Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1346104764223_34184_15024 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1346105173460_397488_15297_exists = null;

            public BooleanParameter _17_0_5_edc0357_1346104908659_55389_15177_exists = null;

            public BooleanParameter _17_0_5_edc0357_1348163169744_444878_14578_exists = null;

            public BooleanParameter _17_0_5_edc0357_1346105076614_639562_15240_exists = null;

            public IntegerParameter _17_0_5_edc0357_1348000884142_707118_14045_endTime = null;

            public ConstraintExpression constraint148 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346105173460_397488_15297_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346104908659_55389_15177_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1348163169744_444878_14578_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346105076614_639562_15240_existsDependency = null;

            public ElaborationRule elaborationRule149 = null;

            public ElaborationRule elaborationRule150 = null;

            public ElaborationRule elaborationRule151 = null;

            public ElaborationRule elaborationRule152 = null;

            public void init_17_0_5_edc0357_1346104764223_34184_15024Members() {
                try {
                    _17_0_5_edc0357_1346105173460_397488_15297_exists = new BooleanParameter("_17_0_5_edc0357_1346105173460_397488_15297_exists", this);
                    _17_0_5_edc0357_1346104908659_55389_15177_exists = new BooleanParameter("_17_0_5_edc0357_1346104908659_55389_15177_exists", this);
                    _17_0_5_edc0357_1348163169744_444878_14578_exists = new BooleanParameter("_17_0_5_edc0357_1348163169744_444878_14578_exists", this);
                    _17_0_5_edc0357_1346105076614_639562_15240_exists = new BooleanParameter("_17_0_5_edc0357_1346105076614_639562_15240_exists", this);
                    _17_0_5_edc0357_1348000884142_707118_14045_endTime = new IntegerParameter("_17_0_5_edc0357_1348000884142_707118_14045_endTime", this);
                    constraint148 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1348000884142_707118_14045_endTime)));
                    _17_0_5_edc0357_1346105173460_397488_15297_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346105173460_397488_15297_exists, new Expression<Boolean>(true));
                    _17_0_5_edc0357_1346104908659_55389_15177_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346104908659_55389_15177_exists, new Expression<Boolean>(true));
                    _17_0_5_edc0357_1348163169744_444878_14578_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1348163169744_444878_14578_exists, new Expression<Boolean>(true));
                    _17_0_5_edc0357_1346105076614_639562_15240_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346105076614_639562_15240_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346104764223_34184_15024Collections() {
                parameters.add(_17_0_5_edc0357_1346105173460_397488_15297_exists);
                parameters.add(_17_0_5_edc0357_1346104908659_55389_15177_exists);
                parameters.add(_17_0_5_edc0357_1348163169744_444878_14578_exists);
                parameters.add(_17_0_5_edc0357_1346105076614_639562_15240_exists);
                parameters.add(_17_0_5_edc0357_1348000884142_707118_14045_endTime);
                constraintExpressions.add(constraint148);
                dependencies.add(_17_0_5_edc0357_1346105173460_397488_15297_existsDependency);
                dependencies.add(_17_0_5_edc0357_1346104908659_55389_15177_existsDependency);
                dependencies.add(_17_0_5_edc0357_1348163169744_444878_14578_existsDependency);
                dependencies.add(_17_0_5_edc0357_1346105076614_639562_15240_existsDependency);
            }

            public void init_17_0_5_edc0357_1346104764223_34184_15024Elaborations() {
                Expression<?>[] arguments149 = new Expression<?>[1];
                arguments149[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition149 = new Expression<Boolean>(_17_0_5_edc0357_1346105076614_639562_15240_exists);
                elaborationRule149 = addElaborationRule(condition149, _17_0_5_edc0357_1346104706708_917454_14957.this, Customer._17_0_5_edc0357_1346104706708_917454_14957._17_0_5_edc0357_1346105076614_639562_15240.class, "_MergeNode_CustomerCB", arguments149);
                Expression<?>[] arguments150 = new Expression<?>[1];
                arguments150[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition150 = new Expression<Boolean>(_17_0_5_edc0357_1348163169744_444878_14578_exists);
                elaborationRule150 = addElaborationRule(condition150, _17_0_5_edc0357_1346104706708_917454_14957.this, Customer._17_0_5_edc0357_1346104706708_917454_14957._17_0_5_edc0357_1348163169744_444878_14578.class, "_AcceptEventAction_CustomerCB", arguments150);
                Expression<?>[] arguments151 = new Expression<?>[1];
                arguments151[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition151 = new Expression<Boolean>(_17_0_5_edc0357_1346105173460_397488_15297_exists);
                elaborationRule151 = addElaborationRule(condition151, _17_0_5_edc0357_1346104706708_917454_14957.this, Customer._17_0_5_edc0357_1346104706708_917454_14957._17_0_5_edc0357_1346105173460_397488_15297.class, "_MergeNode_CustomerCB", arguments151);
                Expression<?>[] arguments152 = new Expression<?>[1];
                arguments152[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition152 = new Expression<Boolean>(_17_0_5_edc0357_1346104908659_55389_15177_exists);
                elaborationRule152 = addElaborationRule(condition152, _17_0_5_edc0357_1346104706708_917454_14957.this, Customer._17_0_5_edc0357_1346104706708_917454_14957._17_0_5_edc0357_1346104908659_55389_15177.class, "_MergeNode_CustomerCB", arguments152);
            }

            public _17_0_5_edc0357_1346104764223_34184_15024() {
                super();
                init_17_0_5_edc0357_1346104764223_34184_15024Members();
                init_17_0_5_edc0357_1346104764223_34184_15024Collections();
                init_17_0_5_edc0357_1346104764223_34184_15024Elaborations();
            }

            public _17_0_5_edc0357_1346104764223_34184_15024(Expression<Integer> _17_0_5_edc0357_1348000884142_707118_14045_endTime) {
                super();
                init_17_0_5_edc0357_1346104764223_34184_15024Members();
                init_17_0_5_edc0357_1346104764223_34184_15024Collections();
                addDependency(this._17_0_5_edc0357_1348000884142_707118_14045_endTime, _17_0_5_edc0357_1348000884142_707118_14045_endTime);
                init_17_0_5_edc0357_1346104764223_34184_15024Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1346104787974_430716_15054 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1346105013928_873722_15220_exists = null;

            public Parameter< Power_System.Signaldr_request > _17_0_5_edc0357_1346105140214_847754_15269 = null;

            public IntegerParameter _17_0_5_edc0357_1346105173460_397488_15297_endTime = null;

            public ConstraintExpression constraint153 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346105013928_873722_15220_existsDependency = null;

            public Dependency< Power_System.Signaldr_request > _17_0_5_edc0357_1346105140214_847754_15269Dependency = null;

            public ElaborationRule elaborationRule154 = null;

            public void init_17_0_5_edc0357_1346104787974_430716_15054Members() {
                try {
                    _17_0_5_edc0357_1346105013928_873722_15220_exists = new BooleanParameter("_17_0_5_edc0357_1346105013928_873722_15220_exists", this);
                    _17_0_5_edc0357_1346105140214_847754_15269 = new Parameter<Power_System.Signaldr_request>("_17_0_5_edc0357_1346105140214_847754_15269", null, null, this);
                    _17_0_5_edc0357_1346105173460_397488_15297_endTime = new IntegerParameter("_17_0_5_edc0357_1346105173460_397488_15297_endTime", this);
                    constraint153 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346105173460_397488_15297_endTime)));
                    _17_0_5_edc0357_1346105013928_873722_15220_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346105013928_873722_15220_exists, new Expression<Boolean>(true));
                    _17_0_5_edc0357_1346105140214_847754_15269Dependency = new Dependency<Power_System.Signaldr_request>(_17_0_5_edc0357_1346105140214_847754_15269, new Expression(new FunctionCall((Object) q_Customer_dr_request, Utils.getMethodForArgTypes("ObjectFlow<Signaldr_request>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346104787974_430716_15054Collections() {
                parameters.add(_17_0_5_edc0357_1346105013928_873722_15220_exists);
                parameters.add(_17_0_5_edc0357_1346105140214_847754_15269);
                parameters.add(_17_0_5_edc0357_1346105173460_397488_15297_endTime);
                constraintExpressions.add(constraint153);
                dependencies.add(_17_0_5_edc0357_1346105013928_873722_15220_existsDependency);
                dependencies.add(_17_0_5_edc0357_1346105140214_847754_15269Dependency);
            }

            public void init_17_0_5_edc0357_1346104787974_430716_15054Elaborations() {
                Expression<?>[] arguments154 = new Expression<?>[2];
                arguments154[0] = new Expression<Integer>(endTime);
                arguments154[1] = new Expression<Power_System.Signaldr_request>(_17_0_5_edc0357_1346105140214_847754_15269);
                Expression<Boolean> condition154 = new Expression<Boolean>(_17_0_5_edc0357_1346105013928_873722_15220_exists);
                elaborationRule154 = addElaborationRule(condition154, _17_0_5_edc0357_1346104706708_917454_14957.this, Customer._17_0_5_edc0357_1346104706708_917454_14957._17_0_5_edc0357_1346105013928_873722_15220.class, "_ReadStructuralFeatureAction_CustomerCB", arguments154);
            }

            public _17_0_5_edc0357_1346104787974_430716_15054() {
                super();
                init_17_0_5_edc0357_1346104787974_430716_15054Members();
                init_17_0_5_edc0357_1346104787974_430716_15054Collections();
                init_17_0_5_edc0357_1346104787974_430716_15054Elaborations();
            }

            public _17_0_5_edc0357_1346104787974_430716_15054(Expression<Integer> _17_0_5_edc0357_1346105173460_397488_15297_endTime) {
                super();
                init_17_0_5_edc0357_1346104787974_430716_15054Members();
                init_17_0_5_edc0357_1346104787974_430716_15054Collections();
                addDependency(this._17_0_5_edc0357_1346105173460_397488_15297_endTime, _17_0_5_edc0357_1346105173460_397488_15297_endTime);
                init_17_0_5_edc0357_1346104787974_430716_15054Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1346104794886_467330_15077 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1346104908659_55389_15177_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1346104840585_256912_15134_exists = null;

            public ConstraintExpression constraint155 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346104840585_256912_15134_existsDependency = null;

            public Dependency< Integer > durationDependency = null;

            public ElaborationRule elaborationRule156 = null;

            public void init_17_0_5_edc0357_1346104794886_467330_15077Members() {
                try {
                    _17_0_5_edc0357_1346104908659_55389_15177_endTime = new IntegerParameter("_17_0_5_edc0357_1346104908659_55389_15177_endTime", this);
                    _17_0_5_edc0357_1346104840585_256912_15134_exists = new BooleanParameter("_17_0_5_edc0357_1346104840585_256912_15134_exists", this);
                    constraint155 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346104908659_55389_15177_endTime)));
                    _17_0_5_edc0357_1346104840585_256912_15134_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346104840585_256912_15134_exists, new Expression<Boolean>(true));
                    durationDependency = new Dependency<Integer>(duration, new Expression<Integer>(15));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346104794886_467330_15077Collections() {
                parameters.add(_17_0_5_edc0357_1346104908659_55389_15177_endTime);
                parameters.add(_17_0_5_edc0357_1346104840585_256912_15134_exists);
                constraintExpressions.add(constraint155);
                removeDependency(duration);
                dependencies.add(_17_0_5_edc0357_1346104840585_256912_15134_existsDependency);
                dependencies.add(durationDependency);
            }

            public void init_17_0_5_edc0357_1346104794886_467330_15077Elaborations() {
                Expression<?>[] arguments156 = new Expression<?>[1];
                arguments156[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition156 = new Expression<Boolean>(_17_0_5_edc0357_1346104840585_256912_15134_exists);
                elaborationRule156 = addElaborationRule(condition156, _17_0_5_edc0357_1346104706708_917454_14957.this, Customer._17_0_5_edc0357_1346104706708_917454_14957._17_0_5_edc0357_1346104840585_256912_15134.class, "_CallBehaviorAction_CustomerCB", arguments156);
            }

            public _17_0_5_edc0357_1346104794886_467330_15077() {
                super();
                init_17_0_5_edc0357_1346104794886_467330_15077Members();
                init_17_0_5_edc0357_1346104794886_467330_15077Collections();
                init_17_0_5_edc0357_1346104794886_467330_15077Elaborations();
            }

            public _17_0_5_edc0357_1346104794886_467330_15077(Expression<Integer> _17_0_5_edc0357_1346104908659_55389_15177_endTime) {
                super();
                init_17_0_5_edc0357_1346104794886_467330_15077Members();
                init_17_0_5_edc0357_1346104794886_467330_15077Collections();
                addDependency(this._17_0_5_edc0357_1346104908659_55389_15177_endTime, _17_0_5_edc0357_1346104908659_55389_15177_endTime);
                init_17_0_5_edc0357_1346104794886_467330_15077Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1346104807417_467678_15096 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1346105173460_397488_15297_exists = null;

            public IntegerParameter _17_0_5_edc0357_1346105157074_69873_15283 = null;

            public IntegerParameter _17_0_5_edc0357_1346105013928_873722_15220_endTime = null;

            public IntegerParameter cba_endTime = null;

            public ConstraintExpression constraint157 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346105173460_397488_15297_existsDependency = null;

            public ElaborationRule elaborationRule158 = null;

            public ElaborationRule elaborationRule159 = null;

            public void init_17_0_5_edc0357_1346104807417_467678_15096Members() {
                try {
                    _17_0_5_edc0357_1346105173460_397488_15297_exists = new BooleanParameter("_17_0_5_edc0357_1346105173460_397488_15297_exists", this);
                    _17_0_5_edc0357_1346105157074_69873_15283 = new IntegerParameter("_17_0_5_edc0357_1346105157074_69873_15283", this);
                    _17_0_5_edc0357_1346105013928_873722_15220_endTime = new IntegerParameter("_17_0_5_edc0357_1346105013928_873722_15220_endTime", this);
                    cba_endTime = new IntegerParameter("cba_endTime", this);
                    constraint157 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346105013928_873722_15220_endTime)));
                    _17_0_5_edc0357_1346105173460_397488_15297_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346105173460_397488_15297_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346104807417_467678_15096Collections() {
                parameters.add(_17_0_5_edc0357_1346105173460_397488_15297_exists);
                parameters.add(_17_0_5_edc0357_1346105157074_69873_15283);
                parameters.add(_17_0_5_edc0357_1346105013928_873722_15220_endTime);
                parameters.add(cba_endTime);
                constraintExpressions.add(constraint157);
                dependencies.add(_17_0_5_edc0357_1346105173460_397488_15297_existsDependency);
            }

            public void init_17_0_5_edc0357_1346104807417_467678_15096Elaborations() {
                Expression<?>[] arguments158 = new Expression<?>[1];
                arguments158[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition158 = new Expression<Boolean>(_17_0_5_edc0357_1346105173460_397488_15297_exists);
                elaborationRule158 = addElaborationRule(condition158, _17_0_5_edc0357_1346104706708_917454_14957.this, Customer._17_0_5_edc0357_1346104706708_917454_14957._17_0_5_edc0357_1346105173460_397488_15297.class, "_MergeNode_CustomerCB", arguments158);
                Expression<?>[] arguments159 = new Expression<?>[2];
                arguments159[0] = new Expression<Integer>(cba_endTime);
                arguments159[1] = new Expression<Integer>(_17_0_5_edc0357_1346105157074_69873_15283);
                Expression<Boolean> condition159 = new Expression<Boolean>(true);
                elaborationRule159 = addElaborationRule(condition159, Customer.this, Customer._17_0_5_edc0357_1345510113560_522513_13778.class, "setDRCap_Activity_Customer", arguments159);
            }

            public _17_0_5_edc0357_1346104807417_467678_15096() {
                super();
                init_17_0_5_edc0357_1346104807417_467678_15096Members();
                init_17_0_5_edc0357_1346104807417_467678_15096Collections();
                init_17_0_5_edc0357_1346104807417_467678_15096Elaborations();
            }

            public _17_0_5_edc0357_1346104807417_467678_15096(Expression<Integer> _17_0_5_edc0357_1346105013928_873722_15220_endTime, Expression<Integer> _17_0_5_edc0357_1346105157074_69873_15283) {
                super();
                init_17_0_5_edc0357_1346104807417_467678_15096Members();
                init_17_0_5_edc0357_1346104807417_467678_15096Collections();
                addDependency(this._17_0_5_edc0357_1346105013928_873722_15220_endTime, _17_0_5_edc0357_1346105013928_873722_15220_endTime);
                addDependency(this._17_0_5_edc0357_1346105157074_69873_15283, _17_0_5_edc0357_1346105157074_69873_15283);
                init_17_0_5_edc0357_1346104807417_467678_15096Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1346104812245_253068_15114 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1346105076614_639562_15240_exists = null;

            public IntegerParameter _17_0_5_edc0357_1348163241394_286425_14598_endTime = null;

            public IntegerParameter cba_endTime = null;

            public ConstraintExpression constraint160 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346105076614_639562_15240_existsDependency = null;

            public ElaborationRule elaborationRule161 = null;

            public ElaborationRule elaborationRule162 = null;

            public void init_17_0_5_edc0357_1346104812245_253068_15114Members() {
                try {
                    _17_0_5_edc0357_1346105076614_639562_15240_exists = new BooleanParameter("_17_0_5_edc0357_1346105076614_639562_15240_exists", this);
                    _17_0_5_edc0357_1348163241394_286425_14598_endTime = new IntegerParameter("_17_0_5_edc0357_1348163241394_286425_14598_endTime", this);
                    cba_endTime = new IntegerParameter("cba_endTime", this);
                    constraint160 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1348163241394_286425_14598_endTime)));
                    _17_0_5_edc0357_1346105076614_639562_15240_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346105076614_639562_15240_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346104812245_253068_15114Collections() {
                parameters.add(_17_0_5_edc0357_1346105076614_639562_15240_exists);
                parameters.add(_17_0_5_edc0357_1348163241394_286425_14598_endTime);
                parameters.add(cba_endTime);
                constraintExpressions.add(constraint160);
                dependencies.add(_17_0_5_edc0357_1346105076614_639562_15240_existsDependency);
            }

            public void init_17_0_5_edc0357_1346104812245_253068_15114Elaborations() {
                Expression<?>[] arguments161 = new Expression<?>[1];
                arguments161[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition161 = new Expression<Boolean>(_17_0_5_edc0357_1346105076614_639562_15240_exists);
                elaborationRule161 = addElaborationRule(condition161, _17_0_5_edc0357_1346104706708_917454_14957.this, Customer._17_0_5_edc0357_1346104706708_917454_14957._17_0_5_edc0357_1346105076614_639562_15240.class, "_MergeNode_CustomerCB", arguments161);
                Expression<?>[] arguments162 = new Expression<?>[1];
                arguments162[0] = new Expression<Integer>(cba_endTime);
                Expression<Boolean> condition162 = new Expression<Boolean>(true);
                elaborationRule162 = addElaborationRule(condition162, Customer.this, Customer._17_0_5_edc0357_1345510113557_625793_13776.class, "changePowerUsage_Activity_Customer", arguments162);
            }

            public _17_0_5_edc0357_1346104812245_253068_15114() {
                super();
                init_17_0_5_edc0357_1346104812245_253068_15114Members();
                init_17_0_5_edc0357_1346104812245_253068_15114Collections();
                init_17_0_5_edc0357_1346104812245_253068_15114Elaborations();
            }

            public _17_0_5_edc0357_1346104812245_253068_15114(Expression<Integer> _17_0_5_edc0357_1348163241394_286425_14598_endTime) {
                super();
                init_17_0_5_edc0357_1346104812245_253068_15114Members();
                init_17_0_5_edc0357_1346104812245_253068_15114Collections();
                addDependency(this._17_0_5_edc0357_1348163241394_286425_14598_endTime, _17_0_5_edc0357_1348163241394_286425_14598_endTime);
                init_17_0_5_edc0357_1346104812245_253068_15114Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1346104840585_256912_15134 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1346104908659_55389_15177_exists = null;

            public IntegerParameter _17_0_5_edc0357_1346104794886_467330_15077_endTime = null;

            public IntegerParameter cba_endTime = null;

            public ConstraintExpression constraint163 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346104908659_55389_15177_existsDependency = null;

            public ElaborationRule elaborationRule164 = null;

            public ElaborationRule elaborationRule165 = null;

            public void init_17_0_5_edc0357_1346104840585_256912_15134Members() {
                try {
                    _17_0_5_edc0357_1346104908659_55389_15177_exists = new BooleanParameter("_17_0_5_edc0357_1346104908659_55389_15177_exists", this);
                    _17_0_5_edc0357_1346104794886_467330_15077_endTime = new IntegerParameter("_17_0_5_edc0357_1346104794886_467330_15077_endTime", this);
                    cba_endTime = new IntegerParameter("cba_endTime", this);
                    constraint163 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346104794886_467330_15077_endTime)));
                    _17_0_5_edc0357_1346104908659_55389_15177_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346104908659_55389_15177_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346104840585_256912_15134Collections() {
                parameters.add(_17_0_5_edc0357_1346104908659_55389_15177_exists);
                parameters.add(_17_0_5_edc0357_1346104794886_467330_15077_endTime);
                parameters.add(cba_endTime);
                constraintExpressions.add(constraint163);
                dependencies.add(_17_0_5_edc0357_1346104908659_55389_15177_existsDependency);
            }

            public void init_17_0_5_edc0357_1346104840585_256912_15134Elaborations() {
                Expression<?>[] arguments164 = new Expression<?>[1];
                arguments164[0] = new Expression<Integer>(cba_endTime);
                Expression<Boolean> condition164 = new Expression<Boolean>(true);
                elaborationRule164 = addElaborationRule(condition164, Customer.this, Customer._17_0_5_edc0357_1345510113556_623276_13775.class, "usePower_Activity_Customer", arguments164);
                Expression<?>[] arguments165 = new Expression<?>[1];
                arguments165[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition165 = new Expression<Boolean>(_17_0_5_edc0357_1346104908659_55389_15177_exists);
                elaborationRule165 = addElaborationRule(condition165, _17_0_5_edc0357_1346104706708_917454_14957.this, Customer._17_0_5_edc0357_1346104706708_917454_14957._17_0_5_edc0357_1346104908659_55389_15177.class, "_MergeNode_CustomerCB", arguments165);
            }

            public _17_0_5_edc0357_1346104840585_256912_15134() {
                super();
                init_17_0_5_edc0357_1346104840585_256912_15134Members();
                init_17_0_5_edc0357_1346104840585_256912_15134Collections();
                init_17_0_5_edc0357_1346104840585_256912_15134Elaborations();
            }

            public _17_0_5_edc0357_1346104840585_256912_15134(Expression<Integer> _17_0_5_edc0357_1346104794886_467330_15077_endTime) {
                super();
                init_17_0_5_edc0357_1346104840585_256912_15134Members();
                init_17_0_5_edc0357_1346104840585_256912_15134Collections();
                addDependency(this._17_0_5_edc0357_1346104794886_467330_15077_endTime, _17_0_5_edc0357_1346104794886_467330_15077_endTime);
                init_17_0_5_edc0357_1346104840585_256912_15134Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1346104860701_80157_15146 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1348163169744_444878_14578_endTime = null;

            public ConstraintExpression constraint166 = null;

            public void init_17_0_5_edc0357_1346104860701_80157_15146Members() {
                try {
                    _17_0_5_edc0357_1348163169744_444878_14578_endTime = new IntegerParameter("_17_0_5_edc0357_1348163169744_444878_14578_endTime", this);
                    constraint166 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1348163169744_444878_14578_endTime)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346104860701_80157_15146Collections() {
                parameters.add(_17_0_5_edc0357_1348163169744_444878_14578_endTime);
                constraintExpressions.add(constraint166);
            }

            public void init_17_0_5_edc0357_1346104860701_80157_15146Elaborations() {
            }

            public _17_0_5_edc0357_1346104860701_80157_15146() {
                super();
                init_17_0_5_edc0357_1346104860701_80157_15146Members();
                init_17_0_5_edc0357_1346104860701_80157_15146Collections();
                init_17_0_5_edc0357_1346104860701_80157_15146Elaborations();
            }

            public _17_0_5_edc0357_1346104860701_80157_15146(Expression<Integer> _17_0_5_edc0357_1348163169744_444878_14578_endTime) {
                super();
                init_17_0_5_edc0357_1346104860701_80157_15146Members();
                init_17_0_5_edc0357_1346104860701_80157_15146Collections();
                addDependency(this._17_0_5_edc0357_1348163169744_444878_14578_endTime, _17_0_5_edc0357_1348163169744_444878_14578_endTime);
                init_17_0_5_edc0357_1346104860701_80157_15146Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1346104908659_55389_15177 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1346104794886_467330_15077_exists = null;

            public IntegerParameter invoker_endTime = null;

            public ConstraintExpression constraint167 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346104794886_467330_15077_existsDependency = null;

            public ElaborationRule elaborationRule168 = null;

            public void init_17_0_5_edc0357_1346104908659_55389_15177Members() {
                try {
                    _17_0_5_edc0357_1346104794886_467330_15077_exists = new BooleanParameter("_17_0_5_edc0357_1346104794886_467330_15077_exists", this);
                    invoker_endTime = new IntegerParameter("invoker_endTime", this);
                    constraint167 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(invoker_endTime)));
                    _17_0_5_edc0357_1346104794886_467330_15077_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346104794886_467330_15077_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346104908659_55389_15177Collections() {
                parameters.add(_17_0_5_edc0357_1346104794886_467330_15077_exists);
                parameters.add(invoker_endTime);
                constraintExpressions.add(constraint167);
                dependencies.add(_17_0_5_edc0357_1346104794886_467330_15077_existsDependency);
            }

            public void init_17_0_5_edc0357_1346104908659_55389_15177Elaborations() {
                Expression<?>[] arguments168 = new Expression<?>[1];
                arguments168[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition168 = new Expression<Boolean>(_17_0_5_edc0357_1346104794886_467330_15077_exists);
                elaborationRule168 = addElaborationRule(condition168, _17_0_5_edc0357_1346104706708_917454_14957.this, Customer._17_0_5_edc0357_1346104706708_917454_14957._17_0_5_edc0357_1346104794886_467330_15077.class, "_AcceptEventAction_CustomerCB", arguments168);
            }

            public _17_0_5_edc0357_1346104908659_55389_15177() {
                super();
                init_17_0_5_edc0357_1346104908659_55389_15177Members();
                init_17_0_5_edc0357_1346104908659_55389_15177Collections();
                init_17_0_5_edc0357_1346104908659_55389_15177Elaborations();
            }

            public _17_0_5_edc0357_1346104908659_55389_15177(Expression<Integer> invoker_endTime) {
                super();
                init_17_0_5_edc0357_1346104908659_55389_15177Members();
                init_17_0_5_edc0357_1346104908659_55389_15177Collections();
                addDependency(this.invoker_endTime, invoker_endTime);
                init_17_0_5_edc0357_1346104908659_55389_15177Elaborations();
                fixTimeDependencies();
            }

        }

        public class _17_0_5_edc0357_1346105013928_873722_15220 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1346104787974_430716_15054_endTime = null;

            public IntegerParameter _17_0_5_edc0357_1346105116990_449068_15259 = null;

            public Parameter< Power_System.Signaldr_request > _17_0_5_edc0357_1346105104143_626038_15258 = null;

            public BooleanParameter _17_0_5_edc0357_1346104807417_467678_15096_exists = null;

            public ConstraintExpression constraint169 = null;

            public Dependency< Integer > _17_0_5_edc0357_1346105116990_449068_15259Dependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346104807417_467678_15096_existsDependency = null;

            public ElaborationRule elaborationRule170 = null;

            public void init_17_0_5_edc0357_1346105013928_873722_15220Members() {
                try {
                    _17_0_5_edc0357_1346104787974_430716_15054_endTime = new IntegerParameter("_17_0_5_edc0357_1346104787974_430716_15054_endTime", this);
                    _17_0_5_edc0357_1346105116990_449068_15259 = new IntegerParameter("_17_0_5_edc0357_1346105116990_449068_15259", this);
                    _17_0_5_edc0357_1346105104143_626038_15258 = new Parameter<Power_System.Signaldr_request>("_17_0_5_edc0357_1346105104143_626038_15258", null, null, this);
                    _17_0_5_edc0357_1346104807417_467678_15096_exists = new BooleanParameter("_17_0_5_edc0357_1346104807417_467678_15096_exists", this);
                    constraint169 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346104787974_430716_15054_endTime)));
                    FunctionCall nestedCall3 = new FunctionCall(null, TimeVaryingMap.class, "getValue", new Object[] { startTime } );
                    FunctionCall nestedCall2 = new FunctionCall(null, Parameter.class, "getValue", (Object[]) null, nestedCall3 );
                    FunctionCall call1 = new FunctionCall(_17_0_5_edc0357_1346105116990_449068_15259, Parameter.class, "getMember", new Object[] { "cap__17_0_5_edc0357_1345510113618_430459_13856" }, nestedCall2 );
                    _17_0_5_edc0357_1346105116990_449068_15259Dependency = new Dependency<Integer>(_17_0_5_edc0357_1346105116990_449068_15259, new Expression(call1));
                    //_17_0_5_edc0357_1346105116990_449068_15259Dependency = new Dependency<Integer>(_17_0_5_edc0357_1346105116990_449068_15259, new Expression(new FunctionCall((Object) (((Parameter<?>) (_17_0_5_edc0357_1346105104143_626038_15258.getMember("cap__17_0_5_edc0357_1345510113618_430459_13856"))).getValue()), Utils.getMethodForArgTypes("Customer._17_0_5_edc0357_1346104706708_917454_14957._17_0_5_edc0357_1346105013928_873722_15220", "generated", "getValue"), new Object[] { new Expression<Integer>(startTime) })));
                    _17_0_5_edc0357_1346104807417_467678_15096_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346104807417_467678_15096_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346105013928_873722_15220Collections() {
                parameters.add(_17_0_5_edc0357_1346104787974_430716_15054_endTime);
                parameters.add(_17_0_5_edc0357_1346105116990_449068_15259);
                parameters.add(_17_0_5_edc0357_1346105104143_626038_15258);
                parameters.add(_17_0_5_edc0357_1346104807417_467678_15096_exists);
                constraintExpressions.add(constraint169);
                dependencies.add(_17_0_5_edc0357_1346105116990_449068_15259Dependency);
                dependencies.add(_17_0_5_edc0357_1346104807417_467678_15096_existsDependency);
            }

            public void init_17_0_5_edc0357_1346105013928_873722_15220Elaborations() {
                Expression<?>[] arguments170 = new Expression<?>[2];
                arguments170[0] = new Expression<Integer>(endTime);
                arguments170[1] = new Expression<Integer>(_17_0_5_edc0357_1346105116990_449068_15259);
                Expression<Boolean> condition170 = new Expression<Boolean>(_17_0_5_edc0357_1346104807417_467678_15096_exists);
                elaborationRule170 = addElaborationRule(condition170, _17_0_5_edc0357_1346104706708_917454_14957.this, Customer._17_0_5_edc0357_1346104706708_917454_14957._17_0_5_edc0357_1346104807417_467678_15096.class, "_CallBehaviorAction_CustomerCB", arguments170);
            }

            public _17_0_5_edc0357_1346105013928_873722_15220() {
                super();
                init_17_0_5_edc0357_1346105013928_873722_15220Members();
                init_17_0_5_edc0357_1346105013928_873722_15220Collections();
                init_17_0_5_edc0357_1346105013928_873722_15220Elaborations();
            }

            public _17_0_5_edc0357_1346105013928_873722_15220(Expression<Integer> _17_0_5_edc0357_1346104787974_430716_15054_endTime, Expression<Power_System.Signaldr_request> _17_0_5_edc0357_1346105104143_626038_15258) {
                super();
                init_17_0_5_edc0357_1346105013928_873722_15220Members();
                init_17_0_5_edc0357_1346105013928_873722_15220Collections();
                addDependency(this._17_0_5_edc0357_1346104787974_430716_15054_endTime, _17_0_5_edc0357_1346104787974_430716_15054_endTime);
                addDependency(this._17_0_5_edc0357_1346105104143_626038_15258, _17_0_5_edc0357_1346105104143_626038_15258);
                init_17_0_5_edc0357_1346105013928_873722_15220Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1346105076614_639562_15240 extends DurativeEvent {

            public IntegerParameter invoker_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1348163241394_286425_14598_exists = null;

            public ConstraintExpression constraint171 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1348163241394_286425_14598_existsDependency = null;

            public ElaborationRule elaborationRule172 = null;

            public void init_17_0_5_edc0357_1346105076614_639562_15240Members() {
                try {
                    invoker_endTime = new IntegerParameter("invoker_endTime", this);
                    _17_0_5_edc0357_1348163241394_286425_14598_exists = new BooleanParameter("_17_0_5_edc0357_1348163241394_286425_14598_exists", this);
                    constraint171 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(invoker_endTime)));
                    _17_0_5_edc0357_1348163241394_286425_14598_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1348163241394_286425_14598_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346105076614_639562_15240Collections() {
                parameters.add(invoker_endTime);
                parameters.add(_17_0_5_edc0357_1348163241394_286425_14598_exists);
                constraintExpressions.add(constraint171);
                dependencies.add(_17_0_5_edc0357_1348163241394_286425_14598_existsDependency);
            }

            public void init_17_0_5_edc0357_1346105076614_639562_15240Elaborations() {
                Expression<?>[] arguments172 = new Expression<?>[1];
                arguments172[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition172 = new Expression<Boolean>(_17_0_5_edc0357_1348163241394_286425_14598_exists);
                elaborationRule172 = addElaborationRule(condition172, _17_0_5_edc0357_1346104706708_917454_14957.this, Customer._17_0_5_edc0357_1346104706708_917454_14957._17_0_5_edc0357_1348163241394_286425_14598.class, "_AcceptEventAction_CustomerCB", arguments172);
            }

            public _17_0_5_edc0357_1346105076614_639562_15240() {
                super();
                init_17_0_5_edc0357_1346105076614_639562_15240Members();
                init_17_0_5_edc0357_1346105076614_639562_15240Collections();
                init_17_0_5_edc0357_1346105076614_639562_15240Elaborations();
            }

            public _17_0_5_edc0357_1346105076614_639562_15240(Expression<Integer> invoker_endTime) {
                super();
                init_17_0_5_edc0357_1346105076614_639562_15240Members();
                init_17_0_5_edc0357_1346105076614_639562_15240Collections();
                addDependency(this.invoker_endTime, invoker_endTime);
                init_17_0_5_edc0357_1346105076614_639562_15240Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1346105173460_397488_15297 extends DurativeEvent {

            public IntegerParameter invoker_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1346104787974_430716_15054_exists = null;

            public ConstraintExpression constraint173 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346104787974_430716_15054_existsDependency = null;

            public ElaborationRule elaborationRule174 = null;

            public void init_17_0_5_edc0357_1346105173460_397488_15297Members() {
                try {
                    invoker_endTime = new IntegerParameter("invoker_endTime", this);
                    _17_0_5_edc0357_1346104787974_430716_15054_exists = new BooleanParameter("_17_0_5_edc0357_1346104787974_430716_15054_exists", this);
                    constraint173 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(invoker_endTime)));
                    _17_0_5_edc0357_1346104787974_430716_15054_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346104787974_430716_15054_exists, new Expression(new FunctionCall((Object) q_Customer_dr_request, Utils.getMethodForArgTypes("ObjectFlow<Signaldr_request>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346105173460_397488_15297Collections() {
                parameters.add(invoker_endTime);
                parameters.add(_17_0_5_edc0357_1346104787974_430716_15054_exists);
                constraintExpressions.add(constraint173);
                dependencies.add(_17_0_5_edc0357_1346104787974_430716_15054_existsDependency);
            }

            public void init_17_0_5_edc0357_1346105173460_397488_15297Elaborations() {
                Expression<?>[] arguments174 = new Expression<?>[1];
                arguments174[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition174 = new Expression<Boolean>(_17_0_5_edc0357_1346104787974_430716_15054_exists);
                elaborationRule174 = addElaborationRule(condition174, _17_0_5_edc0357_1346104706708_917454_14957.this, Customer._17_0_5_edc0357_1346104706708_917454_14957._17_0_5_edc0357_1346104787974_430716_15054.class, "_AcceptEventAction_CustomerCB", arguments174);
            }

            public _17_0_5_edc0357_1346105173460_397488_15297() {
                super();
                init_17_0_5_edc0357_1346105173460_397488_15297Members();
                init_17_0_5_edc0357_1346105173460_397488_15297Collections();
                init_17_0_5_edc0357_1346105173460_397488_15297Elaborations();
            }

            public _17_0_5_edc0357_1346105173460_397488_15297(Expression<Integer> invoker_endTime) {
                super();
                init_17_0_5_edc0357_1346105173460_397488_15297Members();
                init_17_0_5_edc0357_1346105173460_397488_15297Collections();
                addDependency(this.invoker_endTime, invoker_endTime);
                init_17_0_5_edc0357_1346105173460_397488_15297Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1348000884142_707118_14045 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1346104727721_22566_14990_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1346104764223_34184_15024_exists = null;

            public ConstraintExpression constraint175 = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346104764223_34184_15024_existsDependency = null;

            public ElaborationRule elaborationRule176 = null;

            public void init_17_0_5_edc0357_1348000884142_707118_14045Members() {
                try {
                    _17_0_5_edc0357_1346104727721_22566_14990_endTime = new IntegerParameter("_17_0_5_edc0357_1346104727721_22566_14990_endTime", this);
                    _17_0_5_edc0357_1346104764223_34184_15024_exists = new BooleanParameter("_17_0_5_edc0357_1346104764223_34184_15024_exists", this);
                    constraint175 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346104727721_22566_14990_endTime)));
                    durationDependency = new Dependency<Integer>(duration, new Expression<Integer>(20));
                    _17_0_5_edc0357_1346104764223_34184_15024_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346104764223_34184_15024_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1348000884142_707118_14045Collections() {
                parameters.add(_17_0_5_edc0357_1346104727721_22566_14990_endTime);
                parameters.add(_17_0_5_edc0357_1346104764223_34184_15024_exists);
                constraintExpressions.add(constraint175);
                removeDependency(duration);
                dependencies.add(durationDependency);
                dependencies.add(_17_0_5_edc0357_1346104764223_34184_15024_existsDependency);
            }

            public void init_17_0_5_edc0357_1348000884142_707118_14045Elaborations() {
                Expression<?>[] arguments176 = new Expression<?>[1];
                arguments176[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition176 = new Expression<Boolean>(_17_0_5_edc0357_1346104764223_34184_15024_exists);
                elaborationRule176 = addElaborationRule(condition176, _17_0_5_edc0357_1346104706708_917454_14957.this, Customer._17_0_5_edc0357_1346104706708_917454_14957._17_0_5_edc0357_1346104764223_34184_15024.class, "_ForkNode_CustomerCB", arguments176);
            }

            public _17_0_5_edc0357_1348000884142_707118_14045() {
                super();
                init_17_0_5_edc0357_1348000884142_707118_14045Members();
                init_17_0_5_edc0357_1348000884142_707118_14045Collections();
                init_17_0_5_edc0357_1348000884142_707118_14045Elaborations();
            }

            public _17_0_5_edc0357_1348000884142_707118_14045(Expression<Integer> _17_0_5_edc0357_1346104727721_22566_14990_endTime) {
                super();
                init_17_0_5_edc0357_1348000884142_707118_14045Members();
                init_17_0_5_edc0357_1348000884142_707118_14045Collections();
                addDependency(this._17_0_5_edc0357_1346104727721_22566_14990_endTime, _17_0_5_edc0357_1346104727721_22566_14990_endTime);
                init_17_0_5_edc0357_1348000884142_707118_14045Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1348163169744_444878_14578 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1346104764223_34184_15024_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1346104860701_80157_15146_exists = null;

            public ConstraintExpression constraint177 = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346104860701_80157_15146_existsDependency = null;

            public ElaborationRule elaborationRule178 = null;

            public void init_17_0_5_edc0357_1348163169744_444878_14578Members() {
                try {
                    _17_0_5_edc0357_1346104764223_34184_15024_endTime = new IntegerParameter("_17_0_5_edc0357_1346104764223_34184_15024_endTime", this);
                    _17_0_5_edc0357_1346104860701_80157_15146_exists = new BooleanParameter("_17_0_5_edc0357_1346104860701_80157_15146_exists", this);
                    constraint177 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346104764223_34184_15024_endTime)));
                    durationDependency = new Dependency<Integer>(duration, new Expression<Integer>(10000));
                    _17_0_5_edc0357_1346104860701_80157_15146_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346104860701_80157_15146_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1348163169744_444878_14578Collections() {
                parameters.add(_17_0_5_edc0357_1346104764223_34184_15024_endTime);
                parameters.add(_17_0_5_edc0357_1346104860701_80157_15146_exists);
                constraintExpressions.add(constraint177);
                removeDependency(duration);
                dependencies.add(durationDependency);
                dependencies.add(_17_0_5_edc0357_1346104860701_80157_15146_existsDependency);
            }

            public void init_17_0_5_edc0357_1348163169744_444878_14578Elaborations() {
                Expression<?>[] arguments178 = new Expression<?>[1];
                arguments178[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition178 = new Expression<Boolean>(_17_0_5_edc0357_1346104860701_80157_15146_exists);
                elaborationRule178 = addElaborationRule(condition178, _17_0_5_edc0357_1346104706708_917454_14957.this, Customer._17_0_5_edc0357_1346104706708_917454_14957._17_0_5_edc0357_1346104860701_80157_15146.class, "_ActivityFinalNode_CustomerCB", arguments178);
            }

            public _17_0_5_edc0357_1348163169744_444878_14578() {
                super();
                init_17_0_5_edc0357_1348163169744_444878_14578Members();
                init_17_0_5_edc0357_1348163169744_444878_14578Collections();
                init_17_0_5_edc0357_1348163169744_444878_14578Elaborations();
            }

            public _17_0_5_edc0357_1348163169744_444878_14578(Expression<Integer> _17_0_5_edc0357_1346104764223_34184_15024_endTime) {
                super();
                init_17_0_5_edc0357_1348163169744_444878_14578Members();
                init_17_0_5_edc0357_1348163169744_444878_14578Collections();
                addDependency(this._17_0_5_edc0357_1346104764223_34184_15024_endTime, _17_0_5_edc0357_1346104764223_34184_15024_endTime);
                init_17_0_5_edc0357_1348163169744_444878_14578Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1348163241394_286425_14598 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1346105076614_639562_15240_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1346104812245_253068_15114_exists = null;

            public ConstraintExpression constraint179 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346104812245_253068_15114_existsDependency = null;

            public Dependency< Integer > durationDependency = null;

            public ElaborationRule elaborationRule180 = null;

            public void init_17_0_5_edc0357_1348163241394_286425_14598Members() {
                try {
                    _17_0_5_edc0357_1346105076614_639562_15240_endTime = new IntegerParameter("_17_0_5_edc0357_1346105076614_639562_15240_endTime", this);
                    _17_0_5_edc0357_1346104812245_253068_15114_exists = new BooleanParameter("_17_0_5_edc0357_1346104812245_253068_15114_exists", this);
                    constraint179 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346105076614_639562_15240_endTime)));
                    _17_0_5_edc0357_1346104812245_253068_15114_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346104812245_253068_15114_exists, new Expression<Boolean>(true));
                    durationDependency = new Dependency<Integer>(duration, new Expression<Integer>(60));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1348163241394_286425_14598Collections() {
                parameters.add(_17_0_5_edc0357_1346105076614_639562_15240_endTime);
                parameters.add(_17_0_5_edc0357_1346104812245_253068_15114_exists);
                constraintExpressions.add(constraint179);
                removeDependency(duration);
                dependencies.add(_17_0_5_edc0357_1346104812245_253068_15114_existsDependency);
                dependencies.add(durationDependency);
            }

            public void init_17_0_5_edc0357_1348163241394_286425_14598Elaborations() {
                Expression<?>[] arguments180 = new Expression<?>[1];
                arguments180[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition180 = new Expression<Boolean>(_17_0_5_edc0357_1346104812245_253068_15114_exists);
                elaborationRule180 = addElaborationRule(condition180, _17_0_5_edc0357_1346104706708_917454_14957.this, Customer._17_0_5_edc0357_1346104706708_917454_14957._17_0_5_edc0357_1346104812245_253068_15114.class, "_CallBehaviorAction_CustomerCB", arguments180);
            }

            public _17_0_5_edc0357_1348163241394_286425_14598() {
                super();
                init_17_0_5_edc0357_1348163241394_286425_14598Members();
                init_17_0_5_edc0357_1348163241394_286425_14598Collections();
                init_17_0_5_edc0357_1348163241394_286425_14598Elaborations();
            }

            public _17_0_5_edc0357_1348163241394_286425_14598(Expression<Integer> _17_0_5_edc0357_1346105076614_639562_15240_endTime) {
                super();
                init_17_0_5_edc0357_1348163241394_286425_14598Members();
                init_17_0_5_edc0357_1348163241394_286425_14598Collections();
                addDependency(this._17_0_5_edc0357_1346105076614_639562_15240_endTime, _17_0_5_edc0357_1346105076614_639562_15240_endTime);
                init_17_0_5_edc0357_1348163241394_286425_14598Elaborations();
                fixTimeDependencies();
            }
        }

        public _17_0_5_edc0357_1346104706708_917454_14957(Expression<Integer> invoke_time) {
            super();
            init_17_0_5_edc0357_1346104706708_917454_14957Members();
            init_17_0_5_edc0357_1346104706708_917454_14957Collections();
            addDependency(this.invoke_time, invoke_time);
            init_17_0_5_edc0357_1346104706708_917454_14957Elaborations();
            fixTimeDependencies();
        }
    }

    public Customer(Power_System x) {
        super();
        this.x = new Parameter<Power_System>("Power_System", null, x, null);
        initCustomerMembers();
        initCustomerCollections();
        initCustomerElaborations();
    }
}
