/**
 *
 */
package com.changxx.srpc.rpc;

import java.io.Serializable;


/**
 * RPC请求
 */
public class RpcRequest implements Serializable {

    /**
     * 请求ID
     */
    private long id;

    /**
     * 请求数据
     */
    private Object data;

    /**
     * 调用方法请求IP
     */
    private String remoteAddr;

    public RpcRequest() {
        this.id = System.nanoTime();
    }

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

    public String getRemoteAddr() {
        return remoteAddr;
    }

    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr;
    }

}
