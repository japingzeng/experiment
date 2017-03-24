package nettyTest.NIOTest.nettyHelloWorld.FixedLength;

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

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String body = (String) msg;
        LOGGER.info("***[receive client msg: {}]***", body);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
