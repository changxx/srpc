/**
 *
 */
package com.changxx.srpc.rpc;


import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Consumer Netty 客户端实现
 */
public class NettyConsumerClient implements ConsumerClient {

    private static final Logger log = LoggerFactory.getLogger(NettyConsumerClient.class);

    /**
     * Netty channel future
     */
    private ChannelFuture channelFuture;

    /**
     * 主机
     */
    private String host;

    /**
     * 端口
     */
    private int port;

    private boolean success = true;

    /**
     * 事件循环组
     */
    private EventLoopGroup group;

    public NettyConsumerClient(String host, int port) {
        try {
            this.host = host;
            this.port = port;
            this.connect();
        } catch (Exception e) {
            log.error("consumer client initlizating fail", e);
            setSuccess(false);
        }
    }

    public void connect() throws Exception {
        System.out.println("consumer client start connect");
        group = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(group).channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch)
                            throws Exception {
                        ch.pipeline().addLast(new ObjectEncoder());
                        ch.pipeline().addLast(new ObjectDecoder(1024 * 1024, ClassResolvers.cacheDisabled(null)));
                        ch.pipeline().addLast(new ConsumerHandler());
                    }
                });

        channelFuture = b.connect(host, port);
        channelFuture.awaitUninterruptibly();
        assert channelFuture.isDone();

        if (channelFuture.isCancelled()) {

        } else if (!channelFuture.isSuccess()) {
            throw new RuntimeException(channelFuture.cause().getCause());
        }
    }

    public Channel getChannel() {
        return channelFuture.channel();
    }

    public FutureResult request(Request request) {
        RpcRequest rpcRequest = new RpcRequest();
        FutureResult futureResult = new RpcFutureResult(rpcRequest.getId());
        rpcRequest.setData(request);
        ChannelFuture f = channelFuture;

        if (!f.isSuccess()) {
            log.info("client channel connect failure!!!");
            throw new RuntimeException(channelFuture.cause().getCause());
        } else {
            log.info("client channel write, id: {}", rpcRequest.getId());
            f.channel().writeAndFlush(rpcRequest);
        }

        return futureResult;
    }

    public void close() {
        group.shutdownGracefully();
    }

    public String getHost() {
        return this.host;
    }

    public int getPort() {
        return this.port;
    }

    public String getAddress() {
        return this.host + ":" + port;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
