package nettyTest.NIOTest.nettyHttpXml.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import nettyTest.NIOTest.nettyHttpXml.codec.HttpXmlRequest;
import nettyTest.NIOTest.nettyHttpXml.codec.HttpXmlResponse;
import nettyTest.NIOTest.pojo.OrderFactory;

/**
 * Created by Administrator on 2017/3/28.
 */
public class HttpXmlClientHandle extends
        SimpleChannelInboundHandler<HttpXmlResponse> {
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        HttpXmlRequest request = new HttpXmlRequest(null,
                OrderFactory.create(123));
        ctx.writeAndFlush(request);
    }
    @Override
    protected void messageReceived(ChannelHandlerContext ctx,
                                   HttpXmlResponse msg) throws Exception {
        System.out.println("The client receive response of http header is : "
                + msg.getHttpResponse().headers().names());
        System.out.println("The client receive response of http body is : "
                + msg.getResult());
    }
    private void printHttpMsg(ByteBuf byteBuf) {
        int len = byteBuf.readableBytes();
        byte[] bytes = new byte[len];
        byteBuf.getBytes(0, bytes);
        String s = new String(bytes);
        System.out.println(s);
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
