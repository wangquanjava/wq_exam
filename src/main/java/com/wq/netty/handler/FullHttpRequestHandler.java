package com.wq.netty.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;

public class FullHttpRequestHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

	String chatUrl;
	public FullHttpRequestHandler(String chatUrl) {
		this.chatUrl = chatUrl;
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest msg) throws Exception {
		if(chatUrl.equalsIgnoreCase(msg.uri())){
			ctx.fireChannelRead(msg.retain());	
			return;
		}
        
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		ctx.close();
		cause.printStackTrace(System.err);
	}
	
}
