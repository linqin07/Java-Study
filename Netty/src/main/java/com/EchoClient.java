package com;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * Description:
 * author: LinQin
 * date: 2018/08/06
 */
public class EchoClient {
    private final String host;
    private final int port;

    public EchoClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.err.println(
                    "Usage: " + EchoClient.class.getSimpleName() +
                            " <host> <port>");
            // return;
        }

        String host = "127.0.0.1";
        int port = Integer.parseInt("8080");
        new EchoClient(host, port).start();
    }

    public void start() throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {    /*⇽---  创建Bootstrap*/
            Bootstrap b = new Bootstrap();     /*⇽---  指定EventLoopGroup以处理客户端事件；需要适用于NIO的实现*/
            b.group(group)
                    .channel(NioSocketChannel.class)     /*⇽---  适用于NIO传输的Channel类型*/
                    .remoteAddress(new InetSocketAddress(host, port))     /*⇽---  设置服务器的InetSocketAddr-ess![](/api/storage/getbykey/screenshow?key=17043add7e9c14a5d3f7)     */
                    .handler(new ChannelInitializer<SocketChannel>() {   /* ⇽---  在创建Channel时，向ChannelPipeline中添加一个Echo-ClientHandler实例*/
                        @Override
                        public void initChannel(SocketChannel ch)
                                throws Exception {
                            ch.pipeline().addLast(
                                    new EchoClientHandler());
                        }
                    });
            ChannelFuture f = b.connect().sync();    /* ⇽---  连接到远程节点，阻塞等待直到连接完成*/
            f.channel().closeFuture().sync();      /*⇽---  阻塞，直到Channel关闭*/
        } finally {
            group.shutdownGracefully().sync();      /* ⇽---  关闭线程池并且释放所有的资源*/
        }
    }
}