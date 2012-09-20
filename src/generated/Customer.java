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
import java.util.Queue;
import java.util.LinkedList;
import gov.nasa.jpl.ae.event.Timepoint;
import gov.nasa.jpl.ae.fuml.ObjectFlow;

public class Customer extends ParameterListenerImpl {

    public StringParameter classifierBehavior = null;

    public Parameter q_Customer_receiveMeterReading = null;

    public Parameter usage__17_0_5_edc0357_1345510113561_560076_13779 = null;

    public Parameter q_Customer_dr_request = null;
    
    public Parameter q_Customer_changeUsage = null;

    public Parameter cap__17_0_5_edc0357_1345510113566_55150_13784 = null;

    public Power_System x = null;

    public Parameter q_Customer_yes = null;

    public Parameter q_Customer_changeLoadValue = null;

    public Parameter q_Customer_no = null;

    public void initCustomerMembers() {
        try {
            classifierBehavior = new StringParameter("classifierBehavior", this);
            q_Customer_receiveMeterReading = new Parameter("q_Customer_receiveMeterReading", null, new ObjectFlow("q_Customer_receiveMeterReading", Power_System.SignalreceiveMeterReading.class), this);
            usage__17_0_5_edc0357_1345510113561_560076_13779 = new Parameter("usage__17_0_5_edc0357_1345510113561_560076_13779", null, new TimeVaryingMap("usage"), this);
            q_Customer_dr_request = new Parameter("q_Customer_dr_request", null, new ObjectFlow("q_Customer_dr_request", Power_System.Signaldr_request.class), this);
            cap__17_0_5_edc0357_1345510113566_55150_13784 = new Parameter("cap__17_0_5_edc0357_1345510113566_55150_13784", null, new TimeVaryingMap("cap"), this);
            //x = new Parameter("x", null, null, this);
            q_Customer_yes = new Parameter("q_Customer_yes", null, new ObjectFlow("q_Customer_yes", Power_System.Signalyes.class), this);
            q_Customer_changeLoadValue = new Parameter("q_Customer_changeLoadValue", null, new ObjectFlow("q_Customer_changeLoadValue", Power_System.SignalchangeLoadValue.class), this);
            q_Customer_no = new Parameter("q_Customer_no", null, new ObjectFlow("q_Customer_no", Power_System.Signalno.class), this);
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
        //parameters.add(x);
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

        public Parameter sig_17_0_5_edc0357_1346280185062_487679_12810 = null;

        public Parameter sig_17_0_5_edc0357_1345510114028_727317_14215 = null;

        public Parameter sig_17_0_5_edc0357_1345510114028_129199_14214 = null;

        public IntegerParameter invoke_time = null;

        public ElaborationRule elaborationRule26 = null;

        public void init_17_0_5_edc0357_1345510113556_623276_13775Members() {
            try {
                sig_17_0_5_edc0357_1346280185062_487679_12810 = new Parameter("sig_17_0_5_edc0357_1346280185062_487679_12810", null, new ObjectFlow("sig_17_0_5_edc0357_1346280185062_487679_12810"), this);
                sig_17_0_5_edc0357_1345510114028_727317_14215 = new Parameter("sig_17_0_5_edc0357_1345510114028_727317_14215", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114028_727317_14215"), this);
                sig_17_0_5_edc0357_1345510114028_129199_14214 = new Parameter("sig_17_0_5_edc0357_1345510114028_129199_14214", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114028_129199_14214"), this);
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

            public Parameter _17_0_5_edc0357_1345510114780_387271_15102 = null;

            public ConstraintExpression constraint28 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114027_337563_14204_existsDependency = null;

            public ElaborationRule elaborationRule29 = null;

            public void init_17_0_5_edc0357_1345510114026_592814_14200Members() {
                try {
                    _17_0_5_edc0357_1345510114026_43123_14199_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114026_43123_14199_endTime", this);
                    _17_0_5_edc0357_1345510114027_337563_14204_exists = new BooleanParameter("_17_0_5_edc0357_1345510114027_337563_14204_exists", this);
                    _17_0_5_edc0357_1345510114780_387271_15102 = new Parameter("_17_0_5_edc0357_1345510114780_387271_15102", null, Customer.this, this);
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

            public Parameter _17_0_5_edc0357_1345510114781_225445_15104 = null;

            public IntegerParameter _17_0_5_edc0357_1345510114027_337563_14204_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1345510114027_851146_14206_exists = null;

            public ConstraintExpression constraint31 = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114781_279299_15103Dependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114027_851146_14206_existsDependency = null;

            public ElaborationRule elaborationRule32 = null;

            public void init_17_0_5_edc0357_1345510114027_454308_14202Members() {
                try {
                    _17_0_5_edc0357_1345510114781_279299_15103 = new IntegerParameter("_17_0_5_edc0357_1345510114781_279299_15103", this);
                    _17_0_5_edc0357_1345510114781_225445_15104 = new Parameter("_17_0_5_edc0357_1345510114781_225445_15104", null, null, this);
                    _17_0_5_edc0357_1345510114027_337563_14204_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114027_337563_14204_endTime", this);
                    _17_0_5_edc0357_1345510114027_851146_14206_exists = new BooleanParameter("_17_0_5_edc0357_1345510114027_851146_14206_exists", this);
                    constraint31 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114027_337563_14204_endTime)));
                    _17_0_5_edc0357_1345510114781_279299_15103Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114781_279299_15103, new Expression(new FunctionCall((Object) ((Customer) _17_0_5_edc0357_1345510114781_225445_15104.getValue()).usage__17_0_5_edc0357_1345510113561_560076_13779.getValue(), Utils.getMethodForArgTypes("Customer._17_0_5_edc0357_1345510113556_623276_13775._17_0_5_edc0357_1345510114027_454308_14202", "generated", "getValue"), new Object[] { new Expression<Integer>(startTime) })));
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
                elaborationRule32 = addElaborationRule(condition32, _17_0_5_edc0357_1345510113556_623276_13775.this, Customer._17_0_5_edc0357_1345510113556_623276_13775._17_0_5_edc0357_1345510114027_851146_14206.class, "_ForkNode_usePower", arguments32);
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

            public Parameter _17_0_5_edc0357_1345510114782_358183_15105 = null;

            public BooleanParameter _17_0_5_edc0357_1346280179065_311539_12799_exists = null;

            public ConstraintExpression constraint33 = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114783_59952_15106Dependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346280179065_311539_12799_existsDependency = null;

            public Effect effect34 = null;

            public ElaborationRule elaborationRule35 = null;

            public void init_17_0_5_edc0357_1345510114027_512113_14203Members() {
                try {
                    _17_0_5_edc0357_1345510114027_337563_14204_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114027_337563_14204_endTime", this);
                    _17_0_5_edc0357_1345510114783_59952_15106 = new IntegerParameter("_17_0_5_edc0357_1345510114783_59952_15106", this);
                    _17_0_5_edc0357_1345510114782_358183_15105 = new Parameter("_17_0_5_edc0357_1345510114782_358183_15105", null, null, this);
                    _17_0_5_edc0357_1346280179065_311539_12799_exists = new BooleanParameter("_17_0_5_edc0357_1346280179065_311539_12799_exists", this);
                    constraint33 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114027_337563_14204_endTime)));
                    _17_0_5_edc0357_1345510114783_59952_15106Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114783_59952_15106, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114028_129199_14214, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1346280179065_311539_12799_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346280179065_311539_12799_exists, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1346280185062_487679_12810, Utils.getMethodForArgTypes("ObjectFlow<Object>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })));
                    effect34 = new EffectFunction(new FunctionCall((Object) ((Power_System) x).ss_17_0_5_edc0357_1345510113562_98635_13780_changeLoadValue.getValue(), Utils.getMethodForArgTypes("Customer._17_0_5_edc0357_1345510113556_623276_13775._17_0_5_edc0357_1345510114027_512113_14203", "generated", "send"), new Object[] { x.new SignalchangeLoadValue(_17_0_5_edc0357_1345510114783_59952_15106.getValue()), endTime }));
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
            }

            public void init_17_0_5_edc0357_1345510114027_512113_14203Elaborations() {
                Expression<?>[] arguments35 = new Expression<?>[1];
                arguments35[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition35 = new Expression<Boolean>(_17_0_5_edc0357_1346280179065_311539_12799_exists);
                elaborationRule35 = addElaborationRule(condition35, _17_0_5_edc0357_1345510113556_623276_13775.this, Customer._17_0_5_edc0357_1345510113556_623276_13775._17_0_5_edc0357_1346280179065_311539_12799.class, "_JoinNode_usePower", arguments35);
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

            public Parameter objectToPass = null;

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
                    objectToPass = new Parameter("objectToPass", null, null, this);
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
                Expression<Boolean> condition37 = new Expression<Boolean>(_17_0_5_edc0357_1345510114027_512113_14203_exists);
                elaborationRule37 = addElaborationRule(condition37, _17_0_5_edc0357_1345510113556_623276_13775.this, Customer._17_0_5_edc0357_1345510113556_623276_13775._17_0_5_edc0357_1345510114027_512113_14203.class, "_SendSignalAction_usePower", arguments37);
                Expression<?>[] arguments38 = new Expression<?>[2];
                arguments38[0] = new Expression<Integer>(endTime);
                arguments38[1] = new Expression<Customer>(objectToPass);
                Expression<Boolean> condition38 = new Expression<Boolean>(_17_0_5_edc0357_1345510114027_379047_14205_exists);
                elaborationRule38 = addElaborationRule(condition38, _17_0_5_edc0357_1345510113556_623276_13775.this, Customer._17_0_5_edc0357_1345510113556_623276_13775._17_0_5_edc0357_1345510114027_379047_14205.class, "_SendSignalAction_usePower", arguments38);
                Expression<?>[] arguments39 = new Expression<?>[2];
                arguments39[0] = new Expression<Integer>(endTime);
                arguments39[1] = new Expression<Customer>(objectToPass);
                Expression<Boolean> condition39 = new Expression<Boolean>(_17_0_5_edc0357_1345510114027_454308_14202_exists);
                elaborationRule39 = addElaborationRule(condition39, _17_0_5_edc0357_1345510113556_623276_13775.this, Customer._17_0_5_edc0357_1345510113556_623276_13775._17_0_5_edc0357_1345510114027_454308_14202.class, "_ReadStructuralFeatureAction_usePower", arguments39);
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

            public Parameter _17_0_5_edc0357_1345510114784_30882_15107 = null;

            public IntegerParameter _17_0_5_edc0357_1345510114784_286838_15108 = null;

            public ConstraintExpression constraint40 = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114784_286838_15108Dependency = null;

            public Effect effect41 = null;

            public Effect effect42 = null;

            public void init_17_0_5_edc0357_1345510114027_379047_14205Members() {
                try {
                    _17_0_5_edc0357_1345510114027_337563_14204_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114027_337563_14204_endTime", this);
                    _17_0_5_edc0357_1345510114784_30882_15107 = new Parameter("_17_0_5_edc0357_1345510114784_30882_15107", null, null, this);
                    _17_0_5_edc0357_1345510114784_286838_15108 = new IntegerParameter("_17_0_5_edc0357_1345510114784_286838_15108", this);
                    constraint40 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114027_337563_14204_endTime)));
                    _17_0_5_edc0357_1345510114784_286838_15108Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114784_286838_15108, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114028_727317_14215, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    effect41 = new EffectFunction(new FunctionCall((Object) sig_17_0_5_edc0357_1346280185062_487679_12810, Utils.getMethodForArgTypes("ObjectFlow<Object>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { true, startTime }));
                    effect42 = new EffectFunction(new FunctionCall((Object) ((Power_System) x).ss_17_0_5_edc0357_1345510113565_310611_13783_receiveMeterReading.getValue(), Utils.getMethodForArgTypes("Customer._17_0_5_edc0357_1345510113556_623276_13775._17_0_5_edc0357_1345510114027_379047_14205", "generated", "send"), new Object[] { x.new SignalreceiveMeterReading(_17_0_5_edc0357_1345510114784_286838_15108.getValue()), endTime }));
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

            public Effect effect45 = null;

            public void init_17_0_5_edc0357_1345510114027_851146_14206Members() {
                try {
                    _17_0_5_edc0357_1345510114027_454308_14202_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114027_454308_14202_endTime", this);
                    objectToPass = new IntegerParameter("objectToPass", this);
                    constraint43 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114027_454308_14202_endTime)));
                    effect44 = new EffectFunction(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114028_129199_14214, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                    effect45 = new EffectFunction(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114028_727317_14215, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114027_851146_14206Collections() {
                parameters.add(_17_0_5_edc0357_1345510114027_454308_14202_endTime);
                parameters.add(objectToPass);
                constraintExpressions.add(constraint43);
                Set<Effect> effectsForsig_17_0_5_edc0357_1345510114028_129199_14214 = new HashSet<Effect>();
                effectsForsig_17_0_5_edc0357_1345510114028_129199_14214.add(effect44);
                effects.put((Parameter<?>) sig_17_0_5_edc0357_1345510114028_129199_14214, effectsForsig_17_0_5_edc0357_1345510114028_129199_14214);
                Set<Effect> effectsForsig_17_0_5_edc0357_1345510114028_727317_14215 = new HashSet<Effect>();
                effectsForsig_17_0_5_edc0357_1345510114028_727317_14215.add(effect45);
                effects.put((Parameter<?>) sig_17_0_5_edc0357_1345510114028_727317_14215, effectsForsig_17_0_5_edc0357_1345510114028_727317_14215);
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

        public Parameter sig_17_0_5_edc0357_1345510114793_324647_15121 = null;

        public Parameter sig_17_0_5_edc0357_1345510114089_958429_14264 = null;

        public Parameter sig_17_0_5_edc0357_1345510114089_103639_14268 = null;

        public IntegerParameter invoke_time = null;

        public Parameter sig_17_0_5_edc0357_1345510114089_297842_14265 = null;

        public Parameter sig_17_0_5_edc0357_1345510114089_58426_14269 = null;

        public ElaborationRule elaborationRule48 = null;

        public void init_17_0_5_edc0357_1345510113557_625793_13776Members() {
            try {
                sig_17_0_5_edc0357_1345510114793_324647_15121 = new Parameter("sig_17_0_5_edc0357_1345510114793_324647_15121", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114793_324647_15121"), this);
                sig_17_0_5_edc0357_1345510114089_958429_14264 = new Parameter("sig_17_0_5_edc0357_1345510114089_958429_14264", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114089_958429_14264"), this);
                sig_17_0_5_edc0357_1345510114089_103639_14268 = new Parameter("sig_17_0_5_edc0357_1345510114089_103639_14268", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114089_103639_14268"), this);
                invoke_time = new IntegerParameter("invoke_time", this);
                sig_17_0_5_edc0357_1345510114089_297842_14265 = new Parameter("sig_17_0_5_edc0357_1345510114089_297842_14265", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114089_297842_14265"), this);
                sig_17_0_5_edc0357_1345510114089_58426_14269 = new Parameter("sig_17_0_5_edc0357_1345510114089_58426_14269", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114089_58426_14269"), this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void init_17_0_5_edc0357_1345510113557_625793_13776Collections() {
            parameters.add(sig_17_0_5_edc0357_1345510114793_324647_15121);
            parameters.add(sig_17_0_5_edc0357_1345510114089_958429_14264);
            parameters.add(sig_17_0_5_edc0357_1345510114089_103639_14268);
            parameters.add(invoke_time);
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

            public Parameter _17_0_5_edc0357_1345510114793_215722_15122 = null;

            public ConstraintExpression constraint49 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114088_487345_14255_existsDependency = null;

            public ElaborationRule elaborationRule50 = null;

            public void init_17_0_5_edc0357_1345510114087_484087_14249Members() {
                try {
                    _17_0_5_edc0357_1348000726471_590820_13942_endTime = new IntegerParameter("_17_0_5_edc0357_1348000726471_590820_13942_endTime", this);
                    _17_0_5_edc0357_1345510114088_487345_14255_exists = new BooleanParameter("_17_0_5_edc0357_1345510114088_487345_14255_exists", this);
                    _17_0_5_edc0357_1345510114793_215722_15122 = new Parameter("_17_0_5_edc0357_1345510114793_215722_15122", null, Customer.this, this);
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

            public BooleanParameter _17_0_5_edc0357_1345510114088_357828_14256_exists = null;

            public IntegerParameter _17_0_5_edc0357_1345510114794_965367_15124 = null;

            public ConstraintExpression constraint51 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114088_357828_14256_existsDependency = null;

            public ElaborationRule elaborationRule52 = null;

            public void init_17_0_5_edc0357_1345510114087_224243_14250Members() {
                try {
                    _17_0_5_edc0357_1348000726471_590820_13942_endTime = new IntegerParameter("_17_0_5_edc0357_1348000726471_590820_13942_endTime", this);
                    _17_0_5_edc0357_1345510114088_357828_14256_exists = new BooleanParameter("_17_0_5_edc0357_1345510114088_357828_14256_exists", this);
                    _17_0_5_edc0357_1345510114794_965367_15124 = new IntegerParameter("_17_0_5_edc0357_1345510114794_965367_15124", this);
                    constraint51 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1348000726471_590820_13942_endTime)));
                    _17_0_5_edc0357_1345510114088_357828_14256_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114088_357828_14256_exists, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114089_958429_14264, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114087_224243_14250Collections() {
                parameters.add(_17_0_5_edc0357_1348000726471_590820_13942_endTime);
                parameters.add(_17_0_5_edc0357_1345510114088_357828_14256_exists);
                parameters.add(_17_0_5_edc0357_1345510114794_965367_15124);
                constraintExpressions.add(constraint51);
                dependencies.add(_17_0_5_edc0357_1345510114088_357828_14256_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510114087_224243_14250Elaborations() {
                Expression<?>[] arguments52 = new Expression<?>[2];
                arguments52[0] = new Expression<Integer>(endTime);
                arguments52[1] = new Expression<Integer>(_17_0_5_edc0357_1345510114794_965367_15124);
                Expression<Boolean> condition52 = new Expression<Boolean>(_17_0_5_edc0357_1345510114088_357828_14256_exists);
                elaborationRule52 = addElaborationRule(condition52, _17_0_5_edc0357_1345510113557_625793_13776.this, Customer._17_0_5_edc0357_1345510113557_625793_13776._17_0_5_edc0357_1345510114088_357828_14256.class, "_CallBehaviorAction_changePowerUsage", arguments52);
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

            public Parameter _17_0_5_edc0357_1345510114796_230370_15126 = null;

            public BooleanParameter _17_0_5_edc0357_1345510114088_571377_14257_exists = null;

            public IntegerParameter _17_0_5_edc0357_1345510114088_487345_14255_endTime = null;

            public ConstraintExpression constraint53 = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114795_154720_15125Dependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114088_571377_14257_existsDependency = null;

            public Effect effect54 = null;

            public ElaborationRule elaborationRule55 = null;

            public void init_17_0_5_edc0357_1345510114087_795044_14251Members() {
                try {
                    _17_0_5_edc0357_1345510114795_154720_15125 = new IntegerParameter("_17_0_5_edc0357_1345510114795_154720_15125", this);
                    _17_0_5_edc0357_1345510114796_230370_15126 = new Parameter("_17_0_5_edc0357_1345510114796_230370_15126", null, null, this);
                    _17_0_5_edc0357_1345510114088_571377_14257_exists = new BooleanParameter("_17_0_5_edc0357_1345510114088_571377_14257_exists", this);
                    _17_0_5_edc0357_1345510114088_487345_14255_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114088_487345_14255_endTime", this);
                    constraint53 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114088_487345_14255_endTime)));
                    _17_0_5_edc0357_1345510114795_154720_15125Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114795_154720_15125, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114089_103639_14268, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1345510114088_571377_14257_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114088_571377_14257_exists, new Functions.And(new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114089_58426_14269, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114089_297842_14265, Utils.getMethodForArgTypes("ObjectFlow<Customer>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) }))));
                    effect54 = new EffectFunction(new FunctionCall((Object) usage__17_0_5_edc0357_1345510113561_560076_13779, Utils.getMethodForArgTypes("TimeVaryingMap<Integer>", "generated", "setValue", java.lang.Integer.class, java.lang.Object.class), new Object[] { startTime, _17_0_5_edc0357_1345510114795_154720_15125 }));
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
                Set<Effect> effectsForusage__17_0_5_edc0357_1345510113561_560076_13779 = new HashSet<Effect>();
                effectsForusage__17_0_5_edc0357_1345510113561_560076_13779.add(effect54);
                effects.put((Parameter<?>) usage__17_0_5_edc0357_1345510113561_560076_13779, effectsForusage__17_0_5_edc0357_1345510113561_560076_13779);
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

            public Parameter _17_0_5_edc0357_1345510114797_445623_15128 = null;

            public IntegerParameter _17_0_5_edc0357_1345510114088_487345_14255_endTime = null;

            public ConstraintExpression constraint58 = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114796_365668_15127Dependency = null;

            public Effect effect59 = null;

            public void init_17_0_5_edc0357_1345510114087_731407_14254Members() {
                try {
                    _17_0_5_edc0357_1345510114796_365668_15127 = new IntegerParameter("_17_0_5_edc0357_1345510114796_365668_15127", this);
                    _17_0_5_edc0357_1345510114797_445623_15128 = new Parameter("_17_0_5_edc0357_1345510114797_445623_15128", null, null, this);
                    _17_0_5_edc0357_1345510114088_487345_14255_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114088_487345_14255_endTime", this);
                    constraint58 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114088_487345_14255_endTime)));
                    _17_0_5_edc0357_1345510114796_365668_15127Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114796_365668_15127, new Expression(new FunctionCall((Object) ((Customer) _17_0_5_edc0357_1345510114797_445623_15128.getValue()).cap__17_0_5_edc0357_1345510113566_55150_13784.getValue(), Utils.getMethodForArgTypes("Customer._17_0_5_edc0357_1345510113557_625793_13776._17_0_5_edc0357_1345510114087_731407_14254", "generated", "getValue"), new Object[] { new Expression<Integer>(startTime) })));
                    effect59 = new EffectFunction(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114089_958429_14264, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { _17_0_5_edc0357_1345510114796_365668_15127, startTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114087_731407_14254Collections() {
                parameters.add(_17_0_5_edc0357_1345510114796_365668_15127);
                parameters.add(_17_0_5_edc0357_1345510114797_445623_15128);
                parameters.add(_17_0_5_edc0357_1345510114088_487345_14255_endTime);
                constraintExpressions.add(constraint58);
                dependencies.add(_17_0_5_edc0357_1345510114796_365668_15127Dependency);
                Set<Effect> effectsForsig_17_0_5_edc0357_1345510114089_958429_14264 = new HashSet<Effect>();
                effectsForsig_17_0_5_edc0357_1345510114089_958429_14264.add(effect59);
                effects.put((Parameter<?>) sig_17_0_5_edc0357_1345510114089_958429_14264, effectsForsig_17_0_5_edc0357_1345510114089_958429_14264);
            }

            public void init_17_0_5_edc0357_1345510114087_731407_14254Elaborations() {
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

            public Parameter objectToPass = null;

            public BooleanParameter _17_0_5_edc0357_1345510114087_731407_14254_exists = null;

            public IntegerParameter _17_0_5_edc0357_1345510114087_484087_14249_endTime = null;

            public ConstraintExpression constraint60 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114087_795044_14251_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114087_731407_14254_existsDependency = null;

            public Effect effect61 = null;

            public ElaborationRule elaborationRule62 = null;

            public ElaborationRule elaborationRule63 = null;

            public void init_17_0_5_edc0357_1345510114088_487345_14255Members() {
                try {
                    _17_0_5_edc0357_1345510114087_795044_14251_exists = new BooleanParameter("_17_0_5_edc0357_1345510114087_795044_14251_exists", this);
                    objectToPass = new Parameter("objectToPass", null, null, this);
                    _17_0_5_edc0357_1345510114087_731407_14254_exists = new BooleanParameter("_17_0_5_edc0357_1345510114087_731407_14254_exists", this);
                    _17_0_5_edc0357_1345510114087_484087_14249_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114087_484087_14249_endTime", this);
                    constraint60 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114087_484087_14249_endTime)));
                    _17_0_5_edc0357_1345510114087_795044_14251_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114087_795044_14251_exists, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114089_103639_14268, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })));
                    _17_0_5_edc0357_1345510114087_731407_14254_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114087_731407_14254_exists, new Expression<Boolean>(true));
                    effect61 = new EffectFunction(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114089_297842_14265, Utils.getMethodForArgTypes("ObjectFlow<Customer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114088_487345_14255Collections() {
                parameters.add(_17_0_5_edc0357_1345510114087_795044_14251_exists);
                parameters.add(objectToPass);
                parameters.add(_17_0_5_edc0357_1345510114087_731407_14254_exists);
                parameters.add(_17_0_5_edc0357_1345510114087_484087_14249_endTime);
                constraintExpressions.add(constraint60);
                dependencies.add(_17_0_5_edc0357_1345510114087_795044_14251_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510114087_731407_14254_existsDependency);
                Set<Effect> effectsForsig_17_0_5_edc0357_1345510114089_297842_14265 = new HashSet<Effect>();
                effectsForsig_17_0_5_edc0357_1345510114089_297842_14265.add(effect61);
                effects.put((Parameter<?>) sig_17_0_5_edc0357_1345510114089_297842_14265, effectsForsig_17_0_5_edc0357_1345510114089_297842_14265);
            }

            public void init_17_0_5_edc0357_1345510114088_487345_14255Elaborations() {
                Expression<?>[] arguments62 = new Expression<?>[2];
                arguments62[0] = new Expression<Integer>(endTime);
                arguments62[1] = new Expression<Customer>(objectToPass);
                Expression<Boolean> condition62 = new Expression<Boolean>(_17_0_5_edc0357_1345510114087_731407_14254_exists);
                elaborationRule62 = addElaborationRule(condition62, _17_0_5_edc0357_1345510113557_625793_13776.this, Customer._17_0_5_edc0357_1345510113557_625793_13776._17_0_5_edc0357_1345510114087_731407_14254.class, "_ReadStructuralFeatureAction_changePowerUsage", arguments62);
                Expression<?>[] arguments63 = new Expression<?>[2];
                arguments63[0] = new Expression<Integer>(endTime);
                arguments63[1] = new Expression<Customer>(objectToPass);
                Expression<Boolean> condition63 = new Expression<Boolean>(_17_0_5_edc0357_1345510114087_795044_14251_exists);
                elaborationRule63 = addElaborationRule(condition63, _17_0_5_edc0357_1345510113557_625793_13776.this, Customer._17_0_5_edc0357_1345510113557_625793_13776._17_0_5_edc0357_1345510114087_795044_14251.class, "_AddStructuralFeatureValueAction_changePowerUsage", arguments63);
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

            public BooleanParameter _17_0_5_edc0357_1345510114088_999820_14258_exists = null;

            public IntegerParameter _17_0_5_edc0357_1345510114792_358855_15120 = null;

            public IntegerParameter new_load = null;

            public IntegerParameter _17_0_5_edc0357_1345510114791_46408_15119 = null;

            public IntegerParameter cap = null;

            public IntegerParameter desired = null;

            public IntegerParameter _17_0_5_edc0357_1345510114798_618420_15129 = null;

            public IntegerParameter _17_0_5_edc0357_1345510114087_224243_14250_endTime = null;

            public ConstraintExpression constraint64 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114088_999820_14258_existsDependency = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114792_358855_15120Dependency = null;

            public Dependency< Integer > new_loadDependency = null;

            public Dependency< Integer > capDependency = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114798_618420_15129Dependency = null;

            public Dependency< Integer > desiredDependency = null;

            public ElaborationRule elaborationRule65 = null;

            public void init_17_0_5_edc0357_1345510114088_357828_14256Members() {
                try {
                    _17_0_5_edc0357_1345510114088_999820_14258_exists = new BooleanParameter("_17_0_5_edc0357_1345510114088_999820_14258_exists", this);
                    _17_0_5_edc0357_1345510114792_358855_15120 = new IntegerParameter("_17_0_5_edc0357_1345510114792_358855_15120", this);
                    new_load = new IntegerParameter("new_load", this);
                    _17_0_5_edc0357_1345510114791_46408_15119 = new IntegerParameter("_17_0_5_edc0357_1345510114791_46408_15119", this);
                    cap = new IntegerParameter("cap", this);
                    desired = new IntegerParameter("desired", this);
                    _17_0_5_edc0357_1345510114798_618420_15129 = new IntegerParameter("_17_0_5_edc0357_1345510114798_618420_15129", this);
                    _17_0_5_edc0357_1345510114087_224243_14250_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114087_224243_14250_endTime", this);
                    constraint64 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114087_224243_14250_endTime)));
                    _17_0_5_edc0357_1345510114088_999820_14258_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114088_999820_14258_exists, new Expression<Boolean>(true));
                    _17_0_5_edc0357_1345510114792_358855_15120Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114792_358855_15120, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114089_958429_14264, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    new_loadDependency = new Dependency<Integer>(new_load, new Expression(new FunctionCall((Object) this, Utils.getMethodForArgTypes("Customer._17_0_5_edc0357_1345510113557_625793_13776._17_0_5_edc0357_1345510114088_357828_14256", "generated", "min"), new Object[] { new Expression<Integer>(desired), new Expression<Integer>(cap) })));
                    capDependency = new Dependency<Integer>(cap, new Expression<Integer>(_17_0_5_edc0357_1345510114792_358855_15120));
                    _17_0_5_edc0357_1345510114798_618420_15129Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114798_618420_15129, new Expression<Integer>(new_load));
                    desiredDependency = new Dependency<Integer>(desired, new Expression<Integer>(_17_0_5_edc0357_1345510114791_46408_15119));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114088_357828_14256Collections() {
                parameters.add(_17_0_5_edc0357_1345510114088_999820_14258_exists);
                parameters.add(_17_0_5_edc0357_1345510114792_358855_15120);
                parameters.add(new_load);
                parameters.add(_17_0_5_edc0357_1345510114791_46408_15119);
                parameters.add(cap);
                parameters.add(desired);
                parameters.add(_17_0_5_edc0357_1345510114798_618420_15129);
                parameters.add(_17_0_5_edc0357_1345510114087_224243_14250_endTime);
                constraintExpressions.add(constraint64);
                dependencies.add(_17_0_5_edc0357_1345510114088_999820_14258_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510114792_358855_15120Dependency);
                dependencies.add(new_loadDependency);
                dependencies.add(capDependency);
                dependencies.add(_17_0_5_edc0357_1345510114798_618420_15129Dependency);
                dependencies.add(desiredDependency);
            }

            public void init_17_0_5_edc0357_1345510114088_357828_14256Elaborations() {
                Expression<?>[] arguments65 = new Expression<?>[2];
                arguments65[0] = new Expression<Integer>(endTime);
                arguments65[1] = new Expression<Integer>(_17_0_5_edc0357_1345510114798_618420_15129);
                Expression<Boolean> condition65 = new Expression<Boolean>(_17_0_5_edc0357_1345510114088_999820_14258_exists);
                elaborationRule65 = addElaborationRule(condition65, _17_0_5_edc0357_1345510113557_625793_13776.this, Customer._17_0_5_edc0357_1345510113557_625793_13776._17_0_5_edc0357_1345510114088_999820_14258.class, "_ForkNode_changePowerUsage", arguments65);
            }

            public _17_0_5_edc0357_1345510114088_357828_14256() {
                super();
                init_17_0_5_edc0357_1345510114088_357828_14256Members();
                init_17_0_5_edc0357_1345510114088_357828_14256Collections();
                init_17_0_5_edc0357_1345510114088_357828_14256Elaborations();
            }

            public _17_0_5_edc0357_1345510114088_357828_14256(Expression<Integer> _17_0_5_edc0357_1345510114087_224243_14250_endTime, Expression<Integer> _17_0_5_edc0357_1345510114791_46408_15119) {
                super();
                init_17_0_5_edc0357_1345510114088_357828_14256Members();
                init_17_0_5_edc0357_1345510114088_357828_14256Collections();
                addDependency(this._17_0_5_edc0357_1345510114087_224243_14250_endTime, _17_0_5_edc0357_1345510114087_224243_14250_endTime);
                addDependency(this._17_0_5_edc0357_1345510114791_46408_15119, _17_0_5_edc0357_1345510114791_46408_15119);
                init_17_0_5_edc0357_1345510114088_357828_14256Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114088_571377_14257 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1345510114801_45672_15133 = null;

            public IntegerParameter _17_0_5_edc0357_1345510114087_795044_14251_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1345510114087_791191_14253_exists = null;

            public Parameter _17_0_5_edc0357_1345510114800_96164_15132 = null;

            public ConstraintExpression constraint66 = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114801_45672_15133Dependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114087_791191_14253_existsDependency = null;

            public Dependency< Customer > _17_0_5_edc0357_1345510114800_96164_15132Dependency = null;

            public Effect effect67 = null;

            public ElaborationRule elaborationRule68 = null;

            public void init_17_0_5_edc0357_1345510114088_571377_14257Members() {
                try {
                    _17_0_5_edc0357_1345510114801_45672_15133 = new IntegerParameter("_17_0_5_edc0357_1345510114801_45672_15133", this);
                    _17_0_5_edc0357_1345510114087_795044_14251_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114087_795044_14251_endTime", this);
                    _17_0_5_edc0357_1345510114087_791191_14253_exists = new BooleanParameter("_17_0_5_edc0357_1345510114087_791191_14253_exists", this);
                    _17_0_5_edc0357_1345510114800_96164_15132 = new Parameter("_17_0_5_edc0357_1345510114800_96164_15132", null, null, this);
                    constraint66 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114087_795044_14251_endTime)));
                    _17_0_5_edc0357_1345510114801_45672_15133Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114801_45672_15133, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114089_58426_14269, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1345510114087_791191_14253_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114087_791191_14253_exists, new Expression<Boolean>(true));
                    _17_0_5_edc0357_1345510114800_96164_15132Dependency = new Dependency<Customer>(_17_0_5_edc0357_1345510114800_96164_15132, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114089_297842_14265, Utils.getMethodForArgTypes("ObjectFlow<Customer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    effect67 = new EffectFunction(new FunctionCall((Object) ((Power_System) x).ss_17_0_5_edc0357_1345510113562_98635_13780_changeLoadValue.getValue(), Utils.getMethodForArgTypes("Customer._17_0_5_edc0357_1345510113557_625793_13776._17_0_5_edc0357_1345510114088_571377_14257", "generated", "send"), new Object[] { x.new SignalchangeLoadValue(_17_0_5_edc0357_1345510114801_45672_15133.getValue()), endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114088_571377_14257Collections() {
                parameters.add(_17_0_5_edc0357_1345510114801_45672_15133);
                parameters.add(_17_0_5_edc0357_1345510114087_795044_14251_endTime);
                parameters.add(_17_0_5_edc0357_1345510114087_791191_14253_exists);
                parameters.add(_17_0_5_edc0357_1345510114800_96164_15132);
                constraintExpressions.add(constraint66);
                dependencies.add(_17_0_5_edc0357_1345510114801_45672_15133Dependency);
                dependencies.add(_17_0_5_edc0357_1345510114087_791191_14253_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510114800_96164_15132Dependency);
            }

            public void init_17_0_5_edc0357_1345510114088_571377_14257Elaborations() {
                Expression<?>[] arguments68 = new Expression<?>[1];
                arguments68[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition68 = new Expression<Boolean>(_17_0_5_edc0357_1345510114087_791191_14253_exists);
                elaborationRule68 = addElaborationRule(condition68, _17_0_5_edc0357_1345510113557_625793_13776.this, Customer._17_0_5_edc0357_1345510113557_625793_13776._17_0_5_edc0357_1345510114087_791191_14253.class, "_ActivityFinalNode_changePowerUsage", arguments68);
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

            public IntegerParameter _17_0_5_edc0357_1345510114088_357828_14256_endTime = null;

            public IntegerParameter objectToPass = null;

            public ConstraintExpression constraint69 = null;

            public Effect effect70 = null;

            public Effect effect71 = null;

            public void init_17_0_5_edc0357_1345510114088_999820_14258Members() {
                try {
                    _17_0_5_edc0357_1345510114088_357828_14256_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114088_357828_14256_endTime", this);
                    objectToPass = new IntegerParameter("objectToPass", this);
                    constraint69 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114088_357828_14256_endTime)));
                    effect70 = new EffectFunction(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114089_103639_14268, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                    effect71 = new EffectFunction(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114089_58426_14269, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114088_999820_14258Collections() {
                parameters.add(_17_0_5_edc0357_1345510114088_357828_14256_endTime);
                parameters.add(objectToPass);
                constraintExpressions.add(constraint69);
                Set<Effect> effectsForsig_17_0_5_edc0357_1345510114089_103639_14268 = new HashSet<Effect>();
                effectsForsig_17_0_5_edc0357_1345510114089_103639_14268.add(effect70);
                effects.put((Parameter<?>) sig_17_0_5_edc0357_1345510114089_103639_14268, effectsForsig_17_0_5_edc0357_1345510114089_103639_14268);
                Set<Effect> effectsForsig_17_0_5_edc0357_1345510114089_58426_14269 = new HashSet<Effect>();
                effectsForsig_17_0_5_edc0357_1345510114089_58426_14269.add(effect71);
                effects.put((Parameter<?>) sig_17_0_5_edc0357_1345510114089_58426_14269, effectsForsig_17_0_5_edc0357_1345510114089_58426_14269);
            }

            public void init_17_0_5_edc0357_1345510114088_999820_14258Elaborations() {
            }

            public _17_0_5_edc0357_1345510114088_999820_14258() {
                super();
                init_17_0_5_edc0357_1345510114088_999820_14258Members();
                init_17_0_5_edc0357_1345510114088_999820_14258Collections();
                init_17_0_5_edc0357_1345510114088_999820_14258Elaborations();
            }

            public _17_0_5_edc0357_1345510114088_999820_14258(Expression<Integer> _17_0_5_edc0357_1345510114088_357828_14256_endTime, Expression<Integer> objectToPass) {
                super();
                init_17_0_5_edc0357_1345510114088_999820_14258Members();
                init_17_0_5_edc0357_1345510114088_999820_14258Collections();
                addDependency(this._17_0_5_edc0357_1345510114088_357828_14256_endTime, _17_0_5_edc0357_1345510114088_357828_14256_endTime);
                addDependency(this.objectToPass, objectToPass);
                init_17_0_5_edc0357_1345510114088_999820_14258Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1348000726471_590820_13942 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1345510114087_35215_14252_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1345510114087_224243_14250_exists = null;

            public BooleanParameter _17_0_5_edc0357_1345510114087_484087_14249_exists = null;

            public ConstraintExpression constraint72 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114087_224243_14250_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114087_484087_14249_existsDependency = null;

            public ElaborationRule elaborationRule73 = null;

            public ElaborationRule elaborationRule74 = null;

            public void init_17_0_5_edc0357_1348000726471_590820_13942Members() {
                try {
                    _17_0_5_edc0357_1345510114087_35215_14252_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114087_35215_14252_endTime", this);
                    _17_0_5_edc0357_1345510114087_224243_14250_exists = new BooleanParameter("_17_0_5_edc0357_1345510114087_224243_14250_exists", this);
                    _17_0_5_edc0357_1345510114087_484087_14249_exists = new BooleanParameter("_17_0_5_edc0357_1345510114087_484087_14249_exists", this);
                    constraint72 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114087_35215_14252_endTime)));
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
                constraintExpressions.add(constraint72);
                dependencies.add(_17_0_5_edc0357_1345510114087_224243_14250_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510114087_484087_14249_existsDependency);
            }

            public void init_17_0_5_edc0357_1348000726471_590820_13942Elaborations() {
                Expression<?>[] arguments73 = new Expression<?>[1];
                arguments73[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition73 = new Expression<Boolean>(_17_0_5_edc0357_1345510114087_484087_14249_exists);
                elaborationRule73 = addElaborationRule(condition73, _17_0_5_edc0357_1345510113557_625793_13776.this, Customer._17_0_5_edc0357_1345510113557_625793_13776._17_0_5_edc0357_1345510114087_484087_14249.class, "_ReadSelfAction_changePowerUsage", arguments73);
                Expression<?>[] arguments74 = new Expression<?>[1];
                arguments74[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition74 = new Expression<Boolean>(_17_0_5_edc0357_1345510114087_224243_14250_exists);
                elaborationRule74 = addElaborationRule(condition74, _17_0_5_edc0357_1345510113557_625793_13776.this, Customer._17_0_5_edc0357_1345510113557_625793_13776._17_0_5_edc0357_1345510114087_224243_14250.class, "_ValueSpecificationAction_changePowerUsage", arguments74);
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

        public Parameter sig_17_0_5_edc0357_1345510114105_705998_14305 = null;

        public Parameter sig_17_0_5_edc0357_1345510114105_129800_14307 = null;

        public Parameter sig_17_0_5_edc0357_1345510114106_840970_14313 = null;

        public Parameter sig_17_0_5_edc0357_1345510114105_724841_14306 = null;

        public IntegerParameter invoke_time = null;

        public Parameter sig_17_0_5_edc0357_1345510114105_938474_14303 = null;

        public ElaborationRule elaborationRule75 = null;

        public void init_17_0_5_edc0357_1345510113559_180117_13777Members() {
            try {
                sig_17_0_5_edc0357_1345510114105_705998_14305 = new Parameter("sig_17_0_5_edc0357_1345510114105_705998_14305", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114105_705998_14305"), this);
                sig_17_0_5_edc0357_1345510114105_129800_14307 = new Parameter("sig_17_0_5_edc0357_1345510114105_129800_14307", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114105_129800_14307"), this);
                sig_17_0_5_edc0357_1345510114106_840970_14313 = new Parameter("sig_17_0_5_edc0357_1345510114106_840970_14313", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114106_840970_14313"), this);
                sig_17_0_5_edc0357_1345510114105_724841_14306 = new Parameter("sig_17_0_5_edc0357_1345510114105_724841_14306", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114105_724841_14306"), this);
                invoke_time = new IntegerParameter("invoke_time", this);
                sig_17_0_5_edc0357_1345510114105_938474_14303 = new Parameter("sig_17_0_5_edc0357_1345510114105_938474_14303", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114105_938474_14303"), this);
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
            Expression<?>[] arguments75 = new Expression<?>[1];
            arguments75[0] = new Expression<Integer>(invoke_time);
            Expression<Boolean> condition75 = new Expression<Boolean>(true);
            elaborationRule75 = addElaborationRule(condition75, _17_0_5_edc0357_1345510113559_180117_13777.this, Customer._17_0_5_edc0357_1345510113559_180117_13777._17_0_5_edc0357_1345510114103_561395_14296.class, "_InitialNode_initialize", arguments75);
        }

        public _17_0_5_edc0357_1345510113559_180117_13777() {
            super();
            init_17_0_5_edc0357_1345510113559_180117_13777Members();
            init_17_0_5_edc0357_1345510113559_180117_13777Collections();
            init_17_0_5_edc0357_1345510113559_180117_13777Elaborations();
        }

        public class _17_0_5_edc0357_1345510114103_775997_14293 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1345510114104_596689_14298_exists = null;

            public Parameter _17_0_5_edc0357_1345510114806_956292_15147 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114104_596689_14298_existsDependency = null;

            public ElaborationRule elaborationRule76 = null;

            public void init_17_0_5_edc0357_1345510114103_775997_14293Members() {
                try {
                    _17_0_5_edc0357_1345510114104_596689_14298_exists = new BooleanParameter("_17_0_5_edc0357_1345510114104_596689_14298_exists", this);
                    _17_0_5_edc0357_1345510114806_956292_15147 = new Parameter("_17_0_5_edc0357_1345510114806_956292_15147", null, Customer.this, this);
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
                Expression<?>[] arguments76 = new Expression<?>[2];
                arguments76[0] = new Expression<Integer>(endTime);
                arguments76[1] = new Expression<Customer>(_17_0_5_edc0357_1345510114806_956292_15147);
                Expression<Boolean> condition76 = new Expression<Boolean>(_17_0_5_edc0357_1345510114104_596689_14298_exists);
                elaborationRule76 = addElaborationRule(condition76, _17_0_5_edc0357_1345510113559_180117_13777.this, Customer._17_0_5_edc0357_1345510113559_180117_13777._17_0_5_edc0357_1345510114104_596689_14298.class, "_ForkNode_initialize", arguments76);
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

            public Effect effect77 = null;

            public void init_17_0_5_edc0357_1345510114103_36875_14294Members() {
                try {
                    _17_0_5_edc0357_1345510114807_983002_15149 = new IntegerParameter("_17_0_5_edc0357_1345510114807_983002_15149", this);
                    effect77 = new EffectFunction(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114105_938474_14303, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { _17_0_5_edc0357_1345510114807_983002_15149, startTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114103_36875_14294Collections() {
                parameters.add(_17_0_5_edc0357_1345510114807_983002_15149);
                Set<Effect> effectsForsig_17_0_5_edc0357_1345510114105_938474_14303 = new HashSet<Effect>();
                effectsForsig_17_0_5_edc0357_1345510114105_938474_14303.add(effect77);
                effects.put((Parameter<?>) sig_17_0_5_edc0357_1345510114105_938474_14303, effectsForsig_17_0_5_edc0357_1345510114105_938474_14303);
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

            public Parameter _17_0_5_edc0357_1345510114809_763294_15151 = null;

            public ConstraintExpression constraint78 = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114808_431003_15150Dependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114104_720146_14302_existsDependency = null;

            public Dependency< Customer > _17_0_5_edc0357_1345510114809_763294_15151Dependency = null;

            public Effect effect79 = null;

            public ElaborationRule elaborationRule80 = null;

            public void init_17_0_5_edc0357_1345510114103_810338_14295Members() {
                try {
                    _17_0_5_edc0357_1345510114808_431003_15150 = new IntegerParameter("_17_0_5_edc0357_1345510114808_431003_15150", this);
                    _17_0_5_edc0357_1345510114104_812599_14301_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114104_812599_14301_endTime", this);
                    _17_0_5_edc0357_1345510114104_720146_14302_exists = new BooleanParameter("_17_0_5_edc0357_1345510114104_720146_14302_exists", this);
                    _17_0_5_edc0357_1345510114809_763294_15151 = new Parameter("_17_0_5_edc0357_1345510114809_763294_15151", null, null, this);
                    constraint78 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114104_812599_14301_endTime)));
                    _17_0_5_edc0357_1345510114808_431003_15150Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114808_431003_15150, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114105_938474_14303, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1345510114104_720146_14302_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114104_720146_14302_exists, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114106_840970_14313, Utils.getMethodForArgTypes("ObjectFlow<Object>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })));
                    _17_0_5_edc0357_1345510114809_763294_15151Dependency = new Dependency<Customer>(_17_0_5_edc0357_1345510114809_763294_15151, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114105_705998_14305, Utils.getMethodForArgTypes("ObjectFlow<Customer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    effect79 = new EffectFunction(new FunctionCall((Object) usage__17_0_5_edc0357_1345510113561_560076_13779, Utils.getMethodForArgTypes("TimeVaryingMap<Integer>", "generated", "setValue", java.lang.Integer.class, java.lang.Object.class), new Object[] { startTime, _17_0_5_edc0357_1345510114808_431003_15150 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114103_810338_14295Collections() {
                parameters.add(_17_0_5_edc0357_1345510114808_431003_15150);
                parameters.add(_17_0_5_edc0357_1345510114104_812599_14301_endTime);
                parameters.add(_17_0_5_edc0357_1345510114104_720146_14302_exists);
                parameters.add(_17_0_5_edc0357_1345510114809_763294_15151);
                constraintExpressions.add(constraint78);
                dependencies.add(_17_0_5_edc0357_1345510114808_431003_15150Dependency);
                dependencies.add(_17_0_5_edc0357_1345510114104_720146_14302_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510114809_763294_15151Dependency);
                Set<Effect> effectsForusage__17_0_5_edc0357_1345510113561_560076_13779 = new HashSet<Effect>();
                effectsForusage__17_0_5_edc0357_1345510113561_560076_13779.add(effect79);
                effects.put((Parameter<?>) usage__17_0_5_edc0357_1345510113561_560076_13779, effectsForusage__17_0_5_edc0357_1345510113561_560076_13779);
            }

            public void init_17_0_5_edc0357_1345510114103_810338_14295Elaborations() {
                Expression<?>[] arguments80 = new Expression<?>[1];
                arguments80[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition80 = new Expression<Boolean>(_17_0_5_edc0357_1345510114104_720146_14302_exists);
                elaborationRule80 = addElaborationRule(condition80, _17_0_5_edc0357_1345510113559_180117_13777.this, Customer._17_0_5_edc0357_1345510113559_180117_13777._17_0_5_edc0357_1345510114104_720146_14302.class, "_JoinNode_initialize", arguments80);
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

            public ElaborationRule elaborationRule81 = null;

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
                Expression<?>[] arguments81 = new Expression<?>[1];
                arguments81[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition81 = new Expression<Boolean>(_17_0_5_edc0357_1345510114104_812599_14301_exists);
                elaborationRule81 = addElaborationRule(condition81, _17_0_5_edc0357_1345510113559_180117_13777.this, Customer._17_0_5_edc0357_1345510113559_180117_13777._17_0_5_edc0357_1345510114104_812599_14301.class, "_ForkNode_initialize", arguments81);
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

            public ConstraintExpression constraint82 = null;

            public void init_17_0_5_edc0357_1345510114103_977080_14297Members() {
                try {
                    _17_0_5_edc0357_1345510114104_720146_14302_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114104_720146_14302_endTime", this);
                    constraint82 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114104_720146_14302_endTime)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114103_977080_14297Collections() {
                parameters.add(_17_0_5_edc0357_1345510114104_720146_14302_endTime);
                constraintExpressions.add(constraint82);
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

            public Parameter objectToPass = null;

            public IntegerParameter _17_0_5_edc0357_1345510114103_775997_14293_endTime = null;

            public ConstraintExpression constraint83 = null;

            public Effect effect84 = null;

            public Effect effect85 = null;

            public void init_17_0_5_edc0357_1345510114104_596689_14298Members() {
                try {
                    objectToPass = new Parameter("objectToPass", null, null, this);
                    _17_0_5_edc0357_1345510114103_775997_14293_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114103_775997_14293_endTime", this);
                    constraint83 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114103_775997_14293_endTime)));
                    effect84 = new EffectFunction(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114105_705998_14305, Utils.getMethodForArgTypes("ObjectFlow<Customer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                    effect85 = new EffectFunction(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114105_129800_14307, Utils.getMethodForArgTypes("ObjectFlow<Customer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114104_596689_14298Collections() {
                parameters.add(objectToPass);
                parameters.add(_17_0_5_edc0357_1345510114103_775997_14293_endTime);
                constraintExpressions.add(constraint83);
                Set<Effect> effectsForsig_17_0_5_edc0357_1345510114105_129800_14307 = new HashSet<Effect>();
                effectsForsig_17_0_5_edc0357_1345510114105_129800_14307.add(effect85);
                effects.put((Parameter<?>) sig_17_0_5_edc0357_1345510114105_129800_14307, effectsForsig_17_0_5_edc0357_1345510114105_129800_14307);
                Set<Effect> effectsForsig_17_0_5_edc0357_1345510114105_705998_14305 = new HashSet<Effect>();
                effectsForsig_17_0_5_edc0357_1345510114105_705998_14305.add(effect84);
                effects.put((Parameter<?>) sig_17_0_5_edc0357_1345510114105_705998_14305, effectsForsig_17_0_5_edc0357_1345510114105_705998_14305);
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

            public Parameter _17_0_5_edc0357_1345510114810_534712_15153 = null;

            public IntegerParameter _17_0_5_edc0357_1345510114104_812599_14301_endTime = null;

            public IntegerParameter _17_0_5_edc0357_1345510114810_895741_15152 = null;

            public ConstraintExpression constraint86 = null;

            public Dependency< Customer > _17_0_5_edc0357_1345510114810_534712_15153Dependency = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114810_895741_15152Dependency = null;

            public Effect effect87 = null;

            public Effect effect88 = null;

            public void init_17_0_5_edc0357_1345510114104_663398_14299Members() {
                try {
                    _17_0_5_edc0357_1345510114810_534712_15153 = new Parameter("_17_0_5_edc0357_1345510114810_534712_15153", null, null, this);
                    _17_0_5_edc0357_1345510114104_812599_14301_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114104_812599_14301_endTime", this);
                    _17_0_5_edc0357_1345510114810_895741_15152 = new IntegerParameter("_17_0_5_edc0357_1345510114810_895741_15152", this);
                    constraint86 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114104_812599_14301_endTime)));
                    _17_0_5_edc0357_1345510114810_534712_15153Dependency = new Dependency<Customer>(_17_0_5_edc0357_1345510114810_534712_15153, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114105_129800_14307, Utils.getMethodForArgTypes("ObjectFlow<Customer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1345510114810_895741_15152Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114810_895741_15152, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114105_724841_14306, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    effect87 = new EffectFunction(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114106_840970_14313, Utils.getMethodForArgTypes("ObjectFlow<Object>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { true, startTime }));
                    effect88 = new EffectFunction(new FunctionCall((Object) cap__17_0_5_edc0357_1345510113566_55150_13784, Utils.getMethodForArgTypes("TimeVaryingMap<Integer>", "generated", "setValue", java.lang.Integer.class, java.lang.Object.class), new Object[] { startTime, _17_0_5_edc0357_1345510114810_895741_15152 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114104_663398_14299Collections() {
                parameters.add(_17_0_5_edc0357_1345510114810_534712_15153);
                parameters.add(_17_0_5_edc0357_1345510114104_812599_14301_endTime);
                parameters.add(_17_0_5_edc0357_1345510114810_895741_15152);
                constraintExpressions.add(constraint86);
                dependencies.add(_17_0_5_edc0357_1345510114810_534712_15153Dependency);
                dependencies.add(_17_0_5_edc0357_1345510114810_895741_15152Dependency);
                Set<Effect> effectsForcap__17_0_5_edc0357_1345510113566_55150_13784 = new HashSet<Effect>();
                effectsForcap__17_0_5_edc0357_1345510113566_55150_13784.add(effect88);
                effects.put((Parameter<?>) cap__17_0_5_edc0357_1345510113566_55150_13784, effectsForcap__17_0_5_edc0357_1345510113566_55150_13784);
                Set<Effect> effectsForsig_17_0_5_edc0357_1345510114106_840970_14313 = new HashSet<Effect>();
                effectsForsig_17_0_5_edc0357_1345510114106_840970_14313.add(effect87);
                effects.put((Parameter<?>) sig_17_0_5_edc0357_1345510114106_840970_14313, effectsForsig_17_0_5_edc0357_1345510114106_840970_14313);
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

            public Effect effect89 = null;

            public void init_17_0_5_edc0357_1345510114104_334510_14300Members() {
                try {
                    _17_0_5_edc0357_1345510114811_932135_15155 = new IntegerParameter("_17_0_5_edc0357_1345510114811_932135_15155", this);
                    effect89 = new EffectFunction(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114105_724841_14306, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { _17_0_5_edc0357_1345510114811_932135_15155, startTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114104_334510_14300Collections() {
                parameters.add(_17_0_5_edc0357_1345510114811_932135_15155);
                Set<Effect> effectsForsig_17_0_5_edc0357_1345510114105_724841_14306 = new HashSet<Effect>();
                effectsForsig_17_0_5_edc0357_1345510114105_724841_14306.add(effect89);
                effects.put((Parameter<?>) sig_17_0_5_edc0357_1345510114105_724841_14306, effectsForsig_17_0_5_edc0357_1345510114105_724841_14306);
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

            public ConstraintExpression constraint90 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114103_810338_14295_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114104_663398_14299_existsDependency = null;

            public ElaborationRule elaborationRule91 = null;

            public ElaborationRule elaborationRule92 = null;

            public void init_17_0_5_edc0357_1345510114104_812599_14301Members() {
                try {
                    _17_0_5_edc0357_1345510114103_810338_14295_exists = new BooleanParameter("_17_0_5_edc0357_1345510114103_810338_14295_exists", this);
                    _17_0_5_edc0357_1345510114103_561395_14296_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114103_561395_14296_endTime", this);
                    _17_0_5_edc0357_1345510114104_663398_14299_exists = new BooleanParameter("_17_0_5_edc0357_1345510114104_663398_14299_exists", this);
                    constraint90 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114103_561395_14296_endTime)));
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
                constraintExpressions.add(constraint90);
                dependencies.add(_17_0_5_edc0357_1345510114103_810338_14295_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510114104_663398_14299_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510114104_812599_14301Elaborations() {
                Expression<?>[] arguments91 = new Expression<?>[1];
                arguments91[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition91 = new Expression<Boolean>(_17_0_5_edc0357_1345510114104_663398_14299_exists);
                elaborationRule91 = addElaborationRule(condition91, _17_0_5_edc0357_1345510113559_180117_13777.this, Customer._17_0_5_edc0357_1345510113559_180117_13777._17_0_5_edc0357_1345510114104_663398_14299.class, "_AddStructuralFeatureValueAction_initialize", arguments91);
                Expression<?>[] arguments92 = new Expression<?>[1];
                arguments92[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition92 = new Expression<Boolean>(_17_0_5_edc0357_1345510114103_810338_14295_exists);
                elaborationRule92 = addElaborationRule(condition92, _17_0_5_edc0357_1345510113559_180117_13777.this, Customer._17_0_5_edc0357_1345510113559_180117_13777._17_0_5_edc0357_1345510114103_810338_14295.class, "_AddStructuralFeatureValueAction_initialize", arguments92);
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

            public ConstraintExpression constraint93 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114103_977080_14297_existsDependency = null;

            public ElaborationRule elaborationRule94 = null;

            public void init_17_0_5_edc0357_1345510114104_720146_14302Members() {
                try {
                    _17_0_5_edc0357_1345510114103_810338_14295_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114103_810338_14295_endTime", this);
                    _17_0_5_edc0357_1345510114103_977080_14297_exists = new BooleanParameter("_17_0_5_edc0357_1345510114103_977080_14297_exists", this);
                    constraint93 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114103_810338_14295_endTime)));
                    _17_0_5_edc0357_1345510114103_977080_14297_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114103_977080_14297_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114104_720146_14302Collections() {
                parameters.add(_17_0_5_edc0357_1345510114103_810338_14295_endTime);
                parameters.add(_17_0_5_edc0357_1345510114103_977080_14297_exists);
                constraintExpressions.add(constraint93);
                dependencies.add(_17_0_5_edc0357_1345510114103_977080_14297_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510114104_720146_14302Elaborations() {
                Expression<?>[] arguments94 = new Expression<?>[1];
                arguments94[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition94 = new Expression<Boolean>(_17_0_5_edc0357_1345510114103_977080_14297_exists);
                elaborationRule94 = addElaborationRule(condition94, _17_0_5_edc0357_1345510113559_180117_13777.this, Customer._17_0_5_edc0357_1345510113559_180117_13777._17_0_5_edc0357_1345510114103_977080_14297.class, "_ActivityFinalNode_initialize", arguments94);
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

        public Parameter sig_17_0_5_edc0357_1345510114167_567578_14359 = null;

        public IntegerParameter _17_0_5_edc0357_1346105157074_69873_15283 = null;

        public Parameter sig_17_0_5_edc0357_1345510114168_746941_14366 = null;

        public Parameter sig_17_0_5_edc0357_1345510114168_147606_14365 = null;

        public Parameter sig_17_0_5_edc0357_1345510114167_676201_14362 = null;

        public IntegerParameter invoke_time = null;

        public Parameter sig_17_0_5_edc0357_1345510114167_732692_14364 = null;

        public ElaborationRule elaborationRule95 = null;

        public ElaborationRule elaborationRule96 = null;

        public void init_17_0_5_edc0357_1345510113560_522513_13778Members() {
            try {
                sig_17_0_5_edc0357_1345510114167_567578_14359 = new Parameter("sig_17_0_5_edc0357_1345510114167_567578_14359", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114167_567578_14359"), this);
                _17_0_5_edc0357_1346105157074_69873_15283 = new IntegerParameter("_17_0_5_edc0357_1346105157074_69873_15283", this);
                sig_17_0_5_edc0357_1345510114168_746941_14366 = new Parameter("sig_17_0_5_edc0357_1345510114168_746941_14366", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114168_746941_14366"), this);
                sig_17_0_5_edc0357_1345510114168_147606_14365 = new Parameter("sig_17_0_5_edc0357_1345510114168_147606_14365", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114168_147606_14365"), this);
                sig_17_0_5_edc0357_1345510114167_676201_14362 = new Parameter("sig_17_0_5_edc0357_1345510114167_676201_14362", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114167_676201_14362"), this);
                invoke_time = new IntegerParameter("invoke_time", this);
                sig_17_0_5_edc0357_1345510114167_732692_14364 = new Parameter("sig_17_0_5_edc0357_1345510114167_732692_14364", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114167_732692_14364"), this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void init_17_0_5_edc0357_1345510113560_522513_13778Collections() {
            parameters.add(sig_17_0_5_edc0357_1345510114167_567578_14359);
            parameters.add(_17_0_5_edc0357_1346105157074_69873_15283);
            parameters.add(sig_17_0_5_edc0357_1345510114168_746941_14366);
            parameters.add(sig_17_0_5_edc0357_1345510114168_147606_14365);
            parameters.add(sig_17_0_5_edc0357_1345510114167_676201_14362);
            parameters.add(invoke_time);
            parameters.add(sig_17_0_5_edc0357_1345510114167_732692_14364);
        }

        public void init_17_0_5_edc0357_1345510113560_522513_13778Elaborations() {
            Expression<?>[] arguments95 = new Expression<?>[2];
            arguments95[0] = new Expression<Integer>(invoke_time);
            arguments95[1] = new Expression<Integer>(_17_0_5_edc0357_1346105157074_69873_15283);
            Expression<Boolean> condition95 = new Expression<Boolean>(true);
            elaborationRule95 = addElaborationRule(condition95, _17_0_5_edc0357_1345510113560_522513_13778.this, Customer._17_0_5_edc0357_1345510113560_522513_13778._17_0_5_edc0357_1346105164389_404592_15285.class, "capval_ActivityParameterNode_setDRCap", arguments95);
            Expression<?>[] arguments96 = new Expression<?>[1];
            arguments96[0] = new Expression<Integer>(invoke_time);
            Expression<Boolean> condition96 = new Expression<Boolean>(true);
            elaborationRule96 = addElaborationRule(condition96, _17_0_5_edc0357_1345510113560_522513_13778.this, Customer._17_0_5_edc0357_1345510113560_522513_13778._17_0_5_edc0357_1345510114161_34701_14349.class, "customerInitial_InitialNode_setDRCap", arguments96);
        }

        public _17_0_5_edc0357_1345510113560_522513_13778() {
            super();
            init_17_0_5_edc0357_1345510113560_522513_13778Members();
            init_17_0_5_edc0357_1345510113560_522513_13778Collections();
            init_17_0_5_edc0357_1345510113560_522513_13778Elaborations();
        }

        public class _17_0_5_edc0357_1345510114159_726624_14346 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1345510114163_254081_14351_exists = null;

            public Parameter _17_0_5_edc0357_1345510114817_17394_15168 = null;

            public BooleanParameter _17_0_5_edc0357_1345510114166_387809_14355_exists = null;

            public IntegerParameter _17_0_5_edc0357_1345510114161_34701_14349_endTime = null;

            public ConstraintExpression constraint97 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114163_254081_14351_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114166_387809_14355_existsDependency = null;

            public ElaborationRule elaborationRule98 = null;

            public ElaborationRule elaborationRule99 = null;

            public void init_17_0_5_edc0357_1345510114159_726624_14346Members() {
                try {
                    _17_0_5_edc0357_1345510114163_254081_14351_exists = new BooleanParameter("_17_0_5_edc0357_1345510114163_254081_14351_exists", this);
                    _17_0_5_edc0357_1345510114817_17394_15168 = new Parameter("_17_0_5_edc0357_1345510114817_17394_15168", null, Customer.this, this);
                    _17_0_5_edc0357_1345510114166_387809_14355_exists = new BooleanParameter("_17_0_5_edc0357_1345510114166_387809_14355_exists", this);
                    _17_0_5_edc0357_1345510114161_34701_14349_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114161_34701_14349_endTime", this);
                    constraint97 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114161_34701_14349_endTime)));
                    _17_0_5_edc0357_1345510114163_254081_14351_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114163_254081_14351_exists, new Expression<Boolean>(true));
                    _17_0_5_edc0357_1345510114166_387809_14355_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114166_387809_14355_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114159_726624_14346Collections() {
                parameters.add(_17_0_5_edc0357_1345510114163_254081_14351_exists);
                parameters.add(_17_0_5_edc0357_1345510114817_17394_15168);
                parameters.add(_17_0_5_edc0357_1345510114166_387809_14355_exists);
                parameters.add(_17_0_5_edc0357_1345510114161_34701_14349_endTime);
                constraintExpressions.add(constraint97);
                dependencies.add(_17_0_5_edc0357_1345510114163_254081_14351_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510114166_387809_14355_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510114159_726624_14346Elaborations() {
                Expression<?>[] arguments98 = new Expression<?>[1];
                arguments98[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition98 = new Expression<Boolean>(_17_0_5_edc0357_1345510114163_254081_14351_exists);
                elaborationRule98 = addElaborationRule(condition98, _17_0_5_edc0357_1345510113560_522513_13778.this, Customer._17_0_5_edc0357_1345510113560_522513_13778._17_0_5_edc0357_1345510114163_254081_14351.class, "customerDecideParticipation_DecisionNode_setDRCap", arguments98);
                Expression<?>[] arguments99 = new Expression<?>[2];
                arguments99[0] = new Expression<Integer>(endTime);
                arguments99[1] = new Expression<Customer>(_17_0_5_edc0357_1345510114817_17394_15168);
                Expression<Boolean> condition99 = new Expression<Boolean>(_17_0_5_edc0357_1345510114166_387809_14355_exists);
                elaborationRule99 = addElaborationRule(condition99, _17_0_5_edc0357_1345510113560_522513_13778.this, Customer._17_0_5_edc0357_1345510113560_522513_13778._17_0_5_edc0357_1345510114166_387809_14355.class, "forkNodeReadSelf_ForkNode_setDRCap", arguments99);
            }

            public _17_0_5_edc0357_1345510114159_726624_14346() {
                super();
                init_17_0_5_edc0357_1345510114159_726624_14346Members();
                init_17_0_5_edc0357_1345510114159_726624_14346Collections();
                init_17_0_5_edc0357_1345510114159_726624_14346Elaborations();
            }

            public _17_0_5_edc0357_1345510114159_726624_14346(Expression<Integer> _17_0_5_edc0357_1345510114161_34701_14349_endTime) {
                super();
                init_17_0_5_edc0357_1345510114159_726624_14346Members();
                init_17_0_5_edc0357_1345510114159_726624_14346Collections();
                addDependency(this._17_0_5_edc0357_1345510114161_34701_14349_endTime, _17_0_5_edc0357_1345510114161_34701_14349_endTime);
                init_17_0_5_edc0357_1345510114159_726624_14346Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114160_29879_14347 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1345510114165_641732_14354_exists = null;

            public IntegerParameter _17_0_5_edc0357_1345510114818_835880_15169 = null;

            public Parameter _17_0_5_edc0357_1345510114819_229911_15170 = null;

            public IntegerParameter _17_0_5_edc0357_1345510114163_254081_14351_endTime = null;

            public ConstraintExpression constraint100 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114165_641732_14354_existsDependency = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114818_835880_15169Dependency = null;

            public Dependency< Customer > _17_0_5_edc0357_1345510114819_229911_15170Dependency = null;

            public Effect effect101 = null;

            public ElaborationRule elaborationRule102 = null;

            public void init_17_0_5_edc0357_1345510114160_29879_14347Members() {
                try {
                    _17_0_5_edc0357_1345510114165_641732_14354_exists = new BooleanParameter("_17_0_5_edc0357_1345510114165_641732_14354_exists", this);
                    _17_0_5_edc0357_1345510114818_835880_15169 = new IntegerParameter("_17_0_5_edc0357_1345510114818_835880_15169", this);
                    _17_0_5_edc0357_1345510114819_229911_15170 = new Parameter("_17_0_5_edc0357_1345510114819_229911_15170", null, null, this);
                    _17_0_5_edc0357_1345510114163_254081_14351_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114163_254081_14351_endTime", this);
                    constraint100 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114163_254081_14351_endTime)));
                    _17_0_5_edc0357_1345510114165_641732_14354_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114165_641732_14354_exists, new Functions.And(new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114167_676201_14362, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })), new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114168_147606_14365, Utils.getMethodForArgTypes("ObjectFlow<Customer>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) }))));
                    _17_0_5_edc0357_1345510114818_835880_15169Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114818_835880_15169, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114167_567578_14359, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1345510114819_229911_15170Dependency = new Dependency<Customer>(_17_0_5_edc0357_1345510114819_229911_15170, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114167_732692_14364, Utils.getMethodForArgTypes("ObjectFlow<Customer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    effect101 = new EffectFunction(new FunctionCall((Object) cap__17_0_5_edc0357_1345510113566_55150_13784, Utils.getMethodForArgTypes("TimeVaryingMap<Integer>", "generated", "setValue", java.lang.Integer.class, java.lang.Object.class), new Object[] { startTime, _17_0_5_edc0357_1345510114818_835880_15169 }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114160_29879_14347Collections() {
                parameters.add(_17_0_5_edc0357_1345510114165_641732_14354_exists);
                parameters.add(_17_0_5_edc0357_1345510114818_835880_15169);
                parameters.add(_17_0_5_edc0357_1345510114819_229911_15170);
                parameters.add(_17_0_5_edc0357_1345510114163_254081_14351_endTime);
                constraintExpressions.add(constraint100);
                dependencies.add(_17_0_5_edc0357_1345510114165_641732_14354_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510114818_835880_15169Dependency);
                dependencies.add(_17_0_5_edc0357_1345510114819_229911_15170Dependency);
                Set<Effect> effectsForcap__17_0_5_edc0357_1345510113566_55150_13784 = new HashSet<Effect>();
                effectsForcap__17_0_5_edc0357_1345510113566_55150_13784.add(effect101);
                effects.put((Parameter<?>) cap__17_0_5_edc0357_1345510113566_55150_13784, effectsForcap__17_0_5_edc0357_1345510113566_55150_13784);
            }

            public void init_17_0_5_edc0357_1345510114160_29879_14347Elaborations() {
                Expression<?>[] arguments102 = new Expression<?>[1];
                arguments102[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition102 = new Expression<Boolean>(_17_0_5_edc0357_1345510114165_641732_14354_exists);
                elaborationRule102 = addElaborationRule(condition102, _17_0_5_edc0357_1345510113560_522513_13778.this, Customer._17_0_5_edc0357_1345510113560_522513_13778._17_0_5_edc0357_1345510114165_641732_14354.class, "sendSignalYes_SendSignalAction_setDRCap", arguments102);
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

            public BooleanParameter _17_0_5_edc0357_1345510114159_726624_14346_exists = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114159_726624_14346_existsDependency = null;

            public ElaborationRule elaborationRule103 = null;

            public void init_17_0_5_edc0357_1345510114161_34701_14349Members() {
                try {
                    _17_0_5_edc0357_1345510114159_726624_14346_exists = new BooleanParameter("_17_0_5_edc0357_1345510114159_726624_14346_exists", this);
                    _17_0_5_edc0357_1345510114159_726624_14346_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114159_726624_14346_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114161_34701_14349Collections() {
                parameters.add(_17_0_5_edc0357_1345510114159_726624_14346_exists);
                dependencies.add(_17_0_5_edc0357_1345510114159_726624_14346_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510114161_34701_14349Elaborations() {
                Expression<?>[] arguments103 = new Expression<?>[1];
                arguments103[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition103 = new Expression<Boolean>(_17_0_5_edc0357_1345510114159_726624_14346_exists);
                elaborationRule103 = addElaborationRule(condition103, _17_0_5_edc0357_1345510113560_522513_13778.this, Customer._17_0_5_edc0357_1345510113560_522513_13778._17_0_5_edc0357_1345510114159_726624_14346.class, "customerReadSelf_ReadSelfAction_setDRCap", arguments103);
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

            public void init_17_0_5_edc0357_1345510114162_717249_14350Members() {
                try {
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114162_717249_14350Collections() {
            }

            public void init_17_0_5_edc0357_1345510114162_717249_14350Elaborations() {
            }

            public _17_0_5_edc0357_1345510114162_717249_14350() {
                super();
                init_17_0_5_edc0357_1345510114162_717249_14350Members();
                init_17_0_5_edc0357_1345510114162_717249_14350Collections();
                init_17_0_5_edc0357_1345510114162_717249_14350Elaborations();
            }
        }

        public class _17_0_5_edc0357_1345510114163_254081_14351 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1345510114163_833189_14352_exists = null;

            public IntegerParameter _17_0_5_edc0357_1345510114159_726624_14346_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1345510114160_29879_14347_exists = null;

            public ConstraintExpression constraint104 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114163_833189_14352_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114160_29879_14347_existsDependency = null;

            public ElaborationRule elaborationRule105 = null;

            public ElaborationRule elaborationRule106 = null;

            public void init_17_0_5_edc0357_1345510114163_254081_14351Members() {
                try {
                    _17_0_5_edc0357_1345510114163_833189_14352_exists = new BooleanParameter("_17_0_5_edc0357_1345510114163_833189_14352_exists", this);
                    _17_0_5_edc0357_1345510114159_726624_14346_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114159_726624_14346_endTime", this);
                    _17_0_5_edc0357_1345510114160_29879_14347_exists = new BooleanParameter("_17_0_5_edc0357_1345510114160_29879_14347_exists", this);
                    constraint104 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114159_726624_14346_endTime)));
                    _17_0_5_edc0357_1345510114163_833189_14352_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114163_833189_14352_exists, new Expression<Boolean>(true));
                    _17_0_5_edc0357_1345510114160_29879_14347_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114160_29879_14347_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114163_254081_14351Collections() {
                parameters.add(_17_0_5_edc0357_1345510114163_833189_14352_exists);
                parameters.add(_17_0_5_edc0357_1345510114159_726624_14346_endTime);
                parameters.add(_17_0_5_edc0357_1345510114160_29879_14347_exists);
                constraintExpressions.add(constraint104);
                dependencies.add(_17_0_5_edc0357_1345510114163_833189_14352_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510114160_29879_14347_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510114163_254081_14351Elaborations() {
                Expression<?>[] arguments105 = new Expression<?>[1];
                arguments105[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition105 = new Expression<Boolean>(_17_0_5_edc0357_1345510114163_833189_14352_exists);
                elaborationRule105 = addElaborationRule(condition105, _17_0_5_edc0357_1345510113560_522513_13778.this, Customer._17_0_5_edc0357_1345510113560_522513_13778._17_0_5_edc0357_1345510114163_833189_14352.class, "sendSignalNo_SendSignalAction_setDRCap", arguments105);
                Expression<?>[] arguments106 = new Expression<?>[1];
                arguments106[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition106 = new Expression<Boolean>(_17_0_5_edc0357_1345510114160_29879_14347_exists);
                elaborationRule106 = addElaborationRule(condition106, _17_0_5_edc0357_1345510113560_522513_13778.this, Customer._17_0_5_edc0357_1345510113560_522513_13778._17_0_5_edc0357_1345510114160_29879_14347.class, "addStructuralFeatureCap_AddStructuralFeatureValueAction_setDRCap", arguments106);
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

            public Parameter _17_0_5_edc0357_1345510114820_809313_15172 = null;

            public IntegerParameter _17_0_5_edc0357_1345510114163_254081_14351_endTime = null;

            public ConstraintExpression constraint107 = null;

            public Dependency< Customer > _17_0_5_edc0357_1345510114820_809313_15172Dependency = null;

            public Effect effect108 = null;

            public Effect effect109 = null;

            public void init_17_0_5_edc0357_1345510114163_833189_14352Members() {
                try {
                    _17_0_5_edc0357_1345510114820_809313_15172 = new Parameter("_17_0_5_edc0357_1345510114820_809313_15172", null, null, this);
                    _17_0_5_edc0357_1345510114163_254081_14351_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114163_254081_14351_endTime", this);
                    constraint107 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114163_254081_14351_endTime)));
                    _17_0_5_edc0357_1345510114820_809313_15172Dependency = new Dependency<Customer>(_17_0_5_edc0357_1345510114820_809313_15172, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114168_746941_14366, Utils.getMethodForArgTypes("ObjectFlow<Customer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    //effect108 = new EffectFunction(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114168_645304_14367, Utils.getMethodForArgTypes("Customer._17_0_5_edc0357_1345510113560_522513_13778._17_0_5_edc0357_1345510114163_833189_14352", "generated", "send"), new Object[] { true, startTime }));
                    effect109 = new EffectFunction(new FunctionCall((Object) ((Power_System) x).ss_17_0_5_edc0357_1345510113564_305135_13782_no.getValue(), Utils.getMethodForArgTypes("Customer._17_0_5_edc0357_1345510113560_522513_13778._17_0_5_edc0357_1345510114163_833189_14352", "generated", "send"), new Object[] { x.new Signalno(), endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114163_833189_14352Collections() {
                parameters.add(_17_0_5_edc0357_1345510114820_809313_15172);
                parameters.add(_17_0_5_edc0357_1345510114163_254081_14351_endTime);
                constraintExpressions.add(constraint107);
                dependencies.add(_17_0_5_edc0357_1345510114820_809313_15172Dependency);
            }

            public void init_17_0_5_edc0357_1345510114163_833189_14352Elaborations() {
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

            public ConstraintExpression constraint110 = null;

            public Effect effect111 = null;

            public Effect effect112 = null;

            public void init_17_0_5_edc0357_1345510114164_830320_14353Members() {
                try {
                    objectToPass = new IntegerParameter("objectToPass", this);
                    _17_0_5_edc0357_1346105164389_404592_15285_endTime = new IntegerParameter("_17_0_5_edc0357_1346105164389_404592_15285_endTime", this);
                    constraint110 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346105164389_404592_15285_endTime)));
                    effect111 = new EffectFunction(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114167_567578_14359, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                    effect112 = new EffectFunction(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114167_676201_14362, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114164_830320_14353Collections() {
                parameters.add(objectToPass);
                parameters.add(_17_0_5_edc0357_1346105164389_404592_15285_endTime);
                constraintExpressions.add(constraint110);
                Set<Effect> effectsForsig_17_0_5_edc0357_1345510114167_567578_14359 = new HashSet<Effect>();
                effectsForsig_17_0_5_edc0357_1345510114167_567578_14359.add(effect111);
                effects.put((Parameter<?>) sig_17_0_5_edc0357_1345510114167_567578_14359, effectsForsig_17_0_5_edc0357_1345510114167_567578_14359);
                Set<Effect> effectsForsig_17_0_5_edc0357_1345510114167_676201_14362 = new HashSet<Effect>();
                effectsForsig_17_0_5_edc0357_1345510114167_676201_14362.add(effect112);
                effects.put((Parameter<?>) sig_17_0_5_edc0357_1345510114167_676201_14362, effectsForsig_17_0_5_edc0357_1345510114167_676201_14362);
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

            public IntegerParameter _17_0_5_edc0357_1345510114822_185942_15174 = null;

            public Parameter _17_0_5_edc0357_1345510114821_349321_15173 = null;

            public ConstraintExpression constraint113 = null;

            public Dependency< Integer > _17_0_5_edc0357_1345510114822_185942_15174Dependency = null;

            public Dependency< Customer > _17_0_5_edc0357_1345510114821_349321_15173Dependency = null;

            public Effect effect114 = null;

            public Effect effect115 = null;

            public void init_17_0_5_edc0357_1345510114165_641732_14354Members() {
                try {
                    _17_0_5_edc0357_1345510114160_29879_14347_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114160_29879_14347_endTime", this);
                    _17_0_5_edc0357_1345510114822_185942_15174 = new IntegerParameter("_17_0_5_edc0357_1345510114822_185942_15174", this);
                    _17_0_5_edc0357_1345510114821_349321_15173 = new Parameter("_17_0_5_edc0357_1345510114821_349321_15173", null, null, this);
                    constraint113 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114160_29879_14347_endTime)));
                    _17_0_5_edc0357_1345510114822_185942_15174Dependency = new Dependency<Integer>(_17_0_5_edc0357_1345510114822_185942_15174, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114167_676201_14362, Utils.getMethodForArgTypes("ObjectFlow<Integer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1345510114821_349321_15173Dependency = new Dependency<Customer>(_17_0_5_edc0357_1345510114821_349321_15173, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114168_147606_14365, Utils.getMethodForArgTypes("ObjectFlow<Customer>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    //effect114 = new EffectFunction(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114167_973069_14361, Utils.getMethodForArgTypes("Customer._17_0_5_edc0357_1345510113560_522513_13778._17_0_5_edc0357_1345510114165_641732_14354", "generated", "send"), new Object[] { true, startTime }));
                    effect115 = new EffectFunction(new FunctionCall((Object) ((Power_System) x).ss_17_0_5_edc0357_1345510113564_305135_13782_yes.getValue(), Utils.getMethodForArgTypes("Customer._17_0_5_edc0357_1345510113560_522513_13778._17_0_5_edc0357_1345510114165_641732_14354", "generated", "send"), new Object[] { x.new Signalyes(_17_0_5_edc0357_1345510114822_185942_15174.getValue()), endTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114165_641732_14354Collections() {
                parameters.add(_17_0_5_edc0357_1345510114160_29879_14347_endTime);
                parameters.add(_17_0_5_edc0357_1345510114822_185942_15174);
                parameters.add(_17_0_5_edc0357_1345510114821_349321_15173);
                constraintExpressions.add(constraint113);
                dependencies.add(_17_0_5_edc0357_1345510114822_185942_15174Dependency);
                dependencies.add(_17_0_5_edc0357_1345510114821_349321_15173Dependency);
            }

            public void init_17_0_5_edc0357_1345510114165_641732_14354Elaborations() {
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

            public Parameter objectToPass = null;

            public IntegerParameter _17_0_5_edc0357_1345510114159_726624_14346_endTime = null;

            public ConstraintExpression constraint116 = null;

            public Effect effect117 = null;

            public Effect effect118 = null;

            public Effect effect119 = null;

            public void init_17_0_5_edc0357_1345510114166_387809_14355Members() {
                try {
                    objectToPass = new Parameter("objectToPass", null, null, this);
                    _17_0_5_edc0357_1345510114159_726624_14346_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114159_726624_14346_endTime", this);
                    constraint116 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114159_726624_14346_endTime)));
                    effect117 = new EffectFunction(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114167_732692_14364, Utils.getMethodForArgTypes("ObjectFlow<Customer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                    effect118 = new EffectFunction(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114168_147606_14365, Utils.getMethodForArgTypes("ObjectFlow<Customer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                    effect119 = new EffectFunction(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114168_746941_14366, Utils.getMethodForArgTypes("ObjectFlow<Customer>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { objectToPass, startTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114166_387809_14355Collections() {
                parameters.add(objectToPass);
                parameters.add(_17_0_5_edc0357_1345510114159_726624_14346_endTime);
                constraintExpressions.add(constraint116);
                Set<Effect> effectsForsig_17_0_5_edc0357_1345510114167_732692_14364 = new HashSet<Effect>();
                effectsForsig_17_0_5_edc0357_1345510114167_732692_14364.add(effect117);
                effects.put((Parameter<?>) sig_17_0_5_edc0357_1345510114167_732692_14364, effectsForsig_17_0_5_edc0357_1345510114167_732692_14364);
                Set<Effect> effectsForsig_17_0_5_edc0357_1345510114168_147606_14365 = new HashSet<Effect>();
                effectsForsig_17_0_5_edc0357_1345510114168_147606_14365.add(effect118);
                effects.put((Parameter<?>) sig_17_0_5_edc0357_1345510114168_147606_14365, effectsForsig_17_0_5_edc0357_1345510114168_147606_14365);
                Set<Effect> effectsForsig_17_0_5_edc0357_1345510114168_746941_14366 = new HashSet<Effect>();
                effectsForsig_17_0_5_edc0357_1345510114168_746941_14366.add(effect119);
                effects.put((Parameter<?>) sig_17_0_5_edc0357_1345510114168_746941_14366, effectsForsig_17_0_5_edc0357_1345510114168_746941_14366);
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

            public ElaborationRule elaborationRule120 = null;

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
                Expression<?>[] arguments120 = new Expression<?>[2];
                arguments120[0] = new Expression<Integer>(endTime);
                arguments120[1] = new Expression<Integer>(_17_0_5_edc0357_1346105157074_69873_15283);
                Expression<Boolean> condition120 = new Expression<Boolean>(_17_0_5_edc0357_1345510114164_830320_14353_exists);
                elaborationRule120 = addElaborationRule(condition120, _17_0_5_edc0357_1345510113560_522513_13778.this, Customer._17_0_5_edc0357_1345510113560_522513_13778._17_0_5_edc0357_1345510114164_830320_14353.class, "forkNodeLastSignalValue_ForkNode_setDRCap", arguments120);
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

        public Parameter sig_17_0_5_edc0357_1345510113323_942738_13620 = null;

        public Parameter sig_17_0_5_edc0357_1345510113318_974497_13612 = null;

        public IntegerParameter invoke_time = null;

        public Parameter sig_17_0_5_edc0357_1340656734213_808349_11756 = null;

        public ElaborationRule elaborationRule121 = null;

        public void init_17_0_5_edc0357_1346104706708_917454_14957Members() {
            try {
                sig_17_0_5_edc0357_1345510113323_942738_13620 = new Parameter("sig_17_0_5_edc0357_1345510113323_942738_13620", null, new ObjectFlow("sig_17_0_5_edc0357_1345510113323_942738_13620"), this);
                sig_17_0_5_edc0357_1345510113318_974497_13612 = new Parameter("sig_17_0_5_edc0357_1345510113318_974497_13612", null, new ObjectFlow("sig_17_0_5_edc0357_1345510113318_974497_13612"), this);
                invoke_time = new IntegerParameter("invoke_time", this);
                sig_17_0_5_edc0357_1340656734213_808349_11756 = new Parameter("sig_17_0_5_edc0357_1340656734213_808349_11756", null, new ObjectFlow("sig_17_0_5_edc0357_1340656734213_808349_11756"), this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void init_17_0_5_edc0357_1346104706708_917454_14957Collections() {
            parameters.add(sig_17_0_5_edc0357_1345510113323_942738_13620);
            parameters.add(sig_17_0_5_edc0357_1345510113318_974497_13612);
            parameters.add(invoke_time);
            parameters.add(sig_17_0_5_edc0357_1340656734213_808349_11756);
        }

        public void init_17_0_5_edc0357_1346104706708_917454_14957Elaborations() {
            Expression<?>[] arguments121 = new Expression<?>[1];
            arguments121[0] = new Expression<Integer>(invoke_time);
            Expression<Boolean> condition121 = new Expression<Boolean>(true);
            elaborationRule121 = addElaborationRule(condition121, _17_0_5_edc0357_1346104706708_917454_14957.this, Customer._17_0_5_edc0357_1346104706708_917454_14957._17_0_5_edc0357_1346104724327_278485_14979.class, "_InitialNode_CustomerCB", arguments121);
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

            public ElaborationRule elaborationRule122 = null;

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
                Expression<?>[] arguments122 = new Expression<?>[1];
                arguments122[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition122 = new Expression<Boolean>(_17_0_5_edc0357_1346104727721_22566_14990_exists);
                elaborationRule122 = addElaborationRule(condition122, _17_0_5_edc0357_1346104706708_917454_14957.this, Customer._17_0_5_edc0357_1346104706708_917454_14957._17_0_5_edc0357_1346104727721_22566_14990.class, "_CallBehaviorAction_CustomerCB", arguments122);
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

            public ConstraintExpression constraint123 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1348000884142_707118_14045_existsDependency = null;

            public ElaborationRule elaborationRule124 = null;

            public ElaborationRule elaborationRule125 = null;

            public void init_17_0_5_edc0357_1346104727721_22566_14990Members() {
                try {
                    _17_0_5_edc0357_1348000884142_707118_14045_exists = new BooleanParameter("_17_0_5_edc0357_1348000884142_707118_14045_exists", this);
                    _17_0_5_edc0357_1346104724327_278485_14979_endTime = new IntegerParameter("_17_0_5_edc0357_1346104724327_278485_14979_endTime", this);
                    cba_endTime = new IntegerParameter("cba_endTime", this);
                    constraint123 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346104724327_278485_14979_endTime)));
                    _17_0_5_edc0357_1348000884142_707118_14045_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1348000884142_707118_14045_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346104727721_22566_14990Collections() {
                parameters.add(_17_0_5_edc0357_1348000884142_707118_14045_exists);
                parameters.add(_17_0_5_edc0357_1346104724327_278485_14979_endTime);
                parameters.add(cba_endTime);
                constraintExpressions.add(constraint123);
                dependencies.add(_17_0_5_edc0357_1348000884142_707118_14045_existsDependency);
            }

            public void init_17_0_5_edc0357_1346104727721_22566_14990Elaborations() {
                Expression<?>[] arguments124 = new Expression<?>[1];
                arguments124[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition124 = new Expression<Boolean>(_17_0_5_edc0357_1348000884142_707118_14045_exists);
                elaborationRule124 = addElaborationRule(condition124, _17_0_5_edc0357_1346104706708_917454_14957.this, Customer._17_0_5_edc0357_1346104706708_917454_14957._17_0_5_edc0357_1348000884142_707118_14045.class, "_AcceptEventAction_CustomerCB", arguments124);
                Expression<?>[] arguments125 = new Expression<?>[1];
                arguments125[0] = new Expression<Integer>(cba_endTime);
                Expression<Boolean> condition125 = new Expression<Boolean>(true);
                elaborationRule125 = addElaborationRule(condition125, Customer.this, Customer._17_0_5_edc0357_1345510113559_180117_13777.class, "initialize_Activity_Customer", arguments125);
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

            public BooleanParameter _17_0_5_edc0357_1346105076614_639562_15240_exists = null;

            //public BooleanParameter _17_0_5_edc0357_1346104773078_654914_15030_exists = null;

            public IntegerParameter _17_0_5_edc0357_1348000884142_707118_14045_endTime = null;

            public ConstraintExpression constraint126 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346105173460_397488_15297_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346104908659_55389_15177_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346105076614_639562_15240_existsDependency = null;

            //public Dependency< Boolean > _17_0_5_edc0357_1346104773078_654914_15030_existsDependency = null;

            public ElaborationRule elaborationRule127 = null;

            public ElaborationRule elaborationRule128 = null;

            public ElaborationRule elaborationRule129 = null;

            public ElaborationRule elaborationRule130 = null;

            public void init_17_0_5_edc0357_1346104764223_34184_15024Members() {
                try {
                    _17_0_5_edc0357_1346105173460_397488_15297_exists = new BooleanParameter("_17_0_5_edc0357_1346105173460_397488_15297_exists", this);
                    _17_0_5_edc0357_1346104908659_55389_15177_exists = new BooleanParameter("_17_0_5_edc0357_1346104908659_55389_15177_exists", this);
                    _17_0_5_edc0357_1346105076614_639562_15240_exists = new BooleanParameter("_17_0_5_edc0357_1346105076614_639562_15240_exists", this);
                    //_17_0_5_edc0357_1346104773078_654914_15030_exists = new BooleanParameter("_17_0_5_edc0357_1346104773078_654914_15030_exists", this);
                    _17_0_5_edc0357_1348000884142_707118_14045_endTime = new IntegerParameter("_17_0_5_edc0357_1348000884142_707118_14045_endTime", this);
                    constraint126 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1348000884142_707118_14045_endTime)));
                    _17_0_5_edc0357_1346105173460_397488_15297_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346105173460_397488_15297_exists, new Expression<Boolean>(true));
                    _17_0_5_edc0357_1346104908659_55389_15177_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346104908659_55389_15177_exists, new Expression<Boolean>(true));
                    _17_0_5_edc0357_1346105076614_639562_15240_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346105076614_639562_15240_exists, new Expression<Boolean>(true));
                    //_17_0_5_edc0357_1346104773078_654914_15030_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346104773078_654914_15030_exists, new Expression(new FunctionCall((Object) q_Customer_stop, Utils.getMethodForArgTypes("Customer._17_0_5_edc0357_1346104706708_917454_14957._17_0_5_edc0357_1346104764223_34184_15024", "generated", "hasStuff"), new Object[] { new Expression<Integer>(endTime) })));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346104764223_34184_15024Collections() {
                parameters.add(_17_0_5_edc0357_1346105173460_397488_15297_exists);
                parameters.add(_17_0_5_edc0357_1346104908659_55389_15177_exists);
                parameters.add(_17_0_5_edc0357_1346105076614_639562_15240_exists);
                //parameters.add(_17_0_5_edc0357_1346104773078_654914_15030_exists);
                parameters.add(_17_0_5_edc0357_1348000884142_707118_14045_endTime);
                constraintExpressions.add(constraint126);
                dependencies.add(_17_0_5_edc0357_1346105173460_397488_15297_existsDependency);
                dependencies.add(_17_0_5_edc0357_1346104908659_55389_15177_existsDependency);
                dependencies.add(_17_0_5_edc0357_1346105076614_639562_15240_existsDependency);
                //dependencies.add(_17_0_5_edc0357_1346104773078_654914_15030_existsDependency);
            }

            public void init_17_0_5_edc0357_1346104764223_34184_15024Elaborations() {
                Expression<?>[] arguments127 = new Expression<?>[1];
                arguments127[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition127 = new Expression<Boolean>(_17_0_5_edc0357_1346104908659_55389_15177_exists);
                elaborationRule127 = addElaborationRule(condition127, _17_0_5_edc0357_1346104706708_917454_14957.this, Customer._17_0_5_edc0357_1346104706708_917454_14957._17_0_5_edc0357_1346104908659_55389_15177.class, "_MergeNode_CustomerCB", arguments127);
                Expression<?>[] arguments128 = new Expression<?>[1];
                arguments128[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition128 = new Expression<Boolean>(_17_0_5_edc0357_1346105076614_639562_15240_exists);
                elaborationRule128 = addElaborationRule(condition128, _17_0_5_edc0357_1346104706708_917454_14957.this, Customer._17_0_5_edc0357_1346104706708_917454_14957._17_0_5_edc0357_1346105076614_639562_15240.class, "_MergeNode_CustomerCB", arguments128);
                Expression<?>[] arguments129 = new Expression<?>[1];
                arguments129[0] = new Expression<Integer>(endTime);
                //Expression<Boolean> condition129 = new Expression<Boolean>(_17_0_5_edc0357_1346104773078_654914_15030_exists);
                //elaborationRule129 = addElaborationRule(condition129, _17_0_5_edc0357_1346104706708_917454_14957.this, Customer._17_0_5_edc0357_1346104706708_917454_14957._17_0_5_edc0357_1346104773078_654914_15030.class, "_AcceptEventAction_CustomerCB", arguments129);
                Expression<?>[] arguments130 = new Expression<?>[1];
                arguments130[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition130 = new Expression<Boolean>(_17_0_5_edc0357_1346105173460_397488_15297_exists);
                elaborationRule130 = addElaborationRule(condition130, _17_0_5_edc0357_1346104706708_917454_14957.this, Customer._17_0_5_edc0357_1346104706708_917454_14957._17_0_5_edc0357_1346105173460_397488_15297.class, "_MergeNode_CustomerCB", arguments130);
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
/*
        public class _17_0_5_edc0357_1346104773078_654914_15030 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1346104764223_34184_15024_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1346104860701_80157_15146_exists = null;

            public ConstraintExpression constraint131 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346104860701_80157_15146_existsDependency = null;

            public ElaborationRule elaborationRule132 = null;

            public void init_17_0_5_edc0357_1346104773078_654914_15030Members() {
                try {
                    _17_0_5_edc0357_1346104764223_34184_15024_endTime = new IntegerParameter("_17_0_5_edc0357_1346104764223_34184_15024_endTime", this);
                    _17_0_5_edc0357_1346104860701_80157_15146_exists = new BooleanParameter("_17_0_5_edc0357_1346104860701_80157_15146_exists", this);
                    constraint131 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346104764223_34184_15024_endTime)));
                    _17_0_5_edc0357_1346104860701_80157_15146_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346104860701_80157_15146_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346104773078_654914_15030Collections() {
                parameters.add(_17_0_5_edc0357_1346104764223_34184_15024_endTime);
                parameters.add(_17_0_5_edc0357_1346104860701_80157_15146_exists);
                constraintExpressions.add(constraint131);
                dependencies.add(_17_0_5_edc0357_1346104860701_80157_15146_existsDependency);
            }

            public void init_17_0_5_edc0357_1346104773078_654914_15030Elaborations() {
                Expression<?>[] arguments132 = new Expression<?>[1];
                arguments132[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition132 = new Expression<Boolean>(_17_0_5_edc0357_1346104860701_80157_15146_exists);
                elaborationRule132 = addElaborationRule(condition132, _17_0_5_edc0357_1346104706708_917454_14957.this, Customer._17_0_5_edc0357_1346104706708_917454_14957._17_0_5_edc0357_1346104860701_80157_15146.class, "_ActivityFinalNode_CustomerCB", arguments132);
            }

            public _17_0_5_edc0357_1346104773078_654914_15030() {
                super();
                init_17_0_5_edc0357_1346104773078_654914_15030Members();
                init_17_0_5_edc0357_1346104773078_654914_15030Collections();
                init_17_0_5_edc0357_1346104773078_654914_15030Elaborations();
            }

            public _17_0_5_edc0357_1346104773078_654914_15030(Expression<Integer> _17_0_5_edc0357_1346104764223_34184_15024_endTime) {
                super();
                init_17_0_5_edc0357_1346104773078_654914_15030Members();
                init_17_0_5_edc0357_1346104773078_654914_15030Collections();
                addDependency(this._17_0_5_edc0357_1346104764223_34184_15024_endTime, _17_0_5_edc0357_1346104764223_34184_15024_endTime);
                init_17_0_5_edc0357_1346104773078_654914_15030Elaborations();
                fixTimeDependencies();
            }
        }
*/
        public class _17_0_5_edc0357_1346104784830_441700_15042 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1346105076614_639562_15240_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1346104812245_253068_15114_exists = null;

            public ConstraintExpression constraint133 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346104812245_253068_15114_existsDependency = null;

            public ElaborationRule elaborationRule134 = null;

            public void init_17_0_5_edc0357_1346104784830_441700_15042Members() {
                try {
                    _17_0_5_edc0357_1346105076614_639562_15240_endTime = new IntegerParameter("_17_0_5_edc0357_1346105076614_639562_15240_endTime", this);
                    _17_0_5_edc0357_1346104812245_253068_15114_exists = new BooleanParameter("_17_0_5_edc0357_1346104812245_253068_15114_exists", this);
                    constraint133 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346105076614_639562_15240_endTime)));
                    _17_0_5_edc0357_1346104812245_253068_15114_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346104812245_253068_15114_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346104784830_441700_15042Collections() {
                parameters.add(_17_0_5_edc0357_1346105076614_639562_15240_endTime);
                parameters.add(_17_0_5_edc0357_1346104812245_253068_15114_exists);
                constraintExpressions.add(constraint133);
                dependencies.add(_17_0_5_edc0357_1346104812245_253068_15114_existsDependency);
            }

            public void init_17_0_5_edc0357_1346104784830_441700_15042Elaborations() {
                Expression<?>[] arguments134 = new Expression<?>[1];
                arguments134[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition134 = new Expression<Boolean>(_17_0_5_edc0357_1346104812245_253068_15114_exists);
                elaborationRule134 = addElaborationRule(condition134, _17_0_5_edc0357_1346104706708_917454_14957.this, Customer._17_0_5_edc0357_1346104706708_917454_14957._17_0_5_edc0357_1346104812245_253068_15114.class, "_CallBehaviorAction_CustomerCB", arguments134);
            }

            public _17_0_5_edc0357_1346104784830_441700_15042() {
                super();
                init_17_0_5_edc0357_1346104784830_441700_15042Members();
                init_17_0_5_edc0357_1346104784830_441700_15042Collections();
                init_17_0_5_edc0357_1346104784830_441700_15042Elaborations();
            }

            public _17_0_5_edc0357_1346104784830_441700_15042(Expression<Integer> _17_0_5_edc0357_1346105076614_639562_15240_endTime) {
                super();
                init_17_0_5_edc0357_1346104784830_441700_15042Members();
                init_17_0_5_edc0357_1346104784830_441700_15042Collections();
                addDependency(this._17_0_5_edc0357_1346105076614_639562_15240_endTime, _17_0_5_edc0357_1346105076614_639562_15240_endTime);
                init_17_0_5_edc0357_1346104784830_441700_15042Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1346104787974_430716_15054 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1346105013928_873722_15220_exists = null;

            public Parameter _17_0_5_edc0357_1346105140214_847754_15269 = null;

            public IntegerParameter _17_0_5_edc0357_1346105173460_397488_15297_endTime = null;

            public ConstraintExpression constraint135 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346105013928_873722_15220_existsDependency = null;

            public Dependency< Power_System.Signaldr_request > _17_0_5_edc0357_1346105140214_847754_15269Dependency = null;

            public ElaborationRule elaborationRule136 = null;

            public void init_17_0_5_edc0357_1346104787974_430716_15054Members() {
                try {
                    _17_0_5_edc0357_1346105013928_873722_15220_exists = new BooleanParameter("_17_0_5_edc0357_1346105013928_873722_15220_exists", this);
                    _17_0_5_edc0357_1346105140214_847754_15269 = new Parameter("_17_0_5_edc0357_1346105140214_847754_15269", null, null, this);
                    _17_0_5_edc0357_1346105173460_397488_15297_endTime = new IntegerParameter("_17_0_5_edc0357_1346105173460_397488_15297_endTime", this);
                    constraint135 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346105173460_397488_15297_endTime)));
                    _17_0_5_edc0357_1346105013928_873722_15220_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346105013928_873722_15220_exists, new Expression<Boolean>(true));
                    _17_0_5_edc0357_1346105140214_847754_15269Dependency = new Dependency<Power_System.Signaldr_request>(_17_0_5_edc0357_1346105140214_847754_15269, new Expression(new FunctionCall((Object) q_Customer_dr_request, Utils.getMethodForArgTypes("ObjectFlow<Power_System.dr_request>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346104787974_430716_15054Collections() {
                parameters.add(_17_0_5_edc0357_1346105013928_873722_15220_exists);
                parameters.add(_17_0_5_edc0357_1346105140214_847754_15269);
                parameters.add(_17_0_5_edc0357_1346105173460_397488_15297_endTime);
                constraintExpressions.add(constraint135);
                dependencies.add(_17_0_5_edc0357_1346105013928_873722_15220_existsDependency);
                dependencies.add(_17_0_5_edc0357_1346105140214_847754_15269Dependency);
            }

            public void init_17_0_5_edc0357_1346104787974_430716_15054Elaborations() {
                Expression<?>[] arguments136 = new Expression<?>[2];
                arguments136[0] = new Expression<Integer>(endTime);
                arguments136[1] = new Expression<Power_System.Signaldr_request>(_17_0_5_edc0357_1346105140214_847754_15269);
                Expression<Boolean> condition136 = new Expression<Boolean>(_17_0_5_edc0357_1346105013928_873722_15220_exists);
                elaborationRule136 = addElaborationRule(condition136, _17_0_5_edc0357_1346104706708_917454_14957.this, Customer._17_0_5_edc0357_1346104706708_917454_14957._17_0_5_edc0357_1346105013928_873722_15220.class, "_ReadStructuralFeatureAction_CustomerCB", arguments136);
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

            public ConstraintExpression constraint137 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346104840585_256912_15134_existsDependency = null;

            public Dependency< Integer > durationDependency = null;

            public ElaborationRule elaborationRule138 = null;

            public void init_17_0_5_edc0357_1346104794886_467330_15077Members() {
                try {
                    _17_0_5_edc0357_1346104908659_55389_15177_endTime = new IntegerParameter("_17_0_5_edc0357_1346104908659_55389_15177_endTime", this);
                    _17_0_5_edc0357_1346104840585_256912_15134_exists = new BooleanParameter("_17_0_5_edc0357_1346104840585_256912_15134_exists", this);
                    constraint137 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346104908659_55389_15177_endTime)));
                    _17_0_5_edc0357_1346104840585_256912_15134_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346104840585_256912_15134_exists, new Expression<Boolean>(true));
                    durationDependency = new Dependency<Integer>(duration, new Expression<Integer>(15));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346104794886_467330_15077Collections() {
                parameters.add(_17_0_5_edc0357_1346104908659_55389_15177_endTime);
                parameters.add(_17_0_5_edc0357_1346104840585_256912_15134_exists);
                constraintExpressions.add(constraint137);
                removeDependency(duration);
                dependencies.add(_17_0_5_edc0357_1346104840585_256912_15134_existsDependency);
                dependencies.add(durationDependency);
            }

            public void init_17_0_5_edc0357_1346104794886_467330_15077Elaborations() {
                Expression<?>[] arguments138 = new Expression<?>[1];
                arguments138[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition138 = new Expression<Boolean>(_17_0_5_edc0357_1346104840585_256912_15134_exists);
                elaborationRule138 = addElaborationRule(condition138, _17_0_5_edc0357_1346104706708_917454_14957.this, Customer._17_0_5_edc0357_1346104706708_917454_14957._17_0_5_edc0357_1346104840585_256912_15134.class, "_CallBehaviorAction_CustomerCB", arguments138);
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

            public ConstraintExpression constraint139 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346105173460_397488_15297_existsDependency = null;

            public ElaborationRule elaborationRule140 = null;

            public ElaborationRule elaborationRule141 = null;

            public void init_17_0_5_edc0357_1346104807417_467678_15096Members() {
                try {
                    _17_0_5_edc0357_1346105173460_397488_15297_exists = new BooleanParameter("_17_0_5_edc0357_1346105173460_397488_15297_exists", this);
                    _17_0_5_edc0357_1346105157074_69873_15283 = new IntegerParameter("_17_0_5_edc0357_1346105157074_69873_15283", this);
                    _17_0_5_edc0357_1346105013928_873722_15220_endTime = new IntegerParameter("_17_0_5_edc0357_1346105013928_873722_15220_endTime", this);
                    cba_endTime = new IntegerParameter("cba_endTime", this);
                    constraint139 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346105013928_873722_15220_endTime)));
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
                constraintExpressions.add(constraint139);
                dependencies.add(_17_0_5_edc0357_1346105173460_397488_15297_existsDependency);
            }

            public void init_17_0_5_edc0357_1346104807417_467678_15096Elaborations() {
                Expression<?>[] arguments140 = new Expression<?>[2];
                arguments140[0] = new Expression<Integer>(cba_endTime);
                arguments140[1] = new Expression<Integer>(_17_0_5_edc0357_1346105157074_69873_15283);
                Expression<Boolean> condition140 = new Expression<Boolean>(true);
                elaborationRule140 = addElaborationRule(condition140, Customer.this, Customer._17_0_5_edc0357_1345510113560_522513_13778.class, "setDRCap_Activity_Customer", arguments140);
                Expression<?>[] arguments141 = new Expression<?>[1];
                arguments141[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition141 = new Expression<Boolean>(_17_0_5_edc0357_1346105173460_397488_15297_exists);
                elaborationRule141 = addElaborationRule(condition141, _17_0_5_edc0357_1346104706708_917454_14957.this, Customer._17_0_5_edc0357_1346104706708_917454_14957._17_0_5_edc0357_1346105173460_397488_15297.class, "_MergeNode_CustomerCB", arguments141);
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

            public IntegerParameter _17_0_5_edc0357_1346104784830_441700_15042_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1346105076614_639562_15240_exists = null;

            public IntegerParameter cba_endTime = null;

            public ConstraintExpression constraint142 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346105076614_639562_15240_existsDependency = null;

            public ElaborationRule elaborationRule143 = null;

            public ElaborationRule elaborationRule144 = null;

            public void init_17_0_5_edc0357_1346104812245_253068_15114Members() {
                try {
                    _17_0_5_edc0357_1346104784830_441700_15042_endTime = new IntegerParameter("_17_0_5_edc0357_1346104784830_441700_15042_endTime", this);
                    _17_0_5_edc0357_1346105076614_639562_15240_exists = new BooleanParameter("_17_0_5_edc0357_1346105076614_639562_15240_exists", this);
                    cba_endTime = new IntegerParameter("cba_endTime", this);
                    constraint142 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346104784830_441700_15042_endTime)));
                    _17_0_5_edc0357_1346105076614_639562_15240_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346105076614_639562_15240_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346104812245_253068_15114Collections() {
                parameters.add(_17_0_5_edc0357_1346104784830_441700_15042_endTime);
                parameters.add(_17_0_5_edc0357_1346105076614_639562_15240_exists);
                parameters.add(cba_endTime);
                constraintExpressions.add(constraint142);
                dependencies.add(_17_0_5_edc0357_1346105076614_639562_15240_existsDependency);
            }

            public void init_17_0_5_edc0357_1346104812245_253068_15114Elaborations() {
                Expression<?>[] arguments143 = new Expression<?>[1];
                arguments143[0] = new Expression<Integer>(cba_endTime);
                Expression<Boolean> condition143 = new Expression<Boolean>(true);
                elaborationRule143 = addElaborationRule(condition143, Customer.this, Customer._17_0_5_edc0357_1345510113557_625793_13776.class, "changePowerUsage_Activity_Customer", arguments143);
                Expression<?>[] arguments144 = new Expression<?>[1];
                arguments144[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition144 = new Expression<Boolean>(_17_0_5_edc0357_1346105076614_639562_15240_exists);
                elaborationRule144 = addElaborationRule(condition144, _17_0_5_edc0357_1346104706708_917454_14957.this, Customer._17_0_5_edc0357_1346104706708_917454_14957._17_0_5_edc0357_1346105076614_639562_15240.class, "_MergeNode_CustomerCB", arguments144);
            }

            public _17_0_5_edc0357_1346104812245_253068_15114() {
                super();
                init_17_0_5_edc0357_1346104812245_253068_15114Members();
                init_17_0_5_edc0357_1346104812245_253068_15114Collections();
                init_17_0_5_edc0357_1346104812245_253068_15114Elaborations();
            }

            public _17_0_5_edc0357_1346104812245_253068_15114(Expression<Integer> _17_0_5_edc0357_1346104784830_441700_15042_endTime) {
                super();
                init_17_0_5_edc0357_1346104812245_253068_15114Members();
                init_17_0_5_edc0357_1346104812245_253068_15114Collections();
                addDependency(this._17_0_5_edc0357_1346104784830_441700_15042_endTime, _17_0_5_edc0357_1346104784830_441700_15042_endTime);
                init_17_0_5_edc0357_1346104812245_253068_15114Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1346104840585_256912_15134 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1346104908659_55389_15177_exists = null;

            public IntegerParameter _17_0_5_edc0357_1346104794886_467330_15077_endTime = null;

            public IntegerParameter cba_endTime = null;

            public ConstraintExpression constraint145 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346104908659_55389_15177_existsDependency = null;

            public ElaborationRule elaborationRule146 = null;

            public ElaborationRule elaborationRule147 = null;

            public void init_17_0_5_edc0357_1346104840585_256912_15134Members() {
                try {
                    _17_0_5_edc0357_1346104908659_55389_15177_exists = new BooleanParameter("_17_0_5_edc0357_1346104908659_55389_15177_exists", this);
                    _17_0_5_edc0357_1346104794886_467330_15077_endTime = new IntegerParameter("_17_0_5_edc0357_1346104794886_467330_15077_endTime", this);
                    cba_endTime = new IntegerParameter("cba_endTime", this);
                    constraint145 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346104794886_467330_15077_endTime)));
                    _17_0_5_edc0357_1346104908659_55389_15177_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346104908659_55389_15177_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346104840585_256912_15134Collections() {
                parameters.add(_17_0_5_edc0357_1346104908659_55389_15177_exists);
                parameters.add(_17_0_5_edc0357_1346104794886_467330_15077_endTime);
                parameters.add(cba_endTime);
                constraintExpressions.add(constraint145);
                dependencies.add(_17_0_5_edc0357_1346104908659_55389_15177_existsDependency);
            }

            public void init_17_0_5_edc0357_1346104840585_256912_15134Elaborations() {
                Expression<?>[] arguments146 = new Expression<?>[1];
                arguments146[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition146 = new Expression<Boolean>(_17_0_5_edc0357_1346104908659_55389_15177_exists);
                elaborationRule146 = addElaborationRule(condition146, _17_0_5_edc0357_1346104706708_917454_14957.this, Customer._17_0_5_edc0357_1346104706708_917454_14957._17_0_5_edc0357_1346104908659_55389_15177.class, "_MergeNode_CustomerCB", arguments146);
                Expression<?>[] arguments147 = new Expression<?>[1];
                arguments147[0] = new Expression<Integer>(cba_endTime);
                Expression<Boolean> condition147 = new Expression<Boolean>(true);
                elaborationRule147 = addElaborationRule(condition147, Customer.this, Customer._17_0_5_edc0357_1345510113556_623276_13775.class, "usePower_Activity_Customer", arguments147);
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
/*
        public class _17_0_5_edc0357_1346104860701_80157_15146 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1346104773078_654914_15030_endTime = null;

            public ConstraintExpression constraint148 = null;

            public void init_17_0_5_edc0357_1346104860701_80157_15146Members() {
                try {
                    _17_0_5_edc0357_1346104773078_654914_15030_endTime = new IntegerParameter("_17_0_5_edc0357_1346104773078_654914_15030_endTime", this);
                    constraint148 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346104773078_654914_15030_endTime)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346104860701_80157_15146Collections() {
                parameters.add(_17_0_5_edc0357_1346104773078_654914_15030_endTime);
                constraintExpressions.add(constraint148);
            }

            public void init_17_0_5_edc0357_1346104860701_80157_15146Elaborations() {
            }

            public _17_0_5_edc0357_1346104860701_80157_15146() {
                super();
                init_17_0_5_edc0357_1346104860701_80157_15146Members();
                init_17_0_5_edc0357_1346104860701_80157_15146Collections();
                init_17_0_5_edc0357_1346104860701_80157_15146Elaborations();
            }

            public _17_0_5_edc0357_1346104860701_80157_15146(Expression<Integer> _17_0_5_edc0357_1346104773078_654914_15030_endTime) {
                super();
                init_17_0_5_edc0357_1346104860701_80157_15146Members();
                init_17_0_5_edc0357_1346104860701_80157_15146Collections();
                addDependency(this._17_0_5_edc0357_1346104773078_654914_15030_endTime, _17_0_5_edc0357_1346104773078_654914_15030_endTime);
                init_17_0_5_edc0357_1346104860701_80157_15146Elaborations();
                fixTimeDependencies();
            }
        }
*/
        public class _17_0_5_edc0357_1346104908659_55389_15177 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1346104794886_467330_15077_exists = null;

            public IntegerParameter invoker_endTime = null;

            public ConstraintExpression constraint149 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346104794886_467330_15077_existsDependency = null;

            public ElaborationRule elaborationRule150 = null;

            public void init_17_0_5_edc0357_1346104908659_55389_15177Members() {
                try {
                    _17_0_5_edc0357_1346104794886_467330_15077_exists = new BooleanParameter("_17_0_5_edc0357_1346104794886_467330_15077_exists", this);
                    invoker_endTime = new IntegerParameter("invoker_endTime", this);
                    constraint149 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(invoker_endTime)));
                    _17_0_5_edc0357_1346104794886_467330_15077_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346104794886_467330_15077_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346104908659_55389_15177Collections() {
                parameters.add(_17_0_5_edc0357_1346104794886_467330_15077_exists);
                parameters.add(invoker_endTime);
                constraintExpressions.add(constraint149);
                dependencies.add(_17_0_5_edc0357_1346104794886_467330_15077_existsDependency);
            }

            public void init_17_0_5_edc0357_1346104908659_55389_15177Elaborations() {
                Expression<?>[] arguments150 = new Expression<?>[1];
                arguments150[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition150 = new Expression<Boolean>(_17_0_5_edc0357_1346104794886_467330_15077_exists);
                elaborationRule150 = addElaborationRule(condition150, _17_0_5_edc0357_1346104706708_917454_14957.this, Customer._17_0_5_edc0357_1346104706708_917454_14957._17_0_5_edc0357_1346104794886_467330_15077.class, "_AcceptEventAction_CustomerCB", arguments150);
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

            public Parameter _17_0_5_edc0357_1346105104143_626038_15258 = null;

            public BooleanParameter _17_0_5_edc0357_1346104807417_467678_15096_exists = null;

            public ConstraintExpression constraint151 = null;

            public Dependency< Integer > _17_0_5_edc0357_1346105116990_449068_15259Dependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346104807417_467678_15096_existsDependency = null;

            public ElaborationRule elaborationRule152 = null;

            public void init_17_0_5_edc0357_1346105013928_873722_15220Members() {
                try {
                    _17_0_5_edc0357_1346104787974_430716_15054_endTime = new IntegerParameter("_17_0_5_edc0357_1346104787974_430716_15054_endTime", this);
                    _17_0_5_edc0357_1346105116990_449068_15259 = new IntegerParameter("_17_0_5_edc0357_1346105116990_449068_15259", this);
                    _17_0_5_edc0357_1346105104143_626038_15258 = new Parameter("_17_0_5_edc0357_1346105104143_626038_15258", null, null, this);
                    _17_0_5_edc0357_1346104807417_467678_15096_exists = new BooleanParameter("_17_0_5_edc0357_1346104807417_467678_15096_exists", this);
                    constraint151 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346104787974_430716_15054_endTime)));
                    _17_0_5_edc0357_1346105116990_449068_15259Dependency = new Dependency<Integer>(_17_0_5_edc0357_1346105116990_449068_15259, new Expression(new FunctionCall((Object) ((Power_System.Signaldr_request) _17_0_5_edc0357_1346105104143_626038_15258.getValue()).cap__17_0_5_edc0357_1345510113618_430459_13856.getValue(), Utils.getMethodForArgTypes("Customer._17_0_5_edc0357_1346104706708_917454_14957._17_0_5_edc0357_1346105013928_873722_15220", "generated", "getValue"), new Object[] { new Expression<Integer>(startTime) })));
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
                constraintExpressions.add(constraint151);
                dependencies.add(_17_0_5_edc0357_1346105116990_449068_15259Dependency);
                dependencies.add(_17_0_5_edc0357_1346104807417_467678_15096_existsDependency);
            }

            public void init_17_0_5_edc0357_1346105013928_873722_15220Elaborations() {
                Expression<?>[] arguments152 = new Expression<?>[2];
                arguments152[0] = new Expression<Integer>(endTime);
                arguments152[1] = new Expression<Integer>(_17_0_5_edc0357_1346105116990_449068_15259);
                Expression<Boolean> condition152 = new Expression<Boolean>(_17_0_5_edc0357_1346104807417_467678_15096_exists);
                elaborationRule152 = addElaborationRule(condition152, _17_0_5_edc0357_1346104706708_917454_14957.this, Customer._17_0_5_edc0357_1346104706708_917454_14957._17_0_5_edc0357_1346104807417_467678_15096.class, "_CallBehaviorAction_CustomerCB", arguments152);
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

            public BooleanParameter _17_0_5_edc0357_1346104784830_441700_15042_exists = null;

            public ConstraintExpression constraint153 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346104784830_441700_15042_existsDependency = null;

            public ElaborationRule elaborationRule154 = null;

            public void init_17_0_5_edc0357_1346105076614_639562_15240Members() {
                try {
                    invoker_endTime = new IntegerParameter("invoker_endTime", this);
                    _17_0_5_edc0357_1346104784830_441700_15042_exists = new BooleanParameter("_17_0_5_edc0357_1346104784830_441700_15042_exists", this);
                    constraint153 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(invoker_endTime)));
                    _17_0_5_edc0357_1346104784830_441700_15042_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346104784830_441700_15042_exists, new Expression(new FunctionCall((Object) q_Customer_changeUsage, Utils.getMethodForArgTypes("Customer._17_0_5_edc0357_1346104706708_917454_14957._17_0_5_edc0357_1346105076614_639562_15240", "generated", "hasStuff"), new Object[] { new Expression<Integer>(endTime) })));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346105076614_639562_15240Collections() {
                parameters.add(invoker_endTime);
                parameters.add(_17_0_5_edc0357_1346104784830_441700_15042_exists);
                constraintExpressions.add(constraint153);
                dependencies.add(_17_0_5_edc0357_1346104784830_441700_15042_existsDependency);
            }

            public void init_17_0_5_edc0357_1346105076614_639562_15240Elaborations() {
                Expression<?>[] arguments154 = new Expression<?>[1];
                arguments154[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition154 = new Expression<Boolean>(_17_0_5_edc0357_1346104784830_441700_15042_exists);
                elaborationRule154 = addElaborationRule(condition154, _17_0_5_edc0357_1346104706708_917454_14957.this, Customer._17_0_5_edc0357_1346104706708_917454_14957._17_0_5_edc0357_1346104784830_441700_15042.class, "_AcceptEventAction_CustomerCB", arguments154);
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

            public IntegerParameter objectToPass = null;

            public ConstraintExpression constraint155 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346104787974_430716_15054_existsDependency = null;

            public ElaborationRule elaborationRule156 = null;

            public void init_17_0_5_edc0357_1346105173460_397488_15297Members() {
                try {
                    invoker_endTime = new IntegerParameter("invoker_endTime", this);
                    _17_0_5_edc0357_1346104787974_430716_15054_exists = new BooleanParameter("_17_0_5_edc0357_1346104787974_430716_15054_exists", this);
                    objectToPass = new IntegerParameter("objectToPass", this);
                    constraint155 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(invoker_endTime)));
                    _17_0_5_edc0357_1346104787974_430716_15054_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346104787974_430716_15054_exists, new Expression(new FunctionCall((Object) q_Customer_dr_request, Utils.getMethodForArgTypes("ObjectFlow<Power_System.dr_request>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1346105173460_397488_15297Collections() {
                parameters.add(invoker_endTime);
                parameters.add(_17_0_5_edc0357_1346104787974_430716_15054_exists);
                parameters.add(objectToPass);
                constraintExpressions.add(constraint155);
                dependencies.add(_17_0_5_edc0357_1346104787974_430716_15054_existsDependency);
            }

            public void init_17_0_5_edc0357_1346105173460_397488_15297Elaborations() {
                Expression<?>[] arguments156 = new Expression<?>[1];
                arguments156[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition156 = new Expression<Boolean>(_17_0_5_edc0357_1346104787974_430716_15054_exists);
                elaborationRule156 = addElaborationRule(condition156, _17_0_5_edc0357_1346104706708_917454_14957.this, Customer._17_0_5_edc0357_1346104706708_917454_14957._17_0_5_edc0357_1346104787974_430716_15054.class, "_AcceptEventAction_CustomerCB", arguments156);
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

            public ConstraintExpression constraint157 = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1346104764223_34184_15024_existsDependency = null;

            public ElaborationRule elaborationRule158 = null;

            public void init_17_0_5_edc0357_1348000884142_707118_14045Members() {
                try {
                    _17_0_5_edc0357_1346104727721_22566_14990_endTime = new IntegerParameter("_17_0_5_edc0357_1346104727721_22566_14990_endTime", this);
                    _17_0_5_edc0357_1346104764223_34184_15024_exists = new BooleanParameter("_17_0_5_edc0357_1346104764223_34184_15024_exists", this);
                    constraint157 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1346104727721_22566_14990_endTime)));
                    durationDependency = new Dependency<Integer>(duration, new Expression<Integer>(20));
                    _17_0_5_edc0357_1346104764223_34184_15024_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1346104764223_34184_15024_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1348000884142_707118_14045Collections() {
                parameters.add(_17_0_5_edc0357_1346104727721_22566_14990_endTime);
                parameters.add(_17_0_5_edc0357_1346104764223_34184_15024_exists);
                constraintExpressions.add(constraint157);
                removeDependency(duration);
                dependencies.add(durationDependency);
                dependencies.add(_17_0_5_edc0357_1346104764223_34184_15024_existsDependency);
            }

            public void init_17_0_5_edc0357_1348000884142_707118_14045Elaborations() {
                Expression<?>[] arguments158 = new Expression<?>[1];
                arguments158[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition158 = new Expression<Boolean>(_17_0_5_edc0357_1346104764223_34184_15024_exists);
                elaborationRule158 = addElaborationRule(condition158, _17_0_5_edc0357_1346104706708_917454_14957.this, Customer._17_0_5_edc0357_1346104706708_917454_14957._17_0_5_edc0357_1346104764223_34184_15024.class, "_ForkNode_CustomerCB", arguments158);
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
        this.x = x;
    }
}
