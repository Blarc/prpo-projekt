package interceptors;

import anotations.CallCounter;
import beans.CallCounterBean;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@CallCounter
public class CallCounterInterceptor {

    @Inject
    CallCounterBean callCounterBean;

    @AroundInvoke
    public Object increaseCounter(InvocationContext context) throws Exception {
        callCounterBean.increaseCounter();
        return context.proceed();
    }
}
