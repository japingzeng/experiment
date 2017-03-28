package nettyTest.NIOTest.nettyHttpXml.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import nettyTest.NIOTest.util.HttpHeaderUtil;

import java.util.List;

/**
 * Created by Administrator on 2017/3/28.
 */
public class HttpXmlResponseEncoder extends
        AbstractHttpXmlEncoder<HttpXmlResponse> {
    /*
     * (non-Javadoc)
     *
     * @see
     * io.netty.handler.codec.MessageToMessageEncoder#encode(io.netty.channel
     * .ChannelHandlerContext, java.lang.Object, java.util.List)
     */
    @Override
    public void encode(ChannelHandlerContext ctx, HttpXmlResponse msg,
                          List<Object> out) throws Exception {
        ByteBuf body = encode0(ctx, msg.getResult());
        FullHttpResponse response = msg.getHttpResponse();
        if (response == null) {
            response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, body);
        } else {
            response = new DefaultFullHttpResponse(msg.getHttpResponse()
                    .getProtocolVersion(), msg.getHttpResponse().getStatus(),
                    body);
        }
        response.headers().set(HttpHeaders.Names.CONTENT_TYPE, "text/xml");
        HttpHeaderUtil.setContentLength(response, body.readableBytes());
        out.add(response);
    }
}
