/**
 *
 */
package com.changxx.srpc.rpc;


/**
 * 服务调用方客户端接口
 *
 * @author zhaohongbo
 */
public interface ConsumerClient {

    /**
     * 请求Provider Server
     *
     * @return
     */
    FutureResult request(Request request);

    /**
     * 连接Provider Server
     *
     * @throws Exception
     */
    void connect() throws Exception;

    /**
     * 关闭Provider Server连接
     */
    void close();

    /**
     * 获取Provider Server主机IP
     *
     * @return
     */
    String getHost();

    /**
     * 获取Provider Server主机端口
     *
     * @return
     */
    int getPort();

    /**
     * 获取Provider Server主机+端口
     *
     * @return
     */
    String getAddress();

    /**
     * 请求状态
     *
     * @return
     */
    boolean isSuccess();

    /**
     * 设置请求状态
     *
     * @param success
     */
    void setSuccess(boolean success);
}
