/**
 *
 */
package com.changxx.srpc.rpc;

import com.changxx.srpc.util.ReflectionUtils;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * 服务提供方请求处理器
 */
public class ProviderHandler extends ChannelHandlerAdapter {

    private static final Logger log = LoggerFactory.getLogger(ProviderHandler.class);

    protected Map<String, Object> serviceMap;

    public ProviderHandler(Map<String, Object> serviceMap) {
        this.serviceMap = serviceMap;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        ctx.close();
        super.exceptionCaught(ctx, cause);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        if (msg == null || !msg.getClass().equals(RpcRequest.class)) {
            return;
        }
        RpcRequest rpcRequest = (RpcRequest) msg;
        log.info("ProviderHandler channelRead id: {}", rpcRequest.getId());

        Request request = (Request) rpcRequest.getData();
        Object target = serviceMap.get(request.getServiceKey());
        Object resultObject = ReflectionUtils.invokeMethod(target, request.getMethodName(), request.getParameterTypes(), request.getParameters());
        RpcResponse response = new RpcResponse();
        response.setId(rpcRequest.getId());
        response.setData(resultObject);

        log.info("provider write: {}", resultObject);
        ctx.write(response);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
        super.channelReadComplete(ctx);
    }

}
