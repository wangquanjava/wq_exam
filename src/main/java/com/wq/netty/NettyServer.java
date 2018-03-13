package com.wq.netty;

import com.wq.netty.handler.FullHttpRequestHandler;
import com.wq.netty.handler.TextWebSocketFrameHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * Created by wqquan.wang on 2018/3/14.
 */
public class NettyServer {
    private Integer port;
    private String serverPath;

    public void init (){
        Thread thread = new Thread(new Runnable() {

            EventLoopGroup bossgroup = new NioEventLoopGroup();
            EventLoopGroup workergroup = new NioEventLoopGroup();
            public void run() {
                ServerBootstrap serverBootstrap = new ServerBootstrap();
                serverBootstrap.group(bossgroup, workergroup);
                serverBootstrap.channel(NioServerSocketChannel.class);
                serverBootstrap.childHandler(new ChannelInitializer() {
                    protected void initChannel(Channel channel) throws Exception {
                        ChannelPipeline pipeline = channel.pipeline();
                        pipeline.addLast(new HttpServerCodec());pipeline.addLast(new HttpServerCodec());
                        pipeline.addLast(new HttpObjectAggregator(65536));
                        pipeline.addLast(new ChunkedWriteHandler());
                        pipeline.addLast(new WebSocketServerProtocolHandler(serverPath));
                        pipeline.addLast(new FullHttpRequestHandler(serverPath));
                        pipeline.addLast(new TextWebSocketFrameHandler());



                    }
                });


            }
        });
    }
    public void destroy () {

    }
}
