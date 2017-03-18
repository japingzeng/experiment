package nettyTest.NIOTest;


/**
 * @Author japing
 * @Date 2017/3/17 11:44
 * @Description:
 */
public class TimeServer {

    public static void main(String[] args) {
        int port = 8080;
        if (null != args && args.length > 0) {
            port = Integer.valueOf(args[0]);
        }

        MultiplexerTimeServer timeServer = new MultiplexerTimeServer(port);
        new Thread(timeServer, "NIO-MultiplexerTimeServer-001").start();
    }
}
