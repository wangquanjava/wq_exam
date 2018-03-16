package com.wq.netty;

import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * Created by wqquan.wang on 2018/3/14.
 */
public class NettyServerManager {
    // client channel CLIENT_GROUP
    public static ChannelGroup CLIENT_GROUP = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);


}
