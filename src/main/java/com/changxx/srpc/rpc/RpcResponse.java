/**
 *
 */
package com.changxx.srpc.rpc;

import java.io.Serializable;

/**
 * RPC 响应结果默认实现
 */
public class RpcResponse implements Serializable {

    /**
     * 响应ID与请求ID相同
     */
    private long id;

    /**
     * 响应数据
     */
    private Object data;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
