package idgenerate;

import idgenerate.util.DateUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;


public class timetest {
	private static final String datestr = "2016-12-07 00:00:00";
	private static final String format = "yyyy-MM-dd HH:mm:ss";
	public static void main(String[] args) {
//		long systime = System.currentTimeMillis();
//		System.out.println(systime);
//		System.out.println(Long.toHexString(systime));
//	    System.out.println(Long.toBinaryString(systime));
//	    ConcurrentHashMap<Long, Long> hasmap = new ConcurrentHashMap<>();
//	    long tmp = systime << 23;
//	    long test = systime ^ 0xffffffff;
//	    long test1 = test;
//	   
//	    System.out.println("test1=" + Long.toBinaryString(test1));
//	    
//	    System.out.println("tmp  =" + Long.toBinaryString(tmp));
//	    System.out.println(Long.toBinaryString(test));
//	    
//	    long machineHouse = 1;// << 38;
//	    System.out.println("machineHouse=" + Long.MAX_VALUE);
//	    long result = systime ^ machineHouse;
//	    System.out.println("result=" + Long.toBinaryString(result));
//	    Date fixedDate = DateUtils.getFormatDate(datestr, format);
//	    long fixed = fixedDate.getTime();
//	    System.out.println(Long.toBinaryString(fixed));
//	    Integer a = 0x1 ^ 0x0111;
//	    System.out.println(Integer.toBinaryString(a));
	    
//	    FutureTask<String> vFutureTask = new FutureTask<>(new Callable<String>() {
//
//			@Override
//			public String call() throws Exception {
//				// TODO Auto-generated method stub
//				return "hello";
//			}
//		});
//	    vFutureTask.run();
//	    try {
//			String result = vFutureTask.get();
//		} catch (InterruptedException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (ExecutionException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
	    
	    try {
			
//			
//			long b = System.currentTimeMillis();
//			
//			long e = System.currentTimeMillis();
//			System.out.println("b2=" +Long.toBinaryString(b));
//			System.out.println("e2=" + Long.toBinaryString(e));
//			System.out.println("b=" + b);
//			System.out.println("c=" + e);
//			System.out.println(e-b);
//			
//			
//			
//			long sequenceMask = -1L << 7L;
//			System.out.println(Long.toBinaryString(-1L));
//			System.out.println(Long.toBinaryString(sequenceMask));
//			System.out.println(Long.toBinaryString(-1L ^ sequenceMask));
//			
			InetAddress address = InetAddress.getLocalHost();
			System.out.println(address.getHostAddress());
			byte[] ipByreBytes = address.getAddress();
			hash(ipByreBytes);
			
			System.out.println((hash("10.186.55.2") % 127));
			System.out.println((hash("10.186.55.1") % 127));
			System.out.println((hash("10.186.55.0") % 127));
			System.out.println(hash("10.186.54.255") % 127);
			System.out.println((hash("10.186.230.236") % 127));
			System.out.println(hash("10.186.230.235") % 127);
			System.out.println(hash("10.186.230.232") % 127);
			System.out.println(hash("10.186.230.233") % 127);
			System.out.println(hash("10.186.131.136") % 127);
			System.out.println(hash("10.186.8.252") % 127);
			
			System.out.println(hash("10.186.8.250") % 127);
			System.out.println(hash("10.186.131.135") % 127);
			System.out.println(hash("10.186.8.249") % 127);
			
			System.out.println(hash("10.186.8.248") % 127);
			System.out.println(hash("10.186.8.251") % 127);
			System.out.println(hash("10.186.8.247") % 127);
			System.out.println(hash("10.186.8.246") % 127);
			System.out.println(hash("10.186.131.134") % 127);
			
			System.out.println(hash("10.186.8.244") % 127);
			System.out.println(hash("10.186.8.243") % 127);
			
			System.out.println(hash("10.186.8.245") % 127);
			System.out.println(hash("10.186.8.242") % 127);
			
			System.out.println(hash("10.186.8.241") % 127);
			System.out.println(hash("10.186.8.230") % 127);
			System.out.println(hash("10.186.8.229") % 127);
			System.out.println(hash("10.186.8.228") % 127);
			System.out.println(hash("10.186.8.227") % 127);
			System.out.println(hash("10.186.8.226") % 127);
			System.out.println(hash("10.186.8.225") % 127);
			System.out.println(hash("10.186.8.224") % 127);
			System.out.println(hash("10.186.8.223") % 127);
			System.out.println(hash("10.186.8.222") % 127);
			System.out.println(hash("10.186.8.221") % 127);
			
			System.out.println("======================================================================");
			System.out.println((hash("10.190.21.90") % 127));
			System.out.println(hash("10.190.21.81") % 127);
			System.out.println(hash("10.190.21.83") % 127);
			System.out.println((hash("10.190.21.82") % 127));
			System.out.println((hash("10.190.7.219") % 127));
			System.out.println((hash("10.190.7.220") % 127));
			System.out.println(hash("10.190.7.26") % 127);
			System.out.println((hash("10.190.7.25") % 127));
			System.out.println((hash("10.190.7.21") % 127));
			System.out.println((hash("10.190.7.23") % 127));
			System.out.println((hash("10.190.7.22") % 127));
			System.out.println((hash("10.190.7.24") % 127));
			System.out.println((hash("10.190.7.20") % 127));
			System.out.println((hash("10.190.7.19") % 127));
			System.out.println((hash("10.190.7.18") % 127));
			System.out.println((hash("10.190.7.17") % 127));
			System.out.println((hash("10.190.7.1") % 127));
			System.out.println((hash("10.190.7.2") % 127));
			System.out.println((hash("10.190.7.3") % 127));
			System.out.println((hash("10.190.7.4") % 127));
			System.out.println((hash("10.190.7.5") % 127));
			System.out.println((hash("10.190.7.6") % 127));
			System.out.println((hash("10.190.8.7") % 127));
			System.out.println((hash("10.190.9.7") % 127));
			System.out.println((hash("10.190.10.7") % 127));
			System.out.println((hash("10.190.11.7") % 127));
			System.out.println((hash("10.190.12.7") % 127));
			System.out.println((hash("10.190.13.7") % 127));
			System.out.println((hash("10.190.14.7") % 127));
			System.out.println((hash("10.190.15.7") % 127));
			System.out.println((hash("10.190.16.7") % 127));
//			hash("10.190.7.17");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   
	}
	
//	 static int hash(Object k) {
//	        int h = 0;
//	        h ^= k.hashCode();
//
//	        h += (h <<  15) ^ 0xffffcd7d;
//	        h ^= (h >>> 10);
//	        h += (h <<   3);
//	        h ^= (h >>>  6);
//	        h += (h <<   2) + (h << 14);
//	        return h ^ (h >>> 16);
//	    }
	 private static List<Integer> list = new ArrayList<>();
	 static int hash(String ip) {
		 String[] ipStr = ip.split("\\.");
		 int subNetBits = (Integer.valueOf(ipStr[2]).intValue() & 0xff) << 8;
		 int hostsBits = (Integer.valueOf(ipStr[3]).intValue() & 0xff);
		 int num = subNetBits | hostsBits;
		 System.out.println("[二进制]： " + Integer.toBinaryString(num) + " [十进制]: " + num);
		 if (list.contains(num)) {
			 System.out.println(String.format("[数字num%d重复]", num));
			 
		 }
		 list.add(num);
		 return num;
	 }
	 
	 /**
	  * 
	  * @param ipByte
	  * @return
	  * @Description: TODO(这里用一句话描述这个类的作用) 
	  * @author japing 
	  * @date 2016年12月14日 下午5:01:30
	  */
	 static int hash(byte[] ipByte) {
		 for (byte item : ipByte) {
			 System.out.println("[二进制] " + Integer.toBinaryString(item & 0xff) + "十进制item" + (item & 0xff));
		 }
		 int subNetBits = ipByte[2] & 0xff;
		 System.out.println(String.format("subNetNum%d", subNetBits));
		 
		 int hostsBits = ipByte[3] & 0xff;
		 System.out.println(String.format("hostsBits%d", hostsBits));
		 
		 int num = (subNetBits << 8) | ((hostsBits & 0xff));
		 
		 System.out.println(String.format("num=%d ", num));
		 
		 return num;
	 }
	 
	 /**
	  *  使用 LRANGE list1 0 -1 获取redis中存储的所有服务器ip对应的编号0-127
	  *  
	  *  RPUSH mylist "hello" 往该列表里存入当前服务器ip对应的编号，该编号是经过hash得到，得到该编号后与从redis拉取到的list进行contains操作，如果包含，进行加一操作
	  */
}
