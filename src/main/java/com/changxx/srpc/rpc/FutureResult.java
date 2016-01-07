/**
 *
 */
package com.changxx.srpc.rpc;

/**
 * 响应Result
 */
public interface FutureResult {

    /**
     * 获取服务端响应结果
     *
     * @return
     */
    Object get();
}
