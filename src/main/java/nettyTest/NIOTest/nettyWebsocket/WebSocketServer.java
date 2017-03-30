package nettyTest.NIOTest.nettyWebsocket;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * Created by Administrator on 2017/3/30.
 */
public class WebSocketServer {

    public void run(int port) throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            //HttpServerCodec的作用是将请求和应答消息编码或者解码为HTTP消息;
                            ch.pipeline().addLast("http-codec", new HttpServerCodec());
                            //使用HttpObjectAggregator会把多个消息转换为一个单一的FullHttpRequest或是FullHttpResponse
                            ch.pipeline().addLast("http-aggregator", new HttpObjectAggregator(65536));
                            //支持处理异步发送大数据文件，但不占用过多的内存，防止发生内存泄漏,这里是向客户端发送html5文件
                            ch.pipeline().addLast("http-chunked", new ChunkedWriteHandler());
                            //这个是我们自定义的，处理文件服务器逻辑。主要功能还是在这个文件中
                            ch.pipeline().addLast("http-fileServerHandler", new WebSocketServerHandler());
                        }
                    });
            Channel ch  = b.bind(port).sync().channel();//这里写你本机的IP地址
            System.out.println("web socket server started at port "+port+".");
            System.out.println("open your browser and navigate to http://localhost:"+port+"/");
            ch.closeFuture().sync();
        } finally{
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        int port = 80;
        if (null != args && args.length > 0) {
            port = Integer.valueOf(args[0]);
        }

        new WebSocketServer().run(port);
    }
}
