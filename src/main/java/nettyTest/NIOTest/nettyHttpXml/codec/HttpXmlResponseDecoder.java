package nettyTest.NIOTest.nettyHttpXml.codec;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.util.List;

/**
 * Created by Administrator on 2017/3/28.
 */
public class HttpXmlResponseDecoder extends AbstractHttpXmlDecoder<FullHttpResponse> {

    public HttpXmlResponseDecoder(Class<?> clazz) {
        this(clazz, false);
    }
    public HttpXmlResponseDecoder(Class<?> clazz, boolean isPrintlog) {
        super(clazz, isPrintlog);
    }
    @Override
    protected void decode(ChannelHandlerContext ctx, FullHttpResponse msg, List<Object> out) throws Exception {
        if (!msg.getDecoderResult().isSuccess()) {
            sendError(ctx, HttpResponseStatus.BAD_REQUEST);
            return;
        }
        HttpXmlResponse resHttpXmlResponse = new HttpXmlResponse(msg, decode0(
                ctx, msg.content()));
        out.add(resHttpXmlResponse);
    }
    private static void sendError(ChannelHandlerContext ctx,
                                  HttpResponseStatus status) {
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                status, Unpooled.copiedBuffer("Failure: " + status.toString()
                + "\r\n", CharsetUtil.UTF_8));
        response.headers().set(HttpHeaders.Names.CONTENT_TYPE, "text/plain; charset=UTF-8");
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }
}
