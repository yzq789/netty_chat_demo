package chat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Scanner;

/**
 * Created by Zhiqi Yang on 2017/10/15.
 */
public class Client {

    String host = "127.0.0.1";
    int port = 5050;
    ChannelInitializer<SocketChannel> channelInitializer = new ClientInitializer();

    public void run() throws InterruptedException {
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.handler(channelInitializer);

            // Start the client.
            ChannelFuture f = b.connect(host, port).sync();
            Channel channel = f.channel();
            Scanner scanner = new Scanner(System.in);
            while(true){
                String msg = scanner.nextLine();
                channel.writeAndFlush(msg + "\n");  //此处的\n必须有，否则接收端的Delimiter不工作,只缓存不处理
            }
        } finally {
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        Client client = new Client();
        client.run();
    }
}
