package gov.nasa.jpl.ae.event;

import gov.nasa.jpl.ae.solver.Domain;
import gov.nasa.jpl.ae.solver.HasDomain;
import gov.nasa.jpl.ae.solver.Variable;
import gov.nasa.jpl.ae.util.DomainHelper;
import gov.nasa.jpl.mbee.util.ClassUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

public class JavaSuggestiveFunctionCall extends Functions.SuggestiveFunctionCall {

    public static class GetMember extends JavaSuggestiveFunctionCall {
        public static Method getMemberMethod = null;
        public static Method getMemberMethod() {
            if ( getMemberMethod != null ) return getMemberMethod;
            getMemberMethod = ClassUtils.getMethodForArgTypes(Parameter.class, "getMember", String.class, boolean.class);
            return getMemberMethod;
        }
        public GetMember(Object object, Object[] arguments) throws NoSuchMethodException {
            super(object, getMemberMethod(), arguments);
        }

        public GetMember(Functions.SuggestiveFunctionCall suggestiveFunctionCall) {
            super(suggestiveFunctionCall);
        }

        public GetMember(FunctionCall functionCall) {
            super(functionCall);
        }

        public GetMember(FunctionCall functionCall, FunctionCall pickFunctionCall, FunctionCall reversePickFunctionCall) {
            super(functionCall, pickFunctionCall, reversePickFunctionCall);
        }

        @Override
        public <T> T pickValue(Variable<T> variable) {
            // TODO -- This should return a string value that is the name of a member of the object's class.
            return super.pickValue(variable);
        }

        @Override
        public Domain<?> calculateDomain(boolean propagate, Set<HasDomain> seen) {
            Object result = null;
            try {
                result = evaluate(false);
            } catch (IllegalAccessException e) {
            } catch (InvocationTargetException e) {
            } catch (InstantiationException e) {
            }
            return DomainHelper.getDomain(result);
        }

        @Override
        public FunctionCall inverse(Object returnValue, Object arg) {
            // TODO
            return super.inverse(returnValue, arg);
        }

        @Override
        public FunctionCall inverseSingleValue(Object returnValue, Object arg) {
            // TODO
            return super.inverseSingleValue(returnValue, arg);
        }

        @Override
        public boolean isMonotonic() {
            return false;
        }

        @Override
        public boolean isContinuous() {
            return false;
        }
    }


    public JavaSuggestiveFunctionCall(Object object, Method method, Object[] arguments) {
        super(object, method, arguments);
    }

    public JavaSuggestiveFunctionCall(Functions.SuggestiveFunctionCall suggestiveFunctionCall) {
        super(suggestiveFunctionCall);
    }

    public JavaSuggestiveFunctionCall(FunctionCall functionCall) {
        super(functionCall);
    }

    public JavaSuggestiveFunctionCall(FunctionCall functionCall, FunctionCall pickFunctionCall, FunctionCall reversePickFunctionCall) {
        super(functionCall, pickFunctionCall, reversePickFunctionCall);
    }


    @Override
    public <T> T pickValue(Variable<T> variable) {
        return super.pickValue(variable);
    }

    @Override
    public Domain<?> calculateDomain(boolean propagate, Set<HasDomain> seen) {
        return super.calculateDomain(propagate, seen);
    }

    @Override
    public FunctionCall inverse(Object returnValue, Object arg) {
        return super.inverse(returnValue, arg);
    }

    @Override
    public FunctionCall inverseSingleValue(Object returnValue, Object arg) {
        return super.inverseSingleValue(returnValue, arg);
    }

    @Override
    public boolean isMonotonic() {
        return super.isMonotonic();
    }

    @Override
    public boolean isContinuous() {
        return super.isContinuous();
    }
}
