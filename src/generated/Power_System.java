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

public class Power_System extends ParameterListenerImpl {

    public Parameter ss_17_0_5_edc0357_1345510113564_305135_13782_yes = null;

    public Parameter ss_17_0_5_edc0357_1345510113565_310611_13783_receiveMeterReading = null;

    public Parameter ss_17_0_5_edc0357_1345510113564_305135_13782_no = null;

    public StringParameter classifierBehavior = null;

    public Parameter c = null;

    public Parameter ss_17_0_5_edc0357_1345510113549_985081_13767_receiveLoadReading = null;

    public Parameter ss_17_0_5_edc0357_1345510113607_182740_13840_changeGenerationValue = null;

    public Parameter l = null;

    public Parameter ss_17_0_5_edc0357_1345510113562_98635_13780_changeLoadValue = null;

    public Parameter p = null;

    public Parameter ss_17_0_5_edc0357_1345510113550_816176_13768_receiveGenReading = null;

    public Parameter ss_17_0_5_edc0357_1345510113605_306486_13837_dr_request = null;

    public void initPower_SystemMembers() {
        try {
            ss_17_0_5_edc0357_1345510113564_305135_13782_yes = new Parameter("ss_17_0_5_edc0357_1345510113564_305135_13782_yes", null, new ObjectFlow("ss_17_0_5_edc0357_1345510113564_305135_13782_yes", Power_System.Signalyes.class), this);
            ss_17_0_5_edc0357_1345510113565_310611_13783_receiveMeterReading = new Parameter("ss_17_0_5_edc0357_1345510113565_310611_13783_receiveMeterReading", null, new ObjectFlow("ss_17_0_5_edc0357_1345510113565_310611_13783_receiveMeterReading", Power_System.SignalreceiveMeterReading.class), this);
            ss_17_0_5_edc0357_1345510113564_305135_13782_no = new Parameter("ss_17_0_5_edc0357_1345510113564_305135_13782_no", null, new ObjectFlow("ss_17_0_5_edc0357_1345510113564_305135_13782_no", Power_System.Signalno.class), this);
            classifierBehavior = new StringParameter("classifierBehavior", this);
            c = new Parameter("c", null, new Customer(this), this);
            ss_17_0_5_edc0357_1345510113549_985081_13767_receiveLoadReading = new Parameter("ss_17_0_5_edc0357_1345510113549_985081_13767_receiveLoadReading", null, new ObjectFlow("ss_17_0_5_edc0357_1345510113549_985081_13767_receiveLoadReading", Power_System.SignalreceiveLoadReading.class), this);
            ss_17_0_5_edc0357_1345510113607_182740_13840_changeGenerationValue = new Parameter("ss_17_0_5_edc0357_1345510113607_182740_13840_changeGenerationValue", null, new ObjectFlow("ss_17_0_5_edc0357_1345510113607_182740_13840_changeGenerationValue", Power_System.SignalchangeGenerationValue.class), this);
            l = new Parameter("l", null, new LADWP(this), this);
            ss_17_0_5_edc0357_1345510113562_98635_13780_changeLoadValue = new Parameter("ss_17_0_5_edc0357_1345510113562_98635_13780_changeLoadValue", null, new ObjectFlow("ss_17_0_5_edc0357_1345510113562_98635_13780_changeLoadValue", Power_System.SignalchangeLoadValue.class), this);
            p = new Parameter("p", null, new Power(this), this);
            ss_17_0_5_edc0357_1345510113550_816176_13768_receiveGenReading = new Parameter("ss_17_0_5_edc0357_1345510113550_816176_13768_receiveGenReading", null, new ObjectFlow("ss_17_0_5_edc0357_1345510113550_816176_13768_receiveGenReading", Power_System.SignalreceiveGenReading.class), this);
            ss_17_0_5_edc0357_1345510113605_306486_13837_dr_request = new Parameter("ss_17_0_5_edc0357_1345510113605_306486_13837_dr_request", null, new ObjectFlow("ss_17_0_5_edc0357_1345510113605_306486_13837_dr_request", Power_System.Signaldr_request.class), this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void initPower_SystemCollections() {
        parameters.add(ss_17_0_5_edc0357_1345510113564_305135_13782_yes);
        parameters.add(ss_17_0_5_edc0357_1345510113565_310611_13783_receiveMeterReading);
        parameters.add(ss_17_0_5_edc0357_1345510113564_305135_13782_no);
        parameters.add(classifierBehavior);
        parameters.add(c);
        parameters.add(ss_17_0_5_edc0357_1345510113549_985081_13767_receiveLoadReading);
        parameters.add(ss_17_0_5_edc0357_1345510113607_182740_13840_changeGenerationValue);
        parameters.add(l);
        parameters.add(ss_17_0_5_edc0357_1345510113562_98635_13780_changeLoadValue);
        parameters.add(p);
        parameters.add(ss_17_0_5_edc0357_1345510113550_816176_13768_receiveGenReading);
        parameters.add(ss_17_0_5_edc0357_1345510113605_306486_13837_dr_request);
    }

    public void initPower_SystemElaborations() {
    }



    public class _17_0_5_edc0357_1345510113586_863604_13814 extends DurativeEvent {

        public Parameter sig_17_0_5_edc0357_1345510114227_570675_14436 = null;

        public IntegerParameter invoke_time = null;

        public Parameter sig_17_0_5_edc0357_1345510114227_991374_14433 = null;

        public ElaborationRule elaborationRule0 = null;

        public void init_17_0_5_edc0357_1345510113586_863604_13814Members() {
            try {
                sig_17_0_5_edc0357_1345510114227_570675_14436 = new Parameter("sig_17_0_5_edc0357_1345510114227_570675_14436", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114227_570675_14436"), this);
                invoke_time = new IntegerParameter("invoke_time", this);
                sig_17_0_5_edc0357_1345510114227_991374_14433 = new Parameter("sig_17_0_5_edc0357_1345510114227_991374_14433", null, new ObjectFlow("sig_17_0_5_edc0357_1345510114227_991374_14433"), this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void init_17_0_5_edc0357_1345510113586_863604_13814Collections() {
            parameters.add(sig_17_0_5_edc0357_1345510114227_570675_14436);
            parameters.add(invoke_time);
            parameters.add(sig_17_0_5_edc0357_1345510114227_991374_14433);
        }

        public void init_17_0_5_edc0357_1345510113586_863604_13814Elaborations() {
            Expression<?>[] arguments0 = new Expression<?>[1];
            arguments0[0] = new Expression<Integer>(invoke_time);
            Expression<Boolean> condition0 = new Expression<Boolean>(true);
            elaborationRule0 = addElaborationRule(condition0, _17_0_5_edc0357_1345510113586_863604_13814.this, Power_System._17_0_5_edc0357_1345510113586_863604_13814._17_0_5_edc0357_1345510114224_875365_14417.class, "_InitialNode_start_system", arguments0);
        }

        public _17_0_5_edc0357_1345510113586_863604_13814() {
            super();
            init_17_0_5_edc0357_1345510113586_863604_13814Members();
            init_17_0_5_edc0357_1345510113586_863604_13814Collections();
            init_17_0_5_edc0357_1345510113586_863604_13814Elaborations();
        }

        public class _17_0_5_edc0357_1345510114224_875365_14417 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1345510114225_872340_14418_exists = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114225_872340_14418_existsDependency = null;

            public ElaborationRule elaborationRule1 = null;

            public void init_17_0_5_edc0357_1345510114224_875365_14417Members() {
                try {
                    _17_0_5_edc0357_1345510114225_872340_14418_exists = new BooleanParameter("_17_0_5_edc0357_1345510114225_872340_14418_exists", this);
                    _17_0_5_edc0357_1345510114225_872340_14418_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114225_872340_14418_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114224_875365_14417Collections() {
                parameters.add(_17_0_5_edc0357_1345510114225_872340_14418_exists);
                dependencies.add(_17_0_5_edc0357_1345510114225_872340_14418_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510114224_875365_14417Elaborations() {
                Expression<?>[] arguments1 = new Expression<?>[1];
                arguments1[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition1 = new Expression<Boolean>(_17_0_5_edc0357_1345510114225_872340_14418_exists);
                elaborationRule1 = addElaborationRule(condition1, _17_0_5_edc0357_1345510113586_863604_13814.this, Power_System._17_0_5_edc0357_1345510113586_863604_13814._17_0_5_edc0357_1345510114225_872340_14418.class, "_ReadSelfAction_start_system", arguments1);
            }

            public _17_0_5_edc0357_1345510114224_875365_14417() {
                super();
                init_17_0_5_edc0357_1345510114224_875365_14417Members();
                init_17_0_5_edc0357_1345510114224_875365_14417Collections();
                init_17_0_5_edc0357_1345510114224_875365_14417Elaborations();
            }

            public _17_0_5_edc0357_1345510114224_875365_14417(Expression<Integer> startTime) {
                super();
                init_17_0_5_edc0357_1345510114224_875365_14417Members();
                init_17_0_5_edc0357_1345510114224_875365_14417Collections();
                removeDependency(this.startTime);
                addDependency(this.startTime, startTime);
                init_17_0_5_edc0357_1345510114224_875365_14417Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114225_872340_14418 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1345510114225_676052_14419_exists = null;

            public IntegerParameter _17_0_5_edc0357_1345510114224_875365_14417_endTime = null;

            public Parameter _17_0_5_edc0357_1345510114833_363806_15210 = null;

            public ConstraintExpression constraint2 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114225_676052_14419_existsDependency = null;

            public ElaborationRule elaborationRule3 = null;

            public void init_17_0_5_edc0357_1345510114225_872340_14418Members() {
                try {
                    _17_0_5_edc0357_1345510114225_676052_14419_exists = new BooleanParameter("_17_0_5_edc0357_1345510114225_676052_14419_exists", this);
                    _17_0_5_edc0357_1345510114224_875365_14417_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114224_875365_14417_endTime", this);
                    _17_0_5_edc0357_1345510114833_363806_15210 = new Parameter("_17_0_5_edc0357_1345510114833_363806_15210", null, Power_System.this, this);
                    constraint2 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114224_875365_14417_endTime)));
                    _17_0_5_edc0357_1345510114225_676052_14419_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114225_676052_14419_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114225_872340_14418Collections() {
                parameters.add(_17_0_5_edc0357_1345510114225_676052_14419_exists);
                parameters.add(_17_0_5_edc0357_1345510114224_875365_14417_endTime);
                parameters.add(_17_0_5_edc0357_1345510114833_363806_15210);
                constraintExpressions.add(constraint2);
                dependencies.add(_17_0_5_edc0357_1345510114225_676052_14419_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510114225_872340_14418Elaborations() {
                Expression<?>[] arguments3 = new Expression<?>[2];
                arguments3[0] = new Expression<Integer>(endTime);
                arguments3[1] = new Expression<Power_System>(_17_0_5_edc0357_1345510114833_363806_15210);
                Expression<Boolean> condition3 = new Expression<Boolean>(_17_0_5_edc0357_1345510114225_676052_14419_exists);
                elaborationRule3 = addElaborationRule(condition3, _17_0_5_edc0357_1345510113586_863604_13814.this, Power_System._17_0_5_edc0357_1345510113586_863604_13814._17_0_5_edc0357_1345510114225_676052_14419.class, "_ForkNode_start_system", arguments3);
            }

            public _17_0_5_edc0357_1345510114225_872340_14418() {
                super();
                init_17_0_5_edc0357_1345510114225_872340_14418Members();
                init_17_0_5_edc0357_1345510114225_872340_14418Collections();
                init_17_0_5_edc0357_1345510114225_872340_14418Elaborations();
            }

            public _17_0_5_edc0357_1345510114225_872340_14418(Expression<Integer> _17_0_5_edc0357_1345510114224_875365_14417_endTime) {
                super();
                init_17_0_5_edc0357_1345510114225_872340_14418Members();
                init_17_0_5_edc0357_1345510114225_872340_14418Collections();
                addDependency(this._17_0_5_edc0357_1345510114224_875365_14417_endTime, _17_0_5_edc0357_1345510114224_875365_14417_endTime);
                init_17_0_5_edc0357_1345510114225_872340_14418Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114225_676052_14419 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1345510114225_927143_14421_exists = null;

            public BooleanParameter _17_0_5_edc0357_1345510114226_580474_14425_exists = null;

            public BooleanParameter _17_0_5_edc0357_1345510114225_255738_14420_exists = null;

            public Parameter objectToPass = null;

            public IntegerParameter _17_0_5_edc0357_1345510114225_872340_14418_endTime = null;

            public ConstraintExpression constraint4 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114225_927143_14421_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114226_580474_14425_existsDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114225_255738_14420_existsDependency = null;

            public ElaborationRule elaborationRule5 = null;

            public ElaborationRule elaborationRule6 = null;

            public ElaborationRule elaborationRule7 = null;

            public void init_17_0_5_edc0357_1345510114225_676052_14419Members() {
                try {
                    _17_0_5_edc0357_1345510114225_927143_14421_exists = new BooleanParameter("_17_0_5_edc0357_1345510114225_927143_14421_exists", this);
                    _17_0_5_edc0357_1345510114226_580474_14425_exists = new BooleanParameter("_17_0_5_edc0357_1345510114226_580474_14425_exists", this);
                    _17_0_5_edc0357_1345510114225_255738_14420_exists = new BooleanParameter("_17_0_5_edc0357_1345510114225_255738_14420_exists", this);
                    objectToPass = new Parameter("objectToPass", null, null, this);
                    _17_0_5_edc0357_1345510114225_872340_14418_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114225_872340_14418_endTime", this);
                    constraint4 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114225_872340_14418_endTime)));
                    _17_0_5_edc0357_1345510114225_927143_14421_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114225_927143_14421_exists, new Expression<Boolean>(true));
                    _17_0_5_edc0357_1345510114226_580474_14425_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114226_580474_14425_exists, new Expression<Boolean>(true));
                    _17_0_5_edc0357_1345510114225_255738_14420_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114225_255738_14420_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114225_676052_14419Collections() {
                parameters.add(_17_0_5_edc0357_1345510114225_927143_14421_exists);
                parameters.add(_17_0_5_edc0357_1345510114226_580474_14425_exists);
                parameters.add(_17_0_5_edc0357_1345510114225_255738_14420_exists);
                parameters.add(objectToPass);
                parameters.add(_17_0_5_edc0357_1345510114225_872340_14418_endTime);
                constraintExpressions.add(constraint4);
                dependencies.add(_17_0_5_edc0357_1345510114225_927143_14421_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510114226_580474_14425_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510114225_255738_14420_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510114225_676052_14419Elaborations() {
                Expression<?>[] arguments5 = new Expression<?>[2];
                arguments5[0] = new Expression<Integer>(endTime);
                arguments5[1] = new Expression<Power_System>(objectToPass);
                Expression<Boolean> condition5 = new Expression<Boolean>(_17_0_5_edc0357_1345510114226_580474_14425_exists);
                elaborationRule5 = addElaborationRule(condition5, _17_0_5_edc0357_1345510113586_863604_13814.this, Power_System._17_0_5_edc0357_1345510113586_863604_13814._17_0_5_edc0357_1345510114226_580474_14425.class, "_ReadStructuralFeatureAction_start_system", arguments5);
                Expression<?>[] arguments6 = new Expression<?>[2];
                arguments6[0] = new Expression<Integer>(endTime);
                arguments6[1] = new Expression<Power_System>(objectToPass);
                Expression<Boolean> condition6 = new Expression<Boolean>(_17_0_5_edc0357_1345510114225_255738_14420_exists);
                elaborationRule6 = addElaborationRule(condition6, _17_0_5_edc0357_1345510113586_863604_13814.this, Power_System._17_0_5_edc0357_1345510113586_863604_13814._17_0_5_edc0357_1345510114225_255738_14420.class, "_ReadStructuralFeatureAction_start_system", arguments6);
                Expression<?>[] arguments7 = new Expression<?>[2];
                arguments7[0] = new Expression<Integer>(endTime);
                arguments7[1] = new Expression<Power_System>(objectToPass);
                Expression<Boolean> condition7 = new Expression<Boolean>(_17_0_5_edc0357_1345510114225_927143_14421_exists);
                elaborationRule7 = addElaborationRule(condition7, _17_0_5_edc0357_1345510113586_863604_13814.this, Power_System._17_0_5_edc0357_1345510113586_863604_13814._17_0_5_edc0357_1345510114225_927143_14421.class, "_ReadStructuralFeatureAction_start_system", arguments7);
            }

            public _17_0_5_edc0357_1345510114225_676052_14419() {
                super();
                init_17_0_5_edc0357_1345510114225_676052_14419Members();
                init_17_0_5_edc0357_1345510114225_676052_14419Collections();
                init_17_0_5_edc0357_1345510114225_676052_14419Elaborations();
            }

            public _17_0_5_edc0357_1345510114225_676052_14419(Expression<Integer> _17_0_5_edc0357_1345510114225_872340_14418_endTime, Expression<Power_System> objectToPass) {
                super();
                init_17_0_5_edc0357_1345510114225_676052_14419Members();
                init_17_0_5_edc0357_1345510114225_676052_14419Collections();
                addDependency(this._17_0_5_edc0357_1345510114225_872340_14418_endTime, _17_0_5_edc0357_1345510114225_872340_14418_endTime);
                addDependency(this.objectToPass, objectToPass);
                init_17_0_5_edc0357_1345510114225_676052_14419Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114225_255738_14420 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1345510114225_676052_14419_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1345510114225_19712_14422_exists = null;

            public Parameter _17_0_5_edc0357_1345510114834_104458_15211 = null;

            public Parameter _17_0_5_edc0357_1345510114834_33609_15212 = null;

            public ConstraintExpression constraint8 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114225_19712_14422_existsDependency = null;

            public Dependency< Customer > _17_0_5_edc0357_1345510114834_104458_15211Dependency = null;

            public ElaborationRule elaborationRule9 = null;

            public void init_17_0_5_edc0357_1345510114225_255738_14420Members() {
                try {
                    _17_0_5_edc0357_1345510114225_676052_14419_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114225_676052_14419_endTime", this);
                    _17_0_5_edc0357_1345510114225_19712_14422_exists = new BooleanParameter("_17_0_5_edc0357_1345510114225_19712_14422_exists", this);
                    _17_0_5_edc0357_1345510114834_104458_15211 = new Parameter("_17_0_5_edc0357_1345510114834_104458_15211", null, null, this);
                    _17_0_5_edc0357_1345510114834_33609_15212 = new Parameter("_17_0_5_edc0357_1345510114834_33609_15212", null, null, this);
                    constraint8 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114225_676052_14419_endTime)));
                    _17_0_5_edc0357_1345510114225_19712_14422_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114225_19712_14422_exists, new Expression<Boolean>(true));
                    _17_0_5_edc0357_1345510114834_104458_15211Dependency = new Dependency<Customer>(_17_0_5_edc0357_1345510114834_104458_15211, new Expression<Customer>(((Customer) _17_0_5_edc0357_1345510114834_33609_15212.getMember("c"))));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114225_255738_14420Collections() {
                parameters.add(_17_0_5_edc0357_1345510114225_676052_14419_endTime);
                parameters.add(_17_0_5_edc0357_1345510114225_19712_14422_exists);
                parameters.add(_17_0_5_edc0357_1345510114834_104458_15211);
                parameters.add(_17_0_5_edc0357_1345510114834_33609_15212);
                constraintExpressions.add(constraint8);
                dependencies.add(_17_0_5_edc0357_1345510114225_19712_14422_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510114834_104458_15211Dependency);
            }

            public void init_17_0_5_edc0357_1345510114225_255738_14420Elaborations() {
                Expression<?>[] arguments9 = new Expression<?>[2];
                arguments9[0] = new Expression<Integer>(endTime);
                arguments9[1] = new Expression<Customer>(_17_0_5_edc0357_1345510114834_104458_15211);
                Expression<Boolean> condition9 = new Expression<Boolean>(_17_0_5_edc0357_1345510114225_19712_14422_exists);
                elaborationRule9 = addElaborationRule(condition9, _17_0_5_edc0357_1345510113586_863604_13814.this, Power_System._17_0_5_edc0357_1345510113586_863604_13814._17_0_5_edc0357_1345510114225_19712_14422.class, "sobc_StartObjectBehaviorAction_start_system", arguments9);
            }

            public _17_0_5_edc0357_1345510114225_255738_14420() {
                super();
                init_17_0_5_edc0357_1345510114225_255738_14420Members();
                init_17_0_5_edc0357_1345510114225_255738_14420Collections();
                init_17_0_5_edc0357_1345510114225_255738_14420Elaborations();
            }

            public _17_0_5_edc0357_1345510114225_255738_14420(Expression<Integer> _17_0_5_edc0357_1345510114225_676052_14419_endTime, Expression<Power_System> _17_0_5_edc0357_1345510114834_33609_15212) {
                super();
                init_17_0_5_edc0357_1345510114225_255738_14420Members();
                init_17_0_5_edc0357_1345510114225_255738_14420Collections();
                addDependency(this._17_0_5_edc0357_1345510114225_676052_14419_endTime, _17_0_5_edc0357_1345510114225_676052_14419_endTime);
                addDependency(this._17_0_5_edc0357_1345510114834_33609_15212, _17_0_5_edc0357_1345510114834_33609_15212);
                init_17_0_5_edc0357_1345510114225_255738_14420Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114225_927143_14421 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1345510114225_676052_14419_endTime = null;

            public Parameter _17_0_5_edc0357_1345510114835_226978_15213 = null;

            public Parameter _17_0_5_edc0357_1345510114836_890496_15214 = null;

            public ConstraintExpression constraint10 = null;

            public Dependency< Power > _17_0_5_edc0357_1345510114835_226978_15213Dependency = null;

            public Effect effect11 = null;

            public Object effect11Var = null;

            public void init_17_0_5_edc0357_1345510114225_927143_14421Members() {
                try {
                    _17_0_5_edc0357_1345510114225_676052_14419_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114225_676052_14419_endTime", this);
                    _17_0_5_edc0357_1345510114835_226978_15213 = new Parameter("_17_0_5_edc0357_1345510114835_226978_15213", null, null, this);
                    _17_0_5_edc0357_1345510114836_890496_15214 = new Parameter("_17_0_5_edc0357_1345510114836_890496_15214", null, null, this);
                    constraint10 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114225_676052_14419_endTime)));
                    _17_0_5_edc0357_1345510114835_226978_15213Dependency = new Dependency<Power>(_17_0_5_edc0357_1345510114835_226978_15213, new Expression<Power>(((Power) _17_0_5_edc0357_1345510114836_890496_15214.getMember("p"))));
                    effect11Var = sig_17_0_5_edc0357_1345510114227_991374_14433;
                    effect11 = new EffectFunction(new FunctionCall((Object) effect11Var, Utils.getMethodForArgTypes("ObjectFlow<Power>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { _17_0_5_edc0357_1345510114835_226978_15213, startTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114225_927143_14421Collections() {
                parameters.add(_17_0_5_edc0357_1345510114225_676052_14419_endTime);
                parameters.add(_17_0_5_edc0357_1345510114835_226978_15213);
                parameters.add(_17_0_5_edc0357_1345510114836_890496_15214);
                constraintExpressions.add(constraint10);
                dependencies.add(_17_0_5_edc0357_1345510114835_226978_15213Dependency);
                Set<Effect> effectsForeffect11Var = new HashSet<Effect>();
                effectsForeffect11Var.add(effect11);
                effects.put((Parameter<?>) effect11Var, effectsForeffect11Var);
            }

            public void init_17_0_5_edc0357_1345510114225_927143_14421Elaborations() {
            }

            public _17_0_5_edc0357_1345510114225_927143_14421() {
                super();
                init_17_0_5_edc0357_1345510114225_927143_14421Members();
                init_17_0_5_edc0357_1345510114225_927143_14421Collections();
                init_17_0_5_edc0357_1345510114225_927143_14421Elaborations();
            }

            public _17_0_5_edc0357_1345510114225_927143_14421(Expression<Integer> _17_0_5_edc0357_1345510114225_676052_14419_endTime, Expression<Power_System> _17_0_5_edc0357_1345510114836_890496_15214) {
                super();
                init_17_0_5_edc0357_1345510114225_927143_14421Members();
                init_17_0_5_edc0357_1345510114225_927143_14421Collections();
                addDependency(this._17_0_5_edc0357_1345510114225_676052_14419_endTime, _17_0_5_edc0357_1345510114225_676052_14419_endTime);
                addDependency(this._17_0_5_edc0357_1345510114836_890496_15214, _17_0_5_edc0357_1345510114836_890496_15214);
                init_17_0_5_edc0357_1345510114225_927143_14421Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114225_19712_14422 extends DurativeEvent {

            public Parameter _17_0_5_edc0357_1345510114836_265959_15215 = null;

            public IntegerParameter _17_0_5_edc0357_1345510114225_255738_14420_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1345510114226_959292_14423_exists = null;

            public ConstraintExpression constraint12 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114226_959292_14423_existsDependency = null;

            public ElaborationRule elaborationRule13 = null;

            public ElaborationRule elaborationRule14 = null;

            public void init_17_0_5_edc0357_1345510114225_19712_14422Members() {
                try {
                    _17_0_5_edc0357_1345510114836_265959_15215 = new Parameter("_17_0_5_edc0357_1345510114836_265959_15215", null, null, this);
                    _17_0_5_edc0357_1345510114225_255738_14420_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114225_255738_14420_endTime", this);
                    _17_0_5_edc0357_1345510114226_959292_14423_exists = new BooleanParameter("_17_0_5_edc0357_1345510114226_959292_14423_exists", this);
                    constraint12 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114225_255738_14420_endTime)));
                    _17_0_5_edc0357_1345510114226_959292_14423_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114226_959292_14423_exists, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114227_991374_14433, Utils.getMethodForArgTypes("ObjectFlow<Power>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114225_19712_14422Collections() {
                parameters.add(_17_0_5_edc0357_1345510114836_265959_15215);
                parameters.add(_17_0_5_edc0357_1345510114225_255738_14420_endTime);
                parameters.add(_17_0_5_edc0357_1345510114226_959292_14423_exists);
                constraintExpressions.add(constraint12);
                dependencies.add(_17_0_5_edc0357_1345510114226_959292_14423_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510114225_19712_14422Elaborations() {
                Expression<?>[] arguments13 = new Expression<?>[1];
                arguments13[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition13 = new Expression<Boolean>(_17_0_5_edc0357_1345510114226_959292_14423_exists);
                elaborationRule13 = addElaborationRule(condition13, _17_0_5_edc0357_1345510113586_863604_13814.this, Power_System._17_0_5_edc0357_1345510113586_863604_13814._17_0_5_edc0357_1345510114226_959292_14423.class, "sobp_StartObjectBehaviorAction_start_system", arguments13);
                Expression<?>[] arguments14 = new Expression<?>[1];
                arguments14[0] = new Expression<Integer>(startTime);
                Expression<Boolean> condition14 = new Expression<Boolean>(true);
                elaborationRule14 = addElaborationRule(condition14, c, Customer._17_0_5_edc0357_1346104706708_917454_14957.class, "CustomerCB_Activity_Customer", arguments14);
            }

            public _17_0_5_edc0357_1345510114225_19712_14422() {
                super();
                init_17_0_5_edc0357_1345510114225_19712_14422Members();
                init_17_0_5_edc0357_1345510114225_19712_14422Collections();
                init_17_0_5_edc0357_1345510114225_19712_14422Elaborations();
            }

            public _17_0_5_edc0357_1345510114225_19712_14422(Expression<Integer> _17_0_5_edc0357_1345510114225_255738_14420_endTime, Expression<Customer> _17_0_5_edc0357_1345510114836_265959_15215) {
                super();
                init_17_0_5_edc0357_1345510114225_19712_14422Members();
                init_17_0_5_edc0357_1345510114225_19712_14422Collections();
                addDependency(this._17_0_5_edc0357_1345510114225_255738_14420_endTime, _17_0_5_edc0357_1345510114225_255738_14420_endTime);
                addDependency(this._17_0_5_edc0357_1345510114836_265959_15215, _17_0_5_edc0357_1345510114836_265959_15215);
                init_17_0_5_edc0357_1345510114225_19712_14422Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114226_959292_14423 extends DurativeEvent {

            public Parameter _17_0_5_edc0357_1345510114837_924145_15216 = null;

            public BooleanParameter _17_0_5_edc0357_1345510114226_407269_14426_exists = null;

            public IntegerParameter _17_0_5_edc0357_1345510114225_19712_14422_endTime = null;

            public ConstraintExpression constraint15 = null;

            public Dependency< Power > _17_0_5_edc0357_1345510114837_924145_15216Dependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1345510114226_407269_14426_existsDependency = null;

            public ElaborationRule elaborationRule16 = null;

            public ElaborationRule elaborationRule17 = null;

            public void init_17_0_5_edc0357_1345510114226_959292_14423Members() {
                try {
                    _17_0_5_edc0357_1345510114837_924145_15216 = new Parameter("_17_0_5_edc0357_1345510114837_924145_15216", null, null, this);
                    _17_0_5_edc0357_1345510114226_407269_14426_exists = new BooleanParameter("_17_0_5_edc0357_1345510114226_407269_14426_exists", this);
                    _17_0_5_edc0357_1345510114225_19712_14422_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114225_19712_14422_endTime", this);
                    constraint15 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114225_19712_14422_endTime)));
                    _17_0_5_edc0357_1345510114837_924145_15216Dependency = new Dependency<Power>(_17_0_5_edc0357_1345510114837_924145_15216, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114227_991374_14433, Utils.getMethodForArgTypes("ObjectFlow<Power>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                    _17_0_5_edc0357_1345510114226_407269_14426_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1345510114226_407269_14426_exists, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114227_570675_14436, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "hasStuff", java.lang.Integer.class), new Object[] { new Expression<Integer>(endTime) })));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114226_959292_14423Collections() {
                parameters.add(_17_0_5_edc0357_1345510114837_924145_15216);
                parameters.add(_17_0_5_edc0357_1345510114226_407269_14426_exists);
                parameters.add(_17_0_5_edc0357_1345510114225_19712_14422_endTime);
                constraintExpressions.add(constraint15);
                dependencies.add(_17_0_5_edc0357_1345510114837_924145_15216Dependency);
                dependencies.add(_17_0_5_edc0357_1345510114226_407269_14426_existsDependency);
            }

            public void init_17_0_5_edc0357_1345510114226_959292_14423Elaborations() {
                Expression<?>[] arguments16 = new Expression<?>[1];
                arguments16[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition16 = new Expression<Boolean>(_17_0_5_edc0357_1345510114226_407269_14426_exists);
                elaborationRule16 = addElaborationRule(condition16, _17_0_5_edc0357_1345510113586_863604_13814.this, Power_System._17_0_5_edc0357_1345510113586_863604_13814._17_0_5_edc0357_1345510114226_407269_14426.class, "sobl_StartObjectBehaviorAction_start_system", arguments16);
                Expression<?>[] arguments17 = new Expression<?>[1];
                arguments17[0] = new Expression<Integer>(startTime);
                Expression<Boolean> condition17 = new Expression<Boolean>(true);
                elaborationRule17 = addElaborationRule(condition17, p, Power._17_0_5_edc0357_1345510155557_623464_18238.class, "PowerCB_Activity_Power", arguments17);
            }

            public _17_0_5_edc0357_1345510114226_959292_14423() {
                super();
                init_17_0_5_edc0357_1345510114226_959292_14423Members();
                init_17_0_5_edc0357_1345510114226_959292_14423Collections();
                init_17_0_5_edc0357_1345510114226_959292_14423Elaborations();
            }

            public _17_0_5_edc0357_1345510114226_959292_14423(Expression<Integer> _17_0_5_edc0357_1345510114225_19712_14422_endTime) {
                super();
                init_17_0_5_edc0357_1345510114226_959292_14423Members();
                init_17_0_5_edc0357_1345510114226_959292_14423Collections();
                addDependency(this._17_0_5_edc0357_1345510114225_19712_14422_endTime, _17_0_5_edc0357_1345510114225_19712_14422_endTime);
                init_17_0_5_edc0357_1345510114226_959292_14423Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114226_580474_14425 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1345510114225_676052_14419_endTime = null;

            public Parameter _17_0_5_edc0357_1345510114838_659499_15218 = null;

            public Parameter _17_0_5_edc0357_1345510114839_149885_15219 = null;

            public ConstraintExpression constraint18 = null;

            public Dependency< LADWP > _17_0_5_edc0357_1345510114838_659499_15218Dependency = null;

            public Effect effect19 = null;

            public Object effect19Var = null;

            public void init_17_0_5_edc0357_1345510114226_580474_14425Members() {
                try {
                    _17_0_5_edc0357_1345510114225_676052_14419_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114225_676052_14419_endTime", this);
                    _17_0_5_edc0357_1345510114838_659499_15218 = new Parameter("_17_0_5_edc0357_1345510114838_659499_15218", null, null, this);
                    _17_0_5_edc0357_1345510114839_149885_15219 = new Parameter("_17_0_5_edc0357_1345510114839_149885_15219", null, null, this);
                    constraint18 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114225_676052_14419_endTime)));
                    _17_0_5_edc0357_1345510114838_659499_15218Dependency = new Dependency<LADWP>(_17_0_5_edc0357_1345510114838_659499_15218, new Expression<LADWP>(((LADWP) _17_0_5_edc0357_1345510114839_149885_15219.getMember("l"))));
                    effect19Var = sig_17_0_5_edc0357_1345510114227_570675_14436;
                    effect19 = new EffectFunction(new FunctionCall((Object) effect19Var, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "send", java.lang.Object.class, java.lang.Integer.class), new Object[] { _17_0_5_edc0357_1345510114838_659499_15218, startTime }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114226_580474_14425Collections() {
                parameters.add(_17_0_5_edc0357_1345510114225_676052_14419_endTime);
                parameters.add(_17_0_5_edc0357_1345510114838_659499_15218);
                parameters.add(_17_0_5_edc0357_1345510114839_149885_15219);
                constraintExpressions.add(constraint18);
                dependencies.add(_17_0_5_edc0357_1345510114838_659499_15218Dependency);
                Set<Effect> effectsForeffect19Var = new HashSet<Effect>();
                effectsForeffect19Var.add(effect19);
                effects.put((Parameter<?>) effect19Var, effectsForeffect19Var);
            }

            public void init_17_0_5_edc0357_1345510114226_580474_14425Elaborations() {
            }

            public _17_0_5_edc0357_1345510114226_580474_14425() {
                super();
                init_17_0_5_edc0357_1345510114226_580474_14425Members();
                init_17_0_5_edc0357_1345510114226_580474_14425Collections();
                init_17_0_5_edc0357_1345510114226_580474_14425Elaborations();
            }

            public _17_0_5_edc0357_1345510114226_580474_14425(Expression<Integer> _17_0_5_edc0357_1345510114225_676052_14419_endTime, Expression<Power_System> _17_0_5_edc0357_1345510114839_149885_15219) {
                super();
                init_17_0_5_edc0357_1345510114226_580474_14425Members();
                init_17_0_5_edc0357_1345510114226_580474_14425Collections();
                addDependency(this._17_0_5_edc0357_1345510114225_676052_14419_endTime, _17_0_5_edc0357_1345510114225_676052_14419_endTime);
                addDependency(this._17_0_5_edc0357_1345510114839_149885_15219, _17_0_5_edc0357_1345510114839_149885_15219);
                init_17_0_5_edc0357_1345510114226_580474_14425Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1345510114226_407269_14426 extends DurativeEvent {

            public BooleanParameter _17_0_5_edc0357_1348077539710_788857_14450_exists = null;

            public Parameter _17_0_5_edc0357_1345510114839_131710_15220 = null;

            public IntegerParameter _17_0_5_edc0357_1345510114226_959292_14423_endTime = null;

            public ConstraintExpression constraint20 = null;

            public Dependency< Boolean > _17_0_5_edc0357_1348077539710_788857_14450_existsDependency = null;

            public Dependency< LADWP > _17_0_5_edc0357_1345510114839_131710_15220Dependency = null;

            public ElaborationRule elaborationRule21 = null;

            public ElaborationRule elaborationRule22 = null;

            public void init_17_0_5_edc0357_1345510114226_407269_14426Members() {
                try {
                    _17_0_5_edc0357_1348077539710_788857_14450_exists = new BooleanParameter("_17_0_5_edc0357_1348077539710_788857_14450_exists", this);
                    _17_0_5_edc0357_1345510114839_131710_15220 = new Parameter("_17_0_5_edc0357_1345510114839_131710_15220", null, null, this);
                    _17_0_5_edc0357_1345510114226_959292_14423_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114226_959292_14423_endTime", this);
                    constraint20 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114226_959292_14423_endTime)));
                    _17_0_5_edc0357_1348077539710_788857_14450_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1348077539710_788857_14450_exists, new Expression<Boolean>(true));
                    _17_0_5_edc0357_1345510114839_131710_15220Dependency = new Dependency<LADWP>(_17_0_5_edc0357_1345510114839_131710_15220, new Expression(new FunctionCall((Object) sig_17_0_5_edc0357_1345510114227_570675_14436, Utils.getMethodForArgTypes("ObjectFlow<LADWP>", "generated", "receive", java.lang.Integer.class), new Object[] { new Functions.Minus(new Expression<Integer>(startTime), new Expression<Integer>(1)) })));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1345510114226_407269_14426Collections() {
                parameters.add(_17_0_5_edc0357_1348077539710_788857_14450_exists);
                parameters.add(_17_0_5_edc0357_1345510114839_131710_15220);
                parameters.add(_17_0_5_edc0357_1345510114226_959292_14423_endTime);
                constraintExpressions.add(constraint20);
                dependencies.add(_17_0_5_edc0357_1348077539710_788857_14450_existsDependency);
                dependencies.add(_17_0_5_edc0357_1345510114839_131710_15220Dependency);
            }

            public void init_17_0_5_edc0357_1345510114226_407269_14426Elaborations() {
                Expression<?>[] arguments21 = new Expression<?>[1];
                arguments21[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition21 = new Expression<Boolean>(_17_0_5_edc0357_1348077539710_788857_14450_exists);
                elaborationRule21 = addElaborationRule(condition21, _17_0_5_edc0357_1345510113586_863604_13814.this, Power_System._17_0_5_edc0357_1345510113586_863604_13814._17_0_5_edc0357_1348077539710_788857_14450.class, "_AcceptEventAction_start_system", arguments21);
                Expression<?>[] arguments22 = new Expression<?>[1];
                arguments22[0] = new Expression<Integer>(startTime);
                Expression<Boolean> condition22 = new Expression<Boolean>(true);
                elaborationRule22 = addElaborationRule(condition22, l, LADWP._17_0_5_edc0357_1346100467245_506694_13577.class, "LADWP_CB_Activity_LADWP", arguments22);
            }

            public _17_0_5_edc0357_1345510114226_407269_14426() {
                super();
                init_17_0_5_edc0357_1345510114226_407269_14426Members();
                init_17_0_5_edc0357_1345510114226_407269_14426Collections();
                init_17_0_5_edc0357_1345510114226_407269_14426Elaborations();
            }

            public _17_0_5_edc0357_1345510114226_407269_14426(Expression<Integer> _17_0_5_edc0357_1345510114226_959292_14423_endTime) {
                super();
                init_17_0_5_edc0357_1345510114226_407269_14426Members();
                init_17_0_5_edc0357_1345510114226_407269_14426Collections();
                addDependency(this._17_0_5_edc0357_1345510114226_959292_14423_endTime, _17_0_5_edc0357_1345510114226_959292_14423_endTime);
                init_17_0_5_edc0357_1345510114226_407269_14426Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1348077539710_788857_14450 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1345510114226_407269_14426_endTime = null;

            public BooleanParameter _17_0_5_edc0357_1348077577791_22941_14468_exists = null;

            public ConstraintExpression constraint23 = null;

            public Dependency< Integer > durationDependency = null;

            public Dependency< Boolean > _17_0_5_edc0357_1348077577791_22941_14468_existsDependency = null;

            public ElaborationRule elaborationRule24 = null;

            public void init_17_0_5_edc0357_1348077539710_788857_14450Members() {
                try {
                    _17_0_5_edc0357_1345510114226_407269_14426_endTime = new IntegerParameter("_17_0_5_edc0357_1345510114226_407269_14426_endTime", this);
                    _17_0_5_edc0357_1348077577791_22941_14468_exists = new BooleanParameter("_17_0_5_edc0357_1348077577791_22941_14468_exists", this);
                    constraint23 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1345510114226_407269_14426_endTime)));
                    durationDependency = new Dependency<Integer>(duration, new Expression<Integer>(10000));
                    _17_0_5_edc0357_1348077577791_22941_14468_existsDependency = new Dependency<Boolean>(_17_0_5_edc0357_1348077577791_22941_14468_exists, new Expression<Boolean>(true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1348077539710_788857_14450Collections() {
                parameters.add(_17_0_5_edc0357_1345510114226_407269_14426_endTime);
                parameters.add(_17_0_5_edc0357_1348077577791_22941_14468_exists);
                constraintExpressions.add(constraint23);
                removeDependency(duration);
                dependencies.add(durationDependency);
                dependencies.add(_17_0_5_edc0357_1348077577791_22941_14468_existsDependency);
            }

            public void init_17_0_5_edc0357_1348077539710_788857_14450Elaborations() {
                Expression<?>[] arguments24 = new Expression<?>[1];
                arguments24[0] = new Expression<Integer>(endTime);
                Expression<Boolean> condition24 = new Expression<Boolean>(_17_0_5_edc0357_1348077577791_22941_14468_exists);
                elaborationRule24 = addElaborationRule(condition24, _17_0_5_edc0357_1345510113586_863604_13814.this, Power_System._17_0_5_edc0357_1345510113586_863604_13814._17_0_5_edc0357_1348077577791_22941_14468.class, "_ActivityFinalNode_start_system", arguments24);
            }

            public _17_0_5_edc0357_1348077539710_788857_14450() {
                super();
                init_17_0_5_edc0357_1348077539710_788857_14450Members();
                init_17_0_5_edc0357_1348077539710_788857_14450Collections();
                init_17_0_5_edc0357_1348077539710_788857_14450Elaborations();
            }

            public _17_0_5_edc0357_1348077539710_788857_14450(Expression<Integer> _17_0_5_edc0357_1345510114226_407269_14426_endTime) {
                super();
                init_17_0_5_edc0357_1348077539710_788857_14450Members();
                init_17_0_5_edc0357_1348077539710_788857_14450Collections();
                addDependency(this._17_0_5_edc0357_1345510114226_407269_14426_endTime, _17_0_5_edc0357_1345510114226_407269_14426_endTime);
                init_17_0_5_edc0357_1348077539710_788857_14450Elaborations();
                fixTimeDependencies();
            }
        }

        public class _17_0_5_edc0357_1348077577791_22941_14468 extends DurativeEvent {

            public IntegerParameter _17_0_5_edc0357_1348077539710_788857_14450_endTime = null;

            public ConstraintExpression constraint25 = null;

            public void init_17_0_5_edc0357_1348077577791_22941_14468Members() {
                try {
                    _17_0_5_edc0357_1348077539710_788857_14450_endTime = new IntegerParameter("_17_0_5_edc0357_1348077539710_788857_14450_endTime", this);
                    constraint25 = new ConstraintExpression(new Functions.Greater(new Expression<Integer>(startTime), new Expression<Integer>(_17_0_5_edc0357_1348077539710_788857_14450_endTime)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            protected void init_17_0_5_edc0357_1348077577791_22941_14468Collections() {
                parameters.add(_17_0_5_edc0357_1348077539710_788857_14450_endTime);
                constraintExpressions.add(constraint25);
            }

            public void init_17_0_5_edc0357_1348077577791_22941_14468Elaborations() {
            }

            public _17_0_5_edc0357_1348077577791_22941_14468() {
                super();
                init_17_0_5_edc0357_1348077577791_22941_14468Members();
                init_17_0_5_edc0357_1348077577791_22941_14468Collections();
                init_17_0_5_edc0357_1348077577791_22941_14468Elaborations();
            }

            public _17_0_5_edc0357_1348077577791_22941_14468(Expression<Integer> _17_0_5_edc0357_1348077539710_788857_14450_endTime) {
                super();
                init_17_0_5_edc0357_1348077577791_22941_14468Members();
                init_17_0_5_edc0357_1348077577791_22941_14468Collections();
                addDependency(this._17_0_5_edc0357_1348077539710_788857_14450_endTime, _17_0_5_edc0357_1348077539710_788857_14450_endTime);
                init_17_0_5_edc0357_1348077577791_22941_14468Elaborations();
                fixTimeDependencies();
            }
        }
    }

    public class SignalchangeLoadValue extends ParameterListenerImpl {

        public Parameter load__17_0_5_edc0357_1345510113551_910480_13770 = null;

        public void initSignalchangeLoadValueMembers() {
            try {
                load__17_0_5_edc0357_1345510113551_910480_13770 = new Parameter("load__17_0_5_edc0357_1345510113551_910480_13770", null, new TimeVaryingMap("load"), this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void initSignalchangeLoadValueCollections() {
            parameters.add(load__17_0_5_edc0357_1345510113551_910480_13770);
        }

        public void initSignalchangeLoadValueElaborations() {
        }

        public SignalchangeLoadValue() {
            super();
            initSignalchangeLoadValueMembers();
            initSignalchangeLoadValueCollections();
            initSignalchangeLoadValueElaborations();
        }

        public SignalchangeLoadValue(Integer x) {
            super();
            initSignalchangeLoadValueMembers();
            initSignalchangeLoadValueCollections();
            initSignalchangeLoadValueElaborations();
            load__17_0_5_edc0357_1345510113551_910480_13770.setValue(x);
        }
    }

    public class SignalchangeGenerationValue extends ParameterListenerImpl {

        public Parameter newGenValue__17_0_5_edc0357_1345510113551_931345_13769 = null;

        public void initSignalchangeGenerationValueMembers() {
            try {
                newGenValue__17_0_5_edc0357_1345510113551_931345_13769 = new Parameter("newGenValue__17_0_5_edc0357_1345510113551_931345_13769", null, new TimeVaryingMap("newGenValue"), this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void initSignalchangeGenerationValueCollections() {
            parameters.add(newGenValue__17_0_5_edc0357_1345510113551_931345_13769);
        }

        public void initSignalchangeGenerationValueElaborations() {
        }

        public SignalchangeGenerationValue() {
            super();
            initSignalchangeGenerationValueMembers();
            initSignalchangeGenerationValueCollections();
            initSignalchangeGenerationValueElaborations();
        }

        public SignalchangeGenerationValue(Integer x) {
            super();
            initSignalchangeGenerationValueMembers();
            initSignalchangeGenerationValueCollections();
            initSignalchangeGenerationValueElaborations();
            newGenValue__17_0_5_edc0357_1345510113551_931345_13769.setValue(x);
        }
    }

    public class Signaldr_request extends ParameterListenerImpl {

        public Parameter cap__17_0_5_edc0357_1345510113618_430459_13856 = null;

        public void initSignaldr_requestMembers() {
            try {
                cap__17_0_5_edc0357_1345510113618_430459_13856 = new Parameter("cap__17_0_5_edc0357_1345510113618_430459_13856", null, new TimeVaryingMap("cap"), this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void initSignaldr_requestCollections() {
            parameters.add(cap__17_0_5_edc0357_1345510113618_430459_13856);
        }

        public void initSignaldr_requestElaborations() {
        }

        public Signaldr_request() {
            super();
            initSignaldr_requestMembers();
            initSignaldr_requestCollections();
            initSignaldr_requestElaborations();
        }

        public Signaldr_request(Integer x) {
            super();
            initSignaldr_requestMembers();
            initSignaldr_requestCollections();
            initSignaldr_requestElaborations();
            cap__17_0_5_edc0357_1345510113618_430459_13856.setValue(x);
        }
    }

    public class Signalyes extends ParameterListenerImpl {

        public Parameter newLoad__17_0_5_edc0357_1345510113619_149263_13857 = null;

        public void initSignalyesMembers() {
            try {
                newLoad__17_0_5_edc0357_1345510113619_149263_13857 = new Parameter("newLoad__17_0_5_edc0357_1345510113619_149263_13857", null, new TimeVaryingMap("newLoad"), this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void initSignalyesCollections() {
            parameters.add(newLoad__17_0_5_edc0357_1345510113619_149263_13857);
        }

        public void initSignalyesElaborations() {
        }

        public Signalyes() {
            super();
            initSignalyesMembers();
            initSignalyesCollections();
            initSignalyesElaborations();
        }

        public Signalyes(Integer x) {
            super();
            initSignalyesMembers();
            initSignalyesCollections();
            initSignalyesElaborations();
            newLoad__17_0_5_edc0357_1345510113619_149263_13857.setValue(x);
        }
    }

    public class Signalno extends ParameterListenerImpl {

        public void initSignalnoMembers() {
            try {
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void initSignalnoCollections() {
        }

        public void initSignalnoElaborations() {
        }

        public Signalno() {
            super();
            initSignalnoMembers();
            initSignalnoCollections();
            initSignalnoElaborations();
        }
    }

    public class SignalreceiveMeterReading extends ParameterListenerImpl {

        public Parameter meter_value__17_0_5_edc0357_1345510113616_372105_13853 = null;

        public void initSignalreceiveMeterReadingMembers() {
            try {
                meter_value__17_0_5_edc0357_1345510113616_372105_13853 = new Parameter("meter_value__17_0_5_edc0357_1345510113616_372105_13853", null, new TimeVaryingMap("meter_value"), this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void initSignalreceiveMeterReadingCollections() {
            parameters.add(meter_value__17_0_5_edc0357_1345510113616_372105_13853);
        }

        public void initSignalreceiveMeterReadingElaborations() {
        }

        public SignalreceiveMeterReading() {
            super();
            initSignalreceiveMeterReadingMembers();
            initSignalreceiveMeterReadingCollections();
            initSignalreceiveMeterReadingElaborations();
        }

        public SignalreceiveMeterReading(Integer x) {
            super();
            initSignalreceiveMeterReadingMembers();
            initSignalreceiveMeterReadingCollections();
            initSignalreceiveMeterReadingElaborations();
            meter_value__17_0_5_edc0357_1345510113616_372105_13853.setValue(x);
        }
    }

    public class SignalreceiveLoadReading extends ParameterListenerImpl {

        public Parameter actual_load__17_0_5_edc0357_1345510113617_570582_13854 = null;

        public void initSignalreceiveLoadReadingMembers() {
            try {
                actual_load__17_0_5_edc0357_1345510113617_570582_13854 = new Parameter("actual_load__17_0_5_edc0357_1345510113617_570582_13854", null, new TimeVaryingMap("actual_load"), this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void initSignalreceiveLoadReadingCollections() {
            parameters.add(actual_load__17_0_5_edc0357_1345510113617_570582_13854);
        }

        public void initSignalreceiveLoadReadingElaborations() {
        }

        public SignalreceiveLoadReading() {
            super();
            initSignalreceiveLoadReadingMembers();
            initSignalreceiveLoadReadingCollections();
            initSignalreceiveLoadReadingElaborations();
        }

        public SignalreceiveLoadReading(Integer x) {
            super();
            initSignalreceiveLoadReadingMembers();
            initSignalreceiveLoadReadingCollections();
            initSignalreceiveLoadReadingElaborations();
            actual_load__17_0_5_edc0357_1345510113617_570582_13854.setValue(x);
        }
    }

    public class SignalreceiveGenReading extends ParameterListenerImpl {

        public Parameter actual_power__17_0_5_edc0357_1345510113617_469482_13855 = null;

        public void initSignalreceiveGenReadingMembers() {
            try {
                actual_power__17_0_5_edc0357_1345510113617_469482_13855 = new Parameter("actual_power__17_0_5_edc0357_1345510113617_469482_13855", null, new TimeVaryingMap("actual_power"), this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void initSignalreceiveGenReadingCollections() {
            parameters.add(actual_power__17_0_5_edc0357_1345510113617_469482_13855);
        }

        public void initSignalreceiveGenReadingElaborations() {
        }

        public SignalreceiveGenReading() {
            super();
            initSignalreceiveGenReadingMembers();
            initSignalreceiveGenReadingCollections();
            initSignalreceiveGenReadingElaborations();
        }

        public SignalreceiveGenReading(Integer x) {
            super();
            initSignalreceiveGenReadingMembers();
            initSignalreceiveGenReadingCollections();
            initSignalreceiveGenReadingElaborations();
            actual_power__17_0_5_edc0357_1345510113617_469482_13855.setValue(x);
        }
    }

    public Power_System() {
        super();
        initPower_SystemMembers();
        initPower_SystemCollections();
        initPower_SystemElaborations();
        ((ObjectFlow) ss_17_0_5_edc0357_1345510113562_98635_13780_changeLoadValue.getValue()).addListener(((ObjectFlow) ((Power) p.getValue()).q_Power_changeLoadValue.getValue()));
        ((ObjectFlow) ss_17_0_5_edc0357_1345510113607_182740_13840_changeGenerationValue.getValue()).addListener(((ObjectFlow) ((Power) p.getValue()).q_Power_changeGenerationValue.getValue()));
        ((ObjectFlow) ss_17_0_5_edc0357_1345510113605_306486_13837_dr_request.getValue()).addListener(((ObjectFlow) ((Customer) c.getValue()).q_Customer_dr_request.getValue()));
        ((ObjectFlow) ss_17_0_5_edc0357_1345510113564_305135_13782_yes.getValue()).addListener(((ObjectFlow) ((LADWP) l.getValue()).q_LADWP_yes.getValue()));
        ((ObjectFlow) ss_17_0_5_edc0357_1345510113564_305135_13782_no.getValue()).addListener(((ObjectFlow) ((LADWP) l.getValue()).q_LADWP_no.getValue()));
        ((ObjectFlow) ss_17_0_5_edc0357_1345510113565_310611_13783_receiveMeterReading.getValue()).addListener(((ObjectFlow) ((LADWP) l.getValue()).q_LADWP_receiveMeterReading.getValue()));
        ((ObjectFlow) ss_17_0_5_edc0357_1345510113549_985081_13767_receiveLoadReading.getValue()).addListener(((ObjectFlow) ((LADWP) l.getValue()).q_LADWP_receiveLoadReading.getValue()));
        ((ObjectFlow) ss_17_0_5_edc0357_1345510113550_816176_13768_receiveGenReading.getValue()).addListener(((ObjectFlow) ((LADWP) l.getValue()).q_LADWP_receiveGenReading.getValue()));
    }
}
