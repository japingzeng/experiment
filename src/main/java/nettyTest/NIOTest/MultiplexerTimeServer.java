package nettyTest.NIOTest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

/**
 * @Author japing
 * @Date 2017/3/17 14:33
 * @Description:
 */
public class MultiplexerTimeServer implements Runnable{

    private static final Logger LOGGER = LoggerFactory.getLogger(MultiplexerTimeServer.class);
    private Selector selector;
    private ServerSocketChannel servChannel;
    private volatile boolean stop;

    /**
     * 初始化多路复用器、绑定监听端口
     * @param port
     */
    public MultiplexerTimeServer(int port) {
        try {
            selector = Selector.open();
            servChannel = ServerSocketChannel.open();
            servChannel.configureBlocking(false);
            servChannel.socket().bind(new InetSocketAddress(port), 1024);
            servChannel.register(selector, SelectionKey.OP_ACCEPT);
            LOGGER.info("the time server is start in port: {}", port);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void stop() {
        this.stop = true;
    }

    @Override
    public void run() {
        while (!stop) {
            try {
                selector.select(1000);
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> it = selectedKeys.iterator();
                SelectionKey key = null;
                while (it.hasNext()) {
                    key = it.next();
                    it.remove();
                    handleInput(key);
                    if (null != key) {
                        key.cancel();
                        if (null != key.channel()) {
                            key.channel().close();
                        }
                    }
                }
            } catch (IOException e) {
                LOGGER.error("", e);
            }
        }

        /**
         * 多路复用器关闭后，所有注册在上面的Channel 和  Pipe 等资源都会被自动去注册并关闭，所以不需要重复释放资源
         */
        if (null != selector) {
            try {
                selector.close();
            } catch (IOException e) {
                LOGGER.error("", e);
            }
        }
    }


    private void handleInput(SelectionKey key) throws IOException{
        if (key.isValid()) {
            //处理新接入的请求信息
            if (key.isAcceptable()) {
                // Accept the new connection
                ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                SocketChannel sc = ssc.accept();
                sc.configureBlocking(false);
                // Add the new connection to the selector
                sc.register(selector, SelectionKey.OP_READ);
            }
            if (key.isReadable()) {
                //read the data
                SocketChannel sc = (SocketChannel)key.channel();
                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                int readBytes = sc.read(readBuffer);
                if (readBytes > 0) {
                    readBuffer.flip();
                    byte[] bytes = new byte[readBuffer.remaining()];
                    readBuffer.get(bytes);
                    String body = new String(bytes, "UTF-8");
                    LOGGER.info("the time server recieve order: " + body);
                    String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new Date(System.currentTimeMillis()).toString() : "BAD ORDER";
                    doWrite(sc, currentTime);
                } else if (readBytes < 0) {
                    //对端链路关闭
                    key.cancel();
                    sc.close();
                } else {
                    ; //读到0字节，忽略
                }
            }
        }
    }

    private void doWrite(SocketChannel sc, String response) throws IOException {
        if (null != response && response.trim().length() > 0) {
            byte[] bytes = response.getBytes();
            ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
            writeBuffer.put(bytes);
            writeBuffer.flip();
            sc.write(writeBuffer);
        }
    }

    /**
     * @Author japing
     * @Date 2017/3/17 11:44
     * @Description:
     */
    public static class TimeServer {

        public static void main(String[] args) {
            int port = 8080;
            if (null != args && args.length > 0) {
                port = Integer.valueOf(args[0]);
            }

            MultiplexerTimeServer timeServer = new MultiplexerTimeServer(port);
            new Thread(timeServer, "NIO-MultiplexerTimeServer-001").start();
        }
    }

    /**
     * @Author japing
     * @Date 2017/3/17 14:57
     * @Description:
     */
    public static class TimeClient {

        public static void main(String[] args) {
            int port = 8080;
            if (null != args && args.length > 0) {
                port = Integer.valueOf(args[0]);
            }

            nettyTest.AioTest.TimeClientHandle timeClientHandle = new nettyTest.AioTest.TimeClientHandle("127.0.0.1", port);
            new Thread(timeClientHandle, "TimeClientHandle").start();

        }
    }
}
