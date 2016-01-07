/**
 *
 */
package com.changxx.srpc.rpc;


import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 服务调用方响应处理器
 */
public class ConsumerHandler extends ChannelHandlerAdapter {

    private static final Logger log = LoggerFactory.getLogger(ConsumerHandler.class);

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        log.error("Unexpected exception from downstream:" + cause.getMessage());
        ctx.close();
        log.error("客户端异常退出");
        super.exceptionCaught(ctx, cause);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        RpcResponse response = (RpcResponse) msg;
        log.info("channel read, id: {}, data: {}", response.getId(), response.getData());
        Object responseData = response.getData();
        if (responseData == null) {
            responseData = new Object();
        }
        RpcFutureResult.FUTURE_RESULT.put(response.getId(), responseData);
        log.info("FUTURE_RESULT length: {}", RpcFutureResult.FUTURE_RESULT.size());
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
        super.channelReadComplete(ctx);
    }
}
