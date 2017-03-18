package nettyTest.NIOTest.AioTest;

import nettyTest.NIOTest.MultiplexerTimeServer;

/**
 * @Author japing
 * @Date 2017/3/18 17:42
 * @Description:
 */
public class TimeServer {

    public static void main(String[] args) {
        int port = 8080;
        if (null != args && args.length > 0) {
            port = Integer.valueOf(args[0]);
        }

        AsyncTimeServerHandler timeServer = new AsyncTimeServerHandler(port);
        new Thread(timeServer, "AIO-AsyncTimeServerHandler-001").start();
    }
}
