package com.changxx.srpc.invocation;

import com.changxx.srpc.rpc.ConsumerClient;
import com.changxx.srpc.rpc.FutureResult;
import com.changxx.srpc.rpc.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author changxx on 2016/1/6.
 */
public class RpcInvocation implements InvocationHandler {

    private static final Logger log = LoggerFactory.getLogger(RpcInvocation.class);

    private ConsumerClient client;

    private String serviceKey;

    public RpcInvocation(ConsumerClient client, String serviceKey) {
        this.client = client;
        this.serviceKey = serviceKey;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (client == null) {
            throw new RuntimeException("No provider host found for service:" + serviceKey);
        }

        log.info("proxy invoke methor: {}, args: {}", method, args);

        String methodName = method.getName();
        Class<?>[] parameterTypes = method.getParameterTypes();
        log.info("method.getDeclaringClass()=" + method.getDeclaringClass());
        if (method.getDeclaringClass() == Object.class) {
            return method.invoke(this, args);
        }
        if ("toString".equals(methodName) && parameterTypes.length == 0) {
            return this.toString();
        }
        if ("hashCode".equals(methodName) && parameterTypes.length == 0) {
            return this.hashCode();
        }
        if ("equals".equals(methodName) && parameterTypes.length == 1) {
            return this.equals(args[0]);
        }

        Request request = new Request();
        request.setMethodName(method.getName());
        request.setParameters(args);
        request.setParameterTypes(method.getParameterTypes());
        request.setServiceKey(serviceKey);
        FutureResult futureResult = client.request(request);
        return futureResult.get();
    }

    public ConsumerClient getClient() {
        return client;
    }

    public void setClient(ConsumerClient client) {
        this.client = client;
    }

    public String getServiceKey() {
        return serviceKey;
    }

    public void setServiceKey(String serviceKey) {
        this.serviceKey = serviceKey;
    }
}
