package experiment.other;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.omg.CORBA.PRIVATE_MEMBER;

import com.mysql.fabric.xmlrpc.base.Array;

/**
 * 
 * @ClassName: HashTest 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author japing 
 * @date 2016年12月14日 上午11:47:19 
 *
 */
public class HashTest {
	private static final char[] DIGITS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
	
	public static void main(String[] args) {
		ConcurrentHashMap<String, Long> cHashMap = new ConcurrentHashMap<>();
		cHashMap.put("first", 1L);
		Long first = cHashMap.get("first");
		long currentTime = System.currentTimeMillis();
		System.out.println("当前真实时间 ： " + Long.toBinaryString(currentTime) + "| " + currentTime + "| " + currentTime * 10000L);
		currentTime = System.currentTimeMillis()*10000L + 122192928000000000L;
		System.out.println("变化前 ：" + Long.toBinaryString(currentTime));
		currentTime = currentTime + 1L;
		System.out.println("当前时间+1 " + Long.toBinaryString(currentTime));
		
		long time = currentTime << 32;
		System.out.println("左移32 " + Long.toBinaryString(time));
		System.out.println("yidonghou :" + Long.toBinaryString(currentTime));
	//	time |= (currentTime & 281470681743360L) >> 16;
		System.out.println("&111111111111111132个0 :" + Long.toBinaryString(currentTime & 281470681743360L));
		System.out.println("右移动16 : " + Long.toBinaryString((currentTime & 281470681743360L) >> 16));
		time |= (currentTime & 281470681743360L) >> 16;
		System.out.println("1ge time |=: " + Long.toBinaryString(time));
    //    time |= 4096L | currentTime >> 48 & 4095L;
		//currentTime >> 48
		long right48 = currentTime >> 48;
        System.out.println("右移动48位 ： " + Long.toBinaryString(right48));
        long rightt48yu4095L = right48 & 4095L;
        System.out.println("&4095L : " + Long.toBinaryString(rightt48yu4095L));
        long huo4096rightt48yu4095L = 4096L | rightt48yu4095L;
        System.out.println("4096L| : " + Long.toBinaryString(huo4096rightt48yu4095L));
        time |= 4096L | currentTime >> 48 & 4095L;
        System.out.println("time |= " + Long.toBinaryString(time));
        
        List<String> list = getMac();
        for (String item : list) {
        	System.out.println(item);
        	System.out.println();
        }
        
        try {
			byte[] ex2 = InetAddress.getLocalHost().getAddress();
			System.out.println(ex2[0]);
			System.out.println(ex2[0] << 24);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        System.out.println("===========" + ((long)(Math.random() * 16383.0D) << 48));
        
        
		
	}
	
	
	
	
		static List<String> getMac() {
			List<String> macList = new ArrayList<>();
			try {
				Enumeration<NetworkInterface> nis = NetworkInterface.getNetworkInterfaces();
				while (nis.hasMoreElements()) {
					NetworkInterface ni = (NetworkInterface) nis.nextElement(); //获得当前迭代网络接口
					if (null != ni) {
						byte[] adress = ni.getHardwareAddress();
						if (null != adress && adress.length == 6 && adress[1] != -1) {
							macList.add(parseByte(new StringBuilder(), adress));
						}
					}
					
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			return macList;
		}
		
		static String parseByte(Appendable a, byte[] macBytes) {
			int length = macBytes.length;
			byte[] bytes = macBytes;
			try {
				for (int i = 0; i < length; i++) {
					a.append(DIGITS[((bytes[i] & 240) >> 4)]);
					a.append(DIGITS[(bytes[i] & 15)]);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}	
			return a.toString();
		}
	
	    
	    private int hash(Object k) {
	        int h = 0;
	        h ^= k.hashCode();

	        h += (h <<  15) ^ 0xffffcd7d;
	        h ^= (h >>> 10);
	        h += (h <<   3);
	        h ^= (h >>>  6);
	        h += (h <<   2) + (h << 14);
	        return h ^ (h >>> 16);
	    }

}
