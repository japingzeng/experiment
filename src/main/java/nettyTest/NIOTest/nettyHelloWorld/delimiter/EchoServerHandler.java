package nettyTest.NIOTest.nettyHelloWorld.delimiter;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author japing
 * @Date 2017/3/24 14:26
 * @Description:
 */
public class EchoServerHandler extends ChannelHandlerAdapter{

    private static final Logger LOGGER = LoggerFactory.getLogger(EchoServerHandler.class);
    int counter = 0;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String body = (String) msg;
        LOGGER.info("***[This is counter= {}, times receive client: {}]***", counter, body);
        body += "$_";
        ByteBuf echo = Unpooled.copiedBuffer(body.getBytes());
        ctx.writeAndFlush(echo);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
