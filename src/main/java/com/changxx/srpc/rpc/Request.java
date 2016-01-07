package com.changxx.srpc.rpc;

import java.io.Serializable;
import java.util.Map;

/**
 * @author changxx on 2016/1/6.
 */
public class Request implements Serializable{

    /**
     * 服务唯一标识
     */
    private String serviceKey;

    /**
     * 服务方法名称
     */
    private String methodName;

    /**
     * 方法参数类型列表
     */
    private Class<?>[] parameterTypes;

    /**
     * 方法参数列表
     */
    private Object[] parameters;

    /**
     * 附加参数
     */
    private Map<String, String> attachments;

    public String getServiceKey() {
        return serviceKey;
    }

    public void setServiceKey(String serviceKey) {
        this.serviceKey = serviceKey;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(Class<?>[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }

    public Map<String, String> getAttachments() {
        return attachments;
    }

    public void setAttachments(Map<String, String> attachments) {
        this.attachments = attachments;
    }
}
