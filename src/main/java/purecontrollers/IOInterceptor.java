package purecontrollers;

import java.util.List;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * {@link IOInterceptor} checks the return value of methods annotated with
 * {@link IOProvider} and processes them as IO.
 */
class IOInterceptor implements MethodInterceptor {

    private final CommandHQ hq;

    public IOInterceptor(CommandHQ anHQ) {
        hq = anHQ;
    }

    public Object invoke(MethodInvocation invocation) throws Throwable {
        Object io = invocation.proceed();

        if (io != null) {
            processIO(io);
        }

        return io;
    }

    private void processIO(Object io) {
        if (io instanceof List) {
            for (Object ioItem : ((List<?>) io)) {
                hq.processIO(ioItem);
            }
        } else {
            hq.processIO(io);
        }
    }
}
