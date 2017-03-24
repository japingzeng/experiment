package nettyTest.NIOTest.nettyHelloWorld.delimiter;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author japing
 * @Date 2017/3/24 14:48
 * @Description:
 */
public class EchoClientHandler extends ChannelHandlerAdapter{

    private static final Logger LOGGER = LoggerFactory.getLogger(EchoClientHandler.class);
    private int counter;
    static final String ECHO_REO = "Hi, Welcome to Netty.$_";

    public EchoClientHandler() {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 10; i++) {
            ctx.writeAndFlush(Unpooled.copiedBuffer(ECHO_REO.getBytes()));
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
       LOGGER.info("***[this is counter {}, times receive form server: {}]***", ++counter, msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
