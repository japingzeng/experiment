package nettyTest.NIOTest;

/**
 * @Author japing
 * @Date 2017/3/17 14:57
 * @Description:
 */
public class TimeClient {

    public static void main(String[] args) {
        int port = 8080;
        if (null != args && args.length > 0) {
            port = Integer.valueOf(args[0]);
        }

        TimeClientHandle timeClientHandle = new TimeClientHandle("127.0.0.1", port);
        new Thread(timeClientHandle, "TimeClientHandle").start();

    }
}
