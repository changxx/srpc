/**
 *
 */
package com.changxx.srpc.rpc;

import java.util.concurrent.ConcurrentHashMap;

/**
 * RPC 响应结果默认实现
 */
public class RpcFutureResult implements FutureResult {

    public static ConcurrentHashMap<Long, Object> FUTURE_RESULT = new ConcurrentHashMap<Long, Object>();

    private long id;

    public static final int DEFAULT_TIMEOUT = 3000;

    public RpcFutureResult(long id) {
        this.id = id;
    }

    public Object get() {
        Object result = null;
        long start = System.currentTimeMillis();
        while (result == null && !isTimeout(start)) {
            result = FUTURE_RESULT.get(id);
        }
        FUTURE_RESULT.remove(id);
        return result;
    }

    public static boolean isTimeout(long start) {
        return (System.currentTimeMillis() - start) > DEFAULT_TIMEOUT;
    }
}
