package idgenerate;

import java.net.InetAddress;
import java.net.NetworkInterface;

/**
 * Created by Administrator on 2017/3/10.
 */
public class MacTest {
    public static void main(String[] args) {
        long id = 0L;
        try {
            InetAddress ip = InetAddress.getLocalHost();
            NetworkInterface network = NetworkInterface.getByInetAddress(ip);
            if (network == null) {
                id = 1L;
            } else {
                byte[] mac = network.getHardwareAddress();
                for (byte i : mac) {
                    System.out.println(i);
                }
            }
        } catch (Exception e) {
            System.out.println(" getDatacenterId: " + e.getMessage());
        }
    }
}
