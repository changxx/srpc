package com.changxx.srpc.rpc;

import com.changxx.srpc.conf.ProviderConfig;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 服务提供方Netty Server Socket实现
 */
public class NettyProviderServer extends AbstractProviderServer {

    private static final Logger LOG = LoggerFactory.getLogger(NettyProviderServer.class);

    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;

    public void start() {
        bossGroup = new NioEventLoopGroup();
        workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childHandler(new ChildChannelHandler());
            serverBootstrap.bind(8050).syncUninterruptibly();
            LOG.info("Dora netty server [127.0.0.1] start on 8050");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }

    /**
     * 网络事件处理器
     */
    private class ChildChannelHandler extends ChannelInitializer<SocketChannel> {

        @Override
        protected void initChannel(SocketChannel ch) throws Exception {
            ch.pipeline().addLast(new ObjectEncoder());
            ch.pipeline().addLast(new ObjectDecoder(1024 * 1024 * 5, ClassResolvers.cacheDisabled(null)));
            ch.pipeline().addLast(new ProviderHandler(ProviderConfig.serviceMap));
        }
    }

}


