package nettyTest.NIOTest.AioTest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.Date;

/**
 * @Author japing
 * @Date 2017/3/18 16:40
 * @Description:
 */
public class ReadCompletionHandler implements CompletionHandler<Integer, ByteBuffer>{

    private static final Logger LOGGER = LoggerFactory.getLogger(ReadCompletionHandler.class);
    private AsynchronousSocketChannel channel;

    public ReadCompletionHandler(AsynchronousSocketChannel channel) {
        if (null == this.channel) {
            this.channel = channel;
        }
    }

    @Override
    public void completed(Integer result, ByteBuffer attachment) {
        attachment.flip();
        byte[] body = new byte[attachment.remaining()];
        attachment.get(body);
        try {
            String req = new String(body, "UTF-8");
            LOGGER.info("***[the time server recieve order: {}]***", req);
            String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(req) ? new Date().toString() : "BAD ORDER";
            doWrite(currentTime);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    private void doWrite(final String currentTime) {
       if (StringUtils.isNotBlank(currentTime) && currentTime.trim().length() > 0) {
           byte[] bytes = currentTime.getBytes();
           ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
           writeBuffer.get(bytes);
           writeBuffer.flip();
           channel.write(writeBuffer, writeBuffer, new CompletionHandler<Integer, ByteBuffer>() {
               @Override
               public void completed(Integer result, ByteBuffer buffer) {
                   //如果没有发送完成，继续发送
                   if (buffer.hasRemaining()) {
                       channel.write(buffer, buffer, this);
                   }
               }

               @Override
               public void failed(Throwable exc, ByteBuffer attachment) {
                   try {
                       channel.close();
                   } catch (IOException e) {
                       e.printStackTrace();
                   }
               }
           });
       }
    }

    @Override
    public void failed(Throwable exc, ByteBuffer buffer) {
        try {
            this.channel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
