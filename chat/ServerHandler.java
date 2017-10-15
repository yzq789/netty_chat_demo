package chat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by Zhiqi Yang on 2017/10/15.
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {

    public void handlerAdded(ChannelHandlerContext ctx){
        System.out.println("ServerHandler#handlerAdded");
        ServerChannelPool.channels.add(ctx.channel());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg){
        String message = (String)msg;
        System.out.println(message);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx){
        System.out.println("ServerHandler#channelActive");
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx){
        System.out.println("ServerHandler#handerRemoved");
        System.out.println(ctx.channel());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (7)
        cause.printStackTrace();
        ctx.close();
    }

}
